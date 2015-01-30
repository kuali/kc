/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.propdev.impl.approve;

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.propdev.impl.person.CoPiInfoDO;
import org.kuali.coeus.propdev.impl.budget.CostShareInfoDO;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class ProposalDevelopmentApproverViewDO implements Serializable  {
    

    private static final long serialVersionUID = 8148637372623283215L;
    
    String projectTitle;
    String proposalNumber;
    String proposalType;
    ScaleTwoDecimal directCost;
    ScaleTwoDecimal indirectCost;
    ScaleTwoDecimal totalCost;
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
    public ScaleTwoDecimal getDirectCost() {
        return directCost;
    }
    public void setDirectCost(ScaleTwoDecimal directCost) {
        this.directCost = directCost;
    }
    public ScaleTwoDecimal getIndirectCost() {
        return indirectCost;
    }
    public void setIndirectCost(ScaleTwoDecimal indirectCost) {
        this.indirectCost = indirectCost;
    }
    public ScaleTwoDecimal getTotalCost() {
        return totalCost;
    }
    public void setTotalCost(ScaleTwoDecimal totalCost) {
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
