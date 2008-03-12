/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.web.struts.action;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.RiceConstants;
import org.kuali.core.question.ConfirmationQuestion;
import org.kuali.core.rule.event.DocumentAuditEvent;
import org.kuali.core.service.DocumentService;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.service.KualiRuleService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.web.struts.action.AuditModeAction;
import org.kuali.core.web.struts.form.KualiDocumentFormBase;
import org.kuali.core.web.struts.form.KualiForm;
import org.kuali.core.workflow.service.KualiWorkflowDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.ProposalCopyCriteria;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalCopyService;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.rice.KNSServiceLocator;

import edu.iu.uis.eden.EdenConstants;
import edu.iu.uis.eden.KEWServiceLocator;
import edu.iu.uis.eden.actionrequests.ActionRequestValue;
import edu.iu.uis.eden.clientapp.FutureRequestDocumentStateManager;
import edu.iu.uis.eden.clientapp.WorkflowDocument;
import edu.iu.uis.eden.clientapp.WorkflowInfo;
import edu.iu.uis.eden.clientapp.vo.ActionRequestVO;
import edu.iu.uis.eden.clientapp.vo.DocumentDetailVO;
import edu.iu.uis.eden.clientapp.vo.NetworkIdVO;
import edu.iu.uis.eden.clientapp.vo.ReportActionToTakeVO;
import edu.iu.uis.eden.clientapp.vo.ReportCriteriaVO;
import edu.iu.uis.eden.clientapp.vo.RouteNodeInstanceVO;
import edu.iu.uis.eden.clientapp.vo.UserIdVO;
import edu.iu.uis.eden.clientapp.vo.UserVO;
import edu.iu.uis.eden.clientapp.vo.UuIdVO;
import edu.iu.uis.eden.clientapp.vo.WorkgroupVO;
import edu.iu.uis.eden.doctype.DocumentType;
import edu.iu.uis.eden.doctype.DocumentTypeService;
import edu.iu.uis.eden.engine.node.KeyValuePair;
import edu.iu.uis.eden.engine.node.RouteNode;
import edu.iu.uis.eden.engine.simulation.SimulationCriteria;
import edu.iu.uis.eden.engine.simulation.SimulationEngine;
import edu.iu.uis.eden.engine.simulation.SimulationResults;
import edu.iu.uis.eden.exception.WorkflowException;
import edu.iu.uis.eden.routetemplate.RuleTemplate;
import edu.iu.uis.eden.routetemplate.RuleTemplateService;
import edu.iu.uis.eden.server.BeanConverter;
import edu.iu.uis.eden.user.WorkflowUser;
import edu.iu.uis.eden.util.Utilities;
import edu.iu.uis.eden.util.Utilities.RouteLogActionRequestSorter;
import edu.iu.uis.eden.workgroup.WorkflowGroupId;
import edu.iu.uis.eden.workgroup.Workgroup;
import edu.iu.uis.eden.workgroup.WorkgroupService;

