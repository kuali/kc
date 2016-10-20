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
package org.kuali.kra.irb.actions;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.kuali.coeus.common.committee.impl.meeting.CommitteeScheduleMinuteBase;
import org.kuali.coeus.common.committee.impl.meeting.MinuteEntryType;
import org.kuali.coeus.common.framework.attachment.AttachmentFile;
import org.kuali.coeus.common.framework.auth.task.ApplicationTask;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.common.framework.print.*;
import org.kuali.coeus.common.framework.print.util.PrintingUtils;
import org.kuali.coeus.common.framework.print.watermark.WatermarkConstants;
import org.kuali.coeus.common.framework.print.watermark.WatermarkService;
import org.kuali.coeus.common.notification.impl.bo.NotificationType;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
import org.kuali.coeus.sys.framework.controller.KcHoldingPageConstants;
import org.kuali.coeus.sys.framework.validation.AuditHelper;
import org.kuali.coeus.sys.framework.controller.StrutsConfirmation;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolAction;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.actions.amendrenew.ModifyAmendmentSectionsEvent;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewService;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendmentBean;
import org.kuali.kra.irb.actions.assignagenda.ProtocolAssignToAgendaBean;
import org.kuali.kra.irb.actions.assignagenda.ProtocolAssignToAgendaService;
import org.kuali.kra.irb.actions.assigncmtsched.ProtocolAssignCmtSchedBean;
import org.kuali.kra.irb.actions.assigncmtsched.ProtocolAssignCmtSchedService;
import org.kuali.kra.irb.actions.copy.ProtocolCopyService;
import org.kuali.kra.irb.actions.correspondence.ProtocolActionCorrespondenceGenerationService;
import org.kuali.kra.irb.actions.correspondence.ProtocolActionsCorrespondence;
import org.kuali.kra.irb.actions.decision.CommitteeDecision;
import org.kuali.kra.irb.actions.decision.CommitteeDecisionAbstainerEvent;
import org.kuali.kra.irb.actions.decision.CommitteeDecisionRecuserEvent;
import org.kuali.kra.irb.actions.decision.CommitteePerson;
import org.kuali.kra.irb.actions.delete.ProtocolDeleteService;
import org.kuali.kra.irb.actions.followup.FollowupActionService;
import org.kuali.kra.irb.actions.genericactions.ProtocolGenericActionBean;
import org.kuali.kra.irb.actions.history.ProtocolHistoryFilterDatesEvent;
import org.kuali.kra.irb.actions.modifysubmission.ProtocolModifySubmissionBean;
import org.kuali.kra.irb.actions.modifysubmission.ProtocolModifySubmissionEvent;
import org.kuali.kra.irb.actions.modifysubmission.ProtocolModifySubmissionService;
import org.kuali.kra.irb.actions.notification.ProtocolNotificationRequestBean;
import org.kuali.kra.irb.actions.notifyirb.ProtocolNotifyIrbBean;
import org.kuali.kra.irb.actions.print.ProtocolActionPrintEvent;
import org.kuali.kra.irb.actions.print.ProtocolPrintType;
import org.kuali.kra.irb.actions.print.ProtocolPrintingService;
import org.kuali.kra.irb.actions.request.ProtocolRequestBean;
import org.kuali.kra.irb.actions.reviewcomments.*;
import org.kuali.kra.irb.actions.risklevel.*;
import org.kuali.kra.irb.actions.submit.*;
import org.kuali.kra.irb.actions.undo.UndoLastActionBean;
import org.kuali.kra.irb.actions.undo.UndoLastActionService;
import org.kuali.kra.irb.auth.ProtocolTask;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondence;
import org.kuali.kra.irb.infrastructure.IrbConstants;
import org.kuali.kra.irb.noteattachment.*;
import org.kuali.kra.irb.notification.IRBNotificationContext;
import org.kuali.kra.irb.notification.IRBNotificationRenderer;
import org.kuali.kra.irb.onlinereview.ProtocolReviewAttachment;
import org.kuali.kra.irb.questionnaire.print.IrbCorrespondencePrintingService;
import org.kuali.kra.irb.summary.ProtocolSummary;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolFormBase;
import org.kuali.kra.protocol.actions.ProtocolOnlineReviewCommentable;
import org.kuali.kra.protocol.actions.ProtocolSubmissionDocBase;
import org.kuali.kra.protocol.actions.notify.ProtocolActionAttachment;
import org.kuali.kra.protocol.actions.print.ProtocolSummaryPrintOptions;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentBase;
import org.kuali.kra.protocol.noteattachment.ProtocolNotepadBase;
import org.kuali.kra.protocol.summary.AttachmentSummary;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.kns.document.authorization.DocumentAuthorizerBase;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.struts.action.AuditModeAction;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The set of actions for the Protocol Actions tab.
 */
@SuppressWarnings("deprecation")
public class ProtocolProtocolActionsAction extends ProtocolAction implements AuditModeAction {

    private static final Log LOG = LogFactory.getLog(ProtocolProtocolActionsAction.class);
    private static final String CONFIRM_NO_ACTION = "";
    private static final String CONFIRM_DELETE_ACTION_ATT = "confirmDeleteActionAttachment";
    private static final String CONFIRM_FOLLOWUP_ACTION = "confirmAddFollowupAction";
    
    private static final String CONFIRM_SUBMIT_FOR_REVIEW_KEY = "confirmSubmitForReview";
    private static final String CONFIRM_ASSIGN_TO_AGENDA_KEY = "confirmAssignToAgenda";
    private static final String CONFIRM_ASSIGN_CMT_SCHED_KEY = "confirmAssignCmtSched";
    private static final String CONIFRM_REMOVE_REVIEWER_KEY="confirmRemoveReviewer";
     
    private static final String PERSON_NOT_FOUND_FORMAT_STRING = "%s (not found)";
    
    private static final String NOT_FOUND_SELECTION = "The attachment was not found for selection ";

    private static final String CONFIRM_DELETE_PROTOCOL_KEY = "confirmDeleteProtocol";
    private static final String INVALID_ATTACHMENT = "this attachment version is invalid ";

    /** signifies that a response has already be handled therefore forwarding to obtain a response is not needed. */
    private static final ActionForward RESPONSE_ALREADY_HANDLED = null;
    private static final String SUBMISSION_ID = "submissionId";
    private static final String CORRESPONDENCE = "correspondence";
    
    private static final Map<String, String> PRINTTAG_MAP = new HashMap<String, String>() {
        {
            put("summary", "PROTOCOL_SUMMARY_VIEW_REPORT");
            put("full", "PROTOCOL_FULL_PROTOCOL_REPORT");
            put("history", "PROTOCOL_PROTOCOL_HISTORY_REPORT");
            put("comments", "PROTOCOL_REVIEW_COMMENTS_REPORT");
    }};


    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
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
        // submit action may change "submission details", so re-initializa it
        
