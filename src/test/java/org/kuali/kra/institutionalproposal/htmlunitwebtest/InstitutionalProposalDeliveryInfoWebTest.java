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
package org.kuali.kra.institutionalproposal.htmlunitwebtest;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Integration test for Delivery Info panel on Institutional Proposal Home page.
 */
public class InstitutionalProposalDeliveryInfoWebTest extends InstitutionalProposalHomeWebTest {

    /**
     * Sets the Mailing Name and Address on the Delivery Info Panel and verifies that the correct data appears
     * 
     * @throws Exception
     */
    @Test
    public void mailingInfoValidation() throws Exception {
        HtmlPage institutionalProposalPage = lookup(proposalHomePage, "mailingAddressId", "rolodexId", "1741");
        
        HtmlElement firstName = getElementById(institutionalProposalPage, "mailingFirstName");
        HtmlElement middleName = getElementById(institutionalProposalPage, "mailingMiddleName");
        HtmlElement lastName = getElementById(institutionalProposalPage, "mailingLastName");
        HtmlElement organization = getElementById(institutionalProposalPage, "mailingOrganization");
        HtmlElement addressLine1 = getElementById(institutionalProposalPage, "mailingAddressLine1");
        HtmlElement addressLine2 = getElementById(institutionalProposalPage, "mailingAddressLine2");
        HtmlElement addressLine3 = getElementById(institutionalProposalPage, "mailingAddressLine3");
        HtmlElement city = getElementById(institutionalProposalPage, "mailingCity");
        HtmlElement state = getElementById(institutionalProposalPage, "mailingState");
 
        assertNotNull(organization);
        assertEquals("Chris", firstName.asText());
        assertNull(middleName);
        assertEquals("Joyce", lastName.asText());
        assertEquals("Lockheed Missiles & Space Company", organization.asText());
        assertEquals("Procurement Office", addressLine1.asText());
        assertEquals("1111 Lockheed Way", addressLine2.asText());
        assertEquals("Orgn. 67-21, Building 107", addressLine3.asText());
        assertEquals("Sunnyvale", city.asText());
        assertEquals("CA", state.asText());
    }
}
