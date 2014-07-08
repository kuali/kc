/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.propdev.impl.specialreview;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.framework.compliance.core.AddSpecialReviewEvent;
import org.kuali.coeus.common.framework.compliance.core.SaveSpecialReviewEvent;
import org.kuali.coeus.common.framework.compliance.core.SpecialReviewService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentAction;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentForm;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.framework.compliance.core.SpecialReviewType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Handles Special Review Actions.
 */
public class ProposalDevelopmentSpecialReviewAction extends ProposalDevelopmentAction {
    
    private static final String SAVE_SPECIAL_REVIEW_FIELD = "document.developmentProposalList[0].propSpecialReviews";
    private static final String CONFIRM_DELETE_SPECIAL_REVIEW_KEY = "confirmDeleteSpecialReview";
    
    private SpecialReviewService specialReviewService;
    private ProposalDevelopmentSpecialReviewService proposalDevelopmentSpecialReviewService;
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = super.execute(mapping, form, request, response);
        
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        
        proposalDevelopmentForm.getSpecialReviewHelper().prepareView();
        
        return forward;
    }

    @Override
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception {

        ActionForward forward = super.refresh(mapping, form, request, response);

        String prefix = getSpecialReviewService().getProtocolSaveLocationPrefix(request.getParameterMap());
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        
        ProposalSpecialReview proposalSpecialReview = null;
        
        if (StringUtils.startsWith(prefix, "specialReviewHelper.newSpecialReview")) {
            proposalSpecialReview = proposalDevelopmentForm.getSpecialReviewHelper().getNewSpecialReview();
        } else {
            int index = getSpecialReviewService().getProtocolIndex(prefix);
            if (index != -1) {
                proposalSpecialReview = proposalDevelopmentForm.getProposalDevelopmentDocument().getDevelopmentProposal().getPropSpecialReviews().get(index);
            }
        }
        
        proposalDevelopmentForm.getSpecialReviewHelper().prepareProtocolLinkViewFields(proposalSpecialReview);
        
        return forward;
    }
    
    /**
     * Adds a special review item. The add only completes if the special review to be added passes all audit rules.
     * 
     * @param mapping the action mapping
     * @param form the action form
     * @param request the request
     * @param response the response
     * @return the action forward
     * @throws Exception if unable to add the special review
     */
    public ActionForward addSpecialReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument document = proposalDevelopmentForm.getProposalDevelopmentDocument();
        ProposalSpecialReview specialReview = proposalDevelopmentForm.getSpecialReviewHelper().getNewSpecialReview();
        List<ProposalSpecialReview> specialReviews = document.getDevelopmentProposal().getPropSpecialReviews();
        boolean isProtocolLinkingEnabled = false;
        if (SpecialReviewType.HUMAN_SUBJECTS.equals(specialReview.getSpecialReviewTypeCode())) {
            isProtocolLinkingEnabled =  proposalDevelopmentForm.getSpecialReviewHelper().getIsIrbProtocolLinkingEnabled() ;
        }
        else if (SpecialReviewType.ANIMAL_USAGE.equals(specialReview.getSpecialReviewTypeCode())) {
            isProtocolLinkingEnabled =  proposalDevelopmentForm.getSpecialReviewHelper().getIsIacucProtocolLinkingEnabled();
        }
        proposalDevelopmentForm.getSpecialReviewHelper().prepareProtocolLinkViewFields(specialReview);
        
        KualiRuleService ruleService = getKualiRuleService();
        if (ruleService.applyRules(new AddSpecialReviewEvent<ProposalSpecialReview>(document, specialReview, specialReviews, isProtocolLinkingEnabled ))) {
            specialReview.setSpecialReviewNumber(document.getDocumentNextValue(Constants.PROPOSAL_SPECIALREVIEW_NUMBER));
            document.getDevelopmentProposal().getPropSpecialReviews().add(specialReview);
            proposalDevelopmentForm.getSpecialReviewHelper().setNewSpecialReview(new ProposalSpecialReview());
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
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
     * @throws Exception if unable to add the special review
     */
    public ActionForward confirmDeleteSpecialReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {

        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        if (CONFIRM_DELETE_SPECIAL_REVIEW_KEY.equals(question)) {
            ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
            ProposalDevelopmentDocument document = proposalDevelopmentForm.getProposalDevelopmentDocument();
            
            document.getDevelopmentProposal().getPropSpecialReviews().remove(getLineToDelete(request));
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument document = proposalDevelopmentForm.getProposalDevelopmentDocument();
        ProposalSpecialReview specialReview = proposalDevelopmentForm.getSpecialReviewHelper().getNewSpecialReview();
        List<ProposalSpecialReview> specialReviews = document.getDevelopmentProposal().getPropSpecialReviews();
        boolean isPDIrbProtocolLinkingEnabled = proposalDevelopmentForm.getSpecialReviewHelper().getIsIrbProtocolLinkingEnabled() ;;
        boolean isPDIacucProtocolLinkingEnabled = proposalDevelopmentForm.getSpecialReviewHelper().getIsIacucProtocolLinkingEnabled();
       
        proposalDevelopmentForm.getSpecialReviewHelper().prepareProtocolLinkViewFields(specialReview);
        
        if (applyRules(new SaveSpecialReviewEvent<ProposalSpecialReview>(SAVE_SPECIAL_REVIEW_FIELD, document, specialReviews, isPDIrbProtocolLinkingEnabled, isPDIacucProtocolLinkingEnabled))) {
            forward = super.save(mapping, form, request, response);
        }
        
        return forward;
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
        
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalSpecialReview proposalSpecialReview = proposalDevelopmentForm.getSpecialReviewHelper().getNewSpecialReview();
        viewProtocolUrl = getViewProtocolUrl(proposalSpecialReview);
        
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
        
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        String lineNumber = request.getParameter("line");
        
        if (NumberUtils.isNumber(lineNumber)) {
            int index = Integer.parseInt(lineNumber);
            ProposalSpecialReview proposalSpecialReview = proposalDevelopmentForm.getProposalDevelopmentDocument().getDevelopmentProposal().getPropSpecialReviews().get(index);
            viewProtocolUrl = getViewProtocolUrl(proposalSpecialReview);
        }
        
        return new ActionForward(viewProtocolUrl, true);
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
            specialReviewService = KcServiceLocator.getService(SpecialReviewService.class);
        }
        return specialReviewService;
    }
    
    public void setSpecialReviewService(SpecialReviewService specialReviewService) {
        this.specialReviewService = specialReviewService;
    }

    /**
     * creates Protocol for Human subjects or Animal subjects
     * 
     * @param mapping the action mapping
     * @param form the action form
     * @param request the request
     * @param response the response
     * @return the action forward
     * @throws Exception if unable to add the special review
     */
    public ActionForward createProtocol(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument document = proposalDevelopmentForm.getProposalDevelopmentDocument();
        
        ProposalSpecialReview specialReview = proposalDevelopmentForm.getSpecialReviewHelper().getNewSpecialReview();
        if (getProposalDevelopmentSpecialReviewService().createProtocol(specialReview, document)) {
            proposalDevelopmentForm.getSpecialReviewHelper().setNewSpecialReview(new ProposalSpecialReview());
        }
        

        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ProposalDevelopmentSpecialReviewService getProposalDevelopmentSpecialReviewService() {
        if (proposalDevelopmentSpecialReviewService == null) {
            proposalDevelopmentSpecialReviewService = KcServiceLocator.getService(ProposalDevelopmentSpecialReviewService.class);
        }
        return proposalDevelopmentSpecialReviewService;
    }

    public void setProposalDevelopmentSpecialReviewService(
            ProposalDevelopmentSpecialReviewService proposalDevelopmentSpecialReviewService) {
        this.proposalDevelopmentSpecialReviewService = proposalDevelopmentSpecialReviewService;
    }

    protected KualiRuleService getKualiRuleService (){
    	return KcServiceLocator.getService(KualiRuleService.class);
    }
}