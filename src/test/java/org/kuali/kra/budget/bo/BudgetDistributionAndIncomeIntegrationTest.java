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
package org.kuali.kra.budget.bo;

import java.sql.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.distributionincome.BudgetDistributionAndIncomeComponent;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;

public abstract class BudgetDistributionAndIncomeIntegrationTest extends KcUnitTestBase {
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
    
    protected ProposalDevelopmentService proposalDevelopmentService;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        proposalDevelopmentService = KraServiceLocator.getService(ProposalDevelopmentService.class);
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
        Budget budget = budgetDocument.getBudget();
        // add budget periods here to circumvent automatic generation of budget periods 
        budget.add(createBudgetPeriod(1));
        budget.add(createBudgetPeriod(2));
        
//        budget.setProposalNumber(proposalDocument.getDevelopmentProposal().getProposalNumber());
        budgetDocument.getDocumentHeader().setDocumentDescription("Budget Document");
        budget.setBudgetVersionNumber(1);
        budget.setStartDate(getNowDate());
        budget.setEndDate(getNowDate());
        budget.setOhRateClassCode("1");
        budget.setUrRateClassCode("2");
        budget.setModularBudgetFlag(false);
        
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
        proposalDocument.getDocumentHeader().setDocumentDescription("ProposalDevelopmentDocumentTest test doc");
        proposalDocument.getDevelopmentProposal().setSponsorCode("005770");
        proposalDocument.getDevelopmentProposal().setTitle("project title");
        proposalDocument.getDevelopmentProposal().setRequestedStartDateInitial(getNowDate());
        proposalDocument.getDevelopmentProposal().setRequestedEndDateInitial(getNowDate());
        proposalDocument.getDevelopmentProposal().setActivityTypeCode("1");
        proposalDocument.getDevelopmentProposal().setProposalTypeCode("1");
        proposalDocument.getDevelopmentProposal().setOwnedByUnitNumber("000001");

        proposalDevelopmentService.initializeUnitOrganizationLocation(proposalDocument);
        proposalDevelopmentService.initializeProposalSiteNumbers(proposalDocument);
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
