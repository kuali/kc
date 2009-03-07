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
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.kuali.core.UserSession;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.rule.event.AddCommitteeScheduleDateConflictEvent;
import org.kuali.kra.committee.rule.event.CommitteeScheduleEvent;
import org.kuali.kra.committee.web.struts.form.schedule.ScheduleData;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.rules.SoftError;

public class CommitteeScheduleDateConflictRuleTest extends KraTestBase {
    
    private ScheduleData scheduleData;
    
    private AddCommitteeScheduleDateConflictEvent event;
    
    private CommitteeScheduleDateConflictRule rule;
    
    @SuppressWarnings("unchecked")
    @Override
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("aslusar"));
        GlobalVariables.setErrorMap(new ErrorMap());
        GlobalVariables.setAuditErrorMap(new HashMap());  
    }
    
    @Test
    public void testHardErrorTrue() throws Exception {
        
        List<CommitteeSchedule> list = prerequisiteHardError();
        
        event = new AddCommitteeScheduleDateConflictEvent(Constants.EMPTY_STRING,  null, null, list, CommitteeScheduleEvent.event.HARDERROR);
        testAssertTrue();
    }
    
    @Test
    public void testHardErrorFalse() throws Exception {
        
        List<CommitteeSchedule> list = prerequisiteHardError();
        
        CommitteeSchedule temp = new CommitteeSchedule();
        temp.setScheduledDate(new java.sql.Date(new Date().getTime()));
        list.add(temp);
        
        event = new AddCommitteeScheduleDateConflictEvent(Constants.EMPTY_STRING,  null, null, list, CommitteeScheduleEvent.event.HARDERROR);
        testAssertFalse();
    }    
    
    public List<CommitteeSchedule> prerequisiteHardError() {
        List<CommitteeSchedule> list = new ArrayList<CommitteeSchedule>();
        CommitteeSchedule temp  = new CommitteeSchedule();
        temp.setScheduledDate(new java.sql.Date(new Date().getTime()));
        list.add(temp);
        
        Date dt = DateUtils.addDays(new Date(), 1);
        temp = new CommitteeSchedule();
        temp.setScheduledDate(new java.sql.Date(dt.getTime()));
        list.add(temp);
        return list;
    }
    
    @Test
    public void testSoftErrorTrue() throws Exception {
        
        prerequisite();
        event = new AddCommitteeScheduleDateConflictEvent(Constants.EMPTY_STRING,  null, scheduleData, null, CommitteeScheduleEvent.event.SOFTERROR);
        testAssertTrue();
    }

    @Test
    public void testSoftErrorFalse() throws Exception {
        
        prerequisite();
        scheduleData.getDatesInConflict().add(new java.sql.Date(new Date().getTime()));
        event = new AddCommitteeScheduleDateConflictEvent(Constants.EMPTY_STRING,  null, scheduleData, null, CommitteeScheduleEvent.event.SOFTERROR);
        testAssertTrue();
                
        Map<String, Collection<SoftError>> map = rule.getSoftErrors();
        assertNotNull(map.get(CommitteeScheduleDateConflictRule.DATES_IN_CONFLICT_ERROR_KEY));
            
    }
    
    private void testAssertTrue() {
        rule = new CommitteeScheduleDateConflictRule();
        boolean val = rule.processAddCommitteeScheduleRuleBusinessRules(event);
        assertTrue(val);
    }
    
    private void testAssertFalse() {
        rule = new CommitteeScheduleDateConflictRule();
        boolean val = rule.processAddCommitteeScheduleRuleBusinessRules(event);
        assertFalse(val);
    }
    
    private void prerequisite() {
        scheduleData = new ScheduleData();   
        scheduleData.setDatesInConflict(new ArrayList<java.sql.Date>());
    }
}
