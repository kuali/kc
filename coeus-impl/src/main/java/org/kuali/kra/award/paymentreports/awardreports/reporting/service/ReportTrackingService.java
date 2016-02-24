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
package org.kuali.kra.award.paymentreports.awardreports.reporting.service;

import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.kra.award.paymentreports.awardreports.reporting.ReportTracking;
import org.kuali.kra.award.paymentreports.awardreports.reporting.ReportTrackingBean;

import java.text.ParseException;
import java.util.List;

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
