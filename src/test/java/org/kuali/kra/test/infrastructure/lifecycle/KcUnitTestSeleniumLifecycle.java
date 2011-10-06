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

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class KcUnitTestSeleniumLifecycle extends KcUnitTestBaseLifecycle {
    
    private WebDriver driver;
    
    /**
     * Returns the current Web Driver.
     * @return the current Web Driver
     */
    public WebDriver getWebDriver() {
        return driver;
    }
    
    @Override
    protected void doPerSuiteStart() throws Throwable {
        driver = new FirefoxDriver();
        ((FirefoxDriver) driver).executeScript("window.resizeTo(1024,768)");
    }
    
    @Override
    protected void doPerSuiteStop() throws Throwable {
        driver.quit();
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

}