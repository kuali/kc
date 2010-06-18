/*
 * Copyright 2005-2010 The Kuali Foundation.
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
package org.kuali.kra.s2s.generator.impl;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.OrganizationType;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalSite;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.generator.S2STestBase;

public class PerformanceSiteV1_2GeneratorTest extends
		S2STestBase<PerformanceSiteV1_2Generator> {

	@Override
	protected Class<PerformanceSiteV1_2Generator> getFormGeneratorClass() {
		return PerformanceSiteV1_2Generator.class;
	}

	@Override
	protected void prepareData(ProposalDevelopmentDocument document)
			throws Exception {
		Organization organization = new Organization();
		organization.setOrganizationName("MIT");
		organization.setFedralEmployerId("7645926813");
		organization.setDunsNumber("236745890");
		organization.setCongressionalDistrict("Alaska");
		List<OrganizationType> organizationTypes = new ArrayList<OrganizationType>();
		OrganizationType organizationType = new OrganizationType();
		organizationType.setOrganizationTypeCode(1);
		organizationTypes.add(organizationType);
		organization.setOrganizationTypes(organizationTypes);

		Rolodex rolodex = new Rolodex();
		rolodex.setAddressLine1("Address1");
		rolodex.setAddressLine2("address2");
		rolodex.setCity("Alabama");
		rolodex.setState("AL");
		rolodex.setCountryCode("USA");
		rolodex.setFirstName("John");
		rolodex.setLastName("Doe");
		rolodex.setTitle("Title");
		rolodex.setPhoneNumber("89414454");
		rolodex.setEmailAddress("john@gmail.com");
		rolodex.setFaxNumber("114354");
		rolodex.setRolodexId(1);
		organization.setRolodex(rolodex);
		Rolodex rolodex2 = new Rolodex();
		rolodex2.setAddressLine1("addressLine1");
		rolodex2.setAddressLine2("address2");
		rolodex2.setCity("Alabama");
		rolodex2.setCounty("county");
		rolodex2.setState("AL");
		rolodex2.setCountryCode("USA");
		rolodex2.setPostalCode("487546");
		rolodex2.setTitle("Title");
		rolodex2.setRolodexId(123);

		Organization perforOrganization = new Organization();
		perforOrganization.setCongressionalDistrict("congressionalDistrict");
		perforOrganization.setRolodex(rolodex2);
		DevelopmentProposal developmentProposal = document
				.getDevelopmentProposal();
		developmentProposal
				.setPerformingOrgFromOrganization(perforOrganization);
		developmentProposal.setApplicantOrgFromOrganization(organization);
		developmentProposal
				.setProgramAnnouncementTitle("programAnnouncementTitle");
		// developmentProposal.setPerformingOrganization has side effect logic.
		// It creates proposalSite object for each organization without setting
		// the mandatory field - LOCATION_NAME. The code below sets location
		// name.
		int count = 0;
		for (ProposalSite site : developmentProposal.getProposalSites()) {
			site.setLocationName("NJ");
			site.setSiteNumber(++count);
		}
	}
}
