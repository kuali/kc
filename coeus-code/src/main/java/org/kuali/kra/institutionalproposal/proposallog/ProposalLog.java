/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.rolodex.nonorg.NonOrganizationalRolodex;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.institutionalproposal.proposallog.service.ProposalLogService;
import org.kuali.kra.negotiations.bo.Negotiable;
import org.kuali.kra.negotiations.bo.NegotiationPersonDTO;
import org.kuali.kra.proposaldevelopment.bo.ProposalType;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates data and behavior of a Proposal Log.
 */
public class ProposalLog extends KcPersistableBusinessObjectBase implements Negotiable {
    
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
    private String deadlineTime;
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
    
    private String mergedWith;
    private ProposalLog mergedWithProposal;
    private String instProposalNumber;
    
    private transient KcPersonService kcPersonService;
    

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
    
    public String getDeadlineTime() {
        return deadlineTime;
    }

    public void setDeadlineTime(String deadlineTime) {
        this.deadlineTime = deadlineTime;
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
        return new KcPerson();
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

    @Override 
    protected void prePersist() {
        super.prePersist();
        setSponsorName();
        mergeTemporaryLog();
    }    

    @Override
    protected void preUpdate() {
        super.preUpdate();
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
        if (StringUtils.isNotBlank(getMergedWith()) && StringUtils.equals(getProposalLogTypeCode(), ProposalLogUtils.getProposalLogPermanentTypeCode())) {
            KcServiceLocator.getService(ProposalLogService.class).mergeProposalLog(this, this.getMergedWith());
        }
    }
    
    /* End data persistence methods. */

    /**
     * Gets the KC Person Service.
     * @return KC Person Service.
     */
    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KcServiceLocator.getService(KcPersonService.class);
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

    @Override
    public String getSubAwardRequisitionerId() {
        return EMPTY_STRING;
    }

    public ProposalLog getMergedWithProposal() {
        return mergedWithProposal;
    }

    public void setMergedWithProposal(ProposalLog mergedWithProposal) {
        this.mergedWithProposal = mergedWithProposal;
    }    
}
