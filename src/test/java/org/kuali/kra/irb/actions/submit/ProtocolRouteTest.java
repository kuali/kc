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
package org.kuali.kra.irb.actions.submit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolFinderDao;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewService;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendmentBean;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitAction;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.bo.DocumentHeader;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;

/**
 *
 */
@PerSuiteUnitTestData(@UnitTestData(sqlFiles = {
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_status.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_ORG_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_PERSON_ROLES.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_type.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_ATTACHMENT_GROUP.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_ATTACHMENT_STATUS.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_ATTACHMENT_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_ATTACHMENT_TYPE_GROUP.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_SUBMISSION_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_review_type.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_REVIEWER_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_committee_type.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_ACTION_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_SUBMISSION_TYPE_QUALIFIER.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_EXEMPT_STUDIES_CHECKLIST.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_EXPEDITED_REVIEW_CHECKLIST.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_SUBMISSION_STATUS.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_schedule_status.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_MODULES.sql", delimiter = ";")
}))
public class ProtocolRouteTest extends KraTestBase {

    private static final String VALID_SUBMISSION_TYPE = "100";
    private static final String VALID_PROTOCOL_REVIEW_TYPE = "1";
    private static final String SUMMARY = "my test summary";
    
    private ProtocolSubmitActionService protocolSubmitActionService;
    private BusinessObjectService businessObjectService;   
    private DocumentService documentService;
    private ProtocolAmendRenewService protocolAmendRenewService;
    private ProtocolFinderDao protocolFinder;
    
