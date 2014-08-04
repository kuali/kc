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

import gov.grants.apply.forms.rrSF424V10.*;
import gov.grants.apply.forms.rrSF424V10.RRSF424Document.RRSF424;
import gov.grants.apply.forms.rrSF424V10.RRSF424Document.RRSF424.*;
import gov.grants.apply.forms.rrSF424V10.RRSF424Document.RRSF424.ApplicantInfo.ContactPersonInfo;
import gov.grants.apply.forms.rrSF424V10.RRSF424Document.RRSF424.ApplicantType.SmallBusinessOrganizationType;
import gov.grants.apply.forms.rrSF424V10.RRSF424Document.RRSF424.ApplicantType.SmallBusinessOrganizationType.IsSociallyEconomicallyDisadvantaged;
import gov.grants.apply.forms.rrSF424V10.RRSF424Document.RRSF424.ApplicantType.SmallBusinessOrganizationType.IsWomenOwned;
import gov.grants.apply.forms.rrSF424V10.RRSF424Document.RRSF424.ApplicationType.OtherAgencySubmissionExplanation;
import gov.grants.apply.forms.rrSF424V10.RRSF424Document.RRSF424.ApplicationType.RevisionCode;
import gov.grants.apply.forms.rrSF424V10.RRSF424Document.RRSF424.ApplicationType.RevisionCodeOtherExplanation;
import gov.grants.apply.forms.sf424V10.StateReviewCodeType;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.globalLibraryV10.OrganizationDataTypeV2;
import gov.grants.apply.system.globalLibraryV10.YesNoDataType;
import gov.grants.apply.system.globalLibraryV10.YesNoDataType.Enum;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.api.org.OrganizationContract;
import org.kuali.coeus.common.api.person.KcPersonContract;
import org.kuali.coeus.common.api.ynq.YnqConstant;
import org.kuali.coeus.common.questionnaire.api.answer.AnswerHeaderContract;
import org.kuali.coeus.common.api.rolodex.RolodexService;
import org.kuali.coeus.common.api.ynq.YnqContract;
import org.kuali.coeus.common.budget.api.income.BudgetProjectIncomeContract;
import org.kuali.coeus.common.budget.api.period.BudgetPeriodContract;
import org.kuali.coeus.common.api.rolodex.RolodexContract;
import org.kuali.coeus.common.api.sponsor.SponsorContract;
import org.kuali.coeus.propdev.api.budget.ProposalDevelopmentBudgetExtContract;
import org.kuali.coeus.propdev.api.budget.modular.BudgetModularIdcContract;
import org.kuali.coeus.propdev.api.core.DevelopmentProposalContract;
import org.kuali.coeus.propdev.api.location.ProposalSiteContract;
import org.kuali.coeus.propdev.api.person.ProposalPersonContract;
import org.kuali.coeus.propdev.api.s2s.S2sOpportunityContract;
import org.kuali.coeus.propdev.api.ynq.ProposalYnqContract;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.s2sgen.api.core.S2SException;
import org.kuali.coeus.propdev.api.attachment.NarrativeContract;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.person.DepartmentalPersonDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Class for generating the XML object for grants.gov RRSF424V1_0. Form is
 * generated using XMLBean classes and is based on RRSF424V1_0 schema.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@FormGenerator("RRSF424V1_0Generator")
public class RRSF424V1_0Generator extends RRSF424BaseGenerator {

    private List<? extends AnswerHeaderContract> answerHeaders;
	private DepartmentalPersonDto departmentalPerson;
	private static final Log LOG = LogFactory
			.getLog(RRSF424V1_0Generator.class);

    @Value("http://apply.grants.gov/forms/RR_SF424-V1.0")
    private String namespace;

    @Value("RR_SF424-V1.0")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/RR_SF424-V1.0.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.rrSF424V10")
    private String packageName;

    @Value("120")
    private int sortIndex;

