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
 * Test authorizations for a protocol.
 */
public class ProtocolAuthorizationSeleniumTest extends KcSeleniumTestBase {
    
    private static final String CREATE_PROTOCOL_LINK_NAME = "Create IRB Protocol";
    
    private static final String ERROR_ID = "font[color = 'red']";
    
    private static final String NO_PERMISSION_USERNAME = "woods";
    private static final String VIEWER_PERMISSION_USERNAME = "irbviewer";
    
    private static final String ERROR_NOT_AUTHORIZED_OPEN = "not authorized to open document";
    private static final String ERROR_NOT_AUTHORIZED_VIEW = "not authorized to view document";
    private static final String ERROR_NOT_AUTHORIZED_INITIATE = "not authorized to initiate document";
    
    private static final String SAVE_BUTTON = "methodToCall.save";
    
    private ProtocolSeleniumHelper helper;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        helper = ProtocolSeleniumHelper.instance(driver);
        helper.loginBackdoor();
    }
    
    @After
    public void tearDown() throws Exception {
        helper.loginBackdoor();
        helper = null;
        
        super.tearDown();
    }
    
    /**
     * Tests that user who doesn't have read-only access to a protocol will not be able to view it.
     * 
     * @throws Exception
     */
    @Test
    public void testBlankAuthorization() throws Exception {
        helper.createProtocol();
        
        String documentNumber = helper.getDocumentNumber();
        helper.closeDocument();
        helper.loginBackdoor(NO_PERMISSION_USERNAME);
        helper.docSearch(documentNumber);
        
        helper.assertSelectorContains(ERROR_ID, ERROR_NOT_AUTHORIZED_OPEN);
    }
    
    /**
     * Tests that a user who has read-only access to a protocol can view it but not modify it.
     * 
     * @throws Exception
     */
    @Test
    public void testViewAuthorization() throws Exception {
        helper.createProtocol();
        
        String documentNumber = helper.getDocumentNumber();
        helper.closeDocument();
        helper.loginBackdoor(VIEWER_PERMISSION_USERNAME);
        helper.docSearch(documentNumber);
        
        helper.assertSelectorDoesNotContain(ERROR_ID, ERROR_NOT_AUTHORIZED_VIEW);
        
        helper.assertElementDoesNotExist(SAVE_BUTTON);
    }
    
    /**
     * Tests that the who has full access to a committee can view and modify a protocol.
     * 
     * @throws Exception
     */
    @Test
    public void testCreateAuthorizationNoError() throws Exception {
        helper.createProtocol();
    }
    
    /**
     * Tests that a user who doesn't have create access to a protocol will not be able to create it.
     * 
     * @throws Exception
     */
    @Test
    public void testCreateAuthorizationError() throws Exception {
        helper.loginBackdoor(NO_PERMISSION_USERNAME);
        
        helper.clickResearcherTab();
        
        helper.click(CREATE_PROTOCOL_LINK_NAME);
        
        helper.assertSelectorContains(ERROR_ID, ERROR_NOT_AUTHORIZED_INITIATE);        
    }
    
    /**
     * Tests that a user who full access to a protocol can both create and modify it.
     * 
     * @throws Exception
     */
    @Test
    public void testModifyAuthorization() throws Exception {
        helper.createProtocol();
        
        helper.assertNoPageErrors();
        
        helper.saveDocument();
        
        helper.assertNoPageErrors();
    }

}