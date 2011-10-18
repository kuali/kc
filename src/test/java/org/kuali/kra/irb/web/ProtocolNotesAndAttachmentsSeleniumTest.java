/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.irb.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

/**
 * Tests the Protocol Attachments tab in the Notes & Attachments page of a Protocol.
 */
public class ProtocolNotesAndAttachmentsSeleniumTest extends KcSeleniumTestBase {
    
    private static final String TAB_ID = "Protocol Attachments";
    private static final String FILE_NAME_ID = "div[id='attachmentProtocolFileName%d']";
    
    private static final String HELPER_PREFIX = "notesAttachmentsHelper.newAttachmentProtocol.";
    private static final String LIST_PREFIX = "document.protocolList[0].attachmentProtocols[0].";
    
    private static final String TYPE_CODE_ID = "typeCode";
    private static final String STATUS_CODE_ID = "statusCode";
    private static final String COMMENTS_ID = "comments";
    private static final String NEW_FILE_ID = "newFile";
    private static final String CONTACT_NAME_ID = "contactName";
    private static final String CONTACT_EMAIL_ADDRESS_ID = "contactEmailAddress";
    private static final String CONTACT_PHONE_NUMBER_ID = "contactPhoneNumber";
    private static final String DESCRIPTION_ID = "description";
    private static final String HELPER_ATTACHMENT_PROTOCOL_TYPE_CODE_ID = HELPER_PREFIX + TYPE_CODE_ID;
    private static final String HELPER_ATTACHMENT_PROTOCOL_STATUS_CODE_ID = HELPER_PREFIX + STATUS_CODE_ID;
    private static final String HELPER_ATTACHMENT_PROTOCOL_COMMENTS_ID = HELPER_PREFIX + COMMENTS_ID;
    private static final String HELPER_ATTACHMENT_PROTOCOL_NEW_FILE_ID = HELPER_PREFIX + NEW_FILE_ID;
    private static final String HELPER_ATTACHMENT_PROTOCOL_CONTACT_NAME_ID = HELPER_PREFIX + CONTACT_NAME_ID;
    private static final String HELPER_ATTACHMENT_PROTOCOL_CONTACT_EMAIL_ADDRESS_ID = HELPER_PREFIX + CONTACT_EMAIL_ADDRESS_ID;
    private static final String HELPER_ATTACHMENT_PROTOCOL_CONTACT_PHONE_NUMBER_ID = HELPER_PREFIX + CONTACT_PHONE_NUMBER_ID;
    private static final String HELPER_ATTACHMENT_PROTOCOL_DESCRIPTION_ID = HELPER_PREFIX + DESCRIPTION_ID;
    private static final String LIST_ATTACHMENT_PROTOCOL_STATUS_CODE_ID = LIST_PREFIX + STATUS_CODE_ID;
    private static final String LIST_ATTACHMENT_PROTOCOL_COMMENTS_ID = LIST_PREFIX + COMMENTS_ID;
    private static final String LIST_ATTACHMENT_PROTOCOL_NEW_FILE_ID = LIST_PREFIX + NEW_FILE_ID;
    private static final String LIST_ATTACHMENT_PROTOCOL_CONTACT_NAME_ID = LIST_PREFIX + CONTACT_NAME_ID;
    private static final String LIST_ATTACHMENT_PROTOCOL_CONTACT_EMAIL_ADDRESS_ID = LIST_PREFIX + CONTACT_EMAIL_ADDRESS_ID;
    private static final String LIST_ATTACHMENT_PROTOCOL_CONTACT_PHONE_NUMBER_ID = LIST_PREFIX + CONTACT_PHONE_NUMBER_ID;
    private static final String LIST_ATTACHMENT_PROTOCOL_DESCRIPTION_ID = LIST_PREFIX + DESCRIPTION_ID;

    private static final String PROTOCOL_ATTACHMENTS_LABEL = "Protocol Attachments (%d)";
    private static final String TYPE_CODE = "Informed Consent Document";
    private static final String STATUS_CODE = "Incomplete";
    private static final String COMMENTS = "some comments";
    private static final String NAME = "AxlRose";
    private static final String EMAIL = "axl@gnr.com";
    private static final String PHONE = "123-456-7890";
    private static final String DESCRIPTION = "a description";
    
    private static final String ADD_ATTACHMENT_PROTOCOL_BUTTON = "methodToCall.addAttachmentProtocol";
    private static final String REPLACE_ATTACHMENT_PROTOCOL_BUTTON = "replaceButton%d";
    private static final String DELETE_ATTACHMENT_PROTOCOL_BUTTON = "methodToCall.deleteAttachmentProtocol";

