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
package org.kuali.kra.irb.actions.submit;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.irb.Protocol;

public class ProtocolAvailableActionServiceTest extends ProtocolActionServiceTestBase{
    
   
    private Mockery context;
    
    private Protocol protocol;
    
    private ProtocolActionServiceImpl protocolActionService;
    
    @Before
    public void setUp() {
        context  = new JUnit4Mockery();
        protocol = getProtocol(context);
        protocolActionService = new ProtocolActionServiceImpl();

        ProtocolSubmission protocolSubmission = new ProtocolSubmission();        
        protocol.setProtocolSubmission(protocolSubmission);
        
        protocol.setProtocolNumber("001Z");        
    }   
    
    @Test
    public void testActionTypeCode200() {                
        protocol.getProtocolSubmission().setSubmissionTypeCode("100");        
        List<String> list = protocolActionService.availableActionTypeCodes(protocol);
        assertEquals("200", list.get(0));
    }

}
