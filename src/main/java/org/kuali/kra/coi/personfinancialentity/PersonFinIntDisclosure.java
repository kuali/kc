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
package org.kuali.kra.coi.personfinancialentity;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.OrganizationTypeList;
import org.kuali.kra.bo.Sponsor;

public class PersonFinIntDisclosure extends KraPersistableBusinessObjectBase { 
    
    private static final long serialVersionUID = 1L;

    private Long personFinIntDisclosureId; 
    private String personId; 
    private String entityNumber; 
    private Integer sequenceNumber; 
    private Integer statusCode; 
    private String statusDescription; 
    private String entityName; 
    private Integer entityTypeCode; 
    private String entityOwnershipType; 
    private Integer relationshipTypeCode; 
    private String relationshipDescription; 
    private boolean relatedToOrganizationFlag; 
    private String orgRelationDescription; 
    private String sponsorCode; 
    private boolean currentFlag; 

    private FinIntEntityRelType finIntEntityRelType; 
    private FinIntEntityStatus finIntEntityStatus; 
    private OrganizationTypeList organizationTypeList; 
    private List<InvCoiDiscDetail> invCoiDiscDetails; 
    private List<FinIntEntityYnq> finIntEntityYnqs; 

    private Sponsor sponsor;
    
    public PersonFinIntDisclosure() { 

    } 
    
    public Long getPersonFinIntDisclosureId() {
        return personFinIntDisclosureId;
    }

    public void setPersonFinIntDisclosureId(Long personFinIntDisclosureId) {
        this.personFinIntDisclosureId = personFinIntDisclosureId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getEntityNumber() {
        return entityNumber;
    }

    public void setEntityNumber(String entityNumber) {
        this.entityNumber = entityNumber;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public Integer getEntityTypeCode() {
        return entityTypeCode;
    }

    public void setEntityTypeCode(Integer entityTypeCode) {
        this.entityTypeCode = entityTypeCode;
    }

    public String getEntityOwnershipType() {
        return entityOwnershipType;
    }

    public void setEntityOwnershipType(String entityOwnershipType) {
        this.entityOwnershipType = entityOwnershipType;
    }

    public Integer getRelationshipTypeCode() {
        return relationshipTypeCode;
    }

    public void setRelationshipTypeCode(Integer relationshipTypeCode) {
        this.relationshipTypeCode = relationshipTypeCode;
    }

    public String getRelationshipDescription() {
        return relationshipDescription;
    }

    public void setRelationshipDescription(String relationshipDescription) {
        this.relationshipDescription = relationshipDescription;
    }

    public boolean getRelatedToOrganizationFlag() {
        return relatedToOrganizationFlag;
    }

    public void setRelatedToOrganizationFlag(boolean relatedToOrganizationFlag) {
        this.relatedToOrganizationFlag = relatedToOrganizationFlag;
    }

    public String getOrgRelationDescription() {
        return orgRelationDescription;
    }

    public void setOrgRelationDescription(String orgRelationDescription) {
        this.orgRelationDescription = orgRelationDescription;
    }

    public String getSponsorCode() {
        return sponsorCode;
    }

    public void setSponsorCode(String sponsorCode) {
        this.sponsorCode = sponsorCode;
    }

    public FinIntEntityRelType getFinIntEntityRelType() {
        return finIntEntityRelType;
    }

    public void setFinIntEntityRelType(FinIntEntityRelType finIntEntityRelType) {
        this.finIntEntityRelType = finIntEntityRelType;
    }

    public FinIntEntityStatus getFinIntEntityStatus() {
        return finIntEntityStatus;
    }

    public void setFinIntEntityStatus(FinIntEntityStatus finIntEntityStatus) {
        this.finIntEntityStatus = finIntEntityStatus;
    }

    public OrganizationTypeList getOrganizationTypeList() {
        return organizationTypeList;
    }

    public void setOrganizationTypeList(OrganizationTypeList organizationTypeList) {
        this.organizationTypeList = organizationTypeList;
    }

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("personFinIntDisclosureId", this.getPersonFinIntDisclosureId());
        hashMap.put("personId", this.getPersonId());
        hashMap.put("entityNumber", this.getEntityNumber());
        hashMap.put("sequenceNumber", this.getSequenceNumber());
        hashMap.put("statusCode", this.getStatusCode());
        hashMap.put("statusDescription", this.getStatusDescription());
        hashMap.put("entityName", this.getEntityName());
        hashMap.put("entityTypeCode", this.getEntityTypeCode());
        hashMap.put("entityOwnershipType", this.getEntityOwnershipType());
        hashMap.put("relationshipTypeCode", this.getRelationshipTypeCode());
        hashMap.put("relationshipDescription", this.getRelationshipDescription());
        hashMap.put("relatedToOrganizationFlag", this.getRelatedToOrganizationFlag());
        hashMap.put("orgRelationDescription", this.getOrgRelationDescription());
        hashMap.put("sponsorCode", this.getSponsorCode());
        return hashMap;
    }

    public List<InvCoiDiscDetail> getInvCoiDiscDetails() {
        return invCoiDiscDetails;
    }

    public void setInvCoiDiscDetails(List<InvCoiDiscDetail> invCoiDiscDetails) {
        this.invCoiDiscDetails = invCoiDiscDetails;
    }

    public Sponsor getSponsor() {
        if (StringUtils.isNotBlank(sponsorCode) && sponsor == null) {
            this.refreshReferenceObject("sponsor");
        }
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }
    public List<FinIntEntityYnq> getFinIntEntityYnqs() {
        return finIntEntityYnqs;
    }

    public void setFinIntEntityYnqs(List<FinIntEntityYnq> finIntEntityYnqs) {
        this.finIntEntityYnqs = finIntEntityYnqs;
    }

    public boolean isCurrentFlag() {
        return currentFlag;
    }

    public void setCurrentFlag(boolean currentFlag) {
        this.currentFlag = currentFlag;
    }
 
}