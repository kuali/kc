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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.irb.test.ProtocolRuleTestBase;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;

public class ProtocolRiskLevelRuleTest extends ProtocolRuleTestBase {
    
    private static final String RISK_LEVEL_PROPERTY_NAME = Constants.PROTOCOL_FULL_APPROVAL_ACTION_PROPERTY_KEY + ".protocolRiskLevelBean";
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        GlobalVariables.clear();
    }
    
    @Test
    public void testAddOK() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        ProtocolRiskLevel protocolRiskLevel = new ProtocolRiskLevel();
        protocolRiskLevel.setProtocol(protocolDocument.getProtocol());
        protocolRiskLevel.setRiskLevelCode("1");
        
        ProtocolAddRiskLevelEvent event = new ProtocolAddRiskLevelEvent(protocolDocument, RISK_LEVEL_PROPERTY_NAME, protocolRiskLevel);
        ProtocolAddRiskLevelRule rule = new ProtocolAddRiskLevelRule();
        assertTrue(rule.processRules(event));
    }
    
    @Test
    public void testAddAbsentRiskLevel() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        ProtocolRiskLevel protocolRiskLevel = new ProtocolRiskLevel();
        protocolRiskLevel.setProtocol(protocolDocument.getProtocol());
        
        ProtocolAddRiskLevelEvent event = new ProtocolAddRiskLevelEvent(protocolDocument, RISK_LEVEL_PROPERTY_NAME, protocolRiskLevel);
        ProtocolAddRiskLevelRule rule = new ProtocolAddRiskLevelRule();
        assertFalse(rule.processRules(event));
        assertError(RISK_LEVEL_PROPERTY_NAME + ".newProtocolRiskLevel.riskLevelCode", KeyConstants.ERROR_REQUIRED);
    }
    
    @Test
    public void testAddAbsentDateAssigned() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        ProtocolRiskLevel protocolRiskLevel = new ProtocolRiskLevel();
        protocolRiskLevel.setProtocol(protocolDocument.getProtocol());
        protocolRiskLevel.setRiskLevelCode("1");
        protocolRiskLevel.setDateAssigned(null);
        
        ProtocolAddRiskLevelEvent event = new ProtocolAddRiskLevelEvent(protocolDocument, RISK_LEVEL_PROPERTY_NAME, protocolRiskLevel);
        ProtocolAddRiskLevelRule rule = new ProtocolAddRiskLevelRule();
        assertFalse(rule.processRules(event));
        assertError(RISK_LEVEL_PROPERTY_NAME + ".newProtocolRiskLevel.dateAssigned", KeyConstants.ERROR_REQUIRED);
    }
    
    @Test
    public void testAddDuplicateRiskLevels() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        ProtocolRiskLevel protocolRiskLevel = new ProtocolRiskLevel();
        protocolRiskLevel.setProtocol(protocolDocument.getProtocol());
        protocolRiskLevel.setRiskLevelCode("1");
        protocolRiskLevel.setComments("Original");
        
        protocolDocument.getProtocol().getProtocolRiskLevels().add(protocolRiskLevel);
        
        ProtocolRiskLevel duplicateProtocolRiskLevel = new ProtocolRiskLevel();
        duplicateProtocolRiskLevel.setProtocol(protocolDocument.getProtocol());
        duplicateProtocolRiskLevel.setRiskLevelCode("1");
        duplicateProtocolRiskLevel.setComments("Duplicate");
        
        ProtocolAddRiskLevelEvent event = new ProtocolAddRiskLevelEvent(protocolDocument, RISK_LEVEL_PROPERTY_NAME, duplicateProtocolRiskLevel);
        ProtocolAddRiskLevelRule rule = new ProtocolAddRiskLevelRule();
        assertFalse(rule.processRules(event));
        assertError(RISK_LEVEL_PROPERTY_NAME + ".newProtocolRiskLevel.riskLevelCode", KeyConstants.ERROR_PROTOCOL_DUPLICATE_RISK_LEVEL);
    }
    
    @Test
    public void testAddActiveInactiveStatus() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        ProtocolRiskLevel protocolRiskLevel = new ProtocolRiskLevel();
        protocolRiskLevel.setProtocol(protocolDocument.getProtocol());
        protocolRiskLevel.setRiskLevelCode("1");
        protocolRiskLevel.setStatus("A");
        protocolRiskLevel.setComments("Original");
        
        protocolDocument.getProtocol().getProtocolRiskLevels().add(protocolRiskLevel);
        
        ProtocolRiskLevel duplicateProtocolRiskLevel = new ProtocolRiskLevel();
        duplicateProtocolRiskLevel.setProtocol(protocolDocument.getProtocol());
        duplicateProtocolRiskLevel.setRiskLevelCode("1");
        duplicateProtocolRiskLevel.setStatus("I");
        duplicateProtocolRiskLevel.setComments("Duplicate");
        
        ProtocolAddRiskLevelEvent event = new ProtocolAddRiskLevelEvent(protocolDocument, RISK_LEVEL_PROPERTY_NAME, duplicateProtocolRiskLevel);
        ProtocolAddRiskLevelRule rule = new ProtocolAddRiskLevelRule();
        assertTrue(rule.processRules(event));
    }
    
    @Test
    public void testUpdateOK() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        ProtocolRiskLevel protocolRiskLevel = new ProtocolRiskLevel();
        protocolRiskLevel.setProtocol(protocolDocument.getProtocol());
        protocolRiskLevel.setRiskLevelCode("1");
        protocolRiskLevel.setStatus("A");
        protocolRiskLevel.setComments("Original");
        
        protocolDocument.getProtocol().getProtocolRiskLevels().add(protocolRiskLevel);
        
        protocolRiskLevel.setStatus("I");
        protocolRiskLevel.setDateInactivated(new Date(System.currentTimeMillis()));
        
        ProtocolUpdateRiskLevelEvent event = new ProtocolUpdateRiskLevelEvent(protocolDocument, 0);
        ProtocolUpdateRiskLevelRule rule = new ProtocolUpdateRiskLevelRule();
        assertTrue(rule.processRules(event));
    }
    
    @Test
    public void testUpdateAbsentDateUpdated() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        ProtocolRiskLevel protocolRiskLevel = new ProtocolRiskLevel();
        protocolRiskLevel.setProtocol(protocolDocument.getProtocol());
        protocolRiskLevel.setRiskLevelCode("1");
        protocolRiskLevel.setStatus("A");
        protocolRiskLevel.setComments("Original");
        
        protocolDocument.getProtocol().getProtocolRiskLevels().add(protocolRiskLevel);
        
        protocolRiskLevel.setStatus("I");
        
        ProtocolUpdateRiskLevelEvent event = new ProtocolUpdateRiskLevelEvent(protocolDocument, 0);
        ProtocolUpdateRiskLevelRule rule = new ProtocolUpdateRiskLevelRule();
        assertFalse(rule.processRules(event));
        assertError(Constants.PROTOCOL_UPDATE_RISK_LEVEL_KEY + "[" + event.getIndex() + "].dateInactivated", KeyConstants.ERROR_PROTOCOL_DATE_INACTIVATED_REQUIRED);
    }
    
}