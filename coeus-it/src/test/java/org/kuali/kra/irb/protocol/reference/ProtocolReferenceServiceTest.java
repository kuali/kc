/*
 * Copyright 2005-2013 The Kuali Foundation
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
