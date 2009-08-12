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

import gov.grants.apply.forms.rrSF424V10.StateReviewCodeTypeDataType;
import gov.grants.apply.forms.rrSF424V10.RRSF424Document.RRSF424.StateReview;
import gov.grants.apply.forms.sf424V10.ApplicantTypeCodeType;
import gov.grants.apply.forms.sf424V10.ApplicationTypeCodeType;
import gov.grants.apply.forms.sf424V10.GrantApplicationDocument;
import gov.grants.apply.forms.sf424V10.GrantApplicationType;
import gov.grants.apply.forms.sf424V10.RevisionCodeType;
import gov.grants.apply.forms.sf424V10.StateReviewCodeType;
import gov.grants.apply.forms.sf424V10.SubmissionTypeCodeType;
import gov.grants.apply.forms.sf424V10.AddressDocument.Address;
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

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.OrganizationYnq;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.distributionincome.BudgetProjectIncome;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.generator.bo.DepartmentalPerson;
import org.kuali.kra.s2s.util.S2SConstants;

/**
 * This Class is used to generate XML object for grants.gov SF424V1.0. This form is generated using XMLBean classes and is based on
 * SF424-V1.0 schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class SF424V1_0Generator extends SF424BaseGenerator {

    private static final Logger LOG = Logger.getLogger(SF424V1_0Generator.class);
    private DepartmentalPerson aorInfo;
    private long stateReviewDate = 0;

    /**
     * 
     * This method returns GrantApplicationDocument object based on proposal development document which contains the
     * GrantApplication information for a particular proposal
     * 
     * @param proposalDevelopmentDocument (ProposalDevelopmentDocument)
     * @return grantApplicationDocument {@link XmlObject} of type GrantApplicationDocument.
     */
    private GrantApplicationDocument getGrantApplication() {

        GrantApplicationDocument grantApplicationDocument = GrantApplicationDocument.Factory.newInstance();
        grantApplicationDocument.setGrantApplication(getGrantApplicationType());
        return grantApplicationDocument;
    }

    /**
     * 
     * This method gets GrantApplicationType for the form . GrantApplicationType includes information regarding SubmissionTypeCode
     * ApplicationTypeCode,Revision,AgencyName,StateID,CFDANumber,SubmittingOrganization,AuthorizedRepresentative.
     * 
     * @param proposalDevelopmentDocument (ProposalDevelopmentDocument)
     * @return GrantApplicationType application details.
     */
    private GrantApplicationType getGrantApplicationType() {

        GrantApplicationType grantApplicationType = GrantApplicationType.Factory.newInstance();
        grantApplicationType.setFormVersionIdentifier(S2SConstants.FORMVERSION_1_0);
        // Set default values for mandatory fields
        grantApplicationType.setAgencyName("");


        String submissionTypeCode = getSF424SubmissionType(pdDoc);
        if (submissionTypeCode != null) {
            SubmissionTypeCodeType.Enum submissionType = SubmissionTypeCodeType.Enum.forString(submissionTypeCode);
            grantApplicationType.setSubmissionTypeCode(submissionType);
        }
        grantApplicationType.setSubmittedDate(s2sUtilService.getCurrentCalendar());
        ApplicationTypeCodeType.Enum applicationTypeCodeDataType = null;
        if (pdDoc.getDevelopmentProposal().getProposalTypeCode() != null) {
            int proposalTypeCode = Integer.parseInt(pdDoc.getDevelopmentProposal().getProposalTypeCode());
            if (proposalTypeCode < PROPOSAL_TYPE_RESUBMISSION) {
                applicationTypeCodeDataType = ApplicationTypeCodeType.Enum.forInt(proposalTypeCode);
            }
        }
        grantApplicationType.setApplicationTypeCode(applicationTypeCodeDataType);
        if (pdDoc.getDevelopmentProposal().getS2sOpportunity() != null && pdDoc.getDevelopmentProposal().getS2sOpportunity().getS2sSubmissionTypeCode() != null) {
            pdDoc.getDevelopmentProposal().getS2sOpportunity().refreshNonUpdateableReferences();

            String revisionCode = pdDoc.getDevelopmentProposal().getS2sOpportunity().getRevisionCode();
            if (revisionCode != null) {
                Revision revision = Revision.Factory.newInstance();
                String revision1 = null;
                String revision2 = null;
                revision1 = revisionCode.substring(0, 1);
                if (revisionCode.length() > 1) {
                    revision2 = revisionCode.substring(1, 2);
                }
                if (revision1 != null && !revision1.equals(OTHER_SPECIFY_CODE)) {
                    RevisionCodeType.Enum rev1Enum = RevisionCodeType.Enum.forString(revision1);
                    revision.setRevisionCode1(rev1Enum);
                }
                if (revision2 != null) {
                    RevisionCodeType.Enum rev2Enum = RevisionCodeType.Enum.forString(revision2);
                    revision.setRevisionCode2(rev2Enum);
                }
                grantApplicationType.setRevision(revision);
            }
        }
        if (pdDoc.getDevelopmentProposal().getSponsor() != null) {
            grantApplicationType.setAgencyName(pdDoc.getDevelopmentProposal().getSponsor().getSponsorName());
        }
        Rolodex rolodex = pdDoc.getDevelopmentProposal().getApplicantOrganization().getRolodex();
        if (rolodex != null) {
            grantApplicationType.setStateID(pdDoc.getDevelopmentProposal().getApplicantOrganization().getRolodex().getState());
        }
        grantApplicationType.setFederalID(s2sUtilService.getFederalId(pdDoc));
        grantApplicationType.setCFDANumber(pdDoc.getDevelopmentProposal().getCfdaNumber());
        if (pdDoc.getDevelopmentProposal().getProgramAnnouncementTitle() != null) {
            String announcementTitle;
            if (pdDoc.getDevelopmentProposal().getProgramAnnouncementTitle().length() > PROGRAM_ANNOUNCEMENT_TITLE_LENGTH) {
                // announcementTitle contains sub string value of ProgramAnnouncementTitle
                announcementTitle = pdDoc.getDevelopmentProposal().getProgramAnnouncementTitle().substring(0, PROGRAM_ANNOUNCEMENT_TITLE_LENGTH);
            }
            else {
                announcementTitle = pdDoc.getDevelopmentProposal().getProgramAnnouncementTitle();
            }
            grantApplicationType.setActivityTitle(announcementTitle);
        }
        grantApplicationType.setSubmittingOrganization(getSubmittingOrganization());
        grantApplicationType.setProject(getProject());
        try {
            grantApplicationType.setBudget(getBudget());
        }
        catch (S2SException e) {
            LOG.error(e.getMessage(), e);
            return grantApplicationType;
        }
        Individual individual = Individual.Factory.newInstance();
        individual.setAuthorizedRepresentative(getAuthorizedRepresentative());
        individual.setContact(getContact());
        grantApplicationType.setIndividual(individual);
        grantApplicationType.setStateReviewCode(getStateReviewCode());
        if (stateReviewDate != 0) {
            Calendar stateDate = dateTimeService.getCurrentCalendar();
            stateDate.setTimeInMillis(stateReviewDate);
            grantApplicationType.setStateReviewDate(stateDate);
        }
        grantApplicationType.setAuthorizedRepresentativeSignature(aorInfo.getFullName());

        grantApplicationType.setSignedDate(s2sUtilService.getCurrentCalendar());
        grantApplicationType.setCoreSchemaVersion(CORE_SCHEMA_VERSION_1_0);
        return grantApplicationType;
    }

    /**
     * 
     * This method returns StateReviewCode status for the application.StateReviewCode can be Not covered,Not reviewed
     * 
     * @param proposalDevelopmentDocument (ProposalDevelopmentDocument)
     * @return stateType (StateReviewCodeType.Enum) revision details.
     */
    private StateReviewCodeType.Enum getStateReviewCode() {

        Map<String, String> eoStateReview = s2sUtilService.getEOStateReview(pdDoc);
        StateReviewCodeType.Enum stateType = null;
        StateReviewCodeTypeDataType.Enum stateReviewCodeType = null;
        String strReview = eoStateReview.get(S2SConstants.YNQ_ANSWER);

        if (STATE_REVIEW_YES.equals(strReview)) {
            stateType = StateReviewCodeType.YES;
        }
        else if (STATE_REVIEW_NO.equals(strReview)) {
            stateType = StateReviewCodeType.NOT_REVIEWED;
        }
        else {
            stateType = StateReviewCodeType.NOT_COVERED;
        }
        if (eoStateReview.get(S2SConstants.YNQ_REVIEW_DATE) != null) {
            stateReviewDate = Long.parseLong(eoStateReview.get(S2SConstants.YNQ_REVIEW_DATE));
        }
        StateReview stateReview = StateReview.Factory.newInstance();
        stateReview.setStateReviewCodeType(stateReviewCodeType);
        return stateType;
    }

    /**
     * 
     * This method returns the AuthorizedRepresentative details such as FirstName,MiddleName,LastName,EmailAddress
     * TelephoneNumber,FaxNumber and RepresentativeTitle
     * 
     * @param proposalDevelopmentDocument (ProposalDevelopmentDocument)
     * @return AuthorizedRepresentative authorized representative details.
     */
    private AuthorizedRepresentative getAuthorizedRepresentative() {

        AuthorizedRepresentative authorizedRep = AuthorizedRepresentative.Factory.newInstance();
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
                authorizedRep.setRepresentativeTitle(aorInfo.getPrimaryTitle().substring(0, PRIMARY_TITLE_MAX_LENGTH));
            }
            else {
                authorizedRep.setRepresentativeTitle(aorInfo.getPrimaryTitle());
            }
        }
        return authorizedRep;
    }

    /**
     * 
     * This method get budget informations.Budget informations includes FederalEstimatedAmount,LocalEstimatedAmount
     * ProgramIncomeEstimatedAmount,OtherEstimatedAmount and TotalEstimatedAmount
     * 
     * @param proposalDevelopmentDocument (ProposalDevelopmentDocument)
     * @return Budget total estimated budget details.
     * @throws S2SException
     */
    private Budget getBudget() throws S2SException {

        Budget budget = Budget.Factory.newInstance();
        CurrencyCodeType.Enum currencyEnum = CurrencyCodeType.USD;
        budget.setCurrencyCode(currencyEnum);
        budget.setFederalEstimatedAmount(BigDecimal.ZERO);
        budget.setTotalEstimatedAmount(BigDecimal.ZERO);

        org.kuali.kra.budget.core.Budget budgetDoc = s2sBudgetCalculatorService.getFinalBudgetVersion(pdDoc).getBudget();
        if (budgetDoc != null) {
            budget.setFederalEstimatedAmount(budgetDoc.getTotalCost().bigDecimalValue());
            budget.setApplicantEstimatedAmount(budgetDoc.getCostSharingAmount().bigDecimalValue());
            // Following values hardcoded as in coeus
            budget.setStateEstimatedAmount(BigDecimal.ZERO);
            budget.setLocalEstimatedAmount(BigDecimal.ZERO);
            budget.setOtherEstimatedAmount(BigDecimal.ZERO);
            BigDecimal projectIncome = BigDecimal.ZERO;
            for (BudgetProjectIncome budgetProjectIncome : budgetDoc.getBudgetProjectIncomes()) {
                if (budgetProjectIncome.getProjectIncome() != null) {
                    projectIncome = projectIncome.add(budgetProjectIncome.getProjectIncome().bigDecimalValue());
                }
            }
            budget.setProgramIncomeEstimatedAmount(projectIncome);
            BudgetDecimal totalEstimatedAmount = BudgetDecimal.ZERO;
            if (budgetDoc.getTotalCost() != null) {
                totalEstimatedAmount = totalEstimatedAmount.add(budgetDoc.getTotalCost());
            }
            if (budgetDoc.getCostSharingAmount() != null) {
                totalEstimatedAmount = totalEstimatedAmount.add(budgetDoc.getCostSharingAmount());
            }
            budget.setTotalEstimatedAmount(totalEstimatedAmount.bigDecimalValue().add(projectIncome));
        }
        return budget;
    }

    /**
     * 
     * This method gets AuthorizedRepresentative (principal investigator) contact informations which includes FirstName
     * MiddleName,LastName,EmailAddress,TelephoneNumber and FaxNumber
     * 
     * @param proposalDevelopmentDocument (ProposalDevelopmentDocument)
     * @return Contact principal investigator contact details.
     */
    private Contact getContact() {

        Contact contact = Contact.Factory.newInstance();
        ProposalPerson PI = s2sUtilService.getPrincipalInvestigator(pdDoc);
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
     * This method gets all the informations related to the project. Project informations are ProjectTitle,Location,
     * ProposedStartDate,ProposedEndDate and CongressionalDistrict.
     * 
     * @param proposalDevelopmentDocument (ProposalDevelopmentDocument)
     * @return project (Project)
     */
    private Project getProject() {

        Project project = Project.Factory.newInstance();
        project.setProjectTitle(pdDoc.getDevelopmentProposal().getTitle());
        Rolodex rolodex = pdDoc.getDevelopmentProposal().getApplicantOrganization().getRolodex();
        if (rolodex != null) {
            project.setLocation(pdDoc.getDevelopmentProposal().getApplicantOrganization().getRolodex().getState());
        }
        project.setProposedStartDate(s2sUtilService.convertDateToCalendar(pdDoc.getDevelopmentProposal().getRequestedStartDateInitial()));
        project.setProposedEndDate(s2sUtilService.convertDateToCalendar(pdDoc.getDevelopmentProposal().getRequestedEndDateInitial()));

        if (pdDoc.getDevelopmentProposal().getPerformingOrganization() != null) {
            project.setCongressionalDistrict(pdDoc.getDevelopmentProposal().getPerformingOrganization().getFirstCongressionalDistrictName());
        }
        else {
            project.setCongressionalDistrict("");
        }
        return project;
    }

    /**
     * 
     * Gets the information about the Submitting Organization.This includes details like CongressionalDistrict
     * DelinquentFederalDebtIndicator,OrganizationName,DUNSID,DepartmentName,DivisionName,ApplicantID,ApplicantTypeCode Organization
     * details,OrganizationIdentifyingInformation and Address
     * 
     * @param proposalDevelopmentDocument (ProposalDevelopmentDocument)
     * @return submittingOrganization(SubmittingOrganization) organization details.
     */
    private SubmittingOrganization getSubmittingOrganization() {
        SubmittingOrganization submittingOrganization = SubmittingOrganization.Factory.newInstance();
        if (pdDoc.getDevelopmentProposal().getApplicantOrganization() != null) {
            submittingOrganization.setCongressionalDistrict(pdDoc.getDevelopmentProposal().getApplicantOrganization().getFirstCongressionalDistrictName());
        }
        YesNoType.Enum yesNo = YesNoType.N;
        for (OrganizationYnq orgYnq : pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization().getOrganizationYnqs()) {
            if (PROPOSAL_YNQ_FEDERAL_DEBTS.equals(orgYnq.getQuestionId())) {
                if (orgYnq.getAnswer() != null) {
                    yesNo = YesNoType.Enum.forString(orgYnq.getAnswer());
                }
            }
        }
        submittingOrganization.setDelinquentFederalDebtIndicator(yesNo);
        Organization organization = Organization.Factory.newInstance();
        if (pdDoc.getDevelopmentProposal().getApplicantOrganization() != null) {
            organization.setOrganizationName(pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization().getOrganizationName());
        }
        if (pdDoc.getDevelopmentProposal().getApplicantOrganization() != null) {
            organization.setDUNSID(pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization().getDunsNumber());
        }
        String departmentName = "";
        if (pdDoc.getDevelopmentProposal().getOwnedByUnit() != null) {
            departmentName = pdDoc.getDevelopmentProposal().getOwnedByUnit().getUnitName();
        }
        if (departmentName != null && departmentName.length() > DEPARTMENT_NAME_MAX_LENGTH) {
            organization.setDepartmentName(departmentName.substring(0, DEPARTMENT_NAME_MAX_LENGTH));
        }
        else {
            organization.setDepartmentName(departmentName);
        }
        String divisionName = s2sUtilService.getDivisionName(pdDoc);
        if (divisionName != null) {
            organization.setDivisionName(divisionName);
        }
        if (pdDoc.getDevelopmentProposal().getApplicantOrganization() != null) {
            organization.setEmployerID(pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization().getFedralEmployerId());
        }
        OrganizationIdentifyingInformation orgIdentifyingInformation = OrganizationIdentifyingInformation.Factory.newInstance();
        orgIdentifyingInformation.setApplicantID(pdDoc.getDevelopmentProposal().getProposalNumber());
        orgIdentifyingInformation.setApplicantTypeCode(getApplicantType());
        orgIdentifyingInformation.setOrganization(organization);
        submittingOrganization.setOrganizationIdentifyingInformation(orgIdentifyingInformation);
        Address address = Address.Factory.newInstance();
        // Set default values for mandatory fields
        address.setStreet1("");
        address.setCity("");
        Rolodex rolodex = null;
        if (pdDoc.getDevelopmentProposal().getPerformingOrganization() != null) {
            rolodex = pdDoc.getDevelopmentProposal().getPerformingOrganization().getRolodex();
            address.setStreet1(rolodex.getAddressLine1());
            address.setStreet2(rolodex.getAddressLine2());
            address.setCity(rolodex.getCity());
            address.setCounty(rolodex.getCounty());
            address.setStateCode(rolodex.getState());
            address.setZipCode(rolodex.getPostalCode());

            if (rolodex.getCountryCode() != null) {
                CountryCodeType.Enum countryEnum = CountryCodeType.Enum.forString(rolodex.getCountryCode());
                address.setCountry(countryEnum);
            }
        }
        submittingOrganization.setAddress(address);
        return submittingOrganization;
    }

    /**
     * 
     * Gets the Applicant type code information for the particular applicant.It returns enumeration value for the code such as State
     * Government,Non-profit Organization,Native American Tribal Government etc.
     * 
     * @param proposalDevelopmentDocument (ProposalDevelopmentDocument)
     * @return applicantTypeCode(ApplicantTypeCodeType.Enum) corresponding to the organization type code.
     */
    private ApplicantTypeCodeType.Enum getApplicantType() {

        ApplicantTypeCodeType.Enum applicantTypeCode = null;
        int orgTypeCode = 0;
        if (pdDoc.getDevelopmentProposal().getApplicantOrganization() != null && pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization().getOrganizationTypes() != null
                && pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization().getOrganizationTypes().size() > 0) {
            orgTypeCode = pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization().getOrganizationType(0).getOrganizationTypeCode().intValue();
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
     * This method returns Submission type details for the Submission type.It returns enumeration value for the subission type.
     * Submission type can be Construction,Non construction, Application, Pre application.
     * 
     * @param pdDoc (ProposalDevelopmentDocument)
     * @return submissionType(String) corresponding to submission type code.
     */
    private String getSF424SubmissionType(ProposalDevelopmentDocument pdDoc) {

        String submissionType = null;
        String suffix;

        if (ACTIVITY_TYPE_CODE_CONSTRUCTION.equals(pdDoc.getDevelopmentProposal().getActivityTypeCode())) {
            suffix = ACTIVITY_TYPE_CODE_LS_SUFFIX_CONSTRUCTION;
        }
        else {
            suffix = ACTIVITY_TYPE_CODE_LS_SUFFIX_NONCONSTRUCTION;
        }
        if (pdDoc.getDevelopmentProposal().getS2sOpportunity() != null && pdDoc.getDevelopmentProposal().getS2sOpportunity().getS2sSubmissionTypeCode() != null) {
            pdDoc.getDevelopmentProposal().getS2sOpportunity().refreshNonUpdateableReferences();
            if (S2S_SUBMISSION_TYPE_CODE_NOTSELECTED.equals(pdDoc.getDevelopmentProposal().getS2sOpportunity().getS2sSubmissionType()
                    .getS2sSubmissionTypeCode())) {
                submissionType = ACTIVITY_TYPE_CODE_LS_SUFFIX_PREAPPLICATION + suffix;
            }
            else {
                submissionType = ACTIVITY_TYPE_CODE_LS_SUFFIX_APPLICATION + suffix;
            }
        }
        return submissionType;
    }

    /**
     * This method creates {@link XmlObject} of type {@link GrantApplicationDocument} by populating data from the given
     * {@link ProposalDevelopmentDocument}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocument}
     * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(ProposalDevelopmentDocument)
     */
    public XmlObject getFormObject(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        aorInfo = s2sUtilService.getDepartmentalPerson(pdDoc);
        return getGrantApplication();
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
        GrantApplicationType grantApplicationType = (GrantApplicationType) xmlObject;
        GrantApplicationDocument grantDocument = GrantApplicationDocument.Factory.newInstance();
        grantDocument.setGrantApplication(grantApplicationType);
        return grantDocument;
    }
}
