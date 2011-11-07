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
package org.kuali.kra.irb.protocol.location;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.test.ProtocolRuleTestBase;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.ErrorMessage;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.TypedArrayList;

public class ProtocolLocationRuleTest extends ProtocolRuleTestBase {

    private static final String NEW_PROTOCOL_LOCATION = "protocolHelper.newProtocolLocation";
    private ProtocolLocationRule rule = null;
    private List<ProtocolOrganizationType> protocolOrganizationTypeList;
    private BusinessObjectService bos;
    protected static final String NEW_ORGANIZATION_VALUE =  "000002";
    protected static final String OLD_ORGANIZATION_VALUE =  "000001";

    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new ProtocolLocationRule();
        bos = KraServiceLocator.getService(BusinessObjectService.class);
        protocolOrganizationTypeList = (List)bos.findAll(ProtocolOrganizationType.class);
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        bos = null;
        protocolOrganizationTypeList = null;
        super.tearDown();
    }

    /** 
     * This method is to check organization type codes in the list.
     * 
     */
    @Test
    public void checkOrganizationTypes() {
        assertNotNull(protocolOrganizationTypeList);
        assertTrue(protocolOrganizationTypeList.size()>0);
    }


    /**
     * Test Add protocol location.
     * Valid - no error
     * @throws Exception
     */
    @Test
    public void testAddProtocolLocationOK() throws Exception {
        ProtocolLocation newProtocolLocation = new ProtocolLocation();
        newProtocolLocation.setProtocolOrganizationType(protocolOrganizationTypeList.get(0));
        newProtocolLocation.setProtocolOrganizationTypeCode(protocolOrganizationTypeList.get(0).getProtocolOrganizationTypeCode());
        newProtocolLocation.setOrganizationId(NEW_ORGANIZATION_VALUE);
        assertTrue(rule.processAddProtocolLocationBusinessRules(getAddProtocolLocationEvent(newProtocolLocation)));
    }
    
    /**
     * This method is to test an error case
     * Invalid - Check duplicate organization error message
     * @throws Exception
     */
    @Test
    public void testAddProtocolLocationInValid() throws Exception {
        ProtocolLocation newProtocolLocation = new ProtocolLocation();
        newProtocolLocation.setProtocolOrganizationType(protocolOrganizationTypeList.get(0));
        newProtocolLocation.setProtocolOrganizationTypeCode(protocolOrganizationTypeList.get(0).getProtocolOrganizationTypeCode());
        newProtocolLocation.setOrganizationId(OLD_ORGANIZATION_VALUE);
        assertFalse(rule.processAddProtocolLocationBusinessRules(getAddProtocolLocationEvent(newProtocolLocation)));
        
        TypedArrayList errors = GlobalVariables.getErrorMap().getMessages(NEW_PROTOCOL_LOCATION + ".organizationId");
        assertTrue(errors.size() == 1);    
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), KeyConstants.ERROR_PROTOCOL_LOCATION_ORGANIZATION_ID_DUPLICATE);
    }
    
    /**
     * This method is to get add protocol location event
     * @param newProtocolLocation
     * @return
     * @throws Exception
     */
    private AddProtocolLocationEvent getAddProtocolLocationEvent(ProtocolLocation newProtocolLocation) throws Exception {
        ProtocolDocument document = getNewProtocolDocument();
        AddProtocolLocationEvent event = new AddProtocolLocationEvent(Constants.EMPTY_STRING,document,newProtocolLocation);
        return event;
    }

}
