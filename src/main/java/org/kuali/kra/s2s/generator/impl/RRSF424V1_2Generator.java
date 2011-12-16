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

import gov.grants.apply.forms.rrSF42412V12.AORInfoType;
import gov.grants.apply.forms.rrSF42412V12.ApplicationTypeCodeDataType;
import gov.grants.apply.forms.rrSF42412V12.OrganizationContactPersonDataType;
import gov.grants.apply.forms.rrSF42412V12.RRSF42412Document;
import gov.grants.apply.forms.rrSF42412V12.RevisionTypeCodeDataType;
import gov.grants.apply.forms.rrSF42412V12.StateReviewCodeTypeDataType;
import gov.grants.apply.forms.rrSF42412V12.SubmissionTypeDataType;
import gov.grants.apply.forms.rrSF42412V12.ApplicationTypeCodeDataType.Enum;
import gov.grants.apply.forms.rrSF42412V12.RRSF42412Document.RRSF42412;
import gov.grants.apply.forms.rrSF42412V12.RRSF42412Document.RRSF42412.ApplicantInfo;
import gov.grants.apply.forms.rrSF42412V12.RRSF42412Document.RRSF42412.ApplicantType;
import gov.grants.apply.forms.rrSF42412V12.RRSF42412Document.RRSF42412.ApplicationType;
import gov.grants.apply.forms.rrSF42412V12.RRSF42412Document.RRSF42412.EstimatedProjectFunding;
import gov.grants.apply.forms.rrSF42412V12.RRSF42412Document.RRSF42412.StateReview;
import gov.grants.apply.forms.rrSF42412V12.RRSF42412Document.RRSF42412.ApplicantInfo.ContactPersonInfo;
import gov.grants.apply.forms.rrSF42412V12.RRSF42412Document.RRSF42412.ApplicantType.SmallBusinessOrganizationType;
import gov.grants.apply.forms.rrSF42412V12.RRSF42412Document.RRSF42412.ApplicantType.SmallBusinessOrganizationType.IsSociallyEconomicallyDisadvantaged;
import gov.grants.apply.forms.rrSF42412V12.RRSF42412Document.RRSF42412.ApplicantType.SmallBusinessOrganizationType.IsWomenOwned;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.globalLibraryV20.AddressDataType;
import gov.grants.apply.system.globalLibraryV20.ApplicantTypeCodeDataType;
import gov.grants.apply.system.globalLibraryV20.OrganizationDataType;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType;
import gov.grants.apply.system.universalCodesV20.CountryCodeDataType;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.UnitAdministrator;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.distributionincome.BudgetProjectIncome;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.proposaldevelopment.bo.ProposalSite;
import org.kuali.kra.proposaldevelopment.bo.ProposalYnq;
import org.kuali.kra.proposaldevelopment.budget.modular.BudgetModularIdc;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.bo.S2sOpportunity;
import org.kuali.kra.s2s.generator.bo.DepartmentalPerson;
import org.kuali.kra.s2s.util.S2SConstants;

