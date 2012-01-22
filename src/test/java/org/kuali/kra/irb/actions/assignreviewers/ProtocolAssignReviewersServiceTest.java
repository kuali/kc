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
package org.kuali.kra.irb.actions.assignreviewers;

import java.sql.Date;
import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.bo.CommitteeMembershipExpertise;
import org.kuali.kra.committee.bo.CommitteeMembershipRole;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.service.CommitteeMembershipService;
import org.kuali.kra.committee.test.CommitteeFactory;
import org.kuali.kra.committee.web.struts.form.schedule.Time12HrFmt;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.assigncmtsched.ProtocolAssignCmtSchedBean;
import org.kuali.kra.irb.actions.assigncmtsched.ProtocolAssignCmtSchedService;
import org.kuali.kra.irb.actions.submit.ProtocolReviewType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * Test the ProtocolDeleteService implementation.
 */
public class ProtocolAssignReviewersServiceTest extends KcUnitTestBase {
    
    private static final String PERSON_ID = "000000003";
    private static final String MEMBERSHIP_TYPE_CD = "1";
    private static final Date TERM_START_DATE = Date.valueOf("2009-01-01");
    private static final Date TERM_END_DATE = Date.valueOf("2009-01-31");
    private static final String MEMBERSHIP_ROLE_CD_1 = "1";
    private static final String MEMBERSHIP_ROLE_CD_4 = "4";
    private static final String MEMBERSHIP_ROLE_CD_7 = "7";
    private static final Date ROLE_START_DATE = Date.valueOf("2009-01-10");
    private static final Date ROLE_END_DATE = Date.valueOf("2009-01-20");
    private static final String RESEARCH_AREA_CODE_1 = "000001";
    private static final String RESEARCH_AREA_CODE_3 = "01.0101";
    private static final String RESEARCH_AREA_CODE_5 = "01.0201";

