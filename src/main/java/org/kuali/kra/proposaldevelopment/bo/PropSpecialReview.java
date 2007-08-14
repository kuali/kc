package org.kuali.kra.proposaldevelopment.bo;

import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class PropSpecialReview extends KraPersistableBusinessObjectBase {
    //TODO : temporarily change proposalnumber from string to integer to see if ojb willwork
	private Integer proposalNumber;
	private Integer specialReviewNumber;
	private Date applicationDate;
	private Date approvalDate;
	private Integer approvalTypeCode;
	private String comments;
	private String protocolNumber;
	private Integer specialReviewCode;

	public Integer getProposalNumber() {
		return proposalNumber;
	}

	public void setProposalNumber(Integer proposalNumber) {
		this.proposalNumber = proposalNumber;
	}

	public Integer getSpecialReviewNumber() {
		return specialReviewNumber;
	}

	public void setSpecialReviewNumber(Integer specialReviewNumber) {
		this.specialReviewNumber = specialReviewNumber;
	}

	public Date getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public Integer getApprovalTypeCode() {
		return approvalTypeCode;
	}

	public void setApprovalTypeCode(Integer approvalTypeCode) {
		this.approvalTypeCode = approvalTypeCode;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getProtocolNumber() {
		return protocolNumber;
	}

	public void setProtocolNumber(String protocolNumber) {
		this.protocolNumber = protocolNumber;
	}

	public Integer getSpecialReviewCode() {
		return specialReviewCode;
	}

	public void setSpecialReviewCode(Integer specialReviewCode) {
		this.specialReviewCode = specialReviewCode;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("proposalNumber", getProposalNumber());
		hashMap.put("specialReviewNumber", getSpecialReviewNumber());
		hashMap.put("applicationDate", getApplicationDate());
		hashMap.put("approvalDate", getApprovalDate());
		hashMap.put("approvalTypeCode", getApprovalTypeCode());
		hashMap.put("comments", getComments());
		hashMap.put("protocolNumber", getProtocolNumber());
		hashMap.put("specialReviewCode", getSpecialReviewCode());
		return hashMap;
	}
}
