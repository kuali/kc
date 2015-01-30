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
package org.kuali.coeus.propdev.impl.hierarchy;

import org.kuali.coeus.propdev.impl.budget.ProposalBudgetStatus;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiography;

import java.util.List;


public interface ProposalHierarchyDao {
	public List<DevelopmentProposal> getHierarchyChildProposals(String parentProposalNumber);
    public List<String> getHierarchyChildProposalNumbers(String proposalNumber);
    public List<ProposalBudgetStatus> getHierarchyChildProposalBudgetStatuses(String proposalNumber);
    public boolean personInMultipleChildProposals(String personId, String hierarchyProposalNumber);
    public DevelopmentProposal getDevelopmentProposal(String proposalNumber);
    public String getProposalState(String proposalNumber);
    public List<ProposalPerson> isPersonOnProposal(String proposalNumber, String personId);

    }
