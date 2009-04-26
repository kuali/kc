/*
 * Copyright 2006-2008 The Kuali Foundation
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

import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.ProposalCopyCriteria;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.event.CopyProposalEvent;
import org.kuali.kra.proposaldevelopment.service.ProposalCopyService;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.kra.s2s.bo.S2sAppSubmission;
import org.kuali.kra.s2s.bo.S2sSubmissionHistory;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DateTimeService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.KualiRuleService;
import org.kuali.rice.kns.service.PessimisticLockService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;

/**
 * Handles all of the actions from the Proposal Development Actions web page.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProposalDevelopmentResubmissionAction extends ProposalDevelopmentAction {
    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentActionsAction.class);

    private DateTimeService dateTimeService;
    /**
     * Struts mapping for the Proposal web page.  
     */
    private static final String MAPPING_PROPOSAL = "proposal";    
    
    public ActionForward copyProposalForResubmission(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ActionForward nextWebPage = null;
        BusinessObjectService boService = KraServiceLocator.getService(BusinessObjectService.class);
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument doc = proposalDevelopmentForm.getProposalDevelopmentDocument();
        int i=0;
        for(S2sSubmissionHistory s2sSubmissionHistory:doc.getS2sSubmissionHistory()){
            s2sSubmissionHistory = (S2sSubmissionHistory)boService.retrieve(s2sSubmissionHistory);
            doc.getS2sSubmissionHistory().get(i).setSubmissionTime(s2sSubmissionHistory.getSubmissionTime());
            i++;
        }
        
        ProposalCopyCriteria criteria = proposalDevelopmentForm.getCopyCriteria();
        
        // check any business rules
        boolean rulePassed = getKualiRuleService().applyRules(new CopyProposalEvent(doc, criteria));

        if (!rulePassed) {
            nextWebPage = mapping.findForward(Constants.MAPPING_BASIC);
        }
        else {
        // Use the Copy Service to copy the proposal.

        ProposalCopyService proposalCopyService = (ProposalCopyService) KraServiceLocator.getService("proposalCopyService");
        if (proposalCopyService == null) {

            // Something bad happened. The errors are in the Global Error Map
            // which will be displayed to the user.

            nextWebPage = mapping.findForward(Constants.MAPPING_BASIC);
        }
        else {
            String originalProposalId = doc.getProposalNumber();
            String newDocId = proposalCopyService.copyProposal(doc, criteria);
            KualiWorkflowDocument originalWFDoc= doc.getDocumentHeader().getWorkflowDocument();
            KraServiceLocator.getService(PessimisticLockService.class).releaseAllLocksForUser(doc.getPessimisticLocks(), (UniversalUser) GlobalVariables.getUserSession().getPerson());
            DocumentService docService = KNSServiceLocator.getDocumentService();
            // Switch over to the new proposal development document and
            // go to the Proposal web page.
            
            proposalDevelopmentForm.setDocId(newDocId);
            this.loadDocument(proposalDevelopmentForm);
            ProposalDevelopmentDocument copiedDocument = proposalDevelopmentForm.getProposalDevelopmentDocument();
            copiedDocument.setS2sAppSubmission(new ArrayList<S2sAppSubmission>());
            copiedDocument.setContinuedFrom(originalProposalId);
            copiedDocument.setProposalTypeCode("4");
            copiedDocument.getS2sOpportunity().setS2sSubmissionType(null);
                        
            KualiWorkflowDocument workflowDocument = copiedDocument.getDocumentHeader().getWorkflowDocument();
            if(!originalWFDoc.stateIsFinal()){
                originalWFDoc.cancel("");
            }
            //Copying the submission history from the previous proposal to new proposal
            //setting id field to null so that OJB can treat this as a new insert
            //just copying the existing history and not adding any new history
            for(S2sSubmissionHistory s2sSubmissionHistory : copiedDocument.getS2sSubmissionHistory()){
                s2sSubmissionHistory.setId(null);
            }           
            docService.saveDocument(copiedDocument);
            boService.save(copiedDocument.getS2sSubmissionHistory());
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
    
    
    
    
    
