/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.committee.bo;

import java.sql.Date;
import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Id;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * 
 * This class implements the committee membership roles object.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class CommitteeMembershipRole extends KraPersistableBusinessObjectBase{

    @Id
    @Column(name = "COMM_MEMBER_ROLES_ID")
    private Long committeeMembershipRoleId;

    @Column(name = "COMM_MEMBERSHIP_ID_FK")
    private Long committeeMembershipIdFk;

    @Column(name = "MEMBERSHIP_ID")
    private String membershipId;

    @Column(name = "SEQUENCE_NUMBER")
    private Integer sequenceNumber;

    @Column(name = "MEMBERSHIP_ROLE_CODE")
    private String membershipRoleCode;

    @Column(name = "START_DATE")
    private Date startDate; 

    @Column(name = "END_DATE")
    private Date endDate; 
    
    private MembershipRole membershipRole;

    public CommitteeMembershipRole() {
    }

    public Long getCommitteeMembershipRoleId() {
        return committeeMembershipRoleId;
    }

    public void setCommitteeMembershipRoleId(Long committeeMembershipRoleId) {
        this.committeeMembershipRoleId = committeeMembershipRoleId;
    }

    public Long getCommitteeMembershipIdFk() {
        return committeeMembershipIdFk;
    }

    public void setCommitteeMembershipIdFk(Long committeeMembershipIdFk) {
        this.committeeMembershipIdFk = committeeMembershipIdFk;
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

    public String getMembershipRoleCode() {
        return membershipRoleCode;
    }

    public void setMembershipRoleCode(String membershipRoleCode) {
        this.membershipRoleCode = membershipRoleCode;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public MembershipRole getMembershipRole() {
        return membershipRole;
    }

    public void setMembershipRole(MembershipRole membershipRole) {
        this.membershipRole = membershipRole;
    }
    
    @Override
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("committeeMembershipRoleId", getCommitteeMembershipRoleId());
        hashMap.put("committeeMembershipIdFk", getCommitteeMembershipIdFk());
        hashMap.put("membershipId", getMembershipId());
        hashMap.put("sequenceNumber", getSequenceNumber());
        hashMap.put("membershipRoleCode", getMembershipRoleCode());
        hashMap.put("startDate", getStartDate());
        hashMap.put("endDate", getEndDate());
        return hashMap;
    }
    
    public void init(CommitteeMembership committeeMembership) {
        setMembershipId(committeeMembership.getMembershipId());
    }

}
