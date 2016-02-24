/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.institutionalproposal.rules;

import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalNotepad;
import org.kuali.kra.institutionalproposal.rules.InstitutionalProposalNoteEventBase.ErrorType;
import org.kuali.kra.rules.TemplateRuleTest;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;

public class InstitutionalProposalNoteAddRuleTest extends KcIntegrationTestBase {

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
