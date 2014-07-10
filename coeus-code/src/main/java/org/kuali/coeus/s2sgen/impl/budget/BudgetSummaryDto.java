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

import java.util.ArrayList;
import java.util.List;

public class BudgetSummaryDto {


    private String proposalNumber;
    private int version;
    private List<BudgetPeriodDto> budgetPeriods;
    private String finalVersionFlag;
    private ScaleTwoDecimal cumTotalFundsForSrPersonnel = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal cumTotalFundsForOtherPersonnel = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal cumTotalFundsForPersonnel = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal cumNumOtherPersonnel = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal cumEquipmentFunds = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal cumTravel = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal cumDomesticTravel = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal cumForeignTravel = ScaleTwoDecimal.ZERO;

    private List<OtherDirectCostInfoDto> otherDirect=new ArrayList<OtherDirectCostInfoDto>();

    private ScaleTwoDecimal partStipendCost = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal partTravelCost = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal partOtherCost = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal partSubsistence = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal partTuition = ScaleTwoDecimal.ZERO;
    private int participantCount;

    private ScaleTwoDecimal cumTotalDirectCosts = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal cumTotalIndirectCosts = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal cumTotalCosts = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal cumFee = ScaleTwoDecimal.ZERO;


    private ScaleTwoDecimal cumTotalCostSharing = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal cumTotalNonFundsForSrPersonnel = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal cumTotalNonFundsForOtherPersonnel = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal cumTotalNonFundsForPersonnel = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal cumTravelNonFund = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal cumDomesticTravelNonFund = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal cumForeignTravelNonFund = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal partStipendCostSharing = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal partTravelCostSharing = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal partSubsistenceCostSharing = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal partTuitionCostSharing = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal partOtherCostSharing = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal cumEquipmentNonFunds = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal cumTotalDirectCostSharing = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal cumTotalIndirectCostSharing = ScaleTwoDecimal.ZERO;

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
    public List<BudgetPeriodDto> getBudgetPeriods() {
        return budgetPeriods;
    }

    /**
     * Setter for property budgetPeriods.
     * 
     * @param budgetPeriods New value of property budgetPeriods.
     */
    public void setBudgetPeriods(List<BudgetPeriodDto> budgetPeriods) {
        this.budgetPeriods = budgetPeriods;
    }


    /**
     * Getter for property cumTotalFundsForSrPersonnel.
     * 
     * @return Value of property cumTotalFundsForSrPersonnel.
     */
    public ScaleTwoDecimal getCumTotalFundsForSrPersonnel() {
        return cumTotalFundsForSrPersonnel;
    }

    /**
     * Setter for property cumTotalFundsForSrPersonnel.
     * 
     * @param cumTotalFundsForSrPersonnel New value of property cumTotalFundsForSrPersonnel.
     */
    public void setCumTotalFundsForSrPersonnel(ScaleTwoDecimal cumTotalFundsForSrPersonnel) {
        this.cumTotalFundsForSrPersonnel = cumTotalFundsForSrPersonnel;
    }


    /**
     * Getter for property cumTotalFundsForOtherPersonnel.
     * 
     * @return Value of property cumTotalFundsForOtherPersonnel.
     */
    public ScaleTwoDecimal getCumTotalFundsForOtherPersonnel() {
        return cumTotalFundsForOtherPersonnel;
    }

    /**
     * Setter for property cumTotalFundsForOtherPersonnel.
     * 
     * @param cumTotalFundsForOtherPersonnel New value of property cumTotalFundsForOtherPersonnel.
     */
    public void setCumTotalFundsForOtherPersonnel(ScaleTwoDecimal cumTotalFundsForOtherPersonnel) {
        this.cumTotalFundsForOtherPersonnel = cumTotalFundsForOtherPersonnel;
    }


    /**
     * Getter for property cumTotalFundsForPersonnel.
     * 
     * @return Value of property cumTotalFundsForPersonnel.
     */
    public ScaleTwoDecimal getCumTotalFundsForPersonnel() {
        return cumTotalFundsForPersonnel;
    }

    /**
     * Setter for property cumTotalFundsForPersonnel.
     * 
     * @param cumTotalFundsForPersonnel New value of property cumTotalFundsForPersonnel.
     */
    public void setCumTotalFundsForPersonnel(ScaleTwoDecimal cumTotalFundsForPersonnel) {
        this.cumTotalFundsForPersonnel = cumTotalFundsForPersonnel;
    }


    /**
     * Getter for property cumNumOtherPersonnel.
     * 
     * @return Value of property cumNumOtherPersonnel.
     */
    public ScaleTwoDecimal getCumNumOtherPersonnel() {
        return cumNumOtherPersonnel;
    }

