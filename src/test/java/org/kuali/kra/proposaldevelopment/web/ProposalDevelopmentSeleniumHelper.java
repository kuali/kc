/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.web;

import org.kuali.kra.test.infrastructure.KcSeleniumHelper;
import org.openqa.selenium.WebDriver;

/**
 * Provides methods for creating a Proposal Development document, clicking on tabs, and populating fields.
 */
public class ProposalDevelopmentSeleniumHelper extends KcSeleniumHelper {
    
    private static final String PAGE_TITLE = "Kuali :: Proposal Development Document";
    
    private static final String CREATE_PROPOSAL_DEVELOPMENT_LINK_NAME = "Create Proposal";
    private static final String PROPOSAL_LINK_NAME = "proposal";
    private static final String GRANTS_GOV_LINK_NAME = "grantsGov";
    private static final String KEY_PERSONNEL_LINK_NAME = "keyPersonnel";
    private static final String SPECIAL_REVIEW_LINK_NAME = "specialReview";
    private static final String CUSTOM_DATA_LINK_NAME = "customData";
    private static final String ABSTRACTS_AND_ATTACHMENTS_LINK_NAME = "abstractsAttachments";
    private static final String QUESTIONS_LINK_NAME = "questions";
    private static final String BUDGET_VERSIONS_LINK_NAME = "budgetVersions";
    private static final String PERMISSIONS_LINK_NAME = "permissions";
    private static final String ACTIONS_LINK_NAME = "actions";
    
    private static final String PERSONNEL_ITEMS_FOR_REVIEW_TAB_ID = "Personnel Items for Review";
    private static final String ASDF_TAB_ID = "asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf";
    private static final String GRANTS_GOV_AGENCY_SPECIFIC_QUESTIONS_TAB_ID = "Grants gov Agency Specific Questions";
    private static final String PROPOSAL_QUESTIONS_TAB_ID = "Proposal Questions";
    
    private static final String DOCUMENT_DESCRIPTION_ID = "document.documentHeader.documentDescription";
    private static final String LIST_PREFIX = "document.developmentProposalList[0].";
    private static final String SPONSOR_CODE_ID = LIST_PREFIX + "sponsorCode";
    private static final String TYPE_CODE_ID = LIST_PREFIX + "proposalTypeCode";
    private static final String REQUESTED_START_DATE_ID = LIST_PREFIX + "requestedStartDateInitial";
    private static final String OWNED_BY_UNIT_ID = LIST_PREFIX + "ownedByUnitNumber";
    private static final String REQUESTED_END_DATE_ID = LIST_PREFIX + "requestedEndDateInitial";
    private static final String ACTIVITY_TYPE_CODE_ID = LIST_PREFIX + "activityTypeCode";
    private static final String TITLE_ID = LIST_PREFIX + "title";
    private static final String PERSON_ID_TAG = "newPersonId";
    private static final String PERSON_ID_ID = "personId";
    private static final String PERSON_ROLE_ID_ID = "newProposalPerson.proposalPersonRoleId";
    private static final String PERSON_YNQS_ID = "proposalPersonQuestionnaireHelpers[0].answerHeaders[0].answers[%d].answer";
    private static final String CREDIT_SPLITS_ID = LIST_PREFIX + "investigator[0].creditSplits[%d].credit";
    private static final String UNIT_CREDIT_SPLITS_ID = LIST_PREFIX + "investigator[0].units[0].creditSplits[%d].credit";
    private static final String GRADUATE_STUDENT_COUNT_ID = "customAttributeValues(id4)";
    private static final String BILLING_ELEMENT_ID = "customAttributeValues(id1)";
    private static final String YNQS_ID = LIST_PREFIX + "proposalYnq[%d].answer";
    private static final String NEW_BUDGET_VERSION_NAME_ID = "newBudgetVersionName";
    private static final String FINAL_VERSION_FLAG_ID = "document.budgetDocumentVersion[%d].budgetVersionOverview.finalVersionFlag";
    private static final String BUDGET_VERSION_ID = "document.budgetDocumentVersion[%d].budgetVersionOverview.budgetStatus";
    private static final String USERNAME_FIELD_ID = "newProposalUser.username";
    private static final String ROLENAME_FIELD_ID = "newProposalUser.roleName";
    
