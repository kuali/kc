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
package org.kuali.kra.irb.actions.decision;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.kra.committee.bo.CommitteeDecisionMotionType;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;

@RunWith(JMock.class)
public class CommitteeDecisionServiceTest {
    
    private static final Long PROTOCOL_ID = 1234L;
    private static final String PROTOCOL_NUMBER = "123456";
    private static final String PROTOCOL_NEXT_ACTION_ID_KEY = "actionId";
    private static final Long SUBMISSION_ID = 1234L;
    private static final String COMMITTEE_ID = "1285093659990";
    private static final String SCHEDULE_ID = "10014";
    
    private static final Integer YES_COUNT = 2;
    private static final Integer NO_COUNT = 0;
    private static final Integer ABSTAIN_COUNT = 0;
    private static final Integer RECUSED_COUNT = 0;
    private static final String VOTING_COMMENTS = "just some dumb comments";
    
    private CommitteeDecisionServiceImpl service;
    private ProtocolActionService protocolActionService;
    private BusinessObjectService businessObjectService;
    private CommitteeService committeeService;
    private DocumentService documentService;
    
    private Protocol protocol;
    private ProtocolDocument protocolDocument;
    private ProtocolSubmission protocolSubmission;
    private ProtocolAction protocolAction;
    
    private Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};

    @Before
    public void setUp() throws Exception {
        businessObjectService = context.mock(BusinessObjectService.class);       
        protocolActionService = context.mock(ProtocolActionService.class);
        committeeService = context.mock(CommitteeService.class);
        documentService = context.mock(DocumentService.class);
        
        service = new CommitteeDecisionServiceImpl();
        service.setBusinessObjectService(businessObjectService);
        service.setProtocolActionService(protocolActionService);
        service.setCommitteeService(committeeService);
        service.setDocumentService(documentService);
    }

    @After
    public void tearDown() throws Exception {
        service = null;
    }

    @Test
    public void testProcessApproveCommitteeDecision() throws Exception {
        prerequisite(CommitteeDecisionMotionType.APPROVE);
        
        CommitteeDecision committeeDecision = createCommitteeDecision(CommitteeDecisionMotionType.APPROVE);
        service.processCommitteeDecision(protocol, committeeDecision);
        
        ProtocolAction lastAction = protocol.getLastProtocolAction();
        assertNotNull(lastAction);
        assertEquals(ProtocolActionType.RECORD_COMMITTEE_DECISION, lastAction.getProtocolActionTypeCode());
        ProtocolSubmission submission = protocol.getProtocolSubmission();
        assertNotNull(submission);
        assertEquals(CommitteeDecisionMotionType.APPROVE, submission.getCommitteeDecisionMotionTypeCode());
        assertEquals(YES_COUNT, submission.getYesVoteCount());
        assertEquals(NO_COUNT, submission.getNoVoteCount());
        assertEquals(ABSTAIN_COUNT, submission.getAbstainerCount());
        assertEquals(RECUSED_COUNT, submission.getRecusedCount());
        assertEquals(VOTING_COMMENTS, submission.getVotingComments());
    }
    
    @Test
    public void testProcessDisapproveCommitteeDecision() throws Exception {
        prerequisite(CommitteeDecisionMotionType.DISAPPROVE);
        
        CommitteeDecision committeeDecision = createCommitteeDecision(CommitteeDecisionMotionType.DISAPPROVE);
        service.processCommitteeDecision(protocol, committeeDecision);
        
        ProtocolAction lastAction = protocol.getLastProtocolAction();
        assertNotNull(lastAction);
        assertEquals(ProtocolActionType.RECORD_COMMITTEE_DECISION, lastAction.getProtocolActionTypeCode());
        ProtocolSubmission submission = protocol.getProtocolSubmission();
        assertNotNull(submission);
        assertEquals(CommitteeDecisionMotionType.DISAPPROVE, submission.getCommitteeDecisionMotionTypeCode());
        assertEquals(YES_COUNT, submission.getYesVoteCount());
        assertEquals(NO_COUNT, submission.getNoVoteCount());
        assertEquals(ABSTAIN_COUNT, submission.getAbstainerCount());
        assertEquals(RECUSED_COUNT, submission.getRecusedCount());
        assertEquals(VOTING_COMMENTS, submission.getVotingComments());
    }
    
    @Test
    public void testProcessSMRCommitteeDecision() throws Exception {
        prerequisite(CommitteeDecisionMotionType.SPECIFIC_MINOR_REVISIONS);
        
        CommitteeDecision committeeDecision = createCommitteeDecision(CommitteeDecisionMotionType.SPECIFIC_MINOR_REVISIONS);
        service.processCommitteeDecision(protocol, committeeDecision);
        
        ProtocolAction lastAction = protocol.getLastProtocolAction();
        assertNotNull(lastAction);
        assertEquals(ProtocolActionType.RECORD_COMMITTEE_DECISION, lastAction.getProtocolActionTypeCode());
        ProtocolSubmission submission = protocol.getProtocolSubmission();
        assertNotNull(submission);
        assertEquals(CommitteeDecisionMotionType.SPECIFIC_MINOR_REVISIONS, submission.getCommitteeDecisionMotionTypeCode());
        assertEquals(YES_COUNT, submission.getYesVoteCount());
        assertEquals(NO_COUNT, submission.getNoVoteCount());
        assertEquals(ABSTAIN_COUNT, submission.getAbstainerCount());
        assertEquals(RECUSED_COUNT, submission.getRecusedCount());
        assertEquals(VOTING_COMMENTS, submission.getVotingComments());
    }
    
    @Test
    public void testProcessSRRCommitteeDecision() throws Exception {
        prerequisite(CommitteeDecisionMotionType.SUBSTANTIVE_REVISIONS_REQUIRED);
        
        CommitteeDecision committeeDecision = createCommitteeDecision(CommitteeDecisionMotionType.SUBSTANTIVE_REVISIONS_REQUIRED);
        service.processCommitteeDecision(protocol, committeeDecision);
        
        ProtocolAction lastAction = protocol.getLastProtocolAction();
        assertNotNull(lastAction);
        assertEquals(ProtocolActionType.RECORD_COMMITTEE_DECISION, lastAction.getProtocolActionTypeCode());
        ProtocolSubmission submission = protocol.getProtocolSubmission();
        assertNotNull(submission);
        assertEquals(CommitteeDecisionMotionType.SUBSTANTIVE_REVISIONS_REQUIRED, submission.getCommitteeDecisionMotionTypeCode());
        assertEquals(YES_COUNT, submission.getYesVoteCount());
        assertEquals(NO_COUNT, submission.getNoVoteCount());
        assertEquals(ABSTAIN_COUNT, submission.getAbstainerCount());
        assertEquals(RECUSED_COUNT, submission.getRecusedCount());
        assertEquals(VOTING_COMMENTS, submission.getVotingComments());
    }
    
    private void prerequisite(String motionTypeCode) throws Exception {
        mockProtocol();
        mockProtocolSubmission(motionTypeCode);
        mockProtocolAction();
        mockProtocolActionService();
        mockBusinessObjectService();
        mockCommitteeService();
        mockDocumentService();
    }
    
    private void mockProtocol() {
        protocol = context.mock(Protocol.class);
        
        context.checking(new Expectations() {{
            allowing(protocol).getProtocolId();
            will(returnValue(PROTOCOL_ID));
            
            allowing(protocol).getProtocolNumber();
            will(returnValue(PROTOCOL_NUMBER));
            
            allowing(protocol).getProtocolDocument();
            will(returnValue(protocolDocument));
            
            allowing(protocol).getSequenceNumber();
            will(returnValue(0));
            
            allowing(protocol).getNextValue(PROTOCOL_NEXT_ACTION_ID_KEY);
            will(returnValue(2));

            allowing(protocol).getProtocolActions();
            will(returnValue(new ArrayList<ProtocolAction>()));
            
            allowing(protocol).refresh();
        }});
    }
    
    private void mockProtocolSubmission(final String motionTypeCode) {
        protocolSubmission = context.mock(ProtocolSubmission.class);
        
        context.checking(new Expectations() {{
            List<ProtocolSubmission> submissions = new ArrayList<ProtocolSubmission>();
            submissions.add(protocolSubmission);
            allowing(protocol).getProtocolSubmissions();
            will(returnValue(submissions));
            
            allowing(protocol).getProtocolSubmission();
            will(returnValue(protocolSubmission));
            
            allowing(protocolSubmission).getSubmissionId();
            will(returnValue(SUBMISSION_ID));
            
            allowing(protocolSubmission).getSubmissionNumber();
            will(returnValue(1));
            
            allowing(protocolSubmission).getSubmissionStatusCode();
            will(returnValue(ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE));
            
            allowing(protocolSubmission).getCommitteeId();
            will(returnValue(COMMITTEE_ID));
            
            allowing(protocolSubmission).getScheduleId();
            will(returnValue(SCHEDULE_ID));
            
            allowing(protocolSubmission).getScheduleIdFk();
            will(returnValue(1L));
            
            allowing(protocolSubmission).getCommitteeDecisionMotionTypeCode();
            will(returnValue(motionTypeCode));
            
            allowing(protocolSubmission).getYesVoteCount();
            will(returnValue(YES_COUNT));
            
            allowing(protocolSubmission).getNoVoteCount();
            will(returnValue(NO_COUNT));
            
            allowing(protocolSubmission).getAbstainerCount();
            will(returnValue(ABSTAIN_COUNT));
            
            allowing(protocolSubmission).getRecusedCount();
            will(returnValue(RECUSED_COUNT));
            
            allowing(protocolSubmission).getVotingComments();
            will(returnValue(VOTING_COMMENTS));
            
            allowing(protocolSubmission).setCommitteeDecisionMotionTypeCode(CommitteeDecisionMotionType.APPROVE);
            allowing(protocolSubmission).setCommitteeDecisionMotionTypeCode(CommitteeDecisionMotionType.DISAPPROVE);
            allowing(protocolSubmission).setCommitteeDecisionMotionTypeCode(CommitteeDecisionMotionType.SPECIFIC_MINOR_REVISIONS);
            allowing(protocolSubmission).setCommitteeDecisionMotionTypeCode(CommitteeDecisionMotionType.SUBSTANTIVE_REVISIONS_REQUIRED);
            allowing(protocolSubmission).setYesVoteCount(YES_COUNT);
            allowing(protocolSubmission).setNoVoteCount(NO_COUNT);
            allowing(protocolSubmission).setAbstainerCount(ABSTAIN_COUNT);
            allowing(protocolSubmission).setRecusedCount(RECUSED_COUNT);
            allowing(protocolSubmission).setVotingComments(VOTING_COMMENTS);
        }});
    }
    
    private void mockProtocolAction() {
        protocolAction = new ProtocolAction(protocol, protocolSubmission, ProtocolActionType.RECORD_COMMITTEE_DECISION);
        
        context.checking(new Expectations() {{
            allowing(protocol).getLastProtocolAction();
            will(returnValue(protocolAction));
        }});
    
    }
    
    private void mockProtocolActionService() {
        context.checking(new Expectations() {{
            allowing(protocolActionService).updateProtocolStatus(protocolAction, protocol);
        }});
    }
    
    private void mockBusinessObjectService() {
        context.checking(new Expectations() {{
            allowing(businessObjectService).save(protocolAction);
            allowing(businessObjectService).save(protocolDocument);
        }});
    }
    
    private void mockCommitteeService() {
        context.checking(new Expectations() {{
            allowing(committeeService).getAvailableMembers(COMMITTEE_ID, SCHEDULE_ID);
            will(returnValue(new ArrayList<CommitteeMembership>()));
        }});
    }
    
    private void mockDocumentService() throws Exception {
        context.checking(new Expectations() {{
            allowing(documentService).saveDocument(protocolDocument);
        }});
    }
    
    private CommitteeDecision createCommitteeDecision(String motionTypeCode) {
        CommitteeDecision committeeDecision = new CommitteeDecision(null);
        committeeDecision.setMotionTypeCode(motionTypeCode);
        committeeDecision.setYesCount(YES_COUNT);
        committeeDecision.setNoCount(NO_COUNT);
        committeeDecision.setAbstainCount(ABSTAIN_COUNT);
        committeeDecision.setRecusedCount(RECUSED_COUNT);
        committeeDecision.getReviewComments().setProtocolId(PROTOCOL_ID);
        committeeDecision.setVotingComments(VOTING_COMMENTS);
        return committeeDecision;
    }

}