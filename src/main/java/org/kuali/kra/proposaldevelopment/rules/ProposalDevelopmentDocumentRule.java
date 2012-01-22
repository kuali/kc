/*
 * Copyright 2005-2010 The Kuali Foundation
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
import org.kuali.kra.budget.core.BudgetService;
import org.kuali.kra.common.specialreview.rule.event.SaveSpecialReviewEvent;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.NarrativeUserRights;
import org.kuali.kra.proposaldevelopment.bo.PropScienceKeyword;
import org.kuali.kra.proposaldevelopment.bo.ProposalAbstract;
import org.kuali.kra.proposaldevelopment.bo.ProposalCopyCriteria;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalUser;
import org.kuali.kra.proposaldevelopment.bo.ProposalUserEditRoles;
import org.kuali.kra.proposaldevelopment.bo.ProposalYnq;
import org.kuali.kra.proposaldevelopment.bo.YnqGroupName;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.hierarchy.ProposalHierarchyException;
import org.kuali.kra.proposaldevelopment.rule.AbstractsRule;
import org.kuali.kra.proposaldevelopment.rule.AddCongressionalDistrictRule;
import org.kuali.kra.proposaldevelopment.rule.AddInstituteAttachmentRule;
import org.kuali.kra.proposaldevelopment.rule.AddKeyPersonRule;
import org.kuali.kra.proposaldevelopment.rule.AddNarrativeRule;
import org.kuali.kra.proposaldevelopment.rule.AddPersonnelAttachmentRule;
import org.kuali.kra.proposaldevelopment.rule.AddProposalSiteRule;
import org.kuali.kra.proposaldevelopment.rule.CalculateCreditSplitRule;
import org.kuali.kra.proposaldevelopment.rule.ChangeKeyPersonRule;
import org.kuali.kra.proposaldevelopment.rule.CopyProposalRule;
import org.kuali.kra.proposaldevelopment.rule.DeleteCongressionalDistrictRule;
import org.kuali.kra.proposaldevelopment.rule.DeleteProposalSiteRule;
import org.kuali.kra.proposaldevelopment.rule.NewNarrativeUserRightsRule;
import org.kuali.kra.proposaldevelopment.rule.PermissionsRule;
import org.kuali.kra.proposaldevelopment.rule.ProposalDataOverrideRule;
import org.kuali.kra.proposaldevelopment.rule.ResubmissionPromptRule;
import org.kuali.kra.proposaldevelopment.rule.SaveKeyPersonRule;
import org.kuali.kra.proposaldevelopment.rule.SaveNarrativesRule;
import org.kuali.kra.proposaldevelopment.rule.event.AddInstituteAttachmentEvent;
import org.kuali.kra.proposaldevelopment.rule.event.AddNarrativeEvent;
import org.kuali.kra.proposaldevelopment.rule.event.AddPersonnelAttachmentEvent;
import org.kuali.kra.proposaldevelopment.rule.event.AddProposalCongressionalDistrictEvent;
import org.kuali.kra.proposaldevelopment.rule.event.AddProposalSiteEvent;
import org.kuali.kra.proposaldevelopment.rule.event.BasicProposalSiteEvent;
import org.kuali.kra.proposaldevelopment.rule.event.ChangeKeyPersonEvent;
import org.kuali.kra.proposaldevelopment.rule.event.ClearProposalSiteAddressRule;
import org.kuali.kra.proposaldevelopment.rule.event.DeleteProposalCongressionalDistrictEvent;
import org.kuali.kra.proposaldevelopment.rule.event.ProposalDataOverrideEvent;
import org.kuali.kra.proposaldevelopment.rule.event.ResubmissionRuleEvent;
import org.kuali.kra.proposaldevelopment.rule.event.SaveNarrativesEvent;
import org.kuali.kra.proposaldevelopment.rule.event.SavePersonnelAttachmentEvent;
import org.kuali.kra.proposaldevelopment.rule.event.SaveProposalSitesEvent;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService;
import org.kuali.kra.proposaldevelopment.specialreview.ProposalSpecialReview;
import org.kuali.kra.proposaldevelopment.web.bean.ProposalUserRoles;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rule.event.KraDocumentEventBaseExtension;
import org.kuali.kra.rule.event.SaveCustomAttributeEvent;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.kra.service.SponsorService;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;



/**
 * Main Business Rule class for <code>{@link ProposalDevelopmentDocument}</code>. Responsible for delegating rules to independent rule classes.
 *
 * @see org.kuali.proposaldevelopment.rules.KeyPersonnelAuditRule
 * @see org.kuali.proposaldevelopment.rules.PersonEditableFieldRule
 * @see org.kuali.proposaldevelopment.rules.ProposalDevelopmentKeyPersonsRule
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class ProposalDevelopmentDocumentRule extends ResearchDocumentRuleBase implements AddCongressionalDistrictRule, AddKeyPersonRule, AddNarrativeRule,SaveNarrativesRule, AddInstituteAttachmentRule, AddPersonnelAttachmentRule, AddProposalSiteRule, BusinessRuleInterface, SaveProposalSitesRule, DeleteProposalSiteRule, ClearProposalSiteAddressRule, AbstractsRule, CopyProposalRule, ChangeKeyPersonRule, DeleteCongressionalDistrictRule, PermissionsRule, NewNarrativeUserRightsRule, SaveKeyPersonRule,CalculateCreditSplitRule, ProposalDataOverrideRule, ResubmissionPromptRule {
    
    @SuppressWarnings("unused")
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalDevelopmentDocumentRule.class); 
    private static final String PROPOSAL_QUESTIONS_KEY="proposalYnq[%d].%s";
    private static final String PROPOSAL_QUESTIONS_KEY_PROPERTY_ANSWER="answer";
    private static final String PROPOSAL_QUESTIONS_KEY_PROPERTY_REVIEW_DATE="reviewDate";
    private static final String PROPOSAL_QUESTIONS_KEY_PROPERTY_EXPLANATION="explanation";
    private static final String SAVE_SPECIAL_REVIEW_FIELD = "document.developmentProposalList[0].propSpecialReviews";
    
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(Document document) {
        boolean retval = true;
        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument) document;

        retval &= super.processCustomRouteDocumentBusinessRules(document);
        retval &= new KeyPersonnelAuditRule().processRunAuditBusinessRules(document);

        return retval;
    }

    @Override
    protected boolean processCustomSaveDocumentBusinessRules(Document document) {
        if (!(document instanceof ProposalDevelopmentDocument)) {
            return false;
        }

        boolean valid = true;

        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument) document;

        GlobalVariables.getMessageMap().addToErrorPath(DOCUMENT_ERROR_PATH);
        getDictionaryValidationService().validateDocumentAndUpdatableReferencesRecursively(
            document, getMaxDictionaryValidationDepth(), VALIDATION_REQUIRED, CHOMP_LAST_LETTER_S_FROM_COLLECTION_NAME);
        GlobalVariables.getMessageMap().removeFromErrorPath(DOCUMENT_ERROR_PATH);
        
        GlobalVariables.getMessageMap().addToErrorPath("document.developmentProposalList[0]");
        valid &= processProposalRequiredFieldsBusinessRule(proposalDevelopmentDocument);
        valid &= processProtocolCustomDataBusinessRules(proposalDevelopmentDocument);
        
        GlobalVariables.getMessageMap().removeFromErrorPath("document.developmentProposalList[0]");
        valid &= processSpecialReviewBusinessRule(proposalDevelopmentDocument);
        GlobalVariables.getMessageMap().addToErrorPath("document.developmentProposalList[0]");
        
        valid &= processProposalYNQBusinessRule(proposalDevelopmentDocument, false);
        valid &= processBudgetVersionsBusinessRule(proposalDevelopmentDocument, false);
        valid &= processProposalGrantsGovBusinessRule(proposalDevelopmentDocument);
        valid &= processSponsorProgramBusinessRule(proposalDevelopmentDocument);
        valid &= processKeywordBusinessRule(proposalDevelopmentDocument);
        GlobalVariables.getMessageMap().removeFromErrorPath("document.developmentProposalList[0]");
     
        return valid;
    }
    
    private boolean processProtocolCustomDataBusinessRules(ProposalDevelopmentDocument document) {
        return processRules(new SaveCustomAttributeEvent(Constants.EMPTY_STRING, document));
    }

    /**
     * This method validates 'Proposal Special review'. It checks
     * validSpecialReviewApproval table, and if there is a match, then checks
     * protocalnumberflag, applicationdateflag, and approvaldataflag.
     *
     * @param proposalDevelopmentDocument : The proposalDevelopmentDocument that is being validated
     * @return valid Does the validation pass
     */
    private boolean processSpecialReviewBusinessRule(ProposalDevelopmentDocument proposalDocument) {
        List<ProposalSpecialReview> specialReviews = proposalDocument.getDevelopmentProposal().getPropSpecialReviews();
        boolean isProtocolLinkingEnabled 
            = getParameterService().getParameterValueAsBoolean("KC-PROTOCOL", "Document", "irb.protocol.development.proposal.linking.enabled");
        return processRules(new SaveSpecialReviewEvent<ProposalSpecialReview>(
            SAVE_SPECIAL_REVIEW_FIELD, proposalDocument, specialReviews, isProtocolLinkingEnabled));
    }

    public boolean processDeleteProposalSiteRules(BasicProposalSiteEvent proposalSiteEvent) {
        return new ProposalSiteRule().processBasicProposalSiteRules(proposalSiteEvent);
    }
    
    public boolean processClearProposalSiteAddressRules(BasicProposalSiteEvent ProposalSiteEvent) {
        return new ProposalSiteRule().processBasicProposalSiteRules(ProposalSiteEvent);
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
                info("no answer");
                valid = false;
                
                reportError(String.format(PROPOSAL_QUESTIONS_KEY,j,PROPOSAL_QUESTIONS_KEY_PROPERTY_ANSWER), KeyConstants.ERROR_REQUIRED_ANSWER, questionSerial.get(proposalYnq.getQuestionId()).toString(),groupName);
            }
            /* look for date requried */
            String dateRequiredFor = proposalYnq.getYnq().getDateRequiredFor();
            if(dateRequiredFor != null) {
                if (StringUtils.isNotBlank(ynqAnswer) && 
                        dateRequiredFor.contains(ynqAnswer) && 
                        proposalYnq.getReviewDate() == null) {
                    info("No review date");
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
                    info("No explanation date");
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
        DataDictionaryService dataDictionaryService = KraServiceLocator.getService(DataDictionaryService.class);

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
        
        ProposalDevelopmentService proposalDevelopmentService = KraServiceLocator.getService(ProposalDevelopmentService.class);
        if (StringUtils.isNotBlank(proposalDevelopmentDocument.getDevelopmentProposal().getCurrentAwardNumber())) {
            if (proposalDevelopmentService.getProposalCurrentAwardVersion(proposalDevelopmentDocument) == null) {
                valid = false;
                errorMap.putError("currentAwardNumber", KeyConstants.ERROR_MISSING, 
                        dataDictionaryService.getAttributeErrorLabel(DevelopmentProposal.class, "currentAwardNumber"));
            }
        }
        
        if (StringUtils.isNotBlank(proposalDevelopmentDocument.getDevelopmentProposal().getContinuedFrom())) {
            if (proposalDevelopmentService.getProposalContinuedFromVersion(proposalDevelopmentDocument) == null) {
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
    * @return
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
     * @return
    */
    private boolean processSponsorProgramBusinessRule(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        
        boolean valid = true;
        String regExpr = "(\\d{2})(\\.)(\\d{3})[a-zA-z]?";
        MessageMap errorMap = GlobalVariables.getMessageMap();
        DataDictionaryService dataDictionaryService = KraServiceLocator.getService(DataDictionaryService.class);
        if (StringUtils.isNotBlank(proposalDevelopmentDocument.getDevelopmentProposal().getCfdaNumber())
                && !(proposalDevelopmentDocument.getDevelopmentProposal().getCfdaNumber().matches(regExpr))
                && GlobalVariables.getMessageMap().getMessages("document.developmentProposalList[0].cfdaNumber") == null) {
            errorMap.putError("developmentProposalList[0].cfdaNumber", RiceKeyConstants.ERROR_INVALID_FORMAT, new String[] {
                    dataDictionaryService.getAttributeErrorLabel(DevelopmentProposal.class, "cfdaNumber"),
                    proposalDevelopmentDocument.getDevelopmentProposal().getCfdaNumber() });
            valid = false;
         }
 
        SponsorService sponsorService = KraServiceLocator.getService(SponsorService.class);
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
 
        return valid;
    }
    
    private boolean processKeywordBusinessRule(ProposalDevelopmentDocument document) {
        List<PropScienceKeyword> keywords = document.getDevelopmentProposal().getPropScienceKeywords();
        
        for ( PropScienceKeyword keyword : keywords ) {
            for ( PropScienceKeyword keyword2 : keywords ) {
                if ( keyword == keyword2 ) {
                    continue;
                } else if ( StringUtils.equalsIgnoreCase(keyword.getScienceKeywordCode(), keyword2.getScienceKeywordCode()) ) {
                    GlobalVariables.getMessageMap().putError("propScienceKeyword", "error.proposalKeywords.duplicate");
                   
                    return false;
                }
            }
        }
        return true;
    }
   
      
    /**
     * @see org.kuali.kra.proposaldevelopment.rule.AddNarrativeRule#processAddNarrativeBusinessRules(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument,org.kuali.kra.proposaldevelopment.bo.Narrative)
     */
    public boolean processAddNarrativeBusinessRules(AddNarrativeEvent addNarrativeEvent) {
        return new ProposalDevelopmentNarrativeRule().processAddNarrativeBusinessRules(addNarrativeEvent);
    }

    /**
     * @see org.kuali.rice.krad.rules.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.rice.krad.document.Document)
     */
    @Override
    public boolean processRunAuditBusinessRules(Document document){
        if (((ProposalDevelopmentDocument)document).getDevelopmentProposal().isChild()) {
            throw new RuntimeException(new ProposalHierarchyException("Cannot run validation on a Proposal Hierarchy Child."));
        }
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
        
        retval &= new ProposalDevelopmentYnqAuditRule().processRunAuditBusinessRules(document);
        //Change for KRACOEUS-1403 ends here       
        retval &= new ProposalDevelopmentGrantsGovAuditRule().processRunAuditBusinessRules(document);
        
        retval &= new ProposalDevelopmentS2sQuestionnaireAuditRule().processRunAuditBusinessRules(proposalDevelopmentDocument);
        retval &= new ProposalDevelopmentQuestionnaireAuditRule().processRunAuditBusinessRules(proposalDevelopmentDocument);
        
        // audit check for budgetversion with final status
        try {
            retval &= KraServiceLocator.getService(BudgetService.class).validateBudgetAuditRule((ProposalDevelopmentDocument)document);
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
     * @see org.kuali.kra.proposaldevelopment.rule.ChangeKeyPersonRule#processChangeKeyPersonBusinessRules(org.kuali.kra.proposaldevelopment.bo.ProposalPerson, org.kuali.rice.krad.bo.BusinessObject)
     */
    public boolean processChangeKeyPersonBusinessRules(ProposalPerson proposalPerson, BusinessObject source,int index) {
        return new ProposalDevelopmentKeyPersonsRule().processChangeKeyPersonBusinessRules(proposalPerson, source,index);
    }

    /**
     * 
     * @see org.kuali.kra.proposaldevelopment.rule.AddProposalSiteRule#processAddProposalSiteBusinessRules(org.kuali.kra.proposaldevelopment.rule.event.AddProposalSiteEvent)
     */
    public boolean processAddProposalSiteBusinessRules(AddProposalSiteEvent addProposalLocationEvent) {
        return new ProposalDevelopmentProposalLocationRule().processAddProposalSiteBusinessRules(addProposalLocationEvent);    
    }

    public boolean processSaveProposalSiteBusinessRules(SaveProposalSitesEvent saveProposalSitesEvent) {
        return new ProposalDevelopmentProposalLocationRule().processSaveProposalSiteBusinessRules(saveProposalSitesEvent);    
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

    public boolean processCalculateCreditSplitBusinessRules(ProposalDevelopmentDocument document) {
        // TODO Auto-generated method stub
        return new ProposalDevelopmentKeyPersonsRule().processCalculateCreditSplitBusinessRules(document);
    }

    public boolean processProposalDataOverrideRules(ProposalDataOverrideEvent proposalDataOverrideEvent) {
        return new ProposalDevelopmentDataOverrideRule().processProposalDataOverrideRules(proposalDataOverrideEvent);
    }
    
    public boolean processResubmissionPromptBusinessRules(ResubmissionRuleEvent resubmissionRuleEvent) {
        return new ProposalDevelopmentResubmissionPromptRule().processResubmissionPromptBusinessRules(resubmissionRuleEvent);
    }

    public boolean processRules(KraDocumentEventBaseExtension event) {
        boolean retVal = false;
        retVal = event.getRule().processRules(event);
        return retVal;
    }

}