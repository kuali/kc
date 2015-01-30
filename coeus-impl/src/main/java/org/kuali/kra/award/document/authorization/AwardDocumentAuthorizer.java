/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.award.document.authorization;

import org.kuali.coeus.common.framework.auth.KcTransactionalDocumentAuthorizerBase;
import org.kuali.coeus.common.framework.auth.task.ApplicationTask;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.award.awardhierarchy.AwardHierarchyService;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.infrastructure.AwardTaskNames;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.permission.PermissionService;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import java.util.*;

/**
 * This class is the Award Document Authorizer.  It determines the edit modes and
 * document actions for all award documents.
 */
public class AwardDocumentAuthorizer extends KcTransactionalDocumentAuthorizerBase {
    
    private AwardHierarchyService awardHierarchyService;
    
    /**
     * @see org.kuali.rice.kns.document.authorization.TransactionalDocumentAuthorizer#getEditModes(
     * org.kuali.rice.krad.document.Document, org.kuali.rice.kim.api.identity.Person, java.util.Set)
     */
    public Set<String> getEditModes(Document document, Person user, Set<String> currentEditModes) {
        Set<String> editModes = new HashSet<String>();
        
        AwardDocument awardDocument = (AwardDocument) document;
        
        if (awardDocument.getAward().getAwardId() == null) {
            if (canCreateAward(user.getPrincipalId())) {
                editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);         
                if (canViewChartOfAccountsElement(awardDocument)) {
                    editModes.add("viewChartOfAccountsElement");
                }
                if (canViewAccountElement(awardDocument)) {
                    editModes.add("viewAccountElement");
                }
            }
            else {
                editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
            }
        }
        else {
            boolean isCanceled = awardDocument.isCanceled();
            if (!awardDocument.isCanceled() && canExecuteAwardTask(user.getPrincipalId(), awardDocument, AwardTaskNames.MODIFY_AWARD.getAwardTaskName())) {  
                editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
            }
            else if (canExecuteAwardTask(user.getPrincipalId(), awardDocument, AwardTaskNames.VIEW_AWARD.getAwardTaskName())) {
                editModes.add(AuthorizationConstants.EditMode.VIEW_ONLY);
            }
            else {
                editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
            }
            
            if (!isCanceled && canExecuteAwardTask(user.getPrincipalId(), awardDocument, TaskName.ADD_BUDGET)) {
                editModes.add("addBudget");
            }
                    
            if (canExecuteAwardTask(user.getPrincipalId(), awardDocument, TaskName.OPEN_BUDGETS)) {
                editModes.add("openBudgets");
            }
                    
            if (!isCanceled && canExecuteAwardTask(user.getPrincipalId(), awardDocument, TaskName.MODIFY_BUDGET)) {
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
            if (canViewChartOfAccountsElement(awardDocument)) {
                editModes.add("viewChartOfAccountsElement");
            }
            if (canViewAccountElement(awardDocument)) {
                editModes.add("viewAccountElement");
            }
        }
        
        return editModes;
    }
    
