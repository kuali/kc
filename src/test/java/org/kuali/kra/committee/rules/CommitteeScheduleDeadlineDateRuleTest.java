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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.kuali.core.UserSession;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.rule.event.DeadlineCommitteeScheduleEvent;
import org.kuali.kra.committee.rule.event.CommitteeScheduleEvent.Event;
import org.kuali.kra.infrastructure.Constants;

public class CommitteeScheduleDeadlineDateRuleTest extends KraTestBase {
    
    private List<CommitteeSchedule> committeeSchedules;
    
    private DeadlineCommitteeScheduleEvent event;
    
    private CommitteeScheduleDeadlineDateRule rule;
    
    @SuppressWarnings("unchecked")
    @Override
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("aslusar"));
        GlobalVariables.setErrorMap(new ErrorMap());
        GlobalVariables.setAuditErrorMap(new HashMap());  
    }
    
    /**
     * This method is test case of deadline date before schedule meeting date.
     * @throws Exception
     */
    @Test
    public void testProcessRule() throws Exception {
        
        prerequisite();        
        event = new DeadlineCommitteeScheduleEvent(Constants.EMPTY_STRING, null, null, committeeSchedules, Event.HARDERROR);
        boolean val = executeRule();
        assertTrue(val);
    }
    

    /**
     * This method executes rule.
     * @return
     */
    private boolean executeRule() {
        rule = new CommitteeScheduleDeadlineDateRule();
        boolean val = rule.processCommitteeScheduleDeadlineBusinessRules(event);
        return val;
    }
    
    /**
     * This method is helper method to test cases to set prerequisite.
     */
    private void prerequisite() {
        committeeSchedules = new ArrayList<CommitteeSchedule>();
        CommitteeSchedule temp  = new CommitteeSchedule();
        temp.setScheduledDate(new java.sql.Date(new Date().getTime()));        
        Date dt = DateUtils.addDays(new Date(), -1);
        temp.setProtocolSubDeadline(new java.sql.Date(dt.getTime()));
        committeeSchedules.add(temp);
    }
}
