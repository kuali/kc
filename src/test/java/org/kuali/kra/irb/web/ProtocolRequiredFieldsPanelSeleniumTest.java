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
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

/**
 * Tests the Requires Fields for Saving Document tab in the Protocol page of a Protocol.
 */
public class ProtocolRequiredFieldsPanelSeleniumTest extends KcSeleniumTestBase {
    
    private static final String PAGE_TITLE = "Kuali :: KC Protocol";
    
    private static final String CREATE_PROTOCOL_LINK_NAME = "Create IRB Protocol";
    
    private static final String HEADER_STATUS_ID = "table[class='headerinfo'] tbody tr:nth-child(1) td:nth-child(4)";
    private static final String PRINCIPAL_INVESTIGATOR_NAME_DIV_ID = "div[id='principalInvestigatorName.div']";
    private static final String LEAD_UNIT_NAME_DIV_ID = "div[id='protocolHelper.leadUnitName.div']";
    private static final String ERROR_ID = "tab-RequiredFieldsforSavingDocument-div";
    
    private static final String HELPER_PREFIX = "protocolHelper.";
    private static final String LIST_PREFIX = "document.protocolList[0].";
    
    private static final String DOCUMENT_DESCRIPTION_ID = "document.documentHeader.documentDescription";
    private static final String TYPE_CODE_ID = "protocolTypeCode";
    private static final String TITLE_ID = "title";
    private static final String PERSON_ID_ID = "personId";
    private static final String LEAD_UNIT_NUMBER_ID = "leadUnitNumber";
    private static final String HELPER_PERSON_ID_ID = HELPER_PREFIX + PERSON_ID_ID;
    private static final String HELPER_LEAD_UNIT_NUMBER_ID = HELPER_PREFIX + LEAD_UNIT_NUMBER_ID;
    private static final String LIST_TYPE_CODE_ID = LIST_PREFIX + TYPE_CODE_ID;
    private static final String LIST_TITLE_ID = LIST_PREFIX + TITLE_ID;
    
    private static final String DEFAULT_DOCUMENT_DESCRIPTION = "Protocol Document";
    private static final String DEFAULT_TYPE_CODE = "Standard";
    private static final String DEFAULT_TITLE = "New protocol test";
    private static final String DEFAULT_PERSON_ID = "10000000004";
    private static final String DEFAULT_PERSON_ID_NAME = "Nicholas Majors";
    private static final String DEFAULT_LEAD_UNIT_NUMBER = "000001";
    
    private static final String STATUS = "Pending/In Progress";
    private static final String LEAD_UNIT_NUMBER = "bogus";
    
    private static final String ERROR_PRINCIPAL_INVESTIGATOR_REQUIRED = "Principal Investigator (Principal Investigator) is a required field";
    private static final String ERROR_TITLE_REQUIRED = "Title (Title) is a required field";
    private static final String ERROR_LEAD_UNIT_NUMBER_REQUIRED = "Lead Unit (Lead Unit) is a required field";
    private static final String ERROR_LEAD_UNIT_NUMBER_INVALID = "Lead Unit is invalid";
    
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
     * Test the field value persistence.
     *  
     * @throws Exception
     */
    @Test
    public void testRequiredFields() throws Exception {
        helper.createProtocol();
        helper.assertNoPageErrors();
        
        helper.assertElementContains(DOCUMENT_DESCRIPTION_ID, DEFAULT_DOCUMENT_DESCRIPTION); 
        helper.assertElementContains(LIST_TYPE_CODE_ID, DEFAULT_TYPE_CODE);
        helper.assertElementContains(LIST_TITLE_ID, DEFAULT_TITLE);
        helper.assertSelectorContains(PRINCIPAL_INVESTIGATOR_NAME_DIV_ID, DEFAULT_PERSON_ID_NAME);
        helper.assertSelectorContains(LEAD_UNIT_NAME_DIV_ID, DEFAULT_LEAD_UNIT_NUMBER);
        
        helper.assertSelectorContains(HEADER_STATUS_ID, STATUS);
    }
    
    /**
     * Test that a blank investigator id results in an error.
     * 
     * @throws Exception
     */
    @Test
    public  void testRequiredFieldsNegativeInvestigatorId() throws Exception {        
        helper.clickResearcherTab();
        
        helper.click(CREATE_PROTOCOL_LINK_NAME);
        helper.assertTitleContains(PAGE_TITLE);
        
        helper.set(DOCUMENT_DESCRIPTION_ID, DEFAULT_DOCUMENT_DESCRIPTION);
        helper.set(TYPE_CODE_ID, DEFAULT_TYPE_CODE);
        helper.set(TITLE_ID, DEFAULT_TITLE);
        
        helper.saveDocument();
        helper.assertPageErrors();
        
        helper.assertError(ERROR_ID, ERROR_PRINCIPAL_INVESTIGATOR_REQUIRED);
    }

    /**
     * Test that a blank title results in an error.
     *
     * @throws Exception
     */
    @Test
    public  void testRequiredFieldsNegativeTitle() throws Exception {
        helper.createProtocol();
        helper.assertNoPageErrors();
        
        helper.set(LIST_TITLE_ID, Constants.EMPTY_STRING);        

        helper.saveDocument();
        helper.assertPageErrors();
        
        helper.assertError(ERROR_ID, ERROR_TITLE_REQUIRED);
    }
    
    /**
     * Test that a blank lead unit results in an error.
     * 
     * @throws Exception
     */
    @Test
    public  void testRequiredFieldsNegativeLeadUnit() throws Exception {
        helper.clickResearcherTab();
        
        helper.click(CREATE_PROTOCOL_LINK_NAME);
        helper.assertTitleContains(PAGE_TITLE);
        
        helper.set(DOCUMENT_DESCRIPTION_ID, DEFAULT_DOCUMENT_DESCRIPTION);
        helper.set(TYPE_CODE_ID, DEFAULT_TYPE_CODE);
        helper.set(TITLE_ID, DEFAULT_TITLE);
        helper.lookup(HELPER_PERSON_ID_ID, PERSON_ID_ID, DEFAULT_PERSON_ID);
        helper.set(HELPER_LEAD_UNIT_NUMBER_ID, Constants.EMPTY_STRING);
        
        helper.saveDocument();
        helper.assertPageErrors();
        
        helper.assertError(ERROR_ID, ERROR_LEAD_UNIT_NUMBER_REQUIRED);
    }
    
    /**
     * Test that an invalid lead unit results in an error.
     * 
     * @throws Exception
     */
    @Test
    public  void testRequiredFieldsNegativeInvalidLeadUnit() throws Exception {
        helper.clickResearcherTab();
        
        helper.click(CREATE_PROTOCOL_LINK_NAME);
        helper.assertTitleContains(PAGE_TITLE);
        
        helper.set(DOCUMENT_DESCRIPTION_ID, DEFAULT_DOCUMENT_DESCRIPTION);
        helper.set(TYPE_CODE_ID, DEFAULT_TYPE_CODE);
        helper.set(TITLE_ID, DEFAULT_TITLE);
        helper.lookup(HELPER_PERSON_ID_ID, PERSON_ID_ID, DEFAULT_PERSON_ID);
        helper.set(HELPER_LEAD_UNIT_NUMBER_ID, LEAD_UNIT_NUMBER);
        
        helper.saveDocument();
        helper.assertPageErrors();
        
        helper.assertError(ERROR_ID, ERROR_LEAD_UNIT_NUMBER_INVALID);
    }
    
}