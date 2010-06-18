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
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalSite;
import org.kuali.kra.proposaldevelopment.bo.ProposalYnq;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.generator.S2STestBase;

/**
 * 
 * This class tests PHS398CoverPageSupplement form V1.3
 */

public class PHS398CoverPageSupplementV1_3GeneratorTest extends
		S2STestBase<PHS398CoverPageSupplementV1_3Generator> {

	@Override
	protected Class<PHS398CoverPageSupplementV1_3Generator> getFormGeneratorClass() {
		return PHS398CoverPageSupplementV1_3Generator.class;
	}

	@Override
	protected void prepareData(ProposalDevelopmentDocument document)
			throws Exception {
		DevelopmentProposal developmentProposal = document
				.getDevelopmentProposal();
		developmentProposal.setActivityTypeCode("8");
		Rolodex rolodex = new Rolodex();
		rolodex.setState("AL");
		rolodex.setAddressLine1("Address1");
		rolodex.setAddressLine2("address2");
		rolodex.setCity("Bangkok");
		rolodex.setCountryCode("USA");
		rolodex.setFirstName("John");
		rolodex.setLastName("Doe");
		rolodex.setMiddleName("A");
		rolodex.setPhoneNumber("982345");
		rolodex.setTitle("ProjectTitle");
		rolodex.setFaxNumber("09823456");
		rolodex.setEmailAddress("john@gmail.com");
		rolodex.setRolodexId(1234);
		Organization organization = new Organization();
		organization.setRolodex(rolodex);
		developmentProposal.setApplicantOrgFromOrganization(organization);
		ProposalPerson person = new ProposalPerson();
		person.setProposalPersonRoleId("PI");
		person.setFirstName("MARTIN");
		person.setLastName("MERRILL");
		ProposalYnq proposalYnq = new ProposalYnq();
		proposalYnq.setAnswer("Y");
		proposalYnq.setQuestionId("17");
		ProposalYnq proposalYnq2 = new ProposalYnq();
		proposalYnq2.setAnswer("Y");
		proposalYnq2.setExplanation("CelA, CelB");
		proposalYnq2.setQuestionId("18");
		ProposalYnq proposalYnq3 = new ProposalYnq();
		proposalYnq3.setAnswer("Y");
		proposalYnq3.setQuestionId("13");
		List<ProposalYnq> proListYnq = new ArrayList<ProposalYnq>();
		proListYnq.add(proposalYnq);
		proListYnq.add(proposalYnq2);
		proListYnq.add(proposalYnq3);
		developmentProposal.setProposalYnqs(proListYnq);

		person.setOptInCertificationStatus("Y");
		person.setOptInUnitStatus("Y");
		person.setProposalPersonNumber(1001);
		List<ProposalPerson> argProposalPersons = new ArrayList<ProposalPerson>();
		argProposalPersons.add(person);
		developmentProposal.setProposalPersons(argProposalPersons);
        person.setProposalPersonRoleId(Constants.PRINCIPAL_INVESTIGATOR_ROLE);
		document.setDevelopmentProposal(developmentProposal);
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
