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
package org.kuali.kra.irb.summary;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ProtocolSummary implements Serializable {

    private static final long serialVersionUID = 1880834136103817283L;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    
    private String protocolNumber;
    private String approvalDate;
    private String lastApprovalDate;
    private String applicationDate;
    private String expirationDate;
    private String piName;
    private Integer piProtocolPersonId;
    private String type;
    private String status;
    private String title;
    private List<PersonnelSummary> persons = new ArrayList<PersonnelSummary>();
    private List<ResearchAreaSummary> researchAreas = new ArrayList<ResearchAreaSummary>();
    private List<AttachmentSummary> attachments = new ArrayList<AttachmentSummary>();
    private List<FundingSourceSummary> fundingSources = new ArrayList<FundingSourceSummary>();
    private List<ParticipantSummary> participants = new ArrayList<ParticipantSummary>();
    private List<OrganizationSummary> organizations = new ArrayList<OrganizationSummary>();
    private List<SpecialReviewSummary> specialReviews = new ArrayList<SpecialReviewSummary>();

    private AdditionalInfoSummary additionalInfoSummary;
    
    public ProtocolSummary() {
        
    }

    public String getProtocolNumber() {
        return protocolNumber;
    }

    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public String getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = formatDate(approvalDate);
    }

    public String getLastApprovalDate() {
        return lastApprovalDate;
    }

    public void setLastApprovalDate(Date lastApprovalDate) {
        this.lastApprovalDate = formatDate(lastApprovalDate);
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = formatDate(applicationDate);
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = formatDate(expirationDate);
    }

    public String getPiName() {
        return piName;
    }

    public void setPiName(String piName) {
        this.piName = piName;
    }
    
    public Integer getPiProtocolPersonId() {
        return piProtocolPersonId;
    }
    
    public void setPiProtocolPersonId(Integer protocolPersonId) {
        this.piProtocolPersonId = protocolPersonId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    private String formatDate(Date date) {
        return (date == null) ? "" : dateFormat.format(date);
    }

    public List<PersonnelSummary> getPersons() {
        return persons;
    }
    
    public void add(PersonnelSummary personnelSummary) {
        persons.add(personnelSummary);
    }

    public List<ResearchAreaSummary> getResearchAreas() {
        return researchAreas;
    }
    
    public void add(ResearchAreaSummary researchAreaSummary) {
        researchAreas.add(researchAreaSummary);
    }
    
    public List<AttachmentSummary> getAttachments() {
        return attachments;
    }
    
    public void add(AttachmentSummary attachmentSummary) {
        attachments.add(attachmentSummary);
    }
    
    public List<FundingSourceSummary> getFundingSources() {
        return fundingSources;
    }
    
    public void add(FundingSourceSummary fundingSourceSummary) {
        fundingSources.add(fundingSourceSummary);
    }
    
    public List<ParticipantSummary> getParticipants() {
        return participants;
    }
    
    public void add(ParticipantSummary participantSummary) {
        participants.add(participantSummary);
    }

    public List<OrganizationSummary> getOrganizations() {
        return organizations;
    }
    
    public void add(OrganizationSummary organizationSummary) {
        organizations.add(organizationSummary);
    }

    public List<SpecialReviewSummary> getSpecialReviews() {
        return specialReviews;
    }
    
    public void add(SpecialReviewSummary specialReviewSummary) {
        specialReviews.add(specialReviewSummary);
    }

    public AdditionalInfoSummary getAdditionalInfo() {
        return additionalInfoSummary;
    }
    
    public void setAdditionalInfo(AdditionalInfoSummary additionalInfoSummary) {
        this.additionalInfoSummary = additionalInfoSummary;
    }
}
