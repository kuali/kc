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

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.Map;

import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.generator.bo.DepartmentalPerson;
/*
 * TODO: More javaDoc comments needed
 * TODO: I'm not sure I undertstand the XXX.Enum return types (I don't have this source to see what's going on in there.)
 * TODO: public String getS2SSoapHost() throws IOException; : Should exception should be more generic?
 */
public interface S2SGeneratorUtilService {

    public Map<String, String> getSubmissionType(ProposalDevelopmentDocument pdDoc);
    
    public DepartmentalPerson getDepartmentalPerson(ProposalDevelopmentDocument pdDoc);
    
    public Map<String, String> getEOStateReview(ProposalDevelopmentDocument pdDoc);
    
    public String getFederalId(ProposalDevelopmentDocument proposalDevelopmentDocument);
    
    public String getParameterValue(String parameter);
    
    public CountryCodeDataType.Enum getCountryCodeDataType(String countryCode);
    
    public StateCodeDataType.Enum getStateCodeDataType(String stateName);
    
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
    
    public String getS2SSoapHost() throws IOException;
}
