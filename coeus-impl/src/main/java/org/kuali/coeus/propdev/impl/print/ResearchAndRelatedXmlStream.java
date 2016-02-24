/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.propdev.impl.print;

import gov.nih.era.projectmgmt.sbir.cgap.commonNamespace.AssuranceType;
import gov.nih.era.projectmgmt.sbir.cgap.commonNamespace.ContactInfoType;
import gov.nih.era.projectmgmt.sbir.cgap.commonNamespace.PostalAddressType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.*;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.KeyPersonType.KeyPersonFlag;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ProgramDirectorPrincipalInvestigatorDocument.ProgramDirectorPrincipalInvestigator;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ProjectDescriptionDocument.ProjectDescription;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ResearchAndRelatedProjectDocument.ResearchAndRelatedProject;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ResearchCoverPageDocument.ResearchCoverPage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.common.framework.org.OrganizationYnq;
import org.kuali.coeus.common.framework.org.type.OrganizationType;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.print.util.PrintingUtils;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPerson;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.ProposalPersonUnit;
import org.kuali.coeus.propdev.impl.location.ProposalSite;
import org.kuali.coeus.propdev.impl.specialreview.ProposalSpecialReview;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This class generates XML that confirms with the RaR XSD related to Proposal
 * Submission Report or Sponsor Report. The data for XML is derived from
 * {@link org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase} and {@link Map} of details passed to the class.
 * 
 * 
 */
