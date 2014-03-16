/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.rules;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.maintenance.MaintenanceRuleTestBase;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.List;
import static org.junit.Assert.*;
public class UnitMaintenanceDocumentRuleTest extends MaintenanceRuleTestBase {
    private UnitMaintenanceDocumentRule rule = null;


    @Before
    public void setUp() throws Exception {
        rule = new UnitMaintenanceDocumentRule();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
    }

    @Test
    public void testOK() throws Exception {

        Unit unit=new Unit();
        String unitNumber="BL-RCEN";
        unit.setUnitName(unitNumber);
        unit.setUnitNumber("BL-RCEN");
        unit.setParentUnitNumber("IN-IN");
        unit.setOrganizationId("00001");
        MaintenanceDocument unitmaintenancedocument = newMaintDoc(unit);
        assertTrue(rule.processCustomApproveDocumentBusinessRules(unitmaintenancedocument));
    }

    @Test
    public void testMoveUnitOwnDescendant() throws Exception{
        Unit unit=new Unit();
        unit.setUnitName("IN-IN");
        unit.setUnitNumber("IN-IN");
        unit.setParentUnitNumber("IN-MED");
        unit.setOrganizationId("00001");
        MaintenanceDocument unitmaintenancedocument = newMaintDoc(unit);
        assertFalse(rule.processCustomRouteDocumentBusinessRules(unitmaintenancedocument));
        List errors = GlobalVariables.getMessageMap().getMessages("ddocument.newMaintainableObject.parentUnitNumber");
        errors = GlobalVariables.getMessageMap().getMessages("document.newMaintainableObject.parentUnitNumber");
        assertTrue(errors.size() == 1);
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), KeyConstants.MOVE_UNIT_OWN_DESCENDANTS);
    }

}


