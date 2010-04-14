/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.printing.xmlstream;

import gov.nih.era.projectmgmt.sbir.cgap.commonNamespace.AssuranceType;
import gov.nih.era.projectmgmt.sbir.cgap.commonNamespace.ContactInfoType;
import gov.nih.era.projectmgmt.sbir.cgap.commonNamespace.PostalAddressType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ApplicantOrganizationType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.AuthorizedOrganizationalRepresentativeType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.BudgetPeriodType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.BudgetSummaryType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.BudgetTotalsType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.CoreApplicantSubmissionQualifiersType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.CoreApplicationCategoryType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.CoreBudgetTotalsType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.CoreFederalAgencyReceiptQualifiersType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.CoreFederalDebtDelinquencyQuestionsType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.CoreProjectDatesType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.CoreStateIntergovernmentalReviewType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.CoreStateReceiptQualifiersType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.CoreSubmissionCategoryType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.DescriptionBlockType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.FundingOpportunityDetailsType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.HumanSubjectsType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.KeyPersonType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.OrgAssurancesType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.OtherAgencyQuestionsType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.OtherDirectType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ParticipantType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.PersonFullNameType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ProjectRoleType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ProjectSiteType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ResearchAndRelatedProjectDocument;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.SalariesAndWagesType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.TravelType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.AnimalSubjectDocument.AnimalSubject;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ApplicantOrganizationType.OrganizationContactPerson;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.EquipmentCostsDocument.EquipmentCosts;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.KeyPersonType.KeyPersonFlag;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.OtherDirectCostsDocument.OtherDirectCosts;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ParticipantPatientCostsDocument.ParticipantPatientCosts;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ProgramDirectorPrincipalInvestigatorDocument.ProgramDirectorPrincipalInvestigator;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ProjectDescriptionDocument.ProjectDescription;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ProjectSurveyDocument.ProjectSurvey;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ResearchAndRelatedProjectDocument.ResearchAndRelatedProject;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ResearchCoverPageDocument.ResearchCoverPage;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.TravelCostsDocument.TravelCosts;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.OrganizationType;
import org.kuali.kra.bo.OrganizationYnq;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.calculator.RateClassType;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.nonpersonnel.AbstractBudgetRateAndBase;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.personnel.BudgetPerson;
import org.kuali.kra.budget.personnel.BudgetPersonnelDetails;
import org.kuali.kra.budget.personnel.BudgetPersonnelRateAndBase;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.printing.util.PrintingUtils;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.proposaldevelopment.bo.ProposalSite;
import org.kuali.kra.proposaldevelopment.bo.ProposalSpecialReview;
import org.kuali.kra.proposaldevelopment.bo.ProposalYnq;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

/**
 * This class generates XML that confirms with the RaR XSD related to Proposal
 * Submission Report or Sponsor Report. The data for XML is derived from
 * {@link ResearchDocumentBase} and {@link Map} of details passed to the class.
 * 
 * 
 */
public class ResearchAndRelatedXmlStream extends ProposalBaseStream {

	private static final Logger LOG = Logger
			.getLogger(ResearchAndRelatedXmlStream.class);
	private static final String PROPOSALQUESTION_ID15 = "15";
	private static final String ORGANIZATION_QUESTION_ID_H5 = "H5";
	private static final String ORGANIZATION_QUESTION_ID_I8 = "I8";
	private static final String ORGANIZATION_ID_PARAMETER = "organizationId";
	private static final String DEFAULT_VALUE_FOR_SSN = "XXXXXXXXX";
	private static final String DEFAULT_VALUE_FOR_KEY_PERSON_FLAG_DESCRIPTION = "PI";
	private static final String DEFAULT_VALUE_KEY_PERSON_FLAG_CODE = "true";
	private static final String DEFAULT_VALUE_UNKNOWN = "Unknown";
	private static final String KEY_PERSON_FLAG_DESCRIPTION_VALUE_COLLABORATOR = "Collaborator/Other";
	private static final String KEY_PERSON_FLAG_DESCRIPTION_VALUE_KEY_PERSON = "Key Person";
	private static final String KEY_PERSON_FLAG_CODE_VALUE_FALSE = "false";
	private static final String GENERAL_CERTIFICATION_QUESTION_ID = "H6";
	private static final String LOBBYING_QUESTION_ID = "H0";
	private static final String REFERENCES_BLOCK_TYPE = "references";
	private static final String EQUIPMENT_BLOCK_TYPE = "equipment";
	private static final String FACILITIES_BLOCK_TYPE = "facilities";
	private static final String PROJECT_SUMMARY_BLOCK_TYPE = "summary";
	private static final String ANSWER_PARAMETER = "answer";
	private static final String QUESTION_ID_PARAMETER = "questionId";
	private static final String DEFAULT_ANSWER = "This question has not been answered";
	private static final String EMPTY_STRING = " ";
	private static final String ANSWER_INDICATOR_VALUE = "Y";
	private static final String IACU_APPROVAL_PENDING_VALUE = "Pending";
	private static final String SPECIAL_REVIEW_CODE_1 = "1";
	private static final String SPECIAL_REVIEW_CODE_2 = "2";
	private static final String APPROVAL_TYPE_EXEMPT = "4";
	private static final String STEMCELL = "18";
	private static final String INTERNATIONAL_ACTIVITIES = "H1";
	private static final String PROPRIETARY_INFO = "G8";
	private static final String HISTORICAL_SITES = "G6";
	private static final String GENETICALLY_ENGINEERED = "G4";
	private static final String HAZARDOUS_MATERIALS = "G3";
	private static final String NSFSMALL_GRANT = "14";
	private static final String NSF_BEGINNING_INV = "12";
	private static final String LOBBYING_ACTIVITIES = "H4";
	private static final String BUDGET_JUSTIFICATION_IDENTIFIER = "budgetJust";
	private static final String CATEGORY_CODE_PARTICIPANT_TRAVEL = "31";
	private static final String CATEGORY_CODE_PARTICIPANT_SUBSISTANCE = "36";
	private static final String CATEGORY_CODE_PARTICIPANT_STIPENDS = "32";
	private static final String CATEGORY_CODE_PARTICIPANT_OTHER = "2";
	private static final String CATEGORY_CODE_OUTPATIENT = "33";
	private static final String CATEGORY_CODE_INPATIENT = "9";
	private static final String CATEGORY_CODE_TRAVEL_FOREIGN = "23";
	private static final String CATEGORY_CODE_TRAVEL_DOMESTIC = "7";
	private static final String CATEGORY_CODE_EQUIPMENT = "20";
	private static final String CATEGORY_CODE_EQUIPMENT_RENTAL = "13";
	private static final String REPORT_NAME = "Research and Related";
	private static final String REVIEW_AVAILABILITY_DATE = "04/14/2004";
	private static final String STATE_RECEIPT_DATE = "10/17/2005";
	private static final String AGENCY_RECEIPT_DATE = "12/31/2005";
	private static final String APPLICATION_DATE = "01/02/2004";

	/**
	 * This method generates XML for Proposal Submission Report or Sponsor
	 * Report. It uses data passed in {@link ResearchDocumentBase} for
	 * populating the XML nodes. The XMl once generated is returned as
	 * {@link XmlObject}
	 * 
	 * @param document
	 *            using which XML is generated
	 * @param reportParameters
	 *            parameters related to XML generation
	 * @return {@link XmlObject} representing the XML
	 */
	public Map<String, XmlObject> generateXmlStream(
			ResearchDocumentBase document, Map<String, Object> reportParameters) {
		ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument) document;
		Budget budget = getBudget(proposalDevelopmentDocument);
		ResearchAndRelatedProjectDocument researchAndRelatedProjectDocument = ResearchAndRelatedProjectDocument.Factory
				.newInstance();
		researchAndRelatedProjectDocument
				.setResearchAndRelatedProject(getResearchAndRelatedProject(
						proposalDevelopmentDocument, budget));

