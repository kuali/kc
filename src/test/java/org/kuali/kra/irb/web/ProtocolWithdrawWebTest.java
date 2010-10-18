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
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Test the Withdraw action for a Protocol
 */
public class ProtocolWithdrawWebTest extends ProtocolWebTestBase {

    private static final String REASON = "this is a test";
    
    @Test
    public void testWithdraw() throws Exception {
        HtmlPage protocolActionsPage = getProtocolSubmittedRequiredSubmissionFieldsPage();
        setFieldValue(protocolActionsPage, "actionHelper.protocolWithdrawBean.reason", REASON);
        HtmlPage resultPage = clickOn(protocolActionsPage, "methodToCall.withdrawProtocol.anchor:WithdrawProtocol");
        
        assertNotNull(resultPage);
        assertDoesNotContain(resultPage, ERRORS_FOUND_ON_PAGE);
        
        String docNbr = getDocNbr(resultPage);
        ProtocolDocument protocolDocument = (ProtocolDocument) getDocument(docNbr);
        ProtocolAction protocolAction = protocolDocument.getProtocol().getLastProtocolAction();
        assertEquals(ProtocolActionType.WITHDRAWN, protocolAction.getProtocolActionTypeCode());
        assertEquals(REASON, protocolAction.getComments());
    }

}