/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.common.specialreview.rules;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.ValidSpecialReviewApproval;
import org.kuali.kra.common.specialreview.bo.SpecialReview;
import org.kuali.kra.common.specialreview.bo.SpecialReviewExemption;
import org.kuali.kra.common.specialreview.rule.event.AddSpecialReviewEvent;
import org.kuali.kra.common.specialreview.rule.event.SaveSpecialReviewEvent;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolFinderDao;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.util.GlobalVariables;
import org.springframework.util.CollectionUtils;

/**
 * This class validates all rules associated with SpecialReview.
 * @param <T> Special Review
 */
public class SpecialReviewRuleBase<T extends SpecialReview<? extends SpecialReviewExemption>> extends ResearchDocumentRuleBase {
    
    private static final String DOT = ".";
    
    private static final String APPROVAL_TYPE_CODE_APPROVED = "2";
    
    private static final String PROTOCOL_NUMBER_FIELD = "protocolNumber";
    private static final String PROTOCOL_NUMBER_TITLE = "Protocol Number";
    private static final String APPLICATION_DATE_FIELD = "applicationDate";
    private static final String APPLICATION_DATE_TITLE = "Application Date";
    private static final String APPROVAL_DATE_FIELD = "approvalDate";
    private static final String APPROVAL_DATE_TITLE = "Approval Date";
    private static final String EXPIRATION_DATE_FIELD = "expirationDate";
    private static final String EXPIRATION_DATE_TITLE = "Expiration Date";
    private static final String EXEMPTION_TYPE_CODE_FIELD = "exemptionTypeCodes";
    private static final String EXEMPTION_TYPE_CODE_TITLE = "Exemption #";
    
    private ProtocolFinderDao protocolFinderDao;
    
    /**
     * Runs the rules for adding a specialReview.
     * 
     * @param addSpecialReviewEvent The event invoking the add specialReview rules
     * @return True if the specialReview is valid, false otherwise
     */
    protected boolean processAddSpecialReviewEvent(AddSpecialReviewEvent<T> addSpecialReviewEvent) {
        boolean rulePassed = true;
        
        T specialReview = addSpecialReviewEvent.getSpecialReview();
        String errorPathPrefix = addSpecialReviewEvent.getErrorPathPrefix();
        boolean validateProtocol = addSpecialReviewEvent.getValidateProtocol();
        
        getDictionaryValidationService().validateBusinessObject(specialReview);
        rulePassed &= GlobalVariables.getMessageMap().hasNoErrors();
        rulePassed &= validateDateFields(specialReview, errorPathPrefix);
        rulePassed &= validateSpecialReviewApprovalFields(specialReview, errorPathPrefix, validateProtocol);

        return rulePassed;
    }
    
    /**
     * Runs the rules for saving specialReviews.
     * 
     * @param saveSpecialReviewEvent The event invoking the save specialReview rules
     * @return True if the specialReviews are valid, false otherwise
     */
    protected boolean processSaveSpecialReviewEvent(SaveSpecialReviewEvent<T> saveSpecialReviewEvent) {
        boolean rulePassed = true;
        
        List<T> specialReviews = saveSpecialReviewEvent.getSpecialReviews();
        int i = 0;
        for (T specialReview : specialReviews) {
            String errorPath = saveSpecialReviewEvent.getErrorPathPrefix() + "[" + i++ + "]";
            boolean validateProtocol = saveSpecialReviewEvent.getValidateProtocol();
            rulePassed &= validateDateFields(specialReview, errorPath);
            rulePassed &= validateSpecialReviewApprovalFields(specialReview, errorPath, validateProtocol);
        }
        
        return rulePassed;
    }
    
    /**
     * Validates the interdependencies between the different date fields and the statuses.
     * 
     * @param specialReview The special review object to validate
     * @param errorPath The error path
     * @return true if the specialReview is valid, false otherwise
     */
    private boolean validateDateFields(T specialReview, String errorPath) {
        boolean isValid = true;
        
        if (specialReview.getApplicationDate() != null && specialReview.getApprovalDate() != null 
                && specialReview.getApprovalDate().before(specialReview.getApplicationDate())) {
            isValid = false;
            reportErrorWithoutFullErrorPath(errorPath + DOT + APPROVAL_DATE_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_DATE_SAME_OR_LATER, 
                    APPROVAL_DATE_TITLE, APPLICATION_DATE_TITLE);
        }
        if (specialReview.getApprovalDate() != null && specialReview.getExpirationDate() != null
                && specialReview.getExpirationDate().before(specialReview.getApprovalDate())) {
            isValid = false;
            reportErrorWithoutFullErrorPath(errorPath + DOT + EXPIRATION_DATE_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_DATE_LATER, 
                    EXPIRATION_DATE_TITLE, APPROVAL_DATE_TITLE);
        }
        if (specialReview.getApplicationDate() != null && specialReview.getExpirationDate() != null
                && specialReview.getExpirationDate().before(specialReview.getApplicationDate())) {
            isValid = false;
            reportErrorWithoutFullErrorPath(errorPath + DOT + EXPIRATION_DATE_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_DATE_LATER, 
                    EXPIRATION_DATE_TITLE, APPLICATION_DATE_TITLE);
        }
        if (!APPROVAL_TYPE_CODE_APPROVED.equals(specialReview.getApprovalTypeCode()) && specialReview.getApprovalDate() != null) {
            isValid = false;
            reportErrorWithoutFullErrorPath(errorPath + DOT + APPROVAL_DATE_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_EMPTY_FOR_NOT_APPROVED, 
                    APPROVAL_DATE_TITLE);
        }
        
        return isValid;
    }
    
