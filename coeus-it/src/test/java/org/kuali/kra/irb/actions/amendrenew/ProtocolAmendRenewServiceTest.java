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
package org.kuali.kra.irb.actions.amendrenew;

import org.apache.commons.lang3.StringUtils;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.dao.KraLookupDao;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolFinderDao;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.copy.ProtocolCopyService;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.coeus.common.questionnaire.framework.answer.QuestionnaireAnswerService;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
/**
 * Test the ProtocolAmendRenewService implementation.
 */
public class ProtocolAmendRenewServiceTest extends KcIntegrationTestBase {

    private static final String SUMMARY = "my test summary";
    
    private ProtocolAmendRenewServiceImpl service;
    
    private Mockery context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
        setThreadingPolicy(new Synchroniser());
    }};
    
    @Before
    public void setUp() throws Exception {

        service = new ProtocolAmendRenewServiceImpl();
        service.setDocumentService(KcServiceLocator.getService(DocumentService.class));
        service.setProtocolCopyService(KcServiceLocator.getService(ProtocolCopyService.class));
        service.setKraLookupDao(KcServiceLocator.getService(KraLookupDao.class));
        service.setProtocolFinderDao(KcServiceLocator.getService(ProtocolFinderDao.class));
        service.setQuestionnaireAnswerService(KcServiceLocator.getService(QuestionnaireAnswerService.class));
        service.setBusinessObjectService(KcServiceLocator.getService(BusinessObjectService.class));
    }

    @After
    public void tearDown() throws Exception {
        service = null;
        
    }
    
    @Test
    public void testAmendment() throws Exception {
        ProtocolAmendmentBean protocolAmendmentBean = getMockProtocolAmendmentBean(false, false, false, true, false, true, false, false, false, false, false, false);
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        String docNbr = service.createAmendment(protocolDocument, protocolAmendmentBean);
        
        ProtocolDocument amendmentDocument = (ProtocolDocument) KRADServiceLocatorWeb.getDocumentService().getByDocumentHeaderId(docNbr);
    
        assertEquals(protocolDocument.getProtocol().getProtocolNumber() + "A001", amendmentDocument.getProtocol().getProtocolNumber());
        
        verifyAction(protocolDocument.getProtocol(), ProtocolActionType.AMENDMENT_CREATED, "Amendment-001: Created");
        verifyAmendmentRenewal(amendmentDocument.getProtocol(), SUMMARY, 2);
    }
    
    @Test
    public void testRenewal() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        String docNbr = service.createRenewal(protocolDocument, SUMMARY);
        
        ProtocolDocument amendmentDocument = (ProtocolDocument) KRADServiceLocatorWeb.getDocumentService().getByDocumentHeaderId(docNbr);
    
        assertEquals(protocolDocument.getProtocol().getProtocolNumber() + "R001", amendmentDocument.getProtocol().getProtocolNumber());
        
        verifyAction(protocolDocument.getProtocol(), ProtocolActionType.RENEWAL_CREATED, "Renewal-001: Created");
        verifyAmendmentRenewal(amendmentDocument.getProtocol(), SUMMARY, 0);
    }
    
    @Test
    public void testRenewalWithAmendment() throws Exception {
        ProtocolAmendmentBean protocolAmendmentBean = getMockProtocolAmendmentBean(false, false, false, true, false, true, false, false, false, false, false, false);
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        
        String docNbr = service.createRenewalWithAmendment(protocolDocument, protocolAmendmentBean);
        
        ProtocolDocument amendmentDocument = (ProtocolDocument) KRADServiceLocatorWeb.getDocumentService().getByDocumentHeaderId(docNbr);
    
        assertEquals(protocolDocument.getProtocol().getProtocolNumber() + "R001", amendmentDocument.getProtocol().getProtocolNumber());
        
        verifyAction(protocolDocument.getProtocol(), ProtocolActionType.RENEWAL_WITH_AMENDMENT_CREATED, "Renewal-001: Created");
        verifyAmendmentRenewal(amendmentDocument.getProtocol(), SUMMARY, 2);
    }

    private void verifyAction(Protocol protocol, String expectedActionType, String expectedComment) {
        ProtocolAction action = protocol.getLastProtocolAction();
        assertEquals(expectedActionType, action.getProtocolActionTypeCode());
        assertEquals(action.getProtocolId(), protocol.getProtocolId());
        assertEquals(null, action.getSubmissionIdFk());
        assertEquals(expectedComment, action.getComments());
    }
    
    private void verifyAmendmentRenewal(Protocol protocol, String expectedSummary, int moduleCount) {
        ProtocolAmendRenewal amendRenewal = (ProtocolAmendRenewal) protocol.getProtocolAmendRenewal();
        assertEquals(amendRenewal.getProtocolId(), protocol.getProtocolId());
        assertEquals(expectedSummary, amendRenewal.getSummary());
        verifyModules(amendRenewal, moduleCount);
    }
    
    private void verifyModules(ProtocolAmendRenewal amendRenewal, int moduleCount) {
        List<ProtocolAmendRenewModule> modules = (List)amendRenewal.getModules();
        assertEquals(moduleCount, modules.size());
        if (moduleCount > 0) {
            assertContains(ProtocolModule.ADD_MODIFY_ATTACHMENTS, modules);
            assertContains(ProtocolModule.PROTOCOL_ORGANIZATIONS, modules);
        }
    }

    private void assertContains(String moduleTypeCode, List<ProtocolAmendRenewModule> modules) {
        for (ProtocolAmendRenewModule module : modules) {
            if (StringUtils.equals(moduleTypeCode, module.getProtocolModuleTypeCode())) {
                return;
            }
        }
        assertTrue(false);
    }
    
    /**
     * Verify that the getAmendmentAndRenewals() method works.
     * @throws WorkflowException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testGetAmendmentsAndRenewals() throws Exception {
        ProtocolDocument a1 = ProtocolFactory.createProtocolDocument("0906000001A001");
        ProtocolDocument a2 = ProtocolFactory.createProtocolDocument("0906000001A002");
        ProtocolDocument r1 = ProtocolFactory.createProtocolDocument("0906000001R001");
        List<Protocol> protocols = (List)service.getAmendmentAndRenewals("0906000001");
        assertEquals(3, protocols.size());
        assertTrue(containsProtocol(protocols, a1));
        assertTrue(containsProtocol(protocols, a2));
        assertTrue(containsProtocol(protocols, r1));
    }
    
    private boolean containsProtocol(List<Protocol> protocols, ProtocolDocument a1) {
        for (Protocol protocol : protocols) {
            if (StringUtils.equals(protocol.getProtocolNumber(), a1.getProtocol().getProtocolNumber())) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Verify that the list of available modules is correct.  
     * Create an amendment with some modules and then query to 
     * get the available modules.  We should get the modules that
     * were not included in the amendment.
     * @throws Exception
     */
    @Test
    public void testAvailableModules() throws Exception {
        ProtocolAmendmentBean protocolAmendmentBean = getMockProtocolAmendmentBean(false, false, false, true, false, true, true, false, false, false, false, false);
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument("0906000001");
        
        service.createAmendment(protocolDocument, protocolAmendmentBean);
        
        List<String> modules = service.getAvailableModules("0906000001");
        assertEquals(9, modules.size());
        assertTrue(modules.contains(ProtocolModule.FUNDING_SOURCE));
        assertTrue(modules.contains(ProtocolModule.GENERAL_INFO));
        assertTrue(modules.contains(ProtocolModule.OTHERS));
        assertTrue(modules.contains(ProtocolModule.PROTOCOL_PERSONNEL));
        assertTrue(modules.contains(ProtocolModule.PROTOCOL_REFERENCES));
        assertTrue(modules.contains(ProtocolModule.SPECIAL_REVIEW));
        assertTrue(modules.contains(ProtocolModule.SUBJECTS));
        assertTrue(modules.contains(ProtocolModule.PROTOCOL_PERMISSIONS));
        assertTrue(modules.contains(ProtocolModule.QUESTIONNAIRE));
    }
    
    private ProtocolAmendmentBean getMockProtocolAmendmentBean(final boolean generalInfo, final boolean fundingSource, 
            final boolean protocolReferencesAndOtherIdentifiers, final boolean protocolOrganizations, final boolean subjects, 
            final boolean addModifyAttachments, final boolean areasOfResearch, final boolean specialReview, final boolean protocolPersonnel, 
            final boolean others, final boolean protocolPermissions, final boolean questionnaire) {
        
        final ProtocolAmendmentBean bean = context.mock(ProtocolAmendmentBean.class);
        
        context.checking(new Expectations() {{
            allowing(bean).getSummary();
            will(returnValue(SUMMARY));
            
            allowing(bean).getGeneralInfo();
            will(returnValue(generalInfo));
            
            allowing(bean).getFundingSource();
            will(returnValue(fundingSource));
            
            allowing(bean).getProtocolReferencesAndOtherIdentifiers();
            will(returnValue(protocolReferencesAndOtherIdentifiers));
            
            allowing(bean).getProtocolOrganizations();
            will(returnValue(protocolOrganizations));
            
            allowing(bean).getSubjects();
            will(returnValue(subjects));
            
            allowing(bean).getAddModifyAttachments();
            will(returnValue(addModifyAttachments));
            
            allowing(bean).getAreasOfResearch();
            will(returnValue(areasOfResearch));
            
            allowing(bean).getSpecialReview();
            will(returnValue(specialReview));
            
            allowing(bean).getProtocolPersonnel();
            will(returnValue(protocolPersonnel));
            
            allowing(bean).getOthers();
            will(returnValue(others));
            
            allowing(bean).getProtocolPermissions();
            will(returnValue(protocolPermissions));
            allowing(bean).getQuestionnaire();
            will(returnValue(questionnaire));
        }});
        
        return bean;
    }
    
}
