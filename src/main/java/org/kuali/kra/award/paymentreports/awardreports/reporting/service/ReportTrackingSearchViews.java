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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.paymentreports.ReportTrackingView;

/**
 * Used as a container for Spring configuration related to Report Tracking Views.
 * Views are used to group and sort report tracking search results.
 */
public class ReportTrackingSearchViews {
    
    private List<ReportTrackingView> reportTrackingViews;
    private String piViewName;
    private String customViewName;
    private int piViewIndex;
    private int customViewIndex;
    private List<String> allFields;
    private Map<String, String> displayViewConversions;
    
    /**
     * 
     * Constructs a ReportTrackingSearchViews.java
     */
    public ReportTrackingSearchViews() {
        reportTrackingViews = new ArrayList<ReportTrackingView>();
        allFields = new ArrayList<String>();
        displayViewConversions = new HashMap<String, String>();
        displayViewConversions.put("reportClass.description", "reportClassCode");
        displayViewConversions.put("report.description", "reportCode");
        displayViewConversions.put("frequency.description", "frequencyCode");
        displayViewConversions.put("frequencyBase.description", "frequencyBaseCode");
        displayViewConversions.put("distribution.description", "ospDistributionCode");
        displayViewConversions.put("reportStatus.description", "statusCode");
        displayViewConversions.put("sponsor.sponsorName", "sponsorCode");
        displayViewConversions.put("leadUnit.unitName", "leadUnitNumber");
        
    }
    
    /**
     * Should be called by spring post post init. It will populate the groupByCols on each view
     * using the convertToGroupByCols method and save the index of the piView and customView
     * for use by jsps and code.
     */
    public void init() {
        for (ReportTrackingView view : reportTrackingViews) {
            view.setGroupByCols(convertToGroupByCols(view.getGroupByDisplayCols()));
            if (StringUtils.equals(view.getViewName(), piViewName)) {
                piViewIndex = reportTrackingViews.indexOf(view);
            } else if (StringUtils.equals(view.getViewName(), customViewName)) {
                customViewIndex = reportTrackingViews.indexOf(view);
            }
        }
    }
    
    /**
     * Take the list of columns and convert them to a list appropriate to use as groupBy columns in the SQL.
     * This will use the displayViewConversions map mostly to convert description fields to the related code fields.
     * @param cols
     * @return
     */
    public List<String> convertToGroupByCols(List<String> cols) {
        List<String> result = new ArrayList<String>();
        for (String groupByCol : cols) {
            if (displayViewConversions.containsKey(groupByCol)) {
                result.add(displayViewConversions.get(groupByCol));
            } else {
                result.add(groupByCol);
            }
        }
        return result;
    }
    
    public List<ReportTrackingView> getReportTrackingViews() {
        return reportTrackingViews;
    }
    public void setReportTrackingViews(List<ReportTrackingView> reportTrackingViews) {
        this.reportTrackingViews = reportTrackingViews;
    }
    public Map<String, String> getDisplayViewConversions() {
        return displayViewConversions;
    }
    public void setDisplayViewConversions(Map<String, String> displayViewConversions) {
        this.displayViewConversions = displayViewConversions;
    }

    public List<String> getAllFields() {
        return allFields;
    }

    public void setAllFields(List<String> allFields) {
        this.allFields = allFields;
    }

    public String getPiViewName() {
        return piViewName;
    }

    public void setPiViewName(String piViewName) {
        this.piViewName = piViewName;
    }

    public String getCustomViewName() {
        return customViewName;
    }

    public void setCustomViewName(String customViewName) {
        this.customViewName = customViewName;
    }

    public int getPiViewIndex() {
        return piViewIndex;
    }

    public void setPiViewIndex(int piViewIndex) {
        this.piViewIndex = piViewIndex;
    }

    public int getCustomViewIndex() {
        return customViewIndex;
    }

    public void setCustomViewIndex(int customViewIndex) {
        this.customViewIndex = customViewIndex;
    }
    
    public ReportTrackingView getCustomView() {
        return reportTrackingViews.get(customViewIndex);
    }
}
