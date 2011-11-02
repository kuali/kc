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
package org.kuali.kra.negotiations.rules;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.customdata.AwardCustomDataRuleImpl;
import org.kuali.kra.award.customdata.AwardSaveCustomDataRuleEvent;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.negotiations.bo.NegotiationActivity;
import org.kuali.kra.negotiations.bo.NegotiationActivityAttachment;
import org.kuali.kra.negotiations.bo.NegotiationAssociationType;
import org.kuali.kra.negotiations.customdata.NegotiationCustomDataRuleImpl;
import org.kuali.kra.negotiations.customdata.NegotiationSaveCustomDataRuleEvent;
import org.kuali.kra.negotiations.document.NegotiationDocument;
import org.kuali.kra.negotiations.service.NegotiationService;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * 
 * This class handles the business rules for the Negotiation Object.
 */
public class NegotiationDocumentRule extends ResearchDocumentRuleBase {
    
    private static final String NEGOTIATION_ERROR_PATH = "document.negotiationList[0]";
    private static final String END_DATE_PROPERTY = "negotiationEndDate";
    private static final String NEGOTIATOR_USERNAME_PROPERTY = "negotiatorUserName";
    private static final String ASSOCIATED_DOCMENT_ID = "associatedDocumentId";
    private static final String ACTIVITIES_PREFIX = "activities[";
    private static final String ATTACHMENTS_PREFIX = "attachments[";
    
    private NegotiationService negotiationService;
    
    /**
     * 
     * Constructs a NegotiationDocumentRule.java.
     */
    public NegotiationDocumentRule() {
        super();
    }
    
    @Override
    protected boolean processCustomSaveDocumentBusinessRules(Document document) {
        if (!(document instanceof NegotiationDocument)) {
            return false;
        }
        
        GlobalVariables.getMessageMap().addToErrorPath(DOCUMENT_ERROR_PATH);
        getDictionaryValidationService().validateDocumentAndUpdatableReferencesRecursively(
            document, getMaxDictionaryValidationDepth(), VALIDATION_REQUIRED, CHOMP_LAST_LETTER_S_FROM_COLLECTION_NAME);
        GlobalVariables.getMessageMap().removeFromErrorPath(DOCUMENT_ERROR_PATH);
        
        NegotiationDocument negotiationDocument = (NegotiationDocument) document;
        Negotiation negotiation = negotiationDocument.getNegotiation();
        
        GlobalVariables.getMessageMap().addToErrorPath(NEGOTIATION_ERROR_PATH);
        
        boolean result = true;
        result &= validateEndDate(negotiation);
        result &= validateNegotiator(negotiation);
        result &= validateNegotiationAssociations(negotiation);
        result &= validateNegotiationActivities(negotiation); 
        
        String errorPath = "negotiationCustomData";
        NegotiationSaveCustomDataRuleEvent event = new NegotiationSaveCustomDataRuleEvent(errorPath, 
                                                               negotiationDocument);
        result &= new NegotiationCustomDataRuleImpl().processSaveNegotiationCustomDataBusinessRules(event);
           
        GlobalVariables.getMessageMap().removeFromErrorPath(NEGOTIATION_ERROR_PATH);
        
        return result;
    }
    
    public boolean validateEndDate(Negotiation negotiation) {
        boolean result = true;
        if (negotiation.getNegotiationStatus() != null) {
            if (negotiation.getNegotiationEndDate() != null 
                    && getNegotiationService().getInProgressStatusCodes().contains(negotiation.getNegotiationStatus().getCode())) {
                result = false;
                getErrorReporter().reportError(END_DATE_PROPERTY, KeyConstants.NEGOTIATION_ERROR_INPROGRESS_END_DATE);
            }
            
            if (negotiation.getNegotiationEndDate() == null
                    && getNegotiationService().getCompletedStatusCodes().contains(negotiation.getNegotiationStatus().getCode())) {
                result = false;
                getErrorReporter().reportError(END_DATE_PROPERTY, KeyConstants.NEGOTIATION_ERROR_COMPLETED_END_DATE);            
            }
        }
        
        if (negotiation.getNegotiationEndDate() != null
                && negotiation.getNegotiationEndDate().compareTo(negotiation.getNegotiationStartDate()) < 0) {
            result = false;
            getErrorReporter().reportError(END_DATE_PROPERTY, KeyConstants.NEGOTIATION_ERROR_END_DATE_GREATER_THAN_START);
        }
        return result;
    }
    
    public boolean validateNegotiator(Negotiation negotiation) {
        boolean result = true;
        if (StringUtils.isBlank(negotiation.getNegotiatorPersonId())
                || negotiation.getNegotiator() == null) {
            result = false;
            getErrorReporter().reportError(NEGOTIATOR_USERNAME_PROPERTY, KeyConstants.NEGOTIATION_ERROR_NEGOTIATOR);
        }
        return result;
    }
    
    /**
     * 
     * This method validates the Negotiation Association.
     * @param negotiation
     * @return
     */
    public boolean validateNegotiationAssociations(Negotiation negotiation) {
        System.err.println("validateNegotiationAssociations");
        boolean valid = true;
        if (negotiation.getNegotiationAssociationType() != null 
                && !StringUtils.equals(negotiation.getNegotiationAssociationType().getCode(), NegotiationAssociationType.NONE_ASSOCIATION)
                && StringUtils.isEmpty(negotiation.getAssociatedDocumentId())) {
            getErrorReporter().reportWarning(ASSOCIATED_DOCMENT_ID, KeyConstants.NEGOTIATION_WARNING_ASSOCIATEDID_NOT_SET, 
                    negotiation.getNegotiationAssociationType().getDescription());
        }
        return valid;
    }
    
    /**
     * 
     * Validate existing negotiation activities.
     * @param negotiation
     * @return
     */
    public boolean validateNegotiationActivities(Negotiation negotiation) {
        boolean result = true;
        int index = 0;
        NegotiationActivityRuleImpl rule = new NegotiationActivityRuleImpl();
        for (NegotiationActivity activity : negotiation.getActivities()) {
            GlobalVariables.getMessageMap().addToErrorPath(ACTIVITIES_PREFIX + index + "]");
            result &= rule.validateNegotiationActivity(activity, negotiation);
            result &= validateActivityAttachments(negotiation, activity);
            GlobalVariables.getMessageMap().removeFromErrorPath(ACTIVITIES_PREFIX + index + "]");
            index++;
        }
        return result;
    }
    
    /**
     * 
     * Validate the activities attachments.
     * @param negotiation
     * @param activity
     * @return
     */
    public boolean validateActivityAttachments(Negotiation negotiation, NegotiationActivity activity) {
        boolean result = true;
        int index = 0;
        NegotiationActivityAttachmentRuleImpl rule = new NegotiationActivityAttachmentRuleImpl();
        for (NegotiationActivityAttachment attachment : activity.getAttachments()) {
            GlobalVariables.getMessageMap().addToErrorPath(ATTACHMENTS_PREFIX + index + "]");
            result &= rule.validateAttachmentRule(negotiation, activity, attachment);
            GlobalVariables.getMessageMap().removeFromErrorPath(ATTACHMENTS_PREFIX + index + "]");
            index++;
        }
        return result;
    }

    protected NegotiationService getNegotiationService() {
        if (negotiationService == null) {
            negotiationService = KraServiceLocator.getService(NegotiationService.class);
        }
        return negotiationService;
    }

    public void setNegotiationService(NegotiationService negotiationService) {
        this.negotiationService = negotiationService;
    }

}
