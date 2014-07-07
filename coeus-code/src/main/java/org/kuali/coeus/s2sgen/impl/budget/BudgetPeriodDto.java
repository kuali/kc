/*
 * Copyright 2005-2014 The Kuali Foundation.
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
package org.kuali.coeus.s2sgen.impl.budget;


import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class BudgetPeriodDto {

    private String proposalNumber;
    private int version;
    private String finalVersionFlag;
    private int budgetPeriod;
    private Date startDate;
    private Date endDate;
    private List<KeyPersonDto> keyPersons;
    private List<KeyPersonDto> extraKeyPersons;
    private ScaleTwoDecimal totalFundsKeyPersons;
    private ScaleTwoDecimal totalFundsAttachedKeyPersons;
    private List<OtherPersonnelDto> otherPersonnel;
    private ScaleTwoDecimal totalOtherPersonnelFunds;
    private ScaleTwoDecimal otherPersonnelTotalNumber;
    private ScaleTwoDecimal totalCompensation;
    private List<EquipmentDto> equipment;
    private List<EquipmentDto> extraEquipment;
    private ScaleTwoDecimal totalFundsEquipment;
    private ScaleTwoDecimal totalFundsAttachedEquipment;
    private ScaleTwoDecimal domesticTravelCost;
    private ScaleTwoDecimal foreignTravelCost;
    private ScaleTwoDecimal totalTravelCost;
    private ScaleTwoDecimal partStipendCost;
    private ScaleTwoDecimal partTravelCost;
    private ScaleTwoDecimal partOtherCost;
    private ScaleTwoDecimal partTuition;
    private ScaleTwoDecimal partSubsistence;
    private int participantCount;
    private List<OtherDirectCostInfoDto> otherDirectCosts;
    private ScaleTwoDecimal directCostsTotal;
    private IndirectCostDto indirectCosts;
    private String cognizantFedAgency;
    private ScaleTwoDecimal totalCosts;
    private ScaleTwoDecimal totalIndirectCost;
    private int lineItemCount;

    private ScaleTwoDecimal costSharingAmount;
    private ScaleTwoDecimal domesticTravelCostSharing;
    private ScaleTwoDecimal foreignTravelCostSharing;
    private ScaleTwoDecimal totalTravelCostSharing;
    private ScaleTwoDecimal partStipendCostSharing;
    private ScaleTwoDecimal partTravelCostSharing;
    private ScaleTwoDecimal partTuitionCostSharing;
    private ScaleTwoDecimal partSubsistenceCostSharing;
    private ScaleTwoDecimal partOtherCostSharing;
    private ScaleTwoDecimal totalNonFundsKeyPersons;
    private ScaleTwoDecimal totalNonFundsAttachedKeyPersons;
    private ScaleTwoDecimal totalOtherPersonnelNonFunds;
    private ScaleTwoDecimal totalCompensationCostSharing;
    private ScaleTwoDecimal totalDirectCostSharing;
    private ScaleTwoDecimal totalIndirectCostSharing;

    public BudgetPeriodDto() {
        keyPersons = new ArrayList<KeyPersonDto>();
        extraKeyPersons = new ArrayList<KeyPersonDto>();
        equipment = new ArrayList<EquipmentDto>();
        extraEquipment = new ArrayList<EquipmentDto>();
        otherDirectCosts = new ArrayList<OtherDirectCostInfoDto>();
    }

    /**
     * Getter for proposalNumber.
     * 
     * @return Value of proposalNumber.
     */

    public String getProposalNumber() {
        return proposalNumber;
    }

    /**
     * Setter for proposalNumber
     * 
     * @param proposalNumber New value of proposalNumber.
     */

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    /**
     * Getter for property version.
     * 
     * @return Value of property version.
     */
    public int getVersion() {
        return version;
    }

    /**
     * Setter for property version.
     * 
     * @param version New value of property version.
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * Getter for property finalVersionFlag.
     * 
     * @return Value of property finalVersionFlag.
     */
    public String getFinalVersionFlag() {
        return finalVersionFlag;
    }

    /**
     * Setter for property finalVersionFlag.
     * 
     * @param finalVersionFlag New value of property finalVersionFlag.
     */
    public void setFinalVersionFlag(String finalVersionFlag) {
        this.finalVersionFlag = finalVersionFlag;
    }

    /**
     * Getter for property budgetPeriod.
     * 
     * @return Value of property budgetPeriod.
     */
    public int getBudgetPeriod() {
        return budgetPeriod;
    }

    /**
     * Setter for property budgetPeriod.
     * 
     * @param budgetPeriod New value of property budgetPeriod.
     */
    public void setBudgetPeriod(int budgetPeriod) {
        this.budgetPeriod = budgetPeriod;
    }

    /**
     * Getter for property startDate.
     * 
     * @return Value of property startDate.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Setter for property startDate.
     * 
     * @param startDate New value of property startDate.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Getter for property endDate.
     * 
     * @return Value of property endDate.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Setter for property endDate.
     * 
     * @param endDate New value of property endDate.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    /**
     * Getter for property keyPersons. which is a Vector of keyPersonnel beans
     * 
     * @return Value of property keyPersons
     */
    public List<KeyPersonDto> getKeyPersons() {
        return keyPersons;
    }

    /**
     * Setter for property keyPersons.
     * 
     * @param keyPersons New value of property keyPersons.
     */
    public void setKeyPersons(List<KeyPersonDto> keyPersons) {
        this.keyPersons = keyPersons;
    }


    /**
     * Getter for property extraKeyPersons. which is a Vector of keyPersonnel beans
     * 
     * @return Value of property extraKeyPersons
     */
    public List<KeyPersonDto> getExtraKeyPersons() {
        return extraKeyPersons;
    }

    /**
     * Setter for property extraKeyPersons.
     * 
     * @param extraKeyPersons New value of property extraKeyPersons.
     */
    public void setExtraKeyPersons(List<KeyPersonDto> extraKeyPersons) {
        this.extraKeyPersons = extraKeyPersons;
    }

    /**
     * Getter for property totalFundsKeyPersons.
     * 
     * @return Value of property totalFundsKeyPersons.
     */
    public ScaleTwoDecimal getTotalFundsKeyPersons() {
        return totalFundsKeyPersons;
    }

    /**
     * Setter for property totalFundsKeyPersons.
     * 
     * @param totalFundsKeyPersons New value of property totalFundsKeyPersons.
     */
    public void setTotalFundsKeyPersons(ScaleTwoDecimal totalFundsKeyPersons) {
        this.totalFundsKeyPersons = totalFundsKeyPersons;
    }

    /**
     * Getter for property totalFundsAttachedKeyPersons.
     * 
     * @return Value of property totalFundsAttachedKeyPersons.
     */
    public ScaleTwoDecimal getTotalFundsAttachedKeyPersons() {
        return totalFundsAttachedKeyPersons;
    }

    /**
     * Setter for property totalFundsAttachedKeyPersons.
     * 
     * @param totalFundsAttachedKeyPersons New value of property totalFundsAttachedKeyPersons.
     */
    public void setTotalFundsAttachedKeyPersons(ScaleTwoDecimal totalFundsAttachedKeyPersons) {
        this.totalFundsAttachedKeyPersons = totalFundsAttachedKeyPersons;
    }

    /**
     * Getter for property totalOtherPersonnelFunds.
     * 
     * @return Value of property totalOtherPersonnelFunds.
     */
    public ScaleTwoDecimal getTotalOtherPersonnelFunds() {
        return totalOtherPersonnelFunds;
    }

    /**
     * Setter for property totalOtherPersonnelFunds.
     * 
     * @param totalOtherPersonnelFunds New value of property totalOtherPersonnelFunds.
     */
    public void setTotalOtherPersonnelFunds(ScaleTwoDecimal totalOtherPersonnelFunds) {
        this.totalOtherPersonnelFunds = totalOtherPersonnelFunds;
    }

    /**
     * Getter for property OtherPersonnelTotalNumber.
     * 
     * @return Value of property OtherPersonnelTotalNumber.
     */
    public ScaleTwoDecimal getOtherPersonnelTotalNumber() {
        return otherPersonnelTotalNumber;
    }

    /**
     * Setter for property OtherPersonnelTotalNumber.
     * 
     * @param otherPersonnelTotalNumber New value of property OtherPersonnelTotalNumber.
     */
    public void setOtherPersonnelTotalNumber(ScaleTwoDecimal otherPersonnelTotalNumber) {
        this.otherPersonnelTotalNumber = otherPersonnelTotalNumber;
    }


    /**
     * Getter for property otherPersonnel. which is a Vector of otherPersonnelBeans
     * 
     * @return Value of property otherPersonnel.
     */
    public List<OtherPersonnelDto> getOtherPersonnel() {
        return otherPersonnel;
    }

    /**
     * Setter for property otherPersonnel.
     * 
     * @param otherPersonnel New value of property otherPersonnel.
     */
    public void setOtherPersonnel(List<OtherPersonnelDto> otherPersonnel) {
        this.otherPersonnel = otherPersonnel;
    }

    /**
     * Getter for property totalCompensation.
     * 
     * @return Value of property totalCompensation.
     */
    public ScaleTwoDecimal getTotalCompensation() {
        return totalCompensation;
    }

    /**
     * Setter for property totalCompensation.
     * 
     * @param totalCompensation New value of property totalCompensation.
     */
    public void setTotalCompensation(ScaleTwoDecimal totalCompensation) {
        this.totalCompensation = totalCompensation;
    }

    /**
     * Getter for property equipment. which is a Vector of equipmentBeans
     * 
     * @return Value of property equipment.
     */
    public List<EquipmentDto> getEquipment() {
        return equipment;
    }

    /**
     * Setter for property equipment.
     * 
     * @param equipment New value of property equipment.
     */
    public void setEquipment(List<EquipmentDto> equipment) {
        this.equipment = equipment;
    }

    /**
     * Getter for property extraEquipment. which is a Vector of equipmentBeans
     * 
     * @return Value of property extraEquipment..
     */
    public List<EquipmentDto> getExtraEquipment() {
        return extraEquipment;
    }

    /**
     * Setter for property extraEquipment..
     * 
     * @param extraEquipment New value of property extraEquipment..
     */
    public void setExtraEquipment(List<EquipmentDto> extraEquipment) {
        this.extraEquipment = extraEquipment;
    }


    /**
     * Getter for property domesticTravelCost.
     * 
     * @return Value of property domesticTravelCost.
     */
    public ScaleTwoDecimal getDomesticTravelCost() {
        return domesticTravelCost;
    }

    /**
     * Setter for property domesticTravelCost.
     * 
     * @param domesticTravelCost New value of property domesticTravelCost.
     */
    public void setDomesticTravelCost(ScaleTwoDecimal domesticTravelCost) {
        this.domesticTravelCost = domesticTravelCost;
    }

    /**
     * Getter for property foreignTravelCost.
     * 
     * @return Value of property foreignTravelCost.
     */
    public ScaleTwoDecimal getForeignTravelCost() {
        return foreignTravelCost;
    }

    /**
     * Setter for property foreignTravelCost.
     * 
     * @param foreignTravelCost New value of property foreignTravelCost.
     */
    public void setForeignTravelCost(ScaleTwoDecimal foreignTravelCost) {
        this.foreignTravelCost = foreignTravelCost;
    }

    /**
     * Getter for property totalTravelCost.
     * 
     * @return Value of property totalTravelCost.
     */
    public ScaleTwoDecimal getTotalTravelCost() {
        return totalTravelCost;
    }

    /**
     * Setter for property totalTravelCost.
     * 
     * @param totalTravelCost New value of property totalTravelCost.
     */
    public void setTotalTravelCost(ScaleTwoDecimal totalTravelCost) {
        this.totalTravelCost = totalTravelCost;
    }


    /**
     * Getter for property partOtherCost.
     * 
     * @return Value of property partOtherCost.
     */
    public ScaleTwoDecimal getpartOtherCost() {
        return partOtherCost;
    }

    /**
     * Setter for property partOtherCost.
     * 
     * @param partOtherCost New value of property partOtherCost.
     */
    public void setpartOtherCost(ScaleTwoDecimal partOtherCost) {
        this.partOtherCost = partOtherCost;
    }

    /**
     * Getter for property participantCount.
     * 
     * @return Value of property participantCount.
     */
    public int getparticipantCount() {
        return participantCount;
    }

    /**
     * Setter for property participantCount.
     * 
     * @param participantCount New value of property participantCount.
     */
    public void setparticipantCount(int participantCount) {
        this.participantCount = participantCount;
    }

    /**
     * Getter for property partTravelCost.
     * 
     * @return Value of property partTravelCost.
     */
    public ScaleTwoDecimal getpartTravelCost() {
        return partTravelCost;
    }

    /**
     * Setter for property partTravelCost.
     * 
     * @param partTravelCost New value of property partTravelCost.
     */
    public void setpartTravelCost(ScaleTwoDecimal partTravelCost) {
        this.partTravelCost = partTravelCost;
    }


    /**
     * Getter for property partStipendCost.
     * 
     * @return Value of property partStipendCost.
     */
    public ScaleTwoDecimal getpartStipendCost() {
        return partStipendCost;
    }

    /**
     * Setter for property partStipendCost.
     * 
     * @param partStipendCost New value of property partStipendCost.
     */
    public void setpartStipendCost(ScaleTwoDecimal partStipendCost) {
        this.partStipendCost = partStipendCost;
    }


    /**
     * Getter for property otherDirectCosts. which is a Vector of otherDirectCostBeans
     * 
     * @return Value of property otherDirectCosts.
     */
    public List<OtherDirectCostInfoDto> getOtherDirectCosts() {
        return otherDirectCosts;
    }

    /**
     * Setter for property otherDirectCosts.
     * 
     * @param otherDirectCosts New value of property otherDirectCosts.
     */
    public void setOtherDirectCosts(List<OtherDirectCostInfoDto> otherDirectCosts) {
        this.otherDirectCosts = otherDirectCosts;
    }

    /**
     * Getter for property directCostsTotal.
     * 
     * @return Value of property directCostsTotal.
     */
    public ScaleTwoDecimal getDirectCostsTotal() {
        return directCostsTotal;
    }

    /**
     * Setter for property directCostsTotal.
     * 
     * @param directCostsTotal New value of property directCostsTotal.
     */
    public void setDirectCostsTotal(ScaleTwoDecimal directCostsTotal) {
        this.directCostsTotal = directCostsTotal;
    }

    /**
     * Getter for property indirectCosts. which is an indirectCostsBean
     * 
     * @return Value of property indirectCosts.
     */
    public IndirectCostDto getIndirectCosts() {
        return indirectCosts;
    }

    /**
     * Setter for property indirectCosts.
     * 
     * @param indirectCosts New value of property indirectCosts.
     */
    public void setIndirectCosts(IndirectCostDto indirectCosts) {
        this.indirectCosts = indirectCosts;
    }


    /**
     * Getter for property cognizantFedAgency.
     * 
     * @return Value of property cognizantFedAgency.
     */
    public String getCognizantFedAgency() {
        return cognizantFedAgency;
    }

    /**
     * Setter for property cognizantFedAgency.
     * 
     * @param cognizantFedAgency New value of property cognizantFedAgency.
     */
    public void setCognizantFedAgency(String cognizantFedAgency) {
        this.cognizantFedAgency = cognizantFedAgency;
    }

    /**
     * Getter for property totalCosts.
     * 
     * @return Value of property totalCosts.
     */
    public ScaleTwoDecimal getTotalCosts() {
        return totalCosts;
    }

    /**
     * Setter for property totalCosts.
     * 
     * @param totalCosts New value of property totalCosts.
     */
    public void setTotalCosts(ScaleTwoDecimal totalCosts) {
        this.totalCosts = totalCosts;
    }

    // start add costSaring for fedNonFedBudget repport
    /**
     * Getter for property costSharingAmount.
     * 
     * @return Value of property costSharingAmount.
     */
    public ScaleTwoDecimal getCostSharingAmount() {
        return costSharingAmount==null? ScaleTwoDecimal.ZERO:costSharingAmount;
    }

    /**
     * Setter for property costSharingAmount.
     * 
     * @param costSharingAmount New value of property costSharingAmount.
     */
    public void setCostSharingAmount(ScaleTwoDecimal costSharingAmount) {
        this.costSharingAmount = costSharingAmount;
    }

    public ScaleTwoDecimal getDomesticTravelCostSharing() {
        return domesticTravelCostSharing==null? ScaleTwoDecimal.ZERO:domesticTravelCostSharing;
    }

    public void setDomesticTravelCostSharing(ScaleTwoDecimal domesticTravelCostSharing) {
        this.domesticTravelCostSharing = domesticTravelCostSharing;
    }

    public ScaleTwoDecimal getForeignTravelCostSharing() {
        return foreignTravelCostSharing==null? ScaleTwoDecimal.ZERO:foreignTravelCostSharing;
    }

    public void setForeignTravelCostSharing(ScaleTwoDecimal foreignTravelCostSharing) {
        this.foreignTravelCostSharing = foreignTravelCostSharing;
    }

    public ScaleTwoDecimal getTotalTravelCostSharing() {
        return totalTravelCostSharing==null? ScaleTwoDecimal.ZERO:totalTravelCostSharing;
    }

    public void setTotalTravelCostSharing(ScaleTwoDecimal totalTravelCostSharing) {
        this.totalTravelCostSharing = totalTravelCostSharing;
    }

    public ScaleTwoDecimal getPartStipendCostSharing() {
        return partStipendCostSharing==null? ScaleTwoDecimal.ZERO:partStipendCostSharing;
    }

    public void setPartStipendCostSharing(ScaleTwoDecimal partStipendCostSharing) {
        this.partStipendCostSharing = partStipendCostSharing;
    }

    public ScaleTwoDecimal getPartTravelCostSharing() {
        return partTravelCostSharing==null? ScaleTwoDecimal.ZERO:partTravelCostSharing;
    }

    public void setPartTravelCostSharing(ScaleTwoDecimal partTravelCostSharing) {
        this.partTravelCostSharing = partTravelCostSharing;
    }

    public ScaleTwoDecimal getPartOtherCostSharing() {
        return partOtherCostSharing==null? ScaleTwoDecimal.ZERO:partOtherCostSharing;
    }

    public void setPartOtherCostSharing(ScaleTwoDecimal partOtherCostSharing) {
        this.partOtherCostSharing = partOtherCostSharing;
    }

    public ScaleTwoDecimal getTotalNonFundsKeyPersons() {
        return totalNonFundsKeyPersons;
    }

    public void setTotalNonFundsKeyPersons(ScaleTwoDecimal totalNonFundsKeyPersons) {
        this.totalNonFundsKeyPersons = totalNonFundsKeyPersons;
    }

    public ScaleTwoDecimal getTotalNonFundsAttachedKeyPersons() {
        return totalNonFundsAttachedKeyPersons;
    }

    public void setTotalNonFundsAttachedKeyPersons(ScaleTwoDecimal totalNonFundsAttachedKeyPersons) {
        this.totalNonFundsAttachedKeyPersons = totalNonFundsAttachedKeyPersons;
    }

    public ScaleTwoDecimal getTotalOtherPersonnelNonFunds() {
        return totalOtherPersonnelNonFunds;
    }

    public void setTotalOtherPersonnelNonFunds(ScaleTwoDecimal totalOtherPersonnelNonFunds) {
        this.totalOtherPersonnelNonFunds = totalOtherPersonnelNonFunds;
    }

    public ScaleTwoDecimal getTotalCompensationCostSharing() {
        return totalCompensationCostSharing==null? ScaleTwoDecimal.ZERO:totalCompensationCostSharing;
    }

    public void setTotalCompensationCostSharing(ScaleTwoDecimal totalCompensationCostSharing) {
        this.totalCompensationCostSharing = totalCompensationCostSharing;
    }

    public ScaleTwoDecimal getTotalDirectCostSharing() {
        return totalDirectCostSharing==null? ScaleTwoDecimal.ZERO:totalDirectCostSharing;
    }

    public void setTotalDirectCostSharing(ScaleTwoDecimal totalDirectCostSharing) {
        this.totalDirectCostSharing = totalDirectCostSharing;
    }

    public ScaleTwoDecimal getTotalIndirectCostSharing() {
        return totalIndirectCostSharing==null? ScaleTwoDecimal.ZERO:totalIndirectCostSharing;
    }

    public void setTotalIndirectCostSharing(ScaleTwoDecimal totalIndirectCostSharing) {
        this.totalIndirectCostSharing = totalIndirectCostSharing;
    }


    /**
     * Getter for property totalFundsEquipment.
     * 
     * @return Value of property totalFundsEquipment.
     */
    public ScaleTwoDecimal getTotalFundsEquipment() {
        return totalFundsEquipment;
    }

    /**
     * Setter for property totalFundsEquipment.
     * 
     * @param totalFundsEquipment New value of property totalFundsEquipment.
     */
    public void setTotalFundsEquipment(ScaleTwoDecimal totalFundsEquipment) {
        this.totalFundsEquipment = totalFundsEquipment;
    }

    /**
     * Getter for property totalFundsAttachedEquipment.
     * 
     * @return Value of property totalFundsAttachedEquipment.
     */
    public ScaleTwoDecimal getTotalFundsAttachedEquipment() {
        return totalFundsAttachedEquipment;
    }

    /**
     * Setter for property totalFundsAttachedEquipment.
     * 
     * @param totalFundsAttachedEquipment New value of property totalFundsAttachedEquipment.
     */
    public void setTotalFundsAttachedEquipment(ScaleTwoDecimal totalFundsAttachedEquipment) {
        this.totalFundsAttachedEquipment = totalFundsAttachedEquipment;
    }

    /**
     * Getter for property totalIndirectCost.
     * 
     * @return Value of property totalIndirectCost.
     */
    public ScaleTwoDecimal getTotalIndirectCost() {
        return totalIndirectCost;
    }

    /**
     * Setter for property totalIndirectCost.
     * 
     * @param totalIndirectCost New value of property totalIndirectCost.
     */
    public void setTotalIndirectCost(ScaleTwoDecimal totalIndirectCost) {
        this.totalIndirectCost = totalIndirectCost;
    }

    /**
     * Getter for property partTuition.
     * 
     * @return Value of property partTuition.
     */
    public ScaleTwoDecimal getPartTuition() {
        return partTuition;
    }

    /**
     * Setter for property partTuition.
     * 
     * @param partTuition New value of property partTuition.
     */
    public void setPartTuition(ScaleTwoDecimal partTuition) {
        this.partTuition = partTuition;
    }

    /**
     * Getter for property partSubsistence.
     * 
     * @return Value of property partSubsistence.
     */
    public ScaleTwoDecimal getPartSubsistence() {
        return partSubsistence;
    }

    /**
     * Setter for property partSubsistence.
     * 
     * @param partSubsistence New value of property partSubsistence.
     */
    public void setPartSubsistence(ScaleTwoDecimal partSubsistence) {
        this.partSubsistence = partSubsistence;
    }

    /**
     * Getter for property partSubsistenceCostSharing.
     * 
     * @return Value of property partSubsistenceCostSharing.
     */
    public ScaleTwoDecimal getPartSubsistenceCostSharing() {
        return partSubsistenceCostSharing==null? ScaleTwoDecimal.ZERO:partSubsistenceCostSharing;
    }

    /**
     * Setter for property partSubsistenceCostSharing.
     * 
     * @param partSubsistenceCostSharing New value of property partSubsistenceCostSharing.
     */
    public void setPartSubsistenceCostSharing(ScaleTwoDecimal partSubsistenceCostSharing) {
        this.partSubsistenceCostSharing = partSubsistenceCostSharing;
    }

    /**
     * Getter for property partTuitionCostSharing.
     * 
     * @return Value of property partTuitionCostSharing.
     */
    public ScaleTwoDecimal getPartTuitionCostSharing() {
        return partTuitionCostSharing==null? ScaleTwoDecimal.ZERO:partTuitionCostSharing;
    }

    /**
     * Setter for property partTuitionCostSharing.
     * 
     * @param partTuitionCostSharing New value of property partTuitionCostSharing.
     */
    public void setPartTuitionCostSharing(ScaleTwoDecimal partTuitionCostSharing) {
        this.partTuitionCostSharing = partTuitionCostSharing;
    }

    /**
     * Gets the lineItemCount attribute. 
     * @return Returns the lineItemCount.
     */
    public int getLineItemCount() {
        return lineItemCount;
    }

    /**
     * Sets the lineItemCount attribute value.
     * @param lineItemCount The lineItemCount to set.
     */
    public void setLineItemCount(int lineItemCount) {
        this.lineItemCount = lineItemCount;
    }
}
