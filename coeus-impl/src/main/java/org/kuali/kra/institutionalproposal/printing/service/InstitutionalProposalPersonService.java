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
package org.kuali.kra.institutionalproposal.printing.service;

import org.kuali.coeus.propdev.impl.person.ProposalPerson;

import java.util.List;

/**
 * These class have different methods to provide service like pass proposal
 * number get the list of proposal persons.
 */
public interface InstitutionalProposalPersonService {
	/**
	 * This method will return the list of proposal persons if proposal person
	 * find in development proposal.
	 * 
	 * @param proposalNumber
	 *            is a number used to get the proposal persons for this proposal
	 *            number.
	 * @return list of proposal persons
	 */
	public List<ProposalPerson> getInvestigatorsFromDevelopmentProposal(
			String proposalNumber);

}
