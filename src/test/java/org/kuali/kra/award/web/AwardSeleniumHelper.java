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
package org.kuali.kra.award.web;

import org.kuali.kra.award.AwardFixtureFactory;
import org.kuali.kra.test.infrastructure.KcSeleniumHelper;
import org.openqa.selenium.WebDriver;

/**
 * Provides methods for creating an Award document, clicking on tabs, and populating fields.
 */
public class AwardSeleniumHelper extends KcSeleniumHelper {
    
    private static final String PAGE_TITLE = "Kuali :: KC Award";
    
    private static final String CREATE_AWARD_LINK_NAME = "Award";
    private static final String HOME_LINK_NAME = "home";
    private static final String CONTACTS_LINK_NAME = "contacts";
    private static final String COMMITMENTS_LINK_NAME = "commitments";
    private static final String BUDGET_VERSIONS_LINK_NAME = "budgets";
    private static final String PAYMENT_REPORTS_AND_TERMS_LINK_NAME = "paymentReportsAndTerms";
    private static final String SPECIAL_REVIEW_LINK_NAME = "specialReview";
    private static final String CUSTOM_DATA_LINK_NAME = "customData";
    private static final String COMMENTS_NOTES_AND_ATTACHMENTS_LINK_NAME = "notesAndAttachments";
    private static final String ACTIONS_LINK_NAME = "awardActions";
    private static final String MEDUSA_LINK_NAME = "medusa";
    private static final String TIME_AND_MONEY_LINK_NAME = "methodToCall.timeAndMoney";
    
    private static final String SPONSOR_TEMPLATE_TAB_ID = "Sponsor Template";
    private static final String KEY_PERSONNEL_AND_CREDIT_SPLIT_TAB_ID = "Key Personnel and Credit Split";
    private static final String PERSONNEL_ITEMS_FOR_REVIEW_TAB_ID = "Personnel Items for Review";
    private static final String ASDF_TAB_ID = "asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf";
    
    private static final String DOCUMENT_DESCRIPTION_ID = "document.documentHeader.documentDescription";
    private static final String LIST_PREFIX = "document.awardList[0].";
    private static final String TRANSACTION_TYPE_CODE_ID = LIST_PREFIX + "awardTransactionTypeCode";
    private static final String UNIT_NUMBER_ID = LIST_PREFIX + "unitNumber";
    private static final String ACTIVITY_TYPE_CODE_ID = LIST_PREFIX + "activityTypeCode";
    private static final String STATUS_CODE_ID = LIST_PREFIX + "statusCode";
    private static final String TYPE_CODE_ID = LIST_PREFIX + "awardTypeCode";
    private static final String TITLE_ID = LIST_PREFIX + "title";
    private static final String SPONSOR_CODE_ID = LIST_PREFIX + "sponsorCode";
    private static final String PRIME_SPONSOR_CODE_ID = LIST_PREFIX + "primeSponsorCode";
    private static final String SPONSOR_AWARD_NUMBER_ID = LIST_PREFIX + "sponsorAwardNumber";
    private static final String MODIFICATION_NUMBER_ID = LIST_PREFIX + "modificationNumber";
    private static final String EFFECTIVE_DATE_ID = LIST_PREFIX + "awardEffectiveDate";
    private static final String CURRENT_FUND_EFFECTIVE_DATE_ID = LIST_PREFIX + "awardAmountInfos[0].currentFundEffectiveDate";
    private static final String FINAL_EXPIRATION_DATE_ID = LIST_PREFIX + "awardAmountInfos[0].finalExpirationDate";
    private static final String OBLIGATION_EXPIRATION_DATE_ID = LIST_PREFIX + "awardAmountInfos[0].obligationExpirationDate";
    private static final String ANTICIPATED_AMOUNT_ID = LIST_PREFIX + "awardAmountInfos[0].anticipatedTotalAmount";
    private static final String OBLIGATED_AMOUNT_ID = LIST_PREFIX + "awardAmountInfos[0].amountObligatedToDate";
    private static final String TEMPLATE_CODE_TAG = "document.award.templateCode";
    private static final String PERSON_NAME_TAG = "projectPersonnelBean.newProjectPerson.person.fullName";
    private static final String CONTACT_ROLE_CODE_ID = "projectPersonnelBean.contactRoleCode";
    private static final String CREDIT_SPLITS_ID = "document.awardList[0].projectPersons[0].creditSplits[%d].credit";
    private static final String UNIT_CREDIT_SPLITS_ID = "document.awardList[0].projectPersons[0].units[0].creditSplits[%d].credit";
    private static final String GRADUATE_STUDENT_COUNT_ID = "customDataHelper.customDataValues[3].value";
    private static final String BILLING_ELEMENT_ID = "customDataHelper.customDataValues[0].value";
    private static final String NEW_BUDGET_VERSION_NAME_ID = "newBudgetVersionName";
    private static final String FINAL_VERSION_FLAG_ID = "document.budgetDocumentVersion[%d].budgetVersionOverview.finalVersionFlag";
    private static final String BUDGET_VERSION_ID = "document.budgetDocumentVersion[%d].budgetVersionOverview.budgetStatus";
    
