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
package org.kuali.coeus.propdev.impl.person;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;

import java.util.List;

public interface ProposalPersonService {
    public String getPersonName(ProposalDevelopmentDocument doc, String userId);

    public List<ProposalPerson> getProposalKeyPersonnel(String proposalNumber);
    

    /**
     * This method is to get division name using the 4th level node on the Unit hierarchy
     * 
     * @param proposalPerson Proposal person.
     * @return divisionName based on the 4th level node on the Unit hierarchy.
     */
    public String getProposalPersonDivisionName(ProposalPerson proposalPerson);

    /**
     * This method is to get list of ProposalPersons by matching partial name.
     * Wildcards work as well.
     *
     * @param partialName String representing partial name from search screen
     * @return
     */
    public List<ProposalPerson> getProposalPersonsByPartialName(String partialName);

}
