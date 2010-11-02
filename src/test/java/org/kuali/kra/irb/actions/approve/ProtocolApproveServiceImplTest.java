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
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.correspondence.ProtocolActionCorrespondenceGenerationService;
import org.kuali.kra.irb.actions.risklevel.ProtocolRiskLevel;
import org.kuali.kra.irb.actions.risklevel.ProtocolRiskLevelBean;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.ParameterService;
import org.kuali.rice.kns.util.DateUtils;

public class ProtocolApproveServiceImplTest extends KcUnitTestBase {
    
    private static final Date ACTION_DATE = new Date(System.currentTimeMillis());
    private static final String COMMENTS = "some comments go here";
    private static final Date APPROVAL_DATE = DateUtils.convertToSqlDate(DateUtils.addWeeks(ACTION_DATE, -1));
    private static final Date EXPIRATION_DATE = DateUtils.convertToSqlDate(DateUtils.addYears(ACTION_DATE, 1));
    
    private static final String LOW_RISK_CODE = "1";
    private static final String HIGH_RISK_CODE = "5";
    private static final String ACTIVE_STATUS = "A";
    private static final String INACTIVE_STATUS = "I";
    private static final Date ASSIGNED_DATE = DateUtils.convertToSqlDate(DateUtils.addDays(new Date(System.currentTimeMillis()), -1));
    private static final Date INACTIVATED_DATE = new Date(System.currentTimeMillis());
    private static final String HIGH_RISK_LEVEL_COMMENTS = "Very high risk.";
    private static final String PROTOCOL_TYPE_EXEMPT = "4";
    
    private ProtocolApproveServiceImpl service;

    private Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};
           
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        service = new ProtocolApproveServiceImpl();
        service.setProtocolActionService(KraServiceLocator.getService(ProtocolActionService.class));
        service.setParameterService(getMockParameterService());
        service.setProtocolActionCorrespondenceGenerationService(getMockActionCorrespondenceGenerationService());
        service.setProtocolOnlineReviewService(getMockOnlineReviewService());
        service.setDocumentService(KraServiceLocator.getService(DocumentService.class));
    }

    @Override
    @After
    public void tearDown() throws Exception {
        service = null;
        
        super.tearDown();
    }

    @Test
    public void testApprove() throws Exception{
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();

        service.approve(protocolDocument, getMockApproveBean(new ProtocolRiskLevelBean()));
        
        String expected = ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT;
        assertEquals(expected, protocolDocument.getProtocol().getProtocolStatus().getProtocolStatusCode());
    }
    
    @Test
    public void testApproveRiskLevels() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        ProtocolRiskLevelBean protocolRiskLevelBean = new ProtocolRiskLevelBean();
        
        ProtocolRiskLevel lowRiskLevelProtocol = protocolRiskLevelBean.getNewProtocolRiskLevel();
        lowRiskLevelProtocol.setRiskLevelCode(LOW_RISK_CODE);
        lowRiskLevelProtocol.setDateAssigned(ASSIGNED_DATE);
        lowRiskLevelProtocol.setStatus(ACTIVE_STATUS);
        protocolRiskLevelBean.addNewProtocolRiskLevel(protocolDocument.getProtocol());
        
        ProtocolRiskLevel highRiskLevelProtocol = protocolRiskLevelBean.getNewProtocolRiskLevel();
        highRiskLevelProtocol.setRiskLevelCode(HIGH_RISK_CODE);
        highRiskLevelProtocol.setDateAssigned(ASSIGNED_DATE);
        highRiskLevelProtocol.setStatus(INACTIVE_STATUS);
        highRiskLevelProtocol.setDateInactivated(INACTIVATED_DATE);
        highRiskLevelProtocol.setComments(HIGH_RISK_LEVEL_COMMENTS);
        protocolRiskLevelBean.addNewProtocolRiskLevel(protocolDocument.getProtocol());
        
        ProtocolApproveBean protocolApproveBean = getMockApproveBean(protocolRiskLevelBean);
        service.approve(protocolDocument, protocolApproveBean);
        
        verifyPersistRiskLevel(protocolDocument.getProtocol(), 0, LOW_RISK_CODE, ASSIGNED_DATE, ACTIVE_STATUS);
        verifyPersistRiskLevel(protocolDocument.getProtocol(), 1, HIGH_RISK_CODE, ASSIGNED_DATE, INACTIVE_STATUS, INACTIVATED_DATE, 
                HIGH_RISK_LEVEL_COMMENTS);
    }
    
    private void verifyPersistRiskLevel(Protocol protocol, int index, String expectedRiskLevelCode, Date expectedDateAssigned, String expectedStatus) {
        verifyPersistRiskLevel(protocol, index, expectedRiskLevelCode, expectedDateAssigned, expectedStatus, null, null);
    }
    
    private void verifyPersistRiskLevel(Protocol protocol, int index, String expectedRiskLevelCode, Date expectedDateAssigned, String expectedStatus, 
            Date expectedDateUpdated, String expectedComments) {
        ProtocolRiskLevel protocolRiskLevel = findProtocolRiskLevel(protocol, index);
        assertEquals(protocolRiskLevel.getProtocolId(), protocol.getProtocolId());
        assertEquals(expectedRiskLevelCode, protocolRiskLevel.getRiskLevelCode());
        assertEquals(expectedDateAssigned, protocolRiskLevel.getDateAssigned());
        assertEquals(expectedStatus, protocolRiskLevel.getStatus());
        assertEquals(expectedDateUpdated, protocolRiskLevel.getDateInactivated());
        assertEquals(expectedComments, protocolRiskLevel.getComments());
    }
    
    private ProtocolRiskLevel findProtocolRiskLevel(Protocol protocol, int index) {
        List<ProtocolRiskLevel> riskLevels = protocol.getProtocolRiskLevels();
        assertTrue(index + 1 <= riskLevels.size());
        return riskLevels.get(index);
    }
    
    private ParameterService getMockParameterService() {
        final ParameterService service = context.mock(ParameterService.class);
        
        context.checking(new Expectations() {{
            allowing(service).getParameterValue(ProtocolDocument.class, Constants.PROTOCOL_TYPE_CODE_EXEMPT);
            will(returnValue(PROTOCOL_TYPE_EXEMPT));
        }});
        
        return service;
    }
    
    private ProtocolActionCorrespondenceGenerationService getMockActionCorrespondenceGenerationService() {
        final ProtocolActionCorrespondenceGenerationService service = context.mock(ProtocolActionCorrespondenceGenerationService.class);
        
        context.checking(new Expectations() {{
            ignoring(service);
        }});
        
        return service;
    }
    
    private ProtocolOnlineReviewService getMockOnlineReviewService() {
        final ProtocolOnlineReviewService service = context.mock(ProtocolOnlineReviewService.class);
        
        context.checking(new Expectations() {{
            ignoring(service);
        }});
        
        return service;
    }
    
    private ProtocolApproveBean getMockApproveBean(final ProtocolRiskLevelBean protocolRiskLevelBean) {
        final ProtocolApproveBean bean = context.mock(ProtocolApproveBean.class);
        
        context.checking(new Expectations() {{
            allowing(bean).getApprovalDate();
            will(returnValue(APPROVAL_DATE));
            
            allowing(bean).getExpirationDate();
            will(returnValue(EXPIRATION_DATE));
            
            allowing(bean).getActionDate();
            will(returnValue(ACTION_DATE));
            
            allowing(bean).getComments();
            will(returnValue(COMMENTS));
            
            allowing(bean).getProtocolRiskLevelBean();
            will(returnValue(protocolRiskLevelBean));
        }});
        
        return bean;
    }
    
}