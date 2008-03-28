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

import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.core.service.KualiRuleService;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.RateDecimal;
import org.kuali.kra.budget.bo.BudgetCostShare;
import org.kuali.kra.budget.bo.BudgetDistributionAndIncomeComponent;
import org.kuali.kra.budget.bo.BudgetProjectIncome;
import org.kuali.kra.budget.bo.BudgetUnrecoveredFandA;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.rule.event.AddBudgetCostShareEvent;
import org.kuali.kra.budget.rule.event.AddBudgetProjectIncomeEvent;
import org.kuali.kra.budget.rule.event.AddBudgetUnrecoveredFandAEvent;
import org.kuali.kra.budget.service.BudgetDistrubutionAndIncomeService;
import org.kuali.kra.budget.service.impl.BudgetDistributionAndIncomeServiceImpl;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.Constants;

public class BudgetDistributionAndIncomeAction extends BudgetAction {
    private static final Log LOG = LogFactory.getLog(BudgetDistributionAndIncomeAction.class);
   
    private BudgetDistrubutionAndIncomeService bdiService;
    
    public BudgetDistributionAndIncomeAction() {
        super();
        setBudgetDistributionAndIncomeService(new BudgetDistributionAndIncomeServiceImpl());
    }

    /**
     * Override to intialize defaults after reload
     * 
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#reload(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = super.reload(mapping, form, request, response);
        bdiService.initializeCollectionDefaults(((BudgetForm) form).getBudgetDocument());
        return forward;
    }
    
    public ActionForward addCostShare(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form; 
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        BudgetCostShare budgetCostShare = budgetForm.getNewBudgetCostShare();
        boolean passed = getKualiRuleService().applyRules(createAddRuleEvent(budgetForm, budgetCostShare));
        
        if(passed) {
            BudgetDecimal defaultValue = new BudgetDecimal(0.0);
            if(budgetCostShare.getFiscalYear() == null) {
                budgetCostShare.setFiscalYear(0);
            }
            if(budgetCostShare.getShareAmount() == null) {
                BudgetDecimal shareAmount = budgetCostShare.getFiscalYear() == 0 ? defaultValue : budgetDocument.findCostSharingForFiscalYear(budgetCostShare.getFiscalYear());                 
                budgetCostShare.setShareAmount(shareAmount);
            }
            if(budgetCostShare.getSharePercentage() == null) {
                budgetCostShare.setSharePercentage(defaultValue);
            }
                       
            budgetForm.getBudgetDocument().add(budgetCostShare);
            budgetForm.setNewBudgetCostShare(new BudgetCostShare());
            LOG.debug("Added new BudgetCostShare: " + budgetCostShare);
        }  

        return mapping.findForward(MAPPING_BASIC);
    }
    
    public ActionForward addProjectIncome(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetProjectIncome budgetProjectIncome = budgetForm.getNewBudgetProjectIncome();
        boolean passed = getKualiRuleService().applyRules(createRuleEvent(budgetForm, budgetProjectIncome));
        
        if(passed) {
            budgetForm.getBudgetDocument().add(budgetProjectIncome);
            budgetForm.setNewBudgetProjectIncome(new BudgetProjectIncome());
            LOG.debug("Added new BudgetProjectIncome: " + budgetProjectIncome);
        }  

        return mapping.findForward(MAPPING_BASIC);
    }

    public ActionForward addUnrecoveredFandA(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form; 
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        BudgetUnrecoveredFandA budgetUnrecoveredFandA = budgetForm.getNewBudgetUnrecoveredFandA();
        boolean passed = getKualiRuleService().applyRules(createRuleEvent(budgetForm, budgetUnrecoveredFandA));
        
        if(passed) {;
            if(budgetUnrecoveredFandA.getFiscalYear() == null) {
                budgetUnrecoveredFandA.setFiscalYear(0);
            }
            if(budgetUnrecoveredFandA.getAmount() == null) {
                BudgetDecimal shareAmount = budgetUnrecoveredFandA.getFiscalYear() == 0 ? BudgetDecimal.ZERO : budgetDocument.findUnrecoveredFandAForFiscalYear(budgetUnrecoveredFandA.getFiscalYear());                 
                budgetUnrecoveredFandA.setAmount(shareAmount);
            }
            if(budgetUnrecoveredFandA.getApplicableRate() == null) {
                budgetUnrecoveredFandA.setApplicableRate(RateDecimal.ZERO_RATE);
            }
                       
            budgetForm.getBudgetDocument().add(budgetUnrecoveredFandA);
            budgetForm.setNewBudgetUnrecoveredFandA(new BudgetUnrecoveredFandA());
            LOG.debug("Added new BudgetCostShare: " + budgetUnrecoveredFandA);
        }  

        return mapping.findForward(MAPPING_BASIC);
    }

    public ActionForward deleteCostShare(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ((BudgetForm) form).getBudgetDocument().getBudgetCostShares().remove(getLineToDelete(request));        
        return mapping.findForward(MAPPING_BASIC);
    }
    
    public ActionForward deleteProjectIncome(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ((BudgetForm) form).getBudgetDocument().getBudgetProjectIncomes().remove(getLineToDelete(request));        
        return mapping.findForward(MAPPING_BASIC);
    }
    
    public ActionForward deleteUnrecoveredFandA(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ((BudgetForm) form).getBudgetDocument().getBudgetUnrecoveredFandAs().remove(getLineToDelete(request));        
        return mapping.findForward(MAPPING_BASIC);
    }
    
    public ActionForward refreshTotals(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // recalculation occurs on page render
        return mapping.findForward(MAPPING_BASIC);
    }
    
    public ActionForward resetCostSharingToDefault(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {        
        BudgetDocument budgetDocument = getBudgetDocument(form);
        budgetDocument.getBudgetCostShares().clear();
        bdiService.initializeCostSharingCollectionDefaults(budgetDocument);
        
        return mapping.findForward(MAPPING_BASIC);
    }

    public ActionForward resetUnrecoveredFandAToDefault(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetDocument budgetDocument = getBudgetDocument(form);
        budgetDocument.getBudgetUnrecoveredFandAs().clear();
        bdiService.initializeUnrecoveredFandACollectionDefaults(budgetDocument);
        
        return mapping.findForward(MAPPING_BASIC);
    }
    
    public void setBudgetDistributionAndIncomeService(BudgetDistributionAndIncomeServiceImpl bdiService) {
        this.bdiService = bdiService;
    }
    
    private AddBudgetCostShareEvent createAddRuleEvent(BudgetForm budgetForm, BudgetDistributionAndIncomeComponent budgetCostShare) {
        return new AddBudgetCostShareEvent("Add BudgetCostShare Event", Constants.EMPTY_STRING, budgetForm.getBudgetDocument(), budgetCostShare);
    }
    
    private AddBudgetProjectIncomeEvent createRuleEvent(BudgetForm budgetForm, BudgetProjectIncome budgetProjectIncome) {
        return new AddBudgetProjectIncomeEvent("Add BudgetProjectIncome Event", Constants.EMPTY_STRING, budgetForm.getBudgetDocument(), budgetProjectIncome);
    }
    
    private AddBudgetUnrecoveredFandAEvent createRuleEvent(BudgetForm budgetForm, BudgetUnrecoveredFandA budgetUnrecoveredFandA) {
        return new AddBudgetUnrecoveredFandAEvent("Add BudgetUnrecoveredFandA Event", Constants.EMPTY_STRING, budgetForm.getBudgetDocument(), budgetUnrecoveredFandA);
    }
    
    private BudgetDocument getBudgetDocument(ActionForm form) {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        return budgetDocument;
    }
    
    private KualiRuleService getKualiRuleService() {
        return getService(KualiRuleService.class);
    }
}
