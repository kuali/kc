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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.test.ProtocolRuleTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.List;

import static org.junit.Assert.*;
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
        bos = KcServiceLocator.getService(BusinessObjectService.class);
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
        
        List errors = GlobalVariables.getMessageMap().getMessages(NEW_PROTOCOL_LOCATION + ".organizationId");
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
