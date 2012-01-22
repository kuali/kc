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
package org.kuali.kra.meeting;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Test;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.bo.CommitteeMembershipRole;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.bo.MembershipRole;
import org.kuali.kra.committee.web.struts.form.schedule.Time12HrFmt;
import org.kuali.kra.committee.web.struts.form.schedule.Time12HrFmt.MERIDIEM;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondence;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.SequenceAccessorService;

public class MeetingServiceTest extends KcUnitTestBase {
    private Mockery context = new JUnit4Mockery();
    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    private static final String PERSON_ID = "jtester";
    private static final String PERSON_ID_1 = "1";
    private static final String PERSON_ID_2 = "2";
    private static final String PERSON_ID_3 = "3";
    private static final String PERSON_NAME_1 = "test 1";
    private static final String PERSON_NAME_2 = "test 2";
    private static final String PERSON_NAME_3 = "test 3";
    private static final Integer ROLODEX_ID = 1746;
    private static final String MEMBERSHIP_TYPE_CD = "1";
    private static final Date TERM_START_DATE = Date.valueOf("2009-01-01");
    private static final Date TERM_END_DATE = Date.valueOf("2009-01-31");
    private static final Date SCHEDULE_DATE = Date.valueOf("2009-01-15");
    private static final String MEMBERSHIP_ROLE_CD_1 = "1";
    private static final String MEMBERSHIP_ROLE_CD_4 = "4";
    private static final Date ROLE_START_DATE = Date.valueOf("2009-01-10");
    private static final Date ROLE_END_DATE = Date.valueOf("2009-01-20");


    private CommitteeSchedule getCommitteeSchedule() throws Exception {
        CommitteeSchedule committeeSchedule = new CommitteeSchedule();
        committeeSchedule.setId(1L);
        committeeSchedule.setCommittee(createCommittee("test", "committeeName"));
        committeeSchedule.setScheduledDate(new Date(dateFormat.parse("10/01/2009").getTime()));
        committeeSchedule.setTime(new Timestamp(committeeSchedule.getScheduledDate().getTime()));
        committeeSchedule.setPlace("iu - poplar");
        committeeSchedule.setScheduleStatusCode(1);
        return committeeSchedule;
    }

    private Committee createCommittee(String committeeId, String committeeName) {
        Committee committee = new Committee();
        committee.setCommitteeId(committeeId);
        committee.setCommitteeName(committeeName);
        committee.setMaxProtocols(5);
        return committee;
    }

    private List<ScheduleAgenda> getAgendas() throws Exception {
        List<ScheduleAgenda> scheduleAgendas = new ArrayList<ScheduleAgenda>();
        ScheduleAgenda scheduleAgenda = new ScheduleAgenda();
        scheduleAgenda.setScheduleIdFk(1L);
        scheduleAgenda.setAgendaNumber(3);
        scheduleAgenda.setAgendaName("test");
        scheduleAgenda.setScheduleAgendaId(3L);

        scheduleAgenda.setCreateTimestamp(new Timestamp(new Date(dateFormat.parse("10/08/2009").getTime()).getTime()));
        scheduleAgendas.add(scheduleAgenda);
        scheduleAgenda = new ScheduleAgenda();
        scheduleAgenda.setScheduleIdFk(1L);
        scheduleAgenda.setAgendaNumber(2);
        scheduleAgenda.setAgendaName("test");
        scheduleAgenda.setScheduleAgendaId(2L);

        scheduleAgenda.setCreateTimestamp(new Timestamp(new Date(dateFormat.parse("10/05/2009").getTime()).getTime()));
        scheduleAgendas.add(scheduleAgenda);
        scheduleAgenda = new ScheduleAgenda();
        scheduleAgenda.setScheduleIdFk(1L);
        scheduleAgenda.setAgendaNumber(1);
        scheduleAgenda.setAgendaName("test");
        scheduleAgenda.setScheduleAgendaId(1L);


        scheduleAgenda.setCreateTimestamp(new Timestamp(new Date(dateFormat.parse("10/02/2009").getTime()).getTime()));
        scheduleAgendas.add(scheduleAgenda);
        return scheduleAgendas;
    }

