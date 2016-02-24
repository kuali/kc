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
package org.kuali.kra.external.award;

import org.kuali.kra.external.service.KcDtoServiceBase;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;

public class ProposalDtoService extends KcDtoServiceBase<ProposalDTO, InstitutionalProposal> {

	@Override
	public ProposalDTO buildDto(InstitutionalProposal proposal) {
		if (proposal != null) {
			ProposalDTO dto = new ProposalDTO();
			dto.setProposalNumber(proposal.getProposalNumber());
			dto.setRequestedStartDateTotal(proposal.getRequestedStartDateTotal());
			dto.setRequestedEndDateTotal(proposal.getRequestedEndDateTotal());
			dto.setProposalTotalAmount(proposal.getTotalCost());
			dto.setTotalDirectCostTotal(proposal.getTotalDirectCostTotal());
			dto.setTotalIndirectCostTotal(proposal.getTotalIndirectCostTotal());
			dto.setProposalLastUpdateDate(proposal.getUpdateTimestamp());
			dto.setAwardTypeCode(proposal.getAwardTypeCode());
			dto.setSponsorCode(proposal.getSponsorCode());
			dto.setStatusCode(proposal.getStatusCode());
			dto.setCfdaNumber(proposal.getCfdaNumber());
			dto.setTitle(proposal.getTitle());
			dto.setSponsorAwardNumber(proposal.getSponsorAwardNumber());
			return dto;
		} else {
			return null;
		}
	}
}
