/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
package org.kuali.kra.irb.actions.decision;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.jmock.lib.legacy.ClassImposteriser;
import org.kuali.coeus.common.committee.impl.meeting.MinuteEntryType;
import org.kuali.kra.committee.service.CommitteeScheduleAttendanceService;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsBean;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;

import java.util.ArrayList;
import java.util.Collections;

public abstract class CommitteeDecisionRuleBase extends KcIntegrationTestBase {
    
    protected static final Integer YES_COUNT = 2;
    protected static final Integer NO_COUNT = 0;
    protected static final Integer ABSTAIN_COUNT = 1;
    protected static final Integer RECUSED_COUNT = 1;
    protected static final String VOTING_COMMENTS = "just some dumb comments";
    protected static final String REVIEW_COMMENTS = "More dumb comments";
    
    private Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
        setThreadingPolicy(new Synchroniser());
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
