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
package org.kuali.kra.s2s.service.impl;

import gov.grants.apply.system.universalCodesV20.CountryCodeDataType;
import gov.grants.apply.system.universalCodesV20.StateCodeDataType;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TimeZone;

import org.kuali.kra.bo.Country;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.State;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.bo.ProposalYnq;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.bo.S2sOpportunity;
import org.kuali.kra.s2s.generator.bo.DepartmentalPerson;
import org.kuali.kra.s2s.service.S2SGeneratorUtilService;
import org.kuali.kra.s2s.util.PropertyFileReader;
import org.kuali.kra.s2s.util.S2SConstants;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DateTimeService;
import org.kuali.rice.kns.service.KualiConfigurationService;

/**
 * 
 * This class has many helper methods which can be used in stream generators.
 */
public class S2SGeneratorUtilServiceImpl implements S2SGeneratorUtilService {

    private BusinessObjectService businessObjectService;
    private DateTimeService dateTimeService;
    private KualiConfigurationService kualiConfigurationService;
    private static final String SUBMISSION_TYPE_CODE = "submissionTypeCode";
    private static final String SUBMISSION_TYPE_DESCRIPTION = "submissionTypeDescription";
    private static final String YNQ_QUESTION_ID_TYPE_EQ = "EQ";
    private static final String YNQ_NOT_REVIEWED = "N";
    private static final String DATE_FORMAT = "MM/dd/yyyy";
    private static final String KEY_COUNTRY_CODE = "countryCode";

    /**
     * 
     * Constructs a S2SGeneratorUtilServiceImpl.java.
     */
    public S2SGeneratorUtilServiceImpl() {
    }

