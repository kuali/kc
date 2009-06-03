/*
 * Copyright 2006-2008 The Kuali Foundation
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
public class AwardReportsWebTest extends AwardPaymentsAndTermsWebTest {
    
    private static final String METHOD_TO_CALL_REFRESH_PULL_DOWN_MENUS = "methodToCall.refreshPulldownOptions";
    private static final String METHOD_TO_CALL_ADD_AWARD_REPORT_TERM_WHERE_CLASS_IS_FISCAL = "methodToCall.addAwardReportTerm.reportClass1.reportClassIndex0";
    private static final String METHOD_TO_CALL_ADD_AWARD_REPORT_TERM_WHERE_CLASS_IS_PROPERTY= "methodToCall.addAwardReportTerm.reportClass2.reportClassIndex3";    
    
    private static final String NEW_AWARD_REPORT_TERM_0 = "awardReportsBean.newAwardReportTerms[0]";
    private static final String REPORT_CODE_0 = NEW_AWARD_REPORT_TERM_0 + ".reportCode";
    private static final String FREQUENCY_CODE_0 = NEW_AWARD_REPORT_TERM_0 + ".frequencyCode";
    private static final String FREQUENCY_BASE_CODE_0 = NEW_AWARD_REPORT_TERM_0 + ".frequencyBaseCode";
    private static final String OSP_DISTRIBUTION_CODE_0 = NEW_AWARD_REPORT_TERM_0 + ".ospDistributionCode";
    private static final String DUE_DATE_0 = NEW_AWARD_REPORT_TERM_0 + ".dueDate";
    private static final String NEW_AWARD_REPORT_TERM_3 = "awardReportsBean.newAwardReportTerms[3]";
    private static final String REPORT_CODE_3 = NEW_AWARD_REPORT_TERM_3 + ".reportCode";
    private static final String FREQUENCY_CODE_3 = NEW_AWARD_REPORT_TERM_3 + ".frequencyCode";
    private static final String FREQUENCY_BASE_CODE_3 = NEW_AWARD_REPORT_TERM_3 + ".frequencyBaseCode";
    private static final String OSP_DISTRIBUTION_CODE_3 = NEW_AWARD_REPORT_TERM_3 + ".ospDistributionCode";
    private static final String DUE_DATE_3 = NEW_AWARD_REPORT_TERM_3 + ".dueDate";
    private static final String IS_REQUIRED_TEXT = " is a required field";
    private static final String REPORT_CODE_MANDATORY_ERROR_MESSAGE = "Type (Type)" + IS_REQUIRED_TEXT;
    private static final String FREQUENCY_CODE_MANDATORY_ERROR_MESSAGE = "Frequency (Frequency)" + IS_REQUIRED_TEXT;
    private static final String FREQUENCY_BASE_CODE_MANDATORY_ERROR_MESSAGE = "Frequency Base (Frequency Base)" + IS_REQUIRED_TEXT;
    private static final String OSP_DISTRIBUTION_CODE_MANDATORY_ERROR_MESSAGE = "OSP File Copy (OSP File Copy )" + IS_REQUIRED_TEXT;
    private static final String DUE_DATE_MANDATORY_ERROR_MESSAGE = "Due Date (Due Date)" + IS_REQUIRED_TEXT;    
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
    
    /**
     * 
     * This method tests the adding and deleting award report entries.
     * and saving them.  
     * @throws Exception
     */
    @Test
    public void testAwardReportsAddAndDelete() throws Exception{
        setFieldValue(paymentReportsAndTermsPage, REPORT_CODE_0, "5");
        paymentReportsAndTermsPage = clickOn(paymentReportsAndTermsPage, METHOD_TO_CALL_REFRESH_PULL_DOWN_MENUS);        
        setFieldValue(paymentReportsAndTermsPage, FREQUENCY_CODE_0, "14");        
        paymentReportsAndTermsPage = clickOn(paymentReportsAndTermsPage, METHOD_TO_CALL_REFRESH_PULL_DOWN_MENUS);        
        setFieldValue(paymentReportsAndTermsPage, FREQUENCY_BASE_CODE_0, "2");
        setFieldValue(paymentReportsAndTermsPage, OSP_DISTRIBUTION_CODE_0, "1");
        setFieldValue(paymentReportsAndTermsPage, DUE_DATE_0, "06/30/2008");
        
        final HtmlForm form1 = (HtmlForm) paymentReportsAndTermsPage.getForms().get(0);        
        String completeButtonName1=getImageTagName(paymentReportsAndTermsPage, METHOD_TO_CALL_ADD_AWARD_REPORT_TERM_WHERE_CLASS_IS_FISCAL);        
        final HtmlImageInput button1 = (HtmlImageInput) form1.getInputByName(completeButtonName1);
        HtmlPage awardPaymentReportsAndTermsPageAfterAdd = (HtmlPage) button1.click();
        
        HtmlPage awardPaymentReportsAndTermsPageAfterSave = clickOn(awardPaymentReportsAndTermsPageAfterAdd, "methodToCall.save");
        assertDoesNotContain(awardPaymentReportsAndTermsPageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);        
        assertDoesNotContain(awardPaymentReportsAndTermsPageAfterSave, ERRORS_FOUND_ON_PAGE);        
        assertContains(awardPaymentReportsAndTermsPageAfterSave,SAVE_SUCCESS_MESSAGE);
        System.out.println("PAGEASTEXT: " + awardPaymentReportsAndTermsPageAfterSave.asText());
        assertContains(awardPaymentReportsAndTermsPageAfterSave,"Fiscal (1) ");
        
        HtmlPage awardPaymentReportsAndTermsPageAfterDelete = clickOn(awardPaymentReportsAndTermsPageAfterSave,"methodToCall.deleteAwardReportTerm.line0.anchor4");
        HtmlPage awardPaymentReportsAndTermsPageAfterOneMoreSave = clickOn(awardPaymentReportsAndTermsPageAfterDelete, "methodToCall.save");
        assertDoesNotContain(awardPaymentReportsAndTermsPageAfterOneMoreSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);        
        assertDoesNotContain(awardPaymentReportsAndTermsPageAfterOneMoreSave, ERRORS_FOUND_ON_PAGE);        
        assertContains(awardPaymentReportsAndTermsPageAfterOneMoreSave,SAVE_SUCCESS_MESSAGE);
        assertContains(awardPaymentReportsAndTermsPageAfterOneMoreSave,"Fiscal (0) ");
    }
    
    @Test
    public void testAwardReportsAddRequiredness() throws Exception{
        final HtmlForm form1 = (HtmlForm) paymentReportsAndTermsPage.getForms().get(0);        
        String completeButtonName1=getImageTagName(paymentReportsAndTermsPage, METHOD_TO_CALL_ADD_AWARD_REPORT_TERM_WHERE_CLASS_IS_FISCAL);        
        final HtmlImageInput button1 = (HtmlImageInput) form1.getInputByName(completeButtonName1);
        HtmlPage awardPaymentReportsAndTermsPageAfterAdd = (HtmlPage) button1.click();
        assertContains(awardPaymentReportsAndTermsPageAfterAdd,REPORT_CODE_MANDATORY_ERROR_MESSAGE);
        assertContains(awardPaymentReportsAndTermsPageAfterAdd,FREQUENCY_CODE_MANDATORY_ERROR_MESSAGE);
        assertContains(awardPaymentReportsAndTermsPageAfterAdd,FREQUENCY_BASE_CODE_MANDATORY_ERROR_MESSAGE);
        assertContains(awardPaymentReportsAndTermsPageAfterAdd,OSP_DISTRIBUTION_CODE_MANDATORY_ERROR_MESSAGE);
        assertContains(awardPaymentReportsAndTermsPageAfterAdd,DUE_DATE_MANDATORY_ERROR_MESSAGE);        
    }
    
    @Test
    public void testAwardReportsMiscProcurementPurchasingPanelTest() throws Exception{
        setFieldValue(paymentReportsAndTermsPage, SMALL_BUSINESS_SUBCONTRACTING_PLAN, "R");
        setFieldValue(paymentReportsAndTermsPage, PROCUREMENT_PRIORITY_CODE, "DO0076");
        HtmlPage awardPaymentReportsAndTermsPageAfterSave = clickOn(paymentReportsAndTermsPage, "methodToCall.save");
        assertDoesNotContain(awardPaymentReportsAndTermsPageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);        
        assertDoesNotContain(awardPaymentReportsAndTermsPageAfterSave, ERRORS_FOUND_ON_PAGE);        
        assertContains(awardPaymentReportsAndTermsPageAfterSave,SAVE_SUCCESS_MESSAGE);
        assertContains(awardPaymentReportsAndTermsPageAfterSave,"Required");
        assertContains(awardPaymentReportsAndTermsPageAfterSave,"DO0076");        
    }
    
    @Test
    public void testAwardReportsAddAndDeleteForDifferentReportClass() throws Exception{
        setFieldValue(paymentReportsAndTermsPage, REPORT_CODE_3, "39");
        paymentReportsAndTermsPage = clickOn(paymentReportsAndTermsPage, METHOD_TO_CALL_REFRESH_PULL_DOWN_MENUS);        
        setFieldValue(paymentReportsAndTermsPage, FREQUENCY_CODE_3, "14");        
        paymentReportsAndTermsPage = clickOn(paymentReportsAndTermsPage, METHOD_TO_CALL_REFRESH_PULL_DOWN_MENUS);        
        setFieldValue(paymentReportsAndTermsPage, FREQUENCY_BASE_CODE_3, "2");
        setFieldValue(paymentReportsAndTermsPage, OSP_DISTRIBUTION_CODE_3, "3");
        setFieldValue(paymentReportsAndTermsPage, DUE_DATE_3, "06/30/2008");
        
        final HtmlForm form1 = (HtmlForm) paymentReportsAndTermsPage.getForms().get(0);        
        String completeButtonName1=getImageTagName(paymentReportsAndTermsPage, METHOD_TO_CALL_ADD_AWARD_REPORT_TERM_WHERE_CLASS_IS_PROPERTY);        
        final HtmlImageInput button1 = (HtmlImageInput) form1.getInputByName(completeButtonName1);
        HtmlPage awardPaymentReportsAndTermsPageAfterAdd = (HtmlPage) button1.click();
        
        HtmlPage awardPaymentReportsAndTermsPageAfterSave = clickOn(awardPaymentReportsAndTermsPageAfterAdd, "methodToCall.save");
        assertDoesNotContain(awardPaymentReportsAndTermsPageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);        
        assertDoesNotContain(awardPaymentReportsAndTermsPageAfterSave, ERRORS_FOUND_ON_PAGE);        
        assertContains(awardPaymentReportsAndTermsPageAfterSave,SAVE_SUCCESS_MESSAGE);
        assertContains(awardPaymentReportsAndTermsPageAfterSave,"Property (1) ");
               
        HtmlPage awardPaymentReportsAndTermsPageAfterDelete = clickOn(awardPaymentReportsAndTermsPageAfterSave,"methodToCall.deleteAwardReportTerm.line0.anchor7");
        HtmlPage awardPaymentReportsAndTermsPageAfterOneMoreSave = clickOn(awardPaymentReportsAndTermsPageAfterDelete, "methodToCall.save");
        assertDoesNotContain(awardPaymentReportsAndTermsPageAfterOneMoreSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);        
        assertDoesNotContain(awardPaymentReportsAndTermsPageAfterOneMoreSave, ERRORS_FOUND_ON_PAGE);        
        assertContains(awardPaymentReportsAndTermsPageAfterOneMoreSave,SAVE_SUCCESS_MESSAGE);
        assertContains(awardPaymentReportsAndTermsPageAfterOneMoreSave,"Property (0) ");        
    }
    
    //@Test
    public void testAwardReportRecipients() throws Exception{
        setFieldValue(paymentReportsAndTermsPage, REPORT_CODE_0, "5");
        paymentReportsAndTermsPage = clickOn(paymentReportsAndTermsPage, METHOD_TO_CALL_REFRESH_PULL_DOWN_MENUS);        
        setFieldValue(paymentReportsAndTermsPage, FREQUENCY_CODE_0, "14");        
        paymentReportsAndTermsPage = clickOn(paymentReportsAndTermsPage, METHOD_TO_CALL_REFRESH_PULL_DOWN_MENUS);        
        setFieldValue(paymentReportsAndTermsPage, FREQUENCY_BASE_CODE_0, "2");
        setFieldValue(paymentReportsAndTermsPage, OSP_DISTRIBUTION_CODE_0, "1");
        setFieldValue(paymentReportsAndTermsPage, DUE_DATE_0, "06/30/2008");
        
        final HtmlForm form1 = (HtmlForm) paymentReportsAndTermsPage.getForms().get(0);        
        String completeButtonName1=getImageTagName(paymentReportsAndTermsPage, METHOD_TO_CALL_ADD_AWARD_REPORT_TERM_WHERE_CLASS_IS_FISCAL);        
        final HtmlImageInput button1 = (HtmlImageInput) form1.getInputByName(completeButtonName1);
        HtmlPage awardPaymentReportsAndTermsPageAfterAdd = (HtmlPage) button1.click();
        
        HtmlPage awardPaymentReportsAndTermsPageAfterSave = clickOn(awardPaymentReportsAndTermsPageAfterAdd, "methodToCall.save");
        assertDoesNotContain(awardPaymentReportsAndTermsPageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);        
        assertDoesNotContain(awardPaymentReportsAndTermsPageAfterSave, ERRORS_FOUND_ON_PAGE);        
        assertContains(awardPaymentReportsAndTermsPageAfterSave,SAVE_SUCCESS_MESSAGE);
        
        HtmlPage page = lookup(awardPaymentReportsAndTermsPageAfterSave,"newAwardReportTermRecipients");
        
        assertContains(page, "Sunplus Technology Co., Ltd.");
        awardPaymentReportsAndTermsPageAfterSave = clickOn(page, "methodToCall.addRecipient.awardReportTerm1");
        
        System.out.println(awardPaymentReportsAndTermsPageAfterSave.asText());
    }
    
}
