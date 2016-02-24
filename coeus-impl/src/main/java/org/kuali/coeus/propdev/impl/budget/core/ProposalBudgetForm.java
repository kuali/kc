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
package org.kuali.coeus.propdev.impl.budget.core;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.core.BudgetContainer;
import org.kuali.coeus.common.budget.framework.income.BudgetPeriodIncomeTotal;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetJustificationWrapper;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.budget.modular.BudgetModularSummary;
import org.kuali.coeus.propdev.impl.budget.nonpersonnel.AddProjectBudgetLineItemHelper;
import org.kuali.coeus.propdev.impl.budget.person.AddProjectPersonnelHelper;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.validation.Auditable;
import org.kuali.coeus.sys.impl.validation.DataValidationItem;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
import org.kuali.rice.krad.uif.component.Component;
import org.kuali.rice.krad.uif.element.Action;
import org.kuali.rice.krad.uif.element.ToggleMenu;
import org.kuali.rice.krad.web.form.UifFormBase;

public class ProposalBudgetForm extends UifFormBase implements BudgetContainer, Auditable, SelectableBudget {

	private ProposalDevelopmentBudgetExt budget;
	private String defaultBudgetPeriodWarningMessage;
    private Map<String,List<String>> editableBudgetLineItems;
    private AddProjectPersonnelHelper addProjectPersonnelHelper;
    private AddBudgetDto addBudgetDto;
    private AddBudgetDto copyBudgetDto;
    private AddProjectBudgetLineItemHelper addProjectBudgetLineItemHelper;
    private BudgetJustificationWrapper budgetJustificationWrapper;
    private BudgetModularSummary budgetModularSummary;
    private ProposalDevelopmentBudgetExt selectedBudget;
    private boolean auditActivated;
    private List<DataValidationItem> dataValidationItems;
    private boolean viewOnly = false;
    private List<DevelopmentProposal> hierarchyDevelopmentProposals;
    private boolean submitBudgetIndicator;

    public void initialize() {
    	editableBudgetLineItems = new HashMap<String,List<String>>();
    	addProjectPersonnelHelper = new AddProjectPersonnelHelper();
    	addProjectBudgetLineItemHelper = new AddProjectBudgetLineItemHelper();
        budgetJustificationWrapper = new BudgetJustificationWrapper (budget.getBudgetJustification());
        dataValidationItems = new ArrayList<DataValidationItem>();
    }

    public ProposalDevelopmentBudgetExt getBudget() {
		return budget;
	}
	public void setBudget(ProposalDevelopmentBudgetExt budget) {
		this.budget = budget;
	}
	
	public DevelopmentProposal getDevelopmentProposal() {
		return getBudget().getDevelopmentProposal();
	}

    @Override
    public Document getDocument() {
        return getDevelopmentProposal().getProposalDocument();
    }

    public List<Action> getOrderedNavigationActions() {
        List<Action> actions = new ArrayList<Action>();
        addAllActions(actions, view.getNavigation().getItems());
        return actions;
    }
	
    protected void addAllActions(List<Action> actionList, List<? extends Component> components) {
        for (Component component : components) {
            if (component instanceof ToggleMenu) {
                addAllActions(actionList, ((ToggleMenu) component).getMenuItems());
            } else if (component instanceof Action) {
                Action action =(Action) component;
                if (!StringUtils.equals(action.getId(),ProposalBudgetConstants.KradConstants.RETURN_TO_PROPOSAL_LINK)){
                    actionList.add(action);
                }
            }
        }
    }
    
	public String getDefaultBudgetPeriodWarningMessage() {
		return defaultBudgetPeriodWarningMessage;
	}
	public void setDefaultBudgetPeriodWarningMessage(String defaultBudgetPeriodWarningMessage) {
		this.defaultBudgetPeriodWarningMessage = defaultBudgetPeriodWarningMessage;
	}

	public Map<String, List<String>> getEditableBudgetLineItems() {
		return editableBudgetLineItems;
	}

	public void setEditableBudgetLineItems(
			Map<String, List<String>> editableBudgetLineItems) {
		this.editableBudgetLineItems = editableBudgetLineItems;
	}

	public AddProjectPersonnelHelper getAddProjectPersonnelHelper() {
		return addProjectPersonnelHelper;
	}

	public void setAddProjectPersonnelHelper(
			AddProjectPersonnelHelper addProjectPersonnelHelper) {
		this.addProjectPersonnelHelper = addProjectPersonnelHelper;
	}

	public AddBudgetDto getAddBudgetDto() {
		return addBudgetDto;
	}

	public void setAddBudgetDto(AddBudgetDto addBudgetDto) {
		this.addBudgetDto = addBudgetDto;
	}

	public AddBudgetDto getCopyBudgetDto() {
		return copyBudgetDto;
	}

	public void setCopyBudgetDto(AddBudgetDto copyBudgetDto) {
		this.copyBudgetDto = copyBudgetDto;
	}

    public List<BudgetPeriodIncomeTotal> getBudgetPeriodIncomeTotalSummary() {
        List<BudgetPeriodIncomeTotal> budgetPeriodIncomeTotalSummary = new ArrayList<BudgetPeriodIncomeTotal>();
        Map <Integer,ScaleTwoDecimal> periodTotalMap = getBudget().mapProjectIncomeTotalsToBudgetPeriodNumbers();
        for (Map.Entry<Integer,ScaleTwoDecimal> entry : periodTotalMap.entrySet()){
            budgetPeriodIncomeTotalSummary.add (new BudgetPeriodIncomeTotal((Integer)entry.getKey(),(ScaleTwoDecimal)entry.getValue()));
        }
        return budgetPeriodIncomeTotalSummary;
    }

