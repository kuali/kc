/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.service;

import org.jmock.Mock;
import org.jmock.MockObjectTestCase;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kuali.core.bo.DocumentHeader;
import org.kuali.core.workflow.service.KualiWorkflowDocument;
import org.kuali.kra.proposaldevelopment.bo.ProposalState;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.impl.ProposalStateServiceImpl;

/**
 * Unit Tests for the Proposal State Service Implementation.
 */
public class ProposalStateServiceTest extends MockObjectTestCase {
    
    /*
     * Workflow states.
     */
    private static final int INITIATED = 1;
    private static final int SAVED = 2;
    private static final int ENROUTE = 3;
    private static final int APPROVED = 4;
    private static final int DISAPPROVED = 5;
    private static final int CANCELED = 6;
    private static final int EXCEPTION = 7;
    
    private ProposalStateService service;
    
    @BeforeClass
    public void setUp() {
        service = new ProposalStateServiceImpl();
    }

    /**
     * A proposal in the initiated state must always have a proposal state of In Progress.
     * It is impossible for an initiated proposal to be submitted, thus it is not tested.
     */
    @Test
    public void testInitiated() {
        runTest(INITIATED, false, ProposalState.IN_PROGRESS, ProposalState.IN_PROGRESS);  
    }
    
    /**
     * For a proposal in the saved state, it's proposal state depends upon whether
     * it has been submitted or not.  The change in route status does not matter.
     */
    @Test
    public void testSaved() {
        runTest(SAVED, false, ProposalState.IN_PROGRESS, ProposalState.IN_PROGRESS);
        runTest(SAVED, true, ProposalState.APPROVAL_NOT_INITIATED_SUBMITTED, ProposalState.APPROVAL_NOT_INITIATED_SUBMITTED);
    }
    
    /**
     * For a proposal in the enroute state, it's proposal state depends upon whether
     * it has been submitted or not.  The change in route status does not matter.
     */
    @Test
    public void testEnroute() {
        runTest(ENROUTE, false, ProposalState.APPROVAL_PENDING, ProposalState.APPROVAL_PENDING);
        runTest(ENROUTE, true, ProposalState.APPROVAL_PENDING_SUBMITTED, ProposalState.APPROVAL_PENDING_SUBMITTED);
    }
    
    /**
     * For an approved proposal, it's proposal state depends upon whether it has been
     * submitted or not as well as the change in the route status.  Proposals that have
     * not been submitted are in the Approval Granted state regardless of any route status
     * changes.  When the proposal is also submitted, the proposal state depends upon whether
     * the route status change to approved or the sponsor submitted occurred first.
     */
    @Test
    public void testApproved() {
        runTest(APPROVED, false, ProposalState.APPROVAL_GRANTED, ProposalState.APPROVAL_GRANTED);
        runTest(APPROVED, true, ProposalState.APPROVED_AND_SUBMITTED, ProposalState.APPROVED_POST_SUBMISSION);
    }
   
    /**
     * For a disapproved proposal that has not been submitted, it's proposal state is always Disapproved.
     * For a disapproved proposal that has been submitted, the proposal state depends upon which was
     * done first: the change to disapproval or the submission to the sponsor.  Actually, a disapproved
     * proposal can never be submitted, but I'll ignore that for this test.  If it could be submitted
     * afterwards, it's proposal state would remain at Disapproved.  If the proposal is disapproved after
     * the submission, then the state is Disapproved Post Submission.
     */
    @Test
    public void testDisapproved() {
        runTest(DISAPPROVED, false, ProposalState.DISAPPROVED, ProposalState.DISAPPROVED);
        runTest(DISAPPROVED, true, ProposalState.DISAPPROVED, ProposalState.DISAPPROVED_POST_SUBMISSION);
    }
    
    /**
     * If the proposal is canceled, it remains canceled under all conditions.
     */
    @Test
    public void testCanceled() {
        runTest(CANCELED, false, ProposalState.CANCELED, ProposalState.CANCELED);
        runTest(CANCELED, true, ProposalState.CANCELED, ProposalState.CANCELED);
    }
    
