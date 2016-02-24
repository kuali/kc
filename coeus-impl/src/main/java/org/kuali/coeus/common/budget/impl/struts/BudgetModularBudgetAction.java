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

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.budget.modular.BudgetModular;
import org.kuali.coeus.propdev.impl.budget.modular.BudgetModularIdc;
import org.kuali.coeus.propdev.impl.budget.modular.BudgetModularService;
import org.kuali.coeus.propdev.impl.budget.modular.SyncModularBudgetKcEvent;
import org.kuali.coeus.sys.framework.controller.StrutsConfirmation;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.core.BudgetForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.service.KualiRuleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;
import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;
import static org.kuali.rice.krad.util.KRADConstants.QUESTION_INST_ATTRIBUTE_NAME;

public class BudgetModularBudgetAction extends BudgetAction {

    private static final String CONFIRM_SYNC_BUDGET_MODULAR = "confirmSyncBudgetModular";
    
    public ActionForward updateView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        generateModularPeriod(budgetForm);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudget();        
        BudgetModularIdc newBudgetModularIdc = budgetForm.getNewBudgetModularIdc();
        newBudgetModularIdc.setRateNumber(budget.getNextValue("rateNumber"));
        newBudgetModularIdc.calculateFundsRequested();
        BudgetModular budgetModular = budget.getBudgetPeriods().get(budgetForm.getModularSelectedPeriod() - 1).getBudgetModular();
        budgetModular.addNewBudgetModularIdc(newBudgetModularIdc);
        generateModularPeriod(budgetForm);
        budgetForm.setNewBudgetModularIdc(new BudgetModularIdc());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudgetDocument().getBudget();        
        BudgetModular budgetModular = budget.getBudgetPeriods().get(budgetForm.getModularSelectedPeriod() - 1).getBudgetModular();
        budgetModular.getBudgetModularIdcs().remove(getLineToDelete(request));
        generateModularPeriod(budgetForm);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward recalculate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        if (!budgetForm.getModularSelectedPeriod().equals(0)) {
            generateModularPeriod(budgetForm);
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward sync(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean passed = getKcBusinessRulesEngine().applyRules(new SyncModularBudgetKcEvent((ProposalDevelopmentBudgetExt) ((BudgetForm) form).getBudget()));
        if (passed) {
            return confirm(buildSyncBudgetModularConfirmationQuestion(mapping, form, request, response), CONFIRM_SYNC_BUDGET_MODULAR, "");
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.reload(mapping, form, request, response);
        generateModularPeriod((BudgetForm) form);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.save(mapping, form, request, response);
        generateModularPeriod((BudgetForm) form);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     * This method is used to synch the modular budget
     * @param mapping The mapping associated with this action.
     * @param form The Budget form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the destination (always the original proposal web page that caused this action to be invoked)
     * @throws Exception
     */
    public ActionForward confirmSyncBudgetModular(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        Object question = request.getParameter(QUESTION_INST_ATTRIBUTE_NAME);
        if (CONFIRM_SYNC_BUDGET_MODULAR.equals(question)) {
            BudgetModularService budgetModularService = KcServiceLocator.getService(BudgetModularService.class);
            BudgetForm budgetForm = (BudgetForm) form;
            Budget budget = budgetForm.getBudgetDocument().getBudget();        
            budgetModularService.synchModularBudget(budget);
            budgetForm.setBudgetModularSummary(budgetModularService.processModularSummary(budget,false));
        }
        
        return mapping.findForward(MAPPING_BASIC);
    }
    
    private StrutsConfirmation buildSyncBudgetModularConfirmationQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_SYNC_BUDGET_MODULAR, KeyConstants.QUESTION_SYNC_BUDGET_MODULAR, "");
    }
    
    private void generateModularPeriod(BudgetForm budgetForm) {
        Budget budget = budgetForm.getBudgetDocument().getBudget();        
        BudgetModularService budgetModularService = KcServiceLocator.getService(BudgetModularService.class);
        if (budgetForm.getModularSelectedPeriod().equals(0)) {
            budgetForm.setBudgetModularSummary(budgetModularService.processModularSummary(budget,false));
            return;
        }
        BudgetPeriod budgetPeriod = budget.getBudgetPeriods().get(budgetForm.getModularSelectedPeriod() - 1);
        budgetModularService.generateModularPeriod(budgetPeriod);
        // Also update project totals
        budgetForm.setBudgetModularSummary(budgetModularService.processModularSummary(budget,false));
    }
    
    /**
     * Convenience method to allow stubbing
     * @return
     */
    protected KualiRuleService getKualiRuleService() {
        return getService(KualiRuleService.class);
    }
    
}
