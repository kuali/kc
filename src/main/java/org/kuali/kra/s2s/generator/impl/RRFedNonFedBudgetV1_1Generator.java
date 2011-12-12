/*
 * Copyright 2005-2010 The Kuali Foundation.
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
package org.kuali.kra.s2s.generator.impl;


import gov.grants.apply.coeus.additionalEquipment.AdditionalEquipmentListDocument;
import gov.grants.apply.coeus.additionalEquipment.AdditionalEquipmentListDocument.AdditionalEquipmentList;
import gov.grants.apply.coeus.extraKeyPerson.ExtraKeyPersonListDocument;
import gov.grants.apply.coeus.extraKeyPerson.ExtraKeyPersonListDocument.ExtraKeyPersonList;
import gov.grants.apply.coeus.extraKeyPerson.ExtraKeyPersonListDocument.ExtraKeyPersonList.KeyPersons.Compensation;
import gov.grants.apply.forms.rrFedNonFedBudgetV11.BudgetTypeDataType;
import gov.grants.apply.forms.rrFedNonFedBudgetV11.BudgetYear1DataType;
import gov.grants.apply.forms.rrFedNonFedBudgetV11.BudgetYearDataType;
import gov.grants.apply.forms.rrFedNonFedBudgetV11.BudgetYearDataType.Equipment;
import gov.grants.apply.forms.rrFedNonFedBudgetV11.BudgetYearDataType.Equipment.EquipmentList;
import gov.grants.apply.forms.rrFedNonFedBudgetV11.BudgetYearDataType.IndirectCosts;
import gov.grants.apply.forms.rrFedNonFedBudgetV11.BudgetYearDataType.KeyPersons;
import gov.grants.apply.forms.rrFedNonFedBudgetV11.BudgetYearDataType.OtherDirectCosts;
import gov.grants.apply.forms.rrFedNonFedBudgetV11.BudgetYearDataType.OtherDirectCosts.Others;
import gov.grants.apply.forms.rrFedNonFedBudgetV11.BudgetYearDataType.OtherPersonnel;
import gov.grants.apply.forms.rrFedNonFedBudgetV11.BudgetYearDataType.OtherPersonnel.GraduateStudents;
import gov.grants.apply.forms.rrFedNonFedBudgetV11.BudgetYearDataType.OtherPersonnel.PostDocAssociates;
import gov.grants.apply.forms.rrFedNonFedBudgetV11.BudgetYearDataType.OtherPersonnel.SecretarialClerical;
import gov.grants.apply.forms.rrFedNonFedBudgetV11.BudgetYearDataType.OtherPersonnel.UndergraduateStudents;
import gov.grants.apply.forms.rrFedNonFedBudgetV11.BudgetYearDataType.ParticipantTraineeSupportCosts;
import gov.grants.apply.forms.rrFedNonFedBudgetV11.BudgetYearDataType.ParticipantTraineeSupportCosts.Other;
import gov.grants.apply.forms.rrFedNonFedBudgetV11.BudgetYearDataType.Travel;
import gov.grants.apply.forms.rrFedNonFedBudgetV11.KeyPersonCompensationDataType;
import gov.grants.apply.forms.rrFedNonFedBudgetV11.KeyPersonDataType;
import gov.grants.apply.forms.rrFedNonFedBudgetV11.OtherPersonnelDataType;
import gov.grants.apply.forms.rrFedNonFedBudgetV11.RRFedNonFedBudgetDocument;
import gov.grants.apply.forms.rrFedNonFedBudgetV11.RRFedNonFedBudgetDocument.RRFedNonFedBudget;
import gov.grants.apply.forms.rrFedNonFedBudgetV11.RRFedNonFedBudgetDocument.RRFedNonFedBudget.BudgetSummary;
import gov.grants.apply.forms.rrFedNonFedBudgetV11.RRFedNonFedBudgetDocument.RRFedNonFedBudget.BudgetSummary.CumulativeEquipments;
import gov.grants.apply.forms.rrFedNonFedBudgetV11.RRFedNonFedBudgetDocument.RRFedNonFedBudget.BudgetSummary.CumulativeOtherDirect;
import gov.grants.apply.forms.rrFedNonFedBudgetV11.RRFedNonFedBudgetDocument.RRFedNonFedBudget.BudgetSummary.CumulativeTrainee;
import gov.grants.apply.forms.rrFedNonFedBudgetV11.RRFedNonFedBudgetDocument.RRFedNonFedBudget.BudgetSummary.CumulativeTravels;
import gov.grants.apply.forms.rrFedNonFedBudgetV11.SectBCompensationDataType;
import gov.grants.apply.forms.rrFedNonFedBudgetV11.SummaryDataType;
import gov.grants.apply.forms.rrFedNonFedBudgetV11.TotalDataType;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.printing.print.GenericPrintable;
import org.kuali.kra.printing.service.PrintingService;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.generator.bo.BudgetPeriodInfo;
import org.kuali.kra.s2s.generator.bo.BudgetSummaryInfo;
import org.kuali.kra.s2s.generator.bo.CompensationInfo;
import org.kuali.kra.s2s.generator.bo.CostInfo;
import org.kuali.kra.s2s.generator.bo.EquipmentInfo;
import org.kuali.kra.s2s.generator.bo.IndirectCostDetails;
import org.kuali.kra.s2s.generator.bo.KeyPersonInfo;
import org.kuali.kra.s2s.generator.bo.OtherDirectCostInfo;
import org.kuali.kra.s2s.generator.bo.OtherPersonnelInfo;
import org.kuali.kra.s2s.util.S2SConstants;


/**
 * 
 * This class is used to generate XML Document object for grants.gov RRFedNonFedBudgetV1.1. This form is generated using XMLBean
 * API's generated by compiling RRFedNonFedBudgetV1.1 schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class RRFedNonFedBudgetV1_1Generator extends RRFedNonFedBudgetBaseGenerator {

    private static final Log LOG = LogFactory.getLog(RRFedNonFedBudgetV1_0Generator.class);
    private static final String EXTRA_KEYPERSON_ATTACHMENT_NON_FED_XSL = "/org/kuali/kra/s2s/stylesheet/ExtraKeyPersonAttachmentNonFed.xsl";
	private static final String EXTRA_KEYPERSONS = "RRFEDNONFED_EXTRA_KEYPERSONS";
	private static final int EXTRA_KEYPERSONS_TYPE = 11;
	private static final String EXTRA_KEYPERSONS_COMMENT = "RRFEDNONFED_EXTRA_KEYPERSONS";
	private static final String ADDITIONAL_EQUIPMENT_NARRATIVE_TYPE_CODE ="12";
	private static final String ADDITIONAL_EQUIPMENT_NARRATIVE_COMMENT = "RRFEDNONFED_ADDITIONAL_EQUIPMENT";
    /**
     * This method returns RRFedNonFedBudgetDocument object based on proposal development document which contains the informations
     * such as DUNSID,OrganizationName,BudgetType,BudgetYear and BudgetSummary.
     * 
     * @return rrFedNonFedBudgetDocument {@link XmlObject} of type RRFedNonFedBudgetDocument.
     */
    private RRFedNonFedBudgetDocument getRRFedNonFedBudget() {
        RRFedNonFedBudgetDocument rrFedNonFedBudgetDocument = RRFedNonFedBudgetDocument.Factory.newInstance();
        RRFedNonFedBudget rrFedNonFedBudget = RRFedNonFedBudget.Factory.newInstance();
        rrFedNonFedBudget.setFormVersion(S2SConstants.FORMVERSION_1_1);
        if (pdDoc.getDevelopmentProposal().getApplicantOrganization() != null) {
            rrFedNonFedBudget.setDUNSID(pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization().getDunsNumber());
        }
        if (pdDoc.getDevelopmentProposal().getApplicantOrganization() != null) {
            rrFedNonFedBudget.setOrganizationName(pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization().getOrganizationName());
        }
        rrFedNonFedBudget.setBudgetType(BudgetTypeDataType.PROJECT);
        // Set default values for mandatory fields

        List<BudgetPeriodInfo> budgetPeriodList;
        BudgetSummaryInfo budgetSummary = null;
        try {
            budgetPeriodList = s2sBudgetCalculatorService.getBudgetPeriods(pdDoc);
            budgetSummary = s2sBudgetCalculatorService.getBudgetInfo(pdDoc,budgetPeriodList);
        }
        catch (S2SException e) {
            LOG.error(e.getMessage(), e);
            return rrFedNonFedBudgetDocument;
        }
        for (BudgetPeriodInfo budgetPeriodData : budgetPeriodList) {
            if (budgetPeriodData.getBudgetPeriod() == BudgetPeriodInfo.BUDGET_PERIOD_1) {
                rrFedNonFedBudget.setBudgetYear1(getBudgetYear1DataType(budgetPeriodData));
            }
            else if (budgetPeriodData.getBudgetPeriod() == BudgetPeriodInfo.BUDGET_PERIOD_2) {
                rrFedNonFedBudget.setBudgetYear2(getBudgetYearDataType(budgetPeriodData));
            }
            else if (budgetPeriodData.getBudgetPeriod() == BudgetPeriodInfo.BUDGET_PERIOD_3) {
                rrFedNonFedBudget.setBudgetYear3(getBudgetYearDataType(budgetPeriodData));
            }
            else if (budgetPeriodData.getBudgetPeriod() == BudgetPeriodInfo.BUDGET_PERIOD_4) {
                rrFedNonFedBudget.setBudgetYear4(getBudgetYearDataType(budgetPeriodData));
            }
            else if (budgetPeriodData.getBudgetPeriod() == BudgetPeriodInfo.BUDGET_PERIOD_5) {
                rrFedNonFedBudget.setBudgetYear5(getBudgetYearDataType(budgetPeriodData));
            }
        }
        for (BudgetPeriodInfo budgetPeriodData : budgetPeriodList) {
            if (budgetPeriodData.getBudgetPeriod() == BudgetPeriodInfo.BUDGET_PERIOD_1) {
                rrFedNonFedBudget.setBudgetYear1(getBudgetJustificationAttachment());
            }
        }
        rrFedNonFedBudget.setBudgetSummary(getBudgetSummary(budgetSummary));
        rrFedNonFedBudgetDocument.setRRFedNonFedBudget(rrFedNonFedBudget);
        return rrFedNonFedBudgetDocument;
    }

    /**
     * This method gets BudgetYearDataType details like BudgetPeriodStartDate,BudgetPeriodEndDate,BudgetPeriod
     * KeyPersons,OtherPersonnel,TotalCompensation,Equipment,ParticipantTraineeSupportCosts,Travel,OtherDirectCosts
     * DirectCosts,IndirectCosts,CognizantFederalAgency,TotalCosts based on BudgetPeriodInfo for the RRFedNonFedBudget.
     * 
     * @param periodInfo (BudgetPeriodInfo) budget period entry.
     * @return BudgetYearDataType corresponding to the BudgetSummaryInfo object.
     */
    private BudgetYearDataType getBudgetYearDataType(BudgetPeriodInfo periodInfo) {

        BudgetYearDataType budgetYear = BudgetYearDataType.Factory.newInstance();
        if (periodInfo != null) {
            budgetYear.setBudgetPeriodStartDate(s2sUtilService.convertDateToCalendar(periodInfo.getStartDate()));
            budgetYear.setBudgetPeriodEndDate(s2sUtilService.convertDateToCalendar(periodInfo.getEndDate()));
            gov.grants.apply.forms.rrFedNonFedBudgetV11.BudgetYearDataType.BudgetPeriod.Enum budgetPeriodEnum = gov.grants.apply.forms.rrFedNonFedBudgetV11.BudgetYearDataType.BudgetPeriod.Enum
                    .forInt(periodInfo.getBudgetPeriod());
            budgetYear.setBudgetPeriod(budgetPeriodEnum);
            budgetYear.setKeyPersons(getKeyPersons(periodInfo));
            budgetYear.setOtherPersonnel(getOtherPersonnel(periodInfo));
            SummaryDataType summary = SummaryDataType.Factory.newInstance();
            if (periodInfo.getTotalCompensation() != null) {
                summary.setFederalSummary(periodInfo.getTotalCompensation().bigDecimalValue());
            }
            if (periodInfo.getTotalCompensationCostSharing() != null) {
                summary.setNonFederalSummary(periodInfo.getTotalCompensationCostSharing().bigDecimalValue());
                if (periodInfo.getTotalCompensation() != null) {
                    summary.setTotalFedNonFedSummary(periodInfo.getTotalCompensation().add(
                            periodInfo.getTotalCompensationCostSharing()).bigDecimalValue());
                }
                else {
                    summary.setTotalFedNonFedSummary(periodInfo.getTotalCompensationCostSharing().bigDecimalValue());
                }
            }
            budgetYear.setTotalCompensation(summary);
            budgetYear.setEquipment(getEquipment(periodInfo));
            budgetYear.setTravel(getTravel(periodInfo));
            budgetYear.setParticipantTraineeSupportCosts(getParticipantTraineeSupportCosts(periodInfo));
            budgetYear.setOtherDirectCosts(getOtherDirectCosts(periodInfo));
            SummaryDataType summaryDirect = SummaryDataType.Factory.newInstance();
            if (periodInfo.getDirectCostsTotal() != null) {
                summaryDirect.setFederalSummary(periodInfo.getDirectCostsTotal().bigDecimalValue());
            }
            if (periodInfo.getCostSharingAmount() != null) {
                BudgetDecimal totalDirectCostSharing = periodInfo.getTotalDirectCostSharing();
                summaryDirect.setNonFederalSummary(totalDirectCostSharing.bigDecimalValue());
                if (periodInfo.getDirectCostsTotal() != null) {
                    summaryDirect.setTotalFedNonFedSummary(periodInfo.getDirectCostsTotal().add(totalDirectCostSharing).bigDecimalValue());
                }
                else {
                    summaryDirect.setTotalFedNonFedSummary(totalDirectCostSharing.bigDecimalValue());
                }
            }
            budgetYear.setDirectCosts(summaryDirect);
            IndirectCosts indirectCosts = getIndirectCosts(periodInfo);
            if (indirectCosts != null) {
                budgetYear.setIndirectCosts(indirectCosts);
            }
            budgetYear.setCognizantFederalAgency(periodInfo.getCognizantFedAgency());
            SummaryDataType summaryTotal = SummaryDataType.Factory.newInstance();
            if (periodInfo.getTotalCosts() != null) {
                summaryTotal.setFederalSummary(periodInfo.getTotalCosts().bigDecimalValue());
            }
            summaryTotal.setNonFederalSummary(periodInfo.getCostSharingAmount().bigDecimalValue());
            if (periodInfo.getCostSharingAmount() != null) {
                if (periodInfo.getTotalCosts() != null) {
                    summaryTotal.setTotalFedNonFedSummary(periodInfo.getTotalCosts().add(periodInfo.getCostSharingAmount())
                            .bigDecimalValue());
                }
                else {
                    summaryTotal.setTotalFedNonFedSummary(periodInfo.getCostSharingAmount().bigDecimalValue());
                }
            }
            budgetYear.setTotalCosts(summaryTotal);
        }
        return budgetYear;
    }

    /**
     * This method gets BudgetSummary details such as
     * CumulativeTotalFundsRequestedSeniorKeyPerson,CumulativeTotalFundsRequestedOtherPersonnel
     * CumulativeTotalNoOtherPersonnel,CumulativeTotalFundsRequestedPersonnel,CumulativeEquipments,CumulativeTravels
     * CumulativeTrainee,CumulativeOtherDirect,CumulativeTotalFundsRequestedDirectCosts,CumulativeTotalFundsRequestedIndirectCost
     * CumulativeTotalFundsRequestedDirectIndirectCosts and CumulativeFee based on BudgetSummaryInfo for the RRFedNonFedBudget.
     * 
     * @param budgetSummaryData (BudgetSummaryInfo) budget summary entry.
     * @return BudgetSummary corresponding to the BudgetSummaryInfo object.
     */
    private BudgetSummary getBudgetSummary(BudgetSummaryInfo budgetSummaryData) {

        BudgetSummary budgetSummary = BudgetSummary.Factory.newInstance();
        SummaryDataType summarySeniorKey = SummaryDataType.Factory.newInstance();
        SummaryDataType summaryPersonnel = SummaryDataType.Factory.newInstance();
        SummaryDataType directCosts = SummaryDataType.Factory.newInstance();
        SummaryDataType directIndirectCosts = SummaryDataType.Factory.newInstance();
        if (budgetSummaryData != null) {
            if (budgetSummaryData.getCumTotalFundsForSrPersonnel() != null) {
                summarySeniorKey.setFederalSummary(budgetSummaryData.getCumTotalFundsForSrPersonnel().bigDecimalValue());
            }
            if (budgetSummaryData.getCumTotalNonFundsForSrPersonnel() != null) {
                summarySeniorKey.setNonFederalSummary(budgetSummaryData.getCumTotalNonFundsForSrPersonnel().bigDecimalValue());

                if (budgetSummaryData.getCumTotalFundsForSrPersonnel() != null) {
                    summarySeniorKey.setTotalFedNonFedSummary(budgetSummaryData.getCumTotalFundsForSrPersonnel().add(
                            budgetSummaryData.getCumTotalNonFundsForSrPersonnel()).bigDecimalValue());
                }
                else {
                    summarySeniorKey.setTotalFedNonFedSummary(budgetSummaryData.getCumTotalNonFundsForSrPersonnel()
                            .bigDecimalValue());
                }

            }
            SummaryDataType summaryOtherPersonnel = SummaryDataType.Factory.newInstance();
            if (budgetSummaryData.getCumTotalFundsForOtherPersonnel() != null) {
                summaryOtherPersonnel.setFederalSummary(budgetSummaryData.getCumTotalFundsForOtherPersonnel().bigDecimalValue());
            }
            if (budgetSummaryData.getCumTotalNonFundsForOtherPersonnel() != null) {
                summaryOtherPersonnel.setNonFederalSummary(budgetSummaryData.getCumTotalNonFundsForOtherPersonnel()
                        .bigDecimalValue());


                if (budgetSummaryData.getCumTotalFundsForOtherPersonnel() != null) {
                    summaryOtherPersonnel.setTotalFedNonFedSummary(budgetSummaryData.getCumTotalFundsForOtherPersonnel().add(
                            budgetSummaryData.getCumTotalNonFundsForOtherPersonnel()).bigDecimalValue());
                }
                else {
                    summaryOtherPersonnel.setTotalFedNonFedSummary(budgetSummaryData.getCumTotalNonFundsForOtherPersonnel()
                            .bigDecimalValue());
                }
            }
            budgetSummary.setCumulativeTotalFundsRequestedOtherPersonnel(summaryOtherPersonnel);
            if (budgetSummaryData.getCumNumOtherPersonnel() != null) {
                budgetSummary.setCumulativeTotalNoOtherPersonnel(budgetSummaryData.getCumNumOtherPersonnel().intValue());
            }
            if (budgetSummaryData.getCumTotalFundsForPersonnel() != null) {
                summaryPersonnel.setFederalSummary(budgetSummaryData.getCumTotalFundsForPersonnel().bigDecimalValue());
            }
            if (budgetSummaryData.getCumTotalNonFundsForPersonnel() != null) {
                summaryPersonnel.setNonFederalSummary(budgetSummaryData.getCumTotalNonFundsForPersonnel().bigDecimalValue());
                if (budgetSummaryData.getCumTotalFundsForPersonnel() != null) {
                    summaryPersonnel.setTotalFedNonFedSummary(budgetSummaryData.getCumTotalFundsForPersonnel().add(
                            budgetSummaryData.getCumTotalNonFundsForPersonnel()).bigDecimalValue());
                }
                else {
                    summaryPersonnel
                            .setTotalFedNonFedSummary(budgetSummaryData.getCumTotalNonFundsForPersonnel().bigDecimalValue());
                }
            }
            budgetSummary.setCumulativeEquipments(getCumulativeEquipments(budgetSummaryData));
            budgetSummary.setCumulativeTravels(getCumulativeTravels(budgetSummaryData));
            budgetSummary.setCumulativeTrainee(getCumulativeTrainee(budgetSummaryData));
            budgetSummary.setCumulativeOtherDirect(getCumulativeOtherDirect(budgetSummaryData));
            if (budgetSummaryData.getCumTotalDirectCosts() != null && budgetSummaryData.getCumTotalDirectCostSharing() != null) {
                if (budgetSummaryData.getCumFee() != null) {
                    budgetSummary.setCumulativeFee(budgetSummaryData.getCumFee().bigDecimalValue());
                }
                directCosts.setFederalSummary(budgetSummaryData.getCumTotalDirectCosts().bigDecimalValue());
                directCosts.setNonFederalSummary(budgetSummaryData.getCumTotalDirectCostSharing().bigDecimalValue());
                directCosts.setTotalFedNonFedSummary(budgetSummaryData.getCumTotalDirectCosts().add(
                        budgetSummaryData.getCumTotalDirectCostSharing()).bigDecimalValue());
            }
            if (budgetSummaryData.getCumTotalIndirectCosts() != null && budgetSummaryData.getCumTotalIndirectCostSharing() != null) {
                SummaryDataType summary = SummaryDataType.Factory.newInstance();
                summary.setFederalSummary(budgetSummaryData.getCumTotalIndirectCosts().bigDecimalValue());
                summary.setNonFederalSummary(budgetSummaryData.getCumTotalIndirectCostSharing().bigDecimalValue());
                summary.setTotalFedNonFedSummary(budgetSummaryData.getCumTotalIndirectCosts().add(
                        budgetSummaryData.getCumTotalIndirectCostSharing()).bigDecimalValue());
                budgetSummary.setCumulativeTotalFundsRequestedIndirectCost(summary);
            }
            if (budgetSummaryData.getCumTotalCosts() != null && budgetSummaryData.getCumTotalCostSharing() != null) {
                directIndirectCosts.setFederalSummary(budgetSummaryData.getCumTotalCosts().bigDecimalValue());
                directIndirectCosts.setNonFederalSummary(budgetSummaryData.getCumTotalCostSharing().bigDecimalValue());
                directIndirectCosts.setTotalFedNonFedSummary(budgetSummaryData.getCumTotalCosts().add(
                        budgetSummaryData.getCumTotalCostSharing()).bigDecimalValue());
            }
        }
        budgetSummary.setCumulativeTotalFundsRequestedSeniorKeyPerson(summarySeniorKey);
        budgetSummary.setCumulativeTotalFundsRequestedPersonnel(summaryPersonnel);
        budgetSummary.setCumulativeTotalFundsRequestedDirectCosts(directCosts);
        budgetSummary.setCumulativeTotalFundsRequestedDirectIndirectCosts(directIndirectCosts);
        return budgetSummary;
    }

    /**
     * This method gets CumulativeOtherDirectCost details,CumulativeMaterialAndSupplies,CumulativePublicationCosts,
     * CumulativeConsultantServices,CumulativeADPComputerServices,CumulativeSubawardConsortiumContractualCosts
     * CumulativeEquipmentFacilityRentalFees,CumulativeAlterationsAndRenovations and CumulativeOther1DirectCost based on
     * BudgetSummaryInfo for the RRFedNonFedBudget.
     * 
     * @param budgetSummaryData (BudgetSummaryInfo) budget summary entry.
     * @return CumulativeOtherDirect corresponding to the BudgetSummaryInfo object.
     */
    private CumulativeOtherDirect getCumulativeOtherDirect(BudgetSummaryInfo budgetSummaryData) {
        CumulativeOtherDirect cumulativeOtherDirect = CumulativeOtherDirect.Factory.newInstance();
        SummaryDataType summary = SummaryDataType.Factory.newInstance();
        if (budgetSummaryData != null && budgetSummaryData.getOtherDirectCosts() != null) {
            for (OtherDirectCostInfo cumOtherDirect : budgetSummaryData.getOtherDirectCosts()) {
                if (cumOtherDirect.gettotalOtherDirect() != null) {
                    summary.setFederalSummary(cumOtherDirect.gettotalOtherDirect().bigDecimalValue());
                }
                if (cumOtherDirect.getTotalOtherDirectCostSharing() != null) {
                    summary.setNonFederalSummary(cumOtherDirect.getTotalOtherDirectCostSharing().bigDecimalValue());
                    if (cumOtherDirect.gettotalOtherDirect() != null) {
                        summary.setTotalFedNonFedSummary(cumOtherDirect.gettotalOtherDirect().add(
                                cumOtherDirect.getTotalOtherDirectCostSharing()).bigDecimalValue());
                    }
                    else {
                        summary.setTotalFedNonFedSummary(cumOtherDirect.getTotalOtherDirectCostSharing().bigDecimalValue());
                    }
                }
                TotalDataType totalMaterial = TotalDataType.Factory.newInstance();
                if (cumOtherDirect.getmaterials() != null) {
                    totalMaterial.setFederal(cumOtherDirect.getmaterials().bigDecimalValue());
                }
                if (cumOtherDirect.getMaterialsCostSharing() != null) {
                    totalMaterial.setNonFederal(cumOtherDirect.getMaterialsCostSharing().bigDecimalValue());
                    if (cumOtherDirect.getmaterials() != null) {
                        totalMaterial.setTotalFedNonFed(cumOtherDirect.getmaterials().add(cumOtherDirect.getMaterialsCostSharing())
                                .bigDecimalValue());
                    }
                    else {
                        totalMaterial.setTotalFedNonFed(cumOtherDirect.getMaterialsCostSharing().bigDecimalValue());
                    }
                }
                cumulativeOtherDirect.setCumulativeMaterialAndSupplies(totalMaterial);
                TotalDataType totalPublication = TotalDataType.Factory.newInstance();
                if (cumOtherDirect.getpublications() != null) {
                    totalPublication.setFederal(cumOtherDirect.getpublications().bigDecimalValue());
                }
                if (cumOtherDirect.getPublicationsCostSharing() != null) {
                    totalPublication.setNonFederal(cumOtherDirect.getPublicationsCostSharing().bigDecimalValue());
                    if (cumOtherDirect.getpublications() != null) {
                        totalPublication.setTotalFedNonFed(cumOtherDirect.getpublications().add(
                                cumOtherDirect.getPublicationsCostSharing()).bigDecimalValue());
                    }
                    else {
                        totalPublication.setTotalFedNonFed(cumOtherDirect.getPublicationsCostSharing().bigDecimalValue());
                    }
                }
                cumulativeOtherDirect.setCumulativePublicationCosts(totalPublication);
                TotalDataType totalConsultant = TotalDataType.Factory.newInstance();
                if (cumOtherDirect.getConsultants() != null) {
                    totalConsultant.setFederal(cumOtherDirect.getConsultants().bigDecimalValue());
                }
                if (cumOtherDirect.getConsultantsCostSharing() != null) {
                    totalConsultant.setNonFederal(cumOtherDirect.getConsultantsCostSharing().bigDecimalValue());
                    if (cumOtherDirect.getConsultants() != null) {
                        totalConsultant.setTotalFedNonFed(cumOtherDirect.getConsultants().add(
                                cumOtherDirect.getConsultantsCostSharing()).bigDecimalValue());
                    }
                    else {
                        totalConsultant.setTotalFedNonFed(cumOtherDirect.getConsultantsCostSharing().bigDecimalValue());
                    }
                }
                cumulativeOtherDirect.setCumulativeConsultantServices(totalConsultant);
                TotalDataType totalComputer = TotalDataType.Factory.newInstance();
                if (cumOtherDirect.getcomputer() != null) {
                    totalComputer.setFederal(cumOtherDirect.getcomputer().bigDecimalValue());
                }
                if (cumOtherDirect.getComputerCostSharing() != null) {
                    totalComputer.setNonFederal(cumOtherDirect.getComputerCostSharing().bigDecimalValue());
                    if (cumOtherDirect.getcomputer() != null) {
                        totalComputer.setTotalFedNonFed(cumOtherDirect.getcomputer().add(cumOtherDirect.getComputerCostSharing())
                                .bigDecimalValue());
                    }
                    else {
                        totalComputer.setTotalFedNonFed(cumOtherDirect.getComputerCostSharing().bigDecimalValue());
                    }
                }
                cumulativeOtherDirect.setCumulativeADPComputerServices(totalComputer);
                TotalDataType totalSubaward = TotalDataType.Factory.newInstance();
                if (cumOtherDirect.getsubAwards() != null) {
                    totalSubaward.setFederal(cumOtherDirect.getsubAwards().bigDecimalValue());
                }
                if (cumOtherDirect.getSubAwardsCostSharing() != null) {
                    totalSubaward.setNonFederal(cumOtherDirect.getSubAwardsCostSharing().bigDecimalValue());
                    if (cumOtherDirect.getsubAwards() != null) {
                        totalSubaward.setTotalFedNonFed(cumOtherDirect.getsubAwards().add(cumOtherDirect.getSubAwardsCostSharing())
                                .bigDecimalValue());
                    }
                    else {
                        totalSubaward.setTotalFedNonFed(cumOtherDirect.getSubAwardsCostSharing().bigDecimalValue());
                    }
                }
                cumulativeOtherDirect.setCumulativeSubawardConsortiumContractualCosts(totalSubaward);
                TotalDataType totalEquipment = TotalDataType.Factory.newInstance();
                if (cumOtherDirect.getEquipRental() != null) {
                    totalEquipment.setFederal(cumOtherDirect.getEquipRental().bigDecimalValue());
                }
                if (cumOtherDirect.getEquipRentalCostSharing() != null) {
                    totalEquipment.setNonFederal(cumOtherDirect.getEquipRentalCostSharing().bigDecimalValue());
                    totalEquipment.setTotalFedNonFed(cumOtherDirect.getEquipRental()
                            .add(cumOtherDirect.getEquipRentalCostSharing()).bigDecimalValue());
                }
                cumulativeOtherDirect.setCumulativeEquipmentFacilityRentalFees(totalEquipment);
                cumulativeOtherDirect.setCumulativeEquipmentFacilityRentalFees(totalEquipment);
                TotalDataType totalAlterations = TotalDataType.Factory.newInstance();
                if (cumOtherDirect.getAlterations() != null) {
                    totalAlterations.setFederal(cumOtherDirect.getAlterations().bigDecimalValue());
                }
                if (cumOtherDirect.getAlterationsCostSharing() != null) {
                    totalAlterations.setNonFederal(cumOtherDirect.getAlterationsCostSharing().bigDecimalValue());
                    if (cumOtherDirect.getAlterations() != null) {
                        totalAlterations.setTotalFedNonFed(cumOtherDirect.getAlterations().add(
                                cumOtherDirect.getAlterationsCostSharing()).bigDecimalValue());
                    }
                    else {
                        totalAlterations.setTotalFedNonFed(cumOtherDirect.getAlterationsCostSharing().bigDecimalValue());
                    }
                }
                cumulativeOtherDirect.setCumulativeAlterationsAndRenovations(totalAlterations);
                TotalDataType totalOther = TotalDataType.Factory.newInstance();
                if (cumOtherDirect.getOtherCosts() != null && cumOtherDirect.getOtherCosts().size() > 0) {
                    totalOther.setFederal(new BigDecimal(cumOtherDirect.getOtherCosts().get(0).get(S2SConstants.KEY_COST)));
                    totalOther
                            .setNonFederal(new BigDecimal(cumOtherDirect.getOtherCosts().get(0).get(S2SConstants.KEY_COSTSHARING)));
                }
                if (totalOther.getFederal() != null && totalOther.getNonFederal() != null) {
                    totalOther.setTotalFedNonFed(totalOther.getFederal().add(totalOther.getNonFederal()));
                }
                cumulativeOtherDirect.setCumulativeOther1DirectCost(totalOther);
            }
        }
        cumulativeOtherDirect.setCumulativeTotalFundsRequestedOtherDirectCosts(summary);
        return cumulativeOtherDirect;
    }

    /**
     * This method gets CumulativeTrainee details,
     * CumulativeTotalFundsRequestedTraineeCosts,CumulativeTraineeTuitionFeesHealthInsurance
     * CumulativeTraineeStipends,CumulativeTraineeTravel,CumulativeTraineeSubsistence,CumulativeOtherTraineeCost and
     * CumulativeNoofTrainees based on BudgetSummaryInfo for the RRFedNonFedBudget.
     * 
     * @param budgetSummaryData (BudgetSummaryInfo) budget summary entry.
     * @return CumulativeTrainee corresponding to the BudgetSummaryInfo object.
     */
    private CumulativeTrainee getCumulativeTrainee(BudgetSummaryInfo budgetSummaryData) {
        CumulativeTrainee cumulativeTrainee = CumulativeTrainee.Factory.newInstance();
        SummaryDataType summaryTraineeCosts = SummaryDataType.Factory.newInstance();
        if (budgetSummaryData != null) {
            if (budgetSummaryData.getpartOtherCost() != null && budgetSummaryData.getpartStipendCost() != null
                    && budgetSummaryData.getpartTravelCost() != null && budgetSummaryData.getPartSubsistence() != null
                    && budgetSummaryData.getPartTuition() != null) {
                summaryTraineeCosts.setFederalSummary(budgetSummaryData.getpartOtherCost().add(
                        budgetSummaryData.getpartStipendCost().add(
                                budgetSummaryData.getpartTravelCost().add(
                                        budgetSummaryData.getPartSubsistence().add(budgetSummaryData.getPartTuition()))))
                        .bigDecimalValue());
            }
            if (budgetSummaryData.getPartOtherCostSharing() != null && budgetSummaryData.getPartStipendCostSharing() != null
                    && budgetSummaryData.getPartTravelCostSharing() != null
                    && budgetSummaryData.getPartSubsistenceCostSharing() != null
                    && budgetSummaryData.getPartTuitionCostSharing() != null) {
                summaryTraineeCosts.setNonFederalSummary(budgetSummaryData.getPartOtherCostSharing().add(
                        budgetSummaryData.getPartStipendCostSharing().add(
                                budgetSummaryData.getPartTravelCostSharing().add(
                                        budgetSummaryData.getPartSubsistenceCostSharing().add(
                                                budgetSummaryData.getPartTuitionCostSharing())))).bigDecimalValue());
            }

            if (summaryTraineeCosts.getNonFederalSummary() != null) {
                if (summaryTraineeCosts.getFederalSummary() != null) {
                    summaryTraineeCosts.setTotalFedNonFedSummary(summaryTraineeCosts.getFederalSummary().add(
                            summaryTraineeCosts.getNonFederalSummary()));
                }

                else {
                    summaryTraineeCosts.setTotalFedNonFedSummary(summaryTraineeCosts.getNonFederalSummary());
                }
            }
            TotalDataType totalTuition = TotalDataType.Factory.newInstance();
            if (budgetSummaryData.getPartTuition() != null) {
                totalTuition.setFederal(budgetSummaryData.getPartTuition().bigDecimalValue());
            }
            if (budgetSummaryData.getPartTuitionCostSharing() != null) {
                totalTuition.setNonFederal(budgetSummaryData.getPartTuitionCostSharing().bigDecimalValue());
                if (budgetSummaryData.getPartTuition() != null) {
                    totalTuition.setTotalFedNonFed(budgetSummaryData.getPartTuition().add(
                            budgetSummaryData.getPartTuitionCostSharing()).bigDecimalValue());
                }
                else {
                    totalTuition.setTotalFedNonFed(budgetSummaryData.getPartTuitionCostSharing().bigDecimalValue());
                }
            }
            cumulativeTrainee.setCumulativeTraineeTuitionFeesHealthInsurance(totalTuition);
            TotalDataType totalStipends = TotalDataType.Factory.newInstance();
            if (budgetSummaryData.getpartStipendCost() != null) {
                totalStipends.setFederal(budgetSummaryData.getpartStipendCost().bigDecimalValue());
            }
            if (budgetSummaryData.getPartStipendCostSharing() != null) {
                totalStipends.setNonFederal(budgetSummaryData.getPartStipendCostSharing().bigDecimalValue());
                if (budgetSummaryData.getpartStipendCost() != null) {
                    totalStipends.setTotalFedNonFed(budgetSummaryData.getpartStipendCost().add(
                            budgetSummaryData.getPartStipendCostSharing()).bigDecimalValue());
                }
                else {
                    totalStipends.setTotalFedNonFed(budgetSummaryData.getPartStipendCostSharing().bigDecimalValue());
                }
            }
            cumulativeTrainee.setCumulativeTraineeStipends(totalStipends);
            TotalDataType totalTravel = TotalDataType.Factory.newInstance();
            if (budgetSummaryData.getpartTravelCost() != null) {
                totalTravel.setFederal(budgetSummaryData.getpartTravelCost().bigDecimalValue());
            }
            if (budgetSummaryData.getPartTravelCostSharing() != null) {
                totalTravel.setNonFederal(budgetSummaryData.getPartTravelCostSharing().bigDecimalValue());
                if (budgetSummaryData.getpartTravelCost() != null) {
                    totalTravel.setTotalFedNonFed(budgetSummaryData.getpartTravelCost().add(
                            budgetSummaryData.getPartTravelCostSharing()).bigDecimalValue());
                }
                else {
                    totalTravel.setTotalFedNonFed(budgetSummaryData.getPartTravelCostSharing().bigDecimalValue());
                }
            }
            cumulativeTrainee.setCumulativeTraineeTravel(totalTravel);
            TotalDataType totalSubsistence = TotalDataType.Factory.newInstance();
            if (budgetSummaryData.getPartSubsistence() != null) {
                totalSubsistence.setFederal(budgetSummaryData.getPartSubsistence().bigDecimalValue());
            }
            if (budgetSummaryData.getPartSubsistenceCostSharing() != null) {
                totalSubsistence.setNonFederal(budgetSummaryData.getPartSubsistenceCostSharing().bigDecimalValue());
                if (budgetSummaryData.getPartSubsistence() != null) {
                    totalSubsistence.setTotalFedNonFed(budgetSummaryData.getPartSubsistence().add(
                            budgetSummaryData.getPartSubsistenceCostSharing()).bigDecimalValue());
                }
                else {
                    totalSubsistence.setTotalFedNonFed(budgetSummaryData.getPartSubsistenceCostSharing().bigDecimalValue());
                }
            }
            cumulativeTrainee.setCumulativeTraineeSubsistence(totalSubsistence);
            TotalDataType totalOtherTrainee = TotalDataType.Factory.newInstance();
            if (budgetSummaryData.getpartOtherCost() != null) {
                totalOtherTrainee.setFederal(budgetSummaryData.getpartOtherCost().bigDecimalValue());
            }
            if (budgetSummaryData.getPartOtherCostSharing() != null) {
                totalOtherTrainee.setNonFederal(budgetSummaryData.getPartOtherCostSharing().bigDecimalValue());
                if (budgetSummaryData.getpartOtherCost() != null) {
                    totalOtherTrainee.setTotalFedNonFed(budgetSummaryData.getpartOtherCost().add(
                            budgetSummaryData.getPartOtherCostSharing()).bigDecimalValue());
                }
                else {
                    totalOtherTrainee.setTotalFedNonFed(budgetSummaryData.getPartOtherCostSharing().bigDecimalValue());
                }
            }
            cumulativeTrainee.setCumulativeOtherTraineeCost(totalOtherTrainee);
            cumulativeTrainee.setCumulativeNoofTrainees(budgetSummaryData.getparticipantCount());
        }
        cumulativeTrainee.setCumulativeTotalFundsRequestedTraineeCosts(summaryTraineeCosts);
        return cumulativeTrainee;
    }

    /**
     * This method gets CumulativeEquipments information CumulativeTotalFundsRequestedEquipment based on BudgetSummaryInfo for the
     * form RRFedNonFedBudget.
     * 
     * @param budgetSummaryData (BudgetSummaryInfo) budget summary entry.
     * @return CumulativeEquipments corresponding to the BudgetSummaryInfo object.
     */
    private CumulativeEquipments getCumulativeEquipments(BudgetSummaryInfo budgetSummaryData) {

        CumulativeEquipments cumulativeEquipments = CumulativeEquipments.Factory.newInstance();
        if (budgetSummaryData != null) {
            SummaryDataType summary = SummaryDataType.Factory.newInstance();
            if (budgetSummaryData.getCumEquipmentFunds() != null) {
                summary.setFederalSummary(budgetSummaryData.getCumEquipmentFunds().bigDecimalValue());
            }
            if (budgetSummaryData.getCumEquipmentNonFunds() != null) {
                summary.setNonFederalSummary(budgetSummaryData.getCumEquipmentNonFunds().bigDecimalValue());
                if (budgetSummaryData.getCumEquipmentFunds() != null) {
                    summary.setTotalFedNonFedSummary(budgetSummaryData.getCumEquipmentFunds().add(
                            budgetSummaryData.getCumEquipmentNonFunds()).bigDecimalValue());
                }
                else {
                    summary.setTotalFedNonFedSummary(budgetSummaryData.getCumEquipmentNonFunds().bigDecimalValue());
                }
            }
            cumulativeEquipments.setCumulativeTotalFundsRequestedEquipment(summary);
        }
        return cumulativeEquipments;
    }

    /**
     * This method gets CumulativeTravels details,CumulativeTotalFundsRequestedTravel,CumulativeDomesticTravelCosts and
     * CumulativeForeignTravelCosts based on BudgetSummaryInfo for the RRFedNonFedBudget.
     * 
     * @param budgetSummaryData (BudgetSummaryInfo) budget summary entry.
     * @return CumulativeTravels corresponding to the BudgetSummaryInfo object.
     */
    private CumulativeTravels getCumulativeTravels(BudgetSummaryInfo budgetSummaryData) {
        CumulativeTravels cumulativeTravels = CumulativeTravels.Factory.newInstance();
        SummaryDataType summary = SummaryDataType.Factory.newInstance();
        if (budgetSummaryData != null) {
            if (budgetSummaryData.getCumTravel() != null) {
                summary.setFederalSummary(budgetSummaryData.getCumTravel().bigDecimalValue());
            }
            if (budgetSummaryData.getCumTravelNonFund() != null) {
                summary.setNonFederalSummary(budgetSummaryData.getCumTravelNonFund().bigDecimalValue());
                if (budgetSummaryData.getCumTravel() != null) {
                    summary.setTotalFedNonFedSummary(budgetSummaryData.getCumTravel().add(budgetSummaryData.getCumTravelNonFund())
                            .bigDecimalValue());
                }
                else {
                    summary.setTotalFedNonFedSummary(budgetSummaryData.getCumTravelNonFund().bigDecimalValue());
                }
            }
            TotalDataType totalDomestic = TotalDataType.Factory.newInstance();
            if (budgetSummaryData.getCumDomesticTravel() != null) {
                totalDomestic.setFederal(budgetSummaryData.getCumDomesticTravel().bigDecimalValue());
            }
            if (budgetSummaryData.getCumDomesticTravelNonFund() != null) {
                totalDomestic.setNonFederal(budgetSummaryData.getCumDomesticTravelNonFund().bigDecimalValue());
                if (budgetSummaryData.getCumDomesticTravel() != null) {
                    totalDomestic.setTotalFedNonFed(budgetSummaryData.getCumDomesticTravel().add(
                            budgetSummaryData.getCumDomesticTravelNonFund()).bigDecimalValue());
                }
                else {
                    totalDomestic.setTotalFedNonFed(budgetSummaryData.getCumDomesticTravelNonFund().bigDecimalValue());
                }
            }
            cumulativeTravels.setCumulativeDomesticTravelCosts(totalDomestic);
            TotalDataType totalForeign = TotalDataType.Factory.newInstance();
            if (budgetSummaryData.getCumForeignTravel() != null) {
                totalForeign.setFederal(budgetSummaryData.getCumForeignTravel().bigDecimalValue());
            }
            if (budgetSummaryData.getCumForeignTravelNonFund() != null) {
                totalForeign.setNonFederal(budgetSummaryData.getCumForeignTravelNonFund().bigDecimalValue());
                if (budgetSummaryData.getCumForeignTravel() != null) {
                    totalForeign.setTotalFedNonFed(budgetSummaryData.getCumForeignTravel().add(
                            budgetSummaryData.getCumForeignTravelNonFund()).bigDecimalValue());
                }
                else {
                    totalForeign.setTotalFedNonFed(budgetSummaryData.getCumForeignTravelNonFund().bigDecimalValue());
                }
            }
            cumulativeTravels.setCumulativeForeignTravelCosts(totalForeign);
        }
        cumulativeTravels.setCumulativeTotalFundsRequestedTravel(summary);
        return cumulativeTravels;
    }

    /**
     * This method gets BudgetYear1DataType details like BudgetPeriodStartDate,BudgetPeriodEndDate,BudgetPeriod
     * KeyPersons,OtherPersonnel,TotalCompensation,Equipment,ParticipantTraineeSupportCosts,Travel,OtherDirectCosts
     * DirectCosts,IndirectCosts,CognizantFederalAgency,TotalCosts  based on BudgetPeriodInfo for
     * the RRFedNonFedBudget.
     * 
     * @param periodInfo (BudgetPeriodInfo) budget period entry.
     * @return BudgetYear1DataType corresponding to the BudgetPeriodInfo object.
     */
    private BudgetYear1DataType getBudgetYear1DataType(BudgetPeriodInfo periodInfo) {

        BudgetYear1DataType budgetYear = BudgetYear1DataType.Factory.newInstance();
        if (periodInfo != null) {
            budgetYear.setBudgetPeriodStartDate(s2sUtilService.convertDateToCalendar(periodInfo.getStartDate()));
            budgetYear.setBudgetPeriodEndDate(s2sUtilService.convertDateToCalendar(periodInfo.getEndDate()));
            gov.grants.apply.forms.rrFedNonFedBudgetV11.BudgetYearDataType.BudgetPeriod.Enum budgetPeriodEnum = gov.grants.apply.forms.rrFedNonFedBudgetV11.BudgetYearDataType.BudgetPeriod.Enum
                    .forInt(periodInfo.getBudgetPeriod());
            budgetYear.setBudgetPeriod(budgetPeriodEnum);
            budgetYear.setKeyPersons(getKeyPersons(periodInfo));
            budgetYear.setOtherPersonnel(getOtherPersonnel(periodInfo));
            SummaryDataType summary = SummaryDataType.Factory.newInstance();
            if (periodInfo.getTotalCompensation() != null) {
                summary.setFederalSummary(periodInfo.getTotalCompensation().bigDecimalValue());
            }
            if (periodInfo.getTotalCompensationCostSharing() != null) {
                summary.setNonFederalSummary(periodInfo.getTotalCompensationCostSharing().bigDecimalValue());
                if (periodInfo.getTotalCompensation() != null) {
                    summary.setTotalFedNonFedSummary(periodInfo.getTotalCompensation().add(
                            periodInfo.getTotalCompensationCostSharing()).bigDecimalValue());
                }
                else {
                    summary.setTotalFedNonFedSummary(periodInfo.getTotalCompensationCostSharing().bigDecimalValue());
                }
            }
            budgetYear.setTotalCompensation(summary);
            budgetYear.setEquipment(getEquipment(periodInfo));
            budgetYear.setTravel(getTravel(periodInfo));
            budgetYear.setParticipantTraineeSupportCosts(getParticipantTraineeSupportCosts(periodInfo));
            budgetYear.setOtherDirectCosts(getOtherDirectCosts(periodInfo));
            SummaryDataType summaryDirect = SummaryDataType.Factory.newInstance();
            if (periodInfo.getDirectCostsTotal() != null) {
                summaryDirect.setFederalSummary(periodInfo.getDirectCostsTotal().bigDecimalValue());
            }
            BudgetDecimal totalDirectCostSharing = periodInfo.getTotalDirectCostSharing();
            summaryDirect.setNonFederalSummary(totalDirectCostSharing.bigDecimalValue());
            if (periodInfo.getDirectCostsTotal() != null) {
                summaryDirect.setTotalFedNonFedSummary(periodInfo.getDirectCostsTotal().add(totalDirectCostSharing).bigDecimalValue());
            }
            else {
                summaryDirect.setTotalFedNonFedSummary(totalDirectCostSharing.bigDecimalValue());
            }
            budgetYear.setDirectCosts(summaryDirect);
            budgetYear.setIndirectCosts(getIndirectCosts(periodInfo));
            budgetYear.setCognizantFederalAgency(periodInfo.getCognizantFedAgency());
            SummaryDataType summaryTotal = SummaryDataType.Factory.newInstance();
            if (periodInfo.getTotalCosts() != null) {
                summaryTotal.setFederalSummary(periodInfo.getTotalCosts().bigDecimalValue());
            }
            if (periodInfo.getCostSharingAmount() != null) {
                summaryTotal.setNonFederalSummary(periodInfo.getCostSharingAmount().bigDecimalValue());
                if (periodInfo.getTotalCosts() != null) {
                    summaryTotal.setTotalFedNonFedSummary(periodInfo.getTotalCosts().add(periodInfo.getCostSharingAmount())
                            .bigDecimalValue());
                }
                else {
                    summaryTotal.setTotalFedNonFedSummary(periodInfo.getCostSharingAmount().bigDecimalValue());
                }
            }
            budgetYear.setTotalCosts(summaryTotal);
        }
        
        return budgetYear;
    }
    /*
     * This method gets BudgetJustificationAttachment from proposalDevelopmentDocument for the RRFedNonFedBudget.
     */
    private BudgetYear1DataType getBudgetJustificationAttachment() {
        BudgetYear1DataType budgetYear = BudgetYear1DataType.Factory.newInstance();
        AttachedFileDataType attachedFileDataType = null;
        for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeTypeCode() != null
                    && Integer.parseInt(narrative.getNarrativeTypeCode()) == BUDGET_JUSTIFICATION_ATTACHMENT) {
                attachedFileDataType = getAttachedFileType(narrative);
                if(attachedFileDataType != null){
                    budgetYear.setBudgetJustificationAttachment(attachedFileDataType);
                    break;
                }
            }
        }
        return budgetYear;
    }
    /**
     * This method returns IndirectCosts details such as Base,CostType,FundRequested,Rate and TotalIndirectCosts in
     * BudgetYearDataType based on BudgetPeriodInfo for the RRFedNonFedBudget.
     * 
     * @param periodInfo (BudgetPeriodInfo) budget period entry.
     * @return IndirectCosts corresponding to the BudgetPeriodInfo object.
     */
    private IndirectCosts getIndirectCosts(BudgetPeriodInfo periodInfo) {

        IndirectCosts indirectCosts = null;
        if (periodInfo != null && periodInfo.getIndirectCosts() != null
                && periodInfo.getIndirectCosts().getIndirectCostDetails() != null) {

            int IndirectCostCount = 0;
            List<IndirectCosts.IndirectCost> indirectCostList = new ArrayList<IndirectCosts.IndirectCost>();
            for (IndirectCostDetails indirectCostDetails : periodInfo.getIndirectCosts().getIndirectCostDetails()) {
                IndirectCosts.IndirectCost indirectCost = IndirectCosts.IndirectCost.Factory.newInstance();
                indirectCost.setCostType(indirectCostDetails.getCostType());
                if (indirectCostDetails.getBase() != null) {
                    indirectCost.setBase(indirectCostDetails.getBase().bigDecimalValue());
                }
                if (indirectCostDetails.getRate() != null) {
                    indirectCost.setRate(indirectCostDetails.getRate().bigDecimalValue());
                }
                TotalDataType total = TotalDataType.Factory.newInstance();
                if (indirectCostDetails.getFunds() != null) {
                    total.setFederal(indirectCostDetails.getFunds().bigDecimalValue());
                }
                if (indirectCostDetails.getCostSharing() != null) {
                    total.setNonFederal(indirectCostDetails.getCostSharing().bigDecimalValue());
                    if (indirectCostDetails.getFunds() != null) {
                        total.setTotalFedNonFed(indirectCostDetails.getFunds().add(indirectCostDetails.getCostSharing())
                                .bigDecimalValue());
                    }
                    else {
                        total.setTotalFedNonFed(indirectCostDetails.getCostSharing().bigDecimalValue());
                    }
                }
                indirectCost.setFundRequested(total);
                indirectCostList.add(indirectCost);
                IndirectCostCount++;
                if (IndirectCostCount == ARRAY_LIMIT_IN_SCHEMA) {
                    LOG.warn("Stopping iteration over indirect cost details because array limit in schema is only 4");
                    break;
                }
            }
            if (IndirectCostCount > 0) {
                indirectCosts = IndirectCosts.Factory.newInstance();
                IndirectCosts.IndirectCost indirectCostArray[] = new IndirectCosts.IndirectCost[0];
                indirectCosts.setIndirectCostArray(indirectCostList.toArray(indirectCostArray));
                SummaryDataType summary = SummaryDataType.Factory.newInstance();
                if (periodInfo.getIndirectCosts().getTotalIndirectCosts() != null) {
                    summary.setFederalSummary(periodInfo.getIndirectCosts().getTotalIndirectCosts().bigDecimalValue());
                }
                if (periodInfo.getIndirectCosts().getTotalIndirectCostSharing() != null) {
                    summary.setNonFederalSummary(periodInfo.getIndirectCosts().getTotalIndirectCostSharing().bigDecimalValue());
                    if (periodInfo.getIndirectCosts().getTotalIndirectCosts() != null) {
                        summary.setTotalFedNonFedSummary(periodInfo.getIndirectCosts().getTotalIndirectCosts().add(
                                periodInfo.getIndirectCosts().getTotalIndirectCostSharing()).bigDecimalValue());
                    }
                    else {
                        summary.setTotalFedNonFedSummary(periodInfo.getIndirectCosts().getTotalIndirectCostSharing()
                                .bigDecimalValue());
                    }
                }
                indirectCosts.setTotalIndirectCosts(summary);
            }
        }
        return indirectCosts;
    }


    /**
     * This method gets OtherDirectCosts details such as PublicationCosts,MaterialsSupplies,ConsultantServices,
     * ADPComputerServices,SubawardConsortiumContractualCosts,EquipmentRentalFee,AlterationsRenovations and TotalOtherDirectCost in
     * BudgetYearDataType based on BudgetPeriodInfo for the RRFedNonFedBudget.
     * 
     * @param periodInfo (BudgetPeriodInfo) budget period entry.
     * @return OtherDirectCosts corresponding to the BudgetPeriodInfo object.
     */
    private OtherDirectCosts getOtherDirectCosts(BudgetPeriodInfo periodInfo) {

        OtherDirectCosts otherDirectCosts = OtherDirectCosts.Factory.newInstance();
        TotalDataType totalMaterials = TotalDataType.Factory.newInstance();
        if (periodInfo != null && periodInfo.getOtherDirectCosts() != null && periodInfo.getOtherDirectCosts().size() > 0) {
            if (periodInfo.getOtherDirectCosts().get(0).getmaterials() != null) {
                totalMaterials.setFederal(periodInfo.getOtherDirectCosts().get(0).getmaterials().bigDecimalValue());
            }
            if (periodInfo.getOtherDirectCosts().get(0).getMaterialsCostSharing() != null) {
                totalMaterials.setNonFederal(periodInfo.getOtherDirectCosts().get(0).getMaterialsCostSharing().bigDecimalValue());
                if (periodInfo.getOtherDirectCosts().get(0).getmaterials() != null) {
                    totalMaterials.setTotalFedNonFed(periodInfo.getOtherDirectCosts().get(0).getmaterials().add(
                            periodInfo.getOtherDirectCosts().get(0).getMaterialsCostSharing()).bigDecimalValue());
                }
                else {
                    totalMaterials.setTotalFedNonFed(periodInfo.getOtherDirectCosts().get(0).getMaterialsCostSharing()
                            .bigDecimalValue());
                }
            }
            otherDirectCosts.setMaterialsSupplies(totalMaterials);
            TotalDataType totalPublication = TotalDataType.Factory.newInstance();
            if (periodInfo.getOtherDirectCosts().get(0).getpublications() != null) {
                totalPublication.setFederal(periodInfo.getOtherDirectCosts().get(0).getpublications().bigDecimalValue());
            }
            if (periodInfo.getOtherDirectCosts().get(0).getPublicationsCostSharing() != null) {
                totalPublication.setNonFederal(periodInfo.getOtherDirectCosts().get(0).getPublicationsCostSharing()
                        .bigDecimalValue());
                if (periodInfo.getOtherDirectCosts().get(0).getpublications() != null) {
                    totalPublication.setTotalFedNonFed(periodInfo.getOtherDirectCosts().get(0).getpublications().add(
                            periodInfo.getOtherDirectCosts().get(0).getPublicationsCostSharing()).bigDecimalValue());
                }
                else {
                    totalPublication.setTotalFedNonFed(periodInfo.getOtherDirectCosts().get(0).getPublicationsCostSharing()
                            .bigDecimalValue());
                }
            }
            otherDirectCosts.setPublicationCosts(totalPublication);
            TotalDataType totalConsultant = TotalDataType.Factory.newInstance();
            if (periodInfo.getOtherDirectCosts().get(0).getConsultants() != null) {
                totalConsultant.setFederal(periodInfo.getOtherDirectCosts().get(0).getConsultants().bigDecimalValue());
            }
            if (periodInfo.getOtherDirectCosts().get(0).getConsultantsCostSharing() != null) {
                totalConsultant
                        .setNonFederal(periodInfo.getOtherDirectCosts().get(0).getConsultantsCostSharing().bigDecimalValue());
                if (periodInfo.getOtherDirectCosts().get(0).getConsultants() != null) {
                    totalConsultant.setTotalFedNonFed(periodInfo.getOtherDirectCosts().get(0).getConsultants().add(
                            periodInfo.getOtherDirectCosts().get(0).getConsultantsCostSharing()).bigDecimalValue());
                }
                else {
                    totalConsultant.setTotalFedNonFed(periodInfo.getOtherDirectCosts().get(0).getConsultantsCostSharing()
                            .bigDecimalValue());
                }
            }
            otherDirectCosts.setConsultantServices(totalConsultant);
            TotalDataType totalADP = TotalDataType.Factory.newInstance();
            if (periodInfo.getOtherDirectCosts().get(0).getcomputer() != null) {
                totalADP.setFederal(periodInfo.getOtherDirectCosts().get(0).getcomputer().bigDecimalValue());
            }
            if (periodInfo.getOtherDirectCosts().get(0).getComputerCostSharing() != null) {
                totalADP.setNonFederal(periodInfo.getOtherDirectCosts().get(0).getComputerCostSharing().bigDecimalValue());
                if (periodInfo.getOtherDirectCosts().get(0).getcomputer() != null) {
                    totalADP.setTotalFedNonFed(periodInfo.getOtherDirectCosts().get(0).getcomputer().add(
                            periodInfo.getOtherDirectCosts().get(0).getComputerCostSharing()).bigDecimalValue());
                }
                else {
                    totalADP.setTotalFedNonFed(periodInfo.getOtherDirectCosts().get(0).getComputerCostSharing().bigDecimalValue());
                }
            }
            otherDirectCosts.setADPComputerServices(totalADP);
            TotalDataType totalSubaward = TotalDataType.Factory.newInstance();
            if (periodInfo.getOtherDirectCosts().get(0).getsubAwards() != null) {
                totalSubaward.setFederal(periodInfo.getOtherDirectCosts().get(0).getsubAwards().bigDecimalValue());
            }
            if (periodInfo.getOtherDirectCosts().get(0).getSubAwardsCostSharing() != null) {
                totalSubaward.setNonFederal((periodInfo.getOtherDirectCosts().get(0).getSubAwardsCostSharing().bigDecimalValue()));
                if (periodInfo.getOtherDirectCosts().get(0).getsubAwards() != null) {
                    totalSubaward.setTotalFedNonFed(periodInfo.getOtherDirectCosts().get(0).getsubAwards().add(
                            periodInfo.getOtherDirectCosts().get(0).getSubAwardsCostSharing()).bigDecimalValue());
                }
                else {
                    totalSubaward.setTotalFedNonFed(periodInfo.getOtherDirectCosts().get(0).getSubAwardsCostSharing()
                            .bigDecimalValue());
                }
            }
            otherDirectCosts.setSubawardConsortiumContractualCosts(totalSubaward);
            TotalDataType totalEquipment = TotalDataType.Factory.newInstance();
            if (periodInfo.getOtherDirectCosts().get(0).getEquipRental() != null) {
                totalEquipment.setFederal(periodInfo.getOtherDirectCosts().get(0).getEquipRental().bigDecimalValue());
            }
            if (periodInfo.getOtherDirectCosts().get(0).getEquipRentalCostSharing() != null) {
                totalEquipment.setNonFederal(periodInfo.getOtherDirectCosts().get(0).getEquipRentalCostSharing().bigDecimalValue());
                if (periodInfo.getOtherDirectCosts().get(0).getEquipRental() != null) {
                    totalEquipment.setTotalFedNonFed(periodInfo.getOtherDirectCosts().get(0).getEquipRental().add(
                            periodInfo.getOtherDirectCosts().get(0).getEquipRentalCostSharing()).bigDecimalValue());
                }
                else {
                    totalEquipment.setTotalFedNonFed(periodInfo.getOtherDirectCosts().get(0).getEquipRentalCostSharing()
                            .bigDecimalValue());
                }
            }
            otherDirectCosts.setEquipmentRentalFee(totalEquipment);
            TotalDataType totalAlterations = TotalDataType.Factory.newInstance();
            if (periodInfo.getOtherDirectCosts().get(0).getAlterations() != null) {
                totalAlterations.setFederal(periodInfo.getOtherDirectCosts().get(0).getAlterations().bigDecimalValue());
            }
            if (periodInfo.getOtherDirectCosts().get(0).getAlterationsCostSharing() != null) {
                totalAlterations.setNonFederal(periodInfo.getOtherDirectCosts().get(0).getAlterationsCostSharing()
                        .bigDecimalValue());
                if (periodInfo.getOtherDirectCosts().get(0).getAlterations() != null) {
                    totalAlterations.setTotalFedNonFed(periodInfo.getOtherDirectCosts().get(0).getAlterations().add(
                            periodInfo.getOtherDirectCosts().get(0).getAlterationsCostSharing()).bigDecimalValue());
                }
                else {
                    totalAlterations.setTotalFedNonFed(periodInfo.getOtherDirectCosts().get(0).getAlterationsCostSharing()
                            .bigDecimalValue());
                }
            }
            otherDirectCosts.setAlterationsRenovations(totalAlterations);
            otherDirectCosts.setOthers(getOthersForOtherDirectCosts(periodInfo));
            SummaryDataType summary = SummaryDataType.Factory.newInstance();
            if (periodInfo.getOtherDirectCosts().get(0).gettotalOtherDirect() != null) {
                summary.setFederalSummary(periodInfo.getOtherDirectCosts().get(0).gettotalOtherDirect().bigDecimalValue());
            }
            if (periodInfo.getOtherDirectCosts().get(0).getTotalOtherDirectCostSharing() != null) {
                summary.setNonFederalSummary(periodInfo.getOtherDirectCosts().get(0).getTotalOtherDirectCostSharing()
                        .bigDecimalValue());
                if (periodInfo.getOtherDirectCosts().get(0).gettotalOtherDirect() != null) {
                    summary.setTotalFedNonFedSummary(periodInfo.getOtherDirectCosts().get(0).gettotalOtherDirect().add(
                            periodInfo.getOtherDirectCosts().get(0).getTotalOtherDirectCostSharing()).bigDecimalValue());
                }
                else {
                    summary.setTotalFedNonFedSummary(periodInfo.getOtherDirectCosts().get(0).getTotalOtherDirectCostSharing()
                            .bigDecimalValue());
                }
            }
            otherDirectCosts.setTotalOtherDirectCost(summary);
        }
        return otherDirectCosts;
    }

    /**
     * This method is to get Other type description and total cost OtherDirectCosts details in BudgetYearDataType based on
     * BudgetPeriodInfo for the RRFedNonFedBudget.
     * 
     * @param periodInfo (BudgetPeriodInfo) budget period entry.
     * @return Other others for other direct costs corresponding to the BudgetPeriodInfo object.
     */
    private Others getOthersForOtherDirectCosts(BudgetPeriodInfo periodInfo) {

        Others othersDirect = Others.Factory.newInstance();
        if (periodInfo != null && periodInfo.getOtherDirectCosts() != null) {
            Others.Other otherArray[] = new Others.Other[periodInfo.getOtherDirectCosts().size()];
            int Otherscount = 0;
            Others.Other other = Others.Other.Factory.newInstance();
            for (OtherDirectCostInfo otherDirectCostInfo : periodInfo.getOtherDirectCosts()) {
                TotalDataType total = TotalDataType.Factory.newInstance();
                if (otherDirectCostInfo.getOtherCosts() != null && otherDirectCostInfo.getOtherCosts().size() > 0) {
                    total.setFederal(new BigDecimal(otherDirectCostInfo.getOtherCosts().get(0).get(S2SConstants.KEY_COST)));
                    total
                            .setNonFederal(new BigDecimal(otherDirectCostInfo.getOtherCosts().get(0).get(
                                    S2SConstants.KEY_COSTSHARING)));
                    if (otherDirectCostInfo.getOtherCosts().get(0).get(S2SConstants.KEY_COST) != null) {
                        total.setTotalFedNonFed(new BigDecimal(otherDirectCostInfo.getOtherCosts().get(0)
                                .get(S2SConstants.KEY_COST)).add(new BigDecimal(otherDirectCostInfo.getOtherCosts().get(0).get(
                                S2SConstants.KEY_COSTSHARING))));
                    }
                    else {
                        total.setTotalFedNonFed(new BigDecimal(otherDirectCostInfo.getOtherCosts().get(0).get(
                                S2SConstants.KEY_COSTSHARING)));
                    }
                }
                other.setCost(total);
                other.setDescription(OTHERCOST_DESCRIPTION);
                otherArray[Otherscount] = other;
                Otherscount++;
            }
            othersDirect.setOtherArray(otherArray);
        }
        return othersDirect;
    }

    /**
     * This method gets ParticipantTraineeSupportCosts details in BudgetYearDataType such as TuitionFeeHealthInsurance
     * Stipends,Subsistence,Travel,Other,ParticipantTraineeNumber and TotalCost based on the BudgetPeriodInfo for the
     * RRFedNonFedBudget.
     * 
     * @param periodInfo (BudgetPeriodInfo) budget period entry.
     * @return ParticipantTraineeSupportCosts corresponding to the BudgetPeriodInfo object.
     */
    private ParticipantTraineeSupportCosts getParticipantTraineeSupportCosts(BudgetPeriodInfo periodInfo) {

        ParticipantTraineeSupportCosts traineeSupportCosts = ParticipantTraineeSupportCosts.Factory.newInstance();
        if (periodInfo != null) {
            TotalDataType totalTution = TotalDataType.Factory.newInstance();
            if (periodInfo.getPartTuition() != null) {
                totalTution.setFederal(periodInfo.getPartTuition().bigDecimalValue());
            }
            if (periodInfo.getPartTuitionCostSharing() != null) {
                totalTution.setNonFederal(periodInfo.getPartTuitionCostSharing().bigDecimalValue());
                if (periodInfo.getPartTuition() != null) {
                    totalTution.setTotalFedNonFed(periodInfo.getPartTuition().add(periodInfo.getPartTuitionCostSharing())
                            .bigDecimalValue());
                }
                else {
                    totalTution.setTotalFedNonFed(periodInfo.getPartTuitionCostSharing().bigDecimalValue());
                }
            }
            traineeSupportCosts.setTuitionFeeHealthInsurance(totalTution);
            TotalDataType totalStipends = TotalDataType.Factory.newInstance();
            if (periodInfo.getpartStipendCost() != null) {
                totalStipends.setFederal(periodInfo.getpartStipendCost().bigDecimalValue());
            }
            if (periodInfo.getPartStipendCostSharing() != null) {
                totalStipends.setNonFederal(periodInfo.getPartStipendCostSharing().bigDecimalValue());
                if (periodInfo.getpartStipendCost() != null) {
                    totalStipends.setTotalFedNonFed(periodInfo.getpartStipendCost().add(periodInfo.getPartStipendCostSharing())
                            .bigDecimalValue());
                }
                else {
                    totalStipends.setTotalFedNonFed(periodInfo.getPartStipendCostSharing().bigDecimalValue());
                }
            }
            traineeSupportCosts.setStipends(totalStipends);
            TotalDataType totalTravel = TotalDataType.Factory.newInstance();
            if (periodInfo.getpartTravelCost() != null) {
                totalTravel.setFederal(periodInfo.getpartTravelCost().bigDecimalValue());
            }
            if (periodInfo.getPartTravelCostSharing() != null) {
                totalTravel.setNonFederal(periodInfo.getPartTravelCostSharing().bigDecimalValue());
                if (periodInfo.getpartTravelCost() != null) {
                    totalTravel.setTotalFedNonFed(periodInfo.getpartTravelCost().add(periodInfo.getPartTravelCostSharing())
                            .bigDecimalValue());
                }
                else {
                    totalTravel.setTotalFedNonFed(periodInfo.getPartTravelCostSharing().bigDecimalValue());
                }
            }
            traineeSupportCosts.setTravel(totalTravel);
            TotalDataType totalSubsistence = TotalDataType.Factory.newInstance();
            if (periodInfo.getPartSubsistence() != null) {
                totalSubsistence.setFederal(periodInfo.getPartSubsistence().bigDecimalValue());
            }
            if (periodInfo.getPartSubsistenceCostSharing() != null) {
                totalSubsistence.setNonFederal(periodInfo.getPartSubsistenceCostSharing().bigDecimalValue());
                if (periodInfo.getPartSubsistence() != null) {
                    totalSubsistence.setTotalFedNonFed(periodInfo.getPartSubsistence().add(
                            periodInfo.getPartSubsistenceCostSharing()).bigDecimalValue());
                }
                else {
                    totalSubsistence.setTotalFedNonFed(periodInfo.getPartSubsistenceCostSharing().bigDecimalValue());
                }
            }
            traineeSupportCosts.setSubsistence(totalSubsistence);
            traineeSupportCosts.setOther(getOtherPTSupportCosts(periodInfo));
            traineeSupportCosts.setParticipantTraineeNumber(periodInfo.getparticipantCount());
            SummaryDataType summary = SummaryDataType.Factory.newInstance();
            summary.setFederalSummary(periodInfo.getpartOtherCost().add(
                    periodInfo.getpartStipendCost().add(
                            periodInfo.getpartTravelCost().add(periodInfo.getPartSubsistence().add(periodInfo.getPartTuition()))))
                    .bigDecimalValue());

            summary.setNonFederalSummary(periodInfo.getPartOtherCostSharing().add(
                    periodInfo.getPartStipendCostSharing().add(
                            periodInfo.getPartTravelCostSharing().add(
                                    periodInfo.getPartSubsistenceCostSharing().add(periodInfo.getPartTuitionCostSharing()))))
                    .bigDecimalValue());
            if (summary.getNonFederalSummary() != null) {
                if (summary.getFederalSummary() != null) {
                    summary.setTotalFedNonFedSummary(summary.getFederalSummary().add(summary.getNonFederalSummary()));
                }
                else {
                    summary.setTotalFedNonFedSummary(summary.getNonFederalSummary());
                }
            }
            traineeSupportCosts.setTotalCost(summary);
        }
        return traineeSupportCosts;
    }

    /**
     * This method gets Other type description and total cost for ParticipantTraineeSupportCosts based on BudgetPeriodInfo.
     * 
     * @param periodInfo (BudgetPeriodInfo) budget period entry.
     * @return Other other participant trainee support costs corresponding to the BudgetPeriodInfo object.
     */
    private Other getOtherPTSupportCosts(BudgetPeriodInfo periodInfo) {

        Other other = Other.Factory.newInstance();
        other.setDescription(OTHERCOST_DESCRIPTION);
        TotalDataType total = TotalDataType.Factory.newInstance();
        if (periodInfo != null) {
            if (periodInfo.getpartOtherCost() != null) {
                total.setFederal(periodInfo.getpartOtherCost().bigDecimalValue());
            }
            if (periodInfo.getPartOtherCostSharing() != null) {
                total.setNonFederal(periodInfo.getPartOtherCostSharing().bigDecimalValue());
                if (periodInfo.getpartOtherCost() != null) {
                    total.setTotalFedNonFed(periodInfo.getpartOtherCost().add(periodInfo.getPartOtherCostSharing())
                            .bigDecimalValue());
                }
                else {
                    total.setTotalFedNonFed(periodInfo.getPartOtherCostSharing().bigDecimalValue());
                }
            }
        }
        other.setCost(total);
        return other;
    }

    /**
     * This method gets Equipment details such as EquipmentItem,FundsRequested,TotalFundForAttachedEquipment, TotalFund and
     * AdditionalEquipmentsAttachment based on BudgetPeriodInfo for the RRFedNonFedBudget.
     * 
     * @param periodInfo (BudgetPeriodInfo) budget period entry.
     * @return Equipment cost details corresponding to the BudgetPeriodInfo object.
     */
    private Equipment getEquipment(BudgetPeriodInfo periodInfo) {
        Equipment equipment = Equipment.Factory.newInstance();
        EquipmentList[] equipmentArray = new EquipmentList[0];
        List<EquipmentList> equipmentArrayList = new ArrayList<EquipmentList>();
        if (periodInfo.getEquipment() != null && periodInfo.getEquipment() != null && periodInfo.getEquipment().size() > 0) {
            if (periodInfo.getEquipment() != null) {
                SummaryDataType totalFund = SummaryDataType.Factory.newInstance();
                totalFund.setFederalSummary(BigDecimal.ZERO);
                totalFund.setNonFederalSummary(BigDecimal.ZERO);
                totalFund.setTotalFedNonFedSummary(BigDecimal.ZERO);
                for (CostInfo costInfo : periodInfo.getEquipment().get(0).getEquipmentList()) {
                    EquipmentList equipmentList = EquipmentList.Factory.newInstance();
                    equipmentList.setEquipmentItem(costInfo.getDescription());

                    TotalDataType fundsRequested = TotalDataType.Factory.newInstance();
                    fundsRequested.setFederal(costInfo.getCost().bigDecimalValue());
                    fundsRequested.setNonFederal(costInfo.getCostSharing().bigDecimalValue());
                    fundsRequested.setTotalFedNonFed(costInfo.getCost().add(costInfo.getCostSharing()).bigDecimalValue());

                    //  equipmentList.setNonFederal(costInfo.getCostSharing().bigDecimalValue());
                    //  equipmentList.setTotalFedNonFed(costInfo.getCost().add(costInfo.getCostSharing()).bigDecimalValue());   
                    //  prepare the totals
                    totalFund.setFederalSummary(totalFund.getFederalSummary().add(costInfo.getCost().bigDecimalValue()));
                    totalFund.setNonFederalSummary(totalFund.getNonFederalSummary()
                            .add(costInfo.getCostSharing().bigDecimalValue()));

                    equipmentList.setFundsRequested(fundsRequested);                   
                    equipmentArrayList.add(equipmentList);
                }
                totalFund.setTotalFedNonFedSummary(totalFund.getFederalSummary().add(totalFund.getNonFederalSummary()));
                equipmentArray = equipmentArrayList.toArray(equipmentArray);
                equipment.setEquipmentListArray(equipmentArray);
                equipment.setTotalFund(totalFund);
                // Extra Exuipment Amount Setting.
                EquipmentInfo equipmentInfo = periodInfo.getEquipment().get(0);
                TotalDataType totalFundForExtraEquipment = TotalDataType.Factory.newInstance();
                totalFundForExtraEquipment.setFederal(equipmentInfo.getTotalExtraFund().bigDecimalValue());
                totalFundForExtraEquipment.setNonFederal(equipmentInfo.getTotalExtraNonFund().bigDecimalValue());
                if (equipmentInfo.getTotalExtraFund() != null) {
                	totalFundForExtraEquipment.setTotalFedNonFed(equipmentInfo.getTotalExtraFund().add(equipmentInfo.getTotalExtraNonFund())
                            .bigDecimalValue());
                }
                else {
                	totalFundForExtraEquipment.setTotalFedNonFed(equipmentInfo.getTotalExtraNonFund().bigDecimalValue());
                }
                equipment.setTotalFundForAttachedEquipment(totalFundForExtraEquipment);
                // Save Total Fund.
                SummaryDataType summary = SummaryDataType.Factory.newInstance();
                if (equipmentInfo.getTotalFund() != null) {
                    summary.setFederalSummary(equipmentInfo.getTotalFund().bigDecimalValue());
                }
                if (equipmentInfo.getTotalNonFund() != null) {
                    summary.setNonFederalSummary(equipmentInfo.getTotalNonFund().bigDecimalValue());
                    if (equipmentInfo.getTotalFund() != null) {
                        summary.setTotalFedNonFedSummary(equipmentInfo.getTotalFund().add(equipmentInfo.getTotalNonFund())
                                .bigDecimalValue());
                    }
                    else {
                        summary.setTotalFedNonFedSummary(equipmentInfo.getTotalNonFund().bigDecimalValue());
                    }
                }
                equipment.setTotalFund(summary);
                
            }
        }
        Narrative narrative = saveExtraEquipment(periodInfo);
        AttachedFileDataType attachedFileDataType = null;
        if(narrative!=null){
        	attachedFileDataType = getAttachedFileType(narrative);
        	if(attachedFileDataType != null){
        		equipment.setAdditionalEquipmentsAttachment(attachedFileDataType);
        	}
        }
        return equipment;
    }


	private Narrative saveExtraEquipment(BudgetPeriodInfo periodInfo) {
	    Narrative narrative=null;
		List<CostInfo> extraEquipmentList = periodInfo.getEquipment().get(0).getExtraEquipmentList();
		if (extraEquipmentList.size() > 0) {
			AdditionalEquipmentList additionalEquipmentList = AdditionalEquipmentList.Factory
					.newInstance();
			additionalEquipmentList.setProposalNumber(pdDoc
					.getDevelopmentProposal().getProposalNumber());
			additionalEquipmentList.setBudgetPeriod(new BigInteger(Integer
					.toString(periodInfo.getBudgetPeriod())));

			additionalEquipmentList
					.setEquipmentListArray(getEquipmentListArray(extraEquipmentList));

			AdditionalEquipmentListDocument additionalEquipmentDoc = AdditionalEquipmentListDocument.Factory
					.newInstance();
			additionalEquipmentDoc
					.setAdditionalEquipmentList(additionalEquipmentList);
			Source xsltSource = new StreamSource(
					getClass()
							.getResourceAsStream(
									"/org/kuali/kra/s2s/stylesheet/AdditionalEquipmentAttachmentNonFed.xsl"));
			Map<String, Source> xSLTemplateWithBookmarks = new HashMap<String, Source>();
			xSLTemplateWithBookmarks.put("", xsltSource);

			String xmlData = additionalEquipmentDoc.xmlText();
			Map<String, byte[]> streamMap = new HashMap<String, byte[]>();
			streamMap.put("", xmlData.getBytes());
			GenericPrintable printable = new GenericPrintable();
			printable.setXSLTemplateWithBookmarks(xSLTemplateWithBookmarks);
			printable.setStreamMap(streamMap);
			PrintingService printingService = KraServiceLocator
					.getService(PrintingService.class);
			try {
				AttachmentDataSource printData = printingService
						.print(printable);
				String fileName = pdDoc.getDevelopmentProposal()
						.getProposalNumber()
						+ "_ADDITIONAL_EQUIPMENT.pdf";
				narrative = saveNarrative(printData.getContent(),
						ADDITIONAL_EQUIPMENT_NARRATIVE_TYPE_CODE, fileName,
						ADDITIONAL_EQUIPMENT_NARRATIVE_COMMENT);
			} catch (PrintingException e) {
				e.printStackTrace();
			}
		}
		return narrative;
	}
	private gov.grants.apply.coeus.additionalEquipment.AdditionalEquipmentListDocument.AdditionalEquipmentList.EquipmentList[] getEquipmentListArray(
			List<CostInfo> extraEquipmentArrayList) {
		List<AdditionalEquipmentList.EquipmentList> additionalEquipmentListList = new ArrayList<AdditionalEquipmentList.EquipmentList>();
		AdditionalEquipmentList.EquipmentList equipmentList = null;
		for (CostInfo costInfo : extraEquipmentArrayList) {
			equipmentList = AdditionalEquipmentList.EquipmentList.Factory
					.newInstance();
			equipmentList.setFundsRequested(costInfo.getCost()
					.bigDecimalValue());			
			equipmentList.setNonFederal(costInfo.getCostSharing().bigDecimalValue());
			equipmentList.setTotalFedNonFed(costInfo.getCost().add(costInfo.getCostSharing()).bigDecimalValue());        
	
			equipmentList
					.setEquipmentItem(costInfo.getDescription() != null ? costInfo
							.getDescription()
							: costInfo.getCategory());
			additionalEquipmentListList.add(equipmentList);
		}
		return additionalEquipmentListList
				.toArray(new gov.grants.apply.coeus.additionalEquipment.AdditionalEquipmentListDocument.AdditionalEquipmentList.EquipmentList[0]);
	}
	private Narrative saveExtraKeyPersons(BudgetPeriodInfo periodInfo) {
	    Narrative extraKPNarrative = null;
		if (periodInfo.getExtraKeyPersons() != null && !periodInfo.getExtraKeyPersons().isEmpty()) {
			ExtraKeyPersonListDocument  extraKeyPersonListDocument = ExtraKeyPersonListDocument.Factory.newInstance();
			ExtraKeyPersonList extraKeyPersonList = ExtraKeyPersonList.Factory.newInstance(); 
			extraKeyPersonList.setProposalNumber(pdDoc.getDevelopmentProposal().getProposalNumber());
			extraKeyPersonList.setBudgetPeriod(new BigInteger(""+periodInfo.getBudgetPeriod()));
			extraKeyPersonList.setKeyPersonsArray(getExtraKeyPersons(periodInfo.getExtraKeyPersons()));
			extraKeyPersonListDocument.setExtraKeyPersonList(extraKeyPersonList);
			String xmlData = extraKeyPersonListDocument.xmlText();
			Map<String, byte[]> streamMap = new HashMap<String, byte[]>();
			streamMap.put("", xmlData.getBytes());
			Source xsltSource = new StreamSource(getClass()
					.getResourceAsStream(EXTRA_KEYPERSON_ATTACHMENT_NON_FED_XSL));
			Map<String, Source> xSLTemplateWithBookmarks = new HashMap<String, Source>();
			xSLTemplateWithBookmarks.put("", xsltSource);
			
			GenericPrintable printable = new GenericPrintable();
			printable.setXSLTemplateWithBookmarks(xSLTemplateWithBookmarks);
			printable.setStreamMap(streamMap);
			PrintingService printingService= KraServiceLocator.getService(PrintingService.class);
			try {
				AttachmentDataSource printData = printingService.print(printable);
				String fileName = pdDoc.getDevelopmentProposal().getProposalNumber()+"_"+periodInfo.getBudgetPeriod()+"_"+EXTRA_KEYPERSONS+".pdf";
				extraKPNarrative = saveNarrative(printData.getContent(), ""+EXTRA_KEYPERSONS_TYPE, fileName, EXTRA_KEYPERSONS_COMMENT);
			} catch (PrintingException e) {
				e.printStackTrace();
			}
		}
		return extraKPNarrative;
	}
	private gov.grants.apply.coeus.extraKeyPerson.ExtraKeyPersonListDocument.ExtraKeyPersonList.KeyPersons[] getExtraKeyPersons(List<KeyPersonInfo> keyPersonList) {
		List<gov.grants.apply.coeus.extraKeyPerson.ExtraKeyPersonListDocument.ExtraKeyPersonList.KeyPersons> keypersonslist = new ArrayList<gov.grants.apply.coeus.extraKeyPerson.ExtraKeyPersonListDocument.ExtraKeyPersonList.KeyPersons>();
		for(KeyPersonInfo keyPersonInfo : keyPersonList){
			gov.grants.apply.coeus.extraKeyPerson.ExtraKeyPersonListDocument.ExtraKeyPersonList.KeyPersons keyPerson = gov.grants.apply.coeus.extraKeyPerson.ExtraKeyPersonListDocument.ExtraKeyPersonList.KeyPersons.Factory.newInstance();
			keyPerson.setFirstName(keyPersonInfo.getFirstName());
			keyPerson.setMiddleName(keyPersonInfo.getMiddleName());
			keyPerson.setLastName(keyPersonInfo.getLastName());
			keyPerson.setProjectRole(keyPersonInfo.getRole());
			keyPerson.setCompensation(getExtraKeyPersonCompensation(keyPersonInfo));
			keypersonslist.add(keyPerson);
		}
		return keypersonslist.toArray(new gov.grants.apply.coeus.extraKeyPerson.ExtraKeyPersonListDocument.ExtraKeyPersonList.KeyPersons[0]);
	}
    private Compensation getExtraKeyPersonCompensation(KeyPersonInfo keyPersonInfo) {
        ExtraKeyPersonList.KeyPersons.Compensation compensation = ExtraKeyPersonListDocument.ExtraKeyPersonList.KeyPersons.Compensation.Factory.newInstance();
        compensation.setAcademicMonths(keyPersonInfo.getAcademicMonths().bigDecimalValue());
        compensation.setCalendarMonths(keyPersonInfo.getCalendarMonths().bigDecimalValue());
        compensation.setSummerMonths(keyPersonInfo.getSummerMonths().bigDecimalValue());

         compensation.setBaseSalary(keyPersonInfo.getBaseSalary().bigDecimalValue());
        compensation.setFringeBenefits(keyPersonInfo.getFringe().add(keyPersonInfo.getFringeCostSharing()).bigDecimalValue());
        compensation.setFundsRequested(keyPersonInfo.getFundsRequested().bigDecimalValue());
        compensation.setRequestedSalary(keyPersonInfo.getRequestedSalary().add(keyPersonInfo.getCostSharingAmount()).bigDecimalValue());
        compensation.setNonFederal(keyPersonInfo.getNonFundsRequested().bigDecimalValue());
        if (keyPersonInfo.getFundsRequested() != null){
            compensation.setTotalFedNonFed(keyPersonInfo.getFundsRequested().add(keyPersonInfo.getNonFundsRequested()).bigDecimalValue());
        }else {
            compensation.setTotalFedNonFed(keyPersonInfo.getNonFundsRequested().bigDecimalValue());
        }
        
        return compensation;
    }

    /**
     * This method gets Travel cost information including DomesticTravelCost,ForeignTravelCost and TotalTravelCost in the
     * BudgetYearDataType based on BudgetPeriodInfo for the RRFedNonFedBudget.
     * 
     * @param periodInfo (BudgetPeriodInfo) budget period entry.
     * @return Travel cost details corresponding to the BudgetPeriodInfo object.
     */
    private Travel getTravel(BudgetPeriodInfo periodInfo) {

        Travel travel = Travel.Factory.newInstance();
        if (periodInfo != null) {
            TotalDataType total = TotalDataType.Factory.newInstance();
            if (periodInfo.getDomesticTravelCost() != null) {
                total.setFederal(periodInfo.getDomesticTravelCost().bigDecimalValue());
            }
            if (periodInfo.getDomesticTravelCostSharing() != null) {
                total.setNonFederal(periodInfo.getDomesticTravelCostSharing().bigDecimalValue());
                if (periodInfo.getDomesticTravelCost() != null) {
                    total.setTotalFedNonFed(periodInfo.getDomesticTravelCost().add(periodInfo.getDomesticTravelCostSharing())
                            .bigDecimalValue());
                }
                else {
                    total.setTotalFedNonFed(periodInfo.getDomesticTravelCostSharing().bigDecimalValue());
                }
            }
            travel.setDomesticTravelCost(total);
            TotalDataType totalForeign = TotalDataType.Factory.newInstance();
            if (periodInfo.getForeignTravelCost() != null) {
                totalForeign.setFederal(periodInfo.getForeignTravelCost().bigDecimalValue());
            }
            if (periodInfo.getForeignTravelCostSharing() != null) {
                totalForeign.setNonFederal(periodInfo.getForeignTravelCostSharing().bigDecimalValue());
                if (periodInfo.getForeignTravelCost() != null) {
                    totalForeign.setTotalFedNonFed(periodInfo.getForeignTravelCost().add(periodInfo.getForeignTravelCostSharing())
                            .bigDecimalValue());
                }
                else {
                    totalForeign.setTotalFedNonFed(periodInfo.getForeignTravelCostSharing().bigDecimalValue());
                }
            }
            travel.setForeignTravelCost(totalForeign);
            SummaryDataType summary = SummaryDataType.Factory.newInstance();
            if (periodInfo.getTotalTravelCost() != null) {
                summary.setFederalSummary(periodInfo.getTotalTravelCost().bigDecimalValue());
            }
            if (periodInfo.getTotalTravelCostSharing() != null) {
                summary.setNonFederalSummary(periodInfo.getTotalTravelCostSharing().bigDecimalValue());
                if (periodInfo.getTotalTravelCost() != null) {
                    summary.setTotalFedNonFedSummary(periodInfo.getTotalTravelCost().add(periodInfo.getTotalTravelCostSharing())
                            .bigDecimalValue());
                }
                else {
                    summary.setTotalFedNonFedSummary(periodInfo.getTotalTravelCostSharing().bigDecimalValue());
                }
            }
            travel.setTotalTravelCost(summary);
        }
        return travel;
    }

    /**
     * This method gets OtherPersonnel informations like PostDocAssociates,GraduateStudents,UndergraduateStudents
     * SecretarialClerical based on PersonnelType and also gets NumberOfPersonnel, ProjectRole,Compensation
     * OtherPersonnelTotalNumber and TotalOtherPersonnelFund based on BudgetPeriodInfo for the RRFedNonFedBudget.
     * 
     * @param periodInfo (BudgetPeriodInfo) budget period entry.
     * @return OtherPersonnel details corresponding to the BudgetPeriodInfo object.
     */
    private OtherPersonnel getOtherPersonnel(BudgetPeriodInfo periodInfo) {
        OtherPersonnel otherPersonnel = OtherPersonnel.Factory.newInstance();
        int OtherpersonalCount = 0;
        List<OtherPersonnelDataType> otherPersonnelList = new ArrayList<OtherPersonnelDataType>();
        OtherPersonnelDataType otherPersonnelDataTypeArray[] = new OtherPersonnelDataType[1];
        if (periodInfo != null) {
            if (periodInfo.getOtherPersonnel() != null) {
                for (OtherPersonnelInfo otherPersonnelInfo : periodInfo.getOtherPersonnel()) {
                    if (OTHERPERSONNEL_POSTDOC.equals(otherPersonnelInfo.getPersonnelType())) {
                        otherPersonnel.setPostDocAssociates(getPostDocAssociates(otherPersonnelInfo));
                    }
                    else if (OTHERPERSONNEL_GRADUATE.equals(otherPersonnelInfo.getPersonnelType())) {
                        otherPersonnel.setGraduateStudents(getGraduateStudents(otherPersonnelInfo));
                    }
                    else if (OTHERPERSONNEL_UNDERGRADUATE.equals(otherPersonnelInfo.getPersonnelType())) {
                        otherPersonnel.setUndergraduateStudents(getUndergraduateStudents(otherPersonnelInfo));
                    }
                    else if (OTHERPERSONNEL_SECRETARIAL.equals(otherPersonnelInfo.getPersonnelType())) {
                        otherPersonnel.setSecretarialClerical(getSecretarialClerical(otherPersonnelInfo));
                    }
                    else if (OtherpersonalCount < OTHERPERSONNEL_MAX_ALLOWED) {// Max allowed is 6
                        OtherPersonnelDataType otherPersonnelDataType = OtherPersonnelDataType.Factory.newInstance();
                        otherPersonnelDataType.setNumberOfPersonnel(otherPersonnelInfo.getNumberPersonnel());
                        otherPersonnelDataType.setProjectRole(otherPersonnelInfo.getRole());
                        otherPersonnelDataType.setCompensation(getSectBCompensationDataType(otherPersonnelInfo.getCompensation()));
                        otherPersonnelList.add(otherPersonnelDataType);
                        OtherpersonalCount++;
                    }
                }
                otherPersonnelDataTypeArray = otherPersonnelList.toArray(otherPersonnelDataTypeArray);
                otherPersonnel.setOtherArray(otherPersonnelDataTypeArray);

                if (periodInfo.getOtherPersonnelTotalNumber() != null) {
                    otherPersonnel.setOtherPersonnelTotalNumber(periodInfo.getOtherPersonnelTotalNumber().intValue());
                }
            }
            SummaryDataType summary = SummaryDataType.Factory.newInstance();
            if (periodInfo.getTotalOtherPersonnelFunds() != null) {
                summary.setFederalSummary(periodInfo.getTotalOtherPersonnelFunds().bigDecimalValue());
            }
            if (periodInfo.getTotalOtherPersonnelNonFunds() != null) {
                summary.setNonFederalSummary(periodInfo.getTotalOtherPersonnelNonFunds().bigDecimalValue());
                if (periodInfo.getTotalOtherPersonnelFunds() != null) {
                    summary.setTotalFedNonFedSummary(periodInfo.getTotalOtherPersonnelFunds().add(
                            periodInfo.getTotalOtherPersonnelNonFunds()).bigDecimalValue());
                }
                else {
                    summary.setTotalFedNonFedSummary(periodInfo.getTotalOtherPersonnelNonFunds().bigDecimalValue());
                }
            }
            otherPersonnel.setTotalOtherPersonnelFund(summary);
        }
        return otherPersonnel;
    }

    /**
     * This method gets the PostDocAssociates details,ProjectRole, NumberOfPersonnel,Compensation based on OtherPersonnelInfo for
     * the RRFedNonFedBudget,if it is a PostDocAssociates type.
     * 
     * @param otherPersonnel (OtherPersonnelInfo) other personnel entry.
     * @return PostDocAssociates details corresponding to the OtherPersonnelInfo object.
     */
    private PostDocAssociates getPostDocAssociates(OtherPersonnelInfo otherPersonnel) {

        PostDocAssociates postDoc = PostDocAssociates.Factory.newInstance();
        if (otherPersonnel != null) {
            postDoc.setNumberOfPersonnel(otherPersonnel.getNumberPersonnel());
            postDoc.setProjectRole(otherPersonnel.getRole());
            postDoc.setCompensation(getSectBCompensationDataType(otherPersonnel.getCompensation()));
        }
        return postDoc;
    }

    /**
     * This method gets the GraduateStudents details,ProjectRole, NumberOfPersonnel,Compensation based on OtherPersonnelInfo for the
     * RRFedNonFedBudget, if it is a GraduateStudents type.
     * 
     * @param otherPersonnel (OtherPersonnelInfo) other personnel entry.
     * @return GraduateStudents details corresponding to the OtherPersonnelInfo object.
     */
    private GraduateStudents getGraduateStudents(OtherPersonnelInfo otherPersonnel) {

        GraduateStudents graduate = GraduateStudents.Factory.newInstance();
        if (otherPersonnel != null) {
            graduate.setNumberOfPersonnel(otherPersonnel.getNumberPersonnel());
            graduate.setProjectRole(otherPersonnel.getRole());
            graduate.setCompensation(getSectBCompensationDataType(otherPersonnel.getCompensation()));
        }
        return graduate;
    }

    /**
     * This method is to get the UndergraduateStudents details,ProjectRole, NumberOfPersonnel,Compensation based on
     * OtherPersonnelInfo for the RRFedNonFedBudget,if it is a UndergraduateStudents type.
     * 
     * @param otherPersonnel (OtherPersonnelInfo) other personnel entry.
     * @return UndergraduateStudents details corresponding to the OtherPersonnelInfo object.
     */
    private UndergraduateStudents getUndergraduateStudents(OtherPersonnelInfo otherPersonnel) {

        UndergraduateStudents undergraduate = UndergraduateStudents.Factory.newInstance();
        if (otherPersonnel != null) {
            undergraduate.setNumberOfPersonnel(otherPersonnel.getNumberPersonnel());
            undergraduate.setProjectRole(otherPersonnel.getRole());
            undergraduate.setCompensation(getSectBCompensationDataType(otherPersonnel.getCompensation()));
        }
        return undergraduate;
    }

    /**
     * This method is to get the SecretarialClerical details,ProjectRole, NumberOfPersonnel,Compensation based on OtherPersonnelInfo
     * for the RRFedNonFedBudget,if it is a SecretarialClerical type.
     * 
     * @param otherPersonnel (OtherPersonnelInfo) other personnel entry.
     * @return SecretarialClerical details corresponding to the OtherPersonnelInfo object.
     */
    private SecretarialClerical getSecretarialClerical(OtherPersonnelInfo otherPersonnel) {

        SecretarialClerical secretarial = SecretarialClerical.Factory.newInstance();
        if (otherPersonnel != null) {
            secretarial.setNumberOfPersonnel(otherPersonnel.getNumberPersonnel());
            secretarial.setProjectRole(otherPersonnel.getRole());
            secretarial.setCompensation(getSectBCompensationDataType(otherPersonnel.getCompensation()));
        }
        return secretarial;
    }

    /**
     * This method gets SectBCompensationDataType details AcademicMonths,CalendarMonths,FringeBenefits
     * FundsRequested,SummerMonths,and RequestedSalary based on KeyPersonInfo for the OtherPersonnel.
     * 
     * @param compensation (CompensationInfo) compensation entry.
     * @return SectBCompensationDataType corresponding to the CompensationInfo object.
     */
    private SectBCompensationDataType getSectBCompensationDataType(CompensationInfo compensation) {

        SectBCompensationDataType sectBCompensation = SectBCompensationDataType.Factory.newInstance();
        if (compensation != null) {
            if (compensation.getAcademicMonths() != null) {
                sectBCompensation.setAcademicMonths(compensation.getAcademicMonths().bigDecimalValue());
            }
            if (compensation.getCalendarMonths() != null) {
                sectBCompensation.setCalendarMonths(compensation.getCalendarMonths().bigDecimalValue());
            }
            if (compensation.getSummerMonths() != null) {
                sectBCompensation.setSummerMonths(compensation.getSummerMonths().bigDecimalValue());
            }
            if (compensation.getFringe() != null) {
                sectBCompensation.setFringeBenefits(compensation.getFringe().bigDecimalValue());
            }
            if (compensation.getRequestedSalary() != null) {
                sectBCompensation.setRequestedSalary(compensation.getRequestedSalary().bigDecimalValue());
            }
            TotalDataType totalDataType = TotalDataType.Factory.newInstance();
            if (compensation.getFundsRequested() != null) {
                totalDataType.setFederal(compensation.getFundsRequested().bigDecimalValue());
            }
            if (compensation.getNonFundsRequested() != null) {
                totalDataType.setNonFederal(compensation.getNonFundsRequested().bigDecimalValue());
            }
            if (compensation.getFundsRequested() != null && compensation.getNonFundsRequested() != null)
                totalDataType.setTotalFedNonFed(compensation.getFundsRequested().add(compensation.getNonFundsRequested())
                        .bigDecimalValue());
            sectBCompensation.setOtherTotal(totalDataType);
        }
        return sectBCompensation;
    }

    /**
     * This method gets KeyPersons details such as Name,ProjectRole,Compensation,TotalFundForAttachedKeyPersons
     * TotalFundForKeyPersons and AttachedKeyPersons based on BudgetPeriodInfo for the RRFedNonFedBudget.
     * 
     * @param periodInfo (BudgetPeriodInfo) budget period entry.
     * @return KeyPersons details corresponding to the BudgetPeriodInfo object.
     */
    private KeyPersons getKeyPersons(BudgetPeriodInfo periodInfo) {
        KeyPersons keyPersons = KeyPersons.Factory.newInstance();
        if (periodInfo != null) {
            if (periodInfo.getKeyPersons() != null) {
                KeyPersonDataType[] keyPersonDataTypeArray = new KeyPersonDataType[periodInfo.getKeyPersons().size()];
                int keyPersonCount = 0;
                for (KeyPersonInfo keyPerson : periodInfo.getKeyPersons()) {
                    KeyPersonDataType keyPersonDataType = KeyPersonDataType.Factory.newInstance();
                    keyPersonDataType.setName(globLibV20Generator.getHumanNameDataType(keyPerson));                
                    if(keyPerson.getKeyPersonRole()!=null){
                        keyPersonDataType.setProjectRole(keyPerson.getKeyPersonRole());
                    }
                    else {
                        keyPersonDataType.setProjectRole(keyPerson.getRole());                        
                    }
                    keyPersonDataType.setCompensation(getCompensation(keyPerson));
                    keyPersonDataTypeArray[keyPersonCount] = keyPersonDataType;
                    keyPersonCount++;
                    LOG.info("keyPersonCount:" + keyPersonCount);
                }
                keyPersons.setKeyPersonArray(keyPersonDataTypeArray);
            }
            SummaryDataType summary = SummaryDataType.Factory.newInstance();
            if (periodInfo.getTotalFundsKeyPersons() != null) {
                summary.setFederalSummary(periodInfo.getTotalFundsKeyPersons().bigDecimalValue());
            }
            if (periodInfo.getTotalNonFundsKeyPersons() != null) {
                summary.setNonFederalSummary(periodInfo.getTotalNonFundsKeyPersons().bigDecimalValue());
                if (periodInfo.getTotalFundsKeyPersons() != null) {
                    summary.setTotalFedNonFedSummary(periodInfo.getTotalFundsKeyPersons().add(
                            periodInfo.getTotalNonFundsKeyPersons()).bigDecimalValue());
                }
                else {
                    summary.setTotalFedNonFedSummary(periodInfo.getTotalNonFundsKeyPersons().bigDecimalValue());
                }
            }
            keyPersons.setTotalFundForKeyPersons(summary);
            SummaryDataType summaryAttachedKey = SummaryDataType.Factory.newInstance();

            BigDecimal totalFederalSummary = BigDecimal.ZERO;
            BigDecimal totalNonFederalSummary = BigDecimal.ZERO;
            for(KeyPersonInfo keyPersonInfo : periodInfo.getExtraKeyPersons()){
            	totalFederalSummary = totalFederalSummary.add(keyPersonInfo.getFundsRequested().bigDecimalValue());
            	totalNonFederalSummary = totalNonFederalSummary.add(keyPersonInfo.getNonFundsRequested().bigDecimalValue());
            }
            summaryAttachedKey.setFederalSummary(totalFederalSummary);
            summaryAttachedKey.setNonFederalSummary(totalNonFederalSummary);
            summaryAttachedKey.setTotalFedNonFedSummary(totalFederalSummary.add(totalNonFederalSummary));
            keyPersons.setTotalFundForAttachedKeyPersons(summaryAttachedKey);
        }
        Narrative extraKeyPersonNarr = saveExtraKeyPersons(periodInfo);
        AttachedFileDataType attachedFileDataType = null;
//        for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
//            if (narrative.getNarrativeTypeCode() != null
//                    && Integer.parseInt(narrative.getNarrativeTypeCode()) == ADDITIONAL_KEYPERSONS_ATTACHMENT) {
        if(extraKeyPersonNarr!=null){
        	attachedFileDataType = getAttachedFileType(extraKeyPersonNarr);
        	if(attachedFileDataType != null){
        		keyPersons.setAttachedKeyPersons(attachedFileDataType);
//        		break;
        	}
        }
//        }
        return keyPersons;
    }

    /**
     * This method gets KeyPersonCompensationDataType informations such as AcademicMonths,CalendarMonths,FringeBenefits
     * SummerMonths,RequestedSalary,FundsRequested and BaseSalary based on KeyPersonInfo for the keyPerson.
     * 
     * @param keyPerson (KeyPersonInfo) key person entry.
     * @return KeyPersonCompensationDataType corresponding to the KeyPersonInfo object.
     */
    private KeyPersonCompensationDataType getCompensation(KeyPersonInfo keyPerson) {

        KeyPersonCompensationDataType keyPersonCompensation = KeyPersonCompensationDataType.Factory.newInstance();
        if (keyPerson != null) {
            if (keyPerson.getAcademicMonths() != null) {
                keyPersonCompensation.setAcademicMonths(keyPerson.getAcademicMonths().bigDecimalValue());
            }
            if (keyPerson.getCalendarMonths() != null) {
                keyPersonCompensation.setCalendarMonths(keyPerson.getCalendarMonths().bigDecimalValue());
            }
            if (keyPerson.getSummerMonths() != null) {
                keyPersonCompensation.setSummerMonths(keyPerson.getSummerMonths().bigDecimalValue());
            }
            if (keyPerson.getFringe() != null) {
                keyPersonCompensation.setFringeBenefits(keyPerson.getFringe().bigDecimalValue());
            }
            if (keyPerson.getRequestedSalary() != null) {
                keyPersonCompensation.setRequestedSalary(keyPerson.getRequestedSalary().bigDecimalValue());
            }
            TotalDataType totalDataType = TotalDataType.Factory.newInstance();
            if (keyPerson.getFundsRequested() != null) {
                totalDataType.setFederal(keyPerson.getFundsRequested().bigDecimalValue());
            }
            if (keyPerson.getNonFundsRequested() != null) {
                totalDataType.setNonFederal(keyPerson.getNonFundsRequested().bigDecimalValue());
            }
            if (keyPerson.getFundsRequested() != null && keyPerson.getNonFundsRequested() != null) {
                totalDataType.setTotalFedNonFed(keyPerson.getFundsRequested().add(keyPerson.getNonFundsRequested())
                        .bigDecimalValue());
            }
            keyPersonCompensation.setTotal(totalDataType);
            if (keyPerson.getBaseSalary() != null) {
                keyPersonCompensation.setBaseSalary(keyPerson.getBaseSalary().bigDecimalValue());
            }
        }
        return keyPersonCompensation;
    }

    /**
     * This method creates {@link XmlObject} of type {@link RRFedNonFedBudgetDocument} by populating data from the given
     * {@link ProposalDevelopmentDocument}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocument}
     * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(ProposalDevelopmentDocument)
     */
    public XmlObject getFormObject(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getRRFedNonFedBudget();
    }

    /**
     * This method typecasts the given {@link XmlObject} to the required generator type and returns back the document of that
     * generator type.
     * 
     * @param xmlObject which needs to be converted to the document type of the required generator
     * @return {@link XmlObject} document of the required generator type
     * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(XmlObject)
     */
    public XmlObject getFormObject(XmlObject xmlObject) {

        RRFedNonFedBudget rrFedNonFedBudget = (RRFedNonFedBudget) xmlObject;
        RRFedNonFedBudgetDocument rrFedNonFedBudgetDocument = RRFedNonFedBudgetDocument.Factory.newInstance();
        rrFedNonFedBudgetDocument.setRRFedNonFedBudget(rrFedNonFedBudget);
        return rrFedNonFedBudgetDocument;
    }
}
