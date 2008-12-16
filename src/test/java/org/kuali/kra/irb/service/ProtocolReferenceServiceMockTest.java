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

import java.util.HashMap;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.MockObjectTestCase;
import org.jmock.Mockery;
import org.junit.Test;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.irb.bo.ProtocolReference;
import org.kuali.kra.irb.bo.ProtocolReferenceType;
import org.kuali.kra.irb.service.impl.ProtocolReferenceServiceImpl;

public class ProtocolReferenceServiceMockTest extends MockObjectTestCase {
    
    Mockery context = new Mockery();
    
    @SuppressWarnings("unchecked")
    @Test
    public void testAddProtocolReference()
    {
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        final ProtocolReferenceServiceImpl protocolReferenceService = new ProtocolReferenceServiceImpl();
        protocolReferenceService.setBusinessObjectService(businessObjectService);

        context.checking(new Expectations() {{
            Map keyMap = new HashMap();
            keyMap.put("protocolReferenceTypeCode", new Integer(1));
            oneOf (businessObjectService).findByPrimaryKey(ProtocolReferenceType.class, keyMap);
            will(returnValue(new ProtocolReferenceType()));
        }});
        
        ProtocolReference protocolReference = new ProtocolReference();
        protocolReference.setProtocolReferenceTypeCode(new Integer(1));
        protocolReferenceService.addProtocolReference(new Protocol(), protocolReference);
        
        context.assertIsSatisfied();
    }
}
