/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.budget.framework.personnel.*;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.framework.rolodex.NonOrganizationalRolodex;
import org.kuali.coeus.sys.framework.controller.StrutsConfirmation;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetConstants;
import org.kuali.coeus.common.budget.framework.core.SaveBudgetEvent;
import org.kuali.coeus.common.budget.framework.core.category.BudgetCategory;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItemCalculatedAmount;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.core.BudgetForm;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kns.lookup.LookupResultsService;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;
import static org.kuali.kra.infrastructure.Constants.MAPPING_CLOSE_PAGE;
import static org.kuali.rice.krad.util.KRADConstants.QUESTION_INST_ATTRIBUTE_NAME;

/**
 * Action class for Budget Personnel page
 */
public class BudgetPersonnelAction extends BudgetExpensesAction {
    
    private static final String CONFIRM_DELETE_BUDGET_PERSON = "confirmDeleteBudgetPerson";
    private static final String EMPTY_GROUP_NAME = "";
    private static final String DEFAULT_GROUP_NAME = "(new group)";
    private static final String BUDGET_PERSONS_FIELD_NAME_START = "budgetPersons[";
    private static final String BUDGET_PERSONS_FIELD_NAME_PERSON_NUMBER = "].personNumber";
    
    private BudgetPersonnelBudgetService budgetPersonnelBudgetService;
    private BudgetPersonService budgetPersonService;
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudget();
        // for fixing audit error
        if (budget.getBudgetCategoryTypeCodes() == null || budget.getBudgetCategoryTypeCodes().size() == 0) {
            populatePersonnelCategoryTypeCodes(budgetForm);
        }
        ActionForward forward = super.execute(mapping, form, request, response);
                 
