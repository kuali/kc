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
import org.kuali.coeus.sys.framework.controller.StrutsConfirmation;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetParentDocument;
import org.kuali.coeus.common.budget.framework.rate.BudgetRate;
import org.kuali.coeus.common.budget.framework.rate.BudgetRatesService;
import org.kuali.coeus.common.budget.framework.rate.RateClassType;
import org.kuali.coeus.common.budget.framework.core.BudgetForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.kuali.rice.krad.util.KRADConstants.QUESTION_INST_ATTRIBUTE_NAME;

public class BudgetRatesAction extends BudgetAction {

    private static final String CONFIRM_SYNC_RATES = "confirmSyncRates";
    private static final String CONFIRM_SYNC_ALL_RATES = "confirmSyncAllRates";
    private static final String CONFIRM_RESET_RATES = "confirmResetRates";
    private static final String CONFIRM_RESET_ALL_RATES = "confirmResetAllRates";
    private static final String CONFIRM_RATE_CHANGE = "confirmRateChange";
    private static final String REJECT_RATE_CHANGE = "rejectRateChange";

    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudget();
        // Temporary fix to resolve budget form NULL issue
        if(KNSGlobalVariables.getKualiForm() == null) {
            KNSGlobalVariables.setKualiForm((KualiForm)form);
        }
        budget.setRateSynced(false);
        
        if (haveRatesBeenChanged(budget)) {
            return askConfirmRateChange(mapping, budgetForm, request, response);
        }

        ActionForward forward = super.save(mapping, form, request, response);
        if ((!budgetForm.getMethodToCall().equals("save") && budgetForm.isAuditActivated())) forward = mapping.findForward("rates_save");
        return forward;
    }
    
    /**
     * 
     */
    public ActionForward resetRates(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        return confirm(buildResetRatesConfirmationQuestion(mapping, form, request, response), CONFIRM_RESET_RATES, "");
    }
    
    /**
     * 
     */
    
    private StrutsConfirmation buildResetRatesConfirmationQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_RESET_RATES, 
                KeyConstants.QUESTION_RESET_RATES, "");
    }
    
    /**
     * Action called to reset budget rates for each panel.
     * 
     * @param mapping 
     * @param form
     * @param request
     * @param response
     * @return ActionForward instance for forwarding to the tab.
     */
    public ActionForward confirmResetRates(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        
        Object question = request.getParameter(QUESTION_INST_ATTRIBUTE_NAME);
        if (CONFIRM_RESET_RATES.equals(question)) {
            BudgetForm budgetForm = (BudgetForm) form;
            Budget budget = budgetForm.getBudget();
            RateClassType rateClassT = budget.getRateClassTypes().get(getSelectedLine(request));
            String rateClassType = rateClassT.getCode();
            budget.getBudgetRatesService().resetBudgetRatesForRateClassType(rateClassType, budget);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     */
    public ActionForward syncRates(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        return confirm(buildSyncRatesConfirmationQuestion(mapping, form, request, response), CONFIRM_SYNC_RATES, "");
    }
    
    /**
     * 
     */
    private StrutsConfirmation buildSyncRatesConfirmationQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_SYNC_RATES, 
                KeyConstants.QUESTION_SYNC_RATES, "");
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
    public ActionForward confirmSyncRates(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        
        Object question = request.getParameter(QUESTION_INST_ATTRIBUTE_NAME);
        if (CONFIRM_SYNC_RATES.equals(question)) {
            BudgetForm budgetForm = (BudgetForm) form;
            Budget budget = budgetForm.getBudget();
            RateClassType rateClassT = budget.getRateClassTypes().get(getSelectedLine(request));
            String rateClassType = rateClassT.getCode();
            getBudgetRatesService().syncBudgetRatesForRateClassType(rateClassType, budget);
            budget.setRateClassTypesReloaded(false);
            if (rateClassType.equals("O")) {
                budget.setRateSynced(true);
            }
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    private BudgetRatesService getBudgetRatesService() {
        return KcServiceLocator.getService(BudgetRatesService.class);
    }

    /**
     * 
     */
    public ActionForward syncAllRates(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        return confirm(buildSyncAllRatesConfirmationQuestion(mapping, form, request, response), CONFIRM_SYNC_ALL_RATES, "");
    }
    
    /**
     * 
     */
     private StrutsConfirmation buildSyncAllRatesConfirmationQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
             HttpServletResponse response) throws Exception {
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_SYNC_ALL_RATES, 
                KeyConstants.QUESTION_SYNC_ALL_RATES, "");
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
    public ActionForward confirmSyncAllRates(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        
        Object question = request.getParameter(QUESTION_INST_ATTRIBUTE_NAME);
        if (CONFIRM_SYNC_ALL_RATES.equals(question)) {
            BudgetForm budgetForm = (BudgetForm) form;
            Budget budget = budgetForm.getBudget();

            //Rates-Refresh Scenario-4
            budget.setRateClassTypesReloaded(true);
            budget.getBudgetRatesService().syncAllBudgetRates(budget);
            
            budget.setRateSynced(true);
            BudgetParentDocument parentDocument = budget.getBudgetParent().getDocument();
            if (!budget.getActivityTypeCode().equals(parentDocument.getBudgetParent().getActivityTypeCode())) {
                budget.setActivityTypeCode(parentDocument.getBudgetParent().getActivityTypeCode());
            }
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     */
    public ActionForward resetAllRates(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        return confirm(buildResetAllRatesConfirmationQuestion(mapping, form, request, response), CONFIRM_RESET_ALL_RATES, "");
    }
    
    /**
     * 
     */
    private StrutsConfirmation buildResetAllRatesConfirmationQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, 
                CONFIRM_RESET_ALL_RATES, KeyConstants.QUESTION_RESET_ALL_RATES, "");
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
    public ActionForward confirmResetAllRates(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        
        Object question = request.getParameter(QUESTION_INST_ATTRIBUTE_NAME);
        if (CONFIRM_RESET_ALL_RATES.equals(question)) {
            BudgetForm budgetForm = (BudgetForm) form;
            Budget budget = budgetForm.getBudget();
            getBudgetRatesService().resetAllBudgetRates(budget);
        }
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
    public ActionForward updateRatesView(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudget();
        getBudgetRatesService().viewLocation(budgetForm.getViewLocation(),budgetForm.getViewBudgetPeriod(), budget);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    private boolean haveRatesBeenChanged(Budget budget) {
        for (BudgetRate rate : budget.getBudgetRates()) {
            if (rate.isRateChanged()) {
                return true;
            }
        }
        return false;
    }
    
    public ActionForward askConfirmRateChange(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        return confirm(buildParameterizedConfirmationQuestion(mapping, form, request, response, 
                CONFIRM_RATE_CHANGE, KeyConstants.QUESTION_RATE_CHANGED), CONFIRM_RATE_CHANGE, REJECT_RATE_CHANGE);        
    }
    
    public ActionForward confirmRateChange(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudgetDocument().getBudget();
        for (BudgetRate rate : budget.getBudgetRates()) {
            rate.setRateChanged(false);
        }
        return this.save(mapping, budgetForm, request, response);
    }
    
    public ActionForward rejectRateChange(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudgetDocument().getBudget();
        for (BudgetRate rate : budget.getBudgetRates()) {
            rate.setRateChanged(false);
            rate.setExactApplicableRate(rate.getOldApplicableRate());
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    
}

