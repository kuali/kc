package org.kuali.coeus.propdev.impl.budget.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.rice.krad.uif.component.Component;
import org.kuali.rice.krad.uif.element.Action;
import org.kuali.rice.krad.uif.element.ToggleMenu;
import org.kuali.rice.krad.web.form.UifFormBase;

public class ProposalBudgetForm extends UifFormBase {

	private ProposalDevelopmentBudgetExt budget;
	private String defaultBudgetPeriodWarningMessage;

	public ProposalDevelopmentBudgetExt getBudget() {
		return budget;
	}
	public void setBudget(ProposalDevelopmentBudgetExt budget) {
		this.budget = budget;
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

}
