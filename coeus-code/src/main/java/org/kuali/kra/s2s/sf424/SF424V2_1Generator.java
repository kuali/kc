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
package org.kuali.kra.s2s.sf424;

import gov.grants.apply.forms.sf42421V21.SF42421Document;
import gov.grants.apply.forms.sf42421V21.SF42421Document.SF42421;
import gov.grants.apply.forms.sf42421V21.SF42421Document.SF42421.ApplicationType;
import gov.grants.apply.forms.sf42421V21.SF42421Document.SF42421.RevisionType;
import gov.grants.apply.forms.sf42421V21.SF42421Document.SF42421.StateReview;
import gov.grants.apply.forms.sf42421V21.SF42421Document.SF42421.SubmissionType;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.attachmentsV10.AttachmentGroupMin0Max100DataType;
import gov.grants.apply.system.globalLibraryV20.ApplicantTypeCodeDataType;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.OrganizationType;
import org.kuali.kra.bo.OrganizationYnq;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.distributionincome.BudgetProjectIncome;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItemCalculatedAmount;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.proposaldevelopment.ProposalDevelopmentUtils;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalSite;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.bo.S2sOpportunity;
import org.kuali.kra.s2s.bo.S2sSubmissionType;
import org.kuali.kra.s2s.generator.bo.DepartmentalPerson;
import org.kuali.kra.s2s.generator.impl.SF424BaseGenerator;
import org.kuali.kra.s2s.util.S2SConstants;