    @Test
    public void testSaveCommitteeSchedule() throws Exception {
        MeetingServiceImpl meetingService = new MeetingServiceImpl();
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        final CommitteeSchedule committeeSchedule = getCommitteeSchedule();
        committeeSchedule.setEndTime(committeeSchedule.getTime());
        committeeSchedule.setStartTime(committeeSchedule.getTime());
        committeeSchedule.setViewTime(new Time12HrFmt("01:00", MERIDIEM.PM));
        committeeSchedule.setViewStartTime(new Time12HrFmt("01:00", MERIDIEM.PM));
        committeeSchedule.setViewEndTime(new Time12HrFmt("02:00", MERIDIEM.PM));
        final List<CommScheduleActItem> deletedOtherActions = new ArrayList<CommScheduleActItem>();
        CommScheduleActItem actItem = new CommScheduleActItem();
        deletedOtherActions.add(actItem);
        context.checking(new Expectations() {
            {
                one(businessObjectService).delete(deletedOtherActions);
                one(businessObjectService).save(committeeSchedule);


            }
        });
        meetingService.setBusinessObjectService(businessObjectService);
        meetingService.saveMeetingDetails(committeeSchedule, deletedOtherActions);
        Assert.assertEquals(committeeSchedule.getCommittee().getCommitteeId(), "test");
        Assert.assertEquals(committeeSchedule.getCommittee().getCommitteeName(), "committeeName");
        Assert.assertEquals(committeeSchedule.getPlace(), "iu - poplar");
        Assert.assertEquals(committeeSchedule.getScheduledDate(), new Date(dateFormat.parse("10/01/2009").getTime()));
        Assert.assertEquals(committeeSchedule.getMaxProtocols(), new Integer(5));
        Assert.assertEquals(committeeSchedule.getId(), new Long(1));
        Assert.assertNotSame(committeeSchedule.getTime(), new Timestamp(committeeSchedule.getScheduledDate().getTime()));
        Assert.assertEquals(committeeSchedule.getScheduleStatusCode(), new Integer(1));
        // TODO : need to set up protocolsubmission/otheractions/attendances/minutes for more testing
        // to check whetehr it is really persisted in DB ok or assume the mock 'save' and 'delete' are ok ?

    }

    @Test
    public void testgetStandardReviewComment() throws Exception {
        MeetingServiceImpl meetingService = new MeetingServiceImpl();
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        final ProtocolContingency protocolContingency = new ProtocolContingency();
        protocolContingency.setProtocolContingencyCode("1");
        protocolContingency.setDescription("Protocol Contingency comment #1");
        context.checking(new Expectations() {
            {
                Map<String, String> queryMap = new HashMap<String, String>();
                queryMap.put("protocolContingencyCode", "1");
                one(businessObjectService).findByPrimaryKey(ProtocolContingency.class, queryMap);
                will(returnValue(protocolContingency));

            }
        });
        meetingService.setBusinessObjectService(businessObjectService);
        String description = meetingService.getStandardReviewComment("1");
        Assert.assertEquals(description, "Protocol Contingency comment #1");
        context.checking(new Expectations() {
            {
                Map<String, String> queryMap = new HashMap<String, String>();
                queryMap.put("protocolContingencyCode", "2");
                one(businessObjectService).findByPrimaryKey(ProtocolContingency.class, queryMap);
                will(returnValue(null));

            }
        });
        description = meetingService.getStandardReviewComment("2");
        Assert.assertTrue(description == null);


    }

    @Test
    public void testAddOtherAction() throws Exception {
        MeetingServiceImpl meetingService = new MeetingServiceImpl();
        final SequenceAccessorService sequenceAccessorService = context.mock(SequenceAccessorService.class);
        final CommScheduleActItem newOtherAction = getOtherActionItem(1L, "1", 0);
        newOtherAction.setScheduleActItemTypeCode("1");
        context.checking(new Expectations() {
            {
                one(sequenceAccessorService).getNextAvailableSequenceNumber("SEQ_MEETING_ID");
                will(returnValue(newOtherAction.getCommScheduleActItemsId()));

            }
        });
        meetingService.setSequenceAccessorService(sequenceAccessorService);
        CommitteeSchedule committeeSchedule = new CommitteeSchedule();
        committeeSchedule.setCommScheduleActItems(new ArrayList<CommScheduleActItem>());
        meetingService.addOtherAction(newOtherAction, committeeSchedule);
        Assert.assertTrue(committeeSchedule.getCommScheduleActItems().size() == 1);
        Assert.assertEquals(committeeSchedule.getCommScheduleActItems().get(0).getScheduleActItemTypeCode(), "1");
        Assert.assertEquals(committeeSchedule.getCommScheduleActItems().get(0).getActionItemNumber(), new Integer(1));
    }