    private ProtocolSeleniumHelper helper;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        helper = ProtocolSeleniumHelper.instance(driver);
    }
    
    @After
    public void tearDown() throws Exception {
        helper = null;
        
        super.tearDown();
    }
    
    /** 
     * Test adding, replacing, and removing protocol attachments. 
     * 
     * @throws Exception
     */
    @Test
    public void testProtocolAttachment() throws Exception {
        helper.createProtocol();
        helper.clickProtocolNotesAndAttachmentsPage();
        
        helper.openTab(TAB_ID);
        
        helper.assertPageContains(String.format(PROTOCOL_ATTACHMENTS_LABEL, 0));
        
        helper.set(HELPER_ATTACHMENT_PROTOCOL_TYPE_CODE_ID, TYPE_CODE);
        helper.set(HELPER_ATTACHMENT_PROTOCOL_STATUS_CODE_ID, STATUS_CODE);
        helper.set(HELPER_ATTACHMENT_PROTOCOL_COMMENTS_ID, COMMENTS);
        helper.set(HELPER_ATTACHMENT_PROTOCOL_NEW_FILE_ID, helper.getAbsoluteFilePath(ProtocolNotesAndAttachmentsSeleniumTest.class));
        helper.set(HELPER_ATTACHMENT_PROTOCOL_CONTACT_NAME_ID, NAME);
        helper.set(HELPER_ATTACHMENT_PROTOCOL_CONTACT_EMAIL_ADDRESS_ID, EMAIL);
        helper.set(HELPER_ATTACHMENT_PROTOCOL_CONTACT_PHONE_NUMBER_ID, PHONE);
        helper.set(HELPER_ATTACHMENT_PROTOCOL_DESCRIPTION_ID, DESCRIPTION);
        
        helper.click(ADD_ATTACHMENT_PROTOCOL_BUTTON);
        
        helper.clickExpandAll();
        
        helper.assertPageContains(String.format(PROTOCOL_ATTACHMENTS_LABEL, 1));
        
        helper.assertElementContains(LIST_ATTACHMENT_PROTOCOL_STATUS_CODE_ID, STATUS_CODE);
        helper.assertElementContains(LIST_ATTACHMENT_PROTOCOL_COMMENTS_ID, COMMENTS);
        helper.assertSelectorContains(String.format(FILE_NAME_ID, 0), helper.getSimpleFilePath(ProtocolNotesAndAttachmentsSeleniumTest.class));
        helper.assertElementContains(LIST_ATTACHMENT_PROTOCOL_CONTACT_NAME_ID, NAME);
        helper.assertElementContains(LIST_ATTACHMENT_PROTOCOL_CONTACT_EMAIL_ADDRESS_ID, EMAIL);
        helper.assertElementContains(LIST_ATTACHMENT_PROTOCOL_CONTACT_PHONE_NUMBER_ID, PHONE);
        helper.assertElementContains(LIST_ATTACHMENT_PROTOCOL_DESCRIPTION_ID, DESCRIPTION);
                
        helper.click(String.format(REPLACE_ATTACHMENT_PROTOCOL_BUTTON, 0));
        helper.set(LIST_ATTACHMENT_PROTOCOL_NEW_FILE_ID, helper.getAbsoluteFilePath(KcSeleniumTestBase.class));
        
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.clickExpandAll();
        
        helper.assertPageContains(String.format(PROTOCOL_ATTACHMENTS_LABEL, 1));
        
        helper.assertElementContains(LIST_ATTACHMENT_PROTOCOL_STATUS_CODE_ID, STATUS_CODE);
        helper.assertElementContains(LIST_ATTACHMENT_PROTOCOL_COMMENTS_ID, COMMENTS);
        helper.assertSelectorContains(String.format(FILE_NAME_ID, 0), helper.getSimpleFilePath(KcSeleniumTestBase.class));
        helper.assertElementContains(LIST_ATTACHMENT_PROTOCOL_CONTACT_NAME_ID, NAME);
        helper.assertElementContains(LIST_ATTACHMENT_PROTOCOL_CONTACT_EMAIL_ADDRESS_ID, EMAIL);
        helper.assertElementContains(LIST_ATTACHMENT_PROTOCOL_CONTACT_PHONE_NUMBER_ID, PHONE);
        helper.assertElementContains(LIST_ATTACHMENT_PROTOCOL_DESCRIPTION_ID, DESCRIPTION);
        
        helper.click(DELETE_ATTACHMENT_PROTOCOL_BUTTON);
        helper.clickYesAnswer();
        
        helper.assertSelectorDoesNotContain(String.format(FILE_NAME_ID, 0), helper.getSimpleFilePath(KcSeleniumTestBase.class));
        
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.clickExpandAll();
        
        helper.assertSelectorDoesNotContain(String.format(FILE_NAME_ID, 1), helper.getSimpleFilePath(KcSeleniumTestBase.class));
    }
    
}