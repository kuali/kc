/*
 * Copyright 2006-2008 The Kuali Foundation
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

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.web.struts.form.schedule.Time12HrFmt;
import org.kuali.kra.committee.web.struts.form.schedule.Time12HrFmt.MERIDIEM;

public class MeetingRuleTest {
    private MeetingDetailsRule rule;
    private Time12HrFmt viewStartTime;
    private Time12HrFmt viewEndTime;
    private Time12HrFmt viewTime;

    @Before
    public void setUp() {
        viewTime = new Time12HrFmt("01:00", MERIDIEM.PM);
        viewStartTime = new Time12HrFmt("01:00", MERIDIEM.PM);
        viewEndTime = new Time12HrFmt("01:00", MERIDIEM.PM);
        rule = new MeetingDetailsRule();
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
    }

    @Test 
    public void testRuleIsOK() throws Exception {
        CommitteeSchedule committeeSchedule = new CommitteeSchedule();
        committeeSchedule.setViewStartTime(viewStartTime);
        committeeSchedule.setViewEndTime(viewEndTime);
        committeeSchedule.setViewTime(viewTime);
        Assert.assertTrue(rule.validateMeetingDetails(committeeSchedule));
        
      viewStartTime.setTime("12:00");
      Assert.assertTrue(rule.validateMeetingDetails(committeeSchedule));

      viewStartTime.setTime("01:00");
      viewEndTime.setTime("02:30");
      Assert.assertTrue(rule.validateMeetingDetails(committeeSchedule));

    }
    
    
    @Test 
    public void testViewTimeIsNotOK() throws Exception {
        testTimeIsNotFormatOk(viewTime);
    }
    
    @Test 
    public void testViewStartTimeIsNotOK() throws Exception {
        testTimeIsNotFormatOk(viewStartTime);

    }
    @Test 
    public void testViewEndTimeIsNotOK() throws Exception {
        testTimeIsNotFormatOk(viewEndTime);

    }
    
    @Test 
    public void testViewEndTimeBeforeViewStartTime() throws Exception {
        CommitteeSchedule committeeSchedule = new CommitteeSchedule();
        committeeSchedule.setViewStartTime(viewStartTime);
        committeeSchedule.setViewEndTime(viewEndTime);
        committeeSchedule.setViewTime(viewTime);
        
        viewEndTime.setMeridiem("AM");
        Assert.assertFalse(rule.validateMeetingDetails(committeeSchedule));
        viewEndTime.setMeridiem("PM");
        viewEndTime.setTime("12:30");
        Assert.assertFalse(rule.validateMeetingDetails(committeeSchedule));
        viewEndTime.setTime("01:30");
        viewStartTime.setTime("02:30");
        Assert.assertFalse(rule.validateMeetingDetails(committeeSchedule));

    }

    private void testTimeIsNotFormatOk(Time12HrFmt time) {
        CommitteeSchedule committeeSchedule = new CommitteeSchedule();
        committeeSchedule.setViewStartTime(viewStartTime);
        committeeSchedule.setViewEndTime(viewEndTime);
        committeeSchedule.setViewTime(viewTime);
        
        time.setTime("13:00");
      Assert.assertFalse(rule.validateMeetingDetails(committeeSchedule));

      time.setTime("12:61");
      Assert.assertFalse(rule.validateMeetingDetails(committeeSchedule));
      time.setTime("13:61");
      Assert.assertFalse(rule.validateMeetingDetails(committeeSchedule));
        
    }

}
