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

import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.S2sRevisionType;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.jpa.annotations.Sequence;

@Entity
@Table(name="SUBMISSION_HISTORY")
@Sequence(name="SEQUENCE_SUBMISSION_HISTORY", property="id")
public class S2sSubmissionHistory extends KraPersistableBusinessObjectBase {
    @Id
	@Column(name="ID")
	private Long id;
    
    @Column(name="PROPOSAL_NUMBER")
	private String proposalNumber;
    
    @Column(name="PROPOSAL_NUMBER_ORIG")
	private String proposalNumberOrig;
    
    @Column(name="ORIGINAL_PROPOSAL_ID")
	private String originalProposalId;
    
    @Column(name="SUBMITTED_BY")
	private String submittedBy;
    
    @Column(name="S2S_SUBMISSION_TYPE_CODE")
	private String s2sSubmissionTypeCode;
    
    @Column(name="S2S_REVISION_TYPE_CODE")
	private String s2sRevisionTypeCode;
    
    @Column(name="SUBMISSION_TIMESTAMP")
	private Timestamp submissionTime;
    
    @Column(name="FEDERAL_IDENTIFIER")
	private String federalIdentifier;	
    
	@OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="S2S_SUBMISSION_TYPE_CODE", insertable=false, updatable=false)
	private S2sSubmissionType s2sSubmissionType;
	
	@OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="S2S_REVISION_TYPE_CODE", insertable=false, updatable=false)
	private S2sRevisionType s2sRevisionType;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST })
	@JoinColumn(name="PROPOSAL_NUMBER", insertable = false, updatable = false)
	private ProposalDevelopmentDocument proposalDevelopmentDocument;
	
    public String getProposalNumber() {
		return proposalNumber;
	}

	public void setProposalNumber(String proposalNumber) {
		this.proposalNumber = proposalNumber;
	}


	public String getS2sSubmissionTypeCode() {
		return s2sSubmissionTypeCode;
	}

	public void setS2sSubmissionTypeCode(String s2sSubmissionTypeCode) {
		this.s2sSubmissionTypeCode = s2sSubmissionTypeCode;
	}

    public S2sRevisionType getS2sRevisionType() {
        return s2sRevisionType;
    }

    public void setS2sRevisionType(S2sRevisionType revisionType) {
        s2sRevisionType = revisionType;
    }

    public S2sSubmissionType getS2sSubmissionType() {
        return s2sSubmissionType;
    }

    public void setS2sSubmissionType(S2sSubmissionType submissionType) {
        s2sSubmissionType = submissionType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalProposalId() {
        return originalProposalId;
    }

    public void setOriginalProposalId(String originalProposalId) {
        this.originalProposalId = originalProposalId;
    }

    public String getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(String submittedBy) {
        this.submittedBy = submittedBy;
    }

    public String getS2sRevisionTypeCode() {
        return s2sRevisionTypeCode;
    }

    public void setS2sRevisionTypeCode(String revisionTypeCode) {
        s2sRevisionTypeCode = revisionTypeCode;
    }

    public Timestamp getSubmissionTime() {
        return submissionTime;
    }

    public void setSubmissionTime(Timestamp submissionTime) {
        this.submissionTime = submissionTime;
    }

    public String getFederalIdentifier() {
        return federalIdentifier;
    }

    public void setFederalIdentifier(String federalIdentifier) {
        this.federalIdentifier = federalIdentifier;
    }

    @Override 
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = new LinkedHashMap();
        hashMap.put("id", getId());
        hashMap.put("proposalNumber", getProposalNumber());        
        hashMap.put("originalProposalId", getOriginalProposalId());
        hashMap.put("s2sSubmissionTypeCode", getS2sSubmissionTypeCode());
        hashMap.put("submittedBy", getSubmittedBy());
        hashMap.put("s2sRevisionTypeCode", getS2sRevisionTypeCode());
        hashMap.put("submissionTime", getSubmissionTime());
        hashMap.put("federalIdentifier", getFederalIdentifier());        
        hashMap.put("updateTimestamp", this.getUpdateTimestamp());
        hashMap.put("updateUser", this.getUpdateUser());
        return hashMap;
    }

    public String getProposalNumberOrig() {
        return proposalNumberOrig;
    }

    public void setProposalNumberOrig(String proposalNumberOrig) {
        this.proposalNumberOrig = proposalNumberOrig;
    }

    public ProposalDevelopmentDocument getProposalDevelopmentDocument() {
        return proposalDevelopmentDocument;
    }

    public void setProposalDevelopmentDocument(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this.proposalDevelopmentDocument = proposalDevelopmentDocument;
    }    
}

