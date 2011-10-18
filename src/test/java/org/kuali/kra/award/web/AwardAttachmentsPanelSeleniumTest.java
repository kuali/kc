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
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

/**
 * Tests the Attachments tab in the Comments, Notes & Attachments page of an Award.
 */
public class AwardAttachmentsPanelSeleniumTest extends KcSeleniumTestBase {
    
    private static final String TAB_ID = "Attachments";
    private static final String TABLE_ID = "attachments-table";
    
    private static final String BEAN_PREFIX = "awardAttachmentFormBean.newAttachment.";
    
    private static final String NEW_FILE_ID = "newFile";
    private static final String DESCRIPTION_ID = "description";
    private static final String TYPE_CODE_ID = "typeCode";
    private static final String BEAN_NEW_FILE_ID = BEAN_PREFIX + NEW_FILE_ID;
    private static final String BEAN_DESCRIPTION_ID = BEAN_PREFIX + DESCRIPTION_ID;
    private static final String BEAN_TYPE_CODE_ID = BEAN_PREFIX + TYPE_CODE_ID;

    private static final String ATTACHMENTS_LABEL = "Attachments (%d)";
    private static final String TYPE_CODE = "Sponsor Document";
    private static final String DESCRIPTION = "a description";
    
    private static final String DELETE_ATTACHMENT_BUTTON = "methodToCall.deleteAttachment";
    private static final String ADD_ATTACHMENT_BUTTON = "methodToCall.addAttachment";
    
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
     * Test the addition and deletion of attachments.
     *
     * @throws Exception
     */
    @Test
    public void testAddDeleteAttachment() throws Exception {
        helper.createAward();
        helper.clickAwardCommentsNotesAndAttachmentsPage();

        helper.assertPageContains(String.format(ATTACHMENTS_LABEL, 0));
        
        helper.openTab(TAB_ID);
        helper.set(BEAN_TYPE_CODE_ID, TYPE_CODE);
        helper.set(BEAN_DESCRIPTION_ID, DESCRIPTION);

        helper.set(BEAN_NEW_FILE_ID, helper.getAbsoluteFilePath(AwardAttachmentsPanelSeleniumTest.class));
        helper.click(ADD_ATTACHMENT_BUTTON);
        
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertPageContains(String.format(ATTACHMENTS_LABEL, 1));
        helper.assertTableRowCount(TABLE_ID, 3);
        
        helper.assertTableCellValueContains(TABLE_ID, TYPE_CODE);
        helper.assertTableCellValueContains(TABLE_ID, DESCRIPTION);
        helper.assertTableCellValueContains(TABLE_ID, helper.getSimpleFilePath(AwardAttachmentsPanelSeleniumTest.class));
        
        helper.click(DELETE_ATTACHMENT_BUTTON);
        helper.clickYesAnswer();
        
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertPageContains(String.format(ATTACHMENTS_LABEL, 0));
        helper.assertTableRowCount("attachments-table", 2);
    }

}