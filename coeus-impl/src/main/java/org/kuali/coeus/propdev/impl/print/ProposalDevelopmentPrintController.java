package org.kuali.coeus.propdev.impl.print;

import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentControllerBase;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.sys.framework.controller.ControllerFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ProposalDevelopmentPrintController extends ProposalDevelopmentControllerBase{

    @Autowired
    @Qualifier("proposalDevelopmentPrintingService")
    private ProposalDevelopmentPrintingService proposalDevelopmentPrintingService;

    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=preparePrintDialog")
    public ModelAndView preparePrintDialog(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) {
        getProposalDevelopmentPrintingService().populateSponsorForms(form.getSponsorFormTemplates(),form.getDevelopmentProposal().getSponsorCode());

        return getModelAndViewService().showDialog("PropDev-Print-Dialog",true,form);
    }

    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=printSponsorForms")
    public ModelAndView printSponsorForms(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, HttpServletResponse response) throws Exception {

        Map<String,Object> reportParameters = new HashMap<String,Object>();
        reportParameters.put(ProposalDevelopmentPrintingService.SELECTED_TEMPLATES,
                getProposalDevelopmentPrintingService().getSponsorFormTemplates(form.getSponsorFormTemplates()));


            AttachmentDataSource dataStream = getProposalDevelopmentPrintingService().printProposalDevelopmentReport(form.getDevelopmentProposal(),
                    ProposalDevelopmentPrintingService.PRINT_PROPOSAL_SPONSOR_FORMS, reportParameters);

            ControllerFileUtils.streamToResponse(dataStream, response);
            return null;
    }

    public ProposalDevelopmentPrintingService getProposalDevelopmentPrintingService() {
        return proposalDevelopmentPrintingService;
    }

    public void setProposalDevelopmentPrintingService(ProposalDevelopmentPrintingService proposalDevelopmentPrintingService) {
        this.proposalDevelopmentPrintingService = proposalDevelopmentPrintingService;
    }
}


