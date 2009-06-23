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

/**
 * 
 * This is the integration test for Award Closeout panel. 
 */
public class AwardCloseoutWebTest extends AwardPaymentsAndTermsWebTest {
        
    public static final String ADD_AWARD_CLOSEOUT = "methodToCall.addAwardCloseout.anchorAwardCloseout";
    public static final String CLOSEOUT_REPORT_NAME_FIELD = "awardCloseoutBean.newAwardCloseout.closeoutReportName";
    public static final String DUE_DATE_FIELD = "awardCloseoutBean.newAwardCloseout.dueDate";
    public static final String FINAL_SUBMISSION_DATE_FIELD = "awardCloseoutBean.newAwardCloseout.finalSubmissionDate";
    public static final String CLOSEOUT_DATE_FIELD = "document.awardList[0].closeoutDate";
    public static final String ARCHIVE_LOCATION_FIELD = "document.awardList[0].archiveLocation";
    
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
    public void testAwardCloseoutStaticReportsArePersistedUponAwardCreation(){
        assertContains(paymentReportsAndTermsPage, "1 Financial Report");
        assertContains(paymentReportsAndTermsPage, "2 Technical");
        assertContains(paymentReportsAndTermsPage, "3 Patent");
        assertContains(paymentReportsAndTermsPage, "4 Property");
    }
    
    @Test
    public void testAwardCloseoutValidations() throws Exception{
        paymentReportsAndTermsPage = clickOn(paymentReportsAndTermsPage, ADD_AWARD_CLOSEOUT);
        assertContains(paymentReportsAndTermsPage,ERRORS_FOUND_ON_PAGE);
        assertContains(paymentReportsAndTermsPage,"Final Report (Final Report) is a required field.");
        
        setFieldValue(paymentReportsAndTermsPage, CLOSEOUT_REPORT_NAME_FIELD, "Test Report 1");
        setFieldValue(paymentReportsAndTermsPage, DUE_DATE_FIELD, "5");
        setFieldValue(paymentReportsAndTermsPage, FINAL_SUBMISSION_DATE_FIELD, "5");
        
        paymentReportsAndTermsPage = clickOn(paymentReportsAndTermsPage, ADD_AWARD_CLOSEOUT);
        assertContains(paymentReportsAndTermsPage,ERRORS_FOUND_ON_PAGE);
        assertContains(paymentReportsAndTermsPage,"5 is not a valid date. Valid date format is xx/xx/xxxx.");        
    }
    
    @Test
    public void testNonCloseoutNonReportFieldsValidations() throws Exception{
        setFieldValue(paymentReportsAndTermsPage,CLOSEOUT_DATE_FIELD,"32423as");        
        paymentReportsAndTermsPage = clickOn(paymentReportsAndTermsPage, SAVE_PAGE);                
        assertContains(paymentReportsAndTermsPage, ERRORS_FOUND_ON_PAGE);        
        assertContains(paymentReportsAndTermsPage,"32423as is not a valid date. Valid date format is xx/xx/xxxx.");                
    }
    
    
    @Test
    public void testNonCloseoutNonReportFieldsPersistence() throws Exception{
        setFieldValue(paymentReportsAndTermsPage,ARCHIVE_LOCATION_FIELD,"Test Archive Location");
        setFieldValue(paymentReportsAndTermsPage,CLOSEOUT_DATE_FIELD,"06/15/2009");
        
        paymentReportsAndTermsPage = clickOn(paymentReportsAndTermsPage, SAVE_PAGE);
        assertDoesNotContain(paymentReportsAndTermsPage, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);        
        assertDoesNotContain(paymentReportsAndTermsPage, ERRORS_FOUND_ON_PAGE);        
        assertContains(paymentReportsAndTermsPage,SAVE_SUCCESS_MESSAGE);
        paymentReportsAndTermsPage = clickOn(paymentReportsAndTermsPage, RELOAD_PAGE);
        assertContains(paymentReportsAndTermsPage,"Test Archive Location");
        assertContains(paymentReportsAndTermsPage,"06/15/2009");        
    }
    
    @Test    
    public void testAwardCloseoutAddDeleteUserDefinedReport() throws Exception{        
        setFieldValue(paymentReportsAndTermsPage, "awardCloseoutBean.newAwardCloseout.closeoutReportName", "Test Report 1");
        setFieldValue(paymentReportsAndTermsPage, "awardCloseoutBean.newAwardCloseout.dueDate", "5/15/2009");
        setFieldValue(paymentReportsAndTermsPage, "awardCloseoutBean.newAwardCloseout.finalSubmissionDate", "5/15/2009");
        paymentReportsAndTermsPage = clickOn(paymentReportsAndTermsPage, "methodToCall.addAwardCloseout.anchorAwardCloseout");
        
        paymentReportsAndTermsPage = clickOn(paymentReportsAndTermsPage, "methodToCall.save");        
        assertDoesNotContain(paymentReportsAndTermsPage, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);        
        assertDoesNotContain(paymentReportsAndTermsPage, ERRORS_FOUND_ON_PAGE);        
        assertContains(paymentReportsAndTermsPage,SAVE_SUCCESS_MESSAGE);
                
        paymentReportsAndTermsPage = clickOn(paymentReportsAndTermsPage, "methodToCall.deleteAwardCloseout.line4.anchor23");
        paymentReportsAndTermsPage = clickOn(paymentReportsAndTermsPage, "methodToCall.save");
        assertDoesNotContain(paymentReportsAndTermsPage, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);        
        assertDoesNotContain(paymentReportsAndTermsPage, ERRORS_FOUND_ON_PAGE);        
        assertContains(paymentReportsAndTermsPage,SAVE_SUCCESS_MESSAGE);
    }
    
}
