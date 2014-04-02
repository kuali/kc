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
