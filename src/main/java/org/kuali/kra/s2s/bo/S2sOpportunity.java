/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.s2s.bo;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.S2sRevisionType;

public class S2sOpportunity extends KraPersistableBusinessObjectBase {
	private String proposalNumber;
	private String cfdaNumber;
	private Timestamp closingDate;
	private String competetionId;
	private String instructionUrl;
	private Timestamp openingDate;
	private String opportunity;
	//private String programAnnouncementNumber;
	//private String programAnnouncementTitle;
	private String opportunityId;
	private String opportunityTitle;
	private String revisionCode;
	private String revisionOtherDescription;
	private Integer s2sSubmissionTypeCode;
	private String schemaUrl;
	private List<S2sOppForms> s2sOppForms;
	private S2sSubmissionType s2sSubmissionType;
	private S2sRevisionType s2sRevisionType; 
	private S2sAppSubmission s2sAppSubmission;

    public S2sSubmissionType getS2sSubmissionType() {
        return s2sSubmissionType;
    }

    public void setS2sSubmissionType(S2sSubmissionType submissionType) {
        s2sSubmissionType = submissionType;
    }

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

/*	public String getProgramAnnouncementNumber() {
		return programAnnouncementNumber;
	}

	public void setProgramAnnouncementNumber(String programAnnouncementNumber) {
		this.programAnnouncementNumber = programAnnouncementNumber;
	}

	public String getProgramAnnouncementTitle() {
		return programAnnouncementTitle;
	}

	public void setProgramAnnouncementTitle(String programAnnouncementTitle) {
		this.programAnnouncementTitle = programAnnouncementTitle;
	}
*/
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

	public Integer getS2sSubmissionTypeCode() {
		return s2sSubmissionTypeCode;
	}

	public void setS2sSubmissionTypeCode(Integer s2sSubmissionTypeCode) {
		this.s2sSubmissionTypeCode = s2sSubmissionTypeCode;
	}

	public String getSchemaUrl() {
		return schemaUrl;
	}

	public void setSchemaUrl(String schemaUrl) {
		this.schemaUrl = schemaUrl;
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
    
    public S2sAppSubmission getS2sAppSubmission() {
        return s2sAppSubmission;
    }

    public void setS2sAppSubmission(S2sAppSubmission appSubmission) {
        s2sAppSubmission = appSubmission;
    }
}
