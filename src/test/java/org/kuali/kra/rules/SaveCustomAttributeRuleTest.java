/*
 * Copyright 2005-2010 The Kuali Foundation
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
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.infrastructure.TestUtilities;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rules.ProposalDevelopmentRuleTestBase;
import org.kuali.kra.proposaldevelopment.service.ProposalRoleTemplateService;
import org.kuali.kra.rule.event.SaveCustomAttributeEvent;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.util.AutoPopulatingList;


// TODO : temporary extends ProposalDevelopmentRuleTestBase to test proposal document custom data 
// need more generic class for extension, so we can test other modules, such as budget too
public class SaveCustomAttributeRuleTest extends ProposalDevelopmentRuleTestBase {
    private SaveCustomAttributeRule rule = null;
    private BusinessObjectService bos;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new SaveCustomAttributeRule();
        bos = KraServiceLocator.getService(BusinessObjectService.class);
    }

    @Override
    @After
    public void tearDown() throws Exception {
        rule = null;
        bos = null;
        super.tearDown();
    }



    /**
     * Test a good case.
     * 
     * @throws Exception
     */
    @Test
    public void testOK() throws Exception {

        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        document.setCustomAttributeDocuments(TestUtilities.setupTestCustomAttributeDocuments());

        document.getCustomAttributeDocuments().get("1").getCustomAttribute().setValue(TestUtilities.BILLING_ELEMENT_VALUE);
        document.getCustomAttributeDocuments().get("4").getCustomAttribute().setValue(TestUtilities.GRADUATE_STUDENT_COUNT_VALUE);
        document.getCustomAttributeDocuments().get("8").getCustomAttribute().setValue(TestUtilities.LOCAL_REVIEW_DATE_VALUE);
        SaveCustomAttributeEvent saveCustomAttributeEvent = new SaveCustomAttributeEvent(Constants.EMPTY_STRING, document);
        assertTrue(rule.processRules(saveCustomAttributeEvent));
    }

    @Test
    public void testUnspecifiedRequiredField() throws Exception {

        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        document.setCustomAttributeDocuments(TestUtilities.setupTestCustomAttributeDocuments());

        //billing element is not set
        document.getCustomAttributeDocuments().get("4").getCustomAttribute().setValue(TestUtilities.GRADUATE_STUDENT_COUNT_VALUE);
        document.getCustomAttributeDocuments().get("8").getCustomAttribute().setValue(TestUtilities.LOCAL_REVIEW_DATE_VALUE);
        SaveCustomAttributeEvent saveCustomAttributeEvent = new SaveCustomAttributeEvent(Constants.EMPTY_STRING, document);
        assertTrue(rule.processRules(saveCustomAttributeEvent));
        
        UserSession currentSession = GlobalVariables.getUserSession();
        PessimisticLock lock = KRADServiceLocatorWeb.getPessimisticLockService().generateNewLock(document.getDocumentNumber(), "PROPOSAL-"+document.getDocumentNumber(), currentSession.getPerson());
        document.addPessimisticLock(lock);
        
        String userId = GlobalVariables.getUserSession().getPrincipalId();
        KraAuthorizationService kraAuthService = KraServiceLocator.getService(KraAuthorizationService.class);
        kraAuthService.addRole(userId, RoleConstants.AGGREGATOR, document);
        ProposalRoleTemplateService proposalRoleTemplateService = KraServiceLocator.getService(ProposalRoleTemplateService.class);
        proposalRoleTemplateService.addUsers(document);

        try {
            KraServiceLocator.getService(DocumentService.class).saveDocument(document);
            KraServiceLocator.getService(DocumentService.class).routeDocument(document, "just testing", null);
        }
        catch (org.kuali.rice.krad.exception.ValidationException ex) {
            assertEquals(ex.getMessage(), "business rule evaluation failed");
        }

    }

    @Test
    public void testInvalidNumberFormat() throws Exception {

        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        document.setCustomAttributeDocuments(TestUtilities.setupTestCustomAttributeDocuments());

        document.getCustomAttributeDocuments().get("1").getCustomAttribute().setValue(TestUtilities.BILLING_ELEMENT_VALUE);
        document.getCustomAttributeDocuments().get("4").getCustomAttribute().setValue(TestUtilities.GRADUATE_STUDENT_COUNT_VALUE+"a");
        document.getCustomAttributeDocuments().get("8").getCustomAttribute().setValue(TestUtilities.LOCAL_REVIEW_DATE_VALUE);
        SaveCustomAttributeEvent saveCustomAttributeEvent = new SaveCustomAttributeEvent(Constants.EMPTY_STRING, document);
        assertFalse(rule.processRules(saveCustomAttributeEvent));
        AutoPopulatingList errors = GlobalVariables.getMessageMap().getMessages("customAttributeValues(id4)");
        assertTrue(errors.size() == 1);
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(KeyConstants.ERROR_INVALID_FORMAT_WITH_FORMAT, message.getErrorKey());
    }
    
    @Test
    public void testInvalidLength() throws Exception {

        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        document.setCustomAttributeDocuments(TestUtilities.setupTestCustomAttributeDocuments());

        document.getCustomAttributeDocuments().get("1").getCustomAttribute().setValue(TestUtilities.BILLING_ELEMENT_VALUE+"123456789012345678901234567890");
        document.getCustomAttributeDocuments().get("4").getCustomAttribute().setValue(TestUtilities.GRADUATE_STUDENT_COUNT_VALUE);
        document.getCustomAttributeDocuments().get("8").getCustomAttribute().setValue(TestUtilities.LOCAL_REVIEW_DATE_VALUE);
        SaveCustomAttributeEvent saveCustomAttributeEvent = new SaveCustomAttributeEvent(Constants.EMPTY_STRING, document);
        assertFalse(rule.processRules(saveCustomAttributeEvent));
        AutoPopulatingList errors = GlobalVariables.getMessageMap().getMessages("customAttributeValues(id1)");
        assertTrue(errors.size() == 1);
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), RiceKeyConstants.ERROR_MAX_LENGTH);
    }

    @Test
    public void testInvalidDateFormat() throws Exception {

        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        document.setCustomAttributeDocuments(TestUtilities.setupTestCustomAttributeDocuments());

        document.getCustomAttributeDocuments().get("1").getCustomAttribute().setValue(TestUtilities.BILLING_ELEMENT_VALUE);
        document.getCustomAttributeDocuments().get("4").getCustomAttribute().setValue(TestUtilities.GRADUATE_STUDENT_COUNT_VALUE);
        document.getCustomAttributeDocuments().get("8").getCustomAttribute().setValue("2008-02-08");
        SaveCustomAttributeEvent saveCustomAttributeEvent = new SaveCustomAttributeEvent(Constants.EMPTY_STRING, document);
        assertFalse(rule.processRules(saveCustomAttributeEvent));
        AutoPopulatingList errors = GlobalVariables.getMessageMap().getMessages("customAttributeValues(id8)");
        assertTrue(errors.size() == 1);
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(RiceKeyConstants.ERROR_INVALID_FORMAT, message.getErrorKey());
    }
    
    @Test
    public void testInvalidDateValue() throws Exception {

        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        document.setCustomAttributeDocuments(TestUtilities.setupTestCustomAttributeDocuments());

        document.getCustomAttributeDocuments().get("1").getCustomAttribute().setValue(TestUtilities.BILLING_ELEMENT_VALUE);
        document.getCustomAttributeDocuments().get("4").getCustomAttribute().setValue(TestUtilities.GRADUATE_STUDENT_COUNT_VALUE);
        document.getCustomAttributeDocuments().get("8").getCustomAttribute().setValue("02/29/2010");
        SaveCustomAttributeEvent saveCustomAttributeEvent = new SaveCustomAttributeEvent(Constants.EMPTY_STRING, document);
        assertFalse(rule.processRules(saveCustomAttributeEvent));
        AutoPopulatingList errors = GlobalVariables.getMessageMap().getMessages("customAttributeValues(id8)");
        assertTrue(errors.size() == 1);
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(KeyConstants.ERROR_DATE, message.getErrorKey());
    }


    
}