    /**
     * Validates the rules surrounding the ValidSpecialReviewApproval.
     * @param specialReview The special review to validate
     * @param errorPath The error path
     * @param validateProtocol Whether or not to validate whether the given protocol number refers to an existing Protocol
     * @return true if the specialReview is valid, false otherwise
     */
    @SuppressWarnings("unchecked")
    private boolean validateSpecialReviewApprovalFields(T specialReview, String errorPath, boolean validateProtocol) {
        boolean isValid = true;
        
        if (StringUtils.isNotBlank(specialReview.getSpecialReviewTypeCode()) && StringUtils.isNotBlank(specialReview.getApprovalTypeCode())) {
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put("specialReviewTypeCode", specialReview.getSpecialReviewTypeCode());
            fieldValues.put("approvalTypeCode", specialReview.getApprovalTypeCode());
            Collection<ValidSpecialReviewApproval> validApprovals = getBusinessObjectService().findMatching(ValidSpecialReviewApproval.class, fieldValues);

            for (ValidSpecialReviewApproval validApproval : validApprovals) {
                isValid &= validateApprovalFields(validApproval, specialReview, errorPath, validateProtocol);
            }
        }
        
        return isValid;
    }
    
    /**
     * Validates the rules that determine whether certain fields are required in certain circumstances.
     * 
     * @param approval The maintenance document that determines whether a field is required
     * @param specialReview The special review to validate
     * @param errorPath The error path
     * @param validateProtocol Whether or not to validate whether the given protocol number refers to an existing Protocol
     * @return true if the specialReview is valid, false otherwise
     */
    private boolean validateApprovalFields(ValidSpecialReviewApproval approval, T specialReview, String errorPath, boolean validateProtocol) {
        boolean isValid = true;

        if (approval.isProtocolNumberFlag()) {
            if (StringUtils.isBlank(specialReview.getProtocolNumber())) {
                isValid = false;
                reportErrorWithoutFullErrorPath(errorPath + DOT + PROTOCOL_NUMBER_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_REQUIRED_FOR_VALID, 
                        PROTOCOL_NUMBER_TITLE, getValidSpecialReviewApprovalErrorString(approval));
            } else {
                if (validateProtocol) {
                    Protocol protocol = getProtocolFinderDao().findCurrentProtocolByNumber(specialReview.getProtocolNumber());
                    if (protocol == null) {
                        isValid = false;
                        reportErrorWithoutFullErrorPath(errorPath + DOT + PROTOCOL_NUMBER_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_PROTOCOL_NUMBER_INVALID);
                    }
                }
            }
        }
        if (approval.isApplicationDateFlag() && specialReview.getApplicationDate() == null) {
            isValid = false;
            reportErrorWithoutFullErrorPath(errorPath + DOT + APPLICATION_DATE_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_REQUIRED_FOR_VALID, 
                    APPLICATION_DATE_TITLE, getValidSpecialReviewApprovalErrorString(approval));
        }
        if (approval.isApprovalDateFlag() && specialReview.getApprovalDate() == null) {
            isValid = false;
            reportErrorWithoutFullErrorPath(errorPath + DOT + APPROVAL_DATE_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_REQUIRED_FOR_VALID, 
                    APPROVAL_DATE_TITLE, getValidSpecialReviewApprovalErrorString(approval));
        }
        if (approval.isExemptNumberFlag() && CollectionUtils.isEmpty(specialReview.getExemptionTypeCodes())) {
            isValid = false;
            reportErrorWithoutFullErrorPath(errorPath + DOT + EXEMPTION_TYPE_CODE_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_REQUIRED_FOR_VALID, 
                    EXEMPTION_TYPE_CODE_TITLE, getValidSpecialReviewApprovalErrorString(approval));
        }
        if (!approval.isExemptNumberFlag() && specialReview.getExemptionTypeCodes() != null && !specialReview.getExemptionTypeCodes().isEmpty()) {
            isValid = false;
            reportErrorWithoutFullErrorPath(errorPath + DOT + EXEMPTION_TYPE_CODE_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_CANNOT_SELECT_EXEMPTION_FOR_VALID, 
                    getValidSpecialReviewApprovalErrorString(approval));
        }
        
        return isValid;
    }
    
    /**
     * Composes the String used when reporting specifics of a ValidSpecialReviewApproval error.
     * @param validSpecialReviewApproval
     * @return the correct error string for this validSpecialReviewApproval
     */
    private String getValidSpecialReviewApprovalErrorString(ValidSpecialReviewApproval validSpecialReviewApproval) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(validSpecialReviewApproval.getSpecialReviewType().getDescription());
        stringBuilder.append("/");
        stringBuilder.append(validSpecialReviewApproval.getSpecialReviewApprovalType().getDescription());
        return stringBuilder.toString();
    }
    
    public ProtocolFinderDao getProtocolFinderDao() {
        if (protocolFinderDao == null) {
            protocolFinderDao = KraServiceLocator.getService(ProtocolFinderDao.class);
        }
        return protocolFinderDao;
    }

    public void setProtocolFinderDao(ProtocolFinderDao protocolFinderDao) {
        this.protocolFinderDao = protocolFinderDao;
    }
    
}