        return forward;
    }
   
    public ActionForward closePopUp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        budgetForm.setViewDivFlag(false);
        return mapping.findForward(Constants.BUDGET_PERSONNEL_PAGE);

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
        Budget budget = budgetForm.getBudget();

        Integer budgetCategoryTypeIndex = Integer.parseInt(getBudgetCategoryTypeIndex(request));
        BudgetLineItem newBudgetLineItem = budgetForm.getNewBudgetLineItems().get(budgetCategoryTypeIndex);
        final BudgetPersonnelDetails budgetPersonDetails = budgetForm.getNewBudgetPersonnelDetails();
        budgetPersonDetails.setBudgetId(budget.getBudgetId());
        budgetPersonDetails.setPeriodTypeCode(this.getParameterService().getParameterValueAsString(
                Budget.class, Constants.BUDGET_PERSON_DETAILS_DEFAULT_PERIODTYPE));
        budgetPersonDetails.setCostElement(newBudgetLineItem.getCostElement());
        budgetPersonDetails.setBudgetPerson(CollectionUtils.find(budget.getBudgetPersons(), new Predicate<BudgetPerson>() {
            @Override
            public boolean evaluate(BudgetPerson object) {
                return object != null && object.getBudgetId().equals(budgetPersonDetails.getBudgetId()) && object.getPersonSequenceNumber().equals(budgetPersonDetails.getPersonSequenceNumber());
            }
        }));
	    String groupErrorKey = "";
	    if(StringUtils.isNotEmpty(newBudgetLineItem.getGroupName())) {
	        groupErrorKey = "newBudgetLineItems[" + budgetCategoryTypeIndex + "].costElement" ;
	    }
	    if(StringUtils.isEmpty(newBudgetLineItem.getGroupName())) {
	    	newBudgetLineItem.setGroupName(budgetForm.getNewGroupName());
	        groupErrorKey = "newGroupName";
	    }

        //if the group name is empty or still the default group name(JS error?) then set the group name to the empty string.
        if(StringUtils.isEmpty(newBudgetLineItem.getGroupName()) || StringUtils.equals(newBudgetLineItem.getGroupName(), DEFAULT_GROUP_NAME)) {
            newBudgetLineItem.setGroupName(EMPTY_GROUP_NAME);  
        }

        if (getKcBusinessRulesEngine().applyRules(new AddPersonnelBudgetEvent(budget, budget.getBudgetPeriod(budgetForm.getViewBudgetPeriod() - 1),
        		newBudgetLineItem, budgetPersonDetails, groupErrorKey))) {
            Map<String, Object> primaryKeys = new HashMap<String, Object>();
            primaryKeys.put("budgetId", budget.getBudgetId());
            primaryKeys.put("budgetPeriod", budgetForm.getViewBudgetPeriod().toString());
            List<BudgetPeriod> budgetPeriods = (List<BudgetPeriod>) getBusinessObjectService().findMatching(BudgetPeriod.class, primaryKeys);
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
            List<BudgetLineItem> existingLineItems = budget.getBudgetPeriod(budgetPeriod.getBudgetPeriod() - 1).getBudgetLineItems();
            
            if(GlobalVariables.getMessageMap().hasNoErrors()) {
                for(BudgetLineItem budgetLineItem : existingLineItems) {
                    budgetLineItem.refreshNonUpdateableReferences();
                    if(budgetLineItem.getBudgetCategory().getBudgetCategoryTypeCode().equalsIgnoreCase(newBudgetCategory.getBudgetCategoryTypeCode())) {
                        existingPersonnelLineItems.add(budgetLineItem);
                        if(newBudgetLineItem.getCostElement().equalsIgnoreCase(budgetLineItem.getCostElement()) && 
                                (StringUtils.equals(newBudgetLineItem.getGroupName(), budgetLineItem.getGroupName()) ||
                                        (StringUtils.isEmpty(newBudgetLineItem.getGroupName()) && StringUtils.isEmpty(budgetLineItem.getGroupName())))) { 
                            //Existing ObjCode / Group Name combo - add the new Person to the Line Item's Person List
                            if (budgetPersonDetails.getPersonSequenceNumber().intValue() != -1) {
                                //This is NOT a Summary entry
                                if(getKcBusinessRulesEngine().applyRules(new AddPersonnelLineItemBudgetEvent(budget, "newBudgetPersonnelDetails", budgetLineItem))) {
                        			addBudgetPersonnelDetails(budgetForm, budgetPeriod, budgetLineItem, budgetPersonDetails);
                        		}
                            } else if (!getKcBusinessRulesEngine().applyRules(new AddSummaryPersonnelLineItemBudgetEvent(budget, "newBudgetPersonnelDetails", budgetLineItem))){
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
                    newBudgetLineItem.setBudgetPeriodId(budgetPeriod.getBudgetPeriodId());
                    newBudgetLineItem.setBudgetPeriodBO(budgetPeriod);
                    newBudgetLineItem.setBudgetCategory(newBudgetCategory);
                    
                    newBudgetLineItem.setStartDate(budget.getBudgetPeriod(budgetPeriod.getBudgetPeriod() - 1).getStartDate());
                    newBudgetLineItem.setEndDate(budget.getBudgetPeriod(budgetPeriod.getBudgetPeriod() - 1).getEndDate());
                    newBudgetLineItem.setStartDate(newBudgetLineItem.getStartDate());
                    newBudgetLineItem.setEndDate(newBudgetLineItem.getEndDate());
                    
                    newBudgetLineItem.setBudgetId(budget.getBudgetId());
                    newBudgetLineItem.setLineItemNumber(budgetForm.getBudgetDocument().getHackedDocumentNextValue(Constants.BUDGET_LINEITEM_NUMBER));
                    newBudgetLineItem.setApplyInRateFlag(true);
                    newBudgetLineItem.refreshReferenceObject("costElementBO");
                    
                    // on/off campus flag enhancement
                    String onOffCampusFlag = budget.getOnOffCampusFlag();
                    if (onOffCampusFlag.equalsIgnoreCase(BudgetConstants.DEFAULT_CAMPUS_FLAG)) {
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

                    budget.getBudgetPeriod(budgetPeriod.getBudgetPeriod() - 1).getBudgetLineItems().add(newBudgetLineItem);            
                    recalculateBudgetPeriod(budgetForm,budget, budget.getBudgetPeriod(budgetPeriod.getBudgetPeriod() - 1));
                    getCalculationService().populateCalculatedAmount(budget, newBudgetLineItem);
                    openTabLineItemIndex = newBudgetLineItem.getLineItemNumber();
                }
                
                BudgetLineItem newLineItemToAdd = budget.getNewBudgetLineItem();
                budgetForm.getNewBudgetLineItems().set(budgetCategoryTypeIndex, newLineItemToAdd);
                request.setAttribute("openTabLineItemIndex", openTabLineItemIndex);
                budgetForm.setNewBudgetPersonnelDetails(newLineItemToAdd.getNewBudgetPersonnelLineItem());
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
            GlobalVariables.getMessageMap().putError("newBudgetPersonnelDetails.personSequenceNumber", RiceKeyConstants.ERROR_REQUIRED, new String[] { "Person (Person)" });
            errorFound=true;
        }
        
        if(!errorFound){
            getBudgetPersonnelBudgetService().addBudgetPersonnelDetails(budgetForm.getBudgetDocument().getBudget(), budgetPeriod, newBudgetLineItem, newBudgetPersonnelDetails);
            updatePersonnelBudgetRate(newBudgetLineItem);
            budgetForm.setNewBudgetPersonnelDetails(newBudgetLineItem.getNewBudgetPersonnelLineItem());
        }        
    }  
    
    public ActionForward deleteBudgetPersonnelDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudget();
        int selectedBudgetPeriodIndex = budgetForm.getViewBudgetPeriod()-1;
        int selectedBudgetLineItemIndex = getSelectedLine(request); 
        getBudgetPersonnelBudgetService().deleteBudgetPersonnelDetails(budget, selectedBudgetPeriodIndex, selectedBudgetLineItemIndex, getSelectedPersonnel(request));
        
        HashMap uniqueBudgetPersonnelCount = new HashMap();
        int qty = 0;
        for (BudgetPersonnelDetails budgetPersonnelDetails : budget.getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(selectedBudgetLineItemIndex).getBudgetPersonnelDetailsList()) {
            if(!uniqueBudgetPersonnelCount.containsValue(budgetPersonnelDetails.getPersonId())){
                uniqueBudgetPersonnelCount.put(qty, budgetPersonnelDetails.getPersonId());
                qty = qty + 1;
            }
        }    
        budget.getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(selectedBudgetLineItemIndex).setQuantity(new Integer(qty));
        
        //If it is the last person to be deleted from the Line Item, then remove the line item also
        if(qty == 0) {
            budget.getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItems().remove(selectedBudgetLineItemIndex);
        }
        
        recalculateBudgetPeriod(budgetForm,budget, budget.getBudgetPeriod(selectedBudgetPeriodIndex));
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
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
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
        Budget budget = budgetForm.getBudget();
        int selectedBudgetPeriodIndex = budgetForm.getViewBudgetPeriod()-1;
        int selectedBudgetLineItemIndex = getSelectedLine(request);   
        int selectedPersonnelIndex = getSelectedPersonnel(request);
        boolean errorFound = false;
        BudgetLineItem selectedBudgetLineItem = budget.getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(selectedBudgetLineItemIndex);
        BudgetPersonnelDetails budgetPersonnelDetails = selectedBudgetLineItem.getBudgetPersonnelDetailsList().get(selectedPersonnelIndex);
        
        errorFound = personnelDetailsCheck(budget, selectedBudgetPeriodIndex, selectedBudgetLineItemIndex, selectedPersonnelIndex);
        
        if(!errorFound){
            updatePersonnelBudgetRate(selectedBudgetLineItem);
            getBudgetPersonnelBudgetService().calculateBudgetPersonnelBudget(budget, selectedBudgetLineItem, budgetPersonnelDetails, selectedPersonnelIndex);
            
            recalculateBudgetPeriod(budgetForm,budget, budget.getBudgetPeriod(selectedBudgetPeriodIndex));
            getCalculationService().populateCalculatedAmount(budget, selectedBudgetLineItem);
        }  
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    private boolean personnelDetailsCheck(Budget budget, int selectedBudgetPeriodIndex, int selectedBudgetLineItemIndex, int selectedPersonnelIndex) {
        BudgetLineItem selectedBudgetLineItem = budget.getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(selectedBudgetLineItemIndex);
        BudgetPersonnelDetails budgetPersonnelDetails = selectedBudgetLineItem.getBudgetPersonnelDetailsList().get(selectedPersonnelIndex);
        boolean errorFound = false;
        GlobalVariables.getMessageMap().addToErrorPath("document");
        
        if(StringUtils.isEmpty(budgetPersonnelDetails.getPeriodTypeCode())) { 
            GlobalVariables.getMessageMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex   +"].budgetLineItem[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + selectedPersonnelIndex + "].periodTypeCode", KeyConstants.ERROR_REQUIRED_PERIOD_TYPE);
            errorFound=true;
        }
        
        if(budgetPersonnelDetails.getPercentEffort().isGreaterThan(new ScaleTwoDecimal(100))){
            GlobalVariables.getMessageMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex   +"].budgetLineItem[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + selectedPersonnelIndex + "].percentEffort", KeyConstants.ERROR_PERCENTAGE, Constants.PERCENT_EFFORT_FIELD);
            errorFound=true;
        }
        if(budgetPersonnelDetails.getPercentCharged().isGreaterThan(new ScaleTwoDecimal(100))){
            GlobalVariables.getMessageMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItem[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + selectedPersonnelIndex + "].percentCharged", KeyConstants.ERROR_PERCENTAGE, Constants.PERCENT_CHARGED_FIELD);
            errorFound=true;
        }
        if(budgetPersonnelDetails.getPercentCharged().isGreaterThan(budgetPersonnelDetails.getPercentEffort())){
            GlobalVariables.getMessageMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItem[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + selectedPersonnelIndex + "].percentCharged", KeyConstants.ERROR_PERCENT_EFFORT_LESS_THAN_PERCENT_CHARGED);
            errorFound=true;
        }
        errorFound = errorFound || personnelDatesCheck(selectedBudgetLineItem, budgetPersonnelDetails, selectedBudgetPeriodIndex, selectedBudgetLineItemIndex, selectedPersonnelIndex);
        
        GlobalVariables.getMessageMap().removeFromErrorPath("document");
        return errorFound;
    }

    private boolean personnelDatesCheck(BudgetLineItem budgetLineItem, BudgetPersonnelDetails budgetPersonnelDetails, int selectedBudgetPeriodIndex, int selectedBudgetLineItemIndex, int detailIndex) {
        boolean errorFound = false;
        
        if(budgetPersonnelDetails.getStartDate() == null) {
            GlobalVariables.getMessageMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItem[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + detailIndex + "].startDate", RiceKeyConstants.ERROR_REQUIRED, new String[] { "Start Date (Start Date)" });
            errorFound=true;
        }
        if(budgetPersonnelDetails.getEndDate() == null) {
            GlobalVariables.getMessageMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItem[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + detailIndex + "].endDate", RiceKeyConstants.ERROR_REQUIRED, new String[] { "End Date (End Date)" });
            errorFound=true;
        }
        
        if(!errorFound) {
            if(budgetPersonnelDetails.getEndDate().compareTo(budgetPersonnelDetails.getStartDate()) < 0) {
                GlobalVariables.getMessageMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItem[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + detailIndex + "].endDate", KeyConstants.ERROR_PERSONNEL_DETAIL_DATES);
                errorFound=true;
            }
            if(budgetLineItem.getEndDate().compareTo(budgetPersonnelDetails.getEndDate()) < 0) {
                GlobalVariables.getMessageMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItem[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + detailIndex + "].endDate", KeyConstants.ERROR_PERSONNEL_DETAIL_END_DATE, new String[] {"can not be after", "end date"});
                errorFound=true;
            }
            if(budgetLineItem.getStartDate().compareTo(budgetPersonnelDetails.getEndDate()) > 0) {
                GlobalVariables.getMessageMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItem[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + detailIndex + "].endDate", KeyConstants.ERROR_PERSONNEL_DETAIL_END_DATE, new String[] {"can not be before", "start date"});
                errorFound=true;
            }
            if(budgetLineItem.getStartDate().compareTo(budgetPersonnelDetails.getStartDate()) > 0) {
                GlobalVariables.getMessageMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItem[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + detailIndex + "].startDate", KeyConstants.ERROR_PERSONNEL_DETAIL_START_DATE, new String[] {"can not be before", "start date"});
                errorFound=true;
            }
            if(budgetLineItem.getEndDate().compareTo(budgetPersonnelDetails.getStartDate()) < 0) {
                GlobalVariables.getMessageMap().putError("budgetPeriod[" + selectedBudgetPeriodIndex +"].budgetLineItem[" + selectedBudgetLineItemIndex + "].budgetPersonnelDetailsList[" + detailIndex + "].startDate", KeyConstants.ERROR_PERSONNEL_DETAIL_START_DATE, new String[] {"can not be after", "end date"});
                errorFound=true;
            }
        }
        
        return errorFound;
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
            
            @SuppressWarnings("unchecked")
            Class<BusinessObject> lookupResultsBOClass = (Class<BusinessObject>) Class.forName(budgetForm.getLookupResultsBOClassName());
            
            Collection<BusinessObject> rawValues = KcServiceLocator.getService(LookupResultsService.class)
                .retrieveSelectedResultBOs(lookupResultsSequenceNumber, lookupResultsBOClass,
                        GlobalVariables.getUserSession().getPerson().getPrincipalId());
            
            if (lookupResultsBOClass.isAssignableFrom(KcPerson.class)) {
                for (Iterator iter = rawValues.iterator(); iter.hasNext();) {
                    KcPerson person = (KcPerson) iter.next();
                    BudgetPerson budgetPerson = new BudgetPerson(person);
                    populateAndAddBudgetPerson(budgetPerson, budgetForm.getBudget());
                }
            } else if (lookupResultsBOClass.isAssignableFrom(NonOrganizationalRolodex.class)) {
                for (Iterator iter = rawValues.iterator(); iter.hasNext();) {
                    Rolodex rolodex = (Rolodex) iter.next();
                    BudgetPerson budgetPerson = new BudgetPerson(rolodex);
                    populateAndAddBudgetPerson(budgetPerson, budgetForm.getBudget());
                }
            } else if (lookupResultsBOClass.isAssignableFrom(TbnPerson.class)) {
                for (Iterator iter = rawValues.iterator(); iter.hasNext();) {
                    TbnPerson tbn = (TbnPerson) iter.next();
                    BudgetPerson budgetPerson = new BudgetPerson(tbn);
                    populateAndAddBudgetPerson(budgetPerson, budgetForm.getBudget());
                }
            }
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudget();
        List<Integer> toBeDeletedLineItems;
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        for(BudgetPeriod budgetPeriod:budget.getBudgetPeriods()){
            int i = 0;
            toBeDeletedLineItems = new ArrayList<Integer>();
            for(BudgetLineItem budgetLineItem:budgetPeriod.getBudgetLineItems()){
            	if (budgetLineItem.getBudgetCategory() == null && budgetLineItem.getBudgetCategoryCode() != null) {
            		budgetLineItem.setBudgetCategory(getBusinessObjectService().findBySinglePrimaryKey(BudgetCategory.class, budgetLineItem.getBudgetCategoryCode()));
            	}
                if(StringUtils.equalsIgnoreCase(budgetLineItem.getBudgetCategory().getBudgetCategoryTypeCode(), Constants.BUDGET_CATEGORY_PERSONNEL)) { 
                    if(!StringUtils.equalsIgnoreCase(budgetLineItem.getCostElement(), budgetLineItem.getCostElementBO().getCostElement())){
                        budgetLineItem.refreshReferenceObject("costElementBO");
                        budgetLineItem.setBudgetCategoryCode(budgetLineItem.getCostElementBO().getBudgetCategoryCode());
                    }
                    updatePersonnelBudgetRate(budgetLineItem);
                    if(checkToRetainBudgetLineItem(budgetLineItem, budget)) {
                        toBeDeletedLineItems.add(i);
                    }
                }
                i++; 
            }
            
            for(Integer lineItemIndex: toBeDeletedLineItems) {
                budgetPeriod.getBudgetLineItems().remove(lineItemIndex.intValue());
            }
        }

        getBudgetPersonService().populateBudgetPersonDefaultDataIfEmpty(budget);

        if (getKcBusinessRulesEngine().applyRules(new BudgetSavePersonnelEvent(budget, budget.getBudgetPeriod(budgetForm.getViewBudgetPeriod()-1)))) {
            if (budgetForm.isAuditActivated()) {
                forward = super.save(mapping, form, request, response);
            } else {
                super.save(mapping, form, request, response);
            }        	
        }
        
        return forward;
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
        AwardBudgetDocument awardBudgetDocument = ((BudgetForm) form).getBudgetDocument();
        Budget budget = awardBudgetDocument.getBudget();
        String errorPath = BUDGET_PERSONS_FIELD_NAME_START + "0" + BUDGET_PERSONS_FIELD_NAME_PERSON_NUMBER;
        if (!getKcBusinessRulesEngine().applyRules(new DeleteBudgetPersonEvent(budget, budget.getBudgetPerson(getLineToDelete(request)), errorPath))) {
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
            AwardBudgetDocument awardBudgetDocument = ((BudgetForm) form).getBudgetDocument();
            Budget budget = awardBudgetDocument.getBudget();
            getBudgetPersonnelBudgetService()
                .deleteBudgetPersonnelDetailsForPerson(budget,             
                                                       budget.getBudgetPerson(getLineToDelete(request)));            

         
            budget.getBudgetPersons().remove(getLineToDelete(request));            
        }
        
        return mapping.findForward(MAPPING_BASIC);
    }
    
    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudget();
        Document document = budgetForm.getDocument();

        // prepare for the reload action - set doc id and command
        budgetForm.setDocId(document.getDocumentNumber());
        budgetForm.setCommand(KewApiConstants.DOCSEARCH_COMMAND);
        // forward off to the doc handler
        ActionForward actionForward = docHandler(mapping, form, request, response);
        KNSGlobalVariables.getMessageList().add(RiceKeyConstants.MESSAGE_RELOADED);
        
        reconcilePersonnelRoles(budget);
        populatePersonnelCategoryTypeCodes(budgetForm);  

        for(BudgetPeriod budgetPeriod:budget.getBudgetPeriods()){
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
        Budget budget = budgetForm.getBudget();
        getBudgetPersonService().synchBudgetPersonsToProposal(budget);
        reconcilePersonnelRoles(budget);
        return mapping.findForward(MAPPING_BASIC);
    }
    
    /**
     * Convenience method for adding populating a new budget person and adding to budget document
     * 
     * @param budgetPerson
     * @param budget
     */
    private void populateAndAddBudgetPerson(BudgetPerson budgetPerson, Budget budget) {
        getBudgetPersonService().addBudgetPerson(budget, budgetPerson);
    }
    
    /**
     * Builds the Delete Abstract Confirmation Question as a <code>{@link StrutsConfirmation}</code> instance.
     *
     * The confirmation question is extracted from the resource bundle
     * and the parameter {0} is replaced with the name of the abstract type
     * that will be deleted.
     * 
     * @param mapping The mapping associated with this action.
     * @param form The Proposal Development form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the confirmation question
     * @throws Exception
     */
    private StrutsConfirmation buildDeleteBudgetPersonConfirmationQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardBudgetDocument awardBudgetDocument = ((BudgetForm) form).getBudgetDocument();
        Budget budget = awardBudgetDocument.getBudget();
        String personName = budget.getBudgetPerson(getLineToDelete(request)).getPersonName();
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_DELETE_BUDGET_PERSON, KeyConstants.QUESTION_DELETE_PERSON, personName);
    }

    protected BudgetPersonnelBudgetService getBudgetPersonnelBudgetService() {
    	if (budgetPersonnelBudgetService == null) {
    		budgetPersonnelBudgetService = KcServiceLocator.getService(BudgetPersonnelBudgetService.class);
    	}
    	return budgetPersonnelBudgetService;
    }
    
        public ActionForward personnelRates(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudget();
        int budgetPeriodNumber = Integer.parseInt(request.getParameter("budgetPeriod"));
        int budgetLineItemNumber = Integer.parseInt(request.getParameter("line"));
        String rateClassCode = request.getParameter("rateClassCode");
        String rateTypeCode = request.getParameter("rateTypeCode");
        
        String fieldName = request.getParameter("fieldName");
        
        BudgetPeriod selectedBudgetPeriod = budget.getBudgetPeriod(budgetPeriodNumber-1);
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
    
    private boolean checkForDeletedPerson(Budget budget, BudgetPersonnelDetails budgetPersonnelDetails) {
        for(BudgetPerson person : budget.getBudgetPersons()) {
            if(person.getPersonSequenceNumber().intValue() == budgetPersonnelDetails.getPersonSequenceNumber().intValue()) {
               return false;
            }
        }
        return true; 
    }
    
    private boolean checkToRetainBudgetLineItem(BudgetLineItem budgetLineItem, Budget budget) {
        List<Integer> deletedPersons = new ArrayList<Integer>();
        int originalPersonListSize = budgetLineItem.getBudgetPersonnelDetailsList().size();
        
        int i = 0;
        for(BudgetPersonnelDetails budgetPersonnelDetails: budgetLineItem.getBudgetPersonnelDetailsList()){
            if(budgetPersonnelDetails.getBudgetPerson() == null) {
                budgetPersonnelDetails.refreshReferenceObject("budgetPerson");
            }
            
            if(budgetPersonnelDetails.getBudgetPerson() != null && checkForDeletedPerson(budget, budgetPersonnelDetails)) {
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
        Budget budget = budgetForm.getBudget();
        int budgetPeriodNumber = Integer.parseInt(request.getParameter("budgetPeriod"));
        int budgetLineItemNumber = Integer.parseInt(request.getParameter("line"));
        int personNumber = Integer.parseInt(request.getParameter("personNumber"));
        
        BudgetPeriod selectedBudgetPeriod = budget.getBudgetPeriod(budgetPeriodNumber-1);
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
        Budget budget = budgetForm.getBudget();
        int budgetPeriodNumber = Integer.parseInt(request.getParameter("budgetPeriod"));
        int budgetLineItemNumber = Integer.parseInt(request.getParameter("line"));
        int personNumber = Integer.parseInt(request.getParameter("personnelIndex"));

        BudgetPeriod selectedBudgetPeriod = budget.getBudgetPeriod(budgetPeriodNumber-1);
        BudgetLineItem selectedLineItem = selectedBudgetPeriod.getBudgetLineItem(budgetLineItemNumber);

        return mapping.findForward(MAPPING_CLOSE_PAGE);
    }
    
    //For the Summary View - Calculations
    public ActionForward calculateLineItem(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudget();
        int selectedBudgetPeriodIndex = budgetForm.getViewBudgetPeriod()-1;
        int selectedBudgetLineItemIndex = getSelectedLine(request);   
        BudgetLineItem selectedBudgetLineItem = budget.getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(selectedBudgetLineItemIndex);
        
        if (getKcBusinessRulesEngine().applyRules(new SaveBudgetEvent(budget))) {
            updatePersonnelBudgetRate(selectedBudgetLineItem);
            getCalculationService().calculateBudgetLineItem(budget, selectedBudgetLineItem); 
            recalculateBudgetPeriod(budgetForm, budget, budget.getBudgetPeriod(selectedBudgetPeriodIndex));
            getCalculationService().populateCalculatedAmount(budget, selectedBudgetLineItem);
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
        Budget budget = budgetForm.getBudget();
        int sltdLineItem = getSelectedLine(request);
        int sltdBudgetPeriod = budgetForm.getViewBudgetPeriod()-1;
        if (getKcBusinessRulesEngine().applyRules(new PersonnelApplyToPeriodsBudgetEvent(budget,
        		"document.budget.budgetPeriod[" + sltdBudgetPeriod + "].budgetLineItem[" + sltdLineItem + "]", budget.getBudgetPeriod(sltdBudgetPeriod).getBudgetLineItem(sltdLineItem), 
        		budget.getBudgetPeriod(sltdBudgetPeriod)))) {
            getCalculationService().applyToLaterPeriods(budget, budget.getBudgetPeriod(sltdBudgetPeriod), budget.getBudgetPeriod(sltdBudgetPeriod).getBudgetLineItem(sltdLineItem));
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward calculatePersonSalaryDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm)form;       
        Budget budget = budgetForm.getBudgetDocument().getBudget();
        List<BudgetPersonSalaryDetails> budgetPersonSalaryDetails = new ArrayList<BudgetPersonSalaryDetails>();
        budgetPersonSalaryDetails =  getBudgetPersonnelBudgetService().calculatePersonSalary(budget, getSelectedLine(request));        
        budgetForm.getBudgetDocument().getBudget().getBudgetPerson(getSelectedLine(request)).setBudgetPersonSalaryDetails(budgetPersonSalaryDetails);
        budgetForm.setViewDivFlag(true);
        budgetForm.setPersonIndex(getSelectedLine(request));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward saveBudgetPersonSalaryInDiffPeriods(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        final int  zero = 0;
        int listIndex = zero;
        BudgetForm budgetForm = (BudgetForm)form;                  
        Budget budget = budgetForm.getBudgetDocument().getBudget();
        List<BudgetPersonSalaryDetails> budgetPersonSalaryDetails = budget.getBudgetPerson(getSelectedLine(request)).getBudgetPersonSalaryDetails();
        
        HashMap budgetPersonInPeriodsSalaryMap = new HashMap();
        budgetPersonInPeriodsSalaryMap.put("personSequenceNumber", budget.getBudgetPerson(getSelectedLine(request)).getPersonSequenceNumber());
        budgetPersonInPeriodsSalaryMap.put("budgetId", budget.getBudgetId());
        if (budget.getBudgetPerson(getSelectedLine(request)).getPersonId() != null) {
            budgetPersonInPeriodsSalaryMap.put("personId", budget.getBudgetPerson(getSelectedLine(request)).getPersonId());
        } else if (budget.getBudgetPerson(getSelectedLine(request)).getRolodexId() != null) {
            budgetPersonInPeriodsSalaryMap.put("personId", budget.getBudgetPerson(getSelectedLine(request)).getRolodexId());
        } else {
            budgetPersonInPeriodsSalaryMap.put("personId", budget.getBudgetPerson(getSelectedLine(request)).getTbnId());
        }
        
        
        Collection<BudgetPersonSalaryDetails> salaryDetails = getBusinessObjectService().findMatching(BudgetPersonSalaryDetails.class, budgetPersonInPeriodsSalaryMap);
        List<BudgetPersonSalaryDetails> personSalaryDetails = (List<BudgetPersonSalaryDetails>) salaryDetails;             
        
        for (BudgetPersonSalaryDetails budgetPerSalaryDetails : budgetPersonSalaryDetails) {
            budgetPerSalaryDetails.setBudgetId(budget.getBudgetId());
            budgetPerSalaryDetails.setPersonSequenceNumber(budget.getBudgetPerson(getSelectedLine(request))
                    .getPersonSequenceNumber());
            budgetPerSalaryDetails.setBudgetPerson(budget.getBudgetPerson(getSelectedLine(request)));
            if (budget.getBudgetPerson(getSelectedLine(request)).getPersonId() != null) {
                budgetPerSalaryDetails.setPersonId(budget.getBudgetPerson(getSelectedLine(request)).getPersonId());
            } else if (budget.getBudgetPerson(getSelectedLine(request)).getRolodexId() != null) {
                budgetPerSalaryDetails.setPersonId(budget.getBudgetPerson(getSelectedLine(request)).getRolodexId().toString());
            } else {
                budgetPerSalaryDetails.setPersonId(budget.getBudgetPerson(getSelectedLine(request)).getTbnId());
            }
            if (personSalaryDetails != null && personSalaryDetails.size() > 0) {
                budgetPerSalaryDetails.setBudgetPeriod(personSalaryDetails.get(listIndex).getBudgetPeriod());
                budgetPerSalaryDetails.setBudgetPersonSalaryDetailId(personSalaryDetails.get(listIndex)
                        .getBudgetPersonSalaryDetailId());

            } else {
                budgetPerSalaryDetails.setBudgetPeriod(listIndex + 1);
            }

            listIndex = listIndex + 1;
        }
       
        
        
       
       
        if(budgetPersonSalaryDetails !=null && budgetPersonSalaryDetails.size() > 0){
            getBusinessObjectService().save(budgetPersonSalaryDetails);
        }
        
        budgetForm.setViewDivFlag(true);
        budgetForm.setPersonIndex(getSelectedLine(request));
        return mapping.findForward(Constants.MAPPING_BASIC);
        
        
    }

	public void setBudgetPersonnelBudgetService(
			BudgetPersonnelBudgetService budgetPersonnelBudgetService) {
		this.budgetPersonnelBudgetService = budgetPersonnelBudgetService;
	}

	public BudgetPersonService getBudgetPersonService() {
		if (budgetPersonService == null) {
			budgetPersonService = KcServiceLocator.getService(BudgetPersonService.class);
		}
		return budgetPersonService;
	}

	public void setBudgetPersonService(BudgetPersonService budgetPersonService) {
		this.budgetPersonService = budgetPersonService;
	}
   
   
}
