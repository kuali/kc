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
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.IdClass;

import java.util.LinkedHashMap;

@IdClass(org.kuali.kra.proposaldevelopment.bo.id.NarrativeAttachmentId.class)
@Entity
@Table(name="NARRATIVE_ATTACHMENT")
public class NarrativeAttachment extends AttachmentDataSource{
	
	@Id
	@Column(name="PROPOSAL_NUMBER")
	private String proposalNumber;
	
	@Id
    @Column(name="MODULE_NUMBER")
    private Integer moduleNumber;
	
    @Column(name="NARRATIVE_DATA")
	private byte[] narrativeData;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST })
    @JoinColumns({@JoinColumn(name="PROPOSAL_NUMBER", insertable = false, updatable = false),
                  @JoinColumn(name="MODULE_NUMBER", insertable=false, updatable=false)})
    private Narrative narrative;

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

	public byte[] getNarrativeData() {
		return narrativeData;
	}

	public void setNarrativeData(byte[] narrativePdf) {
	    this.narrativeData = narrativePdf;
	}
	
	public byte[] getContent() {
	    return narrativeData;
	}

	public Narrative getNarrative() {
        return narrative;
    }

    public void setNarrative(Narrative narrative) {
        this.narrative = narrative;
    }

    @Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("moduleNumber", getModuleNumber());
		hashMap.put("proposalNumber", getProposalNumber());
		hashMap.put("fileName", getFileName());
        hashMap.put("contentType", getContentType());
		return hashMap;
	}

}

