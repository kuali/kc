/*
 * Copyright 2008 The Kuali Foundation.
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
package org.kuali.kra.s2s.generator.bo;


import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.budget.BudgetDecimal;

public class BudgetPeriodInfo {

    public static final int BUDGET_PERIOD_1 = 1;
    public static final int BUDGET_PERIOD_2 = 2;
    public static final int BUDGET_PERIOD_3 = 3;
    public static final int BUDGET_PERIOD_4 = 4;
    public static final int BUDGET_PERIOD_5 = 5;

    private String proposalNumber;
    private int version;
    private String finalVersionFlag;
    private int budgetPeriod;
    private Date startDate;
    private Date endDate;
    private List<KeyPersonInfo> keyPersons;
    private List<KeyPersonInfo> extraKeyPersons;
    private BudgetDecimal totalFundsKeyPersons;
    private BudgetDecimal totalFundsAttachedKeyPersons;
    private List<OtherPersonnelInfo> otherPersonnel;
    private BudgetDecimal totalOtherPersonnelFunds;
    private BudgetDecimal otherPersonnelTotalNumber;
    private BudgetDecimal totalCompensation;
    private List<EquipmentInfo> equipment;
    private List<EquipmentInfo> extraEquipment;
    private BudgetDecimal totalFundsEquipment;
    private BudgetDecimal totalFundsAttachedEquipment;
    private BudgetDecimal domesticTravelCost;
    private BudgetDecimal foreignTravelCost;
    private BudgetDecimal totalTravelCost;
    private BudgetDecimal partStipendCost;
    private BudgetDecimal partTravelCost;
    private BudgetDecimal partOtherCost;
    private BudgetDecimal partTuition;
    private BudgetDecimal partSubsistence;
    private int participantCount;
    private List<OtherDirectCostInfo> otherDirectCosts;
    private BudgetDecimal directCostsTotal;
    private IndirectCostInfo indirectCosts;
    private String cognizantFedAgency;
    private BudgetDecimal totalCosts;
    private BudgetDecimal totalIndirectCost;
    private int lineItemCount;

    private BudgetDecimal costSharingAmount;
    private BudgetDecimal domesticTravelCostSharing;
    private BudgetDecimal foreignTravelCostSharing;
    private BudgetDecimal totalTravelCostSharing;
    private BudgetDecimal partStipendCostSharing;
    private BudgetDecimal partTravelCostSharing;
    private BudgetDecimal partTuitionCostSharing;
    private BudgetDecimal partSubsistenceCostSharing;
    private BudgetDecimal partOtherCostSharing;
    private BudgetDecimal totalNonFundsKeyPersons;
    private BudgetDecimal totalNonFundsAttachedKeyPersons;
    private BudgetDecimal totalOtherPersonnelNonFunds;
    private BudgetDecimal totalCompensationCostSharing;
    private BudgetDecimal totalDirectCostSharing;
    private BudgetDecimal totalIndirectCostSharing;


    /**
     * Default Constructor
     */

    public BudgetPeriodInfo() {
        keyPersons = new ArrayList<KeyPersonInfo>();
        extraKeyPersons = new ArrayList<KeyPersonInfo>();
        equipment = new ArrayList<EquipmentInfo>();
        extraEquipment = new ArrayList<EquipmentInfo>();
        otherDirectCosts = new ArrayList<OtherDirectCostInfo>();
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
    public List<KeyPersonInfo> getKeyPersons() {
        return keyPersons;
    }

    /**
     * Setter for property keyPersons.
     * 
     * @param keyPersons New value of property keyPersons.
     */
    public void setKeyPersons(List<KeyPersonInfo> keyPersons) {
        this.keyPersons = keyPersons;
    }


    /**
     * Getter for property extraKeyPersons. which is a Vector of keyPersonnel beans
     * 
     * @return Value of property extraKeyPersons
     */
    public List<KeyPersonInfo> getExtraKeyPersons() {
        return extraKeyPersons;
    }

    /**
     * Setter for property extraKeyPersons.
     * 
     * @param extraKeyPersons New value of property extraKeyPersons.
     */
    public void setExtraKeyPersons(List<KeyPersonInfo> extraKeyPersons) {
        this.extraKeyPersons = extraKeyPersons;
    }

    /**
     * Getter for property totalFundsKeyPersons.
     * 
     * @return Value of property totalFundsKeyPersons.
     */
    public BudgetDecimal getTotalFundsKeyPersons() {
        return totalFundsKeyPersons;
    }

    /**
     * Setter for property totalFundsKeyPersons.
     * 
     * @param totalFundsKeyPersons New value of property totalFundsKeyPersons.
     */
    public void setTotalFundsKeyPersons(BudgetDecimal totalFundsKeyPersons) {
        this.totalFundsKeyPersons = totalFundsKeyPersons;
    }

    /**
     * Getter for property totalFundsAttachedKeyPersons.
     * 
     * @return Value of property totalFundsAttachedKeyPersons.
     */
    public BudgetDecimal getTotalFundsAttachedKeyPersons() {
        return totalFundsAttachedKeyPersons;
    }

    /**
     * Setter for property totalFundsAttachedKeyPersons.
     * 
     * @param totalFundsAttachedKeyPersons New value of property totalFundsAttachedKeyPersons.
     */
    public void setTotalFundsAttachedKeyPersons(BudgetDecimal totalFundsAttachedKeyPersons) {
        this.totalFundsAttachedKeyPersons = totalFundsAttachedKeyPersons;
    }

    /**
     * Getter for property totalOtherPersonnelFunds.
     * 
     * @return Value of property totalOtherPersonnelFunds.
     */
    public BudgetDecimal getTotalOtherPersonnelFunds() {
        return totalOtherPersonnelFunds;
    }

    /**
     * Setter for property totalOtherPersonnelFunds.
     * 
     * @param totalOtherPersonnelFunds New value of property totalOtherPersonnelFunds.
     */
    public void setTotalOtherPersonnelFunds(BudgetDecimal totalOtherPersonnelFunds) {
        this.totalOtherPersonnelFunds = totalOtherPersonnelFunds;
    }

    /**
     * Getter for property OtherPersonnelTotalNumber.
     * 
     * @return Value of property OtherPersonnelTotalNumber.
     */
    public BudgetDecimal getOtherPersonnelTotalNumber() {
        return otherPersonnelTotalNumber;
    }

    /**
     * Setter for property OtherPersonnelTotalNumber.
     * 
     * @param OtherPersonnelTotalNumber New value of property OtherPersonnelTotalNumber.
     */
    public void setOtherPersonnelTotalNumber(BudgetDecimal otherPersonnelTotalNumber) {
        this.otherPersonnelTotalNumber = otherPersonnelTotalNumber;
    }


    /**
     * Getter for property otherPersonnel. which is a Vector of otherPersonnelBeans
     * 
     * @return Value of property otherPersonnel.
     */
    public List<OtherPersonnelInfo> getOtherPersonnel() {
        return otherPersonnel;
    }

    /**
     * Setter for property otherPersonnel.
     * 
     * @param otherPersonnel New value of property otherPersonnel.
     */
    public void setOtherPersonnel(List<OtherPersonnelInfo> otherPersonnel) {
        this.otherPersonnel = otherPersonnel;
    }

    /**
     * Getter for property totalCompensation.
     * 
     * @return Value of property totalCompensation.
     */
    public BudgetDecimal getTotalCompensation() {
        return totalCompensation;
    }

    /**
     * Setter for property totalCompensation.
     * 
     * @param totalCompensation New value of property totalCompensation.
     */
    public void setTotalCompensation(BudgetDecimal totalCompensation) {
        this.totalCompensation = totalCompensation;
    }

    /**
     * Getter for property equipment. which is a Vector of equipmentBeans
     * 
     * @return Value of property equipment.
     */
    public List<EquipmentInfo> getEquipment() {
        return equipment;
    }

    /**
     * Setter for property equipment.
     * 
     * @param equipment New value of property equipment.
     */
    public void setEquipment(List<EquipmentInfo> equipment) {
        this.equipment = equipment;
    }

    /**
     * Getter for property extraEquipment. which is a Vector of equipmentBeans
     * 
     * @return Value of property extraEquipment..
     */
    public List<EquipmentInfo> getExtraEquipment() {
        return extraEquipment;
    }

    /**
     * Setter for property extraEquipment..
     * 
     * @param extraEquipment. New value of property extraEquipment..
     */
    public void setExtraEquipment(List<EquipmentInfo> extraEquipment) {
        this.extraEquipment = extraEquipment;
    }


    /**
     * Getter for property domesticTravelCost.
     * 
     * @return Value of property domesticTravelCost.
     */
    public BudgetDecimal getDomesticTravelCost() {
        return domesticTravelCost;
    }

    /**
     * Setter for property domesticTravelCost.
     * 
     * @param domesticTravelCost New value of property domesticTravelCost.
     */
    public void setDomesticTravelCost(BudgetDecimal domesticTravelCost) {
        this.domesticTravelCost = domesticTravelCost;
    }

    /**
     * Getter for property foreignTravelCost.
     * 
     * @return Value of property foreignTravelCost.
     */
    public BudgetDecimal getForeignTravelCost() {
        return foreignTravelCost;
    }

    /**
     * Setter for property foreignTravelCost.
     * 
     * @param foreignTravelCost New value of property foreignTravelCost.
     */
    public void setForeignTravelCost(BudgetDecimal foreignTravelCost) {
        this.foreignTravelCost = foreignTravelCost;
    }

    /**
     * Getter for property totalTravelCost.
     * 
     * @return Value of property totalTravelCost.
     */
    public BudgetDecimal getTotalTravelCost() {
        return totalTravelCost;
    }

    /**
     * Setter for property totalTravelCost.
     * 
     * @param totalTravelCost New value of property totalTravelCost.
     */
    public void setTotalTravelCost(BudgetDecimal totalTravelCost) {
        this.totalTravelCost = totalTravelCost;
    }


    /**
     * Getter for property partOtherCost.
     * 
     * @return Value of property partOtherCost.
     */
    public BudgetDecimal getpartOtherCost() {
        return partOtherCost;
    }

    /**
     * Setter for property partOtherCost.
     * 
     * @param partOtherCost New value of property partOtherCost.
     */
    public void setpartOtherCost(BudgetDecimal partOtherCost) {
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
    public BudgetDecimal getpartTravelCost() {
        return partTravelCost;
    }

    /**
     * Setter for property partTravelCost.
     * 
     * @param partTravelCost New value of property partTravelCost.
     */
    public void setpartTravelCost(BudgetDecimal partTravelCost) {
        this.partTravelCost = partTravelCost;
    }


    /**
     * Getter for property partStipendCost.
     * 
     * @return Value of property partStipendCost.
     */
    public BudgetDecimal getpartStipendCost() {
        return partStipendCost;
    }

    /**
     * Setter for property partStipendCost.
     * 
     * @param partStipendCost New value of property partStipendCost.
     */
    public void setpartStipendCost(BudgetDecimal partStipendCost) {
        this.partStipendCost = partStipendCost;
    }


    /**
     * Getter for property otherDirectCosts. which is a Vector of otherDirectCostBeans
     * 
     * @return Value of property otherDirectCosts.
     */
    public List<OtherDirectCostInfo> getOtherDirectCosts() {
        return otherDirectCosts;
    }

    /**
     * Setter for property otherDirectCosts.
     * 
     * @param otherDirectCosts New value of property otherDirectCosts.
     */
    public void setOtherDirectCosts(List<OtherDirectCostInfo> otherDirectCosts) {
        this.otherDirectCosts = otherDirectCosts;
    }

    /**
     * Getter for property directCostsTotal.
     * 
     * @return Value of property directCostsTotal.
     */
    public BudgetDecimal getDirectCostsTotal() {
        return directCostsTotal;
    }

    /**
     * Setter for property directCostsTotal.
     * 
     * @param directCostsTotal New value of property directCostsTotal.
     */
    public void setDirectCostsTotal(BudgetDecimal directCostsTotal) {
        this.directCostsTotal = directCostsTotal;
    }

    /**
     * Getter for property indirectCosts. which is an indirectCostsBean
     * 
     * @return Value of property indirectCosts.
     */
    public IndirectCostInfo getIndirectCosts() {
        return indirectCosts;
    }

    /**
     * Setter for property indirectCosts.
     * 
     * @param indirectCosts New value of property indirectCosts.
     */
    public void setIndirectCosts(IndirectCostInfo indirectCosts) {
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
    public BudgetDecimal getTotalCosts() {
        return totalCosts;
    }

    /**
     * Setter for property totalCosts.
     * 
     * @param totalCosts New value of property totalCosts.
     */
    public void setTotalCosts(BudgetDecimal totalCosts) {
        this.totalCosts = totalCosts;
    }

    // start add costSaring for fedNonFedBudget repport
    /**
     * Getter for property costSharingAmount.
     * 
     * @return Value of property costSharingAmount.
     */
    public BudgetDecimal getCostSharingAmount() {
        return costSharingAmount;
    }

    /**
     * Setter for property costSharingAmount.
     * 
     * @param costSharingAmount New value of property costSharingAmount.
     */
    public void setCostSharingAmount(BudgetDecimal costSharingAmount) {
        this.costSharingAmount = costSharingAmount;
    }

    public BudgetDecimal getDomesticTravelCostSharing() {
        return domesticTravelCostSharing;
    }

    public void setDomesticTravelCostSharing(BudgetDecimal domesticTravelCostSharing) {
        this.domesticTravelCostSharing = domesticTravelCostSharing;
    }

    public BudgetDecimal getForeignTravelCostSharing() {
        return foreignTravelCostSharing;
    }

    public void setForeignTravelCostSharing(BudgetDecimal foreignTravelCostSharing) {
        this.foreignTravelCostSharing = foreignTravelCostSharing;
    }

    public BudgetDecimal getTotalTravelCostSharing() {
        return totalTravelCostSharing;
    }

    public void setTotalTravelCostSharing(BudgetDecimal totalTravelCostSharing) {
        this.totalTravelCostSharing = totalTravelCostSharing;
    }

    public BudgetDecimal getPartStipendCostSharing() {
        return partStipendCostSharing;
    }

    public void setPartStipendCostSharing(BudgetDecimal partStipendCostSharing) {
        this.partStipendCostSharing = partStipendCostSharing;
    }

    public BudgetDecimal getPartTravelCostSharing() {
        return partTravelCostSharing;
    }

    public void setPartTravelCostSharing(BudgetDecimal partTravelCostSharing) {
        this.partTravelCostSharing = partTravelCostSharing;
    }

    public BudgetDecimal getPartOtherCostSharing() {
        return partOtherCostSharing;
    }

    public void setPartOtherCostSharing(BudgetDecimal partOtherCostSharing) {
        this.partOtherCostSharing = partOtherCostSharing;
    }

    public BudgetDecimal getTotalNonFundsKeyPersons() {
        return totalNonFundsKeyPersons;
    }

    public void setTotalNonFundsKeyPersons(BudgetDecimal totalNonFundsKeyPersons) {
        this.totalNonFundsKeyPersons = totalNonFundsKeyPersons;
    }

    public BudgetDecimal getTotalNonFundsAttachedKeyPersons() {
        return totalNonFundsAttachedKeyPersons;
    }

    public void setTotalNonFundsAttachedKeyPersons(BudgetDecimal totalNonFundsAttachedKeyPersons) {
        this.totalNonFundsAttachedKeyPersons = totalNonFundsAttachedKeyPersons;
    }

    public BudgetDecimal getTotalOtherPersonnelNonFunds() {
        return totalOtherPersonnelNonFunds;
    }

    public void setTotalOtherPersonnelNonFunds(BudgetDecimal totalOtherPersonnelNonFunds) {
        this.totalOtherPersonnelNonFunds = totalOtherPersonnelNonFunds;
    }

    public BudgetDecimal getTotalCompensationCostSharing() {
        return totalCompensationCostSharing;
    }

    public void setTotalCompensationCostSharing(BudgetDecimal totalCompensationCostSharing) {
        this.totalCompensationCostSharing = totalCompensationCostSharing;
    }

    public BudgetDecimal getTotalDirectCostSharing() {
        return totalDirectCostSharing;
    }

    public void setTotalDirectCostSharing(BudgetDecimal totalDirectCostSharing) {
        this.totalDirectCostSharing = totalDirectCostSharing;
    }

    public BudgetDecimal getTotalIndirectCostSharing() {
        return totalIndirectCostSharing;
    }

    public void setTotalIndirectCostSharing(BudgetDecimal totalIndirectCostSharing) {
        this.totalIndirectCostSharing = totalIndirectCostSharing;
    }


    /**
     * Getter for property totalFundsEquipment.
     * 
     * @return Value of property totalFundsEquipment.
     */
    public BudgetDecimal getTotalFundsEquipment() {
        return totalFundsEquipment;
    }

    /**
     * Setter for property totalFundsEquipment.
     * 
     * @param totalFundsEquipment New value of property totalFundsEquipment.
     */
    public void setTotalFundsEquipment(BudgetDecimal totalFundsEquipment) {
        this.totalFundsEquipment = totalFundsEquipment;
    }

    /**
     * Getter for property totalFundsAttachedEquipment.
     * 
     * @return Value of property totalFundsAttachedEquipment.
     */
    public BudgetDecimal getTotalFundsAttachedEquipment() {
        return totalFundsAttachedEquipment;
    }

    /**
     * Setter for property totalFundsAttachedEquipment.
     * 
     * @param totalFundsAttachedEquipment New value of property totalFundsAttachedEquipment.
     */
    public void setTotalFundsAttachedEquipment(BudgetDecimal totalFundsAttachedEquipment) {
        this.totalFundsAttachedEquipment = totalFundsAttachedEquipment;
    }

    /**
     * Getter for property totalIndirectCost.
     * 
     * @return Value of property totalIndirectCost.
     */
    public BudgetDecimal getTotalIndirectCost() {
        return totalIndirectCost;
    }

    /**
     * Setter for property totalIndirectCost.
     * 
     * @param totalIndirectCost New value of property totalIndirectCost.
     */
    public void setTotalIndirectCost(BudgetDecimal totalIndirectCost) {
        this.totalIndirectCost = totalIndirectCost;
    }

    /**
     * Getter for property partTuition.
     * 
     * @return Value of property partTuition.
     */
    public BudgetDecimal getPartTuition() {
        return partTuition;
    }

    /**
     * Setter for property partTuition.
     * 
     * @param partTuition New value of property partTuition.
     */
    public void setPartTuition(BudgetDecimal partTuition) {
        this.partTuition = partTuition;
    }

    /**
     * Getter for property partSubsistence.
     * 
     * @return Value of property partSubsistence.
     */
    public BudgetDecimal getPartSubsistence() {
        return partSubsistence;
    }

    /**
     * Setter for property partSubsistence.
     * 
     * @param partSubsistence New value of property partSubsistence.
     */
    public void setPartSubsistence(BudgetDecimal partSubsistence) {
        this.partSubsistence = partSubsistence;
    }

    /**
     * Getter for property partSubsistenceCostSharing.
     * 
     * @return Value of property partSubsistenceCostSharing.
     */
    public BudgetDecimal getPartSubsistenceCostSharing() {
        return partSubsistenceCostSharing;
    }

    /**
     * Setter for property partSubsistenceCostSharing.
     * 
     * @param partSubsistenceCostSharing New value of property partSubsistenceCostSharing.
     */
    public void setPartSubsistenceCostSharing(BudgetDecimal partSubsistenceCostSharing) {
        this.partSubsistenceCostSharing = partSubsistenceCostSharing;
    }

    /**
     * Getter for property partTuitionCostSharing.
     * 
     * @return Value of property partTuitionCostSharing.
     */
    public BudgetDecimal getPartTuitionCostSharing() {
        return partTuitionCostSharing;
    }

    /**
     * Setter for property partTuitionCostSharing.
     * 
     * @param partTuitionCostSharing New value of property partTuitionCostSharing.
     */
    public void setPartTuitionCostSharing(BudgetDecimal partTuitionCostSharing) {
        this.partTuitionCostSharing = partTuitionCostSharing;
    }

    
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = new LinkedHashMap();
        hashMap.put("proposalNumber", getProposalNumber());
        hashMap.put("totalIndirectCostSharing", getTotalIndirectCostSharing());
        hashMap.put("version", getTotalIndirectCostSharing());
        hashMap.put("finalVersionFlag", getFinalVersionFlag());
        hashMap.put("budgetPeriod", getBudgetPeriod());
        hashMap.put("startDate", getStartDate());
        hashMap.put("endDate", getEndDate());
        hashMap.put("totalFundsKeyPersons", getTotalFundsKeyPersons());
        hashMap.put("totalFundsAttachedKeyPersons", getTotalFundsAttachedKeyPersons());
        hashMap.put("totalOtherPersonnelFunds", getTotalOtherPersonnelFunds());
        hashMap.put("otherPersonnelTotalNumber", getOtherPersonnelTotalNumber());
        hashMap.put("totalCompensation", getTotalCompensation());
        hashMap.put("totalFundsEquipment", getTotalFundsEquipment());
        hashMap.put("totalFundsAttachedEquipment", getTotalFundsAttachedEquipment());
        hashMap.put("domesticTravelCost", getDomesticTravelCost());
        hashMap.put("foreignTravelCost", getForeignTravelCost());
        hashMap.put("totalTravelCost", getTotalTravelCost());
        hashMap.put("partStipendCost", getpartStipendCost());
        hashMap.put("partTravelCost", getpartTravelCost());
        hashMap.put("partOtherCost", getpartOtherCost());
        hashMap.put("partTuition", getPartTuition());
        hashMap.put("partSubsistence", getPartSubsistence());
        hashMap.put("participantCount", getparticipantCount());
        hashMap.put("directCostsTotal", getDirectCostsTotal());
        hashMap.put("indirectCosts", getIndirectCosts());
        hashMap.put("cognizantFedAgency", getCognizantFedAgency());
        hashMap.put("totalCosts", getTotalCosts());
        hashMap.put("totalIndirectCost", getTotalIndirectCost());
        hashMap.put("costSharingAmount", getCostSharingAmount());
        hashMap.put("domesticTravelCostSharing", getDomesticTravelCostSharing());
        hashMap.put("foreignTravelCostSharing", getForeignTravelCostSharing());
        hashMap.put("totalTravelCostSharing", getTotalTravelCostSharing());
        hashMap.put("partStipendCostSharing", getPartStipendCostSharing());
        hashMap.put("partTravelCostSharing", getPartTravelCostSharing());
        hashMap.put("partTuitionCostSharing", getPartTuitionCostSharing());
        hashMap.put("partSubsistenceCostSharing", getPartSubsistenceCostSharing());
        hashMap.put("partOtherCostSharing", getPartOtherCostSharing());
        hashMap.put("totalNonFundsKeyPersons", getTotalNonFundsKeyPersons());
        hashMap.put("totalNonFundsAttachedKeyPersons", getTotalNonFundsAttachedKeyPersons());
        hashMap.put("totalOtherPersonnelNonFunds", getTotalOtherPersonnelNonFunds());
        hashMap.put("totalCompensationCostSharing", getTotalCompensationCostSharing());
        hashMap.put("totalDirectCostSharing", getTotalDirectCostSharing());
        hashMap.put("totalIndirectCostSharing", getTotalIndirectCostSharing());


        return hashMap;
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