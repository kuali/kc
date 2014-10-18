package org.kuali.coeus.propdev.impl.basic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.kuali.coeus.propdev.impl.core.*;
import org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyErrorWarningDto;
import org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyKeyConstants;
import org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyService;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
import org.kuali.rice.krad.exception.AuthorizationException;
import org.kuali.rice.krad.service.PessimisticLockService;
import org.kuali.rice.krad.uif.field.AttributeQueryResult;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.web.form.DialogResponse;
import org.kuali.rice.krad.web.form.DocumentFormBase;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Qualifier;

@Controller
public class ProposalDevelopmentCoreController extends ProposalDevelopmentControllerBase {

    private static final Logger LOG = Logger.getLogger(ProposalDevelopmentCoreController.class);
	@Autowired
	@Qualifier("proposalHierarchyService")
	ProposalHierarchyService proposalHierarchyService;
    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    @Autowired
    @Qualifier("pessimisticLockService")
    private PessimisticLockService pessimisticLockService;

    public ProposalHierarchyService getProposalHierarchyService() {
		if (proposalHierarchyService == null) {
			proposalHierarchyService = KcServiceLocator.getService(ProposalHierarchyService.class);
		}
		return proposalHierarchyService;
	}

	public void setProposalHierarchyService(
			ProposalHierarchyService proposalHierarchyService) {
		this.proposalHierarchyService = proposalHierarchyService;
	}

    public GlobalVariableService getGlobalVariableService() {
        if (globalVariableService == null) {
            globalVariableService = KcServiceLocator.getService(GlobalVariableService.class);
        }
        return globalVariableService;
    }

    public PessimisticLockService getPessimisticLockService() {
        return pessimisticLockService;
    }

