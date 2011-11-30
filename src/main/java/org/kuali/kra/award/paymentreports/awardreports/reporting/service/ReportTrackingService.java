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
import org.kuali.kra.award.paymentreports.awardreports.reporting.ReportTrackingBean;

/**
 * 
 * This class manages the services needed for Report Tracking.
 */
public interface ReportTrackingService {
    
    /**
     * 
     * This method generates the reports for all the award report items in the award. Use the forceReportRegeneration
     * to over ride the autoRegenerateReports call.
     * @param award
     * @param forceReportRegeneration
     * @throws ParseException 
     */
    void generateReportTrackingAndSave(Award award, boolean forceReportRegeneration) throws ParseException;
    
    /**
     * 
     * This method simply refreshes the reporting tracking objects, no delete, no save.
     * @param award
     * @throws ParseException 
     */
    void refreshReportTracking(Award award) throws ParseException;
    
    /**
     * 
     * This method gets all the saved ReportTracking objects for a given AwardReportTerm.
     * @param awardTerm
     * @return
     */
    List<ReportTracking> getReportTacking(AwardReportTerm awardTerm);
    
    /**
     * 
     * This method returns all the report tracking records asociated with an award.
     * @param award
     * @return
     */
    List<ReportTracking> getReportTacking(Award award);
    
    /**
     * 
     * This method returns true if the award is a root award, otherwise returns false.
     * @param award
     * @return
     */
    boolean autoRegenerateReports(Award award);
    
    /**
     * 
     * This method updates the MultiEditSelected attributes of every ReportTracking object in the list to whatever the
     * selectedValue value is.
     * @param reportTrackingListing
     * @param selectedValue
     */
    void setReportTrackingListSelected(List<ReportTracking> reportTrackingListing, boolean selectedValue);
    
    /**
     * 
     * This method updates the value of every object in the reportTrackingListing list that has a true value for MultiEditSelected
     * for each attribture that is populated in the report tracking bean.
     * @param reportTrackingListing
     * @param reportTrackingBean
     */
    void updateMultipleReportTrackingRecords(List<ReportTracking> reportTrackingListing, ReportTrackingBean reportTrackingBean);
    
    /**
     * 
     * This method compares the passed in award to the DB version of the award and checks to see if any fields
     * have changed that will cause changes to the report tracking records.  If this message returns true, the 
     * caller of this function should display a warning message to the user about this change.
     * @param award
     * @return
     */
    boolean shouldAlertReportTrackingDetailChange(Award award);
}