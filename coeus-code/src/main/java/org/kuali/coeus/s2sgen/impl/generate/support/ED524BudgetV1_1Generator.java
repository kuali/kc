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
package org.kuali.coeus.s2sgen.impl.generate.support;

import gov.grants.apply.forms.ed524BudgetV11.ED524BudgetDocument;
import gov.grants.apply.forms.ed524BudgetV11.ED524BudgetDocument.ED524Budget;
import gov.grants.apply.forms.ed524BudgetV11.ED524BudgetDocument.ED524Budget.IndirectCost;
import gov.grants.apply.forms.ed524BudgetV11.ED524BudgetDocument.ED524Budget.IndirectCost.ApprovingFederalAgency;
import gov.grants.apply.forms.ed524BudgetV11.ED524BudgetDocument.ED524Budget.IndirectCost.IndirectCostRateAgreementFromDate;
import gov.grants.apply.forms.ed524BudgetV11.ED524BudgetDocument.ED524Budget.IndirectCost.OtherApprovingFederalAgency;
import gov.grants.apply.forms.ed524BudgetV11.ED524BudgetDocument.ED524Budget.IndirectCost.RestrictedIndirectCostRate;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.budget.api.period.BudgetPeriodContract;
import org.kuali.coeus.propdev.api.budget.ProposalDevelopmentBudgetExtContract;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.s2sgen.impl.budget.BudgetPeriodNum;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;

import java.math.BigDecimal;

@FormGenerator("ED524BudgetV1_1Generator")
public class ED524BudgetV1_1Generator extends ED524BudgetBaseGenerator {
    private static final Log LOG = LogFactory.getLog(ED524BudgetV1_1Generator.class);

