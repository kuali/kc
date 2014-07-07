package org.kuali.coeus.propdev.impl.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kuali.coeus.propdev.impl.state.ProposalState;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProposalDevelopmentSubmitController extends
		ProposalDevelopmentControllerBase {

    @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=deleteProposal")
    public ModelAndView deleteProposal(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
    	getProposalDevelopmentService().deleteProposal(form.getProposalDevelopmentDocument());
        return getTransactionalDocumentControllerService().refresh(form, result, request, response);
    }
    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveAndExit")
    public  ModelAndView saveAndExit(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response)throws Exception{	
   		save(form,result,request,response);
   		return getTransactionalDocumentControllerService().returnToHub(form); 
	}
    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=submitForReview")
    public  ModelAndView submitForReview(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response)throws Exception {	
    	form.getDevelopmentProposal().setSubmitFlag(true);
    	return getTransactionalDocumentControllerService().refresh(form, result, request, response); 	
   } 
   @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=cancelProposal")
    public ModelAndView cancelProposal(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
	   form.getDevelopmentProposal().setProposalStateTypeCode(ProposalState.CANCELED);
	   return getTransactionalDocumentControllerService().refresh(form, result, request, response);
    }
}
