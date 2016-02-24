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
package org.kuali.kra.budget.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.budget.framework.core.BudgetException;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.CostElement;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetJustificationService;
import org.kuali.coeus.common.budget.impl.nonpersonnel.BudgetJustificationServiceImpl;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetJustificationWrapper;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;

import java.util.Map;
import java.util.TreeMap;

public class BudgetJustificationServiceTest {
    private Budget budgetDocument;
    private BudgetJustificationService budgetJustificationService;
    private BudgetJustificationWrapper budgetJustificationWrapper;
    private Map<String, CostElement> costElementMap;
    private String userId;
    
    @Before
    public void setUp() {
        initializeCostElementMap();        
        initializeBudgetJustificationService();        
        initializeBudgetDocument();        
        budgetJustificationWrapper = new BudgetJustificationWrapper(null);
    }

    @After
    public void tearDown() {
        budgetDocument = null;
        budgetJustificationWrapper = null;
        budgetJustificationService = null;
    }

    @Test(expected=BudgetException.class)
    public void testJustificationConsolidation_NoLineItemJustifications() throws Exception {
        budgetJustificationService.consolidateExpenseJustifications(budgetDocument, budgetJustificationWrapper);
    }
    
    @Test
    public void testPreSave() throws Exception {
        initializeBudgetJustificationWrapper();
        
        String originalLastUpdateTime = budgetJustificationWrapper.getLastUpdateTime();
        String originalLastUpdateUser = budgetJustificationWrapper.getLastUpdateUser();
        
        budgetDocument.setBudgetJustification(budgetJustificationWrapper.toString());        
        userId = "newUser";
        budgetJustificationService.preSave(budgetDocument, budgetJustificationWrapper);
        
        BudgetJustificationWrapper testWrapper = new BudgetJustificationWrapper(budgetDocument.getBudgetJustification()); 
        Assert.assertTrue(!testWrapper.getLastUpdateTime().equals(originalLastUpdateTime));
        Assert.assertTrue(!testWrapper.getLastUpdateUser().equals(originalLastUpdateUser));
        Assert.assertEquals("newUser", testWrapper.getLastUpdateUser());        
    }
    
    @Test
    public void testJustificationConsolidation_WithStartingValue_WithNoLineItemJustifications() throws Exception {
        initializeBudgetJustificationWrapper();
        
        String startingValue = budgetJustificationWrapper.getJustificationText();
        budgetDocument.setBudgetJustification(budgetJustificationWrapper.toString());
        
        try {
            budgetJustificationService.consolidateExpenseJustifications(budgetDocument, budgetJustificationWrapper);            
            Assert.assertEquals(budgetJustificationWrapper.getJustificationText(), startingValue);
            Assert.assertTrue("\nStarting text: " + startingValue + "\nConsolidated text:\n" + budgetJustificationWrapper.getJustificationText(), 
                                budgetJustificationWrapper.getJustificationText().startsWith(startingValue));
        } catch (BudgetException e) {
            // expected here
        }
    }
    
    @Test
    public void testJustificationConsolidation_WithStartingValue_WithLineItemJustifications() throws Exception {
        initializeBudgetJustificationWrapper();
        addTestLineItemsToBudgetPeriods();
        
        String startingValue = budgetJustificationWrapper.getJustificationText();
        budgetDocument.setBudgetJustification(budgetJustificationWrapper.toString());
        
        try {
            budgetJustificationService.consolidateExpenseJustifications(budgetDocument, budgetJustificationWrapper);            
            Assert.assertTrue(budgetJustificationWrapper.getJustificationText().length() > startingValue.length());
            Assert.assertTrue("\nStarting text: " + startingValue + "\nConsolidated text:\n" + budgetJustificationWrapper.getJustificationText(), 
                                budgetJustificationWrapper.getJustificationText().startsWith(startingValue));
        } catch (BudgetException e) {
            // expected here
        }
    }

    private void addTestLineItemsToBudgetPeriods() {
        for(BudgetPeriod budgetPeriod: budgetDocument.getBudgetPeriods()) {
            int i = 1;        
            for (String costElementCode: costElementMap.keySet()) {  
                BudgetLineItem lineItem = budgetPeriod.getNewBudgetLineItem();
                lineItem.setBudgetPeriod(budgetPeriod.getBudgetPeriod());
                lineItem.setLineItemNumber(new Integer(i));
                lineItem.setLineItemSequence(new Integer(i));
                lineItem.setBudgetJustification(String.format("Line Item %d justification", i++));
                lineItem.setCostElement(costElementCode);
                budgetPeriod.getBudgetLineItems().add(lineItem);
            }
        }
    }
    
    @Test
    public void testJustificationConsolidation_WithStartingValue_NoLineItemJustifications() throws Exception {
        initializeBudgetJustificationWrapper();
        
        String startingValue = budgetJustificationWrapper.toString();
        budgetDocument.setBudgetJustification(startingValue);
        
        try {
            budgetJustificationService.consolidateExpenseJustifications(budgetDocument, budgetJustificationWrapper);
            Assert.assertEquals(startingValue, budgetDocument.getBudgetJustification());
        } catch (BudgetException e) {
            // expected here
        }
    }
        
    private void initializeBudgetDocument() {
        budgetDocument = new ProposalDevelopmentBudgetExt();
        for(int i = 1; i <= 2; i++) {
            BudgetPeriod budgetPeriod = new BudgetPeriod();
            budgetPeriod.setBudgetPeriod(i);
            budgetDocument.getBudgetPeriods().add(budgetPeriod);
        }
    }

    /**
     * This method initializes the BudgetJustificationService, but stubs out its methods that do service/object lookups
     */
    private void initializeBudgetJustificationService() {
        budgetJustificationService = new BudgetJustificationServiceImpl() {
            @Override
            protected Map<String, CostElement> loadCostElements() {
                return costElementMap;
            }

            @Override
            protected String getLoggedInUserNetworkId() {
                return getUserId();
            }
        };
    }
    
    private void initializeBudgetJustificationWrapper() {
        budgetJustificationWrapper.setJustificationText("Starting text");
        budgetJustificationWrapper.setLastUpdateTime("01/01/2008 20:10");
        budgetJustificationWrapper.setLastUpdateUser("testUser");
    }
    
    private void initializeCostElementMap() {
        costElementMap = new TreeMap<String, CostElement>();
        for(int i = 0; i < 10; i++) {
            CostElement ce = new CostElement();
            ce.setCostElement("ce" + i);
            ce.setDescription(String.format("Cost Element %d text", i));
            costElementMap.put(ce.getCostElement(), ce);
        }
    }
 
    private String getUserId() {
        return userId;
    }
}
