/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.award.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

/**
 * Tests the Reports tab in the Payment, Reports & Terms page of an Award.
 */
public class AwardReportsPanelSeleniumTest extends KcSeleniumTestBase {
    
    private static final String TAB_ID = "Reports";
    private static final String ERROR_PANEL_ID = "tab-ReportClasses:Financial-div";
    private static final String SUB_TAB_ID = "div[class='innerTab-head']";
    private static final String FINANCIAL_TAB_ID = "ReportClasses:Financial";
    private static final String PROPERTY_TAB_ID = "ReportClasses:Property";
    
    private static final String BEAN_PREFIX = "awardReportsBean.newAwardReportTerms[%d].";
    private static final String LIST_PREFIX = "document.awardList[0].";
    
    private static final String REPORT_CODE_ID = "reportCode";
    private static final String FREQUENCY_CODE_ID = "frequencyCode";
    private static final String FREQUENCY_BASE_CODE_ID = "frequencyBaseCode";
    private static final String OSP_DISTRIBUTION_CODE_ID = "ospDistributionCode";
    private static final String DUE_DATE_ID = "dueDate";
    private static final String ROLODEX_ID_ID = "rolodexId";
    private static final String SUB_PLAN_FLAG_ID = "subPlanFlag";
    private static final String PROCUREMENT_PRIORITY_CODE_ID = "procurementPriorityCode";
    private static final String BEAN_REPORT_CODE_FINANCIAL_ID = String.format(BEAN_PREFIX, 0) + REPORT_CODE_ID;
    private static final String BEAN_FREQUENCY_CODE_FINANCIAL_ID = String.format(BEAN_PREFIX, 0) + FREQUENCY_CODE_ID;
    private static final String BEAN_FREQUENCY_BASE_CODE_FINANCIAL_ID = String.format(BEAN_PREFIX, 0) + FREQUENCY_BASE_CODE_ID;
    private static final String BEAN_OSP_DISTRIBUTION_CODE_FINANCIAL_ID = String.format(BEAN_PREFIX, 0) + OSP_DISTRIBUTION_CODE_ID;
    private static final String BEAN_DUE_DATE_FINANCIAL_ID = String.format(BEAN_PREFIX, 0) + DUE_DATE_ID;
    private static final String BEAN_REPORT_CODE_PROPERTY_ID = String.format(BEAN_PREFIX, 4) + REPORT_CODE_ID;
    private static final String BEAN_FREQUENCY_CODE_PROPERTY_ID = String.format(BEAN_PREFIX, 4) + FREQUENCY_CODE_ID;
    private static final String BEAN_FREQUENCY_BASE_CODE_PROPERTY_ID = String.format(BEAN_PREFIX, 4) + FREQUENCY_BASE_CODE_ID;
    private static final String BEAN_OSP_DISTRIBUTION_CODE_PROPERTY_ID = String.format(BEAN_PREFIX, 4) + OSP_DISTRIBUTION_CODE_ID;
    private static final String BEAN_DUE_DATE_PROPERTY_ID = String.format(BEAN_PREFIX, 4) + DUE_DATE_ID;
    private static final String LIST_SUB_PLAN_FLAG_ID = LIST_PREFIX + SUB_PLAN_FLAG_ID;
    private static final String LIST_PROCUREMENT_PRIORITY_CODE_ID = LIST_PREFIX + PROCUREMENT_PRIORITY_CODE_ID;
    
    private static final String FINANCIAL_LABEL = "Financial (%d)";
    private static final String PROPERTY_LABEL = "Property (%d)";
    
    private static final String REPORT_CODE_FINANCIAL = "Final (Final Report)";
    private static final String REPORT_CODE_PROPERTY = "SF 1018 (Final Report)";
    private static final String FREQUENCY_CODE = "As required";
    private static final String FREQUENCY_BASE_CODE = "Project Start Date";
    private static final String OSP_DISTRIBUTION_CODE_EMPTY = "select";
    private static final String OSP_DISTRIBUTION_CODE_FINANCIAL = "Report";
    private static final String OSP_DISTRIBUTION_CODE_PROPERTY = "Letter Only";
    private static final String DUE_DATE = "06/30/2008";
    private static final String ROLODEX_ID = "18725";
    private static final String SUB_PLAN_FLAG = "Required";
    private static final String PROCUREMENT_PRIORITY_CODE = "DO0076";
    
