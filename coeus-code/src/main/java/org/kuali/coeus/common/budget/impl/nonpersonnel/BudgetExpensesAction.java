/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.budget.impl.nonpersonnel;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.budget.framework.core.BudgetAction;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetService;
import org.kuali.coeus.common.budget.framework.core.BudgetDocument;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetExpenseRule;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetFormulatedCostDetail;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.print.BudgetPrintType;
import org.kuali.coeus.common.budget.framework.core.BudgetForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.coeus.common.budget.framework.print.BudgetPrintService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

public class BudgetExpensesAction extends BudgetAction {
    private static final Log LOG = LogFactory.getLog(BudgetExpensesAction.class);
    
    public ActionForward updateBudgetPeriodView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {                
        calculateCurrentBudgetPeriod((BudgetForm) form,false);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudgetDocument().getBudget();
        ActionForward forward = super.execute(mapping, form, request, response);
        // for fixing audit error
        if (budget.getBudgetCategoryTypeCodes() == null || budget.getBudgetCategoryTypeCodes().size() == 0) {
            populateNonPersonnelCategoryTypeCodes(budgetForm);
        }
        
        if(StringUtils.isNotBlank(budgetForm.getActivePanelName())) {
            populateTabState(budgetForm, budgetForm.getActivePanelName());
        }
        
        return forward;
        
    }


