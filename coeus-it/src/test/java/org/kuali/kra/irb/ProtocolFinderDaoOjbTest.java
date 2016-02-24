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
package org.kuali.kra.irb;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
public class ProtocolFinderDaoOjbTest extends KcIntegrationTestBase {
    
    private static final String PROTOCOL_NUMBER = "0906000003";
    private static final String PROTOCOL_NUMBER2 = "0906000002";

    private ProtocolFinderDao protocolFinder;
    
    @Before
    public void setUp() throws Exception {
        protocolFinder = KcServiceLocator.getService(ProtocolFinderDao.class);
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void testFindProtocol() throws WorkflowException {
        ProtocolFactory.createProtocolDocument(PROTOCOL_NUMBER, 1);
        ProtocolDocument protocolDocument1 = ProtocolFactory.createProtocolDocument(PROTOCOL_NUMBER, 2);
        
        ProtocolDocument protocolDocument2 = ProtocolFactory.createProtocolDocument(PROTOCOL_NUMBER+"A001", 1);
       
        List<Protocol> protocols = (List)protocolFinder.findProtocols(PROTOCOL_NUMBER);
        assertEquals(3, protocols.size());
        assertEquals(PROTOCOL_NUMBER, protocols.get(0).getProtocolNumber());
    }

    /**
     * Add "two" protocols.  One of the two protocols will have two versions.  For each of
     * the "two" protocols, we should get back the latest version.
     */
    @Test
    public void testFinder() throws WorkflowException {
        ProtocolFactory.createProtocolDocument(PROTOCOL_NUMBER, 1);
        ProtocolDocument protocolDocument1 = ProtocolFactory.createProtocolDocument(PROTOCOL_NUMBER, 2);

        ProtocolDocument protocolDocument2 = ProtocolFactory.createProtocolDocument(PROTOCOL_NUMBER2, 1);

        Protocol protocol = protocolFinder.findCurrentProtocolByNumber(PROTOCOL_NUMBER2);
        assertNotNull(protocol);
        assertEquals(protocolDocument2.getProtocol().getProtocolId(), protocol.getProtocolId());

        protocol = protocolFinder.findCurrentProtocolByNumber(PROTOCOL_NUMBER);
        assertNotNull(protocol);
        assertEquals(protocolDocument1.getProtocol().getProtocolId(), protocol.getProtocolId());
    }
}
