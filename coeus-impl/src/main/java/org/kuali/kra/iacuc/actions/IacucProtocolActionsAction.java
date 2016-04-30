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
package org.kuali.kra.iacuc.actions;

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
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.coeus.common.framework.print.Printable;
import org.kuali.coeus.common.framework.print.PrintableAttachment;
import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.common.framework.print.util.PrintingUtils;
import org.kuali.coeus.common.framework.print.watermark.WatermarkConstants;
import org.kuali.coeus.common.framework.print.watermark.WatermarkService;
import org.kuali.coeus.common.notification.impl.bo.NotificationType;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
import org.kuali.coeus.sys.framework.controller.KcHoldingPageConstants;
import org.kuali.coeus.sys.framework.validation.AuditHelper;
import org.kuali.coeus.sys.framework.controller.StrutsConfirmation;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolAction;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.iacuc.actions.amendrenew.IacucProtocolAmendRenewService;
import org.kuali.kra.iacuc.actions.amendrenew.ModifyIacucAmendmentSectionsEvent;
import org.kuali.kra.iacuc.actions.copy.IacucProtocolCopyService;
import org.kuali.kra.iacuc.actions.decision.IacucCommitteeDecision;
import org.kuali.kra.iacuc.actions.decision.IacucCommitteeDecisionAbstainerEvent;
import org.kuali.kra.iacuc.actions.decision.IacucCommitteeDecisionRecuserEvent;
import org.kuali.kra.iacuc.actions.decision.IacucCommitteePerson;
import org.kuali.kra.iacuc.actions.delete.IacucProtocolDeleteService;
import org.kuali.kra.iacuc.actions.followup.IacucFollowupActionService;
import org.kuali.kra.iacuc.actions.genericactions.IacucProtocolGenericActionBean;
import org.kuali.kra.iacuc.actions.print.IacucProtocolPrintingService;
import org.kuali.kra.iacuc.actions.request.IacucProtocolRequestBean;
import org.kuali.kra.iacuc.actions.reviewcomments.*;
import org.kuali.kra.iacuc.actions.submit.*;
import org.kuali.kra.iacuc.actions.undo.IacucProtocolUndoLastActionService;
import org.kuali.kra.iacuc.auth.IacucProtocolTask;
import org.kuali.kra.iacuc.committee.meeting.IacucCommitteeScheduleMinute;
import org.kuali.kra.iacuc.correspondence.IacucProtocolActionCorrespondenceGenerationService;
import org.kuali.kra.iacuc.correspondence.IacucProtocolActionsCorrespondence;
import org.kuali.kra.iacuc.infrastructure.IacucConstants;
import org.kuali.kra.iacuc.noteattachment.IacucProtocolAttachmentPersonnel;
import org.kuali.kra.iacuc.noteattachment.IacucProtocolAttachmentProtocol;
import org.kuali.kra.iacuc.noteattachment.IacucProtocolAttachmentService;
import org.kuali.kra.iacuc.notification.IacucProtocolNotificationContext;
import org.kuali.kra.iacuc.notification.IacucProtocolNotificationRenderer;
import org.kuali.kra.iacuc.notification.IacucProtocolNotificationRequestBean;
import org.kuali.kra.iacuc.onlinereview.IacucProtocolReviewAttachment;
import org.kuali.kra.iacuc.questionnaire.print.IacucCorrespondencePrintingService;
import org.kuali.kra.iacuc.questionnaire.print.IacucQuestionnairePrintingService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.actions.history.ProtocolHistoryFilterDatesEvent;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.ProtocolFormBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.ProtocolActionBean;
import org.kuali.kra.protocol.actions.ProtocolOnlineReviewCommentable;
import org.kuali.kra.protocol.actions.ProtocolSubmissionDocBase;
import org.kuali.kra.protocol.actions.notify.ProtocolActionAttachment;
import org.kuali.kra.protocol.actions.print.ProtocolActionPrintEvent;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewerBeanBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.actions.undo.UndoLastActionBean;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondence;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentBase;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentProtocolBase;
import org.kuali.kra.protocol.summary.AttachmentSummary;
import org.kuali.kra.protocol.summary.ProtocolSummary;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.kns.document.authorization.DocumentAuthorizerBase;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IacucProtocolActionsAction extends IacucProtocolAction {

    private static final Log LOG = LogFactory.getLog(IacucProtocolActionsAction.class);
    private static final String CONFIRM_NO_ACTION = "";
    private static final String CONFIRM_DELETE_ACTION_ATT = "confirmDeleteActionAttachment";
    private static final String CONFIRM_FOLLOWUP_ACTION = "confirmAddFollowupAction";

    private static final String CONFIRM_SUBMIT_FOR_REVIEW_KEY = "confirmSubmitForReview";
    
    private static final String NOT_FOUND_SELECTION = "The attachment was not found for selection ";

    private static final String CONFIRM_DELETE_PROTOCOL_KEY = "confirmDeleteProtocol";
    private static final String INVALID_ATTACHMENT = "this attachment version is invalid ";

    /** signifies that a response has already be handled therefore forwarding to obtain a response is not needed. */
    private static final ActionForward RESPONSE_ALREADY_HANDLED = null;
    private static final String SUBMISSION_ID = "submissionId";
    private static final String CORRESPONDENCE = "correspondence";
    public static final String ACTION_HELPER = "actionHelper.";
    public static final String REVIEWER_ATTRIBUTE = ".reviewer[";
    public static final String NUMBER_OF_REVIEWERS_ATTRIBUTE = ".numberOfReviewers";
    public static final String REVIEWER_TYPE_CODE_ATTRIBUTE = "].reviewerTypeCode";
    public static final String PERSON_ID_ATTRIBUTE = "].personId";
    public static final String FULL_NAME_ATTRIBUTE = "].fullName";
    public static final String NON_EMPLOYEE_FLAG_ATTRIBUTE = "].nonEmployeeFlag";
    public static final String TRUE_FLAG = "Y";


    public ActionForward assignCommitteeSchedule(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        IacucProtocol protocol = (IacucProtocol) protocolForm.getProtocolDocument().getProtocol();
        final String callerString = "assignCommitteeSchedule";
 
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    @Override
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

        if ("close".equals(protocolForm.getMethodToCall()) || protocolForm.getMethodToCall() == null) {
            // If we're closing, we can just leave right here.
            return mapping.findForward(KRADConstants.MAPPING_PORTAL);
        }

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
            final IacucProtocolDocument copy = (IacucProtocolDocument) getDocumentService().saveDocument(getIacucProtocolCopyService().copyProtocol(protocolForm.getIacucProtocolDocument()));

            // Switch over to the new protocol document and
            // go to the ProtocolBase tab web page.

            protocolForm.setDocId(copy.getDocumentNumber());
            protocolForm.setViewOnly(false);
            loadDocument(protocolForm);
            protocolForm.getIacucProtocolDocument().setViewOnly(protocolForm.isViewOnly());
            protocolForm.getActionHelper().setCurrentSubmissionNumber(-1);
            protocolForm.getProtocolHelper().prepareView();
            
            return mapping.findForward(IacucConstants.PROTOCOL_TAB);
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }    

    public ActionForward activate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return KcServiceLocator.getService(AuditHelper.class).setAuditMode(mapping, (ProtocolFormBase) form, true);
    }

    public ActionForward deactivate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return KcServiceLocator.getService(AuditHelper.class).setAuditMode(mapping, (ProtocolFormBase) form, false);
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
    

    public ActionForward assignCommittee(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        if(getProtocolActionRequestService().isAssignCommitteeAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().assignCommittee(protocolForm);
            forward = mapping.findForward(forwardTo);
        }
        return forward;
    }
      
    private boolean isCommitteeMeetingAssignedMaxProtocols(String committeeId, String scheduleId) {
        boolean isMax = false;
        
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
     * Method dispatched from org.kuali.coeus.sys.framework.controller.KcTransactionalDocumentActionBase for
     * when a "yes" condition is met.
     * 
     * @param mapping The mapping associated with this action.
     * @param form The ProtocolBase form.
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
        List<ProtocolReviewerBeanBase> reviewers = getReviewers(request, "iacucProtocolSubmitAction");
        super.route(mapping, protocolForm, request, response);
        getProtocolActionRequestService().submitForReview(protocolForm, reviewers);
        return routeProtocolToHoldingPage(mapping, protocolForm);
    }
    
        
    private ActionForward routeProtocolToHoldingPage(ActionMapping mapping, ProtocolFormBase protocolForm) {
        String routeHeaderId = protocolForm.getProtocolDocument().getDocumentNumber();
        String returnLocation = buildActionUrl(routeHeaderId, Constants.MAPPING_PROTOCOL_ACTIONS, "IacucProtocolDocument");
        
        ActionForward basicForward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
        ActionRedirect holdingPageForward = new ActionRedirect(mapping.findForward(KcHoldingPageConstants.MAPPING_HOLDING_PAGE));
        holdingPageForward.addParameter(KcHoldingPageConstants.HOLDING_PAGE_DOCUMENT_ID, routeHeaderId);
        return routeToHoldingPage(basicForward, basicForward, holdingPageForward, returnLocation, routeHeaderId);
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
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        if(getProtocolActionRequestService().isAdministrativelyMarkIncompleteProtocolAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().administrativelyMarkIncompleteProtocol(protocolForm);
            loadDocument(protocolForm);
            protocolForm.getProtocolHelper().prepareView();
            forward = mapping.findForward(forwardTo);
        }
        return forward;
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
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        if(getProtocolActionRequestService().isAdministrativelyWithdrawProtocolAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().administrativelyWithdrawProtocol(protocolForm);
            loadDocument(protocolForm);
            protocolForm.getProtocolHelper().prepareView();
            forward = mapping.findForward(forwardTo);
        }
        return forward;
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
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        if(getProtocolActionRequestService().isWithdrawProtocolAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().withdrawProtocol(protocolForm);
            loadDocument(protocolForm);
            protocolForm.getProtocolHelper().prepareView();
            forward = mapping.findForward(forwardTo);
        }
        return forward;
    }
    

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
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        String forwardTo = getProtocolActionRequestService().notifyProtocol(protocolForm);
        forward = mapping.findForward(forwardTo);
        loadDocument(protocolForm);
        protocolForm.getProtocolHelper().prepareView();
        return forward;
    }

    public ActionForward addSubmissionDoc(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        ProtocolBase protocol = protocolForm.getActionHelper().getProtocol();
        int actionIndex = getSelectedLine(request);
        if (((IacucActionHelper) protocolForm.getActionHelper()).validFile((protocol.getProtocolActions().get(actionIndex)).getNewActionAttachment(), "protocolNotifyIrbBean")) {
            IacucProtocolSubmissionDoc fyiAttachment = null;
            ProtocolActionAttachment newAttachment = (protocol.getProtocolActions().get(actionIndex)).getNewActionAttachment();
            Long submissionId = protocol.getProtocolActions().get(actionIndex).getSubmissionIdFk();
            for(ProtocolSubmissionBase fyiSubmission : protocol.getProtocolSubmissions()) {
                if(fyiSubmission.getSubmissionId().longValue() == submissionId.longValue()) {
                    fyiAttachment = IacucProtocolSubmissionBuilder.createProtocolSubmissionDoc((IacucProtocolSubmission) fyiSubmission, newAttachment.getFile().getFileName(),
                            newAttachment.getFile().getContentType(), newAttachment.getFile().getFileData(), newAttachment.getDescription());
                    break;
                }
            }

            if(fyiAttachment != null) {
                getBusinessObjectService().save(fyiAttachment);
                ((IacucActionHelper) ((IacucProtocolForm) form).getActionHelper()).getIacucProtocolNotifyIacucBean().setNewActionAttachment(new ProtocolActionAttachment());
            }
        }
        return mapping.findForward(getProtocolHistoryForwardNameHook());
    }

    public ActionForward deleteSubmissionDoc(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                             HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
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
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
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
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        if(getProtocolActionRequestService().isCreateRenewalAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().createRenewal(protocolForm);
            forward = mapping.findForward(forwardTo);
            // Switch over to the new protocol document and
            // go to the Protocol tab web page.
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
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        if(getProtocolActionRequestService().isCreateRenewalWithAmendmentAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().createRenewalWithAmendment(protocolForm);
            forward = mapping.findForward(forwardTo);
            // Switch over to the new protocol document and
            // go to the Protocol tab web page.
            loadDocument(protocolForm);
            protocolForm.getProtocolHelper().prepareView();
        }
        return forward;
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
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        if(getProtocolActionRequestService().isCreateContinuationAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().createContinuation(protocolForm);
            forward = mapping.findForward(forwardTo);
            // Switch over to the new protocol document and
            // go to the Protocol tab web page.
            loadDocument(protocolForm);
            protocolForm.getProtocolHelper().prepareView();
        }
        return forward;
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
    public ActionForward createContinuationWithAmendment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        if(getProtocolActionRequestService().isCreateContinuationWithAmendmentAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().createContinuationWithAmendment(protocolForm);
            forward = mapping.findForward(forwardTo);
            // Switch over to the new protocol document and
            // go to the Protocol tab web page.
            loadDocument(protocolForm);
            protocolForm.getProtocolHelper().prepareView();
        }
        return forward;
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
            ProtocolCorrespondence newProtocolCorrespondence = getProtocolCorrespondence(protocolForm, IacucConstants.PROTOCOL_ACTIONS_TAB, newNotificationBean, false);
            protocolForm.getActionHelper().setProtocolCorrespondence(newProtocolCorrespondence);
            recordProtocolActionSuccess("Delete ProtocolBase, Amendment, or Renewal");
            
            if (newProtocolCorrespondence != null) {
                return mapping.findForward(CORRESPONDENCE);
            } else {
                return checkToSendNotification(mapping, IacucConstants.PROTOCOL_TAB, (IacucProtocolForm)protocolForm, newNotificationBean);
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
        if (dataStream.getData() != null) {
            dataStream.setName(fileName.toString());
            PrintingUtils.streamToResponse(dataStream, response);
            forward = null;
        }
        return forward;
    }
    
    public ActionForward printProtocolCorrespondences(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocol protocol = protocolForm.getIacucProtocolDocument().getIacucProtocol();
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        String fileName = "IACUC_Protocol_Correspondence_Report.pdf";
        String reportName = protocol.getProtocolNumber() + "-" + "ProtocolCorrespondences";
        AttachmentDataSource dataStream = getProtocolPrintingService().print(reportName, getIacucCorrespondencePrintingService().getCorrespondencePrintable(protocol, protocolForm.getActionHelper().getCorrespondencesToPrint()));
        if (dataStream.getData() != null) {
            dataStream.setName(fileName.toString());
            PrintingUtils.streamToResponse(dataStream, response);
            forward = null;
        }
        return forward;
    }
    
    private IacucCorrespondencePrintingService getIacucCorrespondencePrintingService() {
        return KcServiceLocator.getService(IacucCorrespondencePrintingService.class);
    }

    protected IacucQuestionnairePrintingService getIacucQuestionnairePrintingService() {
        return KcServiceLocator.getService(IacucQuestionnairePrintingService.class);
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
     * This is to view Personnel attachment if attachment is selected in print &amp; summary panel.
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
        
        if (attachmentSummary.getAttachmentType().startsWith(Constants.PROTOCOL_ATTACHMENT_PREFIX)) {
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
        return mapping.findForward(Constants.MAPPING_IACUC_PROTOCOL_HISTORY);
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
        return mapping.findForward(Constants.MAPPING_IACUC_PROTOCOL_HISTORY);
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
    
    

    public ActionForward assignToAgenda(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        if(getProtocolActionRequestService().isAssignToAgendaAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().assignToAgenda(protocolForm);
            forward = mapping.findForward(forwardTo);
        }
        return forward;
    }
    
    public ActionForward removeFromAgenda(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        if(getProtocolActionRequestService().isRemoveFromAgendaAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().removeFromAgenda(protocolForm);
            forward = mapping.findForward(forwardTo);
        }
        return forward;
    }

    
    
    public ActionForward protocolReviewNotRequired(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        if(getProtocolActionRequestService().isProtocolReviewNotRequiredAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().protocolReviewNotRequired(protocolForm);
            forward = mapping.findForward(forwardTo);
        }
        return forward;
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
    @SuppressWarnings("deprecation")
    public ActionForward grantFullApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
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
        if(getProtocolActionRequestService().isGrantAdminApprovalAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().grantAdminApproval(protocolForm);
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
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        String taskName = getTaskName(request);
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
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
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        if(getProtocolActionRequestService().isWithdrawRequestActionAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().withdrawRequestAction(protocolForm);
            forward = mapping.findForward(forwardTo);
        }
        return forward;
    }
    

    public ActionForward addRequestAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
            throws Exception {
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
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        if(getProtocolActionRequestService().isDisapproveProtocolAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().disapproveProtocol(protocolForm);
            forward = mapping.findForward(forwardTo);
        }
        return forward;
    }
    
    
    public ActionForward expire(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        if(getProtocolActionRequestService().isExpireProtocolAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().expireProtocol(protocolForm);
            forward = mapping.findForward(forwardTo);
        }
        return forward;
    }
    
 public ActionForward terminate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
     ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
     IacucProtocolForm protocolForm = (IacucProtocolForm) form;
     if(getProtocolActionRequestService().isTerminateProtocolAuthorized(protocolForm)) {
         String forwardTo = getProtocolActionRequestService().terminateProtocol(protocolForm);
         forward = mapping.findForward(forwardTo);
     }
     return forward;
  }
 
 public ActionForward suspend(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
     ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
     IacucProtocolForm protocolForm = (IacucProtocolForm) form;
     if(getProtocolActionRequestService().isSuspendProtocolAuthorized(protocolForm)) {
         String forwardTo = getProtocolActionRequestService().suspendProtocol(protocolForm);
         forward = mapping.findForward(forwardTo);
     }
     return forward;
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
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        if(getProtocolActionRequestService().isAcknowledgementAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().acknowledgement(protocolForm);
            forward = mapping.findForward(forwardTo);
        }
        return forward;
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
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        if(getProtocolActionRequestService().isHoldAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().hold(protocolForm);
            forward = mapping.findForward(forwardTo);
        }
        return forward;
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
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        if(getProtocolActionRequestService().isLiftHoldAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().liftHold(protocolForm);
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
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        if(getProtocolActionRequestService().isReturnForSMRAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().returnForSMR(protocolForm);
            loadDocument(protocolForm);
            protocolForm.getProtocolHelper().prepareView();
            forward = mapping.findForward(forwardTo);
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
        if(getProtocolActionRequestService().isReturnForSRRAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().returnForSRR(protocolForm);
            loadDocument(protocolForm);
            protocolForm.getProtocolHelper().prepareView();
            forward = mapping.findForward(forwardTo);
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
        if(getProtocolActionRequestService().isReturnToPIAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().returnToPI(protocolForm);
            loadDocument(protocolForm);
            protocolForm.getProtocolHelper().prepareView();
            forward = mapping.findForward(forwardTo);
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
        if(getProtocolActionRequestService().isDeactivateAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().deactivate(protocolForm);
            forward = mapping.findForward(forwardTo);
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
        if(getProtocolActionRequestService().isOpenProtocolForAdminCorrectionAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().openProtocolForAdminCorrection(protocolForm);
            forward = mapping.findForward(forwardTo);
        }
        return forward;
    }    
    
    public ActionForward submitCommitteeDecision(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        if(getProtocolActionRequestService().isSubmitCommitteeDecisionAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().submitCommitteeDecision(protocolForm);
            confirmFollowupAction(mapping, form, request, response, Constants.MAPPING_BASIC);
            forward = mapping.findForward(forwardTo);
        }
        return forward;
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
        return KcServiceLocator.getService(IacucProtocolPrintingService.class);
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
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        if(getProtocolActionRequestService().isAbandonAuthorized(protocolForm)) {
            String forwardTo = getProtocolActionRequestService().abandon(protocolForm);
            forward = mapping.findForward(forwardTo);
        }
        return forward;
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
    
    private IacucProtocolAttachmentService getProtocolAttachmentService() {
        return KcServiceLocator.getService(IacucProtocolAttachmentService.class);
    }
    
    private TaskAuthorizationService getTaskAuthorizationService() {
        return KcServiceLocator.getService(TaskAuthorizationService.class);
    }
    
    public IacucProtocolCopyService getIacucProtocolCopyService() {
        return KcServiceLocator.getService(IacucProtocolCopyService.class);
    }
    
    private IacucProtocolAmendRenewService getProtocolAmendRenewService() {
        return KcServiceLocator.getService(IacucProtocolAmendRenewService.class);
    }
    
    private IacucProtocolDeleteService getProtocolDeleteService() {
        return KcServiceLocator.getService(IacucProtocolDeleteService.class);
    }
    
    private IacucReviewCommentsService getReviewCommentsService() {
        return KcServiceLocator.getService(IacucReviewCommentsService.class);
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

    private IacucFollowupActionService getFollowupActionService() {
        return KcServiceLocator.getService(IacucFollowupActionService.class);
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
        return getProtocolActionRequestService().hasDocumentStateChanged(protocolForm);
    }
    
    private ActionForward checkToSendNotification(ActionMapping mapping, String forwardName, IacucProtocolForm protocolForm, IacucProtocolNotificationRequestBean notificationRequestBean) {
        boolean sendNotification = getProtocolActionRequestService().checkToSendNotification(protocolForm, notificationRequestBean, forwardName); 
        return sendNotification ? mapping.findForward(IacucConstants.NOTIFICATION_EDITOR) : mapping.findForward(forwardName);
    }    
    
    public ActionForward sendNotification(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocol protocol = (IacucProtocol)protocolForm.getProtocolDocument().getProtocol();
        
        IacucProtocolNotificationRenderer renderer = new IacucProtocolNotificationRenderer(protocol);
        IacucProtocolNotificationContext context = new IacucProtocolNotificationContext(protocol, NotificationType.AD_HOC_NOTIFICATION_TYPE, NotificationType.AD_HOC_CONTEXT, renderer);
        
        protocolForm.getNotificationHelper().initializeDefaultValues(context);
        
        return mapping.findForward(IacucConstants.NOTIFICATION_EDITOR);
    }
    
    
    protected KcNotificationService getNotificationService() {
        return KcServiceLocator.getService(KcNotificationService.class);
    }
         
    public ActionForward viewCorrespondence(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        IacucActionHelper actionHelper = (IacucActionHelper) ((ProtocolFormBase) form).getActionHelper();
        PrintableAttachment source = new PrintableAttachment();
        ProtocolCorrespondence correspondence = actionHelper.getProtocolCorrespondence();
            
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

    @SuppressWarnings("deprecation")
    private ActionForward correspondenceAction(ActionMapping mapping, ActionForm form, boolean saveAction) {
        IacucProtocolForm protocolForm = ((IacucProtocolForm) form);
        IacucActionHelper actionHelper = (IacucActionHelper) protocolForm.getActionHelper();
        ProtocolCorrespondence correspondence = actionHelper.getProtocolCorrespondence();

        if (saveAction) {
            if (correspondence.getFinalFlag()) {
                correspondence.setFinalFlagTimestamp(KcServiceLocator.getService(DateTimeService.class).getCurrentTimestamp());
                           
            }
            getBusinessObjectService().save(correspondence);
        }
        if (GlobalVariables.getUserSession().retrieveObject("approvalComplCorrespondence") != null) {
               GlobalVariables.getUserSession().addObject(DocumentAuthorizerBase.USER_SESSION_METHOD_TO_CALL_COMPLETE_OBJECT_KEY, GlobalVariables.getUserSession().retrieveObject("approvalComplCorrespondence"));
               GlobalVariables.getUserSession().removeObject("approvalComplCorrespondence");
        }

        if (correspondence.getNotificationRequestBean() != null) {
            return checkToSendNotification(mapping, correspondence.getForwardName(), protocolForm,
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

            return mapping.findForward(Constants.MAPPING_BASIC);
        }

        AttachmentDataSource dataSource = generateCorrespondenceDocument(protocol, protocolCorrespondence);
        PrintableAttachment source = new PrintableAttachment();
        if (dataSource != null) {
            protocolCorrespondence.setCorrespondence(dataSource.getData());
            protocolCorrespondence.setFinalFlag(false);
            protocolCorrespondence.setCreateUser(GlobalVariables.getUserSession().getPrincipalName());
            protocolCorrespondence.setCreateTimestamp(KcServiceLocator.getService(DateTimeService.class).getCurrentTimestamp());
            protocolCorrespondence.setForwardName(IacucConstants.PROTOCOL_ACTIONS_TAB);
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
        return KcServiceLocator.getService(IacucProtocolActionCorrespondenceGenerationService.class);
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

            return mapping.findForward(Constants.MAPPING_BASIC);
        }
        protocolCorrespondence.setForwardName(IacucConstants.PROTOCOL_ACTIONS_TAB);
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
        if (dataStream.getData() != null) {
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
            if (dataStream.getData() != null) {
                PrintingUtils.streamToResponse(dataStream, response);
                forward = null;
            }
        }
        return forward;
    }

    private IacucProtocolPrintingService getIacucProtocolPrintingService() {
        return KcServiceLocator.getService(IacucProtocolPrintingService.class);
    }
    
    public ActionForward sendReviewDeterminationNotificationAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        String forwardTo = getProtocolActionRequestService().sendReviewDeterminationNotificationAction(protocolForm);
        forward = mapping.findForward(forwardTo);
        return forward;
    }
    
    public ActionForward modifySubmissionAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        List<ProtocolReviewerBeanBase> reviewers = getReviewers(request, "iacucProtocolModifySubmissionBean");
        if(getProtocolActionRequestService().isModifySubmissionActionAuthorized(protocolForm, reviewers)) {
            String forwardTo = getProtocolActionRequestService().modifySubmissionAction(protocolForm, reviewers);
            GlobalVariables.getMessageMap().getWarningMessages().clear();
            forward = mapping.findForward(forwardTo);
        }
        return forward;
    }

    protected List<ProtocolReviewerBeanBase> getReviewers(HttpServletRequest request, String beanName) {
        String reviewerBean = ACTION_HELPER + beanName + REVIEWER_ATTRIBUTE;
        String numberOfReviewersParam = ACTION_HELPER + beanName + NUMBER_OF_REVIEWERS_ATTRIBUTE;
        int number = Integer.parseInt(request.getParameter(numberOfReviewersParam));
        List<ProtocolReviewerBeanBase> beans = new ArrayList<>();
        
        for (int i= 0; i < number; i++) {
            String reviewerTypeCode = request.getParameter(reviewerBean+i+ REVIEWER_TYPE_CODE_ATTRIBUTE);
            String personId = request.getParameter(reviewerBean + i + PERSON_ID_ATTRIBUTE);
            String fullName = request.getParameter(reviewerBean+i+ FULL_NAME_ATTRIBUTE);
            String nonEmployeeFlag = request.getParameter(reviewerBean+i+ NON_EMPLOYEE_FLAG_ATTRIBUTE);

            if (ObjectUtils.isNotNull(personId)) {
                IacucProtocolReviewerBean bean = new IacucProtocolReviewerBean();
                bean.setFullName(fullName); 
                bean.setPersonId(personId); 
                bean.setReviewerTypeCode(reviewerTypeCode);
                bean.setActionFlag(IacucProtocolReviewerBean.CREATE);
                bean.setNonEmployeeFlag(nonEmployeeFlag.equalsIgnoreCase(TRUE_FLAG));
                beans.add(bean);
            }
        }
        return beans;
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
         ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
         IacucProtocolForm protocolForm = (IacucProtocolForm) form;
         if(getProtocolActionRequestService().isTableProtocolAuthorized(protocolForm)) {
             String forwardTo = getProtocolActionRequestService().tableProtocol(protocolForm);
             loadDocument(protocolForm);
             protocolForm.getProtocolHelper().prepareView();
             forward = mapping.findForward(forwardTo);
         }
         return forward;
     }
    
    public ActionForward undoLastAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        
        if (!hasDocumentStateChanged(protocolForm)) {
            IacucProtocolDocument protocolDocument = (IacucProtocolDocument) protocolForm.getProtocolDocument();
            UndoLastActionBean undoLastActionBean = protocolForm.getActionHelper().getUndoLastActionBean();
            String lastActionType = undoLastActionBean.getLastAction().getProtocolActionTypeCode();
            
            IacucProtocolUndoLastActionService undoLastActionService = KcServiceLocator.getService(IacucProtocolUndoLastActionService.class);
            ProtocolDocumentBase updatedDocument = undoLastActionService.undoLastAction(protocolDocument, undoLastActionBean);
                       
    
            recordProtocolActionSuccess("Undo Last Action");
    
            if (!updatedDocument.getDocumentNumber().equals(protocolForm.getDocId())) {
                protocolForm.setDocId(updatedDocument.getDocumentNumber());
                loadDocument(protocolForm);
                protocolForm.getProtocolHelper().prepareView();
                return mapping.findForward(IacucConstants.PROTOCOL_TAB);
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
