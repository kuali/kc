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
package org.kuali.kra.budget.document;

import java.sql.Date;

import org.kuali.core.document.Copyable;
import org.kuali.core.document.SessionDocument;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

public class BudgetDocument extends ResearchDocumentBase implements Copyable, SessionDocument {

    private Integer proposalNumber;
    private Integer budgetVersionNumber;
    private String comments;
    private Long costSharingAmount;
    private Date endDate;
    private boolean finalVersionFlag;
    private String modularBudgetFlag;
    private Integer ohRateClassCode;
    private Integer ohRateTypeCode;
    private Long residualFunds;
    private Date startDate;
    private Long totalCost;
    private Long totalCostLimit;
    private Long totalDirectCost;
    private Long totalIndirectCost;
    private Long underrecoveryAmount;
    private Integer urRateClassCode;
    private ProposalDevelopmentDocument proposal;

    public BudgetDocument(){
        super();
    }

    public void initialize() {
    }

    public Integer getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(Integer proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getCostSharingAmount() {
        return costSharingAmount;
    }

    public void setCostSharingAmount(Long costSharingAmount) {
        this.costSharingAmount = costSharingAmount;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean getFinalVersionFlag() {
        return finalVersionFlag;
    }

    public void setFinalVersionFlag(boolean finalVersionFlag) {
        this.finalVersionFlag = finalVersionFlag;
    }

    public String getModularBudgetFlag() {
        return modularBudgetFlag;
    }

    public void setModularBudgetFlag(String modularBudgetFlag) {
        this.modularBudgetFlag = modularBudgetFlag;
    }

    public Integer getOhRateClassCode() {
        return ohRateClassCode;
    }

    public void setOhRateClassCode(Integer ohRateClassCode) {
        this.ohRateClassCode = ohRateClassCode;
    }

    public Integer getOhRateTypeCode() {
        return ohRateTypeCode;
    }

    public void setOhRateTypeCode(Integer ohRateTypeCode) {
        this.ohRateTypeCode = ohRateTypeCode;
    }

    public Long getResidualFunds() {
        return residualFunds;
    }

    public void setResidualFunds(Long residualFunds) {
        this.residualFunds = residualFunds;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Long getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Long totalCost) {
        this.totalCost = totalCost;
    }

    public Long getTotalCostLimit() {
        return totalCostLimit;
    }

    public void setTotalCostLimit(Long totalCostLimit) {
        this.totalCostLimit = totalCostLimit;
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

    public Integer getUrRateClassCode() {
        return urRateClassCode;
    }

    public void setUrRateClassCode(Integer urRateClassCode) {
        this.urRateClassCode = urRateClassCode;
    }

    public Integer getBudgetVersionNumber() {
        return budgetVersionNumber;
    }

    public void setBudgetVersionNumber(Integer budgetVersionNumber) {
        this.budgetVersionNumber = budgetVersionNumber;
    }

    public ProposalDevelopmentDocument getProposal() {
        return proposal;
    }

    public void setProposal(ProposalDevelopmentDocument proposal) {
        this.proposal = proposal;
    }



}
