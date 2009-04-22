/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.rules;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.irb.web.struts.bean.ProtocolSubmitAction;
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;

/**
 * Test the business rules for Protocol Permissions.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@PerSuiteUnitTestData(
        @UnitTestData(
            sqlFiles = {
                @UnitTestFile(filename = "classpath:sql/dml/load_SUBMISSION_TYPE.sql", delimiter = ";")
               ,@UnitTestFile(filename = "classpath:sql/dml/load_protocol_review_type.sql", delimiter = ";")
            }
        )
    )
public class ProtocolSubmitActionTest extends ProtocolRuleTestBase {

    private ProtocolActionRule rule = null;
    private static final String VALID_SUBMISSION_TYPE = "100";
    private static final String VALID_REVIEW_TYPE = "1";
    private static final String INVALID_SUBMISSION_TYPE = "ahfgdfsgr#%$#$%#$%";
    private static final String INVALID_REVIEW_TYPE = INVALID_SUBMISSION_TYPE;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new ProtocolActionRule();
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        super.tearDown();
    }

    /**
     * Test a valid submission.
     * @throws Exception
     */
    @Test
    public void testSubmitOK() throws Exception {
        ProtocolDocument document = getNewProtocolDocument();
        ProtocolSubmitAction submitAction = new ProtocolSubmitAction(null);
        submitAction.setSubmissionTypeCode(VALID_SUBMISSION_TYPE);
        submitAction.setProtocolReviewTypeCode(VALID_REVIEW_TYPE);
        assertTrue(rule.processSubmitAction(document, submitAction));
        assertEquals(GlobalVariables.getErrorMap().size(), 0);
    }

    /**
     * Test a empty submission type.
     * @throws Exception
     */
    @Test
    public void testSubmissionTypeEmpty() throws Exception {
        ProtocolDocument document = getNewProtocolDocument();
        ProtocolSubmitAction submitAction = new ProtocolSubmitAction(null);
        submitAction.setSubmissionTypeCode("");
        submitAction.setProtocolReviewTypeCode(VALID_REVIEW_TYPE);
        assertFalse(rule.processSubmitAction(document, submitAction));
        assertError(Constants.PROTOCOL_SUBMIT_ACTION_PROPERTY_KEY + ".submissionTypeCode", 
                    KeyConstants.ERROR_PROTOCOL_SUBMISSION_TYPE_NOT_SELECTED);
    }
    
    /**
     * Test a empty review type.
     * @throws Exception
     */
    @Test
    public void testReviewTypeEmpty() throws Exception {
        ProtocolDocument document = getNewProtocolDocument();
        ProtocolSubmitAction submitAction = new ProtocolSubmitAction(null);
        submitAction.setSubmissionTypeCode(VALID_SUBMISSION_TYPE);
        submitAction.setProtocolReviewTypeCode("");
        assertFalse(rule.processSubmitAction(document, submitAction));
        assertError(Constants.PROTOCOL_SUBMIT_ACTION_PROPERTY_KEY + ".protocolReviewTypeCode", 
                    KeyConstants.ERROR_PROTOCOL_REVIEW_TYPE_NOT_SELECTED);
    }
    
    /**
     * Test a invalid submission type.
     * @throws Exception
     */
    @Test
    public void testSubmissionTypeInvalid() throws Exception {
        ProtocolDocument document = getNewProtocolDocument();
        ProtocolSubmitAction submitAction = new ProtocolSubmitAction(null);
        submitAction.setSubmissionTypeCode(INVALID_SUBMISSION_TYPE);
        submitAction.setProtocolReviewTypeCode(VALID_REVIEW_TYPE);
        assertFalse(rule.processSubmitAction(document, submitAction));
        assertError(Constants.PROTOCOL_SUBMIT_ACTION_PROPERTY_KEY + ".submissionTypeCode", 
                    KeyConstants.ERROR_PROTOCOL_SUBMISSION_TYPE_INVALID);
    }
    
    /**
     * Test a invalid review type.
     * @throws Exception
     */
    @Test
    public void testReviewTypeInvalid() throws Exception {
        ProtocolDocument document = getNewProtocolDocument();
        ProtocolSubmitAction submitAction = new ProtocolSubmitAction(null);
        submitAction.setSubmissionTypeCode(VALID_SUBMISSION_TYPE);
        submitAction.setProtocolReviewTypeCode(INVALID_REVIEW_TYPE);
        assertFalse(rule.processSubmitAction(document, submitAction));
        assertError(Constants.PROTOCOL_SUBMIT_ACTION_PROPERTY_KEY + ".protocolReviewTypeCode", 
                    KeyConstants.ERROR_PROTOCOL_REVIEW_TYPE_INVALID);
    }
}
