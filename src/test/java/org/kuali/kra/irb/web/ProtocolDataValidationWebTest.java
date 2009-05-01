/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.web;

import org.junit.Ignore;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * This is the integration test for Data Validation tab on Protocol Actions page.
 */
public class ProtocolDataValidationWebTest extends ProtocolWebTestBase {
    
    private static final String METHOD_ACTIVATE = "methodToCall.activate";
    private static final String METHOD_DEACTIVATE = "methodToCall.deactivate";
    
    /**
     * 
     * This method tests turning on Data Validation will add audit errors when none of the audit values are set on the award.
     * @throws Exception when bad happens
     */
    @Test @Ignore
    public void testTurnOnDataValidation() throws Exception{
        HtmlPage protocolActionsPageAfterActivate = clickOn(getActionsPage(), METHOD_ACTIVATE);
        //assertContains(awardActionsPageAfterActivate,INSERT_ERROR_HERE);
        //assertContains(awardActionsPageAfterActivate, INSERT_ERROR_HERE);
    }
    
    /**
     * 
     * This method tests turning off Validation after an Activation
     * @throws Exception when bad happens
     */
    @Test @Ignore
    public void testTurnOffDataValidationAfterActivate() throws Exception{
        HtmlPage protocolActionsPageAfterActivate = clickOn(getActionsPage(), METHOD_ACTIVATE);
        //assertContains(awardActionsPageAfterActivate, INSERT_ERROR_HERE);
        //assertContains(awardActionsPageAfterActivate, INSERT_ERROR_HERE);
        HtmlPage protocolActionsPageAfterDeactivate = clickOn(protocolActionsPageAfterActivate, METHOD_DEACTIVATE);
        //assertDoesNotContain(awardActionsPageAfterDeactivate, INSERT_ERROR_HERE);
        //assertDoesNotContain(awardActionsPageAfterDeactivate, INSERT_ERROR_HERE);
    }
}
