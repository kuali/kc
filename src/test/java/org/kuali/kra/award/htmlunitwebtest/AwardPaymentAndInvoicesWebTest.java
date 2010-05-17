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
package org.kuali.kra.award.htmlunitwebtest;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlImageInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * 
 * This is the integration test for Award Reports page. 
 */
@org.junit.Ignore("This test is not meant to be run against the 2.0 release")
public class AwardPaymentAndInvoicesWebTest extends AwardPaymentsAndTermsWebTest {
    
    private static final String METHOD_TO_CALL_REFRESH_PULL_DOWN_MENUS = "methodToCall.refreshPulldownOptions";    
    private static final String METHOD_TO_CALL_ADD_AWARD_REPORT_TERM_FOR_PAYMENT_AND_INVOICES = "methodToCall.addAwardReportTerm.reportClass6.reportClassIndex5";
    private static final String METHOD_TO_CALL_ADD_PAYMENT_SCHEDULE = "methodToCall.addPaymentScheduleItem";
    private static final String METHOD_TO_CALL_ADD_AWARD_REPORT_TERM_WHERE_CLASS_IS_PROPERTY= "methodToCall.addAwardReportTerm.reportClass2.reportClassIndex3";    
    
    private static final String BASIS_OF_PAYMENT_CODE_FIELD = "document.awardList[0].basisOfPaymentCode";
    private static final String METHOD_OF_PAYMENT_CODE_FIELD = "document.awardList[0].methodOfPaymentCode";
    private static final String FINAL_INVOICE_DUE_FIELD = "document.awardList[0].finalInvoiceDue";
    private static final String DOCUMENT_FUNDING_ID_FIELD = "document.awardList[0].documentFundingId";
    private static final String NEW_AWARD_REPORT_TERM_0 = "newAwardReportTerm[0]";
    private static final String NEW_AWARD_REPORT_TERM_5 = "newAwardReportTerm[5]";
    private static final String REPORT_CODE_0 = NEW_AWARD_REPORT_TERM_0 + ".reportCode";
    private static final String FREQUENCY_CODE_0 = NEW_AWARD_REPORT_TERM_0 + ".frequencyCode";
    private static final String FREQUENCY_BASE_CODE_0 = NEW_AWARD_REPORT_TERM_0 + ".frequencyBaseCode";
    private static final String OSP_DISTRIBUTION_CODE_0 = NEW_AWARD_REPORT_TERM_0 + ".ospDistributionCode";
    private static final String DUE_DATE_0 = NEW_AWARD_REPORT_TERM_0 + ".dueDate";
    private static final String REPORT_CODE_5 = NEW_AWARD_REPORT_TERM_5 + ".reportCode";
    private static final String FREQUENCY_CODE_5 = NEW_AWARD_REPORT_TERM_5 + ".frequencyCode";
    private static final String FREQUENCY_BASE_CODE_5 = NEW_AWARD_REPORT_TERM_5 + ".frequencyBaseCode";
    private static final String OSP_DISTRIBUTION_CODE_5 = NEW_AWARD_REPORT_TERM_5 + ".ospDistributionCode";
    private static final String DUE_DATE_5 = NEW_AWARD_REPORT_TERM_5 + ".dueDate";
    private static final String NEW_AWARD_REPORT_TERM_3 = "newAwardReportTerm[3]";
    private static final String NEW_INVOICE_NUMBER = "paymentScheduleBean.newAwardPaymentSchedule.invoiceNumber";
    private static final String NEW_DUE_DATE = "paymentScheduleBean.newAwardPaymentSchedule.dueDate";
    private static final String NEW_SUBMITTED_BY = "paymentScheduleBean.newAwardPaymentSchedule.submittedBy";
    private static final String NEW_STATUS = "paymentScheduleBean.newAwardPaymentSchedule.status";
    private static final String NEW_SUBMIT_DATE = "paymentScheduleBean.newAwardPaymentSchedule.submitDate";
    private static final String NEW_STATUS_DESCRIPTION = "paymentScheduleBean.newAwardPaymentSchedule.statusDescription";
    private static final String NEW_AMOUNT = "paymentScheduleBean.newAwardPaymentSchedule.amount";    
    private static final String REPORT_CODE_3 = NEW_AWARD_REPORT_TERM_3 + ".reportCode";
    private static final String FREQUENCY_CODE_3 = NEW_AWARD_REPORT_TERM_3 + ".frequencyCode";
    private static final String FREQUENCY_BASE_CODE_3 = NEW_AWARD_REPORT_TERM_3 + ".frequencyBaseCode";
    private static final String OSP_DISTRIBUTION_CODE_3 = NEW_AWARD_REPORT_TERM_3 + ".ospDistributionCode";
    private static final String DUE_DATE_3 = NEW_AWARD_REPORT_TERM_3 + ".dueDate";
    private static final String REPORT_CODE_MANDATORY_ERROR_MESSAGE = "Type is a mandatory field";
    private static final String FREQUENCY_CODE_MANDATORY_ERROR_MESSAGE = "Frequency is a mandatory field";
    private static final String FREQUENCY_BASE_CODE_MANDATORY_ERROR_MESSAGE = "Frequency Base is a mandatory field";
    private static final String OSP_DISTRIBUTION_CODE_MANDATORY_ERROR_MESSAGE = "OSP File Copy is a mandatory field";
    private static final String DUE_DATE_MANDATORY_ERROR_MESSAGE = "Due Date is a mandatory field";
    private static final String SMALL_BUSINESS_SUBCONTRACTING_PLAN = "document.awardList[0].subPlanFlag";
    private static final String PROCUREMENT_PRIORITY_CODE = "document.awardList[0].procurementPriorityCode";
    /**
     * The set up method calls the parent super method and gets the 
     * award Payment, Reports and Terms page after that.
     * @see org.kuali.kra.award.htmlunitwebtest.AwardWebTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * This method calls parent tear down method and than sets awardTimeAndMoneyPage to null
     * @see org.kuali.kra.award.htmlunitwebtest.AwardWebTestBase#tearDown()
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    @Test
    public void testPaymentAndInvoicesTopPanel() throws Exception{
        
        setFieldValue(paymentReportsAndTermsPage, BASIS_OF_PAYMENT_CODE_FIELD, "1");
        setFieldValue(paymentReportsAndTermsPage, METHOD_OF_PAYMENT_CODE_FIELD, "1");
        setFieldValue(paymentReportsAndTermsPage, FINAL_INVOICE_DUE_FIELD, "5");
        setFieldValue(paymentReportsAndTermsPage, DOCUMENT_FUNDING_ID_FIELD, "5");
        paymentReportsAndTermsPage = clickOn(paymentReportsAndTermsPage, "methodToCall.save");
        assertDoesNotContain(paymentReportsAndTermsPage, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);        
        assertDoesNotContain(paymentReportsAndTermsPage, ERRORS_FOUND_ON_PAGE);        
        assertContains(paymentReportsAndTermsPage,SAVE_SUCCESS_MESSAGE);
        
        setFieldValue(paymentReportsAndTermsPage, BASIS_OF_PAYMENT_CODE_FIELD, "");
        setFieldValue(paymentReportsAndTermsPage, METHOD_OF_PAYMENT_CODE_FIELD, "");
        setFieldValue(paymentReportsAndTermsPage, FINAL_INVOICE_DUE_FIELD, "");
        setFieldValue(paymentReportsAndTermsPage, DOCUMENT_FUNDING_ID_FIELD, "");
        paymentReportsAndTermsPage = clickOn(paymentReportsAndTermsPage, "methodToCall.save");
        assertDoesNotContain(paymentReportsAndTermsPage, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);        
        assertDoesNotContain(paymentReportsAndTermsPage, ERRORS_FOUND_ON_PAGE);        
        assertContains(paymentReportsAndTermsPage,SAVE_SUCCESS_MESSAGE);
        
        /*setFieldValue(paymentReportsAndTermsPage, BASIS_OF_PAYMENT_CODE_FIELD, "");
        setFieldValue(paymentReportsAndTermsPage, METHOD_OF_PAYMENT_CODE_FIELD, "");
        setFieldValue(paymentReportsAndTermsPage, FINAL_INVOICE_DUE_FIELD, "txt");
        setFieldValue(paymentReportsAndTermsPage, DOCUMENT_FUNDING_ID_FIELD, "");
        paymentReportsAndTermsPage = clickOn(paymentReportsAndTermsPage, "methodToCall.save");
        assertDoesNotContain(paymentReportsAndTermsPage, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);        
        assertDoesNotContain(paymentReportsAndTermsPage,SAVE_SUCCESS_MESSAGE);
        assertContains(paymentReportsAndTermsPage, ERRORS_FOUND_ON_PAGE);
        assertContains(paymentReportsAndTermsPage, "txt is not a valid Integer");*/        
    }
    
    @Test
    public void testPaymentAndInvoicesRequirementsSimpleAddDelete() throws Exception{
        setFieldValue(paymentReportsAndTermsPage, REPORT_CODE_5, "5");
        paymentReportsAndTermsPage = clickOn(paymentReportsAndTermsPage, METHOD_TO_CALL_REFRESH_PULL_DOWN_MENUS);        
        setFieldValue(paymentReportsAndTermsPage, FREQUENCY_CODE_5, "11");        
        paymentReportsAndTermsPage = clickOn(paymentReportsAndTermsPage, METHOD_TO_CALL_REFRESH_PULL_DOWN_MENUS);        
        setFieldValue(paymentReportsAndTermsPage, FREQUENCY_BASE_CODE_5, "4");
        setFieldValue(paymentReportsAndTermsPage, OSP_DISTRIBUTION_CODE_5, "1");
        setFieldValue(paymentReportsAndTermsPage, DUE_DATE_5, "03/05/2009");
        
        final HtmlForm form1 = (HtmlForm) paymentReportsAndTermsPage.getForms().get(0);        
        String completeButtonName1=getImageTagName(paymentReportsAndTermsPage, METHOD_TO_CALL_ADD_AWARD_REPORT_TERM_FOR_PAYMENT_AND_INVOICES);        
        final HtmlImageInput button1 = (HtmlImageInput) form1.getInputByName(completeButtonName1);
        HtmlPage awardPaymentReportsAndTermsPageAfterAdd = (HtmlPage) button1.click();
        
        HtmlPage awardPaymentReportsAndTermsPageAfterSave = clickOn(awardPaymentReportsAndTermsPageAfterAdd, "methodToCall.save");
        assertDoesNotContain(awardPaymentReportsAndTermsPageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);        
        assertDoesNotContain(awardPaymentReportsAndTermsPageAfterSave, ERRORS_FOUND_ON_PAGE);        
        assertContains(awardPaymentReportsAndTermsPageAfterSave,SAVE_SUCCESS_MESSAGE);

        assertContains(awardPaymentReportsAndTermsPageAfterSave,"Payment & Invoice Requirements (1) ");
        
        HtmlPage awardPaymentReportsAndTermsPageAfterDelete = clickOn(awardPaymentReportsAndTermsPageAfterSave,"methodToCall.deleteAwardReportTerm.line0.anchor1");
        HtmlPage awardPaymentReportsAndTermsPageAfterOneMoreSave = clickOn(awardPaymentReportsAndTermsPageAfterDelete, "methodToCall.save");
        assertDoesNotContain(awardPaymentReportsAndTermsPageAfterOneMoreSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);        
        assertDoesNotContain(awardPaymentReportsAndTermsPageAfterOneMoreSave, ERRORS_FOUND_ON_PAGE);        
        assertContains(awardPaymentReportsAndTermsPageAfterOneMoreSave,SAVE_SUCCESS_MESSAGE);
        assertContains(awardPaymentReportsAndTermsPageAfterOneMoreSave,"Payment & Invoice Requirements (0) ");
        
    }
    
    @Test
    public void testPaymentAndInvoicesRequirementsAddRequiredness() throws Exception{
        final HtmlForm form1 = (HtmlForm) paymentReportsAndTermsPage.getForms().get(0);        
        String completeButtonName1=getImageTagName(paymentReportsAndTermsPage, METHOD_TO_CALL_ADD_AWARD_REPORT_TERM_FOR_PAYMENT_AND_INVOICES);        
        final HtmlImageInput button1 = (HtmlImageInput) form1.getInputByName(completeButtonName1);
        HtmlPage awardPaymentReportsAndTermsPageAfterAdd = (HtmlPage) button1.click();
        assertContains(awardPaymentReportsAndTermsPageAfterAdd,REPORT_CODE_MANDATORY_ERROR_MESSAGE);
        assertContains(awardPaymentReportsAndTermsPageAfterAdd,FREQUENCY_CODE_MANDATORY_ERROR_MESSAGE);
        assertContains(awardPaymentReportsAndTermsPageAfterAdd,FREQUENCY_BASE_CODE_MANDATORY_ERROR_MESSAGE);
        assertContains(awardPaymentReportsAndTermsPageAfterAdd,OSP_DISTRIBUTION_CODE_MANDATORY_ERROR_MESSAGE);
        assertContains(awardPaymentReportsAndTermsPageAfterAdd,DUE_DATE_MANDATORY_ERROR_MESSAGE);        
    }
    
    @Test
    public void testAwardPaymentScheduleSimpleAddDelete() throws Exception{
        setFieldValue(paymentReportsAndTermsPage, NEW_INVOICE_NUMBER, "5");
        setFieldValue(paymentReportsAndTermsPage, NEW_DUE_DATE, "03/06/2009");
        setFieldValue(paymentReportsAndTermsPage, NEW_SUBMITTED_BY, "quickstar");
        setFieldValue(paymentReportsAndTermsPage, NEW_STATUS, "added");
        setFieldValue(paymentReportsAndTermsPage, NEW_SUBMIT_DATE, "03/06/2009");
        setFieldValue(paymentReportsAndTermsPage, NEW_STATUS_DESCRIPTION, "added");
        setFieldValue(paymentReportsAndTermsPage, NEW_AMOUNT, "500.00");
        
        final HtmlForm form1 = (HtmlForm) paymentReportsAndTermsPage.getForms().get(0);        
        String completeButtonName1=getImageTagName(paymentReportsAndTermsPage, METHOD_TO_CALL_ADD_PAYMENT_SCHEDULE);        
        final HtmlImageInput button1 = (HtmlImageInput) form1.getInputByName(completeButtonName1);
        HtmlPage awardPaymentReportsAndTermsPageAfterAdd = (HtmlPage) button1.click();
        
        HtmlPage awardPaymentReportsAndTermsPageAfterSave = clickOn(awardPaymentReportsAndTermsPageAfterAdd, "methodToCall.save");

        assertDoesNotContain(awardPaymentReportsAndTermsPageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);        
        assertDoesNotContain(awardPaymentReportsAndTermsPageAfterSave, ERRORS_FOUND_ON_PAGE);        
        assertContains(awardPaymentReportsAndTermsPageAfterSave,SAVE_SUCCESS_MESSAGE);        
    }
        
}
