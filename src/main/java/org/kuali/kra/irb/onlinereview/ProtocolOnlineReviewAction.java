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
package org.kuali.kra.irb.onlinereview;

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
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolAction;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.ProtocolOnlineReviewDocument;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.notification.RejectReviewNotificationRenderer;
import org.kuali.kra.irb.actions.reviewcomments.ReviewAttachmentsBean;
import org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsBean;
import org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsService;
import org.kuali.kra.irb.actions.submit.ProtocolReviewer;
import org.kuali.kra.irb.actions.submit.ProtocolReviewerBean;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.notification.IRBNotificationContext;
import org.kuali.kra.irb.notification.IRBNotificationRenderer;
import org.kuali.kra.irb.onlinereview.event.AddProtocolOnlineReviewAttachmentEvent;
import org.kuali.kra.irb.onlinereview.event.AddProtocolOnlineReviewCommentEvent;
import org.kuali.kra.irb.onlinereview.event.DeleteProtocolOnlineReviewEvent;
import org.kuali.kra.irb.onlinereview.event.DisapproveProtocolOnlineReviewCommentEvent;
import org.kuali.kra.irb.onlinereview.event.RejectProtocolOnlineReviewCommentEvent;
import org.kuali.kra.irb.onlinereview.event.RouteProtocolOnlineReviewEvent;
import org.kuali.kra.irb.onlinereview.event.SaveProtocolOnlineReviewEvent;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.kra.meeting.MinuteEntryType;
import org.kuali.kra.service.KraWorkflowService;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.kra.web.struts.action.AuditActionHelper;
import org.kuali.rice.kns.bo.Note;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KualiRuleService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.RiceKeyConstants;
import org.kuali.rice.kns.util.WebUtils;
import org.kuali.rice.kns.web.struts.action.AuditModeAction;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;

/**
 * The set of actions for the Protocol Actions tab.
 */
public class ProtocolOnlineReviewAction extends ProtocolAction implements AuditModeAction {

    private static final Log LOG = LogFactory.getLog(ProtocolOnlineReviewAction.class);

    private static final String PROTOCOL_TAB = "protocol";
    private static final String DOCUMENT_REJECT_QUESTION="DocReject";
    private static final String DOCUMENT_DELETE_QUESTION="ProtocolDocDelete";
    private static final String UPDATE_REVIEW_STATUS_TO_FINAL="statusToFinal";
    private static final String DOCUMENT_REJECT_REASON_MAXLENGTH = "2000";
    private static final String ERROR_DOCUMENT_DELETE_REASON_REQUIRED = "You must enter a reason for this deletion.  The reason must be no more than {0} characters long.";
    //Protocol Online Review Action Forwards
  
    private static final String NOT_FOUND_SELECTION = "the attachment was not found for selection ";
    
    
    /** signifies that a response has already be handled therefore forwarding to obtain a response is not needed. */
    private static final ActionForward RESPONSE_ALREADY_HANDLED = null;

