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

import gov.grants.apply.forms.rrSF424V11.*;
import gov.grants.apply.forms.rrSF424V11.RRSF424Document.RRSF424;
import gov.grants.apply.forms.rrSF424V11.RRSF424Document.RRSF424.*;
import gov.grants.apply.forms.rrSF424V11.RRSF424Document.RRSF424.ApplicantInfo.ContactPersonInfo;
import gov.grants.apply.forms.rrSF424V11.RRSF424Document.RRSF424.ApplicantType.SmallBusinessOrganizationType;
import gov.grants.apply.forms.rrSF424V11.RRSF424Document.RRSF424.ApplicantType.SmallBusinessOrganizationType.IsSociallyEconomicallyDisadvantaged;
import gov.grants.apply.forms.rrSF424V11.RRSF424Document.RRSF424.ApplicantType.SmallBusinessOrganizationType.IsWomenOwned;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.globalLibraryV20.AddressDataType;
import gov.grants.apply.system.globalLibraryV20.ApplicantTypeCodeDataType;
import gov.grants.apply.system.globalLibraryV20.OrganizationDataType;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.api.person.KcPersonContract;
import org.kuali.coeus.common.api.org.OrganizationContract;
import org.kuali.coeus.common.api.ynq.YnqConstant;
import org.kuali.coeus.common.questionnaire.api.answer.AnswerHeaderContract;
import org.kuali.coeus.common.api.rolodex.RolodexService;
import org.kuali.coeus.common.budget.api.income.BudgetProjectIncomeContract;
import org.kuali.coeus.common.budget.api.nonpersonnel.BudgetLineItemCalculatedAmountContract;
import org.kuali.coeus.common.budget.api.nonpersonnel.BudgetLineItemContract;
import org.kuali.coeus.common.budget.api.period.BudgetPeriodContract;
import org.kuali.coeus.common.api.rolodex.RolodexContract;
import org.kuali.coeus.common.api.sponsor.SponsorContract;
import org.kuali.coeus.propdev.api.abstrct.ProposalAbstractContract;
import org.kuali.coeus.propdev.api.budget.ProposalDevelopmentBudgetExtContract;
import org.kuali.coeus.propdev.api.budget.modular.BudgetModularIdcContract;
import org.kuali.coeus.propdev.api.core.DevelopmentProposalContract;
import org.kuali.coeus.propdev.api.location.ProposalSiteContract;
import org.kuali.coeus.propdev.api.person.ProposalPersonContract;
import org.kuali.coeus.propdev.api.s2s.S2sOpportunityContract;
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
import java.util.*;

