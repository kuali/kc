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
package org.kuali.kra.proposaldevelopment.rules;

import static org.kuali.kra.infrastructure.Constants.AUDIT_ERRORS;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentS2sQuestionnaireService;
import org.kuali.kra.questionnaire.QuestionnaireUsage;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.kra.s2s.bo.S2sOppForms;
import org.kuali.kra.s2s.bo.S2sOpportunity;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

public class ProposalDevelopmentS2sQuestionnaireAuditRule extends ResearchDocumentRuleBase implements DocumentAuditRule {

    private static final String PROPOSAL_S2S_QUESTIONS_KEY="s2sQuestionnaireHelper.answerHeaders[%s].answers[0].answer";
    private static final String PROPOSAL_S2S_QUESTIONNAIRE_PANEL_KEY="%s%s%s";
   
    private transient ProposalDevelopmentS2sQuestionnaireService proposalDevelopmentS2sQuestionnaireService;
    
    public boolean processRunAuditBusinessRules(Document document) {
        
        boolean valid = true;
        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)document;
        DevelopmentProposal developmentProposal = proposalDevelopmentDocument.getDevelopmentProposal();
        S2sOpportunity opp = developmentProposal.getS2sOpportunity();
        
        if (opp!=null && opp.getS2sOppForms()!=null) {
            for (S2sOppForms oppforms : opp.getS2sOppForms()) {
                List<QuestionnaireUsage> usages = getProposalDevelopmentS2sQuestionnaireService().getQuestionnaireUsages(oppforms.getOppNameSpace(), oppforms.getFormName());
                // if the returned usages list is empty, there are no Questionnaires for that opp form.
                if (usages.size()>0) {
                    List<AnswerHeader> headers = proposalDevelopmentS2sQuestionnaireService.getProposalAnswerHeaderForForm(developmentProposal,oppforms.getOppNameSpace(),oppforms.getFormName());
                    for (int i=0;i<headers.size();i++) {
                        AnswerHeader header = headers.get(i);
                        if (!header.getCompleted()) {
                            valid = false;
                            getProposalS2sAuditErrorsByGroup("s2sQuestionnaireHelper",usages.get(0).getQuestionnaireLabel(),i).add(
                                    new AuditError(String.format(PROPOSAL_S2S_QUESTIONS_KEY, i), KeyConstants.ERROR_S2S_QUESTIONNAIRE_NOT_COMPLETE,
                                            Constants.QUESTIONS_PAGE+"."+usages.get(0).getQuestionnaireLabel(), new String[] {usages.get(0).getQuestionnaireLabel()}));
                        }
                    }
                }
            }
        }
        return valid;
    }
    
    private synchronized ProposalDevelopmentS2sQuestionnaireService getProposalDevelopmentS2sQuestionnaireService() {
        if (proposalDevelopmentS2sQuestionnaireService == null) {
            proposalDevelopmentS2sQuestionnaireService = KraServiceLocator.getService(ProposalDevelopmentS2sQuestionnaireService.class);
        }
        return proposalDevelopmentS2sQuestionnaireService;
    }
    
    
    /**
     * This method should only be called if an audit error is intending to be added because it will actually add a <code>{@link List<AuditError>}</code>
     * to the auditErrorMap.
     * 
     * @return List of AuditError instances
     */
    @SuppressWarnings("unchecked")
    private List<AuditError> getProposalS2sAuditErrorsByGroup(String formProperty, String usageLabel, Integer answerHeaderIndex) {
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        String key = String.format( PROPOSAL_S2S_QUESTIONNAIRE_PANEL_KEY, formProperty, usageLabel, answerHeaderIndex );
        
        if (!KNSGlobalVariables.getAuditErrorMap().containsKey(key)) {
           KNSGlobalVariables.getAuditErrorMap().put(key, new AuditCluster(usageLabel, auditErrors, AUDIT_ERRORS));
        }
        else {
            auditErrors = ((AuditCluster)KNSGlobalVariables.getAuditErrorMap().get(key)).getAuditErrorList();
        }
        
        return auditErrors;
    }

    
    

}
