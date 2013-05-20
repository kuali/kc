/*
 * Copyright 2005-2013 The Kuali Foundation
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.authorization.ApplicationTask;
import org.kuali.kra.bo.AttachmentFile;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.bo.CoeusSubModule;
import org.kuali.kra.common.committee.meeting.CommitteeScheduleMinuteBase;
import org.kuali.kra.common.committee.meeting.MinuteEntryType;
import org.kuali.kra.common.committee.service.CommitteeServiceBase;
import org.kuali.kra.common.notification.bo.NotificationType;
import org.kuali.kra.common.notification.bo.NotificationTypeRecipient;
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolAction;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.iacuc.actions.abandon.IacucProtocolAbandonService;
import org.kuali.kra.iacuc.actions.amendrenew.CreateIacucAmendmentEvent;
import org.kuali.kra.iacuc.actions.amendrenew.CreateIacucContinuationEvent;
import org.kuali.kra.iacuc.actions.amendrenew.CreateIacucRenewalEvent;
import org.kuali.kra.iacuc.actions.amendrenew.IacucProtocolAmendRenewService;
import org.kuali.kra.iacuc.actions.amendrenew.IacucProtocolAmendmentBean;
import org.kuali.kra.iacuc.actions.amendrenew.ModifyIacucAmendmentSectionsEvent;
import org.kuali.kra.iacuc.actions.approve.IacucProtocolApproveBean;
import org.kuali.kra.iacuc.actions.approve.IacucProtocolApproveEvent;
import org.kuali.kra.iacuc.actions.approve.IacucProtocolApproveService;
import org.kuali.kra.iacuc.actions.assignCmt.IacucProtocolAssignCmtBean;
import org.kuali.kra.iacuc.actions.assignCmt.IacucProtocolAssignCmtEvent;
import org.kuali.kra.iacuc.actions.assignCmt.IacucProtocolAssignCmtService;
import org.kuali.kra.iacuc.actions.assignagenda.IacucProtocolAssignToAgendaBean;
import org.kuali.kra.iacuc.actions.assignagenda.IacucProtocolAssignToAgendaEvent;
import org.kuali.kra.iacuc.actions.assignagenda.IacucProtocolAssignToAgendaService;
import org.kuali.kra.iacuc.actions.copy.IacucProtocolCopyService;
import org.kuali.kra.iacuc.actions.correction.IacucAdminCorrectionBean;
import org.kuali.kra.iacuc.actions.correction.IacucProtocolAdminCorrectionEvent;
import org.kuali.kra.iacuc.actions.decision.IacucCommitteeDecision;
import org.kuali.kra.iacuc.actions.decision.IacucCommitteeDecisionAbstainerEvent;
import org.kuali.kra.iacuc.actions.decision.IacucCommitteeDecisionEvent;
import org.kuali.kra.iacuc.actions.decision.IacucCommitteeDecisionRecuserEvent;
import org.kuali.kra.iacuc.actions.decision.IacucCommitteeDecisionService;
import org.kuali.kra.iacuc.actions.decision.IacucCommitteePerson;
import org.kuali.kra.iacuc.actions.delete.IacucProtocolDeleteService;
import org.kuali.kra.iacuc.actions.followup.IacucFollowupActionService;
import org.kuali.kra.iacuc.actions.genericactions.IacucProtocolGenericActionBean;
import org.kuali.kra.iacuc.actions.genericactions.IacucProtocolGenericActionEvent;
import org.kuali.kra.iacuc.actions.genericactions.IacucProtocolGenericActionService;
import org.kuali.kra.iacuc.actions.modifysubmission.IacucProtocolModifySubmissionBean;
import org.kuali.kra.iacuc.actions.modifysubmission.IacucProtocolModifySubmissionEvent;
import org.kuali.kra.iacuc.actions.modifysubmission.IacucProtocolModifySubmissionService;
import org.kuali.kra.iacuc.actions.noreview.IacucProtocolReviewNotRequiredBean;
import org.kuali.kra.iacuc.actions.noreview.IacucProtocolReviewNotRequiredEvent;
import org.kuali.kra.iacuc.actions.noreview.IacucProtocolReviewNotRequiredService;
import org.kuali.kra.iacuc.actions.notifycommittee.IacucProtocolNotifyCommitteeService;
import org.kuali.kra.iacuc.actions.notifyiacuc.IacucProtocolNotifyIacucService;
import org.kuali.kra.iacuc.actions.notifyiacuc.NotifyIacucNotificationRenderer;
import org.kuali.kra.iacuc.actions.print.IacucProtocolPrintingService;
import org.kuali.kra.iacuc.actions.request.IacucProtocolRequestBean;
import org.kuali.kra.iacuc.actions.request.IacucProtocolRequestEvent;
import org.kuali.kra.iacuc.actions.request.IacucProtocolRequestRule;
import org.kuali.kra.iacuc.actions.request.IacucProtocolRequestService;
import org.kuali.kra.iacuc.actions.reviewcomments.IacucProtocolAddReviewAttachmentEvent;
import org.kuali.kra.iacuc.actions.reviewcomments.IacucProtocolAddReviewCommentEvent;
import org.kuali.kra.iacuc.actions.reviewcomments.IacucProtocolManageReviewAttachmentEvent;
import org.kuali.kra.iacuc.actions.reviewcomments.IacucReviewAttachmentsBean;
import org.kuali.kra.iacuc.actions.reviewcomments.IacucReviewCommentsBean;
import org.kuali.kra.iacuc.actions.reviewcomments.IacucReviewCommentsService;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolReviewType;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolReviewerBean;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmitAction;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmitActionEvent;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmitActionService;
import org.kuali.kra.iacuc.actions.submit.IacucValidProtocolActionAction;
import org.kuali.kra.iacuc.actions.table.IacucProtocolTableBean;
import org.kuali.kra.iacuc.actions.table.IacucProtocolTableService;
import org.kuali.kra.iacuc.actions.undo.IacucProtocolUndoLastActionService;
import org.kuali.kra.iacuc.actions.withdraw.IacucProtocolWithdrawService;
import org.kuali.kra.iacuc.auth.IacucGenericProtocolAuthorizer;
import org.kuali.kra.iacuc.auth.IacucProtocolTask;
import org.kuali.kra.iacuc.committee.meeting.IacucCommitteeScheduleMinute;
import org.kuali.kra.iacuc.committee.service.IacucCommitteeService;
import org.kuali.kra.iacuc.correspondence.IacucProtocolActionCorrespondenceGenerationService;
import org.kuali.kra.iacuc.correspondence.IacucProtocolActionsCorrespondence;
import org.kuali.kra.iacuc.correspondence.IacucProtocolCorrespondence;
import org.kuali.kra.iacuc.noteattachment.IacucProtocolAttachmentPersonnel;
import org.kuali.kra.iacuc.noteattachment.IacucProtocolAttachmentProtocol;
import org.kuali.kra.iacuc.noteattachment.IacucProtocolAttachmentService;
import org.kuali.kra.iacuc.notification.IacucProtocolAssignReviewerNotificationRenderer;
import org.kuali.kra.iacuc.notification.IacucProtocolGenericActionNotificationRenderer;
import org.kuali.kra.iacuc.notification.IacucProtocolNotification;
import org.kuali.kra.iacuc.notification.IacucProtocolNotificationContext;
import org.kuali.kra.iacuc.notification.IacucProtocolNotificationRenderer;
import org.kuali.kra.iacuc.notification.IacucProtocolNotificationRequestBean;
import org.kuali.kra.iacuc.notification.IacucProtocolRequestActionNotificationRenderer;
import org.kuali.kra.iacuc.notification.IacucProtocolReviewDeterminationNotificationRenderer;
import org.kuali.kra.iacuc.notification.IacucProtocolWithReasonNotificationRenderer;
import org.kuali.kra.iacuc.notification.IacucRequestActionNotificationBean;
import org.kuali.kra.iacuc.onlinereview.IacucProtocolOnlineReview;
import org.kuali.kra.iacuc.onlinereview.IacucProtocolReviewAttachment;
import org.kuali.kra.iacuc.questionnaire.IacucProtocolModuleQuestionnaireBean;
import org.kuali.kra.iacuc.questionnaire.IacucProtocolQuestionnaireAuditRule;
import org.kuali.kra.iacuc.questionnaire.print.IacucQuestionnairePrintingService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.actions.history.ProtocolHistoryFilterDatesEvent;
import org.kuali.kra.protocol.summary.AttachmentSummary;
import org.kuali.kra.protocol.summary.ProtocolSummary;
import org.kuali.kra.printing.Printable;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.printing.service.WatermarkService;
import org.kuali.kra.printing.util.PrintingUtils;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.ProtocolFormBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.ProtocolActionBean;
import org.kuali.kra.protocol.actions.ProtocolActionTypeBase;
import org.kuali.kra.protocol.actions.ProtocolOnlineReviewCommentable;
import org.kuali.kra.protocol.actions.ProtocolSubmissionDocBase;
import org.kuali.kra.protocol.actions.notify.ProtocolActionAttachment;
import org.kuali.kra.protocol.actions.print.ProtocolActionPrintEvent;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewerBeanBase;
import org.kuali.kra.protocol.actions.undo.UndoLastActionBean;
import org.kuali.kra.protocol.auth.ProtocolTaskBase;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondence;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentBase;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentProtocolBase;
import org.kuali.kra.protocol.notification.ProtocolNotificationRequestBeanBase;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean;
import org.kuali.kra.questionnaire.answer.QuestionnaireAnswerService;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.kra.util.watermark.WatermarkConstants;
import org.kuali.kra.web.struts.action.AuditActionHelper;
import org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase;
import org.kuali.kra.web.struts.action.StrutsConfirmation;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.kns.document.authorization.DocumentAuthorizerBase;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.util.WebUtils;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.ObjectUtils;
import org.springframework.util.CollectionUtils;

public class IacucProtocolActionsAction extends IacucProtocolAction {
    
    private static final Log LOG = LogFactory.getLog(IacucProtocolActionsAction.class);
    private static final String CONFIRM_NO_ACTION = "";
    private static final String CONFIRM_DELETE_ACTION_ATT = "confirmDeleteActionAttachment";
    private static final String CONFIRM_FOLLOWUP_ACTION = "confirmAddFollowupAction";

    private static final String PROTOCOL_TAB = "iacucProtocol";
    private static final String PROTOCOL_ACTIONS_TAB = "iacucProtocolActions";
    
    private static final String CONFIRM_SUBMIT_FOR_REVIEW_KEY = "confirmSubmitForReview";
    private static final String CONFIRM_ASSIGN_TO_AGENDA_KEY = "confirmAssignToAgenda";
    private static final String CONFIRM_ASSIGN_CMT_SCHED_KEY = "confirmAssignCmtSched";
    private static final String CONIFRM_REMOVE_REVIEWER_KEY="confirmRemoveReviewer";
    
    
    private static final String NOT_FOUND_SELECTION = "The attachment was not found for selection ";

    private static final String CONFIRM_DELETE_PROTOCOL_KEY = "confirmDeleteProtocol";
    private static final String INVALID_ATTACHMENT = "this attachment version is invalid ";

    /** signifies that a response has already be handled therefore forwarding to obtain a response is not needed. */
    private static final ActionForward RESPONSE_ALREADY_HANDLED = null;
    private static final String SUBMISSION_ID = "submissionId";
    private static final String CORRESPONDENCE = "correspondence";
    

    public ActionForward assignCommitteeSchedule(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        IacucProtocol protocol = (IacucProtocol) protocolForm.getProtocolDocument().getProtocol();
        final String callerString = "assignCommitteeSchedule";
 
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    // map to decide the followup action page to open.  "value" part is the action tab "title"
    private static Map<String, String> motionTypeMap = new HashMap<String, String>() {
        {
            put("1", "Approve Action");
            put("2", "Disapprove");
            put("3", "Return for Specific Minor Revisions");
            put("4", "Return for Substantive Revisions Required");
        }
    };


 
    /** {@inheritDoc} */
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        if (request.getParameterMap().containsKey("doCopy")) {
            String docId  = request.getParameter("docId");
            IacucProtocolDocument ipd = (IacucProtocolDocument) this.getDocumentService().getByDocumentHeaderId(docId);
            protocolForm.setDocument(ipd);
            protocolForm.setDefaultOpenCopyTab(true);
        }
        
        // set the current task name on the action helper before the requested method is dispatched
        // so that beans etc can access it when preparing view after/during the requested method's execution
        String currentTaskName = getTaskName(request);
        if(currentTaskName != null) {
            protocolForm.getActionHelper().setCurrentTask(currentTaskName);
        }
        else {
            protocolForm.getActionHelper().setCurrentTask("");
        }
        ActionForward actionForward = super.execute(mapping, form, request, response);
        protocolForm.getActionHelper().prepareView();
        protocolForm.getActionHelper().initFilterDatesView();
        // submit action may change "submission details", so re-initialize it
        // TODO do we really need this? the above call to prepareView() will invoke it anyway, so the below call seems redundant and wasteful.
        ((IacucActionHelper)protocolForm.getActionHelper()).initSubmissionDetails();
        
        return actionForward;
    }

    
    
    /**
     * Invoked when the "copy protocol" button is clicked.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward copyProtocol(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {


        IacucProtocolForm protocolForm = (IacucProtocolForm) form;

        ApplicationTask task = new ApplicationTask(TaskName.CREATE_IACUC_PROTOCOL);
        if (isAuthorized(task)) {
            String newDocId = getIacucProtocolCopyService().copyProtocol(protocolForm.getIacucProtocolDocument()).getDocumentNumber();

            // Switch over to the new protocol document and
            // go to the ProtocolBase tab web page.

            protocolForm.setDocId(newDocId);
            protocolForm.setViewOnly(false);
            loadDocument(protocolForm);
            protocolForm.getIacucProtocolDocument().setViewOnly(protocolForm.isViewOnly());
            protocolForm.getActionHelper().setCurrentSubmissionNumber(-1);
            protocolForm.getProtocolHelper().prepareView();
            
            return mapping.findForward(PROTOCOL_TAB);
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }    

    /** {@inheritDoc} */
    public ActionForward activate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return new AuditActionHelper().setAuditMode(mapping, (ProtocolFormBase) form, true);
    }

    /** {@inheritDoc} */
    public ActionForward deactivate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return new AuditActionHelper().setAuditMode(mapping, (ProtocolFormBase) form, false);
    }

    /**
     * Refreshes the page. We only need to redraw the page. This method is used when JavaScript is disabled. During a review
     * submission action, the user will have to refresh the page. For example, after a committee is selected, the page needs to be
     * refreshed so that the available scheduled dates for that committee can be displayed in the drop-down menu for the scheduled
     * dates. Please see ProtocolSubmitAction.prepareView() for how the Submit for Review works on a refresh.
     * 
     * @param mapping the mapping associated with this action.
     * @param form the ProtocolBase form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the name of the HTML page to display
     * @throws Exception doesn't ever really happen
     */
    public ActionForward refreshPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    
    /**
     * Submit a protocol for review.
     * 
     * @param mapping the mapping associated with this action.
     * @param form the ProtocolBase form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the name of the HTML page to display
     * @throws Exception
     */
    public ActionForward submitForReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);

        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolDocument protocolDocument = (IacucProtocolDocument)protocolForm.getProtocolDocument();
        protocolForm.setAuditActivated(true);
        IacucProtocolTask task = new IacucProtocolTask(TaskName.SUBMIT_IACUC_PROTOCOL, protocolDocument.getIacucProtocol());
        if (isAuthorized(task)) {
            IacucProtocolSubmitAction submitAction = (IacucProtocolSubmitAction) protocolForm.getActionHelper().getProtocolSubmitAction();            
            if (applyRules(protocolForm,new IacucProtocolSubmitActionEvent(protocolDocument, submitAction))) {
                AuditActionHelper auditActionHelper = new AuditActionHelper();
                if (auditActionHelper.auditUnconditionally(protocolDocument)) {
                    
                    if (isCommitteeMeetingAssignedMaxProtocols(submitAction.getNewCommitteeId(), submitAction.getNewScheduleId())) {
                        forward = confirm(buildSubmitForReviewConfirmationQuestion(mapping, form, request, response), CONFIRM_SUBMIT_FOR_REVIEW_KEY, "");
                    } else {
                        forward = submitForReviewAndRedirect(mapping, form, request, response);
                    }
                } else {
                    GlobalVariables.getMessageMap().clearErrorMessages();
                    GlobalVariables.getMessageMap().putError("datavalidation", KeyConstants.ERROR_WORKFLOW_SUBMISSION,  new String[] {});
                }
            }
        }

        return forward;
    }
    

    public ActionForward assignCommittee(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocol protocol = (IacucProtocol) protocolForm.getProtocolDocument().getProtocol();
        ProtocolTaskBase task = new IacucProtocolTask(TaskName.IACUC_ASSIGN_TO_COMMITTEE, protocol);
        
        if (!hasDocumentStateChanged(protocolForm)) {
            if (isAuthorized(task)) {
                IacucActionHelper actionHelper = (IacucActionHelper)protocolForm.getActionHelper();
                IacucProtocolAssignCmtBean actionBean = actionHelper.getProtocolAssignCmtBean();
                if (applyRules(new IacucProtocolAssignCmtEvent(protocolForm.getProtocolDocument(), actionBean))) {
                    if( protocolForm.getProtocolDocument().getProtocol().getProtocolSubmission() != null) {
                        getAssignToCmtService().assignToCommittee(protocolForm.getProtocolDocument().getProtocol(), actionBean);
                        recordProtocolActionSuccess("Assign to Committee");
                        protocolForm.setReinitializeModifySubmissionFields(true);
                    }
                }
            }
        } else {
            GlobalVariables.getMessageMap().clearErrorMessages();
            GlobalVariables.getMessageMap().putError("documentstatechanged", KeyConstants.ERROR_IACUC_PROTOCOL_DOCUMENT_STATE_CHANGED,  new String[] {});            
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    protected IacucProtocolAssignCmtService getAssignToCmtService() {
        return KraServiceLocator.getService(IacucProtocolAssignCmtService.class);
    }
      
    private boolean isCommitteeMeetingAssignedMaxProtocols(String committeeId, String scheduleId) {
        boolean isMax = false;
        
        return isMax;
    }

    private int activeSubmissionCount(List<IacucProtocolSubmission> submissions) {
        int count = 0;
        for (IacucProtocolSubmission submission : submissions) {
            if (submission.getProtocol().isActive()) {
                count++;
            }
        }
        return count;
    }

    /*
     * Builds the confirmation question to verify if the user wants to submit the protocol for review.
     */
    private StrutsConfirmation buildSubmitForReviewConfirmationQuestion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_SUBMIT_FOR_REVIEW_KEY,
                KeyConstants.QUESTION_PROTOCOL_CONFIRM_SUBMIT_FOR_REVIEW);
    }

    /**
     * Method dispatched from <code>{@link KraTransactionalDocumentActionBase#confirm(StrutsQuestion, String, String)}</code> for
     * when a "yes" condition is met.
     * 
     * @param mapping The mapping associated with this action.
     * @param form The ProtocolBase form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the destination
     * @throws Exception
     * @see KraTransactionalDocumentActionBase#confirm(StrutsQuestion, String, String)
     */
    public ActionForward confirmSubmitForReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {
        
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        
        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        if (CONFIRM_SUBMIT_FOR_REVIEW_KEY.equals(question)) {
            forward = submitForReviewAndRedirect(mapping, form, request, response);
        }

        return forward;
    }

    
    /**
     * Submits the ProtocolBase for review and calculates the redirect back to the portal page, adding in the proper parameters for displaying a message to the
     * user upon successful submission.
     * 
     * @param mapping The mapping associated with this action.
     * @param form The ProtocolBase form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the destination
     * @throws Exception
     */
    private ActionForward submitForReviewAndRedirect(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
    throws Exception {
    
    IacucProtocolForm protocolForm = (IacucProtocolForm) form;
    IacucProtocolDocument protocolDocument = protocolForm.getIacucProtocolDocument();
    IacucProtocol protocol = protocolDocument.getIacucProtocol();
    IacucProtocolSubmitAction submitAction = (IacucProtocolSubmitAction) protocolForm.getActionHelper().getProtocolSubmitAction();
    
    getProtocolSubmitActionService().submitToIacucForReview(protocol, submitAction);
     
    super.route(mapping, protocolForm, request, response);
    
    // first, send out notification that protocol has been submitted
    IacucProtocolNotificationRenderer submitRenderer = new IacucProtocolNotificationRenderer(protocol);
    IacucProtocolNotificationContext submitContext = new IacucProtocolNotificationContext(protocol, null, 
                                                IacucProtocolActionType.SUBMITTED_TO_IACUC, "Submit", submitRenderer);
    getNotificationService().sendNotificationAndPersist(submitContext, new IacucProtocolNotification(), protocol);
    return routeProtocolToHoldingPage(mapping, protocolForm);
}
    
        
    private ActionForward routeProtocolToHoldingPage(ActionMapping mapping, ProtocolFormBase protocolForm) {
        String routeHeaderId = protocolForm.getProtocolDocument().getDocumentNumber();
        String returnLocation = buildActionUrl(routeHeaderId, Constants.MAPPING_IACUC_PROTOCOL_ACTIONS, "IacucProtocolDocument");
        
        ActionForward basicForward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
        ActionForward holdingPageForward = mapping.findForward(Constants.MAPPING_HOLDING_PAGE);
        return routeToHoldingPage(basicForward, basicForward, holdingPageForward, returnLocation);
    }
    
    
    
    /**
     * Administratively mark incomplete a previously submitted protocol.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward administrativelyMarkIncompleteProtocol(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocol protocol = protocolForm.getIacucProtocolDocument().getIacucProtocol();
        IacucProtocolTask task = new IacucProtocolTask(TaskName.ADMIN_INCOMPLETE_PROTOCOL, protocol);
        
        if (!hasDocumentStateChanged(protocolForm)) {
            if (isAuthorized(task)) {
                ProtocolDocumentBase pd = getProtocolWithdrawService().administrativelyMarkIncomplete(protocol, protocolForm.getActionHelper().getProtocolAdminIncompleteBean());
    
                protocolForm.setDocId(pd.getDocumentNumber());
                loadDocument(protocolForm);
                protocolForm.getProtocolHelper().prepareView();
                
                IacucProtocolNotificationRequestBean newNotificationBean = new IacucProtocolNotificationRequestBean(protocol, IacucProtocolActionType.ADMINISTRATIVELY_INCOMPLETE, "Administratively Marked Incomplete");
                ProtocolCorrespondence newProtocolCorrespondence = getProtocolCorrespondence(protocolForm, PROTOCOL_TAB, newNotificationBean, false);
                protocolForm.getActionHelper().setProtocolCorrespondence(newProtocolCorrespondence);
                recordProtocolActionSuccess("Administratively Mark Incomplete");
                
                if (newProtocolCorrespondence != null) {
                    return mapping.findForward(CORRESPONDENCE);
                } else {
                    return checkToSendNotification(mapping, mapping.findForward(PROTOCOL_TAB), protocolForm, newNotificationBean);
                }
            }
        } else {
            GlobalVariables.getMessageMap().clearErrorMessages();
            GlobalVariables.getMessageMap().putError("documentstatechanged", KeyConstants.ERROR_PROTOCOL_DOCUMENT_STATE_CHANGED,  new String[] {}); 
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    
    
    /**
     * Administratively withdraw a previously submitted protocol.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward administrativelyWithdrawProtocol(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocol protocol = protocolForm.getIacucProtocolDocument().getIacucProtocol();
        IacucProtocolTask task = new IacucProtocolTask(TaskName.ADMIN_WITHDRAW_PROTOCOL, protocol);
        
        if (!hasDocumentStateChanged(protocolForm)) {
            if (isAuthorized(task)) {
                ProtocolDocumentBase pd = getProtocolWithdrawService().administrativelyWithdraw(protocol, protocolForm.getActionHelper().getProtocolAdminWithdrawBean());
    
                protocolForm.setDocId(pd.getDocumentNumber());
                loadDocument(protocolForm);
                protocolForm.getProtocolHelper().prepareView();
                
                IacucProtocolNotificationRequestBean newNotificationBean = new IacucProtocolNotificationRequestBean(protocol, IacucProtocolActionType.ADMINISTRATIVELY_WITHDRAWN, "Administratively Withdrawn");
                ProtocolCorrespondence newProtocolCorrespondence = getProtocolCorrespondence(protocolForm, PROTOCOL_TAB, newNotificationBean, false);
                protocolForm.getActionHelper().setProtocolCorrespondence(newProtocolCorrespondence);
                recordProtocolActionSuccess("Administratively Withdraw");
                
                if (newProtocolCorrespondence != null) {
                    return mapping.findForward(CORRESPONDENCE);
                } else {
                    return checkToSendNotification(mapping, mapping.findForward(PROTOCOL_TAB), protocolForm, newNotificationBean);
                }
            }
        } else {
            GlobalVariables.getMessageMap().clearErrorMessages();
            GlobalVariables.getMessageMap().putError("documentstatechanged", KeyConstants.ERROR_PROTOCOL_DOCUMENT_STATE_CHANGED,  new String[] {}); 
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    
    
    /**
     * Withdraw a previously submitted protocol.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward withdrawProtocol(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocol protocol = protocolForm.getIacucProtocolDocument().getIacucProtocol();
        IacucProtocolTask task = new IacucProtocolTask(TaskName.PROTOCOL_WITHDRAW, protocol);
        
        if (!hasDocumentStateChanged(protocolForm)) {
            if (isAuthorized(task)) {
                ProtocolDocumentBase pd = getProtocolWithdrawService().withdraw(protocol, protocolForm.getActionHelper().getProtocolWithdrawBean());
    
                protocolForm.setDocId(pd.getDocumentNumber());
                loadDocument(protocolForm);
                protocolForm.getProtocolHelper().prepareView();
                
                IacucProtocolNotificationRequestBean newNotificationBean = new IacucProtocolNotificationRequestBean(protocol, IacucProtocolActionType.IACUC_WITHDRAWN, "Withdrawn");
                ProtocolCorrespondence newProtocolCorrespondence = getProtocolCorrespondence(protocolForm, PROTOCOL_TAB, newNotificationBean, false);
                protocolForm.getActionHelper().setProtocolCorrespondence(newProtocolCorrespondence);
                recordProtocolActionSuccess("Withdraw");
                
                if (newProtocolCorrespondence != null) {
                    return mapping.findForward(CORRESPONDENCE);
                } else {
                    return checkToSendNotification(mapping, mapping.findForward(PROTOCOL_TAB), protocolForm, newNotificationBean);
                }
            }
        } else {
            GlobalVariables.getMessageMap().clearErrorMessages();
            GlobalVariables.getMessageMap().putError("documentstatechanged", KeyConstants.ERROR_PROTOCOL_DOCUMENT_STATE_CHANGED,  new String[] {}); 
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    private IacucProtocolCorrespondence getProtocolCorrespondence (ProtocolFormBase protocolForm, String forwardName, ProtocolNotificationRequestBeanBase notificationRequestBean, boolean holdingPage) {
        boolean result = false;
        
        Map<String,Object> keyValues = new HashMap<String, Object>();
        keyValues.put("actionIdFk", protocolForm.getProtocolDocument().getProtocol().getLastProtocolAction().getProtocolActionId());
        List<IacucProtocolCorrespondence> correspondences = (List<IacucProtocolCorrespondence>)getBusinessObjectService().findMatching(IacucProtocolCorrespondence.class, keyValues);
        if (correspondences.isEmpty()) {
            return null;
        } else {
            IacucProtocolCorrespondence correspondence = correspondences.get(0);
            correspondence.setForwardName(forwardName);
            correspondence.setNotificationRequestBean(notificationRequestBean);
            correspondence.setHoldingPage(holdingPage);
            return correspondence;
            
        }
    }
//
    /**
     * Notify the IACUC office.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward notifyIacucProtocol(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucActionHelper actionHelper = (IacucActionHelper) protocolForm.getActionHelper();
        actionHelper.getIacucProtocolNotifyIacucBean().setAnswerHeaders(getAnswerHeaders(form, IacucProtocolActionType.NOTIFY_IACUC));
        if (isMandatoryQuestionnaireComplete(actionHelper.getIacucProtocolNotifyIacucBean(), "actionHelper.protocolNotifyIacucBean.datavalidation")) {
            getIacucProtocolNotifyIacucService().submitIacucNotification((IacucProtocol)protocolForm.getProtocolDocument().getProtocol(),
                    actionHelper.getIacucProtocolNotifyIacucBean());
            protocolForm.getQuestionnaireHelper().setAnswerHeaders(new ArrayList<AnswerHeader>());
            LOG.info("notifyIacucProtocol " + protocolForm.getProtocolDocument().getDocumentNumber());

            recordProtocolActionSuccess("Notify IACUC");
            return checkToSendNotification(mapping, mapping.findForward(PROTOCOL_ACTIONS_TAB), protocolForm, new IacucProtocolNotificationRequestBean((IacucProtocol)protocolForm.getProtocolDocument().getProtocol(),IacucProtocolActionType.NOTIFY_IACUC, "Notify IACUC"));
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    private IacucProtocolNotifyIacucService getIacucProtocolNotifyIacucService() {
        return KraServiceLocator.getService(IacucProtocolNotifyIacucService.class);
    }    

    /*
     * get the saved answer headers
     */
    private List<AnswerHeader> getAnswerHeaders(ActionForm form, String actionTypeCode) {
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        ModuleQuestionnaireBean moduleQuestionnaireBean = new IacucProtocolModuleQuestionnaireBean(CoeusModule.IACUC_PROTOCOL_MODULE_CODE, protocolForm.getProtocolDocument().getProtocol().getProtocolNumber() + "T", CoeusSubModule.PROTOCOL_SUBMISSION, actionTypeCode, false);
        return getQuestionnaireAnswerService().getQuestionnaireAnswer(moduleQuestionnaireBean);

    }
    
    /*
     * check if the mandatory submission questionnaire is complete before submit a request/notify IACUC action
     */
    private boolean isMandatoryQuestionnaireComplete(IacucProtocolSubmissionBeanBase submissionBean, String errorKey) {
        boolean valid = true;
        IacucProtocolQuestionnaireAuditRule auditRule = new IacucProtocolQuestionnaireAuditRule();
        if (!auditRule.isMandatorySubmissionQuestionnaireComplete(submissionBean.getAnswerHeaders())) {
            GlobalVariables.getMessageMap().clearErrorMessages();
            GlobalVariables.getMessageMap().putError(errorKey, KeyConstants.ERROR_MANDATORY_QUESTIONNAIRE);
            valid = false;
        }
        return valid;
    }
    
    
    /**
     * Create an Amendment.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward createAmendment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolDocument protocolDocument = protocolForm.getIacucProtocolDocument();
        IacucProtocol protocol = protocolForm.getIacucProtocolDocument().getIacucProtocol();
        IacucProtocolTask task = new IacucProtocolTask(TaskName.CREATE_IACUC_PROTOCOL_AMENDMENT, protocol);
        IacucActionHelper actionHelper = (IacucActionHelper) protocolForm.getActionHelper();
        if (isAuthorized(task)) {
            if (!applyRules(new CreateIacucAmendmentEvent(protocolDocument, Constants.PROTOCOL_CREATE_AMENDMENT_KEY,
                    actionHelper.getProtocolAmendmentBean()))) {
                return mapping.findForward(Constants.MAPPING_BASIC);
            }

            String newDocId = getProtocolAmendRenewService().createAmendment(protocolForm.getProtocolDocument(),
                    protocolForm.getActionHelper().getProtocolAmendmentBean());
            // Switch over to the new protocol document and
            // go to the ProtocolBase tab web page.

            protocolForm.setDocId(newDocId);
            loadDocument(protocolForm);
            protocolForm.getActionHelper().setCurrentSubmissionNumber(-1);
            protocolForm.getProtocolHelper().prepareView();
            
            recordProtocolActionSuccess("Create Amendment");

            IacucProtocolNotificationRequestBean notificationBean = new IacucProtocolNotificationRequestBean(protocol, IacucProtocolActionType.AMENDMENT_CREATED_NOTIFICATION, "Amendment Created");
            protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, PROTOCOL_TAB, notificationBean, false));
            if (protocolForm.getActionHelper().getProtocolCorrespondence() != null) {
                return mapping.findForward(CORRESPONDENCE);
            } else {
                return checkToSendNotification(mapping, mapping.findForward(PROTOCOL_TAB), protocolForm, notificationBean);
            }
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    

    /**
     * Modify an Amendment.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward modifyAmendmentSections(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolDocument protocolDocument = protocolForm.getIacucProtocolDocument();
        IacucProtocol protocol = protocolDocument.getIacucProtocol();
        protocolForm.getActionHelper().setCurrentTask(TaskName.MODIFY_PROTOCOL_AMMENDMENT_SECTIONS);
        IacucProtocolTask task = new IacucProtocolTask(TaskName.MODIFY_PROTOCOL_AMMENDMENT_SECTIONS, protocol);
        if (isAuthorized(task)) {
            if (!applyRules(new ModifyIacucAmendmentSectionsEvent(protocolDocument, Constants.PROTOCOL_MODIFY_AMENDMENT_KEY,
                protocolForm.getActionHelper().getProtocolAmendmentBean()))) {
                return mapping.findForward(Constants.MAPPING_BASIC);
            }
        
            getProtocolAmendRenewService().updateAmendmentRenewal(protocolForm.getProtocolDocument(), 
                    protocolForm.getActionHelper().getProtocolAmendmentBean());
            
            return save(mapping, protocolForm, request, response);
        }
            
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    
    /**
     * Create a Renewal without an Amendment.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward createRenewal(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolDocument protocolDocument = protocolForm.getIacucProtocolDocument();
        IacucProtocol protocol = protocolForm.getIacucProtocolDocument().getIacucProtocol();
        IacucProtocolTask task = new IacucProtocolTask(TaskName.CREATE_IACUC_PROTOCOL_RENEWAL, protocol);
        if (isAuthorized(task)) {
            if (!applyRules(new CreateIacucRenewalEvent(protocolDocument,
                    Constants.PROTOCOL_CREATE_RENEWAL_SUMMARY_KEY, protocolForm.getActionHelper().getRenewalSummary()))) {
                    return mapping.findForward(Constants.MAPPING_BASIC);
                }
            String newDocId = getProtocolAmendRenewService().createRenewal(protocolForm.getProtocolDocument(),((ProtocolFormBase) form).getActionHelper().getRenewalSummary());
            // Switch over to the new protocol document and
            // go to the ProtocolBase tab web page.

            protocolForm.setDocId(newDocId);
            loadDocument(protocolForm);

            protocolForm.getActionHelper().setCurrentSubmissionNumber(-1);
            protocolForm.getProtocolHelper().prepareView();
            
            recordProtocolActionSuccess("Create Renewal without Amendment");
            
            // Form fields copy needed to support modifyAmendmentSections
            protocolForm.getActionHelper().getProtocolAmendmentBean().setSummary(protocolForm.getActionHelper().getRenewalSummary());

            IacucProtocolNotificationRequestBean notificationBean = new IacucProtocolNotificationRequestBean(protocol, IacucProtocolActionType.RENEWAL_CREATED_NOTIFICATION, "Renewal Created");
            protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, PROTOCOL_TAB, notificationBean, false));
            if (protocolForm.getActionHelper().getProtocolCorrespondence() != null) {
                return mapping.findForward(CORRESPONDENCE);
            } else {
                return checkToSendNotification(mapping, mapping.findForward(PROTOCOL_TAB), protocolForm, notificationBean);
            }
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * Create a Renewal with an Amendment.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward createRenewalWithAmendment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolDocument protocolDocument = protocolForm.getIacucProtocolDocument();
        IacucProtocol protocol = protocolForm.getIacucProtocolDocument().getIacucProtocol();
        IacucProtocolTask task = new IacucProtocolTask(TaskName.CREATE_IACUC_PROTOCOL_RENEWAL, protocol);
        IacucActionHelper actionHelper = (IacucActionHelper) protocolForm.getActionHelper();
        IacucProtocolAmendmentBean renewAmendmentBean = (IacucProtocolAmendmentBean)actionHelper.getProtocolRenewAmendmentBean();
        if (isAuthorized(task)) {
            if (!applyRules(new CreateIacucAmendmentEvent(protocolDocument, Constants.PROTOCOL_CREATE_RENEWAL_WITH_AMENDMENT_KEY,
                    renewAmendmentBean))) {
                return mapping.findForward(Constants.MAPPING_BASIC);
            }

            String newDocId = getProtocolAmendRenewService().createRenewalWithAmendment(protocolDocument,
                    renewAmendmentBean);
            // Switch over to the new protocol document and
            // go to the ProtocolBase tab web page.

            protocolForm.setDocId(newDocId);
            loadDocument(protocolForm);

            protocolForm.getActionHelper().setCurrentSubmissionNumber(-1);
            protocolForm.getProtocolHelper().prepareView();
            
            recordProtocolActionSuccess("Create Renewal with Amendment");
            
            // Form fields copy needed to support modifyAmendmentSections
            protocolForm.getActionHelper().setProtocolAmendmentBean(renewAmendmentBean);

            IacucProtocolNotificationRequestBean notificationBean = new IacucProtocolNotificationRequestBean(protocol, IacucProtocolActionType.RENEWAL_WITH_AMENDMENT_CREATED, "Renewal With Amendment Created");
            protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, PROTOCOL_TAB, notificationBean, false));
            if (protocolForm.getActionHelper().getProtocolCorrespondence() != null) {
                return mapping.findForward(CORRESPONDENCE);
            } else {
                return checkToSendNotification(mapping, mapping.findForward(PROTOCOL_TAB), protocolForm, notificationBean);
            }
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Create a Continuation without an Amendment.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward createContinuation(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolDocument protocolDocument = protocolForm.getIacucProtocolDocument();
        IacucProtocol protocol = protocolForm.getIacucProtocolDocument().getIacucProtocol();
        IacucProtocolTask task = new IacucProtocolTask(TaskName.CREATE_IACUC_PROTOCOL_CONTINUATION, protocol);
        IacucActionHelper actionHelper = (IacucActionHelper)protocolForm.getActionHelper();
        if (isAuthorized(task)) {
            if (!applyRules(new CreateIacucContinuationEvent(protocolDocument,
                    Constants.PROTOCOL_CREATE_CONTINUATION_SUMMARY_KEY, actionHelper.getContinuationSummary()))) {
                    return mapping.findForward(Constants.MAPPING_BASIC);
                }
            String newDocId = getProtocolAmendRenewService().createContinuation(protocolDocument, actionHelper.getContinuationSummary());
            // Switch over to the new protocol document and
            // go to the ProtocolBase tab web page.

            protocolForm.setDocId(newDocId);
            loadDocument(protocolForm);

            protocolForm.getActionHelper().setCurrentSubmissionNumber(-1);
            protocolForm.getProtocolHelper().prepareView();
            
            recordProtocolActionSuccess("Create Continuation without Amendment");
            
            // Form fields copy needed to support modifyAmendmentSections
            protocolForm.getActionHelper().getProtocolAmendmentBean().setSummary(actionHelper.getContinuationSummary());

            IacucProtocolNotificationRequestBean notificationBean = new IacucProtocolNotificationRequestBean(protocol, IacucProtocolActionType.CONTINUATION_CREATED_NOTIFICATION, "Continuation Created");
            protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, PROTOCOL_TAB, notificationBean, false));
            if (protocolForm.getActionHelper().getProtocolCorrespondence() != null) {
                return mapping.findForward(CORRESPONDENCE);
            } else {
                return checkToSendNotification(mapping, mapping.findForward(PROTOCOL_TAB), protocolForm, notificationBean);
            }
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Create a Continuation with an Amendment.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward createContinuationWithAmendment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolDocument protocolDocument = protocolForm.getIacucProtocolDocument();
        IacucProtocol protocol = protocolForm.getIacucProtocolDocument().getIacucProtocol();
        IacucActionHelper actionHelper = (IacucActionHelper)protocolForm.getActionHelper();
        IacucProtocolTask task = new IacucProtocolTask(TaskName.CREATE_IACUC_PROTOCOL_CONTINUATION, protocol);
        IacucProtocolAmendmentBean continuationAmendmentBean = (IacucProtocolAmendmentBean)actionHelper.getProtocolContinuationAmendmentBean();
        if (isAuthorized(task)) {
            if (!applyRules(new CreateIacucAmendmentEvent(protocolDocument,
                Constants.PROTOCOL_CREATE_CONTINUATION_WITH_AMENDMENT_KEY, continuationAmendmentBean))) {
                return mapping.findForward(Constants.MAPPING_BASIC);
            }

            String newDocId = getProtocolAmendRenewService().createContinuationWithAmendment(protocolDocument,
                    continuationAmendmentBean);
            // Switch over to the new protocol document and
            // go to the ProtocolBase tab web page.

            protocolForm.setDocId(newDocId);
            loadDocument(protocolForm);

            protocolForm.getActionHelper().setCurrentSubmissionNumber(-1);
            protocolForm.getProtocolHelper().prepareView();
            
            recordProtocolActionSuccess("Create Continuation with Amendment");
            
            // Form fields copy needed to support modifyAmendmentSections
            protocolForm.getActionHelper().setProtocolAmendmentBean(continuationAmendmentBean);

            IacucProtocolNotificationRequestBean notificationBean = new IacucProtocolNotificationRequestBean(protocol, IacucProtocolActionType.CONTINUATION_AMENDMENT, "Continuation With Amendment Created");
            protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, PROTOCOL_TAB, notificationBean, false));
            if (protocolForm.getActionHelper().getProtocolCorrespondence() != null) {
                return mapping.findForward(CORRESPONDENCE);
            } else {
                return checkToSendNotification(mapping, mapping.findForward(PROTOCOL_TAB), protocolForm, notificationBean);
            }
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Delete a ProtocolBase/Amendment/Renewal. Remember that amendments and renewals are simply protocol documents that were copied
     * from a protocol.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteProtocol(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        IacucProtocolTask task = new IacucProtocolTask(TaskName.PROTOCOL_AMEND_RENEW_DELETE, (IacucProtocol) protocolForm.getProtocolDocument().getProtocol());
        if (isAuthorized(task)) {
            return confirm(buildDeleteProtocolConfirmationQuestion(mapping, form, request, response), 
                           CONFIRM_DELETE_PROTOCOL_KEY,
                           "");
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * If the user confirms the deletion, then delete the protocol.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward confirmDeleteProtocol(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        if (CONFIRM_DELETE_PROTOCOL_KEY.equals(question)) {
            ProtocolFormBase protocolForm = (ProtocolFormBase) form;
            IacucProtocol protocol = (IacucProtocol)protocolForm.getProtocolDocument().getProtocol();
            getProtocolDeleteService().delete(protocol, protocolForm.getActionHelper().getProtocolDeleteBean());
            
            // send out notification that protocol has been deleted and record success
            IacucProtocolNotificationRequestBean newNotificationBean = new IacucProtocolNotificationRequestBean(protocol, IacucProtocolActionType.IACUC_DELETED, "Deleted");
            ProtocolCorrespondence newProtocolCorrespondence = getProtocolCorrespondence(protocolForm, PROTOCOL_ACTIONS_TAB, newNotificationBean, false);
            protocolForm.getActionHelper().setProtocolCorrespondence(newProtocolCorrespondence);
            recordProtocolActionSuccess("Delete ProtocolBase, Amendment, or Renewal");
            
            if (newProtocolCorrespondence != null) {
                return mapping.findForward(CORRESPONDENCE);
            } else {
                return checkToSendNotification(mapping, mapping.findForward(PROTOCOL_TAB), protocolForm, newNotificationBean);
            }
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * Build the question to ask the user. Ask if they really want to delete the protocol.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    private StrutsConfirmation buildDeleteProtocolConfirmationQuestion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolDocumentBase doc = ((ProtocolFormBase) form).getProtocolDocument();
        String protocolNumber = doc.getProtocol().getProtocolNumber();
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_DELETE_PROTOCOL_KEY,
                KeyConstants.QUESTION_DELETE_PROTOCOL_CONFIRMATION, protocolNumber);
    }


    /**
     * 
     * This method is to view protocol attachment at protocol actions/print
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward viewProtocolAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocol protocol = protocolForm.getIacucProtocolDocument().getIacucProtocol();
        int selected = getSelectedLine(request);
        IacucProtocolAttachmentProtocol attachment = (IacucProtocolAttachmentProtocol)protocol.getActiveAttachmentProtocolsNoDelete().get(selected);
        return printAttachmentProtocol(mapping, response, attachment,protocolForm);
    }
    
    /**
     * 
     * This method is for 'view' personnel attachment. lost when merging from 3.0 to trunk
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward viewProtocolPersonnelAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        int selected = getSelectedLine(request);
        IacucProtocolAttachmentPersonnel personAttach = (IacucProtocolAttachmentPersonnel)protocolForm.getProtocolDocument().getProtocol().getAttachmentPersonnels().get(selected);
        return printPersonnelAttachmentProtocol(mapping, response, personAttach,protocolForm);

    }

    public ActionForward printProtocolQuestionnaires(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocol protocol = protocolForm.getIacucProtocolDocument().getIacucProtocol();
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        String fileName = "Protocol_questionnaire_Report.pdf";
        String reportName = protocol.getProtocolNumber() + "-" + "ProtocolQuestionnaires";
        AttachmentDataSource dataStream = getProtocolPrintingService().print(reportName, getIacucQuestionnairePrintingService().getQuestionnairePrintable(protocol, protocolForm.getActionHelper().getQuestionnairesToPrints()));
        if (dataStream.getContent() != null) {
            dataStream.setFileName(fileName.toString());
            PrintingUtils.streamToResponse(dataStream, response);
            forward = null;
        }
        return forward;
    }
    
    protected IacucQuestionnairePrintingService getIacucQuestionnairePrintingService() {
        return KraServiceLocator.getService(IacucQuestionnairePrintingService.class);
    }
    
    /*
     * This is to view attachment if attachment is selected in print panel.
     */
    private ActionForward printAttachmentProtocol(ActionMapping mapping, HttpServletResponse response, IacucProtocolAttachmentProtocol attachment,ProtocolFormBase form) throws Exception {
        if (attachment == null) {
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
        final AttachmentFile file = attachment.getFile();
        byte[] attachmentFile =null;
        String attachmentFileType=file.getType().replace("\"", "");
        attachmentFileType=attachmentFileType.replace("\\", "");           
        if(attachmentFileType.equalsIgnoreCase(WatermarkConstants.ATTACHMENT_TYPE_PDF)){
            attachmentFile=getProtocolAttachmentFile(form,attachment);
            if(attachmentFile!=null) {          
                this.streamToResponse(attachmentFile, getValidHeaderString(file.getName()), getValidHeaderString(file.getType()), response);    
            } else {
                this.streamToResponse(file.getData(), getValidHeaderString(file.getName()), getValidHeaderString(file.getType()), response);    
            }
            return RESPONSE_ALREADY_HANDLED;
        }
        this.streamToResponse(file.getData(), getValidHeaderString(file.getName()), getValidHeaderString(file.getType()), response);
        return RESPONSE_ALREADY_HANDLED;
    }

    /*
     * This is to view Personnel attachment if attachment is selected in print & summary panel.
     */
    private ActionForward printPersonnelAttachmentProtocol(ActionMapping mapping, HttpServletResponse response, ProtocolAttachmentBase attachment,IacucProtocolForm form) throws Exception {

        if (attachment == null) {
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
        final AttachmentFile file = attachment.getFile();
        this.streamToResponse(file.getData(), getValidHeaderString(file.getName()), getValidHeaderString(file.getType()), response);
        return RESPONSE_ALREADY_HANDLED;
    }
    
    /**
     * 
     * This method for applying the selected watermark to the attachment 
     * @param protocolForm form
     * @param protocolAttachmentBase attachment
     * @return attachment file
     */
    private byte[] getProtocolAttachmentFile(ProtocolFormBase form,ProtocolAttachmentProtocolBase attachment) {
        
        byte[] attachmentFile =null;
        final AttachmentFile file = attachment.getFile();
        Printable printableArtifacts= getProtocolPrintingService().getProtocolPrintArtifacts(form.getProtocolDocument().getProtocol());
        ProtocolBase protocolCurrent = form.getProtocolDocument().getProtocol();
        int currentProtoSeqNumber= protocolCurrent.getSequenceNumber();
        try {
            if(printableArtifacts.isWatermarkEnabled()){
                int currentAttachmentSequence=attachment.getSequenceNumber();
                String docStatusCode=attachment.getDocumentStatusCode();
                String statusCode=attachment.getStatusCode();
                // TODO perhaps the check for equality of protocol and attachment sequence numbers, below, is now redundant
                if(((getProtocolAttachmentService().isAttachmentActive(attachment))&&(currentProtoSeqNumber == currentAttachmentSequence))||(docStatusCode.equals("1"))){
                    if (ProtocolAttachmentProtocolBase.COMPLETE_STATUS_CODE.equals(statusCode)) {
                        attachmentFile = getWatermarkService().applyWatermark(file.getData(),printableArtifacts.getWatermarkable().getWatermark());
                    }
                }else{
                    attachmentFile = getWatermarkService().applyWatermark(file.getData(),printableArtifacts.getWatermarkable().getInvalidWatermark());
                    LOG.info(INVALID_ATTACHMENT + attachment.getDocumentId());
                }
            }
        }catch (Exception e) {
            LOG.error("Exception Occured in ProtocolNoteAndAttachmentAction. : ",e);    
        }        
        return attachmentFile;
    }
    
    
    
    /**
     * Filters the actions shown in the History sub-panel, first validating the dates before filtering and refreshing the page.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward filterHistory(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        Date startDate = protocolForm.getActionHelper().getFilteredHistoryStartDate();
        Date endDate = protocolForm.getActionHelper().getFilteredHistoryEndDate();
        
        if (applyRules(new ProtocolHistoryFilterDatesEvent(protocolForm.getProtocolDocument(), startDate, endDate))) {
            protocolForm.getActionHelper().initFilterDatesView();
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Shows all of the actions in the History sub-panel.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward resetHistory(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        protocolForm.getActionHelper().setFilteredHistoryStartDate(null);
        protocolForm.getActionHelper().setFilteredHistoryEndDate(null);
        protocolForm.getActionHelper().initFilterDatesView();
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * Load a ProtocolBase summary into the summary sub-panel. The protocol summary to load corresponds to the currently selected
     * protocol action in the History sub-panel.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward loadProtocolSummary(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        org.kuali.kra.iacuc.actions.IacucProtocolAction action = (org.kuali.kra.iacuc.actions.IacucProtocolAction)protocolForm.getActionHelper().getSelectedProtocolAction();
        if (action != null) {
            protocolForm.getActionHelper().setCurrentSequenceNumber(action.getSequenceNumber());
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward viewAttachmentProtocol(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return this.viewAttachment(mapping, (ProtocolFormBase) form, request, response);
    }
    
    private ActionForward viewAttachment(ActionMapping mapping, ProtocolFormBase form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        final int selection = this.getSelectedLine(request);
        
        ProtocolSummary protocolSummary = form.getActionHelper().getProtocolSummary();
        
        int selectedIndex = getSelectedLine(request);
        AttachmentSummary attachmentSummary = protocolSummary.getAttachments().get(selectedIndex);
        
        if (attachmentSummary.getAttachmentType().startsWith("ProtocolBase: ")) {
            IacucProtocolAttachmentProtocol attachment = getProtocolAttachmentService().getAttachment(IacucProtocolAttachmentProtocol.class, attachmentSummary.getAttachmentId());
            return printAttachmentProtocol(mapping, response, attachment, form);
        } 
        else {
            IacucProtocolAttachmentPersonnel personnelAttachment = getProtocolAttachmentService().getAttachment(IacucProtocolAttachmentPersonnel.class, attachmentSummary.getAttachmentId());
            return printPersonnelAttachmentProtocol(mapping, response, personnelAttachment, (IacucProtocolForm)form);
        }

    }  
       

    /**
     * Go to the previous summary.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward viewPreviousProtocolSummary(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucActionHelper actionHelper = (IacucActionHelper) protocolForm.getActionHelper();
        actionHelper.setCurrentSequenceNumber(actionHelper.getCurrentSequenceNumber() - 1);
        ((ProtocolFormBase) form).getActionHelper().initSummaryDetails();

        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * Go to the next summary.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward viewNextProtocolSummary(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        IacucActionHelper actionHelper = (IacucActionHelper) protocolForm.getActionHelper();
        actionHelper.setCurrentSequenceNumber(actionHelper.getCurrentSequenceNumber() + 1);
        ((ProtocolFormBase) form).getActionHelper().initSummaryDetails();
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * 
     * This method to load previous submission for display
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward viewPreviousSubmission (ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        IacucActionHelper actionHelper = (IacucActionHelper) protocolForm.getActionHelper();
        actionHelper.setCurrentSubmissionNumber(actionHelper.getPrevSubmissionNumber());
        actionHelper.setAmendmentDetails();
        protocolForm.getActionHelper().initSubmissionDetails();
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     * This method is to load next submission for display
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward viewNextSubmission(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        IacucActionHelper actionHelper = (IacucActionHelper) protocolForm.getActionHelper();
        actionHelper.setCurrentSubmissionNumber(actionHelper.getNextSubmissionNumber());
        actionHelper.setAmendmentDetails();
        protocolForm.getActionHelper().initSubmissionDetails();
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * 
     * This method is to render protocol action page when 'view' is clicked in meeting page, ProtocolBase submitted panel.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward start(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(SUBMISSION_ID, request.getParameter(SUBMISSION_ID));
        IacucProtocolSubmission protocolSubmission = (IacucProtocolSubmission) getBusinessObjectService().findByPrimaryKey(IacucProtocolSubmission.class, fieldValues);
        protocolSubmission.getProtocol().setProtocolSubmission(protocolSubmission);
        
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        protocolForm.setDocId(protocolSubmission.getProtocol().getProtocolDocument().getDocumentNumber());
        loadDocument(protocolForm);
        protocolForm.initialize();
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    
    
    /**
     * 
     * This method... 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward assignToAgenda(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        // set the task name to prevent entered data from being overwritten (in case of user errors) due to bean refresh in the action helper's prepare view 
        protocolForm.getActionHelper().setCurrentTask(TaskName.ASSIGN_TO_AGENDA);
        IacucProtocol protocol = (IacucProtocol) protocolForm.getProtocolDocument().getProtocol();
       
        if (!hasDocumentStateChanged(protocolForm)) {
            ProtocolTaskBase task = new IacucProtocolTask(TaskName.ASSIGN_TO_AGENDA, protocol);
            if (isAuthorized(task)) {
                IacucProtocolAssignToAgendaBean actionBean = (IacucProtocolAssignToAgendaBean) protocolForm.getActionHelper().getAssignToAgendaBean();
                if (applyRules(new IacucProtocolAssignToAgendaEvent((IacucProtocolDocument) protocolForm.getProtocolDocument(), actionBean))) {               
                    getProtocolAssignToAgendaService().assignToAgenda(protocol, actionBean);
                    saveReviewComments(protocolForm, (IacucReviewCommentsBean) actionBean.getReviewCommentsBean());
                    recordProtocolActionSuccess("Assign to Agenda");
                    
                    ProtocolActionBase lastAction = protocolForm.getProtocolDocument().getProtocol().getLastProtocolAction();
                    ProtocolActionTypeBase lastActionType = lastAction.getProtocolActionType();
                    String description = lastActionType.getDescription();
                    IacucProtocolNotificationRenderer renderer = new IacucProtocolNotificationRenderer(protocol);
                    IacucProtocolNotificationContext context = new IacucProtocolNotificationContext(protocol, IacucProtocolActionType.ASSIGNED_TO_AGENDA, description, renderer);
                    
                    if (protocolForm.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
                        protocolForm.getNotificationHelper().initializeDefaultValues(context);
                        forward = mapping.findForward("iacucProtocolNotificationEditor");
                    } else {
                        getNotificationService().sendNotificationAndPersist(context, new IacucProtocolNotification(), protocol);
                    }
                }
                actionBean.prepareView();
            }
        } else {
            GlobalVariables.getMessageMap().clearErrorMessages();
            GlobalVariables.getMessageMap().putError("documentstatechanged", KeyConstants.ERROR_PROTOCOL_DOCUMENT_STATE_CHANGED,  new String[] {}); 
        }
        
        return forward;
    }
    
    public ActionForward removeFromAgenda(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocol protocol = (IacucProtocol) protocolForm.getProtocolDocument().getProtocol();
        IacucActionHelper actionHelper = (IacucActionHelper)protocolForm.getActionHelper();
        
        if (!hasDocumentStateChanged(protocolForm)) {
            ProtocolTaskBase task = new IacucProtocolTask(TaskName.REMOVE_FROM_AGENDA, protocol);
            if (isAuthorized(task)) {
                IacucProtocolGenericActionBean actionBean = actionHelper.getIacucProtocolRemoveFromAgendaBean();
                getProtocolAssignToAgendaService().removeFromAgenda(protocol, actionBean);
                saveReviewComments(protocolForm, (IacucReviewCommentsBean) actionBean.getReviewCommentsBean());
                recordProtocolActionSuccess("Removed Agenda");
            
                IacucProtocolGenericActionNotificationRenderer renderer = new IacucProtocolGenericActionNotificationRenderer(protocol,actionBean.getActionDate());
                IacucProtocolNotificationContext context = new IacucProtocolNotificationContext(protocol, IacucProtocolActionType.REMOVE_FROM_AGENDA, actionBean.getComments(), renderer);
                if (protocolForm.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
                    protocolForm.getNotificationHelper().initializeDefaultValues(context);
                    forward = mapping.findForward("iacucProtocolNotificationEditor");
                } else {
                    getNotificationService().sendNotificationAndPersist(context, new IacucProtocolNotification(), protocol);
                }
            }
        } else {
            GlobalVariables.getMessageMap().clearErrorMessages();
            GlobalVariables.getMessageMap().putError("documentstatechanged", KeyConstants.ERROR_PROTOCOL_DOCUMENT_STATE_CHANGED,  new String[] {}); 
        }
        
        return forward;
    }

    
    
    public ActionForward protocolReviewNotRequired(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolDocument document = protocolForm.getIacucProtocolDocument();
        IacucProtocol protocol = document.getIacucProtocol();
        IacucProtocolTask task = new IacucProtocolTask(TaskName.REVIEW_NOT_REQUIRED_IACUC_PROTOCOL, protocol);
        if (isAuthorized(task)) {
            IacucActionHelper actionHelper = (IacucActionHelper)protocolForm.getActionHelper();
            IacucProtocolReviewNotRequiredBean actionBean = (IacucProtocolReviewNotRequiredBean) actionHelper.getProtocolReviewNotRequiredBean();
            if (applyRules(new IacucProtocolReviewNotRequiredEvent(document, actionBean))) {
                KraServiceLocator.getService(IacucProtocolReviewNotRequiredService.class).reviewNotRequired(document, actionBean);
            
                recordProtocolActionSuccess("Review Not Required");
                IacucProtocolNotificationRequestBean notificationBean = new IacucProtocolNotificationRequestBean(document.getIacucProtocol(), IacucProtocolActionType.IACUC_REVIEW_NOT_REQUIRED, "Review Not Required");
                protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, PROTOCOL_TAB, notificationBean, false));
                if (protocolForm.getActionHelper().getProtocolCorrespondence() != null) {
                    return mapping.findForward(CORRESPONDENCE);
                } else {
                    return checkToSendNotification(mapping, mapping.findForward(PROTOCOL_TAB), protocolForm, notificationBean);
                }
            }
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Perform Full Approve Action - maps to IACUCReview RouteNode.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward grantFullApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {
        
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolDocument document = (IacucProtocolDocument) protocolForm.getProtocolDocument();
        IacucProtocol protocol = document.getIacucProtocol();
        IacucProtocolApproveBean actionBean = (IacucProtocolApproveBean) protocolForm.getActionHelper().getProtocolFullApprovalBean();
        
        if (hasPermission(TaskName.APPROVE_PROTOCOL, (IacucProtocol) document.getProtocol())) {
            if (applyRules(new IacucProtocolApproveEvent(document, actionBean))) {
                forward = super.approve(mapping, protocolForm, request, response);
                getProtocolApproveService().grantFullApproval(protocol, actionBean);
                saveReviewComments(protocolForm, (IacucReviewCommentsBean) actionBean.getReviewCommentsBean());
                IacucProtocolSubmission submission = (IacucProtocolSubmission)protocol.getProtocolSubmission();

                IacucProtocolNotificationRequestBean notificationBean;
                String actionType;
                String actionDescription;
                String actionDescription2;
                if (StringUtils.equals(submission.getProtocolReviewTypeCode(),IacucProtocolReviewType.DESIGNATED_MEMBER_REVIEW)) {
                    actionType = IacucProtocolActionType.DESIGNATED_REVIEW_APPROVAL;
                    actionDescription = "Designated Member Approval";
                    actionDescription2 = "Designated Member Approved";
                }
                else {
                    actionType = IacucProtocolActionType.IACUC_APPROVED;
                    actionDescription = "Full Approval";
                    actionDescription2 = "Approved";
                }
                recordProtocolActionSuccess(actionDescription);
                notificationBean = new IacucProtocolNotificationRequestBean((IacucProtocol) protocolForm.getProtocolDocument().getProtocol(), actionType, actionDescription);
                // issue : protocolcorrespondence is reset after loading correspondence ? more work
                // somehow docforkey is not in session for this case ?
                // hack this for now
                protocolForm.getProtocolHelper().prepareView();
                if (protocolForm.getActionHelper().getProtocolCorrespondence() != null) {
                    // TODO : this is hack
                    // may need to add it back when save/close corr ?
                    
                    GlobalVariables.getUserSession().addObject("approvalComplCorrespondence", GlobalVariables.getUserSession().retrieveObject(DocumentAuthorizerBase.USER_SESSION_METHOD_TO_CALL_COMPLETE_OBJECT_KEY));
                    // temporarily remove this key which is generated by super.approve
                    GlobalVariables.getUserSession().removeObject(DocumentAuthorizerBase.USER_SESSION_METHOD_TO_CALL_COMPLETE_OBJECT_KEY);
                    return mapping.findForward(CORRESPONDENCE);
                } else {
                    IacucProtocolNotificationRenderer renderer = new IacucProtocolNotificationRenderer((IacucProtocol) document.getProtocol());
                    IacucProtocolNotificationContext context = new IacucProtocolNotificationContext((IacucProtocol) document.getProtocol(), actionType, actionDescription2, renderer);
                    getNotificationService().sendNotificationAndPersist(context, new IacucProtocolNotification(), protocol);
                    forward = routeProtocolToHoldingPage(mapping, protocolForm);                                    
                }
            }
        }
        
        return forward;
    }
    
    /**
     * Perform Response Approve Action.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward grantAdminApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {
        
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolDocument document = (IacucProtocolDocument) protocolForm.getProtocolDocument();
        IacucProtocolApproveBean actionBean = (IacucProtocolApproveBean) protocolForm.getActionHelper().getProtocolAdminApprovalBean();
        
        if (hasPermission(TaskName.ADMIN_APPROVE_PROTOCOL, (IacucProtocol) document.getProtocol())) {
            if (applyRules(new IacucProtocolApproveEvent(document, actionBean))) {
                getProtocolApproveService().grantAdminApproval(document.getProtocol(), actionBean);
                saveReviewComments(protocolForm, (IacucReviewCommentsBean) actionBean.getReviewCommentsBean());
                recordProtocolActionSuccess("Administrative Approval");
                IacucProtocolNotificationRequestBean notificationBean = new IacucProtocolNotificationRequestBean((IacucProtocol) document.getProtocol(), IacucProtocolActionType.ADMINISTRATIVE_APPROVAL, "Admin Approval");
                protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, PROTOCOL_TAB, notificationBean, false));
                if (protocolForm.getActionHelper().getProtocolCorrespondence() != null) {
                    forward = mapping.findForward(CORRESPONDENCE);
                } else {
                    forward = checkToSendNotification(mapping, mapping.findForward(PROTOCOL_TAB), protocolForm, notificationBean);
                }
            }
        }
        
        return forward;
    }

    /**
     * Requests an action to be performed by an administrator.  The user can request the following actions:
     * 
     *   Close
     *   Close Enrollment
     *   Data Analysis Only
     *   Deactivate
     *   Reopen Enrollment
     *   Suspension
     *   Termination
     *   Lift Hold
     * 
     * Uses the enumeration <code>IacucProtocolRequestAction</code> to encapsulate the unique properties on each action.
     * @param mapping The mapping associated with this action.
     * @param form The ProtocolBase form.
     * @param request The HTTP request
     * @param response The HTTP response
     * @return the forward to the current page
     * @throws Exception
     */
    public ActionForward requestAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {
        
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolDocument document = protocolForm.getIacucProtocolDocument();
        IacucProtocol protocol = document.getIacucProtocol();
        String taskName = getTaskName(request);
        
        if (StringUtils.isNotBlank(taskName) && isAuthorized(new IacucProtocolTask(taskName, protocol))) {
            IacucProtocolRequestAction requestAction = IacucProtocolRequestAction.valueOfTaskName(taskName);
            IacucProtocolRequestBean requestBean = getProtocolRequestBean(form, request);
            
            if (requestBean != null) {
                boolean valid = applyRules(new IacucProtocolRequestEvent<IacucProtocolRequestRule>(document, requestAction.getErrorPath(), requestBean));
                requestBean.setAnswerHeaders(getAnswerHeaders(form, requestAction.getActionTypeCode()));
                valid &= isMandatoryQuestionnaireComplete(requestBean, "actionHelper." + requestAction.getBeanName() + ".datavalidation");
                if (valid) {
                    getProtocolRequestService().submitRequest(protocolForm.getIacucProtocolDocument().getIacucProtocol(), requestBean);            
                    recordProtocolActionSuccess(requestAction.getActionName());
                    return sendRequestNotification(mapping, form, protocol, requestBean);
                }
            }
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward addRequestAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
            throws Exception {
        System.err.println("addRequestAttachment");
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolDocument document = protocolForm.getIacucProtocolDocument();
        IacucProtocol protocol = document.getIacucProtocol();
        String taskName = getTaskName(request);
        
        if (StringUtils.isNotBlank(taskName) && isAuthorized(new IacucProtocolTask(taskName, protocol))) {
            IacucProtocolRequestBean requestBean = getProtocolRequestBean(form, request);
            if (requestBean.getNewActionAttachment().getFile() != null && requestBean.getNewActionAttachment().getFile().getFileData().length > 0) {
                requestBean.getNewActionAttachment().setFileName(requestBean.getNewActionAttachment().getFile().getFileName());
                requestBean.getActionAttachments().add(requestBean.getNewActionAttachment());
                requestBean.setNewActionAttachment(new ProtocolActionAttachment());
            } else {
                GlobalVariables.getMessageMap().putError("actionHelper.iacucProtocolSuspendRequestBean.newActionAttachment.file", 
                        KeyConstants.AWARD_ATTACHMENT_FILE_REQUIRED);
            }
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward viewRequestAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
            throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolDocument document = protocolForm.getIacucProtocolDocument();
        IacucProtocol protocol = document.getIacucProtocol();
        String taskName = getTaskName(request);
        if (StringUtils.isNotBlank(taskName) && isAuthorized(new IacucProtocolTask(taskName, protocol))) {
            IacucProtocolRequestBean requestBean = getProtocolRequestBean(form, request);
            int lineNumber = getSelectedLine(request);
            ProtocolActionAttachment actionAttachment = requestBean.getActionAttachments().get(lineNumber);
            if (actionAttachment.getFile() != null) {
                System.err.println("actionAttachment.getFile().getContentType(): " + actionAttachment.getFile().getContentType());                
                this.streamToResponse(actionAttachment.getFile().getFileData(), actionAttachment.getFileName(), actionAttachment.getFile().getContentType(), response);
                return RESPONSE_ALREADY_HANDLED;
            }
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward deleteRequestAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
            throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolDocument document = protocolForm.getIacucProtocolDocument();
        IacucProtocol protocol = document.getIacucProtocol();
        String taskName = getTaskName(request);
        if (StringUtils.isNotBlank(taskName) && isAuthorized(new IacucProtocolTask(taskName, protocol))) {
            IacucProtocolRequestAction requestAction = IacucProtocolRequestAction.valueOfTaskName(taskName);
            IacucProtocolRequestBean requestBean = getProtocolRequestBean(form, request);
            int lineNumber = getSelectedLine(request);
            requestBean.getActionAttachments().remove(lineNumber);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    private ActionForward sendRequestNotification(ActionMapping mapping, ActionForm form, IacucProtocol protocol, IacucProtocolRequestBean requestBean) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolActionType protocolActionType = getBusinessObjectService().findBySinglePrimaryKey(IacucProtocolActionType.class, requestBean.getProtocolActionTypeCode());
        String protocolActionTypeCode = protocolActionType.getProtocolActionTypeCode();
        String description = protocolActionType.getDescription();
        IacucRequestActionNotificationBean newNotificationBean = new IacucRequestActionNotificationBean(protocolForm.getIacucProtocolDocument().getIacucProtocol(), protocolActionTypeCode, description, requestBean.getReason());
        return checkToSendNotification(mapping, mapping.findForward(PROTOCOL_ACTIONS_TAB), protocolForm, newNotificationBean);
    }

    private IacucProtocolRequestBean getProtocolRequestBean(ActionForm form, HttpServletRequest request) {
        IacucProtocolRequestBean protocolRequestBean = null;
        
        IacucProtocolActionBean protocolActionBean = getActionBean(form, request);
        if (protocolActionBean != null && protocolActionBean instanceof IacucProtocolRequestBean) {
            protocolRequestBean = (IacucProtocolRequestBean) protocolActionBean;
        }
        
        return protocolRequestBean;
    }

    /**
     * Disapproves this ProtocolBase.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward disapproveProtocol(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolDocument document = (IacucProtocolDocument) protocolForm.getProtocolDocument();
        IacucProtocol protocol = (IacucProtocol) document.getProtocol();
        IacucProtocolGenericActionBean actionBean = (IacucProtocolGenericActionBean) protocolForm.getActionHelper().getProtocolDisapproveBean();
        
        if (hasPermission(TaskName.DISAPPROVE_PROTOCOL, protocol)) {
            if (applyRules(new IacucProtocolGenericActionEvent(document, actionBean))) {
                getProtocolGenericActionService().disapprove(protocol, actionBean);
                saveReviewComments(protocolForm, (IacucReviewCommentsBean) actionBean.getReviewCommentsBean());
                
                recordProtocolActionSuccess("Disapprove");
                IacucProtocolNotificationRequestBean notificationBean = new IacucProtocolNotificationRequestBean((IacucProtocol) protocolForm.getProtocolDocument().getProtocol(), IacucProtocolActionType.IACUC_DISAPPROVED, "Disapproved");
                protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, PROTOCOL_TAB, notificationBean, false));
                if (protocolForm.getActionHelper().getProtocolCorrespondence() != null) {
                    return mapping.findForward(CORRESPONDENCE);
                } else {
                    return checkToSendNotification(mapping, mapping.findForward(PROTOCOL_TAB), protocolForm, notificationBean);
                }
            }
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    
    public ActionForward expire(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolDocument document = (IacucProtocolDocument) protocolForm.getProtocolDocument();
        IacucProtocol protocol = (IacucProtocol) document.getProtocol();
        IacucProtocolGenericActionBean actionBean = (IacucProtocolGenericActionBean) protocolForm.getActionHelper().getProtocolExpireBean();
        
        if (hasGenericPermission(IacucGenericProtocolAuthorizer.EXPIRE_PROTOCOL, protocol)) {
            if (applyRules(new IacucProtocolGenericActionEvent(document, actionBean))) {
                getProtocolGenericActionService().expire(protocol, actionBean);
                saveReviewComments(protocolForm, (IacucReviewCommentsBean) actionBean.getReviewCommentsBean());
                
                recordProtocolActionSuccess("Expire");
                IacucProtocolNotificationRequestBean notificationBean = 
                        new IacucProtocolNotificationRequestBean((IacucProtocol) protocolForm.getProtocolDocument().getProtocol(), IacucProtocolActionType.EXPIRED, "Expired");
                protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, PROTOCOL_TAB, notificationBean, false));
                if (protocolForm.getActionHelper().getProtocolCorrespondence() != null) {
                    return mapping.findForward(CORRESPONDENCE);
                } else {
                    return checkToSendNotification(mapping, mapping.findForward(PROTOCOL_TAB), protocolForm, notificationBean);
                }
            }
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
 public ActionForward terminate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
     IacucProtocolForm protocolForm = (IacucProtocolForm) form;
     IacucProtocolDocument document = (IacucProtocolDocument) protocolForm.getProtocolDocument();
     IacucProtocol protocol = (IacucProtocol) document.getProtocol();
     IacucProtocolGenericActionBean actionBean = (IacucProtocolGenericActionBean) protocolForm.getActionHelper().getProtocolTerminateBean();
     
     if (hasGenericPermission(IacucGenericProtocolAuthorizer.TERMINATE_PROTOCOL, protocol)) {
         if (applyRules(new IacucProtocolGenericActionEvent(document, actionBean))) {
             getProtocolGenericActionService().terminate(protocol, actionBean);
             saveReviewComments(protocolForm, (IacucReviewCommentsBean) actionBean.getReviewCommentsBean());
             
             recordProtocolActionSuccess("Terminate");
             IacucProtocolNotificationRequestBean notificationBean = 
                     new IacucProtocolNotificationRequestBean((IacucProtocol) protocolForm.getProtocolDocument().getProtocol(), IacucProtocolActionType.TERMINATED, "Terminated");
             protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, PROTOCOL_TAB, notificationBean, false));
             if (protocolForm.getActionHelper().getProtocolCorrespondence() != null) {
                 return mapping.findForward(CORRESPONDENCE);
             } else {
                 return checkToSendNotification(mapping, mapping.findForward(PROTOCOL_TAB), protocolForm, notificationBean);
             }
         }
     }
     return mapping.findForward(Constants.MAPPING_BASIC);
  }
 
 public ActionForward suspend(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
     IacucProtocolForm protocolForm = (IacucProtocolForm) form;
     IacucProtocolDocument document = (IacucProtocolDocument) protocolForm.getProtocolDocument();
     IacucProtocol protocol = (IacucProtocol) document.getProtocol();
     IacucProtocolGenericActionBean actionBean = (IacucProtocolGenericActionBean) protocolForm.getActionHelper().getProtocolSuspendBean();
     
     if (hasGenericPermission(IacucGenericProtocolAuthorizer.SUSPEND_PROTOCOL, protocol)) {
         if (applyRules(new IacucProtocolGenericActionEvent(document, actionBean))) {
             getProtocolGenericActionService().suspend(protocol, actionBean);
             saveReviewComments(protocolForm, (IacucReviewCommentsBean) actionBean.getReviewCommentsBean());
             
             recordProtocolActionSuccess("Suspended");
             IacucProtocolNotificationRequestBean notificationBean = 
                     new IacucProtocolNotificationRequestBean((IacucProtocol) protocolForm.getProtocolDocument().getProtocol(), IacucProtocolActionType.SUSPENDED, "Suspended");
             protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, PROTOCOL_TAB, notificationBean, false));
             if (protocolForm.getActionHelper().getProtocolCorrespondence() != null) {
                 return mapping.findForward(CORRESPONDENCE);
             } else {
                 return checkToSendNotification(mapping, mapping.findForward(PROTOCOL_TAB), protocolForm, notificationBean);
             }
         }
     }
     return mapping.findForward(Constants.MAPPING_BASIC);
  }
       
    /**
     * Sends IACUC Acknowledgement for this ProtocolBase.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward iacucAcknowledgement(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolDocument document = (IacucProtocolDocument) protocolForm.getProtocolDocument();
        IacucProtocol protocol = (IacucProtocol) document.getProtocol();
        IacucProtocolGenericActionBean actionBean = ((IacucActionHelper) protocolForm.getActionHelper()).getIacucAcknowledgeBean();
        
        if (hasPermission(TaskName.IACUC_ACKNOWLEDGEMENT, protocol)) {
            if (applyRules(new IacucProtocolGenericActionEvent(document, actionBean))) {
                getProtocolGenericActionService().iacucAcknowledgement(protocol, actionBean);
                saveReviewComments(protocolForm, (IacucReviewCommentsBean) actionBean.getReviewCommentsBean());
                    
                recordProtocolActionSuccess("IACUC Acknowledgement");
                return checkToSendNotification(mapping, mapping.findForward(PROTOCOL_ACTIONS_TAB), protocolForm, 
                        new IacucProtocolNotificationRequestBean(protocol, IacucProtocolActionType.IACUC_ACKNOWLEDGEMENT, "IACUC Acknowledgement"));
            }
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Hold the IACUC ProtocolBase
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward iacucHold(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolDocument document = (IacucProtocolDocument) protocolForm.getProtocolDocument();
        IacucProtocol protocol = (IacucProtocol) document.getProtocol();
        IacucProtocolGenericActionBean actionBean = ((IacucActionHelper) protocolForm.getActionHelper()).getIacucProtocolHoldBean();
        
        if (hasPermission(TaskName.IACUC_PROTOCOL_HOLD, protocol)) {
            if (applyRules(new IacucProtocolGenericActionEvent(document, actionBean))) {
                getProtocolGenericActionService().iacucHold(protocol, actionBean);
                saveReviewComments(protocolForm, (IacucReviewCommentsBean) actionBean.getReviewCommentsBean());
                recordProtocolActionSuccess("IACUC Hold");
                
                IacucProtocolNotificationRequestBean notificationBean = new IacucProtocolNotificationRequestBean((IacucProtocol) protocolForm.getProtocolDocument().getProtocol(), IacucProtocolActionType.HOLD, "IACUC Hold");
                protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, PROTOCOL_TAB, notificationBean, false));
                if (protocolForm.getActionHelper().getProtocolCorrespondence() != null) {
                    return mapping.findForward(CORRESPONDENCE);
                } else {
                    return checkToSendNotification(mapping, mapping.findForward(PROTOCOL_TAB), protocolForm, notificationBean);
                }
            }
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }   
    
    /**
     * Hold the IACUC ProtocolBase
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward iacucLiftHold(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolDocument document = (IacucProtocolDocument) protocolForm.getProtocolDocument();
        IacucProtocol protocol = (IacucProtocol) document.getProtocol();
        IacucProtocolGenericActionBean actionBean = ((IacucActionHelper) protocolForm.getActionHelper()).getIacucProtocolLiftHoldBean();
        
        if (hasPermission(TaskName.IACUC_PROTOCOL_LIFT_HOLD, protocol)) {
            if (applyRules(new IacucProtocolGenericActionEvent(document, actionBean))) {
                getProtocolGenericActionService().iacucLiftHold(protocol, actionBean);
                saveReviewComments(protocolForm, (IacucReviewCommentsBean) actionBean.getReviewCommentsBean());
                    
                recordProtocolActionSuccess("IACUC Lift Hold");
                return checkToSendNotification(mapping, mapping.findForward(PROTOCOL_ACTIONS_TAB), protocolForm, 
                        new IacucProtocolNotificationRequestBean(protocol, IacucProtocolActionType.LIFT_HOLD, "IACUC Lift Hold"));
            }
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }    
    
    /**
     * Returns the protocol to the PI for specific minor revisions.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward returnForSMR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolDocument document = (IacucProtocolDocument) protocolForm.getProtocolDocument();
        IacucProtocol protocol = (IacucProtocol) document.getProtocol();
        IacucProtocolGenericActionBean actionBean = (IacucProtocolGenericActionBean) protocolForm.getActionHelper().getProtocolSMRBean();
        
        if (hasPermission(TaskName.RETURN_FOR_SMR, protocol)) {
            if (applyRules(new IacucProtocolGenericActionEvent(document, actionBean))) {
                ProtocolDocumentBase newDocument = getProtocolGenericActionService().returnForSMR(protocol, actionBean);
                saveReviewComments(protocolForm, (IacucReviewCommentsBean) actionBean.getReviewCommentsBean());
                
                protocolForm.setDocId(newDocument.getDocumentNumber());
                loadDocument(protocolForm);
                protocolForm.getProtocolHelper().prepareView();
                recordProtocolActionSuccess("Return for Specific Minor Revisions");
                protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, PROTOCOL_TAB, 
                        new IacucProtocolNotificationRequestBean(protocol, IacucProtocolActionType.IACUC_MINOR_REVISIONS_REQUIRED, "Minor Revisions Required"), false));

                if (protocolForm.getActionHelper().getProtocolCorrespondence() != null) {
                    return mapping.findForward(CORRESPONDENCE);
                } else {
                    forward = checkToSendNotification(mapping, mapping.findForward(PROTOCOL_TAB), protocolForm, 
                            new IacucProtocolNotificationRequestBean(protocol, IacucProtocolActionType.IACUC_MINOR_REVISIONS_REQUIRED, "Minor Revisions Required"));                                   
                }
            }
        }
        
        return forward;
    }
    
    
    
    /**
     * Returns the protocol to the PI for substantial revisions.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward returnForSRR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolDocument document = (IacucProtocolDocument) protocolForm.getProtocolDocument();
        IacucProtocol protocol = (IacucProtocol) document.getProtocol();
        IacucProtocolGenericActionBean actionBean = (IacucProtocolGenericActionBean) protocolForm.getActionHelper().getProtocolSRRBean();
        
        if (hasPermission(TaskName.RETURN_FOR_SRR, protocol)) {
            if (applyRules(new IacucProtocolGenericActionEvent(document, actionBean))) {
                ProtocolDocumentBase newDocument = getProtocolGenericActionService().returnForSRR(protocol, actionBean);
                saveReviewComments(protocolForm, (IacucReviewCommentsBean) actionBean.getReviewCommentsBean());
                
                protocolForm.setDocId(newDocument.getDocumentNumber());
                loadDocument(protocolForm);
                protocolForm.getProtocolHelper().prepareView();
                
                recordProtocolActionSuccess("Return for Substantive Revisions Required");
                
                IacucProtocolNotificationRequestBean newNotificationBean = new IacucProtocolNotificationRequestBean(protocol, IacucProtocolActionType.IACUC_MAJOR_REVISIONS_REQUIRED, "Major Revisions Required");
                protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, PROTOCOL_TAB, newNotificationBean, false));

                if (protocolForm.getActionHelper().getProtocolCorrespondence() != null) {
                    return mapping.findForward(CORRESPONDENCE);
                } else {
                    forward = checkToSendNotification(mapping, mapping.findForward(PROTOCOL_TAB), protocolForm, newNotificationBean);                                   
                }
            }
        }
        
        return forward;
    }   
    
    /**
     * Returns the protocol to the PI for substantial revisions.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward returnToPI(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolDocument document = (IacucProtocolDocument) protocolForm.getProtocolDocument();
        IacucProtocol protocol = (IacucProtocol) document.getProtocol();
        IacucProtocolGenericActionBean actionBean = (IacucProtocolGenericActionBean) protocolForm.getActionHelper().getProtocolReturnToPIBean();
        
        if (hasPermission(TaskName.RETURN_TO_PI_PROTOCOL, protocol)) {
            if (applyRules(new IacucProtocolGenericActionEvent(document, actionBean))) {
                ProtocolDocumentBase newDocument = getProtocolGenericActionService().returnToPI(protocol, actionBean);
                saveReviewComments(protocolForm, (IacucReviewCommentsBean) actionBean.getReviewCommentsBean());
                
                protocolForm.setDocId(newDocument.getDocumentNumber());
                loadDocument(protocolForm);
                protocolForm.getProtocolHelper().prepareView();
                
                recordProtocolActionSuccess("Return To PI");
                
                IacucProtocolNotificationRequestBean newNotificationBean = new IacucProtocolNotificationRequestBean(protocol, IacucProtocolActionType.RETURNED_TO_PI, "Return To PI");
                protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, PROTOCOL_TAB, newNotificationBean, false));

                if (protocolForm.getActionHelper().getProtocolCorrespondence() != null) {
                    return mapping.findForward(CORRESPONDENCE);
                } else {
                    forward = checkToSendNotification(mapping, mapping.findForward(PROTOCOL_TAB), protocolForm, newNotificationBean);                                   
                }
            }
        }
        
        return forward;
    }     
    
    
    /**
     * Deactivates this IACUC ProtocolBase.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    
    public ActionForward iacucDeactivate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);

        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolDocument document = protocolForm.getIacucProtocolDocument();
        IacucProtocol protocol = document.getIacucProtocol();
        IacucActionHelper actionHelper = (IacucActionHelper)protocolForm.getActionHelper();
        IacucProtocolGenericActionBean actionBean = actionHelper.getIacucProtocolDeactivateBean();
        
        if (!hasDocumentStateChanged(protocolForm)) {
            if (hasPermission(TaskName.IACUC_PROTOCOL_DEACTIVATE, protocol)) {
                if (applyRules(new IacucProtocolGenericActionEvent(document, actionBean))) {
                    getProtocolGenericActionService().iacucDeactivate(protocol, actionBean);
                    saveReviewComments(protocolForm, (IacucReviewCommentsBean) actionBean.getReviewCommentsBean());

                    recordProtocolActionSuccess("Deactivated");
                    IacucProtocolNotificationRequestBean notificationBean = new IacucProtocolNotificationRequestBean(protocol, IacucProtocolActionType.DEACTIVATED, "Deactivated");
                    protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, PROTOCOL_TAB, notificationBean, false));

                    if (protocolForm.getActionHelper().getProtocolCorrespondence() != null) {
                        return mapping.findForward(CORRESPONDENCE);
                    } else {
                        return checkToSendNotification(mapping, mapping.findForward(PROTOCOL_TAB), protocolForm, notificationBean);                                   
                    }
                }
            }
        } else {
            GlobalVariables.getMessageMap().clearErrorMessages();
            GlobalVariables.getMessageMap().putError("documentstatechanged", KeyConstants.ERROR_PROTOCOL_DOCUMENT_STATE_CHANGED,  new String[] {}); 
        }
        
        return forward;
    }
    
    public ActionForward manageComments(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        if (!hasDocumentStateChanged(protocolForm)) {
            if (hasPermission(TaskName.PROTOCOL_MANAGE_REVIEW_COMMENTS, (IacucProtocol) protocolForm.getProtocolDocument().getProtocol())) {
                IacucProtocolGenericActionBean actionBean = (IacucProtocolGenericActionBean) protocolForm.getActionHelper().getProtocolManageReviewCommentsBean();
                saveReviewComments(protocolForm, (IacucReviewCommentsBean) actionBean.getReviewCommentsBean());
                
                recordProtocolActionSuccess("Manage Review Comments");
            }
        } else {
            GlobalVariables.getMessageMap().clearErrorMessages();
            GlobalVariables.getMessageMap().putError("documentstatechanged", KeyConstants.ERROR_PROTOCOL_DOCUMENT_STATE_CHANGED,  new String[] {}); 
        }        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Open ProtocolDocumentBase in Read/Write mode for Admin Correction
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward openProtocolForAdminCorrection(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);

        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolDocument protocolDocument = protocolForm.getIacucProtocolDocument();
        IacucProtocol protocol = protocolForm.getIacucProtocolDocument().getIacucProtocol();
        
        IacucProtocolTask task = new IacucProtocolTask(TaskName.IACUC_PROTOCOL_ADMIN_CORRECTION, protocol);
        if (!hasDocumentStateChanged(protocolForm)) {
            if (isAuthorized(task)) {
                if (applyRules(new IacucProtocolAdminCorrectionEvent(protocolDocument, Constants.PROTOCOL_ADMIN_CORRECTION_PROPERTY_KEY,
                        protocolForm.getActionHelper().getProtocolAdminCorrectionBean()))) {
                    protocolDocument.getProtocol().setCorrectionMode(true); 
                    protocolForm.getProtocolHelper().prepareView();
    
                    IacucAdminCorrectionBean adminCorrectionBean = (IacucAdminCorrectionBean)protocolForm.getActionHelper().getProtocolAdminCorrectionBean();
                    protocolDocument.updateProtocolStatus(IacucProtocolActionType.ADMINISTRATIVE_CORRECTION, adminCorrectionBean.getComments());
                    recordProtocolActionSuccess("Make Administrative Correction");
    
                    IacucProtocolNotificationRequestBean notificationBean = new IacucProtocolNotificationRequestBean(protocol, IacucProtocolActionType.ADMINISTRATIVE_CORRECTION, "Administrative Correction");
                    protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, PROTOCOL_TAB, notificationBean, false));

                    if (protocolForm.getActionHelper().getProtocolCorrespondence() != null) {
                        return mapping.findForward(CORRESPONDENCE);
                    } else {
                        return checkToSendNotification(mapping, mapping.findForward(PROTOCOL_TAB), protocolForm, notificationBean);                                   
                    }
                }
            }
        } else {
            GlobalVariables.getMessageMap().clearErrorMessages();
            GlobalVariables.getMessageMap().putError("documentstatechanged", KeyConstants.ERROR_PROTOCOL_DOCUMENT_STATE_CHANGED,  new String[] {}); 
        } 
        
        return forward;  
    }    
    
    public ActionForward submitCommitteeDecision(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        if (!hasDocumentStateChanged(protocolForm)) {
            IacucCommitteeDecision actionBean = (IacucCommitteeDecision) protocolForm.getActionHelper().getCommitteeDecision();
            if (applyRules(new IacucCommitteeDecisionEvent((IacucProtocolDocument) protocolForm.getProtocolDocument(), actionBean))){                
                getCommitteeDecisionService().processCommitteeDecision(protocolForm.getProtocolDocument().getProtocol(), actionBean);
                saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
                confirmFollowupAction(mapping, form, request, response, Constants.MAPPING_BASIC);
                protocolForm.getTabStates().put(":" + WebUtils.generateTabKey(motionTypeMap.get(actionBean.getMotionTypeCode())), "OPEN");
                recordProtocolActionSuccess("Record Committee Decision");
            }
        } else {
            GlobalVariables.getMessageMap().clearErrorMessages();
            GlobalVariables.getMessageMap().putError("documentstatechanged", KeyConstants.ERROR_PROTOCOL_DOCUMENT_STATE_CHANGED,  new String[] {}); 
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward addAbstainer(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucCommitteeDecision decision = (IacucCommitteeDecision) protocolForm.getActionHelper().getCommitteeDecision();
        if (applyRules(new IacucCommitteeDecisionAbstainerEvent((IacucProtocolDocument) protocolForm.getProtocolDocument(), decision))){
            decision.getAbstainers().add(decision.getNewAbstainer());
            decision.setNewAbstainer(new IacucCommitteePerson());
            decision.setAbstainCount(decision.getAbstainCount() + 1);
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward deleteAbstainer(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucCommitteeDecision decision = (IacucCommitteeDecision) protocolForm.getActionHelper().getCommitteeDecision();
        IacucCommitteePerson person = decision.getAbstainers().get(getLineToDelete(request));
        if (person != null) {
            decision.getAbstainersToDelete().add(person);
            decision.getAbstainers().remove(getLineToDelete(request));
            decision.setAbstainCount(decision.getAbstainCount() - 1);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward addRecused(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucCommitteeDecision decision = (IacucCommitteeDecision) protocolForm.getActionHelper().getCommitteeDecision();
        if (applyRules(new IacucCommitteeDecisionRecuserEvent((IacucProtocolDocument) protocolForm.getProtocolDocument(), decision))) {
            decision.getRecused().add(decision.getNewRecused());
            decision.setNewRecused(new IacucCommitteePerson());
            decision.setRecusedCount(decision.getRecusedCount() + 1);
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    
    public ActionForward deleteRecused(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucCommitteeDecision decision = (IacucCommitteeDecision) protocolForm.getActionHelper().getCommitteeDecision();
        IacucCommitteePerson person = decision.getRecused().get(getLineToDelete(request));
        if (person != null) {
            decision.getRecusedToDelete().add(person);
            decision.getRecused().remove(getLineToDelete(request));
            decision.setRecusedCount(decision.getRecusedCount() - 1);
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    private IacucProtocolPrintingService getProtocolPrintingService() {
        return KraServiceLocator.getService(IacucProtocolPrintingService.class);
    }
    
    /**
     * Adds a review comment to the bean indicated by the task name in the request.
     * 
     * @param mapping The mapping associated with this action.
     * @param form The ProtocolBase form.
     * @param request The HTTP request
     * @param response The HTTP response
     * @return the forward to the current page
     */
    public ActionForward addReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        ProtocolDocumentBase document = protocolForm.getProtocolDocument();
        IacucReviewCommentsBean reviewCommentsBean = getReviewCommentsBean(mapping, form, request, response);
        
        if (reviewCommentsBean != null) {
            String errorPropertyName = reviewCommentsBean.getErrorPropertyName();
            CommitteeScheduleMinuteBase newReviewComment = reviewCommentsBean.getNewReviewComment();
            List<CommitteeScheduleMinuteBase> reviewComments = reviewCommentsBean.getReviewComments();
            IacucProtocol protocol = (IacucProtocol) document.getProtocol();
            
            if (applyRules(new IacucProtocolAddReviewCommentEvent((IacucProtocolDocument) document, errorPropertyName, newReviewComment))) {
                getReviewCommentsService().addReviewComment(newReviewComment, reviewComments, protocol);
                
                reviewCommentsBean.setNewReviewComment(new IacucCommitteeScheduleMinute(MinuteEntryType.PROTOCOL));
            }
            reviewCommentsBean.setHideReviewerName(getReviewCommentsService().setHideReviewerName(reviewCommentsBean.getReviewComments()));            
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }    

     
    /**
     * Moves up a review comment in the bean indicated by the task name in the request.
     * 
     * @param mapping The mapping associated with this action.
     * @param form The ProtocolBase form.
     * @param request The HTTP request
     * @param response The HTTP response
     * @return the forward to the current page
     */
    public ActionForward moveUpReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        ProtocolDocumentBase document = protocolForm.getProtocolDocument();
        IacucReviewCommentsBean reviewCommentsBean = getReviewCommentsBean(mapping, form, request, response);
        
        if (reviewCommentsBean != null) {
            List<CommitteeScheduleMinuteBase> reviewComments = reviewCommentsBean.getReviewComments();
            int lineNumber = getSelectedLine(request);    
            getReviewCommentsService().moveUpReviewComment(reviewComments, document.getProtocol(), lineNumber);
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Moves down a review comment in the bean indicated by the task name in the request.
     * 
     * @param mapping The mapping associated with this action.
     * @param form The ProtocolBase form.
     * @param request The HTTP request
     * @param response The HTTP response
     * @return the forward to the current page
     */
    public ActionForward moveDownReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        ProtocolDocumentBase document = protocolForm.getProtocolDocument();
        IacucReviewCommentsBean reviewCommentsBean = getReviewCommentsBean(mapping, form, request, response);
        
        if (reviewCommentsBean != null) {
            List<CommitteeScheduleMinuteBase> reviewComments = reviewCommentsBean.getReviewComments();
            int lineNumber = getSelectedLine(request);            
            getReviewCommentsService().moveDownReviewComment(reviewComments, document.getProtocol(), lineNumber);
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Deletes a review comment from the bean indicated by the task name in the request.
     * 
     * @param mapping The mapping associated with this action.
     * @param form The ProtocolBase form.
     * @param request The HTTP request
     * @param response The HTTP response
     * @return the forward to the current page
     */
    public ActionForward deleteReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        IacucReviewCommentsBean reviewCommentsBean = getReviewCommentsBean(mapping, form, request, response);
        
        if (reviewCommentsBean != null) {
            List<CommitteeScheduleMinuteBase> reviewComments = reviewCommentsBean.getReviewComments();
            int lineNumber = getLineToDelete(request);
            List<CommitteeScheduleMinuteBase> deletedReviewComments = reviewCommentsBean.getDeletedReviewComments();
            
            getReviewCommentsService().deleteReviewComment(reviewComments, lineNumber, deletedReviewComments);
            if (reviewComments.isEmpty()) {
                reviewCommentsBean.setHideReviewerName(true);
            } else {
                reviewCommentsBean.setHideReviewerName(getReviewCommentsService().setHideReviewerName(reviewCommentsBean.getReviewComments()));
            }
       }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    
    
    
    
    
    public ActionForward abandon(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolDocument protocolDocument = (IacucProtocolDocument) protocolForm.getProtocolDocument();
        IacucProtocol protocol = protocolDocument.getIacucProtocol();
        ProtocolTaskBase task = new IacucProtocolTask(TaskName.IACUC_ABANDON_PROTOCOL, protocol);
        if (isAuthorized(task)) {
            getProtocolAbandonService().abandonProtocol(protocolForm.getProtocolDocument().getProtocol(),
                    protocolForm.getActionHelper().getProtocolAbandonBean());
            protocolForm.getProtocolHelper().prepareView();
            
            recordProtocolActionSuccess("Abandon");
            ProtocolActionBase lastAction = protocol.getLastProtocolAction();

            protocolForm.getProtocolHelper().prepareView();

            IacucProtocolNotificationRequestBean notificationBean = new IacucProtocolNotificationRequestBean(protocolForm.getIacucProtocolDocument().getIacucProtocol(),IacucProtocolActionType.IACUC_ABANDON, "Abandon");
            protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, PROTOCOL_ACTIONS_TAB, notificationBean, false));

            if (protocolForm.getActionHelper().getProtocolCorrespondence() != null) {
                return mapping.findForward(CORRESPONDENCE);
            } else {
                return checkToSendNotification(mapping, mapping.findForward(PROTOCOL_TAB), protocolForm, notificationBean);
            }

        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }


    
    /**
     * Saves the review comments to the database and performs refresh and cleanup.
     * @param protocolForm
     * @param actionBean
     * @throws Exception
     */
    private void saveReviewComments(IacucProtocolForm protocolForm, IacucReviewCommentsBean actionBean) throws Exception { 
        getReviewCommentsService().saveReviewComments(actionBean.getReviewComments(), actionBean.getDeletedReviewComments());           
        actionBean.setDeletedReviewComments(new ArrayList<CommitteeScheduleMinuteBase>());
        protocolForm.getActionHelper().prepareCommentsView();
    }
 
    

    
    private IacucReviewCommentsBean getReviewCommentsBean(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        IacucReviewCommentsBean reviewCommentsBean = null;
        
        ProtocolActionBean protocolActionBean = getActionBean(form, request);
        if (protocolActionBean != null && protocolActionBean instanceof ProtocolOnlineReviewCommentable) {
            reviewCommentsBean = (IacucReviewCommentsBean) ((ProtocolOnlineReviewCommentable) protocolActionBean).getReviewCommentsBean();
        }
        
        return reviewCommentsBean;
    }
    
    
    private IacucProtocolActionBean getActionBean(ActionForm form, HttpServletRequest request) {
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;

        String taskName = getTaskName(request);
        
        IacucProtocolActionBean protocolActionBean = null;
        if (StringUtils.isNotBlank(taskName)) {
            protocolActionBean = (IacucProtocolActionBean) protocolForm.getActionHelper().getActionBean(taskName);
        }

        return protocolActionBean;
    }
    
    
    private String getTaskName(HttpServletRequest request) {
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        
        String taskName = "";
        if (StringUtils.isNotBlank(parameterName)) {
            taskName = StringUtils.substringBetween(parameterName, ".taskName", ".");
        }
        
        return taskName;
    }
    
    private boolean hasPermission(String taskName, IacucProtocol protocol) {
        IacucProtocolTask task = new IacucProtocolTask(taskName, protocol);
        return getTaskAuthorizationService().isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), task);
    }
    
    
    private boolean hasGenericPermission(String genericActionName, IacucProtocol protocol) {
        IacucProtocolTask task = new IacucProtocolTask(TaskName.GENERIC_IACUC_PROTOCOL_ACTION, protocol, genericActionName);
        return getTaskAuthorizationService().isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), task);
    }
    
    private IacucProtocolAttachmentService getProtocolAttachmentService() {
        return KraServiceLocator.getService(IacucProtocolAttachmentService.class);
    }
    
    private TaskAuthorizationService getTaskAuthorizationService() {
        return KraServiceLocator.getService(TaskAuthorizationService.class);
    }
    
    private IacucProtocolGenericActionService getProtocolGenericActionService() {
        return KraServiceLocator.getService(IacucProtocolGenericActionService.class);
    }
     
    private IacucProtocolAbandonService getProtocolAbandonService() {
        return KraServiceLocator.getService(IacucProtocolAbandonService.class);
    }
     
    
    public IacucProtocolCopyService getIacucProtocolCopyService() {
        return KraServiceLocator.getService(IacucProtocolCopyService.class);
    }
    
    
    private IacucProtocolSubmitActionService getProtocolSubmitActionService() {
        return KraServiceLocator.getService(IacucProtocolSubmitActionService.class);
    }
    
    
    private IacucProtocolWithdrawService getProtocolWithdrawService() {
        return KraServiceLocator.getService(IacucProtocolWithdrawService.class);
    }
    
    
    private IacucProtocolRequestService getProtocolRequestService() {
        return KraServiceLocator.getService(IacucProtocolRequestService.class);
    }
    
    private IacucProtocolNotifyCommitteeService getProtocolNotifyCommitteeService() {
        return KraServiceLocator.getService(IacucProtocolNotifyCommitteeService.class);
    }
   
    
    private IacucProtocolAmendRenewService getProtocolAmendRenewService() {
        return KraServiceLocator.getService(IacucProtocolAmendRenewService.class);        
    }
    
    private IacucProtocolDeleteService getProtocolDeleteService() {
        return KraServiceLocator.getService(IacucProtocolDeleteService.class);
    }
    
    private IacucProtocolAssignCmtService getProtocolAssignCmtService() {
        return KraServiceLocator.getService(IacucProtocolAssignCmtService.class);
    }
    
    private IacucProtocolAssignToAgendaService getProtocolAssignToAgendaService() {
        return KraServiceLocator.getService(IacucProtocolAssignToAgendaService.class);
    }
    
    private IacucProtocolApproveService getProtocolApproveService() {
        return KraServiceLocator.getService(IacucProtocolApproveService.class);
    }
    
    private CommitteeServiceBase getCommitteeService() {
        return KraServiceLocator.getService(IacucCommitteeService.class);
    }
    
    private IacucCommitteeDecisionService getCommitteeDecisionService() {
        return KraServiceLocator.getService(IacucCommitteeDecisionService.class);
    }
 
    private IacucReviewCommentsService getReviewCommentsService() {
        return KraServiceLocator.getService(IacucReviewCommentsService.class);
    }
        
    /**
     * 
     * This method is to add a file to notify iacuc 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addNotifyIacucAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucActionHelper actionHelper = (IacucActionHelper) protocolForm.getActionHelper();
        if (actionHelper.validFile(actionHelper.getIacucProtocolNotifyIacucBean().getNewActionAttachment(), "iacucProtocolNotifyIacucBean")) {
            LOG.info("addNotifyIacucAttachment " + actionHelper.getIacucProtocolNotifyIacucBean().getNewActionAttachment().getFile().getFileName()
                    + ((ProtocolFormBase) form).getProtocolDocument().getDocumentNumber());
            actionHelper.addNotifyIacucAttachment();
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * 
     * This method view a file added to notify irb panel
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward viewNotifyIacucAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return this.viewAttachment(mapping, (IacucProtocolForm) form, request, response);
    }

    /*
     * utility to view file 
     */
    private ActionForward viewAttachment(ActionMapping mapping, IacucProtocolForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        int selection = this.getSelectedLine(request);
        IacucActionHelper actionHelper = (IacucActionHelper) form.getActionHelper();
        ProtocolActionAttachment attachment = actionHelper.getIacucProtocolNotifyIacucBean().getActionAttachments().get(
                selection);

        if (attachment == null) {
            LOG.info(NOT_FOUND_SELECTION + selection);
            // may want to tell the user the selection was invalid.
            return mapping.findForward(Constants.MAPPING_BASIC);
        }

        this.streamToResponse(attachment.getFile().getFileData(), getValidHeaderString(attachment.getFile().getFileName()),
                getValidHeaderString(attachment.getFile().getContentType()), response);

        return RESPONSE_ALREADY_HANDLED;
    }

    /**
     * 
     * This method to delete a file added in norify irb panel
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteNotifyIacucAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucActionHelper actionHelper = (IacucActionHelper) protocolForm.getActionHelper();
        return confirmDeleteAttachment(mapping, protocolForm, request, response, 
                actionHelper.getIacucProtocolNotifyIacucBean().getActionAttachments());
    }

    /*
     * confirmation question for delete norify irb file or request attachment file
     */
    private ActionForward confirmDeleteAttachment(ActionMapping mapping, ProtocolFormBase form, HttpServletRequest request,
            HttpServletResponse response, List<ProtocolActionAttachment> attachments) throws Exception {

        int selection = this.getSelectedLine(request);
        ProtocolActionAttachment attachment = attachments.get(selection);

        if (attachment == null) {
            LOG.info(NOT_FOUND_SELECTION + selection);
            // may want to tell the user the selection was invalid.
            return mapping.findForward(Constants.MAPPING_BASIC);
        }

        StrutsConfirmation confirm = buildParameterizedConfirmationQuestion(mapping, form, request, response,
                CONFIRM_DELETE_ACTION_ATT, KeyConstants.QUESTION_DELETE_ATTACHMENT_CONFIRMATION, "", attachment
                        .getFile().getFileName());

        return confirm(confirm, CONFIRM_DELETE_ACTION_ATT, CONFIRM_NO_ACTION);
    }


    /**
     * 
     * method when confirm to delete notify irb file or request action attachment
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward confirmDeleteActionAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {
        
        String taskName = getTaskName(request);
        int selection = getSelectedLine(request);
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucActionHelper actionHelper = (IacucActionHelper) protocolForm.getActionHelper();
        
        if (StringUtils.isBlank(taskName)) {
            actionHelper.getIacucProtocolNotifyIacucBean().getActionAttachments().remove(selection);
        } else {
            IacucProtocolRequestBean requestBean = getProtocolRequestBean(form, request);
            requestBean.getActionAttachments().remove(selection);
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    

    /**
     * 
     * This method is to view the submission doc displayed in history panel
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward viewSubmissionDoc(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        int actionIndex = getSelectedLine(request);
        int attachmentIndex = getSelectedAttachment(request);
        ProtocolActionBase protocolAction = protocolForm.getActionHelper().getProtocol().getProtocolActions().get(actionIndex);
        ProtocolSubmissionDocBase attachment = protocolAction.getProtocolSubmissionDocs().get(attachmentIndex);

        if (attachment == null) {
            LOG.info(NOT_FOUND_SELECTION + "protocolAction: " + actionIndex + ", protocolSubmissionDoc: " + attachmentIndex);
            // may want to tell the user the selection was invalid.
            return mapping.findForward(Constants.MAPPING_BASIC);
        }

        this.streamToResponse(attachment.getDocument(), getValidHeaderString(attachment.getFileName()), getValidHeaderString(attachment.getContentType()), response);

        return RESPONSE_ALREADY_HANDLED;
    }
    
    /**
     * 
     * This method is to view correspondences in history panel.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward viewActionCorrespondence(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        int actionIndex = getSelectedLine(request);
        int attachmentIndex = getSelectedAttachment(request);
        ProtocolActionBase protocolAction = protocolForm.getActionHelper().getProtocol().getProtocolActions().get(actionIndex);
        ProtocolCorrespondence attachment = protocolAction.getProtocolCorrespondences().get(attachmentIndex);

        if (attachment == null) {
            LOG.info(NOT_FOUND_SELECTION + "protocolAction: " + actionIndex + ", protocolCorrespondence: " + attachmentIndex);
            // may want to tell the user the selection was invalid.
            return mapping.findForward(Constants.MAPPING_BASIC);
        }

        this.streamToResponse(attachment.getCorrespondence(), StringUtils.replace(attachment.getProtocolCorrespondenceType().getDescription(), " ", "") + ".pdf", 
                Constants.PDF_REPORT_CONTENT_TYPE, response);

        return RESPONSE_ALREADY_HANDLED;
    }
    
    /*
     * utility to get "actionidx;atachmentidx"
     */
    private int getSelectedAttachment(HttpServletRequest request) {
        int selectedAttachment = -1;
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            String attachmentNumber = StringUtils.substringBetween(parameterName, ".attachment", ".");
            selectedAttachment = Integer.parseInt(attachmentNumber);
        }

        return selectedAttachment;
    }   
    
    private void recordProtocolActionSuccess(String protocolActionName) {
        KNSGlobalVariables.getMessageList().add(KeyConstants.MESSAGE_PROTOCOL_ACTION_SUCCESSFULLY_COMPLETED, protocolActionName);
    }
    
    /*
     * confirmation question for followup action
     */
    private ActionForward confirmFollowupAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response, String forward) throws Exception {

        List<IacucValidProtocolActionAction> validFollowupActions = getFollowupActionService().getFollowupsForProtocol(((ProtocolFormBase)form).getProtocolDocument().getProtocol());

        if (validFollowupActions.isEmpty()) {
            LOG.info("No followup action");
            return mapping.findForward(forward);
        } else if (!validFollowupActions.get(0).getUserPromptFlag()) {
            addFollowupAction(((ProtocolFormBase)form).getProtocolDocument().getProtocol());
            return mapping.findForward(forward);
        }

        StrutsConfirmation confirm = buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_FOLLOWUP_ACTION,
                KeyConstants.QUESTION_PROTOCOL_CONFIRM_FOLLOWUP_ACTION, validFollowupActions.get(0).getUserPrompt());
        LOG.info("followup action "+validFollowupActions.get(0).getUserPrompt());

        return confirm(confirm, CONFIRM_FOLLOWUP_ACTION, CONFIRM_NO_ACTION);
    }

    /**
     * 
     * This method if 'Yes' to do followup action, then this will be executed.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward confirmAddFollowupAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        addFollowupAction(((ProtocolFormBase)form).getProtocolDocument().getProtocol());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    private void addFollowupAction(ProtocolBase protocol) throws Exception {

        List<IacucValidProtocolActionAction> validFollowupActions = getFollowupActionService().getFollowupsForProtocol(protocol);
        protocol.getLastProtocolAction().setFollowupActionCode(validFollowupActions.get(0).getFollowupActionCode());
        getBusinessObjectService().save(protocol.getLastProtocolAction());
    }

    private QuestionnaireAnswerService getQuestionnaireAnswerService() {
        return KraServiceLocator.getService(QuestionnaireAnswerService.class);
    }
    
    private IacucFollowupActionService getFollowupActionService() {
        return KraServiceLocator.getService(IacucFollowupActionService.class);
    }
    
    /**
     * This method is to get Watermark Service. 
     */
    private WatermarkService getWatermarkService() {
        return  KraServiceLocator.getService(WatermarkService.class);  
    }

    /**
     * 
     * This method is to view review attachment
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward viewReviewAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        // TODO : refactor methods related with review attachment to see if they are sharable with
        // online review action
        IacucReviewAttachmentsBean reviewAttachmentsBean = getReviewAttachmentsBean(mapping, form, request, response);

        if (reviewAttachmentsBean != null) {
            return streamReviewAttachment(mapping, request, response, reviewAttachmentsBean.getReviewAttachments());
        } else {
            return RESPONSE_ALREADY_HANDLED;
        }
    }
    
    /**
     * 
     * This method is to view review attachment from submission detail sub panel
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward viewSubmissionReviewAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        // TODO : refactor methods related with review attachment to see if they are sharable with 
        // online review action
          return streamReviewAttachment(mapping, request, response, (List<IacucProtocolReviewAttachment>) ((IacucProtocolForm)form).getActionHelper().getReviewAttachments());
    }
    
    private ActionForward streamReviewAttachment (ActionMapping mapping, HttpServletRequest request, HttpServletResponse response, List<IacucProtocolReviewAttachment> reviewAttachments) throws Exception {

        int lineNumber = getLineToDelete(request);
        final IacucProtocolReviewAttachment attachment = reviewAttachments.get(lineNumber);
        
        if (attachment == null) {
            LOG.info(NOT_FOUND_SELECTION + lineNumber);
            //may want to tell the user the selection was invalid.
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
        
        final AttachmentFile file = attachment.getFile();
        this.streamToResponse(file.getData(), getValidHeaderString(file.getName()),  getValidHeaderString(file.getType()), response);
        return RESPONSE_ALREADY_HANDLED;
    }

    
    /**
     * 
     * This method is to delete the review attachment from manage review attachment
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward deleteReviewAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        IacucReviewAttachmentsBean reviewAttachmentsBean = getReviewAttachmentsBean(mapping, form, request, response);
        
        if (reviewAttachmentsBean != null) {
            List<IacucProtocolReviewAttachment> reviewAttachments = reviewAttachmentsBean.getReviewAttachments();
            int lineNumber = getLineToDelete(request);
            List<IacucProtocolReviewAttachment> deletedReviewAttachments = reviewAttachmentsBean.getDeletedReviewAttachments();
            
            getReviewCommentsService().deleteReviewAttachment(reviewAttachments, lineNumber, deletedReviewAttachments);
            if (reviewAttachments.isEmpty()) {
                reviewAttachmentsBean.setHideReviewerName(true);
            } else {
                reviewAttachmentsBean.setHideReviewerName(getReviewCommentsService().setHideReviewerName(reviewAttachmentsBean.getReviewAttachments()));
            }
       }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /*
     * get the protocol manage review attachment bean
     */
    private IacucReviewAttachmentsBean getReviewAttachmentsBean(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        IacucReviewAttachmentsBean reviewAttachmentsBean = null;
        
        IacucProtocolActionBean protocolActionBean = getActionBean(form, request);
        if (protocolActionBean != null && protocolActionBean instanceof ProtocolOnlineReviewCommentable) {
            reviewAttachmentsBean = (IacucReviewAttachmentsBean) ((ProtocolOnlineReviewCommentable) protocolActionBean).getReviewAttachmentsBean();
        }
        
        return reviewAttachmentsBean;
    }

    /**
     * 
     * This method is for the submission of manage review attachment
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward manageAttachments(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        if(!hasDocumentStateChanged(protocolForm)) {
            if (hasPermission(TaskName.PROTOCOL_MANAGE_REVIEW_COMMENTS, (IacucProtocol) protocolForm.getProtocolDocument().getProtocol())) {
                if (applyRules(new IacucProtocolManageReviewAttachmentEvent((IacucProtocolDocument) protocolForm.getProtocolDocument(),
                    "actionHelper.protocolManageReviewCommentsBean.reviewAttachmentsBean.", 
                    ((IacucReviewAttachmentsBean)protocolForm.getActionHelper().getProtocolManageReviewCommentsBean().getReviewAttachmentsBean()).getReviewAttachments()))) {
                    IacucProtocolGenericActionBean actionBean = (IacucProtocolGenericActionBean) protocolForm.getActionHelper().getProtocolManageReviewCommentsBean();
                    saveReviewAttachments(protocolForm, (IacucReviewAttachmentsBean) actionBean.getReviewAttachmentsBean());
    
                    recordProtocolActionSuccess("Manage Review Attachments");
                }
            }
        } else {
            GlobalVariables.getMessageMap().clearErrorMessages();
            GlobalVariables.getMessageMap().putError("documentstatechanged", KeyConstants.ERROR_PROTOCOL_DOCUMENT_STATE_CHANGED,  new String[] {}); 
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /*
     * this method is to save review attachment when manage review attachment is submitted
     */
    private void saveReviewAttachments(IacucProtocolForm protocolForm, IacucReviewAttachmentsBean actionBean) throws Exception { 
        getReviewCommentsService().saveReviewAttachments(actionBean.getReviewAttachments(), actionBean.getDeletedReviewAttachments());           
        actionBean.setDeletedReviewAttachments(new ArrayList<IacucProtocolReviewAttachment>());
        protocolForm.getActionHelper().prepareCommentsView();
    }

    
    /**
     * 
     * This method is for adding new review attachment in manage review attachment
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward addReviewAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        ProtocolDocumentBase document = protocolForm.getProtocolDocument();
        IacucReviewAttachmentsBean reviewAttachmentsBean = getReviewAttachmentsBean(mapping, form, request, response);
        
        if (reviewAttachmentsBean != null) {
            String errorPropertyName = reviewAttachmentsBean.getErrorPropertyName();
            IacucProtocolReviewAttachment newReviewAttachment = reviewAttachmentsBean.getNewReviewAttachment();
            List<IacucProtocolReviewAttachment> reviewAttachments = reviewAttachmentsBean.getReviewAttachments();
            ProtocolBase protocol = document.getProtocol();
            
            if (applyRules(new IacucProtocolAddReviewAttachmentEvent((IacucProtocolDocument) document, errorPropertyName, newReviewAttachment))) {
                getReviewCommentsService().addReviewAttachment(newReviewAttachment, reviewAttachments, protocol);
                
                reviewAttachmentsBean.setNewReviewAttachment(new IacucProtocolReviewAttachment());
            }
            reviewAttachmentsBean.setHideReviewerName(getReviewCommentsService().setHideReviewerName(reviewAttachmentsBean.getReviewAttachments()));            
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

  
    
    
    /**
     * 
     * This method checks the document state to see if something has changed between the time the user
     * loaded the document to when they clicked on an action.
     * @param protocolForm
     */
    private boolean hasDocumentStateChanged(IacucProtocolForm protocolForm) {
        boolean result = false;
        
        Map<String,Object> primaryKeys = new HashMap<String, Object>();
        primaryKeys.put("protocolId", protocolForm.getProtocolDocument().getProtocol().getProtocolId());
        IacucProtocol dbProtocol = getBusinessObjectService().findByPrimaryKey(IacucProtocol.class, primaryKeys);
        
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
           result = !isDocumentPostprocessingComplete(protocolForm.getIacucProtocolDocument());
        }
        
        return result;
    }
    
    
    private boolean isDocumentPostprocessingComplete(IacucProtocolDocument document) {
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
    
    
    
    private ActionForward checkToSendNotification(ActionMapping mapping, ActionForward forward, ProtocolFormBase form, IacucProtocolNotificationRequestBean notificationRequestBean) {
        
              IacucProtocolNotificationRenderer renderer = null;
              IacucProtocol protocol = (IacucProtocol)notificationRequestBean.getProtocol();
              IacucProtocolForm protocolForm = (IacucProtocolForm)form;
              
              if (StringUtils.equals(IacucProtocolActionType.NOTIFY_IACUC, notificationRequestBean.getActionType())) {
                  renderer = new NotifyIacucNotificationRenderer(protocol, ((IacucActionHelper)protocolForm.getActionHelper()).getIacucProtocolNotifyIacucBean().getComment());
              } else if (StringUtils.equals(IacucProtocolActionType.IACUC_DELETED, notificationRequestBean.getActionType()) ||
                         StringUtils.equals(IacucProtocolActionType.IACUC_WITHDRAWN, notificationRequestBean.getActionType())) {
                  renderer = new IacucProtocolWithReasonNotificationRenderer(protocol, protocolForm.getActionHelper().getProtocolDeleteBean());
              } else if (StringUtils.equals(IacucProtocolActionType.REQUEST_DEACTIVATE, notificationRequestBean.getActionType()) ||
                         StringUtils.equals(IacucProtocolActionType.REQUEST_LIFT_HOLD, notificationRequestBean.getActionType())) {
                  IacucRequestActionNotificationBean requestNotificationRequestBean = (IacucRequestActionNotificationBean)notificationRequestBean; 
                  renderer = new IacucProtocolRequestActionNotificationRenderer(protocol, requestNotificationRequestBean.getReason());
              
              } else if (StringUtils.equals(IacucProtocolActionType.IACUC_REQUEST_SUSPEND, notificationRequestBean.getActionType())) {
                  IacucProtocolRequestBean iacucProtocolSuspendRequestBean = ((IacucActionHelper)protocolForm.getActionHelper()).getIacucProtocolSuspendRequestBean();
                  renderer = new NotifyIacucNotificationRenderer(protocol, iacucProtocolSuspendRequestBean.getReason());
              } else {
                  renderer = new IacucProtocolNotificationRenderer(protocol);
              }
                  
              IacucProtocolNotificationContext context = new IacucProtocolNotificationContext(protocol, notificationRequestBean.getActionType(), notificationRequestBean.getDescription(), renderer);
              if (protocolForm.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
                  context.setForwardName(forward.getName());
                  protocolForm.getNotificationHelper().initializeDefaultValues(context);
                  return mapping.findForward("iacucProtocolNotificationEditor");
              } else {
                  getNotificationService().sendNotificationAndPersist(context, new IacucProtocolNotification(), protocol);
                  return forward;
              }
          }    
    
    public ActionForward sendNotification(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocol protocol = (IacucProtocol)protocolForm.getProtocolDocument().getProtocol();
        
        IacucProtocolNotificationRenderer renderer = new IacucProtocolNotificationRenderer(protocol);
        IacucProtocolNotificationContext context = new IacucProtocolNotificationContext(protocol, NotificationType.AD_HOC_NOTIFICATION_TYPE, NotificationType.AD_HOC_CONTEXT, renderer);
        
        protocolForm.getNotificationHelper().initializeDefaultValues(context);
        
        return mapping.findForward("iacucProtocolNotificationEditor");
    }

    
    protected PersonService getPersonService() {
        return KraServiceLocator.getService(PersonService.class);
    }
    
    
    protected KcNotificationService getNotificationService() {
        return KraServiceLocator.getService(KcNotificationService.class);
    }
         
    public ActionForward viewCorrespondence(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        IacucActionHelper actionHelper = (IacucActionHelper) ((ProtocolFormBase) form).getActionHelper();
        PrintableAttachment source = new PrintableAttachment();
        ProtocolCorrespondence correspondence = actionHelper.getProtocolCorrespondence();
            
        source.setContent(correspondence.getCorrespondence());
        source.setContentType(Constants.PDF_REPORT_CONTENT_TYPE);
        source.setFileName("Correspondence-" + correspondence.getProtocolCorrespondenceType().getDescription() + Constants.PDF_FILE_EXTENSION);
        PrintingUtils.streamToResponse(source, response);
        
        return null;
    }
    
    /*
     * concrete class for AttachmentDataSource.
     * This is a similar class from printingserviceimpl
     * TODO : maybe should create a public class for this ?
     */
    private class PrintableAttachment extends AttachmentDataSource {
        private byte[] streamData;

        public byte[] getContent() {
            return streamData;
        }

        public void setContent(byte[] streamData) {
            this.streamData = streamData;
        }
    }

    public ActionForward saveCorrespondence(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return correspondenceAction(mapping, form, true);
    }

    public ActionForward closeCorrespondence(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        return correspondenceAction(mapping, form, false);
    }

    private ActionForward correspondenceAction(ActionMapping mapping, ActionForm form, boolean saveAction) {
        IacucProtocolForm protocolForm = ((IacucProtocolForm) form);
        IacucActionHelper actionHelper = (IacucActionHelper) protocolForm.getActionHelper();
        ProtocolCorrespondence correspondence = actionHelper.getProtocolCorrespondence();

        if (saveAction) {
            if (correspondence.getFinalFlag()) {
                correspondence.setFinalFlagTimestamp(KraServiceLocator.getService(DateTimeService.class).getCurrentTimestamp());
                           
            }
            getBusinessObjectService().save(correspondence);
        }
        // TODO : this is a hack for fullapprove to restore key
        if (GlobalVariables.getUserSession().retrieveObject("approvalComplCorrespondence") != null) {
               GlobalVariables.getUserSession().addObject(DocumentAuthorizerBase.USER_SESSION_METHOD_TO_CALL_COMPLETE_OBJECT_KEY, GlobalVariables.getUserSession().retrieveObject("approvalComplCorrespondence"));
               GlobalVariables.getUserSession().removeObject("approvalComplCorrespondence");
        }

        // TODO : forward will be based different action correspondence. this is a test for withdraw
        if (correspondence.getNotificationRequestBean() != null) {
            return checkToSendNotification(mapping, mapping.findForward(correspondence.getForwardName()), protocolForm,
                    (IacucProtocolNotificationRequestBean) correspondence.getNotificationRequestBean());
        } else {
            if (correspondence.isHoldingPage()) {
                return routeProtocolToHoldingPage(mapping, protocolForm);
            } else {
                return mapping.findForward(correspondence.getForwardName());
            }
        }
   
    }
    
    public ActionForward regenerateCorrespondence(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        int actionIndex = getSelectedLine(request);
        int attachmentIndex = getSelectedAttachment(request);
        ProtocolBase protocol = protocolForm.getActionHelper().getProtocol();
        ProtocolActionBase protocolAction = protocolForm.getActionHelper().getProtocol().getProtocolActions().get(actionIndex);
        ProtocolCorrespondence protocolCorrespondence = protocolAction.getProtocolCorrespondences().get(attachmentIndex);

        if (protocolCorrespondence == null) {
            LOG.info(NOT_FOUND_SELECTION + "protocolAction: " + actionIndex + ", protocolCorrespondence: " + attachmentIndex);
            // may want to tell the user the selection was invalid.
            return mapping.findForward(Constants.MAPPING_BASIC);
        }

        AttachmentDataSource dataSource = generateCorrespondenceDocument(protocol, protocolCorrespondence);
        PrintableAttachment source = new PrintableAttachment();
        if (dataSource != null) {
            protocolCorrespondence.setCorrespondence(dataSource.getContent());
            protocolCorrespondence.setFinalFlag(false);
            protocolCorrespondence.setCreateUser(GlobalVariables.getUserSession().getPrincipalName());
            protocolCorrespondence.setCreateTimestamp(KraServiceLocator.getService(DateTimeService.class).getCurrentTimestamp());
            protocolCorrespondence.setForwardName(PROTOCOL_ACTIONS_TAB);
            protocolForm.getActionHelper().setProtocolCorrespondence(protocolCorrespondence);
            getBusinessObjectService().save(protocolCorrespondence);
            return mapping.findForward(CORRESPONDENCE);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);

    }
    
    protected AttachmentDataSource generateCorrespondenceDocument(ProtocolBase protocol, ProtocolCorrespondence oldCorrespondence) throws PrintingException {
        IacucProtocolActionsCorrespondence correspondence = new IacucProtocolActionsCorrespondence(oldCorrespondence.getProtocolAction().getProtocolActionTypeCode());
        correspondence.setProtocol(protocol);
        return getProtocolActionCorrespondenceGenerationService().reGenerateCorrespondenceDocument(correspondence);
    } 

    private IacucProtocolActionCorrespondenceGenerationService getProtocolActionCorrespondenceGenerationService() {
        return KraServiceLocator.getService(IacucProtocolActionCorrespondenceGenerationService.class);
    }

    public ActionForward updateCorrespondence(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        int actionIndex = getSelectedLine(request);
        int attachmentIndex = getSelectedAttachment(request);
        ProtocolActionBase protocolAction = protocolForm.getActionHelper().getProtocol().getProtocolActions().get(actionIndex);
        protocolAction.refreshReferenceObject("protocolCorrespondences");
        ProtocolCorrespondence protocolCorrespondence = protocolAction.getProtocolCorrespondences().get(attachmentIndex);

        if (protocolCorrespondence == null) {
            LOG.info(NOT_FOUND_SELECTION + "protocolAction: " + actionIndex + ", protocolCorrespondence: " + attachmentIndex);
            // may want to tell the user the selection was invalid.
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
        protocolCorrespondence.setForwardName(PROTOCOL_ACTIONS_TAB);
        protocolForm.getActionHelper().setProtocolCorrespondence(protocolCorrespondence);

        return mapping.findForward(CORRESPONDENCE);

    }

    
    

    /**
     * 
     * This method is to print the sections selected.  This is more like coeus implementation.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward printProtocolSelectedItems(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        /**
         * resetting the form's document because it does not have a valid Workflow document unless this is done. KCIAC-389
         */
        IacucProtocolDocument ipd = (IacucProtocolDocument)this.getDocumentService().getByDocumentHeaderId(protocolForm.getDocId());
        protocolForm.setDocument(ipd);
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        AttachmentDataSource dataStream = getIacucProtocolPrintingService().printProtocolSelectedItems(protocolForm);
        if (dataStream.getContent() != null) {
            PrintingUtils.streamToResponse(dataStream, response);
            forward = null;
        }


        return forward;
    }
    
    /**
     * 
     * This method is to print protocol reports
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward printProtocolDocument(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        
        IacucActionHelper actionHelper = (IacucActionHelper)protocolForm.getActionHelper();
        
        if (applyRules(new ProtocolActionPrintEvent(protocolForm.getProtocolDocument(), actionHelper.getSummaryReport(),
            actionHelper.getFullReport(), actionHelper.getHistoryReport(), actionHelper.getReviewCommentsReport()))) {
            AttachmentDataSource dataStream = getIacucProtocolPrintingService().printProtocolDocument(protocolForm);
            if (dataStream.getContent() != null) {
                PrintingUtils.streamToResponse(dataStream, response);
                forward = null;
            }
        }
        return forward;
    }

    private IacucProtocolPrintingService getIacucProtocolPrintingService() {
        return KraServiceLocator.getService(IacucProtocolPrintingService.class);
    }
    
    public ActionForward sendReviewDeterminationNotificationAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucActionHelper actionHelper = (IacucActionHelper) protocolForm.getActionHelper();
        IacucProtocol protocol = (IacucProtocol) protocolForm.getProtocolDocument().getProtocol();
        IacucProtocolModifySubmissionBean bean = actionHelper.getIacucProtocolModifySubmissionBean();
        Date dueDate = bean.getDueDate();
        IacucProtocolReviewDeterminationNotificationRenderer renderer = new IacucProtocolReviewDeterminationNotificationRenderer(protocol, dueDate);
        IacucProtocolNotificationContext context = new IacucProtocolNotificationContext(protocol, IacucProtocolActionType.REVIEW_TYPE_DETERMINATION, "Review Type Determination", renderer);
        
        if (protocolForm.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
            protocolForm.getNotificationHelper().initializeDefaultValues(context);
            forward = mapping.findForward("iacucProtocolNotificationEditor");
        } else {
            getNotificationService().sendNotificationAndPersist(context, new IacucProtocolNotification(), protocol);
        }
        recordProtocolActionSuccess("Send Review Type Determination Notification");

        return forward;
    }
    
    protected IdentityService getIdentityService() {
        return KraServiceLocator.getService(IdentityService.class);
    }
    
    public ActionForward modifySubmissionAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        String callerString = String.format("assignReviewers");

        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
       
        IacucProtocol protocol = (IacucProtocol)protocolForm.getIacucProtocolDocument().getIacucProtocol();
        IacucProtocolTask task = new IacucProtocolTask(TaskName.IACUC_MODIFY_PROTOCOL_SUBMISSION, protocol);
        if (!hasDocumentStateChanged(protocolForm)) {
            if (isAuthorized(task)) {
                IacucProtocolModifySubmissionBean bean = ((IacucActionHelper) protocolForm.getActionHelper()).getIacucProtocolModifySubmissionBean();
                /*
                 * TODO: Fix rules for assign reviewers and add committee + schedule and take the valid protocol sub tables into account
                 */

                // for some reason auto-populating list is not working properly, so setting the reviewers on the bean explicitly
                setReviewers(form, request, bean);
                if (applyRules(new IacucProtocolModifySubmissionEvent(protocolForm.getProtocolDocument(), bean))) {                                        
                    List<ProtocolReviewerBeanBase> beans = bean.getReviewers();
                    
                    //clear the warnings before rendering the page.
                    getModifySubmissionService().modifySubmission(protocolForm.getProtocolDocument(), bean, beans);
                    GlobalVariables.getMessageMap().getWarningMessages().clear();
                    recordProtocolActionSuccess("Modify Submission");
    
                    forward = performNotificationRendering(mapping, protocolForm, beans);
                    IacucProtocolNotificationRenderer assignRenderer = new IacucProtocolNotificationRenderer(protocol);
                    IacucProtocolNotificationContext assignContext = new IacucProtocolNotificationContext(protocol, null, 
                            IacucProtocolActionType.MODIFY_PROTOCOL_SUBMISSION, "Modified", assignRenderer);
                    getNotificationService().sendNotificationAndPersist(assignContext, new IacucProtocolNotification(), protocol);
                    protocolForm.setReinitializeModifySubmissionFields(true);
                }
            }
        } else {
            GlobalVariables.getMessageMap().clearErrorMessages();
            GlobalVariables.getMessageMap().putError("documentstatechanged", KeyConstants.ERROR_IACUC_PROTOCOL_DOCUMENT_STATE_CHANGED,  new String[] {}); 
        }
        return mapping.findForward(Constants.MAPPING_BASIC);        
    }

    
   /**
    * 
    * This method...
    * @param request
    */
  
    protected void setReviewers(ActionForm form, HttpServletRequest request, IacucProtocolModifySubmissionBean submissionBean) {
        int number = Integer.parseInt(request.getParameter("actionHelper.iacucProtocolModifySubmissionBean.numberOfReviewers"));
        List<ProtocolReviewerBeanBase> beans = new ArrayList<ProtocolReviewerBeanBase>();
        for (int i= 0; i < number; i++) {
            String reviewerTypeCode = request.getParameter("actionHelper.iacucProtocolModifySubmissionBean.reviewer["+i+"].reviewerTypeCode");
            String personId = request.getParameter("actionHelper.iacucProtocolModifySubmissionBean.reviewer[" + i + "].personId");
            String fullName = request.getParameter("actionHelper.iacucProtocolModifySubmissionBean.reviewer["+i+"].fullName");
            String nonEmployeeFlag = request.getParameter("actionHelper.iacucProtocolModifySubmissionBean.reviewer["+i+"].nonEmployeeFlag");
            if (ObjectUtils.isNotNull(personId)) {
                IacucProtocolReviewerBean bean = new IacucProtocolReviewerBean();
                bean.setFullName(fullName); 
                bean.setPersonId(personId); 
                bean.setReviewerTypeCode(reviewerTypeCode);
                bean.setActionFlag(IacucProtocolReviewerBean.CREATE);
                beans.add(bean);
            }
        }
        submissionBean.setReviewers(beans);
    }
    
    protected IacucProtocolModifySubmissionService getModifySubmissionService() {
        return KraServiceLocator.getService(IacucProtocolModifySubmissionService.class);
    }
    
    protected ActionForward performNotificationRendering(ActionMapping mapping, IacucProtocolForm protocolForm, List<ProtocolReviewerBeanBase> beans) {
        IacucProtocol protocol = (IacucProtocol) protocolForm.getProtocolDocument().getProtocol();
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        IacucProtocolAssignReviewerNotificationRenderer renderer = new IacucProtocolAssignReviewerNotificationRenderer(protocol, "added");
        List<ProtocolNotificationRequestBeanBase> addReviewerNotificationBeans = getNotificationRequestBeans(beans,
                IacucProtocolReviewerBean.CREATE, true);
        List<ProtocolNotificationRequestBeanBase> removeReviewerNotificationBeans = getNotificationRequestBeans(beans,
                IacucProtocolReviewerBean.REMOVE, false);
        if (!CollectionUtils.isEmpty(addReviewerNotificationBeans)) {
            ProtocolNotificationRequestBeanBase notificationBean = addReviewerNotificationBeans.get(0);
            IacucProtocolNotificationContext context = new IacucProtocolNotificationContext((IacucProtocol)notificationBean.getProtocol(),
                (IacucProtocolOnlineReview)notificationBean.getProtocolOnlineReview(), notificationBean.getActionType(),
                notificationBean.getDescription(), renderer);
            if (protocolForm.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
                forward = checkToSendNotification(mapping, mapping.findForward(PROTOCOL_ACTIONS_TAB), protocolForm,
                        renderer, addReviewerNotificationBeans);
                if (!CollectionUtils.isEmpty(removeReviewerNotificationBeans)) {
                    GlobalVariables.getUserSession().addObject("removeReviewer", removeReviewerNotificationBeans);
                }
            }
        }
        else {
            if (!CollectionUtils.isEmpty(removeReviewerNotificationBeans)) {
                renderer = new IacucProtocolAssignReviewerNotificationRenderer(protocol, "removed");
                ProtocolNotificationRequestBeanBase notificationBean = removeReviewerNotificationBeans.get(0);
                IacucProtocolNotificationContext context = new IacucProtocolNotificationContext(protocol,
                    (IacucProtocolOnlineReview)notificationBean.getProtocolOnlineReview(), notificationBean.getActionType(),
                    notificationBean.getDescription(), renderer);
                if (protocolForm.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
                    forward = checkToSendNotification(mapping, mapping.findForward(PROTOCOL_ACTIONS_TAB),
                            protocolForm, renderer, removeReviewerNotificationBeans);
                }
            }
        }
        return forward;
    }
    