/**
 * Handles all of the actions from the Proposal Development Actions web page.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProposalDevelopmentActionsAction extends ProposalDevelopmentAction implements AuditModeAction {
    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentActionsAction.class);
    private static final String DOCUMENT_APPROVE_QUESTION = "DocApprove";

    /**
     * Struts mapping for the Proposal web page.  
     */
    private static final String MAPPING_PROPOSAL = "proposal";

    /**
     * Calls the document service to approve the document
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    @Override
    public ActionForward approve(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        KualiDocumentFormBase kualiDocumentFormBase = (KualiDocumentFormBase) form;
        KualiWorkflowDocument workflowDoc = kualiDocumentFormBase.getDocument().getDocumentHeader().getWorkflowDocument();

        if (canGenerateRequestsInFuture(workflowDoc)) {
            return promptUserForInput(workflowDoc, mapping, form, request, response);
        }

        return super.approve(mapping, form, request, response);
    }

    private ActionForward promptUserForInput(KualiWorkflowDocument workflowDoc, ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        Object question = request.getParameter(RiceConstants.QUESTION_INST_ATTRIBUTE_NAME);
        Object buttonClicked = request.getParameter(RiceConstants.QUESTION_CLICKED_BUTTON);
        String methodToCall = ((KualiForm) form).getMethodToCall();
        
        if (question == null) {
            // ask question if not already asked
              return this.performQuestionWithoutInput(mapping, form, request, response, DOCUMENT_APPROVE_QUESTION, KNSServiceLocator
                      .getKualiConfigurationService().getPropertyString(KeyConstants.QUESTION_APPROVE_FUTURE_REQUESTS),
                       RiceConstants.CONFIRMATION_QUESTION, methodToCall, "");             
        }
        else if(DOCUMENT_APPROVE_QUESTION.equals(question) && ConfirmationQuestion.NO.equals(buttonClicked)) {
            workflowDoc.setDoNotReceiveFutureRequests();
        }
        else if(DOCUMENT_APPROVE_QUESTION.equals(question)){ 
            workflowDoc.setReceiveFutureRequests();  
        }
        
        return super.approve(mapping, form, request, response);
    }   

    private boolean canGenerateRequestsInFuture(KualiWorkflowDocument workflowDoc) throws Exception {
        WorkflowInfo info = new WorkflowInfo();
        String loggedInUserID = GlobalVariables.getUserSession().getUniversalUser().getPersonUniversalIdentifier();
        NetworkIdVO networkId = new NetworkIdVO(loggedInUserID);
        ReportCriteriaVO reportCriteria = new ReportCriteriaVO(new Long(workflowDoc.getRouteHeaderId()));
        reportCriteria.setTargetUsers(new UserIdVO[] { networkId });

        boolean receiveFutureRequests = false;
        boolean doNotReceiveFutureRequests = false;    

        List variables = workflowDoc.getRouteHeader().getVariables();
        if (CollectionUtils.isNotEmpty(variables)) {
            for (Object variable : variables) {
                KeyValuePair kvp = (KeyValuePair) variable;
                if (kvp.getKey().startsWith(EdenConstants.RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_KEY)
                        && kvp.getValue().toUpperCase().equals(EdenConstants.RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_VALUE)
                        && kvp.getKey().contains(networkId.getNetworkId())) {
                    receiveFutureRequests = true;  
                }
                else if (kvp.getKey().startsWith(EdenConstants.RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_KEY)
                      && kvp.getValue().toUpperCase().equals(EdenConstants.DONT_RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_VALUE)
                      && kvp.getKey().contains(networkId.getNetworkId())) {
                    doNotReceiveFutureRequests = true;  
                }
            }
        } 

        return (canGenerateMultipleApprovalRequests(info, reportCriteria, networkId) && (receiveFutureRequests == false && doNotReceiveFutureRequests == false));
    }
    
    private boolean canGenerateMultipleApprovalRequests(WorkflowInfo info, ReportCriteriaVO reportCriteria, NetworkIdVO networkId) throws Exception {
        int approvalRequestsCount = 0;
        
        DocumentDetailVO results1 = info.routingReport(reportCriteria);
        for(ActionRequestVO actionRequest : results1.getActionRequests() ){
            if(!actionRequest.isRoleRequest() && actionRequest.isPending() && actionRequest.getActionRequested().equalsIgnoreCase(EdenConstants.ACTION_REQUEST_APPROVE_REQ) && 
                    recipientMatchesUser(actionRequest, networkId)) {
                approvalRequestsCount+=1; 
            }
        }
          
        return (approvalRequestsCount > 1);
    }
    
    private boolean recipientMatchesUser(ActionRequestVO actionRequest, NetworkIdVO networkId) {
        if(actionRequest != null && networkId != null) {
            UserVO recipientUser = actionRequest.getUserVO();
            if(recipientUser != null && recipientUser.getNetworkId().equals(networkId.getNetworkId())) {
                return true;
            } else {
                WorkgroupVO recipientGroup = actionRequest.getWorkgroupVO();
                if(recipientGroup != null && recipientGroup.getMembers().length > 0) {
                    for(UserVO member: recipientGroup.getMembers()) {
                        if(member != null && member.getNetworkId().equals(networkId.getNetworkId())) {
                            return true;
                        } 
                    }
                }
            }
        }
        
        return false;
    }

    /**
     * Copies a Proposal Development Document based upon user-specified criteria.
     * 
     * @param mapping the Struct's Action Mapping.
     * @param form the Proposal Development Form.
     * @param request the HTTP request.
     * @param response the HTTP response
     * @return the next web page to display
     * @throws Exception
     */
    public ActionForward copyProposal(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ActionForward nextWebPage = null;

        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument doc = proposalDevelopmentForm.getProposalDevelopmentDocument();
        ProposalCopyCriteria criteria = proposalDevelopmentForm.getCopyCriteria();

        // Use the Copy Service to copy the proposal.

        ProposalCopyService proposalCopyService = (ProposalCopyService) KraServiceLocator.getService("proposalCopyService");
        if (proposalCopyService == null) {

            // Something bad happened. The errors are in the Global Error Map
            // which will be displayed to the user.

            nextWebPage = mapping.findForward(Constants.MAPPING_BASIC);
        }
        else {
            String newDocId = proposalCopyService.copyProposal(doc, criteria);

            // Switch over to the new proposal development document and
            // go to the Proposal web page.

            proposalDevelopmentForm.setDocId(newDocId);
            this.loadDocument(proposalDevelopmentForm);

            nextWebPage = mapping.findForward(MAPPING_PROPOSAL);
        }

        return nextWebPage;
    }

    /**
     * @see org.kuali.core.web.struts.action.AuditModeAction#activate(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ActionForward activate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        proposalDevelopmentForm.setAuditActivated(true);

        KraServiceLocator.getService(KualiRuleService.class).applyRules(
                new DocumentAuditEvent(proposalDevelopmentForm.getDocument()));

        return mapping.findForward(RiceConstants.MAPPING_BASIC);
    }

    /**
     * @see org.kuali.core.web.struts.action.AuditModeAction#deactivate(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ActionForward deactivate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        proposalDevelopmentForm.setAuditActivated(false);

        return mapping.findForward((RiceConstants.MAPPING_BASIC));
    }


}
