/*
 * Copyright 2006-2008 The Kuali Foundation
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

import java.util.Map;

import org.kuali.kra.award.home.Award;

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
