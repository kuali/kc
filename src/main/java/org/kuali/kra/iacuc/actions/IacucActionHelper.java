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
package org.kuali.kra.iacuc.actions;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.bo.CoeusSubModule;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.actions.assignCmt.IacucProtocolAssignCmtBean;
import org.kuali.kra.iacuc.actions.delete.IacucProtocolDeleteBean;
import org.kuali.kra.iacuc.actions.genericactions.IacucProtocolGenericActionBean;
import org.kuali.kra.iacuc.actions.notifycommittee.IacucProtocolNotifyCommitteeBean;
import org.kuali.kra.iacuc.actions.notifyiacuc.ProtocolNotifyIacucBean;
import org.kuali.kra.iacuc.actions.reviewcomments.IacucReviewCommentsService;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmitAction;
import org.kuali.kra.iacuc.actions.table.IacucProtocolTableBean;
import org.kuali.kra.iacuc.actions.withdraw.IacucProtocolWithdrawBean;
import org.kuali.kra.iacuc.auth.IacucProtocolTask;
import org.kuali.kra.iacuc.questionnaire.IacucProtocolModuleQuestionnaireBean;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.ProtocolForm;
import org.kuali.kra.protocol.actions.ActionHelper;
import org.kuali.kra.protocol.actions.ProtocolAction;
import org.kuali.kra.protocol.actions.ProtocolActionBean;
import org.kuali.kra.protocol.actions.delete.ProtocolDeleteBean;
import org.kuali.kra.protocol.actions.genericactions.ProtocolGenericActionBean;
import org.kuali.kra.protocol.actions.notifycommittee.ProtocolNotifyCommitteeBean;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsService;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmitAction;
import org.kuali.kra.protocol.actions.withdraw.ProtocolWithdrawBean;
import org.kuali.kra.protocol.auth.ProtocolTask;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondence;
import org.kuali.kra.protocol.questionnaire.ProtocolModuleQuestionnaireBean;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean;
import org.kuali.kra.questionnaire.answer.QuestionnaireAnswerService;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * The form helper class for the Protocol Actions tab.
 */
public class IacucActionHelper extends ActionHelper {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 777750088765246427L;
    
    
    /**
     * Each Helper must contain a reference to its document form
     * so that it can access the document.
     */
    private boolean canDeleteIacucProtocol;
    private boolean canDeleteIacucProtocolUnavailable;
    private boolean canAdministrativelyApprove;
    private boolean canAdministrativelyApproveUnavailable;
    private boolean canAdministrativelyMarkIncomplete;
    private boolean canAdministrativelyMarkIncompleteUnavailable;
    private boolean canAdministrativelyWithdraw;
    private boolean canAdministrativelyWithdrawUnavailable;
    private boolean canReviewNotRequired;
    private boolean canReviewNotRequiredUnavailable;
    private boolean canNotifyIacuc = false;
    private boolean canNotifyIacucUnavailable = false;
    private boolean canDesignatedMemberApproval = false;
    private boolean canDesignatedMemberApprovalUnavailable = false;
    private boolean canHold = false;
    private boolean canHoldUnavailable = false;
    private boolean canLiftHold = false;
    private boolean canLiftHoldUnavailable = false;
    private boolean canRequestToLiftHold = false;
    private boolean canRequestToLiftHoldUnavailable = false;
    private boolean canTable = false;
    private boolean canTableUnavailable = false;
    private boolean canIacucAcknowledge = false;
    private boolean canIacucAcknowledgeUnavailable = false;
    private boolean canIacucRequestDeactivate = false;
    private boolean canIacucRequestDeactivateUnavailable = false;
    private boolean canAssignCmt = false;
    private boolean canAssignCmtUnavailable = false;
    
    // action beans that are specific to IACUC
    protected IacucProtocolTableBean iacucProtocolTableBean;
    protected ProtocolNotifyIacucBean protocolNotifyIacucBean;      
    protected IacucProtocolAssignCmtBean protocolAssignCmtBean;

