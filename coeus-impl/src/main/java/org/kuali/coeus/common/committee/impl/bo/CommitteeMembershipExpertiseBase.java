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
package org.kuali.coeus.common.committee.impl.bo;

import org.kuali.kra.bo.ResearchAreaBase;

/**
 * 
 * This class implements the committee membership expertise business object.
 * 
 */
public abstract class CommitteeMembershipExpertiseBase extends CommitteeSequenceAssociateBase {

    private static final long serialVersionUID = 4926007164018659270L;

    private Long committeeMembershipExpertiseId;

    private Long committeeMembershipIdFk;

    private String researchAreaCode;

    private ResearchAreaBase researchArea;

    public CommitteeMembershipExpertiseBase() {
        setResearchArea(getNewResearchAreaInstanceHook());
    }

    protected abstract ResearchAreaBase getNewResearchAreaInstanceHook();
    
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

    public ResearchAreaBase getResearchArea() {
        return researchArea;
    }

    public void setResearchArea(ResearchAreaBase researchArea) {
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
        CommitteeMembershipExpertiseBase committeeMembershipExpertise = (CommitteeMembershipExpertiseBase) obj;
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
