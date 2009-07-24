/*
 * Copyright 2008 The Kuali Foundation.
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

import gov.grants.apply.forms.rrSF424V10.AORInfoType;
import gov.grants.apply.forms.rrSF424V10.ApplicantTypeCodeDataType;
import gov.grants.apply.forms.rrSF424V10.ApplicationTypeCodeDataType;
import gov.grants.apply.forms.rrSF424V10.OrganizationContactPersonDataType;
import gov.grants.apply.forms.rrSF424V10.RRSF424Document;
import gov.grants.apply.forms.rrSF424V10.StateReviewCodeTypeDataType;
import gov.grants.apply.forms.rrSF424V10.SubmissionTypeDataType;
import gov.grants.apply.forms.rrSF424V10.RRSF424Document.RRSF424;
import gov.grants.apply.forms.rrSF424V10.RRSF424Document.RRSF424.ApplicantInfo;
import gov.grants.apply.forms.rrSF424V10.RRSF424Document.RRSF424.ApplicantType;
import gov.grants.apply.forms.rrSF424V10.RRSF424Document.RRSF424.ApplicationType;
import gov.grants.apply.forms.rrSF424V10.RRSF424Document.RRSF424.EstimatedProjectFunding;
import gov.grants.apply.forms.rrSF424V10.RRSF424Document.RRSF424.StateReview;
import gov.grants.apply.forms.rrSF424V10.RRSF424Document.RRSF424.ApplicantInfo.ContactPersonInfo;
import gov.grants.apply.forms.rrSF424V10.RRSF424Document.RRSF424.ApplicantType.SmallBusinessOrganizationType;
import gov.grants.apply.forms.rrSF424V10.RRSF424Document.RRSF424.ApplicantType.SmallBusinessOrganizationType.IsSociallyEconomicallyDisadvantaged;
import gov.grants.apply.forms.rrSF424V10.RRSF424Document.RRSF424.ApplicantType.SmallBusinessOrganizationType.IsWomenOwned;
import gov.grants.apply.forms.rrSF424V10.RRSF424Document.RRSF424.ApplicationType.OtherAgencySubmissionExplanation;
import gov.grants.apply.forms.rrSF424V10.RRSF424Document.RRSF424.ApplicationType.RevisionCode;
import gov.grants.apply.forms.rrSF424V10.RRSF424Document.RRSF424.ApplicationType.RevisionCodeOtherExplanation;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.globalLibraryV10.OrganizationDataTypeV2;
import gov.grants.apply.system.globalLibraryV10.YesNoDataType;
import gov.grants.apply.system.globalLibraryV10.YesNoDataType.Enum;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.UnitAdministrator;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetModularIdc;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetProjectIncome;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.proposaldevelopment.bo.ProposalYnq;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.bo.S2sOpportunity;
import org.kuali.kra.s2s.generator.bo.DepartmentalPerson;
import org.kuali.kra.s2s.util.S2SConstants;

/**
 * Class for generating the XML object for grants.gov RRSF424V1_0. Form is generated using XMLBean classes and is based on
 * RRSF424V1_0 schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class RRSF424V1_0Generator extends RRSF424BaseGenerator {

    private DepartmentalPerson departmentalPerson;
    private static final Logger LOG = Logger.getLogger(RRSF424V1_0Generator.class);

    /**
     * 
     * This method gives information of applications that are used in RRSF424
     * 
     * @return rrSF424Document {@link XmlObject} of type RRSF424Document.
     */
    private RRSF424Document getRRSF424() {

        RRSF424Document rrSF424Document = RRSF424Document.Factory.newInstance();
        RRSF424 rrsf424 = RRSF424.Factory.newInstance();
        rrsf424.setFormVersion(S2SConstants.FORMVERSION_1_0);
        S2sOpportunity s2sOpportunity = pdDoc.getDevelopmentProposal().getS2sOpportunity();
        if (s2sOpportunity != null && s2sOpportunity.getS2sSubmissionTypeCode() != null) {
            s2sOpportunity.refreshNonUpdateableReferences();
            rrsf424.setSubmissionTypeCode(SubmissionTypeDataType.Enum.forString(pdDoc.getDevelopmentProposal().getS2sOpportunity().getS2sSubmissionType()
                    .getDescription()));
        }
        rrsf424.setSubmittedDate(s2sUtilService.getCurrentCalendar());
        Rolodex rolodex = pdDoc.getDevelopmentProposal().getOrganization().getRolodex();
        if (rolodex != null) {
            rrsf424.setStateID(rolodex.getState());
        }
        String federalId = s2sUtilService.getFederalId(pdDoc);
        if (federalId != null && !federalId.equals(S2SConstants.FEDERAL_ID_NOT_FOUND)) {
            rrsf424.setFederalID(federalId);
        }
        rrsf424.setApplicantInfo(getApplicationInfo());
        rrsf424.setApplicantType(getApplicantType());
        rrsf424.setApplicationType(getApplicationType());
        Organization organization = pdDoc.getDevelopmentProposal().getOrganization();
        if (organization != null) {
            rrsf424.setEmployerID(organization.getFedralEmployerId());
        }
        Sponsor sponsor = pdDoc.getDevelopmentProposal().getSponsor();
        if (sponsor != null) {
            rrsf424.setFederalAgencyName(sponsor.getSponsorName());
        }
        rrsf424.setCFDANumber(pdDoc.getDevelopmentProposal().getCfdaNumber());
        if (pdDoc.getDevelopmentProposal().getProgramAnnouncementTitle() != null) {
            String announcementTitle;
            if (pdDoc.getDevelopmentProposal().getProgramAnnouncementTitle().length() > PROGRAM_ANNOUNCEMENT_TITLE_MAX_LENGTH) {
                announcementTitle = pdDoc.getDevelopmentProposal().getProgramAnnouncementTitle().substring(0, PROGRAM_ANNOUNCEMENT_TITLE_MAX_LENGTH);
            }
            else {
                announcementTitle = pdDoc.getDevelopmentProposal().getProgramAnnouncementTitle();
            }
            rrsf424.setActivityTitle(announcementTitle);
        }
        rrsf424.setProjectTitle(pdDoc.getDevelopmentProposal().getTitle());
        if (pdDoc.getDevelopmentProposal().getPerformingOrganization() != null) {
            Rolodex rolodexOrganization = pdDoc.getDevelopmentProposal().getPerformingOrganization().getRolodex();
            if (rolodexOrganization != null) {
                rrsf424.setLocation(rolodexOrganization.getState());
            }
        }

        rrsf424.setProposedProjectPeriod(getProjectPeriod());
        rrsf424.setCongressionalDistrict(getCongDistrict());
        rrsf424.setPDPIContactInfo(getPDPI());
        try {
            rrsf424.setEstimatedProjectFunding(getProjectFunding());
        }
        catch (S2SException e) {
            LOG.error(e.getMessage(), e);
            return rrSF424Document;
        }
        rrsf424.setStateReview(getStateReview());
        // Value is hardcoded
        rrsf424.setTrustAgree(YesNoDataType.YES);
        rrsf424.setAORInfo(getAORInfoType());
        for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeTypeCode() != null && Integer.parseInt(narrative.getNarrativeTypeCode()) == PRE_APPLICATION) {
                AttachedFileDataType preAttachment = AttachedFileDataType.Factory.newInstance();
                preAttachment = getAttachedFileType(narrative);
                rrsf424.setPreApplicationAttachment(preAttachment);
            }
        }
        if (departmentalPerson != null) {
            rrsf424.setAORSignature(departmentalPerson.getFullName());
        }
        else {
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
        BudgetDocument budgetDoc = s2sBudgetCalculatorService.getFinalBudgetVersion(pdDoc);
        EstimatedProjectFunding funding = EstimatedProjectFunding.Factory.newInstance();
        funding.setTotalEstimatedAmount(BigDecimal.ZERO);
        funding.setTotalfedNonfedrequested(BigDecimal.ZERO);
        funding.setEstimatedProgramIncome(BigDecimal.ZERO);
        
        if (budgetDoc != null) {
            if (budgetDoc.getModularBudgetFlag()) {
                BudgetDecimal fundsRequested = BudgetDecimal.ZERO;
                BudgetDecimal totalDirectCost = BudgetDecimal.ZERO;
                BudgetDecimal totalCost = BudgetDecimal.ZERO;
                // get modular budget amounts instead of budget detail amounts
                for (BudgetPeriod budgetPeriod : budgetDoc.getBudgetPeriods()) {
                    totalDirectCost.add(budgetPeriod.getBudgetModular().getTotalDirectCost());
                    for (BudgetModularIdc budgetModularIdc : budgetPeriod.getBudgetModular().getBudgetModularIdcs()) {
                        fundsRequested.add(budgetModularIdc.getFundsRequested());
                    }
                }
                totalCost.add(totalDirectCost);
                totalCost.add(fundsRequested);
                budgetDoc.setTotalIndirectCost(fundsRequested);
                budgetDoc.setTotalCost(totalCost);
            }

            BudgetDecimal fedNonFedCost = BudgetDecimal.ZERO;
            fedNonFedCost.add(budgetDoc.getTotalCost());
            fedNonFedCost.add(budgetDoc.getCostSharingAmount());

            BigDecimal totalProjectIncome = BigDecimal.ZERO;
            for (BudgetProjectIncome budgetProjectIncome : budgetDoc.getBudgetProjectIncomes()) {
                if (budgetProjectIncome.getProjectIncome() != null) {
                    totalProjectIncome = totalProjectIncome.add(budgetProjectIncome.getProjectIncome().bigDecimalValue());
                }
            }
            funding.setTotalEstimatedAmount(budgetDoc.getTotalCost().bigDecimalValue());
            funding.setTotalfedNonfedrequested(fedNonFedCost.bigDecimalValue());
            funding.setEstimatedProgramIncome(totalProjectIncome);
        }
        return funding;
    }

    /**
     * 
     * This method gives the information for an application which consists of personal details
     * 
     * @return ApplicantInfo applicant details.
     */
    private ApplicantInfo getApplicationInfo() {
        ApplicantInfo appInfo = ApplicantInfo.Factory.newInstance();
        String contactType = getContactType();
        if (CONTACT_TYPE_I.equals(contactType)) {
            // use organization rolodex contact
            if (pdDoc.getDevelopmentProposal().getOrganization() != null) {
                appInfo.setContactPersonInfo(getContactInfo(pdDoc.getDevelopmentProposal().getOrganization().getRolodex()));
            }
        }
        else {
            // contact will come from unit or unit_administrators
            DepartmentalPerson depPerson = getContactPerson(pdDoc, contactType);
            ContactPersonInfo contactInfo = ContactPersonInfo.Factory.newInstance();
            if (depPerson != null) {
                contactInfo.setName(globLibV10Generator.getHumanNameDataType(depPerson));
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
        OrganizationDataTypeV2 orgType = OrganizationDataTypeV2.Factory.newInstance();
        Rolodex rolodex = pdDoc.getDevelopmentProposal().getOrganization().getRolodex();
        orgType.setAddress(globLibV10Generator.getAddressRequireCountryDataType(rolodex));

        Organization organization = pdDoc.getDevelopmentProposal().getOrganization();
        if (organization != null) {
            orgType.setOrganizationName(organization.getOrganizationName());
            orgType.setDUNSID(organization.getDunsNumber());
        }
        if (pdDoc.getDevelopmentProposal().getOwnedByUnit() != null) {
            String departmentName = pdDoc.getDevelopmentProposal().getOwnedByUnit().getUnitName();
            if (departmentName != null && departmentName.length() > DEPARTMENT_NAME_MAX_LENGTH) {
                departmentName = departmentName.substring(0, DEPARTMENT_NAME_MAX_LENGTH - 1);
            }
            if (departmentName != null) {
                orgType.setDepartmentName(departmentName);
            }
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
     * This method is used to get the details of Contact person
     * 
     * @param pdDoc(ProposalDevelopmentDocument) proposal development document.
     * @param contactType(String) for which the DepartmentalPerson has to be found.
     * @return depPerson(DepartmentalPerson) corresponding to the contact type.
     */
    private DepartmentalPerson getContactPerson(ProposalDevelopmentDocument pdDoc, String contactType) {
        boolean isNumber = true;
        try {
            Integer.parseInt(contactType);
        }
        catch (NumberFormatException e) {
            isNumber = false;
        }
        DepartmentalPerson depPerson = new DepartmentalPerson();
        if (isNumber) {
            for (ProposalPerson person : pdDoc.getDevelopmentProposal().getProposalPersons()) {
                for (ProposalPersonUnit unit : person.getUnits()) {
                    if (unit.isLeadUnit()) {
                        for (UnitAdministrator admin : unit.getUnit().getUnitAdministrators()) {
                            if (admin.getUnitAdministratorTypeCode().equals(contactType)) {
                                depPerson.setLastName(person.getLastName());
                                depPerson.setFirstName(person.getFirstName());
                                depPerson.setMiddleName(person.getMiddleName());
                                depPerson.setEmailAddress(person.getEmailAddress());
                                depPerson.setOfficePhone(person.getOfficePhone());
                                depPerson.setFaxNumber(person.getFaxNumber());
                            }
                        }
                    }
                }
            }
        }
        return depPerson;
    }

    /**
     * 
     * This method is used to get Contact person information
     * 
     * @param rolodex(Rolodex)
     * @return ContactPersonInfo corresponding to the Rolodex object.
     */
    private ContactPersonInfo getContactInfo(Rolodex rolodexOrganization) {
        ContactPersonInfo contactInfo = ContactPersonInfo.Factory.newInstance();
        contactInfo.setName(globLibV10Generator.getHumanNameDataType(rolodexOrganization));
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
        Map<String, String> eoStateReview = s2sUtilService.getEOStateReview(pdDoc);
        StateReviewCodeTypeDataType.Enum stateReviewCodeType = null;
        String strReview = eoStateReview.get(S2SConstants.YNQ_ANSWER);
        if (STATE_REVIEW_YES.equals(strReview)) {
            stateReviewCodeType = StateReviewCodeTypeDataType.YES;
        }
        else if (STATE_REVIEW_NO.equals(strReview)) {
            stateReviewCodeType = StateReviewCodeTypeDataType.PROGRAM_HAS_NOT_BEEN_SELECTED_BY_STATE_FOR_REVIEW;
        }
        else {
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

        if (pdDoc.getDevelopmentProposal().getProposalTypeCode() != null && Integer.parseInt(pdDoc.getDevelopmentProposal().getProposalTypeCode()) < PROPOSAL_TYPE_CODE_6) {
            // Check <6 to ensure that if proposalType='TASk ORDER", it must not set. THis is because enum ApplicationType has no
            // entry for TASK ORDER
            ApplicationTypeCodeDataType.Enum applicationTypeCodeDataType = ApplicationTypeCodeDataType.Enum.forInt(Integer
                    .parseInt(pdDoc.getDevelopmentProposal().getProposalTypeCode()));
            applicationType.setApplicationTypeCode(applicationTypeCodeDataType);

            Map<String, String> submissionInfo = s2sUtilService.getSubmissionType(pdDoc);
            if (Integer.parseInt(pdDoc.getDevelopmentProposal().getProposalTypeCode()) == ApplicationTypeCodeDataType.INT_REVISION) {
                String revisionCode = null;
                if (submissionInfo.get(S2SConstants.KEY_REVISION_CODE) != null) {
                    revisionCode = submissionInfo.get(S2SConstants.KEY_REVISION_CODE);
                    RevisionCode revisionCodeApplication = RevisionCode.Factory.newInstance();
                    revisionCodeApplication.setStringValue(revisionCode);
                    applicationType.setRevisionCode(revisionCodeApplication);
                }
            }

            if (Integer.parseInt(pdDoc.getDevelopmentProposal().getProposalTypeCode()) == ApplicationTypeCodeDataType.INT_RESUBMISSION) {
                String revisionCodeOtherDesc = null;
                if (submissionInfo.get(S2SConstants.KEY_REVISION_OTHER_DESCRIPTION) != null) {
                    revisionCodeOtherDesc = submissionInfo.get(S2SConstants.KEY_REVISION_OTHER_DESCRIPTION);
                    RevisionCodeOtherExplanation revisionCodeOtherExplanation = RevisionCodeOtherExplanation.Factory.newInstance();
                    revisionCodeOtherExplanation.setStringValue(revisionCodeOtherDesc);
                    applicationType.setRevisionCodeOtherExplanation(revisionCodeOtherExplanation);
                }
            }
        }
        ProposalYnq proposalYnq = getAnswer(PROPOSAL_YNQ_OTHER_AGENCY_SUBMISSION, pdDoc);
        Enum answer = YesNoDataType.NO;
        if (proposalYnq != null && proposalYnq.getAnswer() != null) {
            answer = (proposalYnq.getAnswer().equals(S2SConstants.PROPOSAL_YNQ_ANSWER_Y) ? YesNoDataType.YES : YesNoDataType.NO);
        }

        applicationType.setIsOtherAgencySubmission(answer);
        if (answer.equals(YesNoDataType.YES)) {
            OtherAgencySubmissionExplanation otherAgencySubmissionExplanation = OtherAgencySubmissionExplanation.Factory
                    .newInstance();
            otherAgencySubmissionExplanation.setIsOtherAgencySubmission(answer);
            String answerExplanation = proposalYnq.getExplanation();
            if (answerExplanation != null) {
                if (answerExplanation.length() > ANSWER_EXPLANATION_MAX_LENGTH) {
                    otherAgencySubmissionExplanation.setStringValue(answerExplanation.substring(0, ANSWER_EXPLANATION_MAX_LENGTH));
                }
                else {
                    otherAgencySubmissionExplanation.setStringValue(answerExplanation);
                }
            }
            applicationType.setOtherAgencySubmissionExplanation(otherAgencySubmissionExplanation);
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
        RRSF424.ProposedProjectPeriod proposedProjectPeriod = RRSF424.ProposedProjectPeriod.Factory.newInstance();
        proposedProjectPeriod.setProposedStartDate(s2sUtilService.convertDateToCalendar(pdDoc.getDevelopmentProposal().getRequestedStartDateInitial()));
        proposedProjectPeriod.setProposedEndDate(s2sUtilService.convertDateToCalendar(pdDoc.getDevelopmentProposal().getRequestedEndDateInitial()));
        return proposedProjectPeriod;
    }

    /**
     * 
     * This method is used to get Congressional District for RRSF424
     * 
     * @return CongressionalDistrict congressional district for the Applicant and Project.
     */
    private RRSF424.CongressionalDistrict getCongDistrict() {
        Organization organization = pdDoc.getDevelopmentProposal().getOrganization();
        Organization performOrganization = pdDoc.getDevelopmentProposal().getPerformingOrganization();
        RRSF424.CongressionalDistrict congressionalDistrict = RRSF424.CongressionalDistrict.Factory.newInstance();
        if (organization != null) {
            congressionalDistrict.setApplicantCongressionalDistrict(organization.getCongressionalDistrict());
        }
        else {
            congressionalDistrict.setApplicantCongressionalDistrict("");
        }
        if (performOrganization != null) {
            congressionalDistrict.setProjectCongressionalDistrict(performOrganization.getCongressionalDistrict());
        }
        else {
            congressionalDistrict.setProjectCongressionalDistrict("");
        }
        return congressionalDistrict;
    }

    /**
     * 
     * This method is used to get details of Principal Investigator for Organization Contact
     * 
     * @return OrganizationContactPersonDataType Principal investigator details.
     */
    private OrganizationContactPersonDataType getPDPI() {

        OrganizationContactPersonDataType PDPI = OrganizationContactPersonDataType.Factory.newInstance();
        ProposalPerson PI = null;
        for (ProposalPerson proposalPerson : pdDoc.getDevelopmentProposal().getProposalPersons()) {
            if (PRINCIPAL_INVESTIGATOR.equals(proposalPerson.getProposalPersonRoleId())) {
                PI = proposalPerson;
                Organization organization = pdDoc.getDevelopmentProposal().getOrganization();
                PDPI.setName(globLibV10Generator.getHumanNameDataType(PI));
                PDPI.setPhone(PI.getOfficePhone());
                PDPI.setEmail(PI.getEmailAddress());
                if (PI.getFaxNumber() != null) {
                    PDPI.setFax(PI.getFaxNumber());
                }
                PDPI.setAddress(globLibV10Generator.getAddressRequireCountryDataType(PI));

                if (PI.getDirectoryTitle() != null) {
                    if (PI.getDirectoryTitle().length() > DIRECTORY_TITLE_MAX_LENGTH) {
                        PDPI.setTitle(PI.getDirectoryTitle().substring(0, DIRECTORY_TITLE_MAX_LENGTH));
                    }
                    else {
                        PDPI.setTitle(PI.getDirectoryTitle());
                    }
                }

                String departmentName = null;
                if (pdDoc.getDevelopmentProposal().getOwnedByUnit() != null) {
                    departmentName = pdDoc.getDevelopmentProposal().getOwnedByUnit().getUnitName();
                    if (departmentName != null) {
                        if (departmentName.length() > DEPARTMENT_NAME_MAX_LENGTH) {
                            departmentName = departmentName.substring(0, DEPARTMENT_NAME_MAX_LENGTH - 1);
                            PDPI.setDepartmentName(departmentName.substring(0, DEPARTMENT_NAME_MAX_LENGTH - 1));
                        }
                        else {
                            PDPI.setDepartmentName(departmentName);
                        }
                    }
                }

                // divisionName
                String divisionName = s2sUtilService.getDivisionName(pdDoc);
                if (divisionName != null) {
                    PDPI.setDivisionName(divisionName);
                }
                if (organization != null) {
                    PDPI.setOrganizationName(organization.getOrganizationName());
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
        Organization organization = pdDoc.getDevelopmentProposal().getOrganization();
        AORInfoType aorInfoType = AORInfoType.Factory.newInstance();
        if (departmentalPerson != null) {
            aorInfoType.setName(globLibV10Generator.getHumanNameDataType(departmentalPerson));

            if (departmentalPerson.getPrimaryTitle() != null) {
                if (departmentalPerson.getPrimaryTitle().length() > PRIMARY_TITLE_MAX_LENGTH) {
                    aorInfoType.setTitle(departmentalPerson.getPrimaryTitle().substring(0, PRIMARY_TITLE_MAX_LENGTH));
                }
                else {
                    aorInfoType.setTitle(departmentalPerson.getPrimaryTitle());
                }
            }
            aorInfoType.setAddress(globLibV10Generator.getAddressRequireCountryDataType(departmentalPerson));
            aorInfoType.setPhone(departmentalPerson.getOfficePhone());
            aorInfoType.setFax(departmentalPerson.getFaxNumber());
            aorInfoType.setDepartmentName(departmentalPerson.getDirDept());
            aorInfoType.setEmail(departmentalPerson.getEmailAddress());
            if (departmentalPerson.getHomeUnit() == null) {
                aorInfoType.setDivisionName(S2SConstants.VALUE_UNKNOWN);
            }
            else {
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
        SmallBusinessOrganizationType smallOrganizationType = SmallBusinessOrganizationType.Factory.newInstance();
        IsSociallyEconomicallyDisadvantaged isSociallyEconomicallyDisadvantaged = IsSociallyEconomicallyDisadvantaged.Factory
                .newInstance();
        IsWomenOwned isWomenOwned = IsWomenOwned.Factory.newInstance();
        boolean smallBusflag = false;
        int orgTypeCode = 0;
        if (pdDoc.getDevelopmentProposal().getOrganization() != null && pdDoc.getDevelopmentProposal().getOrganization().getOrganizationTypes() != null
                && pdDoc.getDevelopmentProposal().getOrganization().getOrganizationTypes().size() > 0) {
            orgTypeCode = pdDoc.getDevelopmentProposal().getOrganization().getOrganizationTypes().get(0).getOrganizationTypeCode();
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
                smallOrganizationType.setIsSociallyEconomicallyDisadvantaged(isSociallyEconomicallyDisadvantaged);
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
            applicantType.setSmallBusinessOrganizationType(smallOrganizationType);
        }
        applicantType.setApplicantTypeCode(applicantTypeCode);
        return applicantType;
    }

    /**
     * 
     * This method is used to get the answer for ProposalYnq
     * 
     * @param questionId for which the proposalYnq has to be found.
     * @return proposalYnq corresponding to the questionId.
     */
    private ProposalYnq getAnswer(String questionId, ProposalDevelopmentDocument proposalDevelopmentDocument) {
        String question;
        ProposalYnq ynQ = null;
        for (ProposalYnq proposalYnq : proposalDevelopmentDocument.getDevelopmentProposal().getProposalYnqs()) {
            question = proposalYnq.getQuestionId();

            if (question != null && question.equals(questionId)) {
                ynQ = proposalYnq;
                break;
            }
        }
        return ynQ;
    }

    /**
     * This method creates {@link XmlObject} of type {@link RRSF424Document} by populating data from the given
     * {@link ProposalDevelopmentDocument}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocument}
     * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(ProposalDevelopmentDocument)
     */
    public XmlObject getFormObject(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        departmentalPerson = s2sUtilService.getDepartmentalPerson(proposalDevelopmentDocument);
        return getRRSF424();
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
        RRSF424 rrsf424 = (RRSF424) xmlObject;
        RRSF424Document rrSF424Document = RRSF424Document.Factory.newInstance();
        rrSF424Document.setRRSF424(rrsf424);
        return rrSF424Document;
    }
}