    /**
     * Setter for property cumNumOtherPersonnel.
     * 
     * @param cumNumOtherPersonnel New value of property cumNumOtherPersonnel.
     */
    public void setCumNumOtherPersonnel(ScaleTwoDecimal cumNumOtherPersonnel) {
        this.cumNumOtherPersonnel = cumNumOtherPersonnel;
    }


    /**
     * Getter for property cumEquipmentFunds.
     * 
     * @return Value of property cumEquipmentFunds.
     */
    public ScaleTwoDecimal getCumEquipmentFunds() {
        return cumEquipmentFunds;
    }

    /**
     * Setter for property cumEquipmentFunds.
     * 
     * @param cumEquipmentFunds New value of property cumEquipmentFunds.
     */
    public void setCumEquipmentFunds(ScaleTwoDecimal cumEquipmentFunds) {
        this.cumEquipmentFunds = cumEquipmentFunds;
    }


    /**
     * Getter for property cumTravel.
     * 
     * @return Value of property cumTravel.
     */
    public ScaleTwoDecimal getCumTravel() {
        return cumTravel;
    }

    /**
     * Setter for property cumTravel.
     * 
     * @param cumTravel New value of property cumTravel.
     */
    public void setCumTravel(ScaleTwoDecimal cumTravel) {
        this.cumTravel = cumTravel;
    }

    /**
     * Getter for property cumDomesticTravel.
     * 
     * @return Value of property cumDomesticTravel.
     */
    public ScaleTwoDecimal getCumDomesticTravel() {
        return cumDomesticTravel;
    }

    /**
     * Setter for property cumDomesticTravel.
     * 
     * @param cumDomesticTravel New value of property cumDomesticTravel.
     */
    public void setCumDomesticTravel(ScaleTwoDecimal cumDomesticTravel) {
        this.cumDomesticTravel = cumDomesticTravel;
    }

    /**
     * Getter for property cumForeignTravel.
     * 
     * @return Value of property cumForeignTravel.
     */
    public ScaleTwoDecimal getCumForeignTravel() {
        return cumForeignTravel;
    }

