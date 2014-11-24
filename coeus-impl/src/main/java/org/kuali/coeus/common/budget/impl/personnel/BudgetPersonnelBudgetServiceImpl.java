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
package org.kuali.coeus.common.budget.impl.personnel;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.budget.framework.personnel.*;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.budget.framework.calculator.BudgetCalculationService;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetConstants;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetFormulatedCostDetail;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItemBase;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.kra.award.budget.service.AwardBudgetPersonService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.util.MessageMap;
import org.kuali.rice.krad.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;


@Component("budgetPersonnelBudgetService")
public class BudgetPersonnelBudgetServiceImpl implements BudgetPersonnelBudgetService {

    private static final Log LOG = LogFactory.getLog(BudgetPersonnelBudgetServiceImpl.class);
    public static final int BUDGET_PERIOD_1 = 1;

    @Autowired
    @Qualifier("budgetPersonService")
    private BudgetPersonService budgetPersonService;
    @Autowired
    @Qualifier("budgetCalculationService")
    private BudgetCalculationService budgetCalculationService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;

    @Autowired
	@Qualifier("dataObjectService")
    private DataObjectService dataObjectService;
    
    private AwardBudgetPersonService awardBudgetPersonService;

    @Override
    public void addBudgetPersonnelDetails(Budget budget, BudgetPeriod budgetPeriod, BudgetLineItem budgetLineItem, BudgetPersonnelDetails newBudgetPersonnelDetails) {
    	try {
            ConvertUtils.register(new SqlDateConverter(null), java.sql.Date.class);
            ConvertUtils.register(new SqlTimestampConverter(null), java.sql.Timestamp.class);
            BeanUtils.copyProperties(newBudgetPersonnelDetails,(BudgetLineItemBase)budgetLineItem);
            //budget justification should never end up on the personnel details
            newBudgetPersonnelDetails.setBudgetJustification(null);
        }catch (Exception e) {
            LOG.error(e.getMessage(), e);
            copyLineItemToPersonnelDetails(budgetLineItem, newBudgetPersonnelDetails);
        }
        /*
         * Need to solve the document next value refresh issue
         */
        newBudgetPersonnelDetails.setPersonNumber(budget.getNextValue(Constants.BUDGET_PERSON_LINE_NUMBER));
        newBudgetPersonnelDetails.setPersonSequenceNumber(newBudgetPersonnelDetails.getPersonSequenceNumber());
        BudgetPerson budgetPerson;
        if(budget instanceof org.kuali.kra.award.budget.AwardBudgetExt) {
        	budgetPerson = getAwardBudgetPersonService().findBudgetPerson(newBudgetPersonnelDetails);
        } else {
        	budgetPerson = getBudgetPersonService().findBudgetPerson(newBudgetPersonnelDetails);
        }
        if(budgetPerson != null) {
            newBudgetPersonnelDetails.setPersonId(budgetPerson.getPersonRolodexTbnId());
            newBudgetPersonnelDetails.setJobCode(budgetPerson.getJobCode());
            newBudgetPersonnelDetails.setBudgetPerson(budgetPerson);
        }
        newBudgetPersonnelDetails.setSequenceNumber(budget.getNextValue(Constants.BUDGET_PERSON_LINE_SEQUENCE_NUMBER));
        newBudgetPersonnelDetails.refreshNonUpdateableReferences();
        budgetLineItem.getBudgetPersonnelDetailsList().add(newBudgetPersonnelDetails);
    }



    public List<BudgetPersonSalaryDetails> calculatePersonSalary(Budget budget, int personIndex){
       
        List<BudgetPersonSalaryDetails> budgetPersonSalaryDetails = new ArrayList<BudgetPersonSalaryDetails>(); 
        String rate = getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class,
                Constants.DEFAULT_INFLATION_RATE_FOR_SALARY);
        List<BudgetPeriod> budgetPeriodList = null;
        BigDecimal actualPersonSalary = ScaleTwoDecimal.ZERO.bigDecimalValue();
        BigDecimal personSalary = ScaleTwoDecimal.ZERO.bigDecimalValue();
        BigDecimal newRate = new ScaleTwoDecimal(rate).bigDecimalValue();
        budgetPeriodList = budget.getBudgetPeriods();
        
