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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireUsage;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.answer.QuestionnaireAnswerService;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

import java.util.ArrayList;
import java.util.List;

import static org.kuali.coeus.propdev.impl.datavalidation.ProposalDevelopmentDataValidationConstants.*;

public class ProposalDevelopmentQuestionnaireAuditRule extends KcTransactionalDocumentRuleBase implements DocumentAuditRule {

    private transient QuestionnaireAnswerService questionnaireAnswerService;
    private transient GlobalVariableService globalVariableService;

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

    public boolean processRunAuditBusinessRules(Document document) {
        
        boolean valid = true;
        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)document;
        ModuleQuestionnaireBean moduleQuestionnaireBean = new ProposalDevelopmentModuleQuestionnaireBean(proposalDevelopmentDocument.getDevelopmentProposal());
        List<AnswerHeader> headers = getQuestionnaireAnswerService().getQuestionnaireAnswer(moduleQuestionnaireBean);
        for (AnswerHeader answerHeader : headers) {
            QuestionnaireUsage usage = getQuestionnaireUsage(moduleQuestionnaireBean.getModuleItemCode(), moduleQuestionnaireBean.getModuleSubItemCode(), answerHeader.getQuestionnaire().getQuestionnaireUsages());

            if (!answerHeader.isCompleted() && usage.isMandatory()) {
                valid = false;
                getAuditErrors(answerHeader.getLabel()).add(
                        new AuditError(QUESTIONNAIRE_PAGE_ID + "-" + StringUtils.removePattern(answerHeader.getLabel(),"([^0-9a-zA-Z\\-_])"), KeyConstants.ERROR_QUESTIONNAIRE_NOT_COMPLETE,
                                QUESTIONNAIRE_PAGE_ID + "." + QUESTIONNAIRE_PAGE_ID + "-" + StringUtils.removePattern(answerHeader.getLabel(),"([^0-9a-zA-Z\\-_])"), new String[]{answerHeader.getLabel()}));
            }
        }
        return valid;
    }

    public QuestionnaireAnswerService getQuestionnaireAnswerService() {
        if (questionnaireAnswerService == null) {
            questionnaireAnswerService = KcServiceLocator.getService(QuestionnaireAnswerService.class);
        }
        return questionnaireAnswerService;
    }

    public GlobalVariableService getGlobalVariableService() {
        if (globalVariableService == null) {
            globalVariableService = KcServiceLocator.getService(GlobalVariableService.class);
        }
        return globalVariableService;
    }

    private List<AuditError> getAuditErrors(String sectionName) {
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        String clusterKey = QUESTIONNAIRE_PAGE_NAME + "." + sectionName;
        if (!getGlobalVariableService().getAuditErrorMap().containsKey(clusterKey)) {
            getGlobalVariableService().getAuditErrorMap().put(clusterKey, new AuditCluster(clusterKey, auditErrors, AUDIT_ERRORS));
        }
        else {
            auditErrors = getGlobalVariableService().getAuditErrorMap().get(clusterKey).getAuditErrorList();
        }
        return auditErrors;
    }
    
    

}
