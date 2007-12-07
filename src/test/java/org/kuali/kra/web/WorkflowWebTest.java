/*
 * Copyright 2007 The Kuali Foundation.
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.web;

import org.junit.Test;
import org.kuali.kra.KraWebTestBase;

import com.gargoylesoftware.htmlunit.html.FrameWindow;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Tests the KRA Workflow web interface
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class WorkflowWebTest extends KraWebTestBase {

    /**
     * Verify that the Outbox link shows up on the Action List page.
     * @throws Exception
     */
    @Test
    public void testActionListOutbox() throws Exception {
        HtmlPage portalPage = getPortalPage();
        HtmlPage actionListPage = clickOn(portalPage, "Action List");
        FrameWindow frame = actionListPage.getFrameByName("iframeportlet");
        assertContains((HtmlPage)frame.getEnclosedPage(), "Outbox");
    }

}
