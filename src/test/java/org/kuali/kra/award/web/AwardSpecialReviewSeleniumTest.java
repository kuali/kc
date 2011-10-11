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
package org.kuali.kra.award.web;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

public class AwardSpecialReviewSeleniumTest extends KcSeleniumTestBase {

    private static final String TABLE_ID = "specialReviewTableId";
    private static final String ERROR_PANEL_ID = "tab-SpecialReview-div";

    private static final String HELPER_PREFIX = "specialReviewHelper.newSpecialReview.";
    private static final String LIST_PREFIX = "document.awardList[0].specialReviews[%d].";
    
    private static final String SPECIAL_REVIEW_TYPE_CODE_ID = "specialReviewTypeCode";
    private static final String APPROVAL_TYPE_CODE_ID = "approvalTypeCode";
    private static final String PROTOCOL_NUMBER_ID = "protocolNumber";
    private static final String APPLICATION_DATE_ID = "applicationDate";
    private static final String APPROVAL_DATE_ID = "approvalDate";
    private static final String EXPIRATION_DATE_ID = "expirationDate";
    private static final String EXEMPTION_TYPE_CODES_ID = "exemptionTypeCodes";
    private static final String COMMENTS_ID = "comments";
    private static final String HELPER_SPECIAL_REVIEW_TYPE_CODE_ID = HELPER_PREFIX + SPECIAL_REVIEW_TYPE_CODE_ID;
    private static final String HELPER_APPROVAL_TYPE_CODE_ID = HELPER_PREFIX + APPROVAL_TYPE_CODE_ID;
    private static final String HELPER_PROTOCOL_NUMBER_ID = HELPER_PREFIX + PROTOCOL_NUMBER_ID;
    private static final String HELPER_APPLICATION_DATE_ID = HELPER_PREFIX + APPLICATION_DATE_ID;
    private static final String HELPER_APPROVAL_DATE_ID = HELPER_PREFIX + APPROVAL_DATE_ID;
    private static final String HELPER_EXPIRATION_DATE_ID = HELPER_PREFIX + EXPIRATION_DATE_ID;
    private static final String HELPER_EXEMPTION_TYPE_CODES_ID = HELPER_PREFIX + EXEMPTION_TYPE_CODES_ID;
    private static final String HELPER_COMMENTS_ID = HELPER_PREFIX + COMMENTS_ID;
    private static final String LIST_SPECIAL_REVIEW_TYPE_CODE_ID = LIST_PREFIX + SPECIAL_REVIEW_TYPE_CODE_ID;
    private static final String LIST_APPROVAL_TYPE_CODE_ID = LIST_PREFIX + APPROVAL_TYPE_CODE_ID;
    private static final String LIST_PROTOCOL_NUMBER_ID = LIST_PREFIX + PROTOCOL_NUMBER_ID;
    private static final String LIST_APPLICATION_DATE_ID = LIST_PREFIX + APPLICATION_DATE_ID;
    private static final String LIST_APPROVAL_DATE_ID = LIST_PREFIX + APPROVAL_DATE_ID;
    private static final String LIST_EXPIRATION_DATE_ID = LIST_PREFIX + EXPIRATION_DATE_ID;
    private static final String LIST_EXEMPTION_TYPE_CODES_ID = LIST_PREFIX + EXEMPTION_TYPE_CODES_ID;
    private static final String LIST_COMMENTS_ID = LIST_PREFIX + COMMENTS_ID;
    
    private static final String SPECIAL_REVIEW_TYPE_NAME = "Animal Usage";
    private static final String APPROVAL_TYPE_APPROVED_NAME = "Approved";
    private static final String APPROVAL_TYPE_EXEMPT_NAME = "Exempt";
    private static final String PROTOCOL_NUMBER = "1000000001";
    private static final String APPLICATION_DATE = "01/01/2009";
    private static final String APPROVAL_DATE = "02/01/2009";
    private static final String EXPIRATION_DATE = "03/01/2009";
    private static final String COMMENTS = "This is a test";
    private static final String EXEMPTION_TYPE_E1_NAME = "E1";
    
    private static final String ADD_SPECIAL_REVIEW_BUTTON = "methodToCall.addSpecialReview";
    private static final String DELETE_SPECIAL_REVIEW_BUTTON = "methodToCall.deleteSpecialReview.line%d";
    
