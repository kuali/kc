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
package org.kuali.kra.committee.bo;

import java.sql.Date;
import java.util.LinkedHashMap;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class CommitteeMembership extends KraPersistableBusinessObjectBase { 
	
    @Id
    @Column(name = "COMM_MEMBERSHIPS_ID")
    private Integer committeeMembershipsId;
    
    @Column(name = "COMMITTEE_ID")
	private String committeeId; 
    
    @Column(name = "PERSON_ID")
	private String personId; 
    
    @Column(name = "MEMBERSHIP_ID")
	private String membershipId; 
    
    @Column(name = "SEQUENCE_NUMBER")
	private Integer sequenceNumber; 
    
    @Column(name = "NON_EMPLOYEE_FLAG")
	private boolean nonEmployee; 
    
    @Column(name = "PAID_MEMBER_FLAG")
	private boolean paidMember; 
    
    @Column(name = "TERM_START_DATE")
	private Date termStartDate; 
    
    @Column(name = "TERM_END_DATE")
	private Date termEndDate; 
    
    @Column(name = "MEMBERSHIP_TYPE_CODE")
	private Integer membershipTypeCode; 
    
    @Column(name = "COMMENTS")
	private String comments;
    
    @Column(name = "CONTACT_NOTES")
	private String contactNotes;
    
    @Column(name = "TRAINING_NOTES")
	private String trainingNotes;
	
    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "COMM_MEMBERSHIP_TYPE", insertable = true, updatable = true)
	private CommitteeMembershipType membershipType; 
	
	public CommitteeMembership() { 
	} 
	
	public Integer getCommitteeMembershipsId() {
		return committeeMembershipsId;
	}

	public void setCommitteeMembershipsId(Integer committeeMembershipsId) {
		this.committeeMembershipsId = committeeMembershipsId;
	}

	public String getCommitteeId() {
		return committeeId;
	}

	public void setCommitteeId(String committeeId) {
		this.committeeId = committeeId;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getMembershipId() {
		return membershipId;
	}

	public void setMembershipId(String membershipId) {
		this.membershipId = membershipId;
	}

	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public boolean getNonEmployee() {
		return nonEmployee;
	}

	public void setNonEmployee(boolean nonEmployeeFlag) {
		this.nonEmployee = nonEmployeeFlag;
	}

	public boolean getPaidMember() {
		return paidMember;
	}

	public void setPaidMember(boolean paidMemberFlag) {
		this.paidMember = paidMemberFlag;
	}

	public Date getTermStartDate() {
		return termStartDate;
	}

	public void setTermStartDate(Date termStartDate) {
		this.termStartDate = termStartDate;
	}

	public Date getTermEndDate() {
		return termEndDate;
	}

	public void setTermEndDate(Date termEndDate) {
		this.termEndDate = termEndDate;
	}

	public Integer getMembershipTypeCode() {
		return membershipTypeCode;
	}

	public void setMembershipTypeCode(Integer membershipTypeCode) {
		this.membershipTypeCode = membershipTypeCode;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

    public String getContactNotes() {
        return contactNotes;
    }

    public void setContactNotes(String contactNotes) {
        this.contactNotes = contactNotes;
    }
    public String getTrainingNotes() {
        return trainingNotes;
    }

    public void setTrainingNotes(String trainingNotes) {
        this.trainingNotes = trainingNotes;
    }
	public CommitteeMembershipType getMembershipType() {
		return membershipType;
	}

	public void setMembershipType(CommitteeMembershipType membershipType) {
		this.membershipType = membershipType;
	}

	@Override 
	protected LinkedHashMap<String,Object> toStringMapper() {
		LinkedHashMap<String,Object> hashMap = new LinkedHashMap<String,Object>();
		hashMap.put("committeeMembershipsId", getCommitteeMembershipsId());
		hashMap.put("committeeId", getCommitteeId());
		hashMap.put("personId", getPersonId());
		hashMap.put("membershipId", getMembershipId());
		hashMap.put("sequenceNumber", getSequenceNumber());
		hashMap.put("personName", getPersonName());
		hashMap.put("nonEmployee", getNonEmployee());
		hashMap.put("paidMember", getPaidMember());
		hashMap.put("termStartDate", getTermStartDate());
		hashMap.put("termEndDate", getTermEndDate());
		hashMap.put("membershipTypeCode", getMembershipTypeCode());
		hashMap.put("comments", getComments());
        hashMap.put("contactNotes", getContactNotes());
        hashMap.put("trainingNotes", getTrainingNotes());
		return hashMap;
	}

	public String getPersonName() {
	    // TODO: cniesen - implement name getter.  Use People/Rolodex lookup based on nonEmployee
	    String personName = "To be implemented";
        return personName;
    }
}