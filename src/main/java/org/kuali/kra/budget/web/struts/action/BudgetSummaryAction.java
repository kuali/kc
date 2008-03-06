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

import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.core.service.KualiRuleService;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.rule.event.AddBudgetPeriodEvent;
import org.kuali.kra.budget.rule.event.DeleteBudgetPeriodEvent;
import org.kuali.kra.budget.rule.event.GenerateBudgetPeriodEvent;
import org.kuali.kra.budget.rule.event.SaveBudgetPeriodEvent;
import org.kuali.kra.budget.service.BudgetCalculationService;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.Constants;

public class BudgetSummaryAction extends BudgetAction {
    private static final Log LOG = LogFactory.getLog(BudgetSummaryAction.class);

    
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        boolean rulePassed = getKualiRuleService().applyRules(new SaveBudgetPeriodEvent(Constants.EMPTY_STRING, budgetForm.getBudgetDocument()));
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        if(rulePassed){
            /* calculate all periods */
            budgetForm.getBudgetDocument().getBudgetSummaryService().calculateBudget(budgetDocument);
            return super.save(mapping, form, request, response);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    
    /**
     * This method is used to add a new Budget Period
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward addBudgetPeriod(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetPeriod newBudgetPeriod = budgetForm.getNewBudgetPeriod();
        //List<BudgetPeriod> budgetPeriods =  budgetForm.getBudgetDocument().getBudgetPeriods();
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        if(getKualiRuleService().applyRules(new AddBudgetPeriodEvent(Constants.EMPTY_STRING, budgetForm.getBudgetDocument(), newBudgetPeriod))){
            budgetForm.getBudgetDocument().getBudgetSummaryService().addBudgetPeriod(budgetDocument, newBudgetPeriod);
            /* set new period and calculate all periods */
            budgetForm.setNewBudgetPeriod(new BudgetPeriod());
            budgetForm.getBudgetDocument().getBudgetSummaryService().calculateBudget(budgetDocument);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method is used to delete an existing Budget Period
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward deleteBudgetPeriod(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        int delPeriod = getLineToDelete(request);
        if(getKualiRuleService().applyRules(new DeleteBudgetPeriodEvent(Constants.EMPTY_STRING, budgetForm.getBudgetDocument(), delPeriod))){
            budgetForm.getBudgetDocument().getBudgetSummaryService().deleteBudgetPeriod(budgetDocument, delPeriod);
            /* calculate all periods */
            budgetForm.getBudgetDocument().getBudgetSummaryService().calculateBudget(budgetDocument);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method is used to recalculate Budget Period data
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward recalculateBudgetPeriod(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        /* calculate all periods */
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        budgetForm.getBudgetDocument().getBudgetSummaryService().calculateBudget(budgetDocument);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method is used to generate all Budget Period data
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward generateAllPeriods(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        boolean rulePassed = getKualiRuleService().applyRules(new GenerateBudgetPeriodEvent(Constants.EMPTY_STRING, budgetForm.getBudgetDocument()));
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        if(rulePassed){
            /* calculate first period - only period 1 exists at this point*/
            budgetForm.getBudgetDocument().getBudgetSummaryService().calculateBudget(budgetDocument);
            /* generate all periods */
            budgetForm.getBudgetDocument().getBudgetSummaryService().generateAllPeriods(budgetDocument);

            /* calculate all periods */
            budgetForm.getBudgetDocument().getBudgetSummaryService().calculateBudget(budgetDocument);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method is used to calculate all Budget Period data
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward calculateAllPeriods(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        /* calculate all periods */
        budgetForm.getBudgetDocument().getBudgetSummaryService().calculateBudget(budgetDocument);

        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    private KualiRuleService getKualiRuleService() {
        return getService(KualiRuleService.class);
    }

}
