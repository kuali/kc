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
package org.kuali.kra.irb.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

public class ProtocolModifySubmissionActionSeleniumTest extends KcSeleniumTestBase {
    
    private static final String ACTION_TAB_ID = "Request an Action";
    private static final String SUBMIT_TAB_ID = ": Submit for Review";
    private static final String TAB_ID = ":Modify Submission Request";
    
    private static final String SUBMIT_HELPER_PREFIX = "actionHelper.protocolSubmitAction.";
    private static final String HELPER_PREFIX = "actionHelper.protocolModifySubmissionBean.";
    
    private static final String SUBMISSION_TYPE_CODE_ID = "submissionTypeCode";
    private static final String PROTOCOL_REVIEW_TYPE_CODE_ID = "protocolReviewTypeCode";
    private static final String BILLABLE_ID = "billable";
    private static final String SUBMISSION_HELPER_SUBMISSION_TYPE_CODE_ID = SUBMIT_HELPER_PREFIX + SUBMISSION_TYPE_CODE_ID;
    private static final String SUBMISSION_HELPER_PROTOCOL_REVIEW_TYPE_CODE_ID = SUBMIT_HELPER_PREFIX + PROTOCOL_REVIEW_TYPE_CODE_ID;
    private static final String HELPER_BILLABLE_ID = HELPER_PREFIX + BILLABLE_ID;
    
    private static final String SUBMISSION_TYPE_CODE = "Initial Protocol Application for Approval";
    private static final String PROTOCOL_REVIEW_TYPE_CODE = "Full";
    private static final String BILLABLE =  "on";
    
    private static final String SUBMIT_FOR_REVIEW_BUTTON = "methodToCall.submitForReview";
    private static final String MODIFY_SUBMISSION_BUTTON = "methodToCall.modifySubmsionAction";
    
    private ProtocolSeleniumHelper helper;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        helper = ProtocolSeleniumHelper.instance(driver);
        helper.loginBackdoor();
    }
    
    @After
    public void tearDown() throws Exception {
        helper.loginBackdoor();
        helper = null;
        
        super.tearDown();
    }
    
    /**
     * Test the billable authorization to make sure that the IRB Admin is allowed to edit the billable checkbox.
     * 
     * @throws Exception
     */
    @Test
    public void testBillableHasPermission() throws Exception {
        helper.createProtocol();
        helper.clickProtocolActionsPage();

        helper.openTab(ACTION_TAB_ID);
        
        helper.openTab(SUBMIT_TAB_ID);
        helper.set(SUBMISSION_HELPER_SUBMISSION_TYPE_CODE_ID, SUBMISSION_TYPE_CODE);
        helper.set(SUBMISSION_HELPER_PROTOCOL_REVIEW_TYPE_CODE_ID, PROTOCOL_REVIEW_TYPE_CODE);

        helper.click(SUBMIT_FOR_REVIEW_BUTTON);
        helper.assertNoPageErrors();
        
        helper.openTab(TAB_ID);
        
        helper.assertElementExists(BILLABLE);
        
        helper.set(HELPER_BILLABLE_ID, BILLABLE);
        
        helper.click(MODIFY_SUBMISSION_BUTTON);

        helper.assertElementContains(HELPER_BILLABLE_ID, BILLABLE);
    }
    
    /**
     * Test the billable authorization to make sure users other than IRB Admin are not allowed to edit the billable checkbox.
     * 
     * @throws Exception
     */
    @Test
    public void testBillableHasNoPermission() throws Exception {
        helper.createProtocol();
        helper.clickProtocolActionsPage();

        helper.openTab(ACTION_TAB_ID);
        
        helper.openTab(SUBMIT_TAB_ID);
        helper.set(SUBMISSION_HELPER_SUBMISSION_TYPE_CODE_ID, SUBMISSION_TYPE_CODE);
        helper.set(SUBMISSION_HELPER_PROTOCOL_REVIEW_TYPE_CODE_ID, PROTOCOL_REVIEW_TYPE_CODE);

        helper.click(SUBMIT_FOR_REVIEW_BUTTON);
        helper.assertNoPageErrors();
        
        String documentNumber = helper.getDocumentNumber();
        helper.closeDocument();
        helper.loginBackdoor("jtester");
        helper.docSearch(documentNumber);
        
        helper.clickProtocolActionsPage();
        
        helper.assertElementDoesNotExist(HELPER_BILLABLE_ID);
    }

}