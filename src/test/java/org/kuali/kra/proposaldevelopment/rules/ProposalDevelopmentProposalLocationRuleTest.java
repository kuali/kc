/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.rules;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.util.ErrorMessage;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.TypedArrayList;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.bo.ProposalLocation;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.event.AddProposalLocationEvent;

public class ProposalDevelopmentProposalLocationRuleTest extends ProposalDevelopmentRuleTestBase {

    private static final String NEW_PROPOSAL_LOCATION = "newPropLocation";
    private ProposalDevelopmentProposalLocationRule rule = null;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new ProposalDevelopmentProposalLocationRule();
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        super.tearDown();
    }

    /**
     * Test a good case.
     * 
     * @throws Exception
     */
    @Test
    public void testOK() throws Exception {

        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();

        ProposalLocation newProposalLocation = new ProposalLocation();
        newProposalLocation.setLocation("test location");
        AddProposalLocationEvent addProposalLocationEvent = new AddProposalLocationEvent(Constants.EMPTY_STRING, document,
            newProposalLocation);
        assertTrue(rule.processAddProposalLocationBusinessRules(addProposalLocationEvent));
    }

    /**
     * Test adding an proposal location with an empty location name. This corresponds to a empty string type code, i.e. the user
     * didn't enter a location name.
     * 
     * @throws Exception
     */
    @Test
    public void testEmptyLocationName() throws Exception {
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        ProposalLocation newProposalLocation = new ProposalLocation();
        // newProposalLocation.setLocation("test location");
        AddProposalLocationEvent addProposalLocationEvent = new AddProposalLocationEvent(Constants.EMPTY_STRING, document,
            newProposalLocation);
        assertFalse(rule.processAddProposalLocationBusinessRules(addProposalLocationEvent));

        TypedArrayList errors = GlobalVariables.getErrorMap().getMessages(NEW_PROPOSAL_LOCATION + ".location");
        assertTrue(errors.size() == 1);

        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), KeyConstants.ERROR_REQUIRED_FOR_PROPLOCATION_NAME);
    }

}
