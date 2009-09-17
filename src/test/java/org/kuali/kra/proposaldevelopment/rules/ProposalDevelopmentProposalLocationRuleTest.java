/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
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
import org.kuali.kra.bo.Organization;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.bo.ProposalSite;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.event.AddProposalSiteEvent;
import org.kuali.rice.kns.util.ErrorMessage;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.TypedArrayList;

public class ProposalDevelopmentProposalLocationRuleTest extends ProposalDevelopmentRuleTestBase {

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

        ProposalSite newProposalSite = new ProposalSite();
        newProposalSite.setLocationName("test location");
        newProposalSite.setOrganization(new Organization());
        AddProposalSiteEvent addProposalLocationEvent = new AddProposalSiteEvent(Constants.EMPTY_STRING, document,
            newProposalSite);
        assertTrue(rule.processAddProposalSiteBusinessRules(addProposalLocationEvent));
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
        ProposalSite newProposalSite = new ProposalSite();
        newProposalSite.setOrganization(new Organization());
        AddProposalSiteEvent addProposalLocationEvent = new AddProposalSiteEvent(Constants.EMPTY_STRING, document,
            newProposalSite);
        assertFalse(rule.processAddProposalSiteBusinessRules(addProposalLocationEvent));

        TypedArrayList errors = GlobalVariables.getErrorMap().getMessages("locationName");
        assertTrue(errors.size() == 1);

        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), KeyConstants.ERROR_PROPOSAL_SITES_LOCATION_NAME_REQUIRED);
    }

    /**
     * Test adding an proposal location with no Organization.
     * 
     * @throws Exception
     */
    @Test
    public void testEmptyAddress() throws Exception {
        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        ProposalSite newProposalSite = new ProposalSite();
        newProposalSite.setLocationName("test location");
        AddProposalSiteEvent addProposalLocationEvent = new AddProposalSiteEvent(Constants.EMPTY_STRING, document,
            newProposalSite);
        assertFalse(rule.processAddProposalSiteBusinessRules(addProposalLocationEvent));

        TypedArrayList errors = GlobalVariables.getErrorMap().getMessages("address");
        assertTrue(errors.size() == 1);

        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertEquals(message.getErrorKey(), KeyConstants.ERROR_PROPOSAL_SITES_ADDRESS_REQUIRED);
    }
}
