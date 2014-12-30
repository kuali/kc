package org.kuali.kra.award.external.award;

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
