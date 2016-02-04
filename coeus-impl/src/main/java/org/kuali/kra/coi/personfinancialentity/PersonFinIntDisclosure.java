/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.coi.personfinancialentity;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.org.type.OrganizationTypeList;
import org.kuali.coeus.common.framework.version.sequence.owner.SequenceOwner;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.coi.notesandattachments.attachments.FinancialEntityAttachment;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.datetime.DateTimeService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class is bo of person Int. FE disclosure
 */
public class PersonFinIntDisclosure extends KcPersistableBusinessObjectBase implements SequenceOwner<PersonFinIntDisclosure> {


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
    private List<FinancialEntityAttachment> finEntityAttachments;
    private Sponsor sponsor;
    private String sponsorName;
    private String entitySponsorsResearch;
    private String studentInvolvement;
    private String staffInvolvement;
    private String facilityUse;

    private transient DateTimeService dateTimeService;
    
    // @SkipVersioning
    private FinancialEntityReporter financialEntityReporter;
    
    // foe FE history version display
    @SkipVersioning
    private List<PersonFinIntDisclosure> versions;


    public PersonFinIntDisclosure() { 
        super();
        finEntityContactInfos = new ArrayList<FinancialEntityContactInfo>();
        finEntityContactInfos.add(new FinancialEntityContactInfo());
        finEntityAttachments = new ArrayList<FinancialEntityAttachment>();
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

        
    }

    public PersonFinIntDisclosure getSequenceOwner() {

        return this;
    }

    public void resetPersistenceState() {
        this.personFinIntDisclosureId = null;
        
    }

    public void incrementSequenceNumber() {
        this.sequenceNumber++; 
                
    }

    public Integer getOwnerSequenceNumber() {

        return null;
    }

    public String getVersionNameField() {
        return "entityNumber";
    }

    @Override
    public String getVersionNameFieldValue() {
        return entityNumber;
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

    public List<FinancialEntityAttachment> getFinEntityAttachments() {
        return finEntityAttachments;
    }

    public void setFinEntityAttachments(List<FinancialEntityAttachment> finEntityAttachments) {
        this.finEntityAttachments = finEntityAttachments;
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

    @SuppressWarnings("unchecked")
    @Override
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        managedLists.add(finEntityAttachments);
        return managedLists;
    }

    public String getEntitySponsorsResearch() {
        return entitySponsorsResearch;
    }

    public void setEntitySponsorsResearch(String entitySponsorsResearch) {
        this.entitySponsorsResearch = entitySponsorsResearch;
    }

    public String getStudentInvolvement() {
        return studentInvolvement;
    }

    public void setStudentInvolvement(String studentInvolvement) {
        this.studentInvolvement = studentInvolvement;
    }

    public String getStaffInvolvement() {
        return staffInvolvement;
    }

    public void setStaffInvolvement(String staffInvolvement) {
        this.staffInvolvement = staffInvolvement;
    }

    public String getFacilityUse() {
        return facilityUse;
    }

    public void setFacilityUse(String facilityUse) {
        this.facilityUse = facilityUse;
    }

}
