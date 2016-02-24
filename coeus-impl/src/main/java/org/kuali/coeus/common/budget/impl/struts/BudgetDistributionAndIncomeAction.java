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
package org.kuali.coeus.common.budget.impl.struts;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.budget.framework.distribution.BudgetCostShare;
import org.kuali.coeus.common.budget.framework.income.AddBudgetProjectIncomeEvent;
import org.kuali.coeus.common.budget.framework.distribution.BudgetDistributionService;
import org.kuali.coeus.common.budget.framework.income.BudgetProjectIncome;
import org.kuali.coeus.common.budget.framework.distribution.BudgetUnrecoveredFandA;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetForm;
import org.kuali.coeus.common.budget.impl.distribution.AddBudgetCostShareEvent;
import org.kuali.coeus.common.budget.impl.distribution.AddBudgetUnrecoveredFandAEvent;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.rice.krad.service.KualiRuleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;
import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;

public class BudgetDistributionAndIncomeAction extends BudgetAction {
    private static final Log LOG = LogFactory.getLog(BudgetDistributionAndIncomeAction.class);
   
    private BudgetDistributionService bdiService;
    

    public BudgetDistributionAndIncomeAction() {
        super();
        bdiService = KcServiceLocator.getService(BudgetDistributionService.class);
    }

    /**
     * Override to intialize defaults after reload
     * 
     * @see org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase#reload(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = super.reload(mapping, form, request, response);
        Budget budget = ((BudgetForm)form).getBudgetDocument().getBudget();
        bdiService.initializeCollectionDefaults(budget);
        return forward;
    }
    
    /**
     * Add a Cost Share, validating as needed 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addCostShare(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form; 
        Budget budget = budgetForm.getBudgetDocument().getBudget();
        BudgetCostShare budgetCostShare = budgetForm.getNewBudgetCostShare();
        boolean passed = getKcBusinessRulesEngine().applyRules(new AddBudgetCostShareEvent(budget, budgetCostShare));
        
        if(passed) {
            setCostShareAddRowDefaults(budget, budgetCostShare);
                       
            budget.add(budgetCostShare);
            budgetForm.setNewBudgetCostShare(new BudgetCostShare());
            LOG.debug("Added new BudgetCostShare: " + budgetCostShare);
        }  

        return mapping.findForward(MAPPING_BASIC);
    }
    
    /**
     * 
     * Add BudgetProjectIncome, validating as needed
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addProjectIncome(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetProjectIncome budgetProjectIncome = budgetForm.getNewBudgetProjectIncome();
        boolean passed = getKcBusinessRulesEngine().applyRules(createRuleEvent(budgetForm, budgetProjectIncome));
        
        if(passed) {
            budgetForm.getBudgetDocument().getBudget().add(budgetProjectIncome);
            budgetForm.setNewBudgetProjectIncome(new BudgetProjectIncome());
            LOG.debug("Added new BudgetProjectIncome: " + budgetProjectIncome);
        }  

        return mapping.findForward(MAPPING_BASIC);
    }

    /**
     * Add UnrecoveredFandA, validating as needed
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addUnrecoveredFandA(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form; 
        AwardBudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        BudgetUnrecoveredFandA budgetUnrecoveredFandA = budgetForm.getNewBudgetUnrecoveredFandA();
        boolean passed = getKcBusinessRulesEngine().applyRules(createRuleEvent(budgetForm, budgetUnrecoveredFandA));
        
        if(passed) {
            setUnrecoveredFandAAddRowDefaults(budgetDocument, budgetUnrecoveredFandA);
                       
            budgetForm.getBudgetDocument().getBudget().add(budgetUnrecoveredFandA);
            budgetForm.setNewBudgetUnrecoveredFandA(new BudgetUnrecoveredFandA());
            LOG.debug("Added new BudgetCostShare: " + budgetUnrecoveredFandA);
        }  

        return mapping.findForward(MAPPING_BASIC);
    }

    /**
     * Delete Cost Share row
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteCostShare(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ((BudgetForm)form).getBudgetDocument().getBudget().removeBudgetCostShare(getLineToDelete(request));        
        return mapping.findForward(MAPPING_BASIC);
    }
    
    /**
     * Delete Project Income row
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteProjectIncome(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ((BudgetForm)form).getBudgetDocument().getBudget().removeBudgetProjectIncome(getLineToDelete(request));        
        return mapping.findForward(MAPPING_BASIC);
    }
    
    /**
     * Delete Unrecovered F&amp;A row
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteUnrecoveredFandA(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ((BudgetForm)form).getBudgetDocument().getBudget().removeBudgetUnrecoveredFandA(getLineToDelete(request));        
        return mapping.findForward(MAPPING_BASIC);
    }
    
    /**
     * Refresh totals. Calculations are done on page render, so simply re-render page 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward refreshTotals(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // recalculation occurs on page render
        return mapping.findForward(MAPPING_BASIC);
    }
    
    /**
     * Reset default rows
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward resetCostSharingToDefault(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {        
        Budget budget = getDocument(form).getBudget();
        budget.getBudgetCostShares().clear();
        bdiService.initializeCostSharingCollectionDefaults(budget);
        
        return mapping.findForward(MAPPING_BASIC);
    }

    /**
     * Reset default rows
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward resetUnrecoveredFandAToDefault(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Budget budget = getDocument(form).getBudget();
        budget.getBudgetUnrecoveredFandAs().clear();
        bdiService.initializeUnrecoveredFandACollectionDefaults(budget);
        
        return mapping.findForward(MAPPING_BASIC);
    }
    
    /**
     * Convenience method to allow stubbing
     * @return
     */
    protected KualiRuleService getKualiRuleService() {
        return getService(KualiRuleService.class);
    }
    
