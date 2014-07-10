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

import java.util.List;
import java.util.Map;

public class OtherDirectCostInfoDto {

    private ScaleTwoDecimal materials;
    private ScaleTwoDecimal publications;
    private ScaleTwoDecimal consultants;
    private ScaleTwoDecimal computer;
    private ScaleTwoDecimal subAwards;
    private ScaleTwoDecimal equipRental;
    private ScaleTwoDecimal alterations;
    private List<Map<String,String>> otherCosts;
    private ScaleTwoDecimal totalOtherDirect;

    private ScaleTwoDecimal domTravel;
    private ScaleTwoDecimal foreignTravel;
    private ScaleTwoDecimal totTravel;

    private ScaleTwoDecimal partTravel;
    private ScaleTwoDecimal partStipends;
    private ScaleTwoDecimal partOther;
    private ScaleTwoDecimal participantTotal;
    private ScaleTwoDecimal partTuition;
    private ScaleTwoDecimal partSubsistence;

    private ScaleTwoDecimal materialsCostSharing;
    private ScaleTwoDecimal publicationsCostSharing;
    private ScaleTwoDecimal consultantsCostSharing;
    private ScaleTwoDecimal computerCostSharing;
    private ScaleTwoDecimal subAwardsCostSharing;
    private ScaleTwoDecimal equipRentalCostSharing;
    private ScaleTwoDecimal alterationsCostSharing;
    private ScaleTwoDecimal domTravelCostSharing;
    private ScaleTwoDecimal foreignTravelCostSharing;
    private ScaleTwoDecimal totTravelCostSharing;
    private ScaleTwoDecimal partStipendsCostSharing;
    private ScaleTwoDecimal partTravelCostSharing;
    private ScaleTwoDecimal partOtherCostSharing;
    private ScaleTwoDecimal participantTotalCostSharing;
    private ScaleTwoDecimal totalOtherDirectCostSharing;
    private ScaleTwoDecimal partTuitionCostSharing;
    private ScaleTwoDecimal partSubsistenceCostSharing;
    private int particpantTotalCount;


    /**
     * Getter for property totalOtherDirect
     * 
     * @return Value of property totalOtherDirect.
     */
    public ScaleTwoDecimal gettotalOtherDirect() {
        return totalOtherDirect;
    }

    /**
     * Setter for property totalOtherDirect
     * 
     * @param totalOtherDirect New value of property totalOtherDirect.
     */
    public void settotalOtherDirect(ScaleTwoDecimal totalOtherDirect) {
        this.totalOtherDirect = totalOtherDirect;
    }


    /**
     * Getter for property materials
     * 
     * @return Value of property materials.
     */
    public ScaleTwoDecimal getmaterials() {
        return materials;
    }

    /**
     * Setter for property materials.
     * 
     * @param materials New value of property materials.
     */
    public void setmaterials(ScaleTwoDecimal materials) {
        this.materials = materials;
    }

    /**
     * Getter for property publications
     * 
     * @return Value of property publications.
     */
    public ScaleTwoDecimal getpublications() {
        return publications;
    }

    /**
     * Setter for property publications.
     * 
     * @param publications New value of property publications.
     */
    public void setpublications(ScaleTwoDecimal publications) {
        this.publications = publications;
    }

    /**
     * Getter for property consultants
     * 
     * @return Value of property consultants.
     */
    public ScaleTwoDecimal getConsultants() {
        return consultants;
    }

    /**
     * Setter for property consultants.
     * 
     * @param consultants New value of property consultants.
     */
    public void setConsultants(ScaleTwoDecimal consultants) {
        this.consultants = consultants;
    }

    /**
     * Getter for property computer
     * 
     * @return Value of property computer.
     */
    public ScaleTwoDecimal getcomputer() {
        return computer;
    }

    /**
     * Setter for property computer.
     * 
     * @param computer New value of property computer.
     */
    public void setcomputer(ScaleTwoDecimal computer) {
        this.computer = computer;
    }

