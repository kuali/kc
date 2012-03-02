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

import java.sql.Date;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.SpecialReviewApprovalType;
import org.kuali.kra.bo.SpecialReviewType;
import org.kuali.kra.bo.ValidSpecialReviewApproval;
import org.kuali.kra.common.specialreview.bo.SpecialReview;
import org.kuali.kra.common.specialreview.bo.SpecialReviewExemption;
import org.kuali.kra.common.specialreview.rule.event.AddSpecialReviewEvent;
import org.kuali.kra.common.specialreview.rule.event.SaveSpecialReviewEvent;
import org.kuali.kra.common.specialreview.rule.event.SaveSpecialReviewLinkEvent;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolFinderDao;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.util.CollectionUtils;

/**
 * This class validates all rules associated with SpecialReview.
 * @param <T> Special Review
 */
public class SpecialReviewRuleBase<T extends SpecialReview<? extends SpecialReviewExemption>> extends ResearchDocumentRuleBase {
    
    private static final String TYPE_CODE_FIELD = "specialReviewTypeCode";
    private static final String APPROVAL_TYPE_CODE_FIELD = "approvalTypeCode";
    
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
    
    private static final String HUMAN_SUBJECTS_LINK_TO_IRB_ERROR_STRING = "Human Subjects/Link to IRB";
    
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
        List<T> specialReviews = addSpecialReviewEvent.getSpecialReviews();
        boolean validateProtocol = addSpecialReviewEvent.getIsProtocolLinkingEnabled();
        
        getDictionaryValidationService().validateBusinessObject(specialReview);
        rulePassed &= GlobalVariables.getMessageMap().hasNoErrors();
        if (validateProtocol && SpecialReviewType.HUMAN_SUBJECTS.equals(specialReview.getSpecialReviewTypeCode())) {
            rulePassed &= validateProtocolNumber(specialReview, specialReviews, HUMAN_SUBJECTS_LINK_TO_IRB_ERROR_STRING);
        } else {
            rulePassed &= validateSpecialReviewApprovalFields(specialReview);
            rulePassed &= validateDateFields(specialReview);
        }

