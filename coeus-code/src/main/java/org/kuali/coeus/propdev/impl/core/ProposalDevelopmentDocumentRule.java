/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.core;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.api.sponsor.SponsorService;
import org.kuali.coeus.common.framework.custom.KcDocumentBaseAuditRule;
import org.kuali.coeus.common.framework.ynq.YnqGroupName;
import org.kuali.coeus.propdev.api.core.SubmissionInfoService;
import org.kuali.coeus.propdev.impl.abstrct.ProposalDevelopmentAbstractsRule;
import org.kuali.coeus.propdev.impl.attachment.*;
import org.kuali.coeus.propdev.impl.attachment.institute.*;
import org.kuali.coeus.propdev.impl.abstrct.AbstractsRule;
import org.kuali.coeus.propdev.impl.abstrct.ProposalAbstract;
import org.kuali.coeus.propdev.impl.basic.ProposalDevelopmentProposalRequiredFieldsAuditRule;
import org.kuali.coeus.propdev.impl.sponsor.ProposalDevelopmentSponsorProgramInformationAuditRule;
import org.kuali.coeus.propdev.impl.budget.editable.BudgetDataOverrideEvent;
import org.kuali.coeus.propdev.impl.budget.editable.BudgetDataOverrideRule;
import org.kuali.coeus.propdev.impl.budget.editable.ProposalBudgetDataOverrideRule;
import org.kuali.coeus.propdev.impl.copy.CopyProposalRule;
import org.kuali.coeus.propdev.impl.copy.ProposalCopyCriteria;
import org.kuali.coeus.propdev.impl.copy.ProposalDevelopmentCopyRule;
import org.kuali.coeus.propdev.impl.docperm.*;
import org.kuali.coeus.propdev.impl.editable.ProposalDataOverrideEvent;
import org.kuali.coeus.propdev.impl.editable.ProposalDataOverrideRule;
import org.kuali.coeus.propdev.impl.editable.ProposalDevelopmentDataOverrideRule;
import org.kuali.coeus.propdev.impl.keyword.PropScienceKeyword;
import org.kuali.coeus.propdev.impl.location.*;
import org.kuali.coeus.propdev.impl.person.KeyPersonnelAuditRule;
import org.kuali.coeus.propdev.impl.person.KeyPersonnelCertificationRule;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.attachment.*;
import org.kuali.coeus.propdev.impl.person.creditsplit.CalculateCreditSplitRule;
import org.kuali.coeus.propdev.impl.person.AddKeyPersonRule;
import org.kuali.coeus.propdev.impl.person.ChangeKeyPersonRule;
import org.kuali.coeus.propdev.impl.person.ProposalDevelopmentKeyPersonsRule;
import org.kuali.coeus.propdev.impl.person.SaveKeyPersonRule;
import org.kuali.coeus.propdev.impl.questionnaire.ProposalDevelopmentQuestionnaireAuditRule;
import org.kuali.coeus.propdev.impl.resubmit.ProposalDevelopmentResubmissionPromptRule;
import org.kuali.coeus.propdev.impl.resubmit.ResubmissionPromptRule;
import org.kuali.coeus.propdev.impl.resubmit.ResubmissionRuleEvent;
import org.kuali.coeus.propdev.impl.s2s.ProposalDevelopmentGrantsGovAuditRule;
import org.kuali.coeus.propdev.impl.s2s.question.ProposalDevelopmentS2sQuestionnaireAuditRule;
import org.kuali.coeus.propdev.impl.ynq.ProposalDevelopmentYnqAuditRule;
import org.kuali.coeus.propdev.impl.ynq.ProposalYnq;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.util.DateUtils;
import org.kuali.coeus.common.budget.framework.core.BudgetService;
import org.kuali.coeus.common.budget.framework.core.BudgetParentDocumentRule;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyException;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;
import org.kuali.rice.krad.rules.rule.event.ApproveDocumentEvent;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.util.HashMap;
import java.util.List;



