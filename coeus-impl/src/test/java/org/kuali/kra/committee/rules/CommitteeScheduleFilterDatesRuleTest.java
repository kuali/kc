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

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.kuali.coeus.common.committee.impl.web.struts.form.schedule.ScheduleData;
import org.kuali.coeus.sys.impl.validation.ErrorReporterImpl;
import org.kuali.kra.committee.rule.event.CommitteeScheduleEventBase.ErrorType;
import org.kuali.kra.committee.rule.event.CommitteeScheduleFilterEvent;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.rules.TemplateRuleTest;

import java.util.Date;

public class CommitteeScheduleFilterDatesRuleTest { 
    
    @Test
    public void testTrue() {
    
        new  TemplateRuleTest<CommitteeScheduleFilterEvent, CommitteeScheduleFilterDatesRule> (){
            
            @Override
            protected void prerequisite() {
                
                ScheduleData scheduleData = new ScheduleData();  
                Date dt = DateUtils.addDays(new Date(), 1);  
                scheduleData.setFilterStartDate(new java.sql.Date(new Date().getTime()));
                scheduleData.setFilerEndDate(new java.sql.Date(dt.getTime()));
                
                event = new CommitteeScheduleFilterEvent(Constants.EMPTY_STRING, null, scheduleData, null, ErrorType.HARDERROR);
                rule = new CommitteeScheduleFilterDatesRule();
                rule.setErrorReporter(new ErrorReporterImpl());
                expectedReturnValue = true;
            }
        };
    }

    @Test
    public void testFalse() {
    
        new  TemplateRuleTest<CommitteeScheduleFilterEvent, CommitteeScheduleFilterDatesRule> (){
            
            @Override
            protected void prerequisite() {
                
                ScheduleData scheduleData = new ScheduleData();   
                scheduleData.setFilterStartDate(new java.sql.Date(new Date().getTime()));
                Date endDate = DateUtils.addDays(new Date(), -1);
                scheduleData.setFilerEndDate(new java.sql.Date(endDate.getTime()));
                
                event = new CommitteeScheduleFilterEvent(Constants.EMPTY_STRING, null, scheduleData, null, ErrorType.HARDERROR);
                rule = new CommitteeScheduleFilterDatesRule();
                rule.setErrorReporter(new ErrorReporterImpl());
                expectedReturnValue = false;
            }
        };
    }
}
