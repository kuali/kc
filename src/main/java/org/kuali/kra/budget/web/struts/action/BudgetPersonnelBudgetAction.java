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
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetPerson;
import org.kuali.kra.budget.bo.BudgetPersonnelCalculatedAmount;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.lookup.keyvalue.BudgetCategoryTypeValuesFinder;
import org.kuali.kra.budget.service.BudgetCalculationService;
import org.kuali.kra.budget.service.BudgetPersonnelBudgetService;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.RiceKeyConstants;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

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
            GlobalVariables.getErrorMap().putError("newBudgetPersonnelDetails.percentEffort",KeyConstants.ERROR_PERCENTAGE, Constants.PERCENT_EFFORT_FIELD);
            errorFound=true;
        }
        if(newBudgetPersonnelDetails.getPercentCharged().isLessThan(new BudgetDecimal(0)) 
                || newBudgetPersonnelDetails.getPercentCharged().isGreaterThan(new BudgetDecimal(100))){
            GlobalVariables.getErrorMap().putError("newBudgetPersonnelDetails.percentCharged",KeyConstants.ERROR_PERCENTAGE, Constants.PERCENT_CHARGED_FIELD);
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
        if (budgetForm.getNewBudgetPersonnelDetails().getPersonSequenceNumber() == null) {
            GlobalVariables.getErrorMap().putError("newBudgetPersonnelDetails.personSequenceNumber", RiceKeyConstants.ERROR_REQUIRED, new String[] { "Person (Person)" });
            errorFound=true;
        }

        HashMap uniqueBudgetPersonnelCount = new HashMap();
        int qty = 0;
        if(!errorFound){
            BudgetPersonnelBudgetService budgetPersonnelBudgetService = KraServiceLocator.getService(BudgetPersonnelBudgetService.class);
            //KualiConfigurationService kualiConfigurationService = KraServiceLocator.getService(KualiConfigurationService.class);
//            budgetPersonnelBudgetService.addBudgetPersonnelDetails(budgetForm.getBudgetDocument(), selectedBudgetPeriodIndex,selectedBudgetLineItemIndex, newBudgetPersonnelDetails);
            for (BudgetPersonnelDetails budgetPersonnelDetails : budgetForm.getDocument().getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(selectedBudgetLineItemIndex).getBudgetPersonnelDetailsList()) {
                if(!uniqueBudgetPersonnelCount.containsValue(budgetPersonnelDetails.getPersonId())){
                    uniqueBudgetPersonnelCount.put(qty, budgetPersonnelDetails.getPersonId());
                    qty = qty + 1;
                }
            }    
            budgetForm.getDocument().getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(selectedBudgetLineItemIndex).setQuantity(new Integer(qty));
            updatePersonnelBudgetRate(budgetForm.getDocument().getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(selectedBudgetLineItemIndex));
            BudgetCalculationService budgetCalculationService = KraServiceLocator.getService(BudgetCalculationService.class);
            budgetCalculationService.calculateBudgetPeriod(budgetForm.getDocument(), budgetForm.getDocument().getBudgetPeriod(selectedBudgetPeriodIndex));
            //budgetForm.setNewBudgetPersonnelDetails(new BudgetPersonnelDetails());
            //budgetForm.getNewBudgetPersonnelDetails().setPeriodTypeCode(kualiConfigurationService.getParameterValue(
                    //Constants.PARAMETER_MODULE_BUDGET, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.BUDGET_PERSON_DEFAULT_PERIOD_TYPE));
            //setBudgetPersonDefaultPeriodTypeCode(budgetForm);
        }        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }    

    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        //setBudgetPersonDefaultPeriodTypeCode(budgetForm);  
        ActionForward actionForward = super.reload(mapping, form, request, response);
        BusinessObjectService bos = KraServiceLocator.getService(BusinessObjectService.class);
        BudgetDocument budgetDocument = budgetForm.getDocument();
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
        budgetPersonnelBudgetService.deleteBudgetPersonnelDetails(budgetForm.getDocument(), selectedBudgetPeriodIndex,selectedBudgetLineItemIndex, getLineToDelete(request));
        HashMap uniqueBudgetPersonnelCount = new HashMap();
        int qty = 0;
        for (BudgetPersonnelDetails budgetPersonnelDetails : budgetForm.getDocument().getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(selectedBudgetLineItemIndex).getBudgetPersonnelDetailsList()) {
            if(!uniqueBudgetPersonnelCount.containsValue(budgetPersonnelDetails.getPersonId())){
                uniqueBudgetPersonnelCount.put(qty, budgetPersonnelDetails.getPersonId());
                qty = qty + 1;
            }
        }    
        budgetForm.getDocument().getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(selectedBudgetLineItemIndex).setQuantity(new Integer(qty));
        
        BudgetCalculationService budgetCalculationService = KraServiceLocator.getService(BudgetCalculationService.class);
        budgetCalculationService.calculateBudgetPeriod(budgetForm.getDocument(), budgetForm.getDocument().getBudgetPeriod(selectedBudgetPeriodIndex));
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
        BudgetLineItem selectedBudgetLineItem = budgetForm.getDocument().getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(selectedBudgetLineItemIndex);
        int k=0;
        boolean errorFound = false;
        GlobalVariables.getErrorMap().addToErrorPath("document");
        for(BudgetPersonnelDetails budgetPersonnelDetails: selectedBudgetLineItem.getBudgetPersonnelDetailsList()){
           errorFound=errorFound || personnelDatesCheck(selectedBudgetLineItem, budgetPersonnelDetails, selectedBudgetPeriodIndex, selectedBudgetLineItemIndex, k);
            k++;
        }
        GlobalVariables.getErrorMap().removeFromErrorPath("document");
        if (errorFound) {
            return mapping.findForward(Constants.MAPPING_BASIC);
        } else {
            BudgetCalculationService budgetCalculationService = KraServiceLocator.getService(BudgetCalculationService.class);
            budgetCalculationService.calculateBudgetLineItem(budgetForm.getDocument(), selectedBudgetLineItem);
            
            BudgetCategoryTypeValuesFinder budgetCategoryTypeValuesFinder = new BudgetCategoryTypeValuesFinder();
            List<KeyLabelPair> budgetCategoryTypes = new ArrayList<KeyLabelPair>();        
            budgetCategoryTypes = budgetCategoryTypeValuesFinder.getKeyValues();
            for(int i=0;i<budgetCategoryTypes.size();i++){
                budgetForm.getNewBudgetLineItems().add(new BudgetLineItem());
            }
            budgetForm.getDocument().setBudgetCategoryTypeCodes(budgetCategoryTypes);
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
        int selectedBudgetPeriodIndex = budgetForm.getViewBudgetPeriod()-1;
        int selectedBudgetLineItemIndex = budgetForm.getSelectedBudgetLineItemIndex();
        BudgetLineItem selectedBudgetLineItem = budgetForm.getDocument().getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(selectedBudgetLineItemIndex);
        int k=0;
        boolean errorFound = false;
        GlobalVariables.getErrorMap().addToErrorPath("document");
        for(BudgetPersonnelDetails budgetPersonnelDetails: selectedBudgetLineItem.getBudgetPersonnelDetailsList()){
            if(budgetPersonnelDetails.getPercentEffort().isLessThan(new BudgetDecimal(0)) 
                    || budgetPersonnelDetails.getPercentEffort().isGreaterThan(new BudgetDecimal(100))){
                GlobalVariables.getErrorMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItems[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + k + "].percentEffort",KeyConstants.ERROR_PERCENTAGE, Constants.PERCENT_EFFORT_FIELD);
                errorFound=true;
            }
            if(budgetPersonnelDetails.getPercentCharged().isLessThan(new BudgetDecimal(0)) 
                    || budgetPersonnelDetails.getPercentCharged().isGreaterThan(new BudgetDecimal(100))){
                GlobalVariables.getErrorMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItems[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + k + "].percentCharged",KeyConstants.ERROR_PERCENTAGE, Constants.PERCENT_CHARGED_FIELD);
                errorFound=true;
            }
            if(budgetPersonnelDetails.getPercentCharged().isGreaterThan(budgetPersonnelDetails.getPercentEffort())){
                GlobalVariables.getErrorMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItems[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + k + "].percentCharged",KeyConstants.ERROR_PERCENT_EFFORT_LESS_THAN_PERCENT_CHARGED);
                errorFound=true;
            }
            errorFound=errorFound || personnelDatesCheck(selectedBudgetLineItem, budgetPersonnelDetails, selectedBudgetPeriodIndex, selectedBudgetLineItemIndex, k);
            k++;
        }
        GlobalVariables.getErrorMap().removeFromErrorPath("document");
//        BudgetLineItem selectedBudgetLineItem = budgetForm.getSelectedBudgetLineItem();
        if(!errorFound){
            BudgetCalculationService budgetCalculationService = KraServiceLocator.getService(BudgetCalculationService.class);
            budgetCalculationService.calculateBudgetLineItem(budgetForm.getDocument(), selectedBudgetLineItem);
        }
//        BudgetPersonnelDetails budgetPersonnelDetails = selectedBudgetLineItem.getBudgetPersonnelDetailsList().get(getLineToDelete(request));        
//        BudgetPersonnelBudgetService budgetPersonnelBudgetService = KraServiceLocator.getService(BudgetPersonnelBudgetService.class);
//        budgetPersonnelBudgetService.calculateBudgetPersonnelBudget(budgetForm.getDocument(),selectedBudgetLineItem,budgetPersonnelDetails, getLineToDelete(request));
//        BudgetCalculationService budgetCalculationService = KraServiceLocator.getService(BudgetCalculationService.class);
//        budgetCalculationService.calculateBudgetLineItem(budgetForm.getDocument(), budgetPersonnelDetails);
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
        BudgetLineItem selectedBudgetLineItem = budgetForm.getDocument().getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(selectedBudgetLineItemIndex);
        BudgetPersonnelDetails budgetPersonnelDetails = selectedBudgetLineItem.getBudgetPersonnelDetailsList().get(getLineToDelete(request));
        boolean errorFound = false;
        GlobalVariables.getErrorMap().addToErrorPath("document");
        if(budgetPersonnelDetails.getPercentEffort().isLessThan(new BudgetDecimal(0)) 
                || budgetPersonnelDetails.getPercentEffort().isGreaterThan(new BudgetDecimal(100))){
            GlobalVariables.getErrorMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex   +"].budgetLineItems[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + getLineToDelete(request) + "].percentEffort",KeyConstants.ERROR_PERCENTAGE, Constants.PERCENT_EFFORT_FIELD);
            errorFound=true;
        }
        if(budgetPersonnelDetails.getPercentCharged().isLessThan(new BudgetDecimal(0)) 
                || budgetPersonnelDetails.getPercentCharged().isGreaterThan(new BudgetDecimal(100))){
            GlobalVariables.getErrorMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItems[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + getLineToDelete(request) + "].percentCharged",KeyConstants.ERROR_PERCENTAGE, Constants.PERCENT_CHARGED_FIELD);
            errorFound=true;
        }
        if(budgetPersonnelDetails.getPercentCharged().isGreaterThan(budgetPersonnelDetails.getPercentEffort())){
            GlobalVariables.getErrorMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItems[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + getLineToDelete(request) + "].percentCharged",KeyConstants.ERROR_PERCENT_EFFORT_LESS_THAN_PERCENT_CHARGED);
            errorFound=true;
        }
        errorFound=errorFound || personnelDatesCheck(selectedBudgetLineItem, budgetPersonnelDetails, selectedBudgetPeriodIndex, selectedBudgetLineItemIndex, getLineToDelete(request));
        if(!errorFound){
            BudgetPersonnelBudgetService budgetPersonnelBudgetService = KraServiceLocator.getService(BudgetPersonnelBudgetService.class);
            budgetPersonnelBudgetService.calculateBudgetPersonnelBudget(budgetForm.getDocument(),selectedBudgetLineItem,budgetPersonnelDetails, getLineToDelete(request));
        }        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        int selectedBudgetPeriodIndex = budgetForm.getViewBudgetPeriod()-1;
        int selectedBudgetLineItemIndex = budgetForm.getSelectedBudgetLineItemIndex();
        BudgetLineItem selectedBudgetLineItem = budgetForm.getDocument().getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(selectedBudgetLineItemIndex);
        int k=0;
        boolean errorFound = false;
        GlobalVariables.getErrorMap().addToErrorPath("document");
        for(BudgetPersonnelDetails budgetPersonnelDetails: selectedBudgetLineItem.getBudgetPersonnelDetailsList()){
            errorFound |= personnelDatesCheck(selectedBudgetLineItem, budgetPersonnelDetails, selectedBudgetPeriodIndex, selectedBudgetLineItemIndex, k);
            k++;
        }
        GlobalVariables.getErrorMap().removeFromErrorPath("document");
        if (errorFound) {
            BudgetCalculationService budgetCalculationService = KraServiceLocator.getService(BudgetCalculationService.class);
            budgetCalculationService.calculateBudgetLineItem(budgetForm.getDocument(), selectedBudgetLineItem);
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
            GlobalVariables.getErrorMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItems[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + detailIndex + "].endDate",KeyConstants.ERROR_PERSONNEL_DETAIL_DATES);
            errorFound=true;
        }
        if(budgetLineItem.getEndDate().compareTo(budgetPersonnelDetails.getEndDate()) < 0) {
            GlobalVariables.getErrorMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItems[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + detailIndex + "].endDate",KeyConstants.ERROR_PERSONNEL_DETAIL_END_DATE, new String[] {"can not be after", "end date"});
            errorFound=true;
        }
        if(budgetLineItem.getStartDate().compareTo(budgetPersonnelDetails.getEndDate()) > 0) {
            GlobalVariables.getErrorMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItems[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + detailIndex + "].endDate",KeyConstants.ERROR_PERSONNEL_DETAIL_END_DATE, new String[] {"can not be before", "start date"});
            errorFound=true;
        }
        if(budgetLineItem.getStartDate().compareTo(budgetPersonnelDetails.getStartDate()) > 0) {
            GlobalVariables.getErrorMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItems[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + detailIndex + "].startDate",KeyConstants.ERROR_PERSONNEL_DETAIL_START_DATE, new String[] {"can not be before", "start date"});
            errorFound=true;
        }
        if(budgetLineItem.getEndDate().compareTo(budgetPersonnelDetails.getStartDate()) < 0) {
            GlobalVariables.getErrorMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItems[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + detailIndex + "].startDate",KeyConstants.ERROR_PERSONNEL_DETAIL_START_DATE, new String[] {"can not be after", "end date"});
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
