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
package org.kuali.coeus.propdev.impl.hierarchy;

import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.ProposalPersonDegree;
import org.kuali.coeus.propdev.impl.state.ProposalState;

import java.util.List;


public interface ProposalHierarchyDao {
	List<DevelopmentProposal> getHierarchyChildProposals(String parentProposalNumber);
    List<String> getHierarchyChildProposalNumbers(String proposalNumber);
    boolean employeePersonInMultipleChildProposals(String personId, String hierarchyProposalNumber);
    boolean nonEmployeePersonInMultipleChildProposals(Integer rolodexId, String hierarchyProposalNumber);
    DevelopmentProposal getDevelopmentProposal(String proposalNumber);
    ProposalState getProposalState(String proposalNumber);
    List<ProposalPerson> isEmployeePersonOnProposal(String proposalNumber, String personId);
    List<ProposalPerson> isNonEmployeePersonOnProposal(String proposalNumber, Integer rolodexId);
    void deleteDegreeInfo(String proposalNumber, Integer proposalPersonNumber, ProposalPerson person);
    List<ProposalPersonDegree> getDegreeInformation(String proposalNumber, ProposalPerson person);
}