    public Date getLineItemStartDate(BudgetLineItem lineItem) {
        if(CollectionUtils.isNotEmpty(lineItem.getBudgetPersonnelDetailsList())) {
            List<BudgetPersonnelDetails> personnelDetailsWithDifferentDates = lineItem.getBudgetPersonnelDetailsList().stream().filter(
                    budgetPersonnelDetails -> budgetPersonnelDetails.getStartDate() == null ||
                            lineItem.getBudgetPersonnelDetailsList().get(0).getStartDate() == null ||
                            budgetPersonnelDetails.getStartDate().compareTo(lineItem.getBudgetPersonnelDetailsList().get(0).getStartDate()) != 0).
                    collect(Collectors.toList());
            return personnelDetailsWithDifferentDates.isEmpty() ? lineItem.getBudgetPersonnelDetails(0).getStartDate() : lineItem.getStartDate();
        }
        return lineItem.getStartDate();
    }

    public Date getLineItemEndDate(BudgetLineItem lineItem) {
        if(CollectionUtils.isNotEmpty(lineItem.getBudgetPersonnelDetailsList())) {
            List<BudgetPersonnelDetails> personnelDetailsWithDifferentDates = lineItem.getBudgetPersonnelDetailsList().stream().filter(
                    budgetPersonnelDetails -> budgetPersonnelDetails.getEndDate() == null ||
                            lineItem.getBudgetPersonnelDetailsList().get(0).getEndDate() == null ||
                            budgetPersonnelDetails.getEndDate().compareTo(lineItem.getBudgetPersonnelDetailsList().get(0).getEndDate()) != 0).
                    collect(Collectors.toList());
            return personnelDetailsWithDifferentDates.isEmpty() ? lineItem.getBudgetPersonnelDetails(0).getEndDate() : lineItem.getEndDate();
        }
        return lineItem.getEndDate();
    }

    public boolean isUnrecoveredFandAEditFormVisible() {
        return budget != null && budget.isUnrecoveredFandAApplicable() && budget.isUnrecoveredFandAAvailable();
    }

    public boolean isCostSharingEditFormVisible() {
        return budget != null && budget.isCostSharingApplicable() && budget.isCostSharingAvailable();
    }

	public AddProjectBudgetLineItemHelper getAddProjectBudgetLineItemHelper() {
		return addProjectBudgetLineItemHelper;
	}

	public void setAddProjectBudgetLineItemHelper(
			AddProjectBudgetLineItemHelper addProjectBudgetLineItemHelper) {
		this.addProjectBudgetLineItemHelper = addProjectBudgetLineItemHelper;
	}

    public BudgetJustificationWrapper getBudgetJustificationWrapper() {
        return budgetJustificationWrapper;
    }

    public void setBudgetJustificationWrapper(BudgetJustificationWrapper budgetJustificationWrapper) {
        this.budgetJustificationWrapper = budgetJustificationWrapper;
    }

    public void setBudgetModularSummary(BudgetModularSummary budgetModularSummary) {
        this.budgetModularSummary = budgetModularSummary;
    }

    public BudgetModularSummary getBudgetModularSummary() {return budgetModularSummary;}

    public ProposalDevelopmentBudgetExt getSelectedBudget() {
        return selectedBudget;
    }

    public void setSelectedBudget(ProposalDevelopmentBudgetExt selectedBudget) {
        this.selectedBudget = selectedBudget;
    }

    @Override
    public boolean isAuditActivated() {
		return auditActivated;
	}

    @Override
	public void setAuditActivated(boolean auditActivated) {
		this.auditActivated = auditActivated;
	}

	public List<DataValidationItem> getDataValidationItems() {
		return dataValidationItems;
	}

	public void setDataValidationItems(List<DataValidationItem> dataValidationItems) {
		this.dataValidationItems = dataValidationItems;
	}

	public boolean isViewOnly() {
		return viewOnly;
	}

	public void setViewOnly(boolean viewOnly) {
		this.viewOnly = viewOnly;
	}


    public List<DevelopmentProposal> getHierarchyDevelopmentProposals() {
        return hierarchyDevelopmentProposals;
    }

    public void setHierarchyDevelopmentProposals(List<DevelopmentProposal> hierarchyDevelopmentProposals) {
        this.hierarchyDevelopmentProposals = hierarchyDevelopmentProposals;
    }

    public boolean isSubmitBudgetIndicator() {
        return submitBudgetIndicator;
    }

    public void setSubmitBudgetIndicator(boolean submitBudgetIndicator) {
        this.submitBudgetIndicator = submitBudgetIndicator;
    }

    public boolean isProposalDevelopmentDocumentLocked() {
        getBudget().getDevelopmentProposal().getProposalDocument().refreshPessimisticLocks();
        List<PessimisticLock> proposalDevelopmentLocks = getBudget().getDevelopmentProposal().getProposalDocument().getPessimisticLocks();
        Person user = getGlobalVariableService().getUserSession().getPerson();
        for (PessimisticLock lock : proposalDevelopmentLocks) {
            String lockRegion = lock.getLockDescriptor() != null ? StringUtils.split(lock.getLockDescriptor(), "-")[1] : null;
            if (!lock.isOwnedByUser(user) && StringUtils.equals(lockRegion, KraAuthorizationConstants.LOCK_DESCRIPTOR_PROPOSAL)) {
                return true;
            }
        }
        return false;
    }

    public GlobalVariableService getGlobalVariableService() {
        return KcServiceLocator.getService(GlobalVariableService.class);
    }

    public boolean isCanEdit() {
        if (isCanEditView() == null) {
            return false;
        }
        return isCanEditView();
    }

}
