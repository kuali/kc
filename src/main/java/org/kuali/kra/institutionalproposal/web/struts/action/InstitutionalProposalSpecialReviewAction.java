/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.institutionalproposal.web.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.common.specialreview.rule.event.AddSpecialReviewEvent;
import org.kuali.kra.common.specialreview.rule.event.SaveSpecialReviewLinkEvent;
import org.kuali.kra.common.specialreview.service.SpecialReviewService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.specialreview.InstitutionalProposalSpecialReview;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;
import org.kuali.rice.krad.util.KRADConstants;

/**
 * Invokes rules on and applies actions to add, delete, or save SpecialReviews.
 */
public class InstitutionalProposalSpecialReviewAction extends InstitutionalProposalAction {
    
    private static final String CONFIRM_DELETE_SPECIAL_REVIEW_KEY = "confirmDeleteSpecialReview";
    
    private SpecialReviewService specialReviewService;
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = super.execute(mapping, form, request, response);
        
        InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
        
        institutionalProposalForm.getSpecialReviewHelper().prepareView();
        
        return forward;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception {

        ActionForward forward = super.refresh(mapping, form, request, response);

        String prefix = getSpecialReviewService().getProtocolSaveLocationPrefix(request.getParameterMap());
        InstitutionalProposalForm proposalForm = (InstitutionalProposalForm) form;
        
        InstitutionalProposalSpecialReview proposalSpecialReview = null;
        if (StringUtils.startsWith(prefix, "specialReviewHelper.newSpecialReview")) {
            proposalSpecialReview = proposalForm.getSpecialReviewHelper().getNewSpecialReview();
        } else {
            int index = getSpecialReviewService().getProtocolIndex(prefix);
            if (index != -1) {
                proposalSpecialReview = proposalForm.getInstitutionalProposalDocument().getInstitutionalProposal().getSpecialReviews().get(index);
            }
        }
        
        proposalForm.getSpecialReviewHelper().prepareProtocolLinkViewFields(proposalSpecialReview);
        
        return forward;
    }