    @Autowired
    @Qualifier("rolodexService")
    private RolodexService rolodexService;

	/**
	 *
	 * This method gives information of applications that are used in RRSF424
	 *
	 * @return rrSF424Document {@link XmlObject} of type RRSF424Document.
	 */
	private RRSF424Document getRRSF424() {
        answerHeaders = getPropDevQuestionAnswerService().getQuestionnaireAnswerHeaders(pdDoc.getDevelopmentProposal().getProposalNumber());
		RRSF424Document rrSF424Document = RRSF424Document.Factory.newInstance();
		RRSF424 rrsf424 = RRSF424.Factory.newInstance();
		rrsf424.setFormVersion(FormVersion.v1_0.getVersion());
		S2sOpportunityContract s2sOpportunity = pdDoc.getDevelopmentProposal()
				.getS2sOpportunity();
		if (s2sOpportunity != null
				&& s2sOpportunity.getS2sSubmissionType() != null) {
			rrsf424.setSubmissionTypeCode(SubmissionTypeDataType.Enum
					.forString(pdDoc.getDevelopmentProposal()
							.getS2sOpportunity().getS2sSubmissionType()
							.getDescription()));
		}
		rrsf424.setSubmittedDate(Calendar.getInstance());
        RolodexContract rolodex = pdDoc.getDevelopmentProposal()
				.getApplicantOrganization().getRolodex();
		if (rolodex != null) {
			rrsf424.setStateID(rolodex.getState());
		}
		String federalId = getSubmissionInfoService().getFederalId(pdDoc.getDevelopmentProposal().getProposalNumber());
		if (federalId != null) {
			if (federalId.length() > 30) {
				rrsf424.setFederalID(federalId.substring(0, 30));
			} else {
				rrsf424.setFederalID(federalId);
			}
		}
		rrsf424.setApplicantInfo(getApplicationInfo());
		rrsf424.setApplicantType(getApplicantType());
		rrsf424.setApplicationType(getApplicationType());
		OrganizationContract organization = pdDoc.getDevelopmentProposal()
				.getApplicantOrganization().getOrganization();
		boolean isNih  = isSponsorInHierarchy(pdDoc.getDevelopmentProposal(), SPONSOR_GROUPS,SPONSOR_NIH);
		if (organization != null) {
		    if (organization.getPhsAccount() != null  && isNih) {
                rrsf424.setEmployerID(organization.getPhsAccount());
            } else {
                rrsf424.setEmployerID(organization.getFederalEmployerId());
            }
		}
		SponsorContract sponsor = pdDoc.getDevelopmentProposal().getSponsor();
		if (sponsor != null) {
			rrsf424.setFederalAgencyName(sponsor.getSponsorName());
		}
		if(pdDoc.getDevelopmentProposal().getCfdaNumber()!=null){
	        rrsf424.setCFDANumber(pdDoc.getDevelopmentProposal().getCfdaNumber());
		}
		if (pdDoc.getDevelopmentProposal().getProgramAnnouncementTitle() != null) {
			String announcementTitle;
			if (pdDoc.getDevelopmentProposal().getProgramAnnouncementTitle()
					.length() > PROGRAM_ANNOUNCEMENT_TITLE_MAX_LENGTH) {
				announcementTitle = pdDoc.getDevelopmentProposal()
						.getProgramAnnouncementTitle().substring(0,
								PROGRAM_ANNOUNCEMENT_TITLE_MAX_LENGTH);
			} else {
				announcementTitle = pdDoc.getDevelopmentProposal()
						.getProgramAnnouncementTitle();
			}
			rrsf424.setActivityTitle(announcementTitle);
		}
		rrsf424.setProjectTitle(pdDoc.getDevelopmentProposal().getTitle());
		ProposalSiteContract performingOrganization = pdDoc.getDevelopmentProposal()
				.getPerformingOrganization();
		if (performingOrganization.getOrganization() != null) {
            RolodexContract rolodexOrganization = rolodexService.getRolodex(performingOrganization
                    .getOrganization().getContactAddressId());
			if (rolodexOrganization != null) {
				rrsf424.setLocation(rolodexOrganization.getState());
			}
		}

		rrsf424.setProposedProjectPeriod(getProjectPeriod());
		rrsf424.setCongressionalDistrict(getCongDistrict());
		rrsf424.setPDPIContactInfo(getPDPI());
		try {
			rrsf424.setEstimatedProjectFunding(getProjectFunding());
		} catch (S2SException e) {
			LOG.error(e.getMessage(), e);
			return rrSF424Document;
		}
		rrsf424.setStateReview(getStateReview());
		// Value is hardcoded
		rrsf424.setTrustAgree(YesNoDataType.YES);
		rrsf424.setAORInfo(getAORInfoType());
		for (NarrativeContract narrative : pdDoc.getDevelopmentProposal()
				.getNarratives()) {
			if (narrative.getNarrativeType().getCode() != null
					&& Integer.parseInt(narrative.getNarrativeType().getCode()) == PRE_APPLICATION) {
				AttachedFileDataType preAttachment = getAttachedFileType(narrative);
				if(preAttachment != null){
					rrsf424.setPreApplicationAttachment(preAttachment);
					break;
				}
			}
		}
		if (departmentalPerson != null) {
			rrsf424.setAORSignature(departmentalPerson.getFullName());
		} else {
			rrsf424.setAORSignature("");
		}
		rrsf424.setAORSignedDate(Calendar.getInstance());
		rrSF424Document.setRRSF424(rrsf424);
		return rrSF424Document;
	}

