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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;

import org.kuali.kra.SequenceAssociate;
import org.kuali.kra.SequenceOwner;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Person;
import org.kuali.kra.irb.personnel.ProtocolPersonRolodex;

/**
 * 
 * This class implements the committee membership object.
 * 
 * @author Kuali Research Administration Team (kc.dev@kuali.org)
 */
@SuppressWarnings("serial")
public class CommitteeMembership extends KraPersistableBusinessObjectBase implements SequenceAssociate {

    private final String DATE_FORMAT = "MM/dd/yyyy";

    @Id
    @Column(name = "COMM_MEMBERSHIP_ID")
    private Long committeeMembershipId;

    @Column(name = "COMMITTEE_ID_FK")
    private Long committeeIdFk;

    @Column(name = "COMMITTEE_ID")
    private String committeeId;

    @Column(name = "PERSON_ID")
    private String personId;

    @Column(name = "ROLODEX_ID")
    private Integer rolodexId;

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
    private String membershipTypeCode;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "CONTACT_NOTES")
    private String contactNotes;

    @Column(name = "TRAINING_NOTES")
    private String trainingNotes;

    private List<CommitteeMembershipRole> membershipRoles;
    private List<CommitteeMembershipExpertise> membershipExpertise;

    private CommitteeMembershipType membershipType;

    private Person person;
    private ProtocolPersonRolodex rolodex;

    private SequenceOwner sequenceOwner;

    private boolean delete;

    public CommitteeMembership() {
        setMembershipRoles(new ArrayList<CommitteeMembershipRole>());
        setMembershipExpertise(new ArrayList<CommitteeMembershipExpertise>());
    }

    public Long getCommitteeMembershipId() {
        return committeeMembershipId;
    }

    public void setCommitteeMembershipId(Long committeeMembershipId) {
        this.committeeMembershipId = committeeMembershipId;
    }

    public Long getCommitteeIdFk() {
        return committeeIdFk;
    }

    public void setCommitteeIdFk(Long committeeIdFk) {
        this.committeeIdFk = committeeIdFk;
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

    public Integer getRolodexId() {
        return rolodexId;
    }

    public void setRolodexId(Integer rolodexId) {
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

    public String getFormattedTermStartDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        return dateFormat.format(termStartDate);
    }

    public void setTermStartDate(Date termStartDate) {
        this.termStartDate = termStartDate;
    }

    public Date getTermEndDate() {
        return termEndDate;
    }

    public String getFormattedTermEndDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        return dateFormat.format(termEndDate);
    }

    public void setTermEndDate(Date termEndDate) {
        this.termEndDate = termEndDate;
    }

    public String getMembershipTypeCode() {
        return membershipTypeCode;
    }

    public void setMembershipTypeCode(String membershipTypeCode) {
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

    public void setMembershipRoles(List<CommitteeMembershipRole> membershipRoles) {
        this.membershipRoles = membershipRoles;
        for (CommitteeMembershipRole role : membershipRoles) {
            role.init(this);
        }
    }

    public List<CommitteeMembershipRole> getMembershipRoles() {
        return membershipRoles;
    }

    public void setMembershipExpertise(List<CommitteeMembershipExpertise> committeeMembershipExpertise) {
        this.membershipExpertise = committeeMembershipExpertise;
        for (CommitteeMembershipExpertise expertise : committeeMembershipExpertise) {
            expertise.init(this);
        }
    }

    public List<CommitteeMembershipExpertise> getMembershipExpertise() {
        return membershipExpertise;
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

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        CommitteeMembership committeeMembership = (CommitteeMembership) obj;
        if (this.committeeIdFk != null && this.committeeIdFk.equals(committeeMembership.committeeIdFk)
                && (this.personId != null && this.personId.equals(committeeMembership.personId) 
                        || this.rolodexId != null && this.rolodexId.equals(committeeMembership.rolodexId))
                && (this.termStartDate != null && this.termStartDate.equals(committeeMembership.termStartDate))) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + (this.committeeIdFk == null ? 0 : this.committeeIdFk.hashCode());
        result = PRIME * result + (this.personId == null ? 0 : this.personId.hashCode());
        result = PRIME * result + (this.rolodexId == null ? 0 : this.rolodexId.hashCode());
        result = PRIME * result + (this.termStartDate == null ? 0 : this.termStartDate.hashCode());
        return result;
    }

    @Override
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("committeeMembershipId", getCommitteeMembershipId());
        hashMap.put("committeeIdFk", getCommitteeIdFk());
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
     * Returns if the committee membership is active or inactive. (Current date within term dates.)
     * 
     * @return <code>active</code> if the current date is within the committee membership's term, <code>inactive</code> otherwise
     * @throws NullPointerException - if either the termStartDate or termEndDate are null.
     */
    public String getStatus() {
        java.util.Date currentDate = new java.util.Date();
        if (currentDate.before(getTermStartDate()) || currentDate.after(getTermEndDate())) {
            return "inactive";
        }
        else {
            return "active";
        }
    }

    /**
     * Indicates if the committee memberships are of the same person (i.e. the personId and rolodexId are the same).
     * 
     * @param committeeMembership - the committee membership to compare against
     * @return <code>true</code> if both committee membership belong to the same person, <code>false</code> otherwise
     */
    public boolean isSamePerson(CommitteeMembership committeeMembership) {
        boolean isEquals = false;

        if (this.personId != null && this.personId.equals(committeeMembership.personId) || this.rolodexId != null
                && this.rolodexId.equals(committeeMembership.rolodexId)) {
            isEquals = true;
        }

        return isEquals;
    }

    public SequenceOwner getSequenceOwner() {
        return this.sequenceOwner;
    }

    public void setSequenceOwner(SequenceOwner newOwner) {
        this.sequenceOwner = newOwner;
    }

    public void resetPersistenceState() {
        setCommitteeMembershipId(null);
    }

}