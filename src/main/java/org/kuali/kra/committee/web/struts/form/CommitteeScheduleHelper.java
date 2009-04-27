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
package org.kuali.kra.committee.web.struts.form;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.service.CommitteeScheduleService;
import org.kuali.kra.committee.web.struts.form.schedule.ScheduleData;
import org.kuali.kra.infrastructure.KraServiceLocator;

public class CommitteeScheduleHelper implements Serializable {
    
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(CommitteeScheduleHelper.class);
    
    public static final Boolean TRUE = true;
    
    public static final Boolean FALSE = false;
    
    private ScheduleData scheduleData;
    
    private CommitteeForm form;
    
    public CommitteeScheduleHelper(CommitteeForm form) {
        this.form = form;
        this.setScheduleData(new ScheduleData());
    }
    
    /**
     * This method is UI view hook.
     */
    public void prepareView() {
        prepareCommitteeScheduleDeleteView();
    }
    
    public ScheduleData getScheduleData() {
        return scheduleData;
    }

    public void setScheduleData(ScheduleData scheduleData) {
        this.scheduleData = scheduleData;
    }    
    
    /**
     * This method prepares view to filter dates between start and end date.
     * @param startDate
     * @param endDate
     */
    public void prepareFilterDatesView(Date startDate, Date endDate) {
        List<CommitteeSchedule> committeeSchedules = form.getCommitteeDocument().getCommittee().getCommitteeSchedules();
        startDate = DateUtils.addDays(startDate, -1);
        endDate = DateUtils.addDays(endDate, 1);
        Date scheduleDate = null;
        for(CommitteeSchedule committeeSchedule : committeeSchedules) {            
            scheduleDate = committeeSchedule.getScheduledDate();
            if(scheduleDate.after(startDate) && scheduleDate.before(endDate)) 
                committeeSchedule.setFilter(TRUE);            
            else
                committeeSchedule.setFilter(FALSE);
        }
    }
    
    /**
     * This method prepares view to reset filtered dates.
     */
    public void resetFilterDatesView() {
        List<CommitteeSchedule> committeeSchedules = form.getCommitteeDocument().getCommittee().getCommitteeSchedules();
        for(CommitteeSchedule committeeSchedule : committeeSchedules) {
                committeeSchedule.setFilter(TRUE);            
        }
        getScheduleData().setFilterStartDate(null);
        getScheduleData().setFilerEndDate(null);
    }
    
    /**
     * Helper method prepares view for deleteable CommitteeSchedules.
     */
    private void prepareCommitteeScheduleDeleteView(){
        
        List<CommitteeSchedule> committeeSchedules = form.getCommitteeDocument().getCommittee().getCommitteeSchedules();
        boolean flag = false;
        CommitteeScheduleService service = getCommitteeScheduleService();
        for(CommitteeSchedule committeeSchedule: committeeSchedules) {            
            flag = service.isCommitteeScheduleDeletable(committeeSchedule);
            committeeSchedule.setDelete(flag);
        }    
    }
    
    /**
     * This method returns CommitteeScheduleService.
     * @return
     */
    private CommitteeScheduleService getCommitteeScheduleService() {
        return KraServiceLocator.getService(CommitteeScheduleService.class);
    }
}
