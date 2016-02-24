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
import org.kuali.coeus.sys.impl.validation.ErrorReporterImpl;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.rule.event.CommitteeScheduleDeadlineEvent;
import org.kuali.kra.committee.rule.event.CommitteeScheduleEventBase.ErrorType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.rules.TemplateRuleTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommitteeScheduleDeadlineRuleTest {
    
    @Test
    public void testTrue() {
    
        new  TemplateRuleTest<CommitteeScheduleDeadlineEvent, CommitteeScheduleDeadlineDateRule> (){
            
            @Override
            protected void prerequisite() {
                
                List<CommitteeSchedule> committeeSchedules = new ArrayList<CommitteeSchedule>();
                CommitteeSchedule temp  = new CommitteeSchedule();
                temp.setScheduledDate(new java.sql.Date(new Date().getTime()));        
                Date dt = DateUtils.addDays(new Date(), -1);
                temp.setProtocolSubDeadline(new java.sql.Date(dt.getTime()));
                committeeSchedules.add(temp);
                
                event = new CommitteeScheduleDeadlineEvent(Constants.EMPTY_STRING, null, null, committeeSchedules, ErrorType.HARDERROR);
                rule = new CommitteeScheduleDeadlineDateRule();
                rule.setErrorReporter(new ErrorReporterImpl());
                expectedReturnValue = true;
            }
        };
    }
    
    @Test
    public void testFalse() {
        new  TemplateRuleTest<CommitteeScheduleDeadlineEvent, CommitteeScheduleDeadlineDateRule> (){
            @Override
            protected void prerequisite() {
                
                List<CommitteeSchedule> committeeSchedules = new ArrayList<CommitteeSchedule>();
                CommitteeSchedule temp  = new CommitteeSchedule();
                temp.setScheduledDate(new java.sql.Date(new Date().getTime()));        
                Date dt = DateUtils.addDays(new Date(), 1);
                temp.setProtocolSubDeadline(new java.sql.Date(dt.getTime()));
                committeeSchedules.add(temp);
                
                event = new CommitteeScheduleDeadlineEvent(Constants.EMPTY_STRING, null, null, committeeSchedules, ErrorType.HARDERROR);
                rule = new CommitteeScheduleDeadlineDateRule();
                rule.setErrorReporter(new ErrorReporterImpl());
                expectedReturnValue = false;
            }
        };
    }
}
