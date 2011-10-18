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
 * Tests the Personnel page of a Protocol.
 */
public class ProtocolPersonnelSeleniumTest extends KcSeleniumTestBase {
    
    private static final String PROTOCOL_PERSON_HELPER_PREFIX = "personnelHelper.newProtocolPerson.";
    private static final String PROTOCOL_ATTACHMENT_PERSONNELS_HELPER_PREFIX = "personnelHelper.newProtocolAttachmentPersonnels[%d].";
    private static final String PROTOCOL_PERSON_UNITS_HELPER_PREFIX = "personnelHelper.newProtocolPersonUnits[%d].";
    private static final String PROTOCOL_PERSONS_LIST_PREFIX = "document.protocolList[0].protocolPersons[%d].";
    
    private static final String PERSON_ID_ID = "personId";
    private static final String PROTOCOL_PERSON_ROLE_ID_ID = "protocolPersonRoleId";
    private static final String TYPE_CODE_ID = "typeCode";
    private static final String DESCRIPTION_ID = "description";
    private static final String NEW_FILE_ID = "newFile";
    private static final String UNIT_NUMBER_ID = "unitNumber";
    private static final String DELETE_ID = "delete";
    private static final String PROTOCOL_PERSON_HELPER_PROTOCOL_PERSON_ROLE_ID_ID = PROTOCOL_PERSON_HELPER_PREFIX + PROTOCOL_PERSON_ROLE_ID_ID;
    private static final String PROTOCOL_ATTACHMENT_PERSONNELS_HELPER_TYPE_CODE_ID = PROTOCOL_ATTACHMENT_PERSONNELS_HELPER_PREFIX + TYPE_CODE_ID;
    private static final String PROTOCOL_ATTACHMENT_PERSONNELS_HELPER_DESCRIPTION_ID = PROTOCOL_ATTACHMENT_PERSONNELS_HELPER_PREFIX + DESCRIPTION_ID;
    private static final String PROTOCOL_ATTACHMENT_PERSONNELS_HELPER_NEW_FILE_ID = PROTOCOL_ATTACHMENT_PERSONNELS_HELPER_PREFIX + NEW_FILE_ID;
    private static final String PROTOCOL_PERSON_UNITS_HELPER_UNIT_NUMBER_ID = PROTOCOL_PERSON_UNITS_HELPER_PREFIX + UNIT_NUMBER_ID;
    private static final String PROTOCOL_PERSONS_LIST_DELETE_ID = PROTOCOL_PERSONS_LIST_PREFIX + DELETE_ID;
    private static final String PROTOCOL_PERSONS_LIST_PROTOCOL_PERSON_ROLE_ID_ID = PROTOCOL_PERSONS_LIST_PREFIX + PROTOCOL_PERSON_ROLE_ID_ID;

    private static final String PERSON_ID_PRINCIPAL_INVESTIGATOR_NAME = "Nicholas Majors";
    private static final String PERSON_ID_CO_INVESTIGATOR = "10000000005";
    private static final String PERSON_ID_CO_INVESTIGATOR_NAME = "Inez Chew";
    private static final String PROTOCOL_PERSON_ROLE_CO_INVESTIGATOR = "Co-Investigator";
    private static final String PROTOCOL_ATTACHMENT_PERSONNELS_TYPE_CODE = "Biography";
    private static final String PROTOCOL_ATTACHMENT_PERSONNELS_DESCRIPTION = "Biography attachment";
    private static final String UNIT_NUMBER = "IU-UNIV";
    private static final String CHECKBOX_CHECKED = "on";
    
    private static final String ERROR_PRINCIPAL_INVESTIGATOR_REQUIRED = "A Principal Investigator must be assigned to the Protocol.";
    
    private static final String ADD_PROTOCOL_PERSON_BUTTON = "methodToCall.addProtocolPerson";
    private static final String CLEAR_PROTOCOL_PERSON_BUTTON = "methodToCall.clearProtocolPerson";
    private static final String DELETE_PROTOCOL_PERSON_BUTTON = "methodToCall.deleteProtocolPerson";
    private static final String ADD_PERSONNEL_ATTACHMENT_BUTTON = "methodToCall.addPersonnelAttachment.document.protocolList[0].protocolPersons[%d].line";
    private static final String DELETE_PERSONNEL_ATTACHMENT_BUTTON = "methodToCall.deletePersonnelAttachment.document.protocolList[0].protocolPersons[%d].line%d";
    private static final String ADD_PROTOCOL_PERSON_UNIT_BUTTON = "methodToCall.addProtocolPersonUnit.document.protocolList[0].protocolPersons[%d].line";
    private static final String DELETE_PROTOCOL_PERSON_UNIT_BUTTON = "methodToCall.deleteProtocolPersonUnit.document.protocolList[0].protocolPersons[%d].line%d";

    
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
     * Test that the principal investigator exists already on the page.
     * 
     * @throws Exception
     */
    @Test
    public void testPrincipalInvestigatorExists() throws Exception {
        helper.createProtocol();
        helper.clickProtocolPersonnelPage();
        
        helper.assertPageContains(PERSON_ID_PRINCIPAL_INVESTIGATOR_NAME);
    }
    
