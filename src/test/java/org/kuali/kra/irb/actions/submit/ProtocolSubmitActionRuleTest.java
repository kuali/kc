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
package org.kuali.kra.irb.actions.submit;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.submit.ProtocolReviewerBean;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitAction;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitActionRule;
import org.kuali.kra.irb.test.ProtocolRuleTestBase;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.bo.Parameter;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.util.GlobalVariables;
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
               ,@UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_REVIEWER_TYPE.sql", delimiter = ";")
               ,@UnitTestFile(filename = "classpath:sql/dml/load_EXEMPT_STUDIES_CHECKLIST.sql", delimiter = ";")
               ,@UnitTestFile(filename = "classpath:sql/dml/load_EXPEDITED_REVIEW_CHECKLIST.sql", delimiter = ";")
            }
        )
    )
public class ProtocolSubmitActionRuleTest extends ProtocolRuleTestBase {

    private ProtocolSubmitActionRule rule = null;
    private static final String VALID_SUBMISSION_TYPE = "100";
    private static final String VALID_REVIEW_TYPE = "1";
    private static final String INVALID_SUBMISSION_TYPE = "ahfgdfsgr#%$#$%#$%";
    private static final String INVALID_REVIEW_TYPE = INVALID_SUBMISSION_TYPE;
    private static final String COMMITTEE_ID = "1";
    private static final String SCHEDULE_ID = "1";
    private static final String MANDATORY = "M";
    private static final String OPTIONAL = "O";

    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new ProtocolSubmitActionRule();
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
    @SuppressWarnings("deprecation")
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
    
    /**
     * Verify that for an exempt review type, the validation will
     * pass if there is at least one check list item that is selected.
     */
    @SuppressWarnings("deprecation")
    @Test
    public void testExemptCheckListOK() throws WorkflowException {
        ProtocolDocument document = getNewProtocolDocument();
        ProtocolSubmitAction submitAction = new ProtocolSubmitAction(null);
        submitAction.setSubmissionTypeCode(VALID_SUBMISSION_TYPE);
        List<ExemptStudiesCheckListItem> checkList = new ArrayList<ExemptStudiesCheckListItem>();
        ExemptStudiesCheckListItem item = new ExemptStudiesCheckListItem();
        item.setChecked(true);
        checkList.add(item);
        submitAction.setExemptStudiesCheckList(checkList);
        submitAction.setProtocolReviewTypeCode(ProtocolReviewType.EXEMPT_STUDIES_REVIEW_TYPE_CODE);
        assertTrue(rule.processSubmitAction(document, submitAction));
        assertEquals(GlobalVariables.getErrorMap().size(), 0);
    }
    
    /**
     * Verify that for an exempt review type, the validation will
     * fail if there isn't any check list items selected.
     */
    @Test
    public void testExemptCheckListNone() throws WorkflowException {
        ProtocolDocument document = getNewProtocolDocument();
        ProtocolSubmitAction submitAction = new ProtocolSubmitAction(null);
        submitAction.setSubmissionTypeCode(VALID_SUBMISSION_TYPE);
        submitAction.setProtocolReviewTypeCode(ProtocolReviewType.EXEMPT_STUDIES_REVIEW_TYPE_CODE);
        List<ExemptStudiesCheckListItem> checkList = new ArrayList<ExemptStudiesCheckListItem>();
        ExemptStudiesCheckListItem item = new ExemptStudiesCheckListItem();
        item.setChecked(false);
        submitAction.setExemptStudiesCheckList(checkList);
        assertFalse(rule.processSubmitAction(document, submitAction));
        assertError(Constants.PROTOCOL_SUBMIT_ACTION_PROPERTY_KEY, 
                    KeyConstants.ERROR_PROTOCOL_AT_LEAST_ONE_CHECKLIST_ITEM);
    }
    
    /**
     * Verify that for an expedited review type, the validation will
     * pass if there is at least one check list item that is selected.
     */
    @SuppressWarnings("deprecation")
    @Test
    public void testExpeditedCheckListOK() throws WorkflowException {
        ProtocolDocument document = getNewProtocolDocument();
        ProtocolSubmitAction submitAction = new ProtocolSubmitAction(null);
        submitAction.setSubmissionTypeCode(VALID_SUBMISSION_TYPE);
        List<ExpeditedReviewCheckListItem> checkList = new ArrayList<ExpeditedReviewCheckListItem>();
        ExpeditedReviewCheckListItem item = new ExpeditedReviewCheckListItem();
        item.setChecked(true);
        checkList.add(item);
        submitAction.setExpeditedReviewCheckList(checkList);
        submitAction.setProtocolReviewTypeCode(ProtocolReviewType.EXPEDITED_REVIEW_TYPE_CODE);
        assertTrue(rule.processSubmitAction(document, submitAction));
        assertEquals(GlobalVariables.getErrorMap().size(), 0);
    }
    
