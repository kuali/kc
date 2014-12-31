package org.kuali.coeus.propdev.impl.budget.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetContainer;
import org.kuali.coeus.common.budget.framework.income.BudgetPeriodIncomeTotal;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetJustificationWrapper;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.budget.modular.BudgetModularSummary;
import org.kuali.coeus.propdev.impl.budget.nonpersonnel.AddProjectBudgetLineItemHelper;
import org.kuali.coeus.propdev.impl.budget.person.AddProjectPersonnelHelper;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.validation.Auditable;
import org.kuali.coeus.sys.impl.validation.DataValidationItem;
import org.kuali.rice.krad.document.Document;
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

    public int findIndexOfPageId(List<Action> actions) {
        return findIndexOfPageId(actions, getPageId());
    }
    
    public int findIndexOfPageId(List<Action> actions, String pageId) {
        for (int i = 0, len = actions.size(); i < len; i++) {
            Action action = actions.get(i);
            if (StringUtils.equals(pageId, action.getNavigateToPageId())) {
                return i;
            }
        }
        return 0;
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

}