    @Test
    public void testDeleteOtherAction() throws Exception {
        MeetingServiceImpl meetingService = new MeetingServiceImpl();
        List<CommScheduleActItem> items = new ArrayList<CommScheduleActItem>();
        List<CommScheduleActItem> deletedItems = new ArrayList<CommScheduleActItem>();
        CommScheduleActItem otherAction1 = getOtherActionItem(1L, "1", 1);
        items.add(otherAction1);
        CommScheduleActItem otherAction2 = getOtherActionItem(2L, "2", 2);
        items.add(otherAction2);
        CommitteeSchedule committeeSchedule = new CommitteeSchedule();
        committeeSchedule.setCommScheduleActItems(items);

        meetingService.deleteOtherAction(committeeSchedule, 1, deletedItems);
        Assert.assertTrue(committeeSchedule.getCommScheduleActItems().size() == 1);
        Assert.assertEquals(committeeSchedule.getCommScheduleActItems().get(0).getScheduleActItemTypeCode(), "1");
        Assert.assertEquals(committeeSchedule.getCommScheduleActItems().get(0).getActionItemNumber(), new Integer(1));
        Assert.assertTrue(deletedItems.size() == 1);
        Assert.assertEquals(deletedItems.get(0).getScheduleActItemTypeCode(), "2");
        Assert.assertEquals(deletedItems.get(0).getActionItemNumber(), new Integer(2));
    }

    private CommScheduleActItem getOtherActionItem(Long commScheduleActItemsId, String scheduleActItemTypeCode, int actionItemNumber) {
        CommScheduleActItem otherAction = new CommScheduleActItem() {
            @Override
            public void refreshReferenceObject(String referenceObjectName) {
                if (referenceObjectName.equals("scheduleActItemType")) {
                    ScheduleActItemType scheduleActItemType = new ScheduleActItemType();
                    scheduleActItemType.setScheduleActItemTypeCode(this.getScheduleActItemTypeCode());
                }

            }
        };

        otherAction.setActionItemNumber(actionItemNumber);
        otherAction.setScheduleActItemTypeCode(scheduleActItemTypeCode);
        otherAction.setCommScheduleActItemsId(commScheduleActItemsId);
        return otherAction;

    }

    @Test
    public void testMarkAbsent() throws Exception {
        MeetingServiceImpl meetingService = new MeetingServiceImpl();
        List<MemberPresentBean> memberPresentBeans = new ArrayList<MemberPresentBean>();
        List<MemberAbsentBean> memberAbsentBeans = new ArrayList<MemberAbsentBean>();
        memberPresentBeans.add(getMemberPresentBean(PERSON_ID_1, PERSON_NAME_1));
        memberPresentBeans.add(getMemberPresentBean(PERSON_ID_2, PERSON_NAME_2));
        memberPresentBeans.add(getMemberPresentBean(PERSON_ID_3, PERSON_NAME_3));

        meetingService.markAbsent(memberPresentBeans, memberAbsentBeans, 1);
        Assert.assertTrue(memberPresentBeans.size() == 2);
        Assert.assertTrue(memberAbsentBeans.size() == 1);
        Assert.assertEquals(memberPresentBeans.get(0).getAttendance().getPersonId(), PERSON_ID_1);
        Assert.assertEquals(memberPresentBeans.get(0).getAttendance().getPersonName(), PERSON_NAME_1);
        Assert.assertEquals(memberPresentBeans.get(1).getAttendance().getPersonId(), PERSON_ID_3);
        Assert.assertEquals(memberPresentBeans.get(1).getAttendance().getPersonName(), PERSON_NAME_3);
        Assert.assertEquals(memberAbsentBeans.get(0).getAttendance().getPersonId(), PERSON_ID_2);
        Assert.assertEquals(memberAbsentBeans.get(0).getAttendance().getPersonName(), PERSON_NAME_2);
    }


