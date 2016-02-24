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
package org.kuali.kra.irb.correspondence;

import org.apache.struts.upload.FormFile;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondenceTemplateBase;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondenceTemplateRule;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondenceTypeBase;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProtocolCorrespondenceTemplateRuleTest {

    Mockery context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
    FormFile mockedFile = null;

    @Before
    public void setUp() throws Exception {
    	
    	mockedFile = this.context.mock(FormFile.class);
    	
        // Clear any error messages that may have been created in prior tests.
        MessageMap messageMap = GlobalVariables.getMessageMap();
        messageMap.clearErrorMessages();
    }
    
    @After
    public void tearDown() throws Exception {
    	mockedFile = null;
    }
    
    /**
     * 
     * This test simulates a correspondence template being added whose committee is not specified.
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testAddCorrespondenceTemplateMissingCommittee() throws Exception {
    	simulateValidMockedFileBehavior();
    	
        ProtocolCorrespondenceType correspondenceType = new ProtocolCorrespondenceType();
        ProtocolCorrespondenceTemplate newCorrespondenceTemplate = getCorrespondenceTemplate(null);
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
    	simulateValidMockedFileBehavior();
    	
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
     * This test simulates a correspondence template being added with a missing file. 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testAddCorrespondenceTemplateMissingFile() throws Exception {
    	this.context.checking(new Expectations() {{
    		allowing(mockedFile).getContentType();
    		will(returnValue(Constants.CORRESPONDENCE_TEMPLATE_CONTENT_TYPE_1));
    		
    		allowing(mockedFile).getFileData();
			will(returnValue(null));
    	}});
    	
        ProtocolCorrespondenceType correspondenceType = getCorrespondenceType(new String[] {"12"}); 
        ProtocolCorrespondenceTemplate newCorrespondenceTemplate = getCorrespondenceTemplate("13");
        int index = 2;
        
        boolean rulePassed = new ProtocolCorrespondenceTemplateRule().processAddProtocolCorrespondenceTemplateRules(correspondenceType, newCorrespondenceTemplate, index);
        assertFalse(rulePassed);
    	
        /*
         * There should be one error.
         */
        MessageMap messageMap = GlobalVariables.getMessageMap();
        assertEquals(1, messageMap.getErrorCount());
        
        /*
         * Verify that the error key of the templateFile field is in the MessageMap.
         */
        assertTrue(messageMap.doesPropertyHaveError("newCorrespondenceTemplates[2].templateFile"));

        /*
         * Verify that the correct error message is in the MessageMap.
         */
        List<ErrorMessage> errorMessages = messageMap.getErrorMessagesForProperty("newCorrespondenceTemplates[2].templateFile");
        assertEquals(KeyConstants.ERROR_CORRESPONDENCE_TEMPLATE_EMPTY_FILE, errorMessages.get(0).getErrorKey());
    }

    /**
     * 
     * This test simulates a correspondence template being added with an empty file. 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testAddCorrespondenceTemplateEmptyFile() throws Exception {
    	this.context.checking(new Expectations() {{
    		allowing(mockedFile).getContentType();
    		will(returnValue(Constants.CORRESPONDENCE_TEMPLATE_CONTENT_TYPE_1));
    		
    		allowing(mockedFile).getFileData();
			will(returnValue(new byte[] {}));
    	}});
    	
        ProtocolCorrespondenceType correspondenceType = getCorrespondenceType(new String[] {"12"}); 
        ProtocolCorrespondenceTemplate newCorrespondenceTemplate = getCorrespondenceTemplate("13");
        int index = 2;
        
        boolean rulePassed = new ProtocolCorrespondenceTemplateRule().processAddProtocolCorrespondenceTemplateRules(correspondenceType, newCorrespondenceTemplate, index);
        assertFalse(rulePassed);
    	
        /*
         * There should be one error.
         */
        MessageMap messageMap = GlobalVariables.getMessageMap();
        assertEquals(1, messageMap.getErrorCount());
        
        /*
         * Verify that the error key of the templateFile field is in the MessageMap.
         */
        assertTrue(messageMap.doesPropertyHaveError("newCorrespondenceTemplates[2].templateFile"));

        /*
         * Verify that the correct error message is in the MessageMap.
         */
        List<ErrorMessage> errorMessages = messageMap.getErrorMessagesForProperty("newCorrespondenceTemplates[2].templateFile");
        assertEquals(KeyConstants.ERROR_CORRESPONDENCE_TEMPLATE_EMPTY_FILE, errorMessages.get(0).getErrorKey());
    }

    /**
     * 
     * This test simulates a correspondence template being added with the incorrect file type. 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testAddCorrespondenceTemplateInvalidFileType() throws Exception {
    	this.context.checking(new Expectations() {{
    		allowing(mockedFile).getContentType();
    		will(returnValue("bad/content-type"));
    		
    		allowing(mockedFile).getFileData();
			will(returnValue(new byte[] { (byte) 1, (byte) 2, (byte) 3 }));
    	}});
    	
        ProtocolCorrespondenceType correspondenceType = getCorrespondenceType(new String[] {"12"}); 
        ProtocolCorrespondenceTemplate newCorrespondenceTemplate = getCorrespondenceTemplate("13");
        int index = 2;
        
        boolean rulePassed = new ProtocolCorrespondenceTemplateRule().processAddProtocolCorrespondenceTemplateRules(correspondenceType, newCorrespondenceTemplate, index);
        assertFalse(rulePassed);
    	
        /*
         * There should be one error.
         */
        MessageMap messageMap = GlobalVariables.getMessageMap();
        assertEquals(1, messageMap.getErrorCount());
        
        /*
         * Verify that the error key of the templateFile field is in the MessageMap.
         */
        assertTrue(messageMap.doesPropertyHaveError("newCorrespondenceTemplates[2].templateFile"));

        /*
         * Verify that the correct error message is in the MessageMap.
         */
        List<ErrorMessage> errorMessages = messageMap.getErrorMessagesForProperty("newCorrespondenceTemplates[2].templateFile");
        assertEquals(KeyConstants.ERROR_CORRESPONDENCE_TEMPLATE_INVALID_FILE_TYPE, errorMessages.get(0).getErrorKey());
    }

    /**
     * 
     * This test simulates a correspondence template successfully being added. 
     * @throws Exception
     */
    @Test
    public void testAddCorrespondenceTemplate() throws Exception {
    	simulateValidMockedFileBehavior();
    	
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
    
    /**
     * 
     * This test simulates a correspondence template being added with a missing file. 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testReplaceCorrespondenceTemplateMissingFile() throws Exception {
    	this.context.checking(new Expectations() {{
    		allowing(mockedFile).getContentType();
    		will(returnValue(Constants.CORRESPONDENCE_TEMPLATE_CONTENT_TYPE_1));
    		
    		allowing(mockedFile).getFileData();
			will(returnValue(null));
    	}});
    	
        ProtocolCorrespondenceType correspondenceType = getCorrespondenceType(new String[] {"12"}); 
        ProtocolCorrespondenceTemplate newCorrespondenceTemplate = getCorrespondenceTemplate("13");
        int typeIndex = 2;
        int templateIndex = 3;
        
        boolean rulePassed = new ProtocolCorrespondenceTemplateRule().processReplaceProtocolCorrespondenceTemplateRules(correspondenceType, newCorrespondenceTemplate, typeIndex, templateIndex);
        assertFalse(rulePassed);
    	
        /*
         * There should be one error.
         */
        MessageMap messageMap = GlobalVariables.getMessageMap();
        assertEquals(1, messageMap.getErrorCount());
        
        /*
         * Verify that the error key of the templateFile field is in the MessageMap.
         */
        assertTrue(messageMap.doesPropertyHaveError("replaceCorrespondenceTemplates[" + typeIndex + "].list[" + templateIndex + "].templateFile"));

        /*
         * Verify that the correct error message is in the MessageMap.
         */
        List<ErrorMessage> errorMessages = messageMap.getErrorMessagesForProperty("replaceCorrespondenceTemplates[" + typeIndex + "].list[" + templateIndex + "].templateFile");
        assertEquals(KeyConstants.ERROR_CORRESPONDENCE_TEMPLATE_EMPTY_FILE, errorMessages.get(0).getErrorKey());
    }

    /**
     * 
     * This test simulates a correspondence template being added with an empty file. 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testReplaceCorrespondenceTemplateEmptyFile() throws Exception {
    	this.context.checking(new Expectations() {{
    		allowing(mockedFile).getContentType();
    		will(returnValue(Constants.CORRESPONDENCE_TEMPLATE_CONTENT_TYPE_1));
    		
    		allowing(mockedFile).getFileData();
			will(returnValue(new byte[] {}));
    	}});
    	
        ProtocolCorrespondenceType correspondenceType = getCorrespondenceType(new String[] {"12"}); 
        ProtocolCorrespondenceTemplate newCorrespondenceTemplate = getCorrespondenceTemplate("13");
        int typeIndex = 2;
        int templateIndex = 3;
        
        boolean rulePassed = new ProtocolCorrespondenceTemplateRule().processReplaceProtocolCorrespondenceTemplateRules(correspondenceType, newCorrespondenceTemplate, typeIndex, templateIndex);
        assertFalse(rulePassed);
    	
        /*
         * There should be one error.
         */
        MessageMap messageMap = GlobalVariables.getMessageMap();
        assertEquals(1, messageMap.getErrorCount());
        
        /*
         * Verify that the error key of the templateFile field is in the MessageMap.
         */
        assertTrue(messageMap.doesPropertyHaveError("replaceCorrespondenceTemplates[" + typeIndex + "].list[" + templateIndex + "].templateFile"));

        /*
         * Verify that the correct error message is in the MessageMap.
         */
        List<ErrorMessage> errorMessages = messageMap.getErrorMessagesForProperty("replaceCorrespondenceTemplates[" + typeIndex + "].list[" + templateIndex + "].templateFile");
        assertEquals(KeyConstants.ERROR_CORRESPONDENCE_TEMPLATE_EMPTY_FILE, errorMessages.get(0).getErrorKey());
    }

    /**
     * 
     * This test simulates a correspondence template being replaced with the incorrect file type. 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testReplaceCorrespondenceTemplateInvalidFileType() throws Exception {
    	this.context.checking(new Expectations() {{
    		allowing(mockedFile).getContentType();
    		will(returnValue("bad/content-type"));
    		
    		allowing(mockedFile).getFileData();
			will(returnValue(new byte[] { (byte) 1, (byte) 2, (byte) 3 }));
    	}});
    	
        ProtocolCorrespondenceType correspondenceType = getCorrespondenceType(new String[] {"12"}); 
        ProtocolCorrespondenceTemplate newCorrespondenceTemplate = getCorrespondenceTemplate("12");
        int typeIndex = 2;
        int templateIndex = 3;
        
        boolean rulePassed = new ProtocolCorrespondenceTemplateRule().processReplaceProtocolCorrespondenceTemplateRules(correspondenceType, newCorrespondenceTemplate, typeIndex, templateIndex);
        assertFalse(rulePassed);
    	
        /*
         * There should be one error.
         */
        MessageMap messageMap = GlobalVariables.getMessageMap();
        assertEquals(1, messageMap.getErrorCount());
        
        /*
         * Verify that the error key of the templateFile field is in the MessageMap.
         */
        assertTrue(messageMap.doesPropertyHaveError("replaceCorrespondenceTemplates[" + typeIndex + "].list[" + templateIndex + "].templateFile"));

        /*
         * Verify that the correct error message is in the MessageMap.
         */
        List<ErrorMessage> errorMessages = messageMap.getErrorMessagesForProperty("replaceCorrespondenceTemplates[" + typeIndex + "].list[" + templateIndex + "].templateFile");
        assertEquals(KeyConstants.ERROR_CORRESPONDENCE_TEMPLATE_INVALID_FILE_TYPE, errorMessages.get(0).getErrorKey());
    }

    /**
     * 
     * This test simulates a correspondence template successfully being replaced. 
     * @throws Exception
     */
    @Test
    public void testReplaceCorrespondenceTemplate() throws Exception {
    	simulateValidMockedFileBehavior();
    	
        ProtocolCorrespondenceType correspondenceType = getCorrespondenceType(new String[] {"12"}); 
        ProtocolCorrespondenceTemplate newCorrespondenceTemplate = getCorrespondenceTemplate("12");
        int index = 2;
        
        boolean rulePassed = new ProtocolCorrespondenceTemplateRule().processReplaceProtocolCorrespondenceTemplateRules(correspondenceType, newCorrespondenceTemplate, index, index);
        assertTrue(rulePassed);

        /*
         * There should be no errors.
         */
        MessageMap messageMap = GlobalVariables.getMessageMap();
        assertEquals(0, messageMap.getErrorCount());
   }
    
    /**
     * 
     * This test simulates a default correspondence template being added with a missing file. 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testAddDefaultCorrespondenceTemplateMissingFile() throws Exception {
    	this.context.checking(new Expectations() {{
    		allowing(mockedFile).getContentType();
    		will(returnValue(Constants.CORRESPONDENCE_TEMPLATE_CONTENT_TYPE_1));
    		
    		allowing(mockedFile).getFileData();
			will(returnValue(null));
    	}});
    	
        ProtocolCorrespondenceType correspondenceType = getCorrespondenceType(new String[] {"12"}); 
        ProtocolCorrespondenceTemplate newCorrespondenceTemplate = getCorrespondenceTemplate("13");
        int index = 2;
        
        boolean rulePassed = new ProtocolCorrespondenceTemplateRule().processAddDefaultProtocolCorrespondenceTemplateRules(correspondenceType, newCorrespondenceTemplate, index);
        assertFalse(rulePassed);
    	
        /*
         * There should be one error.
         */
        MessageMap messageMap = GlobalVariables.getMessageMap();
        assertEquals(1, messageMap.getErrorCount());
        
        /*
         * Verify that the error key of the templateFile field is in the MessageMap.
         */
        assertTrue(messageMap.doesPropertyHaveError("newDefaultCorrespondenceTemplates[2].templateFile"));

        /*
         * Verify that the correct error message is in the MessageMap.
         */
        List<ErrorMessage> errorMessages = messageMap.getErrorMessagesForProperty("newDefaultCorrespondenceTemplates[2].templateFile");
        assertEquals(KeyConstants.ERROR_CORRESPONDENCE_TEMPLATE_EMPTY_FILE, errorMessages.get(0).getErrorKey());
    }

    /**
     * 
     * This test simulates a default correspondence template being added with an empty file. 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testAddDefaultCorrespondenceTemplateEmptyFile() throws Exception {
    	this.context.checking(new Expectations() {{
    		allowing(mockedFile).getContentType();
    		will(returnValue(Constants.CORRESPONDENCE_TEMPLATE_CONTENT_TYPE_1));
    		
    		allowing(mockedFile).getFileData();
			will(returnValue(new byte[] {}));
    	}});
    	
        ProtocolCorrespondenceType correspondenceType = getCorrespondenceType(new String[] {"12"}); 
        ProtocolCorrespondenceTemplate newCorrespondenceTemplate = getCorrespondenceTemplate("13");
        int index = 2;
        
        boolean rulePassed = new ProtocolCorrespondenceTemplateRule().processAddDefaultProtocolCorrespondenceTemplateRules(correspondenceType, newCorrespondenceTemplate, index);
        assertFalse(rulePassed);
    	
        /*
         * There should be one error.
         */
        MessageMap messageMap = GlobalVariables.getMessageMap();
        assertEquals(1, messageMap.getErrorCount());
        
        /*
         * Verify that the error key of the templateFile field is in the MessageMap.
         */
        assertTrue(messageMap.doesPropertyHaveError("newDefaultCorrespondenceTemplates[2].templateFile"));

        /*
         * Verify that the correct error message is in the MessageMap.
         */
        List<ErrorMessage> errorMessages = messageMap.getErrorMessagesForProperty("newDefaultCorrespondenceTemplates[2].templateFile");
        assertEquals(KeyConstants.ERROR_CORRESPONDENCE_TEMPLATE_EMPTY_FILE, errorMessages.get(0).getErrorKey());
    }

    /**
     * 
     * This test simulates a default correspondence template being added with the incorrect file type. 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testAddDefaultCorrespondenceTemplateInvalidFileType() throws Exception {
    	this.context.checking(new Expectations() {{
    		allowing(mockedFile).getContentType();
    		will(returnValue("bad/content-type"));
    		
    		allowing(mockedFile).getFileData();
			will(returnValue(new byte[] { (byte) 1, (byte) 2, (byte) 3 }));
    	}});
    	
        ProtocolCorrespondenceType correspondenceType = getCorrespondenceType(new String[] {"12"}); 
        ProtocolCorrespondenceTemplate newCorrespondenceTemplate = getCorrespondenceTemplate("13");
        int index = 2;
        
        boolean rulePassed = new ProtocolCorrespondenceTemplateRule().processAddDefaultProtocolCorrespondenceTemplateRules(correspondenceType, newCorrespondenceTemplate, index);
        assertFalse(rulePassed);
    	
        /*
         * There should be one error.
         */
        MessageMap messageMap = GlobalVariables.getMessageMap();
        assertEquals(1, messageMap.getErrorCount());
        
        /*
         * Verify that the error key of the templateFile field is in the MessageMap.
         */
        assertTrue(messageMap.doesPropertyHaveError("newDefaultCorrespondenceTemplates[2].templateFile"));

        /*
         * Verify that the correct error message is in the MessageMap.
         */
        List<ErrorMessage> errorMessages = messageMap.getErrorMessagesForProperty("newDefaultCorrespondenceTemplates[2].templateFile");
        assertEquals(KeyConstants.ERROR_CORRESPONDENCE_TEMPLATE_INVALID_FILE_TYPE, errorMessages.get(0).getErrorKey());
    }

    /**
     * 
     * This test simulates a default correspondence template successfully being added. 
     * @throws Exception
     */
    @Test
    public void testAddDefaultCorrespondenceTemplate() throws Exception {
    	simulateValidMockedFileBehavior();
    	
        ProtocolCorrespondenceType correspondenceType = getCorrespondenceType(new String[] {"12"}); 
        ProtocolCorrespondenceTemplate newCorrespondenceTemplate = getCorrespondenceTemplate("13");
        int index = 2;
        
        boolean rulePassed = new ProtocolCorrespondenceTemplateRule().processAddDefaultProtocolCorrespondenceTemplateRules(correspondenceType, newCorrespondenceTemplate, index);
        assertTrue(rulePassed);

        /*
         * There should be no errors.
         */
        MessageMap messageMap = GlobalVariables.getMessageMap();
        assertEquals(0, messageMap.getErrorCount());
   }
    
    /**
     * 
     * This test simulates the correspondence templates being unsuccessfully saved because a committee has not been specified.
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testSaveCorrespondenceTemplateMissingCommittee() throws Exception {
    	simulateValidMockedFileBehavior();
    	
        List<ProtocolCorrespondenceTypeBase> protocolCorrespondenceTypes = new ArrayList<ProtocolCorrespondenceTypeBase>();
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
    
    /**
     * 
     * This test simulates the correspondence templates being unsuccessfully saved because a specified committee id is blank.
     * @throws Exception
     */
   @SuppressWarnings("unchecked")
   @Test
    public void testSaveCorrespondenceTemplateBlankCommittee() throws Exception {
    	simulateValidMockedFileBehavior();
    	
        List<ProtocolCorrespondenceTypeBase> protocolCorrespondenceTypes = new ArrayList<ProtocolCorrespondenceTypeBase>();
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

    /**
     * 
     * This test simulates the correspondence templates being unsuccessfully being saved because a committee has been specified multiple times.
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testSaveCorrespondenceTemplateDuplicateCommittee() throws Exception {
    	simulateValidMockedFileBehavior();
    	
        List<ProtocolCorrespondenceTypeBase> protocolCorrespondenceTypes = new ArrayList<ProtocolCorrespondenceTypeBase>();
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
     * This test simulates the correspondence templates being unsuccessfully being saved because of a missing file name.
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testSaveCorrespondenceTemplateMissingFileName() throws Exception {
        List<ProtocolCorrespondenceTypeBase> protocolCorrespondenceTypes = new ArrayList<ProtocolCorrespondenceTypeBase>();
        protocolCorrespondenceTypes.add(getCorrespondenceType(new String[] {"1", "2"}));

        // create and add faulty data
        ProtocolCorrespondenceType correspondenceType = getCorrespondenceType(new String[] {"1"});
        ProtocolCorrespondenceTemplate errorTemplate = getCorrespondenceTemplate("2");
        errorTemplate.setFileName("");
        correspondenceType.getProtocolCorrespondenceTemplates().add(errorTemplate);
        protocolCorrespondenceTypes.add(correspondenceType);

        boolean rulePassed = new ProtocolCorrespondenceTemplateRule().processSaveProtocolCorrespondenceTemplateRules(protocolCorrespondenceTypes);
        assertFalse(rulePassed);
        
        /*
         * There should be one error.
         */
        MessageMap messageMap = GlobalVariables.getMessageMap();
        assertEquals(1, messageMap.getErrorCount());
        
        /*
         * Verify that the error key of the file field is in the MessageMap.
         */
        assertTrue(messageMap.doesPropertyHaveError("correspondenceTypes[1].protocolCorrespondenceTemplates[1].templateFile"));

        /*
         * Verify that the correct error message is in the MessageMap.
         */
        List<ErrorMessage> errorMessages = messageMap.getErrorMessagesForProperty("correspondenceTypes[1].protocolCorrespondenceTemplates[1].templateFile");
        assertEquals(KeyConstants.ERROR_CORRESPONDENCE_TEMPLATE_INVALID_FILE, errorMessages.get(0).getErrorKey());
    }
    
    /**
     * 
     * This test simulates the correspondence templates being unsuccessfully being saved because of an invalid file.
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testSaveCorrespondenceTemplateInvalidFile() throws Exception {
        List<ProtocolCorrespondenceTypeBase> protocolCorrespondenceTypes = new ArrayList<ProtocolCorrespondenceTypeBase>();
        protocolCorrespondenceTypes.add(getCorrespondenceType(new String[] {"1", "2"}));

        // create and add faulty data
        ProtocolCorrespondenceType correspondenceType = getCorrespondenceType(new String[] {"1"});
        ProtocolCorrespondenceTemplate errorTemplate = getCorrespondenceTemplate("2");
        errorTemplate.setCorrespondenceTemplate(new byte[] {} );
        correspondenceType.getProtocolCorrespondenceTemplates().add(errorTemplate);
        protocolCorrespondenceTypes.add(correspondenceType);

        boolean rulePassed = new ProtocolCorrespondenceTemplateRule().processSaveProtocolCorrespondenceTemplateRules(protocolCorrespondenceTypes);
        assertFalse(rulePassed);
        
        /*
         * There should be one error.
         */
        MessageMap messageMap = GlobalVariables.getMessageMap();
        assertEquals(1, messageMap.getErrorCount());
        
        /*
         * Verify that the error key of the file field is in the MessageMap.
         */
        assertTrue(messageMap.doesPropertyHaveError("correspondenceTypes[1].protocolCorrespondenceTemplates[1].templateFile"));

        /*
         * Verify that the correct error message is in the MessageMap.
         */
        List<ErrorMessage> errorMessages = messageMap.getErrorMessagesForProperty("correspondenceTypes[1].protocolCorrespondenceTemplates[1].templateFile");
        assertEquals(KeyConstants.ERROR_CORRESPONDENCE_TEMPLATE_INVALID_FILE, errorMessages.get(0).getErrorKey());
    }
    
    /**
     * 
     * This test simulates the correspondence templates being unsuccessfully being saved because of a missing file name.
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testSaveCorrespondenceTemplateMissingDefaultFileName() throws Exception {
        List<ProtocolCorrespondenceTypeBase> protocolCorrespondenceTypes = new ArrayList<ProtocolCorrespondenceTypeBase>();
        protocolCorrespondenceTypes.add(getCorrespondenceType(new String[] {"1", "2"}));

        // create and add faulty data
        ProtocolCorrespondenceType correspondenceType = getCorrespondenceType(new String[] {"1"});
        ProtocolCorrespondenceTemplate errorTemplate = getCorrespondenceTemplate(Constants.DEFAULT_CORRESPONDENCE_TEMPLATE);
        errorTemplate.setFileName("");
        correspondenceType.getProtocolCorrespondenceTemplates().add(errorTemplate);
        protocolCorrespondenceTypes.add(correspondenceType);

        boolean rulePassed = new ProtocolCorrespondenceTemplateRule().processSaveProtocolCorrespondenceTemplateRules(protocolCorrespondenceTypes);
        assertFalse(rulePassed);
        
        /*
         * There should be one error.
         */
        MessageMap messageMap = GlobalVariables.getMessageMap();
        assertEquals(1, messageMap.getErrorCount());
        
        /*
         * Verify that the error key of the file field is in the MessageMap.
         */
        assertTrue(messageMap.doesPropertyHaveError("newDefaultCorrespondenceTemplates[1].templateFile"));

        /*
         * Verify that the correct error message is in the MessageMap.
         */
        List<ErrorMessage> errorMessages = messageMap.getErrorMessagesForProperty("newDefaultCorrespondenceTemplates[1].templateFile");
        assertEquals(KeyConstants.ERROR_CORRESPONDENCE_TEMPLATE_INVALID_FILE, errorMessages.get(0).getErrorKey());
    }
    
    /**
     * 
     * This test simulates the correspondence templates being unsuccessfully being saved because of an invalid file.
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testSaveCorrespondenceTemplateInvalidDefaultFile() throws Exception {
        List<ProtocolCorrespondenceTypeBase> protocolCorrespondenceTypes = new ArrayList<ProtocolCorrespondenceTypeBase>();
        protocolCorrespondenceTypes.add(getCorrespondenceType(new String[] {"1", "2"}));

        // create and add faulty data
        ProtocolCorrespondenceType correspondenceType = getCorrespondenceType(new String[] {"1"});
        ProtocolCorrespondenceTemplate errorTemplate = getCorrespondenceTemplate(Constants.DEFAULT_CORRESPONDENCE_TEMPLATE);
        errorTemplate.setCorrespondenceTemplate(new byte[] {} );
        correspondenceType.getProtocolCorrespondenceTemplates().add(errorTemplate);
        protocolCorrespondenceTypes.add(correspondenceType);

        boolean rulePassed = new ProtocolCorrespondenceTemplateRule().processSaveProtocolCorrespondenceTemplateRules(protocolCorrespondenceTypes);
        assertFalse(rulePassed);
        
        /*
         * There should be one error.
         */
        MessageMap messageMap = GlobalVariables.getMessageMap();
        assertEquals(1, messageMap.getErrorCount());
        
        /*
         * Verify that the error key of the file field is in the MessageMap.
         */
        assertTrue(messageMap.doesPropertyHaveError("newDefaultCorrespondenceTemplates[1].templateFile"));

        /*
         * Verify that the correct error message is in the MessageMap.
         */
        List<ErrorMessage> errorMessages = messageMap.getErrorMessagesForProperty("newDefaultCorrespondenceTemplates[1].templateFile");
        assertEquals(KeyConstants.ERROR_CORRESPONDENCE_TEMPLATE_INVALID_FILE, errorMessages.get(0).getErrorKey());
    }
    
    /**
     * 
     * This test simulates the correspondence templates successfully being saved.
     * @throws Exception
     */
    @Test
    public void testSaveCorrespondenceTemplate() throws Exception {
        List<ProtocolCorrespondenceTypeBase> protocolCorrespondenceTypes = new ArrayList<ProtocolCorrespondenceTypeBase>();
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
     * @throws IOException 
     */
    private ProtocolCorrespondenceType getCorrespondenceType(String committeeIds[]) throws IOException {
    	simulateValidMockedFileBehavior();
    	
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
     * @throws IOException 
     */
    private ProtocolCorrespondenceTemplate getCorrespondenceTemplate(String committeeId) throws IOException {
    	simulateValidMockedFileBehavior();
    	
        ProtocolCorrespondenceTemplateBase protocolCorrespondenceTemplate = new ProtocolCorrespondenceTemplate() {
            private static final long serialVersionUID = 1L;

            // Since we don't actually test the sort order and we don't want to instantiate the Committee
            // object the compareTo method is overridden.
            @Override
            public int compareTo(ProtocolCorrespondenceTemplateBase arg) {
                return this.getCommitteeId().compareTo(arg.getCommitteeId());
            }
        };
        
        protocolCorrespondenceTemplate.setCommitteeId(committeeId);
        protocolCorrespondenceTemplate.setFileName("test.xml");
        protocolCorrespondenceTemplate.setCorrespondenceTemplate(new byte[] { (byte) 1, (byte) 2, (byte) 3 });
        protocolCorrespondenceTemplate.setTemplateFile(mockedFile);
        return (ProtocolCorrespondenceTemplate) protocolCorrespondenceTemplate;
    }
    
    private void simulateValidMockedFileBehavior() throws IOException {
    	this.context.checking(new Expectations() {{
    		allowing(mockedFile).getContentType();
    		will(returnValue(Constants.CORRESPONDENCE_TEMPLATE_CONTENT_TYPE_1));
    		
    		allowing(mockedFile).getFileData();
			will(returnValue(new byte[] { (byte) 1, (byte) 2, (byte) 3 }));
    	}});
    }
}
