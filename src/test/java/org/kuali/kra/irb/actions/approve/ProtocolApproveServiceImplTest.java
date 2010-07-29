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
package org.kuali.kra.irb.actions.approve;

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
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.risklevel.ProtocolRiskLevel;
import org.kuali.kra.irb.actions.risklevel.ProtocolRiskLevelBean;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.util.GlobalVariables;

//@PerSuiteUnitTestData(@UnitTestData(sqlFiles = {
//        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_status.sql", delimiter = ";"),
//        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_ORG_TYPE.sql", delimiter = ";"),
//        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_PERSON_ROLES.sql", delimiter = ";"),
//        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_type.sql", delimiter = ";"),
//        @UnitTestFile(filename = "classpath:sql/dml/load_SUBMISSION_TYPE.sql", delimiter = ";"),
//        @UnitTestFile(filename = "classpath:sql/dml/load_SUBMISSION_TYPE_QUALIFIER.sql", delimiter = ";"),
//        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_review_type.sql", delimiter = ";"),
//        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_REVIEWER_TYPE.sql", delimiter = ";"),
//        @UnitTestFile(filename = "classpath:sql/dml/load_committee_type.sql", delimiter = ";"),
//        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_ATTACHMENT_TYPE.sql", delimiter = ";")
//}))

public class ProtocolApproveServiceImplTest extends KcUnitTestBase {
    
    private DocumentService documentService;
    private ProtocolApproveServiceImpl protocolApproveServiceImpl;
    private ProtocolApproveService protocolApproveService;
    
    private static final Date BASIC_ACTION_DATE = new Date(2010, 2, 14);
    
    private static final String LOW_RISK_CODE = "1";
    private static final String HIGH_RISK_CODE = "5";
    private static final String ACTIVE_STATUS = "A";
    private static final String INACTIVE_STATUS = "I";
    private static final String HIGH_RISK_LEVEL_COMMENTS = "Very high risk.";

            
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        documentService = KraServiceLocator.getService(DocumentService.class);
        protocolApproveService = KraServiceLocator.getService(ProtocolApproveService.class);
        protocolApproveServiceImpl = (ProtocolApproveServiceImpl)KraServiceLocator.getService(ProtocolApproveService.class);
    }

    @After
    public void tearDown() throws Exception {
        documentService = null;
        protocolApproveService = null;
        protocolApproveServiceImpl = null;
        GlobalVariables.setUserSession(null);
        super.tearDown();
    }

    @Test
    public void testSetDocumentService() {
        protocolApproveServiceImpl.setDocumentService(documentService);
        assertTrue(true);
    }

    @Test
    public void testApprove() throws Exception{
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        Protocol prot = protocolDocument.getProtocol();
        ProtocolApproveBean actionBean = new ProtocolApproveBean();
        actionBean.setActionDate(BASIC_ACTION_DATE);
        actionBean.setApprovalDate(BASIC_ACTION_DATE);
        actionBean.setComments("some comments go here");
        actionBean.setExpirationDate(BASIC_ACTION_DATE);
        documentService.saveDocument(protocolDocument);
        protocolApproveService.approve(protocolDocument, actionBean);
        documentService.saveDocument(protocolDocument);
        String expected = ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT;
        assertEquals(expected, prot.getProtocolStatus().getProtocolStatusCode());
    }
    
    @Test
    public void testApproveRiskLevels() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        ProtocolApproveBean protocolApproveBean = new ProtocolApproveBean();
        ProtocolRiskLevelBean protocolRiskLevelBean = protocolApproveBean.getProtocolRiskLevelBean();
        
        ProtocolRiskLevel lowRiskLevelProtocol = protocolRiskLevelBean.getNewProtocolRiskLevel();
        lowRiskLevelProtocol.setRiskLevelCode(LOW_RISK_CODE);
        lowRiskLevelProtocol.setDateAssigned(BASIC_ACTION_DATE);
        lowRiskLevelProtocol.setStatus(ACTIVE_STATUS);
        protocolRiskLevelBean.addNewProtocolRiskLevel(protocolDocument.getProtocol());
        
        ProtocolRiskLevel highRiskLevelProtocol = protocolRiskLevelBean.getNewProtocolRiskLevel();
        highRiskLevelProtocol.setRiskLevelCode(HIGH_RISK_CODE);
        highRiskLevelProtocol.setDateAssigned(BASIC_ACTION_DATE);
        highRiskLevelProtocol.setStatus(INACTIVE_STATUS);
        highRiskLevelProtocol.setDateInactivated(BASIC_ACTION_DATE);
        highRiskLevelProtocol.setComments(HIGH_RISK_LEVEL_COMMENTS);
        protocolRiskLevelBean.addNewProtocolRiskLevel(protocolDocument.getProtocol());
        
        protocolApproveService.approve(protocolDocument, protocolApproveBean);
        
        verifyPersistRiskLevel(protocolDocument.getProtocol(), 0, LOW_RISK_CODE, BASIC_ACTION_DATE, ACTIVE_STATUS);
        verifyPersistRiskLevel(protocolDocument.getProtocol(), 1, HIGH_RISK_CODE, BASIC_ACTION_DATE, INACTIVE_STATUS, BASIC_ACTION_DATE, 
                HIGH_RISK_LEVEL_COMMENTS);
    }
    
    private void verifyPersistRiskLevel(Protocol protocol, int index, String expectedRiskLevelCode, Date expectedDateAssigned, String expectedStatus) {
        verifyPersistRiskLevel(protocol, index, expectedRiskLevelCode, expectedDateAssigned, expectedStatus, null, null);
    }
    
    private void verifyPersistRiskLevel(Protocol protocol, int index, String expectedRiskLevelCode, Date expectedDateAssigned, String expectedStatus, 
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
        List<ProtocolRiskLevel> list = (List<ProtocolRiskLevel>) getBusinessObjectService().findMatching(ProtocolRiskLevel.class, fieldValues);
        
        assertTrue(index + 1 <= list.size());
        return list.get(index);
    }
}