    private MemberPresentBean getMemberPresentBean(String personId, String personName) {
        MemberPresentBean memberPresentBean = new MemberPresentBean();
        CommitteeScheduleAttendance attendance = new CommitteeScheduleAttendance();
        attendance.setPersonId(personId);
        attendance.setPersonName(personName);
        memberPresentBean.setAttendance(attendance);
        return memberPresentBean;
    }

    @Test
    public void testPresentVoting() throws Exception {
        MeetingServiceImpl meetingService = new MeetingServiceImpl();
        List<MemberPresentBean> memberPresentBeans = new ArrayList<MemberPresentBean>();
        List<MemberAbsentBean> memberAbsentBeans = new ArrayList<MemberAbsentBean>();
        memberAbsentBeans.add(getMemberAbsentBean(PERSON_ID_1, PERSON_NAME_1));
        memberAbsentBeans.add(getMemberAbsentBean(PERSON_ID_2, PERSON_NAME_2));
        memberAbsentBeans.add(getMemberAbsentBean(PERSON_ID_3, PERSON_NAME_3));
        CommitteeSchedule committeeSchedule = new CommitteeSchedule();

        committeeSchedule.setCommittee(getCommitteeWithMember());
        // TODO : test if "alternate for" role ?
        committeeSchedule.setScheduledDate(SCHEDULE_DATE);
        MeetingHelper meetingHelper = new MeetingHelper(new MeetingForm());
        meetingHelper.setCommitteeSchedule(committeeSchedule);
        meetingHelper.setMemberAbsentBeans(memberAbsentBeans);
        meetingHelper.setMemberPresentBeans(memberPresentBeans);
        meetingService.presentVoting(meetingHelper, 1);
        Assert.assertTrue(memberPresentBeans.size() == 1);
        Assert.assertTrue(memberAbsentBeans.size() == 2);
        Assert.assertEquals(memberAbsentBeans.get(0).getAttendance().getPersonId(), PERSON_ID_1);
        Assert.assertEquals(memberAbsentBeans.get(0).getAttendance().getPersonName(), PERSON_NAME_1);
        Assert.assertEquals(memberAbsentBeans.get(1).getAttendance().getPersonId(), PERSON_ID_3);
        Assert.assertEquals(memberAbsentBeans.get(1).getAttendance().getPersonName(), PERSON_NAME_3);
        Assert.assertEquals(memberPresentBeans.get(0).getAttendance().getPersonId(), PERSON_ID_2);
        Assert.assertEquals(memberPresentBeans.get(0).getAttendance().getPersonName(), PERSON_NAME_2);
    }

    @Test
    public void testPresentOther() throws Exception {
        MeetingServiceImpl meetingService = new MeetingServiceImpl();
        List<OtherPresentBean> otherPresentBeans = new ArrayList<OtherPresentBean>();
        List<MemberAbsentBean> memberAbsentBeans = new ArrayList<MemberAbsentBean>();
        memberAbsentBeans.add(getMemberAbsentBean(PERSON_ID_1, PERSON_NAME_1));
        memberAbsentBeans.add(getMemberAbsentBean(PERSON_ID_2, PERSON_NAME_2));
        memberAbsentBeans.add(getMemberAbsentBean(PERSON_ID_3, PERSON_NAME_3));
        CommitteeSchedule committeeSchedule = new CommitteeSchedule();

        committeeSchedule.setCommittee(getCommitteeWithMember());

        committeeSchedule.setScheduledDate(SCHEDULE_DATE);
        MeetingHelper meetingHelper = new MeetingHelper(new MeetingForm());
        meetingHelper.setCommitteeSchedule(committeeSchedule);
        meetingHelper.setMemberAbsentBeans(memberAbsentBeans);
        meetingHelper.setOtherPresentBeans(otherPresentBeans);
        meetingService.presentOther(meetingHelper, 1);
        Assert.assertTrue(otherPresentBeans.size() == 1);
        Assert.assertTrue(memberAbsentBeans.size() == 2);
        Assert.assertEquals(memberAbsentBeans.get(0).getAttendance().getPersonId(), PERSON_ID_1);
        Assert.assertEquals(memberAbsentBeans.get(0).getAttendance().getPersonName(), PERSON_NAME_1);
        Assert.assertEquals(memberAbsentBeans.get(1).getAttendance().getPersonId(), PERSON_ID_3);
        Assert.assertEquals(memberAbsentBeans.get(1).getAttendance().getPersonName(), PERSON_NAME_3);
        Assert.assertEquals(otherPresentBeans.get(0).getAttendance().getPersonId(), PERSON_ID_2);
        Assert.assertEquals(otherPresentBeans.get(0).getAttendance().getPersonName(), PERSON_NAME_2);
    }

