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
package org.kuali.kra.budget.service.impl;

import java.sql.Date;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.budget.BudgetException;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.CostElement;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.service.BudgetJustificationService;
import org.kuali.kra.budget.web.struts.form.BudgetJustificationWrapper;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;

public class BudgetJustificationServiceImpl implements BudgetJustificationService {

    /**
     * 
     * @see org.kuali.kra.budget.service.BudgetJustificationService#consolidateExpenseJustifications(org.kuali.kra.budget.document.BudgetDocument, org.kuali.kra.budget.web.struts.form.BudgetJustificationWrapper)
     */
    public void consolidateExpenseJustifications(BudgetDocument budgetDocument, BudgetJustificationWrapper budgetJustificationWrapper) throws BudgetException {
        if(budgetDocument.areLineItemJustificationsPresent()) {
            addConsolidatedLineItemJustificationText(budgetDocument, budgetJustificationWrapper);
        } else {
            throw new BudgetException();
        }
    }

    /**
     * 
     * @see org.kuali.kra.budget.service.BudgetJustificationService#preSave(org.kuali.kra.budget.document.BudgetDocument, org.kuali.kra.budget.web.struts.form.BudgetJustificationWrapper)
     */
    public void preSave(BudgetDocument budgetDocument, BudgetJustificationWrapper budgetJustificationWrapper) {
        updateJustficationMetaData(budgetJustificationWrapper);
        budgetDocument.setBudgetJustification(budgetJustificationWrapper.toString());
    }
    
    /**
     * This method looks up user id. Protected to allow stubbing
     * @return
     */
    protected String getLoggedInUserNetworkId() {
        return GlobalVariables.getUserSession().getPrincipalName();
    }
    
    /**
     * This method loads Cost Elements, mapping them to their cost element code
     * @return
     */
    @SuppressWarnings("unchecked")
    protected Map<String, CostElement> loadCostElements() {
        Collection<CostElement> costElements = (Collection<CostElement>) KraServiceLocator.getService(BusinessObjectService.class).findAll(CostElement.class);
        Map<String, CostElement> costElementsMappedToCostElementCode = new TreeMap<String, CostElement>(); 
        for(CostElement costElement: costElements) {
            costElementsMappedToCostElementCode.put(costElement.getCostElement(), costElement);
        }
        return costElementsMappedToCostElementCode;
    }

    /**
     * This method adds the required footer between budget period justifications
     * @param sb
     */
    private void addBudgetPeriodFooter(StringBuilder sb) {
        sb.append("\n");
    }

    /**
     * This method adds the required header for each budget period
     * @param sb
     * @param budgetPeriodNumber
     */
    private void addBudgetPeriodHeader(StringBuilder sb, int budgetPeriodNumber) {
        sb.append("Period ");
        sb.append(budgetPeriodNumber);
        sb.append("\n");
    }

    /**
     * This method adds consolidated Line Item Justification text to any existing text
     * @param budgetDocument
     * @param budgetJustificationWrapper
     */
    private void addConsolidatedLineItemJustificationText(BudgetDocument budgetDocument, BudgetJustificationWrapper budgetJustificationWrapper) {
        String existingJustificationText = budgetJustificationWrapper.getJustificationText();
        StringBuilder sb = new StringBuilder();
        
        if(!StringUtils.isEmpty(existingJustificationText)) {
            sb.append(existingJustificationText);
            addLineItemJustificationTextSeparatorText(sb);
        }
        
        for(BudgetPeriod budgetPeriod: budgetDocument.getBudgetPeriods()) {
            if(budgetPeriod.getBudgetLineItems().size() > 0) {
                addJustificationTextByBudgetPeriod(budgetPeriod, sb);                        
            }
        }
        budgetJustificationWrapper.setJustificationText(sb.toString());
    }

    /**
     * 
     * This method aggregates Justification text by BudgetPeriod
     * @param budgetPeriod
     * @param sb
     */
    private void addJustificationTextByBudgetPeriod(BudgetPeriod budgetPeriod, StringBuilder sb) {
        Map<String, CostElement> costElementsMappedToCostElementCode = loadCostElements();
        
        boolean periodHeaderAdded = false;
        for(BudgetLineItem lineItem: budgetPeriod.getBudgetLineItems()) {
            periodHeaderAdded = addLineItemJustificationText(costElementsMappedToCostElementCode, sb, budgetPeriod.getBudgetPeriod(), periodHeaderAdded, lineItem);
        }
        if(periodHeaderAdded) {
            addBudgetPeriodFooter(sb);
        }
    }

    /**
     * This method aggregates Line Item Justification text for a budget period 
     * @param costElementsMappedToCostElementCode
     * @param sb
     * @param budgetPeriodNumber
     * @param periodHeaderAdded
     * @param lineItem
     * @return
     */
    private boolean addLineItemJustificationText(Map<String, CostElement> costElementsMappedToCostElementCode, StringBuilder sb,
                                                    int budgetPeriodNumber, boolean periodHeaderAdded, BudgetLineItem lineItem) {
        String lineItemJustification = lineItem.getBudgetJustification();
        if(!StringUtils.isEmpty(lineItemJustification)) {
            if (!periodHeaderAdded) {
                addBudgetPeriodHeader(sb, budgetPeriodNumber);
                periodHeaderAdded = true;
            }
            addLineItemJustificationTextElements(costElementsMappedToCostElementCode, sb, lineItem, lineItemJustification);
        }
        return periodHeaderAdded;
    }
    

    /**
     * This method adds the line item justification elements to the buffer
     * @param costElementsMappedToCostElementCode
     * @param sb
     * @param lineItem
     * @param lineItemJustification
     */
    private void addLineItemJustificationTextElements(Map<String, CostElement> costElementsMappedToCostElementCode, StringBuilder sb,
                                                        BudgetLineItem lineItem, String lineItemJustification) {
        sb.append(costElementsMappedToCostElementCode.get(lineItem.getCostElement()).getDescription());
        sb.append("\n");
        sb.append(lineItemJustification);
        sb.append("\n");
    }

    /**
     * This method adds the required seprator text
     * @param sb
     */
    private void addLineItemJustificationTextSeparatorText(StringBuilder sb) {
        sb.append("\n\n");
        sb.append("***************************");
        sb.append("\n\n");
    }

    /**
     * This method updates the Justification text meta-data with the user and date/time
     * @param budgetJustificationWrapper
     */
    private void updateJustficationMetaData(BudgetJustificationWrapper budgetJustificationWrapper) {
        String updateUser = getLoggedInUserNetworkId();
        budgetJustificationWrapper.setLastUpdateUser(updateUser);
        budgetJustificationWrapper.setLastUpdateTime(new Date(System.currentTimeMillis()));
    }
}