    private static final String DEFAULT_DOCUMENT_DESCRIPTION = "Proposal Development Web Test";
    private static final String DEFAULT_SPONSOR_CODE = "005770";
    private static final String DEFAULT_TYPE_CODE = "New";
    private static final String DEFAULT_REQUESTED_START_DATE = "08/14/2007";
    private static final String DEFAULT_OWNED_BY_UNIT = "000001 - University";
    private static final String DEFAULT_REQUESTED_END_DATE = "08/21/2007";
    private static final String DEFAULT_ACTIVITY_TYPE = "Instruction";
    private static final String DEFAULT_TITLE = "Project title";
    private static final String DEFAULT_PI_PERSON_ID = "10000000004";
    private static final String DEFAULT_PI_CONTACT_ROLE = "Principal Investigator";
    private static final String DEFAULT_TOTAL_CREDIT_SPLIT = "100.00";
    private static final String DEFAULT_GRADUATE_STUDENT_COUNT = "5";
    private static final String DEFAULT_BILLING_ELEMENT = "College";
    private static final String DEFAULT_BUDGET_VERSION_NAME = "Ver1";
    private static final String DEFAULT_BUDGET_STATUS = "Complete";
    private static final String DEFAULT_APPROVER = "jtester";
    private static final String DEFAULT_VIEWER_ROLENAME = "Viewer";
    
    private static final String YES_RADIO_FIELD_VALUE = "Y";
    private static final String NO_RADIO_FIELD_VALUE = "N";
    private static final String NA_RADIO_FIELD_VALUE = "X";
    
    private static final String INSERT_PROPOSAL_PERSON_BUTTON = "methodToCall.insertProposalPerson";
    private static final String ADD_PROPOSAL_USER_BUTTON = "methodToCall.addProposalUser";
    private static final String ADD_NEW_BUDGET_BUTTON = "methodToCall.addBudgetVersion";
    private static final String OPEN_BUDGET_BUTTON = "methodToCall.openBudgetVersion.line";
    private static final String SUBMIT_TO_SPONSOR_BUTTON = "methodToCall.submitToSponsor";
    
    private static ProposalDevelopmentSeleniumHelper helper;
    
    public static ProposalDevelopmentSeleniumHelper instance(WebDriver driver) {
        if (helper == null) {
            helper = new ProposalDevelopmentSeleniumHelper(driver);
        }
        return helper;
    }
    
