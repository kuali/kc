package org.kuali.kra.proposaldevelopment.printing.xmlstream;

import gov.nih.era.projectmgmt.sbir.cgap.commonNamespace.ContactInfoType;
import gov.nih.era.projectmgmt.sbir.cgap.commonNamespace.PostalAddressType;
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
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.OtherAgencyQuestionsType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.OtherDirectType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ParticipantType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.PersonFullNameType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ProjectRoleType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ProjectSiteType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.TravelCostsDocument;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.TravelType;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.AnimalSubjectDocument.AnimalSubject;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ApplicantOrganizationType.OrganizationContactPerson;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.EquipmentCostsDocument.EquipmentCosts;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.OtherDirectCostsDocument.OtherDirectCosts;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ParticipantPatientCostsDocument.ParticipantPatientCosts;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ProjectSurveyDocument.ProjectSurvey;
import gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.TravelCostsDocument.TravelCosts;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.OrganizationYnq;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.calculator.RateClassType;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.nonpersonnel.AbstractBudgetRateAndBase;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.personnel.BudgetPerson;
import org.kuali.kra.budget.personnel.BudgetPersonnelDetails;
import org.kuali.kra.budget.personnel.BudgetPersonnelRateAndBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalSite;
import org.kuali.kra.proposaldevelopment.bo.ProposalSpecialReview;
import org.kuali.kra.proposaldevelopment.bo.ProposalYnq;

public abstract class AbstractResearchAndRelatedStream extends ProposalBaseStream{
	private static final Logger LOG = Logger.getLogger(AbstractResearchAndRelatedStream.class);
	
	protected static final String EMPTY_STRING = " ";
	protected static final String REPORT_NAME = "Research and Related";
	protected static final String SPECIAL_REVIEW_CODE_2 = "2";
	protected static final String IACU_APPROVAL_PENDING_VALUE = "Pending";
	protected static final String BUDGET_JUSTIFICATION_IDENTIFIER = "budgetJust";
	private static final String CATEGORY_CODE_EQUIPMENT_RENTAL = "13";
	private static final String CATEGORY_CODE_EQUIPMENT = "20";
	private static final String CATEGORY_CODE_TRAVEL_FOREIGN = "23";
	private static final String CATEGORY_CODE_TRAVEL_DOMESTIC = "7";
	private static final String CATEGORY_CODE_PARTICIPANT_TRAVEL = "31";
	private static final String CATEGORY_CODE_PARTICIPANT_SUBSISTANCE = "36";
	private static final String CATEGORY_CODE_PARTICIPANT_STIPENDS = "32";
	private static final String CATEGORY_CODE_PARTICIPANT_OTHER = "2";
	private static final String CATEGORY_CODE_OUTPATIENT = "33";
	private static final String CATEGORY_CODE_INPATIENT = "9";
	protected static final String DEFAULT_VALUE_UNKNOWN = "Unknown";
	private static final String PROPOSALQUESTION_ID15 = "15";
	private static final String ANSWER_INDICATOR_VALUE = "Y";
	private static final String DEFAULT_ANSWER = "This question has not been answered";
	private static final String STEMCELL = "18";
	private static final String INTERNATIONAL_ACTIVITIES = "H1";
	private static final String PROPRIETARY_INFO = "G8";
	private static final String HISTORICAL_SITES = "G6";
	private static final String GENETICALLY_ENGINEERED = "G4";
	private static final String HAZARDOUS_MATERIALS = "G3";
	private static final String NSFSMALL_GRANT = "14";
	private static final String NSF_BEGINNING_INV = "12";
	private static final String LOBBYING_ACTIVITIES = "H4";
	private static final String ANSWER_PARAMETER = "answer";
	private static final String QUESTION_ID_PARAMETER = "questionId";
	private static final String ORGANIZATION_ID_PARAMETER = "organizationId";
	private static final String APPLICATION_DATE = "01/02/2004";
	private static final String AGENCY_RECEIPT_DATE = "12/31/2005";
	private static final String STATE_RECEIPT_DATE = "10/17/2005";
	private static final String REVIEW_AVAILABILITY_DATE = "04/14/2004";
	
	private static final String CATEGORY_CODE_DOMESTIC_TRAVEL = "73";
	private static final String cATEGORY_CODE_FOREIGN_TRAVEL = "74";
	
	
	
