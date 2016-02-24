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

import org.kuali.kra.award.home.Award;

import java.util.Map;

/**
 * 
 * This is the AwardReportsService interface.
 */
public interface AwardReportsService {
    
    /**
     * 
     * This method prepares the AwardReportTerm and related objects for the display of UI.
     * This should get called every time Payment, Reports and Terms page is loaded.
     * 
     * @param award
     * @return
     */
    Map<String, Object> initializeObjectsForReportsAndPayments(Award award);
    
    /**
     * 
     * This method gets called from the DWR script to populate to update frequency based on
     * Report Class and Type.
     * 
     * @param reportClassCode
     * @param reportCode
     * @return
     */
    String getFrequencyCodes(String reportClassCode, String reportCode);
    
    /**
     * 
     * This method gets called from the DWR script to populate Frequency Base based on
     * Frequency
     * 
     * @param frequencyCode
     * @return
     */
    String getFrequencyBaseCodes(String frequencyCode);    
}
