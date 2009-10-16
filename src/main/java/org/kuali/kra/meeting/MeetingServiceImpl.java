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
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.web.struts.form.schedule.Time12HrFmt;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.ErrorMessage;
import org.kuali.rice.kns.util.GlobalVariables;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class MeetingServiceImpl implements MeetingService {

    BusinessObjectService businessObjectService;

    public Date getAgendaGenerationDate(Long scheduleId) {
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

    public void SaveMeetingDetails(CommitteeSchedule committeeSchedule, List<? extends PersistableBusinessObject> deletedBos) {
        committeeSchedule.setStartTime(addHrMinToDate(committeeSchedule.getStartTime(), committeeSchedule.getViewStartTime()));
        committeeSchedule.setEndTime(addHrMinToDate(committeeSchedule.getEndTime(), committeeSchedule.getViewEndTime()));
        committeeSchedule.setTime(addHrMinToDate(committeeSchedule.getTime(), committeeSchedule.getViewTime()));

        if (!deletedBos.isEmpty()) {
            businessObjectService.delete(deletedBos);
        }
        businessObjectService.save(committeeSchedule);
        // a hook to display "successfully saved" message
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorKey("message.saved");
        GlobalVariables.getMessageList().add(errorMessage);

    }

    public String getStandardReviewComment(String protocolContingencyCode) {
        String description = null;
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("protocolContingencyCode", protocolContingencyCode);
        ProtocolContingency protocolContingency = (ProtocolContingency)businessObjectService.findByPrimaryKey(ProtocolContingency.class, queryMap);
        if (protocolContingency!= null) {
            description = protocolContingency.getDescription();
        }
        return description;
    }

    private Timestamp addHrMinToDate(Timestamp time, Time12HrFmt viewTime) {
        java.util.Date dt = new java.util.Date(0);
        dt = DateUtils.round(dt, Calendar.DAY_OF_MONTH);
        return new Timestamp(DateUtils.addMinutes(dt, viewTime.findMinutes()).getTime());
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }


}
