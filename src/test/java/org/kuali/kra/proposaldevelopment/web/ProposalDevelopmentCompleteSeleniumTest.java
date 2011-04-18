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
import org.kuali.kra.budget.web.BudgetSeleniumHelper;
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
    
    protected static final String NICHOLAS_MAJORS_PERSON_ID = "10000000004";
    protected static final String NICHOLAS_MAJORS_NAME = "Nicholas Majors";
    protected static final String PI_CONTACT_ROLE = "Principal Investigator";
    protected static final String APPROVER = "jtester";
    protected static final String VIEWER_ROLENAME = "Viewer";
    protected static final String YES_RADIO_FIELD_VALUE = "Y";
    protected static final String NO_RADIO_FIELD_VALUE = "N";
    protected static final String NA_RADIO_FIELD_VALUE = "X";
    protected static final String TOTAL_CREDIT_SPLIT = "100.00";
    
    protected static final String INSERT_PROPOSAL_PERSON = "methodToCall.insertProposalPerson";
    protected static final String YES_BUTTON = "methodToCall.processAnswer.button0";
    
    protected static final String NEW_BUDGET_VERSION_NAME_ID = "newBudgetVersionName";
    protected static final String ADD_NEW_BUDGET_ID = "methodToCall.addBudgetVersion";
    //append index of budget (ie. 0, 1, 2)
    protected static final String OPEN_BUDGET_ID = "methodToCall.openBudgetVersion.line";
    protected static final String BUDGET_VERSION_NAME = "Ver1";
    protected static final String JOB_CODE = "AA023";
    protected static final String SALARY_AMOUNT = "100000";
    protected static final String JOB_CODE_NAME = "Faculty Salaries Non-Tenured - On";
    protected static final String PERCENT_CHARGED = "100";
    protected static final String PERCENT_EFFORT = "100";
    protected static final String LINE_ITEM_DESC1 = "Equipment - Not MTDC";
    protected static final String LINE_ITEM_DESC2 = "Travel";
    protected static final String LINE_ITEM_AMT = "1000";

    @Test
    public void testProposalDevelopmentComplete() throws Exception {
        createProposalDevelopment();
        
        addKeyPersonnel();
        
        addCustomData();
        
        addQuestions();
        
        addBudget();
        
        addPermissions();

        submit();
    }
    
    protected void addKeyPersonnel() {
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
    
    protected void addCustomData() {
        clickProposalDevelopmentCustomDataPage();

        openTab("Personnel Items for Review");
        set(GRADUATE_STUDENT_COUNT_ID, TestUtilities.GRADUATE_STUDENT_COUNT_VALUE);
        
        openTab("asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf");
        set(BILLING_ELEMENT_ID, TestUtilities.BILLING_ELEMENT_VALUE);
    }
    
    protected void addQuestions() {
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
    
    protected void addBudget() {
        clickProposalDevelopmentBudgetVersionsPage();
        set(NEW_BUDGET_VERSION_NAME_ID, BUDGET_VERSION_NAME);
        click(ADD_NEW_BUDGET_ID);
        assertSelectorContains(".budgetline td", BUDGET_VERSION_NAME);
        click(OPEN_BUDGET_ID + "0");
        BudgetSeleniumHelper budgetHelper = new BudgetSeleniumHelper();
        budgetHelper.clickBudgetPersonnelTab();
        budgetHelper.setPersonDetails(0, JOB_CODE, SALARY_AMOUNT);
        saveDocument();
        clickExpandAll();
        budgetHelper.addPersonnelDetail(NICHOLAS_MAJORS_NAME, JOB_CODE, JOB_CODE_NAME);
        budgetHelper.setPersonPercents(0, 0, 0, PERCENT_EFFORT, PERCENT_CHARGED);
        budgetHelper.clickNonPersonnelTab();
        budgetHelper.addLineItem(0, LINE_ITEM_DESC1, "1", LINE_ITEM_AMT);
        budgetHelper.addLineItem(1, LINE_ITEM_DESC2, null, LINE_ITEM_AMT);
        saveDocument();
        budgetHelper.returnToProposal();
        clickProposalDevelopmentBudgetVersionsPage();
        markBudgetVersionComplete(0);
    }
    
    protected void markBudgetVersionComplete(int budgetIndex) {
        click("document.budgetDocumentVersion[0].budgetVersionOverview.finalVersionFlag");
        set("document.budgetDocumentVersion[0].budgetVersionOverview.budgetStatus", "Complete");
    }
    
    
    protected void addPermissions() {
        clickProposalDevelopmentPermissionsPage();
        
        set(USERNAME_FIELD_ID, APPROVER);
        set(ROLENAME_FIELD_ID, VIEWER_ROLENAME);
        click(ADD_PROPOSAL_USER_ID);
    }
    
    protected void submit() {
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