	/**
	 *
	 * This method is to get estimated project funds for RRSF424
	 *
	 * @return EstimatedProjectFunding estimated total cost for the project.
	 * @throws S2SException
	 */
	private EstimatedProjectFunding getProjectFunding() throws S2SException {
        ProposalDevelopmentBudgetExtContract budget = s2SCommonBudgetService.getBudget(pdDoc.getDevelopmentProposal());

		EstimatedProjectFunding funding = EstimatedProjectFunding.Factory
				.newInstance();
		funding.setTotalEstimatedAmount(BigDecimal.ZERO);
		funding.setTotalfedNonfedrequested(BigDecimal.ZERO);
		funding.setEstimatedProgramIncome(BigDecimal.ZERO);

        ScaleTwoDecimal totalCost = ScaleTwoDecimal.ZERO;
		if (budget != null) {
			if (budget.getModularBudgetFlag()) {
				ScaleTwoDecimal fundsRequested = ScaleTwoDecimal.ZERO;
				ScaleTwoDecimal totalDirectCost = ScaleTwoDecimal.ZERO;

				// get modular budget amounts instead of budget detail amounts
				for (BudgetPeriodContract budgetPeriod : budget.getBudgetPeriods()) {
					totalDirectCost = totalDirectCost.add(budgetPeriod
							.getBudgetModular().getTotalDirectCost());
					for (BudgetModularIdcContract budgetModularIdc : budgetPeriod
							.getBudgetModular().getBudgetModularIdcs()) {
						fundsRequested = fundsRequested.add(budgetModularIdc
								.getFundsRequested());
					}
				}
				totalCost = totalCost.add(totalDirectCost);
				totalCost = totalCost.add(fundsRequested);
			}

			ScaleTwoDecimal fedNonFedCost = ScaleTwoDecimal.ZERO;
			fedNonFedCost = fedNonFedCost.add(totalCost);
			fedNonFedCost = fedNonFedCost.add(budget.getCostSharingAmount());


			BigDecimal totalProjectIncome = BigDecimal.ZERO;
			for (BudgetProjectIncomeContract budgetProjectIncome : budget
					.getBudgetProjectIncomes()) {
				if (budgetProjectIncome.getProjectIncome() != null) {
					totalProjectIncome = totalProjectIncome
							.add(budgetProjectIncome.getProjectIncome()
									.bigDecimalValue());
				}
			}
			funding.setTotalEstimatedAmount(totalCost
					.bigDecimalValue());
			funding.setTotalfedNonfedrequested(fedNonFedCost.bigDecimalValue());
			funding.setEstimatedProgramIncome(totalProjectIncome);
		}
		return funding;
	}

