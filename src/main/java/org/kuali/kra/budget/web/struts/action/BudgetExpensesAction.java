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
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.budget.bo.BudgetCategory;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.lookup.keyvalue.BudgetCategoryTypeValuesFinder;
import org.kuali.kra.budget.lookup.keyvalue.BudgetCategoryValuesFinder;
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
        else{
            Map<String, String> primaryKeys = new HashMap<String, String>();
            primaryKeys.put("budgetPeriod", budgetForm.getViewBudgetPeriod().toString());        
            BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);        
            BudgetPeriod budgetPeriod = (BudgetPeriod)businessObjectService.findByPrimaryKey(BudgetPeriod.class, primaryKeys);
            BudgetCategory newBudgetCategory = new BudgetCategory();
            
            budgetForm.setBudgetCategoryTypeCode(getSelectedBudgetCategoryType(request));
            newBudgetCategory.setBudgetCategoryTypeCode(budgetForm.getBudgetCategoryTypeCode());
            newBudgetLineItem.setBudgetPeriod(budgetPeriod.getBudgetPeriod());
            newBudgetLineItem.setBudgetCategory(newBudgetCategory);
            newBudgetLineItem.setStartDate(budgetDocument.getBudgetPeriod(budgetPeriod.getBudgetPeriod() - 1).getStartDate());
            newBudgetLineItem.setEndDate(budgetForm.getBudgetDocument().getBudgetPeriod(budgetPeriod.getBudgetPeriod() - 1).getEndDate());
            newBudgetLineItem.setProposalNumber(budgetDocument.getBudgetPeriod(budgetPeriod.getBudgetPeriod() - 1).getProposalNumber());
            newBudgetLineItem.setBudgetVersionNumber(budgetDocument.getBudgetPeriod(budgetPeriod.getBudgetPeriod() - 1).getBudgetVersionNumber());
            if(budgetForm.isDocumentNextValueRefresh()){
                budgetForm.getBudgetDocument().refreshReferenceObject("documentNextvalues");            
                budgetForm.setDocumentNextValueRefresh(false);
            }    
            newBudgetLineItem.setLineItemNumber(budgetForm.getBudgetDocument().getDocumentNextValue(Constants.BUDGET_LINEITEM_NUMBER));
            newBudgetLineItem.setApplyInRateFlag(true);
            newBudgetLineItem.setOnOffCampusFlag(true);       

            newBudgetLineItem.setLineItemSequence(newBudgetLineItem.getLineItemNumber());
            
            /*
            if(!budgetForm.getBudgetPeriodLineItemNumbersMapping().containsKey(budgetForm.getViewBudgetPeriod().toString())){            
                budgetForm.getBudgetLineItemNumbers().put(budgetForm.getBudgetCategoryTypeCode(), new Integer(0));                       
                budgetForm.getBudgetPeriodLineItemNumbersMapping().put(budgetForm.getViewBudgetPeriod().toString(), budgetForm.getBudgetLineItemNumbers());                        
            }else{            
                HashMap tempMap = budgetForm.getBudgetPeriodLineItemNumbersMapping().get(budgetForm.getViewBudgetPeriod().toString());
                if(!tempMap.containsKey(budgetForm.getBudgetCategoryTypeCode())){
                    tempMap.put(budgetForm.getBudgetCategoryTypeCode(), new Integer(0));
                    budgetForm.getBudgetPeriodLineItemNumbersMapping().put(budgetForm.getViewBudgetPeriod().toString(), tempMap);                
                }else{
                    String temp = tempMap.get(budgetForm.getBudgetCategoryTypeCode()).toString();
                    tempMap.remove(budgetForm.getBudgetCategoryTypeCode());
                    tempMap.put(budgetForm.getBudgetCategoryTypeCode(),new Integer(Integer.parseInt(temp)+1));
                    budgetForm.getBudgetPeriodLineItemNumbersMapping().remove(budgetForm.getViewBudgetPeriod().toString());
                    budgetForm.getBudgetPeriodLineItemNumbersMapping().put(budgetForm.getViewBudgetPeriod().toString(), tempMap);                
                }
            }
            
            if(!budgetForm.getBudgetPeriodLineItemSequenceMapping().containsKey(budgetForm.getViewBudgetPeriod().toString())){
                ArrayList tempList = new ArrayList();
                tempList.add(new Integer(0));
                budgetForm.getBudgetLineItemSequence().put(budgetForm.getBudgetCategoryTypeCode(), tempList);
                budgetForm.getBudgetPeriodLineItemSequenceMapping().put(budgetForm.getViewBudgetPeriod().toString(), budgetForm.getBudgetLineItemSequence());            
            }else{
                HashMap tempMap = budgetForm.getBudgetPeriodLineItemSequenceMapping().get(budgetForm.getViewBudgetPeriod().toString());
                if(!tempMap.containsKey(budgetForm.getBudgetCategoryTypeCode())){
                    ArrayList tempList = new ArrayList();
                    tempList.add(new Integer(0));
                    tempMap.put(budgetForm.getBudgetCategoryTypeCode(), tempList);
                    budgetForm.getBudgetPeriodLineItemSequenceMapping().put(budgetForm.getViewBudgetPeriod().toString(), tempMap);
                }else{
                    ArrayList tempList = new ArrayList();
                    tempList = (ArrayList) tempMap.get(budgetForm.getBudgetCategoryTypeCode());
                    tempList.add(new Integer(tempList.size()));
                    tempMap.remove(budgetForm.getBudgetCategoryTypeCode());
                    tempMap.put(budgetForm.getBudgetCategoryTypeCode(),tempList);
                    budgetForm.getBudgetPeriodLineItemSequenceMapping().remove(budgetForm.getViewBudgetPeriod().toString());
                    budgetForm.getBudgetPeriodLineItemSequenceMapping().put(budgetForm.getViewBudgetPeriod().toString(), tempMap);                
                }                
            }
            */
            BudgetCalculationService budgetCalculationService = KraServiceLocator.getService(BudgetCalculationService.class);
            budgetCalculationService.populateCalculatedAmount(budgetDocument, newBudgetLineItem);              
            budgetForm.getBudgetDocument().getBudgetPeriod(budgetPeriod.getBudgetPeriod() - 1).getBudgetLineItems().add(newBudgetLineItem);
            newBudgetLineItem = new BudgetLineItem();                
            budgetForm.getNewBudgetLineItems().set(budgetCategoryTypeIndex,newBudgetLineItem);
        }        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }    
    
    public ActionForward deleteBudgetLineItem(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getBudgetDocument();
        
        Map<String, String> primaryKeys = new HashMap<String, String>();
        primaryKeys.put("budgetPeriod", budgetForm.getViewBudgetPeriod().toString());        
        BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);        
        BudgetPeriod budgetPeriod = (BudgetPeriod)businessObjectService.findByPrimaryKey(BudgetPeriod.class, primaryKeys);
        
        /*
        if(Integer.parseInt(budgetForm.getBudgetPeriodLineItemNumbersMapping().get(budgetForm.getViewBudgetPeriod().toString()).get(budgetForm.getBudgetCategoryTypeCode().toString()).toString()) == 0){
            budgetForm.getBudgetPeriodLineItemNumbersMapping().get(budgetForm.getViewBudgetPeriod().toString()).remove(budgetForm.getBudgetCategoryTypeCode().toString());
        }else{
            String temp = budgetForm.getBudgetPeriodLineItemNumbersMapping().get(budgetForm.getViewBudgetPeriod().toString()).get(budgetForm.getBudgetCategoryTypeCode().toString()).toString();
            budgetForm.getBudgetPeriodLineItemNumbersMapping().get(budgetForm.getViewBudgetPeriod().toString()).get(budgetForm.getBudgetCategoryTypeCode().toString());
            budgetForm.getBudgetPeriodLineItemNumbersMapping().get(budgetForm.getViewBudgetPeriod().toString()).put(budgetForm.getBudgetCategoryTypeCode(),new Integer(Integer.parseInt(temp)-1));
        }
        
        if(((ArrayList)budgetForm.getBudgetPeriodLineItemSequenceMapping().get(budgetForm.getViewBudgetPeriod().toString()).get(budgetForm.getBudgetCategoryTypeCode().toString())).size() == 1){
            budgetForm.getBudgetPeriodLineItemSequenceMapping().get(budgetForm.getViewBudgetPeriod().toString()).remove(budgetForm.getBudgetCategoryTypeCode().toString());
        }else{
            ArrayList tempList = new ArrayList();
            tempList = (ArrayList)budgetForm.getBudgetPeriodLineItemSequenceMapping().get(budgetForm.getViewBudgetPeriod().toString()).get(budgetForm.getBudgetCategoryTypeCode().toString());
            tempList.remove(tempList.size()-1);
            budgetForm.getBudgetPeriodLineItemSequenceMapping().get(budgetForm.getViewBudgetPeriod().toString()).remove(budgetForm.getBudgetCategoryTypeCode().toString());
            budgetForm.getBudgetPeriodLineItemSequenceMapping().get(budgetForm.getViewBudgetPeriod().toString()).put(budgetForm.getBudgetCategoryTypeCode().toString(),tempList);            
        }   */
        budgetForm.getBudgetDocument().getBudgetPeriod(budgetPeriod.getBudgetPeriod()).getBudgetLineItems().remove(getLineToDelete(request));        
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
        
        budgetForm.getBudgetDocument().refreshReferenceObject("budgetPeriods");
        budgetForm.setDocumentNextValueRefresh(true);
        
        List<KeyLabelPair> budgetCategoryTypes = new ArrayList<KeyLabelPair>();        
        budgetCategoryTypes = budgetCategoryTypeValuesFinder.getKeyValues();        
        budgetForm.getBudgetDocument().setBudgetCategoryTypeCodes(budgetCategoryTypes);
        
        
        /*
        for(KeyLabelPair budgetCategoryType:budgetCategoryTypes){
            for(BudgetPeriod budgetPeriod: budgetPeriods){                
                budgetLineItems = budgetPeriod.getBudgetLineItems();
                for(BudgetLineItem budgetLineItem: budgetLineItems){
                    if(!budgetForm.getBudgetPeriodLineItemNumbersMapping().containsKey(budgetPeriod.getBudgetPeriod().toString())){
                        if(StringUtils.equalsIgnoreCase(budgetLineItem.getBudgetCategory().getBudgetCategoryTypeCode(),budgetCategoryType.key.toString())){
                            budgetForm.getBudgetLineItemNumbers().put(budgetCategoryType.getKey(), new Integer(0));                       
                            budgetForm.getBudgetPeriodLineItemNumbersMapping().put(budgetPeriod.getBudgetPeriod().toString(), budgetForm.getBudgetLineItemNumbers());
                        }                                                
                    }else{            
                        if(StringUtils.equalsIgnoreCase(budgetLineItem.getBudgetCategory().getBudgetCategoryTypeCode(),budgetCategoryType.key.toString())){
                            HashMap tempMap = budgetForm.getBudgetPeriodLineItemNumbersMapping().get(budgetPeriod.getBudgetPeriod().toString());
                            if(!tempMap.containsKey(budgetCategoryType.key.toString())){
                                tempMap.put(budgetCategoryType.key.toString(), new Integer(0));
                                budgetForm.getBudgetPeriodLineItemNumbersMapping().put(budgetPeriod.getBudgetPeriod().toString(), tempMap);                
                            }else{
                                String temp = tempMap.get(budgetCategoryType.getKey()).toString();
                                tempMap.remove(budgetCategoryType.key.toString());
                                tempMap.put(budgetCategoryType.key.toString(),new Integer(Integer.parseInt(temp)+1));
                                budgetForm.getBudgetPeriodLineItemNumbersMapping().remove(budgetPeriod.getBudgetPeriod().toString());
                                budgetForm.getBudgetPeriodLineItemNumbersMapping().put(budgetPeriod.getBudgetPeriod().toString(), tempMap);                
                            }                            
                        }                        
                    }
                    if(!budgetForm.getBudgetPeriodLineItemSequenceMapping().containsKey(budgetPeriod.getBudgetPeriod().toString())){
                        if(StringUtils.equalsIgnoreCase(budgetLineItem.getBudgetCategory().getBudgetCategoryTypeCode(),budgetCategoryType.key.toString())){
                            ArrayList tempList = new ArrayList();
                            tempList.add(new Integer(0));
                            budgetForm.getBudgetLineItemSequence().put(budgetCategoryType.key.toString(), tempList);
                            budgetForm.getBudgetPeriodLineItemSequenceMapping().put(budgetPeriod.getBudgetPeriod().toString(), budgetForm.getBudgetLineItemSequence());
                        }                                    
                    }else{
                        if(StringUtils.equalsIgnoreCase(budgetLineItem.getBudgetCategory().getBudgetCategoryTypeCode(),budgetCategoryType.key.toString())){
                            HashMap tempMap = budgetForm.getBudgetPeriodLineItemSequenceMapping().get(budgetPeriod.getBudgetPeriod().toString());
                            if(!tempMap.containsKey(budgetCategoryType.key.toString())){
                                ArrayList tempList = new ArrayList();
                                tempList.add(new Integer(0));
                                tempMap.put(budgetCategoryType.key.toString(), tempList);
                                budgetForm.getBudgetPeriodLineItemSequenceMapping().put(budgetPeriod.getBudgetPeriod().toString(), tempMap);
                            }else{
                                ArrayList tempList = new ArrayList();
                                tempList = (ArrayList) tempMap.get(budgetCategoryType.key.toString());
                                tempList.add(new Integer(tempList.size()));
                                tempMap.remove(budgetCategoryType.key.toString());
                                tempMap.put(budgetCategoryType.key.toString(),tempList);
                                budgetForm.getBudgetPeriodLineItemSequenceMapping().remove(budgetPeriod.getBudgetPeriod().toString());
                                budgetForm.getBudgetPeriodLineItemSequenceMapping().put(budgetPeriod.getBudgetPeriod().toString(), tempMap);                
                            }
                        }                                        
                    }
                }
            }
        }
        */
        return actionForward;
    }
    
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {        
        
        BudgetForm budgetForm = (BudgetForm) form;
        //budgetForm.setDocumentNextValueRefresh(true);
        /*
        budgetForm.setBudgetCategoryTypeCode(getSelectedBudgetCategoryType(request));
        
        List<BudgetPeriod> budgetPeriods = new ArrayList<BudgetPeriod>();
        List<BudgetLineItem> budgetLineItems = new ArrayList<BudgetLineItem>();
        List<BudgetCategory> budgetCategories = new ArrayList<BudgetCategory>();
        budgetPeriods = budgetForm.getBudgetDocument().getBudgetPeriods();
        for(BudgetPeriod budgetPeriod: budgetPeriods){
            budgetLineItems = budgetPeriod.getBudgetLineItems();
            for(BudgetLineItem budgetLineItem: budgetLineItems){
                BudgetCategory budgetCategory = budgetLineItem.getBudgetCategory();
                budgetCategory.setBudgetCategoryCode(budgetLineItem.getBudgetCategoryCode());
                budgetCategories.add(budgetCategory);                
            }
        }
        int i = 0;
        int budgetPeriodsSize = budgetPeriods.size();
        for (int j=0;j<budgetPeriodsSize;j++){
            for(BudgetCategory budgetCategory1: budgetCategories){
                budgetLineItems.get(i).setBudgetCategory(budgetCategory1);
            }
            budgetForm.getBudgetDocument().getBudgetPeriod(j).setBudgetLineItems(budgetLineItems);
            i++;
        }*/
        
        
        return super.save(mapping, form, request, response);
    }
}