    /**
     * This method is used to add a new Budget Line Item
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward addBudgetLineItem(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        Integer budgetCategoryTypeIndex = Integer.parseInt(getBudgetCategoryTypeIndex(request));
        BudgetLineItem newBudgetLineItem = budgetForm.getNewBudgetLineItems().get(budgetCategoryTypeIndex);        
        Budget budget = budgetDocument.getBudget();
        
        BudgetService budgetService = KcServiceLocator.getService(BudgetService.class);
        
        if(budgetForm.getViewBudgetPeriod() == null || StringUtils.equalsIgnoreCase(budgetForm.getViewBudgetPeriod().toString(), "0")){
            GlobalVariables.getMessageMap().putError("viewBudgetPeriod", KeyConstants.ERROR_BUDGET_PERIOD_NOT_SELECTED);
        } else if(newBudgetLineItem.getCostElement() == null || StringUtils.equalsIgnoreCase(newBudgetLineItem.getCostElement(), "")){
            GlobalVariables.getMessageMap().putError("newBudgetLineItems[" + budgetCategoryTypeIndex + "].costElement", KeyConstants.ERROR_COST_ELEMENT_NOT_SELECTED);
        } else if(newBudgetLineItem.getCostElement() == null || StringUtils.equalsIgnoreCase(newBudgetLineItem.getCostElement(), "")){
            GlobalVariables.getMessageMap().putError("newBudgetLineItems[" + budgetCategoryTypeIndex + "].costElement", KeyConstants.ERROR_COST_ELEMENT_NOT_SELECTED);
        } else if (newBudgetLineItem.getQuantity() != null && newBudgetLineItem.getQuantity().intValue()<0) {
            GlobalVariables.getMessageMap().putError("newBudgetLineItems[" + budgetCategoryTypeIndex + "].quantity", KeyConstants.ERROR_NEGATIVE_AMOUNT,"Quantity");
        } else{
            Map<String, Object> primaryKeys = new HashMap<String, Object>();
            primaryKeys.put("budgetId", budget.getBudgetId());
            primaryKeys.put("budgetPeriod", budgetForm.getViewBudgetPeriod().toString());
            BusinessObjectService businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
            List<BudgetPeriod> budgetPeriods = budget.getBudgetPeriods();
            BudgetPeriod budgetPeriod = null;
            for (BudgetPeriod tempBudgetPeriod : budgetPeriods) {
                if(tempBudgetPeriod.getBudgetPeriod().equals(budgetForm.getViewBudgetPeriod())){
                    budgetPeriod = tempBudgetPeriod;
                }
            }

            budgetService.populateNewBudgetLineItem(newBudgetLineItem, budgetPeriod);
            budgetPeriod.getBudgetLineItems().add(newBudgetLineItem);            
            
            getCalculationService().populateCalculatedAmount(budget, newBudgetLineItem);
            recalculateBudgetPeriod(budgetForm,budget, budget.getBudgetPeriod(budgetPeriod.getBudgetPeriod() - 1));
            BudgetLineItem newLineItemToAdd = budgetPeriod.getNewBudgetLineItem();
            budgetForm.getNewBudgetLineItems().set(budgetCategoryTypeIndex, newLineItemToAdd);
            
            populateTabState(budgetForm, budgetService.getBudgetExpensePanelName(budgetPeriod, newBudgetLineItem));
        }  
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }


    private boolean isBudgetFormulatedCostEnabled() {
        String formulatedCostEnabled = getParameterService().getParameterValueAsString(BudgetDocument.class, Constants.FORMULATED_COST_ENABLED);
        return (formulatedCostEnabled!=null && formulatedCostEnabled.equalsIgnoreCase("Y"))?true:false;
    }
    private List<String> getFormulatedCostElements() {
        String formulatedCEsValue = getParameterService().getParameterValueAsString(BudgetDocument.class, Constants.FORMULATED_COST_ELEMENTS);
        String[] formulatedCEs = formulatedCEsValue==null?new String[0]:formulatedCEsValue.split(",");
        return Arrays.asList(formulatedCEs);
    }
    
    /**
     * This method is used to add a new Budget Line Item
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward addBudgetFormulatedCost(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        Budget budget = budgetDocument.getBudget();
        BudgetFormulatedCostDetail newBudgetFormulatedCost = budgetForm.getNewBudgetFormulatedCost();
        int lineItemNumber = getImagePropertyValue(request, ".budgetLineItemNumber",".");
        int budgetPeriod = getImagePropertyValue(request, ".budgetPeriod",".budgetLineItemNumber");
        if(new BudgetExpenseRule().processBudgetFormulatedCostValidations(newBudgetFormulatedCost,budgetPeriod,lineItemNumber,"newBudgetFormulatedCost")){
            BudgetPeriod budgetPeriodBO = budget.getBudgetPeriod(budgetPeriod-1);
            BudgetLineItem budgetLineItem = budgetPeriodBO.getBudgetLineItem(lineItemNumber);
            newBudgetFormulatedCost.setFormulatedNumber(budgetDocument.getHackedDocumentNextValue(Constants.BUDGET_FORMULATED_NUMBER));
            newBudgetFormulatedCost.setBudgetLineItemId(budgetLineItem.getBudgetLineItemId());
            calculateBudgetFormulatedCost(newBudgetFormulatedCost);
            budgetLineItem.getBudgetFormulatedCosts().add(newBudgetFormulatedCost);
            budgetForm.setNewBudgetFormulatedCost(new BudgetFormulatedCostDetail());
            budgetLineItem.setLineItemCost(getFormulatedCostsTotal(budgetLineItem));
            recalculateBudgetPeriod(budgetForm, budget, budgetPeriodBO);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }


    private ScaleTwoDecimal getFormulatedCostsTotal(BudgetLineItem budgetLineItem) {
        List<BudgetFormulatedCostDetail> budgetFormulatedCosts = budgetLineItem.getBudgetFormulatedCosts();
        ScaleTwoDecimal formulatedExpenses = ScaleTwoDecimal.ZERO;
        for (BudgetFormulatedCostDetail budgetFormulatedCostDetail : budgetFormulatedCosts) {
            calculateBudgetFormulatedCost(budgetFormulatedCostDetail);
            formulatedExpenses = formulatedExpenses.add(budgetFormulatedCostDetail.getCalculatedExpenses());
        }
        return formulatedExpenses;
    }

    private int getImagePropertyValue(HttpServletRequest request, String open,String close) {
        int selectedLine = -1;
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            String lineNumber = StringUtils.substringBetween(parameterName, open, close);
            if (StringUtils.isEmpty(lineNumber)) {
                return selectedLine;
            }
            selectedLine = Integer.parseInt(lineNumber);
        }
        return selectedLine;
    }


    private void calculateBudgetFormulatedCost( BudgetFormulatedCostDetail budgetFormulatedCost) {
        BigDecimal unitCost = budgetFormulatedCost.getUnitCost().bigDecimalValue();
        BigDecimal count = new ScaleTwoDecimal(budgetFormulatedCost.getCount()).bigDecimalValue();
        BigDecimal frequency = new ScaleTwoDecimal(budgetFormulatedCost.getFrequency()).bigDecimalValue();
        BigDecimal calculatedExpense = unitCost.multiply(count).multiply(frequency);
        budgetFormulatedCost.setCalculatedExpenses(new ScaleTwoDecimal(calculatedExpense));
    }


    /**
     * This method is used to add a new Budget Line Item
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward deleteBudgetFormulatedCost(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        Budget budget = budgetDocument.getBudget();
        int selectedLine = getSelectedLine(request);
        int lineItemNumber = getImagePropertyValue(request, ".budgetLineItemNumber",".");
        int budgetPeriod = getImagePropertyValue(request, ".budgetPeriod",".budgetLineItemNumber");
        BudgetPeriod budgetPeriodBO = budget.getBudgetPeriod(budgetPeriod-1);
        BudgetLineItem budgetLineItem = budgetPeriodBO.getBudgetLineItem(lineItemNumber);
        budgetLineItem.getBudgetFormulatedCosts().remove(selectedLine);
        budgetLineItem.setLineItemCost(getFormulatedCostsTotal(budgetLineItem));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    /**
     * 
     * This method is used to delete a line item
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteBudgetLineItem(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudgetDocument().getBudget();
        int sltdBudgetPeriod = budgetForm.getViewBudgetPeriod()-1;
        int sltdBudgetLineItem = getLineToDelete(request);
        BudgetPeriod budgetPeriod = budget.getBudgetPeriod(sltdBudgetPeriod);
        BudgetLineItem budgetLineItem = budgetPeriod.getBudgetLineItems().get(sltdBudgetLineItem);     
        BudgetService budgetService = KcServiceLocator.getService(BudgetService.class);
        
        if (new BudgetExpenseRule().processCheckExistBudgetPersonnelDetailsBusinessRules(budgetForm.getBudgetDocument(), 
                    budget.getBudgetPeriod(sltdBudgetPeriod).getBudgetLineItems().get(getLineToDelete(request)), getLineToDelete(request))) {
            budgetPeriod.getBudgetLineItems().remove(sltdBudgetLineItem);        
            recalculateBudgetPeriod(budgetForm, budget, budget.getBudgetPeriod(sltdBudgetPeriod));
            
            populateTabState(budgetForm, budgetService.getBudgetExpensePanelName(budgetPeriod, budgetLineItem));
        }
        return mapping.findForward("basic");
    }
    
    protected String getSelectedBudgetCategoryType(HttpServletRequest request) {
        String selectedCategoryTypeCode = "";
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            selectedCategoryTypeCode = StringUtils.substringBetween(parameterName, ".budgetCategoryTypeCode", ".");
        }
        return selectedCategoryTypeCode;
    }
    
    protected String getBudgetCategoryTypeIndex(HttpServletRequest request) {
        String selectedBudgetCategoryTypeIndex = "";
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            selectedBudgetCategoryTypeIndex = StringUtils.substringBetween(parameterName, ".catTypeIndex", ".");
        }
        return selectedBudgetCategoryTypeIndex;
    }
    
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        ActionForward actionForward = super.reload(mapping, form, request, response);
        populateNonPersonnelCategoryTypeCodes(budgetForm);
        
        return actionForward;
    }
 
    /**
     * This method is used to navigate it to personnel budget page
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward calculateCurrentPeriod(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        calculateCurrentBudgetPeriod(budgetForm,true);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    /**
     * This method is used to navigate it to personnel budget page
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward viewPersonnelSalaries(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm)form;
        Budget budget = budgetForm.getBudgetDocument().getBudget();
        BudgetPrintService budgetPrintService = KcServiceLocator.getService(BudgetPrintService.class);
        try{
            AttachmentDataSource dataStream = budgetPrintService.readBudgetPrintStream(budget, BudgetPrintType.BUDGET_SALARY_REPORT.getBudgetPrintType());
            streamToResponse(dataStream,response);
        }catch(Exception ex){
            LOG.error("Error getting salary report", ex);
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
        return null;
    }
    
    /**
     * This method overrides the save to update budget category code based on changed cost element
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudgetDocument().getBudget();
        List<String> formulatedCostElements = null;
        if (isBudgetFormulatedCostEnabled()) {
            formulatedCostElements = getFormulatedCostElements();
        } else {
            formulatedCostElements = new ArrayList<String>();
        }
        int budgetPeriodIndex = -1;
        for(BudgetPeriod budgetPeriod:budget.getBudgetPeriods()){
            ++budgetPeriodIndex;
            int lineItemNumber = -1;
            for(BudgetLineItem budgetLineItem:budgetPeriod.getBudgetLineItems()){
                ++lineItemNumber;
                if(!StringUtils.equalsIgnoreCase(budgetLineItem.getCostElement(), budgetLineItem.getCostElementBO().getCostElement())){
                    if(formulatedCostElements.contains(budgetLineItem.getCostElement())){
                        budgetLineItem.setFormulatedCostElementFlag(true);
                    }
                    budgetLineItem.refreshReferenceObject("costElementBO");
                    budgetLineItem.setBudgetCategoryCode(budgetLineItem.getCostElementBO().getBudgetCategoryCode());
                }
                if(processBudgetFormulatedCostValidations(budgetLineItem,budgetPeriodIndex,lineItemNumber)){
                    calculateAndUpdateFormulatedCost(budgetLineItem);
                }else{
                    return mapping.findForward(Constants.MAPPING_BASIC);
                }
                getCalculationService().updatePersonnelBudgetRate(budgetLineItem);
            }
        }
        
        if (new BudgetExpenseRule().processCheckLineItemDates(budgetForm.getBudgetDocument())) {
            ActionForward actionForward = super.save(mapping, form, request, response);
            budget.setBudgetLineItemDeleted(false);
            return actionForward;
        } else {
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
    }


    private boolean processBudgetFormulatedCostValidations(BudgetLineItem budgetLineItem, int budgetPeriodIndex,
            int lineItemNumber) {
        List<BudgetFormulatedCostDetail> budgetFormulatedCosts = budgetLineItem.getBudgetFormulatedCosts();
        boolean valid = true;
        int index = -1;
        String errorKey = "document.budget.budgetPeriod[" + budgetPeriodIndex + "].budgetLineItem["+ lineItemNumber + "].";
        for (BudgetFormulatedCostDetail budgetFormulatedCostDetail : budgetFormulatedCosts) {
            
            valid = valid&new BudgetExpenseRule().processBudgetFormulatedCostValidations(
                    budgetFormulatedCostDetail,budgetPeriodIndex,lineItemNumber,errorKey+"budgetFormulatedCosts["+(++index)+"]");
        }
        return valid;
    }


    private void calculateAndUpdateFormulatedCost(BudgetLineItem budgetLineItem) {
        if(budgetLineItem.getFormulatedCostElementFlag()){
            ScaleTwoDecimal formulatedCostTotal = getFormulatedCostsTotal(budgetLineItem);
            if(formulatedCostTotal!=null){
                budgetLineItem.setLineItemCost(formulatedCostTotal);
            }
        }
    }
    
    /**
     * 
     * This method does the sync to Period Cost Limit
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward syncCostLimitYes(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudgetDocument().getBudget();        
        int sltdLineItem = getSelectedLine(request);
        int sltdBudgetPeriod = budgetForm.getViewBudgetPeriod()-1;
        BudgetPeriod budgetPeriod = budget.getBudgetPeriod(sltdBudgetPeriod);
        if (new BudgetExpenseRule().processCheckLineItemDates(budgetPeriod, sltdLineItem)) {
            getCalculationService().syncToPeriodCostLimit(budget, budget.getBudgetPeriod(sltdBudgetPeriod), 
                    budget.getBudgetPeriod(sltdBudgetPeriod).getBudgetLineItem(sltdLineItem));
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    /**
     * 
     * This method does the sync to Period Cost Limit
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward syncDirectCostLimitYes(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudgetDocument().getBudget();        
        int sltdLineItem = getSelectedLine(request);
        int sltdBudgetPeriod = budgetForm.getViewBudgetPeriod()-1;
        BudgetPeriod budgetPeriod = budget.getBudgetPeriod(sltdBudgetPeriod);
        if (new BudgetExpenseRule().processCheckLineItemDates(budgetPeriod, sltdLineItem)) {
            getCalculationService().syncToPeriodDirectCostLimit(budget, budget.getBudgetPeriod(sltdBudgetPeriod), 
                    budget.getBudgetPeriod(sltdBudgetPeriod).getBudgetLineItem(sltdLineItem));
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward syncToPeriodCostLimit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudgetDocument().getBudget();        
        int sltdBudgetPeriod = budgetForm.getViewBudgetPeriod()-1;
        BudgetPeriod budgetPeriod = budget.getBudgetPeriod(sltdBudgetPeriod);
        if(budgetPeriod.getTotalCost().isGreaterThan(budgetPeriod.getTotalCostLimit())) {
            return confirm(buildParameterizedConfirmationQuestion(mapping, form, request, response, 
                        "syncCostLimitYes", "confirmation.periodTotal.greaterThan.costLimit"),
                        "syncCostLimitYes","");
        }else{
            return syncCostLimitYes(mapping, form, request, response);
        }
    }
    public ActionForward syncToPeriodDirectCostLimit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudgetDocument().getBudget();        
        int sltdBudgetPeriod = budgetForm.getViewBudgetPeriod()-1;
        BudgetPeriod budgetPeriod = budget.getBudgetPeriod(sltdBudgetPeriod);
        if(budgetPeriod.getTotalDirectCost().isGreaterThan(budgetPeriod.getDirectCostLimit())) {
            return confirm(buildParameterizedConfirmationQuestion(mapping, form, request, response, 
                        "syncDirectCostLimitYes", "confirmation.periodTotalDirectCost.greaterThan.costLimit"),
                        "syncDirectCostLimitYes","");
        }else{
            return syncDirectCostLimitYes(mapping, form, request, response);
        }
    }
    
    /**
     * 
     * This method does the Apply to Later Periods
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward applyToLaterPeriods(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudgetDocument().getBudget();        
        int sltdLineItem = getSelectedLine(request);
        int sltdBudgetPeriod = budgetForm.getViewBudgetPeriod()-1;
        BudgetExpenseRule budgetExpenseRule = new BudgetExpenseRule();
        if (budgetExpenseRule.processApplyToLaterPeriodsWithPersonnelDetails(budgetForm.getBudgetDocument(), 
                    budget.getBudgetPeriod(sltdBudgetPeriod), budget.getBudgetPeriod(sltdBudgetPeriod).getBudgetLineItem(sltdLineItem), sltdLineItem) &&
            budgetExpenseRule.processCheckLineItemDates(budget.getBudgetPeriod(sltdBudgetPeriod), sltdLineItem)) {
            getCalculationService().applyToLaterPeriods(budget, budget.getBudgetPeriod(sltdBudgetPeriod), budget.getBudgetPeriod(sltdBudgetPeriod).getBudgetLineItem(sltdLineItem));
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    private void calculateCurrentBudgetPeriod(BudgetForm budgetForm,boolean forceCalculation) {
        Budget budget = budgetForm.getBudgetDocument().getBudget();        
        int selectedPeriod = budgetForm.getViewBudgetPeriod().intValue();
        BudgetPeriod budgetPeriod = budget.getBudgetPeriod(selectedPeriod-1);
        budgetPeriod.setBudget(budget);
        for(BudgetLineItem budgetLineItem:budgetPeriod.getBudgetLineItems()){
            getCalculationService().updatePersonnelBudgetRate(budgetLineItem);
            if(budgetLineItem.getFormulatedCostElementFlag()){
                calculateAndUpdateFormulatedCost(budgetLineItem);
            }
        }
        if (new BudgetExpenseRule().processCheckLineItemDates(budgetForm.getBudgetDocument())) {
            if(forceCalculation){
                recalculateBudgetPeriod(budgetForm, budget, budgetPeriod);
            }else{
                calculateBudgetPeriod(budget, budgetPeriod);
            }
        }

    }
}