    private static final String ERROR_REPORT_CODE_REQUIRED = "Type (Type) is a required field";
    private static final String ERROR_FREQUENCY_CODE_REQUIRED = "Frequency (Frequency) is a required field";
    private static final String ERROR_OSP_DISTRIBUTION_CODE_REQUIRED = "OSP File Copy (OSP File Copy ) is a required field";
    
    private static final String ADD_AWARD_REPORT_TERM_FINANCIAL_BUTTON = "methodToCall.addAwardReportTerm.reportClass1.reportClassIndex0";
    private static final String DELETE_AWARD_REPORT_TERM_FINANCIAL_BUTTON = "methodToCall.deleteAwardReportTerm.line0";
    private static final String ADD_AWARD_REPORT_TERM_PROPERTY_BUTTON = "methodToCall.addAwardReportTerm.reportClass2.reportClassIndex4";    
    private static final String DELETE_AWARD_REPORT_TERM_PROPERTY_BUTTON = "methodToCall.deleteAwardReportTerm.line0";
    private static final String ADD_RECIPIENT_BUTTON = "methodToCall.addRecipient.awardReportTerm0";
    
    private AwardSeleniumHelper helper;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        helper = AwardSeleniumHelper.instance(driver);
    }
    
    @After
    public void tearDown() throws Exception {
        helper = null;
        
        super.tearDown();
    }
    
    /**
     * Test the addition and deletion of financial report classes with no errors.
     *
     * @throws Exception
     */
    @Test
    public void testAddDeleteFinancialNoErrors() throws Exception {
        helper.createAward();
        helper.clickAwardPaymentReportsAndTermsPage();
        
        helper.openTab(TAB_ID);
        
        helper.assertSelectorContains(SUB_TAB_ID, String.format(FINANCIAL_LABEL, 0));
        
        helper.openTab(FINANCIAL_TAB_ID);
        helper.set(BEAN_REPORT_CODE_FINANCIAL_ID, REPORT_CODE_FINANCIAL);
        helper.set(BEAN_FREQUENCY_CODE_FINANCIAL_ID, FREQUENCY_CODE);
        helper.set(BEAN_FREQUENCY_BASE_CODE_FINANCIAL_ID, FREQUENCY_BASE_CODE);
        helper.set(BEAN_OSP_DISTRIBUTION_CODE_FINANCIAL_ID, OSP_DISTRIBUTION_CODE_FINANCIAL);
        helper.set(BEAN_DUE_DATE_FINANCIAL_ID, DUE_DATE);
        
        helper.click(ADD_AWARD_REPORT_TERM_FINANCIAL_BUTTON);
        
        helper.saveDocument();
        helper.assertNoPageErrors();

        helper.assertSelectorContains(SUB_TAB_ID, String.format(FINANCIAL_LABEL, 1));
        
        helper.click(DELETE_AWARD_REPORT_TERM_FINANCIAL_BUTTON);

        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertSelectorContains(SUB_TAB_ID, String.format(FINANCIAL_LABEL, 0));
    }
    
    /**
     * Test the addition of financial report classes with errors.
     *
     * @throws Exception
     */
    @Test
    public void testAddFinancialErrors() throws Exception {
        helper.createAward();
        helper.clickAwardPaymentReportsAndTermsPage();
        
        helper.openTab(TAB_ID);
        
        helper.openTab(FINANCIAL_TAB_ID);
        helper.set(BEAN_OSP_DISTRIBUTION_CODE_FINANCIAL_ID, OSP_DISTRIBUTION_CODE_FINANCIAL);
        
        helper.click(ADD_AWARD_REPORT_TERM_FINANCIAL_BUTTON);
        
        helper.assertErrorCount(ERROR_PANEL_ID, 3);
        helper.assertError(ERROR_PANEL_ID, ERROR_REPORT_CODE_REQUIRED);
        helper.assertError(ERROR_PANEL_ID, ERROR_FREQUENCY_CODE_REQUIRED);
        
        helper.set(BEAN_REPORT_CODE_FINANCIAL_ID, REPORT_CODE_FINANCIAL);
        helper.set(BEAN_FREQUENCY_CODE_FINANCIAL_ID, FREQUENCY_CODE);        
        helper.set(BEAN_FREQUENCY_BASE_CODE_FINANCIAL_ID, FREQUENCY_BASE_CODE);
        helper.set(BEAN_OSP_DISTRIBUTION_CODE_FINANCIAL_ID, OSP_DISTRIBUTION_CODE_EMPTY);

        helper.click(ADD_AWARD_REPORT_TERM_FINANCIAL_BUTTON);
        
        helper.assertErrorCount(ERROR_PANEL_ID, 1);
        helper.assertError(ERROR_PANEL_ID, ERROR_OSP_DISTRIBUTION_CODE_REQUIRED);       
    }
    
    /**
     * Test the addition financial recipients.
     *
     * @throws Exception
     */
    @Test
    public void testAddFinancialRecipients() throws Exception {
        helper.createAward();
        helper.clickAwardPaymentReportsAndTermsPage();
        
        helper.openTab(TAB_ID);
        
        helper.openTab(FINANCIAL_TAB_ID);
        helper.set(BEAN_REPORT_CODE_FINANCIAL_ID, REPORT_CODE_FINANCIAL);
        helper.set(BEAN_FREQUENCY_CODE_FINANCIAL_ID, FREQUENCY_CODE);
        helper.set(BEAN_FREQUENCY_BASE_CODE_FINANCIAL_ID, FREQUENCY_BASE_CODE);
        helper.set(BEAN_OSP_DISTRIBUTION_CODE_FINANCIAL_ID, OSP_DISTRIBUTION_CODE_FINANCIAL);
        helper.set(BEAN_DUE_DATE_FINANCIAL_ID, DUE_DATE);
        
        helper.click(ADD_AWARD_REPORT_TERM_FINANCIAL_BUTTON);
        
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.openTab(3);
        helper.lookup(ROLODEX_ID_ID, ROLODEX_ID_ID, ROLODEX_ID);
        
        helper.click(ADD_RECIPIENT_BUTTON);
        
        helper.saveDocument();
        helper.assertNoPageErrors();
    }
    
    /**
     * Test the addition and deletion of financial property classes.
     *
     * @throws Exception
     */
    @Test
    public void testAddDeleteProperty() throws Exception {
        helper.createAward();
        helper.clickAwardPaymentReportsAndTermsPage();
        
        helper.openTab(TAB_ID);
        
        helper.assertSelectorContains(SUB_TAB_ID, String.format(PROPERTY_LABEL, 0));
                
        helper.openTab(PROPERTY_TAB_ID);
        helper.set(BEAN_REPORT_CODE_PROPERTY_ID, REPORT_CODE_PROPERTY);
        helper.set(BEAN_FREQUENCY_CODE_PROPERTY_ID, FREQUENCY_CODE);
        helper.set(BEAN_FREQUENCY_BASE_CODE_PROPERTY_ID, FREQUENCY_BASE_CODE);
        helper.set(BEAN_OSP_DISTRIBUTION_CODE_PROPERTY_ID, OSP_DISTRIBUTION_CODE_PROPERTY);
        helper.set(BEAN_DUE_DATE_PROPERTY_ID, DUE_DATE);
        
        helper.click(ADD_AWARD_REPORT_TERM_PROPERTY_BUTTON);
        
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertSelectorContains(SUB_TAB_ID, String.format(PROPERTY_LABEL, 1));
        
        helper.click(DELETE_AWARD_REPORT_TERM_PROPERTY_BUTTON);

        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertSelectorContains(SUB_TAB_ID, String.format(PROPERTY_LABEL, 0));
    }
    
    /**
     * Test the modification of miscellaneous procurement purchasing fields.
     *
     * @throws Exception
     */
    @Test
    public void testModifyMiscellaneousProcurementPurchasing() throws Exception {
        helper.createAward();
        helper.clickAwardPaymentReportsAndTermsPage();
        
        helper.openTab(TAB_ID);
        
        helper.set(LIST_SUB_PLAN_FLAG_ID, SUB_PLAN_FLAG);
        helper.set(LIST_PROCUREMENT_PRIORITY_CODE_ID, PROCUREMENT_PRIORITY_CODE);

        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertElementContains(LIST_SUB_PLAN_FLAG_ID, SUB_PLAN_FLAG);
        helper.assertElementContains(LIST_PROCUREMENT_PRIORITY_CODE_ID, PROCUREMENT_PRIORITY_CODE);
    }
    
}