/*
 * Copyright 2008 The Kuali Foundation.
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
package org.kuali.kra.lookup;

import java.net.URL;

import org.junit.Test;
import org.kuali.kra.KraWebTestBase;
import org.kuali.rice.test.data.PerTestUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;
import org.kuali.rice.test.data.UnitTestSql;
import org.kuali.rice.testharness.HtmlUnitUtil;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/*@PerTestUnitTestData(
        @UnitTestData(
                order = {UnitTestData.Type.SQL_STATEMENTS, UnitTestData.Type.SQL_FILES},
                sqlStatements = {
                        @UnitTestSql("delete from trv_acct where acct_fo_id between 101 and 301")
                        ,@UnitTestSql("delete from trv_acct_fo where acct_fo_id between 101 and 301")
                },
                sqlFiles = {
                        @UnitTestFile(filename = "classpath:testFiscalOfficers.sql", delimiter = ";")
                        , @UnitTestFile(filename = "classpath:testTravelAccounts.sql", delimiter = ";")
                }
        )
)*/
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
        final HtmlPage page = getPortalPage();

        HtmlPage lookupPage = clickOn(page, "Sponsor");
        assertEquals(lookupPage.getTitleText(), "Kuali Portal Index");
        HtmlPage lookupResultsPage = clickOn(lookupPage, "methodToCall.search");
        assertTrue(lookupResultsPage.asText().contains("items found. Please refine your search criteria to narrow down your search. 50 items found, displaying all items"));
    }
       
 }
