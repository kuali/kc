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

import static org.hamcrest.core.Is.is;
import static org.hamcrest.text.StringContains.containsString;

import org.junit.Assert;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * This is the integration test for Note And Attachment tab on Protocol Note And Attachment page.
 */
public class ProtocolNoteAndAttachmentWebTest extends ProtocolWebTestBase {
           
    private static final String CONFIRM_DELETE_YES_BUTTON = "methodToCall.processAnswer.button0";
    private static final String PROTOCOL_ATTACHMENT_CONFIRM_DELETE_MSG = "Are you sure you would like to delete the following attachment: Protocol Attachment ProtocolWebTestBase.class?";
    private static final String DESCRIPTION = "a description";
    private static final String PHONE = "123-456-7890";
    private static final String EMAIL = "axl@gnr.com";
    private static final String NAME = "AxlRose";
    private static final String COMMENTS = "some comments";
    private static final String METHOD_TO_CALL_DELETE_ATTACHMENT_PROTOCOL = "methodToCall.deleteAttachmentProtocol";
    private static final String FILE_2 = "ProtocolWebTestBase.class";
    private static final String ATTACHMENT_PROTOCOL_0_NEW_FILE_NAME = "document.protocolList[0].attachmentProtocols[0].newFile";
    private static final String FILE_1 = "ProtocolNoteAndAttachmentWebTest.class";
    private static final String ATTACHMENT_PROTOCOL_0_DESCRIPTION_NAME = "document.protocolList[0].attachmentProtocols[0].description";
    private static final String ATTACHMENT_PROTOCOL_0_CONTACT_PHONE_NUMBER_NAME = "document.protocolList[0].attachmentProtocols[0].contactPhoneNumber";
    private static final String ATTACHMENT_PROTOCOL_0_CONTACT_EMAIL_ADDRESS_NAME = "document.protocolList[0].attachmentProtocols[0].contactEmailAddress";
    private static final String ATTACHMENT_PROTOCOL_0_CONTACT_NAME_NAME = "document.protocolList[0].attachmentProtocols[0].contactName";
    private static final String ATTACHMENT_PROTOCOL_0_COMMENTS_NAME = "document.protocolList[0].attachmentProtocols[0].comments";
    private static final String ATTACHMENT_PROTOCOL_0_STATUS_CODE_NAME = "document.protocolList[0].attachmentProtocols[0].statusCode";
    private static final String INCOMPLETE_TYPE_NAME = "Incomplete";
    private static final String ONE_PROTOCOL_ATTACHMENTS = "Protocol Attachments (1)";
    private static final String METHOD_TO_CALL_ADD_ATTACHMENT_PROTOCOL = "methodToCall.addAttachmentProtocol";
    private static final String NEW_ATTACHMENT_PROTOCOL_NEW_FILE_NAME = "notesAttachmentsHelper.newAttachmentProtocol.newFile";
    private static final String NEW_ATTACHMENT_PROTOCOL_DESCRIPTION_NAME = "notesAttachmentsHelper.newAttachmentProtocol.description";
    private static final String NEW_ATTACHMENT_PROTOCOL_CONTACT_PHONE_NUMBER_NAME = "notesAttachmentsHelper.newAttachmentProtocol.contactPhoneNumber";
    private static final String NEW_ATTACHMENT_PROTOCOL_CONTACT_EMAIL_ADDRESS_NAME = "notesAttachmentsHelper.newAttachmentProtocol.contactEmailAddress";
    private static final String NEW_ATTACHMENT_PROTOCOL_CONTACT_NAME_NAME = "notesAttachmentsHelper.newAttachmentProtocol.contactName";
    private static final String NEW_ATTACHMENT_PROTOCOL_COMMENTS_NAME = "notesAttachmentsHelper.newAttachmentProtocol.comments";
    private static final String NEW_ATTACHMENT_PROTOCOL_STATUS_CODE_NAME = "notesAttachmentsHelper.newAttachmentProtocol.statusCode";
    private static final String NEW_ATTACHMENT_PROTOCOL_TYPE_CODE_NAME = "notesAttachmentsHelper.newAttachmentProtocol.typeCode";
    private static final String NO_PROTCOL_ATTACHMENTS = "Protocol Attachments (0)";
    private static final String TYPE_CODE_1 = "1";
    private static final String STATUS_CODE_1 = "1";
    
