/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.coeus.s2sgen.impl.generate.support;

import gov.grants.apply.forms.rrBudget13V13.BudgetTypeDataType;
import gov.grants.apply.forms.rrBudget13V13.BudgetYearDataType;
import gov.grants.apply.forms.rrBudget13V13.BudgetYearDataType.*;
import gov.grants.apply.forms.rrBudget13V13.BudgetYearDataType.Equipment.EquipmentList;
import gov.grants.apply.forms.rrBudget13V13.BudgetYearDataType.KeyPersons.KeyPerson;
import gov.grants.apply.forms.rrBudget13V13.BudgetYearDataType.OtherPersonnel.*;
import gov.grants.apply.forms.rrBudget13V13.RRBudget13Document;
import gov.grants.apply.forms.rrBudget13V13.RRBudget13Document.RRBudget13;
import gov.grants.apply.forms.rrBudget13V13.RRBudget13Document.RRBudget13.BudgetSummary;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType.FileLocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.propdev.api.core.DevelopmentProposalContract;
import org.kuali.coeus.propdev.api.person.ProposalPersonContract;
import org.kuali.coeus.s2sgen.api.core.InfastructureConstants;
import org.kuali.coeus.s2sgen.api.generate.AttachmentData;
import org.kuali.coeus.s2sgen.impl.budget.*;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.s2sgen.api.core.S2SException;
import org.kuali.coeus.propdev.api.attachment.NarrativeContract;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * This class is to generate xml stream for grants.gov form RRBudget13-V1_3
 * ref schema namespace <code> http://apply.grants.gov/forms/RR_Budget13-V1.3</code>
 */
@FormGenerator("RRBudgetV1_3Generator")
public class RRBudgetV1_3Generator extends RRBudgetBaseGenerator {

    private static final Log LOG = LogFactory.getLog(RRBudgetV1_3Generator.class);

    @Value("http://apply.grants.gov/forms/RR_Budget_1_3-V1.3")
    private String namespace;

    @Value("RR_Budget_1_3")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/RR_Budget-V1.3.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.rrBudget13V13")
    private String packageName;

    @Value("165")
    private int sortIndex;

