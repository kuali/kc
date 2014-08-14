package org.kuali.coeus.propdev.impl.budget.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.budget.person.AddProjectPersonnelHelper;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.rice.krad.uif.component.Component;
import org.kuali.rice.krad.uif.element.Action;
import org.kuali.rice.krad.uif.element.ToggleMenu;
import org.kuali.rice.krad.web.form.TransactionalDocumentFormBase;
import org.kuali.rice.krad.web.form.UifFormBase;

public class ProposalBudgetForm extends UifFormBase {

	private ProposalDevelopmentBudgetExt budget;
	private String defaultBudgetPeriodWarningMessage;
    private Map<String,List<String>> editableBudgetLineItems;
    private AddProjectPersonnelHelper addProjectPersonnelHelper;
    private AddBudgetDto addBudgetDto;
    private AddBudgetDto copyBudgetDto;

    public void initialize() {
    	editableBudgetLineItems = new HashMap<String,List<String>>();
    	addProjectPersonnelHelper = new AddProjectPersonnelHelper();
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
                actionList.add((Action) component);
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
    

}
