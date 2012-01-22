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
package org.kuali.kra.irb.actions.genericactions;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolVersionService;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.correspondence.ProtocolActionCorrespondenceGenerationService;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.actions.submit.ProtocolReviewType;
import org.kuali.kra.irb.actions.submit.ProtocolReviewerBean;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionQualifierType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitAction;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitActionService;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.krad.bo.AdHocRouteRecipient;
import org.kuali.rice.krad.service.DocumentService;

public class ProtocolGenericActionsServiceTest extends KcUnitTestBase {

    private static final String BASIC_COMMENT = "some dummy comments here";
    private static final Date BASIC_ACTION_DATE = new Date(System.currentTimeMillis());
    
    private ProtocolGenericActionServiceImpl service;
    
    private Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        service = new ProtocolGenericActionServiceImpl();
        service.setProtocolActionService(KraServiceLocator.getService(ProtocolActionService.class));
        service.setDocumentService(KraServiceLocator.getService(DocumentService.class));
        service.setProtocolActionCorrespondenceGenerationService(getMockProtocolActionCorrespondenceGenerationService());
        service.setProtocolOnlineReviewService(getMockProtocolOnlineReviewService());
        service.setProtocolVersionService(KraServiceLocator.getService(ProtocolVersionService.class));
        service.setKcNotificationService(getMockKcNotificationService());
    }

    @Override
    @After
    public void tearDown() throws Exception {
        service = null;
        
        super.tearDown();
    }

    @Test
    public void testClosedByPI() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        ProtocolActionType prevType = new ProtocolActionType();
        prevType.setProtocolActionTypeCode(ProtocolActionType.REQUEST_TO_CLOSE);
        ProtocolAction pa = new ProtocolAction();
        pa.setProtocolActionType(prevType);
        pa.setProtocolActionTypeCode(prevType.getProtocolActionTypeCode());
        pa.setProtocolId(protocolDocument.getProtocol().getProtocolId());
        pa.setProtocolNumber(protocolDocument.getProtocol().getProtocolNumber());
        pa.setActionId(123);
        pa.setActualActionDate(new Timestamp(System.currentTimeMillis()));
        protocolDocument.getProtocol().getProtocolActions().add(pa);
        
        ProtocolGenericActionBean actionBean = getMockProtocolGenericActionBean();
        service.close(protocolDocument.getProtocol(), actionBean);

        String expected = ProtocolStatus.CLOSED_BY_INVESTIGATOR;
        assertEquals(expected, protocolDocument.getProtocol().getProtocolStatus().getProtocolStatusCode());
    }
    
    @Test
    public void testClosedAdministratively() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        ProtocolActionType prevType = new ProtocolActionType();
        prevType.setProtocolActionTypeCode(ProtocolActionType.APPROVED);
        ProtocolAction pa = new ProtocolAction();
        pa.setProtocolActionType(prevType);
        pa.setProtocolActionTypeCode(prevType.getProtocolActionTypeCode());
        pa.setProtocolId(protocolDocument.getProtocol().getProtocolId());
        pa.setProtocolNumber(protocolDocument.getProtocol().getProtocolNumber());
        pa.setActionId(1234);
        pa.setActualActionDate(new Timestamp(System.currentTimeMillis()));
        protocolDocument.getProtocol().getProtocolActions().add(pa);
        
        ProtocolGenericActionBean actionBean = getMockProtocolGenericActionBean();
        service.close(protocolDocument.getProtocol(), actionBean);
        
        String expected = ProtocolStatus.CLOSED_ADMINISTRATIVELY;
        assertEquals(expected, protocolDocument.getProtocol().getProtocolStatus().getProtocolStatusCode());
    }

    @Test
    public void testCloseEnrollment() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        ProtocolGenericActionBean actionBean = getMockProtocolGenericActionBean();
        service.closeEnrollment(protocolDocument.getProtocol(), actionBean);
        
        String expected = ProtocolStatus.ACTIVE_CLOSED_TO_ENROLLMENT;
        assertEquals(expected, protocolDocument.getProtocol().getProtocolStatus().getProtocolStatusCode());
    }

    @Test
    public void testExpire() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        ProtocolGenericActionBean actionBean = getMockProtocolGenericActionBean();
        service.expire(protocolDocument.getProtocol(), actionBean);
        
        String expected = ProtocolStatus.EXPIRED;
        assertEquals(expected, protocolDocument.getProtocol().getProtocolStatus().getProtocolStatusCode());
    }
    
    @Test
    public void testIrbAcknowledgement() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        ProtocolSubmissionType prevSubmissionType = new ProtocolSubmissionType();
        prevSubmissionType.setSubmissionTypeCode(ProtocolSubmissionType.INITIAL_SUBMISSION);
        ProtocolReviewType prevReviewType = new ProtocolReviewType();
        prevReviewType.setReviewTypeCode(ProtocolReviewType.FULL_TYPE_CODE);
        ProtocolSubmission submission = new ProtocolSubmission();
        submission.setProtocolSubmissionType(prevSubmissionType);
        submission.setSubmissionTypeCode(prevSubmissionType.getSubmissionTypeCode());
        submission.setProtocolReviewType(prevReviewType);
        submission.setProtocolReviewTypeCode(prevReviewType.getReviewTypeCode());
        submission.setProtocolId(protocolDocument.getProtocol().getProtocolId());
        submission.setProtocolNumber(protocolDocument.getProtocol().getProtocolNumber());
        submission.setSubmissionId(1234L);
        submission.setSubmissionNumber(1);
        submission.setSubmissionDate(new Date(System.currentTimeMillis()));
        protocolDocument.getProtocol().getProtocolSubmissions().add(submission);
        
        ProtocolGenericActionBean actionBean = getMockProtocolGenericActionBean();
        service.irbAcknowledgement(protocolDocument.getProtocol(), actionBean);
        
        String expected = ProtocolSubmissionStatus.IRB_ACKNOWLEDGEMENT;
        assertEquals(expected, protocolDocument.getProtocol().getProtocolSubmission().getSubmissionStatusCode());
    }

    @Test
    public void testPermitDataAnalysis() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        ProtocolGenericActionBean actionBean = getMockProtocolGenericActionBean();
        service.permitDataAnalysis(protocolDocument.getProtocol(), actionBean);
        
        String expected = ProtocolStatus.ACTIVE_DATA_ANALYSIS_ONLY;
        assertEquals(expected, protocolDocument.getProtocol().getProtocolStatus().getProtocolStatusCode());
    }

    @Test
    public void testReopen() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        ProtocolGenericActionBean actionBean = getMockProtocolGenericActionBean();
        service.reopenEnrollment(protocolDocument.getProtocol(), actionBean);
        
        String expected = ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT;
        assertEquals(expected, protocolDocument.getProtocol().getProtocolStatus().getProtocolStatusCode());
    }
    
    @Test
    public void testSuspendByPI() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        ProtocolActionType prevType = new ProtocolActionType();
        prevType.setProtocolActionTypeCode(ProtocolActionType.REQUEST_FOR_SUSPENSION);
        ProtocolAction pa = new ProtocolAction();
        pa.setProtocolActionType(prevType);
        pa.setProtocolActionTypeCode(prevType.getProtocolActionTypeCode());
        pa.setProtocolId(protocolDocument.getProtocol().getProtocolId());
        pa.setProtocolNumber(protocolDocument.getProtocol().getProtocolNumber());
        pa.setActionId(123);
        pa.setActualActionDate(new Timestamp(System.currentTimeMillis()));
        protocolDocument.getProtocol().getProtocolActions().add(pa);
        
        ProtocolGenericActionBean actionBean = getMockProtocolGenericActionBean();
        service.suspend(protocolDocument.getProtocol(), actionBean);
        
        String expected = ProtocolStatus.SUSPENDED_BY_PI;
        assertEquals(expected, protocolDocument.getProtocol().getProtocolStatus().getProtocolStatusCode());
    }
    
    @Test
    public void testSuspendByIRB() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        ProtocolActionType prevType = new ProtocolActionType();
        prevType.setProtocolActionTypeCode(ProtocolActionType.APPROVED);
        ProtocolAction pa = new ProtocolAction();
        pa.setProtocolActionType(prevType);
        pa.setProtocolActionTypeCode(prevType.getProtocolActionTypeCode());
        pa.setProtocolId(protocolDocument.getProtocol().getProtocolId());
        pa.setProtocolNumber(protocolDocument.getProtocol().getProtocolNumber());
        pa.setActionId(1234);
        pa.setActualActionDate(new Timestamp(System.currentTimeMillis()));
        protocolDocument.getProtocol().getProtocolActions().add(pa);
        
        ProtocolGenericActionBean actionBean = getMockProtocolGenericActionBean();
        service.suspend(protocolDocument.getProtocol(), actionBean);
        
        String expected = ProtocolStatus.SUSPENDED_BY_IRB;
        assertEquals(expected, protocolDocument.getProtocol().getProtocolStatus().getProtocolStatusCode());
    }

    @Test
    public void testSuspendByDsmb() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        ProtocolGenericActionBean actionBean = getMockProtocolGenericActionBean();
        service.suspendByDsmb(protocolDocument.getProtocol(), actionBean);
        
        String expected = ProtocolStatus.SUSPENDED_BY_DSMB;
        assertEquals(expected, protocolDocument.getProtocol().getProtocolStatus().getProtocolStatusCode());
    }

    @Test
    public void testTerminate() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        ProtocolGenericActionBean actionBean = getMockProtocolGenericActionBean();
        service.terminate(protocolDocument.getProtocol(), actionBean);
        
        String expected = ProtocolStatus.TERMINATED_BY_IRB;
        assertEquals(expected, protocolDocument.getProtocol().getProtocolStatus().getProtocolStatusCode());
    }
    
    @Test
    public void testDisapprove() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        ProtocolSubmitActionService protocolSubmitActionService = KraServiceLocator.getService(ProtocolSubmitActionService.class);
        protocolSubmitActionService.submitToIrbForReview(protocolDocument.getProtocol(), getMockProtocolSubmitAction());
        DocumentService documentService = KraServiceLocator.getService(DocumentService.class);
        documentService.routeDocument(protocolDocument.getProtocol().getProtocolDocument(), "Initial Document Route", new ArrayList<AdHocRouteRecipient>());
        
        ProtocolGenericActionBean actionBean = getMockProtocolGenericActionBean();
        service.disapprove(protocolDocument.getProtocol(), actionBean);
        
        String expected = ProtocolStatus.DISAPPROVED;
        assertEquals(expected, protocolDocument.getProtocol().getProtocolStatus().getProtocolStatusCode());
        assertTrue(protocolDocument.getProtocol().getProtocolDocument().getDocumentHeader().getWorkflowDocument().isDisapproved());
    }
    
    @Test
    public void testReturnForSMR() throws Exception {
        ProtocolDocument oldDocument = ProtocolFactory.createProtocolDocument();
        
        ProtocolGenericActionBean actionBean = getMockProtocolGenericActionBean();
        ProtocolDocument newDocument = service.returnForSMR(oldDocument.getProtocol(), actionBean);
        
        String expectedStatus = ProtocolStatus.SPECIFIC_MINOR_REVISIONS_REQUIRED;
        String unexpectedDocumentNumber = oldDocument.getDocumentNumber();
        assertEquals(expectedStatus, newDocument.getProtocol().getProtocolStatus().getProtocolStatusCode());
        assertTrue(oldDocument.getDocumentHeader().getWorkflowDocument().isCanceled());
        assertNotSame(unexpectedDocumentNumber, newDocument.getDocumentNumber());
    }
    
    @Test
    public void testReturnForSRR() throws Exception {
        ProtocolDocument oldDocument = ProtocolFactory.createProtocolDocument();
        
        ProtocolGenericActionBean actionBean = getMockProtocolGenericActionBean();
        ProtocolDocument newDocument = service.returnForSRR(oldDocument.getProtocol(), actionBean);
        
        String expectedStatus = ProtocolStatus.SUBSTANTIVE_REVISIONS_REQUIRED;
        String unexpectedDocumentNumber = oldDocument.getDocumentNumber();
        assertEquals(expectedStatus, newDocument.getProtocol().getProtocolStatus().getProtocolStatusCode());
        assertTrue(oldDocument.getDocumentHeader().getWorkflowDocument().isCanceled());
        assertNotSame(unexpectedDocumentNumber, newDocument.getDocumentNumber());
    }
    
    private ProtocolActionCorrespondenceGenerationService getMockProtocolActionCorrespondenceGenerationService() {
        final ProtocolActionCorrespondenceGenerationService service = context.mock(ProtocolActionCorrespondenceGenerationService.class);
        
        context.checking(new Expectations() {{
            ignoring(service);
        }});
        
        return service;
    }
    
    private ProtocolOnlineReviewService getMockProtocolOnlineReviewService() {
        final ProtocolOnlineReviewService service = context.mock(ProtocolOnlineReviewService.class);
        
        context.checking(new Expectations() {{
            ignoring(service);
        }});
        
        return service;
    }
    
    private ProtocolGenericActionBean getMockProtocolGenericActionBean(){
        final ProtocolGenericActionBean bean = context.mock(ProtocolGenericActionBean.class);
        
        context.checking(new Expectations() {{
            allowing(bean).getComments();
            will(returnValue(BASIC_COMMENT));
            
            allowing(bean).getActionDate();
            will(returnValue(BASIC_ACTION_DATE));
        }});
        
        return bean;
    }
    
    private ProtocolSubmitAction getMockProtocolSubmitAction() {
        final ProtocolSubmitAction action = context.mock(ProtocolSubmitAction.class);
        
        context.checking(new Expectations() {{
            allowing(action).getSubmissionTypeCode();
            will(returnValue(ProtocolSubmissionType.INITIAL_SUBMISSION));
            
            allowing(action).getProtocolReviewTypeCode();
            will(returnValue(ProtocolReviewType.FULL_TYPE_CODE));
            
            allowing(action).getSubmissionQualifierTypeCode();
            will(returnValue(ProtocolSubmissionQualifierType.ANNUAL_SCHEDULED_BY_IRB));
            
            allowing(action).getNewCommitteeId();
            will(returnValue(Constants.EMPTY_STRING));
            
            allowing(action).getNewScheduleId();
            will(returnValue(Constants.EMPTY_STRING));
            
            allowing(action).getReviewers();
            will(returnValue(new ArrayList<ProtocolReviewerBean>()));
        }});

        return action;
    }
    
    private KcNotificationService getMockKcNotificationService() {
        final KcNotificationService service = context.mock(KcNotificationService.class);
        
        context.checking(new Expectations() {{
            ignoring(service);
        }});
        
        return service;
    }

}