/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.service;

import java.util.Collection;

public interface AwardTemplateReportTermService {

    /**
     * 
     * This method is to get the report types of the ValidClassReportFrequency based on the reportClasscode and converted to string separated by ",".
     * @param reportClassCode
     * @return String
     * @throws Exception
     */
    public String getReportTypeForAjaxCall(String reportClassCode) throws Exception;
    
    /**
     * 
     * This method is to get the report types of the ValidClassReportFrequency based on the reportClasscode
     * @param reportClassCode
     * @return
     * @throws Exception
     */
    public Collection getReportTypesUsingReportClassCode(String reportClassCode) throws Exception ;
    
    /**
     * 
     * This method is to get the frequency based on reportCode and converted to string separated by ",".
     * @param reportCode
     * @param reportClass
     * @return String
     * @throws Exception
     */
    public String getFrequencyForAjaxCall(String reportCode, String reportClass) throws Exception;

    /**
     * 
     * This method is to get the frequency based on the reportCode
     * @param reportClassCode
     * @param reportClass
     * @return
     * @throws Exception
     */
    public Collection getFrequencyUsingReportCodeAndClass(String reportCode, String reportClass) throws Exception ;
    /**
     * 
     * This method is to get frequency bases based on frequencyCode and converted to string separated by ",".
     * @param frequencyCode
     * @return String
     * @throws Exception
     */
    public String getFrequencyBaseForAjaxCall(String frequencyCode) throws Exception;
    /**
     * 
     * This method is to get the frequency bases based on the frequencyCode
     * @param frequencyCode
     * @return
     * @throws Exception
     */
    public Collection getFrequencyBaseUsingFrequencyCode(String frequencyCode) throws Exception ;

}
