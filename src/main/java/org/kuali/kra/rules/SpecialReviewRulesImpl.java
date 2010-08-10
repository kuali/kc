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
package org.kuali.kra.rules;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.AbstractSpecialReview;
import org.kuali.kra.bo.ValidSpecialReviewApproval;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rule.SpecialReviewRule;
import org.kuali.kra.rule.event.AddSpecialReviewEvent;
import org.kuali.kra.rule.event.SaveSpecialReviewEvent;

/**
 * This class validates all rules associated with SpecialReview.
 */
@SuppressWarnings("unchecked")
public class SpecialReviewRulesImpl<T extends AbstractSpecialReview> extends ResearchDocumentRuleBase implements SpecialReviewRule<T> {
    
    private static final String DOT = ".";
    
    private static final String APPROVAL_TYPE_CODE_APPROVED = "2";
    
    private static final String APPROVAL_TYPE_CODE_FIELD = "approvalTypeCode";
    private static final String SPECIAL_REVIEW_CODE_FIELD = "specialReviewCode";
    
    private static final String PROTOCOL_NUMBER_FIELD = "protocolNumber";
    private static final String PROTOCOL_NUMBER_TITLE = "Protocol Number";
    private static final String APPLICATION_DATE_FIELD = "applicationDate";
    private static final String APPLICATION_DATE_TITLE = "Application Date";
    private static final String APPROVAL_DATE_FIELD = "approvalDate";
    private static final String APPROVAL_DATE_TITLE = "Approval Date";
    private static final String EXPIRATION_DATE_FIELD = "expirationDate";
    private static final String EXPIRATION_DATE_TITLE = "Expiration Date";
    private static final String EXEMPTION_TYPE_CODE_FIELD = "exemptionTypeCodes";
    private static final String EXEMPTION_TYPE_CODE_TITLE = "Exemption Number";
    
    private String rootErrorPath;
    
    /**
     * Constructs a SpecialReviewRulesImpl.java.
     */
    public SpecialReviewRulesImpl() {
        this("newSpecialReview");
    }
   
