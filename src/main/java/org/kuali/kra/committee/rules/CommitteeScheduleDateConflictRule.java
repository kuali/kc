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
package org.kuali.kra.committee.rules;

import java.sql.Date;
import java.util.List;

import org.kuali.core.util.DateUtils;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.rule.AddCommitteeScheduleDateConflictRule;
import org.kuali.kra.committee.rule.event.AddCommitteeScheduleDateConflictEvent;
import org.kuali.kra.committee.service.CommitteeScheduleService;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;

public class CommitteeScheduleDateConflictRule extends CommitteeDocumentRule implements AddCommitteeScheduleDateConflictRule {
    
    public static final String id1 = "document.committee.committeeSchedules[";
    public static final String id2 = "].scheduledDate";
    
    public boolean processAddCommitteeScheduleRuleBusinessRules(AddCommitteeScheduleDateConflictEvent addCommitteeScheduleEvent) {
        boolean rulePassed = true;
        
        List<CommitteeSchedule> committeeSchedules = addCommitteeScheduleEvent.getCommitteeSchedules();
        
        Date scheduleDate = null;
        Date copyOfScheduleDate = null;
        int count = 0;
        StringBuilder sb = null;
        
        for(CommitteeSchedule committeeSchedule : committeeSchedules) {
            scheduleDate = committeeSchedule.getScheduledDate();
            copyOfScheduleDate = committeeSchedule.getCopyOfScheduleDate();            
            if(!DateUtils.isSameDay(scheduleDate, copyOfScheduleDate) && isDateConflicting(scheduleDate)) {
                sb = new StringBuilder();
                sb.append(id1).append(count).append(id2);
                reportError(sb.toString(), KeyConstants.ERROR_COMMITTEESCHEDULE_DATE_CONFLICT, scheduleDate.toString());
                rulePassed = false;
            }
            count++;
        }
        
        return rulePassed;
    }
    
    private boolean isDateConflicting(Date scheduleDate) {
        boolean retVal = false;
        CommitteeScheduleService service = getCommitteeScheduleService();
        List<CommitteeSchedule> committeeSchedules = service.getCommitteeSchedules(scheduleDate);
        if(committeeSchedules.size() != 0)
            retVal = true;
        return retVal;
    }
    
    private CommitteeScheduleService getCommitteeScheduleService() {
        return KraServiceLocator.getService(CommitteeScheduleService.class);
    }

}