        if ("close".equals(protocolForm.getMethodToCall()) || protocolForm.getMethodToCall() == null) {
            // If we're closing, we can just leave right here.
            return mapping.findForward(KRADConstants.MAPPING_PORTAL);
        }
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
            final ProtocolDocument copy = (ProtocolDocument) getDocumentService().saveDocument(getProtocolCopyService().copyProtocol(protocolForm.getProtocolDocument()));

            // Switch over to the new protocol document and
            // go to the Protocol tab web page.

            protocolForm.setDocId(copy.getDocumentNumber());
            protocolForm.setViewOnly(false);
            loadDocument(protocolForm);
            protocolForm.getProtocolDocument().setViewOnly(protocolForm.isViewOnly());
            protocolForm.getActionHelper().setCurrentSubmissionNumber(-1);
            protocolForm.getProtocolHelper().prepareView();
            protocolForm.getActionHelper().prepareCommentsView();

            return mapping.findForward(IrbConstants.PROTOCOL_TAB);
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    @Override
    public ActionForward activate(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        return KcServiceLocator.getService(AuditHelper.class).setAuditMode(mapping, (ProtocolForm) form, true);
    }

    @Override
    public ActionForward deactivate(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        return KcServiceLocator.getService(AuditHelper.class).setAuditMode(mapping, (ProtocolForm) form, false);
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
        ProtocolDocument protocolDocument = (ProtocolDocument) protocolForm.getProtocolDocument();
        protocolForm.setAuditActivated(true);
        ProtocolTask task = new ProtocolTask(TaskName.SUBMIT_PROTOCOL, (Protocol) protocolDocument.getProtocol());
        if (isAuthorized(task)) {
            ProtocolSubmitAction submitAction = (ProtocolSubmitAction) protocolForm.getActionHelper().getProtocolSubmitAction();            
            if (applyRules(protocolForm,new ProtocolSubmitActionEvent(protocolDocument, submitAction))) {
                AuditHelper auditHelper = KcServiceLocator.getService(AuditHelper.class);
                if (auditHelper.auditUnconditionally(protocolDocument)) {
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
                int currentSubmissionCount = (schedule.getLatestProtocolSubmissions() == null) ? 0 : activeSubmissonCount(schedule.getLatestProtocolSubmissions());
                int maxSubmissionCount = schedule.getMaxProtocols();
                isMax = currentSubmissionCount >= maxSubmissionCount;
            }
        }
        
        return isMax;
    }

    private int activeSubmissonCount(List<ProtocolSubmissionLite> submissions) {
        int count = 0;
        for (ProtocolSubmissionLite submission : submissions) {
            if (submission.isProtocolActive()) {
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
     * Method dispatched from org.kuali.coeus.sys.framework.controller.KcTransactionalDocumentActionBase for
     * when a "yes" condition is met.
     * 
     * @param mapping The mapping associated with this action.
     * @param form The Protocol form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the destination
     * @throws Exception
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
        super.route(mapping, protocolForm, request, response);
        boolean isPromptToNotifyUser = getProtocolActionRequestService().submitForReviewAndPromptToNotifyUser(protocolForm, Boolean.TRUE);
        if (isPromptToNotifyUser) {
            return mapping.findForward(IrbConstants.PROTOCOL_NOTIFICATION_EDITOR);
        } else {             
            return routeProtocolToHoldingPage(mapping, protocolForm);
        }
    }

    private ActionForward routeProtocolToHoldingPage(ActionMapping mapping, ProtocolForm protocolForm) {
        String routeHeaderId = protocolForm.getProtocolDocument().getDocumentNumber();
        String returnLocation = buildActionUrl(routeHeaderId, Constants.MAPPING_PROTOCOL_ACTIONS, "ProtocolDocument");
        
        ActionForward basicForward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
        ActionRedirect holdingPageForward = new ActionRedirect(mapping.findForward(KcHoldingPageConstants.MAPPING_HOLDING_PAGE));
        holdingPageForward.addParameter(KcHoldingPageConstants.HOLDING_PAGE_DOCUMENT_ID, routeHeaderId);
        return routeToHoldingPage(basicForward, basicForward, holdingPageForward, returnLocation, routeHeaderId);

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
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        ProtocolForm protocolForm = (ProtocolForm) form;
        if(getProtocolActionRequestService().isWithdrawProtocolAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().withdrawProtocol(protocolForm);
            forward = mapping.findForward(forwardTo);
            loadDocument(protocolForm);
            protocolForm.getProtocolHelper().prepareView();
        }
        return forward;
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
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        ProtocolForm protocolForm = (ProtocolForm) form;
        String forwardTo = getProtocolActionRequestService().notifyIrbProtocol(protocolForm);
        forward = mapping.findForward(forwardTo);
        loadDocument(protocolForm);
        protocolForm.getProtocolHelper().prepareView();
        return forward;
    }

    public ActionForward addSubmissionDoc(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
        Protocol protocol = ((ProtocolForm) form).getActionHelper().getProtocol();
        int actionIndex = getSelectedLine(request);
        if (((ProtocolForm) form).getActionHelper().validFile(protocol.getProtocolActions().get(actionIndex).getNewActionAttachment(), "protocolNotifyIrbBean")) {
            ProtocolSubmissionDoc fyiAttachment = null;
            ProtocolActionAttachment newAttachment = protocol.getProtocolActions().get(actionIndex).getNewActionAttachment();
            Long submissionId = protocol.getProtocolActions().get(actionIndex).getSubmissionIdFk();
            for(ProtocolSubmissionBase fyiSubmission : protocol.getProtocolSubmissions()) {
                if(fyiSubmission.getSubmissionId().longValue() == submissionId.longValue()) {
                    fyiAttachment = ProtocolSubmissionBuilder.createProtocolSubmissionDoc((ProtocolSubmission) fyiSubmission, newAttachment.getFile().getFileName(),
                            newAttachment.getFile().getContentType(), newAttachment.getFile().getFileData(), newAttachment.getDescription());
                    break;
                }
            }

            if(fyiAttachment != null) {
                getBusinessObjectService().save(fyiAttachment);
                ((ProtocolForm) form).getActionHelper().getProtocolNotifyIrbBean().setNewActionAttachment(new ProtocolActionAttachment());
            }
        }
        return mapping.findForward(getProtocolHistoryForwardNameHook());
    }

    public ActionForward deleteSubmissionDoc(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                             HttpServletResponse response) throws Exception {
        ProtocolFormBase protocolForm = (ProtocolForm) form;
        int actionIndex = getSelectedLine(request);
        int attachmentIndex = getSelectedAttachment(request);
        org.kuali.kra.protocol.actions.ProtocolActionBase protocolAction = protocolForm.getActionHelper().getProtocol().getProtocolActions().get(actionIndex);
        ProtocolSubmissionDocBase attachment = protocolAction.getProtocolSubmissionDocs().get(attachmentIndex);

        if (attachment == null) {
            LOG.info("The attachment was not found for protocolAction: " + actionIndex + ", protocolSubmissionDoc: " + attachmentIndex);

            return mapping.findForward(getProtocolHistoryForwardNameHook());
        }

        getBusinessObjectService().delete(attachment);

        return mapping.findForward(getProtocolHistoryForwardNameHook());
    }

    /**
     * Notify the IRB committee.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward notifyCommitteeProtocol(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        ProtocolForm protocolForm = (ProtocolForm) form;
        String forwardTo = getProtocolActionRequestService().notifyCommitteeProtocol(protocolForm);
        forward = mapping.findForward(forwardTo);
        return forward;
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
    public ActionForward createAmendment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        ProtocolForm protocolForm = (ProtocolForm) form;
        if(getProtocolActionRequestService().isCreateAmendmentAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().createAmendment(protocolForm);
            forward = mapping.findForward(forwardTo);
            // Switch over to the new protocol document and
            // go to the Protocol tab web page.
            loadDocument(protocolForm);
            protocolForm.getProtocolHelper().prepareView();
        }
        return forward;
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
    public ActionForward modifyAmendmentSections(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ProtocolForm protocolForm = (ProtocolForm) form;
        Protocol protocol = (Protocol) protocolForm.getProtocolDocument().getProtocol();
        protocolForm.getActionHelper().setCurrentTask(TaskName.MODIFY_PROTOCOL_AMMENDMENT_SECTIONS);
        ProtocolTask task = new ProtocolTask(TaskName.MODIFY_PROTOCOL_AMMENDMENT_SECTIONS, protocol);
        if (isAuthorized(task)) {
            if (!applyRules(new ModifyAmendmentSectionsEvent(protocolForm.getProtocolDocument(), Constants.PROTOCOL_MODIFY_AMENDMENT_KEY,
                (ProtocolAmendmentBean) protocolForm.getActionHelper().getProtocolAmendmentBean()))) {
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
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        if(getProtocolActionRequestService().isCreateRenewalAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().createRenewal(protocolForm);
            forward = mapping.findForward(forwardTo);
            loadDocument(protocolForm);
            protocolForm.getProtocolHelper().prepareView();
        }
        return forward;
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
    public ActionForward createRenewalWithAmendment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        if(getProtocolActionRequestService().isCreateRenewalWithAmendmentAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().createRenewalWithAmendment(protocolForm);
            forward = mapping.findForward(forwardTo);
            loadDocument(protocolForm);
            protocolForm.getProtocolHelper().prepareView();
        }
        return forward;
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
        ProtocolTask task = new ProtocolTask(TaskName.PROTOCOL_AMEND_RENEW_DELETE, (Protocol) protocolForm.getProtocolDocument().getProtocol());
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
            getProtocolDeleteService().delete(protocolForm.getProtocolDocument());
            
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
        ProtocolDocument doc = (ProtocolDocument) ((ProtocolForm) form).getProtocolDocument();
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
        ProtocolAttachmentProtocol attachment = (ProtocolAttachmentProtocol) protocolForm.getProtocolDocument().getProtocol().getActiveAttachmentProtocolsNoDelete().get(selected);
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
        ProtocolAttachmentPersonnel personAttach = (ProtocolAttachmentPersonnel) protocolForm.getProtocolDocument().getProtocol().getAttachmentPersonnels().get(selected);
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
        Protocol protocol = (Protocol) protocolForm.getProtocolDocument().getProtocol();
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        ActionHelper actionHelper = (ActionHelper) protocolForm.getActionHelper();
        StringBuffer fileName = new StringBuffer().append("Protocol-");
        if (applyRules(new ProtocolActionPrintEvent(protocolForm.getProtocolDocument(), actionHelper.getSummaryReport(),
            actionHelper.getFullReport(), actionHelper.getHistoryReport(), actionHelper.getReviewCommentsReport()))) {
            ProtocolPrintType printType = ProtocolPrintType.PROTOCOL_FULL_PROTOCOL_REPORT;
            String reportName = protocol.getProtocolNumber()+"-"+printType.getReportName();
            AttachmentDataSource dataStream = getProtocolPrintingService().print(reportName,getPrintReportArtifacts(protocolForm, fileName));
            if (dataStream.getData() != null) {
                dataStream.setName(fileName.toString());
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
        Protocol protocol = (Protocol) protocolForm.getProtocolDocument().getProtocol();
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        String fileName = "Protocol_Summary_Report.pdf";
        ProtocolPrintType printType = ProtocolPrintType.PROTOCOL_FULL_PROTOCOL_REPORT;
        String reportName = protocol.getProtocolNumber() + "-" + printType.getReportName();
        AttachmentDataSource dataStream = getProtocolPrintingService().print(reportName, getPrintArtifacts(protocolForm));
        if (dataStream.getData() != null) {
            dataStream.setName(fileName.toString());
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
        String reportName = protocol.getProtocolNumber() + "-" + "ProtocolQuestionnaires";
        AttachmentDataSource dataStream = getProtocolPrintingService().print(reportName, getQuestionnairePrintingService().getQuestionnairePrintable(protocolForm.getProtocolDocument().getProtocol(), protocolForm.getActionHelper().getQuestionnairesToPrints()));
        if (dataStream.getData() != null) {
            dataStream.setName(fileName.toString());
            PrintingUtils.streamToResponse(dataStream, response);
            forward = null;
        }


        return forward;
    }
    
    public ActionForward printProtocolCorrespondences(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        Protocol protocol = protocolForm.getProtocolDocument().getProtocol();
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        String fileName = "IRB_Protocol_Ccorrespondence_Report.pdf";
        String reportName = protocol.getProtocolNumber() + "-" + "ProtocolCorrespondences";
        AttachmentDataSource dataStream = getProtocolPrintingService().print(reportName, getIrbCorrespondencePrintingService().getCorrespondencePrintable(protocol, protocolForm.getActionHelper().getCorrespondencesToPrint()));
        if (dataStream.getData() != null) {
            dataStream.setName(fileName.toString());
            PrintingUtils.streamToResponse(dataStream, response);
            forward = null;
        }
        return forward;
    }
    
    private IrbCorrespondencePrintingService getIrbCorrespondencePrintingService() {
        return KcServiceLocator.getService(IrbCorrespondencePrintingService.class);
    }

    /*
     * get printables for protocol &amp; questionnaires.
     * Protocol only has one printable and each questionnaire has its own printable.
     */
    private List<Printable> getPrintArtifacts(ActionForm form) {
        ProtocolForm protocolForm = (ProtocolForm) form;
        List<Printable> printableArtifactList = new ArrayList<Printable>();
        org.kuali.kra.protocol.actions.print.ProtocolPrintType printType = org.kuali.kra.protocol.actions.print.ProtocolPrintType.valueOf(PRINTTAG_MAP.get("full"));

        AbstractPrint printable = (AbstractPrint)getProtocolPrintingService().getProtocolPrintable(printType);
        printable.setPrintableBusinessObject(protocolForm.getProtocolDocument().getProtocol());
        Map reportParameters = new HashMap();
        ProtocolSummaryPrintOptions summaryOptions = protocolForm.getActionHelper().getProtocolPrintOption();
        
        reportParameters.put(ProtocolSummaryPrintOptions.class, summaryOptions);

        printable.setReportParameters(reportParameters);
        printableArtifactList.add(printable);
        if (summaryOptions.isReviewComments()) {
            Map reportParameters1 = getReportOptions(protocolForm, ProtocolPrintType.PROTOCOL_REVIEW_COMMENTS_REPORT);
            AbstractPrint printable1 = (AbstractPrint)getProtocolPrintingService().getProtocolPrintable(org.kuali.kra.protocol.actions.print.ProtocolPrintType.valueOf(PRINTTAG_MAP.get("comments")));
            printable1.setPrintableBusinessObject(protocolForm.getProtocolDocument().getProtocol());
            printable1.setReportParameters(reportParameters1);
            printableArtifactList.add(printable1);
            
        }
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
     * This is to view Personnel attachment if attachment is selected in print &amp; summary panel.
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
     * @param form form
     * @param attachment attachment
     * @return attachment file
     */
    private byte[] getProtocolAttachmentFile(ProtocolForm form,ProtocolAttachmentProtocol attachment) {
        
        byte[] attachmentFile =null;
        final AttachmentFile file = attachment.getFile();
        Printable printableArtifacts= getProtocolPrintingService().getProtocolPrintArtifacts(form.getProtocolDocument().getProtocol());
        Protocol protocolCurrent = form.getProtocolDocument().getProtocol();
        int currentProtoSeqNumber= protocolCurrent.getSequenceNumber();
        try {
            if(printableArtifacts.isWatermarkEnabled()){
                int currentAttachmentSequence=attachment.getSequenceNumber();
                String docStatusCode=attachment.getDocumentStatusCode();
                String statusCode=attachment.getStatusCode();
                // TODO perhaps the check for equality of protocol and attachment sequence numbers, below, is now redundant
                if(((getProtocolAttachmentService().isAttachmentActive(attachment))&&(currentProtoSeqNumber == currentAttachmentSequence))||(docStatusCode.equals("1"))){
                    if (ProtocolAttachmentProtocol.COMPLETE_STATUS_CODE.equals(statusCode)) {
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
        ProtocolForm protocolForm = (ProtocolForm) form;
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
        org.kuali.kra.irb.actions.ProtocolAction action = (org.kuali.kra.irb.actions.ProtocolAction) protocolForm.getActionHelper().getSelectedProtocolAction();
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
        ProtocolSummary protocolSummary = (ProtocolSummary) protocolForm.getActionHelper().getProtocolSummary();
        if (((String)request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE)).contains(".prev.")) {
            protocolSummary = (ProtocolSummary) protocolForm.getActionHelper().getPrevProtocolSummary();
        }
        int selectedIndex = getSelectedLine(request);
        AttachmentSummary attachmentSummary = protocolSummary.getAttachments().get(selectedIndex);
        
        
        if (attachmentSummary.getAttachmentType().startsWith(Constants.PROTOCOL_ATTACHMENT_PREFIX)) {
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
        ActionHelper actionHelper = (ActionHelper) protocolForm.getActionHelper();
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
        ActionHelper actionHelper = (ActionHelper) protocolForm.getActionHelper();
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
        ActionHelper actionHelper = (ActionHelper) protocolForm.getActionHelper();
        actionHelper.setCurrentSubmissionNumber(actionHelper.getPrevSubmissionNumber());
        actionHelper.setAmendmentDetails();
        actionHelper.initAmendmentBeans(true);
        protocolForm.getActionHelper().initSubmissionDetails();
        return mapping.findForward(Constants.MAPPING_PROTOCOL_HISTORY);
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
        ActionHelper actionHelper = (ActionHelper) protocolForm.getActionHelper();
        actionHelper.setCurrentSubmissionNumber(actionHelper.getNextSubmissionNumber());
        actionHelper.setAmendmentDetails();
        actionHelper.initAmendmentBeans(true);
        protocolForm.getActionHelper().initSubmissionDetails();
        return mapping.findForward(Constants.MAPPING_PROTOCOL_HISTORY);
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
    
    

    public ActionForward assignToAgenda(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        ProtocolForm protocolForm = (ProtocolForm) form;
        if(getProtocolActionRequestService().isAssignToAgendaAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().assignToAgenda(protocolForm);
            forward = mapping.findForward(forwardTo);
        }
        return forward;
    }
    
    public ActionForward protocolReviewNotRequired(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        ProtocolForm protocolForm = (ProtocolForm) form;
        if(getProtocolActionRequestService().isProtocolReviewNotRequiredAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().protocolReviewNotRequired(protocolForm);
            forward = mapping.findForward(forwardTo);
        }
        return forward;
    }
    
    public ActionForward confirmAssignToAgenda(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);

        if (CONFIRM_ASSIGN_TO_AGENDA_KEY.equals(question)) {
            ProtocolForm protocolForm = (ProtocolForm) form;
            ProtocolAssignToAgendaBean actionBean = (ProtocolAssignToAgendaBean) protocolForm.getActionHelper().getAssignToAgendaBean();
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
        if(getProtocolActionRequestService().isAssignReviewersAuthorized(protocolForm)) {
            boolean processRequest = true;
            if (GlobalVariables.getMessageMap().hasWarnings()) {
                String callerString = String.format("assignReviewers");
                Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
                if (question == null) {
                    // ask question if not already asked
                    forward = performQuestionWithoutInput(mapping, form, request, response, 
                                                            CONIFRM_REMOVE_REVIEWER_KEY, 
                                                            getKualiConfigurationService().getPropertyValueAsString(KeyConstants.MESSAGE_REMOVE_REVIEWERS_WITH_COMMENTS), 
                                                            KRADConstants.CONFIRMATION_QUESTION, 
                                                            callerString, 
                                                            "");
                    processRequest = false;
                }else {
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
                String forwardTo = getProtocolActionRequestService().assignReviewers(protocolForm);
                forward = mapping.findForward(forwardTo);
            }
        }
        return forward;
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
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        ProtocolForm protocolForm = (ProtocolForm) form;
        if(getProtocolActionRequestService().isGrantExemptionAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().grantExemption(protocolForm);
            confirmFollowupAction(mapping, form, request, response, Constants.MAPPING_BASIC);
            forward = mapping.findForward(forwardTo);
        }
        if (request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME) != null) {
            confirmFollowupAction(mapping, form, request, response, Constants.MAPPING_BASIC);
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
        if(getProtocolActionRequestService().isFullApprovalAuthorized(protocolForm)) {
            forward = super.approve(mapping, protocolForm, request, response);
            getProtocolActionRequestService().grantFullApproval(protocolForm);
            if (protocolForm.getActionHelper().getProtocolCorrespondence() != null) {
                return mapping.findForward(CORRESPONDENCE);
            }else {
                forward = routeProtocolToHoldingPage(mapping, protocolForm);
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
        
        if(getProtocolActionRequestService().isExpeditedApprovalAuthorized(protocolForm)) {
            getProtocolActionRequestService().grantExpeditedApproval(protocolForm);
            forward = confirmFollowupAction(mapping, form, request, response, IrbConstants.PROTOCOL_ACTIONS_TAB);
        }else {
            return forward;
        }

        // Question frame work will execute method twice.  so, need to be aware that service will not be executed twice.
        if (request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME) != null) {
            confirmFollowupAction(mapping, form, request, response, IrbConstants.PROTOCOL_ACTIONS_TAB);                                  
            protocolForm.getProtocolHelper().prepareView();
        }

        if (protocolForm.getActionHelper().getProtocolCorrespondence() != null) {
            return mapping.findForward(CORRESPONDENCE);
        } else {
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
        if(getProtocolActionRequestService().isResponseApprovalAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().grantResponseApproval(protocolForm);
            routeProtocolToHoldingPage(mapping, protocolForm);
            forward = mapping.findForward(forwardTo);
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
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        ProtocolForm protocolForm = (ProtocolForm) form;
        String taskName = getTaskName(request);
        if(getProtocolActionRequestService().isRequestActionAuthorized(protocolForm, taskName)) {
            String forwardTo = getProtocolActionRequestService().performRequestAction(protocolForm, taskName);
            forward = mapping.findForward(forwardTo);
        }
        return forward;
    }
    
    /**
     * Withdraws a previously submitted request action.
     * 
     * @param mapping The mapping associated with this action.
     * @param form The Protocol form.
     * @param request The HTTP request
     * @param response The HTTP response
     * @return the forward to the current page
     * @throws Exception
     */
    public ActionForward withdrawRequestAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        ProtocolForm protocolForm = (ProtocolForm) form;
        String taskName = getTaskName(request);
        if(getProtocolActionRequestService().isWithdrawRequestActionAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().withdrawRequestAction(protocolForm);
            forward = mapping.findForward(forwardTo);
        }
        return forward;
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
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        ProtocolForm protocolForm = (ProtocolForm) form;
        if(getProtocolActionRequestService().isCloseProtocolAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().closeProtocol(protocolForm);
            forward = mapping.findForward(forwardTo);
        }
        return forward;
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
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        ProtocolForm protocolForm = (ProtocolForm) form;
        if(getProtocolActionRequestService().isCloseEnrollmentAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().closeEnrollment(protocolForm);
            forward = mapping.findForward(forwardTo);
        }
        return forward;
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
        if(getProtocolActionRequestService().isDeferProtocolAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().deferProtocol(protocolForm);
            forward = mapping.findForward(forwardTo);
            loadDocument(protocolForm);
            protocolForm.getProtocolHelper().prepareView();
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
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        ProtocolForm protocolForm = (ProtocolForm) form;
        if(getProtocolActionRequestService().isDisapproveProtocolAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().disapproveProtocol(protocolForm);
            forward = mapping.findForward(forwardTo);
        }
        return forward;
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
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        ProtocolForm protocolForm = (ProtocolForm) form;
        if(getProtocolActionRequestService().isExpireProtocolAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().expireProtocol(protocolForm);
            forward = mapping.findForward(forwardTo);
        }
        return forward;
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
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        ProtocolForm protocolForm = (ProtocolForm) form;
        if(getProtocolActionRequestService().isIrbAcknowledgementAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().irbAcknowledgement(protocolForm);
            forward = mapping.findForward(forwardTo);
        }
        return forward;
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
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        ProtocolForm protocolForm = (ProtocolForm) form;
        if(getProtocolActionRequestService().isPermitDataAnalysisAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().permitDataAnalysis(protocolForm);
            forward = mapping.findForward(forwardTo);
        }
        return forward;
    }

    /**
     * Reopens enrollment for this Protocol.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward reopenEnrollment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        ProtocolForm protocolForm = (ProtocolForm) form;
        if(getProtocolActionRequestService().isReopenEnrollmentAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().reopenEnrollment(protocolForm);
            forward = mapping.findForward(forwardTo);
        }
        return forward;
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
        if(getProtocolActionRequestService().isReturnForSMRAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().returnForSMR(protocolForm);
            forward = mapping.findForward(forwardTo);
            loadDocument(protocolForm);
            protocolForm.getProtocolHelper().prepareView();
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
        if(getProtocolActionRequestService().isReturnForSRRAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().returnForSRR(protocolForm);
            forward = mapping.findForward(forwardTo);
            loadDocument(protocolForm);
            protocolForm.getProtocolHelper().prepareView();
        }
        return forward;
    }
    
    /**
     * Returns the protocol to the PI.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward returnToPI(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        ProtocolForm protocolForm = (ProtocolForm) form;
        if(getProtocolActionRequestService().isReturnToPIAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().returnToPI(protocolForm);
            forward = mapping.findForward(forwardTo);
            loadDocument(protocolForm);
            protocolForm.getProtocolHelper().prepareView();
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
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        ProtocolForm protocolForm = (ProtocolForm) form;
        if(getProtocolActionRequestService().isSuspendAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().suspend(protocolForm);
            forward = mapping.findForward(forwardTo);
        }
        return forward;
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
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        ProtocolForm protocolForm = (ProtocolForm) form;
        if(getProtocolActionRequestService().isSuspendByDsmbAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().suspendByDsmb(protocolForm);
            forward = mapping.findForward(forwardTo);
        }
        return forward;
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
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        ProtocolForm protocolForm = (ProtocolForm) form;
        if(getProtocolActionRequestService().isTerminateAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().terminate(protocolForm);
            forward = mapping.findForward(forwardTo);
        }
        return forward;
    }
    
    public ActionForward manageComments(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        ProtocolForm protocolForm = (ProtocolForm) form;
        if(getProtocolActionRequestService().isManageCommentsAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().manageComments(protocolForm);
            forward = mapping.findForward(forwardTo);
        }
        return forward;
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
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        ProtocolForm protocolForm = (ProtocolForm) form;
        if(getProtocolActionRequestService().isOpenProtocolForAdminCorrectionAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().openProtocolForAdminCorrection(protocolForm);
            forward = mapping.findForward(forwardTo);
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
            
            UndoLastActionService undoLastActionService = KcServiceLocator.getService(UndoLastActionService.class);
            ProtocolDocument updatedDocument = (ProtocolDocument) undoLastActionService.undoLastAction(protocolDocument, undoLastActionBean);
                       
    
            recordProtocolActionSuccess("Undo Last Action");
    
            if (!updatedDocument.getDocumentNumber().equals(protocolForm.getDocId())) {
                protocolForm.setDocId(updatedDocument.getDocumentNumber());
                loadDocument(protocolForm);
                protocolForm.getProtocolHelper().prepareView();
                return mapping.findForward(IrbConstants.PROTOCOL_TAB);
            }
            if (ProtocolActionType.SPECIFIC_MINOR_REVISIONS_REQUIRED.equals(lastActionType)
                    || ProtocolActionType.SUBSTANTIVE_REVISIONS_REQUIRED.equals(lastActionType)) {
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
    
    public ActionForward submitCommitteeDecision(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        ProtocolForm protocolForm = (ProtocolForm) form;
        if(getProtocolActionRequestService().isSubmitCommitteeDecisionAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().submitCommitteeDecision(protocolForm);
            forward = mapping.findForward(forwardTo);
            confirmFollowupAction(mapping, form, request, response, Constants.MAPPING_BASIC);
        }
        return forward;
    }
    
    public ActionForward addAbstainer(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        CommitteeDecision decision = (CommitteeDecision) protocolForm.getActionHelper().getCommitteeDecision();
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
        CommitteeDecision decision = (CommitteeDecision) protocolForm.getActionHelper().getCommitteeDecision();
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
        CommitteeDecision decision = (CommitteeDecision) protocolForm.getActionHelper().getCommitteeDecision();
        if (applyRules(new CommitteeDecisionRecuserEvent(protocolForm.getProtocolDocument(), decision))) {
            decision.getRecused().add(decision.getNewRecused());
            decision.setNewRecused(new CommitteePerson());
            decision.setRecusedCount(decision.getRecusedCount() + 1);
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward modifySubmissionAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        final String callerString = "assignCommitteeSchedule";
            ProtocolModifySubmissionBean bean = protocolForm.getActionHelper().getProtocolModifySubmissionBean();
        ProtocolAssignCmtSchedBean actionBean = ((ActionHelper) protocolForm.getActionHelper()).getAssignCmtSchedBean();
            if (applyRules(new ProtocolModifySubmissionEvent(protocolForm.getProtocolDocument(), bean))) {
            if (!hasDocumentStateChanged(protocolForm)) {
                ProtocolSubmission protocolSubmission = protocolForm.getProtocolDocument().getProtocol().getProtocolSubmission();
                if( protocolSubmission != null) {
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
                            // reset selected value on panel, since they declined reassignment.
                            actionBean.setScheduleId(protocolSubmission.getScheduleId());
            }
        } else {
                        performAssignment = true;
                    }
        
                    if (performAssignment) {
                        if(StringUtils.equals(protocolSubmission.getSubmissionStatusCode(), ProtocolSubmissionStatus.IN_AGENDA)) {
                            getProtocolAssignCmtSchedService().assignToCommitteeAndSchedulePostAgendaAssignment((Protocol) protocolForm.getProtocolDocument().getProtocol(), actionBean);
                        }else {
                        getProtocolAssignCmtSchedService().assignToCommitteeAndSchedule((Protocol) protocolForm.getProtocolDocument().getProtocol(), actionBean);
                    }
                    }
                        
                }
            }
            KcServiceLocator.getService(ProtocolModifySubmissionService.class).modifySubmission(protocolForm.getProtocolDocument(), bean);
            recordProtocolActionSuccess("Modify Submission Request");
        } else {
            //need to check whether there are existing business rule error messages, do not want to lose them
            if (GlobalVariables.getMessageMap().getErrorCount() <= 0) {            
                GlobalVariables.getMessageMap().clearErrorMessages();
                GlobalVariables.getMessageMap().putError("documentstatechanged", KeyConstants.ERROR_PROTOCOL_DOCUMENT_STATE_CHANGED,  new String[] {});
            }
        }
        return mapping.findForward(Constants.MAPPING_BASIC);        
    }
    
    public ActionForward deleteRecused(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        CommitteeDecision decision = (CommitteeDecision) protocolForm.getActionHelper().getCommitteeDecision();
        CommitteePerson person = decision.getRecused().get(getLineToDelete(request));
        if (person != null) {
            decision.getRecusedToDelete().add(person);
            decision.getRecused().remove(getLineToDelete(request));
            decision.setRecusedCount(decision.getRecusedCount() - 1);
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    private Printable getPrintableArtifacts(Protocol protocol, String reportType, StringBuffer fileName,Map reportParameters) {
        org.kuali.kra.protocol.actions.print.ProtocolPrintType printType = org.kuali.kra.protocol.actions.print.ProtocolPrintType.valueOf(PRINTTAG_MAP.get(reportType));

        AbstractPrint printable = (AbstractPrint)getProtocolPrintingService().getProtocolPrintable(printType);
        printable.setPrintableBusinessObject(protocol);
        printable.setReportParameters(reportParameters);
        fileName.append(reportType).append("-");
        return printable;
    }

    private ProtocolPrintingService getProtocolPrintingService() {
        return KcServiceLocator.getService(ProtocolPrintingService.class);
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
            CommitteeScheduleMinute newReviewComment = (CommitteeScheduleMinute) reviewCommentsBean.getNewReviewComment();
            List<CommitteeScheduleMinuteBase> reviewComments = reviewCommentsBean.getReviewComments();
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
            List<CommitteeScheduleMinuteBase> reviewComments = reviewCommentsBean.getReviewComments();
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
            List<CommitteeScheduleMinuteBase> reviewComments = reviewCommentsBean.getReviewComments();
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
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        ProtocolForm protocolForm = (ProtocolForm) form;
        if(getProtocolActionRequestService().isAbandonAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().abandon(protocolForm);
            forward = mapping.findForward(forwardTo);
        }
        return forward;
    }

    
    private ReviewCommentsBean getReviewCommentsBean(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ReviewCommentsBean reviewCommentsBean = null;
        
        ProtocolActionBean protocolActionBean = getActionBean(form, request);
        if (protocolActionBean != null && protocolActionBean instanceof ProtocolOnlineReviewCommentable) {
            reviewCommentsBean = (ReviewCommentsBean) ((ProtocolOnlineReviewCommentable) protocolActionBean).getReviewCommentsBean();
        }
        
        return reviewCommentsBean;
    }
    
    private ProtocolActionBean getActionBean(ActionForm form, HttpServletRequest request) {
        ProtocolForm protocolForm = (ProtocolForm) form;

        String taskName = getTaskName(request);
        
        ProtocolActionBean protocolActionBean = null;
        if (StringUtils.isNotBlank(taskName)) {
            protocolActionBean = (ProtocolActionBean) protocolForm.getActionHelper().getActionBean(taskName);
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
    
    private ProtocolAttachmentService getProtocolAttachmentService() {
        return KcServiceLocator.getService(ProtocolAttachmentService.class);
    }
    
    private TaskAuthorizationService getTaskAuthorizationService() {
        return KcServiceLocator.getService(TaskAuthorizationService.class);
    }
    
    public ProtocolCopyService getProtocolCopyService() {
        return KcServiceLocator.getService(ProtocolCopyService.class);
    }
    
    public ProtocolAmendRenewService getProtocolAmendRenewService() {
        return KcServiceLocator.getService(ProtocolAmendRenewService.class);
    }
    
    private ProtocolDeleteService getProtocolDeleteService() {
        return KcServiceLocator.getService(ProtocolDeleteService.class);
    }
    
    private ProtocolAssignCmtSchedService getProtocolAssignCmtSchedService() {
        return KcServiceLocator.getService(ProtocolAssignCmtSchedService.class);
    }
    
    private ProtocolAssignToAgendaService getProtocolAssignToAgendaService() {
        return KcServiceLocator.getService(ProtocolAssignToAgendaService.class);
    }
    
    private CommitteeService getCommitteeService() {
        return KcServiceLocator.getService(CommitteeService.class);
    }
    
    private ProtocolRiskLevelService getProtocolRiskLevelService() {
        return KcServiceLocator.getService(ProtocolRiskLevelService.class);
    }
    
    private ReviewCommentsService getReviewCommentsService() {
        return KcServiceLocator.getService(ReviewCommentsService.class);
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
        ProtocolActionAttachment attachment = (ProtocolActionAttachment) form.getActionHelper().getProtocolNotifyIrbBean().getActionAttachments().get(selection);

        if (attachment == null) {
            LOG.info(NOT_FOUND_SELECTION + selection);

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
        return confirmDeleteAttachment(mapping, (ProtocolForm) form, request, response, (List) ((ProtocolForm) form).getActionHelper().getProtocolNotifyIrbBean().getActionAttachments());
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
        org.kuali.kra.irb.actions.ProtocolAction protocolAction = (org.kuali.kra.irb.actions.ProtocolAction) protocolForm.getActionHelper().getProtocol().getProtocolActions().get(actionIndex);
        ProtocolSubmissionDoc attachment = (ProtocolSubmissionDoc) protocolAction.getProtocolSubmissionDocs().get(attachmentIndex);

        if (attachment == null) {
            LOG.info(NOT_FOUND_SELECTION + "protocolAction: " + actionIndex + ", protocolSubmissionDoc: " + attachmentIndex);

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
        org.kuali.kra.irb.actions.ProtocolAction protocolAction = (org.kuali.kra.irb.actions.ProtocolAction) protocolForm.getActionHelper().getProtocol().getProtocolActions().get(actionIndex);
        ProtocolCorrespondence attachment = (ProtocolCorrespondence) protocolAction.getProtocolCorrespondences().get(attachmentIndex);

        if (attachment == null) {
            LOG.info(NOT_FOUND_SELECTION + "protocolAction: " + actionIndex + ", protocolCorrespondence: " + attachmentIndex);

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
            ProtocolActionAttachment attachment = (ProtocolActionAttachment) requestBean.getActionAttachments().get(selection);
    
            if (attachment == null) {
                LOG.info(NOT_FOUND_SELECTION + selection);

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
            forward = confirmDeleteAttachment(mapping, (ProtocolForm) form, request, response, (List) requestBean.getActionAttachments());
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
    

    public ActionForward saveNotes(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        Protocol protocol = protocolForm.getProtocolDocument().getProtocol();
            
        if (!hasDocumentStateChanged(protocolForm)) {
            if (protocolForm.getActionHelper().getCanManageNotes()) {
                
                final AddProtocolNotepadRule rule = new AddProtocolNotepadRuleImpl();

                boolean validNotes = true;
                //validate all of them first
                for (ProtocolNotepadBase note : protocol.getNotepads()) {
                    if (note.isEditable()) {
                        AddProtocolNotepadEvent event = new AddProtocolNotepadEvent((ProtocolDocument) protocol.getProtocolDocument(), (ProtocolNotepad) note);
                        if (!rule.processAddProtocolNotepadRules(event)) {
                            validNotes = false;
                        }
                    }
                }
                
                if (validNotes) {
                    for (ProtocolNotepadBase note : protocol.getNotepads()) {
                        if (StringUtils.isBlank(note.getUpdateUserFullName())) {
                            note.setUpdateUserFullName(GlobalVariables.getUserSession().getPerson().getName());
                            note.setUpdateTimestamp(KcServiceLocator.getService(DateTimeService.class).getCurrentTimestamp());
                        }
                        
                        if (StringUtils.isNotBlank(note.getCreateUser())) {
                            Person creator = this.getPersonService().getPersonByPrincipalName(note.getCreateUser());
                            note.setCreateUserFullName(creator==null?String.format(PERSON_NOT_FOUND_FORMAT_STRING, note.getCreateUser()):creator.getName());
                        } else {
                            note.setCreateUserFullName("");
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

    /*
     * confirmation question for followup action
     */
    private ActionForward confirmFollowupAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response, String forward) throws Exception {

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

    private FollowupActionService getFollowupActionService() {
        return KcServiceLocator.getService(FollowupActionService.class);
    }
    /**
     * This method is to get Watermark Service. 
     */
    private WatermarkService getWatermarkService() {
        return  KcServiceLocator.getService(WatermarkService.class);
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
          return streamReviewAttachment(mapping, request, response, (List<ProtocolReviewAttachment>) ((ProtocolForm)form).getActionHelper().getReviewAttachments());
    }
    
    private ActionForward streamReviewAttachment (ActionMapping mapping, HttpServletRequest request, HttpServletResponse response, List<ProtocolReviewAttachment> reviewAttachments) throws Exception {

        int lineNumber = getLineToDelete(request);
        final ProtocolReviewAttachment attachment = reviewAttachments.get(lineNumber);
        
        if (attachment == null) {
            LOG.info(NOT_FOUND_SELECTION + lineNumber);

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
            reviewAttachmentsBean = (ReviewAttachmentsBean) ((ProtocolOnlineReviewCommentable) protocolActionBean).getReviewAttachmentsBean();
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
                if (applyRules(new ProtocolManageReviewAttachmentEvent(protocolForm.getProtocolDocument(),
                    "actionHelper.protocolManageReviewCommentsBean.reviewAttachmentsBean.", protocolForm.getActionHelper()
                            .getProtocolManageReviewCommentsBean().getReviewAttachmentsBean().getReviewAttachments()))) {
                    ProtocolGenericActionBean actionBean = (ProtocolGenericActionBean) protocolForm.getActionHelper().getProtocolManageReviewCommentsBean();
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

    
    private boolean hasDocumentStateChanged(ProtocolForm protocolForm) {
        return getProtocolActionRequestService().hasDocumentStateChanged(protocolForm);
    }
    
    private ActionForward checkToSendNotification(ActionMapping mapping, String forwardName, ProtocolForm protocolForm, ProtocolNotificationRequestBean notificationRequestBean) {
        boolean sendNotification = getProtocolActionRequestService().checkToSendNotification(protocolForm, notificationRequestBean, forwardName); 
        return sendNotification ? mapping.findForward(IrbConstants.PROTOCOL_NOTIFICATION_EDITOR) : mapping.findForward(forwardName);
    }
    
    public ActionForward sendNotification(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        Protocol protocol = protocolForm.getProtocolDocument().getProtocol();
        
        IRBNotificationRenderer renderer = new IRBNotificationRenderer(protocol);
        IRBNotificationContext context = new IRBNotificationContext(protocol, NotificationType.AD_HOC_NOTIFICATION_TYPE, NotificationType.AD_HOC_CONTEXT, renderer);
        
        protocolForm.getNotificationHelper().initializeDefaultValues(context);
        
        return mapping.findForward(IrbConstants.PROTOCOL_NOTIFICATION_EDITOR);
    }

    protected PersonService getPersonService() {
        return KcServiceLocator.getService(PersonService.class);
    }
    
    protected KcNotificationService getNotificationService() {
        return KcServiceLocator.getService(KcNotificationService.class);
    }
    
    public ActionForward viewCorrespondence(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ActionHelper actionHelper = ((ProtocolForm) form).getActionHelper();
        PrintableAttachment source = new PrintableAttachment();
        ProtocolCorrespondence correspondence = (ProtocolCorrespondence) actionHelper.getProtocolCorrespondence();
        source.setData(correspondence.getCorrespondence());
        source.setType(Constants.PDF_REPORT_CONTENT_TYPE);
        source.setName("Correspondence-" + correspondence.getProtocolCorrespondenceType().getDescription() + Constants.PDF_FILE_EXTENSION);
        PrintingUtils.streamToResponse(source, response);
        
        return null;
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
        ProtocolForm protocolForm = ((ProtocolForm) form);
        ActionHelper actionHelper = protocolForm.getActionHelper();
        ProtocolCorrespondence correspondence = (ProtocolCorrespondence) actionHelper.getProtocolCorrespondence();
        if (saveAction) {
            if (correspondence.getFinalFlag()) {
                correspondence.setFinalFlagTimestamp(KcServiceLocator.getService(DateTimeService.class).getCurrentTimestamp());
                           
            }
            getBusinessObjectService().save(correspondence);
        }
        // TODO : this is a hack for fullapprove to restore key
        if (GlobalVariables.getUserSession().retrieveObject("approvalComplpondence") != null) {
               GlobalVariables.getUserSession().addObject(DocumentAuthorizerBase.USER_SESSION_METHOD_TO_CALL_COMPLETE_OBJECT_KEY, GlobalVariables.getUserSession().retrieveObject("approvalComplCorrespondence"));
               GlobalVariables.getUserSession().removeObject("approvalComplCorrespondence");
        }

        // TODO : forward will be based different action correspondence. this is a test for withdraw
        if (correspondence.getNotificationRequestBean() != null) {
            return checkToSendNotification(mapping, correspondence.getForwardName(), protocolForm,
                    (ProtocolNotificationRequestBean) correspondence.getNotificationRequestBean());
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
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        int actionIndex = getSelectedLine(request);
        int attachmentIndex = getSelectedAttachment(request);
        org.kuali.kra.irb.actions.ProtocolAction protocolAction = (org.kuali.kra.irb.actions.ProtocolAction) protocolForm.getActionHelper().getProtocol().getProtocolActions().get(actionIndex);
        ProtocolCorrespondence protocolCorrespondence = (ProtocolCorrespondence) protocolAction.getProtocolCorrespondences().get(attachmentIndex);

        if (protocolCorrespondence == null) {
            LOG.info(NOT_FOUND_SELECTION + "protocolAction: " + actionIndex + ", protocolCorrespondence: " + attachmentIndex);

            return mapping.findForward(Constants.MAPPING_BASIC);
        }

        Protocol protocol = (Protocol) protocolCorrespondence.getProtocol();
        AttachmentDataSource dataSource = generateCorrespondenceDocument(protocol, protocolCorrespondence);
        if (dataSource != null) {
            protocolCorrespondence.setCorrespondence(dataSource.getData());
            protocolCorrespondence.setFinalFlag(false);
            protocolCorrespondence.setCreateUser(GlobalVariables.getUserSession().getPrincipalName());
            protocolCorrespondence.setCreateTimestamp(KcServiceLocator.getService(DateTimeService.class).getCurrentTimestamp());
            protocolCorrespondence.setForwardName(IrbConstants.PROTOCOL_ACTIONS_TAB);
            protocolForm.getActionHelper().setProtocolCorrespondence(protocolCorrespondence);
            getBusinessObjectService().save(protocolCorrespondence);
            return mapping.findForward(CORRESPONDENCE);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);

    }
    
    protected AttachmentDataSource generateCorrespondenceDocument(ProtocolBase protocol, ProtocolCorrespondence oldCorrespondence) throws PrintingException {
        ProtocolActionsCorrespondence correspondence = new ProtocolActionsCorrespondence(oldCorrespondence.getProtocolAction().getProtocolActionTypeCode());
        correspondence.setProtocol(protocol);
        return getProtocolActionCorrespondenceGenerationService().reGenerateCorrespondenceDocument(correspondence);
    } 

    private ProtocolActionCorrespondenceGenerationService getProtocolActionCorrespondenceGenerationService() {
        return KcServiceLocator.getService(ProtocolActionCorrespondenceGenerationService.class);
    }

    public ActionForward updateCorrespondence(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        int actionIndex = getSelectedLine(request);
        int attachmentIndex = getSelectedAttachment(request);
        org.kuali.kra.irb.actions.ProtocolAction protocolAction = (org.kuali.kra.irb.actions.ProtocolAction) protocolForm.getActionHelper().getProtocol().getProtocolActions().get(actionIndex);
        protocolAction.refreshReferenceObject("protocolCorrespondences");
        ProtocolCorrespondence protocolCorrespondence = (ProtocolCorrespondence) protocolAction.getProtocolCorrespondences().get(attachmentIndex);

        if (protocolCorrespondence == null) {
            LOG.info(NOT_FOUND_SELECTION + "protocolAction: " + actionIndex + ", protocolCorrespondence: " + attachmentIndex);

            return mapping.findForward(Constants.MAPPING_BASIC);
        }
        protocolCorrespondence.setForwardName(IrbConstants.PROTOCOL_ACTIONS_TAB);
        protocolForm.getActionHelper().setProtocolCorrespondence(protocolCorrespondence);

        return mapping.findForward(CORRESPONDENCE);

    }

}
