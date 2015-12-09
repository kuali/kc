/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.s2sgen.impl.generate.support;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class is used to test RRPersonalDataV1_1 form
 */
public class RRPersonalDataV1_2GeneratorTest extends
		S2STestBase<RRPersonalDataV1_2Generator> {

	@Override
	protected String getFormGeneratorName() {
		return RRPersonalDataV1_2Generator.class.getSimpleName();
	}

	@Override
	protected void prepareData(ProposalDevelopmentDocument document)
			throws Exception {
		ProposalPerson proposalPerson = new ProposalPerson();
		proposalPerson.setProposalPersonRoleId("PI");
		proposalPerson.setFirstName("Philip");
		proposalPerson.setLastName("Berg");
		proposalPerson.setSocialSecurityNumber("1234");
		proposalPerson.setGender("F");
		proposalPerson.setRace("Asian");
		proposalPerson.setHandicapType("Hearing");
		proposalPerson.setCountryOfCitizenship("USA");
		proposalPerson.setOptInCertificationStatus(true);
		proposalPerson.setOptInUnitStatus(true);
		proposalPerson.setProposalPersonNumber(1001);
		proposalPerson.setRace("English");
        proposalPerson.setDevelopmentProposal(document.getDevelopmentProposal());


        ProposalPerson keyPerson = new ProposalPerson();
		keyPerson.setProposalPersonRoleId("COI");
		keyPerson.setFirstName("Terry");
		keyPerson.setLastName("Durkin");
		keyPerson.setSocialSecurityNumber("9876");
		keyPerson.setGender("M");
		keyPerson.setRace("American Indian or Alaska Native");
		keyPerson.setHandicapType("Visual");
		keyPerson.setCountryOfCitizenship("USA");
		keyPerson.setOptInCertificationStatus(true);
		keyPerson.setOptInUnitStatus(true);
		keyPerson.setProposalPersonNumber(1002);
		keyPerson.setRace("English");
        keyPerson.setDevelopmentProposal(document.getDevelopmentProposal());

		List<ProposalPerson> proposalPersonList = new ArrayList<ProposalPerson>();
		proposalPersonList.add(proposalPerson);
		proposalPersonList.add(keyPerson);
		document.getDevelopmentProposal()
				.setProposalPersons(proposalPersonList);
	}
}