    /**
     * 
     * This method is used to get 5 Budget years information for the form ED524Budget
     * 
     * @return ed524BudgetDocument {@link XmlObject} of type ED524BudgetDocument.
     */
    private ED524BudgetDocument getED524Budget() {
        
        ED524BudgetDocument ed524BudgetDocument = ED524BudgetDocument.Factory.newInstance();
        ED524Budget ed524Budget = ED524Budget.Factory.newInstance();

        ed524Budget.setFormVersion(FormVersion.v1_1.getVersion());
        if (pdDoc.getDevelopmentProposal().getApplicantOrganization() != null) {
            ed524Budget.setLEGALNAME(pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization().getOrganizationName());
        }
        else {
            ed524Budget.setLEGALNAME(DEFAULT_LEGAL_NAME);
        }

        ScaleTwoDecimal totalCategoryCS = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalPersonnel = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalPersonnelCS = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalFringe = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalFringeCS = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalTravel = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalTravelCS = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalEquip = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalEquipCS = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalSupplies = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalSuppliesCS = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalContractual = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalContractualCS = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalConstruction = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalConstructionCS = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalOther = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalOtherCS = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalTraining = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalTrainingCS = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalCostAllYrs = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalCostSharingAllYrs = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalDirectCostAllYrs = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalDirectCostAllYrsCS = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalIndirectCostAllYrs = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal totalIndirectCostAllYrsCS = ScaleTwoDecimal.ZERO;


        ProposalDevelopmentBudgetExtContract budget = pdDoc.getDevelopmentProposal().getFinalBudget();

        if (budget == null) {
            return ed524BudgetDocument;
        }

        for (BudgetPeriodContract budgetPeriod : budget.getBudgetPeriods()) {
            if (budgetPeriod.getBudgetPeriod().equals(BudgetPeriodNum.P1.getNum())) {
                getTotalCosts(budgetPeriod);
                ed524Budget.setBudgetFederalFirstYearAmount(totalCost.bigDecimalValue());
                ed524Budget.setBudgetNonFederalFirstYearAmount(totalCostSharing.bigDecimalValue());
                ed524Budget.setBudgetFederalDirectFirstYearAmount(totalDirectCost.bigDecimalValue());
                ed524Budget.setBudgetFederalIndirectFirstYearAmount(totalIndirectCost.bigDecimalValue());

                totalCostAllYrs = totalCostAllYrs.add(totalCost);
                totalCostSharingAllYrs = totalCostSharingAllYrs.add(totalCostSharing);
                totalDirectCostAllYrs = totalDirectCostAllYrs.add(totalDirectCost);
                totalIndirectCostAllYrs = totalIndirectCostAllYrs.add(totalIndirectCost);

                // Total Indirect Cost Sharing
                getIndirectCosts(budgetPeriod);
                ed524Budget.setBudgetNonFederalIndirectFirstYearAmount(indirectCS.bigDecimalValue());
                totalIndirectCostAllYrsCS = totalIndirectCostAllYrsCS.add(indirectCS);

                // Supplies
                getSuppliesCosts(budgetPeriod);
                ed524Budget.setBudgetFederalSuppliesFirstYearAmount(supplyCost.bigDecimalValue());
                ed524Budget.setBudgetNonFederalSuppliesFirstYearAmount(supplyCostCS.bigDecimalValue());

                totalCategoryCS = totalCategoryCS.add(supplyCostCS);
                totalSupplies = totalSupplies.add(supplyCost);
                totalSuppliesCS = totalSuppliesCS.add(supplyCostCS);

                // Construction
                // Set to zero as it set hard coded as 0 in the procedure
                ed524Budget.setBudgetFederalConstructionFirstYearAmount(ScaleTwoDecimal.ZERO.bigDecimalValue());
                ed524Budget.setBudgetNonFederalConstructionFirstYearAmount(ScaleTwoDecimal.ZERO.bigDecimalValue());


                // Other
                getOtherCosts(budgetPeriod);
                ed524Budget.setBudgetFederalOtherFirstYearAmount(otherCost.bigDecimalValue());
                ed524Budget.setBudgetNonFederalOtherFirstYearAmount(otherCostCS.bigDecimalValue());

                totalCategoryCS = totalCategoryCS.add(otherCostCS);
                totalOther = totalOther.add(otherCost);
                totalOtherCS = totalOtherCS.add(otherCostCS);

                // Equipment
                getEquipmentCosts(budgetPeriod);
                ed524Budget.setBudgetFederalEquipmentFirstYearAmount(equipmentCost.bigDecimalValue());
                ed524Budget.setBudgetNonFederalEquipmentFirstYearAmount(equipmentCostCS.bigDecimalValue());

                totalCategoryCS = totalCategoryCS.add(equipmentCostCS);
                totalEquip = totalEquip.add(equipmentCost);
                totalEquipCS = totalEquipCS.add(equipmentCostCS);

                // Contractual
                getContractualCosts(budgetPeriod);
                ed524Budget.setBudgetFederalContractualFirstYearAmount(contractualCost.bigDecimalValue());
                ed524Budget.setBudgetNonFederalContractualFirstYearAmount(contractualCostCS.bigDecimalValue());

                totalCategoryCS = totalCategoryCS.add(contractualCostCS);
                totalContractual = totalContractual.add(contractualCost);
                totalContractualCS = totalContractualCS.add(contractualCostCS);

                // Travel
                getTravelCosts(budgetPeriod);
                ed524Budget.setBudgetFederalTravelFirstYearAmount(travelCost.bigDecimalValue());
                ed524Budget.setBudgetNonFederalTravelFirstYearAmount(travelCostCS.bigDecimalValue());

                totalCategoryCS = totalCategoryCS.add(travelCostCS);
                totalTravel = totalTravel.add(travelCost);
                totalTravelCS = totalTravelCS.add(travelCostCS);

                // Training
                getTrainingCosts(budgetPeriod);
                ed524Budget.setBudgetFederalTrainingFirstYearAmount(trainingCost.bigDecimalValue());
                ed524Budget.setBudgetNonFederalTrainingFirstYearAmount(trainingCostCS.bigDecimalValue());

                totalTraining = totalTraining.add(trainingCost);
                totalTrainingCS = totalTrainingCS.add(trainingCostCS);

                // Fringe
                ed524Budget.setBudgetFederalFringeFirstYearAmount(categoryCostFringe.bigDecimalValue());
                ed524Budget.setBudgetNonFederalFringeFirstYearAmount(categoryCostCSFringe.bigDecimalValue());

                totalCategoryCS = totalCategoryCS.add(categoryCostCSFringe);
                totalFringe = totalFringe.add(categoryCostFringe);
                totalFringeCS = totalFringeCS.add(categoryCostCSFringe);

                // Personnel
                getPersonnelCosts(budgetPeriod);
                ed524Budget.setBudgetFederalPersonnelFirstYearAmount(personnelCost.bigDecimalValue());
                ed524Budget.setBudgetNonFederalPersonnelFirstYearAmount(personnelCostCS.bigDecimalValue());

                totalCategoryCS = totalCategoryCS.add(personnelCostCS);
                totalPersonnel = totalPersonnel.add(personnelCost);
                totalPersonnelCS = totalPersonnelCS.add(personnelCostCS);

                ed524Budget.setBudgetNonFederalDirectFirstYearAmount(totalCategoryCS.bigDecimalValue());

            }
            else if (budgetPeriod.getBudgetPeriod().equals(BudgetPeriodNum.P2.getNum())) {
                totalCategoryCS = ScaleTwoDecimal.ZERO;
                getTotalCosts(budgetPeriod);
                ed524Budget.setBudgetFederalSecondYearAmount(totalCost.bigDecimalValue());
                ed524Budget.setBudgetNonFederalSecondYearAmount(totalCostSharing.bigDecimalValue());
                ed524Budget.setBudgetFederalDirectSecondYearAmount(totalDirectCost.bigDecimalValue());
                ed524Budget.setBudgetFederalIndirectSecondYearAmount(totalIndirectCost.bigDecimalValue());

                totalCostAllYrs = totalCostAllYrs.add(totalCost);
                totalCostSharingAllYrs = totalCostSharingAllYrs.add(totalCostSharing);
                totalDirectCostAllYrs = totalDirectCostAllYrs.add(totalDirectCost);
                totalIndirectCostAllYrs = totalIndirectCostAllYrs.add(totalIndirectCost);

                // Total Indirect Cost Sharing
                getIndirectCosts(budgetPeriod);
                ed524Budget.setBudgetNonFederalIndirectSecondYearAmount(indirectCS.bigDecimalValue());
                totalIndirectCostAllYrsCS = totalIndirectCostAllYrsCS.add(indirectCS);

                // Supplies
                getSuppliesCosts(budgetPeriod);
                ed524Budget.setBudgetFederalSuppliesSecondYearAmount(supplyCost.bigDecimalValue());
                ed524Budget.setBudgetNonFederalSuppliesSecondYearAmount(supplyCostCS.bigDecimalValue());

                totalCategoryCS = totalCategoryCS.add(supplyCostCS);
                totalSupplies = totalSupplies.add(supplyCost);
                totalSuppliesCS = totalSuppliesCS.add(supplyCostCS);

                // Construction
                // Set to zero as it set hard coded as 0 in the procedure
                ed524Budget.setBudgetFederalConstructionSecondYearAmount(ScaleTwoDecimal.ZERO.bigDecimalValue());
                ed524Budget.setBudgetNonFederalConstructionSecondYearAmount(ScaleTwoDecimal.ZERO.bigDecimalValue());


                // Other
                getOtherCosts(budgetPeriod);
                if (otherCost.longValue() < 0) {
                    ed524Budget.setBudgetFederalOtherSecondYearAmount(BigDecimal.ZERO);
                }
                else {
                    ed524Budget.setBudgetFederalOtherSecondYearAmount(otherCost.bigDecimalValue());
                }

                ed524Budget.setBudgetNonFederalOtherSecondYearAmount(otherCostCS.bigDecimalValue());

                totalCategoryCS = totalCategoryCS.add(otherCostCS);
                totalOther = totalOther.add(otherCost);
                totalOtherCS = totalOtherCS.add(otherCostCS);

                // Equipment
                getEquipmentCosts(budgetPeriod);
                ed524Budget.setBudgetFederalEquipmentSecondYearAmount(equipmentCost.bigDecimalValue());
                ed524Budget.setBudgetNonFederalEquipmentSecondYearAmount(equipmentCostCS.bigDecimalValue());

                totalCategoryCS = totalCategoryCS.add(equipmentCostCS);
                totalEquip = totalEquip.add(equipmentCost);
                totalEquipCS = totalEquipCS.add(equipmentCostCS);

                // Contractual
                getContractualCosts(budgetPeriod);
                ed524Budget.setBudgetFederalContractualSecondYearAmount(contractualCost.bigDecimalValue());
                ed524Budget.setBudgetNonFederalContractualSecondYearAmount(contractualCostCS.bigDecimalValue());

                totalCategoryCS = totalCategoryCS.add(contractualCostCS);
                totalContractual = totalContractual.add(contractualCost);
                totalContractualCS = totalContractualCS.add(contractualCostCS);

                // Travel
                getTravelCosts(budgetPeriod);
                ed524Budget.setBudgetFederalTravelSecondYearAmount(travelCost.bigDecimalValue());
                ed524Budget.setBudgetNonFederalTravelSecondYearAmount(travelCostCS.bigDecimalValue());

                totalCategoryCS = totalCategoryCS.add(travelCostCS);
                totalTravel = totalTravel.add(travelCost);
                totalTravelCS = totalTravelCS.add(travelCostCS);

                // Training
                ed524Budget.setBudgetFederalTrainingSecondYearAmount(trainingCost.bigDecimalValue());
                ed524Budget.setBudgetNonFederalTrainingSecondYearAmount(trainingCostCS.bigDecimalValue());

                totalTraining = totalTraining.add(trainingCost);
                totalTrainingCS = totalTrainingCS.add(trainingCostCS);

                // Fringe
                ed524Budget.setBudgetFederalFringeSecondYearAmount(categoryCostFringe.bigDecimalValue());
                ed524Budget.setBudgetNonFederalFringeSecondYearAmount(categoryCostCSFringe.bigDecimalValue());

                totalCategoryCS = totalCategoryCS.add(categoryCostCSFringe);
                totalFringe = totalFringe.add(categoryCostFringe);
                totalFringeCS = totalFringeCS.add(categoryCostCSFringe);

                // Personnel
                getPersonnelCosts(budgetPeriod);
                ed524Budget.setBudgetFederalPersonnelSecondYearAmount(personnelCost.bigDecimalValue());
                ed524Budget.setBudgetNonFederalPersonnelSecondYearAmount(personnelCostCS.bigDecimalValue());

                totalCategoryCS = totalCategoryCS.add(personnelCostCS);
                totalPersonnel = totalPersonnel.add(personnelCost);
                totalPersonnelCS = totalPersonnelCS.add(personnelCostCS);

                ed524Budget.setBudgetNonFederalDirectSecondYearAmount(totalCategoryCS.bigDecimalValue());
            }
            else if (budgetPeriod.getBudgetPeriod().equals(BudgetPeriodNum.P3.getNum())) {
                totalCategoryCS = ScaleTwoDecimal.ZERO;
                getTotalCosts(budgetPeriod);
                ed524Budget.setBudgetFederalThirdYearAmount(totalCost.bigDecimalValue());
                ed524Budget.setBudgetNonFederalThirdYearAmount(totalCostSharing.bigDecimalValue());
                ed524Budget.setBudgetFederalDirectThirdYearAmount(totalDirectCost.bigDecimalValue());
                ed524Budget.setBudgetFederalIndirectThirdYearAmount(totalIndirectCost.bigDecimalValue());

                totalCostAllYrs = totalCostAllYrs.add(totalCost);
                totalCostSharingAllYrs = totalCostSharingAllYrs.add(totalCostSharing);
                totalDirectCostAllYrs = totalDirectCostAllYrs.add(totalDirectCost);
                totalIndirectCostAllYrs = totalIndirectCostAllYrs.add(totalIndirectCost);

                // Total Indirect Cost Sharing
                getIndirectCosts(budgetPeriod);
                ed524Budget.setBudgetNonFederalIndirectThirdYearAmount(indirectCS.bigDecimalValue());
                totalIndirectCostAllYrsCS = totalIndirectCostAllYrsCS.add(indirectCS);

                // Supplies
                getSuppliesCosts(budgetPeriod);
                ed524Budget.setBudgetFederalSuppliesThirdYearAmount(supplyCost.bigDecimalValue());
                ed524Budget.setBudgetNonFederalSuppliesThirdYearAmount(supplyCostCS.bigDecimalValue());

                totalCategoryCS = totalCategoryCS.add(supplyCostCS);
                totalSupplies = totalSupplies.add(supplyCost);
                totalSuppliesCS = totalSuppliesCS.add(supplyCostCS);

                // Construction
                // Set to zero as it set hard coded as 0 in the procedure
                ed524Budget.setBudgetFederalConstructionThirdYearAmount(ScaleTwoDecimal.ZERO.bigDecimalValue());
                ed524Budget.setBudgetNonFederalConstructionThirdYearAmount(ScaleTwoDecimal.ZERO.bigDecimalValue());


                // Other
                getOtherCosts(budgetPeriod);
                if (otherCost.longValue() < 0) {
                    ed524Budget.setBudgetFederalOtherThirdYearAmount(BigDecimal.ZERO);
                }
                else {
                    ed524Budget.setBudgetFederalOtherThirdYearAmount(otherCost.bigDecimalValue());
                }
                ed524Budget.setBudgetNonFederalOtherThirdYearAmount(otherCostCS.bigDecimalValue());

                totalCategoryCS = totalCategoryCS.add(otherCostCS);
                totalOther = totalOther.add(otherCost);
                totalOtherCS = totalOtherCS.add(otherCostCS);

                // Equipment
                getEquipmentCosts(budgetPeriod);
                ed524Budget.setBudgetFederalEquipmentThirdYearAmount(equipmentCost.bigDecimalValue());
                ed524Budget.setBudgetNonFederalEquipmentThirdYearAmount(equipmentCostCS.bigDecimalValue());

                totalCategoryCS = totalCategoryCS.add(equipmentCostCS);
                totalEquip = totalEquip.add(equipmentCost);
                totalEquipCS = totalEquipCS.add(equipmentCostCS);

                // Contractual
                getContractualCosts(budgetPeriod);
                ed524Budget.setBudgetFederalContractualThirdYearAmount(contractualCost.bigDecimalValue());
                ed524Budget.setBudgetNonFederalContractualThirdYearAmount(contractualCostCS.bigDecimalValue());

                totalCategoryCS = totalCategoryCS.add(contractualCostCS);
                totalContractual = totalContractual.add(contractualCost);
                totalContractualCS = totalContractualCS.add(contractualCostCS);

                // Travel
                getTravelCosts(budgetPeriod);
                ed524Budget.setBudgetFederalTravelThirdYearAmount(travelCost.bigDecimalValue());
                ed524Budget.setBudgetNonFederalTravelThirdYearAmount(travelCostCS.bigDecimalValue());

                totalCategoryCS = totalCategoryCS.add(travelCostCS);
                totalTravel = totalTravel.add(travelCost);
                totalTravelCS = totalTravelCS.add(travelCostCS);

                // Training
                ed524Budget.setBudgetFederalTrainingThirdYearAmount(trainingCost.bigDecimalValue());
                ed524Budget.setBudgetNonFederalTrainingThirdYearAmount(trainingCostCS.bigDecimalValue());

                totalTraining = totalTraining.add(trainingCost);
                totalTrainingCS = totalTrainingCS.add(trainingCostCS);

                // Fringe
                ed524Budget.setBudgetFederalFringeThirdYearAmount(categoryCostFringe.bigDecimalValue());
                ed524Budget.setBudgetNonFederalFringeThirdYearAmount(categoryCostCSFringe.bigDecimalValue());

                totalCategoryCS = totalCategoryCS.add(categoryCostCSFringe);
                totalFringe = totalFringe.add(categoryCostFringe);
                totalFringeCS = totalFringeCS.add(categoryCostCSFringe);

                // Personnel
                getPersonnelCosts(budgetPeriod);
                ed524Budget.setBudgetFederalPersonnelThirdYearAmount(personnelCost.bigDecimalValue());
                ed524Budget.setBudgetNonFederalPersonnelThirdYearAmount(personnelCostCS.bigDecimalValue());

                totalCategoryCS = totalCategoryCS.add(personnelCostCS);
                totalPersonnel = totalPersonnel.add(personnelCost);
                totalPersonnelCS = totalPersonnelCS.add(personnelCostCS);

                ed524Budget.setBudgetNonFederalDirectThirdYearAmount(totalCategoryCS.bigDecimalValue());
            }
            else if (budgetPeriod.getBudgetPeriod().equals(BudgetPeriodNum.P4.getNum())) {
                totalCategoryCS = ScaleTwoDecimal.ZERO;
                getTotalCosts(budgetPeriod);
                ed524Budget.setBudgetFederalFourthYearAmount(totalCost.bigDecimalValue());
                ed524Budget.setBudgetNonFederalFourthYearAmount(totalCostSharing.bigDecimalValue());
                ed524Budget.setBudgetFederalDirectFourthYearAmount(totalDirectCost.bigDecimalValue());
                ed524Budget.setBudgetFederalIndirectFourthYearAmount(totalIndirectCost.bigDecimalValue());

                totalCostAllYrs = totalCostAllYrs.add(totalCost);
                totalCostSharingAllYrs = totalCostSharingAllYrs.add(totalCostSharing);
                totalDirectCostAllYrs = totalDirectCostAllYrs.add(totalDirectCost);
                totalIndirectCostAllYrs = totalIndirectCostAllYrs.add(totalIndirectCost);

                // Total Indirect Cost Sharing
                getIndirectCosts(budgetPeriod);
                ed524Budget.setBudgetNonFederalIndirectFourthYearAmount(indirectCS.bigDecimalValue());
                totalIndirectCostAllYrsCS = totalIndirectCostAllYrsCS.add(indirectCS);

                // Supplies
                getSuppliesCosts(budgetPeriod);
                ed524Budget.setBudgetFederalSuppliesFourthYearAmount(supplyCost.bigDecimalValue());
                ed524Budget.setBudgetNonFederalSuppliesFourthYearAmount(supplyCostCS.bigDecimalValue());

                totalCategoryCS = totalCategoryCS.add(supplyCostCS);
                totalSupplies = totalSupplies.add(supplyCost);
                totalSuppliesCS = totalSuppliesCS.add(supplyCostCS);

                // Construction
                // Set to zero as it set hard coded as 0 in the procedure
                ed524Budget.setBudgetFederalConstructionFourthYearAmount(ScaleTwoDecimal.ZERO.bigDecimalValue());
                ed524Budget.setBudgetNonFederalConstructionFourthYearAmount(ScaleTwoDecimal.ZERO.bigDecimalValue());


                // Other
                getOtherCosts(budgetPeriod);
                ed524Budget.setBudgetFederalOtherFourthYearAmount(otherCost.bigDecimalValue());
                ed524Budget.setBudgetNonFederalOtherFourthYearAmount(otherCostCS.bigDecimalValue());

                totalCategoryCS = totalCategoryCS.add(otherCostCS);
                totalOther = totalOther.add(otherCost);
                totalOtherCS = totalOtherCS.add(otherCostCS);

                // Equipment
                getEquipmentCosts(budgetPeriod);
                ed524Budget.setBudgetFederalEquipmentFourthYearAmount(equipmentCost.bigDecimalValue());
                ed524Budget.setBudgetNonFederalEquipmentFourthYearAmount(equipmentCostCS.bigDecimalValue());

                totalCategoryCS = totalCategoryCS.add(equipmentCostCS);
                totalEquip = totalEquip.add(equipmentCost);
                totalEquipCS = totalEquipCS.add(equipmentCostCS);

                // Contractual
                getContractualCosts(budgetPeriod);
                ed524Budget.setBudgetFederalContractualFourthYearAmount(contractualCost.bigDecimalValue());
                ed524Budget.setBudgetNonFederalContractualFourthYearAmount(contractualCostCS.bigDecimalValue());

                totalCategoryCS = totalCategoryCS.add(contractualCostCS);
                totalContractual = totalContractual.add(contractualCost);
                totalContractualCS = totalContractualCS.add(contractualCostCS);

                // Travel
                getTravelCosts(budgetPeriod);
                ed524Budget.setBudgetFederalTravelFourthYearAmount(travelCost.bigDecimalValue());
                ed524Budget.setBudgetNonFederalTravelFourthYearAmount(travelCostCS.bigDecimalValue());

                totalCategoryCS = totalCategoryCS.add(travelCostCS);
                totalTravel = totalTravel.add(travelCost);
                totalTravelCS = totalTravelCS.add(travelCostCS);

                // Training
                ed524Budget.setBudgetFederalTrainingFourthYearAmount(trainingCost.bigDecimalValue());
                ed524Budget.setBudgetNonFederalTrainingFourthYearAmount(trainingCostCS.bigDecimalValue());

                totalTraining = totalTraining.add(trainingCost);
                totalTrainingCS = totalTrainingCS.add(trainingCostCS);

                // Fringe
                ed524Budget.setBudgetFederalFringeFourthYearAmount(categoryCostFringe.bigDecimalValue());
                ed524Budget.setBudgetNonFederalFringeFourthYearAmount(categoryCostCSFringe.bigDecimalValue());

                totalCategoryCS = totalCategoryCS.add(categoryCostCSFringe);
                totalFringe = totalFringe.add(categoryCostFringe);
                totalFringeCS = totalFringeCS.add(categoryCostCSFringe);

                // Personnel
                getPersonnelCosts(budgetPeriod);
                ed524Budget.setBudgetFederalPersonnelFourthYearAmount(personnelCost.bigDecimalValue());
                ed524Budget.setBudgetNonFederalPersonnelFourthYearAmount(personnelCostCS.bigDecimalValue());

                totalCategoryCS = totalCategoryCS.add(personnelCostCS);
                totalPersonnel = totalPersonnel.add(personnelCost);
                totalPersonnelCS = totalPersonnelCS.add(personnelCostCS);

                ed524Budget.setBudgetNonFederalDirectFourthYearAmount(totalCategoryCS.bigDecimalValue());
            }
            else if (budgetPeriod.getBudgetPeriod().equals(BudgetPeriodNum.P5.getNum())) {
                totalCategoryCS = ScaleTwoDecimal.ZERO;
                getTotalCosts(budgetPeriod);
                ed524Budget.setBudgetFederalFifthYearAmount(totalCost.bigDecimalValue());
                ed524Budget.setBudgetNonFederalFifthYearAmount(totalCostSharing.bigDecimalValue());
                ed524Budget.setBudgetFederalDirectFifthYearAmount(totalDirectCost.bigDecimalValue());
                ed524Budget.setBudgetFederalIndirectFifthYearAmount(totalIndirectCost.bigDecimalValue());

                totalCostAllYrs = totalCostAllYrs.add(totalCost);
                totalCostSharingAllYrs = totalCostSharingAllYrs.add(totalCostSharing);
                totalDirectCostAllYrs = totalDirectCostAllYrs.add(totalDirectCost);
                totalIndirectCostAllYrs = totalIndirectCostAllYrs.add(totalIndirectCost);

                // Total Indirect Cost Sharing
                getIndirectCosts(budgetPeriod);
                ed524Budget.setBudgetNonFederalIndirectFifthYearAmount(indirectCS.bigDecimalValue());
                totalIndirectCostAllYrsCS.add(indirectCS);

                // Supplies
                getSuppliesCosts(budgetPeriod);
                ed524Budget.setBudgetFederalSuppliesFifthYearAmount(supplyCost.bigDecimalValue());
                ed524Budget.setBudgetNonFederalSuppliesFifthYearAmount(supplyCostCS.bigDecimalValue());

                totalCategoryCS = totalCategoryCS.add(supplyCostCS);
                totalSupplies = totalSupplies.add(supplyCost);
                totalSuppliesCS = totalSuppliesCS.add(supplyCostCS);

                // Construction
                // Set to zero as it set hard coded as 0 in the procedure

                ed524Budget.setBudgetFederalConstructionFifthYearAmount(ScaleTwoDecimal.ZERO.bigDecimalValue());
                ed524Budget.setBudgetNonFederalConstructionFifthYearAmount(ScaleTwoDecimal.ZERO.bigDecimalValue());


                // Other
                getOtherCosts(budgetPeriod);
                ed524Budget.setBudgetFederalOtherFifthYearAmount(otherCost.bigDecimalValue());
                ed524Budget.setBudgetNonFederalOtherFifthYearAmount(otherCostCS.bigDecimalValue());

                totalCategoryCS = totalCategoryCS.add(otherCostCS);
                totalOther = totalOther.add(otherCost);
                totalOtherCS = totalOtherCS.add(otherCostCS);

                // Equipment
                getEquipmentCosts(budgetPeriod);
                ed524Budget.setBudgetFederalEquipmentFifthYearAmount(equipmentCost.bigDecimalValue());
                ed524Budget.setBudgetNonFederalEquipmentFifthYearAmount(equipmentCostCS.bigDecimalValue());

                totalCategoryCS = totalCategoryCS.add(equipmentCostCS);
                totalEquip = totalEquip.add(equipmentCost);
                totalEquipCS = totalEquipCS.add(equipmentCostCS);

                // Contractual
                getContractualCosts(budgetPeriod);
                ed524Budget.setBudgetFederalContractualFifthYearAmount(contractualCost.bigDecimalValue());
                ed524Budget.setBudgetNonFederalContractualFifthYearAmount(contractualCostCS.bigDecimalValue());

                totalCategoryCS = totalCategoryCS.add(contractualCostCS);
                totalContractual = totalContractual.add(contractualCost);
                totalContractualCS = totalContractualCS.add(contractualCostCS);

                // Travel
                getTravelCosts(budgetPeriod);
                ed524Budget.setBudgetFederalTravelFifthYearAmount(travelCost.bigDecimalValue());
                ed524Budget.setBudgetNonFederalTravelFifthYearAmount(travelCostCS.bigDecimalValue());

                totalCategoryCS = totalCategoryCS.add(travelCostCS);
                totalTravel = totalTravel.add(travelCost);
                totalTravelCS = totalTravelCS.add(travelCostCS);

                // Training
                ed524Budget.setBudgetFederalTrainingFifthYearAmount(trainingCost.bigDecimalValue());
                ed524Budget.setBudgetNonFederalTrainingFifthYearAmount(trainingCostCS.bigDecimalValue());

                totalTraining = totalTraining.add(trainingCost);
                totalTrainingCS = totalTrainingCS.add(trainingCostCS);

                // Fringe
                ed524Budget.setBudgetFederalFringeFifthYearAmount(categoryCostFringe.bigDecimalValue());
                ed524Budget.setBudgetNonFederalFringeFifthYearAmount(categoryCostCSFringe.bigDecimalValue());

                totalCategoryCS = totalCategoryCS.add(categoryCostCSFringe);
                totalFringe = totalFringe.add(categoryCostFringe);
                totalFringeCS = totalFringeCS.add(categoryCostCSFringe);

                // Personnel
                getPersonnelCosts(budgetPeriod);
                ed524Budget.setBudgetFederalPersonnelFifthYearAmount(personnelCost.bigDecimalValue());
                ed524Budget.setBudgetNonFederalPersonnelFifthYearAmount(personnelCostCS.bigDecimalValue());

                totalCategoryCS = totalCategoryCS.add(personnelCostCS);
                totalPersonnel = totalPersonnel.add(personnelCost);
                totalPersonnelCS = totalPersonnelCS.add(personnelCostCS);

                ed524Budget.setBudgetNonFederalDirectFifthYearAmount(totalCategoryCS.bigDecimalValue());
            }
        }// end of main for loop
        ed524Budget.setBudgetFederalPersonnelTotalAmount(totalPersonnel.bigDecimalValue());
        ed524Budget.setBudgetNonFederalPersonnelTotalAmount(totalPersonnelCS.bigDecimalValue());
        ed524Budget.setBudgetFederalFringeTotalAmount(totalFringe.bigDecimalValue());
        ed524Budget.setBudgetNonFederalFringeTotalAmount(totalFringeCS.bigDecimalValue());
        ed524Budget.setBudgetFederalTravelTotalAmount(totalTravel.bigDecimalValue());

        ed524Budget.setBudgetNonFederalTravelTotalAmount(totalTravelCS.bigDecimalValue());
        ed524Budget.setBudgetFederalEquipmentTotalAmount(totalEquip.bigDecimalValue());
        ed524Budget.setBudgetNonFederalEquipmentTotalAmount(totalEquipCS.bigDecimalValue());
        ed524Budget.setBudgetFederalContractualTotalAmount(totalContractual.bigDecimalValue());

        ed524Budget.setBudgetNonFederalContractualTotalAmount(totalContractualCS.bigDecimalValue());
        ed524Budget.setBudgetFederalSuppliesTotalAmount(totalSupplies.bigDecimalValue());
        ed524Budget.setBudgetNonFederalSuppliesTotalAmount(totalSuppliesCS.bigDecimalValue());
        ed524Budget.setBudgetFederalConstructionTotalAmount(totalConstruction.bigDecimalValue());

        ed524Budget.setBudgetNonFederalConstructionTotalAmount(totalConstructionCS.bigDecimalValue());
        ed524Budget.setBudgetFederalTrainingTotalAmount(totalTraining.bigDecimalValue());
        ed524Budget.setBudgetNonFederalTrainingTotalAmount(totalTrainingCS.bigDecimalValue());
        if (totalOther.longValue() < 0) {
            ed524Budget.setBudgetFederalOtherTotalAmount(BigDecimal.ZERO);
        }
        else {
            ed524Budget.setBudgetFederalOtherTotalAmount(totalOther.bigDecimalValue());
        }
        ed524Budget.setBudgetNonFederalOtherTotalAmount(totalOtherCS.bigDecimalValue());

        totalDirectCostAllYrsCS = totalPersonnelCS.add(totalFringeCS).add(totalTravelCS).add(totalEquipCS).add(totalContractualCS)
                .add(totalConstructionCS).add(totalSuppliesCS).add(totalOtherCS);
        ed524Budget.setBudgetNonFederalDirectTotalAmount(totalDirectCostAllYrsCS.bigDecimalValue());
        ed524Budget.setBudgetNonFederalIndirectTotalAmount(totalIndirectCostAllYrsCS.bigDecimalValue());
        ed524Budget.setBudgetFederalIndirectTotalAmount(totalIndirectCostAllYrs.bigDecimalValue());
        ed524Budget.setBudgetFederalTotalAmount(totalCostAllYrs.bigDecimalValue());

        ed524Budget.setBudgetFederalDirectTotalAmount(totalDirectCostAllYrs.bigDecimalValue());
        ed524Budget.setBudgetNonFederalTotalAmount(totalCostSharingAllYrs.bigDecimalValue());
        /***************************************************************************************************************************
         * ** INDIRECT COST INFO
         **************************************************************************************************************************/

        IndirectCost indirectCost = IndirectCost.Factory.newInstance();
        ApprovingFederalAgency approvingFederalAgency = ApprovingFederalAgency.Factory.newInstance();
        OtherApprovingFederalAgency otherAgency = OtherApprovingFederalAgency.Factory.newInstance();
        IndirectCostRateAgreementFromDate fromDate = IndirectCostRateAgreementFromDate.Factory.newInstance();
        String restrictedQuestion = RESTIRCTED_QUESTION;
        String agencyName = getAgencyName();
        String indirectCostRateAgreement = pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization().getIndirectCostRateAgreement();
        if (indirectCostRateAgreement != null && indirectCostRateAgreement.equals(INDIRECT_COST_RATE_AGREEMENT_NONE)) {
            approvingFederalAgency.setIsIndirectCostRateAgreementApproved(YesNoDataType.N_NO);
        }
        else {
            fromDate.setCalendarValue(s2SDateTimeService.convertDateStringToCalendar(indirectCostRateAgreement));
            fromDate.setIsIndirectCostRateAgreementApproved(YesNoDataType.Y_YES);
            if (fromDate.getCalendarValue() != null) {
                indirectCost.setIndirectCostRateAgreementFromDate(fromDate);
            }
            approvingFederalAgency.setIsIndirectCostRateAgreementApproved(YesNoDataType.Y_YES);
            if (agencyName.equals(APPROVING_FEDERAL_AGENCY_ED)) {
                approvingFederalAgency.setStringValue(APPROVING_FEDERAL_AGENCY_ED);
            }
            else {
                approvingFederalAgency.setStringValue(APPROVING_FEDERAL_AGENCY_OTHER);
                otherAgency.setApprovingFederalAgency(ApprovingFederalAgency.OTHER);
                otherAgency.setStringValue(agencyName);
                indirectCost.setOtherApprovingFederalAgency(otherAgency);
                indirectCost.setIsIndirectCostRateAgreementApproved(YesNoDataType.Y_YES);
                if (restrictedQuestion.length() > 1) {
                    indirectCost.setRestrictedIndirectCostRate(RestrictedIndirectCostRate.COMPLIES_WITH_34_CFR_76_564_C_2);
                }
                indirectCost.setApprovingFederalAgency(approvingFederalAgency);
                ed524Budget.setIndirectCost(indirectCost);
            }
        }
        ed524BudgetDocument.setED524Budget(ed524Budget);
        return ed524BudgetDocument;
    }

    /**
     * This method creates {@link XmlObject} of type {@link ED524BudgetDocument} by populating data from the given
     * {@link ProposalDevelopmentDocumentContract}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocumentContract}
     */
    public XmlObject getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {

        this.pdDoc = proposalDevelopmentDocument;
        return getED524Budget();
    }
}
