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
package org.kuali.kra.irb.actions.reviewcomments;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.committee.impl.meeting.CommitteeScheduleMinuteBase;
import org.kuali.coeus.common.committee.impl.meeting.MinuteEntryType;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolFinderDao;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kim.api.role.RoleService;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.*;

public class ReviewCommentsServiceTest extends KcIntegrationTestBase{
    
    private static final String FIRST_COMMENT = "First Review Comment";
    private static final String SECOND_COMMENT = "Second Review Comment";
    private static final Long PROTOCOL_ONLINE_REVIEW_FK_ID = new Long("12514314361461436");

    private ReviewCommentsServiceImpl service;
    
    private Mockery context = new JUnit4Mockery() {{
        setThreadingPolicy(new Synchroniser());
    }};
    
    @Before
    public void setUp() throws Exception {

        service = new ReviewCommentsServiceImpl();
    }
    
    @After
    public void tearDown() throws Exception {
        service = null;
        
    }

    @Test
    public void testAddReviewComment() throws Exception {

        Protocol protocol = ProtocolFactory.createProtocolDocument().getProtocol();

        List<CommitteeScheduleMinuteBase> reviewComments = new ArrayList<CommitteeScheduleMinuteBase>();

        CommitteeScheduleMinute firstNewReviewComment = new CommitteeScheduleMinute();
        firstNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.PROTOCOL);
        firstNewReviewComment.setMinuteEntry(FIRST_COMMENT);
        service.setDateTimeService(getMockDateTimeService());

        service.addReviewComment(firstNewReviewComment, reviewComments, protocol);

        assertEquals(1, reviewComments.size());
        CommitteeScheduleMinute firstReviewComment = (CommitteeScheduleMinute) reviewComments.get(0);
        assertNotNull(firstReviewComment);
        assertEquals(MinuteEntryType.PROTOCOL, firstReviewComment.getMinuteEntryTypeCode());
        assertEquals(Integer.valueOf(0), firstReviewComment.getEntryNumber());
        assertEquals(FIRST_COMMENT, firstReviewComment.getMinuteEntry());

