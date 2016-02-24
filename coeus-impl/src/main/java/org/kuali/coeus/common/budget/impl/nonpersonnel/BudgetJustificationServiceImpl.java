/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
package org.kuali.coeus.common.budget.impl.nonpersonnel;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetJustificationService;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetJustificationWrapper;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.core.BudgetException;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.CostElement;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

@Component("budgetJustificationService")
public class BudgetJustificationServiceImpl implements BudgetJustificationService {

    protected final Log LOG = LogFactory.getLog(BudgetJustificationServiceImpl.class);

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    @Override
    public void consolidateExpenseJustifications(Budget budget, BudgetJustificationWrapper budgetJustificationWrapper) throws BudgetException {
        if(budget.areLineItemJustificationsPresent()) {
            addConsolidatedLineItemJustificationText(budget, budgetJustificationWrapper);
        } else {
            LOG.error( "There are no line item budget justifications");
            throw new BudgetException();
        }
    }

    @Override
    public void preSave(Budget budget, BudgetJustificationWrapper budgetJustificationWrapper) {
        updateJustficationMetaData(budgetJustificationWrapper);
        budget.setBudgetJustification(budgetJustificationWrapper.toString());
    }
    
    /**
     * This method looks up user id. Protected to allow stubbing
     * @return
     */
    protected String getLoggedInUserNetworkId() {
        return globalVariableService.getUserSession().getPrincipalName();
    }
    
    /**
     * This method loads Cost Elements, mapping them to their cost element code
     * @return
     */
    @SuppressWarnings("unchecked")
    protected Map<String, CostElement> loadCostElements() {
        Collection<CostElement> costElements = (Collection<CostElement>) getBusinessObjectService().findAll(CostElement.class);
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
    protected void addBudgetPeriodFooter(StringBuilder sb) {
        sb.append("\n");
    }

    /**
     * This method adds the required header for each budget period
     * @param sb
     * @param budgetPeriodNumber
     */
    protected void addBudgetPeriodHeader(StringBuilder sb, int budgetPeriodNumber) {
        sb.append("Period ");
        sb.append(budgetPeriodNumber);
        sb.append("\n");
    }

    /**
     * This method adds consolidated Line Item Justification text to any existing text
     * @param budget
     * @param budgetJustificationWrapper
     */
    protected void addConsolidatedLineItemJustificationText(Budget budget, BudgetJustificationWrapper budgetJustificationWrapper) {
        String existingJustificationText = budgetJustificationWrapper.getJustificationText();
        StringBuilder sb = new StringBuilder();
        
        if(!StringUtils.isEmpty(existingJustificationText)) {
            sb.append(existingJustificationText);
            addLineItemJustificationTextSeparatorText(sb);
        }
        
        for(BudgetPeriod budgetPeriod: budget.getBudgetPeriods()) {
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
    protected void addJustificationTextByBudgetPeriod(BudgetPeriod budgetPeriod, StringBuilder sb) {
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
    protected boolean addLineItemJustificationText(Map<String, CostElement> costElementsMappedToCostElementCode, StringBuilder sb,
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
    protected void addLineItemJustificationTextElements(Map<String, CostElement> costElementsMappedToCostElementCode, StringBuilder sb,
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
    protected void addLineItemJustificationTextSeparatorText(StringBuilder sb) {
        sb.append("\n\n");
        sb.append("***************************");
        sb.append("\n\n");
    }

    /**
     * This method updates the Justification text meta-data with the user and date/time
     * @param budgetJustificationWrapper
     */
    protected void updateJustficationMetaData(BudgetJustificationWrapper budgetJustificationWrapper) {
        String updateUser = getLoggedInUserNetworkId();
        budgetJustificationWrapper.setLastUpdateUser(updateUser);
        budgetJustificationWrapper.setLastUpdateTime(new Date(System.currentTimeMillis()));
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }
}
