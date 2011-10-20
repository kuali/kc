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
package org.kuali.kra.award.paymentreports.awardreports.reporting.service;

import java.text.ParseException;
import java.util.List;

import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.kra.award.paymentreports.awardreports.reporting.ReportTracking;

/**
 * 
 * This class manages the services needed for Report Tracking.
 */
public interface ReportTrackingService {
    
    /**
     * 
     * This method generates the reports for all the award report items in the award.
     * @param award
     * @throws ParseException 
     */
    void generateReportTrackingAndSave(Award award) throws ParseException;
    
    /**
     * 
     * This method gets all the saved ReportTracking objects for a given AwardReportTerm.
     * @param awardTerm
     * @return
     */
    List<ReportTracking> getReportTacking(AwardReportTerm awardTerm);
}