    private AwardSeleniumHelper helper;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        helper = AwardSeleniumHelper.instance(driver);
    }
    
    @After
    public void tearDown() throws Exception {
        helper = null;
        
        super.tearDown();
    }

    /**
     * Verify that we can successfully add an approved special review and then verify that it is indeed saved to the database.
     */
    @Test
    public void testAddApprovedSpecialReview() throws Exception {
        helper.createAward();
        helper.clickAwardSpecialReviewPage();
        
        setApprovedSpecialReviewFields();
        helper.click(ADD_SPECIAL_REVIEW_BUTTON);
        helper.assertNoPageErrors();
        
        checkRow(SPECIAL_REVIEW_TYPE_NAME, APPROVAL_TYPE_APPROVED_NAME, PROTOCOL_NUMBER, APPLICATION_DATE, APPROVAL_DATE, EXPIRATION_DATE, 
                 Collections.<String>emptyList(), COMMENTS, 0);
        
        helper.closeAndSearchDocument();
        helper.clickAwardSpecialReviewPage();
        checkRow(SPECIAL_REVIEW_TYPE_NAME, APPROVAL_TYPE_APPROVED_NAME, PROTOCOL_NUMBER, APPLICATION_DATE, APPROVAL_DATE, EXPIRATION_DATE, 
                 Collections.<String>emptyList(), COMMENTS, 0);
    }
    
    /**
     * Verify that we can successfully add an exempt special review and then verify that it is indeed saved to the database.
     */
    @Test
    public void testAddExemptSpecialReview() throws Exception {
        helper.createAward();
        helper.clickAwardSpecialReviewPage();
        
        setExemptSpecialReviewFields();
        helper.click(ADD_SPECIAL_REVIEW_BUTTON);
        helper.assertNoPageErrors();
        
        checkRow(SPECIAL_REVIEW_TYPE_NAME, APPROVAL_TYPE_EXEMPT_NAME, Constants.EMPTY_STRING, APPLICATION_DATE, Constants.EMPTY_STRING, 
                 EXPIRATION_DATE, Collections.singletonList(EXEMPTION_TYPE_E1_NAME), COMMENTS, 0);
        
        helper.closeAndSearchDocument();
        helper.clickAwardSpecialReviewPage();
        checkRow(SPECIAL_REVIEW_TYPE_NAME, APPROVAL_TYPE_EXEMPT_NAME, Constants.EMPTY_STRING, APPLICATION_DATE, Constants.EMPTY_STRING, 
                 EXPIRATION_DATE, Collections.singletonList(EXEMPTION_TYPE_E1_NAME), COMMENTS, 0);
    }
    
    /**
     * Verify that we can delete a special review entry and that the change is reflected in the database.
     */
    @Test
    public void testDeleteSpecialReview() throws Exception {
        helper.createAward();
        helper.clickAwardSpecialReviewPage();
        
        setApprovedSpecialReviewFields();
        helper.click(ADD_SPECIAL_REVIEW_BUTTON);
        helper.assertNoPageErrors();
        
        helper.closeAndSearchDocument();
        helper.clickAwardSpecialReviewPage();
        
        helper.click(String.format(DELETE_SPECIAL_REVIEW_BUTTON, 0));
        helper.clickYesAnswer();
        helper.assertTableRowCount(TABLE_ID, 3);
        
        helper.closeAndSearchDocument();
        helper.clickAwardSpecialReviewPage();
        helper.assertTableRowCount(TABLE_ID, 3);
    }
    
    /**
     * Try adding a special review without setting the type and approval status.  We should get back two errors.
     */
    @Test
    public void testAddErrorSpecialReview() throws Exception {
        helper.createAward();
        helper.clickAwardSpecialReviewPage();
        
        helper.click(ADD_SPECIAL_REVIEW_BUTTON);
        helper.assertErrorCount(ERROR_PANEL_ID, 2);
    }
    
    /**
     * Sets the fields for an approved special review.
     */
    private void setApprovedSpecialReviewFields() throws IOException {
        helper.set(HELPER_SPECIAL_REVIEW_TYPE_CODE_ID, SPECIAL_REVIEW_TYPE_NAME);
        helper.set(HELPER_APPROVAL_TYPE_CODE_ID, APPROVAL_TYPE_APPROVED_NAME);
        helper.set(HELPER_PROTOCOL_NUMBER_ID, PROTOCOL_NUMBER);
        helper.set(HELPER_APPLICATION_DATE_ID, APPLICATION_DATE);
        helper.set(HELPER_APPROVAL_DATE_ID, APPROVAL_DATE);
        helper.set(HELPER_EXPIRATION_DATE_ID, EXPIRATION_DATE);
        helper.set(HELPER_COMMENTS_ID, COMMENTS);
    }
    
    /**
     * Sets the fields for an exempt special review.
     */
    private void setExemptSpecialReviewFields() {
        helper.set(HELPER_SPECIAL_REVIEW_TYPE_CODE_ID, SPECIAL_REVIEW_TYPE_NAME);
        helper.set(HELPER_APPROVAL_TYPE_CODE_ID, APPROVAL_TYPE_EXEMPT_NAME);
        helper.set(HELPER_APPLICATION_DATE_ID, APPLICATION_DATE);
        helper.set(HELPER_EXPIRATION_DATE_ID, EXPIRATION_DATE);
        helper.set(HELPER_EXEMPTION_TYPE_CODES_ID, EXEMPTION_TYPE_E1_NAME);
        helper.set(HELPER_COMMENTS_ID, COMMENTS);
    }
    
    /**
     * Checks an added row to verify it contains the correct data.
     * 
     * @param type the expected type
     * @param approvalStatus the expected approval status
     * @param protocolNumber the expected protocol number
     * @param applicationDate the expected application date
     * @param approvalDate the expected approval date
     * @param expirationDate the expected expiration date
     * @param exemptionTypeCodes the expected exemption type codes
     * @param comments the expected comments
     * @param index the index to check
     */
    private void checkRow(String type, String approvalStatus, String protocolNumber, String applicationDate, String approvalDate, String expirationDate, 
                          List<String> exemptionTypeCodes, String comments, int index) {
        helper.assertElementContains(String.format(LIST_SPECIAL_REVIEW_TYPE_CODE_ID, index), type);
        helper.assertElementContains(String.format(LIST_APPROVAL_TYPE_CODE_ID, index), approvalStatus);
        helper.assertElementContains(String.format(LIST_PROTOCOL_NUMBER_ID, index), protocolNumber);
        helper.assertElementContains(String.format(LIST_APPLICATION_DATE_ID, index), applicationDate);
        helper.assertElementContains(String.format(LIST_APPROVAL_DATE_ID, index), approvalDate);
        helper.assertElementContains(String.format(LIST_EXPIRATION_DATE_ID, index), expirationDate);
        for (String exemptionTypeCode : exemptionTypeCodes) {
            helper.assertSelectedOptionsContain(String.format(LIST_EXEMPTION_TYPE_CODES_ID, index), exemptionTypeCode);
        }
        helper.assertElementContains(String.format(LIST_COMMENTS_ID, index), comments);
    }

}