/**
 * This Class is used to generate XML object for grants.gov SF424V2.1. This form is generated using XMLBean classes and is based on
 * SF424-V2.1 schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class SF424V2_1Generator extends SF424BaseGenerator {

    private static final Log LOG = LogFactory.getLog(SF424V2_1Generator.class);

    private DepartmentalPerson aorInfo = null;
    private String applicantTypeOtherSpecify = null;
    private String federalDebtExp;
    private String stateReviewDate = null;
    private String strReview = null;
    private static final String ORGANIZATION_YNQ_ANSWER_YES = "Y";
    
    public static final int AREAS_AFFECTED_ATTACHMENT = 135;
    public static final int DEBT_EXPLANATION_ATTACHMENT = 136;
    public static final int ADDITIONAL_PROJECT_TITLE_ATTACHMENT = 137;
    public static final int ADDITIONAL_CONGRESSIONAL_DISTRICTS_ATTACHMENT = 138;


    /**
     * 
     * This method returns SF42421Document object based on proposal development document which contains the SF42421Document information
     * for a particular proposal
     * 
     * @param proposalDevelopmentDocument (ProposalDevelopmentDocument)
     * @return SF42421Document {@link XmlObject} of type SF42421Document.
     */
    private SF42421Document getSF42421Doc() {
        SF42421Document sf42421Document = SF42421Document.Factory.newInstance();
        sf42421Document.setSF42421(getSF42421());
        return sf42421Document;
    }

    /**
     * 
     * This method gets SF42421 information for the form which includes informations regarding SubmissionTypeCode
     * ApplicationType,RevisionType,AgencyName,ApplicantID,CFDANumber,FederalEntityIdentifier,AuthorizedRepresentative.
     * 
     * @param proposalDevelopmentDocument (ProposalDevelopmentDocument)
     * @return sf424V21 object containing applicant and application details.
     */
    private SF42421 getSF42421() {

        SF42421 sf424V21 = SF42421.Factory.newInstance();
        sf424V21.setFormVersion(S2SConstants.FORMVERSION_2_1);
        boolean hasBudgetLineItem = false;
        S2sOpportunity s2sOpportunity = pdDoc.getDevelopmentProposal().getS2sOpportunity();
        if (s2sOpportunity != null && s2sOpportunity.getS2sSubmissionTypeCode() != null) {
            s2sOpportunity.refreshNonUpdateableReferences();
            S2sSubmissionType submissionType = s2sOpportunity.getS2sSubmissionType();
            SubmissionType.Enum subEnum = SubmissionType.Enum.forInt(Integer.parseInt(submissionType.getS2sSubmissionTypeCode()));
            sf424V21.setSubmissionType(subEnum);
            ApplicationType.Enum applicationTypeEnum = null;
            if (pdDoc.getDevelopmentProposal().getProposalTypeCode() != null) {
                String proposalTypeCode = pdDoc.getDevelopmentProposal().getProposalTypeCode();
                if (ProposalDevelopmentUtils.getProposalDevelopmentDocumentParameter(
                        ProposalDevelopmentUtils.PROPOSAL_TYPE_CODE_NEW_PARM).equals(proposalTypeCode)) {
			        applicationTypeEnum = ApplicationType.NEW;
                } else if (ProposalDevelopmentUtils.getProposalDevelopmentDocumentParameter(
                        ProposalDevelopmentUtils.PROPOSAL_TYPE_CODE_RESUBMISSION_PARM).equals(proposalTypeCode)) {
					applicationTypeEnum = ApplicationType.REVISION;
                } else if (ProposalDevelopmentUtils.getProposalDevelopmentDocumentParameter(
                        ProposalDevelopmentUtils.PROPOSAL_TYPE_CODE_RENEWAL_PARM).equals(proposalTypeCode)) {
                    applicationTypeEnum = ApplicationType.CONTINUATION;
                } else if (ProposalDevelopmentUtils.getProposalDevelopmentDocumentParameter(
                        ProposalDevelopmentUtils.PROPOSAL_TYPE_CODE_CONTINUATION_PARM).equals(proposalTypeCode)) {
                    applicationTypeEnum = ApplicationType.CONTINUATION;
                } else if (ProposalDevelopmentUtils.getProposalDevelopmentDocumentParameter(
                        ProposalDevelopmentUtils.PROPOSAL_TYPE_CODE_REVISION_PARM).equals(proposalTypeCode)) {
                    applicationTypeEnum = ApplicationType.REVISION;
                }
            }
            sf424V21.setApplicationType(applicationTypeEnum);
            String revisionType = s2sOpportunity.getRevisionCode();
            if (revisionType != null) {
                RevisionType.Enum revType = null;
                if (revisionType.equals(INCREASE_AWARD_CODE)) {                    
                    revType = RevisionType.A_INCREASE_AWARD;
                    
                } else if (revisionType.equals(DECREASE_AWARD_CODE)) {                    
                    revType = RevisionType.B_DECREASE_AWARD;
                    
                } else if (revisionType.equals(INCREASE_DURATION_CODE)) {
                    revType = RevisionType.C_INCREASE_DURATION;
                    
                } else if (revisionType.equals(DECREASE_DURATION_CODE)) {
                    revType = RevisionType.D_DECREASE_DURATION;
                    
                } else if (revisionType.equals(INCREASE_AWARD_DECREASE_DURATION_CODE)) {
                    revType = RevisionType.AD_INCREASE_AWARD_DECREASE_DURATION;
                    
                } else if (revisionType.equals(INCREASE_AWARD_INCREASE_DURATION_CODE)) {
                    revType = RevisionType.AC_INCREASE_AWARD_INCREASE_DURATION;
                    
                } else if (revisionType.equals(DECREASE_AWARD_DECREASE_DURATION_CODE)) {
                    revType = RevisionType.BD_DECREASE_AWARD_DECREASE_DURATION;
                    
                } else if (revisionType.equals(DECREASE_AWARD_INCREASE_DURATION_CODE)) {
                    revType = RevisionType.BC_DECREASE_AWARD_INCREASE_DURATION;
                    
                } else if (revisionType.equals(OTHER_SPECIFY_CODE)) {
                    revType = RevisionType.E_OTHER_SPECIFY;
                }

                if (revType != null) {
                    sf424V21.setRevisionType(revType);
                }
                if (revisionType.startsWith(REVISIONCODE_STARTS_WITH_E)) {
                    sf424V21.setRevisionOtherSpecify(s2sOpportunity.getRevisionOtherDescription());
                }
            }
        }
        sf424V21.setDateReceived(s2sUtilService.getCurrentCalendar());
        sf424V21.setApplicantID(pdDoc.getDevelopmentProposal().getProposalNumber());
		String federalId = s2sUtilService.getFederalId(pdDoc);
		if (federalId != null) {
        	sf424V21.setFederalEntityIdentifier(federalId);
		}

        Organization organization = null;
        organization = pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization();
        if (organization != null) {
            sf424V21.setOrganizationName(organization.getOrganizationName());
            sf424V21.setEmployerTaxpayerIdentificationNumber(organization.getFedralEmployerId());
            sf424V21.setDUNSNumber(organization.getDunsNumber());
            sf424V21.setOrganizationAffiliation(organization.getOrganizationName());
        } else {
            sf424V21.setOrganizationName(null);
            sf424V21.setEmployerTaxpayerIdentificationNumber(null);
            sf424V21.setDUNSNumber(null);
        }
        Rolodex rolodex = null;
        rolodex = pdDoc.getDevelopmentProposal().getApplicantOrganization().getRolodex();
        sf424V21.setApplicant(globLibV20Generator.getAddressDataType(rolodex));
        String departmentName = null;
        if (pdDoc.getDevelopmentProposal().getOwnedByUnit() != null) {
            departmentName = pdDoc.getDevelopmentProposal().getOwnedByUnit().getUnitName();
        }
        if (departmentName != null) {
            if (departmentName.length() > DEPARTMENT_NAME_MAX_LENGTH) {
                sf424V21.setDepartmentName(departmentName.substring(0, DEPARTMENT_NAME_MAX_LENGTH));
            } else {
                sf424V21.setDepartmentName(departmentName);
            }
        }
        String divisionName = s2sUtilService.getDivisionName(pdDoc);
        if (divisionName != null) {
            sf424V21.setDivisionName(divisionName);
        }
        ProposalPerson personInfo = s2sUtilService.getPrincipalInvestigator(pdDoc);
        if (personInfo != null) {
            sf424V21.setContactPerson(globLibV20Generator.getHumanNameDataType(personInfo));
            if (personInfo.getDirectoryTitle() != null) {
                sf424V21.setTitle(personInfo.getDirectoryTitle());
            }
            sf424V21.setPhoneNumber(personInfo.getOfficePhone());
            if (personInfo.getFaxNumber() != null) {
                sf424V21.setFax(personInfo.getFaxNumber());
            }
            sf424V21.setEmail(personInfo.getEmailAddress());
        } else {
            sf424V21.setPhoneNumber(null);
            sf424V21.setEmail(null);
        }

        setApplicatTypeCodes(sf424V21);

        if (pdDoc.getDevelopmentProposal().getSponsor() != null) {
            sf424V21.setAgencyName(pdDoc.getDevelopmentProposal().getSponsor().getSponsorName());
        }
        if (pdDoc.getDevelopmentProposal().getCfdaNumber() != null) {
            sf424V21.setCFDANumber(pdDoc.getDevelopmentProposal().getCfdaNumber());
        }
        if (pdDoc.getDevelopmentProposal().getProgramAnnouncementTitle() != null) {
            String announcementTitle;
            if (pdDoc.getDevelopmentProposal().getProgramAnnouncementTitle().length() > PROGRAM_ANNOUNCEMENT_TITLE_LENGTH) {
                announcementTitle = pdDoc.getDevelopmentProposal().getProgramAnnouncementTitle().substring(0, PROGRAM_ANNOUNCEMENT_TITLE_LENGTH);
            } else {
                announcementTitle = pdDoc.getDevelopmentProposal().getProgramAnnouncementTitle();
            }
            sf424V21.setCFDAProgramTitle(announcementTitle);
        }
        if (pdDoc.getDevelopmentProposal().getS2sOpportunity() != null) {
        	sf424V21.setFundingOpportunityNumber(pdDoc.getDevelopmentProposal()
					.getS2sOpportunity().getOpportunityId());
			if (pdDoc.getDevelopmentProposal().getS2sOpportunity()
					.getOpportunityTitle() != null) {
				sf424V21.setFundingOpportunityTitle(pdDoc
						.getDevelopmentProposal().getS2sOpportunity()
						.getOpportunityTitle());
			}
			if (pdDoc.getDevelopmentProposal().getS2sOpportunity().getCompetetionId() != null) {
                sf424V21.setCompetitionIdentificationNumber(pdDoc.getDevelopmentProposal().getS2sOpportunity().getCompetetionId());
            }
        } else {
            sf424V21.setFundingOpportunityTitle(null);
        }
        for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeTypeCode() != null
                    && Integer.parseInt(narrative.getNarrativeTypeCode()) == AREAS_AFFECTED_ATTACHMENT) {
                AttachedFileDataType attachedFileDataType = getAttachedFileType(narrative);
                if (attachedFileDataType != null) {
                    sf424V21.setAreasAffected(attachedFileDataType);
                    break;
                }
            }
        }
        sf424V21.setProjectTitle(pdDoc.getDevelopmentProposal().getTitle());
        AttachmentGroupMin0Max100DataType attachedFileMin0Max100 = AttachmentGroupMin0Max100DataType.Factory.newInstance();
        attachedFileMin0Max100.setAttachedFileArray(getAttachedFileDataTypes());
        sf424V21.setAdditionalProjectTitle(attachedFileMin0Max100);
        String congressionalDistrict = organization.getCongressionalDistrict() == null ? S2SConstants.VALUE_UNKNOWN : organization
                .getCongressionalDistrict();
        if (congressionalDistrict.length() > CONGRESSIONAL_DISTRICT_MAX_LENGTH) {
            sf424V21.setCongressionalDistrictApplicant(congressionalDistrict.substring(0, CONGRESSIONAL_DISTRICT_MAX_LENGTH));
        } else {
            sf424V21.setCongressionalDistrictApplicant(congressionalDistrict);
        }
        ProposalSite perfOrganization = pdDoc.getDevelopmentProposal().getPerformingOrganization();
        if (perfOrganization != null) {
            String congDistrictProject = perfOrganization.getFirstCongressionalDistrictName() == null ? S2SConstants.VALUE_UNKNOWN
                    : perfOrganization.getFirstCongressionalDistrictName();
            if (congDistrictProject.length() > CONGRESSIONAL_DISTRICT_MAX_LENGTH) {
                sf424V21.setCongressionalDistrictProgramProject(congDistrictProject.substring(0, CONGRESSIONAL_DISTRICT_MAX_LENGTH));
            } else {
                sf424V21.setCongressionalDistrictProgramProject(congDistrictProject);
            }
        }
        for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeTypeCode() != null
                    && Integer.parseInt(narrative.getNarrativeTypeCode()) == ADDITIONAL_CONGRESSIONAL_DISTRICTS_ATTACHMENT) {
                AttachedFileDataType attachedFileDataType = getAttachedFileType(narrative);
                if (attachedFileDataType != null) {
                	sf424V21.setAdditionalCongressionalDistricts(attachedFileDataType);
                	break;
                }
            }
        }
        if (pdDoc.getDevelopmentProposal().getRequestedStartDateInitial() != null) {
            sf424V21.setProjectStartDate(s2sUtilService.convertDateToCalendar(pdDoc.getDevelopmentProposal().getRequestedStartDateInitial()));
        } else {
            sf424V21.setProjectStartDate(null);
        }
        if (pdDoc.getDevelopmentProposal().getRequestedEndDateInitial() != null) {
            sf424V21.setProjectEndDate(s2sUtilService.convertDateToCalendar(pdDoc.getDevelopmentProposal().getRequestedEndDateInitial()));
        } else {
            sf424V21.setProjectEndDate(null);
        }

        Budget budget = null;
        try {
            BudgetDocument budgetDocument = s2sBudgetCalculatorService.getFinalBudgetVersion(pdDoc);
            budget = budgetDocument == null ? null : budgetDocument.getBudget();
        } catch (S2SException e) {
            LOG.error(e.getMessage(), e);
            return sf424V21;

        }
        if (budget != null) {
            if (budget.getTotalCost() != null) {
                sf424V21.setFederalEstimatedFunding(budget.getTotalCost().bigDecimalValue());
            }
            BudgetDecimal fedNonFedCost = budget.getTotalCost();
            BudgetDecimal costSharingAmount = BudgetDecimal.ZERO;

            for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
                for (BudgetLineItem lineItem : budgetPeriod.getBudgetLineItems()) {
                    hasBudgetLineItem = true;
                    if (budget.getSubmitCostSharingFlag() && lineItem.getSubmitCostSharingFlag()) {
                        costSharingAmount =  costSharingAmount.add(lineItem.getCostSharingAmount());
                        List<BudgetLineItemCalculatedAmount> calculatedAmounts = lineItem.getBudgetCalculatedAmounts();
                        for (BudgetLineItemCalculatedAmount budgetLineItemCalculatedAmount : calculatedAmounts) {
                            costSharingAmount =  costSharingAmount.add(budgetLineItemCalculatedAmount.getCalculatedCostSharing());
                        }                        
                    }
                }
            }
            if (!hasBudgetLineItem && budget.getSubmitCostSharingFlag()) {
                costSharingAmount = budget.getCostSharingAmount();      
            }
            fedNonFedCost = fedNonFedCost.add(costSharingAmount);
            sf424V21.setApplicantEstimatedFunding(costSharingAmount.bigDecimalValue());
            BigDecimal projectIncome = BigDecimal.ZERO;
            for (BudgetProjectIncome budgetProjectIncome : budget.getBudgetProjectIncomes()) {
                projectIncome = projectIncome.add(budgetProjectIncome.getProjectIncome().bigDecimalValue());
            }
            sf424V21.setProgramIncomeEstimatedFunding(projectIncome);

            BudgetDecimal totalEstimatedAmount = BudgetDecimal.ZERO;
            if (budget.getTotalCost() != null) {
                totalEstimatedAmount = totalEstimatedAmount.add(budget.getTotalCost());
            }
            totalEstimatedAmount = totalEstimatedAmount.add(costSharingAmount);
            totalEstimatedAmount = totalEstimatedAmount.add(new BudgetDecimal(projectIncome));
            sf424V21.setTotalEstimatedFunding(totalEstimatedAmount.bigDecimalValue());
        } else {
            sf424V21.setFederalEstimatedFunding(BigDecimal.ZERO);
            sf424V21.setApplicantEstimatedFunding(BigDecimal.ZERO);
            sf424V21.setProgramIncomeEstimatedFunding(BigDecimal.ZERO);
            sf424V21.setTotalEstimatedFunding(BigDecimal.ZERO);
        }
        sf424V21.setStateEstimatedFunding(BigDecimal.ZERO);
        sf424V21.setLocalEstimatedFunding(BigDecimal.ZERO);
        sf424V21.setOtherEstimatedFunding(BigDecimal.ZERO);
        sf424V21.setStateReview(getStateReviewCode());
        if (strReview != null && strReview.equals(STATE_REVIEW_YES)) {
            Calendar reviewDate = null;
            reviewDate = s2sUtilService.convertDateStringToCalendar(stateReviewDate);
            sf424V21.setStateReviewAvailableDate(reviewDate);
        }
        YesNoDataType.Enum yesNo = YesNoDataType.N_NO;
        Organization applicantOrganization = pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization();
        if (applicantOrganization != null) {
            for (OrganizationYnq orgYnq : applicantOrganization.getOrganizationYnqs()) {
                if (orgYnq.getQuestionId() != null && orgYnq.getQuestionId().equals(PROPOSAL_YNQ_FEDERAL_DEBTS)) {
                    String orgYnqanswer = orgYnq.getAnswer();
                    if (orgYnqanswer != null) {
                        if (orgYnqanswer.equalsIgnoreCase(ORGANIZATION_YNQ_ANSWER_YES)) {
                            yesNo = YesNoDataType.Y_YES;
                        } else {
                            yesNo = YesNoDataType.N_NO;
                        }
                    }
                    federalDebtExp = orgYnq.getExplanation();
                }
            }
        }
        sf424V21.setDelinquentFederalDebt(yesNo);
        for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeTypeCode() != null
                    && Integer.parseInt(narrative.getNarrativeTypeCode()) == DEBT_EXPLANATION_ATTACHMENT) {
                AttachedFileDataType attachedFileDataType = getAttachedFileType(narrative);
                if (attachedFileDataType != null) {
                    sf424V21.setDebtExplanation(attachedFileDataType);
                    break;
                }
            }
        }
        
        sf424V21.setCertificationAgree(YesNoDataType.Y_YES);
        sf424V21.setAuthorizedRepresentative(globLibV20Generator.getHumanNameDataType(aorInfo));
        if (aorInfo.getPrimaryTitle() != null) {
            if (aorInfo.getPrimaryTitle().length() > PRIMARY_TITLE_MAX_LENGTH) {
                sf424V21.setAuthorizedRepresentativeTitle(aorInfo.getPrimaryTitle().substring(0, PRIMARY_TITLE_MAX_LENGTH));
            } else {
                sf424V21.setAuthorizedRepresentativeTitle(aorInfo.getPrimaryTitle());
            }
        } else {
            sf424V21.setAuthorizedRepresentativeTitle(null);
        }
        sf424V21.setAuthorizedRepresentativePhoneNumber(aorInfo.getOfficePhone());
        sf424V21.setAuthorizedRepresentativeEmail(aorInfo.getEmailAddress());
        sf424V21.setAuthorizedRepresentativeFax(aorInfo.getFaxNumber());
        sf424V21.setAORSignature(aorInfo.getFullName());
        sf424V21.setDateSigned(s2sUtilService.getCurrentCalendar());
        return sf424V21;
    }

    private void setApplicatTypeCodes(SF42421 sf424V21) {
        Organization organization = pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization();
        if (organization.getOrganizationTypes() == null) {
            organization.refreshReferenceObject("organizationTypes");
        }
        List<OrganizationType> organizationTypes = organization.getOrganizationTypes();
        if (organizationTypes.isEmpty()) {
            sf424V21.setApplicantTypeCode1(null);
            return;
        }
        for (int i = 0; i < organizationTypes.size() && i < 3; i++) {
            OrganizationType orgType = organizationTypes.get(i);
            ApplicantTypeCodeDataType.Enum applicantTypeCode = ApplicantTypeCodeDataType.X_OTHER_SPECIFY;
            switch (orgType.getOrganizationTypeCode()) {
                case 1:
                    applicantTypeCode = ApplicantTypeCodeDataType.C_CITY_OR_TOWNSHIP_GOVERNMENT;
                    break;
                case 2:
                    applicantTypeCode = ApplicantTypeCodeDataType.A_STATE_GOVERNMENT;
                    break;
                case 3:
                    applicantTypeCode = ApplicantTypeCodeDataType.X_OTHER_SPECIFY;
                    applicantTypeOtherSpecify = "Federal Government";
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
                    applicantTypeOtherSpecify = "Socially and Economically Disadvantaged";
                    break;
                case 15:
                    applicantTypeCode = ApplicantTypeCodeDataType.X_OTHER_SPECIFY;
                    applicantTypeOtherSpecify = "Women owned";
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
            }
            switch (i) {
                case 0:
                    sf424V21.setApplicantTypeCode1(applicantTypeCode);
                    break;
                case 1:
                    sf424V21.setApplicantTypeCode2(applicantTypeCode);
                    break;
                case 2:
                    sf424V21.setApplicantTypeCode3(applicantTypeCode);
                    break;
            }
            if (applicantTypeOtherSpecify != null) {
                sf424V21.setApplicantTypeOtherSpecify(applicantTypeOtherSpecify);
            }
        }
    }

    /**
     * 
     * This method returns StateReviewCode status for the application.StateReviewCode can be Not covered,Not reviewed
     * 
     * @param proposalDevelopmentDocument (ProposalDevelopmentDocument)
     * @return stateType (StateReview.Enum) corresponding to state review code.
     */
    private StateReview.Enum getStateReviewCode() {
        Map<String, String> eoStateReview = s2sUtilService.getEOStateReview(pdDoc);
        StateReview.Enum stateType = null;
        String stateReviewData = null;
        strReview = eoStateReview.get(S2SConstants.YNQ_ANSWER);
        if (strReview != null) {
            if (strReview.equals(STATE_REVIEW_YES)) {
                stateType = StateReview.A_THIS_APPLICATION_WAS_MADE_AVAILABLE_TO_THE_STATE_UNDER_THE_EXECUTIVE_ORDER_12372_PROCESS_FOR_REVIEW_ON;
            } else if (strReview.equals(STATE_REVIEW_NO)) {
                stateReviewData = eoStateReview.get(S2SConstants.YNQ_STATE_REVIEW_DATA);
                if (stateReviewData != null && S2SConstants.YNQ_STATE_NOT_COVERED.equals(stateReviewData)) {
                    stateType = StateReview.C_PROGRAM_IS_NOT_COVERED_BY_E_O_12372;
                } else if (stateReviewData != null && S2SConstants.YNQ_STATE_NOT_SELECTED.equals(stateReviewData)) {
                    stateType = StateReview.B_PROGRAM_IS_SUBJECT_TO_E_O_12372_BUT_HAS_NOT_BEEN_SELECTED_BY_THE_STATE_FOR_REVIEW;
                }
            }
        }
        if (eoStateReview.get(S2SConstants.YNQ_REVIEW_DATE) != null) {
            stateReviewDate = eoStateReview.get(S2SConstants.YNQ_REVIEW_DATE);
        }
        return stateType;
    }

    /**
     * 
     * This method is used to get List of project title attachments from NarrativeAttachmentList
     * 
     * @param proposalDevelopmentDocument(ProposalDevelopmentDocument)
     * @return AttachedFileDataType[] array of attachments for project title attachment type.
     */
    private AttachedFileDataType[] getAttachedFileDataTypes() {
        List<AttachedFileDataType> attachedFileDataTypeList = new ArrayList<AttachedFileDataType>();
        AttachedFileDataType attachedFileDataType = null;
        for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeTypeCode() != null
                    && Integer.parseInt(narrative.getNarrativeTypeCode()) == ADDITIONAL_PROJECT_TITLE_ATTACHMENT) {
                attachedFileDataType = getAttachedFileType(narrative);
                if (attachedFileDataType != null) {
                	attachedFileDataTypeList.add(attachedFileDataType);
                }
            }
        }
        return attachedFileDataTypeList.toArray(new AttachedFileDataType[0]);
    }
   
    /**
     * This method creates {@link XmlObject} of type {@link SF424Document} by populating data from the given
     * {@link ProposalDevelopmentDocument}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocument}
     * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(ProposalDevelopmentDocument)
     */
    public XmlObject getFormObject(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        aorInfo = s2sUtilService.getDepartmentalPerson(pdDoc);
        return getSF42421Doc();
    }

    /**
     * This method typecasts the given {@link XmlObject} to the required generator type and returns back the document of that
     * generator type.
     * 
     * @param xmlObject which needs to be converted to the document type of the required generator
     * @return {@link XmlObject} document of the required generator type
     * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(XmlObject)
     */
    public XmlObject getFormObject(XmlObject xmlObject) {
        SF42421 sf42421 = (SF42421) xmlObject;
        SF42421Document sfDocument = SF42421Document.Factory.newInstance();
        sfDocument.setSF42421(sf42421);
        return sfDocument;
    }
}
