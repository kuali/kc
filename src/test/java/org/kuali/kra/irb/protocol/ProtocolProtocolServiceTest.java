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
package org.kuali.kra.irb.protocol;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.protocol.ProtocolProtocolService;
import org.kuali.kra.irb.web.ProtocolWebTestBase;
import org.kuali.rice.kns.service.BusinessObjectService;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * 
 * This class is to test service, but extends protocolwebtestbase.
 * The methods to test need to have protocol document properly set.  web test is probably the 
 * easiest way to create protocol document.
 * tried to use 'DocumentService' to create/save document.  Lots of trouble to deal with validation/rules check.
 */
public class ProtocolProtocolServiceTest extends ProtocolWebTestBase{
    
    private HtmlPage protocolPage;
    private ProtocolProtocolService protocolProtocolService;

    /**
     * The set up method calls the parent super method and gets the 
     * protocol page after saving initial required fields.
     * @see org.kuali.kra.irb.web.ProtocolWebTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        protocolPage = getProtocolSavedRequiredFieldsPage();
        protocolProtocolService = KraServiceLocator.getService(ProtocolProtocolService.class);
    }

    /**
     * This method calls parent tear down method and than sets protocolPage to null
     * @see org.kuali.kra.irb.web.ProtocolWebTestBase#tearDown()
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        protocolPage = null;
        protocolProtocolService=null;
    }
    
    /**
     * 
     * This method tests load protocol based on document#/protocolnumber for edit.
     * @throws Exception
     */
    @Test
    public void testLoadProtocolForEdit() throws Exception{
        assertContains(protocolPage,SAVE_SUCCESS_MESSAGE);        
        String protocolNumber = getFieldValue(protocolPage, "document.protocolList[0].protocolNumber");
        ProtocolDocument protocolDoc = new ProtocolDocument();
        protocolProtocolService.loadProtocolForEdit(protocolDoc, protocolNumber); 
        assertNotNull(protocolDoc.getProtocol());
        assertEquals(protocolNumber, protocolDoc.getProtocol().getProtocolNumber());
        Map<String, String> qMap = new HashMap<String, String>();
        qMap.put("documentNumber", getDocNbr(protocolPage));
        ProtocolDocument protocolDoc1 = (ProtocolDocument) KraServiceLocator.getService(BusinessObjectService.class)
                .findByPrimaryKey(ProtocolDocument.class, qMap);

        assertEquals(protocolDoc.getProtocol().getProtocolNumber(), protocolDoc1.getProtocol().getProtocolNumber());
        assertEquals(protocolDoc.getProtocol().getProtocolId(), protocolDoc1.getProtocol().getProtocolId());
        assertEquals(protocolDoc.getProtocol().getSequenceNumber(), protocolDoc1.getProtocol().getSequenceNumber());
        assertEquals(protocolDoc.getProtocol().getProtocolType(), protocolDoc1.getProtocol().getProtocolType());
        assertEquals(protocolDoc.getProtocol().getProtocolStatus(), protocolDoc1.getProtocol().getProtocolStatus());
    }
    

}
