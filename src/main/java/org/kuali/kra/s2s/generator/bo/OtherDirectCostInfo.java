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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.budget.BudgetDecimal;

public class OtherDirectCostInfo {

    private BudgetDecimal materials;
    private BudgetDecimal publications;
    private BudgetDecimal consultants;
    private BudgetDecimal computer;
    private BudgetDecimal subAwards;
    private BudgetDecimal equipRental;
    private BudgetDecimal alterations;
    private List<Map<String,String>> otherCosts;
    private BudgetDecimal totalOtherDirect;

    private BudgetDecimal domTravel;
    private BudgetDecimal foreignTravel;
    private BudgetDecimal totTravel;

    private BudgetDecimal partTravel;
    private BudgetDecimal partStipends;
    private BudgetDecimal partOther;
    private BudgetDecimal participantTotal;
    private BudgetDecimal partTuition;
    private BudgetDecimal partSubsistence;

    private BudgetDecimal materialsCostSharing;
    private BudgetDecimal publicationsCostSharing;
    private BudgetDecimal consultantsCostSharing;
    private BudgetDecimal computerCostSharing;
    private BudgetDecimal subAwardsCostSharing;
    private BudgetDecimal equipRentalCostSharing;
    private BudgetDecimal alterationsCostSharing;
    private BudgetDecimal domTravelCostSharing;
    private BudgetDecimal foreignTravelCostSharing;
    private BudgetDecimal totTravelCostSharing;
    private BudgetDecimal partStipendsCostSharing;
    private BudgetDecimal partTravelCostSharing;
    private BudgetDecimal partOtherCostSharing;
    private BudgetDecimal participantTotalCostSharing;
    private BudgetDecimal totalOtherDirectCostSharing;
    private BudgetDecimal partTuitionCostSharing;
    private BudgetDecimal partSubsistenceCostSharing;
    private int particpantTotalCount;


    public OtherDirectCostInfo() {
        // otherCosts = new ArrayList<otherCosts>();
    }


    /**
     * Getter for property totalOtherDirect
     * 
     * @return Value of property totalOtherDirect.
     */

    public BudgetDecimal gettotalOtherDirect() {
        return totalOtherDirect;
    }

    /**
     * Setter for property totalOtherDirect
     * 
     * @param totalOtherDirect New value of property totalOtherDirect.
     */
    public void settotalOtherDirect(BudgetDecimal totalOtherDirect) {
        this.totalOtherDirect = totalOtherDirect;
    }


    /**
     * Getter for property materials
     * 
     * @return Value of property materials.
     */
    public BudgetDecimal getmaterials() {
        return materials;
    }

    /**
     * Setter for property materials.
     * 
     * @param materials New value of property materials.
     */
    public void setmaterials(BudgetDecimal materials) {
        this.materials = materials;
    }

    /**
     * Getter for property publications
     * 
     * @return Value of property publications.
     */
    public BudgetDecimal getpublications() {
        return publications;
    }

    /**
     * Setter for property publications.
     * 
     * @param publications New value of property publications.
     */
    public void setpublications(BudgetDecimal publications) {
        this.publications = publications;
    }

    /**
     * Getter for property consultants
     * 
     * @return Value of property consultants.
     */
    public BudgetDecimal getConsultants() {
        return consultants;
    }

    /**
     * Setter for property consultants.
     * 
     * @param consultants New value of property consultants.
     */
    public void setConsultants(BudgetDecimal consultants) {
        this.consultants = consultants;
    }

    /**
     * Getter for property computer
     * 
     * @return Value of property computer.
     */
    public BudgetDecimal getcomputer() {
        return computer;
    }

    /**
     * Setter for property computer.
     * 
     * @param computer New value of property computer.
     */
    public void setcomputer(BudgetDecimal computer) {
        this.computer = computer;
    }

    /**
     * Getter for property subAwards
     * 
     * @return Value of property subAwards.
     */
    public BudgetDecimal getsubAwards() {
        return subAwards;
    }

