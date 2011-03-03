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
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

/**
 * Base class for all integration tests for Awards.
 */
public abstract class AwardSeleniumTestBase extends KcSeleniumTestBase {
    
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
    
    private static final String DOCUMENT_DESCRIPTION_ID = "document.documentHeader.documentDescription";
    private static final String AWARD_ID_PREFIX = "document.awardList[0].";
    private static final String AWARD_TYPE_ID = AWARD_ID_PREFIX + "awardTypeCode";
    private static final String AWARD_TITLE_ID = AWARD_ID_PREFIX + "title";
    private static final String UNIT_NUMBER = AWARD_ID_PREFIX + "unitNumber";
    private static final String PREF_SPONSOR_CODE_ID = AWARD_ID_PREFIX + "primeSponsorCode";
    private static final String SPONSOR_CODE_ID = AWARD_ID_PREFIX + "sponsorCode";
    private static final String STATUS_CODE_ID = AWARD_ID_PREFIX + "statusCode";
    private static final String MOD_NUMBER_ID = AWARD_ID_PREFIX + "modificationNumber";
    private static final String SPONSOR_AWARD_NUMBER_ID = AWARD_ID_PREFIX + "sponsorAwardNumber";
    private static final String AWARD_EXEC_DATE_ID = AWARD_ID_PREFIX + "awardAmountInfos[0].obligationExpirationDate";
    private static final String AWARD_EFF_DATE_ID = AWARD_ID_PREFIX + "awardAmountInfos[0].currentFundEffectiveDate";
    private static final String ACTIVITY_TYPE_CODE_ID = AWARD_ID_PREFIX + "activityTypeCode";
    private static final String BEGIN_DATE_ID = AWARD_ID_PREFIX + "beginDate";
    private static final String PROJECT_END_DATE_ID = AWARD_ID_PREFIX + "awardAmountInfos[0].finalExpirationDate";
    private static final String AWARD_TRANSACTION_TYPE_CODE = AWARD_ID_PREFIX + "awardTransactionTypeCode";
    
    private static final String DEFAULT_DOCUMENT_DESCRIPTION = "Award Development Web Test";
    private static final String ONE = "1";
    private static final String AWARD_TITLE = "Award Title";
    private static final String GOOGLE_SPONSOR_CODE = "005979";
    private static final String SPONSOR_AWARD_NUMBER = "1R01CA123456";
    private static final String DATE_VALUE = "03/01/2009";
    private static final String DATE_VALUE_AFTER = "04/01/2009";
    private static final String END_DATE_VALUE = "09/01/2010";
        
    /**
     * Creates a new instance of the Award, filling in all required fields, and saving. 
     */
    protected final void createAward() {
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
        set(AWARD_TYPE_ID, ONE);
        set(AWARD_TITLE_ID, AWARD_TITLE);
        set(PREF_SPONSOR_CODE_ID, GOOGLE_SPONSOR_CODE);
        set(STATUS_CODE_ID, ONE);
        set(SPONSOR_CODE_ID, GOOGLE_SPONSOR_CODE);
        set(MOD_NUMBER_ID, ONE);
        set(SPONSOR_AWARD_NUMBER_ID, SPONSOR_AWARD_NUMBER);
        set(AWARD_EXEC_DATE_ID, DATE_VALUE_AFTER);
        set(AWARD_EFF_DATE_ID, DATE_VALUE);
        set(ACTIVITY_TYPE_CODE_ID, ONE);
        set(BEGIN_DATE_ID, DATE_VALUE);
        set(PROJECT_END_DATE_ID, END_DATE_VALUE);
        set(AWARD_TRANSACTION_TYPE_CODE, ONE);
        set(UNIT_NUMBER, AwardFixtureFactory.UNIVERSITY_UNIT_NUMBER);
    }
    
    /**
     * Navigate to the Award Home page.
     */
    protected void clickAwardHomePage() {
        click(HOME_LINK_NAME);
    }
    
    /**
     * Navigate to the Award Contacts page.
     */
    protected void clickAwardContactsPage() {
        click(CONTACTS_LINK_NAME);
    }
    
    /**
     * Navigate to the Award Commitments page.
     */
    protected void clickAwardCommitmentsPage() {
        click(COMMITMENTS_LINK_NAME);
    }
    
    /**
     * Navigate to the Award Budget Versions page.
     */
    protected void clickAwardBudgetVersionsPage() {
        click(BUDGET_VERSIONS_LINK_NAME);
    }
    
    /**
     * Navigate to the Award Payment, Reports & Terms page.
     */
    protected void clickAwardPaymentReportsAndTermsPage() {
        click(PAYMENT_REPORTS_AND_TERMS_LINK_NAME);
    }
    
    /**
     * Navigate to the Award Special Review page.
     */
    protected void clickAwardSpecialReviewPage() {
        click(SPECIAL_REVIEW_LINK_NAME);
    }
    
    /**
     * Navigate to the Award Custom Data page.
     */
    protected void clickAwardCustomDataPage() {
        click(CUSTOM_DATA_LINK_NAME);
    }
    
    /**
     * Navigate to the Award Comments, Notes & Attachments page.
     */
    protected void clickAwardCommentsNotesAndAttachmentsPage() {
        click(COMMENTS_NOTES_AND_ATTACHMENTS_LINK_NAME);
    }
    
    /**
     * Navigate to the Award Actions page.
     */
    protected void clickAwardActionsPage() {
        click(ACTIONS_LINK_NAME);
    }
    
    /**
     * Navigate to the Award Medusa page.
     */
    protected void clickAwardMedusaPage() {
        click(MEDUSA_LINK_NAME);
    }

}