    /**
     * Getter for property subAwards
     * 
     * @return Value of property subAwards.
     */
    public ScaleTwoDecimal getsubAwards() {
        return subAwards;
    }

    /**
     * Setter for property subAwards.
     * 
     * @param subAwards New value of property subAwards.
     */
    public void setsubAwards(ScaleTwoDecimal subAwards) {
        this.subAwards = subAwards;
    }

    /**
     * Getter for property equipRental
     * 
     * @return Value of property EquipRental.
     */
    public ScaleTwoDecimal getEquipRental() {
        return equipRental;
    }

    /**
     * Setter for property equipRental.
     * 
     * @param equipRental New value of property equipRental.
     */
    public void setEquipRental(ScaleTwoDecimal equipRental) {
        this.equipRental = equipRental;
    }

    /**
     * Getter for property alterations
     * 
     * @return Value of property alterations.
     */
    public ScaleTwoDecimal getAlterations() {
        return alterations;
    }

    /**
     * Setter for property alterations.
     * 
     * @param alterations New value of property alterations.
     */
    public void setAlterations(ScaleTwoDecimal alterations) {
        this.alterations = alterations;
    }

    /**
     * Getter for property domTravel
     * 
     * @return Value of property domTravel.
     */
    public ScaleTwoDecimal getDomTravel() {
        return domTravel;
    }

    /**
     * Setter for property domTravel.
     * 
     * @param domTravel New value of property domTravel.
     */
    public void setDomTravel(ScaleTwoDecimal domTravel) {
        this.domTravel = domTravel;
    }

    /**
     * Getter for property foreignTravel
     * 
     * @return Value of property foreignTravel.
     */
    public ScaleTwoDecimal getForeignTravel() {
        return foreignTravel;
    }

    /**
     * Setter for property foreignTravel.
     * 
     * @param foreignTravel New value of property foreignTravel.
     */
    public void setForeignTravel(ScaleTwoDecimal foreignTravel) {
        this.foreignTravel = foreignTravel;
    }

    /**
     * Getter for property totTravel
     * 
     * @return Value of property totTravel.
     */
    public ScaleTwoDecimal getTotTravel() {
        return totTravel;
    }