    public IacucProtocolAssignCmtBean getProtocolAssignCmtBean() {
        return protocolAssignCmtBean;
    }

    public void setProtocolAssignCmtBean(IacucProtocolAssignCmtBean protocolAssignCmtBean) {
        this.protocolAssignCmtBean = protocolAssignCmtBean;
    }

    /**
     * Constructs an ActionHelper.
     * @param form the protocol form
     * @throws Exception 
     */
    public IacucActionHelper(ProtocolForm form) throws Exception {
        super(form);
        
        protocolAssignCmtBean = new IacucProtocolAssignCmtBean(this);
        iacucProtocolTableBean = new IacucProtocolTableBean(this);

        initIacucSpecificActionBeanTaskMap();
   }
    
    /**
     * Initializes the mapping between the task names and the beans.  This is used to get the bean associated to the task name passed in from the tag file.
     * The reason TaskName (a text code) is used and ProtocolActionType (a number code) is not is because not every task is mapped to a ProtocolActionType.
     */
    private void initIacucSpecificActionBeanTaskMap() {
        actionBeanTaskMap.put(TaskName.IACUC_PROTOCOL_TABLE, iacucProtocolTableBean);
   
    }

  
    public void prepareView() throws Exception {
        protocolSubmitAction.prepareView();
        super.prepareView();

        submissionConstraint = getParameterValue(Constants.PARAMETER_IACUC_COMM_SELECTION_DURING_SUBMISSION);

        canSubmitProtocol = hasPermission(TaskName.SUBMIT_IACUC_PROTOCOL);
        canSubmitProtocolUnavailable = hasPermission(TaskName.SUBMIT_IACUC_PROTOCOL_UNAVAILABLE);


        // IACUC-specific actions
        canAssignCmt = hasPermission(TaskName.IACUC_ASSIGN_TO_COMMITTEE);
        canDeleteIacucProtocol = hasPermission(TaskName.DELETE_IACUC_PROTOCOL);
        canDeleteIacucProtocolUnavailable = hasPermission(TaskName.DELETE_IACUC_PROTOCOL_UNAVAILABLE);
        canAdministrativelyApprove = hasPermission(TaskName.ADMIN_APPROVE_IACUC_PROTOCOL);
        canAdministrativelyApproveUnavailable = hasPermission(TaskName.ADMIN_APPROVE_IACUC_PROTOCOL_UNAVAILABLE);
        canAdministrativelyWithdraw = hasPermission(TaskName.ADMIN_WITHDRAW_IACUC_PROTOCOL);
        canAdministrativelyWithdrawUnavailable = hasPermission(TaskName.ADMIN_WITHDRAW_IACUC_PROTOCOL_UNAVAILABLE);
        canNotifyIacuc = hasPermission(TaskName.IACUC_NOTIFY_COMMITTEE);
        canNotifyIacucUnavailable = hasPermission(TaskName.IACUC_NOTIFY_COMMITTEE_UNAVAILABLE);
        canHold = hasPermission(TaskName.IACUC_PROTOCOL_HOLD);
        canHoldUnavailable = hasPermission(TaskName.IACUC_PROTOCOL_HOLD_UNAVAILABLE);
        canLiftHold = hasPermission(TaskName.IACUC_PROTOCOL_LIFT_HOLD);
        canLiftHoldUnavailable = hasPermission(TaskName.IACUC_PROTOCOL_LIFT_HOLD_UNAVAILABLE);
        canRequestToLiftHold = hasPermission(TaskName.IACUC_PROTOCOL_REQUEST_LIFT_HOLD);
        canRequestToLiftHoldUnavailable = hasPermission(TaskName.IACUC_PROTOCOL_REQUEST_LIFT_HOLD_UNAVAILABLE);
        canTable = hasPermission(TaskName.IACUC_PROTOCOL_TABLE);
        canTableUnavailable = hasPermission(TaskName.IACUC_PROTOCOL_TABLE_UNAVAILABLE);
        canIacucAcknowledge = hasPermission(TaskName.IACUC_ACKNOWLEDGEMENT);
        canIacucAcknowledgeUnavailable = hasPermission(TaskName.IACUC_ACKNOWLEDGEMENT_UNAVAILABLE);
        canIacucRequestDeactivate = hasPermission(TaskName.IACUC_PROTOCOL_REQUEST_DEACTIVATE);
        canIacucRequestDeactivateUnavailable = hasPermission(TaskName.IACUC_PROTOCOL_REQUEST_DEACTIVATE_UNAVAILABLE);
        canAdministrativelyMarkIncomplete = hasPermission(TaskName.ADMIN_INCOMPLETE_IACUC_PROTOCOL);
        canAdministrativelyMarkIncompleteUnavailable = hasPermission(TaskName.ADMIN_INCOMPLETE_IACUC_PROTOCOL_UNAVAILABLE);
        canDesignatedMemberApproval = hasPermission(TaskName.IACUC_PROTOCOL_DESIGNATED_MEMBER_APPROVAL);
        canDesignatedMemberApprovalUnavailable = hasPermission(TaskName.IACUC_PROTOCOL_DESIGNATED_MEMBER_APPROVAL_UNAVAILABLE);
        canHold = hasPermission(TaskName.IACUC_PROTOCOL_HOLD);
        canHoldUnavailable = hasPermission(TaskName.IACUC_PROTOCOL_HOLD_UNAVAILABLE);
        canLiftHold = hasPermission(TaskName.IACUC_PROTOCOL_LIFT_HOLD);
        canLiftHoldUnavailable = hasPermission(TaskName.IACUC_PROTOCOL_LIFT_HOLD_UNAVAILABLE);
        canNotifyIacuc = hasPermission(TaskName.IACUC_NOTIFY_COMMITTEE);
        canNotifyIacucUnavailable = hasPermission(TaskName.IACUC_NOTIFY_COMMITTEE_UNAVAILABLE);
        canRequestToLiftHold = hasPermission(TaskName.IACUC_PROTOCOL_REQUEST_LIFT_HOLD);
        canRequestToLiftHoldUnavailable = hasPermission(TaskName.IACUC_PROTOCOL_REQUEST_LIFT_HOLD_UNAVAILABLE);
        canReviewNotRequired = hasPermission(TaskName.REVIEW_NOT_REQUIRED_IACUC_PROTOCOL);
        canReviewNotRequiredUnavailable = hasPermission(TaskName.REVIEW_NOT_REQUIRED_IACUC_PROTOCOL_UNAVAILABLE);
        canTable = hasPermission(TaskName.IACUC_PROTOCOL_TABLE);
        canTableUnavailable = hasPermission(TaskName.IACUC_PROTOCOL_TABLE_UNAVAILABLE);
        canAssignCmt = hasPermission(TaskName.IACUC_ASSIGN_TO_COMMITTEE);
        canAssignCmtUnavailable = hasPermission(TaskName.IACUC_ASSIGN_TO_COMMITTEE_UNAVAILABLE);

    }
    
