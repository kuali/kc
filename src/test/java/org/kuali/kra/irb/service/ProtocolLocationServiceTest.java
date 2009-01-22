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

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.irb.bo.ProtocolLocation;
import org.kuali.kra.irb.service.impl.ProtocolLocationServiceImpl;

public class ProtocolLocationServiceTest {
    protected static final String NEW_ORGANIZATION_VALUE =  "000001";
    
    @Test
    public void testAddProtocolLocation() throws Exception {
        
        ProtocolLocationService service  = new ProtocolLocationServiceImpl();
        
        Protocol protocol = new Protocol();
        protocol.setProtocolLocations(new ArrayList<ProtocolLocation>());
        
        ProtocolLocation protocolLocation = new ProtocolLocation();
        protocolLocation.setOrganizationId(NEW_ORGANIZATION_VALUE);
        
        service.addProtocolLocation(protocol, protocolLocation );

        assertEquals(2, protocol.getProtocolLocations().size());
        
    }

    @Test
    public void testDeleteProtocolLocation() throws Exception {
        ProtocolLocationService service  = new ProtocolLocationServiceImpl();
        Protocol protocol = new Protocol();
        service.deleteProtocolLocation(protocol, 1);
        assertEquals(1, protocol.getProtocolLocations().size());
        
    }

}
