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

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerException;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.proposaldevelopment.bo.ProposalType;

public class ProposalLog extends KraPersistableBusinessObjectBase { 
    
    private static final long serialVersionUID = 1L;

    private String proposalNumber; 
    private String proposalTypeCode; 
    private String title; 
    private String piId; 
    private Integer rolodexId;
    private String piName; 
    private String leadUnit; 
    private String sponsorCode; 
    private String sponsorName; 
    private String logStatus; 
    private String comments; 
    private Date deadlineDate; 
    private String proposalLogTypeCode;
    private Integer fiscalMonth;
    private Integer fiscalYear;
    
    private ProposalType proposalType;
    private Person person;
    private Rolodex rolodex;
    private Sponsor sponsor;
    private ProposalLogStatus proposalLogStatus;
    private Unit unit;
    private ProposalLogType proposalLogType;
    
    public ProposalLog() { 

    } 
    
    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public String getProposalTypeCode() {
        return proposalTypeCode;
    }

    public void setProposalTypeCode(String proposalTypeCode) {
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

    public String getLogStatus() {
        return logStatus;
    }

    public void setLogStatus(String logStatus) {
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
    
    public String getProposalLogTypeCode() {
        return proposalLogTypeCode;
    }

    public void setProposalLogTypeCode(String proposalLogTypeCode) {
        this.proposalLogTypeCode = proposalLogTypeCode;
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
    
    public ProposalLogStatus getProposalLogStatus() {
        return proposalLogStatus;
    }

    public void setProposalLogStatus(ProposalLogStatus proposalLogStatus) {
        this.proposalLogStatus = proposalLogStatus;
    }
    
    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
    
    public ProposalLogType getProposalLogType() {
        return proposalLogType;
    }

    public void setProposalLogType(ProposalLogType proposalLogType) {
        this.proposalLogType = proposalLogType;
    }
    
    public Integer getFiscalMonth() {
        return fiscalMonth;
    }

    public void setFiscalMonth(Integer fiscalMonth) {
        this.fiscalMonth = fiscalMonth;
    }

    public Integer getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(Integer fiscalYear) {
        this.fiscalYear = fiscalYear;
    }
    
    public String getFiscalMonthYear() {
        if (this.getFiscalMonth() != null && this.getFiscalYear() != null) {
            return this.getFiscalMonth().toString() + " / " + this.getFiscalYear().toString();
        }
        return "";
    }
    
    /* These methods are for manipulating data before object persistence. */
    
    /**
     * @see org.kuali.core.bo.PersistableBusinessObjectBase#beforeInsert()
     */
    @Override
    public void beforeInsert(PersistenceBroker persistenceBroker) throws PersistenceBrokerException {
        super.beforeInsert(persistenceBroker);
        setSponsorName();
    }

    /**
     * @see org.kuali.core.bo.PersistableBusinessObjectBase#beforeInsert()
     */
    @Override
    public void beforeUpdate(PersistenceBroker persistenceBroker) throws PersistenceBrokerException {
        super.beforeUpdate(persistenceBroker);
        setSponsorName();
    }
    
    /*
     * This method will set the Sponsor name field from the Sponsor.
     * We need to denormalize this data before persistence for Coeus database compatibility.
     */
    private void setSponsorName() {
        if (this.getSponsorCode() != null) {
            this.refreshReferenceObject("sponsor");
            if (this.getSponsor() != null) {
                this.setSponsorName(this.getSponsor().getSponsorName());
            }
        }
    }
    
    /* End data persistence methods. */

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("proposalNumber", this.getProposalNumber());
        hashMap.put("proposalTypeCode", this.getProposalTypeCode());
        hashMap.put("proposalLogTypeCode", this.getProposalLogTypeCode());
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