/**
 * Class for generating the XML object for grants.gov RRSF424V1_0. Form is
 * generated using XMLBean classes and is based on RRSF424V1_2 schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class RRSF424V1_2Generator extends RRSF424BaseGenerator {
	private static final Log LOG = LogFactory
			.getLog(RRSF424V1_2Generator.class);
	private DepartmentalPerson departmentalPerson;

	/**
	 * 
	 * This method gives information of applications that are used in RRSF424
	 * 
	 * @return rrSF424Document {@link XmlObject} of type RRSF424Document.
	 */
	private RRSF42412Document getRRSF424() {
		RRSF42412Document rrSF424Document = RRSF42412Document.Factory
				.newInstance();
		RRSF42412 rrsf42412 = RRSF42412.Factory.newInstance();
		rrsf42412.setFormVersion(S2SConstants.FORMVERSION_1_2);
		rrsf42412.setSubmittedDate(s2sUtilService.getCurrentCalendar());
		if(getSubmissionTypeCode() != null){
			rrsf42412.setSubmissionTypeCode(SubmissionTypeDataType.Enum.forInt(Integer.parseInt(getSubmissionTypeCode())));
		}
		rrsf42412.setStateID(getRolodexState());
		rrsf42412.setApplicantInfo(getApplicationInfo());
		rrsf42412.setEmployerID(getEmployerId());
		rrsf42412.setApplicantType(getApplicantType());
		rrsf42412.setApplicationType(getApplicationType());
		rrsf42412.setApplicantID(pdDoc.getDevelopmentProposal()
				.getProposalNumber());
		rrsf42412.setFederalAgencyName(getFederalAgencyName());
		rrsf42412.setProjectTitle(getProjectTitle());
		rrsf42412.setProposedProjectPeriod(getProjectPeriod());
		rrsf42412.setCongressionalDistrict(getCongDistrict());
		if(pdDoc.getDevelopmentProposal().getCfdaNumber()!=null){
		    rrsf42412.setCFDANumber(pdDoc.getDevelopmentProposal().getCfdaNumber());
		}
		rrsf42412.setActivityTitle(getActivityTitle());
		setFederalId(rrsf42412);
		rrsf42412.setPDPIContactInfo(getPDPI());
		rrsf42412.setEstimatedProjectFunding(getProjectFunding());
		rrsf42412.setTrustAgree(YesNoDataType.Y_YES);// Value is hardcoded
		rrsf42412.setStateReview(getStateReview());
		rrsf42412.setAORInfo(getAORInfoType());
		rrsf42412.setAORSignature(getAORSignature());
		rrsf42412.setAORSignedDate(s2sUtilService.getCurrentCalendar());
		setPreApplicationAttachment(rrsf42412);
		setSFLLLAttachment(rrsf42412);
		rrSF424Document.setRRSF42412(rrsf42412);
		return rrSF424Document;
	}

	/**
	 * 
	 * This method is to get estimated project funds for RRSF424
	 * 
	 * @return EstimatedProjectFunding estimated total cost for the project.
	 * @throws S2SException
	 */
	private EstimatedProjectFunding getProjectFunding() {
		BudgetDocument budgetDoc = null;
		Budget budget = null;
		EstimatedProjectFunding funding = EstimatedProjectFunding.Factory
				.newInstance();
		funding.setTotalEstimatedAmount(BigDecimal.ZERO);
		funding.setTotalNonfedrequested(BigDecimal.ZERO);
		funding.setTotalfedNonfedrequested(BigDecimal.ZERO);
		funding.setEstimatedProgramIncome(BigDecimal.ZERO);
		try {
			budgetDoc = s2sBudgetCalculatorService.getFinalBudgetVersion(pdDoc);
		} catch (Exception e) {
			LOG.error("Error while fetching Budget document", e);
			return funding;
		}

		if (budgetDoc != null) {
			budget = budgetDoc.getBudget();
		}
		if (budget != null) {
			if (budget.getModularBudgetFlag()) {
				BudgetDecimal fundsRequested = BudgetDecimal.ZERO;
				BudgetDecimal totalDirectCost = BudgetDecimal.ZERO;
				BudgetDecimal totalCost = BudgetDecimal.ZERO;
				// get modular budget amounts instead of budget detail amounts
				for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
					totalDirectCost = totalDirectCost.add(budgetPeriod
							.getBudgetModular().getTotalDirectCost());
					for (BudgetModularIdc budgetModularIdc : budgetPeriod
							.getBudgetModular().getBudgetModularIdcs()) {
						fundsRequested = fundsRequested.add(budgetModularIdc
								.getFundsRequested());
					}
				}
				totalCost = totalCost.add(totalDirectCost);
				totalCost = totalCost.add(fundsRequested);
				budget.setTotalIndirectCost(fundsRequested);
				budget.setTotalCost(totalCost);
			}

			BudgetDecimal fedNonFedCost = BudgetDecimal.ZERO;
			fedNonFedCost = fedNonFedCost.add(budget.getTotalCost());

			for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
                for (BudgetLineItem lineItem : budgetPeriod.getBudgetLineItems()) {
                    if(budget.getSubmitCostSharingFlag() && lineItem.getSubmitCostSharingFlag())
                    fedNonFedCost=fedNonFedCost.add(lineItem.getCostSharingAmount());
                    }
            }
			
			funding = EstimatedProjectFunding.Factory.newInstance();
			funding.setTotalEstimatedAmount(budget.getTotalCost()
					.bigDecimalValue());
			funding.setTotalNonfedrequested(budget.getCostSharingAmount()
					.bigDecimalValue());
			funding.setTotalNonfedrequested(budget.getCostSharingAmount()
					.bigDecimalValue());
			funding.setTotalfedNonfedrequested(fedNonFedCost.bigDecimalValue());
			funding.setEstimatedProgramIncome(getTotalProjectIncome(budget));
		}
		return funding;
	}

	/*
	 * This method computes total project income for the given budget
	 */
	private BigDecimal getTotalProjectIncome(Budget budget) {
		BigDecimal totalProjectIncome = BigDecimal.ZERO;
		for (BudgetProjectIncome budgetProjectIncome : budget
				.getBudgetProjectIncomes()) {
			totalProjectIncome = totalProjectIncome.add(budgetProjectIncome
					.getProjectIncome().bigDecimalValue());
		}
		return totalProjectIncome;
	}

	/**
	 * 
	 * This method gives the information for an application which consists of
	 * personal details
	 * 
	 * @return appInfo(ApplicantInfo) applicant details.
	 */
	private ApplicantInfo getApplicationInfo() {
		ApplicantInfo appInfo = ApplicantInfo.Factory.newInstance();
		appInfo.setContactPersonInfo(getContactPersonInfo());
		appInfo.setOrganizationInfo(getOrganizationDataType());
		return appInfo;
	}

	private ContactPersonInfo getContactPersonInfo() {
		String contactType = getContactType();
		ContactPersonInfo contactInfo = ContactPersonInfo.Factory.newInstance();
		if (contactType.equals(CONTACT_TYPE_I)) {
			// use organization rolodex contact
			if (pdDoc.getDevelopmentProposal().getApplicantOrganization() != null) {
				contactInfo = getContactInfo(pdDoc.getDevelopmentProposal()
						.getApplicantOrganization().getRolodex());
			}
		} else {
			// contact will come from unit or unit_administrators
			DepartmentalPerson depPerson = getContactPerson(pdDoc);
			if (depPerson != null) {
				contactInfo.setName(globLibV20Generator
						.getHumanNameDataType(depPerson));
				contactInfo.setPhone(depPerson.getOfficePhone());
				if (depPerson.getFaxNumber() != null) {
					contactInfo.setFax(depPerson.getFaxNumber());
				}
				if (depPerson.getEmailAddress() != null) {
					contactInfo.setEmail(depPerson.getEmailAddress());
				}
			}
		}
		return contactInfo;
	}

	private OrganizationDataType getOrganizationDataType() {
		OrganizationDataType orgType = OrganizationDataType.Factory
				.newInstance();
		Rolodex rolodex = pdDoc.getDevelopmentProposal()
				.getApplicantOrganization().getOrganization().getRolodex();
		orgType.setAddress(globLibV20Generator.getAddressDataType(rolodex));

		Organization organization = pdDoc.getDevelopmentProposal()
				.getApplicantOrganization().getOrganization();
		if (organization != null) {
			orgType.setOrganizationName(organization.getOrganizationName());
			orgType.setDUNSID(organization.getDunsNumber());
		}
		if (pdDoc.getDevelopmentProposal().getOwnedByUnit() != null) {
			String departmentName = pdDoc.getDevelopmentProposal()
					.getOwnedByUnit().getUnitName();
			if (departmentName != null
					&& departmentName.length() > DEPARTMENT_NAME_MAX_LENGTH) {
				departmentName = departmentName.substring(0,
						DEPARTMENT_NAME_MAX_LENGTH - 1);
			}
			orgType.setDepartmentName(departmentName);

			// divisionName
			String divisionName = s2sUtilService.getDivisionName(pdDoc);
			if (divisionName != null) {
				orgType.setDivisionName(divisionName);
			}
		}
		return orgType;
	}

	/**
	 * 
	 * This method is used to get Contact person information
	 * 
	 * @param rolodex(Rolodex)
	 * @return ContactPersonInfo corresponding to the Rolodex object.
	 */
	private ContactPersonInfo getContactInfo(Rolodex rolodex) {
		ContactPersonInfo contactInfo = ContactPersonInfo.Factory.newInstance();
		contactInfo.setName(globLibV20Generator.getHumanNameDataType(rolodex));
		contactInfo.setPhone("");
		if (rolodex != null) {
			contactInfo.setPhone(rolodex.getPhoneNumber());
			if (rolodex.getFaxNumber() != null) {
				contactInfo.setFax(rolodex.getFaxNumber());
			}
			if (rolodex.getEmailAddress() != null) {
				contactInfo.setEmail(rolodex.getEmailAddress());
			}
		}
		return contactInfo;
	}

	/**
	 * 
	 * This method gives the review information of a state
	 * 
	 * @return stateReview(StateReview) corresponding to the state review code.
	 */
	private StateReview getStateReview() {
		Map<String, String> eoStateReview = s2sUtilService
				.getEOStateReview(pdDoc);
		StateReviewCodeTypeDataType.Enum stateReviewCodeType = null;
		String strReview = eoStateReview.get(S2SConstants.YNQ_ANSWER);
		if (STATE_REVIEW_YES.equals(strReview)) {
			stateReviewCodeType = StateReviewCodeTypeDataType.Y_YES;
		} else if (STATE_REVIEW_NO.equals(strReview)) {
			stateReviewCodeType = StateReviewCodeTypeDataType.PROGRAM_HAS_NOT_BEEN_SELECTED_BY_STATE_FOR_REVIEW;
		} else {
			stateReviewCodeType = StateReviewCodeTypeDataType.PROGRAM_IS_NOT_COVERED_BY_E_O_12372;
		}
		StateReview stateReview = StateReview.Factory.newInstance();
		stateReview.setStateReviewCodeType(stateReviewCodeType);
		return stateReview;
	}

	/**
	 * 
	 * This method is used to get ApplicationType for the form RRSF424
	 * 
	 * @return ApplicationType corresponding to the proposal type code.
	 */
	private ApplicationType getApplicationType() {
		ApplicationType applicationType = ApplicationType.Factory.newInstance();
		Map<String, String> submissionInfo = s2sUtilService
				.getSubmissionType(pdDoc);
		if (pdDoc.getDevelopmentProposal().getProposalTypeCode() != null
				&& Integer.parseInt(pdDoc.getDevelopmentProposal()
						.getProposalTypeCode()) < PROPOSAL_TYPE_CODE_6) {
			// Check <6 to ensure that if proposalType='TASk ORDER", it must not
			// set. THis is because enum ApplicationType has no
			// entry for TASK ORDER
			applicationType
					.setApplicationTypeCode(getApplicationTypeCodeDataType());
			if (Integer.parseInt(pdDoc.getDevelopmentProposal()
					.getProposalTypeCode()) == ApplicationTypeCodeDataType.INT_REVISION) {

				String revisionCode = null;
				if (submissionInfo.get(S2SConstants.KEY_REVISION_CODE) != null) {
					revisionCode = submissionInfo
							.get(S2SConstants.KEY_REVISION_CODE);
					RevisionTypeCodeDataType.Enum revisionCodeApplication = RevisionTypeCodeDataType.Enum
							.forString(revisionCode);
					applicationType.setRevisionCode(revisionCodeApplication);
				}

				String revisionCodeOtherDesc = null;
				if (submissionInfo
						.get(S2SConstants.KEY_REVISION_OTHER_DESCRIPTION) != null) {
					revisionCodeOtherDesc = submissionInfo
							.get(S2SConstants.KEY_REVISION_OTHER_DESCRIPTION);
					applicationType
							.setRevisionCodeOtherExplanation(revisionCodeOtherDesc);
				}
			}
		}
		setOtherAgencySubmissionDetails(applicationType);
		return applicationType;
	}

	private void setOtherAgencySubmissionDetails(ApplicationType applicationType) {
		ProposalYnq proposalYnq = getAnswer(
				PROPOSAL_YNQ_OTHER_AGENCY_SUBMISSION, pdDoc);
		YesNoDataType.Enum answer = YesNoDataType.N_NO;
		if (proposalYnq != null && proposalYnq.getAnswer() != null) {
			answer = (proposalYnq.getAnswer().equals(
					S2SConstants.PROPOSAL_YNQ_ANSWER_Y) ? YesNoDataType.Y_YES
					: YesNoDataType.N_NO);
		}

		applicationType.setIsOtherAgencySubmission(answer);
		if (answer.equals(YesNoDataType.Y_YES)) {
			String answerExplanation = proposalYnq.getExplanation();
			if (answerExplanation != null) {
				if (answerExplanation.length() > ANSWER_EXPLANATION_MAX_LENGTH) {
					applicationType
							.setOtherAgencySubmissionExplanation(answerExplanation
									.substring(0, ANSWER_EXPLANATION_MAX_LENGTH));
				} else {
					applicationType
							.setOtherAgencySubmissionExplanation(answerExplanation);
				}
			}
		}
	}

	private Enum getApplicationTypeCodeDataType() {
		return ApplicationTypeCodeDataType.Enum.forInt(Integer.parseInt(pdDoc
				.getDevelopmentProposal().getProposalTypeCode()));
	}

	/**
	 * 
	 * This method is used to get Proposed Project Period for RRSF424
	 * 
	 * @return ProposedProjectPeriod project start date and end date.
	 */
	private RRSF42412.ProposedProjectPeriod getProjectPeriod() {
		RRSF42412.ProposedProjectPeriod proposedProjectPeriod = RRSF42412.ProposedProjectPeriod.Factory
				.newInstance();
		proposedProjectPeriod.setProposedStartDate(s2sUtilService
				.convertDateToCalendar(pdDoc.getDevelopmentProposal()
						.getRequestedStartDateInitial()));
		proposedProjectPeriod.setProposedEndDate(s2sUtilService
				.convertDateToCalendar(pdDoc.getDevelopmentProposal()
						.getRequestedEndDateInitial()));
		return proposedProjectPeriod;
	}

	/**
	 * 
	 * This method is used to get Congressional District for RRSF424
	 * 
	 * @return CongressionalDistrict congressional district for the Applicant
	 *         and Project.
	 */
	private RRSF42412.CongressionalDistrict getCongDistrict() {
		ProposalSite applicantOrganization = pdDoc.getDevelopmentProposal()
				.getApplicantOrganization();
		RRSF42412.CongressionalDistrict congressionalDistrict = RRSF42412.CongressionalDistrict.Factory
				.newInstance();
		if (applicantOrganization != null) {
			congressionalDistrict
					.setApplicantCongressionalDistrict(applicantOrganization
							.getFirstCongressionalDistrictName());
		} else {
			congressionalDistrict.setApplicantCongressionalDistrict("");
		}
		return congressionalDistrict;
	}

	/**
	 * 
	 * This method is used to get details of Principal Investigator for
	 * Organization Contact
	 * 
	 * @return OrganizationContactPersonDataType Principal investigator details.
	 */
	private OrganizationContactPersonDataType getPDPI() {
		OrganizationContactPersonDataType PDPI = OrganizationContactPersonDataType.Factory
				.newInstance();
		ProposalPerson PI = null;
		for (ProposalPerson proposalPerson : pdDoc.getDevelopmentProposal()
				.getProposalPersons()) {
			if (PRINCIPAL_INVESTIGATOR.equals(proposalPerson
					.getProposalPersonRoleId())) {
				PI = proposalPerson;
				ProposalSite applicantOrganization = pdDoc
						.getDevelopmentProposal().getApplicantOrganization();
				PDPI.setName(globLibV20Generator.getHumanNameDataType(PI));
				PDPI.setPhone(PI.getOfficePhone());
				PDPI.setEmail(PI.getEmailAddress());
				if (PI.getFaxNumber() != null) {
					PDPI.setFax(PI.getFaxNumber());
				}
				PDPI.setAddress(globLibV20Generator.getAddressDataType(PI));
				setDirectoryTitle(PDPI, PI);
				setDepartmentName(PDPI);
				setDivisionName(PDPI);
				if (applicantOrganization != null) {
					PDPI.setOrganizationName(applicantOrganization
							.getLocationName());
				}
			}
		}
		return PDPI;
	}

	private void setDivisionName(OrganizationContactPersonDataType PDPI) {
		String divisionName = s2sUtilService.getDivisionName(pdDoc);
		if (divisionName != null) {
			PDPI.setDivisionName(divisionName);
		}
	}

	private void setDepartmentName(OrganizationContactPersonDataType PDPI) {
		String departmentName = null;
		if (pdDoc.getDevelopmentProposal().getOwnedByUnit() != null) {
			departmentName = pdDoc.getDevelopmentProposal().getOwnedByUnit()
					.getUnitName();
			if (departmentName != null) {
				if (departmentName.length() > DEPARTMENT_NAME_MAX_LENGTH) {
					departmentName = departmentName.substring(0,
							DEPARTMENT_NAME_MAX_LENGTH - 1);
					PDPI.setDepartmentName(departmentName.substring(0,
							DEPARTMENT_NAME_MAX_LENGTH - 1));
				} else {
					PDPI.setDepartmentName(departmentName);
				}
			}
		}
	}

	private void setDirectoryTitle(OrganizationContactPersonDataType PDPI,
			ProposalPerson PI) {
		if (PI.getDirectoryTitle() != null) {
			if (PI.getDirectoryTitle().length() > DIRECTORY_TITLE_MAX_LENGTH) {
				PDPI.setTitle(PI.getDirectoryTitle().substring(0,
						DIRECTORY_TITLE_MAX_LENGTH));
			} else {
				PDPI.setTitle(PI.getDirectoryTitle());
			}
		}
	}

	/**
	 * 
	 * This method is used to get AOR Information for RRSf424
	 * 
	 * @return aorInfoType(AORInfoType) Authorized representative information.
	 */
	private AORInfoType getAORInfoType() {
		ProposalSite applicantOrganization = pdDoc.getDevelopmentProposal()
				.getApplicantOrganization();
		AORInfoType aorInfoType = AORInfoType.Factory.newInstance();
		if (departmentalPerson != null) {
			aorInfoType.setName(globLibV20Generator
					.getHumanNameDataType(departmentalPerson));

			setTitle(aorInfoType);
			setAddress(aorInfoType);
			setDivisionName(aorInfoType);
		}
		if (applicantOrganization != null) {
			aorInfoType.setOrganizationName(applicantOrganization
					.getLocationName());
		}

		return aorInfoType;
	}

	private void setTitle(AORInfoType aorInfoType) {
		if (departmentalPerson.getPrimaryTitle() != null) {
			if (departmentalPerson.getPrimaryTitle().length() > PRIMARY_TITLE_MAX_LENGTH) {
				aorInfoType.setTitle(departmentalPerson.getPrimaryTitle()
						.substring(0, PRIMARY_TITLE_MAX_LENGTH));
			} else {
				aorInfoType.setTitle(departmentalPerson.getPrimaryTitle());
			}
		} else {
			aorInfoType.setTitle("");
		}
	}

	private void setAddress(AORInfoType aorInfoType) {
		AddressDataType address = AddressDataType.Factory.newInstance();

		if (departmentalPerson.getAddress1() != null) {
			if (departmentalPerson.getAddress1().length() > 55) {
				address.setStreet1(departmentalPerson.getAddress1().substring(
						0, 55));
			} else {
				address.setStreet1(departmentalPerson.getAddress1());
			}
		}
		if (departmentalPerson.getAddress2() != null) {
			if (departmentalPerson.getAddress2().length() > 55) {
				address.setStreet2(departmentalPerson.getAddress2().substring(
						0, 55));
			} else {
				address.setStreet2(departmentalPerson.getAddress2());
			}
		}

		if (departmentalPerson.getCounty() != null) {
			address.setCounty(departmentalPerson.getCounty());
		}
		address.setCity(departmentalPerson.getCity());
		address.setZipPostalCode(departmentalPerson.getPostalCode());

		CountryCodeDataType.Enum countryCodeDataType = globLibV20Generator
				.getCountryCodeDataType(departmentalPerson.getCountryCode());
		address.setCountry(countryCodeDataType);

		String state = departmentalPerson.getState();
		if (state != null && !state.equals("")) {
			if (countryCodeDataType != null) {
				if (countryCodeDataType
						.equals(CountryCodeDataType.USA_UNITED_STATES)) {
					address
							.setState(globLibV20Generator
									.getStateCodeDataType(departmentalPerson
											.getState()));
				} else {
					address.setProvince(state);
				}
			}
		}

		aorInfoType.setAddress(address);
		aorInfoType.setPhone(departmentalPerson.getOfficePhone());
		aorInfoType.setFax(departmentalPerson.getFaxNumber());
		aorInfoType.setDepartmentName(departmentalPerson.getDirDept());
		aorInfoType.setEmail(departmentalPerson.getEmailAddress());
	}

	private void setDivisionName(AORInfoType aorInfoType) {
		if (departmentalPerson.getHomeUnit() != null) {
			aorInfoType.setDivisionName(departmentalPerson.getHomeUnit());
		}
	}

	/**
	 * 
	 * This method is used to get Applicant type for RRSF424
	 * 
	 * @return applicantType(ApplicantType) type of applicant.
	 */
	private ApplicantType getApplicantType() {
		ApplicantType applicantType = ApplicantType.Factory.newInstance();
		SmallBusinessOrganizationType smallOrganizationType = SmallBusinessOrganizationType.Factory
				.newInstance();
		IsSociallyEconomicallyDisadvantaged isSociallyEconomicallyDisadvantaged = IsSociallyEconomicallyDisadvantaged.Factory
				.newInstance();
		IsWomenOwned isWomenOwned = IsWomenOwned.Factory.newInstance();
		boolean smallBusflag = false;
		int orgTypeCode = 0;
		if (pdDoc.getDevelopmentProposal().getApplicantOrganization() != null
				&& pdDoc.getDevelopmentProposal().getApplicantOrganization()
						.getOrganization().getOrganizationTypes() != null
				&& pdDoc.getDevelopmentProposal().getApplicantOrganization()
						.getOrganization().getOrganizationTypes().size() > 0) {
			orgTypeCode = pdDoc.getDevelopmentProposal()
					.getApplicantOrganization().getOrganization()
					.getOrganizationTypes().get(0).getOrganizationTypeCode();
		}
		ApplicantTypeCodeDataType.Enum applicantTypeCode = null;

		switch (orgTypeCode) {
		case 1:
			applicantTypeCode = ApplicantTypeCodeDataType.C_CITY_OR_TOWNSHIP_GOVERNMENT;
			break;
		case 2:
			applicantTypeCode = ApplicantTypeCodeDataType.A_STATE_GOVERNMENT;
			break;
		case 3:
			applicantTypeCode = ApplicantTypeCodeDataType.X_OTHER_SPECIFY;
			break;
		case 4:
			applicantTypeCode = ApplicantTypeCodeDataType.M_NONPROFIT_WITH_501_C_3_IRS_STATUS_OTHER_THAN_INSTITUTION_OF_HIGHER_EDUCATION;
			break;
		case 5:
			applicantTypeCode = ApplicantTypeCodeDataType.N_NONPROFIT_WITHOUT_501_C_3_IRS_STATUS_OTHER_THAN_INSTITUTION_OF_HIGHER_EDUCATION;
			break;
		case 6:
			applicantTypeCode = ApplicantTypeCodeDataType.Q_FOR_PROFIT_ORGANIZATION_OTHER_THAN_SMALL_BUSINESS;
			break;
		case 7:
			applicantTypeCode = ApplicantTypeCodeDataType.X_OTHER_SPECIFY;
			break;
		case 8:
			applicantTypeCode = ApplicantTypeCodeDataType.I_INDIAN_NATIVE_AMERICAN_TRIBAL_GOVERNMENT_FEDERALLY_RECOGNIZED;
			break;
		case 9:
			applicantTypeCode = ApplicantTypeCodeDataType.P_INDIVIDUAL;
			break;
		case 10:
			applicantTypeCode = ApplicantTypeCodeDataType.O_PRIVATE_INSTITUTION_OF_HIGHER_EDUCATION;
			break;
		case 11:
			applicantTypeCode = ApplicantTypeCodeDataType.R_SMALL_BUSINESS;
			break;
		case 14:
			applicantTypeCode = ApplicantTypeCodeDataType.X_OTHER_SPECIFY;
			isSociallyEconomicallyDisadvantaged.setStringValue(VALUE_YES);
			smallOrganizationType
					.setIsSociallyEconomicallyDisadvantaged(isSociallyEconomicallyDisadvantaged);
			smallBusflag = true;
			break;
		case 15:
			applicantTypeCode = ApplicantTypeCodeDataType.X_OTHER_SPECIFY;
			isWomenOwned.setStringValue(VALUE_YES);
			smallOrganizationType.setIsWomenOwned(isWomenOwned);
			smallBusflag = true;
			break;
		case 21:
			applicantTypeCode = ApplicantTypeCodeDataType.H_PUBLIC_STATE_CONTROLLED_INSTITUTION_OF_HIGHER_EDUCATION;
			break;
		case 22:
			applicantTypeCode = ApplicantTypeCodeDataType.B_COUNTY_GOVERNMENT;
			break;
		case 23:
			applicantTypeCode = ApplicantTypeCodeDataType.D_SPECIAL_DISTRICT_GOVERNMENT;
			break;
		case 24:
			applicantTypeCode = ApplicantTypeCodeDataType.G_INDEPENDENT_SCHOOL_DISTRICT;
			break;
		case 25:
			applicantTypeCode = ApplicantTypeCodeDataType.L_PUBLIC_INDIAN_HOUSING_AUTHORITY;
			break;
		case 26:
			applicantTypeCode = ApplicantTypeCodeDataType.J_INDIAN_NATIVE_AMERICAN_TRIBAL_GOVERNMENT_OTHER_THAN_FEDERALLY_RECOGNIZED;
			break;
		default:
			applicantTypeCode = ApplicantTypeCodeDataType.X_OTHER_SPECIFY;
			break;
		}
		if (smallBusflag) {
			applicantType
					.setSmallBusinessOrganizationType(smallOrganizationType);
		}

		if (orgTypeCode == 3) {
			applicantType
					.setApplicantTypeCodeOtherExplanation("Federal Government");
		}
		applicantType.setApplicantTypeCode(applicantTypeCode);
		return applicantType;
	}

	private ProposalYnq getAnswer(String questionId,
			ProposalDevelopmentDocument proposalDevelopmentDocument) {
		String question;
		ProposalYnq ynq = null;
		for (ProposalYnq proposalYnq : proposalDevelopmentDocument
				.getDevelopmentProposal().getProposalYnqs()) {
			question = proposalYnq.getQuestionId();

			if (question != null && question.equals(questionId)) {
				ynq = proposalYnq;
				break;
			}
		}
		return ynq;
	}

	private String getSubmissionTypeCode() {
		String submissionTypeCode = null;
		S2sOpportunity s2sOpportunity = pdDoc.getDevelopmentProposal()
				.getS2sOpportunity();
		if (s2sOpportunity != null
				&& s2sOpportunity.getS2sSubmissionTypeCode() != null) {
			s2sOpportunity.refreshNonUpdateableReferences();
			submissionTypeCode = s2sOpportunity.getS2sSubmissionTypeCode();
		}
		return submissionTypeCode;
	}

	private String getRolodexState() {
		String state = "";
		Rolodex rolodex = pdDoc.getDevelopmentProposal()
				.getApplicantOrganization().getOrganization().getRolodex();
		if (rolodex != null) {
			state = rolodex.getState();
		}
		return state;
	}

	private String getEmployerId() {
		String employerId = "";
		ProposalSite applicantOrganization = pdDoc.getDevelopmentProposal()
				.getApplicantOrganization();
		if (applicantOrganization != null) {
			employerId = applicantOrganization.getOrganization()
					.getFedralEmployerId();
		}
		return employerId;
	}

	private String getFederalAgencyName() {
		String agencyName = "";
		Sponsor sponsor = pdDoc.getDevelopmentProposal().getSponsor();
		if (sponsor != null) {
			agencyName = sponsor.getSponsorName();
		}
		return agencyName;
	}

	private void setPreApplicationAttachment(RRSF42412 rrsf42412) {
		for (Narrative narrative : pdDoc.getDevelopmentProposal()
				.getNarratives()) {
			if (narrative.getNarrativeTypeCode() != null
					&& Integer.parseInt(narrative.getNarrativeTypeCode()) == PRE_APPLICATION) {
				AttachedFileDataType preAttachment = getAttachedFileType(narrative);
				if(preAttachment != null){
					rrsf42412.setPreApplicationAttachment(preAttachment);
					break;
				}
			}
		}
	}
	private void setSFLLLAttachment(RRSF42412 rrsf42412) {
		for (Narrative narrative : pdDoc.getDevelopmentProposal()
				.getNarratives()) {
			if (narrative.getNarrativeTypeCode() != null
					&& Integer.parseInt(narrative.getNarrativeTypeCode()) == SFLLL_OTHEREXPLANATORY) {
				AttachedFileDataType preAttachment = getAttachedFileType(narrative);
				if(preAttachment != null){
					rrsf42412.setSFLLLAttachment(preAttachment);
					break;
				}
			}
		}
	}

	private String getAORSignature() {
		String AORSignature = "";
		if (departmentalPerson != null) {
			AORSignature = departmentalPerson.getFullName();
		}
		return AORSignature;
	}

	private void setFederalId(RRSF42412 rrsf42412) {
		String federalId = s2sUtilService.getFederalId(pdDoc);
		if (federalId != null) {
			if (federalId.length() > 30) {
				rrsf42412.setFederalID(federalId.substring(0, 30));
			} else {
				rrsf42412.setFederalID(federalId);
			}
		}
	}

	private String getActivityTitle() {
		String announcementTitle = "";
		if (pdDoc.getDevelopmentProposal().getProgramAnnouncementTitle() != null) {
			if (pdDoc.getDevelopmentProposal().getProgramAnnouncementTitle()
					.length() > 120) {
				announcementTitle = pdDoc.getDevelopmentProposal()
						.getProgramAnnouncementTitle().substring(0, 120);
			} else {
				announcementTitle = pdDoc.getDevelopmentProposal()
						.getProgramAnnouncementTitle();
			}
		}
		return announcementTitle;
	}

	private String getProjectTitle() {
		String title = pdDoc.getDevelopmentProposal().getTitle();
		if (title != null && title.length() > 200) {
			title = title.substring(0, 200);
		}
		return title;
	}

	/**
	 * This method creates {@link XmlObject} of type {@link RRSF42412Document}
	 * by populating data from the given {@link ProposalDevelopmentDocument}
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
		departmentalPerson = s2sUtilService
				.getDepartmentalPerson(proposalDevelopmentDocument);
		return getRRSF424();
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
		RRSF42412 rrsf42412 = (RRSF42412) xmlObject;
		RRSF42412Document rrSF424Document = RRSF42412Document.Factory
				.newInstance();
		rrSF424Document.setRRSF42412(rrsf42412);
		return rrSF424Document;
	}
}