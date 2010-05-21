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
package org.kuali.kra.proposaldevelopment.printing.xmlstream;

import gov.nih.era.projectmgmt.sbir.cgap.commonNamespace.AssuranceType;
import gov.nih.era.projectmgmt.sbir.cgap.commonNamespace.ContactInfoType;
import gov.nih.era.projectmgmt.sbir.cgap.commonNamespace.PostalAddressType;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.AbstractType;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.ApplicantOrganizationType;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.AuthorizedOrganizationalRepresentativeType;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.BudgetSummaryType;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.FundingOpportunityDetailsType;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.HumanSubjectsType;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.KeyPersonType;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.OrgAssurancesType;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.ResearchAndRelatedProjectDocument;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.SalariesAndWagesType;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.ProgramDirectorPrincipalInvestigatorDocument.ProgramDirectorPrincipalInvestigator;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.ProjectDescriptionDocument.ProjectDescription;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.ResearchAndRelatedProjectDocument.ResearchAndRelatedProject;
import gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.ResearchCoverPageDocument.ResearchCoverPage;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.DescriptionBlockType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.PersonFullNameType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ProjectRoleType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ProposalPersonType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.KeyPersonType.KeyPersonFlag;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.OrganizationType;
import org.kuali.kra.bo.OrganizationYnq;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.personnel.BudgetPerson;
import org.kuali.kra.budget.personnel.BudgetPersonnelDetails;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.printing.util.PrintingUtils;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalAbstract;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.proposaldevelopment.bo.ProposalSite;
import org.kuali.kra.proposaldevelopment.bo.ProposalSpecialReview;
import org.kuali.kra.proposaldevelopment.bo.ProposalYnq;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.kns.service.ParameterService;

/**
 * This class generates XML that confirms with the RaR XSD related to Proposal
 * Submission Report or Sponsor Report. The data for XML is derived from
 * {@link ResearchDocumentBase} and {@link Map} of details passed to the class.
 * 
 * 
 */
