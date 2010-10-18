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
package org.kuali.kra.irb.questionnaire;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.questionnaire.QuestionnaireUsage;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean;
import org.kuali.kra.questionnaire.answer.QuestionnaireAnswerService;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.DocumentAuditRule;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.GlobalVariables;

public class ProtocolQuestionnaireAuditRule  extends ResearchDocumentRuleBase implements DocumentAuditRule {
    
    private static final String MANDATORY_QUESTIONNAIRE_AUDIT_ERRORS = "mandatoryQuestionnaireAuditErrors";
    
    private List<AuditError> auditErrors;
    
    /**
     * @see org.kuali.kra.rules.ResearchDocumentRuleBase#processRunAuditBusinessRules(org.kuali.rice.kns.document.Document)
     */
    public boolean processRunAuditBusinessRules(Document document) {
        ProtocolDocument protocolDocument = (ProtocolDocument) document;
        auditErrors = new ArrayList<AuditError>();
        
        List<Integer> headerIds = getIncompleteMandatoryQuestionnaire(protocolDocument.getProtocol());

        if (!headerIds.isEmpty()) {
            addErrorToAuditErrors(headerIds);
        }
        reportAndCreateAuditCluster();
        
        return headerIds.isEmpty();
    }
    
    private List<Integer> getIncompleteMandatoryQuestionnaire(Protocol protocol) {
        List<Integer> headers = new ArrayList<Integer>();
        boolean isValid = true;
        int i = 0;
        for (AnswerHeader answerHeader : getQuestionnaireAnswerService().getQuestionnaireAnswer(new ModuleQuestionnaireBean(CoeusModule.IRB_MODULE_CODE, protocol))) {
            if (getQuestionnaireUsage(CoeusModule.IRB_MODULE_CODE,answerHeader.getQuestionnaire().getQuestionnaireUsages()).isMandatory() && !answerHeader.getCompleted()) {
                headers.add(i);
            }
            i++;
        }
        return headers;

    }
    
    private QuestionnaireUsage getQuestionnaireUsage(String moduleItemCode, List<QuestionnaireUsage> questionnaireUsages) {
        QuestionnaireUsage usage = null;
        int version = 0;
        for (QuestionnaireUsage questionnaireUsage : questionnaireUsages) {
            if (usage == null || (moduleItemCode.equals(questionnaireUsage.getModuleItemCode()) && questionnaireUsage.getQuestionnaireSequenceNumber() > version)) {
                version = questionnaireUsage.getQuestionnaireSequenceNumber();
                usage = questionnaireUsage;
            }            
        }
        return usage;
    }
    /**
     * Creates and adds the Audit Error to the <code>{@link List<AuditError>}</code> auditError.
     */
    protected void addErrorToAuditErrors(List<Integer> headerIds) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Constants.PROTOCOL_QUESTIONNAIRE_PAGE);
        stringBuilder.append(".");
        stringBuilder.append(Constants.PROTOCOL_QUESTIONNAIRE_PANEL_ANCHOR);
        for (Integer id : headerIds) {
            auditErrors.add(new AuditError("questionnaireHelper.answerHeaders[" + id + "].answers[0].answer",
                KeyConstants.ERROR_MANDATORY_QUESTIONNAIRE, stringBuilder.toString()));
        }
    }

    /**
     * Creates and adds the AuditCluster to the Global AuditErrorMap.
     */
    @SuppressWarnings("unchecked")
    protected void reportAndCreateAuditCluster() {
        if (auditErrors.size() > 0) {
            GlobalVariables.getAuditErrorMap().put(MANDATORY_QUESTIONNAIRE_AUDIT_ERRORS, 
                    new AuditCluster(Constants.PROTOCOL_QUESTIONNAIRE_PANEL_NAME, auditErrors, Constants.AUDIT_ERRORS));
        }
    }
    
    protected QuestionnaireAnswerService getQuestionnaireAnswerService() {
        return KraServiceLocator.getService(QuestionnaireAnswerService.class);
    }
}
