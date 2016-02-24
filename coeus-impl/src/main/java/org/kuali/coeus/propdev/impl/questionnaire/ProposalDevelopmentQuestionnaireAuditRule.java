/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
