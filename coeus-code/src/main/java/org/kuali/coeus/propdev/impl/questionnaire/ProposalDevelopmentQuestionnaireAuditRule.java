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
package org.kuali.coeus.propdev.impl.questionnaire;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireUsage;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.answer.QuestionnaireAnswerService;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

import java.util.ArrayList;
import java.util.List;

import static org.kuali.kra.infrastructure.Constants.AUDIT_ERRORS;

public class ProposalDevelopmentQuestionnaireAuditRule extends KcTransactionalDocumentRuleBase implements DocumentAuditRule {

    private static final String PROPOSAL_QUESTIONNAIRE_KEY="questionnaireHelper.answerHeaders[%s].answers[0].answer";
    private static final String PROPOSAL_QUESTIONNAIRE_PANEL_KEY="%s%s%s";
   
    private transient QuestionnaireAnswerService questionnaireAnswerService;
    
    public boolean processRunAuditBusinessRules(Document document) {
        
        boolean valid = true;
        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)document;
        List<AnswerHeader> headers = getQuestionnaireAnswerService().getQuestionnaireAnswer(new ProposalDevelopmentModuleQuestionnaireBean(proposalDevelopmentDocument.getDevelopmentProposal()));  
        List<QuestionnaireUsage> usages = getQuestionnaireAnswerService().getPublishedQuestionnaire(new ProposalDevelopmentModuleQuestionnaireBean(proposalDevelopmentDocument.getDevelopmentProposal()));
        int i = 0;
        for (AnswerHeader answerHeader : headers) {
            if (!answerHeader.isCompleted()) {
                for(QuestionnaireUsage questionnaireUsage : usages){
                    String questionnaireId = questionnaireUsage.getQuestionnaire().getQuestionnaireSeqId();
                    if (questionnaireId.equalsIgnoreCase(answerHeader.getQuestionnaire().getQuestionnaireSeqId())){
                        valid = false;
                        getProposalS2sAuditErrorsByGroup("questionnaireHelper",questionnaireUsage.getQuestionnaireLabel(),i).add(
                            new AuditError(String.format(PROPOSAL_QUESTIONNAIRE_KEY, i, "complete"), KeyConstants.ERROR_QUESTIONNAIRE_NOT_COMPLETE,
                                    Constants.QUESTIONS_PAGE+"."+questionnaireUsage.getQuestionnaireLabel(), new String[] {questionnaireUsage.getQuestionnaireLabel()}));
                        break;
                    }
                }
            }
            i++; 
        }
        return valid;
    }
    
    
    private synchronized QuestionnaireAnswerService getQuestionnaireAnswerService() {
        if (questionnaireAnswerService == null) {
            questionnaireAnswerService = KcServiceLocator.getService(QuestionnaireAnswerService.class);
        }
        return questionnaireAnswerService;
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
        String key = String.format( PROPOSAL_QUESTIONNAIRE_PANEL_KEY, formProperty, usageLabel, answerHeaderIndex );
        if (!KNSGlobalVariables.getAuditErrorMap().containsKey(key)) {
           KNSGlobalVariables.getAuditErrorMap().put(key, new AuditCluster(usageLabel, auditErrors, AUDIT_ERRORS));
        }
        else {
            auditErrors = ((AuditCluster)KNSGlobalVariables.getAuditErrorMap().get(key)).getAuditErrorList();
        }
        
        return auditErrors;
    }

    
    

}
