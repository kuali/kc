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
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolAction;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.ProtocolOnlineReviewDocument;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewService;
import org.kuali.kra.irb.actions.approve.ProtocolApproveService;
import org.kuali.kra.irb.actions.assignagenda.ProtocolAssignToAgendaService;
import org.kuali.kra.irb.actions.assigncmtsched.ProtocolAssignCmtSchedService;
import org.kuali.kra.irb.actions.assignreviewers.ProtocolAssignReviewersService;
import org.kuali.kra.irb.actions.copy.ProtocolCopyService;
import org.kuali.kra.irb.actions.decision.CommitteeDecisionService;
import org.kuali.kra.irb.actions.delete.ProtocolDeleteService;
import org.kuali.kra.irb.actions.expediteapproval.ProtocolExpediteApprovalService;
import org.kuali.kra.irb.actions.genericactions.ProtocolGenericActionService;
import org.kuali.kra.irb.actions.grantexemption.ProtocolGrantExemptionService;
import org.kuali.kra.irb.actions.notifyirb.ProtocolNotifyIrbService;
import org.kuali.kra.irb.actions.request.ProtocolRequestService;
import org.kuali.kra.irb.actions.reviewcomments.ReviewerComments;
import org.kuali.kra.irb.actions.reviewcomments.ReviewerCommentsService;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitActionService;
import org.kuali.kra.irb.actions.withdraw.ProtocolWithdrawService;
import org.kuali.kra.irb.auth.ProtocolTask;
import org.kuali.kra.irb.onlinereview.event.AddProtocolOnlineReviewCommentEvent;
import org.kuali.kra.irb.onlinereview.event.RouteProtocolOnlineReviewEvent;
import org.kuali.kra.irb.onlinereview.event.SaveProtocolOnlineReviewEvent;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.service.KraWorkflowService;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.kra.web.struts.action.AuditActionHelper;
import org.kuali.rice.core.util.RiceConstants;
import org.kuali.rice.kns.bo.Note;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.kns.rule.event.KualiDocumentEvent;
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
    private static final String UPDATE_REVIEW_STATUS_TO_FINAL="statusToFinal";
    //Protocol Online Review Action Forwards
  
    /** signifies that a response has already be handled therefore forwarding to obtain a response is not needed. */
    private static final String PROTOCOL_DOCUMENT_NUMBER="protocolDocumentNumber";
    
    /** {@inheritDoc} */
    
  
    
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
        ProtocolForm protocolForm = ( ProtocolForm ) form;
        ProtocolOnlineReviewService protocolOnlineReviewService = getProtocolOnlineReviewService();
        OnlineReviewsActionHelper onlineReviewHelper = protocolForm.getOnlineReviewsActionHelper();
        
        Map<String,Object> keyMap = new HashMap<String,Object>();
        
         
        
        protocolOnlineReviewService.createAndRouteProtocolOnlineReviewDocument( protocolForm.getProtocolDocument().getProtocol(),
                                                          onlineReviewHelper.getNewProtocolReviewCommitteeMembershipId(), 
                                                          onlineReviewHelper.getNewReviewDocumentDescription(),
                                                          onlineReviewHelper.getNewReviewExplanation(),
                                                          onlineReviewHelper.getNewReviewOrganizationDocumentNumber(),
                                                          null,
                                                          true,
                                                          GlobalVariables.getUserSession().getPrincipalId());
        
        protocolForm.getOnlineReviewsActionHelper().init(true);
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
        String idxStr = null;
        if (StringUtils.isBlank(parameterName)||parameterName.indexOf("."+actionMethodToCall+".") == -1) {
            throw new IllegalArgumentException(
                    String.format("getOnlineReviewActionIndex expects a non-empty value for parameterName parameter, "+
                            "and it must contain as a substring the parameter actionMethodToCall. "+
                            "The passed values were (%s,%s)."
                            ,parameterName,actionMethodToCall)
                    );
        }
        //idxStr = StringUtils.substringBetween(parameterName, "."+actionMethodToCall+".", ".anchor" );
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
        
        ActionForward forward =  mapping.findForward(Constants.MAPPING_BASIC);
        String onlineReviewDocumentNumber = getOnlineReviewActionDocumentNumber(
                (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE),
                "approveOnlineReview");
        DocumentService documentService = KraServiceLocator.getService(DocumentService.class);
        ReviewerCommentsService reviewerCommentsService = KraServiceLocator.getService(ReviewerCommentsService.class);
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolOnlineReviewDocument prDoc = protocolForm.getOnlineReviewsActionHelper().getDocumentFromHelperMap(onlineReviewDocumentNumber);
        ReviewerComments reviewComments = protocolForm.getOnlineReviewsActionHelper().getReviewerCommentsFromHelperMap(onlineReviewDocumentNumber);

        //check to see if we are the reviewer and this is an approval to the irb admin.
        if( getKraAuthorizationService().hasRole(GlobalVariables.getUserSession().getPrincipalId(), prDoc.getProtocolOnlineReview(), PermissionConstants.MAINTAIN_PROTOCOL_ONLINE_REVIEW)
            && getKraWorkflowService().isUserApprovalRequested(prDoc, GlobalVariables.getUserSession().getPrincipalId())) {
            //then the status must be final.
            
            if(!StringUtils.equals(prDoc.getProtocolOnlineReview().getProtocolOnlineReviewStatusCode(),ProtocolOnlineReviewStatus.FINAL_STATUS_CD)) {
                Object question = request.getParameter(KNSConstants.QUESTION_INST_ATTRIBUTE_NAME);
                Object buttonClicked = request.getParameter(KNSConstants.QUESTION_CLICKED_BUTTON);
                String reason = request.getParameter(KNSConstants.QUESTION_REASON_ATTRIBUTE_NAME);
                String callerString = String.format("approveOnlineReview.%s.anchor%s",prDoc.getDocumentNumber(),0);
                if(question == null){
                    forward =  this.performQuestionWithInput(mapping, form, request, response, UPDATE_REVIEW_STATUS_TO_FINAL,"Before submitting review to the IRB Administrator, the review status must be marked final.  Do you wish to change the review status to final and submit the review to the IRB Administrator? " , KNSConstants.CONFIRMATION_QUESTION, callerString, "");
                 } 
                else if((UPDATE_REVIEW_STATUS_TO_FINAL.equals(question)) && ConfirmationQuestion.NO.equals(buttonClicked))  {
                    //nothing to do.
                }
                else
                {
                    prDoc.getProtocolOnlineReview().setProtocolOnlineReviewStatusCode(ProtocolOnlineReviewStatus.FINAL_STATUS_CD);
                    documentService.saveDocument(prDoc);
                }
            }
        }
        
        if (!applyRules(new RouteProtocolOnlineReviewEvent(prDoc,reviewComments.getComments(), protocolForm.getOnlineReviewsActionHelper().getIndexByDocumentNumber(onlineReviewDocumentNumber)))) {
            //nothing to do here.
        } else {
            reviewerCommentsService.persistReviewerComments(reviewComments, protocolForm.getProtocolDocument().getProtocol(), prDoc.getProtocolOnlineReview());
            documentService.saveDocument(prDoc);
            documentService.approveDocument(prDoc, "", null);
        }
        
        return forward;
        
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
        DocumentService documentService = KraServiceLocator.getService(DocumentService.class);
        ReviewerCommentsService reviewerCommentsService = KraServiceLocator.getService(ReviewerCommentsService.class);
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolOnlineReviewDocument prDoc = (ProtocolOnlineReviewDocument) protocolForm.getOnlineReviewsActionHelper()
            .getDocumentHelperMap().get(onlineReviewDocumentNumber).get("document");
        documentService.blanketApproveDocument(prDoc, "", null);
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
        ReviewerCommentsService reviewerCommentsService = KraServiceLocator.getService(ReviewerCommentsService.class);
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolOnlineReviewDocument prDoc = protocolForm.getOnlineReviewsActionHelper().getDocumentFromHelperMap(onlineReviewDocumentNumber);
        ReviewerComments reviewComments = protocolForm.getOnlineReviewsActionHelper().getReviewerCommentsFromHelperMap(onlineReviewDocumentNumber);
        if ( !this.applyRules(new SaveProtocolOnlineReviewEvent(prDoc,reviewComments.getComments(), protocolForm.getOnlineReviewsActionHelper().getIndexByDocumentNumber(onlineReviewDocumentNumber)))) {
            //nothing to do, we failed validation return them to the screen.
        } else {
            reviewerCommentsService.persistReviewerComments(reviewComments, protocolForm.getProtocolDocument().getProtocol(), prDoc.getProtocolOnlineReview());
            documentService.saveDocument(prDoc);
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
        DocumentService documentService = KraServiceLocator.getService(DocumentService.class);
        ReviewerCommentsService reviewerCommentsService = KraServiceLocator.getService(ReviewerCommentsService.class);
        ProtocolForm protocolForm = (ProtocolForm) form;
        ActionForward forward =  mapping.findForward(Constants.MAPPING_BASIC);
        
        ProtocolOnlineReviewDocument prDoc = (ProtocolOnlineReviewDocument) protocolForm.getOnlineReviewsActionHelper()
            .getDocumentHelperMap().get(onlineReviewDocumentNumber).get("document");
        Object question = request.getParameter(KNSConstants.QUESTION_INST_ATTRIBUTE_NAME);
        Object buttonClicked = request.getParameter(KNSConstants.QUESTION_CLICKED_BUTTON);
        String reason = request.getParameter(KNSConstants.QUESTION_REASON_ATTRIBUTE_NAME);
        String callerString = String.format("rejectOnlineReview.%s.anchor%s",prDoc.getDocumentNumber(),0);
        if(question == null){
            forward =  this.performQuestionWithInput(mapping, form, request, response, DOCUMENT_REJECT_QUESTION,"Are you sure you want to reject this document?" , KNSConstants.CONFIRMATION_QUESTION, callerString, "");
         } 
        else if((DOCUMENT_REJECT_QUESTION.equals(question)) && ConfirmationQuestion.NO.equals(buttonClicked))  {
            //nothing to do.
        }
        else
        {
            getProtocolOnlineReviewService().returnProtocolOnlineReviewDocumentToReviewer(prDoc,reason,GlobalVariables.getUserSession().getPrincipalId());
        }
        return forward;
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
        DocumentService documentService = KraServiceLocator.getService(DocumentService.class);
        ReviewerCommentsService reviewerCommentsService = KraServiceLocator.getService(ReviewerCommentsService.class);
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolOnlineReviewDocument prDoc = (ProtocolOnlineReviewDocument) protocolForm.getOnlineReviewsActionHelper()
            .getDocumentHelperMap().get(onlineReviewDocumentNumber).get("document");

        Object question = request.getParameter(KNSConstants.QUESTION_INST_ATTRIBUTE_NAME);
        String reason = request.getParameter(KNSConstants.QUESTION_REASON_ATTRIBUTE_NAME);
        String disapprovalNoteText = "";
        String callerString = String.format("disapproveOnlineReview.%s.anchor%s",prDoc.getDocumentNumber(),0);

        // start in logic for confirming the disapproval
        if (question == null) {
            // ask question if not already asked
            return performQuestionWithInput(mapping, form, request, response, KNSConstants.DOCUMENT_DISAPPROVE_QUESTION, getKualiConfigurationService().getPropertyString(RiceKeyConstants.QUESTION_DISAPPROVE_DOCUMENT), KNSConstants.CONFIRMATION_QUESTION, callerString, "");
        }
        else {
            Object buttonClicked = request.getParameter(KNSConstants.QUESTION_CLICKED_BUTTON);
            if ((KNSConstants.DOCUMENT_DISAPPROVE_QUESTION.equals(question)) && ConfirmationQuestion.NO.equals(buttonClicked)) {
                // if no button clicked just reload the doc
                return mapping.findForward(RiceConstants.MAPPING_BASIC);
            }
            else {
                // have to check length on value entered
                String introNoteMessage = getKualiConfigurationService().getPropertyString(RiceKeyConstants.MESSAGE_DISAPPROVAL_NOTE_TEXT_INTRO) + KNSConstants.BLANK_SPACE;

                // build out full message
                disapprovalNoteText = introNoteMessage + reason;
                int disapprovalNoteTextLength = disapprovalNoteText.length();

                // get note text max length from DD
                int noteTextMaxLength = getDataDictionaryService().getAttributeMaxLength(Note.class, KNSConstants.NOTE_TEXT_PROPERTY_NAME).intValue();

                if (StringUtils.isBlank(reason) || (disapprovalNoteTextLength > noteTextMaxLength)) {
                    // figure out exact number of characters that the user can enter
                    int reasonLimit = noteTextMaxLength - disapprovalNoteTextLength;

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
            }
        }

        KualiDocumentFormBase kualiDocumentFormBase = (KualiDocumentFormBase)protocolForm.getOnlineReviewsActionHelper().getDocumentHelperMap().get(onlineReviewDocumentNumber).get("form");
        doProcessingAfterPost( kualiDocumentFormBase, request );
        getDocumentService().disapproveDocument(kualiDocumentFormBase.getDocument(), disapprovalNoteText);
        GlobalVariables.getMessageList().add(RiceKeyConstants.MESSAGE_ROUTE_DISAPPROVED);
        kualiDocumentFormBase.setAnnotation("");
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
        DocumentService documentService = KraServiceLocator.getService(DocumentService.class);
        ReviewerCommentsService reviewerCommentsService = KraServiceLocator.getService(ReviewerCommentsService.class);
        ProtocolForm protocolForm = (ProtocolForm) form;
        ActionForward forward =  mapping.findForward(Constants.MAPPING_BASIC);
        
        ProtocolOnlineReviewDocument prDoc = (ProtocolOnlineReviewDocument) protocolForm.getOnlineReviewsActionHelper()
            .getDocumentHelperMap().get(onlineReviewDocumentNumber).get("document");
        String callerString = String.format("rejectOnlineReview.%s.anchor%s",prDoc.getDocumentNumber(),0);
        Object question = request.getParameter(KNSConstants.QUESTION_INST_ATTRIBUTE_NAME);
        // logic for cancel question
        if (question == null) {
            // ask question if not already asked
            forward =  this.performQuestionWithoutInput(mapping, form, request, response, KNSConstants.DOCUMENT_CANCEL_QUESTION, getKualiConfigurationService().getPropertyString("document.question.cancel.text"), KNSConstants.CONFIRMATION_QUESTION, callerString, "");
        }
        else {
            Object buttonClicked = request.getParameter(KNSConstants.QUESTION_CLICKED_BUTTON);
            if ((KNSConstants.DOCUMENT_CANCEL_QUESTION.equals(question)) && ConfirmationQuestion.NO.equals(buttonClicked)) {
                // if no button clicked just reload the doc
                
            }
            
            KualiDocumentFormBase kualiDocumentFormBase = (KualiDocumentFormBase) form;
            doProcessingAfterPost( kualiDocumentFormBase, request );
            getDocumentService().cancelDocument(prDoc, kualiDocumentFormBase.getAnnotation());
         
        }
        return forward;
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

        String onlineReviewDocumentNumber = getOnlineReviewActionDocumentNumber((String)request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE),"addOnlineReviewComment");
        DocumentService documentService = KraServiceLocator.getService(DocumentService.class);
        ReviewerCommentsService reviewerCommentsService = KraServiceLocator.getService(ReviewerCommentsService.class);
        ProtocolForm protocolForm = (ProtocolForm)form;
        ProtocolOnlineReviewDocument prDoc = protocolForm.getOnlineReviewsActionHelper().getDocumentFromHelperMap(onlineReviewDocumentNumber);
        ReviewerComments comments = protocolForm.getOnlineReviewsActionHelper().getReviewerCommentsFromHelperMap(onlineReviewDocumentNumber);
        if ( !this.applyRules(new AddProtocolOnlineReviewCommentEvent(prDoc,comments.getNewComment(), protocolForm.getOnlineReviewsActionHelper().getIndexByDocumentNumber(onlineReviewDocumentNumber)))) {
            //nothing to do, we failed validation return them to the screen.
        } else {
            comments.addNewComment(protocolForm.getProtocolDocument().getProtocol(),prDoc.getProtocolOnlineReview() );
            reviewerCommentsService.persistReviewerComments(comments, protocolForm.getProtocolDocument().getProtocol(), prDoc.getProtocolOnlineReview());
            documentService.saveDocument(prDoc);
        }
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
        
        String onlineReviewDocumentNumber = getOnlineReviewActionDocumentNumber((String)request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE),"moveUpOnlineReviewComment");
        int onlineReviewCommentIndex = getOnlineReviewActionIndexNumber((String)request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE),"moveUpOnlineReviewComment");
        DocumentService documentService = KraServiceLocator.getService(DocumentService.class);
        ReviewerCommentsService reviewerCommentsService = KraServiceLocator.getService(ReviewerCommentsService.class);
        ProtocolForm protocolForm = (ProtocolForm)form;
        ProtocolOnlineReviewDocument prDoc = protocolForm.getOnlineReviewsActionHelper().getDocumentFromHelperMap(onlineReviewDocumentNumber);
        ReviewerComments comments = protocolForm.getOnlineReviewsActionHelper().getReviewerCommentsFromHelperMap(onlineReviewDocumentNumber);
        comments.moveUp(onlineReviewCommentIndex, onlineReviewCommentIndex);
        reviewerCommentsService.persistReviewerComments(comments, protocolForm.getProtocolDocument().getProtocol(), prDoc.getProtocolOnlineReview());
        documentService.saveDocument(prDoc);
         
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
        
        String onlineReviewDocumentNumber = getOnlineReviewActionDocumentNumber((String)request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE),"moveDownOnlineReviewComment");
        int onlineReviewCommentIndex = getOnlineReviewActionIndexNumber((String)request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE),"moveDownOnlineReviewComment");
        DocumentService documentService = KraServiceLocator.getService(DocumentService.class);
        ReviewerCommentsService reviewerCommentsService = KraServiceLocator.getService(ReviewerCommentsService.class);
        ProtocolForm protocolForm = (ProtocolForm)form;
      
        ProtocolOnlineReviewDocument prDoc = protocolForm.getOnlineReviewsActionHelper().getDocumentFromHelperMap(onlineReviewDocumentNumber);
        ReviewerComments comments = protocolForm.getOnlineReviewsActionHelper().getReviewerCommentsFromHelperMap(onlineReviewDocumentNumber);
        comments.moveDown(onlineReviewCommentIndex, onlineReviewCommentIndex);
        reviewerCommentsService.persistReviewerComments(comments, protocolForm.getProtocolDocument().getProtocol(), prDoc.getProtocolOnlineReview());
        documentService.saveDocument(prDoc);
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
        String onlineReviewDocumentNumber = getOnlineReviewActionDocumentNumber((String)request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE),"deleteOnlineReviewComment");
        int onlineReviewCommentIndex = getOnlineReviewActionIndexNumber((String)request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE),"deleteOnlineReviewComment");
        DocumentService documentService = KraServiceLocator.getService(DocumentService.class);
        ReviewerCommentsService reviewerCommentsService = KraServiceLocator.getService(ReviewerCommentsService.class);
        ProtocolForm protocolForm = (ProtocolForm)form;
        ProtocolOnlineReviewDocument prDoc = (ProtocolOnlineReviewDocument) documentService.getByDocumentHeaderId(onlineReviewDocumentNumber);
        ReviewerComments comments = protocolForm.getOnlineReviewsActionHelper().getReviewerCommentsFromHelperMap(onlineReviewDocumentNumber);
        comments.deleteComment(onlineReviewCommentIndex);
        reviewerCommentsService.persistReviewerComments(comments, protocolForm.getProtocolDocument().getProtocol(), prDoc.getProtocolOnlineReview());
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
    
    private TaskAuthorizationService getTaskAuthorizationService() {
        return KraServiceLocator.getService(TaskAuthorizationService.class);
    }
    
    
    private ReviewerCommentsService getReviewerCommentsService() {
        return KraServiceLocator.getService(ReviewerCommentsService.class);
    }
    
    private ProtocolOnlineReviewService getProtocolOnlineReviewService() {
        return KraServiceLocator.getService( ProtocolOnlineReviewService.class );
    }
    
    private KraAuthorizationService getKraAuthorizationService() {
        return KraServiceLocator.getService(KraAuthorizationService.class);
    }
    
    private KraWorkflowService getKraWorkflowService() {
        return KraServiceLocator.getService(KraWorkflowService.class);
    }

}
