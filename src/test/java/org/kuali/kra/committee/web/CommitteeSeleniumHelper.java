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

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.test.infrastructure.KcSeleniumHelper;
import org.openqa.selenium.WebDriver;

/**
 * Provides methods for creating a Committee document, clicking on tabs, and populating fields.
 */
public class CommitteeSeleniumHelper extends KcSeleniumHelper {
    
    private static final String PAGE_TITLE = "Kuali :: Committee Document";
    
    private static final String CREATE_COMMITTEE_LINK_NAME = "Create Committee";
    private static final String COMMITTEE_LINK_NAME = "committee";
    private static final String SCHEDULE_LINK_NAME = "committeeSchedule";
    private static final String MEMBERS_LINK_NAME = "committeeMembership";
    private static final String ACTIONS_LINK_NAME = "committeeActions";
    
    private static final String RESEARCH_AREAS_TAG = "committeeResearchAreas";
    
    private static final String DOCUMENT_DESCRIPTION_ID = "document.documentHeader.documentDescription";
    private static final String LIST_PREFIX = "document.committeeList[0].";
    private static final String ID_ID = LIST_PREFIX + "committeeId";
    private static final String NAME_ID = LIST_PREFIX + "committeeName";
    private static final String HOME_UNIT_NUMBER_ID = LIST_PREFIX + "homeUnitNumber";
    private static final String TYPE_CODE_ID = LIST_PREFIX + "committeeTypeCode";
    private static final String DESCRIPTION_ID = LIST_PREFIX + "committeeDescription";
    private static final String SCHEDULE_DESCRIPTION_ID = LIST_PREFIX + "scheduleDescription";
    private static final String MINIMUM_MEMBERS_REQUIRED_ID = LIST_PREFIX + "minimumMembersRequired";
    private static final String REVIEW_TYPE_CODE_ID = LIST_PREFIX + "reviewTypeCode";
    private static final String MAX_PROTOCOLS_ID = LIST_PREFIX + "maxProtocols";
    private static final String ADVANCED_SUBMISSION_DAYS_REQUIRED_ID = LIST_PREFIX + "advancedSubmissionDaysRequired";
    private static final String RESEARCH_AREA_CODE_ID = "researchAreaCode";
    
    private static final String DEFAULT_DOCUMENT_DESCRIPTION = "Committee Web Test";
    private static final String DEFAULT_NAME = " Committee";
    private static final String DEFAULT_HOME_UNIT_NUMBER = "000001";
    private static final String DEFAULT_TYPE = "IRB";
    private static final String DEFAULT_DESCRIPTION = "xxx";
    private static final String DEFAULT_SCHEDULE_DESCRIPTION = "foo";
    private static final String DEFAULT_MINIMUM_MEMBERS_REQUIRED = "3";
    private static final String DEFAULT_REVIEW_TYPE = "Full";
    private static final String DEFAULT_MAX_PROTOCOLS = "10";
    private static final String DEFAULT_ADVANCED_SUBMISSION_DAYS_REQUIRED = "1";
    private static final String DEFAULT_RESEARCH_AREA_CODE = "000001";
    
    private static CommitteeSeleniumHelper helper;
    
    private String currentCommitteeId = Constants.EMPTY_STRING;
    
    public static CommitteeSeleniumHelper instance(WebDriver driver) {
        if (helper == null) {
            helper = new CommitteeSeleniumHelper(driver);
        }
        return helper;
    }
    
    private CommitteeSeleniumHelper(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Returns the current committee ID.
     * 
     * @return the current committee ID
     */
    public final String getCurrentCommitteeID() {
        return currentCommitteeId;
    }
    
    /**
     * This method returns the committee ID for the next committee that is created.
     *  
     * @return String - containing the next available committee ID
     */
    private String getNextCommitteeID() {
        return String.valueOf(System.currentTimeMillis());
    }
    
    /**
     * Navigate to the Committee Committee page.
     */
    public void clickCommitteeCommitteePage() {
        click(COMMITTEE_LINK_NAME);
    }
    
    /**
     * Navigate to the Committee Members page.
     */
    public void clickCommitteeMembersPage() {
        click(MEMBERS_LINK_NAME);
    }
    
    /**
     * Navigate to the Committee Schedule page.
     */
    public void clickCommitteeSchedulePage() {
        click(SCHEDULE_LINK_NAME);
    }
    
    /**
     * Navigate to the Committee Actions page.
     */
    public void clickCommitteeActionsPage() {
        click(ACTIONS_LINK_NAME);
    }
    
    /**
     * Creates a new instance of the Committee page, filling in all required values, and saving.
     */
    public final void createCommittee() {
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
    private void setDefaultRequiredFields() {
        currentCommitteeId = getNextCommitteeID();
        
        set(DOCUMENT_DESCRIPTION_ID, DEFAULT_DOCUMENT_DESCRIPTION);
        set(ID_ID, currentCommitteeId);
        set(NAME_ID, currentCommitteeId + DEFAULT_NAME);
        set(HOME_UNIT_NUMBER_ID, DEFAULT_HOME_UNIT_NUMBER);
        set(TYPE_CODE_ID, DEFAULT_TYPE);
        set(DESCRIPTION_ID, DEFAULT_DESCRIPTION);
        set(SCHEDULE_DESCRIPTION_ID, DEFAULT_SCHEDULE_DESCRIPTION);
        set(MINIMUM_MEMBERS_REQUIRED_ID, DEFAULT_MINIMUM_MEMBERS_REQUIRED);
        set(REVIEW_TYPE_CODE_ID, DEFAULT_REVIEW_TYPE);
        set(MAX_PROTOCOLS_ID, DEFAULT_MAX_PROTOCOLS);
        set(ADVANCED_SUBMISSION_DAYS_REQUIRED_ID, DEFAULT_ADVANCED_SUBMISSION_DAYS_REQUIRED);
        multiLookup(RESEARCH_AREAS_TAG, RESEARCH_AREA_CODE_ID, DEFAULT_RESEARCH_AREA_CODE);
    }

}