    private CommitteeMembership getMembership(String personID, Integer rolodexID, String membershipTypeCode, Date termStartDate,
            Date termEndDate) {
        CommitteeMembership committeeMembership = new CommitteeMembership();
        committeeMembership.setPersonId(personID);
        committeeMembership.setRolodexId(rolodexID);
        committeeMembership.setMembershipTypeCode(membershipTypeCode);
        committeeMembership.setTermStartDate(termStartDate);
        committeeMembership.setTermEndDate(termEndDate);
        return committeeMembership;
    }

    private MemberAbsentBean getMemberAbsentBean(String personId, String personName) {
        MemberAbsentBean memberAbsentBean = new MemberAbsentBean();
        CommitteeScheduleAttendance attendance = new CommitteeScheduleAttendance();
        attendance.setPersonId(personId);
        attendance.setPersonName(personName);
        memberAbsentBean.setAttendance(attendance);
        return memberAbsentBean;
    }

    private CommitteeMembershipRole getRole(String membershipRoleCode, Date startDate, Date endDate) {
        CommitteeMembershipRole committeeMembershipRole = new CommitteeMembershipRole();
        committeeMembershipRole.setMembershipRoleCode(membershipRoleCode);
        committeeMembershipRole.setStartDate(startDate);
        committeeMembershipRole.setEndDate(endDate);
        MembershipRole membershipRole = new MembershipRole();
        membershipRole.setMembershipRoleCode(membershipRoleCode);
        membershipRole.setDescription("Role " + membershipRoleCode);
        committeeMembershipRole.setMembershipRole(membershipRole);
        return committeeMembershipRole;
    }

    @Test
    public void testAddOtherPresent() throws Exception {
        MeetingServiceImpl meetingService = new MeetingServiceImpl();
        OtherPresentBean newOtherPresentBean = getOtherPresentBean(PERSON_ID_1, PERSON_NAME_1, true);
        CommitteeSchedule committeeSchedule = new CommitteeSchedule();
        List<OtherPresentBean> otherPresentBeans = new ArrayList<OtherPresentBean>();
        List<MemberAbsentBean> memberAbsentBeans = new ArrayList<MemberAbsentBean>();
        memberAbsentBeans.add(getMemberAbsentBean(PERSON_ID_1, PERSON_NAME_1));

        committeeSchedule.setCommittee(getCommitteeWithMember());

        committeeSchedule.setScheduledDate(SCHEDULE_DATE);

        MeetingHelper meetingHelper = new MeetingHelper(new MeetingForm());
        meetingHelper.setMemberAbsentBeans(memberAbsentBeans);
        meetingHelper.setOtherPresentBeans(otherPresentBeans);
        meetingHelper.setNewOtherPresentBean(newOtherPresentBean);
        meetingHelper.setCommitteeSchedule(committeeSchedule);
        meetingService.addOtherPresent(meetingHelper);
        Assert.assertTrue(otherPresentBeans.size() == 1);
        Assert.assertTrue(memberAbsentBeans.size() == 0);
        Assert.assertEquals(otherPresentBeans.get(0).getAttendance().getPersonId(), PERSON_ID_1);
        Assert.assertEquals(otherPresentBeans.get(0).getAttendance().getPersonName(), PERSON_NAME_1);
    }

    private Committee getCommitteeWithMember() {
        Committee committee = new Committee();
        CommitteeMembership committeeMembership = getMembership(PERSON_ID_1, null, MEMBERSHIP_TYPE_CD, TERM_START_DATE,
                TERM_END_DATE);
        committeeMembership.getMembershipRoles().add(getRole(MEMBERSHIP_ROLE_CD_1, ROLE_START_DATE, ROLE_END_DATE));
        committee.getCommitteeMemberships().add(committeeMembership);
        committee.getCommitteeMemberships()
                .add(getMembership(null, ROLODEX_ID, MEMBERSHIP_TYPE_CD, TERM_START_DATE, TERM_END_DATE));
        committeeMembership.getMembershipRoles().add(getRole(MEMBERSHIP_ROLE_CD_4, ROLE_START_DATE, ROLE_END_DATE));
        return committee;
    }
    
