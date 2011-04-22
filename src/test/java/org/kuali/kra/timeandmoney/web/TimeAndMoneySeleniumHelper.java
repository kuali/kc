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
package org.kuali.kra.timeandmoney.web;

import org.kuali.kra.test.infrastructure.KcSeleniumHelper;
import org.openqa.selenium.WebDriver;

/**
 * Base class for all integration tests for Time and Money documents.
 */
public class TimeAndMoneySeleniumHelper extends KcSeleniumHelper {
    
    private static final String RETURN_TO_AWARD_LINK_NAME = "methodToCall.returnToAward";
    
    private static TimeAndMoneySeleniumHelper helper;
    
    public static TimeAndMoneySeleniumHelper instance(WebDriver driver) {
        if (helper == null) {
            helper = new TimeAndMoneySeleniumHelper(driver);
        }
        return helper;
    }
    
    private TimeAndMoneySeleniumHelper(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Return to the Award document from the Time & Money document.
     */
    public void returnToAwardDocument() {
        click(RETURN_TO_AWARD_LINK_NAME);
    }

}