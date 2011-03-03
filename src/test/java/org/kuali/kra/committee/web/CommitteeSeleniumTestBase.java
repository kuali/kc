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
package org.kuali.kra.committee.web;

import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

/**
 * Base class for all integration tests for Committees.
 */
public class CommitteeSeleniumTestBase extends KcSeleniumTestBase {
    
    private static final String PAGE_TITLE = "Kuali :: Committee Document";
    
    private static final String CREATE_COMMITTEE_LINK_NAME = "Create Committee";
    private static final String COMMITTEE_LINK_NAME = "committee";
    private static final String SCHEDULE_LINK_NAME = "committeeSchedule";
    private static final String MEMBERS_LINK_NAME = "committeeMembership";
    private static final String ACTIONS_LINK_NAME = "committeeActions";
    
    private static final String DOCUMENT_DESCRIPTION_ID = "document.documentHeader.documentDescription";
    private static final String COMMITTEE_LIST_PREFIX = "document.committeeList[0].";
    private static final String COMMITTEE_TYPE_CODE_ID = COMMITTEE_LIST_PREFIX + "committeeTypeCode";
    private static final String COMMITTEE_MAX_PROTOCOLS_ID = COMMITTEE_LIST_PREFIX + "maxProtocols";
    private static final String COMMITTEE_HOME_UNIT_NUMBER_ID = COMMITTEE_LIST_PREFIX + "homeUnitNumber";
    private static final String COMMITTEE_MIN_MEMBERS_REQUIRED_ID = COMMITTEE_LIST_PREFIX + "minimumMembersRequired";
    private static final String COMMITTEE_NAME_ID = COMMITTEE_LIST_PREFIX + "committeeName";
    private static final String COMMITTEE_ADV_SUBMISSION_DAYS_REQUIRED_ID = COMMITTEE_LIST_PREFIX + "advancedSubmissionDaysRequired";
    private static final String COMMITTEE_REVIEW_TYPE_CODE_ID = COMMITTEE_LIST_PREFIX + "reviewTypeCode";
    private static final String COMMITTEE_ID_ID = COMMITTEE_LIST_PREFIX + "committeeId";
    private static final String COMMITTEE_DESCRIPTION_ID = COMMITTEE_LIST_PREFIX + "committeeDescription";
    private static final String COMMITTEE_SCHEDULE_DESCRIPTION_ID = COMMITTEE_LIST_PREFIX + "scheduleDescription";
    
    private static final String DEFAULT_DOCUMENT_DESCRIPTION = "Committee Web Test";
    private static final String DEFAULT_TYPE = "IRB";
    private static final String DEFAULT_MAX_PROTOCOLS = "10";
    private static final String DEFAULT_HOME_UNIT_NUMBER = "000001";
    private static final String DEFAULT_MIN_MEMBERS_REQUIRED = "3";
    private static final String DEFAULT_NAME = "Committee Test ";
    private static final String DEFAULT_ADV_SUBMISSION_DAYS_REQUIRED = "1";
    private static final String DEFAULT_REVIEW_TYPE = "FULL";
    private static final String DEFAULT_DESCRIPTION = "xxx";
    private static final String DEFAULT_SCHEDULE_DESCRIPTION = "foo";
    private static final String DEFAULT_RESEARCH_AREA_CODE = "000001";
    
    /**
     * @see org.kuali.kra.KraWebTestBase#getLoginUserName()
     */
    protected String getLoginUserName() {
        return "chew";
    }
        
    /**
     * Creates a new instance of the Committee page, filling in all required values, and saving.
     */
    protected final void createCommittee() {
        clickCentralAdminTab();
        
        click(CREATE_COMMITTEE_LINK_NAME);
        assertTitleContains(PAGE_TITLE);
        
        setDefaultRequiredFields();
        
        saveDocument();
        assertSave();
    }
    
    /**
     * Sets the Committee's required fields to legal default values.
     */
    protected void setDefaultRequiredFields() {
        String committeeId = getNextCommitteeID();
        
        set(DOCUMENT_DESCRIPTION_ID, DEFAULT_DOCUMENT_DESCRIPTION);
        set(COMMITTEE_TYPE_CODE_ID, DEFAULT_TYPE);
        set(COMMITTEE_MAX_PROTOCOLS_ID, DEFAULT_MAX_PROTOCOLS);
        set(COMMITTEE_HOME_UNIT_NUMBER_ID, DEFAULT_HOME_UNIT_NUMBER);
        set(COMMITTEE_REVIEW_TYPE_CODE_ID, DEFAULT_REVIEW_TYPE);
        set(COMMITTEE_NAME_ID, DEFAULT_NAME + committeeId);
        set(COMMITTEE_MIN_MEMBERS_REQUIRED_ID, DEFAULT_MIN_MEMBERS_REQUIRED);
        set(COMMITTEE_ADV_SUBMISSION_DAYS_REQUIRED_ID, DEFAULT_ADV_SUBMISSION_DAYS_REQUIRED);
        set(COMMITTEE_ID_ID, committeeId);
        set(COMMITTEE_DESCRIPTION_ID, DEFAULT_DESCRIPTION);
        set(COMMITTEE_SCHEDULE_DESCRIPTION_ID, DEFAULT_SCHEDULE_DESCRIPTION);
        
        lookupResearchArea(DEFAULT_RESEARCH_AREA_CODE);
    }
    
    /**
     * This method returns the committee ID for the next committee that is created.
     *  
     * @return String - containing the next available committee ID
     */
    protected String getNextCommitteeID() {
        return String.valueOf(System.currentTimeMillis());
    }
    
    /**
     * Navigate to the Committee Committee page.
     */
    protected void clickCommitteeCommitteePage() {
        click(COMMITTEE_LINK_NAME);
    }
    
    /**
     * Navigate to the Committee Members page.
     */
    protected void clickCommitteeMembersPage() {
        click(MEMBERS_LINK_NAME);
    }
    
    /**
     * Navigate to the Committee Schedule page.
     */
    protected void clickCommitteeSchedulePage() {
        click(SCHEDULE_LINK_NAME);
    }
    
    /**
     * Navigate to the Committee Actions page.
     */
    protected void clickCommitteeActionsPage() {
        click(ACTIONS_LINK_NAME);
    }
    
    protected void lookupResearchArea(String researchAreaCode) {
        multiLookup("committeeResearchAreas", "researchAreaCode", researchAreaCode);
    }
    
    protected void lookupEmployeeCommitteeMember(String personId) {
        lookup("committeeHelper.newCommitteeMembership.personId", "personId", personId);
    }
    
    protected void lookupNonEmployeeCommitteeMember(String personId) {
        lookup("committeeHelper.newCommitteeMembership.rolodexId", "rolodexId", personId);
    }

}