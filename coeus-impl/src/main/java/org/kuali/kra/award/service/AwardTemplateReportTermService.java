/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
     */
    String getReportTypeForAjaxCall(String reportClassCode);
    
    /**
     * 
     * This method is to get the report types of the ValidClassReportFrequency based on the reportClasscode.
     */
    Collection<String> getReportTypesUsingReportClassCode(String reportClassCode);
    
    /**
     * 
     * This method is to get the frequency based on reportCode and converted to string separated by ",".
     */
    String getFrequencyForAjaxCall(String reportCode, String reportClass);

    /**
     * 
     * This method is to get the frequency based on the reportCode.
     */
    Collection<String> getFrequencyUsingReportCodeAndClass(String reportCode, String reportClass);
    /**
     * 
     * This method is to get frequency bases based on frequencyCode and converted to string separated by ",".
     */
    String getFrequencyBaseForAjaxCall(String frequencyCode);
    /**
     * 
     * This method is to get the frequency bases based on the frequencyCode.
     */
    Collection<String> getFrequencyBaseUsingFrequencyCode(String frequencyCode);

}
