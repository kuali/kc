package org.kuali.coeus.propdev.impl.budget.core;

import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyErrorWarningDto;
import org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyKeyConstants;
import org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProposalBudgetHierarchyController extends ProposalBudgetControllerBase {

    @Autowired
    @Qualifier("proposalHierarchyService")
    ProposalHierarchyService proposalHierarchyService;

    @Transactional @RequestMapping(value = "/proposalBudget", params = "methodToCall=syncAllBudgets")
    public ModelAndView syncAllBudgets(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
        DevelopmentProposal hierarchyProposal = form.getDevelopmentProposal();
        List<ProposalHierarchyErrorWarningDto> errors = getProposalHierarchyService().validateParent(hierarchyProposal);
        if (!displayErrors(errors)) {
            getProposalHierarchyService().synchronizeAllChildrenBudgets(hierarchyProposal);
            displayMessage(ProposalHierarchyKeyConstants.MESSAGE_SYNC_SUCCESS, new String[]{});
        }
        return getModelAndViewService().getModelAndView(form);
    }

    @Transactional @RequestMapping(value = "/proposalBudget", params = "methodToCall=syncBudget")
    public ModelAndView syncBudget(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
        DevelopmentProposal childProposal = form.getDevelopmentProposal();
        DevelopmentProposal hierarchy = getProposalHierarchyService().getDevelopmentProposal(childProposal.getHierarchyParentProposalNumber());
        List<ProposalHierarchyErrorWarningDto> errors = getProposalHierarchyService().validateChildForSync(childProposal, hierarchy, false);
        if (!displayErrors(errors)) {
            getProposalHierarchyService().synchronizeChildProposalBudget(form.getBudget(), childProposal);
            displayMessage(ProposalHierarchyKeyConstants.MESSAGE_SYNC_SUCCESS, new String[]{});
        }

        return getModelAndViewService().getModelAndView(form);
    }

    protected void displayMessage(String messageKey, String... errorParameters) {
        getGlobalVariableService().getMessageMap().putInfo(ProposalHierarchyKeyConstants.FIELD_GENERIC, messageKey, errorParameters);
    }

    protected boolean displayErrors(List<ProposalHierarchyErrorWarningDto> errors) {
        int severeErrors = 0;
        for (ProposalHierarchyErrorWarningDto error : errors) {
            severeErrors += error.isSevere() ? 1 : 0;
            if (error.isSevere()) {
                getGlobalVariableService().getMessageMap().putError(ProposalHierarchyKeyConstants.FIELD_GENERIC, error.getErrorKey(), error.getErrorParameters());
            } else {
                getGlobalVariableService().getMessageMap().putWarning(ProposalHierarchyKeyConstants.FIELD_GENERIC, error.getErrorKey(), error.getErrorParameters());
            }
        }
        return severeErrors > 0;
    }

    public ProposalHierarchyService getProposalHierarchyService() {
        return proposalHierarchyService;
    }

    public void setProposalHierarchyService(ProposalHierarchyService proposalHierarchyService) {
        this.proposalHierarchyService = proposalHierarchyService;
    }
}
