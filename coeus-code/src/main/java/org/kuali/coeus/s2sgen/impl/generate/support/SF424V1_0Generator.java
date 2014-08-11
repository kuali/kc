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

import gov.grants.apply.forms.rrSF424V10.RRSF424Document.RRSF424.StateReview;
import gov.grants.apply.forms.rrSF424V10.StateReviewCodeTypeDataType;
import gov.grants.apply.forms.sf424V10.AddressDocument.Address;
import gov.grants.apply.forms.sf424V10.*;
import gov.grants.apply.forms.sf424V10.AuthorizedRepresentativeDocument.AuthorizedRepresentative;
import gov.grants.apply.forms.sf424V10.BudgetDocument.Budget;
import gov.grants.apply.forms.sf424V10.ContactDocument.Contact;
import gov.grants.apply.forms.sf424V10.IndividualDocument.Individual;
import gov.grants.apply.forms.sf424V10.OrganizationDocument.Organization;
import gov.grants.apply.forms.sf424V10.OrganizationIdentifyingInformationDocument.OrganizationIdentifyingInformation;
import gov.grants.apply.forms.sf424V10.ProjectDocument.Project;
import gov.grants.apply.forms.sf424V10.RevisionDocument.Revision;
import gov.grants.apply.forms.sf424V10.SubmittingOrganizationDocument.SubmittingOrganization;
import gov.grants.apply.system.globalV10.YesNoType;
import gov.grants.apply.system.universalCodesV10.CountryCodeType;
import gov.grants.apply.system.universalCodesV10.CurrencyCodeType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.api.org.OrganizationYnqContract;
import org.kuali.coeus.common.budget.api.income.BudgetProjectIncomeContract;
import org.kuali.coeus.common.api.rolodex.RolodexContract;
import org.kuali.coeus.propdev.api.budget.ProposalDevelopmentBudgetExtContract;
import org.kuali.coeus.propdev.api.person.ProposalPersonContract;
import org.kuali.coeus.propdev.api.s2s.S2SConfigurationService;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.s2sgen.api.core.ConfigurationConstants;
import org.kuali.coeus.s2sgen.api.core.S2SException;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.person.DepartmentalPersonDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Map;