    /**
     * Constructs a SpecialReviewRulesImpl.java.
     * @param rootErrorPath
     */
    public SpecialReviewRulesImpl(String rootErrorPath) {
        this.rootErrorPath = rootErrorPath;
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.rule.SpecialReviewRule#processAddSpecialReviewEvent(org.kuali.kra.rule.event.AddSpecialReviewEvent)
     */
    public boolean processAddSpecialReviewEvent(AddSpecialReviewEvent<T> addSpecialReviewEvent) {
        boolean rulePassed = true;
        
        rulePassed &= validateRequiredFields(addSpecialReviewEvent.getSpecialReview(), rootErrorPath);
        rulePassed &= validateDateFields(addSpecialReviewEvent.getSpecialReview(), rootErrorPath);
        rulePassed &= validateApprovalFields(addSpecialReviewEvent.getSpecialReview(), rootErrorPath);

        return rulePassed;
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.rule.SpecialReviewRule#processSaveSpecialReviewEvent(org.kuali.kra.rule.event.SaveSpecialReviewEvent)
     */
    public boolean processSaveSpecialReviewEvent(SaveSpecialReviewEvent saveSpecialReviewEvent) {
        boolean rulePassed = true;
        
        List<T> specialReviews = saveSpecialReviewEvent.getSpecialReviews();
        int i = 0;
        for (T specialReview : specialReviews) {
            String errorPath = rootErrorPath + "[" + i++ + "]";
            rulePassed &= validateRequiredFields(specialReview, errorPath);
            rulePassed &= validateDateFields(specialReview, errorPath);
            rulePassed &= validateApprovalFields(specialReview, errorPath);
        }
        
        return rulePassed;
    }
    
    /**
     * Validates the required fields.
     * @param specialReview The special review to validate
     * @param errorPath The error path
     * @return true if the specialReview is valid, false otherwise
     */
    private boolean validateRequiredFields(T specialReview, String errorPath) {
        boolean isValid = true;
        
        if (StringUtils.isBlank(specialReview.getApprovalTypeCode())) {
            isValid = false;
            reportError(errorPath + DOT + APPROVAL_TYPE_CODE_FIELD, KeyConstants.ERROR_REQUIRED_SELECT_APPROVAL_STATUS);
        }
        if (StringUtils.isBlank(specialReview.getSpecialReviewCode())){
            isValid = false;
            reportError(errorPath + DOT + SPECIAL_REVIEW_CODE_FIELD, KeyConstants.ERROR_REQUIRED_SELECT_SPECIAL_REVIEW_CODE);
        }
        if (!APPROVAL_TYPE_CODE_APPROVED.equals(specialReview.getApprovalTypeCode()) && specialReview.getApprovalDate() != null) {
            isValid = false;
            reportError(errorPath + DOT + APPROVAL_DATE_FIELD, KeyConstants.ERROR_NOT_APPROVED_SPECIALREVIEW, APPROVAL_DATE_TITLE);
        }
        
        return isValid;
    }
    
    /**
     * Validates the interdependencies between the different date fields and the statuses.
     * @param specialReview
     * @param errorPath The error path
     * @return true if the specialReview is valid, false otherwise
     */
    private boolean validateDateFields(T specialReview, String errorPath) {
        boolean isValid = true;
        
        if (specialReview.getApplicationDate() != null && specialReview.getApprovalDate() != null 
                && specialReview.getApprovalDate().before(specialReview.getApplicationDate())) {
            isValid = false;
            reportError(errorPath + DOT + APPROVAL_DATE_FIELD, KeyConstants.ERROR_APPROVAL_DATE_BEFORE_APPLICATION_DATE_SPECIALREVIEW, APPROVAL_DATE_TITLE, 
                    APPLICATION_DATE_TITLE);
        }
        if (specialReview.getApprovalDate() != null && specialReview.getExpirationDate() != null
                && specialReview.getExpirationDate().before(specialReview.getApprovalDate())) {
            isValid = false;
            reportError(errorPath + DOT + EXPIRATION_DATE_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_DATE_ORDERING, EXPIRATION_DATE_TITLE,
                    APPROVAL_DATE_TITLE);
        }
        if (specialReview.getApplicationDate() != null && specialReview.getExpirationDate() != null
                && specialReview.getExpirationDate().before(specialReview.getApplicationDate())) {
            isValid = false;
            reportError(errorPath + DOT + EXPIRATION_DATE_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_DATE_ORDERING, EXPIRATION_DATE_TITLE,
                    APPLICATION_DATE_TITLE);
        }
        
        return isValid;
    }
    
    /**
     * Validates the rules surrounding the ValidSpecialReviewApproval.
     * @param specialReview The special review to validate
     * @param errorPath The error path
     * @return true if the specialReview is valid, false otherwise
     */
    private boolean validateApprovalFields(T specialReview, String errorPath) {
        boolean isValid = true;
        
        if (StringUtils.isNotBlank(specialReview.getApprovalTypeCode()) && StringUtils.isNotBlank(specialReview.getSpecialReviewCode())) {
            specialReview.refreshReferenceObject("validSpecialReviewApproval");
            ValidSpecialReviewApproval validSpecialReviewApproval = specialReview.getValidSpecialReviewApproval();
            
            if (validSpecialReviewApproval != null) {
                if (validSpecialReviewApproval.isProtocolNumberFlag() && StringUtils.isBlank(specialReview.getProtocolNumber())) {
                    isValid = false;
                    reportError(errorPath + DOT + PROTOCOL_NUMBER_FIELD, KeyConstants.ERROR_REQUIRED_FOR_VALID_SPECIALREVIEW, PROTOCOL_NUMBER_TITLE,
                            getValidSpecialReviewApprovalErrorString(validSpecialReviewApproval));
                }
                if (validSpecialReviewApproval.isApplicationDateFlag() && specialReview.getApplicationDate() == null) {
                    isValid = false;
                    reportError(errorPath + DOT + APPLICATION_DATE_FIELD, KeyConstants.ERROR_REQUIRED_FOR_VALID_SPECIALREVIEW, PROTOCOL_NUMBER_TITLE,
                            getValidSpecialReviewApprovalErrorString(validSpecialReviewApproval));
                }
                if (validSpecialReviewApproval.isApprovalDateFlag() && specialReview.getApprovalDate() == null) {
                    isValid = false;
                    reportError(errorPath + DOT + APPROVAL_DATE_FIELD, KeyConstants.ERROR_REQUIRED_FOR_VALID_SPECIALREVIEW, PROTOCOL_NUMBER_TITLE,
                            getValidSpecialReviewApprovalErrorString(validSpecialReviewApproval));
                }
                if (validSpecialReviewApproval.isExemptNumberFlag() && specialReview.getExemptionTypeCodes() != null 
                        && specialReview.getExemptionTypeCodes().isEmpty()) {
                    isValid = false;
                    reportError(errorPath + DOT + EXEMPTION_TYPE_CODE_FIELD, KeyConstants.ERROR_REQUIRED_FOR_VALID_SPECIALREVIEW, EXEMPTION_TYPE_CODE_TITLE, 
                            getValidSpecialReviewApprovalErrorString(validSpecialReviewApproval));
                }
                if (!validSpecialReviewApproval.isExemptNumberFlag() && specialReview.getExemptionTypeCodes() != null 
                        && !specialReview.getExemptionTypeCodes().isEmpty()) {
                    isValid = false;
                    reportError(errorPath + DOT + EXEMPTION_TYPE_CODE_FIELD, KeyConstants.ERROR_EXEMPT_NUMBER_SELECTED, 
                            getValidSpecialReviewApprovalErrorString(validSpecialReviewApproval));
                }
            }
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
        stringBuilder.append(validSpecialReviewApproval.getSpecialReview().getDescription());
        stringBuilder.append("/");
        stringBuilder.append(validSpecialReviewApproval.getSpecialReviewApprovalType().getDescription());
        return stringBuilder.toString();
    }
    
}