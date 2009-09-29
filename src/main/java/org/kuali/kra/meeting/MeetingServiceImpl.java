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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateUtils;
import org.apache.struts.action.ActionForm;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.web.struts.form.schedule.Time12HrFmt;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.ErrorMessage;
import org.kuali.rice.kns.util.GlobalVariables;

public class MeetingServiceImpl implements MeetingService {

    BusinessObjectService businessObjectService;

    private Date getAgendaGenerationDate(Long scheduleId) {
        Map fieldValues = new HashMap();
        fieldValues.put("scheduleIdFk", scheduleId);
        List<ScheduleAgenda> scheduleAgendas = (List<ScheduleAgenda>) businessObjectService.findMatchingOrderBy(
                ScheduleAgenda.class, fieldValues, "createTimestamp", false);
        if (scheduleAgendas.isEmpty()) {
            return null;
        }
        else {
            return new Date(scheduleAgendas.get(0).getCreateTimestamp().getTime());
        }
    }

    public void SaveMeetingDetails(CommitteeSchedule committeeSchedule, List<CommScheduleActItem> deletedOtherActions) {
        committeeSchedule.setStartTime(addHrMinToDate(committeeSchedule.getStartTime(),committeeSchedule.getViewStartTime()));
        committeeSchedule.setEndTime(addHrMinToDate(committeeSchedule.getEndTime(),committeeSchedule.getViewEndTime()));
        committeeSchedule.setTime(addHrMinToDate(committeeSchedule.getTime(),committeeSchedule.getViewTime()));

        if (!deletedOtherActions.isEmpty()) {
            businessObjectService.delete(deletedOtherActions);
        }
        businessObjectService.save(committeeSchedule);
        // a hook to display "successfully saved" message
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorKey("message.saved");
        GlobalVariables.getMessageList().add(errorMessage);

    }

    private Timestamp addHrMinToDate(Timestamp time, Time12HrFmt viewTime) {
        java.util.Date dt = new java.util.Date(time.getTime());
        dt = DateUtils.round(dt, Calendar.DAY_OF_MONTH);
        return new Timestamp(DateUtils.addMinutes(dt, viewTime.findMinutes()).getTime());        
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public void populateFormHelper(ActionForm form, HttpServletRequest request) {
        Map fieldValues = new HashMap();
        fieldValues.put("id", request.getParameter("scheduleId"));
        CommitteeSchedule commSchedule = (CommitteeSchedule) businessObjectService.findByPrimaryKey(CommitteeSchedule.class,
                fieldValues);
        for (ProtocolSubmission protocolSubmission : commSchedule.getProtocolSubmissions()) {
            ProtocolSubmittedBean protocolSubmittedBean = new ProtocolSubmittedBean();
            ProtocolPerson pi = protocolSubmission.getProtocol().getPrincipalInvestigator();
            protocolSubmittedBean.setPersonId(pi.getPersonId());
            protocolSubmittedBean.setPersonName(pi.getPersonName());
            protocolSubmittedBean.setRolodexId(pi.getRolodexId());
//            protocolSubmittedBean.setProtocolPersonId(pi.getProtocolPersonId());
            ((MeetingForm) form).getMeetingHelper().getProtocolSubmittedBeans().add(protocolSubmittedBean);
        }
        ((MeetingForm) form).getMeetingHelper().setAgendaGenerationDate(getAgendaGenerationDate(commSchedule.getId()));
        ((MeetingForm) form).getMeetingHelper().setCommitteeSchedule(commSchedule);

        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.applyPattern("MM/dd/yyyy");

        String label = commSchedule.getCommittee().getCommitteeName() + " #" + request.getParameter("lineNum") + " Meeting "
                + dateFormat.format(commSchedule.getScheduledDate());
        ((MeetingForm) form).getMeetingHelper().setTabLabel(label);

    }

}
