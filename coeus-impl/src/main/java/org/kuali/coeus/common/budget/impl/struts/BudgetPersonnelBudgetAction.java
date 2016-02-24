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
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.category.BudgetCategoryTypeValuesFinder;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPerson;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelBudgetService;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelCalculatedAmount;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.coeus.common.budget.framework.core.BudgetForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class BudgetPersonnelBudgetAction extends BudgetAction {


    /**
     * This method is used to navigate it to personnel budget page
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward updatePersonnelBudgetView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward(Constants.MAPPING_PERSONNEL_BUDGET);
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
    public ActionForward addBudgetPersonnelDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudget();
        int selectedBudgetPeriodIndex = budgetForm.getViewBudgetPeriod()-1;
        int selectedBudgetLineItemIndex = budgetForm.getSelectedBudgetLineItemIndex();
        BudgetPersonnelDetails newBudgetPersonnelDetails = budgetForm.getNewBudgetPersonnelDetails();
        
        boolean errorFound = false;
        if(newBudgetPersonnelDetails.getPercentEffort().isGreaterThan(new ScaleTwoDecimal(100))){
            GlobalVariables.getMessageMap().putError("newBudgetPersonnelDetails.percentEffort",KeyConstants.ERROR_PERCENTAGE, Constants.PERCENT_EFFORT_FIELD);
            errorFound=true;
        }
        if(newBudgetPersonnelDetails.getPercentCharged().isGreaterThan(new ScaleTwoDecimal(100))){
            GlobalVariables.getMessageMap().putError("newBudgetPersonnelDetails.percentCharged",KeyConstants.ERROR_PERCENTAGE, Constants.PERCENT_CHARGED_FIELD);
            errorFound=true;
        }
        if(newBudgetPersonnelDetails.getPercentCharged().isGreaterThan(newBudgetPersonnelDetails.getPercentEffort())){
            GlobalVariables.getMessageMap().putError("newBudgetPersonnelDetails.percentCharged",KeyConstants.ERROR_PERCENT_EFFORT_LESS_THAN_PERCENT_CHARGED);
            errorFound=true;
        }
        if (StringUtils.isBlank((budgetForm.getNewBudgetPersonnelDetails().getPeriodTypeCode()))) {
            GlobalVariables.getMessageMap().putError("newBudgetPersonnelDetails.periodTypeCode", RiceKeyConstants.ERROR_REQUIRED, new String[] { "Period Type (Period Type)" });
            errorFound=true;
        }
        if (budgetForm.getNewBudgetPersonnelDetails().getPersonSequenceNumber() == null) {
            GlobalVariables.getMessageMap().putError("newBudgetPersonnelDetails.personSequenceNumber", RiceKeyConstants.ERROR_REQUIRED, new String[] { "Person (Person)" });
            errorFound=true;
        }

        HashMap uniqueBudgetPersonnelCount = new HashMap();
        int qty = 0;
        if(!errorFound){
            BudgetPersonnelBudgetService budgetPersonnelBudgetService = KcServiceLocator.getService(BudgetPersonnelBudgetService.class);
            for (BudgetPersonnelDetails budgetPersonnelDetails : budget.getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(selectedBudgetLineItemIndex).getBudgetPersonnelDetailsList()) {
                if(!uniqueBudgetPersonnelCount.containsValue(budgetPersonnelDetails.getPersonId())){
                    uniqueBudgetPersonnelCount.put(qty, budgetPersonnelDetails.getPersonId());
                    qty = qty + 1;
                }
            }    
            budget.getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(selectedBudgetLineItemIndex).setQuantity(new Integer(qty));
            updatePersonnelBudgetRate(budget.getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(selectedBudgetLineItemIndex));
            getCalculationService().calculateBudgetPeriod(budget, budget.getBudgetPeriod(selectedBudgetPeriodIndex));
        }        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }    

    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        //setBudgetPersonDefaultPeriodTypeCode(budgetForm);  
        ActionForward actionForward = super.reload(mapping, form, request, response);
        BusinessObjectService bos = KcServiceLocator.getService(BusinessObjectService.class);
        Budget budget = budgetForm.getBudget();
        Map queryMap = new HashMap();
        queryMap.put("budgetId", budget.getBudgetId());
        Collection budgetPersons = bos.findMatching(BudgetPerson.class, queryMap);
        budget.setBudgetPersons(budgetPersons==null?new ArrayList():(List)budgetPersons);
        
        BudgetCategoryTypeValuesFinder budgetCategoryTypeValuesFinder = new BudgetCategoryTypeValuesFinder();
        List<KeyValue> budgetCategoryTypes = new ArrayList<KeyValue>();        
        budgetCategoryTypes = budgetCategoryTypeValuesFinder.getKeyValues();
        for(int i=0;i<budgetCategoryTypes.size();i++){
            budgetForm.getNewBudgetLineItems().add(budget.getNewBudgetLineItem());
        }
        budget.setBudgetCategoryTypeCodes(budgetCategoryTypes);
        
        return actionForward;
    }
    
    public ActionForward deleteBudgetPersonnelDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudget();
        int selectedBudgetPeriodIndex = budgetForm.getViewBudgetPeriod()-1;
        int selectedBudgetLineItemIndex = budgetForm.getSelectedBudgetLineItemIndex();
        BudgetPersonnelBudgetService budgetPersonnelBudgetService = KcServiceLocator.getService(BudgetPersonnelBudgetService.class);
        budgetPersonnelBudgetService.deleteBudgetPersonnelDetails(budget, selectedBudgetPeriodIndex,selectedBudgetLineItemIndex, getLineToDelete(request));
        HashMap uniqueBudgetPersonnelCount = new HashMap();
        int qty = 0;
        for (BudgetPersonnelDetails budgetPersonnelDetails : budget.getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(selectedBudgetLineItemIndex).getBudgetPersonnelDetailsList()) {
            if(!uniqueBudgetPersonnelCount.containsValue(budgetPersonnelDetails.getPersonId())){
                uniqueBudgetPersonnelCount.put(qty, budgetPersonnelDetails.getPersonId());
                qty = qty + 1;
            }
        }    
        budget.getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(selectedBudgetLineItemIndex).setQuantity(new Integer(qty));
        
        getCalculationService().calculateBudgetPeriod(budget, budget.getBudgetPeriod(selectedBudgetPeriodIndex));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    /**
     * This method is used to return back to expenses tab
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward returnToExpenses(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudget();
        int selectedBudgetPeriodIndex = budgetForm.getViewBudgetPeriod()-1;
        int selectedBudgetLineItemIndex = budgetForm.getSelectedBudgetLineItemIndex();
        BudgetLineItem selectedBudgetLineItem = budget.getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(selectedBudgetLineItemIndex);
        int k=0;
        boolean errorFound = false;
        GlobalVariables.getMessageMap().addToErrorPath("document");
        for(BudgetPersonnelDetails budgetPersonnelDetails: selectedBudgetLineItem.getBudgetPersonnelDetailsList()){
           errorFound=errorFound || personnelDatesCheck(selectedBudgetLineItem, budgetPersonnelDetails, selectedBudgetPeriodIndex, selectedBudgetLineItemIndex, k);
            k++;
        }
        GlobalVariables.getMessageMap().removeFromErrorPath("document");
        if (errorFound) {
            return mapping.findForward(Constants.MAPPING_BASIC);
        } else {
            getCalculationService().calculateBudgetLineItem(budget, selectedBudgetLineItem);
            
            BudgetCategoryTypeValuesFinder budgetCategoryTypeValuesFinder = new BudgetCategoryTypeValuesFinder();
            List<KeyValue> budgetCategoryTypes = new ArrayList<KeyValue>();        
            budgetCategoryTypes = budgetCategoryTypeValuesFinder.getKeyValues();
            for(int i=0;i<budgetCategoryTypes.size();i++){
                budgetForm.getNewBudgetLineItems().add(budget.getNewBudgetLineItem());
            }
            budget.setBudgetCategoryTypeCodes(budgetCategoryTypes);
            request.setAttribute("fromPersonnelBudget"+budgetForm.getViewBudgetPeriod()+""+selectedBudgetLineItemIndex, true);
            
            return mapping.findForward("expenses");
        }
    }
    /**
     * This method calculates the salary
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward calculateLineItem(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudget();
        int selectedBudgetPeriodIndex = budgetForm.getViewBudgetPeriod()-1;
        int selectedBudgetLineItemIndex = budgetForm.getSelectedBudgetLineItemIndex();
        BudgetLineItem selectedBudgetLineItem = budget.getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(selectedBudgetLineItemIndex);
        int k=0;
        boolean errorFound = false;
        GlobalVariables.getMessageMap().addToErrorPath("document");
        for(BudgetPersonnelDetails budgetPersonnelDetails: selectedBudgetLineItem.getBudgetPersonnelDetailsList()){
            if(budgetPersonnelDetails.getPercentEffort().isGreaterThan(new ScaleTwoDecimal(100))){
                GlobalVariables.getMessageMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItems[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + k + "].percentEffort",KeyConstants.ERROR_PERCENTAGE, Constants.PERCENT_EFFORT_FIELD);
                errorFound=true;
            }
            if(budgetPersonnelDetails.getPercentCharged().isGreaterThan(new ScaleTwoDecimal(100))){
                GlobalVariables.getMessageMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItems[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + k + "].percentCharged",KeyConstants.ERROR_PERCENTAGE, Constants.PERCENT_CHARGED_FIELD);
                errorFound=true;
            }
            if(budgetPersonnelDetails.getPercentCharged().isGreaterThan(budgetPersonnelDetails.getPercentEffort())){
                GlobalVariables.getMessageMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItems[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + k + "].percentCharged",KeyConstants.ERROR_PERCENT_EFFORT_LESS_THAN_PERCENT_CHARGED);
                errorFound=true;
            }
            errorFound=errorFound || personnelDatesCheck(selectedBudgetLineItem, budgetPersonnelDetails, selectedBudgetPeriodIndex, selectedBudgetLineItemIndex, k);
            k++;
        }
        GlobalVariables.getMessageMap().removeFromErrorPath("document");
        if(!errorFound){
            getCalculationService().calculateBudgetLineItem(budget, selectedBudgetLineItem);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    /**
     * This method calculates the salary
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward calculateSalary(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudget();
        int selectedBudgetPeriodIndex = budgetForm.getViewBudgetPeriod()-1;
        int selectedBudgetLineItemIndex = budgetForm.getSelectedBudgetLineItemIndex();
        BudgetLineItem selectedBudgetLineItem = budget.getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(selectedBudgetLineItemIndex);
        BudgetPersonnelDetails budgetPersonnelDetails = selectedBudgetLineItem.getBudgetPersonnelDetailsList().get(getLineToDelete(request));
        boolean errorFound = false;
        GlobalVariables.getMessageMap().addToErrorPath("document");
        if(budgetPersonnelDetails.getPercentEffort().isGreaterThan(new ScaleTwoDecimal(100))){
            GlobalVariables.getMessageMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex   +"].budgetLineItems[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + getLineToDelete(request) + "].percentEffort",KeyConstants.ERROR_PERCENTAGE, Constants.PERCENT_EFFORT_FIELD);
            errorFound=true;
        }
        if(budgetPersonnelDetails.getPercentCharged().isGreaterThan(new ScaleTwoDecimal(100))){
            GlobalVariables.getMessageMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItems[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + getLineToDelete(request) + "].percentCharged",KeyConstants.ERROR_PERCENTAGE, Constants.PERCENT_CHARGED_FIELD);
            errorFound=true;
        }
        if(budgetPersonnelDetails.getPercentCharged().isGreaterThan(budgetPersonnelDetails.getPercentEffort())){
            GlobalVariables.getMessageMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItems[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + getLineToDelete(request) + "].percentCharged",KeyConstants.ERROR_PERCENT_EFFORT_LESS_THAN_PERCENT_CHARGED);
            errorFound=true;
        }
        errorFound=errorFound || personnelDatesCheck(selectedBudgetLineItem, budgetPersonnelDetails, selectedBudgetPeriodIndex, selectedBudgetLineItemIndex, getLineToDelete(request));
        if(!errorFound){
            BudgetPersonnelBudgetService budgetPersonnelBudgetService = KcServiceLocator.getService(BudgetPersonnelBudgetService.class);
            budgetPersonnelBudgetService.calculateBudgetPersonnelBudget(budget,selectedBudgetLineItem,budgetPersonnelDetails, getLineToDelete(request));
        }        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudget();
        int selectedBudgetPeriodIndex = budgetForm.getViewBudgetPeriod()-1;
        int selectedBudgetLineItemIndex = budgetForm.getSelectedBudgetLineItemIndex();
        BudgetLineItem selectedBudgetLineItem = budget.getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(selectedBudgetLineItemIndex);
        int k=0;
        boolean errorFound = false;
        GlobalVariables.getMessageMap().addToErrorPath("document");
        for(BudgetPersonnelDetails budgetPersonnelDetails: selectedBudgetLineItem.getBudgetPersonnelDetailsList()){
            errorFound |= personnelDatesCheck(selectedBudgetLineItem, budgetPersonnelDetails, selectedBudgetPeriodIndex, selectedBudgetLineItemIndex, k);
            k++;
        }
        GlobalVariables.getMessageMap().removeFromErrorPath("document");
        if (errorFound) {
            getCalculationService().calculateBudgetLineItem(budget, selectedBudgetLineItem);
            return mapping.findForward(Constants.MAPPING_BASIC);
        } else {
            ActionForward actionForward = super.save(mapping, form, request, response);
            selectedBudgetLineItem.setBudgetPersonnelLineItemDeleted(false);
            return actionForward;
        }
    }
    
    private boolean personnelDatesCheck(BudgetLineItem budgetLineItem, BudgetPersonnelDetails budgetPersonnelDetails, int selectedBudgetPeriodIndex, int selectedBudgetLineItemIndex, int detailIndex) {
        boolean errorFound = false;
        if(budgetPersonnelDetails.getEndDate().compareTo(budgetPersonnelDetails.getStartDate()) < 0) {
            GlobalVariables.getMessageMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItems[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + detailIndex + "].endDate",KeyConstants.ERROR_PERSONNEL_DETAIL_DATES);
            errorFound=true;
        }
        if(budgetLineItem.getEndDate().compareTo(budgetPersonnelDetails.getEndDate()) < 0) {
            GlobalVariables.getMessageMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItems[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + detailIndex + "].endDate",KeyConstants.ERROR_PERSONNEL_DETAIL_END_DATE, new String[] {"can not be after", "end date"});
            errorFound=true;
        }
        if(budgetLineItem.getStartDate().compareTo(budgetPersonnelDetails.getEndDate()) > 0) {
            GlobalVariables.getMessageMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItems[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + detailIndex + "].endDate",KeyConstants.ERROR_PERSONNEL_DETAIL_END_DATE, new String[] {"can not be before", "start date"});
            errorFound=true;
        }
        if(budgetLineItem.getStartDate().compareTo(budgetPersonnelDetails.getStartDate()) > 0) {
            GlobalVariables.getMessageMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItems[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + detailIndex + "].startDate",KeyConstants.ERROR_PERSONNEL_DETAIL_START_DATE, new String[] {"can not be before", "start date"});
            errorFound=true;
        }
        if(budgetLineItem.getEndDate().compareTo(budgetPersonnelDetails.getStartDate()) < 0) {
            GlobalVariables.getMessageMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItems[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + detailIndex + "].startDate",KeyConstants.ERROR_PERSONNEL_DETAIL_START_DATE, new String[] {"can not be after", "end date"});
            errorFound=true;
        }
        
        return errorFound;
    }
     
    private void updatePersonnelBudgetRate(BudgetLineItem budgetLineItem){
        int j = 0;
        for(BudgetPersonnelDetails budgetPersonnelDetails: budgetLineItem.getBudgetPersonnelDetailsList()){
            j=0;
            for(BudgetPersonnelCalculatedAmount budgetPersonnelCalculatedAmount:budgetPersonnelDetails.getBudgetPersonnelCalculatedAmounts()){
                budgetPersonnelCalculatedAmount.setApplyRateFlag(budgetLineItem.getBudgetLineItemCalculatedAmounts().get(j).getApplyRateFlag());                        
                j++;
            }
        }
    }

    
}
