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

import org.apache.commons.lang.ObjectUtils;

/**
 * 
 * This class implements the committee membership role business object.
 * 
 */
public class CommitteeMembershipRole extends CommitteeSequenceAssociate {

    private static final long serialVersionUID = 6048477313137155626L;

    @Id
    @Column(name = "COMM_MEMBER_ROLES_ID")
    private Long committeeMembershipRoleId;

    @Column(name = "COMM_MEMBERSHIP_ID_FK")
    private Long committeeMembershipIdFk;

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
        CommitteeMembershipRole committeeMembershipRole = (CommitteeMembershipRole) obj;
        if (ObjectUtils.equals(this.committeeMembershipIdFk, committeeMembershipRole.committeeMembershipIdFk) 
                && ObjectUtils.equals(this.committeeMembershipRoleId, committeeMembershipRole.committeeMembershipRoleId)
                && ObjectUtils.equals(this.startDate, committeeMembershipRole.startDate)) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + (this.committeeMembershipIdFk == null ? 0 : this.committeeMembershipIdFk.hashCode());
        result = PRIME * result + (this.committeeMembershipRoleId == null ? 0 : this.committeeMembershipRoleId.hashCode());
        result = PRIME * result + (this.startDate == null ? 0 : this.startDate.hashCode());
        return result;
    }

    @Override
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("committeeMembershipRoleId", getCommitteeMembershipRoleId());
        hashMap.put("committeeMembershipIdFk", getCommitteeMembershipIdFk());
        hashMap.put("membershipRoleCode", getMembershipRoleCode());
        hashMap.put("startDate", getStartDate());
        hashMap.put("endDate", getEndDate());
        return hashMap;
    }
    
    public void resetPersistenceState() {
        setCommitteeMembershipRoleId(null);
    }

}
