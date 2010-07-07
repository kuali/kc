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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
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
import org.kuali.kra.irb.actions.request.ProtocolRequestBean;
import org.kuali.kra.irb.actions.request.ProtocolRequestService;
import org.kuali.kra.irb.actions.request.ProtocolRequestServiceImpl;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitAction;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitActionService;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;

/**
 * Test the ProtocolWithdrawService implementation.
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
        @UnitTestFile(filename = "classpath:sql/dml/load_SUBMISSION_STATUS.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_CONTINGENCY.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_EXEMPT_STUDIES_CHECKLIST.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_SUBMISSION_TYPE_QUALIFIER.sql", delimiter = ";") }))
public class UndoLastActionServiceTest extends KraTestBase {

    private static final String COMMENTS = "something silly";
    private static final String VALID_SUBMISSION_TYPE = "100";
    private static final String VALID_REVIEW_TYPE = "1";

    private static final String VALID_CONTINGENCY_CODE_1 = "22";
    private static final String VALID_EXEMPT_STUDIES_ITEM_CODE = "1";

    private UndoLastActionService undoLastActionService;
    private ProtocolSubmitActionService protocolSubmitActionService;
    private ProtocolAssignToAgendaService protocolAssignToAgendaService;
    private ProtocolApproveService protocolApproveService;
    private ProtocolRequestService protocolRequestService;
    private ProtocolGenericActionService protocolGenericActionService;
    private BusinessObjectService businessObjectService;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        undoLastActionService = KraServiceLocator.getService(UndoLastActionService.class);
        protocolSubmitActionService = KraServiceLocator.getService(ProtocolSubmitActionService.class);
        protocolAssignToAgendaService = KraServiceLocator.getService(ProtocolAssignToAgendaService.class);
        protocolApproveService = KraServiceLocator.getService(ProtocolApproveService.class);
        protocolRequestService = KraServiceLocator.getService(ProtocolRequestService.class);
        protocolGenericActionService = KraServiceLocator.getService(ProtocolGenericActionService.class);
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        super.tearDown();
    }

    private ProtocolDocument initProtocolDocument() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        Long protocolId = protocolDocument.getProtocol().getProtocolId();
        ProtocolSubmitAction submitAction = createSubmitAction("668", "1", VALID_REVIEW_TYPE);
        submitAction.setSubmissionQualifierTypeCode("2");
        protocolSubmitActionService.submitToIrbForReview(protocolDocument.getProtocol(), submitAction);

        ProtocolAssignToAgendaBean assignToAgendaBean = new ProtocolAssignToAgendaBean(null);
        assignToAgendaBean.setComments("assigning to agenda");
        assignToAgendaBean.setCommitteName("test committee");
        assignToAgendaBean.setProtocolAssigned(true);
        protocolAssignToAgendaService.assignToAgenda(protocolDocument.getProtocol(), assignToAgendaBean);
        boolean result = protocolAssignToAgendaService.isAssignedToAgenda(protocolDocument.getProtocol());
        assertTrue(result);

        ProtocolApproveBean approvalActionBean = new ProtocolApproveBean();
        approvalActionBean.setActionDate(new Date(System.currentTimeMillis()));
        approvalActionBean.setApprovalDate(new Date(System.currentTimeMillis()));
        approvalActionBean.setComments("approving");
        approvalActionBean.setExpirationDate(new Date(System.currentTimeMillis()));
        approvalActionBean.setProtocolId(protocolId);
        protocolApproveService.approve(protocolDocument.getProtocol(), approvalActionBean);

        assertEquals(ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT, protocolDocument.getProtocol().getProtocolStatusCode());
        ProtocolSubmission submission = protocolDocument.getProtocol().getProtocolSubmissions().get(0);
        assertEquals(ProtocolSubmissionStatus.APPROVED, submission.getSubmissionStatusCode());
        ProtocolAction protocolAction = findProtocolAction(protocolDocument.getProtocol().getProtocolId());
        assertNotNull(protocolAction);
        assertEquals("approving", protocolAction.getComments());
        assertEquals("204", protocolAction.getProtocolActionTypeCode());

        return protocolDocument;
    }

    @Test
    public void testUndoApproveAction() throws Exception {
        ProtocolDocument protocolDocument = initProtocolDocument();

        UndoLastActionBean undoLastActionBean = new UndoLastActionBean();
        undoLastActionBean.setComments(COMMENTS);

        undoLastActionService.undoLastAction(protocolDocument, undoLastActionBean);

        assertEquals(ProtocolStatus.SUBMITTED_TO_IRB, protocolDocument.getProtocol().getProtocolStatusCode());
        ProtocolSubmission submission = protocolDocument.getProtocol().getProtocolSubmissions().get(0);
        assertEquals(ProtocolSubmissionStatus.IN_AGENDA, submission.getSubmissionStatusCode());
        ProtocolAction protocolAction = findProtocolAction(protocolDocument.getProtocol().getProtocolId());
        assertNotNull(protocolAction);
        assertEquals("assigning to agenda", protocolAction.getComments());
        assertEquals("200", protocolAction.getProtocolActionTypeCode());
    }

    @Test
    public void testUndoRequestToCloseAction() throws Exception {
        ProtocolDocument protocolDocument = initProtocolDocument();

        UndoLastActionBean undoLastActionBean = new UndoLastActionBean();
        undoLastActionBean.setComments(COMMENTS);
        ProtocolRequestBean requestToCloseBean = new ProtocolRequestBean(ProtocolActionType.REQUEST_TO_CLOSE,
            ProtocolSubmissionType.REQUEST_TO_CLOSE);
        protocolRequestService.submitRequest(protocolDocument.getProtocol(), requestToCloseBean);

        assertEquals(ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT, protocolDocument.getProtocol().getProtocolStatusCode());
        ProtocolSubmission submission = findProtocolSubmission(protocolDocument);
        assertEquals(ProtocolSubmissionStatus.PENDING, submission.getSubmissionStatusCode());
        ProtocolAction protocolAction = findProtocolAction(protocolDocument.getProtocol().getProtocolId());
        assertNotNull(protocolAction);
        assertEquals("105", protocolAction.getProtocolActionTypeCode());

        undoLastActionService.undoLastAction(protocolDocument, undoLastActionBean);

        assertEquals(ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT, protocolDocument.getProtocol().getProtocolStatusCode());
        submission = findProtocolSubmission(protocolDocument);
        assertEquals(ProtocolSubmissionStatus.APPROVED, submission.getSubmissionStatusCode());
        protocolAction = findProtocolAction(protocolDocument.getProtocol().getProtocolId());
        assertNotNull(protocolAction);
        assertEquals("approving", protocolAction.getComments());
        assertEquals("204", protocolAction.getProtocolActionTypeCode());
    }

    @Test
    public void testUndoCloseAction() throws Exception {
        ProtocolDocument protocolDocument = initProtocolDocument();

        UndoLastActionBean undoLastActionBean = new UndoLastActionBean();
        undoLastActionBean.setComments(COMMENTS);
        ProtocolRequestBean requestToCloseBean = new ProtocolRequestBean(ProtocolActionType.REQUEST_TO_CLOSE,
            ProtocolSubmissionType.REQUEST_TO_CLOSE);
        protocolRequestService.submitRequest(protocolDocument.getProtocol(), requestToCloseBean);

        assertEquals(ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT, protocolDocument.getProtocol().getProtocolStatusCode());
        ProtocolSubmission submission = findProtocolSubmission(protocolDocument);
        assertEquals(ProtocolSubmissionStatus.PENDING, submission.getSubmissionStatusCode());
        assertEquals(ProtocolSubmissionType.REQUEST_TO_CLOSE, submission.getSubmissionTypeCode());
        ProtocolAction protocolAction = findProtocolAction(protocolDocument.getProtocol().getProtocolId());
        assertNotNull(protocolAction);
        assertEquals("105", protocolAction.getProtocolActionTypeCode());

        ProtocolGenericActionBean closeActionBean = new ProtocolGenericActionBean();
        closeActionBean.setActionDate(new Date(System.currentTimeMillis()));
        closeActionBean.setComments("closing administratively");
        protocolGenericActionService.close(protocolDocument.getProtocol(), closeActionBean);

        assertTrue(protocolDocument.getProtocol().getProtocolStatusCode().equals(ProtocolStatus.CLOSED_ADMINISTRATIVELY)
                || protocolDocument.getProtocol().getProtocolStatusCode().equals("301"));
        submission = protocolDocument.getProtocol().getProtocolSubmission();
        assertEquals(ProtocolSubmissionStatus.CLOSED, submission.getSubmissionStatusCode());
        protocolAction = findProtocolAction(protocolDocument.getProtocol().getProtocolId());
        assertNotNull(protocolAction);
        assertEquals("300", protocolAction.getProtocolActionTypeCode());

        undoLastActionService.undoLastAction(protocolDocument, undoLastActionBean);

        assertEquals(ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT, protocolDocument.getProtocol().getProtocolStatusCode());
        submission = findProtocolSubmission(protocolDocument);
        assertEquals(ProtocolSubmissionStatus.PENDING, submission.getSubmissionStatusCode());
        protocolAction = findProtocolAction(protocolDocument.getProtocol().getProtocolId());
        assertNotNull(protocolAction);
        assertEquals("105", protocolAction.getProtocolActionTypeCode());
    }

    @Test
    public void testUndoRequestForSuspensionAction() throws Exception {
        ProtocolDocument protocolDocument = initProtocolDocument();

        UndoLastActionBean undoLastActionBean = new UndoLastActionBean();
        undoLastActionBean.setComments(COMMENTS);
        ProtocolRequestBean requestForSuspensionBean = new ProtocolRequestBean(ProtocolActionType.REQUEST_FOR_SUSPENSION,
            ProtocolSubmissionType.REQUEST_FOR_SUSPENSION);
        protocolRequestService.submitRequest(protocolDocument.getProtocol(), requestForSuspensionBean);

        assertEquals(ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT, protocolDocument.getProtocol().getProtocolStatusCode());
        ProtocolSubmission submission = findProtocolSubmission(protocolDocument);
        assertEquals(ProtocolSubmissionStatus.PENDING, submission.getSubmissionStatusCode());
        ProtocolAction protocolAction = findProtocolAction(protocolDocument.getProtocol().getProtocolId());
        assertNotNull(protocolAction);
        assertEquals("106", protocolAction.getProtocolActionTypeCode());

        undoLastActionService.undoLastAction(protocolDocument, undoLastActionBean);

        assertEquals(ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT, protocolDocument.getProtocol().getProtocolStatusCode());
        submission = findProtocolSubmission(protocolDocument);
        assertEquals(ProtocolSubmissionStatus.APPROVED, submission.getSubmissionStatusCode());
        protocolAction = findProtocolAction(protocolDocument.getProtocol().getProtocolId());
        assertNotNull(protocolAction);
        assertEquals("approving", protocolAction.getComments());
        assertEquals("204", protocolAction.getProtocolActionTypeCode());
    }
    
    @Test
    public void testUndoEnrollmentActions() throws Exception {
        ProtocolDocument protocolDocument = initProtocolDocument();

        UndoLastActionBean undoLastActionBean = new UndoLastActionBean();
        undoLastActionBean.setComments(COMMENTS);
        ProtocolRequestBean requestToCloseBean = new ProtocolRequestBean(ProtocolActionType.REQUEST_TO_CLOSE_ENROLLMENT,
            ProtocolSubmissionType.REQUEST_TO_CLOSE_ENROLLMENT);
        protocolRequestService.submitRequest(protocolDocument.getProtocol(), requestToCloseBean);

        assertEquals(ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT, protocolDocument.getProtocol().getProtocolStatusCode());
        ProtocolSubmission submission = findProtocolSubmission(protocolDocument);
        assertEquals(ProtocolSubmissionStatus.PENDING, submission.getSubmissionStatusCode());
        assertEquals(ProtocolSubmissionType.REQUEST_TO_CLOSE_ENROLLMENT, submission.getSubmissionTypeCode());
        ProtocolAction protocolAction = findProtocolAction(protocolDocument.getProtocol().getProtocolId());
        assertNotNull(protocolAction);
        assertEquals(ProtocolActionType.REQUEST_TO_CLOSE_ENROLLMENT, protocolAction.getProtocolActionTypeCode());

        ProtocolGenericActionBean closeActionBean = new ProtocolGenericActionBean();
        closeActionBean.setActionDate(new Date(System.currentTimeMillis()));
        closeActionBean.setComments("closing enrollment");
        protocolGenericActionService.closeEnrollment(protocolDocument.getProtocol(), closeActionBean);

        assertTrue(protocolDocument.getProtocol().getProtocolStatusCode().equals(ProtocolStatus.ACTIVE_CLOSED_TO_ENROLLMENT));
        submission = protocolDocument.getProtocol().getProtocolSubmission();
        assertEquals(ProtocolSubmissionStatus.CLOSED_FOR_ENROLLMENT, submission.getSubmissionStatusCode());
        protocolAction = findProtocolAction(protocolDocument.getProtocol().getProtocolId());
        assertNotNull(protocolAction);
        assertEquals(ProtocolActionType.CLOSED_FOR_ENROLLMENT, protocolAction.getProtocolActionTypeCode());

        undoLastActionService.undoLastAction(protocolDocument, undoLastActionBean);

        assertEquals(ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT, protocolDocument.getProtocol().getProtocolStatusCode());
        submission = findProtocolSubmission(protocolDocument);
        assertEquals(ProtocolSubmissionStatus.PENDING, submission.getSubmissionStatusCode());
        assertEquals(ProtocolSubmissionType.REQUEST_TO_CLOSE_ENROLLMENT, submission.getSubmissionTypeCode());
        protocolAction = findProtocolAction(protocolDocument.getProtocol().getProtocolId());
        assertNotNull(protocolAction);
        assertEquals(ProtocolActionType.REQUEST_TO_CLOSE_ENROLLMENT, protocolAction.getProtocolActionTypeCode());
    }


    @SuppressWarnings("unchecked")
    private ProtocolAction findProtocolAction(Long protocolId) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("protocolId", protocolId);
        List<ProtocolAction> actions = (List<ProtocolAction>) businessObjectService.findMatching(ProtocolAction.class, fieldValues);
        assertTrue(actions.size() > 0);
        ProtocolAction action = actions.get(actions.size() - 1);
        return action;
    }

    @SuppressWarnings("unchecked")
    private ProtocolSubmission findProtocolSubmission(ProtocolDocument protocolDocument) {
        int lastIndex = protocolDocument.getProtocol().getProtocolSubmissions().size() - 1;
        return protocolDocument.getProtocol().getProtocolSubmissions().get(lastIndex);
    }

    /*
     * Create protocol submission action.
     */
    private ProtocolSubmitAction createSubmitAction(String committeeId, String scheduleId, String protocolReviewTypeCode) {
        ProtocolSubmitAction submitAction = new ProtocolSubmitAction(null);
        submitAction.setSubmissionTypeCode(VALID_SUBMISSION_TYPE);
        submitAction.setProtocolReviewTypeCode(protocolReviewTypeCode);
        submitAction.setCommitteeId(committeeId);
        submitAction.setScheduleId(scheduleId);
        return submitAction;
    }
}
