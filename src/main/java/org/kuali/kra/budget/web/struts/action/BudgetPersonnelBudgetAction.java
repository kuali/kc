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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.RiceKeyConstants;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetPerson;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.lookup.keyvalue.BudgetCategoryTypeValuesFinder;
import org.kuali.kra.budget.service.BudgetCalculationService;
import org.kuali.kra.budget.service.BudgetPersonnelBudgetService;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;

public class BudgetPersonnelBudgetAction extends BudgetAction {
    private static final Log LOG = LogFactory.getLog(BudgetPersonnelBudgetAction.class);
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
        int selectedBudgetPeriodIndex = budgetForm.getViewBudgetPeriod()-1;
        int selectedBudgetLineItemIndex = budgetForm.getSelectedBudgetLineItemIndex();
        BudgetPersonnelDetails newBudgetPersonnelDetails = budgetForm.getNewBudgetPersonnelDetails();
        
        boolean errorFound = false;
        if(newBudgetPersonnelDetails.getPercentEffort().isLessThan(new BudgetDecimal(0)) 
                || newBudgetPersonnelDetails.getPercentEffort().isGreaterThan(new BudgetDecimal(100))){
            GlobalVariables.getErrorMap().putError("newBudgetPersonnelDetails.percentEffort",KeyConstants.ERROR_PERCENT_EFFORT_INVALID);
            errorFound=true;
        }
        if(newBudgetPersonnelDetails.getPercentCharged().isLessThan(new BudgetDecimal(0)) 
                || newBudgetPersonnelDetails.getPercentCharged().isGreaterThan(new BudgetDecimal(100))){
            GlobalVariables.getErrorMap().putError("newBudgetPersonnelDetails.percentCharged",KeyConstants.ERROR_PERCENT_CHARGED_INVALID);
            errorFound=true;
        }
        if(newBudgetPersonnelDetails.getPercentCharged().isGreaterThan(newBudgetPersonnelDetails.getPercentEffort())){
            GlobalVariables.getErrorMap().putError("newBudgetPersonnelDetails.percentCharged",KeyConstants.ERROR_PERCENT_EFFORT_LESS_THAN_PERCENT_CHARGED);
            errorFound=true;
        }
        if (StringUtils.isBlank((budgetForm.getNewBudgetPersonnelDetails().getPeriodTypeCode()))) {
            GlobalVariables.getErrorMap().putError("newBudgetPersonnelDetails.periodTypeCode", RiceKeyConstants.ERROR_REQUIRED, new String[] { "Period Type (Period Type)" });
            errorFound=true;
        }

