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
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(JMock.class)
public class ProtocolValuesFinderTest {
    /*
     * TODO : this is probably not a good practice to increase the visibility of 'getBusinessObjectService'
     * from private to protected; just for simplifying test.
     * There is a need to set up protocolsubmissions, and need to run web test to create these.
     * It will overkill to add webtest and then test this values finder.
     * Is it  ok to add this test as part of MeetingWebTest, then it will make this much easier.
     */
    
    private Mockery context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};

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
