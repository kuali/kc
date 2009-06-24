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
package org.kuali.kra.institutionalproposal.home;

import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class InstitutionalProposalLog extends KraPersistableBusinessObjectBase { 
    
    private static final long serialVersionUID = 1L;

    private String proposalNumber; 
    private Integer proposalTypeCode; 
    private String title; 
    private String piId; 
    private String piName; 
    private boolean nonMitPersonFlag; 
    private String leadUnit; 
    private String sponsorCode; 
    private String sponsorName; 
    private boolean logStatus; 
    private String comments; 
    private Date deadlineDate; 
    
    
    public InstitutionalProposalLog() { 

    } 
    
    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public Integer getProposalTypeCode() {
        return proposalTypeCode;
    }

    public void setProposalTypeCode(Integer proposalTypeCode) {
        this.proposalTypeCode = proposalTypeCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPiId() {
        return piId;
    }

    public void setPiId(String piId) {
        this.piId = piId;
    }

    public String getPiName() {
        return piName;
    }

    public void setPiName(String piName) {
        this.piName = piName;
    }

    public boolean getNonMitPersonFlag() {
        return nonMitPersonFlag;
    }

    public void setNonMitPersonFlag(boolean nonMitPersonFlag) {
        this.nonMitPersonFlag = nonMitPersonFlag;
    }

    public String getLeadUnit() {
        return leadUnit;
    }

    public void setLeadUnit(String leadUnit) {
        this.leadUnit = leadUnit;
    }

    public String getSponsorCode() {
        return sponsorCode;
    }

    public void setSponsorCode(String sponsorCode) {
        this.sponsorCode = sponsorCode;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    public boolean getLogStatus() {
        return logStatus;
    }

    public void setLogStatus(boolean logStatus) {
        this.logStatus = logStatus;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("proposalNumber", this.getProposalNumber());
        hashMap.put("proposalTypeCode", this.getProposalTypeCode());
        hashMap.put("title", this.getTitle());
        hashMap.put("piId", this.getPiId());
        hashMap.put("piName", this.getPiName());
        hashMap.put("nonMitPersonFlag", this.getNonMitPersonFlag());
        hashMap.put("leadUnit", this.getLeadUnit());
        hashMap.put("sponsorCode", this.getSponsorCode());
        hashMap.put("sponsorName", this.getSponsorName());
        hashMap.put("logStatus", this.getLogStatus());
        hashMap.put("comments", this.getComments());
        hashMap.put("deadlineDate", this.getDeadlineDate());
        return hashMap;
    }
    
}