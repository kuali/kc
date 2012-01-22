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
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.maintenance.MaintenanceRuleTestBase;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.util.AutoPopulatingList;

public class CustomAttributeDocumentMaintenanceDocumentRuleTest extends MaintenanceRuleTestBase {
    private CustomAttributeDocumentMaintenanceDocumentRule rule = null;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new CustomAttributeDocumentMaintenanceDocumentRule();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        super.tearDown();
    }

    /**
     * 
     * This method test both custom attribute that has not been referenced by any doc
     * @throws Exception
     */
    @Test
    public void testOK() throws Exception {

        CustomAttributeDocument customAttributeDocument = new CustomAttributeDocument();
        customAttributeDocument.setCustomAttributeId(new Integer(1));
        customAttributeDocument.setActive(true);
        MaintenanceDocument customAttributeDocumentMaintDoc = newMaintDoc(customAttributeDocument);
        assertTrue(rule.processCustomRouteDocumentBusinessRules(customAttributeDocumentMaintDoc));
        assertTrue(rule.processCustomApproveDocumentBusinessRules(customAttributeDocumentMaintDoc));
    }

    /**
     * 
     * This method to test to check if it ok to inactivate a customattribute doc.
     * @throws Exception
     */
    @Test
    public void testOkToInactivate() throws Exception {

        CustomAttributeDocument customAttributeDocument = new CustomAttributeDocument();
        customAttributeDocument.setCustomAttributeId(new Integer(8));
        MaintenanceDocument customAttributeDocumentMaintDoc = newMaintDoc(customAttributeDocument);
        assertTrue(rule.processCustomRouteDocumentBusinessRules(customAttributeDocumentMaintDoc));

        // approve will have the same error too.
        assertTrue(rule.processCustomApproveDocumentBusinessRules(customAttributeDocumentMaintDoc));

    }

    @Test
    public void testInvalidCustomAttributeId() throws Exception {

        CustomAttributeDocument customAttributeDocument = new CustomAttributeDocument();
        customAttributeDocument.setCustomAttributeId(new Integer(99));
        MaintenanceDocument customAttributeDocumentMaintDoc = newMaintDoc(customAttributeDocument);
        assertFalse(rule.processCustomRouteDocumentBusinessRules(customAttributeDocumentMaintDoc));
        AutoPopulatingList errors = GlobalVariables.getMessageMap().getMessages(Constants.DOCUMENT_NEWMAINTAINABLEOBJECT_CUSTOM_ATTRIBUTE_ID);
        assertTrue(errors.size() == 1);
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), KeyConstants.ERROR_INVALID_CUSTOM_ATT_ID);

        // approve will have the same error too.
        assertFalse(rule.processCustomApproveDocumentBusinessRules(customAttributeDocumentMaintDoc));
        errors = GlobalVariables.getMessageMap().getMessages(Constants.DOCUMENT_NEWMAINTAINABLEOBJECT_CUSTOM_ATTRIBUTE_ID);
        assertTrue(errors.size() == 1);
        message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), KeyConstants.ERROR_INVALID_CUSTOM_ATT_ID);

    }

}
