/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.actions.amendrenew;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.dao.KraLookupDao;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.copy.ProtocolCopyService;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;

/**
 * Test the ProtocolAmendRenewService implementation.
 */
@PerSuiteUnitTestData(@UnitTestData(sqlFiles = {
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_status.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_ORG_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_PERSON_ROLES.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_type.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_SUBMISSION_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_SUBMISSION_TYPE_QUALIFIER.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_protocol_review_type.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_REVIEWER_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_committee_type.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_ACTION_TYPE.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_MODULES.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/dml/load_SUBMISSION_STATUS.sql", delimiter = ";")
}))
public class ProtocolAmendRenewServiceTest extends KraTestBase {

    private static final String SUMMARY = "my test summary";
    
    private ProtocolAmendRenewServiceImpl protocolAmendRenewService;
    private BusinessObjectService businessObjectService;
    private DocumentService documentService;   
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("superuser"));
        protocolAmendRenewService = new ProtocolAmendRenewServiceImpl();
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        documentService = KraServiceLocator.getService(DocumentService.class);
        ProtocolCopyService copyService = KraServiceLocator.getService(ProtocolCopyService.class);
        protocolAmendRenewService.setDocumentService(documentService);
        protocolAmendRenewService.setProtocolCopyService(copyService);
        protocolAmendRenewService.setKraLookupDao(KraServiceLocator.getService(KraLookupDao.class));
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        super.tearDown();
    }
    
    @Test
    public void testAmendment() throws Exception {
        ProtocolAmendmentBean amendmentBean = new ProtocolAmendmentBean();
        amendmentBean.setAddModifyAttachments(true);
        amendmentBean.setProtocolOrganizations(true);
        amendmentBean.setSummary(SUMMARY);
        
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        String docNbr = protocolAmendRenewService.createAmendment(protocolDocument, amendmentBean);
        
        ProtocolDocument amendmentDocument = (ProtocolDocument) getDocumentService().getByDocumentHeaderId(docNbr);
    
        assertEquals(protocolDocument.getProtocol().getProtocolNumber() + "A001", amendmentDocument.getProtocol().getProtocolNumber());
        
        verifyAction(protocolDocument.getProtocol(), ProtocolActionType.AMENDMENT_CREATED, "Amendment 001 created.");
        verifyAmendmentRenewal(amendmentDocument.getProtocol(), SUMMARY, 2);
    }
    
    @Test
    public void testRenewal() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        String docNbr = protocolAmendRenewService.createRenewal(protocolDocument);
        
        ProtocolDocument amendmentDocument = (ProtocolDocument) getDocumentService().getByDocumentHeaderId(docNbr);
    
        assertEquals(protocolDocument.getProtocol().getProtocolNumber() + "R001", amendmentDocument.getProtocol().getProtocolNumber());
        
        verifyAction(protocolDocument.getProtocol(), ProtocolActionType.RENEWAL_CREATED, "Renewal 001 created.");
        verifyAmendmentRenewal(amendmentDocument.getProtocol(), null, 0);
    }
    
    @Test
    public void testRenewalWithAmendment() throws Exception {
        ProtocolAmendmentBean amendmentBean = new ProtocolAmendmentBean();
        amendmentBean.setAddModifyAttachments(true);
        amendmentBean.setProtocolOrganizations(true);
        amendmentBean.setSummary(SUMMARY);
        
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        String docNbr = protocolAmendRenewService.createRenewalWithAmendment(protocolDocument, amendmentBean);
        
        ProtocolDocument amendmentDocument = (ProtocolDocument) getDocumentService().getByDocumentHeaderId(docNbr);
    
        assertEquals(protocolDocument.getProtocol().getProtocolNumber() + "R001", amendmentDocument.getProtocol().getProtocolNumber());
        
        verifyAction(protocolDocument.getProtocol(), ProtocolActionType.RENEWAL_CREATED, "Renewal 001 created.");
        verifyAmendmentRenewal(amendmentDocument.getProtocol(), SUMMARY, 2);
    }
    
    private void verifyAmendmentRenewal(Protocol protocol, String expectedSummary, int moduleCount) {
        ProtocolAmendRenewal amendRenewal = findAmendRenewal(protocol.getProtocolId());
        assertEquals(amendRenewal.getProtocolId(), protocol.getProtocolId());
        assertEquals(expectedSummary, amendRenewal.getSummary());
        verifyModules(amendRenewal, moduleCount);
    }

    private void verifyModules(ProtocolAmendRenewal amendRenewal, int moduleCount) {
        List<ProtocolAmendRenewModule> modules = findModules(amendRenewal.getId());
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

    private void verifyAction(Protocol protocol, String expectedActionType, String expectedComment) {
        ProtocolAction action = findProtocolAction(protocol.getProtocolId());
        assertEquals(expectedActionType, action.getProtocolActionTypeCode());
        assertEquals(action.getProtocolId(), protocol.getProtocolId());
        assertEquals(null, action.getSubmissionIdFk());
        assertEquals(expectedComment, action.getComments());
    }

    @SuppressWarnings("unchecked")
    private ProtocolAction findProtocolAction(Long protocolId) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("protocolId", protocolId);
        List<ProtocolAction> actions = (List<ProtocolAction>) businessObjectService.findMatching(ProtocolAction.class, fieldValues);
        
        assertEquals(1, actions.size());
        ProtocolAction action = actions.get(0);
        return action;
    }
    
    @SuppressWarnings("unchecked")
    private ProtocolAmendRenewal findAmendRenewal(Long protocolId) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("protocolId", protocolId);
        List<ProtocolAmendRenewal> list = (List<ProtocolAmendRenewal>) businessObjectService.findMatching(ProtocolAmendRenewal.class, fieldValues);
        
        assertEquals(1, list.size());
        ProtocolAmendRenewal item = list.get(0);
        return item;
    }
    
    @SuppressWarnings("unchecked")
    private List<ProtocolAmendRenewModule> findModules(Long amendRenewId) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("protocolAmendRenewalId", amendRenewId);
        List<ProtocolAmendRenewModule> list = (List<ProtocolAmendRenewModule>) businessObjectService.findMatching(ProtocolAmendRenewModule.class, fieldValues);
        return list;
    }
    
    /**
     * Verify that the getAmendmentAndRenewals() method works.
     * @throws WorkflowException
     */
    @Test
    public void testGetAmendmentsAndRenewals() throws WorkflowException {
        ProtocolDocument a1 = ProtocolFactory.createProtocolDocument("0906000001A001");
        ProtocolDocument a2 = ProtocolFactory.createProtocolDocument("0906000001A002");
        ProtocolDocument r1 = ProtocolFactory.createProtocolDocument("0906000001R001");
        List<Protocol> protocols = protocolAmendRenewService.getAmendmentAndRenewals("0906000001");
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
        ProtocolAmendmentBean amendmentBean = new ProtocolAmendmentBean();
        amendmentBean.setAddModifyAttachments(true);
        amendmentBean.setAreasOfResearch(true);
        amendmentBean.setProtocolOrganizations(true);
        amendmentBean.setSummary(SUMMARY);
        
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument("0906000001");
        protocolAmendRenewService.createAmendment(protocolDocument, amendmentBean);
        
        List<String> modules = protocolAmendRenewService.getAvailableModules("0906000001");
        assertEquals(7, modules.size());
        assertTrue(modules.contains(ProtocolModule.FUNDING_SOURCE));
        assertTrue(modules.contains(ProtocolModule.GENERAL_INFO));
        assertTrue(modules.contains(ProtocolModule.OTHERS));
        assertTrue(modules.contains(ProtocolModule.PROTOCOL_PERSONNEL));
        assertTrue(modules.contains(ProtocolModule.PROTOCOL_REFERENCES));
        assertTrue(modules.contains(ProtocolModule.SPECIAL_REVIEW));
        assertTrue(modules.contains(ProtocolModule.SUBJECTS));
    }
}
