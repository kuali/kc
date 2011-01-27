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
import org.kuali.kra.bo.CoeusSubModule;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.questionnaire.BaseQuestionnaireAuditRule;
import org.kuali.kra.questionnaire.QuestionnaireUsage;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean;
import org.kuali.kra.questionnaire.answer.QuestionnaireAnswerService;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.DocumentAuditRule;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.GlobalVariables;

public class ProtocolQuestionnaireAuditRule  extends BaseQuestionnaireAuditRule<ProtocolDocument> implements DocumentAuditRule {
    
    //private static final String MANDATORY_QUESTIONNAIRE_AUDIT_ERRORS = "questionnaireHelper%s%s";
    
    private static final String PROTOCOL_QUESTIONNAIRE_KEY="questionnaireHelper.answerHeaders[%s].answers[0].answer";
    private static final String PROTOCOL_QUESTIONNAIRE_PANEL_KEY="%s%s%s";
    
    private boolean requestSubmission;
    
    public boolean processRunAuditBusinessRules(Document document) {
        Protocol protocol = ((ProtocolDocument)document).getProtocol();
        
        boolean isValid = true;
        List<AnswerHeader> headers = getQuestionnaireAnswerService().getQuestionnaireAnswer(new ProtocolModuleQuestionnaireBean(protocol));
        
        if (headers!=null) {
            for (int i=0;i<headers.size();i++) {
                AnswerHeader header = headers.get(i);
                QuestionnaireUsage usage = getQuestionnaireUsage(CoeusModule.IRB_MODULE_CODE,header.getQuestionnaire().getQuestionnaireUsages());
                if (usage.isMandatory() && !header.getCompleted()) {
                    isValid = false;
                    addErrorToAuditErrors(i,usage);
                }
            }
        }
        return isValid;
            
    }
    
    protected List<Integer> getIncompleteMandatoryQuestionnaire(ProtocolDocument protocolDocument) {
        Protocol protocol = protocolDocument.getProtocol();
        ModuleQuestionnaireBean moduleQuestionnaireBean = new ProtocolModuleQuestionnaireBean(protocol);
        if (isRequestSubmission()) {
            moduleQuestionnaireBean.setModuleSubItemCode(CoeusSubModule.PROTOCOL_SUBMISSION);
        }
        return super.getIncompleteMandatoryQuestionnaire(CoeusModule.IRB_MODULE_CODE, moduleQuestionnaireBean);
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
    protected void addErrorToAuditErrors(Integer answerHeaderIndex, QuestionnaireUsage usage) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Constants.PROTOCOL_QUESTIONNAIRE_PAGE);
        stringBuilder.append(".");
        stringBuilder.append(Constants.PROTOCOL_QUESTIONNAIRE_PANEL_ANCHOR);
        
        getProtocolAuditErrors("questionnaireHelper",usage.getQuestionnaireLabel(),answerHeaderIndex).add(new AuditError(String.format(PROTOCOL_QUESTIONNAIRE_KEY, answerHeaderIndex),
                KeyConstants.ERROR_MANDATORY_QUESTIONNAIRE, stringBuilder.toString()));
        
    }
    
    
    /**
     * This method should only be called if an audit error is intending to be added because it will actually add a <code>{@link List<AuditError>}</code>
     * to the auditErrorMap.
     * 
     * @return List of AuditError instances
     */
    @SuppressWarnings("unchecked")
    private List<AuditError> getProtocolAuditErrors(String formProperty, String usageLabel, Integer answerHeaderIndex) {
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        String key = String.format( PROTOCOL_QUESTIONNAIRE_PANEL_KEY, formProperty, usageLabel, answerHeaderIndex );
        
        if (!GlobalVariables.getAuditErrorMap().containsKey(key)) {
           GlobalVariables.getAuditErrorMap().put(key, new AuditCluster(usageLabel, auditErrors, Constants.AUDIT_ERRORS));
        }
        else {
            auditErrors = ((AuditCluster)GlobalVariables.getAuditErrorMap().get(key)).getAuditErrorList();
        }
        
        return auditErrors;
    }
    
    
    public boolean isMandatorySubmissionQuestionnaireComplete(List<AnswerHeader> answerHeaders) {
        boolean isValid = true;
        for (AnswerHeader answerHeader : answerHeaders) {
            if (getQuestionnaireUsage(CoeusModule.IRB_MODULE_CODE, CoeusSubModule.PROTOCOL_SUBMISSION, answerHeader.getQuestionnaire().getQuestionnaireUsages()).isMandatory() 
                    && !getQuestionnaireAnswerService().isQuestionnaireAnswerComplete(answerHeader.getAnswers())) {
                isValid = false;
                break;
            }
        }
        return isValid;
    }
    
    /**
     * Creates and adds the AuditCluster to the Global AuditErrorMap.
     */

    protected QuestionnaireAnswerService getQuestionnaireAnswerService() {
        return KraServiceLocator.getService(QuestionnaireAnswerService.class);
    }
    
    @Override
    protected String getAuditErrorLink() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Constants.PROTOCOL_QUESTIONNAIRE_PAGE);
        stringBuilder.append(".");
        stringBuilder.append(Constants.PROTOCOL_QUESTIONNAIRE_PANEL_ANCHOR);
        return stringBuilder.toString();
    }

    @Override
    protected String getAuditErrorsLabel() {
        // TODO Auto-generated method stub
        return null;
    }
    
    public boolean isRequestSubmission() {
        return requestSubmission;
    }
    
    public void setRequestSubmittion(boolean requestSubmission) {
        this.requestSubmission = requestSubmission;
    }
}
