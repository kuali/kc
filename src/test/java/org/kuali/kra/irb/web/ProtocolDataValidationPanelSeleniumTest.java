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
package org.kuali.kra.irb.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

/**
 * Tests the Data Validation tab in the Actions page of a Protocol.
 */
public class ProtocolDataValidationPanelSeleniumTest extends KcSeleniumTestBase {
    
    private static final String TAB_ID = "Data Validation";
    
    private static final String ACTIVATE_BUTTON = "methodToCall.activate";
    private static final String DEACTIVATE_BUTTON = "methodToCall.deactivate";
    
    private ProtocolSeleniumHelper helper;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        helper = ProtocolSeleniumHelper.instance(driver);
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
    public void testTurnOnOffDataValidationAfterActivate() throws Exception{
        helper.createProtocol();
        helper.clickProtocolActionsPage();
        
        helper.openTab(TAB_ID);
        helper.click(ACTIVATE_BUTTON);
        
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.click(DEACTIVATE_BUTTON);
        
        helper.saveDocument();
        helper.assertNoPageErrors();
    }

}