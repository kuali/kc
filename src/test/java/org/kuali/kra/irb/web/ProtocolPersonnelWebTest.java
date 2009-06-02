/*
 * Copyright 2006-2008 The Kuali Foundation
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
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * 
 * This is the integration test for protocol personnel tab. 
 */
@SuppressWarnings("unchecked")
@PerSuiteUnitTestData(@UnitTestData(sqlFiles = {
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_PERSON_ROLE_MAPPING.sql", delimiter = ";")}))
public class ProtocolPersonnelWebTest extends ProtocolWebTestBase{
    
    private HtmlPage personnelPage;
    
    private static final String PRINCIPAL_INVESTIGATOR_NAME = "Terry Durkin";
    private static final String PERSONNEL_TAB_PAGE = "methodToCall.headerTab.headerDispatch.save.navigateTo.personnel.x";
    private static final String PERSON_LOOKUP = "org.kuali.kra.bo.Person";
    private static final String PERSON_ID_FIELD = "personId";
    private static final String CO_INVESTIGATOR_PERSON_ID = "000000002";
    private static final String CO_INVESTIGATOR_NAME = "Philip Berg";
    private static final String CO_INVESTIGATOR_ROLE_ID = "COI";
    private static final String NEW_PERSON_NAME_FIELD = "personnelHelper.newProtocolPerson.personName";
    private static final String NEW_PERSON_ROLE_ID_FIELD = "personnelHelper.newProtocolPerson.protocolPersonRoleId";
    private static final String ADD_PERSON_BUTTON = "methodToCall.addProtocolPerson";
    private static final String CLEAR_PERSON_BUTTON = "methodToCall.clearProtocolPerson";
    private static final String ADD_UNIT_BUTTON = "methodToCall.addProtocolPersonUnit.document.protocolList[0].protocolPersons[0].line";
    private static final String DELETE_UNIT_BUTTON = "methodToCall.deleteProtocolPersonUnit.document.protocolList[0].protocolPersons[0].line1";
    private static final String DELETE_PERSON_BUTTON = "methodToCall.deleteProtocolPerson";
    private static final String UNIT_NUMBER = "IU-UNIV";
    private static final String PERSON_PI_DELETE_FLAG = "document.protocolList[0].protocolPersons[0].delete";
    private static final String PERSON_COI_DELETE_FLAG = "document.protocolList[0].protocolPersons[1].delete";
    private static final String PERSON_PI_ROLE_ID_FIELD = "document.protocolList[0].protocolPersons[0].protocolPersonRoleId";
    private static final String CHECKBOX_CHECKED = "on";
    private static final String ERROR_PROTOCOL_WITHOUT_PI = "A Principal Investigator must be assigned to the Protocol.";
    private static final String ERROR_ROLE_CHANGE_NOT_PERMITTED = "This role change is not permitted.";
    private static final String NEW_PERSON1_UNIT_FIELD = "personnelHelper.newProtocolPersonUnits[0].unitNumber";
    private static final String NEW_PERSON2_UNIT_FIELD = "personnelHelper.newProtocolPersonUnits[0].unitNumber";
    

    /**
     * The set up method calls the parent super method and gets the 
     * protocol page after saving initial required fields.
     * Then navigate to personnel page
     * @see org.kuali.kra.irb.web.ProtocolWebTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.personnelPage = clickOn(getProtocolSavedRequiredFieldsPage(), PERSONNEL_TAB_PAGE);
        
    }

    /**
     * This method calls parent tear down method and than sets personnelPage to null
     * @see org.kuali.kra.irb.web.ProtocolWebTestBase#tearDown()
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        this.personnelPage = null;
    }
    
    /**
     * 
     * This method is to confirm Principal Investigator exists in Personnel page.
     * Principal investigator is part of mandatory fields in protocol tab.
     * Investigator selected in protocol tab will be listed in personnel page.
     * @throws Exception
     */
    @Test
    public void testPrincipalInvestigatorExists() throws Exception{
        assertTrue(getPersonnelPage().asText().contains(PRINCIPAL_INVESTIGATOR_NAME));
    }
    
    /**
     * This method is to test add person - Co-Investigator role
     * @throws Exception
     */
    @Test
    public void testAddCoInvestigator() throws Exception {
        assertFalse(personnelPage.asText().contains(CO_INVESTIGATOR_NAME));
        HtmlPage personnelPage = getPerson();
        assertTrue(personnelPage.asText().contains(CO_INVESTIGATOR_NAME));
        personnelPage = addPerson(personnelPage, CO_INVESTIGATOR_ROLE_ID);
        assertFalse(personnelPage.asText().contains(ERRORS_FOUND_ON_PAGE));
        saveAndSearchDoc(personnelPage);
    }
    
    /**
     * This method is to test clear option.
     * Select a person and clear selected person to search for a new person.
     * @throws Exception
     */
    @Test
    public void testClearPerson() throws Exception {
        assertFalse(personnelPage.asText().contains(CO_INVESTIGATOR_NAME));
        HtmlPage personnelPage = getPerson();
        setFieldValue(personnelPage,NEW_PERSON_ROLE_ID_FIELD, CO_INVESTIGATOR_ROLE_ID);
        assertTrue(personnelPage.asText().contains(CO_INVESTIGATOR_NAME));
        
        personnelPage = clickOn(getElementByName(personnelPage, CLEAR_PERSON_BUTTON, true));
        assertFalse(personnelPage.asText().contains(CO_INVESTIGATOR_NAME));
    }
    
    /**
     * This method is to test unit section for a person
     * add protocol unit
     * @throws Exception
     */
    @Test
    public void testAddUnitForPerson() throws Exception {
        HtmlPage personnelPage = getPersonnelPage();
        assertFalse(personnelPage.asText().contains(UNIT_NUMBER));
        personnelPage = addProtocolUnit(personnelPage, NEW_PERSON1_UNIT_FIELD);
        assertTrue(personnelPage.asText().contains(UNIT_NUMBER));
        saveAndSearchDoc(personnelPage);
    }
    
    /**
     * This method is to test unit section for a person
     * delete an existing protocol unit
     * @throws Exception
     */
    @Test
    public void testDeleteUnitFromPerson() throws Exception {
        HtmlPage personnelPage = getPersonnelPage();
        personnelPage = addProtocolUnit(personnelPage, NEW_PERSON1_UNIT_FIELD);
        assertTrue(personnelPage.asText().contains(UNIT_NUMBER));
        personnelPage = clickOn(getElementByName(personnelPage, DELETE_UNIT_BUTTON, true));
        assertFalse(personnelPage.asText().contains(UNIT_NUMBER));
        saveAndSearchDoc(personnelPage);
    }

    /**
     * This method is to delete person with role Principal Investigator
     * Save should throw exception indicating that there should be at least
     * one Principal Investigator
     * @throws Exception
     */
    @Test
    public void testDeletePersonFailure() throws Exception {
        HtmlPage personnelPage = getPersonnelPage();
        personnelPage = deletePerson(personnelPage, PERSON_PI_DELETE_FLAG);
        personnelPage = savePage(personnelPage);
        assertContains(personnelPage,ERROR_PROTOCOL_WITHOUT_PI);                         
    }

    /**
     * This method is to delete person 
     * Existing person is Principal Investigator
     * Add a new person and delete that person.
     * @throws Exception
     */
    @Test
    public void testDeletePersonSuccess() throws Exception {
        HtmlPage personnelPage = getPerson();
        personnelPage = addPerson(personnelPage, CO_INVESTIGATOR_ROLE_ID);
        personnelPage = addProtocolUnit(personnelPage, NEW_PERSON2_UNIT_FIELD);
        personnelPage = savePage(personnelPage);
        assertTrue(personnelPage.asText().contains(CO_INVESTIGATOR_NAME));
        personnelPage = deletePerson(personnelPage, PERSON_COI_DELETE_FLAG);
        personnelPage = savePage(personnelPage);
        assertFalse(personnelPage.asText().contains(CO_INVESTIGATOR_NAME));
    }

    
    /**
     * This method test an exception
     * Change role of PI to CO-I and save.
     * This should throw an exception indicating at least one PI should 
     * be assigned for a protocol
     * @throws Exception
     */
    @Test
    public void testAtleastOnePI() throws Exception {
        HtmlPage personnelPage = getPersonnelPage();
        setFieldValue(personnelPage, PERSON_PI_ROLE_ID_FIELD, CO_INVESTIGATOR_ROLE_ID);
        personnelPage = savePage(personnelPage);
        assertDoesNotContain(personnelPage, ERROR_ROLE_CHANGE_NOT_PERMITTED);                         
        assertContains(personnelPage,ERROR_PROTOCOL_WITHOUT_PI);                         
    }

    /**
     * This method is to get personnel page
     * @return HtmlPage
     */
    private HtmlPage getPersonnelPage() {
        return personnelPage;
    }
    
    /**
     * This method is to look up a person
     * @return HtmlPage
     * @throws Exception
     */
    private HtmlPage getPerson() throws Exception {
        return lookup(getPersonnelPage(), PERSON_LOOKUP, PERSON_ID_FIELD, CO_INVESTIGATOR_PERSON_ID);
    }
    
    /**
     * This method is add person to a page
     * @return HtmlPage
     * @throws Exception
     */
    private HtmlPage addPerson(HtmlPage page, String personRole) throws Exception {
        setFieldValue(page,NEW_PERSON_ROLE_ID_FIELD, personRole);
        return clickOn(getElementByName(page, ADD_PERSON_BUTTON, true));
    }

    /**
     * This method is to add a protocol unit
     * @param page
     * @return
     * @throws Exception
     */
    private HtmlPage addProtocolUnit(HtmlPage page, String unitField) throws Exception {
        setFieldValue(page, unitField, UNIT_NUMBER);
        return clickOn(getElementByName(page, ADD_UNIT_BUTTON, true));
    }
    
    /**
     * This method is to delete a person
     * @param page
     * @param deletePerson
     * @return
     * @throws Exception
     */
    private HtmlPage deletePerson(HtmlPage page, String deletePerson) throws Exception {
        setFieldValue(page, deletePerson, CHECKBOX_CHECKED);
        return clickOn(getElementByName(page, DELETE_PERSON_BUTTON, false));
    }

}
