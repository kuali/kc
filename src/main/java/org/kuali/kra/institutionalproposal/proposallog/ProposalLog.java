/*
 * Copyright 2005-2010 The Kuali Foundation
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ojb.broker.PersistenceBroker;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.NonOrganizationalRolodex;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPerson;
import org.kuali.kra.institutionalproposal.proposallog.service.ProposalLogService;
import org.kuali.kra.negotiations.bo.Negotiable;
import org.kuali.kra.negotiations.bo.NegotiationPersonDTO;
import org.kuali.kra.proposaldevelopment.bo.ProposalType;
import org.kuali.kra.service.KcPersonService;

/**
 * Encapsulates data and behavior of a Proposal Log.
 */
public class ProposalLog extends KraPersistableBusinessObjectBase implements Negotiable { 
    
    /** Log Status property name. */
    static final String LOG_STATUS = "logStatus";
    
    /** Proposal Log Type Code property name. */
    static final String PROPOSAL_LOG_TYPE_CODE = "proposalLogTypeCode";
    
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
    private String createUser;
    private Timestamp createTimestamp;
    
    private ProposalType proposalType;
    private NonOrganizationalRolodex rolodex;
    private Sponsor sponsor;
    private ProposalLogStatus proposalLogStatus;
    private Unit unit;
    private ProposalLogType proposalLogType;
    
    private String proposalLogToMerge;
    
    private String mergedWith;
    private String instProposalNumber;
    
    private transient KcPersonService kcPersonService;
    
    /**
     * Constructs a ProposalLog.java.
     */
    public ProposalLog() { 

    } 
    
    /* Public convenience methods */
    
    /**
     * Determine whether this Proposal Log has been persisted.
     * 
     * @return boolean
     */
    public boolean isPersisted() {
        return this.getVersionNumber() != null;
    }
    
    /**
     * Determine whether this Proposal Log has been promoted to an Institutional Proposal.
     * 
     * @return boolean
     */
    public boolean isSubmitted() {
        return ProposalLogUtils.getProposalLogSubmittedStatusCode().equals(this.getLogStatus());
    }
    
    /**
     * Determine whether this Proposal Log has a proposal log type of Temporary.
     * 
     * @return boolean
     */
    public boolean isLogTypeTemporary() {
        return ProposalLogUtils.getProposalLogTemporaryTypeCode().equals(this.getProposalLogTypeCode());
    }
    
    /**
     * Determine whether this Proposal Log is a candidate to be merged with another Proposal Log.
     * 
     * @return boolean
     */
    public boolean isMergeCandidate() {
        return ProposalLogUtils.getProposalLogTemporaryTypeCode().equals(this.getProposalLogTypeCode())
            && !ProposalLogUtils.getProposalLogMergedStatusCode().equals(this.getLogStatus());
    }
    
    /* End public convenience methods */
    
    /* Getters and setters */
    
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
        if (!isEmpty(sponsorCode)) {
            setSponsorName();
        }
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

    public KcPerson getPerson() {
        if (this.piId != null) {
            return this.getKcPersonService().getKcPersonByPersonId(this.piId);
        }
        return null;
    }
    
    public NonOrganizationalRolodex getRolodex() {
        return rolodex;
    }

    public void setRolodex(NonOrganizationalRolodex rolodex) {
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
    
    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }
    
    public String getProposalLogToMerge() {
        return proposalLogToMerge;
    }

    public void setProposalLogToMerge(String proposalLogToMerge) {
        this.proposalLogToMerge = proposalLogToMerge;
    }
    
    public String getMergedWith()
    {
        return mergedWith;
    }
    
    public void setMergedWith(String mergedWith)
    {
        this.mergedWith = mergedWith;
    }

    public String getInstProposalNumber()
    {
        return instProposalNumber;
    }
    
    public void setInstProposalNumber(String instProposalNumber)
    {
        this.instProposalNumber = instProposalNumber;
    }
   
    /* End getters and setters */
    
    
    
    /* These methods are for manipulating data before object persistence. */

    /**
     * @see org.kuali.core.bo.PersistableBusinessObjectBase#beforeInsert()
     */
    @Override 
    public void beforeInsert(PersistenceBroker persistenceBroker) {
        super.beforeInsert(persistenceBroker);
        setSponsorName();
        mergeTemporaryLog();
    }

