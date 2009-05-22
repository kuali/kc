/*
 * Copyright 2006-2008 The Kuali Foundation
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.authorization.ApplicationTask;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.ProtocolAction;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.actions.copy.ProtocolCopyService;
import org.kuali.kra.irb.actions.notifyirb.ProtocolNotifyIrbService;
import org.kuali.kra.irb.actions.request.ProtocolRequestService;
import org.kuali.kra.irb.actions.submit.ProtocolReviewType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitAction;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitActionEvent;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitActionService;
import org.kuali.kra.irb.actions.withdraw.ProtocolWithdrawService;
import org.kuali.kra.irb.auth.ProtocolTask;
import org.kuali.kra.web.struts.action.AuditActionHelper;
import org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase;
import org.kuali.kra.web.struts.action.StrutsConfirmation;
import org.kuali.rice.kns.web.struts.action.AuditModeAction;

/**
 * The set of actions for the Protocol Actions tab.
 */
public class ProtocolProtocolActionsAction extends ProtocolAction implements AuditModeAction {

    private static final String PROTOCOL_TAB = "protocol";
    private static final String CONFIRM_SUBMIT_FOR_REVIEW_KEY = "confirmSubmitForReview";
    
    /**
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);

        ((ProtocolForm)form).getActionHelper().prepareView();
        
        return actionForward;
    }
    
    /**
     * Invoked when the "copy protocol" button is clicked.
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
            ProtocolCopyService protocolCopyService = KraServiceLocator.getService(ProtocolCopyService.class);
            String newDocId = protocolCopyService.copyProtocol(protocolForm.getDocument());
            
            // Switch over to the new protocol document and
            // go to the Protocol tab web page.
            
            protocolForm.setDocId(newDocId);
            loadDocument(protocolForm);
        
            return mapping.findForward(PROTOCOL_TAB);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    
    /** {@inheritDoc} */
    public ActionForward activate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {        
        return new AuditActionHelper().setAuditMode(mapping, (ProtocolForm) form, true);
    }