    /**
     * This method returns RRBudget13Document object based on proposal development
     * document which contains the informations such as
     * DUNSID,OrganizationName,BudgetType,BudgetYear and BudgetSummary.
     * 
     * @return rrBudgetDocument {@link XmlObject} of type RRBudget13Document.
     */
    private RRBudget13Document getRRBudget13() {
        
        deleteAutoGenNarratives();
        RRBudget13Document rrBudgetDocument = RRBudget13Document.Factory
                .newInstance();
        RRBudget13 rrBudget = RRBudget13.Factory.newInstance();
        rrBudget.setFormVersion(FormVersion.v1_3.getVersion());
        if (pdDoc.getDevelopmentProposal().getApplicantOrganization() != null) {
            rrBudget.setDUNSID(pdDoc.getDevelopmentProposal()
                    .getApplicantOrganization().getOrganization()
                    .getDunsNumber());
            rrBudget.setOrganizationName(pdDoc.getDevelopmentProposal()
                    .getApplicantOrganization().getOrganization()
                    .getOrganizationName());
        }
        rrBudget.setBudgetType(BudgetTypeDataType.PROJECT);

        List<BudgetPeriodDto> budgetperiodList;
        BudgetSummaryDto budgetSummary = null;
        try {
            validateBudgetForForm(pdDoc);
            budgetperiodList = s2sBudgetCalculatorService.getBudgetPeriods(pdDoc);
            budgetSummary = s2sBudgetCalculatorService.getBudgetInfo(pdDoc,budgetperiodList);
        } catch (S2SException e) {
            LOG.error(e.getMessage(), e);
            return rrBudgetDocument;
        }

        rrBudget.setBudgetSummary(getBudgetSummary(budgetSummary));
        
        for (BudgetPeriodDto budgetPeriodData : budgetperiodList) {
            setBudgetYearDataType(rrBudget,budgetPeriodData);
        }
        AttachedFileDataType attachedFileDataType = AttachedFileDataType.Factory.newInstance();
        for (NarrativeContract narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeType().getCode() != null
                    && Integer.parseInt(narrative.getNarrativeType().getCode()) == 132) {
                attachedFileDataType = getAttachedFileType(narrative);
                if (attachedFileDataType != null) {
                    break;
                }
            }
        }
        rrBudget.setBudgetJustificationAttachment(getBudgetJustification());
        rrBudgetDocument.setRRBudget13(rrBudget);
        return rrBudgetDocument;
    }


    /**
     * This method gets BudgetYearDataType details like
     * BudgetPeriodStartDate,BudgetPeriodEndDate,BudgetPeriod
     * KeyPersons,OtherPersonnel,TotalCompensation,Equipment,ParticipantTraineeSupportCosts,Travel,OtherDirectCosts
     * DirectCosts,IndirectCosts,CognizantFederalAgency,TotalCosts based on
     * BudgetPeriodInfo for the RRBudget13.
     * 
     * @param periodInfo
     *            (BudgetPeriodInfo) budget period entry.
     * @return BudgetYear1DataType corresponding to the BudgetSummaryInfo
     *         object.
     */
    private void setBudgetYearDataType(RRBudget13 rrBudget,BudgetPeriodDto periodInfo) {

        BudgetYearDataType budgetYear = rrBudget.addNewBudgetYear();
        if (periodInfo != null) {
            budgetYear.setBudgetPeriodStartDate(s2SDateTimeService.convertDateToCalendar(periodInfo.getStartDate()));
            budgetYear.setBudgetPeriodEndDate(s2SDateTimeService.convertDateToCalendar(periodInfo.getEndDate()));
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
     * on BudgetSummaryInfo for the RRBudget13.
     * 
     * @param budgetSummaryData
     *            (BudgetSummaryInfo) budget summary entry.
     * @return BudgetSummary details corresponding to the BudgetSummaryInfo
     *         object.
     */
    private gov.grants.apply.forms.rrBudget13V13.RRBudget13Document.RRBudget13.BudgetSummary getBudgetSummary(BudgetSummaryDto budgetSummaryData) {

        BudgetSummary budgetSummary = BudgetSummary.Factory.newInstance();
        OtherDirectCostInfoDto otherDirectCosts = null;
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
                if (budgetSummaryData.getCumTotalFundsForOtherPersonnel() != null
                        && budgetSummaryData.getCumTotalFundsForOtherPersonnel().isGreaterThan(ScaleTwoDecimal.ZERO)) {
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
                budgetSummary.setCumulativeTotalFundsRequestedTraineeCosts(budgetSummaryData.getpartOtherCost()
                            .add(budgetSummaryData.getpartStipendCost()
                                    .add(budgetSummaryData.getpartTravelCost()
                                            .add(budgetSummaryData.getPartTuition()
                                                    .add(budgetSummaryData.getPartSubsistence())))).bigDecimalValue());
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
                        budgetSummary.setCumulativeOther1DirectCost(new BigDecimal(hmCosts.get(CostConstants.KEY_COST).toString()));
                       } else if (j==1) {
                           budgetSummary.setCumulativeOther2DirectCost(new BigDecimal(hmCosts.get(CostConstants.KEY_COST).toString()));
                       } else {
                        budgetSummary.setCumulativeOther3DirectCost(new BigDecimal(hmCosts.get(CostConstants.KEY_COST).toString()));
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
     * based on the BudgetPeriodInfo for the RRBudget13.
     * 
     * @param periodInfo
     *            (BudgetPeriodInfo) budget period entry.
     * @return ParticipantTraineeSupportCosts corresponding to the
     *         BudgetPeriodInfo object.
     */
    private ParticipantTraineeSupportCosts getParticipantTraineeSupportCosts(
            BudgetPeriodDto periodInfo) {

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
    private gov.grants.apply.forms.rrBudget13V13.BudgetYearDataType.ParticipantTraineeSupportCosts.Other getOtherPTSupportCosts(BudgetPeriodDto periodInfo) {
        gov.grants.apply.forms.rrBudget13V13.BudgetYearDataType.ParticipantTraineeSupportCosts.Other other = gov.grants.apply.forms.rrBudget13V13.BudgetYearDataType.ParticipantTraineeSupportCosts.Other.Factory.newInstance();
        other.setDescription(OTHERCOST_DESCRIPTION);
        ScaleTwoDecimal otherCost = ScaleTwoDecimal.ZERO;
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
     * for the RRBudget13.
     * 
     * @param periodInfo
     *            (BudgetPeriodInfo) budget period entry.
     * @return OtherDirectCosts corresponding to the BudgetPeriodInfo object.
     */
    private OtherDirectCosts getOtherDirectCosts(BudgetPeriodDto periodInfo) {

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
     * BudgetYearDataType based on BudgetPeriodInfo for the RRBudget13.
     * 
     * @param periodInfo
     *            (BudgetPeriodInfo) budget period entry.
     * @return IndirectCosts corresponding to the BudgetPeriodInfo object.
     */
    private IndirectCosts getIndirectCosts(BudgetPeriodDto periodInfo) {

        IndirectCosts indirectCosts = null;

        if (periodInfo != null
                && periodInfo.getIndirectCosts() != null
                && periodInfo.getIndirectCosts().getIndirectCostDetails() != null) {

            List<IndirectCosts.IndirectCost> indirectCostList = new ArrayList<IndirectCosts.IndirectCost>();
            int IndirectCostCount = 0;
            for (IndirectCostDetailsDto indirectCostDetails : periodInfo
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
     * for the RRBudget13.
     * 
     * @param otherDirectCosts otherDirectCosts xmlObject
     * @param periodInfo
     *            (BudgetPeriodInfo) budget period entry.
     */
    private void setOthersForOtherDirectCosts(OtherDirectCosts otherDirectCosts, BudgetPeriodDto periodInfo) {
        if (periodInfo != null && periodInfo.getOtherDirectCosts() != null) {
            for (OtherDirectCostInfoDto otherDirectCostInfo : periodInfo.getOtherDirectCosts()) {
                gov.grants.apply.forms.rrBudget13V13.BudgetYearDataType.OtherDirectCosts.Other other = otherDirectCosts.addNewOther();
                if (otherDirectCostInfo.getOtherCosts() != null
                        && otherDirectCostInfo.getOtherCosts().size() > 0) {
                    other
                            .setCost(new BigDecimal(otherDirectCostInfo
                                    .getOtherCosts().get(0).get(
                                            CostConstants.KEY_COST)));
                }
                other.setDescription(OTHERCOST_DESCRIPTION);
            }
        }
    }

    /**
     * This method gets Travel cost information including
     * DomesticTravelCost,ForeignTravelCost and TotalTravelCost in the
     * BudgetYearDataType based on BudgetPeriodInfo for the RRBudget13.
     * 
     * @param periodInfo
     *            (BudgetPeriodInfo) budget period entry.
     * @return Travel costs corresponding to the BudgetPeriodInfo object.
     */
    private Travel getTravel(BudgetPeriodDto periodInfo) {

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
     * RRBudget13.
     * 
     * @param periodInfo
     *            (BudgetPeriodInfo) budget period entry.
     * @return Equipment costs corresponding to the BudgetPeriodInfo object.
     * 
     */
    private Equipment getEquipment(BudgetPeriodDto periodInfo) {
        Equipment equipment = Equipment.Factory.newInstance();
        NarrativeContract extraEquipmentNarr = null;
        if (periodInfo != null && periodInfo.getEquipment() != null
                && periodInfo.getEquipment().size() > 0) {
            // Evaluating Equipments.
            List<EquipmentList> equipmentArrayList = new ArrayList<EquipmentList>();
            ScaleTwoDecimal totalFund = ScaleTwoDecimal.ZERO;
            for (CostDto costInfo : periodInfo.getEquipment().get(0)
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
            List<CostDto> extraEquipmentArrayList = new ArrayList<CostDto>();
            ScaleTwoDecimal totalExtraEquipFund = ScaleTwoDecimal.ZERO;
            for(CostDto costInfo:periodInfo.getEquipment().get(0).getExtraEquipmentList()){
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
            equipmentAttachment.setFileName(extraEquipmentNarr.getNarrativeAttachment().getName());
            equipmentAttachment
                    .setMimeType(InfastructureConstants.CONTENT_TYPE_OCTET_STREAM);
            if (extraEquipmentNarr.getNarrativeAttachment() != null) {
                equipmentAttachment.setHashValue(getHashValue(extraEquipmentNarr
                        .getNarrativeAttachment().getData()));
            }
            AttachmentData attachmentData = new AttachmentData();
            attachmentData.setContent(extraEquipmentNarr
                    .getNarrativeAttachment().getData());
            attachmentData.setContentId(contentId);
            attachmentData.setContentType(InfastructureConstants.CONTENT_TYPE_OCTET_STREAM);
            attachmentData.setFileName(extraEquipmentNarr.getNarrativeAttachment().getName());
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
     * TotalOtherPersonnelFund based on BudgetPeriodInfo for the RRBudget13.
     * 
     * @param periodInfo
     *            (BudgetPeriodInfo) budget period entry.
     * @return OtherPersonnel details corresponding to the BudgetPeriodInfo
     *         object.
     */
    private OtherPersonnel getOtherPersonnel(BudgetPeriodDto periodInfo) {
        OtherPersonnel otherPersonnel = OtherPersonnel.Factory.newInstance();
        int otherPersonnelCount = 0;
        List<gov.grants.apply.forms.rrBudget13V13.BudgetYearDataType.OtherPersonnel.Other> otherPersonnelList = new ArrayList<gov.grants.apply.forms.rrBudget13V13.BudgetYearDataType.OtherPersonnel.Other>();
        gov.grants.apply.forms.rrBudget13V13.BudgetYearDataType.OtherPersonnel.Other otherPersonnelDataTypeArray[] = new gov.grants.apply.forms.rrBudget13V13.BudgetYearDataType.OtherPersonnel.Other[1];
        if (periodInfo != null) {
            for (OtherPersonnelDto otherPersonnelInfo : periodInfo
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
                    CompensationDto sectBCompType = otherPersonnelInfo.getCompensation();
                    gov.grants.apply.forms.rrBudget13V13.BudgetYearDataType.OtherPersonnel.Other otherPersonnelDataType = otherPersonnel.addNewOther();
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
            otherPersonnel.setOtherArray((Other[]) otherPersonnelDataTypeArray);

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
     * RRBudget13,if it is a PostDocAssociates type.
     * 
     * @param otherPersonnel
     *            (OtherPersonnelInfo)other personnel info entry.
     * @return PostDocAssociates details corresponding to the OtherPersonnelInfo
     *         object.
     */
    private PostDocAssociates getPostDocAssociates(
            OtherPersonnelDto otherPersonnel) {

        PostDocAssociates postDocAssociates = PostDocAssociates.Factory
                .newInstance();
        if (otherPersonnel != null) {
            postDocAssociates.setNumberOfPersonnel(otherPersonnel
                    .getNumberPersonnel());
            postDocAssociates.setProjectRole(otherPersonnel.getRole());
            CompensationDto sectBCompType = otherPersonnel.getCompensation();

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
     * RRBudget13, if it is a GraduateStudents type.
     * 
     * @param otherPersonnel
     *            (OtherPersonnelInfo) other personnel info entry.
     * @return GraduateStudents details corresponding to the OtherPersonnelInfo
     *         object.
     */
    private GraduateStudents getGraduateStudents(
            OtherPersonnelDto otherPersonnel) {

        GraduateStudents graduate = GraduateStudents.Factory.newInstance();
        if (otherPersonnel != null) {
            graduate.setNumberOfPersonnel(otherPersonnel.getNumberPersonnel());
            graduate.setProjectRole(otherPersonnel.getRole());
            CompensationDto sectBCompType = otherPersonnel.getCompensation();

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
     * RRBudget13,if it is a UndergraduateStudents type.
     * 
     * @param otherPersonnel
     *            (OtherPersonnelInfo) other personnel info entry.
     * @return UndergraduateStudents details corresponding to the
     *         OtherPersonnelInfo object.
     */
    private UndergraduateStudents getUndergraduateStudents(
            OtherPersonnelDto otherPersonnel) {

        UndergraduateStudents undergraduate = UndergraduateStudents.Factory
                .newInstance();
        if (otherPersonnel != null) {
            undergraduate.setNumberOfPersonnel(otherPersonnel
                    .getNumberPersonnel());
            undergraduate.setProjectRole(otherPersonnel.getRole());
            CompensationDto sectBCompType = otherPersonnel.getCompensation();

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
     * RRBudget13,if it is a SecretarialClerical type.
     * 
     * @param otherPersonnel
     *            (OtherPersonnelInfo) other personnel info entry.
     * @return SecretarialClerical corresponding to the OtherPersonnelInfo
     *         object.
     */
    private SecretarialClerical getSecretarialClerical(
            OtherPersonnelDto otherPersonnel) {

        SecretarialClerical secretarialClerical = SecretarialClerical.Factory
                .newInstance();
        if (otherPersonnel != null) {
            secretarialClerical.setNumberOfPersonnel(otherPersonnel
                    .getNumberPersonnel());
            secretarialClerical.setProjectRole(otherPersonnel.getRole());
            CompensationDto sectBCompType = otherPersonnel.getCompensation();

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
     * for the RRBudget13.
     * 
     * @param periodInfo
     *            (BudgetPeriodInfo) budget period entry.
     * @return KeyPersons details corresponding to the BudgetPeriodInfo object.
     */
    private KeyPersons getKeyPersons(BudgetPeriodDto periodInfo) {

        KeyPersons keyPersons = KeyPersons.Factory.newInstance();
        ScaleTwoDecimal extraFunds = ScaleTwoDecimal.ZERO;
        ScaleTwoDecimal baseSalaryByPeriod;

        if (periodInfo != null) {
            if (periodInfo.getKeyPersons() != null) {
                List<KeyPerson> keyPersonList = new ArrayList<KeyPerson>();
                int keyPersonCount = 0;
                for (KeyPersonDto keyPerson : periodInfo.getKeyPersons()) {
                  if(keyPerson.getRole().equals(NID_PD_PI) || hasPersonnelBudget(keyPerson,periodInfo.getBudgetPeriod())){
                    KeyPerson keyPersonDataType = KeyPerson.Factory.newInstance();
                    keyPersonDataType.setName(globLibV20Generator
                            .getHumanNameDataType(keyPerson));
                    if (isSponsorNIH(pdDoc)
                            && KEYPERSON_CO_PD_PI.equals(keyPerson.getRole())) {
                        DevelopmentProposalContract developmentProposal=pdDoc.getDevelopmentProposal();
                        
                        for (ProposalPersonContract proposalPerson : developmentProposal.getInvestigators()) {
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
                        if (pdDoc.getDevelopmentProposal().getBudgets() != null) {
                            baseSalaryByPeriod = s2sBudgetCalculatorService.getBaseSalaryByPeriod(pdDoc.getDevelopmentProposal().getBudgets().get(0)
                                    .getBudgetId(), periodInfo.getBudgetPeriod(), keyPerson);
                            if (baseSalaryByPeriod != null) {
                                keyPersonDataType.setBaseSalary(baseSalaryByPeriod.bigDecimalValue());
                            }
                            else {
                                if (keyPerson.getBaseSalary() != null) {
                                    keyPersonDataType.setBaseSalary(keyPerson.getBaseSalary().bigDecimalValue());
                                }
                            }

                        }
                        else {
                            if (keyPerson.getBaseSalary() != null) {
                                keyPersonDataType.setBaseSalary(keyPerson.getBaseSalary().bigDecimalValue());
                            }
                        }
                    keyPersonDataType.setRequestedSalary(keyPerson.getRequestedSalary().bigDecimalValue());
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
            for (KeyPersonDto keyPerson : periodInfo.getExtraKeyPersons()) {
                extraFunds = extraFunds.add(keyPerson.getFundsRequested());
            }
        }
        keyPersons.setTotalFundForAttachedKeyPersons(extraFunds.bigDecimalValue());
        NarrativeContract extraKeyPersonNarr = saveExtraKeyPersons(periodInfo);
        if(extraKeyPersonNarr!=null){
            AttachedFileDataType attachedKeyPersons = AttachedFileDataType.Factory.newInstance();
            FileLocation fileLocation = FileLocation.Factory.newInstance();
            attachedKeyPersons.setFileLocation(fileLocation);
            String contentId = createContentId(extraKeyPersonNarr);
            fileLocation.setHref(contentId);
            attachedKeyPersons.setFileLocation(fileLocation);
            attachedKeyPersons.setFileName(extraKeyPersonNarr.getNarrativeAttachment().getName());
            attachedKeyPersons
                    .setMimeType(InfastructureConstants.CONTENT_TYPE_OCTET_STREAM);
            AttachmentData attachmentData = new AttachmentData();
            byte[] narrativeContent = null;
            if (extraKeyPersonNarr.getNarrativeAttachment() != null) {
                narrativeContent = extraKeyPersonNarr
                .getNarrativeAttachment().getData();
                
            }
            if(narrativeContent != null && narrativeContent.length > 0){
                attachedKeyPersons.setHashValue(getHashValue(narrativeContent));
                attachmentData.setContent(narrativeContent);
                attachmentData.setContentId(contentId);
                attachmentData
                        .setContentType(InfastructureConstants.CONTENT_TYPE_OCTET_STREAM);
                attachmentData.setFileName(extraKeyPersonNarr.getNarrativeAttachment().getName());
                addAttachment(attachmentData);
                keyPersons.setAttachedKeyPersons(attachedKeyPersons);
            }
        }
        return keyPersons;
    }    
  
    /**
     * This method gets BudgetJustificationAttachment from ProposalDevelopmentDocumentContract
     *  for the RRBudget
     *     
     * @return AttachedFileDataType details corresponding to the
     *         BudgetJustificationAttachment.     
     */
    private AttachedFileDataType getBudgetJustification() {
        AttachedFileDataType attachedFileDataType = AttachedFileDataType.Factory.newInstance();
        for (NarrativeContract narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeType().getCode() != null
                    && Integer.parseInt(narrative.getNarrativeType().getCode()) == BUDGET_JUSTIFICATION_ATTACHMENT) {
                attachedFileDataType = getAttachedFileType(narrative);
                if (attachedFileDataType != null) {
                    break;
                }
            }
        }
        return attachedFileDataType;
    }

    /**
     * This method creates {@link XmlObject} of type {@link RRBudget13Document} by
     * populating data from the given {@link ProposalDevelopmentDocumentContract}
     * 
     * @param proposalDevelopmentDocument
     *            for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given
     *         {@link ProposalDevelopmentDocumentContract}
     */
    public XmlObject getFormObject(
            ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getRRBudget13();
    }

    @Override
    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    @Override
    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    @Override
    public Resource getStylesheet() {
        return stylesheet;
    }

    public void setStylesheet(Resource stylesheet) {
        this.stylesheet = stylesheet;
    }

    @Override
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }
}