    //Used for redirecting to/from the ProtocolOnlineReviewRedirect action.
    private static final String PROTOCOL_DOCUMENT_NUMBER="protocolDocumentNumber";
    private static final String PROTOCOL_ONLINE_REVIEW_DOCUMENT_NUMBER="protocolOnlineReviewDocumentNumber";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);
            
        ((ProtocolForm) form).getActionHelper().prepareView();
        ((ProtocolForm) form).getOnlineReviewsActionHelper().init(false);
        return actionForward;
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
        ((ProtocolForm)form).getOnlineReviewsActionHelper().init(false);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    
    /**
     * Get the Kuali Rule Service.
     * @return the Kuali Rule Service
     */
    @Override
    protected KualiRuleService getKualiRuleService() {
        return KraServiceLocator.getService(KualiRuleService.class);
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
    
  
    
    /**
     * 
     * @param mapping the mapping associated with this action.
     * @param form the Protocol form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the name of the HTML page to display
     * @throws Exception doesn't ever really happen
     */
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
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    
    
  
    /**
     * The online review actions are encoded into the methodToCall parameters
     * using: (actionMethodToCall).(onlineReviewDocumentNumber).anchor in the name attributes of 
     * the online review form buttons.  Where
     * 
     * actionMethodToCall is for example 'routeOnlineReview'
     * onlineReviewDocumentNumber: is the document number of the online review
     *   
     * @param parameterName The parameter being decoded, usually retrieved from the request parameters as KNSConstants.METHOD_TO_CALL_ATTRIBUTE. 
     * @param actionMethodToCall The methodToCall ( function name in the action being executed. ).
     * 
     * @return
     */
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
    
    /**
     * 
     * This method is to render protocol review page.  It is redirected to by the protocol online review redirect action
     * when the edit link is clicked on in the action list.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward startProtocolOnlineReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Map<String, String> fieldValues = new HashMap<String, String>();
        String protocolDocumentNumber = request.getParameter(PROTOCOL_DOCUMENT_NUMBER);
        ((ProtocolForm) form).setDocument(getDocumentService().getByDocumentHeaderId(
                protocolDocumentNumber));
        ((ProtocolForm) form).initialize();
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
    public ActionForward approveOnlineReview(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        String onlineReviewDocumentNumber = getOnlineReviewActionDocumentNumber(
                (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE),
                "approveOnlineReview");
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolOnlineReviewDocument prDoc = protocolForm.getOnlineReviewsActionHelper().getDocumentFromHelperMap(onlineReviewDocumentNumber);
        ReviewCommentsBean reviewCommentsBean = protocolForm.getOnlineReviewsActionHelper().getReviewCommentsBeanFromHelperMap(onlineReviewDocumentNumber);
        ReviewAttachmentsBean reviewAttachmentsBean = protocolForm.getOnlineReviewsActionHelper().getReviewAttachmentsBeanFromHelperMap(onlineReviewDocumentNumber);
        boolean isApproveReview = StringUtils.equals(ProtocolOnlineReviewStatus.SAVED_STATUS_CD, prDoc.getProtocolOnlineReview().getProtocolOnlineReviewStatusCode());
        //check to see if we are the reviewer and this is an approval to the irb admin.
        
        boolean validComments = applyRules(new RouteProtocolOnlineReviewEvent(prDoc, reviewCommentsBean.getReviewComments(), protocolForm.getOnlineReviewsActionHelper().getIndexByDocumentNumber(onlineReviewDocumentNumber)));
        boolean statusIsOk = false;
        
        if( validComments && getKraWorkflowService().isUserApprovalRequested(prDoc, GlobalVariables.getUserSession().getPrincipalId())) {
            //then the status must be final.
                prDoc.getProtocolOnlineReview().setProtocolOnlineReviewStatusCode(ProtocolOnlineReviewStatus.FINAL_STATUS_CD);
                prDoc.getProtocolOnlineReview().setReviewerApproved(true);
                if (getKraWorkflowService().isDocumentOnNode(prDoc, Constants.ONLINE_REVIEW_ROUTE_NODE_ADMIN_REVIEW)) {
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
            
            Protocol protocol = protocolForm.getProtocolDocument().getProtocol();
            ProtocolOnlineReview protocolOnlineReview = prDoc.getProtocolOnlineReview();
            IRBNotificationRenderer renderer = new IRBNotificationRenderer(protocol);
            IRBNotificationContext context = new IRBNotificationContext(protocol, protocolOnlineReview, ProtocolActionType.REVIEW_COMPLETE, "Review Complete", renderer);
            getKcNotificationService().sendNotification(context);
            
            if (!protocolForm.getEditingMode().containsKey("maintainProtocolOnlineReviews")) {
                // reviewer approve will return here
                return mapping.findForward(KNSConstants.MAPPING_PORTAL);
            } else if (isApproveReview) {
                // admin approve review will return here
                return routeProtocolOLRToHoldingPage(mapping, protocolForm, prDoc.getDocumentNumber(), "Approve");
            }
        }
        
        
       
        return mapping.findForward(Constants.MAPPING_BASIC);
        
    }

    private ActionForward routeProtocolOLRToHoldingPage(ActionMapping mapping, ProtocolForm protocolForm, String olrDocId, String olrEvent) {
        Long routeHeaderId = Long.parseLong(protocolForm.getDocument().getDocumentNumber());
        String returnLocation = buildActionUrl(routeHeaderId, Constants.MAPPING_PROTOCOL_ONLINE_REVIEW , "ProtocolDocument");
        // use this doc id for holding action to check if online review document is complete and return to online review tab
        returnLocation += "&" + "olrDocId=" + olrDocId + "&" + "olrEvent=" + olrEvent;
        ActionForward basicForward = mapping.findForward(KNSConstants.MAPPING_PORTAL);
        //ActionForward holdingPageForward = mapping.findForward(Constants.MAPPING_HOLDING_PAGE);
        ActionForward holdingPageForward = mapping.findForward(Constants.MAPPING_IRB_HOLDING_PAGE);
        GlobalVariables.getUserSession().addObject(Constants.HOLDING_PAGE_DOCUMENT_ID, (Object)olrDocId);
        
        return routeToHoldingPage(basicForward, basicForward, holdingPageForward, returnLocation);

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
    public ActionForward blanketApproveOnlineReview(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String onlineReviewDocumentNumber = getOnlineReviewActionDocumentNumber(
                (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE),
                "blanketApproveOnlineReview");
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolOnlineReviewDocument prDoc = (ProtocolOnlineReviewDocument) protocolForm.getOnlineReviewsActionHelper()
            .getDocumentHelperMap().get(onlineReviewDocumentNumber).get("document");
        prDoc.getProtocolOnlineReview().addActionPerformed("BlanketApprove");
        getDocumentService().blanketApproveDocument(prDoc, "", null);
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
    public ActionForward saveOnlineReview(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String onlineReviewDocumentNumber = getOnlineReviewActionDocumentNumber(
                (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE),
                "saveOnlineReview");
        DocumentService documentService = KraServiceLocator.getService(DocumentService.class);
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolOnlineReviewDocument prDoc = protocolForm.getOnlineReviewsActionHelper().getDocumentFromHelperMap(onlineReviewDocumentNumber);
        ReviewCommentsBean reviewCommentsBean = protocolForm.getOnlineReviewsActionHelper().getReviewCommentsBeanFromHelperMap(onlineReviewDocumentNumber);
        ReviewAttachmentsBean reviewAttachmentsBean = protocolForm.getOnlineReviewsActionHelper().getReviewAttachmentsBeanFromHelperMap(onlineReviewDocumentNumber);
        if ( !this.applyRules(new SaveProtocolOnlineReviewEvent(prDoc, reviewCommentsBean.getReviewComments(), protocolForm.getOnlineReviewsActionHelper().getIndexByDocumentNumber(onlineReviewDocumentNumber)))) {
            //nothing to do, we failed validation return them to the screen.
        } else {
            ProtocolReviewer reviewer = prDoc.getProtocolOnlineReview().getProtocolReviewer();
            getBusinessObjectService().save(reviewer);
            getReviewCommentsService().saveReviewComments(reviewCommentsBean.getReviewComments(), reviewCommentsBean.getDeletedReviewComments());
            getReviewCommentsService().saveReviewAttachments(reviewAttachmentsBean.getReviewAttachments(), reviewAttachmentsBean.getDeletedReviewAttachments());           
            documentService.saveDocument(prDoc);
            recordOnlineReviewActionSuccess("saved", prDoc);
            protocolForm.getOnlineReviewsActionHelper().init(true);
        }
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
    public ActionForward rejectOnlineReview(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        String onlineReviewDocumentNumber = getOnlineReviewActionDocumentNumber(
                (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE),
                "rejectOnlineReview");
        ProtocolForm protocolForm = (ProtocolForm) form;        
        ProtocolOnlineReviewDocument prDoc = (ProtocolOnlineReviewDocument) protocolForm.getOnlineReviewsActionHelper()
            .getDocumentHelperMap().get(onlineReviewDocumentNumber).get("document");
        Object question = request.getParameter(KNSConstants.QUESTION_INST_ATTRIBUTE_NAME);
        Object buttonClicked = request.getParameter(KNSConstants.QUESTION_CLICKED_BUTTON);
        String reason = request.getParameter(KNSConstants.QUESTION_REASON_ATTRIBUTE_NAME);
        String callerString = String.format("rejectOnlineReview.%s.anchor%s",prDoc.getDocumentNumber(),0);
        if(question == null){
            return this.performQuestionWithInput(mapping, form, request, response, DOCUMENT_REJECT_QUESTION,"Are you sure you want to return this document to reviewer ?" , KNSConstants.CONFIRMATION_QUESTION, callerString, "");
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
                return this.performQuestionWithInputAgainBecauseOfErrors(mapping, form, request, response, DOCUMENT_REJECT_QUESTION, "Are you sure you want to return this document to reviewer ?", KNSConstants.CONFIRMATION_QUESTION, callerString, "", reason, KeyConstants.ERROR_ONLINE_REVIEW_REJECTED_REASON_REQUIRED, KNSConstants.QUESTION_REASON_ATTRIBUTE_NAME, DOCUMENT_REJECT_REASON_MAXLENGTH);              
            } else if (WebUtils.containsSensitiveDataPatternMatch(reason)) {
                return this.performQuestionWithInputAgainBecauseOfErrors(mapping, form, request, response, 
                        DOCUMENT_REJECT_QUESTION, "Are you sure you want to return this document to reviewer ?", 
                        KNSConstants.CONFIRMATION_QUESTION, callerString, "", reason, RiceKeyConstants.ERROR_DOCUMENT_FIELD_CONTAINS_POSSIBLE_SENSITIVE_DATA,
                        KNSConstants.QUESTION_REASON_ATTRIBUTE_NAME, "reason");
            } else {
                prDoc.getProtocolOnlineReview().setProtocolOnlineReviewStatusCode(ProtocolOnlineReviewStatus.SAVED_STATUS_CD);
                prDoc.getProtocolOnlineReview().addActionPerformed("Reject");
                prDoc.getProtocolOnlineReview().setReviewerApproved(false);
                prDoc.getProtocolOnlineReview().setAdminAccepted(false);
                setOnlineReviewCommentFinalFlags(prDoc.getProtocolOnlineReview(), false);
                getDocumentService().saveDocument(prDoc);
                getProtocolOnlineReviewService().returnProtocolOnlineReviewDocumentToReviewer(prDoc,reason,GlobalVariables.getUserSession().getPrincipalId());
                
                Protocol protocol = protocolForm.getProtocolDocument().getProtocol();
                ProtocolOnlineReview protocolOnlineReview = prDoc.getProtocolOnlineReview();
                RejectReviewNotificationRenderer renderer = new RejectReviewNotificationRenderer(protocol, reason);
                IRBNotificationContext context = new IRBNotificationContext(protocol, protocolOnlineReview, ProtocolActionType.REVIEW_REJECTED, "Return to Reviewer", renderer);
                getKcNotificationService().sendNotification(context);
                
                protocolForm.getOnlineReviewsActionHelper().init(true);
                recordOnlineReviewActionSuccess("returned to reviewer", prDoc);   
                return routeProtocolOLRToHoldingPage(mapping, protocolForm, prDoc.getDocumentNumber(), "Reject");

            }
        }
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
    public ActionForward disapproveOnlineReview(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
         
        String onlineReviewDocumentNumber = getOnlineReviewActionDocumentNumber(
                (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE),
                "disapproveOnlineReview");
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolOnlineReviewDocument prDoc = (ProtocolOnlineReviewDocument) protocolForm.getOnlineReviewsActionHelper()
            .getDocumentHelperMap().get(onlineReviewDocumentNumber).get("document");
        ReviewCommentsBean reviewCommentsBean = protocolForm.getOnlineReviewsActionHelper().getReviewCommentsBeanFromHelperMap(onlineReviewDocumentNumber);
        Object question = request.getParameter(KNSConstants.QUESTION_INST_ATTRIBUTE_NAME);
        String reason = request.getParameter(KNSConstants.QUESTION_REASON_ATTRIBUTE_NAME);
        String disapprovalNoteText = "";
        String callerString = String.format("disapproveOnlineReview.%s.anchor%s",prDoc.getDocumentNumber(),0);
       
        //the data gets saved here, need to validate the save ok.
        if (!this.applyRules(new SaveProtocolOnlineReviewEvent(prDoc, reviewCommentsBean.getReviewComments(), protocolForm.getOnlineReviewsActionHelper().getIndexByDocumentNumber(onlineReviewDocumentNumber)))) {
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
        
        
        // start in logic for confirming the disapproval
        if (question == null) {
            // ask question if not already asked
            return performQuestionWithInput(mapping, form, request, response, KNSConstants.DOCUMENT_DISAPPROVE_QUESTION, getKualiConfigurationService().getPropertyString(RiceKeyConstants.QUESTION_DISAPPROVE_DOCUMENT), KNSConstants.CONFIRMATION_QUESTION, callerString, "");
        } else {
            Object buttonClicked = request.getParameter(KNSConstants.QUESTION_CLICKED_BUTTON);
            if ((KNSConstants.DOCUMENT_DISAPPROVE_QUESTION.equals(question)) && ConfirmationQuestion.NO.equals(buttonClicked)) {
                // if no button clicked just reload the doc
                return mapping.findForward(Constants.MAPPING_BASIC);
            } else {
                // have to check length on value entered
                String introNoteMessage = getKualiConfigurationService().getPropertyString(RiceKeyConstants.MESSAGE_DISAPPROVAL_NOTE_TEXT_INTRO) + KNSConstants.BLANK_SPACE;

                // build out full message
                disapprovalNoteText = introNoteMessage + reason;

                // get note text max length from DD
                int noteTextMaxLength = getDataDictionaryService().getAttributeMaxLength(Note.class, KNSConstants.NOTE_TEXT_PROPERTY_NAME).intValue();

                if (!this.applyRules(new DisapproveProtocolOnlineReviewCommentEvent(prDoc, reason, disapprovalNoteText, noteTextMaxLength))) {
                    // figure out exact number of characters that the user can enter
                    int reasonLimit = noteTextMaxLength - introNoteMessage.length();

                    if (reason == null) {
                        // prevent a NPE by setting the reason to a blank string
                        reason = "";
                    }
                    return this.performQuestionWithInputAgainBecauseOfErrors(mapping, form, request, response, KNSConstants.DOCUMENT_DISAPPROVE_QUESTION, getKualiConfigurationService().getPropertyString(RiceKeyConstants.QUESTION_DISAPPROVE_DOCUMENT), KNSConstants.CONFIRMATION_QUESTION, callerString, "", reason, RiceKeyConstants.ERROR_DOCUMENT_DISAPPROVE_REASON_REQUIRED, KNSConstants.QUESTION_REASON_ATTRIBUTE_NAME, new Integer(reasonLimit).toString());
                } 
                
                if (WebUtils.containsSensitiveDataPatternMatch(disapprovalNoteText)) {
                    return this.performQuestionWithInputAgainBecauseOfErrors(mapping, form, request, response, 
                            KNSConstants.DOCUMENT_DISAPPROVE_QUESTION, getKualiConfigurationService().getPropertyString(RiceKeyConstants.QUESTION_DISAPPROVE_DOCUMENT), 
                            KNSConstants.CONFIRMATION_QUESTION, callerString, "", reason, RiceKeyConstants.ERROR_DOCUMENT_FIELD_CONTAINS_POSSIBLE_SENSITIVE_DATA,
                            KNSConstants.QUESTION_REASON_ATTRIBUTE_NAME, "reason");
                } 
                
                prDoc.getProtocolOnlineReview().addActionPerformed("Disapprove");
                KualiDocumentFormBase kualiDocumentFormBase = (KualiDocumentFormBase)protocolForm.getOnlineReviewsActionHelper().getDocumentHelperMap().get(onlineReviewDocumentNumber).get(OnlineReviewsActionHelper.FORM_MAP_KEY);
                doProcessingAfterPost( kualiDocumentFormBase, request );
                ProtocolOnlineReviewDocument document = (ProtocolOnlineReviewDocument) kualiDocumentFormBase.getDocument();
                document.getProtocolOnlineReview().setProtocolOnlineReviewStatusCode(ProtocolOnlineReviewStatus.REMOVED_CANCELLED_STATUS_CD);
                document.getProtocolOnlineReview().setReviewerApproved(false);
                document.getProtocolOnlineReview().setAdminAccepted(false);
                getBusinessObjectService().save(document.getProtocolOnlineReview());
                getDocumentService().disapproveDocument(document, disapprovalNoteText);
                GlobalVariables.getMessageList().add(RiceKeyConstants.MESSAGE_ROUTE_DISAPPROVED);
                kualiDocumentFormBase.setAnnotation("");
                protocolForm.getOnlineReviewsActionHelper().init(true);
                recordOnlineReviewActionSuccess("disapproved", prDoc);
                
                if (!protocolForm.getEditingMode().containsKey("maintainProtocolOnlineReviews")) {
                    //redirect to the actual online review document page.
                    //response.sendRedirect(String.format("protocolOnlineReviewRedirect.do?methodToCall=startProtocolOnlineReview&%s=%s",PROTOCOL_ONLINE_REVIEW_DOCUMENT_NUMBER,prDoc.getDocumentNumber()));
                    //return super.returnToSender(request, mapping, protocolForm);
                    return mapping.findForward(KNSConstants.MAPPING_PORTAL);
                }
                
                
            }
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
       
  
    }

    /**
     * 
     * @param mapping the mapping associated with this action.
     * @param form the Protocol form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the name of the HTML page to display
     * @throws Exception if something bad happens
     */
    public ActionForward deleteOnlineReview(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
         
        String onlineReviewDocumentNumber = getOnlineReviewActionDocumentNumber(
                (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE),
                "deleteOnlineReview");
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolOnlineReviewDocument prDoc = (ProtocolOnlineReviewDocument) protocolForm.getOnlineReviewsActionHelper()
            .getDocumentHelperMap().get(onlineReviewDocumentNumber).get("document");
        ReviewCommentsBean reviewCommentsBean = protocolForm.getOnlineReviewsActionHelper().getReviewCommentsBeanFromHelperMap(onlineReviewDocumentNumber);
        Object question = request.getParameter(KNSConstants.QUESTION_INST_ATTRIBUTE_NAME);
        String reason = request.getParameter(KNSConstants.QUESTION_REASON_ATTRIBUTE_NAME);
        String deleteNoteText = "";
        String callerString = String.format("disapproveOnlineReview.%s.anchor%s",prDoc.getDocumentNumber(),0);
       
        //the data gets saved here, need to validate the save ok.
        if (!this.applyRules(new SaveProtocolOnlineReviewEvent(prDoc, reviewCommentsBean.getReviewComments(), protocolForm.getOnlineReviewsActionHelper().getIndexByDocumentNumber(onlineReviewDocumentNumber)))) {
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
        
        
        // start in logic for confirming the disapproval
        if (question == null) {
            // ask question if not already asked
            return performQuestionWithInput(mapping, form, request, response, DOCUMENT_DELETE_QUESTION, "Are you sure you want to delete this document?", KNSConstants.CONFIRMATION_QUESTION, callerString, "");
        } else {
            Object buttonClicked = request.getParameter(KNSConstants.QUESTION_CLICKED_BUTTON);
            if ((DOCUMENT_DELETE_QUESTION.equals(question)) && ConfirmationQuestion.NO.equals(buttonClicked)) {
                // if no button clicked just reload the doc
                return mapping.findForward(Constants.MAPPING_BASIC);
            } else {
                // have to check length on value entered
                String introNoteMessage = "Deletion reason -" + KNSConstants.BLANK_SPACE;

                // build out full message
                deleteNoteText = introNoteMessage + reason;

                // get note text max length from DD
                int noteTextMaxLength = getDataDictionaryService().getAttributeMaxLength(Note.class, KNSConstants.NOTE_TEXT_PROPERTY_NAME).intValue();

                if (!this.applyRules(new DeleteProtocolOnlineReviewEvent(prDoc, reason, deleteNoteText, noteTextMaxLength))) {
                    // figure out exact number of characters that the user can enter
                    int reasonLimit = noteTextMaxLength - introNoteMessage.length();

                    if (reason == null) {
                        // prevent a NPE by setting the reason to a blank string
                        reason = "";
                    }
                    return this.performQuestionWithInputAgainBecauseOfErrors(mapping, form, request, response, DOCUMENT_DELETE_QUESTION, "Are you sure you want to delete this document?", KNSConstants.CONFIRMATION_QUESTION, callerString, "", reason, ERROR_DOCUMENT_DELETE_REASON_REQUIRED, KNSConstants.QUESTION_REASON_ATTRIBUTE_NAME, new Integer(reasonLimit).toString());
                } 
                
                if (WebUtils.containsSensitiveDataPatternMatch(deleteNoteText)) {
                    return this.performQuestionWithInputAgainBecauseOfErrors(mapping, form, request, response, 
                            DOCUMENT_DELETE_QUESTION, "Are you sure you want to delete this document?", 
                            KNSConstants.CONFIRMATION_QUESTION, callerString, "", reason, RiceKeyConstants.ERROR_DOCUMENT_FIELD_CONTAINS_POSSIBLE_SENSITIVE_DATA,
                            KNSConstants.QUESTION_REASON_ATTRIBUTE_NAME, "reason");
                } 
                
                prDoc.getProtocolOnlineReview().addActionPerformed("Delete");
                KualiDocumentFormBase kualiDocumentFormBase = (KualiDocumentFormBase)protocolForm.getOnlineReviewsActionHelper().getDocumentHelperMap().get(onlineReviewDocumentNumber).get(OnlineReviewsActionHelper.FORM_MAP_KEY);
                doProcessingAfterPost( kualiDocumentFormBase, request );
                ProtocolOnlineReviewDocument document = (ProtocolOnlineReviewDocument) kualiDocumentFormBase.getDocument();
                document.getProtocolOnlineReview().setProtocolOnlineReviewStatusCode(ProtocolOnlineReviewStatus.REMOVED_CANCELLED_STATUS_CD);
                document.getProtocolOnlineReview().setReviewerApproved(false);
                document.getProtocolOnlineReview().setAdminAccepted(false);
                getBusinessObjectService().save(document.getProtocolOnlineReview());
                getDocumentService().disapproveDocument(document, deleteNoteText);
                GlobalVariables.getMessageList().add(RiceKeyConstants.MESSAGE_ROUTE_DISAPPROVED);
                kualiDocumentFormBase.setAnnotation("");
                protocolForm.getOnlineReviewsActionHelper().init(true);
                recordOnlineReviewActionSuccess("deleted", prDoc);
                
                if (!protocolForm.getEditingMode().containsKey("maintainProtocolOnlineReviews")) {
                    return mapping.findForward(KNSConstants.MAPPING_PORTAL);
                }
                
                
            }
        }
        
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
    public ActionForward cancelOnlineReview(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String onlineReviewDocumentNumber = getOnlineReviewActionDocumentNumber(
                (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE),
                "cancelOnlineReview");
        ProtocolForm protocolForm = (ProtocolForm) form;
        
        ProtocolOnlineReviewDocument prDoc = (ProtocolOnlineReviewDocument) protocolForm.getOnlineReviewsActionHelper()
            .getDocumentHelperMap().get(onlineReviewDocumentNumber).get("document");
        String callerString = String.format("rejectOnlineReview.%s.anchor%s",prDoc.getDocumentNumber(),0);
        Object question = request.getParameter(KNSConstants.QUESTION_INST_ATTRIBUTE_NAME);
        // logic for cancel question
        if (question == null) {
            // ask question if not already asked
            return this.performQuestionWithoutInput(mapping, form, request, response, KNSConstants.DOCUMENT_CANCEL_QUESTION, getKualiConfigurationService().getPropertyString("document.question.cancel.text"), KNSConstants.CONFIRMATION_QUESTION, callerString, "");
        }
        else {
            Object buttonClicked = request.getParameter(KNSConstants.QUESTION_CLICKED_BUTTON);
            if ((KNSConstants.DOCUMENT_CANCEL_QUESTION.equals(question)) && ConfirmationQuestion.NO.equals(buttonClicked)) {
                // if no button clicked just reload the doc
                
            }
            
            KualiDocumentFormBase kualiDocumentFormBase = (KualiDocumentFormBase) form;
            doProcessingAfterPost( kualiDocumentFormBase, request );
            getDocumentService().cancelDocument(prDoc, kualiDocumentFormBase.getAnnotation());
            protocolForm.getOnlineReviewsActionHelper().init(true);
            
            if (!protocolForm.getEditingMode().containsKey("maintainProtocolOnlineReviews")) {
                //redirect to the actual online review document page.
                return super.returnToSender(request, mapping, protocolForm);
                //response.sendRedirect(String.format("protocolOnlineReviewRedirect.do?methodToCall=startProtocolOnlineReview&%s=%s",PROTOCOL_ONLINE_REVIEW_DOCUMENT_NUMBER,prDoc.getDocumentNumber()));
            }
            
         
        }
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
    public ActionForward addOnlineReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ProtocolForm protocolForm = (ProtocolForm) form;
        OnlineReviewsActionHelper actionHelper = protocolForm.getOnlineReviewsActionHelper();
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
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
            
            reviewCommentsBean.setNewReviewComment(new CommitteeScheduleMinute(MinuteEntryType.PROTOCOL_REVIEWER_COMMENT));
        }
        
        //protocolForm.getOnlineReviewsActionHelper().init(true);
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }    

    /**
     * 
     * This method is for action to add OLR review attachment
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addOnlineReviewAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ProtocolForm protocolForm = (ProtocolForm) form;
        OnlineReviewsActionHelper actionHelper = protocolForm.getOnlineReviewsActionHelper();
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
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
            
            reviewAttachmentsBean.setNewReviewAttachment(new ProtocolReviewAttachment());
        }
        
        //protocolForm.getOnlineReviewsActionHelper().init(true);
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
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
    public ActionForward moveUpOnlineReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        OnlineReviewsActionHelper actionHelper = protocolForm.getOnlineReviewsActionHelper();
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
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
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
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

    /**
     * 
     * @param mapping the mapping associated with this action.
     * @param form the Protocol form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the name of the HTML page to display
     * @throws Exception doesn't ever really happen
     */
    public ActionForward deleteOnlineReviewComment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        OnlineReviewsActionHelper actionHelper = protocolForm.getOnlineReviewsActionHelper();
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
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
    
    /**
     * 
     * This method is to delete the OLR review attachment and persist it
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteOnlineReviewAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ProtocolForm protocolForm = (ProtocolForm) form;
        OnlineReviewsActionHelper actionHelper = protocolForm.getOnlineReviewsActionHelper();
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
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
    
        
    private TaskAuthorizationService getTaskAuthorizationService() {
        return KraServiceLocator.getService(TaskAuthorizationService.class);
    }
    
    private ReviewCommentsService getReviewCommentsService() {
        return KraServiceLocator.getService(ReviewCommentsService.class);
    }
        
    private KraWorkflowService getKraWorkflowService() {
        return KraServiceLocator.getService(KraWorkflowService.class);
    }
    
    private KcNotificationService getKcNotificationService() {
        return KraServiceLocator.getService(KcNotificationService.class);
    }
    
    protected void recordOnlineReviewActionSuccess(String onlineReviewActionName, ProtocolOnlineReviewDocument document) {
        String documentInfo = String.format("document number:%s, reviewer:%s", document.getDocumentNumber(), document.getProtocolOnlineReview().getProtocolReviewer().getFullName());
        GlobalVariables.getMessageList().add(KeyConstants.MESSAGE_ONLINE_REVIEW_ACTION_SUCCESSFULLY_COMPLETED,onlineReviewActionName, documentInfo);
    }
    
    private void setOnlineReviewCommentFinalFlags(ProtocolOnlineReview onlineReview, boolean flagValue) {
        List<CommitteeScheduleMinute> minutes = onlineReview.getCommitteeScheduleMinutes();
        for (CommitteeScheduleMinute minute : minutes) {
            minute.setFinalFlag(flagValue);
        }
    }
    
    /**
     * 
     * This method is for 'view' OLR review attachment
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward viewOnlineReviewAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        OnlineReviewsActionHelper actionHelper = protocolForm.getOnlineReviewsActionHelper();
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
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