    /** {@inheritDoc} */
    public ActionForward deactivate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return new AuditActionHelper().setAuditMode(mapping, (ProtocolForm) form, false);
    }
    
    /**
     * Refreshes the page.  We only need to redraw the page.  This method is
     * used when JavaScript is disabled.  During a review submission action,
     * the user will have to refresh the page.  For example, after a committee
     * is selected, the page needs to be refreshed so that the available 
     * scheduled dates for that committee can be displayed in the drop-down
     * menu for the scheduled dates.  Please see ProtocolSubmitAction.prepareView()
     * for how the Submit for Review works on a refresh.
     * 
     * @param mapping the mapping associated with this action.
     * @param form the Protocol form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the name of the HTML page to display
     * @throws Exception
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
    public ActionForward submitForReview(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolTask task = new ProtocolTask(TaskName.SUBMIT_PROTOCOL, protocolForm.getProtocolDocument().getProtocol());
        if (isAuthorized(task)) {
            ProtocolSubmitAction submitAction = protocolForm.getActionHelper().getProtocolSubmitAction();
            if (applyRules(new ProtocolSubmitActionEvent(protocolForm.getProtocolDocument(), submitAction))) {
                if (isCommitteeMeetingAssignedMaxProtocols(submitAction.getCommitteeId(), submitAction.getScheduleId())) {
                    return confirm(buildSubmitForReviewConfirmationQuestion(mapping, form, request, response), CONFIRM_SUBMIT_FOR_REVIEW_KEY, "");
                }
                getProtocolActionService().submitToIrbForReview(protocolForm.getProtocolDocument().getProtocol(),
                                                                submitAction);
            }
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    private ProtocolSubmitActionService getProtocolActionService() {
        return KraServiceLocator.getService(ProtocolSubmitActionService.class);
    }

    /*
     * Builds the confirmation question to verify if the user wants to submit the protocol for review.
     */
    private StrutsConfirmation buildSubmitForReviewConfirmationQuestion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_SUBMIT_FOR_REVIEW_KEY, KeyConstants.QUESTION_PROTOCOL_CONFIRM_SUBMIT_FOR_REVIEW);
    }

    /**
     * Method dispatched from <code>{@link KraTransactionalDocumentActionBase#confirm(StrutsQuestion, String, String)}</code> for when a "yes" condition is met.
     * 
     * @param mapping The mapping associated with this action.
     * @param form The Protocol form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the destination (always the original Protocol Document web page that caused this action to be invoked)
     * @throws Exception
     * @see KraTransactionalDocumentActionBase#confirm(StrutsQuestion, String, String)
     */
    public ActionForward confirmSubmitForReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Object question = request.getParameter(QUESTION_INST_ATTRIBUTE_NAME);
        
        if (CONFIRM_SUBMIT_FOR_REVIEW_KEY.equals(question)) {
            ProtocolForm protocolForm = (ProtocolForm) form;
            ProtocolSubmitAction submitAction = protocolForm.getActionHelper().getProtocolSubmitAction();
            getProtocolActionService().submitToIrbForReview(protocolForm.getProtocolDocument().getProtocol(),
                                                            submitAction);
        }
        
        return mapping.findForward(MAPPING_BASIC);
    }        

    private boolean isCommitteeMeetingAssignedMaxProtocols(String committeeId, String scheduleId) {
        return false;
    }

    /**
     * Display the description for an Expedited Review Check List Item.
     * 
     * @param mapping the mapping associated with this action.
     * @param form the Protocol form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the name of the HTML page to display
     * @throws Exception
     */
    public ActionForward getExpeditedReviewDescription(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        protocolForm.getActionHelper().getProtocolSubmitAction().setCheckListItemDescriptionInfo(ProtocolReviewType.EXPEDITED_REVIEW_TYPE_CODE,
                                                                                                 getLineNum(request));
        
        return mapping.findForward(Constants.MAPPING_CHECKLIST_ITEM_DESCRIPITION);
    }
    
    /**
     * Display the description for an Exempt Studies Check List Item.
     * 
     * @param mapping the mapping associated with this action.
     * @param form the Protocol form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the name of the HTML page to display
     * @throws Exception
     */
    public ActionForward getExemptStudiesDescription(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        protocolForm.getActionHelper().getProtocolSubmitAction().setCheckListItemDescriptionInfo(ProtocolReviewType.EXEMPT_STUDIES_REVIEW_TYPE_CODE,
                                                                                                 getLineNum(request));
        
        return mapping.findForward(Constants.MAPPING_CHECKLIST_ITEM_DESCRIPITION);
    }
    
    /**
     * Withdraw a previously submitted protocol.
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
        getProtocolWithdrawService().withdraw(protocolForm.getProtocolDocument().getProtocol(), protocolForm.getActionHelper().getProtocolWithdrawBean());

        return mapping.findForward(MAPPING_BASIC);
    }
    
    private ProtocolWithdrawService getProtocolWithdrawService() {
        return KraServiceLocator.getService(ProtocolWithdrawService.class);
    }
    
    /**
     * Request a close of the protocol.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward closeRequestProtocol(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        getProtocolRequestService().submitRequest(protocolForm.getProtocolDocument().getProtocol(), protocolForm.getActionHelper().getProtocolCloseRequestBean());
        return mapping.findForward(MAPPING_BASIC);
    }
    
    /**
     * Request a suspension of a protocol.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward suspendRequestProtocol(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        getProtocolRequestService().submitRequest(protocolForm.getProtocolDocument().getProtocol(), protocolForm.getActionHelper().getProtocolSuspendRequestBean());
        return mapping.findForward(MAPPING_BASIC);
    }
    
    /**
     * Request a close of enrollment for a protocol.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward closeEnrollmentRequestProtocol(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        getProtocolRequestService().submitRequest(protocolForm.getProtocolDocument().getProtocol(), protocolForm.getActionHelper().getProtocolCloseEnrollmentRequestBean());
        return mapping.findForward(MAPPING_BASIC);
    }
    
    /**
     * Request to re-open enrollment for a protocol.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward reopenEnrollmentRequestProtocol(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        getProtocolRequestService().submitRequest(protocolForm.getProtocolDocument().getProtocol(), protocolForm.getActionHelper().getProtocolReOpenEnrollmentRequestBean());
        return mapping.findForward(MAPPING_BASIC);
    }
    
    /**
     * Request for data analysis only for a protocol.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward dataAnalysisOnlyRequestProtocol(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        getProtocolRequestService().submitRequest(protocolForm.getProtocolDocument().getProtocol(), protocolForm.getActionHelper().getProtocolDataAnalysisRequestBean());
        return mapping.findForward(MAPPING_BASIC);
    }
    
    private ProtocolRequestService getProtocolRequestService() {
        return KraServiceLocator.getService(ProtocolRequestService.class);
    }
    
    /**
     * Notify the IRB office.
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
        getProtocolNotifyIrbService().submitIrbNotification(protocolForm.getProtocolDocument().getProtocol(), protocolForm.getActionHelper().getProtocolNotifyIrbBean());
        return mapping.findForward(MAPPING_BASIC);
    }
    
    private ProtocolNotifyIrbService getProtocolNotifyIrbService() {
        return KraServiceLocator.getService(ProtocolNotifyIrbService.class);
    }

    /**
     * Get the line number.
     * 
     * @param request the HTTP request
     * @return the line number
     */
    private int getLineNum(HttpServletRequest request) {
        
        // If JavaScript is enabled, the line is returned to the web server
        // as an HTTP parameter.  If not, it is embedded within the "methodToCall" syntax.
        
        String lineNumStr = request.getParameter("line");
        try {
            return Integer.parseInt(lineNumStr);
        } catch (Exception ex) {
            return this.getLineToDelete(request);
        }
    }
}
