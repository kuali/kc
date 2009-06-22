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
package org.kuali.kra.irb.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewModule;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewal;
import org.kuali.kra.irb.actions.amendrenew.ProtocolModule;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Test the Create Amendment/Renewal actions.
 */
@PerSuiteUnitTestData(
        @UnitTestData(
            sqlFiles = {
                @UnitTestFile(filename = "classpath:sql/dml/load_SUBMISSION_TYPE.sql", delimiter = ";")
               ,@UnitTestFile(filename = "classpath:sql/dml/load_protocol_review_type.sql", delimiter = ";")
               ,@UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_ACTION_TYPE.sql", delimiter = ";")
               ,@UnitTestFile(filename = "classpath:sql/dml/load_SUBMISSION_TYPE_QUALIFIER.sql", delimiter = ";")
               ,@UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_MODULES.sql", delimiter = ";")
            }
        )
    )
public class ProtocolAmendRenewWebTest extends ProtocolWebTestBase {

     private static final String SUMMARY = "this is a summary";
    
    private BusinessObjectService businessObjectService;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();    
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
    }
   
    @Test
    public void testCreateAmendment() throws Exception {
        HtmlPage protocolPage = getProtocolSavedRequiredFieldsPage();
        String docNbr = this.getDocNbr(protocolPage);
        ProtocolDocument protocolDocument = (ProtocolDocument) getDocument(docNbr);
        
        HtmlPage protocolActionsPage = clickOnTab(protocolPage, PROTOCOL_ACTIONS_LINK_NAME);
        
        setFieldValue(protocolActionsPage, "actionHelper.protocolAmendmentBean.summary", SUMMARY);
        setFieldValue(protocolActionsPage, "actionHelper.protocolAmendmentBean.fundingSource", "on");
        setFieldValue(protocolActionsPage, "actionHelper.protocolAmendmentBean.others", "on");
        
        HtmlPage resultPage = clickOn(protocolActionsPage, "methodToCall.createAmendment.anchor:CreateAmendment");
        
        assertNotNull(resultPage);
        assertDoesNotContain(resultPage, ERRORS_FOUND_ON_PAGE);
        
        docNbr = this.getDocNbr(resultPage);
        ProtocolDocument amendmentDocument = (ProtocolDocument) getDocument(docNbr);
        
        assertEquals(protocolDocument.getProtocol().getProtocolNumber() + "A001", amendmentDocument.getProtocol().getProtocolNumber());
        
        verifyAction(protocolDocument.getProtocol(), ProtocolActionType.AMENDMENT_CREATED);
        verifyAmendmentRenewal(amendmentDocument.getProtocol(), SUMMARY, 2);
    }
    
    @Test
    public void testCreateRenewal() throws Exception {
        HtmlPage protocolPage = getProtocolSavedRequiredFieldsPage();
        String docNbr = this.getDocNbr(protocolPage);
        ProtocolDocument protocolDocument = (ProtocolDocument) getDocument(docNbr);
        
        HtmlPage protocolActionsPage = clickOnTab(protocolPage, PROTOCOL_ACTIONS_LINK_NAME);
         
        HtmlPage resultPage = clickOn(protocolActionsPage, "methodToCall.createRenewal.anchor:CreateRenewalwithoutAmendment");
        
        assertNotNull(resultPage);
        assertDoesNotContain(resultPage, ERRORS_FOUND_ON_PAGE);
        
        docNbr = this.getDocNbr(resultPage);
        ProtocolDocument amendmentDocument = (ProtocolDocument) getDocument(docNbr);
        
        assertEquals(protocolDocument.getProtocol().getProtocolNumber() + "R001", amendmentDocument.getProtocol().getProtocolNumber());
        
        verifyAction(protocolDocument.getProtocol(), ProtocolActionType.RENEWAL_CREATED);
        verifyAmendmentRenewal(amendmentDocument.getProtocol(), null, 0);
    }
    
    @Test
    public void testCreateRenewalWithAmendment() throws Exception {
        HtmlPage protocolPage = getProtocolSavedRequiredFieldsPage();
        String docNbr = this.getDocNbr(protocolPage);
        ProtocolDocument protocolDocument = (ProtocolDocument) getDocument(docNbr);
        
        HtmlPage protocolActionsPage = clickOnTab(protocolPage, PROTOCOL_ACTIONS_LINK_NAME);
        
        setFieldValue(protocolActionsPage, "actionHelper.protocolRenewAmendmentBean.summary", SUMMARY);
        setFieldValue(protocolActionsPage, "actionHelper.protocolRenewAmendmentBean.fundingSource", "on");
        setFieldValue(protocolActionsPage, "actionHelper.protocolRenewAmendmentBean.others", "on");
        
        HtmlPage resultPage = clickOn(protocolActionsPage, "methodToCall.createRenewalWithAmendment.anchor:CreateRenewalwithAmendment");
        
        assertNotNull(resultPage);
        assertDoesNotContain(resultPage, ERRORS_FOUND_ON_PAGE);
        
        docNbr = this.getDocNbr(resultPage);
        ProtocolDocument amendmentDocument = (ProtocolDocument) getDocument(docNbr);
        
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
            assertContains(ProtocolModule.FUNDING_SOURCE, modules);
            assertContains(ProtocolModule.OTHERS, modules);
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
