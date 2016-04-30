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
package org.kuali.kra.protocol.actions;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.meeting.CommitteeScheduleMinuteBase;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.ProtocolFormBase;
import org.kuali.kra.protocol.actions.correspondence.ProtocolActionCorrespondenceGenerationService;
import org.kuali.kra.protocol.actions.correspondence.ProtocolActionsCorrespondenceBase;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsBeanBase;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsService;
import org.kuali.kra.protocol.auth.ProtocolTaskBase;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondence;
import org.kuali.kra.protocol.notification.ProtocolNotification;
import org.kuali.kra.protocol.notification.ProtocolNotificationContextBase;
import org.kuali.kra.protocol.notification.ProtocolNotificationRequestBeanBase;
import org.kuali.kra.protocol.onlinereview.ProtocolReviewAttachmentBase;
import org.kuali.kra.protocol.questionnaire.ProtocolQuestionnaireAuditRuleBase;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean;
import org.kuali.coeus.common.questionnaire.framework.answer.QuestionnaireAnswerService;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
import org.kuali.rice.krad.rules.rule.event.DocumentEvent;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ProtocolActionRequestServiceImpl implements ProtocolActionRequestService {

    private KualiRuleService kualiRuleService;
    private TaskAuthorizationService taskAuthorizationService;
    private BusinessObjectService businessObjectService;
    private ProtocolActionCorrespondenceGenerationService protocolActionCorrespondenceGenerationService;
    private KcNotificationService notificationService;
    private PersonService personService;
    private ReviewCommentsService<? extends ProtocolReviewAttachmentBase> reviewCommentsService;
    private QuestionnaireAnswerService questionnaireAnswerService;

    private static final String FORWARD_TO_CORRESPONDENCE = "correspondence";
    protected static final String RETURN_TO_HOLDING_PAGE = "holdingPage";
    
    protected static final String ACTION_NAME_AMENDMENT = "Create Amendment";
    protected static final String ACTION_NAME_RENEWAL_WITHOUT_AMENDMENT = "Create Renewal without Amendment";
    protected static final String ACTION_NAME_RENEWAL_WITH_AMENDMENT = "Create Renewal with Amendment";
    protected static final String ACTION_NAME_FYI = "Create FYI";
    protected static final String ACTION_NAME_ASSIGN_TO_AGENDA = "Assign to Agenda";
    protected static final String ACTION_NAME_REVIEW_NOT_REQUIRED = "Review Not Required";
    protected static final String ACTION_NAME_DISAPPROVE = "Disapprove";
    protected static final String ACTION_NAME_EXPIRE = "Expire";
    protected static final String ACTION_NAME_TERMINATE = "Terminate";
    protected static final String ACTION_NAME_SUSPEND = "Suspend";
    protected static final String ACTION_NAME_SMR = "Return for Specific Minor Revisions";
    protected static final String ACTION_NAME_SRR = "Return for Substantive Revisions Required";
    protected static final String ACTION_NAME_RETURN_TO_PI = "Return To PI";
    protected static final String ACTION_NAME_MANAGE_ADMINISTRATIVE_CORRECTION = "Make Administrative Correction";
    protected static final String ACTION_NAME_RECORD_ABANDON = "Abandon";
    protected static final String ACTION_NAME_WITHDRAW = "Withdraw";
    protected static final String ACTION_NAME_RECORD_COMMITTEE_DECISION = "Record Committee Decision";

    protected final boolean applyRules(DocumentEvent event) {
        return getKualiRuleService().applyRules(event);
    }

    public KualiRuleService getKualiRuleService() {
        return kualiRuleService;
    }

    public void setKualiRuleService(KualiRuleService kualiRuleService) {
        this.kualiRuleService = kualiRuleService;
    }

    public TaskAuthorizationService getTaskAuthorizationService() {
        return taskAuthorizationService;
    }

    public void setTaskAuthorizationService(TaskAuthorizationService taskAuthorizationService) {
        this.taskAuthorizationService = taskAuthorizationService;
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * This method is to verify whether current user authorized to perform the given task
     * @param taskName
     * @param protocol
     * @return
     */
    protected boolean hasPermission(String taskName, ProtocolBase protocol) {
        ProtocolTaskBase task = getProtocolTaskInstanceHook(taskName, protocol);
        return getTaskAuthorizationService().isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), task);
    }
    
    /**
     * This method is to verify whether current user authorized to perform the generic action task
     * @param genericActionName
     * @param protocol
     * @return
     */
    protected boolean hasGenericPermission(String genericActionName, ProtocolBase protocol) {
        ProtocolTaskBase task = getProtocolGenericActionTaskInstanceHook(genericActionName, protocol);
        return getTaskAuthorizationService().isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), task);
    }
    
    /**
     * This method is to generate correspondence for every action performed
     * Check whether trigger correspondence is enabled.
     * @param protocolActionTypeCode
     * @param protocol
     * @throws Exception
     */
    protected void generateActionCorrespondence(String protocolActionTypeCode, ProtocolBase protocol) throws Exception {
        if(isCorrespondenceRequired(protocolActionTypeCode)) {
            ProtocolActionsCorrespondenceBase correspondence = getNewProtocolActionsCorrespondence(protocolActionTypeCode);
            correspondence.setPrintableBusinessObject(protocol);
            correspondence.setProtocol(protocol);
            getProtocolActionCorrespondenceGenerationService().generateCorrespondenceDocumentAndAttach(correspondence);
        }
    }

    protected void updateDocumentStatusChangedMessage() {
        GlobalVariables.getMessageMap().clearErrorMessages();
        GlobalVariables.getMessageMap().putError("documentstatechanged", KeyConstants.ERROR_PROTOCOL_DOCUMENT_STATE_CHANGED,  new String[] {}); 
    }
    
    private boolean isCorrespondenceRequired(String protocolActionTypeCode) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("protocolActionTypeCode", protocolActionTypeCode);
        ProtocolActionTypeBase protocolActionType = (ProtocolActionTypeBase) getBusinessObjectService().findByPrimaryKey(getProtocolActionTypeBOClassHook(), fieldValues);
        return protocolActionType.getTriggerCorrespondence();
    }

    public void createProtocol(ProtocolFormBase protocolForm) throws Exception {
        generateActionCorrespondence(getProtocolCreatedActionTypeHook(), protocolForm.getProtocolDocument().getProtocol());
    }

    public void rejectedInRouting(ProtocolBase protocol) throws Exception {
        generateActionCorrespondence(getProtocolRejectedInRoutingActionTypeHook(), protocol);
    }

    public void recalledInRouting(ProtocolBase protocol) throws Exception {
        generateActionCorrespondence(getProtocolRecalledInRoutingActionTypeHook(), protocol);
    }
    
    protected void recordProtocolActionSuccess(String protocolActionName) {
        KNSGlobalVariables.getMessageList().add(KeyConstants.MESSAGE_PROTOCOL_ACTION_SUCCESSFULLY_COMPLETED, protocolActionName);
    }
    
    protected void saveReviewComments(ProtocolFormBase protocolForm, ReviewCommentsBeanBase actionBean) throws Exception { 
    	getReviewCommentsService().updateScheduleForReviewComments(protocolForm.getProtocolDocument().getProtocol(), actionBean.getReviewComments());
        getReviewCommentsService().saveReviewComments(actionBean.getReviewComments(), actionBean.getDeletedReviewComments());           
        actionBean.setDeletedReviewComments(new ArrayList<CommitteeScheduleMinuteBase>());
        protocolForm.getActionHelper().prepareCommentsView();
    }
    
    @Override
    public boolean hasDocumentStateChanged(ProtocolFormBase protocolForm) {
        boolean result = false;
        Map<String,Object> primaryKeys = new HashMap<String, Object>();
        primaryKeys.put("protocolId", protocolForm.getProtocolDocument().getProtocol().getProtocolId());
        ProtocolBase dbProtocol = (ProtocolBase)getBusinessObjectService().findByPrimaryKey(getProtocolBOClassHook(), primaryKeys);
        
        //First lets check the protocol status & submission status
        if (dbProtocol != null) {
            if (!StringUtils.equals(dbProtocol.getProtocolStatusCode(), 
                    protocolForm.getProtocolDocument().getProtocol().getProtocolStatusCode())) {
                result = true;
            }
            if (dbProtocol.getProtocolSubmission() != null && 
                    protocolForm.getProtocolDocument().getProtocol().getProtocolSubmission().getSubmissionStatusCode() != null) {
                if (!StringUtils.equals(dbProtocol.getProtocolSubmission().getSubmissionStatusCode(), 
                        protocolForm.getProtocolDocument().getProtocol().getProtocolSubmission().getSubmissionStatusCode())) {
                    result = true;
                }
            }
        }
        
        //If no changes in the protocol, lets check the document for workflow changes
        if (!result) {
           result = !isDocumentPostprocessingComplete(protocolForm.getProtocolDocument());
        }
        return result;
    }
    
    private boolean isDocumentPostprocessingComplete(ProtocolDocumentBase document) {
        return document.getDocumentHeader().hasWorkflowDocument() && !isPessimisticallyLocked(document);
    }
    
    private boolean isPessimisticallyLocked(Document document) {
        boolean isPessimisticallyLocked = false;
        
        Person pessimisticLockHolder = getPersonService().getPersonByPrincipalName(KewApiConstants.SYSTEM_USER);
        for (PessimisticLock pessimisticLock : document.getPessimisticLocks()) {
            if (pessimisticLock.isOwnedByUser(pessimisticLockHolder)) {
                isPessimisticallyLocked = true;
                break;
            }
        }
        return isPessimisticallyLocked;
    }
    
    /**
     * This method is to set the parameters required for the new document after performing
     * an action
     * @param protocolForm
     * @param newDocId
     * @param protocolActionName
     */
    @SuppressWarnings("deprecation")
    protected void refreshAfterProtocolAction(ProtocolFormBase protocolForm, String newDocId, String protocolActionName, boolean resetCurrentSubmission) {
        protocolForm.setDocId(newDocId);
        if(resetCurrentSubmission) {
            protocolForm.getActionHelper().setCurrentSubmissionNumber(-1);
        }
        recordProtocolActionSuccess(protocolActionName);
    }
    
    /**
     * This method is to get the path where user is send to performing protocol action
     * @param protocolForm
     * @param notificationBean
     * @return
     */
    protected String getRedirectPathAfterProtocolAction(ProtocolFormBase protocolForm, ProtocolNotificationRequestBeanBase notificationBean, String promptAfterNotification) {
        if (protocolForm.getActionHelper().getProtocolCorrespondence() != null) {
            return FORWARD_TO_CORRESPONDENCE;
        } else {
            boolean sendNotification = checkToSendNotification(protocolForm, notificationBean, promptAfterNotification); 
            return sendNotification ? getNotificationEditorHook() : promptAfterNotification;
        }
    }
    
    public boolean checkToSendNotification(ProtocolFormBase protocolForm, ProtocolNotificationRequestBeanBase notificationRequestBean, String promptAfterNotification) {
        ProtocolBase protocol = protocolForm.getProtocolDocument().getProtocol();
        ProtocolNotificationContextBase context = getProtocolNotificationContextHook(notificationRequestBean, protocolForm);
        if (protocolForm.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
            context.setForwardName(promptAfterNotification);
            protocolForm.getNotificationHelper().initializeDefaultValues(context);
            return true;
        } else {
            getNotificationService().sendNotificationAndPersist(context, getProtocolNotificationInstanceHook(), protocol);
            return false;
        }
    }    

    public ProtocolCorrespondence getProtocolCorrespondence (ProtocolFormBase protocolForm, String forwardName, ProtocolNotificationRequestBeanBase notificationRequestBean, boolean holdingPage) {
        return getProtocolCorrespondence(protocolForm.getProtocolDocument().getProtocol(), forwardName, notificationRequestBean, holdingPage);
    }

    public ProtocolCorrespondence getProtocolCorrespondence (ProtocolBase protocol, String forwardName, ProtocolNotificationRequestBeanBase notificationRequestBean, boolean holdingPage) {
        Map<String,Object> keyValues = new HashMap<String, Object>();
        keyValues.put("actionIdFk", protocol.getLastProtocolAction().getProtocolActionId());
        List<? extends ProtocolCorrespondence> correspondences = (List<? extends ProtocolCorrespondence>)getBusinessObjectService().findMatching(getProtocolCorrespondenceBOClassHook(), keyValues);
        if (correspondences.isEmpty()) {
            return null;
        } else {
            ProtocolCorrespondence correspondence = correspondences.get(0);
            correspondence.setForwardName(forwardName);
            correspondence.setNotificationRequestBean(notificationRequestBean);
            correspondence.setHoldingPage(holdingPage);
            return correspondence;
        }
    }
    
    protected ProtocolActionBean getActionBean(ProtocolFormBase protocolForm, String taskName) {
        ProtocolActionBean protocolActionBean = null;
        if (StringUtils.isNotBlank(taskName)) {
            protocolActionBean = (ProtocolActionBean) protocolForm.getActionHelper().getActionBean(taskName);
        }
        return protocolActionBean;
    }
    
    protected List<AnswerHeader> getAnswerHeaders(ProtocolFormBase protocolForm, String actionTypeCode) {
        ModuleQuestionnaireBean moduleQuestionnaireBean = getProtocolModuleQuestionnaireBeanInstanceHook(protocolForm, actionTypeCode);
        return getQuestionnaireAnswerService().getQuestionnaireAnswer(moduleQuestionnaireBean);
    }
    
    /**
     * This method is to check if the mandatory submission questionnaire is complete 
     * before submit a request
     * @param answerHeaders
     * @param errorKey
     * @return
     */
    protected boolean isMandatoryQuestionnaireComplete(List<AnswerHeader> answerHeaders, String errorKey) {
        boolean valid = true;
        ProtocolQuestionnaireAuditRuleBase auditRule = getProtocolQuestionnaireAuditRuleInstanceHook();
        if (!auditRule.isMandatorySubmissionQuestionnaireComplete(answerHeaders)) {
            GlobalVariables.getMessageMap().clearErrorMessages();
            GlobalVariables.getMessageMap().putError(errorKey, KeyConstants.ERROR_MANDATORY_QUESTIONNAIRE);
            valid = false;
        }
        return valid;
    }

    protected String sendRequestNotification(ProtocolFormBase protocolForm, String requestProtocolActionTypeCode, String reason, String protocolActionTab) throws Exception {
        ProtocolActionTypeBase protocolActionType = getBusinessObjectService().findBySinglePrimaryKey(getProtocolActionTypeBOClassHook(), requestProtocolActionTypeCode);
        String protocolActionTypeCode = protocolActionType.getProtocolActionTypeCode();
        String description = protocolActionType.getDescription();
        ProtocolNotificationRequestBeanBase notificationBean = getRequestActionNotificationBeanInstanceHook(protocolForm.getProtocolDocument().getProtocol(), protocolActionTypeCode, description, reason); 
        return getRedirectPathAfterProtocolAction(protocolForm, notificationBean, protocolActionTab);
    }
    
    protected abstract ProtocolNotificationRequestBeanBase getRequestActionNotificationBeanInstanceHook(ProtocolBase protocol, String protocolActionTypeCode, String description, String reason);
    protected abstract ProtocolQuestionnaireAuditRuleBase getProtocolQuestionnaireAuditRuleInstanceHook();
    protected abstract ModuleQuestionnaireBean getProtocolModuleQuestionnaireBeanInstanceHook(ProtocolFormBase protocolForm, String actionTypeCode);
    protected abstract Class<? extends ProtocolCorrespondence> getProtocolCorrespondenceBOClassHook();
    protected abstract String getNotificationEditorHook();
    protected abstract ProtocolNotification getProtocolNotificationInstanceHook();
    protected abstract ProtocolNotificationContextBase getProtocolNotificationContextHook(ProtocolNotificationRequestBeanBase notificationRequestBean, ProtocolFormBase protocolForm);
    protected abstract ProtocolTaskBase getProtocolTaskInstanceHook(String taskName, ProtocolBase protocol);
    protected abstract ProtocolTaskBase getProtocolGenericActionTaskInstanceHook(String genericActionName, ProtocolBase protocol);
    protected abstract ProtocolActionsCorrespondenceBase getNewProtocolActionsCorrespondence(String protocolActionTypeCode);
    protected abstract Class<? extends ProtocolActionTypeBase> getProtocolActionTypeBOClassHook();
    protected abstract String getProtocolCreatedActionTypeHook();
    protected abstract Class<? extends ProtocolBase> getProtocolBOClassHook();
    protected abstract String getProtocolRejectedInRoutingActionTypeHook();
    protected abstract String getProtocolRecalledInRoutingActionTypeHook();

    public ProtocolActionCorrespondenceGenerationService getProtocolActionCorrespondenceGenerationService() {
        return protocolActionCorrespondenceGenerationService;
    }

    public void setProtocolActionCorrespondenceGenerationService(
            ProtocolActionCorrespondenceGenerationService protocolActionCorrespondenceGenerationService) {
        this.protocolActionCorrespondenceGenerationService = protocolActionCorrespondenceGenerationService;
    }

    public KcNotificationService getNotificationService() {
        return notificationService;
    }

    public void setNotificationService(KcNotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public ReviewCommentsService<? extends ProtocolReviewAttachmentBase> getReviewCommentsService() {
        return reviewCommentsService;
    }

    public void setReviewCommentsService(ReviewCommentsService<? extends ProtocolReviewAttachmentBase> reviewCommentsService) {
        this.reviewCommentsService = reviewCommentsService;
    }

    public PersonService getPersonService() {
        return personService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    public QuestionnaireAnswerService getQuestionnaireAnswerService() {
        return questionnaireAnswerService;
    }

    public void setQuestionnaireAnswerService(QuestionnaireAnswerService questionnaireAnswerService) {
        this.questionnaireAnswerService = questionnaireAnswerService;
    }

}