//  /*
//  * This is for assign reviewer and submit for review.  The notificationRequestBeans contains all 'added' or 'removed'
//  * reviewers.  All the roles recipient will be merged, then forward to protocolnotificationeditor for ad hoc notification 
//  * process.
//  */
 private ActionForward checkToSendNotification(ActionMapping mapping, ActionForward forward, ProtocolFormBase protocolForm,
         IacucProtocolNotificationRenderer renderer, List<ProtocolNotificationRequestBeanBase> notificationRequestBeans) {

     IacucProtocolNotificationContext context = new IacucProtocolNotificationContext((IacucProtocol) notificationRequestBeans.get(0).getProtocol(),
         (IacucProtocolOnlineReview)notificationRequestBeans.get(0).getProtocolOnlineReview(), notificationRequestBeans.get(0).getActionType(),
         notificationRequestBeans.get(0).getDescription(), renderer);
     context.setPopulateRole(true);
     if (protocolForm.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
         protocolForm.getNotificationHelper().initializeDefaultValues(context);
         List<NotificationTypeRecipient> notificationRecipients = protocolForm.getNotificationHelper()
                 .getNotificationRecipients();
         List<NotificationTypeRecipient> allRecipients = new ArrayList<NotificationTypeRecipient>();
         for (NotificationTypeRecipient recipient : notificationRecipients) {
             try {
                 NotificationTypeRecipient copiedRecipient = (NotificationTypeRecipient) ObjectUtils.deepCopy(recipient);
                 // populate role qualifier with proper context
                 context.populateRoleQualifiers(copiedRecipient);
                 allRecipients.add(copiedRecipient);
             }
             catch (Exception e) {
                 // TODO
             }
         }
         int i = 1;
         // add all new reviewer to recipients
         while (notificationRequestBeans.size() > i) {
             context = new IacucProtocolNotificationContext((IacucProtocol) notificationRequestBeans.get(i).getProtocol(), 
                                                  (IacucProtocolOnlineReview) notificationRequestBeans.get(i).getProtocolOnlineReview(), 
                                                  notificationRequestBeans.get(i).getActionType(), 
                                                  notificationRequestBeans.get(i).getDescription(), renderer);
             context.setPopulateRole(true);
             protocolForm.getNotificationHelper().initializeDefaultValues(context);
             List<NotificationTypeRecipient> recipients = protocolForm.getNotificationHelper().getNotificationRecipients();

             for (NotificationTypeRecipient recipient : recipients) {
                 try {
                     // note : need to deepcopy here. If I don't do that, then all reviewer role will have same
                     // notificationrecipient object returned from service call
                     // probably the object service/ojb has a cache ?
                     NotificationTypeRecipient copiedRecipient = (NotificationTypeRecipient) ObjectUtils.deepCopy(recipient);
                     context.populateRoleQualifiers(copiedRecipient);
                     allRecipients.add(copiedRecipient);
                 }
                 catch (Exception e) {
                     // TODO
                 }
             }
             i++;
         }
         protocolForm.getNotificationHelper().setNotificationRecipients(allRecipients);
         if (forward == null) {
             context.setForwardName("holdingPage");
         } else {
             context.setForwardName(forward.getName());
         }
         return mapping.findForward("iacucProtocolNotificationEditor");
     }
     else {

         return forward;
     }
 }
 
 private List<ProtocolNotificationRequestBeanBase> getNotificationRequestBeans(List<ProtocolReviewerBeanBase> beans, String actionFlag, boolean notNullFlag) {
     List<ProtocolNotificationRequestBeanBase> notificationRequestBeans = new ArrayList<ProtocolNotificationRequestBeanBase>();
     for (ProtocolReviewerBeanBase bean : beans) {
         if (StringUtils.equals(actionFlag, bean.getActionFlag()) && (notNullFlag == !StringUtils.isEmpty(bean.getReviewerTypeCode()))) {
             notificationRequestBeans.add(bean.getNotificationRequestBean());
         }
     }
     return notificationRequestBeans;
 }
 
 
     /**
      * Table a protocol.
      * 
      * @param mapping
      * @param form
      * @param request
      * @param response
      * @return
      * @throws Exception
      */
     public ActionForward tableProtocol(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    
         IacucProtocolForm protocolForm = (IacucProtocolForm) form;
         IacucProtocol protocol = protocolForm.getIacucProtocolDocument().getIacucProtocol();
         IacucProtocolTask task = new IacucProtocolTask(TaskName.IACUC_PROTOCOL_TABLE, protocol);
         
         if (!hasDocumentStateChanged(protocolForm)) {
             if (isAuthorized(task)) {
                 IacucProtocolTableBean actionBean = ((IacucActionHelper) protocolForm.getActionHelper()).getIacucProtocolTableBean();
                 getIacucProtocolTableService().tableProtocol(protocol, actionBean);
                 recordProtocolActionSuccess("Table ProtocolBase");                 
                 IacucProtocolNotificationRequestBean notificationRequestBean = new IacucProtocolNotificationRequestBean(protocol, IacucProtocolActionType.TABLED, "Tabled");
                 protocolForm.getActionHelper().setProtocolCorrespondence(getProtocolCorrespondence(protocolForm, PROTOCOL_ACTIONS_TAB, notificationRequestBean, false));

                 if (protocolForm.getActionHelper().getProtocolCorrespondence() != null) {
                     return mapping.findForward(CORRESPONDENCE);
                 } else {
                     return checkToSendNotification(mapping, mapping.findForward(PROTOCOL_ACTIONS_TAB), protocolForm, notificationRequestBean);
                 }

             }
         } else {
             GlobalVariables.getMessageMap().clearErrorMessages();
             GlobalVariables.getMessageMap().putError("documentstatechanged", KeyConstants.ERROR_PROTOCOL_DOCUMENT_STATE_CHANGED,  new String[] {}); 
         }
    
         return mapping.findForward(Constants.MAPPING_BASIC);
     }
    
    
    
    private IacucProtocolTableService  getIacucProtocolTableService() {
        return KraServiceLocator.getService(IacucProtocolTableService.class);
    }

    public ActionForward undoLastAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        
        if (!hasDocumentStateChanged(protocolForm)) {
            IacucProtocolDocument protocolDocument = (IacucProtocolDocument) protocolForm.getProtocolDocument();
            UndoLastActionBean undoLastActionBean = protocolForm.getActionHelper().getUndoLastActionBean();
            String lastActionType = undoLastActionBean.getLastAction().getProtocolActionTypeCode();
            
            IacucProtocolUndoLastActionService undoLastActionService = KraServiceLocator.getService(IacucProtocolUndoLastActionService.class);
            ProtocolDocumentBase updatedDocument = undoLastActionService.undoLastAction(protocolDocument, undoLastActionBean);
                       
    
            recordProtocolActionSuccess("Undo Last Action");
    
            if (!updatedDocument.getDocumentNumber().equals(protocolForm.getDocId())) {
                protocolForm.setDocId(updatedDocument.getDocumentNumber());
                loadDocument(protocolForm);
                protocolForm.getProtocolHelper().prepareView();
                return mapping.findForward(PROTOCOL_TAB);
            }
            if (IacucProtocolActionType.IACUC_MAJOR_REVISIONS_REQUIRED.equals(lastActionType)
                    || IacucProtocolActionType.IACUC_MINOR_REVISIONS_REQUIRED.equals(lastActionType)) {
                // undo SMR/SRR may need to create & route onln revw document,
                // this will need some time.   also, some change in db may not be viewable 
                // before document is routed.  so, add this holding page for undo SMR/SRR.
                return routeProtocolToHoldingPage(mapping, protocolForm);
            }
        } else {
            GlobalVariables.getMessageMap().clearErrorMessages();
            GlobalVariables.getMessageMap().putError("documentstatechanged", KeyConstants.ERROR_PROTOCOL_DOCUMENT_STATE_CHANGED,  new String[] {}); 
        }
        return mapping.findForward(Constants.MAPPING_BASIC);

    }
    

}
