/*
 * Copyright 2006-2009 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.rules;

import static org.kuali.kra.logging.BufferedLogger.info;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.ValidSpecialReviewApproval;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.NarrativeUserRights;
import org.kuali.kra.proposaldevelopment.bo.ProposalAbstract;
import org.kuali.kra.proposaldevelopment.bo.ProposalCopyCriteria;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonYnq;
import org.kuali.kra.proposaldevelopment.bo.ProposalSpecialReview;
import org.kuali.kra.proposaldevelopment.bo.ProposalUser;
import org.kuali.kra.proposaldevelopment.bo.ProposalUserEditRoles;
import org.kuali.kra.proposaldevelopment.bo.ProposalYnq;
import org.kuali.kra.proposaldevelopment.bo.YnqGroupName;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.AbstractsRule;
import org.kuali.kra.proposaldevelopment.rule.AddInstituteAttachmentRule;
import org.kuali.kra.proposaldevelopment.rule.AddKeyPersonRule;
import org.kuali.kra.proposaldevelopment.rule.AddNarrativeRule;
import org.kuali.kra.proposaldevelopment.rule.AddPersonnelAttachmentRule;
import org.kuali.kra.proposaldevelopment.rule.AddProposalLocationRule;
import org.kuali.kra.proposaldevelopment.rule.AddProposalSpecialReviewRule;
import org.kuali.kra.proposaldevelopment.rule.CalculateCreditSplitRule;
import org.kuali.kra.proposaldevelopment.rule.ChangeKeyPersonRule;
import org.kuali.kra.proposaldevelopment.rule.CopyProposalRule;
import org.kuali.kra.proposaldevelopment.rule.NewNarrativeUserRightsRule;
import org.kuali.kra.proposaldevelopment.rule.PermissionsRule;
import org.kuali.kra.proposaldevelopment.rule.ProposalDataOverrideRule;
import org.kuali.kra.proposaldevelopment.rule.SaveKeyPersonRule;
import org.kuali.kra.proposaldevelopment.rule.SaveNarrativesRule;
import org.kuali.kra.proposaldevelopment.rule.event.AddInstituteAttachmentEvent;
import org.kuali.kra.proposaldevelopment.rule.event.AddNarrativeEvent;
import org.kuali.kra.proposaldevelopment.rule.event.AddPersonnelAttachmentEvent;
import org.kuali.kra.proposaldevelopment.rule.event.AddProposalLocationEvent;
import org.kuali.kra.proposaldevelopment.rule.event.AddProposalSpecialReviewEvent;
import org.kuali.kra.proposaldevelopment.rule.event.ChangeKeyPersonEvent;
import org.kuali.kra.proposaldevelopment.rule.event.ProposalDataOverrideEvent;
import org.kuali.kra.proposaldevelopment.rule.event.SaveNarrativesEvent;
import org.kuali.kra.proposaldevelopment.rule.event.SavePersonnelAttachmentEvent;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService;
import org.kuali.kra.proposaldevelopment.web.bean.ProposalUserRoles;
import org.kuali.kra.rule.CustomAttributeRule;
import org.kuali.kra.rule.event.SaveCustomAttributeEvent;
import org.kuali.kra.rules.KraCustomAttributeRule;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.RiceKeyConstants;

/**
 * Main Business Rule class for <code>{@link ProposalDevelopmentDocument}</code>. Responsible for delegating rules to independent rule classes.
 *
 * @see org.kuali.proposaldevelopment.rules.KeyPersonnelAuditRule
 * @see org.kuali.proposaldevelopment.rules.PersonEditableFieldRule
 * @see org.kuali.proposaldevelopment.rules.ProposalDevelopmentKeyPersonsRule
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class ProposalDevelopmentDocumentRule extends ResearchDocumentRuleBase implements AddKeyPersonRule, AddNarrativeRule,SaveNarrativesRule, AddInstituteAttachmentRule, AddPersonnelAttachmentRule, AddProposalLocationRule,AddProposalSpecialReviewRule , AbstractsRule, CopyProposalRule, ChangeKeyPersonRule, PermissionsRule, CustomAttributeRule, NewNarrativeUserRightsRule, SaveKeyPersonRule,CalculateCreditSplitRule, ProposalDataOverrideRule  {
    
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(Document document) {
        boolean retval = true;
        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument) document;

        retval &= super.processCustomRouteDocumentBusinessRules(document);
        
        retval &= processProposalPersonYNQBusinessRule(proposalDevelopmentDocument);

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
        
        // KRACOEUS-641: Changed CHOMP_LAST_LETTER_S_FROM_COLLECTION_NAME to false to prevent duplicate error messages
        final boolean VALIDATION_REQUIRED = true;
        final boolean CHOMP_LAST_LETTER_S_FROM_COLLECTION_NAME = false;
        getDictionaryValidationService().validateDocumentAndUpdatableReferencesRecursively(document, getMaxDictionaryValidationDepth(), VALIDATION_REQUIRED, CHOMP_LAST_LETTER_S_FROM_COLLECTION_NAME);

        valid &= processProposalRequiredFieldsBusinessRule(proposalDevelopmentDocument);
        valid &= processOrganizationLocationBusinessRule(proposalDevelopmentDocument);
        valid &= processSpecialReviewBusinessRule(proposalDevelopmentDocument);
        valid &= processProposalYNQBusinessRule(proposalDevelopmentDocument, false);
        valid &= processBudgetVersionsBusinessRule(proposalDevelopmentDocument, false);
        valid &= processProposalGrantsGovBusinessRule(proposalDevelopmentDocument);
        valid &= processSponsorProgramBusinessRule(proposalDevelopmentDocument);
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

        for (ProposalSpecialReview propSpecialReview : proposalDevelopmentDocument.getDevelopmentProposal().getPropSpecialReviews()) {
            errorMap.addToErrorPath("propSpecialReview[" + i + "]");
            
            ProposalDevelopmentProposalSpecialReviewRule specialReviewRule = new ProposalDevelopmentProposalSpecialReviewRule();
            valid &= specialReviewRule.processValidSpecialReviewBusinessRules(propSpecialReview, "documentExemptNumbers[" + i + "]");
            valid &= specialReviewRule.processProposalSpecialReviewBusinessRules(propSpecialReview);
            
            errorMap.removeFromErrorPath("propSpecialReview[" + i + "]");
            i++;
        }
        return valid;
    }

    /**
    *
    * Validate proposal person questions rule. 
    * Answers are mandatory for routing
    * @param proposalDevelopmentDocument
    * @return
    */
    public boolean processProposalPersonYNQBusinessRule(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        boolean valid = true;

        //checkErrors();
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        int i = 0;
        List<ProposalPerson> proposalPersons = proposalDevelopmentDocument.getDevelopmentProposal().getInvestigators();
        for (ProposalPerson proposalPerson : proposalPersons) {
            List<ProposalPersonYnq> proposalPersonYnqs = proposalPerson.getProposalPersonYnqs();
            String errorPath = "proposalPerson[" + i + "]";
            errorMap.addToErrorPath(errorPath);
            for (ProposalPersonYnq proposalPersonYnq : proposalPersonYnqs) {
                /* look for answers - required for routing */
                if(StringUtils.isBlank(proposalPersonYnq.getAnswer())) {
                    valid = false;
                    errorMap.putError("answer", KeyConstants.ERROR_REQUIRED_ANSWER);
                }
            }
            errorMap.removeFromErrorPath(errorPath);
            i++;
        }
        return valid;
    }

    /**
    *
    * Validate proposal questions rule. validate explanation required and date required fields based on 
    * question configuration. Answers are mandatory for routing
    * @param proposalDevelopmentDocument
    * @return
    */
    public boolean processProposalYNQBusinessRule(ProposalDevelopmentDocument proposalDevelopmentDocument, boolean docRouting) {
        boolean valid = true;

        //checkErrors();
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        int i = 0;
        
        if(!errorMap.getErrorPath().contains("document")) {
            errorMap.clearErrorPath();
            errorMap.addToErrorPath("document");
            errorMap.addToErrorPath("developmentProposalList[0]");
        }
        HashMap ynqGroupSerial = getQuestionSerialNumberBasedOnGroup(proposalDevelopmentDocument);
        for (ProposalYnq proposalYnq : proposalDevelopmentDocument.getDevelopmentProposal().getProposalYnqs()) {
            
            String groupName = proposalYnq.getYnq().getGroupName();
            Integer serialNumber = (Integer)ynqGroupSerial.get(proposalYnq.getQuestionId());
            String errorPath = "proposalYnq[" + groupName + "][" + i + "]";
            errorMap.addToErrorPath(errorPath);
            String[] errorParameter = {serialNumber+"", groupName};
            String ynqAnswer = proposalYnq.getAnswer();
            /* look for answers - required for routing */
            if(docRouting && StringUtils.isBlank(proposalYnq.getAnswer())) {
                info("no answer");
                valid = false;
                errorMap.putError("answer", KeyConstants.ERROR_REQUIRED_ANSWER, errorParameter);
            }
            /* look for date requried */
            String dateRequiredFor = proposalYnq.getYnq().getDateRequiredFor();
            if(dateRequiredFor != null) {
                if (StringUtils.isNotBlank(ynqAnswer) && 
                        dateRequiredFor.contains(ynqAnswer) && 
                        proposalYnq.getReviewDate() == null) {
                    info("No review date");
                    valid = false;
                    errorMap.putError("reviewDate", KeyConstants.ERROR_REQUIRED_FOR_REVIEW_DATE);
                }
            }

            /* look for explanation requried */
            String explanationRequiredFor = proposalYnq.getYnq().getExplanationRequiredFor();
            if(explanationRequiredFor != null) {
                if (StringUtils.isNotBlank(ynqAnswer) && 
                    explanationRequiredFor.contains(ynqAnswer) && 
                    StringUtils.isBlank(proposalYnq.getExplanation())) {
                    info("No explanation date");
                    valid = false;
                    errorMap.putError("explanation", KeyConstants.ERROR_REQUIRED_FOR_EXPLANATION);
                }
            }
            errorMap.removeFromErrorPath(errorPath);
            i++;
        }
        return valid;

    }

    private HashMap getQuestionSerialNumberBasedOnGroup(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        HashMap ynqGroupSerial = new HashMap();
        for (YnqGroupName ynqGroupName : proposalDevelopmentDocument.getDevelopmentProposal().getYnqGroupNames()) {
            Integer serialNumber = Integer.valueOf(1);
            for (ProposalYnq proposalYnq : proposalDevelopmentDocument.getDevelopmentProposal().getProposalYnqs()) {
                if(ynqGroupName.getGroupName().equalsIgnoreCase(proposalYnq.getYnq().getGroupName())) {
                    ynqGroupSerial.put(proposalYnq.getQuestionId(), serialNumber);
                    serialNumber ++;
                }
            }
        }
        return ynqGroupSerial;
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
        DataDictionaryService dataDictionaryService = KraServiceLocator.getService(DataDictionaryService.class);
        
        valid = validateProposalTypeField(proposalDevelopmentDocument);

        proposalDevelopmentDocument.getDevelopmentProposal().refreshReferenceObject("sponsor");
        if (proposalDevelopmentDocument.getDevelopmentProposal().getSponsorCode() != null
                && proposalDevelopmentDocument.getDevelopmentProposal().getSponsor() == null) {
            valid = false;
            errorMap.putError("sponsorCode", KeyConstants.ERROR_MISSING, dataDictionaryService.getAttributeErrorLabel(
                    DevelopmentProposal.class, "sponsorCode"));
        }
        
        //if either is missing, it should be caught on the DD validation.
        if (proposalDevelopmentDocument.getDevelopmentProposal().getRequestedStartDateInitial() != null
                && proposalDevelopmentDocument.getDevelopmentProposal().getRequestedEndDateInitial() != null) {
            if (proposalDevelopmentDocument.getDevelopmentProposal().getRequestedStartDateInitial().after(
                    proposalDevelopmentDocument.getDevelopmentProposal().getRequestedEndDateInitial())) {
                valid = false;
                errorMap.putError("requestedStartDateInitial", KeyConstants.ERROR_START_DATE_AFTER_END_DATE,
                        new String[] {
                                dataDictionaryService.getAttributeErrorLabel(DevelopmentProposal.class,
                                        "requestedStartDateInitial"),
                                dataDictionaryService.getAttributeErrorLabel(DevelopmentProposal.class,
                                        "requestedEndDateInitial") });
            }
        }
        
        return valid;
    }
    
    /**
     * Validates business rules pertaining to the Proposal Type.  The rules are:
     * 
     * <ol>
     * <li>If the Proposal Type is Renewal, Revision, or Continuation, then the
     * Sponsor Proposal Id field must be assigned a value.</li>
     * </ol>
     * 
     * @param proposalDevelopmentDocument the Proposal Development Document
     * @return true if valid; otherwise false (if false, the Global ErrorMap is populated)
     */
    private boolean validateProposalTypeField(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        boolean valid = true;
        
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        DataDictionaryService dataDictionaryService = KraServiceLocator.getService(DataDictionaryService.class);
        
        String proposalTypeCode = proposalDevelopmentDocument.getDevelopmentProposal().getProposalTypeCode();
        String sponsorProposalId = proposalDevelopmentDocument.getDevelopmentProposal().getSponsorProposalNumber();

        if (isProposalTypeRenewalRevisionContinuation(proposalTypeCode) && StringUtils.isEmpty(sponsorProposalId)) {
            valid = false;
            errorMap.putError("sponsorProposalNumber", KeyConstants.ERROR_REQUIRED_PROPOSAL_SPONSOR_ID, dataDictionaryService
                    .getAttributeErrorLabel(DevelopmentProposal.class, "sponsorProposalNumber"));
        }
        
        // TODO: Must add in other validations regarding awards, etc.  see KRACOEUS-290.
        
        return valid;
    }
    
    /**
     * Is the Proposal Type set to Renewal, Revision, or a Continuation?
     * @param proposalTypeCode proposal type code
     * @return true or false
     */
    private boolean isProposalTypeRenewalRevisionContinuation(String proposalTypeCode) {
        String proposalTypeCodeRenewal = getKualiConfigurationService().getParameter(Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT,KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_RENEWAL).getParameterValue();
        String proposalTypeCodeRevision = getKualiConfigurationService().getParameter(Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT,KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_REVISION).getParameterValue();
        String proposalTypeCodeContinuation = getKualiConfigurationService().getParameter(Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT,KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_CONTINUATION).getParameterValue();
         
        return !StringUtils.isEmpty(proposalTypeCode) &&
               (proposalTypeCode.equals(proposalTypeCodeRenewal) ||
                proposalTypeCode.equals(proposalTypeCodeRevision) ||
                proposalTypeCode.equals(proposalTypeCodeContinuation));
    }

    /**
     *
     * Validate organization/location rule. specifically, at least one location is required.
     * 
     * @param proposalDevelopmentDocument
     * @return
     */
    private boolean processOrganizationLocationBusinessRule(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        boolean valid = true;

        if (proposalDevelopmentDocument.getDevelopmentProposal().getOrganizationId() != null
                && (proposalDevelopmentDocument.getDevelopmentProposal().getProposalLocations().size() == 0 || (proposalDevelopmentDocument
                        .getDevelopmentProposal().getProposalLocations().size() == 1 && ((proposalDevelopmentDocument.getDevelopmentProposal()
                        .getProposalLocations().get(0))).getLocationSequenceNumber() == null))) {
            GlobalVariables.getErrorMap().removeFromErrorPath("document");
            reportError("newPropLocation.location", KeyConstants.ERROR_REQUIRED_FOR_PROPLOCATION);
            GlobalVariables.getErrorMap().addToErrorPath("document");
            valid = false;
        }
        return valid;

    }
    
    /**
    *
    * Validate Grants.gov business rules.
    * @param proposalDevelopmentDocument
    * @return
    */
    private boolean processProposalGrantsGovBusinessRule(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        boolean valid = true;

        if (proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity() != null
                && proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getOpportunityId() != null
                && StringUtils.equalsIgnoreCase(proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getRevisionCode(),
                        getKualiConfigurationService().getParameter(Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT,
                                Constants.PARAMETER_COMPONENT_DOCUMENT, KeyConstants.S2S_REVISIONTYPE_OTHER).getParameterValue())
                && (proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getRevisionOtherDescription() == null || StringUtils
                        .equals(proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getRevisionOtherDescription().trim(),
                                ""))) {
            reportError("s2sOpportunity.revisionOtherDescription", KeyConstants.ERROR_IF_REVISIONTYPE_IS_OTHER);
            valid &= false;
        }
        
        if (proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity() != null
                && proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getOpportunityId() != null
                && !StringUtils.equalsIgnoreCase(proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getRevisionCode(),
                        getKualiConfigurationService().getParameter(Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT,
                                Constants.PARAMETER_COMPONENT_DOCUMENT, KeyConstants.S2S_REVISIONTYPE_OTHER).getParameterValue())
                && (proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getRevisionOtherDescription() != null && !StringUtils
                        .equals(proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getRevisionOtherDescription().trim(),
                                ""))) {
            reportError("s2sOpportunity.revisionOtherDescription",
                    KeyConstants.ERROR_IF_REVISIONTYPE_IS_NOT_OTHER_SPECIFY_NOT_BLANK);
            valid &= false;
        }        
        return valid;
    }
    
    public boolean processAddKeyPersonBusinessRules(ProposalDevelopmentDocument document, ProposalPerson person) {
        return new ProposalDevelopmentKeyPersonsRule().processAddKeyPersonBusinessRules(document, person);
    }
    /**
     * Validate Sponsor/program Information rule. Regex validation for CFDA number(7 digits with a period in the 3rd character and an optional alpha character in the 7th field).
     * @param proposalDevelopmentDocument
     * @return
    */
    private boolean processSponsorProgramBusinessRule(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        
        boolean valid = true;
        String regExpr = "(\\d{2})(\\.)(\\d{3})[a-zA-z]?";
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        DataDictionaryService dataDictionaryService = KraServiceLocator.getService(DataDictionaryService.class);
        if (StringUtils.isNotBlank(proposalDevelopmentDocument.getDevelopmentProposal().getCfdaNumber())
                && !(proposalDevelopmentDocument.getDevelopmentProposal().getCfdaNumber().matches(regExpr))
                && GlobalVariables.getErrorMap().getMessages("document.developmentProposalList[0].cfdaNumber") == null) {
            errorMap.putError("cfdaNumber", RiceKeyConstants.ERROR_INVALID_FORMAT, new String[] {
                    dataDictionaryService.getAttributeErrorLabel(DevelopmentProposal.class, "cfdaNumber"),
                    proposalDevelopmentDocument.getDevelopmentProposal().getCfdaNumber() });
            valid = false;
         }
        return valid;
    }
   
      
    /**
     * @see org.kuali.kra.proposaldevelopment.rule.AddNarrativeRule#processAddNarrativeBusinessRules(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument,org.kuali.kra.proposaldevelopment.bo.Narrative)
     */
    public boolean processAddNarrativeBusinessRules(AddNarrativeEvent addNarrativeEvent) {
        return new ProposalDevelopmentNarrativeRule().processAddNarrativeBusinessRules(addNarrativeEvent);
    }

    /**
     * @see org.kuali.rice.kns.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.rice.kns.document.Document)
     */
    @Override
    public boolean processRunAuditBusinessRules(Document document){
        boolean retval = true;
        
        retval &= super.processRunAuditBusinessRules(document);
        
        retval &= new ProposalDevelopmentProposalRequiredFieldsAuditRule().processRunAuditBusinessRules(document);
        
        retval &= new ProposalDevelopmentSponsorProgramInformationAuditRule().processRunAuditBusinessRules(document);
        
        retval &= new KeyPersonnelAuditRule().processRunAuditBusinessRules(document);
        
        //audit for Proposal Attachments to ensure status code is set to complete.
        retval &= new ProposalDevelopmentProposalAttachmentsAuditRule().processRunAuditBusinessRules(document);
        
        //Change for KRACOEUS-1403
        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument) document;
        proposalDevelopmentDocument.getDevelopmentProposal().getYnqService().populateProposalQuestions(
                proposalDevelopmentDocument.getDevelopmentProposal().getProposalYnqs(),
                proposalDevelopmentDocument.getDevelopmentProposal().getYnqGroupNames(), proposalDevelopmentDocument);
        processProposalYNQBusinessRule((ProposalDevelopmentDocument) document, true);
        retval &= new ProposalDevelopmentYnqAuditRule().processRunAuditBusinessRules(document);
        //Change for KRACOEUS-1403 ends here
        
        retval &= new ProposalSpecialReviewAuditRule().processRunAuditBusinessRules(document);        
        
        retval &= new ProposalDevelopmentGrantsGovAuditRule().processRunAuditBusinessRules(document);
        // audit check for budgetversion with final status
        try {
            retval &= KraServiceLocator.getService(ProposalDevelopmentService.class).validateBudgetAuditRule((ProposalDevelopmentDocument)document);
        } catch (Exception ex) {
            // TODO : should log it here
            throw new RuntimeException("Validate Budget Audit rules encountered exception", ex);
        }
        return retval;
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
     * 
     * @see org.kuali.kra.proposaldevelopment.rule.AddPersonnelAttachmentsRule#processAddPersonnelAttachmentsBusinessRules(org.kuali.kra.proposaldevelopment.rule.event.AddPersonnelAttachmentsEvent)
     */
    public boolean processSavePersonnelAttachmentBusinessRules(SavePersonnelAttachmentEvent savePersonnelAttachmentEvent) {
        return new ProposalDevelopmentPersonnelAttachmentRule().processSavePersonnelAttachmentBusinessRules(savePersonnelAttachmentEvent);
    }

    /**
     * Delegating method for the <code>{@link ChangeKeyPersonRule}</code> which is triggered by the <code>{@link ChangeKeyPersonEvent}</code>
     * 
     * @see org.kuali.kra.proposaldevelopment.rule.ChangeKeyPersonRule#processChangeKeyPersonBusinessRules(org.kuali.kra.proposaldevelopment.bo.ProposalPerson, org.kuali.rice.kns.bo.BusinessObject)
     */
    public boolean processChangeKeyPersonBusinessRules(ProposalPerson proposalPerson, BusinessObject source,int index) {
        return new ProposalDevelopmentKeyPersonsRule().processChangeKeyPersonBusinessRules(proposalPerson, source,index);
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
    
    /**
     * @see org.kuali.kra.proposaldevelopment.rule.PermissionsRule#processAddProposalUserBusinessRules(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument, java.util.List, org.kuali.kra.proposaldevelopment.bo.ProposalUser)
     */
    public boolean processAddProposalUserBusinessRules(ProposalDevelopmentDocument document,List<ProposalUserRoles> list, ProposalUser proposalUser) {
        return new ProposalDevelopmentPermissionsRule().processAddProposalUserBusinessRules(document, list, proposalUser);
    }
    
    /**
     * @see org.kuali.kra.proposaldevelopment.rule.PermissionsRule#processDeleteProposalUserBusinessRules(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument, java.util.List, int)
     */
    public boolean processDeleteProposalUserBusinessRules(ProposalDevelopmentDocument document,List<ProposalUserRoles> list, int index) {
        return new ProposalDevelopmentPermissionsRule().processDeleteProposalUserBusinessRules(document, list, index);
    }
    
    /**
     * @see org.kuali.kra.proposaldevelopment.rule.PermissionsRule#processEditProposalUserRolesBusinessRules(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument, java.util.List, org.kuali.kra.proposaldevelopment.bo.ProposalUserEditRoles)
     */
    public boolean processEditProposalUserRolesBusinessRules(ProposalDevelopmentDocument document, List<ProposalUserRoles> list, ProposalUserEditRoles editRoles) {
        return new ProposalDevelopmentPermissionsRule().processEditProposalUserRolesBusinessRules(document, list, editRoles);
    }

    
    /**
     * Delegate to {@link org.kuali.kra.proposaldevelopment.rules.ProposalDevelopmentKeyPersonsRule#processSaveKeyPersonBusinessRules(ProposalDevelopmentDocument)
     * 
     * @see org.kuali.kra.proposaldevelopment.rule.SaveKeyPersonRule#processSaveKeyPersonBusinessRules(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
     */
    public boolean processSaveKeyPersonBusinessRules(ProposalDevelopmentDocument document) {
        info("In processSaveKeyPersonBusinessRules()");
        return new ProposalDevelopmentKeyPersonsRule().processCustomSaveDocumentBusinessRules(document);
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.rule.NewNarrativeUserRightsRule#processNewNarrativeUserRightsBusinessRules(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument, java.util.List, int)
     */
    public boolean processNewNarrativeUserRightsBusinessRules(ProposalDevelopmentDocument document,
            List<NarrativeUserRights> newNarrativeUserRights, int narrativeIndex) {
        return new ProposalDevelopmentNarrativeRule().processNewNarrativeUserRightsBusinessRules(document, newNarrativeUserRights, narrativeIndex);
    }
    
    public boolean processCustomAttributeRules(SaveCustomAttributeEvent saveCustomAttributeEvent) {
        return new KraCustomAttributeRule().processCustomAttributeRules(saveCustomAttributeEvent);    
    }

    @Override
    protected KualiConfigurationService getKualiConfigurationService(){
        return KraServiceLocator.getService(KualiConfigurationService.class);
    }

    public boolean processCalculateCreditSplitBusinessRules(ProposalDevelopmentDocument document) {
        // TODO Auto-generated method stub
        return new ProposalDevelopmentKeyPersonsRule().processCalculateCreditSplitBusinessRules(document);
    }

    public boolean processProposalDataOverrideRules(ProposalDataOverrideEvent proposalDataOverrideEvent) {
        return new ProposalDevelopmentDataOverrideRule().processProposalDataOverrideRules(proposalDataOverrideEvent);
    }
}
