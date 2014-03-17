/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.sys.impl.workflow;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.WorkflowDocumentFactory;
import org.kuali.rice.kew.api.action.ActionRequest;
import org.kuali.rice.kew.api.action.ActionRequestPolicy;
import org.kuali.rice.kew.api.action.RoutingReportCriteria;
import org.kuali.rice.kew.api.action.WorkflowDocumentActionsService;
import org.kuali.rice.kew.api.actionlist.ActionListService;
import org.kuali.rice.kew.api.document.DocumentDetail;
import org.kuali.rice.kew.api.document.WorkflowDocumentService;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * KRA Workflow Service Implementation.
 */
@Component("kcWorkflowService")
public class KcWorkflowServiceImpl implements KcWorkflowService {
    static Log LOG = LogFactory.getLog(KcWorkflowService.class);

    @Autowired
    @Qualifier("workflowDocumentActionsService")
    protected WorkflowDocumentActionsService workflowDocumentActionsService;

    @Autowired
    @Qualifier("kewWorkflowDocumentService")
    protected WorkflowDocumentService workflowDocumentService;

    @Autowired
    @Qualifier("actionListService")
    protected ActionListService actionListService;

    @Override
    public boolean hasWorkflowPermission(String userId, Document doc) {
        boolean hasPermission = false;
        WorkflowDocument workflowDoc = getWorkflowDocument(doc);
        if (workflowDoc != null && isInWorkflow(doc)) {
            String routeHeaderId = workflowDoc.getDocumentId();
            hasPermission = workflowDocumentActionsService.isUserInRouteLog(routeHeaderId, userId, true);
        }
        return hasPermission;
    }

    @Override
    public boolean isClosed(Document doc) {
        boolean isClosed = false;
        WorkflowDocument workflowDoc = getWorkflowDocument(doc);
        if (workflowDoc != null) {
            isClosed = workflowDoc.isApproved() ||
                       workflowDoc.isCanceled() ||
                       workflowDoc.isDisapproved() ||
                       workflowDoc.isException();
        }
        return isClosed;
    }

    @Override
    public boolean isEnRoute(Document doc) {
        boolean isEnRoute = false;
        WorkflowDocument workflowDoc = getWorkflowDocument(doc);
        if (workflowDoc != null) {
            isEnRoute = workflowDoc.isEnroute();
        }
        return isEnRoute;
    }
   
    @Override
    public boolean isInWorkflow(Document doc) {
        boolean isInWorkflow = false;
        WorkflowDocument workflowDoc = getWorkflowDocument(doc);
        if (workflowDoc != null) {
            isInWorkflow = !(workflowDoc.isInitiated() ||
                             workflowDoc.isSaved());
        }
        return isInWorkflow;
    }
    
    /**
     * Get the corresponding workflow document.  
     * @param doc the document
     * @return the workflow document or null if there is none
     */
    protected WorkflowDocument getWorkflowDocument(Document doc) {
        WorkflowDocument workflowDocument = null;
        if (doc != null) {
            DocumentHeader header = doc.getDocumentHeader();
            if (header != null) {
                try {
                    workflowDocument = header.getWorkflowDocument();
                } 
                catch (RuntimeException ex) {
                    // do nothing; there is no workflow document
                }
            }
        }
        return workflowDocument;
    }
    
     
    /**
     * This method gets the workflow document using the given principalId.
     * 
     * @param doc The document you want the workflow document for.
     * @param principalId The principalId to use getting the document.  This impacts the return values for isApprovalRequested, etc.
     * @return
     */
    protected WorkflowDocument getWorkflowDocument(Document doc, String principalId) {
        return WorkflowDocumentFactory.loadDocument(principalId, doc.getDocumentHeader().getWorkflowDocument().getDocumentId());
    }
    

    @Override
    public boolean isUserApprovalRequested(Document doc, String principalId) {
        boolean hasApprovalRequest = false;
        WorkflowDocument workDoc = getWorkflowDocument(doc,principalId);
        if(workDoc != null ) {
            hasApprovalRequest = workDoc.isApprovalRequested();
        }
        return hasApprovalRequest;
    }

    @Override
    public boolean isUserActionRequested(Document doc, String principalId) {
        boolean hasActionRequest = false;
        WorkflowDocument workDoc = getWorkflowDocument(doc,principalId);
        if(workDoc != null ) {
            hasActionRequest = workDoc.isApprovalRequested() || workDoc.isAcknowledgeRequested() || workDoc.isFYIRequested();
        }
        return hasActionRequest;
    }
    
    @Override
    public boolean isDocumentOnNode(Document document,String nodeName) {
        boolean result = false;
        try {
            result = document != null && document.getDocumentHeader().getWorkflowDocument().getNodeNames().contains(nodeName);
            return result;
        } catch(Exception we) {
            LOG.error( String.format( "Exception generated when trying to determine if document %s is on active %s node.  Reason:%s", nodeName,document.getDocumentNumber(), we.getMessage()) );
            throw new RuntimeException( String.format( "Exception generated when trying determine if document %s is on active %s route node.  Reason:%s", nodeName, document.getDocumentNumber(), we.getMessage()), we ); 
        }
    }
    
    @Override
    public boolean isCurrentNode(Document document, String nodeName){
        boolean result = false;
        try {
            result = document != null && document.getDocumentHeader().getWorkflowDocument().getCurrentNodeNames().contains(nodeName);
            return result;
        } catch(Exception we) {
            LOG.error( String.format( "Exception generated when trying to determine if document %s is on active or terminal %s node.  Reason:%s", nodeName,document.getDocumentNumber(), we.getMessage()) );
            throw new RuntimeException( String.format( "Exception generated when trying determine if document %s is on active or terminal %s route node.  Reason:%s", nodeName, document.getDocumentNumber(), we.getMessage()), we ); 
        }
    }

