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
package org.kuali.kra.irb.protocol.location;

import org.junit.Test;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;

import static org.junit.Assert.assertEquals;

public class ProtocolLocationServiceTest extends KcIntegrationTestBase {
    protected static final String NEW_ORGANIZATION_VALUE =  "000004";
    
    /**
     * This method is to add a new protocol location
     * @throws Exception
     */
    @Test
    public void testAddProtocolLocation() throws Exception {
        
        ProtocolLocationService service  = new ProtocolLocationServiceImpl();
        
        Protocol protocol = new Protocol(){
            @Override
            public void refreshReferenceObject(String referenceObjectName) {}
            
        };
        
        service.addProtocolLocation(protocol, getNewProtocolLocation() );

        assertEquals(1, protocol.getProtocolLocations().size());
        
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