	/**
	 *
	 * This method gives the information for an application which consists of
	 * personal details
	 *
	 * @return ApplicantInfo applicant details.
	 */
	private ApplicantInfo getApplicationInfo() {
		ApplicantInfo appInfo = ApplicantInfo.Factory.newInstance();
		String contactType = getContactType();
		if (CONTACT_TYPE_I.equals(contactType)) {
			// use organization rolodex contact
			if (pdDoc.getDevelopmentProposal().getApplicantOrganization() != null) {
				appInfo.setContactPersonInfo(getContactInfo(pdDoc
						.getDevelopmentProposal().getApplicantOrganization()
						.getRolodex()));
			}
		} else {
			// contact will come from unit or unit_administrators
			DepartmentalPersonDto depPerson = getContactPerson(pdDoc);
			ContactPersonInfo contactInfo = ContactPersonInfo.Factory
					.newInstance();
			if (depPerson != null) {
				contactInfo.setName(globLibV10Generator
						.getHumanNameDataType(depPerson));
				contactInfo.setPhone(depPerson.getOfficePhone());
				if (depPerson.getFaxNumber() != null) {
					contactInfo.setFax(depPerson.getFaxNumber());
				}
				if (depPerson.getEmailAddress() != null) {
					contactInfo.setEmail(depPerson.getEmailAddress());
				}
			}
			appInfo.setContactPersonInfo(contactInfo);
		}
		OrganizationDataTypeV2 orgType = OrganizationDataTypeV2.Factory
				.newInstance();
        RolodexContract rolodex = pdDoc.getDevelopmentProposal()
				.getApplicantOrganization().getRolodex();
		orgType.setAddress(globLibV10Generator
				.getAddressRequireCountryDataType(rolodex));

		OrganizationContract organization = pdDoc.getDevelopmentProposal()
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
			if (departmentName != null) {
				orgType.setDepartmentName(departmentName);
			}
			// divisionName
			String divisionName = getDivisionName(pdDoc);
			if (divisionName != null) {
				orgType.setDivisionName(divisionName);
			}
		}
		appInfo.setOrganizationInfo(orgType);
		return appInfo;
	}

	/**
	 *
	 * This method is used to get Contact person information
	 *
	 * @param rolodexOrganization
	 * @return ContactPersonInfo corresponding to the Rolodex object.
	 */
	private ContactPersonInfo getContactInfo(RolodexContract rolodexOrganization) {
		ContactPersonInfo contactInfo = ContactPersonInfo.Factory.newInstance();
		contactInfo.setName(globLibV10Generator
				.getHumanNameDataType(rolodexOrganization));
		contactInfo.setPhone("");
		if (rolodexOrganization != null) {
			contactInfo.setPhone(rolodexOrganization.getPhoneNumber());
			if (rolodexOrganization.getFaxNumber() != null) {
				contactInfo.setFax(rolodexOrganization.getFaxNumber());
			}
			if (rolodexOrganization.getEmailAddress() != null) {
				contactInfo.setEmail(rolodexOrganization.getEmailAddress());
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
		Map<String, String> eoStateReview = getEOStateReview(pdDoc);
		StateReviewCodeTypeDataType.Enum stateReviewCodeType = null;
		String stateReviewData = null;
		String strReview = eoStateReview.get(YNQ_ANSWER);
		if (STATE_REVIEW_YES.equals(strReview)) {
			stateReviewCodeType = StateReviewCodeTypeDataType.YES;
		} else if (STATE_REVIEW_NO.equals(strReview)) {
		    stateReviewData = eoStateReview.get(YNQ_STATE_REVIEW_DATA);
            if (stateReviewData != null && StateReviewCodeType.NOT_COVERED.toString().equals(stateReviewData)) {
                stateReviewCodeType = StateReviewCodeTypeDataType.PROGRAM_IS_NOT_COVERED_BY_E_O_12372;
            } else if (stateReviewData != null && YNQ_STATE_NOT_SELECTED.equals(stateReviewData)) {
                stateReviewCodeType = StateReviewCodeTypeDataType.PROGRAM_HAS_NOT_BEEN_SELECTED_BY_STATE_FOR_REVIEW;
            }
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

		if (pdDoc.getDevelopmentProposal().getProposalType() != null
				&& Integer.parseInt(pdDoc.getDevelopmentProposal()
						.getProposalType().getCode()) < PROPOSAL_TYPE_CODE_6) {
			// Check <6 to ensure that if proposalType='TASk ORDER", it must not
			// set. THis is because enum ApplicationType has no
			// entry for TASK ORDER
			ApplicationTypeCodeDataType.Enum applicationTypeCodeDataType = ApplicationTypeCodeDataType.Enum
					.forInt(Integer.parseInt(pdDoc.getDevelopmentProposal()
							.getProposalType().getCode()));
			applicationType.setApplicationTypeCode(applicationTypeCodeDataType);

			Map<String, String> submissionInfo = getSubmissionType(pdDoc);
			if (Integer.parseInt(pdDoc.getDevelopmentProposal()
					.getProposalType().getCode()) == ApplicationTypeCodeDataType.INT_REVISION) {
				String revisionCode = null;
				if (submissionInfo.get(KEY_REVISION_CODE) != null) {
					revisionCode = submissionInfo
							.get(KEY_REVISION_CODE);
					RevisionCode revisionCodeApplication = RevisionCode.Factory
							.newInstance();
					revisionCodeApplication.setStringValue(revisionCode);
					applicationType.setRevisionCode(revisionCodeApplication);
				}
				String revisionCodeOtherDesc = null;
				if (submissionInfo
						.get(KEY_REVISION_OTHER_DESCRIPTION) != null) {
					revisionCodeOtherDesc = submissionInfo
							.get(KEY_REVISION_OTHER_DESCRIPTION);
					RevisionCodeOtherExplanation revisionCodeOtherExplanation = RevisionCodeOtherExplanation.Factory
							.newInstance();
					revisionCodeOtherExplanation
							.setStringValue(revisionCodeOtherDesc);
					applicationType
							.setRevisionCodeOtherExplanation(revisionCodeOtherExplanation);
				}
			}
		}
		ProposalYnqContract proposalYnq = getAnswer(
				PROPOSAL_YNQ_OTHER_AGENCY_SUBMISSION, pdDoc);
		Enum answer = YesNoDataType.NO;
		if (proposalYnq != null && proposalYnq.getAnswer() != null) {
			answer = (proposalYnq.getAnswer().equals(
					YnqConstant.YES.code()) ? YesNoDataType.YES
					: YesNoDataType.NO);
		}

		applicationType.setIsOtherAgencySubmission(answer);
		if (answer.equals(YesNoDataType.YES)) {
			OtherAgencySubmissionExplanation otherAgencySubmissionExplanation = OtherAgencySubmissionExplanation.Factory
					.newInstance();
			otherAgencySubmissionExplanation.setIsOtherAgencySubmission(answer);
			String answerExplanation = proposalYnq.getExplanation();
			if (answerExplanation != null) {
				if (answerExplanation.length() > ANSWER_EXPLANATION_MAX_LENGTH) {
					otherAgencySubmissionExplanation
							.setStringValue(answerExplanation.substring(0,
									ANSWER_EXPLANATION_MAX_LENGTH));
				} else {
					otherAgencySubmissionExplanation
							.setStringValue(answerExplanation);
				}
			}
			applicationType
					.setOtherAgencySubmissionExplanation(otherAgencySubmissionExplanation);
		}
		return applicationType;
	}

	/**
	 *
	 * This method is used to get Proposed Project Period for RRSF424
	 *
	 * @return ProposedProjectPeriod project start date and end date.
	 */
	private RRSF424.ProposedProjectPeriod getProjectPeriod() {
		RRSF424.ProposedProjectPeriod proposedProjectPeriod = RRSF424.ProposedProjectPeriod.Factory
				.newInstance();
		proposedProjectPeriod.setProposedStartDate(s2SDateTimeService
				.convertDateToCalendar(pdDoc.getDevelopmentProposal()
						.getRequestedStartDateInitial()));
		proposedProjectPeriod.setProposedEndDate(s2SDateTimeService
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
	private RRSF424.CongressionalDistrict getCongDistrict() {
		OrganizationContract organization = pdDoc.getDevelopmentProposal()
				.getApplicantOrganization().getOrganization();

		// get the organization property of the Performing Organization
		ProposalSiteContract performingOrgSite = pdDoc.getDevelopmentProposal()
				.getPerformingOrganization();
		OrganizationContract performingOrganization = null;
		if (performingOrgSite != null) {
			performingOrganization = performingOrgSite.getOrganization();
		}

		RRSF424.CongressionalDistrict congressionalDistrict = RRSF424.CongressionalDistrict.Factory
				.newInstance();
		if (organization != null) {
			congressionalDistrict
					.setApplicantCongressionalDistrict(organization
							.getCongressionalDistrict());
		} else {
			congressionalDistrict.setApplicantCongressionalDistrict("");
		}
		if (performingOrganization != null) {
			congressionalDistrict
					.setProjectCongressionalDistrict(performingOrganization
							.getCongressionalDistrict());
		} else {
			congressionalDistrict.setProjectCongressionalDistrict("");
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
		ProposalPersonContract PI = null;
		for (ProposalPersonContract proposalPerson : pdDoc.getDevelopmentProposal()
				.getProposalPersons()) {
			if (PRINCIPAL_INVESTIGATOR.equals(proposalPerson
					.getProposalPersonRoleId())) {
				PI = proposalPerson;
				OrganizationContract organization = pdDoc.getDevelopmentProposal()
						.getApplicantOrganization().getOrganization();
				PDPI.setName(globLibV10Generator.getHumanNameDataType(PI));
				PDPI.setPhone(PI.getOfficePhone());
				PDPI.setEmail(PI.getEmailAddress());
				if (PI.getFaxNumber() != null) {
					PDPI.setFax(PI.getFaxNumber());
				}
				PDPI.setAddress(globLibV10Generator
						.getAddressRequireCountryDataType(PI));

				if (PI.getDirectoryTitle() != null) {
					if (PI.getDirectoryTitle().length() > DIRECTORY_TITLE_MAX_LENGTH) {
						PDPI.setTitle(PI.getDirectoryTitle().substring(0,
								DIRECTORY_TITLE_MAX_LENGTH));
					} else {
						PDPI.setTitle(PI.getDirectoryTitle());
					}
				}

				if(PI.getHomeUnit() != null) {
                    KcPersonContract kcPerson = PI.getPerson();
		            String departmentName =  kcPerson.getOrganizationIdentifier();
		            PDPI.setDepartmentName(departmentName);
		        }
		        else
		        {
		            DevelopmentProposalContract developmentProposal = pdDoc.getDevelopmentProposal();
		            PDPI.setDepartmentName(developmentProposal.getOwnedByUnit().getUnitName());
		        }

				// divisionName
				String divisionName=proposalPerson.getDivision();
				if (divisionName != null) {
					PDPI.setDivisionName(divisionName);
				}
				if (organization != null) {
					PDPI
							.setOrganizationName(organization
									.getOrganizationName());
				}
			}
		}
		return PDPI;

	}

	/**
	 *
	 * This method is used to get AOR Information for RRSf424
	 *
	 * @return aorInfoType(AORInfoType) Authorized representative information.
	 */
	private AORInfoType getAORInfoType() {
		OrganizationContract organization = pdDoc.getDevelopmentProposal()
				.getApplicantOrganization().getOrganization();
		AORInfoType aorInfoType = AORInfoType.Factory.newInstance();
		if (departmentalPerson != null) {
			aorInfoType.setName(globLibV10Generator
					.getHumanNameDataType(departmentalPerson));

			if (departmentalPerson.getPrimaryTitle() != null) {
				if (departmentalPerson.getPrimaryTitle().length() > PRIMARY_TITLE_MAX_LENGTH) {
					aorInfoType.setTitle(departmentalPerson.getPrimaryTitle()
							.substring(0, PRIMARY_TITLE_MAX_LENGTH));
				} else {
					aorInfoType.setTitle(departmentalPerson.getPrimaryTitle());
				}
			}
			aorInfoType.setAddress(globLibV10Generator
					.getAddressRequireCountryDataType(departmentalPerson));
			aorInfoType.setPhone(departmentalPerson.getOfficePhone());
			aorInfoType.setFax(departmentalPerson.getFaxNumber());
			aorInfoType.setDepartmentName(departmentalPerson.getDirDept());
			aorInfoType.setEmail(departmentalPerson.getEmailAddress());
			if (departmentalPerson.getHomeUnit() != null) {
				aorInfoType.setDivisionName(departmentalPerson.getHomeUnit());
			}

		}
		if (organization != null) {
			aorInfoType.setOrganizationName(organization.getOrganizationName());
		}

		return aorInfoType;
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
					.getOrganizationTypes().get(0).getOrganizationTypeList().getCode();
		}
		ApplicantTypeCodeDataType.Enum applicantTypeCode = null;
		switch (orgTypeCode) {
		case 1: {
			// local
			applicantTypeCode = ApplicantTypeCodeDataType.C_CITY_OR_TOWNSHIP_GOVERNMENT;
			break;
		}
		case 2: {
			// state
			applicantTypeCode = ApplicantTypeCodeDataType.A_STATE_GOVERNMENT;
			break;
		}
		case 3: {
			// federal
			applicantTypeCode = ApplicantTypeCodeDataType.P_OTHER_SPECIFY;
			break;
		}
		case 4: {
			// Private non-profit
			applicantTypeCode = ApplicantTypeCodeDataType.J_NONPROFIT_WITH_501_C_3_IRS_STATUS_OTHER_THAN_INSTITUTION_OF_HIGHER_EDUCATION;
			break;
		}
		case 5: {
			// Non-Profit
			applicantTypeCode = ApplicantTypeCodeDataType.K_NONPROFIT_WITHOUT_501_C_3_IRS_STATUS_OTHER_THAN_INSTITUTION_OF_HIGHER_EDUCATION;
			break;
		}
		case 6: {
			// For-profit
			applicantTypeCode = ApplicantTypeCodeDataType.N_FOR_PROFIT_ORGANIZATION_OTHER_THAN_SMALL_BUSINESS;
			break;
		}
		case 7: {
			// Other
			applicantTypeCode = ApplicantTypeCodeDataType.P_OTHER_SPECIFY;
			break;
		}
		case 8: {
			// Indian Tribal Government
			applicantTypeCode = ApplicantTypeCodeDataType.G_NATIVE_AMERICAN_TRIBAL_GOVERNMENT_FEDERALLY_RECOGNIZED;
			break;
		}
		case 9: {
			// Individual
			applicantTypeCode = ApplicantTypeCodeDataType.M_INDIVIDUAL;
			break;
		}
		case 10: {
			// Inst of higher learning
			applicantTypeCode = ApplicantTypeCodeDataType.L_PRIVATE_INSTITUTION_OF_HIGHER_EDUCATION;
			break;
		}
		case 11: {
			// Small Business
			applicantTypeCode = ApplicantTypeCodeDataType.O_SMALL_BUSINESS;
			break;
		}
		case 14: {
			// disadvantaged
			applicantTypeCode = ApplicantTypeCodeDataType.P_OTHER_SPECIFY;
			// value is hardcoded
			isSociallyEconomicallyDisadvantaged.setStringValue(VALUE_YES);
			applicantTypeCode = ApplicantTypeCodeDataType.O_SMALL_BUSINESS;
			smallOrganizationType.setApplicantTypeCode(applicantTypeCode);
			smallOrganizationType
					.setIsSociallyEconomicallyDisadvantaged(isSociallyEconomicallyDisadvantaged);
			smallBusflag = true;
			break;
		}
		case 15: {
			// women owned
			applicantTypeCode = ApplicantTypeCodeDataType.P_OTHER_SPECIFY;
			// value is hardcoded
			isWomenOwned.setStringValue(VALUE_YES);
			applicantTypeCode = ApplicantTypeCodeDataType.O_SMALL_BUSINESS;
			smallOrganizationType.setApplicantTypeCode(applicantTypeCode);
			smallOrganizationType.setIsWomenOwned(isWomenOwned);
			smallBusflag = true;
			break;
		}
		case 21: {
			applicantTypeCode = ApplicantTypeCodeDataType.F_STATE_CONTROLLED_INSTITUTION_OF_HIGHER_EDUCATION;
			break;
		}
		case 22: {
			applicantTypeCode = ApplicantTypeCodeDataType.B_COUNTY_GOVERNMENT;
			break;
		}
		case 23: {
			applicantTypeCode = ApplicantTypeCodeDataType.D_SPECIAL_DISTRICT_GOVERNMENTS;
			break;
		}
		case 24: {
			applicantTypeCode = ApplicantTypeCodeDataType.E_INDEPENDENT_SCHOOL_DISTRICT;
			break;
		}
		case 25: {
			applicantTypeCode = ApplicantTypeCodeDataType.H_PUBLIC_INDIAN_HOUSING_AUTHORITY;
			break;
		}
		case 26: {
			applicantTypeCode = ApplicantTypeCodeDataType.I_NATIVE_AMERICAN_TRIBAL_ORGANIZATION_OTHER_THAN_FEDERALLY_RECOGNIZED;
			break;
		}
		default: {
			applicantTypeCode = ApplicantTypeCodeDataType.P_OTHER_SPECIFY;
		}
		}
		if (smallBusflag) {
			applicantType
					.setSmallBusinessOrganizationType(smallOrganizationType);
		}
		applicantType.setApplicantTypeCode(applicantTypeCode);
		return applicantType;
	}
	/**
	 *
	 * This method is used to get the answer for ProposalYnq
	 *
	 * @param questionId
	 *            for which the proposalYnq has to be found.
	 * @return proposalYnq corresponding to the questionId.
	 */
	private ProposalYnqContract getAnswer(String questionId,
			ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
		YnqContract question;
		ProposalYnqContract ynQ = null;
		for (ProposalYnqContract proposalYnq : proposalDevelopmentDocument
				.getDevelopmentProposal().getProposalYnqs()) {
			question = proposalYnq.getYnq();

			if (question != null && question.getQuestionId().equals(questionId)) {
				ynQ = proposalYnq;
				break;
			}
		}
		return ynQ;
	}

	/**
	 * This method creates {@link XmlObject} of type {@link RRSF424Document} by
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
		departmentalPerson = departmentalPersonService
				.getDepartmentalPerson(proposalDevelopmentDocument);
		return getRRSF424();
	}
    @Override
    protected List<? extends AnswerHeaderContract> getAnswerHeaders() {
        return answerHeaders;
    }

    public RolodexService getRolodexService() {
        return rolodexService;
    }

    public void setRolodexService(RolodexService rolodexService) {
        this.rolodexService = rolodexService;
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
