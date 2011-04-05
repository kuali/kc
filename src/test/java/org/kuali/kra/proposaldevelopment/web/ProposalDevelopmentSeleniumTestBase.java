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

import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

/**
 * Base class for all integration tests for Proposal Developments.
 */
public class ProposalDevelopmentSeleniumTestBase extends KcSeleniumTestBase {
    
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
    
    private static final String DOCUMENT_DESCRIPTION_ID = "document.documentHeader.documentDescription";
    private static final String LIST_PREFIX = "document.developmentProposalList[0].";
    private static final String SPONSOR_CODE_ID = LIST_PREFIX + "sponsorCode";
    private static final String TYPE_CODE_ID = LIST_PREFIX + "proposalTypeCode";
    private static final String REQUESTED_START_DATE_ID = LIST_PREFIX + "requestedStartDateInitial";
    private static final String OWNED_BY_UNIT_ID = LIST_PREFIX + "ownedByUnitNumber";
    private static final String REQUESTED_END_DATE_ID = LIST_PREFIX + "requestedEndDateInitial";
    private static final String ACTIVITY_TYPE_CODE_ID = LIST_PREFIX + "activityTypeCode";
    private static final String TITLE_ID = LIST_PREFIX + "title";
    
    private static final String DEFAULT_DOCUMENT_DESCRIPTION = "Proposal Development Web Test";
    private static final String DEFAULT_SPONSOR_CODE = "005770";
    private static final String DEFAULT_TYPE_CODE = "New";
    private static final String DEFAULT_REQUESTED_START_DATE = "08/14/2007";
    private static final String DEFAULT_OWNED_BY_UNIT = "000001 - University";
    private static final String DEFAULT_REQUESTED_END_DATE = "08/21/2007";
    private static final String DEFAULT_ACTIVITY_TYPE = "Instruction";
    private static final String DEFAULT_TITLE = "Project title";
    
    private static final String SUBMIT_TO_SPONSOR_BUTTON = "methodToCall.submitToSponsor";
        
    /**
     * Creates a new instance of the Proposal Development page, filling in all required values, and saving.
     */
    protected final void createProposalDevelopment() {
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
    protected void setDefaultRequiredFields() {
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
     * Navigate to the Proposal Development Proposal page.
     */
    protected void clickProposalDevelopmentProposalPage() {
        click(PROPOSAL_LINK_NAME);
    }
    
    /**
     * Navigate to the Proposal Development Grants.gov page.
     */
    protected void clickProposalDevelopmentGrantsGovPage() {
        click(GRANTS_GOV_LINK_NAME);
    }
    
    /**
     * Navigate to the Proposal Development Key Personnel page.
     */
    protected void clickProposalDevelopmentKeyPersonnelPage() {
        click(KEY_PERSONNEL_LINK_NAME);
    }
    
    /**
     * Navigate to the Proposal Development Special Review page.
     */
    protected void clickProposalDevelopmentSpecialReviewPage() {
        click(SPECIAL_REVIEW_LINK_NAME);
    }
    
    /**
     * Navigate to the Proposal Development Custom Data page.
     */
    protected void clickProposalDevelopmentCustomDataPage() {
        click(CUSTOM_DATA_LINK_NAME);
    }
    
    /**
     * Navigate to the Proposal Development Abstracts and Attachments page.
     */
    protected void clickProposalDevelopmentAbstractsAndAttachmentsPage() {
        click(ABSTRACTS_AND_ATTACHMENTS_LINK_NAME);
    }
    
    /**
     * Navigate to the Proposal Development Questions page.
     */
    protected void clickProposalDevelopmentQuestionsPage() {
        click(QUESTIONS_LINK_NAME);
    }
    
    /**
     * Navigate to the Proposal Development Budget Versions page.
     */
    protected void clickProposalDevelopmentBudgetVersionsPage() {
        click(BUDGET_VERSIONS_LINK_NAME);
    }
    
    /**
     * Navigate to the Proposal Development Permissions page.
     */
    protected void clickProposalDevelopmentPermissionsPage() {
        click(PERMISSIONS_LINK_NAME);
    }
    
    /**
     * Navigate to the Proposal Development Actions page.
     */
    protected void clickProposalDevelopmentActionsPage() {
        click(ACTIONS_LINK_NAME);
    }
    
    /**
     * Submit this Proposal Development to the Sponsor.
     */
    protected void submitToSponsor() {
        click(SUBMIT_TO_SPONSOR_BUTTON);
    }

}