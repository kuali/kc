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
package org.kuali.kra.irb.correspondence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.util.ErrorMessage;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.MessageMap;

public class ProtocolCorrespondenceTemplateRuleTest {
    
    @Before
    public void setUp() throws Exception {
        // Clear any error messages that may have been created in prior tests.
        MessageMap messageMap = GlobalVariables.getMessageMap();
        messageMap.clearErrorMessages();
    }
    
    @After
    public void tearDown() throws Exception {
    }
    
    /**
     * 
     * This test simulates a correspondence template being added whose committee is not specified.
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testAddCorrespondenceTemplateMissingCommittee() throws Exception {
        ProtocolCorrespondenceType correspondenceType = new ProtocolCorrespondenceType();
        ProtocolCorrespondenceTemplate newCorrespondenceTemplate = new ProtocolCorrespondenceTemplate();
        int index = 2;
        
        boolean rulePassed = new ProtocolCorrespondenceTemplateRule().processAddProtocolCorrespondenceTemplateRules(correspondenceType, newCorrespondenceTemplate, index);
        assertFalse(rulePassed);
        
        /*
         * There should be one error.
         */
        MessageMap messageMap = GlobalVariables.getMessageMap();
        assertEquals(1, messageMap.getErrorCount());
        
        /*
         * Verify that the error key of the committee field is in the MessageMap.
         */
        assertTrue(messageMap.doesPropertyHaveError("newCorrespondenceTemplates[2].committeeId"));
        
