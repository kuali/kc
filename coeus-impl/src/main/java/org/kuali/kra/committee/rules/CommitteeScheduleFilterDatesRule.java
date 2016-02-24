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
package org.kuali.kra.committee.rules;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.kra.committee.rule.event.CommitteeScheduleFilterEvent;
import org.kuali.kra.infrastructure.KeyConstants;

import java.sql.Date;

public class CommitteeScheduleFilterDatesRule extends KcTransactionalDocumentRuleBase implements KcBusinessRule<CommitteeScheduleFilterEvent> {
    
    public static final String SCHEDULEDATE_FILTERSTARTDATE = "committeeHelper.scheduleData.filterStartDate";
    
    public static final String SCHEDULEDATE_FILTERENDDATE = "committeeHelper.scheduleData.filerEndDate";
    
    public static final String [] MSG = {"Start date", "End date"}; 
            
    @Override
    public boolean processRules(CommitteeScheduleFilterEvent filterCommitteeScheduleEvent) {
        
        boolean rulePassed = true;
        
        Date startDate = filterCommitteeScheduleEvent.getScheduleData().getFilterStartDate();
        Date endDate = filterCommitteeScheduleEvent.getScheduleData().getFilerEndDate();
        
        if(null == startDate) {
            reportError(SCHEDULEDATE_FILTERSTARTDATE, KeyConstants.ERROR_COMMITTEESCHEDULE_FILTER_DATE, MSG[0]);
            rulePassed = false;
        }
        if(null == endDate) {
            reportError(SCHEDULEDATE_FILTERENDDATE, KeyConstants.ERROR_COMMITTEESCHEDULE_FILTER_DATE, MSG[1]);
            rulePassed = false;
        }     
        
        if(rulePassed && startDate.toString().equals(endDate.toString())) {            
            reportError(SCHEDULEDATE_FILTERENDDATE, KeyConstants.ERROR_COMMITTEESCHEDULE_STARTANDENDDATE_EQUAL);
            rulePassed = false;
        }
        
        if(rulePassed && startDate.after(endDate)) {            
            reportError(SCHEDULEDATE_FILTERENDDATE, KeyConstants.ERROR_COMMITTEESCHEDULE_STARTANDENDDATE);
            rulePassed = false;
        }
        
        return rulePassed;
    }

}
