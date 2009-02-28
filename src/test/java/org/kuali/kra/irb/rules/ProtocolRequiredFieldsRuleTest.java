/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.rules;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.common.permissions.bo.PermissionsUser;
import org.kuali.kra.common.permissions.web.bean.User;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.irb.rule.event.SaveProtocolRequiredFieldsEvent;


public class ProtocolRequiredFieldsRuleTest extends ProtocolRuleTestBase {

    private static final String PROTOCOL_TITLE_FORM_ELEMENT="document.protocol.title";
    private static final String PROTOCOL_PIID_FORM_ELEMENT="document.protocolHelper.personId";
    private static final String PROTOCOL_LUN_FORM_ELEMENT="protocolHelper.leadUnitNumber";
    private static final String PROTOCOL_TYPE_FORM_ELEMENT="document.protocol.protocolTypeCode";
    private static StringBuffer bigString;
    
    
    private ProtocolRequiredFieldsRule rule = null;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        rule = new ProtocolRequiredFieldsRule();
        bigString = new StringBuffer();
        while (bigString.length()<2001) {
                bigString.append("1234567890");
        }
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        super.tearDown();
    }

    /**
     * Test a valid addition of a user.
     * @throws Exception
     */
    @Test
    public void testOK() throws Exception {
        ProtocolDocument document = getNewProtocolDocument();
        setProtocolRequiredFields(document);
        SaveProtocolRequiredFieldsEvent event = new SaveProtocolRequiredFieldsEvent(document);
        assertTrue(rule.processSaveProtocolRequiredFieldsRules(event));
    }
    
    /**
     * Test saving with a missing title
     * @throws Exception
     */
    @Test
    public void testMissingTitle() throws Exception {
        ProtocolDocument document = getNewProtocolDocument();
        setProtocolRequiredFields(document);
        document.getProtocol().setTitle("");
        SaveProtocolRequiredFieldsEvent event = new SaveProtocolRequiredFieldsEvent(document);
        assertFalse(rule.processSaveProtocolRequiredFieldsRules(event));
        assertError(PROTOCOL_TITLE_FORM_ELEMENT, KeyConstants.ERROR_PROTOCOL_TITLE_NOT_FOUND);
    }
    
    /**
     * Test saving with a missing title
     * @throws Exception
     */
    @Test
    public void testMissingType() throws Exception {
        ProtocolDocument document = getNewProtocolDocument();
        setProtocolRequiredFields(document);
        document.getProtocol().setProtocolType(null);
        document.getProtocol().setProtocolTypeCode("");
        SaveProtocolRequiredFieldsEvent event = new SaveProtocolRequiredFieldsEvent(document);
        assertFalse(rule.processSaveProtocolRequiredFieldsRules(event));
        assertError(PROTOCOL_TYPE_FORM_ELEMENT, KeyConstants.ERROR_PROTOCOL_TYPE_NOT_FOUND);
    }
    
    
    /**
     * Test saving with a missing title
     * @throws Exception
     */
    @Test
    public void testMissinPI() throws Exception {
        ProtocolDocument document = getNewProtocolDocument();
        setProtocolRequiredFields(document);
        document.getProtocol().getProtocolPersons().clear();
        SaveProtocolRequiredFieldsEvent event = new SaveProtocolRequiredFieldsEvent(document);
        assertFalse(rule.processSaveProtocolRequiredFieldsRules(event));
        assertError(PROTOCOL_PIID_FORM_ELEMENT, KeyConstants.ERROR_PROTOCOL_PRINCIPAL_INVESTIGATOR_NAME_NOT_FOUND);
    }
    
    
    /**
     * Test saving with a missing title
     * @throws Exception
     */
    @Test
    public void testMissingLU() throws Exception {
        ProtocolDocument document = getNewProtocolDocument();
        setProtocolRequiredFields(document);
        document.getProtocol().setLeadUnitNumber("");
        document.getProtocol().setLeadUnitForValidation(null);
        SaveProtocolRequiredFieldsEvent event = new SaveProtocolRequiredFieldsEvent(document);
        assertFalse(rule.processSaveProtocolRequiredFieldsRules(event));
        assertError(PROTOCOL_LUN_FORM_ELEMENT, KeyConstants.ERROR_PROTOCOL_LEAD_UNIT_NAME_NOT_FOUND);
    }
    
    
    /**
     * Test saving with a missing title
     * @throws Exception
     */
    @Test
    public void testInvalidLU() throws Exception {
        ProtocolDocument document = getNewProtocolDocument();
        setProtocolRequiredFields(document);
        document.getProtocol().setLeadUnitNumber("bogus");
        document.getProtocol().setLeadUnitForValidation(null);
        SaveProtocolRequiredFieldsEvent event = new SaveProtocolRequiredFieldsEvent(document);
        assertFalse(rule.processSaveProtocolRequiredFieldsRules(event));
        assertError(PROTOCOL_LUN_FORM_ELEMENT, KeyConstants.ERROR_PROTOCOL_LEAD_UNIT_NUM_INVALID);
    }
    
    /**
     * Test saving with a missing title
     * @throws Exception
     */
    @Test
    public void testOversizedTitle() throws Exception {
        ProtocolDocument document = getNewProtocolDocument();
        setProtocolRequiredFields(document);
        document.getProtocol().setTitle(bigString.toString());
        SaveProtocolRequiredFieldsEvent event = new SaveProtocolRequiredFieldsEvent(document);
        assertFalse(rule.processSaveProtocolRequiredFieldsRules(event));
        assertError(PROTOCOL_TITLE_FORM_ELEMENT, "error.maxLength");
    }
}
