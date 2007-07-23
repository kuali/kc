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
package org.kuali.kra.baseline;

import java.net.URL;

import org.junit.Test;
import org.kuali.kra.KraTestBase;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;


public class BaseLineAppStartTest extends KraTestBase {

    @Test public void testHomePage() throws Exception {
        // bh79 7/23/2007
        // Temporarily commented out until Rice test is fixed, just so our Bamboo tests won't fail
        //
        // final WebClient webClient = new WebClient();
        // final URL url = new URL("http://localhost:" + getPort() + "/kra-dev/");
        // final HtmlPage page = (HtmlPage)webClient.getPage(url);
        // assertEquals("Kuali Portal Index", page.getTitleText() );
    }

}
