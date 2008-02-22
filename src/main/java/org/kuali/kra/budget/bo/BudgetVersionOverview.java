/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.budget.bo;

import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerException;
import org.kuali.core.bo.DocumentHeader;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.util.KualiDecimal;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.infrastructure.KraServiceLocator;

/**
 * Class representation of a Budget Overview Business Object.  This BO maps to
 * the BudgetDocument table but excludes most references.
 * 
 * @author kra-developers-l@indiana.edu
 */
public class BudgetVersionOverview extends KraPersistableBusinessObjectBase implements Comparable<BudgetVersionOverview> {
    
    private String proposalNumber;
    private Integer budgetVersionNumber;
    private String documentNumber;
    private String documentDescription;
    private KualiDecimal costSharingAmount;
    private Date endDate;
    private Date startDate;
    private boolean finalVersionFlag;
    private String ohRateTypeCode;
    private KualiDecimal residualFunds;
    private KualiDecimal totalCost;
    private KualiDecimal totalDirectCost;
    private KualiDecimal totalIndirectCost;
    private KualiDecimal totalCostLimit;
    private KualiDecimal underrecoveryAmount;
    private String comments;
    
    private String name;
    private String budgetStatus;
    
    public Integer getBudgetVersionNumber() {
        return budgetVersionNumber;
    }

    public void setBudgetVersionNumber(Integer budgetVersionNumber) {
        this.budgetVersionNumber = budgetVersionNumber;
    }

    public KualiDecimal getCostSharingAmount() {
        return costSharingAmount;
    }

    public void setCostSharingAmount(KualiDecimal costSharingAmount) {
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

    public KualiDecimal getResidualFunds() {
        return residualFunds;
    }

    public void setResidualFunds(KualiDecimal residualFunds) {
        this.residualFunds = residualFunds;
    }

    public KualiDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(KualiDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public KualiDecimal getTotalDirectCost() {
        return totalDirectCost;
    }

    public void setTotalDirectCost(KualiDecimal totalDirectCost) {
        this.totalDirectCost = totalDirectCost;
    }

    public KualiDecimal getTotalIndirectCost() {
        return totalIndirectCost;
    }

    public void setTotalIndirectCost(KualiDecimal totalIndirectCost) {
        this.totalIndirectCost = totalIndirectCost;
    }

    public KualiDecimal getUnderrecoveryAmount() {
        return underrecoveryAmount;
    }

    public void setUnderrecoveryAmount(KualiDecimal underrecoveryAmount) {
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

    public KualiDecimal getTotalCostLimit() {
        return totalCostLimit;
    }

    public void setTotalCostLimit(KualiDecimal totalCostLimit) {
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
    
    /**
     * @see org.kuali.core.bo.PersistableBusinessObjectBase#afterLookup()
     */
    @Override
    public void afterLookup(PersistenceBroker persistenceBroker) throws PersistenceBrokerException {
        // The purpose of this lookup is to get the document description from the doc header,
        // without mapping the enire doc header (which can be large) in ojb.
        super.afterLookup(persistenceBroker);
        BusinessObjectService boService = KraServiceLocator.getService(BusinessObjectService.class);
        Map<String, Object> keyMap = new HashMap<String, Object>();
        keyMap.put("documentNumber", this.documentNumber);
        DocumentHeader docHeader = (DocumentHeader) boService.findByPrimaryKey(DocumentHeader.class, keyMap);
        this.documentDescription = docHeader.getFinancialDocumentDescription();
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
    
    /**
     * 
     * @see java.lang.Comparable
     */
    public int compareTo(BudgetVersionOverview otherVersion) {
        return this.budgetVersionNumber.compareTo(otherVersion.getBudgetVersionNumber());
    }
    
}
