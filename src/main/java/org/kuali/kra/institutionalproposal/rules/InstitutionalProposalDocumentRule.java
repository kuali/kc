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
package org.kuali.kra.institutionalproposal.rules;

import java.util.List;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.institutionalproposal.InstitutionalProposalCustomDataAuditRule;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalCreditSplitBean;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPersonAuditRule;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPersonSaveRuleEvent;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPersonSaveRuleImpl;
import org.kuali.kra.institutionalproposal.customdata.InstitutionalProposalCustomDataRuleImpl;
import org.kuali.kra.institutionalproposal.customdata.InstitutionalProposalSaveCustomDataRuleEvent;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalSpecialReview;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalUnrecoveredFandA;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rule.SpecialReviewRule;
import org.kuali.kra.rule.event.AddSpecialReviewEvent;
import org.kuali.kra.rule.event.KraDocumentEventBaseExtension;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.kra.rules.SpecialReviewRulesImpl;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * This class...
 */
public class InstitutionalProposalDocumentRule extends ResearchDocumentRuleBase implements SpecialReviewRule<InstitutionalProposalSpecialReview>
                                                                              , BusinessRuleInterface {
    
    public static final String DOCUMENT_ERROR_PATH = "document";
    public static final String INSTITUTIONAL_PROPOSAL_ERROR_PATH = "institutionalProposalList[0]";
    public static final String IP_ERROR_PATH = "institutionalProposal";
    
    public static final boolean VALIDATION_REQUIRED = true;
    public static final boolean CHOMP_LAST_LETTER_S_FROM_COLLECTION_NAME = false;
    
    /**
     * Error upon add - 
     * 1.  Select a special review type
     * 2.  Select an approval status
     * 3.  Approval Date should be later than Application Date
     */
    public boolean processAddSpecialReviewEvent(AddSpecialReviewEvent<InstitutionalProposalSpecialReview> addSpecialReviewEvent) {
        SpecialReviewRulesImpl ruleImpl = new SpecialReviewRulesImpl();
        return ruleImpl.processAddSpecialReviewEvent(addSpecialReviewEvent);
    }
    
    /**
     * 
     * @see org.kuali.core.rules.DocumentRuleBase#processCustomSaveDocumentBusinessRules(
     * org.kuali.rice.kns.document.Document)
     */
    @Override
    protected boolean processCustomSaveDocumentBusinessRules(Document document) {
        boolean retval = true;
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        if (!(document instanceof InstitutionalProposalDocument)) {
            return false;
        }
        
        errorMap.addToErrorPath(DOCUMENT_ERROR_PATH);
        getDictionaryValidationService().validateDocumentAndUpdatableReferencesRecursively(
                document, getMaxDictionaryValidationDepth(),
                VALIDATION_REQUIRED, CHOMP_LAST_LETTER_S_FROM_COLLECTION_NAME);
        errorMap.removeFromErrorPath(DOCUMENT_ERROR_PATH);
        
        
        retval &= processSaveInstitutionalProposalCustomDataBusinessRules(document);
        retval &= processUnrecoveredFandABusinessRules(document);
        retval &= processSponsorProgramBusinessRule(document);
        retval &= processInstitutionalProposalBusinessRules(document);
        retval &= processInstitutionalProposalFinancialRules(document);
        
        errorMap.addToErrorPath(DOCUMENT_ERROR_PATH);
        retval &= processSpecialReviewBusinessRule(document);
        errorMap.addToErrorPath(DOCUMENT_ERROR_PATH);

        retval &= processInstitutionalProposalPersonBusinessRules(errorMap, document);
        retval &= processInstitutionalProposalPersonCreditSplitBusinessRules(document);
        retval &= processInstitutionalProposalPersonUnitCreditSplitBusinessRules(document);
        
        return retval;
    }    
    
    /**
    *
    * process save Custom Data Business Rules.
    * @param institutionalProposalDocument
    * @return
    */
    private boolean processSaveInstitutionalProposalCustomDataBusinessRules(Document document) {
        boolean valid = true;
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        InstitutionalProposalDocument institutionalProposalDocument = (InstitutionalProposalDocument) document;
        errorMap.addToErrorPath(DOCUMENT_ERROR_PATH);
        errorMap.addToErrorPath(INSTITUTIONAL_PROPOSAL_ERROR_PATH);
        String errorPath = "institutionalProposalCustomData";
        errorMap.addToErrorPath(errorPath);
        InstitutionalProposalSaveCustomDataRuleEvent event = new InstitutionalProposalSaveCustomDataRuleEvent(errorPath, 
                                                               institutionalProposalDocument);
        valid &= new InstitutionalProposalCustomDataRuleImpl().processSaveInstitutionalProposalCustomDataBusinessRules(event);
        errorMap.removeFromErrorPath(errorPath);
        errorMap.removeFromErrorPath(INSTITUTIONAL_PROPOSAL_ERROR_PATH);
        errorMap.removeFromErrorPath(DOCUMENT_ERROR_PATH);
        return valid;
    }
    
    /**
    *
    * process Cost Share business rules.
    * @param awardDocument
    * @return
    */
    private boolean processUnrecoveredFandABusinessRules(Document document) {
        boolean valid = true;
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        InstitutionalProposalDocument institutionalProposalDocument = (InstitutionalProposalDocument) document;
        int i = 0;
        List<InstitutionalProposalUnrecoveredFandA> institutionalProposalUnrecoveredFandAs = 
                                    institutionalProposalDocument.getInstitutionalProposal().getInstitutionalProposalUnrecoveredFandAs();
        errorMap.addToErrorPath(DOCUMENT_ERROR_PATH);
        errorMap.addToErrorPath(IP_ERROR_PATH);
        for (InstitutionalProposalUnrecoveredFandA institutionalProposalUnrecoveredFandA : institutionalProposalUnrecoveredFandAs) {
            String errorPath = "institutionalProposalUnrecoveredFandAs[" + i + Constants.RIGHT_SQUARE_BRACKET;
            errorMap.addToErrorPath(errorPath);
            InstitutionalProposalSaveUnrecoveredFandARuleEvent event = new InstitutionalProposalSaveUnrecoveredFandARuleEvent(errorPath, 
                                                                                institutionalProposalDocument, 
                                                                                institutionalProposalUnrecoveredFandA);
            valid &= new InstitutionalProposalUnrecoveredFandARuleImpl().processSaveInstitutionalProposalUnrecoveredFandABusinessRules(event);
            errorMap.removeFromErrorPath(errorPath);
            i++;
        }
        errorMap.removeFromErrorPath(IP_ERROR_PATH);
        errorMap.removeFromErrorPath(DOCUMENT_ERROR_PATH);
        return valid;
    }
    
    /**
     * @see org.kuali.core.rule.DocumentAuditRule#processRunAuditBusinessRules(
     * org.kuali.rice.kns.document.Document)
     */
    public boolean processRunAuditBusinessRules(Document document){
        boolean retval = true;
        
        //retval &= super.processRunAuditBusinessRules(document);
        retval &= new InstitutionalProposalCustomDataAuditRule().processRunAuditBusinessRules(document);
        retval &= new InstitutionalProposalPersonAuditRule().processRunAuditBusinessRules(document);
        return retval;
        
        
    }
    
    private boolean processInstitutionalProposalPersonBusinessRules(ErrorMap errorMap, Document document) {
        errorMap.addToErrorPath(DOCUMENT_ERROR_PATH);
        errorMap.addToErrorPath(IP_ERROR_PATH);
        InstitutionalProposalPersonSaveRuleEvent event = new InstitutionalProposalPersonSaveRuleEvent("Project Persons", "projectPersons", document);
        boolean success = new InstitutionalProposalPersonSaveRuleImpl().processInstitutionalProposalPersonSaveBusinessRules(event);
        errorMap.removeFromErrorPath(IP_ERROR_PATH);
        errorMap.removeFromErrorPath(DOCUMENT_ERROR_PATH);
        
        return success;
    }
    
    private boolean processInstitutionalProposalPersonCreditSplitBusinessRules(Document document) {
        InstitutionalProposalDocument institutionalProposalDocument = (InstitutionalProposalDocument) document;
        return new InstitutionalProposalCreditSplitBean(institutionalProposalDocument).recalculateCreditSplit();
        
    }
    
    private boolean processInstitutionalProposalPersonUnitCreditSplitBusinessRules(Document document) {
        InstitutionalProposalDocument institutionalProposalDocument = (InstitutionalProposalDocument) document;
        return new InstitutionalProposalCreditSplitBean(institutionalProposalDocument).recalculateCreditSplit();
    }
    
    /**
     * Validate Sponsor/program Information rule. Regex validation for CFDA number(7 digits with a period in the 3rd character and an optional alpha character in the 7th field).
     * @param proposalDevelopmentDocument
     * @return
    */
    private boolean processSponsorProgramBusinessRule(Document document) {
        boolean valid = true;
        InstitutionalProposalDocument institutionalProposalDocument = (InstitutionalProposalDocument) document;
        String errorPath = "institutionalSponsorAndProgram";
        InstitutionalProposalSponsorAndProgramRuleEvent event = new InstitutionalProposalSponsorAndProgramRuleEvent(errorPath, 
                                                               institutionalProposalDocument, institutionalProposalDocument.getInstitutionalProposal());
        valid &= new InstitutionalProposalSponsorAndProgramRuleImpl().processInstitutionalProposalSponsorAndProgramRules(event);
        return valid;
    } 
    
    /**
     * This method validates 'Proposal Special review'. It checks
     * validSpecialReviewApproval table, and if there is a match, then checks
     * protocalnumberflag, applicationdateflag, and approvaldataflag.
     *
     * @paramDocument : The institutionalProposalDocument that is being validated
     * @return valid Does the validation pass
     */
    private boolean processSpecialReviewBusinessRule(Document document) {
        boolean valid = true;
        InstitutionalProposalDocument institutionalProposalDocument = (InstitutionalProposalDocument) document;

        ErrorMap errorMap = GlobalVariables.getErrorMap();

        int i = 0;

        for (InstitutionalProposalSpecialReview propSpecialReview : institutionalProposalDocument.getInstitutionalProposal().getSpecialReviews()) {
            errorMap.addToErrorPath("institutionalProposal.specialReview[" + i + "]");
            InstitutionalProposalSpecialReviewRule specialReviewRule = new InstitutionalProposalSpecialReviewRule();
            valid &= specialReviewRule.processValidSpecialReviewBusinessRules(propSpecialReview, "documentExemptNumbers[" + i + "]");
            valid &= specialReviewRule.processProposalSpecialReviewBusinessRules(propSpecialReview);
            
            errorMap.removeFromErrorPath("institutionalProposal.specialReview[" + i + "]");
            i++;
        }
        return valid;
    }
    
    /**
     * Validate Sponsor/program Information rule. Regex validation for CFDA number(7 digits with a period in the 3rd character and an optional alpha character in the 7th field).
     * @param proposalDevelopmentDocument
     * @return
    */
    private boolean processInstitutionalProposalFinancialRules(Document document) {
        boolean valid = true;
        InstitutionalProposalDocument institutionalProposalDocument = (InstitutionalProposalDocument) document;
        String errorPath = "institutionalProposalFinancial";
        InstitutionalProposalFinancialRuleEvent event = new InstitutionalProposalFinancialRuleEvent(errorPath, 
                                                               institutionalProposalDocument, institutionalProposalDocument.getInstitutionalProposal());
        valid &= new InstitutionalProposalFinancialRuleImpl().processInstitutionalProposalFinancialRules(event);
        return valid;
    }    
    
    /**
     * Validate information on Institutional Proposal Tab from Institutional Proposal Home page.
     * @param proposalDevelopmentDocument
     * @return
    */
    private boolean processInstitutionalProposalBusinessRules(Document document) {
        boolean valid = true;
        InstitutionalProposalDocument institutionalProposalDocument = (InstitutionalProposalDocument) document;
        String errorPath = "institutionalProposal";
        InstitutionalProposalRuleEvent event = new InstitutionalProposalRuleEvent(errorPath, 
                                                               institutionalProposalDocument, institutionalProposalDocument.getInstitutionalProposal());
        valid &= new InstitutionalProposalRuleImpl().processInstitutionalProposalRules(event);
        return valid;
    }

    public boolean processRules(KraDocumentEventBaseExtension event) {
        boolean retVal = false;
        retVal = event.getRule().processRules(event);
        return retVal;
    }    
    

}
