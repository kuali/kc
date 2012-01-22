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
package org.kuali.kra.budget.rules;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.budget.calculator.ValidCalcType;
import org.kuali.kra.budget.nonpersonnel.ValidCalcTypeMaintenanceDocumentRule;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.maintenance.MaintenanceRuleTestBase;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.util.AutoPopulatingList;

public class ValidCalcTypeMaintenanceDocumentRuleTest extends MaintenanceRuleTestBase {
    private ValidCalcTypeMaintenanceDocumentRule rule = null;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new ValidCalcTypeMaintenanceDocumentRule();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        super.tearDown();
    }

    @Test
    public void testOK() throws Exception {

        ValidCalcType validCalcType = new ValidCalcType();
        
        validCalcType.setRateClassCode("10");
        validCalcType.setRateTypeCode("1");
        validCalcType.setRateClassType("X");
        validCalcType.setDependentRateClassType("Y");
        MaintenanceDocument validCalcTypeDocument = newMaintDoc(validCalcType);
        assertTrue(rule.processCustomRouteDocumentBusinessRules(validCalcTypeDocument));
        Map eMap = GlobalVariables.getMessageMap().getErrorMessages();
        assertEquals(0, eMap.size());
        
        assertTrue(rule.processCustomApproveDocumentBusinessRules(validCalcTypeDocument));
        assertEquals(0, eMap.size());
    }
    
    /**
     * Since a DependentRateClassType is not required,
     * this tests that not specifying one does not generate an error.
     * @throws Exception if failure occurs
     */
    @Test
    public void testOKNoDependentRateClassType() throws Exception {

        ValidCalcType validCalcType = new ValidCalcType();
        
        validCalcType.setRateClassCode("10");
        validCalcType.setRateTypeCode("1");
        validCalcType.setRateClassType("X");
        validCalcType.setDependentRateClassType(null);
        MaintenanceDocument validCalcTypeDocument = newMaintDoc(validCalcType);
        assertTrue(rule.processCustomRouteDocumentBusinessRules(validCalcTypeDocument));
        Map eMap = GlobalVariables.getMessageMap().getErrorMessages();
        assertEquals(0, eMap.size());
        
        assertTrue(rule.processCustomApproveDocumentBusinessRules(validCalcTypeDocument));
        assertEquals(0, eMap.size());
    }

    /**
     * 
     * This method to test rate type does not exist.
     * @throws Exception
     */
    @Test
    public void testRateTypeNotExist() throws Exception {

        ValidCalcType validCalcType = new ValidCalcType();
        
        validCalcType.setRateClassCode("10");
        validCalcType.setRateTypeCode("2");
        validCalcType.setRateClassType("X");
        validCalcType.setDependentRateClassType("Y");
        MaintenanceDocument validCalcTypeDocument = newMaintDoc(validCalcType);
        assertFalse(rule.processCustomRouteDocumentBusinessRules(validCalcTypeDocument));
        AutoPopulatingList errors = GlobalVariables.getMessageMap().getMessages("document.newMaintainableObject.rateTypeCode");
        assertEquals(1, errors.size());
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), KeyConstants.ERROR_RATE_TYPE_NOT_EXIST);

        // approve will have the same error too.
        assertFalse(rule.processCustomApproveDocumentBusinessRules(validCalcTypeDocument));
        errors = GlobalVariables.getMessageMap().getMessages("document.newMaintainableObject.rateTypeCode");
        assertEquals(1, errors.size());
        message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), KeyConstants.ERROR_RATE_TYPE_NOT_EXIST);

    }

    @Test
    public void testRateClassTypeNotExist() throws Exception {

        ValidCalcType validCalcType = new ValidCalcType();
        
        validCalcType.setRateClassCode("10");
        validCalcType.setRateTypeCode("1");
        validCalcType.setRateClassType("1");
        validCalcType.setDependentRateClassType("Y");
        MaintenanceDocument validCalcTypeDocument = newMaintDoc(validCalcType);
        assertFalse(rule.processCustomRouteDocumentBusinessRules(validCalcTypeDocument));
        AutoPopulatingList errors = GlobalVariables.getMessageMap().getMessages("document.newMaintainableObject.rateClassType");
        assertEquals(1, errors.size());
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), RiceKeyConstants.ERROR_EXISTENCE);

        // approve will have the same error too.
        assertFalse(rule.processCustomApproveDocumentBusinessRules(validCalcTypeDocument));
        errors = GlobalVariables.getMessageMap().getMessages("document.newMaintainableObject.rateClassType");
        assertEquals(1, errors.size());
        message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), RiceKeyConstants.ERROR_EXISTENCE);

    }

    @Test
    public void testDependentrateClassTypeNotExist() throws Exception {

        ValidCalcType validCalcType = new ValidCalcType();
        
        validCalcType.setRateClassCode("10");
        validCalcType.setRateTypeCode("1");
        validCalcType.setRateClassType("X");
        validCalcType.setDependentRateClassType("1");
        MaintenanceDocument validCalcTypeDocument = newMaintDoc(validCalcType);
        assertFalse(rule.processCustomRouteDocumentBusinessRules(validCalcTypeDocument));
        AutoPopulatingList errors = GlobalVariables.getMessageMap().getMessages("document.newMaintainableObject.dependentRateClassType");
        assertEquals(1, errors.size());
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), RiceKeyConstants.ERROR_EXISTENCE);

        // approve will have the same error too.
        assertFalse(rule.processCustomApproveDocumentBusinessRules(validCalcTypeDocument));
        errors = GlobalVariables.getMessageMap().getMessages("document.newMaintainableObject.dependentRateClassType");
        assertEquals(1, errors.size());
        message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), RiceKeyConstants.ERROR_EXISTENCE);

    }
}
