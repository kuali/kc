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


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * This class loads the Award SpecialReview tab page
 */
public class AwardPaymentsAndTermsWebTest extends AwardWebTestBase {
    protected static final String PAYMENT_REPORTS_AND_TERMS_LINK_NAME = "paymentReportsAndTerms";
    private static final Log LOG = LogFactory.getLog(AwardPaymentsAndTermsWebTest.class);
    
    protected HtmlPage paymentReportsAndTermsPage;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        paymentReportsAndTermsPage = clickOnTab(getAwardHomePage(), PAYMENT_REPORTS_AND_TERMS_LINK_NAME);        
    }
    
    @After
    public void tearDown() throws Exception {
        paymentReportsAndTermsPage = null;
        super.tearDown();
    }
 
    /**
     * @return
     * 
     */
    protected HtmlPage getAwardPaymentReportsAndTermsPage() {
        return paymentReportsAndTermsPage;
    }
    
    protected void dumpPage() {
        if(LOG.isDebugEnabled()) {
            LOG.debug(paymentReportsAndTermsPage);
        }
    }
    
    /** prevents init errors from not having a test method. */
    @Test @Ignore
    public void foo() {}
}
