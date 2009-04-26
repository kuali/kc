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

import static org.kuali.kra.committee.rule.event.CommitteeScheduleEventBase.ErrorType.HARDERROR;
import static org.kuali.kra.committee.rule.event.CommitteeScheduleEventBase.ErrorType.SOFTERROR;

import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.rule.event.CommitteeScheduleDateConflictEvent;
import org.kuali.kra.committee.rule.event.CommitteeScheduleEventBase.ErrorType;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.util.DateUtils;

public class CommitteeScheduleDateConflictRule extends ResearchDocumentRuleBase implements BusinessRuleInterface<CommitteeScheduleDateConflictEvent> {
    
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(CommitteeScheduleDateConflictRule.class);
    
    public static final String ID = "document.committeeList[0].committeeSchedules[%1$s].scheduledDate";
    
    public static final String DATES_IN_CONFLICT_ERROR_KEY = "datesInConflict";
    

    /**
     * @see org.kuali.kra.committee.rule.BusinessRuleInterface#processRules(org.kuali.kra.rule.event.KraDocumentEventBase)
     */
    public boolean processRules(CommitteeScheduleDateConflictEvent addCommitteeScheduleEvent) {
        
        boolean rulePassed = true;   
        ErrorType type = addCommitteeScheduleEvent.getType();        
        switch (type) {
            case HARDERROR:
                rulePassed = processHardErrors(addCommitteeScheduleEvent);
                break;
            case SOFTERROR:
                rulePassed = processSoftErrors(addCommitteeScheduleEvent);
                break;
        }
        return rulePassed;
    }
    
    /**
     * This method process hard error in date conflict.
     * @param addCommitteeScheduleEvent
     * @return
     */
    private boolean processHardErrors(CommitteeScheduleDateConflictEvent addCommitteeScheduleEvent) {
        boolean rulePassed = true;
        List<CommitteeSchedule> committeeSchedules = addCommitteeScheduleEvent.getCommitteeSchedules();   
        List<Date> conflictDates = new LinkedList<Date>();
        
        rulePassed = parseUniqueDateSet(committeeSchedules, conflictDates);
        if(!rulePassed) {
            identifyPotentialConflicts(committeeSchedules, conflictDates);
        }
        return rulePassed;
    }
    
    /**
     * Helper method to find if dates in schedule are not conflicting.
     * @param committeeSchedules is list of CommitteeSchedule.
     * @param Date in conflict are added to conflictDates list. 
     * @return true if no conflict else false.
     */
    private boolean parseUniqueDateSet(List<CommitteeSchedule> committeeSchedules, List<Date> conflictDates){
        boolean retVal = true;
        boolean flag = true;
        Set<Date> set = new LinkedHashSet<Date>();
        for(CommitteeSchedule committeeSchedule : committeeSchedules) {
            flag = true;
            flag = set.add(committeeSchedule.getScheduledDate());
            if(!flag)
                conflictDates.add(committeeSchedule.getScheduledDate());
        }        
        if(conflictDates.size() > 0)
            retVal = false;
        return retVal;
    }
    
    /**
     * Helper method to identify potential conflicts and adds error message to map.
     * @param committeeSchedules
     * @param conflictDates
     */
    private void identifyPotentialConflicts(List<CommitteeSchedule> committeeSchedules, List<Date> conflictDates) {
        Date scheduleDate = null;
        int count = 0;
        for(Date date : conflictDates) {
            count = 0;
            for(CommitteeSchedule committeeSchedule : committeeSchedules) {
                scheduleDate = committeeSchedule.getScheduledDate();
                if(DateUtils.isSameDay(date, scheduleDate)){
                    reportError(String.format(ID, count), KeyConstants.ERROR_COMMITTEESCHEDULE_DATE_CONFLICT, scheduleDate.toString());
                }
                count++;
            }   
        }
    }
    
    /**
     * This method process date conflict for soft error message.
     * @param addCommitteeScheduleEvent
     * @return
     */
    private boolean processSoftErrors(CommitteeScheduleDateConflictEvent addCommitteeScheduleEvent) {
        boolean rulePassed = true;
        List<Date> datesInConflict = addCommitteeScheduleEvent.getScheduleData().getDatesInConflict();
        for(Date date: datesInConflict) {
            reportSoftError(DATES_IN_CONFLICT_ERROR_KEY, KeyConstants.ERROR_COMMITTEESCHEDULE_DATES_SKIPPED, date.toString());
        }
        return rulePassed;
    }
}
