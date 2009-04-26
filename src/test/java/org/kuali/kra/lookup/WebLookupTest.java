/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.lookup;

import org.junit.Test;
import org.kuali.kra.KraWebTestBase;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * This class tests KULRICE-984: Lookups - Relative Limit Gap
 * making sure that lookup resultSetLimits set in the DD for
 * a BO will override the system wide default.
 *
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 *
 */
public class WebLookupTest extends KraWebTestBase {

    public WebLookupTest() {
    }

    /**
     * This method tests a web lookup with a DD return limit
     *
     * @throws Exception
     */
    @Test
    public void testLookupReturnLimits() throws Exception {
        
        HtmlPage adminpage = clickOn(getPortalPage(), "Unit", "Kuali Portal Index");
        assertEquals(adminpage.getTitleText(), "Kuali Portal Index");
        HtmlPage lookupPage=clickOn(adminpage,"Sponsor Lookup");
        HtmlPage lookupResultsPage = clickOn(lookupPage, "methodToCall.search");
        assertTrue(lookupResultsPage.asText().contains("items found. Please refine your search criteria to narrow down your search."));
        assertTrue(lookupResultsPage.asText().contains("50 items found, displaying all items.") || lookupResultsPage.asText().contains("50 items retrieved, displaying all items."));
    }
       
 }
