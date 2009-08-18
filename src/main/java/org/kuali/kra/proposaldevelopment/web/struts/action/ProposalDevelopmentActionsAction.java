/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.web.struts.action;

import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.bo.SponsorFormTemplate;
import org.kuali.kra.bo.SponsorFormTemplateList;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.budget.versions.BudgetVersionOverview;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.kim.service.KIMService;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.proposaldevelopment.bo.ProposalChangedData;
import org.kuali.kra.proposaldevelopment.bo.ProposalCopyCriteria;
import org.kuali.kra.proposaldevelopment.bo.ProposalOverview;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.event.CopyProposalEvent;
import org.kuali.kra.proposaldevelopment.rule.event.ProposalDataOverrideEvent;
import org.kuali.kra.proposaldevelopment.service.ProposalCopyService;
import org.kuali.kra.proposaldevelopment.service.ProposalStateService;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.bo.S2sAppSubmission;
import org.kuali.kra.s2s.bo.S2sSubmissionHistory;
import org.kuali.kra.s2s.service.PrintService;
import org.kuali.kra.s2s.service.S2SService;
import org.kuali.kra.service.KraPersistenceStructureService;
import org.kuali.kra.web.struts.action.AuditActionHelper;
import org.kuali.kra.web.struts.action.StrutsConfirmation;
import org.kuali.rice.kew.dto.ActionRequestDTO;
import org.kuali.rice.kew.dto.DocumentDetailDTO;
import org.kuali.rice.kew.dto.NetworkIdDTO;
import org.kuali.rice.kew.dto.ReportCriteriaDTO;
import org.kuali.rice.kew.engine.node.KeyValuePair;
import org.kuali.rice.kew.engine.node.RouteNodeInstance;
import org.kuali.rice.kew.service.KEWServiceLocator;
import org.kuali.rice.kew.service.WorkflowInfo;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.service.KualiRuleService;
import org.kuali.rice.kns.service.PersistenceStructureService;
import org.kuali.rice.kns.service.PessimisticLockService;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.ObjectUtils;
import org.kuali.rice.kns.util.WebUtils;
import org.kuali.rice.kns.web.struts.action.AuditModeAction;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;