    /**
     * Test adding a co-investigator.
     * 
     * @throws Exception
     */
    @Test
    public void testAddCoInvestigator() throws Exception {
        helper.createProtocol();
        helper.clickProtocolPersonnelPage();
        
        helper.assertPageDoesNotContain(PERSON_ID_CO_INVESTIGATOR_NAME);
        
        helper.lookup(PERSON_ID_ID, PERSON_ID_ID, PERSON_ID_CO_INVESTIGATOR);
        
        helper.assertPageContains(PERSON_ID_CO_INVESTIGATOR_NAME);
        
        helper.set(PROTOCOL_PERSON_HELPER_PROTOCOL_PERSON_ROLE_ID_ID, PROTOCOL_PERSON_ROLE_CO_INVESTIGATOR);
        helper.click(ADD_PROTOCOL_PERSON_BUTTON);
        
        helper.assertNoPageErrors();
    }
    
    /**
     * Test clearing a person from the lookup.
     * 
     * @throws Exception
     */
    @Test
    public void testClearPerson() throws Exception {
        helper.createProtocol();
        helper.clickProtocolPersonnelPage();
        
        helper.assertPageDoesNotContain(PERSON_ID_CO_INVESTIGATOR_NAME);
        
        helper.lookup(PERSON_ID_ID, PERSON_ID_ID, PERSON_ID_CO_INVESTIGATOR);
        helper.set(PROTOCOL_PERSON_HELPER_PROTOCOL_PERSON_ROLE_ID_ID,PROTOCOL_PERSON_ROLE_CO_INVESTIGATOR);
        
        helper.assertPageContains(PERSON_ID_CO_INVESTIGATOR_NAME);
        
        helper.click(CLEAR_PROTOCOL_PERSON_BUTTON);
        
        helper.assertPageDoesNotContain(PERSON_ID_CO_INVESTIGATOR_NAME);
    }
    
    /**
     * Test adding an attachment for a person.
     * 
     * @throws Exception
     */
    @Test
    public void testAddAttachmentForPerson() throws Exception {
        helper.createProtocol();
        helper.clickProtocolPersonnelPage();
        
        helper.assertPageDoesNotContain(PROTOCOL_ATTACHMENT_PERSONNELS_DESCRIPTION);
        
        helper.set(String.format(PROTOCOL_ATTACHMENT_PERSONNELS_HELPER_TYPE_CODE_ID, 0), PROTOCOL_ATTACHMENT_PERSONNELS_TYPE_CODE);
        helper.set(String.format(PROTOCOL_ATTACHMENT_PERSONNELS_HELPER_DESCRIPTION_ID, 0), PROTOCOL_ATTACHMENT_PERSONNELS_DESCRIPTION);
        helper.set(String.format(PROTOCOL_ATTACHMENT_PERSONNELS_HELPER_NEW_FILE_ID, 0), helper.getAbsoluteFilePath(ProtocolPersonnelSeleniumTest.class));
        helper.click(String.format(ADD_PERSONNEL_ATTACHMENT_BUTTON, 0));
        
        helper.assertPageContains(PROTOCOL_ATTACHMENT_PERSONNELS_DESCRIPTION);
    }
    
    /**
     * Test deleting an attachment for a person.
     * 
     * @throws Exception
     */
    @Test
    public void testDeleteAttachmentForPerson() throws Exception {
        helper.createProtocol();
        helper.clickProtocolPersonnelPage();
        
        helper.openTab(0);
        
        helper.openTab(3);
        helper.set(String.format(PROTOCOL_ATTACHMENT_PERSONNELS_HELPER_TYPE_CODE_ID, 0), PROTOCOL_ATTACHMENT_PERSONNELS_TYPE_CODE);
        helper.set(String.format(PROTOCOL_ATTACHMENT_PERSONNELS_HELPER_DESCRIPTION_ID, 0), PROTOCOL_ATTACHMENT_PERSONNELS_DESCRIPTION);
        helper.set(String.format(PROTOCOL_ATTACHMENT_PERSONNELS_HELPER_NEW_FILE_ID, 0), helper.getAbsoluteFilePath(ProtocolPersonnelSeleniumTest.class));
        helper.click(String.format(ADD_PERSONNEL_ATTACHMENT_BUTTON, 0));
        
        helper.assertPageContains(PROTOCOL_ATTACHMENT_PERSONNELS_DESCRIPTION);
        
        helper.click(String.format(DELETE_PERSONNEL_ATTACHMENT_BUTTON, 0, 0));
        helper.clickYesAnswer();
        
        helper.assertPageDoesNotContain(PROTOCOL_ATTACHMENT_PERSONNELS_DESCRIPTION);
    }

