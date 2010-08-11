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


import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * 
 * This is the integration test for protocol location / organization panel. 
 */
public class ProtocolLocationWebTest extends ProtocolWebTestBase{
    
    private HtmlPage protocolPage;
    protected static final String NEW_ORGANIZATION_ID =  "protocolHelper.newProtocolLocation.organizationId";
    protected static final String NEW_ORGANIZATION_VALUE =  "000001";
    protected static final String ADDRESS_LINE_1 =  "Address Line 1";

    protected static final String DELETE_LOCATION = "methodToCall.deleteProtocolLocation.line0.anchor";
    protected static final String ADD_LOCATION = "methodToCall.addProtocolLocation.anchor";
    protected static final String CLEAR_ADDRESS = "methodToCall.clearProtocolLocationAddress.line0.anchor22";
    protected static final String ERROR_PROTOCOL_WITHOUT_ORGANIZATION = "At least one organization must be entered.";

    /**
     * protocol location existing field / values
     */
    protected enum ProtocolLocationExistingValues {       
        ORGANIZATION_ID("document.protocolList[0].protocolLocations[0].organizationId", "000001"),
        ROLODEX_ID("document.protocolList[0].protocolLocations[0].rolodexId", "1"),
        HUMANSUB_ASSURANCE("document.protocolList[0].protocolLocations[0].organization.humanSubAssurance", "FWA00004881");
                
        private final String code;   
        private final String value;

        ProtocolLocationExistingValues(String code, String value){
            this.code = code;
            this.value = value;          
        }

        public String getCode() {   
            return code; 
        }

        public String getValue() { 
            return value; 
        }

    }

    /**
     * The set up method calls the parent super method and gets the 
     * protocol page after saving initial required fields.
     * @see org.kuali.kra.irb.web.ProtocolWebTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        protocolPage = getProtocolSavedRequiredFieldsPage();
        
    }

    /**
     * This method calls parent tear down method and than sets protocolPage to null
     * @see org.kuali.kra.irb.web.ProtocolWebTestBase#tearDown()
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        protocolPage = null;
    }
    
    /**
     * 
     * This method tests protocol location / organization panel with initialized values.
     * @throws Exception
     */
    @Test
    public void testLocationPanelInit() throws Exception{
        validatePage(protocolPage, getProtocolLocationExistingFieldsMap());
    }
    
    /**
     * 
     * This method tests delete and add new protocol location / organization functionality.
     * Delete - check if page contains organization id before delete and after delete
     * organization id should exist before delete and page does not contain after delete.
     * Add - add new location with new organization id. Check if new organization id exists before
     * and after adding new location.
     * @throws Exception
     */
    @Test
    public void testDeleteAndAddLocation() throws Exception{
        HtmlPage pageAfterDeleteLocation = deleteDefaultLocation();
        //add new location
        assertDoesNotContain(pageAfterDeleteLocation, "1 000001 University Performing Organization");
        setFieldValues(pageAfterDeleteLocation, getProtocolLocationNewFieldsMap());
        HtmlPage pageAfterAddLocation = clickOnByName(pageAfterDeleteLocation, ADD_LOCATION, true);
        assertContains(pageAfterAddLocation, NEW_ORGANIZATION_VALUE);
    }

    /**
     * This method will remove existing default location and save the page
     * Save should throw and error.
     * @throws Exception
     */
    @Test
    public void testDeleteLocationAndSave() throws Exception{
        HtmlPage pageAfterDeleteLocation = deleteDefaultLocation();
        pageAfterDeleteLocation = this.saveDoc(pageAfterDeleteLocation);
        assertContains(pageAfterDeleteLocation,ERROR_PROTOCOL_WITHOUT_ORGANIZATION);                         
    }
    
    /**
     * This method is to delete default protocol location
     * @return
     */
    private HtmlPage deleteDefaultLocation() throws Exception{
        assertContains(protocolPage, ProtocolLocationExistingValues.ORGANIZATION_ID.getValue());
        //delete existing location
        HtmlPage pageAfterDeleteLocation = clickOnByName(protocolPage, DELETE_LOCATION, true);
        assertDoesNotContain(pageAfterDeleteLocation, "1 000001 University Performing Organization");
        return pageAfterDeleteLocation;
    }
    
    /**
     * 
     * This method is to test clear address functionality.
     * Check to see if address exists before and after invoking clear address function.
     * @throws Exception
     */
    @Test
    public void testClearAddress() throws Exception{
        HtmlPage page = clickOnExpandAll(protocolPage);
        assertContains(page, ADDRESS_LINE_1);
        //clear existing address
        HtmlPage pageAfterClearAddress = clickOn(page, CLEAR_ADDRESS);
        assertDoesNotContain(pageAfterClearAddress, ADDRESS_LINE_1);
    }
    
    /**
     * This method is to construct a map of protocol location existing field values
     * linked to enum ProtocolLocationExistingValues declared on top
     * @return protocol location fields and values
     */
    protected Map<String,String> getProtocolLocationExistingFieldsMap(){
        Map<String,String> locationFieldMap = new HashMap<String,String>(); 
        for (ProtocolLocationExistingValues protocolLocationExistingValue : ProtocolLocationExistingValues.values()) {
            locationFieldMap.put(protocolLocationExistingValue.getCode(), protocolLocationExistingValue.getValue());
        }
        return locationFieldMap;
    }

    /**
     * This method is to construct a map of protocol location - to add a new protocol location
     * @return protocol location fields and values
     */
    protected Map<String,String> getProtocolLocationNewFieldsMap(){
        Map<String,String> locationFieldMap = new HashMap<String,String>(); 
        locationFieldMap.put(NEW_ORGANIZATION_ID, NEW_ORGANIZATION_VALUE);
        return locationFieldMap;
    }
}
