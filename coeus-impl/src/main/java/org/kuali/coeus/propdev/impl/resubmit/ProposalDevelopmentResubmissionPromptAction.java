/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.propdev.impl.resubmit;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.propdev.impl.action.ProposalDevelopmentActionsAction;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentForm;
import org.kuali.kra.infrastructure.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * On a Proposal Development resubmission, determines whether a new Institutional Proposal should be generated.
 */
public class ProposalDevelopmentResubmissionPromptAction extends ProposalDevelopmentActionsAction {
    
    /**
     * Proceeds with submitting this Proposal Development document to the sponsor once it determines whether to generate the Institutional Proposal.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward proceed(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument document = proposalDevelopmentForm.getProposalDevelopmentDocument();
        String submissionOption = proposalDevelopmentForm.getResubmissionOption();
        
        if (applyRules(new ResubmissionRuleEvent(document, submissionOption))) {
            super.submitToSponsor(mapping, form, request, response);
            forward = mapping.findForward(Constants.MAPPING_PROPOSAL_ACTIONS);
        }
        
        if(proposalDevelopmentForm.isGrantsGovSubmitFlag()){
            super.submitToGrantsGov(mapping, proposalDevelopmentForm, request, response);
            forward = mapping.findForward(Constants.GRANTS_GOV_PAGE);
        }
        
        return forward;
    }
    
    @Override
    public ActionForward cancel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward(Constants.MAPPING_PROPOSAL_ACTIONS);
    }

}
