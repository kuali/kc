/*
 * Copyright 2006-2008 The Kuali Foundation
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
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;
import org.kuali.rice.core.util.RiceConstants;

/**
 * This class...
 */
public class InstitutionalProposalIntellectualPropertyReviewAction extends InstitutionalProposalAction {
    
    /**
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#save(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     * 
     * There's nothing editable on this page, so skip saving the whole object graph.
     */
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward(RiceConstants.MAPPING_BASIC);
    }
    
    public ActionForward editIntellectualPropertyReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
        InstitutionalProposalDocument institutionalProposalDocument = (InstitutionalProposalDocument) institutionalProposalForm.getDocument();
        
        response.sendRedirect("kr/maintenance.do?businessObjectClassName=org.kuali.kra.institutionalproposal.ipreview.IntellectualPropertyReview&methodToCall=edit&proposalId=" 
                + institutionalProposalDocument.getInstitutionalProposal().getProposalId());
        
        return null;
    }

}
