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
package org.kuali.kra.irb.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.irb.bo.Protocol;
import org.kuali.rice.test.data.PerTestUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;
import org.kuali.rice.test.data.UnitTestSql;

@PerTestUnitTestData(
    @UnitTestData(
            sqlFiles = {
                  @UnitTestFile(filename = "classpath:sql/dml/load_protocols_for_protocoldaotest.sql", delimiter = ";")
            }
   )
)

public class ProtocolDaoOjbTest extends KraTestBase {
    private static final String PROTOCOL_ID_VALUE="201";
    private static final String PROTOCOL_NUMBER_VALUE="202";
    private static final String PROTOCOL_ID_PROPERTY="protocolId";
    private static final String PROTOCOL_NUMBER_PROPERTY="protocolNumber";
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
    
    @After 
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    @Test
    public void testGetProtocols() {
         
        testProtocolId();
        testResearchArea();
        testPerson();
        testInvestigator();
        testPerformingOrganization();
        testPerformingFundingSource();
    }

    private void testProtocolId() {
        Map fieldValues = new HashMap();
        fieldValues.put(PROTOCOL_ID_PROPERTY, PROTOCOL_ID_VALUE);
        fieldValues.put(PROTOCOL_NUMBER_PROPERTY, PROTOCOL_ID_VALUE);
        List<Protocol> protocols = getProtocolDao().getProtocols(fieldValues);
        assertEquals(protocols.size(), 0);

        fieldValues.put(PROTOCOL_NUMBER_PROPERTY, PROTOCOL_NUMBER_VALUE);
        protocols = getProtocolDao().getProtocols(fieldValues);
        assertEquals(protocols.size(), 1);

    }
    
    private void  testResearchArea() {
    
        Map fieldValues = new HashMap();
        fieldValues.put(PROTOCOL_ID_PROPERTY, PROTOCOL_ID_VALUE);
        fieldValues.put(PROTOCOL_NUMBER_PROPERTY, PROTOCOL_NUMBER_VALUE);
        fieldValues.put("researchAreaCode", "01.0*");
        List<Protocol> protocols = getProtocolDao().getProtocols(fieldValues);
        assertEquals(protocols.size(), 1);

        fieldValues.put("researchAreaCode", "02.0*");
        protocols = getProtocolDao().getProtocols(fieldValues);
        assertEquals(protocols.size(), 0);
    }

    private void  testPerson() {
        
        Map fieldValues = new HashMap();
        fieldValues.put(PROTOCOL_ID_PROPERTY, PROTOCOL_ID_VALUE);
        fieldValues.put(PROTOCOL_NUMBER_PROPERTY, PROTOCOL_NUMBER_VALUE);
        fieldValues.put("keyPerson", "a*");
        fieldValues.put("researchAreaCode", "01.0*");
        List<Protocol> protocols = getProtocolDao().getProtocols(fieldValues);
        assertEquals(protocols.size(), 1);
        
        fieldValues.put("keyPerson", "t*");
        fieldValues.put("researchAreaCode", "01.0*");
        protocols = getProtocolDao().getProtocols(fieldValues);
        assertEquals(protocols.size(), 0);

    }
    
    private void  testInvestigator() {
        
        Map fieldValues = new HashMap();
        fieldValues.put(PROTOCOL_ID_PROPERTY, PROTOCOL_ID_VALUE);
        fieldValues.put(PROTOCOL_NUMBER_PROPERTY, PROTOCOL_NUMBER_VALUE);
        fieldValues.put("keyPerson", "a*");
        fieldValues.put("investigator", "t*");
        fieldValues.put("researchAreaCode", "01.0*");
        List<Protocol> protocols = getProtocolDao().getProtocols(fieldValues);
        assertEquals(protocols.size(), 1);
        
        fieldValues.put("investigator", "a*");
        fieldValues.put("researchAreaCode", "01.0*");
        protocols = getProtocolDao().getProtocols(fieldValues);
        assertEquals(protocols.size(), 0);

    }
    
    private void  testPerformingOrganization() {
        
        Map fieldValues = new HashMap();
        fieldValues.put(PROTOCOL_ID_PROPERTY, PROTOCOL_ID_VALUE);
        fieldValues.put(PROTOCOL_NUMBER_PROPERTY, PROTOCOL_NUMBER_VALUE);
        fieldValues.put("keyPerson", "a*");
        fieldValues.put("investigator", "t*");
        fieldValues.put("performingOrganizationId", "000001");
        fieldValues.put("researchAreaCode", "01.0*");
        List<Protocol> protocols = getProtocolDao().getProtocols(fieldValues);
        assertEquals(protocols.size(), 1);
        
        fieldValues.put("performingOrganizationId", "000002");
        protocols = getProtocolDao().getProtocols(fieldValues);
        assertEquals(protocols.size(), 0);

    }
    private void  testPerformingFundingSource() {
        
        Map fieldValues = new HashMap();
        fieldValues.put(PROTOCOL_ID_PROPERTY, PROTOCOL_ID_VALUE);
        fieldValues.put(PROTOCOL_NUMBER_PROPERTY, PROTOCOL_NUMBER_VALUE);
        fieldValues.put("keyPerson", "a*");
        fieldValues.put("investigator", "t*");
        fieldValues.put("performingOrganizationId", "000001");
        fieldValues.put("researchAreaCode", "01.0*");
        fieldValues.put("fundingSource", "000610");
        List<Protocol> protocols = getProtocolDao().getProtocols(fieldValues);
        assertEquals(protocols.size(), 1);
        
        fieldValues.put("fundingSource", "000001");
        protocols = getProtocolDao().getProtocols(fieldValues);
        assertEquals(protocols.size(), 1);
        
    }
    
    private ProtocolDao getProtocolDao() {
        return getService(ProtocolDao.class);
    }

}
