/*
 * Copyright 2005-2013 The Kuali Foundation
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
import java.util.Collections;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.kuali.kra.committee.service.CommitteeScheduleAttendanceService;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsBean;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.kra.common.committee.meeting.MinuteEntryType;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;

public abstract class CommitteeDecisionRuleBase extends KcUnitTestBase {
    
    protected static final Integer YES_COUNT = 2;
    protected static final Integer NO_COUNT = 0;
    protected static final Integer ABSTAIN_COUNT = 1;
    protected static final Integer RECUSED_COUNT = 1;
    protected static final String VOTING_COMMENTS = "just some dumb comments";
    protected static final String REVIEW_COMMENTS = "More dumb comments";
    
    private Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};
    
    protected CommitteeScheduleAttendanceService getMockCommitteeScheduleAttendanceService(final Integer yesCount, final Integer noCount) {
        final CommitteeScheduleAttendanceService service = context.mock(CommitteeScheduleAttendanceService.class);
        
        context.checking(new Expectations() {{
            allowing(service).getActualVotingMembersCount(null, null);
            will(returnValue((yesCount != null ? yesCount : 0) + (noCount != null ? noCount : 0) + ABSTAIN_COUNT + RECUSED_COUNT));
        }});
        
        return service;
    }
    
    protected CommitteeDecision getMockCommitteeDecisionBean(final String motionTypeCode, final Integer yesCount, final Integer noCount, 
            final CommitteePerson newAbstainer, final CommitteePerson newRecused, final Protocol protocol, final boolean hasReviewComment) {
        
        final CommitteeDecision bean = context.mock(CommitteeDecision.class);
        
        context.checking(new Expectations() {{
            allowing(bean).getProtocol();
            will(returnValue(protocol));
            
            allowing(bean).getMotionTypeCode();
            will(returnValue(motionTypeCode));
            
            allowing(bean).getYesCount();
            will(returnValue(yesCount));
            
            allowing(bean).getNoCount();
            will(returnValue(noCount));
            
            allowing(bean).getAbstainCount();
            will(returnValue(ABSTAIN_COUNT));
            
            allowing(bean).getRecusedCount();
            will(returnValue(RECUSED_COUNT));
            
            allowing(bean).getVotingComments();
            will(returnValue(VOTING_COMMENTS));
            
            allowing(bean).getNewAbstainer();
            will(returnValue(newAbstainer));
            
            allowing(bean).getNewRecused();
            will(returnValue(newRecused));
            
            allowing(bean).getAbstainers();
            will(returnValue(Collections.singletonList(getBasicAbstainer())));
            
            allowing(bean).getRecused();
            will(returnValue(Collections.singletonList(getBasicRecused())));
            
            allowing(bean).getAbstainersToDelete();
            will(returnValue(new ArrayList<CommitteePerson>()));
            
            allowing(bean).getRecusedToDelete();
            will(returnValue(new ArrayList<CommitteePerson>()));
            
            allowing(bean).getTotalVoteCount();
            will(returnValue((yesCount != null ? yesCount : 0) + (noCount != null ? noCount : 0) + ABSTAIN_COUNT + RECUSED_COUNT));
            
            allowing(bean).getReviewCommentsBean();
            will(returnValue(getMockReviewCommentsBean(protocol, hasReviewComment)));
            
            allowing(bean).getYesCountValue();
            will(returnValue((yesCount != null ? yesCount : 0)));
            
            allowing(bean).getNoCountValue();
            will(returnValue((noCount != null ? noCount : 0)));
            
        }});
        
        return bean;
    }
    
    private ReviewCommentsBean getMockReviewCommentsBean(final Protocol protocol, final boolean hasReviewComment) {
        final ReviewCommentsBean bean = context.mock(ReviewCommentsBean.class);
        
        context.checking(new Expectations() {{
            allowing(bean).getReviewComments();
            will(returnValue(hasReviewComment? Collections.singletonList(getBasicReviewComment(protocol)) : Collections.EMPTY_LIST));
        }});
        
        return bean;
    }
    
    protected CommitteePerson getBasicAbstainer() {
        CommitteePerson person = new CommitteePerson();
        person.setMembershipId(new Long(1));
        return person;
    }
    
    protected CommitteePerson getBasicRecused() {
        CommitteePerson person = new CommitteePerson();
        person.setMembershipId(new Long(2));
        return person;
    }
    
    protected CommitteePerson getBasicPerson() {
        CommitteePerson person = new CommitteePerson();
        person.setMembershipId(new Long(3));
        return person;
    }
    
    private CommitteeScheduleMinute getBasicReviewComment(Protocol protocol) {
        CommitteeScheduleMinute minute = new CommitteeScheduleMinute();
        minute.setProtocolIdFk(protocol.getProtocolId());
        minute.setProtocol(protocol);
        minute.setMinuteEntryTypeCode(MinuteEntryType.PROTOCOL);
        minute.setMinuteEntry(REVIEW_COMMENTS);
        return minute;
    }
    
}