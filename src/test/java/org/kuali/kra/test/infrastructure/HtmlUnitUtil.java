/*
 * Copyright 2007-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl2.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.test.infrastructure;

import java.net.URL;

import org.kuali.rice.core.api.config.property.ConfigContext;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

public class HtmlUnitUtil {

    public static final String BASE_URL = "http://localhost:" + getPort() + "/knstest";
    
    public static HtmlPage gotoPageAndLogin(String url) throws Exception {
        final WebClient webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER_6);
        HtmlPage loginPage = (HtmlPage)webClient.getPage(new URL(url));
        HtmlForm htmlForm = (HtmlForm)loginPage.getForms().get(0);
        htmlForm.getInputByName("__login_user").setValueAttribute("quickstart");
        HtmlSubmitInput button = (HtmlSubmitInput)htmlForm.getInputByValue("Login");
        return (HtmlPage)button.click();
    }
    
    public static boolean pageContainsText(HtmlPage page, String text) {
        return page.asText().contains(text);
    }
    
    public static Integer getPort() {
	return new Integer(ConfigContext.getCurrentContextConfig().getProperty("kns.test.port"));
    }
}
