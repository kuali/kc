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
package org.kuali.kra.proposaldevelopment.service;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.ProposalState;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.krad.bo.DocumentHeader;

/**
 * Unit Tests for the Proposal State Service Implementation.
 */
public class ProposalStateServiceTest extends KcUnitTestBase {
    
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
    private Mockery mockery;
    private WorkflowDocument mock;
    
    @Before
    public void initTest() {
        mockery = new JUnit4Mockery();
        service = KraServiceLocator.getService(ProposalStateService.class);
        mock = mockery.mock(WorkflowDocument.class);
    }

    /**
     * A proposal in the initiated state must always have a proposal state of In Progress.
     * It is impossible for an initiated proposal to be submitted, thus it is not tested.
     */
    @Test
    public void testInitiated() {
        runTest(INITIATED, false, false, ProposalState.IN_PROGRESS, ProposalState.IN_PROGRESS);  
    }
    
    /**
     * For a proposal in the saved state, it's proposal state depends upon whether
     * it has been submitted or not.  The change in route status does not matter.
     */
    @Test
    public void testSaved() {
        runTest(SAVED, false, false, ProposalState.IN_PROGRESS, ProposalState.IN_PROGRESS);
        runTest(SAVED, true, false, ProposalState.APPROVAL_NOT_INITIATED_SUBMITTED, ProposalState.APPROVAL_NOT_INITIATED_SUBMITTED);
    }
    
    /**
     * For a proposal in the enroute state, it's proposal state depends upon whether
     * it has been submitted or not.  The change in route status does not matter.
     */
    @Test
    public void testEnroute() {
        runTest(ENROUTE, false, false, ProposalState.APPROVAL_PENDING, ProposalState.APPROVAL_PENDING );
        runTest(ENROUTE, true, false, ProposalState.APPROVAL_PENDING_SUBMITTED, ProposalState.APPROVAL_PENDING_SUBMITTED );
        runTest(ENROUTE, true, true, ProposalState.REVISIONS_REQUESTED, ProposalState.REVISIONS_REQUESTED );
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
        runTest(APPROVED, false, false, ProposalState.APPROVAL_GRANTED, ProposalState.APPROVAL_GRANTED);
        runTest(APPROVED, true, false, ProposalState.APPROVED_AND_SUBMITTED, ProposalState.APPROVED_POST_SUBMISSION);
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
        runTest(DISAPPROVED, false, false, ProposalState.DISAPPROVED, ProposalState.DISAPPROVED);
        runTest(DISAPPROVED, true, false, ProposalState.DISAPPROVED, ProposalState.DISAPPROVED_POST_SUBMISSION);
    }
    
    /**
     * If the proposal is canceled, it remains canceled under all conditions.
     */
    @Test
    public void testCanceled() {
        runTest(CANCELED, false, false, ProposalState.CANCELED, ProposalState.CANCELED);
        runTest(CANCELED, true, false, ProposalState.CANCELED, ProposalState.CANCELED);
    }
    
    /**
     * If the proposal encounters an exception, it remains in the document error state under all conditions.
     */
    @Test
    public void testException() {
        runTest(EXCEPTION, false, false, ProposalState.DOCUMENT_ERROR, ProposalState.DOCUMENT_ERROR);
        runTest(EXCEPTION, true, false, ProposalState.DOCUMENT_ERROR, ProposalState.DOCUMENT_ERROR );
    }
    
    
    /**
     * Verify that a proposal with the given workflow state and submit to sponsor state,
     * will produce the two given expected proposal state values.  The first expected 
     * state value is when the route status has not changed.  The second expected state
     * value is when the route status has changed.
     * @param workflowState the current workflow state of the proposal
     * @param isSubmitted has the proposal been submitted to the sponsor?
     * @param isRejected has the proposal been rejected? ( Revisions Requested ).
     * @param expectedState1 the first expected proposal state value
     * @param expectedState2 the second expected proposal state value
     *  
     */
    private void runTest(int workflowState, boolean isSubmitted, boolean isRejected, String expectedState1, String expectedState2 ) {
        ProposalDevelopmentDocument doc = createProposalDevelopmentDocument(workflowState, isSubmitted);
        
        String state = service.getProposalStateTypeCode(doc, false, isRejected );
        assertEquals("Proposal State", expectedState1, state);
        
        state = service.getProposalStateTypeCode(doc, true, isRejected);
        assertEquals("Proposal State", expectedState2, state );
        
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
        doc.getDevelopmentProposal().setSubmitFlag(submitted);
        
        DocumentHeader docHdr = new DocumentHeader();

        setMockExpectations(mock, workflowState);
         
        docHdr.setWorkflowDocument((WorkflowDocument) mock);
        doc.setDocumentHeader(docHdr);
        
        return doc;
    }
    
    /**
     * Set the expectations for the WorkflowDocument mock.
     * @param mock the WorkflowDocument mock
     * @param workflowState the workflow state
     */
    private void setMockExpectations(final WorkflowDocument mock, final int workflowState) {
        
        mockery.checking(new Expectations() {{
            
            switch (workflowState) {
                case INITIATED:
                    atLeast(1).of(mock).isInitiated(); will(returnValue(true));
                    break;
                    
                case SAVED:
                    atLeast(1).of(mock).isInitiated(); will(returnValue(false));
                    atLeast(1).of(mock).isSaved(); will(returnValue(true));
                    break;
                    
                case ENROUTE:
                    atLeast(1).of(mock).isInitiated(); will(returnValue(false));
                    atLeast(1).of(mock).isSaved(); will(returnValue(false));
                    atLeast(2).of(mock).isEnroute(); will(returnValue(true));
                    break;
                    
                case APPROVED:
                    atLeast(1).of(mock).isInitiated(); will(returnValue(false));
                    atLeast(1).of(mock).isSaved(); will(returnValue(false));
                    atLeast(1).of(mock).isEnroute(); will(returnValue(false));
                    atLeast(1).of(mock).isApproved(); will(returnValue(true));
                    break;
                    
                case DISAPPROVED:
                    atLeast(1).of(mock).isInitiated(); will(returnValue(false));
                    atLeast(1).of(mock).isSaved(); will(returnValue(false));
                    atLeast(1).of(mock).isEnroute(); will(returnValue(false));
                    atLeast(1).of(mock).isApproved(); will(returnValue(false));
                    atLeast(1).of(mock).isDisapproved(); will(returnValue(true));
                    break;
                    
                case CANCELED:
                    atLeast(1).of(mock).isInitiated(); will(returnValue(false));
                    atLeast(1).of(mock).isSaved(); will(returnValue(false));
                    atLeast(1).of(mock).isEnroute(); will(returnValue(false));
                    atLeast(1).of(mock).isApproved(); will(returnValue(false));
                    atLeast(1).of(mock).isDisapproved(); will(returnValue(false));
                    atLeast(1).of(mock).isCanceled(); will(returnValue(true));
                    break;
                    
                case EXCEPTION:
                    atLeast(1).of(mock).isInitiated(); will(returnValue(false));
                    atLeast(1).of(mock).isSaved(); will(returnValue(false));
                    atLeast(1).of(mock).isEnroute(); will(returnValue(false));
                    atLeast(1).of(mock).isApproved(); will(returnValue(false));
                    atLeast(1).of(mock).isDisapproved(); will(returnValue(false));
                    atLeast(1).of(mock).isCanceled(); will(returnValue(false));
                    break;
                
            }
        }});
    }
}
