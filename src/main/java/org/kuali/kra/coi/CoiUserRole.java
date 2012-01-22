/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.coi;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class CoiUserRole extends KraPersistableBusinessObjectBase {

    private Long coiUserRolesId; 
    private Long coiDisclosureId; 
    private String coiDisclosureNumber;
    private Integer sequenceNumber; 
    private String roleName;
    private String userId;
    private String reviewerCode;
    
    //transient fields used for display purposes
    private transient KcPerson person;
    private transient CoiReviewer coiReviewer;
    
    public Long getCoiUserRolesId() {
        return coiUserRolesId;
    }

    public void setCoiUserRolesId(Long coiUserRolesId) {
        this.coiUserRolesId = coiUserRolesId;
    }

    public Long getCoiDisclosureId() {
        return coiDisclosureId;
    }

    public void setCoiDisclosureId(Long coiDisclosureId) {
        this.coiDisclosureId = coiDisclosureId;
    }

    public String getCoiDisclosureNumber() {
        return coiDisclosureNumber;
    }

    public void setCoiDisclosureNumber(String coiDisclosureNumber) {
        this.coiDisclosureNumber = coiDisclosureNumber;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReviewerCode() {
        return reviewerCode;
    }

    public void setReviewerCode(String reviewerCode) {
        this.reviewerCode = reviewerCode;
    }

    public KcPerson getPerson() {
        return person;
    }

    public void setPerson(KcPerson person) {
        this.person = person;
    }

    public CoiReviewer getCoiReviewer() {
        return coiReviewer;
    }

    public void setCoiReviewer(CoiReviewer coiReviewer) {
        this.coiReviewer = coiReviewer;
    }

}