        HashMap uniqueBudgetPersonnelCount = new HashMap();
        int qty = 0;
        if(!errorFound){
            BudgetPersonnelBudgetService budgetPersonnelBudgetService = KraServiceLocator.getService(BudgetPersonnelBudgetService.class);
            budgetPersonnelBudgetService.addBudgetPersonnelDetails(budgetForm.getBudgetDocument(), selectedBudgetPeriodIndex,selectedBudgetLineItemIndex, newBudgetPersonnelDetails);
            for (BudgetPersonnelDetails budgetPersonnelDetails : budgetForm.getBudgetDocument().getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(selectedBudgetLineItemIndex).getBudgetPersonnelDetailsList()) {
                if(!uniqueBudgetPersonnelCount.containsValue(budgetPersonnelDetails.getPersonId())){
                    uniqueBudgetPersonnelCount.put(qty, budgetPersonnelDetails.getPersonId());
                    qty = qty + 1;
                }
            }    
            budgetForm.getBudgetDocument().getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(selectedBudgetLineItemIndex).setQuantity(new Integer(qty));
            BudgetCalculationService budgetCalculationService = KraServiceLocator.getService(BudgetCalculationService.class);
            budgetCalculationService.calculateBudgetPeriod(budgetForm.getBudgetDocument(), budgetForm.getBudgetDocument().getBudgetPeriod(selectedBudgetPeriodIndex));
            budgetForm.setNewBudgetPersonnelDetails(new BudgetPersonnelDetails());
        }        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }    
    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        ActionForward actionForward = super.reload(mapping, form, request, response);
        BusinessObjectService bos = KraServiceLocator.getService(BusinessObjectService.class);
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        Map queryMap = new HashMap();
        queryMap.put("proposalNumber", budgetDocument.getProposalNumber());
        queryMap.put("budgetVersionNumber", budgetDocument.getBudgetVersionNumber());
        Collection budgetPersons = bos.findMatching(BudgetPerson.class, queryMap);
        budgetDocument.setBudgetPersons(budgetPersons==null?new ArrayList():(List)budgetPersons);
        
        BudgetCategoryTypeValuesFinder budgetCategoryTypeValuesFinder = new BudgetCategoryTypeValuesFinder();
        List<KeyLabelPair> budgetCategoryTypes = new ArrayList<KeyLabelPair>();        
        budgetCategoryTypes = budgetCategoryTypeValuesFinder.getKeyValues();
        for(int i=0;i<budgetCategoryTypes.size();i++){
            budgetForm.getNewBudgetLineItems().add(new BudgetLineItem());
        }
        budgetDocument.setBudgetCategoryTypeCodes(budgetCategoryTypes);
        
        return actionForward;
    }
    
    public ActionForward deleteBudgetPersonnelDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        int selectedBudgetPeriodIndex = budgetForm.getViewBudgetPeriod()-1;
        int selectedBudgetLineItemIndex = budgetForm.getSelectedBudgetLineItemIndex();
        BudgetPersonnelBudgetService budgetPersonnelBudgetService = KraServiceLocator.getService(BudgetPersonnelBudgetService.class);
        budgetPersonnelBudgetService.deleteBudgetPersonnelDetails(budgetForm.getBudgetDocument(), selectedBudgetPeriodIndex,selectedBudgetLineItemIndex, getLineToDelete(request));
        HashMap uniqueBudgetPersonnelCount = new HashMap();
        int qty = 0;
        for (BudgetPersonnelDetails budgetPersonnelDetails : budgetForm.getBudgetDocument().getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(selectedBudgetLineItemIndex).getBudgetPersonnelDetailsList()) {
            if(!uniqueBudgetPersonnelCount.containsValue(budgetPersonnelDetails.getPersonId())){
                uniqueBudgetPersonnelCount.put(qty, budgetPersonnelDetails.getPersonId());
                qty = qty + 1;
            }
        }    
        budgetForm.getBudgetDocument().getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(selectedBudgetLineItemIndex).setQuantity(new Integer(qty));
        
        BudgetCalculationService budgetCalculationService = KraServiceLocator.getService(BudgetCalculationService.class);
        budgetCalculationService.calculateBudgetPeriod(budgetForm.getBudgetDocument(), budgetForm.getBudgetDocument().getBudgetPeriod(selectedBudgetPeriodIndex));
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
        int selectedBudgetPeriodIndex = budgetForm.getViewBudgetPeriod()-1;
        int selectedBudgetLineItemIndex = budgetForm.getSelectedBudgetLineItemIndex();
        BudgetLineItem selectedBudgetLineItem = budgetForm.getBudgetDocument().getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(selectedBudgetLineItemIndex);
        BudgetCalculationService budgetCalculationService = KraServiceLocator.getService(BudgetCalculationService.class);
        budgetCalculationService.calculateBudgetLineItem(budgetForm.getBudgetDocument(), selectedBudgetLineItem);
        
        BudgetCategoryTypeValuesFinder budgetCategoryTypeValuesFinder = new BudgetCategoryTypeValuesFinder();
        List<KeyLabelPair> budgetCategoryTypes = new ArrayList<KeyLabelPair>();        
        budgetCategoryTypes = budgetCategoryTypeValuesFinder.getKeyValues();
        for(int i=0;i<budgetCategoryTypes.size();i++){
            budgetForm.getNewBudgetLineItems().add(new BudgetLineItem());
        }
        budgetForm.getBudgetDocument().setBudgetCategoryTypeCodes(budgetCategoryTypes);
        request.setAttribute("fromPersonnelBudget"+budgetForm.getViewBudgetPeriod()+""+selectedBudgetLineItemIndex, true);
        
        return mapping.findForward("expenses");
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
        int selectedBudgetPeriodIndex = budgetForm.getViewBudgetPeriod()-1;
        int selectedBudgetLineItemIndex = budgetForm.getSelectedBudgetLineItemIndex();
        BudgetLineItem selectedBudgetLineItem = budgetForm.getBudgetDocument().getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(selectedBudgetLineItemIndex);
        int k=0;
        boolean errorFound = false;
        GlobalVariables.getErrorMap().addToErrorPath("document");
        for(BudgetPersonnelDetails budgetPersonnelDetails: selectedBudgetLineItem.getBudgetPersonnelDetailsList()){
            if(budgetPersonnelDetails.getPercentEffort().isLessThan(new BudgetDecimal(0)) 
                    || budgetPersonnelDetails.getPercentEffort().isGreaterThan(new BudgetDecimal(100))){
                GlobalVariables.getErrorMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItems[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + k + "].percentEffort",KeyConstants.ERROR_PERCENT_EFFORT_INVALID);
                errorFound=true;
            }
            if(budgetPersonnelDetails.getPercentCharged().isLessThan(new BudgetDecimal(0)) 
                    || budgetPersonnelDetails.getPercentCharged().isGreaterThan(new BudgetDecimal(100))){
                GlobalVariables.getErrorMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItems[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + k + "].percentCharged",KeyConstants.ERROR_PERCENT_CHARGED_INVALID);
                errorFound=true;
            }
            if(budgetPersonnelDetails.getPercentCharged().isGreaterThan(budgetPersonnelDetails.getPercentEffort())){
                GlobalVariables.getErrorMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItems[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + k + "].percentCharged",KeyConstants.ERROR_PERCENT_EFFORT_LESS_THAN_PERCENT_CHARGED);
                errorFound=true;
            }
            k++;
        }
        GlobalVariables.getErrorMap().removeFromErrorPath("document");
