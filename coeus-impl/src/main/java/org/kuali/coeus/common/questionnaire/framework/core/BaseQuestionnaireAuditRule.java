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
package org.kuali.coeus.common.questionnaire.framework.core;

import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean;
import org.kuali.coeus.common.questionnaire.framework.answer.QuestionnaireAnswerService;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseQuestionnaireAuditRule<T extends KcTransactionalDocumentBase> extends KcTransactionalDocumentRuleBase implements DocumentAuditRule {

    private static final String MANDATORY_QUESTIONNAIRE_AUDIT_ERRORS = "mandatoryQuestionnaireAuditErrors";
    private List<AuditError> auditErrors;
    
    
    protected abstract List<Integer> getIncompleteMandatoryQuestionnaire(T document);
    
    public boolean processRunAuditBusinessRules(Document document) {
        @SuppressWarnings("unchecked")
        T inDocument = (T)document;
        auditErrors = new ArrayList<AuditError>();
        List<Integer> headerIds = getIncompleteMandatoryQuestionnaire(inDocument);
        if (!headerIds.isEmpty()) {
            addErrorToAuditErrors(headerIds);
        }
        reportAndCreateAuditCluster(getAuditErrorsLabel());
        
        return headerIds.isEmpty();
    }
    
    protected List<Integer> getIncompleteMandatoryQuestionnaire(String ModuleCode, ModuleQuestionnaireBean moduleQuestionnaireBean) {
        List<Integer> headers = new ArrayList<Integer>();
        int i = 0;
        for (AnswerHeader answerHeader : getQuestionnaireAnswerService().getQuestionnaireAnswer(moduleQuestionnaireBean)) {
            if (getQuestionnaireUsage(ModuleCode, moduleQuestionnaireBean.getModuleSubItemCode(), answerHeader.getQuestionnaire().getQuestionnaireUsages()).isMandatory() && !answerHeader.isCompleted()) {
                headers.add(i);
            }
            i++;
        }
        return headers;
    }
    
    protected abstract String getAuditErrorLink();
    protected abstract String getAuditErrorsLabel();
    
    
    protected QuestionnaireUsage getQuestionnaireUsage(String moduleItemCode, String moduleSubItemCode, List<QuestionnaireUsage> questionnaireUsages) {
        QuestionnaireUsage usage = null;
        int version = 0;
        for (QuestionnaireUsage questionnaireUsage : questionnaireUsages) {
           if (moduleItemCode.equals(questionnaireUsage.getModuleItemCode()) && moduleSubItemCode.equals(questionnaireUsage.getModuleSubItemCode()) && questionnaireUsage.getQuestionnaireSequenceNumber() > version) {
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
        for (Integer id : headerIds) {
            auditErrors.add(new AuditError("questionnaireHelper.answerHeaders[" + id + "].answers[0].answer",
                KeyConstants.ERROR_MANDATORY_QUESTIONNAIRE, getAuditErrorLink()));
        }
    }
    
    
    /**
     * Creates and adds the AuditCluster to the Global AuditErrorMap.
     */
    @SuppressWarnings("unchecked")
    protected void reportAndCreateAuditCluster(String label) {
        if (auditErrors.size() > 0) {
            KNSGlobalVariables.getAuditErrorMap().put(MANDATORY_QUESTIONNAIRE_AUDIT_ERRORS, 
                    new AuditCluster(label, auditErrors, Constants.AUDIT_ERRORS));
        }
    }
  
    protected QuestionnaireAnswerService getQuestionnaireAnswerService() {
        return KcServiceLocator.getService(QuestionnaireAnswerService.class);
    }

}