    /**
     * Setter for property totTravel.
     * 
     * @param totTravel New value of property totTravel.
     */
    public void setTotTravel(ScaleTwoDecimal totTravel) {
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
    public ScaleTwoDecimal getPartStipends() {
        return partStipends;
    }

    /**
     * Setter for property partStipends.
     * 
     * @param partStipends New value of property partStipends.
     */
    public void setPartStipends(ScaleTwoDecimal partStipends) {
        this.partStipends = partStipends;
    }

    /**
     * Getter for property partOther
     * 
     * @return Value of property partOther.
     */
    public ScaleTwoDecimal getPartOther() {
        return partOther;
    }

    /**
     * Setter for property partOther.
     * 
     * @param partOther New value of property partOther.
     */
    public void setPartOther(ScaleTwoDecimal partOther) {
        this.partOther = partOther;
    }

    /**
     * Getter for property partTravel
     * 
     * @return Value of property partTravel.
     */
    public ScaleTwoDecimal getPartTravel() {
        return partTravel;
    }

    /**
     * Setter for property partTravel.
     * 
     * @param partTravel New value of property partTravel.
     */
    public void setPartTravel(ScaleTwoDecimal partTravel) {
        this.partTravel = partTravel;
    }

    /**
     * Getter for property participantTotal
     * 
     * @return Value of property participantTotal.
     */
    public ScaleTwoDecimal getParticipantTotal() {
        return participantTotal;
    }

    /**
     * Setter for property participantTotal.
     * 
     * @param participantTotal New value of property participantTotal.
     */
    public void setParticipantTotal(ScaleTwoDecimal participantTotal) {
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
     * @param particpantTotalCount New value of property particpantTotalCount.
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
    public ScaleTwoDecimal getMaterialsCostSharing() {
        return materialsCostSharing;
    }

    /**
     * Setter for property materialsCostSharing.
     * 
     * @param materialsCostSharing New value of property materialsCostSharing.
     */
    public void setMaterialsCostSharing(ScaleTwoDecimal materialsCostSharing) {
        this.materialsCostSharing = materialsCostSharing;
    }

    public ScaleTwoDecimal getPublicationsCostSharing() {
        return publicationsCostSharing;
    }

    public void setPublicationsCostSharing(ScaleTwoDecimal publicationsCostSharing) {
        this.publicationsCostSharing = publicationsCostSharing;
    }

    public ScaleTwoDecimal getConsultantsCostSharing() {
        return consultantsCostSharing;
    }

    public void setConsultantsCostSharing(ScaleTwoDecimal consultantsCostSharing) {
        this.consultantsCostSharing = consultantsCostSharing;
    }

    public ScaleTwoDecimal getComputerCostSharing() {
        return computerCostSharing;
    }

    public void setComputerCostSharing(ScaleTwoDecimal computerCostSharing) {
        this.computerCostSharing = computerCostSharing;
    }

    public ScaleTwoDecimal getSubAwardsCostSharing() {
        return subAwardsCostSharing;
    }

    public void setSubAwardsCostSharing(ScaleTwoDecimal subAwardsCostSharing) {
        this.subAwardsCostSharing = subAwardsCostSharing;
    }

    public ScaleTwoDecimal getEquipRentalCostSharing() {
        return equipRentalCostSharing;
    }

    public void setEquipRentalCostSharing(ScaleTwoDecimal equipRentalCostSharing) {
        this.equipRentalCostSharing = equipRentalCostSharing;
    }

    public ScaleTwoDecimal getAlterationsCostSharing() {
        return alterationsCostSharing;
    }

    public void setAlterationsCostSharing(ScaleTwoDecimal alterationsCostSharing) {
        this.alterationsCostSharing = alterationsCostSharing;
    }

    public ScaleTwoDecimal getDomTravelCostSharing() {
        return domTravelCostSharing;
    }

    public void setDomTravelCostSharing(ScaleTwoDecimal domTravelCostSharing) {
        this.domTravelCostSharing = domTravelCostSharing;
    }

    public ScaleTwoDecimal getForeignTravelCostSharing() {
        return foreignTravelCostSharing;
    }

    public void setForeignTravelCostSharing(ScaleTwoDecimal foreignTravelCostSharing) {
        this.foreignTravelCostSharing = foreignTravelCostSharing;
    }

    public ScaleTwoDecimal getTotTravelCostSharing() {
        return totTravelCostSharing;
    }

    public void setTotTravelCostSharing(ScaleTwoDecimal totTravelCostSharing) {
        this.totTravelCostSharing = totTravelCostSharing;
    }

    public ScaleTwoDecimal getPartStipendsCostSharing() {
        return partStipendsCostSharing;
    }

    public void setPartStipendsCostSharing(ScaleTwoDecimal partStipendsCostSharing) {
        this.partStipendsCostSharing = partStipendsCostSharing;
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

    public ScaleTwoDecimal getParticipantTotalCostSharing() {
        return participantTotalCostSharing;
    }

    public void setParticipantTotalCostSharing(ScaleTwoDecimal participantTotalCostSharing) {
        this.participantTotalCostSharing = participantTotalCostSharing;
    }

    public ScaleTwoDecimal getTotalOtherDirectCostSharing() {
        return totalOtherDirectCostSharing;
    }

    public void setTotalOtherDirectCostSharing(ScaleTwoDecimal totalOtherDirectCostSharing) {
        this.totalOtherDirectCostSharing = totalOtherDirectCostSharing;
    }

    // end add costSaring for fedNonFedBudget repport


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

}
