/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.common.framework.compliance.core;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.compliance.exemption.SpecialReviewExemption;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolFinderDao;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolFinderDao;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.util.CollectionUtils;

import java.sql.Date;
import java.util.*;

/**
 * This class validates all rules associated with SpecialReview.
 * @param <T> Special Review
 */
public class SpecialReviewRuleBase<T extends SpecialReview<? extends SpecialReviewExemption>> extends KcTransactionalDocumentRuleBase {
    
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
    private static final String ANIMAL_USAGE_LINK_TO_IACUC_ERROR_STRING = "Animal Usage/Link to IACUC";
    
    private ProtocolFinderDao protocolFinderDao;
    private IacucProtocolFinderDao iacucProtocolFinderDao;
    
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
        boolean validateProtocol = addSpecialReviewEvent.getIsIrbProtocolLinkingEnabled();
        
        getDictionaryValidationService().validateBusinessObject(specialReview);
        rulePassed &= GlobalVariables.getMessageMap().hasNoErrors();
        if (validateProtocol && SpecialReviewType.HUMAN_SUBJECTS.equals(specialReview.getSpecialReviewTypeCode())) {
            rulePassed &= validateProtocolNumber(specialReview, specialReviews, HUMAN_SUBJECTS_LINK_TO_IRB_ERROR_STRING);
        }
        else   if (validateProtocol && SpecialReviewType.ANIMAL_USAGE.equals(specialReview.getSpecialReviewTypeCode()) ) {
                rulePassed &= validateProtocolNumber(specialReview, specialReviews, ANIMAL_USAGE_LINK_TO_IACUC_ERROR_STRING);               
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
        boolean validateIrbProtocol = saveSpecialReviewEvent.getValidateIrbProtocol();
        boolean validateIacucProtocol = saveSpecialReviewEvent.getValidateIacucProtocol();
        
        int i = 0;
        for (T specialReview : specialReviews) {
            String errorPath = saveSpecialReviewEvent.getArrayErrorPathPrefix() + Constants.LEFT_SQUARE_BRACKET + i++ + Constants.RIGHT_SQUARE_BRACKET;
            
            GlobalVariables.getMessageMap().addToErrorPath(errorPath);
            if (validateIrbProtocol && SpecialReviewType.HUMAN_SUBJECTS.equals(specialReview.getSpecialReviewTypeCode())) {
                rulePassed &= validateProtocolNumber(specialReview, specialReviews, HUMAN_SUBJECTS_LINK_TO_IRB_ERROR_STRING);
            } 
            else if (validateIacucProtocol && SpecialReviewType.ANIMAL_USAGE.equals(specialReview.getSpecialReviewTypeCode())) {
                rulePassed &= validateProtocolNumber(specialReview, specialReviews, ANIMAL_USAGE_LINK_TO_IACUC_ERROR_STRING);
            } 
            else {
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
            if (protocol != null && !protocol.getProtocolDocument().getPessimisticLocks().isEmpty()) {
                isValid = false;
                reportError(PROTOCOL_NUMBER_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_PROTOCOL_LOCKED, protocolNumber);
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
        
        if (!StringUtils.equals(SpecialReviewApprovalType.NOT_YET_APPLIED, specialReview.getApprovalTypeCode())) {
            if (StringUtils.isBlank(specialReview.getProtocolNumber())) {
                isValid = false;
                reportError(PROTOCOL_NUMBER_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_REQUIRED_FOR_VALID, PROTOCOL_NUMBER_TITLE, errorString);
            } else {
                if ( SpecialReviewType.HUMAN_SUBJECTS.equals(specialReview.getSpecialReviewTypeCode()))
                {
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
                else if ( SpecialReviewType.ANIMAL_USAGE.equals(specialReview.getSpecialReviewTypeCode()))
                {
                    IacucProtocol iacucProtocol = (IacucProtocol)getIacucProtocolFinderDao().findCurrentProtocolByNumber(specialReview.getProtocolNumber());
                    if (iacucProtocol == null) {
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

        boolean kradScreen = !StringUtils.equals(EXEMPTION_TYPE_CODE_FIELD,getExemptionTypeCodeField());
        boolean noExceptions = CollectionUtils.isEmpty(kradScreen?specialReview.getSpecialReviewExemptions():specialReview.getExemptionTypeCodes());
        if (approval.isExemptNumberFlag()) {
            if (noExceptions) {
                isValid = false;
                reportError(getExemptionTypeCodeField(), KeyConstants.ERROR_SPECIAL_REVIEW_REQUIRED_FOR_VALID, EXEMPTION_TYPE_CODE_TITLE, errorString);
            }
        } else {
            if (!noExceptions) {
                isValid = false;
                reportError(getExemptionTypeCodeField(), KeyConstants.ERROR_SPECIAL_REVIEW_CANNOT_SELECT_EXEMPTION_FOR_VALID, errorString);
            }
        }

        return isValid;
    }

    private String getExemptionTypeCodeField() {
        List<String> errorPath = getGlobalVariableService().getMessageMap().getErrorPath();
        if (errorPath.isEmpty() || StringUtils.equals(GlobalVariables.getMessageMap().getErrorPath().get(0),"specialReviewHelper.newSpecialReview")) {
            return EXEMPTION_TYPE_CODE_FIELD;
        }
        return "specialReviewExemptions";
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
            protocolFinderDao = KcServiceLocator.getService(ProtocolFinderDao.class);
        }
        return protocolFinderDao;
    }

    public void setProtocolFinderDao(ProtocolFinderDao protocolFinderDao) {
        this.protocolFinderDao = protocolFinderDao;
    }

    public IacucProtocolFinderDao getIacucProtocolFinderDao() {
        if (iacucProtocolFinderDao == null) {
            iacucProtocolFinderDao = KcServiceLocator.getService(IacucProtocolFinderDao.class);
        }
        return iacucProtocolFinderDao;
    }

    public void setIacucProtocolFinderDao(IacucProtocolFinderDao iacucProtocolFinderDao) {
        this.iacucProtocolFinderDao = iacucProtocolFinderDao;
    }

   public GlobalVariableService getGlobalVariableService() {
        return KcServiceLocator.getService(GlobalVariableService.class);
   }
}
