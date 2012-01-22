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
package org.kuali.kra.meeting;

import java.util.HashMap;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.meeting.MeetingEventBase.ErrorType;
import org.kuali.kra.rules.TemplateRuleTest;
import org.kuali.rice.krad.service.BusinessObjectService;

@RunWith(JMock.class)
public class MeetingAddMinuteRuleTest {
    private Mockery context = new JUnit4Mockery();

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
