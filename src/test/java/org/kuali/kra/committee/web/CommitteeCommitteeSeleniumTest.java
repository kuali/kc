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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

/**
 * Tests the Committee page of a Committee.
 */
public class CommitteeCommitteeSeleniumTest extends KcSeleniumTestBase {
    
    private static final String ERROR_PANEL_ID = "tab-Committee-div";
    
    private static final String LIST_PREFIX = "document.committeeList[0].";
    
    private static final String DOCUMENT_DESCRIPTION_ID = "document.documentHeader.documentDescription";
    private static final String COMMITTEE_ID_ID = "committeeId";
    private static final String COMMITTEE_NAME_ID = "committeeName";
    private static final String HOME_UNIT_NUMBER_ID = "homeUnitNumber";
    private static final String COMMITTEE_TYPE_CODE_ID = "committeeTypeCode";
    private static final String COMMITTEE_DESCRIPTION_ID = "committeeDescription";
    private static final String SCHEDULE_DESCRIPTION_ID = "scheduleDescription";
    private static final String MINIMUM_MEMBERS_REQUIRED_ID = "minimumMembersRequired";
    private static final String REVIEW_TYPE_CODE_ID = "reviewTypeCode";
    private static final String MAX_PROTOCOLS_ID = "maxProtocols";
    private static final String ADVANCED_SUBMISSION_REQUIRED_ID = "advancedSubmissionDaysRequired";
    
    private static final String LIST_COMMITEE_ID_ID = LIST_PREFIX + COMMITTEE_ID_ID;
    private static final String LIST_COMMITTEE_NAME_ID = LIST_PREFIX + COMMITTEE_NAME_ID;
    private static final String LIST_HOME_UNIT_NUMBER_ID = LIST_PREFIX + HOME_UNIT_NUMBER_ID;
    private static final String LIST_COMMITTEE_TYPE_CODE_ID = LIST_PREFIX + COMMITTEE_TYPE_CODE_ID;
    private static final String LIST_COMMITTEE_DESCRIPTION_ID = LIST_PREFIX + COMMITTEE_DESCRIPTION_ID;
    private static final String LIST_SCHEDULE_DESCRIPTION_ID = LIST_PREFIX + SCHEDULE_DESCRIPTION_ID;
    private static final String LIST_MINIMUM_MEMBERS_REQUIRED_ID = LIST_PREFIX + MINIMUM_MEMBERS_REQUIRED_ID;
    private static final String LIST_REVIEW_TYPE_CODE_ID = LIST_PREFIX + REVIEW_TYPE_CODE_ID;
    
    private static final String LIST_MAX_PROTOCOLS_ID = LIST_PREFIX + MAX_PROTOCOLS_ID;
    private static final String LIST_ADVANCED_SUBMISSION_DAYS_REQUIRED_ID = LIST_PREFIX + ADVANCED_SUBMISSION_REQUIRED_ID;
    
    private static final String DEFAULT_DOCUMENT_DESCRIPTION = "Committee Web Test";
    private static final String DEFAULT_COMMITTEE_NAME = " Committee";
    private static final String DEFAULT_HOME_UNIT_NUMBER = "000001";
    private static final String DEFAULT_COMMITTEE_TYPE_CODE_NAME = "IRB";
    private static final String DEFAULT_COMMITTEE_DESCRIPTION = "xxx";
    private static final String DEFAULT_SCHEDULE_DESCRIPTION = "foo";
    private static final String DEFAULT_MINIMUM_MEMBERS_REQUIRED = "3";
    private static final String DEFAULT_REVIEW_TYPE_CODE_NAME = "Full";
    private static final String DEFAULT_MAX_PROTOCOLS = "10";
    private static final String DEFAULT_ADVANCED_SUBMISSION_DAYS_REQUIRED = "1";
    
    private static final String HOME_UNIT_NUMBER = "xxx";

    private static final String DESCRIPTION_VALUE_1 = "Description text";
    private static final String DESCRIPTION_VALUE_2 = "Another description text";
    
    private static final String ERROR_INVALID_UNIT = "xxx is not a valid Unit";
    
    private CommitteeSeleniumHelper helper;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        helper = CommitteeSeleniumHelper.instance(driver);
    }
    
    @After
    public void tearDown() throws Exception {
        helper = null;
        
        super.tearDown();
    }

    /**
     * Tests the default fields for a committee.
     * 
     * @throws Exception
     */
    @Test
    public void testDefaultFields() throws Exception {
        helper.createCommittee();
        helper.assertNoPageErrors();
        
        helper.closeAndSearchDocument();
        
        helper.assertElementContains(DOCUMENT_DESCRIPTION_ID, DEFAULT_DOCUMENT_DESCRIPTION);
        helper.assertElementContains(LIST_COMMITEE_ID_ID, helper.getCurrentCommitteeID());
        helper.assertElementContains(LIST_COMMITTEE_NAME_ID, helper.getCurrentCommitteeID() + DEFAULT_COMMITTEE_NAME);
        helper.assertElementContains(LIST_HOME_UNIT_NUMBER_ID, DEFAULT_HOME_UNIT_NUMBER);
        helper.assertElementContains(LIST_COMMITTEE_TYPE_CODE_ID, DEFAULT_COMMITTEE_TYPE_CODE_NAME);
        helper.assertElementContains(LIST_COMMITTEE_DESCRIPTION_ID, DEFAULT_COMMITTEE_DESCRIPTION);
        helper.assertElementContains(LIST_SCHEDULE_DESCRIPTION_ID, DEFAULT_SCHEDULE_DESCRIPTION);
        helper.assertElementContains(LIST_MINIMUM_MEMBERS_REQUIRED_ID, DEFAULT_MINIMUM_MEMBERS_REQUIRED);
        helper.assertElementContains(LIST_REVIEW_TYPE_CODE_ID, DEFAULT_REVIEW_TYPE_CODE_NAME);
        helper.assertElementContains(LIST_MAX_PROTOCOLS_ID, DEFAULT_MAX_PROTOCOLS);
        helper.assertElementContains(LIST_ADVANCED_SUBMISSION_DAYS_REQUIRED_ID, DEFAULT_ADVANCED_SUBMISSION_DAYS_REQUIRED);
    }
    
    /**
     * Tests that an invalid home unit number will result in an error message.
     * 
     * @throws Exception
     */
    @Test
    public void testInvalidHomeUnit() throws Exception {
        helper.createCommittee();
        helper.assertNoPageErrors();
        
        helper.set(LIST_HOME_UNIT_NUMBER_ID, HOME_UNIT_NUMBER);
        
        helper.saveDocument();
        helper.assertError(ERROR_PANEL_ID, ERROR_INVALID_UNIT);
    }
    
    /**
     * Tests the expanded text for the committee description.
     *
     * @throws Exception
     */
    @Test
    public void testCommitteeDescriptionExpandedTextArea() throws Exception {
        helper.createCommittee();
        
        helper.assertExpandedTextArea(LIST_COMMITTEE_DESCRIPTION_ID, DESCRIPTION_VALUE_1, DESCRIPTION_VALUE_2);
    }
    
    /**
     * Tests the expanded text for the schedule description.
     *
     * @throws Exception
     */
    @Test
    public void testScheduleDescriptionExpandedTextArea() throws Exception {
        helper.createCommittee();
        
        helper.assertExpandedTextArea(SCHEDULE_DESCRIPTION_ID, DESCRIPTION_VALUE_1, DESCRIPTION_VALUE_2);
    }

}