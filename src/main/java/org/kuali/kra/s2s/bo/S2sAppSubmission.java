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

import java.util.LinkedHashMap;
import java.sql.Timestamp;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class S2sAppSubmission extends KraPersistableBusinessObjectBase {
	private String proposalNumber;
	private Integer submissionNumber;
	private String agencyTrackingId;
	private String comments;
	private String ggTrackingId;
	private Timestamp lastModifiedDate;
	private Timestamp lastNotifiedDate;
	private Timestamp receivedDate;
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
}
