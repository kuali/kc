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
import org.kuali.RiceConstants;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.DictionaryValidationService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.budget.bo.BudgetCategory;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetPersonnelCalculatedAmount;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.lookup.keyvalue.BudgetCategoryTypeValuesFinder;
import org.kuali.kra.budget.rules.BudgetExpenseRule;
import org.kuali.kra.budget.service.BudgetCalculationService;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;

public class BudgetExpensesAction extends BudgetAction {
    private static final Log LOG = LogFactory.getLog(BudgetExpensesAction.class);
    
    public ActionForward updateBudgetPeriodView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {                
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward forward = super.execute(mapping, form, request, response);
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        // for fixing audit error
        if (budgetDocument.getBudgetCategoryTypeCodes() == null || budgetDocument.getBudgetCategoryTypeCodes().size() == 0) {
            BudgetCategoryTypeValuesFinder budgetCategoryTypeValuesFinder = new BudgetCategoryTypeValuesFinder();
            List<KeyLabelPair> budgetCategoryTypes = new ArrayList<KeyLabelPair>();        
            budgetCategoryTypes = budgetCategoryTypeValuesFinder.getKeyValues();
            for(int i=0;i<budgetCategoryTypes.size();i++){
                budgetForm.getNewBudgetLineItems().add(new BudgetLineItem());
            }
            budgetDocument.setBudgetCategoryTypeCodes(budgetCategoryTypes);
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
        Integer budgetCategoryTypeIndex = Integer.parseInt(getBudgetCategoryTypeIndex(request));
        BudgetLineItem newBudgetLineItem = budgetForm.getNewBudgetLineItems().get(budgetCategoryTypeIndex);        
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        
        if(budgetForm.getViewBudgetPeriod() == null || StringUtils.equalsIgnoreCase(budgetForm.getViewBudgetPeriod().toString(), "0")){
            GlobalVariables.getErrorMap().putError("viewBudgetPeriod", KeyConstants.ERROR_BUDGET_PERIOD_NOT_SELECTED);
        }
        else if(newBudgetLineItem.getCostElement() == null || StringUtils.equalsIgnoreCase(newBudgetLineItem.getCostElement(), "")){
            GlobalVariables.getErrorMap().putError("newBudgetLineItems[" + budgetCategoryTypeIndex + "].costElement", KeyConstants.ERROR_COST_ELEMENT_NOT_SELECTED);
        }
        else{
            Map<String, String> primaryKeys = new HashMap<String, String>();
            primaryKeys.put("budgetPeriod", budgetForm.getViewBudgetPeriod().toString());        
            BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);        
            BudgetPeriod budgetPeriod = (BudgetPeriod)businessObjectService.findByPrimaryKey(BudgetPeriod.class, primaryKeys);
            BudgetCategory newBudgetCategory = new BudgetCategory();
            
            newBudgetCategory.setBudgetCategoryTypeCode(getSelectedBudgetCategoryType(request));
            newBudgetCategory.refreshNonUpdateableReferences();
            newBudgetLineItem.setBudgetPeriod(budgetPeriod.getBudgetPeriod());
            newBudgetLineItem.setBudgetCategory(newBudgetCategory);
            newBudgetLineItem.setStartDate(budgetDocument.getBudgetPeriod(budgetPeriod.getBudgetPeriod() - 1).getStartDate());
            newBudgetLineItem.setEndDate(budgetForm.getBudgetDocument().getBudgetPeriod(budgetPeriod.getBudgetPeriod() - 1).getEndDate());
            newBudgetLineItem.setProposalNumber(budgetDocument.getBudgetPeriod(budgetPeriod.getBudgetPeriod() - 1).getProposalNumber());
            newBudgetLineItem.setBudgetVersionNumber(budgetDocument.getBudgetPeriod(budgetPeriod.getBudgetPeriod() - 1).getBudgetVersionNumber());
//            if(budgetForm.isDocumentNextValueRefresh()){
//                budgetForm.getBudgetDocument().refreshReferenceObject("documentNextvalues");            
//                budgetForm.setDocumentNextValueRefresh(false);
//            }    
            newBudgetLineItem.setLineItemNumber(budgetForm.getBudgetDocument().getHackedDocumentNextValue(Constants.BUDGET_LINEITEM_NUMBER));
            newBudgetLineItem.setApplyInRateFlag(true);
            newBudgetLineItem.refreshReferenceObject("costElementBO");
            
            // on/off campus flag enhancement
            String onOffCampusFlag = budgetDocument.getOnOffCampusFlag();
            if (onOffCampusFlag.equalsIgnoreCase(Constants.DEFALUT_CAMUS_FLAG)) {
                newBudgetLineItem.setOnOffCampusFlag(newBudgetLineItem.getCostElementBO().getOnOffCampusFlag()); 
            } else {
                newBudgetLineItem.setOnOffCampusFlag(onOffCampusFlag.equalsIgnoreCase(Constants.ON_CAMUS_FLAG));                 
            }
            newBudgetLineItem.setBudgetCategoryCode(newBudgetLineItem.getCostElementBO().getBudgetCategoryCode());
            newBudgetLineItem.setLineItemSequence(newBudgetLineItem.getLineItemNumber());
            
            BudgetCalculationService budgetCalculationService = KraServiceLocator.getService(BudgetCalculationService.class);                          
            budgetForm.getBudgetDocument().getBudgetPeriod(budgetPeriod.getBudgetPeriod() - 1).getBudgetLineItems().add(newBudgetLineItem);            
            budgetCalculationService.calculateBudgetPeriod(budgetForm.getBudgetDocument(), budgetForm.getBudgetDocument().getBudgetPeriod(budgetPeriod.getBudgetPeriod() - 1));
            
            budgetCalculationService.populateCalculatedAmount(budgetDocument, newBudgetLineItem);
//            newBudgetLineItem = new BudgetLineItem();                
            budgetForm.getNewBudgetLineItems().set(budgetCategoryTypeIndex,new BudgetLineItem());
            //budgetForm.setLineAddedOrDeletedSinceLastSaveOrCalculate(true);
        }        
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
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        int sltdBudgetPeriod = budgetForm.getViewBudgetPeriod()-1;
        if (new BudgetExpenseRule().processCheckExistBudgetPersonnelDetailsBusinessRules(budgetDocument, budgetForm.getBudgetDocument().getBudgetPeriod(sltdBudgetPeriod).getBudgetLineItems().get(getLineToDelete(request)), getLineToDelete(request))) {
            budgetForm.getBudgetDocument().getBudgetPeriod(sltdBudgetPeriod).getBudgetLineItems().remove(getLineToDelete(request));        
            budgetDocument.setBudgetLineItemDeleted(true);        
            //budgetForm.setLineAddedOrDeletedSinceLastSaveOrCalculate(true);
            BudgetCalculationService budgetCalculationService = KraServiceLocator.getService(BudgetCalculationService.class);
            budgetCalculationService.calculateBudgetPeriod(budgetForm.getBudgetDocument(), budgetForm.getBudgetDocument().getBudgetPeriod(sltdBudgetPeriod));
        }
        return mapping.findForward("basic");
    }
    