    /**
     * Test adding a unit for a person.
     * 
     * @throws Exception
     */
    @Test
    public void testAddUnitForPerson() throws Exception {
        helper.createProtocol();
        helper.clickProtocolPersonnelPage();
        
        helper.assertPageDoesNotContain(UNIT_NUMBER);
        
        helper.set(String.format(PROTOCOL_PERSON_UNITS_HELPER_UNIT_NUMBER_ID, 0), UNIT_NUMBER);
        helper.click(String.format(ADD_PROTOCOL_PERSON_UNIT_BUTTON, 0));
        
        helper.assertPageContains(UNIT_NUMBER);
    }
    
    /**
     * Test deleting a unit for a person.
     * 
     * @throws Exception
     */
    @Test
    public void testDeleteUnitFromPerson() throws Exception {
        helper.createProtocol();
        helper.clickProtocolPersonnelPage();
        
        helper.openTab(0);
        
        helper.openTab(4);
        helper.set(String.format(PROTOCOL_PERSON_UNITS_HELPER_UNIT_NUMBER_ID, 0), UNIT_NUMBER);
        helper.click(String.format(ADD_PROTOCOL_PERSON_UNIT_BUTTON, 0));
        
        helper.assertPageContains(UNIT_NUMBER);
        
        helper.click(String.format(DELETE_PROTOCOL_PERSON_UNIT_BUTTON, 0, 1));
        
        helper.assertPageDoesNotContain(UNIT_NUMBER);
    }

    /**
     * Test deleting the principal investigator and verify that it throws an error.
     * 
     * @throws Exception
     */
    @Test
    public void testDeletePersonFailure() throws Exception {
        helper.createProtocol();
        helper.clickProtocolPersonnelPage();
        
        helper.set(String.format(PROTOCOL_PERSONS_LIST_DELETE_ID, 0), CHECKBOX_CHECKED);
        helper.click(DELETE_PROTOCOL_PERSON_BUTTON);
        
        helper.saveDocument();
        helper.assertPageErrors();

        helper.assertPageContains(ERROR_PRINCIPAL_INVESTIGATOR_REQUIRED);
    }

    /**
     * Test deleting a person that is not a principal investigator.
     * 
     * @throws Exception
     */
    @Test
    public void testDeletePersonSuccess() throws Exception {
        helper.createProtocol();
        helper.clickProtocolPersonnelPage();
        
        helper.lookup(PERSON_ID_ID, PERSON_ID_ID, PERSON_ID_CO_INVESTIGATOR);
        
        helper.set(PROTOCOL_PERSON_HELPER_PROTOCOL_PERSON_ROLE_ID_ID, PROTOCOL_PERSON_ROLE_CO_INVESTIGATOR);
        helper.click(ADD_PROTOCOL_PERSON_BUTTON);
        
        helper.openTab(1);
        
        helper.openTab(5);
        helper.set(String.format(PROTOCOL_PERSON_UNITS_HELPER_UNIT_NUMBER_ID, 1), UNIT_NUMBER);
        helper.click(String.format(ADD_PROTOCOL_PERSON_UNIT_BUTTON, 1));
        
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertPageContains(PERSON_ID_CO_INVESTIGATOR_NAME);
        
        helper.set(String.format(PROTOCOL_PERSONS_LIST_DELETE_ID, 1), CHECKBOX_CHECKED);
        helper.click(DELETE_PROTOCOL_PERSON_BUTTON, true);
        
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertPageDoesNotContain(PERSON_ID_CO_INVESTIGATOR_NAME);
    }

    
    /**
     * Test setting the existing principal investigator to a new role and verify that it throws an error.
     * @throws Exception
     */
    @Test
    public void testAtleastOnePI() throws Exception {
        helper.createProtocol();
        helper.clickProtocolPersonnelPage();
        
        helper.openTab(0);
        
        helper.openTab(1);
        helper.set(String.format(PROTOCOL_PERSONS_LIST_PROTOCOL_PERSON_ROLE_ID_ID, 0), PROTOCOL_PERSON_ROLE_CO_INVESTIGATOR);
        
        helper.saveDocument();
        helper.assertPageErrors();
        
        helper.assertPageContains(ERROR_PRINCIPAL_INVESTIGATOR_REQUIRED);
    }

}