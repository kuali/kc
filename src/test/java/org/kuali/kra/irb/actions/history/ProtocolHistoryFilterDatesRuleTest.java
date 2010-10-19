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
package org.kuali.kra.irb.actions.history;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.test.ProtocolRuleTestBase;
import org.kuali.kra.rules.TemplateRuleTest;

public class ProtocolHistoryFilterDatesRuleTest extends ProtocolRuleTestBase { 
    
    @Test
    public void testOK() {
        new TemplateRuleTest<ProtocolHistoryFilterDatesEvent, ProtocolHistoryFilterDatesRule> () {
            
            @Override
            protected void prerequisite() {
                Date startDate = new Date(System.currentTimeMillis());
                Date endDate = new Date(System.currentTimeMillis());
                
                event = new ProtocolHistoryFilterDatesEvent(null, startDate, endDate);
                rule = new ProtocolHistoryFilterDatesRule();
                expectedReturnValue = true;
            }
        };
    }

    @Test
    public void testStartDateNull() {
        new TemplateRuleTest<ProtocolHistoryFilterDatesEvent, ProtocolHistoryFilterDatesRule> (){
            
            @Override
            protected void prerequisite() {
                Date startDate = null;
                Date endDate = new Date(System.currentTimeMillis());
                
                event = new ProtocolHistoryFilterDatesEvent(null, startDate, endDate);
                rule = new ProtocolHistoryFilterDatesRule();
                expectedReturnValue = false;
            }
            
            @Override
            public void checkRuleAssertions() {
                assertError(Constants.PROTOCOL_HISTORY_DATE_RANGE_FILTER_START_DATE_KEY, 
                        KeyConstants.ERROR_REQUIRED);
            }
        };
    }
    
    @Test
    public void testEndDateNull() {
        new TemplateRuleTest<ProtocolHistoryFilterDatesEvent, ProtocolHistoryFilterDatesRule> (){
            
            @Override
            protected void prerequisite() {
                Date startDate = new Date(System.currentTimeMillis());
                Date endDate = null;
                
                event = new ProtocolHistoryFilterDatesEvent(null, startDate, endDate);
                rule = new ProtocolHistoryFilterDatesRule();
                expectedReturnValue = false;
            }
            
            @Override
            public void checkRuleAssertions() {
                assertError(Constants.PROTOCOL_HISTORY_DATE_RANGE_FILTER_END_DATE_KEY, 
                        KeyConstants.ERROR_REQUIRED);
            }
        };
    }
    
    @Test
    public void testEndDateBeforeStartDate() {
        new TemplateRuleTest<ProtocolHistoryFilterDatesEvent, ProtocolHistoryFilterDatesRule> (){
            
            @Override
            protected void prerequisite() {
                Date startDate = new Date(System.currentTimeMillis());
                Date endDate = DateUtils.addDays(startDate, -1);
                
                event = new ProtocolHistoryFilterDatesEvent(null, startDate, endDate);
                rule = new ProtocolHistoryFilterDatesRule();
                expectedReturnValue = false;
            }
            
            @Override
            public void checkRuleAssertions() {
                assertError(Constants.PROTOCOL_HISTORY_DATE_RANGE_FILTER_START_DATE_KEY, 
                        KeyConstants.ERROR_START_DATE_AFTER_END_DATE);
            }
        };
    }
    
}