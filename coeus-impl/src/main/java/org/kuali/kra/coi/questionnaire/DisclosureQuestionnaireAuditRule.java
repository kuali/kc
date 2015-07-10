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
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.util.GlobalVariables;
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
     * This method should only be called if an audit error is intending to be added because it will actually add a <code>{@link List&lt;AuditError&gt;}</code>
     * to the auditErrorMap.
     * 
     * @return List of AuditError instances
     */
    @SuppressWarnings("unchecked")
    private List<AuditError> getCoiDisclosureAuditErrors() {
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        String key = DISCLOSURE_QUESTIONNAIRE_PANEL_KEY;

        if (!GlobalVariables.getAuditErrorMap().containsKey(key)) {
            GlobalVariables.getAuditErrorMap().put(key, new AuditCluster("Questionnaire", auditErrors, Constants.AUDIT_ERRORS));
        }
        else {
            auditErrors = ((AuditCluster)GlobalVariables.getAuditErrorMap().get(key)).getAuditErrorList();
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
