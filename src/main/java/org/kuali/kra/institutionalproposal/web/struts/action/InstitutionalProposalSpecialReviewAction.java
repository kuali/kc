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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalSpecialReview;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;
import org.kuali.kra.rule.event.AddSpecialReviewEvent;

/**
 * Invokes rules on and applies actions to add, delete, or save SpecialReviews.
 */
public class InstitutionalProposalSpecialReviewAction extends InstitutionalProposalAction {
    
    private static final String NEW_SPECIAL_REVIEW = "newSpecialReview";

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
        InstitutionalProposalSpecialReview newSpecialReview = institutionalProposalForm.getNewSpecialReview();
        
        if (applyRules(new AddSpecialReviewEvent<InstitutionalProposalSpecialReview>(NEW_SPECIAL_REVIEW, document, newSpecialReview))) {
            newSpecialReview.setSpecialReviewNumber(document.getDocumentNextValue(Constants.SPECIAL_REVIEW_NUMBER));
            document.getInstitutionalProposal().getSpecialReviews().add(newSpecialReview);
            institutionalProposalForm.setNewSpecialReview(new InstitutionalProposalSpecialReview());
        }
        
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    /**
     * This method deletes the SpecialReview from the list.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteSpecialReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {
        InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
        InstitutionalProposalDocument document = institutionalProposalForm.getInstitutionalProposalDocument();
        
        document.getInstitutionalProposal().getSpecialReviews().remove(getLineToDelete(request));

        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.institutionalproposal.web.struts.action.InstitutionalProposalAction#save(org.apache.struts.action.ActionMapping, 
     *      org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
        InstitutionalProposal institutionalProposal = institutionalProposalForm.getInstitutionalProposalDocument().getInstitutionalProposal();
        
        if (!institutionalProposal.getSpecialReviews().isEmpty()) {
            institutionalProposal.setSpecialReviewIndicator("1");
        } else {
            institutionalProposal.setSpecialReviewIndicator("0");
        }
        
        return super.save(mapping, form, request, response);
    }
    
}