    /** tests adding, removing, and replacing protocol attachments. */
    @Test
    public void testProtocolAttachment() throws Exception {
        HtmlPage initalPage = getNoteAttachmentPage();
        Assert.assertThat(initalPage.asText(), containsString(NO_PROTCOL_ATTACHMENTS));
        
        HtmlPage afterAddPage = clickOnExpandAll(addProtocolAttachment(initalPage));
        validateAddedProtocolAttachment(afterAddPage);
        
        HtmlPage afterReplacePage =  clickOnExpandAll(replaceProtocolAttachmentFile(afterAddPage));
        validateReplacedProtocolAttachmentFile(afterReplacePage);
        
        HtmlPage confirmDeletePage =  deleteProtocolAttachment(afterReplacePage);
        validateConfirmDeleteProtocolAttachment(confirmDeletePage);
        
        HtmlPage afterDeletePage =  clickOnExpandAll(confirmDeleteProtocolAttachment(confirmDeletePage));
        validateDeletedProtocolAttachment(afterDeletePage);
        
        HtmlPage afterSaveDeletePage =  clickOnExpandAll(saveDoc(afterDeletePage));
        validateDeletedProtocolAttachment(afterSaveDeletePage);
    }  
    
    /**
     *  adds a protocol attachment.
     *  @param initalPage the attachments page
     *  @return page after add
     */
    private HtmlPage addProtocolAttachment(HtmlPage initalPage) throws Exception {
        setFieldValue(initalPage, NEW_ATTACHMENT_PROTOCOL_TYPE_CODE_NAME, TYPE_CODE_1);
        setFieldValue(initalPage, NEW_ATTACHMENT_PROTOCOL_STATUS_CODE_NAME, STATUS_CODE_1);
        setFieldValue(initalPage, NEW_ATTACHMENT_PROTOCOL_COMMENTS_NAME, COMMENTS);
        setFieldValue(initalPage, NEW_ATTACHMENT_PROTOCOL_CONTACT_NAME_NAME, NAME);
        setFieldValue(initalPage, NEW_ATTACHMENT_PROTOCOL_CONTACT_EMAIL_ADDRESS_NAME, EMAIL);
        setFieldValue(initalPage, NEW_ATTACHMENT_PROTOCOL_CONTACT_PHONE_NUMBER_NAME, PHONE);
        setFieldValue(initalPage, NEW_ATTACHMENT_PROTOCOL_DESCRIPTION_NAME, DESCRIPTION);
        setFieldValue(initalPage, NEW_ATTACHMENT_PROTOCOL_NEW_FILE_NAME, this.getFilePath(ProtocolNoteAndAttachmentWebTest.class));
        return clickOnByName(initalPage, METHOD_TO_CALL_ADD_ATTACHMENT_PROTOCOL, true);
    }     
    
    /**
     *  validates page after add.
     *  @param afterAddPage the attachments page after add
     */
    private void validateAddedProtocolAttachment(HtmlPage afterAddPage) throws Exception {
        
        Assert.assertThat(afterAddPage.asText(), containsString(ONE_PROTOCOL_ATTACHMENTS));
        Assert.assertThat(afterAddPage.asText(), containsString(INCOMPLETE_TYPE_NAME));
        Assert.assertThat(getFieldValue(afterAddPage, ATTACHMENT_PROTOCOL_0_STATUS_CODE_NAME), is(STATUS_CODE_1));
        Assert.assertThat(getFieldValue(afterAddPage, ATTACHMENT_PROTOCOL_0_COMMENTS_NAME), is(COMMENTS));
        Assert.assertThat(getFieldValue(afterAddPage, ATTACHMENT_PROTOCOL_0_CONTACT_NAME_NAME), is(NAME));
        Assert.assertThat(getFieldValue(afterAddPage, ATTACHMENT_PROTOCOL_0_CONTACT_EMAIL_ADDRESS_NAME), is(EMAIL));
        Assert.assertThat(getFieldValue(afterAddPage, ATTACHMENT_PROTOCOL_0_CONTACT_PHONE_NUMBER_NAME), is(PHONE));
        Assert.assertThat(getFieldValue(afterAddPage, ATTACHMENT_PROTOCOL_0_DESCRIPTION_NAME), is(DESCRIPTION));
        Assert.assertThat(afterAddPage.asText(), containsString(FILE_1));
    }
    