    /**
     * Factory method
     * @param budgetForm
     * @param budgetProjectIncome
     * @return
     */
    private AddBudgetProjectIncomeEvent createRuleEvent(BudgetForm budgetForm, BudgetProjectIncome budgetProjectIncome) {
        return new AddBudgetProjectIncomeEvent(budgetForm.getBudget(), budgetProjectIncome);
    }
    
    /**
     * Factory method
     * @param budgetForm
     * @param budgetUnrecoveredFandA
     * @return
     */
    private AddBudgetUnrecoveredFandAEvent createRuleEvent(BudgetForm budgetForm, BudgetUnrecoveredFandA budgetUnrecoveredFandA) {
        return new AddBudgetUnrecoveredFandAEvent(budgetForm.getBudget(), budgetUnrecoveredFandA);
    }
    
    /**
     * Convenience method
     * @param form
     * @return
     */
    private AwardBudgetDocument getDocument(ActionForm form) {
        BudgetForm budgetForm = (BudgetForm) form;
        AwardBudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        return budgetDocument;
    }
    
    /**
     * Sets default values for new Cost Share row when value not present
     * @param budget
     * @param budgetCostShare
     */
    private void setCostShareAddRowDefaults(Budget budget, BudgetCostShare budgetCostShare) {
        ScaleTwoDecimal defaultValue = ScaleTwoDecimal.ZERO;
        if(budgetCostShare.getProjectPeriod() == null) {
            budgetCostShare.setProjectPeriod(0);
        }
        if(budgetCostShare.getShareAmount() == null) {
            ScaleTwoDecimal shareAmount = budgetCostShare.getProjectPeriod() == 0 ? defaultValue : budget.findCostSharingForFiscalYear(budgetCostShare.getProjectPeriod());
            budgetCostShare.setShareAmount(shareAmount);
        }
        if(budgetCostShare.getSharePercentage() == null) {
            budgetCostShare.setSharePercentage(defaultValue);
        }
    }
    
    /**
     * Sets default values for new UnrecoveredFandA row when value not present
     * @param budgetDocument
     * @param budgetUnrecoveredFandA
     */
    private void setUnrecoveredFandAAddRowDefaults(AwardBudgetDocument budgetDocument, BudgetUnrecoveredFandA budgetUnrecoveredFandA) {
        if(budgetUnrecoveredFandA.getFiscalYear() == null) {
            budgetUnrecoveredFandA.setFiscalYear(0);
        }
        if(budgetUnrecoveredFandA.getAmount() == null) {
            ScaleTwoDecimal shareAmount = budgetUnrecoveredFandA.getFiscalYear() == 0 ? ScaleTwoDecimal.ZERO :
                    budgetDocument.getBudget().findUnrecoveredFandAForFiscalYear(budgetUnrecoveredFandA.getFiscalYear());                 
            budgetUnrecoveredFandA.setAmount(shareAmount);
        }
        if(budgetUnrecoveredFandA.getApplicableRate() == null) {
            budgetUnrecoveredFandA.setApplicableRate(ScaleTwoDecimal.ZERO);
        }
    }
}
