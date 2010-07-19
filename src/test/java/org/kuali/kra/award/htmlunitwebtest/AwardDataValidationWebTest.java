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

import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * This is the integration test for Data Validation tab on Award Actions page.
 */
public class AwardDataValidationWebTest extends AwardActionsWebTest {
    
    private static final String METHOD_ACTIVATE = "methodToCall.activate";
    private static final String METHOD_DEACTIVATE = "methodToCall.deactivate";
    private static final String TERMS_ERROR_STRING = "There must be at least one Equipment Approval Terms defined.";
    private static final String REPORT_ERROR_STRING = "There are no reports defined";
    
    /**
     * The set up method calls the parent super method and gets the 
     * award Actions page after that.
     * @see org.kuali.kra.award.htmlunitwebtest.AwardWebTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * This method calls parent tear down method and than sets awardAction to null
     * @see org.kuali.kra.award.htmlunitwebtest.AwardWebTestBase#tearDown()
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    /**
     * 
     *This method tests turning on Data Validation will add audit errors when none of the audit values are set on the award.
     * @throws Exception
     */
    @Test
    public void testTurnOnDataValidation() throws Exception{
        HtmlPage awardActionsPageAfterActivate = clickOn(awardActionsPage, METHOD_ACTIVATE);
        System.out.println(awardActionsPageAfterActivate.asXml());
        assertTrue(awardActionsPageAfterActivate.asXml().contains(TERMS_ERROR_STRING));
        assertTrue(awardActionsPageAfterActivate.asXml().contains(REPORT_ERROR_STRING));
    }
    
    /**
     * 
     *This method tests turning off Validation after an Activation
     * @throws Exception
     */
    
    @Test
    public void testTurnOffDataValidationAfterActivate() throws Exception{
        HtmlPage awardActionsPageAfterActivate = clickOn(awardActionsPage, METHOD_ACTIVATE);
        assertTrue(awardActionsPageAfterActivate.asXml().contains(TERMS_ERROR_STRING));
        assertTrue(awardActionsPageAfterActivate.asXml().contains(REPORT_ERROR_STRING));
        HtmlPage awardActionsPageAfterDeactivate = clickOn(awardActionsPageAfterActivate, METHOD_DEACTIVATE);
        assertFalse(awardActionsPageAfterDeactivate.asXml().contains(TERMS_ERROR_STRING));
        assertFalse(awardActionsPageAfterDeactivate.asXml().contains(REPORT_ERROR_STRING));
    }
    
    
}