    /**
     * Setter for property subAwards.
     * 
     * @param subAwards New value of property subAwards.
     */
    public void setsubAwards(BudgetDecimal subAwards) {
        this.subAwards = subAwards;
    }

    /**
     * Getter for property equipRental
     * 
     * @return Value of property EquipRental.
     */
    public BudgetDecimal getEquipRental() {
        return equipRental;
    }

    /**
     * Setter for property equipRental.
     * 
     * @param equipRental New value of property equipRental.
     */
    public void setEquipRental(BudgetDecimal equipRental) {
        this.equipRental = equipRental;
    }

    /**
     * Getter for property alterations
     * 
     * @return Value of property alterations.
     */
    public BudgetDecimal getAlterations() {
        return alterations;
    }

    /**
     * Setter for property alterations.
     * 
     * @param alterations New value of property alterations.
     */
    public void setAlterations(BudgetDecimal alterations) {
        this.alterations = alterations;
    }

    /**
     * Getter for property domTravel
     * 
     * @return Value of property domTravel.
     */
    public BudgetDecimal getDomTravel() {
        return domTravel;
    }

    /**
     * Setter for property domTravel.
     * 
     * @param domTravel New value of property domTravel.
     */
    public void setDomTravel(BudgetDecimal domTravel) {
        this.domTravel = domTravel;
    }

    /**
     * Getter for property foreignTravel
     * 
     * @return Value of property foreignTravel.
     */
    public BudgetDecimal getForeignTravel() {
        return foreignTravel;
    }

    /**
     * Setter for property foreignTravel.
     * 
     * @param foreignTravel New value of property foreignTravel.
     */
    public void setForeignTravel(BudgetDecimal foreignTravel) {
        this.foreignTravel = foreignTravel;
    }

    /**
     * Getter for property totTravel
     * 
     * @return Value of property totTravel.
     */
    public BudgetDecimal getTotTravel() {
        return totTravel;
    }

    /**
     * Setter for property totTravel.
     * 
     * @param totTravel New value of property totTravel.
     */
    public void setTotTravel(BudgetDecimal totTravel) {
        this.totTravel = totTravel;
    }

    /**
     * Getter for property otherCosts
     * 
     * @return Value of property otherCosts.
     */
    public List<Map<String,String>> getOtherCosts() {
        return otherCosts;
    }

    /**
     * Setter for property otherCosts.
     * 
     * @param otherCosts New value of property otherCosts.
     */
    public void setOtherCosts(List<Map<String,String>> otherCosts) {
        this.otherCosts = otherCosts;
    }

    /**
     * Getter for property partStipends
     * 
     * @return Value of property partStipends.
     */
    public BudgetDecimal getPartStipends() {
        return partStipends;
    }

    /**
     * Setter for property partStipends.
     * 
     * @param partStipends New value of property partStipends.
     */
    public void setPartStipends(BudgetDecimal partStipends) {
        this.partStipends = partStipends;
    }

    /**
     * Getter for property partStipendsCount
     * 
     * @return Value of property partStipendsCount.
     */
    // public int getPartStipendsCount() {
    // return partStipendsCount;
    // }
    /**
     * Setter for property partStipendsCount.
     * 
     * @param partStipendsCount New value of property partStipendsCount.
     */
    // public void setPartStipendsCount(int partStipendsCount) {
    // this.partStipendsCount = partStipendsCount;
    // }
    /**
     * Getter for property partOther
     * 
     * @return Value of property partOther.
     */
    public BudgetDecimal getPartOther() {
        return partOther;
    }

    /**
     * Setter for property partOther.
     * 
     * @param partOther New value of property partOther.
     */
    public void setPartOther(BudgetDecimal partOther) {
        this.partOther = partOther;
    }

    /**
     * Getter for property partOtherCount
     * 
     * @return Value of property partOtherCount.
     */
    // public int getPartOtherCount() {
    // return partOtherCount;
    // }
    /**
     * Setter for property partOtherCount.
     * 
     * @param partOtherCount New value of property partOtherCount.
     */
    // public void setPartOtherCount(int partOtherCount) {
    // this.partOtherCount= partOtherCount;
    // }
    /**
     * Getter for property partTravel
     * 
     * @return Value of property partTravel.
     */
    public BudgetDecimal getPartTravel() {
        return partTravel;
    }

