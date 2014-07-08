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
package org.kuali.kra.s2s.service.impl;


import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.api.country.CountryContract;
import org.kuali.coeus.common.api.country.KcCountryService;
import org.kuali.coeus.common.api.person.KcPersonContract;
import org.kuali.coeus.common.api.person.KcPersonRepositoryService;
import org.kuali.coeus.common.api.org.OrganizationContract;
import org.kuali.coeus.common.api.question.AnswerContract;
import org.kuali.coeus.common.api.question.AnswerHeaderContract;
import org.kuali.coeus.common.api.question.QuestionAnswerService;
import org.kuali.coeus.common.api.rolodex.RolodexService;
import org.kuali.coeus.common.api.state.KcStateService;
import org.kuali.coeus.common.api.state.StateContract;
import org.kuali.coeus.common.api.unit.UnitContract;
import org.kuali.coeus.common.api.unit.UnitRepositoryService;
import org.kuali.coeus.common.api.unit.admin.UnitAdministratorContract;
import org.kuali.coeus.common.budget.api.personnel.BudgetPersonnelDetailsContract;
import org.kuali.coeus.common.api.rolodex.RolodexContract;
import org.kuali.coeus.instprop.api.admin.ProposalAdminDetailsContract;
import org.kuali.coeus.instprop.api.admin.ProposalAdminDetailsService;
import org.kuali.coeus.propdev.api.PropDevQuestionAnswerService;
import org.kuali.coeus.propdev.api.person.ProposalPersonContract;
import org.kuali.coeus.propdev.api.s2s.S2SConfigurationService;
import org.kuali.coeus.propdev.api.s2s.S2sOpportunityContract;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.s2s.CitizenshipTypes;
import org.kuali.kra.s2s.ConfigurationConstants;
import org.kuali.kra.s2s.generator.bo.DepartmentalPerson;
import org.kuali.kra.s2s.generator.bo.KeyPersonInfo;
import org.kuali.kra.s2s.service.CitizenshipTypeService;
import org.kuali.kra.s2s.service.S2SUtilService;
import org.kuali.kra.s2s.util.S2SConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.*;

