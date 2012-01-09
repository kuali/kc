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

import gov.grants.apply.forms.rrSF424V11.AORInfoType;
import gov.grants.apply.forms.rrSF424V11.ApplicationTypeCodeDataType;
import gov.grants.apply.forms.rrSF424V11.OrganizationContactPersonDataType;
import gov.grants.apply.forms.rrSF424V11.RRSF424Document;
import gov.grants.apply.forms.rrSF424V11.RevisionTypeCodeDataType;
import gov.grants.apply.forms.rrSF424V11.StateReviewCodeTypeDataType;
import gov.grants.apply.forms.rrSF424V11.SubmissionTypeDataType;
import gov.grants.apply.forms.rrSF424V11.RRSF424Document.RRSF424;
import gov.grants.apply.forms.rrSF424V11.RRSF424Document.RRSF424.ApplicantInfo;
import gov.grants.apply.forms.rrSF424V11.RRSF424Document.RRSF424.ApplicantType;
import gov.grants.apply.forms.rrSF424V11.RRSF424Document.RRSF424.ApplicationType;
import gov.grants.apply.forms.rrSF424V11.RRSF424Document.RRSF424.EstimatedProjectFunding;
import gov.grants.apply.forms.rrSF424V11.RRSF424Document.RRSF424.StateReview;
import gov.grants.apply.forms.rrSF424V11.RRSF424Document.RRSF424.ApplicantInfo.ContactPersonInfo;
import gov.grants.apply.forms.rrSF424V11.RRSF424Document.RRSF424.ApplicantType.SmallBusinessOrganizationType;
import gov.grants.apply.forms.rrSF424V11.RRSF424Document.RRSF424.ApplicantType.SmallBusinessOrganizationType.IsSociallyEconomicallyDisadvantaged;
import gov.grants.apply.forms.rrSF424V11.RRSF424Document.RRSF424.ApplicantType.SmallBusinessOrganizationType.IsWomenOwned;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.globalLibraryV20.AddressDataType;
import gov.grants.apply.system.globalLibraryV20.ApplicantTypeCodeDataType;
import gov.grants.apply.system.globalLibraryV20.OrganizationDataType;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType.Enum;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.bo.UnitAdministrator;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.distributionincome.BudgetProjectIncome;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.ProposalAbstract;
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
 * generated using XMLBean classes and is based on RRSF424V1_0 schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class RRSF424V1_1Generator extends RRSF424BaseGenerator {

	private static final Log LOG = LogFactory
			.getLog(RRSF424V1_0Generator.class);

	private DepartmentalPerson departmentalPerson;

	/**
	 * 
	 * This method gives information of applications that are used in RRSF424
	 * 
	 * @return rrSF424Document {@link XmlObject} of type RRSF424Document.
	 */
	private RRSF424Document getRRSF424() {
        DevelopmentProposal devProp = pdDoc.getDevelopmentProposal();

		RRSF424Document rrSF424Document = RRSF424Document.Factory.newInstance();
		RRSF424 rrsf424 = RRSF424.Factory.newInstance();
		rrsf424.setFormVersion(S2SConstants.FORMVERSION_1_1);
		S2sOpportunity s2sOpportunity = devProp.getS2sOpportunity();
		if (s2sOpportunity != null
				&& s2sOpportunity.getS2sSubmissionTypeCode() != null) {
			s2sOpportunity.refreshNonUpdateableReferences();
			rrsf424.setSubmissionTypeCode(SubmissionTypeDataType.Enum
					.forString(devProp.getS2sOpportunity()
							.getS2sSubmissionType().getDescription()));
		}
		rrsf424.setSubmittedDate(s2sUtilService.getCurrentCalendar());
		Organization applicantOrganization = devProp.getApplicantOrganization()
				.getOrganization();
		if (applicantOrganization != null
				&& applicantOrganization.getRolodex() != null) {
			String state = applicantOrganization.getRolodex().getState();
			rrsf424.setStateID(state);
		}
		String federalId = s2sUtilService.getFederalId(pdDoc);
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
		if (applicantOrganization != null) {
			rrsf424.setEmployerID(applicantOrganization.getFedralEmployerId());
		}
		Sponsor sponsor = devProp.getSponsor();
		if (sponsor != null) {
			rrsf424.setFederalAgencyName(sponsor.getSponsorName());
		}
		if(devProp.getCfdaNumber()!=null){
		    rrsf424.setCFDANumber(devProp.getCfdaNumber());
		}
		if (devProp.getProgramAnnouncementTitle() != null) {
			String announcementTitle;
			if (devProp.getProgramAnnouncementTitle().length() > 120) {
				announcementTitle = devProp.getProgramAnnouncementTitle()
						.substring(0, 120);
			} else {
				announcementTitle = devProp.getProgramAnnouncementTitle();
			}
			rrsf424.setActivityTitle(announcementTitle);
		}
		rrsf424.setProjectTitle(devProp.getTitle());
		if (devProp.getProposalAbstracts() != null) {   
           List<ProposalAbstract> proposalAbstractList = devProp.getProposalAbstracts(); 
           String state="";     
           for (ProposalAbstract proposalAbstract : proposalAbstractList) {
               if( proposalAbstract.getAbstractTypeCode().equals(AREAS_AFFECTED_ABSTRACT_TYPE_CODE))           
                   state = proposalAbstract.getAbstractDetails();  
               }
           rrsf424.setLocation(state);
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
        rrsf424.setTrustAgree(YesNoDataType.Y_YES);
        rrsf424.setAORInfo(getAORInfoType());
        for (Narrative narrative : devProp.getNarratives()) {
            AttachedFileDataType attachedFileDataType=null;
            switch(Integer.parseInt(narrative.getNarrativeTypeCode())){
                case(PRE_APPLICATION):
                    attachedFileDataType = getAttachedFileType(narrative);
                    if(attachedFileDataType!=null) {
                        rrsf424.setPreApplicationAttachment(attachedFileDataType);
                    }
                    break;
                case(ADDITIONAL_CONGRESSIONAL_DESTRICT):
                    attachedFileDataType = getAttachedFileType(narrative);
                    if(attachedFileDataType!=null) {
                        rrsf424.setAdditionalCongressionalDistricts(attachedFileDataType);
                    }
                    break;
            }
        }
        if (departmentalPerson != null) {
            rrsf424.setAORSignature(departmentalPerson.getFullName());
        } else {
            rrsf424.setAORSignature("");
        }
        rrsf424.setAORSignedDate(s2sUtilService.getCurrentCalendar());
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
		BudgetDocument budgetDocument = s2sBudgetCalculatorService
				.getFinalBudgetVersion(pdDoc);
		Budget budget = budgetDocument == null ? null : budgetDocument
				.getBudget();
		EstimatedProjectFunding funding = EstimatedProjectFunding.Factory
				.newInstance();
		funding.setTotalEstimatedAmount(BigDecimal.ZERO);
		funding.setTotalfedNonfedrequested(BigDecimal.ZERO);
		funding.setEstimatedProgramIncome(BigDecimal.ZERO);
		boolean hasBudgetLineItem = false;
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
			
			BigDecimal totalProjectIncome = BigDecimal.ZERO;

			for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
	            for (BudgetLineItem lineItem : budgetPeriod.getBudgetLineItems()) {
	                hasBudgetLineItem = true;
	                if(budget.getSubmitCostSharingFlag() && lineItem.getSubmitCostSharingFlag())
	                fedNonFedCost=fedNonFedCost.add(lineItem.getCostSharingAmount());
	                }
			}
			if(!hasBudgetLineItem && budget.getSubmitCostSharingFlag()){
			    fedNonFedCost=fedNonFedCost.add(budget.getCostSharingAmount());
			}
			for (BudgetProjectIncome budgetProjectIncome : budget
					.getBudgetProjectIncomes()) {
				totalProjectIncome = totalProjectIncome.add(budgetProjectIncome
						.getProjectIncome().bigDecimalValue());
			}

			funding = EstimatedProjectFunding.Factory.newInstance();
			funding.setTotalEstimatedAmount(budget.getTotalCost()
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
	 * @return appInfo(ApplicantInfo) applicant details.
	 */
	private ApplicantInfo getApplicationInfo() {
		ApplicantInfo appInfo = ApplicantInfo.Factory.newInstance();
		String contactType = getContactType();
		if (contactType.equals(CONTACT_TYPE_I)) {
			// use organization rolodex contact
			if (pdDoc.getDevelopmentProposal().getApplicantOrganization() != null) {
				appInfo.setContactPersonInfo(getContactInfo(pdDoc
						.getDevelopmentProposal().getApplicantOrganization()
						.getRolodex()));
			}
		} else {
			// contact will come from unit or unit_administrators
			DepartmentalPerson depPerson = getContactPerson(pdDoc);
			ContactPersonInfo contactInfo = ContactPersonInfo.Factory
					.newInstance();
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
			appInfo.setContactPersonInfo(contactInfo);
		}
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
		appInfo.setOrganizationInfo(orgType);
		return appInfo;
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
		String stateReviewDate = null;
		if (STATE_REVIEW_YES.equals(strReview)) {
			stateReviewCodeType = StateReviewCodeTypeDataType.Y_YES;
			stateReviewDate = eoStateReview.get(S2SConstants.YNQ_REVIEW_DATE);
		} else if (STATE_REVIEW_NO.equals(strReview)) {
			stateReviewCodeType = StateReviewCodeTypeDataType.PROGRAM_HAS_NOT_BEEN_SELECTED_BY_STATE_FOR_REVIEW;
		} else {
			stateReviewCodeType = StateReviewCodeTypeDataType.PROGRAM_IS_NOT_COVERED_BY_E_O_12372;
		}
		StateReview stateReview = StateReview.Factory.newInstance();
		stateReview.setStateReviewCodeType(stateReviewCodeType);
		if (stateReviewDate != null) {
			stateReview.setStateReviewDate(s2sUtilService
					.convertDateStringToCalendar(stateReviewDate));
		}
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
			ApplicationTypeCodeDataType.Enum applicationTypeCodeDataType = ApplicationTypeCodeDataType.Enum
					.forInt(Integer.parseInt(pdDoc.getDevelopmentProposal()
							.getProposalTypeCode()));
			applicationType.setApplicationTypeCode(applicationTypeCodeDataType);
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
		ProposalYnq proposalYnq = getAnswer(
				PROPOSAL_YNQ_OTHER_AGENCY_SUBMISSION, pdDoc);
		Enum answer = YesNoDataType.N_NO;
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
	private RRSF424.CongressionalDistrict getCongDistrict() {
		ProposalSite applicantOrganization = pdDoc.getDevelopmentProposal()
				.getApplicantOrganization();
		ProposalSite performOrganization = pdDoc.getDevelopmentProposal()
				.getPerformingOrganization();
		RRSF424.CongressionalDistrict congressionalDistrict = RRSF424.CongressionalDistrict.Factory
				.newInstance();
		if (applicantOrganization != null) {
			congressionalDistrict
					.setApplicantCongressionalDistrict(applicantOrganization
							.getFirstCongressionalDistrictName());
		} else {
			congressionalDistrict.setApplicantCongressionalDistrict("");
		}
		if (performOrganization != null) {
			congressionalDistrict
					.setProjectCongressionalDistrict(performOrganization
							.getFirstCongressionalDistrictName());
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
				if (PI.getDirectoryTitle() != null) {
					if (PI.getDirectoryTitle().length() > DIRECTORY_TITLE_MAX_LENGTH) {
						PDPI.setTitle(PI.getDirectoryTitle().substring(0,
								DIRECTORY_TITLE_MAX_LENGTH));
					} else {
						PDPI.setTitle(PI.getDirectoryTitle());
					}
				}
				String departmentName = null;
				if (pdDoc.getDevelopmentProposal().getOwnedByUnit() != null) {
					departmentName = pdDoc.getDevelopmentProposal()
							.getOwnedByUnit().getUnitName();
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
				// divisionName
				String divisionName = s2sUtilService.getDivisionName(pdDoc);
				if (divisionName != null) {
					PDPI.setDivisionName(divisionName);
				}
				if (applicantOrganization != null) {
					PDPI.setOrganizationName(applicantOrganization
							.getLocationName());
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
		ProposalSite applicantOrganization = pdDoc.getDevelopmentProposal()
				.getApplicantOrganization();
		AORInfoType aorInfoType = AORInfoType.Factory.newInstance();
		if (departmentalPerson != null) {
			aorInfoType.setName(globLibV20Generator
					.getHumanNameDataType(departmentalPerson));
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
			AddressDataType address = AddressDataType.Factory.newInstance();
			address.setStreet1(departmentalPerson.getAddress1());
			address.setStreet2(departmentalPerson.getAddress2());
			address.setCity(departmentalPerson.getCity());
			if (departmentalPerson.getState() != null) {
				address.setState(globLibV20Generator
						.getStateCodeDataType(departmentalPerson.getState()));
			}
			address.setZipPostalCode(departmentalPerson.getPostalCode());
			if (departmentalPerson.getCountryCode() != null) {
				address.setCountry(globLibV20Generator
						.getCountryCodeDataType(departmentalPerson
								.getCountryCode()));
			}
			aorInfoType.setAddress(address);
			aorInfoType.setPhone(departmentalPerson.getOfficePhone());
			aorInfoType.setFax(departmentalPerson.getFaxNumber());
			aorInfoType.setDepartmentName(departmentalPerson.getDirDept());
			aorInfoType.setEmail(departmentalPerson.getEmailAddress());
			if (departmentalPerson.getHomeUnit() != null) {
				aorInfoType.setDivisionName(departmentalPerson.getHomeUnit());
			}
		}
		if (applicantOrganization != null) {
			aorInfoType.setOrganizationName(applicantOrganization
					.getLocationName());
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

	/**
	 * 
	 * This method is used to get the answer for ProposalYnq
	 * 
	 * @param questionId
	 *            for which the proposalYnq has to be found.
	 * @return proposalYnq corresponding to the questionId.
	 */
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

	/**
	 * This method creates {@link XmlObject} of type {@link RRSF424Document} by
	 * populating data from the given {@link ProposalDevelopmentDocument}
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
		RRSF424 rrsf424 = (RRSF424) xmlObject;
		RRSF424Document rrSF424Document = RRSF424Document.Factory.newInstance();
		rrSF424Document.setRRSF424(rrsf424);
		return rrSF424Document;
	}
}