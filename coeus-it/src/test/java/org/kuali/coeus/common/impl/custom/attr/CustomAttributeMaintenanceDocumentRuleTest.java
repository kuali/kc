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
package org.kuali.coeus.common.impl.custom.attr;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.custom.attr.CustomAttribute;
import org.kuali.coeus.common.impl.custom.attr.CustomAttributeMaintenanceDocumentRule;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.maintenance.MaintenanceRuleTestBase;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.List;
import static org.junit.Assert.*;
public class CustomAttributeMaintenanceDocumentRuleTest extends MaintenanceRuleTestBase {
    private CustomAttributeMaintenanceDocumentRule rule = null;

    @Before
    public void setUp() throws Exception {
        rule = new CustomAttributeMaintenanceDocumentRule();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
    }

    /**
     * 
     * This method test both lookupclass and lookupreturn are specified
     * @throws Exception
     */
    @Test
    public void testOK() throws Exception {

        CustomAttribute customAttribute = new CustomAttribute();
        customAttribute.setLookupClass("org.kuali.kra.bo.DegreeType");
        customAttribute.setLookupReturn("degreeCode");
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
        customAttribute.setLookupClass("org.kuali.kra.bo.DegreeType");
        //customAttribute.setLookupReturn("roleId");
        MaintenanceDocument customAttributeDocument = newMaintDoc(customAttribute);
        assertFalse(rule.processCustomRouteDocumentBusinessRules(customAttributeDocument));
        List errors = GlobalVariables.getMessageMap().getMessages(Constants.DOCUMENT_NEWMAINTAINABLEOBJECT_LOOKUPRETURN);
        assertTrue(errors.size() == 1);
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), RiceKeyConstants.ERROR_REQUIRED);

        // approve will have the same error too.
        assertFalse(rule.processCustomApproveDocumentBusinessRules(customAttributeDocument));
        errors = GlobalVariables.getMessageMap().getMessages(Constants.DOCUMENT_NEWMAINTAINABLEOBJECT_LOOKUPRETURN);
        assertTrue(errors.size() == 1);
        message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), RiceKeyConstants.ERROR_REQUIRED);

    }

}
