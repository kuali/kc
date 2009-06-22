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
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.copy.ProtocolCopyService;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.ErrorMap;
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
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_MODULES.sql", delimiter = ";")
}))
public class ProtocolAmendRenewServiceTest extends KraTestBase {

    private static final String SUMMARY = "my test summary";
    
    private ProtocolAmendRenewServiceImpl protocolAmendRenewService;
    private BusinessObjectService businessObjectService;   
    
    @SuppressWarnings("unchecked")
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("superuser"));
        GlobalVariables.setErrorMap(new ErrorMap());
        GlobalVariables.setAuditErrorMap(new HashMap());
        protocolAmendRenewService = new ProtocolAmendRenewServiceImpl();
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        ProtocolCopyService copyService = KraServiceLocator.getService(ProtocolCopyService.class);
        protocolAmendRenewService.setBusinessObjectService(businessObjectService);
        protocolAmendRenewService.setProtocolCopyService(copyService);
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        GlobalVariables.setErrorMap(null);
        GlobalVariables.setAuditErrorMap(null);
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
        
        verifyAction(protocolDocument.getProtocol(), ProtocolActionType.AMENDMENT_CREATED);
        verifyAmendmentRenewal(amendmentDocument.getProtocol(), SUMMARY, 2);
    }
    
    @Test
    public void testRenewal() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();
        String docNbr = protocolAmendRenewService.createRenewal(protocolDocument);
        
        ProtocolDocument amendmentDocument = (ProtocolDocument) getDocumentService().getByDocumentHeaderId(docNbr);
    
        assertEquals(protocolDocument.getProtocol().getProtocolNumber() + "R001", amendmentDocument.getProtocol().getProtocolNumber());
        
        verifyAction(protocolDocument.getProtocol(), ProtocolActionType.RENEWAL_CREATED);
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
        
        verifyAction(protocolDocument.getProtocol(), ProtocolActionType.RENEWAL_CREATED);
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

    private void verifyAction(Protocol protocol, String expectedActionType) {
        ProtocolAction action = findProtocolAction(protocol.getProtocolId());
        assertEquals(expectedActionType, action.getProtocolActionTypeCode());
        assertEquals(action.getProtocolId(), protocol.getProtocolId());
        assertEquals(null, action.getSubmissionIdFk());
        assertEquals(null, action.getComments());
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
}