/**
 * 
 * 
 * This class is the implementation for all reusable components that are part of S2S
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@Component("s2SUtilService")
public class S2SUtilServiceImpl implements S2SUtilService {

    private static final String SUBMISSION_TYPE_CODE = "submissionTypeCode";
    private static final String SUBMISSION_TYPE_DESCRIPTION = "submissionTypeDescription";
    private static final String YNQ_NOT_REVIEWED = "X";
    private static final int DIVISION_NAME_MAX_LENGTH = 30;
    private static final String CONTACT_TYPE_O = "O";

    public static final Long PROPOSAL_YNQ_QUESTION_129 = 129L;
    public static final Long PROPOSAL_YNQ_QUESTION_130 = 130L;
    public static final Long PROPOSAL_YNQ_QUESTION_131 = 131L;

    @Autowired
    @Qualifier("s2SConfigurationService")
    private S2SConfigurationService s2SConfigurationService;

    @Autowired
    @Qualifier("citizenshipTypeService")
    private CitizenshipTypeService citizenshipTypeService;

    @Autowired
    @Qualifier("proposalAdminDetailsService")
    private ProposalAdminDetailsService proposalAdminDetailsService;

    @Autowired
    @Qualifier("kcPersonRepositoryService")
    private KcPersonRepositoryService kcPersonRepositoryService;

    @Autowired
    @Qualifier("unitRepositoryService")
    private UnitRepositoryService unitRepositoryService;

    @Autowired
    @Qualifier("kcCountryService")
    private KcCountryService kcCountryService;

    @Autowired
    @Qualifier("kcStateService")
    private KcStateService kcStateService;

    @Autowired
    @Qualifier("questionAnswerService")
    private QuestionAnswerService questionAnswerService;

    @Autowired
    @Qualifier("propDevQuestionAnswerService")
    private PropDevQuestionAnswerService propDevQuestionAnswerService;

    @Autowired
    @Qualifier("rolodexService")
    private RolodexService rolodexService;
    /**
     * This method creates and returns Map of submission details like submission type, description and Revision code
     *
     * @param pdDoc Proposal Development Document.
     * @return Map<String, String> Map of submission details.
     * @see org.kuali.kra.s2s.service.S2SUtilService#getSubmissionType(org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract)
     */
    public Map<String, String> getSubmissionType(ProposalDevelopmentDocumentContract pdDoc) {
        Map<String, String> submissionInfo = new HashMap<String, String>();
        S2sOpportunityContract opportunity = pdDoc.getDevelopmentProposal().getS2sOpportunity();
        if (opportunity != null) {
            String submissionTypeCode = opportunity.getS2sSubmissionType().getCode();
            String submissionTypeDescription = "";
            if (opportunity.getS2sSubmissionType() != null) {
                submissionTypeDescription = opportunity.getS2sSubmissionType().getDescription();
            }
            String revisionCode = opportunity.getS2sRevisionType().getCode();
            String revisionOtherDescription = opportunity.getRevisionOtherDescription();

            submissionInfo.put(SUBMISSION_TYPE_CODE, submissionTypeCode);
            submissionInfo.put(SUBMISSION_TYPE_DESCRIPTION, submissionTypeDescription);
            submissionInfo.put(S2SConstants.KEY_REVISION_CODE, revisionCode);
            if (revisionOtherDescription != null) {
                submissionInfo.put(S2SConstants.KEY_REVISION_OTHER_DESCRIPTION, revisionOtherDescription);
            }
        }
        return submissionInfo;
    }

    /**
     * This method populates and returns the Departmental Person object for a given proposal document
     * 
     * @param pdDoc Proposal Development Document.
     * @return DepartmentalPerson departmental Person object for a given proposal document.
     * @see org.kuali.kra.s2s.service.S2SUtilService#getDepartmentalPerson(org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract)
     */
    public DepartmentalPerson getDepartmentalPerson(ProposalDevelopmentDocumentContract pdDoc) {
        int count = 0;
        DepartmentalPerson depPerson = new DepartmentalPerson();
        List<? extends ProposalAdminDetailsContract> proposalAdminDetailsList = proposalAdminDetailsService.findProposalAdminDetailsByPropDevNumber(pdDoc.getDevelopmentProposal().getProposalNumber());
        count = proposalAdminDetailsList.size();
        if (count < 1) {
            // Proposal has not been submitted

            OrganizationContract organization = pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization();
            RolodexContract rolodex = organization == null ? null : rolodexService.getRolodex(organization.getContactAddressId());
            if (rolodex != null) {
                depPerson.setFirstName(rolodex.getFirstName());
                depPerson.setMiddleName(rolodex.getMiddleName());
                depPerson.setLastName(rolodex.getLastName());
                StringBuilder fullName = new StringBuilder();
                if (rolodex.getFirstName() != null) {
                    fullName.append(rolodex.getFirstName());
                    fullName.append(" ");
                }
                if (rolodex.getMiddleName() != null) {
                    fullName.append(rolodex.getMiddleName());
                    fullName.append(" ");
                }
                if (rolodex.getLastName() != null) {
                    fullName.append(rolodex.getLastName());
                }
                depPerson.setFullName(fullName.toString());

                depPerson.setEmailAddress(rolodex.getEmailAddress());
                depPerson.setOfficePhone(rolodex.getPhoneNumber());
                depPerson.setPrimaryTitle(rolodex.getTitle());
                depPerson.setAddress1(rolodex.getAddressLine1());
                depPerson.setAddress2(rolodex.getAddressLine2());
                depPerson.setAddress3(rolodex.getAddressLine3());
                depPerson.setCity(rolodex.getCity());
                depPerson.setCounty(rolodex.getCounty());
                depPerson.setCountryCode(rolodex.getCountryCode());
                depPerson.setFaxNumber(rolodex.getFaxNumber());
                depPerson.setPostalCode(rolodex.getPostalCode());
                depPerson.setState(rolodex.getState());
                depPerson.setPersonId(Integer.toString(rolodex.getRolodexId()));
                depPerson.setDirDept(organization.getOrganizationName());
            }
        }
        else {
            ProposalAdminDetailsContract proposalAdminDetails = proposalAdminDetailsList.get(0);
            KcPersonContract person = this.kcPersonRepositoryService.findKcPersonByUserName(proposalAdminDetails.getSignedBy());

            if (person != null) {
                depPerson.setFirstName(person.getFirstName());
                depPerson.setMiddleName(person.getMiddleName());
                depPerson.setLastName(person.getLastName());
                depPerson.setFullName(person.getFullName());
                depPerson.setEmailAddress(person.getEmailAddress());
                depPerson.setOfficePhone(person.getPhoneNumber());
                depPerson.setPrimaryTitle(person.getPrimaryTitle());
                depPerson.setAddress1(person.getAddressLine1());
                depPerson.setAddress2(person.getAddressLine2());
                depPerson.setAddress3(person.getAddressLine3());
                depPerson.setCity(person.getCity());
                depPerson.setCounty(person.getCounty());
                depPerson.setCountryCode(person.getCountryCode());
                depPerson.setFaxNumber(person.getFaxNumber());
                depPerson.setPostalCode(person.getPostalCode());
                depPerson.setState(person.getState());
                depPerson.setPersonId(person.getPersonId());
                depPerson.setDirDept(person.getContactOrganizationName());
            }
        }
        return depPerson;
    }

    /**
     * This method limits the number of key persons to n, returns list of key persons, first n in case firstN is true, or all other
     * than first n, in case of firstN being false
     * 
     * @param proposalPersons list of {@link org.kuali.coeus.propdev.api.person.ProposalPersonContract}
     * @param firstN value that determines whether the returned list should contain first n persons or the rest of persons
     * @param n number of key persons that are considered as not extra persons
     * @return list of {@link org.kuali.coeus.propdev.api.person.ProposalPersonContract}
     * @see org.kuali.kra.s2s.service.S2SUtilService#getNKeyPersons(java.util.List, boolean, int)
     */
    public List<ProposalPersonContract> getNKeyPersons(List<? extends ProposalPersonContract> proposalPersons, boolean firstN, int n) {
        ProposalPersonContract proposalPerson, previousProposalPerson;
        int size = proposalPersons.size();

        for (int i = size - 1; i > 0; i--) {
            proposalPerson = proposalPersons.get(i);
            previousProposalPerson = proposalPersons.get(i - 1);
            if (proposalPerson.getPersonId() != null && previousProposalPerson.getPersonId() != null
                    && proposalPerson.getPersonId().equals(previousProposalPerson.getPersonId())) {
                proposalPersons.remove(i);
            }
            else if (proposalPerson.getRolodexId() != null && previousProposalPerson.getRolodexId() != null
                    && proposalPerson.getRolodexId().equals(previousProposalPerson.getRolodexId())) {
                proposalPersons.remove(i);
            }
        }

        size = proposalPersons.size();
        if (firstN) {
            List<ProposalPersonContract> firstNPersons = new ArrayList<ProposalPersonContract>();

            // Make sure we don't exceed the size of the list.
            if (size > n) {
                size = n;
            }
            // remove extras
            for (int i = 0; i < size; i++) {
                firstNPersons.add(proposalPersons.get(i));
            }
            return firstNPersons;
        }
        else {
            // return extra people
            List<ProposalPersonContract> extraPersons = new ArrayList<ProposalPersonContract>();
            for (int i = n; i < size; i++) {
                extraPersons.add(proposalPersons.get(i));
            }
            return extraPersons;
        }
    }

    /**
     * This method returns a map containing the answers related to EOState REview for a given proposal
     * 
     * @param pdDoc Proposal Development Document.
     * @return Map<String, String> map containing the answers related to EOState Review for a given proposal.
     * @see org.kuali.kra.s2s.service.S2SUtilService#getEOStateReview(org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract)
     */
    public Map<String, String> getEOStateReview(ProposalDevelopmentDocumentContract pdDoc) {
        Map<String, String> stateReview = new HashMap<String, String>();
        List<? extends AnswerHeaderContract> answerHeaders = propDevQuestionAnswerService.getQuestionnaireAnswerHeaders(pdDoc.getDevelopmentProposal().getProposalNumber());
        for (AnswerContract answers : answerHeaders.get(0).getAnswers()) {
            if (answers.getQuestionId() != null
                    && answers.getQuestionId().equals(PROPOSAL_YNQ_QUESTION_129)) {
                if (stateReview.get(S2SConstants.YNQ_ANSWER) == null) {
                    stateReview.put(S2SConstants.YNQ_ANSWER, answers.getAnswer());
                }
            }
            if (answers.getQuestionId() != null
                    && answers.getQuestionId().equals(PROPOSAL_YNQ_QUESTION_130)) {
                if (stateReview.get(S2SConstants.YNQ_REVIEW_DATE) == null) {
                    stateReview.put(S2SConstants.YNQ_REVIEW_DATE, answers.getAnswer());
                }
            }
            if (answers.getQuestionId() != null
                    && answers.getQuestionId().equals(PROPOSAL_YNQ_QUESTION_131)) {
                if (stateReview.get(S2SConstants.YNQ_STATE_REVIEW_DATA) == null) {
                    stateReview.put(S2SConstants.YNQ_STATE_REVIEW_DATA, answers.getAnswer());
                }
            }
        }
        // If question is not answered or question is inactive
        if (stateReview.size() == 0) {
            stateReview.put(S2SConstants.YNQ_ANSWER, YNQ_NOT_REVIEWED);
            stateReview.put(S2SConstants.YNQ_REVIEW_DATE, null);
        }
        return stateReview;
    }

    /**
     * This method returns a {@link Calendar} whose date matches the date passed as {@link String}
     * 
     * @param dateStr string in "MM/dd/yyyy" format for which the Calendar value has to be returned.
     * @return Calendar calendar value corresponding to the date string.
     * @see org.kuali.kra.s2s.service.S2SUtilService#convertDateStringToCalendar(java.lang.String)
     */
    public Calendar convertDateStringToCalendar(String dateStr) {
        Calendar calendar = null;
        if (dateStr != null) {
            calendar = Calendar.getInstance();
            calendar.set(Integer.parseInt(dateStr.substring(6, 10)), Integer.parseInt(dateStr.substring(0, 2)) - 1,
                    Integer.parseInt(dateStr.substring(3, 5)));
        }
        return calendar;
    }

    /**
     * This method is used to get Calendar date for the corresponding date object.
     *
     * @param date(Date) date for which Calendar value has to be found.
     * @return calendar value corresponding to the date.
     * @see org.kuali.kra.s2s.service.S2SUtilService#convertDateToCalendar(java.util.Date)
     */
    public Calendar convertDateToCalendar(java.util.Date date) {
        Calendar calendar = null;
        if (date != null) {
            calendar = Calendar.getInstance();
            calendar.setTime(date);
        }
        return calendar;
    }

    /**
     * This method is to get division name using the OwnedByUnit and traversing through the parent units till the top level
     * 
     * @param pdDoc Proposal development document.
     * @return divisionName based on the OwnedByUnit.
     * @see org.kuali.kra.s2s.service.S2SUtilService#getDivisionName(org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract)
     */
    public String getDivisionName(ProposalDevelopmentDocumentContract pdDoc) {
        String divisionName = null;
        if (pdDoc != null && pdDoc.getDevelopmentProposal().getOwnedByUnit() != null) {
            UnitContract ownedByUnit = pdDoc.getDevelopmentProposal().getOwnedByUnit();
            // traverse through the parent units till the top level unit
            while (ownedByUnit.getParentUnit() != null) {
                ownedByUnit = ownedByUnit.getParentUnit();
            }
            divisionName = ownedByUnit.getUnitName();
            if (divisionName.length() > DIVISION_NAME_MAX_LENGTH) {
                divisionName = divisionName.substring(0, DIVISION_NAME_MAX_LENGTH);
            }
        }
        return divisionName;
    }
            
    /**
     * This method is to get PrincipalInvestigator from person list
     * 
     * @param pdDoc Proposal development document.
     * @return ProposalPerson PrincipalInvestigator for the proposal.
     * @see org.kuali.kra.s2s.service.S2SUtilService#getPrincipalInvestigator(org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract)
     */
    public ProposalPersonContract getPrincipalInvestigator(ProposalDevelopmentDocumentContract pdDoc) {
        ProposalPersonContract proposalPerson = null;
        if (pdDoc != null) {
            for (ProposalPersonContract person : pdDoc.getDevelopmentProposal().getProposalPersons()) {
                if (ContactRole.PI_CODE.equals(person.getProposalPersonRoleId())) {
                    proposalPerson = person;
                }
            }
        }
        return proposalPerson;
    }

    /**
     * Finds all the Investigators associated with the provided pdDoc.
     */
    public List<ProposalPersonContract> getCoInvestigators(ProposalDevelopmentDocumentContract pdDoc) {
        List<ProposalPersonContract> investigators = new ArrayList<ProposalPersonContract>();
        if (pdDoc != null) {
            for (ProposalPersonContract person : pdDoc.getDevelopmentProposal().getProposalPersons()) {
            	//multi-pis are still considered co-i within S2S.
                if (person.isCoInvestigator() || person.isMultiplePi()) {
                    investigators.add(person);
                }
            }
        }
        return investigators;
    }

    /**
     * Finds all the key Person associated with the provided pdDoc.
     */
    public List<ProposalPersonContract> getKeyPersons(ProposalDevelopmentDocumentContract pdDoc) {
        List<ProposalPersonContract> keyPersons = new ArrayList<ProposalPersonContract>();
        if (pdDoc != null) {
            for (ProposalPersonContract person : pdDoc.getDevelopmentProposal().getProposalPersons()) {
                if (ContactRole.KEY_PERSON_CODE.equals(person.getProposalPersonRoleId())) {
                    keyPersons.add(person);
                }
            }
        }
        return keyPersons;
    }

    /**
     * This method is to get a Country object from the country code
     * 
     * @param countryCode country code for the country.
     * @return Country object matching the code
     * @see org.kuali.kra.s2s.service.S2SUtilService#getCountryFromCode(java.lang.String)
     */
    public CountryContract getCountryFromCode(String countryCode) {
        if(countryCode==null) return null;
        CountryContract country = getKcCountryService().getCountryByAlternateCode(countryCode);
        if(country==null){
            country = getKcCountryService().getCountry(countryCode);
        }
        return country;
    }


    /**
     * This method is to get a State object from the state name
     * 
     * @param stateName Name of the state
     * @return State object matching the name.
     * @see org.kuali.kra.s2s.service.S2SUtilService#getStateFromName(java.lang.String, java.lang.String)
     */
    public StateContract getStateFromName(String countryAlternateCode, String stateName) {
        CountryContract country = getCountryFromCode(countryAlternateCode);

        StateContract state = getKcStateService().getState(country.getCode(), stateName);
        return state;
    }

    /**
     * This method compares a key person with budget person. It checks whether the key person is from PERSON or ROLODEX and matches
     * the respective person ID with the person in {@link org.kuali.coeus.common.budget.api.personnel.BudgetPersonnelDetailsContract}
     * 
     * @param keyPersonInfo - key person to compare
     * @param budgetPersonnelDetails person from BudgetPersonnelDetails
     * @return true if persons match, false otherwise
     * @see org.kuali.kra.s2s.service.S2SUtilService#keyPersonEqualsBudgetPerson(org.kuali.kra.s2s.generator.bo.KeyPersonInfo,
     *      BudgetPersonnelDetailsContract)
     */
    public boolean keyPersonEqualsBudgetPerson(KeyPersonInfo keyPersonInfo, BudgetPersonnelDetailsContract budgetPersonnelDetails) {
        boolean equal = false;
        if (keyPersonInfo != null && budgetPersonnelDetails != null) {
            String budgetPersonId = budgetPersonnelDetails.getPersonId();
            if ((keyPersonInfo.getPersonId() != null && keyPersonInfo.getPersonId().equals(budgetPersonId))
                    || (keyPersonInfo.getRolodexId() != null && keyPersonInfo.getRolodexId().toString().equals(budgetPersonId))) {
                equal = true;
            }
        }
        return equal;
    }

    /**
     * 
     * This method is used to get the details of Contact person
     * 
     * @param pdDoc(ProposalDevelopmentDocumentContract) proposal development document.
     * @return depPerson(DepartmentalPerson) corresponding to the contact type.
     */
    public DepartmentalPerson getContactPerson(ProposalDevelopmentDocumentContract pdDoc) {
        String contactType = getContactType();
        boolean isNumber = true;
        try {
            Integer.parseInt(contactType);
        }
        catch (NumberFormatException e) {
            isNumber = false;
        }
        DepartmentalPerson depPerson = new DepartmentalPerson();
        if (isNumber) {
            UnitContract leadUnit = pdDoc.getDevelopmentProposal().getOwnedByUnit();
                    if (leadUnit!=null) {
                        KcPersonContract unitAdmin = null;
                        for (UnitAdministratorContract admin : leadUnit.getUnitAdministrators()) {
                            if (contactType.equals(admin.getUnitAdministratorType().getCode())) {
                                unitAdmin = kcPersonRepositoryService.findKcPersonByPersonId(admin.getPersonId());
                                depPerson.setLastName(unitAdmin.getLastName());
                                depPerson.setFirstName(unitAdmin.getFirstName());
                                if (unitAdmin.getMiddleName() != null) {
                                    depPerson.setMiddleName(unitAdmin.getMiddleName());
                                }
                                depPerson.setEmailAddress(unitAdmin.getEmailAddress());
                                depPerson.setOfficePhone(unitAdmin.getOfficePhone());
                                depPerson.setFaxNumber(unitAdmin.getFaxNumber());
                                depPerson.setPrimaryTitle(unitAdmin.getPrimaryTitle());
                                depPerson.setAddress1(unitAdmin.getAddressLine1());
                                depPerson.setAddress2(unitAdmin.getAddressLine2());
                                depPerson.setAddress3(unitAdmin.getAddressLine3());
                                depPerson.setCity(unitAdmin.getCity());
                                depPerson.setCounty(unitAdmin.getCounty());
                                depPerson.setCountryCode(unitAdmin.getCountryCode());
                                depPerson.setPostalCode(unitAdmin.getPostalCode());
                                depPerson.setState(unitAdmin.getState());
                                break;
                            }
                        }
                        if (unitAdmin == null) {
                            UnitContract parentUnit = getUnitRepositoryService().findTopUnit();
                            for (UnitAdministratorContract parentAdmin : parentUnit.getUnitAdministrators()) {
                                if (contactType.equals(parentAdmin.getUnitAdministratorType().getCode())) {
                                    KcPersonContract parentUnitAdmin = kcPersonRepositoryService.findKcPersonByPersonId(parentAdmin.getPersonId());
                                    depPerson.setLastName(parentUnitAdmin.getLastName());
                                    depPerson.setFirstName(parentUnitAdmin.getFirstName());
                                    if (parentUnitAdmin.getMiddleName() != null) {
                                        depPerson.setMiddleName(parentUnitAdmin.getMiddleName());
                                    }
                                    depPerson.setEmailAddress(parentUnitAdmin.getEmailAddress());
                                    depPerson.setOfficePhone(parentUnitAdmin.getOfficePhone());
                                    depPerson.setFaxNumber(parentUnitAdmin.getFaxNumber());
                                    depPerson.setPrimaryTitle(parentUnitAdmin.getPrimaryTitle());
                                    depPerson.setAddress1(parentUnitAdmin.getAddressLine1());
                                    depPerson.setAddress2(parentUnitAdmin.getAddressLine2());
                                    depPerson.setAddress3(parentUnitAdmin.getAddressLine3());
                                    depPerson.setCity(parentUnitAdmin.getCity());
                                    depPerson.setCounty(parentUnitAdmin.getCounty());
                                    depPerson.setCountryCode(parentUnitAdmin.getCountryCode());
                                    depPerson.setPostalCode(parentUnitAdmin.getPostalCode());
                                    depPerson.setState(parentUnitAdmin.getState());
                                    break;
                                }
                            }
                        }
                    }
                
            
        }
        return depPerson;
    }

    public KcPersonRepositoryService getKcPersonRepositoryService() {
        return kcPersonRepositoryService;
    }

    public void setKcPersonRepositoryService(KcPersonRepositoryService kcPersonRepositoryService) {
        this.kcPersonRepositoryService = kcPersonRepositoryService;
    }

    /**
     * 
     * This method returns the type of contact person for a proposal
     * 
     * @return String contact type for the proposal
     */
    protected String getContactType() {
        String contactType = s2SConfigurationService.getValueAsString(ConfigurationConstants.PROPOSAL_CONTACT_TYPE);
        if (contactType == null || contactType.length() == 0) {
            contactType = CONTACT_TYPE_O;
        }
        return contactType;
    }

    /**
     * 
     * This method computes the number of months between any 2 given {@link Date} objects
     * 
     * @param dateStart starting date.
     * @param dateEnd end date.
     * 
     * @return number of months between the start date and end date.
     */
    public ScaleTwoDecimal getNumberOfMonths(java.util.Date dateStart, java.util.Date dateEnd) {
        ScaleTwoDecimal monthCount = ScaleTwoDecimal.ZERO;
        int fullMonthCount = 0;

        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        startDate.setTime(dateStart);
        endDate.setTime(dateEnd);

        startDate.clear(Calendar.HOUR);
        startDate.clear(Calendar.MINUTE);
        startDate.clear(Calendar.SECOND);
        startDate.clear(Calendar.MILLISECOND);

        endDate.clear(Calendar.HOUR);
        endDate.clear(Calendar.MINUTE);
        endDate.clear(Calendar.SECOND);
        endDate.clear(Calendar.MILLISECOND);

        if (startDate.after(endDate)) {
            return ScaleTwoDecimal.ZERO;
        }
        int startMonthDays = startDate.getActualMaximum(Calendar.DATE) - startDate.get(Calendar.DATE);
        startMonthDays++;
        int startMonthMaxDays = startDate.getActualMaximum(Calendar.DATE);
        BigDecimal startMonthFraction = BigDecimal.valueOf(startMonthDays).setScale(2, RoundingMode.HALF_UP).divide(BigDecimal.valueOf(startMonthMaxDays).setScale(2, RoundingMode.HALF_UP), RoundingMode.HALF_UP);

        int endMonthDays = endDate.get(Calendar.DATE);
        int endMonthMaxDays = endDate.getActualMaximum(Calendar.DATE);

        BigDecimal endMonthFraction = BigDecimal.valueOf(endMonthDays).setScale(2, RoundingMode.HALF_UP).divide(BigDecimal.valueOf(endMonthMaxDays).setScale(2, RoundingMode.HALF_UP), RoundingMode.HALF_UP);

        startDate.set(Calendar.DATE, 1);
        endDate.set(Calendar.DATE, 1);

        while (startDate.getTimeInMillis() < endDate.getTimeInMillis()) {
            startDate.set(Calendar.MONTH, startDate.get(Calendar.MONTH) + 1);
            fullMonthCount++;
        }
        fullMonthCount = fullMonthCount - 1;
        monthCount = monthCount.add(new ScaleTwoDecimal(fullMonthCount)).add(new ScaleTwoDecimal(startMonthFraction)).add(new ScaleTwoDecimal(endMonthFraction));
        return monthCount;
    }


    public void setCitizenshipTypeService(CitizenshipTypeService citizenshipTypeService) {
        this.citizenshipTypeService = citizenshipTypeService;
    }

    /**
     * Implementation should return one of the enums defined in PHS398CareerDevelopmentAwardSup11V11 form schema. For now, it
     * returns US RESIDENT as default
     * 
     * @see org.kuali.kra.s2s.service.S2SUtilService#getCitizenship(ProposalPersonContract)
     * 
     */
    public CitizenshipTypes getCitizenship(ProposalPersonContract proposalPerson) {
        String citizenSource = "1";
        String piCitizenShipValue = s2SConfigurationService.getValueAsString(ConfigurationConstants.PI_CUSTOM_DATA);
        if (piCitizenShipValue != null) {
            citizenSource = piCitizenShipValue;
        }
        if (citizenSource.equals("0")) {
            CitizenshipTypes citizenShipType = citizenshipTypeService.getCitizenshipDataFromExternalSource();
            return citizenShipType;
        }
        else {
            Integer citizenShip;
            Boolean allowOverride = s2SConfigurationService.getValueAsBoolean(
                    ConfigurationConstants.ALLOW_PROPOSAL_PERSON_TO_OVERRIDE_KC_PERSON_EXTENDED_ATTRIBUTES);
            if (allowOverride) {
                citizenShip = proposalPerson.getCitizenshipType().getCode();
            }
            else {
                citizenShip = proposalPerson.getPerson().getCitizenshipTypeCode();
            }
            CitizenshipTypes retVal = null;
            String citizenShipCode = String.valueOf(citizenShip);
            if (citizenShipCode.equals(s2SConfigurationService.getValueAsString(
                    ConfigurationConstants.NON_US_CITIZEN_WITH_TEMPORARY_VISA_TYPE_CODE))) {
                return CitizenshipTypes.NON_US_CITIZEN_WITH_TEMPORARY_VISA;
            }
            else if (citizenShipCode.equals(s2SConfigurationService.getValueAsString(
                    ConfigurationConstants.PERMANENT_RESIDENT_OF_US_TYPE_CODE))) {
                return CitizenshipTypes.PERMANENT_RESIDENT_OF_US;
            }
            else if (citizenShipCode.equals(s2SConfigurationService.getValueAsString(
                    ConfigurationConstants.US_CITIZEN_OR_NONCITIZEN_NATIONAL_TYPE_CODE))) {
                return CitizenshipTypes.US_CITIZEN_OR_NONCITIZEN_NATIONAL;
            }
            else if (citizenShipCode.equals(s2SConfigurationService.getValueAsString(
                    ConfigurationConstants.PERMANENT_RESIDENT_OF_US_PENDING))) {
                return CitizenshipTypes.PERMANENT_RESIDENT_OF_US_PENDING;
            }
            else {
                throw new IllegalArgumentException("Invalid citizenship type provided");
            }

        }
    }

    public String removeTimezoneFactor(String applicationXmlText) {
        Calendar cal = Calendar.getInstance();
        int dstOffsetMilli = cal.get(Calendar.DST_OFFSET);
        int zoneOffsetMilli = cal.get(Calendar.ZONE_OFFSET);
        zoneOffsetMilli = cal.getTimeZone().useDaylightTime()?zoneOffsetMilli+dstOffsetMilli:zoneOffsetMilli;
        int zoneOffset = zoneOffsetMilli/(1000*60*60);
        String timezoneId = TimeZone.getTimeZone("GMT"+zoneOffset).getID();
        String offset="+00:00";
        if(timezoneId.length()>6){
            offset = timezoneId.substring(timezoneId.length()-6);
        }
        String filteredApplicationStr = StringUtils.remove(applicationXmlText, offset);
        return filteredApplicationStr;
    }

    public S2SConfigurationService getS2SConfigurationService() {
        return s2SConfigurationService;
    }

    public void setS2SConfigurationService(S2SConfigurationService s2SConfigurationService) {
        this.s2SConfigurationService = s2SConfigurationService;
    }

    public CitizenshipTypeService getCitizenshipTypeService() {
        return citizenshipTypeService;
    }

    public KcStateService getKcStateService() {
        return kcStateService;
    }

    public void setKcStateService(KcStateService kcStateService) {
        this.kcStateService = kcStateService;
    }

    public KcCountryService getKcCountryService() {
        return kcCountryService;
    }

    public void setKcCountryService(KcCountryService kcCountryService) {
        this.kcCountryService = kcCountryService;
    }

    public ProposalAdminDetailsService getProposalAdminDetailsService() {
        return proposalAdminDetailsService;
    }

    public void setProposalAdminDetailsService(ProposalAdminDetailsService proposalAdminDetailsService) {
        this.proposalAdminDetailsService = proposalAdminDetailsService;
    }

    public QuestionAnswerService getQuestionAnswerService() {
        return questionAnswerService;
    }

    public void setQuestionAnswerService(QuestionAnswerService questionAnswerService) {
        this.questionAnswerService = questionAnswerService;
    }

    public PropDevQuestionAnswerService getPropDevQuestionAnswerService() {
        return propDevQuestionAnswerService;
    }

    public void setPropDevQuestionAnswerService(PropDevQuestionAnswerService propDevQuestionAnswerService) {
        this.propDevQuestionAnswerService = propDevQuestionAnswerService;
    }

    public RolodexService getRolodexService() {
        return rolodexService;
    }

    public void setRolodexService(RolodexService rolodexService) {
        this.rolodexService = rolodexService;
    }

    public UnitRepositoryService getUnitRepositoryService() {
        return unitRepositoryService;
    }

    public void setUnitRepositoryService(UnitRepositoryService unitRepositoryService) {
        this.unitRepositoryService = unitRepositoryService;
    }
}
