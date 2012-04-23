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
package org.kuali.kra.s2s.service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.personnel.BudgetPersonnelDetails;
import org.kuali.kra.infrastructure.CitizenshipTypes;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.questionnaire.answer.Answer;
import org.kuali.kra.s2s.generator.bo.DepartmentalPerson;
import org.kuali.kra.s2s.generator.bo.KeyPersonInfo;
import org.kuali.rice.location.api.country.Country;
import org.kuali.rice.location.api.state.State;

/**
 * 
 * This class is for maintaining all reusable components that are part of S2S
 * module.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public interface S2SUtilService {

	/**
	 * 
	 * This method creates and returns Map of submission details like submission
	 * type, description and Revision code
	 * 
	 * @param pdDoc
	 *            Proposal Development Document.
	 * @return Map<String, String> Map of submission details.
	 */
	public Map<String, String> getSubmissionType(
			ProposalDevelopmentDocument pdDoc);

	/**
	 * 
	 * This method populates and returns the Departmental Person object for a
	 * given proposal document
	 * 
	 * @param pdDoc
	 *            Proposal Development Document.
	 * @return DepartmentalPerson departmental Person object for a given
	 *         proposal document.
	 */
	public DepartmentalPerson getDepartmentalPerson(
			ProposalDevelopmentDocument pdDoc);

	/**
	 * 
	 * This method returns a map containing the answers related to EOState
	 * REview for a given proposal
	 * 
	 * @param pdDoc
	 *            Proposal Development Document.
	 * @return Map<String, String> map containing the answers related to
	 *         EOState Review for a given proposal.
	 */
	public Map<String, String> getEOStateReview(
			ProposalDevelopmentDocument pdDoc);
	/**
	 * 
	 * This method limits the number of key persons to n, returns list of key
	 * persons, first n in case firstN is true, or all other than first n, in
	 * case of firstN being false
	 * 
	 * @param keyPersons
	 *            list of {@link ProposalPerson}
	 * @param firstN
	 *            value that determines whether the returned list should contain
	 *            first n persons or the rest of persons
	 * @param n
	 *            number of key persons that are considered as not extra persons
	 * @return list of {@link ProposalPerson}
	 */
	public List<ProposalPerson> getNKeyPersons(List<ProposalPerson> keyPersons,
			boolean firstN, int n);
	/**
	 * 
	 * This method returns the Federal ID for a given proposal
	 * 
	 * @param proposalDevelopmentDocument
	 *            Proposal Development Document.
	 * @return Federal ID for a given proposal.
	 */
	public String getFederalId(
			ProposalDevelopmentDocument proposalDevelopmentDocument);
	
	/**
	 * Get the tracking id from the newest development proposal linked to the
	 * institutional proposal.
	 * @param proposal
	 * @return
	 */
	String getGgTrackingIdFromProposal(InstitutionalProposal proposal);
	
	/**
	 * 
	 * This method fetches system constant parameters
	 * 
	 * @param parameter
	 *            String for which value must be fetched
	 * @return String System constant parameters.
	 */
	public String getParameterValue(String parameter);
	/**
	 * 
	 * This method returns a {@link Calendar} whose date matches the date passed
	 * as {@link String}
	 * 
	 * @param dateStr
	 *            string for which the Calendar value has to be found.
	 * @return Calendar calendar value corresponding to the date string.
	 */
	public Calendar convertDateStringToCalendar(String dateStr);
	/**
	 * 
	 * This method is used to get current Calendar
	 * 
	 * @return {@link Calendar}
	 */
	public Calendar getCurrentCalendar();
	/**
	 * 
	 * This method is used to get Calendar date
	 * 
	 * @param date(Date)
	 *            date for which Calendar value has to be found.
	 * @return cal(Calendar) calendar value corresponding to the date.
	 */
	public Calendar convertDateToCalendar(Date date);
	/**
	 * 
	 * This method is to get division name using the OwnedByUnit and traversing
	 * through the parent units till the top level
	 * 
	 * @param pdDoc
	 *            Proposal development document.
	 * @return divisionName based on the OwnedByUnit.
	 */
	public String getDivisionName(ProposalDevelopmentDocument pdDoc);
	/**
	 * 
	 * This method is to get PrincipalInvestigator from person list
	 * 
	 * @param pdDoc
	 *            Proposal development document.
	 * @return ProposalPerson PrincipalInvestigator for the proposal.
	 */
	public ProposalPerson getPrincipalInvestigator(
			ProposalDevelopmentDocument pdDoc);
	/**
	 * Finds a Country object from the country code
	 * 
	 * @param countryCode
	 *            Country name
	 * @return Country object matching the code
	 */
	public Country getCountryFromCode(String countryCode);
	/**
	 * Finds a State object from the state name
	 * @param default country 3-character code
	 * @param stateName
	 *            Name of the state (two-letter state code)
	 * @return State object matching the name.
	 */
	public State getStateFromName(String countryAlternateCode, String stateName);
	/**
	 * 
	 * This method compares a proposal person with budget person. It checks
	 * whether the proposal person is from PERSON or ROLODEX and matches the
	 * respective person ID with the person in {@link BudgetPersonnelDetails}
	 * 
	 * @param proposalPerson -
	 *            key person from proposal
	 * @param budgetPersonnelDetails
	 *            person from BudgetPersonnelDetails
	 * @return true if persons match, false otherwise
	 */
	public boolean proposalPersonEqualsBudgetPerson(
			ProposalPerson proposalPerson,
			BudgetPersonnelDetails budgetPersonnelDetails);

	/**
	 * 
	 * This method compares a key person with budget person. It checks whether
	 * the key person is from PERSON or ROLODEX and matches the respective
	 * person ID with the person in {@link BudgetPersonnelDetails}
	 * 
	 * @param keyPersonInfo -
	 *            key person to compare
	 * @param budgetPersonnelDetails
	 *            person from BudgetPersonnelDetails
	 * @return true if persons match, false otherwise
	 */
	public boolean keyPersonEqualsBudgetPerson(KeyPersonInfo keyPersonInfo,
			BudgetPersonnelDetails budgetPersonnelDetails);

	/**
	 * 
	 * This method is used to get the property from configuration file. If there
	 * is no value defined, it returns defaultValue
	 * 
	 * @param key
	 * @return value
	 */
	public String getProperty(String key);

	/**
	 * This method converts String objects in the String array into a String
	 * separated by commas and returns it.
	 * 
	 * @param stringArray
	 * @return String created from StringArray
	 */
	public String convertStringArrayToString(String[] stringArray);
	
	/**
	 * 
	 * Converts String objects in a list into a String separated by commas
	 * @param stringList
	 * @return
	 */
	public String convertStringListToString(List<String> stringList);	

	/**
	 * Finds all the Questionnaire Answers associates with provided
	 * ProposalNumber and questionnaireId.
	 * 
	 * @param DevelopmentProposal
	 * @param namespace
	 * @param formname
	 * @return List of Questionnaire {@link Answer}.
	 */
    public List<Answer> getQuestionnaireAnswers(DevelopmentProposal developmentProposal,String namespace,String formname);
    /**
     * 
     * This method gets the answers from a questionnaire for a proposal person.
     * @param pdDoc
     * @param proposalPerson
     * @return
     */
    public List<Answer> getQuestionnaireAnswersForPI(ProposalDevelopmentDocument pdDoc);
	/**
	 * Finds all the co-investigators associated with the provided pdDoc.
	 * @param ProposalDevelopmentDocument
	 * @return List of Co-Investigators {@link ProposalPerson}.
	 */
	
	public List<ProposalPerson> getCoInvestigators(ProposalDevelopmentDocument pdDoc);
	
	/**
	 * Finds all the key Person associated with the provided pdDoc.
	 * @param ProposalDevelopmentDocument
	 * @return List of Key Persons {@link ProposalPerson}.
	 */
	
	public List<ProposalPerson> getKeyPersons (ProposalDevelopmentDocument pdDoc);

    /**
     * 
     * This method is used to get the details of Contact person
     * 
     * @param pdDoc(ProposalDevelopmentDocument)
     *            proposal development document.
     * @param contactType(String)
     *            for which the DepartmentalPerson has to be found.
     * @return depPerson(DepartmentalPerson) corresponding to the contact type.
     */
    public DepartmentalPerson getContactPerson(ProposalDevelopmentDocument pdDoc) ;
    
    /**
     * 
     * This method is used to delete all auto generated pdfs as part of s2s forms creation
     * @param ProposalDevelopmentDocument
     */
    public void deleteSystemGeneratedAttachments(ProposalDevelopmentDocument pdDoc);
    
    public BudgetDecimal getNumberOfMonths(Date dateStart, Date dateEnd);
    /**
     * 
     * This method gets the Federal Agency for the given
     * {@link DevelopmentProposal}
     * 
     * @param pdDoc
     *            Proposal Development Document.
     * @return {@link String} Federal Agency
     */
    public String getCognizantFedAgency(DevelopmentProposal developmentProposal);

    /**
     * 
     * This method is used to get the citizenship from either warehouse or from person custom element
     * @param proposalPerson
     * @return
     */
    public CitizenshipTypes getCitizenship(ProposalPerson proposalPerson);

    public String removeTimezoneFactor(String applicationXmlText);

}
