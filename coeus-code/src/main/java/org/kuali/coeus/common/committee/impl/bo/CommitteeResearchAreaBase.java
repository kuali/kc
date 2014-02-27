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
