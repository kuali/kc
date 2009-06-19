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
package org.kuali.kra.irb.protocol.location;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.protocol.location.ProtocolLocation;
import org.kuali.kra.irb.protocol.location.ProtocolLocationService;
import org.kuali.kra.irb.protocol.location.ProtocolLocationServiceImpl;

public class ProtocolLocationServiceTest {
    protected static final String NEW_ORGANIZATION_VALUE =  "000004";
    
    /**
     * This method is to add a new protocol location
     * @throws Exception
     */
    @Test
    public void testAddProtocolLocation() throws Exception {
        
        ProtocolLocationService service  = new ProtocolLocationServiceImpl();
        
        Protocol protocol = new Protocol();
        
        service.addProtocolLocation(protocol, getNewProtocolLocation() );

        assertEquals(2, protocol.getProtocolLocations().size());
        
    }

    /**
     * This method is to get a new protocol location data
     * @return ProtocolLocation
     */
    private ProtocolLocation getNewProtocolLocation() {
        ProtocolLocation protocolLocation = new ProtocolLocation();
        protocolLocation.setOrganizationId(NEW_ORGANIZATION_VALUE);
        return protocolLocation;
        
    }

}