    private BusinessObjectService businessObjectService;
    private ProtocolAssignCmtSchedService protocolAssignCmtSchedService;
    private CommitteeMembershipService committeeMembershipService;
    private CommitteeDocument committeeDocument;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        protocolAssignCmtSchedService = KraServiceLocator.getService(ProtocolAssignCmtSchedService.class);
        committeeMembershipService = KraServiceLocator.getService(CommitteeMembershipService.class);
        committeeDocument = createCommittee("666");
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        super.tearDown();
    }
    
    /**
     * Verify that a protocol can be assigned to a committee without a schedule.
     * @throws Exception
     */
    @Test
    public void testAssignmentReviewers() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
       
        ProtocolSubmission submission = createSubmission(protocolDocument.getProtocol(), ProtocolSubmissionStatus.PENDING);
        protocolDocument.getProtocol().getProtocolSubmissions().add(submission);
        
        ProtocolAssignCmtSchedBean actionBean = new ProtocolAssignCmtSchedBean(null);
        actionBean.setCommitteeId(committeeDocument.getCommittee().getCommitteeId());
        actionBean.setScheduleId("1");
        
        protocolAssignCmtSchedService.assignToCommitteeAndSchedule(protocolDocument.getProtocol(), actionBean);
        
        assertEquals(ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE, submission.getSubmissionStatusCode());
        assertEquals("666", submission.getCommitteeId());
        assertEquals("1", submission.getScheduleId());
    }
    
    /**
     * Create a submission.
     */
    private ProtocolSubmission createSubmission(Protocol protocol, String statusCode) {
        ProtocolSubmission submission = new ProtocolSubmission();
        submission.setProtocol(protocol);
        submission.setProtocolId(protocol.getProtocolId());
        submission.setProtocolNumber(protocol.getProtocolNumber());
        submission.setSubmissionId(new Long(1));
        submission.setSubmissionNumber(1);
        submission.setSubmissionTypeCode(ProtocolSubmissionType.INITIAL_SUBMISSION);
        submission.setSubmissionStatusCode(statusCode);
        submission.setProtocolReviewTypeCode(ProtocolReviewType.FULL_TYPE_CODE);
        submission.setSubmissionDate(new Date(System.currentTimeMillis()));
        if (StringUtils.equals(statusCode, ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE)) {
            submission.setCommitteeId("1");
        }
        return submission;
    }
    
    /**
     * Create a committee.
     */
    private CommitteeDocument createCommittee(String committeeId) throws WorkflowException {
        CommitteeDocument committeeDocument = CommitteeFactory.createCommitteeDocument(committeeId);
        Committee committee = committeeDocument.getCommittee();
        CommitteeSchedule schedule = new CommitteeSchedule();
        schedule.setScheduleId("1");
        schedule.setPlace("my office");
        schedule.setEndTime(new Timestamp(System.currentTimeMillis() + 100));
        schedule.setScheduledDate(new Date(System.currentTimeMillis()));
        schedule.setStartTime(new Timestamp(System.currentTimeMillis() - 100));
        schedule.setFilter(false);
        schedule.setMaxProtocols(committee.getMaxProtocols());
        schedule.setTime(new Timestamp(System.currentTimeMillis()));
        schedule.setViewTime(new Time12HrFmt(new Timestamp(System.currentTimeMillis())));
        schedule.setProtocolSubDeadline(new Date(System.currentTimeMillis() - 500));
        schedule.setScheduleStatusCode(1);
        committee.getCommitteeSchedules().add(schedule);
        
        CommitteeMembership committeeMembership = getMembership(PERSON_ID, null, MEMBERSHIP_TYPE_CD, TERM_START_DATE, TERM_END_DATE);
        committeeMembership.getMembershipExpertise().add(getExpertise(RESEARCH_AREA_CODE_1));
        committeeMembership.getMembershipExpertise().add(getExpertise(RESEARCH_AREA_CODE_3));
        committeeMembership.getMembershipExpertise().add(getExpertise(RESEARCH_AREA_CODE_5));
        committeeMembership.getMembershipRoles().add(getRole(MEMBERSHIP_ROLE_CD_1, ROLE_START_DATE, ROLE_END_DATE));
        committeeMembership.getMembershipRoles().add(getRole(MEMBERSHIP_ROLE_CD_4, ROLE_START_DATE, ROLE_END_DATE));
        committeeMembership.getMembershipRoles().add(getRole(MEMBERSHIP_ROLE_CD_7, ROLE_START_DATE, ROLE_END_DATE));
        
        committeeMembershipService.addCommitteeMembership(committee, committeeMembership);
        
        businessObjectService.save(committeeDocument);
        return committeeDocument;
    }
    
    /**
     * This method creates and returns a <code>CommitteeMembership</code>.
     * 
     * @param personID
     * @param rolodexID
     * @param membershipTypeCode
     * @param termStartDate
     * @param termEndDate
     * @return CommitteeMembership
     */
    private CommitteeMembership getMembership(String personID, Integer rolodexID, String membershipTypeCode, Date termStartDate, Date termEndDate) {
        CommitteeMembership committeeMembership = new CommitteeMembership();
        committeeMembership.setPersonId(personID);
        committeeMembership.setPersonName("Jack");
        committeeMembership.setRolodexId(rolodexID);
        committeeMembership.setMembershipTypeCode(membershipTypeCode);
        committeeMembership.setTermStartDate(termStartDate);
        committeeMembership.setTermEndDate(termEndDate);
        return committeeMembership;
    }
    
    /**
     * This method creates and returns a <code>CommitteeMembershipRole</code>.
     *
     * @param membershipRoleCode
     * @param startDate
     * @param endDate
     * @return CommitteeMembershipRole
     */
    private CommitteeMembershipRole getRole(String membershipRoleCode, Date startDate, Date endDate) {
        CommitteeMembershipRole committeeMembershipRole = new CommitteeMembershipRole();
        committeeMembershipRole.setMembershipRoleCode(membershipRoleCode);
        committeeMembershipRole.setStartDate(startDate);
        committeeMembershipRole.setEndDate(endDate);
        return committeeMembershipRole;
    }
    
    /**
     * This method creates and returns a <code>CommitteeMembershipExpertise</code>.
     * 
     * @param researchAreaCode
     * @return CommitteeMembershipExpertise
     */
    private CommitteeMembershipExpertise getExpertise(String researchAreaCode) {
        CommitteeMembershipExpertise committeeMembershipExpertise = new CommitteeMembershipExpertise();
        committeeMembershipExpertise.setResearchAreaCode(researchAreaCode);
        return committeeMembershipExpertise;
        
    }
}
