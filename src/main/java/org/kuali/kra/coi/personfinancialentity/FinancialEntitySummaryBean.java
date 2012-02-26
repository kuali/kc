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

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.kuali.kra.coi.notesandattachments.attachments.FinancialEntityAttachmentSummary;

public class FinancialEntitySummaryBean implements Serializable {
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -8525835671837464825L;
    private String address;
    private String webAddress;
    private String relationshipDescription;
    private String sponsorName;
    private String statusDescription;
    private String ownershipType;
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
    
    
}
