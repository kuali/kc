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
public class AwardSponsorContactsWebTest extends AwardContactsWebTest {
    private static final String ADD_NEW_CONTACT_BUTTON_CONTEXT = METHOD_TO_CALL_PREFIX + "addSponsorContact";
    private static final String CLEAR_NEW_CONTACT_BUTTON_CONTEXT = METHOD_TO_CALL_PREFIX + "clearNewSponsorContact";
    private static final String DELETE_CONTACT_BUTTON_CONTEXT = METHOD_TO_CALL_PREFIX + "deleteSponsorContact";
    private static final String NONEMPLOYEE_LOOKUP_CONTEXT = "sponsorContactsBean.rolodexId";
    private static final String TAB_COUNT_MSG = "Sponsor Contacts (%d)";
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void deletingSponsorContact() throws Exception {
        addNewNonEmployeeContact();
        deleteContactFromList(ROLODEX_FULL_NAME);
    }
    
    @Test
    public void testAddingSponsorContact() throws Exception {
        addNonEmployeeContact();
    }

    @Test
    public void testClearingSponsorContact() throws Exception {
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
    
    protected String getTabCountMessagePattern() {
        return TAB_COUNT_MSG;
    }
    
    protected String getRolodexLookupContext() {
        return NONEMPLOYEE_LOOKUP_CONTEXT; 
    }
}