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

import gov.grants.apply.forms.sf424V20.SF424Document;
import gov.grants.apply.forms.sf424V20.SF424Document.SF424;
import gov.grants.apply.forms.sf424V20.SF424Document.SF424.ApplicationType;
import gov.grants.apply.forms.sf424V20.SF424Document.SF424.RevisionType;
import gov.grants.apply.forms.sf424V20.SF424Document.SF424.StateReview;
import gov.grants.apply.forms.sf424V20.SF424Document.SF424.SubmissionType;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.attachmentsV10.AttachmentGroupMin0Max100DataType;
import gov.grants.apply.system.globalLibraryV20.ApplicantTypeCodeDataType;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.proposaldevelopment.ProposalDevelopmentUtils;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.ProposalAbstract;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalSite;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.bo.S2sOpportunity;
import org.kuali.kra.s2s.bo.S2sSubmissionType;
import org.kuali.kra.s2s.generator.bo.DepartmentalPerson;
import org.kuali.kra.s2s.util.S2SConstants;

/**
 * This Class is used to generate XML object for grants.gov SF424V2.0. This form is generated using XMLBean classes and is based on
 * SF424-V2.0 schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class SF424V2_0Generator extends SF424BaseGenerator {

    private static final Log LOG = LogFactory.getLog(SF424V2_0Generator.class);

    private DepartmentalPerson aorInfo = null;
    private String applicantTypeOtherSpecify = null;
    private String federalDebtExp;
    private String stateReviewDate = null;
    private static final String ORGANIZATION_YNQ_ANSWER_YES = "Y";


    /**
     * 
     * This method returns SF424Document object based on proposal development document which contains the SF424Document information
     * for a particular proposal
     * 
     * @param proposalDevelopmentDocument (ProposalDevelopmentDocument)
     * @return SF424Document {@link XmlObject} of type SF424Document.
     */
    private SF424Document getSF424Doc() {
        SF424Document sf424Document = SF424Document.Factory.newInstance();
        sf424Document.setSF424(getSF424());
        return sf424Document;
    }

    /**
     * 
     * This method gets SF424 information for the form which includes informations regarding SubmissionTypeCode
     * ApplicationType,RevisionType,AgencyName,ApplicantID,CFDANumber,FederalEntityIdentifier,AuthorizedRepresentative.
     * 
     * @param proposalDevelopmentDocument (ProposalDevelopmentDocument)
     * @return sf424V2 object containing applicant and application details.
     */
    private SF424 getSF424() {

        SF424 sf424V2 = SF424.Factory.newInstance();
        sf424V2.setFormVersion(S2SConstants.FORMVERSION_2_0);
        S2sOpportunity s2sOpportunity = pdDoc.getDevelopmentProposal().getS2sOpportunity();
        if (s2sOpportunity != null && s2sOpportunity.getS2sSubmissionTypeCode() != null) {
            s2sOpportunity.refreshNonUpdateableReferences();
            S2sSubmissionType submissionType = s2sOpportunity.getS2sSubmissionType();
            SubmissionType.Enum subEnum = SubmissionType.Enum.forInt(Integer.parseInt(submissionType.getS2sSubmissionTypeCode()));
            sf424V2.setSubmissionType(subEnum);
            ApplicationType.Enum applicationTypeEnum = null;
            if (pdDoc.getDevelopmentProposal().getProposalTypeCode() != null) {
                String proposalTypeCode = pdDoc.getDevelopmentProposal().getProposalTypeCode();
                if(ProposalDevelopmentUtils.getProposalDevelopmentDocumentParameter(
                        ProposalDevelopmentUtils.PROPOSAL_TYPE_CODE_NEW_PARM).equals(proposalTypeCode)){
					applicationTypeEnum = ApplicationType.NEW;
                }else if(ProposalDevelopmentUtils.getProposalDevelopmentDocumentParameter(
                        ProposalDevelopmentUtils.PROPOSAL_TYPE_CODE_RESUBMISSION_PARM).equals(proposalTypeCode)){
					applicationTypeEnum = ApplicationType.REVISION;
                }else if(ProposalDevelopmentUtils.getProposalDevelopmentDocumentParameter(
                        ProposalDevelopmentUtils.PROPOSAL_TYPE_CODE_RENEWAL_PARM).equals(proposalTypeCode)){
                    applicationTypeEnum = ApplicationType.CONTINUATION;
                }else if(ProposalDevelopmentUtils.getProposalDevelopmentDocumentParameter(
                        ProposalDevelopmentUtils.PROPOSAL_TYPE_CODE_CONTINUATION_PARM).equals(proposalTypeCode)){
                    applicationTypeEnum = ApplicationType.CONTINUATION;
                }else if(ProposalDevelopmentUtils.getProposalDevelopmentDocumentParameter(
                        ProposalDevelopmentUtils.PROPOSAL_TYPE_CODE_REVISION_PARM).equals(proposalTypeCode)){
                    applicationTypeEnum = ApplicationType.REVISION;
                }
            }
            sf424V2.setApplicationType(applicationTypeEnum);
            String revisionType = s2sOpportunity.getRevisionCode();
            if (revisionType != null) {
                RevisionType.Enum revType = null;
                if (revisionType.equals(INCREASE_AWARD_CODE)) {
                    revType = RevisionType.A_INCREASE_AWARD;
                }
                else if (revisionType.equals(DECREASE_AWARD_CODE)) {
                    revType = RevisionType.B_DECREASE_AWARD;
                }
                else if (revisionType.equals(INCREASE_DURATION_CODE)) {
                    revType = RevisionType.C_INCREASE_DURATION;
                }
                else if (revisionType.equals(DECREASE_DURATION_CODE)) {
                    revType = RevisionType.D_DECREASE_DURATION;
                }
                else if (revisionType.equals(INCREASE_AWARD_DECREASE_DURATION_CODE)) {
                    revType = RevisionType.AD_INCREASE_AWARD_DECREASE_DURATION;
                }
                else if (revisionType.equals(INCREASE_AWARD_INCREASE_DURATION_CODE)) {
                    revType = RevisionType.AC_INCREASE_AWARD_INCREASE_DURATION;
                }
                else if (revisionType.equals(DECREASE_AWARD_DECREASE_DURATION_CODE)) {
                    revType = RevisionType.BD_DECREASE_AWARD_DECREASE_DURATION;
                }
                else if (revisionType.equals(DECREASE_AWARD_INCREASE_DURATION_CODE)) {
                    revType = RevisionType.BC_DECREASE_AWARD_INCREASE_DURATION;
                }
                else if (revisionType.equals(OTHER_SPECIFY_CODE)) {
                    revType = RevisionType.E_OTHER_SPECIFY;
                }

                if (revType != null) {
                    sf424V2.setRevisionType(revType);
                }
                if (revisionType.startsWith(REVISIONCODE_STARTS_WITH_E)) {
                    sf424V2.setRevisionOtherSpecify(s2sOpportunity.getRevisionOtherDescription());
                }
            }
        }
        sf424V2.setDateReceived(s2sUtilService.getCurrentCalendar());
        sf424V2.setApplicantID(pdDoc.getDevelopmentProposal().getProposalNumber());
		String federalId = s2sUtilService.getFederalId(pdDoc);
		if (federalId != null) {
        	sf424V2.setFederalEntityIdentifier(federalId);
		}

        Organization organization = null;
        organization = pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization();
        if (organization != null) {
            sf424V2.setOrganizationName(organization.getOrganizationName());
            sf424V2.setEmployerTaxpayerIdentificationNumber(organization.getFedralEmployerId());
            sf424V2.setDUNSNumber(organization.getDunsNumber());
            sf424V2.setOrganizationAffiliation(organization.getOrganizationName());
        }
        else {
            sf424V2.setOrganizationName(null);
            sf424V2.setEmployerTaxpayerIdentificationNumber(null);
            sf424V2.setDUNSNumber(null);
        }
        Rolodex rolodex = null;
        rolodex = pdDoc.getDevelopmentProposal().getApplicantOrganization().getRolodex();
        sf424V2.setApplicant(globLibV20Generator.getAddressDataType(rolodex));
        String departmentName = null;
        if (pdDoc.getDevelopmentProposal().getOwnedByUnit() != null) {
            departmentName = pdDoc.getDevelopmentProposal().getOwnedByUnit().getUnitName();
        }
        if (departmentName != null) {
            if (departmentName.length() > DEPARTMENT_NAME_MAX_LENGTH) {
                sf424V2.setDepartmentName(departmentName.substring(0, DEPARTMENT_NAME_MAX_LENGTH));
            }
            else {
                sf424V2.setDepartmentName(departmentName);
            }
        }
        String divisionName = s2sUtilService.getDivisionName(pdDoc);
        if (divisionName != null) {
            sf424V2.setDivisionName(divisionName);
        }
        ProposalPerson personInfo = s2sUtilService.getPrincipalInvestigator(pdDoc);
        if (personInfo != null) {
            sf424V2.setContactPerson(globLibV20Generator.getHumanNameDataType(personInfo));
            if (personInfo.getDirectoryTitle() != null) {
                sf424V2.setTitle(personInfo.getDirectoryTitle());
            }
            sf424V2.setPhoneNumber(personInfo.getOfficePhone());
            if (personInfo.getFaxNumber() != null) {
                sf424V2.setFax(personInfo.getFaxNumber());
            }
            sf424V2.setEmail(personInfo.getEmailAddress());
        }
        else {
            sf424V2.setPhoneNumber(null);
            sf424V2.setEmail(null);
        }

        setApplicatTypeCodes(sf424V2);

        if (pdDoc.getDevelopmentProposal().getSponsor() != null) {
            sf424V2.setAgencyName(pdDoc.getDevelopmentProposal().getSponsor().getSponsorName());
        }
        if(pdDoc.getDevelopmentProposal().getCfdaNumber()!=null){
            sf424V2.setCFDANumber(pdDoc.getDevelopmentProposal().getCfdaNumber());
        }
        if (pdDoc.getDevelopmentProposal().getProgramAnnouncementTitle() != null) {
            String announcementTitle;
            if (pdDoc.getDevelopmentProposal().getProgramAnnouncementTitle().length() > PROGRAM_ANNOUNCEMENT_TITLE_LENGTH) {
                announcementTitle = pdDoc.getDevelopmentProposal().getProgramAnnouncementTitle().substring(0, PROGRAM_ANNOUNCEMENT_TITLE_LENGTH);
            }
            else {
                announcementTitle = pdDoc.getDevelopmentProposal().getProgramAnnouncementTitle();
            }
            sf424V2.setCFDAProgramTitle(announcementTitle);
        }
        if (pdDoc.getDevelopmentProposal().getS2sOpportunity() != null) {
        	sf424V2.setFundingOpportunityNumber(pdDoc.getDevelopmentProposal()
					.getS2sOpportunity().getOpportunityId());
			if (pdDoc.getDevelopmentProposal().getS2sOpportunity()
					.getOpportunityTitle() != null) {
				sf424V2.setFundingOpportunityTitle(pdDoc
						.getDevelopmentProposal().getS2sOpportunity()
						.getOpportunityTitle());
			}
			if (pdDoc.getDevelopmentProposal().getS2sOpportunity().getCompetetionId() != null) {
                sf424V2.setCompetitionIdentificationNumber(pdDoc.getDevelopmentProposal().getS2sOpportunity().getCompetetionId());
            }
        }
        else {
            sf424V2.setFundingOpportunityTitle(null);
        }
        String areasAffected = null;
        for (ProposalAbstract proposalAbstract : pdDoc.getDevelopmentProposal().getProposalAbstracts()) {
            if (proposalAbstract.getAbstractTypeCode() != null
                    && Integer.parseInt(proposalAbstract.getAbstractTypeCode()) == ABSTRACTTYPE_CODE_AREAS_AFFECTED) {
                areasAffected = proposalAbstract.getAbstractDetails();
                if (areasAffected != null && areasAffected.length() > AREAS_AFFECTED_MAX_LENGTH) {
                    sf424V2.setAffectedAreas(areasAffected.substring(0, AREAS_AFFECTED_MAX_LENGTH));
                }
                else {
                    sf424V2.setAffectedAreas(areasAffected);
                }
            }
        }
        sf424V2.setProjectTitle(pdDoc.getDevelopmentProposal().getTitle());
        AttachmentGroupMin0Max100DataType attachedFileMin0Max100 = AttachmentGroupMin0Max100DataType.Factory.newInstance();
        attachedFileMin0Max100.setAttachedFileArray(getAttachedFileDataTypes());
        sf424V2.setAdditionalProjectTitle(attachedFileMin0Max100);
        String congressionalDistrict = organization.getCongressionalDistrict() == null ? S2SConstants.VALUE_UNKNOWN : organization
                .getCongressionalDistrict();
        if (congressionalDistrict.length() > CONGRESSIONAL_DISTRICT_MAX_LENGTH) {
            sf424V2.setCongressionalDistrictApplicant(congressionalDistrict.substring(0, CONGRESSIONAL_DISTRICT_MAX_LENGTH));
        }
        else {
            sf424V2.setCongressionalDistrictApplicant(congressionalDistrict);
        }
        ProposalSite perfOrganization = pdDoc.getDevelopmentProposal().getPerformingOrganization();
        if (perfOrganization != null) {
            String congDistrictProject = perfOrganization.getFirstCongressionalDistrictName() == null ? S2SConstants.VALUE_UNKNOWN
                    : perfOrganization.getFirstCongressionalDistrictName();
            if (congDistrictProject.length() > CONGRESSIONAL_DISTRICT_MAX_LENGTH) {
                sf424V2.setCongressionalDistrictProgramProject(congDistrictProject.substring(0, CONGRESSIONAL_DISTRICT_MAX_LENGTH));
            }
            else {
                sf424V2.setCongressionalDistrictProgramProject(congDistrictProject);
            }
        }
        for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeTypeCode() != null
                    && Integer.parseInt(narrative.getNarrativeTypeCode()) == CONGRESSIONAL_DISTRICTS_ATTACHMENT) {
                AttachedFileDataType attachedFileDataType = getAttachedFileType(narrative);
                if(attachedFileDataType != null){
                	sf424V2.setAdditionalCongressionalDistricts(attachedFileDataType);
                	break;
                }
            }
        }
        if (pdDoc.getDevelopmentProposal().getRequestedStartDateInitial() != null) {
            sf424V2.setProjectStartDate(s2sUtilService.convertDateToCalendar(pdDoc.getDevelopmentProposal().getRequestedStartDateInitial()));
        }
        else {
            sf424V2.setProjectStartDate(null);
        }
        if (pdDoc.getDevelopmentProposal().getRequestedEndDateInitial() != null) {
            sf424V2.setProjectEndDate(s2sUtilService.convertDateToCalendar(pdDoc.getDevelopmentProposal().getRequestedEndDateInitial()));
        }
        else {
            sf424V2.setProjectEndDate(null);
        }

        Budget budgetDoc = null;
        try {
            BudgetDocument budgetDocument = s2sBudgetCalculatorService.getFinalBudgetVersion(pdDoc);
            budgetDoc = budgetDocument==null?null:budgetDocument.getBudget();
        }catch (S2SException e) {
            LOG.error(e.getMessage(), e);
            return sf424V2;

        }
        if (budgetDoc != null) {
            if (budgetDoc.getTotalCost() != null) {
                sf424V2.setFederalEstimatedFunding(budgetDoc.getTotalCost().bigDecimalValue());
            }
            BudgetDecimal fedNonFedCost = BudgetDecimal.ZERO;
            BudgetDecimal totalfedNonFedCost = BudgetDecimal.ZERO;
            if (budgetDoc.getCostSharingAmount() != null) {
                for (BudgetPeriod budgetPeriod : budgetDoc.getBudgetPeriods()) {
                    for (BudgetLineItem lineItem : budgetPeriod.getBudgetLineItems()) {
                        if(budgetDoc.getSubmitCostSharingFlag() && lineItem.getSubmitCostSharingFlag())
                        fedNonFedCost=fedNonFedCost.add(lineItem.getCostSharingAmount());
                        }
                }
                sf424V2.setApplicantEstimatedFunding(fedNonFedCost.bigDecimalValue());
            }
            BigDecimal projectIncome = BigDecimal.ZERO;
            for (BudgetProjectIncome budgetProjectIncome : budgetDoc.getBudgetProjectIncomes()) {
                projectIncome = projectIncome.add(budgetProjectIncome.getProjectIncome().bigDecimalValue());
            }
            sf424V2.setProgramIncomeEstimatedFunding(projectIncome);

            BudgetDecimal totalEstimatedAmount = BudgetDecimal.ZERO;
            if (budgetDoc.getTotalCost() != null) {
                totalEstimatedAmount = totalEstimatedAmount.add(budgetDoc.getTotalCost());
            }
            if (budgetDoc.getCostSharingAmount() != null) {
                for (BudgetPeriod budgetPeriod : budgetDoc.getBudgetPeriods()) {
                    for (BudgetLineItem lineItem : budgetPeriod.getBudgetLineItems()) {
                        if(budgetDoc.getSubmitCostSharingFlag() && lineItem.getSubmitCostSharingFlag())
                            totalfedNonFedCost=totalfedNonFedCost.add(lineItem.getCostSharingAmount());
                        }
                }
                totalEstimatedAmount = totalEstimatedAmount.add(totalfedNonFedCost);
            }
            totalEstimatedAmount = totalEstimatedAmount.add(new BudgetDecimal(projectIncome));
            sf424V2.setTotalEstimatedFunding(totalEstimatedAmount.bigDecimalValue());
        }
        else {
            sf424V2.setFederalEstimatedFunding(BigDecimal.ZERO);
            sf424V2.setApplicantEstimatedFunding(BigDecimal.ZERO);
            sf424V2.setProgramIncomeEstimatedFunding(BigDecimal.ZERO);
            sf424V2.setTotalEstimatedFunding(BigDecimal.ZERO);
        }
        sf424V2.setStateEstimatedFunding(BigDecimal.ZERO);
        sf424V2.setLocalEstimatedFunding(BigDecimal.ZERO);
        sf424V2.setOtherEstimatedFunding(BigDecimal.ZERO);
        sf424V2.setStateReview(getStateReviewCode());
        if (stateReviewDate != null) {
            sf424V2.setStateReviewAvailableDate(s2sUtilService.convertDateStringToCalendar(stateReviewDate));
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
                        }
                        else {
                            yesNo = YesNoDataType.N_NO;
                        }
                    }
                    federalDebtExp = orgYnq.getExplanation();
                }
            }
        }
        sf424V2.setDelinquentFederalDebt(yesNo);
        if (sf424V2.getDelinquentFederalDebt() != null && sf424V2.getDelinquentFederalDebt().equals(YesNoDataType.Y_YES)) {
            sf424V2.setDelinquentFederalDebtExplanation(federalDebtExp);
        }
        sf424V2.setCertificationAgree(YesNoDataType.Y_YES);
        sf424V2.setAuthorizedRepresentative(globLibV20Generator.getHumanNameDataType(aorInfo));
        if (aorInfo.getPrimaryTitle() != null) {
            if (aorInfo.getPrimaryTitle().length() > PRIMARY_TITLE_MAX_LENGTH) {
                sf424V2.setAuthorizedRepresentativeTitle(aorInfo.getPrimaryTitle().substring(0, PRIMARY_TITLE_MAX_LENGTH));
            }
            else {
                sf424V2.setAuthorizedRepresentativeTitle(aorInfo.getPrimaryTitle());
            }
        }
        else {
            sf424V2.setAuthorizedRepresentativeTitle(null);
        }
        sf424V2.setAuthorizedRepresentativePhoneNumber(aorInfo.getOfficePhone());
        sf424V2.setAuthorizedRepresentativeEmail(aorInfo.getEmailAddress());
        sf424V2.setAuthorizedRepresentativeFax(aorInfo.getFaxNumber());
        sf424V2.setAORSignature(aorInfo.getFullName());
        sf424V2.setDateSigned(s2sUtilService.getCurrentCalendar());
        return sf424V2;
    }

    private void setApplicatTypeCodes(SF424 sf424V2) {
        Organization organization = pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization();
        if (organization.getOrganizationTypes() == null) {
            organization.refreshReferenceObject("organizationTypes");
        }
        List<OrganizationType> organizationTypes = organization.getOrganizationTypes();
        if (organizationTypes.isEmpty()) {
            sf424V2.setApplicantTypeCode1(null);
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
                    sf424V2.setApplicantTypeCode1(applicantTypeCode);
                    break;
                case 1:
                    sf424V2.setApplicantTypeCode2(applicantTypeCode);
                    break;
                case 2:
                    sf424V2.setApplicantTypeCode3(applicantTypeCode);
                    break;
            }
            if (applicantTypeOtherSpecify != null) {
                sf424V2.setApplicantTypeOtherSpecify(applicantTypeOtherSpecify);
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
        String strReview = eoStateReview.get(S2SConstants.YNQ_ANSWER);
        if (strReview != null) {
            if (strReview.equals(STATE_REVIEW_NA)) {
                stateType = StateReview.C_PROGRAM_IS_NOT_COVERED_BY_E_O_12372;
            }
            else if (strReview.equals(STATE_REVIEW_YES)) {
                stateType = StateReview.A_THIS_APPLICATION_WAS_MADE_AVAILABLE_TO_THE_STATE_UNDER_THE_EXECUTIVE_ORDER_12372_PROCESS_FOR_REVIEW_ON;
            }
            else {
                stateType = StateReview.B_PROGRAM_IS_SUBJECT_TO_E_O_12372_BUT_HAS_NOT_BEEN_SELECTED_BY_THE_STATE_FOR_REVIEW;
            }
        }
        else {
            stateType = StateReview.B_PROGRAM_IS_SUBJECT_TO_E_O_12372_BUT_HAS_NOT_BEEN_SELECTED_BY_THE_STATE_FOR_REVIEW;
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
                    && Integer.parseInt(narrative.getNarrativeTypeCode()) == PROJECT_TITLE_ATTACHMENT) {
                attachedFileDataType = getAttachedFileType(narrative);
                if(attachedFileDataType != null){
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
        return getSF424Doc();
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
        SF424 sf424 = (SF424) xmlObject;
        SF424Document sfDocument = SF424Document.Factory.newInstance();
        sfDocument.setSF424(sf424);
        return sfDocument;
    }
}
