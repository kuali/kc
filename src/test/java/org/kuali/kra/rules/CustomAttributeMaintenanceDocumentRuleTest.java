/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.rules;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.RiceKeyConstants;
import org.kuali.core.UserSession;
import org.kuali.core.document.MaintenanceDocument;
import org.kuali.core.util.ErrorMessage;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.TypedArrayList;
import org.kuali.kra.bo.CustomAttribute;
import org.kuali.kra.maintenance.MaintenanceRuleTestBase;

public class CustomAttributeMaintenanceDocumentRuleTest extends MaintenanceRuleTestBase {
    private CustomAttributeMaintenanceDocumentRule rule = null;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new CustomAttributeMaintenanceDocumentRule();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        super.tearDown();
    }

    /**
     * 
     * This method test both lookupclass and lookupreturn are specified
     * @throws Exception
     */
    @Test
    public void testOK() throws Exception {

        CustomAttribute customAttribute = new CustomAttribute();
        customAttribute.setLookupClass("org.kuali.kra.bo.UserRole");
        customAttribute.setLookupReturn("roleId");
        MaintenanceDocument customAttributeDocument = newMaintDoc(customAttribute);
        assertTrue(rule.processCustomRouteDocumentBusinessRules(customAttributeDocument));
        assertTrue(rule.processCustomApproveDocumentBusinessRules(customAttributeDocument));
    }

    /**
     * 
     * This method to test lookupreturn is not specified and lookupclass is selected.
     * @throws Exception
     */
    @Test
    public void testUnspecifiedLookupReturn() throws Exception {

        CustomAttribute customAttribute = new CustomAttribute();
        customAttribute.setLookupClass("org.kuali.kra.bo.UserRole");
        //customAttribute.setLookupReturn("roleId");
        MaintenanceDocument customAttributeDocument = newMaintDoc(customAttribute);
        assertFalse(rule.processCustomRouteDocumentBusinessRules(customAttributeDocument));
        TypedArrayList errors = GlobalVariables.getErrorMap().getMessages("document.newMaintainableObject.lookupReturn");
        assertTrue(errors.size() == 1);
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), RiceKeyConstants.ERROR_REQUIRED);

        // approve will have the same error too.
        assertFalse(rule.processCustomApproveDocumentBusinessRules(customAttributeDocument));
        errors = GlobalVariables.getErrorMap().getMessages("document.newMaintainableObject.lookupReturn");
        assertTrue(errors.size() == 1);
        message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), RiceKeyConstants.ERROR_REQUIRED);

    }

}
