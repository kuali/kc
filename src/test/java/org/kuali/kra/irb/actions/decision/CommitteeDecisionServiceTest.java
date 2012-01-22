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

import java.util.ArrayList;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.committee.bo.CommitteeDecisionMotionType;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.assigncmtsched.ProtocolAssignCmtSchedBean;
import org.kuali.kra.irb.actions.assigncmtsched.ProtocolAssignCmtSchedService;
import org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsBean;
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
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

public class CommitteeDecisionServiceTest extends KcUnitTestBase {
    
    private static final Integer YES_COUNT = 2;
    private static final Integer NO_COUNT = 0;
    private static final Integer ABSTAIN_COUNT = 0;
    private static final Integer RECUSED_COUNT = 0;
    private static final String VOTING_COMMENTS = "just some dumb comments";
    
    private CommitteeDecisionServiceImpl service;
    private ProtocolSubmitActionService protocolSubmitActionService;
    private ProtocolAssignCmtSchedService protocolAssignCmtSchedService;
    
    private Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        service = new CommitteeDecisionServiceImpl();
        service.setProtocolActionService(KraServiceLocator.getService(ProtocolActionService.class));
        service.setBusinessObjectService(KraServiceLocator.getService(BusinessObjectService.class));
        service.setCommitteeService(getMockCommitteeService());
        service.setDocumentService(getMockDocumentService());
        
        protocolSubmitActionService = KraServiceLocator.getService(ProtocolSubmitActionService.class);
        protocolAssignCmtSchedService = KraServiceLocator.getService(ProtocolAssignCmtSchedService.class);
    }

    @Override
    @After
    public void tearDown() throws Exception {
        service = null;
        protocolSubmitActionService = null;
        protocolAssignCmtSchedService = null;
        
        super.tearDown();
    }

    @Test
    public void testProcessApproveCommitteeDecision() throws Exception {
        Protocol protocol = getProtocolAssignedToAgenda();
        
        CommitteeDecision committeeDecision = getMockCommitteeDecisionBean(CommitteeDecisionMotionType.APPROVE);
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
        Protocol protocol = getProtocolAssignedToAgenda();
        
        CommitteeDecision committeeDecision = getMockCommitteeDecisionBean(CommitteeDecisionMotionType.DISAPPROVE);
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
        Protocol protocol = getProtocolAssignedToAgenda();
        
        CommitteeDecision committeeDecision = getMockCommitteeDecisionBean(CommitteeDecisionMotionType.SPECIFIC_MINOR_REVISIONS);
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
        Protocol protocol = getProtocolAssignedToAgenda();
        
        CommitteeDecision committeeDecision = getMockCommitteeDecisionBean(CommitteeDecisionMotionType.SUBSTANTIVE_REVISIONS_REQUIRED);
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
    
    private Protocol getProtocolAssignedToAgenda() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        protocolSubmitActionService.submitToIrbForReview(protocolDocument.getProtocol(), getMockSubmitAction());
        assertEquals(ProtocolStatus.SUBMITTED_TO_IRB, protocolDocument.getProtocol().getProtocolStatusCode());
        
        protocolAssignCmtSchedService.assignToCommitteeAndSchedule(protocolDocument.getProtocol(), getMockAssignCmtSchedBean());
        assertEquals(ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE, protocolDocument.getProtocol().getProtocolSubmission().getSubmissionStatusCode());
        
        return protocolDocument.getProtocol();
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
    
    private ProtocolAssignCmtSchedBean getMockAssignCmtSchedBean() {
        final ProtocolAssignCmtSchedBean bean = context.mock(ProtocolAssignCmtSchedBean.class);
        
        context.checking(new Expectations() {{
            allowing(bean).getNewCommitteeId();
            will(returnValue(Constants.EMPTY_STRING));
            
            allowing(bean).getNewScheduleId();
            will(returnValue(Constants.EMPTY_STRING));
            
            allowing(bean).scheduleHasChanged();
            will(returnValue(false));
        }});
        
        return bean;
    }
    
    private CommitteeService getMockCommitteeService() {
        final CommitteeService service = context.mock(CommitteeService.class);
        
        context.checking(new Expectations() {{
            allowing(service).getAvailableMembers(null, null);
            will(returnValue(new ArrayList<CommitteeMembership>()));
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
    
    private CommitteeDecision getMockCommitteeDecisionBean(final String motionTypeCode) {
        final CommitteeDecision bean = context.mock(CommitteeDecision.class);
        
        context.checking(new Expectations() {{
            allowing(bean).getMotionTypeCode();
            will(returnValue(motionTypeCode));
            
            allowing(bean).getYesCount();
            will(returnValue(YES_COUNT));
            
            allowing(bean).getNoCount();
            will(returnValue(NO_COUNT));
            
            allowing(bean).getAbstainCount();
            will(returnValue(ABSTAIN_COUNT));
            
            allowing(bean).getRecusedCount();
            will(returnValue(RECUSED_COUNT));
            
            allowing(bean).getVotingComments();
            will(returnValue(VOTING_COMMENTS));
            
            allowing(bean).getAbstainers();
            will(returnValue(new ArrayList<CommitteePerson>()));
            
            allowing(bean).getRecused();
            will(returnValue(new ArrayList<CommitteePerson>()));
            
            allowing(bean).getAbstainersToDelete();
            will(returnValue(new ArrayList<CommitteePerson>()));
            
            allowing(bean).getRecusedToDelete();
            will(returnValue(new ArrayList<CommitteePerson>()));
            
            allowing(bean).getReviewCommentsBean();
            will(returnValue(new ReviewCommentsBean(Constants.EMPTY_STRING)));
        }});
        
        return bean;
    }

}