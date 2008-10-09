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
package org.kuali.kra.s2s.bo;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.IdClass;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

@IdClass(org.kuali.kra.s2s.bo.id.S2sAppAttachmentsId.class)
@Entity
@Table(name="S2S_APP_ATTACHMENTS")
public class S2sAppAttachments extends KraPersistableBusinessObjectBase {
    
    @Id
    @Column(name="PROPOSAL_NUMBER")
    private String proposalNumber;
    
	@Id
	@Column(name="CONTENT_ID")
	private String contentId;
	
	@Column(name="CONTENT_TYPE")
	private String contentType;
	
	@Column(name="HASH_CODE")
	private String hashCode;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST })
    @JoinColumn(name="PROPOSAL_NUMBER", insertable = false, updatable = false)
    private S2sApplication s2sApplication;

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getProposalNumber() {
		return proposalNumber;
	}

	public void setProposalNumber(String proposalNumber) {
		this.proposalNumber = proposalNumber;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getHashCode() {
		return hashCode;
	}

	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}

	public S2sApplication getS2sApplication() {
        return s2sApplication;
    }

    public void setS2sApplication(S2sApplication application) {
        s2sApplication = application;
    }

    @Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("contentId", getContentId());
		hashMap.put("proposalNumber", getProposalNumber());
		hashMap.put("contentType", getContentType());
		hashMap.put("hashCode", getHashCode());
		return hashMap;
	}
}

