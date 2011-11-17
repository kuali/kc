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
package org.kuali.kra.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.CoPiInfoDO;
import org.kuali.kra.proposaldevelopment.bo.CostShareInfoDO;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalDevelopmentApproverViewDO;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.kra.service.KraWorkflowService;
import org.kuali.rice.kew.dto.ActionRequestDTO;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kew.service.WorkflowDocument;
import org.kuali.rice.kew.service.WorkflowInfo;
import org.kuali.rice.kew.service.WorkflowUtility;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kns.bo.DocumentHeader;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;

/**
 * KRA Workflow Service Implementation.
 */
public class KraWorkflowServiceImpl implements KraWorkflowService {
    private WorkflowUtility workflowUtility;
    
    public void setWorkflowUtility(WorkflowUtility workflowUtility) {
        this.workflowUtility = workflowUtility;
    }

    static Log LOG = LogFactory.getLog(KraWorkflowService.class);
    
    /**
     * @see org.kuali.kra.service.KraWorkflowService#hasWorkflowPermission(java.lang.String, org.kuali.rice.kns.document.Document)
     */
    public boolean hasWorkflowPermission(String userId, Document doc) {
        boolean hasPermission = false;
        KualiWorkflowDocument workflowDoc = getWorkflowDocument(doc);
        if (workflowDoc != null && isInWorkflow(doc)) {
            Long routeHeaderId = workflowDoc.getRouteHeader().getRouteHeaderId();
            WorkflowInfo info = new WorkflowInfo();
            try {
                hasPermission = info.isUserAuthenticatedByRouteLog(routeHeaderId, userId, true);
            }
            catch (WorkflowException e) {
            }
        }
        return hasPermission;
    }

    /**
     * @see org.kuali.kra.service.KraWorkflowService#isClosed(org.kuali.rice.kns.document.Document)
     */
    public boolean isClosed(Document doc) {
        boolean isClosed = false;
        KualiWorkflowDocument workflowDoc = getWorkflowDocument(doc);
        if (workflowDoc != null) {
            isClosed = workflowDoc.stateIsApproved() ||
                       workflowDoc.stateIsCanceled() ||
                       workflowDoc.stateIsDisapproved() ||
                       workflowDoc.stateIsException();
        }
        return isClosed;
    }

    /**
     * @see org.kuali.kra.service.KraWorkflowService#isEnRoute(org.kuali.rice.kns.document.Document)
     */
    public boolean isEnRoute(Document doc) {
        boolean isEnRoute = false;
        KualiWorkflowDocument workflowDoc = getWorkflowDocument(doc);
        if (workflowDoc != null) {
            isEnRoute = workflowDoc.stateIsEnroute();
        }
        return isEnRoute;
    }
   
    /**
     * @see org.kuali.kra.service.KraWorkflowService#isInWorkflow(org.kuali.rice.kns.document.Document)
     */
    public boolean isInWorkflow(Document doc) {
        boolean isInWorkflow = false;
        KualiWorkflowDocument workflowDoc = getWorkflowDocument(doc);
        if (workflowDoc != null) {
            isInWorkflow = !(workflowDoc.stateIsInitiated() ||
                             workflowDoc.stateIsSaved());
        }
        return isInWorkflow;
    }
    
    /**
     * Get the corresponding workflow document.  
     * @param doc the document
     * @return the workflow document or null if there is none
     */
    protected KualiWorkflowDocument getWorkflowDocument(Document doc) {
        KualiWorkflowDocument workflowDocument = null;
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
        WorkflowDocument workDoc = null;
        try {
            workDoc = new WorkflowDocument(principalId, doc.getDocumentHeader().getWorkflowDocument().getRouteHeaderId());
        }
        catch (WorkflowException e) {
         // do nothing; there is no workflow document
        }
        return workDoc;
    }
    

    /**
     * @see org.kuali.kra.service.KraWorkflowService#isUserApprovalRequested(org.kuali.rice.kns.document.Document, java.lang.String)
     */
    public boolean isUserApprovalRequested(Document doc, String principalId) {
        boolean hasApprovalRequest = false;
        WorkflowDocument workDoc = getWorkflowDocument(doc,principalId);
        if(workDoc != null ) {
            hasApprovalRequest = workDoc.isApprovalRequested();
        }
        return hasApprovalRequest;
    }

    /**
     * @see org.kuali.kra.service.KraWorkflowService#isUserApprovalRequested(org.kuali.rice.kns.document.Document, java.lang.String)
     */
    public boolean isUserActionRequested(Document doc, String principalId) {
        boolean hasActionRequest = false;
        WorkflowDocument workDoc = getWorkflowDocument(doc,principalId);
        if(workDoc != null ) {
            hasActionRequest = workDoc.isApprovalRequested() || workDoc.isAcknowledgeRequested() || workDoc.isFYIRequested();
        }
        return hasActionRequest;
    }
    
