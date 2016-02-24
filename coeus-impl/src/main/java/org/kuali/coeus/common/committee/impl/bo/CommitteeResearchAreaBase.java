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
 * This class implements the committee research area business object.
 */
public abstract class CommitteeResearchAreaBase extends CommitteeAssociateBase {

    private static final long serialVersionUID = 6586026093806484327L;

    private Long id;

    private String researchAreaCode;

    private CommitteeBase committee;

    private ResearchAreaBase researchArea;

    public CommitteeResearchAreaBase() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResearchAreaCode() {
        return researchAreaCode;
    }

    public void setResearchAreaCode(String researchAreaCode) {
        this.researchAreaCode = researchAreaCode;
    }

    public ResearchAreaBase getResearchAreas() {
        return researchArea;
    }

    public void setResearchAreas(ResearchAreaBase researchArea) {
        this.researchArea = researchArea;
    }

    public CommitteeBase getCommittee() {
        return committee;
    }

    public void setCommittee(CommitteeBase committee) {
        this.committee = committee;
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        CommitteeResearchAreaBase committeeResearchArea = (CommitteeResearchAreaBase) obj;
        if (this.getCommitteeIdFk() != null && this.getCommitteeIdFk().equals(committeeResearchArea.getCommitteeIdFk()) && this.getResearchAreaCode() != null && this.getResearchAreaCode().equals(committeeResearchArea.getResearchAreaCode())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + (this.getCommitteeIdFk() == null ? 0 : this.getCommitteeIdFk().hashCode());
        result = PRIME * result + (this.getResearchAreaCode() == null ? 0 : this.getResearchAreaCode().hashCode());
        return result;
    }

    public void resetPersistenceState() {
        setId(null);
    }
}
