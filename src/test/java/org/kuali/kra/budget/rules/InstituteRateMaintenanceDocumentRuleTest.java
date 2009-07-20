/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.budget.rules;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.InstituteRate;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.maintenance.MaintenanceRuleTestBase;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.util.ErrorMessage;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.RiceKeyConstants;
import org.kuali.rice.kns.util.TypedArrayList;

public class InstituteRateMaintenanceDocumentRuleTest extends MaintenanceRuleTestBase {
    private InstituteRateMaintenanceDocumentRule rule = null;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new InstituteRateMaintenanceDocumentRule();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        super.tearDown();
    }

    @Test
    public void testOK() throws Exception {

        InstituteRate instituteRate = new InstituteRate();
        
        instituteRate.setRateClassCode("1");
        instituteRate.setRateTypeCode("1");
        instituteRate.setUnitNumber("000001");
        instituteRate.setActivityTypeCode("1");
        MaintenanceDocument instituteRateDocument = newMaintDoc(instituteRate);
        assertTrue(rule.processCustomRouteDocumentBusinessRules(instituteRateDocument));
        assertTrue(rule.processCustomApproveDocumentBusinessRules(instituteRateDocument));
    }

    /**
     * 
     * This method to test rate type does not exist.
     * @throws Exception
     */
    @Test
    public void testRateTypeNotExist() throws Exception {

        InstituteRate instituteRate = new InstituteRate();
        
        instituteRate.setRateClassCode("10");
        instituteRate.setRateTypeCode("2");
        instituteRate.setUnitNumber("000001");
        instituteRate.setActivityTypeCode("1");
        MaintenanceDocument instituteRateDocument = newMaintDoc(instituteRate);
        assertFalse(rule.processCustomRouteDocumentBusinessRules(instituteRateDocument));
        TypedArrayList errors = GlobalVariables.getErrorMap().getMessages("document.newMaintainableObject.rateTypeCode");
        assertTrue(errors.size() == 1);
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), KeyConstants.ERROR_RATE_TYPE_NOT_EXIST);

        // approve will have the same error too.
        assertFalse(rule.processCustomApproveDocumentBusinessRules(instituteRateDocument));
        errors = GlobalVariables.getErrorMap().getMessages("document.newMaintainableObject.rateTypeCode");
        assertTrue(errors.size() == 1);
        message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), KeyConstants.ERROR_RATE_TYPE_NOT_EXIST);

    }

    @Test
    public void testUnitNumberNotExist() throws Exception {

        InstituteRate instituteRate = new InstituteRate();
        
        instituteRate.setRateClassCode("10");
        instituteRate.setRateTypeCode("1");
        instituteRate.setUnitNumber("00000x");
        instituteRate.setActivityTypeCode("1");
        MaintenanceDocument instituteRateDocument = newMaintDoc(instituteRate);
        assertFalse(rule.processCustomRouteDocumentBusinessRules(instituteRateDocument));
        TypedArrayList errors = GlobalVariables.getErrorMap().getMessages("document.newMaintainableObject.unitNumber");
        assertTrue(errors.size() == 1);
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), RiceKeyConstants.ERROR_EXISTENCE);

        // approve will have the same error too.
        assertFalse(rule.processCustomApproveDocumentBusinessRules(instituteRateDocument));
        errors = GlobalVariables.getErrorMap().getMessages("document.newMaintainableObject.unitNumber");
        assertTrue(errors.size() == 1);
        message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), RiceKeyConstants.ERROR_EXISTENCE);

    }

    @Test
    public void testActivityTypeNotExist() throws Exception {

        InstituteRate instituteRate = new InstituteRate();
        
        instituteRate.setRateClassCode("10");
        instituteRate.setRateTypeCode("1");
        instituteRate.setUnitNumber("000001");
        instituteRate.setActivityTypeCode("x");
        MaintenanceDocument instituteRateDocument = newMaintDoc(instituteRate);
        assertFalse(rule.processCustomRouteDocumentBusinessRules(instituteRateDocument));
        TypedArrayList errors = GlobalVariables.getErrorMap().getMessages("document.newMaintainableObject.activityTypeCode");
        assertTrue(errors.size() == 1);
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), RiceKeyConstants.ERROR_EXISTENCE);

        // approve will have the same error too.
        assertFalse(rule.processCustomApproveDocumentBusinessRules(instituteRateDocument));
        errors = GlobalVariables.getErrorMap().getMessages("document.newMaintainableObject.activityTypeCode");
        assertTrue(errors.size() == 1);
        message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), RiceKeyConstants.ERROR_EXISTENCE);

    }

}
