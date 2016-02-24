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
package org.kuali.kra.irb.actions.genericactions;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolVersionService;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.submit.*;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.krad.bo.AdHocRouteRecipient;
import org.kuali.rice.krad.service.DocumentService;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import static org.junit.Assert.*;
public class ProtocolGenericActionsServiceTest extends KcIntegrationTestBase {

    private static final String BASIC_COMMENT = "some dummy comments here";
    private static final Date BASIC_ACTION_DATE = new Date(System.currentTimeMillis());
    
    private ProtocolGenericActionServiceImpl service;
    
    private Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
        setThreadingPolicy(new Synchroniser());
    }};

    @Before
    public void setUp() throws Exception {

        service = new ProtocolGenericActionServiceImpl();
        service.setProtocolActionService(KcServiceLocator.getService(ProtocolActionService.class));
        service.setDocumentService(KcServiceLocator.getService(DocumentService.class));
        service.setProtocolOnlineReviewService(getMockProtocolOnlineReviewService());
        service.setProtocolVersionService(KcServiceLocator.getService(ProtocolVersionService.class));
    }

    @After
    public void tearDown() throws Exception {
        service = null;
        
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
        submission.setSubmissionStatusCode(ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
        protocolDocument.getProtocol().getProtocolSubmissions().add(submission);
        submission.setProtocol(protocolDocument.getProtocol());
        KNSServiceLocator.getBusinessObjectService().save(submission);
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
        
        ProtocolSubmitActionService protocolSubmitActionService = KcServiceLocator.getService(ProtocolSubmitActionService.class);
        protocolSubmitActionService.submitToIrbForReview(protocolDocument.getProtocol(), getMockProtocolSubmitAction());
        DocumentService documentService = KcServiceLocator.getService(DocumentService.class);
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

}
