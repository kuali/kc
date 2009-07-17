/*
 * Copyright 2006-2008 The Kuali Foundation
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

import java.util.LinkedHashMap;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.kuali.kra.bo.ResearchArea;


/**
 * This class implements the committee research area business object.
 */
@javax.persistence.Entity
@Table(name = "COMM_RESEARCH_AREAS")
public class CommitteeResearchArea extends CommitteeAssociate {

    private static final long serialVersionUID = 6586026093806484327L;

    @javax.persistence.Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "RESEARCH_AREA_CODE")
    private String researchAreaCode;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "COMMITTEE_ID", insertable = false, updatable = false)
    private Committee committee;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "RESEARCH_AREA_CODE", insertable = false, updatable = false)
    private ResearchArea researchArea;
    
    public CommitteeResearchArea() {
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

    public ResearchArea getResearchAreas() {
        return researchArea;
    }

    public void setResearchAreas(ResearchArea researchArea) {
        this.researchArea = researchArea;
    }

    public Committee getCommittee() {
        return committee;
    }

    public void setCommittee(Committee committee) {
        this.committee = committee;
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        CommitteeResearchArea committeeResearchArea = (CommitteeResearchArea) obj;
        if (this.getCommitteeIdFk() != null && this.getCommitteeIdFk().equals(committeeResearchArea.getCommitteeIdFk())
                && this.getResearchAreaCode() != null && this.getResearchAreaCode().equals(committeeResearchArea.getResearchAreaCode())) {
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
    
    @Override
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = super.toStringMapper();
        hashMap.put("id", getId());
        hashMap.put("researchAreaCode", getResearchAreaCode());
        return hashMap;
    }

    public void resetPersistenceState() {
        setId(null);
    }

}