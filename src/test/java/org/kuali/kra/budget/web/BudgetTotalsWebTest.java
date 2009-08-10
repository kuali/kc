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
package org.kuali.kra.budget.web;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.web.ProposalDevelopmentWebTestBase;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.test.SQLDataLoader;
import org.kuali.rice.test.data.PerTestUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;
import org.kuali.rice.test.data.UnitTestSql;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;

public class BudgetTotalsWebTest extends ProposalDevelopmentWebTestBase {
    private static final String PDDOC_BUDGET_VERSIONS_LINK_NAME = "methodToCall.headerTab.headerDispatch.save.navigateTo.budgetVersions.x";
    private static final String BDOC_BUDGET_TOTALS_LINK_NAME = "methodToCall.headerTab.headerDispatch.save.navigateTo.summaryTotals.x";
    private static final String NEW_BUDGET_VERSION_NAME = "newBudgetVersionName";
    private static final String ADD_BUDGET_VERSION_BUTTON = "methodToCall.addBudgetVersion";

    private static final String DEFAULT_DOCUMENT_DESCRIPTION = "Proposal Development Web Test";
    private static final String DEFAULT_PROPOSAL_SPONSOR_CODE = "005894";
    private static final String DEFAULT_PROPOSAL_TITLE = "Project title";
    private static final String DEFAULT_PROPOSAL_REQUESTED_START_DATE = "09/01/2008";
    private static final String DEFAULT_PROPOSAL_REQUESTED_END_DATE = "08/31/2012";
    private static final String DEFAULT_PROPOSAL_ACTIVITY_TYPE = "1";
    private static final String DEFAULT_PROPOSAL_TYPE_CODE = "1";
    private static final String DEFAULT_PROPOSAL_OWNED_BY_UNIT = "IN-CARD";

    private static final String  TOTAL_HEAHER = "Totals Expenses Object Code Object Code Name Period 1 Period 2 Period 3 Period 4 Total"; 
    private static final String  OBJECTCODE_LINE_400025 = "400025 Faculty Salaries Tenured - On 160000.00 160000.00 160000.00 160000.00 640000.0"; 
    private static final String  OBJECTCODE_LINE_400390 = "400390 Post-Doctoral Staff 45000.00 45000.00 45000.00 45000.00 180000.0"; 
    private static final String  OBJECTCODE_LINE_400700 = "400700 Graduate Student Staff - On 50000.00 50000.00 50000.00 50000.00 200000.0"; 
    private static final String  OBJECTCODE_LINE_420050 = "420050 Travel Expenses 5000.00 5000.00 5000.00 5000.00 20000.0"; 
    private static final String  OBJECTCODE_LINE_420258 = "420258 Office Supplies 1000.00 1000.00 1000.00 1000.00 4000.0"; 
    private static final String  OBJECTCODE_LINE_420600 = "420600 Subcontracts (first 25K) - Subject to F\\&A 60000.00 60000.00 60000.00 60000.00 240000.0"; 
    private static final String  OBJECTCODE_LINE_420710 = "420710 Consultants 10000.00 10000.00 10000.00 10000.00 40000.0"; 
    private static final String  OH_MTDC_LINE = "Calculated Expenses F & A - MTDC 187500.00 187500.00 187500.00 187500.00 750000.0"; 
    private static final String  OH_TDC_LINE = "F & A - TDC 30956.25 0.00 0.00 0.00 30956.25"; 
    private static final String  EMP_BENEFIT_LINE = "Fringe Benefits - Research Rate 55350.00 55350.00 55350.00 55350.00 221400.0"; 
    private static final String  VACATION_LINE = "Vacation - Vacation 4275.00 4275.00 4275.00 4275.00 17100.0";
    private static final String  TOTALS_LINE = "Totals 609081.25 578125.00 578125.00 578125.00 2343456.25";
    
