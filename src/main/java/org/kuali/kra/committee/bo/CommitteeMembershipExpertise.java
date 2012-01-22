/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.committee.bo;

import org.kuali.kra.bo.ResearchArea;

/**
 * 
 * This class implements the committee membership expertise business object.
 * 
 */
public class CommitteeMembershipExpertise extends CommitteeSequenceAssociate {

    private static final long serialVersionUID = 4926007164018659270L;

    private Long committeeMembershipExpertiseId;

    private Long committeeMembershipIdFk;

    private String researchAreaCode;

    private ResearchArea researchArea;

    public CommitteeMembershipExpertise() {
        setResearchArea(new ResearchArea());
    }

    public Long getCommitteeMembershipExpertiseId() {
        return committeeMembershipExpertiseId;
    }

    public void setCommitteeMembershipExpertiseId(Long committeeMembershipExpertiseId) {
        this.committeeMembershipExpertiseId = committeeMembershipExpertiseId;
    }

    public Long getCommitteeMembershipIdFk() {
        return committeeMembershipIdFk;
    }

    public void setCommitteeMembershipIdFk(Long committeeMembershipIdFk) {
        this.committeeMembershipIdFk = committeeMembershipIdFk;
    }

    public String getResearchAreaCode() {
        return researchAreaCode;
    }

    public void setResearchAreaCode(String researchAreaCode) {
        this.researchAreaCode = researchAreaCode;
    }

    public ResearchArea getResearchArea() {
        return researchArea;
    }

    public void setResearchArea(ResearchArea researchArea) {
        this.researchArea = researchArea;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        CommitteeMembershipExpertise committeeMembershipExpertise = (CommitteeMembershipExpertise) obj;
        if (this.committeeMembershipIdFk != null && this.committeeMembershipIdFk.equals(committeeMembershipExpertise.committeeMembershipIdFk) && this.researchAreaCode != null && this.researchAreaCode.equals(committeeMembershipExpertise.researchAreaCode)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + (this.committeeMembershipIdFk == null ? 0 : this.committeeMembershipIdFk.hashCode());
        result = PRIME * result + (this.researchAreaCode == null ? 0 : this.researchAreaCode.hashCode());
        return result;
    }

    public void resetPersistenceState() {
        setCommitteeMembershipExpertiseId(null);
    }
}
