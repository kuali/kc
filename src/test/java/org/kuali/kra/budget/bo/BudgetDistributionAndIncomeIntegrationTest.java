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
package org.kuali.kra.budget.bo;

import java.sql.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.UserSession;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

import edu.iu.uis.eden.exception.WorkflowException;

public abstract class BudgetDistributionAndIncomeIntegrationTest extends KraTestBase {
    protected static final BudgetDecimal AMOUNT_1 = new BudgetDecimal(1000.00);
    protected static final BudgetDecimal AMOUNT_2 = new BudgetDecimal(2000.00);
    protected static final BudgetDecimal AMOUNT_3 = new BudgetDecimal(3000.00);
    
    protected static final int FY_2008 = 2008;
    protected static final int FY_2009 = 2009;
    protected static final int FY_2010 = 2010;
    
    protected static final String SOURCE_ACCOUNT_1 = "12345A";
    protected static final String SOURCE_ACCOUNT_2 = "12345B";
    protected static final String SOURCE_ACCOUNT_3 = "12345C";
    
    protected BudgetDocument budgetDocument;
    
    protected ProposalDevelopmentDocument proposalDocument;
    
    // Strategy pattern methods to be implemented by concrete subclasses
    protected abstract void addBudgetDistributionAndIncomeComponent(BudgetDistributionAndIncomeComponent component);
    protected abstract BudgetDistributionAndIncomeComponent createBudgetDistributionAndIncomeComponent();
    protected abstract BudgetDistributionAndIncomeComponent[] createBudgetDistributionAndIncomeComponentCollection();
    protected abstract List<? extends BudgetDistributionAndIncomeComponent> getBudgetDistributionAndIncomeComponents(BudgetDocument budgetDocument);
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        initProposalDocument();
        initBudgetDocument();
    }
    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        budgetDocument = null;
        super.tearDown();
    }
    @Test
    public void testDelete_FromMultipleInstances() throws Exception {
        BudgetDistributionAndIncomeComponent[] components = createBudgetDistributionAndIncomeComponentCollection();
        saveBudgetDistributionAndIncomeComponentCollection(components);
                
        for(int i = components.length - 1; i >= 0; i--) {
            assertNotNull(getBudgetDistributionAndIncomeComponents(budgetDocument).remove(i));
            getDocumentService().saveDocument(budgetDocument);
            BudgetDocument savedDocument = (BudgetDocument) getDocumentService().getByDocumentHeaderId(budgetDocument.getDocumentNumber());
            assertEquals(i, getBudgetDistributionAndIncomeComponents(savedDocument).size());
        }
    }     
    @Test
    public void testDelete_SingleInstance() throws Exception {
        initializeAndSaveSingleInstance();
        BudgetDocument savedDocument = loadDocumentAndCheckSingleComponentSaved();
              
        getBudgetDistributionAndIncomeComponents(savedDocument).remove(0);
        getDocumentService().saveDocument(savedDocument);
      
        savedDocument = (BudgetDocument) getDocumentService().getByDocumentHeaderId(budgetDocument.getDocumentNumber());
        assertEquals(0, getBudgetDistributionAndIncomeComponents(savedDocument).size());
    }
    
    @Test
    public void testSave_MultipleInstances() throws Exception {
        BudgetDistributionAndIncomeComponent[] components = createBudgetDistributionAndIncomeComponentCollection();
        BudgetDocument savedDocument = saveBudgetDistributionAndIncomeComponentCollection(components);
        assertNotNull(savedDocument);        
        assertEquals(components.length, getBudgetDistributionAndIncomeComponents(savedDocument).size());        
    }
    
    @Test
    public void testSave_SingleInstance() throws Exception {
        initializeAndSaveSingleInstance();
        loadDocumentAndCheckSingleComponentSaved();        
    }
    
    protected Date getNowDate() {
        return new Date(System.currentTimeMillis());
    }
    
    private BudgetPeriod createBudgetPeriod(Integer budgetPeriodNumber) {
        BudgetPeriod budgetPeriod = new BudgetPeriod();
        budgetPeriod.setBudgetPeriod(budgetPeriodNumber);
        budgetPeriod.setStartDate(getNowDate());
        budgetPeriod.setEndDate(getNowDate());
        return budgetPeriod;
    }
    
    private void initBudgetDocument() throws Exception {
        budgetDocument = (BudgetDocument) getDocumentService().getNewDocument("BudgetDocument");
        
        // add budget periods here to circumvent automatic generation of budget periods 
        budgetDocument.add(createBudgetPeriod(1));
        budgetDocument.add(createBudgetPeriod(2));
        
        budgetDocument.setProposalNumber(proposalDocument.getProposalNumber());
        budgetDocument.getDocumentHeader().setFinancialDocumentDescription("Budget Document");
        budgetDocument.setBudgetVersionNumber(1);
        budgetDocument.setStartDate(getNowDate());
        budgetDocument.setEndDate(getNowDate());
        budgetDocument.setOhRateClassCode("1");
        budgetDocument.setUrRateClassCode("2");
        budgetDocument.setModularBudgetFlag(false);
        
        getDocumentService().saveDocument(budgetDocument);
        budgetDocument = (BudgetDocument) getDocumentService().getByDocumentHeaderId(budgetDocument.getDocumentNumber());        
    }
    
    private void initializeAndSaveSingleInstance() throws WorkflowException, Exception {
        BudgetDistributionAndIncomeComponent component = createBudgetDistributionAndIncomeComponent();
        addBudgetDistributionAndIncomeComponent(component);
        getDocumentService().saveDocument(budgetDocument);
    }
    
    private void initProposalDocument() throws Exception {
        proposalDocument = (ProposalDevelopmentDocument) getDocumentService().getNewDocument("ProposalDevelopmentDocument");
        proposalDocument.getDocumentHeader().setFinancialDocumentDescription("ProposalDevelopmentDocumentTest test doc");
        proposalDocument.setSponsorCode("005770");
        proposalDocument.setTitle("project title");
        proposalDocument.setRequestedStartDateInitial(getNowDate());
        proposalDocument.setRequestedEndDateInitial(getNowDate());
        proposalDocument.setActivityTypeCode("1");
        proposalDocument.setProposalTypeCode("1");
        proposalDocument.setOwnedByUnitNumber("000001");

        getDocumentService().saveDocument(proposalDocument);

        proposalDocument = (ProposalDevelopmentDocument) getDocumentService().getByDocumentHeaderId(proposalDocument.getDocumentNumber());        
    }

    private BudgetDocument loadDocumentAndCheckSingleComponentSaved() throws WorkflowException, Exception {
        BudgetDocument savedDocument = (BudgetDocument) getDocumentService().getByDocumentHeaderId(budgetDocument.getDocumentNumber());
        assertNotNull(savedDocument);        
        assertEquals(1, getBudgetDistributionAndIncomeComponents(savedDocument).size());
        return savedDocument;
    }
    
    private BudgetDocument saveBudgetDistributionAndIncomeComponentCollection(BudgetDistributionAndIncomeComponent[] components) throws Exception {
        for(BudgetDistributionAndIncomeComponent component: components) {
              addBudgetDistributionAndIncomeComponent(component);
        }
                  
        getDocumentService().saveDocument(budgetDocument);
    
        return (BudgetDocument) getDocumentService().getByDocumentHeaderId(budgetDocument.getDocumentNumber());
    }
}
