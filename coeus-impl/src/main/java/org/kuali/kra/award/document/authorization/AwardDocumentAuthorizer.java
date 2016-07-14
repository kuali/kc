/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.common.framework.auth.task.ApplicationTask;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.award.awardhierarchy.AwardHierarchyService;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.infrastructure.AwardPermissionConstants;
import org.kuali.kra.award.infrastructure.AwardTaskNames;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.infrastructure.TimeAndMoneyPermissionConstants;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.doctype.service.DocumentTypePermissionService;
import org.kuali.rice.kew.routeheader.DocumentRouteHeaderValue;
import org.kuali.rice.kew.routeheader.service.RouteHeaderService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import java.util.*;

/**
 * This class is the Award Document Authorizer.  It determines the edit modes and
 * document actions for all award documents.
 */
public class AwardDocumentAuthorizer extends KcTransactionalDocumentAuthorizerBase {

    public static final String ADD_BUDGET = "addBudget";
    public static final String OPEN_BUDGETS = "openBudgets";
    public static final String MODIFY_AWARD_BUDGET = "modifyAwardBudget";
    public static final String CREATE_AWARD_ACCOUNT = "createAwardAccount";
    public static final String POST_AWARD = "postAward";
    public static final String POST_TIME_AND_MONEY = "postTimeAndMoney";
    public static final String AWARD_SYNC = "awardSync";
    public static final String CAN_MAINTAIN_AWARD_ATTACHMENTS = "CAN_MAINTAIN_AWARD_ATTACHMENTS";
    public static final String CAN_VIEW_AWARD_ATTACHMENTS = "CAN_VIEW_AWARD_ATTACHMENTS";
    private static final String VIEW_ACCOUNT_ELEMENT = "viewAccountElement";
    private static final String VIEW_CHART_OF_ACCOUNTS_ELEMENT = "viewChartOfAccountsElement";

    private AwardHierarchyService awardHierarchyService;