    /**
     * Verify that for an expedited review type, the validation will
     * fail if there isn't any check list items selected.
     */
    @Test
    public void testExpeditedCheckListNone() throws WorkflowException {
        ProtocolDocument document = getNewProtocolDocument();
        ProtocolSubmitAction submitAction = new ProtocolSubmitAction(null);
        submitAction.setSubmissionTypeCode(VALID_SUBMISSION_TYPE);
        submitAction.setProtocolReviewTypeCode(ProtocolReviewType.EXPEDITED_REVIEW_TYPE_CODE);
        List<ExpeditedReviewCheckListItem> checkList = new ArrayList<ExpeditedReviewCheckListItem>();
        ExpeditedReviewCheckListItem item = new ExpeditedReviewCheckListItem();
        item.setChecked(false);
        submitAction.setExpeditedReviewCheckList(checkList);
        assertFalse(rule.processSubmitAction(document, submitAction));
        assertError(Constants.PROTOCOL_SUBMIT_ACTION_PROPERTY_KEY, 
                    KeyConstants.ERROR_PROTOCOL_AT_LEAST_ONE_CHECKLIST_ITEM);
    }
    
    /**
     * Test validation for a couple of reviewers.  
     * There should be no errors.
     */
    @SuppressWarnings("deprecation")
    @Test
    public void testOKReviewers() throws WorkflowException {
        ProtocolDocument document = getNewProtocolDocument();
        ProtocolSubmitAction submitAction = new ProtocolSubmitAction(null);
        submitAction.setSubmissionTypeCode(VALID_SUBMISSION_TYPE);
        submitAction.setProtocolReviewTypeCode(VALID_REVIEW_TYPE);
        ProtocolReviewerBean reviewer = new ProtocolReviewerBean();
        reviewer.setChecked(false);
        reviewer.setReviewerTypeCode("");
        submitAction.getReviewers().add(reviewer);
        reviewer = new ProtocolReviewerBean();
        reviewer.setChecked(true);
        reviewer.setReviewerTypeCode("1");
        submitAction.getReviewers().add(reviewer);
        assertTrue(rule.processSubmitAction(document, submitAction));
        assertEquals(GlobalVariables.getErrorMap().size(), 0);
    }
    
    /**
     * Test a reviewer that is "unchecked" but has a reviewer type set.
     */
    @Test
    public void testUncheckedReviewer() throws WorkflowException {
        ProtocolDocument document = getNewProtocolDocument();
        ProtocolSubmitAction submitAction = new ProtocolSubmitAction(null);
        submitAction.setSubmissionTypeCode(VALID_SUBMISSION_TYPE);
        submitAction.setProtocolReviewTypeCode(VALID_REVIEW_TYPE);
        ProtocolReviewerBean reviewer = new ProtocolReviewerBean();
        reviewer.setChecked(false);
        reviewer.setReviewerTypeCode("1");
        submitAction.getReviewers().add(reviewer);
        assertFalse(rule.processSubmitAction(document, submitAction));
        assertError(Constants.PROTOCOL_SUBMIT_ACTION_PROPERTY_KEY + ".reviewer[0].reviewerTypeCode", 
                    KeyConstants.ERROR_PROTOCOL_REVIEWER_NOT_CHECKED_BUT_TYPE_SELECTED);
    }
    
    /**
     * Test a reviewer that is "checked" but does not have a reviewer type.
     */
    @Test
    public void testCheckedReviewer() throws WorkflowException {
        ProtocolDocument document = getNewProtocolDocument();
        ProtocolSubmitAction submitAction = new ProtocolSubmitAction(null);
        submitAction.setSubmissionTypeCode(VALID_SUBMISSION_TYPE);
        submitAction.setProtocolReviewTypeCode(VALID_REVIEW_TYPE);
        ProtocolReviewerBean reviewer = new ProtocolReviewerBean();
        reviewer.setChecked(true);
        reviewer.setReviewerTypeCode("");
        submitAction.getReviewers().add(reviewer);
        assertFalse(rule.processSubmitAction(document, submitAction));
        assertError(Constants.PROTOCOL_SUBMIT_ACTION_PROPERTY_KEY + ".reviewer[0].reviewerTypeCode", 
                    KeyConstants.ERROR_PROTOCOL_REVIEWER_NO_TYPE_BUT_REVIEWER_CHECKED);
    }
    
