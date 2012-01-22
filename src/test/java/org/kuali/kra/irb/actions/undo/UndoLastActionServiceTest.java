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
package org.kuali.kra.irb.actions.undo;

import java.sql.Date;
import java.util.ArrayList;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.approve.ProtocolApproveBean;
import org.kuali.kra.irb.actions.approve.ProtocolApproveService;
import org.kuali.kra.irb.actions.assignagenda.ProtocolAssignToAgendaBean;
import org.kuali.kra.irb.actions.assignagenda.ProtocolAssignToAgendaService;
import org.kuali.kra.irb.actions.genericactions.ProtocolGenericActionBean;
import org.kuali.kra.irb.actions.genericactions.ProtocolGenericActionService;
import org.kuali.kra.irb.actions.notifyirb.ProtocolActionAttachment;
import org.kuali.kra.irb.actions.request.ProtocolRequestBean;
import org.kuali.kra.irb.actions.request.ProtocolRequestService;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.actions.submit.ProtocolReviewType;
import org.kuali.kra.irb.actions.submit.ProtocolReviewerBean;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionQualifierType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitAction;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitActionService;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.kra.util.DateUtils;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

/**
 * Test the ProtocolWithdrawService implementation.
 */
public class UndoLastActionServiceTest extends KcUnitTestBase {

    private static final String COMMENTS = "something silly";
    
    private static final String ASSIGN_TO_AGENDA_COMMENTS = "assigning to agenda";
    private static final Date ASSIGN_TO_AGENDA_ACTION_DATE = new Date(System.currentTimeMillis());
    private static final Date APPROVAL_ACTION_DATE = new Date(System.currentTimeMillis());
    private static final Date APPROVAL_APPROVAL_DATE = DateUtils.convertToSqlDate(DateUtils.addWeeks(APPROVAL_ACTION_DATE, -1));
    private static final Date APPROVAL_EXPIRATION_DATE = DateUtils.convertToSqlDate(DateUtils.addYears(APPROVAL_ACTION_DATE, 1));
    private static final String APPROVAL_COMMENTS = "approving";
    private static final String CLOSE_COMMENTS = "closing administratively";
    private static final String CLOSE_ENROLLMENT_COMMENTS = "closing enrollment";
    private static final Date CLOSE_ACTION_DATE = new Date(System.currentTimeMillis());

    private UndoLastActionServiceImpl service;
    private ProtocolSubmitActionService protocolSubmitActionService;
    private ProtocolAssignToAgendaService protocolAssignToAgendaService;
    private ProtocolApproveService protocolApproveService;
    private ProtocolRequestService protocolRequestService;
    private ProtocolGenericActionService protocolGenericActionService;
    
    private Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        service = new UndoLastActionServiceImpl();
        service.setProtocolActionService(KraServiceLocator.getService(ProtocolActionService.class));
        service.setBusinessObjectService(getMockBusinessObjectService());
        service.setDocumentService(getMockDocumentService());
        