    /**
     * Setter for property cumForeignTravel.
     * 
     * @param cumForeignTravel New value of property cumForeignTravel.
     */
    public void setCumForeignTravel(ScaleTwoDecimal cumForeignTravel) {
        this.cumForeignTravel = cumForeignTravel;
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
     * Getter for property otherDirect. which is a Vector of otherDirectCostBeans
     * 
     * @return Value of property otherDirect.
     */
    public List<OtherDirectCostInfoDto> getOtherDirectCosts() {
        return otherDirect;
    }

    /**
     * Setter for property otherDirect.
     * 
     * @param otherDirect New value of property otherDirect.
     */
    public void setOtherDirectCosts(List<OtherDirectCostInfoDto> otherDirect) {
        this.otherDirect = otherDirect;
    }


    /**
     * Getter for property cumTotalDirectCosts.
     * 
     * @return Value of property cumTotalDirectCosts.
     */
    public ScaleTwoDecimal getCumTotalDirectCosts() {
        return cumTotalDirectCosts;
    }

    /**
     * Setter for property cumTotalDirectCosts.
     * 
     * @param cumTotalDirectCosts New value of property cumTotalDirectCosts.
     */
    public void setCumTotalDirectCosts(ScaleTwoDecimal cumTotalDirectCosts) {
        this.cumTotalDirectCosts = cumTotalDirectCosts;
    }


    /**
     * Getter for property cumTotalIndirectCosts.
     * 
     * @return Value of property cumTotalIndirectCosts.
     */
    public ScaleTwoDecimal getCumTotalIndirectCosts() {
        return cumTotalIndirectCosts;
    }

    /**
     * Setter for property cumTotalIndirectCosts.
     * 
     * @param cumTotalIndirectCosts New value of property cumTotalIndirectCosts.
     */
    public void setCumTotalIndirectCosts(ScaleTwoDecimal cumTotalIndirectCosts) {
        this.cumTotalIndirectCosts = cumTotalIndirectCosts;
    }


    /**
     * Getter for property cumTotalCosts.
     * 
     * @return Value of property cumTotalCosts.
     */
    public ScaleTwoDecimal getCumTotalCosts() {
        return cumTotalCosts;
    }

    /**
     * Setter for property cumTotalCosts.
     * 
     * @param cumTotalCosts New value of property cumTotalCosts.
     */
    public void setCumTotalCosts(ScaleTwoDecimal cumTotalCosts) {
        this.cumTotalCosts = cumTotalCosts;
    }


    /**
     * Getter for property cumFee.
     * 
     * @return Value of property cumFee.
     */
    public ScaleTwoDecimal getCumFee() {
        return cumFee;
    }

    /**
     * Setter for property cumFee.
     * 
     * @param cumFee New value of property cumFee.
     */
    public void setCumFee(ScaleTwoDecimal cumFee) {
        this.cumFee = cumFee;
    }


    public ScaleTwoDecimal getCumTotalCostSharing() {
        return cumTotalCostSharing;
    }

    public void setCumTotalCostSharing(ScaleTwoDecimal cumTotalCostSharing) {
        this.cumTotalCostSharing = cumTotalCostSharing;
    }

    public ScaleTwoDecimal getCumTotalNonFundsForSrPersonnel() {
        return cumTotalNonFundsForSrPersonnel;
    }

    public void setCumTotalNonFundsForSrPersonnel(ScaleTwoDecimal cumTotalNonFundsForSrPersonnel) {
        this.cumTotalNonFundsForSrPersonnel = cumTotalNonFundsForSrPersonnel;
    }

    public ScaleTwoDecimal getCumTotalNonFundsForOtherPersonnel() {
        return cumTotalNonFundsForOtherPersonnel;
    }

    public void setCumTotalNonFundsForOtherPersonnel(ScaleTwoDecimal cumTotalNonFundsForOtherPersonnel) {
        this.cumTotalNonFundsForOtherPersonnel = cumTotalNonFundsForOtherPersonnel;
    }

    public ScaleTwoDecimal getCumTotalNonFundsForPersonnel() {
        return cumTotalNonFundsForPersonnel;
    }

    public void setCumTotalNonFundsForPersonnel(ScaleTwoDecimal cumTotalNonFundsForPersonnel) {
        this.cumTotalNonFundsForPersonnel = cumTotalNonFundsForPersonnel;
    }

    public ScaleTwoDecimal getCumTravelNonFund() {
        return cumTravelNonFund;
    }

    public void setCumTravelNonFund(ScaleTwoDecimal cumTravelNonFund) {
        this.cumTravelNonFund = cumTravelNonFund;
    }

    public ScaleTwoDecimal getCumDomesticTravelNonFund() {
        return cumDomesticTravelNonFund;
    }

    public void setCumDomesticTravelNonFund(ScaleTwoDecimal cumDomesticTravelNonFund) {
        this.cumDomesticTravelNonFund = cumDomesticTravelNonFund;
    }

    public ScaleTwoDecimal getCumForeignTravelNonFund() {
        return cumForeignTravelNonFund;
    }

    public void setCumForeignTravelNonFund(ScaleTwoDecimal cumForeignTravelNonFund) {
        this.cumForeignTravelNonFund = cumForeignTravelNonFund;
    }

    public ScaleTwoDecimal getPartStipendCostSharing() {
        return partStipendCostSharing;
    }

    public void setPartStipendCostSharing(ScaleTwoDecimal partStipendCostSharing) {
        this.partStipendCostSharing = partStipendCostSharing;
    }

    public ScaleTwoDecimal getPartTravelCostSharing() {
        return partTravelCostSharing;
    }

    public void setPartTravelCostSharing(ScaleTwoDecimal partTravelCostSharing) {
        this.partTravelCostSharing = partTravelCostSharing;
    }

    public ScaleTwoDecimal getPartOtherCostSharing() {
        return partOtherCostSharing;
    }

    public void setPartOtherCostSharing(ScaleTwoDecimal partOtherCostSharing) {
        this.partOtherCostSharing = partOtherCostSharing;
    }

    public ScaleTwoDecimal getCumEquipmentNonFunds() {
        return cumEquipmentNonFunds;
    }

    public void setCumEquipmentNonFunds(ScaleTwoDecimal cumEquipmentNonFunds) {
        this.cumEquipmentNonFunds = cumEquipmentNonFunds;
    }

    public ScaleTwoDecimal getCumTotalDirectCostSharing() {
        return cumTotalDirectCostSharing;
    }

    public void setCumTotalDirectCostSharing(ScaleTwoDecimal cumTotalDirectCostSharing) {
        this.cumTotalDirectCostSharing = cumTotalDirectCostSharing;
    }

    public ScaleTwoDecimal getCumTotalIndirectCostSharing() {
        return cumTotalIndirectCostSharing;
    }

    public void setCumTotalIndirectCostSharing(ScaleTwoDecimal cumTotalIndirectCostSharing) {
        this.cumTotalIndirectCostSharing = cumTotalIndirectCostSharing;
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
     * Getter for property partSubsistenceCostSharing.
     * 
     * @return Value of property partSubsistenceCostSharing.
     */
    public ScaleTwoDecimal getPartSubsistenceCostSharing() {
        return partSubsistenceCostSharing;
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
        return partTuitionCostSharing;
    }

    /**
     * Setter for property partTuitionCostSharing.
     * 
     * @param partTuitionCostSharing New value of property partTuitionCostSharing.
     */
    public void setPartTuitionCostSharing(ScaleTwoDecimal partTuitionCostSharing) {
        this.partTuitionCostSharing = partTuitionCostSharing;
    }

}
