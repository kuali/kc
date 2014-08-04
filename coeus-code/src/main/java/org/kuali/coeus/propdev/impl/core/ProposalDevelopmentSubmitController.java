package org.kuali.coeus.propdev.impl.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kuali.coeus.propdev.impl.state.ProposalState;
import org.kuali.coeus.sys.framework.validation.AuditHelper;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.web.controller.MethodAccessible;
import org.kuali.rice.krad.web.form.DialogResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@SuppressWarnings("deprecation")
@Controller
public class ProposalDevelopmentSubmitController extends
		ProposalDevelopmentControllerBase {

    @Autowired
    @Qualifier("auditHelper")
    private AuditHelper auditHelper;


    @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=deleteProposal")
    public ModelAndView deleteProposal(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        DialogResponse disapproveConfirm = form.getDialogResponse("PropDev-SubmitPage-ConfirmDelete");
        if (disapproveConfirm == null) {
            return getModelAndViewService().showDialog("PropDev-SubmitPage-ConfirmDelete", true, form);
        }

        getProposalDevelopmentService().deleteProposal(form.getProposalDevelopmentDocument());
        return getNavigationControllerService().returnToHub(form);

    }
    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveAndExit")
    public  ModelAndView saveAndExit(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response)throws Exception{	
   		save(form,result,request,response);
   		return getNavigationControllerService().returnToHub(form);
	}
    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=submitForReview")
    public  ModelAndView submitForReview(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response)throws Exception {
        form.setAuditActivated(true);

        AuditHelper.ValidationState state = getAuditHelper().isValidSubmission(form, true);
        if (state != AuditHelper.ValidationState.ERROR){
        	form.getDevelopmentProposal().setSubmitFlag(true);
    		return getTransactionalDocumentControllerService().route(form);
    	}else{
    		GlobalVariables.getMessageMap().clearErrorMessages();
            form.setDataValidationItems(((ProposalDevelopmentViewHelperServiceImpl)form.getViewHelperService()).populateDataValidation(form,form.getView().getViewIndex()));
    		return getModelAndViewService().showDialog("PropDev-DataValidationSection", true, form);
    	}
   } 
   @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=cancelProposal")
    public ModelAndView cancelProposal(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
	   form.getDevelopmentProposal().setProposalStateTypeCode(ProposalState.CANCELED);
	   return getTransactionalDocumentControllerService().cancel(form);
    }
    @MethodAccessible
    @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=navigate", "actionParameters[navigateToPageId]=PropDev-SubmitPage"}) 
    public ModelAndView navigateToSubmit(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
        ((ProposalDevelopmentViewHelperServiceImpl) form.getViewHelperService()).populateCreditSplits(form);
        ((ProposalDevelopmentViewHelperServiceImpl) form.getViewHelperService()).populateQuestionnaires(form);
        return getNavigationControllerService().navigate(form);
   }
  
    public AuditHelper getAuditHelper() {
        return auditHelper;
    }

    public void setAuditHelper(AuditHelper auditHelper) {
        this.auditHelper = auditHelper;
    }
}