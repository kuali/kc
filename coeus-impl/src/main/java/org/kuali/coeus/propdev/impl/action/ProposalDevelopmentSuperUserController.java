package org.kuali.coeus.propdev.impl.action;


import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentControllerBase;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.sys.framework.validation.AuditHelper;
import org.kuali.kra.infrastructure.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProposalDevelopmentSuperUserController extends ProposalDevelopmentControllerBase {


    @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=superUserTakeActions")
    public ModelAndView superUserTakeActions(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
        if(!getValidationState(form).equals(AuditHelper.ValidationState.ERROR)) {
            return getTransactionalDocumentControllerService().superUserTakeActions(form);
        }
        else{
            return getModelAndViewService().showDialog("PropDev-DataValidationSection", true, form);
        }
    }

    @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=superUserApprove")
    public ModelAndView superUserApprove(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
        if(!getValidationState(form).equals(AuditHelper.ValidationState.ERROR)) {
            return getTransactionalDocumentControllerService().superUserApprove(form);
        }
        else{
            return getModelAndViewService().showDialog("PropDev-DataValidationSection", true, form);
        }
    }

    @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=superUserDisapprove")
    public ModelAndView superUserDisapprove(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
        return getTransactionalDocumentControllerService().superUserDisapprove(form);
    }
}