/**
 * Main Business Rule class for <code>{@link ProposalDevelopmentDocument}</code>. Responsible for delegating rules to independent rule classes.
 *
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */

public class ProposalDevelopmentDocumentRule extends BudgetParentDocumentRule implements AddCongressionalDistrictRule, AddKeyPersonRule, AddNarrativeRule, ReplaceNarrativeRule, SaveNarrativesRule, AddInstituteAttachmentRule, ReplaceInstituteAttachmentRule, AddPersonnelAttachmentRule, ReplacePersonnelAttachmentRule, AddProposalSiteRule, KcBusinessRule, SaveProposalSitesRule, AbstractsRule, CopyProposalRule, ChangeKeyPersonRule, DeleteCongressionalDistrictRule, PermissionsRule, NewNarrativeUserRightsRule, SaveKeyPersonRule,CalculateCreditSplitRule, ProposalDataOverrideRule, ResubmissionPromptRule, BudgetDataOverrideRule, DocumentAuditRule {

    @SuppressWarnings("unused")
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalDevelopmentDocumentRule.class); 
    private static final String PROPOSAL_QUESTIONS_KEY="proposalYnq[%d].%s";
    private static final String PROPOSAL_QUESTIONS_KEY_PROPERTY_ANSWER="answer";
    private static final String PROPOSAL_QUESTIONS_KEY_PROPERTY_REVIEW_DATE="reviewDate";
    private static final String PROPOSAL_QUESTIONS_KEY_PROPERTY_EXPLANATION="explanation";

    private SponsorService sponsorService;
    private DataDictionaryService dataDictionaryService;
    private BudgetService budgetService;
    private SubmissionInfoService submissionInfoService;

    protected DataDictionaryService getDataDictionaryService (){
        if (dataDictionaryService == null)
            dataDictionaryService = KNSServiceLocator.getDataDictionaryService();
        return dataDictionaryService;
    }
    protected BudgetService getBudgetService (){
        if (budgetService ==null)
            budgetService = KcServiceLocator.getService(BudgetService.class);
        return budgetService;
    }

    protected SponsorService getSponsorService() {
        if (sponsorService ==null)
            sponsorService = KcServiceLocator.getService(SponsorService.class);
        return sponsorService;
    }
    protected SubmissionInfoService getSubmissionInfoService (){
        if (submissionInfoService == null)
            submissionInfoService = KcServiceLocator.getService(SubmissionInfoService.class);
        return submissionInfoService;
    }
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(Document document) {
        boolean retval = true;
        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument) document;

        retval &= super.processCustomRouteDocumentBusinessRules(document);
        retval &= new KeyPersonnelAuditRule().processRunAuditBusinessRules(document);
        retval &= new KeyPersonnelCertificationRule().processRouteDocument(document);
        
        return retval;
    }

    @Override
    protected boolean processCustomSaveDocumentBusinessRules(Document document) {
        if (!(document instanceof ProposalDevelopmentDocument)) {
            return false;
        }

        boolean valid = true;

        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument) document;
        
        GlobalVariables.getMessageMap().addToErrorPath("document.developmentProposalList[0]");
        valid &= processProposalRequiredFieldsBusinessRule(proposalDevelopmentDocument);
        
        valid &= processProposalYNQBusinessRule(proposalDevelopmentDocument, false);
        valid &= processBudgetVersionsBusinessRule(proposalDevelopmentDocument, false);
        valid &= processProposalGrantsGovBusinessRule(proposalDevelopmentDocument);
        valid &= processSponsorProgramBusinessRule(proposalDevelopmentDocument);
        valid &= processKeywordBusinessRule(proposalDevelopmentDocument);
        valid &= proccessValidateSponsor(proposalDevelopmentDocument);
        GlobalVariables.getMessageMap().removeFromErrorPath("document.developmentProposalList[0]");
     
        return valid;
    }
    
    @Override
    protected boolean processCustomApproveDocumentBusinessRules(ApproveDocumentEvent approveEvent) {
        boolean retval = super.processCustomApproveDocumentBusinessRules(approveEvent);

        retval &= new KeyPersonnelCertificationRule().processApproveDocument(approveEvent);
        
        return retval;
    }
    
    private boolean proccessValidateSponsor(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        boolean valid = true;
        DataDictionaryService dataDictionaryService = getDataDictionaryService();
        if (!this.getSponsorService().isValidSponsor(proposalDevelopmentDocument.getDevelopmentProposal().getSponsor())) {
            valid = false;
            GlobalVariables.getMessageMap().putError("sponsorCode", KeyConstants.ERROR_MISSING, dataDictionaryService.getAttributeErrorLabel(DevelopmentProposal.class, "sponsorCode"));
        }
        if (!StringUtils.isEmpty(proposalDevelopmentDocument.getDevelopmentProposal().getPrimeSponsorCode()) && 
                !this.getSponsorService().isValidSponsor(proposalDevelopmentDocument.getDevelopmentProposal().getPrimeSponsor())) {
            valid = false;
            GlobalVariables.getMessageMap().putError("primeSponsorCode", KeyConstants.ERROR_MISSING, dataDictionaryService.getAttributeErrorLabel(DevelopmentProposal.class, "primeSponsorCode"));
            
        }
        return valid;
    }
    
    public boolean processAddCongressionalDistrictRules(AddProposalCongressionalDistrictEvent addCongressionalDistrictEvent) {
        return new ProposalDevelopmentCongressionalDistrictRule().processAddCongressionalDistrictRules(addCongressionalDistrictEvent);
    }

    public boolean processDeleteCongressionalDistrictRules(DeleteProposalCongressionalDistrictEvent deleteCongressionalDistrictEvent) {
        return new ProposalDevelopmentCongressionalDistrictRule().processDeleteCongressionalDistrictRules(deleteCongressionalDistrictEvent);
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
        
        for( int j = 0; j < proposalDevelopmentDocument.getDevelopmentProposal().getProposalYnqs().size();j++) {
            ProposalYnq proposalYnq = proposalDevelopmentDocument.getDevelopmentProposal().getProposalYnqs().get(j);

            String groupName = proposalYnq.getYnq().getGroupName();
            HashMap<String,Integer> questionSerial = getQuestionSerialNumberBasedOnGroup( proposalDevelopmentDocument );
            String[] errorParameter = {groupName};
            String ynqAnswer = proposalYnq.getAnswer();
            /* look for answers - required for routing */
            if(docRouting && StringUtils.isBlank(proposalYnq.getAnswer())) {
                LOG.info("no answer");
                valid = false;
                
                reportError(String.format(PROPOSAL_QUESTIONS_KEY,j,PROPOSAL_QUESTIONS_KEY_PROPERTY_ANSWER), KeyConstants.ERROR_REQUIRED_ANSWER, questionSerial.get(proposalYnq.getQuestionId()).toString(),groupName);
            }
            /* look for date requried */
            String dateRequiredFor = proposalYnq.getYnq().getDateRequiredFor();
            if(dateRequiredFor != null) {
                if (StringUtils.isNotBlank(ynqAnswer) && 
                        dateRequiredFor.contains(ynqAnswer) && 
                        proposalYnq.getReviewDate() == null) {
                    LOG.info("No review date");
                    valid = false;
                    reportError(String.format(PROPOSAL_QUESTIONS_KEY,j,PROPOSAL_QUESTIONS_KEY_PROPERTY_REVIEW_DATE), KeyConstants.ERROR_REQUIRED_FOR_REVIEW_DATE,  questionSerial.get(proposalYnq.getQuestionId()).toString(),groupName);
     
                }
            }

            /* look for explanation requried */
            String explanationRequiredFor = proposalYnq.getYnq().getExplanationRequiredFor();
            if(explanationRequiredFor != null) {
                if (StringUtils.isNotBlank(ynqAnswer) && 
                    explanationRequiredFor.contains(ynqAnswer) && 
                    StringUtils.isBlank(proposalYnq.getExplanation())) {
                    LOG.info("No explanation date");
                    valid = false;
                    reportError(String.format(PROPOSAL_QUESTIONS_KEY,j,PROPOSAL_QUESTIONS_KEY_PROPERTY_EXPLANATION),  KeyConstants.ERROR_REQUIRED_FOR_EXPLANATION,  questionSerial.get(proposalYnq.getQuestionId()).toString(),groupName);
                }
            }
        }
        
        return valid;
    }
    
    
    public static HashMap<String,Integer> getQuestionSerialNumberBasedOnGroup(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        HashMap<String,Integer> ynqGroupSerial = new HashMap<String,Integer>();
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

        MessageMap errorMap = GlobalVariables.getMessageMap();
        DataDictionaryService dataDictionaryService = getDataDictionaryService();

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
        
        if (StringUtils.isNotBlank(proposalDevelopmentDocument.getDevelopmentProposal().getCurrentAwardNumber())) {
            if (getSubmissionInfoService().getProposalCurrentAwardSponsorAwardNumber(proposalDevelopmentDocument.getDevelopmentProposal().getCurrentAwardNumber()) == null) {
                valid = false;
                errorMap.putError("currentAwardNumber", KeyConstants.ERROR_MISSING, 
                        dataDictionaryService.getAttributeErrorLabel(DevelopmentProposal.class, "currentAwardNumber"));
            }
        }
        
        if (StringUtils.isNotBlank(proposalDevelopmentDocument.getDevelopmentProposal().getContinuedFrom())) {
            if (getSubmissionInfoService().getProposalContinuedFromVersionProposalId(proposalDevelopmentDocument.getDevelopmentProposal().getContinuedFrom()) == null) {
                valid = false;
                errorMap.putError("continuedFrom", KeyConstants.ERROR_MISSING, 
                        dataDictionaryService.getAttributeErrorLabel(DevelopmentProposal.class, "continuedFrom"));                
            }
        }
        
        return valid;
    }

    /**
    *
    *
    * Validate Grants.gov business rules.
    * @param proposalDevelopmentDocument
    * @return boolean
    */
    private boolean processProposalGrantsGovBusinessRule(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        boolean valid = true;

        if (proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity() != null
                && proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getOpportunityId() != null
                && StringUtils.equalsIgnoreCase(proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getRevisionCode(),
                        this.getS2sRevisionTypeOther())
                && (proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getRevisionOtherDescription() == null || StringUtils
                        .equals(proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getRevisionOtherDescription().trim(),
                                ""))) {
            reportError("s2sOpportunity.revisionOtherDescription", KeyConstants.ERROR_IF_REVISIONTYPE_IS_OTHER);
            valid &= false;
        }
        
        if (proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity() != null
                && proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getOpportunityId() != null
                && !StringUtils.equalsIgnoreCase(proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getRevisionCode(),
                        this.getS2sRevisionTypeOther())
                && (proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getRevisionOtherDescription() != null && !StringUtils
                        .equals(proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getRevisionOtherDescription().trim(),
                                ""))) {
            reportError("s2sOpportunity.revisionOtherDescription",
                    KeyConstants.ERROR_IF_REVISIONTYPE_IS_NOT_OTHER_SPECIFY_NOT_BLANK);
            valid &= false;
        }        
        return valid;
    }
    
    /**
     * Gets the S2s Revision Type Other parameter
     * @return the parameter
     */
    private String getS2sRevisionTypeOther() {
        return this.getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, KeyConstants.S2S_REVISIONTYPE_OTHER);
    }
    
    public boolean processAddKeyPersonBusinessRules(ProposalDevelopmentDocument document, ProposalPerson person) {
        return new ProposalDevelopmentKeyPersonsRule().processAddKeyPersonBusinessRules(document, person);
    }
    /**
     * Validate Sponsor/program Information rule. Regex validation for CFDA number(7 digits with a period in the 3rd character and an optional alpha character in the 7th field).
     * @param proposalDevelopmentDocument
     * @return boolean
    */
    private boolean processSponsorProgramBusinessRule(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        
        boolean valid = true;
        String regExpr = "(\\d{2})(\\.)(\\d{3})[a-zA-z]?";
        MessageMap errorMap = GlobalVariables.getMessageMap();
        DataDictionaryService dataDictionaryService = getDataDictionaryService();
        if (StringUtils.isNotBlank(proposalDevelopmentDocument.getDevelopmentProposal().getCfdaNumber())
                && !(proposalDevelopmentDocument.getDevelopmentProposal().getCfdaNumber().matches(regExpr))
                && GlobalVariables.getMessageMap().getMessages("document.developmentProposalList[0].cfdaNumber") == null) {
            errorMap.putError("developmentProposalList[0].cfdaNumber", RiceKeyConstants.ERROR_INVALID_FORMAT, new String[] {
                    dataDictionaryService.getAttributeErrorLabel(DevelopmentProposal.class, "cfdaNumber"),
                    proposalDevelopmentDocument.getDevelopmentProposal().getCfdaNumber() });
            valid = false;
         }
 
        SponsorService sponsorService = getSponsorService();
         String sponsorCode = proposalDevelopmentDocument.getDevelopmentProposal().getPrimeSponsorCode();
   
         if (sponsorCode != null)
         {
             String sponsorName = sponsorService.getSponsorName(sponsorCode);
             if (sponsorName == null)
             {
                 errorMap.putError("developmentProposalList[0].primeSponsorCode", RiceKeyConstants.ERROR_EXISTENCE, new String[] {
                         dataDictionaryService.getAttributeLabel(DevelopmentProposal.class, "primeSponsorCode") });
                 valid = false;
             }
         }
         if (proposalDevelopmentDocument.getDevelopmentProposal().getDeadlineTime() != null) {

            
             String deadLineTime = DateUtils.formatFrom12Or24Str(proposalDevelopmentDocument.getDevelopmentProposal().getDeadlineTime());
             if (!deadLineTime.equalsIgnoreCase(Constants.INVALID_TIME)) {
                 proposalDevelopmentDocument.getDevelopmentProposal().setDeadlineTime(deadLineTime);
             } else {
                 errorMap.putError("deadlineTime", KeyConstants.INVALID_DEADLINE_TIME, 
                         dataDictionaryService.getAttributeErrorLabel(DevelopmentProposal.class, "deadlineTime"));
                 valid = false;
             }
         }
        return valid;
    }
    
    private boolean processKeywordBusinessRule(ProposalDevelopmentDocument document) {
        List<PropScienceKeyword> keywords = document.getDevelopmentProposal().getPropScienceKeywords();
        
        for ( PropScienceKeyword keyword : keywords ) {
            for ( PropScienceKeyword keyword2 : keywords ) {
                if ( keyword == keyword2 ) {
                    continue;
                } else if ( StringUtils.equalsIgnoreCase(keyword.getScienceKeyword().getCode(), keyword2.getScienceKeyword().getCode()) ) {
                    GlobalVariables.getMessageMap().putError("propScienceKeyword", "error.proposalKeywords.duplicate");
                   
                    return false;
                }
            }
        }
        return true;
    }
   
      

    public boolean processAddNarrativeBusinessRules(AddNarrativeEvent addNarrativeEvent) {
        return new ProposalDevelopmentNarrativeRule().processAddNarrativeBusinessRules(addNarrativeEvent);
    }

    public boolean processRunAuditBusinessRules(Document document){
        if (((ProposalDevelopmentDocument)document).getDevelopmentProposal().isChild()) {
            throw new RuntimeException(new ProposalHierarchyException("Cannot run validation on a Proposal Hierarchy Child."));
        }
        boolean retval = true;
        
        retval &= new KcDocumentBaseAuditRule().processRunAuditBusinessRules(document);
        
        retval &= new ProposalDevelopmentProposalRequiredFieldsAuditRule().processRunAuditBusinessRules(document);
        
        retval &= new ProposalDevelopmentSponsorProgramInformationAuditRule().processRunAuditBusinessRules(document);
        
        retval &= new KeyPersonnelAuditRule().processRunAuditBusinessRules(document);
        
        retval &= new KeyPersonnelCertificationRule().processRunAuditBusinessRules(document);
        //audit for Proposal Attachments to ensure status code is set to complete.
        retval &= new ProposalDevelopmentProposalAttachmentsAuditRule().processRunAuditBusinessRules(document);
        
        //Change for KRACOEUS-1403
        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument) document;
        proposalDevelopmentDocument.getDevelopmentProposal().getYnqService().populateProposalQuestions(
                proposalDevelopmentDocument.getDevelopmentProposal().getProposalYnqs(),
                proposalDevelopmentDocument.getDevelopmentProposal().getYnqGroupNames(), proposalDevelopmentDocument);
        
        retval &= new ProposalDevelopmentYnqAuditRule().processRunAuditBusinessRules(document);

        retval &= new ProposalDevelopmentGrantsGovAuditRule().processRunAuditBusinessRules(document);
        
        retval &= new ProposalDevelopmentS2sQuestionnaireAuditRule().processRunAuditBusinessRules(proposalDevelopmentDocument);
        retval &= new ProposalDevelopmentQuestionnaireAuditRule().processRunAuditBusinessRules(proposalDevelopmentDocument);
        
        // audit check for budgetversion with final status
        try {
            retval &= getBudgetService().validateBudgetAuditRule((ProposalDevelopmentDocument)document);
        } catch (Exception ex) {
            throw new RuntimeException("Validate Budget Audit rules encountered exception", ex);
        }
       
        return retval;
    }

    @Override
    public boolean processAddAbstractBusinessRules(ProposalDevelopmentDocument document, ProposalAbstract proposalAbstract) {
        return new ProposalDevelopmentAbstractsRule().processAddAbstractBusinessRules(document, proposalAbstract);
    }

    public boolean processSaveNarrativesBusinessRules(SaveNarrativesEvent saveNarrativesEvent) {
        return new ProposalDevelopmentNarrativeRule().processSaveNarrativesBusinessRules(saveNarrativesEvent);
    }

    public boolean processReplaceNarrativeBusinessRules(ReplaceNarrativeEvent replaceNarrativeEvent) {
        return new ProposalDevelopmentNarrativeRule().processReplaceNarrativeBusinessRules(replaceNarrativeEvent);
    }

    @Override
    public boolean processCopyProposalBusinessRules(ProposalDevelopmentDocument document, ProposalCopyCriteria criteria) {
        return new ProposalDevelopmentCopyRule().processCopyProposalBusinessRules(document, criteria);
    }

    @Override
    public boolean processAddInstituteAttachmentBusinessRules(AddInstituteAttachmentEvent addInstituteAttachmentEvent) {
        return new ProposalDevelopmentInstituteAttachmentRule().processAddInstituteAttachmentBusinessRules(addInstituteAttachmentEvent);
    }

    public boolean processReplaceInstituteAttachmentBusinessRules(ReplaceInstituteAttachmentEvent event) {
        return new ProposalDevelopmentInstituteAttachmentRule().processReplaceInstituteAttachmentBusinessRules(event);
    }

    public boolean processAddPersonnelAttachmentBusinessRules(AddPersonnelAttachmentEvent addPersonnelAttachmentEvent) {
        return new ProposalDevelopmentPersonnelAttachmentRule().processAddPersonnelAttachmentBusinessRules(addPersonnelAttachmentEvent);    
    }

    public boolean processSavePersonnelAttachmentBusinessRules(SavePersonnelAttachmentEvent savePersonnelAttachmentEvent) {
        return new ProposalDevelopmentPersonnelAttachmentRule().processSavePersonnelAttachmentBusinessRules(savePersonnelAttachmentEvent);
    }

    public boolean processReplacePersonnelAttachmentBusinessRules(ReplacePersonnelAttachmentEvent event) {
        return new ProposalDevelopmentPersonnelAttachmentRule().processReplacePersonnelAttachmentBusinessRules(event);
    }
    
    /**
     * Delegating method for the <code>{@link ChangeKeyPersonRule}</code> which is triggered by the <code>{@link org.kuali.coeus.propdev.impl.person.ChangeKeyPersonEvent}</code>
     * 
     */
    public boolean processChangeKeyPersonBusinessRules(ProposalPerson proposalPerson, BusinessObject source,int index) {
        return new ProposalDevelopmentKeyPersonsRule().processChangeKeyPersonBusinessRules(proposalPerson, source, index);
    }

    @Override
    public boolean processAddProposalSiteBusinessRules(AddProposalSiteEvent addProposalLocationEvent) {
        return new ProposalDevelopmentProposalLocationRule().processAddProposalSiteBusinessRules(addProposalLocationEvent);    
    }

    public boolean processSaveProposalSiteBusinessRules(SaveProposalSitesEvent saveProposalSitesEvent) {
        return new ProposalDevelopmentProposalLocationRule().processSaveProposalSiteBusinessRules(saveProposalSitesEvent);    
    }
    
    @Override
    public boolean processAddProposalUserBusinessRules(ProposalDevelopmentDocument document,List<ProposalUserRoles> list, ProposalUser proposalUser) {
        return new ProposalDevelopmentPermissionsRule().processAddProposalUserBusinessRules(document, list, proposalUser);
    }
    
    @Override
    public boolean processDeleteProposalUserBusinessRules(ProposalDevelopmentDocument document,List<ProposalUserRoles> list, int index) {
        return new ProposalDevelopmentPermissionsRule().processDeleteProposalUserBusinessRules(document, list, index);
    }
    
    @Override
    public boolean processEditProposalUserRolesBusinessRules(ProposalDevelopmentDocument document, List<ProposalUserRoles> list, ProposalUserEditRoles editRoles) {
        return new ProposalDevelopmentPermissionsRule().processEditProposalUserRolesBusinessRules(document, list, editRoles);
    }

    
    /**
     * Delegate to {@link org.kuali.coeus.propdev.impl.person.ProposalDevelopmentKeyPersonsRule#processSaveKeyPersonBusinessRules(ProposalDevelopmentDocument)
     * 
     */
    public boolean processSaveKeyPersonBusinessRules(ProposalDevelopmentDocument document) {
        LOG.info("In processSaveKeyPersonBusinessRules()");
        return new ProposalDevelopmentKeyPersonsRule().processCustomSaveDocumentBusinessRules(document);
    }

    @Override
    public boolean processNewNarrativeUserRightsBusinessRules(ProposalDevelopmentDocument document,
            List<NarrativeUserRights> newNarrativeUserRights, int narrativeIndex) {
        return new ProposalDevelopmentNarrativeRule().processNewNarrativeUserRightsBusinessRules(document, newNarrativeUserRights, narrativeIndex);
    }

    public boolean processCalculateCreditSplitBusinessRules(ProposalDevelopmentDocument document) {
        return new ProposalDevelopmentKeyPersonsRule().processCalculateCreditSplitBusinessRules(document);
    }

    public boolean processProposalDataOverrideRules(ProposalDataOverrideEvent proposalDataOverrideEvent) {
        return new ProposalDevelopmentDataOverrideRule().processProposalDataOverrideRules(proposalDataOverrideEvent);
    }
    
    public boolean processBudgetDataOverrideRules(BudgetDataOverrideEvent budgetDataOverrideEvent) {
        return new ProposalBudgetDataOverrideRule().processBudgetDataOverrideRules(budgetDataOverrideEvent);
    }
    
    
    public boolean processResubmissionPromptBusinessRules(ResubmissionRuleEvent resubmissionRuleEvent) {
        return new ProposalDevelopmentResubmissionPromptRule().processResubmissionPromptBusinessRules(resubmissionRuleEvent);
    }

    public boolean processRules(KcDocumentEventBaseExtension event) {
        boolean retVal = false;
        retVal = event.getRule().processRules(event);
        return retVal;
    }
    
}