        BudgetPerson budgetPerson = budget.getBudgetPerson(personIndex);
            for (BudgetPeriod budgetPeriodData : budgetPeriodList) {
                BudgetPersonSalaryDetails personSalaryDetails = new BudgetPersonSalaryDetails();
              
                personSalaryDetails.setBudgetId(budget.getBudgetId());
                personSalaryDetails.setPersonSequenceNumber(budgetPerson.getPersonSequenceNumber());
                personSalaryDetails.setBudgetPeriod(budgetPeriodData.getBudgetPeriod());
                personSalaryDetails.setPersonId(budgetPerson.getPersonId());
                if (budgetPeriodData.getBudgetPeriod() == BUDGET_PERIOD_1) {
                    if (budgetPerson.getEffectiveDate().equals(budgetPerson.getStartDate())) {

                        personSalaryDetails.setBaseSalary(budgetPerson.getCalculationBase());
                        actualPersonSalary = budgetPerson.getCalculationBase().bigDecimalValue();

                    } else {

                        actualPersonSalary = budgetPerson.getCalculationBase().add(
                                new ScaleTwoDecimal(budgetPerson.getCalculationBase().bigDecimalValue().multiply(newRate.divide(new ScaleTwoDecimal(100).bigDecimalValue(), RoundingMode.HALF_UP)))).bigDecimalValue();
                        
                      
                    }


                } else {

                    personSalary = actualPersonSalary.add(actualPersonSalary.multiply(newRate.divide(new ScaleTwoDecimal(100).bigDecimalValue(), RoundingMode.HALF_UP)));
                    personSalaryDetails.setBaseSalary(new ScaleTwoDecimal(personSalary));
                    actualPersonSalary = personSalary;
                }
                budgetPersonSalaryDetails.add(personSalaryDetails);
            }  
            return budgetPersonSalaryDetails;
    }
    
    
    public void calculateBudgetPersonnelBudget(Budget budget, BudgetLineItem selectedBudgetLineItem,
            BudgetPersonnelDetails budgetPersonnelDetails, int lineNumber) {
        copyLineItemToPersonnelDetails(selectedBudgetLineItem,budgetPersonnelDetails);
        budgetCalculationService.calculateBudgetLineItem(budget, budgetPersonnelDetails);
        // error message if effective data is out of range
        if (budgetPersonnelDetails.getSalaryRequested().equals(ScaleTwoDecimal.ZERO)) {
            int budgetPeriodNumber = budgetPersonnelDetails.getBudgetPeriod() - 1;
            BudgetPeriod budgetPeriod = budget.getBudgetPeriod(budgetPeriodNumber);
            Date personEffectiveDate =  budgetPersonnelDetails.getBudgetPerson().getEffectiveDate();
            if (personEffectiveDate.after(budgetPeriod.getEndDate())) {
                MessageMap errorMap = globalVariableService.getMessageMap();
                // salaryrequested is hidden field, so use person
                errorMap.putError("document.budgetPeriod["+budgetPeriodNumber+"].budgetLineItems["+budgetPeriodNumber+"].budgetPersonnelDetailsList["+lineNumber+"].personSequenceNumber", KeyConstants.ERROR_EFFECTIVE_DATE_OUT_OF_RANGE, new String []{budgetPersonnelDetails.getBudgetPerson().getPersonName() });

            }
        }
    }

    protected void copyLineItemToPersonnelDetails(BudgetLineItem budgetLineItem, BudgetPersonnelDetails budgetPersonnelDetails) {
    	budgetPersonnelDetails.setBudget(budgetLineItem.getBudget());
        budgetPersonnelDetails.setBudgetId(budgetLineItem.getBudgetId());
        budgetPersonnelDetails.setBudgetPeriod(budgetLineItem.getBudgetPeriod());
        budgetPersonnelDetails.setBudgetPeriodId(budgetLineItem.getBudgetPeriodId());
        budgetPersonnelDetails.setLineItemNumber(budgetLineItem.getLineItemNumber());
        budgetPersonnelDetails.setCostElement(budgetLineItem.getCostElement());
        budgetPersonnelDetails.setCostElementBO(budgetLineItem.getCostElementBO());
        budgetPersonnelDetails.setApplyInRateFlag(budgetLineItem.getApplyInRateFlag());
        budgetPersonnelDetails.setOnOffCampusFlag(budgetLineItem.getOnOffCampusFlag());
        budgetPersonnelDetails.setStartDate(budgetLineItem.getStartDate());
        budgetPersonnelDetails.setEndDate(budgetLineItem.getEndDate());
        budgetPersonnelDetails.setBudgetLineItem(budgetLineItem);
    }

    public void deleteBudgetPersonnelDetails(Budget budget, int selectedBudgetPeriodIndex,
            int selectedBudgetLineItemIndex, int lineToDelete) {
        BudgetLineItem selectedBudgetLineItem = budget.getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(selectedBudgetLineItemIndex);
        selectedBudgetLineItem.getBudgetPersonnelDetailsList().remove(lineToDelete);
        selectedBudgetLineItem.setBudgetPersonnelLineItemDeleted(true);
    }

    /**
     * Removes all {@link BudgetPersonnelDetails} instances for a given {@link BudgetPerson}. Has to iterate through {@link BudgetPeriod} instances,
     * {@link BudgetLineItem} instances, and finally {@link BudgetPersonnelDetails} instances. Then the {@link BudgetPerson} instances are compared.
     *
     * @param document Budget to remove {@link BudgetPersonnelDetails} from
     * @param person {@link BudgetPerson} we're looking for
     */
    public void deleteBudgetPersonnelDetailsForPerson(Budget document, BudgetPerson person) {
        boolean personFound = false;
        BudgetPersonnelDetails toRemove = null;
        BudgetLineItem lineItem = null;
        
        for (Iterator<BudgetPeriod> period_it = document.getBudgetPeriods().iterator(); period_it.hasNext() && !personFound;) {
            BudgetPeriod period = period_it.next();

            for (Iterator<BudgetLineItem> lineItem_it = period.getBudgetLineItems().iterator(); lineItem_it.hasNext() && !personFound;) {
                lineItem = lineItem_it.next();

                for (Iterator<BudgetPersonnelDetails> personnelDetails_it = lineItem.getBudgetPersonnelDetailsList().iterator(); personnelDetails_it.hasNext() && !personFound;) {
                    BudgetPersonnelDetails personnelDetails = personnelDetails_it.next();
                    
                    if (personnelDetails.getBudgetPerson() == null) {
                        personnelDetails.refreshReferenceObject("budgetPerson");
                    }

                    if (ObjectUtils.equalByKeys(personnelDetails.getBudgetPerson(), person)) {
                        LOG.debug("Comparing " + personnelDetails.getBudgetPerson().getPersonId() + " and " + person.getPersonId());

                        lineItem.setBudgetPersonnelLineItemDeleted(true);
                        personFound = true;
                        toRemove = personnelDetails;
                    }
                }
            }
        }
        
        if (personFound && toRemove != null && lineItem != null) {
            LOG.debug("Removing " + toRemove);
            lineItem.getBudgetPersonnelDetailsList().remove(toRemove);
        }
    }

    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }

    public BudgetPersonService getBudgetPersonService() {
        return budgetPersonService;
    }

    public void setBudgetPersonService(BudgetPersonService budgetPersonService) {
        this.budgetPersonService = budgetPersonService;
    }

    public BudgetCalculationService getBudgetCalculationService() {
        return budgetCalculationService;
    }

    public void setBudgetCalculationService(BudgetCalculationService budgetCalculationService) {
        this.budgetCalculationService = budgetCalculationService;
    }

    protected ParameterService getParameterService() {
        return this.parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
    
    public AwardBudgetPersonService getAwardBudgetPersonService() {
    	if (awardBudgetPersonService == null) {
    		awardBudgetPersonService = KcServiceLocator.getService(AwardBudgetPersonService.class);
    	}
		return awardBudgetPersonService;
	}

	public void setAwardBudgetPersonService(AwardBudgetPersonService awardBudgetPersonService) {
		this.awardBudgetPersonService = awardBudgetPersonService;
	}



	public void calculateBudgetPersonnelLineItem(Budget budget, BudgetLineItem budgetLineItem, BudgetPersonnelDetails budgetPersonnelDetails, int lineItemNumber) {
    	getBudgetCalculationService().updatePersonnelBudgetRate(budgetLineItem);
    	if(budgetPersonnelDetails.getPersonSequenceNumber() == BudgetConstants.BudgetPerson.SUMMARYPERSON.getPersonSequenceNumber()) {
    		getBudgetCalculationService().calculateBudgetLineItem(budget, budgetLineItem); 
        	getBudgetCalculationService().populateCalculatedAmount(budget, budgetLineItem);
    	}else {
        	calculateBudgetPersonnelBudget(budget, budgetLineItem, budgetPersonnelDetails, lineItemNumber);
        	getBudgetCalculationService().populateCalculatedAmount(budget, budgetLineItem);
        	getBudgetCalculationService().populateCalculatedAmount(budget, budgetPersonnelDetails);
    	}
    }
    
    public void addBudgetPersonnelToPeriod(BudgetPeriod budgetPeriod, BudgetLineItem newBudgetLineItem, BudgetPersonnelDetails newBudgetPersonnelDetail) {
    	Budget budget = budgetPeriod.getBudget();
    	BudgetLineItem groupedBudgetLineItem = getExistingBudgetLineItemBasedOnCostElementAndGroup(budgetPeriod, newBudgetLineItem);
    	if(groupedBudgetLineItem == null) {
    		groupedBudgetLineItem = newBudgetLineItem;
    		setNewBudgetLineItemAttributes(budgetPeriod, groupedBudgetLineItem);
            budgetPeriod.getBudgetLineItems().add(groupedBudgetLineItem);
    	}
    	groupedBudgetLineItem.setQuantity(getLineItemQuantity(newBudgetLineItem));
    	int newLineNumber = groupedBudgetLineItem.getBudgetPersonnelDetailsList().size() + 1;
    	if(newBudgetPersonnelDetail.getPersonSequenceNumber() != BudgetConstants.BudgetPerson.SUMMARYPERSON.getPersonSequenceNumber()) {
        	addPersonnelToPeriod(budgetPeriod, groupedBudgetLineItem, newBudgetPersonnelDetail);
        	calculateBudgetPersonnelLineItem(budget, groupedBudgetLineItem, newBudgetPersonnelDetail, newLineNumber);
    	}
    }
    
    /**
     * This method is to find existing line item that is grouped by cost element and group name
     * @param budgetPeriod
     * @param newBudgetLineItem
     * @return
     */
    protected BudgetLineItem getExistingBudgetLineItemBasedOnCostElementAndGroup(BudgetPeriod budgetPeriod, BudgetLineItem newBudgetLineItem){
    	String newLineItemKey = getLineItemGroupKey(newBudgetLineItem);
    	for(BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
        	String existingLineItemKey = getLineItemGroupKey(budgetLineItem);
        	if(newLineItemKey.equalsIgnoreCase(existingLineItemKey)) {
        		return budgetLineItem;
        	}
    	}
    	return null;
    }
    
    protected String getLineItemGroupKey(BudgetLineItem budgetLineItem) {
    	if(StringUtils.isEmpty(budgetLineItem.getGroupName())) {
        	return budgetLineItem.getCostElement();
    	}else {
        	return budgetLineItem.getCostElement().concat(budgetLineItem.getGroupName());
    	}
    }
    
    protected void setNewBudgetLineItemAttributes(BudgetPeriod budgetPeriod, BudgetLineItem newBudgetLineItem) {
    	Budget budget = budgetPeriod.getBudget();
    	Integer lineItemNumber = budget.getNextValue(Constants.BUDGET_LINEITEM_NUMBER);
    	newBudgetLineItem.setBudgetId(budget.getBudgetId());
        newBudgetLineItem.setBudgetPeriod(budgetPeriod.getBudgetPeriod());
        newBudgetLineItem.setBudgetPeriodId(budgetPeriod.getBudgetPeriodId());
        newBudgetLineItem.setBudgetPeriodBO(budgetPeriod);
        newBudgetLineItem.setLineItemNumber(lineItemNumber);
        newBudgetLineItem.setLineItemSequence(lineItemNumber);
        newBudgetLineItem.setApplyInRateFlag(true);
    	
        String onOffCampusFlag = budget.getOnOffCampusFlag();
        if (onOffCampusFlag.equalsIgnoreCase(BudgetConstants.DEFAULT_CAMPUS_FLAG)) {
            newBudgetLineItem.setOnOffCampusFlag(newBudgetLineItem.getCostElementBO().getOnOffCampusFlag()); 
        } else {
            newBudgetLineItem.setOnOffCampusFlag(onOffCampusFlag.equalsIgnoreCase(Constants.ON_CAMUS_FLAG));                 
        }
    }
    
    private Integer getLineItemQuantity(BudgetLineItem personnelLineItem) {
        HashSet<String> uniqueBudgetPersonnelCount = new HashSet<String>();
        Integer quantity = 0;
        for (BudgetPersonnelDetails budgetPersonnelDetails : personnelLineItem.getBudgetPersonnelDetailsList()) {
            if(!uniqueBudgetPersonnelCount.contains(budgetPersonnelDetails.getPersonId())){
                uniqueBudgetPersonnelCount.add(budgetPersonnelDetails.getPersonId());
                quantity++;
            }
        }    
        return quantity;
    }
    
    protected void addPersonnelToPeriod(BudgetPeriod budgetPeriod, BudgetLineItem budgetLineItem, BudgetPersonnelDetails newBudgetPersonnelDetail) {
    	Budget budget = budgetPeriod.getBudget();
        copyLineItemToPersonnelDetails(budgetLineItem, newBudgetPersonnelDetail);
        newBudgetPersonnelDetail.setPersonNumber(budget.getNextValue(Constants.BUDGET_PERSON_LINE_NUMBER));
        refreshBudgetPersonnelLineItemReferences(newBudgetPersonnelDetail);
        BudgetPerson budgetPerson = newBudgetPersonnelDetail.getBudgetPerson();
        if(budgetPerson != null) {
        	newBudgetPersonnelDetail.setPersonId(budgetPerson.getPersonRolodexTbnId());
        	newBudgetPersonnelDetail.setJobCode(budgetPerson.getJobCode());
        }
        newBudgetPersonnelDetail.setSequenceNumber(budget.getNextValue(Constants.BUDGET_PERSON_LINE_SEQUENCE_NUMBER));
        budgetLineItem.getBudgetPersonnelDetailsList().add(newBudgetPersonnelDetail);
    }

    private void refreshBudgetPersonnelLineItemReferences(BudgetPersonnelDetails newBudgetPersonnelDetail) {
		if(newBudgetPersonnelDetail.getBudgetId() != null && newBudgetPersonnelDetail.getPersonSequenceNumber() != null) {
			getDataObjectService().wrap(newBudgetPersonnelDetail).fetchRelationship("budgetPerson");
		}
		if(newBudgetPersonnelDetail.getPeriodTypeCode() != null) {
			getDataObjectService().wrap(newBudgetPersonnelDetail).fetchRelationship("budgetPeriodType");
		}
    }
    
    public void calculateCurrentBudgetPeriod(BudgetPeriod budgetPeriod) {
        for(BudgetLineItem budgetLineItem:budgetPeriod.getBudgetLineItems()){
            getBudgetCalculationService().updatePersonnelBudgetRate(budgetLineItem);
            if(budgetLineItem.getFormulatedCostElementFlag()){
                calculateAndUpdateFormulatedCost(budgetLineItem);
            }
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

    private ScaleTwoDecimal getFormulatedCostsTotal(BudgetLineItem budgetLineItem) {
        List<BudgetFormulatedCostDetail> budgetFormulatedCosts = budgetLineItem.getBudgetFormulatedCosts();
        ScaleTwoDecimal formulatedExpenses = ScaleTwoDecimal.ZERO;
        for (BudgetFormulatedCostDetail budgetFormulatedCostDetail : budgetFormulatedCosts) {
            calculateBudgetFormulatedCost(budgetFormulatedCostDetail);
            formulatedExpenses = formulatedExpenses.add(budgetFormulatedCostDetail.getCalculatedExpenses());
        }
        return formulatedExpenses;
    }
    
    private void calculateBudgetFormulatedCost( BudgetFormulatedCostDetail budgetFormulatedCost) {
    	ScaleTwoDecimal unitCost = budgetFormulatedCost.getUnitCost();
    	ScaleTwoDecimal count = new ScaleTwoDecimal(budgetFormulatedCost.getCount());
    	ScaleTwoDecimal frequency = new ScaleTwoDecimal(budgetFormulatedCost.getFrequency());
    	ScaleTwoDecimal calculatedExpense = unitCost.multiply(count).multiply(frequency);
        budgetFormulatedCost.setCalculatedExpenses(calculatedExpense);
    }
    
	public DataObjectService getDataObjectService() {
		return dataObjectService;
	}

	public void setDataObjectService(DataObjectService dataObjectService) {
		this.dataObjectService = dataObjectService;
	}
}
