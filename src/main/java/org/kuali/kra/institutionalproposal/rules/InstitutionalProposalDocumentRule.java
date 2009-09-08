/*
 * Copyright 2006-2008 The Kuali Foundation
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

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.commitments.AwardCostShareRuleEvent;
import org.kuali.kra.award.commitments.AwardCostShareRuleImpl;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.InstitutionalProposalCustomDataAuditRule;
import org.kuali.kra.institutionalproposal.InstitutionalProposalGraduateStudentAuditRule;
import org.kuali.kra.institutionalproposal.customdata.InstitutionalProposalCustomDataRuleImpl;
import org.kuali.kra.institutionalproposal.customdata.InstitutionalProposalSaveCustomDataRuleEvent;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalUnrecoveredFandA;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.RiceKeyConstants;

/**
 * This class...
 */
public class InstitutionalProposalDocumentRule extends ResearchDocumentRuleBase {
    
    public static final String DOCUMENT_ERROR_PATH = "document";
    public static final String INSTITUTIONAL_PROPOSAL_ERROR_PATH = "institutionalProposalList[0]";
    public static final String IP_ERROR_PATH = "institutionalProposal";
    
    public static final boolean VALIDATION_REQUIRED = true;
    public static final boolean CHOMP_LAST_LETTER_S_FROM_COLLECTION_NAME = false;
    
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
        retval &= new InstitutionalProposalGraduateStudentAuditRule().processRunAuditBusinessRules(document);

        return retval;
        
        
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
    

}
