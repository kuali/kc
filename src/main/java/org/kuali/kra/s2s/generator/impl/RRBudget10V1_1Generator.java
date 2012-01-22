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
package org.kuali.kra.s2s.generator.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import gov.grants.apply.forms.rrBudget10V11.RRBudget10Document;
import gov.grants.apply.forms.rrBudget10V11.RRBudget10Document.RRBudget10;
import gov.grants.apply.forms.rrBudget10V11.BudgetTypeDataType;
import gov.grants.apply.forms.rrBudget10V11.BudgetYearDataType;
import gov.grants.apply.forms.rrBudget10V11.BudgetYearDataType.Equipment;
import gov.grants.apply.forms.rrBudget10V11.BudgetYearDataType.IndirectCosts;
import gov.grants.apply.forms.rrBudget10V11.BudgetYearDataType.KeyPersons;
import gov.grants.apply.forms.rrBudget10V11.BudgetYearDataType.OtherDirectCosts;
import gov.grants.apply.forms.rrBudget10V11.BudgetYearDataType.OtherPersonnel;
import gov.grants.apply.forms.rrBudget10V11.BudgetYearDataType.ParticipantTraineeSupportCosts;
import gov.grants.apply.forms.rrBudget10V11.BudgetYearDataType.Travel;
import gov.grants.apply.forms.rrBudget10V11.BudgetYearDataType.Equipment.EquipmentList;
import gov.grants.apply.forms.rrBudget10V11.BudgetYearDataType.KeyPersons.KeyPerson;
import gov.grants.apply.forms.rrBudget10V11.BudgetYearDataType.OtherPersonnel.GraduateStudents;
import gov.grants.apply.forms.rrBudget10V11.BudgetYearDataType.OtherPersonnel.PostDocAssociates;
import gov.grants.apply.forms.rrBudget10V11.BudgetYearDataType.OtherPersonnel.SecretarialClerical;
import gov.grants.apply.forms.rrBudget10V11.BudgetYearDataType.OtherPersonnel.UndergraduateStudents;
import gov.grants.apply.forms.rrBudget10V11.BudgetYearDataType.ParticipantTraineeSupportCosts.Other;
import gov.grants.apply.forms.rrBudget10V11.RRBudget10Document.RRBudget10.BudgetSummary;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType.FileLocation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.generator.bo.AttachmentData;
import org.kuali.kra.s2s.generator.bo.BudgetPeriodInfo;
import org.kuali.kra.s2s.generator.bo.BudgetSummaryInfo;
import org.kuali.kra.s2s.generator.bo.CompensationInfo;
import org.kuali.kra.s2s.generator.bo.CostInfo;
import org.kuali.kra.s2s.generator.bo.IndirectCostDetails;
import org.kuali.kra.s2s.generator.bo.KeyPersonInfo;
import org.kuali.kra.s2s.generator.bo.OtherDirectCostInfo;
import org.kuali.kra.s2s.generator.bo.OtherPersonnelInfo;
import org.kuali.kra.s2s.util.S2SConstants;

/**
 * 
 * This class is to generate xml stream for grants.gov form RRBudget10-V1_1
 * ref schema namespace <code> http://apply.grants.gov/forms/RR_Budget10-V1.1</code>
 */
public class RRBudget10V1_1Generator extends RRBudgetBaseGenerator {

    private static final Log LOG = LogFactory.getLog(RRBudget10V1_1Generator.class);

