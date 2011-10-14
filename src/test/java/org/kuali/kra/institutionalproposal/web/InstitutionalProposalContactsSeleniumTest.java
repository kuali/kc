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
package org.kuali.kra.institutionalproposal.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

/**
 * Tests the Contacts page of an Institutional Proposal.
 */
public class InstitutionalProposalContactsSeleniumTest extends KcSeleniumTestBase {
    
    private static final String PROJECT_PERSONNEL_TAG_ID = "ProjectPersonnel";
    
    private static final String BEAN_PREFIX = "projectPersonnelBean.";
    
    private static final String PERSON_ID_TAG = BEAN_PREFIX + "personId";
    private static final String ROLODEX_ID_TAG = BEAN_PREFIX + "rolodexId";
    
    private static final String PERSON_ID_ID = "personId";
    private static final String ROLODEX_ID_ID = "rolodexId";
    private static final String CONTACT_ROLE_CODE_ID = BEAN_PREFIX + "contactRoleCode";
    private static final String KEY_PERSON_ROLE_ID = BEAN_PREFIX + "newInstitutionalProposalContact.keyPersonRole";

    private static final String PERSON_ID = "10000000002";
    private static final String ROLODEX_ID = "1727";
    
    private static final String EMPLOYEE_FULL_NAME = "Joe Tester";
    private static final String ROLODEX_FULL_NAME = "Ho, Pauline";
    private static final String CO_INVESTIGATOR_CONTACT_ROLE_CODE = "Co-Investigator";
    private static final String KEY_PERSON_CONTACT_ROLE_CODE = "Key Person";
    private static final String OBSERVER_KEY_PERSON_ROLE = "Observer";
    
    private static final String ADD_PROJECT_PERSON_BUTTON = "methodToCall.addProjectPerson";
    private static final String DELETE_PROJECT_PERSON_BUTTON = "methodToCall.deleteProjectPerson.line%d";
    
    private InstitutionalProposalSeleniumHelper helper;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        helper = InstitutionalProposalSeleniumHelper.instance(driver);
    }
    
    @After
    public void tearDown() throws Exception {
        helper = null;
        
        super.tearDown();
    }
    
    /**
     * Test the addition and removal of an employee.
     * 
     * @throws Exception
     */
    @Test
    public void testAddRemoveEmployeeContact() throws Exception {
        helper.createInstitutionalProposal();
        helper.clickInstitutionalProposalContactsPage();
        
        helper.openTab(PROJECT_PERSONNEL_TAG_ID);
        helper.lookup(PERSON_ID_TAG, PERSON_ID_ID, PERSON_ID);
        
        helper.set(CONTACT_ROLE_CODE_ID, CO_INVESTIGATOR_CONTACT_ROLE_CODE);
        helper.click(ADD_PROJECT_PERSON_BUTTON);
        
        helper.assertNoPageErrors();
        helper.assertPageContains(EMPLOYEE_FULL_NAME);
        
        helper.click(String.format(DELETE_PROJECT_PERSON_BUTTON, 1));
        helper.assertPageDoesNotContain(EMPLOYEE_FULL_NAME);
    }

    /**
     * Test the addition and removal of a non-employee.
     * 
     * @throws Exception
     */
    @Test
    public void testAddRemoveRolodexContact() throws Exception {
        helper.createInstitutionalProposal();
        helper.clickInstitutionalProposalContactsPage();
        
        helper.openTab(PROJECT_PERSONNEL_TAG_ID);
        helper.lookup(ROLODEX_ID_TAG, ROLODEX_ID_ID, ROLODEX_ID);
        
        helper.set(CONTACT_ROLE_CODE_ID, KEY_PERSON_CONTACT_ROLE_CODE);
        helper.set(KEY_PERSON_ROLE_ID, OBSERVER_KEY_PERSON_ROLE);
        helper.click(ADD_PROJECT_PERSON_BUTTON);
        
        helper.assertNoPageErrors();
        helper.assertPageContains(ROLODEX_FULL_NAME);
        
        helper.click(String.format(DELETE_PROJECT_PERSON_BUTTON, 1));
        helper.assertPageDoesNotContain(ROLODEX_FULL_NAME);
    }

}