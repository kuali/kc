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
package org.kuali.kra.irb.actions.assignagenda;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.actions.ActionHelper;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.actions.submit.ProtocolReviewType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.bo.DocumentHeader;
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
    
    private DocumentService documentService;
    private ProtocolActionService protocolActionService;
    private ProtocolAssignToAgendaService protocolAssignToAgendaService;
    private ProtocolAssignToAgendaServiceImpl protocolAssignToAgendaServiceImpl;
    
    private BusinessObjectService businessObjectService;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        documentService = KraServiceLocator.getService(DocumentService.class);
        protocolActionService = KraServiceLocator.getService(ProtocolActionService.class);
        protocolAssignToAgendaService = KraServiceLocator.getService(ProtocolAssignToAgendaService.class);
        protocolAssignToAgendaServiceImpl = (ProtocolAssignToAgendaServiceImpl)KraServiceLocator.getService(ProtocolAssignToAgendaService.class);
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
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

    
    @Test
    public void testAssignToAgenda() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        ProtocolSubmission submission = createSubmission(protocolDocument.getProtocol(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
        protocolDocument.getProtocol().getProtocolSubmissions().add(submission);
        ProtocolForm form = new ProtocolForm();
        ActionHelper actionHelper = new ActionHelper(form);
        ProtocolAssignToAgendaBean actionBean = actionHelper.getAssignToAgendaBean();
        actionBean.setComments("this is a comment");
        actionBean.setCommitteName("committee name");
        actionBean.setProtocolAssigned(true);
        //actionBean.setScheduleDate(new Date());
        protocolAssignToAgendaService.assignToAgenda(protocolDocument.getProtocol(), actionBean);
        assertTrue(true);
    }

    @Test
    public void testIsAssignedToAgenda1() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        ProtocolSubmission submission = createSubmission(protocolDocument.getProtocol(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
        protocolDocument.getProtocol().getProtocolSubmissions().add(submission);
        boolean result = protocolAssignToAgendaService.isAssignedToAgenda(protocolDocument.getProtocol());
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
        boolean result = protocolAssignToAgendaService.isAssignedToAgenda(protocolDocument.getProtocol());
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
        String result = protocolAssignToAgendaService.getAssignToAgendaComments(protocolDocument.getProtocol());
        assertEquals(comments, result);
    }

    @Test
    public void testGetAssignedCommitteeId() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        ProtocolSubmission submission = createSubmission(protocolDocument.getProtocol(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
        protocolDocument.getProtocol().getProtocolSubmissions().add(submission);
        String committeeId = protocolAssignToAgendaService.getAssignedCommitteeId(protocolDocument.getProtocol());
        assertEquals("1", committeeId);
    }

    @Test
    public void testGetAssignedCommitteeName() throws Exception {
        
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        ProtocolSubmission submission = createSubmission(protocolDocument.getProtocol(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
        
        Committee com = new Committee();
        com.setCommitteeId("1");
        com.setHomeUnitNumber("000001");
        com.setCommitteeTypeCode("1");
        com.setReviewTypeCode("1");
        String passedInCommitteeName = "testCommitteeName";
        com.setCommitteeName(passedInCommitteeName);
        submission.setCommittee(com);
        
        DocumentHeader documentHeader = new DocumentHeader();
        documentHeader.setDocumentNumber("101");
        documentHeader.setDocumentDescription("super cool description");
        CommitteeDocument cd = new CommitteeDocument();
        cd.setCommittee(com);
        cd.setDocumentNumber("1");
        cd.setDocumentHeader(documentHeader);
        cd.setUpdateTimestamp(new Timestamp(20100305));
        cd.setUpdateUser("quickstart");
        cd.setVersionNumber(new Long(1));
        com.setCommitteeDocument(cd);
        
        businessObjectService.save(documentHeader);
        businessObjectService.save(cd);
        businessObjectService.save(com);
        
        protocolDocument.getProtocol().getProtocolSubmissions().add(submission);
        
        documentService.saveDocument(protocolDocument);
        
        String committeeName = protocolAssignToAgendaService.getAssignedCommitteeName(protocolDocument.getProtocol());
        assertEquals(passedInCommitteeName, committeeName);
    }

    @Test
    public void testGetAssignedScheduleDate() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        ProtocolSubmission submission = createSubmission(protocolDocument.getProtocol(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
        
                
        Committee com = new Committee();
        com.setCommitteeId("1");
        com.setId(new Long(1));
        com.setHomeUnitNumber("000001");
        com.setCommitteeTypeCode("1");
        com.setReviewTypeCode("1");
        com.setAdvancedSubmissionDaysRequired(new Integer(1));
        String passedInCommitteeName = "testCommitteeName";
        com.setCommitteeName(passedInCommitteeName);
        submission.setCommittee(com);
        
        CommitteeSchedule cs = new CommitteeSchedule();
        cs.setCommittee(com);
        cs.setCommitteeIdFk(com.getId());
        cs.setScheduleId("1069");
        java.sql.Date basicDate = new java.sql.Date(2010, 3, 17);
        cs.setAgendaProdRevDate(basicDate);
        cs.setId(new Long(12345));
        cs.setScheduledDate(basicDate);
        cs.setProtocolSubDeadline(basicDate);
        cs.setScheduleStatusCode(new Integer(1));
        cs.setTime(new Timestamp(20100305));
        
        List<CommitteeSchedule> committeeSchedules = new ArrayList<CommitteeSchedule>();
        committeeSchedules.add(cs);
        com.setCommitteeSchedules(committeeSchedules);
        
        
        DocumentHeader documentHeader = new DocumentHeader();
        documentHeader.setDocumentNumber("101");
        documentHeader.setDocumentDescription("super cool description");
        CommitteeDocument cd = new CommitteeDocument();
        cd.setCommittee(com);
        cd.setDocumentNumber("1");
        cd.setDocumentHeader(documentHeader);
        cd.setUpdateTimestamp(new Timestamp(20100305));
        cd.setUpdateUser("quickstart");
        cd.setVersionNumber(new Long(1));
        com.setCommitteeDocument(cd);
        
        businessObjectService.save(documentHeader);
        businessObjectService.save(cd);
        businessObjectService.save(com);
        businessObjectService.save(cs);
        
        submission.setScheduleId(cs.getScheduleId());
        
        protocolDocument.getProtocol().getProtocolSubmissions().add(submission);
        
        documentService.saveDocument(protocolDocument);
        
        String agendaDate = protocolAssignToAgendaService.getAssignedScheduleDate(protocolDocument.getProtocol());
        
        assertNotNull(agendaDate);
    }
    
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
        if (StringUtils.equals(statusCode, ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE)) {
            submission.setCommitteeId("1");
        }
        return submission;
    }
}
