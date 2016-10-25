/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
package org.kuali.kra.irb.actions.request;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.upload.FormFile;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.committee.impl.web.struts.form.schedule.Time12HrFmt;
import org.kuali.coeus.org.jmock.lib.legacy.ClassImposteriser;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.test.MockFormFile;
import org.kuali.kra.bo.DocumentNextvalue;
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
import java.util.*;

import static org.junit.Assert.*;
// import org.kuali.kra.irb.actions.notifyirb.ProtocolActionAttachment;

/**
 * Test the ProtocolRequestService implementation.
 * 
 * For each of the below tests, the submitRequest() method is
 * invoked.  This method has no return value.  Rather, this method is
 * simply creating database entries for the submission.  After calling
 * the submitRequest(), a check is done against the database to
 * verify that the changes occurred as expected.
*/
public class ProtocolRequestServiceTest extends KcIntegrationTestBase {

    private static final String COMMITTEE_ID = "925";
    private static final String REASON = "my test reason";
    
    private ProtocolRequestServiceImpl service;
    private BusinessObjectService businessObjectService;
    private DocumentService documentService;
    
    private Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
        setThreadingPolicy(new Synchroniser());
    }};
   
    @Before
    public void setUp() throws Exception {

        businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);

        service = new ProtocolRequestServiceImpl();
        service.setBusinessObjectService(businessObjectService);
        service.setProtocolActionService(KcServiceLocator.getService(ProtocolActionService.class));
        service.setDocumentService(KcServiceLocator.getService(DocumentService.class));
        
        documentService = KcServiceLocator.getService(DocumentService.class);
    }

    @After
    public void tearDown() throws Exception {
        service = null;
        businessObjectService = null;
        
    }
    
    @Test
    public void testDumbTest(){
        assertTrue(true);
    }
    
    /*
     * Test a Request for Close with no committee and no file attachment.
     * The other requests, e.g. Suspension, are actually the same and therefore
     * do not need to be tested.  The only difference is the ProtocolActionType
     * and ProtocolSubmissionType which is trivial.
     */
    @Test
    public void testRequestWithNoCommitteeNoFile() throws WorkflowException {
        ProtocolRequestBean closeRequest = getMockProtocolRequestBean(ProtocolActionType.REQUEST_TO_CLOSE, ProtocolSubmissionType.REQUEST_TO_CLOSE, 
                Constants.EMPTY_STRING, "protocolCloseRequestBean");
        
        runTest(Constants.EMPTY_STRING, null, closeRequest);
    }
    
    /*
     * Test a Request for Close with a committee and an attached file.
     * The other requests, e.g. Suspension, are actually the same and therefore
     * do not need to be tested.  The only difference is the ProtocolActionType
     * and ProtocolSubmissionType which is trivial.
     */
    @Test
    public void testRequest() throws Exception {
        ProtocolActionAttachment attachment = new ProtocolActionAttachment();
        MockFormFile formFile = new MockFormFile();
        formFile.setFileData(new byte[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9});
        formFile.setFileSize(formFile.getFileData().length);
        formFile.setFileName("test");
        attachment.setFile(formFile);
        ProtocolRequestBean closeRequest = getMockProtocolRequestBean(ProtocolActionType.REQUEST_TO_CLOSE, ProtocolSubmissionType.REQUEST_TO_CLOSE, 
                COMMITTEE_ID, "protocolCloseRequestBean", attachment);
        
        runTest(COMMITTEE_ID, null, closeRequest);
    }
    
    /*
     * Runs a test for the configuration defined by the input parameters.
     */
    private void runTest(String committeeId, FormFile formFile, ProtocolRequestBean requestBean) throws WorkflowException {
        
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        List<DocumentNextvalue> documentNextvalues = new ArrayList<DocumentNextvalue>();
        DocumentNextvalue dnv1 = new DocumentNextvalue();
        dnv1.setDocumentKey("123456");
        dnv1.setPropertyName("submissionNumber");
        dnv1.setNextValue(new Integer(1));
        documentNextvalues.add(dnv1);
        
        DocumentNextvalue dnv2 = new DocumentNextvalue();
        dnv2.setDocumentKey("123456");
        dnv2.setPropertyName("submissionDocId");
        dnv2.setNextValue(new Integer(1));
        documentNextvalues.add(dnv2);
        
        DocumentNextvalue dnv3 = new DocumentNextvalue();
        dnv3.setDocumentKey("123456");
        dnv3.setPropertyName("actionId");
        dnv3.setNextValue(new Integer(1));
        documentNextvalues.add(dnv3);

        
        protocolDocument.setDocumentNextvalues(documentNextvalues);
        
        protocolDocument.getProtocol().setProtocolDocument(protocolDocument);
        
        CommitteeDocument committeeDocument = null;
        if (!StringUtils.isBlank(committeeId)) {
            committeeDocument = createCommittee(committeeId);
        }
        service.submitRequest(protocolDocument.getProtocol(), requestBean);
        
        ProtocolSubmission protocolSubmission = protocolDocument.getProtocol().getProtocolSubmission();
        assertNotNull(protocolSubmission);
        
        ProtocolAction protocolAction = protocolDocument.getProtocol().getLastProtocolAction();
        assertNotNull(protocolAction);
        
        verifyAction(protocolAction, requestBean, protocolSubmission);
        verifySubmission(protocolSubmission, protocolDocument.getProtocol(), requestBean, committeeDocument);
        verifySubmissionDoc(protocolSubmission, requestBean);
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
    private void verifySubmission(ProtocolSubmission submission, Protocol protocol, ProtocolRequestBean requestBean, CommitteeDocument committeeDocument) {
        
        assertEquals(protocol.getProtocolNumber(), submission.getProtocolNumber());
        assertEquals(protocol.getSequenceNumber(), submission.getSequenceNumber());
        assertEquals(new Integer(1), submission.getSubmissionNumber());
        
        assertEquals(requestBean.getSubmissionTypeCode(), submission.getSubmissionTypeCode());
        assertEquals(ProtocolReviewType.FULL_TYPE_CODE, submission.getProtocolReviewTypeCode());
        assertEquals(ProtocolSubmissionStatus.PENDING, submission.getSubmissionStatusCode());
       
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
    private void verifySubmissionDoc(ProtocolSubmission protocolSubmission, ProtocolRequestBean requestBean) {
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
    private void verifyAction(ProtocolAction action, ProtocolRequestBean requestBean, ProtocolSubmission submission) {
        assertEquals(requestBean.getProtocolActionTypeCode(), action.getProtocolActionTypeCode());
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
    
    private ProtocolRequestBean getMockProtocolRequestBean(final String protocolActionTypeCode, final String submissionTypeCode, final String committeeId, 
            final String beanName, final ProtocolActionAttachment... attachments) {
        
        final ProtocolRequestBean bean = context.mock(ProtocolRequestBean.class);
        final Protocol protocol = new Protocol();
        protocol.setProtocolNumber("13049581");
        
        context.checking(new Expectations() {{
            allowing(bean).getProtocolActionTypeCode();
            will(returnValue(protocolActionTypeCode));
            
            allowing(bean).getSubmissionTypeCode();
            will(returnValue(submissionTypeCode));
            
            allowing(bean).getCommitteeId();
            will(returnValue(committeeId));
            
            allowing(bean).getReason();
            will(returnValue(REASON));
            
            allowing(bean).getBeanName();
            will(returnValue(beanName));
            
            allowing(bean).getActionAttachments();
            will(returnValue(Arrays.asList(attachments)));
            
            allowing(bean).getQuestionnaireHelper();
            will(returnValue(new IrbSubmissionQuestionnaireHelper(protocol, protocolActionTypeCode, null, false)));
        }});
        
        return bean;
    }
    
}
