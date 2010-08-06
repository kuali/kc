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
package org.kuali.kra.bo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.rules.KcPersonExtendedAttributesPersonIdExistenceRule;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.maintenance.MaintenanceRuleTestBase;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.util.ErrorMessage;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.MessageMap;
import org.kuali.rice.kns.util.RiceKeyConstants;
import org.kuali.rice.kns.util.TypedArrayList;

public class KcPersonExtendedAttributesPersonIdExistenceRuleTest extends MaintenanceRuleTestBase {
    private KcPersonExtendedAttributesPersonIdExistenceRule rule = null;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new KcPersonExtendedAttributesPersonIdExistenceRule();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        super.tearDown();
    }

    @Test
    public void testExists() throws Exception {
        KcPersonExtendedAttributes kcPersonEA= new KcPersonExtendedAttributes();
        kcPersonEA.setPersonId("10000000060");   // admin's id, should exists
        MaintenanceDocument kcPesonExtendedAttribuesDocument = newMaintDoc(kcPersonEA);
        MessageMap eMap = GlobalVariables.getMessageMap();
        assertEquals(0, eMap.getErrorCount());
        assertTrue(rule.processSaveDocument(kcPesonExtendedAttribuesDocument));
        assertEquals(0, eMap.getErrorCount());
    }

    @Test
    public void testNotExists() throws Exception {
        KcPersonExtendedAttributes kcPersonEA = new KcPersonExtendedAttributes();
        kcPersonEA.setPersonId("12345");   // should not exists
        MaintenanceDocument kcPesonExtendedAttribuesDocument = newMaintDoc(kcPersonEA);
        MessageMap eMap = GlobalVariables.getMessageMap();
        assertEquals(0, eMap.getErrorCount());
        assertFalse(rule.processSaveDocument(kcPesonExtendedAttribuesDocument));
        assertEquals(1, eMap.getErrorCount());
        TypedArrayList errors = eMap.getErrorMessagesForProperty(KNSConstants.MAINTENANCE_NEW_MAINTAINABLE + "principalId");
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), RiceKeyConstants.ERROR_EXISTENCE);
    }
}