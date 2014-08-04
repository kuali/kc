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

import gov.grants.apply.forms.phs398ModularBudgetV11.PHS398ModularBudgetDocument;
import gov.grants.apply.forms.phs398ModularBudgetV11.PHS398ModularBudgetDocument.PHS398ModularBudget;
import gov.grants.apply.forms.phs398ModularBudgetV11.PHS398ModularBudgetDocument.PHS398ModularBudget.*;
import gov.grants.apply.forms.phs398ModularBudgetV11.PHS398ModularBudgetDocument.PHS398ModularBudget.CummulativeBudgetInfo.BudgetJustifications;
import gov.grants.apply.forms.phs398ModularBudgetV11.PHS398ModularBudgetDocument.PHS398ModularBudget.CummulativeBudgetInfo.BudgetJustifications.AdditionalNarrativeJustification;
import gov.grants.apply.forms.phs398ModularBudgetV11.PHS398ModularBudgetDocument.PHS398ModularBudget.CummulativeBudgetInfo.BudgetJustifications.ConsortiumJustification;
import gov.grants.apply.forms.phs398ModularBudgetV11.PHS398ModularBudgetDocument.PHS398ModularBudget.CummulativeBudgetInfo.BudgetJustifications.PersonnelJustification;
import gov.grants.apply.forms.phs398ModularBudgetV11.PHS398ModularBudgetDocument.PHS398ModularBudget.CummulativeBudgetInfo.EntirePeriodTotalCost;
import gov.grants.apply.forms.phs398ModularBudgetV11.PHS398ModularBudgetDocument.PHS398ModularBudget.Periods.DirectCost;
import gov.grants.apply.forms.phs398ModularBudgetV11.PHS398ModularBudgetDocument.PHS398ModularBudget.Periods.IndirectCost;
import gov.grants.apply.forms.phs398ModularBudgetV11.PHS398ModularBudgetDocument.PHS398ModularBudget.Periods.IndirectCost.IndirectCostItems;
import gov.grants.apply.forms.phs398ModularBudgetV11.PHS398ModularBudgetDocument.PHS398ModularBudget.Periods2.DirectCost2;
import gov.grants.apply.forms.phs398ModularBudgetV11.PHS398ModularBudgetDocument.PHS398ModularBudget.Periods2.IndirectCost2;
import gov.grants.apply.forms.phs398ModularBudgetV11.PHS398ModularBudgetDocument.PHS398ModularBudget.Periods2.IndirectCost2.IndirectCostItems2;
import gov.grants.apply.forms.phs398ModularBudgetV11.PHS398ModularBudgetDocument.PHS398ModularBudget.Periods3.DirectCost3;
import gov.grants.apply.forms.phs398ModularBudgetV11.PHS398ModularBudgetDocument.PHS398ModularBudget.Periods3.IndirectCost3;
import gov.grants.apply.forms.phs398ModularBudgetV11.PHS398ModularBudgetDocument.PHS398ModularBudget.Periods3.IndirectCost3.IndirectCostItems3;
import gov.grants.apply.forms.phs398ModularBudgetV11.PHS398ModularBudgetDocument.PHS398ModularBudget.Periods4.DirectCost4;
import gov.grants.apply.forms.phs398ModularBudgetV11.PHS398ModularBudgetDocument.PHS398ModularBudget.Periods4.IndirectCost4;
import gov.grants.apply.forms.phs398ModularBudgetV11.PHS398ModularBudgetDocument.PHS398ModularBudget.Periods4.IndirectCost4.IndirectCostItems4;
import gov.grants.apply.forms.phs398ModularBudgetV11.PHS398ModularBudgetDocument.PHS398ModularBudget.Periods5.DirectCost5;
import gov.grants.apply.forms.phs398ModularBudgetV11.PHS398ModularBudgetDocument.PHS398ModularBudget.Periods5.IndirectCost5;
import gov.grants.apply.forms.phs398ModularBudgetV11.PHS398ModularBudgetDocument.PHS398ModularBudget.Periods5.IndirectCost5.IndirectCostItems5;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.api.org.OrganizationContract;
import org.kuali.coeus.common.api.rolodex.RolodexContract;
import org.kuali.coeus.common.budget.api.period.BudgetPeriodContract;
import org.kuali.coeus.propdev.api.budget.ProposalDevelopmentBudgetExtContract;
import org.kuali.coeus.propdev.api.budget.modular.BudgetModularContract;
import org.kuali.coeus.propdev.api.budget.modular.BudgetModularIdcContract;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.s2sgen.impl.budget.BudgetPeriodNum;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.propdev.api.attachment.NarrativeContract;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for generating the XML object for grants.gov PHS398ModularBudgetV1_1.
 * Form is generated using XMLBean classes and is based on PHS398ModularBudget
 * schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@FormGenerator("PHS398ModularBudgetV1_1Generator")
