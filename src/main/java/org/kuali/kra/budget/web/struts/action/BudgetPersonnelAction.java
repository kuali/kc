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

import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;
import static org.kuali.kra.infrastructure.Constants.MAPPING_CLOSE_PAGE;
import static org.kuali.rice.kns.util.KNSConstants.QUESTION_INST_ATTRIBUTE_NAME;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.bo.NonOrganizationalRolodex;
import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetCategory;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetLineItemCalculatedAmount;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetPerson;
import org.kuali.kra.budget.bo.BudgetPersonnelCalculatedAmount;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.bo.TbnPerson;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.rules.BudgetExpenseRule;
import org.kuali.kra.budget.rules.BudgetPersonnelExpenseRule;
import org.kuali.kra.budget.rules.BudgetPersonnelRule;
import org.kuali.kra.budget.service.BudgetCalculationService;
import org.kuali.kra.budget.service.BudgetPersonService;
import org.kuali.kra.budget.service.BudgetPersonnelBudgetService;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.web.struts.action.StrutsConfirmation;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.document.authorization.DocumentAuthorizer;
import org.kuali.rice.kns.lookup.LookupResultsService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.RiceKeyConstants;

/**
 * Action class for Budget Personnel page
 */
public class BudgetPersonnelAction extends BudgetExpensesAction {
    
    private static final Log LOG = LogFactory.getLog(BudgetPersonnelAction.class);
    
    private static final String CONFIRM_DELETE_BUDGET_PERSON = "confirmDeleteBudgetPerson";
    private static final String EMPTY_GROUP_NAME = "";
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getDocument();
        // for fixing audit error
        if (budgetDocument.getBudgetCategoryTypeCodes() == null || budgetDocument.getBudgetCategoryTypeCodes().size() == 0) {
            populatePersonnelCategoryTypeCodes(budgetForm);
        }
        ActionForward forward = super.execute(mapping, form, request, response);
                 