/**
 * Handles all of the actions from the Proposal Development Actions web page.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProposalDevelopmentActionsAction extends ProposalDevelopmentAction implements AuditModeAction {
    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentActionsAction.class);
    private static final String DOCUMENT_APPROVE_QUESTION = "DocApprove";
    private static final String DOCUMENT_ROUTE_QUESTION="DocRoute";
    private static final String DOCUMENT_REJECT_QUESTION="DocReject";
    
    private static final String CONFIRM_SUBMISSION_WITH_WARNINGS_KEY = "submitApplication";
    private static final String EMPTY_STRING = "";
    
    private static final int OK = 0;
    private static final int WARNING = 1;
    private static final int ERROR = 2;
    
    private KIMService kimService;
    
    /**
     * Struts mapping for the Proposal web page.  
     */
    private static final String MAPPING_PROPOSAL = "proposal";
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);
        return actionForward;
    }

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
        Object question = request.getParameter(KNSConstants.QUESTION_INST_ATTRIBUTE_NAME);
        Object buttonClicked = request.getParameter(KNSConstants.QUESTION_CLICKED_BUTTON);
        String methodToCall = ((KualiForm) form).getMethodToCall();
        
        if (question == null) {
            // ask question if not already asked
              return this.performQuestionWithoutInput(mapping, form, request, response, DOCUMENT_APPROVE_QUESTION, KNSServiceLocator
                      .getKualiConfigurationService().getPropertyString(KeyConstants.QUESTION_APPROVE_FUTURE_REQUESTS),
                       KNSConstants.CONFIRMATION_QUESTION, methodToCall, "");             
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
        String loggedInUserID = (new UniversalUser (GlobalVariables.getUserSession().getPerson())).getPersonUniversalIdentifier();
        String loggedInPrincipalId = GlobalVariables.getUserSession().getPrincipalId();
        NetworkIdDTO networkId = new NetworkIdDTO(loggedInUserID);
        ReportCriteriaDTO reportCriteria = new ReportCriteriaDTO(new Long(workflowDoc.getRouteHeaderId()));
        reportCriteria.setTargetPrincipalIds(new String[] { loggedInPrincipalId });

        boolean receiveFutureRequests = false;
        boolean doNotReceiveFutureRequests = false;    

        List variables = workflowDoc.getRouteHeader().getVariables();
        if (CollectionUtils.isNotEmpty(variables)) {
            for (Object variable : variables) {
                KeyValuePair kvp = (KeyValuePair) variable;
                if (kvp.getKey().startsWith(KEWConstants.RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_KEY)
                        && kvp.getValue().toUpperCase().equals(KEWConstants.RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_VALUE)
                        && kvp.getKey().contains(networkId.getNetworkId())) {
                    receiveFutureRequests = true; 
                    break;
                }
                else if (kvp.getKey().startsWith(KEWConstants.RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_KEY)
                      && kvp.getValue().toUpperCase().equals(KEWConstants.DONT_RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_VALUE)
                      && kvp.getKey().contains(networkId.getNetworkId())) {
                    doNotReceiveFutureRequests = true; 
                    break;
                }
            }
        } 

        return ((receiveFutureRequests == false && doNotReceiveFutureRequests == false) && canGenerateMultipleApprovalRequests(reportCriteria, networkId));
    }
    
    private boolean canGenerateMultipleApprovalRequests(ReportCriteriaDTO reportCriteria, NetworkIdDTO networkId) throws Exception {
        int approvalRequestsCount = 0;
        WorkflowInfo info = new WorkflowInfo();
        
        DocumentDetailDTO results1 = info.routingReport(reportCriteria);
        for(ActionRequestDTO actionRequest : results1.getActionRequests() ){
            if(!actionRequest.isRoleRequest() && actionRequest.isPending() && actionRequest.getActionRequested().equalsIgnoreCase(KEWConstants.ACTION_REQUEST_APPROVE_REQ) && 
                    recipientMatchesUser(actionRequest, networkId)) {
                approvalRequestsCount+=1; 
            }
        }
          
        return (approvalRequestsCount > 1);
    }
    
    private boolean recipientMatchesUser(ActionRequestDTO actionRequest, NetworkIdDTO networkId) {
        if(actionRequest != null && networkId != null) {
            //UserVO recipientUser = actionRequest.get getUserVO();
            String recipientUser = actionRequest.getPrincipalId();
            if(recipientUser != null && recipientUser.equals(networkId.getNetworkId())) {
                return true;
            } else {
                String recipientGroupId = actionRequest.getGroupId();
                //WorkgroupVO recipientGroup = actionRequest.getWorkgroupVO();
                KIMService kimService = getKimService();
                List<String> groupMembers = kimService.getGroupMemberPrincipalIds(recipientGroupId);
                if(groupMembers != null && groupMembers.size() > 0) {
                    for(String member: groupMembers) {
                        if(member != null && member.equals(networkId.getNetworkId())) {
                            return true;
                        } 
                    }
                }
            }
        }
        
        return false;  
    }
    
    private KIMService getKimService() {
        if (kimService != null) {
            return kimService;
        }
        kimService = KraServiceLocator.getService(KIMService.class);
        return kimService;
    }
    
    /**
     * Updates an Editable Proposal Field and 
     * adds it to the Proposal Change History
     * 
     * @param mapping the Struct's Action Mapping.
     * @param form the Proposal Development Form.
     * @param request the HTTP request.
     * @param response the HTTP response
     * @return the next web page to display
     * @throws Exception
     */
    public ActionForward addProposalChangedData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        BusinessObjectService boService = KraServiceLocator.getService(BusinessObjectService.class);
        KraPersistenceStructureService kraPersistenceStructureService = KraServiceLocator.getService(KraPersistenceStructureService.class);

        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument pdDocument = pdForm.getDocument();
        ProposalChangedData newProposalChangedData = pdForm.getNewProposalChangedData();
        
        newProposalChangedData.setProposalNumber(pdDocument.getDevelopmentProposal().getProposalNumber());
        newProposalChangedData.setChangeNumber(getNextChangeNumber(boService, newProposalChangedData.getProposalNumber(), newProposalChangedData.getColumnName()));
        if(StringUtils.isEmpty(newProposalChangedData.getDisplayValue()) && StringUtils.isNotEmpty(newProposalChangedData.getChangedValue())) {
            newProposalChangedData.setDisplayValue(newProposalChangedData.getChangedValue());
        }
        
        String tmpLookupReturnPk = "";
        if(newProposalChangedData.getEditableColumn() != null) {
            tmpLookupReturnPk = newProposalChangedData.getEditableColumn().getLookupPkReturn();
        }
        
        newProposalChangedData.refreshReferenceObject("editableColumn");
        newProposalChangedData.getEditableColumn().setLookupPkReturn(tmpLookupReturnPk);
        
        if(newProposalChangedData.getEditableColumn() != null) {
            if(!newProposalChangedData.getEditableColumn().getHasLookup()) {
                newProposalChangedData.setDisplayValue(newProposalChangedData.getChangedValue());
            }
        }
            
        if(getKualiRuleService().applyRules(new ProposalDataOverrideEvent(pdForm.getDocument(), newProposalChangedData))){
            boService.save(newProposalChangedData);
        
            ProposalOverview proposalWrapper = createProposalWrapper(pdDocument);
            Map<String, String> columnToAttributesMap = kraPersistenceStructureService.getDBColumnToObjectAttributeMap(ProposalOverview.class);
            String proposalAttributeToPersist = columnToAttributesMap.get(newProposalChangedData.getColumnName());
            ObjectUtils.setObjectProperty(proposalWrapper, proposalAttributeToPersist, newProposalChangedData.getChangedValue());
            ObjectUtils.setObjectProperty(pdDocument, proposalAttributeToPersist, newProposalChangedData.getChangedValue());
            
            boService.save(proposalWrapper);
            pdForm.getDocument().setVersionNumber(proposalWrapper.getVersionNumber());
            
            pdForm.setNewProposalChangedData(new ProposalChangedData());
            growProposalChangedHistory(pdDocument, newProposalChangedData);
        }
        
        return mapping.findForward("basic");
    }
    
    private void growProposalChangedHistory(ProposalDevelopmentDocument pdDocument, ProposalChangedData newProposalChangedData) {
        Map<String, List<ProposalChangedData>> changeHistory = pdDocument.getDevelopmentProposal().getProposalChangeHistory();
        if(changeHistory.get(newProposalChangedData.getEditableColumn().getColumnLabel()) == null) {
            changeHistory.put(newProposalChangedData.getEditableColumn().getColumnLabel(), new ArrayList<ProposalChangedData>());
        } 
        
        changeHistory.get(newProposalChangedData.getEditableColumn().getColumnLabel()).add(0, newProposalChangedData);
    }
    
    private ProposalOverview createProposalWrapper(ProposalDevelopmentDocument pdDocument) throws Exception {
        ProposalOverview proposalWrapper = new ProposalOverview();
        PersistenceStructureService persistentStructureService = KraServiceLocator.getService(PersistenceStructureService.class);
        List<String> fieldsToUpdate = (List<String>) persistentStructureService.listFieldNames(ProposalOverview.class);
        Object tempVal;
        for(String field: fieldsToUpdate) {
            tempVal = ObjectUtils.getPropertyValue(pdDocument, field);
            ObjectUtils.setObjectProperty(proposalWrapper, field, (tempVal != null) ? tempVal.toString() : null);
        }
        return proposalWrapper;
    } 
    
    private int getNextChangeNumber(BusinessObjectService boService, String proposalNumber, String columnName) {
        int changeNumber = 0;
        Map<String, Object> keyMap = new HashMap<String, Object>();
        keyMap.put("proposalNumber", proposalNumber);
        keyMap.put("columnName", columnName);
        List<ProposalChangedData> changedDataList = (List<ProposalChangedData>) boService.findMatchingOrderBy(ProposalChangedData.class, keyMap, "changeNumber", true);
        if(CollectionUtils.isNotEmpty(changedDataList)) {
            changeNumber = ((ProposalChangedData) changedDataList.get(changedDataList.size()-1)).getChangeNumber();
        }
        
        return ++changeNumber;
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
        ProposalDevelopmentDocument doc = proposalDevelopmentForm.getDocument();
        ProposalCopyCriteria criteria = proposalDevelopmentForm.getCopyCriteria();
        
        // check any business rules
        boolean rulePassed = getKualiRuleService().applyRules(new CopyProposalEvent(doc, criteria));
        
        if (!rulePassed) {
            nextWebPage = mapping.findForward(Constants.MAPPING_BASIC);
        }
        else {
            // Use the Copy Service to copy the proposal.
            
            ProposalCopyService proposalCopyService = (ProposalCopyService) KraServiceLocator.getService("proposalCopyService");
            if (proposalCopyService == null) {
                
                // Something bad happened. The errors are in the Global Error Map
                // which will be displayed to the user.
                
                nextWebPage = mapping.findForward(Constants.MAPPING_BASIC);
            }
            else {
                String newDocId = proposalCopyService.copyProposal(doc, criteria);
                KraServiceLocator.getService(PessimisticLockService.class).releaseAllLocksForUser(doc.getPessimisticLocks(), new UniversalUser (GlobalVariables.getUserSession().getPerson()));
                
                // Switch over to the new proposal development document and
                // go to the Proposal web page.
                
                proposalDevelopmentForm.setDocId(newDocId);
                this.loadDocument(proposalDevelopmentForm);
                
                ProposalDevelopmentDocument copiedDocument = proposalDevelopmentForm.getDocument();
                initializeProposalUsers(copiedDocument);//add in any default permissions
                copiedDocument.getDevelopmentProposal().setS2sAppSubmission(new ArrayList<S2sAppSubmission>());            
                
                DocumentService docService = KraServiceLocator.getService(DocumentService.class);
                docService.saveDocument(copiedDocument);
                
                nextWebPage = mapping.findForward(MAPPING_PROPOSAL);
                
                // Helper method to clear document form data.
                proposalDevelopmentForm.clearDocumentRelatedState();
            
            }
        }

        return nextWebPage;
    }

    /** {@inheritDoc} */
    public ActionForward activate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return new AuditActionHelper().setAuditMode(mapping, (ProposalDevelopmentForm) form, true);
    }

    /** {@inheritDoc} */
    public ActionForward deactivate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return new AuditActionHelper().setAuditMode(mapping, (ProposalDevelopmentForm) form, false);
    }
    
    public ActionForward reject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String NodeName = null;
        KualiDocumentFormBase kualiDocumentFormBase = (KualiDocumentFormBase) form;
        KualiWorkflowDocument workflowdocument=kualiDocumentFormBase.getDocument().getDocumentHeader().getWorkflowDocument();
