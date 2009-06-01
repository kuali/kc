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

import org.kuali.kra.award.htmlunitwebtest.AwardWebTestBase;

import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlImageInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * This class loads the Award SpecialReview tab page
 */
public abstract class AwardContactsWebTest extends AwardWebTestBase {
    private static final String ROLE_CODE_FIELD_ID = "roleCode";

    protected static final String CONTACTS_LINK_NAME = "contacts.x";

    protected static final String EMPLOYEE_FULL_NAME = "Joe Tester";

    protected static final String LINE_NUMBER_SUFFIX_PATTERN = ".line%d";
    protected static final String PERSON_ID_FIELD = "personId";
    protected static final String PERSON_ID_VALUE = "000000008";
    
    protected static final String ROLODEX_FULL_NAME = "Pauline Ho";
    protected static final String ROLODEX_ID_FIELD = "rolodexId";
    protected static final String ROLODEX_ID_VALUE = "1727";

    private static final String SUBCLASSES_SHOULD_OVERRIDE_THIS_METHOD_MSG = "Subclasses should override this method";
    
    protected HtmlPage contactsPage; 
    
    @Override
    public void setUp() throws Exception {
        super.setUp();
        contactsPage = clickOnTab(getAwardHomePage(), CONTACTS_LINK_NAME);
    }
    
    @Override
    public void tearDown() throws Exception {
        contactsPage = null;
        super.tearDown();
    }
    
    protected void addEmployeeContact(String contactRoleCode) throws Exception {
        assertContains(contactsPage, getTabCountMessage(0));
        addNewEmployeeContact(contactRoleCode);
        checkForErrorsOnPage();
        assertContains(contactsPage, getTabCountMessage(1));
    }
    protected void addNewEmployeeContact(String contactRoleCode) throws Exception {
        selectEmployee();
        assertContains(contactsPage, EMPLOYEE_FULL_NAME);
        selectContactRole(contactRoleCode);
        addSelectedContact();
    }

    protected void addNewNonEmployeeContact(String contactRoleCode) throws Exception {
        selectNonEmployee();
        selectContactRole(contactRoleCode);
        addSelectedContact();
    }
    protected void addNonEmployeeContact(String contactRoleCode) throws Exception {
        assertContains(contactsPage, getTabCountMessage(0));
        addNewNonEmployeeContact(contactRoleCode);
        checkForErrorsOnPage();
        assertContains(contactsPage, ROLODEX_FULL_NAME);
        assertContains(contactsPage, getTabCountMessage(1));
    }

    protected void addSelectedContact() throws IOException {
        HtmlForm form = (HtmlForm) contactsPage.getForms().get(0);
        String addButtonName = getImageTagName(contactsPage, getAddSelectedContactButtonContext());        
        HtmlImageInput addButton = (HtmlImageInput) form.getInputByName(addButtonName);
        contactsPage = (HtmlPage) addButton.click();        
    }
    
    protected void checkClearingEmployeeContact() throws Exception {
        selectEmployee();
        assertContains(contactsPage, EMPLOYEE_FULL_NAME);
        clearSelection();
        assertDoesNotContain(contactsPage, EMPLOYEE_FULL_NAME);
    }
    
    protected void checkClearingNonEmployeeContact() throws Exception {
        selectNonEmployee();
        assertContains(contactsPage, ROLODEX_FULL_NAME);
        clearSelection();
        assertDoesNotContain(contactsPage, ROLODEX_FULL_NAME);
    }

    protected void checkForErrorsOnPage() {
        assertDoesNotContain(contactsPage, EXCEPTION_OR_SYSTEM_ERROR);
        assertDoesNotContain(contactsPage, ERRORS_FOUND_ON_PAGE);
    }

    protected void clearSelection() throws Exception {
        contactsPage = pressAButton(contactsPage, getClearSelectedContactButtonContext());        
    }

    protected void deleteContactFromList(String contactName) throws Exception {
        assertContains(contactsPage, contactName);
        deleteContactFromTable(0);
        assertContains(contactsPage, getTabCountMessage(0));
        assertDoesNotContain(contactsPage, contactName);
    }

    protected abstract String getAddSelectedContactButtonContext();

    /**
     * @return
     * 
     */
    protected HtmlPage getAwardContactsPage() {
        return contactsPage;
    }

    protected abstract String getClearSelectedContactButtonContext();
    protected abstract String getDeleteContactFromTableButtonContext();
    protected abstract String getContactRoleLookupContext();

    protected String getEmployeeLookupContext() {
        throw new RuntimeException(SUBCLASSES_SHOULD_OVERRIDE_THIS_METHOD_MSG);
    }

    protected String getRolodexLookupContext() {
        throw new RuntimeException(SUBCLASSES_SHOULD_OVERRIDE_THIS_METHOD_MSG);
    }

    protected String getTabCountMessage(int expectedCount) {
        return String.format(getTabCountMessagePattern(), expectedCount);
    }

    protected abstract String getTabCountMessagePattern();

    protected void selectEmployee() throws Exception {
        contactsPage = lookup(contactsPage, getEmployeeLookupContext(), PERSON_ID_FIELD, PERSON_ID_VALUE);
    }

    protected void selectNonEmployee() throws Exception {
        contactsPage = lookup(contactsPage, getRolodexLookupContext(), ROLODEX_ID_FIELD, ROLODEX_ID_VALUE);        
    }

    private String buildDeleteButtonMethodToCallName(String buttonContext, int rowNum) {
        return String.format(buttonContext + LINE_NUMBER_SUFFIX_PATTERN, rowNum);
    }
    
    private void deleteContactFromTable(int rowNum) throws IOException {
        contactsPage = pressAButton(contactsPage, buildDeleteButtonMethodToCallName(getDeleteContactFromTableButtonContext(), rowNum));
    }
    
    private void selectContactRole(String contactRoleCode) throws IOException {
        contactsPage = lookup(contactsPage, getContactRoleLookupContext(), ROLE_CODE_FIELD_ID, contactRoleCode);
    }
}
