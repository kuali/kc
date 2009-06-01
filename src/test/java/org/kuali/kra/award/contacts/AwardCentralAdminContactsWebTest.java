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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * This class tests the ApprovedEquipment panel
 */
public class AwardCentralAdminContactsWebTest extends AwardContactsWebTest {
    private static final String CONTACT_ROLE_CODE = "1";
    private static final String CONTACT_ROLE_CONTEXT = "centralAdminContactsBean.contactRoleCode";
    private static final String ADD_NEW_CONTACT_BUTTON_CONTEXT = METHOD_TO_CALL_PREFIX + "addCentralAdminContact";
    private static final String CLEAR_NEW_CONTACT_BUTTON_CONTEXT = METHOD_TO_CALL_PREFIX + "clearNewCentralAdminContact";
    private static final String DELETE_CONTACT_BUTTON_CONTEXT = METHOD_TO_CALL_PREFIX + "deleteCentralAdminContact";
    private static final String EMPLOYEE_LOOKUP_CONTEXT = "centralAdminContactsBean.personId";
    private static final String TAB_COUNT_MSG = "Central Administration Contacts (%d)";
    
    @Test
    public void deletingCentralAdminContact() throws Exception {
        addNewEmployeeContact(CONTACT_ROLE_CODE);
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
    public void testAddingCentralAdminContact() throws Exception {
        addEmployeeContact(CONTACT_ROLE_CODE);
    }

    @Test
    public void testClearingCentralAdminContact() throws Exception {
        checkClearingEmployeeContact();
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
    
    protected String getContactRoleLookupContext() {
        return CONTACT_ROLE_CONTEXT;
    }
    
    protected String getTabCountMessagePattern() {
        return TAB_COUNT_MSG;
    }
}