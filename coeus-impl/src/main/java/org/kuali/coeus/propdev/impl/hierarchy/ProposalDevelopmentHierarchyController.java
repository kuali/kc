package org.kuali.coeus.propdev.impl.hierarchy;

import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentControllerBase;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProposalDevelopmentHierarchyController extends ProposalDevelopmentControllerBase {

    @Autowired
    @Qualifier("proposalHierarchyService")
    ProposalHierarchyService proposalHierarchyService;

    @Transactional
    @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=createHierarchy"})
    public ModelAndView createHierarchy(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
        DevelopmentProposal initialChildProposal = form.getProposalDevelopmentDocument().getDevelopmentProposal();
        List<ProposalHierarchyErrorWarningDto> errors = getProposalHierarchyService().validateChildCandidate(initialChildProposal);

        if (!displayErrors(errors)) {
            String userId = getGlobalVariableService().getUserSession().getPrincipalId();
            String hierarchyProposalNumber = getProposalHierarchyService().createHierarchy(initialChildProposal, userId);
            displayMessage(ProposalHierarchyKeyConstants.MESSAGE_CREATE_SUCCESS, new String[]{hierarchyProposalNumber});
        }
        return getModelAndViewService().getModelAndView(form);
    }


    @Transactional @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=syncAllHierarchy")
    public ModelAndView syncAllHierarchy(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
        ProposalDevelopmentDocument hierarchyProposalDoc = form.getProposalDevelopmentDocument();
        List<ProposalHierarchyErrorWarningDto> errors = getProposalHierarchyService().validateParent(hierarchyProposalDoc.getDevelopmentProposal());
        if (!displayErrors(errors)) {
            getProposalHierarchyService().synchronizeAllChildren(hierarchyProposalDoc.getDevelopmentProposal());
            displayMessage(ProposalHierarchyKeyConstants.MESSAGE_SYNC_SUCCESS, new String[]{});
        }
        return getModelAndViewService().getModelAndView(form);
    }

    /*
    Link this unlinked child to parent proposal via search
     */
    @Transactional @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=linkToHierarchy")
    public ModelAndView linkToHierarchy(@RequestParam("hierarchyProposalNumber") String hierarchyProposalNumber, 
    		@RequestParam("hierarchyBudgetTypeCode") String hierarchyBudgetTypeCode, 
    		@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {

        DevelopmentProposal hierarchyProposal = getProposalHierarchyService().getDevelopmentProposal(hierarchyProposalNumber);
        DevelopmentProposal newChildProposal = form.getProposalDevelopmentDocument().getDevelopmentProposal();
        ProposalDevelopmentBudgetExt childBudget = getProposalHierarchyService().getSyncableBudget(newChildProposal);
        if(childBudget == null){
            getGlobalVariableService().getMessageMap().putError(ProposalHierarchyKeyConstants.FIELD_CHILD_NUMBER,ProposalHierarchyKeyConstants.ERROR_LINK_NO_BUDGET_VERSION);
        }else{
	        List<ProposalHierarchyErrorWarningDto> errors = getProposalHierarchyService().validateLinkToHierarchy(hierarchyProposal, newChildProposal);
	        if (!displayErrors(errors)) {
	            getProposalHierarchyService().linkToHierarchy(hierarchyProposal, newChildProposal, hierarchyBudgetTypeCode);
                displayMessage(ProposalHierarchyKeyConstants.MESSAGE_LINK_SUCCESS, new String[]{newChildProposal.getProposalNumber(), hierarchyProposal.getProposalNumber()});
            }
        }
        return getModelAndViewService().getModelAndView(form);
    }



    /*
    Link a child proposal to this parent
     */
    @Transactional @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=linkChildToHierarchy")
    public ModelAndView linkChildToHierarchy(@RequestParam("hierarchyProposalNumber") String hierarchyProposalNumber,
    		@RequestParam("hierarchyBudgetTypeCode") String hierarchyBudgetTypeCode,
    		@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
        DevelopmentProposal hierarchyProposal = form.getProposalDevelopmentDocument().getDevelopmentProposal();
        DevelopmentProposal newChildProposal = getProposalHierarchyService().getDevelopmentProposal(hierarchyProposalNumber);

        List<ProposalHierarchyErrorWarningDto> errors = getProposalHierarchyService().validateParent(hierarchyProposal);
        errors.addAll(getProposalHierarchyService().validateChildCandidate(newChildProposal));
        errors.addAll(getProposalHierarchyService().validateChildCandidateForHierarchy(hierarchyProposal, newChildProposal, true));
        errors.addAll(getProposalHierarchyService().validateSponsor(newChildProposal, hierarchyProposal));
        errors.addAll(getProposalHierarchyService().validateIsAggregatorOnChild(newChildProposal));

        if (!displayErrors(errors)) {
            getProposalHierarchyService().linkToHierarchy(hierarchyProposal, newChildProposal, hierarchyBudgetTypeCode);
            displayMessage(ProposalHierarchyKeyConstants.MESSAGE_LINK_SUCCESS, new String[]{hierarchyProposal.getProposalNumber(), newChildProposal.getProposalNumber()});
        }

        return getModelAndViewService().getModelAndView(form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=hierarchyActionCanceled")
    public ModelAndView hierarchyActionCanceled(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
        return getNavigationControllerService().back(form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=syncToHierarchyParent")
    public ModelAndView syncToHierarchyParent(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
        DevelopmentProposal childProposal = form.getProposalDevelopmentDocument().getDevelopmentProposal();
        DevelopmentProposal hierarchy = getProposalHierarchyService().getDevelopmentProposal(childProposal.getHierarchyParentProposalNumber());
        List<ProposalHierarchyErrorWarningDto> errors = getProposalHierarchyService().validateChildForSync(childProposal, hierarchy, false);
        if (!displayErrors(errors)) {
            getProposalHierarchyService().synchronizeChild(childProposal);
            displayMessage(ProposalHierarchyKeyConstants.MESSAGE_SYNC_SUCCESS, new String[]{});
        }

        return getModelAndViewService().getModelAndView(form);
    }

    @Transactional @RequestMapping(value ="/proposalDevelopment", params = "methodToCall=removeFromHierarchy")
    public ModelAndView removeFromHierarchy(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
        DevelopmentProposal childProposal = form.getProposalDevelopmentDocument().getDevelopmentProposal();
        List<ProposalHierarchyErrorWarningDto> errors = getProposalHierarchyService().validateChildForRemoval(childProposal);

        if (!displayErrors(errors)) {
            getProposalHierarchyService().removeFromHierarchy(childProposal);
            displayMessage(ProposalHierarchyKeyConstants.MESSAGE_REMOVE_SUCCESS);
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
