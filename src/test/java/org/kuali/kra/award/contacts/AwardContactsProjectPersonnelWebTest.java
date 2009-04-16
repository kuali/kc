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
package org.kuali.kra.award.contacts;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlImageInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * This class tests the ApprovedEquipment panel
 */
public class AwardContactsProjectPersonnelWebTest extends AwardContactsWebTest {
    private static final String ADD_NEW_CONTACT_BUTTON_CONTEXT = METHOD_TO_CALL_PREFIX + "addProjectPerson";
    private static final String CLEAR_NEW_CONTACT_BUTTON_CONTEXT = METHOD_TO_CALL_PREFIX + "clearNewProjectPerson";
    private static final String DELETE_CONTACT_BUTTON_CONTEXT = METHOD_TO_CALL_PREFIX + "deleteProjectPerson";
    private static final String EMPLOYEE_LOOKUP_CONTEXT = "projectPersonnelBean.personId";
    private static final String ROLDOEX_LOOKUP_CONTEXT = "projectPersonnelBean.rolodexId";
    private static final String TAB_COUNT_MSG = "Project Personnel (%d)";    
    
    @Test
    public void deletingEmployeeProjectPerson() throws Exception {
        addEmployeeContact();
        deleteContactFromList(EMPLOYEE_FULL_NAME);
    }
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testAddingProjectPerson_Employee() throws Exception {
        addEmployeeContact();
    }
    
    @Test
    public void testAddingProjectPersonUnitToEmployee() throws Exception {
        addEmployeeContact();
        addUnit(true);
    }

    @Test
    public void testSavingProjectPerson() throws Exception {
        addEmployeeContact();
        addUnit(true);
        save(contactsPage);
        checkForErrorsOnPage();
    }
    
    private void addUnit(boolean leadUnit) throws Exception {
        selectUnit();
        setFieldValue(contactsPage, "projectPersonnelBean.newAwardPersonUnit.leadUnit", leadUnit ? CHECKED : UNCHECKED);
        checkForErrorsOnPage();
        addSelectedUnit();        
    }

    protected void addSelectedUnit() throws IOException {
        HtmlForm form = (HtmlForm) contactsPage.getForms().get(0);
        String addButtonName = getImageTagName(contactsPage, METHOD_TO_CALL_PREFIX + "addNewProjectPersonUnit");        
        HtmlImageInput addButton = (HtmlImageInput) form.getInputByName(addButtonName);
        contactsPage = (HtmlPage) addButton.click();
        checkForErrorsOnPage();
        assertContains(contactsPage, "CARDIOLOGY");        
    }
    
    private void selectUnit() throws Exception {
        contactsPage = lookup(contactsPage, "projectPersonnelBean.newUnitNumber", "unitNumber", "IN-CARD");
    }

    @Test
    public void testAddingProjectPerson_Nonemployee() throws Exception {
        addNonEmployeeContact();
    }
    
    @Test
    public void testClearingProjectPerson_Employee() throws Exception {
        checkClearingEmployeeContact();
    }
    
    @Test
    public void testClearingProjectPerson_NonEmployee() throws Exception {
        checkClearingNonEmployeeContact();
    }

    protected String getAddSelectedContactButtonContext() {
        return ADD_NEW_CONTACT_BUTTON_CONTEXT;
    }
    
    protected String getClearSelectedContactButtonContext() {
        return CLEAR_NEW_CONTACT_BUTTON_CONTEXT;
    }
   
    protected String getDeleteContactFromTableButtonContext() {
        return DELETE_CONTACT_BUTTON_CONTEXT;
    }
    
    protected String getEmployeeLookupContext() {
        return EMPLOYEE_LOOKUP_CONTEXT;
    }

    protected String getRolodexLookupContext() {
        return ROLDOEX_LOOKUP_CONTEXT; 
    }
    
    protected String getTabCountMessagePattern() {
        return TAB_COUNT_MSG;
    }
}