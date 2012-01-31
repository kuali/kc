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
package org.kuali.kra.award.document.authorization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.authorization.ApplicationTask;
import org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase;
import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.award.awardhierarchy.AwardHierarchyService;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.AwardTaskNames;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.kra.timeandmoney.service.ActivePendingTransactionsService;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kew.web.session.UserSession;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kim.bo.types.dto.AttributeSet;
import org.kuali.rice.kim.service.IdentityManagementService;
import org.kuali.rice.kim.util.KimConstants;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.kns.bo.DocumentHeader;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.service.ParameterConstants;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;

/**
 * This class is the Award Document Authorizer.  It determines the edit modes and
 * document actions for all award documents.
 */
public class AwardDocumentAuthorizer extends KcTransactionalDocumentAuthorizerBase {
    
    private AwardHierarchyService awardHierarchyService;
    /**
     * @see org.kuali.rice.kns.document.authorization.TransactionalDocumentAuthorizer#getEditModes(
     * org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person, java.util.Set)
     */
    public Set<String> getEditModes(Document document, Person user, Set<String> currentEditModes) {
        Set<String> editModes = new HashSet<String>();
        
        AwardDocument awardDocument = (AwardDocument) document;
        
        if (awardDocument.getAward().getAwardId() == null) {
            if (canCreateAward(user.getPrincipalId())) {
                editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);         
                if (canViewChartOfAccountsElement()) {
                    editModes.add("viewChartOfAccountsElement");
                }
            }
            else {
                editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
            }
        }
        else {
            if (canExecuteAwardTask(user.getPrincipalId(), awardDocument, AwardTaskNames.MODIFY_AWARD.getAwardTaskName())) {  
                editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
            }
            else if (canExecuteAwardTask(user.getPrincipalId(), awardDocument, AwardTaskNames.VIEW_AWARD.getAwardTaskName())) {
                editModes.add(AuthorizationConstants.EditMode.VIEW_ONLY);
            }
            else {
                editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
            }
            
            if (canExecuteAwardTask(user.getPrincipalId(), awardDocument, TaskName.ADD_BUDGET)) {
                editModes.add("addBudget");
            }
                    
            if (canExecuteAwardTask(user.getPrincipalId(), awardDocument, TaskName.OPEN_BUDGETS)) {
                editModes.add("openBudgets");
            }
                    
            if (canExecuteAwardTask(user.getPrincipalId(), awardDocument, TaskName.MODIFY_BUDGET)) {
                editModes.add("modifyAwardBudget");
            }
            
            if (canCreateAward(user.getPrincipalId())) {
                editModes.add(Constants.CAN_CREATE_AWARD_KEY);
            }
            
            if (canCreateAwardAccount(document, user)) {
                editModes.add("createAwardAccount");
            }
            if (awardHasHierarchyChildren(document)) {
                editModes.add("awardSync");
            }   
            if (canViewChartOfAccountsElement()) {
                editModes.add("viewChartOfAccountsElement");
            }
        }
        
