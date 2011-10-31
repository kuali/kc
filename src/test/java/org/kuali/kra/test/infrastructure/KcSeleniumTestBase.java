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
package org.kuali.kra.test.infrastructure;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runner.notification.RunListener;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.test.infrastructure.lifecycle.KcUnitTestSeleniumLifecycle;
import org.kuali.rice.test.web.HtmlUnitUtil;
import org.openqa.selenium.WebDriver;

@RunWith(KcSeleniumTestRunner.class)
public class KcSeleniumTestBase extends KcUnitTestBase {
    
    private static final String BROWSER_PROTOCOL = "http";
    private static final String BROWSER_ADDRESS = "127.0.0.1";
    private static final String PORTAL_ADDRESS = "kc-dev";
    
    protected static WebDriver driver;
    
    private static KcUnitTestSeleniumLifecycle LIFECYCLE = new KcUnitTestSeleniumLifecycle();
    private static RunListener RUN_LISTENER = new KcUnitTestRunListener(LIFECYCLE);
    
    private String defaultHandle = Constants.EMPTY_STRING;

    @BeforeClass
    public static final void seleniumBeforeClass() {
        if (!LIFECYCLE.isPerSuiteStarted()) {
            LIFECYCLE.startPerSuite();
            driver = LIFECYCLE.getWebDriver();
        }
        LIFECYCLE.startPerClass();
    }
    
    @AfterClass
    public static final void seleniumAfterClass() {
        LIFECYCLE.stopPerClass();
    }
    
    @Before
    public void seleniumBeforeTest() {
        LIFECYCLE.startPerTest(transactional);
    }
    
    @After
    public void seleniumAfterTest() {
        LIFECYCLE.stopPerTest();
    }
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        driver.get(BROWSER_PROTOCOL + "://" + BROWSER_ADDRESS + ":" + HtmlUnitUtil.getPort().toString() + "/" + PORTAL_ADDRESS);
        defaultHandle = driver.getWindowHandle();
    }
    
    @After
    public void tearDown() throws Exception {
        for (String handle : driver.getWindowHandles()) {
            if (!StringUtils.equals(handle, defaultHandle)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
        driver.get(BROWSER_PROTOCOL + "://" + BROWSER_ADDRESS + ":" + HtmlUnitUtil.getPort().toString() + "/" + PORTAL_ADDRESS);
        
        super.tearDown();
    }
    
    /**
     * This method returns the {@code RunListener} needed to ensure the KC persistent lifecycles shut down properly
     * @return the RunListener responsible for shutting down all KC persistent lifecycles
     */
    public static RunListener getRunListener() {
        return RUN_LISTENER;
    }

}