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

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.IdClass;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.sql.Timestamp;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

@IdClass(org.kuali.kra.s2s.bo.id.S2sAppSubmissionId.class)
@Entity
@Table(name="S2S_APP_SUBMISSION")
public class S2sAppSubmission extends KraPersistableBusinessObjectBase {
	@Id
	@Column(name="PROPOSAL_NUMBER")
	private String proposalNumber;
	
	@Id
	@Column(name="SUBMISSION_NUMBER")
	private Integer submissionNumber;
	
	@Column(name="AGENCY_TRACKING_ID")
	private String agencyTrackingId;
	
	@Column(name="COMMENTS")
	private String comments;
	
	@Column(name="GG_TRACKING_ID")
	private String ggTrackingId;
	
	@Column(name="LAST_MODIFIED_DATE")
	private Timestamp lastModifiedDate;
	
	@Column(name="LAST_NOTIFIED_DATE")
	private Timestamp lastNotifiedDate;
	
	@Column(name="RECEIVED_DATE")
	private Timestamp receivedDate;
	
	@Column(name="STATUS")
	private String status;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="PROPOSAL_NUMBER", insertable=false, updatable=false)
	private S2sApplication s2sApplication;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST })
	@JoinColumn(name="PROPOSAL_NUMBER", insertable = false, updatable = false)
    private ProposalDevelopmentDocument proposalDevelopmentDocument;

	public String getProposalNumber() {
		return proposalNumber;
	}

	public void setProposalNumber(String proposalNumber) {
		this.proposalNumber = proposalNumber;
	}

	public Integer getSubmissionNumber() {
		return submissionNumber;
	}

	public void setSubmissionNumber(Integer submissionNumber) {
		this.submissionNumber = submissionNumber;
	}

	public String getAgencyTrackingId() {
		return agencyTrackingId;
	}

	public void setAgencyTrackingId(String agencyTrackingId) {
		this.agencyTrackingId = agencyTrackingId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getGgTrackingId() {
		return ggTrackingId;
	}

	public void setGgTrackingId(String ggTrackingId) {
		this.ggTrackingId = ggTrackingId;
	}

	public Timestamp getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Timestamp lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Timestamp getLastNotifiedDate() {
		return lastNotifiedDate;
	}

	public void setLastNotifiedDate(Timestamp lastNotifiedDate) {
		this.lastNotifiedDate = lastNotifiedDate;
	}

	public Timestamp getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Timestamp receivedDate) {
		this.receivedDate = receivedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("proposalNumber", getProposalNumber());
		hashMap.put("submissionNumber", getSubmissionNumber());
		hashMap.put("agencyTrackingId", getAgencyTrackingId());
		hashMap.put("comments", getComments());
		hashMap.put("ggTrackingId", getGgTrackingId());
		hashMap.put("lastModifiedDate", getLastModifiedDate());
		hashMap.put("lastNotifiedDate", getLastNotifiedDate());
		hashMap.put("receivedDate", getReceivedDate());
		hashMap.put("status", getStatus());
		return hashMap;
	}

    /**
     * Gets the s2sApplications attribute. 
     * @return Returns the s2sApplications.
     */
    public List<S2sApplication> getS2sApplication() {
        List<S2sApplication> list = new ArrayList<S2sApplication>();
        list.add(s2sApplication);
        return list;
    }

    /**
     * Sets the s2sApplications attribute value.
     * @param applications The s2sApplications to set.
     */
    public void setS2sApplication(List<S2sApplication> s2sApplication) {
        this.s2sApplication = s2sApplication.get(0);
    }

    public ProposalDevelopmentDocument getProposalDevelopmentDocument() {
        return proposalDevelopmentDocument;
    }

    public void setProposalDevelopmentDocument(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this.proposalDevelopmentDocument = proposalDevelopmentDocument;
    }
}

