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
package org.kuali.kra.irb.actions.notifyirb;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.test.CommitteeFactory;
import org.kuali.kra.committee.web.struts.form.schedule.Time12HrFmt;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolSubmissionDoc;
import org.kuali.kra.irb.actions.request.MockFormFile;
import org.kuali.kra.irb.actions.submit.ProtocolReviewType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionQualifierType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;

/**
 * Test the ProtocolNotifyIrbService implementation.
 */
@PerSuiteUnitTestData(@UnitTestData(sqlFiles = {
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_status.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_ORG_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_PERSON_ROLES.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_type.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_SUBMISSION_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_SUBMISSION_TYPE_QUALIFIER.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_review_type.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_REVIEWER_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_committee_type.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_ACTION_TYPE.sql", delimiter = ";")
}))
public class ProtocolNotifyIrbServiceTest extends KraTestBase {

    private static final String REASON = "my test reason";
    
    private ProtocolNotifyIrbServiceImpl protocolNotifyIrbService;
    private BusinessObjectService businessObjectService;   
    
    @SuppressWarnings("unchecked")
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("superuser"));
        GlobalVariables.setErrorMap(new ErrorMap());
        GlobalVariables.setAuditErrorMap(new HashMap());
        protocolNotifyIrbService = new ProtocolNotifyIrbServiceImpl();
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        protocolNotifyIrbService.setBusinessObjectService(businessObjectService);
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        GlobalVariables.setErrorMap(null);
        GlobalVariables.setAuditErrorMap(null);
        super.tearDown();
    }
    
    @Test
    public void testRequestWithNoCommitteeNoFile() throws WorkflowException {
        ProtocolNotifyIrbBean notifyIrbBean = new ProtocolNotifyIrbBean();
        notifyIrbBean.setSubmissionQualifierTypeCode(ProtocolSubmissionQualifierType.AE_UADE);
        notifyIrbBean.setCommitteeId("");
        notifyIrbBean.setFile(null);
        notifyIrbBean.setComment(REASON);
        runTest(notifyIrbBean);
    }
    
    @Test
    public void testRequestWithCommitteeAndFile() throws WorkflowException {
        ProtocolNotifyIrbBean notifyIrbBean = new ProtocolNotifyIrbBean();
        notifyIrbBean.setSubmissionQualifierTypeCode(ProtocolSubmissionQualifierType.AE_UADE);
        notifyIrbBean.setCommitteeId("913");
        notifyIrbBean.setFile(new MockFormFile());
        notifyIrbBean.setComment(REASON);
        runTest(notifyIrbBean);
    }
    
    /*
     * Runs a test for the configuration defined by the input parameters.
     */
    private void runTest(ProtocolNotifyIrbBean notifyIrbBean) throws WorkflowException {
        
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        CommitteeDocument committeeDocument = null;
        if (!StringUtils.isBlank(notifyIrbBean.getCommitteeId())) {
            committeeDocument = createCommittee(notifyIrbBean.getCommitteeId());
        }
        
        protocolNotifyIrbService.submitIrbNotification(protocolDocument.getProtocol(), notifyIrbBean);
    
        ProtocolSubmission protocolSubmission = findSubmission(protocolDocument.getProtocol().getProtocolId());
        assertNotNull(protocolSubmission);
        
        ProtocolAction protocolAction = findProtocolAction(protocolDocument.getProtocol().getProtocolId());
        assertNotNull(protocolAction);
        
        verifyAction(protocolAction, notifyIrbBean, protocolSubmission);
        verifySubmission(protocolSubmission, protocolDocument.getProtocol(), notifyIrbBean, committeeDocument);
        verifySubmissionDoc(protocolSubmission, notifyIrbBean);
    }

    /*
     * Create a committee.
     */
    private CommitteeDocument createCommittee(String committeeId) throws WorkflowException {
        CommitteeDocument committeeDocument = CommitteeFactory.createCommitteeDocument(committeeId);
        Committee committee = committeeDocument.getCommittee();
        CommitteeSchedule schedule = new CommitteeSchedule();
        //schedule.setCommittee(committee);
        schedule.setScheduleId("1");
        schedule.setPlace("my office");
        schedule.setEndTime(new Date(System.currentTimeMillis() + 100));
        schedule.setScheduledDate(new Date(System.currentTimeMillis()));
        schedule.setStartTime(new Date(System.currentTimeMillis() - 100));
        schedule.setFilter(false);
        schedule.setMaxProtocols(committee.getMaxProtocols());
        schedule.setTime(new Timestamp(System.currentTimeMillis()));
        schedule.setViewTime(new Time12HrFmt(new Timestamp(System.currentTimeMillis())));
        schedule.setProtocolSubDeadline(new Date(System.currentTimeMillis() - 500));
        schedule.setScheduleStatusCode(1);
        committee.getCommitteeSchedules().add(schedule);
        businessObjectService.save(committeeDocument);
        return committeeDocument;
    }

    /*
     * Verify that the created submission and protocol action is what is expected
     * based upon the "submitAction".
     */
    private void verifySubmission(ProtocolSubmission submission, Protocol protocol, ProtocolNotifyIrbBean requestBean, CommitteeDocument committeeDocument) {
        
        assertEquals(protocol.getProtocolNumber(), submission.getProtocolNumber());
        assertEquals(protocol.getSequenceNumber(), submission.getSequenceNumber());
        assertEquals(new Integer(1), submission.getSubmissionNumber());
        
        assertEquals(ProtocolSubmissionType.NOTIFY_IRB, submission.getSubmissionTypeCode());
        assertEquals(ProtocolReviewType.FULL_TYPE_CODE, submission.getProtocolReviewTypeCode());
        assertEquals(ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE, submission.getSubmissionStatusCode());
       
        assertEquals(convert(requestBean.getCommitteeId()), convert(submission.getCommitteeId()));
        
        if (committeeDocument == null) {
            assertNull(submission.getCommitteeIdFk());
        }
        else {
            assertEquals(committeeDocument.getCommittee().getId(), submission.getCommitteeIdFk());
        }
    }
    
    /*
     * Verify the attached document.
     */
    private void verifySubmissionDoc(ProtocolSubmission protocolSubmission, ProtocolNotifyIrbBean requestBean) {
        ProtocolSubmissionDoc doc = findSubmissionDoc(protocolSubmission);
        if (requestBean.getFile() == null) {
            assertNull(doc);
        }
        else {
            assertEquals(requestBean.getFile().getFileName(), doc.getFileName());
            assertEquals(requestBean.getFile().getFileSize(), doc.getDocument().length);
        }
    }
    
    /*
     * Verfy that the Protocol Action is correct.
     */
    private void verifyAction(ProtocolAction action, ProtocolNotifyIrbBean requestBean, ProtocolSubmission submission) {
        assertEquals(ProtocolActionType.NOTIFY_IRB, action.getProtocolActionTypeCode());
        assertEquals(submission.getSubmissionId(), action.getSubmissionIdFk());
        assertEquals(REASON, action.getComments());
    }

    /*
     * If a string is null, convert it to an empty string.
     */
    private String convert(String s) {
        if (s == null) return "";
        return s;
    }

    /**
     * Find the ProtocolAction in the database.
     */
    @SuppressWarnings("unchecked")
    private ProtocolAction findProtocolAction(Long protocolId) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("protocolId", protocolId);
        List<ProtocolAction> actions = (List<ProtocolAction>) businessObjectService.findMatching(ProtocolAction.class, fieldValues);
        
        assertEquals(1, actions.size());
        ProtocolAction action = actions.get(0);
        return action;
    }

    /*
     * Find the ProtocolSubmission in the database.
     */
    @SuppressWarnings("unchecked")
    private ProtocolSubmission findSubmission(Long protocolId) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("protocolId", protocolId);
        List<ProtocolSubmission> submissions = (List<ProtocolSubmission>) businessObjectService.findMatching(ProtocolSubmission.class, fieldValues);
        
        assertEquals(1, submissions.size());
        ProtocolSubmission submission = submissions.get(0);
        return submission;
    }
    
    /*
     * Find a ProtocolSubmissionDoc in the database.
     */
    @SuppressWarnings("unchecked")
    private ProtocolSubmissionDoc findSubmissionDoc(ProtocolSubmission protocolSubmission) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("submissionIdFk", protocolSubmission.getSubmissionId());
        List<ProtocolSubmissionDoc> docs = (List<ProtocolSubmissionDoc>) businessObjectService.findMatching(ProtocolSubmissionDoc.class, fieldValues);
        
        if (docs.size() == 0) {
            return null;
        }
        return docs.get(0);
    }
}
