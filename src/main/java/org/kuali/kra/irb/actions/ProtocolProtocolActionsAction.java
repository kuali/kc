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
package org.kuali.kra.irb.actions;

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
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.notification.AwardNotificationContext;
import org.kuali.kra.award.notification.AwardNotificationRenderer;
import org.kuali.kra.bo.AttachmentFile;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.bo.CoeusSubModule;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.common.notification.bo.NotificationTypeRecipient;
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolAction;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.actions.abandon.ProtocolAbandonService;
import org.kuali.kra.irb.actions.amendrenew.CreateAmendmentEvent;
import org.kuali.kra.irb.actions.amendrenew.CreateRenewalEvent;
import org.kuali.kra.irb.actions.amendrenew.ModifyAmendmentSectionsEvent;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewService;
import org.kuali.kra.irb.actions.approve.ProtocolApproveBean;
import org.kuali.kra.irb.actions.approve.ProtocolApproveEvent;
import org.kuali.kra.irb.actions.approve.ProtocolApproveService;
import org.kuali.kra.irb.actions.assignagenda.ProtocolAssignToAgendaBean;
import org.kuali.kra.irb.actions.assignagenda.ProtocolAssignToAgendaEvent;
import org.kuali.kra.irb.actions.assignagenda.ProtocolAssignToAgendaService;
import org.kuali.kra.irb.actions.assigncmtsched.ProtocolAssignCmtSchedBean;
import org.kuali.kra.irb.actions.assigncmtsched.ProtocolAssignCmtSchedEvent;
import org.kuali.kra.irb.actions.assigncmtsched.ProtocolAssignCmtSchedService;
import org.kuali.kra.irb.actions.assignreviewers.ProtocolAssignReviewersBean;
import org.kuali.kra.irb.actions.assignreviewers.ProtocolAssignReviewersEvent;
import org.kuali.kra.irb.actions.assignreviewers.ProtocolAssignReviewersService;
import org.kuali.kra.irb.actions.copy.ProtocolCopyService;
import org.kuali.kra.irb.actions.correction.AdminCorrectionBean;
import org.kuali.kra.irb.actions.correction.AdminCorrectionService;
import org.kuali.kra.irb.actions.correction.ProtocolAdminCorrectionEvent;
import org.kuali.kra.irb.actions.decision.CommitteeDecision;
import org.kuali.kra.irb.actions.decision.CommitteeDecisionAbstainerEvent;
import org.kuali.kra.irb.actions.decision.CommitteeDecisionEvent;
import org.kuali.kra.irb.actions.decision.CommitteeDecisionRecuserEvent;
import org.kuali.kra.irb.actions.decision.CommitteeDecisionService;
import org.kuali.kra.irb.actions.decision.CommitteePerson;
import org.kuali.kra.irb.actions.delete.ProtocolDeleteService;
import org.kuali.kra.irb.actions.followup.FollowupActionService;
import org.kuali.kra.irb.actions.genericactions.ProtocolGenericActionBean;
import org.kuali.kra.irb.actions.genericactions.ProtocolGenericActionEvent;
import org.kuali.kra.irb.actions.genericactions.ProtocolGenericActionService;
import org.kuali.kra.irb.actions.grantexemption.ProtocolGrantExemptionBean;
import org.kuali.kra.irb.actions.grantexemption.ProtocolGrantExemptionEvent;
import org.kuali.kra.irb.actions.grantexemption.ProtocolGrantExemptionService;
import org.kuali.kra.irb.actions.history.ProtocolHistoryFilterDatesEvent;
import org.kuali.kra.irb.actions.modifysubmission.ProtocolModifySubmissionBean;
import org.kuali.kra.irb.actions.modifysubmission.ProtocolModifySubmissionEvent;
import org.kuali.kra.irb.actions.modifysubmission.ProtocolModifySubmissionService;
import org.kuali.kra.irb.actions.noreview.ProtocolReviewNotRequiredBean;
import org.kuali.kra.irb.actions.noreview.ProtocolReviewNotRequiredEvent;
import org.kuali.kra.irb.actions.noreview.ProtocolReviewNotRequiredService;
import org.kuali.kra.irb.actions.notification.AssignReviewerNotificationRenderer;
import org.kuali.kra.irb.actions.notification.NotifyIrbNotificationRenderer;
import org.kuali.kra.irb.actions.notification.ProtocolNotificationRequestBean;
import org.kuali.kra.irb.actions.notifyirb.ProtocolActionAttachment;
import org.kuali.kra.irb.actions.notifyirb.ProtocolNotifyIrbBean;
import org.kuali.kra.irb.actions.notifyirb.ProtocolNotifyIrbService;
import org.kuali.kra.irb.actions.print.ProtocolActionPrintEvent;
import org.kuali.kra.irb.actions.print.ProtocolPrintType;
import org.kuali.kra.irb.actions.print.ProtocolPrintingService;
import org.kuali.kra.irb.actions.request.ProtocolRequestBean;
import org.kuali.kra.irb.actions.request.ProtocolRequestEvent;
import org.kuali.kra.irb.actions.request.ProtocolRequestRule;
import org.kuali.kra.irb.actions.request.ProtocolRequestService;
import org.kuali.kra.irb.actions.reviewcomments.ProtocolAddReviewAttachmentEvent;
import org.kuali.kra.irb.actions.reviewcomments.ProtocolAddReviewCommentEvent;
import org.kuali.kra.irb.actions.reviewcomments.ProtocolManageReviewAttachmentEvent;
import org.kuali.kra.irb.actions.reviewcomments.ReviewAttachmentsBean;
import org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsBean;
import org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsService;
import org.kuali.kra.irb.actions.risklevel.ProtocolAddRiskLevelEvent;
import org.kuali.kra.irb.actions.risklevel.ProtocolRiskLevel;
import org.kuali.kra.irb.actions.risklevel.ProtocolRiskLevelBean;
import org.kuali.kra.irb.actions.risklevel.ProtocolRiskLevelService;
import org.kuali.kra.irb.actions.risklevel.ProtocolUpdateRiskLevelEvent;
import org.kuali.kra.irb.actions.submit.ProtocolReviewerBean;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitAction;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitActionEvent;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitActionService;
import org.kuali.kra.irb.actions.submit.ValidProtocolActionAction;
import org.kuali.kra.irb.actions.undo.UndoLastActionBean;
import org.kuali.kra.irb.actions.undo.UndoLastActionService;
import org.kuali.kra.irb.actions.withdraw.ProtocolWithdrawService;
import org.kuali.kra.irb.auth.GenericProtocolAuthorizer;
import org.kuali.kra.irb.auth.ProtocolTask;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondence;
import org.kuali.kra.irb.noteattachment.AddProtocolNotepadEvent;
import org.kuali.kra.irb.noteattachment.AddProtocolNotepadRule;
import org.kuali.kra.irb.noteattachment.AddProtocolNotepadRuleImpl;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentBase;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentPersonnel;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentProtocol;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentService;
import org.kuali.kra.irb.noteattachment.ProtocolNotepad;
import org.kuali.kra.irb.notification.IRBNotificationContext;
import org.kuali.kra.irb.notification.IRBNotificationRenderer;
import org.kuali.kra.irb.onlinereview.ProtocolReviewAttachment;
import org.kuali.kra.irb.questionnaire.ProtocolQuestionnaireAuditRule;
import org.kuali.kra.irb.summary.AttachmentSummary;
import org.kuali.kra.irb.summary.ProtocolSummary;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.kra.meeting.MinuteEntryType;
import org.kuali.kra.printing.Printable;
import org.kuali.kra.printing.print.AbstractPrint;
import org.kuali.kra.printing.service.WatermarkService;
import org.kuali.kra.printing.util.PrintingUtils;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
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
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.util.WebUtils;
import org.kuali.rice.kns.web.struts.action.AuditModeAction;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.ObjectUtils;
import org.springframework.util.CollectionUtils;

/**
 * The set of actions for the Protocol Actions tab.
 */
public class ProtocolProtocolActionsAction extends ProtocolAction implements AuditModeAction {

    private static final Log LOG = LogFactory.getLog(ProtocolProtocolActionsAction.class);
    private static final String CONFIRM_NO_ACTION = "";
    private static final String CONFIRM_DELETE_ACTION_ATT = "confirmDeleteActionAttachment";
    private static final String CONFIRM_FOLLOWUP_ACTION = "confirmAddFollowupAction";
    
    private static final String PROTOCOL_TAB = "protocol";
    private static final String PROTOCOL_ACTIONS_TAB = "protocolActions";
    
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
     
    
    private static final Map<String, String> PRINTTAG_MAP = new HashMap<String, String>() {
        {
            put("summary", "PROTOCOL_SUMMARY_VIEW_REPORT");
            put("full", "PROTOCOL_FULL_PROTOCOL_REPORT");
            put("history", "PROTOCOL_PROTOCOL_HISTORY_REPORT");
            put("comments", "PROTOCOL_REVIEW_COMMENTS_REPORT");
    }};

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
        ProtocolForm protocolForm = (ProtocolForm) form;
        if (StringUtils.isNotBlank(((ProtocolForm) form).getQuestionnaireHelper().getSubmissionActionTypeCode())) {
            //    && StringUtils.isBlank(getSubmitActionType(request))) {
            // questionnaire is already loaded for this action.
            ProtocolSubmissionBeanBase submissionBean = getSubmissionBean(form, protocolForm.getQuestionnaireHelper()
                    .getSubmissionActionTypeCode());
            if (!CollectionUtils.isEmpty(protocolForm.getQuestionnaireHelper().getAnswerHeaders())) {
                setQnCompleteStatus(protocolForm.getQuestionnaireHelper().getAnswerHeaders());
                submissionBean.setAnswerHeaders(protocolForm.getQuestionnaireHelper().getAnswerHeaders());
            } 
        }
        ActionForward actionForward = super.execute(mapping, form, request, response);
        protocolForm.getActionHelper().prepareView();
        // submit action may change "submission details", so re-initializa it
        protocolForm.getActionHelper().initSubmissionDetails();
        
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


        ProtocolForm protocolForm = (ProtocolForm) form;