    private ProposalDevelopmentSeleniumHelper(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Navigate to the Proposal Development Proposal page.
     */
    public void clickProposalDevelopmentProposalPage() {
        click(PROPOSAL_LINK_NAME);
    }
    
    /**
     * Navigate to the Proposal Development Grants.gov page.
     */
    public void clickProposalDevelopmentGrantsGovPage() {
        click(GRANTS_GOV_LINK_NAME);
    }
    
    /**
     * Navigate to the Proposal Development Key Personnel page.
     */
    public void clickProposalDevelopmentKeyPersonnelPage() {
        click(KEY_PERSONNEL_LINK_NAME);
    }
    
    /**
     * Navigate to the Proposal Development Special Review page.
     */
    public void clickProposalDevelopmentSpecialReviewPage() {
        click(SPECIAL_REVIEW_LINK_NAME);
    }
    
    /**
     * Navigate to the Proposal Development Custom Data page.
     */
    public void clickProposalDevelopmentCustomDataPage() {
        click(CUSTOM_DATA_LINK_NAME);
    }
    
    /**
     * Navigate to the Proposal Development Abstracts and Attachments page.
     */
    public void clickProposalDevelopmentAbstractsAndAttachmentsPage() {
        click(ABSTRACTS_AND_ATTACHMENTS_LINK_NAME);
    }
    
    /**
     * Navigate to the Proposal Development Questions page.
     */
    public void clickProposalDevelopmentQuestionsPage() {
        click(QUESTIONS_LINK_NAME);
    }
    
    /**
     * Navigate to the Proposal Development Budget Versions page.
     */
    public void clickProposalDevelopmentBudgetVersionsPage() {
        click(BUDGET_VERSIONS_LINK_NAME);
    }
    
    /**
     * Navigate to the Proposal Development Permissions page.
     */
    public void clickProposalDevelopmentPermissionsPage() {
        click(PERMISSIONS_LINK_NAME);
    }
    
    /**
     * Navigate to the Proposal Development Actions page.
     */
    public void clickProposalDevelopmentActionsPage() {
        click(ACTIONS_LINK_NAME);
    }
    
    /**
     * Submit this Proposal Development to the Sponsor.
     */
    public void submitToSponsor() {
        click(SUBMIT_TO_SPONSOR_BUTTON);
    }
    
    /**
     * Creates a new instance of the Proposal Development page, filling in all required values, and saving.
     */
    public void createProposalDevelopment() {
        clickResearcherTab();
        
        click(CREATE_PROPOSAL_DEVELOPMENT_LINK_NAME);
        assertTitleContains(PAGE_TITLE);

        setDefaultRequiredFields();
        
        saveDocument();
        assertSave();
    }
    
    /**
     * Sets the Proposal Development's required fields to legal default values.
     */
    private void setDefaultRequiredFields() {
        set(DOCUMENT_DESCRIPTION_ID, DEFAULT_DOCUMENT_DESCRIPTION);
        set(SPONSOR_CODE_ID, DEFAULT_SPONSOR_CODE);
        set(TYPE_CODE_ID, DEFAULT_TYPE_CODE);
        set(REQUESTED_START_DATE_ID, DEFAULT_REQUESTED_START_DATE);
        set(OWNED_BY_UNIT_ID, DEFAULT_OWNED_BY_UNIT);
        set(REQUESTED_END_DATE_ID, DEFAULT_REQUESTED_END_DATE);
        set(ACTIVITY_TYPE_CODE_ID, DEFAULT_ACTIVITY_TYPE);
        set(TITLE_ID, DEFAULT_TITLE);
    }
    
    /**
     * Adds a default Key Personnel with 100% total credit split and certifies them.
     */
    public void addKeyPersonnel() {
        clickProposalDevelopmentKeyPersonnelPage();

        lookup(PERSON_ID_TAG, PERSON_ID_ID, DEFAULT_PI_PERSON_ID);
        set(PERSON_ROLE_ID_ID, DEFAULT_PI_CONTACT_ROLE);
        click(INSERT_PROPOSAL_PERSON_BUTTON);
        
        openTab(0);
        
        openTab(5);
        set(String.format(PERSON_YNQS_ID, 0), YES_RADIO_FIELD_VALUE);
        set(String.format(PERSON_YNQS_ID, 1), NO_RADIO_FIELD_VALUE);
        set(String.format(PERSON_YNQS_ID, 2), YES_RADIO_FIELD_VALUE);
        set(String.format(PERSON_YNQS_ID, 3), NO_RADIO_FIELD_VALUE);
        set(String.format(PERSON_YNQS_ID, 4), NO_RADIO_FIELD_VALUE);
        set(String.format(PERSON_YNQS_ID, 5), YES_RADIO_FIELD_VALUE);
        
        for (int i = 0; i < 4; i++) {
            set(String.format(CREDIT_SPLITS_ID, i), DEFAULT_TOTAL_CREDIT_SPLIT);
            set(String.format(UNIT_CREDIT_SPLITS_ID, i), DEFAULT_TOTAL_CREDIT_SPLIT);
        }
    }
    
    /**
     * Adds default Custom Data.
     */
    public void addCustomData() {
        clickProposalDevelopmentCustomDataPage();

        openTab(PERSONNEL_ITEMS_FOR_REVIEW_TAB_ID);
        set(GRADUATE_STUDENT_COUNT_ID, DEFAULT_GRADUATE_STUDENT_COUNT);
        
        openTab(ASDF_TAB_ID);
        set(BILLING_ELEMENT_ID, DEFAULT_BILLING_ELEMENT);
    }
    
    /**
     * Answers default Questions.
     */
    public void addQuestions() {
        clickProposalDevelopmentQuestionsPage();

        openTab(PROPOSAL_QUESTIONS_TAB_ID);
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
        set(String.format(YNQS_ID, 20), NO_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 21), NO_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 22), YES_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 23), NO_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 24), NO_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 25), NO_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 26), NO_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 27), NO_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 28), NO_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 29), NO_RADIO_FIELD_VALUE);
        set(String.format(YNQS_ID, 30), NO_RADIO_FIELD_VALUE);
    }
    
    /**
     * Adds a default Budget.
     */
    public void addBudget() {
        clickProposalDevelopmentBudgetVersionsPage();
        
        set(NEW_BUDGET_VERSION_NAME_ID, DEFAULT_BUDGET_VERSION_NAME);
        click(ADD_NEW_BUDGET_BUTTON);
        assertSelectorContains(".budgetline td", DEFAULT_BUDGET_VERSION_NAME);
    }
    
    /**
     * Opens the Budget with the corresponding {@code budgetNumber}.
     * 
     * @param budgetNumber the index of the Budget to open
     */
    public void openBudget(int budgetNumber) {
        clickProposalDevelopmentBudgetVersionsPage();
        
        click(OPEN_BUDGET_BUTTON + String.valueOf(budgetNumber));
    }
    
    /**
     * Finalizes the Budget with the corresponding {@code budgetNumber}.
     * 
     * @param budgetNumber the index of the Budget to finalize
     */
    public void finalizeBudget(int budgetNumber) {
        clickProposalDevelopmentBudgetVersionsPage();
        
        click(String.format(FINAL_VERSION_FLAG_ID, budgetNumber));
        set(String.format(BUDGET_VERSION_ID, budgetNumber), DEFAULT_BUDGET_STATUS);
    }
    
    /**
     * Adds default Permissions.
     */
    public void addPermissions() {
        clickProposalDevelopmentPermissionsPage();
        
        set(USERNAME_FIELD_ID, DEFAULT_APPROVER);
        set(ROLENAME_FIELD_ID, DEFAULT_VIEWER_ROLENAME);
        click(ADD_PROPOSAL_USER_BUTTON);
    }
    
    /**
     * Submits this Proposal Development proposal.
     */
    public void submit() {
        clickProposalDevelopmentActionsPage();

        routeDocument();
        clickYesAnswer();
        assertRoute();
        
        blanketApproveDocument();
        assertApprove();
        
        submitToSponsor();
        clickYesAnswer();
    }

}