	/*
	 * This method will set the values to animal subject attributes like
	 * AssuranceNumber,VertebrateAnimalsUsedQuestion,IACUCApprovalDate and
	 * IACUCApprovalPending.
	 */
	protected AnimalSubject getAnimalSubject(
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
	 * This method will get the animal welfare assurance number from the
	 * organization
	 */
	protected String getAnimalWelfareAssuranceNumber(
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
	protected Organization getOrganizationFromDevelopmentProposal(
			DevelopmentProposal developmentProposal) {
		Organization organization = null;
		ProposalSite proposalSite = developmentProposal
				.getApplicantOrganization();
		if (proposalSite != null) {
			organization = proposalSite.getOrganization();
		}
		return organization;
	}
	
	/*
	 * This method gets CoreSubmissionCategoryType XMLObject and setting data to
	 * CoreSubmissionCategoryType object from developmentProposal
	 * activityDescription and creationStatusCode data
	 */
	protected CoreSubmissionCategoryType getSubmissionCategoryForResearchCoverPage(
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
	 * This method gets BudgetTotalType XMLObject by setting data from totalCost
	 * and costSharingAmount to BudgetTotalType for budgetPeriod and budget
	 */
	protected BudgetTotalsType getBudgetTotals(BudgetDecimal totalCost,
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
	 * This method gets DescriptionBlockType XMLObject. In the
	 * DescriptionBlockType object only FileIdentifier fields sets by
	 * concatenating proposal number and budget Just
	 */
	protected DescriptionBlockType getBudgetJustification(String proposalNumber) {
		DescriptionBlockType descBlockType = DescriptionBlockType.Factory
				.newInstance();
		descBlockType.setFileIdentifier(new StringBuilder(proposalNumber)
				.append(BUDGET_JUSTIFICATION_IDENTIFIER).toString());
		return descBlockType;
	}
	
	/*
	 * This method gets arrays of otherDirectCost XMLObject from List of
	 * BudgetLineItems by checking the budgetCategoryCode as Other
	 */
	protected OtherDirectCosts[] getOtherDirectCosts(
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
	 * This method check budgetCagegoryCode for other in BudgetLineItem by
	 * checking budgetCategoryCode travel, equipment, patient and participant
	 */
	protected boolean isBudgetCategoryOther(BudgetLineItem budgetLineItem) {
		boolean isOther = true;
		if (isBudgetCategoryEquipment(budgetLineItem)
				&& isBudgetCategoryTravel(budgetLineItem)
				&& isBudgetCategoryParticipantPatient(budgetLineItem)) {
			isOther = false;
		}
		return isOther;
	}
	
	/*
	 * This method check budgetCagegoryCode for equipment in BudgetLineItem
	 */
	protected boolean isBudgetCategoryEquipment(BudgetLineItem budgetLineItem) {
		return budgetLineItem.getBudgetCategoryCode().equals(
				CATEGORY_CODE_EQUIPMENT_RENTAL)
				|| budgetLineItem.getBudgetCategoryCode().equals(
						CATEGORY_CODE_EQUIPMENT);
	}
	
	/*
	 * This method gets sum of lineItemCost as travel total cost from List of
	 * BudgetLineItem, if budgetCategoryCode is travel for budgetLineItem
	 */
	protected BigDecimal getTravelTotal(List<BudgetLineItem> budgetLineItems) {
		BudgetDecimal cost = BudgetDecimal.ZERO;
		for (BudgetLineItem budgetLineItem : budgetLineItems) {
			if (isBudgetCategoryTravel(budgetLineItem)) {
				cost = cost.add(budgetLineItem.getLineItemCost());
			}
		}
		return cost.bigDecimalValue();
	}
	
	/*
	 * This method check budgetCagegoryCode for travel in BudgetLineItem
	 */
	protected boolean isBudgetCategoryTravel(BudgetLineItem budgetLineItem) {
		return budgetLineItem.getBudgetCategoryCode().equals(
				CATEGORY_CODE_TRAVEL_DOMESTIC)
				|| budgetLineItem.getBudgetCategoryCode().equals(
						CATEGORY_CODE_TRAVEL_FOREIGN);
	}
	
	/*
	 * This method check budgetCagegoryCode for Participant and Patient in
	 * BudgetLineItem
	 */
	protected boolean isBudgetCategoryParticipantPatient(
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
	 * This method gets arrays of EquipmentCost XMLObject from list of
	 * budgetLineItems by checking the budgetCategory as equipment
	 */
	protected EquipmentCosts[] getEquipmentCosts(
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
	 * This method gets TravelType Enum value based on budgetCategory
	 * Description
	 */
	protected TravelType.Enum getTravelType(String budgetCategoryDesc) {
		TravelType.Enum travelType = TravelType.Enum
				.forString(budgetCategoryDesc);
		return travelType;
	}
	
	/*
	 * This method gets personFullNameType XMLObject and setting lastName,
	 * firstName, middleName data to it
	 */
	protected gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.PersonFullNameType getContactPersonFullName(String lastName,
			String firstName, String middleName) {
		PersonFullNameType personFullNameType = PersonFullNameType.Factory
				.newInstance();
		personFullNameType.setLastName(lastName);
		personFullNameType.setFirstName(firstName);
		personFullNameType.setMiddleName(middleName);
		return personFullNameType;
	}
	
	/*
	 * This method gets OrganizationContactPerson XMLObject and set data from
	 * Rolodex if Rolodex is there else it set default values to it
	 */
	protected OrganizationContactPerson getOrganizationContactPerson(
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
	 * This method gets ContactInfoType XMLObject and sets data to it from
	 * Rolodex
	 */
	protected ContactInfoType getPersonContactInformation(Rolodex rolodex) {
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
	 * This method gets contactInfoType XMLObject and set email, faxNumber,
	 * phoneNumber, postalAddress default values to it
	 */
	protected ContactInfoType getOrganizationPersonContactInformationWithDefaultValues() {
		ContactInfoType contactInfoType = ContactInfoType.Factory.newInstance();
		contactInfoType.setEmail(DEFAULT_VALUE_UNKNOWN);
		contactInfoType.setFaxNumber(DEFAULT_VALUE_UNKNOWN);
		contactInfoType.setPhoneNumber(DEFAULT_VALUE_UNKNOWN);
		contactInfoType.setPostalAddress(getPostalAddressWithDefaultValues());
		return contactInfoType;
	}
	
	/*
	 * This method gets PostalAddressType XMLObject and setting rolodex details
	 * to it
	 */
	protected PostalAddressType getOrganizationAddress(Rolodex rolodex) {
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
	protected OtherAgencyQuestionsType getOtherAgencyQuestionsForResearchCoverPage(
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
	 * This method return true if question is answered otherwise false .
	 */
	protected boolean getAnswerFromOrganizationYnq(OrganizationYnq organizationYnq) {
		return organizationYnq.getAnswer().equals(ANSWER_INDICATOR_VALUE) ? true
				: false;
	}
	/*
	 * This method will set the values to project survey attributes based on
	 * whether question answered or not status. If no proposal YNQ 's then set
	 * to default values.
	 */
	protected ProjectSurvey getProjectSurvey(
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
	 * This method gets SalariesAndWages from List of BudgetPersonnelRateAndBase
	 * List for a BudgetLineItem by checking the rateClassType as Vacation and
	 * Employee Benefit
	 */
	protected BigDecimal getSalaryWagesTotal(List<BudgetLineItem> budgetLineItems) {
		BudgetDecimal salaryAndWagesTotal = BudgetDecimal.ZERO;
		for (BudgetLineItem budgetLineItem : budgetLineItems) {
			for (BudgetPersonnelDetails budgetPersDetails : budgetLineItem
					.getBudgetPersonnelDetailsList()) {
				salaryAndWagesTotal = salaryAndWagesTotal
						.add(getSalaryWagesTotalForLineItem(budgetPersDetails));
			}
		}
		return salaryAndWagesTotal.bigDecimalValue();
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
	protected BudgetDecimal getFringeCost(BudgetPersonnelDetails budgetPersDetails) {
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
	 * This method gets true if rateClassType is E else false from RateAndBase
	 */
	private boolean isRateAndBaseOfRateClassTypeEB(
			AbstractBudgetRateAndBase rateAndBase) {
		if (rateAndBase == null) {
			LOG.debug("isRateAndBaseOfRateClassTypeEB : Rate and Base is null");
			return false;
		}		
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
		if (rateAndBase == null) {
			LOG.debug("isRateAndBaseOfRateClassTypeVacation : Rate and Base is null");
			return false;
		}		
		rateAndBase.refreshNonUpdateableReferences();
		if(rateAndBase.getRateClass()!=null && RateClassType.VACATION.getRateClassType().equals(
				rateAndBase.getRateClass().getRateClassType())){
			return true;
		}else{
			return false;
		}
	}
	
	/*
	 * This method gets true if rateClassType is O else false from RateAndBase
	 */
	protected boolean isRateAndBaseOfRateClassTypeOverhead(
			AbstractBudgetRateAndBase rateAndBase) {
		if (rateAndBase == null) {
			LOG.debug("isRateAndBaseOfRateClassTypeOverhead : Rate and Base is null");
			return false;
		}		
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
	 * This method gets sum of lineItemCost as otherDirect total cost from List
	 * of BudgetLineItem, if budgetCategoryCode is paticipantPatient for
	 * budgetLineItem
	 */
	protected BigDecimal getParticipantPatientTotal(
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
	protected BigDecimal getOtherDirectTotal(List<BudgetLineItem> budgetLineItems) {
		BudgetDecimal cost = BudgetDecimal.ZERO;
		for (BudgetLineItem budgetLineItem : budgetLineItems) {
			if (isBudgetCategoryOther(budgetLineItem)) {
				cost = cost.add(budgetLineItem.getLineItemCost());
			}
		}
		return cost.bigDecimalValue();
	}
	
	/*
	 * This method gets Arrays of TravelCost XMLObject from list of
	 * BudgetLineItems by checking the BudgetCategoryCode as Travel
	 */
	protected TravelCosts[] getTravelCosts(List<BudgetLineItem> budgetLineItems) {
		List<TravelCosts> travelCostList = new ArrayList<TravelCosts>();
		for (BudgetLineItem budgetLineItem : budgetLineItems) {
			if (isBudgetCategoryTravel(budgetLineItem)) {
				TravelCosts travelCost = TravelCosts.Factory.newInstance();
				if (CATEGORY_CODE_TRAVEL_FOREIGN.equals(budgetLineItem.getBudgetCategoryCode())) {
					travelCost.setType(TravelType.FOREIGN);
				}
				else{ //if (CATEGORY_CODE_TRAVEL_DOMESTIC.equals(budgetLineItem.getBudgetCategoryCode())) {
					travelCost.setType(TravelType.DOMESTIC);
				}	
				
				travelCost.setCost(budgetLineItem.getLineItemCost()
						.bigDecimalValue());
				travelCost.setDescription(budgetLineItem
						.getLineItemDescription());
				travelCostList.add(travelCost);
			}
		}
		return travelCostList.toArray(new TravelCosts[0]);
	}

	/*
	 * This method gets Arrays of ParticipantPatientCost XMLObject from list of
	 * BudgetLineItems by checking the BudgetCategoryCode as paricipantPatient
	 */
	protected ParticipantPatientCosts[] getParticipantPatientCost(
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
	private ParticipantType.Enum getParticipantPatientType(
			String budgetCatgoryDesc) {
		ParticipantType.Enum participantType = ParticipantType.Enum
				.forString(budgetCatgoryDesc);
		if (participantType == null) {
			participantType = ParticipantType.OTHER;
		}
		return participantType;
	}
	
	/*
	 * This method gets sum of lineItemCost as equipment total cost from List of
	 * BudgetLineItem, if budgetCategoryCode is equipment for budgetLineItem
	 */
	protected BigDecimal getEquipmentTotal(List<BudgetLineItem> budgetLineItems) {
		BudgetDecimal cost = BudgetDecimal.ZERO;
		for (BudgetLineItem budgetLineItem : budgetLineItems) {
			if (isBudgetCategoryEquipment(budgetLineItem)) {
				cost = cost.add(budgetLineItem.getLineItemCost());
			}
		}
		return cost.bigDecimalValue();
	}
	
	/*
	 * This method will get the list of Organization YNQ for given question id.
	 */
	protected List<OrganizationYnq> getOrganizationYNQ(String questionId) {
		OrganizationYnq organizationYnq = null;
		Map<String, String> organizationYnqMap = new HashMap<String, String>();
		organizationYnqMap.put(ORGANIZATION_ID_PARAMETER, questionId);
		List<OrganizationYnq> organizationYnqs = (List<OrganizationYnq>) businessObjectService
				.findMatching(OrganizationYnq.class, organizationYnqMap);
		return organizationYnqs;
	}
	
	/*
	 * This method gets ProjectRoleType as Enum value if the budgetPerson role
	 * not match with the enum type the roleType set to Other
	 */
	protected gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ProjectRoleType.Enum getProjectRoleType(
			BudgetPerson budgetPerson) {
		gov.nih.era.projectmgmt.sbir.cgap.researchandrelatedNamespace.ProjectRoleType.Enum roleType = ProjectRoleType.Enum
				.forString(budgetPerson.getRole());
		if (roleType == null) {
			roleType = ProjectRoleType.OTHER;
		}
		return roleType;
	}
	
	/*
	 * This method gets CoreApplicationCategoryType XMLObject and setting
	 * description to it from ProposalType which comes from DevelopmentProposal
	 */
	protected CoreApplicationCategoryType getApplicationCategoryForResearchCoverPage(
			String propTypeDesc) {
		CoreApplicationCategoryType coreApplicationCategoryType = CoreApplicationCategoryType.Factory
				.newInstance();
		coreApplicationCategoryType
				.setCategoryIdentifier(propTypeDesc == null ? EMPTY_STRING
						: propTypeDesc);
		return coreApplicationCategoryType;
	}

	/*
	 * This method gets CoreApplicantSubmissionQualifiersType XMLObject and
	 * setting Application date to it
	 */
	protected CoreApplicantSubmissionQualifiersType getApplicantSubmissionQualifiersForResearchCoverPage()
			throws ParseException {
		CoreApplicantSubmissionQualifiersType coreApplicantSubmissionQualifiersType = CoreApplicantSubmissionQualifiersType.Factory
				.newInstance();
		coreApplicantSubmissionQualifiersType
				.setApplicationDate(dateTimeService.getCalendar(dateTimeService
						.convertToDate(APPLICATION_DATE)));

		return coreApplicantSubmissionQualifiersType;
	}
	/*
	 * This method gets CoreFederalAgencyReceiptQualifiersType XMLObject and
	 * setting AgencyName and AgencyReceiptDate to it
	 */
	protected CoreFederalAgencyReceiptQualifiersType getFederalAgencyReceiptQualifiersForResearchCoverPage()
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
	 * This method gets CoreStateReceiptQualifiersType XMLObject and setting
	 * stateReceiptDate to it
	 */
	protected CoreStateReceiptQualifiersType getStateReceiptQualifiersForResearchCoverPage()
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
	 * This method gets CoreStateIntergovernmentalReviewType XMLObject and
	 * setting ReviewAvailityDate and SubjectToreviewQuestion data to it
	 */
	protected CoreStateIntergovernmentalReviewType getStateIntergovernmentalReviewForResearchCoverPage()
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
	 * This method gets CoreFederalDebtDelinquencyQuestionsType XMLObject and
	 * setting pplicantDelinquentIndicator data to it
	 */
	protected CoreFederalDebtDelinquencyQuestionsType getFederalDebtDelinquencyQuestionForResearchCoverPage() {
		CoreFederalDebtDelinquencyQuestionsType ccoreFedDebtQuestionsType = CoreFederalDebtDelinquencyQuestionsType.Factory
				.newInstance();
		ccoreFedDebtQuestionsType.setApplicantDelinquentIndicator(false);
		return ccoreFedDebtQuestionsType;
	}

	/*
	 * This method gets CoreProjectDatesType XMLObject and setting
	 * projectStartDate and projectEndDate data to it from developmentProposal
	 * requestedStartDateInitial and requestedEndDateInitial
	 */
	protected CoreProjectDatesType getProjectDatesForResearchCoverPage(
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
	 * This method gets CoreBudgetTotalsType XMLObject and setting budget data
	 * to it if budget is available and budgetModular flag is set else setting
	 * to default value as 0
	 */
	protected CoreBudgetTotalsType getBudgetTotalsForResearchCoverPage(
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
	 * This method gets ProjectSiteType XMLObject and set data from performing
	 * organization to it
	 */
	protected ProjectSiteType getProjectSiteForResearchCoverPage(
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
	 * This method gets PostalAddressType XMLObject and setting proposalPerson
	 * details to it
	 */
	protected PostalAddressType getPostalAddress(ProposalPerson proposalPerson) {
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
	 * This method gets contactInfoType XMLObject and set email, faxNumber,
	 * phoneNumber, postalAddress to it from proposalPerson
	 */
	protected ContactInfoType getPersonContactInformation(
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
	protected void sortKeyPersonWithName(
			List<ProposalPerson> proposalPersonList) {
		Collections.sort(proposalPersonList,new Comparator<ProposalPerson> (){
			public int compare(ProposalPerson pp1, ProposalPerson pp2) {
				return pp1.getFullName().compareTo(pp2.getFullName());
			}
		});
	}
}

