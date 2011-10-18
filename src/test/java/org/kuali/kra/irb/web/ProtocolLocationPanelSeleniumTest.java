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
 * Tests the Organizations (Location) tab in the Protocol page of a Protocol.
 */
public class ProtocolLocationPanelSeleniumTest extends KcSeleniumTestBase {
    
    private static final String TAB_ID = "Organizations";
    private static final String TABLE_ID = "location-table";
    private static final String ERROR_ID = "tab-Organizations-div";
    
    private static final String HELPER_PREFIX = "protocolHelper.newProtocolLocation.";
    
    private static final String ORGANIZATION_ID_ID = "organizationId";
    private static final String PROTOCOL_ORGANIZATION_TYPE_CODE_ID = "protocolOrganizationTypeCode";
    private static final String HELPER_ORGANIZATION_ID =  HELPER_PREFIX + ORGANIZATION_ID_ID;
    private static final String HELPER_PROTOCOL_ORGANIZATION_TYPE_CODE_ID = HELPER_PREFIX + PROTOCOL_ORGANIZATION_TYPE_CODE_ID;
    
    private static final String ORGANIZATION_ID =  "000001";
    private static final String PROTOCOL_ORGANIZATION_TYPE_CODE = "Performing Organization";
    private static final String CONTACT = "Last Name, First Name: Address Line 1 Address Line 2 Address Line 3, Kuali Coeus,   MA   53421";
    private static final String HUMAN_SUB_ASSURANCE = "FWA00004881";
    
    private static final String ERROR_ORGANIZATION_REQUIRED = "At least one organization must be entered.";

    private static final String ADD_PROTOCOL_LOCATION_BUTTON = "methodToCall.addProtocolLocation";
    private static final String DELETE_PROTOCOL_LOCATION_BUTTON = "methodToCall.deleteProtocolLocation.line%d";
    private static final String CLEAR_PROTOCOL_LOCATION_ADDRESS_BUTTON = "methodToCall.clearProtocolLocationAddress.line%d";
    
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
     * Test the initial values.
     * 
     * @throws Exception
     */
    @Test
    public void testDefaultLocationPanel() throws Exception {
        helper.createProtocol();
        
        helper.openTab(TAB_ID);
        
        helper.assertTableCellValueContains(TABLE_ID, 2, 0, ORGANIZATION_ID);
        helper.assertTableCellValueContains(TABLE_ID, 2, 2, CONTACT);
        helper.assertTableCellValueContains(TABLE_ID, 2, 3, HUMAN_SUB_ASSURANCE);
    }
    
    /**
     * Test deletion and addition of the initial values.
     * 
     * @throws Exception
     */
    @Test
    public void testDeleteAndAddDefaultLocation() throws Exception {
        helper.createProtocol();
        
        helper.openTab(TAB_ID);
        
        helper.click(String.format(DELETE_PROTOCOL_LOCATION_BUTTON, 0));
        
        helper.set(HELPER_ORGANIZATION_ID, ORGANIZATION_ID);
        helper.set(HELPER_PROTOCOL_ORGANIZATION_TYPE_CODE_ID, PROTOCOL_ORGANIZATION_TYPE_CODE);
        
        helper.click(ADD_PROTOCOL_LOCATION_BUTTON);
        
        helper.assertTableCellValueContains(TABLE_ID, 2, 0, ORGANIZATION_ID);
        helper.assertTableCellValueContains(TABLE_ID, 2, 2, CONTACT);
        helper.assertTableCellValueContains(TABLE_ID, 2, 3, HUMAN_SUB_ASSURANCE);
    }

    /**
     * Test removing the default location and assert that it throws an error.
     * 
     * @throws Exception
     */
    @Test
    public void testDeleteLocation() throws Exception {
        helper.createProtocol();
        
        helper.openTab(TAB_ID);
        
        helper.click(String.format(DELETE_PROTOCOL_LOCATION_BUTTON, 0));
        
        helper.saveDocument();
        helper.assertPageErrors();
        
        helper.assertError(ERROR_ID, ERROR_ORGANIZATION_REQUIRED);                         
    }
    
    /**
     * Test clearing an address.
     * 
     * @throws Exception
     */
    @Test
    public void testClearAddress() throws Exception {
        helper.createProtocol();
        
        helper.openTab(TAB_ID);
        
        helper.assertTableCellValueContains(TABLE_ID, 2, 2, CONTACT);
        
        helper.click(String.format(CLEAR_PROTOCOL_LOCATION_ADDRESS_BUTTON, 0));
        
        helper.assertTableCellValueDoesNotContain(TABLE_ID, 2, 2, CONTACT);
    }

}