    private static final String DEFAULT_DOCUMENT_DESCRIPTION = "Award Development Web Test";
    private static final String DEFAULT_TRANSACTION_TYPE = "New";
    private static final String DEFAULT_ACTIVITY_TYPE = "Instruction";
    private static final String DEFAULT_STATUS = "Active";
    private static final String DEFAULT_TYPE = "Grant";
    private static final String DEFAULT_TITLE = "Award Title";
    private static final String DEFAULT_SPONSOR_CODE = "005979";
    private static final String DEFAULT_SPONSOR_AWARD_NUMBER = "1R01CA123456";
    private static final String DEFAULT_MODIFICATION_NUMBER = "1";
    private static final String DEFAULT_BEGIN_DATE = "03/01/2009";
    private static final String DEFAULT_MIDDLE_DATE = "04/01/2009";
    private static final String DEFAULT_END_DATE = "09/01/2010";
    private static final String DEFAULT_AMOUNT = "10000.00";
    private static final String DEFAULT_SPONSOR_TEMPLATE_CODE = "1";
    private static final String DEFAULT_PI_PERSON_NAME = "majors";
    private static final String DEFAULT_PI_CONTACT_ROLE = "Principal Investigator";
    private static final String DEFAULT_TOTAL_CREDIT_SPLIT = "100";
    private static final String DEFAULT_GRADUATE_STUDENT_COUNT = "5";
    private static final String DEFAULT_BILLING_ELEMENT = "College";
    private static final String DEFAULT_BUDGET_VERSION_NAME = "Ver1";
    private static final String DEFAULT_BUDGET_STATUS = "Complete";
    
    private static final String ADD_PERSON_BUTTON = "methodToCall.addProjectPerson";
    private static final String ADD_NEW_BUDGET_BUTTON = "methodToCall.addBudgetVersion";
    private static final String OPEN_BUDGET_BUTTON = "methodToCall.openBudgetVersion.line";
    private static final String APPLY_AWARD_TEMPLATE_BUTTON = "methodToCall.applySponsorTemplate";

    private static AwardSeleniumHelper helper;
    
    public static AwardSeleniumHelper instance(WebDriver driver) {
        if (helper == null) {
            helper = new AwardSeleniumHelper(driver);
        }
        return helper;
    }
    
