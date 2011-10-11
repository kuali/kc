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
 * Tests the Data Validation tab in the Actions page of an Award.
 */
public class AwardDataValidationPanelSeleniumTest extends KcSeleniumTestBase {
    
    private static final String DATA_VALIDATION_TAB_ID = "Data Validation";
    private static final String TERMS_VALIDATION_ERRORS_TAB_ID = "Terms Validation Errors";
    private static final String REPORTS_VALIDATION_ERRORS_TAB_ID = "Reports Validation Errors";
    
    private static final String ERROR_TERMS = "There must be at least one Equipment Approval Terms defined.";
    private static final String ERROR_REPORTS = "There are no reports defined";
    
    private static final String ACTIVATE_BUTTON = "methodToCall.activate";
    private static final String DEACTIVATE_BUTTON = "methodToCall.deactivate";
    
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
     * Test turning validation on and off.
     * 
     * @throws Exception
     */
    @Test
    public void testTurnOnOffDataValidationAfterActivate() throws Exception {
        helper.createAward();
        helper.clickAwardActionsPage();
        
        helper.openTab(DATA_VALIDATION_TAB_ID);
        helper.click(ACTIVATE_BUTTON);
        
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.openTab(TERMS_VALIDATION_ERRORS_TAB_ID);
        helper.openTab(REPORTS_VALIDATION_ERRORS_TAB_ID);
        
        helper.assertPageContains(ERROR_TERMS);
        helper.assertPageContains(ERROR_REPORTS);
        
        helper.click(DEACTIVATE_BUTTON);
        
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertPageDoesNotContain(ERROR_TERMS);
        helper.assertPageDoesNotContain(ERROR_REPORTS);
    }
    
}