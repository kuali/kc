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

import org.kuali.kra.test.infrastructure.KcSeleniumHelper;
import org.openqa.selenium.WebDriver;

/**
 * Provides methods for creating an Institutional Development document, clicking on tabs, and populating fields.
 */
public class InstitutionalProposalSeleniumHelper extends KcSeleniumHelper {
    
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
    
    private static final String INSTITUTIONAL_PROPOSAL_TAB_ID = "Institutional Proposal";
    private static final String SPONSOR_PROGRAM_INFORMATION_TAB_ID = "Sponsor Program Information";
    private static final String PERSONNEL_ITEMS_FOR_REVIEW_TAB_ID = "Personnel Items for Review";
    private static final String ASDF_TAB_ID = "asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf";
    
    private static final String DOCUMENT_DESCRIPTION_ID = "document.documentHeader.documentDescription";
    
    private static final String PL_DEFAULT_DOCUMENT_DESCRIPTION = "Proposal Log Web Test";
    private static final String PL_LIST_PREFIX = "document.newMaintainableObject.";
    private static final String PL_PROPOSAL_TYPE_CODE_ID = PL_LIST_PREFIX + "proposalTypeCode";
    private static final String PL_TITLE_ID = PL_LIST_PREFIX + "title";
    private static final String PL_PERSON_USER_NAME_ID = PL_LIST_PREFIX + "person.userName";
    private static final String PL_LEAD_UNIT_ID = PL_LIST_PREFIX + "leadUnit";
    
    private static final String IP_LIST_PREFIX = "document.institutionalProposalList[0].";
    private static final String IP_STATUS_CODE_ID = "document.institutionalProposal.statusCode";
    private static final String IP_ACTIVITY_TYPE_CODE_ID = IP_LIST_PREFIX + "activityTypeCode";
    private static final String IP_PROPOSAL_TYPE_CODE_ID = IP_LIST_PREFIX + "proposalTypeCode";
    private static final String IP_TITLE_ID = IP_LIST_PREFIX + "title";
    private static final String IP_SPONSOR_CODE_ID = IP_LIST_PREFIX + "sponsorCode";
    private static final String IP_CREDIT_SPLITS_ID = IP_LIST_PREFIX + "projectPersons[0].creditSplit[%d].credit";
    private static final String IP_UNIT_CREDIT_SPLITS_ID = IP_LIST_PREFIX + "projectPersons[0].unit[0].creditSplit[%d].credit";
    private static final String IP_GRADUATE_STUDENT_COUNT_ID = "institutionalProposalCustomDataFormHelper.customDataValues[3].value";
    private static final String IP_BILLING_ELEMENT_ID = "institutionalProposalCustomDataFormHelper.customDataValues[0].value";
    
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
    private static final String IP_DEFAULT_TOTAL_CREDIT_SPLIT = "100.00";
    private static final String IP_DEFAULT_GRADUATE_STUDENT_COUNT = "5";
    private static final String IP_DEFAULT_BILLING_ELEMENT = "College";
    
    private static InstitutionalProposalSeleniumHelper helper;
    
    public static InstitutionalProposalSeleniumHelper instance(WebDriver driver) {
        if (helper == null) {
            helper = new InstitutionalProposalSeleniumHelper(driver);
        }
        return helper;
    }
    
