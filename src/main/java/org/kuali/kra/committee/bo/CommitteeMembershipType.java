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

import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * 
 * This class implements the committee membership type object.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@Entity
@Table(name = "COMM_MEMBERSHIP_TYPE")
public class CommitteeMembershipType extends KraPersistableBusinessObjectBase { 
    
    @Id
    @Column(name = "MEMBERSHIP_TYPE_CODE")
    private String membershipTypeCode; 
    
    @Column(name = "DESCRIPTION")
    private String description; 
    
    public CommitteeMembershipType() { 

    } 
    
    public String getMembershipTypeCode() {
        return membershipTypeCode;
    }

    public void setMembershipTypeCode(String membershipTypeCode) {
        this.membershipTypeCode = membershipTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override 
    protected LinkedHashMap<String,Object> toStringMapper() {
        LinkedHashMap<String,Object> hashMap = new LinkedHashMap<String,Object>();
        hashMap.put("membershipTypeCode", getMembershipTypeCode());
        hashMap.put("description", getDescription());
        return hashMap;
    }
    
}