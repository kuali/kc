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
package org.kuali.kra.institutionalproposal.proposallog;

import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.proposaldevelopment.bo.ProposalType;

public class ProposalLog extends KraPersistableBusinessObjectBase { 
    
    private static final long serialVersionUID = 1L;

    private String proposalNumber; 
    private Integer proposalTypeCode; 
    private String title; 
    private String piId; 
    private Integer rolodexId;
    private String piName; 
    private String leadUnit; 
    private String sponsorCode; 
    private String sponsorName; 
    private boolean logStatus; 
    private String comments; 
    private Date deadlineDate; 
    
    private ProposalType proposalType;
    private Person person;
    private Rolodex rolodex;
    private Sponsor sponsor;
    
    public ProposalLog() { 

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
    
    public Integer getRolodexId() {
        return rolodexId;
    }

    public void setRolodexId(Integer rolodexId) {
        this.rolodexId = rolodexId;
    }

    public String getPiName() {
        return piName;
    }

    public void setPiName(String piName) {
        this.piName = piName;
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
    
    public ProposalType getProposalType() {
        return proposalType;
    }

    public void setProposalType(ProposalType proposalType) {
        this.proposalType = proposalType;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Rolodex getRolodex() {
        return rolodex;
    }

    public void setRolodex(Rolodex rolodex) {
        this.rolodex = rolodex;
    }

    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
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
        hashMap.put("leadUnit", this.getLeadUnit());
        hashMap.put("sponsorCode", this.getSponsorCode());
        hashMap.put("sponsorName", this.getSponsorName());
        hashMap.put("logStatus", this.getLogStatus());
        hashMap.put("comments", this.getComments());
        hashMap.put("deadlineDate", this.getDeadlineDate());
        return hashMap;
    }
    
}