		Map<String, XmlObject> xmlObjectList = new LinkedHashMap<String, XmlObject>();
		xmlObjectList.put(REPORT_NAME, researchAndRelatedProjectDocument);
		return xmlObjectList;
	}

	/*
	 * This method will set the values to root element of xml object
	 * ResearchAndRelatedProject attributes like project description ,
	 * Organization assurances , budget summary, Research Cover page, and
	 * keyPerson.
	 */
	private ResearchAndRelatedProject getResearchAndRelatedProject(
			ProposalDevelopmentDocument proposalDevelopmentDocument,
			Budget budget) {
		ResearchAndRelatedProject researchAndRelatedProject = ResearchAndRelatedProject.Factory
				.newInstance();
		DevelopmentProposal developmentProposal = proposalDevelopmentDocument
				.getDevelopmentProposal();
		researchAndRelatedProject
				.setProjectDescription(getProjectDescription(developmentProposal));
		researchAndRelatedProject
				.setOrgAssurances(getOrgAssurances(developmentProposal));
		researchAndRelatedProject
				.setKeyPerson(getKeyPersonType(developmentProposal));

		try {
			researchAndRelatedProject
					.setResearchCoverPage(getResearchCoverPage(
							developmentProposal, budget));
		} catch (ParseException e) {
			LOG.error("Unable to parse String date", e);
		}

		researchAndRelatedProject.setBudgetSummary(getBudgetSummary(budget,
				developmentProposal.getProposalNumber()));
		return researchAndRelatedProject;
	}

	/*
	 * This method gets BudgetSummaryType XMLObject and setting data to
	 * BudgetSummaryType from budget and budgetPeriod which having final
	 * versionFlag enable.
	 */
	private BudgetSummaryType getBudgetSummary(Budget budget,
			String proposalNumber) {
		BudgetSummaryType budgetSummaryType = BudgetSummaryType.Factory
				.newInstance();
		if (budget != null) {
			BudgetPeriod budgetPeriod = budget.getBudgetPeriod(1);
			budgetSummaryType.setInitialBudgetTotals(getBudgetTotals(
					budgetPeriod.getTotalCost(), budgetPeriod
							.getCostSharingAmount()));
			budgetSummaryType.setAllBudgetTotals(getBudgetTotals(budget
					.getTotalCost(), budget.getCostSharingAmount()));
			budgetSummaryType.setBudgetPeriodArray(getBudgetPeriodArray(budget
					.getBudgetPeriods()));
			budgetSummaryType
					.setBudgetJustification(getBudgetJustification(proposalNumber));
			// budgetSummaryType.setBudgetDirectCostsTotal(budget
			// .getTotalDirectCost().bigDecimalValue());
			// budgetSummaryType.setBudgetIndirectCostsTotal(budget
			// .getTotalIndirectCost().bigDecimalValue());
			// budgetSummaryType.setBudgetCostsTotal(budget.getTotalCost()
			// .bigDecimalValue());
		}
		return budgetSummaryType;
	}

	/*
	 * This method gets DescriptionBlockType XMLObject. In the
	 * DescriptionBlockType object only FileIdentifier fields sets by
	 * concatenating proposal number and budget Just
	 */
	private DescriptionBlockType getBudgetJustification(String proposalNumber) {
		DescriptionBlockType descBlockType = DescriptionBlockType.Factory
				.newInstance();
		descBlockType.setFileIdentifier(new StringBuilder(proposalNumber)
				.append(BUDGET_JUSTIFICATION_IDENTIFIER).toString());
		return descBlockType;
	}

	/*
	 * This method gets arrays of BudgetPeriodType XMLObjects by setting each
	 * BudgetPeriodType data from budgetPeriod data
	 */
	private BudgetPeriodType[] getBudgetPeriodArray(
			List<BudgetPeriod> budgetPeriodList) {
		List<BudgetPeriodType> budgetPeriodTypeList = new ArrayList<BudgetPeriodType>();
		for (BudgetPeriod budgetPeriod : budgetPeriodList) {
			if (budgetPeriod.getBudgetPeriod() != null) {
				List<BudgetLineItem> budgetLineItems = budgetPeriod
						.getBudgetLineItems();
				BudgetPeriodType budgetPeriodType = BudgetPeriodType.Factory
						.newInstance();
				budgetPeriodType.setBudgetPeriodID(new BigInteger(String
						.valueOf(budgetPeriod.getBudgetPeriod())));
				budgetPeriodType.setStartDate(dateTimeService
						.getCalendar(budgetPeriod.getStartDate()));
				budgetPeriodType.setEndDate(dateTimeService
						.getCalendar(budgetPeriod.getEndDate()));
				budgetPeriodType.setFee(new BigDecimal(0));
				budgetPeriodType
						.setSalariesWagesTotal(getSalaryWagesTotal(budgetLineItems));
				budgetPeriodType
						.setSalariesAndWagesArray(getSalaryAndWages(budgetLineItems));
				budgetPeriodType
						.setEquipmentTotal(getEquipmentTotal(budgetLineItems));
				budgetPeriodType
						.setEquipmentCostsArray(getEquipmentCosts(budgetLineItems));
				budgetPeriodType
						.setOtherDirectCostsArray(getOtherDirectCosts(budgetLineItems));
				budgetPeriodType
						.setOtherDirectTotal(getOtherDirectTotal(budgetLineItems));
				budgetPeriodType
						.setTravelCostsArray(getTravelCosts(budgetLineItems));
				budgetPeriodType
						.setTravelTotal(getTravelTotal(budgetLineItems));
				budgetPeriodType
						.setParticipantPatientCostsArray(getParticipantPatientCost(budgetLineItems));
				budgetPeriodType
						.setParticipantPatientTotal(getParticipantPatientTotal(budgetLineItems));
				budgetPeriodType.setPeriodDirectCostsTotal(budgetPeriod
						.getTotalDirectCost().bigDecimalValue());
				budgetPeriodType.setIndirectCostsTotal(budgetPeriod
						.getTotalIndirectCost().bigDecimalValue());
				budgetPeriodType.setPeriodCostsTotal(budgetPeriod
						.getTotalCost().bigDecimalValue());
				budgetPeriodType.setProgramIncome(new BigDecimal(0));
				budgetPeriodTypeList.add(budgetPeriodType);
			}
		}
		return budgetPeriodTypeList.toArray(new BudgetPeriodType[0]);
	}

	/*
	 * This method gets SalariesAndWages from List of BudgetPersonnelRateAndBase
	 * List for a BudgetLineItem by checking the rateClassType as Vacation and
	 * Employee Benefit
	 */
	private BigDecimal getSalaryWagesTotal(List<BudgetLineItem> budgetLineItems) {
		BudgetDecimal salaryAndWagesTotal = BudgetDecimal.ZERO;
		for (BudgetLineItem budgetLineItem : budgetLineItems) {
			for (BudgetPersonnelDetails budgetPersDetails : budgetLineItem
					.getBudgetPersonnelDetailsList()) {
				salaryAndWagesTotal = salaryAndWagesTotal
						.add(getSalaryWagesTotalForLineItem(budgetPersDetails));
			}
		}
		return null;
	}

	/*
	 * This method gets SalaryAndWages amount from List of
	 * BudgetPersonnelRateAndBase as sum of salaryRequested and CalculatedCost
	 * by checking the rateClassType for vacation and employee benefit
	 */
	private BudgetDecimal getSalaryWagesTotalForLineItem(
			BudgetPersonnelDetails budgetPersDetails) {
		BudgetDecimal salaryAndWages = BudgetDecimal.ZERO;
		salaryAndWages = salaryAndWages.add(budgetPersDetails
				.getSalaryRequested());
		salaryAndWages = salaryAndWages.add(getFringeCost(budgetPersDetails));
		return salaryAndWages;
	}

	/*
	 * This method gets the fringe amount from List of
	 * BudgetPersonnelRateAndBase
	 */
	private BudgetDecimal getFringeCost(BudgetPersonnelDetails budgetPersDetails) {
		BudgetDecimal fringe = BudgetDecimal.ZERO;
		for (BudgetPersonnelRateAndBase budgetPersRateBase : budgetPersDetails
				.getBudgetPersonnelRateAndBaseList()) {
			if (isRateAndBaseOfRateClassTypeEB(budgetPersRateBase)
					|| isRateAndBaseOfRateClassTypeVacation(budgetPersRateBase)
					|| isRateAndBaseOfRateClassTypeOverhead(budgetPersRateBase)) {
				fringe = fringe.add(budgetPersRateBase.getCalculatedCost());
			}
		}
		return fringe;
	}

	/*
	 * This method gets true if rateClassType is O else false from RateAndBase
	 */
	protected boolean isRateAndBaseOfRateClassTypeOverhead(
			AbstractBudgetRateAndBase rateAndBase) {
		rateAndBase.refreshNonUpdateableReferences();
		if (rateAndBase.getRateClass() != null
				&& RateClassType.OVERHEAD.getRateClassType().equals(
						rateAndBase.getRateClass().getRateClassType())) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * This method gets true if rateClassType is E else false from RateAndBase
	 */
	private boolean isRateAndBaseOfRateClassTypeEB(
			AbstractBudgetRateAndBase rateAndBase) {
		rateAndBase.refreshNonUpdateableReferences();
		if (rateAndBase.getRateClass() != null
				&& RateClassType.EMPLOYEE_BENEFITS.getRateClassType().equals(
						rateAndBase.getRateClass().getRateClassType())) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * This method gets true if rateClassType is V else false from RateAndBase
	 */
	private boolean isRateAndBaseOfRateClassTypeVacation(
			AbstractBudgetRateAndBase rateAndBase) {
		rateAndBase.refreshNonUpdateableReferences();
		if(rateAndBase.getRateClass()!=null && RateClassType.VACATION.getRateClassType().equals(
				rateAndBase.getRateClass().getRateClassType())){
			return true;
		}else{
			return false;
		}
	}

	/*
	 * This method gets sum of lineItemCost as otherDirect total cost from List
	 * of BudgetLineItem, if budgetCategoryCode is paticipantPatient for
	 * budgetLineItem
	 */
	private BigDecimal getParticipantPatientTotal(
			List<BudgetLineItem> budgetLineItems) {
		BudgetDecimal cost = BudgetDecimal.ZERO;
		for (BudgetLineItem budgetLineItem : budgetLineItems) {
			if (isBudgetCategoryParticipantPatient(budgetLineItem)) {
				cost = cost.add(budgetLineItem.getLineItemCost());
			}
		}
		return cost.bigDecimalValue();
	}

	/*
	 * This method gets sum of lineItemCost as otherDirect total cost from List
	 * of BudgetLineItem, if budgetCategoryCode is other for budgetLineItem
	 */
	private BigDecimal getOtherDirectTotal(List<BudgetLineItem> budgetLineItems) {
		BudgetDecimal cost = BudgetDecimal.ZERO;
		for (BudgetLineItem budgetLineItem : budgetLineItems) {
			if (isBudgetCategoryOther(budgetLineItem)) {
				cost = cost.add(budgetLineItem.getLineItemCost());
			}
		}
		return cost.bigDecimalValue();
	}

	/*
	 * This method gets sum of lineItemCost as travel total cost from List of
	 * BudgetLineItem, if budgetCategoryCode is travel for budgetLineItem
	 */
	private BigDecimal getTravelTotal(List<BudgetLineItem> budgetLineItems) {
		BudgetDecimal cost = BudgetDecimal.ZERO;
		for (BudgetLineItem budgetLineItem : budgetLineItems) {
			if (isBudgetCategoryTravel(budgetLineItem)) {
				cost = cost.add(budgetLineItem.getLineItemCost());
			}
		}
		return cost.bigDecimalValue();
	}

	/*
	 * This method gets sum of lineItemCost as equipment total cost from List of
	 * BudgetLineItem, if budgetCategoryCode is equipment for budgetLineItem
	 */
	private BigDecimal getEquipmentTotal(List<BudgetLineItem> budgetLineItems) {
		BudgetDecimal cost = BudgetDecimal.ZERO;
		for (BudgetLineItem budgetLineItem : budgetLineItems) {
			if (isBudgetCategoryEquipment(budgetLineItem)) {
				cost = cost.add(budgetLineItem.getLineItemCost());
			}
		}
		return cost.bigDecimalValue();
	}

	/*
	 * This method gets Arrays of ParticipantPatientCost XMLObject from list of
	 * BudgetLineItems by checking the BudgetCategoryCode as paricipantPatient
	 */
	private ParticipantPatientCosts[] getParticipantPatientCost(
			List<BudgetLineItem> budgetLineItems) {
		List<ParticipantPatientCosts> participantPatientCostList = new ArrayList<ParticipantPatientCosts>();
		ParticipantPatientCosts participantPatientCost = ParticipantPatientCosts.Factory
				.newInstance();
		for (BudgetLineItem budgetLineItem : budgetLineItems) {
			if (isBudgetCategoryParticipantPatient(budgetLineItem)) {
				participantPatientCost.setCost(budgetLineItem.getLineItemCost()
						.bigDecimalValue());
				participantPatientCost.setDescription(budgetLineItem
						.getLineItemDescription());
				participantPatientCost
						.setType(getParticipantPatientType(budgetLineItem
								.getBudgetCategory().getDescription()));
				participantPatientCostList.add(participantPatientCost);
			}
		}
		return participantPatientCostList
				.toArray(new ParticipantPatientCosts[0]);
	}

	/*
	 * This method gets ParticipantPatientType Enum value based on
	 * budgetCategory Description if there is no enum for budgetCategory
	 * Description take as Other enum value
	 */
	private gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ParticipantType.Enum getParticipantPatientType(
			String budgetCatgoryDesc) {
		gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ParticipantType.Enum participantType = ParticipantType.Enum
				.forString(budgetCatgoryDesc);
		if (participantType == null) {
			participantType = ParticipantType.OTHER;
		}
		return participantType;
	}

	/*
	 * This method gets Arrays of TravelCost XMLObject from list of
	 * BudgetLineItems by checking the BudgetCategoryCode as Travel
	 */
	private TravelCosts[] getTravelCosts(List<BudgetLineItem> budgetLineItems) {
		List<TravelCosts> travelCostList = new ArrayList<TravelCosts>();
		for (BudgetLineItem budgetLineItem : budgetLineItems) {
			if (isBudgetCategoryTravel(budgetLineItem)) {
				TravelCosts travelCost = TravelCosts.Factory.newInstance();
				travelCost.setCost(budgetLineItem.getLineItemCost()
						.bigDecimalValue());
				travelCost.setDescription(budgetLineItem
						.getLineItemDescription());
				travelCost.setType(getTravelType(budgetLineItem
						.getBudgetCategory().getDescription()));
				travelCostList.add(travelCost);
			}
		}
		return travelCostList.toArray(new TravelCosts[0]);
	}

	/*
	 * This method gets TravelType Enum value based on budgetCategory
	 * Description
	 */
	private TravelType.Enum getTravelType(String budgetCategoryDesc) {
		TravelType.Enum travelType = TravelType.Enum
				.forString(budgetCategoryDesc);
		return travelType;
	}

	/*
	 * This method gets arrays of otherDirectCost XMLObject from List of
	 * BudgetLineItems by checking the budgetCategoryCode as Other
	 */
	private OtherDirectCosts[] getOtherDirectCosts(
			List<BudgetLineItem> budgetLineItems) {
		List<OtherDirectCosts> otherDirectCostList = new ArrayList<OtherDirectCosts>();
		for (BudgetLineItem budgetLineItem : budgetLineItems) {
			if (isBudgetCategoryOther(budgetLineItem)) {
				OtherDirectCosts otherDirectCost = OtherDirectCosts.Factory
						.newInstance();
				otherDirectCost.setCost(budgetLineItem.getLineItemCost()
						.bigDecimalValue());
				otherDirectCost.setDescription(budgetLineItem
						.getLineItemDescription());
				otherDirectCost.setType(getOtherDirectType(budgetLineItem
						.getBudgetCategory().getDescription()));
				otherDirectCostList.add(otherDirectCost);
			}
		}
		return otherDirectCostList.toArray(new OtherDirectCosts[0]);
	}

	/*
	 * This method gets OtherDirectType Enum value based on budgetCategory
	 * Description if the budgetCategory description role not match with the
	 * enum type the roleType set to Other
	 */
	private gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.OtherDirectType.Enum getOtherDirectType(
			String budgetCategoryDesc) {
		gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.OtherDirectType.Enum otherDirectType = OtherDirectType.Enum
				.forString(budgetCategoryDesc);
		if (otherDirectType == null) {
			otherDirectType = OtherDirectType.OTHER;
		}
		return otherDirectType;
	}

	/*
	 * This method check budgetCagegoryCode for other in BudgetLineItem by
	 * checking budgetCategoryCode travel, equipment, patient and participant
	 */
	private boolean isBudgetCategoryOther(BudgetLineItem budgetLineItem) {
		boolean isOther = true;
		if (isBudgetCategoryEquipment(budgetLineItem)
				&& isBudgetCategoryTravel(budgetLineItem)
				&& isBudgetCategoryParticipantPatient(budgetLineItem)) {
			isOther = false;
		}
		return isOther;
	}

	/*
	 * This method gets arrays of EquipmentCost XMLObject from list of
	 * budgetLineItems by checking the budgetCategory as equipment
	 */
	private EquipmentCosts[] getEquipmentCosts(
			List<BudgetLineItem> budgetLineItems) {
		List<EquipmentCosts> equipmentCostList = new ArrayList<EquipmentCosts>();
		for (BudgetLineItem budgetLineItem : budgetLineItems) {
			if (isBudgetCategoryEquipment(budgetLineItem)) {
				EquipmentCosts equipmentCost = EquipmentCosts.Factory
						.newInstance();
				equipmentCost.setCost(budgetLineItem.getLineItemCost()
						.bigDecimalValue());
				equipmentCost.setDescription(budgetLineItem
						.getLineItemDescription());
				equipmentCost.setEquipmentDescription(budgetLineItem
						.getBudgetCategory().getDescription());
				equipmentCostList.add(equipmentCost);
			}
		}
		return equipmentCostList.toArray(new EquipmentCosts[0]);
	}

	/*
	 * This method check budgetCagegoryCode for travel in BudgetLineItem
	 */
	private boolean isBudgetCategoryTravel(BudgetLineItem budgetLineItem) {
		return budgetLineItem.getBudgetCategoryCode().equals(
				CATEGORY_CODE_TRAVEL_DOMESTIC)
				|| budgetLineItem.getBudgetCategoryCode().equals(
						CATEGORY_CODE_TRAVEL_FOREIGN);
	}

	/*
	 * This method check budgetCagegoryCode for Participant and Patient in
	 * BudgetLineItem
	 */
	private boolean isBudgetCategoryParticipantPatient(
			BudgetLineItem budgetLineItem) {
		return budgetLineItem.getBudgetCategoryCode().equals(
				CATEGORY_CODE_PARTICIPANT_OTHER)
				|| budgetLineItem.getBudgetCategoryCode().equals(
						CATEGORY_CODE_PARTICIPANT_STIPENDS)
				|| budgetLineItem.getBudgetCategoryCode().equals(
						CATEGORY_CODE_PARTICIPANT_SUBSISTANCE)
				|| budgetLineItem.getBudgetCategoryCode().equals(
						CATEGORY_CODE_PARTICIPANT_TRAVEL)
				|| budgetLineItem.getBudgetCategoryCode().equals(
						CATEGORY_CODE_INPATIENT)
				|| budgetLineItem.getBudgetCategoryCode().equals(
						CATEGORY_CODE_OUTPATIENT);
	}

	/*
	 * This method check budgetCagegoryCode for equipment in BudgetLineItem
	 */
	private boolean isBudgetCategoryEquipment(BudgetLineItem budgetLineItem) {
		return budgetLineItem.getBudgetCategoryCode().equals(
				CATEGORY_CODE_EQUIPMENT_RENTAL)
				|| budgetLineItem.getBudgetCategoryCode().equals(
						CATEGORY_CODE_EQUIPMENT);
	}

	/*
	 * This method gets arrays of SalaryAndWagesType XMLObject
	 */
	private SalariesAndWagesType[] getSalaryAndWages(
			List<BudgetLineItem> budgetLineItems) {
		List<SalariesAndWagesType> salariesAndWagesTypeList = new ArrayList<SalariesAndWagesType>();
		for (BudgetLineItem budgetLineItem : budgetLineItems) {
			for (BudgetPersonnelDetails budgetPersDetails : budgetLineItem
					.getBudgetPersonnelDetailsList()) {
				BudgetPerson budgetPerson = budgetPersDetails.getBudgetPerson();
				if (budgetPerson != null) {
					SalariesAndWagesType salariesAndWagesType = getSalariesAndWagesType(
							budgetPersDetails, budgetPerson);
					salariesAndWagesTypeList.add(salariesAndWagesType);
				}
			}
		}
		return salariesAndWagesTypeList.toArray(new SalariesAndWagesType[0]);
	}

	/*
	 * This method computes the salaries and wages details of a BudgetPerson and
	 * populates SalariesAndWagesType
	 */
	private SalariesAndWagesType getSalariesAndWagesType(
			BudgetPersonnelDetails budgetPersDetails, BudgetPerson budgetPerson) {
		SalariesAndWagesType salariesAndWagesType = SalariesAndWagesType.Factory
				.newInstance();
		salariesAndWagesType.setAppointmentType(budgetPerson
				.getAppointmentTypeCode() == null ? Constants.EMPTY_STRING
				: budgetPerson.getAppointmentTypeCode());
		salariesAndWagesType.setAppointmentMonths(new BigDecimal(budgetPerson
				.getAppointmentTypeCode() == null ? Constants.EMPTY_STRING
				: budgetPerson.getAppointmentTypeCode()));
		salariesAndWagesType.setSummerFundingMonths(BigDecimal.ZERO);
		salariesAndWagesType.setAcademicFundingMonths(new BigDecimal(0.0));
		salariesAndWagesType.setFundingMonths(new BigDecimal(0.0));
		KcPerson person = budgetPerson.getPerson();
		salariesAndWagesType.setName(getContactPersonFullName(person
				.getLastName(), person.getFirstName(), person.getMiddleName()));
		salariesAndWagesType.setProjectRole(getProjectRoleType(budgetPerson));
		salariesAndWagesType.setProjectRoleDescription(budgetPerson.getRole());
		salariesAndWagesType.setSalariesTotal(budgetPersDetails
				.getSalaryRequested().bigDecimalValue());
		BigDecimal fringe = getFringeCost(budgetPersDetails).bigDecimalValue();
		salariesAndWagesType.setFringeCost(fringe);
		salariesAndWagesType.setRequestedCost(budgetPersDetails
				.getSalaryRequested().bigDecimalValue());
		salariesAndWagesType.setBaseSalary(budgetPerson.getCalculationBase()
				.bigDecimalValue());
		salariesAndWagesType.setSalaryAndFringeTotal(budgetPersDetails
				.getSalaryRequested().bigDecimalValue().add(fringe));
		return salariesAndWagesType;
	}

	/*
	 * This method gets ProjectRoleType as Enum value if the budgetPerson role
	 * not match with the enum type the roleType set to Other
	 */
	private gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ProjectRoleType.Enum getProjectRoleType(
			BudgetPerson budgetPerson) {
		gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ProjectRoleType.Enum roleType = ProjectRoleType.Enum
				.forString(budgetPerson.getRole());
		if (roleType == null) {
			roleType = ProjectRoleType.OTHER;
		}
		return roleType;
	}

	/*
	 * This method gets BudgetTotalType XMLObject by setting data from totalCost
	 * and costSharingAmount to BudgetTotalType for budgetPeriod and budget
	 */
	private BudgetTotalsType getBudgetTotals(BudgetDecimal totalCost,
			BudgetDecimal costSharingAmount) {
		BudgetTotalsType budgetTotalType = BudgetTotalsType.Factory
				.newInstance();
		budgetTotalType.setFederalCost(new BigDecimal(totalCost.doubleValue()));
		budgetTotalType.setApplicantCost(new BigDecimal(costSharingAmount
				.doubleValue()));
		budgetTotalType.setStateCost(new BigDecimal(0));
		budgetTotalType.setLocalCost(new BigDecimal(0));
		budgetTotalType.setOtherCost(new BigDecimal(0));
		budgetTotalType.setProgramIncome(new BigDecimal(0));
		return budgetTotalType;
	}

	/*
	 * This method gets ResearchCoverPage XMLObject and set values to it from
	 * developmentProposal
	 */
	private ResearchCoverPage getResearchCoverPage(
			DevelopmentProposal developmentProposal, Budget budget)
			throws ParseException {
		ResearchCoverPage researchCoverPage = ResearchCoverPage.Factory
				.newInstance();
		researchCoverPage
				.setSubmissionCategory(getSubmissionCategoryForResearchCoverPage(
						developmentProposal.getActivityType().getDescription(),
						developmentProposal.getCreationStatusCode()));
		researchCoverPage
				.setApplicationCategory(getApplicationCategoryForResearchCoverPage(developmentProposal
						.getProposalType().getDescription()));
		researchCoverPage
				.setApplicantSubmissionQualifiers(getApplicantSubmissionQualifiersForResearchCoverPage());
		researchCoverPage
				.setFederalAgencyReceiptQualifiers(getFederalAgencyReceiptQualifiersForResearchCoverPage());
		researchCoverPage
				.setStateReceiptQualifiers(getStateReceiptQualifiersForResearchCoverPage());
		researchCoverPage
				.setStateIntergovernmentalReview(getStateIntergovernmentalReviewForResearchCoverPage());
		researchCoverPage
				.setFederalDebtDelinquencyQuestions(getFederalDebtDelinquencyQuestionForResearchCoverPage());
		researchCoverPage.setProjectDates(getProjectDatesForResearchCoverPage(
				developmentProposal.getRequestedStartDateInitial(),
				developmentProposal.getRequestedEndDateInitial()));
		researchCoverPage
				.setBudgetTotals(getBudgetTotalsForResearchCoverPage(budget));
		researchCoverPage
				.setProjectTitle(developmentProposal.getTitle() == null ? DEFAULT_VALUE_UNKNOWN
						: developmentProposal.getTitle());
		researchCoverPage
				.setOtherAgencyQuestions(getOtherAgencyQuestionsForResearchCoverPage(developmentProposal));
		researchCoverPage
				.setApplicantOrganization(getApplicantOrganizationForResearchCoverPage(developmentProposal));
		researchCoverPage
				.setPrimaryProjectSite(getProjectSiteForResearchCoverPage(developmentProposal));
		researchCoverPage
				.setProgramDirectorPrincipalInvestigator(getProgramDirectorPrincipalInvestigatorForResearchCoverPage(developmentProposal));
		researchCoverPage
				.setFundingOpportunityDetails(getFundingOpportunityDetailsForResearchCoverPage(developmentProposal));
		researchCoverPage
				.setAuthorizedOrganizationalRepresentative(getAuthorizedOrganizationalRepresentative(developmentProposal));
		return researchCoverPage;
	}

	/*
	 * This method gets AuthorizedOrganizationalRepresentativeType XMLObject and
	 * set data to it from authorisedOraganization
	 */
	private AuthorizedOrganizationalRepresentativeType getAuthorizedOrganizationalRepresentative(
			DevelopmentProposal developmentProposal) {
		Organization authorisedOrg = getAuthorisedOrganization(developmentProposal);
		Rolodex rolodex = authorisedOrg.getRolodex();
		AuthorizedOrganizationalRepresentativeType authOrgRepType = AuthorizedOrganizationalRepresentativeType.Factory
				.newInstance();
		if (authorisedOrg != null) {
			authOrgRepType.setPositionTitle(rolodex.getTitle());
			authOrgRepType
					.setContactInformation(getPersonContactInformation(rolodex));
			authOrgRepType.setName(getContactPersonFullName(rolodex
					.getLastName(), rolodex.getFirstName(), rolodex
					.getMiddleName()));
		} else {
			authOrgRepType.setName(getContactPersonFullName(
					DEFAULT_VALUE_UNKNOWN, DEFAULT_VALUE_UNKNOWN,
					DEFAULT_VALUE_UNKNOWN));
			authOrgRepType.setPositionTitle(DEFAULT_VALUE_UNKNOWN);
			authOrgRepType
					.setContactInformation(getOrganizationPersonContactInformationWithDefaultValues());
		}
		return authOrgRepType;
	}

	/*
	 * This method gets Authorized Organization from proposalSites by checking
	 * locationTypeCode value 1 from list of proposalSites
	 */
	private Organization getAuthorisedOrganization(
			DevelopmentProposal developmentProposal) {
		Organization authorisedOrg = null;
		for (ProposalSite proposalSite : developmentProposal.getProposalSites()) {
			if (proposalSite.getLocationTypeCode() == 1) {
				authorisedOrg = proposalSite.getOrganization();
			}
		}
		return authorisedOrg;
	}

	/*
	 * This method gets ContactInfoType XMLObject and sets data to it from
	 * Rolodex
	 */
	private ContactInfoType getPersonContactInformation(Rolodex rolodex) {
		ContactInfoType contactInfoType = ContactInfoType.Factory.newInstance();
		String emailAddress = rolodex.getEmailAddress();
		if (emailAddress != null) {
			contactInfoType.setEmail(emailAddress);
		}
		String faxNumber = rolodex.getFaxNumber();
		if (faxNumber != null) {
			contactInfoType.setFaxNumber(faxNumber);
		}
		String officePhone = rolodex.getPhoneNumber();
		if (officePhone != null) {
			contactInfoType.setPhoneNumber(officePhone);
		}
		contactInfoType.setPostalAddress(getOrganizationAddress(rolodex));
		return contactInfoType;
	}

	/*
	 * This method gets FundingOpportunityDetailsType XMLObject and set
	 * programAnnouncementTitle and Number to it
	 */
	private FundingOpportunityDetailsType getFundingOpportunityDetailsForResearchCoverPage(
			DevelopmentProposal developmentProposal) {
		FundingOpportunityDetailsType fundingOpportunityType = FundingOpportunityDetailsType.Factory
				.newInstance();
		String programAnnouncementNumber = developmentProposal
				.getProgramAnnouncementNumber();
		String programAnnouncementTitle = developmentProposal
				.getProgramAnnouncementTitle();
		fundingOpportunityType
				.setFundingOpportunityNumber(programAnnouncementNumber == null ? EMPTY_STRING
						: programAnnouncementNumber);
		fundingOpportunityType
				.setFundingOpportunityTitle(programAnnouncementTitle == null ? EMPTY_STRING
						: programAnnouncementTitle);
		return fundingOpportunityType;
	}

	/*
	 * This method gets ProgramDirectorPrincipalInvestigator XMLObject and set
	 * Principal Investigator data to it
	 */
	private ProgramDirectorPrincipalInvestigator getProgramDirectorPrincipalInvestigatorForResearchCoverPage(
			DevelopmentProposal developmentProposal) {
		ProgramDirectorPrincipalInvestigator principalInvestigatorType = ProgramDirectorPrincipalInvestigator.Factory
				.newInstance();
		ProposalPerson principalInvestigator = PrintingUtils
				.getPrincipalInvestigator(developmentProposal
						.getProposalPersons());
		principalInvestigatorType
				.setContactInformation(getPersonContactInformation(principalInvestigator));
		principalInvestigatorType
				.setName(getContactPersonFullName(principalInvestigator
						.getLastName(), principalInvestigator.getFirstName(),
						principalInvestigator.getMiddleName()));
		return principalInvestigatorType;
	}

	/*
	 * This method gets ProjectSiteType XMLObject and set data from performing
	 * organization to it
	 */
	private ProjectSiteType getProjectSiteForResearchCoverPage(
			DevelopmentProposal developmentProposal) {
		ProposalSite performingOrg = developmentProposal
				.getPerformingOrganization();
		ProjectSiteType projectSiteType = ProjectSiteType.Factory.newInstance();
		Organization organization = performingOrg.getOrganization();
		projectSiteType.setOrganizationName(organization.getOrganizationName());
		projectSiteType.setCongressionalDistrict(organization
				.getCongressionalDistrict());
		projectSiteType.setPostalAddress(getOrganizationAddress(organization
				.getRolodex()));
		return projectSiteType;
	}

	/*
	 * This method gets ApplicantOrganizationType XMLObject for
	 * ResearchCoverPage and set the data from organization to it if data is
	 * there else put default data
	 */
	private ApplicantOrganizationType getApplicantOrganizationForResearchCoverPage(
			DevelopmentProposal developmentProposal) {
		Organization organization = developmentProposal
				.getApplicantOrganization().getOrganization();
		OrganizationType organizationType = organization.getOrganizationType(0);
		ApplicantOrganizationType applicantOrganizationType = ApplicantOrganizationType.Factory
				.newInstance();
		applicantOrganizationType.setOrganizationName(organization
				.getOrganizationName() == null ? DEFAULT_VALUE_UNKNOWN
				: organization.getOrganizationName());
		applicantOrganizationType.setOrganizationDUNS(organization
				.getDunsNumber() == null ? DEFAULT_VALUE_UNKNOWN : organization
				.getDunsNumber());
		applicantOrganizationType.setOrganizationEIN(organization
				.getFedralEmployerId() == null ? DEFAULT_VALUE_UNKNOWN
				: organization.getFedralEmployerId());
		if (organization.getPhsAccount() != null) {
			applicantOrganizationType.setPHSAccountID(organization
					.getPhsAccount());
		}
		applicantOrganizationType
				.setOrganizationCategoryCode(organizationType == null ? DEFAULT_VALUE_UNKNOWN
						: organizationType.getOrganizationTypeCode().toString());
		applicantOrganizationType
				.setOrganizationCategoryDescription(organizationType == null ? DEFAULT_VALUE_UNKNOWN
						: organizationType.getOrganizationTypeList()
								.getDescription());
		applicantOrganizationType
				.setOrganizationCongressionalDistrict(organization
						.getCongressionalDistrict() == null ? DEFAULT_VALUE_UNKNOWN
						: organization.getCongressionalDistrict());
		applicantOrganizationType
				.setOrganizationAddress(getOrganizationAddress(organization
						.getRolodex()));
		applicantOrganizationType
				.setOrganizationContactPerson(getOrganizationContactPerson(developmentProposal
						.getApplicantOrganization().getRolodex()));
		String cageNumber = organization.getCageNumber();
		if (cageNumber != null) {
			applicantOrganizationType.setCageNumber(cageNumber);
		}
		return applicantOrganizationType;
	}

	/*
	 * This method gets OrganizationContactPerson XMLObject and set data from
	 * Rolodex if Rolodex is there else it set default values to it
	 */
	private OrganizationContactPerson getOrganizationContactPerson(
			Rolodex rolodex) {
		OrganizationContactPerson orgContactPerson = OrganizationContactPerson.Factory
				.newInstance();
		if (rolodex != null) {
			orgContactPerson.setName(getContactPersonFullName(rolodex
					.getLastName(), rolodex.getFirstName(), rolodex
					.getMiddleName()));
			orgContactPerson.setPositionTitle(rolodex.getTitle());
			orgContactPerson
					.setContactInformation(getPersonContactInformation(rolodex));
		} else {
			orgContactPerson.setName(getContactPersonFullName(
					DEFAULT_VALUE_UNKNOWN, DEFAULT_VALUE_UNKNOWN,
					DEFAULT_VALUE_UNKNOWN));
			orgContactPerson.setPositionTitle(DEFAULT_VALUE_UNKNOWN);
			orgContactPerson
					.setContactInformation(getOrganizationPersonContactInformationWithDefaultValues());
		}
		return orgContactPerson;
	}

	/*
	 * This method gets contactInfoType XMLObject and set email, faxNumber,
	 * phoneNumber, postalAddress to it from proposalPerson
	 */
	private ContactInfoType getPersonContactInformation(
			ProposalPerson proposalPerson) {
		ContactInfoType contactInfoType = ContactInfoType.Factory.newInstance();
		String emailAddress = proposalPerson.getEmailAddress();
		if (emailAddress != null) {
			contactInfoType.setEmail(emailAddress);
		}
		String faxNumber = proposalPerson.getFaxNumber();
		if (faxNumber != null) {
			contactInfoType.setFaxNumber(faxNumber);
		}
		String officePhone = proposalPerson.getOfficePhone();
		if (officePhone != null) {
			contactInfoType.setPhoneNumber(officePhone);
		}
		contactInfoType.setPostalAddress(getPostalAddress(proposalPerson));
		return contactInfoType;
	}

	/*
	 * This method gets contactInfoType XMLObject and set email, faxNumber,
	 * phoneNumber, postalAddress default values to it
	 */
	private ContactInfoType getOrganizationPersonContactInformationWithDefaultValues() {
		ContactInfoType contactInfoType = ContactInfoType.Factory.newInstance();
		contactInfoType.setEmail(DEFAULT_VALUE_UNKNOWN);
		contactInfoType.setFaxNumber(DEFAULT_VALUE_UNKNOWN);
		contactInfoType.setPhoneNumber(DEFAULT_VALUE_UNKNOWN);
		contactInfoType.setPostalAddress(getPostalAddressWithDefaultValues());
		return contactInfoType;
	}

	/*
	 * This method gets PostalAddressType XMLObject and setting proposalPerson
	 * details to it
	 */
	private PostalAddressType getPostalAddress(ProposalPerson proposalPerson) {
		PostalAddressType postalAddressType = PostalAddressType.Factory
				.newInstance();
		postalAddressType.setStreetArray(getStreetAddress(proposalPerson
				.getAddressLine1(), proposalPerson.getAddressLine2(),
				proposalPerson.getAddressLine3()));
		String city = proposalPerson.getCity();
		postalAddressType.setCity((city == null || city.trim().equals(
				Constants.EMPTY_STRING)) ? DEFAULT_VALUE_UNKNOWN : city);
		postalAddressType.setState(proposalPerson.getState());
		String postalCode = proposalPerson.getPostalCode();
		postalAddressType.setPostalCode((postalCode == null || postalCode
				.trim().equals(Constants.EMPTY_STRING)) ? DEFAULT_VALUE_UNKNOWN
				: postalCode);
		String county = proposalPerson.getCounty();
		postalAddressType.setCountry((county == null || county.trim().equals(
				Constants.EMPTY_STRING)) ? DEFAULT_VALUE_UNKNOWN : county);
		return postalAddressType;
	}

	/*
	 * This method gets PostalAddressType XMLObject and setting default details
	 * to it
	 */
	private PostalAddressType getPostalAddressWithDefaultValues() {
		PostalAddressType postalAddressType = PostalAddressType.Factory
				.newInstance();
		postalAddressType.setStreetArray(getStreetAddress(
				DEFAULT_VALUE_UNKNOWN, DEFAULT_VALUE_UNKNOWN,
				DEFAULT_VALUE_UNKNOWN));
		postalAddressType.setCity(DEFAULT_VALUE_UNKNOWN);
		postalAddressType.setState(DEFAULT_VALUE_UNKNOWN);
		postalAddressType.setPostalCode(DEFAULT_VALUE_UNKNOWN);
		postalAddressType.setCountry(DEFAULT_VALUE_UNKNOWN);
		return postalAddressType;
	}

	/*
	 * This method gets personFullNameType XMLObject and setting lastName,
	 * firstName, middleName data to it
	 */
	private PersonFullNameType getContactPersonFullName(String lastName,
			String firstName, String middleName) {
		PersonFullNameType personFullNameType = PersonFullNameType.Factory
				.newInstance();
		personFullNameType.setLastName(lastName);
		personFullNameType.setFirstName(firstName);
		personFullNameType.setMiddleName(middleName);
		return personFullNameType;
	}

	/*
	 * This method gets PostalAddressType XMLObject and setting rolodex details
	 * to it
	 */
	private PostalAddressType getOrganizationAddress(Rolodex rolodex) {
		PostalAddressType postalAddressType = PostalAddressType.Factory
				.newInstance();
		postalAddressType.setStreetArray(getStreetAddress(rolodex
				.getAddressLine1(), rolodex.getAddressLine2(), rolodex
				.getAddressLine3()));
		String city = rolodex.getCity();
		postalAddressType.setCity((city == null || city.trim().equals(
				Constants.EMPTY_STRING)) ? DEFAULT_VALUE_UNKNOWN : city);
		postalAddressType.setState(rolodex.getState());
		String postalCode = rolodex.getPostalCode();
		postalAddressType.setPostalCode((postalCode == null || postalCode
				.trim().equals(Constants.EMPTY_STRING)) ? DEFAULT_VALUE_UNKNOWN
				: postalCode);
		String county = rolodex.getCounty();
		postalAddressType.setCountry((county == null || county.trim().equals(
				Constants.EMPTY_STRING)) ? DEFAULT_VALUE_UNKNOWN : county);
		return postalAddressType;
	}

	/*
	 * This method gets arrays of Address from address1, address2, address3
	 */
	private String[] getStreetAddress(String address1, String address2,
			String address3) {
		List<String> streetAddress = new ArrayList<String>();
		if (address1 != null) {
			streetAddress.add(address1);
		}
		if (address2 != null) {
			streetAddress.add(address2);
		}
		if (address3 != null) {
			streetAddress.add(address3);
		}
		return streetAddress.toArray(new String[0]);
	}

	/*
	 * This method gets OtherAgencyQuestionsType XMLObject by setting
	 * AgencyIndicator and OtherAgencyName name to it if QuestionId value is 15
	 * else it set Only AgencyIndicator
	 */
	private OtherAgencyQuestionsType getOtherAgencyQuestionsForResearchCoverPage(
			DevelopmentProposal developmentProposal) {
		OtherAgencyQuestionsType otherAgencyQuestionsType = OtherAgencyQuestionsType.Factory
				.newInstance();
		otherAgencyQuestionsType.setOtherAgencyIndicator(false);
		for (ProposalYnq proposalYnq : developmentProposal.getProposalYnqs()) {
			if (proposalYnq.getQuestionId().equals(PROPOSALQUESTION_ID15)
					&& proposalYnq.getAnswer() != null) {
				otherAgencyQuestionsType.setOtherAgencyIndicator(proposalYnq
						.getAnswer().equals(ANSWER_INDICATOR_VALUE) ? true
						: false);
				otherAgencyQuestionsType.setOtherAgencyNames(proposalYnq
						.getExplanation() == null ? EMPTY_STRING : proposalYnq
						.getExplanation());
			}
		}
		return otherAgencyQuestionsType;
	}

	/*
	 * This method gets CoreBudgetTotalsType XMLObject and setting budget data
	 * to it if budget is available and budgetModular flag is set else setting
	 * to default value as 0
	 */
	private CoreBudgetTotalsType getBudgetTotalsForResearchCoverPage(
			Budget budget) {
		CoreBudgetTotalsType coreBudgetTotalsType = CoreBudgetTotalsType.Factory
				.newInstance();
		if (budget != null && budget.getVersionNumber() > 0) {
			if (!budget.getModularBudgetFlag()) {
				coreBudgetTotalsType.setApplicantCost(budget
						.getCostSharingAmount().bigDecimalValue());
				coreBudgetTotalsType.setFederalCost(budget.getTotalCost()
						.bigDecimalValue());
				coreBudgetTotalsType.setOtherCost(budget.getTotalIndirectCost()
						.bigDecimalValue());
			}
		} else {
			coreBudgetTotalsType.setApplicantCost(BigDecimal.ZERO);
			coreBudgetTotalsType.setFederalCost(BigDecimal.ZERO);
			coreBudgetTotalsType.setOtherCost(BigDecimal.ZERO);
		}
		coreBudgetTotalsType.setLocalCost(BigDecimal.ZERO);
		coreBudgetTotalsType.setProgramIncome(BigDecimal.ZERO);
		coreBudgetTotalsType.setStateCost(BigDecimal.ZERO);
		return coreBudgetTotalsType;
	}

	/*
	 * This method gets CoreProjectDatesType XMLObject and setting
	 * projectStartDate and projectEndDate data to it from developmentProposal
	 * requestedStartDateInitial and requestedEndDateInitial
	 */
	private CoreProjectDatesType getProjectDatesForResearchCoverPage(
			Date startDate, Date endDate) {
		CoreProjectDatesType coreProjectDatesType = CoreProjectDatesType.Factory
				.newInstance();
		coreProjectDatesType.setProjectStartDate(dateTimeService
				.getCalendar(startDate));
		coreProjectDatesType.setProjectEndDate(dateTimeService
				.getCalendar(endDate));
		return coreProjectDatesType;
	}

	/*
	 * This method gets CoreFederalDebtDelinquencyQuestionsType XMLObject and
	 * setting pplicantDelinquentIndicator data to it
	 */
	private CoreFederalDebtDelinquencyQuestionsType getFederalDebtDelinquencyQuestionForResearchCoverPage() {
		CoreFederalDebtDelinquencyQuestionsType ccoreFedDebtQuestionsType = CoreFederalDebtDelinquencyQuestionsType.Factory
				.newInstance();
		ccoreFedDebtQuestionsType.setApplicantDelinquentIndicator(false);
		return ccoreFedDebtQuestionsType;
	}

	/*
	 * This method gets CoreStateIntergovernmentalReviewType XMLObject and
	 * setting ReviewAvailityDate and SubjectToreviewQuestion data to it
	 */
	private CoreStateIntergovernmentalReviewType getStateIntergovernmentalReviewForResearchCoverPage()
			throws ParseException {
		CoreStateIntergovernmentalReviewType coreStateIntergovRevType = CoreStateIntergovernmentalReviewType.Factory
				.newInstance();
		coreStateIntergovRevType.setReviewAvailabilityDate(dateTimeService
				.getCalendar(dateTimeService
						.convertToDate(REVIEW_AVAILABILITY_DATE)));
		coreStateIntergovRevType.setSubjectToReviewQuestion(true);
		return coreStateIntergovRevType;
	}

	/*
	 * This method gets CoreStateReceiptQualifiersType XMLObject and setting
	 * stateReceiptDate to it
	 */
	private CoreStateReceiptQualifiersType getStateReceiptQualifiersForResearchCoverPage()
			throws ParseException {
		CoreStateReceiptQualifiersType coreStateReceiptQualifiersType = CoreStateReceiptQualifiersType.Factory
				.newInstance();
		coreStateReceiptQualifiersType
				.setStateReceiptDate(dateTimeService
						.getCalendar(dateTimeService
								.convertToDate(STATE_RECEIPT_DATE)));
		return coreStateReceiptQualifiersType;
	}

	/*
	 * This method gets CoreFederalAgencyReceiptQualifiersType XMLObject and
	 * setting AgencyName and AgencyReceiptDate to it
	 */
	private CoreFederalAgencyReceiptQualifiersType getFederalAgencyReceiptQualifiersForResearchCoverPage()
			throws ParseException {
		CoreFederalAgencyReceiptQualifiersType coreFedAgencyRecQualType = CoreFederalAgencyReceiptQualifiersType.Factory
				.newInstance();
		coreFedAgencyRecQualType.setAgencyName(Constants.NIH_SPONSOR_ACRONYM);
		coreFedAgencyRecQualType
				.setAgencyReceiptDate(dateTimeService
						.getCalendar(dateTimeService
								.convertToDate(AGENCY_RECEIPT_DATE)));
		return coreFedAgencyRecQualType;
	}

	/*
	 * This method gets CoreApplicantSubmissionQualifiersType XMLObject and
	 * setting Application date to it
	 */
	private CoreApplicantSubmissionQualifiersType getApplicantSubmissionQualifiersForResearchCoverPage()
			throws ParseException {
		CoreApplicantSubmissionQualifiersType coreApplicantSubmissionQualifiersType = CoreApplicantSubmissionQualifiersType.Factory
				.newInstance();
		coreApplicantSubmissionQualifiersType
				.setApplicationDate(dateTimeService.getCalendar(dateTimeService
						.convertToDate(APPLICATION_DATE)));

		return coreApplicantSubmissionQualifiersType;
	}

	/*
	 * This method gets CoreApplicationCategoryType XMLObject and setting
	 * description to it from ProposalType which comes from DevelopmentProposal
	 */
	private CoreApplicationCategoryType getApplicationCategoryForResearchCoverPage(
			String propTypeDesc) {
		CoreApplicationCategoryType coreApplicationCategoryType = CoreApplicationCategoryType.Factory
				.newInstance();
		coreApplicationCategoryType
				.setCategoryIdentifier(propTypeDesc == null ? EMPTY_STRING
						: propTypeDesc);
		return coreApplicationCategoryType;
	}

	/*
	 * This method gets CoreSubmissionCategoryType XMLObject and setting data to
	 * CoreSubmissionCategoryType object from developmentProposal
	 * activityDescription and creationStatusCode data
	 */
	private CoreSubmissionCategoryType getSubmissionCategoryForResearchCoverPage(
			String activityDescription, String creationStatusCode) {
		CoreSubmissionCategoryType coreSubmissionCategoryType = CoreSubmissionCategoryType.Factory
				.newInstance();
		coreSubmissionCategoryType
				.setProjectCategory(activityDescription == null ? EMPTY_STRING
						: activityDescription);

		coreSubmissionCategoryType
				.setSubmissionStatus(creationStatusCode == null ? EMPTY_STRING
						: creationStatusCode);
		return coreSubmissionCategoryType;
	}

	/*
	 * This method will set the values to key person type attributes and finally
	 * return KeyPersonType xml object.
	 */
	private KeyPersonType getKeyPersonType(
			DevelopmentProposal developmentProposal) {
		KeyPersonType keyPersonType = null;
		List<ProposalPerson> proposalPersons = developmentProposal
				.getProposalPersons();
		boolean investigatorFound = false;
		for (ProposalPerson proposalPerson : proposalPersons) {
			if (proposalPerson.isInvestigator()) {
				keyPersonType = getKeyPersonTypeWithValues(developmentProposal,
						proposalPerson);
				investigatorFound = true;
				break;
			}
		}
		if (!investigatorFound) {
			// If no proposal persons found then set to default values
			keyPersonType = getkeyPersonTypeWithDefaultValues();
		}
		return keyPersonType;

	}

	/*
	 * This method will set the values to key person type attributes like
	 * organization department, organization name , position title etc ... if
	 * key person found.
	 */
	private KeyPersonType getKeyPersonTypeWithValues(
			DevelopmentProposal developmentProposal,
			ProposalPerson proposalPerson) {
		KeyPersonType keyPersonType;
		keyPersonType = KeyPersonType.Factory.newInstance();
		PersonFullNameType personFullNameType = getPersonFullName(proposalPerson);
		keyPersonType.setName(personFullNameType);
		ContactInfoType contactInfoType = getContactInfoType(proposalPerson);
		keyPersonType.setContactInformation(contactInfoType);
		// TODO :AuthenticationCredential Not found
		// keyPersonType.setAuthenticationCredential();
		// TODO :BiographicalSketch Not found
		// keyPersonType.setBiographicalSketch();
		KeyPersonFlag keyPersonFlag = getKeyPersonFlag(proposalPerson);
		keyPersonType.setKeyPersonFlag(keyPersonFlag);
		keyPersonType.setSocialSecurityNumber(proposalPerson
				.getSocialSecurityNumber());
		String unitName = getUnitName(proposalPerson);
		if (unitName != null) {
			keyPersonType.setOrganizationDepartment(unitName);
		}
		Organization organization = getOrganizationFromDevelopmentProposal(developmentProposal);
		keyPersonType.setOrganizationName(organization.getOrganizationName());
		// TODO :OrganizationDivision Not found
		// keyPersonType.setOrganizationDivision();
		if (proposalPerson.getPrimaryTitle() != null) {
			keyPersonType.setPositionTitle(proposalPerson.getPrimaryTitle());
		}
		return keyPersonType;
	}

	/*
	 * This method will set the default values to key person type xml object
	 * attributes.
	 */
	private KeyPersonType getkeyPersonTypeWithDefaultValues() {
		KeyPersonType keyPersonType = KeyPersonType.Factory.newInstance();
		keyPersonType.setAuthenticationCredential(DEFAULT_VALUE_UNKNOWN);
		keyPersonType.setBiographicalSketch(DEFAULT_VALUE_UNKNOWN);
		ContactInfoType contactInfoType = ContactInfoType.Factory.newInstance();
		PostalAddressType postalAddressType = PostalAddressType.Factory
				.newInstance();
		postalAddressType.setCity(DEFAULT_VALUE_UNKNOWN);
		postalAddressType.setPostalCode(DEFAULT_VALUE_UNKNOWN);
		postalAddressType.setCountry(DEFAULT_VALUE_UNKNOWN);
		contactInfoType.setPostalAddress(postalAddressType);
		keyPersonType.setContactInformation(contactInfoType);
		KeyPersonFlag flag = KeyPersonFlag.Factory.newInstance();
		flag.setKeyPersonFlagCode(DEFAULT_VALUE_KEY_PERSON_FLAG_CODE);
		flag
				.setKeyPersonFlagDesc(DEFAULT_VALUE_FOR_KEY_PERSON_FLAG_DESCRIPTION);
		keyPersonType.setKeyPersonFlag(flag);
		PersonFullNameType personFullNameType = PersonFullNameType.Factory
				.newInstance();
		personFullNameType.setFirstName(DEFAULT_VALUE_UNKNOWN);
		personFullNameType.setLastName(DEFAULT_VALUE_UNKNOWN);
		keyPersonType.setName(personFullNameType);
		keyPersonType.setOrganizationDepartment(DEFAULT_VALUE_UNKNOWN);
		keyPersonType.setOrganizationDivision(DEFAULT_VALUE_UNKNOWN);
		keyPersonType.setOrganizationName(DEFAULT_VALUE_UNKNOWN);
		keyPersonType.setPositionTitle(DEFAULT_VALUE_UNKNOWN);
		// TODO: Need to confirm is it Account Identifier is same as ssn
		keyPersonType.setSocialSecurityNumber(DEFAULT_VALUE_FOR_SSN);
		return keyPersonType;
	}

	/*
	 * This method will return the unit name from the unit which is lead unit.
	 */
	private String getUnitName(ProposalPerson proposalPerson) {
		String unitName = null;
		for (ProposalPersonUnit proposalPersonUnit : proposalPerson.getUnits()) {
			if (proposalPersonUnit.isLeadUnit()) {
				Unit unit = proposalPersonUnit.getUnit();
				if (unit != null && unit.getUnitName() != null) {
					unitName = unit.getUnitName();
				}
				break;
			}
		}
		return unitName;
	}

	/*
	 * This method will set values to the values to key person flag attributes
	 * KeyPersonFlagCode,KeyPersonFlagDesc.
	 */
	private KeyPersonFlag getKeyPersonFlag(ProposalPerson proposalPerson) {
		KeyPersonFlag keyPersonFlag = KeyPersonFlag.Factory.newInstance();
		if (proposalPerson.getPercentageEffort() != null
				&& proposalPerson.getPercentageEffort().intValue() != 999) {
			keyPersonFlag
					.setKeyPersonFlagCode(DEFAULT_VALUE_KEY_PERSON_FLAG_CODE);
			keyPersonFlag
					.setKeyPersonFlagDesc(KEY_PERSON_FLAG_DESCRIPTION_VALUE_KEY_PERSON);
		} else {
			keyPersonFlag
					.setKeyPersonFlagCode(KEY_PERSON_FLAG_CODE_VALUE_FALSE);
			keyPersonFlag
					.setKeyPersonFlagDesc(KEY_PERSON_FLAG_DESCRIPTION_VALUE_COLLABORATOR);
		}
		return keyPersonFlag;
	}

	/*
	 * This method will set the values to contact info type attributes like
	 * email , fax number,phone number , postal address.
	 */
	private ContactInfoType getContactInfoType(ProposalPerson proposalPerson) {
		ContactInfoType contactInfoType = ContactInfoType.Factory.newInstance();
		contactInfoType.setEmail(proposalPerson.getEmailAddress());
		contactInfoType.setFaxNumber(proposalPerson.getFaxNumber());
		contactInfoType.setPhoneNumber(proposalPerson.getPhoneNumber());
		PostalAddressType postalAddressType = getPostalAddressType(proposalPerson);
		contactInfoType.setPostalAddress(postalAddressType);
		return contactInfoType;
	}

	/*
	 * This method will set the values to PostalAddressType attributes like
	 * city,country, postal code , and state.
	 */
	private PostalAddressType getPostalAddressType(ProposalPerson proposalPerson) {
		PostalAddressType postalAddressType = PostalAddressType.Factory
				.newInstance();
		postalAddressType.setCity(proposalPerson.getCity());
		postalAddressType.setCountry(proposalPerson.getCounty());
		postalAddressType.setPostalCode(proposalPerson.getPostalCode());
		postalAddressType.setState(proposalPerson.getState());
		return postalAddressType;
	}

	/*
	 * This method will set the values to person full name type attributes like
	 * first name,last name, and middle name.
	 */
	private PersonFullNameType getPersonFullName(ProposalPerson proposalPerson) {
		PersonFullNameType personFullNameType = PersonFullNameType.Factory
				.newInstance();
		personFullNameType.setFirstName(proposalPerson.getFirstName());
		personFullNameType.setLastName(proposalPerson.getLastName());
		personFullNameType.setMiddleName(proposalPerson.getMiddleName());
		return personFullNameType;
	}

	/*
	 * This method will set the values to OrgAssurancesType xml object
	 * attributes like
	 * LobbyingQuestion,GeneralCertificationQuestion,DebarmentAndSuspension and
	 * DrugFreeWorkplace.
	 */
	private OrgAssurancesType getOrgAssurances(
			DevelopmentProposal developmentProposal) {
		OrgAssurancesType orgAssurancesType = OrgAssurancesType.Factory
				.newInstance();
		List<OrganizationYnq> organizationYnqs = null;
		Organization organization = getOrganizationFromDevelopmentProposal(developmentProposal);
		if (organization != null && organization.getOrganizationId() != null) {
			organizationYnqs = getOrganizationYNQ(organization
					.getOrganizationId());
		}
		if (organizationYnqs != null) {
			for (OrganizationYnq organizationYnq : organizationYnqs) {
				if (organizationYnq.getQuestionId()
						.equals(LOBBYING_QUESTION_ID)) {
					orgAssurancesType
							.setLobbyingQuestion(getAnswerFromOrganizationYnq(organizationYnq));
				}
				if (organizationYnq.getQuestionId().equals(
						GENERAL_CERTIFICATION_QUESTION_ID)) {
					orgAssurancesType
							.setGeneralCertificationQuestion(getAnswerFromOrganizationYnq(organizationYnq));
				}
			}
			orgAssurancesType.setDebarmentAndSuspension(getAssuranceType(
					organizationYnqs, ORGANIZATION_QUESTION_ID_I8));
			orgAssurancesType.setDrugFreeWorkplace(getAssuranceType(
					organizationYnqs, ORGANIZATION_QUESTION_ID_H5));
		}
		return orgAssurancesType;
	}

	/*
	 * This method will set the values to the Assurance type attributes if
	 * question id I8 found in organization YNQs.
	 */
	private AssuranceType getAssuranceType(
			List<OrganizationYnq> organizationYnqs, String questionId) {
		AssuranceType assuranceType = AssuranceType.Factory.newInstance();
		for (OrganizationYnq organizationYnq : organizationYnqs) {
			if (organizationYnq.getQuestionId().equals(questionId)) {
				assuranceType
						.setYesNoAnswer(getAnswerFromOrganizationYnq(organizationYnq));
				assuranceType
						.setExplanation(getExplanationFromOrganizationYnq(organizationYnq));
			}
		}
		return assuranceType;
	}

	/*
	 * This method return explanation if explanation found for this question
	 * otherwise empty string
	 */
	private String getExplanationFromOrganizationYnq(
			OrganizationYnq organizationYnq) {
		return organizationYnq.getExplanation() == null ? EMPTY_STRING
				: organizationYnq.getExplanation();
	}

	/*
	 * This method return true if question is answered otherwise false .
	 */
	private boolean getAnswerFromOrganizationYnq(OrganizationYnq organizationYnq) {
		return organizationYnq.getAnswer().equals(ANSWER_INDICATOR_VALUE) ? true
				: false;
	}

	/*
	 * This method will set the values to project description attributes like
	 * human subject type,animal subject , and project survey.
	 * 
	 */
	private ProjectDescription getProjectDescription(
			DevelopmentProposal developmentProposal) {
		ProjectDescription projectDescription = ProjectDescription.Factory
				.newInstance();
		projectDescription
				.setHumanSubject(getHumanSubjectsType(developmentProposal));
		projectDescription
				.setAnimalSubject(getAnimalSubject(developmentProposal));
		projectDescription
				.setProjectSurvey(getProjectSurvey(developmentProposal));
		projectDescription.setProjectSummary(getDescriptionBlockType(
				developmentProposal, PROJECT_SUMMARY_BLOCK_TYPE, true));
		projectDescription.setFacilitiesDescription(getDescriptionBlockType(
				developmentProposal, FACILITIES_BLOCK_TYPE, false));
		projectDescription.setEquipmentDescription(getDescriptionBlockType(
				developmentProposal, EQUIPMENT_BLOCK_TYPE, false));
		projectDescription.setReferences(getDescriptionBlockType(
				developmentProposal, REFERENCES_BLOCK_TYPE, true));
		return projectDescription;
	}

	/*
	 * This method will get the DescriptionBlockType based on the block type
	 * (summary,facilities,equipment, and references).
	 */
	private DescriptionBlockType getDescriptionBlockType(
			DevelopmentProposal developmentProposal, String blockType,
			boolean isFile) {
		DescriptionBlockType descriptionBlockType = DescriptionBlockType.Factory
				.newInstance();
		if (isFile) {
			descriptionBlockType.setFileIdentifier(new StringBuilder(
					developmentProposal.getProposalNumber()).append(blockType)
					.toString());
		} else {
			descriptionBlockType.setText(blockType);
		}
		return descriptionBlockType;
	}

	/*
	 * This method will set the values to project survey attributes based on
	 * whether question answered or not status. If no proposal YNQ 's then set
	 * to default values.
	 */
	private ProjectSurvey getProjectSurvey(
			DevelopmentProposal developmentProposal) {
		ProjectSurvey projectSurvey = ProjectSurvey.Factory.newInstance();
		List<ProposalYnq> proposalYnqs = developmentProposal.getProposalYnqs();
		if (!proposalYnqs.isEmpty()) {
			for (ProposalYnq proposalYnq : proposalYnqs) {
				boolean questionAnswered = false;
				String answer = proposalYnq.getAnswer();
				String questionId = proposalYnq.getQuestionId();
				String explanation = proposalYnq.getExplanation() == null ? EMPTY_STRING
						: proposalYnq.getExplanation();
				if (ANSWER_INDICATOR_VALUE.equals(answer)) {
					questionAnswered = true;
				}
				setProjectSurvey(projectSurvey, questionAnswered, questionId,
						explanation);
			}
		} else {
			setDefaultValuesToProjectSurvey(projectSurvey);
		}
		if (getProposalYNQ(LOBBYING_ACTIVITIES) != null) {
			projectSurvey.setH4Question(true);
		}
		projectSurvey.setCBQuestion(false);
		projectSurvey.setCBText(DEFAULT_ANSWER);
		projectSurvey.setEnvExemptionQuestion(false);
		projectSurvey.setEnvExemptionCBText(DEFAULT_ANSWER);
		projectSurvey.setEnvImpactQuestion(false);
		projectSurvey.setEnvImpactText(DEFAULT_ANSWER);
		return projectSurvey;
	}

	/*
	 * This method set the default values to project survey attributes in case
	 * of proposal ynq's are not found.
	 */
	private void setDefaultValuesToProjectSurvey(ProjectSurvey projectSurvey) {
		projectSurvey.setG3Question(false);
		projectSurvey.setG3Text(DEFAULT_ANSWER);
		projectSurvey.setG4Question(false);
		projectSurvey.setG4Text(DEFAULT_ANSWER);
		projectSurvey.setG6Question(false);
		projectSurvey.setG6Text(DEFAULT_ANSWER);
		projectSurvey.setG8Question(false);
		projectSurvey.setG8Text(DEFAULT_ANSWER);
		projectSurvey.setH1Question(false);
		projectSurvey.setH1Text(DEFAULT_ANSWER);
	}

	/*
	 * This method set the values to project survey attributes in case of
	 * proposal ynq's are found.
	 */
	private void setProjectSurvey(ProjectSurvey projectSurvey,
			boolean questionAnswered, String questionId, String explanation) {
		if (questionId.equals(HAZARDOUS_MATERIALS)) {
			projectSurvey.setG3Question(questionAnswered);
			projectSurvey.setG3Text(explanation);
		} else if (questionId.equals(GENETICALLY_ENGINEERED)) {
			projectSurvey.setG4Question(questionAnswered);
			projectSurvey.setG4Text(explanation);
		} else if (questionId.equals(HISTORICAL_SITES)) {
			projectSurvey.setG6Question(questionAnswered);
			projectSurvey.setG6Text(explanation);
		} else if (questionId.equals(PROPRIETARY_INFO)) {
			projectSurvey.setG8Question(questionAnswered);
			projectSurvey.setG8Text(explanation);
		} else if (questionId.equals(INTERNATIONAL_ACTIVITIES)) {
			projectSurvey.setH1Question(questionAnswered);
			projectSurvey.setH1Text(explanation);
		} else if (questionId.equals(LOBBYING_ACTIVITIES)) {
			projectSurvey.setH4Question(questionAnswered);
		} else if (questionId.equals(NSFSMALL_GRANT)) {
			projectSurvey.setSmallGrantQuestion(questionAnswered);
		} else if (questionId.equals(NSF_BEGINNING_INV)) {
			projectSurvey.setNSFbeginningInvestQuestion(questionAnswered);
		} else if (questionId.equals(STEMCELL)) {
			projectSurvey.setStemCellQuestion(questionAnswered);
			projectSurvey.setStemCellText(explanation);
		}
	}

	/*
	 * This method will get the list of Organization YNQ for given question id.
	 */
	private List<OrganizationYnq> getOrganizationYNQ(String questionId) {
		OrganizationYnq organizationYnq = null;
		Map<String, String> organizationYnqMap = new HashMap<String, String>();
		organizationYnqMap.put(ORGANIZATION_ID_PARAMETER, questionId);
		List<OrganizationYnq> organizationYnqs = (List<OrganizationYnq>) businessObjectService
				.findMatching(OrganizationYnq.class, organizationYnqMap);
		return organizationYnqs;
	}

	/*
	 * This method will get the Proposal YNQ for given question id and question
	 * must be answered.
	 */
	private ProposalYnq getProposalYNQ(String questionId) {
		ProposalYnq proposalYnq = null;
		Map<String, String> proposalYnqMap = new HashMap<String, String>();
		proposalYnqMap.put(QUESTION_ID_PARAMETER, questionId);
		proposalYnqMap.put(ANSWER_PARAMETER, ANSWER_INDICATOR_VALUE);
		List<ProposalYnq> proposalYnqs = (List<ProposalYnq>) businessObjectService
				.findMatching(ProposalYnq.class, proposalYnqMap);
		if (proposalYnqs != null && !proposalYnqs.isEmpty()) {
			proposalYnq = proposalYnqs.get(0);
		}
		return proposalYnq;
	}

	/*
	 * This method will set the values to animal subject attributes like
	 * AssuranceNumber,VertebrateAnimalsUsedQuestion,IACUCApprovalDate and
	 * IACUCApprovalPending.
	 */
	private AnimalSubject getAnimalSubject(
			DevelopmentProposal developmentProposal) {
		AnimalSubject animalSubject = AnimalSubject.Factory.newInstance();
		List<ProposalSpecialReview> specialReviewList = developmentProposal
				.getPropSpecialReviews();
		if (specialReviewList != null) {
			for (ProposalSpecialReview proposalSpecialReview : specialReviewList) {
				if (proposalSpecialReview.getSpecialReviewCode() != null
						&& proposalSpecialReview.getSpecialReviewCode().equals(
								SPECIAL_REVIEW_CODE_2)) {
					animalSubject.setVertebrateAnimalsUsedQuestion(true);
					String animalWelfareAssurance = getAnimalWelfareAssuranceNumber(developmentProposal);
					if (animalWelfareAssurance != null) {
						animalSubject
								.setAssuranceNumber(animalWelfareAssurance);
					}
					if (proposalSpecialReview.getApplicationDate() != null) {
						animalSubject.setIACUCApprovalDate(dateTimeService
								.getCalendar(proposalSpecialReview
										.getApplicationDate()));
					} else {
						animalSubject
								.setIACUCApprovalPending(IACU_APPROVAL_PENDING_VALUE);
					}
					break;
				}
			}
		}
		return animalSubject;
	}

	/*
	 * This method will set the the values to human subject like IRB approval
	 * date ,exemption number,human assurance number.
	 */
	private HumanSubjectsType getHumanSubjectsType(
			DevelopmentProposal developmentProposal) {
		HumanSubjectsType humanSubjectsType = HumanSubjectsType.Factory
				.newInstance();
		// exemptionNumber maximum it occurs 6 times no string array size
		// declared as 6
		String exemptionNumber[] = new String[6];
		List<ProposalSpecialReview> specialReviewList = developmentProposal
				.getPropSpecialReviews();
		int arrayIndex = 0;
		if (specialReviewList != null) {
			for (ProposalSpecialReview proposalSpecialReview : specialReviewList) {
				boolean humanSubjectsUsedQuestion = getHumanSubjectsUsedQuestion(proposalSpecialReview);
				humanSubjectsType
						.setHumanSubjectsUsedQuestion(humanSubjectsUsedQuestion);
				if (!proposalSpecialReview.getApprovalTypeCode().equals(
						APPROVAL_TYPE_EXEMPT)
						&& proposalSpecialReview.getApprovalDate() != null) {
					humanSubjectsType.setIRBApprovalDate(dateTimeService
							.getCalendar(proposalSpecialReview
									.getApprovalDate()));
					break;
				} else {
					String comments = getSpecialReviewComments(proposalSpecialReview);
					exemptionNumber[arrayIndex] = comments;
					arrayIndex++;
				}
			}
		}
		setEXemptionNumber(developmentProposal, humanSubjectsType,
				exemptionNumber);
		return humanSubjectsType;
	}

	/*
	 * This method will return true if special review code 1 found in proposal
	 * special review otherwise false.
	 */
	private boolean getHumanSubjectsUsedQuestion(
			ProposalSpecialReview proposalSpecialReview) {
		boolean humanSubjectsUsedQuestion = false;
		if (proposalSpecialReview.getSpecialReviewCode() != null
				&& proposalSpecialReview.getSpecialReviewCode().equals(
						SPECIAL_REVIEW_CODE_1)) {
			humanSubjectsUsedQuestion = true;
		}
		return humanSubjectsUsedQuestion;
	}

	/*
	 * This method will set the value to ExemptionNumberArray element in
	 * HumanSubjectsType type if HumanSubjectsUsedQuestion is not true ,if it is
	 * true then set value to AssuranceNumber.
	 */
	private void setEXemptionNumber(DevelopmentProposal developmentProposal,
			HumanSubjectsType humanSubjectsType, String[] exemptionNumber) {
		if (humanSubjectsType.getHumanSubjectsUsedQuestion() == true) {
			String humanSubAssurance = getHumanAssuranceNumber(developmentProposal);
			if (humanSubAssurance != null) {
				humanSubjectsType.setAssuranceNumber(humanSubAssurance);
			}
		} else {
			humanSubjectsType.setExemptionNumberArray(exemptionNumber);
		}
	}

	/*
	 * This method will get the special review comments if comments found else
	 * default values 'Unknown' will be returned.
	 */
	private String getSpecialReviewComments(
			ProposalSpecialReview proposalSpecialReview) {
		String comments = null;
		if (proposalSpecialReview.getComments() == null) {
			comments = DEFAULT_VALUE_UNKNOWN;
		} else {
			comments = proposalSpecialReview.getComments();
		}
		return comments;
	}

	/*
	 * This method will get the human assurance number from the organization
	 */
	private String getHumanAssuranceNumber(
			DevelopmentProposal developmentProposal) {
		String humanSubAssurance = null;
		Organization organization = getOrganizationFromDevelopmentProposal(developmentProposal);
		if (organization != null && organization.getHumanSubAssurance() != null) {
			humanSubAssurance = organization.getHumanSubAssurance();
		}
		return humanSubAssurance;
	}

	/*
	 * This method will get the animal welfare assurance number from the
	 * organization
	 */
	private String getAnimalWelfareAssuranceNumber(
			DevelopmentProposal developmentProposal) {
		String animalWelfareAssurance = null;
		Organization organization = getOrganizationFromDevelopmentProposal(developmentProposal);
		if (organization != null
				&& organization.getAnimalWelfareAssurance() != null) {
			animalWelfareAssurance = organization.getAnimalWelfareAssurance();
		}
		return animalWelfareAssurance;
	}

	/*
	 * This method will get the Organization from the Development proposal.
	 */
	private Organization getOrganizationFromDevelopmentProposal(
			DevelopmentProposal developmentProposal) {
		Organization organization = null;
		ProposalSite proposalSite = developmentProposal
				.getApplicantOrganization();
		if (proposalSite != null) {
			organization = proposalSite.getOrganization();
		}
		return organization;
	}
}
