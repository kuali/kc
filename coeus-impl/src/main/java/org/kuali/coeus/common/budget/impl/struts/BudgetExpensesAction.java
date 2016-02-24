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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.budget.framework.core.*;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.budget.framework.nonpersonnel.ApplyToPeriodsBudgetEvent;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetDirectCostLimitEvent;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetExpenseService;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetFormulatedCostDetail;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetPeriodCostLimitEvent;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.print.BudgetPrintType;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.coeus.common.budget.framework.print.BudgetPrintService;
import org.kuali.coeus.common.budget.impl.nonpersonnel.AddFormulatedCostBudgetEvent;
import org.kuali.coeus.common.budget.impl.nonpersonnel.DeleteBudgetLineItemEvent;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

public class BudgetExpensesAction extends BudgetAction {
    private static final Log LOG = LogFactory.getLog(BudgetExpensesAction.class);
    
    private BudgetExpenseService budgetExpenseService;
    
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
        AwardBudgetDocument awardBudgetDocument = budgetForm.getBudgetDocument();
        Integer budgetCategoryTypeIndex = Integer.parseInt(getBudgetCategoryTypeIndex(request));
        BudgetLineItem newBudgetLineItem = budgetForm.getNewBudgetLineItems().get(budgetCategoryTypeIndex);        
        Budget budget = awardBudgetDocument.getBudget();
        
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
            
            populateTabState(budgetForm, getBudgetExpenseService().getBudgetExpensePanelName(budgetPeriod, newBudgetLineItem));
        }  
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }


    private boolean isBudgetFormulatedCostEnabled() {
        String formulatedCostEnabled = getParameterService().getParameterValueAsString(AwardBudgetDocument.class, Constants.FORMULATED_COST_ENABLED);
        return (formulatedCostEnabled!=null && formulatedCostEnabled.equalsIgnoreCase("Y"))?true:false;
    }
    private List<String> getFormulatedCostElements() {
        String formulatedCEsValue = getParameterService().getParameterValueAsString(AwardBudgetDocument.class, Constants.FORMULATED_COST_ELEMENTS);
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
        Budget budget = budgetForm.getBudget();
        BudgetFormulatedCostDetail newBudgetFormulatedCost = budgetForm.getNewBudgetFormulatedCost();
        int lineItemNumber = getImagePropertyValue(request, ".budgetLineItemNumber",".");
        int budgetPeriod = getImagePropertyValue(request, ".budgetPeriod",".budgetLineItemNumber");
        if (getKcBusinessRulesEngine().applyRules(new AddFormulatedCostBudgetEvent(budget, "newBudgetFormulatedCost", newBudgetFormulatedCost))) {
            BudgetPeriod budgetPeriodBO = budget.getBudgetPeriod(budgetPeriod-1);
            BudgetLineItem budgetLineItem = budgetPeriodBO.getBudgetLineItem(lineItemNumber);
            newBudgetFormulatedCost.setFormulatedNumber(budget.getNextValue(Constants.BUDGET_FORMULATED_NUMBER));
            newBudgetFormulatedCost.setBudgetLineItem(budgetLineItem);
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
        Budget budget = budgetForm.getBudget();
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
        
        if (getKcBusinessRulesEngine().applyRules(new DeleteBudgetLineItemEvent(budget, 
        		"document.budgetPeriod[" + (budgetLineItem.getBudgetPeriod() - 1) + "].budgetLineItem[" + sltdBudgetLineItem + "]",
        		budgetLineItem))) {
            budgetPeriod.getBudgetLineItems().remove(sltdBudgetLineItem);        
            recalculateBudgetPeriod(budgetForm, budget, budget.getBudgetPeriod(sltdBudgetPeriod));
            
            populateTabState(budgetForm, getBudgetExpenseService().getBudgetExpensePanelName(budgetPeriod, budgetLineItem));
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
                calculateAndUpdateFormulatedCost(budgetLineItem);
                getCalculationService().updatePersonnelBudgetRate(budgetLineItem);
            }
        }
                    
        if (getKcBusinessRulesEngine().applyRules(new BudgetSaveEvent(budget))) {
            ActionForward actionForward = super.save(mapping, form, request, response);
            budget.setBudgetLineItemDeleted(false);
            return actionForward;
        } else {
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
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
        BudgetLineItem budgetLineItem = budgetPeriod.getBudgetLineItem(sltdLineItem);
        String errorPath = "document.budgetPeriod[" + sltdBudgetPeriod + "].budgetLineItem[" + sltdLineItem + "]";
        boolean rulePassed = getKcBusinessRulesEngine().applyRules(new ApplyToPeriodsBudgetEvent(budget, errorPath, 
        		budgetLineItem, budgetPeriod));
    	rulePassed &= getKcBusinessRulesEngine().applyRules(new BudgetPeriodCostLimitEvent(budget, budgetPeriod, budgetLineItem, errorPath));
        
        if (rulePassed) {
            boolean syncComplete = getCalculationService().syncToPeriodCostLimit(budget, budgetPeriod, budgetLineItem);
            if(!syncComplete) {
            	GlobalVariables.getMessageMap().putError(errorPath, KeyConstants.INSUFFICIENT_AMOUNT_TO_SYNC);
            }
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
        BudgetLineItem budgetLineItem = budgetPeriod.getBudgetLineItem(sltdLineItem);
        String errorPath = "document.budgetPeriod[" + sltdBudgetPeriod + "].budgetLineItem[" + sltdLineItem + "]";
        boolean rulePassed = getKcBusinessRulesEngine().applyRules(new ApplyToPeriodsBudgetEvent(budget, errorPath, budgetLineItem, budgetPeriod));
    	rulePassed &= getKcBusinessRulesEngine().applyRules(new BudgetDirectCostLimitEvent(budget, budgetPeriod, budgetLineItem, errorPath));
        if (rulePassed) {
        	boolean syncComplete = getCalculationService().syncToPeriodDirectCostLimit(budget, budgetPeriod, budgetLineItem);
            if(!syncComplete) {
            	GlobalVariables.getMessageMap().putError(errorPath, KeyConstants.INSUFFICIENT_AMOUNT_TO_PERIOD_DIRECT_COST_LIMIT_SYNC);
            }
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
        BudgetPeriod budgetPeriod = budget.getBudgetPeriod(sltdBudgetPeriod);
        if (getKcBusinessRulesEngine().applyRules(new ApplyToPeriodsBudgetEvent(budget, 
        		"document.budgetPeriod[" + sltdBudgetPeriod + "].budgetLineItem[" + sltdLineItem + "]", 
        		budgetPeriod.getBudgetLineItem(sltdLineItem), budgetPeriod))) {
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
        if (getKcBusinessRulesEngine().applyRules(new AwardBudgetSaveEvent(budget))) {
            if(forceCalculation){
                recalculateBudgetPeriod(budgetForm, budget, budgetPeriod);
            }else{
                calculateBudgetPeriod(budget, budgetPeriod);
            }
        }

    }

	public BudgetExpenseService getBudgetExpenseService() {
		if (budgetExpenseService == null) {
			budgetExpenseService = KcServiceLocator.getService(BudgetExpenseService.class);
		}
		return budgetExpenseService;
	}


	public void setBudgetExpenseService(BudgetExpenseService budgetExpenseService) {
		this.budgetExpenseService = budgetExpenseService;
	}
}