public class PHS398ModularBudgetV1_1Generator extends
		PHS398ModularBudgetBaseGenerator {


    @Value("http://apply.grants.gov/forms/PHS398_ModularBudget-V1.1")
    private String namespace;

    @Value("PHS398_ModularBudget-V1.1")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/PHS398_ModularBudget-V1.1.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.phs398ModularBudgetV11")
    private String packageName;

    @Value("190")
    private int sortIndex;

	private ScaleTwoDecimal cumulativeConsortiumFandA = ScaleTwoDecimal.ZERO;
	private ScaleTwoDecimal cumulativeDirectCostLessConsortiumFandA = ScaleTwoDecimal.ZERO;
	private ScaleTwoDecimal cumulativeTotalFundsRequestedDirectCosts = ScaleTwoDecimal.ZERO;
	private ScaleTwoDecimal cumulativeTotalFundsRequestedDirectIndirectCosts = ScaleTwoDecimal.ZERO;
	private ScaleTwoDecimal cumulativeTotalFundsRequestedIndirectCost = ScaleTwoDecimal.ZERO;

	/**
	 * 
	 * This method gives modular budget information for PHS398ModularBudget form
	 * 
	 * @return modularBudgetDocument {@link XmlObject} of type
	 *         PHS398ModularBudgetDocument.
	 */
	private PHS398ModularBudgetDocument getPHS398ModularBudget() {

		PHS398ModularBudgetDocument modularBudgetDocument = PHS398ModularBudgetDocument.Factory
				.newInstance();
		PHS398ModularBudget modularBudget = PHS398ModularBudget.Factory
				.newInstance();
		modularBudget.setFormVersion(FormVersion.v1_1.getVersion());

        ProposalDevelopmentBudgetExtContract budget = s2SCommonBudgetService.getBudget(pdDoc.getDevelopmentProposal());
        if (budget != null) {
            for (BudgetPeriodContract budgetPeriod : budget.getBudgetPeriods()) {
                if (budgetPeriod.getBudgetPeriod() == BudgetPeriodNum.P1.getNum()) {
                    modularBudget.setPeriods(getPeriods(budgetPeriod));
                } else if (budgetPeriod.getBudgetPeriod() == BudgetPeriodNum.P2.getNum()) {
                    modularBudget.setPeriods2(getPeriods2(budgetPeriod));
                } else if (budgetPeriod.getBudgetPeriod() == BudgetPeriodNum.P3.getNum()) {
                    modularBudget.setPeriods3(getPeriods3(budgetPeriod));
                } else if (budgetPeriod.getBudgetPeriod() == BudgetPeriodNum.P4.getNum()) {
                    modularBudget.setPeriods4(getPeriods4(budgetPeriod));
                } else if (budgetPeriod.getBudgetPeriod() == BudgetPeriodNum.P5.getNum()) {
                    modularBudget.setPeriods5(getPeriods5(budgetPeriod));
                }
            }
            modularBudget.setCummulativeBudgetInfo(getCummBudget());
        }
		modularBudgetDocument.setPHS398ModularBudget(modularBudget);
		return modularBudgetDocument;
	}

	/**
	 * 
	 * This method is used to get Cummulative Budget information from
	 * ModularBudget form
	 * 
	 * @return CummulativeBudgetInfo containing cummulative cost details.
	 */
	private CummulativeBudgetInfo getCummBudget() {
		CummulativeBudgetInfo cummulativeBudgetInfo = CummulativeBudgetInfo.Factory
				.newInstance();
		EntirePeriodTotalCost entireCost = EntirePeriodTotalCost.Factory
				.newInstance();
		// Set default values to mandatory fields
		entireCost.setCumulativeDirectCostLessConsortiumFandA(BigDecimal.ZERO);
		entireCost.setCumulativeTotalFundsRequestedDirectCosts(BigDecimal.ZERO);
		entireCost
				.setCumulativeTotalFundsRequestedDirectIndirectCosts(BigDecimal.ZERO);

		BudgetJustifications budgetJustifications = BudgetJustifications.Factory
				.newInstance();
		if (!cumulativeTotalFundsRequestedDirectIndirectCosts.toString()
				.equals("0")) {
			entireCost
					.setCumulativeDirectCostLessConsortiumFandA(cumulativeDirectCostLessConsortiumFandA
							.bigDecimalValue());
			entireCost
					.setCumulativeTotalFundsRequestedDirectCosts(cumulativeTotalFundsRequestedDirectCosts
							.bigDecimalValue());
			entireCost.setCumulativeConsortiumFandA(cumulativeConsortiumFandA
					.bigDecimalValue());
			entireCost
					.setCumulativeTotalFundsRequestedDirectIndirectCosts(cumulativeTotalFundsRequestedDirectIndirectCosts
							.bigDecimalValue());
			entireCost
					.setCumulativeTotalFundsRequestedIndirectCost(cumulativeTotalFundsRequestedIndirectCost
							.bigDecimalValue());
			budgetJustifications = getBudgetJustifications();
			if (budgetJustifications.getAdditionalNarrativeJustification() != null
					|| budgetJustifications.getConsortiumJustification() != null
					|| budgetJustifications.getPersonnelJustification() != null) {
				cummulativeBudgetInfo
						.setBudgetJustifications(budgetJustifications);
			}
		}
		cummulativeBudgetInfo.setEntirePeriodTotalCost(entireCost);
		return cummulativeBudgetInfo;
	}

	/**
	 * 
	 * This method gives list of attachments that are used for ModularBudget
	 * form
	 * 
	 * @return BudgetJustifications object containing budget justification
	 *         attachments based on the narrative type code.
	 */
	private BudgetJustifications getBudgetJustifications() {
		BudgetJustifications budgetJustifications = BudgetJustifications.Factory
				.newInstance();
		AttachedFileDataType attachedFileDataType = null;
		for (NarrativeContract narrative : pdDoc.getDevelopmentProposal()
				.getNarratives()) {
			if (narrative.getNarrativeType().getCode() != null) {
				if (Integer.parseInt(narrative.getNarrativeType().getCode()) == PERSONNEL_JUSTIFICATION_CODE) {
		            attachedFileDataType = getAttachedFileType(narrative);
		            if(attachedFileDataType == null){
		                continue;
		            }
					PersonnelJustification personnelJustification = PersonnelJustification.Factory
							.newInstance();
					personnelJustification
							.setAttFile(attachedFileDataType);
					budgetJustifications
							.setPersonnelJustification(personnelJustification);
				}
				if (Integer.parseInt(narrative.getNarrativeType().getCode()) == CONSORTIUM_JUSTIFICATION_CODE) {
		            attachedFileDataType = getAttachedFileType(narrative);
		            if(attachedFileDataType == null){
		                continue;
		            }
					ConsortiumJustification consortiumJustification = ConsortiumJustification.Factory
							.newInstance();
					consortiumJustification
							.setAttFile(attachedFileDataType);
					budgetJustifications
							.setConsortiumJustification(consortiumJustification);
				}
				if (Integer.parseInt(narrative.getNarrativeType().getCode()) == NARRATIVE_JUSTIFICATION_CODE) {
		            attachedFileDataType = getAttachedFileType(narrative);
		            if(attachedFileDataType == null){
		                continue;
		            }
					AdditionalNarrativeJustification narrativeJustification = AdditionalNarrativeJustification.Factory
							.newInstance();
					narrativeJustification
							.setAttFile(attachedFileDataType);
					budgetJustifications
							.setAdditionalNarrativeJustification(narrativeJustification);
				}
			}
		}
		return budgetJustifications;
	}

	/**
	 * 
	 * This method is used to get 1st BudgetPeriod for Modular Budget form
	 * 
	 * @param budgetPeriod
	 *            budget period 1.
	 * @return Periods object containing modular budget details for the
	 *         corresponding budget period.
	 */
	private Periods getPeriods(BudgetPeriodContract budgetPeriod) {

		Periods periods = Periods.Factory.newInstance();
		DirectCost directCost = DirectCost.Factory.newInstance();
		IndirectCost indirectCost = IndirectCost.Factory.newInstance();

		ScaleTwoDecimal consortiumFandA = ScaleTwoDecimal.ZERO;
		ScaleTwoDecimal directCostLessConsortiumFandA = ScaleTwoDecimal.ZERO;
		ScaleTwoDecimal totalDirectCosts = ScaleTwoDecimal.ZERO;
		ScaleTwoDecimal bdTotalIndirectCost = ScaleTwoDecimal.ZERO;
		ScaleTwoDecimal bdCost = ScaleTwoDecimal.ZERO;
		ScaleTwoDecimal bdBaseCost = ScaleTwoDecimal.ZERO;
		ScaleTwoDecimal bdRate = ScaleTwoDecimal.ZERO;
		String costType = null;

		// BudgetPeriod
		periods.setBudgetPeriod(1);

		// StartDate And EndDate
		if (budgetPeriod.getStartDate() != null) {
			periods.setBudgetPeriodStartDate(s2SDateTimeService
					.convertDateToCalendar(budgetPeriod.getStartDate()));
		}
		if (budgetPeriod.getEndDate() != null) {
			periods.setBudgetPeriodEndDate(s2SDateTimeService
					.convertDateToCalendar(budgetPeriod.getEndDate()));
		}

		// Set dfault values for mandatory fields.
		directCost.setDirectCostLessConsortiumFandA(BigDecimal.ZERO);
		directCost.setTotalFundsRequestedDirectCosts(BigDecimal.ZERO);
		periods.setTotalFundsRequestedDirectIndirectCosts(BigDecimal.ZERO);

		// TotalDirectAndIndirectCost
		BudgetModularContract budgetModular = budgetPeriod.getBudgetModular();
		if (budgetModular != null) {
			ScaleTwoDecimal totalCost = getTotalCost(budgetModular);
			periods.setTotalFundsRequestedDirectIndirectCosts(totalCost
					.bigDecimalValue());
			cumulativeTotalFundsRequestedDirectIndirectCosts = cumulativeTotalFundsRequestedDirectIndirectCosts
					.add(totalCost);
			// DirectCosts
			if (budgetModular.getConsortiumFna() != null) {
				consortiumFandA = budgetModular.getConsortiumFna();
				directCost
						.setConsortiumFandA(consortiumFandA.bigDecimalValue());
				cumulativeConsortiumFandA = cumulativeConsortiumFandA
						.add(consortiumFandA);
			}
			if (budgetModular.getDirectCostLessConsortiumFna() != null) {
				directCostLessConsortiumFandA = budgetModular
						.getDirectCostLessConsortiumFna();
				directCost
						.setDirectCostLessConsortiumFandA(directCostLessConsortiumFandA
								.bigDecimalValue());
				cumulativeDirectCostLessConsortiumFandA = cumulativeDirectCostLessConsortiumFandA
						.add(directCostLessConsortiumFandA);
			}
			if (budgetModular.getTotalDirectCost() != null) {
				totalDirectCosts = budgetModular.getTotalDirectCost();
				directCost.setTotalFundsRequestedDirectCosts(totalDirectCosts
						.bigDecimalValue());
				cumulativeTotalFundsRequestedDirectCosts = cumulativeTotalFundsRequestedDirectCosts
						.add(totalDirectCosts);
			}

			List<IndirectCostItems> indirectCostItemsList = new ArrayList<IndirectCostItems>();
			for (BudgetModularIdcContract budgetModularIdc : budgetModular
					.getBudgetModularIdcs()) {
				IndirectCostItems indirectCostItems = IndirectCostItems.Factory
						.newInstance();
				if (budgetModularIdc.getFundsRequested() != null) {
					bdCost = budgetModularIdc.getFundsRequested();
					indirectCostItems.setIndirectCostFundsRequested(bdCost
							.bigDecimalValue());
					bdTotalIndirectCost = bdTotalIndirectCost.add(bdCost);
				}
				if (budgetModularIdc.getIdcBase() != null) {
					bdBaseCost = budgetModularIdc.getIdcBase();
					indirectCostItems.setIndirectCostBase(bdBaseCost
							.bigDecimalValue());
				}
				if (budgetModularIdc.getIdcRate() != null) {
					bdRate = budgetModularIdc.getIdcRate();
					indirectCostItems.setIndirectCostRate(bdRate
							.bigDecimalValue());
				}
				if (budgetModularIdc.getDescription()!= null) {
				    if (budgetModularIdc.getRateClass() != null) {

					costType = budgetModularIdc.getRateClass().getDescription();
				    }else{
		                 costType = budgetModularIdc.getDescription();
		                 }
					
					indirectCostItems.setIndirectCostTypeDescription(costType);
				}
				indirectCostItemsList.add(indirectCostItems);
			}
				IndirectCostItems[] indirectCostItemsArray = new IndirectCostItems[0];
			indirectCostItemsArray = indirectCostItemsList
					.toArray(indirectCostItemsArray);
			indirectCost.setIndirectCostItemsArray(indirectCostItemsArray);
		}
		periods.setDirectCost(directCost);

		// CognizantFederalAgency
		OrganizationContract organization = pdDoc.getDevelopmentProposal()
				.getApplicantOrganization().getOrganization();
		if (organization != null) {
			RolodexContract rolodex = rolodexService.getRolodex(organization.getCognizantAuditor());
			if (rolodex != null) {
				indirectCost
						.setCognizantFederalAgency(getCognizantFederalAgency(rolodex));
			}

			if (organization.getIndirectCostRateAgreement() != null) {
				indirectCost.setIndirectCostAgreementDate(s2SDateTimeService
						.convertDateStringToCalendar(organization
								.getIndirectCostRateAgreement()));
			}

		}
		// TotalFundsRequestedIndirectCost
		indirectCost.setTotalFundsRequestedIndirectCost(bdTotalIndirectCost
				.bigDecimalValue());
		cumulativeTotalFundsRequestedIndirectCost = cumulativeTotalFundsRequestedIndirectCost
				.add(bdTotalIndirectCost);
		periods.setIndirectCost(indirectCost);
		return periods;
	}

	/**
	 * 
	 * This method is used to get 2nd BudgetPeriod for Modular Budget form
	 * 
	 * @param budgetPeriod
	 *            budget period 2.
	 * @return Periods2 object containing modular budget details for the
	 *         corresponding budget period.
	 */
	private Periods2 getPeriods2(BudgetPeriodContract budgetPeriod) {

		Periods2 periods2 = Periods2.Factory.newInstance();
		DirectCost2 directCost2 = DirectCost2.Factory.newInstance();
		IndirectCost2 indirectCost2 = IndirectCost2.Factory.newInstance();

		ScaleTwoDecimal bdTotalIndirectCost = ScaleTwoDecimal.ZERO;

		// BudgetPeriod
		periods2.setBudgetPeriod2(2);

		// StartDate And EndDate
		if (budgetPeriod.getStartDate() != null) {
			periods2.setBudgetPeriodStartDate2(s2SDateTimeService
					.convertDateToCalendar(budgetPeriod.getStartDate()));
		}
		if (budgetPeriod.getEndDate() != null) {
			periods2.setBudgetPeriodEndDate2(s2SDateTimeService
					.convertDateToCalendar(budgetPeriod.getEndDate()));
		}
		// Set default values for mandatory fields.
		directCost2.setDirectCostLessConsortiumFandA2(BigDecimal.ZERO);
		directCost2.setTotalFundsRequestedDirectCosts2(BigDecimal.ZERO);
		periods2.setTotalFundsRequestedDirectIndirectCosts2(BigDecimal.ZERO);

		// TotalDirectAndIndirectCost
		BudgetModularContract budgetModular = budgetPeriod.getBudgetModular();

		// DirectCosts
		if (budgetModular != null) {
			ScaleTwoDecimal totalCost = getTotalCost(budgetModular);
			periods2.setTotalFundsRequestedDirectIndirectCosts2(totalCost
					.bigDecimalValue());
			cumulativeTotalFundsRequestedDirectIndirectCosts = cumulativeTotalFundsRequestedDirectIndirectCosts
					.add(totalCost);

			if (budgetModular.getConsortiumFna() != null) {
				ScaleTwoDecimal consortiumFandA = budgetModular
						.getConsortiumFna();
				directCost2.setConsortiumFandA2(consortiumFandA
						.bigDecimalValue());
				cumulativeConsortiumFandA = cumulativeConsortiumFandA
						.add(consortiumFandA);
			}
			if (budgetModular.getDirectCostLessConsortiumFna() != null) {
				ScaleTwoDecimal directCostLessConsortiumFandA = budgetModular
						.getDirectCostLessConsortiumFna();
				directCost2
						.setDirectCostLessConsortiumFandA2(directCostLessConsortiumFandA
								.bigDecimalValue());
				cumulativeDirectCostLessConsortiumFandA = cumulativeDirectCostLessConsortiumFandA
						.add(directCostLessConsortiumFandA);
			}
			if (budgetModular.getTotalDirectCost() != null) {
				ScaleTwoDecimal totalDirectCosts = budgetModular
						.getTotalDirectCost();
				directCost2.setTotalFundsRequestedDirectCosts2(totalDirectCosts
						.bigDecimalValue());
				cumulativeTotalFundsRequestedDirectCosts = cumulativeTotalFundsRequestedDirectCosts
						.add(totalDirectCosts);
			}
			// IndirectCosts

			ScaleTwoDecimal bdCost = null;
			ScaleTwoDecimal bdBaseCost = null;
			ScaleTwoDecimal bdRate = null;
			String costType = null;

			IndirectCostItems2[] indirectCostItems2Array = null;
			if (budgetModular != null
					&& budgetModular.getBudgetModularIdcs() != null) {
				indirectCostItems2Array = new IndirectCostItems2[budgetModular
						.getBudgetModularIdcs().size()];
			}

			int costItems = 0;
			for (BudgetModularIdcContract budgetModularIdc : budgetModular
					.getBudgetModularIdcs()) {
				IndirectCostItems2 indirectCostItems2 = IndirectCostItems2.Factory
						.newInstance();
				if (budgetModularIdc.getFundsRequested() != null) {
					bdCost = budgetModularIdc.getFundsRequested();
					indirectCostItems2.setIndirectCostFundsRequested(bdCost
							.bigDecimalValue());
					bdTotalIndirectCost = bdTotalIndirectCost.add(bdCost);
				}
				if (budgetModularIdc.getIdcBase() != null) {
					bdBaseCost = budgetModularIdc.getIdcBase();
					indirectCostItems2.setIndirectCostBase(bdBaseCost
							.bigDecimalValue());
				}
				if (budgetModularIdc.getIdcRate() != null) {
					bdRate = budgetModularIdc.getIdcRate();
					indirectCostItems2.setIndirectCostRate(bdRate
							.bigDecimalValue());
				}
				if (budgetModularIdc.getDescription()!= null) {
                    if (budgetModularIdc.getRateClass() != null) {

                    costType = budgetModularIdc.getRateClass().getDescription();
                    }else{
                         costType = budgetModularIdc.getDescription();
                         }
					indirectCostItems2.setIndirectCostTypeDescription(costType);
				}
				if (indirectCostItems2Array != null) {
					indirectCostItems2Array[costItems] = indirectCostItems2;
					costItems++;
				}

			}
			indirectCost2.setIndirectCostItems2Array(indirectCostItems2Array);
		}
		periods2.setDirectCost2(directCost2);

		// CognizantFederalAgency
		OrganizationContract organization = pdDoc.getDevelopmentProposal()
				.getApplicantOrganization().getOrganization();
		if (organization != null) {
            RolodexContract rolodex = rolodexService.getRolodex(organization.getCognizantAuditor());
			if (rolodex != null) {
				indirectCost2
						.setCognizantFederalAgency2(getCognizantFederalAgency(rolodex));
			}
			if (organization.getIndirectCostRateAgreement() != null) {
				indirectCost2.setIndirectCostAgreementDate2(s2SDateTimeService
						.convertDateStringToCalendar(organization
								.getIndirectCostRateAgreement()));
			}
		}

		// TotalFundsRequestedIndirectCost
		indirectCost2.setTotalFundsRequestedIndirectCost2(bdTotalIndirectCost
				.bigDecimalValue());
		cumulativeTotalFundsRequestedIndirectCost = cumulativeTotalFundsRequestedIndirectCost
				.add(bdTotalIndirectCost);
		periods2.setIndirectCost2(indirectCost2);

		return periods2;
	}

	/**
	 * 
	 * This method is used to get 3rd BudgetPeriod for Modular Budget form
	 * 
	 * @param budgetPeriod
	 *            budget period 3.
	 * @return Periods3 object containing modular budget details for the
	 *         corresponding budget period.
	 */
	private Periods3 getPeriods3(BudgetPeriodContract budgetPeriod) {

		Periods3 periods3 = Periods3.Factory.newInstance();
		DirectCost3 directCost3 = DirectCost3.Factory.newInstance();
		IndirectCost3 indirectCost3 = IndirectCost3.Factory.newInstance();

		ScaleTwoDecimal bdTotalIndirectCost = ScaleTwoDecimal.ZERO;
		// BudgetPeriod
		periods3.setBudgetPeriod3(3);

		// StartDate And EndDate
		if (budgetPeriod.getStartDate() != null) {
			periods3.setBudgetPeriodStartDate3(s2SDateTimeService
					.convertDateToCalendar(budgetPeriod.getStartDate()));
		}
		if (budgetPeriod.getEndDate() != null) {
			periods3.setBudgetPeriodEndDate3(s2SDateTimeService
					.convertDateToCalendar(budgetPeriod.getEndDate()));
		}
		// Set default values for mandatory fields.
		directCost3.setDirectCostLessConsortiumFandA3(BigDecimal.ZERO);
		directCost3.setTotalFundsRequestedDirectCosts3(BigDecimal.ZERO);
		periods3.setTotalFundsRequestedDirectIndirectCosts3(BigDecimal.ZERO);

		// TotalDirectAndIndirectCost
		BudgetModularContract budgetModular = budgetPeriod.getBudgetModular();

		// DirectCosts
		if (budgetModular != null) {
			ScaleTwoDecimal totalCost = getTotalCost(budgetModular);
			periods3.setTotalFundsRequestedDirectIndirectCosts3(totalCost
					.bigDecimalValue());
			cumulativeTotalFundsRequestedDirectIndirectCosts = cumulativeTotalFundsRequestedDirectIndirectCosts
					.add(totalCost);

			if (budgetModular.getConsortiumFna() != null) {
				ScaleTwoDecimal consortiumFandA = budgetModular
						.getConsortiumFna();
				directCost3.setConsortiumFandA3(consortiumFandA
						.bigDecimalValue());
				cumulativeConsortiumFandA = cumulativeConsortiumFandA
						.add(consortiumFandA);
			}
			if (budgetModular.getDirectCostLessConsortiumFna() != null) {
				ScaleTwoDecimal directCostLessConsortiumFandA = budgetModular
						.getDirectCostLessConsortiumFna();
				directCost3
						.setDirectCostLessConsortiumFandA3(directCostLessConsortiumFandA
								.bigDecimalValue());
				cumulativeDirectCostLessConsortiumFandA = cumulativeDirectCostLessConsortiumFandA
						.add(directCostLessConsortiumFandA);
			}
			if (budgetModular.getTotalDirectCost() != null) {
				ScaleTwoDecimal totalDirectCosts = budgetModular
						.getTotalDirectCost();
				directCost3.setTotalFundsRequestedDirectCosts3(totalDirectCosts
						.bigDecimalValue());
				cumulativeTotalFundsRequestedDirectCosts = cumulativeTotalFundsRequestedDirectCosts
						.add(totalDirectCosts);
			}
			IndirectCostItems3[] indirectCostItems3Array = null;
			if (budgetModular.getBudgetModularIdcs() != null) {
				indirectCostItems3Array = new IndirectCostItems3[budgetModular
						.getBudgetModularIdcs().size()];
			}

			// IndirectCosts
			ScaleTwoDecimal bdCost = null;
			ScaleTwoDecimal bdBaseCost = null;
			ScaleTwoDecimal bdRate = null;
			String costType = null;

			int costItems = 0;
			for (BudgetModularIdcContract budgetModularIdc : budgetModular
					.getBudgetModularIdcs()) {
				IndirectCostItems3 indirectCostItems3 = IndirectCostItems3.Factory
						.newInstance();
				if (budgetModularIdc.getFundsRequested() != null) {
					bdCost = budgetModularIdc.getFundsRequested();
					indirectCostItems3.setIndirectCostFundsRequested(bdCost
							.bigDecimalValue());
					bdTotalIndirectCost = bdTotalIndirectCost.add(bdCost);
				}
				if (budgetModularIdc.getIdcBase() != null) {
					bdBaseCost = budgetModularIdc.getIdcBase();
					indirectCostItems3.setIndirectCostBase(bdBaseCost
							.bigDecimalValue());
				}
				if (budgetModularIdc.getIdcRate() != null) {
					bdRate = budgetModularIdc.getIdcRate();
					indirectCostItems3.setIndirectCostRate(bdRate
							.bigDecimalValue());
				}
				if (budgetModularIdc.getDescription()!= null) {
                    if (budgetModularIdc.getRateClass() != null) {

                    costType = budgetModularIdc.getRateClass().getDescription();
                    }else{
                         costType = budgetModularIdc.getDescription();
                         }
					indirectCostItems3.setIndirectCostTypeDescription(costType);
				}
				if (indirectCostItems3Array != null) {
					indirectCostItems3Array[costItems] = indirectCostItems3;
					costItems++;
				}
			}
			indirectCost3.setIndirectCostItems3Array(indirectCostItems3Array);
		}
		periods3.setDirectCost3(directCost3);

		// CognizantFederalAgency
		OrganizationContract organization = pdDoc.getDevelopmentProposal()
				.getApplicantOrganization().getOrganization();
		if (organization != null) {
            RolodexContract rolodex = rolodexService.getRolodex(organization.getCognizantAuditor());
			if (rolodex != null) {
				indirectCost3
						.setCognizantFederalAgency3(getCognizantFederalAgency(rolodex));
			}

			if (organization.getIndirectCostRateAgreement() != null) {
				indirectCost3.setIndirectCostAgreementDate3(s2SDateTimeService
						.convertDateStringToCalendar(organization
								.getIndirectCostRateAgreement()));
			}
		}
		// TotalFundsRequestedIndirectCost
		indirectCost3.setTotalFundsRequestedIndirectCost3(bdTotalIndirectCost
				.bigDecimalValue());
		cumulativeTotalFundsRequestedIndirectCost = cumulativeTotalFundsRequestedIndirectCost
				.add(bdTotalIndirectCost);
		periods3.setIndirectCost3(indirectCost3);
		return periods3;
	}

	/**
	 * 
	 * This method is used to get 4th BudgetPeriod for Modular Budget form
	 * 
	 * @param budgetPeriod
	 *            budget period 4.
	 * @return Periods4 object containing modular budget details for the
	 *         corresponding budget period.
	 */
	private Periods4 getPeriods4(BudgetPeriodContract budgetPeriod) {

		Periods4 periods4 = Periods4.Factory.newInstance();
		DirectCost4 directCost4 = DirectCost4.Factory.newInstance();
		IndirectCost4 indirectCost4 = IndirectCost4.Factory.newInstance();

		ScaleTwoDecimal bdTotalIndirectCost = ScaleTwoDecimal.ZERO;
		// BudgetPeriod
		periods4.setBudgetPeriod4(4);

		// StartDate And EndDate
		if (budgetPeriod.getStartDate() != null) {
			periods4.setBudgetPeriodStartDate4(s2SDateTimeService
					.convertDateToCalendar(budgetPeriod.getStartDate()));
		}
		if (budgetPeriod.getEndDate() != null) {
			periods4.setBudgetPeriodEndDate4(s2SDateTimeService
					.convertDateToCalendar(budgetPeriod.getEndDate()));
		}
		// Set dfault values for mandatory fields.
		directCost4.setDirectCostLessConsortiumFandA4(BigDecimal.ZERO);
		directCost4.setTotalFundsRequestedDirectCosts4(BigDecimal.ZERO);
		periods4.setTotalFundsRequestedDirectIndirectCosts4(BigDecimal.ZERO);

		BudgetModularContract budgetModular = budgetPeriod.getBudgetModular();

		// DirectCosts
		if (budgetModular != null) {
			ScaleTwoDecimal totalCost = getTotalCost(budgetModular);
			periods4.setTotalFundsRequestedDirectIndirectCosts4(totalCost
					.bigDecimalValue());
			cumulativeTotalFundsRequestedDirectIndirectCosts = cumulativeTotalFundsRequestedDirectIndirectCosts
					.add(totalCost);
			if (budgetModular.getConsortiumFna() != null) {
				ScaleTwoDecimal consortiumFandA = budgetModular
						.getConsortiumFna();
				directCost4.setConsortiumFandA4(consortiumFandA
						.bigDecimalValue());
				cumulativeConsortiumFandA = cumulativeConsortiumFandA
						.add(consortiumFandA);
			}
			if (budgetModular.getDirectCostLessConsortiumFna() != null) {
				ScaleTwoDecimal directCostLessConsortiumFandA = budgetModular
						.getDirectCostLessConsortiumFna();
				directCost4
						.setDirectCostLessConsortiumFandA4(directCostLessConsortiumFandA
								.bigDecimalValue());
				cumulativeDirectCostLessConsortiumFandA = cumulativeDirectCostLessConsortiumFandA
						.add(directCostLessConsortiumFandA);
			}
			if (budgetModular.getTotalDirectCost() != null) {
				ScaleTwoDecimal totalDirectCosts = budgetModular
						.getTotalDirectCost();
				directCost4.setTotalFundsRequestedDirectCosts4(totalDirectCosts
						.bigDecimalValue());
				cumulativeTotalFundsRequestedDirectCosts = cumulativeTotalFundsRequestedDirectCosts
						.add(totalDirectCosts);
			}
			// IndirectCosts
			ScaleTwoDecimal bdCost = null;
			ScaleTwoDecimal bdBaseCost = null;
			ScaleTwoDecimal bdRate = null;
			String costType = null;

			IndirectCostItems4[] indirectCostItems4Array = null;
			if (budgetModular.getBudgetModularIdcs() != null) {
				indirectCostItems4Array = new IndirectCostItems4[budgetModular
						.getBudgetModularIdcs().size()];
			}
			int costItems = 0;
			for (BudgetModularIdcContract budgetModularIdc : budgetModular
					.getBudgetModularIdcs()) {
				IndirectCostItems4 indirectCostItems4 = IndirectCostItems4.Factory
						.newInstance();
				if (budgetModularIdc.getFundsRequested() != null) {
					bdCost = budgetModularIdc.getFundsRequested();
					indirectCostItems4.setIndirectCostFundsRequested(bdCost
							.bigDecimalValue());
					bdTotalIndirectCost = bdTotalIndirectCost.add(bdCost);
				}
				if (budgetModularIdc.getIdcBase() != null) {
					bdBaseCost = budgetModularIdc.getIdcBase();
					indirectCostItems4.setIndirectCostBase(bdBaseCost
							.bigDecimalValue());
				}
				if (budgetModularIdc.getIdcRate() != null) {
					bdRate = budgetModularIdc.getIdcRate();
					indirectCostItems4.setIndirectCostRate(bdRate
							.bigDecimalValue());
				}
				if (budgetModularIdc.getDescription() != null) {
				    if (budgetModularIdc.getRateClass() != null) {
				        costType = budgetModularIdc.getRateClass().getDescription();
				    }else{
					costType = budgetModularIdc.getDescription();
				    }
					indirectCostItems4.setIndirectCostTypeDescription(costType);
				}
				if (indirectCostItems4Array != null) {
					indirectCostItems4Array[costItems] = indirectCostItems4;
					costItems++;
				}

			}
			indirectCost4.setIndirectCostItems4Array(indirectCostItems4Array);

		}
		periods4.setDirectCost4(directCost4);

		// CognizantFederalAgency
		OrganizationContract organization = pdDoc.getDevelopmentProposal()
				.getApplicantOrganization().getOrganization();
		if (organization != null) {
            RolodexContract rolodex = rolodexService.getRolodex(organization.getCognizantAuditor());
			if (rolodex != null) {
				indirectCost4
						.setCognizantFederalAgency4(getCognizantFederalAgency(rolodex));
			}
			if (organization.getIndirectCostRateAgreement() != null) {
				indirectCost4.setIndirectCostAgreementDate4(s2SDateTimeService
						.convertDateStringToCalendar(organization
								.getIndirectCostRateAgreement()));
			}
		}
		// TotalFundsRequestedIndirectCost
		indirectCost4.setTotalFundsRequestedIndirectCost4(bdTotalIndirectCost
				.bigDecimalValue());
		cumulativeTotalFundsRequestedIndirectCost = cumulativeTotalFundsRequestedIndirectCost
				.add(bdTotalIndirectCost);
		periods4.setIndirectCost4(indirectCost4);
		return periods4;
	}

	/**
	 * 
	 * This method is used to get 5th BudgetPeriod for Modular Budget form
	 * 
	 * @param budgetPeriod
	 *            budget period 5.
	 * @return Periods5 object containing modular budget details for the
	 *         corresponding budget period.
	 */
	private Periods5 getPeriods5(BudgetPeriodContract budgetPeriod) {

		Periods5 periods5 = Periods5.Factory.newInstance();
		DirectCost5 directCost5 = DirectCost5.Factory.newInstance();
		IndirectCost5 indirectCost5 = IndirectCost5.Factory.newInstance();

		ScaleTwoDecimal bdTotalIndirectCost = ScaleTwoDecimal.ZERO;
		// BudgetPeriod
		periods5.setBudgetPeriod5(5);

		// StartDate And EndDate
		if (budgetPeriod.getStartDate() != null) {
			periods5.setBudgetPeriodStartDate5(s2SDateTimeService
					.convertDateToCalendar(budgetPeriod.getStartDate()));
		}
		if (budgetPeriod.getEndDate() != null) {
			periods5.setBudgetPeriodEndDate5(s2SDateTimeService
					.convertDateToCalendar(budgetPeriod.getEndDate()));
		}
		// Set dfault values for mandatory fields.
		directCost5.setDirectCostLessConsortiumFandA5(BigDecimal.ZERO);
		directCost5.setTotalFundsRequestedDirectCosts5(BigDecimal.ZERO);
		periods5.setTotalFundsRequestedDirectIndirectCosts5(BigDecimal.ZERO);

		// TotalDirectAndIndirectCost
		BudgetModularContract budgetModular = budgetPeriod.getBudgetModular();

		// DirectCosts
		if (budgetModular != null) {
			ScaleTwoDecimal totalCost = getTotalCost(budgetModular);
			periods5.setTotalFundsRequestedDirectIndirectCosts5(totalCost
					.bigDecimalValue());
			cumulativeTotalFundsRequestedDirectIndirectCosts = cumulativeTotalFundsRequestedDirectIndirectCosts
					.add(totalCost);

			if (budgetModular.getConsortiumFna() != null) {
				ScaleTwoDecimal consortiumFandA = budgetModular
						.getConsortiumFna();
				directCost5.setConsortiumFandA5(consortiumFandA
						.bigDecimalValue());
				cumulativeConsortiumFandA = cumulativeConsortiumFandA
						.add(consortiumFandA);
			}
			if (budgetModular.getDirectCostLessConsortiumFna() != null) {
				ScaleTwoDecimal directCostLessConsortiumFandA = budgetModular
						.getDirectCostLessConsortiumFna();
				directCost5
						.setDirectCostLessConsortiumFandA5(directCostLessConsortiumFandA
								.bigDecimalValue());
				cumulativeDirectCostLessConsortiumFandA = cumulativeDirectCostLessConsortiumFandA
						.add(directCostLessConsortiumFandA);
			}
			if (budgetModular.getTotalDirectCost() != null) {
				ScaleTwoDecimal totalDirectCosts = budgetModular
						.getTotalDirectCost();
				directCost5.setTotalFundsRequestedDirectCosts5(totalDirectCosts
						.bigDecimalValue());
				cumulativeTotalFundsRequestedDirectCosts = cumulativeTotalFundsRequestedDirectCosts
						.add(totalDirectCosts);
			}
			// IndirectCosts
			ScaleTwoDecimal bdCost = null;
			ScaleTwoDecimal bdBaseCost = null;
			ScaleTwoDecimal bdRate = null;
			String costType = null;

			IndirectCostItems5[] indirectCostItems5Array = null;
			if (budgetModular.getBudgetModularIdcs() != null) {
				indirectCostItems5Array = new IndirectCostItems5[budgetModular
						.getBudgetModularIdcs().size()];
			}
			int costItems = 0;
			for (BudgetModularIdcContract budgetModularIdc : budgetModular
					.getBudgetModularIdcs()) {
				IndirectCostItems5 indirectCostItems5 = IndirectCostItems5.Factory
						.newInstance();
				if (budgetModularIdc.getFundsRequested() != null) {
					bdCost = budgetModularIdc.getFundsRequested();
					indirectCostItems5.setIndirectCostFundsRequested(bdCost
							.bigDecimalValue());
					bdTotalIndirectCost = bdTotalIndirectCost.add(bdCost);
				}
				if (budgetModularIdc.getIdcBase() != null) {
					bdBaseCost = budgetModularIdc.getIdcBase();
					indirectCostItems5.setIndirectCostBase(bdBaseCost
							.bigDecimalValue());
				}
				if (budgetModularIdc.getIdcRate() != null) {
					bdRate = budgetModularIdc.getIdcRate();
					indirectCostItems5.setIndirectCostRate(bdRate
							.bigDecimalValue());
				}
				if (budgetModularIdc.getDescription()!= null) {
                    if (budgetModularIdc.getRateClass() != null) {

                    costType = budgetModularIdc.getRateClass().getDescription();
                    }else{
                         costType = budgetModularIdc.getDescription();
                         }
					indirectCostItems5.setIndirectCostTypeDescription(costType);
				}
				if (indirectCostItems5Array != null) {
					indirectCostItems5Array[costItems] = indirectCostItems5;
					costItems++;
				}

			}
			indirectCost5.setIndirectCostItems5Array(indirectCostItems5Array);
		}
		periods5.setDirectCost5(directCost5);

		// CognizantFederalAgency
		OrganizationContract organization = pdDoc.getDevelopmentProposal()
				.getApplicantOrganization().getOrganization();
		if (organization != null) {
            RolodexContract rolodex = rolodexService.getRolodex(organization.getCognizantAuditor());
			if (rolodex != null) {
				indirectCost5
						.setCognizantFederalAgency5(getCognizantFederalAgency(rolodex));
			}
			if (organization.getIndirectCostRateAgreement() != null) {
				indirectCost5.setIndirectCostAgreementDate5(s2SDateTimeService
						.convertDateStringToCalendar(organization
								.getIndirectCostRateAgreement()));
			}
		}
		// TotalFundsRequestedIndirectCost
		indirectCost5.setTotalFundsRequestedIndirectCost5(bdTotalIndirectCost
				.bigDecimalValue());
		cumulativeTotalFundsRequestedIndirectCost = cumulativeTotalFundsRequestedIndirectCost
				.add(bdTotalIndirectCost);
		periods5.setIndirectCost5(indirectCost5);
		return periods5;
	}

	/**
	 * This method creates {@link XmlObject} of type
	 * {@link PHS398ModularBudgetDocument} by populating data from the given
	 * {@link ProposalDevelopmentDocumentContract}
	 * 
	 * @param proposalDevelopmentDocument
	 *            for which the {@link XmlObject} needs to be created
	 * @return {@link XmlObject} which is generated using the given
	 *         {@link ProposalDevelopmentDocumentContract}
	 */
	public XmlObject getFormObject(
			ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
		this.pdDoc = proposalDevelopmentDocument;
		return getPHS398ModularBudget();
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
