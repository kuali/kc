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

import org.kuali.core.util.KualiDecimal;

import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlImageInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * 
 * This is the integration test for Award Reports page. 
 */
public class AwardReportsWebTest extends AwardWebTestBase{
    
    HtmlPage awardPaymentReportsAndTermsPage;
    
    /**
     * The set up method calls the parent super method and gets the 
     * award Payment, Reports and Terms page after that.
     * @see org.kuali.kra.award.htmlunitwebtest.AwardWebTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        awardPaymentReportsAndTermsPage = getPaymentReportsAndTermsPage();
    }

    /**
     * This method calls parent tear down method and than sets awardTimeAndMoneyPage to null
     * @see org.kuali.kra.award.htmlunitwebtest.AwardWebTestBase#tearDown()
     */
    @After
    public void tearDown() throws Exception {
        awardPaymentReportsAndTermsPage = null;
        super.tearDown();
    }
    
    /**
     * 
     * This method tests the adding of 2 F & A Rates (on and off campus) 
     * and saving them.  
     * @throws Exception
     */
    @Test
    public void testAwardReportsSimpleAdd() throws Exception{
        setFieldValue(awardPaymentReportsAndTermsPage, "newAwardReportTerm[0].reportCode", "5");
        awardPaymentReportsAndTermsPage = clickOn(awardPaymentReportsAndTermsPage, "methodToCall.refreshPulldownOptions");        
        setFieldValue(awardPaymentReportsAndTermsPage, "newAwardReportTerm[0].frequencyCode", "14");        
        awardPaymentReportsAndTermsPage = clickOn(awardPaymentReportsAndTermsPage, "methodToCall.refreshPulldownOptions");        
        setFieldValue(awardPaymentReportsAndTermsPage, "newAwardReportTerm[0].frequencyBaseCode", "2");
        setFieldValue(awardPaymentReportsAndTermsPage, "newAwardReportTerm[0].ospDistributionCode", "1");
        setFieldValue(awardPaymentReportsAndTermsPage, "newAwardReportTerm[0].dueDate", "06/30/2008");
        
        final HtmlForm form1 = (HtmlForm) awardPaymentReportsAndTermsPage.getForms().get(0);        
        String completeButtonName1=getImageTagName(awardPaymentReportsAndTermsPage, "methodToCall.addAwardReportTerm.reportClass1.reportClassIndex0");        
        final HtmlImageInput button1 = (HtmlImageInput) form1.getInputByName(completeButtonName1);
        HtmlPage awardPaymentReportsAndTermsPageAfterAdd = (HtmlPage) button1.click();
        
        HtmlPage awardPaymentReportsAndTermsPageAfterSave = clickOn(awardPaymentReportsAndTermsPageAfterAdd, "methodToCall.save");
        assertDoesNotContain(awardPaymentReportsAndTermsPageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);        
        assertDoesNotContain(awardPaymentReportsAndTermsPageAfterSave, ERRORS_FOUND_ON_PAGE);        
        assertContains(awardPaymentReportsAndTermsPageAfterSave,SAVE_SUCCESS_MESSAGE);
        assertContains(awardPaymentReportsAndTermsPageAfterSave,"Fiscal (1) ");
    }

}