@Component("researchAndRelatedXmlStream")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ResearchAndRelatedXmlStream extends AbstractResearchAndRelatedStream {

	private static final String ORGANIZATION_QUESTION_ID_H5 = "H5";
	private static final String ORGANIZATION_QUESTION_ID_I8 = "I8";
	private static final String DEFAULT_VALUE_FOR_SSN = "XXXXXXXXX";
	private static final String DEFAULT_VALUE_FOR_KEY_PERSON_FLAG_DESCRIPTION = "PI";
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

	

	/**
	 * This method generates XML for Proposal Submission Report or Sponsor
	 * Report. It uses data passed in {@link org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase} for
	 * populating the XML nodes. The XMl once generated is returned as
	 * {@link XmlObject}
	 * 
	 * @param printableBusinessObject
	 *            using which XML is generated
	 * @param reportParameters
	 *            parameters related to XML generation
	 * @return {@link XmlObject} representing the XML
	 */
	public Map<String, XmlObject> generateXmlStream(
			KcPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
		DevelopmentProposal developmentProposal = (DevelopmentProposal) printableBusinessObject;
		Budget budget = getBudget(developmentProposal.getProposalDocument());
		ResearchAndRelatedProjectDocument researchAndRelatedProjectDocument = ResearchAndRelatedProjectDocument.Factory
				.newInstance();
		researchAndRelatedProjectDocument
				.setResearchAndRelatedProject(getResearchAndRelatedProject(developmentProposal, budget));

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
	private ResearchAndRelatedProject getResearchAndRelatedProject(DevelopmentProposal developmentProposal,
			                                                                                                    Budget budget) {
		ResearchAndRelatedProject researchAndRelatedProject = ResearchAndRelatedProject.Factory
				.newInstance();
		researchAndRelatedProject
				.setProjectDescription(getProjectDescription(developmentProposal));
		researchAndRelatedProject
				.setOrgAssurances(getOrgAssurances(developmentProposal));
		researchAndRelatedProject
				.setKeyPerson(getKeyPersonType(developmentProposal));

		researchAndRelatedProject.setResearchCoverPage(getResearchCoverPage(developmentProposal, budget));
		researchAndRelatedProject.setBudgetSummary(getBudgetSummary(developmentProposal,budget));
		return researchAndRelatedProject;
	}

	/*
	 * This method gets BudgetSummaryType XMLObject and setting data to
	 * BudgetSummaryType from budget and budgetPeriod which having final
	 * versionFlag enable.
	 */
	private BudgetSummaryType getBudgetSummary(DevelopmentProposal developmentProposal,Budget budget) {
		BudgetSummaryType budgetSummaryType = BudgetSummaryType.Factory.newInstance();
		if (budget != null) {
			BudgetPeriod budgetPeriod = budget.getBudgetPeriod(1);
			budgetSummaryType.setInitialBudgetTotals(getBudgetTotals(budgetPeriod.getTotalCost(), 
			                                                                budgetPeriod.getCostSharingAmount()));
			budgetSummaryType.setAllBudgetTotals(getBudgetTotals(budget.getTotalCost(), budget.getCostSharingAmount()));
			budgetSummaryType.setBudgetPeriodArray(getBudgetPeriodArray(developmentProposal,budget.getBudgetPeriods()));
			budgetSummaryType.setBudgetJustification(getBudgetJustification(developmentProposal.getProposalNumber()));
			budgetSummaryType.setBudgetDirectCostsTotal(budget.getTotalDirectCost().bigDecimalValue());
			budgetSummaryType.setBudgetIndirectCostsTotal(budget.getTotalIndirectCost().bigDecimalValue());
			budgetSummaryType.setBudgetCostsTotal(budget.getTotalCost().bigDecimalValue());
		}
		return budgetSummaryType;
	}

	/*
	 * This method gets arrays of BudgetPeriodType XMLObjects by setting each
	 * BudgetPeriodType data from budgetPeriod data
	 */
	private BudgetPeriodType[] getBudgetPeriodArray(DevelopmentProposal developmentProposal,List<BudgetPeriod> budgetPeriodList) {
		List<BudgetPeriodType> budgetPeriodTypeList = new ArrayList<BudgetPeriodType>();
		for (BudgetPeriod budgetPeriod : budgetPeriodList) {
			if (budgetPeriod.getBudgetPeriod() != null) {
				List<BudgetLineItem> budgetLineItems = budgetPeriod.getBudgetLineItems();
				BudgetPeriodType budgetPeriodType = BudgetPeriodType.Factory.newInstance();
				budgetPeriodType.setBudgetPeriodID(new BigInteger(String.valueOf(budgetPeriod.getBudgetPeriod())));
				budgetPeriodType.setStartDate(getDateTimeService().getCalendar(budgetPeriod.getStartDate()));
				budgetPeriodType.setEndDate(getDateTimeService().getCalendar(budgetPeriod.getEndDate()));
				budgetPeriodType.setFee(new BigDecimal(0));
				budgetPeriodType.setSalariesWagesTotal(getSalaryWagesTotal(budgetLineItems));
				budgetPeriodType.setSalariesAndWagesArray(getSalaryAndWages(developmentProposal,budgetLineItems));
				budgetPeriodType.setEquipmentTotal(getEquipmentTotal(budgetLineItems));
				budgetPeriodType.setEquipmentCostsArray(getEquipmentCosts(budgetLineItems));
				budgetPeriodType.setOtherDirectCostsArray(getOtherDirectCosts(developmentProposal,budgetLineItems));
				budgetPeriodType.setOtherDirectTotal(getOtherDirectTotal(budgetLineItems));
				budgetPeriodType.setTravelCostsArray(getTravelCosts(budgetLineItems));
				budgetPeriodType.setTravelTotal(getTravelTotal(budgetLineItems));
				budgetPeriodType.setParticipantPatientCostsArray(getParticipantPatientCost(developmentProposal,budgetLineItems));
				budgetPeriodType.setParticipantPatientTotal(getParticipantPatientTotal(budgetLineItems));
				budgetPeriodType.setPeriodDirectCostsTotal(budgetPeriod.getTotalDirectCost().bigDecimalValue());
				budgetPeriodType.setIndirectCostsTotal(budgetPeriod.getTotalIndirectCost().bigDecimalValue());
				budgetPeriodType.setPeriodCostsTotal(budgetPeriod.getTotalCost().bigDecimalValue());
				budgetPeriodType.setProgramIncome(new BigDecimal(0));
				budgetPeriodTypeList.add(budgetPeriodType);
			}
		}
		return budgetPeriodTypeList.toArray(new BudgetPeriodType[0]);
	}

	/*
	 * This method gets OtherDirectType Enum value based on budgetCategory
	 * Description if the budgetCategory description role not match with the
	 * enum type the roleType set to Other
	 */
	protected gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.OtherDirectType.Enum getOtherDirectType(
			String budgetCategoryDesc) {
		gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.OtherDirectType.Enum otherDirectType = OtherDirectType.Enum
				.forString(budgetCategoryDesc);
		if (otherDirectType == null) {
			otherDirectType = OtherDirectType.OTHER;
		}
		return otherDirectType;
	}

	/*
	 * This method gets arrays of SalaryAndWagesType XMLObject
	 */
	private SalariesAndWagesType[] getSalaryAndWages(DevelopmentProposal developmentProposal,
			List<BudgetLineItem> budgetLineItems) {
		List<SalariesAndWagesType> salariesAndWagesTypeList = new ArrayList<SalariesAndWagesType>();
		for (BudgetLineItem budgetLineItem : budgetLineItems) {
			for (BudgetPersonnelDetails budgetPersDetails : budgetLineItem
					.getBudgetPersonnelDetailsList()) {
				budgetPersDetails.refreshNonUpdateableReferences();
				BudgetPerson budgetPerson = budgetPersDetails.getBudgetPerson();
				if (budgetPerson != null) {
					SalariesAndWagesType salariesAndWagesType = getSalariesAndWagesType(developmentProposal,
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
	private SalariesAndWagesType getSalariesAndWagesType(DevelopmentProposal deveopmentProposal,
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
		salariesAndWagesType.setProjectRole(getProjectRoleType(deveopmentProposal,budgetPerson));
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
	private ResearchCoverPage getResearchCoverPage(DevelopmentProposal developmentProposal, Budget budget){
	    developmentProposal.refreshNonUpdateableReferences();
		ResearchCoverPage researchCoverPage = ResearchCoverPage.Factory.newInstance();
		researchCoverPage.setSubmissionCategory(getSubmissionCategoryForResearchCoverPage(
						                        developmentProposal.getActivityType().getDescription(),
						                        developmentProposal.getProposalStateTypeCode()));
		researchCoverPage
				.setApplicationCategory(getApplicationCategoryForResearchCoverPage(developmentProposal
						.getProposalType().getDescription()));
		
		setApplicantSubmissionQualifiersForResearchCoverPage(developmentProposal,researchCoverPage.addNewApplicantSubmissionQualifiers());
		setFederalAgencyReceiptQualifiersForResearchCoverPage(developmentProposal,researchCoverPage.addNewFederalAgencyReceiptQualifiers());
		setStateReceiptQualifiersForResearchCoverPage(developmentProposal,researchCoverPage.addNewStateReceiptQualifiers());
		setStateIntergovernmentalReviewForResearchCoverPage(developmentProposal,researchCoverPage.addNewStateIntergovernmentalReview());
		setFederalDebtDelinquencyQuestionForResearchCoverPage(developmentProposal,researchCoverPage.addNewFederalDebtDelinquencyQuestions());
		researchCoverPage.setProjectDates(getProjectDatesForResearchCoverPage(
		                                developmentProposal.getRequestedStartDateInitial(),
		                                developmentProposal.getRequestedEndDateInitial()));
		researchCoverPage.setBudgetTotals(getBudgetTotalsForResearchCoverPage(budget));
		researchCoverPage.setProjectTitle(developmentProposal.getTitle() == null ? DEFAULT_VALUE_UNKNOWN
						                                                            : developmentProposal.getTitle());
		researchCoverPage.setOtherAgencyQuestions(getOtherAgencyQuestionsForResearchCoverPage(developmentProposal));
		researchCoverPage.setApplicantOrganization(getApplicantOrganizationForResearchCoverPage(developmentProposal));
		researchCoverPage.setPrimaryProjectSite(getProjectSiteForResearchCoverPage(developmentProposal));
		researchCoverPage.setProgramDirectorPrincipalInvestigator(getProgramDirectorPrincipalInvestigatorForResearchCoverPage(developmentProposal));
		researchCoverPage.setFundingOpportunityDetails(getFundingOpportunityDetailsForResearchCoverPage(developmentProposal));
		researchCoverPage.setAuthorizedOrganizationalRepresentative(getAuthorizedOrganizationalRepresentative(developmentProposal));
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
				.getFederalEmployerId() == null ? DEFAULT_VALUE_UNKNOWN
				: organization.getFederalEmployerId());
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
					humanSubjectsType.setIRBApprovalDate(getDateTimeService()
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
		if (proposalSpecialReview.getSpecialReviewTypeCode() != null
				&& proposalSpecialReview.getSpecialReviewTypeCode().equals(
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
}
