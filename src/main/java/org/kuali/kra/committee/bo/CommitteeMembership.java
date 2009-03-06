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

import javax.persistence.Column;
import javax.persistence.Id;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Person;
import org.kuali.kra.irb.bo.ProtocolPersonRolodex;

public class CommitteeMembership extends KraPersistableBusinessObjectBase {

    @Id
    @Column(name = "COMM_MEMBERSHIPS_ID")
    private Long committeeMembershipsId;

    @Column(name = "ID")
    private Long id;

    @Column(name = "COMMITTEE_ID")
    private String committeeId;

    @Column(name = "PERSON_ID")
    private String personId;

    @Column(name = "ROLODEX_ID")
    private String rolodexId;

    @Column(name = "PERSON_NAME")
    private String personName;

    @Column(name = "MEMBERSHIP_ID")
    private String membershipId;

    @Column(name = "SEQUENCE_NUMBER")
    private Integer sequenceNumber;

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

    private CommitteeMembershipType membershipType;

    private Person person;
    private ProtocolPersonRolodex rolodex;

    public CommitteeMembership() {
    }

    public Long getCommitteeMembershipsId() {
        return committeeMembershipsId;
    }

    public void setCommitteeMembershipsId(Long committeeMembershipsId) {
        this.committeeMembershipsId = committeeMembershipsId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRolodexId() {
        return rolodexId;
    }

    public void setRolodexId(String rolodexId) {
        this.rolodexId = rolodexId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public ProtocolPersonRolodex getRolodex() {
        return rolodex;
    }

    public void setRolodex(ProtocolPersonRolodex rolodex) {
        this.rolodex = rolodex;
    }

    @Override
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("committeeMembershipsId", getCommitteeMembershipsId());
        hashMap.put("committeeId", getCommitteeId());
        hashMap.put("personId", getPersonId());
        hashMap.put("rolodexId", getRolodexId());
        hashMap.put("personName", getPersonName());
        hashMap.put("membershipId", getMembershipId());
        hashMap.put("sequenceNumber", getSequenceNumber());
        hashMap.put("paidMember", getPaidMember());
        hashMap.put("termStartDate", getTermStartDate());
        hashMap.put("termEndDate", getTermEndDate());
        hashMap.put("membershipTypeCode", getMembershipTypeCode());
        hashMap.put("comments", getComments());
        hashMap.put("contactNotes", getContactNotes());
        hashMap.put("trainingNotes", getTrainingNotes());
        return hashMap;
    }

    /**
     * Indicates if the <code>CommitteeMemership</code> is "equal to" this one. Equal is defined that the personId and rolodexId are
     * the same.
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        boolean isEquals = false;
        if (obj instanceof CommitteeMembership) {
            CommitteeMembership committeeMembership = (CommitteeMembership) obj;

            if ((idEquals(committeeMembership.personId, this.personId))
                    && (idEquals(committeeMembership.rolodexId, this.rolodexId))) {
                isEquals = true;
            }
        }
        return isEquals;
    }

    private boolean idEquals(String id1, String id2) {
        if ((id1 == null && id2 == null) || (id1 != null && (id1.equals(id2)))) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isDelete() {
        // TODO cniesen - Auto-generated method stub
        return false;
    }
}