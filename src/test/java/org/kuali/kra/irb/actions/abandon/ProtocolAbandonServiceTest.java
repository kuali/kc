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
package org.kuali.kra.irb.actions.abandon;

import java.sql.Date;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.genericactions.ProtocolGenericActionBean;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.service.DocumentService;

/**
 * 
 * This class is to test abandonservice
 */
public class ProtocolAbandonServiceTest  extends KcUnitTestBase {

    private static final String BASIC_COMMENT = "some dummy comments here";
    private static final Date BASIC_ACTION_DATE = new Date(System.currentTimeMillis());
    
    private ProtocolAbandonServiceImpl service;
    
    private Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        service = new ProtocolAbandonServiceImpl();
        service.setProtocolActionService(KraServiceLocator.getService(ProtocolActionService.class));
        service.setDocumentService(getMockDocumentService());        
    }

    @After
    public void tearDown() throws Exception {
        service = null;
        
        super.tearDown();
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