    protected String getSelectedBudgetCategoryType(HttpServletRequest request) {
        String selectedCategoryTypeCode = "";
        String parameterName = (String) request.getAttribute(RiceConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            selectedCategoryTypeCode = StringUtils.substringBetween(parameterName, ".budgetCategoryTypeCode", ".");
        }
        return selectedCategoryTypeCode;
    }
    
    protected String getBudgetCategoryTypeIndex(HttpServletRequest request) {
        String selectedBudgetCategoryTypeIndex = "";
        String parameterName = (String) request.getAttribute(RiceConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            selectedBudgetCategoryTypeIndex = StringUtils.substringBetween(parameterName, ".catTypeIndex", ".");
        }
        return selectedBudgetCategoryTypeIndex;
    }
    
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        
        ActionForward actionForward = super.reload(mapping, form, request, response);

        BudgetCategoryTypeValuesFinder budgetCategoryTypeValuesFinder = new BudgetCategoryTypeValuesFinder();
        
//        budgetForm.getBudgetDocument().refreshReferenceObject("budgetPeriods");
//        budgetForm.setDocumentNextValueRefresh(true);
        
        List<KeyLabelPair> budgetCategoryTypes = new ArrayList<KeyLabelPair>();        
        budgetCategoryTypes = budgetCategoryTypeValuesFinder.getKeyValues();        
        budgetForm.getBudgetDocument().setBudgetCategoryTypeCodes(budgetCategoryTypes);
        for(int i=0;i<budgetCategoryTypes.size();i++){
            budgetForm.getNewBudgetLineItems().add(new BudgetLineItem());
        }
        
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
    public ActionForward personnelBudget(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        int selectedLineNumber = getSelectedLine(request);
//          System.out.println(selectedLineNumber);
        int selectedPeriod = budgetForm.getViewBudgetPeriod().intValue();
        DictionaryValidationService dictionaryValidationService = KraServiceLocator.getService(DictionaryValidationService.class);
        int i =0;
        for(BudgetLineItem budgetLineItem:budgetDocument.getBudgetPeriod(selectedPeriod-1).getBudgetLineItems()){
            GlobalVariables.getErrorMap().addToErrorPath("document.budgetPeriods[" + (selectedPeriod-1) + "].budgetLineItems[" + i + "]");
            dictionaryValidationService.validateBusinessObject(budgetLineItem);
            GlobalVariables.getErrorMap().removeFromErrorPath("document.budgetPeriods[" + (selectedPeriod-1) + "].budgetLineItems[" + i + "]");
            i++;
            updatePersonnelBudgetRate(budgetLineItem);
        }
        BudgetLineItem selectedLineItem = budgetDocument.getBudgetPeriod(selectedPeriod-1).getBudgetLineItem(selectedLineNumber);
        if(GlobalVariables.getErrorMap().getPropertiesWithErrors().size()>0){            
            return mapping.findForward(Constants.MAPPING_EXPENSES_BUDGET);           
        }
        budgetForm.setSelectedBudgetLineItem(selectedLineItem);
        budgetForm.setSelectedBudgetLineItemIndex(selectedLineNumber);
        return mapping.findForward(Constants.MAPPING_PERSONNEL_BUDGET);
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
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        int selectedPeriod = budgetForm.getViewBudgetPeriod().intValue();
        BudgetPeriod budgetPeriod = budgetDocument.getBudgetPeriod(selectedPeriod-1);
        for(BudgetLineItem budgetLineItem:budgetPeriod.getBudgetLineItems()){
            updatePersonnelBudgetRate(budgetLineItem);
        }
        BudgetCalculationService budgetCalculationService  = KraServiceLocator.getService(BudgetCalculationService.class);
        budgetCalculationService.calculateBudgetPeriod(budgetDocument, budgetPeriod);
        //budgetForm.setLineAddedOrDeletedSinceLastSaveOrCalculate(false);
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
        return mapping.findForward(Constants.MAPPING_BASIC);
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
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        //budgetForm.setLineAddedOrDeletedSinceLastSaveOrCalculate(false);
        for(BudgetPeriod budgetPeriod:budgetDocument.getBudgetPeriods()){
            for(BudgetLineItem budgetLineItem:budgetPeriod.getBudgetLineItems()){                
                if(!StringUtils.equalsIgnoreCase(budgetLineItem.getCostElement(), budgetLineItem.getCostElementBO().getCostElement())){
                    budgetLineItem.refreshReferenceObject("costElementBO");
                    budgetLineItem.setBudgetCategoryCode(budgetLineItem.getCostElementBO().getBudgetCategoryCode());
                }
                updatePersonnelBudgetRate(budgetLineItem);
            }
        }
        
        // jira - 1288 move to super.save
//        BudgetCalculationService budgetCalculationService  = KraServiceLocator.getService(BudgetCalculationService.class);
//        for (BudgetPeriod budgetPeriod : budgetDocument.getBudgetPeriods()) {
//            budgetCalculationService.calculateBudgetPeriod(budgetDocument, budgetPeriod);
//        }
        ActionForward actionForward = super.save(mapping, form, request, response);
        budgetDocument.setBudgetLineItemDeleted(false);
        return actionForward;
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
    public ActionForward syncToPeriodCostLimit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();        
        BudgetCalculationService budgetCalculationService  = KraServiceLocator.getService(BudgetCalculationService.class);
        int sltdLineItem = getSelectedLine(request);
        int sltdBudgetPeriod = budgetForm.getViewBudgetPeriod()-1;
        budgetCalculationService.syncToPeriodCostLimit(budgetDocument, budgetDocument.getBudgetPeriod(sltdBudgetPeriod), budgetDocument.getBudgetPeriod(sltdBudgetPeriod).getBudgetLineItem(sltdLineItem));
        return mapping.findForward(Constants.MAPPING_BASIC);
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
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();        
        BudgetCalculationService budgetCalculationService  = KraServiceLocator.getService(BudgetCalculationService.class);
        int sltdLineItem = getSelectedLine(request);
        int sltdBudgetPeriod = budgetForm.getViewBudgetPeriod()-1;
        if (new BudgetExpenseRule().processApplyToLaterPeriodsWithPersonnelDetails(budgetDocument, budgetDocument.getBudgetPeriod(sltdBudgetPeriod), budgetDocument.getBudgetPeriod(sltdBudgetPeriod).getBudgetLineItem(sltdLineItem), sltdLineItem)) {
            budgetCalculationService.applyToLaterPeriods(budgetDocument, budgetDocument.getBudgetPeriod(sltdBudgetPeriod), budgetDocument.getBudgetPeriod(sltdBudgetPeriod).getBudgetLineItem(sltdLineItem));
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public void updatePersonnelBudgetRate(BudgetLineItem budgetLineItem){
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
