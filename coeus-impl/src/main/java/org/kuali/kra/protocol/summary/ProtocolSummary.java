/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
package org.kuali.kra.protocol.summary;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.protocol.actions.ProtocolActionBase;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;



public class ProtocolSummary implements Serializable {

    private static final long serialVersionUID = 1880834136103817283L;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    
    private String lastProtocolActionDescription;
    private String protocolNumber;
    private String initialSubmissionDate;
    private String approvalDate;
    private String lastApprovalDate;
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
 
    private List<OrganizationSummary> organizations = new ArrayList<OrganizationSummary>();
    private List<SpecialReviewSummary> specialReviews = new ArrayList<SpecialReviewSummary>();
    private AdditionalInfoSummary additionalInfoSummary;
    
    private boolean protocolNumberChanged = false;
    private boolean initialSubmissionDateChanged = false;
    private boolean approvalDateChanged = false;
    private boolean lastApprovalDateChanged = false;
    private boolean expirationDateChanged = false;
    private boolean piNameChanged = false;
    private boolean typeChanged = false;
    private boolean statusChanged = false;
    private boolean titleChanged = false;
    
    public ProtocolSummary() {
        
    }
    
    public void setLastProtocolAction(ProtocolActionBase protocolAction) {
        if (protocolAction == null) {
            lastProtocolActionDescription = "";
        }
        else {
            lastProtocolActionDescription = protocolAction.getProtocolActionType().getDescription() + " " + 
                                            dateFormat.format(protocolAction.getActionDate());
        }
    }
    
    public String getLastProtocolActionDescription() {
        return lastProtocolActionDescription;
    }

    public String getProtocolNumber() {
        return protocolNumber;
    }

    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }
    
    public String getInitialSubmissionDate() {
        return initialSubmissionDate;
    }

    public void setInitialSubmissionDate(Date initialSubmissionDate) {
        this.initialSubmissionDate = formatDate(initialSubmissionDate);
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
    
    private String formatDate(java.util.Date date) {
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
    
    public void compare(ProtocolSummary other) {
        protocolNumberChanged = !StringUtils.equals(protocolNumber, other.protocolNumber);
        initialSubmissionDateChanged = !StringUtils.equals(initialSubmissionDate, other.initialSubmissionDate);
        approvalDateChanged = !StringUtils.equals(approvalDate, other.approvalDate);
        lastApprovalDateChanged = !StringUtils.equals(lastApprovalDate, other.lastApprovalDate);
        expirationDateChanged = !StringUtils.equals(expirationDate, other.expirationDate);
        piNameChanged = !StringUtils.equals(piName, other.piName);
        typeChanged = !StringUtils.equals(type, other.type);
        statusChanged = !StringUtils.equals(status, other.status);
        titleChanged = !StringUtils.equals(title, other.title);
        
        comparePersonnel(other);
        compareResearchAreas(other);
  
        compareSpecialReviews(other);
        compareFundingSources(other);
        compareOrganizations(other);
        compareAttachments(other);
        
        additionalInfoSummary.compare(other.getAdditionalInfo());
    }

    private void compareAttachments(ProtocolSummary other) {
        for (AttachmentSummary attachment : attachments) {
            attachment.compare(other);
        }
    }

    private void compareOrganizations(ProtocolSummary other) {
        for (OrganizationSummary organization : organizations) {
            organization.compare(other);
        }
    }

    private void compareFundingSources(ProtocolSummary other) {
        for (FundingSourceSummary fundingSource : fundingSources) {
            fundingSource.compare(other);
        }
    }

    private void compareSpecialReviews(ProtocolSummary other) {
        for (SpecialReviewSummary specialReview : specialReviews) {
            specialReview.compare(other);
        }
    }

    private void comparePersonnel(ProtocolSummary other) {
        for (PersonnelSummary person : persons) {
            person.compare(other);
        }
    }
    
    private void compareResearchAreas(ProtocolSummary other) {
        for (ResearchAreaSummary researchArea : researchAreas) {
            researchArea.compare(other);
        }
    }
    
    public boolean isProtocolNumberChanged() {
        return protocolNumberChanged;
    }
    
    public boolean isInitialSubmissionDateChanged() {
        return initialSubmissionDateChanged;
    }

    public boolean isApprovalDateChanged() {
        return approvalDateChanged;
    }

    public boolean isLastApprovalDateChanged() {
        return lastApprovalDateChanged;
    }

    public boolean isExpirationDateChanged() {
        return expirationDateChanged;
    }

    public boolean isPiNameChanged() {
        return piNameChanged;
    }

    public boolean isTypeChanged() {
        return typeChanged;
    }

    public boolean isStatusChanged() {
        return statusChanged;
    }

    public boolean isTitleChanged() {
        return titleChanged;
    }

    public PersonnelSummary findPerson(String name) {
        for (PersonnelSummary person : persons) {
            if (StringUtils.equals(person.getPersonId(), name)) {
                return person;
            }
        }
        return null;
    }

    public ResearchAreaSummary findResearchArea(String researchAreaCode) {
        for (ResearchAreaSummary researchArea : researchAreas) {
            if (StringUtils.equals(researchArea.getResearchAreaCode(), researchAreaCode)) {
                return researchArea;
            }
        }
        return null;
    }

    public SpecialReviewSummary findSpecialReview(String type, String approvalStatus) {
        for (SpecialReviewSummary specialReview : specialReviews) {
            if (StringUtils.equals(specialReview.getType(), type) &&
                StringUtils.equals(specialReview.getApprovalStatus(), approvalStatus)) {
                return specialReview;
            }
        }
        return null;
    }

    public FundingSourceSummary findFundingSource(String fundingSourceType, String fundingSource) {
        for (FundingSourceSummary source : fundingSources) {
            if (StringUtils.equals(source.getFundingSourceType(), fundingSourceType) 
                    && StringUtils.equals(source.getFundingSource(), fundingSource)) {
                return source;
            }
        }
        return null;
    }

    public OrganizationSummary findOrganization(String organizationId) {
        for (OrganizationSummary organization : organizations) {
            if (StringUtils.equals(organization.getOrganizationId(), organizationId)) {
                return organization;
            }
        }
        return null;
    }

    public AttachmentSummary findAttachment(String fileName, String fileType, long dataLength) {
        for (AttachmentSummary attachment : attachments) {
            if (StringUtils.equals(attachment.getFileName(), fileName) &&
                StringUtils.equals(attachment.getFileType(), fileType) &&
                attachment.getDataLength() == dataLength) {
                return attachment;
            }
        }
        return null;
    }    
}