    /**
     * This method returns RRBudget10Document object based on proposal development
     * document which contains the informations such as
     * DUNSID,OrganizationName,BudgetType,BudgetYear and BudgetSummary.
     * 
     * @return rrBudgetDocument {@link XmlObject} of type RRBudget10Document.
     */
    private RRBudget10Document getRRBudget10() {
        
        deleteAutoGenNarratives();
        RRBudget10Document rrBudgetDocument = RRBudget10Document.Factory
                .newInstance();
        RRBudget10 rrBudget = RRBudget10.Factory.newInstance();
        rrBudget.setFormVersion(S2SConstants.FORMVERSION_1_1);
        if (pdDoc.getDevelopmentProposal().getApplicantOrganization() != null) {
            rrBudget.setDUNSID(pdDoc.getDevelopmentProposal()
                    .getApplicantOrganization().getOrganization()
                    .getDunsNumber());
            rrBudget.setOrganizationName(pdDoc.getDevelopmentProposal()
                    .getApplicantOrganization().getOrganization()
                    .getOrganizationName());
        }
        rrBudget.setBudgetType(BudgetTypeDataType.PROJECT);

        List<BudgetPeriodInfo> budgetperiodList;
        BudgetSummaryInfo budgetSummary = null;
        try {
            budgetperiodList = s2sBudgetCalculatorService.getBudgetPeriods(pdDoc);
            budgetSummary = s2sBudgetCalculatorService.getBudgetInfo(pdDoc,budgetperiodList);
        } catch (S2SException e) {
            LOG.error(e.getMessage(), e);
            return rrBudgetDocument;
        }

        rrBudget.setBudgetSummary(getBudgetSummary(budgetSummary));
        
        for (BudgetPeriodInfo budgetPeriodData : budgetperiodList) {
            setBudgetYearDataType(rrBudget,budgetPeriodData);
        }
        AttachedFileDataType attachedFileDataType = AttachedFileDataType.Factory.newInstance();
        for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeTypeCode() != null
                    && Integer.parseInt(narrative.getNarrativeTypeCode()) == 132) {
                attachedFileDataType = getAttachedFileType(narrative);
                if (attachedFileDataType != null) {
                    break;
                }
            }
        }
        rrBudget.setBudgetJustificationAttachment(attachedFileDataType);
        rrBudgetDocument.setRRBudget10(rrBudget);
        return rrBudgetDocument;
    }


    /**
     * This method gets BudgetYearDataType details like
     * BudgetPeriodStartDate,BudgetPeriodEndDate,BudgetPeriod
     * KeyPersons,OtherPersonnel,TotalCompensation,Equipment,ParticipantTraineeSupportCosts,Travel,OtherDirectCosts
     * DirectCosts,IndirectCosts,CognizantFederalAgency,TotalCosts based on
     * BudgetPeriodInfo for the RRBudget10.
     * 
     * @param periodInfo
     *            (BudgetPeriodInfo) budget period entry.
     * @return BudgetYear1DataType corresponding to the BudgetSummaryInfo
     *         object.
     */
    private void setBudgetYearDataType(RRBudget10 rrBudget,BudgetPeriodInfo periodInfo) {

        BudgetYearDataType budgetYear = rrBudget.addNewBudgetYear();
        if (periodInfo != null) {
            budgetYear.setBudgetPeriodStartDate(s2sUtilService.convertDateToCalendar(periodInfo.getStartDate()));
            budgetYear.setBudgetPeriodEndDate(s2sUtilService.convertDateToCalendar(periodInfo.getEndDate()));
            budgetYear.setKeyPersons(getKeyPersons(periodInfo));
            budgetYear.setOtherPersonnel(getOtherPersonnel(periodInfo));
            if (periodInfo.getTotalCompensation() != null) {
                budgetYear.setTotalCompensation(periodInfo
                        .getTotalCompensation().bigDecimalValue());
            }
            budgetYear.setEquipment(getEquipment(periodInfo));
            budgetYear.setTravel(getTravel(periodInfo));
            budgetYear
                    .setParticipantTraineeSupportCosts(getParticipantTraineeSupportCosts(periodInfo));
            budgetYear.setOtherDirectCosts(getOtherDirectCosts(periodInfo));
            BigDecimal directCosts = periodInfo.getDirectCostsTotal()
                    .bigDecimalValue();
            budgetYear.setDirectCosts(directCosts);
            IndirectCosts indirectCosts = getIndirectCosts(periodInfo);
            if (indirectCosts != null) {
                budgetYear.setIndirectCosts(indirectCosts);
                budgetYear.setTotalCosts(periodInfo.getDirectCostsTotal().bigDecimalValue().add(indirectCosts.getTotalIndirectCosts()));
            }else{
                budgetYear.setTotalCosts(periodInfo.getDirectCostsTotal().bigDecimalValue());
            }
            budgetYear.setCognizantFederalAgency(periodInfo
                    .getCognizantFedAgency());
        }
    }

    /**
     * This method gets BudgetSummary details such as
     * CumulativeTotalFundsRequestedSeniorKeyPerson,CumulativeTotalFundsRequestedOtherPersonnel
     * CumulativeTotalNoOtherPersonnel,CumulativeTotalFundsRequestedPersonnel,CumulativeEquipments,CumulativeTravels
     * CumulativeTrainee,CumulativeOtherDirect,CumulativeTotalFundsRequestedDirectCosts,CumulativeTotalFundsRequestedIndirectCost
     * CumulativeTotalFundsRequestedDirectIndirectCosts and CumulativeFee based
     * on BudgetSummaryInfo for the RRBudget10.
     * 
     * @param budgetSummaryData
     *            (BudgetSummaryInfo) budget summary entry.
     * @return BudgetSummary details corresponding to the BudgetSummaryInfo
     *         object.
     */
    private BudgetSummary getBudgetSummary(BudgetSummaryInfo budgetSummaryData) {

        BudgetSummary budgetSummary = BudgetSummary.Factory.newInstance();
        OtherDirectCostInfo otherDirectCosts = null;
        if(budgetSummaryData!=null){  
            if(budgetSummaryData.getOtherDirectCosts()!=null && budgetSummaryData.getOtherDirectCosts().size() > 0 ){
                otherDirectCosts = budgetSummaryData.getOtherDirectCosts().get(0);
            } 
            if(otherDirectCosts!=null){   
        
                budgetSummary.setCumulativeTotalFundsRequestedSeniorKeyPerson(BigDecimal.ZERO);
                budgetSummary.setCumulativeTotalFundsRequestedPersonnel(BigDecimal.ZERO);
        
                if (budgetSummaryData.getCumTotalFundsForSrPersonnel() != null) {
                    budgetSummary
                            .setCumulativeTotalFundsRequestedSeniorKeyPerson(budgetSummaryData
                                    .getCumTotalFundsForSrPersonnel().bigDecimalValue());
                }
                if (budgetSummaryData.getCumTotalFundsForOtherPersonnel() != null) {
                    budgetSummary
                            .setCumulativeTotalFundsRequestedOtherPersonnel(budgetSummaryData
                                    .getCumTotalFundsForOtherPersonnel()
                                    .bigDecimalValue());
                }
                if (budgetSummaryData.getCumNumOtherPersonnel() != null) {
                    budgetSummary.setCumulativeTotalNoOtherPersonnel(budgetSummaryData
                            .getCumNumOtherPersonnel().intValue());
                }
                if (budgetSummaryData.getCumTotalFundsForPersonnel() != null) {
                    budgetSummary
                            .setCumulativeTotalFundsRequestedPersonnel(budgetSummaryData
                                    .getCumTotalFundsForPersonnel().bigDecimalValue());
                }
                budgetSummary.setCumulativeTotalFundsRequestedEquipment(budgetSummaryData.getCumEquipmentFunds().bigDecimalValue());
                budgetSummary.setCumulativeTotalFundsRequestedTravel(budgetSummaryData.getCumTravel().bigDecimalValue());
                budgetSummary.setCumulativeDomesticTravelCosts(budgetSummaryData.getCumDomesticTravel().bigDecimalValue());
                budgetSummary.setCumulativeForeignTravelCosts(budgetSummaryData.getCumForeignTravel().bigDecimalValue());
                budgetSummary.setCumulativeTotalFundsRequestedTraineeCosts(otherDirectCosts.getParticipantTotal().bigDecimalValue());
                budgetSummary.setCumulativeTraineeStipends(otherDirectCosts.getPartStipends().bigDecimalValue());
                budgetSummary.setCumulativeTraineeSubsistence(otherDirectCosts.getPartSubsistence().bigDecimalValue());
                budgetSummary.setCumulativeTraineeTravel(otherDirectCosts.getPartTravel().bigDecimalValue());
                budgetSummary.setCumulativeTraineeTuitionFeesHealthInsurance(otherDirectCosts.getPartTuition().bigDecimalValue());
                budgetSummary.setCumulativeOtherTraineeCost(budgetSummaryData.getpartOtherCost().bigDecimalValue());
                budgetSummary.setCumulativeNoofTrainees(budgetSummaryData.getparticipantCount());
                budgetSummary.setCumulativeTotalFundsRequestedOtherDirectCosts(otherDirectCosts.gettotalOtherDirect().bigDecimalValue());
                budgetSummary.setCumulativeMaterialAndSupplies(otherDirectCosts.getmaterials().bigDecimalValue());
                budgetSummary.setCumulativePublicationCosts(otherDirectCosts.getpublications().bigDecimalValue());
                budgetSummary.setCumulativeConsultantServices(otherDirectCosts.getConsultants().bigDecimalValue());
                budgetSummary.setCumulativeADPComputerServices(otherDirectCosts.getcomputer().bigDecimalValue());
                budgetSummary.setCumulativeSubawardConsortiumContractualCosts(otherDirectCosts.getsubAwards().bigDecimalValue());
                budgetSummary.setCumulativeEquipmentFacilityRentalFees(otherDirectCosts.getEquipRental().bigDecimalValue());
                budgetSummary.setCumulativeAlterationsAndRenovations(otherDirectCosts.getAlterations().bigDecimalValue());
                List<Map<String,String>> cvOthers = otherDirectCosts.getOtherCosts();
                for (int j = 0; j < cvOthers.size(); j++) {
                    Map<String, String> hmCosts = cvOthers.get(j);
                    if (j==0){
                        budgetSummary.setCumulativeOther1DirectCost(new BigDecimal(hmCosts.get(S2SConstants.KEY_COST).toString()));
                       } else if (j==1) {
                           budgetSummary.setCumulativeOther2DirectCost(new BigDecimal(hmCosts.get(S2SConstants.KEY_COST).toString()));
                       } else {
                        budgetSummary.setCumulativeOther3DirectCost(new BigDecimal(hmCosts.get(S2SConstants.KEY_COST).toString()));
                     }
                }
                budgetSummary.setCumulativeTotalFundsRequestedDirectCosts(budgetSummaryData
                                .getCumTotalDirectCosts().bigDecimalValue());
                budgetSummary.setCumulativeTotalFundsRequestedIndirectCost(budgetSummaryData
                                .getCumTotalIndirectCosts().bigDecimalValue());
                budgetSummary.setCumulativeTotalFundsRequestedDirectIndirectCosts(budgetSummaryData
                                .getCumTotalCosts().bigDecimalValue());
                if (budgetSummaryData.getCumFee() != null) {
                    budgetSummary.setCumulativeFee(budgetSummaryData.getCumFee()
                            .bigDecimalValue());
                }
            }
        }
        return budgetSummary;
    }



    /**
     * This method gets ParticipantTraineeSupportCosts details in
     * BudgetYearDataType such as TuitionFeeHealthInsurance
     * Stipends,Subsistence,Travel,Other,ParticipantTraineeNumber and TotalCost
     * based on the BudgetPeriodInfo for the RRBudget10.
     * 
     * @param periodInfo
     *            (BudgetPeriodInfo) budget period entry.
     * @return ParticipantTraineeSupportCosts corresponding to the
     *         BudgetPeriodInfo object.
     */
    private ParticipantTraineeSupportCosts getParticipantTraineeSupportCosts(
            BudgetPeriodInfo periodInfo) {

        ParticipantTraineeSupportCosts traineeSupportCosts = ParticipantTraineeSupportCosts.Factory
                .newInstance();
        if (periodInfo != null) {
            traineeSupportCosts.setTuitionFeeHealthInsurance(periodInfo
                    .getPartTuition().bigDecimalValue());
            traineeSupportCosts.setStipends(periodInfo.getpartStipendCost()
                    .bigDecimalValue());
            traineeSupportCosts.setTravel(periodInfo.getpartTravelCost()
                    .bigDecimalValue());
            traineeSupportCosts.setSubsistence(periodInfo.getPartSubsistence()
                    .bigDecimalValue());
            traineeSupportCosts.setOther(getOtherPTSupportCosts(periodInfo));
            traineeSupportCosts.setParticipantTraineeNumber(periodInfo
                    .getparticipantCount());
            traineeSupportCosts.setTotalCost(traineeSupportCosts.getTuitionFeeHealthInsurance()
                            .add(traineeSupportCosts.getStipends().add(traineeSupportCosts.getTravel()
                                 .add(traineeSupportCosts.getSubsistence().add(traineeSupportCosts.getOther().getCost())))));
        }
        return traineeSupportCosts;
    }

    /**
     * This method gets Other type description and total cost for
     * ParticipantTraineeSupportCosts based on BudgetPeriodInfo.
     * 
     * @param periodInfo
     *            (BudgetPeriodInfo) budget period entry.
     * @return Other other participant trainee support costs corresponding to
     *         the BudgetPeriodInfo object.
     */
    private Other getOtherPTSupportCosts(BudgetPeriodInfo periodInfo) {
        Other other = Other.Factory.newInstance();
        other.setDescription(OTHERCOST_DESCRIPTION);
        BudgetDecimal otherCost = BudgetDecimal.ZERO;
        if (periodInfo != null && periodInfo.getpartOtherCost() != null) {
            otherCost = periodInfo.getpartOtherCost();
        }
        other.setCost(otherCost.bigDecimalValue());
        return other;
    }

    /**
     * This method gets OtherDirectCosts details such as
     * PublicationCosts,MaterialsSupplies,ConsultantServices,
     * ADPComputerServices,SubawardConsortiumContractualCosts,EquipmentRentalFee,AlterationsRenovations
     * and TotalOtherDirectCost in BudgetYearDataType based on BudgetPeriodInfo
     * for the RRBudget10.
     * 
     * @param periodInfo
     *            (BudgetPeriodInfo) budget period entry.
     * @return OtherDirectCosts corresponding to the BudgetPeriodInfo object.
     */
    private OtherDirectCosts getOtherDirectCosts(BudgetPeriodInfo periodInfo) {

        OtherDirectCosts otherDirectCosts = OtherDirectCosts.Factory
                .newInstance();
        if (periodInfo != null && periodInfo.getOtherDirectCosts().size() > 0) {
            if (periodInfo.getOtherDirectCosts().get(0).getpublications() != null) {
                otherDirectCosts.setPublicationCosts(periodInfo
                        .getOtherDirectCosts().get(0).getpublications()
                        .bigDecimalValue());
            }
            if (periodInfo.getOtherDirectCosts().get(0).getmaterials() != null) {
                otherDirectCosts.setMaterialsSupplies(periodInfo
                        .getOtherDirectCosts().get(0).getmaterials()
                        .bigDecimalValue());
            }
            if (periodInfo.getOtherDirectCosts().get(0).getConsultants() != null) {
                otherDirectCosts.setConsultantServices(periodInfo
                        .getOtherDirectCosts().get(0).getConsultants()
                        .bigDecimalValue());
            }
            if (periodInfo.getOtherDirectCosts().get(0).getcomputer() != null) {
                otherDirectCosts.setADPComputerServices(periodInfo
                        .getOtherDirectCosts().get(0).getcomputer()
                        .bigDecimalValue());
            }
            if (periodInfo.getOtherDirectCosts().get(0).getsubAwards() != null) {
                otherDirectCosts
                        .setSubawardConsortiumContractualCosts(periodInfo
                                .getOtherDirectCosts().get(0).getsubAwards()
                                .bigDecimalValue());
            }
            if (periodInfo.getOtherDirectCosts().get(0).getAlterations() != null) {
                otherDirectCosts.setAlterationsRenovations(periodInfo
                        .getOtherDirectCosts().get(0).getAlterations()
                        .bigDecimalValue());
            }
            if (periodInfo.getOtherDirectCosts().get(0).getEquipRental() != null) {
                otherDirectCosts.setEquipmentRentalFee(periodInfo
                        .getOtherDirectCosts().get(0).getEquipRental()
                        .bigDecimalValue());
            }
            setOthersForOtherDirectCosts(otherDirectCosts,periodInfo);
            if (periodInfo.getOtherDirectCosts().get(0).gettotalOtherDirect() != null) {
                otherDirectCosts.setTotalOtherDirectCost(periodInfo
                        .getOtherDirectCosts().get(0).gettotalOtherDirect()
                        .bigDecimalValue());
            }
        }
        return otherDirectCosts;
    }

    /**
     * This method returns IndirectCosts details such as
     * Base,CostType,FundRequested,Rate and TotalIndirectCosts in
     * BudgetYearDataType based on BudgetPeriodInfo for the RRBudget10.
     * 
     * @param periodInfo
     *            (BudgetPeriodInfo) budget period entry.
     * @return IndirectCosts corresponding to the BudgetPeriodInfo object.
     */
    private IndirectCosts getIndirectCosts(BudgetPeriodInfo periodInfo) {

        IndirectCosts indirectCosts = null;

        if (periodInfo != null
                && periodInfo.getIndirectCosts() != null
                && periodInfo.getIndirectCosts().getIndirectCostDetails() != null) {

            List<IndirectCosts.IndirectCost> indirectCostList = new ArrayList<IndirectCosts.IndirectCost>();
            int IndirectCostCount = 0;
            for (IndirectCostDetails indirectCostDetails : periodInfo
                    .getIndirectCosts().getIndirectCostDetails()) {
                IndirectCosts.IndirectCost indirectCost = IndirectCosts.IndirectCost.Factory
                        .newInstance();
                if (indirectCostDetails.getBase() != null) {
                    indirectCost.setBase(indirectCostDetails.getBase()
                            .bigDecimalValue());
                }
                indirectCost.setCostType(indirectCostDetails.getCostType());
                if (indirectCostDetails.getFunds() != null) {
                    indirectCost.setFundRequested(indirectCostDetails
                            .getFunds().bigDecimalValue());
                }
                if (indirectCostDetails.getRate() != null) {
                    indirectCost.setRate(indirectCostDetails.getRate()
                            .bigDecimalValue());
                }
                indirectCostList.add(indirectCost);
                IndirectCostCount++;
                if (IndirectCostCount == ARRAY_LIMIT_IN_SCHEMA) {
                    LOG
                            .warn("Stopping iteration over indirect cost details because array limit in schema is only 4");
                    break;
                }
            }
            if (IndirectCostCount > 0) {
                indirectCosts = IndirectCosts.Factory.newInstance();
                IndirectCosts.IndirectCost indirectCostArray[] = new IndirectCosts.IndirectCost[0];
                indirectCosts.setIndirectCostArray(indirectCostList
                        .toArray(indirectCostArray));
                if (periodInfo.getIndirectCosts().getTotalIndirectCosts() != null) {
                    indirectCosts.setTotalIndirectCosts(periodInfo
                            .getIndirectCosts().getTotalIndirectCosts()
                            .bigDecimalValue());
                }
            }
        }
        return indirectCosts;
    }

    /**
     * This method is to set Other type description and total cost
     * OtherDirectCosts details in BudgetYearDataType based on BudgetPeriodInfo
     * for the RRBudget10.
     * 
     * @param OtherDirectCosts otherDirectCosts xmlObject
     * @param periodInfo
     *            (BudgetPeriodInfo) budget period entry.
     */
    private void setOthersForOtherDirectCosts(OtherDirectCosts otherDirectCosts, BudgetPeriodInfo periodInfo) {
        if (periodInfo != null && periodInfo.getOtherDirectCosts() != null) {
            for (OtherDirectCostInfo otherDirectCostInfo : periodInfo.getOtherDirectCosts()) {
                gov.grants.apply.forms.rrBudget10V11.BudgetYearDataType.OtherDirectCosts.Other other = otherDirectCosts.addNewOther();
                if (otherDirectCostInfo.getOtherCosts() != null
                        && otherDirectCostInfo.getOtherCosts().size() > 0) {
                    other
                            .setCost(new BigDecimal(otherDirectCostInfo
                                    .getOtherCosts().get(0).get(
                                            S2SConstants.KEY_COST)));
                }
                other.setDescription(OTHERCOST_DESCRIPTION);
            }
        }
    }

    /**
     * This method gets Travel cost information including
     * DomesticTravelCost,ForeignTravelCost and TotalTravelCost in the
     * BudgetYearDataType based on BudgetPeriodInfo for the RRBudget10.
     * 
     * @param periodInfo
     *            (BudgetPeriodInfo) budget period entry.
     * @return Travel costs corresponding to the BudgetPeriodInfo object.
     */
    private Travel getTravel(BudgetPeriodInfo periodInfo) {

        Travel travel = Travel.Factory.newInstance();
        if (periodInfo != null) {
            travel.setDomesticTravelCost(periodInfo.getDomesticTravelCost()
                    .bigDecimalValue());
            travel.setForeignTravelCost(periodInfo.getForeignTravelCost()
                    .bigDecimalValue());
            travel.setTotalTravelCost(periodInfo.getTotalTravelCost()
                    .bigDecimalValue());
        }
        return travel;
    }

    /**
     * This method gets Equipment details such as
     * EquipmentItem,FundsRequested,TotalFundForAttachedEquipment, TotalFund and
     * AdditionalEquipmentsAttachment based on BudgetPeriodInfo for the
     * RRBudget10.
     * 
     * @param periodInfo
     *            (BudgetPeriodInfo) budget period entry.
     * @return Equipment costs corresponding to the BudgetPeriodInfo object.
     * 
     */
    private Equipment getEquipment(BudgetPeriodInfo periodInfo) {
        Equipment equipment = Equipment.Factory.newInstance();
        Narrative extraEquipmentNarr = null;
        if (periodInfo != null && periodInfo.getEquipment() != null
                && periodInfo.getEquipment().size() > 0) {
            // Evaluating Equipments.
            List<EquipmentList> equipmentArrayList = new ArrayList<EquipmentList>();
            BudgetDecimal totalFund = BudgetDecimal.ZERO;
            for (CostInfo costInfo : periodInfo.getEquipment().get(0)
                    .getEquipmentList()) {
                EquipmentList equipmentList = EquipmentList.Factory.newInstance();
                equipmentList.setEquipmentItem(costInfo.getDescription());
                if (costInfo.getCost() != null) {
                    equipmentList.setFundsRequested(costInfo.getCost().bigDecimalValue());
                }
                totalFund = totalFund.add(costInfo.getCost());
                equipmentArrayList.add(equipmentList);
            }

            // Evaluating Extra Equipments.
            List<CostInfo> extraEquipmentArrayList = new ArrayList<CostInfo>();
            BudgetDecimal totalExtraEquipFund = BudgetDecimal.ZERO;
            for(CostInfo costInfo:periodInfo.getEquipment().get(0).getExtraEquipmentList()){
                extraEquipmentArrayList.add(costInfo);
                totalExtraEquipFund = totalExtraEquipFund.add(costInfo.getCost());
            }
            EquipmentList[] equipmentArray = new EquipmentList[0];
            equipmentArray = equipmentArrayList.toArray(equipmentArray);
            equipment.setEquipmentListArray(equipmentArray);
            totalFund = totalFund.add(totalExtraEquipFund);
            equipment.setTotalFund(totalFund.bigDecimalValue());
            if (equipmentArray.length > 0) {
                equipment.setTotalFundForAttachedEquipment(totalExtraEquipFund.bigDecimalValue());
            }
            extraEquipmentNarr = saveAdditionalEquipments(periodInfo,extraEquipmentArrayList);
        }
        if(extraEquipmentNarr!=null){
            AttachedFileDataType equipmentAttachment = AttachedFileDataType.Factory.newInstance();
            FileLocation fileLocation = FileLocation.Factory.newInstance();
            equipmentAttachment.setFileLocation(fileLocation);
            String contentId = createContentId(extraEquipmentNarr);
            fileLocation.setHref(contentId);
            equipmentAttachment.setFileLocation(fileLocation);
            equipmentAttachment.setFileName(extraEquipmentNarr.getFileName());
            equipmentAttachment
                    .setMimeType(S2SConstants.CONTENT_TYPE_OCTET_STREAM);
            extraEquipmentNarr.refreshReferenceObject(NARRATIVE_ATTACHMENT_LIST);
            if (extraEquipmentNarr.getNarrativeAttachmentList() != null
                    && extraEquipmentNarr.getNarrativeAttachmentList().size() > 0) {
                equipmentAttachment.setHashValue(getHashValue(extraEquipmentNarr
                        .getNarrativeAttachmentList().get(0).getContent()));
            }
            AttachmentData attachmentData = new AttachmentData();
            attachmentData.setContent(extraEquipmentNarr
                    .getNarrativeAttachmentList().get(0).getContent());
            attachmentData.setContentId(contentId);
            attachmentData.setContentType(S2SConstants.CONTENT_TYPE_OCTET_STREAM);
            attachmentData.setFileName(extraEquipmentNarr.getFileName());
            addAttachment(attachmentData);
            equipment.setAdditionalEquipmentsAttachment(equipmentAttachment);
        }
        return equipment;
    }

    /**
     * This method gets OtherPersonnel informations like
     * PostDocAssociates,GraduateStudents,UndergraduateStudents
     * SecretarialClerical based on PersonnelType and also gets
     * NumberOfPersonnel, ProjectRole,Compensation OtherPersonnelTotalNumber and
     * TotalOtherPersonnelFund based on BudgetPeriodInfo for the RRBudget10.
     * 
     * @param periodInfo
     *            (BudgetPeriodInfo) budget period entry.
     * @return OtherPersonnel details corresponding to the BudgetPeriodInfo
     *         object.
     */
    private OtherPersonnel getOtherPersonnel(BudgetPeriodInfo periodInfo) {
        OtherPersonnel otherPersonnel = OtherPersonnel.Factory.newInstance();
        int otherPersonnelCount = 0;
        List<gov.grants.apply.forms.rrBudget10V11.BudgetYearDataType.OtherPersonnel.Other> otherPersonnelList = new ArrayList<gov.grants.apply.forms.rrBudget10V11.BudgetYearDataType.OtherPersonnel.Other>();
        gov.grants.apply.forms.rrBudget10V11.BudgetYearDataType.OtherPersonnel.Other otherPersonnelDataTypeArray[] = new gov.grants.apply.forms.rrBudget10V11.BudgetYearDataType.OtherPersonnel.Other[1];
        if (periodInfo != null) {
            for (OtherPersonnelInfo otherPersonnelInfo : periodInfo
                    .getOtherPersonnel()) {
                
                if (OTHERPERSONNEL_POSTDOC.equals(otherPersonnelInfo
                        .getPersonnelType())) {
                    otherPersonnel
                            .setPostDocAssociates(getPostDocAssociates(otherPersonnelInfo));
                } else if (OTHERPERSONNEL_GRADUATE.equals(otherPersonnelInfo
                        .getPersonnelType())) {
                    otherPersonnel
                            .setGraduateStudents(getGraduateStudents(otherPersonnelInfo));
                } else if (OTHERPERSONNEL_UNDERGRADUATE
                        .equals(otherPersonnelInfo.getPersonnelType())) {
                    otherPersonnel
                            .setUndergraduateStudents(getUndergraduateStudents(otherPersonnelInfo));
                } else if (OTHERPERSONNEL_SECRETARIAL.equals(otherPersonnelInfo
                        .getPersonnelType())) {
                    otherPersonnel.setSecretarialClerical(getSecretarialClerical(otherPersonnelInfo));
                } else if (otherPersonnelCount < OTHERPERSONNEL_MAX_ALLOWED) {// Max
                    // allowed
                    // is 6
                    CompensationInfo sectBCompType = otherPersonnelInfo.getCompensation();
                    gov.grants.apply.forms.rrBudget10V11.BudgetYearDataType.OtherPersonnel.Other otherPersonnelDataType = otherPersonnel.addNewOther();
                    otherPersonnelDataType.setNumberOfPersonnel(otherPersonnelInfo.getNumberPersonnel());
                    otherPersonnelDataType.setProjectRole(otherPersonnelInfo.getRole());
                    otherPersonnelDataType.setRequestedSalary(sectBCompType.getRequestedSalary().bigDecimalValue());
                    otherPersonnelDataType.setFringeBenefits(sectBCompType.getFringe().bigDecimalValue());
                    otherPersonnelDataType.setAcademicMonths(sectBCompType.getAcademicMonths().bigDecimalValue());
                    otherPersonnelDataType.setCalendarMonths(sectBCompType.getCalendarMonths().bigDecimalValue());
                    otherPersonnelDataType.setFundsRequested(sectBCompType.getFundsRequested().bigDecimalValue());
                    otherPersonnelDataType.setSummerMonths(sectBCompType.getSummerMonths().bigDecimalValue());
                    otherPersonnelList.add(otherPersonnelDataType);
                    otherPersonnelCount++;
                }
            }
            otherPersonnelDataTypeArray = otherPersonnelList.toArray(otherPersonnelDataTypeArray);
            otherPersonnel.setOtherArray(otherPersonnelDataTypeArray);

            if (periodInfo.getOtherPersonnelTotalNumber() != null) {
                otherPersonnel.setOtherPersonnelTotalNumber(periodInfo
                        .getOtherPersonnelTotalNumber().intValue());
            }
            if (periodInfo.getTotalOtherPersonnelFunds() != null) {
                otherPersonnel.setTotalOtherPersonnelFund(periodInfo
                        .getTotalOtherPersonnelFunds().bigDecimalValue());
            }
        }
        return otherPersonnel;
    }

    /**
     * This method gets the PostDocAssociates details,ProjectRole,
     * NumberOfPersonnel,Compensation based on OtherPersonnelInfo for the
     * RRBudget10,if it is a PostDocAssociates type.
     * 
     * @param otherPersonnel
     *            (OtherPersonnelInfo)other personnel info entry.
     * @return PostDocAssociates details corresponding to the OtherPersonnelInfo
     *         object.
     */
    private PostDocAssociates getPostDocAssociates(
            OtherPersonnelInfo otherPersonnel) {

        PostDocAssociates postDocAssociates = PostDocAssociates.Factory
                .newInstance();
        if (otherPersonnel != null) {
            postDocAssociates.setNumberOfPersonnel(otherPersonnel
                    .getNumberPersonnel());
            postDocAssociates.setProjectRole(otherPersonnel.getRole());
            CompensationInfo sectBCompType = otherPersonnel.getCompensation();

            postDocAssociates.setRequestedSalary(sectBCompType.getRequestedSalary().bigDecimalValue());
            postDocAssociates.setFringeBenefits(sectBCompType.getFringe().bigDecimalValue());
            postDocAssociates.setAcademicMonths(sectBCompType.getAcademicMonths().bigDecimalValue());
            postDocAssociates.setCalendarMonths(sectBCompType.getCalendarMonths().bigDecimalValue());
            postDocAssociates.setFundsRequested(sectBCompType.getFundsRequested().bigDecimalValue());
            postDocAssociates.setSummerMonths(sectBCompType.getSummerMonths().bigDecimalValue());

            
        }
        return postDocAssociates;
    }

    /**
     * This method gets the GraduateStudents details,ProjectRole,
     * NumberOfPersonnel,Compensation based on OtherPersonnelInfo for the
     * RRBudget10, if it is a GraduateStudents type.
     * 
     * @param otherPersonnel
     *            (OtherPersonnelInfo) other personnel info entry.
     * @return GraduateStudents details corresponding to the OtherPersonnelInfo
     *         object.
     */
    private GraduateStudents getGraduateStudents(
            OtherPersonnelInfo otherPersonnel) {

        GraduateStudents graduate = GraduateStudents.Factory.newInstance();
        if (otherPersonnel != null) {
            graduate.setNumberOfPersonnel(otherPersonnel.getNumberPersonnel());
            graduate.setProjectRole(otherPersonnel.getRole());
            CompensationInfo sectBCompType = otherPersonnel.getCompensation();

            graduate.setRequestedSalary(sectBCompType.getRequestedSalary().bigDecimalValue());
            graduate.setFringeBenefits(sectBCompType.getFringe().bigDecimalValue());
            graduate.setAcademicMonths(sectBCompType.getAcademicMonths().bigDecimalValue());
            graduate.setCalendarMonths(sectBCompType.getCalendarMonths().bigDecimalValue());
            graduate.setFundsRequested(sectBCompType.getFundsRequested().bigDecimalValue());
            graduate.setSummerMonths(sectBCompType.getSummerMonths().bigDecimalValue());

        }
        return graduate;
    }

    /**
     * This method is to get the UndergraduateStudents details,ProjectRole,
     * NumberOfPersonnel,Compensation based on OtherPersonnelInfo for the
     * RRBudget10,if it is a UndergraduateStudents type.
     * 
     * @param otherPersonnel
     *            (OtherPersonnelInfo) other personnel info entry.
     * @return UndergraduateStudents details corresponding to the
     *         OtherPersonnelInfo object.
     */
    private UndergraduateStudents getUndergraduateStudents(
            OtherPersonnelInfo otherPersonnel) {

        UndergraduateStudents undergraduate = UndergraduateStudents.Factory
                .newInstance();
        if (otherPersonnel != null) {
            undergraduate.setNumberOfPersonnel(otherPersonnel
                    .getNumberPersonnel());
            undergraduate.setProjectRole(otherPersonnel.getRole());
            CompensationInfo sectBCompType = otherPersonnel.getCompensation();

            undergraduate.setRequestedSalary(sectBCompType.getRequestedSalary().bigDecimalValue());
            undergraduate.setFringeBenefits(sectBCompType.getFringe().bigDecimalValue());
            undergraduate.setAcademicMonths(sectBCompType.getAcademicMonths().bigDecimalValue());
            undergraduate.setCalendarMonths(sectBCompType.getCalendarMonths().bigDecimalValue());
            undergraduate.setFundsRequested(sectBCompType.getFundsRequested().bigDecimalValue());
            undergraduate.setSummerMonths(sectBCompType.getSummerMonths().bigDecimalValue());

            
        }
        return undergraduate;
    }

    /**
     * This method is to get the SecretarialClerical details,ProjectRole,
     * NumberOfPersonnel,Compensation based on OtherPersonnelInfo for the
     * RRBudget10,if it is a SecretarialClerical type.
     * 
     * @param otherPersonnel
     *            (OtherPersonnelInfo) other personnel info entry.
     * @return SecretarialClerical corresponding to the OtherPersonnelInfo
     *         object.
     */
    private SecretarialClerical getSecretarialClerical(
            OtherPersonnelInfo otherPersonnel) {

        SecretarialClerical secretarialClerical = SecretarialClerical.Factory
                .newInstance();
        if (otherPersonnel != null) {
            secretarialClerical.setNumberOfPersonnel(otherPersonnel
                    .getNumberPersonnel());
            secretarialClerical.setProjectRole(otherPersonnel.getRole());
            CompensationInfo sectBCompType = otherPersonnel.getCompensation();

            secretarialClerical.setRequestedSalary(sectBCompType.getRequestedSalary().bigDecimalValue());
            secretarialClerical.setFringeBenefits(sectBCompType.getFringe().bigDecimalValue());
            secretarialClerical.setAcademicMonths(sectBCompType.getAcademicMonths().bigDecimalValue());
            secretarialClerical.setCalendarMonths(sectBCompType.getCalendarMonths().bigDecimalValue());
            secretarialClerical.setFundsRequested(sectBCompType.getFundsRequested().bigDecimalValue());
            secretarialClerical.setSummerMonths(sectBCompType.getSummerMonths().bigDecimalValue());
            
        }
        return secretarialClerical;
    }

    /**
     * This method gets KeyPersons details such as
     * Name,ProjectRole,Compensation,TotalFundForAttachedKeyPersons
     * TotalFundForKeyPersons and AttachedKeyPersons based on BudgetPeriodInfo
     * for the RRBudget10.
     * 
     * @param periodInfo
     *            (BudgetPeriodInfo) budget period entry.
     * @return KeyPersons details corresponding to the BudgetPeriodInfo object.
     */
    private KeyPersons getKeyPersons(BudgetPeriodInfo periodInfo) {

        KeyPersons keyPersons = KeyPersons.Factory.newInstance();
        BudgetDecimal extraFunds = BudgetDecimal.ZERO;
        if (periodInfo != null) {
            if (periodInfo.getKeyPersons() != null) {
                List<KeyPerson> keyPersonList = new ArrayList<KeyPerson>();
                int keyPersonCount = 0;
                for (KeyPersonInfo keyPerson : periodInfo.getKeyPersons()) {
                  if(keyPerson.getRole().equals(NID_PD_PI) || hasPersonnelBudget(keyPerson,periodInfo.getBudgetPeriod())){
                    KeyPerson keyPersonDataType = KeyPerson.Factory.newInstance();
                    keyPersonDataType.setName(globLibV20Generator
                            .getHumanNameDataType(keyPerson));
                    if (isSponsorNIH(pdDoc)
                            && KEYPERSON_CO_PD_PI.equals(keyPerson.getRole())) {
                        DevelopmentProposal developmentProposal=pdDoc.getDevelopmentProposal();     
                        
                        for (ProposalPerson proposalPerson : developmentProposal.getInvestigators()) {                        
                            if(isProposalPersonEqualsKeyPerson(proposalPerson, keyPerson)){   
                                if(proposalPerson.isMultiplePi())
                                    keyPersonDataType.setProjectRole(NID_PD_PI);                               
                                else 
                                    keyPersonDataType.setProjectRole(NID_CO_PD_PI);                                                             
                            }
                        }       
                    } else if(keyPerson.getKeyPersonRole()!=null){
                        keyPersonDataType.setProjectRole(keyPerson.getKeyPersonRole());
                    } else {
                        keyPersonDataType.setProjectRole(keyPerson.getRole());
                    }

                    keyPersonDataType.setRequestedSalary(keyPerson.getRequestedSalary().bigDecimalValue());
                    keyPersonDataType.setBaseSalary(keyPerson.getBaseSalary().bigDecimalValue());
                    keyPersonDataType.setFringeBenefits(keyPerson.getFringe().bigDecimalValue());
                    keyPersonDataType.setAcademicMonths(keyPerson.getAcademicMonths().bigDecimalValue());
                    keyPersonDataType.setCalendarMonths(keyPerson.getCalendarMonths().bigDecimalValue());
                    keyPersonDataType.setFundsRequested(keyPerson.getFundsRequested().bigDecimalValue());
                    keyPersonDataType.setSummerMonths(keyPerson.getSummerMonths().bigDecimalValue());

                    keyPersonList.add(keyPersonDataType);
                    keyPersonCount++;
                    LOG.info("keyPersonCount:" + keyPersonCount);
                }
            }
                keyPersons.setKeyPersonArray(keyPersonList.toArray(new KeyPerson[0]));
            }
            if (periodInfo.getTotalFundsKeyPersons() != null) {
                keyPersons.setTotalFundForKeyPersons(periodInfo
                        .getTotalFundsKeyPersons().bigDecimalValue());
            }
            for (KeyPersonInfo keyPerson : periodInfo.getExtraKeyPersons()) {
                extraFunds = extraFunds.add(keyPerson.getFundsRequested());
            }
        }
        keyPersons.setTotalFundForAttachedKeyPersons(extraFunds.bigDecimalValue());
        Narrative extraKeyPersonNarr = saveExtraKeyPersons(periodInfo);
        if(extraKeyPersonNarr!=null){
            AttachedFileDataType attachedKeyPersons = AttachedFileDataType.Factory.newInstance();
            FileLocation fileLocation = FileLocation.Factory.newInstance();
            attachedKeyPersons.setFileLocation(fileLocation);
            String contentId = createContentId(extraKeyPersonNarr);
            fileLocation.setHref(contentId);
            attachedKeyPersons.setFileLocation(fileLocation);
            attachedKeyPersons.setFileName(extraKeyPersonNarr.getFileName());
            attachedKeyPersons
                    .setMimeType(S2SConstants.CONTENT_TYPE_OCTET_STREAM);
            extraKeyPersonNarr.refreshReferenceObject(NARRATIVE_ATTACHMENT_LIST);
            AttachmentData attachmentData = new AttachmentData();
            byte[] narrativeContent = null;
            if (extraKeyPersonNarr.getNarrativeAttachmentList() != null
                    && extraKeyPersonNarr.getNarrativeAttachmentList().size() > 0) {
                narrativeContent = extraKeyPersonNarr
                .getNarrativeAttachmentList().get(0).getContent();
                
            }
            if(narrativeContent != null && narrativeContent.length > 0){
                attachedKeyPersons.setHashValue(getHashValue(narrativeContent));
                attachmentData.setContent(narrativeContent);
                attachmentData.setContentId(contentId);
                attachmentData
                        .setContentType(S2SConstants.CONTENT_TYPE_OCTET_STREAM);
                attachmentData.setFileName(extraKeyPersonNarr.getFileName());
                addAttachment(attachmentData);
                keyPersons.setAttachedKeyPersons(attachedKeyPersons);
            }
        }
        return keyPersons;
    }

    /**
     * This method creates {@link XmlObject} of type {@link RRBudget10Document} by
     * populating data from the given {@link ProposalDevelopmentDocument}
     * 
     * @param proposalDevelopmentDocument
     *            for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given
     *         {@link ProposalDevelopmentDocument}
     * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(ProposalDevelopmentDocument)
     */
    public XmlObject getFormObject(
            ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getRRBudget10();
    }

    /**
     * This method typecasts the given {@link XmlObject} to the required
     * generator type and returns back the document of that generator type.
     * 
     * @param xmlObject
     *            which needs to be converted to the document type of the
     *            required generator
     * @return {@link XmlObject} document of the required generator type
     * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(XmlObject)
     */
    public XmlObject getFormObject(XmlObject xmlObject) {
        RRBudget10 rrBudget = (RRBudget10) xmlObject;
        RRBudget10Document rrBudgetDocument = RRBudget10Document.Factory
                .newInstance();
        rrBudgetDocument.setRRBudget10(rrBudget);
        return rrBudgetDocument;
    }
}
