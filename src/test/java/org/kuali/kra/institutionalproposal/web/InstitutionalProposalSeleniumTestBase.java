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

import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

/**
 * Base class for all integration tests for Institutional Proposals.
 */
public abstract class InstitutionalProposalSeleniumTestBase extends KcSeleniumTestBase {
    
    private static final String PROPOSAL_LOG_PAGE_TITLE = "Kuali :: Proposal Log";
    
    private static final String CREATE_PROPOSAL_LOG_LINK_NAME = "Proposal Log";
    
    private static final String INSTITUTIONAL_PROPOSAL_PAGE_TITLE = "Kuali :: KC Institutional Proposal";
    
    private static final String CREATE_INSTITUTIONAL_PROPOSAL_LINK_NAME = "Institutional Proposal";
    private static final String HOME_LINK_NAME = "home";
    private static final String CONTACTS_LINK_NAME = "contacts";
    private static final String CUSTOM_DATA_LINK_NAME = "customData";
    private static final String SPECIAL_REVIEW_LINK_NAME = "specialReview";
    private static final String INTELLECTUAL_PROPERTY_REVIEW = "intellectualPropertyReview";
    private static final String DISTRIBUTION_LINK_NAME = "distribution";
    private static final String ACTIONS_LINK_NAME = "institutionalProposalActions";
    
    private static final String DOCUMENT_DESCRIPTION_ID = "document.documentHeader.documentDescription";
    
    private static final String PL_DEFAULT_DOCUMENT_DESCRIPTION = "Proposal Log Web Test";
    private static final String PL_LIST_PREFIX = "document.newMaintainableObject.";
    private static final String PL_PROPOSAL_TYPE_CODE_ID = PL_LIST_PREFIX + "proposalTypeCode";
    private static final String PL_TITLE = PL_LIST_PREFIX + "title";
    private static final String PL_PERSON_USER_NAME = PL_LIST_PREFIX + "person.userName";
    private static final String PL_LEAD_UNIT = PL_LIST_PREFIX + "leadUnit";
    
    private static final String IP_LIST_PREFIX = "document.institutionalProposalList[0].";
    private static final String IP_STATUS_CODE_ID = "document.institutionalProposal.statusCode";
    private static final String IP_ACTIVITY_TYPE_CODE_ID = IP_LIST_PREFIX + "activityTypeCode";
    private static final String IP_PROPOSAL_TYPE_CODE_ID = IP_LIST_PREFIX + "proposalTypeCode";
    private static final String IP_TITLE_ID = IP_LIST_PREFIX + "title";
    private static final String IP_SPONSOR_CODE_ID = IP_LIST_PREFIX + "sponsorCode";
    
    private static final String PL_DEFAULT_PROPSAL_TYPE = "New";
    private static final String PL_DEFAULT_TITLE = "Test Project";
    private static final String PL_DEFAULT_PERSON_USER_NAME = "majors";
    private static final String PL_DEFAULT_LEAD_UNIT = "000001";

    private static final String IP_DEFAULT_DOCUMENT_DESCRIPTION = "Institutional Proposal Web Test";
    private static final String IP_DEFAULT_STATUS = "Pending";
    private static final String IP_DEFAULT_PROPOSAL_TYPE = "New";
    private static final String IP_DEFAULT_ACTIVITY_TYPE = "Instruction";
    private static final String IP_DEFAULT_PROJECT_TITLE = "Test Project";
    private static final String IP_DEFAULT_SPONSOR_CODE = "005891";
    
    protected final void createProposalLog() {
        clickCentralAdminTab();
        
        click(CREATE_PROPOSAL_LOG_LINK_NAME);
        
        assertTitleContains(PROPOSAL_LOG_PAGE_TITLE);
        
        set(DOCUMENT_DESCRIPTION_ID, PL_DEFAULT_DOCUMENT_DESCRIPTION);
        set(PL_PROPOSAL_TYPE_CODE_ID, PL_DEFAULT_PROPSAL_TYPE);
        set(PL_TITLE, PL_DEFAULT_TITLE);
        set(PL_PERSON_USER_NAME, PL_DEFAULT_PERSON_USER_NAME);
        set(PL_LEAD_UNIT, PL_DEFAULT_LEAD_UNIT);
        
        blanketApproveDocument();
    }
        
    /**
     * Creates a new instance of the Institutional Proposal page, filling in all required values, and saving.
     */
    protected final void createInstitutionalProposal() {
        clickCentralAdminTab();
        
        createProposalLog();
        
        clickCentralAdminTab();
        
        lookupProposalLog();
        assertTitleContains(INSTITUTIONAL_PROPOSAL_PAGE_TITLE);
        
        setDefaultRequiredFields();
        
        saveDocument();
        assertSave();
    }
    
    /**
     * Looks up and returns the Proposal Log to be associated with a new Institutional Proposal.
     */
    protected final void lookupProposalLog() {    
        click(CREATE_INSTITUTIONAL_PROPOSAL_LINK_NAME);

        click("methodToCall.search");
        
        assertTableCellValue("row", 0, 0, "select");
        
        click("select", true);
    }
    
    /**
     * Sets the Institutional Proposal's required fields to legal default values.
     */
    protected void setDefaultRequiredFields() {
        set(DOCUMENT_DESCRIPTION_ID, IP_DEFAULT_DOCUMENT_DESCRIPTION);
        
        openTab("Institutional Proposal");
        set(IP_STATUS_CODE_ID, IP_DEFAULT_STATUS);
        set(IP_PROPOSAL_TYPE_CODE_ID, IP_DEFAULT_PROPOSAL_TYPE);
        set(IP_ACTIVITY_TYPE_CODE_ID, IP_DEFAULT_ACTIVITY_TYPE);
        set(IP_TITLE_ID, IP_DEFAULT_PROJECT_TITLE);
        
        openTab("Sponsor Program Information");
        set(IP_SPONSOR_CODE_ID, IP_DEFAULT_SPONSOR_CODE);
    }
    
    /**
     * Navigate to the Institutional Proposal Home page.
     */
    protected void clickInstitutionalProposalHomePage() {
        click(HOME_LINK_NAME);
    }
    
    /**
     * Navigate to the Institutional Proposal Contacts page.
     */
    protected void clickInstitutionalProposalContactsPage() {
        click(CONTACTS_LINK_NAME);
    }
    
    /**
     * Navigate to the Institutional Proposal Custom Data page.
     */
    protected void clickInstitutionalProposalCustomDataPage() {
        click(CUSTOM_DATA_LINK_NAME);
    }
    
    /**
     * Navigate to the Institutional Proposal Special Review page.
     */
    protected void clickInstitutionalProposalSpecialReviewPage() {
        click(SPECIAL_REVIEW_LINK_NAME);
    }
    
    /**
     * Navigate to the Institutional Proposal Intellectual Property Review page.
     */
    protected void clickInstitutionalProposalIntellectualPropertyReviewPage() {
        click(INTELLECTUAL_PROPERTY_REVIEW);
    }
    
    /**
     * Navigate to the Institutional Proposal Distribution page.
     */
    protected void clickInstitutionalProposalDistributionPage() {
        click(DISTRIBUTION_LINK_NAME);
    }
    
    /**
     * Navigate to the Institutional Proposal Actions page.
     */
    protected void clickInstitutionalProposalActionsPage() {
        click(ACTIONS_LINK_NAME);
    }

}