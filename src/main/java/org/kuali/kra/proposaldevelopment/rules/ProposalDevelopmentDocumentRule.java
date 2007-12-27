/*
 * Copyright 2007 The Kuali Foundation.
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.rules;

import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.bo.BusinessObject;
import org.kuali.core.document.Document;
import org.kuali.core.rule.DocumentAuditRule;
import org.kuali.core.service.DataDictionaryService;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.bo.ValidSpecialReviewApproval;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.ProposalAbstract;
import org.kuali.kra.proposaldevelopment.bo.ProposalCopyCriteria;
import org.kuali.kra.proposaldevelopment.bo.ProposalLocation;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalSpecialReview;
import org.kuali.kra.proposaldevelopment.bo.ProposalYnq;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.AbstractsRule;
import org.kuali.kra.proposaldevelopment.rule.AddInstituteAttachmentRule;
import org.kuali.kra.proposaldevelopment.rule.AddKeyPersonRule;
import org.kuali.kra.proposaldevelopment.rule.AddNarrativeRule;
import org.kuali.kra.proposaldevelopment.rule.AddPersonnelAttachmentRule;
import org.kuali.kra.proposaldevelopment.rule.AddProposalLocationRule;
import org.kuali.kra.proposaldevelopment.rule.AddProposalSpecialReviewRule;
import org.kuali.kra.proposaldevelopment.rule.CopyProposalRule;
import org.kuali.kra.proposaldevelopment.rule.ChangeKeyPersonRule;
import org.kuali.kra.proposaldevelopment.rule.SaveNarrativesRule;
import org.kuali.kra.proposaldevelopment.rule.event.AddInstituteAttachmentEvent;
import org.kuali.kra.proposaldevelopment.rule.event.AddNarrativeEvent;
import org.kuali.kra.proposaldevelopment.rule.event.AddPersonnelAttachmentEvent;
import org.kuali.kra.proposaldevelopment.rule.event.AddProposalLocationEvent;
import org.kuali.kra.proposaldevelopment.rule.event.AddProposalSpecialReviewEvent;
import org.kuali.kra.proposaldevelopment.rule.event.SaveNarrativesEvent;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.kra.service.DocumentValidationService;

/**
 * This class...
 *
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class ProposalDevelopmentDocumentRule extends ResearchDocumentRuleBase implements AddKeyPersonRule, AddNarrativeRule,SaveNarrativesRule, AddInstituteAttachmentRule, AddPersonnelAttachmentRule, AddProposalLocationRule,AddProposalSpecialReviewRule , DocumentAuditRule, AbstractsRule, CopyProposalRule, ChangeKeyPersonRule {

    @Override
    protected boolean processCustomRouteDocumentBusinessRules(Document document) {
        boolean retval = true;

        retval &= super.processCustomRouteDocumentBusinessRules(document);

        retval &= new ProposalDevelopmentKeyPersonsRule().processCustomRouteDocumentBusinessRules(document);

        return retval;
    }

    @Override
    protected boolean processCustomSaveDocumentBusinessRules(Document document) {
        if (!(document instanceof ProposalDevelopmentDocument)) {
            return false;
        }

        boolean valid = true;

        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument) document;

        GlobalVariables.getErrorMap().addToErrorPath("document");

        // changing this to '0' so it doesn't validate reference objects within a list
        // temporarily put validateDocumentRecursively, so it can check collections under document
        //KNSServiceLocator.getDictionaryValidationService().validateDocumentRecursively(proposalDevelopmentDocument,0);
        //KNSServiceLocator.getDictionaryValidationService().validateDocument(proposalDevelopmentDocument);
        // TODO : temporary hack tied to KRACOESS-287.  Remove this after rice resolves this issue
        // hack to get rid of message that contains '.documentBusinessObject.' in path
        // rice should look into this problem ?
        // The '.documentBusinessObject.' is filtered out in validateDocumentRecursively for now.
        getService(DocumentValidationService.class).validateDocumentRecursively(proposalDevelopmentDocument,10);
        //getDictionaryValidationService().validateDocumentAndUpdatableReferencesRecursively(document, getMaxDictionaryValidationDepth(), true);


        valid &= processProposalRequiredFieldsBusinessRule(proposalDevelopmentDocument);
        valid &= processOrganizationLocationBusinessRule(proposalDevelopmentDocument);
        valid &= processSpecialReviewBusinessRule(proposalDevelopmentDocument);
        valid &= processProposalYNQBusinessRule(proposalDevelopmentDocument);

        GlobalVariables.getErrorMap().removeFromErrorPath("document");

        return valid;
    }

    /**
     * This method validates 'Proposal Special review'. It checks
     * validSpecialReviewApproval table, and if there is a match, then checks
     * protocalnumberflag, applicationdateflag, and approvaldataflag.
     *
     * @param proposalDevelopmentDocument : The proposalDevelopmentDocument that is being validated
     * @return valid Does the validation pass
     */
    private boolean processSpecialReviewBusinessRule(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        boolean valid = true;

        ErrorMap errorMap = GlobalVariables.getErrorMap();

        int i = 0;
        
        for (ProposalSpecialReview propSpecialReview : proposalDevelopmentDocument.getPropSpecialReviews()) {
            errorMap.addToErrorPath("propSpecialReview[" + i + "]");
            propSpecialReview.refreshReferenceObject("validSpecialReviewApproval");
            if (StringUtils.isNotBlank(propSpecialReview.getApprovalTypeCode()) && StringUtils.isNotBlank(propSpecialReview.getSpecialReviewCode())) {
                ValidSpecialReviewApproval validSpRevApproval = propSpecialReview.getValidSpecialReviewApproval();
                if (validSpRevApproval != null) {
                    if (validSpRevApproval.isProtocolNumberFlag() && StringUtils.isBlank(propSpecialReview.getProtocolNumber())) {
                        valid = false;
                        errorMap.putError("protocolNumber", KeyConstants.ERROR_REQUIRED_FOR_VALID_SPECIALREVIEW, "Protocol Number",
                                validSpRevApproval.getSpecialReview().getDescription() + "/"
                                        + validSpRevApproval.getSpecialReviewApprovalType().getDescription());
                    }
                    if (validSpRevApproval.isApplicationDateFlag() && propSpecialReview.getApplicationDate() == null) {
                        valid = false;
                        errorMap.putError("applicationDate", KeyConstants.ERROR_REQUIRED_FOR_VALID_SPECIALREVIEW,
                                "Protocol Number", validSpRevApproval.getSpecialReview().getDescription() + "/"
                                        + validSpRevApproval.getSpecialReviewApprovalType().getDescription());
                    }
                    if (validSpRevApproval.isApprovalDateFlag() && propSpecialReview.getApprovalDate() == null) {
                        valid = false;
                        errorMap.putError("approvalDate", KeyConstants.ERROR_REQUIRED_FOR_VALID_SPECIALREVIEW, "Protocol Number",
                                validSpRevApproval.getSpecialReview().getDescription() + "/"
                                        + validSpRevApproval.getSpecialReviewApprovalType().getDescription());
                    }

                }

            }
            if (propSpecialReview.getApplicationDate() !=null && propSpecialReview.getApprovalDate() != null && propSpecialReview.getApprovalDate().before(propSpecialReview.getApplicationDate())) {
                errorMap.putError("approvalDate", KeyConstants.ERROR_APPROVAL_DATE_BEFORE_APPLICATION_DATE_SPECIALREVIEW,
                        "Approval Date","Application Date"); 
            }

            errorMap.removeFromErrorPath("propSpecialReview[" + i++ + "]");
        }
        return valid;
    }


    /**
    *
    * Validate proposal questions rule. validate explanation required and date required fields based on 
    * question configuration.
    * @param proposalDevelopmentDocument
    * @return
    */
    private boolean processProposalYNQBusinessRule(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        boolean valid = true;

        //checkErrors();
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        int i = 0;
        for (ProposalYnq proposalYnq : proposalDevelopmentDocument.getProposalYnqs()) {
            
            String groupName = proposalYnq.getYnq().getGroupName();
            String errorPath = "proposalYnq[" + groupName + "][" + i + "]";
            errorMap.addToErrorPath(errorPath);
            /* look for date requried */
            if (StringUtils.isNotBlank(proposalYnq.getAnswer()) && 
                    proposalYnq.getAnswer().equalsIgnoreCase(proposalYnq.getYnq().getDateRequiredFor()) && 
                    proposalYnq.getReviewDate() == null
                   ) {
                    valid = false;
                    errorMap.putError("reviewDate", KeyConstants.ERROR_REQUIRED_FOR_REVIEW_DATE);
            }

            /* look for explanation requried */
            if (StringUtils.isNotBlank(proposalYnq.getAnswer()) && 
                proposalYnq.getAnswer().equalsIgnoreCase(proposalYnq.getYnq().getExplanationRequiredFor()) && 
                StringUtils.isBlank(proposalYnq.getExplanation())
               ) {
                valid = false;
                errorMap.putError("explanation", KeyConstants.ERROR_REQUIRED_FOR_EXPLANATION);
            }
            errorMap.removeFromErrorPath(errorPath);
            i++;
        }
        return valid;

    }

    
    
    /**
     * This method validates Required Fields related fields on
     * the Proposal Development Document.
     * @param proposalDevelopmentDocument document to validate
     * @return boolean whether the validation passed or not
     */
    private boolean processProposalRequiredFieldsBusinessRule(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        boolean valid = true;

        ErrorMap errorMap = GlobalVariables.getErrorMap();

        // "Sponsor Proposal Id" must be entered if the proposal type is not new (i.e. resubmission)
        // or if the proposal type is new and the grants.gov submission type is "changed/corrected".
        String proposalTypeCodeNew = getKualiConfigurationService().getParameter(
                Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT, KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_NEW).getParameterValue();
        DataDictionaryService dataDictionaryService = KraServiceLocator.getService(DataDictionaryService.class);
        if (StringUtils.isNotEmpty(proposalDevelopmentDocument.getProposalTypeCode()) &&
                !proposalDevelopmentDocument.getProposalTypeCode().equals(proposalTypeCodeNew) &&
                StringUtils.isEmpty(proposalDevelopmentDocument.getContinuedFrom())) {
            valid = false;
            errorMap.putError("continuedFrom", KeyConstants.ERROR_REQUIRED_FOR_PROPOSALTYPE_NOTNEW, dataDictionaryService.getAttributeErrorLabel(ProposalDevelopmentDocument.class, "continuedFrom"));
        }

        proposalDevelopmentDocument.refreshReferenceObject("sponsor");
        if (proposalDevelopmentDocument.getSponsorCode() != null && proposalDevelopmentDocument.getSponsor() == null) {
            valid = false;
            errorMap.putError("sponsorCode", KeyConstants.ERROR_MISSING, dataDictionaryService.getAttributeErrorLabel(ProposalDevelopmentDocument.class, "sponsorCode"));
        }
        
        //if either is missing, it should be caught on the DD validation.
        if (proposalDevelopmentDocument.getRequestedStartDateInitial() != null && proposalDevelopmentDocument.getRequestedEndDateInitial() != null) {
            if (proposalDevelopmentDocument.getRequestedStartDateInitial().after(proposalDevelopmentDocument.getRequestedEndDateInitial())) {
                valid = false;
                errorMap.putError("requestedStartDateInitial", KeyConstants.ERROR_START_DATE_AFTER_END_DATE, 
                        new String[] {dataDictionaryService.getAttributeErrorLabel(ProposalDevelopmentDocument.class, "requestedStartDateInitial"),
                        dataDictionaryService.getAttributeErrorLabel(ProposalDevelopmentDocument.class, "requestedEndDateInitial")});
            }
        }
        
        return valid;
    }

    /**
     *
     * Validate organization/location rule. specifically, at least one location is required.
     * @param proposalDevelopmentDocument
     * @return
     */
    private boolean processOrganizationLocationBusinessRule(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        boolean valid = true;

        ErrorMap errorMap = GlobalVariables.getErrorMap();

        if (proposalDevelopmentDocument.getOrganizationId()!=null && (proposalDevelopmentDocument.getProposalLocations().size()==0 ||
                (proposalDevelopmentDocument.getProposalLocations().size()==1 && ((ProposalLocation)(proposalDevelopmentDocument.getProposalLocations().get(0))).getLocationSequenceNumber()==null))) {
            errorMap.addToErrorPath("newPropLocation");
            errorMap.putError("location", KeyConstants.ERROR_REQUIRED_FOR_PROPLOCATION);
            errorMap.removeFromErrorPath("newPropLocation");
            valid = false;
        }
        return valid;

    }


    public boolean processAddKeyPersonBusinessRules(ProposalDevelopmentDocument document, ProposalPerson person) {
        return new ProposalDevelopmentKeyPersonsRule().processAddKeyPersonBusinessRules(document, person);
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.rule.AddNarrativeRule#processAddNarrativeBusinessRules(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument,org.kuali.kra.proposaldevelopment.bo.Narrative)
     */
    public boolean processAddNarrativeBusinessRules(AddNarrativeEvent addNarrativeEvent) {
        return new ProposalDevelopmentNarrativeRule().processAddNarrativeBusinessRules(addNarrativeEvent);    }

    /**
     * @see org.kuali.core.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.core.document.Document)
     */
    public boolean processRunAuditBusinessRules(Document document) {
        return new ProposalDevelopmentSponsorProgramInformationAuditRule().processRunAuditBusinessRules(document);
	}

    /**
     * @see org.kuali.kra.proposaldevelopment.rule.AbstractsRule#processAddAbstractBusinessRules(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument, org.kuali.kra.proposaldevelopment.bo.ProposalAbstract)
     */
    public boolean processAddAbstractBusinessRules(ProposalDevelopmentDocument document, ProposalAbstract proposalAbstract) {
        return new ProposalDevelopmentAbstractsRule().processAddAbstractBusinessRules(document, proposalAbstract);
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.rule.SaveNarrativesRule#processSaveNarrativesBusinessRules(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
     */
    public boolean processSaveNarrativesBusinessRules(SaveNarrativesEvent saveNarrativesEvent) {
        return new ProposalDevelopmentNarrativeRule().processSaveNarrativesBusinessRules(saveNarrativesEvent);
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.rule.CopyProposalRule#processCopyProposalBusinessRules(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument, org.kuali.kra.proposaldevelopment.bo.ProposalCopyCriteria)
     */
    public boolean processCopyProposalBusinessRules(ProposalDevelopmentDocument document, ProposalCopyCriteria criteria) {
        return new ProposalDevelopmentCopyRule().processCopyProposalBusinessRules(document, criteria);
    }

    /**
     * 
     * @see org.kuali.kra.proposaldevelopment.rule.AddInstituteAttachmentRule#processAddInstituteAttachmentBusinessRules(org.kuali.kra.proposaldevelopment.rule.event.AddInstituteAttachmentEvent)
     */
    public boolean processAddInstituteAttachmentBusinessRules(AddInstituteAttachmentEvent addInstituteAttachmentEvent) {
        return new ProposalDevelopmentInstituteAttachmentRule().processAddInstituteAttachmentBusinessRules(addInstituteAttachmentEvent);    
    }

    /**
     * 
     * @see org.kuali.kra.proposaldevelopment.rule.AddPersonnelAttachmentsRule#processAddPersonnelAttachmentsBusinessRules(org.kuali.kra.proposaldevelopment.rule.event.AddPersonnelAttachmentsEvent)
     */
    public boolean processAddPersonnelAttachmentBusinessRules(AddPersonnelAttachmentEvent addPersonnelAttachmentEvent) {
        return new ProposalDevelopmentPersonnelAttachmentRule().processAddPersonnelAttachmentBusinessRules(addPersonnelAttachmentEvent);    
    }

    /**
     * Delegating method for the <code>{@link ChangeKeyPersonRule}</code> which is triggered by the <code>{@link ChangeKeyPersonEvent}</code>
     * 
     * @see org.kuali.kra.proposaldevelopment.rule.ChangeKeyPersonRule#processChangeKeyPersonBusinessRules(org.kuali.kra.proposaldevelopment.bo.ProposalPerson, org.kuali.core.bo.BusinessObject)
     */
    public boolean processChangeKeyPersonBusinessRules(ProposalPerson proposalPerson, BusinessObject source) {
        return new ProposalDevelopmentKeyPersonsRule().processChangeKeyPersonBusinessRules(proposalPerson, source);
    }

    /**
     * 
     * @see org.kuali.kra.proposaldevelopment.rule.AddProposalLocationRule#processAddProposalLocationBusinessRules(org.kuali.kra.proposaldevelopment.rule.event.AddProposalLocationEvent)
     */
    public boolean processAddProposalLocationBusinessRules(AddProposalLocationEvent addProposalLocationEvent) {
        return new ProposalDevelopmentProposalLocationRule().processAddProposalLocationBusinessRules(addProposalLocationEvent);    
    }

    /**
     * 
     * @see org.kuali.kra.proposaldevelopment.rule.AddProposalSpecialReviewRule#processAddProposalSpecialReviewBusinessRules(org.kuali.kra.proposaldevelopment.rule.event.AddProposalSpecialReviewEvent)
     */
    public boolean processAddProposalSpecialReviewBusinessRules(AddProposalSpecialReviewEvent addProposalSpecialReviewEvent) {
        return new ProposalDevelopmentProposalSpecialReviewRule().processAddProposalSpecialReviewBusinessRules(addProposalSpecialReviewEvent);    
    }

}
