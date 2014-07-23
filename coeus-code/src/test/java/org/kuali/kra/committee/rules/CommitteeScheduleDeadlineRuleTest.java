/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
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
                rule.setErrorReporter(new ErrorReporter());
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
                rule.setErrorReporter(new ErrorReporter());
                expectedReturnValue = false;
            }
        };
    }
}
