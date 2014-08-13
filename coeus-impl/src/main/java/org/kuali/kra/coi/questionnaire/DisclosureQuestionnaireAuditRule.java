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
package org.kuali.kra.coi.questionnaire;

import org.apache.commons.lang3.ObjectUtils;
import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.common.framework.module.CoeusSubModule;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.coi.CoiDisclProject;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiDisclosureDocument;
import org.kuali.kra.coi.disclosure.CoiDisclosureProjectBean;
import org.kuali.kra.coi.disclosure.MasterDisclosureBean;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.common.questionnaire.framework.core.BaseQuestionnaireAuditRule;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireUsage;
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

public class DisclosureQuestionnaireAuditRule extends BaseQuestionnaireAuditRule<CoiDisclosureDocument> implements DocumentAuditRule {

    private static final String DISCLOSURE_QUESTIONNAIRE_KEY = "disclosureQuestionnaireHelper.answerHeaders";
    private static final String SCREENING_QUESTIONNAIRE_KEY = "screeningQuestionnaireHelper.answerHeaders";
    private static final String DISCLOSURE_QUESTIONNAIRE_PANEL_KEY = "coiQuestionnaireKey";
    protected static final String MASTER_DISCLOSURE_PROJECT_QUESTIONNAIRE_KEY = "disclosureHelper.masterDisclosureBean.%s[%s].projectQuestionnaireHelper.answerHeaders";
    protected static final String AUDIT_ERROR_LABEL;
    static {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Constants.COI_DISCLOSURE_DISCLOSURE_PAGE);
        stringBuilder.append(".");
        stringBuilder.append(Constants.COI_DISCLOSURE_DISCLOSURE_PANEL_NAME);
        AUDIT_ERROR_LABEL = stringBuilder.toString();
    }

    private boolean requestSubmission;

    public boolean processRunAuditBusinessRules(Document document) {
        CoiDisclosure coiDisclosure = ((CoiDisclosureDocument) document).getCoiDisclosure();  

        boolean isValid = true;
        DisclosureModuleQuestionnaireBean disclosureModuleQuestionnaireBean = new DisclosureModuleQuestionnaireBean(coiDisclosure);
        List<AnswerHeader> headers = getQuestionnaireAnswerService().getQuestionnaireAnswer(disclosureModuleQuestionnaireBean);

        if (headers != null) {
            isValid &= checkAnswerHeaders(headers, DISCLOSURE_QUESTIONNAIRE_KEY);
        }
        
        headers = getQuestionnaireAnswerService().getQuestionnaireAnswer(new DisclosureModuleQuestionnaireBean(coiDisclosure, CoeusSubModule.COI_SCREENING_SUBMODULE));
        if (headers != null) {
            isValid &= checkAnswerHeaders(headers, SCREENING_QUESTIONNAIRE_KEY);
        }
        
        MasterDisclosureBean masterBean = new MasterDisclosureBean();
        
        for (CoiDisclProject project : coiDisclosure.getCoiDisclProjects()) {
            //if this project is on this disclosure then the questionnaire will always be blank because
            //the questionnaire is on the disclosure, not the project, but if this project is not from this
            //disclosure then this is likely an annual or update and the questionnaire will be attached
            //to the project and will need to be validated.
            if (project.getOriginalCoiDisclosureId() != null && !ObjectUtils.equals(project.getOriginalCoiDisclosureId(), coiDisclosure.getCoiDisclosureId())) {            
                CoiDisclosureProjectBean projectBean = new CoiDisclosureProjectBean();
                projectBean.setCoiDisclProject(project);
                projectBean.populateAnswers(coiDisclosure);
                masterBean.addProject(projectBean, projectBean.getCoiDisclProject().getDisclosureEventType());
            }
        }
        
        isValid &= checkProjects(masterBean.getManualAwardProjects(), "manualAwardProjects");
        isValid &= checkProjects(masterBean.getManualIacucProtocolProjects(), "manualIacucProtocolProjects");
        isValid &= checkProjects(masterBean.getManualProposalProjects(), "manualProposalProjects");
        isValid &= checkProjects(masterBean.getManualProtocolProjects(), "manualProtocolProjects");
        isValid &= checkProjects(masterBean.getManualTravelProjects(), "manualTravelProjects");
        isValid &= checkProjects(masterBean.getOtherManualProjects(), "otherManualProjects");
        isValid &= checkProjects(masterBean.getAwardProjects(), "awardProjects");
        isValid &= checkProjects(masterBean.getProposalProjects(), "proposalProjects");
        isValid &= checkProjects(masterBean.getProtocolProjects(), "protocolProjects");

        return isValid;    
    }
    
    protected boolean checkProjects(List<CoiDisclosureProjectBean> projects, String listName) {
        boolean result = true;
        for (int i = 0; i < projects.size(); i++) {
            CoiDisclosureProjectBean projectBean = projects.get(i);
            result &= checkAnswerHeaders(projectBean.getProjectQuestionnaireHelper().getAnswerHeaders(), String.format(MASTER_DISCLOSURE_PROJECT_QUESTIONNAIRE_KEY, listName, i));
        }
        return result;
    }

    protected boolean checkAnswerHeaders(List<AnswerHeader> answerHeaders, String messageKey) {
        boolean isValid = true;
        for (int i = 0;i < answerHeaders.size();i++) {
            AnswerHeader header = answerHeaders.get(i);
            QuestionnaireUsage usage = header.getQuestionnaire().getHighestVersionUsageFor(header.getModuleItemCode(), header.getModuleSubItemCode());
            if ((usage != null) && (usage.isMandatory()) && (!header.isCompleted()) && (header.isActiveQuestionnaire())) {
                isValid = false;
                addAuditError(messageKey + "[" + i + "]", KeyConstants.ERROR_COI_QUESTIONNAIRE_MANDATORY, usage.getQuestionnaireLabel());
            }
            if (header.isNewerVersionPublished() && (header.isActiveQuestionnaire())) {
                isValid = false;
                addAuditError(messageKey + "[" + i + "]", KeyConstants.ERROR_COI_QUESTIONNAIRE_NOTUPDATED, usage.getQuestionnaireLabel());
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

    protected void addAuditError(String errorKey, String messageKey, String label) {
        getCoiDisclosureAuditErrors().add(new AuditError(errorKey, messageKey, AUDIT_ERROR_LABEL, new String[]{label}));
    }

    /**
     * This method should only be called if an audit error is intending to be added because it will actually add a <code>{@link List<AuditError>}</code>
     * to the auditErrorMap.
     * 
     * @return List of AuditError instances
     */
    @SuppressWarnings("unchecked")
    private List<AuditError> getCoiDisclosureAuditErrors() {
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        String key = DISCLOSURE_QUESTIONNAIRE_PANEL_KEY;

        if (!KNSGlobalVariables.getAuditErrorMap().containsKey(key)) {
            KNSGlobalVariables.getAuditErrorMap().put(key, new AuditCluster("Questionnaire", auditErrors, Constants.AUDIT_ERRORS));
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
        return KcServiceLocator.getService(QuestionnaireAnswerService.class);
    }

    @Override
    protected String getAuditErrorLink() {
        return AUDIT_ERROR_LABEL;
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
    
}
