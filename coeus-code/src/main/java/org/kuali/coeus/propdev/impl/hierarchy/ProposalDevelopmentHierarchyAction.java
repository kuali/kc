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
package org.kuali.coeus.propdev.impl.hierarchy;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.propdev.impl.budget.ProposalBudgetStatusService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentAction;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentForm;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.budget.framework.calculator.BudgetCalculationService;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.rice.krad.service.BusinessObjectService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


public class ProposalDevelopmentHierarchyAction extends ProposalDevelopmentAction {

    private static final String FORWARD_PROPOSAL_SUMMARY = "ajaxProposalSummary";
    private static final String FORWARD_BUDGET_SUMMARY = "ajaxBudgetSummary";
    
    private transient ProposalHierarchyService proposalHierarchyService;
    private transient BusinessObjectService businessObjectService;
    private transient ProposalBudgetStatusService proposalBudgetStatusService;
    private transient BudgetCalculationService budgetCalculationService;
    
    /**
     * 
     */
    public ActionForward loadProposalSummary(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm)form;
        String proposalNumber = pdForm.getProposalNumberToSummarize();
        DevelopmentProposal proposal = null;
        if (proposalNumber != null && !proposalNumber.equals("")) {
            Map<String, String> primaryKeys = new HashMap<String, String>();
            primaryKeys.put("proposalNumber", proposalNumber);
            proposal = (DevelopmentProposal) getBusinessObjectService().findByPrimaryKey(DevelopmentProposal.class, primaryKeys);
            if (proposal != null) {
                getProposalBudgetStatusService().loadBudgetStatus(proposal);
            }
        }
        pdForm.setProposalToSummarize(proposal);
        pdForm.setProposalSummary(getProposalHierarchyService().getProposalSummary(proposalNumber));
        
        return mapping.findForward(FORWARD_PROPOSAL_SUMMARY);
    }

    /**
     * 
     */
    public ActionForward loadBudgetSummary(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm)form;
        String budgetIdString = pdForm.getBudgetNumberToSummarize();
        Long budgetId = null;
        Budget budget = null;
        try {
            budgetId = Long.valueOf(budgetIdString);
        }
        catch (NumberFormatException x) {
            budgetId = null;
        }
        
        if (budgetId != null) {
            Map<String, Long> primaryKeys = new HashMap<String, Long>();
            primaryKeys.put("budgetId", budgetId);
            budget = (Budget)getBusinessObjectService().findByPrimaryKey(ProposalDevelopmentBudgetExt.class, primaryKeys);
            if (budget != null) {
                getBudgetCalculationService().calculateBudgetSummaryTotals(budget);

                if (budget.getFinalVersionFlag() != null && Boolean.TRUE.equals(budget.getFinalVersionFlag())) {
                    DevelopmentProposal proposal = (DevelopmentProposal)budget.getBudgetParent();
                    getProposalBudgetStatusService().loadBudgetStatus(proposal);
                    budget.setBudgetStatus(proposal.getBudgetStatus());
                }
                else {
                    String budgetStatusIncompleteCode = this.getParameterService().getParameterValueAsString(
                            BudgetDocument.class, Constants.BUDGET_STATUS_INCOMPLETE_CODE);
                    budget.setBudgetStatus(budgetStatusIncompleteCode);
                    
                }
            }
        }
        pdForm.setBudgetToSummarize(budget);
        pdForm.setProposalHierarchyIndirectObjectCode(getParameterService().getParameterValueAsString(BudgetDocument.class, "proposalHierarchySubProjectIndirectCostElement"));

        return mapping.findForward(FORWARD_BUDGET_SUMMARY);
    }

    public BusinessObjectService getBusinessObjectService() {
        if (businessObjectService ==null)
            businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        return businessObjectService;
    }

    public BudgetCalculationService getBudgetCalculationService (){
        if (budgetCalculationService == null)
            budgetCalculationService = KcServiceLocator.getService(BudgetCalculationService.class);
        return budgetCalculationService;
    }

    public ProposalBudgetStatusService getProposalBudgetStatusService (){
        if (proposalBudgetStatusService ==null)
            proposalBudgetStatusService = KcServiceLocator.getService(ProposalBudgetStatusService.class);
        return proposalBudgetStatusService;
    }

    public ProposalHierarchyService getProposalHierarchyService() {
        if (proposalHierarchyService == null) {
            proposalHierarchyService = KcServiceLocator.getService(ProposalHierarchyService.class);
        }
        return proposalHierarchyService;
    }

    public void setProposalHierarchyService(ProposalHierarchyService proposalHierarchyService) {
        this.proposalHierarchyService = proposalHierarchyService;
    }    

}
