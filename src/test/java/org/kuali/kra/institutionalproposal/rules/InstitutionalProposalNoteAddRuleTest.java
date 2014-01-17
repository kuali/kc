/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.institutionalproposal.rules;

import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalNotepad;
import org.kuali.kra.institutionalproposal.rules.InstitutionalProposalNoteEventBase.ErrorType;
import org.kuali.kra.rules.TemplateRuleTest;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;

public class InstitutionalProposalNoteAddRuleTest extends KcUnitTestBase {

    @Before
    public void setUp() {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }
    
    @Test
    public void testOK() {
        new TemplateRuleTest<InstitutionalProposalNoteAddEvent, InstitutionalProposalNoteAddRule>() {
            @Override
            protected void prerequisite() {

                InstitutionalProposalNotepad institutionalProposalNotepad = new InstitutionalProposalNotepad();
                institutionalProposalNotepad.setNoteTopic("test");
                institutionalProposalNotepad.setComments("test");
                event = new InstitutionalProposalNoteAddEvent(Constants.EMPTY_STRING, null, institutionalProposalNotepad, ErrorType.HARDERROR);
                rule = new InstitutionalProposalNoteAddRule();
                expectedReturnValue = true;
            }
        };


    }

    @Test
    public void testNoNoteTopic() {
        new TemplateRuleTest<InstitutionalProposalNoteAddEvent, InstitutionalProposalNoteAddRule>() {
            @Override
            protected void prerequisite() {

                InstitutionalProposalNotepad institutionalProposalNotepad = new InstitutionalProposalNotepad();
                institutionalProposalNotepad.setNoteTopic("");
                institutionalProposalNotepad.setComments("test");
                event = new InstitutionalProposalNoteAddEvent(Constants.EMPTY_STRING, null, institutionalProposalNotepad, ErrorType.HARDERROR);
                rule = new InstitutionalProposalNoteAddRule();
                expectedReturnValue = false;
            }
        };


    }

    @Test
    public void testNoNoteText() {
        new TemplateRuleTest<InstitutionalProposalNoteAddEvent, InstitutionalProposalNoteAddRule>() {
            @Override
            protected void prerequisite() {

                InstitutionalProposalNotepad institutionalProposalNotepad = new InstitutionalProposalNotepad();
                institutionalProposalNotepad.setNoteTopic("Test");
                institutionalProposalNotepad.setComments("");
                event = new InstitutionalProposalNoteAddEvent(Constants.EMPTY_STRING, null, institutionalProposalNotepad, ErrorType.HARDERROR);
                rule = new InstitutionalProposalNoteAddRule();
                expectedReturnValue = false;
            }
        };


    }

}
