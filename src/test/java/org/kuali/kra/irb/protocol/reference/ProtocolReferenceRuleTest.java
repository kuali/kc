/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.irb.protocol.reference;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.test.ProtocolRuleTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.util.AutoPopulatingList;

public class ProtocolReferenceRuleTest extends ProtocolRuleTestBase {

    private static final String NEW_PROTOCOLREFERENCE= "newProtocolReferenceBean";
    private ProtocolReferenceRule rule = null;
    private List<ProtocolReferenceType> protocolReferenceList;
    private BusinessObjectService bos;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new ProtocolReferenceRule();
        bos = KraServiceLocator.getService(BusinessObjectService.class);
        protocolReferenceList = (List)bos.findAll(ProtocolReferenceType.class);
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        bos = null;
        protocolReferenceList = null;
        super.tearDown();
    }

    /** 
     * 
     * This method approval type and special review codes are OK before do the real rule test
     */
    @Test
    public void checkCodes() {
        assertNotNull(protocolReferenceList);
        assertTrue(protocolReferenceList.size()>1);
    }


    /**
     * Test a good case.
     * @throws Exception
     */
    @Test
    public void testOK() throws Exception {

        ProtocolDocument document = getNewProtocolDocument();
        
        ProtocolReferenceBean newProtocolReference = new ProtocolReferenceBean();
        //newProtocolReference.setProtocolReferenceType(protocolReferenceList.get(0));
        newProtocolReference.setProtocolReferenceTypeCode(protocolReferenceList.get(0).getProtocolReferenceTypeCode());
        newProtocolReference.setReferenceKey("Test Me");
        
        AddProtocolReferenceEvent event = new AddProtocolReferenceEvent(Constants.EMPTY_STRING,document,newProtocolReference);
        assertTrue(rule.processAddProtocolReferenceBusinessRules(event));

    }
    
    @Test
    public void testNotValid() throws Exception {
        
        ProtocolDocument document = getNewProtocolDocument();        
        ProtocolReferenceBean newProtocolReferenceBean = new ProtocolReferenceBean();
        
        AddProtocolReferenceEvent event = new AddProtocolReferenceEvent(Constants.EMPTY_STRING,document,newProtocolReferenceBean);
        assertFalse(rule.processAddProtocolReferenceBusinessRules(event));
        
        AutoPopulatingList errors = GlobalVariables.getMessageMap().getMessages(NEW_PROTOCOLREFERENCE + ".protocolReferenceTypeCode");   
        assertEquals(1, errors.size());
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), KeyConstants.ERROR_PROTOCOLREFERENCE_PROTOCOLREFERENCETYPECODE);
        
        
        errors = GlobalVariables.getMessageMap().getMessages(NEW_PROTOCOLREFERENCE + ".referenceKey");
        assertTrue(errors.size() == 1);
        message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), KeyConstants.ERROR_PROTOCOLREFERENCE_PROTOCOLREFERENCEKEY);
        
    }
}
