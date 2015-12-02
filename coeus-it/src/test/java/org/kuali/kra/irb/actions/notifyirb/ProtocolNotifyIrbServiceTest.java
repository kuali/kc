/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.irb.actions.notifyirb;

import org.apache.commons.lang3.StringUtils;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.committee.impl.web.struts.form.schedule.Time12HrFmt;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.test.CommitteeFactory;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolSubmissionDoc;
import org.kuali.kra.irb.actions.request.MockFormFile;
import org.kuali.kra.irb.actions.submit.*;
import org.kuali.kra.irb.questionnaire.IrbSubmissionQuestionnaireHelper;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.protocol.actions.notify.ProtocolActionAttachment;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
/**
 * Test the Protocol NotifyIrb Service Implementation.
 */
public class ProtocolNotifyIrbServiceTest extends KcIntegrationTestBase {

    private static final String COMMITTEE_ID = "913";
    private static final String COMMENT = "my test reason";
    
    private ProtocolNotifyIrbServiceImpl service;
    private BusinessObjectService businessObjectService;
    private DocumentService documentService;
    
    private Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
        setThreadingPolicy(new Synchroniser());
    }};
    
    @Before
    public void setUp() throws Exception {

        service = new ProtocolNotifyIrbServiceImpl();
        service.setProtocolActionService(KcServiceLocator.getService(ProtocolActionService.class));
        service.setDocumentService(getMockDocumentService());
        
        businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        service.setBusinessObjectService(businessObjectService);
        documentService = KcServiceLocator.getService(DocumentService.class);
    }

    @After
    public void tearDown() throws Exception {
        service = null;
        businessObjectService = null;
        
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
        documentService.saveDocument(committeeDocument);
        documentService.blanketApproveDocument(committeeDocument, "Test Committee", Collections.emptyList());
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
    
    
    private ProtocolNotifyIrbBean getMockProtocolNotifyIrbBean(final String committeeId, final ProtocolActionAttachment... attachments) {
        final ProtocolNotifyIrbBean bean = context.mock(ProtocolNotifyIrbBean.class);
        final Protocol protocol = new Protocol();
        protocol.setProtocolNumber("13049581");
        
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
            
            allowing(bean).getQuestionnaireHelper();
            will(returnValue(new IrbSubmissionQuestionnaireHelper(protocol, ProtocolActionType.NOTIFY_IRB, null, false)));
        }});
        
        return bean;
    }
    
}
