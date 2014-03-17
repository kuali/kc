/*
 * Copyright 2005-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.institutionalproposal.home;

import org.kuali.coeus.common.framework.sequence.associate.SequenceAssociate;
import org.kuali.coeus.common.framework.sequence.owner.SequenceOwner;
import org.kuali.kra.bo.AbstractScienceKeyword;
import org.kuali.kra.bo.ScienceKeyword;

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
        setScienceKeywordCode(scienceKeyword.getScienceKeywordCode());
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
