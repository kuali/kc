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
package org.kuali.kra.irb.actions.assigncmtsched;

import java.sql.Date;
import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.test.CommitteeFactory;
import org.kuali.kra.committee.web.struts.form.schedule.Time12HrFmt;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.submit.ProtocolReviewType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * Test the ProtocolDeleteService implementation.
 */
public class ProtocolAssignCmtSchedServiceTest extends KcUnitTestBase {

    private BusinessObjectService businessObjectService;
    private ProtocolAssignCmtSchedService protocolAssignCmtSchedService;
    private CommitteeDocument committeeDocument;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        protocolAssignCmtSchedService = KraServiceLocator.getService(ProtocolAssignCmtSchedService.class);
        committeeDocument = createCommittee("666");
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        super.tearDown();
    }
    
    /**
     * Verify that no committee id is returned if the protocol does not have a submission.
     * @throws Exception
     */
    @Test
    public void testNoSubmission() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        String committeeId = protocolAssignCmtSchedService.getAssignedCommitteeId(protocolDocument.getProtocol());
        assertTrue(StringUtils.isBlank(committeeId));
    }
    
    /**
     * Verify that no committee id is returned if there is a submission but it hasn't been
     * assigned a committee yet.
     * @throws Exception
     */
    @Test
    public void testSubmissionNoCommittee() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        ProtocolSubmission submission = createSubmission(protocolDocument.getProtocol(), ProtocolSubmissionStatus.PENDING);
        protocolDocument.getProtocol().getProtocolSubmissions().add(submission);
        String committeeId = protocolAssignCmtSchedService.getAssignedCommitteeId(protocolDocument.getProtocol());
        assertTrue(StringUtils.isBlank(committeeId));
    }
    
    /**
     * Verify that the committee id is returned if there is a submission and it has
     * been assigned to a committee.
     * @throws Exception
     */
    @Test
    public void testSubmissionWithCommittee() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        ProtocolSubmission submission = createSubmission(protocolDocument.getProtocol(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
        protocolDocument.getProtocol().getProtocolSubmissions().add(submission);
        String committeeId = protocolAssignCmtSchedService.getAssignedCommitteeId(protocolDocument.getProtocol());
        assertEquals("1", committeeId);
    }
    
    /**
     * Verify that a protocol can be assigned to a committee without a schedule.
     * @throws Exception
     */
    @Test
    public void testAssignmentToCommittee() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
       
        ProtocolSubmission submission = createSubmission(protocolDocument.getProtocol(), ProtocolSubmissionStatus.PENDING);
        protocolDocument.getProtocol().getProtocolSubmissions().add(submission);
        
        ProtocolAssignCmtSchedBean actionBean = new ProtocolAssignCmtSchedBean(null);
        actionBean.setCommitteeId(committeeDocument.getCommittee().getCommitteeId());
        
        protocolAssignCmtSchedService.assignToCommitteeAndSchedule(protocolDocument.getProtocol(), actionBean);
        
        assertEquals(ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE, submission.getSubmissionStatusCode());
        assertEquals("666", submission.getCommitteeId());
        assertEquals(null, submission.getScheduleId());
    }
    
    /**
     * Verify that a protocol can be assigned to a committee and a schedule.
     * @throws Exception
     */
    @Test
    public void testAssignmentToSchedule() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
       
        ProtocolSubmission submission = createSubmission(protocolDocument.getProtocol(), ProtocolSubmissionStatus.PENDING);
        protocolDocument.getProtocol().getProtocolSubmissions().add(submission);
        
        ProtocolAssignCmtSchedBean actionBean = new ProtocolAssignCmtSchedBean(null);
        actionBean.setCommitteeId(committeeDocument.getCommittee().getCommitteeId());
        actionBean.setScheduleId("1");
        
        protocolAssignCmtSchedService.assignToCommitteeAndSchedule(protocolDocument.getProtocol(), actionBean);
        
        assertEquals(ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE, submission.getSubmissionStatusCode());
        assertEquals("666", submission.getCommitteeId());
        assertEquals("1", submission.getScheduleId());
    }
    
    /**
     * Create a submission.
     */
    private ProtocolSubmission createSubmission(Protocol protocol, String statusCode) {
        ProtocolSubmission submission = new ProtocolSubmission();
        submission.setProtocol(protocol);
        submission.setProtocolId(protocol.getProtocolId());
        submission.setProtocolNumber(protocol.getProtocolNumber());
        submission.setSubmissionNumber(1);
        submission.setSubmissionTypeCode(ProtocolSubmissionType.INITIAL_SUBMISSION);
        submission.setSubmissionStatusCode(statusCode);
        submission.setProtocolReviewTypeCode(ProtocolReviewType.FULL_TYPE_CODE);
        submission.setSubmissionDate(new Date(System.currentTimeMillis()));
        if (StringUtils.equals(statusCode, ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE)) {
            submission.setCommitteeId("1");
        }
        return submission;
    }
    
    /**
     * Create a committee.
     */
    private CommitteeDocument createCommittee(String committeeId) throws WorkflowException {
        CommitteeDocument committeeDocument = CommitteeFactory.createCommitteeDocument(committeeId);
        Committee committee = committeeDocument.getCommittee();
        CommitteeSchedule schedule = new CommitteeSchedule();
        schedule.setScheduleId("1");
        schedule.setPlace("my office");
        schedule.setEndTime(new Timestamp(System.currentTimeMillis() + 100));
        schedule.setScheduledDate(new Date(System.currentTimeMillis()));
        schedule.setStartTime(new Timestamp(System.currentTimeMillis() - 100));
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
}