        /*
         * Verify that the correct error message is in the MessageMap.
         */
        List<ErrorMessage> errorMessages = messageMap.getErrorMessagesForProperty("newCorrespondenceTemplates[2].committeeId");
        assertEquals(KeyConstants.ERROR_CORRESPONDENCE_TEMPLATE_COMMITTEE_NOT_SPECIFIED, errorMessages.get(0).getErrorKey());
    }
    
    /**
     * 
     * This test simulates a correspondence template being added for a committee that has already a template specified. 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testAddCorrespondenceTemplateDuplicateCommittee() throws Exception {
        ProtocolCorrespondenceType correspondenceType = getCorrespondenceType(new String[] {"12"}); 
        ProtocolCorrespondenceTemplate newCorrespondenceTemplate = getCorrespondenceTemplate("12");
        int index = 2;
        
        boolean rulePassed = new ProtocolCorrespondenceTemplateRule().processAddProtocolCorrespondenceTemplateRules(correspondenceType, newCorrespondenceTemplate, index);
        assertFalse(rulePassed);

        /*
         * There should be one error.
         */
        MessageMap messageMap = GlobalVariables.getMessageMap();
        assertEquals(1, messageMap.getErrorCount());
        
        /*
         * Verify that the error key of the committee field is in the MessageMap.
         */
        assertTrue(messageMap.doesPropertyHaveError("newCorrespondenceTemplates[2].committeeId"));

        /*
         * Verify that the correct error message is in the MessageMap.
         */
        List<ErrorMessage> errorMessages = messageMap.getErrorMessagesForProperty("newCorrespondenceTemplates[2].committeeId");
        assertEquals(KeyConstants.ERROR_CORRESPONDENCE_TEMPLATE_COMMITTEE_DUPLICATE, errorMessages.get(0).getErrorKey());
    }

    /**
     * 
     * This test simulates a correspondence template successfully being added. 
     * @throws Exception
     */
    @Test
    public void testAddCorrespondenceTemplate() throws Exception {
        ProtocolCorrespondenceType correspondenceType = getCorrespondenceType(new String[] {"12"}); 
        ProtocolCorrespondenceTemplate newCorrespondenceTemplate = getCorrespondenceTemplate("13");
        int index = 2;
        
        boolean rulePassed = new ProtocolCorrespondenceTemplateRule().processAddProtocolCorrespondenceTemplateRules(correspondenceType, newCorrespondenceTemplate, index);
        assertTrue(rulePassed);

        /*
         * There should be no errors.
         */
        MessageMap messageMap = GlobalVariables.getMessageMap();
        assertEquals(0, messageMap.getErrorCount());
   }
    
    @Test
    public void testSaveCorrespondenceTemplateMissingCommittee() throws Exception {
        List<ProtocolCorrespondenceType> protocolCorrespondenceTypes = new ArrayList<ProtocolCorrespondenceType>();
        protocolCorrespondenceTypes.add(getCorrespondenceType(new String[] {null}));

        boolean rulePassed = new ProtocolCorrespondenceTemplateRule().processSaveProtocolCorrespondenceTemplateRules(protocolCorrespondenceTypes);
        assertFalse(rulePassed);
        
        /*
         * There should be one error.
         */
        MessageMap messageMap = GlobalVariables.getMessageMap();
        assertEquals(1, messageMap.getErrorCount());
        
        /*
         * Verify that the error key of the committee field is in the MessageMap.
         */
        assertTrue(messageMap.doesPropertyHaveError("correspondenceTypes[0].protocolCorrespondenceTemplates[0].committeeId"));
        
        /*
         * Verify that the correct error message is in the MessageMap.
         */
        List<ErrorMessage> errorMessages = messageMap.getErrorMessagesForProperty("correspondenceTypes[0].protocolCorrespondenceTemplates[0].committeeId");
        assertEquals(KeyConstants.ERROR_CORRESPONDENCE_TEMPLATE_COMMITTEE_NOT_SPECIFIED, errorMessages.get(0).getErrorKey());
    }
    
    @Test
    public void testSaveCorrespondenceTemplateBlankCommittee() throws Exception {
        List<ProtocolCorrespondenceType> protocolCorrespondenceTypes = new ArrayList<ProtocolCorrespondenceType>();
        protocolCorrespondenceTypes.add(getCorrespondenceType(new String[] {"", "2"}));

        boolean rulePassed = new ProtocolCorrespondenceTemplateRule().processSaveProtocolCorrespondenceTemplateRules(protocolCorrespondenceTypes);
        assertFalse(rulePassed);
        
        /*
         * There should be one error.
         */
        MessageMap messageMap = GlobalVariables.getMessageMap();
        assertEquals(1, messageMap.getErrorCount());
        
        /*
         * Verify that the error key of the committee field is in the MessageMap.
         */
        assertTrue(messageMap.doesPropertyHaveError("correspondenceTypes[0].protocolCorrespondenceTemplates[0].committeeId"));
        
        /*
         * Verify that the correct error message is in the MessageMap.
         */
        List<ErrorMessage> errorMessages = messageMap.getErrorMessagesForProperty("correspondenceTypes[0].protocolCorrespondenceTemplates[0].committeeId");
        assertEquals(KeyConstants.ERROR_CORRESPONDENCE_TEMPLATE_COMMITTEE_NOT_SPECIFIED, errorMessages.get(0).getErrorKey());
    }
    @Test
    public void testSaveCorrespondenceTemplateDuplicateCommittee() throws Exception {
        List<ProtocolCorrespondenceType> protocolCorrespondenceTypes = new ArrayList<ProtocolCorrespondenceType>();
        protocolCorrespondenceTypes.add(getCorrespondenceType(new String[] {"1", "1"}));

        boolean rulePassed = new ProtocolCorrespondenceTemplateRule().processSaveProtocolCorrespondenceTemplateRules(protocolCorrespondenceTypes);
        assertFalse(rulePassed);

        /*
         * There should be one error.
         */
        MessageMap messageMap = GlobalVariables.getMessageMap();
        assertEquals(1, messageMap.getErrorCount());
        
        /*
         * Verify that the error key of the committee field is in the MessageMap.
         */
        assertTrue(messageMap.doesPropertyHaveError("correspondenceTypes[0].protocolCorrespondenceTemplates[1].committeeId"));

        /*
         * Verify that the correct error message is in the MessageMap.
         */
        List<ErrorMessage> errorMessages = messageMap.getErrorMessagesForProperty("correspondenceTypes[0].protocolCorrespondenceTemplates[1].committeeId");
        assertEquals(KeyConstants.ERROR_CORRESPONDENCE_TEMPLATE_COMMITTEE_DUPLICATE, errorMessages.get(0).getErrorKey());
    }
    
    /**
     * 
     * This test simulates the correspondence templates successfully being saved.
     * @throws Exception
     */
    @Test
    public void testSaveCorrespondenceTemplate() throws Exception {
        List<ProtocolCorrespondenceType> protocolCorrespondenceTypes = new ArrayList<ProtocolCorrespondenceType>();
        protocolCorrespondenceTypes.add(getCorrespondenceType(new String[] {"1", "2"}));
        protocolCorrespondenceTypes.add(getCorrespondenceType(new String[] {"1", "3"}));
        protocolCorrespondenceTypes.add(getCorrespondenceType(new String[] {"3", "2"}));

        boolean rulePassed = new ProtocolCorrespondenceTemplateRule().processSaveProtocolCorrespondenceTemplateRules(protocolCorrespondenceTypes);
        assertTrue(rulePassed);
        
        /*
         * There should be no errors.
         */
        MessageMap messageMap = GlobalVariables.getMessageMap();
        assertEquals(0, messageMap.getErrorCount());
    }
    
    /**
     * 
     * This method creates a protocol correspondence type with the specified data.
     * @param committeeIds - a list of committeIds for which correspondence templates should be created and 
     *                       added to the correspondence type.
     * @return ProtocolCorrespondenceType
     */
    private ProtocolCorrespondenceType getCorrespondenceType(String committeeIds[]) {
        ProtocolCorrespondenceType protocolCorrespondenceType = new ProtocolCorrespondenceType();
        for (String committeeId : committeeIds) {
            protocolCorrespondenceType.getProtocolCorrespondenceTemplates().add(getCorrespondenceTemplate(committeeId));
        }
        return protocolCorrespondenceType;
    }
    
    /**
     * 
     * This method creates a protocol correspondence template with the specified data.
     * @param committeeId
     * @return ProtocolCorrespondenceTemplate
     */
    private ProtocolCorrespondenceTemplate getCorrespondenceTemplate(String committeeId) {
        ProtocolCorrespondenceTemplate protocolCorrespondenceTemplate = new ProtocolCorrespondenceTemplate();
        protocolCorrespondenceTemplate.setCommitteeId(committeeId);
        return protocolCorrespondenceTemplate;
    }
}