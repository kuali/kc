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
package org.kuali.coeus.instprop.impl.api;

import com.codiform.moo.annotation.CollectionProperty;
import com.codiform.moo.annotation.Property;

import org.kuali.coeus.sys.framework.summary.FundingProposalSummaryDto;
import org.kuali.coeus.common.api.unit.UnitDto;
import org.kuali.coeus.common.framework.sponsor.SponsorDto;
import org.kuali.coeus.sys.framework.summary.InvestigatorDto;

import java.util.Date;
import java.util.List;

public class InstitutionalProposalSummaryDto {
	private Long proposalId;
    private String proposalNumber;
    private Integer sequenceNumber;
    @Property(source = "instProposalNumber")
    private String proposalLogProposalNumber;
    private Date updateTimestamp;
    @Property(translate = true)
    private InvestigatorDto principalInvestigator;
    @Property(translate = true)
    private SponsorDto sponsor;
    @CollectionProperty(source = "allFundingProposals", itemClass=FundingProposalSummaryDto.class)
    private List<FundingProposalSummaryDto> fundingProposals;
	@Property(translate = true)
	private UnitDto leadUnit;

    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public InvestigatorDto getPrincipalInvestigator() {
        return principalInvestigator;
    }

    public void setPrincipalInvestigator(InvestigatorDto principalInvestigator) {
        this.principalInvestigator = principalInvestigator;
    }

    public SponsorDto getSponsor() {
        return sponsor;
    }

    public void setSponsor(SponsorDto sponsor) {
        this.sponsor = sponsor;
    }

    public List<FundingProposalSummaryDto> getFundingProposals() {
        return fundingProposals;
    }

    public void setFundingProposals(List<FundingProposalSummaryDto> fundingProposals) {
        this.fundingProposals = fundingProposals;
    }

	public Date getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(Date updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

	public UnitDto getLeadUnit() {
		return leadUnit;
	}

	public void setLeadUnit(UnitDto leadUnit) {
		this.leadUnit = leadUnit;
	}

	public Long getProposalId() {
		return proposalId;
	}

	public void setProposalId(Long proposalId) {
		this.proposalId = proposalId;
	}

	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public String getProposalLogProposalNumber() {
		return proposalLogProposalNumber;
	}

	public void setProposalLogProposalNumber(String proposalLogProposalNumber) {
		this.proposalLogProposalNumber = proposalLogProposalNumber;
	}
}