    public Set<String> getEditModes(Document document, Person user, Set<String> currentEditModes) {
        Set<String> editModes = new HashSet<>();

        AwardDocument awardDocument = (AwardDocument) document;

        if (awardDocument.getAward().getAwardId() == null) {
            if (canCreateAward(user.getPrincipalId())) {
                editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
                if (canViewChartOfAccountsElement(awardDocument)) {
                    editModes.add(VIEW_CHART_OF_ACCOUNTS_ELEMENT);
                }
                if (canViewAccountElement(awardDocument)) {
                    editModes.add(VIEW_ACCOUNT_ELEMENT);
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
                editModes.add(ADD_BUDGET);
            }

            if (canExecuteAwardTask(user.getPrincipalId(), awardDocument, TaskName.OPEN_BUDGETS)) {
                editModes.add(OPEN_BUDGETS);
            }

            if (!isCanceled && canExecuteAwardTask(user.getPrincipalId(), awardDocument, TaskName.MODIFY_BUDGET)) {
                editModes.add(MODIFY_AWARD_BUDGET);
            }

            if (canCreateAward(user.getPrincipalId())) {
                editModes.add(Constants.CAN_CREATE_AWARD_KEY);
            }
            if (canCreateAwardAccount(document)) {
                editModes.add(CREATE_AWARD_ACCOUNT);
            }
            if (canViewPostHistory(awardDocument, user.getPrincipalId())) {
                editModes.add(POST_AWARD);
            }
            if (canViewPostHistory(user.getPrincipalId())) {
                editModes.add(POST_TIME_AND_MONEY);
            }
            if (awardHasHierarchyChildren(document)) {
                editModes.add(AWARD_SYNC);
            }
            if (canViewChartOfAccountsElement(awardDocument)) {
                editModes.add(VIEW_CHART_OF_ACCOUNTS_ELEMENT);
            }
            if (canViewAccountElement(awardDocument)) {
                editModes.add(VIEW_ACCOUNT_ELEMENT);
            }
            if (editModes.contains(AuthorizationConstants.EditMode.FULL_ENTRY) ||
                    canMaintainAwardAttachments(awardDocument, user)) {
                editModes.add(CAN_MAINTAIN_AWARD_ATTACHMENTS);
            }
            if (editModes.contains(CAN_MAINTAIN_AWARD_ATTACHMENTS) ||
                    canViewAwardAttachments(awardDocument, user)) {
                editModes.add(CAN_VIEW_AWARD_ATTACHMENTS);
            }
        }

        return editModes;
    }

    public boolean canViewPostHistory(String principalId) {
        return isTimeAndMoneyPostEnabled() && hasPostTimeAndMoneyPermission(principalId);
    }

    public boolean hasPostTimeAndMoneyPermission(String principalId) {
        return getPermissionService().hasPermission(principalId, Constants.KC_SYS, TimeAndMoneyPermissionConstants.POST_TIME_AND_MONEY);
    }

    protected boolean isTimeAndMoneyPostEnabled() {
        return getParameterService().getParameterValueAsBoolean(
                Constants.PARAMETER_TIME_MONEY,
                ParameterConstants.ALL_COMPONENT,
                Constants.TM_POST_ENABLED);
    }

    @Override
    public boolean canInitiate(String documentTypeName, Person user) {
        return canCreateAward(user.getPrincipalId());
    }

    /**
     * This method decides if a user has permissions to create a financial account.
     */
    public boolean canCreateAwardAccount(Document document) {
        AwardDocument awardDocument = (AwardDocument) document;
        Award award = awardDocument.getAward();
        boolean hasPermission = false;
        String status = document.getDocumentHeader().getWorkflowDocument().getStatus().getCode();
        if (status.equalsIgnoreCase(KewApiConstants.ROUTE_HEADER_PROCESSED_CD) ||
            status.equalsIgnoreCase(KewApiConstants.ROUTE_HEADER_FINAL_CD)) {

            if (isFinancialsystemIntegrationOn() || isFinancialRestApiEnabled()) {
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

    public boolean canViewPostHistory(AwardDocument document, String principalId) {
        return isFinancialRestApiEnabled() && document.hasPostAwardPermission(principalId)
                && document.getAward().getAccountNumber() != null;
    }


    protected boolean isFinancialRestApiEnabled() {
        return getParameterService().getParameterValueAsBoolean(
                Constants.PARAMETER_MODULE_AWARD,
                ParameterConstants.ALL_COMPONENT,
                Constants.AWARD_POST_ENABLED);
    }

    protected boolean isFinancialsystemIntegrationOn() {
        return getParameterService().getParameterValueAsBoolean(
                Constants.PARAMETER_MODULE_AWARD,
                ParameterConstants.DOCUMENT_COMPONENT,
                Constants.FIN_SYSTEM_INTEGRATION_ON_OFF_PARAMETER);
    }

    public boolean hasCreateAccountPermission(AwardDocument document) {
        return canExecuteAwardTask(GlobalVariables.getUserSession().getPrincipalId(), document, AwardTaskNames.CREATE_AWARD_ACCOUNT.getAwardTaskName());
    }

    /*
     * This only appears when the integration is ON
     */
    public boolean canViewChartOfAccountsElement(AwardDocument document) {
        return hasCreateAccountPermission(document) && isFinancialsystemIntegrationOn();
    }
    /*
     * This field appears even if the financial integration if OFF
     * but when it is ON, the user needs to have
     * the create account permission to view it.
     */
    public boolean canViewAccountElement(AwardDocument document) {
        boolean hasPermission = true;
        if (isFinancialsystemIntegrationOn()) {
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
        return !isCanceled && canExecuteAwardTask(user.getPrincipalId(), (AwardDocument) document, AwardTaskNames.MODIFY_AWARD.getAwardTaskName())
                    && !isPessimisticLocked((AwardDocument)document);
    }

    protected boolean isPessimisticLocked(AwardDocument document) {
        boolean isLocked = false;
        for (PessimisticLock lock : document.getPessimisticLocks()) {
            // if lock is owned by current user, do not display message for it
            if (!lock.isOwnedByUser(GlobalVariables.getUserSession().getPerson())) {
                isLocked = true;
            }
        }
        return isLocked;
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
        return currentAward.getSequenceNumber() == 1;
    }

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
            Map<String, AwardHierarchyNode> awardHierarchyNodes = new HashMap<>();
            Map<String, AwardHierarchy> awardHierarchyItems;
            awardHierarchyItems = awardHierarchyService.getAwardHierarchy(awardDocument.getAward().getAwardNumber(), new ArrayList<String>());
            AwardHierarchy currentAwardNode = awardHierarchyItems.get(currentAward.getAwardNumber());
            if((currentAwardNode != null && currentAwardNode.isRootNode()) && isCurrentAwardTheFirstVersion(currentAward)) {
                awardHierarchyService.populateAwardHierarchyNodes(awardHierarchyItems, awardHierarchyNodes, currentAward.getAwardNumber(), currentAward.getSequenceNumber().toString());
                canCancel = !doesAwardHierarchyContainFinalChildren(currentAwardNode, awardHierarchyNodes);
            }
        }
        return canCancel;
    }

    @Override
    public boolean canApprove(Document document, Person user) {
        return isEnroute(document) &&  super.canApprove(document, user);
    }

    @Override
    public boolean canDisapprove(Document document, Person user) {
        return canApprove(document, user);
    }

    @Override
    public boolean canBlanketApprove(Document document, Person user) {

        if (areConditionsMetForBlanketApproval(document, user)) {
            if (allowBlanketApproveWhileEnroute()) return canEdit(document);

            // (prior to routing) or to a user with an approval action request
            WorkflowDocument workflowDocument = document.getDocumentHeader().getWorkflowDocument();
            return canRoute(document) || workflowDocument.isApprovalRequested();

        }

        return false;
    }

    private boolean allowBlanketApproveWhileEnroute() {
        // check system parameter - if Y, use default workflow behavior: allow a user with the permission
        // to perform the blanket approve action at any time
        try {
            if (getBlanketApprovalParameter()) {
                return true;
            }
        } catch ( IllegalArgumentException ex ) {
            // do nothing, the parameter does not exist and defaults to "N"
        }
        return false;
    }

    private Boolean getBlanketApprovalParameter() {
        return getParameterService().getParameterValueAsBoolean(KRADConstants.KNS_NAMESPACE, KRADConstants.DetailTypes.DOCUMENT_DETAIL_TYPE,
                KRADConstants.SystemGroupParameterNames.ALLOW_ENROUTE_BLANKET_APPROVE_WITHOUT_APPROVAL_REQUEST_IND);
    }

    private boolean areConditionsMetForBlanketApproval(Document document, Person user) {
        return isInRightStateToApprove(document) && hasPermissionToBlanketApprove(document, user);
    }

    private boolean isInRightStateToApprove(Document document) {
        return !(isFinal(document) || isProcessed (document) || isCanceled(document)) && !((KcTransactionalDocumentBase)document).isViewOnly();
    }

    protected boolean hasPermissionToBlanketApprove(Document document, Person user) {
        WorkflowDocument workflowDocument = document.getDocumentHeader().getWorkflowDocument();
        DocumentRouteHeaderValue routeHeader = getRouteHeaderService().getRouteHeader(workflowDocument.getDocumentId());
        return getDocumentTypePermissionService().canBlanketApprove(user.getPrincipalId(), routeHeader);
    }

    private RouteHeaderService getRouteHeaderService() {
        return KcServiceLocator.getService(RouteHeaderService.class);
    }

    private DocumentTypePermissionService getDocumentTypePermissionService() {
        return KcServiceLocator.getService(DocumentTypePermissionService.class);
    }

    private boolean canCreateAward(String userId) {
        ApplicationTask task = new ApplicationTask(TaskName.CREATE_AWARD);
        return getTaskAuthorizationService().isAuthorized(userId, task);
    }

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
        return (isInRightStateToApprove(document) &&
                        getPermissionService().hasPermission (user.getPrincipalId(), Constants.MODULE_NAMESPACE_AWARD, AwardPermissionConstants.SUBMIT_AWARD.getAwardPermission()));
    }
    @Override
    public boolean canAcknowledge(Document document, Person user) {
        return isProcessed (document) && super.canAcknowledge(document, user);
    }

    protected boolean canMaintainAwardAttachments(AwardDocument document, Person user) {
                return getKcAuthorizationService().hasPermission(user.getPrincipalId(),document,AwardPermissionConstants.CREATE_AWARD.getAwardPermission()) ||
                        getKcAuthorizationService().hasPermission(user.getPrincipalId(),document,AwardPermissionConstants.MAINTAIN_AWARD_ATTACHMENTS.getAwardPermission());
    }

    protected boolean canViewAwardAttachments(AwardDocument document, Person user) {
        return getKcAuthorizationService().hasPermission(user.getPrincipalId(), document, AwardPermissionConstants.VIEW_AWARD_ATTACHMENTS.getAwardPermission());
    }

    private KcAuthorizationService getKcAuthorizationService() {
        return KcServiceLocator.getService(KcAuthorizationService.class);
    }

    protected boolean isProcessed (Document document){
       String status = document.getDocumentHeader().getWorkflowDocument().getStatus().getCode();
       return status.equalsIgnoreCase(KewApiConstants.ROUTE_HEADER_PROCESSED_CD);
   }

    protected boolean isCanceled (Document document){
        String status = document.getDocumentHeader().getWorkflowDocument().getStatus().getCode();
        return status.equalsIgnoreCase(KewApiConstants.ROUTE_HEADER_CANCEL_CD);
    }
}
