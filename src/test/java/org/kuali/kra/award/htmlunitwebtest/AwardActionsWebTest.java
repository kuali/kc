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
import org.junit.Ignore;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * This is the integration test for Award Actions Page.
 */
public class AwardActionsWebTest extends AwardWebTestBase {
protected static final String ACTIONS_LINK_NAME = "awardActions";
    
    protected HtmlPage awardActionsPage; 
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        getAwardHomePage().getWebClient().setJavaScriptEnabled(false);
        awardActionsPage = clickOnTab(getAwardHomePage(), ACTIONS_LINK_NAME);
    }
    
    @After
    public void tearDown() throws Exception {
        awardActionsPage = null;
        super.tearDown();
    }
 
    /**
     * @return
     * 
     */
    protected HtmlPage getAwardPaymentReportsAndTermsPage() {
        return awardActionsPage;
    }
    
    /** prevents init errors from not having a test method. */
    @Test @Ignore
    public void foo() {}
}
