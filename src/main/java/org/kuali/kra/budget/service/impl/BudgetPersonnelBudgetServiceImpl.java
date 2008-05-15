/*
 * Copyright 2008 The Kuali Foundation.
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
package org.kuali.kra.budget.service.impl;

import java.sql.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetLineItemBase;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetPerson;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.service.BudgetCalculationService;
import org.kuali.kra.budget.service.BudgetPersonService;
import org.kuali.kra.budget.service.BudgetPersonnelBudgetService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

/**
 * This class...
 */
public class BudgetPersonnelBudgetServiceImpl implements BudgetPersonnelBudgetService {

    private BudgetPersonService budgetPersonService;
    private BudgetCalculationService budgetCalculationService;
    /**
     * @see org.kuali.kra.budget.service.BudgetPersonnelBudgetService#addBudgetPersonnelDetails(org.kuali.kra.budget.bo.BudgetLineItem, org.kuali.kra.budget.bo.BudgetPersonnelDetails)
     */
    public void addBudgetPersonnelDetails(BudgetDocument budgetDocument,int budgetPeriodIndex,int budgetLineItemIndex, BudgetPersonnelDetails newBudgetPersonnelDetails) {
        BudgetLineItem budgetLineItem = budgetDocument.getBudgetPeriod(budgetPeriodIndex).getBudgetLineItems().get(budgetLineItemIndex);
        try {
            ConvertUtils.register(new SqlDateConverter(null), java.sql.Date.class);
            ConvertUtils.register(new SqlTimestampConverter(null), java.sql.Timestamp.class);
            BeanUtils.copyProperties(newBudgetPersonnelDetails,(BudgetLineItemBase)budgetLineItem);
        }catch (Exception e) {
            copyLineItemToPersonnelDetails(budgetLineItem, newBudgetPersonnelDetails);
        }
        /*
         * Need to solve the documentnext value refresh issue
         */
        
        newBudgetPersonnelDetails.setPersonNumber(budgetDocument.getHackedDocumentNextValue(Constants.BUDGET_PERSON_LINE_NUMBER));
        newBudgetPersonnelDetails.setPersonSequenceNumber(newBudgetPersonnelDetails.getPersonSequenceNumber());
        BudgetPerson budgetPerson = budgetPersonService.findBudgetPerson(newBudgetPersonnelDetails);
        newBudgetPersonnelDetails.setPersonId(budgetPerson.getPersonRolodexTbnId());
        newBudgetPersonnelDetails.setJobCode(budgetPerson.getJobCode());
        newBudgetPersonnelDetails.setSequenceNumber(budgetDocument.getHackedDocumentNextValue(Constants.BUDGET_PERSON_LINE_SEQUENCE_NUMBER));
        budgetCalculationService.populateCalculatedAmount(budgetDocument, newBudgetPersonnelDetails);
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

    public void calculateBudgetPersonnelBudget(BudgetDocument budgetDocument, BudgetLineItem selectedBudgetLineItem,
            BudgetPersonnelDetails budgetPersonnelDetails, int lineNumber) {
        copyLineItemToPersonnelDetails(selectedBudgetLineItem,budgetPersonnelDetails);
        budgetCalculationService.calculateBudgetLineItem(budgetDocument, budgetPersonnelDetails);
        // error message if effective data is out of range
        if (budgetPersonnelDetails.getSalaryRequested().equals(BudgetDecimal.ZERO)) {
            int budgetPeriodNumber = budgetPersonnelDetails.getBudgetPeriod() - 1;
            BudgetPeriod budgetPeriod = budgetDocument.getBudgetPeriod(budgetPeriodNumber);
            Date personEffectiveDate =  budgetPersonnelDetails.getBudgetPerson().getEffectiveDate();
            if (personEffectiveDate.after(budgetPeriod.getEndDate())) {
                ErrorMap errorMap = GlobalVariables.getErrorMap();
                // salaryrequested is hidden field, so use person
                errorMap.putError("document.budgetPeriod["+budgetPeriodNumber+"].budgetLineItems["+budgetPeriodNumber+"].budgetPersonnelDetailsList["+lineNumber+"].personSequenceNumber", KeyConstants.ERROR_EFFECTIVE_DATE_OUT_OF_RANGE, new String []{budgetPersonnelDetails.getBudgetPerson().getPersonName() });

            }
        }
    }

    private void copyLineItemToPersonnelDetails(BudgetLineItem budgetLineItem, BudgetPersonnelDetails budgetPersonnelDetails) {
        budgetPersonnelDetails.setProposalNumber(budgetLineItem.getProposalNumber());
        budgetPersonnelDetails.setBudgetVersionNumber(budgetLineItem.getBudgetVersionNumber());
        budgetPersonnelDetails.setBudgetPeriod(budgetLineItem.getBudgetPeriod());
        budgetPersonnelDetails.setLineItemNumber(budgetLineItem.getLineItemNumber());
        budgetPersonnelDetails.setCostElement(budgetLineItem.getCostElement());
        budgetPersonnelDetails.setCostElementBO(budgetLineItem.getCostElementBO());
    }

    public void deleteBudgetPersonnelDetails(BudgetDocument budgetDocument, int selectedBudgetPeriodIndex,
            int selectedBudgetLineItemIndex, int lineToDelete) {
        budgetDocument.getBudgetPeriod(selectedBudgetPeriodIndex).getBudgetLineItem(selectedBudgetLineItemIndex).getBudgetPersonnelDetailsList().remove(lineToDelete);
    }

}
