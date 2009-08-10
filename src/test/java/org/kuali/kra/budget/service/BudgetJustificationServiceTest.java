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
package org.kuali.kra.budget.service;

import java.util.Map;
import java.util.TreeMap;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.budget.BudgetException;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.CostElement;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.nonpersonnel.BudgetJustificationService;
import org.kuali.kra.budget.nonpersonnel.BudgetJustificationServiceImpl;
import org.kuali.kra.budget.nonpersonnel.BudgetJustificationWrapper;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.parameters.BudgetPeriod;

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
                BudgetLineItem lineItem = new BudgetLineItem();
                lineItem.setBudgetPeriod(budgetPeriod.getBudgetPeriod());        
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
        budgetDocument = new Budget();
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
