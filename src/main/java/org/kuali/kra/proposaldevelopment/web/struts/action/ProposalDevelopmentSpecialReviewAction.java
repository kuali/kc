/*
 * Copyright 2006-2009 The Kuali Foundation
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

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.bo.ExemptionType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.ProposalSpecialReview;
import org.kuali.kra.proposaldevelopment.rule.event.AddProposalSpecialReviewEvent;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.rice.kns.service.KualiRuleService;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * Handles Special Review Actions.
 */
public class ProposalDevelopmentSpecialReviewAction extends ProposalDevelopmentAction {
    
    /**
     * Adds a special review item. The add only completes if the special review to be added passes all audit rules.
     * 
     * @param mapping the action mapping
     * @param form the action form
     * @param request the request
     * @param response the reponse
     * @return the action forward
     * @throws Exception if unable to add the special review
     */
    public ActionForward addSpecialReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm) form;
        ProposalSpecialReview newProposalSpecialReview = pdForm.getNewPropSpecialReview();

        KualiRuleService ruleService = KraServiceLocator.getService(KualiRuleService.class);
        
        if (ruleService.applyRules(new AddProposalSpecialReviewEvent(Constants.EMPTY_STRING, pdForm.getDocument(), newProposalSpecialReview))){
            
            newProposalSpecialReview.setSpecialReviewNumber(pdForm.getDocument().getDocumentNextValue(Constants.PROPOSAL_SPECIALREVIEW_NUMBER));
            pdForm.getDocument().getDevelopmentProposal().getPropSpecialReviews().add(newProposalSpecialReview);

            pdForm.setNewPropSpecialReview(new ProposalSpecialReview());
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Deletes a special review item.
     * 
     * @param mapping the action mapping
     * @param form the action form
     * @param request the request
     * @param response the reponse
     * @return the action forward
     * @throws Exception if unable to add the special review
     */
    public ActionForward deleteSpecialReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        proposalDevelopmentForm.getDocument().getDevelopmentProposal().getPropSpecialReviews().remove(getLineToDelete(request));
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
}
