package org.kuali.kra.s2s.bo;

import java.util.LinkedHashMap;
import java.sql.Date;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class S2sOpportunity extends KraPersistableBusinessObjectBase {
	private String proposalNumber;
	private String cfdaNumber;
	private Date closingDate;
	private String competetionId;
	private String instructionUrl;
	private Date openingDate;
	private String opportunity;
	private String opportunityId;
	private String opportunityTitle;
	private String revisionCode;
	private String revisionOtherDescription;
	private Integer s2sSubmissionTypeCode;
	private String schemaUrl;

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

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
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

	public Date getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}

	public String getOpportunity() {
		return opportunity;
	}

	public void setOpportunity(String opportunity) {
		this.opportunity = opportunity;
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
		hashMap.put("opportunityTitle", getOpportunityTitle());
		hashMap.put("revisionCode", getRevisionCode());
		hashMap.put("revisionOtherDescription", getRevisionOtherDescription());
		hashMap.put("s2sSubmissionTypeCode", getS2sSubmissionTypeCode());
		hashMap.put("schemaUrl", getSchemaUrl());
		return hashMap;
	}
}
