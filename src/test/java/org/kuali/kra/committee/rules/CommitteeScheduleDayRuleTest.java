/*
 * Copyright 2005-2010 The Kuali Foundation
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

import org.junit.Test;
import org.kuali.kra.committee.rule.event.CommitteeScheduleDayEvent;
import org.kuali.kra.committee.rule.event.CommitteeScheduleEventBase.ErrorType;
import org.kuali.kra.committee.web.struts.form.schedule.*;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.rules.TemplateRuleTest;

public class CommitteeScheduleDayRuleTest {
    
    @Test
    public void testTrueMonthly() {    
        new  TemplateRuleTest<CommitteeScheduleDayEvent, CommitteeScheduleDayRule> (){            
            @Override
            protected void prerequisite() {            
                MonthlyScheduleData scheduleData = new MonthlyScheduleData();
                scheduleData.getMonthlySchedule().setDay(31);
                scheduleData.setRecurrenceType(StyleKey.MONTHLY.toString());
                scheduleData.getMonthlySchedule().setMonthOption(MonthlyScheduleDetails.optionValues.XDAYANDXMONTH.toString());               
                event = new CommitteeScheduleDayEvent(Constants.EMPTY_STRING, null, scheduleData, null, ErrorType.HARDERROR);
                rule = new CommitteeScheduleDayRule();
                expectedReturnValue = true;
            }
        };
    }
    
    @Test
    public void testMonthly() {
        new  TemplateRuleTest<CommitteeScheduleDayEvent, CommitteeScheduleDayRule> (){ 
            @Override
            protected void prerequisite() {
                MonthlyScheduleData scheduleData = new MonthlyScheduleData();
                scheduleData.getMonthlySchedule().setDay(32);
                scheduleData.setRecurrenceType(StyleKey.MONTHLY.toString());
                scheduleData.getMonthlySchedule().setMonthOption(MonthlyScheduleDetails.optionValues.XDAYANDXMONTH.toString());
                event = new CommitteeScheduleDayEvent(Constants.EMPTY_STRING, null, scheduleData, null, ErrorType.HARDERROR);
                rule = new CommitteeScheduleDayRule();
                expectedReturnValue = false;
            }
        };
    }
    
    @Test
    public void testTrueYearly() {    
        new  TemplateRuleTest<CommitteeScheduleDayEvent, CommitteeScheduleDayRule> (){            
            @Override
            protected void prerequisite() {            
                YearlyScheduleData scheduleData = new YearlyScheduleData();
                scheduleData.getYearlySchedule().setDay(31);
                scheduleData.setRecurrenceType(StyleKey.YEARLY.toString());
                scheduleData.getYearlySchedule().setYearOption(YearlyScheduleDetails.yearOptionValues.XDAY.toString());               
                event = new CommitteeScheduleDayEvent(Constants.EMPTY_STRING, null, scheduleData, null, ErrorType.HARDERROR);
                rule = new CommitteeScheduleDayRule();
                expectedReturnValue = true;
            }
        };
    }
      
    @Test
    public void testFalseYearly() {    
        new  TemplateRuleTest<CommitteeScheduleDayEvent, CommitteeScheduleDayRule> (){            
            @Override
            protected void prerequisite() {            
                YearlyScheduleData scheduleData = new YearlyScheduleData();
                scheduleData.getYearlySchedule().setDay(33);
                scheduleData.setRecurrenceType(StyleKey.YEARLY.toString());
                scheduleData.getYearlySchedule().setYearOption(YearlyScheduleDetails.yearOptionValues.XDAY.toString());               
                event = new CommitteeScheduleDayEvent(Constants.EMPTY_STRING, null, scheduleData, null, ErrorType.HARDERROR);
                rule = new CommitteeScheduleDayRule();
                expectedReturnValue = false;
            }
        };
    }    
}