    @Override
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
        String status = document.getDocumentHeader().getWorkflowDocument().getStatus().getCode();
        // if document is in processed or final state
        if (status.equalsIgnoreCase(KewApiConstants.ROUTE_HEADER_PROCESSED_CD) || 
            status.equalsIgnoreCase(KewApiConstants.ROUTE_HEADER_FINAL_CD)) {
            
            // if the integration parameter is ON
            if (isFinancialSystemIntegrationParameterOn()) {
                hasPermission = hasCreateAccountPermission(awardDocument);
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
        Boolean awardAccountParameter = getParameterService().getParameterValueAsBoolean (
                                                                Constants.PARAMETER_MODULE_AWARD, 
                                                                ParameterConstants.DOCUMENT_COMPONENT, 
                                                                Constants.FIN_SYSTEM_INTEGRATION_ON_OFF_PARAMETER);
        return awardAccountParameter;
    }
    
    public boolean hasCreateAccountPermission(AwardDocument document) {  
        return canExecuteAwardTask(GlobalVariables.getUserSession().getPrincipalId(), document, AwardTaskNames.CREATE_AWARD_ACCOUNT.getAwardTaskName());
    }
    
    /*
     * This only appears when the integration is ON
     */
    public boolean canViewChartOfAccountsElement(AwardDocument document) {
        if (hasCreateAccountPermission(document) && isFinancialSystemIntegrationParameterOn()) {
            return true;
        }
        return false;
    }
    /*
     * This field appears even if the financial integration if OFF 
     * but when it is ON, the user needs to have
     * the create account permission to view it.
     */
    public boolean canViewAccountElement(AwardDocument document) {
        boolean hasPermission = true;
        if (isFinancialSystemIntegrationParameterOn()) { 
            if (!hasCreateAccountPermission(document)) {
                hasPermission = false;
            }
        }
        return hasPermission;
    }
    @Override
    public boolean canOpen(Document document, Person user) {
        AwardDocument awardDocument = (AwardDocument) document;
        if (awardDocument.getAward().getAwardId() == null) {
            return canCreateAward(user.getPrincipalId());
        }
        return canExecuteAwardTask(user.getPrincipalId(), (AwardDocument) document, AwardTaskNames.VIEW_AWARD.getAwardTaskName());
    }
    
    @Override
    public boolean canEdit(Document document, Person user) {
        boolean isCanceled = ((AwardDocument)document).isCanceled();
        return !isCanceled && canExecuteAwardTask(user.getPrincipalId(), (AwardDocument) document, AwardTaskNames.MODIFY_AWARD.getAwardTaskName());
    }
    
    @Override
    public boolean canSave(Document document, Person user) {
        return canEdit(document, user);
    }
  
    @Override
    public boolean canCopy(Document document, Person user) {
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
        if(currentAward.getSequenceNumber() == 1) {
            return true;
        }else {
            return false;
        }
    }
    /**
     * @throws WorkflowException 
     * @throws WorkflowException 
     * @see org.kuali.coeus.common.framework.auth.KcTransactionalDocumentAuthorizerBase#canCancel(org.kuali.rice.krad.document.Document, org.kuali.rice.kim.api.identity.Person)
     */
    @Override
    public boolean canCancel(Document document, Person user) {
        if(!canEdit(document, user)) {
            return false;
        }
        
        boolean canCancel = true;
        
        DocumentHeader docHeader = document.getDocumentHeader();
        WorkflowDocument workflowDoc = docHeader.getWorkflowDocument();
        if(workflowDoc.isSaved()) {  
            //User cannot cancel if there are FINAL child awards and if this document is the first version 
            //which could possibly happen after an AH is copied
            AwardDocument awardDocument = (AwardDocument) document;
            AwardHierarchyService awardHierarchyService = KcServiceLocator.getService(AwardHierarchyService.class);
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
    public boolean canApprove(Document document, Person user) {
        return isEnroute(document) &&  super.canApprove(document, user);
    }
    
    /**
     * Can the user disapprove the given document?
     * @param document the document
     * @param user the user
     * @return true if the user can disapprove the document; otherwise false
     */
    @Override
    public boolean canDisapprove(Document document, Person user) {
        return canApprove(document, user);
    }
    
    /**
     * Can the user blanket approve the given document?
     * @param document the document
     * @param user the user
     * @return true if the user can blanket approve the document; otherwise false
     */
    @Override
    public boolean canBlanketApprove(Document document, Person user) {
        boolean canBA = false;
        PermissionService permService = KcServiceLocator.getService(KimApiServiceLocator.KIM_PERMISSION_SERVICE);
        canBA = 
                (!(isFinal(document)||isProcessed (document))&&
                        permService.hasPermission (user.getPrincipalId(), "KC-AWARD", "Blanket Approve AwardDocument"));
        if (!isFinal(document) &&canBA){
            // check system parameter - if Y, use default workflow behavior: allow a user with the permission
            // to perform the blanket approve action at any time
            try {
                if ( getParameterService().getParameterValueAsBoolean(KRADConstants.KNS_NAMESPACE, KRADConstants.DetailTypes.DOCUMENT_DETAIL_TYPE, KRADConstants.SystemGroupParameterNames.ALLOW_ENROUTE_BLANKET_APPROVE_WITHOUT_APPROVAL_REQUEST_IND) ) {
                    return canEdit(document);
                }
            } catch ( IllegalArgumentException ex ) {
                // do nothing, the parameter does not exist and defaults to "N"
            }
            // (prior to routing)
            WorkflowDocument workflowDocument = document.getDocumentHeader().getWorkflowDocument();
            if ( canRoute(document)){
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
            awardHierarchyService = 
                    KcServiceLocator.getService(AwardHierarchyService.class);
        }
        return awardHierarchyService;
    }

    @Override
    public boolean canSendNoteFyi(Document document, Person user) {
        return false;
    }

    @Override
    public boolean canFyi(Document document, Person user) {
        return isProcessed(document) && super.canFyi(document, user);
    }
    @Override
    public boolean canRoute(Document document, Person user) {
        boolean canRoute = false;
        PermissionService permService = KcServiceLocator.getService(KimApiServiceLocator.KIM_PERMISSION_SERVICE);
        canRoute = 
                (!(isFinal(document)||isProcessed (document))&&
                        permService.hasPermission (user.getPrincipalId(), "KC-AWARD", "Submit Award"));
        return canRoute;
    }
    @Override
    public boolean canAcknowledge(Document document, Person user) {      
        return isProcessed (document) && super.canAcknowledge(document, user);
    }
    
    protected boolean isProcessed (Document document){
       boolean isProcessed = false;
       String status = document.getDocumentHeader().getWorkflowDocument().getStatus().getCode();
       // if document is in processed state
       if (status.equalsIgnoreCase(KewApiConstants.ROUTE_HEADER_PROCESSED_CD))
               isProcessed = true;
       return isProcessed;   
   }
}
