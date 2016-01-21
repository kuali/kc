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
package org.kuali.kra.iacuc.onlinereview;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipBase;
import org.kuali.coeus.common.committee.impl.meeting.CommitteeScheduleMinuteBase;
import org.kuali.coeus.common.committee.impl.meeting.MinuteEntryType;
import org.kuali.coeus.common.framework.attachment.AttachmentFile;
import org.kuali.coeus.sys.framework.controller.KcHoldingPageConstants;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.util.DateUtils;
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolAction;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.reviewcomments.IacucReviewAttachmentsBean;
import org.kuali.kra.iacuc.actions.reviewcomments.IacucReviewCommentsService;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolReviewerBean;
import org.kuali.kra.iacuc.committee.bo.IacucCommitteeMembership;
import org.kuali.kra.iacuc.committee.meeting.IacucCommitteeScheduleMinute;
import org.kuali.kra.iacuc.notification.*;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolFormBase;
import org.kuali.kra.protocol.ProtocolOnlineReviewDocumentBase;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewAttachmentsBeanBase;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsBeanBase;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsService;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewer;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewerBeanBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.onlinereview.OnlineReviewsActionHelperBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewBase;
import org.kuali.kra.protocol.onlinereview.ProtocolReviewAttachmentBase;
import org.kuali.kra.protocol.onlinereview.event.*;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.krad.bo.Note;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.KRADUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IacucProtocolOnlineReviewAction extends IacucProtocolAction {
    private static final String PROTOCOL_DOCUMENT_NUMBER="protocolDocumentNumber";
    private static final Log LOG = LogFactory.getLog(IacucProtocolOnlineReviewAction.class);

    private static final String NOT_FOUND_SELECTION = "the attachment was not found for selection ";
    private static final ActionForward RESPONSE_ALREADY_HANDLED = null;
    private static final String PROTOCOL_OLR_TAB = "iacucProtocolOnlineReview";
    private static final String DOCUMENT_REJECT_QUESTION="DocReject";
    private static final String DOCUMENT_DELETE_QUESTION="ProtocolDocDelete";
    private static final String DOCUMENT_REJECT_REASON_MAXLENGTH = "2000";
    private static final String ERROR_DOCUMENT_DELETE_REASON_REQUIRED = "You must enter a reason for this deletion.  The reason must be no more than {0} characters long.";
   
    
    public ActionForward createOnlineReview(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        OnlineReviewsActionHelperBase onlineReviewHelper = protocolForm.getOnlineReviewsActionHelper();

        if (validateCreateNewProtocolOnlineReview(protocolForm)) {
            CommitteeMembershipBase membership
                = getBusinessObjectService().findBySinglePrimaryKey(IacucCommitteeMembership.class, onlineReviewHelper.getNewProtocolReviewCommitteeMembershipId());
            ProtocolReviewerBeanBase bean = new IacucProtocolReviewerBean(membership);
            
            String principalId = bean.getPersonId();
            boolean nonEmployeeFlag = bean.getNonEmployeeFlag();
            String reviewerTypeCode = onlineReviewHelper.getNewReviewerTypeCode();
            ProtocolSubmissionBase submission = protocolForm.getProtocolDocument().getProtocol().getProtocolSubmission();
            ProtocolReviewer reviewer = getProtocolOnlineReviewService().createProtocolReviewer(principalId, nonEmployeeFlag, reviewerTypeCode, submission);
            
            ProtocolOnlineReviewDocumentBase document = getProtocolOnlineReviewService().createAndRouteProtocolOnlineReviewDocument(submission, reviewer, 
                    onlineReviewHelper.getNewReviewDocumentDescription(), onlineReviewHelper.getNewReviewExplanation(), 
                    onlineReviewHelper.getNewReviewOrganizationDocumentNumber(), null, true, onlineReviewHelper.getNewReviewDateRequested(), 
                    onlineReviewHelper.getNewReviewDateDue(), GlobalVariables.getUserSession().getPrincipalId());

            protocolForm.getOnlineReviewsActionHelper().init(true);
            recordOnlineReviewActionSuccess("created", document);
            
            //send notification now that the online review has been created.
            ProtocolBase protocol = submission.getProtocol();
            ProtocolOnlineReviewBase protocolOnlineReview = document.getProtocolOnlineReview();
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    protected void recordOnlineReviewActionSuccess(String onlineReviewActionName, ProtocolOnlineReviewDocumentBase document) {
        String documentInfo = String.format("document number:%s, reviewer:%s", document.getDocumentNumber(), document.getProtocolOnlineReview().getProtocolReviewer().getFullName());
        KNSGlobalVariables.getMessageList().add(KeyConstants.MESSAGE_ONLINE_REVIEW_ACTION_SUCCESSFULLY_COMPLETED,onlineReviewActionName, documentInfo);
    }

    public ActionForward startProtocolOnlineReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Map<String, String> fieldValues = new HashMap<String, String>();
        String protocolDocumentNumber = request.getParameter(PROTOCOL_DOCUMENT_NUMBER);
        ((ProtocolFormBase) form).setDocument(getDocumentService().getByDocumentHeaderId(
                protocolDocumentNumber));
        ((ProtocolFormBase) form).initialize();
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    

    public ActionForward addOnlineReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        OnlineReviewsActionHelperBase actionHelper = protocolForm.getOnlineReviewsActionHelper();
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        String documentNumber = getOnlineReviewActionDocumentNumber(parameterName, "addOnlineReviewComment");
        
        ProtocolOnlineReviewDocumentBase document = actionHelper.getDocumentFromHelperMap(documentNumber);
        ReviewCommentsBeanBase reviewCommentsBean = actionHelper.getReviewCommentsBeanFromHelperMap(documentNumber);
        long documentIndex = actionHelper.getIndexByDocumentNumber(documentNumber);
        
        if (applyRules(new AddProtocolOnlineReviewCommentEvent(document, reviewCommentsBean.getNewReviewComment(), documentIndex))
                && applyRules(new SaveProtocolOnlineReviewEvent(document, reviewCommentsBean.getReviewComments(), documentIndex))) {
            CommitteeScheduleMinuteBase newReviewComment = reviewCommentsBean.getNewReviewComment();
            List<CommitteeScheduleMinuteBase> reviewComments = reviewCommentsBean.getReviewComments();
            List<CommitteeScheduleMinuteBase> deletedReviewComments = reviewCommentsBean.getDeletedReviewComments();
            if (protocolForm.getEditingMode().get(TaskName.MAINTAIN_IACUC_PROTOCOL_ONLINEREVIEWS) == null) {
                newReviewComment.setPrivateCommentFlag(true);
                newReviewComment.setFinalFlag(false);
            }
            newReviewComment.setMinuteEntryTypeCode(MinuteEntryType.PROTOCOL_REVIEWER_COMMENT);
            newReviewComment.setReadOnly(false);
            getReviewCommentsService().addReviewComment(newReviewComment, reviewComments, document.getProtocolOnlineReview());
            getReviewCommentsService().saveReviewComments(reviewComments, deletedReviewComments);
            getDocumentService().saveDocument(document);
            
            reviewCommentsBean.setNewReviewComment(new IacucCommitteeScheduleMinute(MinuteEntryType.PROTOCOL_REVIEWER_COMMENT));
        }
        
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }    

    protected String getOnlineReviewActionDocumentNumber(String parameterName, String actionMethodToCall) {
        
        String idxStr = null;
        if (StringUtils.isBlank(parameterName)||parameterName.indexOf("."+actionMethodToCall+".") == -1) {
            throw new IllegalArgumentException(
                    String.format("getOnlineReviewActionIndex expects a non-empty value for parameterName parameter, "+
                            "and it must contain as a substring the parameter actionMethodToCall. "+
                            "The passed values were (%s,%s)."
                            ,parameterName,actionMethodToCall)
                    );
        }
        idxStr = StringUtils.substringBetween(parameterName, "."+actionMethodToCall+".", "." );
        if( idxStr == null || StringUtils.isBlank(idxStr)) {
            throw new IllegalArgumentException(String.format( 
                    "parameterName must be of the form '.(actionMethodToCall).(index).anchor, "+
                    "the passed values were (%s,%s)"
                    ,parameterName,actionMethodToCall
                    ));
        }
        
        return idxStr;
    }

    private ReviewCommentsService getReviewCommentsService() {
        return KcServiceLocator.getService(IacucReviewCommentsService.class);
    }

    public ActionForward deleteOnlineReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        OnlineReviewsActionHelperBase actionHelper = protocolForm.getOnlineReviewsActionHelper();
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        String documentNumber = getOnlineReviewActionDocumentNumber(parameterName, "deleteOnlineReviewComment");
        
        ProtocolOnlineReviewDocumentBase document = actionHelper.getDocumentFromHelperMap(documentNumber);
        ReviewCommentsBeanBase reviewCommentsBean = actionHelper.getReviewCommentsBeanFromHelperMap(documentNumber);
        long documentIndex = actionHelper.getIndexByDocumentNumber(documentNumber);
        int commentIndex = getOnlineReviewActionIndexNumber(parameterName, "deleteOnlineReviewComment");
                
        if (applyRules(new SaveProtocolOnlineReviewEvent(document, reviewCommentsBean.getReviewComments(), documentIndex))) {
            List<CommitteeScheduleMinuteBase> reviewComments = reviewCommentsBean.getReviewComments();
            List<CommitteeScheduleMinuteBase> deletedReviewComments = reviewCommentsBean.getDeletedReviewComments();
            
            getReviewCommentsService().deleteReviewComment(reviewComments, commentIndex, deletedReviewComments);
            getReviewCommentsService().saveReviewComments(reviewComments, deletedReviewComments);
            getDocumentService().saveDocument(document);
        }
        
        protocolForm.getOnlineReviewsActionHelper().init(true);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }    

    protected int getOnlineReviewActionIndexNumber(String parameterName, String actionMethodToCall) {
        int result = -1;
        if (StringUtils.isBlank(parameterName)||parameterName.indexOf("."+actionMethodToCall+".") == -1) {
            throw new IllegalArgumentException(
                    String.format("getOnlineReviewActionIndex expects a non-empty value for parameterName parameter, "+
                            "and it must contain as a substring the parameter actionMethodToCall. "+
                            "The passed values were (%s,%s)."
                            ,parameterName,actionMethodToCall)
                    );
        }
        String idxNmbr = StringUtils.substringBetween(parameterName, ".line.", ".anchor");
        result = Integer.parseInt(idxNmbr);
        return result;
    }

    public ActionForward addOnlineReviewAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        OnlineReviewsActionHelperBase actionHelper = protocolForm.getOnlineReviewsActionHelper();
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        String documentNumber = getOnlineReviewActionDocumentNumber(parameterName, "addOnlineReviewAttachment");
        
        ProtocolOnlineReviewDocumentBase document = actionHelper.getDocumentFromHelperMap(documentNumber);
        ReviewAttachmentsBeanBase reviewAttachmentsBean = actionHelper.getReviewAttachmentsBeanFromHelperMap(documentNumber);
        long documentIndex = actionHelper.getIndexByDocumentNumber(documentNumber);
        
        if (applyRules(new AddProtocolOnlineReviewAttachmentEvent(document, reviewAttachmentsBean.getErrorPropertyName()+"s["+documentIndex+"].", reviewAttachmentsBean.getNewReviewAttachment()))) {
            ProtocolReviewAttachmentBase newReviewAttachment = reviewAttachmentsBean.getNewReviewAttachment();
            List<ProtocolReviewAttachmentBase> reviewAttachments = reviewAttachmentsBean.getReviewAttachments();
            List<ProtocolReviewAttachmentBase> deletedReviewAttachments = reviewAttachmentsBean.getDeletedReviewAttachments();
            if (protocolForm.getEditingMode().get(TaskName.MAINTAIN_PROTOCOL_ONLINEREVIEWS) == null) {
                newReviewAttachment.setProtocolPersonCanView(false);
            }
            getReviewCommentsService().addReviewAttachment(newReviewAttachment, reviewAttachments, document.getProtocolOnlineReview().getProtocol());
            getReviewCommentsService().saveReviewAttachments(reviewAttachments, deletedReviewAttachments);
            getDocumentService().saveDocument(document);
            
            reviewAttachmentsBean.setNewReviewAttachment(new IacucProtocolReviewAttachment());
        }
        
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }    

    public ActionForward deleteOnlineReviewAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        OnlineReviewsActionHelperBase actionHelper = protocolForm.getOnlineReviewsActionHelper();
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        String documentNumber = getOnlineReviewActionDocumentNumber(parameterName, "deleteOnlineReviewAttachment");

        ProtocolOnlineReviewDocumentBase document = actionHelper.getDocumentFromHelperMap(documentNumber);
        ReviewAttachmentsBeanBase reviewCommentsBean = actionHelper.getReviewAttachmentsBeanFromHelperMap(documentNumber);
        long documentIndex = actionHelper.getIndexByDocumentNumber(documentNumber);
        int attachmentIndex = getOnlineReviewActionIndexNumber(parameterName, "deleteOnlineReviewAttachment");

        if (applyRules(new SaveProtocolOnlineReviewEvent(document, actionHelper.getReviewCommentsBeanFromHelperMap(documentNumber)
                .getReviewComments(), documentIndex))) {
            List<ProtocolReviewAttachmentBase> reviewAttachments = document.getProtocolOnlineReview().getReviewAttachments();
            List<ProtocolReviewAttachmentBase> deletedReviewAttachments = reviewCommentsBean.getDeletedReviewAttachments();

            getReviewCommentsService().deleteReviewAttachment(reviewAttachments, attachmentIndex, deletedReviewAttachments);
            getReviewCommentsService().saveReviewAttachments(reviewAttachments, deletedReviewAttachments);
            actionHelper.getReviewAttachmentsBeanFromHelperMap(documentNumber).setReviewAttachments(reviewAttachments);
            getDocumentService().saveDocument(document);
        }

        protocolForm.getOnlineReviewsActionHelper().init(true);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
        
    private boolean validateCreateNewProtocolOnlineReview(ProtocolFormBase protocolForm) {
        boolean valid = true;
        
        if (protocolForm.getOnlineReviewsActionHelper().getNewProtocolReviewCommitteeMembershipId()==null) {
            valid = false;
            GlobalVariables.getMessageMap().putError("onlineReviewsActionHelper.newProtocolReviewCommitteeMembershipId", "error.protocol.onlinereview.create.requiresReviewer", new String[0]);
        }
        
        if( protocolForm.getOnlineReviewsActionHelper().getNewReviewDateRequested() != null && protocolForm.getOnlineReviewsActionHelper().getNewReviewDateDue() != null ) {            
            if ( (DateUtils.isSameDay(protocolForm.getOnlineReviewsActionHelper().getNewReviewDateDue(), protocolForm.getOnlineReviewsActionHelper().getNewReviewDateRequested())) || 
                 (protocolForm.getOnlineReviewsActionHelper().getNewReviewDateDue().after(protocolForm.getOnlineReviewsActionHelper().getNewReviewDateRequested())) ) {
               //no-op,
            }
            else
            {   //dates are not the same or due date is before requested date
                valid=false;
                GlobalVariables.getMessageMap().putError("onlineReviewsActionHelper.newReviewDateDue", "error.protocol.onlinereview.create.dueDateAfterRequestedDate", new String[0]);
            }
        }
        
        if( StringUtils.isEmpty(protocolForm.getOnlineReviewsActionHelper().getNewReviewerTypeCode())) {
            valid=false;
            GlobalVariables.getMessageMap().putError("onlineReviewsActionHelper.newReviewerTypeCode", "error.protocol.onlinereview.create.protocolReviewerTypeCode", new String[0]);
        }
        
        return valid;        
    }

    public ActionForward moveUpOnlineReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        OnlineReviewsActionHelperBase actionHelper = protocolForm.getOnlineReviewsActionHelper();
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        String documentNumber = getOnlineReviewActionDocumentNumber(parameterName, "moveUpOnlineReviewComment");
        
        ProtocolOnlineReviewDocumentBase document = actionHelper.getDocumentFromHelperMap(documentNumber);
        ReviewCommentsBeanBase reviewCommentsBean = actionHelper.getReviewCommentsBeanFromHelperMap(documentNumber);
        long documentIndex = actionHelper.getIndexByDocumentNumber(documentNumber);
        int commentIndex = getOnlineReviewActionIndexNumber(parameterName, "moveUpOnlineReviewComment");
        
        if (applyRules(new SaveProtocolOnlineReviewEvent(document, reviewCommentsBean.getReviewComments(), documentIndex))) {
            ProtocolBase protocol = protocolForm.getProtocolDocument().getProtocol();
            List<CommitteeScheduleMinuteBase> reviewComments = reviewCommentsBean.getReviewComments();
            List<CommitteeScheduleMinuteBase> deletedReviewComments = reviewCommentsBean.getDeletedReviewComments();
            
            getReviewCommentsService().moveUpReviewComment(reviewComments, protocol, commentIndex);
            getReviewCommentsService().saveReviewComments(reviewComments, deletedReviewComments);
            getDocumentService().saveDocument(document);
        }
        
        protocolForm.getOnlineReviewsActionHelper().init(true); 
        return mapping.findForward(Constants.MAPPING_BASIC);
    }    

    /**
     * 
     * @param mapping the mapping associated with this action.
     * @param form the ProtocolBase form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the name of the HTML page to display
     * @throws Exception doesn't ever really happen
     */
    public ActionForward moveDownOnlineReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        OnlineReviewsActionHelperBase actionHelper = protocolForm.getOnlineReviewsActionHelper();
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        String documentNumber = getOnlineReviewActionDocumentNumber(parameterName, "moveDownOnlineReviewComment");
        
        ProtocolOnlineReviewDocumentBase document = actionHelper.getDocumentFromHelperMap(documentNumber);
        ReviewCommentsBeanBase reviewCommentsBean = actionHelper.getReviewCommentsBeanFromHelperMap(documentNumber);
        long documentIndex = actionHelper.getIndexByDocumentNumber(documentNumber);
        int commentIndex = getOnlineReviewActionIndexNumber(parameterName, "moveDownOnlineReviewComment");
              
        if (applyRules(new SaveProtocolOnlineReviewEvent(document, reviewCommentsBean.getReviewComments(), documentIndex))) {
            ProtocolBase protocol = protocolForm.getProtocolDocument().getProtocol();
            List<CommitteeScheduleMinuteBase> reviewComments = reviewCommentsBean.getReviewComments();
            List<CommitteeScheduleMinuteBase> deletedReviewComments = reviewCommentsBean.getDeletedReviewComments();
            
            getReviewCommentsService().moveDownReviewComment(reviewComments, protocol, commentIndex);
            getReviewCommentsService().saveReviewComments(reviewComments, deletedReviewComments);
            getDocumentService().saveDocument(document);
        }
        
        protocolForm.getOnlineReviewsActionHelper().init(true);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }    

    public ActionForward viewOnlineReviewAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        OnlineReviewsActionHelperBase actionHelper = protocolForm.getOnlineReviewsActionHelper();
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        String documentNumber = getOnlineReviewActionDocumentNumber(parameterName, "viewOnlineReviewAttachment");
        
        IacucReviewAttachmentsBean reviewAttachmentsBean = (IacucReviewAttachmentsBean) actionHelper.getReviewAttachmentsBeanFromHelperMap(documentNumber);
        int attachmentIndex = getOnlineReviewActionIndexNumber(parameterName, "viewOnlineReviewAttachment");
        final IacucProtocolReviewAttachment attachment = reviewAttachmentsBean.getReviewAttachments().get(attachmentIndex);
        
        if (attachment == null) {
            LOG.info(NOT_FOUND_SELECTION + attachmentIndex);
            //may want to tell the user the selection was invalid.
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
        
        final AttachmentFile file = attachment.getFile();
        this.streamToResponse(file.getData(), getValidHeaderString(file.getName()),  getValidHeaderString(file.getType()), response);
        
        return RESPONSE_ALREADY_HANDLED;
    }

    public ActionForward saveOnlineReview(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String onlineReviewDocumentNumber = getOnlineReviewActionDocumentNumber(
                (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE),
                "saveOnlineReview");
        DocumentService documentService = KcServiceLocator.getService(DocumentService.class);
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        ProtocolOnlineReviewDocumentBase prDoc = protocolForm.getOnlineReviewsActionHelper().getDocumentFromHelperMap(onlineReviewDocumentNumber);
        ReviewCommentsBeanBase reviewCommentsBean = protocolForm.getOnlineReviewsActionHelper().getReviewCommentsBeanFromHelperMap(onlineReviewDocumentNumber);
        ReviewAttachmentsBeanBase reviewAttachmentsBean = protocolForm.getOnlineReviewsActionHelper().getReviewAttachmentsBeanFromHelperMap(onlineReviewDocumentNumber);
        if ( !this.applyRules(new SaveProtocolOnlineReviewEvent(prDoc, reviewCommentsBean.getReviewComments(), protocolForm.getOnlineReviewsActionHelper().getIndexByDocumentNumber(onlineReviewDocumentNumber)))) {
            //nothing to do, we failed validation return them to the screen.
        } else {
            ProtocolReviewer reviewer = prDoc.getProtocolOnlineReview().getProtocolReviewer();
            
            getReviewCommentsService().saveReviewComments(reviewCommentsBean.getReviewComments(), reviewCommentsBean.getDeletedReviewComments());
            getReviewCommentsService().saveReviewAttachments(reviewAttachmentsBean.getReviewAttachments(), reviewAttachmentsBean.getDeletedReviewAttachments());
            getBusinessObjectService().save(reviewer);
            documentService.saveDocument(prDoc);
            recordOnlineReviewActionSuccess("saved", prDoc);
            protocolForm.getOnlineReviewsActionHelper().init(true);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
        
    }

    public ActionForward approveOnlineReview(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        String onlineReviewDocumentNumber = getOnlineReviewActionDocumentNumber(
                (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE),
                "approveOnlineReview");
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        ProtocolOnlineReviewDocumentBase prDoc = protocolForm.getOnlineReviewsActionHelper().getDocumentFromHelperMap(onlineReviewDocumentNumber);
        ReviewCommentsBeanBase reviewCommentsBean = protocolForm.getOnlineReviewsActionHelper().getReviewCommentsBeanFromHelperMap(onlineReviewDocumentNumber);
        ReviewAttachmentsBeanBase reviewAttachmentsBean = protocolForm.getOnlineReviewsActionHelper().getReviewAttachmentsBeanFromHelperMap(onlineReviewDocumentNumber);
        boolean isApproveReview = StringUtils.equals(IacucProtocolOnlineReviewStatus.SAVED_STATUS_CD, prDoc.getProtocolOnlineReview().getProtocolOnlineReviewStatusCode());
        //check to see if we are the reviewer and this is an approval to the irb admin.
        
        boolean validComments = applyRules(new RouteProtocolOnlineReviewEvent(prDoc, reviewCommentsBean.getReviewComments(), protocolForm.getOnlineReviewsActionHelper().getIndexByDocumentNumber(onlineReviewDocumentNumber)));
        boolean statusIsOk = false;
        
        if( validComments && getKraWorkflowService().isUserApprovalRequested(prDoc, GlobalVariables.getUserSession().getPrincipalId())) {
            //then the status must be final.
                prDoc.getProtocolOnlineReview().setProtocolOnlineReviewStatusCode(IacucProtocolOnlineReviewStatus.FINAL_STATUS_CD);
                prDoc.getProtocolOnlineReview().setReviewerApproved(true);
                if (getKraWorkflowService().isDocumentOnNode(prDoc, Constants.IACUC_ONLINE_REVIEW_ROUTE_NODE_ADMIN_REVIEW)) {
                    prDoc.getProtocolOnlineReview().setAdminAccepted(true);
                    setOnlineReviewCommentFinalFlags(prDoc.getProtocolOnlineReview(), true);
                }
                getBusinessObjectService().save(prDoc.getProtocolOnlineReview());
                getDocumentService().saveDocument(prDoc);
                statusIsOk = true;
        }
        
        if (!validComments || !statusIsOk) {
            //nothing to do here.
        } else {
            getReviewCommentsService().saveReviewComments(reviewCommentsBean.getReviewComments(), reviewCommentsBean.getDeletedReviewComments());
            getReviewCommentsService().saveReviewAttachments(reviewAttachmentsBean.getReviewAttachments(), reviewAttachmentsBean.getDeletedReviewAttachments());           

            prDoc.getProtocolOnlineReview().addActionPerformed("Approve");
            getDocumentService().saveDocument(prDoc);
            getDocumentService().approveDocument(prDoc, "", null);
            protocolForm.getOnlineReviewsActionHelper().init(true);
            recordOnlineReviewActionSuccess("approved", prDoc);
            
            IacucProtocol protocol = (IacucProtocol)protocolForm.getProtocolDocument().getProtocol();
            IacucProtocolOnlineReview protocolOnlineReview = (IacucProtocolOnlineReview)prDoc.getProtocolOnlineReview();
            IacucProtocolNotificationRenderer renderer = new IacucProtocolNotificationRenderer(protocol);
            IacucProtocolNotificationRequestBean notificationBean = 
                new IacucProtocolNotificationRequestBean(protocol, protocolOnlineReview, IacucProtocolActionType.REVIEW_COMPLETE, "Review Complete",  prDoc.getDocumentNumber(), "Approve");
            ActionForward forward = null;
            if (!protocolForm.getEditingMode().containsKey("maintainProtocolOnlineReviews")) {
                forward = mapping.findForward(PROTOCOL_OLR_TAB);
            }
            return checkToSendNotificationWithHoldingPage(mapping, forward, protocolForm, renderer, notificationBean);
        }                
       
        return mapping.findForward(Constants.MAPPING_BASIC);
        
    }
    
    private ActionForward checkToSendNotificationWithHoldingPage(ActionMapping mapping, ActionForward forward, IacucProtocolForm protocolForm, IacucProtocolNotificationRenderer renderer, IacucProtocolNotificationRequestBean notificationRequestBean) {

        IacucProtocolNotificationContext context = new IacucProtocolNotificationContext(notificationRequestBean.getIacucProtocol(), notificationRequestBean.getIacucProtocolOnlineReview(), notificationRequestBean.getActionType(), notificationRequestBean.getDescription(), renderer);

        if (protocolForm.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
            if (forward == null) {
                context.setForwardName("holdingPage:" + notificationRequestBean.getDocNumber() + ":" + notificationRequestBean.getOlrEvent());
            } else {
                context.setForwardName(forward.getName());
            }
            protocolForm.getNotificationHelper().initializeDefaultValues(context);
            return mapping.findForward("iacucProtocolNotificationEditor");
        } else {
            getNotificationService().sendNotificationAndPersist(context, new IacucProtocolNotification(), protocolForm.getProtocolDocument().getProtocol());
            if (forward == null) {
                return routeProtocolOLRToHoldingPage(mapping, protocolForm, notificationRequestBean.getDocNumber(), notificationRequestBean.getOlrEvent());
            } else {
                return forward;
            }
        }
    }

    private ActionForward routeProtocolOLRToHoldingPage(ActionMapping mapping, ProtocolFormBase protocolForm, String olrDocId, String olrEvent) {
        String routeHeaderId = protocolForm.getDocument().getDocumentNumber();
        String returnLocation = buildActionUrl(routeHeaderId, Constants.MAPPING_PROTOCOL_ONLINE_REVIEW , "IacucProtocolDocument");
        // use this doc id for holding action to check if online review document is complete and return to online review tab
        returnLocation += "&" + "olrDocId=" + olrDocId + "&" + "olrEvent=" + olrEvent;
        ActionForward basicForward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
        ActionRedirect holdingPageForward = new ActionRedirect(mapping.findForward(KcHoldingPageConstants.MAPPING_HOLDING_PAGE));
        holdingPageForward.addParameter(KcHoldingPageConstants.HOLDING_PAGE_DOCUMENT_ID, routeHeaderId);
        
        return routeToHoldingPage(basicForward, basicForward, holdingPageForward, returnLocation, olrDocId);

    }


    private KcWorkflowService getKraWorkflowService() {
        return KcServiceLocator.getService(KcWorkflowService.class);
    }

    private void setOnlineReviewCommentFinalFlags(ProtocolOnlineReviewBase onlineReview, boolean flagValue) {
        List<CommitteeScheduleMinuteBase> minutes = onlineReview.getCommitteeScheduleMinutes();
        for (CommitteeScheduleMinuteBase minute : minutes) {
            minute.setFinalFlag(flagValue);
        }
    }
    
    public ActionForward deleteOnlineReview(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
         
        String onlineReviewDocumentNumber = getOnlineReviewActionDocumentNumber(
                (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE),
                "deleteOnlineReview");
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        ProtocolOnlineReviewDocumentBase prDoc = (ProtocolOnlineReviewDocumentBase) protocolForm.getOnlineReviewsActionHelper()
            .getDocumentHelperMap().get(onlineReviewDocumentNumber).get("document");
        ReviewCommentsBeanBase reviewCommentsBean = protocolForm.getOnlineReviewsActionHelper().getReviewCommentsBeanFromHelperMap(onlineReviewDocumentNumber);
        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        String reason = request.getParameter(KRADConstants.QUESTION_REASON_ATTRIBUTE_NAME);
        String deleteNoteText = "";
        String callerString = String.format("disapproveOnlineReview.%s.anchor%s",prDoc.getDocumentNumber(),0);
       
        //the data gets saved here, need to validate the save ok.
        if (!this.applyRules(new SaveProtocolOnlineReviewEvent(prDoc, reviewCommentsBean.getReviewComments(), protocolForm.getOnlineReviewsActionHelper().getIndexByDocumentNumber(onlineReviewDocumentNumber)))) {
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
        
        
        // start in logic for confirming the disapproval
        if (question == null) {
            // ask question if not already asked
            return performQuestionWithInput(mapping, form, request, response, DOCUMENT_DELETE_QUESTION, "Are you sure you want to delete this document?", KRADConstants.CONFIRMATION_QUESTION, callerString, "");
        } else {
            Object buttonClicked = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
            if ((DOCUMENT_DELETE_QUESTION.equals(question)) && ConfirmationQuestion.NO.equals(buttonClicked)) {
                // if no button clicked just reload the doc
                return mapping.findForward(Constants.MAPPING_BASIC);
            } else {
                // have to check length on value entered
                String introNoteMessage = "Deletion reason -" + KRADConstants.BLANK_SPACE;

                // build out full message
                deleteNoteText = introNoteMessage + reason;

                // get note text max length from DD
                int noteTextMaxLength = getDataDictionaryService().getAttributeMaxLength(Note.class, KRADConstants.NOTE_TEXT_PROPERTY_NAME).intValue();

                if (!this.applyRules(new DeleteProtocolOnlineReviewEvent(prDoc, reason, deleteNoteText, noteTextMaxLength))) {
                    // figure out exact number of characters that the user can enter
                    int reasonLimit = noteTextMaxLength - introNoteMessage.length();

                    if (reason == null) {
                        // prevent a NPE by setting the reason to a blank string
                        reason = "";
                    }
                    return this.performQuestionWithInputAgainBecauseOfErrors(mapping, form, request, response, DOCUMENT_DELETE_QUESTION, "Are you sure you want to delete this document?", KRADConstants.CONFIRMATION_QUESTION, callerString, "", reason, ERROR_DOCUMENT_DELETE_REASON_REQUIRED, KRADConstants.QUESTION_REASON_ATTRIBUTE_NAME, new Integer(reasonLimit).toString());
                } 
                
                if (KRADUtils.containsSensitiveDataPatternMatch(deleteNoteText)) {
                    return this.performQuestionWithInputAgainBecauseOfErrors(mapping, form, request, response, 
                            DOCUMENT_DELETE_QUESTION, "Are you sure you want to delete this document?", 
                            KRADConstants.CONFIRMATION_QUESTION, callerString, "", reason, RiceKeyConstants.ERROR_DOCUMENT_FIELD_CONTAINS_POSSIBLE_SENSITIVE_DATA,
                            KRADConstants.QUESTION_REASON_ATTRIBUTE_NAME, "reason");
                } 
                
                ProtocolOnlineReviewBase protocolOnlineReview = prDoc.getProtocolOnlineReview();
                ProtocolBase protocol = protocolForm.getProtocolDocument().getProtocol();
                
                prDoc.getProtocolOnlineReview().addActionPerformed("Delete");
                KualiDocumentFormBase kualiDocumentFormBase = (KualiDocumentFormBase)protocolForm.getOnlineReviewsActionHelper().getDocumentHelperMap().get(onlineReviewDocumentNumber).get(OnlineReviewsActionHelperBase.FORM_MAP_KEY);
                doProcessingAfterPost( kualiDocumentFormBase, request );
                ProtocolOnlineReviewDocumentBase document = (ProtocolOnlineReviewDocumentBase) kualiDocumentFormBase.getDocument();
                document.getProtocolOnlineReview().setProtocolOnlineReviewStatusCode(IacucProtocolOnlineReviewStatus.REMOVED_CANCELLED_STATUS_CD);
                document.getProtocolOnlineReview().setReviewerApproved(false);
                document.getProtocolOnlineReview().setAdminAccepted(false);
                getBusinessObjectService().save(document.getProtocolOnlineReview());
                getDocumentService().disapproveDocument(document, deleteNoteText);
                KNSGlobalVariables.getMessageList().add(RiceKeyConstants.MESSAGE_ROUTE_DISAPPROVED);
                kualiDocumentFormBase.setAnnotation("");
                protocolForm.getOnlineReviewsActionHelper().init(true);
                recordOnlineReviewActionSuccess("deleted", prDoc);
             }
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward rejectOnlineReview(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        String onlineReviewDocumentNumber = getOnlineReviewActionDocumentNumber(
                (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE),
                "rejectOnlineReview");
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;        
        ProtocolOnlineReviewDocumentBase prDoc = (ProtocolOnlineReviewDocumentBase) protocolForm.getOnlineReviewsActionHelper()
            .getDocumentHelperMap().get(onlineReviewDocumentNumber).get("document");
        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        Object buttonClicked = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
        String reason = request.getParameter(KRADConstants.QUESTION_REASON_ATTRIBUTE_NAME);
        String callerString = String.format("rejectOnlineReview.%s.anchor%s",prDoc.getDocumentNumber(),0);
        if(question == null){
            return this.performQuestionWithInput(mapping, form, request, response, DOCUMENT_REJECT_QUESTION,"Are you sure you want to return this document to reviewer ?" , KRADConstants.CONFIRMATION_QUESTION, callerString, "");
         } 
        else if((DOCUMENT_REJECT_QUESTION.equals(question)) && ConfirmationQuestion.NO.equals(buttonClicked))  {
            //nothing to do.
        }
        else
        {
            if (!this.applyRules(new RejectProtocolOnlineReviewCommentEvent(prDoc, reason, new Integer(DOCUMENT_REJECT_REASON_MAXLENGTH).intValue()))) {
                if (reason == null) {
                    reason = ""; //Prevents null pointer exception in performQuestion
                }
                return this.performQuestionWithInputAgainBecauseOfErrors(mapping, form, request, response, DOCUMENT_REJECT_QUESTION, "Are you sure you want to return this document to reviewer ?", KRADConstants.CONFIRMATION_QUESTION, callerString, "", reason, KeyConstants.ERROR_ONLINE_REVIEW_REJECTED_REASON_REQUIRED, KRADConstants.QUESTION_REASON_ATTRIBUTE_NAME, DOCUMENT_REJECT_REASON_MAXLENGTH);              
            } else if (KRADUtils.containsSensitiveDataPatternMatch(reason)) {
                return this.performQuestionWithInputAgainBecauseOfErrors(mapping, form, request, response, 
                        DOCUMENT_REJECT_QUESTION, "Are you sure you want to return this document to reviewer ?", 
                        KRADConstants.CONFIRMATION_QUESTION, callerString, "", reason, RiceKeyConstants.ERROR_DOCUMENT_FIELD_CONTAINS_POSSIBLE_SENSITIVE_DATA,
                        KRADConstants.QUESTION_REASON_ATTRIBUTE_NAME, "reason");
            } else {
                prDoc.getProtocolOnlineReview().setProtocolOnlineReviewStatusCode(IacucProtocolOnlineReviewStatus.SAVED_STATUS_CD);
                prDoc.getProtocolOnlineReview().addActionPerformed("Reject");
                prDoc.getProtocolOnlineReview().setReviewerApproved(false);
                prDoc.getProtocolOnlineReview().setAdminAccepted(false);
                setOnlineReviewCommentFinalFlags(prDoc.getProtocolOnlineReview(), false);
                getDocumentService().saveDocument(prDoc);
                getProtocolOnlineReviewService().returnProtocolOnlineReviewDocumentToReviewer(prDoc,reason,GlobalVariables.getUserSession().getPrincipalId());
                
                IacucProtocol protocol = (IacucProtocol)protocolForm.getProtocolDocument().getProtocol();
                IacucProtocolOnlineReview protocolOnlineReview = (IacucProtocolOnlineReview)prDoc.getProtocolOnlineReview();
               
                protocolForm.getOnlineReviewsActionHelper().init(true);
                IacucRejectReviewNotificationRenderer renderer = new IacucRejectReviewNotificationRenderer((IacucProtocol)protocol, reason);
                IacucProtocolNotificationRequestBean notificationBean = new IacucProtocolNotificationRequestBean(protocol, protocolOnlineReview, IacucProtocolActionType.REVIEW_REJECTED, "Return to Reviewer",  prDoc.getDocumentNumber(), "Reject");               
                
                return checkToSendNotificationWithHoldingPage(mapping, null, (IacucProtocolForm)protocolForm, renderer, notificationBean);
                                            
            }
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }  
        
}
