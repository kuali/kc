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
package org.kuali.kra.irb.actions.notifyirb;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.test.CommitteeFactory;
import org.kuali.kra.committee.web.struts.form.schedule.Time12HrFmt;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolSubmissionDoc;
import org.kuali.kra.irb.actions.notification.ProtocolActionsNotificationService;
import org.kuali.kra.irb.actions.request.MockFormFile;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.actions.submit.ProtocolReviewType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionQualifierType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;

/**
 * Test the Protocol NotifyIrb Service Implementation.
 */
public class ProtocolNotifyIrbServiceTest extends KcUnitTestBase {

    private static final String COMMITTEE_ID = "913";
    private static final String COMMENT = "my test reason";
    
    private ProtocolNotifyIrbServiceImpl service;
    private BusinessObjectService businessObjectService;
    
    private Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        service = new ProtocolNotifyIrbServiceImpl();
        service.setProtocolActionService(KraServiceLocator.getService(ProtocolActionService.class));
        service.setDocumentService(getMockDocumentService());
        
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        service.setBusinessObjectService(businessObjectService);
    }

    @After
    public void tearDown() throws Exception {
        service = null;
        businessObjectService = null;
        
        super.tearDown();
    }
    
    @Test
    public void testRequestWithNoCommitteeNoFile() throws WorkflowException {
        runTest(getMockProtocolNotifyIrbBean(Constants.EMPTY_STRING));
    }
    
    @Test
    public void testRequestWithCommitteeAndFile() throws WorkflowException {
        ProtocolActionAttachment attachment = new ProtocolActionAttachment();
        attachment.setFile(new MockFormFile());
        ProtocolNotifyIrbBean notifyIrbBean = getMockProtocolNotifyIrbBean(COMMITTEE_ID, attachment);
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
        
        service.submitIrbNotification(protocolDocument.getProtocol(), notifyIrbBean);
    
        ProtocolSubmission protocolSubmission = protocolDocument.getProtocol().getProtocolSubmission();
        assertNotNull(protocolSubmission);
        
        ProtocolAction protocolAction = protocolDocument.getProtocol().getLastProtocolAction();
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

    /*
     * Verify that the created submission and protocol action is what is expected
     * based upon the "submitAction".
     */
    private void verifySubmission(ProtocolSubmission submission, Protocol protocol, ProtocolNotifyIrbBean requestBean, CommitteeDocument committeeDocument) {
        
        assertEquals(protocol.getProtocolNumber(), submission.getProtocolNumber());
        assertEquals(protocol.getSequenceNumber(), submission.getSequenceNumber());
        assertEquals(new Integer(1), submission.getSubmissionNumber());
        
        assertEquals(ProtocolSubmissionType.NOTIFY_IRB, submission.getSubmissionTypeCode());
        assertEquals(ProtocolReviewType.FYI_TYPE_CODE, submission.getProtocolReviewTypeCode());
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
        if (requestBean.getActionAttachments().isEmpty()) {
            assertNull(doc);
        }
        else {
            assertEquals(requestBean.getActionAttachments().get(0).getFile().getFileName(), doc.getFileName());
            assertEquals(requestBean.getActionAttachments().get(0).getFile().getFileSize(), doc.getDocument().length);
        }
    }
    
    /*
     * Verfy that the Protocol Action is correct.
     */
    private void verifyAction(ProtocolAction action, ProtocolNotifyIrbBean requestBean, ProtocolSubmission submission) {
        assertEquals(ProtocolActionType.NOTIFY_IRB, action.getProtocolActionTypeCode());
        assertEquals(submission.getSubmissionId(), action.getSubmissionIdFk());
        assertEquals(COMMENT, action.getComments());
    }

    /*
     * If a string is null, convert it to an empty string.
     */
    private String convert(String s) {
        if (s == null) return "";
        return s;
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
    
    private DocumentService getMockDocumentService() {
        final DocumentService service = context.mock(DocumentService.class);
        
        context.checking(new Expectations() {{
            ignoring(service);
        }});
        
        return service;
    }
    
    private ProtocolActionsNotificationService getMockProtocolActionsNotificationService() {
        final ProtocolActionsNotificationService service = context.mock(ProtocolActionsNotificationService.class);
        
        context.checking(new Expectations() {{
            ignoring(service);
        }});
        
        return service;
    }
    
    private ProtocolNotifyIrbBean getMockProtocolNotifyIrbBean(final String committeeId, final ProtocolActionAttachment... attachments) {
        final ProtocolNotifyIrbBean bean = context.mock(ProtocolNotifyIrbBean.class);
        
        context.checking(new Expectations() {{
            allowing(bean).getSubmissionQualifierTypeCode();
            will(returnValue(ProtocolSubmissionQualifierType.AE_UADE));
            
            allowing(bean).getReviewTypeCode();
            will(returnValue(ProtocolReviewType.FYI_TYPE_CODE));
            
            allowing(bean).getCommitteeId();
            will(returnValue(committeeId));
            
            allowing(bean).getComment();
            will(returnValue(COMMENT));
            
            allowing(bean).getActionAttachments();
            will(returnValue(Arrays.asList(attachments)));
            
            allowing(bean).getAnswerHeaders();
            will(returnValue(new ArrayList<AnswerHeader>()));
        }});
        
        return bean;
    }
    
}