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
package org.kuali.kra.irb.actions.delete;

import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewService;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendmentBean;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kew.api.exception.WorkflowException;

/**
 * Test the ProtocolDeleteService implementation.
 */
public class ProtocolDeleteServiceTest extends KcUnitTestBase {

    private static final String REASON = "my test reason";
    private static final String SUMMARY = "summary";
    
    private ProtocolDeleteService service;
    private ProtocolAmendRenewService protocolAmendRenewService;
    
    private Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};
    
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        service = KraServiceLocator.getService(ProtocolDeleteService.class);
        protocolAmendRenewService = KraServiceLocator.getService(ProtocolAmendRenewService.class);
    }

    @After
    public void tearDown() throws Exception {
        service = null;
        protocolAmendRenewService = null;
        
        super.tearDown();
    }
    
    @Test
    public void testDelete() throws WorkflowException {
        ProtocolDeleteBean protocolDeleteBean = getMockProtocolDeleteBean();
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
         
        service.delete(protocolDocument.getProtocol(), protocolDeleteBean);
    
        assertFalse(protocolDocument.getProtocol().isActive());
        assertEquals(ProtocolStatus.DELETED, protocolDocument.getProtocol().getProtocolStatusCode());
    }
    
    @Test
    public void testDeleteAmendment() throws Exception {
        ProtocolAmendmentBean protocolAmendmentBean = getMockProtocolAmendmentBean();
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        String docNbr = protocolAmendRenewService.createAmendment(protocolDocument, protocolAmendmentBean);
        ProtocolDocument amendmentDocument = (ProtocolDocument) getDocumentService().getByDocumentHeaderId(docNbr);
        
        List<String> modules = protocolAmendRenewService.getAvailableModules(protocolDocument.getProtocol().getProtocolNumber());
        assertEquals(10, modules.size());
        
        ProtocolDeleteBean protocolDeleteBean = getMockProtocolDeleteBean();
        service.delete(amendmentDocument.getProtocol(), protocolDeleteBean);
        
        modules = protocolAmendRenewService.getAvailableModules(protocolDocument.getProtocol().getProtocolNumber());
        assertEquals(12, modules.size());
    }
    
    private ProtocolDeleteBean getMockProtocolDeleteBean() {
        final ProtocolDeleteBean bean = context.mock(ProtocolDeleteBean.class);
        
        context.checking(new Expectations() {{
            allowing(bean).getReason();
            will(returnValue(REASON));
        }});
        
        return bean;
    }
    
    private ProtocolAmendmentBean getMockProtocolAmendmentBean() {
        final ProtocolAmendmentBean bean = context.mock(ProtocolAmendmentBean.class);
        
        context.checking(new Expectations() {{
            allowing(bean).getSummary();
            will(returnValue(SUMMARY));
            
            allowing(bean).getGeneralInfo();
            will(returnValue(false));
            
            allowing(bean).getFundingSource();
            will(returnValue(false));
            
            allowing(bean).getProtocolReferencesAndOtherIdentifiers();
            will(returnValue(false));
            
            allowing(bean).getProtocolOrganizations();
            will(returnValue(false));
            
            allowing(bean).getSubjects();
            will(returnValue(false));
            
            allowing(bean).getAddModifyAttachments();
            will(returnValue(true));
            
            allowing(bean).getAreasOfResearch();
            will(returnValue(false));
            
            allowing(bean).getSpecialReview();
            will(returnValue(false));
            
            allowing(bean).getProtocolPersonnel();
            will(returnValue(false));
            
            allowing(bean).getOthers();
            will(returnValue(false));
            
            allowing(bean).getProtocolPermissions();
            will(returnValue(true));
            allowing(bean).getQuestionnaire();
            will(returnValue(false));
        }});
        
        return bean;
    }
    
}