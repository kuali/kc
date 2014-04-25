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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.person.attr.CitizenshipType;
import org.kuali.coeus.common.framework.person.attr.CitizenshipTypeService;
import org.kuali.coeus.common.api.rolodex.RolodexContract;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.common.framework.unit.admin.UnitAdministrator;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.budget.personnel.BudgetPersonnelDetails;
import org.kuali.kra.infrastructure.CitizenshipTypes;
import org.kuali.kra.institutionalproposal.proposaladmindetails.ProposalAdminDetails;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.question.ProposalDevelopmentModuleQuestionnaireBean;
import org.kuali.kra.questionnaire.Questionnaire;
import org.kuali.kra.questionnaire.QuestionnaireQuestion;
import org.kuali.kra.questionnaire.answer.Answer;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean;
import org.kuali.kra.questionnaire.answer.QuestionnaireAnswerService;
import org.kuali.coeus.propdev.impl.s2s.question.ProposalDevelopmentS2sQuestionnaireService;
import org.kuali.coeus.propdev.impl.s2s.S2sOpportunity;
import org.kuali.kra.s2s.generator.bo.DepartmentalPerson;
import org.kuali.kra.s2s.generator.bo.KeyPersonInfo;
import org.kuali.kra.s2s.service.S2SUtilService;
import org.kuali.kra.s2s.util.S2SConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.location.api.country.Country;
import org.kuali.rice.location.api.country.CountryService;
import org.kuali.rice.location.api.state.State;
import org.kuali.rice.location.api.state.StateService;

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
public class S2SUtilServiceImpl implements S2SUtilService {


    private BusinessObjectService businessObjectService;
    private ParameterService parameterService;
    private KcPersonService kcPersonService;
    private CitizenshipTypeService citizenshipTypeService;
    private UnitService unitService;
    private ProposalDevelopmentS2sQuestionnaireService proposalDevelopmentS2sQuestionnaireService;
    private static final String SUBMISSION_TYPE_CODE = "submissionTypeCode";
    private static final String SUBMISSION_TYPE_DESCRIPTION = "submissionTypeDescription";
    private static final String YNQ_NOT_REVIEWED = "X";
    private static final int DIVISION_NAME_MAX_LENGTH = 30;
    private static final String PROPOSAL_CONTACT_TYPE = "PROPOSAL_CONTACT_TYPE";
    private static final String CONTACT_TYPE_O = "O";
    private static final Log LOG = LogFactory.getLog(S2SUtilServiceImpl.class);

    public static final String PROPOSAL_YNQ_QUESTION_129 = "129";
    public static final String PROPOSAL_YNQ_QUESTION_130 = "130";
    public static final String PROPOSAL_YNQ_QUESTION_131 = "131";
    
    private static final String MODULE_ITEM_KEY = "moduleItemKey";
    private static final String MODULE_ITEM_CODE = "moduleItemCode";
    private static final Integer MODULE_ITEM_CODE_THREE = 3;
    private static final String MODULE_SUB_ITEM_CODE = "moduleSubItemCode";
    private static final Integer MODULE_SUB_ITEM_CODE_ZERO = 0;
    private static final String MODULE_SUB_ITEM_KEY = "moduleSubItemKey";
    private static final Integer MODULE_SUB_ITEM_KEY_ZERO = 0;

    private static final String SEQUENCE_NUMBER = "sequenceNumber";
    private static final String QUESTIONNAIRE_ID = "questionnaireId";
    private static final String QUESTIONNAIRE_REF_ID_FK = "questionnaireRefIdFk";
    private static final String PI_CUSTOM_DATA = "PI_CITIZENSHIP_FROM_CUSTOM_DATA";

