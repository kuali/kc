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
package org.kuali.kra.irb.actions.submit;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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
import org.kuali.kra.irb.ProtocolFinderDao;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewService;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendmentBean;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.DocumentService;

public class ProtocolRouteTest extends KcUnitTestBase {

    private static final String SUMMARY = "my test summary";
    
    private ProtocolSubmitActionService protocolSubmitActionService; 
    private DocumentService documentService;
    private ProtocolAmendRenewService protocolAmendRenewService;
    private ProtocolFinderDao protocolFinder;
    
    private Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};
    
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        protocolSubmitActionService = KraServiceLocator.getService(ProtocolSubmitActionService.class);
        documentService = KraServiceLocator.getService(DocumentService.class);
        protocolAmendRenewService = KraServiceLocator.getService(ProtocolAmendRenewService.class);
        protocolFinder = KraServiceLocator.getService(ProtocolFinderDao.class);
    }

    @Override
    @After
    public void tearDown() throws Exception {
        protocolSubmitActionService = null;
        documentService = null;
        protocolAmendRenewService = null;
        protocolFinder = null;
        
        super.tearDown();
    }
    
    /**
     * Test the approval of a protocol.  The protocol status and its corresponding action
     * should be set to approved.
     */
    @Test
    public void runApprovedTest() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
    
        protocolSubmitActionService.submitToIrbForReview(protocolDocument.getProtocol(), getMockSubmitAction());

        documentService.routeDocument(protocolDocument, null, null);
        documentService.blanketApproveDocument(protocolDocument, null, null);
        
        assertTrue(getWorkflowDocument(protocolDocument).isFinal());
        
        //the status update is not happening within doRouteStatusChange anymore
        //assertEquals(protocolDocument.getProtocol().getProtocolStatusCode(), ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT);
        assertTrue(protocolDocument.getProtocol().isActive());
        //verifyProtocolAction(protocolDocument.getProtocol().getProtocolId(), ProtocolActionType.APPROVED);
    }
    
    /**
     * Test the disapproval of a protocol.  The protocol status and its corresponding action
     * should be set to disapproved.
     */
    @Test
    public void runDisapprovedTest() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument("0906000002");
    
        protocolSubmitActionService.submitToIrbForReview(protocolDocument.getProtocol(), getMockSubmitAction());
        
        documentService.routeDocument(protocolDocument, null, null);
        documentService.disapproveDocument(protocolDocument, null);
        
        assertTrue(getWorkflowDocument(protocolDocument).isDisapproved());
        //assertEquals(protocolDocument.getProtocol().getProtocolStatusCode(), ProtocolStatus.DISAPPROVED);
        
        assertTrue(protocolDocument.getProtocol().isActive());
        
        //verifyProtocolAction(protocolDocument.getProtocol().getProtocolId(), ProtocolActionType.DISAPPROVED);
    }
    
    /**
     * Test the approval of an amendment.  Verify that the sequence number has been incremented
     * for the protocol version and that it's status has been set to approved.
     * @throws Exception
     */
    @Test
    public void runAmendmentTest() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument("0906000003");
        ProtocolSubmitAction submitAction = getMockSubmitAction();
    
        protocolSubmitActionService.submitToIrbForReview(protocolDocument.getProtocol(), submitAction);
        
        documentService.routeDocument(protocolDocument, null, null);
        documentService.blanketApproveDocument(protocolDocument, null, null);
        
        String docNbr = protocolAmendRenewService.createAmendment(protocolDocument, getMockProtocolAmendmentBean());
        
        ProtocolDocument amendmentDocument = (ProtocolDocument) getDocumentService().getByDocumentHeaderId(docNbr);
        protocolSubmitActionService.submitToIrbForReview(amendmentDocument.getProtocol(), submitAction);
        
        documentService.routeDocument(amendmentDocument, null, null);
        documentService.blanketApproveDocument(amendmentDocument, null, null);
        
        assertTrue(getWorkflowDocument(amendmentDocument).isFinal());
        
        Protocol newProtocol = protocolFinder.findCurrentProtocolByNumber(protocolDocument.getProtocol().getProtocolNumber());
        assertTrue(newProtocol.getSequenceNumber() == protocolDocument.getProtocol().getSequenceNumber() + 1);
        
        /*
         * Must read the protocol document again in order to obtain the most recent changes.
         */
        protocolDocument = (ProtocolDocument) documentService.getByDocumentHeaderId(protocolDocument.getDocumentNumber());
       
        assertFalse(protocolDocument.getProtocol().isActive());
        assertFalse(amendmentDocument.getProtocol().isActive());
        assertTrue(newProtocol.isActive());
        
        // TODO: This test can be re-added once we can route the new protocol through workflow.
        //assertEquals(getWorkflowDocument(newProtocol.getProtocolDocument).isFinal());
        
        verifyProtocolAction(newProtocol, ProtocolActionType.APPROVED);
    }

    /**
     * Verfy that the protocol has the given protocol action.
     */
    private void verifyProtocolAction(Protocol protocol, String actionTypeCode) {
        List<ProtocolAction> actions = protocol.getProtocolActions();
        for (ProtocolAction action : actions) {
            if (StringUtils.equals(actionTypeCode, action.getProtocolActionTypeCode())) {
                return;
            }
        }
        assertTrue(actionTypeCode + " not found", false);
    }
    
    /**
     * Get the document's workflow document.
     * @param doc
     * @return
     */
    private WorkflowDocument getWorkflowDocument(Document doc) {
        WorkflowDocument workflowDocument = null;
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
    
    private ProtocolAmendmentBean getMockProtocolAmendmentBean() {
        final ProtocolAmendmentBean bean = context.mock(ProtocolAmendmentBean.class);
        
        context.checking(new Expectations() {{
            allowing(bean).getSummary();
            will(returnValue(SUMMARY));
            
            allowing(bean).getGeneralInfo();
            will(returnValue(false));
            
            allowing(bean).getFundingSource();
            will(returnValue(false));
            
            allowing(bean).getProtocolReferencesAndOtherIdentifiers();
            will(returnValue(false));
            
            allowing(bean).getProtocolOrganizations();
            will(returnValue(true));
            
            allowing(bean).getSubjects();
            will(returnValue(false));
            
            allowing(bean).getAddModifyAttachments();
            will(returnValue(true));
            
            allowing(bean).getAreasOfResearch();
            will(returnValue(false));
            
            allowing(bean).getSpecialReview();
            will(returnValue(false));
            
            allowing(bean).getProtocolPersonnel();
            will(returnValue(false));
            
            allowing(bean).getOthers();
            will(returnValue(false));
            
            allowing(bean).getProtocolPermissions();
            will(returnValue(false));
            allowing(bean).getQuestionnaire();
            will(returnValue(false));
        }});
        
        return bean;
    }
    
}