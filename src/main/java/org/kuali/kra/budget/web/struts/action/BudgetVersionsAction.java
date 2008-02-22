/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.budget.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.core.service.DocumentService;
import org.kuali.kra.budget.bo.BudgetVersionOverview;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.service.BudgetService;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

/**
 * Struts Action class for requests from the Budget Versions page.
 */
public class BudgetVersionsAction extends BudgetAction {
    private static final Log LOG = LogFactory.getLog(BudgetVersionsAction.class);
    
    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ActionForward forward = super.docHandler(mapping, form, request, response);
        BudgetForm budgetForm = (BudgetForm) form;
        budgetForm.setFinalBudgetVersion(getFinalBudgetVersion(budgetForm.getBudgetDocument().getProposal().getBudgetVersionOverviews()));
        setProposalStatuses(budgetForm.getBudgetDocument().getProposal());
        
        return forward;
    }
    
    /* There is a certain amount of shared logic in the below methods. If you find yourself maintaining/refactoring this, consider consolidation. */

    /**
     * Action called to create a new budget version.
     * 
     * @param mapping 
     * @param form
     * @param request
     * @param response
     * @return ActionForward instance for forwarding to the tab.
     */
    public ActionForward addBudgetVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDoc = budgetForm.getBudgetDocument();
        ProposalDevelopmentDocument pdDoc = budgetDoc.getProposal();
        BudgetService budgetService = KraServiceLocator.getService(BudgetService.class);
        BudgetDocument newBudgetDoc = budgetService.getNewBudgetVersion(pdDoc, budgetForm.getNewBudgetVersionName());
        
        // Below code is for forwarding to new Budget Document
        Long routeHeaderId = newBudgetDoc.getDocumentHeader().getWorkflowDocument().getRouteHeaderId();
        String forward = buildForwardUrl(routeHeaderId);
        return new ActionForward(forward, true);
    }
    
    /**
     * This method opens a budget version.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward openBudgetVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDoc = budgetForm.getBudgetDocument();
        ProposalDevelopmentDocument pdDoc = budgetDoc.getProposal();
        BudgetVersionOverview budgetToOpen = pdDoc.getBudgetVersionOverview(getSelectedLine(request));
        DocumentService documentService = KraServiceLocator.getService(DocumentService.class);
        BudgetDocument budgetDocument = (BudgetDocument) documentService.getByDocumentHeaderId(budgetToOpen.getDocumentNumber());
        Long routeHeaderId = budgetDocument.getDocumentHeader().getWorkflowDocument().getRouteHeaderId();
        String forward = buildForwardUrl(routeHeaderId);
        return new ActionForward(forward, true);
    }
    
    /**
     * This method copies a budget version's data to a new budget version.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward copyBudgetVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDoc = budgetForm.getBudgetDocument();
        ProposalDevelopmentDocument pdDoc = budgetDoc.getProposal();
        BudgetVersionOverview budgetToCopy = pdDoc.getBudgetVersionOverview(getSelectedLine(request));
        DocumentService documentService = KraServiceLocator.getService(DocumentService.class);
        BudgetDocument budgetDocToCopy = (BudgetDocument) documentService.getByDocumentHeaderId(budgetToCopy.getDocumentNumber());
        BudgetService budgetService = KraServiceLocator.getService(BudgetService.class);
        BudgetDocument newBudgetDocument = budgetService.copyBudgetVersion(budgetDocToCopy);
        Long routeHeaderId = newBudgetDocument.getDocumentHeader().getWorkflowDocument().getRouteHeaderId();
        String forward = buildForwardUrl(routeHeaderId);
        return new ActionForward(forward, true);
    }
    
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        // FIXME These won't work until save proposal status from budget is resolved
        setFinalBudgetVersion(budgetForm.getFinalBudgetVersion(), budgetForm.getBudgetDocument().getProposal().getBudgetVersionOverviews());
        setProposalStatus(budgetForm.getBudgetDocument().getProposal());
        return super.save(mapping, form, request, response);
    }
    
    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = super.reload(mapping, form, request, response);
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDoc = budgetForm.getBudgetDocument();
        budgetForm.setFinalBudgetVersion(getFinalBudgetVersion(budgetDoc.getProposal().getBudgetVersionOverviews()));
        setProposalStatuses(budgetDoc.getProposal());
        return forward;
    }
    
}
