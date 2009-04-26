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
package org.kuali.kra.irb.web;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.rice.kns.service.BusinessObjectService;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ProtocolStatusAndDatesWebTest extends ProtocolWebTestBase{
    
    HtmlPage protocolPage;    

    /**
     * The set up method calls the parent super method and gets the 
     * protocol page after saving initial required fields.
     * @see org.kuali.kra.irb.web.ProtocolWebTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        protocolPage = getProtocolSavedRequiredFieldsPage();
    }

    /**
     * This method calls parent tear down method and than sets protocolPage to null
     * @see org.kuali.kra.irb.web.ProtocolWebTestBase#tearDown()
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        protocolPage = null;
    }
    
    /**
     * 
     * This method tests protocol Status/Dates panel with initialized values.
     * @throws Exception
     */
    @Test
    public void testDatesAndStatusPanelInit() throws Exception{
        assertContains(protocolPage,SAVE_SUCCESS_MESSAGE);        
        String protocolNumber = getFieldValue(protocolPage, "document.protocolList[0].protocolNumber");
        assertContains(protocolPage,"Protocol #: "+ protocolNumber +" Protocol Status: Pending/In Progress ");        
        assertContains(protocolPage,"Initial Approval Date: Last Approval Date: ");        
        assertContains(protocolPage,"Submission Date: Generated on Submission Expiration Date: ");   
        assertContains(protocolPage,"Risk Levels Risk Level Date Assigned Date Updated Status Comments Additional Information"); 
        Map<String, String> qMap = new HashMap<String, String>();
        qMap.put("documentNumber", getDocNbr(protocolPage));
        ProtocolDocument protocolDoc = (ProtocolDocument)KraServiceLocator.getService(BusinessObjectService.class).findByPrimaryKey(ProtocolDocument.class, qMap);
        assertNotNull(protocolDoc);
        assertEquals(protocolNumber, protocolDoc.getProtocol().getProtocolNumber());
        
    }
    

}
