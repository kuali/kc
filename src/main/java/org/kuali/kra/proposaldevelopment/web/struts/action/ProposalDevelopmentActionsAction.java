/*
 * Copyright 2005-2010 The Kuali Foundation
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
import java.util.Collection;
import java.util.Collections;
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
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.bo.SpecialReviewType;
import org.kuali.kra.bo.SponsorFormTemplate;
import org.kuali.kra.bo.SponsorFormTemplateList;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.budget.versions.BudgetVersionOverview;
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.common.specialreview.rule.event.SaveSpecialReviewLinkEvent;
import org.kuali.kra.common.specialreview.service.SpecialReviewService;
import org.kuali.kra.common.web.struts.form.ReportHelperBean;
import org.kuali.kra.common.web.struts.form.ReportHelperBeanContainer;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.institutionalproposal.InstitutionalProposalConstants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.proposaladmindetails.ProposalAdminDetails;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalService;
import org.kuali.kra.institutionalproposal.specialreview.InstitutionalProposalSpecialReview;
import org.kuali.kra.kim.service.KcGroupService;
import org.kuali.kra.printing.service.CurrentAndPendingReportService;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalChangedData;
import org.kuali.kra.proposaldevelopment.bo.ProposalCopyCriteria;
import org.kuali.kra.proposaldevelopment.bo.ProposalOverview;
import org.kuali.kra.proposaldevelopment.bo.ProposalState;
import org.kuali.kra.proposaldevelopment.bo.ProposalType;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.hierarchy.ProposalHierarchyKeyConstants;
import org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService;
import org.kuali.kra.proposaldevelopment.notification.ProposalDevelopmentNotificationContext;
import org.kuali.kra.proposaldevelopment.notification.ProposalDevelopmentNotificationRenderer;
import org.kuali.kra.proposaldevelopment.printing.service.ProposalDevelopmentPrintingService;
import org.kuali.kra.proposaldevelopment.rule.event.CopyProposalEvent;
import org.kuali.kra.proposaldevelopment.rule.event.ProposalDataOverrideEvent;
import org.kuali.kra.proposaldevelopment.rules.ProposalDevelopmentRejectionRule;
import org.kuali.kra.proposaldevelopment.service.ProposalCopyService;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService;
import org.kuali.kra.proposaldevelopment.service.ProposalStateService;
import org.kuali.kra.proposaldevelopment.specialreview.ProposalSpecialReview;
import org.kuali.kra.proposaldevelopment.web.bean.ProposalDevelopmentRejectionBean;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.bo.S2sAppSubmission;
import org.kuali.kra.s2s.bo.S2sSubmissionType;
import org.kuali.kra.s2s.service.PrintService;
import org.kuali.kra.s2s.service.S2SService;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.service.KraPersistenceStructureService;
import org.kuali.kra.web.struts.action.AuditActionHelper;
import org.kuali.kra.web.struts.action.StrutsConfirmation;
import org.kuali.rice.core.api.resourceloader.GlobalResourceLoader;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.action.ActionRequest;
import org.kuali.rice.kew.api.action.RoutingReportCriteria;
import org.kuali.rice.kew.api.action.WorkflowDocumentActionsService;
import org.kuali.rice.kew.api.document.DocumentDetail;
import org.kuali.rice.kim.api.permission.PermissionService;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.util.WebUtils;
import org.kuali.rice.kns.web.struts.action.AuditModeAction;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KRADServiceLocator;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.service.PersistenceStructureService;
import org.kuali.rice.krad.service.PessimisticLockService;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.ObjectUtils;

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
    private static final String DOCUMENT_DELETE_QUESTION="ProposalDocDelete";
    
    private static final String CONFIRM_SUBMISSION_WITH_WARNINGS_KEY = "submitApplication";
    private static final String EMPTY_STRING = "";
    
    private static final int OK = 0;
    private static final int WARNING = 1;
    private static final int ERROR = 2;
    
    /**
     * Struts mapping for the Proposal web page.  
     */
    private static final String MAPPING_PROPOSAL = "proposal";
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);
        if (!StringUtils.equals((String) request.getAttribute("methodToCallAttribute"), "methodToCall.reload"))
            this.populateSponsorForms(form);
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
        WorkflowDocument workflowDoc = kualiDocumentFormBase.getDocument().getDocumentHeader().getWorkflowDocument();

        ActionForward forward;
        if (canGenerateRequestsInFuture(workflowDoc)) {
            forward = promptUserForInput(workflowDoc, "approve", mapping, form, request, response);
        } else {
            forward = super.approve(mapping, form, request, response);
        }
        
        ProposalDevelopmentDocument proposalDevelopmentDocument = ((ProposalDevelopmentForm) form).getDocument();
        if (autogenerateInstitutionalProposal() && proposalDevelopmentDocument.getInstitutionalProposalNumber() != null) {
            if (ProposalType.REVISION_TYPE_CODE.equals(proposalDevelopmentDocument.getDevelopmentProposal().getProposalTypeCode())) {
                KNSGlobalVariables.getMessageList().add(KeyConstants.MESSAGE_INSTITUTIONAL_PROPOSAL_VERSIONED, 
                        proposalDevelopmentDocument.getInstitutionalProposalNumber());
            } else {
                String proposalNumber = proposalDevelopmentDocument.getInstitutionalProposalNumber();
                KNSGlobalVariables.getMessageList().add(KeyConstants.MESSAGE_INSTITUTIONAL_PROPOSAL_CREATED, proposalNumber);
            }
        }
        
        String routeHeaderId = ((ProposalDevelopmentForm) form).getDocument().getDocumentNumber();
        String returnLocation = buildActionUrl(routeHeaderId, Constants.MAPPING_PROPOSAL_ACTIONS, "ProposalDevelopmentDocument");
        
        ActionForward basicForward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
        ActionForward holdingPageForward = mapping.findForward(Constants.MAPPING_HOLDING_PAGE);
        return routeToHoldingPage(basicForward, forward, holdingPageForward, returnLocation);
    }

    private ActionForward promptUserForInput(WorkflowDocument workflowDoc, String action, ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        Object buttonClicked = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
        String methodToCall = ((KualiForm) form).getMethodToCall();
        
        if ( !DOCUMENT_APPROVE_QUESTION.equals(question)) {
            // ask question if not already asked
              return this.performQuestionWithoutInput(mapping, form, request, response, DOCUMENT_APPROVE_QUESTION, KRADServiceLocator
                      .getKualiConfigurationService().getPropertyValueAsString(KeyConstants.QUESTION_APPROVE_FUTURE_REQUESTS),
                       KRADConstants.CONFIRMATION_QUESTION, methodToCall, "");             
        }
        else if(DOCUMENT_APPROVE_QUESTION.equals(question) && ConfirmationQuestion.NO.equals(buttonClicked)) {
            workflowDoc.setDoNotReceiveFutureRequests();
        }
        else if(DOCUMENT_APPROVE_QUESTION.equals(question)){ 
            workflowDoc.setReceiveFutureRequests();  
        }
        
        if( StringUtils.equals(action, "approve"))
            return super.approve(mapping, form, request, response);
        else if ( StringUtils.equals(action, "route"))
            return super.route(mapping, form, request, response);
        else 
            throw new UnsupportedOperationException( String.format( "promptUserForInput does not know how to forward for action %s.", action )); 
    }   

    private boolean canGenerateRequestsInFuture(WorkflowDocument workflowDoc) throws Exception {
        String loggedInPrincipalId = GlobalVariables.getUserSession().getPrincipalId();
        RoutingReportCriteria.Builder reportCriteriaBuilder = RoutingReportCriteria.Builder.createByDocumentId(workflowDoc.getDocumentId());
        reportCriteriaBuilder.setTargetPrincipalIds(Collections.singletonList(loggedInPrincipalId));

        boolean receiveFutureRequests = false;
        boolean doNotReceiveFutureRequests = false;    

        Map<String, String> variables = workflowDoc.getVariables();
        org.kuali.rice.krad.workflow.service.WorkflowDocumentService workflowDocumentService = KRADServiceLocatorWeb.getService("workflowDocumentService");
        String currentRouteNodeNames = workflowDocumentService.getCurrentRouteNodeNames(workflowDoc);
        if (variables != null && CollectionUtils.isNotEmpty(variables.keySet())) {
            Iterator<String> variableIterator = variables.keySet().iterator();
            while(variableIterator.hasNext()) {
                    String variableKey = variableIterator.next();
                    String variableValue = variables.get(variableKey);
                    if (variableKey.startsWith(KewApiConstants.RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_KEY)
                            && variableValue.toUpperCase().equals(KewApiConstants.RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_VALUE)
                            && variableKey.contains(loggedInPrincipalId)) {
                        receiveFutureRequests = true; 
                        break;
                    }
                    else if (variableKey.startsWith(KewApiConstants.RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_KEY)
                          && variableValue.toUpperCase().equals(KewApiConstants.DONT_RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_VALUE)
                          && variableKey.contains(loggedInPrincipalId)) {
                        doNotReceiveFutureRequests = true; 
                        break;
                    }
            }
        } 

        return ((receiveFutureRequests == false && doNotReceiveFutureRequests == false) && canGenerateMultipleApprovalRequests(reportCriteriaBuilder.build(), loggedInPrincipalId, currentRouteNodeNames ));
    }
    
    private boolean canGenerateMultipleApprovalRequests(RoutingReportCriteria reportCriteria, String loggedInPrincipalId, String currentRouteNodeNames ) throws Exception {
        int approvalRequestsCount = 0;
        WorkflowDocumentActionsService info = GlobalResourceLoader.getService("rice.kew.workflowDocumentActionsService");
        
        DocumentDetail results1 = info.executeSimulation(reportCriteria);
        for(ActionRequest actionRequest : results1.getActionRequests() ){
            //!actionRequest.isRoleRequest() &&  removed from condition for 
            if(actionRequest.isPending() && actionRequest.getActionRequested().getCode().equalsIgnoreCase(KewApiConstants.ACTION_REQUEST_APPROVE_REQ) && 
                    recipientMatchesUser(actionRequest, loggedInPrincipalId) && !StringUtils.contains( currentRouteNodeNames,actionRequest.getNodeName()) ) {
                approvalRequestsCount+=1; 
            }
        }
          
        return (approvalRequestsCount > 0);
    }
    
    private boolean recipientMatchesUser(ActionRequest actionRequest, String loggedInPrincipalId) {
        KcGroupService groupService = KraServiceLocator.getService(KcGroupService.class);
        if(actionRequest != null && loggedInPrincipalId != null ) {
            List<ActionRequest> actionRequests =  Collections.singletonList(actionRequest);
            if(actionRequest.isRoleRequest()) {
                actionRequests = actionRequest.getChildRequests();
            }
            for( ActionRequest cActionRequest : actionRequests ) {
                String recipientUser = cActionRequest.getPrincipalId();
                if( ( recipientUser != null && recipientUser.equals(loggedInPrincipalId) ) 
                        || (StringUtils.isNotBlank(cActionRequest.getGroupId()) && groupService.isMemberOfGroup(loggedInPrincipalId, cActionRequest.getGroupId() ))) {
                    return true;
                }
            }
        }
        
        return false;  
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

        ActionForward forward = mapping.findForward("basic");
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
            ObjectUtils.setObjectProperty(pdDocument.getDevelopmentProposal(), proposalAttributeToPersist, newProposalChangedData.getChangedValue());
            
            boService.save(proposalWrapper);
            pdForm.getDocument().setVersionNumber(proposalWrapper.getVersionNumber());
            
            pdForm.setNewProposalChangedData(new ProposalChangedData());
            growProposalChangedHistory(pdDocument, newProposalChangedData);
            
            ProposalDevelopmentNotificationContext context = 
                new ProposalDevelopmentNotificationContext(pdDocument.getDevelopmentProposal(), "103", "Proposal Data Override");
            ((ProposalDevelopmentNotificationRenderer) context.getRenderer()).setProposalChangedData(newProposalChangedData);
            if (pdForm.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
                pdForm.getNotificationHelper().initializeDefaultValues(context);
                forward = mapping.findForward("notificationEditor");
            } else {
                getNotificationService().sendNotification(context);                
            }                

        }
        
        return forward;
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
        for(String field: fieldsToUpdate) {
            boolean noSuchFieldPD = false;
            boolean noSuchFieldBO = false;
            Object tempVal = null;
            
            try {
                tempVal = ObjectUtils.getPropertyValue(pdDocument, field);
            } catch ( Exception e ) {
                noSuchFieldPD = true;
            }
            
            try {
                tempVal = ObjectUtils.getPropertyValue(pdDocument.getDevelopmentProposal(), field);
            } catch ( Exception e ) {
                noSuchFieldBO = true;
            }

            if( tempVal == null && noSuchFieldPD && noSuchFieldBO ) {
                LOG.warn("Could not find property " + field + " in ProposalDevelopmentDocument or DevelopmnentProposal bo.");
            }
            
            ObjectUtils.setObjectProperty(proposalWrapper, field, (tempVal != null) ? tempVal : null);
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
            KraAuthorizationService kraAuthService = KraServiceLocator.getService(KraAuthorizationService.class);
            
            if (proposalCopyService == null) {
                
                // Something bad happened. The errors are in the Global Error Map
                // which will be displayed to the user.
                
                nextWebPage = mapping.findForward(Constants.MAPPING_BASIC);
            }
            else {
                String newDocId = proposalCopyService.copyProposal(doc, criteria);
                KraServiceLocator.getService(PessimisticLockService.class).releaseAllLocksForUser(doc.getPessimisticLocks(), GlobalVariables.getUserSession().getPerson());
                
                // Switch over to the new proposal development document and
                // go to the Proposal web page.
                
                proposalDevelopmentForm.setDocId(newDocId);
                this.loadDocument(proposalDevelopmentForm);  
                
                ProposalDevelopmentDocument copiedDocument = proposalDevelopmentForm.getDocument();
                initializeProposalUsers(copiedDocument);//add in any default permissions
                copiedDocument.getDevelopmentProposal().setS2sAppSubmission(new ArrayList<S2sAppSubmission>());            
                 
                DocumentService docService = KraServiceLocator.getService(DocumentService.class);
                docService.saveDocument(copiedDocument);
                kraAuthService.forceFlushRoleCaches();
                
                nextWebPage = mapping.findForward(MAPPING_PROPOSAL);
                
                // Helper method to clear document form data.
                proposalDevelopmentForm.clearDocumentRelatedState();
                proposalDevelopmentForm.setViewOnly(false);
            
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
    
    public ActionForward reject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //System.err.println("******************* Got to the reject action **************************");
        KualiDocumentFormBase kualiDocumentFormBase = (KualiDocumentFormBase) form;

        //Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        //Object buttonClicked = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
        //String reason = request.getParameter(KRADConstants.QUESTION_REASON_ATTRIBUTE_NAME);
       // String methodToCall = ((KualiForm) form).getMethodToCall();
        //if (question == null) {
          //  System.err.println("               question null");
            ((ProposalDevelopmentForm)form).setShowRejectionConfirmation(true);
            return mapping.findForward(Constants.MAPPING_BASIC);
            //return this.performQuestionWithInput(mapping, form, request, response, DOCUMENT_REJECT_QUESTION,
              //      "Are you sure you want to reject this document?", KRADConstants.CONFIRMATION_QUESTION, methodToCall, "");
        //}
        //else if ((DOCUMENT_REJECT_QUESTION.equals(question)) && ConfirmationQuestion.NO.equals(buttonClicked)) {
          //  System.err.println("               question answered NO");
            //return mapping.findForward(Constants.MAPPING_BASIC);
        //}
        //else {
          //  System.err.println("               question answered YES");
            // reject the document using the service.
            //ProposalDevelopmentDocument pDoc = (ProposalDevelopmentDocument) kualiDocumentFormBase.getDocument();
            
            //ProposalDevelopmentRejectionBean bean = ((ProposalDevelopmentForm)form).getProposalDevelopmentRejectionBean();
            
            //ProposalHierarchyService phService = KraServiceLocator.getService(ProposalHierarchyService.class);
            //phService.rejectProposalDevelopmentDocument(pDoc.getDevelopmentProposal().getProposalNumber(), bean.getRejectReason(), GlobalVariables
              //      .getUserSession().getPrincipalId());
            //return super.returnToSender(request, mapping, kualiDocumentFormBase);
        //}
    }
    
    public ActionForward rejectNo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        ((ProposalDevelopmentForm) form).setShowRejectionConfirmation(false);
        ((ProposalDevelopmentForm) form).setProposalDevelopmentRejectionBean(new ProposalDevelopmentRejectionBean());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward rejectYes(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        KualiDocumentFormBase kualiDocumentFormBase = (KualiDocumentFormBase) form;
        ProposalDevelopmentDocument pDoc = (ProposalDevelopmentDocument) kualiDocumentFormBase.getDocument();
        ProposalDevelopmentRejectionBean bean = ((ProposalDevelopmentForm) form).getProposalDevelopmentRejectionBean();
        
        if (new ProposalDevelopmentRejectionRule().proccessProposalDevelopmentRejection(bean)){
            // reject the document using the service.
            ProposalHierarchyService phService = KraServiceLocator.getService(ProposalHierarchyService.class);
            phService.rejectProposalDevelopmentDocument(pDoc.getDevelopmentProposal().getProposalNumber(), bean.getRejectReason(), GlobalVariables
                    .getUserSession().getPrincipalId(), bean.getRejectFile());
            ((ProposalDevelopmentForm) form).setShowRejectionConfirmation(false);
            return super.returnToSender(request, mapping, kualiDocumentFormBase);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
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
        //HashMap map=KNSGlobalVariables.getAuditErrorMap();
        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        Object buttonClicked = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
        String methodToCall = ((KualiForm) form).getMethodToCall();
        
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        int status = isValidSubmission(pdDoc);
        boolean userSaysOk = false;

       //if((map.size()==1) &&  map.containsKey("sponsorProgramInformationAuditWarnings"))
        if (status == WARNING) {

            if(status == WARNING && question == null){
                forward =  this.performQuestionWithoutInput(mapping, form, request, response, DOCUMENT_ROUTE_QUESTION, "Validation Warning Exists.Are you sure want to submit to workflow routing.", KRADConstants.CONFIRMATION_QUESTION, methodToCall, "");
            } 
            else if(DOCUMENT_ROUTE_QUESTION.equals(question) && ConfirmationQuestion.YES.equals(buttonClicked)) {
                //status is OK now since the user said it was :)
                userSaysOk = true;
            } else if( DOCUMENT_APPROVE_QUESTION.equals(question)) {
                //user said ok in the past since we got to this question.
                userSaysOk = true;
            }

        }
        
        
        if(status == OK || userSaysOk ) {
            WorkflowDocument workflowDoc = proposalDevelopmentForm.getDocument().getDocumentHeader().getWorkflowDocument();
            if( canGenerateRequestsInFuture(workflowDoc) )
                forward = promptUserForInput(workflowDoc, "route", mapping, form, request, response);
            else 
                forward = super.route(mapping, proposalDevelopmentForm, request, response);
        } else if ( status == WARNING ) { 
            
        } else {
            GlobalVariables.getMessageMap().clearErrorMessages(); // clear error from isValidSubmission()    
            GlobalVariables.getMessageMap().putError("datavalidation",KeyConstants.ERROR_WORKFLOW_SUBMISSION,  new String[] {});
        
            if ((pdDoc.getDevelopmentProposal().getContinuedFrom() != null) && isNewProposalType(pdDoc) && isSubmissionApplication(pdDoc)) {
                GlobalVariables.getMessageMap().putError("someKey", KeyConstants.ERROR_RESUBMISSION_INVALID_PROPOSALTYPE_SUBMISSIONTYPE);
            }
        
            forward = mapping.findForward((Constants.MAPPING_BASIC));
        }
        
        String routeHeaderId = proposalDevelopmentForm.getDocument().getDocumentNumber();
        String returnLocation = buildActionUrl(routeHeaderId, Constants.MAPPING_PROPOSAL_ACTIONS, "ProposalDevelopmentDocument");
        
        ActionForward basicForward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
        ActionForward holdingPageForward = mapping.findForward(Constants.MAPPING_HOLDING_PAGE);
        return routeToHoldingPage(basicForward, forward, holdingPageForward, returnLocation);
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
       
        if (!userCanCreateInstitutionalProposal()) {
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
        
        proposalDevelopmentForm.setAuditActivated(true);
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        
        int status = isValidSubmission(proposalDevelopmentDocument);
        
        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        Object buttonClicked = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
        
        if( (question == null || buttonClicked == null ) && status == WARNING && proposalDevelopmentForm.getResubmissionOption()==null) {
                StrutsConfirmation qst = buildSubmitToGrantsGovWithWarningsQuestion(mapping, form, request, response);
                forward = confirm(qst, CONFIRM_SUBMISSION_WITH_WARNINGS_KEY, EMPTY_STRING);
                proposalDevelopmentForm.setResubmissionOption(null);
                proposalDevelopmentForm.setInstitutionalProposalToVersion(null);
        } else if ( ConfirmationQuestion.NO.equals( buttonClicked )) {
            //do nothing
        } else {
            if ( (ConfirmationQuestion.YES.equals(buttonClicked) || status == OK) && requiresResubmissionPrompt(proposalDevelopmentForm)) {
                return mapping.findForward(Constants.MAPPING_RESUBMISSION_PROMPT);
            } 
            forward = submitApplication(mapping, form, request, response);
        }            

        return forward;      
    }
    
    /**
     * Submit a proposal to GrantsGov.  
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward submitToGrantsGov(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response)throws Exception{
      
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)proposalDevelopmentForm.getDocument();
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);        
        
        if(proposalDevelopmentDocument.getDevelopmentProposal().getSubmitFlag()!= true){
            forward = submitToSponsor(mapping, form, request, response);
        }        
    try{
        submitS2sApplication(proposalDevelopmentDocument);
        proposalDevelopmentForm.setShowSubmissionDetails(true);
        forward = mapping.findForward(Constants.GRANTS_GOV_PAGE);        
        
    }catch(S2SException ex){
        LOG.error(ex.getMessage(), ex);
        KNSGlobalVariables.getMessageList().add(new ErrorMessage(KeyConstants.ERROR_ON_GRANTS_GOV_SUBMISSION));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }    
    return forward;
    }   
    
    private boolean requiresResubmissionPrompt(ProposalDevelopmentForm proposalDevelopmentForm) {
        DevelopmentProposal developmentProposal = proposalDevelopmentForm.getDocument().getDevelopmentProposal();
        return (ProposalType.RESUBMISSION_TYPE_CODE.equals(developmentProposal.getProposalTypeCode())
            || ProposalType.CONTINUATION_TYPE_CODE.equals(developmentProposal.getProposalTypeCode())
            || ProposalType.REVISION_TYPE_CODE.equals(developmentProposal.getProposalTypeCode())
            || isSubmissionChangeCorrected(developmentProposal))
            && proposalDevelopmentForm.getResubmissionOption() == null;
    }
    
    private boolean isSubmissionChangeCorrected(DevelopmentProposal developmentProposal) {
        if (developmentProposal.getS2sOpportunity() != null) {
            return S2sSubmissionType.CHANGE_CORRECTED_CODE.equals(developmentProposal.getS2sOpportunity().getS2sSubmissionTypeCode());
        }
        return false;
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
            //GlobalVariables.getMessageMap().putError("someKey", KeyConstants.ERROR_RESUBMISSION_INVALID_PROPOSALTYPE_SUBMISSIONTYPE);
        } else {
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
                for (Iterator iter = KNSGlobalVariables.getAuditErrorMap().keySet().iterator(); iter.hasNext();) {
                    AuditCluster auditCluster = (AuditCluster)KNSGlobalVariables.getAuditErrorMap().get(iter.next());
                    if (!StringUtils.equalsIgnoreCase(auditCluster.getCategory(), Constants.AUDIT_WARNINGS)) {
                        state = ERROR;
                        GlobalVariables.getMessageMap().putError("noKey", KeyConstants.VALIDATTION_ERRORS_BEFORE_GRANTS_GOV_SUBMISSION);
                        break;
                    }
                }
            }
        }
            
        return state;
    }
    
    private PermissionService getPermissionService() {
        return KimApiServiceLocator.getPermissionService();
    }    
    
    /*
     * To submit to sponsor, user must also have permission to create and submit institutional proposal documents.
     */
    private boolean userCanCreateInstitutionalProposal() {
        boolean hasPermission = true;
        Map<String,String> permissionDetails =new HashMap<String,String>();
        permissionDetails.put(PermissionConstants.DOCUMENT_TYPE_ATTRIBUTE_QUALIFIER, InstitutionalProposalConstants.INSTITUTIONAL_PROPOSAL_DOCUMENT_NAME);
        if (!getPermissionService().hasPermission(GlobalVariables.getUserSession().getPrincipalId(), 
                InstitutionalProposalConstants.INSTITUTIONAL_PROPOSAL_NAMESPACE, 
                PermissionConstants.CREATE_INSTITUTIONAL_PROPOSAL, 
                permissionDetails)) {
            hasPermission = false;
            GlobalVariables.getMessageMap().putError("emptyProp", 
                    KeyConstants.ERROR_SUBMIT_TO_SPONSOR_PERMISSONS, 
                    GlobalVariables.getUserSession().getPrincipalName(),
                    PermissionConstants.CREATE_INSTITUTIONAL_PROPOSAL);
        }
        if (!getPermissionService().hasPermission(GlobalVariables.getUserSession().getPrincipalId(), 
                InstitutionalProposalConstants.INSTITUTIONAL_PROPOSAL_NAMESPACE, 
                PermissionConstants.SUBMIT_INSTITUTIONAL_PROPOSAL, 
                permissionDetails)) {
            hasPermission = false;
            GlobalVariables.getMessageMap().putError("emptyProp", 
                    KeyConstants.ERROR_SUBMIT_TO_SPONSOR_PERMISSONS, 
                    GlobalVariables.getUserSession().getPrincipalName(),
                    PermissionConstants.SUBMIT_INSTITUTIONAL_PROPOSAL);
        }
        return hasPermission;
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
        return this.getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, parameterName);
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
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        
        boolean isIPProtocolLinkingEnabled = getParameterService().getParameterValueAsBoolean(
            "KC-PROTOCOL", "Document", "irb.protocol.institute.proposal.linking.enabled");
        List<ProposalSpecialReview> specialReviews = proposalDevelopmentDocument.getDevelopmentProposal().getPropSpecialReviews();
        if (!isIPProtocolLinkingEnabled 
            || applyRules(new SaveSpecialReviewLinkEvent<ProposalSpecialReview>(proposalDevelopmentDocument, specialReviews, new ArrayList<String>()))) {
           
            if (!(autogenerateInstitutionalProposal() && "X".equals(proposalDevelopmentForm.getResubmissionOption()))) {
                proposalDevelopmentDocument.getDevelopmentProposal().setSubmitFlag(true);
    
                ProposalStateService proposalStateService = KraServiceLocator.getService(ProposalStateService.class);
                if (ProposalState.APPROVED.equals(proposalDevelopmentDocument.getDevelopmentProposal().getProposalStateTypeCode())) {
                    proposalDevelopmentDocument.getDevelopmentProposal().setProposalStateTypeCode(ProposalState.APPROVED_AND_SUBMITTED);
                } else {
                    proposalDevelopmentDocument.getDevelopmentProposal().setProposalStateTypeCode(
                            proposalStateService.getProposalStateTypeCode(proposalDevelopmentDocument, false, false));
                }
            } else {
                if (proposalDevelopmentDocument.getDocumentHeader().getWorkflowDocument().isFinal()) {
                    proposalDevelopmentDocument.getDevelopmentProposal().setProposalStateTypeCode(ProposalState.APPROVED);
                } else {
                    proposalDevelopmentDocument.getDevelopmentProposal().setProposalStateTypeCode(ProposalState.APPROVAL_PENDING);                
                }
            }
            String pCode = proposalDevelopmentDocument.getDevelopmentProposal().getProposalStateTypeCode();
            DocumentService documentService = KRADServiceLocatorWeb.getDocumentService();
            documentService.saveDocument(proposalDevelopmentDocument);
            if( !StringUtils.equals(pCode, proposalDevelopmentDocument.getDevelopmentProposal().getProposalStateTypeCode() )) {
                proposalDevelopmentDocument.getDevelopmentProposal().setProposalStateTypeCode(pCode);
                proposalDevelopmentDocument.getDevelopmentProposal().refresh();
                KraServiceLocator.getService(BusinessObjectService.class).save(proposalDevelopmentDocument.getDevelopmentProposal());
            }
    
            if (autogenerateInstitutionalProposal()) {
                generateInstitutionalProposal(proposalDevelopmentForm);
                ProposalDevelopmentNotificationContext context = new ProposalDevelopmentNotificationContext(proposalDevelopmentDocument.getDevelopmentProposal(), "101", "Proposal Submitted");
                if (proposalDevelopmentForm.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
                    proposalDevelopmentForm.getNotificationHelper().initializeDefaultValues(context);
                    forward = mapping.findForward("notificationEditor");
                } else {
                    getNotificationService().sendNotification(context);                
                }                
            }
        }
        
        return forward;
    }
    
    private void generateInstitutionalProposal(ProposalDevelopmentForm proposalDevelopmentForm) {
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getDocument();
        if ("X".equals(proposalDevelopmentForm.getResubmissionOption())) {
            if (proposalDevelopmentDocument.getDocumentHeader().getWorkflowDocument().isFinal()) {
                KNSGlobalVariables.getMessageList().add(KeyConstants.MESSAGE_INSTITUTIONAL_PROPOSAL_NOT_CREATED);
            } else {
                KNSGlobalVariables.getMessageList().add(KeyConstants.MESSAGE_INSTITUTIONAL_PROPOSAL_NOT_CREATED_INROUTE);
            }
            return;
        }
        if ("O".equals(proposalDevelopmentForm.getResubmissionOption())) {
            proposalDevelopmentForm.setInstitutionalProposalToVersion(proposalDevelopmentDocument.getDevelopmentProposal().getContinuedFrom());
        }
        if ("O".equals(proposalDevelopmentForm.getResubmissionOption())
                || "A".equals(proposalDevelopmentForm.getResubmissionOption())) {
            String versionNumber = createInstitutionalProposalVersion(
                    proposalDevelopmentForm.getInstitutionalProposalToVersion(),
                    proposalDevelopmentDocument.getDevelopmentProposal(),
                    proposalDevelopmentDocument.getFinalBudgetForThisProposal());
            KNSGlobalVariables.getMessageList().add(KeyConstants.MESSAGE_INSTITUTIONAL_PROPOSAL_VERSIONED, 
                    versionNumber,
                    proposalDevelopmentForm.getInstitutionalProposalToVersion());
            
            persistProposalAdminDetails(proposalDevelopmentDocument.getDevelopmentProposal().getProposalNumber(), getActiveProposalId(proposalDevelopmentForm.getInstitutionalProposalToVersion()));
            persistSpecialReviewProtocolFundingSourceLink(getActiveProposalId(proposalDevelopmentForm.getInstitutionalProposalToVersion()));
        } else {
            String proposalNumber = createInstitutionalProposal(
                    proposalDevelopmentDocument.getDevelopmentProposal(), proposalDevelopmentDocument.getFinalBudgetForThisProposal());
            KNSGlobalVariables.getMessageList().add(KeyConstants.MESSAGE_INSTITUTIONAL_PROPOSAL_CREATED, proposalNumber);
            persistProposalAdminDetails(proposalDevelopmentDocument.getDevelopmentProposal().getProposalNumber(), getActiveProposalId(proposalNumber));
            persistSpecialReviewProtocolFundingSourceLink(getActiveProposalId(proposalNumber));
        }
    }
    
    private Long getActiveProposalId(String proposalNumber) {
        BusinessObjectService service = KraServiceLocator.getService(BusinessObjectService.class);
        Collection<InstitutionalProposal> ips = service.findMatching(InstitutionalProposal.class, getFieldValues(proposalNumber, "proposalNumber"));
        Long proposalId = ((InstitutionalProposal) ips.toArray()[0]).getProposalId();
        return proposalId;
    }

    /**
     * This method...
     * @param proposalDevelopmentDocument
     * @param instProposalNumber
     * @return
     */
    private Long findInstProposalNumber(String devProposalNumber) {
        Long instProposalId = null;
        BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        Collection<ProposalAdminDetails> proposalAdminDetails = businessObjectService.findMatching(ProposalAdminDetails.class, getFieldValues(devProposalNumber, "devProposalNumber"));
        
        for(Iterator iter = proposalAdminDetails.iterator(); iter.hasNext();){
            ProposalAdminDetails pad = (ProposalAdminDetails) iter.next();
            instProposalId = pad.getInstProposalId();
            break;
        }
        return instProposalId;
    }

    private Map<String, String> getFieldValues(String value, String fieldName) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(fieldName, value);
        return map;
    }

    /**
     * This method...
     * @param instProposalNumber
     * @param instProposalSequenceNumber TODO
     * @param proposalDevelopmentDocument
     */
    private void persistProposalAdminDetails(String devProposalNumber, Long instProposalId) {
        ProposalAdminDetails proposalAdminDetails = new ProposalAdminDetails();
        proposalAdminDetails.setDevProposalNumber(devProposalNumber);
        proposalAdminDetails.setInstProposalId(instProposalId);
        String loggedInUser = GlobalVariables.getUserSession().getPrincipalName();        
        proposalAdminDetails.setSignedBy(loggedInUser);
        BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        businessObjectService.save(proposalAdminDetails);
    }
    
    private void persistSpecialReviewProtocolFundingSourceLink(Long institutionalProposalId) {
        SpecialReviewService specialReviewService = KraServiceLocator.getService(SpecialReviewService.class);
        
        InstitutionalProposal institutionalProposal = getBusinessObjectService().findBySinglePrimaryKey(InstitutionalProposal.class, institutionalProposalId);
        for (InstitutionalProposalSpecialReview specialReview : institutionalProposal.getSpecialReviews()) {
            if (SpecialReviewType.HUMAN_SUBJECTS.equals(specialReview.getSpecialReviewTypeCode())) {                
                String protocolNumber = specialReview.getProtocolNumber();
                String fundingSourceNumber = institutionalProposal.getProposalNumber();
                String fundingSourceTypeCode = FundingSourceType.INSTITUTIONAL_PROPOSAL;
                
                if (!specialReviewService.isLinkedToProtocolFundingSource(protocolNumber, fundingSourceNumber, fundingSourceTypeCode)) {
                    String fundingSourceName = institutionalProposal.getSponsorName();
                    String fundingSourceTitle = institutionalProposal.getTitle();
                    specialReviewService.addProtocolFundingSourceForSpecialReview(
                        protocolNumber, fundingSourceNumber, fundingSourceTypeCode, fundingSourceName, fundingSourceTitle);
                }
            }
        }
    }
    
    /**
     * Submit a proposal to Grants.gov.
     * @param proposalDevelopmentDocument
     * @throws Exception
     */
    private void submitS2sApplication(ProposalDevelopmentDocument proposalDevelopmentDocument) throws Exception{
        S2SService s2sService = ((S2SService) KraServiceLocator.getService(S2SService.class));
        s2sService.submitApplication(proposalDevelopmentDocument);
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
    
    private String createInstitutionalProposal(DevelopmentProposal developmentProposal, Budget budget) {
        InstitutionalProposalService institutionalProposalService = KraServiceLocator.getService(InstitutionalProposalService.class);
        String proposalNumber = institutionalProposalService.createInstitutionalProposal(developmentProposal, budget);
        return proposalNumber;
    }
    
    private String createInstitutionalProposalVersion(String proposalNumber, DevelopmentProposal developmentProposal, Budget budget) {
        InstitutionalProposalService institutionalProposalService = KraServiceLocator.getService(InstitutionalProposalService.class);
        String versionNumber = institutionalProposalService.createInstitutionalProposalVersion(proposalNumber, developmentProposal, budget);
        return versionNumber;
    }
    
    

    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = super.reload(mapping, form, request, response);
        this.populateSponsorForms(form);
        return forward;
    }
    
    /**
     * 
     * This method is called to populate sponsor forms under Print
     * @param form
     * @throws Exception
     */
    public void populateSponsorForms(ActionForm form) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getDocument();
        ProposalDevelopmentPrintingService printService = KraServiceLocator.getService(ProposalDevelopmentPrintingService.class);
        printService.populateSponsorForms(proposalDevelopmentForm.getSponsorFormTemplates(), proposalDevelopmentDocument.getDevelopmentProposal().getSponsorCode());
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
        
        List<SponsorFormTemplateList> sponsorFormTemplateLists = proposalDevelopmentForm.getSponsorFormTemplates();
        ProposalDevelopmentPrintingService printService = KraServiceLocator.getService(ProposalDevelopmentPrintingService.class);
        List<SponsorFormTemplate> printFormTemplates = new ArrayList<SponsorFormTemplate>();  
        printFormTemplates = printService.getSponsorFormTemplates(sponsorFormTemplateLists); 
        Map<String,Object> reportParameters = new HashMap<String,Object>();
        reportParameters.put(ProposalDevelopmentPrintingService.SELECTED_TEMPLATES, printFormTemplates);
        
        // TODO fix - printing code does not catch null fields in proposal and does not fail gracefully when certain fields are blank
        try {
            AttachmentDataSource dataStream = printService.printProposalDevelopmentReport(proposalDevelopmentDocument.getDevelopmentProposal(), 
                    ProposalDevelopmentPrintingService.PRINT_PROPOSAL_SPONSOR_FORMS, reportParameters);
            streamToResponse(dataStream, response);
            return null;//mapping.findForward(Constants.MAPPING_AWARD_BASIC);

        }
        catch (NullPointerException npe) {
            LOG.error("Error generating print stream for proposal forms", npe);
            GlobalVariables.getMessageMap().putError("print.nofield", KeyConstants.ERROR_PRINTING_UNKNOWN);
            return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
        }

//        
//        if(!printFormTemplates.isEmpty()) {
//            String contentType = Constants.PDF_REPORT_CONTENT_TYPE;
//            String ReportName = proposalNumber.concat("_" + proposalDevelopmentDocument.getDevelopmentProposal().getSponsorCode()).concat(Constants.PDF_FILE_EXTENSION);
//            streamToResponse(printFormTemplates, proposalNumber, contentType, ReportName, response);
//            actionForward = null;
//        }
//        return actionForward;
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
        return super.printForms(mapping, form, request, response);
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
            idx = methodToCallAttribute.indexOf("&activePanelName=");
            if (idx > 0) {
                activePanelName = methodToCallAttribute.substring(methodToCallAttribute.indexOf("=", idx)+1,methodToCallAttribute.indexOf(".", idx));
            }
        }
        
        forward = StringUtils.replace(forward, "budgetParameters.do?", "budgetExpenses.do?auditActivated=true&");
        if (viewBudgetPeriodParam != null) {
            forward = StringUtils.replace(forward, "budgetExpenses.do?", "budgetExpenses.do?"+viewBudgetPeriodParam); 
        }
        
        if (StringUtils.isNotBlank(activePanelName) && "Personnel".equals(activePanelName)) {
            forward = StringUtils.replace(forward, "budgetExpenses.do?", "budgetPersonnel.do?activePanelName=" + activePanelName + "&"); 
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
            String routeHeaderId = budgetDocument.getDocumentHeader().getWorkflowDocument().getDocumentId();
            forward = buildForwardUrl(routeHeaderId);
        } catch (Exception e) {
            LOG.info("forward to budgetsummary "+e.getStackTrace());
            //TODO what is the forward here
        }
        return forward;

    }

    public ActionForward linkChildToHierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm)form;
        DevelopmentProposal hierarchyProposal = pdForm.getDocument().getDevelopmentProposal();
        DevelopmentProposal newChildProposal = getHierarchyHelper().getDevelopmentProposal(pdForm.getNewHierarchyChildProposalNumber());
        String hierarchyBudgetTypeCode = pdForm.getNewHierarchyBudgetTypeCode();
        if (newChildProposal == null) {
            GlobalVariables.getMessageMap().putError(ProposalHierarchyKeyConstants.FIELD_CHILD_NUMBER, KeyConstants.ERROR_REQUIRED, "Link Child Proposal");
        }
        else {
            getHierarchyHelper().linkToHierarchy(hierarchyProposal, newChildProposal, hierarchyBudgetTypeCode);
            pdForm.setNewHierarchyChildProposalNumber("");
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward syncAllHierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm)form;
        //DevelopmentProposal hierarchyProposal = pdForm.getDocument().getDevelopmentProposal();
        getHierarchyHelper().syncAllHierarchy(pdForm.getDocument());
        if (GlobalVariables.getMessageMap().containsMessageKey(ProposalHierarchyKeyConstants.QUESTION_EXTEND_PROJECT_DATE_CONFIRM)) {
            return doEndDateConfirmation(mapping, form, request, response, "syncAllHierarchy", "syncAllHierarchyConfirm");
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
        //return reload(mapping, form, request, response);
    }
    
    public ActionForward syncAllHierarchyConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm)form;
        //DevelopmentProposal hierarchyProposal = pdForm.getDocument().getDevelopmentProposal();
        getHierarchyHelper().syncAllHierarchy(pdForm.getDocument(), true);
        return mapping.findForward(Constants.MAPPING_BASIC);
        //return reload(mapping, form, request, response);
    }
    
    public ActionForward removeFromHierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        StrutsConfirmation question = buildParameterizedConfirmationQuestion(mapping, form, request, response, "removeFromHierarchy", ProposalHierarchyKeyConstants.QUESTION_REMOVE_CONFIRM);
        return confirm(question, "removeFromHierarchyConfirmed", "hierarchyActionCanceled");
    }
        
    public ActionForward removeFromHierarchyConfirmed(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm)form;
        DevelopmentProposal childProposal = pdForm.getDocument().getDevelopmentProposal();
        getHierarchyHelper().removeFromHierarchy(childProposal);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward hierarchyActionCanceled(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        KNSGlobalVariables.getMessageList().add(ProposalHierarchyKeyConstants.MESSAGE_ACTION_CANCEL);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward syncToHierarchyParent(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm)form;
        DevelopmentProposal childProposal = pdForm.getDocument().getDevelopmentProposal();
        getHierarchyHelper().syncToHierarchyParent(childProposal);
        if (GlobalVariables.getMessageMap().containsMessageKey(ProposalHierarchyKeyConstants.QUESTION_EXTEND_PROJECT_DATE_CONFIRM)) {
            return doEndDateConfirmation(mapping, form, request, response, "syncToHierarchyParent", "syncToHierarchyParentConfirm");
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward syncToHierarchyParentConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm)form;
        DevelopmentProposal childProposal = pdForm.getDocument().getDevelopmentProposal();
        getHierarchyHelper().syncToHierarchyParent(childProposal, true);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward createHierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm)form;
        DevelopmentProposal initialChildProposal = pdForm.getDocument().getDevelopmentProposal();
        getHierarchyHelper().createHierarchy(initialChildProposal);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward linkToHierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm)form;
        DevelopmentProposal hierarchyProposal = getHierarchyHelper().getDevelopmentProposal(pdForm.getNewHierarchyProposalNumber());
        DevelopmentProposal newChildProposal = pdForm.getDocument().getDevelopmentProposal();
        String hierarchyBudgetTypeCode = pdForm.getNewHierarchyBudgetTypeCode();
        if (hierarchyProposal == null) {
            GlobalVariables.getMessageMap().putError(ProposalHierarchyKeyConstants.FIELD_PARENT_NUMBER, KeyConstants.ERROR_REQUIRED, "Link to Hierarchy");
        }
        else {
            getHierarchyHelper().linkToHierarchy(hierarchyProposal, newChildProposal, hierarchyBudgetTypeCode);
            if (GlobalVariables.getMessageMap().containsMessageKey(ProposalHierarchyKeyConstants.QUESTION_EXTEND_PROJECT_DATE_CONFIRM)) {
                return doEndDateConfirmation(mapping, form, request, response, "linkToHierarchy", "linkToHierarchyConfirm");
            }
            pdForm.setNewHierarchyProposalNumber("");
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward linkToHierarchyConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm)form;
        DevelopmentProposal hierarchyProposal = getHierarchyHelper().getDevelopmentProposal(pdForm.getNewHierarchyProposalNumber());
        DevelopmentProposal newChildProposal = pdForm.getDocument().getDevelopmentProposal();
        String hierarchyBudgetTypeCode = pdForm.getNewHierarchyBudgetTypeCode();
        if (hierarchyProposal == null) {
            GlobalVariables.getMessageMap().putError(ProposalHierarchyKeyConstants.FIELD_PARENT_NUMBER, KeyConstants.ERROR_REQUIRED, "Link to Hierarchy");
        }
        else {
            getHierarchyHelper().linkToHierarchy(hierarchyProposal, newChildProposal, hierarchyBudgetTypeCode, true);
            pdForm.setNewHierarchyProposalNumber("");
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ParameterService getParameterService() {
        return KraServiceLocator.getService(ParameterService.class);
    }
   
    
    @Override
    public ActionForward cancel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument pdDoc = pdForm.getDocument();
        pdDoc.prepareForSave();
        return super.cancel(mapping, form, request, response);
    }
    
    @Override
    public ActionForward disapprove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        return super.disapprove(mapping, form, request, response);
    }
    
    @Override
    public ActionForward fyi(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        ((ProposalDevelopmentForm)form).getDocument().prepareForSave();
        return super.fyi(mapping, form, request, response);
    }

    @Override
    public ActionForward blanketApprove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        
        ActionForward returnForward = super.blanketApprove(mapping, form, request, response);
        
        String routeHeaderId = ((ProposalDevelopmentForm) form).getDocument().getDocumentNumber();
        String returnLocation = buildActionUrl(routeHeaderId, Constants.MAPPING_PROPOSAL_ACTIONS, "ProposalDevelopmentDocument");
        
        ActionForward forward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
        ActionForward holdingPageForward = mapping.findForward(Constants.MAPPING_HOLDING_PAGE);
        return routeToHoldingPage(forward, returnForward, holdingPageForward, returnLocation);
    }

    @Override
    public ActionForward acknowledge(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        ((ProposalDevelopmentForm)form).getDocument().prepareForSave();
        return super.acknowledge(mapping, form, request, response);
    }
    
    /**
     * Prepare current report (i.e. Awards that selected person is on)
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward printCurrentReportPdf(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        CurrentAndPendingReportService currentAndPendingReportService = KraServiceLocator
                .getService(CurrentAndPendingReportService.class);
        ReportHelperBean helper = ((ReportHelperBeanContainer) form).getReportHelperBean();
        Map<String, Object> reportParameters = new HashMap<String, Object>();
        reportParameters.put(CurrentAndPendingReportService.PERSON_ID_KEY, helper.getPersonId());
        reportParameters.put(CurrentAndPendingReportService.REPORT_PERSON_NAME_KEY, helper.getTargetPersonName());
        AttachmentDataSource dataStream = currentAndPendingReportService.printCurrentAndPendingSupportReport(
                CurrentAndPendingReportService.CURRENT_REPORT_TYPE, reportParameters);
        streamToResponse(dataStream.getContent(), dataStream.getFileName(), null, response);
        return null;
    }

    /**
     * Prepare pending report (i.e. InstitutionalProposals that selected person is on) {@inheritDoc}
     */
    public ActionForward printPendingReportPdf(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        CurrentAndPendingReportService currentAndPendingReportService = KraServiceLocator
                .getService(CurrentAndPendingReportService.class);
        ReportHelperBean helper = ((ReportHelperBeanContainer) form).getReportHelperBean();
        Map<String, Object> reportParameters = new HashMap<String, Object>();
        reportParameters.put(CurrentAndPendingReportService.PERSON_ID_KEY, helper.getPersonId());
        reportParameters.put(CurrentAndPendingReportService.REPORT_PERSON_NAME_KEY, helper.getTargetPersonName());
        AttachmentDataSource dataStream = currentAndPendingReportService.printCurrentAndPendingSupportReport(
                CurrentAndPendingReportService.PENDING_REPORT_TYPE, reportParameters);
        streamToResponse(dataStream.getContent(), dataStream.getFileName(), null, response);
        return null;
    }

    /**
     * Prepare current report (i.e. Awards that selected person is on)
     * {@inheritDoc}
     */
    public ActionForward prepareCurrentReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                                                                                                                    throws Exception {
        ReportHelperBean helper = ((ReportHelperBeanContainer)form).getReportHelperBean();
        request.setAttribute(CurrentAndPendingReportService.CURRENT_REPORT_ROWS_KEY, helper.prepareCurrentReport());
        request.setAttribute(CurrentAndPendingReportService.REPORT_PERSON_NAME_KEY, helper.getTargetPersonName()); 
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * Prepare pending report (i.e. InstitutionalProposals that selected person is on)
     * {@inheritDoc}
     */
    public ActionForward preparePendingReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                                                                                                                    throws Exception {
        ReportHelperBean helper = ((ReportHelperBeanContainer)form).getReportHelperBean();
        request.setAttribute(CurrentAndPendingReportService.PENDING_REPORT_ROWS_KEY, helper.preparePendingReport());
        request.setAttribute(CurrentAndPendingReportService.REPORT_PERSON_NAME_KEY, helper.getTargetPersonName());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    private ActionForward doEndDateConfirmation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String questionId, String yesMethodName) throws Exception {
        List<ErrorMessage> errors = GlobalVariables.getMessageMap().getErrorMessagesForProperty(ProposalHierarchyKeyConstants.FIELD_CHILD_NUMBER);
        List<String> proposalNumbers = new ArrayList<String>();
        for (ErrorMessage error : errors) {
            if (error.getErrorKey().equals(ProposalHierarchyKeyConstants.QUESTION_EXTEND_PROJECT_DATE_CONFIRM)) {
                proposalNumbers.add(error.getMessageParameters()[0]);
            }
        }
        String proposalNumberList = StringUtils.join(proposalNumbers, ',');
        StrutsConfirmation question = buildParameterizedConfirmationQuestion(mapping, form, request, response, questionId, ProposalHierarchyKeyConstants.QUESTION_EXTEND_PROJECT_DATE_CONFIRM, proposalNumberList);
        GlobalVariables.getMessageMap().getErrorMessages().clear();
        GlobalVariables.getMessageMap().getWarningMessages().clear();
        GlobalVariables.getMessageMap().getInfoMessages().clear();
        return confirm(question, yesMethodName, "hierarchyActionCanceled");
    }
    
    private boolean autogenerateInstitutionalProposal() {
        return getParameterService().getParameterValueAsBoolean(
                Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, 
                ParameterConstants.DOCUMENT_COMPONENT,
                KeyConstants.AUTOGENERATE_INSTITUTIONAL_PROPOSAL_PARAM);
    }
    
    public ActionForward deleteProposal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (((ProposalDevelopmentForm) form).getDocument().getDevelopmentProposal().isInHierarchy()) {
            KNSGlobalVariables.getMessageList().add(new ErrorMessage(KeyConstants.ERROR_DELETE_PROPOSAL_IN_HIERARCHY));
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
        StrutsConfirmation question = 
            buildParameterizedConfirmationQuestion(mapping, form, request, response, DOCUMENT_DELETE_QUESTION, KeyConstants.QUESTION_DELETE_PROPOSAL);
        return confirm(question, "confirmDeleteProposal", "");
    }
    
    public ActionForward confirmDeleteProposal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm propDevForm = (ProposalDevelopmentForm) form;
        KraServiceLocator.getService(ProposalDevelopmentService.class).deleteProposal(propDevForm.getDocument());
        return mapping.findForward("portal");
    }

    public ActionForward sendNotification(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        DevelopmentProposal developmentProposal = proposalDevelopmentForm.getDocument().getDevelopmentProposal();
        
        ProposalDevelopmentNotificationRenderer renderer = new ProposalDevelopmentNotificationRenderer(developmentProposal);
        ProposalDevelopmentNotificationContext context = new ProposalDevelopmentNotificationContext(developmentProposal, null, "Ad-Hoc Notification", renderer);
        
        proposalDevelopmentForm.getNotificationHelper().initializeDefaultValues(context);
        
        return mapping.findForward("notificationEditor");
    }
    
}