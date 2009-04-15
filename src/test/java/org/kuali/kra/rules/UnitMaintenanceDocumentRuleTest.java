/*
 * Copyright 2006-2009 The Kuali Foundation
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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.UserSession;
import org.kuali.core.document.MaintenanceDocument;
import org.kuali.core.service.LookupService;
import org.kuali.core.util.ErrorMessage;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.TypedArrayList;
import org.kuali.kra.bo.InstituteLaRate;
import org.kuali.kra.bo.InstituteRate;
import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.maintenance.MaintenanceRuleTestBase;
import org.kuali.kra.service.impl.mocks.MockUnitService;
import org.kuali.rice.KNSServiceLocator;

public class UnitMaintenanceDocumentRuleTest extends MaintenanceRuleTestBase {
    private UnitMaintenanceDocumentRule rule = null;
    private MockUnitService unitService;
    private LookupService lookupService;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new UnitMaintenanceDocumentRule();
        lookupService=KNSServiceLocator.getLookupService();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        lookupService=null;
        super.tearDown();
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
        TypedArrayList errors = GlobalVariables.getErrorMap().getMessages("ddocument.newMaintainableObject.parentUnitNumber");
        errors = GlobalVariables.getErrorMap().getMessages("document.newMaintainableObject.parentUnitNumber");
        assertTrue(errors.size() == 1);
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), KeyConstants.MOVE_UNIT_OWN_DESCENDANTS);
    }

}