        CommitteeScheduleMinute secondNewReviewComment = new CommitteeScheduleMinute();
        secondNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.PROTOCOL);
        secondNewReviewComment.setMinuteEntry(SECOND_COMMENT);

        service.addReviewComment(secondNewReviewComment, reviewComments, protocol);

        assertEquals(2, reviewComments.size());
        CommitteeScheduleMinute secondReviewComment = (CommitteeScheduleMinute) reviewComments.get(1);
        assertNotNull(secondReviewComment);
        assertEquals(MinuteEntryType.PROTOCOL, secondReviewComment.getMinuteEntryTypeCode());
        assertEquals(Integer.valueOf(1), secondReviewComment.getEntryNumber());
        assertEquals(SECOND_COMMENT, secondReviewComment.getMinuteEntry());
    }

    @Test
    public void testHideReviewerNameProtocolFalse() throws Exception {
        List<CommitteeScheduleMinute> reviewComments = new ArrayList<CommitteeScheduleMinute>();
        CommitteeSchedule committeeSchedule = createCommitteeSchedule("10", createCommittee());

        CommitteeScheduleMinute firstNewReviewComment = new CommitteeScheduleMinute() {
            @Override
            public void refreshReferenceObject(String referenceObjectName) {
                // do nothing
            }

        };
        firstNewReviewComment.setCommitteeSchedule(committeeSchedule);
        firstNewReviewComment.setCommScheduleMinutesId(1L);
        firstNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.PROTOCOL);
        firstNewReviewComment.setMinuteEntry(FIRST_COMMENT);
        firstNewReviewComment.setCreateUser("majors");
        firstNewReviewComment.setProtocolIdFk(1L);
        reviewComments.add(firstNewReviewComment);
        
        CommitteeScheduleMinute secondNewReviewComment = new CommitteeScheduleMinute() {
            @Override
            public void refreshReferenceObject(String referenceObjectName) {
                // do nothing
            }

        };
        secondNewReviewComment.setCommitteeSchedule(committeeSchedule);
        secondNewReviewComment.setCommScheduleMinutesId(2L);
        secondNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.PROTOCOL);
        secondNewReviewComment.setMinuteEntry(SECOND_COMMENT);
        secondNewReviewComment.setCreateUser("quickstart");
        secondNewReviewComment.setProtocolIdFk(1L);
        reviewComments.add(secondNewReviewComment);
        
        service.setParameterService(getMockParameterService() );       
        service.setRoleService(getMockRoleService());
        service.setCommitteeService(getMockCommitteeService());
        
        service.setKcPersonService(getMockKcPersonService());
        Protocol protocol = new Protocol();
        protocol.setProtocolNumber("001");
        service.setProtocolFinderDao(getProtocolFinderDao(reviewComments));
        assertFalse(service.setHideReviewerName(protocol, 1));
        assertTrue(firstNewReviewComment.isDisplayReviewerName());
        assertTrue(secondNewReviewComment.isDisplayReviewerName());
    }

    @Test
    public void testHideReviewerNameProtocolTrue() throws Exception {
        GlobalVariables.setUserSession(new UserSession("jtester"));
        List<CommitteeScheduleMinute> reviewComments = new ArrayList<CommitteeScheduleMinute>();
        CommitteeSchedule committeeSchedule = createCommitteeSchedule("10", createCommittee());

        CommitteeScheduleMinute firstNewReviewComment = new CommitteeScheduleMinute() {
            @Override
            public void refreshReferenceObject(String referenceObjectName) {
                // do nothing
            }

        };
        firstNewReviewComment.setCommitteeSchedule(committeeSchedule);
        firstNewReviewComment.setCommScheduleMinutesId(1L);
        firstNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.PROTOCOL);
        firstNewReviewComment.setMinuteEntry(FIRST_COMMENT);
        firstNewReviewComment.setCreateUser("majors");
        firstNewReviewComment.setProtocolIdFk(1L);
        reviewComments.add(firstNewReviewComment);
        
        CommitteeScheduleMinute secondNewReviewComment = new CommitteeScheduleMinute() {
            @Override
            public void refreshReferenceObject(String referenceObjectName) {
                // do nothing
            }

        };
        secondNewReviewComment.setCommitteeSchedule(committeeSchedule);
        secondNewReviewComment.setCommScheduleMinutesId(2L);
        secondNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.PROTOCOL);
        secondNewReviewComment.setMinuteEntry(SECOND_COMMENT);
        secondNewReviewComment.setCreateUser("quickstart");
        secondNewReviewComment.setProtocolIdFk(1L);
        reviewComments.add(secondNewReviewComment);
        
        service.setParameterService(getMockParameterService() );       
        service.setKimRoleManagementService(getMockRoleService1());
        service.setCommitteeService(getMockCommitteeService());
        
        service.setKcPersonService(getMockKcPersonService());
        Protocol protocol = new Protocol();
        protocol.setProtocolNumber("001");;
        service.setProtocolFinderDao(getProtocolFinderDao(reviewComments));
        assertTrue(service.setHideReviewerName(protocol, 1));
        assertFalse(firstNewReviewComment.isDisplayReviewerName());
        assertFalse(secondNewReviewComment.isDisplayReviewerName());
    }

    @Test
    public void testHideReviewerNameProtocolFalsePartial() throws Exception {
        GlobalVariables.setUserSession(new UserSession("majors"));
        List<CommitteeScheduleMinute> reviewComments = new ArrayList<CommitteeScheduleMinute>();
        CommitteeSchedule committeeSchedule = createCommitteeSchedule("10", createCommittee());

        CommitteeScheduleMinute firstNewReviewComment = new CommitteeScheduleMinute() {
            @Override
            public void refreshReferenceObject(String referenceObjectName) {
                // do nothing
            }       
            
            @Override
            public boolean isAccepted() {
                return true;
            }

        };
        
        firstNewReviewComment.setCommitteeSchedule(committeeSchedule);
        firstNewReviewComment.setCommScheduleMinutesId(1L);
        firstNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.PROTOCOL);
        firstNewReviewComment.setProtocolOnlineReviewIdFk(PROTOCOL_ONLINE_REVIEW_FK_ID);
        firstNewReviewComment.setMinuteEntry(FIRST_COMMENT);
        firstNewReviewComment.setCreateUser("majors");
        firstNewReviewComment.setProtocolIdFk(1L);
        reviewComments.add(firstNewReviewComment);
        
        CommitteeScheduleMinute secondNewReviewComment = new CommitteeScheduleMinute() {
            @Override
            public void refreshReferenceObject(String referenceObjectName) {
                // do nothing
            }
            
            @Override
            public boolean isAccepted() {
                return true;
            }

        };
        secondNewReviewComment.setCommitteeSchedule(committeeSchedule);
        secondNewReviewComment.setCommScheduleMinutesId(2L);
        secondNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.PROTOCOL);
        secondNewReviewComment.setProtocolOnlineReviewIdFk(PROTOCOL_ONLINE_REVIEW_FK_ID);
        secondNewReviewComment.setMinuteEntry(SECOND_COMMENT);
        secondNewReviewComment.setCreateUser("quickstart");
        secondNewReviewComment.setProtocolIdFk(1L);
        reviewComments.add(secondNewReviewComment);
        
        service.setParameterService(getMockParameterService() );       
        service.setKimRoleManagementService(getMockRoleService1());
        service.setCommitteeService(getMockCommitteeService());
        service.setBusinessObjectService(getMockBusinessObjectService());
        
        service.setKcPersonService(getMockKcPersonService());
        Protocol protocol = new Protocol();
        protocol.setProtocolNumber("001");
        
        
        service.setProtocolFinderDao(getProtocolFinderDao(reviewComments));
        assertFalse(service.setHideReviewerName(protocol, 1));
        assertTrue(firstNewReviewComment.isDisplayReviewerName());
        assertFalse(secondNewReviewComment.isDisplayReviewerName());
    }

    @Test
    public void testHideReviewerNameFalse() throws Exception {
        List<CommitteeScheduleMinute> reviewComments = new ArrayList<CommitteeScheduleMinute>();
        
        CommitteeSchedule committeeSchedule = createCommitteeSchedule("10", createCommittee());
        CommitteeScheduleMinute firstNewReviewComment = new CommitteeScheduleMinute() {
            @Override
            public void refreshReferenceObject(String referenceObjectName) {
                // do nothing
            }

        };
        firstNewReviewComment.setCommitteeSchedule(committeeSchedule);
        firstNewReviewComment.setCommScheduleMinutesId(1L);
        firstNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.PROTOCOL);
        firstNewReviewComment.setMinuteEntry(FIRST_COMMENT);
        firstNewReviewComment.setCreateUser("majors");
        firstNewReviewComment.setProtocolIdFk(1L);
        reviewComments.add(firstNewReviewComment);
        
        CommitteeScheduleMinute secondNewReviewComment = new CommitteeScheduleMinute() {
            @Override
            public void refreshReferenceObject(String referenceObjectName) {
                // do nothing
            }

        };
        secondNewReviewComment.setCommitteeSchedule(committeeSchedule);
        secondNewReviewComment.setCommScheduleMinutesId(2L);
        secondNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.ACTION_ITEM);
        secondNewReviewComment.setMinuteEntry(SECOND_COMMENT);
        secondNewReviewComment.setCreateUser("quickstart");
        secondNewReviewComment.setProtocolIdFk(1L);
        reviewComments.add(secondNewReviewComment);
        
        service.setParameterService(getMockParameterService() );       
        service.setKimRoleManagementService(getMockRoleService());
        service.setCommitteeService(getMockCommitteeService());
        
        service.setKcPersonService(getMockKcPersonService());
        
        assertFalse(service.setHideReviewerName(reviewComments));
        assertTrue(firstNewReviewComment.isDisplayReviewerName());
        assertTrue(secondNewReviewComment.isDisplayReviewerName());
    }
 
    @Test
    public void testHideReviewerNameFalsePartial() throws Exception {
        GlobalVariables.setUserSession(new UserSession("majors"));
        List<CommitteeScheduleMinute> reviewComments = new ArrayList<CommitteeScheduleMinute>();
        CommitteeSchedule committeeSchedule = createCommitteeSchedule("10", createCommittee());

        CommitteeScheduleMinute firstNewReviewComment = new CommitteeScheduleMinute() {
            @Override
            public void refreshReferenceObject(String referenceObjectName) {
                // do nothing
            }

        };
        firstNewReviewComment.setCommitteeSchedule(committeeSchedule);        
        firstNewReviewComment.setCommScheduleMinutesId(1L);
        firstNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.PROTOCOL);
        firstNewReviewComment.setMinuteEntry(FIRST_COMMENT);
        firstNewReviewComment.setCreateUser("majors");
        firstNewReviewComment.setProtocolIdFk(1L);
        reviewComments.add(firstNewReviewComment);
        
        CommitteeScheduleMinute secondNewReviewComment = new CommitteeScheduleMinute() {
            @Override
            public void refreshReferenceObject(String referenceObjectName) {
                // do nothing
            }

        };
        secondNewReviewComment.setCommitteeSchedule(committeeSchedule);
        secondNewReviewComment.setCommScheduleMinutesId(2L);
        secondNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.ACTION_ITEM);
        secondNewReviewComment.setMinuteEntry(SECOND_COMMENT);
        secondNewReviewComment.setCreateUser("quickstart");
        secondNewReviewComment.setProtocolIdFk(1L);
        reviewComments.add(secondNewReviewComment);
        
        service.setParameterService(getMockParameterService() );       
        service.setKimRoleManagementService(getMockRoleService1());
        service.setCommitteeService(getMockCommitteeService());
        
        service.setKcPersonService(getMockKcPersonService());
        
        assertFalse(service.setHideReviewerName(reviewComments));
        assertTrue(firstNewReviewComment.isDisplayReviewerName());
        assertFalse(secondNewReviewComment.isDisplayReviewerName());
    }
 
    @Test
    public void testHideReviewerNameTrue() throws Exception {
        GlobalVariables.setUserSession(new UserSession("jtester"));
        List<CommitteeScheduleMinute> reviewComments = new ArrayList<CommitteeScheduleMinute>();
        CommitteeSchedule committeeSchedule = createCommitteeSchedule("10", createCommittee());

        CommitteeScheduleMinute firstNewReviewComment = new CommitteeScheduleMinute() {
            @Override
            public void refreshReferenceObject(String referenceObjectName) {
                // do nothing
            }

        };
        firstNewReviewComment.setCommitteeSchedule(committeeSchedule);
        firstNewReviewComment.setCommScheduleMinutesId(1L);
        firstNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.PROTOCOL);
        firstNewReviewComment.setMinuteEntry(FIRST_COMMENT);
        firstNewReviewComment.setCreateUser("majors");
        firstNewReviewComment.setProtocolIdFk(1L);
        reviewComments.add(firstNewReviewComment);
        
        CommitteeScheduleMinute secondNewReviewComment = new CommitteeScheduleMinute() {
            @Override
            public void refreshReferenceObject(String referenceObjectName) {
                // do nothing
            }

        };
        secondNewReviewComment.setCommitteeSchedule(committeeSchedule);
        secondNewReviewComment.setCommScheduleMinutesId(2L);
        secondNewReviewComment.setMinuteEntryTypeCode(MinuteEntryType.ACTION_ITEM);
        secondNewReviewComment.setMinuteEntry(SECOND_COMMENT);
        secondNewReviewComment.setCreateUser("quickstart");
        secondNewReviewComment.setProtocolIdFk(1L);
        reviewComments.add(secondNewReviewComment);
        
        service.setParameterService(getMockParameterService() );       
        service.setKimRoleManagementService(getMockRoleService1());
        service.setCommitteeService(getMockCommitteeService());
       
        
        service.setKcPersonService(getMockKcPersonService());
        
        assertTrue(service.setHideReviewerName(reviewComments));
        assertFalse(firstNewReviewComment.isDisplayReviewerName());
        assertFalse(secondNewReviewComment.isDisplayReviewerName());
    }

    private DateTimeService getMockDateTimeService() {
        final DateTimeService service = context.mock(DateTimeService.class);
        
        context.checking(new Expectations() {{
            allowing(service).getCurrentTimestamp();
            will(returnValue(new Timestamp(System.currentTimeMillis())));
        }});
        
        return service;
    }

    private ParameterService getMockParameterService() {
        final ParameterService parameterService = context.mock(ParameterService.class);
        
        context.checking(new Expectations() {{
            allowing(parameterService).getParameterValueAsString(ProtocolDocument.class, Constants.PARAMETER_IRB_DISPLAY_REVIEWER_NAME_TO_PROTOCOL_PERSONNEL);
            will(returnValue("0"));
            allowing(parameterService).getParameterValueAsString(ProtocolDocument.class, Constants.PARAMETER_IRB_DISPLAY_REVIEWER_NAME_TO_REVIEWERS);
            will(returnValue("0"));
            allowing(parameterService).getParameterValueAsString(ProtocolDocument.class, Constants.PARAMETER_IRB_DISPLAY_REVIEWER_NAME_TO_ACTIVE_COMMITTEE_MEMBERS);
            will(returnValue("0"));
        }});
        return parameterService;
    }
    
    private RoleService getMockRoleService() {
        final RoleService kimRoleManagementService = context.mock(RoleService.class);
        final Set<String> adminIds = new HashSet<String>();
        adminIds.add("10000000001");
        context.checking(new Expectations() {{
            allowing(kimRoleManagementService).getRoleMemberPrincipalIds("KC-UNT", RoleConstants.IRB_ADMINISTRATOR,
                    null);
            will(returnValue(adminIds));
        }});
        return kimRoleManagementService;
    }
    private RoleService getMockRoleService1() {
        final RoleService kimRoleManagementService = context.mock(RoleService.class);
        final Set<String> adminIds = new HashSet<String>();
        adminIds.add("10000000001");
        final Set<String> aggregatorIds = new HashSet<String>();
        aggregatorIds.add("10000000002");
        final Set<String> viewerId = new HashSet<String>();
        viewerId.add("10000000003");
        context.checking(new Expectations() {{
            allowing(kimRoleManagementService).getRoleMemberPrincipalIds("KC-UNT", RoleConstants.IRB_ADMINISTRATOR,
                    null);
            will(returnValue(adminIds));
            allowing(kimRoleManagementService).getRoleMemberPrincipalIds("KC-PROTOCOL", RoleConstants.PROTOCOL_AGGREGATOR,
                    null);
            will(returnValue(aggregatorIds));
            allowing(kimRoleManagementService).getRoleMemberPrincipalIds("KC-PROTOCOL", RoleConstants.PROTOCOL_VIEWER,
                    null);
            will(returnValue(viewerId));
        }});
        return kimRoleManagementService;
    }

    private KcPersonService getMockKcPersonService() {
        final KcPersonService kcPersonService = context.mock(KcPersonService.class);
        final KcPerson kcPerson = new KcPerson() {
            public String getUserName() {
                
                return "quickstart";
            }

        };
        kcPerson.setPersonId("10000000001");
        
        context.checking(new Expectations() {{
            allowing(kcPersonService).getKcPersonByPersonId("10000000001");
            will(returnValue(kcPerson));
        }});
        return kcPersonService;
    }

    private ProtocolFinderDao getProtocolFinderDao(List<CommitteeScheduleMinute> reviewComments) {
        final ProtocolFinderDao protocolFinderDao = context.mock(ProtocolFinderDao.class);
        final List<ProtocolSubmission> submissions= new ArrayList<ProtocolSubmission>();
        ProtocolSubmission submission = new ProtocolSubmission() {
            @Override
            public void refreshReferenceObject(String referenceObjectName) {
                // do nothing
            }

        };
        submission.setCommitteeScheduleMinutes((List)reviewComments);
        submission.setProtocolNumber("001");
        submission.setSubmissionNumber(1);
        submissions.add(submission);
        context.checking(new Expectations() {{
            allowing(protocolFinderDao).findProtocolSubmissions("001", 1);
            will(returnValue(submissions));
        }});
        return protocolFinderDao;
    }
    
    private BusinessObjectService getMockBusinessObjectService() {
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        
        
        final ProtocolOnlineReview onlineReview = new ProtocolOnlineReview();
        onlineReview.setProtocolOnlineReviewId(PROTOCOL_ONLINE_REVIEW_FK_ID);
        onlineReview.setAdminAccepted(true);
        onlineReview.setReviewerApproved(true);
        
        context.checking(new Expectations() {{
                allowing(businessObjectService).findBySinglePrimaryKey(ProtocolOnlineReview.class, PROTOCOL_ONLINE_REVIEW_FK_ID);
                will(returnValue(onlineReview));
        }});
                
        return businessObjectService;
    }

    private CommitteeService getMockCommitteeService() {
        final CommitteeService committeeService = context.mock(CommitteeService.class);
        final List<CommitteeMembership> members = new ArrayList<CommitteeMembership>();
        CommitteeMembership member = new CommitteeMembership();
        member.setPersonId("10000000005");
        member.setPersonName("chew");
        members.add(member);
   
        final Committee committee = createCommittee();
        context.checking(new Expectations() {{
            allowing(committeeService).getAvailableMembers(with(any(String.class)), with(any(String.class)));
            will(returnValue(members));
            allowing(committeeService).getCommitteeById("KC001");
            will(returnValue(committee));

        }});
        return committeeService;
    }
    
    private Committee createCommittee() {
        Committee committee = new Committee();
        committee.setId(new Long("11111111"));
        committee.setCommitteeId("KC001");
        committee.setCommitteeName("KC IRB 1");
        
        return committee;
    }
    
    private CommitteeSchedule createCommitteeSchedule(String id, Committee committee) {
        CommitteeSchedule committeeSchedule = new CommitteeSchedule();
        committeeSchedule.setCommittee(committee);
        committeeSchedule.setScheduleId(id);
        
        return committeeSchedule;
    }
}
