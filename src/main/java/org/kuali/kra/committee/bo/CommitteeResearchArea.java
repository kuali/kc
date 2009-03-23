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

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.ResearchArea;


@SuppressWarnings("serial")
@javax.persistence.Entity
@Table(name = "PROTOCOL_RESEARCH_AREAS")
public class CommitteeResearchArea extends KraPersistableBusinessObjectBase {

    @javax.persistence.Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "COMMITTEE_ID")
    private Long committeeId;

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

    public Long getCommitteeId() {
        return committeeId;
    }

    public void setCommitteeId(Long committeeId) {
        this.committeeId = committeeId;
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

    @SuppressWarnings("unchecked")
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = new LinkedHashMap();
        hashMap.put("id", getId());
        hashMap.put("committeeId", getCommitteeId());
        hashMap.put("researchAreaCode", getResearchAreaCode());
        return hashMap;
    }
}