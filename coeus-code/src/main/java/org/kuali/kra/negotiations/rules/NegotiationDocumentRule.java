/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.negotiations.bo.*;
import org.kuali.kra.negotiations.document.NegotiationDocument;
import org.kuali.kra.negotiations.service.NegotiationService;
import org.kuali.kra.rule.event.SaveCustomDataEvent;
import org.kuali.kra.service.SponsorService;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * 
 * This class handles the business rules for the Negotiation Object.
 */
public class NegotiationDocumentRule extends KcTransactionalDocumentRuleBase {
    
    private static final String NEGOTIATION_ERROR_PATH = "document.negotiationList[0]";
    private static final String END_DATE_PROPERTY = "negotiationEndDate";
    private static final String NEGOTIATOR_USERNAME_PROPERTY = "negotiatorUserName";
    private static final String ASSOCIATED_DOCMENT_ID = "associatedDocumentId";
    private static final String ACTIVITIES_PREFIX = "activities[";
    private static final String ATTACHMENTS_PREFIX = "attachments[";
    
    private NegotiationService negotiationService;
    private DataDictionaryService dataDictionaryService;
    private SponsorService sponsorService;
    
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
        
        NegotiationDocument negotiationDocument = (NegotiationDocument) document;
        Negotiation negotiation = negotiationDocument.getNegotiation();
        
        boolean result = true;
        
        result &= processRules(new SaveCustomDataEvent(negotiationDocument, true));

        GlobalVariables.getMessageMap().addToErrorPath(NEGOTIATION_ERROR_PATH);
        
        result &= validateEndDate(negotiation);
        result &= validateNegotiator(negotiation);
        result &= validateNegotiationAssociations(negotiation);
        result &= validateNegotiationUnassociatedDetails(negotiation);
        result &= validateNegotiationActivities(negotiation); 
        
        GlobalVariables.getMessageMap().removeFromErrorPath(NEGOTIATION_ERROR_PATH);
        
        return result;
    }
    
    /**
     * @see org.kuali.coeus.sys.framework.rule.KcBusinessRule#processRules(org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension)
     */
    public boolean processRules(KcDocumentEventBaseExtension event) {
        boolean retVal = false;
        retVal = event.getRule().processRules(event);
        return retVal;
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
        boolean valid = true;
        if (negotiation.getNegotiationAssociationType() != null 
                && !StringUtils.equals(negotiation.getNegotiationAssociationType().getCode(), NegotiationAssociationType.NONE_ASSOCIATION)
                && StringUtils.isEmpty(negotiation.getAssociatedDocumentId())) {
            //negotiation.setAssociatedDocumentWarning(expandErrorString(KeyConstants.NEGOTIATION_WARNING_ASSOCIATEDID_NOT_SET, 
               //     new String[]{negotiation.getNegotiationAssociationType().getDescription()}));
            valid = false;
            //can't do this because the document is final, when final and without error the messagemap is cleared during save
            //so must workaround to display this warning.
            getErrorReporter().reportError(ASSOCIATED_DOCMENT_ID, KeyConstants.NEGOTIATION_WARNING_ASSOCIATEDID_NOT_SET, 
                    negotiation.getNegotiationAssociationType().getDescription());
        }
        return valid;
    }

    
    public boolean validateNegotiationUnassociatedDetails(Negotiation negotiation) {
        boolean valid = true;
        GlobalVariables.getMessageMap().addToErrorPath("unAssociatedDetail");
        if (negotiation.getNegotiationAssociationType() != null 
                && StringUtils.equals(negotiation.getNegotiationAssociationType().getCode(), NegotiationAssociationType.NONE_ASSOCIATION)
                && negotiation.getUnAssociatedDetail() != null) {
            NegotiationUnassociatedDetail detail = negotiation.getUnAssociatedDetail();
            valid &= getDictionaryValidationService().isBusinessObjectValid(detail);
            
            detail.refreshReferenceObject("sponsor");
            if (detail.getSponsorCode() != null && !this.getSponsorService().validateSponsor(detail.getSponsor())) {
                valid = false;
                getErrorReporter().reportError("sponsorCode", KeyConstants.ERROR_MISSING, getDataDictionaryService().getAttributeErrorLabel(
                        NegotiationUnassociatedDetail.class, "sponsorCode"));
            }
            
            detail.refreshReferenceObject("leadUnit");
            if (detail.getLeadUnitNumber() != null && detail.getLeadUnit() == null) {
                valid = false;
                getErrorReporter().reportError("leadUnitNumber", KeyConstants.ERROR_MISSING, getDataDictionaryService().getAttributeErrorLabel(
                        NegotiationUnassociatedDetail.class, "leadUnitNumber"));
            }
            
            detail.refreshReferenceObject("primeSponsor");
            if (detail.getPrimeSponsorCode() != null && !this.getSponsorService().validateSponsor(detail.getPrimeSponsor())) {
                valid = false;
                getErrorReporter().reportError("primeSponsorCode", KeyConstants.ERROR_MISSING, getDataDictionaryService().getAttributeErrorLabel(
                        NegotiationUnassociatedDetail.class, "primeSponsorCode"));
            }
            
            detail.refreshReferenceObject("subAwardOrganization");
            if (detail.getSubAwardOrganizationId() != null && detail.getSubAwardOrganization() == null) {
                valid = false;
                getErrorReporter().reportError("subAwardOrganizationId", KeyConstants.ERROR_MISSING, getDataDictionaryService().getAttributeErrorLabel(
                        NegotiationUnassociatedDetail.class, "subAwardOrganizationId"));
            }
            if (detail.getContactAdminUserName() != null && detail.getContactAdmin() == null) {
                valid = false;
                getErrorReporter().reportError("contactAdminUserName", KeyConstants.ERROR_MISSING, getDataDictionaryService().getAttributeErrorLabel(
                        NegotiationUnassociatedDetail.class, "contactAdminPersonId"));
            }
            if (detail.getPiEmployeeUserName() != null && detail.getPIEmployee() == null) {
                valid = false;
                getErrorReporter().reportError("piEmployeeUserName", KeyConstants.ERROR_MISSING, getDataDictionaryService().getAttributeErrorLabel(
                        NegotiationUnassociatedDetail.class, "piPersonId"));                
            }
            if (detail.getPIEmployee() != null && detail.getPINonEmployee() != null) {
                valid = false;
                getErrorReporter().reportError("piEmployeeUserName", KeyConstants.NEGOTIATION_MULTIPLE_PI, getDataDictionaryService().getAttributeErrorLabel(
                        NegotiationUnassociatedDetail.class, "piPersonId"));                                
            }
        }
        GlobalVariables.getMessageMap().removeFromErrorPath("unAssociatedDetail");
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
            negotiationService = KcServiceLocator.getService(NegotiationService.class);
        }
        return negotiationService;
    }

    public void setNegotiationService(NegotiationService negotiationService) {
        this.negotiationService = negotiationService;
    }

    public DataDictionaryService getDataDictionaryService() {
        if (dataDictionaryService == null) {
            dataDictionaryService = KcServiceLocator.getService(DataDictionaryService.class);
        }
        return dataDictionaryService;
    }

    public void setDataDictionaryService(DataDictionaryService dataDictionaryService) {
        this.dataDictionaryService = dataDictionaryService;
    }

    public SponsorService getSponsorService() {
        if (sponsorService == null) {
            sponsorService = KcServiceLocator.getService(SponsorService.class);
        }
        return sponsorService;
    }

    public void setSponsorService(SponsorService sponsorService) {
        this.sponsorService = sponsorService;
    }

}
