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

import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;
import static org.kuali.rice.kns.util.KNSConstants.QUESTION_INST_ATTRIBUTE_NAME;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.internet.HeaderTokenizer;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.kuali.kra.authorization.ApplicationTask;
import org.kuali.kra.bo.AttachmentFile;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolAction;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.actions.acknowledgement.IrbAcknowledgementBean;
import org.kuali.kra.irb.actions.acknowledgement.IrbAcknowledgementService;
import org.kuali.kra.irb.actions.amendrenew.CreateAmendmentEvent;
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
import org.kuali.kra.irb.actions.defer.ProtocolDeferService;
import org.kuali.kra.irb.actions.delete.ProtocolDeleteService;
import org.kuali.kra.irb.actions.expediteapproval.ProtocolExpediteApprovalService;
import org.kuali.kra.irb.actions.genericactions.ProtocolGenericActionBean;
import org.kuali.kra.irb.actions.genericactions.ProtocolGenericActionService;
import org.kuali.kra.irb.actions.grantexemption.ProtocolGrantExemptionBean;
import org.kuali.kra.irb.actions.grantexemption.ProtocolGrantExemptionService;
import org.kuali.kra.irb.actions.modifysubmission.ProtocolModifySubmissionAction;
import org.kuali.kra.irb.actions.modifysubmission.ProtocolModifySubmissionEvent;
import org.kuali.kra.irb.actions.modifysubmission.ProtocolModifySubmissionService;
import org.kuali.kra.irb.actions.notifyirb.ProtocolNotifyIrbService;
import org.kuali.kra.irb.actions.print.ProtocolPrintType;
import org.kuali.kra.irb.actions.print.ProtocolPrintingService;
import org.kuali.kra.irb.actions.request.ProtocolRequestBean;
import org.kuali.kra.irb.actions.request.ProtocolRequestEvent;
import org.kuali.kra.irb.actions.request.ProtocolRequestService;
import org.kuali.kra.irb.actions.reviewcomments.ReviewerComments;
import org.kuali.kra.irb.actions.reviewcomments.ReviewerCommentsService;
import org.kuali.kra.irb.actions.risklevel.ProtocolAddRiskLevelEvent;
import org.kuali.kra.irb.actions.risklevel.ProtocolRiskLevelBean;
import org.kuali.kra.irb.actions.risklevel.ProtocolUpdateRiskLevelEvent;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitAction;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitActionEvent;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitActionService;
import org.kuali.kra.irb.actions.undo.UndoLastActionBean;
import org.kuali.kra.irb.actions.undo.UndoLastActionService;
import org.kuali.kra.irb.actions.withdraw.ProtocolWithdrawService;
import org.kuali.kra.irb.auth.GenericProtocolAuthorizer;
import org.kuali.kra.irb.auth.ProtocolTask;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentBase;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentProtocol;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentService;
import org.kuali.kra.irb.summary.AttachmentSummary;
import org.kuali.kra.irb.summary.ProtocolSummary;
import org.kuali.kra.printing.Printable;
import org.kuali.kra.printing.print.AbstractPrint;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.kra.web.struts.action.AuditActionHelper;
import org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase;
import org.kuali.kra.web.struts.action.StrutsConfirmation;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.web.struts.action.AuditModeAction;

/**
 * The set of actions for the Protocol Actions tab.
 */
public class ProtocolProtocolActionsAction extends ProtocolAction implements AuditModeAction {

    private static final Log LOG = LogFactory.getLog(ProtocolProtocolActionsAction.class);

    private static final String PROTOCOL_TAB = "protocol";
    private static final String CONFIRM_SUBMIT_FOR_REVIEW_KEY = "confirmSubmitForReview";
    private static final String CONFIRM_ASSIGN_TO_AGENDA_KEY = "confirmAssignToAgenda";
    private static final String CONFIRM_ASSIGN_CMT_SCHED_KEY = "confirmAssignCmtSched";
    
    private static final String NOT_FOUND_SELECTION = "The attachment was not found for selection ";

