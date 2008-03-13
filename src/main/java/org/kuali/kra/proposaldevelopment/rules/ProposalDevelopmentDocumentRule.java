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

import java.util.List;

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
import org.kuali.kra.proposaldevelopment.bo.NarrativeUserRights;
import org.kuali.kra.proposaldevelopment.bo.ProposalAbstract;
import org.kuali.kra.proposaldevelopment.bo.ProposalCopyCriteria;
import org.kuali.kra.proposaldevelopment.bo.ProposalLocation;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonYnq;
import org.kuali.kra.proposaldevelopment.bo.ProposalSpecialReview;
import org.kuali.kra.proposaldevelopment.bo.ProposalUser;
import org.kuali.kra.proposaldevelopment.bo.ProposalUserEditRoles;
import org.kuali.kra.proposaldevelopment.bo.ProposalYnq;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.AbstractsRule;
import org.kuali.kra.proposaldevelopment.rule.AddInstituteAttachmentRule;
import org.kuali.kra.proposaldevelopment.rule.AddKeyPersonRule;
import org.kuali.kra.proposaldevelopment.rule.AddNarrativeRule;
import org.kuali.kra.proposaldevelopment.rule.AddPersonnelAttachmentRule;
import org.kuali.kra.proposaldevelopment.rule.AddProposalLocationRule;
import org.kuali.kra.proposaldevelopment.rule.AddProposalSpecialReviewRule;
import org.kuali.kra.proposaldevelopment.rule.ChangeKeyPersonRule;
import org.kuali.kra.proposaldevelopment.rule.CopyProposalRule;
import org.kuali.kra.proposaldevelopment.rule.NewNarrativeUserRightsRule;
import org.kuali.kra.proposaldevelopment.rule.PermissionsRule;
import org.kuali.kra.proposaldevelopment.rule.SaveNarrativesRule;
import org.kuali.kra.proposaldevelopment.rule.SavePersonnelAttachmentRule;
import org.kuali.kra.proposaldevelopment.rule.event.AddInstituteAttachmentEvent;
import org.kuali.kra.proposaldevelopment.rule.event.AddNarrativeEvent;
import org.kuali.kra.proposaldevelopment.rule.event.AddPersonnelAttachmentEvent;
import org.kuali.kra.proposaldevelopment.rule.event.AddProposalLocationEvent;
import org.kuali.kra.proposaldevelopment.rule.event.AddProposalSpecialReviewEvent;
import org.kuali.kra.proposaldevelopment.rule.event.ChangeKeyPersonEvent;
import org.kuali.kra.proposaldevelopment.rule.event.SaveNarrativesEvent;
import org.kuali.kra.proposaldevelopment.rule.event.SavePersonnelAttachmentEvent;
import org.kuali.kra.proposaldevelopment.service.SystemParameterRetrievalService;
import org.kuali.kra.proposaldevelopment.web.bean.ProposalUserRoles;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.kra.service.DocumentValidationService;
import org.kuali.kra.rule.CustomAttributeRule;
import org.kuali.kra.rule.event.SaveCustomAttributeEvent;
import org.kuali.kra.rules.KraCustomAttributeRule;
import org.kuali.RiceKeyConstants;

