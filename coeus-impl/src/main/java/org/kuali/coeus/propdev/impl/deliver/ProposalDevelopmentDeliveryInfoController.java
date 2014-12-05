package org.kuali.coeus.propdev.impl.deliver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentControllerBase;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.rice.krad.web.form.DocumentFormBase;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProposalDevelopmentDeliveryInfoController extends ProposalDevelopmentControllerBase{

    @Transactional @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=clearMailingNameAddress")
    public ModelAndView clearMailingNameAddress(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
       ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
       DevelopmentProposal developmentProposal = pdForm.getProposalDevelopmentDocument().getDevelopmentProposal();
       if (developmentProposal.getRolodex() != null) {
           developmentProposal.setMailingAddressId(null);
           developmentProposal.setRolodex(null);
       }
       return getRefreshControllerService().refresh(form);
    }
}
