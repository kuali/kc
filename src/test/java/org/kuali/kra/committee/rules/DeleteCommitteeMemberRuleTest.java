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
package org.kuali.kra.committee.rules;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.rule.event.DeleteCommitteeMemberEvent;
import org.kuali.kra.committee.rule.event.CommitteeMemberEventBase.ErrorType;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.committee.service.impl.CommitteeMembershipServiceImpl;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.actions.submit.ProtocolReviewer;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.kra.meeting.CommitteeScheduleAttendance;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.util.AutoPopulatingList;

public class DeleteCommitteeMemberRuleTest extends CommitteeRuleTestBase {
    private DeleteCommitteeMemberRule rule;
    private Mockery context = new JUnit4Mockery();
    private CommitteeMembershipServiceImpl committeeMembershipService;
    
    @Before
    public void setup() throws Exception {
        super.setUp();
        committeeMembershipService = new CommitteeMembershipServiceImpl();
        rule = new DeleteCommitteeMemberRule() {
            // TODO : this is a compromise at this point because lots of real data has to be set up in order to do this
            // test.   "new ComitteeDocument" requires spring service access, so it can not be done with jmock.
            // however, still using jmock for businessobjectservice.  Once, we have committee & protocol data
            // preloaded, then we should not do this override here.
            private static final String ID = "document.committeeList[0].committeeMemberships[";
            private static final String AS_REVIEWER = "as the person is a reviewer of the protocol";
            private static final String AS_ATTENDANCE = "as the person has attended a schedule meeting";
            @Override
            public boolean processRules(DeleteCommitteeMemberEvent event) {

                boolean rulePassed = true;
                int i = 0;
                for (CommitteeMembership member : event.getCommitteeMemberships()) {
                    if (member.isDelete() && committeeMembershipService.isMemberAssignedToReviewer(member,
                            ((CommitteeDocument) event.getDocument()).getCommittee().getCommitteeId())) {
                        reportError(ID + i + "].delete", KeyConstants.ERROR_COMMITTEEMEMBER_DELETE, AS_REVIEWER);
                        rulePassed = false;
                    }
                    if (member.isDelete() && committeeMembershipService.isMemberAttendedMeeting(member,
                            ((CommitteeDocument) event.getDocument()).getCommittee().getCommitteeId())) {
                        reportError(ID + i + "].delete", KeyConstants.ERROR_COMMITTEEMEMBER_DELETE, AS_ATTENDANCE);
                        rulePassed = false;
                    }
                    i++;
                }
                return rulePassed;
            }

        };
    }
    
    @After
    public void tearDown() throws Exception {
        rule = null;
        super.tearDown();
    }
    
    /**
     * Test delete committee member who is not assigned as a reviewer.
     * 
     */
    @Test
    public void testDeleteCommitteeMemberOK() throws Exception {

        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        final Map fieldValues = new HashMap();
        fieldValues.put("committeeId", "test");
        context.checking(new Expectations() {{
            one(businessObjectService).findMatching(ProtocolSubmission.class, fieldValues);
            will(returnValue(getProtocolSubmissions(false)));
        }});
        committeeMembershipService.setBusinessObjectService(businessObjectService);
        final Committee activeCommittee = new Committee();
        activeCommittee.setCommitteeId("test");

        final CommitteeService committeeService = context.mock(CommitteeService.class);
        context.checking(new Expectations() {{
            one(committeeService).getCommitteeById("test");
            will(returnValue(activeCommittee));
        }});
        committeeMembershipService.setCommitteeService(committeeService);
        CommitteeDocument document  = new CommitteeDocument();
        Committee committee = new Committee();
        committee.setCommitteeId("test");
        List<CommitteeMembership> committeeMemberships = new ArrayList<CommitteeMembership>();
        CommitteeMembership committeeMembership = createMembership("100", null, "1", "2009-01-01", "2009-01-10");
        committeeMembership.setDelete(true);
        committeeMemberships.add(committeeMembership);
        List<Committee> committees = new ArrayList<Committee>();
        committees.add(committee);
        committee.setCommitteeMemberships(committeeMemberships);
        document.setCommitteeList(committees);
        DeleteCommitteeMemberEvent event = new DeleteCommitteeMemberEvent(Constants.EMPTY_STRING, document, committeeMemberships, ErrorType.HARDERROR);
        Assert.assertTrue(rule.processRules(event));
        
    }
   
    /**
     * 
     * This method is to check delete a member who is assigned as a reviewer for a protocol.
     * @throws Exception
     */
    @Test
    public void testDeleteCommitteeMemberAsReviewer() throws Exception {

        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        final Map fieldValues = new HashMap();
        fieldValues.put("committeeId", "test");
        context.checking(new Expectations() {{
            one(businessObjectService).findMatching(ProtocolSubmission.class, fieldValues);
            will(returnValue(getProtocolSubmissions(true)));
        }});
        committeeMembershipService.setBusinessObjectService(businessObjectService);
        final Committee activeCommittee = new Committee();
        activeCommittee.setCommitteeId("test");

        final CommitteeService committeeService = context.mock(CommitteeService.class);
        context.checking(new Expectations() {{
            one(committeeService).getCommitteeById("test");
            will(returnValue(activeCommittee));
        }});
        committeeMembershipService.setCommitteeService(committeeService);
        CommitteeDocument document  = new CommitteeDocument();
        Committee committee = new Committee();
        committee.setCommitteeId("test");
        List<CommitteeMembership> committeeMemberships = new ArrayList<CommitteeMembership>();
        CommitteeMembership committeeMembership = createMembership("100", null, "1", "2009-01-01", "2009-01-10");
        committeeMembership.setDelete(true);
        committeeMemberships.add(committeeMembership);
        List<Committee> committees = new ArrayList<Committee>();
        committees.add(committee);
        committee.setCommitteeMemberships(committeeMemberships);
        document.setCommitteeList(committees);
        DeleteCommitteeMemberEvent event = new DeleteCommitteeMemberEvent(Constants.EMPTY_STRING, document, committeeMemberships, ErrorType.HARDERROR);
        Assert.assertFalse(rule.processRules(event));

        
//        assertError("document.committeeList[0].committeeMemberships[0].delete", KeyConstants.ERROR_COMMITTEEMEMBER_DELETE, AS_REVIEWER);
    }

