/*
 * Copyright 2008 The Kuali Foundation.
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
package org.kuali.kra.proposaldevelopment.web;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Unit Test Case for testing user interface functionality of the Delivery Info Panel on the 
 * <code>{@link ProposalDevelopmentDocument}</code> page.
 * 
 */
public class DeliveryInfoPanelWebTest extends ProposalDevelopmentWebTestBase {
    
    /**
     * Sets the Mailing Name and Address on the Delivery Info Panel and verifies that the correct data appears
     * 
     * @throws Exception
     */
    @Test
    public void mailingInfoValidation() throws Exception {
        HtmlPage proposalDevelopmentPage = lookup(getProposalDevelopmentPage(), "mailingAddressId", "rolodexId", "1741");
        
        HtmlElement firstName = getElementById(proposalDevelopmentPage, "mailingFirstName");
        HtmlElement middleName = getElementById(proposalDevelopmentPage, "mailingMiddleName");
        HtmlElement lastName = getElementById(proposalDevelopmentPage, "mailingLastName");
        HtmlElement organization = getElementById(proposalDevelopmentPage, "mailingOrganization");
        HtmlElement addressLine1 = getElementById(proposalDevelopmentPage, "mailingAddressLine1");
        HtmlElement addressLine2 = getElementById(proposalDevelopmentPage, "mailingAddressLine2");
        HtmlElement addressLine3 = getElementById(proposalDevelopmentPage, "mailingAddressLine3");
        HtmlElement city = getElementById(proposalDevelopmentPage, "mailingCity");
        HtmlElement state = getElementById(proposalDevelopmentPage, "mailingState");
 
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
