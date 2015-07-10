/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.protocol.questionnaire;

import org.kuali.coeus.common.framework.module.CoeusSubModule;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.coeus.common.questionnaire.framework.core.BaseQuestionnaireAuditRule;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireUsage;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean;
import org.kuali.coeus.common.questionnaire.framework.answer.QuestionnaireAnswerService;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

import java.util.ArrayList;
import java.util.List;

public abstract class ProtocolQuestionnaireAuditRuleBase extends BaseQuestionnaireAuditRule<ProtocolDocumentBase> implements DocumentAuditRule {
    
    //private static final String MANDATORY_QUESTIONNAIRE_AUDIT_ERRORS = "questionnaireHelper%s%s";
    
    private static final String PROTOCOL_QUESTIONNAIRE_KEY="questionnaireHelper.answerHeaders[%s].answers[0].answer";
    private static final String PROTOCOL_QUESTIONNAIRE_PANEL_KEY="%s%s%s";
    
    private boolean requestSubmission;
    
    public boolean processRunAuditBusinessRules(Document document) {
        ProtocolBase protocol = ((ProtocolDocumentBase)document).getProtocol();
        
        boolean isValid = true;
        ProtocolModuleQuestionnaireBeanBase pmqb = getProtocolModuleQuestionnaireBean(protocol);
        List<AnswerHeader> headers = getQuestionnaireAnswerService().getQuestionnaireAnswer(pmqb);
        
        if (headers!=null) {
            for (int i=0;i<headers.size();i++) {
                AnswerHeader header = headers.get(i);
                QuestionnaireUsage usage = header.getQuestionnaire().getHighestVersionUsageFor(pmqb.getModuleItemCode(), pmqb.getModuleSubItemCode());
                if ( (usage != null) && (usage.isMandatory()) && (!header.isCompleted()) && (header.isActiveQuestionnaire()) ) {
                    isValid = false;
                    addMandatoryQuestionnaireErrorToAuditErrors(i, usage);
                }
                // now check for whether updates if any were required, were actually performed
                // TODO: not sure if we really need to check if questionnaire is active, that should've been taken care of 
                // by the service that sets the new version published flag
                if(header.isNewerVersionPublished() && (header.isActiveQuestionnaire()) ) {
                    isValid = false;
                    addQuestionnaireNotUpdatedErrorToAuditErrors(i, usage);
                }
            }
        }
        
        // extra validation to check if any default usage-only qnnrs are made incomplete in an questionnaire amendment submission
        if( !(protocol.isNew()) && (protocol.getProtocolAmendRenewal().hasModule(getQuestionnaireModuleCodeHook()))) {
            // create a module bean with the amendment protocol number but with default sub module to retrieve answer headers
            pmqb = getProtocolModuleQuestionnaireBean(getModuleCodeHook(), protocol.getProtocolNumber(), "0", protocol.getSequenceNumber().toString(), 
                protocol.getProtocolDocument().getDocumentHeader().getWorkflowDocument().isApproved());
            List<AnswerHeader> defAmendHeaders = getQuestionnaireAnswerService().getQuestionnaireAnswer(pmqb);           
            // now check that each 'mandatory' (default) header is complete, signaling an error otherwise.
            for (int i = 0; i < defAmendHeaders.size(); i++) {
                AnswerHeader header = defAmendHeaders.get(i);
                QuestionnaireUsage usage = header.getQuestionnaire().getHighestVersionUsageFor(getModuleCodeHook(),
                        CoeusSubModule.ZERO_SUBMODULE);
                if ((usage != null) && (usage.isMandatory()) && !(header.isCompleted()) && (header.isActiveQuestionnaire())) {
                    isValid = false;
                    addMandatoryQuestionnaireErrorToAuditErrors((i + headers.size()), usage);
                }
                // now check for whether updates if any were required, were actually performed
                // TODO: not sure if we really need to check if questionnaire is active, that should've been taken care of
                // by the service that sets the new version published flag
                if (header.isNewerVersionPublished() && (header.isActiveQuestionnaire())) {
                    isValid = false;
                    addQuestionnaireNotUpdatedErrorToAuditErrors((i + headers.size()), usage);
                }

            }
        }
        
        return isValid;            
    }
    