    /**
     * Setter for property partTravel.
     * 
     * @param partTravel New value of property partTravel.
     */
    public void setPartTravel(BudgetDecimal partTravel) {
        this.partTravel = partTravel;
    }


    /**
     * Getter for property partTravelCount
     * 
     * @return Value of property partTravelCount.
     */
    // public int getPartTravelCount() {
    // return partTravelCount;
    // }
    /**
     * Setter for property partTravelCount.
     * 
     * @param partTravelCount New value of property partTravelCount.
     */
    // public void setPartTravelCount(int partTravelCount) {
    // this.partTravelCount= partTravelCount;
    // }
    /**
     * Getter for property participantTotal
     * 
     * @return Value of property participantTotal.
     */
    public BudgetDecimal getParticipantTotal() {
        return participantTotal;
    }

    /**
     * Setter for property participantTotal.
     * 
     * @param participantTotal New value of property participantTotal.
     */
    public void setParticipantTotal(BudgetDecimal participantTotal) {
        this.participantTotal = participantTotal;
    }

    /**
     * Getter for property particpantTotalCount
     * 
     * @return Value of property particpantTotalCount.
     */
    public int getParticpantTotalCount() {
        return particpantTotalCount;
    }

    /**
     * Setter for property participantTotalCount.
     * 
     * @param participantTotalCount New value of property particpantTotalCount.
     */
    public void setParticipantTotalCount(int particpantTotalCount) {
        this.particpantTotalCount = particpantTotalCount;
    }

    // start add costSaring for fedNonFedBudget repport
    /**
     * Getter for property materialsCostSharing
     * 
     * @return Value of property materialsCostSharing.
     */
    public BudgetDecimal getMaterialsCostSharing() {
        return materialsCostSharing;
    }

    /**
     * Setter for property materialsCostSharing.
     * 
     * @param materialsCostSharing New value of property materialsCostSharing.
     */
    public void setMaterialsCostSharing(BudgetDecimal materialsCostSharing) {
        this.materialsCostSharing = materialsCostSharing;
    }

    public BudgetDecimal getPublicationsCostSharing() {
        return publicationsCostSharing;
    }

    public void setPublicationsCostSharing(BudgetDecimal publicationsCostSharing) {
        this.publicationsCostSharing = publicationsCostSharing;
    }

    public BudgetDecimal getConsultantsCostSharing() {
        return consultantsCostSharing;
    }

    public void setConsultantsCostSharing(BudgetDecimal consultantsCostSharing) {
        this.consultantsCostSharing = consultantsCostSharing;
    }

    public BudgetDecimal getComputerCostSharing() {
        return computerCostSharing;
    }

    public void setComputerCostSharing(BudgetDecimal computerCostSharing) {
        this.computerCostSharing = computerCostSharing;
    }

    public BudgetDecimal getSubAwardsCostSharing() {
        return subAwardsCostSharing;
    }

    public void setSubAwardsCostSharing(BudgetDecimal subAwardsCostSharing) {
        this.subAwardsCostSharing = subAwardsCostSharing;
    }

    public BudgetDecimal getEquipRentalCostSharing() {
        return equipRentalCostSharing;
    }

    public void setEquipRentalCostSharing(BudgetDecimal equipRentalCostSharing) {
        this.equipRentalCostSharing = equipRentalCostSharing;
    }

    public BudgetDecimal getAlterationsCostSharing() {
        return alterationsCostSharing;
    }

    public void setAlterationsCostSharing(BudgetDecimal alterationsCostSharing) {
        this.alterationsCostSharing = alterationsCostSharing;
    }

    public BudgetDecimal getDomTravelCostSharing() {
        return domTravelCostSharing;
    }

