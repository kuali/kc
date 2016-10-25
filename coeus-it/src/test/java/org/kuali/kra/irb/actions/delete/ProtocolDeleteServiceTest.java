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
package org.kuali.kra.irb.actions.delete;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewService;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendmentBean;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
/**
 * Test the ProtocolDeleteService implementation.
 */
public class ProtocolDeleteServiceTest extends KcIntegrationTestBase {

    private static final String SUMMARY = "summary";
    
    private ProtocolDeleteService service;
    private ProtocolAmendRenewService protocolAmendRenewService;
    
    private Mockery context = new JUnit4Mockery() {{
        setThreadingPolicy(new Synchroniser());
    }};
    
    @Before
    public void setUp() throws Exception {

        service = KcServiceLocator.getService(ProtocolDeleteService.class);
        protocolAmendRenewService = KcServiceLocator.getService(ProtocolAmendRenewService.class);
    }

    @After
    public void tearDown() throws Exception {
        service = null;
        protocolAmendRenewService = null;
        
    }
    
    @Test
    public void testDelete() throws WorkflowException {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
         
        service.delete(protocolDocument);
    
        assertFalse(protocolDocument.getProtocol().isActive());
        assertEquals(ProtocolStatus.DELETED, protocolDocument.getProtocol().getProtocolStatusCode());
    }
    
    @Test
    public void testDeleteAmendment() throws Exception {
        ProtocolAmendmentBean protocolAmendmentBean = getMockProtocolAmendmentBean();
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        String docNbr = protocolAmendRenewService.createAmendment(protocolDocument, protocolAmendmentBean);
        ProtocolDocument amendmentDocument = (ProtocolDocument) KRADServiceLocatorWeb.getDocumentService().getByDocumentHeaderId(docNbr);
        
        List<String> modules = protocolAmendRenewService.getAvailableModules(protocolDocument.getProtocol().getProtocolNumber());
        assertEquals(10, modules.size());
        
        service.delete(amendmentDocument);
        
        modules = protocolAmendRenewService.getAvailableModules(protocolDocument.getProtocol().getProtocolNumber());
        assertEquals(12, modules.size());
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
