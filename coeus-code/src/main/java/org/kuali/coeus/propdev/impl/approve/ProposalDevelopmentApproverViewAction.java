/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.approve;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.common.framework.compliance.core.SpecialReviewService;
import org.kuali.coeus.propdev.impl.action.ProposalDevelopmentActionsAction;
import org.kuali.coeus.propdev.impl.attachment.NarrativeAttachment;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentForm;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiography;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiographyAttachment;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.specialreview.ProposalSpecialReview;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;

public class ProposalDevelopmentApproverViewAction extends ProposalDevelopmentActionsAction {

    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentActionsAction.class);
    private static final String MODULE_NUMBER = "moduleNumber";
    private static final String PROPOSAL_NUMBER = "proposalNumber";
    private static final String PROPOSAL_PERSON_NUMBER = "proposalPersonNumber";
    private static final String BIOGRAPHY_NUMBER = "biographyNumber";
    private static final String LINE_NUMBER = "line";

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
        if(narrativeAttachment==null){
            narrativeAttachment = narrative.getNarrativeAttachment();
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
        if(propPersonBioAttachment==null ){
            propPersonBioAttachment = propPersonBio.getPersonnelAttachment();
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
        if(narrativeAttachment==null){
            narrativeAttachment = narrative.getNarrativeAttachment();
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
        getProposalDevelopmentPrintingService().populateSponsorForms(proposalDevelopmentForm.getSponsorFormTemplates(), proposalDevelopmentDocument.getDevelopmentProposal().getSponsorCode());
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

    protected SpecialReviewService getSpecialReviewService() {
        if (specialReviewService == null) {
            specialReviewService = KcServiceLocator.getService(SpecialReviewService.class);
        }
        return specialReviewService;
    }

    public ActionForward printBudgetForm(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument document = proposalDevelopmentForm.getProposalDevelopmentDocument();       
        BudgetDocument budgetDocument = getProposalBudgetService().getFinalBudgetVersion(document);
        Budget budget = budgetDocument.getBudget();
        Integer selectedLine = getSelectedLine(request);
        if(budget.getBudgetPrintForms().isEmpty()){
            getBudgetPrintService().populateBudgetPrintForms(budget);
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
            AttachmentDataSource dataStream = getBudgetPrintService().readBudgetPrintStream(budget,budgetFormToPrint);
            if(dataStream.getData()!=null){
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
