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
import org.kuali.core.web.format.Formatter;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.RateClassType;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.BudgetDecimalFormatter;
import org.kuali.kra.infrastructure.Constants;

public class BudgetRatesAction extends BudgetAction {
    private static final Log LOG = LogFactory.getLog(BudgetRatesAction.class);

    /**
     * Action called to reset budget rates for each panel.
     * 
     * @param mapping 
     * @param form
     * @param request
     * @param response
     * @return ActionForward instance for forwarding to the tab.
     */
    public ActionForward resetRates(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        RateClassType rateClassT = budgetDocument.getRateClassTypes().get(getSelectedLine(request));
        String rateClassType = rateClassT.getRateClassType();
        budgetDocument.getBudgetRatesService().resetBudgetRatesForRateClassType(rateClassType, budgetDocument);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * Action called to sync budget rates for each panel.
     * 
     * @param mapping 
     * @param form
     * @param request
     * @param response
     * @return ActionForward instance for forwarding to the tab.
     */
    public ActionForward syncRates(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        RateClassType rateClassT = budgetDocument.getRateClassTypes().get(getSelectedLine(request));
        String rateClassType = rateClassT.getRateClassType();
        budgetDocument.getBudgetRatesService().syncBudgetRatesForRateClassType(rateClassType, budgetDocument);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * Action called to sync all budget rates.
     * 
     * @param mapping 
     * @param form
     * @param request
     * @param response
     * @return ActionForward instance for forwarding to the tab.
     */
    public ActionForward syncAllRates(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        budgetDocument.getBudgetRatesService().syncAllBudgetRates(budgetDocument);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * Action called to reset all budget rates.
     * 
     * @param mapping 
     * @param form
     * @param request
     * @param response
     * @return ActionForward instance for forwarding to the tab.
     */
    public ActionForward resetAllRates(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        budgetDocument.getBudgetRatesService().resetAllBudgetRates(budgetDocument);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * Action called to filter on off campus.
     * 
     * @param mapping 
     * @param form
     * @param request
     * @param response
     * @return ActionForward instance for forwarding to the tab.
     */
    public ActionForward updateRatesView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        budgetDocument.getBudgetRatesService().viewLocation(budgetForm.getViewLocation(),budgetForm.getViewBudgetPeriod(), budgetDocument);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

}