    @Test
    public void testdeleteOtherPresent() throws Exception {
        MeetingServiceImpl meetingService = new MeetingServiceImpl();
        CommitteeSchedule committeeSchedule = new CommitteeSchedule();
        List<OtherPresentBean> otherPresentBeans = new ArrayList<OtherPresentBean>();
        otherPresentBeans.add(getOtherPresentBean(PERSON_ID_1, PERSON_NAME_1, true));
        otherPresentBeans.add(getOtherPresentBean(PERSON_ID_3, PERSON_NAME_3, false));
        List<MemberAbsentBean> memberAbsentBeans = new ArrayList<MemberAbsentBean>();
        memberAbsentBeans.add(getMemberAbsentBean(PERSON_ID_2, PERSON_NAME_2));

        committeeSchedule.setCommittee(getCommitteeWithMember());

        committeeSchedule.setScheduledDate(SCHEDULE_DATE);

        MeetingHelper meetingHelper = new MeetingHelper(new MeetingForm());
        meetingHelper.setMemberAbsentBeans(memberAbsentBeans);
        meetingHelper.setOtherPresentBeans(otherPresentBeans);
        meetingHelper.setCommitteeSchedule(committeeSchedule);
        meetingService.deleteOtherPresent(meetingHelper, 0);
        Assert.assertTrue(otherPresentBeans.size() == 1);
        Assert.assertTrue(memberAbsentBeans.size() == 2);
        Assert.assertEquals(otherPresentBeans.get(0).getAttendance().getPersonId(), PERSON_ID_3);
        Assert.assertEquals(otherPresentBeans.get(0).getAttendance().getPersonName(), PERSON_NAME_3);
        Assert.assertEquals(memberAbsentBeans.get(0).getAttendance().getPersonId(), PERSON_ID_2);
        Assert.assertEquals(memberAbsentBeans.get(0).getAttendance().getPersonName(), PERSON_NAME_2);
        Assert.assertEquals(memberAbsentBeans.get(1).getAttendance().getPersonId(), PERSON_ID_1);
        Assert.assertEquals(memberAbsentBeans.get(1).getAttendance().getPersonName(), PERSON_NAME_1);
    }


    private OtherPresentBean getOtherPresentBean(String personId, String personName, boolean isMember) {
        OtherPresentBean otherPresentBean = new OtherPresentBean();
        CommitteeScheduleAttendance attendance = new CommitteeScheduleAttendance();
        attendance.setPersonId(personId);
        attendance.setPersonName(personName);
        otherPresentBean.setAttendance(attendance);
        otherPresentBean.setMember(isMember);
        return otherPresentBean;
    }

    @Test
    public void testAddCommitteeScheduleMinute() throws Exception {
        MeetingServiceImpl meetingService = new MeetingServiceImpl();
        final DateTimeService dateTimeService = context.mock(DateTimeService.class);
        context.checking(new Expectations() {{
            one(dateTimeService).getCurrentTimestamp();
            will(returnValue(new Timestamp(System.currentTimeMillis())));
        }});
        meetingService.setDateTimeService(dateTimeService);
        CommitteeScheduleMinute newCommitteeScheduleMinute = getCommitteeScheduleMinute(1L, "1", 1, 2L);
        CommitteeSchedule committeeSchedule = new CommitteeSchedule();
        committeeSchedule.setId(1L);
        committeeSchedule.setCommitteeScheduleMinutes(new ArrayList<CommitteeScheduleMinute>());
        List<ProtocolSubmission> protocolSubmissions = new ArrayList<ProtocolSubmission>();
        protocolSubmissions.add(getProtocolSubmission(1L));
        committeeSchedule.setProtocolSubmissions(protocolSubmissions);
        MeetingHelper meetingHelper = new MeetingHelper(new MeetingForm());
        meetingHelper.setNewCommitteeScheduleMinute(newCommitteeScheduleMinute);
        meetingHelper.setCommitteeSchedule(committeeSchedule);
        meetingService.addCommitteeScheduleMinute(meetingHelper);
        Assert.assertTrue(committeeSchedule.getCommitteeScheduleMinutes().size() == 1);
        Assert.assertEquals(committeeSchedule.getCommitteeScheduleMinutes().get(0).getMinuteEntryTypeCode(), "1");
        Assert.assertEquals(committeeSchedule.getCommitteeScheduleMinutes().get(0).getEntryNumber(), new Integer(1));
    }

