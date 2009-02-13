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

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.irb.bo.ProtocolReference;
import org.kuali.kra.irb.service.impl.ProtocolReferenceServiceImpl;

public class ProtocolReferenceServiceTest {
    
    @Test
    public void testDeleteProtocolReference() throws Exception {
        
        ProtocolReferenceService service  = new ProtocolReferenceServiceImpl();
        
        Protocol protocol = new Protocol();
        protocol.setProtocolReferences(new ArrayList<ProtocolReference>());
        
        ProtocolReference bo1 = new ProtocolReference();
        bo1.setProtocolReferenceId(1L);
        
        ProtocolReference bo2 = new ProtocolReference();
        bo2.setProtocolReferenceId(2L);
        
        protocol.getProtocolReferences().add(bo1);
        protocol.getProtocolReferences().add(bo2);
        
        
    }
}
