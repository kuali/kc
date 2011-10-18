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
package org.kuali.kra.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.web.MaintenanceDocumentSeleniumHelper;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

/**
 * Tests that various elements of the page are actually showing up.
 */
public class PortalSeleniumTest extends KcSeleniumTestBase {
    
    private MaintenanceDocumentSeleniumHelper helper;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        helper = MaintenanceDocumentSeleniumHelper.instance(driver);
    }
    
    @After
    public void tearDown() throws Exception {
        helper = null;
        
        super.tearDown();
    }
    
    /**
     * Test that the IFrame exists on the page.
     * @throws Exception
     */
    @Test
    public void testIFrame() throws Exception {
        helper.clickResearcherTab();
        
        helper.click("Document Search");
        
        helper.assertElementExists("iframeportlet");  
    }
    
    /**
     * Test that the Action List and Outbox links show up.
     * @throws Exception
     */
    @Test
    public void testActionList() throws Exception {
        helper.clickResearcherTab();
        
        helper.click("Action List");

        helper.assertPageContains("Action List");
        helper.assertPageContains("Outbox");
    }

}