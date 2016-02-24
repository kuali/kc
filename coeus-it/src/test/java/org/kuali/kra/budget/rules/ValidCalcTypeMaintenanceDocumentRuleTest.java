/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.budget.rules;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.budget.framework.calculator.ValidCalcType;
import org.kuali.coeus.common.budget.framework.nonpersonnel.ValidCalcTypeMaintenanceDocumentRule;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.maintenance.MaintenanceRuleTestBase;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;
public class ValidCalcTypeMaintenanceDocumentRuleTest extends MaintenanceRuleTestBase {
    private ValidCalcTypeMaintenanceDocumentRule rule = null;

    @Before
    public void setUp() throws Exception {
        rule = new ValidCalcTypeMaintenanceDocumentRule();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
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
        MessageMap mm = GlobalVariables.getMessageMap();
        List errors = mm.getMessages("document.newMaintainableObject.rateTypeCode");
        assertEquals(1, errors.size());
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), KeyConstants.ERROR_RATE_TYPE_NOT_EXIST);

        // approve will have the same error too.
        assertFalse(rule.processCustomApproveDocumentBusinessRules(validCalcTypeDocument));
        errors = mm.getMessages("document.newMaintainableObject.rateTypeCode");
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
        MessageMap mm = GlobalVariables.getMessageMap();
        List errors = mm.getMessages("document.newMaintainableObject.code");
        assertEquals(1, errors.size());
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), RiceKeyConstants.ERROR_EXISTENCE);

        // approve will have the same error too.
        assertFalse(rule.processCustomApproveDocumentBusinessRules(validCalcTypeDocument));
        errors = GlobalVariables.getMessageMap().getMessages("document.newMaintainableObject.code");
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
        List errors = GlobalVariables.getMessageMap().getMessages("document.newMaintainableObject.dependentRateClassType");
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