        protocolSubmitActionService = KraServiceLocator.getService(ProtocolSubmitActionService.class);
        protocolAssignToAgendaService = KraServiceLocator.getService(ProtocolAssignToAgendaService.class);
        protocolApproveService = KraServiceLocator.getService(ProtocolApproveService.class);
        protocolRequestService = KraServiceLocator.getService(ProtocolRequestService.class);
        protocolGenericActionService = KraServiceLocator.getService(ProtocolGenericActionService.class);
    }

    @Override
    @After
    public void tearDown() throws Exception {
        service = null;
        protocolSubmitActionService = null;
        protocolAssignToAgendaService = null;
        protocolApproveService = null;
        protocolRequestService = null;
        protocolGenericActionService = null;
        
        super.tearDown();
    }

    @Test
    public void testUndoApproveAction() throws Exception {
        ProtocolDocument protocolDocument = getApprovedProtocolDocument();

        service.undoLastAction(protocolDocument, getMockUndoLastActionBean(protocolDocument.getProtocol()));

        assertEquals(ProtocolStatus.SUBMITTED_TO_IRB, protocolDocument.getProtocol().getProtocolStatusCode());
        
        assertTrue(!protocolDocument.getProtocol().getProtocolSubmissions().isEmpty());
        ProtocolSubmission submission = protocolDocument.getProtocol().getProtocolSubmission();
        assertNotNull(submission);
        assertEquals(ProtocolSubmissionStatus.IN_AGENDA, submission.getSubmissionStatusCode());
        
        assertTrue(!protocolDocument.getProtocol().getProtocolActions().isEmpty());
        ProtocolAction action = protocolDocument.getProtocol().getLastProtocolAction();
        assertNotNull(action);
        assertEquals(ProtocolActionType.ASSIGN_TO_AGENDA, action.getProtocolActionTypeCode());
        assertEquals(ASSIGN_TO_AGENDA_COMMENTS, action.getComments());
    }

    @Test
    public void testUndoRequestToCloseAction() throws Exception {
        ProtocolDocument protocolDocument = getApprovedProtocolDocument();

        ProtocolRequestBean requestToCloseBean = getMockProtocolRequestBean(ProtocolActionType.REQUEST_TO_CLOSE,
            ProtocolSubmissionType.REQUEST_TO_CLOSE, "protocolCloseRequestBean");
        protocolRequestService.submitRequest(protocolDocument.getProtocol(), requestToCloseBean);

        assertEquals(ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT, protocolDocument.getProtocol().getProtocolStatusCode());
        
        assertTrue(!protocolDocument.getProtocol().getProtocolSubmissions().isEmpty());
        ProtocolSubmission submission = protocolDocument.getProtocol().getProtocolSubmission();
        assertNotNull(submission);
        assertEquals(ProtocolSubmissionStatus.PENDING, submission.getSubmissionStatusCode());
        
        assertTrue(!protocolDocument.getProtocol().getProtocolActions().isEmpty());
        ProtocolAction action = protocolDocument.getProtocol().getLastProtocolAction();
        assertNotNull(action);
        assertEquals(ProtocolActionType.REQUEST_TO_CLOSE, action.getProtocolActionTypeCode());

        service.undoLastAction(protocolDocument, getMockUndoLastActionBean(protocolDocument.getProtocol()));

        assertEquals(ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT, protocolDocument.getProtocol().getProtocolStatusCode());
        
        assertTrue(!protocolDocument.getProtocol().getProtocolSubmissions().isEmpty());
        ProtocolSubmission lastSubmission = protocolDocument.getProtocol().getProtocolSubmission();
        assertNotNull(lastSubmission);
        assertEquals(ProtocolSubmissionStatus.APPROVED, lastSubmission.getSubmissionStatusCode());
        
        assertTrue(!protocolDocument.getProtocol().getProtocolActions().isEmpty());
        ProtocolAction lastAction = protocolDocument.getProtocol().getLastProtocolAction();
        assertNotNull(lastAction);
        assertEquals(ProtocolActionType.APPROVED, lastAction.getProtocolActionTypeCode());
        assertEquals(APPROVAL_COMMENTS, lastAction.getComments());
    }

    @Test
    public void testUndoCloseAction() throws Exception {
        ProtocolDocument protocolDocument = getApprovedProtocolDocument();

        ProtocolRequestBean requestToCloseBean = getMockProtocolRequestBean(ProtocolActionType.REQUEST_TO_CLOSE,
            ProtocolSubmissionType.REQUEST_TO_CLOSE, "protocolCloseRequestBean");
        protocolRequestService.submitRequest(protocolDocument.getProtocol(), requestToCloseBean);

        assertEquals(ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT, protocolDocument.getProtocol().getProtocolStatusCode());
        
        assertTrue(!protocolDocument.getProtocol().getProtocolSubmissions().isEmpty());
        ProtocolSubmission submission = protocolDocument.getProtocol().getProtocolSubmission();
        assertNotNull(submission);
        assertEquals(ProtocolSubmissionStatus.PENDING, submission.getSubmissionStatusCode());
        
        assertTrue(!protocolDocument.getProtocol().getProtocolActions().isEmpty());
        ProtocolAction action = protocolDocument.getProtocol().getLastProtocolAction();
        assertNotNull(action);
        assertEquals(ProtocolActionType.REQUEST_TO_CLOSE, action.getProtocolActionTypeCode());

        protocolGenericActionService.close(protocolDocument.getProtocol(), getMockGenericActionBean(CLOSE_COMMENTS));

        assertTrue(ProtocolStatus.CLOSED_ADMINISTRATIVELY.equals(protocolDocument.getProtocol().getProtocolStatusCode())
                || ProtocolStatus.CLOSED_BY_INVESTIGATOR.equals(protocolDocument.getProtocol().getProtocolStatusCode()));
        
        assertTrue(!protocolDocument.getProtocol().getProtocolSubmissions().isEmpty());
        ProtocolSubmission lastSubmission = protocolDocument.getProtocol().getProtocolSubmission();
        assertNotNull(lastSubmission);
        assertEquals(ProtocolSubmissionStatus.CLOSED, lastSubmission.getSubmissionStatusCode());
        
        assertTrue(!protocolDocument.getProtocol().getProtocolActions().isEmpty());
        ProtocolAction lastAction = protocolDocument.getProtocol().getLastProtocolAction();
        assertNotNull(lastAction);
        assertEquals(ProtocolActionType.CLOSED_ADMINISTRATIVELY_CLOSED, lastAction.getProtocolActionTypeCode());
        assertEquals(CLOSE_COMMENTS, lastAction.getComments());

        service.undoLastAction(protocolDocument, getMockUndoLastActionBean(protocolDocument.getProtocol()));

        assertEquals(ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT, protocolDocument.getProtocol().getProtocolStatusCode());
        
        assertTrue(!protocolDocument.getProtocol().getProtocolSubmissions().isEmpty());
        ProtocolSubmission lastLastSubmission = protocolDocument.getProtocol().getProtocolSubmission();
        assertNotNull(lastLastSubmission);
        assertEquals(ProtocolSubmissionStatus.PENDING, lastLastSubmission.getSubmissionStatusCode());
        
        assertTrue(!protocolDocument.getProtocol().getProtocolActions().isEmpty());
        ProtocolAction lastLastAction = protocolDocument.getProtocol().getLastProtocolAction();
        assertNotNull(lastLastAction);
        assertEquals(ProtocolActionType.REQUEST_TO_CLOSE, lastLastAction.getProtocolActionTypeCode());
    }

    @Test
    public void testUndoRequestForSuspensionAction() throws Exception {
        ProtocolDocument protocolDocument = getApprovedProtocolDocument();

        ProtocolRequestBean requestForSuspensionBean = getMockProtocolRequestBean(ProtocolActionType.REQUEST_FOR_SUSPENSION,
            ProtocolSubmissionType.REQUEST_FOR_SUSPENSION, "protocolSuspendRequestBean");
        protocolRequestService.submitRequest(protocolDocument.getProtocol(), requestForSuspensionBean);

        assertEquals(ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT, protocolDocument.getProtocol().getProtocolStatusCode());
        
        assertTrue(!protocolDocument.getProtocol().getProtocolSubmissions().isEmpty());
        ProtocolSubmission submission = protocolDocument.getProtocol().getProtocolSubmission();
        assertNotNull(submission);
        assertEquals(ProtocolSubmissionStatus.PENDING, submission.getSubmissionStatusCode());
        
        assertTrue(!protocolDocument.getProtocol().getProtocolActions().isEmpty());
        ProtocolAction action = protocolDocument.getProtocol().getLastProtocolAction();
        assertNotNull(action);
        assertEquals(ProtocolActionType.REQUEST_FOR_SUSPENSION, action.getProtocolActionTypeCode());

        service.undoLastAction(protocolDocument, getMockUndoLastActionBean(protocolDocument.getProtocol()));

        assertEquals(ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT, protocolDocument.getProtocol().getProtocolStatusCode());
        
        assertTrue(!protocolDocument.getProtocol().getProtocolSubmissions().isEmpty());
        ProtocolSubmission lastSubmission = protocolDocument.getProtocol().getProtocolSubmission();
        assertNotNull(lastSubmission);
        assertEquals(ProtocolSubmissionStatus.APPROVED, lastSubmission.getSubmissionStatusCode());
        
        assertTrue(!protocolDocument.getProtocol().getProtocolActions().isEmpty());
        ProtocolAction lastAction = protocolDocument.getProtocol().getLastProtocolAction();
        assertNotNull(lastAction);
        assertEquals(ProtocolActionType.APPROVED, lastAction.getProtocolActionTypeCode());
        assertEquals(APPROVAL_COMMENTS, lastAction.getComments());
    }
    
    @Test
    public void testUndoEnrollmentActions() throws Exception {
        ProtocolDocument protocolDocument = getApprovedProtocolDocument();

        ProtocolRequestBean requestToCloseBean = getMockProtocolRequestBean(ProtocolActionType.REQUEST_TO_CLOSE_ENROLLMENT,
            ProtocolSubmissionType.REQUEST_TO_CLOSE_ENROLLMENT, "protocolCloseEnrollmentRequestBean");
        protocolRequestService.submitRequest(protocolDocument.getProtocol(), requestToCloseBean);

        assertEquals(ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT, protocolDocument.getProtocol().getProtocolStatusCode());
        
        assertTrue(!protocolDocument.getProtocol().getProtocolSubmissions().isEmpty());
        ProtocolSubmission submission = protocolDocument.getProtocol().getProtocolSubmission();
        assertNotNull(submission);
        assertEquals(ProtocolSubmissionStatus.PENDING, submission.getSubmissionStatusCode());
        
        assertTrue(!protocolDocument.getProtocol().getProtocolActions().isEmpty());
        ProtocolAction action = protocolDocument.getProtocol().getLastProtocolAction();
        assertNotNull(action);
        assertEquals(ProtocolActionType.REQUEST_TO_CLOSE_ENROLLMENT, action.getProtocolActionTypeCode());

        protocolGenericActionService.closeEnrollment(protocolDocument.getProtocol(), getMockGenericActionBean(CLOSE_ENROLLMENT_COMMENTS));

        assertEquals(ProtocolStatus.ACTIVE_CLOSED_TO_ENROLLMENT, protocolDocument.getProtocol().getProtocolStatusCode());
        
        assertTrue(!protocolDocument.getProtocol().getProtocolSubmissions().isEmpty());
        ProtocolSubmission lastSubmission = protocolDocument.getProtocol().getProtocolSubmission();
        assertNotNull(lastSubmission);
        assertEquals(ProtocolSubmissionStatus.CLOSED_FOR_ENROLLMENT, lastSubmission.getSubmissionStatusCode());
        
        assertTrue(!protocolDocument.getProtocol().getProtocolActions().isEmpty());
        ProtocolAction lastAction = protocolDocument.getProtocol().getLastProtocolAction();
        assertNotNull(lastAction);
        assertEquals(ProtocolActionType.CLOSED_FOR_ENROLLMENT, lastAction.getProtocolActionTypeCode());
        assertEquals(CLOSE_ENROLLMENT_COMMENTS, lastAction.getComments());

        service.undoLastAction(protocolDocument, getMockUndoLastActionBean(protocolDocument.getProtocol()));

        assertEquals(ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT, protocolDocument.getProtocol().getProtocolStatusCode());
        
        assertTrue(!protocolDocument.getProtocol().getProtocolSubmissions().isEmpty());
        ProtocolSubmission lastLastSubmission = protocolDocument.getProtocol().getProtocolSubmission();
        assertNotNull(lastLastSubmission);
        assertEquals(ProtocolSubmissionStatus.PENDING, lastLastSubmission.getSubmissionStatusCode());
        
        assertTrue(!protocolDocument.getProtocol().getProtocolActions().isEmpty());
        ProtocolAction lastLastAction = protocolDocument.getProtocol().getLastProtocolAction();
        assertNotNull(lastLastAction);
        assertEquals(ProtocolActionType.REQUEST_TO_CLOSE_ENROLLMENT, lastLastAction.getProtocolActionTypeCode());
    }
    
    private ProtocolDocument getApprovedProtocolDocument() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        protocolSubmitActionService.submitToIrbForReview(protocolDocument.getProtocol(), getMockSubmitAction());
        
        assertEquals(ProtocolStatus.SUBMITTED_TO_IRB, protocolDocument.getProtocol().getProtocolStatusCode());

        protocolAssignToAgendaService.assignToAgenda(protocolDocument.getProtocol(), getMockAssignToAgendaBean());
        
        assertTrue(!protocolDocument.getProtocol().getProtocolSubmissions().isEmpty());
        ProtocolSubmission submission = protocolDocument.getProtocol().getProtocolSubmission();
        assertNotNull(submission);
        assertEquals(ProtocolSubmissionStatus.IN_AGENDA, submission.getSubmissionStatusCode());

        protocolApproveService.grantFullApproval(protocolDocument.getProtocol(), getMockApproveBean());
        
        assertEquals(ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT, protocolDocument.getProtocol().getProtocolStatusCode());
        
        assertTrue(!protocolDocument.getProtocol().getProtocolActions().isEmpty());
        ProtocolAction action = protocolDocument.getProtocol().getLastProtocolAction();
        assertNotNull(action);
        assertEquals(APPROVAL_APPROVAL_DATE, protocolDocument.getProtocol().getApprovalDate());
        assertEquals(APPROVAL_EXPIRATION_DATE, protocolDocument.getProtocol().getExpirationDate());
        assertEquals(APPROVAL_COMMENTS, action.getComments());
        assertEquals(APPROVAL_ACTION_DATE, action.getActionDate());
        
        assertTrue(!protocolDocument.getProtocol().getProtocolSubmissions().isEmpty());
        assertEquals(ProtocolSubmissionStatus.APPROVED, submission.getSubmissionStatusCode());

        return protocolDocument;
    }
    
    private BusinessObjectService getMockBusinessObjectService() {
        final BusinessObjectService service = context.mock(BusinessObjectService.class);
        
        context.checking(new Expectations() {{
            ignoring(service);
        }});
        
        return service;
    }
    
    private DocumentService getMockDocumentService() {
        final DocumentService service = context.mock(DocumentService.class);
        
        context.checking(new Expectations() {{
            ignoring(service);
        }});
        
        return service;
    }
    
    private ProtocolSubmitAction getMockSubmitAction() {
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
    
    private ProtocolAssignToAgendaBean getMockAssignToAgendaBean() {
        final ProtocolAssignToAgendaBean bean = context.mock(ProtocolAssignToAgendaBean.class);
        
        context.checking(new Expectations() {{
           allowing(bean).isProtocolAssigned();
           will(returnValue(true));
           
           allowing(bean).getActionDate();
           will(returnValue(ASSIGN_TO_AGENDA_ACTION_DATE));
           
           allowing(bean).getComments();
           will(returnValue(ASSIGN_TO_AGENDA_COMMENTS));
        }});
        
        return bean;
    }
    
    private ProtocolApproveBean getMockApproveBean() {
        final ProtocolApproveBean bean = context.mock(ProtocolApproveBean.class);
        
        context.checking(new Expectations() {{
            allowing(bean).getActionDate();
            will(returnValue(APPROVAL_ACTION_DATE));
            
            allowing(bean).getComments();
            will(returnValue(APPROVAL_COMMENTS));
            
            allowing(bean).getApprovalDate();
            will(returnValue(APPROVAL_APPROVAL_DATE));
            
            allowing(bean).getExpirationDate();
            will(returnValue(APPROVAL_EXPIRATION_DATE));
        }});
        
        return bean;
    }
    
    private UndoLastActionBean getMockUndoLastActionBean(final Protocol protocol) {
        final UndoLastActionBean bean = context.mock(UndoLastActionBean.class);
        
        context.checking(new Expectations() {{
            allowing(bean).getComments();
            will(returnValue(COMMENTS));
            
            allowing(bean).setActionsPerformed(protocol.getProtocolActions());
            
            allowing(bean).getLastPerformedAction();
            will(returnValue(protocol.getLastProtocolAction()));
        }});
        
        return bean;
    }
    
    private ProtocolGenericActionBean getMockGenericActionBean(final String comments){
        final ProtocolGenericActionBean bean = context.mock(ProtocolGenericActionBean.class);
        
        context.checking(new Expectations() {{
            allowing(bean).getComments();
            will(returnValue(comments));
            
            allowing(bean).getActionDate();
            will(returnValue(CLOSE_ACTION_DATE));
        }});
        
        return bean;
    }
    
    private ProtocolRequestBean getMockProtocolRequestBean(final String protocolActionTypeCode, final String submissionTypeCode, final String beanName) {
        final ProtocolRequestBean bean = context.mock(ProtocolRequestBean.class);
        
        context.checking(new Expectations() {{
            allowing(bean).getProtocolActionTypeCode();
            will(returnValue(protocolActionTypeCode));
            
            allowing(bean).getSubmissionTypeCode();
            will(returnValue(submissionTypeCode));
            
            allowing(bean).getCommitteeId();
            will(returnValue(Constants.EMPTY_STRING));
            
            allowing(bean).getReason();
            will(returnValue(Constants.EMPTY_STRING));
            
            allowing(bean).getBeanName();
            will(returnValue(beanName));
            
            allowing(bean).getActionAttachments();
            will(returnValue(new ArrayList<ProtocolActionAttachment>()));

            allowing(bean).getAnswerHeaders();
            will(returnValue(new ArrayList<AnswerHeader>()));
        }});
        
        return bean;
    }
    
}