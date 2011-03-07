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

    private static final String GRADUATE_STUDENT_COUNT_ID = "customAttributeValues(id4)";
    private static final String BILLING_ELEMENT_ID = "customAttributeValues(id1)";
    private static final String USERNAME_FIELD_ID = "newProposalUser.username";
    private static final String ROLENAME_FIELD_ID = "newProposalUser.roleName";
    private static final String ADD_PROPOSAL_USER_ID = "methodToCall.addProposalUser";
    
    private static final String APPROVER = "jtester";
    private static final String VIEWER_ROLENAME = "Viewer";
    private static final String YES_RADIO_FIELD_VALUE = "Y";
    private static final String NO_RADIO_FIELD_VALUE = "N";
    private static final String NA_RADIO_FIELD_VALUE = "X";
    private static final String CREDIT_SPLIT_VALUE = "100.00";

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

        lookupEmployeeKeyPersonnel("10000000004", "Principal Investigator");
        click("methodToCall.insertProposalPerson");
        
        openTab(0);
        
        openTab(4);
        set("document.developmentProposalList[0].proposalPersons[0].proposalPersonYnqs[0].answer", YES_RADIO_FIELD_VALUE);
        set("document.developmentProposalList[0].proposalPersons[0].proposalPersonYnqs[1].answer", NO_RADIO_FIELD_VALUE);
        set("document.developmentProposalList[0].proposalPersons[0].proposalPersonYnqs[2].answer", YES_RADIO_FIELD_VALUE);
        set("document.developmentProposalList[0].proposalPersons[0].proposalPersonYnqs[3].answer", NO_RADIO_FIELD_VALUE);
        set("document.developmentProposalList[0].proposalPersons[0].proposalPersonYnqs[4].answer", NO_RADIO_FIELD_VALUE);
        set("document.developmentProposalList[0].investigator[0].creditSplits[0].credit", CREDIT_SPLIT_VALUE);
        set("document.developmentProposalList[0].investigator[0].creditSplits[1].credit", CREDIT_SPLIT_VALUE);
        set("document.developmentProposalList[0].investigator[0].creditSplits[2].credit", CREDIT_SPLIT_VALUE);
        set("document.developmentProposalList[0].investigator[0].creditSplits[3].credit", CREDIT_SPLIT_VALUE);
        set("document.developmentProposalList[0].investigator[0].units[0].creditSplits[0].credit", CREDIT_SPLIT_VALUE);
        set("document.developmentProposalList[0].investigator[0].units[0].creditSplits[1].credit", CREDIT_SPLIT_VALUE);
        set("document.developmentProposalList[0].investigator[0].units[0].creditSplits[2].credit", CREDIT_SPLIT_VALUE);
        set("document.developmentProposalList[0].investigator[0].units[0].creditSplits[3].credit", CREDIT_SPLIT_VALUE);
    }
    
    private void addCustomData() {
        clickProposalDevelopmentCustomDataPage();

        openTab(0);
        set(GRADUATE_STUDENT_COUNT_ID, TestUtilities.GRADUATE_STUDENT_COUNT_VALUE);
        
        openTab(2);
        set(BILLING_ELEMENT_ID, TestUtilities.BILLING_ELEMENT_VALUE);
    }
    
    private void addQuestions() {
        clickProposalDevelopmentQuestionsPage();

        openTab(0);
        set("document.developmentProposalList[0].proposalYnq[20].answer", YES_RADIO_FIELD_VALUE);
        
        openTab(1);
        set("document.developmentProposalList[0].proposalYnq[0].answer", NO_RADIO_FIELD_VALUE);
        set("document.developmentProposalList[0].proposalYnq[1].answer", NO_RADIO_FIELD_VALUE);
        set("document.developmentProposalList[0].proposalYnq[2].answer", NO_RADIO_FIELD_VALUE);
        set("document.developmentProposalList[0].proposalYnq[3].answer", NO_RADIO_FIELD_VALUE);
        set("document.developmentProposalList[0].proposalYnq[4].answer", NO_RADIO_FIELD_VALUE);
        set("document.developmentProposalList[0].proposalYnq[5].answer", NA_RADIO_FIELD_VALUE);
        set("document.developmentProposalList[0].proposalYnq[6].answer", NO_RADIO_FIELD_VALUE);
        set("document.developmentProposalList[0].proposalYnq[7].answer", NO_RADIO_FIELD_VALUE);
        set("document.developmentProposalList[0].proposalYnq[8].answer", NO_RADIO_FIELD_VALUE);
        set("document.developmentProposalList[0].proposalYnq[9].answer", NO_RADIO_FIELD_VALUE);
        set("document.developmentProposalList[0].proposalYnq[10].answer", NO_RADIO_FIELD_VALUE);
        set("document.developmentProposalList[0].proposalYnq[11].answer", NO_RADIO_FIELD_VALUE);
        set("document.developmentProposalList[0].proposalYnq[12].answer", NO_RADIO_FIELD_VALUE);
        set("document.developmentProposalList[0].proposalYnq[13].answer", NO_RADIO_FIELD_VALUE);
        set("document.developmentProposalList[0].proposalYnq[14].answer", NO_RADIO_FIELD_VALUE);
        set("document.developmentProposalList[0].proposalYnq[15].answer", NA_RADIO_FIELD_VALUE);
        set("document.developmentProposalList[0].proposalYnq[16].answer", NA_RADIO_FIELD_VALUE);
        set("document.developmentProposalList[0].proposalYnq[17].answer", NO_RADIO_FIELD_VALUE);
        set("document.developmentProposalList[0].proposalYnq[18].answer", NO_RADIO_FIELD_VALUE);
        set("document.developmentProposalList[0].proposalYnq[19].answer", NO_RADIO_FIELD_VALUE);
        set("document.developmentProposalList[0].proposalYnq[21].answer", NO_RADIO_FIELD_VALUE);
        set("document.developmentProposalList[0].proposalYnq[22].answer", NO_RADIO_FIELD_VALUE);
        set("document.developmentProposalList[0].proposalYnq[23].answer", YES_RADIO_FIELD_VALUE);
        set("document.developmentProposalList[0].proposalYnq[24].answer", NO_RADIO_FIELD_VALUE);
        set("document.developmentProposalList[0].proposalYnq[25].answer", NO_RADIO_FIELD_VALUE);
        set("document.developmentProposalList[0].proposalYnq[26].answer", NO_RADIO_FIELD_VALUE);
        set("document.developmentProposalList[0].proposalYnq[27].answer", NO_RADIO_FIELD_VALUE);
        set("document.developmentProposalList[0].proposalYnq[28].answer", NO_RADIO_FIELD_VALUE);
        set("document.developmentProposalList[0].proposalYnq[29].answer", NO_RADIO_FIELD_VALUE);
        set("document.developmentProposalList[0].proposalYnq[30].answer", NO_RADIO_FIELD_VALUE);
        set("document.developmentProposalList[0].proposalYnq[31].answer", NO_RADIO_FIELD_VALUE);
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
        click("methodToCall.processAnswer.button0");
        assertRoute();
        
        blanketApproveDocument();
        assertApprove();
        
        submitToSponsor();
        click("methodToCall.processAnswer.button0");
    }

}