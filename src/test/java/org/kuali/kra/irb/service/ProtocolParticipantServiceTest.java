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
package org.kuali.kra.irb.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.MockObjectTestCase;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.util.TypedArrayList;
import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.irb.bo.ProtocolParticipant;
import org.kuali.kra.irb.bo.ParticipantType;
import org.kuali.kra.irb.service.impl.ProtocolParticipantServiceImpl;

public class ProtocolParticipantServiceTest extends MockObjectTestCase {

    Mockery context = new Mockery();
    
    private Protocol protocol;
    
    @Before
    public void setUp() {
        
        ProtocolParticipant protocolParticipant1 = new ProtocolParticipant();
        protocolParticipant1.setParticipantTypeCode("1");
        protocolParticipant1.setParticipantCount(15);
        
        ProtocolParticipant protocolParticipant2 = new ProtocolParticipant();
        protocolParticipant2.setParticipantTypeCode("2");
        protocolParticipant2.setParticipantCount(25);
        
        ProtocolParticipant protocolParticipant3 = new ProtocolParticipant();
        protocolParticipant3.setParticipantTypeCode("3");
        protocolParticipant3.setParticipantCount(35);

        protocol = new Protocol();
        protocol.setProtocolParticipants(new ArrayList<ProtocolParticipant>());
        protocol.getProtocolParticipants().add(protocolParticipant1);
        protocol.getProtocolParticipants().add(protocolParticipant2);
        protocol.getProtocolParticipants().add(protocolParticipant3);
    }

  @SuppressWarnings("unchecked")
  @Test
  public void testAddProtocolParticipant() {
      final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
      final ProtocolParticipantServiceImpl protocolParticipantService = new ProtocolParticipantServiceImpl();
      protocolParticipantService.setBusinessObjectService(businessObjectService);
      
      context.checking(new Expectations() {{
          Map keyMap = new HashMap();
          keyMap.put("participantTypeCode", "1");
          oneOf (businessObjectService).findByPrimaryKey(ParticipantType.class, keyMap);
          will(returnValue(new ParticipantType()));
      }});
      
      ProtocolParticipant protocolParticipant = new ProtocolParticipant();
      protocolParticipant.setParticipantTypeCode("1");
      protocolParticipantService.addProtocolParticipant(new Protocol(), protocolParticipant);
      
      context.assertIsSatisfied();
  }

    @Test
    public void testDeleteProtocolParticipant() {
        final ProtocolParticipantServiceImpl protocolParticipantService = new ProtocolParticipantServiceImpl();
        
        protocolParticipantService.deleteProtocolParticipant(protocol, 1);

        assert(protocol.getProtocolParticipants().size() == 2);
        assert(protocol.getProtocolParticipant(0).getParticipantTypeCode() == "1");
        assert(protocol.getProtocolParticipant(1).getParticipantTypeCode() == "3");
    }
   
}