    /**
     * @see org.kuali.kra.service.KraWorkflowService#isDocumentOnNode(org.kuali.rice.kns.document.Document, java.lang.String)
     */
    public boolean isDocumentOnNode(Document document,String nodeName) {
        boolean result = false;
        try {
            result = document != null && ArrayUtils.contains(document.getDocumentHeader().getWorkflowDocument().getNodeNames(), nodeName);
            return result;
        } catch(WorkflowException we) {
            LOG.error( String.format( "WorkflowException generated when trying to determine if document %s is on %s node.  Reason:%s", nodeName,document.getDocumentNumber(), we.getMessage()) );
            throw new RuntimeException( String.format( "WorkflowException generated when trying determine if document %s is on %s route node.  Reason:%s", nodeName, document.getDocumentNumber(), we.getMessage()), we ); 
        }
    }
    
    
    public boolean isUserAdHocRequestRecipient(Document document, String principalId, String nodeName) {
        try {
            ActionRequestDTO[] actionRequestsForCurrentUser = workflowUtility.getActionRequests(Long.parseLong(document.getDocumentNumber()), nodeName, principalId);
            for(ActionRequestDTO actionRequest : actionRequestsForCurrentUser) {
                if(actionRequest.isAdHocRequest() && actionRequest.isPending()) { 
                    return true;
                }
            }
        }
        catch (WorkflowException e) {
            throw new RuntimeException(e.getMessage());
        }
        return false;
    }
    
    public boolean isUserRouteRespRequestRecipient(Document document, String principalId, String nodeName) {
        try {
            ActionRequestDTO[] actionRequestsForCurrentUser = workflowUtility.getActionRequests(Long.parseLong(document.getDocumentNumber()), nodeName, principalId);
            for(ActionRequestDTO actionRequest : actionRequestsForCurrentUser) {
                if(actionRequest.isPending() && actionRequest.isRouteModuleRequest()) { 
                    return true;
                }
            }
        }
        catch (WorkflowException e) {
            throw new RuntimeException(e.getMessage());
        }
        return false;
    } 
    
    
public ProposalDevelopmentApproverViewDO populateApproverViewDO (ProposalDevelopmentForm proposalDevelopmentForm) {
        
        ProposalDevelopmentApproverViewDO approverViewDO = new ProposalDevelopmentApproverViewDO();
        DevelopmentProposal proposal = proposalDevelopmentForm.getDocument().getDevelopmentProposalList().get(0);
        ProposalDevelopmentService proposalService = KraServiceLocator.getService(ProposalDevelopmentService.class);
        Budget budget = proposalService.getFinalBudget(proposal);
    
        int numberOfCostShare = 0;
        int numberOfCoPI = 0;
        
        /* populate PI info */
        List<CoPiInfoDO> coPiInfos = new ArrayList<CoPiInfoDO>();
        coPiInfos = proposalService.getCoPiPiInfo(proposal);
        approverViewDO.setCoPiInfos(coPiInfos);
        if (coPiInfos != null) {
            numberOfCoPI = coPiInfos.size();
        }
        
        /* populate cost share info */
        List<CostShareInfoDO> costShareInfos = new ArrayList<CostShareInfoDO>();
        if (budget != null) {
            costShareInfos = proposalService.getCostShareInfo(budget);
            approverViewDO.setCostShareInfos(costShareInfos);
            if (costShareInfos != null) {
                numberOfCostShare = costShareInfos.size();
            }
        }
            
        /* populate budget cost info */
        if (budget != null) {
            approverViewDO.setDirectCost(budget.getTotalDirectCost());
            approverViewDO.setIndirectCost(budget.getTotalIndirectCost());
            approverViewDO.setTotalCost(budget.getTotalCost());
        } 
        
        /* Fill the gap between CoPI number and Cost Share Number for JSP rendering purpose */
        if (numberOfCoPI > numberOfCostShare) {
            for (int i=0; i < numberOfCoPI - numberOfCostShare; i++) {
                CostShareInfoDO costShareInfo = new CostShareInfoDO();
                costShareInfos.add(costShareInfo);
            }
        } else if (numberOfCoPI < numberOfCostShare) {
            for (int i=0; i < numberOfCostShare - numberOfCoPI; i++) {
                CoPiInfoDO coPiInfo = new CoPiInfoDO();
                coPiInfos.add(coPiInfo);
            }
        }
        
        /* populate proposal info */
        approverViewDO.setActivityType(proposal.getActivityType().getDescription());
        approverViewDO.setDueDate(proposal.getDeadlineDate());
        approverViewDO.setStartDate(proposal.getRequestedStartDateInitial());
        approverViewDO.setEndDate(proposal.getRequestedEndDateInitial());
        approverViewDO.setProjectTitle(proposal.getTitle());
        approverViewDO.setLeadUnit(proposal.getOwnedByUnitNumber());
        approverViewDO.setProposalNumber(proposal.getProposalNumber());
        approverViewDO.setProposalType(proposal.getProposalType().getDescription());
        approverViewDO.setSponsorName(proposal.getSponsorName());
        approverViewDO.setPiName(proposal.getPrincipalInvestigatorName());
        
        return approverViewDO;          
    }

    /* Check to see if the current user can perform workflow action in proposal development document */
    public boolean canPerformWorkflowAction(ProposalDevelopmentDocument document) {
        
        // Not from the doc handler, don't show the approver view
        if (document.getDocumentHeader().getDocumentNumber() == null) {
            return false;
        }
                
        KcTransactionalDocumentAuthorizerBase documentAuthorizer = (KcTransactionalDocumentAuthorizerBase) KNSServiceLocator.getDocumentHelperService().getDocumentAuthorizer(document);
        Person user = GlobalVariables.getUserSession().getPerson();
        Set<String> documentActions = documentAuthorizer.getDocumentActions(document, user, null);
    
        boolean canApprove= documentActions.contains(KNSConstants.KUALI_ACTION_CAN_APPROVE);
        //boolean canAck = documentActions.contains(KNSConstants.KUALI_ACTION_CAN_ACKNOWLEDGE);
        boolean canDisapprove = documentActions.contains(KNSConstants.KUALI_ACTION_CAN_DISAPPROVE);
        
        return canApprove || canDisapprove;
    }
    
}
