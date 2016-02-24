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
package org.kuali.kra.irb.actions.history;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.test.ProtocolRuleTestBase;
import org.kuali.kra.rules.TemplateRuleTest;

import java.util.Date;

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
