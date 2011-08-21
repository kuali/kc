/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.coi;

import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * 
 * This class is the main bo class of Coi disclosure
 */
public class CoiDisclosure extends KraPersistableBusinessObjectBase { 
    
    private static final long serialVersionUID = 1L;

    private Long coiDisclosureId; 
    private String coiDisclosureNumber; 
    private Integer sequenceNumber; 
    private String personId; 
    private String certificationText; 
    private String certifiedBy; 
    private Date certificationTimestamp; 
    private String disclosureDispositionCode; 
    private String disclosureStatusCode; 
    private Date expirationDate; 
    private String moduleCode; 
    private String reviewStatusCode; 
    private Integer discActiveStatus; 
    private CoiDisclosureDocument coiDisclosureDocument;
    
//    private CoiStatus coiStatus; 
//    private CoiDispositionStatus coiDispositionStatus; 
//    private CoiDisclProjects coiDisclProjects; 
//    private CoiDiscDetails coiDiscDetails; 
//    private CoiDocuments coiDocuments; 
//    private CoiNotepad coiNotepad; 
//    private CoiUserRoles coiUserRoles; 
    
    public CoiDisclosure() { 

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

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getCertificationText() {
        return certificationText;
    }

    public void setCertificationText(String certificationText) {
        this.certificationText = certificationText;
    }

    public String getCertifiedBy() {
        return certifiedBy;
    }

    public void setCertifiedBy(String certifiedBy) {
        this.certifiedBy = certifiedBy;
    }

    public Date getCertificationTimestamp() {
        return certificationTimestamp;
    }

    public void setCertificationTimestamp(Date certificationTimestamp) {
        this.certificationTimestamp = certificationTimestamp;
    }

    public String getDisclosureDispositionCode() {
        return disclosureDispositionCode;
    }

    public void setDisclosureDispositionCode(String disclosureDispositionCode) {
        this.disclosureDispositionCode = disclosureDispositionCode;
    }

    public String getDisclosureStatusCode() {
        return disclosureStatusCode;
    }

    public void setDisclosureStatusCode(String disclosureStatusCode) {
        this.disclosureStatusCode = disclosureStatusCode;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getReviewStatusCode() {
        return reviewStatusCode;
    }

    public void setReviewStatusCode(String reviewStatusCode) {
        this.reviewStatusCode = reviewStatusCode;
    }

    public Integer getDiscActiveStatus() {
        return discActiveStatus;
    }

    public void setDiscActiveStatus(Integer discActiveStatus) {
        this.discActiveStatus = discActiveStatus;
    }

//    public CoiStatus getCoiStatus() {
//        return coiStatus;
//    }
//
//    public void setCoiStatus(CoiStatus coiStatus) {
//        this.coiStatus = coiStatus;
//    }
//
//    public CoiDispositionStatus getCoiDispositionStatus() {
//        return coiDispositionStatus;
//    }
//
//    public void setCoiDispositionStatus(CoiDispositionStatus coiDispositionStatus) {
//        this.coiDispositionStatus = coiDispositionStatus;
//    }
//
//    public CoiDisclProjects getCoiDisclProjects() {
//        return coiDisclProjects;
//    }
//
//    public void setCoiDisclProjects(CoiDisclProjects coiDisclProjects) {
//        this.coiDisclProjects = coiDisclProjects;
//    }
//
//    public CoiDiscDetails getCoiDiscDetails() {
//        return coiDiscDetails;
//    }
//
//    public void setCoiDiscDetails(CoiDiscDetails coiDiscDetails) {
//        this.coiDiscDetails = coiDiscDetails;
//    }
//
//    public CoiDocuments getCoiDocuments() {
//        return coiDocuments;
//    }
//
//    public void setCoiDocuments(CoiDocuments coiDocuments) {
//        this.coiDocuments = coiDocuments;
//    }
//
//    public CoiNotepad getCoiNotepad() {
//        return coiNotepad;
//    }
//
//    public void setCoiNotepad(CoiNotepad coiNotepad) {
//        this.coiNotepad = coiNotepad;
//    }
//
//    public CoiUserRoles getCoiUserRoles() {
//        return coiUserRoles;
//    }
//
//    public void setCoiUserRoles(CoiUserRoles coiUserRoles) {
//        this.coiUserRoles = coiUserRoles;
//    }

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("coiDisclosureId", this.getCoiDisclosureId());
        hashMap.put("coiDisclosureNumber", this.getCoiDisclosureNumber());
        hashMap.put("sequenceNumber", this.getSequenceNumber());
        hashMap.put("personId", this.getPersonId());
        hashMap.put("certificationText", this.getCertificationText());
        hashMap.put("certifiedBy", this.getCertifiedBy());
        hashMap.put("certificationTimestamp", this.getCertificationTimestamp());
        hashMap.put("disclosureDispositionCode", this.getDisclosureDispositionCode());
        hashMap.put("disclosureStatusCode", this.getDisclosureStatusCode());
        hashMap.put("expirationDate", this.getExpirationDate());
        hashMap.put("moduleCode", this.getModuleCode());
        hashMap.put("reviewStatusCode", this.getReviewStatusCode());
        hashMap.put("discActiveStatus", this.getDiscActiveStatus());
        return hashMap;
    }

    public CoiDisclosureDocument getCoiDisclosureDocument() {
        return coiDisclosureDocument;
    }

    public void setCoiDisclosureDocument(CoiDisclosureDocument coiDisclosureDocument) {
        this.coiDisclosureDocument = coiDisclosureDocument;
    }
    
}