/**
 * Main Business Rule class for <code>{@link ProposalDevelopmentDocument}</code>. Responsible for delegating rules to independent rule classes.
 *
 * @see org.kuali.proposaldevelopment.rules.KeyPersonnelAuditRule
 * @see org.kuali.proposaldevelopment.rules.PersonEditableFieldRule
 * @see org.kuali.proposaldevelopment.rules.ProposalDevelopmentKeyPersonsRule
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class ProposalDevelopmentDocumentRule extends ResearchDocumentRuleBase implements AddKeyPersonRule, AddNarrativeRule,SaveNarrativesRule, AddInstituteAttachmentRule, AddPersonnelAttachmentRule, AddProposalLocationRule,AddProposalSpecialReviewRule , DocumentAuditRule, AbstractsRule, CopyProposalRule, ChangeKeyPersonRule, PermissionsRule, CustomAttributeRule  {
    
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(Document document) {
        boolean retval = true;
        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument) document;

        retval &= super.processCustomRouteDocumentBusinessRules(document);

        retval &= new ProposalDevelopmentKeyPersonsRule().processCustomRouteDocumentBusinessRules(document);
        
        retval &= processProposalYNQBusinessRule(proposalDevelopmentDocument, true);
        
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
        
        getDictionaryValidationService().validateDocumentAndUpdatableReferencesRecursively(document, getMaxDictionaryValidationDepth(), true, true);

        valid &= processProposalRequiredFieldsBusinessRule(proposalDevelopmentDocument);
        valid &= processOrganizationLocationBusinessRule(proposalDevelopmentDocument);
        valid &= processSpecialReviewBusinessRule(proposalDevelopmentDocument);
        valid &= processProposalYNQBusinessRule(proposalDevelopmentDocument, false);
        valid &= processBudgetVersionsBusinessRule(proposalDevelopmentDocument.getBudgetVersionOverviews());
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
        List<ProposalPerson> proposalPersons = proposalDevelopmentDocument.getInvestigators();
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
        for (ProposalYnq proposalYnq : proposalDevelopmentDocument.getProposalYnqs()) {
            
            String groupName = proposalYnq.getYnq().getGroupName();
            String errorPath = "proposalYnq[" + groupName + "][" + i + "]";
            errorMap.addToErrorPath(errorPath);
            /* look for answers - required for routing */
            if(docRouting && StringUtils.isBlank(proposalYnq.getAnswer())) {
                valid = false;
                errorMap.putError("answer", KeyConstants.ERROR_REQUIRED_ANSWER);
            }
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
        DataDictionaryService dataDictionaryService = KraServiceLocator.getService(DataDictionaryService.class);
        
        valid = validateProposalTypeField(proposalDevelopmentDocument);

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
        
        String proposalTypeCode = proposalDevelopmentDocument.getProposalTypeCode();
        String sponsorProposalId = proposalDevelopmentDocument.getSponsorProposalNumber();

        if (isProposalTypeRenewalRevisionContinuation(proposalTypeCode) && StringUtils.isEmpty(sponsorProposalId)) {
            valid = false;
            errorMap.putError("sponsorProposalNumber", KeyConstants.ERROR_REQUIRED_PROPOSAL_SPONSOR_ID, dataDictionaryService.getAttributeErrorLabel(ProposalDevelopmentDocument.class, "sponsorProposalNumber"));
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
        String proposalTypeCodeRenewal = getSystemParameterRetreivalService().getParameterValue(KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_RENEWAL, "3");
        String proposalTypeCodeRevision = getSystemParameterRetreivalService().getParameterValue(KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_REVISION, "5");
        String proposalTypeCodeContinuation = getSystemParameterRetreivalService().getParameterValue(KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_CONTINUATION, "2");
         
        return !StringUtils.isEmpty(proposalTypeCode) &&
               (proposalTypeCode.equals(proposalTypeCodeRenewal) ||
                proposalTypeCode.equals(proposalTypeCodeRevision) ||
                proposalTypeCode.equals(proposalTypeCodeContinuation));
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
            reportError("newPropLocation.location", KeyConstants.ERROR_REQUIRED_FOR_PROPLOCATION);
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
        
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        if (proposalDevelopmentDocument.getProposalTypeCode()!=null && proposalDevelopmentDocument.getS2sOpportunity()!= null && proposalDevelopmentDocument.getS2sOpportunity().getOpportunityId()!=null && proposalDevelopmentDocument.getProposalTypeCode().equals(getSystemParameterRetreivalService().getParameterValue(KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_REVISION, "6")) && proposalDevelopmentDocument.getS2sOpportunity().getRevisionCode()==null) {            
            reportError("s2sOpportunity.revisionCode", KeyConstants.ERROR_IF_PROPOSALTYPE_IS_REVISION);
            valid = false;
        }
        
        if(proposalDevelopmentDocument.getS2sOpportunity()!= null && proposalDevelopmentDocument.getS2sOpportunity().getOpportunityId()!=null && StringUtils.equalsIgnoreCase(proposalDevelopmentDocument.getS2sOpportunity().getRevisionCode(), getSystemParameterRetreivalService().getParameterValue(KeyConstants.S2S_REVISIONTYPE_OTHER, "5")) && (proposalDevelopmentDocument.getS2sOpportunity().getRevisionOtherDescription()==null||StringUtils.equals(proposalDevelopmentDocument.getS2sOpportunity().getRevisionOtherDescription().trim(), ""))){
            reportError("s2sOpportunity.revisionOtherDescription",KeyConstants.ERROR_IF_REVISIONTYPE_IS_OTHER);
            valid &= false;
        }
        
        if(proposalDevelopmentDocument.getS2sOpportunity()!= null && proposalDevelopmentDocument.getS2sOpportunity().getOpportunityId()!=null && !StringUtils.equalsIgnoreCase(proposalDevelopmentDocument.getS2sOpportunity().getRevisionCode(), getSystemParameterRetreivalService().getParameterValue(KeyConstants.S2S_REVISIONTYPE_OTHER, "5")) && (proposalDevelopmentDocument.getS2sOpportunity().getRevisionOtherDescription()!=null && !StringUtils.equals(proposalDevelopmentDocument.getS2sOpportunity().getRevisionOtherDescription().trim(), ""))){
            reportError("s2sOpportunity.revisionOtherDescription",KeyConstants.ERROR_IF_REVISIONTYPE_IS_NOT_OTHER_SPECIFY_NOT_BLANK);
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
        if(StringUtils.isNotBlank(proposalDevelopmentDocument.getCfdaNumber()) && !(proposalDevelopmentDocument.getCfdaNumber().matches(regExpr)) && GlobalVariables.getErrorMap().getMessages("document.cfdaNumber") == null) 
        {
            errorMap.putError("cfdaNumber", RiceKeyConstants.ERROR_INVALID_FORMAT, new String []{dataDictionaryService.getAttributeErrorLabel(ProposalDevelopmentDocument.class, "cfdaNumber"), proposalDevelopmentDocument.getCfdaNumber() });
            valid = false;
         }
        return valid;
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
        boolean retval = true;
        
        retval &= new ProposalDevelopmentSponsorProgramInformationAuditRule().processRunAuditBusinessRules(document);
        
        retval &= new KeyPersonnelAuditRule().processRunAuditBusinessRules(document);
        
        retval &= new ProposalDevelopmentGrantsGovAuditRule().processRunAuditBusinessRules(document);
        
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
     * @see org.kuali.kra.proposaldevelopment.rule.NewNarrativeUserRightsRule#processNewNarrativeUserRightsBusinessRules(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument, java.util.List, int)
     */
    public boolean processNewNarrativeUserRightsBusinessRules(ProposalDevelopmentDocument document,
            List<NarrativeUserRights> newNarrativeUserRights, int narrativeIndex) {
        return new ProposalDevelopmentNarrativeRule().processNewNarrativeUserRightsBusinessRules(document, newNarrativeUserRights, narrativeIndex);
    }
    
    public boolean processCustomAttributeRules(SaveCustomAttributeEvent saveCustomAttributeEvent) {
        return new KraCustomAttributeRule().processCustomAttributeRules(saveCustomAttributeEvent);    
    }
    
    private SystemParameterRetrievalService getSystemParameterRetreivalService(){
        return KraServiceLocator.getService(SystemParameterRetrievalService.class);
    }
}
