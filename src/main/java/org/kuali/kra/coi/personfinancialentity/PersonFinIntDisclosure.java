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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.SequenceOwner;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.OrganizationTypeList;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.infrastructure.Constants;

/**
 * 
 * This class is bo of person Int. FE disclosure
 */
public class PersonFinIntDisclosure extends KraPersistableBusinessObjectBase implements SequenceOwner<PersonFinIntDisclosure> { 

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -3218103115548868080L;
    private Long personFinIntDisclosureId;
    private Long financialEntityReporterId;
    private String personId;
    private String entityNumber;
    private Integer sequenceNumber;
    private Integer statusCode;
    private String statusDescription;
    private String entityName;
    private Integer entityTypeCode;
    private String entityOwnershipType;
    private String relationshipTypeCode;
    private String relationshipDescription;
    private boolean relatedToOrganizationFlag;
    private String orgRelationDescription;
    private String principalBusinessActivity;
    private String sponsorCode;
    // added this flag to ease the search of current person FE.
    private boolean currentFlag;
    // this is for 'edit' save/submit ('S'/'F')
    private String processStatus;
    private FinIntEntityRelType finIntEntityRelType;
    private FinIntEntityStatus finIntEntityStatus;
    private OrganizationTypeList organizationTypeList;
    private List<InvCoiDiscDetail> invCoiDiscDetails;
    private List<FinIntEntityYnq> finIntEntityYnqs;
    private List<PersonFinIntDisclDet> perFinIntDisclDetails;
    private List<FinancialEntityContactInfo> finEntityContactInfos;
    private Sponsor sponsor;
    private String sponsorName;
    
    // @SkipVersioning
    private FinancialEntityReporter financialEntityReporter;
    
    // foe FE history version display
    @SkipVersioning
    private List<PersonFinIntDisclosure> versions;


    public PersonFinIntDisclosure() { 
        super();
        finEntityContactInfos = new ArrayList<FinancialEntityContactInfo>();
        finEntityContactInfos.add(new FinancialEntityContactInfo());
    } 
    
    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
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

    public String getRelationshipTypeCode() {
        return relationshipTypeCode;
    }

    public void setRelationshipTypeCode(String relationshipTypeCode) {
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
        if (finIntEntityStatus == null && this.statusCode != null) {
            this.refreshReferenceObject("finIntEntityStatus");
        }
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

    public String getSponsorName() {
        if (getSponsor() == null) {
            return Constants.EMPTY_STRING;
        } else {
            return sponsor.getSponsorName();
        }
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


    public Long getFinancialEntityReporterId() {
        return financialEntityReporterId;
    }

    public void setFinancialEntityReporterId(Long financialEntityReporterId) {
        this.financialEntityReporterId = financialEntityReporterId;
    }

    public Long getPersonFinIntDisclosureId() {
        return personFinIntDisclosureId;
    }

    public void setPersonFinIntDisclosureId(Long personFinIntDisclosureId) {
        this.personFinIntDisclosureId = personFinIntDisclosureId;
    }

    public FinancialEntityReporter getFinancialEntityReporter() {
        return financialEntityReporter;
    }

    public void setFinancialEntityReporter(FinancialEntityReporter financialEntityReporter) {
        this.financialEntityReporter = financialEntityReporter;
    }

    public List<PersonFinIntDisclDet> getPerFinIntDisclDetails() {
        return perFinIntDisclDetails;
    }

    public void setPerFinIntDisclDetails(List<PersonFinIntDisclDet> perFinIntDisclDetails) {
        this.perFinIntDisclDetails = perFinIntDisclDetails;
    }

    public void setSequenceOwner(PersonFinIntDisclosure newlyVersionedOwner) {
        // TODO Auto-generated method stub
        
    }

    public PersonFinIntDisclosure getSequenceOwner() {
        // TODO Auto-generated method stub
        return this;
    }

    public void resetPersistenceState() {
        this.personFinIntDisclosureId = null;
        
    }

    public void incrementSequenceNumber() {
        this.sequenceNumber++; 
                
    }

    public Integer getOwnerSequenceNumber() {
        // TODO Auto-generated method stub
        return null;
    }

    public String getVersionNameField() {
        // TODO Auto-generated method stub
        return "entityNumber";
    }

    public List<PersonFinIntDisclosure> getVersions() {
        return versions;
    }

    public void setVersions(List<PersonFinIntDisclosure> versions) {
        this.versions = versions;
    }

    public List<FinancialEntityContactInfo> getFinEntityContactInfos() {
        return finEntityContactInfos;
    }

    public void setFinEntityContactInfos(List<FinancialEntityContactInfo> finEntityContactInfos) {
        this.finEntityContactInfos = finEntityContactInfos;
    }

    public String getPrincipalBusinessActivity() {
        return principalBusinessActivity;
    }

    public void setPrincipalBusinessActivity(String principalBusinessActivity) {
        this.principalBusinessActivity = principalBusinessActivity;
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    @Override
    public void setUpdateTimestamp(Timestamp updateTimestamp) {
        // if it is just to set FE to non-current, then don't update the timestamp
        if (isCurrentFlag()) {
            super.setUpdateTimestamp(updateTimestamp);
        }
    }
    
    public boolean isStatusActive() {
        return StringUtils.equals(FinIntEntityStatus.ACTIVE, this.statusCode.toString());
    }
    
}