    /**
     * If the proposal encounters an exception, it remains in the document error state under all conditions.
     */
    @Test
    public void testException() {
        runTest(EXCEPTION, false, ProposalState.DOCUMENT_ERROR, ProposalState.DOCUMENT_ERROR);
        runTest(EXCEPTION, true, ProposalState.DOCUMENT_ERROR, ProposalState.DOCUMENT_ERROR);
    }
    
    /**
     * Verify that a proposal with the given workflow state and submit to sponsor state,
     * will produce the two given expected proposal state values.  The first expected 
     * state value is when the route status has not changed.  The second expected state
     * value is when the route status has changed.
     * @param workflowState the current workflow state of the proposal
     * @param isSubmitted has the proposal been submitted to the sponsor?
     * @param expectedState1 the first expected proposal state value
     * @param expectedState2 the second expected proposal state value
     */
    private void runTest(int workflowState, boolean isSubmitted, String expectedState1, String expectedState2) {
        ProposalDevelopmentDocument doc = createProposalDevelopmentDocument(workflowState, isSubmitted);
        
        String state = service.getProposalStateTypeCode(doc, false);
        assertEquals("Proposal State", expectedState1, state);
        
        state = service.getProposalStateTypeCode(doc, true);
        assertEquals("Proposal State", expectedState2, state);
    }
    
    /**
     * Create a Proposal Development Document with the given workflow state
     * and submission to sponsor state.
     * @param workflowState the workflow state
     * @param submitted is the proposal submitted to the sponsor?
     * @return the Proposal Development Document
     */
    private ProposalDevelopmentDocument createProposalDevelopmentDocument(int workflowState, boolean submitted) {
        ProposalDevelopmentDocument doc = new ProposalDevelopmentDocument();
        doc.setSubmitFlag(submitted);
        
        DocumentHeader docHdr = new DocumentHeader();
        
        Mock mock = mock(KualiWorkflowDocument.class);
        setMockExpectations(mock, workflowState);
         
        docHdr.setWorkflowDocument((KualiWorkflowDocument) mock.proxy());
        doc.setDocumentHeader(docHdr);
        
        return doc;
    }
    
    /**
     * Set the expectations for the KualiWorkflowDocument mock.
     * @param mock the KualiWorkflowDocument mock
     * @param workflowState the workflow state
     */
    private void setMockExpectations(Mock mock, int workflowState) {
        switch (workflowState) {
            case INITIATED:
                expects(mock, "stateIsInitiated", true);
                break;
                
            case SAVED:
                expects(mock, "stateIsInitiated", false);
                expects(mock, "stateIsSaved", true);
                break;
                
            case ENROUTE:
                expects(mock, "stateIsInitiated", false);
                expects(mock, "stateIsSaved", false);
                expects(mock, "stateIsEnroute", true);
                break;
                
            case APPROVED:
                expects(mock, "stateIsInitiated", false);
                expects(mock, "stateIsSaved", false);
                expects(mock, "stateIsEnroute", false);
                expects(mock, "stateIsApproved", true);
                break;
                
            case DISAPPROVED:
                expects(mock, "stateIsInitiated", false);
                expects(mock, "stateIsSaved", false);
                expects(mock, "stateIsEnroute", false);
                expects(mock, "stateIsApproved", false);
                expects(mock, "stateIsDisapproved", true);
                break;
                
            case CANCELED:
                expects(mock, "stateIsInitiated", false);
                expects(mock, "stateIsSaved", false);
                expects(mock, "stateIsEnroute", false);
                expects(mock, "stateIsApproved", false);
                expects(mock, "stateIsDisapproved", false);
                expects(mock, "stateIsCanceled", true);
                break;
                
            case EXCEPTION:
                expects(mock, "stateIsInitiated", false);
                expects(mock, "stateIsSaved", false);
                expects(mock, "stateIsEnroute", false);
                expects(mock, "stateIsApproved", false);
                expects(mock, "stateIsDisapproved", false);
                expects(mock, "stateIsCanceled", false);
                break;
        }
    }
    
    /**
     * Set the expectation for a mock.
     * @param mock the mock
     * @param methodName the method to be invoked
     * @param retValue the expected return value
     */
    private void expects(Mock mock, String methodName, boolean retValue) {
        mock.expects(this.atLeastOnce()).method(methodName).will(returnValue(retValue));
    }
}