    /**
     * This method creates and returns Map of submission details like submission type, description and Revision code
     *
     * @param pdDoc Proposal Development Document.
     * @return Map<String, String> Map of submission details.
     * @see org.kuali.kra.s2s.service.S2SUtilService#getSubmissionType(org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument)
     */
    public Map<String, String> getSubmissionType(ProposalDevelopmentDocument pdDoc) {
        Map<String, String> submissionInfo = new HashMap<String, String>();
        S2sOpportunity opportunity = pdDoc.getDevelopmentProposal().getS2sOpportunity();
        if (opportunity != null) {
            opportunity.refreshNonUpdateableReferences();
            String submissionTypeCode = opportunity.getS2sSubmissionTypeCode();
            String submissionTypeDescription = "";
            if (opportunity.getS2sSubmissionType() != null) {
                submissionTypeDescription = opportunity.getS2sSubmissionType().getDescription();
            }
            String revisionCode = opportunity.getRevisionCode();
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
     * @see org.kuali.kra.s2s.service.S2SUtilService#getDepartmentalPerson(org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument)
     */
    public DepartmentalPerson getDepartmentalPerson(ProposalDevelopmentDocument pdDoc) {
        int count = 0;
        DepartmentalPerson depPerson = new DepartmentalPerson();
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("devProposalNumber", pdDoc.getDevelopmentProposal().getProposalNumber());
        List<ProposalAdminDetails> proposalAdminDetailsList = (List<ProposalAdminDetails>) businessObjectService.findMatching(
                ProposalAdminDetails.class, fieldValues);
        count = proposalAdminDetailsList.size();
        if (count < 1) {
            // Proposal has not been submitted

            Organization organization = pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization();
            RolodexContract rolodex = organization == null ? null : organization.getRolodex();
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
            ProposalAdminDetails proposalAdminDetails = proposalAdminDetailsList.get(0);
            KcPerson person = this.kcPersonService.getKcPersonByUserName(proposalAdminDetails.getSignedBy());

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
     * @param proposalPersons list of {@link ProposalPerson}
     * @param firstN value that determines whether the returned list should contain first n persons or the rest of persons
     * @param n number of key persons that are considered as not extra persons
     * @return list of {@link ProposalPerson}
     * @see org.kuali.kra.s2s.service.S2SUtilService#getNKeyPersons(java.util.List, boolean, int)
     */
    public List<ProposalPerson> getNKeyPersons(List<ProposalPerson> proposalPersons, boolean firstN, int n) {
        ProposalPerson proposalPerson, previousProposalPerson;
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
            List<ProposalPerson> firstNPersons = new ArrayList<ProposalPerson>();

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
            List<ProposalPerson> extraPersons = new ArrayList<ProposalPerson>();
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
     * @see org.kuali.kra.s2s.service.S2SUtilService#getEOStateReview(org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument)
     */
    public Map<String, String> getEOStateReview(ProposalDevelopmentDocument pdDoc) {
        Map<String, String> stateReview = new HashMap<String, String>();
        ModuleQuestionnaireBean moduleQuestionnaireBean = new ProposalDevelopmentModuleQuestionnaireBean(pdDoc.getDevelopmentProposal());
        QuestionnaireAnswerService questionnaireAnswerService = KcServiceLocator.getService(QuestionnaireAnswerService.class);
        List<AnswerHeader> answerHeaders = questionnaireAnswerService.getQuestionnaireAnswer(moduleQuestionnaireBean);
        for (Answer answers : answerHeaders.get(0).getAnswers()) {
            if (answers.getQuestion().getQuestionId() != null
                    && answers.getQuestion().getQuestionId().equals(PROPOSAL_YNQ_QUESTION_129)) {
                if (stateReview.get(S2SConstants.YNQ_ANSWER) == null) {
                    stateReview.put(S2SConstants.YNQ_ANSWER, answers.getAnswer());
                }
            }
            if (answers.getQuestion().getQuestionId() != null
                    && answers.getQuestion().getQuestionId().equals(PROPOSAL_YNQ_QUESTION_130)) {
                if (stateReview.get(S2SConstants.YNQ_REVIEW_DATE) == null) {
                    stateReview.put(S2SConstants.YNQ_REVIEW_DATE, answers.getAnswer());
                }
            }
            if (answers.getQuestion().getQuestionId() != null
                    && answers.getQuestion().getQuestionId().equals(PROPOSAL_YNQ_QUESTION_131)) {
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
     * This method fetches system constant parameters
     *
     * @param parameter String for which value must be fetched
     * @return String System constant parameters.
     */
    protected String getParameterValue(String parameter) {
        String parameterValue = null;
        try {
            parameterValue = this.parameterService.getParameterValueAsString(ProposalDevelopmentDocument.class, parameter);
        }
        catch (IllegalArgumentException e) {
            LOG.error("Parameter not found - " + parameter, e);
        }
        return parameterValue;
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
     * @see org.kuali.kra.s2s.service.S2SUtilService#convertDateToCalendar(java.sql.Date)
     */
    public Calendar convertDateToCalendar(Date date) {
        Calendar calendar = null;
        if (date != null) {
            calendar = Calendar.getInstance();
            calendar.setTime(date);
        }
        return calendar;
    }

    /**
     * This method is to set businessObjectService
     * 
     * @param businessObjectService(BusinessObjectService)
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * Sets the ParameterService.
     * 
     * @param parameterService the parameter service.
     */
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    /**
     * This method is to get division name using the OwnedByUnit and traversing through the parent units till the top level
     * 
     * @param pdDoc Proposal development document.
     * @return divisionName based on the OwnedByUnit.
     * @see org.kuali.kra.s2s.service.S2SUtilService#getDivisionName(org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument)
     */
    public String getDivisionName(ProposalDevelopmentDocument pdDoc) {
        String divisionName = null;
        if (pdDoc != null && pdDoc.getDevelopmentProposal().getOwnedByUnit() != null) {
            Unit ownedByUnit = pdDoc.getDevelopmentProposal().getOwnedByUnit();
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
     * @see org.kuali.kra.s2s.service.S2SUtilService#getPrincipalInvestigator(org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument)
     */
    public ProposalPerson getPrincipalInvestigator(ProposalDevelopmentDocument pdDoc) {
        ProposalPerson proposalPerson = null;
        if (pdDoc != null) {
            for (ProposalPerson person : pdDoc.getDevelopmentProposal().getProposalPersons()) {
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
    public List<ProposalPerson> getCoInvestigators(ProposalDevelopmentDocument pdDoc) {
        List<ProposalPerson> investigators = new ArrayList<ProposalPerson>();
        if (pdDoc != null) {
            for (ProposalPerson person : pdDoc.getDevelopmentProposal().getProposalPersons()) {
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
    public List<ProposalPerson> getKeyPersons(ProposalDevelopmentDocument pdDoc) {
        List<ProposalPerson> keyPersons = new ArrayList<ProposalPerson>();
        if (pdDoc != null) {
            for (ProposalPerson person : pdDoc.getDevelopmentProposal().getProposalPersons()) {
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
    public Country getCountryFromCode(String countryCode) {
        if(countryCode==null) return null;
        Country country = getCountryService().getCountryByAlternateCode(countryCode);
        if(country==null){
            country = getCountryService().getCountry(countryCode);
        }
        return country;
    }

    protected static CountryService getCountryService() {
        return KcServiceLocator.getService(CountryService.class);
    }

    /**
     * This method is to get a State object from the state name
     * 
     * @param stateName Name of the state
     * @return State object matching the name.
     * @see org.kuali.kra.s2s.service.S2SUtilService#getStateFromName(java.lang.String, java.lang.String)
     */
    public State getStateFromName(String countryAlternateCode, String stateName) {
        Country country = getCountryFromCode(countryAlternateCode);

        State state = getStateService().getState(country.getCode(), stateName);
        return state;
    }

    protected static StateService getStateService() {
        return KcServiceLocator.getService(StateService.class);
    }


    /**
     * This method compares a key person with budget person. It checks whether the key person is from PERSON or ROLODEX and matches
     * the respective person ID with the person in {@link BudgetPersonnelDetails}
     * 
     * @param keyPersonInfo - key person to compare
     * @param budgetPersonnelDetails person from BudgetPersonnelDetails
     * @return true if persons match, false otherwise
     * @see org.kuali.kra.s2s.service.S2SUtilService#keyPersonEqualsBudgetPerson(org.kuali.kra.s2s.generator.bo.KeyPersonInfo,
     *      org.kuali.kra.budget.personnel.BudgetPersonnelDetails)
     */
    public boolean keyPersonEqualsBudgetPerson(KeyPersonInfo keyPersonInfo, BudgetPersonnelDetails budgetPersonnelDetails) {
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
     * Finds all the Questionnaire Answers associates with provided ProposalNumber.
     * 
     * @param pdDoc
     * @return List of Questionnaire {@link Answer}.
     */
    public List<Answer> getQuestionnaireAnswers(ProposalDevelopmentDocument pdDoc, Integer questionnaireId) {
        List<Answer> questionnaireAnswers = new ArrayList<Answer>();
        String proposalNumber = pdDoc.getDevelopmentProposal().getProposalNumber();
        Questionnaire questionnaire = getHighestSequenceNumberQuestionnair(questionnaireId);
        if (questionnaire != null) {
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put(MODULE_ITEM_KEY, proposalNumber);
            fieldValues.put(MODULE_ITEM_CODE, MODULE_ITEM_CODE_THREE);
            fieldValues.put(MODULE_SUB_ITEM_CODE, MODULE_SUB_ITEM_CODE_ZERO);
            fieldValues.put(MODULE_SUB_ITEM_KEY, MODULE_SUB_ITEM_KEY_ZERO);
            fieldValues.put(QUESTIONNAIRE_REF_ID_FK, questionnaire.getQuestionnaireRefId());
            Collection<AnswerHeader> answerHeaderList = businessObjectService.findMatching(AnswerHeader.class, fieldValues);
            for (AnswerHeader answerHeader : answerHeaderList) {
                questionnaireAnswers.addAll(answerHeader.getAnswers());
            }
        }
        return questionnaireAnswers;
    }

    public List<Answer> getQuestionnaireAnswers(DevelopmentProposal developmentProposal, String namespace, String formname) {
        List<AnswerHeader> answerHeaders = getProposalDevelopmentS2sQuestionnaireService().getProposalAnswerHeaderForForm(
                developmentProposal, namespace, formname);
        List<Answer> questionnaireAnswers = new ArrayList<Answer>();
        for (AnswerHeader answerHeader : answerHeaders) {
            Questionnaire questionnaire = answerHeader.getQuestionnaire();
            List<QuestionnaireQuestion> questionnaireQuestions = questionnaire.getQuestionnaireQuestions();
            for (QuestionnaireQuestion questionnaireQuestion : questionnaireQuestions) {
                Answer questionAnswer = getAnswer(questionnaireQuestion, answerHeader);
                questionnaireAnswers.add(questionAnswer);
            }
        }
        return questionnaireAnswers;
    }

    protected Answer getAnswer(QuestionnaireQuestion questionnaireQuestion, AnswerHeader answerHeader) {
        List<Answer> answers = answerHeader.getAnswers();
        for (Answer answer : answers) {
            if (answer.getQuestionnaireQuestionsIdFk().equals(questionnaireQuestion.getQuestionnaireQuestionsId())) {
                return answer;
            }
        }
        return null;
    }

    /*
     * Finds the {@link Questionnaire} with Highest Sequence Number
     */
    protected Questionnaire getHighestSequenceNumberQuestionnair(Integer questionnaireId) {
        Questionnaire highestQuestionnairSequenceNumber = null;
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(QUESTIONNAIRE_ID, questionnaireId);
        Collection<Questionnaire> questionnairs = businessObjectService.findMatchingOrderBy(Questionnaire.class, fieldValues,
                SEQUENCE_NUMBER, Boolean.FALSE);
        if (questionnairs.size() > 0) {
            List<Questionnaire> questionnairList = new ArrayList<Questionnaire>();
            questionnairList.addAll(questionnairs);
            highestQuestionnairSequenceNumber = questionnairList.get(0);
        }
        return highestQuestionnairSequenceNumber;
    }

    /**
     * 
     * This method is used to get the details of Contact person
     * 
     * @param pdDoc(ProposalDevelopmentDocument) proposal development document.
     * @return depPerson(DepartmentalPerson) corresponding to the contact type.
     */
    public DepartmentalPerson getContactPerson(ProposalDevelopmentDocument pdDoc) {
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
            Unit leadUnit = pdDoc.getDevelopmentProposal().getOwnedByUnit();
                    if (leadUnit!=null) {
                        leadUnit.refreshReferenceObject("unitAdministrators");
                        KcPerson unitAdmin = null;
                        for (UnitAdministrator admin : leadUnit.getUnitAdministrators()) {
                            if (contactType.equals(admin.getUnitAdministratorTypeCode())) {
                                unitAdmin = admin.getPerson();
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
                            Unit parentUnit = getUnitService().getTopUnit();
                            for (UnitAdministrator parentAdmin : parentUnit.getUnitAdministrators()) {
                                if (contactType.equals(parentAdmin.getUnitAdministratorTypeCode())) {
                                    KcPerson parentUnitAdmin = parentAdmin.getPerson();
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

    /**
     * Gets the kcPersonService attribute.
     * 
     * @return Returns the kcPersonService.
     */
    public KcPersonService getKcPersonService() {
        return kcPersonService;
    }

    /**
     * Sets the kcPersonService attribute value.
     * 
     * @param kcPersonService The kcPersonService to set.
     */
    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

    /**
     * 
     * This method returns the type of contact person for a proposal
     * 
     * @return String contact type for the proposal
     */
    protected String getContactType() {
        String contactType = getParameterValue(PROPOSAL_CONTACT_TYPE);
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
    public ScaleTwoDecimal getNumberOfMonths(Date dateStart, Date dateEnd) {
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
     * @see org.kuali.kra.s2s.service.S2SUtilService#getCitizenship(org.kuali.coeus.propdev.impl.person.ProposalPerson)
     * 
     */
    public CitizenshipTypes getCitizenship(ProposalPerson proposalPerson) {
        String citizenSource = "1";
        String piCitizenShipValue = getParameterValue(PI_CUSTOM_DATA);
        if (piCitizenShipValue != null) {
            citizenSource = piCitizenShipValue;
        }
        if (citizenSource.equals("0")) {
            CitizenshipTypes citizenShipType = citizenshipTypeService.getCitizenshipDataFromExternalSource();
            return citizenShipType;
        }
        else {
            CitizenshipType citizenShip;
            String allowOverride = parameterService.getParameterValueAsString("KC-GEN", "A",
                    "ALLOW_PROPOSAL_PERSON_TO_OVERRIDE_KC_PERSON_EXTENDED_ATTRIBUTES");
            if ("Y".equals(allowOverride)) {
                citizenShip = proposalPerson.getCitizenshipType();
            }
            else {
                citizenShip = proposalPerson.getPerson().getExtendedAttributes().getCitizenshipType();
            }
            CitizenshipTypes retVal = null;
            String citizenShipCode = String.valueOf(citizenShip.getCitizenshipTypeCode());
            if (citizenShipCode.equals(parameterService.getParameterValueAsString("KC-GEN", "A",
                    "NON_US_CITIZEN_WITH_TEMPORARY_VISA_TYPE_CODE"))) {
                return CitizenshipTypes.NON_US_CITIZEN_WITH_TEMPORARY_VISA;
            }
            else if (citizenShipCode.equals(parameterService.getParameterValueAsString("KC-GEN", "A",
                    "PERMANENT_RESIDENT_OF_US_TYPE_CODE"))) {
                return CitizenshipTypes.PERMANENT_RESIDENT_OF_US;
            }
            else if (citizenShipCode.equals(parameterService.getParameterValueAsString("KC-GEN", "A",
                    "US_CITIZEN_OR_NONCITIZEN_NATIONAL_TYPE_CODE"))) {
                return CitizenshipTypes.US_CITIZEN_OR_NONCITIZEN_NATIONAL;
            }
            else if (citizenShipCode.equals(parameterService.getParameterValueAsString("KC-GEN", "A",
                    "PERMANENT_RESIDENT_OF_US_PENDING"))) {
                return CitizenshipTypes.PERMANENT_RESIDENT_OF_US_PENDING;
            }
            else {
                throw new IllegalArgumentException("Invalid citizenship type provided");
            }

        }
    }

    /**
     * Gets the proposalDevelopmentS2sQuestionnaireService attribute.
     * 
     * @return Returns the proposalDevelopmentS2sQuestionnaireService.
     */
    public ProposalDevelopmentS2sQuestionnaireService getProposalDevelopmentS2sQuestionnaireService() {
        return proposalDevelopmentS2sQuestionnaireService;
    }

    /**
     * Sets the proposalDevelopmentS2sQuestionnaireService attribute value.
     * 
     * @param proposalDevelopmentS2sQuestionnaireService The proposalDevelopmentS2sQuestionnaireService to set.
     */
    public void setProposalDevelopmentS2sQuestionnaireService(
            ProposalDevelopmentS2sQuestionnaireService proposalDevelopmentS2sQuestionnaireService) {
        this.proposalDevelopmentS2sQuestionnaireService = proposalDevelopmentS2sQuestionnaireService;
    }

    /**
     * Gets the unitService attribute.
     * 
     * @return Returns the unitService.
     */
    public UnitService getUnitService() {
        return unitService;
    }

    /**
     * Sets the unitService attribute value.
     * 
     * @param unitService The unitService to set.
     */
    public void setUnitService(UnitService unitService) {
        this.unitService = unitService;
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
}