    private InstitutionalProposalSeleniumHelper(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Navigate to the Institutional Proposal Home page.
     */
    public void clickInstitutionalProposalHomePage() {
        click(HOME_LINK_NAME);
    }
    
    /**
     * Navigate to the Institutional Proposal Contacts page.
     */
    public void clickInstitutionalProposalContactsPage() {
        click(CONTACTS_LINK_NAME);
    }
    
    /**
     * Navigate to the Institutional Proposal Custom Data page.
     */
    public void clickInstitutionalProposalCustomDataPage() {
        click(CUSTOM_DATA_LINK_NAME);
    }
    
    /**
     * Navigate to the Institutional Proposal Special Review page.
     */
    public void clickInstitutionalProposalSpecialReviewPage() {
        click(SPECIAL_REVIEW_LINK_NAME);
    }
    
    /**
     * Navigate to the Institutional Proposal Intellectual Property Review page.
     */
    public void clickInstitutionalProposalIntellectualPropertyReviewPage() {
        click(INTELLECTUAL_PROPERTY_REVIEW);
    }
    
    /**
     * Navigate to the Institutional Proposal Distribution page.
     */
    public void clickInstitutionalProposalDistributionPage() {
        click(DISTRIBUTION_LINK_NAME);
    }
    
    /**
     * Navigate to the Institutional Proposal Actions page.
     */
    public void clickInstitutionalProposalActionsPage() {
        click(ACTIONS_LINK_NAME);
    }
    
    
    public final void createProposalLog() {
        clickCentralAdminTab();
        
        click(CREATE_PROPOSAL_LOG_LINK_NAME);
        
        assertTitleContains(PROPOSAL_LOG_PAGE_TITLE);
        
        set(DOCUMENT_DESCRIPTION_ID, PL_DEFAULT_DOCUMENT_DESCRIPTION);
        set(PL_PROPOSAL_TYPE_CODE_ID, PL_DEFAULT_PROPSAL_TYPE);
        set(PL_TITLE_ID, PL_DEFAULT_TITLE);
        set(PL_PERSON_USER_NAME_ID, PL_DEFAULT_PERSON_USER_NAME);
        set(PL_LEAD_UNIT_ID, PL_DEFAULT_LEAD_UNIT);
        
        blanketApproveDocument();
    }
        
    /**
     * Creates a new instance of the Institutional Proposal page, filling in all required values, and saving.
     */
    public final void createInstitutionalProposal() {
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
    public final void lookupProposalLog() {    
        click(CREATE_INSTITUTIONAL_PROPOSAL_LINK_NAME);

        click("methodToCall.search");
        
        assertTableCellValueContains("row", 0, 0, "select");
        
        click("select", true);
    }
    
    /**
     * Sets the Institutional Proposal's required fields to legal default values.
     */
    private void setDefaultRequiredFields() {
        set(DOCUMENT_DESCRIPTION_ID, IP_DEFAULT_DOCUMENT_DESCRIPTION);

        openTab(INSTITUTIONAL_PROPOSAL_TAB_ID);
        set(IP_STATUS_CODE_ID, IP_DEFAULT_STATUS);
        set(IP_PROPOSAL_TYPE_CODE_ID, IP_DEFAULT_PROPOSAL_TYPE);
        set(IP_ACTIVITY_TYPE_CODE_ID, IP_DEFAULT_ACTIVITY_TYPE);
        set(IP_TITLE_ID, IP_DEFAULT_PROJECT_TITLE);
        
        openTab(SPONSOR_PROGRAM_INFORMATION_TAB_ID);
        set(IP_SPONSOR_CODE_ID, IP_DEFAULT_SPONSOR_CODE);
    }
    
    /**
     * Modifies default Contact to have 100% total credit split.
     */
    public void addContacts() {
        clickInstitutionalProposalContactsPage();
        
        openTab(0);
        
        for (int i = 0; i < 4; i++) {
            set(String.format(IP_CREDIT_SPLITS_ID, i), IP_DEFAULT_TOTAL_CREDIT_SPLIT);
            set(String.format(IP_UNIT_CREDIT_SPLITS_ID, i), IP_DEFAULT_TOTAL_CREDIT_SPLIT);
        }
    }
    
    /**
     * Adds default Custom Data.
     */
    public void addCustomData() {
        clickInstitutionalProposalCustomDataPage();

        openTab(PERSONNEL_ITEMS_FOR_REVIEW_TAB_ID);
        set(IP_GRADUATE_STUDENT_COUNT_ID, IP_DEFAULT_GRADUATE_STUDENT_COUNT);
        
        openTab(ASDF_TAB_ID);
        set(IP_BILLING_ELEMENT_ID, IP_DEFAULT_BILLING_ELEMENT);
    }
    
    /**
     * Submits this Institutional Proposal.
     */
    public void submit() {
        clickInstitutionalProposalActionsPage();

        routeDocument();
        assertRoute();
    }

}