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

import java.util.List;

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
import org.kuali.kra.budget.service.BudgetDocumentService;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;

public class BudgetVersionsAction extends BudgetAction {
    private static final Log LOG = LogFactory.getLog(BudgetVersionsAction.class);

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
        BudgetDocumentService budgetService = KraServiceLocator.getService(BudgetDocumentService.class);
        BudgetDocument newBudgetDoc = budgetService.getNewBudgetVersion(pdDoc, budgetForm.getNewBudgetVersionName());
        pdDoc.addNewBudgetVersion(newBudgetDoc);
        
        // Below code is for forwarding to new Budget Document
        Long routeHeaderId = newBudgetDoc.getDocumentHeader().getWorkflowDocument().getRouteHeaderId();
        String forward = buildForwardUrl(routeHeaderId);
        return new ActionForward(forward, true);
    }
    
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
    
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        setFinalBudgetVersion(budgetForm.getFinalBudgetVersion(), budgetForm.getBudgetDocument().getProposal().getBudgetVersionOverviews());
        return super.save(mapping, form, request, response);
    }
    
}
