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
 * Tests the KRA IFrame within Portal Links
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class IFrameWebPortalPageTest extends KraWebTestBase {
    
    /**
     * Verify that the when clicked on Sponsor link on portal; it opens the Sponsor Lookup page within an iFrame
     * @throws Exception
     */
    @Test
    public void testIFrameOnPortalPage() throws Exception {
        HtmlPage portalPage = getPortalPage();
        HtmlPage actionListPage = clickOn(portalPage, "Sponsor");
        FrameWindow frame = actionListPage.getFrameByName("iframeportlet");
        assertContains((HtmlPage)frame.getEnclosedPage(), "Sponsor Lookup");
        assertTrue(checkPageHasIFrame(actionListPage));   
    }
    
    /**
     * returns true if the page contains an iFrame; otherwise returns false
     * @param page the HTML web page.
     */
    public boolean checkPageHasIFrame(HtmlPage page) {
        if(getElement(page,"iframeportlet","","E-Doc")!=null)
            return true;            
        else
            return false;
        
    }

}
