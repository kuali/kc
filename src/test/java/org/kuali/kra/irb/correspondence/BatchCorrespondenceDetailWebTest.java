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
package org.kuali.kra.irb.correspondence;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraWebTestBase;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

@PerSuiteUnitTestData(@UnitTestData(sqlFiles = {
        @UnitTestFile(filename = "classpath:sql/dml/load_PROTOCOL_ACTION_TYPE.sql", delimiter = ";")
       ,@UnitTestFile(filename = "classpath:sql/dml/load_PROTO_CORRESP_TYPE.sql", delimiter = ";") 
       ,@UnitTestFile(filename = "classpath:sql/dml/load_BATCH_CORRESPONDENCE.sql", delimiter = ";")
     }
 )
)

public class BatchCorrespondenceDetailWebTest extends KraWebTestBase {
    
    private static final String REFRESH_BUTTON = "methodToCall.start";
    private static final String ADD_BATCH_CORRESPONDENCE_DETAIL_BUTTON = "methodToCall.addBatchCorrespondenceDetail";
    private static final String DELETE_BATCH_CORRESPONDENCE_DETAIL_BUTTON = "methodToCall.deleteBatchCorrespondenceDetail.batchCorrespondenceDetail[0]}";
    
    private static final String BATCH_CORRESPONDENCE_TYPE_CODE_FIELD = "batchCorrespondence.batchCorrespondenceTypeCode";
    private static final String FINAL_ACTION_DAY_FIELD = "batchCorrespondence.finalActionDay";
    private static final String NEW_DAYS_TO_EVENT_FIELD = "newBatchCorrespondenceDetail.daysToEvent";
    private static final String NEW_PROTO_CORRESP_TYPE_CODE_FIELD = "newBatchCorrespondenceDetail.protoCorrespTypeCode";
    private static final String FIFTEEN = "15";
    private static final String RENEWAL_REMINDER_LETTER_ONE_TXT = "Renewal Reminder Letter #1 ";

    private static final String DOCUMENT_RELOAD_MSG = "Document was successfully reloaded.";
    private static final String DOCUMENT_SAVE_MSG = "Document was successfully saved.";

    private HtmlPage page;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        page = buildBatchCorrespondenceDetailPage();
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * This method tests the add and delete actions
     * @throws Exception
     */
    @Test
    public void testAddDeleteBatchCorrespondenceDetail() throws Exception {
        // just making sure assumptions are correct
        assertFalse(hasError(page));
        assertDoesNotContain(page, KeyConstants.ERROR_BATCH_CORRESPONDENCE_DAYS_TO_EVENT_INVALID);
        assertDoesNotContain(page, KeyConstants.ERROR_BATCH_CORRESPONDENCE_PROTO_CORRESP_TYPE_CODE_NOT_SPECIFIED);
        assertDoesNotContain(page, KeyConstants.ERROR_BATCH_CORRESPONDENCE_PROTO_CORRESP_TYPE_CODE_NOT_SPECIFIED);
        assertDoesNotContain(page, FIFTEEN);
        assertDoesNotContain(page, RENEWAL_REMINDER_LETTER_ONE_TXT);
        
        // Test error
        page = clickOn(getElementByName(page, ADD_BATCH_CORRESPONDENCE_DETAIL_BUTTON, true));
        assertTrue(hasError(page));
        assertContains(page, "Number of days missing.");
        assertContains(page, "Protocol correspondence type missing.");
        
        // Test add
        setFieldValue(page, NEW_DAYS_TO_EVENT_FIELD, "15");
        setFieldValue(page, NEW_PROTO_CORRESP_TYPE_CODE_FIELD, "20");
        page = clickOn(getElementByName(page, ADD_BATCH_CORRESPONDENCE_DETAIL_BUTTON, true));
        assertFalse(hasError(page));
        assertContains(page, FIFTEEN);
        assertContains(page, RENEWAL_REMINDER_LETTER_ONE_TXT);
        
        // Test delete
        page= clickOn(getElementByName(page, DELETE_BATCH_CORRESPONDENCE_DETAIL_BUTTON, true));
        assertFalse(hasError(page));
        assertDoesNotContain(page, FIFTEEN);
        assertDoesNotContain(page, RENEWAL_REMINDER_LETTER_ONE_TXT);
    }

    /**
     * This method tests the save action.
     * @throws Exception
     */
    @Test
    public void testSaveBatchCorrespondenceDetail() throws Exception {
        // just making sure assumptions are correct
        assertFalse(hasError(page));
        assertDoesNotContain(page, KeyConstants.ERROR_BATCH_CORRESPONDENCE_FINAL_ACTION_DAY_NOT_SPECIFIED);
        
        // Test error
        setFieldValue(page, FINAL_ACTION_DAY_FIELD, "");
        page= clickOn(getElementByName(page, "methodToCall.save"));
        assertTrue(hasError(page));
        assertContains(page, "Correspondence period missing.");
        
        // save document
        setFieldValue(page, FINAL_ACTION_DAY_FIELD, "60");
        page= clickOn(getElementByName(page, "methodToCall.save"));
        assertFalse(hasError(page));
        assertContains(page, DOCUMENT_SAVE_MSG);
        
        // reload document
        page= clickOn(getElementByName(page, "methodToCall.reload"));
        assertFalse(hasError(page));
        assertContains(page, DOCUMENT_RELOAD_MSG);
    }

    /**
     * Create a new instance of the batch correspondence detail page by clicking on the link to the portal page. 
     * The resulting page of the click through is a frame, so it is important to get the inner page.
     * 
     * @return <code>{@link HtmlPage}</code> instance of the batch correspondence detail page
     * @throws IOException
     */
    protected final HtmlPage buildBatchCorrespondenceDetailPage() throws Exception {
        HtmlPage centralAdminPage = clickOn(getPortalPage(), "Maintenance");
        HtmlPage page = clickOn(centralAdminPage, "Batch Correspondence", "Kuali Portal Index");
        page = getInnerPages(page).get(0);
        assertEquals("Kuali :: Batch Correspondence", page.getTitleText());
        
        // Get Protocol Renewal Reminders batch correspondence
        setFieldValue(page, BATCH_CORRESPONDENCE_TYPE_CODE_FIELD, "2");
        page = clickOn(getElementByName(page, REFRESH_BUTTON, true));
        assertFalse(hasError(page));
        
        return page;
    }

}