    @Test
    public void testDeleteCommitteeScheduleMinute() throws Exception {
        MeetingServiceImpl meetingService = new MeetingServiceImpl();
        List<CommitteeScheduleMinute> items = new ArrayList<CommitteeScheduleMinute>();
        List<CommitteeScheduleMinute> deletedItems = new ArrayList<CommitteeScheduleMinute>();
        CommitteeScheduleMinute minute1 = getCommitteeScheduleMinute(1L, "1", 1, 3L);
        items.add(minute1);
        CommitteeScheduleMinute minute2 = getCommitteeScheduleMinute(2L, "2", 2, 3L);
        items.add(minute2);
        CommitteeSchedule committeeSchedule = new CommitteeSchedule();
        committeeSchedule.setCommitteeScheduleMinutes(items);

        meetingService.deleteCommitteeScheduleMinute(committeeSchedule, deletedItems, 1);
        Assert.assertTrue(committeeSchedule.getCommitteeScheduleMinutes().size() == 1);
        Assert.assertEquals(committeeSchedule.getCommitteeScheduleMinutes().get(0).getMinuteEntryTypeCode(), "1");
        Assert.assertEquals(committeeSchedule.getCommitteeScheduleMinutes().get(0).getEntryNumber(), new Integer(1));
        Assert.assertTrue(deletedItems.size() == 1);
        Assert.assertEquals(deletedItems.get(0).getMinuteEntryTypeCode(), "2");
        Assert.assertEquals(deletedItems.get(0).getEntryNumber(), new Integer(2));
    }

    private CommitteeScheduleMinute getCommitteeScheduleMinute(Long commScheduleMinutesId, String minuteEntryTypeCode,
            int entryNumber, Long submissionId) {
        CommitteeScheduleMinute committeeScheduleMinute = new CommitteeScheduleMinute() {
            @Override
            public void refreshReferenceObject(String referenceObjectName) {
                if (referenceObjectName.equals("minuteEntryType")) {
                    MinuteEntryType minuteEntryType = new MinuteEntryType();
                    minuteEntryType.setMinuteEntryTypeCode(this.getMinuteEntryTypeCode());
                }

            }
        };
        
        ProtocolSubmission submission = getProtocolSubmission(submissionId);
        committeeScheduleMinute.setProtocol(submission.getProtocol());
        committeeScheduleMinute.setEntryNumber(entryNumber);
        committeeScheduleMinute.setMinuteEntryTypeCode(minuteEntryTypeCode);
        committeeScheduleMinute.setCommScheduleMinutesId(commScheduleMinutesId);
        return committeeScheduleMinute;

    }