    /**
     * 
     * This method is to check delete a member who has attended a scheduled meeting.
     * @throws Exception
     */
    @Test
    public void testDeleteCommitteeMemberAsAttendance() throws Exception {

        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        final Map fieldValues = new HashMap();
        fieldValues.put("committeeId", "test");
        context.checking(new Expectations() {{
            one(businessObjectService).findMatching(ProtocolSubmission.class, fieldValues);
            will(returnValue(getProtocolSubmissions(false)));
        }});
        committeeMembershipService.setBusinessObjectService(businessObjectService);
        final Committee activeCommittee = new Committee();
        activeCommittee.setCommitteeId("test");
        List<CommitteeSchedule> schedules = new ArrayList<CommitteeSchedule>(); 
        CommitteeScheduleAttendance  attendance = new CommitteeScheduleAttendance();
        CommitteeSchedule  schedule = new CommitteeSchedule();
        List<CommitteeScheduleAttendance> attendances = new ArrayList<CommitteeScheduleAttendance>(); 
        attendance.setPersonId("100");
        attendances.add(attendance);
        schedules.add(schedule);
        schedule.setCommitteeScheduleAttendances(attendances);
        activeCommittee.setCommitteeSchedules(schedules);
        final CommitteeService committeeService = context.mock(CommitteeService.class);
        context.checking(new Expectations() {{
            one(committeeService).getCommitteeById("test");
            will(returnValue(activeCommittee));
        }});
        committeeMembershipService.setCommitteeService(committeeService);
        CommitteeDocument document  = new CommitteeDocument();
        Committee committee = new Committee();
        committee.setCommitteeId("test");
        List<CommitteeMembership> committeeMemberships = new ArrayList<CommitteeMembership>();
        CommitteeMembership committeeMembership = createMembership("100", null, "1", "2009-01-01", "2009-01-10");
        committeeMembership.setDelete(true);
        committeeMemberships.add(committeeMembership);
        List<Committee> committees = new ArrayList<Committee>();
        committees.add(committee);
        committee.setCommitteeMemberships(committeeMemberships);
        document.setCommitteeList(committees);
        DeleteCommitteeMemberEvent event = new DeleteCommitteeMemberEvent(Constants.EMPTY_STRING, document, committeeMemberships, ErrorType.HARDERROR);
        Assert.assertFalse(rule.processRules(event));
        assertError("document.committeeList[0].committeeMemberships[0].delete", KeyConstants.ERROR_COMMITTEEMEMBER_DELETE);
        
    }

    protected void assertError(String propertyKey, String errorKey) {
        AutoPopulatingList errors = GlobalVariables.getMessageMap().getMessages(propertyKey);
        Assert.assertNotNull(errors);
        Assert.assertTrue(errors.size() == 1);
        
        ErrorMessage message = (ErrorMessage) errors.get(0);
        Assert.assertNotNull(message);
        Assert.assertEquals(message.getErrorKey(), errorKey);
    }

    private List<ProtocolSubmission> getProtocolSubmissions(boolean isSameReviewer) {
        List<ProtocolSubmission> submissions = new ArrayList<ProtocolSubmission>();
        List<ProtocolOnlineReview> reviews = new ArrayList<ProtocolOnlineReview>();
        ProtocolOnlineReview review = new ProtocolOnlineReview();
        ProtocolReviewer reviewer = new ProtocolReviewer();
        if (isSameReviewer) {
            reviewer.setPersonId("100");
        } else {
            reviewer.setPersonId("101");
        }
        review.setProtocolReviewer(reviewer);
        reviews.add(review);
        ProtocolSubmission submission = new ProtocolSubmission();
        submission.setProtocolOnlineReviews(reviews);
        submissions.add(submission);
        return submissions;
    }
    
    private CommitteeMembership createMembership(String personID, Integer rolodexID, String membershipTypeCode, String termStartDate, String termEndDate) {
        CommitteeMembership committeeMembership = new CommitteeMembership();
        committeeMembership.setPersonId(personID);
        committeeMembership.setRolodexId(rolodexID);
        committeeMembership.setMembershipTypeCode(membershipTypeCode);
        if (termStartDate != null) {
            committeeMembership.setTermStartDate(Date.valueOf(termStartDate));
        }
        if (termEndDate != null) {
            committeeMembership.setTermEndDate(Date.valueOf(termEndDate));
        }
        return committeeMembership;
    }

}
