/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.budget.personnel;

import static org.kuali.kra.logging.BufferedLogger.debug;

import java.sql.Date;
import java.util.Iterator;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.calculator.BudgetCalculationService;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItemBase;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;
import org.kuali.rice.krad.util.ObjectUtils;

/**
 * This class...
 */
public class BudgetPersonnelBudgetServiceImpl implements BudgetPersonnelBudgetService {

    private BudgetPersonService budgetPersonService;
    private BudgetCalculationService budgetCalculationService;
    /**
     * @see org.kuali.kra.budget.personnel.BudgetPersonnelBudgetService#addBudgetPersonnelDetails(org.kuali.kra.budget.nonpersonnel.BudgetLineItem, org.kuali.kra.budget.personnel.BudgetPersonnelDetails)
     */
    public void addBudgetPersonnelDetails(BudgetDocument budgetDocument, BudgetPeriod budgetPeriod, BudgetLineItem budgetLineItem, BudgetPersonnelDetails newBudgetPersonnelDetails) {
        try {
            ConvertUtils.register(new SqlDateConverter(null), java.sql.Date.class);
            ConvertUtils.register(new SqlTimestampConverter(null), java.sql.Timestamp.class);
            BeanUtils.copyProperties(newBudgetPersonnelDetails,(BudgetLineItemBase)budgetLineItem);
            //budget justification should never end up on the personnel details
            newBudgetPersonnelDetails.setBudgetJustification(null);
        }catch (Exception e) {
            copyLineItemToPersonnelDetails(budgetLineItem, newBudgetPersonnelDetails);
        }
        /*
         * Need to solve the documentnext value refresh issue
         */
        
        newBudgetPersonnelDetails.setPersonNumber(budgetDocument.getHackedDocumentNextValue(Constants.BUDGET_PERSON_LINE_NUMBER));
        newBudgetPersonnelDetails.setPersonSequenceNumber(newBudgetPersonnelDetails.getPersonSequenceNumber());
        BudgetPerson budgetPerson = budgetPersonService.findBudgetPerson(newBudgetPersonnelDetails);
        if(budgetPerson != null) {
            newBudgetPersonnelDetails.setPersonId(budgetPerson.getPersonRolodexTbnId());
            newBudgetPersonnelDetails.setJobCode(budgetPerson.getJobCode());
            newBudgetPersonnelDetails.setBudgetPerson(budgetPerson);
        }
        newBudgetPersonnelDetails.setSequenceNumber(budgetDocument.getHackedDocumentNextValue(Constants.BUDGET_PERSON_LINE_SEQUENCE_NUMBER));
        //budgetCalculationService.populateCalculatedAmount(budget, newBudgetPersonnelDetails);
        newBudgetPersonnelDetails.refreshNonUpdateableReferences();
        budgetLineItem.getBudgetPersonnelDetailsList().add(newBudgetPersonnelDetails);
    }

    /**
     * Gets the budgetPersonService attribute. 
     * @return Returns the budgetPersonService.
     */
    public BudgetPersonService getBudgetPersonService() {
        return budgetPersonService;
    }

    /**
     * Sets the budgetPersonService attribute value.
     * @param budgetPersonService The budgetPersonService to set.
     */
    public void setBudgetPersonService(BudgetPersonService budgetPersonService) {
        this.budgetPersonService = budgetPersonService;
    }

    /**
     * Gets the budgetCalculationService attribute. 
     * @return Returns the budgetCalculationService.
     */
    public BudgetCalculationService getBudgetCalculationService() {
        return budgetCalculationService;
    }

    /**
     * Sets the budgetCalculationService attribute value.
     * @param budgetCalculationService The budgetCalculationService to set.
     */
    public void setBudgetCalculationService(BudgetCalculationService budgetCalculationService) {
        this.budgetCalculationService = budgetCalculationService;
    }

    public void calculateBudgetPersonnelBudget(Budget budget, BudgetLineItem selectedBudgetLineItem,
            BudgetPersonnelDetails budgetPersonnelDetails, int lineNumber) {
        copyLineItemToPersonnelDetails(selectedBudgetLineItem,budgetPersonnelDetails);
        budgetCalculationService.calculateBudgetLineItem(budget, budgetPersonnelDetails);
        // error message if effective data is out of range
        if (budgetPersonnelDetails.getSalaryRequested().equals(BudgetDecimal.ZERO)) {
            int budgetPeriodNumber = budgetPersonnelDetails.getBudgetPeriod() - 1;
            BudgetPeriod budgetPeriod = budget.getBudgetPeriod(budgetPeriodNumber);
            Date personEffectiveDate =  budgetPersonnelDetails.getBudgetPerson().getEffectiveDate();
            if (personEffectiveDate.after(budgetPeriod.getEndDate())) {
                MessageMap errorMap = GlobalVariables.getMessageMap();
                // salaryrequested is hidden field, so use person
                errorMap.putError("document.budgetPeriod["+budgetPeriodNumber+"].budgetLineItems["+budgetPeriodNumber+"].budgetPersonnelDetailsList["+lineNumber+"].personSequenceNumber", KeyConstants.ERROR_EFFECTIVE_DATE_OUT_OF_RANGE, new String []{budgetPersonnelDetails.getBudgetPerson().getPersonName() });

            }
        }
    }

    protected void copyLineItemToPersonnelDetails(BudgetLineItem budgetLineItem, BudgetPersonnelDetails budgetPersonnelDetails) {
//        budgetPersonnelDetails.setProposalNumber(budgetLineItem.getProposalNumber());
//        budgetPersonnelDetails.setBudgetVersionNumber(budgetLineItem.getBudgetVersionNumber());
        budgetPersonnelDetails.setBudgetId(budgetLineItem.getBudgetId());
        budgetPersonnelDetails.setBudgetPeriod(budgetLineItem.getBudgetPeriod());
        budgetPersonnelDetails.setLineItemNumber(budgetLineItem.getLineItemNumber());
        budgetPersonnelDetails.setCostElement(budgetLineItem.getCostElement());
        budgetPersonnelDetails.setCostElementBO(budgetLineItem.getCostElementBO());
        budgetPersonnelDetails.setApplyInRateFlag(budgetLineItem.getApplyInRateFlag());
        budgetPersonnelDetails.setOnOffCampusFlag(budgetLineItem.getOnOffCampusFlag());
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
                        debug("Comparing ", personnelDetails.getBudgetPerson().getPersonId(),  " and ",  person.getPersonId());

                        lineItem.setBudgetPersonnelLineItemDeleted(true);
                        personFound = true;
                        toRemove = personnelDetails;
                    }
                }
            }
        }
        
        if (personFound && toRemove != null && lineItem != null) {
            debug("Removing ", toRemove);
            lineItem.getBudgetPersonnelDetailsList().remove(toRemove);
        }
    }
}