    /**
     * Test a reviewer that has an invalid reviewer type.
     */
    @Test
    public void testInvalidReviewerType() throws WorkflowException {
        ProtocolDocument document = getNewProtocolDocument();
        ProtocolSubmitAction submitAction = new ProtocolSubmitAction(null);
        submitAction.setSubmissionTypeCode(VALID_SUBMISSION_TYPE);
        submitAction.setProtocolReviewTypeCode(VALID_REVIEW_TYPE);
        ProtocolReviewerBean reviewer = new ProtocolReviewerBean();
        reviewer.setChecked(true);
        reviewer.setReviewerTypeCode("xx");
        submitAction.getReviewers().add(reviewer);
        assertFalse(rule.processSubmitAction(document, submitAction));
        assertError(Constants.PROTOCOL_SUBMIT_ACTION_PROPERTY_KEY + ".reviewer[0].reviewerTypeCode", 
                    KeyConstants.ERROR_PROTOCOL_REVIEWER_TYPE_INVALID);
    }
    
    /**
     * If the mandatory flag has been set, we should get no
     * error if all required fields have been set.
     * @throws WorkflowException
     */
    @SuppressWarnings("deprecation")
    @Test
    public void testMandatoryOK() throws WorkflowException {
        setParameter(MANDATORY);
        
        ProtocolDocument document = getNewProtocolDocument();
        ProtocolSubmitAction submitAction = new ProtocolSubmitAction(null);
        submitAction.setSubmissionTypeCode(VALID_SUBMISSION_TYPE);
        submitAction.setProtocolReviewTypeCode(VALID_REVIEW_TYPE);
        submitAction.setCommitteeId(COMMITTEE_ID);
        submitAction.setScheduleId(SCHEDULE_ID);
        
        assertTrue(rule.processSubmitAction(document, submitAction));
        assertEquals(0, GlobalVariables.getErrorMap().size());
        
        setParameter(OPTIONAL);
    }
    
    /**
     * If the mandatory flag has been set, we should get an error message
     * if the committee id has not been set.
     * @throws WorkflowException
     */
    @Test
    public void testMandatoryCommittee() throws WorkflowException {
        setParameter(MANDATORY);
        
        ProtocolDocument document = getNewProtocolDocument();
        ProtocolSubmitAction submitAction = new ProtocolSubmitAction(null);
        submitAction.setSubmissionTypeCode(VALID_SUBMISSION_TYPE);
        submitAction.setProtocolReviewTypeCode(VALID_REVIEW_TYPE);
        submitAction.setScheduleId(SCHEDULE_ID);
        
        assertFalse(rule.processSubmitAction(document, submitAction));
        assertError(Constants.PROTOCOL_SUBMIT_ACTION_PROPERTY_KEY + ".committeeId", 
                    KeyConstants.ERROR_PROTOCOL_COMMITTEE_NOT_SELECTED);
        
        setParameter(OPTIONAL);
    }
    
    /**
     * If the mandatory flag has been set, we should get an error message
     * if the schedule id has not been set.
     * @throws WorkflowException
     */
    @Test
    public void testMandatorySchedule() throws WorkflowException {
        setParameter(MANDATORY);
        
        ProtocolDocument document = getNewProtocolDocument();
        ProtocolSubmitAction submitAction = new ProtocolSubmitAction(null);
        submitAction.setSubmissionTypeCode(VALID_SUBMISSION_TYPE);
        submitAction.setProtocolReviewTypeCode(VALID_REVIEW_TYPE);
        submitAction.setCommitteeId(COMMITTEE_ID);
        
        assertFalse(rule.processSubmitAction(document, submitAction));
        assertError(Constants.PROTOCOL_SUBMIT_ACTION_PROPERTY_KEY + ".scheduleId", 
                    KeyConstants.ERROR_PROTOCOL_SCHEDULE_NOT_SELECTED);
        
        setParameter(OPTIONAL);
    }
    
    /*
     * Set the IRB parameter for submission in order to make the committee/schedule
     * either mandatory or optional.
     */
    private void setParameter(String value) {
        KualiConfigurationService configService = getService(KualiConfigurationService.class);
        Parameter param = configService.getParameterWithoutExceptions(Constants.PARAMETER_MODULE_PROTOCOL, 
                                                                      Constants.PARAMETER_COMPONENT_DOCUMENT, 
                                                                      Constants.PARAMETER_IRB_COMM_SELECTION_DURING_SUBMISSION);
        param.setParameterValue(value);
        BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        businessObjectService.save(param);
    }
}
