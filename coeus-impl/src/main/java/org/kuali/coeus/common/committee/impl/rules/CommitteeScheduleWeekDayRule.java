/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.common.committee.impl.rules;

import org.kuali.coeus.common.committee.impl.rule.event.CommitteeScheduleWeekDayEvent;
import org.kuali.coeus.common.committee.impl.web.struts.form.schedule.ScheduleData;
import org.kuali.coeus.common.committee.impl.web.struts.form.schedule.StyleKey;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.kra.infrastructure.KeyConstants;

public class CommitteeScheduleWeekDayRule extends KcTransactionalDocumentRuleBase implements KcBusinessRule<CommitteeScheduleWeekDayEvent> {
    
    public static final String ID = "committeeHelper.scheduleData.weeklySchedule.daysOfWeek";
            

    public boolean processRules(CommitteeScheduleWeekDayEvent weekdayCommitteeScheduleEvent) {
        
        boolean rulePassed = true;        
        ScheduleData scheduleData = weekdayCommitteeScheduleEvent.getScheduleData();  
        StyleKey key = StyleKey.valueOf(scheduleData.getRecurrenceType());
        if(key.equalsString(StyleKey.WEEKLY.toString()) && null == scheduleData.getWeeklySchedule().getDaysOfWeek()) {
            reportError(ID, KeyConstants.ERROR_COMMITTEESCHEDULE_WEEKDAY);
            rulePassed = false;
        }        
        return rulePassed;
    }

}
