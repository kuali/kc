/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.irb.protocol;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.DocumentNextvalue;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolVersionService;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.util.GlobalVariables;

public class ProtocolVersionServiceTest extends KcUnitTestBase {
    
    private static final String PROTOCOL_NUMBER = "1021000009";
    
    private ProtocolVersionService protocolVersionService;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        protocolVersionService = KraServiceLocator.getService(ProtocolVersionService.class);
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        super.tearDown();
    }
    
    @Test
    public void testVersioning() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        ProtocolDocument newProtocolDocument = protocolVersionService.versionProtocolDocument(protocolDocument);
        assertNotNull(newProtocolDocument);
        assertTrue(!StringUtils.equals(protocolDocument.getDocumentNumber(), newProtocolDocument.getDocumentNumber()));
        assertTrue((protocolDocument.getProtocol().getSequenceNumber() + 1 == newProtocolDocument.getProtocol().getSequenceNumber()));
        assertFalse(protocolDocument.getProtocol().isActive());
        assertTrue(newProtocolDocument.getProtocol().isActive());
        
        List<DocumentNextvalue> nextValues = newProtocolDocument.getDocumentNextvalues();
        for (DocumentNextvalue nextValue : nextValues) {
            assertEquals(nextValue.getDocumentKey(), newProtocolDocument.getDocumentNumber());
        }
    }
    
    @Test 
    public void testGetProtocolVersion() throws Exception {
        Protocol protocol = protocolVersionService.getProtocolVersion(PROTOCOL_NUMBER, 1);
        assertNull(protocol);
        
        ProtocolDocument protocolDocument1 = ProtocolFactory.createProtocolDocument(PROTOCOL_NUMBER);
        ProtocolDocument protocolDocument2 = protocolVersionService.versionProtocolDocument(protocolDocument1);
        ProtocolDocument protocolDocument3 = protocolVersionService.versionProtocolDocument(protocolDocument2);
        
        protocol = protocolVersionService.getProtocolVersion(PROTOCOL_NUMBER, 2);
        assertNotNull(protocol);
        assertEquals(new Integer(2), protocol.getSequenceNumber());
    }
}