    @SuppressWarnings("unchecked")
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        GlobalVariables.setErrorMap(new ErrorMap());
        GlobalVariables.setAuditErrorMap(new HashMap());
        protocolSubmitActionService = KraServiceLocator.getService(ProtocolSubmitActionService.class);
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        documentService = KraServiceLocator.getService(DocumentService.class);
        protocolAmendRenewService = KraServiceLocator.getService(ProtocolAmendRenewService.class);
        protocolFinder = KraServiceLocator.getService(ProtocolFinderDao.class);
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        GlobalVariables.setErrorMap(null);
        GlobalVariables.setAuditErrorMap(null);
        super.tearDown();
    }
    
    /**
     * Test the approval of a protocol.  The protocol status and its corresponding action
     * should be set to approved.
     */
    @Test
    public void runApprovedTest() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        ProtocolSubmitAction submitAction = createSubmitAction();
    
        protocolSubmitActionService.submitToIrbForReview(protocolDocument.getProtocol(), submitAction);
        
        documentService.routeDocument(protocolDocument, null, null);
        documentService.blanketApproveDocument(protocolDocument, null, null);
        
        assertTrue(getWorkflowDocument(protocolDocument).stateIsFinal());
        assertEquals(protocolDocument.getProtocol().getProtocolStatusCode(), ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT);
        
        assertTrue(protocolDocument.getProtocol().isActive());
        
        verifyProtocolAction(protocolDocument.getProtocol().getProtocolId(), ProtocolActionType.APPROVED);
    }
    
    /**
     * Test the disapproval of a protocol.  The protocol status and its corresponding action
     * should be set to disapproved.
     */
    @Test
    public void runDisapprovedTest() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument("0906000002");
        ProtocolSubmitAction submitAction = createSubmitAction();
    
        protocolSubmitActionService.submitToIrbForReview(protocolDocument.getProtocol(), submitAction);
        
        documentService.routeDocument(protocolDocument, null, null);
        documentService.disapproveDocument(protocolDocument, null);
        
        assertTrue(getWorkflowDocument(protocolDocument).stateIsDisapproved());
        assertEquals(protocolDocument.getProtocol().getProtocolStatusCode(), ProtocolStatus.DISAPPROVED);
        
        assertTrue(protocolDocument.getProtocol().isActive());
        
        verifyProtocolAction(protocolDocument.getProtocol().getProtocolId(), ProtocolActionType.DISAPPROVED);
    }
    
    /**
     * Test the approval of an amendment.  Verify that the sequence number has been incremented
     * for the protocol version and that it's status has been set to approved.
     * @throws Exception
     */
    @Test
    public void runAmendmentTest() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument("0906000003");
        ProtocolSubmitAction submitAction = createSubmitAction();
    
        protocolSubmitActionService.submitToIrbForReview(protocolDocument.getProtocol(), submitAction);
        
        documentService.routeDocument(protocolDocument, null, null);
        documentService.blanketApproveDocument(protocolDocument, null, null);
        
        ProtocolAmendmentBean amendmentBean = new ProtocolAmendmentBean();
        amendmentBean.setAddModifyAttachments(true);
        amendmentBean.setProtocolOrganizations(true);
        amendmentBean.setSummary(SUMMARY);
        
        String docNbr = protocolAmendRenewService.createAmendment(protocolDocument, amendmentBean);
        
        ProtocolDocument amendmentDocument = (ProtocolDocument) getDocumentService().getByDocumentHeaderId(docNbr);
        protocolSubmitActionService.submitToIrbForReview(amendmentDocument.getProtocol(), submitAction);
        
        documentService.routeDocument(amendmentDocument, null, null);
        documentService.blanketApproveDocument(amendmentDocument, null, null);
        
        assertTrue(getWorkflowDocument(amendmentDocument).stateIsFinal());
        
        Protocol newProtocol = protocolFinder.findCurrentProtocolByNumber(protocolDocument.getProtocol().getProtocolNumber());
        assertTrue(newProtocol.getSequenceNumber() == protocolDocument.getProtocol().getSequenceNumber() + 1);
        
        assertFalse(protocolDocument.getProtocol().isActive());
        assertFalse(amendmentDocument.getProtocol().isActive());
        assertTrue(newProtocol.isActive());
        
        // TODO: This test can be re-added once we can route the new protocol through workflow.
        //assertEquals(getWorkflowDocument(newProtocol.getProtocolDocument).isFinal());
        
        verifyProtocolAction(newProtocol.getProtocolId(), ProtocolActionType.APPROVED);
    }

    /**
     * Create protocol submission action.
     */
    private ProtocolSubmitAction createSubmitAction() {
        ProtocolSubmitAction submitAction = new ProtocolSubmitAction(null);
        submitAction.setSubmissionTypeCode(VALID_SUBMISSION_TYPE);
        submitAction.setProtocolReviewTypeCode(VALID_PROTOCOL_REVIEW_TYPE);
        return submitAction;
    }

    /**
     * Verfy that the protocol has the given protocol action.
     */
    private void verifyProtocolAction(Long protocolId, String actionTypeCode) {
        List<ProtocolAction> actions = getProtocolActions(protocolId);
        for (ProtocolAction action : actions) {
            if (StringUtils.equals(actionTypeCode, action.getProtocolActionTypeCode())) {
                return;
            }
        }
        assertTrue(actionTypeCode + " not found", false);
    }

    /**
     * Get the ProtocolActions for a protocol
     */
    @SuppressWarnings("unchecked")
    private List<ProtocolAction> getProtocolActions(Long protocolId) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("protocolId", protocolId);
        return (List<ProtocolAction>) businessObjectService.findMatching(ProtocolAction.class, fieldValues);
    }
    
    /**
     * Get the document's workflow document.
     * @param doc
     * @return
     */
    private KualiWorkflowDocument getWorkflowDocument(Document doc) {
        KualiWorkflowDocument workflowDocument = null;
        if (doc != null) {
            DocumentHeader header = doc.getDocumentHeader();
            if (header != null) {
                try {
                    workflowDocument = header.getWorkflowDocument();
                } 
                catch (RuntimeException ex) {
                    assertTrue("workflow document not found", false);
                }
            }
        }
        return workflowDocument;
    }
}
