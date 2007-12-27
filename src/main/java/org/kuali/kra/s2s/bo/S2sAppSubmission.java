package org.kuali.kra.s2s.bo;

import java.util.LinkedHashMap;
import java.sql.Date;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class S2sAppSubmission extends KraPersistableBusinessObjectBase {
	private String proposalNumber;
	private Integer submissionNumber;
	private String agencyTrackingId;
	private String comments;
	private String ggTrackingId;
	private Date lastModifiedDate;
	private Date lastNotifiedDate;
	private Date receivedDate;
	private String status;

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

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Date getLastNotifiedDate() {
		return lastNotifiedDate;
	}

	public void setLastNotifiedDate(Date lastNotifiedDate) {
		this.lastNotifiedDate = lastNotifiedDate;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
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
}