    @Override
    public void afterInsert(PersistenceBroker persistenceBroker) 
    {
        // this will update the associated temporary log to indicate that it was
        // merged with the current permanent log
        if (getMergedWith() != null)
        {
            super.afterInsert(persistenceBroker);
            KraServiceLocator.getService(ProposalLogService.class).updateMergedTempLog(getMergedWith(), getProposalNumber() );
        }
        return;
    }
    
    
    /**
     * @see org.kuali.core.bo.PersistableBusinessObjectBase#beforeInsert()
     */
    @Override
    public void beforeUpdate(PersistenceBroker persistenceBroker) {
        super.beforeUpdate(persistenceBroker);
        setSponsorName();
    }
    
    /*
     * This method will set the Sponsor name field from the Sponsor.
     * We need to denormalize this data before persistence for Coeus database compatibility.
     */
    private void setSponsorName() {
        if (!isEmpty(this.getSponsorCode())) {
            this.refreshReferenceObject("sponsor");
            if (this.getSponsor()!= null) {
                sponsorName = this.getSponsor().getSponsorName();
            }
            else {
                sponsorName = null;
            }
        }
    }
    
    private void mergeTemporaryLog() {
        if (this.getProposalLogToMerge() != null) {
            KraServiceLocator.getService(ProposalLogService.class).mergeProposalLog(this.getProposalLogToMerge());
        }
    }
    
    /* End data persistence methods. */

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("proposalNumber", this.getProposalNumber());
        hashMap.put("proposalTypeCode", this.getProposalTypeCode());
        hashMap.put(PROPOSAL_LOG_TYPE_CODE, this.getProposalLogTypeCode());
        hashMap.put("title", this.getTitle());
        hashMap.put("piId", this.getPiId());
        hashMap.put("piName", this.getPiName());
        hashMap.put("leadUnit", this.getLeadUnit());
        hashMap.put("sponsorCode", this.getSponsorCode());
        hashMap.put("sponsorName", this.getSponsorName());
        hashMap.put(LOG_STATUS, this.getLogStatus());
        hashMap.put("comments", this.getComments());
        hashMap.put("deadlineDate", this.getDeadlineDate());
        hashMap.put("mergedWith", this.getMergedWith());
        hashMap.put("instProposalNumber", this.getInstProposalNumber());
        return hashMap;
    }
    
    /**
     * Gets the KC Person Service.
     * @return KC Person Service.
     */
    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KraServiceLocator.getService(KcPersonService.class);
        }
        
        return this.kcPersonService;
    }
    
    boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    @Override
    public String getLeadUnitNumber() {
        this.refreshReferenceObject("unit");
        String number = getUnit() == null ? EMPTY_STRING : getUnit().getUnitNumber();
        return number;
    }

    @Override
    public String getLeadUnitName() {
        this.refreshReferenceObject("unit");
        String name = getUnit() == null ? EMPTY_STRING : getUnit().getUnitName();
        return name;
    }

    @Override
    public String getPiEmployeeName() {
        String name = getPerson() == null ? "" : getPerson().getFullName();
        return name;
    }

    @Override
    public String getPiNonEmployeeName() {
        String name = getRolodex() == null ? "" : getRolodex().getFullName();
        return name;
    }

    @Override
    public String getAdminPersonName() {
        return EMPTY_STRING;
    }

    @Override
    public String getPrimeSponsorCode() {
        return EMPTY_STRING;
    }

    @Override
    public String getPrimeSponsorName() {
        return EMPTY_STRING;
    }

    @Override
    public String getSponsorAwardNumber() {
        return EMPTY_STRING;
    }

    @Override
    public String getSubAwardOrganizationName() {
        return EMPTY_STRING;
    }
    
    @Override
    public List<NegotiationPersonDTO> getProjectPeople() {
        List<NegotiationPersonDTO> kcPeople = new ArrayList<NegotiationPersonDTO>();
        if (this.getPerson() != null) {
            kcPeople.add(new NegotiationPersonDTO(this.getPerson(), Constants.PRINCIPAL_INVESTIGATOR_ROLE));
        }
        return kcPeople;
    }

    @Override
    public String getAssociatedDocumentId() {
        return getProposalNumber();
    }
    
    @Override
    public String getNegotiableProposalTypeCode() {
        return getProposalTypeCode();
    }

    @Override
    public ProposalType getNegotiableProposalType() {
        return this.getProposalType();
    }

    @Override
    public String getSubAwardRequisitionerName() {
        return EMPTY_STRING;
    }

    @Override
    public String getSubAwardRequisitionerUnitNumber() {
        return EMPTY_STRING;
    }

    @Override
    public String getSubAwardRequisitionerUnitName() {
        return EMPTY_STRING;
    }    
}
