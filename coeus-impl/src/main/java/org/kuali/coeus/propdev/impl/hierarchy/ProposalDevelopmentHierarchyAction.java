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
            }
        }
        pdForm.setBudgetToSummarize(budget);
        pdForm.setProposalHierarchyIndirectObjectCode(getParameterService().getParameterValueAsString(Budget.class, "proposalHierarchySubProjectIndirectCostElement"));

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
