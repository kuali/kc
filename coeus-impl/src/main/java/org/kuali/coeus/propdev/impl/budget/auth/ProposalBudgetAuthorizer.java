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
package org.kuali.coeus.propdev.impl.budget.auth;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetParentDocument;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetConstants.AuthConstants;
import org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetForm;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.lock.ProposalBudgetLockService;
import org.kuali.coeus.sys.framework.workflow.KcDocumentRejectionService;
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
import org.kuali.rice.krad.uif.view.View;
import org.kuali.rice.krad.uif.view.ViewAuthorizerBase;
import org.kuali.rice.krad.uif.view.ViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * This class is the Budget Document Authorizer.  It determines the edit modes and
 * document actions for all budget documents.
 */
@Component("proposalBudgetAuthorizer")
public class ProposalBudgetAuthorizer extends ViewAuthorizerBase {
    
	@Autowired
	@Qualifier("parameterService")
	private ParameterService parameterService;

    @Autowired
    @Qualifier("kcAuthorizationService")
    private KcAuthorizationService kcAuthorizationService;

    @Autowired
    @Qualifier("kcDocumentRejectionService")
    private KcDocumentRejectionService kcDocumentRejectionService;

    @Autowired
    @Qualifier("kcWorkflowService")
    private KcWorkflowService kcWorkflowService;

    @Autowired
    @Qualifier("proposalBudgetLockService")
    private ProposalBudgetLockService proposalBudgetLockService;

    @Override
    public Set<String> getEditModes(View view, ViewModel model, Person user, Set<String> editModes) {
    	ProposalBudgetForm form = (ProposalBudgetForm) model;
        ProposalDevelopmentBudgetExt budget = form.getBudget();
        ProposalDevelopmentDocument parentDocument = (ProposalDevelopmentDocument) budget.getBudgetParent().getDocument();

        if (isAuthorizedToAddBudget(parentDocument, user)) {
        	editModes.add(AuthConstants.ADD_BUDGET_EDIT_MODE);
        }
        
        if (isAuthorizedToModifyBudget(budget, user)) {
        	editModes.add(AuthConstants.VIEW_BUDGET_EDIT_MODE);
        	if(!form.isViewOnly()) {
            	editModes.add(AuthConstants.CHANGE_COMPLETE_STATUS);
        	}
        	if (!isBudgetComplete(budget)) {
        		if (!budget.getDevelopmentProposal().isParent()) {
		            editModes.add(AuthConstants.MODIFY_BUDGET_EDIT_MODE);
		            if (isAuthorizedToModifyRates(budget, user)) {
		                editModes.add(AuthConstants.MODIFY_RATES_EDIT_MODE);
		            }
		            setPermissions(user, parentDocument, editModes);
        		} else {
        			editModes.add(AuthConstants.MODIFY_HIERARCHY_PARENT_BUDGET);
        		}
        	}
        } else if (isAuthorizedToViewBudget(budget, user)) {
            editModes.add(AuthorizationConstants.EditMode.VIEW_ONLY);
            editModes.add(AuthConstants.VIEW_BUDGET_EDIT_MODE);
            
            setPermissions(user, parentDocument, editModes);
        }
        else {
            editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
        }
        
        if (isAuthorizedToMaintainProposalHierarchy(parentDocument, user)) {
            editModes.add(AuthConstants.MAINTAIN_PROPOSAL_HIERARCHY);
        }
        
        return editModes;
    }
    
    /**
     * Set the permissions to be used during the creation of the web pages.  
     * The JSP files can access the editModeMap (editingMode) to determine what
     * to display to the user.  For example, a JSP file may contain the following:
     * 
     *     <kra:section permission="modifyProposal">
     *         .
     *         .
     *         .
     *     </kra:section>
     * 
     * In the above example, the contents are only rendered if the user is allowed
     * to modify the proposal.  Note that permissions are always signified as 
     * either TRUE or FALSE.
     * 
     * @param user the user
     * @param doc the Proposal Development Document
     * @param editModes the edit mode map
     */
    protected void setPermissions(Person user, BudgetParentDocument doc, Set<String> editModes) {

        if (isAuthorizedToAddBudget(doc, user)) {
            editModes.add(AuthConstants.ADD_BUDGET_EDIT_MODE);
        }
        
        if (isAuthorizedToPrintProposal(doc, user)) {
            editModes.add(AuthConstants.PRINT_EDIT_MODE);
        }
    }

    @Override
    public boolean canOpenView(View view, ViewModel model, Person user) {
        ProposalBudgetForm form = (ProposalBudgetForm) model;
        ProposalDevelopmentBudgetExt budget = form.getBudget();

        return canOpen(budget, user);
    }

    public boolean canOpen(ProposalDevelopmentBudgetExt budget, Person user) {
        return isAuthorizedToViewBudget(budget, user);
    }
    
    public boolean canEdit(ProposalDevelopmentBudgetExt budget, Person user) {
        return isAuthorizedToModifyBudget(budget, user);
    }
    
    public boolean canSave(ProposalDevelopmentBudgetExt budget, Person user) {
        return canEdit(budget, user);
    }
    
    public boolean canReload(ProposalDevelopmentBudgetExt budget, Person user) {
        return canEdit(budget, user);
    }

    public boolean canModifyBudget(ProposalDevelopmentBudgetExt budget, Person user) {
        return isAuthorizedToModifyBudget(budget, user) && !isBudgetComplete(budget);
    }

