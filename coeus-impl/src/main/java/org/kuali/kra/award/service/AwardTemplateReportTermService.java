/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.award.service;

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
