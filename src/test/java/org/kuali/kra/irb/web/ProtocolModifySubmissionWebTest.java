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
package org.kuali.kra.irb.web;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ProtocolModifySubmissionWebTest extends ProtocolWebTestBase {
    
    private static final String IS_BILLABLE_FIELD =  "actionHelper.protocolModifySubmissionBean.billable";
    private static final String IS_BILLABLE_VALUE =  "on";
    private static final String SUBMIT_ACTION_BUTTON = "methodToCall.modifySubmsionAction.anchor:ModifySubmissionRequest";
    
    /**
     * Tests billable authorization to make sure that the IRB Admin is allowed to edit billable checkbox
     * @throws Exception
     */
    @Test
    public void testBillableHasPermission() throws Exception {
        HtmlPage protocolActionsPage = getProtocolSubmittedRequiredSubmissionFieldsPage();
        
        // Make sure the billable field exists
        HtmlElement billableField = getElement(protocolActionsPage, IS_BILLABLE_FIELD);
        assertNotNull(billableField);
        
        // Select billable and submit
        setFieldValue(protocolActionsPage, IS_BILLABLE_FIELD, IS_BILLABLE_VALUE);
        protocolActionsPage = clickOn(protocolActionsPage, SUBMIT_ACTION_BUTTON);

        // Check billable value after submit
        billableField = getElement(protocolActionsPage, IS_BILLABLE_FIELD);
        assertEquals(IS_BILLABLE_VALUE, getFieldValue(protocolActionsPage, IS_BILLABLE_FIELD));
    }
    
    /**
     * Tests billable authorization to make sure users other than IRB Admin are not allowed to edit billable checkbox
     * @throws Exception
     */
    @Test
    public void testBillableHasNoPermission() throws Exception {
        HtmlPage protocolActionsPage = getProtocolSubmittedRequiredSubmissionFieldsPage();
        String documentNumber = getDocNbr(protocolActionsPage);
        loginAsTester();
        HtmlPage protocolPage = docSearch(documentNumber);
        protocolActionsPage = clickOnTab(protocolPage, PROTOCOL_ACTIONS_LINK_NAME);
        
        // Make sure the billable field does not exist
        HtmlElement billableField = getElement(protocolActionsPage, IS_BILLABLE_FIELD);
        assertNull(billableField);
    }

}