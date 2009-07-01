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

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.budget.BudgetDecimal;

public class BudgetSummaryInfo {


    private String proposalNumber;
    private int version;
    private List<BudgetPeriodInfo> budgetPeriods;
    private String finalVersionFlag;
    private BudgetDecimal cumTotalFundsForSrPersonnel = BudgetDecimal.ZERO;
    private BudgetDecimal cumTotalFundsForOtherPersonnel = BudgetDecimal.ZERO;
    private BudgetDecimal cumTotalFundsForPersonnel = BudgetDecimal.ZERO;
    private BudgetDecimal cumNumOtherPersonnel = BudgetDecimal.ZERO;
    private BudgetDecimal cumEquipmentFunds = BudgetDecimal.ZERO;
    private BudgetDecimal cumTravel = BudgetDecimal.ZERO;
    private BudgetDecimal cumDomesticTravel = BudgetDecimal.ZERO;
    private BudgetDecimal cumForeignTravel = BudgetDecimal.ZERO;

    private List<OtherDirectCostInfo> otherDirect=new ArrayList<OtherDirectCostInfo>();

    private BudgetDecimal partStipendCost = BudgetDecimal.ZERO;
    private BudgetDecimal partTravelCost = BudgetDecimal.ZERO;
    private BudgetDecimal partOtherCost = BudgetDecimal.ZERO;
    private BudgetDecimal partSubsistence = BudgetDecimal.ZERO;
    private BudgetDecimal partTuition = BudgetDecimal.ZERO;
    private int participantCount;

    private BudgetDecimal cumTotalDirectCosts = BudgetDecimal.ZERO;
    private BudgetDecimal cumTotalIndirectCosts = BudgetDecimal.ZERO;
    private BudgetDecimal cumTotalCosts = BudgetDecimal.ZERO;
    private BudgetDecimal cumFee = BudgetDecimal.ZERO;


    private BudgetDecimal cumTotalCostSharing = BudgetDecimal.ZERO;
    private BudgetDecimal cumTotalNonFundsForSrPersonnel = BudgetDecimal.ZERO;
    private BudgetDecimal cumTotalNonFundsForOtherPersonnel = BudgetDecimal.ZERO;
    private BudgetDecimal cumTotalNonFundsForPersonnel = BudgetDecimal.ZERO;
    private BudgetDecimal cumTravelNonFund = BudgetDecimal.ZERO;
    private BudgetDecimal cumDomesticTravelNonFund = BudgetDecimal.ZERO;
    private BudgetDecimal cumForeignTravelNonFund = BudgetDecimal.ZERO;
    private BudgetDecimal partStipendCostSharing = BudgetDecimal.ZERO;
    private BudgetDecimal partTravelCostSharing = BudgetDecimal.ZERO;
    private BudgetDecimal partSubsistenceCostSharing = BudgetDecimal.ZERO;
    private BudgetDecimal partTuitionCostSharing = BudgetDecimal.ZERO;
    private BudgetDecimal partOtherCostSharing = BudgetDecimal.ZERO;
    private BudgetDecimal cumEquipmentNonFunds = BudgetDecimal.ZERO;
    private BudgetDecimal cumTotalDirectCostSharing = BudgetDecimal.ZERO;
    private BudgetDecimal cumTotalIndirectCostSharing = BudgetDecimal.ZERO;


    /**
     * Default Constructor
     */