    private static final String CONFIRM_DELETE_PROTOCOL_KEY = "confirmDeleteProtocol";

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

    
    /** {@inheritDoc} */
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);

        ((ProtocolForm) form).getActionHelper().prepareView();
        // submit action may change "submission details", so re-initializa it
        ((ProtocolForm) form).getActionHelper().initSubmissionDetails();

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
            loadDocument(protocolForm);
            protocolForm.getActionHelper().setCurrentSubmissionNumber(-1);
            protocolForm.getProtocolHelper().prepareView();

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
                int currentSubmissionCount = (schedule.getProtocolSubmissions() == null) ? 0 : schedule.getProtocolSubmissions().size();
                int maxSubmissionCount = schedule.getMaxProtocols();
                isMax = currentSubmissionCount >= maxSubmissionCount;
            }
        }
        
        return isMax;
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
        
        Object question = request.getParameter(QUESTION_INST_ATTRIBUTE_NAME);
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
        protocolForm.getActionHelper().getAssignToAgendaBean().init();
        
        super.route(mapping, protocolForm, request, response);
        
        ActionForward forward = returnToSender(request, mapping, protocolForm);
        
        Properties parameters = new Properties();
        parameters.put("successfulSubmission", Boolean.TRUE.toString());
        parameters.put("submissionType", "Protocol");
        parameters.put("refId", protocolDocument.getProtocol().getProtocolNumber());
        
        ActionRedirect redirect = new ActionRedirect(forward);
        for (Map.Entry<Object, Object> parameter : parameters.entrySet()) {
            redirect.addParameter(parameter.getKey().toString(), parameter.getValue());
        }
        
        return redirect;
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
        if (isAuthorized(task)) {
            ProtocolDocument pd = getProtocolWithdrawService().withdraw(protocolForm.getProtocolDocument().getProtocol(),
                    protocolForm.getActionHelper().getProtocolWithdrawBean());

            protocolForm.setDocId(pd.getDocumentNumber());
            loadDocument(protocolForm);
            protocolForm.getProtocolHelper().prepareView();

            return mapping.findForward(PROTOCOL_TAB);
        }

        return mapping.findForward(MAPPING_BASIC);
    }

    /**
     * Request a close of the protocol.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward closeRequestProtocol(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_CLOSE, protocolForm.getProtocolDocument().getProtocol());
        if (isAuthorized(task)) {
            ProtocolRequestBean closeRequestBean = protocolForm.getActionHelper().getProtocolCloseRequestBean();
            if (applyRules(new ProtocolRequestEvent(protocolForm.getProtocolDocument(),
                Constants.PROTOCOL_CLOSE_REQUEST_PROPERTY_KEY, closeRequestBean))) {
                getProtocolRequestService().submitRequest(protocolForm.getProtocolDocument().getProtocol(), closeRequestBean);
            }
        }
        return mapping.findForward(MAPPING_BASIC);
    }

    /**
     * Request a suspension of a protocol.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward suspendRequestProtocol(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_SUSPENSION, protocolForm.getProtocolDocument().getProtocol());
        if (isAuthorized(task)) {
            ProtocolRequestBean suspendRequestBean = protocolForm.getActionHelper().getProtocolSuspendRequestBean();
            if (applyRules(new ProtocolRequestEvent(protocolForm.getProtocolDocument(),
                Constants.PROTOCOL_SUSPEND_REQUEST_PROPERTY_KEY, suspendRequestBean))) {
                getProtocolRequestService().submitRequest(protocolForm.getProtocolDocument().getProtocol(), suspendRequestBean);
            }
        }
        return mapping.findForward(MAPPING_BASIC);
    }

    /**
     * Request a close of enrollment for a protocol.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward closeEnrollmentRequestProtocol(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_CLOSE_ENROLLMENT, protocolForm.getProtocolDocument()
                .getProtocol());
        if (isAuthorized(task)) {
            ProtocolRequestBean closeEnrollmentRequestBean = protocolForm.getActionHelper().getProtocolCloseEnrollmentRequestBean();
            if (applyRules(new ProtocolRequestEvent(protocolForm.getProtocolDocument(),
                Constants.PROTOCOL_CLOSE_ENROLLMENT_REQUEST_PROPERTY_KEY, closeEnrollmentRequestBean))) {
                getProtocolRequestService().submitRequest(protocolForm.getProtocolDocument().getProtocol(),
                        closeEnrollmentRequestBean);
            }
        }
        return mapping.findForward(MAPPING_BASIC);
    }

    /**
     * Request to re-open enrollment for a protocol.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward reopenEnrollmentRequestProtocol(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_REOPEN_ENROLLMENT, protocolForm.getProtocolDocument()
                .getProtocol());
        if (isAuthorized(task)) {
            ProtocolRequestBean reopenEnrollmentRequestBean = protocolForm.getActionHelper()
                    .getProtocolReOpenEnrollmentRequestBean();
            if (applyRules(new ProtocolRequestEvent(protocolForm.getProtocolDocument(),
                Constants.PROTOCOL_REOPEN_ENROLLMENT_REQUEST_PROPERTY_KEY, reopenEnrollmentRequestBean))) {
                getProtocolRequestService().submitRequest(protocolForm.getProtocolDocument().getProtocol(),
                        reopenEnrollmentRequestBean);
            }
        }
        return mapping.findForward(MAPPING_BASIC);
    }

    /**
     * Request for data analysis only for a protocol.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward dataAnalysisOnlyRequestProtocol(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_DATA_ANALYSIS, protocolForm.getProtocolDocument()
                .getProtocol());
        if (isAuthorized(task)) {
            ProtocolRequestBean dataAnalysisRequestBean = protocolForm.getActionHelper().getProtocolDataAnalysisRequestBean();
            if (applyRules(new ProtocolRequestEvent(protocolForm.getProtocolDocument(),
                Constants.PROTOCOL_DATA_ANALYSIS_REQUEST_PROPERTY_KEY, dataAnalysisRequestBean))) {
                getProtocolRequestService()
                        .submitRequest(protocolForm.getProtocolDocument().getProtocol(), dataAnalysisRequestBean);
            }
        }
        return mapping.findForward(MAPPING_BASIC);
    }
    
    
    public ActionForward terminateRequestProtocol(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_REQUEST_TERMINATE, protocolForm.getProtocolDocument()
                .getProtocol());
        if (isAuthorized(task)) {
            ProtocolRequestBean terminateRequestBean = protocolForm.getActionHelper().getProtocolTerminateRequestBean();
            if (applyRules(new ProtocolRequestEvent(protocolForm.getProtocolDocument(),
                Constants.PROTOCOL_TERMINATE_REQUEST_PROPERTY_KEY, terminateRequestBean))) {
                getProtocolRequestService()
                        .submitRequest(protocolForm.getProtocolDocument().getProtocol(), terminateRequestBean);
            }
        }
        return mapping.findForward(MAPPING_BASIC);
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
        getProtocolNotifyIrbService().submitIrbNotification(protocolForm.getProtocolDocument().getProtocol(),
                protocolForm.getActionHelper().getProtocolNotifyIrbBean());
        return mapping.findForward(MAPPING_BASIC);
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
                return mapping.findForward(MAPPING_BASIC);
            }

            String newDocId = getProtocolAmendRenewService().createAmendment(protocolForm.getProtocolDocument(),
                    protocolForm.getActionHelper().getProtocolAmendmentBean());
            // Switch over to the new protocol document and
            // go to the Protocol tab web page.

            protocolForm.setDocId(newDocId);
            loadDocument(protocolForm);
            protocolForm.getActionHelper().setCurrentSubmissionNumber(-1);
            protocolForm.getProtocolHelper().prepareView();
            
            return mapping.findForward(PROTOCOL_TAB);
        }
        return mapping.findForward(MAPPING_BASIC);
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
            String newDocId = getProtocolAmendRenewService().createRenewal(protocolForm.getProtocolDocument());
            // Switch over to the new protocol document and
            // go to the Protocol tab web page.

            protocolForm.setDocId(newDocId);
            loadDocument(protocolForm);

            protocolForm.getActionHelper().setCurrentSubmissionNumber(-1);
            protocolForm.getProtocolHelper().prepareView();
            return mapping.findForward(PROTOCOL_TAB);
        }
        return mapping.findForward(MAPPING_BASIC);
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
                return mapping.findForward(MAPPING_BASIC);
            }

            String newDocId = getProtocolAmendRenewService().createRenewalWithAmendment(protocolForm.getProtocolDocument(),
                    protocolForm.getActionHelper().getProtocolRenewAmendmentBean());
            // Switch over to the new protocol document and
            // go to the Protocol tab web page.

            protocolForm.setDocId(newDocId);
            loadDocument(protocolForm);

            protocolForm.getActionHelper().setCurrentSubmissionNumber(-1);
            protocolForm.getProtocolHelper().prepareView();
            return mapping.findForward(PROTOCOL_TAB);
        }
        return mapping.findForward(MAPPING_BASIC);
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
            return confirm(buildDeleteProtocolConfirmationQuestion(mapping, form, request, response), CONFIRM_DELETE_PROTOCOL_KEY,
                    "");

        }
        return mapping.findForward(MAPPING_BASIC);
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
        Object question = request.getParameter(QUESTION_INST_ATTRIBUTE_NAME);
        if (CONFIRM_DELETE_PROTOCOL_KEY.equals(question)) {
            ProtocolForm protocolForm = (ProtocolForm) form;
            getProtocolDeleteService().delete(protocolForm.getProtocolDocument().getProtocol(),
                    protocolForm.getActionHelper().getProtocolDeleteBean());
        }
        return mapping.findForward(MAPPING_BASIC);
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
     * Print one of the various protocol "documents".
     * 
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
        ActionForward forward = mapping.findForward(MAPPING_BASIC);
        if (StringUtils.isBlank(protocolForm.getActionHelper().getPrintTag())) {
            // validation error
        } else if (protocolForm.getActionHelper().getPrintTag().startsWith("attachment:")) {
            // attachment
            int index = Integer.parseInt(protocolForm.getActionHelper().getPrintTag().substring(protocolForm.getActionHelper().getPrintTag().indexOf(":")+1));
            ProtocolAttachmentProtocol attachment = protocolForm.getProtocolDocument().getProtocol().getActiveAttachmentProtocols().get(index);
            forward = printAttachmentProtocol(mapping, response, attachment);
        } else {
            // print protocol
            ProtocolPrintType printType = ProtocolPrintType.valueOf(PRINTTAG_MAP.get(protocolForm.getActionHelper().getPrintTag()));
            List<Printable> printableArtifactList = getPrintableArtifacts(protocolForm.getProtocolDocument(), printType);
            AttachmentDataSource dataStream = getProtocolPrintingService().print(printableArtifactList);
            if(dataStream.getContent()!=null){
                dataStream.setFileName(printType.getTemplate().substring(0, printType.getTemplate().indexOf(".")));
                streamToResponse(dataStream, response);
                forward = null;
            }
        }
        return forward;
    }

    /*
     * This is to view attachment if attachment is seleccted in print panel.
     */
    private ActionForward printAttachmentProtocol(ActionMapping mapping, HttpServletResponse response, ProtocolAttachmentBase attachment) throws Exception {

        if (attachment == null) {
            return mapping.findForward(Constants.MAPPING_BASIC);
        }

        final AttachmentFile file = attachment.getFile();
        this.streamToResponse(file.getData(), getValidHeaderString(file.getName()), getValidHeaderString(file.getType()), response);

        return RESPONSE_ALREADY_HANDLED;
    }

    /**
     * When the "filter" button is pressed in the History sub-panel, we only need to redraw the page. The results will changed based
     * upon the contents of the Data Range Filter.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward filterHistory(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return mapping.findForward(MAPPING_BASIC);
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
        return mapping.findForward(MAPPING_BASIC);
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
        int selectedIndex = getSelectedLine(request);
        AttachmentSummary attachmentSummary = protocolSummary.getAttachments().get(selectedIndex);
        
        ProtocolAttachmentProtocol attachment = getProtocolAttachmentService().getAttachment(ProtocolAttachmentProtocol.class, 
                attachmentSummary.getAttachmentId());
        AttachmentFile file = attachment.getFile();
        streamToResponse(file.getData(), getValidHeaderString(file.getName()), getValidHeaderString(file.getType()), response);
        
        return RESPONSE_ALREADY_HANDLED;
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

        return mapping.findForward(MAPPING_BASIC);
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
        
        return mapping.findForward(MAPPING_BASIC);
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
    public ActionForward viewPreviousSubmission(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ProtocolForm protocolForm = (ProtocolForm) form;
        ActionHelper actionHelper = protocolForm.getActionHelper();
        actionHelper.setCurrentSubmissionNumber(actionHelper.getCurrentSubmissionNumber() - 1);
        protocolForm.getActionHelper().initSubmissionDetails();
        return mapping.findForward(MAPPING_BASIC);
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
        actionHelper.setCurrentSubmissionNumber(actionHelper.getCurrentSubmissionNumber() + 1);
        protocolForm.getActionHelper().initSubmissionDetails();
        return mapping.findForward(MAPPING_BASIC);
    }

    /**
     * Quotes a string that follows RFC 822 and is valid to include in an http header.
     * 
     * <p>
     * This really should be a part of {@link org.kuali.rice.kns.util.WebUtils WebUtils}.
     * <p>
     * 
     * For example: without this method, file names with spaces will not show up to the client correctly.
     * 
     * <p>
     * This method is not doing a Base64 encode just a quoted printable character otherwise we would have to set the encoding type
     * on the header.
     * <p>
     * 
     * @param s the original string
     * @return the modified header string
     */
    private String getValidHeaderString(String s) {
        return MimeUtility.quote(s, HeaderTokenizer.MIME);
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
        ((ProtocolForm) form).setDocument(getDocumentService().getByDocumentHeaderId(
                protocolSubmission.getProtocol().getProtocolDocument().getDocumentNumber()));
        ((ProtocolForm) form).initialize();
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
    public ActionForward assignToAgenda(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolTask task = new ProtocolTask(TaskName.ASSIGN_TO_AGENDA, protocolForm.getProtocolDocument().getProtocol());
        
        if (isAuthorized(task)) {
            ProtocolAssignToAgendaBean actionBean = protocolForm.getActionHelper().getAssignToAgendaBean();
            if (applyRules(new ProtocolAssignToAgendaEvent(protocolForm.getProtocolDocument(), actionBean))) {               
                getProtocolAssignToAgendaService().assignToAgenda(protocolForm.getProtocolDocument().getProtocol(), actionBean);
                getReviewerCommentsService().persistReviewerComments(actionBean.getReviewComments(), 
                        protocolForm.getProtocolDocument().getProtocol());
                protocolForm.getActionHelper().addReviewerCommentsToBean(protocolForm.getActionHelper().getProtocolApproveBean(), protocolForm);
                protocolForm.getActionHelper().addReviewerCommentsToBean(protocolForm.getActionHelper().getCommitteeDecision(), protocolForm);
            }
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward addAssignToAgendaReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolAssignToAgendaBean actionBean = protocolForm.getActionHelper().getAssignToAgendaBean();
        actionBean.setProtocolId(protocolForm.getProtocolDocument().getProtocol().getProtocolId());
        return addReviewComment(mapping, actionBean.getReviewComments(), protocolForm.getProtocolDocument());
    } 
    
    public ActionForward deleteAssignToAgendaReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolAssignToAgendaBean actionBean = protocolForm.getActionHelper().getAssignToAgendaBean();
        return deleteReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    public ActionForward moveUpAssignToAgendaReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolAssignToAgendaBean actionBean = protocolForm.getActionHelper().getAssignToAgendaBean();
        return moveUpReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    public ActionForward moveDownAssignToAgendaReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolAssignToAgendaBean actionBean = protocolForm.getActionHelper().getAssignToAgendaBean();
        return moveDownReviewComment(mapping, actionBean.getReviewComments(), request);
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
        ProtocolTask task = new ProtocolTask(TaskName.ASSIGN_TO_COMMITTEE_SCHEDULE, protocolForm.getProtocolDocument().getProtocol());
        if (isAuthorized(task)) {
            ProtocolAssignCmtSchedBean actionBean = protocolForm.getActionHelper().getAssignCmtSchedBean();
            if (applyRules(new ProtocolAssignCmtSchedEvent(protocolForm.getProtocolDocument(), actionBean))) {
                if (isCommitteeMeetingAssignedMaxProtocols(actionBean.getNewCommitteeId(), actionBean.getNewScheduleId())) {
                    return confirm(buildAssignToCmtSchedConfirmationQuestion(mapping, form, request, response),
                            CONFIRM_ASSIGN_CMT_SCHED_KEY, "");
                }
                getProtocolAssignCmtSchedService().assignToCommitteeAndSchedule(protocolForm.getProtocolDocument().getProtocol(), actionBean);
                protocolForm.getActionHelper().getAssignToAgendaBean().init();
            }
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /*
     * Builds the confirmation question to verify if the user wants to assign the protocol to the committee.
     */
    private StrutsConfirmation buildAssignToCmtSchedConfirmationQuestion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_ASSIGN_CMT_SCHED_KEY,
                KeyConstants.QUESTION_PROTOCOL_CONFIRM_SUBMIT_FOR_REVIEW);
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

    /**
     * Method dispatched from <code>{@link KraTransactionalDocumentActionBase#confirm(StrutsQuestion, String, String)}</code> for
     * when a "yes" condition is met.
     * 
     * @param mapping The mapping associated with this action.
     * @param form The Protocol form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the destination (always the original Protocol Document web page that caused this action to be invoked)
     * @throws Exception
     * @see KraTransactionalDocumentActionBase#confirm(StrutsQuestion, String, String)
     */
    public ActionForward confirmAssignCmtSched(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Object question = request.getParameter(QUESTION_INST_ATTRIBUTE_NAME);

        if (CONFIRM_ASSIGN_CMT_SCHED_KEY.equals(question)) {
            ProtocolForm protocolForm = (ProtocolForm) form;
            ProtocolAssignCmtSchedBean actionBean = protocolForm.getActionHelper().getAssignCmtSchedBean();
            getProtocolAssignCmtSchedService().assignToCommitteeAndSchedule(protocolForm.getProtocolDocument().getProtocol(), actionBean);
        }

        return mapping.findForward(MAPPING_BASIC);
    }
    
    public ActionForward confirmAssignToAgenda(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Object question = request.getParameter(QUESTION_INST_ATTRIBUTE_NAME);

        if (CONFIRM_ASSIGN_TO_AGENDA_KEY.equals(question)) {
            ProtocolForm protocolForm = (ProtocolForm) form;
            ProtocolAssignToAgendaBean actionBean = protocolForm.getActionHelper().getAssignToAgendaBean();
            getProtocolAssignToAgendaService().assignToAgenda(protocolForm.getProtocolDocument().getProtocol(), actionBean);
        }

        return mapping.findForward(MAPPING_BASIC);
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

        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolTask task = new ProtocolTask(TaskName.ASSIGN_REVIEWERS, protocolForm.getProtocolDocument().getProtocol());
        if (isAuthorized(task)) {
            ProtocolAssignReviewersBean actionBean = protocolForm.getActionHelper().getProtocolAssignReviewersBean();
            if (applyRules(new ProtocolAssignReviewersEvent(protocolForm.getProtocolDocument(), actionBean))) {
                getProtocolAssignReviewersService().assignReviewers(protocolForm.getProtocolDocument().getProtocol(), actionBean.getReviewers());
            }
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
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
    public ActionForward grantExemption(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGrantExemptionBean actionBean = protocolForm.getActionHelper().getProtocolGrantExemptionBean();
        getProtocolGrantExemptionService().grantExemption(protocolForm.getProtocolDocument().getProtocol(), actionBean);
        getReviewerCommentsService().persistReviewerComments(actionBean.getReviewComments(), 
                protocolForm.getProtocolDocument().getProtocol());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward irbAcknowledgement(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        IrbAcknowledgementBean actionBean = protocolForm.getActionHelper().getIrbAcknowledgementBean();
        getIrbAcknowledgementService().irbAcknowledgement(protocolForm.getProtocolDocument().getProtocol(), actionBean);
        getReviewerCommentsService().persistReviewerComments(actionBean.getReviewComments(), 
                protocolForm.getProtocolDocument().getProtocol());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    

    /**
     * Add a review comment to a grant exemption request.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addGrantExemptionReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGrantExemptionBean actionBean = protocolForm.getActionHelper().getProtocolGrantExemptionBean();
        actionBean.setProtocolId(protocolForm.getProtocolDocument().getProtocol().getProtocolId());
        return addReviewComment(mapping, actionBean.getReviewComments(), protocolForm.getProtocolDocument());
    }
        
    public ActionForward addIrbAcknowledgementReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        IrbAcknowledgementBean actionBean = protocolForm.getActionHelper().getIrbAcknowledgementBean();
        actionBean.setProtocolId(protocolForm.getProtocolDocument().getProtocol().getProtocolId());
        return addReviewComment(mapping, actionBean.getReviewComments(), protocolForm.getProtocolDocument());
    }

    /**
     * Delete a review comment from a grant exemption request.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteGrantExemptionReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGrantExemptionBean actionBean = protocolForm.getActionHelper().getProtocolGrantExemptionBean();
        return deleteReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    public ActionForward deleteIrbAcknowledgementReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        IrbAcknowledgementBean actionBean = protocolForm.getActionHelper().getIrbAcknowledgementBean();
        return deleteReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    /**
     * Move a review comment up one in a grant exemption request.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward moveUpGrantExemptionReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGrantExemptionBean actionBean = protocolForm.getActionHelper().getProtocolGrantExemptionBean();
        return moveUpReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    public ActionForward moveUpIrbAcknowledgementReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        IrbAcknowledgementBean actionBean = protocolForm.getActionHelper().getIrbAcknowledgementBean();
        return moveUpReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    /**
     * Move a review comment down one in a grant exemption request.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward moveDownGrantExemptionReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGrantExemptionBean actionBean = protocolForm.getActionHelper().getProtocolGrantExemptionBean();
        return moveDownReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    public ActionForward moveDownIrbAcknowledgementReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        IrbAcknowledgementBean actionBean = protocolForm.getActionHelper().getIrbAcknowledgementBean();
        return moveDownReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    /**
     * Expedite Approval.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward expediteApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolApproveBean actionBean = protocolForm.getActionHelper().getProtocolExpediteApprovalBean();
        getProtocolExpediteApprovalService().grantExpeditedApproval(protocolForm.getProtocolDocument().getProtocol(), actionBean);
        getReviewerCommentsService().persistReviewerComments(actionBean.getReviewComments(), 
                protocolForm.getProtocolDocument().getProtocol());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Add a review comment to a grant exemption request.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addExpediteApprovalReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolApproveBean actionBean = protocolForm.getActionHelper().getProtocolExpediteApprovalBean();
        actionBean.setProtocolId(protocolForm.getProtocolDocument().getProtocol().getProtocolId());
        return addReviewComment(mapping, actionBean.getReviewComments(), protocolForm.getProtocolDocument());
    }
    
    /**
     * Delete a review comment from a grant exemption request.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteExpediteApprovalReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolApproveBean actionBean = protocolForm.getActionHelper().getProtocolExpediteApprovalBean();
        return deleteReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    /**
     * Move a review comment up one in a grant exemption request.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward moveUpExpediteApprovalReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolApproveBean actionBean = protocolForm.getActionHelper().getProtocolExpediteApprovalBean();
        return moveUpReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    /**
     * Move a review comment down one in a grant exemption request.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward moveDownExpediteApprovalReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolApproveBean actionBean = protocolForm.getActionHelper().getProtocolExpediteApprovalBean();
        return moveDownReviewComment(mapping, actionBean.getReviewComments(), request);
    }    
    
    @Override
    public ActionForward approve(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        if (hasPermission(TaskName.APPROVE_PROTOCOL, protocolForm.getProtocolDocument().getProtocol())) {
            if (applyRules(new ProtocolApproveEvent(protocolForm.getProtocolDocument(), protocolForm.getActionHelper()
                    .getProtocolApproveBean()))) {
                ProtocolApproveBean actionBean = protocolForm.getActionHelper().getProtocolApproveBean();
                getProtocolApproveService().approve(protocolForm.getProtocolDocument(), actionBean);
    
                getReviewerCommentsService().persistReviewerComments(actionBean.getReviewComments(), 
                        protocolForm.getProtocolDocument().getProtocol());
            }
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward addApproveReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolApproveBean actionBean = protocolForm.getActionHelper().getProtocolApproveBean();
        actionBean.setProtocolId(protocolForm.getProtocolDocument().getProtocol().getProtocolId());
        return addReviewComment(mapping, actionBean.getReviewComments(), protocolForm.getProtocolDocument());
    }
    
    public ActionForward deleteApproveReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolApproveBean actionBean = protocolForm.getActionHelper().getProtocolApproveBean();
        return deleteReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    public ActionForward moveUpApproveReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolApproveBean actionBean = protocolForm.getActionHelper().getProtocolApproveBean();
        return moveUpReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    public ActionForward moveDownApproveReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolApproveBean actionBean = protocolForm.getActionHelper().getProtocolApproveBean();
        return moveDownReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    public ActionForward defer(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        if (hasPermission(TaskName.DEFER_PROTOCOL, protocolForm.getProtocolDocument().getProtocol())) {
            ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolDeferBean();
            ProtocolDocument newDocument = getProtocolDeferService().defer(protocolForm.getProtocolDocument().getProtocol(), actionBean);
            getReviewerCommentsService().persistReviewerComments(actionBean.getReviewComments(), 
                    protocolForm.getProtocolDocument().getProtocol());
            
            if(!StringUtils.equals(protocolForm.getProtocolDocument().getDocumentNumber(), newDocument.getDocumentNumber())) {
                protocolForm.setDocId(newDocument.getDocumentNumber());
                loadDocument(protocolForm);
                protocolForm.getProtocolHelper().prepareView();
                return mapping.findForward(PROTOCOL_TAB);
            }
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward addDeferReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolDeferBean();
        actionBean.setProtocolId(protocolForm.getProtocolDocument().getProtocol().getProtocolId());
        return addReviewComment(mapping, actionBean.getReviewComments(), protocolForm.getProtocolDocument());
    }
    
    public ActionForward deleteDeferReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolDeferBean();
        return deleteReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    public ActionForward moveUpDeferReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolDeferBean();
        return moveUpReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    public ActionForward moveDownDeferReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolDeferBean();
        return moveDownReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    public ActionForward reopen(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        
        if (hasGenericPermission(GenericProtocolAuthorizer.REOPEN_PROTOCOL, protocolForm.getProtocolDocument().getProtocol())) {
        
            ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolReopenBean();
            getProtocolGenericActionService().reopen(protocolForm.getProtocolDocument().getProtocol(), actionBean);
            
            getReviewerCommentsService().persistReviewerComments(actionBean.getReviewComments(), 
                    protocolForm.getProtocolDocument().getProtocol());
            
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward addReopenReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolReopenBean();
        actionBean.setProtocolId(protocolForm.getProtocolDocument().getProtocol().getProtocolId());
        return addReviewComment(mapping, actionBean.getReviewComments(), protocolForm.getProtocolDocument());
    }
    
    public ActionForward deleteReopenReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form; 
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolReopenBean();
        return deleteReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    public ActionForward moveUpReopenReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolReopenBean();
        return moveUpReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    public ActionForward moveDownReopenReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolReopenBean();
        return moveDownReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    public ActionForward closeEnrollment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        
        if (hasGenericPermission(GenericProtocolAuthorizer.CLOSE_ENROLLMENT_PROTOCOL, protocolForm.getProtocolDocument().getProtocol())) {
        
            ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolCloseEnrollmentBean();
            getProtocolGenericActionService().closeEnrollment(protocolForm.getProtocolDocument().getProtocol(), actionBean);
            
            getReviewerCommentsService().persistReviewerComments(actionBean.getReviewComments(), 
                    protocolForm.getProtocolDocument().getProtocol());
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward addCloseEnrollmentReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolCloseEnrollmentBean();
        actionBean.setProtocolId(protocolForm.getProtocolDocument().getProtocol().getProtocolId());
        return addReviewComment(mapping, actionBean.getReviewComments(), protocolForm.getProtocolDocument());
    }
    
    public ActionForward deleteCloseEnrollmentReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolCloseEnrollmentBean();
        return deleteReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    public ActionForward moveUpCloseEnrollmentReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolCloseEnrollmentBean();
        return moveUpReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    public ActionForward moveDownCloseEnrollmentReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolCloseEnrollmentBean();
        return moveDownReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    public ActionForward suspend(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        
        if (hasGenericPermission(GenericProtocolAuthorizer.SUSPEND_PROTOCOL, protocolForm.getProtocolDocument().getProtocol())) {
        
            ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolSuspendBean();
            getProtocolGenericActionService().suspend(protocolForm.getProtocolDocument().getProtocol(), actionBean);
            
            getReviewerCommentsService().persistReviewerComments(actionBean.getReviewComments(), 
                    protocolForm.getProtocolDocument().getProtocol());
        }
        
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward addSuspendReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolSuspendBean();
        actionBean.setProtocolId(protocolForm.getProtocolDocument().getProtocol().getProtocolId());
        return addReviewComment(mapping, actionBean.getReviewComments(), protocolForm.getProtocolDocument());
    }
    
    public ActionForward deleteSuspendReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolSuspendBean();
        return deleteReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    public ActionForward moveUpSuspendReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolSuspendBean();
        return moveUpReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    public ActionForward moveDownSuspendReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolSuspendBean();
        return moveDownReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    public ActionForward suspendByDmsb(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        
        if (hasGenericPermission(GenericProtocolAuthorizer.SUSPEND_PROTOCOL_BY_DSMB, protocolForm.getProtocolDocument().getProtocol())) {
        
            ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolSuspendByDmsbBean();
            getProtocolGenericActionService().suspendByDsmb(protocolForm.getProtocolDocument().getProtocol(), actionBean);
            
            getReviewerCommentsService().persistReviewerComments(actionBean.getReviewComments(), 
                    protocolForm.getProtocolDocument().getProtocol());
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward addSuspendByDmsbReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolSuspendByDmsbBean();
        actionBean.setProtocolId(protocolForm.getProtocolDocument().getProtocol().getProtocolId());
        return addReviewComment(mapping, actionBean.getReviewComments(), protocolForm.getProtocolDocument());
    }
    
    public ActionForward deleteSuspendByDmsbReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolSuspendByDmsbBean();
        return deleteReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    public ActionForward moveUpSuspendByDmsbReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolSuspendByDmsbBean();
        return moveUpReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    public ActionForward moveDownSuspendByDmsbReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolSuspendByDmsbBean();
        return moveDownReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    @Override
    public ActionForward close(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        if (hasGenericPermission(GenericProtocolAuthorizer.CLOSE_PROTOCOL, protocolForm.getProtocolDocument().getProtocol())) {
            ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolCloseBean();
            getProtocolGenericActionService().close(protocolForm.getProtocolDocument().getProtocol(), actionBean);
            
            getReviewerCommentsService().persistReviewerComments(actionBean.getReviewComments(), 
                    protocolForm.getProtocolDocument().getProtocol());
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward addCloseReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolCloseBean();
        actionBean.setProtocolId(protocolForm.getProtocolDocument().getProtocol().getProtocolId());
        return addReviewComment(mapping, actionBean.getReviewComments(), protocolForm.getProtocolDocument());
    }
    
    public ActionForward deleteCloseReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolCloseBean();
        return deleteReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    public ActionForward moveUpCloseReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolCloseBean();
        return moveUpReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    public ActionForward moveDownCloseReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolCloseBean();
        return moveDownReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    public ActionForward expire(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        if (hasGenericPermission(GenericProtocolAuthorizer.EXPIRE_PROTOCOL, protocolForm.getProtocolDocument().getProtocol())) {
            ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolExpireBean();
            getProtocolGenericActionService().expire(protocolForm.getProtocolDocument().getProtocol(), actionBean);
            
            getReviewerCommentsService().persistReviewerComments(actionBean.getReviewComments(), 
                    protocolForm.getProtocolDocument().getProtocol());
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward addExpireReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolExpireBean();
        actionBean.setProtocolId(protocolForm.getProtocolDocument().getProtocol().getProtocolId());
        return addReviewComment(mapping, actionBean.getReviewComments(), protocolForm.getProtocolDocument());
    }
    
    public ActionForward deleteExpireReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolExpireBean();
        return deleteReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    public ActionForward moveUpExpireReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolExpireBean();
        return moveUpReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    public ActionForward moveDownExpireReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolExpireBean();
        return moveDownReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    public ActionForward terminate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        if (hasGenericPermission(GenericProtocolAuthorizer.TERMINATE_PROTOCOL, protocolForm.getProtocolDocument().getProtocol())) {
            ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolTerminateBean();
            getProtocolGenericActionService().terminate(protocolForm.getProtocolDocument().getProtocol(), actionBean);
            
            getReviewerCommentsService().persistReviewerComments(actionBean.getReviewComments(), 
                    protocolForm.getProtocolDocument().getProtocol());
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward addTerminateReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolTerminateBean();
        actionBean.setProtocolId(protocolForm.getProtocolDocument().getProtocol().getProtocolId());
        return addReviewComment(mapping, actionBean.getReviewComments(), protocolForm.getProtocolDocument());
    }
    
    public ActionForward deleteTerminateReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolTerminateBean();
        return deleteReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    public ActionForward moveUpTerminateReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolTerminateBean();
        return moveUpReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    public ActionForward moveDownTerminateReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolTerminateBean();
        return moveDownReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    public ActionForward permitDataAnalysis(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        if (hasGenericPermission(GenericProtocolAuthorizer.PERMIT_DATA_ANALYSIS, protocolForm.getProtocolDocument().getProtocol())) {
            ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolPermitDataAnalysisBean();
            getProtocolGenericActionService().permitDataAnalysis(protocolForm.getProtocolDocument().getProtocol(), actionBean);
            
            getReviewerCommentsService().persistReviewerComments(actionBean.getReviewComments(), 
                    protocolForm.getProtocolDocument().getProtocol());
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward addPermitDataAnalysisReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolPermitDataAnalysisBean();
        actionBean.setProtocolId(protocolForm.getProtocolDocument().getProtocol().getProtocolId());
        return addReviewComment(mapping, actionBean.getReviewComments(), protocolForm.getProtocolDocument());
    }
    
    public ActionForward deletePermitDataAnalysisReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolPermitDataAnalysisBean();
        return deleteReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    public ActionForward moveUpPermitDataAnalysisReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolPermitDataAnalysisBean();
        return moveUpReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    public ActionForward moveDownPermitDataAnalysisReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolGenericActionBean actionBean = protocolForm.getActionHelper().getProtocolPermitDataAnalysisBean();
        return moveDownReviewComment(mapping, actionBean.getReviewComments(), request);
    }
    
    /**
     * Adds a Risk Level to a protocol in an Approval action.
     * 
     * @param mapping Struts action mapping
     * @param form Form associated with this action
     * @param request Raw HTTP Request
     * @param response Raw HTTP Response
     * @return The mapping for the next page
     * @throws Exception
     */
    public ActionForward addApproveRiskLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ActionHelper actionHelper = protocolForm.getActionHelper();
        
        addRiskLevel(actionHelper.getProtocolApproveBean().getProtocolRiskLevelBean(), protocolForm.getDocument(), actionHelper.getProtocol(), 
                Constants.PROTOCOL_APPROVAL_ENTER_RISK_LEVEL_KEY);
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Adds a Risk Level to a protocol in an Expedited Approval action.
     * 
     * @param mapping Struts action mapping
     * @param form Form associated with this action
     * @param request Raw HTTP Request
     * @param response Raw HTTP Response
     * @return The mapping for the next page
     * @throws Exception
     */
    public ActionForward addExpediteApprovalRiskLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ActionHelper actionHelper = protocolForm.getActionHelper();
        
        addRiskLevel(actionHelper.getProtocolExpediteApprovalBean().getProtocolRiskLevelBean(), protocolForm.getProtocolDocument(), actionHelper.getProtocol(), 
                Constants.PROTOCOL_EXPEDITED_APPROVAL_ENTER_RISK_LEVEL_KEY);
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /*
     * Applies add rules to all risk levels from both types of approval actions
     */
    private void addRiskLevel(ProtocolRiskLevelBean protocolRiskLevelBean, ProtocolDocument document, Protocol protocol, String errorPropertyName) {
        if (applyRules(new ProtocolAddRiskLevelEvent(document, errorPropertyName, protocolRiskLevelBean.getNewProtocolRiskLevel()))) {
            protocolRiskLevelBean.addNewProtocolRiskLevel(protocol);
        }
    }
    
    /**
     * Updates a persisted Risk Level in a protocol for an approval action, 
     * moving the persisted risk level to Inactive status and adding a new Active status risk level.
     * 
     * @param mapping Struts action mapping
     * @param form Form associated with this action
     * @param request Raw HTTP Request
     * @param response Raw HTTP Response
     * @return The mapping for the next page
     * @throws Exception
     */
    public ActionForward updateApproveRiskLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ActionHelper actionHelper = protocolForm.getActionHelper();

        updateRiskLevel(actionHelper.getProtocolApproveBean().getProtocolRiskLevelBean(), protocolForm.getProtocolDocument(), 
                actionHelper.getProtocol(), getSelectedLine(request));
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Updates a persisted Risk Level in a protocol for an expedited approval action, 
     * moving the persisted risk level to Inactive status and adding a new Active status risk level.
     * 
     * @param mapping Struts action mapping
     * @param form Form associated with this action
     * @param request Raw HTTP Request
     * @param response Raw HTTP Response
     * @return The mapping for the next page
     * @throws Exception
     */
    public ActionForward updateExpediteApprovalRiskLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ActionHelper actionHelper = protocolForm.getActionHelper();

        updateRiskLevel(actionHelper.getProtocolExpediteApprovalBean().getProtocolRiskLevelBean(), protocolForm.getProtocolDocument(), 
                actionHelper.getProtocol(), getSelectedLine(request));
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /*
     * Applies update rules to all risk levels from both types of approval actions
     */
    private void updateRiskLevel(ProtocolRiskLevelBean protocolRiskLevelBean, ProtocolDocument document, Protocol protocol, int lineNumber) {
        if (applyRules(new ProtocolUpdateRiskLevelEvent(document, Constants.PROTOCOL_UPDATE_RISK_LEVEL_KEY, lineNumber))) {
            protocolRiskLevelBean.updateProtocolRiskLevel(protocol.getProtocolRiskLevels().get(lineNumber));
        }
    }
    
    /**
     * Deletes a Risk Level from a protocol in an Approval action.
     * 
     * @param mapping Struts action mapping
     * @param form Form associated with this action
     * @param request Raw HTTP Request
     * @param response Raw HTTP Response
     * @return The mapping for the next page
     * @throws Exception
     */
    public ActionForward deleteApproveRiskLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ActionHelper actionHelper = protocolForm.getActionHelper();
        
        deleteRiskLevel(actionHelper.getProtocolApproveBean().getProtocolRiskLevelBean(), actionHelper.getProtocol(), getLineToDelete(request));
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Deletes a Risk Level from a protocol in an Expedited Approval action.
     * 
     * @param mapping Struts action mapping
     * @param form Form associated with this action
     * @param request Raw HTTP Request
     * @param response Raw HTTP Response
     * @return The mapping for the next page
     * @throws Exception
     */
    public ActionForward deleteExpediteApprovalRiskLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ActionHelper actionHelper = protocolForm.getActionHelper();
        
        deleteRiskLevel(actionHelper.getProtocolExpediteApprovalBean().getProtocolRiskLevelBean(), actionHelper.getProtocol(), getLineToDelete(request));
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /*
     * Deletes the risk level from the protocol for both types of approval actions
     */
    private void deleteRiskLevel(ProtocolRiskLevelBean protocolRiskLevelBean, Protocol protocol, int lineNumber) {
        protocolRiskLevelBean.deleteProtocolRiskLevel(protocol, lineNumber);
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
        ActionForward forward = mapping.findForward(MAPPING_BASIC);
        
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_ADMIN_CORRECTION, protocolForm.getProtocolDocument().getProtocol());
        if (isAuthorized(task)) {
            if (applyRules(new ProtocolAdminCorrectionEvent(protocolForm.getProtocolDocument(), protocolForm.getActionHelper()
                        .getProtocolAdminCorrectionBean()))) {
                protocolForm.getProtocolDocument().getProtocol().setCorrectionMode(true); 
                protocolForm.getProtocolHelper().prepareView();
                return mapping.findForward(PROTOCOL_TAB);
            }
        }
        
        return forward;  
    }

    public ActionForward submitAdminCorrection(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument protocolDocument = protocolForm.getProtocolDocument();
        protocolDocument.getProtocol().setCorrectionMode(false); 
        AdminCorrectionBean adminCorrectionBean = protocolForm.getActionHelper().getProtocolAdminCorrectionBean();
        protocolForm.getProtocolDocument().updateProtocolStatus(ProtocolActionType.ADMINISTRATIVE_CORRECTION, adminCorrectionBean.getComments());
         
        AdminCorrectionService adminCorrectionService = KraServiceLocator.getService(AdminCorrectionService.class);
        adminCorrectionService.sendCorrectionNotification(protocolDocument.getProtocol(), adminCorrectionBean);
        
        return mapping.findForward(Constants.MAPPING_BASIC);  
    }
    
    public ActionForward undoLastAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument protocolDocument = protocolForm.getProtocolDocument();
        UndoLastActionBean undoLastActionBean = protocolForm.getActionHelper().getUndoLastActionBean();
        UndoLastActionService undoLastActionService = KraServiceLocator.getService(UndoLastActionService.class);
        ProtocolDocument updatedDocument = undoLastActionService.undoLastAction(protocolDocument, undoLastActionBean);  
        
        if(!updatedDocument.getDocumentNumber().equals(protocolForm.getDocId())) { 
            protocolForm.setDocId(updatedDocument.getDocumentNumber());
            loadDocument(protocolForm);
            protocolForm.getProtocolHelper().prepareView();
            return mapping.findForward(PROTOCOL_TAB);
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward submitCommitteeDecision(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        if (applyRules(new CommitteeDecisionEvent(protocolForm.getProtocolDocument(), protocolForm.getActionHelper().getCommitteeDecision()))){
            ProtocolDocument newDocument = getCommitteeDecisionService().processCommitteeDecision(protocolForm.getProtocolDocument().getProtocol(), 
                                                               protocolForm.getActionHelper().getCommitteeDecision());
            getReviewerCommentsService().persistReviewerComments(
                    protocolForm.getActionHelper().getCommitteeDecision().getReviewComments(), 
                    protocolForm.getProtocolDocument().getProtocol());
            
            if(!StringUtils.equals(protocolForm.getProtocolDocument().getDocumentNumber(), newDocument.getDocumentNumber())) {
                protocolForm.setDocId(newDocument.getDocumentNumber());
                loadDocument(protocolForm);
                protocolForm.getProtocolHelper().prepareView();
                return mapping.findForward(PROTOCOL_TAB);
            }
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward addCommitteeDecisionReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        CommitteeDecision committeeDecision = protocolForm.getActionHelper().getCommitteeDecision();
        committeeDecision.setProtocolId(protocolForm.getProtocolDocument().getProtocol().getProtocolId());
        return addReviewComment(mapping, committeeDecision.getReviewComments(), protocolForm.getProtocolDocument());
    }
    
    public ActionForward deleteCommitteeDecisionReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;        
        return deleteReviewComment(mapping, protocolForm.getActionHelper().getCommitteeDecision().getReviewComments(), request);
    }
    
    public ActionForward moveUpCommitteeDecisionReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        return moveUpReviewComment(mapping, protocolForm.getActionHelper().getCommitteeDecision().getReviewComments(), request);
    }
    
    public ActionForward moveDownCommitteeDecisionReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;        
        return moveDownReviewComment(mapping, protocolForm.getActionHelper().getCommitteeDecision().getReviewComments(), request);
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
        ProtocolModifySubmissionAction bean = protocolForm.getActionHelper().getProtocolModifySubmissionAction();
        if (applyRules(new ProtocolModifySubmissionEvent(protocolForm.getProtocolDocument(), protocolForm.getActionHelper()
                .getProtocolModifySubmissionAction()))) {
            KraServiceLocator.getService(ProtocolModifySubmissionService.class).modifySubmisison(protocolForm.getProtocolDocument(), bean);
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
    
    private List<Printable> getPrintableArtifacts(ProtocolDocument document, ProtocolPrintType protocolPrintType) {

        Printable printable = getProtocolPrintingService().getProtocolPrintable(protocolPrintType);
        ((AbstractPrint) printable).setDocument(document);
        List<Printable> printableArtifactList = new ArrayList<Printable>();
        printableArtifactList.add(printable);
        return printableArtifactList;
    }

    private ProtocolPrintingService getProtocolPrintingService() {
        return KraServiceLocator.getService(ProtocolPrintingService.class);
    }

    private ActionForward addReviewComment(ActionMapping mapping, ReviewerComments comments, ProtocolDocument protocolDocument) throws Exception {
        comments.addNewComment(protocolDocument.getProtocol());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    private ActionForward deleteReviewComment(ActionMapping mapping, ReviewerComments comments, HttpServletRequest request) throws Exception {
        comments.deleteComment(getLineToDelete(request));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    private ActionForward moveUpReviewComment(ActionMapping mapping, ReviewerComments comments, HttpServletRequest request) throws Exception {
        int lineToDelete = getLineToDelete(request); 
        comments.moveUp(lineToDelete, lineToDelete);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    private ActionForward moveDownReviewComment(ActionMapping mapping, ReviewerComments comments, HttpServletRequest request) throws Exception {
        int lineToDelete = getLineToDelete(request);
        comments.moveDown(lineToDelete, lineToDelete);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    private boolean hasPermission(String taskName, Protocol protocol) {
        ProtocolTask task = new ProtocolTask(taskName, protocol);
        return getTaskAuthorizationService().isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), task);
    }
    
    private boolean hasGenericPermission(String genericActionName, Protocol protocol) {
        ProtocolTask task = new ProtocolTask(TaskName.GENERIC_PROTOCOL_ACTION, protocol, genericActionName);
        return getTaskAuthorizationService().isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), task);
    }
    
    /**
     * 
     * This method is a nice tool to print off everything in the request object.  Great for debugging.
     * @param request
     */
    private void debugPrintRequest(HttpServletRequest request) {
        java.util.Iterator keys = request.getParameterMap().keySet().iterator();
        String newLine = "\n";
        StringBuffer sb = new StringBuffer("************************************************").append(newLine);
        while(keys.hasNext()) {
            String key = (String)keys.next();
            sb.append(key).append(" : ");
            try {
                sb.append(request.getParameterMap().get(key).toString());
            } catch(Exception e) {
                sb.append(e.getMessage());
            }
            sb.append(newLine);
        }
        sb.append("****************************").append(newLine);
        System.err.println(sb.toString());
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
    
    private IrbAcknowledgementService getIrbAcknowledgementService() {
        return KraServiceLocator.getService(IrbAcknowledgementService.class);
    }
    
    private ProtocolExpediteApprovalService getProtocolExpediteApprovalService() {
        return KraServiceLocator.getService(ProtocolExpediteApprovalService.class);
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
    
    private ReviewerCommentsService getReviewerCommentsService() {
        return KraServiceLocator.getService(ReviewerCommentsService.class);
    }
    
    private ProtocolDeferService getProtocolDeferService() {
        return KraServiceLocator.getService(ProtocolDeferService.class);
    }

}