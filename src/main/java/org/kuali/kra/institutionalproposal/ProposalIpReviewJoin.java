/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.institutionalproposal;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.ipreview.IntellectualPropertyReview;

public class ProposalIpReviewJoin extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = 1L;

    private Long proposalIpReviewJoinId;

    private Long proposalId;

    private Long ipReviewId;

    private InstitutionalProposal institutionalProposal;

    private IntellectualPropertyReview intellectualPropertyReview;

    public Long getProposalIpReviewJoinId() {
        return proposalIpReviewJoinId;
    }

    public void setProposalIpReviewJoinId(Long proposalIpReviewJoinId) {
        this.proposalIpReviewJoinId = proposalIpReviewJoinId;
    }

    public Long getProposalId() {
        return proposalId;
    }

    public void setProposalId(Long proposalId) {
        this.proposalId = proposalId;
    }

    public Long getIpReviewId() {
        return ipReviewId;
    }

    public void setIpReviewId(Long ipReviewId) {
        this.ipReviewId = ipReviewId;
    }

    public InstitutionalProposal getInstitutionalProposal() {
        return institutionalProposal;
    }

    public void setInstitutionalProposal(InstitutionalProposal institutionalProposal) {
        this.institutionalProposal = institutionalProposal;
    }

    public IntellectualPropertyReview getIntellectualPropertyReview() {
        return intellectualPropertyReview;
    }

    public void setIntellectualPropertyReview(IntellectualPropertyReview intellectualPropertyReview) {
        this.intellectualPropertyReview = intellectualPropertyReview;
    }
}
