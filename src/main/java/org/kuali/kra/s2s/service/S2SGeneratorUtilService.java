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
package org.kuali.kra.s2s.service;

import gov.grants.apply.system.universalCodesV20.CountryCodeDataType;
import gov.grants.apply.system.universalCodesV20.StateCodeDataType;

import java.sql.Date;
import java.util.Calendar;
import java.util.Map;

import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.generator.bo.DepartmentalPerson;

/**
 * 
 * This class is for defining reusable methods for the stream generators
 */
public interface S2SGeneratorUtilService {
    /**
     * Returns submission type for a proposal document.
     * @param pdDoc
     * @return
     */
    public Map<String, String> getSubmissionType(ProposalDevelopmentDocument pdDoc);
    /**
     * 
     * Fetch Rolodex record for the organization attached to the Proposal.
     * @param pdDoc
     * @return
     */
    public DepartmentalPerson getDepartmentalPerson(ProposalDevelopmentDocument pdDoc);
    /**
     * 
     * Returns questions for End of state review
     * @param pdDoc
     * @return
     */
    public Map<String, String> getEOStateReview(ProposalDevelopmentDocument pdDoc);
    /**
     * 
     * Returns federal id associated with the proposal
     * @param proposalDevelopmentDocument
     * @return
     */
    public String getFederalId(ProposalDevelopmentDocument proposalDevelopmentDocument);
    /**
     * 
     * This method fetches system constant parameters
     * @param parameter
     * @return
     */
    public String getParameterValue(String parameter);
    /**
     * 
     * Create country code data type
     * @param countryCode
     * @return
     */
    public CountryCodeDataType.Enum getCountryCodeDataType(String countryCode);
    /**
     * 
     * Create State code data type
     * @param stateName
     * @return
     */
    public StateCodeDataType.Enum getStateCodeDataType(String stateName);
    /**
     * 
     * Convert the date string with YYYY-MM-DD format to calendar object
     * @param date String
     * @return
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
     * @return cal(Calendar)
     */
    public Calendar convertDateToCalendar(Date date);
    
}
