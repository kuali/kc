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
package org.kuali.kra.award.htmlunitwebtest;

import org.junit.Assert;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * This class...
 */
public class AwardAttachmentsWebTest extends AwardNotesAndAttachmentsWebTest {

    
    private static final String ONE_ATTACHMENT = "Attachments (1)";
    private static final String ONE_TYPE_NAME = "Sponsor Document";
    private static final String ATTACHMENT_CONFIRM_DELETE_MSG = "Are you sure you would like to delete the following attachment: Award Attachment AwardAttachmentsWebTest.class";
    private static final String CONFIRM_DELETE_YES_BUTTON = "methodToCall.processAnswer.button0";
    private static final String DESCRIPTION = "a description";
    private static final String TYPE_CODE_1 = "1";
    private static final String METHOD_TO_CALL_DELETE_ATTACHMENT_AWARD = "methodToCall.deleteAttachment";
    private static final String METHOD_TO_CALL_ADD_ATTACHMENT = "methodToCall.addAttachment.anchorAttachments";
    private static final String NEW_ATTACHMENT_NEW_FILE_NAME = "awardAttachmentFormBean.newAttachment.newFile";
    private static final String NEW_ATTACHMENT_DESCRIPTION_NAME = "awardAttachmentFormBean.newAttachment.description";
    private static final String NEW_ATTACHMENT_TYPE_CODE_NAME = "awardAttachmentFormBean.newAttachment.typeCode";
    private static final String NO_ATTACHMENTS = "Attachments (0)";
    private static final String FILE_1 = "AwardAttachmentsWebTest.class";
    
    
    /** tests adding and replacing award attachments. */
    @Test
    public void testAwardAttachment() throws Exception {
        HtmlPage initalPage = getAwardNotesAndAttachmentsPage();
        assertContains(initalPage, NO_ATTACHMENTS);
        
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
     *  adds a award attachment.
     *  @param initalPage the attachments page
     *  @return page after add
     */
    private HtmlPage addAttachment(HtmlPage initalPage) throws Exception {
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
        
        assertContains(afterAddPage, ONE_ATTACHMENT);
        assertContains(afterAddPage, ONE_TYPE_NAME);
        assertContains(afterAddPage, DESCRIPTION);
        assertContains(afterAddPage, FILE_1); 
    }
    
    /**
     *  deletes a award attachment.
     *  @param afterReplacePage the attachments page after replace
     *  @return page after delete
     */
    private HtmlPage deleteAttachment(HtmlPage afterReplacePage) throws Exception {
        return clickOnByName(afterReplacePage, METHOD_TO_CALL_DELETE_ATTACHMENT_AWARD, true);
    }
    
    /**
     * Validates the confirm page after clicking delete.
     * @param confirmDeletePage
     * @throws Exception
     */
    private void validateConfirmDeleteAttachment(HtmlPage confirmDeletePage) throws Exception {
        assertContains(confirmDeletePage, ATTACHMENT_CONFIRM_DELETE_MSG);
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
        assertContains(afterDeletePage, NO_ATTACHMENTS);
        assertDoesNotContain(afterDeletePage, FILE_1);
    }
}