    @Test
    public void testPopulateFormHelper() throws Exception {
        MeetingServiceImpl meetingService = new MeetingServiceImpl();
        CommitteeSchedule committeeSchedule = new CommitteeSchedule();

        Committee committee = new Committee();
        committee.setCommitteeId("1");
        committee.setCommitteeName("Test Committee");
        CommitteeMembership committeeMembership = getMembership(PERSON_ID, null, MEMBERSHIP_TYPE_CD, TERM_START_DATE, TERM_END_DATE);
        committeeMembership.getMembershipRoles().add(getRole(MEMBERSHIP_ROLE_CD_1, ROLE_START_DATE, ROLE_END_DATE));
        committee.getCommitteeMemberships().add(committeeMembership);
        committee.getCommitteeMemberships()
                .add(getMembership(null, ROLODEX_ID, MEMBERSHIP_TYPE_CD, TERM_START_DATE, TERM_END_DATE));
        committeeMembership.getMembershipRoles().add(getRole(MEMBERSHIP_ROLE_CD_4, ROLE_START_DATE, ROLE_END_DATE));
        committeeSchedule.setCommittee(committee);
        // TODO : test if "alternate for" role ?
        committeeSchedule.setScheduledDate(SCHEDULE_DATE);
        committeeSchedule.setId(1L);
        List<ProtocolSubmission> protocolSubmissions = new ArrayList<ProtocolSubmission>();
        protocolSubmissions.add(getProtocolSubmission(1L));
        protocolSubmissions.add(getProtocolSubmission(2L));
        committeeSchedule.setProtocolSubmissions(protocolSubmissions);
        MeetingHelper meetingHelper = new MeetingHelper(new MeetingForm());
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        final List<ScheduleAgenda> agendas = getAgendas();
        final List<CommScheduleMinuteDoc> minuteDocs = getMinuteDocs();
        final List<ProtocolCorrespondence> correspondences = getCorrespondences();
        context.checking(new Expectations() {
            {
                Map queryMap = new HashMap();
                queryMap.put("scheduleIdFk", 1L);
                one(businessObjectService).findMatchingOrderBy(ScheduleAgenda.class, queryMap, "createTimestamp", true);
                ;
                will(returnValue(agendas));
                one(businessObjectService).findMatchingOrderBy(ScheduleAgenda.class, queryMap, "createTimestamp", true);
                ;
                will(returnValue(agendas));
                one(businessObjectService).findMatchingOrderBy(CommScheduleMinuteDoc.class, queryMap, "createTimestamp", true);
                ;
                will(returnValue(minuteDocs));
                Map queryMap1 = new HashMap();
                queryMap1.put("protocolId", 1L);
                one(businessObjectService).findMatching(ProtocolCorrespondence.class, queryMap1);
                ;
                will(returnValue(correspondences));


            }
        });
        meetingService.setBusinessObjectService(businessObjectService);

        meetingService.populateFormHelper(meetingHelper, committeeSchedule, 1);
        Assert.assertTrue(meetingHelper.getMemberAbsentBeans().size() == 2);
        Assert.assertTrue(meetingHelper.getProtocolSubmittedBeans().size() == 2);
        Assert.assertTrue(meetingHelper.getMemberPresentBeans().size() == 0);
        Assert.assertTrue(meetingHelper.getOtherPresentBeans().size() == 0);
        Assert.assertEquals(meetingHelper.getTabLabel(), "Test Committee #1 Meeting " + dateFormat.format(SCHEDULE_DATE));
        Assert.assertTrue(meetingHelper.getMinuteDocs().size() == 1);
        Assert.assertTrue(meetingHelper.getCorrespondences().size() == 1);
        Assert.assertEquals(meetingHelper.getMinuteDocs().get(0).getScheduleIdFk().toString(), "1");
        Assert.assertEquals(meetingHelper.getCorrespondences().get(0).getProtocolId().toString(), "1");

    }

    

    private List<CommScheduleMinuteDoc> getMinuteDocs() {
        List<CommScheduleMinuteDoc> minuteDocs = new ArrayList<CommScheduleMinuteDoc>();
        CommScheduleMinuteDoc minuteDoc = new CommScheduleMinuteDoc();
        minuteDoc.setScheduleIdFk(1L);
        minuteDocs.add(minuteDoc);
        return minuteDocs;

    }
    
    private List<ProtocolCorrespondence> getCorrespondences() {
        List<ProtocolCorrespondence> correspondences = new ArrayList<ProtocolCorrespondence>();
        ProtocolCorrespondence correspondence = new ProtocolCorrespondence();
        correspondence.setProtocolId(1L);
        correspondences.add(correspondence);
        return correspondences;

    }

    private ProtocolSubmission getProtocolSubmission(Long submissionId) {
        ProtocolSubmission protocolSubmission = new ProtocolSubmission() {
            @Override
            public void refreshReferenceObject(String referenceObjectName) {
                // do nothing
            }

        };
        protocolSubmission.setProtocol(getProtocol());
        protocolSubmission.setSubmissionId(submissionId);
        protocolSubmission.setProtocolId(1L);
        return protocolSubmission;

    }

    private Protocol getProtocol() {
        Protocol protocol = new Protocol() {
            @Override
            public void refreshReferenceObject(String referenceObjectName) {
                // do nothing
            }

            @Override
            public ProtocolPerson getPrincipalInvestigator() {
                ProtocolPerson protocolPerson = new ProtocolPerson();
                protocolPerson.setPersonId(PERSON_ID_1);
                protocolPerson.setPersonName(PERSON_NAME_1);
                return protocolPerson;
            }

        };
        return protocol;

    }

}