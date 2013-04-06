/*
 * Copyright 2005-2013 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.web.struts.action;

import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.bo.SponsorFormTemplate;
import org.kuali.kra.bo.SponsorFormTemplateList;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.BudgetService;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.common.specialreview.service.SpecialReviewService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeAttachment;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiography;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiographyAttachment;
import org.kuali.kra.proposaldevelopment.budget.service.BudgetPrintService;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.printing.service.ProposalDevelopmentPrintingService;
import org.kuali.kra.proposaldevelopment.specialreview.ProposalSpecialReview;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.kra.web.struts.action.AuditActionHelper;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.krad.util.GlobalVariables;

public class ProposalDevelopmentApproverViewAction extends ProposalDevelopmentActionsAction {

    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentActionsAction.class);
    private static final String MODULE_NUMBER = "moduleNumber";
    private static final String PROPOSAL_NUMBER = "proposalNumber";
    private static final String PROPOSAL_PERSON_NUMBER = "proposalPersonNumber";
    private static final String BIOGRAPHY_NUMBER = "biographyNumber";
    private static final String LINE_NUMBER = "line";
    private BudgetService budgetService;
    private SpecialReviewService specialReviewService;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        ActionForward forward = super.execute(mapping, form, request, response);
        super.refresh(mapping, form, request, response);
        return forward;
    }

    public ActionForward printForms(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return super.printForms(mapping, form, request, response);
    }


    public ActionForward downloadProposalAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        String line = request.getParameter(LINE_NUMBER);
        int lineNumber = line == null ? 0 : Integer.parseInt(line);
        ProposalDevelopmentDocument pd = proposalDevelopmentForm.getProposalDevelopmentDocument();
        Narrative narrative = pd.getDevelopmentProposal().getNarratives().get(lineNumber);
        NarrativeAttachment narrativeAttachment = findNarrativeAttachment(narrative);
        if(narrativeAttachment==null && !narrative.getNarrativeAttachmentList().isEmpty()){//get it from the memory
            narrativeAttachment = narrative.getNarrativeAttachmentList().get(0);
        }
        streamToResponse(narrativeAttachment,response);
        return null;
    }
    public ActionForward viewPersonnelAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument pd = proposalDevelopmentForm.getProposalDevelopmentDocument();
        String line = request.getParameter(LINE_NUMBER);
        int lineNumber = line == null ? 0 : Integer.parseInt(line);
        ProposalPersonBiography propPersonBio = pd.getDevelopmentProposal().getPropPersonBios().get(lineNumber);
        Map<String,String> propPersonBioAttVal = new HashMap<String,String>();
        propPersonBioAttVal.put(PROPOSAL_NUMBER, propPersonBio.getProposalNumber());
        propPersonBioAttVal.put(BIOGRAPHY_NUMBER, propPersonBio.getBiographyNumber()+"");
        propPersonBioAttVal.put(PROPOSAL_PERSON_NUMBER, propPersonBio.getProposalPersonNumber()+"");
        ProposalPersonBiographyAttachment propPersonBioAttachment = (ProposalPersonBiographyAttachment)getBusinessObjectService().findByPrimaryKey(ProposalPersonBiographyAttachment.class, propPersonBioAttVal);
        if(propPersonBioAttachment==null && !propPersonBio.getPersonnelAttachmentList().isEmpty()){//get it from the memory
            propPersonBioAttachment = propPersonBio.getPersonnelAttachmentList().get(0);
        }
        streamToResponse(propPersonBioAttachment,response);
        return  null;
    }

    public ActionForward downloadInstituteAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        String line = request.getParameter(LINE_NUMBER);

        int lineNumber = line == null ? 0 : Integer.parseInt(line);
        ProposalDevelopmentDocument pd = proposalDevelopmentForm.getProposalDevelopmentDocument();
        Narrative narrative = pd.getDevelopmentProposal().getInstituteAttachments().get(lineNumber);
        NarrativeAttachment narrativeAttachment = findNarrativeAttachment(narrative);
        if(narrativeAttachment==null && !narrative.getNarrativeAttachmentList().isEmpty()){//get it from the memory
            narrativeAttachment = narrative.getNarrativeAttachmentList().get(0);
        }
        streamToResponse(narrativeAttachment,response); 
        return null;
    }
    /**
     * 
     * This method used to find the narrative attachment for a narrative
     * @param narrative
     * @return NarrativeAttachment
     */
    private NarrativeAttachment findNarrativeAttachment(Narrative narrative){
        Map<String,String> narrativeAttachemntMap = new HashMap<String,String>();
        narrativeAttachemntMap.put(PROPOSAL_NUMBER, narrative.getProposalNumber());
        narrativeAttachemntMap.put(MODULE_NUMBER, narrative.getModuleNumber()+"");
        return (NarrativeAttachment)getBusinessObjectService().findByPrimaryKey(NarrativeAttachment.class, narrativeAttachemntMap);
    }

    /**
     * 
     * This method is called to populate sponsor forms under Print
     * @param form
     * @throws Exception
     */
    public void populateSponsorForms(ActionForm form) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getProposalDevelopmentDocument();
        ProposalDevelopmentPrintingService printService = KraServiceLocator.getService(ProposalDevelopmentPrintingService.class);
        printService.populateSponsorForms(proposalDevelopmentForm.getSponsorFormTemplates(), proposalDevelopmentDocument.getDevelopmentProposal().getSponsorCode());
    }

    /**
     * 
     * This method is called to print forms
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward printSponsorForms(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)proposalDevelopmentForm.getProposalDevelopmentDocument();
        ActionForward actionForward = mapping.findForward(MAPPING_BASIC);
        String proposalNumber = proposalDevelopmentDocument.getDevelopmentProposal().getProposalNumber();

        List<SponsorFormTemplateList> sponsorFormTemplateLists = proposalDevelopmentForm.getSponsorFormTemplates();
        ProposalDevelopmentPrintingService printService = KraServiceLocator.getService(ProposalDevelopmentPrintingService.class);
        List<SponsorFormTemplate> printFormTemplates = new ArrayList<SponsorFormTemplate>();  
        printFormTemplates = printService.getSponsorFormTemplates(sponsorFormTemplateLists); 
        Map<String,Object> reportParameters = new HashMap<String,Object>();
        reportParameters.put(ProposalDevelopmentPrintingService.SELECTED_TEMPLATES, printFormTemplates);

        try {
            AttachmentDataSource dataStream = printService.printProposalDevelopmentReport(proposalDevelopmentDocument.getDevelopmentProposal(), 
                    ProposalDevelopmentPrintingService.PRINT_PROPOSAL_SPONSOR_FORMS, reportParameters);
            streamToResponse(dataStream, response);
            return null;

        }
        catch (NullPointerException npe) {
            LOG.error("Error generating print stream for proposal forms", npe);
            GlobalVariables.getMessageMap().putError("print.nofield", KeyConstants.ERROR_PRINTING_UNKNOWN);
            return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
        }


    }
    public ActionForward actions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        if (proposalDevelopmentForm.getProposalDevelopmentDocument().getDocumentNumber() == null) {
            loadDocumentInForm(request, proposalDevelopmentForm);
        }
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getProposalDevelopmentDocument();
        ProposalDevelopmentPrintingService printService = KraServiceLocator.getService(ProposalDevelopmentPrintingService.class);
        printService.populateSponsorForms(proposalDevelopmentForm.getSponsorFormTemplates(), proposalDevelopmentDocument.getDevelopmentProposal().getSponsorCode());
        return mapping.findForward(Constants.PROPOSAL_ACTIONS_PAGE);
    }

    public ActionForward viewSpecialReviewProtocolLink(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
    throws Exception {

        String viewProtocolUrl = Constants.EMPTY_STRING;

        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        String lineNumber = request.getParameter("line");

        if (NumberUtils.isNumber(lineNumber)) {
            int index = Integer.parseInt(lineNumber);
            ProposalSpecialReview proposalSpecialReview = proposalDevelopmentForm.getProposalDevelopmentDocument().getDevelopmentProposal().getPropSpecialReviews().get(index);
            viewProtocolUrl = getViewProtocolUrl(proposalSpecialReview);
        }

        return mapping.findForward(Constants.PERSON_CERTIFICATE); 
    }
    private String getViewProtocolUrl(ProposalSpecialReview specialReview) throws Exception {
        String viewProtocolUrl = Constants.EMPTY_STRING;

        String protocolNumber = specialReview.getProtocolNumber();
        String routeHeaderId = getSpecialReviewService().getViewSpecialReviewProtocolRouteHeaderId(protocolNumber, specialReview.getSpecialReviewTypeCode());
        if (StringUtils.isNotEmpty(routeHeaderId)) { 
            viewProtocolUrl = buildForwardUrl(routeHeaderId) + "&viewDocument=true";
        }

        return viewProtocolUrl;
    }

    public SpecialReviewService getSpecialReviewService() {
        if (specialReviewService == null) {
            specialReviewService = KraServiceLocator.getService(SpecialReviewService.class);
        }
        return specialReviewService;
    }


    public ActionForward printBudgetForm(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument document = proposalDevelopmentForm.getProposalDevelopmentDocument();       
        BudgetDocument budgetDocument = getS2SBudgetCalculatorService() .getFinalBudgetVersion(document);
        BudgetPrintService budgetPrintService = KraServiceLocator
        .getService(BudgetPrintService.class);
        Budget budget = budgetDocument.getBudget();
        Integer selectedLine = getSelectedLine(request);
        if(budget.getBudgetPrintForms().isEmpty()){
            budgetPrintService.populateBudgetPrintForms(budget);
        }
        String budgetFormToPrint = budget.getBudgetPrintForms().get(selectedLine).getBudgetReportId();
        if(proposalDevelopmentForm.getSelectedBudgetPrint()!=null && budgetFormToPrint !=null){
            String forms[]=proposalDevelopmentForm.getSelectedBudgetPrint();  
            if(forms[0].equals(budgetFormToPrint)){
                budget.setPrintBudgetCommentFlag("true");
            }
        }

        proposalDevelopmentForm.setSelectedBudgetPrint(null);

        ActionForward forward = mapping.findForward(MAPPING_BASIC);
        if (budgetFormToPrint != null) {
            AttachmentDataSource dataStream = budgetPrintService.readBudgetPrintStream(budget,budgetFormToPrint);
            if(dataStream.getContent()!=null){
                streamToResponse(dataStream, response);
                forward = null;
            }
        }

        return forward;
    }

    public void setSpecialReviewService(SpecialReviewService specialReviewService) {
        this.specialReviewService = specialReviewService;
    }

}
