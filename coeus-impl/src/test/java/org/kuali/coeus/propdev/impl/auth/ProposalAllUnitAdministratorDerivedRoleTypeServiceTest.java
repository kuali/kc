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
package org.kuali.coeus.propdev.impl.auth;

import static org.junit.Assert.*;

import java.util.Set;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.ProposalPersonUnit;

public class ProposalAllUnitAdministratorDerivedRoleTypeServiceTest {

	private static final String CARD_UNIT = "IN-CARD";
	private static final String MED_UNIT = "IN-MED";
	private static final String UNIV_UNIT = "000001";

	@Test
	public void testGetApplicableUnits() {
		DevelopmentProposal proposal = new DevelopmentProposal();
		proposal.getProposalPersons().add(generateTestProposalPerson(UNIV_UNIT));
		proposal.getProposalPersons().add(generateTestProposalPerson(MED_UNIT, UNIV_UNIT));
		proposal.getProposalPersons().add(generateTestProposalPerson(CARD_UNIT));
		ProposalAllUnitAdministratorDerivedRoleTypeServiceImpl proposalAllUnitAdminService = new ProposalAllUnitAdministratorDerivedRoleTypeServiceImpl();
		Set<String> unitNumbers = proposalAllUnitAdminService.getApplicableUnits(proposal);
		assertEquals(3, unitNumbers.size());
		assertThat(unitNumbers, CoreMatchers.hasItem(UNIV_UNIT));
		assertThat(unitNumbers, CoreMatchers.hasItem(MED_UNIT));
		assertThat(unitNumbers, CoreMatchers.hasItem(CARD_UNIT));
	}
	
	ProposalPerson generateTestProposalPerson(String... unitNumbers) {
		ProposalPerson person = new ProposalPerson();
		for (String unitNumber : unitNumbers) {
			ProposalPersonUnit unit = new ProposalPersonUnit() {
				@Override
				public void refreshReferenceObject(String value) {
					
				}
			};
			unit.setUnitNumber(unitNumber);
			person.addUnit(unit);
		}
		return person;
	}
}
