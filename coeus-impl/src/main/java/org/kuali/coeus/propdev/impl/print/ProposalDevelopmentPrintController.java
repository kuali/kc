/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.propdev.impl.print;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.coeus.common.framework.print.PrintConstants;
import org.kuali.coeus.propdev.impl.auth.ProposalDevelopmentDocumentAuthorizer;
import org.kuali.coeus.propdev.impl.auth.ProposalDevelopmentDocumentViewAuthorizer;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentControllerBase;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.sys.framework.controller.ControllerFileUtils;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.rice.krad.exception.AuthorizationException;
import org.kuali.rice.krad.web.controller.MethodAccessible;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    @Qualifier("kcPersonService")
    private KcPersonService kcPersonService;

    @Autowired
    @Qualifier("proposalDevelopmentDocumentViewAuthorizer")
    private ProposalDevelopmentDocumentViewAuthorizer proposalDevelopmentDocumentViewAuthorizer;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;
    
    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=preparePrintDialog")
    public ModelAndView preparePrintDialog(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) {
        getProposalDevelopmentPrintingService().populateSponsorForms(form.getSponsorFormTemplates(),form.getDevelopmentProposal().getSponsorCode());
        form.setPrintS2sOppForms(form.getDevelopmentProposal().getS2sOppForms());
        return getModelAndViewService().showDialog("PropDev-Print-Dialog",true,form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=printSponsorForms")
    public ModelAndView printSponsorForms(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, HttpServletResponse response) throws Exception {

        proposalDevelopmentDocumentViewAuthorizer.initializeDocumentAuthorizerIfNecessary(form.getProposalDevelopmentDocument());

        if (!((ProposalDevelopmentDocumentAuthorizer) proposalDevelopmentDocumentViewAuthorizer.getDocumentAuthorizer()).isAuthorizedToPrint(form.getProposalDevelopmentDocument(), globalVariableService.getUserSession().getPerson())) {
            throw new AuthorizationException(globalVariableService.getUserSession().getPrincipalName(), "printSponsorForms", "Proposal");
        }

        Map<String,Object> reportParameters = new HashMap<String,Object>();
        reportParameters.put(ProposalDevelopmentPrintingService.SELECTED_TEMPLATES,
                getProposalDevelopmentPrintingService().getSponsorFormTemplates(form.getSponsorFormTemplates()));


            AttachmentDataSource dataStream = getProposalDevelopmentPrintingService().printProposalDevelopmentReport(form.getDevelopmentProposal(),
                    ProposalDevelopmentPrintingService.PRINT_PROPOSAL_SPONSOR_FORMS, reportParameters);

            ControllerFileUtils.streamToResponse(dataStream, response);
            return null;
    }

    @MethodAccessible
    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=generateReport")
    public ModelAndView generateReport(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
        proposalDevelopmentDocumentViewAuthorizer.initializeDocumentAuthorizerIfNecessary(form.getProposalDevelopmentDocument());

        if (!((ProposalDevelopmentDocumentAuthorizer) proposalDevelopmentDocumentViewAuthorizer.getDocumentAuthorizer()).isAuthorizedToPrint(form.getProposalDevelopmentDocument(), globalVariableService.getUserSession().getPerson())) {
            throw new AuthorizationException(globalVariableService.getUserSession().getPrincipalName(), "printReport", "Proposal");
        }

        form.getReportHelper().getCurrentReportBeans().clear();
        form.getReportHelper().getPendingReportBeans().clear();
        form.getReportHelper().setTargetPerson(getKcPersonService().getKcPersonByPersonId(form.getReportHelper().getPersonId()));
        if (StringUtils.equals(form.getReportHelper().getReportType(), "current")) {
            form.getReportHelper().prepareCurrentReport();
        } else {
            form.getReportHelper().preparePendingReport();
        }

        return getRefreshControllerService().refresh(form);
    }

    @MethodAccessible
    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=printReport")
    public ModelAndView printReport(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, HttpServletResponse response) throws Exception {
        proposalDevelopmentDocumentViewAuthorizer.initializeDocumentAuthorizerIfNecessary(form.getProposalDevelopmentDocument());

        if (!((ProposalDevelopmentDocumentAuthorizer) proposalDevelopmentDocumentViewAuthorizer.getDocumentAuthorizer()).isAuthorizedToPrint(form.getProposalDevelopmentDocument(), globalVariableService.getUserSession().getPerson())) {
            throw new AuthorizationException(globalVariableService.getUserSession().getPrincipalName(), "printReport", "Proposal");
        }

        form.getReportHelper().setTargetPerson(getKcPersonService().getKcPersonByPersonId(form.getReportHelper().getPersonId()));

        Map<String, Object> reportParameters = new HashMap<String, Object>();
        reportParameters.put(PrintConstants.PERSON_ID_KEY, form.getReportHelper().getPersonId());
        reportParameters.put(PrintConstants.REPORT_PERSON_NAME_KEY, form.getReportHelper().getTargetPerson().getFullName());
        AttachmentDataSource dataStream = null;
        if (StringUtils.equals(form.getReportHelper().getReportType(), "current")) {
            dataStream = form.getReportHelper().getCurrentAndPendingReportService().printCurrentReport(reportParameters);
        } else {
            dataStream = form.getReportHelper().getCurrentAndPendingReportService().printPendingReport(reportParameters);
        }

        ControllerFileUtils.streamToResponse(dataStream,response);
        return null;
    }

    public ProposalDevelopmentPrintingService getProposalDevelopmentPrintingService() {
        return proposalDevelopmentPrintingService;
    }

    public void setProposalDevelopmentPrintingService(ProposalDevelopmentPrintingService proposalDevelopmentPrintingService) {
        this.proposalDevelopmentPrintingService = proposalDevelopmentPrintingService;
    }

    public KcPersonService getKcPersonService() {
        return kcPersonService;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

    public ProposalDevelopmentDocumentViewAuthorizer getProposalDevelopmentDocumentViewAuthorizer() {
        return proposalDevelopmentDocumentViewAuthorizer;
    }

    public void setProposalDevelopmentDocumentViewAuthorizer(ProposalDevelopmentDocumentViewAuthorizer proposalDevelopmentDocumentViewAuthorizer) {
        this.proposalDevelopmentDocumentViewAuthorizer = proposalDevelopmentDocumentViewAuthorizer;
    }

    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }
}


