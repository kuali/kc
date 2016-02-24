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
package org.kuali.kra.irb.protocol.reference;

import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;

import static org.junit.Assert.assertEquals;

public class ProtocolReferenceServiceTest extends KcIntegrationTestBase {
    
    Protocol protocol;
    ProtocolReference protocolReference = new ProtocolReference();
    
    @Before
    public void setUp() throws Exception {
        protocol = new Protocol(){
            @Override
            public void refreshReferenceObject(String referenceObjectName) {}

           
        };
        protocolReference = new ProtocolReference();
           
    }
    
    @Test
    public void testAddProtocolReference() throws Exception {
        
        ProtocolReferenceServiceImpl service = new ProtocolReferenceServiceImpl();
        
        service.addProtocolReference(protocol, protocolReference);
        
        assertEquals(1, protocol.getProtocolReferences().size());
        
    }
}