    /**
     * This method is for adding AwardSpecialReview to the list.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward addSpecialReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
        InstitutionalProposalDocument document = institutionalProposalForm.getInstitutionalProposalDocument();
        InstitutionalProposalSpecialReview specialReview = institutionalProposalForm.getSpecialReviewHelper().getNewSpecialReview();
        List<InstitutionalProposalSpecialReview> specialReviews = document.getInstitutionalProposal().getSpecialReviews();
        boolean isProtocolLinkingEnabled = institutionalProposalForm.getSpecialReviewHelper().getIsProtocolLinkingEnabled();
        
        institutionalProposalForm.getSpecialReviewHelper().prepareProtocolLinkViewFields(specialReview);
        
        if (applyRules(new AddSpecialReviewEvent<InstitutionalProposalSpecialReview>(document, specialReview, specialReviews, isProtocolLinkingEnabled))) {
            specialReview.setSpecialReviewNumber(document.getDocumentNextValue(Constants.SPECIAL_REVIEW_NUMBER));
            document.getInstitutionalProposal().getSpecialReviews().add(specialReview);
            institutionalProposalForm.getSpecialReviewHelper().setNewSpecialReview(new InstitutionalProposalSpecialReview());
        }
        
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    /**
     * Deletes a special review item after confirmation.
     * 
     * @param mapping the action mapping
     * @param form the action form
     * @param request the request
     * @param response the response
     * @return the action forward
     * @throws Exception if unable to delete the special review
     */
    public ActionForward deleteSpecialReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {

        return confirm(buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_DELETE_SPECIAL_REVIEW_KEY,
                KeyConstants.QUESTION_SPECIAL_REVIEW_DELETE_CONFIRMATION), CONFIRM_DELETE_SPECIAL_REVIEW_KEY, "");
    }
    
    /**
     * Deletes a special review item only if the user confirms it.
     * 
     * @param mapping the action mapping
     * @param form the action form
     * @param request the request
     * @param response the response
     * @return the action forward
     * @throws Exception if unable to delete the special review
     */
    public ActionForward confirmDeleteSpecialReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {
        
        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        if (CONFIRM_DELETE_SPECIAL_REVIEW_KEY.equals(question)) {
            InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
            InstitutionalProposalDocument document = institutionalProposalForm.getInstitutionalProposalDocument();
            
            document.getInstitutionalProposal().getSpecialReviews().remove(getLineToDelete(request));
        }
            
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.institutionalproposal.web.struts.action.InstitutionalProposalAction#save(org.apache.struts.action.ActionMapping, 
     *      org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
        InstitutionalProposalDocument document = institutionalProposalForm.getInstitutionalProposalDocument();
        List<InstitutionalProposalSpecialReview> specialReviews = document.getInstitutionalProposal().getSpecialReviews();
        List<String> linkedProtocolNumbers = institutionalProposalForm.getSpecialReviewHelper().getLinkedProtocolNumbers();
        boolean isProtocolLinkingEnabled = institutionalProposalForm.getSpecialReviewHelper().getIsProtocolLinkingEnabled();
        
        if (isProtocolLinkingEnabled) {
            if (applyRules(new SaveSpecialReviewLinkEvent<InstitutionalProposalSpecialReview>(document, specialReviews, linkedProtocolNumbers))) {
                institutionalProposalForm.getSpecialReviewHelper().syncProtocolFundingSourcesWithSpecialReviews();
            }
        }
        
        // For reasons unknown to me, to enforce saving special review records in order between successive saves, we must save the document before saving 
        // anything else (like the special review indicator) on the document.  This statement can be safely removed if the special review indicator is no 
        // longer being set at this point...
        getDocumentService().saveDocument(document);
        
        if (!document.getInstitutionalProposal().getSpecialReviews().isEmpty()) {
            document.getInstitutionalProposal().setSpecialReviewIndicator("1");
        } else {
            document.getInstitutionalProposal().setSpecialReviewIndicator("0");
        }
        
        return super.save(mapping, form, request, response);
    }
    
    /**
     * Displays the Protocol linked to the new special review item.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward viewNewSpecialReviewProtocolLink(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {
        
        String viewProtocolUrl = Constants.EMPTY_STRING;
        
        InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
        InstitutionalProposalSpecialReview institutionalProposalSpecialReview = institutionalProposalForm.getSpecialReviewHelper().getNewSpecialReview();
        viewProtocolUrl = getViewProtocolUrl(institutionalProposalSpecialReview);
        
        return new ActionForward(viewProtocolUrl, true);
    }
    
    /**
     * Displays the Protocol linked to the special review item on the selected line (from the parameter list since this is run through a popup window).
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward viewSpecialReviewProtocolLink(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {
        
        String viewProtocolUrl = Constants.EMPTY_STRING;
        
        InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
        String lineNumber = request.getParameter("line");
        
        if (NumberUtils.isNumber(lineNumber)) {
            int index = Integer.parseInt(lineNumber);
            InstitutionalProposalSpecialReview institutionalProposalSpecialReview 
                = institutionalProposalForm.getInstitutionalProposalDocument().getInstitutionalProposal().getSpecialReviews().get(index);
            viewProtocolUrl = getViewProtocolUrl(institutionalProposalSpecialReview);
        }
        
        return new ActionForward(viewProtocolUrl, true);
    }
    
    private String getViewProtocolUrl(InstitutionalProposalSpecialReview specialReview) throws Exception {
        String viewProtocolUrl = Constants.EMPTY_STRING;

        String protocolNumber = specialReview.getProtocolNumber();
        String routeHeaderId = getSpecialReviewService().getViewSpecialReviewProtocolRouteHeaderId(protocolNumber);
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
    
    public void setSpecialReviewService(SpecialReviewService specialReviewService) {
        this.specialReviewService = specialReviewService;
    }
    
}