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
package org.kuali.kra.institutionalproposal.home;

import org.kuali.coeus.common.framework.keyword.AbstractScienceKeyword;
import org.kuali.coeus.common.framework.keyword.ScienceKeyword;
import org.kuali.coeus.common.framework.version.sequence.associate.SequenceAssociate;
import org.kuali.coeus.common.framework.version.sequence.owner.SequenceOwner;

public class InstitutionalProposalScienceKeyword extends AbstractScienceKeyword implements SequenceAssociate {

    private static final long serialVersionUID = 1L;

    private Long proposalScienceKeywordId;

    private Boolean selectKeyword = false;

    private String proposalNumber;

    private Integer sequenceNumber;

    private InstitutionalProposal institutionalProposal;

    public InstitutionalProposalScienceKeyword() {
    }

    /**
     * Constructs a PropScienceKeyword.
     * @param proposalNumber
     * @param scienceKeyword
     */
    public InstitutionalProposalScienceKeyword(InstitutionalProposal institutionalProposal, ScienceKeyword scienceKeyword) {
        setInstitutionalProposal(institutionalProposal);
        setScienceKeywordDescription(scienceKeyword.getDescription());
        setScienceKeywordCode(scienceKeyword.getCode());
        setScienceKeyword(scienceKeyword);
    }

    public Long getProposalScienceKeywordId() {
        return proposalScienceKeywordId;
    }

    public void setProposalScienceKeywordId(Long proposalScienceKeywordId) {
        this.proposalScienceKeywordId = proposalScienceKeywordId;
    }

    /**
     * Gets the proposalNumber attribute. 
     * @return Returns the proposalNumber.
     */
    public String getProposalNumber() {
        return proposalNumber;
    }

    /**
     * Sets the proposalNumber attribute value.
     * @param proposalNumber The proposalNumber to set.
     */
    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    /**
     * Gets the sequenceNumber attribute. 
     * @return Returns the sequenceNumber.
     */
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * Sets the sequenceNumber attribute value.
     * @param sequenceNumber The sequenceNumber to set.
     */
    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    /**
     * Gets the institutionalProposal attribute. 
     * @return Returns the institutionalProposal.
     */
    public InstitutionalProposal getInstitutionalProposal() {
        return institutionalProposal;
    }

    /**
     * Sets the institutionalProposal attribute value.
     * @param institutionalProposal The institutionalProposal to set.
     */
    public void setInstitutionalProposal(InstitutionalProposal institutionalProposal) {
        this.institutionalProposal = institutionalProposal;
        if (institutionalProposal != null) {
            setSequenceNumber(institutionalProposal.getSequenceNumber());
            setProposalNumber(institutionalProposal.getProposalNumber());
        } else {
            setSequenceNumber(0);
            setProposalNumber("");
        }
    }

    /**
     * Gets the selectKeyword attribute. 
     * @return Returns the selectKeyword.
     */
    public Boolean getSelectKeyword() {
        return selectKeyword;
    }

    /**
     * Sets the selectKeyword attribute value.
     * @param selectKeyword The selectKeyword to set.
     */
    public void setSelectKeyword(Boolean selectKeyword) {
        this.selectKeyword = selectKeyword;
    }

    @Override
    public SequenceOwner getSequenceOwner() {
        return getInstitutionalProposal();
    }

    @Override
    public void setSequenceOwner(SequenceOwner newlyVersionedOwner) {
        setInstitutionalProposal((InstitutionalProposal) newlyVersionedOwner);
    }

    @Override
    public void resetPersistenceState() {
        this.proposalScienceKeywordId = null;
    }
}
