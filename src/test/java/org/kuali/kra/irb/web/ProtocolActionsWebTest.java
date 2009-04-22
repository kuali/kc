/*
 * Copyright 2006-2008 The Kuali Foundation
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

import java.util.List;

import org.junit.Test;
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

@PerSuiteUnitTestData(
        @UnitTestData(
            sqlFiles = {
                @UnitTestFile(filename = "classpath:sql/dml/load_SUBMISSION_TYPE.sql", delimiter = ";")
               ,@UnitTestFile(filename = "classpath:sql/dml/load_protocol_review_type.sql", delimiter = ";")
            }
        )
    )
public class ProtocolActionsWebTest extends ProtocolWebTestBase {

    protected static final String PROTOCOL_DESCRIPTION_ID =  "document.description";
    protected static final String PROTOCOL_DESCRIPTION =  "keyword_to_test1";
    
    /**
     * Tests whether making a selection in the Submission Type box and the
     * Submission Review Type box results in an error when the Submit button is clicked.
     * @throws Exception
     */
    @Test
    public void testNoError() throws Exception {
        HtmlPage protocolPage = getProtocolSavedRequiredFieldsPage();
        HtmlPage protocolActionsPage = clickOnTab(protocolPage, PROTOCOL_ACTIONS_LINK_NAME);

        // TODO: do I need to expand the inner panel first?
        
        // Make a selection in the Submission Type box and the Submission Review Type box
        setFieldValue(protocolActionsPage, "actionHelper.protocolSubmitAction.submissionTypeCode", "100");
        setFieldValue(protocolActionsPage, "actionHelper.protocolSubmitAction.protocolReviewTypeCode", "1");
  
        // Click Submit
        HtmlPage resultPage = clickOn(protocolActionsPage, "methodToCall.submitForReview.anchor:SubmitforReview");
        
        // Verify that no errors occurred
        assertNotNull(resultPage);
        assertDoesNotContain(resultPage, ERRORS_FOUND_ON_PAGE);
    }
    
    /**
     * Tests whether having no selection in the Submission Type box or the
     * Submission Review Type box results in an error when the Submit button is clicked.
     * @throws Exception
     */
    @Test
    public void testNoSelection() throws Exception {
        HtmlPage protocolPage = getProtocolSavedRequiredFieldsPage();
        HtmlPage protocolActionsPage = clickOnTab(protocolPage, PROTOCOL_ACTIONS_LINK_NAME);

        // Make a selection in the Submission Type box and the Submission Review Type box
        setFieldValue(protocolActionsPage, "actionHelper.protocolSubmitAction.submissionTypeCode", "");
        setFieldValue(protocolActionsPage, "actionHelper.protocolSubmitAction.protocolReviewTypeCode", "");
  
        // Click Submit
        HtmlPage resultPage = clickOn(protocolActionsPage, "methodToCall.submitForReview.anchor:SubmitforReview");
        
        // Verify that an error message is shown for Submission Type, and for Submission Review Type
        assertNotNull(resultPage);
        List<String> errors = getErrors(resultPage, "tab-:SubmitforReview-div");
        assertNotNull(errors);
        assertEquals(2, errors.size());
        assertTrue(containsError(errors, "Submission Review Type is a required field."));
        assertTrue(containsError(errors, "Submission Type is a required field."));
    }
    
}
