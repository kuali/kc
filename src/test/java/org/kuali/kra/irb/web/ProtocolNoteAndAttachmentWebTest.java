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
package org.kuali.kra.irb.web;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import static org.hamcrest.text.StringContains.*;

/**
 * This is the integration test for Note And Attachment tab on Protocol Note And Attachment page.
 */
public class ProtocolNoteAndAttachmentWebTest extends ProtocolWebTestBase {
       
    /*
    notesAndAttachmentsHelper.newAttachmentProtocol.type.code = 1
    notesAndAttachmentsHelper.newAttachmentProtocol.status.code = 1
    notesAndAttachmentsHelper.newAttachmentProtocol.comments = some comments
    notesAndAttachmentsHelper.newAttachmentProtocol.contactName = Axl W. Rose
    notesAndAttachmentsHelper.newAttachmentProtocol.contactEmailAddress = axl@gnr.com
    notesAndAttachmentsHelper.newAttachmentProtocol.contactPhoneNumber = 123-456-7890
    notesAndAttachmentsHelper.newAttachmentProtocol.description = a desc
    notesAndAttachmentsHelper.newAttachmentProtocol.newFile = a location
    */
    /** tests adding, removing, and replacing protocol attachments. */
    @Test
    public void testProtocolAttachment() throws Exception {
        HtmlPage initalPage = getNoteAttachmentPage();
        Assert.assertThat(initalPage.asText(), containsString("Protocol Attachments"));
        
        HtmlPage afterAddPage = addProtocolAttachment(initalPage);
        validateAddedProtocolAttachment(afterAddPage);
    }
    
    private HtmlPage addProtocolAttachment(HtmlPage initalPage) throws Exception {
        setFieldValue(initalPage, "notesAndAttachmentsHelper.newAttachmentProtocol.type.code", "1");
        setFieldValue(initalPage, "notesAndAttachmentsHelper.newAttachmentProtocol.status.code", "1");
        setFieldValue(initalPage, "notesAndAttachmentsHelper.newAttachmentProtocol.comments", "some comments");
        setFieldValue(initalPage, "notesAndAttachmentsHelper.newAttachmentProtocol.contactName", "Axl W. Rose");
        setFieldValue(initalPage, "notesAndAttachmentsHelper.newAttachmentProtocol.contactEmailAddress", "axl@gnr.com");
        setFieldValue(initalPage, "notesAndAttachmentsHelper.newAttachmentProtocol.contactPhoneNumber", "123-456-7890");
        setFieldValue(initalPage, "notesAndAttachmentsHelper.newAttachmentProtocol.description", "a description");
        setFieldValue(initalPage, "notesAndAttachmentsHelper.newAttachmentProtocol.newFile", this.getFilePath(ProtocolNoteAndAttachmentWebTest.class));
        return clickOn(initalPage, "methodToCall.addAttachmentProtocol");
    }
    
    private void validateAddedProtocolAttachment(HtmlPage afterAddPage) throws Exception {
        Map<String, String> addedValues = new HashMap<String, String>();
        //not checking type code
        addedValues.put("document.protocolList[0].attachmentProtocol[0].status.code", "1");
        addedValues.put("document.protocolList[0].attachmentProtocol[0].comments", "some comments");
        addedValues.put("document.protocolList[0].attachmentProtocol[0].contactName", "Axl W. Rose");
        addedValues.put("document.protocolList[0].attachmentProtocol[0].contactEmailAddress", "axl@gnr.com");
        addedValues.put("document.protocolList[0].attachmentProtocol[0].contactPhoneNumber", "123-456-7890");
        addedValues.put("document.protocolList[0].attachmentProtocol[0].description", "a description");
        Assert.assertThat(afterAddPage.asText(), containsString("ProtocolNoteAndAttachmentWebTest.class"));
        
        validatePage(afterAddPage, addedValues);
    }
    
    /** tests adding and replacing personnel attachments. */
    @Test
    public void testPersonnelAttachment() throws Exception {
        
    }
    
    /**
     * Gets the path of a given class file.
     * @param clazz the class
     * @return the path
     */
    private String getFilePath(Class<?> clazz) {
        URL fileUrl = getClass().getResource("/" + clazz.getCanonicalName().replaceAll(".", "/"));
        assertNotNull(fileUrl);
        return fileUrl.getPath();
    }
}