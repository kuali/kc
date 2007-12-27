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
import java.util.LinkedHashMap;

import org.kuali.core.bo.DocumentHeader;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * Class representation of a Budget Overview Business Object.  This BO maps to
 * the BudgetDocument table but excludes most references.
 * 
 * @author kualidev@oncourse.iu.edu
 */
public class BudgetVersionOverview extends KraPersistableBusinessObjectBase {
    
    private Integer proposalNumber;
    private Integer budgetVersionNumber;
    private String documentNumber;
    private String documentDescription;
    private Long costSharingAmount;
    private Date endDate;
    private Date startDate;
    private boolean finalVersionFlag;
    private Integer ohRateTypeCode;
    private Long residualFunds;
    private Long totalCost;
    private Long totalDirectCost;
    private Long totalIndirectCost;
    private Long totalCostLimit;
    private Long underrecoveryAmount;
    private String comments;
    
    private String name;
    private String status;
    
    public Integer getBudgetVersionNumber() {
        return budgetVersionNumber;
    }

    public void setBudgetVersionNumber(Integer budgetVersionNumber) {
        this.budgetVersionNumber = budgetVersionNumber;
    }

    public Long getCostSharingAmount() {
        return costSharingAmount;
    }

    public void setCostSharingAmount(Long costSharingAmount) {
        this.costSharingAmount = costSharingAmount;
    }

    public boolean isFinalVersionFlag() {
        return finalVersionFlag;
    }

    public void setFinalVersionFlag(boolean finalVersionFlag) {
        this.finalVersionFlag = finalVersionFlag;
    }

    public Integer getOhRateTypeCode() {
        return ohRateTypeCode;
    }

    public void setOhRateTypeCode(Integer ohRateTypeCode) {
        this.ohRateTypeCode = ohRateTypeCode;
    }

    public Integer getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(Integer proposalNumber) {
        this.proposalNumber = proposalNumber;
    }
    
    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Long getResidualFunds() {
        return residualFunds;
    }

    public void setResidualFunds(Long residualFunds) {
        this.residualFunds = residualFunds;
    }

    public Long getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Long totalCost) {
        this.totalCost = totalCost;
    }

    public Long getTotalDirectCost() {
        return totalDirectCost;
    }

    public void setTotalDirectCost(Long totalDirectCost) {
        this.totalDirectCost = totalDirectCost;
    }

    public Long getTotalIndirectCost() {
        return totalIndirectCost;
    }

    public void setTotalIndirectCost(Long totalIndirectCost) {
        this.totalIndirectCost = totalIndirectCost;
    }

    public Long getUnderrecoveryAmount() {
        return underrecoveryAmount;
    }

    public void setUnderrecoveryAmount(Long underrecoveryAmount) {
        this.underrecoveryAmount = underrecoveryAmount;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Long getTotalCostLimit() {
        return totalCostLimit;
    }

    public void setTotalCostLimit(Long totalCostLimit) {
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
     * @see org.kuali.core.bo.BusinessObject#toStringMapper()
     */
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap propMap = new LinkedHashMap();
        propMap.put("proposalNumber", this.getProposalNumber());
        propMap.put("budgetVersionNumber", this.getBudgetVersionNumber());
        propMap.put("updateTimestamp", this.getUpdateTimestamp());
        propMap.put("updateUser", this.getUpdateUser());
        return propMap;
    }
}
