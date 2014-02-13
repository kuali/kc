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
package org.kuali.kra.budget.web.struts.action;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.sys.framework.controller.StrutsConfirmation;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.budget.modular.BudgetModular;
import org.kuali.kra.proposaldevelopment.budget.modular.BudgetModularIdc;
import org.kuali.kra.proposaldevelopment.budget.modular.BudgetModularService;
import org.kuali.kra.proposaldevelopment.budget.modular.SyncModularBudgetEvent;
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
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        Budget budget = budgetDocument.getBudget();        
        BudgetModularIdc newBudgetModularIdc = budgetForm.getNewBudgetModularIdc();
        newBudgetModularIdc.setRateNumber(budgetDocument.getHackedDocumentNextValue("rateNumber"));
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
        boolean passed = getKualiRuleService().applyRules(createSyncModularBudgetEvent(((BudgetForm)form).getBudgetDocument()));
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
            budgetForm.setBudgetModularSummary(budgetModularService.generateModularSummary(budget));
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
            budgetForm.setBudgetModularSummary(budgetModularService.generateModularSummary(budget));
            return;
        }
        BudgetPeriod budgetPeriod = budget.getBudgetPeriods().get(budgetForm.getModularSelectedPeriod() - 1);
        budgetModularService.generateModularPeriod(budgetPeriod);
        // Also update project totals
        budgetForm.setBudgetModularSummary(budgetModularService.generateModularSummary(budget));
    }
    
    protected SyncModularBudgetEvent createSyncModularBudgetEvent(BudgetDocument budgetDocument) {
        return new SyncModularBudgetEvent("SyncModularBudgetEvent", Constants.EMPTY_STRING, budgetDocument);
    }
    
    /**
     * Convenience method to allow stubbing
     * @return
     */
    protected KualiRuleService getKualiRuleService() {
        return getService(KualiRuleService.class);
    }
    
}