    protected List<Integer> getIncompleteMandatoryQuestionnaire(ProtocolDocumentBase protocolDocument) {
        ProtocolBase protocol = protocolDocument.getProtocol();
        ModuleQuestionnaireBean moduleQuestionnaireBean = getProtocolModuleQuestionnaireBean(protocol);
        if (isRequestSubmission()) {
            moduleQuestionnaireBean.setModuleSubItemCode(CoeusSubModule.PROTOCOL_SUBMISSION);
        }
        return super.getIncompleteMandatoryQuestionnaire(getModuleCodeHook(), moduleQuestionnaireBean);
    }    
    
    protected void addQuestionnaireNotUpdatedErrorToAuditErrors(Integer answerHeaderIndex, QuestionnaireUsage usage) {
        String errorKey = String.format(PROTOCOL_QUESTIONNAIRE_KEY, answerHeaderIndex);
        String messageKey = KeyConstants.ERROR_QUESTIONNAIRE_NOT_UPDATED;
        getProtocolAuditErrors("questionnaireHelper", usage.getQuestionnaireLabel(), answerHeaderIndex).add(new AuditError(errorKey, messageKey, getAuditErrorLink()));
    }
    
    protected void addMandatoryQuestionnaireErrorToAuditErrors(Integer answerHeaderIndex, QuestionnaireUsage usage) {
        String errorKey = String.format(PROTOCOL_QUESTIONNAIRE_KEY, answerHeaderIndex);
        String messageKey = KeyConstants.ERROR_MANDATORY_QUESTIONNAIRE;
        addErrorToAuditErrors(answerHeaderIndex, usage, errorKey, messageKey);
    }
    
    /**
     * Creates and adds the Audit Error to the <code>{@link List&lt;AuditError&gt;}</code> auditError.
     */
    protected void addErrorToAuditErrors(Integer answerHeaderIndex, QuestionnaireUsage usage, String errorKey, String messageKey) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Constants.PROTOCOL_QUESTIONNAIRE_PAGE);
        stringBuilder.append(".");
        stringBuilder.append(Constants.PROTOCOL_QUESTIONNAIRE_PANEL_ANCHOR);
        
        getProtocolAuditErrors("questionnaireHelper", usage.getQuestionnaireLabel(), answerHeaderIndex).add(new AuditError(errorKey, messageKey, stringBuilder.toString()));
        
    }
    
    /**
     * This method should only be called if an audit error is intending to be added because it will actually add a <code>{@link List&lt;AuditError&gt;}</code>
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
            if (getQuestionnaireUsage(getModuleCodeHook(), CoeusSubModule.PROTOCOL_SUBMISSION, answerHeader.getQuestionnaire().getQuestionnaireUsages()).isMandatory() 
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
        return KcServiceLocator.getService(QuestionnaireAnswerService.class);
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

        return null;
    }
    
    public boolean isRequestSubmission() {
        return requestSubmission;
    }
    
    // TODO this setter seems to be named wrong, fix after checking references
    public void setRequestSubmittion(boolean requestSubmission) {
        this.requestSubmission = requestSubmission;
    }
    
    protected abstract String getModuleCodeHook();

    protected abstract ProtocolModuleQuestionnaireBeanBase getProtocolModuleQuestionnaireBean(ProtocolBase protocol);

    protected abstract ProtocolModuleQuestionnaireBeanBase getProtocolModuleQuestionnaireBean(String moduleItemCode, String moduleItemKey, String moduleSubItemCode, String moduleSubItemKey, boolean finalDoc);

    protected abstract String getQuestionnaireModuleCodeHook();

}
