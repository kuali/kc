/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
package org.kuali.kra.institutionalproposal.web.struts.action;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.ipreview.IntellectualPropertyReview;
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
            response.sendRedirect("kr/maintenance.do?businessObjectClassName=" + IntellectualPropertyReview.class.getName() + "&methodToCall=copy&ipReviewId="
                    + institutionalProposalDocument.getInstitutionalProposal().getProposalIpReviewJoin().getIntellectualPropertyReview().getIpReviewId()
                    + "&proposalId=" + institutionalProposalDocument.getInstitutionalProposal().getProposalId());
        }
        
        return null;
    }

}