    public BudgetSummaryInfo() {
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
     * Getter for property budgetPeriods.
     * 
     * @return Value of property budgetPeriods.
     */
    public List<BudgetPeriodInfo> getBudgetPeriods() {
        return budgetPeriods;
    }

    /**
     * Setter for property budgetPeriods.
     * 
     * @param budgetPeriods New value of property budgetPeriods.
     */
    public void setBudgetPeriods(List<BudgetPeriodInfo> budgetPeriods) {
        this.budgetPeriods = budgetPeriods;
    }


    /**
     * Getter for property cumTotalFundsForSrPersonnel.
     * 
     * @return Value of property cumTotalFundsForSrPersonnel.
     */
    public BudgetDecimal getCumTotalFundsForSrPersonnel() {
        return cumTotalFundsForSrPersonnel;
    }

    /**
     * Setter for property cumTotalFundsForSrPersonnel.
     * 
     * @param cumTotalFundsForSrPersonnel New value of property cumTotalFundsForSrPersonnel.
     */
    public void setCumTotalFundsForSrPersonnel(BudgetDecimal cumTotalFundsForSrPersonnel) {
        this.cumTotalFundsForSrPersonnel = cumTotalFundsForSrPersonnel;
    }


    /**
     * Getter for property cumTotalFundsForOtherPersonnel.
     * 
     * @return Value of property cumTotalFundsForOtherPersonnel.
     */
    public BudgetDecimal getCumTotalFundsForOtherPersonnel() {
        return cumTotalFundsForOtherPersonnel;
    }

    /**
     * Setter for property cumTotalFundsForOtherPersonnel.
     * 
     * @param cumTotalFundsForOtherPersonnel New value of property cumTotalFundsForOtherPersonnel.
     */
    public void setCumTotalFundsForOtherPersonnel(BudgetDecimal cumTotalFundsForOtherPersonnel) {
        this.cumTotalFundsForOtherPersonnel = cumTotalFundsForOtherPersonnel;
    }


    /**
     * Getter for property cumTotalFundsForPersonnel.
     * 
     * @return Value of property cumTotalFundsForPersonnel.
     */
    public BudgetDecimal getCumTotalFundsForPersonnel() {
        return cumTotalFundsForPersonnel;
    }

    /**
     * Setter for property cumTotalFundsForPersonnel.
     * 
     * @param cumTotalFundsForPersonnel New value of property cumTotalFundsForPersonnel.
     */
    public void setCumTotalFundsForPersonnel(BudgetDecimal cumTotalFundsForPersonnel) {
        this.cumTotalFundsForPersonnel = cumTotalFundsForPersonnel;
    }


    /**
     * Getter for property cumNumOtherPersonnel.
     * 
     * @return Value of property cumNumOtherPersonnel.
     */
    public BudgetDecimal getCumNumOtherPersonnel() {
        return cumNumOtherPersonnel;
    }

    /**
     * Setter for property cumNumOtherPersonnel.
     * 
     * @param cumNumOtherPersonnel New value of property cumNumOtherPersonnel.
     */
    public void setCumNumOtherPersonnel(BudgetDecimal cumNumOtherPersonnel) {
        this.cumNumOtherPersonnel = cumNumOtherPersonnel;
    }


    /**
     * Getter for property cumEquipmentFunds.
     * 
     * @return Value of property cumEquipmentFunds.
     */
    public BudgetDecimal getCumEquipmentFunds() {
        return cumEquipmentFunds;
    }

    /**
     * Setter for property cumEquipmentFunds.
     * 
     * @param cumEquipmentFunds New value of property cumEquipmentFunds.
     */
    public void setCumEquipmentFunds(BudgetDecimal cumEquipmentFunds) {
        this.cumEquipmentFunds = cumEquipmentFunds;
    }


    /**
     * Getter for property cumTravel.
     * 
     * @return Value of property cumTravel.
     */
    public BudgetDecimal getCumTravel() {
        return cumTravel;
    }

    /**
     * Setter for property cumTravel.
     * 
     * @param cumTravel New value of property cumTravel.
     */
    public void setCumTravel(BudgetDecimal cumTravel) {
        this.cumTravel = cumTravel;
    }

    /**
     * Getter for property cumDomesticTravel.
     * 
     * @return Value of property cumDomesticTravel.
     */
    public BudgetDecimal getCumDomesticTravel() {
        return cumDomesticTravel;
    }

    /**
     * Setter for property cumDomesticTravel.
     * 
     * @param cumDomesticTravel New value of property cumDomesticTravel.
     */
    public void setCumDomesticTravel(BudgetDecimal cumDomesticTravel) {
        this.cumDomesticTravel = cumDomesticTravel;
    }

    /**
     * Getter for property cumForeignTravel.
     * 
     * @return Value of property cumForeignTravel.
     */
    public BudgetDecimal getCumForeignTravel() {
        return cumForeignTravel;
    }

    /**
     * Setter for property cumForeignTravel.
     * 
     * @param cumForeignTravel New value of property cumForeignTravel.
     */
    public void setCumForeignTravel(BudgetDecimal cumForeignTravel) {
        this.cumForeignTravel = cumForeignTravel;
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
     * Getter for property otherDirect. which is a Vector of otherDirectCostBeans
     * 
     * @return Value of property otherDirect.
     */
    public List<OtherDirectCostInfo> getOtherDirectCosts() {
        return otherDirect;
    }

    /**
     * Setter for property otherDirect.
     * 
     * @param otherDirect New value of property otherDirect.
     */
    public void setOtherDirectCosts(List<OtherDirectCostInfo> otherDirect) {
        this.otherDirect = otherDirect;
    }


    /**
     * Getter for property cumTotalDirectCosts.
     * 
     * @return Value of property cumTotalDirectCosts.
     */
    public BudgetDecimal getCumTotalDirectCosts() {
        return cumTotalDirectCosts;
    }

    /**
     * Setter for property cumTotalDirectCosts.
     * 
     * @param cumTotalDirectCosts New value of property cumTotalDirectCosts.
     */
    public void setCumTotalDirectCosts(BudgetDecimal cumTotalDirectCosts) {
        this.cumTotalDirectCosts = cumTotalDirectCosts;
    }


    /**
     * Getter for property cumTotalIndirectCosts.
     * 
     * @return Value of property cumTotalIndirectCosts.
     */
    public BudgetDecimal getCumTotalIndirectCosts() {
        return cumTotalIndirectCosts;
    }

    /**
     * Setter for property cumTotalIndirectCosts.
     * 
     * @param cumTotalIndirectCosts New value of property cumTotalIndirectCosts.
     */
    public void setCumTotalIndirectCosts(BudgetDecimal cumTotalIndirectCosts) {
        this.cumTotalIndirectCosts = cumTotalIndirectCosts;
    }


    /**
     * Getter for property cumTotalCosts.
     * 
     * @return Value of property cumTotalCosts.
     */
    public BudgetDecimal getCumTotalCosts() {
        return cumTotalCosts;
    }

    /**
     * Setter for property cumTotalCosts.
     * 
     * @param cumTotalCosts New value of property cumTotalCosts.
     */
    public void setCumTotalCosts(BudgetDecimal cumTotalCosts) {
        this.cumTotalCosts = cumTotalCosts;
    }


    /**
     * Getter for property cumFee.
     * 
     * @return Value of property cumFee.
     */
    public BudgetDecimal getCumFee() {
        return cumFee;
    }

    /**
     * Setter for property cumFee.
     * 
     * @param cumFee New value of property cumFee.
     */
    public void setCumFee(BudgetDecimal cumFee) {
        this.cumFee = cumFee;
    }


    public BudgetDecimal getCumTotalCostSharing() {
        return cumTotalCostSharing;
    }

    public void setCumTotalCostSharing(BudgetDecimal cumTotalCostSharing) {
        this.cumTotalCostSharing = cumTotalCostSharing;
    }

    public BudgetDecimal getCumTotalNonFundsForSrPersonnel() {
        return cumTotalNonFundsForSrPersonnel;
    }

    public void setCumTotalNonFundsForSrPersonnel(BudgetDecimal cumTotalNonFundsForSrPersonnel) {
        this.cumTotalNonFundsForSrPersonnel = cumTotalNonFundsForSrPersonnel;
    }

    public BudgetDecimal getCumTotalNonFundsForOtherPersonnel() {
        return cumTotalNonFundsForOtherPersonnel;
    }

    public void setCumTotalNonFundsForOtherPersonnel(BudgetDecimal cumTotalNonFundsForOtherPersonnel) {
        this.cumTotalNonFundsForOtherPersonnel = cumTotalNonFundsForOtherPersonnel;
    }

    public BudgetDecimal getCumTotalNonFundsForPersonnel() {
        return cumTotalNonFundsForPersonnel;
    }

    public void setCumTotalNonFundsForPersonnel(BudgetDecimal cumTotalNonFundsForPersonnel) {
        this.cumTotalNonFundsForPersonnel = cumTotalNonFundsForPersonnel;
    }

    public BudgetDecimal getCumTravelNonFund() {
        return cumTravelNonFund;
    }

    public void setCumTravelNonFund(BudgetDecimal cumTravelNonFund) {
        this.cumTravelNonFund = cumTravelNonFund;
    }

    public BudgetDecimal getCumDomesticTravelNonFund() {
        return cumDomesticTravelNonFund;
    }

    public void setCumDomesticTravelNonFund(BudgetDecimal cumDomesticTravelNonFund) {
        this.cumDomesticTravelNonFund = cumDomesticTravelNonFund;
    }

    public BudgetDecimal getCumForeignTravelNonFund() {
        return cumForeignTravelNonFund;
    }

    public void setCumForeignTravelNonFund(BudgetDecimal cumForeignTravelNonFund) {
        this.cumForeignTravelNonFund = cumForeignTravelNonFund;
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

    public BudgetDecimal getCumEquipmentNonFunds() {
        return cumEquipmentNonFunds;
    }

    public void setCumEquipmentNonFunds(BudgetDecimal cumEquipmentNonFunds) {
        this.cumEquipmentNonFunds = cumEquipmentNonFunds;
    }

    public BudgetDecimal getCumTotalDirectCostSharing() {
        return cumTotalDirectCostSharing;
    }

    public void setCumTotalDirectCostSharing(BudgetDecimal cumTotalDirectCostSharing) {
        this.cumTotalDirectCostSharing = cumTotalDirectCostSharing;
    }

    public BudgetDecimal getCumTotalIndirectCostSharing() {
        return cumTotalIndirectCostSharing;
    }

    public void setCumTotalIndirectCostSharing(BudgetDecimal cumTotalIndirectCostSharing) {
        this.cumTotalIndirectCostSharing = cumTotalIndirectCostSharing;
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

}
