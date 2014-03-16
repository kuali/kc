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
import org.kuali.coeus.common.committee.impl.web.struts.form.schedule.ScheduleData;
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
                expectedReturnValue = false;
            }
        };
    }
}
