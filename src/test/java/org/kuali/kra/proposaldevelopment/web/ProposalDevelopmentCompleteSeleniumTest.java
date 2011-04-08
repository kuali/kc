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
package org.kuali.kra.proposaldevelopment.web;

import org.junit.Test;
import org.kuali.kra.infrastructure.TestUtilities;

public class ProposalDevelopmentCompleteSeleniumTest extends ProposalDevelopmentSeleniumTestBase {
    
    private static final String PERSON_ID_TAG = "newPersonId";

    private static final String PERSON_ID_ID = "personId";
    private static final String PERSON_ROLE_ID_ID = "newProposalPerson.proposalPersonRoleId";
    private static final String PERSON_YNQS_ID = "document.developmentProposalList[0].proposalPersons[0].proposalPersonYnqs[%d].answer";
    private static final String CREDIT_SPLITS_ID = "document.developmentProposalList[0].investigator[0].creditSplits[%d].credit";
    private static final String UNIT_CREDIT_SPLITS_ID = "document.developmentProposalList[0].investigator[0].units[0].creditSplits[%d].credit";
    private static final String GRADUATE_STUDENT_COUNT_ID = "customAttributeValues(id4)";
    private static final String BILLING_ELEMENT_ID = "customAttributeValues(id1)";
    private static final String YNQS_ID = "document.developmentProposalList[0].proposalYnq[%d].answer";
    private static final String USERNAME_FIELD_ID = "newProposalUser.username";
    private static final String ROLENAME_FIELD_ID = "newProposalUser.roleName";
    private static final String ADD_PROPOSAL_USER_ID = "methodToCall.addProposalUser";
    
    private static final String NICHOLAS_MAJORS_PERSON_ID = "10000000004";
    private static final String NICHOLAS_MAJORS_NAME = "Nicholas Majors";
    private static final String PI_CONTACT_ROLE = "Principal Investigator";
    private static final String APPROVER = "jtester";
    private static final String VIEWER_ROLENAME = "Viewer";
    private static final String YES_RADIO_FIELD_VALUE = "Y";
    private static final String NO_RADIO_FIELD_VALUE = "N";
    private static final String NA_RADIO_FIELD_VALUE = "X";
    private static final String TOTAL_CREDIT_SPLIT = "100.00";
    
    private static final String INSERT_PROPOSAL_PERSON = "methodToCall.insertProposalPerson";
    private static final String YES_BUTTON = "methodToCall.processAnswer.button0";

    @Test
    public void testProposalDevelopmentComplete() throws Exception {
        createProposalDevelopment();
        
        addKeyPersonnel();
        
        addCustomData();
        
        addQuestions();
        
        addPermissions();

        submit();
    }
    
    private void addKeyPersonnel() {
        clickProposalDevelopmentKeyPersonnelPage();

        lookup(PERSON_ID_TAG, PERSON_ID_ID, NICHOLAS_MAJORS_PERSON_ID);
        set(PERSON_ROLE_ID_ID, PI_CONTACT_ROLE);
        click(INSERT_PROPOSAL_PERSON);
        
        openTab(NICHOLAS_MAJORS_NAME);
        
        openTab(NICHOLAS_MAJORS_NAME + ": Certify");
        set(String.format(PERSON_YNQS_ID, 0), YES_RADIO_FIELD_VALUE);
        set(String.format(PERSON_YNQS_ID, 1), NO_RADIO_FIELD_VALUE);
        set(String.format(PERSON_YNQS_ID, 2), YES_RADIO_FIELD_VALUE);
        set(String.format(PERSON_YNQS_ID, 3), NO_RADIO_FIELD_VALUE);
        set(String.format(PERSON_YNQS_ID, 4), NO_RADIO_FIELD_VALUE);
        
        for (int i = 0; i < 4; i++) {
            set(String.format(CREDIT_SPLITS_ID, i), TOTAL_CREDIT_SPLIT);
            set(String.format(UNIT_CREDIT_SPLITS_ID, i), TOTAL_CREDIT_SPLIT);
        }
    }
    
    private void addCustomData() {
        clickProposalDevelopmentCustomDataPage();

        openTab("Personnel Items for Review");
        set(GRADUATE_STUDENT_COUNT_ID, TestUtilities.GRADUATE_STUDENT_COUNT_VALUE);
        
        openTab("asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf");
        set(BILLING_ELEMENT_ID, TestUtilities.BILLING_ELEMENT_VALUE);
    }
    
    private void addQuestions() {
        clickProposalDevelopmentQuestionsPage();

        openTab("Grants gov Agency Specific Questions");
        set(String.format(YNQS_ID, 20), YES_RADIO_FIELD_VALUE);
        
        openTab("Proposal Questions");
        set(String.format(YNQS_ID, 0), NO_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 1), NO_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 2), NO_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 3), NO_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 4), NO_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 5), NA_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 6), NO_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 7), NO_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 8), NO_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 9), NO_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 10), NO_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 11), NO_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 12), NO_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 13), NO_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 14), NO_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 15), NA_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 16), NA_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 17), NO_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 18), NO_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 19), NO_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 21), NO_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 22), NO_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 23), YES_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 24), NO_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 25), NO_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 26), NO_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 27), NO_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 28), NO_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 29), NO_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 30), NO_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 31), NO_RADIO_FIELD_VALUE);
    }
    
    private void addPermissions() {
        clickProposalDevelopmentPermissionsPage();
        
        set(USERNAME_FIELD_ID, APPROVER);
        set(ROLENAME_FIELD_ID, VIEWER_ROLENAME);
        click(ADD_PROPOSAL_USER_ID);
    }
    
    private void submit() {
        clickProposalDevelopmentActionsPage();

        routeDocument();
        click(YES_BUTTON);
        assertRoute();
        
        blanketApproveDocument();
        assertApprove();
        
        submitToSponsor();
        click(YES_BUTTON);
    }

}