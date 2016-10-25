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
package org.kuali.kra.irb.actions.abandon;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.protocol.actions.genericactions.ProtocolGenericActionBean;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.DocumentService;

import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
/**
 * 
 * This class is to test abandonservice
 */
public class ProtocolAbandonServiceTest  extends KcIntegrationTestBase {

    private static final String BASIC_COMMENT = "some dummy comments here";
    private static final Date BASIC_ACTION_DATE = new Date(System.currentTimeMillis());
    
    private ProtocolAbandonServiceImpl service;
    
    private Mockery context = new JUnit4Mockery() {{
        setThreadingPolicy(new Synchroniser());
    }};
    
    @Before
    public void setUp() throws Exception {

        service = new ProtocolAbandonServiceImpl();
        service.setProtocolActionService(KcServiceLocator.getService(ProtocolActionService.class));
        service.setDocumentService(getMockDocumentService());        
    }

    @After
    public void tearDown() throws Exception {
        service = null;
        
    }
    
    @Test
    public void testAbandonProtocol() throws WorkflowException {
        runTest(getMockProtocolGenericActionBean());
    }
        
    /*
     * Runs a test for the configuration defined by the input parameters.
     */
    private void runTest(ProtocolGenericActionBean protocolAbandonBean) throws WorkflowException {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        
        service.abandonProtocol(protocolDocument.getProtocol(), protocolAbandonBean);
    
        ProtocolSubmission protocolSubmission = protocolDocument.getProtocol().getProtocolSubmission();
        assertNotNull(protocolSubmission);
        
        ProtocolAction protocolAction = protocolDocument.getProtocol().getLastProtocolAction();
        assertNotNull(protocolAction);
        assertEquals("313", protocolDocument.getProtocol().getProtocolStatusCode());
        
        verifyAction(protocolAction, protocolAbandonBean, protocolSubmission);
    }

     /*
     * Verfy that the Protocol Action is correct.
     */
    private void verifyAction(ProtocolAction action, ProtocolGenericActionBean requestBean, ProtocolSubmission submission) {
        assertEquals(ProtocolActionType.ABANDON_PROTOCOL, action.getProtocolActionTypeCode());
        assertEquals(submission.getSubmissionId(), action.getSubmissionIdFk());
        assertEquals(BASIC_COMMENT, action.getComments());
    }

    private DocumentService getMockDocumentService() {
        final DocumentService service = context.mock(DocumentService.class);
        
        context.checking(new Expectations() {{
            ignoring(service);
        }});
        
        return service;
    }
    
    
    private ProtocolGenericActionBean getMockProtocolGenericActionBean(){
        final ProtocolGenericActionBean bean = context.mock(ProtocolGenericActionBean.class);
        
        context.checking(new Expectations() {{
            allowing(bean).getComments();
            will(returnValue(BASIC_COMMENT));
            
            allowing(bean).getActionDate();
            will(returnValue(BASIC_ACTION_DATE));
        }});
        
        return bean;
    }
    

}
