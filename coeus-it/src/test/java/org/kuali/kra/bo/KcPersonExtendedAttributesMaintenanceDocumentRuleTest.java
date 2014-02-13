/*
 * Copyright 2005-2013 The Kuali Foundation
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
import org.kuali.kra.maintenance.MaintenanceRuleTestBase;
import org.kuali.kra.rules.KcPersonExtendedAttributesMaintenanceDocumentRule;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.maintenance.MaintainableImpl;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.MessageMap;
import org.springframework.util.AutoPopulatingList;

import java.util.List;
import static org.junit.Assert.*;
public class KcPersonExtendedAttributesMaintenanceDocumentRuleTest extends MaintenanceRuleTestBase {
    private KcPersonExtendedAttributesMaintenanceDocumentRule rule = null;

    @Before
    public void setUp() throws Exception {
        rule = new KcPersonExtendedAttributesMaintenanceDocumentRule();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
    }

    @Test
    public void testExists() throws Exception {
        KcPersonExtendedAttributes kcPersonEA= new KcPersonExtendedAttributes();
        kcPersonEA.setPersonId("10000000001");   // quickstart's id, should exists
        MaintenanceDocument kcPesonExtendedAttribuesDocument = newMaintDoc(kcPersonEA);
        MessageMap eMap = GlobalVariables.getMessageMap();
        assertEquals(0, eMap.getErrorCount());
        assertTrue(rule.processSaveDocument(kcPesonExtendedAttribuesDocument));
        assertEquals(0, eMap.getErrorCount());
    }
    
    protected MaintainableImpl getNewMaintainableImpl(PersistableBusinessObject bo) {
        if (bo == null) {
            return new KcPersonExtendedAttributesMaintainableImpl();
        } else {
            MaintainableImpl result = new KcPersonExtendedAttributesMaintainableImpl();
            result.setDataObject(bo);
            return result;
        }
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
        List errors = eMap.getErrorMessagesForProperty(KRADConstants.MAINTENANCE_NEW_MAINTAINABLE + "principalId");
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), RiceKeyConstants.ERROR_EXISTENCE);
    }
}