/**
 * Class for generating the XML object for grants.gov RRSF424V1_0. Form is
 * generated using XMLBean classes and is based on RRSF424V1_0 schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@FormGenerator("RRSF424V1_1Generator")
public class RRSF424V1_1Generator extends RRSF424BaseGenerator {

	private static final Log LOG = LogFactory
			.getLog(RRSF424V1_0Generator.class);

	private DepartmentalPersonDto departmentalPerson;
    private List<? extends AnswerHeaderContract> answerHeaders;

    @Value("http://apply.grants.gov/forms/RR_SF424-V1.1")
    private String namespace;

    @Value("RR_SF424-V1.1")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/RR_SF424-V1.1.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.rrSF424V11")
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
        DevelopmentProposalContract devProp = pdDoc.getDevelopmentProposal();

		RRSF424Document rrSF424Document = RRSF424Document.Factory.newInstance();
		RRSF424 rrsf424 = RRSF424.Factory.newInstance();
		rrsf424.setFormVersion(FormVersion.v1_1.getVersion());
		S2sOpportunityContract s2sOpportunity = devProp.getS2sOpportunity();
		if (s2sOpportunity != null
				&& s2sOpportunity.getS2sSubmissionType() != null) {
			rrsf424.setSubmissionTypeCode(SubmissionTypeDataType.Enum
					.forString(devProp.getS2sOpportunity()
							.getS2sSubmissionType().getDescription()));
		}
		rrsf424.setSubmittedDate(Calendar.getInstance());
		OrganizationContract applicantOrganization = devProp.getApplicantOrganization()
				.getOrganization();

        final RolodexContract rolodex = rolodexService.getRolodex(applicantOrganization.getContactAddressId());

		if (applicantOrganization != null
				&& rolodex != null) {
			String state = rolodex.getState();
			rrsf424.setStateID(state);
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
		boolean isNih  = isSponsorInHierarchy(pdDoc.getDevelopmentProposal(), SPONSOR_GROUPS,SPONSOR_NIH);
        if (applicantOrganization != null) {
            if (applicantOrganization.getPhsAccount() != null && isNih) {
                rrsf424.setEmployerID(applicantOrganization.getPhsAccount());
            } else {
                rrsf424.setEmployerID(applicantOrganization.getFederalEmployerId());
            }
        }
		SponsorContract sponsor = devProp.getSponsor();
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
           List<? extends ProposalAbstractContract> proposalAbstractList = devProp.getProposalAbstracts();
           String state="";     
           for (ProposalAbstractContract proposalAbstract : proposalAbstractList) {
               if( proposalAbstract.getAbstractType().getCode().equals(AREAS_AFFECTED_ABSTRACT_TYPE_CODE))
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
        for (NarrativeContract narrative : devProp.getNarratives()) {
            AttachedFileDataType attachedFileDataType=null;
            switch(Integer.parseInt(narrative.getNarrativeType().getCode())){
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
		boolean hasBudgetLineItem = false;
		if (budget != null) {
            ScaleTwoDecimal totalCost = ScaleTwoDecimal.ZERO;

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
			ScaleTwoDecimal fedNonFedCost = totalCost;
			
			BigDecimal totalProjectIncome = BigDecimal.ZERO;

            ScaleTwoDecimal costSharingAmount = ScaleTwoDecimal.ZERO;

            for (BudgetPeriodContract budgetPeriod : budget.getBudgetPeriods()) {
                for (BudgetLineItemContract lineItem : budgetPeriod.getBudgetLineItems()) {
                    hasBudgetLineItem = true;
                    if (budget.getSubmitCostSharingFlag() && lineItem.getSubmitCostSharingFlag()) {
                        costSharingAmount =  costSharingAmount.add(lineItem.getCostSharingAmount());
                        List<? extends BudgetLineItemCalculatedAmountContract> calculatedAmounts = lineItem.getBudgetLineItemCalculatedAmounts();
                        for (BudgetLineItemCalculatedAmountContract budgetLineItemCalculatedAmount : calculatedAmounts) {
                             costSharingAmount =  costSharingAmount.add(budgetLineItemCalculatedAmount.getCalculatedCostSharing());
                        }
                        
                    }
                }
            }
            if(!hasBudgetLineItem && budget.getSubmitCostSharingFlag()){
                costSharingAmount = budget.getCostSharingAmount();      
            }
            fedNonFedCost = fedNonFedCost.add(costSharingAmount);
            
            for (BudgetProjectIncomeContract budgetProjectIncome : budget
					.getBudgetProjectIncomes()) {
				totalProjectIncome = totalProjectIncome.add(budgetProjectIncome
						.getProjectIncome().bigDecimalValue());
			}

			funding = EstimatedProjectFunding.Factory.newInstance();
			funding.setTotalEstimatedAmount(totalCost.bigDecimalValue());
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
			DepartmentalPersonDto depPerson = getContactPerson(pdDoc);
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
        RolodexContract rolodex = pdDoc.getDevelopmentProposal()
				.getApplicantOrganization().getRolodex();
		orgType.setAddress(globLibV20Generator.getAddressDataType(rolodex));
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
			orgType.setDepartmentName(departmentName);
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
	 * @param rolodex(Rolodex)
	 * @return ContactPersonInfo corresponding to the Rolodex object.
	 */
	private ContactPersonInfo getContactInfo(RolodexContract rolodex) {
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
        Map<String, String> eoStateReview = getEOStateReview(pdDoc);
        StateReviewCodeTypeDataType.Enum stateReviewCodeType = null;
        String strReview = eoStateReview.get(YNQ_ANSWER);
        String stateReviewData = null;
        String stateReviewDate = null;
        
        if (STATE_REVIEW_YES.equals(strReview)) {
            stateReviewCodeType = StateReviewCodeTypeDataType.Y_YES;
            stateReviewDate = eoStateReview.get(YNQ_REVIEW_DATE);
        } else if (STATE_REVIEW_NO.equals(strReview)) {
            stateReviewData = eoStateReview.get(YNQ_STATE_REVIEW_DATA);
            if (stateReviewData != null && YNQ_STATE_NOT_COVERED.equals(stateReviewData)) {
                stateReviewCodeType = StateReviewCodeTypeDataType.PROGRAM_IS_NOT_COVERED_BY_E_O_12372;
            } else if (stateReviewData != null && YNQ_STATE_NOT_SELECTED.equals(stateReviewData)) {
                stateReviewCodeType = StateReviewCodeTypeDataType.PROGRAM_HAS_NOT_BEEN_SELECTED_BY_STATE_FOR_REVIEW;
            }
        }
        StateReview stateReview = StateReview.Factory.newInstance();
        stateReview.setStateReviewCodeType(stateReviewCodeType);
        if (stateReviewDate != null) {
            stateReview.setStateReviewDate(s2SDateTimeService.convertDateStringToCalendar(stateReviewDate));
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
		Map<String, String> submissionInfo = getSubmissionType(pdDoc);
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
			if (Integer.parseInt(pdDoc.getDevelopmentProposal()
					.getProposalType().getCode()) == ApplicationTypeCodeDataType.INT_REVISION) {
				String revisionCode = null;
				if (submissionInfo.get(KEY_REVISION_CODE) != null) {
					revisionCode = submissionInfo
							.get(KEY_REVISION_CODE);
					RevisionTypeCodeDataType.Enum revisionCodeApplication = RevisionTypeCodeDataType.Enum
							.forString(revisionCode);
					applicationType.setRevisionCode(revisionCodeApplication);
				}
				String revisionCodeOtherDesc = null;
				if (submissionInfo
						.get(KEY_REVISION_OTHER_DESCRIPTION) != null) {
					revisionCodeOtherDesc = submissionInfo
							.get(KEY_REVISION_OTHER_DESCRIPTION);
					applicationType
							.setRevisionCodeOtherExplanation(revisionCodeOtherDesc);
				}
			}
		}
		YesNoDataType.Enum answer = null;
        String answerdetails = getAnswer(ANSWER_128, answerHeaders);
        if (answerdetails != null && !answerdetails.equals(NOT_ANSWERED)) {
            answer =  answerdetails.equals(YnqConstant.YES.code()) ? YesNoDataType.Y_YES : YesNoDataType.N_NO;
            applicationType.setIsOtherAgencySubmission(answer);
        } else {
            applicationType.setIsOtherAgencySubmission(null);
        }

        if (answer != null && answer.equals(YesNoDataType.Y_YES)) {
            applicationType.setOtherAgencySubmissionExplanation(getOtherAgencySubmissionExplanation());
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
		ProposalSiteContract applicantOrganization = pdDoc.getDevelopmentProposal()
				.getApplicantOrganization();
		ProposalSiteContract performOrganization = pdDoc.getDevelopmentProposal()
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
		ProposalPersonContract PI = null;
		for (ProposalPersonContract proposalPerson : pdDoc.getDevelopmentProposal()
				.getProposalPersons()) {
			if (PRINCIPAL_INVESTIGATOR.equals(proposalPerson
					.getProposalPersonRoleId())) {
				PI = proposalPerson;
				ProposalSiteContract applicantOrganization = pdDoc
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
				String divisionName = proposalPerson.getDivision();
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
		ProposalSiteContract applicantOrganization = pdDoc.getDevelopmentProposal()
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
						.getStateCodeDataType(departmentalPerson.getCountryCode(), departmentalPerson.getState()));
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
                    .getOrganizationTypes().get(0).getOrganizationTypeList().getCode();
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
