/*
 * Copyright 2006-2008 The Kuali Foundation
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
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TestUtilities;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rules.ProposalDevelopmentRuleTestBase;
import org.kuali.kra.rule.event.SaveCustomAttributeEvent;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.document.authorization.PessimisticLock;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.util.ErrorMessage;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.RiceKeyConstants;
import org.kuali.rice.kns.util.TypedArrayList;


// TODO : temporary extends ProposalDevelopmentRuleTestBase to test proposal document custom data 
// need more generic class for extension, so we can test other modules, such as budget too
public class KraCustomAttributeRulesTest extends ProposalDevelopmentRuleTestBase {
    private KraCustomAttributeRule rule = null;
    private BusinessObjectService bos;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new KraCustomAttributeRule();
        bos = KraServiceLocator.getService(BusinessObjectService.class);
    }

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
        assertTrue(rule.processCustomAttributeRules(saveCustomAttributeEvent));
    }

    @Test
    public void testUnspecifiedRequiredField() throws Exception {

        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        document.setCustomAttributeDocuments(TestUtilities.setupTestCustomAttributeDocuments());

        //billing element is not set
        document.getCustomAttributeDocuments().get("4").getCustomAttribute().setValue(TestUtilities.GRADUATE_STUDENT_COUNT_VALUE);
        document.getCustomAttributeDocuments().get("8").getCustomAttribute().setValue(TestUtilities.LOCAL_REVIEW_DATE_VALUE);
        SaveCustomAttributeEvent saveCustomAttributeEvent = new SaveCustomAttributeEvent(Constants.EMPTY_STRING, document);
        assertTrue(rule.processCustomAttributeRules(saveCustomAttributeEvent));
        
        UserSession currentSession = GlobalVariables.getUserSession();
        PessimisticLock lock = KNSServiceLocator.getPessimisticLockService().generateNewLock(document.getDocumentNumber(), "PROPOSAL-"+document.getDocumentNumber(), currentSession.getPerson());
        document.addPessimisticLock(lock);
        
        try {
            KraServiceLocator.getService(DocumentService.class).routeDocument(document, "just testing", null);
        }
        catch (org.kuali.rice.kns.exception.ValidationException ex) {
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
        assertFalse(rule.processCustomAttributeRules(saveCustomAttributeEvent));
        TypedArrayList errors = GlobalVariables.getErrorMap().getMessages("customAttributeValues(id4)");
        assertTrue(errors.size() == 1);
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), RiceKeyConstants.ERROR_INVALID_FORMAT);
    }
    
    @Test
    public void testInvalidLength() throws Exception {

        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        document.setCustomAttributeDocuments(TestUtilities.setupTestCustomAttributeDocuments());

        document.getCustomAttributeDocuments().get("1").getCustomAttribute().setValue(TestUtilities.BILLING_ELEMENT_VALUE+"123456789012345678901234567890");
        document.getCustomAttributeDocuments().get("4").getCustomAttribute().setValue(TestUtilities.GRADUATE_STUDENT_COUNT_VALUE);
        document.getCustomAttributeDocuments().get("8").getCustomAttribute().setValue(TestUtilities.LOCAL_REVIEW_DATE_VALUE);
        SaveCustomAttributeEvent saveCustomAttributeEvent = new SaveCustomAttributeEvent(Constants.EMPTY_STRING, document);
        assertFalse(rule.processCustomAttributeRules(saveCustomAttributeEvent));
        TypedArrayList errors = GlobalVariables.getErrorMap().getMessages("customAttributeValues(id1)");
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
        assertFalse(rule.processCustomAttributeRules(saveCustomAttributeEvent));
        TypedArrayList errors = GlobalVariables.getErrorMap().getMessages("customAttributeValues(id8)");
        assertTrue(errors.size() == 1);
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), RiceKeyConstants.ERROR_INVALID_FORMAT);
    }


    
}
