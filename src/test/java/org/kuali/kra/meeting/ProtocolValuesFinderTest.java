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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.BusinessObjectService;

@RunWith(JMock.class)
public class ProtocolValuesFinderTest {
    /*
     * TODO : this is probably not a good practice to increase the visibility of 'getBusinessObjectService'
     * from private to protected; just for simplifying test.
     * There is a need to set up protocolsubmissions, and need to run web test to create these.
     * It will overkill to add webtest and then test this values finder.
     * Is it  ok to add this test as part of MeetingWebTest, then it will make this much easier.
     */
    
    private Mockery context = new JUnit4Mockery();

    @Test
    public void testGetKeyValues() throws Exception {
        
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        ProtocolValuesFinder protocolValuesFinder = new ProtocolValuesFinder() { 
            @Override 
            protected BusinessObjectService getBusinessObjectService() {return businessObjectService;} 
                         
         }; 
         final List<ProtocolSubmission> protocolSubmissions = new ArrayList<ProtocolSubmission>();
         ProtocolSubmission protocolSubmission = new ProtocolSubmission();
         protocolSubmission.setScheduleIdFk(1L);
         protocolSubmission.setProtocolId(1L);
         protocolSubmission.setProtocolNumber("1001");
         protocolSubmissions.add(protocolSubmission);
         protocolSubmission = new ProtocolSubmission();
         protocolSubmission.setScheduleIdFk(1L);
         protocolSubmission.setProtocolId(2L);
         protocolSubmission.setProtocolNumber("1002");
         protocolSubmissions.add(protocolSubmission);
         context.checking(new Expectations() {{
             Map fieldValues = new HashMap();
             fieldValues.put("scheduleIdFk", "1");
             one(businessObjectService).findMatching(ProtocolSubmission.class, fieldValues);
             will(returnValue(protocolSubmissions));
             
         }});
         protocolValuesFinder.setScheduleId("1");
         List<KeyValue> keyValues = protocolValuesFinder.getKeyValues();
         Assert.assertEquals(keyValues.size(), 3);
         Assert.assertEquals(keyValues.get(1).getKey().toString(), "1");
         Assert.assertEquals(keyValues.get(1).getValue(), "1001");
         Assert.assertEquals(keyValues.get(2).getKey().toString(), "2");
         Assert.assertEquals(keyValues.get(2).getValue(), "1002");

    }

}