    /**
     * Is the Budget in the final state?
     */
    protected boolean isBudgetComplete(ProposalDevelopmentBudgetExt budget) {
		String budgetStatusCompleteCode = getParameterService().getParameterValueAsString(Budget.class, Constants.BUDGET_STATUS_COMPLETE_CODE);
		return StringUtils.equals(budgetStatusCompleteCode, budget.getBudgetStatus());
    }

    protected boolean isAuthorizedToModifyRates(ProposalDevelopmentBudgetExt budget, Person user) {
        ProposalDevelopmentDocument pdDocument = (ProposalDevelopmentDocument)budget.getBudgetParent().getDocument();
        boolean rejectedDocument = getKcDocumentRejectionService().isDocumentOnInitialNode(pdDocument.getDocumentHeader().getWorkflowDocument());

        return (!getKcWorkflowService().isInWorkflow(pdDocument) || rejectedDocument) &&
                getKcAuthorizationService().hasPermission(user.getPrincipalId(), pdDocument, PermissionConstants.MODIFY_PROPOSAL_RATES) 
                && !pdDocument.getDevelopmentProposal().getSubmitFlag();
    }

    public boolean isAuthorizedToViewBudget(ProposalDevelopmentBudgetExt budget, Person user) {
        ProposalDevelopmentDocument doc = (ProposalDevelopmentDocument) budget.getBudgetParent().getDocument();
        return getKcAuthorizationService().hasPermission(user.getPrincipalId(), doc, PermissionConstants.VIEW_BUDGET)
            || getKcAuthorizationService().hasPermission(user.getPrincipalId(), doc, PermissionConstants.MODIFY_BUDGET)
            || kcWorkflowService.hasWorkflowPermission(user.getPrincipalId(), doc);
    }

    protected boolean isAuthorizedToMaintainProposalHierarchy(Document document, Person user) {
        final ProposalDevelopmentDocument pdDocument = ((ProposalDevelopmentDocument) document);
        return !pdDocument.isViewOnly() && getKcAuthorizationService().hasPermission(user.getPrincipalId(), pdDocument, PermissionConstants.MAINTAIN_PROPOSAL_HIERARCHY);
    }

    protected boolean isAuthorizedToModifyBudget(ProposalDevelopmentBudgetExt budget, Person user) {
        ProposalDevelopmentDocument pdDocument = (ProposalDevelopmentDocument)budget.getBudgetParent().getDocument();
        boolean rejectedDocument = getKcDocumentRejectionService().isDocumentOnInitialNode(pdDocument.getDocumentHeader().getWorkflowDocument());

        return (!getKcWorkflowService().isInWorkflow(pdDocument) || rejectedDocument) &&
                getKcAuthorizationService().hasPermission(user.getPrincipalId(), pdDocument, PermissionConstants.MODIFY_BUDGET) 
                && !pdDocument.getDevelopmentProposal().getSubmitFlag() && userHasLockOnBudget(budget,user);
    }

    protected boolean userHasLockOnBudget(ProposalDevelopmentBudgetExt budget, Person user) {
        ProposalDevelopmentDocument document = budget.getDevelopmentProposal().getProposalDocument();
        for (PessimisticLock lock : document.getPessimisticLocks()) {
            if (lock.isOwnedByUser(user) && getProposalBudgetLockService().doesBudgetVersionMatchDescriptor(lock.getLockDescriptor(),budget.getBudgetVersionNumber())) {
                return true;
            }
        }
        return false;
    }

    protected boolean isAuthorizedToAddBudget(Document document, Person user) {
        final ProposalDevelopmentDocument pdDocument = ((ProposalDevelopmentDocument) document);

        boolean hasPermission = false;

        boolean rejectedDocument = getKcDocumentRejectionService().isDocumentOnInitialNode(pdDocument.getDocumentHeader().getWorkflowDocument());

        if ((!getKcWorkflowService().isInWorkflow(pdDocument) || rejectedDocument) && !pdDocument.isViewOnly() && !pdDocument.getDevelopmentProposal().getSubmitFlag() && !pdDocument.getDevelopmentProposal().isParent()) {
            hasPermission = getKcAuthorizationService().hasPermission(user.getPrincipalId(), pdDocument, PermissionConstants.MODIFY_BUDGET);
        }
        return hasPermission;
    }

    protected boolean isAuthorizedToPrintProposal(Document document, Person user) {
        final BudgetParentDocument bpDocument = ((BudgetParentDocument) document);
        return getKcAuthorizationService().hasPermission(user.getPrincipalId(), bpDocument, PermissionConstants.PRINT_PROPOSAL);
    }

	public ParameterService getParameterService() {
		return parameterService;
	}

	public void setParameterService(ParameterService parameterService) {
		this.parameterService = parameterService;
	}

    public KcAuthorizationService getKcAuthorizationService() {
        return kcAuthorizationService;
    }

    public void setKcAuthorizationService(KcAuthorizationService kcAuthorizationService) {
        this.kcAuthorizationService = kcAuthorizationService;
    }

    public KcDocumentRejectionService getKcDocumentRejectionService() {
        return kcDocumentRejectionService;
    }

    public void setKcDocumentRejectionService(KcDocumentRejectionService kcDocumentRejectionService) {
        this.kcDocumentRejectionService = kcDocumentRejectionService;
    }

    public KcWorkflowService getKcWorkflowService() {
        return kcWorkflowService;
    }

    public void setKcWorkflowService(KcWorkflowService kcWorkflowService) {
        this.kcWorkflowService = kcWorkflowService;
    }

    public ProposalBudgetLockService getProposalBudgetLockService() {
        return proposalBudgetLockService;
    }

    public void setProposalBudgetLockService(ProposalBudgetLockService proposalBudgetLockService) {
        this.proposalBudgetLockService = proposalBudgetLockService;
    }
}