    public boolean isUserAdHocRequestRecipient(Document document, String principalId, String nodeName) {
        try {
            List<ActionRequest> actionRequestsForCurrentUser = workflowDocumentService.getActionRequestsForPrincipalAtNode(document.getDocumentNumber(), nodeName, principalId);
            for(ActionRequest actionRequest : actionRequestsForCurrentUser) {
                if(actionRequest.isAdHocRequest() && actionRequest.isPending()) { 
                    return true;
                }
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        return false;
    }
    
    public boolean isUserRouteRespRequestRecipient(Document document, String principalId, String nodeName) {
        try {
            List<ActionRequest> actionRequestsForCurrentUser = workflowDocumentService.getActionRequestsForPrincipalAtNode(document.getDocumentNumber(), nodeName, principalId);
            for(ActionRequest actionRequest : actionRequestsForCurrentUser) {
                if(actionRequest.isPending() && actionRequest.isRouteModuleRequest()) { 
                    return true;
                }
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        return false;
    }
    
    @Override
    public boolean isFinalApproval(WorkflowDocument workflowDoc) {       
        RoutingReportCriteria.Builder reportCriteriaBuilder = RoutingReportCriteria.Builder.createByDocumentId(workflowDoc.getDocumentId());
        Set<String> approvalNodes = new HashSet<String>();
        String currentRequest = null;
        
        DocumentDetail results1 = workflowDocumentActionsService.executeSimulation(reportCriteriaBuilder.build());
        for (ActionRequest actionRequest : results1.getActionRequests()) {
            if (actionRequest.isPending() && actionRequest.isApprovalRequest()) {
                if (actionRequest.isUserRequest() && willReceiveFutureRequests(workflowDoc, actionRequest.getPrincipalId())) {
                    approvalNodes.add(actionRequest.getNodeName());                    
                } else if (actionRequest.isGroupRequest()) {
                    approvalNodes.add(actionRequest.getNodeName());
                } else if (actionRequest.isRoleRequest() && !requestAlreadyApproved(workflowDoc, actionRequest)) {
                    approvalNodes.add(actionRequest.getNodeName());
                }
            }
        }
        if (currentRequest != null) {
            approvalNodes.remove(currentRequest);
        }

        return approvalNodes.size() == 0;
    }
    
    @Override
    public boolean requestAlreadyApproved(WorkflowDocument workflowDoc, ActionRequest actionRequest) {
        boolean result = false;
        for (ActionRequest childRequest : actionRequest.getChildRequests()) {
            if (childRequest.isUserRequest()) {
                boolean futureRequests = willReceiveFutureRequests(workflowDoc, childRequest.getPrincipalId()); 
                if (actionRequest.getRequestPolicy() == ActionRequestPolicy.ALL && futureRequests) {
                    result = false;
                } else if (actionRequest.getRequestPolicy() == ActionRequestPolicy.FIRST && !futureRequests) {
                    result = true;
                }
            } else if (childRequest.isGroupRequest()) {
                if (actionRequest.getRequestPolicy() == ActionRequestPolicy.ALL) {
                    result = false;
                }
            } else if (childRequest.isRoleRequest()) {
                if (requestAlreadyApproved(workflowDoc, childRequest)) {
                    if (actionRequest.getRequestPolicy() == ActionRequestPolicy.FIRST) {
                        result = true;
                    }
                } else if (actionRequest.getRequestPolicy() == ActionRequestPolicy.ALL){
                    result = false;
                }
            }
        }
        return result;
    }
       
    /**
     * Checks to see if the user has asked to receive future requests or not.
     * @param workflowDoc
     * @param principalId
     * @return true if the user has not asked to NOT receive future requests.
     */
    private boolean willReceiveFutureRequests(WorkflowDocument workflowDoc, String principalId) {
        boolean doNotReceiveFutureRequests = false;    

        Map<String, String> variables = workflowDoc.getVariables();
        if (variables != null && CollectionUtils.isNotEmpty(variables.keySet())) {
            Iterator<String> variableIterator = variables.keySet().iterator();
            while(variableIterator.hasNext()) {
                    String variableKey = variableIterator.next();
                    String variableValue = variables.get(variableKey);
                    if (variableKey.startsWith(KewApiConstants.RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_KEY)
                          && variableValue.toUpperCase().equals(KewApiConstants.DONT_RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_VALUE)
                          && variableKey.contains(principalId)) {
                        doNotReceiveFutureRequests = true; 
                        break;
                    }
            }
        } 
        
        return !doNotReceiveFutureRequests;
    }
    
    private static List<String> approvalCodes = new ArrayList<String>();
    static {
        approvalCodes.add("C");
        approvalCodes.add("A");
    }
    
    public boolean hasPendingApprovalRequests(WorkflowDocument workflowDoc) {
        return !actionListService.getActionItems(workflowDoc.getDocumentId(), approvalCodes).isEmpty();
    }

    protected ActionListService getActionListService() {
        return actionListService;
    }

    public void setActionListService(ActionListService actionListService) {
        this.actionListService = actionListService;
    }

    public void setWorkflowDocumentActionsService(WorkflowDocumentActionsService workflowDocumentActionsService) {
        this.workflowDocumentActionsService = workflowDocumentActionsService;
    }

    public void setWorkflowDocumentService(WorkflowDocumentService workflowDocumentService) {
        this.workflowDocumentService = workflowDocumentService;
    }
}