//        BudgetLineItem selectedBudgetLineItem = budgetForm.getSelectedBudgetLineItem();
        if(!errorFound){
            BudgetCalculationService budgetCalculationService = KraServiceLocator.getService(BudgetCalculationService.class);
            budgetCalculationService.calculateBudgetLineItem(budgetForm.getBudgetDocument(), selectedBudgetLineItem);
        }
//        BudgetPersonnelDetails budgetPersonnelDetails = selectedBudgetLineItem.getBudgetPersonnelDetailsList().get(getLineToDelete(request));        
//        BudgetPersonnelBudgetService budgetPersonnelBudgetService = KraServiceLocator.getService(BudgetPersonnelBudgetService.class);
//        budgetPersonnelBudgetService.calculateBudgetPersonnelBudget(budgetForm.getBudgetDocument(),selectedBudgetLineItem,budgetPersonnelDetails, getLineToDelete(request));
//        BudgetCalculationService budgetCalculationService = KraServiceLocator.getService(BudgetCalculationService.class);
//        budgetCalculationService.calculateBudgetLineItem(budgetForm.getBudgetDocument(), budgetPersonnelDetails);
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
        int selectedBudgetPeriodIndex = budgetForm.getViewBudgetPeriod()-1;
        int selectedBudgetLineItemIndex = budgetForm.getSelectedBudgetLineItemIndex();
        BudgetLineItem selectedBudgetLineItem = budgetForm.getBudgetDocument().getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(selectedBudgetLineItemIndex);
        BudgetPersonnelDetails budgetPersonnelDetails = selectedBudgetLineItem.getBudgetPersonnelDetailsList().get(getLineToDelete(request));
        boolean errorFound = false;
        GlobalVariables.getErrorMap().addToErrorPath("document");
        if(budgetPersonnelDetails.getPercentEffort().isLessThan(new BudgetDecimal(0)) 
                || budgetPersonnelDetails.getPercentEffort().isGreaterThan(new BudgetDecimal(100))){
            GlobalVariables.getErrorMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex   +"].budgetLineItems[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + getLineToDelete(request) + "].percentEffort",KeyConstants.ERROR_PERCENT_EFFORT_INVALID);
            errorFound=true;
        }
        if(budgetPersonnelDetails.getPercentCharged().isLessThan(new BudgetDecimal(0)) 
                || budgetPersonnelDetails.getPercentCharged().isGreaterThan(new BudgetDecimal(100))){
            GlobalVariables.getErrorMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItems[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + getLineToDelete(request) + "].percentCharged",KeyConstants.ERROR_PERCENT_CHARGED_INVALID);
            errorFound=true;
        }
        if(budgetPersonnelDetails.getPercentCharged().isGreaterThan(budgetPersonnelDetails.getPercentEffort())){
            GlobalVariables.getErrorMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItems[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + getLineToDelete(request) + "].percentCharged",KeyConstants.ERROR_PERCENT_EFFORT_LESS_THAN_PERCENT_CHARGED);
            errorFound=true;
        }
        if(!errorFound){
            BudgetPersonnelBudgetService budgetPersonnelBudgetService = KraServiceLocator.getService(BudgetPersonnelBudgetService.class);
            budgetPersonnelBudgetService.calculateBudgetPersonnelBudget(budgetForm.getBudgetDocument(),selectedBudgetLineItem,budgetPersonnelDetails, getLineToDelete(request));
        }        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        int selectedBudgetPeriodIndex = budgetForm.getViewBudgetPeriod()-1;
        int selectedBudgetLineItemIndex = budgetForm.getSelectedBudgetLineItemIndex();
        BudgetLineItem selectedBudgetLineItem = budgetForm.getBudgetDocument().getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(selectedBudgetLineItemIndex);
        BudgetCalculationService budgetCalculationService = KraServiceLocator.getService(BudgetCalculationService.class);
        budgetCalculationService.calculateBudgetLineItem(budgetForm.getBudgetDocument(), selectedBudgetLineItem);
        ActionForward actionForward = super.save(mapping, form, request, response);
        selectedBudgetLineItem.setBudgetPersonnelLineItemDeleted(false);
        return actionForward;
    }
    
}
