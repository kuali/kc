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

import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * This is the integration test for Comments tab on Comments, Notes and Attachments page.
 */
public class AwardCommentWebTest extends AwardNotesAndAttachmentsWebTest {

    private static final String GENERAL_COMMENTS_DESCRIPTION_NAME = "General Comments";
    private static final String GENERAL_COMMENTS_ID = "document.awardList[0].awardGeneralComments.comments";
    private static final String SOME_COMMENTS = "Live Long and Prosper";
    
    

    /**
     * This method tests adding and saving comments on comments tab of Award Comments Notes & Attachments page.
     * @throws Exception
     */
    @Test
    public void testCommentsOnCommentsNotesAttachmentsPage() throws Exception {
        HtmlPage commentPage = getAwardNotesAndAttachmentsPage();
        assertContains(commentPage, GENERAL_COMMENTS_DESCRIPTION_NAME);
        setFieldValue(commentPage, GENERAL_COMMENTS_ID, SOME_COMMENTS);
        commentPage = saveDoc(commentPage);
        
        assertContains(commentPage, "Document was successfully saved.");
        assertContains(commentPage, SOME_COMMENTS);
        
        setFieldValue(commentPage, GENERAL_COMMENTS_ID, "");
        
        commentPage = reload(commentPage);
        assertContains(commentPage, SOME_COMMENTS);
        
        
       
    }
}
