/*
 * Copyright 2006-2009 The Kuali Foundation
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

import static org.kuali.rice.kns.util.KNSConstants.QUESTION_INST_ATTRIBUTE_NAME;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.BudgetParentDocument;
import org.kuali.kra.budget.rates.BudgetRatesService;
import org.kuali.kra.budget.rates.RateClassType;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.web.struts.action.StrutsConfirmation;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiForm;

public class BudgetRatesAction extends BudgetAction {
    private static final Log LOG = LogFactory.getLog(BudgetRatesAction.class);

    private static final String CONFIRM_SYNC_RATES = "confirmSyncRates";
    private static final String CONFIRM_SYNC_ALL_RATES = "confirmSyncAllRates";
    private static final String CONFIRM_RESET_RATES = "confirmResetRates";
    private static final String CONFIRM_RESET_ALL_RATES = "confirmResetAllRates";

    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getDocument();
        Budget budget = budgetDocument.getBudget();
        // Temporary fix to resolve budget form NULL issue
        if(GlobalVariables.getKualiForm() == null) {
            GlobalVariables.setKualiForm((KualiForm)form);
        }
        budget.setRateSynced(false);

        ActionForward forward = super.save(mapping, form, request, response);
        if (!(budgetForm.getMethodToCall().equals("save") && budgetForm.isAuditActivated())) forward = mapping.findForward("rates_save");
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
            BudgetDocument budgetDocument = budgetForm.getDocument();
            Budget budget = budgetDocument.getBudget();
            RateClassType rateClassT = budget.getRateClassTypes().get(getSelectedLine(request));
            String rateClassType = rateClassT.getRateClassType();
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
            BudgetDocument budgetDocument = budgetForm.getDocument();
            Budget budget = budgetDocument.getBudget();
            RateClassType rateClassT = budget.getRateClassTypes().get(getSelectedLine(request));
            String rateClassType = rateClassT.getRateClassType();
            getBudgetRatesService().syncBudgetRatesForRateClassType(rateClassType, budgetDocument);
            budget.setRateClassTypesReloaded(false);
            if (rateClassType.equals("O")) {
                budget.setRateSynced(true);
            }
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    private BudgetRatesService getBudgetRatesService() {
        return KraServiceLocator.getService(BudgetRatesService.class);
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
            BudgetDocument budgetDocument = budgetForm.getDocument();
            Budget budget = budgetDocument.getBudget();

            //Rates-Refresh Scenario-4
            budget.setRateClassTypesReloaded(true);
            budget.getBudgetRatesService().syncAllBudgetRates(budgetDocument);
            
            budget.setRateSynced(true);
            BudgetParentDocument parentDocument = budgetDocument.getParentDocument();
            if (!budget.getActivityTypeCode().equals(parentDocument.getActivityTypeCode())) {
                budget.setActivityTypeCode(parentDocument.getActivityTypeCode());
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
            BudgetDocument budgetDocument = budgetForm.getDocument();
            Budget budget = budgetDocument.getBudget();
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
        BudgetDocument budgetDocument = budgetForm.getDocument();
        Budget budget = budgetDocument.getBudget();
        getBudgetRatesService().viewLocation(budgetForm.getViewLocation(),budgetForm.getViewBudgetPeriod(), budget);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

}

