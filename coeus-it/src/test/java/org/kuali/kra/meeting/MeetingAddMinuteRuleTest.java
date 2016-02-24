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
package org.kuali.kra.meeting;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.coeus.common.committee.impl.meeting.MeetingEventBase.ErrorType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.rules.TemplateRuleTest;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.HashMap;
import java.util.Map;

@RunWith(JMock.class)
public class MeetingAddMinuteRuleTest extends KcIntegrationTestBase {
    private Mockery context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};

    @Test
    public void testMissingProtocol() {    
        new  TemplateRuleTest<MeetingAddMinuteEvent, MeetingAddMinuteRule> (){            
            @Override
            protected void prerequisite() {   
                CommitteeScheduleMinute committeeScheduleMinute = new CommitteeScheduleMinute();
                committeeScheduleMinute.setMinuteEntryTypeCode("3");
                committeeScheduleMinute.setMinuteEntry("Text");
                
                MeetingHelper meetingHelper = new MeetingHelper(new MeetingForm());
                meetingHelper.setNewCommitteeScheduleMinute(committeeScheduleMinute);
                event = new MeetingAddMinuteEvent(Constants.EMPTY_STRING, null, meetingHelper, ErrorType.HARDERROR);
                rule = new MeetingAddMinuteRule();
                expectedReturnValue = false;
            }
       };
 
    
    }

    @Test
    public void testOK() {    
        new  TemplateRuleTest<MeetingAddMinuteEvent, MeetingAddMinuteRule> (){            
            @Override
            protected void prerequisite() {   
                CommitteeScheduleMinute committeeScheduleMinute = new CommitteeScheduleMinute();
                committeeScheduleMinute.setMinuteEntryTypeCode("3");
                committeeScheduleMinute.setMinuteEntry("Text");
                committeeScheduleMinute.setProtocolIdFk(1L);
                
                MeetingHelper meetingHelper = new MeetingHelper(new MeetingForm());
                meetingHelper.setNewCommitteeScheduleMinute(committeeScheduleMinute);
                event = new MeetingAddMinuteEvent(Constants.EMPTY_STRING, null, meetingHelper, ErrorType.HARDERROR);
                rule = new MeetingAddMinuteRule();
                expectedReturnValue = true;
            }
       };
 
    
    }

    @Test
    public void testInvalidReviewCode() {    
        new  TemplateRuleTest<MeetingAddMinuteEvent, MeetingAddMinuteRule> (){            
            @Override
            protected void prerequisite() {   
                CommitteeScheduleMinute committeeScheduleMinute = new CommitteeScheduleMinute();
                committeeScheduleMinute.setMinuteEntryTypeCode("3");
                committeeScheduleMinute.setMinuteEntry("Text");
                committeeScheduleMinute.setProtocolIdFk(1L);
                ProtocolContingency protocolContingency= new ProtocolContingency();
                protocolContingency.setProtocolContingencyCode("111");
                committeeScheduleMinute.setProtocolContingency(protocolContingency);
                committeeScheduleMinute.setProtocolContingencyCode("111");
                final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
                context.checking(new Expectations() {{
                    Map<String, String> queryMap = new HashMap<String, String>();
                    queryMap.put("protocolContingencyCode", "111");
                    one(businessObjectService).findByPrimaryKey(ProtocolContingency.class, queryMap);
                    will(returnValue(null));
                    
                }});
                
                MeetingHelper meetingHelper = new MeetingHelper(new MeetingForm());
                meetingHelper.setNewCommitteeScheduleMinute(committeeScheduleMinute);
                event = new MeetingAddMinuteEvent(Constants.EMPTY_STRING, null, meetingHelper, ErrorType.HARDERROR);
                rule = new MeetingAddMinuteRule();
                rule.setBusinessObjectService(businessObjectService);
                expectedReturnValue = false;
            }
       };
 
    
    }

}
