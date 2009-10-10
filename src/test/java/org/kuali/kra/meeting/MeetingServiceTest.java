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

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.web.struts.form.schedule.Time12HrFmt;
import org.kuali.kra.committee.web.struts.form.schedule.Time12HrFmt.MERIDIEM;
import org.kuali.rice.kns.service.BusinessObjectService;

@RunWith(JMock.class)
public class MeetingServiceTest {
    private Mockery context = new JUnit4Mockery();
    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    
    @Test 
    public void testPopulateFormData() throws Exception {
        MeetingServiceImpl meetingService = new MeetingServiceImpl();
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        final HttpServletRequest request = context.mock(HttpServletRequest.class);
        MeetingForm form = new MeetingForm();
        MeetingHelper meetingHelper = new MeetingHelper(form);
        context.checking(new Expectations() {{
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("id", "1");
            one(businessObjectService).findByPrimaryKey(CommitteeSchedule.class, fieldValues);
            will(returnValue(getCommitteeSchedule()));
            fieldValues= new HashMap<String, Object>();
            fieldValues.put("scheduleIdFk", 1L);
            one(businessObjectService).findMatchingOrderBy(
                    ScheduleAgenda.class, fieldValues, "createTimestamp", false);
            will(returnValue(getAgendas()));
            one(request).getParameter("scheduleId");
            will(returnValue("1"));
            one(request).getParameter("lineNum");
            will(returnValue("2"));

            
        }});
        meetingService.setBusinessObjectService(businessObjectService);
        meetingService.populateFormHelper(form, request);
        CommitteeSchedule committeeSchedule = form.getMeetingHelper().getCommitteeSchedule();
        Assert.assertEquals(committeeSchedule.getCommittee().getCommitteeId(), "test");
        Assert.assertEquals(committeeSchedule.getCommittee().getCommitteeName(), "committeeName");
        Assert.assertEquals(committeeSchedule.getPlace(), "iu - poplar");
        Assert.assertEquals(committeeSchedule.getScheduledDate(), new Date(dateFormat.parse("10/01/2009").getTime()));
        Assert.assertEquals(committeeSchedule.getMaxProtocols(), new Integer(5));
        Assert.assertEquals(committeeSchedule.getId(), new Long(1));
        Assert.assertEquals(committeeSchedule.getTime(), new Timestamp(committeeSchedule.getScheduledDate().getTime()));
        Assert.assertEquals(committeeSchedule.getScheduleStatusCode(), new Integer(1));
        Assert.assertEquals(form.getMeetingHelper().getAgendaGenerationDate(), new Date(dateFormat.parse("10/02/2009").getTime()));
        Assert.assertEquals(form.getMeetingHelper().getTabLabel(), "committeeName #2 Meeting 10/01/2009");
        // TODO : need to set up protocolsubmission/otheractions/attendances/minutes for more testing
    
    }

    private CommitteeSchedule getCommitteeSchedule()  throws Exception {
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
        context.checking(new Expectations() {{
            one(businessObjectService).delete(deletedOtherActions);
            one(businessObjectService).save(committeeSchedule);

            
        }});
        meetingService.setBusinessObjectService(businessObjectService);
        meetingService.SaveMeetingDetails(committeeSchedule, deletedOtherActions);
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

}
