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
package org.kuali.kra.committee.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class CommitteeActionsWebTest extends CommitteeWebTestBase {
    private HtmlPage actionsPage;

    /**
     * The set up method calls the parent super method and gets the 
     * committee page after saving initial required fields.
     * Then navigate to committee members page
     * @see org.kuali.kra.irb.web.CommitteeWebTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.actionsPage = getActionsPage();
        
    }

    /**
     * This method calls parent tear down method and than sets membersPage to null
     * @see org.kuali.kra.irb.web.CommitteeWebTestBase#tearDown()
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        this.actionsPage = null;
    }

    /**
     * This method test the Generate Batch Correspondence action.
     * @throws Exception
     */
    @Test
    public void testGenerateRenewalReminders() throws Exception {
        actionsPage = clickOn(getElementByName(actionsPage, "methodToCall.generateBatchCorrespondence", true));

        assertTrue(hasError(actionsPage));
        assertContains(actionsPage, "Batch type missing.");
        assertContains(actionsPage, "Start date missing.");
        assertContains(actionsPage, "End date missing.");

        
        setFieldValue(actionsPage, "committeeHelper.committeeActionsHelper.generateBatchCorrespondenceTypeCode", "1");
        setFieldValue(actionsPage, "committeeHelper.committeeActionsHelper.generateStartDate", "01/01/2010");
        setFieldValue(actionsPage, "committeeHelper.committeeActionsHelper.generateEndDate", "12/31/2010");
        actionsPage = clickOn(getElementByName(actionsPage, "methodToCall.generateBatchCorrespondence", true));

        assertFalse(hasError(actionsPage));
        assertContains(actionsPage, "Generated Batch Correspondence");
        assertContains(actionsPage, "01/01/2010 through 12/31/2010");
    }
    
    /**
     * This method test the Filter Batch Correspondence History action.
     * @throws Exception
     */
    @Test
    public void testFilterBatchCorrespondenceHistory() throws Exception {
        actionsPage = clickOn(getElementByName(actionsPage, "methodToCall.filterBatchCorrespondenceHistory", true));

        assertTrue(hasError(actionsPage));
        assertContains(actionsPage, "Batch type missing.");

        
        setFieldValue(actionsPage, "committeeHelper.committeeActionsHelper.historyBatchCorrespondenceTypeCode", "1");
        setFieldValue(actionsPage, "committeeHelper.committeeActionsHelper.historyStartDate", "01/01/2010");
        setFieldValue(actionsPage, "committeeHelper.committeeActionsHelper.historyEndDate", "12/31/2010");
        actionsPage = clickOn(getElementByName(actionsPage, "methodToCall.filterBatchCorrespondenceHistory", true));

        assertFalse(hasError(actionsPage));
        assertContains(actionsPage, "Batch Type: Protocol Renewal Reminders");
        assertContains(actionsPage, "01/01/2010 through 12/31/2010");
    }
    
    /**
     * This method test the Print action.
     * 
     * No reports for printing are selected when pressing the print button 
     * and thus cause an error message.
     */
    @Test
    public void testPrintErrorMessages() throws Exception {
        actionsPage = clickOn(getElementByName(actionsPage, "methodToCall.printCommitteeDocument", true));

        assertTrue(hasError(actionsPage));
        assertContains(actionsPage, "No report selected for printing.");
    }


}