    /**
     * Refreshes the comments for all the beans from the database.  Use sparingly since this will erase non-persisted comments.
     */
    public void prepareCommentsView() {

    }
    


    public static boolean hasAssignCmtSchedPermission(String userId, String protocolNumber) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("protocolNumber", protocolNumber);
        BusinessObjectService bos = KraServiceLocator.getService(BusinessObjectService.class);
        IacucProtocol protocol = ((List<IacucProtocol>) bos.findMatching(IacucProtocol.class, fieldValues)).get(0);
        IacucProtocolTask task = new IacucProtocolTask(TaskName.MODIFY_IACUC_PROTOCOL, protocol);
        TaskAuthorizationService tas = KraServiceLocator.getService(TaskAuthorizationService.class);        
        return tas.isAuthorized(userId, task);
    }
    
    protected boolean hasPermission(String taskName) {
        IacucProtocolTask task = new IacucProtocolTask(taskName, getIacucProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected boolean hasGenericPermission(String genericActionName) {
        IacucProtocolTask task = new IacucProtocolTask(TaskName.GENERIC_IACUC_PROTOCOL_ACTION, getIacucProtocol(), genericActionName);
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected boolean hasGenericUnavailablePermission(String genericActionName) {
        IacucProtocolTask task = new IacucProtocolTask(TaskName.GENERIC_IACUC_PROTOCOL_ACTION_UNAVAILABLE, getIacucProtocol(), genericActionName);
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }

    protected boolean hasFollowupAction(String actionCode) {
        return false;
    }
    
    public IacucProtocolSubmitAction getIacucProtocolSubmitAction() {
        return (IacucProtocolSubmitAction)protocolSubmitAction;
    }

    public ProtocolNotifyIacucBean getProtocolNotifyIacucBean() {
        return protocolNotifyIacucBean;
    }

    public ProtocolForm getProtocolForm() {
        return form;
    }
    
    public Protocol getProtocol() {
        return form.getProtocolDocument().getProtocol();
    }

    
 
    
    private boolean hasAnsweredQuestionnaire(String moduleSubItemCode, String moduleSubItemKey) {
        return getAnswerHeaderCount(moduleSubItemCode, moduleSubItemKey) > 0;
    }

    int getAnswerHeaderCount(String moduleSubItemCode, String moduleSubItemKey) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("moduleItemCode", CoeusModule.IACUC_PROTOCOL_MODULE_CODE);
        fieldValues.put("moduleItemKey", getProtocol().getProtocolNumber());
        fieldValues.put("moduleSubItemCode", moduleSubItemCode);
        fieldValues.put("moduleSubItemKey", moduleSubItemKey);
        return getBusinessObjectService().countMatching(AnswerHeader.class, fieldValues);
    }
    
    /*
     * This will check whether there is submission questionnaire.
     * When business rule is implemented, this will become more complicated because
     * each request action may have different set of questionnaire, so this has to be changed.
     */
    private boolean hasSubmissionQuestionnaire() {
        ModuleQuestionnaireBean moduleQuestionnaireBean = new ModuleQuestionnaireBean(CoeusModule.IACUC_PROTOCOL_MODULE_CODE, this.getProtocolForm().getProtocolDocument().getProtocol().getProtocolNumber() + "T", CoeusSubModule.PROTOCOL_SUBMISSION, "999", false);
        return CollectionUtils.isNotEmpty(getQuestionnaireAnswerService().getQuestionnaireAnswer(moduleQuestionnaireBean));
    }

    private QuestionnaireAnswerService getQuestionnaireAnswerService() {
        return KraServiceLocator.getService(QuestionnaireAnswerService.class);
    }


    public ProtocolActionBean getActionBean(String taskName) {
        return actionBeanTaskMap.get(taskName);
    }

    public boolean isCanAbandon() {
        return canAbandon;
    }

    public ProtocolCorrespondence getProtocolCorrespondence() {
        return protocolCorrespondence;
    }

    public void setProtocolCorrespondence(ProtocolCorrespondence protocolCorrespondence) {
        this.protocolCorrespondence = protocolCorrespondence;
    }

    public boolean getIsApproveOpenForFollowup() {
        return hasFollowupAction(IacucProtocolActionType.IACUC_APPROVED);
    }

    public boolean getIsDisapproveOpenForFollowup() {
        return hasFollowupAction(IacucProtocolActionType.IACUC_DISAPPROVED);
    }

    public boolean getIsReturnForSMROpenForFollowup() {
        return hasFollowupAction(IacucProtocolActionType.IACUC_MINOR_REVISIONS_REQUIRED);
    }

    public boolean getIsReturnForSRROpenForFollowup() {
        return hasFollowupAction(IacucProtocolActionType.IACUC_MAJOR_REVISIONS_REQUIRED);
    }

    public boolean isOpenForFollowup() {
        return getIsApproveOpenForFollowup() || getIsDisapproveOpenForFollowup() ||
               getIsReturnForSMROpenForFollowup() || getIsReturnForSRROpenForFollowup();
    }

    protected List<String>getActionTypeSubmissionDocList() {
        return IacucProtocolActionType.getActionTypeSubmissionDocs();
    }

    public boolean isCanDesignatedMemberApproval() {
        return canDesignatedMemberApproval;
    }

    public boolean isCanDesignatedMemberApprovalUnavailable() {
        return canDesignatedMemberApprovalUnavailable;
    }

    public boolean isCanHold() {
        return canHold;
    }

    public boolean isCanHoldUnavailable() {
        return canHoldUnavailable;
    }

    public boolean isCanLiftHold() {
        return canLiftHold;
    }

    public boolean isCanLiftHoldUnavailable() {
        return canLiftHoldUnavailable;
    }

    public boolean isCanRequestToLiftHold() {
        return canRequestToLiftHold;
    }

    public boolean isCanRequestToLiftHoldUnavailable() {
        return canRequestToLiftHoldUnavailable;
    }

    public boolean isCanTable() {
        return canTable;
    }

    public boolean isCanTableUnavailable() {
        return canTableUnavailable;
    }
    
    public boolean isCanNotifyIacuc() {
        return canNotifyIacuc;
    }
    
    public boolean isCanNotifyIacucUnavailable() {
        return canNotifyIacucUnavailable;
    }

    public boolean getCanDeleteIacucProtocol() {
        return canDeleteIacucProtocol;
    }
    
    public boolean getCanDeleteIacucProtocolUnavailable() {
        return canDeleteIacucProtocolUnavailable;
    }
    
    public boolean getCanAdministrativelyApprove() {
        return canAdministrativelyApprove;
    }
    
    public boolean getCanAdministrativelyApproveUnavailable() {
        return canAdministrativelyApproveUnavailable;
    }
    
    public boolean getCanAdministrativelyMarkIncomplete() {
        return canAdministrativelyMarkIncomplete;
    }
    
    public boolean getCanAdministrativelyMarkIncompleteUnavailable() {
        return canAdministrativelyMarkIncompleteUnavailable;
    }
    
    public boolean getCanAdministrativelyWithdraw() {
        return canAdministrativelyWithdraw;
    }
    
    public boolean getCanAdministrativelyWithdrawUnavailable() {
        return canAdministrativelyWithdrawUnavailable;
    }
    
    public boolean getCanReviewNotRequired() {
        return canReviewNotRequired;
    }
    
    public boolean getCanReviewNotRequiredUnavailable() {
        return canReviewNotRequiredUnavailable;
    }

    public boolean canIacucAcknowledge() {
        return canIacucAcknowledge;
    }
    
    public boolean canIacucAcknowledgeUnavailable() {
        return canIacucAcknowledgeUnavailable;
    }
    
    public boolean canIacucRequestDeactivate() {
        return canIacucRequestDeactivate;
    }
    
    public boolean canIacucRequestDeactivateUnavailable() {
        return canIacucRequestDeactivateUnavailable;
    }

    protected String getParameterValue(String parameterName) {
        String result = getParameterService().getParameterValueAsString(IacucProtocolDocument.class, parameterName);
        if (result == null) {
            result = super.getParameterValue(parameterName);
        }
        return result;
    }

    protected IacucProtocol getIacucProtocol() {
        return (IacucProtocol)getProtocol();
    }
    
    public boolean getCanAssignCmt() {
        return canAssignCmt;
    }

    public void setCanAssignCmt(boolean canAssignCmt) {
        this.canAssignCmt = canAssignCmt;
    }

    public boolean getCanAssignCmtUnavailable() {
        return canAssignCmtUnavailable;
    }

    public void setCanAssignCmtUnavailable(boolean canAssignCmtUnavailable) {
        this.canAssignCmtUnavailable = canAssignCmtUnavailable;
    }

    @Override
    protected IacucProtocolTask createNewAmendRenewDeleteTaskInstanceHook(Protocol protocol) {
        return new IacucProtocolTask(TaskName.IACUC_PROTOCOL_AMEND_RENEW_DELETE, (IacucProtocol) protocol);
    }
    

    @Override
    protected IacucProtocolTask createNewAmendRenewDeleteUnavailableTaskInstanceHook(Protocol protocol) {
        return new IacucProtocolTask(TaskName.IACUC_PROTOCOL_AMEND_RENEW_DELETE_UNAVAILABLE, (IacucProtocol) protocol);
    }

    @Override
    protected ProtocolDeleteBean getNewProtocolDeleteBeanInstanceHook(ActionHelper actionHelper) {
        return new IacucProtocolDeleteBean((IacucActionHelper)actionHelper);
    }

    
    protected Class<? extends ReviewCommentsService> getReviewCommentsServiceClassHook() {
        return IacucReviewCommentsService.class;
    }
    
    protected ProtocolGenericActionBean buildProtocolGenericActionBean(String actionTypeCode, String errorPropertyKey) {
        ProtocolGenericActionBean bean = new IacucProtocolGenericActionBean(this, errorPropertyKey);
        
        bean.getReviewCommentsBean().setReviewComments(getCopiedReviewComments());
        bean.getReviewCommentsBean().setHideReviewerName(getReviewCommentsService().setHideReviewerName(bean.getReviewCommentsBean().getReviewComments()));            
        ProtocolAction protocolAction = findProtocolAction(actionTypeCode, getProtocol().getProtocolActions(), getProtocol().getProtocolSubmission());
        if (protocolAction != null) {
            bean.setComments(protocolAction.getComments());
            bean.setActionDate(new Date(protocolAction.getActionDate().getTime()));
        }
        
        return bean;
    }
    
    
    @Override
    protected ProtocolTask createNewAbandonTaskInstanceHook(Protocol protocol) {
        return new IacucProtocolTask(TaskName.IACUC_ABANDON_PROTOCOL, (IacucProtocol) protocol);
    }

    @Override
    protected String getAbandonActionTypeHook() {    
        return IacucProtocolActionType.IACUC_ABANDON;
    }

    @Override
    protected String getAbandonPropertyKeyHook() {
        return Constants.PROTOCOL_ABANDON_ACTION_PROPERTY_KEY;
    }

    @Override
    protected ProtocolSubmitAction getNewProtocolSubmitActionInstanceHook(ActionHelper actionHelper) {
       return new IacucProtocolSubmitAction((IacucActionHelper) actionHelper);
    }

    @Override
    protected ProtocolNotifyCommitteeBean getNewProtocolNotifyCommitteeBeanInstanceHook(ActionHelper actionHelper) {
        return new IacucProtocolNotifyCommitteeBean((IacucActionHelper) actionHelper);
    }


    @Override
    protected ProtocolModuleQuestionnaireBean getNewProtocolModuleQuestionnaireBeanInstanceHook(Protocol protocol) {
        return new IacucProtocolModuleQuestionnaireBean((IacucProtocol)protocol);
    }

    @Override
    protected ProtocolTask getNewSubmitProtocolTaskInstanceHook(Protocol protocol) {
        return new IacucProtocolTask(TaskName.SUBMIT_IACUC_PROTOCOL, (IacucProtocol) protocol);
    }

    @Override
    protected ProtocolTask getNewSubmitProtocolUnavailableTaskInstanceHook(Protocol protocol) {
        return new IacucProtocolTask(TaskName.SUBMIT_IACUC_PROTOCOL_UNAVAILABLE, (IacucProtocol) protocol);
    }

    @Override
    protected ProtocolWithdrawBean getNewProtocolWithdrawBeanInstanceHook(ActionHelper actionHelper) {
        return new IacucProtocolWithdrawBean((IacucActionHelper) actionHelper);
    }

    @Override
    protected ProtocolTask getNewWithdrawProtocolTaskInstanceHook(Protocol protocol) {
        return new IacucProtocolTask(TaskName.IACUC_PROTOCOL_WITHDRAW, (IacucProtocol) protocol);
    }

    @Override
    protected ProtocolTask getNewWithdrawProtocolTaskInstanceUnavailableHook(Protocol protocol) {
        return new IacucProtocolTask(TaskName.IACUC_PROTOCOL_WITHDRAW_UNAVAILABLE, (IacucProtocol) protocol);
    }

}