        return rulePassed;
    }
    
    /**
     * Runs the rules for saving the special reviews that are currently linked to a protocol.
     *
     * @param saveSpecialReviewLinkEvent The event invoking the save specialReview rules
     * @return True if the specialReview is valid, false otherwise
     */
    protected boolean processSaveSpecialReviewLinkEvent(SaveSpecialReviewLinkEvent<T> saveSpecialReviewLinkEvent) {
        boolean rulePassed = true;
        
        for (T specialReview : saveSpecialReviewLinkEvent.getSpecialReviews()) {
            if (SpecialReviewType.HUMAN_SUBJECTS.equals(specialReview.getSpecialReviewTypeCode())) {
                rulePassed &= validateLinkingDocument(specialReview.getProtocolNumber());
            }
        }
        
        for (String linkedProtocolNumber : saveSpecialReviewLinkEvent.getLinkedProtocolNumbers()) {
            rulePassed &= validateLinkingDocument(linkedProtocolNumber);
        }
        
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
        boolean validateProtocol = saveSpecialReviewEvent.getValidateProtocol();
        
        int i = 0;
        for (T specialReview : specialReviews) {
            String errorPath = saveSpecialReviewEvent.getArrayErrorPathPrefix() + Constants.LEFT_SQUARE_BRACKET + i++ + Constants.RIGHT_SQUARE_BRACKET;
            
            GlobalVariables.getMessageMap().addToErrorPath(errorPath);
            if (validateProtocol && SpecialReviewType.HUMAN_SUBJECTS.equals(specialReview.getSpecialReviewTypeCode())) {
                rulePassed &= validateProtocolNumber(specialReview, specialReviews, HUMAN_SUBJECTS_LINK_TO_IRB_ERROR_STRING);
            } else {
                rulePassed &= validateSpecialReviewApprovalFields(specialReview);
                rulePassed &= validateDateFields(specialReview);
            }
            GlobalVariables.getMessageMap().removeFromErrorPath(errorPath);
        }
        
        return rulePassed;
    }
    
    /**
     * Validates whether the Protocol Document of the given protocol number is not locked.
     * 
     * @param protocolNumber the protocol number
     * @return true if the specialReview is valid, false otherwise
     */
    private boolean validateLinkingDocument(String protocolNumber) {
        boolean isValid = true;
        
        if (StringUtils.isNotBlank(protocolNumber)) {
            Protocol protocol = getProtocolFinderDao().findCurrentProtocolByNumber(protocolNumber);
            if (protocol != null) {
                protocol.getProtocolDocument().refreshPessimisticLocks();
                if (!protocol.getProtocolDocument().getPessimisticLocks().isEmpty()) {
                    isValid = false;
                    reportError(PROTOCOL_NUMBER_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_PROTOCOL_LOCKED, protocolNumber);
                }
            }
        }
        
        return isValid;
    }
    
    /**
     * Validates the rules surrounding the protocol number.
     * 
     * @param specialReview The special review to validate
     * @param specialReviews The existing special reviews
     * @param errorString The error string for the type / approval of this special review
     * @return true if the specialReview is valid, false otherwise
     */
    @SuppressWarnings("unchecked")
    private boolean validateProtocolNumber(T specialReview, List<T> specialReviews, String errorString) {
        boolean isValid = true;
        
        if (StringUtils.isBlank(specialReview.getProtocolNumber())) {
            isValid = false;
            reportError(PROTOCOL_NUMBER_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_REQUIRED_FOR_VALID, PROTOCOL_NUMBER_TITLE, errorString);
        } else {
            Protocol protocol = getProtocolFinderDao().findCurrentProtocolByNumber(specialReview.getProtocolNumber());
            if (protocol == null) {
                isValid = false;
                reportError(PROTOCOL_NUMBER_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_PROTOCOL_NUMBER_INVALID);
            } else {
                List<T> existingSpecialReviews = ListUtils.subtract(specialReviews, Collections.singletonList(specialReview));
                for (T existingSpecialReview : existingSpecialReviews) {
                    if (StringUtils.equals(specialReview.getProtocolNumber(), existingSpecialReview.getProtocolNumber())) {
                        isValid = false;
                        reportError(PROTOCOL_NUMBER_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_PROTOCOL_NUMBER_DUPLICATE);
                    }
                }
            }
        }
        
        return isValid;
    }
    
    /**
     * Validates the rules surrounding the ValidSpecialReviewApproval.
     * 
     * @param specialReview The special review to validate
     * @return true if the specialReview is valid, false otherwise
     */
    @SuppressWarnings("unchecked")
    private boolean validateSpecialReviewApprovalFields(T specialReview) {
        boolean isValid = true;
        
        if (StringUtils.isNotBlank(specialReview.getSpecialReviewTypeCode()) && StringUtils.isNotBlank(specialReview.getApprovalTypeCode())) {
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put(TYPE_CODE_FIELD, specialReview.getSpecialReviewTypeCode());
            fieldValues.put(APPROVAL_TYPE_CODE_FIELD, specialReview.getApprovalTypeCode());
            Collection<ValidSpecialReviewApproval> validApprovals = getBusinessObjectService().findMatching(ValidSpecialReviewApproval.class, fieldValues);

            for (ValidSpecialReviewApproval validApproval : validApprovals) {
                String validApprovalErrorString = getValidApprovalErrorString(validApproval);
                isValid &= validateApprovalFields(validApproval, specialReview, validApprovalErrorString);
            }
        }
        
        return isValid;
    }
    
    /**
     * Composes the String used when reporting specifics of a ValidSpecialReviewApproval error.
     * 
     * @param approval The maintenance document that determines whether a field is required
     * @return the correct error string for this validSpecialReviewApproval
     */
    private String getValidApprovalErrorString(ValidSpecialReviewApproval approval) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(approval.getSpecialReviewType().getDescription());
        stringBuilder.append("/");
        stringBuilder.append(approval.getSpecialReviewApprovalType().getDescription());
        return stringBuilder.toString();
    }
    
    /**
     * Validates the rules that determine whether certain fields are required in certain circumstances.
     * 
     * @param approval The maintenance document that determines whether a field is required
     * @param specialReview The special review to validate
     * @param errorString The error string for the type / approval of this special review
     * @return true if the specialReview is valid, false otherwise
     */
    private boolean validateApprovalFields(ValidSpecialReviewApproval approval, T specialReview, String errorString) {
        boolean isValid = true;
        
        if (approval.isProtocolNumberFlag() && StringUtils.isBlank(specialReview.getProtocolNumber())) {
            isValid = false;
            reportError(PROTOCOL_NUMBER_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_REQUIRED_FOR_VALID, PROTOCOL_NUMBER_TITLE, errorString);
        }
        if (approval.isApplicationDateFlag() && specialReview.getApplicationDate() == null) {
            isValid = false;
            reportError(APPLICATION_DATE_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_REQUIRED_FOR_VALID, APPLICATION_DATE_TITLE, errorString);
        }
        if (approval.isApprovalDateFlag() && specialReview.getApprovalDate() == null) {
            isValid = false;
            reportError(APPROVAL_DATE_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_REQUIRED_FOR_VALID, APPROVAL_DATE_TITLE, errorString);
        }
        if (approval.isExemptNumberFlag()) {
            if (CollectionUtils.isEmpty(specialReview.getExemptionTypeCodes())) {
                isValid = false;
                reportError(EXEMPTION_TYPE_CODE_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_REQUIRED_FOR_VALID, EXEMPTION_TYPE_CODE_TITLE, errorString);
            }
        } else {
            if (!CollectionUtils.isEmpty(specialReview.getExemptionTypeCodes())) {
                isValid = false;
                reportError(EXEMPTION_TYPE_CODE_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_CANNOT_SELECT_EXEMPTION_FOR_VALID, errorString);
            }
        }
        
        return isValid;
    }
    
    /**
     * Validates the interdependencies between the different date fields and the statuses.
     * 
     * @param specialReview The special review object to validate
     * @return true if the specialReview is valid, false otherwise
     */
    private boolean validateDateFields(T specialReview) {
        boolean isValid = true;
        
        isValid &= validateDateOrder(specialReview.getApplicationDate(), specialReview.getApprovalDate(), APPROVAL_DATE_FIELD, APPLICATION_DATE_TITLE, 
            APPROVAL_DATE_TITLE);
        isValid &= validateDateOrder(specialReview.getApprovalDate(), specialReview.getExpirationDate(), EXPIRATION_DATE_FIELD, APPROVAL_DATE_TITLE, 
            EXPIRATION_DATE_TITLE);
        isValid &= validateDateOrder(specialReview.getApplicationDate(), specialReview.getExpirationDate(), EXPIRATION_DATE_FIELD, APPLICATION_DATE_TITLE, 
            EXPIRATION_DATE_TITLE);

        if (!SpecialReviewApprovalType.APPROVED.equals(specialReview.getApprovalTypeCode()) && specialReview.getApprovalDate() != null) {
            isValid = false;
            reportError(APPROVAL_DATE_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_EMPTY_FOR_NOT_APPROVED, APPROVAL_DATE_TITLE);
        }
        
        return isValid;
    }
    
    /**
     * Validates that the first date is before or the same as the second date.
     * 
     * @param firstDate The first date
     * @param secondDate The second date
     * @param errorField The field on which to display the error
     * @param firstDateTitle The title of the first date field
     * @param secondDateTitle The title of the second date field
     * @return
     */
    private boolean validateDateOrder(Date firstDate, Date secondDate, String errorField, String firstDateTitle, String secondDateTitle) {
        boolean isValid = true;
        
        if (firstDate != null && secondDate != null && secondDate.before(firstDate)) {
            isValid = false;
            reportError(errorField, KeyConstants.ERROR_SPECIAL_REVIEW_DATE_SAME_OR_LATER, secondDateTitle, firstDateTitle);
        }
        
        return isValid;
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