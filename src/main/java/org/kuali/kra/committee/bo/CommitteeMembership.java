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

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Person;
import org.kuali.kra.irb.personnel.ProtocolPersonRolodex;

/**
 * 
 * This class implements the committee membership object.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class CommitteeMembership extends KraPersistableBusinessObjectBase {

    @Id
    @Column(name = "COMM_MEMBERSHIPS_ID")
    private Long committeeMembershipId;

    @Column(name = "ID")
    private Long id;

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
    private Integer membershipTypeCode;

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
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(termStartDate);
    }

    public void setTermStartDate(Date termStartDate) {
        this.termStartDate = termStartDate;
    }

    public Date getTermEndDate() {
        return termEndDate;
    }

    public String getFormattedTermEndDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(termEndDate);
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

    public void setMembershipRoles(List<CommitteeMembershipRole> membershipRoles) {
        this.membershipRoles = membershipRoles;
        for (CommitteeMembershipRole role: membershipRoles) {
            role.init(this);
        }
    }

    public List<CommitteeMembershipRole> getMembershipRoles() {
        return membershipRoles;
    }
    
    public void setMembershipExpertise(List<CommitteeMembershipExpertise> committeeMembershipExpertise) {
        this.membershipExpertise = committeeMembershipExpertise;
        for (CommitteeMembershipExpertise expertise: committeeMembershipExpertise) {
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
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("committeeMembershipId", getCommitteeMembershipId());
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
        hashMap.put("Role Count", getMembershipRoles().size());
        hashMap.put("Role isEmpty", getMembershipRoles().isEmpty());
        hashMap.put("Role is null", (getMembershipRoles() == null) ? true : false);
        return hashMap;
    }

    /**
     * Indicates if the committee memberships are of the same person (i.e. the personId and rolodexId are
     * the same).
     * 
     * @param committeeMembership - the committee membership to compare against
     * @return <code>true</code> if both committee membership belong to the same person, <code>false</code> otherwise
     */
    public boolean isSamePerson(CommitteeMembership committeeMembership) {
        boolean isEquals = false;
        
        if (this.personId != null && this.personId.equals(committeeMembership.personId)
                || this.rolodexId != null && this.rolodexId.equals(committeeMembership.rolodexId)){
            isEquals = true;
        }
        
        return isEquals;
    }

}