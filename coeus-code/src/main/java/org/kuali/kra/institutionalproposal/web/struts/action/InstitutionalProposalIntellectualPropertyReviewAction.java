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
package org.kuali.kra.institutionalproposal.web.struts.action;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;
import org.kuali.rice.core.api.util.RiceConstants;
import org.kuali.rice.krad.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class InstitutionalProposalIntellectualPropertyReviewAction extends InstitutionalProposalAction {
    
    /**
     * @see org.kuali.coeus.sys.framework.controller.KcTransactionalDocumentActionBase#save(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     * 
     * There's nothing editable on this page, so skip saving the whole object graph.
     */
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // reload so the new inp comment can be refreshed from DB
        super.reload(mapping, form, request, response);
        return mapping.findForward(RiceConstants.MAPPING_BASIC);
    }
    
    public ActionForward editIntellectualPropertyReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
        InstitutionalProposalDocument institutionalProposalDocument = (InstitutionalProposalDocument) institutionalProposalForm.getDocument();
        institutionalProposalDocument.getInstitutionalProposal().getProposalIpReviewJoin().refreshReferenceObject("intellectualPropertyReview");
        
        if (ObjectUtils.isNotNull(institutionalProposalDocument.getInstitutionalProposal().getProposalIpReviewJoin().getIntellectualPropertyReview())) {
            response.sendRedirect("kr/maintenance.do?businessObjectClassName=org.kuali.kra.institutionalproposal.ipreview.IntellectualPropertyReview&methodToCall=copy&ipReviewId=" 
                    + institutionalProposalDocument.getInstitutionalProposal().getProposalIpReviewJoin().getIntellectualPropertyReview().getIpReviewId()
                    + "&proposalId=" + institutionalProposalDocument.getInstitutionalProposal().getProposalId());
        }
        
        return null;
    }

}
