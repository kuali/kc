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
package org.kuali.kra.proposaldevelopment.bo;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.IdClass;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

@IdClass(org.kuali.kra.proposaldevelopment.bo.id.NarrativeUserRightsId.class)
@Entity
@Table(name="NARRATIVE_USER_RIGHTS")
public class NarrativeUserRights extends KraPersistableBusinessObjectBase {
	
	@Id
	@Column(name="PROPOSAL_NUMBER")
	private String proposalNumber;
	
	@Id
    @Column(name="MODULE_NUMBER")
    private Integer moduleNumber;
	
	@Id
	@Column(name="USER_ID")
	private String userId;
	
	@Column(name="ACCESS_TYPE")
	private String accessType;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST })
	@JoinColumns({@JoinColumn(name="PROPOSAL_NUMBER", insertable = false, updatable = false),
	              @JoinColumn(name="MODULE_NUMBER", insertable=false, updatable=false)})
	private Narrative narrative;
	
	@Transient
	private String personName;

	public Integer getModuleNumber() {
		return moduleNumber;
	}

	public void setModuleNumber(Integer moduleNumber) {
		this.moduleNumber = moduleNumber;
	}

	public String getProposalNumber() {
		return proposalNumber;
	}

	public void setProposalNumber(String proposalNumber) {
		this.proposalNumber = proposalNumber;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("moduleNumber", getModuleNumber());
		hashMap.put("proposalNumber", getProposalNumber());
		hashMap.put("personName", getPersonName());
        hashMap.put("userId", getUserId());
		hashMap.put("accessType", getAccessType());
		return hashMap;
	}

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Narrative getNarrative() {
        return narrative;
    }

    public void setNarrative(Narrative narrative) {
        this.narrative = narrative;
    }
}

