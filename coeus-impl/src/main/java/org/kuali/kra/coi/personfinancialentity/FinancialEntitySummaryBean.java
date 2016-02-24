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
package org.kuali.kra.coi.personfinancialentity;

import org.kuali.kra.coi.notesandattachments.attachments.FinancialEntityAttachmentSummary;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class FinancialEntitySummaryBean implements Serializable {

    private static final long serialVersionUID = -8525835671837464825L;
    private String address;
    private String webAddress;
    private String relationshipDescription;
    private String sponsorName;
    private String statusDescription;
    private String ownershipType;
    private String entitySponsorsResearch;
    private String details;
    private Map<String, String> relationshipDetails;
    private List<FinancialEntityAttachmentSummary> attachmentSummary;
    private FinancialEntityForm financialEntityForm;
    
    
    public FinancialEntitySummaryBean(FinancialEntityForm financialEntityForm) {
        this.financialEntityForm = financialEntityForm;
    }
    public FinancialEntityForm getFinancialEntityForm() {
        return financialEntityForm;
    }
    public void setFinancialEntityForm(FinancialEntityForm financialEntityForm) {
        this.financialEntityForm = financialEntityForm;
    }
    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }
    public Map<String, String> getRelationshipDetails() {
        return relationshipDetails;
    }
    public void setRelationshipDetails(Map<String, String> relationshipDetails) {
        this.relationshipDetails = relationshipDetails;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getWebAddress() {
        return webAddress;
    }
    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }
    public String getRelationshipDescription() {
        return relationshipDescription;
    }
    public void setRelationshipDescription(String relationshipDescription) {
        this.relationshipDescription = relationshipDescription;
    }
    public String getSponsorName() {
        return sponsorName;
    }
    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }
    public String getStatusDescription() {
        return statusDescription;
    }
    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }
    public String getOwnershipType() {
        return ownershipType;
    }
    public void setOwnershipType(String ownershipType) {
        this.ownershipType = ownershipType;
    }
    public List<FinancialEntityAttachmentSummary> getAttachmentSummary() {
        return attachmentSummary;
    }
    public void setAttachmentSummary(List<FinancialEntityAttachmentSummary> attachmentSummary) {
        this.attachmentSummary = attachmentSummary;
    }
    public String getEntitySponsorsResearch() {
        return entitySponsorsResearch;
    }
    public void setEntitySponsorsResearch(String entitySponsorsResearch) {
        this.entitySponsorsResearch = entitySponsorsResearch;
    }
    
    
}