    public void setDomTravelCostSharing(BudgetDecimal domTravelCostSharing) {
        this.domTravelCostSharing = domTravelCostSharing;
    }

    public BudgetDecimal getForeignTravelCostSharing() {
        return foreignTravelCostSharing;
    }

    public void setForeignTravelCostSharing(BudgetDecimal foreignTravelCostSharing) {
        this.foreignTravelCostSharing = foreignTravelCostSharing;
    }

    public BudgetDecimal getTotTravelCostSharing() {
        return totTravelCostSharing;
    }

    public void setTotTravelCostSharing(BudgetDecimal totTravelCostSharing) {
        this.totTravelCostSharing = totTravelCostSharing;
    }

    public BudgetDecimal getPartStipendsCostSharing() {
        return partStipendsCostSharing;
    }

    public void setPartStipendsCostSharing(BudgetDecimal partStipendsCostSharing) {
        this.partStipendsCostSharing = partStipendsCostSharing;
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

    public BudgetDecimal getParticipantTotalCostSharing() {
        return participantTotalCostSharing;
    }

    public void setParticipantTotalCostSharing(BudgetDecimal participantTotalCostSharing) {
        this.participantTotalCostSharing = participantTotalCostSharing;
    }

    public BudgetDecimal getTotalOtherDirectCostSharing() {
        return totalOtherDirectCostSharing;
    }

    public void setTotalOtherDirectCostSharing(BudgetDecimal totalOtherDirectCostSharing) {
        this.totalOtherDirectCostSharing = totalOtherDirectCostSharing;
    }

    // end add costSaring for fedNonFedBudget repport


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


    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = new LinkedHashMap();
        hashMap.put("materials", getmaterials());
        hashMap.put("publications", getpublications());
        hashMap.put("consultants", getConsultants());
        hashMap.put("computer", getcomputer());
        hashMap.put("subAwards", getsubAwards());
        hashMap.put("equipRental", getEquipRental());
        hashMap.put("alterations", getAlterations());
        hashMap.put("totalOtherDirect", gettotalOtherDirect());
        hashMap.put("domTravel", getDomTravel());
        hashMap.put("foreignTravel", getForeignTravel());
        hashMap.put("totTravel", getTotTravel());
        hashMap.put("partTravel", getPartTravel());
        hashMap.put("partStipends", getPartStipends());
        hashMap.put("partOther", getPartOther());
        hashMap.put("participantTotal", getParticipantTotal());
        hashMap.put("partTuition", getPartTuition());
        hashMap.put("partSubsistence", getPartSubsistence());
        hashMap.put("materialsCostSharing", getMaterialsCostSharing());
        hashMap.put("publicationsCostSharing", getPublicationsCostSharing());
        hashMap.put("consultantsCostSharing", getConsultantsCostSharing());
        hashMap.put("computerCostSharing", getComputerCostSharing());
        hashMap.put("subAwardsCostSharing", getSubAwardsCostSharing());
        hashMap.put("equipRentalCostSharing", getEquipRentalCostSharing());
        hashMap.put("alterationsCostSharing", getAlterationsCostSharing());
        hashMap.put("domTravelCostSharing", getForeignTravelCostSharing());
        hashMap.put("foreignTravelCostSharing", getForeignTravelCostSharing());
        hashMap.put("totTravelCostSharing", getTotTravelCostSharing());
        hashMap.put("partStipendsCostSharing", getPartStipendsCostSharing());
        hashMap.put("partTravelCostSharing", getPartTravelCostSharing());
        hashMap.put("partOtherCostSharing", getPartOtherCostSharing());
        hashMap.put("participantTotalCostSharing", getParticipantTotalCostSharing());
        hashMap.put("totalOtherDirectCostSharing", getTotalOtherDirectCostSharing());
        hashMap.put("partTuitionCostSharing", getPartTuitionCostSharing());
        hashMap.put("partSubsistenceCostSharing", getPartSubsistenceCostSharing());
        hashMap.put("particpantTotalCount", getParticpantTotalCount());

        return hashMap;
    }

}
