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
package org.kuali.kra.iacuc.onlinereview;

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
import org.kuali.kra.bo.AttachmentFile;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.iacuc.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.reviewcomments.IacucReviewCommentsService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewAction;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewAttachmentsBean;
import org.kuali.kra.protocol.onlinereview.ProtocolReviewAttachment;
import org.kuali.kra.protocol.onlinereview.event.AddProtocolOnlineReviewAttachmentEvent;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.kra.meeting.MinuteEntryType;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.ProtocolForm;
import org.kuali.kra.protocol.ProtocolOnlineReviewDocument;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsBean;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsService;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewer;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewerBean;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmission;
import org.kuali.kra.protocol.onlinereview.OnlineReviewsActionHelper;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReview;
import org.kuali.kra.protocol.onlinereview.event.AddProtocolOnlineReviewCommentEvent;
import org.kuali.kra.protocol.onlinereview.event.SaveProtocolOnlineReviewEvent;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

public class IacucProtocolOnlineReviewAction extends IacucProtocolAction {
    private static final String PROTOCOL_DOCUMENT_NUMBER="protocolDocumentNumber";
    private static final Log LOG = LogFactory.getLog(IacucProtocolOnlineReviewAction.class);

    private static final String NOT_FOUND_SELECTION = "the attachment was not found for selection ";
    private static final ActionForward RESPONSE_ALREADY_HANDLED = null;
    
    
    public ActionForward createOnlineReview(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        OnlineReviewsActionHelper onlineReviewHelper = protocolForm.getOnlineReviewsActionHelper();

        if (validateCreateNewProtocolOnlineReview(protocolForm)) {
            CommitteeMembership membership
                = getBusinessObjectService().findBySinglePrimaryKey(CommitteeMembership.class, onlineReviewHelper.getNewProtocolReviewCommitteeMembershipId());
            ProtocolReviewerBean bean = new ProtocolReviewerBean(membership);
            
            String principalId = bean.getPersonId();
            boolean nonEmployeeFlag = bean.getNonEmployeeFlag();
            //String reviewerTypeCode = StringUtils.isEmpty(bean.getReviewerTypeCode()) ? ProtocolReviewerType.PRIMARY : bean.getReviewerTypeCode();
            String reviewerTypeCode = onlineReviewHelper.getNewReviewerTypeCode();
            ProtocolSubmission submission = protocolForm.getProtocolDocument().getProtocol().getProtocolSubmission();
            ProtocolReviewer reviewer = getProtocolOnlineReviewService().createProtocolReviewer(principalId, nonEmployeeFlag, reviewerTypeCode, submission);
            
            ProtocolOnlineReviewDocument document = getProtocolOnlineReviewService().createAndRouteProtocolOnlineReviewDocument(submission, reviewer, 
                    onlineReviewHelper.getNewReviewDocumentDescription(), onlineReviewHelper.getNewReviewExplanation(), 
                    onlineReviewHelper.getNewReviewOrganizationDocumentNumber(), null, true, onlineReviewHelper.getNewReviewDateRequested(), 
                    onlineReviewHelper.getNewReviewDateDue(), GlobalVariables.getUserSession().getPrincipalId());

            protocolForm.getOnlineReviewsActionHelper().init(true);
            recordOnlineReviewActionSuccess("created", document);
            
            //send notification now that the online review has been created.
            Protocol protocol = submission.getProtocol();
            ProtocolOnlineReview protocolOnlineReview = document.getProtocolOnlineReview();
            // TODO : IACUC
        //    AssignReviewerNotificationRenderer renderer = new AssignReviewerNotificationRenderer(protocol, "added");
//            IRBNotificationContext context = new IRBNotificationContext(protocol, protocolOnlineReview, ProtocolActionType.ASSIGN_REVIEWER, "Assign Reviewer", renderer);
//            getKcNotificationService().sendNotification(context);
//            return checkToSendNotification(mapping, mapping.findForward(PROTOCOL_OLR_TAB), protocolForm, renderer, new ProtocolNotificationRequestBean(protocol, protocolOnlineReview, ProtocolActionType.ASSIGN_REVIEWER, "Assign Reviewer", null, null));
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    protected void recordOnlineReviewActionSuccess(String onlineReviewActionName, ProtocolOnlineReviewDocument document) {
        String documentInfo = String.format("document number:%s, reviewer:%s", document.getDocumentNumber(), document.getProtocolOnlineReview().getProtocolReviewer().getFullName());
        KNSGlobalVariables.getMessageList().add(KeyConstants.MESSAGE_ONLINE_REVIEW_ACTION_SUCCESSFULLY_COMPLETED,onlineReviewActionName, documentInfo);
    }

    public ActionForward startProtocolOnlineReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Map<String, String> fieldValues = new HashMap<String, String>();
        String protocolDocumentNumber = request.getParameter(PROTOCOL_DOCUMENT_NUMBER);
        ((ProtocolForm) form).setDocument(getDocumentService().getByDocumentHeaderId(
                protocolDocumentNumber));
        ((ProtocolForm) form).initialize();
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    

    public ActionForward addOnlineReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ProtocolForm protocolForm = (ProtocolForm) form;
        OnlineReviewsActionHelper actionHelper = protocolForm.getOnlineReviewsActionHelper();
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        String documentNumber = getOnlineReviewActionDocumentNumber(parameterName, "addOnlineReviewComment");
        
        ProtocolOnlineReviewDocument document = actionHelper.getDocumentFromHelperMap(documentNumber);
        ReviewCommentsBean reviewCommentsBean = actionHelper.getReviewCommentsBeanFromHelperMap(documentNumber);
        long documentIndex = actionHelper.getIndexByDocumentNumber(documentNumber);
        
        if (applyRules(new AddProtocolOnlineReviewCommentEvent(document, reviewCommentsBean.getNewReviewComment(), documentIndex))
                && applyRules(new SaveProtocolOnlineReviewEvent(document, reviewCommentsBean.getReviewComments(), documentIndex))) {
            CommitteeScheduleMinute newReviewComment = reviewCommentsBean.getNewReviewComment();
            List<CommitteeScheduleMinute> reviewComments = reviewCommentsBean.getReviewComments();
            List<CommitteeScheduleMinute> deletedReviewComments = reviewCommentsBean.getDeletedReviewComments();
            if (protocolForm.getEditingMode().get(TaskName.MAINTAIN_PROTOCOL_ONLINEREVIEWS) == null) {
                newReviewComment.setPrivateCommentFlag(true);
                newReviewComment.setFinalFlag(false);
            }
            newReviewComment.setMinuteEntryTypeCode(MinuteEntryType.PROTOCOL_REVIEWER_COMMENT);
            getReviewCommentsService().addReviewComment(newReviewComment, reviewComments, document.getProtocolOnlineReview());
            getReviewCommentsService().saveReviewComments(reviewComments, deletedReviewComments);
            getDocumentService().saveDocument(document);
            
            // TODO : is there an IACUC protocol review comment ?
            reviewCommentsBean.setNewReviewComment(new CommitteeScheduleMinute(MinuteEntryType.PROTOCOL_REVIEWER_COMMENT));
        }
        
        //protocolForm.getOnlineReviewsActionHelper().init(true);
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
        return KraServiceLocator.getService(IacucReviewCommentsService.class);
    }

    public ActionForward deleteOnlineReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        OnlineReviewsActionHelper actionHelper = protocolForm.getOnlineReviewsActionHelper();
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        String documentNumber = getOnlineReviewActionDocumentNumber(parameterName, "deleteOnlineReviewComment");
        
        ProtocolOnlineReviewDocument document = actionHelper.getDocumentFromHelperMap(documentNumber);
        ReviewCommentsBean reviewCommentsBean = actionHelper.getReviewCommentsBeanFromHelperMap(documentNumber);
        long documentIndex = actionHelper.getIndexByDocumentNumber(documentNumber);
        int commentIndex = getOnlineReviewActionIndexNumber(parameterName, "deleteOnlineReviewComment");
                
        if (applyRules(new SaveProtocolOnlineReviewEvent(document, reviewCommentsBean.getReviewComments(), documentIndex))) {
            List<CommitteeScheduleMinute> reviewComments = reviewCommentsBean.getReviewComments();
            List<CommitteeScheduleMinute> deletedReviewComments = reviewCommentsBean.getDeletedReviewComments();
            
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

        ProtocolForm protocolForm = (ProtocolForm) form;
        OnlineReviewsActionHelper actionHelper = protocolForm.getOnlineReviewsActionHelper();
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        String documentNumber = getOnlineReviewActionDocumentNumber(parameterName, "addOnlineReviewAttachment");
        
        ProtocolOnlineReviewDocument document = actionHelper.getDocumentFromHelperMap(documentNumber);
        ReviewAttachmentsBean reviewAttachmentsBean = actionHelper.getReviewAttachmentsBeanFromHelperMap(documentNumber);
        long documentIndex = actionHelper.getIndexByDocumentNumber(documentNumber);
        
        if (applyRules(new AddProtocolOnlineReviewAttachmentEvent(document, reviewAttachmentsBean.getErrorPropertyName()+"s["+documentIndex+"].", reviewAttachmentsBean.getNewReviewAttachment()))) {
//                && applyRules(new SaveProtocolOnlineReviewEvent(document, reviewAttachmentsBean.getReviewAttachments(), documentIndex))) {
//                if (applyRules(new ProtocolAddReviewAttachmentEvent(document, errorPropertyName, newReviewAttachment))) {
            ProtocolReviewAttachment newReviewAttachment = reviewAttachmentsBean.getNewReviewAttachment();
            List<ProtocolReviewAttachment> reviewAttachments = reviewAttachmentsBean.getReviewAttachments();
            List<ProtocolReviewAttachment> deletedReviewAttachments = reviewAttachmentsBean.getDeletedReviewAttachments();
            if (protocolForm.getEditingMode().get(TaskName.MAINTAIN_PROTOCOL_ONLINEREVIEWS) == null) {
               // newReviewAttachment.setPrivateFlag(true);
                newReviewAttachment.setProtocolPersonCanView(false);
            }
            getReviewCommentsService().addReviewAttachment(newReviewAttachment, reviewAttachments, document.getProtocolOnlineReview().getProtocol());
 //           addReviewAttachment(newReviewAttachment, reviewAttachments, document.getProtocolOnlineReview());
            getReviewCommentsService().saveReviewAttachments(reviewAttachments, deletedReviewAttachments);
            getDocumentService().saveDocument(document);
            
            reviewAttachmentsBean.setNewReviewAttachment(new IacucProtocolReviewAttachment());
        }
        
        //protocolForm.getOnlineReviewsActionHelper().init(true);
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }    

    public ActionForward deleteOnlineReviewAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ProtocolForm protocolForm = (ProtocolForm) form;
        OnlineReviewsActionHelper actionHelper = protocolForm.getOnlineReviewsActionHelper();
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        String documentNumber = getOnlineReviewActionDocumentNumber(parameterName, "deleteOnlineReviewAttachment");

        ProtocolOnlineReviewDocument document = actionHelper.getDocumentFromHelperMap(documentNumber);
        ReviewAttachmentsBean reviewCommentsBean = actionHelper.getReviewAttachmentsBeanFromHelperMap(documentNumber);
        long documentIndex = actionHelper.getIndexByDocumentNumber(documentNumber);
        int attachmentIndex = getOnlineReviewActionIndexNumber(parameterName, "deleteOnlineReviewAttachment");

        if (applyRules(new SaveProtocolOnlineReviewEvent(document, actionHelper.getReviewCommentsBeanFromHelperMap(documentNumber)
                .getReviewComments(), documentIndex))) {
            List<ProtocolReviewAttachment> reviewAttachments = document.getProtocolOnlineReview().getReviewAttachments();
            List<ProtocolReviewAttachment> deletedReviewAttachments = reviewCommentsBean.getDeletedReviewAttachments();

            // deleteReviewAttachment(reviewAttachments, attachmentIndex, deletedReviewAttachments);
            getReviewCommentsService().deleteReviewAttachment(reviewAttachments, attachmentIndex, deletedReviewAttachments);
            getReviewCommentsService().saveReviewAttachments(reviewAttachments, deletedReviewAttachments);
            actionHelper.getReviewAttachmentsBeanFromHelperMap(documentNumber).setReviewAttachments(reviewAttachments);
            getDocumentService().saveDocument(document);
        }

        protocolForm.getOnlineReviewsActionHelper().init(true);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
        
    private boolean validateCreateNewProtocolOnlineReview(ProtocolForm protocolForm) {
        boolean valid = true;
        
        if (protocolForm.getOnlineReviewsActionHelper().getNewProtocolReviewCommitteeMembershipId()==null) {
            valid = false;
            GlobalVariables.getMessageMap().putError("onlineReviewsActionHelper.newProtocolReviewCommitteeMembershipId", "error.protocol.onlinereview.create.requiresReviewer", new String[0]);
        }
        
        if( protocolForm.getOnlineReviewsActionHelper().getNewReviewDateRequested() != null && protocolForm.getOnlineReviewsActionHelper().getNewReviewDateDue() != null ) {
            if (protocolForm.getOnlineReviewsActionHelper().getNewReviewDateDue().before(protocolForm.getOnlineReviewsActionHelper().getNewReviewDateRequested())) {
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
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        OnlineReviewsActionHelper actionHelper = protocolForm.getOnlineReviewsActionHelper();
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        String documentNumber = getOnlineReviewActionDocumentNumber(parameterName, "moveUpOnlineReviewComment");
        
        ProtocolOnlineReviewDocument document = actionHelper.getDocumentFromHelperMap(documentNumber);
        ReviewCommentsBean reviewCommentsBean = actionHelper.getReviewCommentsBeanFromHelperMap(documentNumber);
        long documentIndex = actionHelper.getIndexByDocumentNumber(documentNumber);
        int commentIndex = getOnlineReviewActionIndexNumber(parameterName, "moveUpOnlineReviewComment");
        
        if (applyRules(new SaveProtocolOnlineReviewEvent(document, reviewCommentsBean.getReviewComments(), documentIndex))) {
            Protocol protocol = protocolForm.getProtocolDocument().getProtocol();
            List<CommitteeScheduleMinute> reviewComments = reviewCommentsBean.getReviewComments();
            List<CommitteeScheduleMinute> deletedReviewComments = reviewCommentsBean.getDeletedReviewComments();
            
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
     * @param form the Protocol form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the name of the HTML page to display
     * @throws Exception doesn't ever really happen
     */
    public ActionForward moveDownOnlineReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        OnlineReviewsActionHelper actionHelper = protocolForm.getOnlineReviewsActionHelper();
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        String documentNumber = getOnlineReviewActionDocumentNumber(parameterName, "moveDownOnlineReviewComment");
        
        ProtocolOnlineReviewDocument document = actionHelper.getDocumentFromHelperMap(documentNumber);
        ReviewCommentsBean reviewCommentsBean = actionHelper.getReviewCommentsBeanFromHelperMap(documentNumber);
        long documentIndex = actionHelper.getIndexByDocumentNumber(documentNumber);
        int commentIndex = getOnlineReviewActionIndexNumber(parameterName, "moveDownOnlineReviewComment");
              
        if (applyRules(new SaveProtocolOnlineReviewEvent(document, reviewCommentsBean.getReviewComments(), documentIndex))) {
            Protocol protocol = protocolForm.getProtocolDocument().getProtocol();
            List<CommitteeScheduleMinute> reviewComments = reviewCommentsBean.getReviewComments();
            List<CommitteeScheduleMinute> deletedReviewComments = reviewCommentsBean.getDeletedReviewComments();
            
            getReviewCommentsService().moveDownReviewComment(reviewComments, protocol, commentIndex);
            getReviewCommentsService().saveReviewComments(reviewComments, deletedReviewComments);
            getDocumentService().saveDocument(document);
        }
        
        protocolForm.getOnlineReviewsActionHelper().init(true);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }    

    public ActionForward viewOnlineReviewAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        OnlineReviewsActionHelper actionHelper = protocolForm.getOnlineReviewsActionHelper();
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        String documentNumber = getOnlineReviewActionDocumentNumber(parameterName, "viewOnlineReviewAttachment");
        
        ReviewAttachmentsBean reviewCommentsBean = actionHelper.getReviewAttachmentsBeanFromHelperMap(documentNumber);
        int attachmentIndex = getOnlineReviewActionIndexNumber(parameterName, "viewOnlineReviewAttachment");
       final ProtocolReviewAttachment attachment = reviewCommentsBean.getReviewAttachments().get(attachmentIndex);
        
        if (attachment == null) {
            LOG.info(NOT_FOUND_SELECTION + attachmentIndex);
            //may want to tell the user the selection was invalid.
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
        
        final AttachmentFile file = attachment.getFile();
        this.streamToResponse(file.getData(), getValidHeaderString(file.getName()),  getValidHeaderString(file.getType()), response);
        
        return RESPONSE_ALREADY_HANDLED;
    }

}
