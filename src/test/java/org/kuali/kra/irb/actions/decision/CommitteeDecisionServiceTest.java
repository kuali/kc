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

import java.sql.Timestamp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.committee.bo.CommitteeDecisionMotionType;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.submit.ProtocolReviewType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;


public class CommitteeDecisionServiceTest extends KcUnitTestBase {
    
    private static final Long PROTOCOL_ID = 1234L;
    private static final String PROTOCOL_NUMBER = "123456";
    private static final String COMMITTEE_ID = "1285093659990";
    private static final String SCHEDULE_ID = "10014";
    
    private static final Integer YES_COUNT = 2;
    private static final Integer NO_COUNT = 0;
    private static final Integer ABSTAIN_COUNT = 0;
    private static final Integer RECUSED_COUNT = 0;
    private static final String VOTING_COMMENTS = "just some dumb comments";
    
    private CommitteeDecisionService committeeDecisionService;
    private Protocol protocol;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        committeeDecisionService = KraServiceLocator.getService("protocolCommitteeDecisionService");
        protocol = ProtocolFactory.createProtocolDocument(PROTOCOL_NUMBER).getProtocol();
        protocol.setProtocolId(PROTOCOL_ID);
        
        ProtocolSubmission submission = createSubmission(protocol);
        protocol.getProtocolSubmissions().add(submission);
    }

    @After
    public void tearDown() throws Exception {
        committeeDecisionService = null;
        super.tearDown();
    }

    @Test
    public void testProcessApproveCommitteeDecision() throws Exception {
        CommitteeDecision committeeDecision = new CommitteeDecision(null);
        committeeDecision.setMotionTypeCode(CommitteeDecisionMotionType.APPROVE);
        committeeDecision.setYesCount(YES_COUNT);
        committeeDecision.setNoCount(NO_COUNT);
        committeeDecision.setAbstainCount(ABSTAIN_COUNT);
        committeeDecision.setRecusedCount(RECUSED_COUNT);
        committeeDecision.getReviewComments().setProtocolId(PROTOCOL_ID);
        committeeDecision.setVotingComments(VOTING_COMMENTS);
        committeeDecisionService.processCommitteeDecision(protocol, committeeDecision);
        
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
        CommitteeDecision committeeDecision = new CommitteeDecision(null);
        committeeDecision.setMotionTypeCode(CommitteeDecisionMotionType.DISAPPROVE);
        committeeDecision.setYesCount(YES_COUNT);
        committeeDecision.setNoCount(NO_COUNT);
        committeeDecision.setAbstainCount(ABSTAIN_COUNT);
        committeeDecision.setRecusedCount(RECUSED_COUNT);
        committeeDecision.getReviewComments().setProtocolId(PROTOCOL_ID);
        committeeDecision.setVotingComments(VOTING_COMMENTS);
        committeeDecisionService.processCommitteeDecision(protocol, committeeDecision);
        
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
        CommitteeDecision committeeDecision = new CommitteeDecision(null);
        committeeDecision.setMotionTypeCode(CommitteeDecisionMotionType.SPECIFIC_MINOR_REVISIONS);
        committeeDecision.setYesCount(YES_COUNT);
        committeeDecision.setNoCount(NO_COUNT);
        committeeDecision.setAbstainCount(ABSTAIN_COUNT);
        committeeDecision.setRecusedCount(RECUSED_COUNT);
        committeeDecision.getReviewComments().setProtocolId(PROTOCOL_ID);
        committeeDecision.setVotingComments(VOTING_COMMENTS);
        committeeDecisionService.processCommitteeDecision(protocol, committeeDecision);
        
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
        CommitteeDecision committeeDecision = new CommitteeDecision(null);
        committeeDecision.setMotionTypeCode(CommitteeDecisionMotionType.SUBSTANTIVE_REVISIONS_REQUIRED);
        committeeDecision.setYesCount(YES_COUNT);
        committeeDecision.setNoCount(NO_COUNT);
        committeeDecision.setAbstainCount(ABSTAIN_COUNT);
        committeeDecision.setRecusedCount(RECUSED_COUNT);
        committeeDecision.getReviewComments().setProtocolId(PROTOCOL_ID);
        committeeDecision.setVotingComments(VOTING_COMMENTS);
        committeeDecisionService.processCommitteeDecision(protocol, committeeDecision);
        
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
    
    private ProtocolSubmission createSubmission(Protocol protocol) {
        ProtocolSubmission submission = new ProtocolSubmission();
        submission.setProtocol(protocol);
        submission.setProtocolId(protocol.getProtocolId());
        submission.setProtocolNumber(protocol.getProtocolNumber());
        submission.setSubmissionNumber(1);
        submission.setSubmissionTypeCode(ProtocolSubmissionType.INITIAL_SUBMISSION);
        submission.setSubmissionStatusCode(ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
        submission.setProtocolReviewTypeCode(ProtocolReviewType.FULL_TYPE_CODE);
        submission.setSubmissionDate(new Timestamp(System.currentTimeMillis()));
        submission.setCommitteeId(COMMITTEE_ID);
        submission.setScheduleId(SCHEDULE_ID);
        
        return submission;
    }

}