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
package org.kuali.coeus.sys.framework.summary;

import com.codiform.moo.annotation.Property;

public class FundingProposalSummaryDto {
	@Property(source = "mvel:award.awardId")
	private Long awardId;
	@Property(source = "mvel:proposal.proposalId")
	private Long proposalId;
	@Property(source = "mvel:proposal.proposalNumber")
	private String proposalNumber;
	@Property(source = "mvel:proposal.sequenceNumber")
	private Integer proposalSequenceNumber;
	@Property(source = "mvel:award.awardNumber")
	private String awardNumber;
	@Property(source = "mvel:award.sequenceNumber")
	private Integer awardSequenceNumber;

	public String getProposalNumber() {
		return proposalNumber;
	}

	public void setProposalNumber(String proposalNumber) {
		this.proposalNumber = proposalNumber;
	}

	public Integer getProposalSequenceNumber() {
		return proposalSequenceNumber;
	}

	public void setProposalSequenceNumber(Integer proposalSequenceNumber) {
		this.proposalSequenceNumber = proposalSequenceNumber;
	}

	public String getAwardNumber() {
		return awardNumber;
	}

	public void setAwardNumber(String awardNumber) {
		this.awardNumber = awardNumber;
	}

	public Integer getAwardSequenceNumber() {
		return awardSequenceNumber;
	}

	public void setAwardSequenceNumber(Integer awardSequenceNumber) {
		this.awardSequenceNumber = awardSequenceNumber;
	}

	public Long getAwardId() {
		return awardId;
	}

	public void setAwardId(Long awardId) {
		this.awardId = awardId;
	}

	public Long getProposalId() {
		return proposalId;
	}

	public void setProposalId(Long proposalId) {
		this.proposalId = proposalId;
	}
}
