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
package org.kuali.kra.committee.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.kra.bo.ResearchArea;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.bo.CommitteeMembershipRole;
import org.kuali.kra.committee.bo.CommitteeResearchArea;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.bo.ScheduleStatus;
import org.kuali.kra.committee.service.impl.CommitteeServiceImpl;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.kra.meeting.ScheduleAgenda;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * Test the methods in CommitteeServiceImpl.
 */
@RunWith(JMock.class)
public class CommitteeServiceTest {
    
    private static final String RESEARCH_AREA_CODE_1 = "01.0101";
    private static final String RESEARCH_AREA_CODE_2 = "01.0102";
    private static final String RESEARCH_AREA_CODE_3 = "01.0103";
    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    
    private Mockery context = new JUnit4Mockery();
    
    /**
     * Verify that the correct committee is returned if it is found.
     */
    @Test
    public void testGetCommitteeByIdFound() {
        CommitteeServiceImpl committeeService = new CommitteeServiceImpl();
        
        /*
         * The CommitteServiceImpl will use the Business Object Service
         * to query the database.  Since we "know" the internals to the
         * CommitteeServiceImpl, we know data to be sent to the Business
         * Object Service and what will be returned if the committee is
         * found.
         */
        final Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("committeeId", "999");
        
        final Collection<Committee> committees = new ArrayList<Committee>();
        Committee committee = new Committee();
        committees.add(committee);
        
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            one(businessObjectService).findMatching(Committee.class, fieldValues); will(returnValue(committees));
        }});
        committeeService.setBusinessObjectService(businessObjectService);
        
        assertEquals(committee, committeeService.getCommitteeById("999"));
    }
    
    /**
     * Verify that null is returned if the committee is not found.
     */
    @Test
    public void testGetCommitteeByIdNotFound() {
        CommitteeServiceImpl committeeService = new CommitteeServiceImpl();
        
        /*
         * The CommitteServiceImpl will use the Business Object Service
         * to query the database.  Since we "know" the internals to the
         * CommitteeServiceImpl, we know data to be sent to the Business
         * Object Service and we know that an empty list of committees
         * is returned if the committee is not in the database.
         */
        final Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("committeeId", "999");
        
        final Collection<Committee> committees = new ArrayList<Committee>();
        
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            one(businessObjectService).findMatching(Committee.class, fieldValues); will(returnValue(committees));
        }});
        committeeService.setBusinessObjectService(businessObjectService);
        
        assertEquals(null, committeeService.getCommitteeById("999"));
    }
    
    /**
     * Verify that we can add a valid research area to a committee.
     */
    @Test
    public void testAddResearchAreas() {
        CommitteeServiceImpl committeeService = new CommitteeServiceImpl();
        Committee committee = new Committee();
        
        List<ResearchArea> researchAreas = new ArrayList<ResearchArea>();
        ResearchArea researchArea1 = new ResearchArea();
        researchArea1.setResearchAreaCode(RESEARCH_AREA_CODE_1);
        researchAreas.add(researchArea1);
        
        ResearchArea researchArea2 = new ResearchArea();
        researchArea2.setResearchAreaCode(RESEARCH_AREA_CODE_2);
        researchAreas.add(researchArea2);
        
        committeeService.addResearchAreas(committee, researchAreas);
        List<CommitteeResearchArea> committeeResearchAreas = committee.getCommitteeResearchAreas();
        assertEquals(2, committeeResearchAreas.size());
        
        assertEquals(RESEARCH_AREA_CODE_1, committeeResearchAreas.get(0).getResearchAreaCode());
        assertEquals(RESEARCH_AREA_CODE_2, committeeResearchAreas.get(1).getResearchAreaCode());
        
        // Try adding a new research along with two duplicates
        
        ResearchArea researchArea3 = new ResearchArea();
        researchArea3.setResearchAreaCode(RESEARCH_AREA_CODE_3);
        researchAreas.add(researchArea3);
        
        committeeService.addResearchAreas(committee, researchAreas);
        committeeResearchAreas = committee.getCommitteeResearchAreas();
        assertEquals(3, committeeResearchAreas.size());
        
        assertEquals(RESEARCH_AREA_CODE_1, committeeResearchAreas.get(0).getResearchAreaCode());
        assertEquals(RESEARCH_AREA_CODE_2, committeeResearchAreas.get(1).getResearchAreaCode());
        assertEquals(RESEARCH_AREA_CODE_3, committeeResearchAreas.get(2).getResearchAreaCode());
    }
    
    /**
     * If there are no scheduled dates, then we should only
     * retrieve one drop down entry for "select".
     */
    @Test
    public void testNoAvailDates() {
        CommitteeServiceImpl committeeService = new CommitteeServiceImpl();
        Committee committee = new Committee();
        initCommitteeService(committeeService, committee);
        
        List<KeyValue> availDates = committeeService.getAvailableCommitteeDates("999");
        assertEquals(1, availDates.size());
        assertEquals("", availDates.get(0).getKey());
    }
    
    /**
     * Only the dates that are in the future and factoring in the advanced
     * submission days can be shown the drop-down menu.  Test this with two
     * dates: one that is today and another that is 20 twenty days from now.
     * Since the advanced submission days is 10, only the second date should
     * be returned.
     */
    @Test
    public void testAvailDates() {
        CommitteeServiceImpl committeeService = new CommitteeServiceImpl();
        Committee committee = new Committee();
        initCommitteeService(committeeService, committee);
 
        ScheduleStatus cs = new ScheduleStatus();
        cs.setDescription("Scheduled");
        cs.setScheduleStatusCode(new Integer(1));
 
        committee.setAdvancedSubmissionDaysRequired(10);
        
        CommitteeSchedule schedule1 = new CommitteeSchedule();
        schedule1.setScheduleId("1");
        schedule1.setScheduledDate(new Date(System.currentTimeMillis()));
        Calendar now = getCalendar(new java.util.Date());
        now.add(Calendar.DAY_OF_MONTH, -10);
        schedule1.setProtocolSubDeadline(new Date(now.getTimeInMillis()));
        schedule1.setTime(new Timestamp(0));
        schedule1.setScheduleStatus(cs);
        committee.getCommitteeSchedules().add(schedule1);
          
        CommitteeSchedule schedule2 = new CommitteeSchedule();
        schedule2.setScheduleId("2");
        schedule2.setScheduledDate(getDate(20));
        schedule2.setProtocolSubDeadline(getDate(10));
        schedule2.setTime(new Timestamp(0));
        schedule2.setScheduleStatus(cs);
        committee.getCommitteeSchedules().add(schedule2);
        
        List<KeyValue> availDates = committeeService.getAvailableCommitteeDates("999");
        assertEquals(2, availDates.size());
        assertEquals("", availDates.get(0).getKey());
        assertEquals(schedule2.getScheduleId(), availDates.get(1).getKey());
    }
    
    /**
     * To test the retrieval of the members available for a specific scheduled
     * date, use three members with roles that start and end at different times.
     * The first member includes the scheduled date and should be returned.  
     * The second member has a role that ends before the scheduled date and thus
     * should be returned.  The third member has a role that starts after the
     * scheduled date and thus should not be returned.
     */
    @Test
    public void testAvailMembers() {
        CommitteeServiceImpl committeeService = new CommitteeServiceImpl();
        Committee committee = new Committee();
        initCommitteeService(committeeService, committee);
         
        CommitteeSchedule schedule = new CommitteeSchedule();
        schedule.setScheduleId("1");
        schedule.setScheduledDate(getDate(20));
        schedule.setTime(new Timestamp(0));
        committee.getCommitteeSchedules().add(schedule);
        
        CommitteeMembership member1 = new CommitteeMembership();
        member1.setMembershipId("1");
        CommitteeMembershipRole role1 = new CommitteeMembershipRole();
        role1.setStartDate(getDate(0));
        role1.setEndDate(getDate(40));
        role1.setMembershipRoleCode(CommitteeMembershipRole.ALTERNATE_ROLE);
        member1.getMembershipRoles().add(role1);
        committee.getCommitteeMemberships().add(member1);
        
        CommitteeMembership member2 = new CommitteeMembership();
        member2.setMembershipId("2");
        CommitteeMembershipRole role2 = new CommitteeMembershipRole();
        role2.setStartDate(getDate(0));
        role2.setEndDate(getDate(5));
        role2.setMembershipRoleCode(CommitteeMembershipRole.ALTERNATE_ROLE);
        member2.getMembershipRoles().add(role2);
        committee.getCommitteeMemberships().add(member2);
        
        CommitteeMembership member3 = new CommitteeMembership();
        member3.setMembershipId("3");
        CommitteeMembershipRole role3 = new CommitteeMembershipRole();
        role3.setStartDate(getDate(40));
        role3.setEndDate(getDate(45));
        role3.setMembershipRoleCode(CommitteeMembershipRole.ALTERNATE_ROLE);
        member3.getMembershipRoles().add(role3);
        committee.getCommitteeMemberships().add(member3);
        
        List<CommitteeMembership> members = committeeService.getAvailableMembers("999", "1");
        assertEquals(1, members.size());
        assertEquals(member1, members.get(0));
    }
    
    /**
     * Test the getCommitteeSchedule() method.  If a schedule is not
     * found, null is returned.  If found, it should return the correct
     * schedule.
     */
    @Test
    public void testGetCommitteeSchedule() {
        CommitteeServiceImpl committeeService = new CommitteeServiceImpl();
        Committee committee = new Committee();
        
        for (int i = 0; i < 3; i++) {
            CommitteeSchedule schedule = new CommitteeSchedule();
            schedule.setScheduleId(Integer.toString(i+1));
            schedule.setScheduledDate(getDate(i+1));
            committee.getCommitteeSchedules().add(schedule);
        }
       
        CommitteeSchedule s = committeeService.getCommitteeSchedule(committee, "4");
        assertNull(s);
        
        s = committeeService.getCommitteeSchedule(committee, "2");
        assertEquals(committee.getCommitteeSchedules().get(1), s);
    }
    
    /*
     * Initialize a committee service with a business object that will
     * return the given committee.
     */
    private void initCommitteeService(CommitteeServiceImpl committeeService, Committee committee) {
        final Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("committeeId", "999");
        
        final Collection<Committee> committees = new ArrayList<Committee>();
        committees.add(committee);
        
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            one(businessObjectService).findMatching(Committee.class, fieldValues); will(returnValue(committees));
        }});
        committeeService.setBusinessObjectService(businessObjectService);
    }
    
    private Date getDate(int extraDays) {
        Calendar cal = getCalendar(new java.util.Date());
        cal.add(Calendar.DAY_OF_MONTH, extraDays); 
        return new Date(cal.getTimeInMillis());
    }
    
    private Calendar getCalendar(java.util.Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }
    
    /**
     * 
     * This method is to test merge old meeting data to new committee at the time of approval.
     * @throws Exception
     */
    @Test
    public void testMergeCommitteeSchedule() throws Exception {
        CommitteeServiceImpl committeeService = new CommitteeServiceImpl();
        
         final Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("committeeId", "test");
        
        final Collection<Committee> committees = new ArrayList<Committee>();
        committees.add(getCommittee("test",1));
        committees.add(getCommittee("test",2));
        
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            one(businessObjectService).findMatching(Committee.class, fieldValues); will(returnValue(committees));
        }});
        committeeService.setBusinessObjectService(businessObjectService);
        List<CommitteeSchedule> schedules = committeeService.mergeCommitteeSchedule("test");
        Assert.assertTrue(schedules.size() == 2);
        CommitteeSchedule schedule = schedules.get(0);
        ScheduleAgenda agenda = schedule.getScheduleAgendas().get(0);
        assertEquals(schedule.getPlace(), "place 2");
        assertEquals(schedule.getScheduleStatusCode().toString(), "2");
        assertEquals(schedules.get(1).getScheduleId(), "4");
        assertEquals(agenda.getAgendaName(), "name 1");
        CommitteeScheduleMinute minute = schedule.getCommitteeScheduleMinutes().get(0);
        assertEquals(minute.getMinuteEntry(), "comment 1");
        assertEquals(minute.getMinuteEntryTypeCode(), "1");

        
    }

    private Committee getCommittee(String committeeId, Integer sequenceNumber) throws Exception {
        Committee committee = new Committee();
        committee.setCommitteeId(committeeId);
        committee.setSequenceNumber(sequenceNumber);
        committee.setCommitteeSchedules(getCommitteeSchedule("1",sequenceNumber.toString(), sequenceNumber));
        if (sequenceNumber == 1) {
            // this one has no meeting data, so it will not be merged
            CommitteeSchedule committeeSchedule = new CommitteeSchedule();
            committeeSchedule.setCommitteeIdFk(1L);
            committeeSchedule.setScheduledDate(new Date(dateFormat.parse("12/24/2012").getTime()));
            committeeSchedule.setScheduleId("3");
            committee.getCommitteeSchedules().add(committeeSchedule);
            // this one has meeting data so, it will be merged
            committeeSchedule = new CommitteeSchedule();
            committeeSchedule.setCommitteeIdFk(1L);
            committeeSchedule.setScheduledDate(new Date(dateFormat.parse("12/17/2012").getTime()));
            committeeSchedule.setScheduleId("4");
            committeeSchedule.setCommitteeScheduleMinutes(getCommitteeScheduleMinute("3"));
            committee.getCommitteeSchedules().add(committeeSchedule);
        }
        return committee;
    }
    
    private List<CommitteeSchedule> getCommitteeSchedule(String scheduleId, String text, Integer scheduleStatus)  throws Exception {
        List<CommitteeSchedule> schedules = new ArrayList<CommitteeSchedule>();
        CommitteeSchedule committeeSchedule = new CommitteeSchedule();
        committeeSchedule.setCommitteeIdFk(1L);
        committeeSchedule.setScheduledDate(new Date(dateFormat.parse("12/31/2012").getTime()));
        committeeSchedule.setScheduleId(scheduleId);
        committeeSchedule.setScheduleAgendas(getScheduleAgenda("name "+text));
        committeeSchedule.setCommitteeScheduleMinutes(getCommitteeScheduleMinute(text));
        committeeSchedule.setTime(new Timestamp(new java.util.Date().getTime()));
        committeeSchedule.setPlace("place "+text);
        committeeSchedule.setScheduleStatusCode(scheduleStatus);
        schedules.add(committeeSchedule);
        return schedules;
    }
    
    private List<ScheduleAgenda> getScheduleAgenda(String text)  throws Exception {
        List<ScheduleAgenda> agendas = new ArrayList<ScheduleAgenda>();
        ScheduleAgenda agenda = new ScheduleAgenda();
        agenda.setAgendaName(text);
        agendas.add(agenda);
        return agendas;
    }
    
    private List<CommitteeScheduleMinute> getCommitteeScheduleMinute(String text)  throws Exception {
        List<CommitteeScheduleMinute> minutes = new ArrayList<CommitteeScheduleMinute>();
        CommitteeScheduleMinute minute = new CommitteeScheduleMinute();
        minute.setMinuteEntryTypeCode(text);
        minute.setMinuteEntry("comment " + text);
        minutes.add(minute);
        return minutes;
    }
}
