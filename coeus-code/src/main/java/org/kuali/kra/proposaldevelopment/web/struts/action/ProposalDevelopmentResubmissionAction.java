/*
 * Copyright 2005-2013 The Kuali Foundation
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

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.bo.ProposalCopyCriteria;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.event.CopyProposalEvent;
import org.kuali.kra.proposaldevelopment.service.ProposalCopyService;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.kra.s2s.bo.S2sAppSubmission;
import org.kuali.rice.kew.api.WorkflowDocument;
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
        BusinessObjectService boService = KcServiceLocator.getService(BusinessObjectService.class);
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument doc = proposalDevelopmentForm.getProposalDevelopmentDocument();
        int i=0;
        
        ProposalCopyCriteria criteria = proposalDevelopmentForm.getCopyCriteria();
        
        // check any business rules
        boolean rulePassed = getKualiRuleService().applyRules(new CopyProposalEvent(doc, criteria));

        if (!rulePassed) {
            nextWebPage = mapping.findForward(Constants.MAPPING_BASIC);
        }
        else {
        // Use the Copy Service to copy the proposal.

        ProposalCopyService proposalCopyService = (ProposalCopyService) KcServiceLocator.getService("proposalCopyService");
        if (proposalCopyService == null) {

            // Something bad happened. The errors are in the Global Error Map
            // which will be displayed to the user.

            nextWebPage = mapping.findForward(Constants.MAPPING_BASIC);
        }
        else {
            String originalProposalId = doc.getDevelopmentProposal().getProposalNumber();
            String newDocId = proposalCopyService.copyProposal(doc, criteria);
            WorkflowDocument originalWFDoc= doc.getDocumentHeader().getWorkflowDocument();
            KcServiceLocator.getService(PessimisticLockService.class).releaseAllLocksForUser(doc.getPessimisticLocks(), GlobalVariables.getUserSession().getPerson());
            DocumentService docService = KRADServiceLocatorWeb.getDocumentService();
            // Switch over to the new proposal development document and
            // go to the Proposal web page.
            
            proposalDevelopmentForm.setDocId(newDocId);
            this.loadDocument(proposalDevelopmentForm);
            ProposalDevelopmentDocument copiedDocument = proposalDevelopmentForm.getProposalDevelopmentDocument();
            copiedDocument.getDevelopmentProposal().setS2sAppSubmission(new ArrayList<S2sAppSubmission>());
            copiedDocument.getDevelopmentProposal().setContinuedFrom(originalProposalId);
            copiedDocument.getDevelopmentProposal().setProposalTypeCode("4");
            copiedDocument.getDevelopmentProposal().getS2sOpportunity().setS2sSubmissionType(null);
                        
            WorkflowDocument workflowDocument = copiedDocument.getDocumentHeader().getWorkflowDocument();
 // Removed cancel of original document until KEW will allow this to happen
 //           if(!originalWFDoc.isFinal()){
 //               originalWFDoc.cancel("");
 //           }
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
}
    
    
    
    
    
