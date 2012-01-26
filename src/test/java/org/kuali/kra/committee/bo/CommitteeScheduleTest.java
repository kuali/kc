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
package org.kuali.kra.committee.bo;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.kuali.kra.committee.web.struts.form.schedule.Time12HrFmt;
import org.kuali.kra.committee.web.struts.form.schedule.Time12HrFmt.MERIDIEM;

/**
 * This class is Template implementation of BoAttributeTestBase<T> class, to test CommitteeSchedule BO for toStringMapper.
 */

public class CommitteeScheduleTest extends BoAttributeTestBase<CommitteeSchedule> {
    
    private static final int ATTRIBUTE_COUNT = 31;
    
    private static final Date date = new Date(new java.util.Date().getTime());
    private static final Timestamp timestamp = new Timestamp(new java.util.Date().getTime());
        
    private static final String FIELD_ID = "id";
    private static final Long FIELD_ID_VALUE = 1L; 

    private static final String FIELD_SCHEDULEID = "scheduleId"; 
    private static final String FIELD_SCHEDULEID_VALUE = "1"; 

    private static final String FIELD_SCHEDULEDDATE = "scheduledDate";
    private static final Date FIELD_SCHEDULEDDATE_VALUE = date; 

    private static final String FIELD_PLACE = "place";
    private static final String FIELD_PLACE_VALUE = "Davis 103";

    private static final String FIELD_TIME = "time";
    private static Timestamp FIELD_TIME_VALUE;

    private static final String FIELD_PROTOCOLSUBDEADLINE = "protocolSubDeadline";
    private static final Date FIELD_PROTOCOLSUBDEADLINE_VALUE = date;

    private static final String FIELD_SCHEDULESTATUSCODE = "scheduleStatusCode";
    private static final Integer FIELD_SCHEDULESTATUSCODE_VALUE = 1;

    private static final String  FIELD_MEETINGDATE = "meetingDate";
    private static final Date FIELD_MEETINGDATE_VALUE = date;  

    private static final String FIELD_STARTTIME = "startTime";
    private static final Timestamp FIELD_STARTTIME_VALUE = timestamp; 
    
    private static final String FIELD_ENDTIME = "endTime";
    private static final Timestamp FIELD_ENDTIME_VALUE = timestamp; 

    private static final String FIELD_AGENDAPRODREVDATE = "agendaProdRevDate";
    private static final Date FIELD_AGENDAPRODREVDATE_VALUE = date;

    private static final String FIELD_MAXPROTOCOLS = "maxProtocols";
    private static final Integer FIELD_MAXPROTOCOLS_VALUE = 1;

    private static final String FIELD_COMMONTS = "comments";
    private static final String FIELD_COMMONTS_VALUE = "Some comment to test should go here";
    
    private static final String FIELD_AVAILABLE_TO_REVIEWERS = "availableToReviewers";
    private static final boolean FIELD_AVAILABLE_TO_REVIEWERS_VALUE = true;
    
    private static CommitteeSchedule cm = new CommitteeSchedule();

