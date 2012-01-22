/*
 * Copyright 2005-2010 The Kuali Foundation
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

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.irb.test.ProtocolRuleTestBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * Test the business rules for Protocol Permissions.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProtocolSubmitActionRuleTest extends ProtocolRuleTestBase {

    private Mockery context = new JUnit4Mockery();

    private ProtocolSubmitActionRule rule = null;
    private static final String VALID_SUBMISSION_TYPE = "100";
    private static final String VALID_REVIEW_TYPE = "1";
    private static final String INVALID_SUBMISSION_TYPE = "ahfgdfsgr#%$#$%#$%";
    private static final String INVALID_REVIEW_TYPE = INVALID_SUBMISSION_TYPE;
    private static final String COMMITTEE_ID = "1";
    private static final String SCHEDULE_ID = "1";
    private static final String MANDATORY = "M";
    private static final String OPTIONAL = "O";

    private ParameterService parameterService;

    @Before
    public void setUpServices() {
        this.parameterService = KraServiceLocator.getService(ParameterService.class);
        // If cache not cleared, causes OLE.
        //this.parameterService.clearCache();
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        // we create an anonymous rule class because for most test methods we want
        // the spoofing check to be disabled i.e. always true
        rule = new ProtocolSubmitActionRule(){
            @Override
            public boolean checkNoSpoofing(ProtocolSubmitAction submitAction) {
                return true;
            }
        };
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        super.tearDown();
    }

    /**
     * Test a valid submission.
     * 
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
        assertEquals(GlobalVariables.getMessageMap().getErrorMessages().size(), 0);
    }

    /**
     * Test a empty submission type.
     * 
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
     * 
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
     * Test a invalid review type.
     * 
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
     * Verify that for an exempt review type, the validation will pass if there is at least one check list item that is selected.
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
        assertEquals(GlobalVariables.getMessageMap().getErrorMessages().size(), 0);
    }

    /**
     * Verify that for an exempt review type, the validation will fail if there isn't any check list items selected.
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
        assertError(Constants.PROTOCOL_SUBMIT_ACTION_PROPERTY_KEY, KeyConstants.ERROR_PROTOCOL_AT_LEAST_ONE_CHECKLIST_ITEM);
    }

    /**
     * Verify that for an expedited review type, the validation will pass if there is at least one check list item that is selected.
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
        assertEquals(GlobalVariables.getMessageMap().getErrorMessages().size(), 0);
    }

    /**
     * Verify that for an expedited review type, the validation will fail if there isn't any check list items selected.
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
        assertError(Constants.PROTOCOL_SUBMIT_ACTION_PROPERTY_KEY, KeyConstants.ERROR_PROTOCOL_AT_LEAST_ONE_CHECKLIST_ITEM);
    }

    /**
     * Test validation for a couple of reviewers. There should be no errors.
     */
    @SuppressWarnings("deprecation")
    @Test
    public void testOKReviewers() throws WorkflowException {
        ProtocolDocument document = getNewProtocolDocument();
        ProtocolSubmitAction submitAction = new ProtocolSubmitAction(null);
        submitAction.setSubmissionTypeCode(VALID_SUBMISSION_TYPE);
        submitAction.setProtocolReviewTypeCode(VALID_REVIEW_TYPE);
        ProtocolReviewerBean reviewer = new ProtocolReviewerBean();
        reviewer.setReviewerTypeCode("");
        submitAction.getReviewers().add(reviewer);
        reviewer = new ProtocolReviewerBean();
        reviewer.setReviewerTypeCode("1");
        submitAction.getReviewers().add(reviewer);
        assertTrue(rule.processSubmitAction(document, submitAction));
        assertEquals(GlobalVariables.getMessageMap().getErrorMessages().size(), 0);
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
        reviewer.setReviewerTypeCode("xx");
        submitAction.getReviewers().add(reviewer);
        assertFalse(rule.processSubmitAction(document, submitAction));
        assertError(Constants.PROTOCOL_SUBMIT_ACTION_PROPERTY_KEY + ".reviewer[0].reviewerTypeCode",
                KeyConstants.ERROR_PROTOCOL_REVIEWER_TYPE_INVALID);
    }


    /**
     * Test the validation check that ensures all reviewers submitted are actually 
     * available for a particular protocol, committee and schedule.
     */
    @Test
    public void testNoSpoofing() throws WorkflowException {
        // overwrite the pre-setup rule because we want spoofing check to be enabled now
        rule = new ProtocolSubmitActionRule();
        
        
        final List<CommitteeMembership> members = new ArrayList<CommitteeMembership>();
        members.add(createMember("dn", "Don", null));
        members.add(createMember("nncy", "Nancy", 1));
        members.add(createMember(null, "Joe", 2));
        members.add(createMember(null, "Joanna", 5));
        final CommitteeService committeeService = context.mock(CommitteeService.class);
        context.checking(new Expectations() {
            {
                allowing(committeeService).getAvailableMembers(COMMITTEE_ID, SCHEDULE_ID);
                will(returnValue(members));
            }
        }); 
       
        rule.setCommitteeService(committeeService);

        final Protocol protocol = new Protocol() {
            private static final long serialVersionUID = -1273061983131550371L;
            @Override
            public void refreshReferenceObject(String referenceObjectName) {
                // do nothing
            }
        };
        
        //Initially we will only have Don and Nancy in the protocol to test success, later we will add Joe and Joanna to test failure
        protocol.getProtocolPersons().add(createProtocolPerson("dn", 10));
        protocol.getProtocolPersons().add(createProtocolPerson("nncy", null));
        
        
        
        ProtocolSubmitAction submitAction = new ProtocolSubmitAction(null) {
            @Override
            public Protocol getProtocol() {
                return protocol;
            }
        };     
       
        // we will use joe and joanna as the submitted reviewers 
        submitAction.getReviewers().add(new ProtocolReviewerBean(createMember(null, "Joe", 2)));
        submitAction.getReviewers().add(new ProtocolReviewerBean(createMember(null, "Joanna", 5)));
        
        submitAction.setCommitteeId(COMMITTEE_ID);
        submitAction.setScheduleId(SCHEDULE_ID);
        
        assertTrue(rule.checkNoSpoofing(submitAction));
        
        // now add Joe to the protocol; that should fail the spoofing check with one error
        protocol.getProtocolPersons().add(createProtocolPerson("", 2));
        assertFalse(rule.checkNoSpoofing(submitAction));
        assertError(Constants.PROTOCOL_SUBMIT_ACTION_PROPERTY_KEY + ".reviewer[0].reviewerUnavailable", KeyConstants.ERROR_PROTOCOL_REVIEWER_NOT_AVAILABLE);
        
        // now add Joanna also to the protocol; that should fail the spoofing check with another error
        protocol.getProtocolPersons().add(createProtocolPerson(null, 5));
        assertFalse(rule.checkNoSpoofing(submitAction));
        assertError(Constants.PROTOCOL_SUBMIT_ACTION_PROPERTY_KEY + ".reviewer[1].reviewerUnavailable", KeyConstants.ERROR_PROTOCOL_REVIEWER_NOT_AVAILABLE);

    }


    private CommitteeMembership createMember(String personId, String personName, Integer rolodexId) {
        CommitteeMembership member = new CommitteeMembership();
        member.setPersonId(personId);
        member.setPersonName(personName);
        member.setRolodexId(rolodexId);
        return member;
    }

    private ProtocolPerson createProtocolPerson(String personId, Integer rolodexId) {
        ProtocolPerson pp = new ProtocolPerson();
        pp.setPersonId(personId);
        pp.setRolodexId(rolodexId);
        return pp;
    }


    /**
     * If the mandatory flag has been set, we should get no error if all required fields have been set.
     * 
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
        assertEquals(0, GlobalVariables.getMessageMap().getErrorMessages().size());

        setParameter(OPTIONAL);
    }

    /**
     * If the mandatory flag has been set, we should get an error message if the committee id has not been set.
     * 
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
     * If the mandatory flag has been set, we should get an error message if the schedule id has not been set.
     * 
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
     * Set the IRB parameter for submission in order to make the committee/schedule either mandatory or optional.
     */
    private void setParameter(String value) {
        // the tranaction handling is not really saved to db.
        // it is ok for testMandatoryOK, but in testMandatoryCommittee, OLE was thrown.
        // so have to try this to force it to save to db.
        // try {
        // super.transactionalLifecycle.stop();
        // }
        // catch (Exception e) {
        //
        // }
        updateParameterForTesting(ProtocolDocument.class,
                Constants.PARAMETER_IRB_COMM_SELECTION_DURING_SUBMISSION, value);
        // try {
        // super.transactionalLifecycle.start();
        // }
        // catch (Exception e) {
        //
        // }
    }
}