        return forward;
    }
    
        
    private BudgetPeriod getSelectedBudgetPeriod(BudgetForm budgetForm) {
        BudgetDocument budgetDocument = budgetForm.getDocument();
        Map<String, String> primaryKeys = new HashMap<String, String>();
        primaryKeys.put("proposalNumber", budgetDocument.getProposalNumber());
        primaryKeys.put("budgetVersionNumber", budgetDocument.getBudgetVersionNumber().toString());
        primaryKeys.put("budgetPeriod", budgetForm.getViewBudgetPeriod().toString());
        BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);        
        List<BudgetPeriod> budgetPeriods = (List<BudgetPeriod>) businessObjectService.findMatching(BudgetPeriod.class, primaryKeys);
        BudgetPeriod budgetPeriod = null;
        if(CollectionUtils.isNotEmpty(budgetPeriods)) {
            budgetPeriod = budgetPeriods.get(0);
        }
        
        return budgetPeriod;
    }

    /**
     * This method is used to add a new Personnel Budget Line Item
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception  
     */
    public ActionForward addPersonnelLineItem(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getDocument();
        DictionaryValidationService dictionaryValidationService = KraServiceLocator.getService(DictionaryValidationService.class);
        KualiConfigurationService kualiConfigurationService = KraServiceLocator.getService(KualiConfigurationService.class);
        BudgetPersonnelRule budgetPersonnelRule = new BudgetPersonnelRule();
        
        Integer budgetCategoryTypeIndex = Integer.parseInt(getBudgetCategoryTypeIndex(request));
        BudgetLineItem newBudgetLineItem = budgetForm.getNewBudgetLineItems().get(budgetCategoryTypeIndex);
        BudgetPersonnelDetails budgetPersonDetails = budgetForm.getNewBudgetPersonnelDetails();
        budgetPersonDetails.setProposalNumber(budgetDocument.getProposalNumber());
        budgetPersonDetails.setBudgetVersionNumber(budgetDocument.getBudgetVersionNumber());
        budgetPersonDetails.setPeriodTypeCode(kualiConfigurationService.getParameterValue(
                Constants.PARAMETER_MODULE_BUDGET, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.BUDGET_PERSON_DETAILS_DEFAULT_PERIODTYPE));
        budgetPersonDetails.setCostElement(newBudgetLineItem.getCostElement());
        
        String groupErrorKey = "";
        if(StringUtils.isNotEmpty(newBudgetLineItem.getGroupName())) {
            groupErrorKey = "newBudgetLineItems[" + budgetCategoryTypeIndex + "].costElement" ;
        }
        if(StringUtils.isEmpty(newBudgetLineItem.getGroupName())) {
            newBudgetLineItem.setGroupName(budgetForm.getNewGroupName());
            groupErrorKey = "newGroupName";
        }
        
        if(StringUtils.isEmpty(newBudgetLineItem.getGroupName())) {
            newBudgetLineItem.setGroupName(EMPTY_GROUP_NAME);  
        }
        
        dictionaryValidationService.validateAttributeFormat(BudgetLineItem.class.getSimpleName(), "groupName", newBudgetLineItem.getGroupName(), groupErrorKey);
        
        if(budgetForm.getViewBudgetPeriod() == null || StringUtils.equalsIgnoreCase(budgetForm.getViewBudgetPeriod().toString(), "0")){
            GlobalVariables.getErrorMap().putError("viewBudgetPeriod", KeyConstants.ERROR_BUDGET_PERIOD_NOT_SELECTED);
        }
        else if(newBudgetLineItem.getCostElement() == null || StringUtils.equalsIgnoreCase(newBudgetLineItem.getCostElement(), "")){
            GlobalVariables.getErrorMap().putError("newBudgetLineItems[" + budgetCategoryTypeIndex + "].costElement", KeyConstants.ERROR_COST_ELEMENT_NOT_SELECTED);
        }else if(budgetPersonDetails.getPersonSequenceNumber() == null){
            GlobalVariables.getErrorMap().putError("newBudgetPersonnelDetails.personSequenceNumber", KeyConstants.ERROR_BUDGET_PERSONNEL_NOT_SELECTED);
        } else if(!budgetPersonnelRule.processCheckJobCodeObjectCodeCombo(budgetDocument, budgetPersonDetails, false)) {
            GlobalVariables.getErrorMap().putError("newBudgetLineItems[" + budgetCategoryTypeIndex + "].costElement", KeyConstants.ERROR_JOBCODE_COST_ELEMENT_COMBO_INVALID);
        }
        else{
            Map<String, String> primaryKeys = new HashMap<String, String>();
            primaryKeys.put("proposalNumber", budgetDocument.getProposalNumber());
            primaryKeys.put("budgetVersionNumber", budgetDocument.getBudgetVersionNumber().toString());
            primaryKeys.put("budgetPeriod", budgetForm.getViewBudgetPeriod().toString());
            BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);        
            List<BudgetPeriod> budgetPeriods = (List<BudgetPeriod>) businessObjectService.findMatching(BudgetPeriod.class, primaryKeys);
            BudgetPeriod budgetPeriod = null;
            if(CollectionUtils.isNotEmpty(budgetPeriods)) {
                budgetPeriod = budgetPeriods.get(0);
            }
            
            BudgetCategory newBudgetCategory = new BudgetCategory();
            newBudgetCategory.setBudgetCategoryTypeCode(getSelectedBudgetCategoryType(request));
            newBudgetCategory.refreshNonUpdateableReferences();
            
            boolean existingCeGroupCombo = false;
            int openTabLineItemIndex = -1;
            
            List<BudgetLineItem> existingPersonnelLineItems = new ArrayList<BudgetLineItem>();
            List<BudgetLineItem> existingLineItems = budgetForm.getDocument().getBudgetPeriod(budgetPeriod.getBudgetPeriod() - 1).getBudgetLineItems();
            
            if(GlobalVariables.getErrorMap().isEmpty()) {
                for(BudgetLineItem budgetLineItem : existingLineItems) {
                    budgetLineItem.refreshNonUpdateableReferences();
                    if(budgetLineItem.getBudgetCategory().getBudgetCategoryTypeCode().equalsIgnoreCase(newBudgetCategory.getBudgetCategoryTypeCode())) {
                        existingPersonnelLineItems.add(budgetLineItem);
                        if(newBudgetLineItem.getCostElement().equalsIgnoreCase(budgetLineItem.getCostElement()) && 
                                (StringUtils.equals(newBudgetLineItem.getGroupName(), budgetLineItem.getGroupName()) ||
                                        (StringUtils.isEmpty(newBudgetLineItem.getGroupName()) && StringUtils.isEmpty(budgetLineItem.getGroupName())))) { 
                            //Existing ObjCode / Group Name combo - add the new Person to the Line Item's Person List
                            if(budgetPersonDetails.getPersonSequenceNumber().intValue() != -1) {
                                //This is NOT a Summary entry
                                BudgetPersonnelExpenseRule budgetPersonnelExpenseRule = new BudgetPersonnelExpenseRule();
                                if(budgetPersonnelExpenseRule.processCheckPersonAddBusinessRules(budgetLineItem)) {
                                    addBudgetPersonnelDetails(budgetForm, budgetPeriod, budgetLineItem, budgetPersonDetails);
                                }  
                            } else if(! new BudgetPersonnelExpenseRule().processCheckSummaryAddBusinessRules(budgetLineItem)) {
                                existingCeGroupCombo = true;
                                break;
                            }
                            
                            openTabLineItemIndex = budgetLineItem.getLineItemNumber();
                            setLineItemQuantity(budgetLineItem);
                            //set some flag to indicate path of progress
                            existingCeGroupCombo = true;
                            break;
                        }
                    }
                }
                
                //If flag not set, then create a new Budget Line Item - ObjCode / Group Name combo is new
                if(!existingCeGroupCombo) {
                    newBudgetLineItem.setBudgetPeriod(budgetPeriod.getBudgetPeriod());
                    newBudgetLineItem.setBudgetCategory(newBudgetCategory);
                    
                    newBudgetLineItem.setStartDate(budgetDocument.getBudgetPeriod(budgetPeriod.getBudgetPeriod() - 1).getStartDate());
                    newBudgetLineItem.setEndDate(budgetForm.getDocument().getBudgetPeriod(budgetPeriod.getBudgetPeriod() - 1).getEndDate());
                    newBudgetLineItem.setStartDate(newBudgetLineItem.getStartDate());
                    newBudgetLineItem.setEndDate(newBudgetLineItem.getEndDate());
                    
                    newBudgetLineItem.setProposalNumber(budgetDocument.getBudgetPeriod(budgetPeriod.getBudgetPeriod() - 1).getProposalNumber());
                    newBudgetLineItem.setBudgetVersionNumber(budgetDocument.getBudgetPeriod(budgetPeriod.getBudgetPeriod() - 1).getBudgetVersionNumber());
                    newBudgetLineItem.setLineItemNumber(budgetForm.getDocument().getHackedDocumentNextValue(Constants.BUDGET_LINEITEM_NUMBER));
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
                    
                    if(budgetPersonDetails.getPersonSequenceNumber().intValue() != -1) {
                        addBudgetPersonnelDetails(budgetForm, budgetPeriod, newBudgetLineItem, budgetPersonDetails);
                    }
                    setLineItemQuantity(newBudgetLineItem);

                    budgetForm.getDocument().getBudgetPeriod(budgetPeriod.getBudgetPeriod() - 1).getBudgetLineItems().add(newBudgetLineItem);            
                    BudgetCalculationService budgetCalculationService = KraServiceLocator.getService(BudgetCalculationService.class);                          
                    budgetCalculationService.calculateBudgetPeriod(budgetForm.getDocument(), budgetForm.getDocument().getBudgetPeriod(budgetPeriod.getBudgetPeriod() - 1));
                    budgetCalculationService.populateCalculatedAmount(budgetDocument, newBudgetLineItem);
                    openTabLineItemIndex = newBudgetLineItem.getLineItemNumber();
                }
                
                BudgetLineItem newLineItemToAdd = new BudgetLineItem();
                budgetForm.getNewBudgetLineItems().set(budgetCategoryTypeIndex, newLineItemToAdd);
                request.setAttribute("openTabLineItemIndex", openTabLineItemIndex);
                budgetForm.setNewBudgetPersonnelDetails(new BudgetPersonnelDetails());
            }
        }       
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    } 
    
    private void setLineItemQuantity(BudgetLineItem personnelLineItem) {
        HashMap uniqueBudgetPersonnelCount = new HashMap();
        int qty = 0;
        for (BudgetPersonnelDetails budgetPersonnelDetails : personnelLineItem.getBudgetPersonnelDetailsList()) {
            if(!uniqueBudgetPersonnelCount.containsValue(budgetPersonnelDetails.getPersonId())){
                uniqueBudgetPersonnelCount.put(qty, budgetPersonnelDetails.getPersonId());
                qty = qty + 1;
            }
        }    
        personnelLineItem.setQuantity(new Integer(qty));
    }
    
    private void addBudgetPersonnelDetails(BudgetForm budgetForm, BudgetPeriod budgetPeriod, BudgetLineItem newBudgetLineItem, BudgetPersonnelDetails newBudgetPersonnelDetails) throws Exception {
        boolean errorFound = false;
        if (budgetForm.getNewBudgetPersonnelDetails().getPersonSequenceNumber() == null) {
            GlobalVariables.getErrorMap().putError("newBudgetPersonnelDetails.personSequenceNumber", RiceKeyConstants.ERROR_REQUIRED, new String[] { "Person (Person)" });
            errorFound=true;
        }
        
        if(!errorFound){
            BudgetPersonnelBudgetService budgetPersonnelBudgetService = KraServiceLocator.getService(BudgetPersonnelBudgetService.class);
            budgetPersonnelBudgetService.addBudgetPersonnelDetails(budgetForm.getDocument(), budgetPeriod, newBudgetLineItem, newBudgetPersonnelDetails);
            updatePersonnelBudgetRate(newBudgetLineItem);
            budgetForm.setNewBudgetPersonnelDetails(new BudgetPersonnelDetails());
        }        
    }  
    
    public ActionForward deleteBudgetPersonnelDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        int selectedBudgetPeriodIndex = budgetForm.getViewBudgetPeriod()-1;
        int selectedBudgetLineItemIndex = getSelectedLine(request); 
        BudgetPersonnelBudgetService budgetPersonnelBudgetService = KraServiceLocator.getService(BudgetPersonnelBudgetService.class);
        budgetPersonnelBudgetService.deleteBudgetPersonnelDetails(budgetForm.getDocument(), selectedBudgetPeriodIndex, selectedBudgetLineItemIndex, getSelectedPersonnel(request));
        
        HashMap uniqueBudgetPersonnelCount = new HashMap();
        int qty = 0;
        for (BudgetPersonnelDetails budgetPersonnelDetails : budgetForm.getDocument().getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(selectedBudgetLineItemIndex).getBudgetPersonnelDetailsList()) {
            if(!uniqueBudgetPersonnelCount.containsValue(budgetPersonnelDetails.getPersonId())){
                uniqueBudgetPersonnelCount.put(qty, budgetPersonnelDetails.getPersonId());
                qty = qty + 1;
            }
        }    
        budgetForm.getDocument().getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(selectedBudgetLineItemIndex).setQuantity(new Integer(qty));
        
        //If it is the last person to be deleted from the Line Item, then remove the line item also
        if(qty == 0) {
            budgetForm.getDocument().getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItems().remove(selectedBudgetLineItemIndex);
        }
        
        BudgetCalculationService budgetCalculationService = KraServiceLocator.getService(BudgetCalculationService.class);
        budgetCalculationService.calculateBudgetPeriod(budgetForm.getDocument(), budgetForm.getDocument().getBudgetPeriod(selectedBudgetPeriodIndex));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Parses the method to call attribute to pick off the line number which should have an action performed on it.
     *
     * @param request
     * @return
     */
    protected int getSelectedPersonnel(HttpServletRequest request) {
        int selectedPersonnel = -1;
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            String personnelIndex = StringUtils.substringBetween(parameterName, ".personnel", ".");
            selectedPersonnel = Integer.parseInt(personnelIndex);
        }

        return selectedPersonnel;
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
        BudgetDocument budgetDocument = budgetForm.getDocument();
        int selectedBudgetPeriodIndex = budgetForm.getViewBudgetPeriod()-1;
        int selectedBudgetLineItemIndex = getSelectedLine(request);   
        int selectedPersonnelIndex = getSelectedPersonnel(request);
        boolean errorFound = false;
        BudgetLineItem selectedBudgetLineItem = budgetDocument.getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(selectedBudgetLineItemIndex);
        BudgetPersonnelDetails budgetPersonnelDetails = selectedBudgetLineItem.getBudgetPersonnelDetailsList().get(selectedPersonnelIndex);
        
        errorFound = personnelDetailsCheck(budgetDocument, selectedBudgetPeriodIndex, selectedBudgetLineItemIndex, selectedPersonnelIndex);
        
        if(!errorFound){
            updatePersonnelBudgetRate(selectedBudgetLineItem);
            BudgetPersonnelBudgetService budgetPersonnelBudgetService = KraServiceLocator.getService(BudgetPersonnelBudgetService.class);
            budgetPersonnelBudgetService.calculateBudgetPersonnelBudget(budgetDocument, selectedBudgetLineItem, budgetPersonnelDetails, selectedPersonnelIndex);
            
            BudgetCalculationService budgetCalculationService = KraServiceLocator.getService(BudgetCalculationService.class); 
            budgetCalculationService.calculateBudgetPeriod(budgetForm.getDocument(), budgetForm.getDocument().getBudgetPeriod(selectedBudgetPeriodIndex));
            budgetCalculationService.populateCalculatedAmount(budgetDocument, selectedBudgetLineItem);
        }  
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    private boolean personnelDetailsCheck(BudgetDocument budgetDocument, int selectedBudgetPeriodIndex, int selectedBudgetLineItemIndex, int selectedPersonnelIndex) {
        BudgetLineItem selectedBudgetLineItem = budgetDocument.getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(selectedBudgetLineItemIndex);
        BudgetPersonnelDetails budgetPersonnelDetails = selectedBudgetLineItem.getBudgetPersonnelDetailsList().get(selectedPersonnelIndex);
        boolean errorFound = false;
        GlobalVariables.getErrorMap().addToErrorPath("document");
        
        if(StringUtils.isEmpty(budgetPersonnelDetails.getPeriodTypeCode())) { 
            GlobalVariables.getErrorMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex   +"].budgetLineItem[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + selectedPersonnelIndex + "].periodTypeCode", KeyConstants.ERROR_REQUIRED_PERIOD_TYPE);
            errorFound=true;
        }
        
        if(budgetPersonnelDetails.getPercentEffort().isLessThan(new BudgetDecimal(0)) 
                || budgetPersonnelDetails.getPercentEffort().isGreaterThan(new BudgetDecimal(100))){
            GlobalVariables.getErrorMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex   +"].budgetLineItem[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + selectedPersonnelIndex + "].percentEffort", KeyConstants.ERROR_PERCENTAGE, Constants.PERCENT_EFFORT_FIELD);
            errorFound=true;
        }
        if(budgetPersonnelDetails.getPercentCharged().isLessThan(new BudgetDecimal(0)) 
                || budgetPersonnelDetails.getPercentCharged().isGreaterThan(new BudgetDecimal(100))){
            GlobalVariables.getErrorMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItem[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + selectedPersonnelIndex + "].percentCharged", KeyConstants.ERROR_PERCENTAGE, Constants.PERCENT_CHARGED_FIELD);
            errorFound=true;
        }
        if(budgetPersonnelDetails.getPercentCharged().isGreaterThan(budgetPersonnelDetails.getPercentEffort())){
            GlobalVariables.getErrorMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItem[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + selectedPersonnelIndex + "].percentCharged", KeyConstants.ERROR_PERCENT_EFFORT_LESS_THAN_PERCENT_CHARGED);
            errorFound=true;
        }
        errorFound = errorFound || personnelDatesCheck(selectedBudgetLineItem, budgetPersonnelDetails, selectedBudgetPeriodIndex, selectedBudgetLineItemIndex, selectedPersonnelIndex);
        
        GlobalVariables.getErrorMap().removeFromErrorPath("document");
        return errorFound;
    }

    private boolean personnelDatesCheck(BudgetLineItem budgetLineItem, BudgetPersonnelDetails budgetPersonnelDetails, int selectedBudgetPeriodIndex, int selectedBudgetLineItemIndex, int detailIndex) {
        boolean errorFound = false;
        
        if(budgetPersonnelDetails.getStartDate() == null) {
            GlobalVariables.getErrorMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItem[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + detailIndex + "].startDate", RiceKeyConstants.ERROR_REQUIRED, new String[] { "Start Date (Start Date)" });
            errorFound=true;
        }
        if(budgetPersonnelDetails.getEndDate() == null) {
            GlobalVariables.getErrorMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItem[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + detailIndex + "].endDate", RiceKeyConstants.ERROR_REQUIRED, new String[] { "End Date (End Date)" });
            errorFound=true;
        }
        
        if(!errorFound) {
            if(budgetPersonnelDetails.getEndDate().compareTo(budgetPersonnelDetails.getStartDate()) < 0) {
                GlobalVariables.getErrorMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItem[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + detailIndex + "].endDate", KeyConstants.ERROR_PERSONNEL_DETAIL_DATES);
                errorFound=true;
            }
            if(budgetLineItem.getEndDate().compareTo(budgetPersonnelDetails.getEndDate()) < 0) {
                GlobalVariables.getErrorMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItem[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + detailIndex + "].endDate", KeyConstants.ERROR_PERSONNEL_DETAIL_END_DATE, new String[] {"can not be after", "end date"});
                errorFound=true;
            }
            if(budgetLineItem.getStartDate().compareTo(budgetPersonnelDetails.getEndDate()) > 0) {
                GlobalVariables.getErrorMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItem[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + detailIndex + "].endDate", KeyConstants.ERROR_PERSONNEL_DETAIL_END_DATE, new String[] {"can not be before", "start date"});
                errorFound=true;
            }
            if(budgetLineItem.getStartDate().compareTo(budgetPersonnelDetails.getStartDate()) > 0) {
                GlobalVariables.getErrorMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItem[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + detailIndex + "].startDate", KeyConstants.ERROR_PERSONNEL_DETAIL_START_DATE, new String[] {"can not be before", "start date"});
                errorFound=true;
            }
            if(budgetLineItem.getEndDate().compareTo(budgetPersonnelDetails.getStartDate()) < 0) {
                GlobalVariables.getErrorMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItem[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + detailIndex + "].startDate", KeyConstants.ERROR_PERSONNEL_DETAIL_START_DATE, new String[] {"can not be after", "end date"});
                errorFound=true;
            }
        }
        
        return errorFound;
    }

    
    private void populatePersonnelDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
            BudgetForm budgetForm = (BudgetForm) form;
            int selectedBudgetPeriodIndex = budgetForm.getViewBudgetPeriod()-1;
            BudgetPeriod selectedBudgetPeriod = budgetForm.getDocument().getBudgetPeriod(selectedBudgetPeriodIndex);
            
            int lineItemCounter = 0;
            List<BudgetLineItem> existingLineItems = budgetForm.getDocument().getBudgetPeriod(selectedBudgetPeriod.getBudgetPeriod() - 1).getBudgetLineItems();
            for(BudgetLineItem budgetLineItem : existingLineItems) {
                budgetLineItem.refreshNonUpdateableReferences();
                if(budgetLineItem.getBudgetCategory().getBudgetCategoryTypeCode().equalsIgnoreCase(getSelectedBudgetCategoryType(request))) {
                    boolean errorFound = false;
                    int personnelCounter = 0;
                    for(BudgetPersonnelDetails budgetPersonnelDetails: budgetLineItem.getBudgetPersonnelDetailsList()) {
                        if(budgetPersonnelDetails.getPercentEffort().isLessThan(new BudgetDecimal(0)) 
                                || budgetPersonnelDetails.getPercentEffort().isGreaterThan(new BudgetDecimal(100))){
                            GlobalVariables.getErrorMap().putError("document.budgetPeriod["+selectedBudgetPeriodIndex+"].budgetLineItem["+lineItemCounter+"].budgetPersonnelDetailsList["+personnelCounter+"].percentEffort", KeyConstants.ERROR_PERCENTAGE, Constants.PERCENT_EFFORT_FIELD);
                            //
                            errorFound=true;
                        }
                        if(budgetPersonnelDetails.getPercentCharged().isLessThan(new BudgetDecimal(0)) 
                                || budgetPersonnelDetails.getPercentCharged().isGreaterThan(new BudgetDecimal(100))){
                            GlobalVariables.getErrorMap().putError("document.budgetPeriod["+selectedBudgetPeriodIndex+"].budgetLineItem["+lineItemCounter+"].budgetPersonnelDetailsList["+personnelCounter+"].percentCharged", KeyConstants.ERROR_PERCENTAGE, Constants.PERCENT_CHARGED_FIELD);
                            errorFound=true;
                        }
                        if(budgetPersonnelDetails.getPercentCharged().isGreaterThan(budgetPersonnelDetails.getPercentEffort())){
                            GlobalVariables.getErrorMap().putError("document.budgetPeriod["+selectedBudgetPeriodIndex+"].budgetLineItem["+lineItemCounter+"].budgetPersonnelDetailsList["+personnelCounter+"].percentCharged",KeyConstants.ERROR_PERCENT_EFFORT_LESS_THAN_PERCENT_CHARGED);
                            errorFound=true;
                        }
                        if (StringUtils.isBlank((budgetPersonnelDetails.getPeriodTypeCode()))) {
                            GlobalVariables.getErrorMap().putError("document.budgetPeriod["+selectedBudgetPeriodIndex+"].budgetLineItem["+lineItemCounter+"].budgetPersonnelDetailsList["+personnelCounter+"].periodTypeCode", RiceKeyConstants.ERROR_REQUIRED, new String[] { "Period Type (Period Type)" });
                            errorFound=true;
                        }
                        personnelCounter++;
                    }
                    
                    HashMap uniqueBudgetPersonnelCount = new HashMap();
                    int qty = 0;
                    if(!errorFound){
                        for (BudgetPersonnelDetails budgetPersonnelDetails : budgetLineItem.getBudgetPersonnelDetailsList()) {
                            if(!uniqueBudgetPersonnelCount.containsValue(budgetPersonnelDetails.getPersonId())){
                                uniqueBudgetPersonnelCount.put(qty, budgetPersonnelDetails.getPersonId());
                                qty = qty + 1;
                            }
                        }    
                        budgetForm.getDocument().getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(lineItemCounter).setQuantity(new Integer(qty));
                        
                        //What happens here?? Should this be done for all Personnel Line Items??
                        updatePersonnelBudgetRate(budgetForm.getDocument().getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(lineItemCounter));
                        
                        BudgetCalculationService budgetCalculationService = KraServiceLocator.getService(BudgetCalculationService.class);
                        budgetCalculationService.calculateBudgetPeriod(budgetForm.getDocument(), budgetForm.getDocument().getBudgetPeriod(selectedBudgetPeriodIndex));
                    } 
                }
                lineItemCounter++;
            }
        } 
    
    /**
     * Overridden to populate BudgetPerson list with persons returned from lookups
     * 
     * @see org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase#refresh(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.refresh(mapping, form, request, response);
        BudgetForm budgetForm = (BudgetForm) form;
        
        // Process return from person/rolodex multi-value lookup
        if (budgetForm.getLookupResultsBOClassName() != null && budgetForm.getLookupResultsSequenceNumber() != null) {
            String lookupResultsSequenceNumber = budgetForm.getLookupResultsSequenceNumber();
            Class<?> lookupResultsBOClass = Class.forName(budgetForm.getLookupResultsBOClassName());
            
            Collection<PersistableBusinessObject> rawValues = KraServiceLocator.getService(LookupResultsService.class)
                .retrieveSelectedResultBOs(lookupResultsSequenceNumber, lookupResultsBOClass,
                        GlobalVariables.getUserSession().getPerson().getPrincipalName());
            
            BudgetPersonService budgetPersonService = KraServiceLocator.getService(BudgetPersonService.class);
            if (lookupResultsBOClass.isAssignableFrom(Person.class)) {
                for (Iterator iter = rawValues.iterator(); iter.hasNext();) {
                    Person person = (Person) iter.next();
                    BudgetPerson budgetPerson = new BudgetPerson(person);
                    populateAndAddBudgetPerson(budgetPerson, budgetForm.getDocument(), budgetPersonService);
                }
            } else if (lookupResultsBOClass.isAssignableFrom(NonOrganizationalRolodex.class)) {
                for (Iterator iter = rawValues.iterator(); iter.hasNext();) {
                    Rolodex rolodex = (Rolodex) iter.next();
                    BudgetPerson budgetPerson = new BudgetPerson(rolodex);
                    populateAndAddBudgetPerson(budgetPerson, budgetForm.getDocument(), budgetPersonService);
                }
            } else if (lookupResultsBOClass.isAssignableFrom(TbnPerson.class)) {
                for (Iterator iter = rawValues.iterator(); iter.hasNext();) {
                    TbnPerson tbn = (TbnPerson) iter.next();
                    BudgetPerson budgetPerson = new BudgetPerson(tbn);
                    populateAndAddBudgetPerson(budgetPerson, budgetForm.getDocument(), budgetPersonService);
                }
            }
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getDocument();
        List<Integer> toBeDeletedLineItems;
        
        for(BudgetPeriod budgetPeriod:budgetDocument.getBudgetPeriods()){
            int i = 0;
            toBeDeletedLineItems = new ArrayList<Integer>();
            for(BudgetLineItem budgetLineItem:budgetPeriod.getBudgetLineItems()){    
                if(budgetLineItem.getBudgetCategory().getBudgetCategoryTypeCode().equalsIgnoreCase(Constants.BUDGET_CATEGORY_PERSONNEL)) {
                    if(!StringUtils.equalsIgnoreCase(budgetLineItem.getCostElement(), budgetLineItem.getCostElementBO().getCostElement())){
                        budgetLineItem.refreshReferenceObject("costElementBO");
                        budgetLineItem.setBudgetCategoryCode(budgetLineItem.getCostElementBO().getBudgetCategoryCode());
                    }
                    updatePersonnelBudgetRate(budgetLineItem);
                    if(checkToRetainBudgetLineItem(budgetLineItem, budgetDocument)) {
                        toBeDeletedLineItems.add(i);
                    }
                }
                i++; 
            }
            
            for(Integer lineItemIndex: toBeDeletedLineItems) {
                budgetPeriod.getBudgetLineItems().remove(lineItemIndex.intValue());
            }
        }
        
        BudgetPersonnelRule personnelRule = new BudgetPersonnelRule();
        if (personnelRule.processCheckBaseSalaryFormat(budgetDocument) && personnelRule.processCheckForJobCodeChange(budgetDocument, budgetForm.getViewBudgetPeriod())) {
            BudgetPersonService budgetPersonService = KraServiceLocator.getService(BudgetPersonService.class);
            budgetPersonService.populateBudgetPersonDefaultDataIfEmpty(budgetDocument);
            
            if(budgetPersonnelDetailsCheck(budgetDocument) && new BudgetPersonnelExpenseRule().processSaveCheckDuplicateBudgetPersonnel(budgetDocument)) {
                super.save(mapping, form, request, response);
            }
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    private boolean budgetPersonnelDetailsCheck(BudgetDocument budgetDocument, int budgetPeriodIndex, int budgetLineItemIndex) {
        boolean valid = true;
        boolean validJobCodeCECombo = false;
        BudgetPersonnelRule budgetPersonnelRule = new BudgetPersonnelRule();
        
        BudgetPeriod selectedBudgetPeriod = budgetDocument.getBudgetPeriod(budgetPeriodIndex);
        BudgetLineItem selectedBudgetLineItem = selectedBudgetPeriod.getBudgetLineItem(budgetLineItemIndex);
        
        int k=0;
        for(BudgetPersonnelDetails budgetPersonnelDetails : selectedBudgetLineItem.getBudgetPersonnelDetailsList()) {
            valid &= !(personnelDetailsCheck(budgetDocument, budgetPeriodIndex, budgetLineItemIndex, k));
            
            validJobCodeCECombo = budgetPersonnelRule.processCheckJobCodeObjectCodeCombo(budgetDocument, budgetPersonnelDetails, true);
            if(!validJobCodeCECombo)  {
                GlobalVariables.getErrorMap().putError("document.budgetPeriod[" + budgetPeriodIndex   +"].budgetLineItem[" + budgetLineItemIndex + "].budgetPersonnelDetailsList[" + k + "].personSequenceNumber", KeyConstants.ERROR_SAVE_JOBCODE_COST_ELEMENT_COMBO_INVALID);
            }
            valid &= validJobCodeCECombo;
            k++;
        }
        
        return valid;
    }

    private boolean budgetPersonnelDetailsCheck(BudgetDocument budgetDocument) {
          boolean valid = true;
          List<BudgetPeriod> budgetPeriods = budgetDocument.getBudgetPeriods();
          List<BudgetLineItem> budgetLineItems;
          int i=0;
          int j=0;
          
          for(BudgetPeriod budgetPeriod: budgetPeriods){
              j=0;
              budgetLineItems = budgetPeriod.getBudgetLineItems();
              for(BudgetLineItem budgetLineItem: budgetLineItems){
                  if (budgetLineItem.getBudgetCategory().getBudgetCategoryTypeCode().equals("P")) {
                      valid &= budgetPersonnelDetailsCheck(budgetDocument, i, j);
                  }
                  j++;
              }
              i++;
          }
          
          return valid;
    }
    
        
    /**
     * 
     * This method is used to delete the proposal attachment
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward deleteBudgetPerson(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        BudgetDocument budgetDocument = ((BudgetForm) form).getDocument();
        if (!new BudgetPersonnelRule().processCheckExistBudgetPersonnelDetailsBusinessRules(budgetDocument, budgetDocument.getBudgetPerson(getLineToDelete(request)))) {
            return mapping.findForward(MAPPING_BASIC);
        } else {
            return confirm(buildDeleteBudgetPersonConfirmationQuestion(mapping, form, request, response), CONFIRM_DELETE_BUDGET_PERSON, "");
        }
    }

    /**
     * 
     * This method is used to delete the proposal attachment
     * @param mapping The mapping associated with this action.
     * @param form The Proposal Development form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the destination (always the original proposal web page that caused this action to be invoked)
     * @throws Exception
     */
    public ActionForward confirmDeleteBudgetPerson(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        Object question = request.getParameter(QUESTION_INST_ATTRIBUTE_NAME);
        if (CONFIRM_DELETE_BUDGET_PERSON.equals(question)) {
            BudgetDocument budgetDocument = ((BudgetForm) form).getDocument();
            getBudgetPersonnelBudgetService()
                .deleteBudgetPersonnelDetailsForPerson(budgetDocument,             
                                                       budgetDocument.getBudgetPerson(getLineToDelete(request)));            

            budgetDocument.getBudgetPersons().remove(getLineToDelete(request));            
        }
        
        return mapping.findForward(MAPPING_BASIC);
    }
    
    /**
     * @see org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase#reload(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        Document document = budgetForm.getDocument();
        // check authorization for reloading document
        if (!budgetForm.getDocumentActions().containsKey(KNSConstants.KUALI_ACTION_CAN_RELOAD)) {
            throw buildAuthorizationException("reload", document);
        }

        // prepare for the reload action - set doc id and command
        budgetForm.setDocId(document.getDocumentNumber());
        budgetForm.setCommand(KEWConstants.DOCSEARCH_COMMAND);
        // forward off to the doc handler
        ActionForward actionForward = docHandler(mapping, form, request, response);
        GlobalVariables.getMessageList().add(RiceKeyConstants.MESSAGE_RELOADED);
        
        reconcilePersonnelRoles(budgetForm.getDocument());
        populatePersonnelCategoryTypeCodes(budgetForm);  
        
        BudgetDocument budgetDocument = budgetForm.getDocument();

        for(BudgetPeriod budgetPeriod:budgetDocument.getBudgetPeriods()){
            for(BudgetLineItem budgetLineItem:budgetPeriod.getBudgetLineItems()){                
                if(!StringUtils.equalsIgnoreCase(budgetLineItem.getCostElement(), budgetLineItem.getCostElementBO().getCostElement())){
                    budgetLineItem.refreshReferenceObject("costElementBO");
                    budgetLineItem.setBudgetCategoryCode(budgetLineItem.getCostElementBO().getBudgetCategoryCode());
                }
                updatePersonnelBudgetRate(budgetLineItem);
            }
        }
        
        return actionForward;
    }
        
    /**
     * This method synchs budget personnel with proposal personnel
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward synchToProposal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        KraServiceLocator.getService(BudgetPersonService.class).synchBudgetPersonsToProposal(budgetForm.getDocument());
        return mapping.findForward(MAPPING_BASIC);
    }
    
    /**
     * Convenience method for adding populating a new budget person and adding to budget document
     * 
     * @param budgetPerson
     * @param budgetDocument
     * @param budgetPersonService
     */
    private void populateAndAddBudgetPerson(BudgetPerson budgetPerson, BudgetDocument budgetDocument, BudgetPersonService budgetPersonService) {
        budgetPersonService.populateBudgetPersonData(budgetDocument, budgetPerson);
        budgetDocument.addBudgetPerson(budgetPerson);
    }
    
    /**
     * Builds the Delete Abstract Confirmation Question as a <code>{@link StrutsConfirmation}</code> instance.<br/>  
     * <br/>
     * The confirmation question is extracted from the resource bundle
     * and the parameter {0} is replaced with the name of the abstract type
     * that will be deleted.
     * 
     * @param mapping The mapping associated with this action.
     * @param form The Proposal Development form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @param questionId String questionId. This needs to be unique for each type of attachment because there are different attachments to delete.
     * @return the confirmation question
     * @throws Exception
     * @see buildParameterizedConfirmationQuestion
     */
    private StrutsConfirmation buildDeleteBudgetPersonConfirmationQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetDocument budgetDocument = ((BudgetForm) form).getDocument();
        String personName = budgetDocument.getBudgetPerson(getLineToDelete(request)).getPersonName();
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_DELETE_BUDGET_PERSON, KeyConstants.QUESTION_DELETE_PERSON, personName);
    }

    private BudgetPersonnelBudgetService getBudgetPersonnelBudgetService() {
        return KraServiceLocator.getService(BudgetPersonnelBudgetService.class);
    }
    
        public ActionForward personnelRates(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        int budgetPeriodNumber = Integer.parseInt(request.getParameter("budgetPeriod"));
        int budgetLineItemNumber = Integer.parseInt(request.getParameter("line"));
        String rateClassCode = request.getParameter("rateClassCode");
        String rateTypeCode = request.getParameter("rateTypeCode");
        
        String fieldName = request.getParameter("fieldName");
        
        BudgetPeriod selectedBudgetPeriod = budgetForm.getDocument().getBudgetPeriod(budgetPeriodNumber-1);
        BudgetLineItem selectedLineItem = selectedBudgetPeriod.getBudgetLineItem(budgetLineItemNumber);

        for(BudgetPersonnelDetails budgetPersonnelDetails : selectedLineItem.getBudgetPersonnelDetailsList()) {
            budgetPersonnelDetails.refreshReferenceObject("budgetPerson");
        }
        
        //Update the rates
        updatePersonnelBudgetRate(selectedLineItem);
        
        request.setAttribute("budgetPeriod", budgetPeriodNumber);
        request.setAttribute("lineNumber", budgetLineItemNumber);
        request.setAttribute("rateClassCode", rateClassCode);
        request.setAttribute("rateTypeCode", rateTypeCode);
        request.setAttribute("fieldName", fieldName);
        
        return mapping.findForward("personnelRates");
    }
    
    private boolean checkForDeletedPerson(BudgetDocument budgetDocument, BudgetPersonnelDetails budgetPersonnelDetails) {
        for(BudgetPerson person : budgetDocument.getBudgetPersons()) {
            //if(person.isDuplicatePerson(budgetPersonnelDetails.getBudgetPerson())) {
            if(person.getPersonSequenceNumber().intValue() == budgetPersonnelDetails.getPersonSequenceNumber().intValue()) {
               return false;
            }
        }
        return true; 
    }
    
    private boolean checkToRetainBudgetLineItem(BudgetLineItem budgetLineItem, BudgetDocument budgetDocument) {
        List<Integer> deletedPersons = new ArrayList<Integer>();
        int originalPersonListSize = budgetLineItem.getBudgetPersonnelDetailsList().size();
        
        int i = 0;
        for(BudgetPersonnelDetails budgetPersonnelDetails: budgetLineItem.getBudgetPersonnelDetailsList()){
            if(budgetPersonnelDetails.getBudgetPerson() == null) {
                budgetPersonnelDetails.refreshReferenceObject("budgetPerson");
            }
            
            if(budgetPersonnelDetails.getBudgetPerson() != null && checkForDeletedPerson(budgetDocument, budgetPersonnelDetails)) {
                //budget person was deleted after that person was budgeted.
                deletedPersons.add(i);
            }
            i++;
        }
        for(Integer deletedPersonIndex : deletedPersons) {
            budgetLineItem.getBudgetPersonnelDetailsList().remove(deletedPersonIndex.intValue());
        }
        if(originalPersonListSize > 0 && deletedPersons.size() == originalPersonListSize) {
            return true;
        }
        
        return false;
    }
    
    public void updatePersonnelBudgetRate(BudgetLineItem budgetLineItem){
        int j = 0;
        
        for(BudgetPersonnelDetails budgetPersonnelDetails: budgetLineItem.getBudgetPersonnelDetailsList()){
            budgetPersonnelDetails.setApplyInRateFlag(budgetLineItem.getApplyInRateFlag());
            budgetPersonnelDetails.setOnOffCampusFlag(budgetLineItem.getOnOffCampusFlag());
            if(budgetPersonnelDetails.getBudgetPerson() == null) {
                budgetPersonnelDetails.refreshReferenceObject("budgetPerson");
            }
            
            j=0;
            for(BudgetPersonnelCalculatedAmount budgetPersonnelCalculatedAmount:budgetPersonnelDetails.getBudgetPersonnelCalculatedAmounts()){
                Boolean updatedApplyRateFlag = null;
                if(budgetPersonnelCalculatedAmount.getRateClass() == null) {
                    budgetPersonnelCalculatedAmount.refreshReferenceObject("rateClass");
                }
                
                for (BudgetLineItemCalculatedAmount budgetLineItemCalculatedAmout : budgetLineItem.getBudgetLineItemCalculatedAmounts()) {
                    if(budgetLineItemCalculatedAmout.getRateClassCode().equalsIgnoreCase(budgetPersonnelCalculatedAmount.getRateClassCode()) && 
                            budgetLineItemCalculatedAmout.getRateTypeCode().equalsIgnoreCase(budgetPersonnelCalculatedAmount.getRateTypeCode())) {
                        updatedApplyRateFlag = budgetLineItemCalculatedAmout.getApplyRateFlag();
                    }
                }
                budgetPersonnelCalculatedAmount.setApplyRateFlag(updatedApplyRateFlag);                        
                j++;
            }
        }
        
        for(BudgetLineItemCalculatedAmount lineItemCalculatedAmount: budgetLineItem.getBudgetLineItemCalculatedAmounts()){
            if(lineItemCalculatedAmount.getRateClass() == null) {
                lineItemCalculatedAmount.refreshReferenceObject("rateClass"); 
            }
        }
    }
    
    public ActionForward personnelDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        int budgetPeriodNumber = Integer.parseInt(request.getParameter("budgetPeriod"));
        int budgetLineItemNumber = Integer.parseInt(request.getParameter("line"));
        int personNumber = Integer.parseInt(request.getParameter("personNumber"));
        
        BudgetPeriod selectedBudgetPeriod = budgetForm.getDocument().getBudgetPeriod(budgetPeriodNumber-1);
        BudgetLineItem selectedLineItem = selectedBudgetPeriod.getBudgetLineItem(budgetLineItemNumber);

        BudgetPersonnelDetails selectedBudgetPersonnelDetails = selectedLineItem.getBudgetPersonnelDetails(personNumber);
        selectedBudgetPersonnelDetails.refreshReferenceObject("budgetPerson");
        
        request.setAttribute("budgetPeriod", budgetPeriodNumber);
        request.setAttribute("lineNumber", budgetLineItemNumber);
        request.setAttribute("personNumber", personNumber);
        
        return mapping.findForward("personnelDetails");
    }
    
    public ActionForward savePersonnelDescription(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        int budgetPeriodNumber = Integer.parseInt(request.getParameter("budgetPeriod"));
        int budgetLineItemNumber = Integer.parseInt(request.getParameter("line"));
        int personNumber = Integer.parseInt(request.getParameter("personnelIndex"));
        
        BudgetPeriod selectedBudgetPeriod = budgetForm.getDocument().getBudgetPeriod(budgetPeriodNumber-1);
        BudgetLineItem selectedLineItem = selectedBudgetPeriod.getBudgetLineItem(budgetLineItemNumber);

        System.out.println("getLineItemDescription: " + selectedLineItem.getBudgetPersonnelDetails(personNumber).getLineItemDescription());
        return mapping.findForward(MAPPING_CLOSE_PAGE);
    }
    
    //For the Summary View - Calculations
    public ActionForward calculateLineItem(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        BudgetDocument budgetDocument = budgetForm.getDocument();
        int selectedBudgetPeriodIndex = budgetForm.getViewBudgetPeriod()-1;
        int selectedBudgetLineItemIndex = getSelectedLine(request);   
        BudgetLineItem selectedBudgetLineItem = budgetForm.getDocument().getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(selectedBudgetLineItemIndex);
        
        if (new BudgetExpenseRule().processCheckLineItemDates(budgetDocument)) {
            updatePersonnelBudgetRate(selectedBudgetLineItem);
            BudgetCalculationService budgetCalculationService = KraServiceLocator.getService(BudgetCalculationService.class);
            budgetCalculationService.calculateBudgetLineItem(budgetForm.getDocument(), selectedBudgetLineItem);
        }
        
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
        BudgetDocument budgetDocument = budgetForm.getDocument();        
        BudgetCalculationService budgetCalculationService  = KraServiceLocator.getService(BudgetCalculationService.class);
        int sltdLineItem = getSelectedLine(request);
        int sltdBudgetPeriod = budgetForm.getViewBudgetPeriod()-1;
        BudgetExpenseRule budgetExpenseRule = new BudgetExpenseRule();
        if (budgetExpenseRule.processApplyToLaterPeriodsWithPersonnelDetails(budgetDocument, budgetDocument.getBudgetPeriod(sltdBudgetPeriod), budgetDocument.getBudgetPeriod(sltdBudgetPeriod).getBudgetLineItem(sltdLineItem), sltdLineItem) &&
                budgetExpenseRule.processCheckLineItemDates(budgetDocument.getBudgetPeriod(sltdBudgetPeriod), sltdLineItem) && 
                budgetPersonnelDetailsCheck(budgetDocument, sltdBudgetPeriod, sltdLineItem) && 
                new BudgetPersonnelExpenseRule().processCheckDuplicateBudgetPersonnel(budgetDocument, sltdBudgetPeriod, sltdLineItem)
                ) {
            budgetCalculationService.applyToLaterPeriods(budgetDocument, budgetDocument.getBudgetPeriod(sltdBudgetPeriod), budgetDocument.getBudgetPeriod(sltdBudgetPeriod).getBudgetLineItem(sltdLineItem));
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
   
}