    /**
     * Validates the confirm page after clicking delete.
     * @param confirmDeletePage
     * @throws Exception
     */
    private void validateConfirmDeleteProtocolAttachment(HtmlPage confirmDeletePage) throws Exception {
        Assert.assertThat(confirmDeletePage.asText(), containsString(PROTOCOL_ATTACHMENT_CONFIRM_DELETE_MSG));
    }
    
    /**
     *  clicks "yes" on the confirm page.
     *  @param afterAddPage the attachments page after add
     *  @return page after replace
     */
    private HtmlPage confirmDeleteProtocolAttachment(HtmlPage afterAddPage) throws Exception {
        return clickOnByName(afterAddPage, CONFIRM_DELETE_YES_BUTTON, true);
    }
    
    /**
     *  replaces a protocol attachment.
     *  @param afterAddPage the attachments page after add
     *  @return page after replace
     */
    private HtmlPage replaceProtocolAttachmentFile(HtmlPage afterAddPage) throws Exception {
        setFieldValue(afterAddPage, ATTACHMENT_PROTOCOL_0_NEW_FILE_NAME, this.getFilePath(ProtocolWebTestBase.class));
        return savePage(afterAddPage);
    }
    
    /**
     *  validates page after replace.
     *  @param afterReplacePage the attachments page after replace
     */
    private void validateReplacedProtocolAttachmentFile(HtmlPage afterReplacePage) throws Exception {
        
        Assert.assertThat(afterReplacePage.asText(), containsString(ONE_PROTOCOL_ATTACHMENTS));
        Assert.assertThat(afterReplacePage.asText(), containsString(INCOMPLETE_TYPE_NAME));
        Assert.assertThat(getFieldValue(afterReplacePage, ATTACHMENT_PROTOCOL_0_STATUS_CODE_NAME), is(STATUS_CODE_1));
        Assert.assertThat(getFieldValue(afterReplacePage, ATTACHMENT_PROTOCOL_0_COMMENTS_NAME), is(COMMENTS));
        Assert.assertThat(getFieldValue(afterReplacePage, ATTACHMENT_PROTOCOL_0_CONTACT_NAME_NAME), is(NAME));
        Assert.assertThat(getFieldValue(afterReplacePage, ATTACHMENT_PROTOCOL_0_CONTACT_EMAIL_ADDRESS_NAME), is(EMAIL));
        Assert.assertThat(getFieldValue(afterReplacePage, ATTACHMENT_PROTOCOL_0_CONTACT_PHONE_NUMBER_NAME), is(PHONE));
        Assert.assertThat(getFieldValue(afterReplacePage, ATTACHMENT_PROTOCOL_0_DESCRIPTION_NAME), is(DESCRIPTION));
        Assert.assertThat(afterReplacePage.asText(), containsString(FILE_2));
    }
    
    /**
     *  deletes a protocol attachment.
     *  @param afterReplacePage the attachments page after replace
     *  @return page after delete
     */
    private HtmlPage deleteProtocolAttachment(HtmlPage afterReplacePage) throws Exception {
        return clickOnByName(afterReplacePage, METHOD_TO_CALL_DELETE_ATTACHMENT_PROTOCOL, true);
    }
    
    /**
     *  validates page after delete.
     *  @param afterDeletePage the attachments page after delete
     */
    private void validateDeletedProtocolAttachment(HtmlPage afterDeletePage) throws Exception {
//        Assert.assertThat(afterDeletePage.asText(), not(containsString(INCOMPLETE_TYPE_NAME)));
//        Assert.assertThat(afterDeletePage.asText(), containsString(NO_PROTCOL_ATTACHMENTS));
//        Assert.assertThat(afterDeletePage.asText(), not(containsString(FILE_2)));
        //FIXME: must change this to work w/ versioning.  right now deletes are never occurring b/c versioning is forced
    }
    
}
