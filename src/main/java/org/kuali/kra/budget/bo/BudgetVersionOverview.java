/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.budget.bo;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.persistence.FetchType;
import javax.persistence.Basic;
import javax.persistence.Lob;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.IdClass;

import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerException;
import org.kuali.core.bo.DocumentHeader;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

/**
 * Class representation of a Budget Overview Business Object.  This BO maps to
 * the BudgetDocument table but excludes most references.
 * 
 * @author kra-developers-l@indiana.edu
 */
@IdClass(org.kuali.kra.budget.bo.id.BudgetVersionOverviewId.class)
@Entity
@Table(name="BUDGET")
public class BudgetVersionOverview extends KraPersistableBusinessObjectBase implements Comparable<BudgetVersionOverview> {
    
    @Id
	@Column(name="PROPOSAL_NUMBER")
	private String proposalNumber;
    
    @Id
	@Column(name="VERSION_NUMBER")
	private Integer budgetVersionNumber;
    
    @Column(name="DOCUMENT_NUMBER")
	private String documentNumber;
    
    @Transient
    private String documentDescription;
    
    @Column(name="COST_SHARING_AMOUNT")
	private BudgetDecimal costSharingAmount;
    
    @Column(name="END_DATE")
	private Date endDate;
    
    @Column(name="START_DATE")
	private Date startDate;
    
    @Column(name="FINAL_VERSION_FLAG")
	private boolean finalVersionFlag;
    
    @Column(name="OH_RATE_TYPE_CODE")
	private String ohRateTypeCode;
    
    @Column(name="RESIDUAL_FUNDS")
	private BudgetDecimal residualFunds;
    
    @Column(name="TOTAL_COST")
	private BudgetDecimal totalCost;
    
    @Column(name="TOTAL_DIRECT_COST")
	private BudgetDecimal totalDirectCost;
    
    @Column(name="TOTAL_INDIRECT_COST")
	private BudgetDecimal totalIndirectCost;
    
    @Column(name="TOTAL_COST_LIMIT")
	private BudgetDecimal totalCostLimit;
    
    @Column(name="UNDERRECOVERY_AMOUNT")
	private BudgetDecimal underrecoveryAmount;
    
    @Lob
	@Basic(fetch=FetchType.LAZY)
	@Column(name="COMMENTS")
	private String comments;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST })
    @JoinColumn(name="PROPOSAL_NUMBER", insertable = false, updatable = false)
    private ProposalDevelopmentDocument proposalDevelopmentDocument;
    
    @Transient
    private boolean descriptionUpdatable;
    
    @Transient
    private String name;
    
    @Transient
    private String budgetStatus;
    
    public Integer getBudgetVersionNumber() {
        return budgetVersionNumber;
    }

    public void setBudgetVersionNumber(Integer budgetVersionNumber) {
        this.budgetVersionNumber = budgetVersionNumber;
    }

    public BudgetDecimal getCostSharingAmount() {
        return costSharingAmount;
    }

    public void setCostSharingAmount(BudgetDecimal costSharingAmount) {
        this.costSharingAmount = costSharingAmount;
    }

    public boolean isFinalVersionFlag() {
        return finalVersionFlag;
    }

    public void setFinalVersionFlag(boolean finalVersionFlag) {
        this.finalVersionFlag = finalVersionFlag;
    }

    public String getOhRateTypeCode() {
        return ohRateTypeCode;
    }

    public void setOhRateTypeCode(String ohRateTypeCode) {
        this.ohRateTypeCode = ohRateTypeCode;
    }

    public String getProposalNumber() {
        return proposalNumber;
    }
    
    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }
    
    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public BudgetDecimal getResidualFunds() {
        return residualFunds;
    }

    public void setResidualFunds(BudgetDecimal residualFunds) {
        this.residualFunds = residualFunds;
    }

    public BudgetDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BudgetDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public BudgetDecimal getTotalDirectCost() {
        return totalDirectCost;
    }

    public void setTotalDirectCost(BudgetDecimal totalDirectCost) {
        this.totalDirectCost = totalDirectCost;
    }

    public BudgetDecimal getTotalIndirectCost() {
        return totalIndirectCost;
    }

    public void setTotalIndirectCost(BudgetDecimal totalIndirectCost) {
        this.totalIndirectCost = totalIndirectCost;
    }

    public BudgetDecimal getUnderrecoveryAmount() {
        return underrecoveryAmount;
    }

    public void setUnderrecoveryAmount(BudgetDecimal underrecoveryAmount) {
        this.underrecoveryAmount = underrecoveryAmount;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBudgetStatus() {
        return budgetStatus;
    }

    public void setBudgetStatus(String budgetStatus) {
        this.budgetStatus = budgetStatus;
    }
    
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public BudgetDecimal getTotalCostLimit() {
        return totalCostLimit;
    }

    public void setTotalCostLimit(BudgetDecimal totalCostLimit) {
        this.totalCostLimit = totalCostLimit;
    }
    
    public String getDocumentDescription() {
        return documentDescription;
    }

    public void setDocumentDescription(String documentDescription) {
        this.documentDescription = documentDescription;
    }
    
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    
    public boolean isDescriptionUpdatable() {
        return descriptionUpdatable;
    }

    public void setDescriptionUpdatable(boolean descriptionUpdatable) {
        this.descriptionUpdatable = descriptionUpdatable;
    }

    public ProposalDevelopmentDocument getProposalDevelopmentDocument() {
        return proposalDevelopmentDocument;
    }

    public void setProposalDevelopmentDocument(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this.proposalDevelopmentDocument = proposalDevelopmentDocument;
    }

    /**
     * @see org.kuali.core.bo.PersistableBusinessObjectBase#afterLookup()
     */
    @Override
    public void afterLookup(PersistenceBroker persistenceBroker) throws PersistenceBrokerException {
        // The purpose of this lookup is to get the document description from the doc header,
        // without mapping the enire doc header (which can be large) in ojb.
        super.afterLookup(persistenceBroker);
        DocumentHeader docHeader = getDocHeader();
        if (docHeader != null) {
            this.documentDescription = docHeader.getDocumentDescription();
        }
    }
    
    /**
     * @see org.kuali.core.bo.BusinessObject#toStringMapper()
     */
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap<String, Object> propMap = new LinkedHashMap<String, Object>();
        propMap.put("proposalNumber", this.getProposalNumber());
        propMap.put("budgetVersionNumber", this.getBudgetVersionNumber());
        propMap.put("updateTimestamp", this.getUpdateTimestamp());
        propMap.put("updateUser", this.getUpdateUser());
        return propMap;
    }
    
    protected DocumentHeader getDocHeader() {
        BusinessObjectService boService = KraServiceLocator.getService(BusinessObjectService.class);
        Map<String, Object> keyMap = new HashMap<String, Object>();
        keyMap.put("documentNumber", this.documentNumber);
        DocumentHeader docHeader = (DocumentHeader) boService.findByPrimaryKey(DocumentHeader.class, keyMap);
        return docHeader;
    }
    
    /**
     * 
     * @see java.lang.Comparable
     */
    public int compareTo(BudgetVersionOverview otherVersion) {
        return this.budgetVersionNumber.compareTo(otherVersion.getBudgetVersionNumber());
    }
    
}

