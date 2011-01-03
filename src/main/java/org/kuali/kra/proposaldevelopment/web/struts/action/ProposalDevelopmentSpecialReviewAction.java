/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.common.specialreview.rule.event.AddSpecialReviewEvent;
import org.kuali.kra.common.specialreview.service.SpecialReviewService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.specialreview.ProposalSpecialReview;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.rice.kns.service.KualiRuleService;

/**
 * Handles Special Review Actions.
 */
public class ProposalDevelopmentSpecialReviewAction extends ProposalDevelopmentAction {
    
    private SpecialReviewService specialReviewService;
    
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
        ProposalDevelopmentDocument document = proposalDevelopmentForm.getDocument();
        ProposalSpecialReview newSpecialReview = proposalDevelopmentForm.getSpecialReviewHelper().getNewSpecialReview();

        KualiRuleService ruleService = KraServiceLocator.getService(KualiRuleService.class);
        if (ruleService.applyRules(new AddSpecialReviewEvent<ProposalSpecialReview>(proposalDevelopmentForm.getDocument(), newSpecialReview))) {
            newSpecialReview.setSpecialReviewNumber(document.getDocumentNextValue(Constants.PROPOSAL_SPECIALREVIEW_NUMBER));
            document.getDevelopmentProposal().getPropSpecialReviews().add(newSpecialReview);
            proposalDevelopmentForm.getSpecialReviewHelper().setNewSpecialReview(new ProposalSpecialReview());
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Deletes a special review item.
     * 
     * @param mapping the action mapping
     * @param form the action form
     * @param request the request
     * @param response the response
     * @return the action forward
     * @throws Exception if unable to add the special review
     */
    public ActionForward deleteSpecialReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {
        
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument document = proposalDevelopmentForm.getDocument();
        document.getDevelopmentProposal().getPropSpecialReviews().remove(getLineToDelete(request));
        
        return mapping.findForward(Constants.MAPPING_BASIC);
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
        
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalSpecialReview proposalSpecialReview = proposalDevelopmentForm.getSpecialReviewHelper().getNewSpecialReview();

        String viewProtocolUrl = getViewProtocolUrl(proposalSpecialReview);

        if (StringUtils.isNotEmpty(viewProtocolUrl)) {
            forward = new ActionForward(viewProtocolUrl, true);
        }
        
        return forward;
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
        
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        String lineNumber = request.getParameter("line");
        
        if (NumberUtils.isNumber(lineNumber)) {
            int index = Integer.parseInt(lineNumber);
            ProposalSpecialReview proposalSpecialReview = proposalDevelopmentForm.getDocument().getDevelopmentProposal().getPropSpecialReviews().get(index);

            String viewProtocolUrl = getViewProtocolUrl(proposalSpecialReview);

            if (StringUtils.isNotBlank(viewProtocolUrl)) {
                forward = new ActionForward(viewProtocolUrl, true);
            }
        }
        
        return forward;
    }
    
    private String getViewProtocolUrl(ProposalSpecialReview specialReview) throws Exception {
        String protocolNumber = specialReview.getProtocolNumber();
        Long routeHeaderId = getSpecialReviewService().getViewSpecialReviewProtocolRouteHeaderId(protocolNumber);
        String forwardUrl = buildForwardUrl(routeHeaderId);
        
        return StringUtils.isNotBlank(forwardUrl) ? forwardUrl + "&viewDocument=true" : Constants.EMPTY_STRING;
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