    private AwardSeleniumHelper(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Navigate to the Award Home page.
     */
    public void clickAwardHomePage() {
        click(HOME_LINK_NAME);
    }
    
    /**
     * Navigate to the Award Contacts page.
     */
    public void clickAwardContactsPage() {
        click(CONTACTS_LINK_NAME);
    }
    
    /**
     * Navigate to the Award Commitments page.
     */
    public void clickAwardCommitmentsPage() {
        click(COMMITMENTS_LINK_NAME);
    }
    
    /**
     * Navigate to the Award Budget Versions page.
     */
    public void clickAwardBudgetVersionsPage() {
        click(BUDGET_VERSIONS_LINK_NAME);
    }
    
    /**
     * Navigate to the Award Payment, Reports & Terms page.
     */
    public void clickAwardPaymentReportsAndTermsPage() {
        click(PAYMENT_REPORTS_AND_TERMS_LINK_NAME);
    }
    
    /**
     * Navigate to the Award Special Review page.
     */
    public void clickAwardSpecialReviewPage() {
        click(SPECIAL_REVIEW_LINK_NAME);
    }
    
    /**
     * Navigate to the Award Custom Data page.
     */
    public void clickAwardCustomDataPage() {
        click(CUSTOM_DATA_LINK_NAME);
    }
    
    /**
     * Navigate to the Award Comments, Notes & Attachments page.
     */
    public void clickAwardCommentsNotesAndAttachmentsPage() {
        click(COMMENTS_NOTES_AND_ATTACHMENTS_LINK_NAME);
    }
    
    /**
     * Navigate to the Award Actions page.
     */
    public void clickAwardActionsPage() {
        click(ACTIONS_LINK_NAME);
    }
    
    /**
     * Navigate to the Award Medusa page.
     */
    public void clickAwardMedusaPage() {
        click(MEDUSA_LINK_NAME);
    }
    
    /**
     * Open the Time & Money document associated with this Award.
     */
    public void openTimeAndMoneyDocument() {
        click(TIME_AND_MONEY_LINK_NAME);
    }
    
    /**
     * Creates a new instance of the Award, filling in all required fields, and saving. 
     */
    public final void createAward() {
        clickCentralAdminTab();
        
        click(CREATE_AWARD_LINK_NAME);
        assertTitleContains(PAGE_TITLE);
        
        setDefaultRequiredFields();
        
        saveDocument();
        assertSave();
    }
    
    /**
     * Sets the Award's required fields to legal default values.
     */
    private void setDefaultRequiredFields() {
        set(DOCUMENT_DESCRIPTION_ID, DEFAULT_DOCUMENT_DESCRIPTION);
        set(TRANSACTION_TYPE_CODE_ID, DEFAULT_TRANSACTION_TYPE);
        set(UNIT_NUMBER_ID, AwardFixtureFactory.UNIVERSITY_UNIT_NUMBER);
        set(ACTIVITY_TYPE_CODE_ID, DEFAULT_ACTIVITY_TYPE);
        set(STATUS_CODE_ID, DEFAULT_STATUS);
        set(TYPE_CODE_ID, DEFAULT_TYPE);
        set(TITLE_ID, DEFAULT_TITLE);
        set(SPONSOR_CODE_ID, DEFAULT_SPONSOR_CODE);
        set(PRIME_SPONSOR_CODE_ID, DEFAULT_SPONSOR_CODE);
        set(SPONSOR_AWARD_NUMBER_ID, DEFAULT_SPONSOR_AWARD_NUMBER);
        set(MODIFICATION_NUMBER_ID, DEFAULT_MODIFICATION_NUMBER);
        set(EFFECTIVE_DATE_ID, DEFAULT_BEGIN_DATE);
        set(CURRENT_FUND_EFFECTIVE_DATE_ID, DEFAULT_BEGIN_DATE);
        set(FINAL_EXPIRATION_DATE_ID, DEFAULT_END_DATE);
        set(OBLIGATION_EXPIRATION_DATE_ID, DEFAULT_MIDDLE_DATE);
        set(ANTICIPATED_AMOUNT_ID, DEFAULT_AMOUNT);
        set(OBLIGATED_AMOUNT_ID, DEFAULT_AMOUNT);
    }
    
    /**
     * Adds a default Sponsor Template.
     */
    public void addSponsorTemplate() {
        clickAwardHomePage();
        
        openTab(SPONSOR_TEMPLATE_TAB_ID);
        set(TEMPLATE_CODE_TAG, DEFAULT_SPONSOR_TEMPLATE_CODE);
        click(APPLY_AWARD_TEMPLATE_BUTTON);
        clickYesAnswer();
        clickYesAnswer();
    }
    
    /**
     * Adds a default Contact with 100% total credit split.
     */
    public void addContacts() {
        clickAwardContactsPage();

        openTab(KEY_PERSONNEL_AND_CREDIT_SPLIT_TAB_ID);
        set(PERSON_NAME_TAG, DEFAULT_PI_PERSON_NAME);
        set(CONTACT_ROLE_CODE_ID, DEFAULT_PI_CONTACT_ROLE);
        click(ADD_PERSON_BUTTON);

        for (int i = 0; i < 4; i++) {
            set(String.format(CREDIT_SPLITS_ID, i), DEFAULT_TOTAL_CREDIT_SPLIT);
            set(String.format(UNIT_CREDIT_SPLITS_ID, i), DEFAULT_TOTAL_CREDIT_SPLIT);
        }
    }
    
    /**
     * Adds default Custom Data.
     */
    public void addCustomData() {
        clickAwardCustomDataPage();
        
        openTab(PERSONNEL_ITEMS_FOR_REVIEW_TAB_ID);
        set(GRADUATE_STUDENT_COUNT_ID, DEFAULT_GRADUATE_STUDENT_COUNT);
        
        openTab(ASDF_TAB_ID);
        set(BILLING_ELEMENT_ID, DEFAULT_BILLING_ELEMENT);
    }
    
    /**
     * Adds a default Budget.
     */
    public void addBudget() {
        clickAwardBudgetVersionsPage();
        
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
        clickAwardBudgetVersionsPage();
        
        click(OPEN_BUDGET_BUTTON + String.valueOf(budgetNumber));
    }
    
    /**
     * Finalizes the Budget with the corresponding {@code budgetNumber}.
     * 
     * @param budgetNumber the index of the Budget to finalize
     */
    public void finalizeBudget(int budgetNumber) {
        clickAwardBudgetVersionsPage();
        
        click(String.format(FINAL_VERSION_FLAG_ID, budgetNumber));
        set(String.format(BUDGET_VERSION_ID, budgetNumber), DEFAULT_BUDGET_STATUS);
    }
    
    /**
     * Submits this Award.
     */
    public void submit() {
        clickAwardActionsPage();
        
        routeDocument();
        clickYesAnswer();
        assertRoute();
    }

}