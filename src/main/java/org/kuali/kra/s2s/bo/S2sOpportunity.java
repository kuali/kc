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

import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.persistence.FetchType;
import javax.persistence.Basic;
import javax.persistence.Lob;
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

@Entity
@Table(name="S2S_OPPORTUNITY")
public class S2sOpportunity extends KraPersistableBusinessObjectBase {
	@Id
	@Column(name="PROPOSAL_NUMBER")
	private String proposalNumber;
	
	@Column(name="CFDA_NUMBER")
	private String cfdaNumber;
	
	@Column(name="CLOSING_DATE")
	private Timestamp closingDate;
	
	@Column(name="COMPETETION_ID")
	private String competetionId;
	
	@Column(name="INSTRUCTION_URL")
	private String instructionUrl;
	
	@Column(name="OPENING_DATE")
	private Timestamp openingDate;
	
	@Lob
	@Basic(fetch=FetchType.LAZY)
	@Column(name="OPPORTUNITY")
	private String opportunity;
	
	@Column(name="OPPORTUNITY_ID")
	private String opportunityId;
	
	@Column(name="OPPORTUNITY_TITLE")
	private String opportunityTitle;
	
	@Column(name="REVISION_CODE")
	private String revisionCode;
	
	@Column(name="REVISION_OTHER_DESCRIPTION")
	private String revisionOtherDescription;
	
	@Column(name="S2S_SUBMISSION_TYPE_CODE")
	private String s2sSubmissionTypeCode;
	
	@Column(name="SCHEMA_URL")
	private String schemaUrl;
	
	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, 
           targetEntity=org.kuali.kra.s2s.bo.S2sOppForms.class, mappedBy="s2sOpportunity")
	private List<S2sOppForms> s2sOppForms;
	
	@OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="S2S_SUBMISSION_TYPE_CODE", insertable=false, updatable=false)
	private S2sSubmissionType s2sSubmissionType;
	
	@OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="REVISION_CODE", insertable=false, updatable=false)
	private S2sRevisionType s2sRevisionType;
	
    public String getProposalNumber() {
		return proposalNumber;
	}

	public void setProposalNumber(String proposalNumber) {
		this.proposalNumber = proposalNumber;
	}

	public String getCfdaNumber() {
		return cfdaNumber;
	}

	public void setCfdaNumber(String cfdaNumber) {
		this.cfdaNumber = cfdaNumber;
	}

	public Timestamp getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Timestamp closingDate) {
		this.closingDate = closingDate;
	}

	public String getCompetetionId() {
		return competetionId;
	}

	public void setCompetetionId(String competetionId) {
		this.competetionId = competetionId;
	}

	public String getInstructionUrl() {
		return instructionUrl;
	}

	public void setInstructionUrl(String instructionUrl) {
		this.instructionUrl = instructionUrl;
	}

	public Timestamp getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(Timestamp openingDate) {
		this.openingDate = openingDate;
	}

	public String getOpportunity() {
		return opportunity;
	}

	public void setOpportunity(String opportunity) {
		this.opportunity = opportunity;
	}

	public String getRevisionCode() {
		return revisionCode;
	}

	public void setRevisionCode(String revisionCode) {
		this.revisionCode = revisionCode;
	}

	public String getRevisionOtherDescription() {
		return revisionOtherDescription;
	}

	public void setRevisionOtherDescription(String revisionOtherDescription) {
		this.revisionOtherDescription = revisionOtherDescription;
	}

	public String getS2sSubmissionTypeCode() {
		return s2sSubmissionTypeCode;
	}

	public void setS2sSubmissionTypeCode(String s2sSubmissionTypeCode) {
		this.s2sSubmissionTypeCode = s2sSubmissionTypeCode;
	}

	public String getSchemaUrl() {
		return schemaUrl;
	}

	public void setSchemaUrl(String schemaUrl) {
		this.schemaUrl = schemaUrl;
	}

    public List<S2sOppForms> getS2sOppForms() {
        return s2sOppForms;
    }

    public void setS2sOppForms(List<S2sOppForms> oppForms) {
        s2sOppForms = oppForms;
    }

    public S2sRevisionType getS2sRevisionType() {
        return s2sRevisionType;
    }

    public void setS2sRevisionType(S2sRevisionType revisionType) {
        s2sRevisionType = revisionType;
    }

    public String getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(String opportunityId) {
        this.opportunityId = opportunityId;
    }

    public String getOpportunityTitle() {
        return opportunityTitle;
    }

    public void setOpportunityTitle(String opportunityTitle) {
        this.opportunityTitle = opportunityTitle;
    }
    
    @Override 
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = new LinkedHashMap();
        hashMap.put("proposalNumber", getProposalNumber());
        hashMap.put("cfdaNumber", getCfdaNumber());
        hashMap.put("closingDate", getClosingDate());
        hashMap.put("competetionId", getCompetetionId());
        hashMap.put("instructionUrl", getInstructionUrl());
        hashMap.put("openingDate", getOpeningDate());
        hashMap.put("opportunity", getOpportunity());
        hashMap.put("opportunityId", getOpportunityId());
        hashMap.put("popportunityTitle", getOpportunityTitle());
        hashMap.put("revisionCode", getRevisionCode());
        hashMap.put("revisionOtherDescription", getRevisionOtherDescription());
        hashMap.put("s2sSubmissionTypeCode", getS2sSubmissionTypeCode());
        hashMap.put("schemaUrl", getSchemaUrl());
        hashMap.put("updateTimestamp", this.getUpdateTimestamp());
        hashMap.put("updateUser", this.getUpdateUser());
        return hashMap;
    }

    public S2sSubmissionType getS2sSubmissionType() {
        return s2sSubmissionType;
    }

    public void setS2sSubmissionType(S2sSubmissionType submissionType) {
        s2sSubmissionType = submissionType;
    }
}