        return editModes;
    }
    
    /**
     * @see org.kuali.rice.kns.document.authorization.DocumentAuthorizer#canInitiate(java.lang.String, org.kuali.rice.kim.bo.Person)
     */
    public boolean canInitiate(String documentTypeName, Person user) {
        return canCreateAward(user.getPrincipalId());
    }
  
    /**
     * This method decides if a user has permissions to create a financial account.
     * @param document
     * @param user
     * @return hasPermission
     */
    public boolean canCreateAwardAccount(Document document, Person user) {
        AwardDocument awardDocument = (AwardDocument) document;
        Award award = awardDocument.getAward();
        boolean hasPermission = false;
        String status = document.getDocumentHeader().getWorkflowDocument().getRouteHeader().getDocRouteStatus();
        // if document is in processed or final state
        if (status.equalsIgnoreCase(KEWConstants.ROUTE_HEADER_PROCESSED_CD) || 
            status.equalsIgnoreCase(KEWConstants.ROUTE_HEADER_FINAL_CD)) {
            
            // if the integration parameter is ON
            if (isFinancialSystemIntegrationParameterOn()) {
                hasPermission = hasCreateAccountPermission();
                // only the OSP admin can create a financial account
                // if account has already been created, anyone can see it
                if (award.getFinancialAccountDocumentNumber() != null) {
                    hasPermission = true;
                }
                
            }
        }
        return hasPermission;
    }
    
    protected boolean isFinancialSystemIntegrationParameterOn() {
        String awardAccountParameter = getParameterService().getParameterValue (
                                                                Constants.PARAMETER_MODULE_AWARD, 
                                                                ParameterConstants.DOCUMENT_COMPONENT, 
                                                                Constants.FIN_SYSTEM_INTEGRATION_ON_OFF_PARAMETER);
        return awardAccountParameter.equalsIgnoreCase(Constants.FIN_SYSTEM_INTEGRATION_ON) ? true : false;
    }
    
    public boolean hasCreateAccountPermission() {
        boolean hasPermission = false;
        IdentityManagementService identityManagementService = getIdentityManagementService();
        AttributeSet set = new AttributeSet();
        set.put("documentTypeName", "AwardDocument");
        set.put("documentAction", "Create award account");
        // if the user has permission.
        hasPermission = identityManagementService.hasPermission(UserSession.getAuthenticatedUser().getPrincipalId(), 
                                                                "KC-AWARD", "Create Award Account",set);
        return hasPermission;    
    }
    
    /*
     * Same permissions for creating and linking accounts
     */
    public boolean canViewChartOfAccountsElement() {
        if (hasCreateAccountPermission() && isFinancialSystemIntegrationParameterOn()) {
            return true;
        }
        return false;
    }
    
    /**
     * @see org.kuali.rice.kns.document.authorization.DocumentAuthorizer#canOpen(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    public boolean canOpen(Document document, Person user) {
        AwardDocument awardDocument = (AwardDocument) document;
        if (awardDocument.getAward().getAwardId() == null) {
            return canCreateAward(user.getPrincipalId());
        }
        return canExecuteAwardTask(user.getPrincipalId(), (AwardDocument) document, AwardTaskNames.VIEW_AWARD.getAwardTaskName());
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canEdit(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    @Override
    public boolean canEdit(Document document, Person user) {
        return canExecuteAwardTask(user.getPrincipalId(), (AwardDocument) document, AwardTaskNames.MODIFY_AWARD.getAwardTaskName());
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canSave(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    @Override
    protected boolean canSave(Document document, Person user) {
        return canEdit(document, user);
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canReload(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    @Override
    protected boolean canReload(Document document, Person user) {
        return canEdit(document, user);
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canCopy(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    @Override
    protected boolean canCopy(Document document, Person user) {
        return false;
    }
    
    private boolean doesAwardHierarchyContainFinalChildren(AwardHierarchy currentAward,  Map<String, AwardHierarchyNode> awardHierarchyNodes) {
        for(AwardHierarchy child : currentAward.getChildren()) {
            AwardHierarchyNode childInfo = awardHierarchyNodes.get(child.getAwardNumber());
            if(childInfo.isAwardDocumentFinalStatus()) {
                return true;
            }
            doesAwardHierarchyContainFinalChildren(childInfo, awardHierarchyNodes);
        }
        
        return false;
    }
    
    private boolean isCurrentAwardTheFirstVersion(Award currentAward) {
        //ActivePendingTransactionsService activePendingTransactionsService = KraServiceLocator.getService(ActivePendingTransactionsService.class);
        //Award activeAward = activePendingTransactionsService.getWorkingAwardVersion(currentAward.getAwardNumber());
//        if(activeAward != null && activeAward.getSequenceNumber().equals(currentAward.getSequenceNumber())) {
//            return true;
//        }
        if(currentAward.getSequenceNumber() == 1) {
            return true;
        }else {
            return false;
        }
    }
    /**
     * @throws WorkflowException 
     * @throws WorkflowException 
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canCancel(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    @Override
    protected boolean canCancel(Document document, Person user) {
        if(!canEdit(document, user)) {
            return false;
        }
        
        boolean canCancel = true;
        
        DocumentHeader docHeader = document.getDocumentHeader();
        KualiWorkflowDocument workflowDoc = docHeader.getWorkflowDocument();
        if(workflowDoc.stateIsSaved()) {  
            //User cannot cancel if there are FINAL child awards and if this document is the first version 
            //which could possibly happen after an AH is copied
            AwardDocument awardDocument = (AwardDocument) document;
            AwardHierarchyService awardHierarchyService = KraServiceLocator.getService(AwardHierarchyService.class);
            Award currentAward = awardDocument.getAward();
            Map<String, AwardHierarchyNode> awardHierarchyNodes = new HashMap<String, AwardHierarchyNode>();
            Map<String, AwardHierarchy> awardHierarchyItems = awardHierarchyService.getAwardHierarchy(awardDocument.getAward().getAwardNumber(), new ArrayList<String>());
            AwardHierarchy currentAwardNode = awardHierarchyItems.get(currentAward.getAwardNumber());
            if(currentAwardNode.isRootNode() && isCurrentAwardTheFirstVersion(currentAward)) { 
                awardHierarchyService.populateAwardHierarchyNodes(awardHierarchyItems, awardHierarchyNodes, currentAward.getAwardNumber(), currentAward.getSequenceNumber().toString());
                canCancel = !doesAwardHierarchyContainFinalChildren(currentAwardNode, awardHierarchyNodes);
            }
        }
        return canCancel;
    }
    
    /**
     * Can the user approve the given document?
     * @param document the document
     * @param user the user
     * @return true if the user can approve the document; otherwise false
     */
    @Override
    protected boolean canApprove(Document document, Person user) {
        return isEnroute(document) &&  super.canApprove(document, user);
    }
    
    /**
     * Can the user disapprove the given document?
     * @param document the document
     * @param user the user
     * @return true if the user can disapprove the document; otherwise false
     */
    @Override
    protected boolean canDisapprove(Document document, Person user) {
        return canApprove(document, user);
    }
    
    /**
     * Can the user blanket approve the given document?
     * @param document the document
     * @param user the user
     * @return true if the user can blanket approve the document; otherwise false
     */
    @Override
    protected boolean canBlanketApprove(Document document, Person user) {
        if (!isFinal(document) && isAuthorizedByTemplate(
                document,
                KNSConstants.KUALI_RICE_WORKFLOW_NAMESPACE,
                KimConstants.PermissionTemplateNames.BLANKET_APPROVE_DOCUMENT,
                user.getPrincipalId()) && super.canBlanketApprove(document, user)) {
            // check system parameter - if Y, use default workflow behavior: allow a user with the permission
            // to perform the blanket approve action at any time
            try {
                if ( getParameterService().getIndicatorParameter(KNSConstants.KNS_NAMESPACE, KNSConstants.DetailTypes.DOCUMENT_DETAIL_TYPE, KNSConstants.SystemGroupParameterNames.ALLOW_ENROUTE_BLANKET_APPROVE_WITHOUT_APPROVAL_REQUEST_IND) ) {
                    return canEdit(document);
                }
            } catch ( IllegalArgumentException ex ) {
                // do nothing, the parameter does not exist and defaults to "N"
            }
            // otherwise, limit the display of the blanket approve button to only the initiator of the document
            // (prior to routing)
            KualiWorkflowDocument workflowDocument = document.getDocumentHeader().getWorkflowDocument();
            if ( canRoute(document) && StringUtils.equals( workflowDocument.getInitiatorPrincipalId(), GlobalVariables.getUserSession().getPrincipalId() ) ) {
                return true;
            }
            // or to a user with an approval action request
            if ( workflowDocument.isApprovalRequested() ) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Does the user have permission to create a award?
     * @param user the user
     * @return true if the user can create a award; otherwise false
     */
    private boolean canCreateAward(String userId) {
        ApplicationTask task = new ApplicationTask(TaskName.CREATE_AWARD);
        return getTaskAuthorizationService().isAuthorized(userId, task);
    }
    
    /**
     * Does the user have permission to execute the given task for a award?
     * @param username the user's username
     * @param doc the award document
     * @param taskName the name of the task
     * @return true if has permission; otherwise false
     */
    private boolean canExecuteAwardTask(String userId, AwardDocument doc, String taskName) {
        AwardTask task = new AwardTask(taskName, doc.getAward());
        return getTaskAuthorizationService().isAuthorized(userId, task);
    }
    
    protected boolean awardHasHierarchyChildren(Document document) {
        AwardDocument awardDocument = (AwardDocument) document;
        AwardHierarchy hierarchy = getAwardHierarchyService().loadAwardHierarchyBranch(awardDocument.getAward().getAwardNumber());
        return hierarchy != null && hierarchy.hasChildren();
    }

    public AwardHierarchyService getAwardHierarchyService() {
        if (awardHierarchyService == null) {
            awardHierarchyService = KraServiceLocator.getService(AwardHierarchyService.class);
        }
        return awardHierarchyService;
    }

}
