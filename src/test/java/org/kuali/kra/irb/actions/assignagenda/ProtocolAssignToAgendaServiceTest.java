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
package org.kuali.kra.irb.actions.assignagenda;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.actions.submit.ProtocolReviewType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;

/**
 * Test the ProtocolDeleteService implementation.
 */
@PerSuiteUnitTestData(@UnitTestData(sqlFiles = {
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_status.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_ORG_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_PERSON_ROLES.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_type.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_SUBMISSION_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_review_type.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_REVIEWER_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_committee_type.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_ACTION_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_SUBMISSION_TYPE_QUALIFIER.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_MODULES.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_SUBMISSION_STATUS.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_schedule_status.sql", delimiter = ";")
}))

public class ProtocolAssignToAgendaServiceTest extends KraTestBase{
    
    private BusinessObjectService businessObjectService;
    private DocumentService documentService;
    private ProtocolActionService protocolActionService;
    private ProtocolAssignToAgendaService protocolAssignToAgendaService;
    private ProtocolAssignToAgendaServiceImpl protocolAssignToAgendaServiceImpl;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        documentService = KraServiceLocator.getService(DocumentService.class);
        protocolActionService = KraServiceLocator.getService(ProtocolActionService.class);
        protocolAssignToAgendaService = KraServiceLocator.getService(ProtocolAssignToAgendaService.class);
        protocolAssignToAgendaServiceImpl = (ProtocolAssignToAgendaServiceImpl)KraServiceLocator.getService(ProtocolAssignToAgendaService.class);
    }

    @Override
    @After
    public void tearDown() throws Exception {
        businessObjectService = null;
        documentService = null;
        protocolActionService = null;
        protocolAssignToAgendaService = null;
        protocolAssignToAgendaServiceImpl = null;
        GlobalVariables.setUserSession(null);
        super.tearDown();
    }
    @Test
    public void testSetDocumentService() {
        protocolAssignToAgendaServiceImpl.setDocumentService(documentService);
        assertTrue(true);
    }

    @Test
    public void testSetProtocolActionService() {
        protocolAssignToAgendaServiceImpl.setProtocolActionService(protocolActionService);
        assertTrue(true);
    }

    
    /*@Test
    public void testAssignToAgenda() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        ProtocolSubmission submission = createSubmission(protocolDocument.getProtocol(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
        protocolDocument.getProtocol().getProtocolSubmissions().add(submission);
        ActionHelper actionHelper = new ActionHelper();
        ProtocolAssignToAgendaBean actionBean = new ProtocolAssignToAgendaBean();
    }*/

    @Test
    public void testIsAssignedToAgenda1() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        ProtocolSubmission submission = createSubmission(protocolDocument.getProtocol(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
        protocolDocument.getProtocol().getProtocolSubmissions().add(submission);
        ProtocolAction pa = protocolAssignToAgendaService.getAssignedToAgendaProtocolAction(protocolDocument.getProtocol());
        boolean result = pa != null;
        assertFalse(result);
    }
    
    @Test
    public void testIsAssignedToAgenda2() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        ProtocolSubmission submission = createSubmission(protocolDocument.getProtocol(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
        protocolDocument.getProtocol().getProtocolSubmissions().add(submission);
        List<ProtocolAction> actions = new ArrayList();
        actions.add(new ProtocolAction(protocolDocument.getProtocol(), submission, ProtocolActionType.ASSIGN_TO_AGENDA));
        protocolDocument.getProtocol().setProtocolActions(actions);
        ProtocolAction pa = protocolAssignToAgendaService.getAssignedToAgendaProtocolAction(protocolDocument.getProtocol());
        boolean result = pa != null;
        assertTrue(result);
    }

    @Test
    public void testGetAssignToAgendaComments() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        ProtocolSubmission submission = createSubmission(protocolDocument.getProtocol(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
        protocolDocument.getProtocol().getProtocolSubmissions().add(submission);
        List<ProtocolAction> actions = new ArrayList();
        ProtocolAction pa = new ProtocolAction(protocolDocument.getProtocol(), submission, ProtocolActionType.ASSIGN_TO_AGENDA);
        String comments = "My test protocol action comments";
        pa.setComments(comments);
        actions.add(pa);
        protocolDocument.getProtocol().setProtocolActions(actions);
        ProtocolAction pa2 = protocolAssignToAgendaService.getAssignedToAgendaProtocolAction(protocolDocument.getProtocol());
        String result = pa2.getComments();
        assertEquals(comments, result);
    }

    @Test
    public void testGetAssignedCommitteeId() throws Exception {

        
        String committeeId = "1";
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        ProtocolSubmission submission = createSubmission(protocolDocument.getProtocol(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
        submission.setCommitteeId(committeeId);
        protocolDocument.getProtocol().getProtocolSubmissions().add(submission);
        List<ProtocolAction> actions = new ArrayList();
        ProtocolAction pa = new ProtocolAction(protocolDocument.getProtocol(), submission, ProtocolActionType.ASSIGN_TO_AGENDA);
        pa.setProtocolSubmission(submission);
        actions.add(pa);
        protocolDocument.getProtocol().setProtocolActions(actions);
        ProtocolAction pa2 = protocolAssignToAgendaService.getAssignedToAgendaProtocolAction(protocolDocument.getProtocol());
        String result = pa2.getProtocolSubmission().getCommitteeId();
        assertEquals(committeeId, result);        
    }

    @Test
    public void testGetAssignedCommitteeName() throws Exception {        
        String committeeId = "1";
        Committee com = new Committee();
        com.setCommitteeId("1");
        String passedInCommitteeName = "testCommitteeName";
        com.setCommitteeName(passedInCommitteeName);
        
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        ProtocolSubmission submission = createSubmission(protocolDocument.getProtocol(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
        submission.setCommitteeId(committeeId);
        submission.setCommittee(com);
        protocolDocument.getProtocol().getProtocolSubmissions().add(submission);
        List<ProtocolAction> actions = new ArrayList();
        ProtocolAction pa = new ProtocolAction(protocolDocument.getProtocol(), submission, ProtocolActionType.ASSIGN_TO_AGENDA);
        pa.setProtocolSubmission(submission);
        actions.add(pa);
        protocolDocument.getProtocol().setProtocolActions(actions);
        ProtocolAction pa2 = protocolAssignToAgendaService.getAssignedToAgendaProtocolAction(protocolDocument.getProtocol());
        String committeeName = pa2.getProtocolSubmission().getCommittee().getCommitteeName();
        assertEquals(passedInCommitteeName, committeeName);; 
        
    }

    /* @Test
    public void testGetAssignedScheduleDate() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        ProtocolSubmission submission = createSubmission(protocolDocument.getProtocol(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
        Committee com = new Committee();
        com.setCommitteeId("1");
        String passedInCommitteeName = "testCommitteeName";
        com.setCommitteeName(passedInCommitteeName);
        
        Date passedInDate = new Date(2009, 11, 14);
        CommitteeSchedule cs = new CommitteeSchedule();
        cs.setScheduledDate(passedInDate);
        cs.setCommitteeIdFk(new Long(1));
        cs.setId(new Long(-666));
        List<CommitteeSchedule> csList = new ArrayList();
        csList.add(cs);
        com.setCommitteeSchedules(csList);
        
        submission.setCommittee(com);
        protocolDocument.getProtocol().getProtocolSubmissions().add(submission);
        java.util.Date committeeDate = protocolAssignToAgendaService.getAssignedScheduleDate(protocolDocument.getProtocol());
        assertEquals(String.valueOf(passedInDate.getTime()), String.valueOf(committeeDate.getTime()), "The committee name was: '" + committeeDate.getTime() + "'");
    }*/
    
    private ProtocolSubmission createSubmission(Protocol protocol, String statusCode) {
        ProtocolSubmission submission = new ProtocolSubmission();
        submission.setProtocol(protocol);
        submission.setProtocolId(protocol.getProtocolId());
        submission.setProtocolNumber(protocol.getProtocolNumber());
        submission.setSubmissionNumber(1);
        submission.setSubmissionTypeCode(ProtocolSubmissionType.INITIAL_SUBMISSION);
        submission.setSubmissionStatusCode(statusCode);
        submission.setProtocolReviewTypeCode(ProtocolReviewType.FULL_TYPE_CODE);
        submission.setSubmissionDate(new Timestamp(System.currentTimeMillis()));
        //if (StringUtils.equals(statusCode, ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE)) {
            submission.setCommitteeId("1");
        //}
        return submission;
    }

}