    public void setPessimisticLockService(PessimisticLockService pessimisticLockService) {
        this.pessimisticLockService = pessimisticLockService;
    }

    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=defaultMapping")
	public ModelAndView defaultMapping(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		return getTransactionalDocumentControllerService().start(form);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=start")
	public ModelAndView start(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return getTransactionalDocumentControllerService().start(form);
	}

	public void checkViewAuthorization(@ModelAttribute("KualiForm") DocumentFormBase form, String methodToCall) throws AuthorizationException {
		getTransactionalDocumentControllerService().checkViewAuthorization(form);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=reload")
	public ModelAndView reload(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return getTransactionalDocumentControllerService().reload(form);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=sessionTimeout")
	public ModelAndView sessionTimeout(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		return getTransactionalDocumentControllerService().sessionTimeout(form);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=addLine")
	public ModelAndView addLine(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request, HttpServletResponse response) throws Exception {
			return getCollectionControllerService().addLine(form); 
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=complete")
	public ModelAndView complete(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return getTransactionalDocumentControllerService().complete(form);
	}

	@RequestMapping(value ="/proposalDevelopment", params = "methodToCall=addBlankLine")
	public ModelAndView addBlankLine(@ModelAttribute("KualiForm") UifFormBase uifForm, HttpServletRequest request,
			HttpServletResponse response) {
		return getCollectionControllerService().addBlankLine(uifForm);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveLine")
	public ModelAndView saveLine(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return getCollectionControllerService().saveLine(form);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=deleteLine")
	public ModelAndView deleteLine(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		return getCollectionControllerService().deleteLine(form);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=back")
	public ModelAndView back(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return getNavigationControllerService().back(form);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=returnToPrevious")
	public ModelAndView returnToPrevious(@ModelAttribute("KualiForm") UifFormBase form) {
		return getNavigationControllerService().returnToPrevious(form);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=returnToHub")
	public ModelAndView returnToHub(@ModelAttribute("KualiForm") UifFormBase form) {
		return getNavigationControllerService().returnToHub(form);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=returnToHistory")
	public ModelAndView returnToHistory(@ModelAttribute("KualiForm") UifFormBase form, boolean homeFlag) {
		return getNavigationControllerService().returnToHistory(form, false, homeFlag, false);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=refresh")
	public ModelAndView refresh(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return getRefreshControllerService().refresh(form);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=performLookup")
	public ModelAndView performLookup(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		return getQueryControllerService().performLookup(form);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=checkForm")
	public ModelAndView checkForm(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return getModelAndViewService().checkForm(form);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=performFieldSuggest")
	public @ResponseBody AttributeQueryResult performFieldSuggest(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		return getQueryControllerService().performFieldSuggest(form);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=downloadAttachment")
	public ModelAndView downloadAttachment(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) throws ServletRequestBindingException, FileNotFoundException, IOException {
		return getTransactionalDocumentControllerService().downloadAttachment(form, response);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=performFieldQuery")
	public @ResponseBody AttributeQueryResult performFieldQuery(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		return getQueryControllerService().performFieldQuery(form);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=tableCsvRetrieval", produces = {"text/csv"})
	@ResponseBody
	public String tableCsvRetrieval(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return getUifExportControllerService().tableCsvRetrieval(form, request, response);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=tableXlsRetrieval", produces = {"text/csv"})
	@ResponseBody
	public String tableXlsRetrieval(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return getUifExportControllerService().tableXlsRetrieval(form, request, response);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=tableXmlRetrieval", produces = {"text/csv"})
	@ResponseBody
	public String tableXmlRetrieval(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		return getUifExportControllerService().tableXmlRetrieval(form, request, response);
	}

	@RequestMapping(value = "/proposalDevelopment", params="methodToCall=tableJsonRetrieval")
	public ModelAndView tableJsonRetrieval(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		return getCollectionControllerService().tableJsonRetrieval(form);
	}
	
    @RequestMapping(params = "methodToCall=retrieveCollectionPage")
	public ModelAndView retrieveCollectionPage(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return getCollectionControllerService().retrieveCollectionPage(form);
	}
    
    @RequestMapping(params = "methodToCall=supervisorFunctions")
    public ModelAndView supervisorFunctions(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	return getTransactionalDocumentControllerService().supervisorFunctions(form);
    }

    @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=createHierarchy"})
    public ModelAndView createHierarchy(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
         DevelopmentProposal initialChildProposal = form.getProposalDevelopmentDocument().getDevelopmentProposal();
         List<ProposalHierarchyErrorWarningDto> errors = getProposalHierarchyService().validateChildCandidate(initialChildProposal);

         if (!displayErrors(errors)) {
             String userId = globalVariableService.getUserSession().getPrincipalId();
             String hierarchyProposalNumber = getProposalHierarchyService().createHierarchy(initialChildProposal, userId);
            displayMessage(ProposalHierarchyKeyConstants.MESSAGE_CREATE_SUCCESS, new String[]{hierarchyProposalNumber});
         }
        return getModelAndViewService().getModelAndView(form);
    }


    @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=syncAllHierarchy")
    public ModelAndView syncAllHierarchy(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
                                        HttpServletRequest request, HttpServletResponse response) throws Exception {
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
    @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=linkToHierarchy")
    public ModelAndView linkToHierarchy(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

            DevelopmentProposal hierarchyProposal = getProposalHierarchyService().getDevelopmentProposal(form.getNewHierarchyProposalNumber());
            DevelopmentProposal newChildProposal = form.getProposalDevelopmentDocument().getDevelopmentProposal();
            String hierarchyBudgetTypeCode = form.getNewHierarchyBudgetTypeCode();
            List<ProposalHierarchyErrorWarningDto> errors = getProposalHierarchyService().validateLinkToHierarchy(hierarchyProposal, newChildProposal);
            if (!displayErrors(errors)) {
                getProposalHierarchyService().linkToHierarchy(hierarchyProposal, newChildProposal, hierarchyBudgetTypeCode);
                displayMessage(ProposalHierarchyKeyConstants.MESSAGE_LINK_SUCCESS, new String[]{newChildProposal.getProposalNumber(), hierarchyProposal.getProposalNumber()});
            }

            return getModelAndViewService().getModelAndView(form);
    }

        /*
        Link a child proposal to this parent
         */
        @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=linkChildToHierarchy")
        public ModelAndView linkChildToHierarchy(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
                                            HttpServletRequest request, HttpServletResponse response) throws Exception {
            ProposalDevelopmentDocumentForm pdForm = form;
            DevelopmentProposal hierarchyProposal = form.getProposalDevelopmentDocument().getDevelopmentProposal();
            DevelopmentProposal newChildProposal = getProposalHierarchyService().getDevelopmentProposal(pdForm.getNewHierarchyChildProposalNumber());
            String hierarchyBudgetTypeCode = pdForm.getNewHierarchyBudgetTypeCode();

            List<ProposalHierarchyErrorWarningDto> errors = getProposalHierarchyService().validateParent(hierarchyProposal);
            errors.addAll(getProposalHierarchyService().validateChildCandidate(newChildProposal));
            errors.addAll(getProposalHierarchyService().validateChildCandidateForHierarchy(hierarchyProposal, newChildProposal, true));
            errors.addAll(getProposalHierarchyService().validateSponsor(newChildProposal, hierarchyProposal));

            if (!displayErrors(errors)) {
                getProposalHierarchyService().linkToHierarchy(hierarchyProposal, newChildProposal, hierarchyBudgetTypeCode);
                displayMessage(ProposalHierarchyKeyConstants.MESSAGE_LINK_SUCCESS, new String[]{hierarchyProposal.getProposalNumber(), newChildProposal.getProposalNumber()});
            }

            return getModelAndViewService().getModelAndView(form);
        }

    @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=hierarchyActionCanceled")
    public ModelAndView hierarchyActionCanceled(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
    	return getNavigationControllerService().back(form);
    }

    @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=syncToHierarchyParent")
    public ModelAndView syncToHierarchyParent(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
                                              HttpServletRequest request, HttpServletResponse response) throws Exception {
        DevelopmentProposal childProposal = form.getProposalDevelopmentDocument().getDevelopmentProposal();
        DevelopmentProposal hierarchy = getProposalHierarchyService().getDevelopmentProposal(childProposal.getHierarchyParentProposalNumber());
        List<ProposalHierarchyErrorWarningDto> errors = getProposalHierarchyService().validateChildForSync(childProposal, hierarchy, false);
        if (!displayErrors(errors)) {
            getProposalHierarchyService().synchronizeChild(childProposal);
            displayMessage(ProposalHierarchyKeyConstants.MESSAGE_SYNC_SUCCESS, new String[]{});
        }

        return getModelAndViewService().getModelAndView(form);
    }

    @RequestMapping(value ="/proposalDevelopment", params = "methodToCall=removeFromHierarchy")
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
            GlobalVariables.getMessageMap().putInfo(ProposalHierarchyKeyConstants.FIELD_GENERIC, messageKey, errorParameters);
        }

        protected boolean displayErrors(List<ProposalHierarchyErrorWarningDto> errors) {
        int severeErrors = 0;
        for (ProposalHierarchyErrorWarningDto error : errors) {
            severeErrors += error.isSevere() ? 1 : 0;
            if (error.isSevere()) {
                GlobalVariables.getMessageMap().putError(ProposalHierarchyKeyConstants.FIELD_GENERIC, error.getErrorKey(), error.getErrorParameters());
            } else {
                GlobalVariables.getMessageMap().putWarning(ProposalHierarchyKeyConstants.FIELD_GENERIC, error.getErrorKey(), error.getErrorParameters());
            }
        }
        return severeErrors > 0 ? true : false;
    }

    @RequestMapping(value ="/proposalDevelopment", params = "methodToCall=closeProposal")
    public ModelAndView closeProposal(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
        DialogResponse dialogResponse = form.getDialogResponse("PropDev-Close-Dialog");
        if(dialogResponse == null) {
            return getModelAndViewService().showDialog("PropDev-Close-Dialog", true, form);
        }else if (dialogResponse.getResponse().equals("yes")){
            super.save(form);
            return closeWithoutSave(form);
        } else if (dialogResponse.getResponse().equals("no")) {
            return closeWithoutSave(form);
        }
        return getModelAndViewService().getModelAndView(form);
    }

    @RequestMapping(value ="/proposalDevelopment", params = "methodToCall=closeWithoutSave")
    public ModelAndView closeWithoutSave(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
        if (form.getProposalDevelopmentDocument().getPessimisticLocks() != null) {
            for (PessimisticLock lock : form.getProposalDevelopmentDocument().getPessimisticLocks()){
                try {
                    getPessimisticLockService().delete(String.valueOf(lock.getId()));
                } catch (AuthorizationException e) {
                    LOG.error("user does not have permission to delete this lock",e);
                }
            }
        }
        return getNavigationControllerService().returnToHub(form);
    }
}