public class NIHResearchAndRelatedXmlStream extends
		AbstractResearchAndRelatedStream {

	private static final Logger LOG = Logger
			.getLogger(NIHResearchAndRelatedXmlStream.class);

	private static final String ORGANIZATION_QUESTION_ID_H5 = "H5";
	private static final String ORGANIZATION_QUESTION_ID_I8 = "I8";
	private static final String DEFAULT_VALUE_KEY_PERSON_FLAG_CODE = "true";
	private static final String KEY_PERSON_FLAG_DESCRIPTION_VALUE_COLLABORATOR = "Collaborator/Other";
	private static final String KEY_PERSON_FLAG_DESCRIPTION_VALUE_KEY_PERSON = "Key Person";
	private static final String KEY_PERSON_FLAG_CODE_VALUE_FALSE = "false";
	private static final String GENERAL_CERTIFICATION_QUESTION_ID = "H6";
	private static final String LOBBYING_QUESTION_ID = "H0";
	private static final String REFERENCES_BLOCK_TYPE = "references";
	private static final String EQUIPMENT_BLOCK_TYPE = "equipment";
	private static final String FACILITIES_BLOCK_TYPE = "facilities";
	private static final String PROJECT_SUMMARY_BLOCK_TYPE = "summary";
	private static final String SPECIAL_REVIEW_CODE_1 = "1";

	private static final String APPROVAL_TYPE_EXEMPT = "4";
	protected static final String PROPOSAL_YNQ_QUESTION_16 = "16";

	private static final String ACADEMIC_PERIOD = "2";
	private static final String CALENDAR_PERIOD = "3";

	private static final String PROJECT_ROLE_PI = "PI";
	private static final String PROJECT_ROLE_COI = "COI";
	private static final String PROJECT_ROLE_KP = "KP";

	protected ParameterService parameterService;

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
				.setAbstractArray(getAbstractArray(developmentProposal));

		researchAndRelatedProject
				.setProposalPersonArray(getProposalPersonArray(
						developmentProposal, budget));
		researchAndRelatedProject
				.setKeyPersonArray(getKeyPersonArray(developmentProposal));

		researchAndRelatedProject
				.setNSFPreviousAwardNumber(getNSFPreviousAwardNumber(proposalDevelopmentDocument));
		researchAndRelatedProject
				.setNSFProjectDuration(getNSFProjectDuration(developmentProposal));
		researchAndRelatedProject
				.setNihInventions(getNihInventions(developmentProposal));

		Award award = getAward(developmentProposal.getCurrentAwardNumber());
		if (award != null) {
			Calendar totalProjectStartDate = Calendar.getInstance();
			totalProjectStartDate.setTime(award.getAwardEffectiveDate());
			researchAndRelatedProject
					.setTotalProjectStartDt(totalProjectStartDate);

			AwardAmountInfo amountInfo = getMaxAwardAmountInfo(award);
			Calendar totalProjectEndDate = Calendar.getInstance();
			totalProjectEndDate.setTime(amountInfo.getFinalExpirationDate());
			researchAndRelatedProject.setTotalProjectEndDt(totalProjectEndDate);
		}
		setNIHDeatils(researchAndRelatedProject, developmentProposal);
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

	private void setNIHDeatils(
			ResearchAndRelatedProject researchAndRelatedProject,
			DevelopmentProposal developmentProposal) {
		String proposalTypeCode = developmentProposal.getProposalTypeCode();
		if ("6".equals(proposalTypeCode)) {
			InstitutionalProposal institutionalProposal = getMaxInstitutionalProposal(developmentProposal);
			researchAndRelatedProject
					.setNihPriorGrantNumber(institutionalProposal
							.getSponsorProposalNumber()); // setNihPriorGrantNumber
		} else if ("3".equals(proposalTypeCode) || "4".equals(proposalTypeCode)
				|| "5".equals(proposalTypeCode)) {
			String federalIdComesFromAwardStr = parameterService
					.getParameterValue(ProposalDevelopmentDocument.class,
							"FEDERAL_ID_COMES_FROM_CURRENT_AWARD");
			if ("1".equals(federalIdComesFromAwardStr)) {
				String currentAwardNumber = developmentProposal
						.getCurrentAwardNumber();
				Award award = getAward(currentAwardNumber);
				String sponsorAwardNumber = award.getSponsorAwardNumber(); // setNihPriorGrantNumber
				String activityCode = sponsorAwardNumber.substring(3, 3); // setNihActivityCode
				String awardType = sponsorAwardNumber.substring(1, 1); // setNihApplicationTypeCode
				researchAndRelatedProject.setNihActivityCode(activityCode);
				researchAndRelatedProject
						.setNihPriorGrantNumber(sponsorAwardNumber);
				researchAndRelatedProject.setNihApplicationTypeCode(awardType);
			}
		}
	}

	/**
	 * @param developmentProposal
	 * @return
	 */
	private InstitutionalProposal getMaxInstitutionalProposal(
			DevelopmentProposal developmentProposal) {
		String continuedFrom = developmentProposal.getContinuedFrom();
		Map<String, Object> fieldValues = new HashMap<String, Object>();
		fieldValues.put("proposalId", continuedFrom);
		List<InstitutionalProposal> institutionalProposals = (List<InstitutionalProposal>) businessObjectService
				.findMatching(InstitutionalProposal.class, fieldValues);
		InstitutionalProposal institutionalProposal = null;
		Integer maxSequenceNumber = 0;
		for (InstitutionalProposal proposal : institutionalProposals) {
			if (maxSequenceNumber < proposal.getSequenceNumber()) {
				maxSequenceNumber = proposal.getSequenceNumber();
				institutionalProposal = proposal;
			}
		}
		return institutionalProposal;
	}

	private String getNihInventions(DevelopmentProposal developmentProposal) {
		String nihInventions = null;
		for (ProposalYnq proposalYnq : developmentProposal.getProposalYnqs()) {
			if (proposalYnq.getQuestionId() != null
					&& proposalYnq.getQuestionId().equals(
							PROPOSAL_YNQ_QUESTION_16)) {
				nihInventions = proposalYnq.getAnswer();
			}
		}
		return nihInventions;
	}

	private BigInteger getNSFProjectDuration(
			DevelopmentProposal developmentProposal) {

		BigInteger projectDuration = null;
		if (developmentProposal.getRequestedEndDateInitial() != null
				&& developmentProposal.getRequestedStartDateInitial() != null) {
			BigDecimal months = getMonthsBetweenDates(developmentProposal
					.getRequestedStartDateInitial(), developmentProposal
					.getRequestedEndDateInitial());
			projectDuration = months.toBigInteger();
		}
		return projectDuration;
	}

	/**
	 * @param Start
	 *            Date
	 * @param End
	 *            Date
	 * @return Difference of the Months of the Dates.
	 */
	private BigDecimal getMonthsBetweenDates(Date startDate, Date endDate) {
		BigDecimal projectDuration;
		com.ibm.icu.util.Calendar calendar = com.ibm.icu.util.Calendar
				.getInstance();
		calendar.setTimeInMillis(startDate.getTime());
		int monthDifference = calendar.fieldDifference(endDate, Calendar.MONTH);
		projectDuration = new BigDecimal(monthDifference);
		return projectDuration;
	}

	private String getNSFPreviousAwardNumber(
			ProposalDevelopmentDocument proposalDevelopmentDocument) {
		String currentAwardNumber = proposalDevelopmentDocument
				.getDevelopmentProposal().getCurrentAwardNumber();
		Award award = getAward(currentAwardNumber);
		if (award != null) {
			return award.getSponsorAwardNumber();
		} else {
			return null;
		}
	}

	private AbstractType[] getAbstractArray(
			DevelopmentProposal developmentProposal) {
		List<AbstractType> abstractTypeList = new ArrayList<AbstractType>();
		AbstractType abstractType = null;
		BigInteger abstractTypeCode = null;
		for (ProposalAbstract proposalAbstract : developmentProposal
				.getProposalAbstracts()) {
			if (proposalAbstract.getAbstractType() != null) {
				try {
					abstractType = AbstractType.Factory.newInstance();
					abstractType.setAbstractText(proposalAbstract
							.getAbstractDetails());
					abstractTypeCode = new BigInteger(proposalAbstract
							.getAbstractType().getAbstractTypeCode());
					abstractType.setAbstractTypeCode(abstractTypeCode);
					abstractType.setAbstractTypeDesc(proposalAbstract
							.getAbstractType().getDescription());
					abstractTypeList.add(abstractType);
				} catch (NumberFormatException e) {
					LOG.info("abstractTypeCode is Not a Number for proposal :"
							+ developmentProposal.getProposalNumber());
				}
			}
		}
		return abstractTypeList.toArray(new AbstractType[0]);
	}

	/*
	 * This method will set the values to Proposal person type attributes and
	 * finally return ProposalPersonType xml object.
	 */
	private ProposalPersonType[] getProposalPersonArray(
			DevelopmentProposal developmentProposal, Budget budget) {
		List<ProposalPersonType> proposalPersonTypeList = new ArrayList<ProposalPersonType>();
		List<ProposalPerson> proposalPersons = developmentProposal
				.getProposalPersons();
		for (ProposalPerson proposalPerson : proposalPersons) {
			proposalPersonTypeList.add(getProposalPersonTypeWithValues(
					developmentProposal, proposalPerson, budget));
		}
		return proposalPersonTypeList.toArray(new ProposalPersonType[0]);
	}

	private ProposalPersonType getProposalPersonTypeWithValues(
			DevelopmentProposal developmentProposal,
			ProposalPerson proposalPerson, Budget budget) {
		ProposalPersonType proposalPersonType = ProposalPersonType.Factory
				.newInstance();
		proposalPersonType.setDegree(proposalPerson.getDegree());
		proposalPersonType.setEmail(proposalPerson.getEmailAddress());
		proposalPersonType
				.setName(getProposalPersonFullNameType(proposalPerson));
		proposalPersonType.setPhone(proposalPerson.getPhoneNumber());
		if(PROJECT_ROLE_PI.equals(proposalPerson.getProposalPersonRoleId())){
			proposalPersonType.setProjectRole(ProjectRoleType.PI_PD);
		}else if(PROJECT_ROLE_COI.equals(proposalPerson.getProposalPersonRoleId())){
			proposalPersonType.setProjectRole(ProjectRoleType.CO_PI_PD);
		}else{
			proposalPersonType.setProjectRole(ProjectRoleType.KEY_PERSON);
		}
		 
		proposalPersonType.setSSN(proposalPerson.getSocialSecurityNumber());
		if (proposalPerson.getDateOfBirth() != null) {
			proposalPersonType.setDOB(proposalPerson.getDateOfBirth()
					.toString());
		}

		BudgetDecimal calendarMonths = BudgetDecimal.ZERO;
		BudgetDecimal academicMonths = BudgetDecimal.ZERO;
		BudgetDecimal summerMonths = BudgetDecimal.ZERO;
		budget.refreshNonUpdateableReferences();
		for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
			if (budgetPeriod.getBudgetPeriod().intValue() == 1) {
				for (BudgetLineItem lineItem : budgetPeriod
						.getBudgetLineItems()) {
					for (BudgetPersonnelDetails budgetPersonnelDetails : lineItem
							.getBudgetPersonnelDetailsList()) {
						if ((proposalPerson.getPersonId() != null && proposalPerson
								.getPersonId().equals(
										budgetPersonnelDetails.getPersonId()))
								|| (proposalPerson.getRolodexId() != null && proposalPerson
										.getRolodexId().toString().equals(
												budgetPersonnelDetails
														.getPersonId()))) {
							proposalPersonType
									.setPercentEffort(budgetPersonnelDetails
											.getPercentEffort()
											.bigDecimalValue());
							proposalPersonType
									.setRequestedCost(budgetPersonnelDetails
											.getSalaryRequested()
											.bigDecimalValue());
							if (ACADEMIC_PERIOD.equals(budgetPersonnelDetails
									.getPeriodTypeCode())) {
								academicMonths = academicMonths
										.add(budgetPersonnelDetails
												.getPercentEffort()
												.multiply(
														new BudgetDecimal(
																"0.01"))
												.multiply(
														getNumberOfMonths(
																budgetPersonnelDetails
																		.getStartDate(),
																budgetPersonnelDetails
																		.getEndDate())));
							} else if (CALENDAR_PERIOD
									.equals(budgetPersonnelDetails
											.getPeriodTypeCode())) {
								calendarMonths = calendarMonths
										.add(budgetPersonnelDetails
												.getPercentEffort()
												.multiply(
														new BudgetDecimal(
																"0.01"))
												.multiply(
														getNumberOfMonths(
																budgetPersonnelDetails
																		.getStartDate(),
																budgetPersonnelDetails
																		.getEndDate())));
							} else
								summerMonths = summerMonths
										.add(budgetPersonnelDetails
												.getPercentEffort()
												.multiply(
														new BudgetDecimal(
																"0.01"))
												.multiply(
														getNumberOfMonths(
																budgetPersonnelDetails
																		.getStartDate(),
																budgetPersonnelDetails
																		.getEndDate())));
						}
					}
				}
			}
		}
		proposalPersonType.setAcademicFundingMonths(academicMonths
				.bigDecimalValue().setScale(2));
		proposalPersonType.setSummerFundingMonths(summerMonths
				.bigDecimalValue().setScale(2));
		proposalPersonType.setFundingMonths(calendarMonths.bigDecimalValue().setScale(2));
		return proposalPersonType;
	}

	/**
	 * 
	 * This method computes the number of months between any 2 given
	 * {@link Date} objects
	 * 
	 * @param dateStart
	 *            starting date.
	 * @param dateEnd
	 *            end date.
	 * 
	 * @return number of months between the start date and end date.
	 */
	private BudgetDecimal getNumberOfMonths(Date dateStart, Date dateEnd) {
		BudgetDecimal monthCount = BudgetDecimal.ZERO;
		int fullMonthCount = 0;

		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		startDate.setTime(dateStart);
		endDate.setTime(dateEnd);

		startDate.clear(Calendar.HOUR);
		startDate.clear(Calendar.MINUTE);
		startDate.clear(Calendar.SECOND);
		startDate.clear(Calendar.MILLISECOND);

		endDate.clear(Calendar.HOUR);
		endDate.clear(Calendar.MINUTE);
		endDate.clear(Calendar.SECOND);
		endDate.clear(Calendar.MILLISECOND);

		if (startDate.after(endDate)) {
			return BudgetDecimal.ZERO;
		}
		int startMonthDays = startDate.getActualMaximum(Calendar.DATE)
				- startDate.get(Calendar.DATE);
		startMonthDays++;
		int startMonthMaxDays = startDate.getActualMaximum(Calendar.DATE);
		BudgetDecimal startMonthFraction = new BudgetDecimal(startMonthDays)
				.divide(new BudgetDecimal(startMonthMaxDays));

		int endMonthDays = endDate.get(Calendar.DATE);
		int endMonthMaxDays = endDate.getActualMaximum(Calendar.DATE);

		BudgetDecimal endMonthFraction = new BudgetDecimal(endMonthDays)
				.divide(new BudgetDecimal(endMonthMaxDays));

		startDate.set(Calendar.DATE, 1);
		endDate.set(Calendar.DATE, 1);

		while (startDate.getTimeInMillis() < endDate.getTimeInMillis()) {
			startDate.set(Calendar.MONTH, startDate.get(Calendar.MONTH) + 1);
			fullMonthCount++;
		}
		fullMonthCount = fullMonthCount - 1;
		monthCount = monthCount.add(new BudgetDecimal(fullMonthCount)).add(
				startMonthFraction).add(endMonthFraction);
		return monthCount;
	}

	private PersonFullNameType getProposalPersonFullNameType(
			ProposalPerson proposalPerson) {
		PersonFullNameType personFullNameType = PersonFullNameType.Factory
				.newInstance();
		personFullNameType.setFirstName(proposalPerson.getFirstName());
		personFullNameType.setLastName(proposalPerson.getLastName());
		personFullNameType.setMiddleName(proposalPerson.getMiddleName());
		return personFullNameType;
	}

	/*
	 * This method will set the values to key person type attributes and finally
	 * return KeyPersonType xml object.
	 */
	// TODO Implement Key person
	private KeyPersonType[] getKeyPersonArray(
			DevelopmentProposal developmentProposal) {
		List<ProposalPerson> propKeyPersons = new ArrayList<ProposalPerson>();
		List<ProposalPerson> propInvestigators = new ArrayList<ProposalPerson>();
		List<ProposalPerson> propPIs = new ArrayList<ProposalPerson>();
		for (ProposalPerson proposalPerson : developmentProposal
				.getProposalPersons()) {
			if (ContactRole.PI_CODE.equals(proposalPerson
					.getProposalPersonRoleId())) {
				propPIs.add(proposalPerson);
			} else if (ContactRole.KEY_PERSON_CODE.equals(proposalPerson
					.getProposalPersonRoleId())) {
				propKeyPersons.add(proposalPerson);
			} else {
				propInvestigators.add(proposalPerson);
			}
		}
		sortKeyPersonWithName(propPIs);
		sortKeyPersonWithName(propKeyPersons);
		sortKeyPersonWithName(propInvestigators);
		List<KeyPersonType> keyPersonPIList = getKeyPersonForPropInvestigator(
				developmentProposal, propPIs);
		List<KeyPersonType> keyPersonInvestigatorList = getKeyPersonForPropInvestigator(
				developmentProposal, propInvestigators);
		List<KeyPersonType> keyPersonKeyPersonList = getKeyPersonForPropKeyPerson(
				developmentProposal, propKeyPersons);
		// TODO The following method will itself sort Sort the list
		List<KeyPersonType> allKeyPersonList = new ArrayList<KeyPersonType>();
		// Add PI, Investigator, KeyPerson in the same order
		allKeyPersonList.addAll(keyPersonPIList);
		allKeyPersonList.addAll(keyPersonInvestigatorList);
		allKeyPersonList.addAll(keyPersonKeyPersonList);
		return allKeyPersonList.toArray(new KeyPersonType[0]);
	}

	private List<KeyPersonType> getKeyPersonForPropKeyPerson(
			DevelopmentProposal developmentProposal,
			List<ProposalPerson> proposalKeyPersons) {
		List<KeyPersonType> keyPersonlist = new ArrayList<KeyPersonType>();
		for (ProposalPerson proposalPerson : proposalKeyPersons) {
			KeyPersonType keyPersonType = KeyPersonType.Factory.newInstance();
			PersonFullNameType personFullNameType = getPersonFullName(proposalPerson);
			keyPersonType.setName(personFullNameType);
			ContactInfoType contactInfoType = getContactInfoType(proposalPerson);
			keyPersonType.setContactInformation(contactInfoType);
			// TODO :AuthenticationCredential Not found
			// keyPersonType.setAuthenticationCredential();
			// TODO :BiographicalSketch Not found
			// keyPersonType.setBiographicalSketch(proposalPerson.getB);
			KeyPersonFlag keyPersonFlag = getKeyPersonFlag(proposalPerson);
			keyPersonType.setKeyPersonFlag(keyPersonFlag);
			keyPersonType.setSocialSecurityNumber(proposalPerson
					.getSocialSecurityNumber());
			String unitName = getUnitName(proposalPerson);
			if (unitName != null) {
				keyPersonType.setOrganizationDepartment(unitName);
			}
			Organization organization = getOrganizationFromDevelopmentProposal(developmentProposal);
			keyPersonType.setOrganizationName(organization
					.getOrganizationName());
			// TODO :OrganizationDivision Not found
			// keyPersonType.setOrganizationDivision();
			if (proposalPerson.getPrimaryTitle() != null) {
				keyPersonType
						.setPositionTitle(proposalPerson.getPrimaryTitle());
			}
			// keyPersonType.addDegree(proposalPerson.getDegree());
			keyPersonlist.add(keyPersonType);
		}
		return keyPersonlist;
	}

	/*
	 * This method will set the values to key person type attributes like
	 * organization department, organization name , position title etc ... if
	 * key person found.
	 */
	private List<KeyPersonType> getKeyPersonForPropInvestigator(
			DevelopmentProposal developmentProposal,
			List<ProposalPerson> proposalPersonList) {
		List<KeyPersonType> keyPersonTypeList = new ArrayList<KeyPersonType>();
		for (ProposalPerson proposalPerson : proposalPersonList) {
			KeyPersonType keyPersonType = KeyPersonType.Factory.newInstance();
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
			keyPersonType.setOrganizationName(organization
					.getOrganizationName());
			// TODO :OrganizationDivision Not found
			// keyPersonType.setOrganizationDivision();
			if (proposalPerson.getPrimaryTitle() != null) {
				keyPersonType
						.setPositionTitle(proposalPerson.getPrimaryTitle());
			}
			// keyPersonType.addDegree(proposalPerson.getDegree());
			keyPersonTypeList.add(keyPersonType);
		}
		return keyPersonTypeList;
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
		}
		return budgetSummaryType;
	}

	/*
	 * This method gets arrays of BudgetPeriodType XMLObjects by setting each
	 * BudgetPeriodType data from budgetPeriod data
	 */
	private gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.BudgetSummaryType.BudgetPeriod[] getBudgetPeriodArray(
			List<BudgetPeriod> budgetPeriodList) {
		List<gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.BudgetSummaryType.BudgetPeriod> budgetPeriods = new ArrayList<gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.BudgetSummaryType.BudgetPeriod>();
		for (BudgetPeriod budgetPeriod : budgetPeriodList) {
			if (budgetPeriod.getBudgetPeriod() != null) {
				List<BudgetLineItem> budgetLineItems = budgetPeriod
						.getBudgetLineItems();
				gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.BudgetSummaryType.BudgetPeriod budgetPeriodType = gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.BudgetSummaryType.BudgetPeriod.Factory
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
				budgetPeriods.add(budgetPeriodType);
			}
		}
		return budgetPeriods
				.toArray(new gov.nih.era.projectmgmt.sbir.cgap.nihspecificNamespace.BudgetSummaryType.BudgetPeriod[0]);
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

	// /*
	// * This method will set the values to key person type attributes like
	// * organization department, organization name , position title etc ... if
	// * key person found.
	// */
	// private KeyPersonType getKeyPersonTypeWithValues(
	// DevelopmentProposal developmentProposal,
	// ProposalPerson proposalPerson) {
	// KeyPersonType keyPersonType = KeyPersonType.Factory.newInstance();
	// PersonFullNameType personFullNameType =
	// getPersonFullName(proposalPerson);
	// keyPersonType.setName(personFullNameType);
	// ContactInfoType contactInfoType = getContactInfoType(proposalPerson);
	// keyPersonType.setContactInformation(contactInfoType);
	// // TODO :AuthenticationCredential Not found
	// // keyPersonType.setAuthenticationCredential();
	// // TODO :BiographicalSketch Not found
	// // keyPersonType.setBiographicalSketch();
	// KeyPersonFlag keyPersonFlag = getKeyPersonFlag(proposalPerson);
	// keyPersonType.setKeyPersonFlag(keyPersonFlag);
	// keyPersonType.setSocialSecurityNumber(proposalPerson
	// .getSocialSecurityNumber());
	// String unitName = getUnitName(proposalPerson);
	// if (unitName != null) {
	// keyPersonType.setOrganizationDepartment(unitName);
	// }
	// Organization organization =
	// getOrganizationFromDevelopmentProposal(developmentProposal);
	// keyPersonType.setOrganizationName(organization.getOrganizationName());
	// // TODO :OrganizationDivision Not found
	// // keyPersonType.setOrganizationDivision();
	// if (proposalPerson.getPrimaryTitle() != null) {
	// keyPersonType.setPositionTitle(proposalPerson.getPrimaryTitle());
	// }
	// return keyPersonType;
	// }

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

	private Double getPersonCalendarMonths(ProposalPerson person, Budget budget) {
		// DECODE
		// (FN_GET_FUNDING_MONTHS(AW_PROPOSAL_NUMBER,'CC',1,PP.PERSON_ID),NULL,'0',
		// FN_GET_FUNDING_MONTHS(AW_PROPOSAL_NUMBER,'CC',1,PP.PERSON_ID) ) +
		// DECODE
		// (FN_GET_FUNDING_MONTHS(AW_PROPOSAL_NUMBER,'CY',1,PP.PERSON_ID),NULL,'0',
		// FN_GET_FUNDING_MONTHS(AW_PROPOSAL_NUMBER,'CY',1,PP.PERSON_ID) )
		// CALENDAR_MONTHS,

		BudgetDecimal ccMonths = getFundingMonths(person, budget, "CC");
		BudgetDecimal cyMonths = getFundingMonths(person, budget, "CY");
		BudgetDecimal personCalendarMonths = ccMonths.add(cyMonths);
		personCalendarMonths.setScale();
		return personCalendarMonths.doubleValue();
	}

	private BudgetDecimal getPersonAcademicMonths(ProposalPerson person,
			Budget budget) {
		return getFundingMonths(person, budget, "AP");
	}

	private BudgetDecimal getPersonSummerMonths(ProposalPerson person,
			Budget budget) {
		return getFundingMonths(person, budget, "SP");
	}

	// budgetPersonnelDetails.getPersonId();
	// budget.getBudgetPersonnelDetailsList();
	// // Find the budget person for proposal person
	// BudgetPerson budgetPerson = null;
	// budgetPerson.
	// sum( PD.percent_effort * round(months_between(PD.end_date,PD.start_date))
	// / 100 ) ;
	private BudgetDecimal getFundingMonths(ProposalPerson person,
			Budget budget, String appointmentTypeCode) {
		BudgetDecimal fundingMonths = null;
		BudgetPersonnelDetails budgetPersonnelDetails = getBudgetPersonnelDetailsForPropPerson(
				budget.getBudgetPersonnelDetailsList(), person,
				appointmentTypeCode);
		if (budgetPersonnelDetails != null) {
			BigDecimal totalMonths = getMonthsBetweenDates(
					budgetPersonnelDetails.getStartDate(),
					budgetPersonnelDetails.getEndDate());
			fundingMonths = budgetPersonnelDetails.getPercentEffort().multiply(
					new BudgetDecimal(totalMonths));
			fundingMonths = fundingMonths.divide(new BudgetDecimal(100));
		}
		return fundingMonths;
	}

	// TODO How to check the appointmentTypeCode for the BudgetPersonnelDetails
	private BudgetPersonnelDetails getBudgetPersonnelDetailsForPropPerson(
			List<BudgetPersonnelDetails> budgetPersonnelDetailsList,
			ProposalPerson propPerson, String appointmentTypeCode) {
		// for each budget personnel details
		BudgetPersonnelDetails budgetPersonnelDetails = null;
		for (BudgetPersonnelDetails budgetPersonnelDet : budgetPersonnelDetailsList) {
			if (isPropsalPersonSameAsBudgetPerson(propPerson,
					budgetPersonnelDet.getBudgetPerson())
					&& appointmentTypeCode.equals(budgetPersonnelDetails
							.getBudgetPerson().getAppointmentType())) {
				budgetPersonnelDetails = budgetPersonnelDet;
				break;
			}
		}
		return budgetPersonnelDetails;
	}

	private BudgetPerson getBudgetPersonForPropPerson(
			List<BudgetPerson> budgetPersons, ProposalPerson propPerson) {
		// for each item
		// if isPropsalPersonSameAsBudgetPerson(propPersons, budgetPerson)
		// return the budget person
		BudgetPerson budgetPerson = null;
		for (BudgetPerson person : budgetPersons) {
			if (isPropsalPersonSameAsBudgetPerson(propPerson, person)) {
				budgetPerson = person;
				break;
			}
		}
		return budgetPerson;
	}

	private boolean isPropsalPersonSameAsBudgetPerson(
			ProposalPerson propPerson, BudgetPerson budgetPerson) {
		boolean propsalPersonSameAsBudgetPerson = false;
		if (propPerson != null && budgetPerson != null) {
			String propPersonId = propPerson.getPersonId();
			Integer propRolodexId = propPerson.getRolodexId();
			if (propPersonId != null) {
				propsalPersonSameAsBudgetPerson = propPersonId
						.equals(budgetPerson.getPersonId());
			} else if (propRolodexId != null) {
				propsalPersonSameAsBudgetPerson = propRolodexId
						.equals(budgetPerson.getRolodexId());
			}
		}
		return propsalPersonSameAsBudgetPerson;
	}

	public ParameterService getParameterService() {
		return parameterService;
	}

	public void setParameterService(ParameterService parameterService) {
		this.parameterService = parameterService;
	}
}