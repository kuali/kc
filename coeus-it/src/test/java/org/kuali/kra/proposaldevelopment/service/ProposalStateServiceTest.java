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
package org.kuali.kra.proposaldevelopment.service;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.state.ProposalState;
import org.kuali.coeus.propdev.impl.state.ProposalStateService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.krad.bo.DocumentHeader;

import static org.junit.Assert.assertEquals;
/**
 * Unit Tests for the Proposal State Service Implementation.
 */
public class ProposalStateServiceTest extends KcIntegrationTestBase {
    
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
        mockery = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
        service = KcServiceLocator.getService(ProposalStateService.class);
        mock = mockery.mock(WorkflowDocument.class);
    }

    /**
     * A proposal in the initiated state must always have a proposal state of In Progress.
     * It is impossible for an initiated proposal to be submitted, thus it is not tested.
     */
    @Test
    public void testInitiated() {
        runTest(INITIATED, false, false, ProposalState.IN_PROGRESS, ProposalState.IN_PROGRESS, null);
    }
    
    /**
     * For a proposal in the saved state, it's proposal state depends upon whether
     * it has been submitted or not.  The change in route status does not matter.
     */
    @Test
    public void testSaved() {
        runTest(SAVED, false, false, ProposalState.IN_PROGRESS, ProposalState.IN_PROGRESS, null);
        runTest(SAVED, true, false, ProposalState.APPROVAL_NOT_INITIATED_SUBMITTED, ProposalState.APPROVAL_NOT_INITIATED_SUBMITTED, null);
    }
    
    /**
     * For a proposal in the enroute state, it's proposal state depends upon whether
     * it has been submitted or not.  The change in route status does not matter.
     */
    @Test
    public void testEnroute() {
        runTest(ENROUTE, false, false, ProposalState.APPROVAL_PENDING, ProposalState.APPROVAL_PENDING, ProposalState.APPROVAL_PENDING);
        runTest(ENROUTE, true, false, ProposalState.APPROVAL_PENDING_SUBMITTED, ProposalState.APPROVAL_PENDING_SUBMITTED, ProposalState.APPROVAL_PENDING);
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
        runTest(APPROVED, false, false, ProposalState.APPROVAL_GRANTED, ProposalState.APPROVAL_GRANTED, ProposalState.APPROVAL_PENDING);
        runTest(APPROVED, true, false, ProposalState.APPROVED_POST_SUBMISSION, ProposalState.APPROVED_POST_SUBMISSION, ProposalState.APPROVAL_PENDING_SUBMITTED);
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
        runTest(DISAPPROVED, false, false, ProposalState.DISAPPROVED, ProposalState.DISAPPROVED, null);
        runTest(DISAPPROVED, true, false, ProposalState.DISAPPROVED_POST_SUBMISSION, ProposalState.DISAPPROVED_POST_SUBMISSION, null);
    }
    
    /**
     * If the proposal is canceled, it remains canceled under all conditions.
     */
    @Test
    public void testCanceled() {
        runTest(CANCELED, false, false, ProposalState.CANCELED, ProposalState.CANCELED, null);
        runTest(CANCELED, true, false, ProposalState.CANCELED, ProposalState.CANCELED, null);
    }
    
    /**
     * If the proposal encounters an exception, it remains in the document error state under all conditions.
     */
    @Test
    public void testException() {
        runTest(EXCEPTION, false, false, ProposalState.DOCUMENT_ERROR, ProposalState.DOCUMENT_ERROR, null);
        runTest(EXCEPTION, true, false, ProposalState.DOCUMENT_ERROR, ProposalState.DOCUMENT_ERROR, null);
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
     * @param previousStateTypeCode
     *  
     */
    private void runTest(int workflowState, boolean isSubmitted, boolean isRejected, String expectedState1, String expectedState2, String previousStateTypeCode) {
        ProposalDevelopmentDocument doc = createProposalDevelopmentDocument(workflowState, isSubmitted);

        doc.getDevelopmentProposal().setProposalStateTypeCode(previousStateTypeCode);
        String state = service.getProposalStateTypeCode(doc, isRejected );
        assertEquals("Proposal State", expectedState1, state);
        
        state = service.getProposalStateTypeCode(doc, isRejected);
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
                    atLeast(1).of(mock).getDocumentId(); will(returnValue(null));
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
