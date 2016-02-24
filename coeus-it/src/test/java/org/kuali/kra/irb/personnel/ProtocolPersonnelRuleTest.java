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
package org.kuali.kra.irb.personnel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.test.ProtocolRuleTestBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonnelRuleBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonnelService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import static org.junit.Assert.*;
import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;
public class ProtocolPersonnelRuleTest extends ProtocolRuleTestBase {
    private ProtocolPersonnelRuleBase rule;
    private static final String CO_INVESTIGATOR_PERSON_ID = "10000000003";
    private static final String CO_INVESTIGATOR_NAME = "Nicholas Majors";
    private static final String CO_INVESTIGATOR_ROLE_ID = "COI";

    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new ProtocolPersonnelRuleBase() {
            
            @Override
            public ProtocolPersonnelService getProtocolPersonnelServiceHook() {
                return getService(ProtocolPersonnelService.class);
            }
        };
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
        MessageMap errorMap = GlobalVariables.getMessageMap();
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
        MessageMap errorMap = GlobalVariables.getMessageMap();
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
        MessageMap errorMap = GlobalVariables.getMessageMap();
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
        MessageMap errorMap = GlobalVariables.getMessageMap();
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