    private DocumentService documentService = null;
    private String documentNumber;
    private String proposalNumber;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        documentService = KNSServiceLocator.getDocumentService();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        documentService = null;
    }


    
    @Test
    public void testBudgetTotals() throws Exception {
//        /* get budget version page in proposal development module */
//        HtmlPage proposalBudgetVersionsPage = getBudgetVersionsPage();
//        /* add new version and open budget version page in budget module */
//        addBudgetVersion(proposalBudgetVersionsPage);
//        //ProposalDevelopmentDocument pd = (ProposalDevelopmentDocument) documentService.getByDocumentHeaderId(documentNumber);
//
//        // set up the details/detailscalamts properly
//        SQLDataLoader sqlDataLoader = new SQLDataLoader("update budget_details set proposal_number ="+ proposalNumber +" where proposal_number=999999");
//        sqlDataLoader.runSql();
//        sqlDataLoader = new SQLDataLoader("update budget_details_cal_amts set proposal_number ="+ proposalNumber +" where proposal_number=999999");
//        sqlDataLoader.runSql();
//        sqlDataLoader = new SQLDataLoader("commit");
//        sqlDataLoader.runSql();
//
//        // docsearch is hung for proposal page, so try budget version page
//        Map fieldValues = new HashMap();
//        fieldValues.put("proposalNumber", proposalNumber);
//        Collection budgetDocuments = (Collection)KraServiceLocator.getService(BusinessObjectService.class).findMatching(BudgetDocument.class, fieldValues);
//        assertNotNull(budgetDocuments);
//        
//        SQLDataLoader tmpDataLoader;
//        BudgetDocument budgetDoc = (BudgetDocument) budgetDocuments.iterator().next();
//        for(BudgetPeriod bPeriod: budgetDoc.getBudgetPeriods()) {
//            Integer bVersionNumber = bPeriod.getBudgetVersionNumber();
//            Integer bPeriodNumber = bPeriod.getBudgetPeriod();
//            Long bPeriodId  = bPeriod.getBudgetPeriodId(); 
//            tmpDataLoader = new SQLDataLoader("UPDATE BUDGET_DETAILS_CAL_AMTS T SET T.BUDGET_PERIOD_NUMBER = " + bPeriodId + " WHERE T.BUDGET_PERIOD_NUMBER = " + (1110+bPeriodNumber) );
//            tmpDataLoader.runSql();
//            tmpDataLoader = new SQLDataLoader("UPDATE BUDGET_DETAILS T SET T.BUDGET_PERIOD_NUMBER = " + bPeriodId + " WHERE T.BUDGET_PERIOD_NUMBER = " + (1110+bPeriodNumber) );
//            tmpDataLoader.runSql();
//            tmpDataLoader = new SQLDataLoader("commit");
//            tmpDataLoader.runSql();
//        }
//
//        HtmlPage budgetVersionsPage = docSearch(budgetDoc.getDocumentNumber());
//        //HtmlPage budgetVersionsPage = docSearch(Integer.toString(Integer.parseInt(documentNumber)+1));
//        /* get budget totals page */
//        HtmlPage budgetTotalsPage = clickOn(budgetVersionsPage, BDOC_BUDGET_TOTALS_LINK_NAME);
//
//        
//        /* check budget totals page.
//         * The numbers displayed in 'asText()' are not formatted
//         *  */
//        assertContains(budgetTotalsPage, TOTAL_HEAHER);
//        assertContains(budgetTotalsPage, OBJECTCODE_LINE_400025);
//        assertContains(budgetTotalsPage, OBJECTCODE_LINE_400390);
//        assertContains(budgetTotalsPage, OBJECTCODE_LINE_400700);
//        assertContains(budgetTotalsPage, OBJECTCODE_LINE_420050);
//        assertContains(budgetTotalsPage, OBJECTCODE_LINE_420258);
//        assertContains(budgetTotalsPage, OBJECTCODE_LINE_420600);
//        assertContains(budgetTotalsPage, OBJECTCODE_LINE_420710);
//        assertContains(budgetTotalsPage, OH_MTDC_LINE);
//        assertContains(budgetTotalsPage, OH_TDC_LINE);
//        assertContains(budgetTotalsPage, EMP_BENEFIT_LINE);
//        assertContains(budgetTotalsPage, VACATION_LINE);
//        assertContains(budgetTotalsPage, TOTALS_LINE);
//        
//        // remove details test data
//        sqlDataLoader = new SQLDataLoader("delete from budget_details where proposal_number ="+ proposalNumber);
//        sqlDataLoader.runSql();
//        sqlDataLoader = new SQLDataLoader("delete from budget_details_cal_amts where proposal_number ="+ proposalNumber);
//        sqlDataLoader.runSql();
//        sqlDataLoader = new SQLDataLoader("commit");
//        sqlDataLoader.runSql();
        
    }
    
    private HtmlPage getBudgetVersionsPage() throws Exception {
        HtmlPage proposalPage = this.getProposalDevelopmentPage();
        documentNumber = getFieldValue(proposalPage, "document.documentHeader.documentNumber");

        setRequiredFields(proposalPage, DEFAULT_DOCUMENT_DESCRIPTION,
                DEFAULT_PROPOSAL_SPONSOR_CODE,
                DEFAULT_PROPOSAL_TITLE,
                DEFAULT_PROPOSAL_REQUESTED_START_DATE,
                DEFAULT_PROPOSAL_REQUESTED_END_DATE,
                DEFAULT_PROPOSAL_ACTIVITY_TYPE,
                DEFAULT_PROPOSAL_TYPE_CODE,
                DEFAULT_PROPOSAL_OWNED_BY_UNIT);
        
        proposalPage = this.saveDoc(proposalPage);
        proposalNumber = getProposalNumber(proposalPage);
        
        HtmlPage budgetVersionsPage = clickOn(proposalPage, PDDOC_BUDGET_VERSIONS_LINK_NAME);
        return budgetVersionsPage;
    }
    
    private String getProposalNumber(HtmlPage proposalPage) {
        HtmlTable table = this.getTable(proposalPage, "tab-RequiredFieldsforSavingDocument-div");
        return table.getRow(0).getCell(1).asText().trim();
    }


    private HtmlPage addBudgetVersion(HtmlPage budgetVersionsPage) throws Exception {
        setFieldValue(budgetVersionsPage, NEW_BUDGET_VERSION_NAME, "Test Budget Version - 1");
        HtmlElement addBtn = getElementByName(budgetVersionsPage, ADD_BUDGET_VERSION_BUTTON, true);
        budgetVersionsPage = clickOn(addBtn);
        return budgetVersionsPage;
    }

}