    /**
     * Returns submission type for a proposal document.
     * @see org.kuali.kra.s2s.service.S2SGeneratorUtilService#getSubmissionType(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
     */
    public Map<String, String> getSubmissionType(ProposalDevelopmentDocument pdDoc) {
        Map<String, String> submissionInfo = new HashMap<String, String>();
        S2sOpportunity opportunity = pdDoc.getDevelopmentProposal().getS2sOpportunity();
        if (opportunity != null) {
            opportunity.refreshNonUpdateableReferences();
            String submissionTypeCode = opportunity.getS2sSubmissionTypeCode();
            String submissionTypeDescription = opportunity.getS2sSubmissionType().getDescription();
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
     * Fetch Rolodex record for the organization attached to the Proposal.
     * @see org.kuali.kra.s2s.service.S2SGeneratorUtilService#getDepartmentalPerson(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
     */
    public DepartmentalPerson getDepartmentalPerson(ProposalDevelopmentDocument pdDoc) {
        int count = 0;
        DepartmentalPerson depPerson = new DepartmentalPerson();
        // TODO fetch count from institute proposal tables after its implementation
        if (count < 1) {
            // Proposal has not been submitted

            Organization organization = pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization();
            Rolodex rolodex = pdDoc.getDevelopmentProposal().getApplicantOrganization().getRolodex();
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
                depPerson.setCountryCode(rolodex.getCountryCode());
                depPerson.setFaxNumber(rolodex.getFaxNumber());
                depPerson.setPostalCode(rolodex.getPostalCode());
                depPerson.setState(rolodex.getState());
                depPerson.setPersonId(Integer.toString(rolodex.getRolodexId()));
                depPerson.setDirDept(organization.getOrganizationName());
            }
        }
        else {
            // proposal has been submitted
            // TODO fetched SIGNED_BY from PROPOSAL_ADMIN_DETAILS after implementation and complete the remaining logic

        }
        return depPerson;
    }

    /**
     * Returns questions for End of state review
     * @see org.kuali.kra.s2s.service.S2SGeneratorUtilService#getEOStateReview(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
     */
    public Map<String, String> getEOStateReview(ProposalDevelopmentDocument pdDoc) {
        Map<String, String> stateReview = new HashMap<String, String>();
        for (ProposalYnq proposalYnq : pdDoc.getDevelopmentProposal().getProposalYnqs()) {
            if (proposalYnq.getQuestionId().equals(YNQ_QUESTION_ID_TYPE_EQ)) {
                stateReview.put(S2SConstants.YNQ_ANSWER, proposalYnq.getAnswer());
                stateReview.put(S2SConstants.YNQ_REVIEW_DATE, String.valueOf(proposalYnq.getReviewDate().getTime()));
            }
        }

        if (stateReview.size() == 0) {
            stateReview.put(S2SConstants.YNQ_ANSWER, YNQ_NOT_REVIEWED);
            stateReview.put(S2SConstants.YNQ_REVIEW_DATE, null);
        }
        return stateReview;

    }

    /**
     * This should be implemented after implementing Institute proposal.
     * @see org.kuali.kra.s2s.service.S2SGeneratorUtilService#getFederalId(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
     */
    public String getFederalId(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        // TODO Implement it after institute proposal development is completed
        return S2SConstants.FEDERAL_ID_NOT_FOUND;
    }

    /**
     * 
     * This method fetches system constant parameters
     * 
     * @param parameter String for which value must be fetched
     * @return String
     */
    public String getParameterValue(String parameter) {
        return kualiConfigurationService.getParameter(Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, 
                Constants.PARAMETER_COMPONENT_DOCUMENT,parameter).getParameterValue();
    }

    /**
     * Create country code data type
     * @see org.kuali.kra.s2s.service.S2SGeneratorUtilService#getCountryCodeDataType(java.lang.String)
     */
    public CountryCodeDataType.Enum getCountryCodeDataType(String countryCode) {
        CountryCodeDataType.Enum countryCodeDataType = null;
        Map<String, String> countryMap = new HashMap<String, String>();
        countryMap.put(KEY_COUNTRY_CODE, countryCode);
        Country country = (Country) businessObjectService.findByPrimaryKey(Country.class, countryMap);
        countryCodeDataType = CountryCodeDataType.Enum.forString(country.getCountryCode() + ": "
                + country.getCountryName().toUpperCase());
        return countryCodeDataType;
    }

    /**
     * Create State code data type
     * @see org.kuali.kra.s2s.service.S2SGeneratorUtilService#getStateCodeDataType(java.lang.String)
     */
    public StateCodeDataType.Enum getStateCodeDataType(String stateName) {
        StateCodeDataType.Enum stateCodeDataType = null;
        Map<String, String> stateMap = new HashMap<String, String>();
        stateMap.put("stateCode", stateName);
        Iterator<State> stateList = businessObjectService.findMatching(State.class, stateMap).iterator();
        while (stateList.hasNext()) {
            State state = stateList.next();
            if (state.getStateCode().equals(stateName)) {
                stateCodeDataType = StateCodeDataType.Enum.forString(state.getStateCode() + ": " + state.getDescription());
                break;
            }
        }
        return stateCodeDataType;
    }

    /**
     * 
     * This method is to format the date
     * @param date String
     * @param format String
     * @return formatted date string
     */
    private String formatDate(String dateStr, String formatStr) {
        int year = 0;
        int month = 0;
        int day = 0;
        if (dateStr == null || formatStr == null)
            return null;
        else {
            year = Integer.parseInt(dateStr.substring(0, 4));
            month = Integer.parseInt(dateStr.substring(5, 7));
            day = Integer.parseInt(dateStr.substring(8, 10));
        }
        return formatDate("" + month + "/" + day + "/" + year, "/-:,");
    }

    /**
     * Convert the date string with YYYY-MM-DD format to calendar object
     * @see org.kuali.kra.s2s.service.S2SGeneratorUtilService#convertDateStringToCalendar(java.lang.String)
     */
    public Calendar convertDateStringToCalendar(String dateStr) {
        GregorianCalendar calendar = new GregorianCalendar();

        if (dateStr != null) {
            if (dateStr.indexOf('-') != -1) { // if the format received is YYYY-MM-DD
                dateStr = formatDate(dateStr, DATE_FORMAT);
            }
            calendar.set(Integer.parseInt(dateStr.substring(6, 10)), Integer.parseInt(dateStr.substring(0, 2)) - 1, Integer
                    .parseInt(dateStr.substring(3, 5)));

            return calendar;
        }
        return null;
    }

    /**
     * 
     * @see org.kuali.kra.s2s.service.S2SGeneratorUtilService#getCurrentCalendar()
     */
    public Calendar getCurrentCalendar() {
        return dateTimeService.getCurrentCalendar();
    }

    /**
     * 
     * @see org.kuali.kra.s2s.service.S2SGeneratorUtilService#convertDateToCalendar(java.sql.Date)
     */
    public Calendar convertDateToCalendar(Date date) {
        if (date == null)
            return null;
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        cal.setTime(date);
        return cal;
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
     * Sets the dateTimeService attribute value.
     * 
     * @param dateTimeService The dateTimeService to set.
     */
    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }
    

    /**
     * Gets the kualiConfigurationService attribute. 
     * @return Returns the kualiConfigurationService.
     */
    public KualiConfigurationService getKualiConfigurationService() {
        return kualiConfigurationService;
    }

    /**
     * Sets the kualiConfigurationService attribute value.
     * @param kualiConfigurationService The kualiConfigurationService to set.
     */
    public void setKualiConfigurationService(KualiConfigurationService kualiConfigurationService) {
        this.kualiConfigurationService = kualiConfigurationService;
    }

}
