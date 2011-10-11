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
package org.kuali.kra.award.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

/**
 * Tests the Comments tab in the Comments, Notes & Attachments page of an Award.
 */
public class AwardCommentsPanelSeleniumTest extends KcSeleniumTestBase {

    private static final String TAB_ID = "Comments";
    private static final String GENERAL_COMMENTS_TAB_ID = "Comments:General Comments";
    
    private static final String LIST_PREFIX = "document.awardList[0].awardComment[1].";
    
    private static final String GENERAL_COMMENTS_ID = "comments";
    private static final String LIST_GENERAL_COMMENTS_ID = LIST_PREFIX + GENERAL_COMMENTS_ID;
    
    private static final String COMMENTS = "Live Long and Prosper";
    
    private AwardSeleniumHelper helper;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        helper = AwardSeleniumHelper.instance(driver);
    }
    
    @After
    public void tearDown() throws Exception {
        helper = null;
        
        super.tearDown();
    }
    
    /**
     * Test the addition and saving of general comments.
     *
     * @throws Exception
     */
    @Test
    public void testCommentsOnCommentsNotesAttachmentsPage() throws Exception {
        helper.createAward();
        helper.clickAwardCommentsNotesAndAttachmentsPage();
        
        helper.openTab(TAB_ID);
        
        helper.openTab(GENERAL_COMMENTS_TAB_ID);
        helper.set(LIST_GENERAL_COMMENTS_ID, COMMENTS);

        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertElementContains(LIST_GENERAL_COMMENTS_ID, COMMENTS);
        
        helper.set(LIST_GENERAL_COMMENTS_ID, Constants.EMPTY_STRING);
        
        helper.reloadDocument();
        
        helper.assertElementContains(LIST_GENERAL_COMMENTS_ID, COMMENTS);
    }
    
}