/*
        Long routeHeaderId=kualiDocumentFormBase.getDocument().getDocumentHeader().getWorkflowDocument().getRouteHeaderId();
        List currentNodeInstances = KEWServiceLocator.getRouteNodeService().getCurrentNodeInstances(routeHeaderId);
        List<RouteNodeInstance> nodeInstances = new ArrayList<RouteNodeInstance>();

        for (Iterator iterator = currentNodeInstances.iterator(); iterator.hasNext();) {
           RouteNodeInstance nodeInstance = (RouteNodeInstance) iterator.next();
           NodeName= nodeInstance.getRouteNode().getRouteNodeName();
        }
*/        
        
        Object question = request.getParameter(KNSConstants.QUESTION_INST_ATTRIBUTE_NAME);
        Object buttonClicked = request.getParameter(KNSConstants.QUESTION_CLICKED_BUTTON);
        String reason = request.getParameter(KNSConstants.QUESTION_REASON_ATTRIBUTE_NAME);
        String methodToCall = ((KualiForm) form).getMethodToCall();
        String rejectNoteText = "";
        if(question == null){
            return this.performQuestionWithInput(mapping, form, request, response, DOCUMENT_REJECT_QUESTION,"Are you sure you want to reject this document?" , KNSConstants.CONFIRMATION_QUESTION, methodToCall, "");
         } 
        else if((DOCUMENT_REJECT_QUESTION.equals(question)) && ConfirmationQuestion.NO.equals(buttonClicked))  {
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
        else
        {
            String introNoteMessage = "Proposal Rejected" + KNSConstants.BLANK_SPACE;
            rejectNoteText = introNoteMessage + reason;
//            workflowdocument.returnToPreviousNode(rejectNoteText, NodeName);
		// Using deprecated method because nothing else available works
            workflowdocument.returnToPreviousRouteLevel(rejectNoteText, 0);
            return super.returnToSender(mapping, kualiDocumentFormBase);
        }
        
       
    }
    
    
    /**
     * route the document using the document service
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    @Override
    public ActionForward route(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean success;
        
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument pdDoc = proposalDevelopmentForm.getDocument();
        
        proposalDevelopmentForm.setAuditActivated(true);
        //success=KraServiceLocator.getService(KualiRuleService.class).applyRules(new DocumentAuditEvent(proposalDevelopmentForm.getDocument()));
        //HashMap map=GlobalVariables.getAuditErrorMap();
        Object question = request.getParameter(KNSConstants.QUESTION_INST_ATTRIBUTE_NAME);
        Object buttonClicked = request.getParameter(KNSConstants.QUESTION_CLICKED_BUTTON);
        String methodToCall = ((KualiForm) form).getMethodToCall();
        
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        int status = isValidSubmission(pdDoc);

       //if((map.size()==1) &&  map.containsKey("sponsorProgramInformationAuditWarnings"))
        if (status == WARNING) 
        {

            if(status == WARNING && question == null){
                return this.performQuestionWithoutInput(mapping, form, request, response, DOCUMENT_ROUTE_QUESTION, "Validation Warning Exists.Are you sure want to submit to workflow routing.", KNSConstants.CONFIRMATION_QUESTION, methodToCall, "");
                
            } 
            else if(DOCUMENT_ROUTE_QUESTION.equals(question) && ConfirmationQuestion.YES.equals(buttonClicked)) {
                ActionForward actionForward = super.route(mapping, form, request, response);
                return actionForward;
            }

            else{
                return mapping.findForward((Constants.MAPPING_BASIC));
            }    

        }
        if(status == OK){
            ActionForward actionForward = super.route(mapping, form, request, response);
            return actionForward;
        }else   {
        GlobalVariables.getErrorMap().clear(); // clear error from isValidSubmission()    
        GlobalVariables.getErrorMap().putError("datavalidation",KeyConstants.ERROR_WORKFLOW_SUBMISSION,  new String[] {});
        
        if ((pdDoc.getDevelopmentProposal().getContinuedFrom() != null) && isNewProposalType(pdDoc) && isSubmissionApplication(pdDoc)) {
        	GlobalVariables.getErrorMap().putError("someKey", KeyConstants.ERROR_RESUBMISSION_INVALID_PROPOSALTYPE_SUBMISSIONTYPE);
        }
        
        return mapping.findForward((Constants.MAPPING_BASIC));
         }
        
}
    
    /**
     * Submit a proposal to a sponsor.  
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward submitToSponsor(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response)throws Exception{
      
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)proposalDevelopmentForm.getDocument();

        proposalDevelopmentForm.setAuditActivated(true);
        
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        int status = isValidSubmission(proposalDevelopmentDocument);
        if (status == OK) {
            forward = submitApplication(mapping, form, request, response);
        } 
        else if (status == WARNING) {
            StrutsConfirmation question = buildSubmitToGrantsGovWithWarningsQuestion(mapping, form, request, response);
            forward = confirm(question, CONFIRM_SUBMISSION_WITH_WARNINGS_KEY, EMPTY_STRING);
        }
       
        return forward;      
    }
    
    /**
     * Is this a valid submission?  
     * @param proposalDevelopmentDocument
     * @return OK, WARNING or ERROR
     */
    private int isValidSubmission(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        int state = OK;
        
        /*
         * If this proposal is a continuation from another proposal, it is illegal for
         * it to have a New Proposal Type and Application Submission Type.
         */
        if ((proposalDevelopmentDocument.getDevelopmentProposal().getContinuedFrom() != null) && isNewProposalType(proposalDevelopmentDocument) && isSubmissionApplication(proposalDevelopmentDocument)) {
            state = ERROR;
            //GlobalVariables.getErrorMap().putError("someKey", KeyConstants.ERROR_RESUBMISSION_INVALID_PROPOSALTYPE_SUBMISSIONTYPE);
        }
        else {
            /* 
             * Don't know what to do about the Audit Rules.  The parent class invokes the Audit rules
             * from its execute() method.  It will stay this way for the time being because I this is
             * what the code did before it was refactored.
             */
            boolean auditPassed = new AuditActionHelper().auditUnconditionally(proposalDevelopmentDocument);
            
            /*
             * Check for S2S validation if we have a Grants.gov Opportunity.  If there is no Grants.gov
             * Opportunity, then the user wants to submit the proposal manually (printing and sending via
             * snail mail).
             */
            boolean s2sPassed = true;
            if (proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity() != null) {
                S2SService s2sService = (S2SService) KraServiceLocator.getService(S2SService.class);
//                s2sPassed = s2sService.validateApplication(proposalDevelopmentDocument.getProposalNumber());
                try {
                    s2sPassed = s2sService.validateApplication(proposalDevelopmentDocument);
                }
                catch (S2SException e) {
                    log.error(e.getMessage(),e);
                    s2sPassed=false;
                }
            }
            
            /*
             * If either reported an error, then we have to check to see if we only have warnings or
             * if we also have some errors.
             */
            if (!auditPassed || !s2sPassed) {
                state = WARNING;
                for (Iterator iter = GlobalVariables.getAuditErrorMap().keySet().iterator(); iter.hasNext();) {
                    AuditCluster auditCluster = (AuditCluster)GlobalVariables.getAuditErrorMap().get(iter.next());
                    if (!StringUtils.equalsIgnoreCase(auditCluster.getCategory(), Constants.AUDIT_WARNINGS)) {
                        state = ERROR;
                        GlobalVariables.getErrorMap().putError("noKey", KeyConstants.VALIDATTION_ERRORS_BEFORE_GRANTS_GOV_SUBMISSION);
                        break;
                    }
                }
            }
        }
            
        return state;
    }
        
    /**
     * Is the proposal classified as NEW according to its proposal type?
     * @param doc the proposal development document
     * @return true if new; otherwise false
     */
    private boolean isNewProposalType(ProposalDevelopmentDocument doc) {
        String newProposalTypeValue = getProposalParameterValue(KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_NEW);
        return StringUtils.equalsIgnoreCase(doc.getDevelopmentProposal().getProposalTypeCode(), newProposalTypeValue);
    }
                
    /**
     * Does the proposal have a Grants.gov Opportunity with a S2S submission type of APPLICATION?
     * @param doc the proposal development document
     * @return true if proposal has grants.gov with submission type of APPLICATION; otherwise false
     */
    private boolean isSubmissionApplication(ProposalDevelopmentDocument doc) {
        if (doc.getDevelopmentProposal().getS2sOpportunity() != null) {
            String applicationSubmissionValue = getProposalParameterValue(KeyConstants.S2S_SUBMISSIONTYPE_APPLICATION);
            return StringUtils.equalsIgnoreCase(doc.getDevelopmentProposal().getS2sOpportunity().getS2sSubmissionTypeCode(), applicationSubmissionValue);
        }
        return false;
    }
    
    /**
     * Get a Proposal parameter value from the Kuali System Parameters.
     * @param parameterName the name of the parameter
     * @return the parameter's value
     */
    private String getProposalParameterValue(String parameterName) {
        KualiConfigurationService configurationService = KraServiceLocator.getService(KualiConfigurationService.class);
        return configurationService.getParameter(Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, 
                                                 Constants.PARAMETER_COMPONENT_DOCUMENT, 
                                                 parameterName).getParameterValue();
    }
    
    /**
     * Submit the proposal to the sponsor without any further error checking.  Can be invoked if the user
     * wishes to ignore an warnings that occurred when first trying to submit the proposal.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward submitApplication(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response)throws Exception{
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)proposalDevelopmentForm.getDocument();
        
        /*
         * If there is an opportunity, then this is a Grants.gov submission.  
         */
        if (proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity() != null) {
            submitS2sApplication(proposalDevelopmentDocument);
            createSubmissionHistory(proposalDevelopmentDocument);
        }
       
        proposalDevelopmentDocument.getDevelopmentProposal().setSubmitFlag(true);
        
        ProposalStateService proposalStateService = KraServiceLocator.getService(ProposalStateService.class);
        proposalDevelopmentDocument.getDevelopmentProposal().setProposalStateTypeCode(proposalStateService.getProposalStateTypeCode(proposalDevelopmentDocument, false));
        
        DocumentService documentService = KNSServiceLocator.getDocumentService();
        documentService.saveDocument(proposalDevelopmentDocument);
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Submit a proposal to Grants.gov.
     * @param proposalDevelopmentDocument
     * @throws Exception
     */
    private void submitS2sApplication(ProposalDevelopmentDocument proposalDevelopmentDocument) throws Exception{
        S2SService s2sService = ((S2SService) KraServiceLocator.getService(S2SService.class));
//        s2sService.submitApplication(proposalDevelopmentDocument.getS2sOpportunity().getProposalNumber());
      s2sService.submitApplication(proposalDevelopmentDocument);
    }
        
    /**
     * Create the submission history for a proposal.
     * @param proposalDevelopmentDocument
     * @throws Exception
     */
    private void createSubmissionHistory(ProposalDevelopmentDocument proposalDevelopmentDocument)throws Exception{
        proposalDevelopmentDocument.getDevelopmentProposal().refreshReferenceObject("s2sAppSubmission");
        GlobalVariables.getMessageList().add(Constants.GRANTS_GOV_SUBMISSION_SUCCESSFUL_MESSAGE);
        S2sSubmissionHistory s2sSubmissionHistory = createSubmissionHistory(proposalDevelopmentDocument.getDevelopmentProposal().getProposalNumber(), proposalDevelopmentDocument);
        proposalDevelopmentDocument.getDevelopmentProposal().getS2sSubmissionHistory().add(s2sSubmissionHistory);
               
        BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        
        Map queryPDD = new HashMap();
        String continuedFrom = proposalDevelopmentDocument.getDevelopmentProposal().getContinuedFrom(); 
        while (continuedFrom!=null) {
            queryPDD.put("proposalNumber", continuedFrom);
            ProposalDevelopmentDocument pd = (ProposalDevelopmentDocument)businessObjectService.findByPrimaryKey(ProposalDevelopmentDocument.class, queryPDD);
            
            S2sSubmissionHistory newS2sSubmissionHistory = createSubmissionHistory(pd.getDevelopmentProposal().getProposalNumber(), proposalDevelopmentDocument);
            pd.getDevelopmentProposal().getS2sSubmissionHistory().add(newS2sSubmissionHistory);
            businessObjectService.save(pd.getDevelopmentProposal().getS2sSubmissionHistory());
            continuedFrom = pd.getDevelopmentProposal().getContinuedFrom();
        }
        businessObjectService.save(proposalDevelopmentDocument.getDevelopmentProposal().getS2sSubmissionHistory());
    }
    
    /**
     * Create a single Submission History entry.
     * @param proposalNumber
     * @param proposalDevelopmentDocument
     * @return
     */
    private S2sSubmissionHistory createSubmissionHistory(String proposalNumber, ProposalDevelopmentDocument proposalDevelopmentDocument) {
        S2sSubmissionHistory s2sSubmissionHistory = new S2sSubmissionHistory();
        s2sSubmissionHistory.setProposalNumber(proposalNumber);
        s2sSubmissionHistory.setProposalNumberOrig(proposalDevelopmentDocument.getDevelopmentProposal().getProposalNumber());
        s2sSubmissionHistory.setOriginalProposalId(proposalDevelopmentDocument.getDevelopmentProposal().getContinuedFrom());
        List<S2sAppSubmission> submissions = proposalDevelopmentDocument.getDevelopmentProposal().getS2sAppSubmission();
        s2sSubmissionHistory.setFederalIdentifier(submissions.get(submissions.size()-1).getGgTrackingId());
        if (proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity() != null) {
            s2sSubmissionHistory.setS2sRevisionTypeCode(proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getRevisionCode());
            s2sSubmissionHistory.setS2sSubmissionTypeCode(proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getS2sSubmissionTypeCode());
        }            
        s2sSubmissionHistory.setSubmittedBy(((UniversalUser) GlobalVariables.getUserSession().getPerson()).getPersonUniversalIdentifier());
        s2sSubmissionHistory.setSubmissionTime(submissions.get(submissions.size()-1).getReceivedDate());
        return s2sSubmissionHistory;
    }
    
    /**
     * 
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    private StrutsConfirmation buildSubmitToGrantsGovWithWarningsQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_SUBMISSION_WITH_WARNINGS_KEY, KeyConstants.QUESTION_SUMBMIT_OPPORTUNITY_WITH_WARNINGS_CONFIRMATION);
    }

    

    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = super.reload(mapping, form, request, response);
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getDocument();
        PrintService printService = KraServiceLocator.getService(PrintService.class);
        printService.populateSponsorForms(proposalDevelopmentForm.getSponsorFormTemplates(), proposalDevelopmentDocument.getDevelopmentProposal().getSponsorCode());
        return forward;
    }
    
    /**
     * 
     * This method is called to print forms
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward printSponsorForms(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)proposalDevelopmentForm.getDocument();
        ActionForward actionForward = mapping.findForward(MAPPING_BASIC);
        String proposalNumber = proposalDevelopmentDocument.getDevelopmentProposal().getProposalNumber();
        List<SponsorFormTemplate> printFormTemplates = new ArrayList<SponsorFormTemplate>();  
        
        List<SponsorFormTemplateList> sponsorFormTemplateLists = proposalDevelopmentForm.getSponsorFormTemplates();
        PrintService printService = KraServiceLocator.getService(PrintService.class);
        printFormTemplates = printService.getSponsorFormTemplates(sponsorFormTemplateLists); 
        
        if(!printFormTemplates.isEmpty()) {
            String contentType = Constants.PDF_REPORT_CONTENT_TYPE;
            String ReportName = proposalNumber.concat("_" + proposalDevelopmentDocument.getDevelopmentProposal().getSponsorCode()).concat(Constants.PDF_FILE_EXTENSION);
            streamToResponse(printFormTemplates, proposalNumber, contentType, ReportName, response);
            actionForward = null;
        }
        return actionForward;
    }

    public void streamToResponse(List<SponsorFormTemplate> printFormTemplates, String proposalNumber, String contentType, String ReportName, HttpServletResponse response) throws Exception{
        
        
        byte[] sftByteStream = KraServiceLocator.getService(PrintService.class).printProposalSponsorForms(proposalNumber, printFormTemplates);
        
        if(sftByteStream == null) {
            return;
        }
        ByteArrayOutputStream baos = null;
        try{
            baos = new ByteArrayOutputStream(sftByteStream.length);
            baos.write(sftByteStream);
            
            WebUtils.saveMimeOutputStreamAsFile(response, contentType, baos, ReportName);
            
        }finally{
            try{
                if(baos!=null){
                    baos.close();
                    baos = null;
                }
            }catch(IOException ioEx){
                LOG.warn(ioEx.getMessage(), ioEx);
            }
        }
}

    
    /**
     * 
     * This method is called to print forms
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward printForms(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)proposalDevelopmentForm.getDocument();
        super.save(mapping, form, request, response);
        boolean grantsGovErrorExists = false;
        boolean errorExists = false;
        boolean warningExists = false;
        AttachmentDataSource attachmentDataSource = KraServiceLocator.getService(S2SService.class).printForm(proposalDevelopmentDocument);
        if(attachmentDataSource==null || attachmentDataSource.getContent()==null){
            for (Iterator iter = GlobalVariables.getAuditErrorMap().keySet().iterator(); iter.hasNext();){     
                AuditCluster auditCluster = (AuditCluster)GlobalVariables.getAuditErrorMap().get(iter.next());
                if(StringUtils.equalsIgnoreCase(auditCluster.getCategory(),Constants.AUDIT_ERRORS)){
                    errorExists=true;
                    break;
                }
                if(StringUtils.equalsIgnoreCase(auditCluster.getCategory(),Constants.GRANTSGOV_ERRORS)){
                    grantsGovErrorExists = true;
                    break;
                }
                if(StringUtils.equalsIgnoreCase(auditCluster.getCategory(),Constants.AUDIT_WARNINGS)){
                    warningExists = true;
                }
            }
            if(grantsGovErrorExists){
                GlobalVariables.getErrorMap().putError("document.noKey", KeyConstants.VALIDATTION_ERRORS_BEFORE_GRANTS_GOV_SUBMISSION);
                proposalDevelopmentForm.setAuditActivated(true);
                return mapping.findForward(Constants.MAPPING_PROPOSAL_ACTIONS);
            }
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
        ByteArrayOutputStream baos = null;
        try{
            baos = new ByteArrayOutputStream(attachmentDataSource.getContent().length);
            baos.write(attachmentDataSource.getContent());
            WebUtils.saveMimeOutputStreamAsFile(response, attachmentDataSource.getContentType(), baos, attachmentDataSource.getFileName());
        }finally{
            try{
                if(baos!=null){
                    baos.close();
                    baos = null;
                }
            }catch(IOException ioEx){
                LOG.warn(ioEx.getMessage(), ioEx);
            }
        }        
        return null;
    }
    
    @Override
    protected KualiRuleService getKualiRuleService() {
        return getService(KualiRuleService.class);
    }
    
    
    /**
     * 
     * This method is for audit rule to forward to the page that the audit error fix is clicked.
     * This is an example for budget audit error forward from proposal to budget page.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward personnel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = getForwardToBudgetUrl(form);
        // TODO : what if forward is null
        forward = StringUtils.replace(forward, "budgetParameters.do?", "budgetPersonnel.do?auditActivated=true&");
        
        return new ActionForward(forward, true);
    }
    
    /**
     * 
     * This method is for audit rule to forward to the Budget Parameters page when the associated audit error fix is clicked.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward budgetDistributionAndIncome(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = getForwardToBudgetUrl(form);
        forward = StringUtils.replace(forward, "budgetParameters.do?", "budgetDistributionAndIncome.do?auditActivated=true&");
        
        return new ActionForward(forward, true);
    }

    /**
     * 
     * This method is for audit rule to forward to the Budget Parameters page when the associated audit error fix is clicked.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward parameters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = getForwardToBudgetUrl(form);
        forward = StringUtils.replace(forward, "budgetParameters.do?", "budgetParameters.do?auditActivated=true&");
        
        return new ActionForward(forward, true);
    }
    
    /**
     * 
     * This method gets called upon clicking of resubmit(replace sponsor) button on Proposal Actions page.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward resubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("resubmit");
    }

    /**
     * 
     * This method is for budget expense rule when 'fix' is clicked.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward budgetExpenses(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = getForwardToBudgetUrl(form);
        String methodToCallAttribute = (String)request.getAttribute("methodToCallAttribute");
        String activePanelName = (String)request.getAttribute("activePanelName");
        
        String viewBudgetPeriodParam = null;
        if (StringUtils.isNotBlank(methodToCallAttribute)) {
            int idx = methodToCallAttribute.indexOf("&viewBudgetPeriod=");
            if (idx > 0) {
                viewBudgetPeriodParam = "viewBudgetPeriod=" + methodToCallAttribute.substring(methodToCallAttribute.indexOf("=", idx)+1,methodToCallAttribute.indexOf(".", idx))+"&";
            }
        }
        
        forward = StringUtils.replace(forward, "budgetParameters.do?", "budgetExpenses.do?auditActivated=true&");
        if (viewBudgetPeriodParam != null) {
            forward = StringUtils.replace(forward, "budgetExpenses.do?", "budgetExpenses.do?"+viewBudgetPeriodParam); 
        }
        
        if (StringUtils.isNotBlank(activePanelName)) {
            forward = StringUtils.replace(forward, "budgetExpenses.do?", "budgetExpenses.do?activePanelName=" + activePanelName + "&"); 
        }

        return new ActionForward(forward, true);
    }
    
    /**
     * 
     * This method is to forward to budget summary page for audit errors.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward summary(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = getForwardToBudgetUrl(form);
        forward = StringUtils.replace(forward, "budgetParameters.do?", "budgetParameters.do?auditActivated=true&");
        
        return new ActionForward(forward, true);
    }
    
    public ActionForward budgetRate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = getForwardToBudgetUrl(form);
        forward = StringUtils.replace(forward, "budgetParameters.do?", "budgetRates.do?auditActivated=true&anchor="+((KualiForm)form).getAnchor()+"&");
        return new ActionForward(forward, true);
    }

    public ActionForward saveProposalActions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return save(mapping, form, request, response);
    }

    /**
     * 
     * This is a helper method to set up the forward to budget url for budget audit error.
     * @param form
     * @return
     */
    private String getForwardToBudgetUrl(ActionForm form) {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument pdDoc = pdForm.getDocument();
        BudgetDocument budgetDocument = null;
        String forward = null;
        try {
            for (BudgetDocumentVersion budgetDocumentVersion: pdDoc.getBudgetDocumentVersions()) {
                BudgetVersionOverview budgetVersion = budgetDocumentVersion.getBudgetVersionOverview();
                if (budgetVersion.isFinalVersionFlag()) {
                    DocumentService documentService = KraServiceLocator.getService(DocumentService.class);
                    budgetDocument = (BudgetDocument) documentService.getByDocumentHeaderId(budgetVersion.getDocumentNumber());
                }
            }
            Long routeHeaderId = budgetDocument.getDocumentHeader().getWorkflowDocument().getRouteHeaderId();
            forward = buildForwardUrl(routeHeaderId);
        } catch (Exception e) {
            LOG.info("forward to budgetsummary "+e.getStackTrace());
            //TODO what is the forward here
        }
        return forward;

    }

}
    
    
    
    
    
