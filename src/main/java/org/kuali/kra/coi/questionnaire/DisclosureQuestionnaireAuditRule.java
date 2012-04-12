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
package org.kuali.kra.coi.questionnaire;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.bo.CoeusSubModule;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiDisclosureDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.questionnaire.BaseQuestionnaireAuditRule;
import org.kuali.kra.questionnaire.QuestionnaireUsage;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean;
import org.kuali.kra.questionnaire.answer.QuestionnaireAnswerService;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

public class DisclosureQuestionnaireAuditRule extends BaseQuestionnaireAuditRule<CoiDisclosureDocument> implements DocumentAuditRule {

    private static final String DISCLOSURE_QUESTIONNAIRE_KEY = "questionnaireHelper.answerHeaders[%s].answers[0].answer";
    private static final String DISCLOSURE_QUESTIONNAIRE_PANEL_KEY = "%s%s%s";

    private boolean requestSubmission;

    public boolean processRunAuditBusinessRules(Document document) {
        CoiDisclosure coiDisclosure = ((CoiDisclosureDocument) document).getCoiDisclosure();  

        boolean isValid = true;
        DisclosureModuleQuestionnaireBean disclosureModuleQuestionnaireBean = new DisclosureModuleQuestionnaireBean(coiDisclosure);
        List<AnswerHeader> headers = getQuestionnaireAnswerService().getQuestionnaireAnswer(disclosureModuleQuestionnaireBean);

        if (headers != null) {
            for (int i = 0;i < headers.size();i++) {
                AnswerHeader header = headers.get(i);
                QuestionnaireUsage usage = header.getQuestionnaire().getHighestVersionUsageFor(disclosureModuleQuestionnaireBean.getModuleItemCode(), disclosureModuleQuestionnaireBean.getModuleSubItemCode());
                if ((usage != null) && (usage.isMandatory()) && (!header.getCompleted()) && (header.isActiveQuestionnaire())) {
                    isValid = false;
                    addMandatoryQuestionnaireErrorToAuditErrors(i, usage);
                }
                if (header.isNewerVersionPublished() && (header.isActiveQuestionnaire())) {
                    isValid = false;
                    addQuestionnaireNotUpdatedErrorToAuditErrors(i, usage);
                }
            }
        }
       
        return isValid;    
    }
    
    
    protected List<Integer> getIncompleteMandatoryQuestionnaire(CoiDisclosureDocument coiDisclosureDocument) {
        CoiDisclosure coiDisclosure = coiDisclosureDocument.getCoiDisclosure();
        ModuleQuestionnaireBean moduleQuestionnaireBean = new DisclosureModuleQuestionnaireBean(coiDisclosure);
        if (isRequestSubmission()) {
            moduleQuestionnaireBean.setModuleSubItemCode(CoeusSubModule.COI_ANNUAL_DISCL_SUBMODULE);
        }
        return super.getIncompleteMandatoryQuestionnaire(CoeusModule.COI_DISCLOSURE_MODULE_CODE, moduleQuestionnaireBean);
    }

    protected void addQuestionnaireNotUpdatedErrorToAuditErrors(Integer answerHeaderIndex, QuestionnaireUsage usage) {
        String errorKey = String.format(DISCLOSURE_QUESTIONNAIRE_KEY, answerHeaderIndex);
        String messageKey = KeyConstants.ERROR_QUESTIONNAIRE_NOT_UPDATED;
        getCoiDisclosureAuditErrors("questionnaireHelper", usage.getQuestionnaireLabel(), answerHeaderIndex).add(new AuditError(errorKey, messageKey, getAuditErrorLink()));
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

    protected void addMandatoryQuestionnaireErrorToAuditErrors(Integer answerHeaderIndex, QuestionnaireUsage usage) {
        String errorKey = String.format(DISCLOSURE_QUESTIONNAIRE_KEY, answerHeaderIndex);
        String messageKey = KeyConstants.ERROR_MANDATORY_QUESTIONNAIRE;
        addErrorToAuditErrors(answerHeaderIndex, usage, errorKey, messageKey);
    }

    /**
     * Creates and adds the Audit Error to the <code>{@link List<AuditError>}</code> auditError.
     */
   protected void addErrorToAuditErrors(Integer answerHeaderIndex, QuestionnaireUsage usage, String errorKey, String messageKey) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Constants.COI_DISCLOSURE_DISCLOSURE_PAGE);
        stringBuilder.append(".");
        stringBuilder.append(Constants.COI_DISCLOSURE_DISCLOSURE_PANEL_NAME);

        getCoiDisclosureAuditErrors("questionnaireHelper", usage.getQuestionnaireLabel(), answerHeaderIndex).add(new AuditError(errorKey, messageKey, stringBuilder.toString()));

    }


    /**
     * This method should only be called if an audit error is intending to be added because it will actually add a <code>{@link List<AuditError>}</code>
     * to the auditErrorMap.
     * 
     * @return List of AuditError instances
     */
    @SuppressWarnings("unchecked")
    private List<AuditError> getCoiDisclosureAuditErrors(String formProperty, String usageLabel, Integer answerHeaderIndex) {
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        String key = String.format(DISCLOSURE_QUESTIONNAIRE_PANEL_KEY, formProperty, usageLabel, answerHeaderIndex);

        if (!KNSGlobalVariables.getAuditErrorMap().containsKey(key)) {
            KNSGlobalVariables.getAuditErrorMap().put(key, new AuditCluster(usageLabel, auditErrors, Constants.AUDIT_ERRORS));
        }
        else {
            auditErrors = ((AuditCluster)KNSGlobalVariables.getAuditErrorMap().get(key)).getAuditErrorList();
        }

        return auditErrors;
    }
    public boolean isMandatorySubmissionQuestionnaireComplete(List<AnswerHeader> answerHeaders) {
        boolean isValid = true;
        for (AnswerHeader answerHeader : answerHeaders) {
            if (getQuestionnaireUsage(CoeusModule.COI_DISCLOSURE_MODULE_CODE, CoeusSubModule.COI_ANNUAL_DISCL_SUBMODULE, answerHeader.getQuestionnaire().getQuestionnaireUsages()).isMandatory() 
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
        stringBuilder.append(Constants.COI_DISCLOSURE_DISCLOSURE_PAGE);
        stringBuilder.append(".");
        stringBuilder.append(Constants.COI_DISCLOSURE_DISCLOSURE_PANEL_NAME);
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

    // TODO this setter seems to be named wrong, fix after checking references
    public void setRequestSubmittion(boolean requestSubmission) {
        this.requestSubmission = requestSubmission;
    }
    
}
