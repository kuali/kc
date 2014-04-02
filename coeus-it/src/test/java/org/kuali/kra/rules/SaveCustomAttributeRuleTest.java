/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.rules;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocValue;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument;
import org.kuali.coeus.common.impl.custom.CustomDataRule;
import org.kuali.coeus.propdev.impl.auth.perm.ProposalRoleTemplateService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.CustomAttributeDocumentTestUtilities;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.proposaldevelopment.rules.ProposalDevelopmentRuleTestBase;
import org.kuali.kra.rule.event.SaveCustomDataEvent;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

// TODO : temporary extends ProposalDevelopmentRuleTestBase to test proposal document custom data 
// need more generic class for extension, so we can test other modules, such as budget too
public class SaveCustomAttributeRuleTest extends ProposalDevelopmentRuleTestBase {
    private CustomDataRule rule = null;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new CustomDataRule();
    }

    @Override
    @After
    public void tearDown() throws Exception {
        rule = null;
        super.tearDown();
    }


    protected void setCustomAttributeValue(ProposalDevelopmentDocument document, CustomAttributeDocument customAttribute, String value) {
        CustomAttributeDocValue newValue = new CustomAttributeDocValue();
        newValue.setCustomAttribute(customAttribute.getCustomAttribute());
        newValue.setCustomAttributeId(customAttribute.getCustomAttributeId().longValue());
        newValue.setValue(value);
        document.getCustomDataList().add(newValue);
    }

    /**
     * Test a good case.
     * 
     * @throws Exception
     */
    @Test
    public void testOK() throws Exception {

        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        document.setCustomAttributeDocuments(CustomAttributeDocumentTestUtilities.setupTestCustomAttributeDocuments());

        setCustomAttributeValue(document, document.getCustomAttributeDocuments().get("1"), CustomAttributeDocumentTestUtilities.BILLING_ELEMENT_VALUE);
        setCustomAttributeValue(document, document.getCustomAttributeDocuments().get("4"), CustomAttributeDocumentTestUtilities.GRADUATE_STUDENT_COUNT_VALUE);
        setCustomAttributeValue(document, document.getCustomAttributeDocuments().get("8"), CustomAttributeDocumentTestUtilities.LOCAL_REVIEW_DATE_VALUE);
        setCustomAttributeValue(document, document.getCustomAttributeDocuments().get("9"), "Yes");
        setCustomAttributeValue(document, document.getCustomAttributeDocuments().get("5"), "quickstart");
        SaveCustomDataEvent saveCustomAttributeEvent = new SaveCustomDataEvent(document);
        assertTrue(rule.processRules(saveCustomAttributeEvent));
    }

    @Test
    public void testUnspecifiedRequiredField() throws Exception {

        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        document.setCustomAttributeDocuments(CustomAttributeDocumentTestUtilities.setupTestCustomAttributeDocuments());

        //billing element is not set
        setCustomAttributeValue(document, document.getCustomAttributeDocuments().get("4"), CustomAttributeDocumentTestUtilities.GRADUATE_STUDENT_COUNT_VALUE);
        setCustomAttributeValue(document, document.getCustomAttributeDocuments().get("8"), CustomAttributeDocumentTestUtilities.LOCAL_REVIEW_DATE_VALUE);
        SaveCustomDataEvent saveCustomAttributeEvent = new SaveCustomDataEvent(document);
        assertTrue(rule.processRules(saveCustomAttributeEvent));
        
        UserSession currentSession = GlobalVariables.getUserSession();
        String kualiSessionId = currentSession.getKualiSessionId();
        if (kualiSessionId == null) {
            kualiSessionId = UUID.randomUUID().toString();
            currentSession.setKualiSessionId(kualiSessionId);
        }
        PessimisticLock lock = KRADServiceLocatorWeb.getPessimisticLockService().generateNewLock(document.getDocumentNumber(), "PROPOSAL-"+document.getDocumentNumber(), currentSession.getPerson());
        document.addPessimisticLock(lock);
        
        String userId = GlobalVariables.getUserSession().getPrincipalId();
        KcAuthorizationService kraAuthService = KcServiceLocator.getService(KcAuthorizationService.class);

        try {
            KcServiceLocator.getService(DocumentService.class).saveDocument(document);
            if(!kraAuthService.hasRole(userId, document, RoleConstants.AGGREGATOR)) {
                kraAuthService.addRole(userId, RoleConstants.AGGREGATOR, document);
            }
            ProposalRoleTemplateService proposalRoleTemplateService = KcServiceLocator.getService(ProposalRoleTemplateService.class);
            proposalRoleTemplateService.addUsers(document);

            KcServiceLocator.getService(DocumentService.class).routeDocument(document, "just testing", null);
        }
        catch (org.kuali.rice.krad.exception.ValidationException ex) {
            assertEquals(ex.getMessage(), "business rule evaluation failed");
        }

    }

    @Test
    public void testInvalidNumberFormat() throws Exception {

        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        document.setCustomAttributeDocuments(CustomAttributeDocumentTestUtilities.setupTestCustomAttributeDocuments());

        setCustomAttributeValue(document, document.getCustomAttributeDocuments().get("1"), CustomAttributeDocumentTestUtilities.BILLING_ELEMENT_VALUE);
        setCustomAttributeValue(document, document.getCustomAttributeDocuments().get("4"), CustomAttributeDocumentTestUtilities.GRADUATE_STUDENT_COUNT_VALUE+"a");
        setCustomAttributeValue(document, document.getCustomAttributeDocuments().get("8"), CustomAttributeDocumentTestUtilities.LOCAL_REVIEW_DATE_VALUE);
        SaveCustomDataEvent saveCustomAttributeEvent = new SaveCustomDataEvent(document);
        assertFalse(rule.processRules(saveCustomAttributeEvent));
        List errors = GlobalVariables.getMessageMap().getMessages("customDataHelper.customDataList[1].value");
        assertTrue(errors.size() == 1);
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(KeyConstants.ERROR_INVALID_FORMAT_WITH_FORMAT, message.getErrorKey());
    }
    
    @Test
    public void testInvalidLength() throws Exception {

        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        document.setCustomAttributeDocuments(CustomAttributeDocumentTestUtilities.setupTestCustomAttributeDocuments());

        setCustomAttributeValue(document, document.getCustomAttributeDocuments().get("1"), CustomAttributeDocumentTestUtilities.BILLING_ELEMENT_VALUE+"123456789012345678901234567890");
        setCustomAttributeValue(document, document.getCustomAttributeDocuments().get("4"), CustomAttributeDocumentTestUtilities.GRADUATE_STUDENT_COUNT_VALUE);
        setCustomAttributeValue(document, document.getCustomAttributeDocuments().get("8"), CustomAttributeDocumentTestUtilities.LOCAL_REVIEW_DATE_VALUE);
        SaveCustomDataEvent saveCustomAttributeEvent = new SaveCustomDataEvent(document);
        assertFalse(rule.processRules(saveCustomAttributeEvent));
        List errors = GlobalVariables.getMessageMap().getMessages("customDataHelper.customDataList[0].value");
        assertTrue(errors.size() == 1);
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), RiceKeyConstants.ERROR_MAX_LENGTH);
    }

    @Test
    public void testInvalidDateFormat() throws Exception {

        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        document.setCustomAttributeDocuments(CustomAttributeDocumentTestUtilities.setupTestCustomAttributeDocuments());

        
        setCustomAttributeValue(document, document.getCustomAttributeDocuments().get("1"), CustomAttributeDocumentTestUtilities.BILLING_ELEMENT_VALUE);
        setCustomAttributeValue(document, document.getCustomAttributeDocuments().get("4"), CustomAttributeDocumentTestUtilities.GRADUATE_STUDENT_COUNT_VALUE);
        setCustomAttributeValue(document, document.getCustomAttributeDocuments().get("8"), "2008-02-08");
        SaveCustomDataEvent saveCustomAttributeEvent = new SaveCustomDataEvent(document);
        assertFalse(rule.processRules(saveCustomAttributeEvent));
        List errors = GlobalVariables.getMessageMap().getMessages("customDataHelper.customDataList[2].value");
        assertTrue(errors.size() == 1);
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(RiceKeyConstants.ERROR_INVALID_FORMAT, message.getErrorKey());
    }
    
    @Test
    public void testInvalidDateValue() throws Exception {

        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        document.setCustomAttributeDocuments(CustomAttributeDocumentTestUtilities.setupTestCustomAttributeDocuments());

        setCustomAttributeValue(document, document.getCustomAttributeDocuments().get("1"), CustomAttributeDocumentTestUtilities.BILLING_ELEMENT_VALUE);
        setCustomAttributeValue(document, document.getCustomAttributeDocuments().get("4"), CustomAttributeDocumentTestUtilities.GRADUATE_STUDENT_COUNT_VALUE);
        setCustomAttributeValue(document, document.getCustomAttributeDocuments().get("8"), "02/29/2010");
        SaveCustomDataEvent saveCustomAttributeEvent = new SaveCustomDataEvent(document);
        assertFalse(rule.processRules(saveCustomAttributeEvent));
        List errors = GlobalVariables.getMessageMap().getMessages("customDataHelper.customDataList[2].value");
        assertTrue(errors.size() == 1);
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(KeyConstants.ERROR_DATE, message.getErrorKey());
    }
    
    @Test
    public void testInvalidPerson() throws Exception {

        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        document.setCustomAttributeDocuments(CustomAttributeDocumentTestUtilities.setupTestCustomAttributeDocuments());

        setCustomAttributeValue(document, document.getCustomAttributeDocuments().get("1"), CustomAttributeDocumentTestUtilities.BILLING_ELEMENT_VALUE);
        setCustomAttributeValue(document, document.getCustomAttributeDocuments().get("4"), CustomAttributeDocumentTestUtilities.GRADUATE_STUDENT_COUNT_VALUE);
        setCustomAttributeValue(document, document.getCustomAttributeDocuments().get("8"), CustomAttributeDocumentTestUtilities.LOCAL_REVIEW_DATE_VALUE);
        setCustomAttributeValue(document, document.getCustomAttributeDocuments().get("5"), "noarealperson");
        SaveCustomDataEvent saveCustomAttributeEvent = new SaveCustomDataEvent(document);
        assertFalse(rule.processRules(saveCustomAttributeEvent));
        List errors = GlobalVariables.getMessageMap().getMessages("customDataHelper.customDataList[3].value");
        assertTrue(errors.size() == 1);
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(RiceKeyConstants.ERROR_EXISTENCE, message.getErrorKey());
    }  
    
    @Test
    public void testInvalidLookup() throws Exception {

        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        document.setCustomAttributeDocuments(CustomAttributeDocumentTestUtilities.setupTestCustomAttributeDocuments());

        setCustomAttributeValue(document, document.getCustomAttributeDocuments().get("1"), CustomAttributeDocumentTestUtilities.BILLING_ELEMENT_VALUE);
        setCustomAttributeValue(document, document.getCustomAttributeDocuments().get("4"), CustomAttributeDocumentTestUtilities.GRADUATE_STUDENT_COUNT_VALUE);
        setCustomAttributeValue(document, document.getCustomAttributeDocuments().get("8"), CustomAttributeDocumentTestUtilities.LOCAL_REVIEW_DATE_VALUE);
        setCustomAttributeValue(document, document.getCustomAttributeDocuments().get("9"), "Uh");
        SaveCustomDataEvent saveCustomAttributeEvent = new SaveCustomDataEvent(document);
        assertFalse(rule.processRules(saveCustomAttributeEvent));
        List errors = GlobalVariables.getMessageMap().getMessages("customDataHelper.customDataList[3].value");
        assertTrue(errors.size() == 1);
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(RiceKeyConstants.ERROR_EXISTENCE, message.getErrorKey());
    }      


    
}