/**
 * This Class is used to generate XML object for grants.gov SF424V1.0. This form
 * is generated using XMLBean classes and is based on SF424-V1.0 schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@FormGenerator("SF424V1_0Generator")
public class SF424V1_0Generator extends SF424BaseGenerator {

	private static final Log LOG = LogFactory
			.getLog(SF424V1_0Generator.class);
	private DepartmentalPersonDto aorInfo;
	private String stateReviewDate = null;

    @Autowired
    @Qualifier("s2SConfigurationService")
    protected S2SConfigurationService s2SConfigurationService;

    @Value("http://apply.grants.gov/forms/SF424-V1.0")
    private String namespace;

    @Value("SF424-V1.0")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/SF424-V1.0.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.sf424V10")
    private String packageName;

    @Value(DEFAULT_SORT_INDEX)
    private int sortIndex;

	/**
	 * 
	 * This method returns GrantApplicationDocument object based on proposal
	 * development document which contains the GrantApplication information for
	 * a particular proposal
	 *
	 * @return grantApplicationDocument {@link XmlObject} of type
	 *         GrantApplicationDocument.
	 */
	private GrantApplicationDocument getGrantApplication() {

		GrantApplicationDocument grantApplicationDocument = GrantApplicationDocument.Factory
				.newInstance();
		grantApplicationDocument.setGrantApplication(getGrantApplicationType());
		return grantApplicationDocument;
	}

	/**
	 * 
	 * This method gets GrantApplicationType for the form . GrantApplicationType
	 * includes information regarding SubmissionTypeCode
	 * ApplicationTypeCode,Revision,AgencyName,StateID,CFDANumber,SubmittingOrganization,AuthorizedRepresentative.
	 *
	 * @return GrantApplicationType application details.
	 */
	private GrantApplicationType getGrantApplicationType() {

		GrantApplicationType grantApplicationType = GrantApplicationType.Factory
				.newInstance();
		grantApplicationType
				.setFormVersionIdentifier(FormVersion.v1_0.getVersion());
		// Set default values for mandatory fields
		grantApplicationType.setAgencyName("");

		String submissionTypeCode = getSF424SubmissionType(pdDoc);
		if (submissionTypeCode != null) {
			SubmissionTypeCodeType.Enum submissionType = SubmissionTypeCodeType.Enum
					.forString(submissionTypeCode);
			grantApplicationType.setSubmissionTypeCode(submissionType);
		}
		grantApplicationType.setSubmittedDate(Calendar.getInstance());
		ApplicationTypeCodeType.Enum applicationTypeCodeDataType = null;
		if (pdDoc.getDevelopmentProposal().getProposalType() != null) {
			int proposalTypeCode = Integer.parseInt(pdDoc
					.getDevelopmentProposal().getProposalType().getCode());
			if (proposalTypeCode < Integer.parseInt(s2SConfigurationService.getValueAsString(
                    ConfigurationConstants.PROPOSAL_TYPE_CODE_RESUBMISSION))) {
				applicationTypeCodeDataType = ApplicationTypeCodeType.Enum
						.forInt(proposalTypeCode);
			}
		}
		grantApplicationType
				.setApplicationTypeCode(applicationTypeCodeDataType);
		if (pdDoc.getDevelopmentProposal().getS2sOpportunity() != null
				&& pdDoc.getDevelopmentProposal().getS2sOpportunity()
						.getS2sSubmissionType() != null) {

			String revisionCode = pdDoc.getDevelopmentProposal()
					.getS2sOpportunity().getS2sRevisionType().getCode();
			if (revisionCode != null) {
				Revision revision = Revision.Factory.newInstance();
				String revision1 = null;
				String revision2 = null;
				revision1 = revisionCode.substring(0, 1);
				if (revisionCode.length() > 1) {
					revision2 = revisionCode.substring(1, 2);
				}
				if (revision1 != null && !revision1.equals(OTHER_SPECIFY_CODE)) {
					RevisionCodeType.Enum rev1Enum = RevisionCodeType.Enum
							.forString(revision1);
					revision.setRevisionCode1(rev1Enum);
				}
				if (revision2 != null) {
					RevisionCodeType.Enum rev2Enum = RevisionCodeType.Enum
							.forString(revision2);
					revision.setRevisionCode2(rev2Enum);
				}
				grantApplicationType.setRevision(revision);
			}
		}
		if (pdDoc.getDevelopmentProposal().getSponsor() != null) {
			grantApplicationType.setAgencyName(pdDoc.getDevelopmentProposal()
					.getSponsor().getSponsorName());
		}
        RolodexContract rolodex = pdDoc.getDevelopmentProposal()
				.getApplicantOrganization().getRolodex();
		if (rolodex != null) {
			grantApplicationType.setStateID(pdDoc.getDevelopmentProposal()
					.getApplicantOrganization().getRolodex().getState());
		}
		String federalId = getSubmissionInfoService().getFederalId(pdDoc.getDevelopmentProposal().getProposalNumber());
		if (federalId != null) {
			grantApplicationType.setFederalID(federalId);
		}
		if(pdDoc.getDevelopmentProposal().getCfdaNumber()!=null){
		    grantApplicationType.setCFDANumber(pdDoc.getDevelopmentProposal().getCfdaNumber());
		}
		if (pdDoc.getDevelopmentProposal().getProgramAnnouncementTitle() != null) {
			String announcementTitle;
			if (pdDoc.getDevelopmentProposal().getProgramAnnouncementTitle()
					.length() > PROGRAM_ANNOUNCEMENT_TITLE_LENGTH) {
				// announcementTitle contains sub string value of
				// ProgramAnnouncementTitle
				announcementTitle = pdDoc.getDevelopmentProposal()
						.getProgramAnnouncementTitle().substring(0,
								PROGRAM_ANNOUNCEMENT_TITLE_LENGTH);
			} else {
				announcementTitle = pdDoc.getDevelopmentProposal()
						.getProgramAnnouncementTitle();
			}
			grantApplicationType.setActivityTitle(announcementTitle);
		}
		grantApplicationType
				.setSubmittingOrganization(getSubmittingOrganization());
		grantApplicationType.setProject(getProject());
		try {
			grantApplicationType.setBudget(getBudget());
		} catch (S2SException e) {
			LOG.error(e.getMessage(), e);
			return grantApplicationType;
		}
		Individual individual = Individual.Factory.newInstance();
		individual.setAuthorizedRepresentative(getAuthorizedRepresentative());
		individual.setContact(getContact());
		grantApplicationType.setIndividual(individual);
		grantApplicationType.setStateReviewCode(getStateReviewCode());
		if (stateReviewDate != null) {
			grantApplicationType.setStateReviewDate(s2SDateTimeService.convertDateStringToCalendar(stateReviewDate));
		}
		grantApplicationType.setAuthorizedRepresentativeSignature(aorInfo
				.getFullName());

		grantApplicationType.setSignedDate(Calendar.getInstance());
		grantApplicationType.setCoreSchemaVersion(CORE_SCHEMA_VERSION_1_0);
		return grantApplicationType;
	}

	/**
	 * 
	 * This method returns StateReviewCode status for the
	 * application.StateReviewCode can be Not covered,Not reviewed
	 *
	 * @return stateType (StateReviewCodeType.Enum) revision details.
	 */
    private StateReviewCodeType.Enum getStateReviewCode() {
        
        Map<String, String> eoStateReview = getEOStateReview(pdDoc);
        StateReviewCodeType.Enum stateType = null;
        StateReviewCodeTypeDataType.Enum stateReviewCodeType = null;
        String strReview = eoStateReview.get(YNQ_ANSWER);
        String stateReviewData = null;

        if (STATE_REVIEW_YES.equals(strReview)) {
            stateType = StateReviewCodeType.YES;
        }
        else {
            stateReviewData = eoStateReview.get(YNQ_STATE_REVIEW_DATA);
            if (stateReviewData != null && StateReviewCodeType.NOT_COVERED.toString().equals(stateReviewData)) {
                stateType = StateReviewCodeType.NOT_COVERED;
                stateReviewCodeType = StateReviewCodeTypeDataType.PROGRAM_IS_NOT_COVERED_BY_E_O_12372;
            }
            else if (stateReviewData != null && YNQ_STATE_NOT_SELECTED.equals(stateReviewData)) {
                stateType = StateReviewCodeType.NOT_REVIEWED;
                stateReviewCodeType = StateReviewCodeTypeDataType.PROGRAM_HAS_NOT_BEEN_SELECTED_BY_STATE_FOR_REVIEW;
            }
        }

        if (eoStateReview.get(YNQ_REVIEW_DATE) != null) {
            stateReviewDate = eoStateReview.get(YNQ_REVIEW_DATE);
        }

        StateReview stateReview = StateReview.Factory.newInstance();
        stateReview.setStateReviewCodeType(stateReviewCodeType);
        return stateType;
    }

	/**
	 * 
	 * This method returns the AuthorizedRepresentative details such as
	 * FirstName,MiddleName,LastName,EmailAddress TelephoneNumber,FaxNumber and
	 * RepresentativeTitle
	 *
	 * @return AuthorizedRepresentative authorized representative details.
	 */
	private AuthorizedRepresentative getAuthorizedRepresentative() {

		AuthorizedRepresentative authorizedRep = AuthorizedRepresentative.Factory
				.newInstance();
		if (aorInfo.getFirstName() != null) {
			authorizedRep.setGivenName1(aorInfo.getFirstName());
		}
		if (aorInfo.getMiddleName() != null) {
			authorizedRep.setGivenName2(aorInfo.getMiddleName());
		}
		if (aorInfo.getLastName() != null) {
			authorizedRep.setFamilyName(aorInfo.getLastName());
		}
		if (aorInfo.getEmailAddress() != null) {
			authorizedRep.setElectronicMailAddress(aorInfo.getEmailAddress());
		}
		if (aorInfo.getOfficePhone() != null) {
			authorizedRep.setTelephoneNumber(aorInfo.getOfficePhone());
		}
		if (aorInfo.getFaxNumber() != null) {
			authorizedRep.setFaxNumber(aorInfo.getFaxNumber());
		}

		if (aorInfo.getPrimaryTitle() != null) {
			if (aorInfo.getPrimaryTitle().length() > PRIMARY_TITLE_MAX_LENGTH) {
				authorizedRep.setRepresentativeTitle(aorInfo.getPrimaryTitle()
						.substring(0, PRIMARY_TITLE_MAX_LENGTH));
			} else {
				authorizedRep.setRepresentativeTitle(aorInfo.getPrimaryTitle());
			}
		}
		return authorizedRep;
	}

	/**
	 * 
	 * This method get budget informations.Budget informations includes
	 * FederalEstimatedAmount,LocalEstimatedAmount
	 * ProgramIncomeEstimatedAmount,OtherEstimatedAmount and
	 * TotalEstimatedAmount
	 *
	 * @return Budget total estimated budget details.
	 * @throws S2SException
	 */
	private Budget getBudget() throws S2SException {

		Budget budget = Budget.Factory.newInstance();
		CurrencyCodeType.Enum currencyEnum = CurrencyCodeType.USD;
		budget.setCurrencyCode(currencyEnum);
		budget.setFederalEstimatedAmount(BigDecimal.ZERO);
		budget.setTotalEstimatedAmount(BigDecimal.ZERO);

        ProposalDevelopmentBudgetExtContract pBudget = s2SCommonBudgetService.getBudget(pdDoc.getDevelopmentProposal());

        if (pBudget != null) {
			budget.setFederalEstimatedAmount(pBudget.getTotalCost()
					.bigDecimalValue());
			budget.setApplicantEstimatedAmount(pBudget.getCostSharingAmount()
					.bigDecimalValue());
			// Following values hardcoded as in coeus
			budget.setStateEstimatedAmount(BigDecimal.ZERO);
			budget.setLocalEstimatedAmount(BigDecimal.ZERO);
			budget.setOtherEstimatedAmount(BigDecimal.ZERO);
			BigDecimal projectIncome = BigDecimal.ZERO;
			for (BudgetProjectIncomeContract budgetProjectIncome : pBudget
					.getBudgetProjectIncomes()) {
				if (budgetProjectIncome.getProjectIncome() != null) {
					projectIncome = projectIncome.add(budgetProjectIncome
							.getProjectIncome().bigDecimalValue());
				}
			}
			budget.setProgramIncomeEstimatedAmount(projectIncome);
			ScaleTwoDecimal totalEstimatedAmount = ScaleTwoDecimal.ZERO;
			if (pBudget.getTotalCost() != null) {
				totalEstimatedAmount = totalEstimatedAmount.add(pBudget
						.getTotalCost());
			}
			if (pBudget.getCostSharingAmount() != null) {
				totalEstimatedAmount = totalEstimatedAmount.add(pBudget
						.getCostSharingAmount());
			}
			budget.setTotalEstimatedAmount(totalEstimatedAmount
					.bigDecimalValue().add(projectIncome));
		}
		return budget;
	}

	/**
	 * 
	 * This method gets AuthorizedRepresentative (principal investigator)
	 * contact informations which includes FirstName
	 * MiddleName,LastName,EmailAddress,TelephoneNumber and FaxNumber
	 *
	 * @return Contact principal investigator contact details.
	 */
	private Contact getContact() {

		Contact contact = Contact.Factory.newInstance();
		ProposalPersonContract PI = s2SProposalPersonService.getPrincipalInvestigator(pdDoc);
		if (PI != null) {
			if (PI.getFirstName() != null) {
				contact.setGivenName1(PI.getFirstName());
			}
			if (PI.getMiddleName() != null) {
				contact.setGivenName2(PI.getMiddleName());
			}
			if (PI.getLastName() != null) {
				contact.setFamilyName(PI.getLastName());
			}
			if (PI.getEmailAddress() != null) {
				contact.setElectronicMailAddress(PI.getEmailAddress());
			}
			if (PI.getOfficePhone() != null) {
				contact.setTelephoneNumber(PI.getOfficePhone());
			}
			if (PI.getFaxNumber() != null) {
				contact.setFaxNumber(PI.getFaxNumber());
			}
		}
		return contact;
	}

	/**
	 * 
	 * This method gets all the informations related to the project. Project
	 * informations are ProjectTitle,Location, ProposedStartDate,ProposedEndDate
	 * and CongressionalDistrict.
	 *
	 * @return project (Project)
	 */
	private Project getProject() {

		Project project = Project.Factory.newInstance();
		project.setProjectTitle(pdDoc.getDevelopmentProposal().getTitle());
        RolodexContract rolodex = pdDoc.getDevelopmentProposal()
				.getApplicantOrganization().getRolodex();
		if (rolodex != null) {
			project.setLocation(pdDoc.getDevelopmentProposal()
					.getApplicantOrganization().getRolodex().getState());
		}
		project.setProposedStartDate(s2SDateTimeService.convertDateToCalendar(pdDoc
				.getDevelopmentProposal().getRequestedStartDateInitial()));
		project.setProposedEndDate(s2SDateTimeService.convertDateToCalendar(pdDoc
				.getDevelopmentProposal().getRequestedEndDateInitial()));

		if (pdDoc.getDevelopmentProposal().getPerformingOrganization() != null) {
			project.setCongressionalDistrict(pdDoc.getDevelopmentProposal()
					.getPerformingOrganization()
					.getFirstCongressionalDistrictName());
		} else {
			project.setCongressionalDistrict("");
		}
		return project;
	}

	/**
	 * 
	 * Gets the information about the Submitting Organization.This includes
	 * details like CongressionalDistrict
	 * DelinquentFederalDebtIndicator,OrganizationName,DUNSID,DepartmentName,DivisionName,ApplicantID,ApplicantTypeCode
	 * Organization details,OrganizationIdentifyingInformation and Address
	 *
	 * @return submittingOrganization(SubmittingOrganization) organization
	 *         details.
	 */
	private SubmittingOrganization getSubmittingOrganization() {
		SubmittingOrganization submittingOrganization = SubmittingOrganization.Factory
				.newInstance();
		if (pdDoc.getDevelopmentProposal().getApplicantOrganization() != null) {
			submittingOrganization.setCongressionalDistrict(pdDoc
					.getDevelopmentProposal().getApplicantOrganization()
					.getFirstCongressionalDistrictName());
		}
		YesNoType.Enum yesNo = YesNoType.N;
		for (OrganizationYnqContract orgYnq : pdDoc.getDevelopmentProposal()
				.getApplicantOrganization().getOrganization()
				.getOrganizationYnqs()) {
			if (PROPOSAL_YNQ_FEDERAL_DEBTS.equals(orgYnq.getQuestionId())) {
				if (orgYnq.getAnswer() != null) {
					yesNo = YesNoType.Enum.forString(orgYnq.getAnswer());
				}
			}
		}
		submittingOrganization.setDelinquentFederalDebtIndicator(yesNo);
		Organization organization = Organization.Factory.newInstance();
		if (pdDoc.getDevelopmentProposal().getApplicantOrganization() != null) {
			organization.setOrganizationName(pdDoc.getDevelopmentProposal()
					.getApplicantOrganization().getOrganization()
					.getOrganizationName());
		}
		if (pdDoc.getDevelopmentProposal().getApplicantOrganization() != null) {
			organization.setDUNSID(pdDoc.getDevelopmentProposal()
					.getApplicantOrganization().getOrganization()
					.getDunsNumber());
		}
		String departmentName = "";
		if (pdDoc.getDevelopmentProposal().getOwnedByUnit() != null) {
			departmentName = pdDoc.getDevelopmentProposal().getOwnedByUnit()
					.getUnitName();
		}
		if (departmentName != null
				&& departmentName.length() > DEPARTMENT_NAME_MAX_LENGTH) {
			organization.setDepartmentName(departmentName.substring(0,
					DEPARTMENT_NAME_MAX_LENGTH));
		} else {
			organization.setDepartmentName(departmentName);
		}
		String divisionName = getDivisionName(pdDoc);
		if (divisionName != null) {
			organization.setDivisionName(divisionName);
		}
		if (pdDoc.getDevelopmentProposal().getApplicantOrganization() != null) {
			organization.setEmployerID(pdDoc.getDevelopmentProposal()
					.getApplicantOrganization().getOrganization()
					.getFederalEmployerId());
		}
		OrganizationIdentifyingInformation orgIdentifyingInformation = OrganizationIdentifyingInformation.Factory
				.newInstance();
		orgIdentifyingInformation.setApplicantID(pdDoc.getDevelopmentProposal()
				.getProposalNumber());
		orgIdentifyingInformation.setApplicantTypeCode(getApplicantType());
		orgIdentifyingInformation.setOrganization(organization);
		submittingOrganization
				.setOrganizationIdentifyingInformation(orgIdentifyingInformation);
		Address address = Address.Factory.newInstance();
		// Set default values for mandatory fields
		address.setStreet1("");
		address.setCity("");

        RolodexContract rolodex = null;
		if (pdDoc.getDevelopmentProposal().getPerformingOrganization() != null) {
			rolodex = pdDoc.getDevelopmentProposal()
					.getPerformingOrganization().getRolodex();
			if (rolodex != null) {
				address.setStreet1(rolodex.getAddressLine1() == null ? ""
						: rolodex.getAddressLine1());
				address.setStreet2(rolodex.getAddressLine2());
				address.setCity(rolodex.getCity() == null ? "" : rolodex
						.getCity());
				address.setCounty(rolodex.getCounty());
				address.setStateCode(rolodex.getState());
				address.setZipCode(rolodex.getPostalCode());
				if (rolodex.getCountryCode() != null) {
					CountryCodeType.Enum countryEnum = CountryCodeType.Enum
							.forString(rolodex.getCountryCode());
					address.setCountry(countryEnum);
				}
				submittingOrganization.setAddress(address);
			}
		}

		submittingOrganization.setAddress(address);
		return submittingOrganization;
	}

	/**
	 * 
	 * Gets the Applicant type code information for the particular applicant.It
	 * returns enumeration value for the code such as State
	 * Government,Non-profit Organization,Native American Tribal Government etc.
	 *
	 * @return applicantTypeCode(ApplicantTypeCodeType.Enum) corresponding to
	 *         the organization type code.
	 */
	private ApplicantTypeCodeType.Enum getApplicantType() {

		ApplicantTypeCodeType.Enum applicantTypeCode = null;
		int orgTypeCode = 0;
		if (pdDoc.getDevelopmentProposal().getApplicantOrganization() != null
				&& pdDoc.getDevelopmentProposal().getApplicantOrganization()
						.getOrganization().getOrganizationTypes() != null
				&& pdDoc.getDevelopmentProposal().getApplicantOrganization()
						.getOrganization().getOrganizationTypes().size() > 0) {
			orgTypeCode = pdDoc.getDevelopmentProposal()
					.getApplicantOrganization().getOrganization()
					.getOrganizationTypes().get(0).getOrganizationTypeList().getCode()
					.intValue();
		}
		switch (orgTypeCode) {
		case 1: {
			applicantTypeCode = ApplicantTypeCodeType.CITY_OR_TOWNSHIP_GOVERNMENT;
			break;
		}
		case 2: {
			applicantTypeCode = ApplicantTypeCodeType.STATE_GOVERNMENT;
			break;
		}
		case 3: {
			applicantTypeCode = ApplicantTypeCodeType.OTHER;
			break;
		}
		case 4: {
			applicantTypeCode = ApplicantTypeCodeType.NONPROFIT_ORGANIZATION_OTHER_THAN_INSTITUTION_OF_HIGHER_EDUCATION_OTHER_SPECIFY;
			break;
		}
		case 5: {
			applicantTypeCode = ApplicantTypeCodeType.NONPROFIT_ORGANIZATION_OTHER_THAN_INSTITUTION_OF_HIGHER_EDUCATION_OTHER_SPECIFY;
			break;
		}
		case 6: {
			applicantTypeCode = ApplicantTypeCodeType.FOR_PROFIT_ORGANIZATION_OTHER_THAN_SMALL_BUSINESS;
			break;
		}
		case 7: {
			applicantTypeCode = ApplicantTypeCodeType.OTHER;
			break;
		}
		case 8: {
			applicantTypeCode = ApplicantTypeCodeType.NATIVE_AMERICAN_TRIBAL_GOVERNMENT_FEDERALLY_RECOGNIZED;
			break;
		}
		case 9: {
			applicantTypeCode = ApplicantTypeCodeType.INDIVIDUAL;
			break;
		}
		case 10: {
			applicantTypeCode = ApplicantTypeCodeType.PRIVATE_INSTITUTION_OF_HIGHER_EDUCATION;
			break;
		}
		case 11: {
			applicantTypeCode = ApplicantTypeCodeType.SMALL_BUSINESS;
			break;
		}
		case 21: {
			applicantTypeCode = ApplicantTypeCodeType.PUBLIC_STATE_CONTROLLED_INSTITUTION_OF_HIGHER_EDUCATION;
			break;
		}
		case 22: {
			applicantTypeCode = ApplicantTypeCodeType.COUNTY_GOVERNMENT;
			break;
		}
		case 23: {
			applicantTypeCode = ApplicantTypeCodeType.SPECIAL_DISTRICT;
			break;
		}
		case 24: {
			applicantTypeCode = ApplicantTypeCodeType.INDEPENDENT_SCHOOL_DISTRICT;
			break;
		}
		case 25: {
			applicantTypeCode = ApplicantTypeCodeType.PUBLIC_INDIAN_HOUSING_AUTHORITY;
			break;
		}
		case 26: {
			applicantTypeCode = ApplicantTypeCodeType.NATIVE_AMERICAN_TRIBAL_ORGANIZATION_OTHER_THAN_FEDERALLY_RECOGNIZED;
			break;
		}
		default: {
			applicantTypeCode = ApplicantTypeCodeType.OTHER;
			break;
		}
		}
		return applicantTypeCode;
	}

	/**
	 * 
	 * This method returns Submission type details for the Submission type.It
	 * returns enumeration value for the subission type. Submission type can be
	 * Construction,Non construction, Application, Pre application.
	 * 
	 * @param pdDoc
	 *            (ProposalDevelopmentDocumentContract)
	 * @return submissionType(String) corresponding to submission type code.
	 */
	private String getSF424SubmissionType(ProposalDevelopmentDocumentContract pdDoc) {

		String submissionType = null;
		String suffix;

		if (s2SConfigurationService.getValueAsString(
                ConfigurationConstants.ACTIVITY_TYPE_CODE_CONSTRUCTION).equals(pdDoc
				.getDevelopmentProposal().getActivityType().getCode())) {
			suffix = ACTIVITY_TYPE_CODE_LS_SUFFIX_CONSTRUCTION;
		} else {
			suffix = ACTIVITY_TYPE_CODE_LS_SUFFIX_NONCONSTRUCTION;
		}
		if (pdDoc.getDevelopmentProposal().getS2sOpportunity() != null
				&& pdDoc.getDevelopmentProposal().getS2sOpportunity()
						.getS2sSubmissionType() != null) {
			if (s2SConfigurationService.getValueAsString(
                    ConfigurationConstants.S2S_SUBMISSION_TYPE_CODE_PREAPPLICATION).equals(
			                pdDoc.getDevelopmentProposal().getS2sOpportunity()
					.getS2sSubmissionType().getCode())) {
				submissionType = ACTIVITY_TYPE_CODE_LS_SUFFIX_PREAPPLICATION
						+ suffix;
			} else {
				submissionType = ACTIVITY_TYPE_CODE_LS_SUFFIX_APPLICATION
						+ suffix;
			}
		}
		return submissionType;
	}

	/**
	 * This method creates {@link XmlObject} of type
	 * {@link GrantApplicationDocument} by populating data from the given
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
		aorInfo = departmentalPersonService.getDepartmentalPerson(pdDoc);
		return getGrantApplication();
	}

    public S2SConfigurationService getS2SConfigurationService() {
        return s2SConfigurationService;
    }

    public void setS2SConfigurationService(S2SConfigurationService s2SConfigurationService) {
        this.s2SConfigurationService = s2SConfigurationService;
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
