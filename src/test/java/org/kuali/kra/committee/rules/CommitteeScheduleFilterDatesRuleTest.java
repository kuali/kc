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

import java.util.Date;
import java.util.HashMap;

import org.junit.Test;
import org.kuali.core.UserSession;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.committee.rule.event.FilterCommitteeScheduleEvent;
import org.kuali.kra.committee.rule.event.CommitteeScheduleEvent.Event;
import org.kuali.kra.committee.web.struts.form.schedule.ScheduleData;
import org.kuali.kra.infrastructure.Constants;

public class CommitteeScheduleFilterDatesRuleTest extends KraTestBase {
    
    private ScheduleData scheduleData;
    
    private FilterCommitteeScheduleEvent event;
    
    private CommitteeScheduleFilterDatesRule rule;
    
    @SuppressWarnings("unchecked")
    @Override
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("aslusar"));
        GlobalVariables.setErrorMap(new ErrorMap());
        GlobalVariables.setAuditErrorMap(new HashMap());  
    }
    
    /**
     * This method test filter dates for not null.
     * @throws Exception
     */
    @Test
    public void testProcessRule() throws Exception {
        
        prerequisite();        
        event = new FilterCommitteeScheduleEvent(Constants.EMPTY_STRING, null, scheduleData, null, Event.HARDERROR);
        boolean val = executeRule();
        assertTrue(val);
    }
    

    /**
     * This method executes rule.
     * @return
     */
    private boolean executeRule() {
        rule = new CommitteeScheduleFilterDatesRule();
        boolean val = rule.processCommitteeScheduleFilterBusinessRules(event);
        return val;
    }
    
    /**
     * This method is helper method to test cases to set prerequisite.
     */
    private void prerequisite() {
        scheduleData = new ScheduleData();   
        scheduleData.setFilterStartDate(new java.sql.Date(new Date().getTime()));
        scheduleData.setFilerEndDate(new java.sql.Date(new Date().getTime()));
    }
}
