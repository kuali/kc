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
package org.kuali.kra.test.infrastructure.lifecycle;

import org.kuali.rice.test.web.HtmlUnitUtil;
import org.openqa.selenium.server.SeleniumServer;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class KcUnitTestSeleniumLifecycle extends KcUnitTestBaseLifecycle {
    
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 4444;
    private static final String BROWSER_START_COMMAND = "*firefox";
    private static final String BROWSER_PROTOCOL = "http";
    private static final String BROWSER_ADDRESS = "localhost";
    
    private SeleniumServer seleniumServer;
    private Selenium selenium;
    
    public Selenium getSelenium() {
        return selenium;
    }
    
    @Override
    protected void doPerSuiteStart() throws Throwable {
        String browserUrl = getBrowserUrl(BROWSER_PROTOCOL, BROWSER_ADDRESS, HtmlUnitUtil.getPort());
        
        seleniumServer = new SeleniumServer();
        seleniumServer.start();
        
        selenium = new DefaultSelenium(SERVER_HOST, SERVER_PORT, BROWSER_START_COMMAND, browserUrl);
        selenium.start();
    }
    
    @Override
    protected void doPerSuiteStop() throws Throwable {
        selenium.stop();
        
        seleniumServer.stop();
    }

    @Override
    protected void doPerClassStart() throws Throwable {
    }

    @Override
    protected void doPerClassStop() throws Throwable {
    }

    @Override
    protected void doPerTestStart(boolean transactional) throws Throwable {
    }

    @Override
    protected void doPerTestStop() throws Throwable {
    }
    
    private static String getBrowserUrl(String protocol, String address, int port) {
        return protocol + "://" + address + ":" + Integer.toString(port) + "/";
    }

}