        ApplicationTask task = new ApplicationTask(TaskName.CREATE_PROTOCOL);
        if (isAuthorized(task)) {
            String newDocId = getProtocolCopyService().copyProtocol(protocolForm.getDocument()).getDocumentNumber();

            // Switch over to the new protocol document and
            // go to the Protocol tab web page.

            protocolForm.setDocId(newDocId);
            protocolForm.setViewOnly(false);
            loadDocument(protocolForm);
            protocolForm.getDocument().setViewOnly(protocolForm.isViewOnly());
            protocolForm.getActionHelper().setCurrentSubmissionNumber(-1);
            protocolForm.getProtocolHelper().prepareView();
            protocolForm.getActionHelper().prepareCommentsView();

            return mapping.findForward(PROTOCOL_TAB);
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /** {@inheritDoc} */
    public ActionForward activate(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        return new AuditActionHelper().setAuditMode(mapping, (ProtocolForm) form, true);
    }

    /** {@inheritDoc} */
    public ActionForward deactivate(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        return new AuditActionHelper().setAuditMode(mapping, (ProtocolForm) form, false);
    }

    /**
     * Refreshes the page. We only need to redraw the page. This method is used when JavaScript is disabled. During a review
     * submission action, the user will have to refresh the page. For example, after a committee is selected, the page needs to be
     * refreshed so that the available scheduled dates for that committee can be displayed in the drop-down menu for the scheduled
     * dates. Please see ProtocolSubmitAction.prepareView() for how the Submit for Review works on a refresh.
     * 
     * @param mapping the mapping associated with this action.
     * @param form the Protocol form.
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
     * @param form the Protocol form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the name of the HTML page to display
     * @throws Exception
     */
    public ActionForward submitForReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);

        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument protocolDocument = protocolForm.getProtocolDocument();
        protocolForm.setAuditActivated(true);
        ProtocolTask task = new ProtocolTask(TaskName.SUBMIT_PROTOCOL, protocolDocument.getProtocol());
        if (isAuthorized(task)) {
            ProtocolSubmitAction submitAction = protocolForm.getActionHelper().getProtocolSubmitAction();            
            if (applyRules(new ProtocolSubmitActionEvent(protocolDocument, submitAction))) {
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
    
    private boolean isCommitteeMeetingAssignedMaxProtocols(String committeeId, String scheduleId) {
        boolean isMax = false;
        
        Committee committee = getCommitteeService().getCommitteeById(committeeId);
        if (committee != null) {
            CommitteeSchedule schedule = getCommitteeService().getCommitteeSchedule(committee, scheduleId);
            if (schedule != null) {
                int currentSubmissionCount = (schedule.getProtocolSubmissions() == null) ? 0 : activeSubmissonCount(schedule.getProtocolSubmissions());
                int maxSubmissionCount = schedule.getMaxProtocols();
                isMax = currentSubmissionCount >= maxSubmissionCount;
            }
        }
        
        return isMax;
    }

    private int activeSubmissonCount(List<ProtocolSubmission> submissions) {
        int count = 0;
        for (ProtocolSubmission submission : submissions) {
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
     * @param form The Protocol form.
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
     * Submits the Protocol for review and calculates the redirect back to the portal page, adding in the proper parameters for displaying a message to the
     * user upon successful submission.
     * 
     * @param mapping The mapping associated with this action.
     * @param form The Protocol form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the destination
     * @throws Exception
     */
    private ActionForward submitForReviewAndRedirect(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument protocolDocument = protocolForm.getProtocolDocument();
        ProtocolSubmitAction submitAction = protocolForm.getActionHelper().getProtocolSubmitAction();
        
        getProtocolSubmitActionService().submitToIrbForReview(protocolDocument.getProtocol(), submitAction);
        protocolForm.getActionHelper().getAssignCmtSchedBean().init();
        
        super.route(mapping, protocolForm, request, response);
        AssignReviewerNotificationRenderer renderer = new AssignReviewerNotificationRenderer(protocolForm.getProtocolDocument().getProtocol(), "added");
        List<ProtocolNotificationRequestBean> addReviewerNotificationBeans = getNotificationRequestBeans(submitAction.getReviewers(),ProtocolReviewerBean.CREATE);
        if (!CollectionUtils.isEmpty(addReviewerNotificationBeans)) {
            ProtocolNotificationRequestBean notificationBean = addReviewerNotificationBeans.get(0);
            IRBNotificationContext context = new IRBNotificationContext(notificationBean.getProtocol(),
                    notificationBean.getProtocolOnlineReview(), notificationBean.getActionType(),
                    notificationBean.getDescription(), renderer);
            if (protocolForm.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
                return checkToSendNotification(mapping, null, protocolForm, renderer, addReviewerNotificationBeans);
            }
        }
        return routeProtocolToHoldingPage(mapping, protocolForm);
        //return createSuccessfulSubmitRedirect("Protocol", protocolDocument.getProtocol().getProtocolNumber(), request, mapping, protocolForm);
    }

    private ActionForward routeProtocolToHoldingPage(ActionMapping mapping, ProtocolForm protocolForm) {
        String routeHeaderId = protocolForm.getDocument().getDocumentNumber();
        String returnLocation = buildActionUrl(routeHeaderId, Constants.MAPPING_PROTOCOL_ACTIONS, "ProtocolDocument");
        
        ActionForward basicForward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
        ActionForward holdingPageForward = mapping.findForward(Constants.MAPPING_HOLDING_PAGE);
        return routeToHoldingPage(basicForward, basicForward, holdingPageForward, returnLocation);

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

        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_WITHDRAW, protocolForm.getProtocolDocument().getProtocol());
        
        if (!hasDocumentStateChanged(protocolForm)) {
            if (isAuthorized(task)) {
                ProtocolDocument pd = getProtocolWithdrawService().withdraw(protocolForm.getProtocolDocument().getProtocol(),
                        protocolForm.getActionHelper().getProtocolWithdrawBean());
    
                protocolForm.setDocId(pd.getDocumentNumber());
                loadDocument(protocolForm);
                protocolForm.getProtocolHelper().prepareView();
                
                recordProtocolActionSuccess("Withdraw");
                return checkToSendNotification(mapping, mapping.findForward(PROTOCOL_TAB), protocolForm, new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(), ProtocolActionType.WITHDRAWN, "Withdrawn"));
    
//                return mapping.findForward(PROTOCOL_TAB);
            }
        } else {
            GlobalVariables.getMessageMap().clearErrorMessages();
            GlobalVariables.getMessageMap().putError("documentstatechanged", KeyConstants.ERROR_PROTOCOL_DOCUMENT_STATE_CHANGED,  new String[] {}); 
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * Notify the IRB office.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward notifyIrbProtocol(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ProtocolForm protocolForm = (ProtocolForm) form;
        protocolForm.getActionHelper().getProtocolNotifyIrbBean().setAnswerHeaders(getAnswerHeaders(form, ProtocolActionType.NOTIFY_IRB));
        if (isMandatoryQuestionnaireComplete(protocolForm.getActionHelper().getProtocolNotifyIrbBean(), "actionHelper.protocolNotifyIrbBean.datavalidation")) {
            getProtocolNotifyIrbService().submitIrbNotification(protocolForm.getProtocolDocument().getProtocol(),
                    protocolForm.getActionHelper().getProtocolNotifyIrbBean());
            protocolForm.getQuestionnaireHelper().setAnswerHeaders(new ArrayList<AnswerHeader>());
            LOG.info("notifyIrbProtocol " + protocolForm.getProtocolDocument().getDocumentNumber());

            recordProtocolActionSuccess("Notify IRB");
            return checkToSendNotification(mapping, mapping.findForward(PROTOCOL_ACTIONS_TAB), protocolForm, new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(),ProtocolActionType.NOTIFY_IRB, "Notify IRB"));
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /*
     * get the saved answer headers
     */
    private List<AnswerHeader> getAnswerHeaders(ActionForm form, String actionTypeCode) {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ModuleQuestionnaireBean moduleQuestionnaireBean = new ModuleQuestionnaireBean(CoeusModule.IRB_MODULE_CODE, protocolForm.getProtocolDocument().getProtocol().getProtocolNumber() + "T", CoeusSubModule.PROTOCOL_SUBMISSION, actionTypeCode, false);
        return getQuestionnaireAnswerService().getQuestionnaireAnswer(moduleQuestionnaireBean);

    }
    
    /*
     * check if the mandatory submission questionnaire is complete before submit a request/notify irb action
     */
    private boolean isMandatoryQuestionnaireComplete(ProtocolSubmissionBeanBase submissionBean, String errorKey) {
        boolean valid = true;
        ProtocolQuestionnaireAuditRule auditRule = new ProtocolQuestionnaireAuditRule();
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

        ProtocolForm protocolForm = (ProtocolForm) form;

        ProtocolTask task = new ProtocolTask(TaskName.CREATE_PROTOCOL_AMMENDMENT, protocolForm.getProtocolDocument().getProtocol());
        if (isAuthorized(task)) {
            if (!applyRules(new CreateAmendmentEvent(protocolForm.getProtocolDocument(), Constants.PROTOCOL_CREATE_AMENDMENT_KEY,
                protocolForm.getActionHelper().getProtocolAmendmentBean()))) {
                return mapping.findForward(Constants.MAPPING_BASIC);
            }

            String newDocId = getProtocolAmendRenewService().createAmendment(protocolForm.getProtocolDocument(),
                    protocolForm.getActionHelper().getProtocolAmendmentBean());
            // Switch over to the new protocol document and
            // go to the Protocol tab web page.

            protocolForm.setDocId(newDocId);
            loadDocument(protocolForm);
            protocolForm.getActionHelper().setCurrentSubmissionNumber(-1);
            protocolForm.getProtocolHelper().prepareView();
            
            recordProtocolActionSuccess("Create Amendment");
            
            return mapping.findForward(PROTOCOL_TAB);
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

        ProtocolForm protocolForm = (ProtocolForm) form;
        Protocol protocol = protocolForm.getProtocolDocument().getProtocol();
        protocolForm.getActionHelper().setCurrentTask(TaskName.MODIFY_PROTOCOL_AMMENDMENT_SECTIONS);
        ProtocolTask task = new ProtocolTask(TaskName.MODIFY_PROTOCOL_AMMENDMENT_SECTIONS, protocol);
        if (isAuthorized(task)) {
            if (!applyRules(new ModifyAmendmentSectionsEvent(protocolForm.getProtocolDocument(), Constants.PROTOCOL_MODIFY_AMENDMENT_KEY,
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

        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolTask task = new ProtocolTask(TaskName.CREATE_PROTOCOL_RENEWAL, protocolForm.getProtocolDocument().getProtocol());
        if (isAuthorized(task)) {
            if (!applyRules(new CreateRenewalEvent(protocolForm.getProtocolDocument(),
                    Constants.PROTOCOL_CREATE_RENEWAL_SUMMARY_KEY, protocolForm.getActionHelper().getRenewalSummary()))) {
                    return mapping.findForward(Constants.MAPPING_BASIC);
                }
            String newDocId = getProtocolAmendRenewService().createRenewal(protocolForm.getProtocolDocument(),((ProtocolForm) form).getActionHelper().getRenewalSummary());
            // Switch over to the new protocol document and
            // go to the Protocol tab web page.

            protocolForm.setDocId(newDocId);
            loadDocument(protocolForm);

            protocolForm.getActionHelper().setCurrentSubmissionNumber(-1);
            protocolForm.getProtocolHelper().prepareView();
            
            recordProtocolActionSuccess("Create Renewal without Amendment");
            
            // Form fields copy needed to support modifyAmendmentSections
            protocolForm.getActionHelper().getProtocolAmendmentBean().setSummary(protocolForm.getActionHelper().getRenewalSummary());
            
            return mapping.findForward(PROTOCOL_TAB);
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

        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolTask task = new ProtocolTask(TaskName.CREATE_PROTOCOL_RENEWAL, protocolForm.getProtocolDocument().getProtocol());
        if (isAuthorized(task)) {
            if (!applyRules(new CreateAmendmentEvent(protocolForm.getProtocolDocument(),
                Constants.PROTOCOL_CREATE_RENEWAL_WITH_AMENDMENT_KEY, protocolForm.getActionHelper()
                        .getProtocolRenewAmendmentBean()))) {
                return mapping.findForward(Constants.MAPPING_BASIC);
            }

            String newDocId = getProtocolAmendRenewService().createRenewalWithAmendment(protocolForm.getProtocolDocument(),
                    protocolForm.getActionHelper().getProtocolRenewAmendmentBean());
            // Switch over to the new protocol document and
            // go to the Protocol tab web page.

            protocolForm.setDocId(newDocId);
            loadDocument(protocolForm);

            protocolForm.getActionHelper().setCurrentSubmissionNumber(-1);
            protocolForm.getProtocolHelper().prepareView();
            
            recordProtocolActionSuccess("Create Renewal with Amendment");
            
            // Form fields copy needed to support modifyAmendmentSections
            protocolForm.getActionHelper().setProtocolAmendmentBean(protocolForm.getActionHelper().getProtocolRenewAmendmentBean());
            
            return mapping.findForward(PROTOCOL_TAB);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Delete a Protocol/Amendment/Renewal. Remember that amendments and renewals are simply protocol documents that were copied
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

        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_AMEND_RENEW_DELETE, protocolForm.getProtocolDocument().getProtocol());
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
            ProtocolForm protocolForm = (ProtocolForm) form;
            getProtocolDeleteService().delete(protocolForm.getProtocolDocument().getProtocol(),
                    protocolForm.getActionHelper().getProtocolDeleteBean());
            
            recordProtocolActionSuccess("Delete Protocol, Amendment, or Renewal");
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
        ProtocolDocument doc = ((ProtocolForm) form).getDocument();
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
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        int selected = getSelectedLine(request);
        ProtocolAttachmentProtocol attachment = protocolForm.getProtocolDocument().getProtocol().getActiveAttachmentProtocolsNoDelete().get(selected);
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
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        int selected = getSelectedLine(request);
        ProtocolAttachmentPersonnel personAttach = protocolForm.getProtocolDocument().getProtocol().getAttachmentPersonnels().get(selected);
        return printPersonnelAttachmentProtocol(mapping, response, personAttach,protocolForm);

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
        ProtocolForm protocolForm = (ProtocolForm) form;
        Protocol protocol = protocolForm.getProtocolDocument().getProtocol();
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        ActionHelper actionHelper = protocolForm.getActionHelper();
        StringBuffer fileName = new StringBuffer().append("Protocol-");
        if (applyRules(new ProtocolActionPrintEvent(protocolForm.getProtocolDocument(), actionHelper.getSummaryReport(),
            actionHelper.getFullReport(), actionHelper.getHistoryReport(), actionHelper.getReviewCommentsReport()))) {
            ProtocolPrintType printType = ProtocolPrintType.PROTOCOL_FULL_PROTOCOL_REPORT;
            String reportName = protocol.getProtocolNumber()+"-"+printType.getReportName();
            AttachmentDataSource dataStream = getProtocolPrintingService().print(reportName,getPrintReportArtifacts(protocolForm, fileName));
            if (dataStream.getContent() != null) {
                dataStream.setFileName(fileName.toString());
                PrintingUtils.streamToResponse(dataStream, response);
                forward = null;
            }
        }


        return forward;
    }
    private Map<Class,Object> getReportOptions(ProtocolForm protocolForm, ProtocolPrintType printType) {
        Map<Class,Object> reportParameters = new HashMap<Class, Object>();
        ProtocolSummaryPrintOptions summaryOptions = protocolForm.getActionHelper().getProtocolSummaryPrintOptions();
        if(printType.equals(ProtocolPrintType.PROTOCOL_FULL_PROTOCOL_REPORT)){
            summaryOptions.setActions(true);
            summaryOptions.setAmendmentRenewalHistory(true);
            summaryOptions.setAmmendmentRenewalSummary(true);
            summaryOptions.setAreaOfResearch(true);
            summaryOptions.setAttachments(true);
            summaryOptions.setCorrespondents(true);
            summaryOptions.setDocuments(true);
            summaryOptions.setFundingSource(true);
            summaryOptions.setInvestigator(true);
            summaryOptions.setNotes(true);
            summaryOptions.setOrganizaition(true);
            summaryOptions.setProtocolDetails(true);
            summaryOptions.setReferences(true);
            summaryOptions.setRiskLevel(true);
            summaryOptions.setRoles(true);
            summaryOptions.setSpecialReview(true);
            summaryOptions.setStudyPersonnels(true);
            summaryOptions.setSubjects(true);
        }
        reportParameters.put(ProtocolSummaryPrintOptions.class, summaryOptions);
        return reportParameters;
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
        ProtocolForm protocolForm = (ProtocolForm) form;
        Protocol protocol = protocolForm.getProtocolDocument().getProtocol();
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        String fileName = "Protocol_Summary_Report.pdf";
        ProtocolPrintType printType = ProtocolPrintType.PROTOCOL_FULL_PROTOCOL_REPORT;
        String reportName = protocol.getProtocolNumber() + "-" + printType.getReportName();
        AttachmentDataSource dataStream = getProtocolPrintingService().print(reportName, getPrintArtifacts(protocolForm));
        if (dataStream.getContent() != null) {
            dataStream.setFileName(fileName.toString());
            PrintingUtils.streamToResponse(dataStream, response);
            forward = null;
        }


        return forward;
    }

    
    public ActionForward printProtocolQuestionnaires(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        Protocol protocol = protocolForm.getProtocolDocument().getProtocol();
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        String fileName = "Protocol_questionnaire_Report.pdf";
//        Integer selectedQid = getSelectedLine(request);
        String reportName = protocol.getProtocolNumber() + "-" + "ProtocolQuestionnaires";
        AttachmentDataSource dataStream = getProtocolPrintingService().print(reportName, getQuestionnairePrintingService().getQuestionnairePrintable(protocolForm.getProtocolDocument().getProtocol(), protocolForm.getActionHelper().getQuestionnairesToPrints()));
        if (dataStream.getContent() != null) {
            dataStream.setFileName(fileName.toString());
            PrintingUtils.streamToResponse(dataStream, response);
            forward = null;
        }


        return forward;
    }

    /*
     * get printables for protocol & questionnaires.
     * Protocol only has one printable and each questionnaire has its own printable.
     */
    private List<Printable> getPrintArtifacts(ActionForm form) {
        ProtocolForm protocolForm = (ProtocolForm) form;
        List<Printable> printableArtifactList = new ArrayList<Printable>();
        ProtocolPrintType printType = ProtocolPrintType.valueOf(PRINTTAG_MAP.get("full"));

        AbstractPrint printable = (AbstractPrint)getProtocolPrintingService().getProtocolPrintable(printType);
        printable.setPrintableBusinessObject(protocolForm.getProtocolDocument().getProtocol());
     //   Map reportParameters = getReportOptions(protocolForm,ProtocolPrintType.PROTOCOL_FULL_PROTOCOL_REPORT);
        Map reportParameters = new HashMap();
        ProtocolSummaryPrintOptions summaryOptions = protocolForm.getActionHelper().getProtocolPrintOption();
        
        reportParameters.put(ProtocolSummaryPrintOptions.class, summaryOptions);

        printable.setReportParameters(reportParameters);
        printableArtifactList.add(printable);
        if (summaryOptions.isReviewComments()) {
            Map reportParameters1 = getReportOptions(protocolForm,ProtocolPrintType.PROTOCOL_REVIEW_COMMENTS_REPORT);
            AbstractPrint printable1 = (AbstractPrint)getProtocolPrintingService().getProtocolPrintable(ProtocolPrintType.valueOf(PRINTTAG_MAP.get("comments")));
            printable1.setPrintableBusinessObject(protocolForm.getProtocolDocument().getProtocol());
            printable1.setReportParameters(reportParameters1);
            printableArtifactList.add(printable1);
            
        }
        /** kcirb-1159 is closed for not fixing
        if (summaryOptions.isProtocolHistory()) {
            Map reportParameters1 = getReportOptions(protocolForm,ProtocolPrintType.PROTOCOL_PROTOCOL_HISTORY_REPORT);
            AbstractPrint printable1 = (AbstractPrint)getProtocolPrintingService().getProtocolPrintable(ProtocolPrintType.valueOf(PRINTTAG_MAP.get("history")));
            printable1.setPrintableBusinessObject(protocolForm.getProtocolDocument().getProtocol());
            printable1.setReportParameters(reportParameters1);
            printableArtifactList.add(printable1);
            
        }
        **/
//        printableArtifactList.addAll(getQuestionnairePrintingService().getQuestionnairePrintable(protocolForm.getProtocolDocument().getProtocol(), protocolForm.getActionHelper().getQuestionnairesToPrints()));

        return printableArtifactList;
    }

    
    /*
     * set up all artifacts and filename
     */
    private List<Printable> getPrintReportArtifacts(ActionForm form, StringBuffer fileName) {
        ProtocolForm protocolForm = (ProtocolForm) form;
        Boolean printSummary = protocolForm.getActionHelper().getSummaryReport();
        Boolean printFull = protocolForm.getActionHelper().getFullReport();
        Boolean printHistory = protocolForm.getActionHelper().getHistoryReport();
        Boolean printReviewComments = protocolForm.getActionHelper().getReviewCommentsReport();
        List<Printable> printableArtifactList = new ArrayList<Printable>();
        if (printSummary) {
            Map reportParameters = getReportOptions(protocolForm,ProtocolPrintType.PROTOCOL_SUMMARY_VIEW_REPORT);
            printableArtifactList.add(getPrintableArtifacts(protocolForm.getProtocolDocument().getProtocol(), "summary", fileName,reportParameters));
            protocolForm.getActionHelper().setSummaryReport(false);
        }
        if (printFull) {
            Map reportParameters = getReportOptions(protocolForm,ProtocolPrintType.PROTOCOL_FULL_PROTOCOL_REPORT);
            printableArtifactList.add(getPrintableArtifacts(protocolForm.getProtocolDocument().getProtocol(), "full", fileName,reportParameters));
            protocolForm.getActionHelper().setFullReport(false);
        }
        if (printHistory) {
            Map reportParameters = getReportOptions(protocolForm,ProtocolPrintType.PROTOCOL_PROTOCOL_HISTORY_REPORT);
            printableArtifactList.add(getPrintableArtifacts(protocolForm.getProtocolDocument().getProtocol(), "history", fileName,reportParameters));
            protocolForm.getActionHelper().setHistoryReport(false);
        }
        if (printReviewComments) {
            Map reportParameters = getReportOptions(protocolForm,ProtocolPrintType.PROTOCOL_REVIEW_COMMENTS_REPORT);
            printableArtifactList
                    .add(getPrintableArtifacts(protocolForm.getProtocolDocument().getProtocol(), "comments", fileName,reportParameters));
            protocolForm.getActionHelper().setReviewCommentsReport(false);
        }
        fileName.append("report.pdf");
        return printableArtifactList;
    }
    
    /*
     * This is to view attachment if attachment is selected in print panel.
     */
    private ActionForward printAttachmentProtocol(ActionMapping mapping, HttpServletResponse response, ProtocolAttachmentProtocol attachment,ProtocolForm form) throws Exception {
        if (attachment == null) {
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
        final AttachmentFile file = attachment.getFile();
           byte[] attachmentFile =null;
           String attachmentFileType=file.getType().replace("\"", "");
           attachmentFileType=attachmentFileType.replace("\\", "");           
           if(attachmentFileType.equalsIgnoreCase(WatermarkConstants.ATTACHMENT_TYPE_PDF)){
               attachmentFile=getProtocolAttachmentFile(form,attachment);
               if(attachmentFile!=null){          
                   this.streamToResponse(attachmentFile, getValidHeaderString(file.getName()), getValidHeaderString(file.getType()), response);    }
               else{
                   this.streamToResponse(file.getData(), getValidHeaderString(file.getName()), getValidHeaderString(file.getType()), response);    }
               return RESPONSE_ALREADY_HANDLED;
           }
        this.streamToResponse(file.getData(), getValidHeaderString(file.getName()), getValidHeaderString(file.getType()), response);
//        byte[] watermarkedFile = KraServiceLocator.getService(WatermarkService.class).applyWatermark( file.getData(),getProtocolWatermarkBeanObject("199"));
//        this.streamToResponse(watermarkedFile, getValidHeaderString(file.getName()), getValidHeaderString(file.getType()), response);

        return RESPONSE_ALREADY_HANDLED;
    }

    /*
     * This is to view Personnel attachment if attachment is selected in print & summary panel.
     */
    private ActionForward printPersonnelAttachmentProtocol(ActionMapping mapping, HttpServletResponse response, ProtocolAttachmentBase attachment,ProtocolForm form) throws Exception {

        if (attachment == null) {
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
        final AttachmentFile file = attachment.getFile();
        this.streamToResponse(file.getData(), getValidHeaderString(file.getName()), getValidHeaderString(file.getType()), response);
        return RESPONSE_ALREADY_HANDLED;
    }
    /**
     * 
     * This method for set the attachment with the watermark which selected  by the client .
     * @param protocolForm form
     * @param protocolAttachmentBase attachment
     * @return attachment file
     */
    private byte[] getProtocolAttachmentFile(ProtocolForm form,ProtocolAttachmentProtocol attachment) {
        
        byte[] attachmentFile =null;
        final AttachmentFile file = attachment.getFile();
        Printable printableArtifacts= getProtocolPrintingService().getProtocolPrintArtifacts(form.getProtocolDocument().getProtocol());
        Protocol protocolCurrent = form.getDocument().getProtocol();
        int currentProtoSeqNumber= protocolCurrent.getSequenceNumber();
        try {
            if(printableArtifacts.isWatermarkEnabled()){
                int currentAttachmentSequence=attachment.getSequenceNumber();
                String docStatusCode=attachment.getDocumentStatusCode();
                // TODO perhaps the check for equality of protocol and attachment sequence numbers, below, is now redundant
                if(((getProtocolAttachmentService().isAttachmentActive(attachment))&&(currentProtoSeqNumber == currentAttachmentSequence))||(docStatusCode.equals("1"))){
                            attachmentFile = getWatermarkService().applyWatermark(file.getData(),printableArtifacts.getWatermarkable().getWatermark());
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
        ProtocolForm protocolForm = (ProtocolForm) form;
        Date startDate = protocolForm.getActionHelper().getFilteredHistoryStartDate();
        Date endDate = protocolForm.getActionHelper().getFilteredHistoryEndDate();
        
        if (applyRules(new ProtocolHistoryFilterDatesEvent(protocolForm.getDocument(), startDate, endDate))) {
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
        ProtocolForm protocolForm = (ProtocolForm) form;
        protocolForm.getActionHelper().setFilteredHistoryStartDate(null);
        protocolForm.getActionHelper().setFilteredHistoryEndDate(null);
        protocolForm.getActionHelper().initFilterDatesView();
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * Load a Protocol summary into the summary sub-panel. The protocol summary to load corresponds to the currently selected
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
        ProtocolForm protocolForm = (ProtocolForm) form;
        org.kuali.kra.irb.actions.ProtocolAction action = protocolForm.getActionHelper().getSelectedProtocolAction();
        if (action != null) {
            protocolForm.getActionHelper().setCurrentSequenceNumber(action.getSequenceNumber());
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * View an attachment via the Summary sub-panel.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward viewAttachmentProtocol(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolSummary protocolSummary = protocolForm.getActionHelper().getProtocolSummary();
        if (((String)request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE)).contains(".prev.")) {
            protocolSummary = protocolForm.getActionHelper().getPrevProtocolSummary();
        }
        int selectedIndex = getSelectedLine(request);
        AttachmentSummary attachmentSummary = protocolSummary.getAttachments().get(selectedIndex);
        
        
        if (attachmentSummary.getAttachmentType().startsWith("Protocol: ")) {
            ProtocolAttachmentProtocol attachment = getProtocolAttachmentService().getAttachment(ProtocolAttachmentProtocol.class, attachmentSummary.getAttachmentId());
            return printAttachmentProtocol(mapping, response, attachment, protocolForm);
        } 
        else {
            ProtocolAttachmentBase personnelAttachment = getProtocolAttachmentService().getAttachment(ProtocolAttachmentPersonnel.class, attachmentSummary.getAttachmentId());
            return printPersonnelAttachmentProtocol(mapping, response, personnelAttachment, protocolForm);
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

        ProtocolForm protocolForm = (ProtocolForm) form;
        ActionHelper actionHelper = protocolForm.getActionHelper();
        actionHelper.setCurrentSequenceNumber(actionHelper.getCurrentSequenceNumber() - 1);
        ((ProtocolForm) form).getActionHelper().initSummaryDetails();

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

        ProtocolForm protocolForm = (ProtocolForm) form;
        ActionHelper actionHelper = protocolForm.getActionHelper();
        actionHelper.setCurrentSequenceNumber(actionHelper.getCurrentSequenceNumber() + 1);
        ((ProtocolForm) form).getActionHelper().initSummaryDetails();
        
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

        ProtocolForm protocolForm = (ProtocolForm) form;
        ActionHelper actionHelper = protocolForm.getActionHelper();
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

        ProtocolForm protocolForm = (ProtocolForm) form;
        ActionHelper actionHelper = protocolForm.getActionHelper();
        actionHelper.setCurrentSubmissionNumber(actionHelper.getNextSubmissionNumber());
        actionHelper.setAmendmentDetails();
        protocolForm.getActionHelper().initSubmissionDetails();
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * 
     * This method is to render protocol action page when 'view' is clicked in meeting page, Protocol submitted panel.
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
        ProtocolSubmission protocolSubmission = (ProtocolSubmission) getBusinessObjectService().findByPrimaryKey(ProtocolSubmission.class, fieldValues);
        protocolSubmission.getProtocol().setProtocolSubmission(protocolSubmission);
        
        ProtocolForm protocolForm = (ProtocolForm) form;
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
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        Protocol protocol = protocolForm.getProtocolDocument().getProtocol();
       
        if (!hasDocumentStateChanged(protocolForm)) {
            ProtocolTask task = new ProtocolTask(TaskName.ASSIGN_TO_AGENDA, protocolForm.getProtocolDocument().getProtocol());
            if (isAuthorized(task)) {
                ProtocolAssignToAgendaBean actionBean = protocolForm.getActionHelper().getAssignToAgendaBean();
                if (applyRules(new ProtocolAssignToAgendaEvent(protocolForm.getProtocolDocument(), actionBean))) {               
                    getProtocolAssignToAgendaService().assignToAgenda(protocolForm.getProtocolDocument().getProtocol(), actionBean);
                    saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
                    recordProtocolActionSuccess("Assign to Agenda");
                    
                    org.kuali.kra.irb.actions.ProtocolAction lastAction = protocolForm.getProtocolDocument().getProtocol().getLastProtocolAction();
                    ProtocolActionType lastActionType = lastAction.getProtocolActionType();
                    String description = lastActionType.getDescription();
                    IRBNotificationRenderer renderer = new IRBNotificationRenderer(protocol);
                    IRBNotificationContext context = new IRBNotificationContext(protocol, ProtocolActionType.ASSIGN_TO_AGENDA, description, renderer);
                    
                    if (protocolForm.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
                        protocolForm.getNotificationHelper().initializeDefaultValues(context);
                        forward = mapping.findForward("protocolNotificationEditor");
                    } else {
                        getNotificationService().sendNotification(context);
                    }
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
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REVIEW_NOT_REQUIRED, protocolForm.getProtocolDocument().getProtocol());
        if (isAuthorized(task)) {
            ProtocolReviewNotRequiredBean actionBean = protocolForm.getActionHelper().getProtocolReviewNotRequiredBean();
            if (applyRules(new ProtocolReviewNotRequiredEvent(protocolForm.getProtocolDocument(), actionBean))) {
                KraServiceLocator.getService(ProtocolReviewNotRequiredService.class).reviewNotRequired(protocolForm.getProtocolDocument(), actionBean);
            
                recordProtocolActionSuccess("Review Not Required");
            }
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Assign a protocol to a committee/schedule.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward assignCommitteeSchedule(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        final String callerString = "assignCommitteeSchedule";
        ProtocolTask task = new ProtocolTask(TaskName.ASSIGN_TO_COMMITTEE_SCHEDULE, protocolForm.getProtocolDocument().getProtocol());
        
        if (!hasDocumentStateChanged(protocolForm)) {
            if (isAuthorized(task)) {
                ProtocolAssignCmtSchedBean actionBean = protocolForm.getActionHelper().getAssignCmtSchedBean();
                if (applyRules(new ProtocolAssignCmtSchedEvent(protocolForm.getProtocolDocument(), actionBean))) {
                    
                    if( protocolForm.getProtocolDocument().getProtocol().getProtocolSubmission() != null) {
                        boolean performAssignment = false;
                        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
                        Object buttonClicked = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
    
                    
                        if (isCommitteeMeetingAssignedMaxProtocols(actionBean.getNewCommitteeId(), actionBean.getNewScheduleId())) {
                            //There are existing reviews and we are changing schedules
                            //need to verify with the user that they want to remove the existing reviews before proceeding.
                            if (question==null || !CONFIRM_ASSIGN_CMT_SCHED_KEY.equals(question)) {
                                return performQuestionWithoutInput(mapping, form, request, response, CONFIRM_ASSIGN_CMT_SCHED_KEY,
                                        getKualiConfigurationService().getPropertyValueAsString(KeyConstants.QUESTION_PROTOCOL_CONFIRM_SUBMIT_FOR_REVIEW), KRADConstants.CONFIRMATION_QUESTION, callerString, "" );
                            } else if (ConfirmationQuestion.YES.equals(buttonClicked)) {
                                performAssignment = true;
                            } else {
                                //nothing to do, answered no.
                            }
                        } else {
                            performAssignment = true;
                        }
        
                        if (performAssignment) {
                            getProtocolAssignCmtSchedService().assignToCommitteeAndSchedule(protocolForm.getProtocolDocument().getProtocol(), actionBean);
                            recordProtocolActionSuccess("Assign to Committee and Schedule");
                        }
                        ((ProtocolForm)form).getActionHelper().prepareView();
                    }
                }
            }
        } else {
            GlobalVariables.getMessageMap().clearErrorMessages();
            GlobalVariables.getMessageMap().putError("documentstatechanged", KeyConstants.ERROR_PROTOCOL_DOCUMENT_STATE_CHANGED,  new String[] {}); 
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
     /**
     * 
     * Builds the confirmation question to verify if the user wants to assign the protocol to the committee.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    private StrutsConfirmation buildAssignToAgendaConfirmationQuestion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_ASSIGN_TO_AGENDA_KEY,
                KeyConstants.QUESTION_PROTOCOL_CONFIRM_SUBMIT_FOR_REVIEW);
    }
   
    public ActionForward confirmAssignToAgenda(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);

        if (CONFIRM_ASSIGN_TO_AGENDA_KEY.equals(question)) {
            ProtocolForm protocolForm = (ProtocolForm) form;
            ProtocolAssignToAgendaBean actionBean = protocolForm.getActionHelper().getAssignToAgendaBean();
            getProtocolAssignToAgendaService().assignToAgenda(protocolForm.getProtocolDocument().getProtocol(), actionBean);
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Assign a protocol to some reviewers.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward assignReviewers(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolTask task = new ProtocolTask(TaskName.ASSIGN_REVIEWERS, protocolForm.getProtocolDocument().getProtocol());
        String callerString = String.format("assignReviewers");
        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        
        if (!hasDocumentStateChanged(protocolForm)) {
            if (isAuthorized(task)) {
                ProtocolAssignReviewersBean actionBean = protocolForm.getActionHelper().getProtocolAssignReviewersBean();
                if (applyRules(new ProtocolAssignReviewersEvent(protocolForm.getProtocolDocument(), actionBean))) {
                    boolean processRequest = true;
                    
                    if (GlobalVariables.getMessageMap().hasWarnings()) {
                        if (question == null) {
                            // ask question if not already asked
                            forward = performQuestionWithoutInput(mapping, form, request, response, 
                                                                    CONIFRM_REMOVE_REVIEWER_KEY, 
                                                                    getKualiConfigurationService().getPropertyValueAsString(KeyConstants.MESSAGE_REMOVE_REVIEWERS_WITH_COMMENTS), 
                                                                    KRADConstants.CONFIRMATION_QUESTION, 
                                                                    callerString, 
                                                                    "");
                            processRequest = false;
                        }
                        else {
                            Object buttonClicked = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
                            if ((KRADConstants.DOCUMENT_DISAPPROVE_QUESTION.equals(question)) && ConfirmationQuestion.NO.equals(buttonClicked)) {
                                // if no button clicked just reload the doc
                                processRequest = false;
                                if (LOG.isDebugEnabled()) {
                                    LOG.debug("User declined to confirm the request, not processing.");
                                }
                            }
                        }
                    
                    }
                    
                    if (processRequest) {
                        ProtocolSubmission submission = protocolForm.getProtocolDocument().getProtocol().getProtocolSubmission();
                        List<ProtocolReviewerBean> beans = actionBean.getReviewers();
                        getProtocolAssignReviewersService().assignReviewers(submission, beans);
                        //clear the warnings before rendering the page.
                        GlobalVariables.getMessageMap().getWarningMessages().clear();
                        
                        recordProtocolActionSuccess("Assign Reviewers");
                        AssignReviewerNotificationRenderer renderer = new AssignReviewerNotificationRenderer(protocolForm
                                .getProtocolDocument().getProtocol(), "added");
                        List<ProtocolNotificationRequestBean> addReviewerNotificationBeans = getNotificationRequestBeans(beans,
                                ProtocolReviewerBean.CREATE);
                        List<ProtocolNotificationRequestBean> removeReviewerNotificationBeans = getNotificationRequestBeans(beans,
                                ProtocolReviewerBean.REMOVE);
                        if (!CollectionUtils.isEmpty(addReviewerNotificationBeans)) {
                            ProtocolNotificationRequestBean notificationBean = addReviewerNotificationBeans.get(0);
                            IRBNotificationContext context = new IRBNotificationContext(notificationBean.getProtocol(),
                                notificationBean.getProtocolOnlineReview(), notificationBean.getActionType(),
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
                                renderer = new AssignReviewerNotificationRenderer(protocolForm.getProtocolDocument().getProtocol(),
                                    "removed");
                                ProtocolNotificationRequestBean notificationBean = removeReviewerNotificationBeans.get(0);
                                IRBNotificationContext context = new IRBNotificationContext(notificationBean.getProtocol(),
                                    notificationBean.getProtocolOnlineReview(), notificationBean.getActionType(),
                                    notificationBean.getDescription(), renderer);
                                if (protocolForm.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
                                    forward = checkToSendNotification(mapping, mapping.findForward(PROTOCOL_ACTIONS_TAB),
                                            protocolForm, renderer, removeReviewerNotificationBeans);
                                }
                            }
                        }
                    }
                }
            }
        }
        else {
            GlobalVariables.getMessageMap().clearErrorMessages();
            GlobalVariables.getMessageMap().putError("documentstatechanged", KeyConstants.ERROR_PROTOCOL_DOCUMENT_STATE_CHANGED,
                    new String[] {});
        }
            
        return forward;
    }
    
    private List<ProtocolNotificationRequestBean> getNotificationRequestBeans(List<ProtocolReviewerBean> beans, String actionFlag) {
        List<ProtocolNotificationRequestBean> notificationRequestBeans = new ArrayList<ProtocolNotificationRequestBean>();
        for (ProtocolReviewerBean bean : beans) {
            if (StringUtils.equals(actionFlag, bean.getActionFlag())) {
                notificationRequestBeans.add(bean.getNotificationRequestBean());
            }
        }
        return notificationRequestBeans;
    }
    
    /**
     * Grant an exemption to a protocol.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward grantExemption(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument document = protocolForm.getProtocolDocument();
        Protocol protocol = document.getProtocol();
        ProtocolGrantExemptionBean actionBean = protocolForm.getActionHelper().getProtocolGrantExemptionBean();
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        if (hasPermission(TaskName.GRANT_EXEMPTION, protocol)) {
            if (applyRules(new ProtocolGrantExemptionEvent(document, actionBean))) {
                getProtocolGrantExemptionService().grantExemption(protocol, actionBean);
                saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());                
                recordProtocolActionSuccess("Grant Exemption");
                forward = confirmFollowupAction(mapping, form, request, response, Constants.MAPPING_BASIC);
           }
        }
        if (request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME) != null) {
            forward = confirmFollowupAction(mapping, form, request, response, Constants.MAPPING_BASIC);
        }
        
        return forward;
    }
    
    /**
     * Perform Full Approve Action - maps to IRBReview RouteNode.
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
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument document = protocolForm.getProtocolDocument();
        ProtocolApproveBean actionBean = protocolForm.getActionHelper().getProtocolFullApprovalBean();
        
        if (hasPermission(TaskName.APPROVE_PROTOCOL, document.getProtocol())) {
            if (applyRules(new ProtocolApproveEvent(document, actionBean))) {
                forward = super.approve(mapping, protocolForm, request, response);
                getProtocolApproveService().grantFullApproval(document.getProtocol(), actionBean);
                saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
//                if (document.getProtocol().isAmendment() || document.getProtocol().isRenewal()) {
//                    forward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
//                }
                forward = routeProtocolToHoldingPage(mapping, protocolForm);                                    
                
                recordProtocolActionSuccess("Full Approval");
            }
        }
        
        return forward;
    }
    
    /**
     * Perform Expedited Approve Action.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward grantExpeditedApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {
        
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument document = protocolForm.getProtocolDocument();
        ProtocolApproveBean actionBean = protocolForm.getActionHelper().getProtocolExpeditedApprovalBean();
        
        if (hasPermission(TaskName.EXPEDITE_APPROVAL, document.getProtocol())) {
            if (applyRules(new ProtocolApproveEvent(document, actionBean))) {
                getProtocolApproveService().grantExpeditedApproval(protocolForm.getProtocolDocument().getProtocol(), actionBean);
                saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
                recordProtocolActionSuccess("Expedited Approval");
                forward = confirmFollowupAction(mapping, form, request, response, KRADConstants.MAPPING_PORTAL);
            }
        }
        // Question frame work will execute method twice.  so, need to be aware that service will not be executed twice.
        if (request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME) != null) {
            confirmFollowupAction(mapping, form, request, response, KRADConstants.MAPPING_PORTAL);
            //forward = mapping.findForward(KRADConstants.MAPPING_PORTAL);                                    
            forward = routeProtocolToHoldingPage(mapping, protocolForm);                                    
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
    public ActionForward grantResponseApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {
        
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument document = protocolForm.getProtocolDocument();
        ProtocolApproveBean actionBean = protocolForm.getActionHelper().getProtocolResponseApprovalBean();
        
        if (hasPermission(TaskName.RESPONSE_APPROVAL, document.getProtocol())) {
            if (applyRules(new ProtocolApproveEvent(document, actionBean))) {
                getProtocolApproveService().grantResponseApproval(document.getProtocol(), actionBean);
                saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
               // forward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
                forward = routeProtocolToHoldingPage(mapping, protocolForm);                                    
                
                recordProtocolActionSuccess("Response Approval");
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
     *   Reopen Enrollment
     *   Suspension
     *   Termination
     * 
     * Uses the enumeration <code>ProtocolRequestAction</code> to encapsulate the unique properties on each action.
     * @param mapping The mapping associated with this action.
     * @param form The Protocol form.
     * @param request The HTTP request
     * @param response The HTTP response
     * @return the forward to the current page
     * @throws Exception
     */
    public ActionForward requestAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument document = protocolForm.getProtocolDocument();
        Protocol protocol = document.getProtocol();
        String taskName = getTaskName(request);
        
        if (StringUtils.isNotBlank(taskName) && isAuthorized(new ProtocolTask(taskName, protocol))) {
            ProtocolRequestAction requestAction = ProtocolRequestAction.valueOfTaskName(taskName);
            ProtocolRequestBean requestBean = getProtocolRequestBean(form, request);
            
            if (requestBean != null) {
                boolean valid = applyRules(new ProtocolRequestEvent<ProtocolRequestRule>(document, requestAction.getErrorPath(), requestBean));
                requestBean.setAnswerHeaders(getAnswerHeaders(form, requestAction.getActionTypeCode()));
                valid &= isMandatoryQuestionnaireComplete(requestBean, "actionHelper." + requestAction.getBeanName() + ".datavalidation");
                if (valid) {
                    getProtocolRequestService().submitRequest(protocolForm.getProtocolDocument().getProtocol(), requestBean);            
                    recordProtocolActionSuccess(requestAction.getActionName());
                    return sendRequestNotification(mapping, form, protocol, requestBean);
                }
            }
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    private ActionForward sendRequestNotification(ActionMapping mapping, ActionForm form, Protocol protocol, ProtocolRequestBean requestBean) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolActionType protocolActionType = getBusinessObjectService().findBySinglePrimaryKey(ProtocolActionType.class, requestBean.getProtocolActionTypeCode());
        String protocolActionTypeCode = protocolActionType.getProtocolActionTypeCode();
        String description = protocolActionType.getDescription();
        
        return checkToSendNotification(mapping, mapping.findForward(PROTOCOL_ACTIONS_TAB), protocolForm, new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(), protocolActionTypeCode, description));
    }

    private ProtocolRequestBean getProtocolRequestBean(ActionForm form, HttpServletRequest request) {
        ProtocolRequestBean protocolRequestBean = null;
        
        ProtocolActionBean protocolActionBean = getActionBean(form, request);
        if (protocolActionBean != null && protocolActionBean instanceof ProtocolRequestBean) {
            protocolRequestBean = (ProtocolRequestBean) protocolActionBean;
        }
        
        return protocolRequestBean;
    }
    
    /**
     * Closes this Protocol.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward closeProtocol(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument document = protocolForm.getProtocolDocument();
        Protocol protocol = document.getProtocol();
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolCloseBean();
        
        if (hasGenericPermission(GenericProtocolAuthorizer.CLOSE_PROTOCOL, protocol)) {
            if (applyRules(new ProtocolGenericActionEvent(document, actionBean))) {
                getProtocolGenericActionService().close(protocol, actionBean);
                saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
                
                recordProtocolActionSuccess("Close");
            }
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * Closes enrollment for this Protocol.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward closeEnrollment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument document = protocolForm.getProtocolDocument();
        Protocol protocol = document.getProtocol();
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolCloseEnrollmentBean();
        
        if (hasGenericPermission(GenericProtocolAuthorizer.CLOSE_ENROLLMENT_PROTOCOL, protocol)) {
            if (applyRules(new ProtocolGenericActionEvent(document, actionBean))) {
                getProtocolGenericActionService().closeEnrollment(protocol, actionBean);
                saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
            
                recordProtocolActionSuccess("Close Enrollment");
            }
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Defers this Protocol to a later meeting.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward defer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument document = protocolForm.getProtocolDocument();
        Protocol protocol = document.getProtocol();
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolDeferBean();
        
        if (!hasDocumentStateChanged(protocolForm)) {
            if (hasPermission(TaskName.DEFER_PROTOCOL, protocol)) {
                if (applyRules(new ProtocolGenericActionEvent(document, actionBean))) {
                    ProtocolDocument newDocument = getProtocolGenericActionService().defer(protocol, actionBean);
                    saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
                    
                    protocolForm.setDocId(newDocument.getDocumentNumber());
                    loadDocument(protocolForm);
                    protocolForm.getProtocolHelper().prepareView();
                    
                    recordProtocolActionSuccess("Defer");
                    
                    forward = checkToSendNotification(mapping, mapping.findForward(PROTOCOL_TAB), protocolForm, new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(),ProtocolActionType.DEFERRED, "Deferred"));
//                    forward = mapping.findForward(PROTOCOL_TAB);
                }
            }
        } else {
            GlobalVariables.getMessageMap().clearErrorMessages();
            GlobalVariables.getMessageMap().putError("documentstatechanged", KeyConstants.ERROR_PROTOCOL_DOCUMENT_STATE_CHANGED,  new String[] {}); 
        }
        
        return forward;
    }
    
    /**
     * Disapproves this Protocol.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward disapproveProtocol(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument document = protocolForm.getProtocolDocument();
        Protocol protocol = document.getProtocol();
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolDisapproveBean();
        
        if (hasPermission(TaskName.DISAPPROVE_PROTOCOL, protocol)) {
            if (applyRules(new ProtocolGenericActionEvent(document, actionBean))) {
                getProtocolGenericActionService().disapprove(protocol, actionBean);
                saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
                
                recordProtocolActionSuccess("Disapprove");
            }
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Expires this Protocol.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward expire(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument document = protocolForm.getProtocolDocument();
        Protocol protocol = document.getProtocol();
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolExpireBean();
        
        if (hasGenericPermission(GenericProtocolAuthorizer.EXPIRE_PROTOCOL, protocol)) {
            if (applyRules(new ProtocolGenericActionEvent(document, actionBean))) {
                getProtocolGenericActionService().expire(protocol, actionBean);
                saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
                
                recordProtocolActionSuccess("Expire");
            }
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Sends IRB Acknowledgement for this Protocol.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward irbAcknowledgement(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument document = protocolForm.getProtocolDocument();
        Protocol protocol = document.getProtocol();
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolIrbAcknowledgementBean();
        
        if (hasPermission(TaskName.IRB_ACKNOWLEDGEMENT, protocol)) {
            if (applyRules(new ProtocolGenericActionEvent(document, actionBean))) {
                getProtocolGenericActionService().irbAcknowledgement(protocol, actionBean);
                saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
                    
                recordProtocolActionSuccess("IRB Acknowledgement");
                return checkToSendNotification(mapping, mapping.findForward(PROTOCOL_ACTIONS_TAB), protocolForm, new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(),ProtocolActionType.IRB_ACKNOWLEDGEMENT, "IRB Acknowledgement"));
            }
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * Permits data analysis only on this Protocol.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward permitDataAnalysis(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument document = protocolForm.getProtocolDocument();
        Protocol protocol = document.getProtocol();
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolPermitDataAnalysisBean();
        
        if (hasGenericPermission(GenericProtocolAuthorizer.PERMIT_DATA_ANALYSIS, protocol)) {
            if (applyRules(new ProtocolGenericActionEvent(document, actionBean))) {
                getProtocolGenericActionService().permitDataAnalysis(protocol, actionBean);
                saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
                
                recordProtocolActionSuccess("Permit Data Analysis Only");
            }
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * Reopens enrollment for this Protocol.
     * This method...
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward reopenEnrollment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument document = protocolForm.getProtocolDocument();
        Protocol protocol = document.getProtocol();
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolReopenEnrollmentBean();
        
        if (hasGenericPermission(GenericProtocolAuthorizer.REOPEN_PROTOCOL, protocol)) {
            if (applyRules(new ProtocolGenericActionEvent(document, actionBean))) {
                getProtocolGenericActionService().reopenEnrollment(protocol, actionBean);
                saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
                
                recordProtocolActionSuccess("Re-open Enrollment");
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
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument document = protocolForm.getProtocolDocument();
        Protocol protocol = document.getProtocol();
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolSMRBean();
        
        if (hasPermission(TaskName.RETURN_FOR_SMR, protocol)) {
            if (applyRules(new ProtocolGenericActionEvent(document, actionBean))) {
                ProtocolDocument newDocument = getProtocolGenericActionService().returnForSMR(protocol, actionBean);
                saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
                
                protocolForm.setDocId(newDocument.getDocumentNumber());
                loadDocument(protocolForm);
                protocolForm.getProtocolHelper().prepareView();
                
                recordProtocolActionSuccess("Return for Specific Minor Revisions");
//              org.kuali.kra.irb.actions.ProtocolAction lastAction = protocolForm.getProtocolDocument().getProtocol().getLastProtocolAction();
//              ProtocolActionType lastActionType = lastAction.getProtocolActionType();
//              String description = lastActionType.getDescription();
//              IRBNotificationRenderer renderer = new IRBNotificationRenderer(protocolForm.getProtocolDocument().getProtocol());
//              IRBNotificationContext context = new IRBNotificationContext(protocolForm.getProtocolDocument().getProtocol(), ProtocolActionType.SPECIFIC_MINOR_REVISIONS_REQUIRED, "Specific Minor Revisions Required", renderer);
//              
//              if (protocolForm.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
//                  protocolForm.getNotificationHelper().initializeDefaultValues(context);
//                  return mapping.findForward("protocolNotificationEditor");
//              } else {
//                  getNotificationService().sendNotification(context);
//              }
                forward = checkToSendNotification(mapping, mapping.findForward(PROTOCOL_TAB), protocolForm, new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(),ProtocolActionType.SPECIFIC_MINOR_REVISIONS_REQUIRED, "Specific Minor Revisions Required"));
//                forward = mapping.findForward(PROTOCOL_TAB);
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
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument document = protocolForm.getProtocolDocument();
        Protocol protocol = document.getProtocol();
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolSRRBean();
        
        if (hasPermission(TaskName.RETURN_FOR_SRR, protocol)) {
            if (applyRules(new ProtocolGenericActionEvent(document, actionBean))) {
                ProtocolDocument newDocument = getProtocolGenericActionService().returnForSRR(protocol, actionBean);
                saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
                
                protocolForm.setDocId(newDocument.getDocumentNumber());
                loadDocument(protocolForm);
                protocolForm.getProtocolHelper().prepareView();
                
                recordProtocolActionSuccess("Return for Substantive Revisions Required");
                
                forward = checkToSendNotification(mapping, mapping.findForward(PROTOCOL_TAB), protocolForm, new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(),ProtocolActionType.SUBSTANTIVE_REVISIONS_REQUIRED, "Substantive Revisions Required"));
            }
        }
        
        return forward;
    }
    
    /**
     * Suspends this Protocol.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward suspend(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument document = protocolForm.getProtocolDocument();
        Protocol protocol = document.getProtocol();
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolSuspendBean();
        
        if (hasGenericPermission(GenericProtocolAuthorizer.SUSPEND_PROTOCOL, protocol)) {
            if (applyRules(new ProtocolGenericActionEvent(document, actionBean))) {
                getProtocolGenericActionService().suspend(protocol, actionBean);
                saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
                
                recordProtocolActionSuccess("Suspend");
            }
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Suspends this Protocol by DSMB.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward suspendByDsmb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument document = protocolForm.getProtocolDocument();
        Protocol protocol = document.getProtocol();
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolSuspendByDsmbBean();
        
        if (hasGenericPermission(GenericProtocolAuthorizer.SUSPEND_PROTOCOL_BY_DSMB, protocol)) {
            if (applyRules(new ProtocolGenericActionEvent(document, actionBean))) {
                getProtocolGenericActionService().suspendByDsmb(protocol, actionBean);
                saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
                
                recordProtocolActionSuccess("Suspend by DSMB");
            }
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Terminates this Protocol.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward terminate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument document = protocolForm.getProtocolDocument();
        Protocol protocol = document.getProtocol();
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolTerminateBean();

        if (hasGenericPermission(GenericProtocolAuthorizer.TERMINATE_PROTOCOL, protocolForm.getProtocolDocument().getProtocol())) {
            if (applyRules(new ProtocolGenericActionEvent(document, actionBean))) {
                getProtocolGenericActionService().terminate(protocol, actionBean);
                saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
                
                recordProtocolActionSuccess("Terminate");
            }
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward manageComments(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        if (!hasDocumentStateChanged(protocolForm)) {
            if (hasPermission(TaskName.PROTOCOL_MANAGE_REVIEW_COMMENTS, protocolForm.getProtocolDocument().getProtocol())) {
                ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolManageReviewCommentsBean();
                saveReviewComments(protocolForm, actionBean.getReviewCommentsBean());
                
                recordProtocolActionSuccess("Manage Review Comments");
            }
        } else {
            GlobalVariables.getMessageMap().clearErrorMessages();
            GlobalVariables.getMessageMap().putError("documentstatechanged", KeyConstants.ERROR_PROTOCOL_DOCUMENT_STATE_CHANGED,  new String[] {}); 
        }        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Open ProtocolDocument in Read/Write mode for Admin Correction
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

        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument protocolDocument = protocolForm.getProtocolDocument();
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_ADMIN_CORRECTION, protocolDocument.getProtocol());
        if (!hasDocumentStateChanged(protocolForm)) {
            if (isAuthorized(task)) {
                if (applyRules(new ProtocolAdminCorrectionEvent(protocolDocument, protocolForm.getActionHelper()
                            .getProtocolAdminCorrectionBean()))) {
                    protocolDocument.getProtocol().setCorrectionMode(true); 
                    protocolForm.getProtocolHelper().prepareView();
    
                    AdminCorrectionBean adminCorrectionBean = protocolForm.getActionHelper().getProtocolAdminCorrectionBean();
                    protocolDocument.updateProtocolStatus(ProtocolActionType.ADMINISTRATIVE_CORRECTION, adminCorrectionBean.getComments());
    
                    AdminCorrectionService adminCorrectionService = KraServiceLocator.getService(AdminCorrectionService.class);
                    adminCorrectionService.sendCorrectionNotification(protocolDocument.getProtocol(), adminCorrectionBean);
    
                    recordProtocolActionSuccess("Make Administrative Correction");
                    
                    return mapping.findForward(PROTOCOL_TAB);
                }
            }
        } else {
            GlobalVariables.getMessageMap().clearErrorMessages();
            GlobalVariables.getMessageMap().putError("documentstatechanged", KeyConstants.ERROR_PROTOCOL_DOCUMENT_STATE_CHANGED,  new String[] {}); 
        } 
        
        return forward;  
    }

    public ActionForward undoLastAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ProtocolForm protocolForm = (ProtocolForm) form;
        
        if (!hasDocumentStateChanged(protocolForm)) {
            ProtocolDocument protocolDocument = protocolForm.getProtocolDocument();
            UndoLastActionBean undoLastActionBean = protocolForm.getActionHelper().getUndoLastActionBean();
            String lastActionType = undoLastActionBean.getLastPerformedAction().getProtocolActionTypeCode();
            
            UndoLastActionService undoLastActionService = KraServiceLocator.getService(UndoLastActionService.class);
            ProtocolDocument updatedDocument = undoLastActionService.undoLastAction(protocolDocument, undoLastActionBean);
                       
    
            recordProtocolActionSuccess("Undo Last Action");
    
            if (!updatedDocument.getDocumentNumber().equals(protocolForm.getDocId())) {
                protocolForm.setDocId(updatedDocument.getDocumentNumber());
                loadDocument(protocolForm);
                protocolForm.getProtocolHelper().prepareView();
                return mapping.findForward(PROTOCOL_TAB);
            }
            if (ProtocolActionType.SPECIFIC_MINOR_REVISIONS_REQUIRED.equals(lastActionType)
                    || ProtocolActionType.SUBSTANTIVE_REVISIONS_REQUIRED.equals(lastActionType)) {
                // undo SMR/SRR may need to create & route onln revw document,
                // this will need some time.   also, some change in db may not be viewable 
                // before document is routed.  so, add this holding page for undo SMR/SRR.
                //            protocolForm.setActionHelper(new ActionHelper(protocolForm));
                //
                //            protocolForm.getActionHelper().prepareView();
                return routeProtocolToHoldingPage(mapping, protocolForm);
            }
        } else {
            GlobalVariables.getMessageMap().clearErrorMessages();
            GlobalVariables.getMessageMap().putError("documentstatechanged", KeyConstants.ERROR_PROTOCOL_DOCUMENT_STATE_CHANGED,  new String[] {}); 
        }
        return mapping.findForward(Constants.MAPPING_BASIC);

    }
    
    public ActionForward submitCommitteeDecision(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        if (!hasDocumentStateChanged(protocolForm)) {
            if (applyRules(new CommitteeDecisionEvent(protocolForm.getProtocolDocument(), protocolForm.getActionHelper().getCommitteeDecision()))){
                CommitteeDecision actionBean = protocolForm.getActionHelper().getCommitteeDecision();
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
        ProtocolForm protocolForm = (ProtocolForm) form;
        CommitteeDecision decision = protocolForm.getActionHelper().getCommitteeDecision();
        if (applyRules(new CommitteeDecisionAbstainerEvent(protocolForm.getProtocolDocument(), decision))){
            decision.getAbstainers().add(decision.getNewAbstainer());
            decision.setNewAbstainer(new CommitteePerson());
            decision.setAbstainCount(decision.getAbstainCount() + 1);
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward deleteAbstainer(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        CommitteeDecision decision = protocolForm.getActionHelper().getCommitteeDecision();
        CommitteePerson person = decision.getAbstainers().get(getLineToDelete(request));
        if (person != null) {
            decision.getAbstainersToDelete().add(person);
            decision.getAbstainers().remove(getLineToDelete(request));
            decision.setAbstainCount(decision.getAbstainCount() - 1);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward addRecused(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        CommitteeDecision decision = protocolForm.getActionHelper().getCommitteeDecision();
        if (applyRules(new CommitteeDecisionRecuserEvent(protocolForm.getProtocolDocument(), decision))) {
            decision.getRecused().add(decision.getNewRecused());
            decision.setNewRecused(new CommitteePerson());
            decision.setRecusedCount(decision.getRecusedCount() + 1);
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward modifySubmsionAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        
        if (!hasDocumentStateChanged(protocolForm)) {
            ProtocolModifySubmissionBean bean = protocolForm.getActionHelper().getProtocolModifySubmissionBean();
            if (applyRules(new ProtocolModifySubmissionEvent(protocolForm.getProtocolDocument(), bean))) {
                KraServiceLocator.getService(ProtocolModifySubmissionService.class).modifySubmisison(protocolForm.getProtocolDocument(), bean);
            
                recordProtocolActionSuccess("Modify Submission Request");
            }
        } else {
            GlobalVariables.getMessageMap().clearErrorMessages();
            GlobalVariables.getMessageMap().putError("documentstatechanged", KeyConstants.ERROR_PROTOCOL_DOCUMENT_STATE_CHANGED,  new String[] {}); 
        }
        return mapping.findForward(Constants.MAPPING_BASIC);        
    }
    
    public ActionForward deleteRecused(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        CommitteeDecision decision = protocolForm.getActionHelper().getCommitteeDecision();
        CommitteePerson person = decision.getRecused().get(getLineToDelete(request));
        if (person != null) {
            decision.getRecusedToDelete().add(person);
            decision.getRecused().remove(getLineToDelete(request));
            decision.setRecusedCount(decision.getRecusedCount() - 1);
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    private Printable getPrintableArtifacts(Protocol protocol, String reportType, StringBuffer fileName,Map reportParameters) {
        ProtocolPrintType printType = ProtocolPrintType.valueOf(PRINTTAG_MAP.get(reportType));

        AbstractPrint printable = (AbstractPrint)getProtocolPrintingService().getProtocolPrintable(printType);
        printable.setPrintableBusinessObject(protocol);
        printable.setReportParameters(reportParameters);
        fileName.append(reportType).append("-");
        return printable;
    }

    private ProtocolPrintingService getProtocolPrintingService() {
        return KraServiceLocator.getService(ProtocolPrintingService.class);
    }

    /**
     * Adds a risk level to the bean indicated by the task name in the request.
     * 
     * @param mapping The mapping associated with this action.
     * @param form The Protocol form.
     * @param request The HTTP request
     * @param response The HTTP response
     * @return the forward to the current page
     */
    public ActionForward addRiskLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument document = protocolForm.getProtocolDocument();
        ProtocolRiskLevelBean protocolRiskLevelBean = getProtocolRiskLevelBean(mapping, form, request, response);
        
        if (protocolRiskLevelBean != null) {
            String errorPropertyName = protocolRiskLevelBean.getErrorPropertyKey();
            ProtocolRiskLevel newProtocolRiskLevel = protocolRiskLevelBean.getNewProtocolRiskLevel();
            Protocol protocol = document.getProtocol();
            
            if (applyRules(new ProtocolAddRiskLevelEvent(document, errorPropertyName, newProtocolRiskLevel))) {
                getProtocolRiskLevelService().addRiskLevel(newProtocolRiskLevel, protocol);
                
                protocolRiskLevelBean.setNewProtocolRiskLevel(new ProtocolRiskLevel());
            }
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * Updates a persisted risk level in the bean indicated by the task name in the request, moving the persisted risk level to Inactive status and adding a 
     * new Active status risk level.
     * 
     * @param mapping The mapping associated with this action.
     * @param form The Protocol form.
     * @param request The HTTP request
     * @param response The HTTP response
     * @return the forward to the current page
     */
    public ActionForward updateRiskLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument document = protocolForm.getProtocolDocument();
        ProtocolRiskLevelBean protocolRiskLevelBean = getProtocolRiskLevelBean(mapping, form, request, response);
        
        if (protocolRiskLevelBean != null) {
            int lineNumber = getSelectedLine(request);
            ProtocolRiskLevel currentProtocolRiskLevel = document.getProtocol().getProtocolRiskLevels().get(lineNumber);
            ProtocolRiskLevel newProtocolRiskLevel = protocolRiskLevelBean.getNewProtocolRiskLevel();
            
            if (applyRules(new ProtocolUpdateRiskLevelEvent(document, lineNumber))) {
                getProtocolRiskLevelService().updateRiskLevel(currentProtocolRiskLevel, newProtocolRiskLevel);
            }
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Deletes a risk level from the bean indicated by the task name in the request.
     * 
     * @param mapping The mapping associated with this action.
     * @param form The Protocol form.
     * @param request The HTTP request
     * @param response The HTTP response
     * @return the forward to the current page
     */
    public ActionForward deleteRiskLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument document = protocolForm.getProtocolDocument();
        ProtocolRiskLevelBean protocolRiskLevelBean = getProtocolRiskLevelBean(mapping, form, request, response);
        
        if (protocolRiskLevelBean != null) {
            int lineNumber = getSelectedLine(request);
            Protocol protocol = document.getProtocol();
            
            getProtocolRiskLevelService().deleteRiskLevel(lineNumber, protocol);
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    private ProtocolRiskLevelBean getProtocolRiskLevelBean(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ProtocolRiskLevelBean protocolRiskLevelBean = null;
        
        ProtocolActionBean protocolActionBean = getActionBean(form, request);
        if (protocolActionBean != null && protocolActionBean instanceof ProtocolRiskLevelCommentable) {
            protocolRiskLevelBean = ((ProtocolRiskLevelCommentable) protocolActionBean).getProtocolRiskLevelBean();
        }
        
        return protocolRiskLevelBean;
    }

    /**
     * Adds a review comment to the bean indicated by the task name in the request.
     * 
     * @param mapping The mapping associated with this action.
     * @param form The Protocol form.
     * @param request The HTTP request
     * @param response The HTTP response
     * @return the forward to the current page
     */
    public ActionForward addReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument document = protocolForm.getProtocolDocument();
        ReviewCommentsBean reviewCommentsBean = getReviewCommentsBean(mapping, form, request, response);
        
        if (reviewCommentsBean != null) {
            String errorPropertyName = reviewCommentsBean.getErrorPropertyName();
            CommitteeScheduleMinute newReviewComment = reviewCommentsBean.getNewReviewComment();
            List<CommitteeScheduleMinute> reviewComments = reviewCommentsBean.getReviewComments();
            Protocol protocol = document.getProtocol();
            
            if (applyRules(new ProtocolAddReviewCommentEvent(document, errorPropertyName, newReviewComment))) {
                getReviewCommentsService().addReviewComment(newReviewComment, reviewComments, protocol);
                
                reviewCommentsBean.setNewReviewComment(new CommitteeScheduleMinute(MinuteEntryType.PROTOCOL));
            }
            reviewCommentsBean.setHideReviewerName(getReviewCommentsService().setHideReviewerName(reviewCommentsBean.getReviewComments()));            
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    

    /**
     * Moves up a review comment in the bean indicated by the task name in the request.
     * 
     * @param mapping The mapping associated with this action.
     * @param form The Protocol form.
     * @param request The HTTP request
     * @param response The HTTP response
     * @return the forward to the current page
     */
    public ActionForward moveUpReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument document = protocolForm.getProtocolDocument();
        ReviewCommentsBean reviewCommentsBean = getReviewCommentsBean(mapping, form, request, response);
        
        if (reviewCommentsBean != null) {
            List<CommitteeScheduleMinute> reviewComments = reviewCommentsBean.getReviewComments();
            Protocol protocol = document.getProtocol();
            int lineNumber = getSelectedLine(request);
    
            getReviewCommentsService().moveUpReviewComment(reviewComments, protocol, lineNumber);
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Moves down a review comment in the bean indicated by the task name in the request.
     * 
     * @param mapping The mapping associated with this action.
     * @param form The Protocol form.
     * @param request The HTTP request
     * @param response The HTTP response
     * @return the forward to the current page
     */
    public ActionForward moveDownReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument document = protocolForm.getProtocolDocument();
        ReviewCommentsBean reviewCommentsBean = getReviewCommentsBean(mapping, form, request, response);
        
        if (reviewCommentsBean != null) {
            List<CommitteeScheduleMinute> reviewComments = reviewCommentsBean.getReviewComments();
            Protocol protocol = document.getProtocol();
            int lineNumber = getSelectedLine(request);
            
            getReviewCommentsService().moveDownReviewComment(reviewComments, protocol, lineNumber);
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Deletes a review comment from the bean indicated by the task name in the request.
     * 
     * @param mapping The mapping associated with this action.
     * @param form The Protocol form.
     * @param request The HTTP request
     * @param response The HTTP response
     * @return the forward to the current page
     */
    public ActionForward deleteReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ReviewCommentsBean reviewCommentsBean = getReviewCommentsBean(mapping, form, request, response);
        
        if (reviewCommentsBean != null) {
            List<CommitteeScheduleMinute> reviewComments = reviewCommentsBean.getReviewComments();
            int lineNumber = getLineToDelete(request);
            List<CommitteeScheduleMinute> deletedReviewComments = reviewCommentsBean.getDeletedReviewComments();
            
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

        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolTask task = new ProtocolTask(TaskName.ABANDON_PROTOCOL, protocolForm.getProtocolDocument().getProtocol());
        if (isAuthorized(task)) {
            getProtocolAbandonService().abandonProtocol(protocolForm.getProtocolDocument().getProtocol(),
                    protocolForm.getActionHelper().getProtocolAbandonBean());
            protocolForm.getProtocolHelper().prepareView();
            
            recordProtocolActionSuccess("Abandon");
            org.kuali.kra.irb.actions.ProtocolAction lastAction = protocolForm.getProtocolDocument().getProtocol().getLastProtocolAction();
            return checkToSendNotification(mapping, mapping.findForward(PROTOCOL_ACTIONS_TAB), protocolForm, new ProtocolNotificationRequestBean(protocolForm.getProtocolDocument().getProtocol(),ProtocolActionType.ABANDON_PROTOCOL, "Abandon"));

        }

        // should it return to portal page ?
        return mapping.findForward(Constants.MAPPING_BASIC);
    }


    
    /**
     * Saves the review comments to the database and performs refresh and cleanup.
     * @param protocolForm
     * @param actionBean
     * @throws Exception
     */
    private void saveReviewComments(ProtocolForm protocolForm, ReviewCommentsBean actionBean) throws Exception { 
        getReviewCommentsService().saveReviewComments(actionBean.getReviewComments(), actionBean.getDeletedReviewComments());           
        actionBean.setDeletedReviewComments(new ArrayList<CommitteeScheduleMinute>());
        protocolForm.getActionHelper().prepareCommentsView();
    }
    
    private ReviewCommentsBean getReviewCommentsBean(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ReviewCommentsBean reviewCommentsBean = null;
        
        ProtocolActionBean protocolActionBean = getActionBean(form, request);
        if (protocolActionBean != null && protocolActionBean instanceof ProtocolOnlineReviewCommentable) {
            reviewCommentsBean = ((ProtocolOnlineReviewCommentable) protocolActionBean).getReviewCommentsBean();
        }
        
        return reviewCommentsBean;
    }
    
    private ProtocolActionBean getActionBean(ActionForm form, HttpServletRequest request) {
        ProtocolForm protocolForm = (ProtocolForm) form;

        String taskName = getTaskName(request);
        
        ProtocolActionBean protocolActionBean = null;
        if (StringUtils.isNotBlank(taskName)) {
            protocolActionBean = protocolForm.getActionHelper().getActionBean(taskName);
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
    
    private boolean hasPermission(String taskName, Protocol protocol) {
        ProtocolTask task = new ProtocolTask(taskName, protocol);
        return getTaskAuthorizationService().isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), task);
    }
    
    private boolean hasGenericPermission(String genericActionName, Protocol protocol) {
        ProtocolTask task = new ProtocolTask(TaskName.GENERIC_PROTOCOL_ACTION, protocol, genericActionName);
        return getTaskAuthorizationService().isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), task);
    }
    
    private ProtocolAttachmentService getProtocolAttachmentService() {
        return KraServiceLocator.getService(ProtocolAttachmentService.class);
    }
    
    private TaskAuthorizationService getTaskAuthorizationService() {
        return KraServiceLocator.getService(TaskAuthorizationService.class);
    }
    
    private ProtocolGenericActionService getProtocolGenericActionService() {
        return KraServiceLocator.getService(ProtocolGenericActionService.class);
    }
     
    private ProtocolAbandonService getProtocolAbandonService() {
        return KraServiceLocator.getService(ProtocolAbandonService.class);
    }
       
    public ProtocolCopyService getProtocolCopyService() {
        return KraServiceLocator.getService(ProtocolCopyService.class);
    }
    
    private ProtocolSubmitActionService getProtocolSubmitActionService() {
        return KraServiceLocator.getService(ProtocolSubmitActionService.class);
    }
    
    private ProtocolWithdrawService getProtocolWithdrawService() {
        return KraServiceLocator.getService(ProtocolWithdrawService.class);
    }
    
    private ProtocolRequestService getProtocolRequestService() {
        return KraServiceLocator.getService(ProtocolRequestService.class);
    }
    
    private ProtocolNotifyIrbService getProtocolNotifyIrbService() {
        return KraServiceLocator.getService(ProtocolNotifyIrbService.class);
    }
    
    private ProtocolAmendRenewService getProtocolAmendRenewService() {
        return KraServiceLocator.getService(ProtocolAmendRenewService.class);
    }
    
    private ProtocolDeleteService getProtocolDeleteService() {
        return KraServiceLocator.getService(ProtocolDeleteService.class);
    }
    
    private ProtocolAssignCmtSchedService getProtocolAssignCmtSchedService() {
        return KraServiceLocator.getService(ProtocolAssignCmtSchedService.class);
    }
    
    private ProtocolAssignToAgendaService getProtocolAssignToAgendaService() {
        return KraServiceLocator.getService(ProtocolAssignToAgendaService.class);
    }
    
    private ProtocolAssignReviewersService getProtocolAssignReviewersService() {
        return KraServiceLocator.getService(ProtocolAssignReviewersService.class);
    }
    
    private ProtocolGrantExemptionService getProtocolGrantExemptionService() {
        return KraServiceLocator.getService(ProtocolGrantExemptionService.class);
    }
    
    private ProtocolApproveService getProtocolApproveService() {
        return KraServiceLocator.getService(ProtocolApproveService.class);
    }
    
    private CommitteeService getCommitteeService() {
        return KraServiceLocator.getService(CommitteeService.class);
    }
    
    private CommitteeDecisionService getCommitteeDecisionService() {
        return KraServiceLocator.getService("protocolCommitteeDecisionService");
    }
    
    private ProtocolRiskLevelService getProtocolRiskLevelService() {
        return KraServiceLocator.getService(ProtocolRiskLevelService.class);
    }
    
    private ReviewCommentsService getReviewCommentsService() {
        return KraServiceLocator.getService(ReviewCommentsService.class);
    }
    
    /**
     * 
     * This method is to add a file to notify irb 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addNotifyIrbAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (((ProtocolForm) form).getActionHelper().validFile(((ProtocolForm) form).getActionHelper().getProtocolNotifyIrbBean().getNewActionAttachment(), "protocolNotifyIrbBean")) {
            LOG.info("addNotifyIrbAttachment " +((ProtocolForm) form).getActionHelper().getProtocolNotifyIrbBean().getNewActionAttachment().getFile().getFileName()
                    + ((ProtocolForm) form).getProtocolDocument().getDocumentNumber());
            ((ProtocolForm) form).getActionHelper().addNotifyIrbAttachment();
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
    public ActionForward viewNotifyIrbAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return this.viewAttachment(mapping, (ProtocolForm) form, request, response);
    }

    /*
     * utility to view file 
     */
    private ActionForward viewAttachment(ActionMapping mapping, ProtocolForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        int selection = this.getSelectedLine(request);
        ProtocolActionAttachment attachment = form.getActionHelper().getProtocolNotifyIrbBean().getActionAttachments().get(
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
    public ActionForward deleteNotifyIrbAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return confirmDeleteAttachment(mapping, (ProtocolForm) form, request, response, ((ProtocolForm) form).getActionHelper().getProtocolNotifyIrbBean().getActionAttachments());
    }

    /*
     * confirmation question for delete norify irb file or request attachment file
     */
    private ActionForward confirmDeleteAttachment(ActionMapping mapping, ProtocolForm form, HttpServletRequest request,
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

        if (StringUtils.isBlank(taskName)) {
            ProtocolNotifyIrbBean notifyIrbBean = ((ProtocolForm) form).getActionHelper().getProtocolNotifyIrbBean();
            notifyIrbBean.getActionAttachments().remove(selection);
        } else {
            ProtocolRequestBean requestBean = getProtocolRequestBean(form, request);
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
        ProtocolForm protocolForm = (ProtocolForm) form;
        int actionIndex = getSelectedLine(request);
        int attachmentIndex = getSelectedAttachment(request);
        org.kuali.kra.irb.actions.ProtocolAction protocolAction = protocolForm.getActionHelper().getProtocol().getProtocolActions().get(actionIndex);
        ProtocolSubmissionDoc attachment = protocolAction.getProtocolSubmissionDocs().get(attachmentIndex);

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
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        int actionIndex = getSelectedLine(request);
        int attachmentIndex = getSelectedAttachment(request);
        org.kuali.kra.irb.actions.ProtocolAction protocolAction = protocolForm.getActionHelper().getProtocol().getProtocolActions().get(actionIndex);
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
    
    /**
     * 
     * This method is to add attachment for several request actions.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addRequestAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolRequestBean requestBean = getProtocolRequestBean(form, request);
        
        if (protocolForm.getActionHelper().validFile(requestBean.getNewActionAttachment(), requestBean.getBeanName())) {
            // add this log to trace if there is any further issue
            LOG.info("addRequestAttachment " + requestBean.getProtocolActionTypeCode() + " " + requestBean.getNewActionAttachment().getFile().getFileName()
                    + protocolForm.getProtocolDocument().getDocumentNumber());
            
            requestBean.getActionAttachments().add(requestBean.getNewActionAttachment());
            requestBean.setNewActionAttachment(new ProtocolActionAttachment());
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * 
     * This method view the selected attachment from the request action panel
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward viewRequestAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {

        ProtocolRequestBean requestBean = getProtocolRequestBean(form, request);
        int selection = getSelectedLine(request);
        
        if (requestBean != null) {
            ProtocolActionAttachment attachment = requestBean.getActionAttachments().get(selection);
    
            if (attachment == null) {
                LOG.info(NOT_FOUND_SELECTION + selection);
                // may want to tell the user the selection was invalid.
                return mapping.findForward(Constants.MAPPING_BASIC);
            }
    
            this.streamToResponse(attachment.getFile().getFileData(), getValidHeaderString(attachment.getFile().getFileName()),
                    getValidHeaderString(attachment.getFile().getContentType()), response);
        }
        
        return RESPONSE_ALREADY_HANDLED;
    }

    /**
     * 
     * This method is to delete the selected request action attachment
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteRequestAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {
        
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        
        ProtocolRequestBean requestBean = getProtocolRequestBean(form, request);
        if (requestBean != null) {
            forward = confirmDeleteAttachment(mapping, (ProtocolForm) form, request, response, requestBean.getActionAttachments());
        }
        
        return forward;
    }
    
    private void recordProtocolActionSuccess(String protocolActionName) {
        KNSGlobalVariables.getMessageList().add(KeyConstants.MESSAGE_PROTOCOL_ACTION_SUCCESSFULLY_COMPLETED, protocolActionName);
    }
    
    /**
     * Method called when adding a protocol note.
     * 
     * @param mapping the action mapping
     * @param form the form.
     * @param request the request.
     * @param response the response.
     * @return an action forward.
     * @throws Exception if there is a problem executing the request.
     */
    public ActionForward addNote(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        if (protocolForm.getActionHelper().getCanManageNotes()) {
            protocolForm.getNotesAttachmentsHelper().addNewNote();
            protocolForm.getNotesAttachmentsHelper().setManageNotesOpen(true);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Method called when modifying an existing protocol note.
     * 
     * @param mapping the action mapping
     * @param form the form.
     * @param request the request.
     * @param response the response.
     * @return an action forward.
     * @throws Exception if there is a problem executing the request.
     */
    public ActionForward editNote(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        if (protocolForm.getActionHelper().getCanManageNotes()) {
            int selection = this.getSelectedLine(request);
            protocolForm.getNotesAttachmentsHelper().modifyNote(selection);
            protocolForm.getNotesAttachmentsHelper().setManageNotesOpen(true);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Method called when deleting an existing protocol note.
     * 
     * @param mapping the action mapping
     * @param form the form.
     * @param request the request.
     * @param response the response.
     * @return an action forward.
     * @throws Exception if there is a problem executing the request.
     */
    public ActionForward deleteNote(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        
        if (protocolForm.getActionHelper().getCanManageNotes()) {
            if (protocolForm.getActionHelper().getCanManageNotes()) {
                int noteToDelete = getLineToDelete(request);
                protocolForm.getNotesAttachmentsHelper().deleteNote(noteToDelete);
                protocolForm.getNotesAttachmentsHelper().setManageNotesOpen(false);
            }
        }
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
    public ActionForward saveNotes(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        Protocol protocol = protocolForm.getProtocolDocument().getProtocol();
            
        if (!hasDocumentStateChanged(protocolForm)) {
            if (protocolForm.getActionHelper().getCanManageNotes()) {
                
                final AddProtocolNotepadRule rule = new AddProtocolNotepadRuleImpl();
                    
                //final AddProtocolNotepadEvent event = new AddProtocolNotepadEvent(this.form.getDocument(), this.newProtocolNotepad);
                boolean validNotes = true;
                //validate all of them first
                for (ProtocolNotepad note : protocol.getNotepads()) {
                    if (note.isEditable()) {
                        AddProtocolNotepadEvent event = new AddProtocolNotepadEvent(protocol.getProtocolDocument(), note);
                        if (!rule.processAddProtocolNotepadRules(event)) {
                            validNotes = false;
                        }
                    }
                }
                
                if (validNotes) {
                    for (ProtocolNotepad note : protocol.getNotepads()) {
                        if (StringUtils.isBlank(note.getUpdateUserFullName())) {
                            note.setUpdateUserFullName(GlobalVariables.getUserSession().getPerson().getName());
                            note.setUpdateTimestamp(KraServiceLocator.getService(DateTimeService.class).getCurrentTimestamp());
                        }
                        note.setEditable(false);
                    }
                    getBusinessObjectService().save(protocol.getNotepads());
                    recordProtocolActionSuccess("Manage Notes");
                }
            }
        } else {
            GlobalVariables.getMessageMap().clearErrorMessages();
            GlobalVariables.getMessageMap().putError("documentstatechanged", KeyConstants.ERROR_PROTOCOL_DOCUMENT_STATE_CHANGED,  new String[] {}); 
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
       

    public ActionForward submissionQuestionnaire(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        ((ProtocolForm)form).getQuestionnaireHelper().prepareView();
        ((ProtocolForm)form).getQuestionnaireHelper().setSubmissionActionTypeCode(getSubmitActionType(request));
        // TODO : if questionnaire is already populated, then don't need to do it
            ProtocolSubmissionBeanBase submissionBean = getSubmissionBean(form, ((ProtocolForm)form).getQuestionnaireHelper().getSubmissionActionTypeCode());
            if (CollectionUtils.isEmpty(submissionBean.getAnswerHeaders())) {
                ((ProtocolForm)form).getQuestionnaireHelper().populateAnswers();
                submissionBean.setAnswerHeaders(((ProtocolForm)form).getQuestionnaireHelper().getAnswerHeaders());
            } else {
                ((ProtocolForm)form).getQuestionnaireHelper().setAnswerHeaders(submissionBean.getAnswerHeaders());
            }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }


    /*
     * confirmation question for followup action
     */
    private ActionForward confirmFollowupAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response, String forward) throws Exception {

        //List<ValidProtocolActionAction> validFollowupActions = getFollowupActionService().getFollowupsForProtocol(((ProtocolForm)form).getProtocolDocument().getProtocol());
        List<ValidProtocolActionAction> validFollowupActions = getFollowupActionService().getFollowupsForProtocol(((ProtocolForm)form).getProtocolDocument().getProtocol());

        if (validFollowupActions.isEmpty()) {
            LOG.info("No followup action");
            return mapping.findForward(forward);
        } else if (!validFollowupActions.get(0).getUserPromptFlag()) {
            addFollowupAction(((ProtocolForm)form).getProtocolDocument().getProtocol());
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

        addFollowupAction(((ProtocolForm)form).getProtocolDocument().getProtocol());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    private void addFollowupAction(Protocol protocol) throws Exception {

        List<ValidProtocolActionAction> validFollowupActions = getFollowupActionService().getFollowupsForProtocol(protocol);
        protocol.getLastProtocolAction().setFollowupActionCode(validFollowupActions.get(0).getFollowupActionCode());
        getBusinessObjectService().save(protocol.getLastProtocolAction());
    }

    private void setQnCompleteStatus(List<AnswerHeader> answerHeaders) {
        for (AnswerHeader answerHeader : answerHeaders) {
            answerHeader.setCompleted(getQuestionnaireAnswerService().isQuestionnaireAnswerComplete(answerHeader.getAnswers()));
        }
    }
    
    private QuestionnaireAnswerService getQuestionnaireAnswerService() {
        return KraServiceLocator.getService(QuestionnaireAnswerService.class);
    }
    private FollowupActionService getFollowupActionService() {
        return KraServiceLocator.getService(FollowupActionService.class);
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
        ReviewAttachmentsBean reviewAttachmentsBean = getReviewAttachmentsBean(mapping, form, request, response);

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
          return streamReviewAttachment(mapping, request, response, ((ProtocolForm)form).getActionHelper().getReviewAttachments());
    }
    
    private ActionForward streamReviewAttachment (ActionMapping mapping, HttpServletRequest request, HttpServletResponse response, List<ProtocolReviewAttachment> reviewAttachments) throws Exception {

        int lineNumber = getLineToDelete(request);
        final ProtocolReviewAttachment attachment = reviewAttachments.get(lineNumber);
        
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
        ReviewAttachmentsBean reviewAttachmentsBean = getReviewAttachmentsBean(mapping, form, request, response);
        
        if (reviewAttachmentsBean != null) {
            List<ProtocolReviewAttachment> reviewAttachments = reviewAttachmentsBean.getReviewAttachments();
            int lineNumber = getLineToDelete(request);
            List<ProtocolReviewAttachment> deletedReviewAttachments = reviewAttachmentsBean.getDeletedReviewAttachments();
            
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
    private ReviewAttachmentsBean getReviewAttachmentsBean(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ReviewAttachmentsBean reviewAttachmentsBean = null;
        
        ProtocolActionBean protocolActionBean = getActionBean(form, request);
        if (protocolActionBean != null && protocolActionBean instanceof ProtocolOnlineReviewCommentable) {
            reviewAttachmentsBean = ((ProtocolOnlineReviewCommentable) protocolActionBean).getReviewAttachmentsBean();
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
    public ActionForward manageAttachments(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ProtocolForm protocolForm = (ProtocolForm) form;
        if(!hasDocumentStateChanged(protocolForm)) {
            if (hasPermission(TaskName.PROTOCOL_MANAGE_REVIEW_COMMENTS, protocolForm.getProtocolDocument().getProtocol())) {
                if (applyRules(new ProtocolManageReviewAttachmentEvent(protocolForm.getDocument(),
                    "actionHelper.protocolManageReviewCommentsBean.reviewAttachmentsBean.", protocolForm.getActionHelper()
                            .getProtocolManageReviewCommentsBean().getReviewAttachmentsBean().getReviewAttachments()))) {
                    ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolManageReviewCommentsBean();
                    saveReviewAttachments(protocolForm, actionBean.getReviewAttachmentsBean());
    
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
    private void saveReviewAttachments(ProtocolForm protocolForm, ReviewAttachmentsBean actionBean) throws Exception { 
        getReviewCommentsService().saveReviewAttachments(actionBean.getReviewAttachments(), actionBean.getDeletedReviewAttachments());           
        actionBean.setDeletedReviewAttachments(new ArrayList<ProtocolReviewAttachment>());
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
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument document = protocolForm.getProtocolDocument();
        ReviewAttachmentsBean reviewAttachmentsBean = getReviewAttachmentsBean(mapping, form, request, response);
        
        if (reviewAttachmentsBean != null) {
            String errorPropertyName = reviewAttachmentsBean.getErrorPropertyName();
            ProtocolReviewAttachment newReviewAttachment = reviewAttachmentsBean.getNewReviewAttachment();
            List<ProtocolReviewAttachment> reviewAttachments = reviewAttachmentsBean.getReviewAttachments();
            Protocol protocol = document.getProtocol();
            
            if (applyRules(new ProtocolAddReviewAttachmentEvent(document, errorPropertyName, newReviewAttachment))) {
                getReviewCommentsService().addReviewAttachment(newReviewAttachment, reviewAttachments, protocol);
                
                reviewAttachmentsBean.setNewReviewAttachment(new ProtocolReviewAttachment());
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
    private boolean hasDocumentStateChanged(ProtocolForm protocolForm) {
        boolean result = false;
        
        Map<String,Object> primaryKeys = new HashMap<String, Object>();
        primaryKeys.put("protocolId", protocolForm.getProtocolDocument().getProtocol().getProtocolId());
        Protocol dbProtocol = (Protocol)getBusinessObjectService().findByPrimaryKey(Protocol.class, primaryKeys);
        
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
    
    private boolean isDocumentPostprocessingComplete(ProtocolDocument document) {
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
    
    private ActionForward checkToSendNotification(ActionMapping mapping, ActionForward forward, ProtocolForm protocolForm, ProtocolNotificationRequestBean notificationRequestBean) {
        
        IRBNotificationRenderer renderer = new IRBNotificationRenderer(notificationRequestBean.getProtocol());
        if (StringUtils.equals(ProtocolActionType.NOTIFY_IRB, notificationRequestBean.getActionType())) {
            //renderer = new NotifyIrbNotificationRenderer(notificationRequestBean.getProtocol(), notificationRequestBean.getProtocol().getLastProtocolAction().getComments());
            renderer = new NotifyIrbNotificationRenderer(notificationRequestBean.getProtocol(), protocolForm.getActionHelper().getProtocolNotifyIrbBean().getComment());

        }
        IRBNotificationContext context = new IRBNotificationContext(notificationRequestBean.getProtocol(), notificationRequestBean.getActionType(), notificationRequestBean.getDescription(), renderer);
        
        if (protocolForm.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
            context.setForwardName(forward.getName());
            protocolForm.getNotificationHelper().initializeDefaultValues(context);
             return mapping.findForward("protocolNotificationEditor");
        } else {
            getNotificationService().sendNotification(context);
            return forward;
        }
    }
    
    /*
     * This is for assign reviewer and submit for review.  The notificationRequestBeans contains all 'added' or 'removed'
     * reviewers.  All the roles recipient will be merged, then forward to protocolnotificationeditor for ad hoc notification 
     * process.
     */
    private ActionForward checkToSendNotification(ActionMapping mapping, ActionForward forward, ProtocolForm protocolForm,
            IRBNotificationRenderer renderer, List<ProtocolNotificationRequestBean> notificationRequestBeans) {

        // AssignReviewerNotificationRenderer renderer = new AssignReviewerNotificationRenderer(protocol, "added");
        IRBNotificationContext context = new IRBNotificationContext(notificationRequestBeans.get(0).getProtocol(),
            notificationRequestBeans.get(0).getProtocolOnlineReview(), notificationRequestBeans.get(0).getActionType(),
            notificationRequestBeans.get(0).getDescription(), renderer);
        context.setPopulateRole(true);
        if (protocolForm.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
//            context.setForwardName(forward.getName());
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
                context = new IRBNotificationContext(notificationRequestBeans.get(i).getProtocol(), notificationRequestBeans.get(i)
                        .getProtocolOnlineReview(), notificationRequestBeans.get(i).getActionType(), notificationRequestBeans
                        .get(i).getDescription(), renderer);
                context.setPopulateRole(true);
                // protocolForm.getNotificationHelper().setNotificationRecipients(new ArrayList<NotificationTypeRecipient>());
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
                // allRecipients.addAll(recipients);
                i++;
            }
            protocolForm.getNotificationHelper().setNotificationRecipients(allRecipients);
            if (forward == null) {
                context.setForwardName("holdingPage");
            } else {
                context.setForwardName(forward.getName());
            }
        return mapping.findForward("protocolNotificationEditor");
        }
        else {
//            int i = 0;
//            while (notificationRequestBeans.size() > i) {
//                context = new IRBNotificationContext(notificationRequestBeans.get(i).getProtocol(), notificationRequestBeans.get(i)
//                        .getProtocolOnlineReview(), notificationRequestBeans.get(i).getActionType(), notificationRequestBeans
//                        .get(i).getDescription(), renderer);
//                protocolForm.getNotificationHelper().initializeDefaultValues(context);
//                List<NotificationTypeRecipient> recipients = protocolForm.getNotificationHelper().getNotificationRecipients();
//
//                List<NotificationTypeRecipient> allRecipients = new ArrayList<NotificationTypeRecipient>();
//                for (NotificationTypeRecipient recipient : recipients) {
//                    try {
//                        // note : need to deepcopy here. If I don't do that, then all reviewer role will have same
//                        // notificationrecipient object returned from service call
//                        // probably the object service/ojb has a cache ?
//                        NotificationTypeRecipient copiedRecipient = (NotificationTypeRecipient) ObjectUtils.deepCopy(recipient);
//                        context.populateRoleQualifiers(copiedRecipient);
//                        allRecipients.add(copiedRecipient);
//                    } catch (Exception e) {
//                        
//                    }
//                }
//                protocolForm.getNotificationHelper().setNotificationRecipients(allRecipients);
//                getNotificationService().sendNotification(context);
//                i++;
//            }
            return forward;
        }
    }
    
    public ActionForward sendNotification(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        Protocol protocol = protocolForm.getProtocolDocument().getProtocol();
        
        IRBNotificationRenderer renderer = new IRBNotificationRenderer(protocol);
        IRBNotificationContext context = new IRBNotificationContext(protocol, null, "Ad-Hoc Notification", renderer);
        
        protocolForm.getNotificationHelper().initializeDefaultValues(context);
        
        return mapping.findForward("protocolNotificationEditor");
    }

    protected PersonService getPersonService() {
        return KraServiceLocator.getService(PersonService.class);
    }
    
    protected KcNotificationService getNotificationService() {
        return KraServiceLocator.getService(KcNotificationService.class);
    }
}