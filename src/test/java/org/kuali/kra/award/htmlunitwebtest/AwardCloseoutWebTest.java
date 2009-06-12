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
 * This is the integration test for Award Closeout panel. 
 */
public class AwardCloseoutWebTest extends AwardPaymentsAndTermsWebTest {
    
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
    
    @Test    
    public void testAwardCloseoutAddDeleteUserDefinedReport() throws Exception{
        setFieldValue(paymentReportsAndTermsPage, "awardCloseoutBean.newAwardCloseout.closeoutReportName", "Test Report 1");
        setFieldValue(paymentReportsAndTermsPage, "awardCloseoutBean.newAwardCloseout.dueDate", "5");
        setFieldValue(paymentReportsAndTermsPage, "awardCloseoutBean.newAwardCloseout.finalSubmissionDate", "5");
        
        paymentReportsAndTermsPage = clickOn(paymentReportsAndTermsPage, "methodToCall.addAwardCloseout.anchorAwardCloseout");
        assertContains(paymentReportsAndTermsPage,ERRORS_FOUND_ON_PAGE);
        assertContains(paymentReportsAndTermsPage,"5 is not a valid date. Valid date format is xx/xx/xxxx.");
        
        setFieldValue(paymentReportsAndTermsPage, "awardCloseoutBean.newAwardCloseout.closeoutReportName", "Test Report 1");
        setFieldValue(paymentReportsAndTermsPage, "awardCloseoutBean.newAwardCloseout.dueDate", "5/15/2009");
        setFieldValue(paymentReportsAndTermsPage, "awardCloseoutBean.newAwardCloseout.finalSubmissionDate", "5/15/2009");
        paymentReportsAndTermsPage = clickOn(paymentReportsAndTermsPage, "methodToCall.addAwardCloseout.anchorAwardCloseout");
        System.out.println(paymentReportsAndTermsPage.asText());
        paymentReportsAndTermsPage = clickOn(paymentReportsAndTermsPage, "methodToCall.save");
        System.out.println(paymentReportsAndTermsPage.asText());
        assertDoesNotContain(paymentReportsAndTermsPage, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);        
        assertDoesNotContain(paymentReportsAndTermsPage, ERRORS_FOUND_ON_PAGE);        
        assertContains(paymentReportsAndTermsPage,SAVE_SUCCESS_MESSAGE);
        
        
        paymentReportsAndTermsPage = clickOn(paymentReportsAndTermsPage, "methodToCall.deleteAwardCloseout.line4");
        paymentReportsAndTermsPage = clickOn(paymentReportsAndTermsPage, "methodToCall.save");
        assertDoesNotContain(paymentReportsAndTermsPage, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);        
        assertDoesNotContain(paymentReportsAndTermsPage, ERRORS_FOUND_ON_PAGE);        
        assertContains(paymentReportsAndTermsPage,SAVE_SUCCESS_MESSAGE);
    }
    
}
