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
import org.kuali.coeus.propdev.impl.copy.CopyProposalEvent;
import org.kuali.coeus.propdev.impl.copy.ProposalCopyCriteria;
import org.kuali.coeus.propdev.impl.copy.ProposalCopyService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentAction;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.s2s.S2sAppSubmission;
import org.kuali.rice.krad.service.*;
import org.kuali.rice.krad.util.GlobalVariables;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;

/**
 * Handles all of the actions from the Proposal Development Actions web page.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProposalDevelopmentResubmissionAction extends ProposalDevelopmentAction {

    /**
     * Struts mapping for the Proposal web page.  
     */
    private static final String MAPPING_PROPOSAL = "proposal";    
    
    public ActionForward copyProposalForResubmission(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ActionForward nextWebPage = null;
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument doc = proposalDevelopmentForm.getProposalDevelopmentDocument();
        
        ProposalCopyCriteria criteria = proposalDevelopmentForm.getCopyCriteria();
        
        // check any business rules
        boolean rulePassed = getKualiRuleService().applyRules(new CopyProposalEvent(doc, criteria));

        if (!rulePassed) {
            nextWebPage = mapping.findForward(Constants.MAPPING_BASIC);
        }
        else {
        // Use the Copy Service to copy the proposal.

        ProposalCopyService proposalCopyService = getProposalCopyService();
        if (proposalCopyService == null) {

            // Something bad happened. The errors are in the Global Error Map
            // which will be displayed to the user.

            nextWebPage = mapping.findForward(Constants.MAPPING_BASIC);
        }
        else {
            String originalProposalId = doc.getDevelopmentProposal().getProposalNumber();
            ProposalDevelopmentDocument newDoc = proposalCopyService.copyProposal(doc, criteria);
            getPessimisticLockService().releaseAllLocksForUser(doc.getPessimisticLocks(), GlobalVariables.getUserSession().getPerson());
            DocumentService docService = KRADServiceLocatorWeb.getDocumentService();
            // Switch over to the new proposal development document and
            // go to the Proposal web page.
            
            proposalDevelopmentForm.setDocId(newDoc.getDocumentNumber());
            this.loadDocument(proposalDevelopmentForm);
            ProposalDevelopmentDocument copiedDocument = proposalDevelopmentForm.getProposalDevelopmentDocument();
            copiedDocument.getDevelopmentProposal().setS2sAppSubmission(new ArrayList<S2sAppSubmission>());
            copiedDocument.getDevelopmentProposal().setContinuedFrom(originalProposalId);
            copiedDocument.getDevelopmentProposal().setProposalTypeCode("4");
            copiedDocument.getDevelopmentProposal().getS2sOpportunity().setS2sSubmissionType(null);
                        
            docService.saveDocument(copiedDocument);
            nextWebPage = mapping.findForward(MAPPING_PROPOSAL);
            }
        }

        return nextWebPage;
    }
    
    @Override
    protected KualiRuleService getKualiRuleService() {
        return getService(KualiRuleService.class);
    }
    
    protected ProposalCopyService getProposalCopyService() {
        return (ProposalCopyService) getService("proposalCopyService");
    }
    
    protected PessimisticLockService getPessimisticLockService() {
        return getService(PessimisticLockService.class);
    }
}
    
    
    
    
    