    /**
     * Constructs a CommitteeScheduleTest.java.
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public CommitteeScheduleTest() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {        
        super(ATTRIBUTE_COUNT, cm);
    }

    /**
     * @see org.kuali.kra.committee.bo.BoAttributeTestBase#getFieldMap()
     */
    @SuppressWarnings("unchecked")
    @Override
    protected Map getFieldMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(FIELD_ID, FIELD_ID_VALUE);
        map.put(FIELD_SCHEDULEID, FIELD_SCHEDULEID_VALUE);
        map.put(FIELD_SCHEDULEDDATE, FIELD_SCHEDULEDDATE_VALUE);
        map.put(FIELD_PLACE, FIELD_PLACE_VALUE);
        map.put(FIELD_TIME, FIELD_TIME_VALUE);
        map.put(FIELD_PROTOCOLSUBDEADLINE, FIELD_PROTOCOLSUBDEADLINE_VALUE);
        map.put(FIELD_SCHEDULESTATUSCODE, FIELD_SCHEDULESTATUSCODE_VALUE);
        map.put(FIELD_MEETINGDATE, FIELD_MEETINGDATE_VALUE);
        map.put(FIELD_STARTTIME, FIELD_STARTTIME_VALUE);
        map.put(FIELD_ENDTIME, FIELD_ENDTIME_VALUE);
        map.put(FIELD_AGENDAPRODREVDATE, FIELD_AGENDAPRODREVDATE_VALUE);
        map.put(FIELD_MAXPROTOCOLS, FIELD_MAXPROTOCOLS_VALUE);
        map.put(FIELD_COMMONTS, FIELD_COMMONTS_VALUE);
        map.put(FIELD_AVAILABLE_TO_REVIEWERS, FIELD_AVAILABLE_TO_REVIEWERS_VALUE);
        return map;
    }

    /**
     * @see org.kuali.kra.committee.bo.BoAttributeTestBase#boPrerequisite()
     */
    @Override
    protected void boPrerequisite(){
        super.boPrerequisite();
        java.util.Date dt = new java.util.Date(0);
        Time12HrFmt time12HrFmt = new Time12HrFmt("10:30",MERIDIEM.AM);
        dt = DateUtils.round(dt, Calendar.DAY_OF_MONTH);
        dt = DateUtils.addMinutes(dt, time12HrFmt.findMinutes());
        FIELD_TIME_VALUE = new java.sql.Timestamp(dt.getTime());
    }
    
    /**
     * @see org.kuali.kra.committee.bo.BoAttributeTestBase#boPostrequisite()
     */
    @Override
    protected void boPostrequisite() {
        super.boPostrequisite();
        cm.setViewTime(new Time12HrFmt("10:30",MERIDIEM.AM));
    }
    
    
    @Test
    public void testIsActiveFor() {
        // create two schedules with two different dates. one in November and the other in December
        CommitteeSchedule novemberSchedule = new CommitteeSchedule();
        novemberSchedule.setScheduledDate(Date.valueOf("2011-11-25"));
        
        CommitteeSchedule decemberSchedule = new CommitteeSchedule();
        decemberSchedule.setScheduledDate(Date.valueOf("2011-12-25"));
                
        // create four committee membership mock objects, 
        // one active only for November date, one active for only December date, 
        // one active for both dates, and the last inactive for both dates.
        CommitteeMembership novemberMember = new CommitteeMembership() {            
            public boolean isActive(Date date) {
                if(date.equals(Date.valueOf("2011-11-25"))) {
                    return true;
                }
                else {
                    return false;
                }
            }
        };
        novemberMember.setPersonId("novemberPerson");
        
        CommitteeMembership decemberMember = new CommitteeMembership() {            
            public boolean isActive(Date date) {
                if(date.equals(Date.valueOf("2011-12-25"))) {
                    return true;
                }
                else {
                    return false;
                }
            }     
        };
        decemberMember.setPersonId("decemberPerson");
        
        CommitteeMembership novemberDecemberMember = new CommitteeMembership() {            
            public boolean isActive(Date date) {
                if( (date.equals(Date.valueOf("2011-12-25"))) || (date.equals(Date.valueOf("2011-11-25"))) ) {
                    return true;
                }
                else {
                    return false;
                }
            }
        };
        novemberDecemberMember.setPersonId("novemberDecemberPerson");
        
        CommitteeMembership neitherNovemberNorDecemberMember = new CommitteeMembership() {            
            public boolean isActive(Date date) {
                if ( !(date.equals(Date.valueOf("2011-12-25"))) && !(date.equals(Date.valueOf("2011-11-25"))) ) {
                    return true;
                }
                else {
                    return false;
                }
            }      
        };
        neitherNovemberNorDecemberMember.setPersonId("neitherNovemberNorDecemberPerson");
       
        // create the committee instance and add the memberships to it
        Committee committee = new Committee();
        committee.getCommitteeMemberships().add(novemberMember);
        committee.getCommitteeMemberships().add(decemberMember);
        committee.getCommitteeMemberships().add(novemberDecemberMember);
        committee.getCommitteeMemberships().add(neitherNovemberNorDecemberMember);
        
        // set the committee instance on the two schedules created earlier
        decemberSchedule.setCommittee(committee);
        novemberSchedule.setCommittee(committee);
        
        assertTrue(novemberSchedule.isActiveFor("novemberPerson"));
        assertTrue(decemberSchedule.isActiveFor("decemberPerson"));
        
        assertFalse(novemberSchedule.isActiveFor("decemberPerson"));
        assertFalse(decemberSchedule.isActiveFor("novemberPerson"));
        
        assertTrue(decemberSchedule.isActiveFor("novemberDecemberPerson"));
        assertTrue(novemberSchedule.isActiveFor("novemberDecemberPerson"));
        
        assertFalse(novemberSchedule.isActiveFor("neitherNovemberNorDecemberPerson"));
        assertFalse(decemberSchedule.isActiveFor("neitherNovemberNorDecemberPerson"));
        
        assertFalse(novemberSchedule.isActiveFor(null));
        decemberSchedule.setCommittee(null);
        assertFalse(decemberSchedule.isActiveFor("decemberPerson"));
        // restore committee
        decemberSchedule.setCommittee(committee);
        assertTrue(decemberSchedule.isActiveFor("decemberPerson"));
        
    }
}
