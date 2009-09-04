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
package org.kuali.kra.award.htmlunitwebtest;

import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.text.StringContains.containsString;

import java.net.URL;

import org.junit.Assert;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * This class...
 */
public class AwardAttachmentsWebTest extends AwardNotesAndAttachmentsWebTest {

    
    private static final String ONE_ATTACHMENT = "Attachments (1)";
    private static final String ONE_TYPE_NAME = "Notice of Award";
    private static final String ATTACHMENT_CONFIRM_DELETE_MSG = "Are you sure you want to delete Attachment 1";
    private static final String CONFIRM_DELETE_YES_BUTTON = "methodToCall.processAnswer.button0";
    private static final String DESCRIPTION = "a description";
    private static final String TYPE_CODE_1 = "1";
    private static final String METHOD_TO_CALL_DELETE_ATTACHMENT_PERSONNEL = "methodToCall.deleteAttachment";
    private static final String METHOD_TO_CALL_ADD_ATTACHMENT = "methodToCall.addAttachment";
    private static final String NEW_ATTACHMENT_NEW_FILE_NAME = "awardAttachmentFormBean.newAttachment.newFile";
    private static final String NEW_ATTACHMENT_DESCRIPTION_NAME = "awardAttachmentFormBean.newAttachment.description";
    private static final String NEW_ATTACHMENT_TYPE_CODE_NAME = "awardAttachmentFormBean.newAttachment.typeCode";
    private static final String NO_ATTACHMENTS = "Attachments (0)";
    private static final String FILE_1 = "AwardAttachmentsWebTest.class";
    
    
    /** tests adding and replacing personnel attachments. */
    @Test
    public void testPersonnelAttachment() throws Exception {
        HtmlPage initalPage = getAwardNotesAndAttachmentsPage();
        Assert.assertThat(initalPage.asText(), containsString(NO_ATTACHMENTS));
        
        HtmlPage afterAddPage = addAttachment(initalPage);  
        validateAddedAttachment(afterAddPage);
        
        HtmlPage confirmDeletePage = deleteAttachment(afterAddPage);
        validateConfirmDeleteAttachment(confirmDeletePage);
        
        HtmlPage afterDeletePage = confirmDeleteAttachment(confirmDeletePage);
        validateDeletedAttachment(afterDeletePage);
        
        HtmlPage afterSaveDeletePage = saveDoc(afterDeletePage);
        validateDeletedAttachment(afterSaveDeletePage);
    }
    
    /**
     *  adds a personnel attachment.
     *  @param initalPage the attachments page
     *  @return page after add
     */
    private HtmlPage addAttachment(HtmlPage initalPage) throws Exception {
        //should find the PI - Terry Durkin
        setFieldValue(initalPage, NEW_ATTACHMENT_TYPE_CODE_NAME, TYPE_CODE_1);
        setFieldValue(initalPage, NEW_ATTACHMENT_DESCRIPTION_NAME, DESCRIPTION);
        setFieldValue(initalPage, NEW_ATTACHMENT_NEW_FILE_NAME, this.getFilePath(AwardAttachmentsWebTest.class));
        return clickOnByName(initalPage, METHOD_TO_CALL_ADD_ATTACHMENT, true);
    }     
    
    /**
     *  validates page after add.
     *  @param afterAddPage the attachments page after add
     */
    private void validateAddedAttachment(HtmlPage afterAddPage) throws Exception {
        
        Assert.assertThat(afterAddPage.asText(), containsString(ONE_ATTACHMENT));
        Assert.assertThat(afterAddPage.asText(), containsString(ONE_TYPE_NAME));
        Assert.assertThat(afterAddPage.asText(), containsString(DESCRIPTION));
        Assert.assertThat(afterAddPage.asText(), containsString(FILE_1)); 
    }
    
    /**
     *  deletes a personnel attachment.
     *  @param afterReplacePage the attachments page after replace
     *  @return page after delete
     */
    private HtmlPage deleteAttachment(HtmlPage afterReplacePage) throws Exception {
        return clickOnByName(afterReplacePage, METHOD_TO_CALL_DELETE_ATTACHMENT_PERSONNEL, true);
    }
    
    /**
     * Validates the confirm page after clicking delete.
     * @param confirmDeletePage
     * @throws Exception
     */
    private void validateConfirmDeleteAttachment(HtmlPage confirmDeletePage) throws Exception {
        Assert.assertThat(confirmDeletePage.asText(), containsString(ATTACHMENT_CONFIRM_DELETE_MSG));
    }
    
    /**
     *  clicks "yes" on the confirm page.
     *  @param afterAddPage the attachments page after add
     *  @return page after replace
     */
    private HtmlPage confirmDeleteAttachment(HtmlPage afterAddPage) throws Exception {
        return clickOnByName(afterAddPage, CONFIRM_DELETE_YES_BUTTON, true);
    }
    
    /**
     *  validates page after delete.
     *  @param afterDeletePage the attachments page after delete
     */
    private void validateDeletedAttachment(HtmlPage afterDeletePage) throws Exception {
        Assert.assertThat(afterDeletePage.asText(), containsString(NO_ATTACHMENTS));
        Assert.assertThat(afterDeletePage.asText(), not(containsString(FILE_1)));
    }
    
    /**
     * Gets the path of a given class file.
     * @param clazz the class
     * @return the path
     */
    private String getFilePath(Class<?> clazz) {
        URL fileUrl = getClass().getResource("/" + clazz.getCanonicalName().replaceAll("\\.", "/") + ".class");
        assertNotNull(fileUrl);
        return fileUrl.getPath();
    }
}
