package org.kuali.coeus.propdev.impl.action;


import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentControllerBase;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.sys.framework.validation.AuditHelper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProposalDevelopmentSuperUserController extends ProposalDevelopmentControllerBase {


    @Transactional @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=superUserTakeActions")
    public ModelAndView superUserTakeActions(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
        if(!getValidationState(form).equals(AuditHelper.ValidationState.ERROR)) {
            form.setEvaluateFlagsAndModes(true);
            return getTransactionalDocumentControllerService().superUserTakeActions(form);
        }
        else{
            return getModelAndViewService().showDialog("PropDev-DataValidationSection", true, form);
        }
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=superUserApprove")
    public ModelAndView superUserApprove(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
        if(!getValidationState(form).equals(AuditHelper.ValidationState.ERROR)) {
            form.setEvaluateFlagsAndModes(true);
            return getTransactionalDocumentControllerService().superUserApprove(form);
        }
        else{
            return getModelAndViewService().showDialog("PropDev-DataValidationSection", true, form);
        }
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=superUserDisapprove")
    public ModelAndView superUserDisapprove(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
        form.setEvaluateFlagsAndModes(true);
        return getTransactionalDocumentControllerService().superUserDisapprove(form);
    }
}
