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
package org.kuali.kra.irb.actions.approve;

import org.apache.commons.lang3.time.DateUtils;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.org.jmock.lib.legacy.ClassImposteriser;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.DocumentService;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import static org.junit.Assert.*;
public class ProtocolApproveServiceImplTest extends KcIntegrationTestBase {
    
    private static final Date ACTION_DATE = new Date(System.currentTimeMillis());
    private static final String COMMENTS = "some comments go here";
    private static final Date APPROVAL_DATE = org.kuali.coeus.sys.framework.util.DateUtils.convertToSqlDate(DateUtils.addWeeks(ACTION_DATE, -1));
    private static final Date EXPIRATION_DATE = org.kuali.coeus.sys.framework.util.DateUtils.convertToSqlDate(DateUtils.addYears(ACTION_DATE, 1));
    
    private static final String PROTOCOL_TYPE_EXEMPT = "4";
    
    private ProtocolApproveServiceImpl service;

    private Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
        setThreadingPolicy(new Synchroniser());
    }};
           
    @Before
    public void setUp() throws Exception {

        service = new ProtocolApproveServiceImpl();
        service.setProtocolActionService(KcServiceLocator.getService(ProtocolActionService.class));
        service.setParameterService(getMockParameterService());
        service.setProtocolOnlineReviewService(getMockOnlineReviewService());
        service.setDocumentService(KcServiceLocator.getService(DocumentService.class));
    }

    @After
    public void tearDown() throws Exception {
        service = null;
        
    }

    @Test
    public void testFullApproval() throws Exception{
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        addProtocolAction(protocolDocument.getProtocol());
        service.grantFullApproval(protocolDocument, getMockProtocolApproveBean());
        
        verifyPersistStatusAction(protocolDocument.getProtocol());
    }
    
    @Test
    public void testExpeditedApproval() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        addProtocolAction(protocolDocument.getProtocol());

        service.grantExpeditedApproval(protocolDocument, getMockProtocolApproveBean());
    
        verifyPersistStatusAction(protocolDocument.getProtocol());
    }
    
    @Test
    public void testResponseApproval() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        addProtocolAction(protocolDocument.getProtocol());
        
        service.grantResponseApproval(protocolDocument, getMockProtocolApproveBean());
    
        verifyPersistStatusAction(protocolDocument.getProtocol());
    }
    
    private void addProtocolAction (Protocol protocol) {
        ProtocolAction newAction = new ProtocolAction();
        newAction.setActionId(protocol.getNextValue("actionId"));
        newAction.setActualActionDate(new Timestamp(System.currentTimeMillis()));
        newAction.setActionDate(new Timestamp(ACTION_DATE.getTime()));
        newAction.setProtocolActionTypeCode(ProtocolActionType.NOTIFIED_COMMITTEE);
        newAction.setProtocolNumber(protocol.getProtocolNumber());
        newAction.setProtocolId(protocol.getProtocolId());
        newAction.setSequenceNumber(protocol.getSequenceNumber());
        newAction.setComments(COMMENTS);
        if (protocol.getProtocolActions() == null) {
            protocol.setProtocolActions(new ArrayList<ProtocolActionBase>());
        }
        protocol.getProtocolActions().add(newAction);
    }
    
    private void verifyPersistStatusAction(Protocol protocol) {
        assertEquals(ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT, protocol.getProtocolStatusCode());
        
        assertTrue(!protocol.getProtocolActions().isEmpty());
        ProtocolAction action = protocol.getLastProtocolAction();
        assertNotNull(action);
        assertEquals(APPROVAL_DATE, protocol.getApprovalDate());
        assertEquals(EXPIRATION_DATE, protocol.getExpirationDate());
        assertEquals(COMMENTS, action.getComments());
        assertEquals(ACTION_DATE, action.getActionDate());
    }
    
    private ParameterService getMockParameterService() {
        final ParameterService service = context.mock(ParameterService.class);
        
        context.checking(new Expectations() {{
            allowing(service).getParameterValueAsString(ProtocolDocument.class, Constants.PROTOCOL_TYPE_CODE_EXEMPT);
            will(returnValue(PROTOCOL_TYPE_EXEMPT));
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
    
    private ProtocolApproveBean getMockProtocolApproveBean() {
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
        }});
        
        return bean;
    }
    
}
