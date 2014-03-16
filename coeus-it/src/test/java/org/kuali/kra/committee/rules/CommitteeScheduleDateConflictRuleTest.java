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
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.rule.event.CommitteeScheduleDateConflictEvent;
import org.kuali.kra.committee.rule.event.CommitteeScheduleEventBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.common.committee.impl.web.struts.form.schedule.ScheduleData;
import org.kuali.coeus.sys.framework.validation.SoftError;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.util.*;
import static org.junit.Assert.*;
public class CommitteeScheduleDateConflictRuleTest extends KcIntegrationTestBase {
    
    private ScheduleData scheduleData;
    
    private CommitteeScheduleDateConflictEvent event;
    
    private CommitteeScheduleDateConflictRule rule;
    
    private static final java.sql.Date DATE_1 = new java.sql.Date(new Date().getTime());
    private static final java.sql.Date DATE_2 = new java.sql.Date((DateUtils.addDays(new Date(), 1)).getTime());

    
    @SuppressWarnings("unchecked")
    @Before
    public void setUp() throws Exception {
        GlobalVariables.setMessageMap(new MessageMap());
        KNSGlobalVariables.setAuditErrorMap(new HashMap());  
    }
    
    @Test
    public void testHardErrorTrue() throws Exception {
        
        List<CommitteeSchedule> list = prerequisiteHardError();
        
        event = new CommitteeScheduleDateConflictEvent(Constants.EMPTY_STRING,  null, null, list, CommitteeScheduleEventBase.ErrorType.HARDERROR);
        boolean val = executeRule();
        assertTrue(val);
    }
    
    /**
     * This method test Hard Error section of Date Conflict Rule.
     * @throws Exception
     */
    @Test
    public void testHardErrorFalse() throws Exception {
        
        List<CommitteeSchedule> list = prerequisiteHardError();
        
        CommitteeSchedule temp = new CommitteeSchedule();
        temp.setScheduledDate(DATE_1);
        list.add(temp);
        
        event = new CommitteeScheduleDateConflictEvent(Constants.EMPTY_STRING,  null, null, list, CommitteeScheduleEventBase.ErrorType.HARDERROR);
        boolean val = executeRule();
        assertFalse(val);
    }    
    
    /**
     * This method is helper method to Hard Error test cases to set prerequisite.
     * @return
     */
    public List<CommitteeSchedule> prerequisiteHardError() {
        List<CommitteeSchedule> list = new ArrayList<CommitteeSchedule>();
        CommitteeSchedule temp  = new CommitteeSchedule();
        temp.setScheduledDate(DATE_1);
        list.add(temp);
        
        temp = new CommitteeSchedule();
        temp.setScheduledDate(DATE_2);
        list.add(temp);
        return list;
    }
    
    /**
     * This method test Soft Error section of Date Conflict Rule for no conflict in dates.
     * @throws Exception
     */
    @Test
    public void testSoftErrorTrue() throws Exception {
        
        prerequisiteSoftError();
        event = new CommitteeScheduleDateConflictEvent(Constants.EMPTY_STRING,  null, scheduleData, null, CommitteeScheduleEventBase.ErrorType.SOFTERROR);
        boolean val = executeRule();
        assertTrue(val);
    }

    /**
     * This method test Soft Error section of Date Conflict Rule for conflict in dates.
     * @throws Exception
     */
    @Test
    public void testSoftErrorFalse() throws Exception {
        
        prerequisiteSoftError();
        scheduleData.getDatesInConflict().add(DATE_1);
        event = new CommitteeScheduleDateConflictEvent(Constants.EMPTY_STRING,  null, scheduleData, null, CommitteeScheduleEventBase.ErrorType.SOFTERROR);
        boolean val = executeRule();
        assertTrue(val);
                
        Map<String, Collection<SoftError>> map = rule.getSoftErrors();
        assertNotNull(map.get(CommitteeScheduleDateConflictRule.DATES_IN_CONFLICT_ERROR_KEY));
            
    }
    
    /**
     * This method executes rule.
     * @return
     */
    private boolean executeRule() {
        rule = new CommitteeScheduleDateConflictRule();
        boolean val = rule.processRules(event);
        return val;
    }
    
    /**
     * This method is helper method to Soft Error test cases to set prerequisite.
     */
    private void prerequisiteSoftError() {
        scheduleData = new ScheduleData();   
        scheduleData.setDatesInConflict(new ArrayList<java.sql.Date>());
    }
}
