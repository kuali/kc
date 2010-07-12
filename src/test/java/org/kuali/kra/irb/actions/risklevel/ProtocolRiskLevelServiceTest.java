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
package org.kuali.kra.irb.actions.risklevel;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.irb.test.ProtocolRuleTestBase;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;

@PerSuiteUnitTestData(@UnitTestData(sqlFiles = {
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_status.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_ORG_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_PERSON_ROLES.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_type.sql", delimiter = ";")
}))
public class ProtocolRiskLevelServiceTest extends ProtocolRuleTestBase {
    
    private ProtocolRiskLevelServiceImpl protocolRiskLevelService;
    private BusinessObjectService businessObjectService;
    
    private static final String LOW_RISK_CODE = "1";
    private Date lowRiskDate;
    private static final String ACTIVE_STATUS = "A";
    
    private static final String HIGH_RISK_CODE = "5";
    private Date highRiskDate;
    private static final String INACTIVE_STATUS = "I";
    private static final String HIGH_RISK_LEVEL_COMMENTS = "Very high risk.";
    
    private static final long DAY = 86400000;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        protocolRiskLevelService = new ProtocolRiskLevelServiceImpl();
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        protocolRiskLevelService.setBusinessObjectService(businessObjectService);
    
        long systemTime = System.currentTimeMillis();
        lowRiskDate = new Date(systemTime);
        highRiskDate = new Date(systemTime + DAY);
    }
    
    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        super.tearDown();
    }
    
    @Test
    public void testSubmitRiskLevels() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        ProtocolRiskLevelBean protocolRiskLevelBean = new ProtocolRiskLevelBean();
        
        ProtocolRiskLevel lowRiskLevelProtocol = protocolRiskLevelBean.getNewProtocolRiskLevel();
        lowRiskLevelProtocol.setRiskLevelCode(LOW_RISK_CODE);
        lowRiskLevelProtocol.setDateAssigned(lowRiskDate);
        lowRiskLevelProtocol.setStatus(ACTIVE_STATUS);
        protocolRiskLevelBean.addNewProtocolRiskLevel(protocolDocument.getProtocol());
        
        ProtocolRiskLevel highRiskLevelProtocol = protocolRiskLevelBean.getNewProtocolRiskLevel();
        highRiskLevelProtocol.setRiskLevelCode(HIGH_RISK_CODE);
        highRiskLevelProtocol.setDateAssigned(highRiskDate);
        highRiskLevelProtocol.setStatus(INACTIVE_STATUS);
        highRiskLevelProtocol.setDateInactivated(highRiskDate);
        highRiskLevelProtocol.setComments(HIGH_RISK_LEVEL_COMMENTS);
        protocolRiskLevelBean.addNewProtocolRiskLevel(protocolDocument.getProtocol());
        
        protocolRiskLevelService.persistProtocolRiskLevels(protocolDocument.getProtocol());
        
        verifyPersist(protocolDocument.getProtocol(), 0, LOW_RISK_CODE, lowRiskDate, ACTIVE_STATUS);
        verifyPersist(protocolDocument.getProtocol(), 1, HIGH_RISK_CODE, highRiskDate, INACTIVE_STATUS, highRiskDate, HIGH_RISK_LEVEL_COMMENTS);
    }
    
    private void verifyPersist(Protocol protocol, int index, String expectedRiskLevelCode, Date expectedDateAssigned, String expectedStatus) {
        verifyPersist(protocol, index, expectedRiskLevelCode, expectedDateAssigned, expectedStatus, null, null);
    }
    
    private void verifyPersist(Protocol protocol, int index, String expectedRiskLevelCode, Date expectedDateAssigned, String expectedStatus, 
            Date expectedDateUpdated, String expectedComments) {
        ProtocolRiskLevel protocolRiskLevel = findProtocolRiskLevel(protocol.getProtocolId(), index);
        assertEquals(protocolRiskLevel.getProtocolId(), protocol.getProtocolId());
        assertEquals(expectedRiskLevelCode, protocolRiskLevel.getRiskLevelCode());
        assertEquals(expectedDateAssigned, protocolRiskLevel.getDateAssigned());
        assertEquals(expectedStatus, protocolRiskLevel.getStatus());
        assertEquals(expectedDateUpdated, protocolRiskLevel.getDateInactivated());
        assertEquals(expectedComments, protocolRiskLevel.getComments());
    }
    
    @SuppressWarnings("unchecked")
    private ProtocolRiskLevel findProtocolRiskLevel(Long protocolId, int index) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("protocolId", protocolId);
        List<ProtocolRiskLevel> list = (List<ProtocolRiskLevel>) businessObjectService.findMatching(ProtocolRiskLevel.class, fieldValues);
        
        assertTrue(index + 1 <= list.size());
        return list.get(index);
    }

}
