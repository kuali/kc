/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.bo;

import java.io.Serializable;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.budget.BudgetDecimal;

public class ProposalDevelopmentApproverViewDO implements Serializable  {
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 8148637372623283215L;
    
    String projectTitle;
    String proposalNumber;
    String proposalType;
    BudgetDecimal directCost;
    BudgetDecimal indirectCost;
    BudgetDecimal totalCost;
    Date dueDate;
    Date startDate;
    Date endDate;
    String activityType;
    List<CoPiInfoDO> coPiInfos;
    String piName;
    String leadUnit;
    List<CostShareInfoDO> costShareInfos;
    List<String> otherUnits;
    String sponsorName;
    int proposalPersonIndex;
    
    public String getProjectTitle() {
        return projectTitle;
    }
    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }
    public String getProposalNumber() {
        return proposalNumber;
    }
    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }
    public String getProposalType() {
        return proposalType;
    }
    public void setProposalType(String proposalType) {
        this.proposalType = proposalType;
    }
    public BudgetDecimal getDirectCost() {
        return directCost;
    }
    public void setDirectCost(BudgetDecimal directCost) {
        this.directCost = directCost;
    }
    public BudgetDecimal getIndirectCost() {
        return indirectCost;
    }
    public void setIndirectCost(BudgetDecimal indirectCost) {
        this.indirectCost = indirectCost;
    }
    public BudgetDecimal getTotalCost() {
        return totalCost;
    }
    public void setTotalCost(BudgetDecimal totalCost) {
        this.totalCost = totalCost;
    }
    public Date getDueDate() {
        return dueDate;
    }
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public String getActivityType() {
        return activityType;
    }
    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }
    public List<CostShareInfoDO> getCostShareInfos() {
        return costShareInfos;
    }
    public void setCostShareInfos(List<CostShareInfoDO> costShareInfos) {
        this.costShareInfos = costShareInfos;
    }
    public String getLeadUnit() {
        return leadUnit;
    }
    public void setLeadUnit(String leadUnit) {
        this.leadUnit = leadUnit;
    }
    public List<String> getOtherUnits() {
        return otherUnits;
    }
    public void setOtherUnits(List<String> otherUnits) {
        this.otherUnits = otherUnits;
    }
    public String getPiName() {
        return piName;
    }
    public void setPiName(String piName) {
        this.piName = piName;
    }
    public String getSponsorName() {
        return sponsorName;
    }
    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }
    public int getProposalPersonIndex() {
        return proposalPersonIndex;
    }
    public void setProposalPersonIndex(int proposalPersonIndex) {
        this.proposalPersonIndex = proposalPersonIndex;
    }
    public List<CoPiInfoDO> getCoPiInfos() {
        return coPiInfos;
    }
    public void setCoPiInfos(List<CoPiInfoDO> coPiInfos) {
        this.coPiInfos = coPiInfos;
    }
    
}
