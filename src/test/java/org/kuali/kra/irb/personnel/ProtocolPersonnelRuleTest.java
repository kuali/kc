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
package org.kuali.kra.irb.personnel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.test.ProtocolRuleTestBase;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;

public class ProtocolPersonnelRuleTest extends ProtocolRuleTestBase {
    private ProtocolPersonnelRuleBase rule;
    private static final String CO_INVESTIGATOR_PERSON_ID = "10000000003";
    private static final String CO_INVESTIGATOR_NAME = "Nicholas Majors";
    private static final String CO_INVESTIGATOR_ROLE_ID = "COI";
    private static final String CORRESPONDENT_ROLE_ID = "CRC";
    
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new ProtocolPersonnelRuleBase();
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        super.tearDown();
    }

    /** 
     * This method is to test a valid save.
     * Personnel information - Investigator is set through
     * initial protocol required fields
     * 
     */
    @Test
    public void testPersonnelSaveValid() throws Exception {
        ProtocolDocument document  = getNewProtocolDocument();
        setProtocolRequiredFields(document);
        boolean rulesPassed = rule.processSaveProtocolPersonnelEvent(getSaveProtocolPersonnelEvent(document));
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        assertTrue(rulesPassed);
        assertEquals(0, errorMap.getErrorCount());
    }

    /**
     * This method is to test for an error during save.
     * Validation triggered during personnel save.
     * Remove Investigator details and save the document
     * should throw exception - At least one PI is required
     * @throws Exception
     */
    @Test
    public void testPersonnelSaveInvalid() throws Exception {
        ProtocolDocument document  = getNewProtocolDocument();
        setProtocolRequiredFields(document);
        document.getProtocol().getProtocolPersons().remove(0);
        boolean rulesPassed = rule.processSaveProtocolPersonnelEvent(getSaveProtocolPersonnelEvent(document));
        assertFalse(rulesPassed);
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        assertTrue(errorMap.containsMessageKey(KeyConstants.ERROR_PRINCIPAL_INVESTIGATOR_NOT_FOUND));
    }
    
    /**
     * This method is to test add personnel
     * Add a new person - Co-Investigator and it should be valid
     * @throws Exception
     */
    @Test
    public void testAddPersonnelValid() throws Exception {
        ProtocolDocument document  = getNewProtocolDocument();
        setProtocolRequiredFields(document);
        boolean rulesPassed = rule.processAddProtocolPersonnelEvent(getAddProtocolPersonnelEvent(document, getCoInvestigator()));
        assertTrue(rulesPassed);
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        assertEquals(0, errorMap.getErrorCount());
    }
    
    /**
     * This method is to test add personnel with exception
     * Add a new person - existing Principal Investigator and it should 
     * validate and throw duplicate exception
     * @throws Exception
     */
    @Test
    public void testAddPersonnelInValid() throws Exception {
        ProtocolDocument document  = getNewProtocolDocument();
        setProtocolRequiredFields(document);
        boolean rulesPassed = rule.processAddProtocolPersonnelEvent(getAddProtocolPersonnelEvent(document, getPrincipalInvestigator()));
        assertFalse(rulesPassed);
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        assertTrue(errorMap.containsMessageKey(KeyConstants.ERROR_PROTOCOL_PERSONNEL_MULTIPLE_PI));
    }

    /**
     * This method is to get a person with Co-Investigator role
     * @return ProtocolPerson
     */
    private ProtocolPerson getCoInvestigator() {
        return getProtocolPerson(CO_INVESTIGATOR_PERSON_ID, CO_INVESTIGATOR_NAME, CO_INVESTIGATOR_ROLE_ID);
    }
    

    
    /**
     * This method is to get add protocol location event
     * @param newProtocolLocation
     * @return
     * @throws Exception
     */
    private AddProtocolPersonnelEvent getAddProtocolPersonnelEvent(ProtocolDocument document, ProtocolPerson newProtocolPerson) throws Exception {
        AddProtocolPersonnelEvent event = new AddProtocolPersonnelEvent(Constants.EMPTY_STRING,document,newProtocolPerson);
        return event;
    }

    /**
     * This method is to get save protocol personnel event
     * @param document
     * @return
     * @throws Exception
     */
    private SaveProtocolPersonnelEvent getSaveProtocolPersonnelEvent(ProtocolDocument document) throws Exception {
        SaveProtocolPersonnelEvent event = new SaveProtocolPersonnelEvent(Constants.EMPTY_STRING,document);
        return event;
    }
}
