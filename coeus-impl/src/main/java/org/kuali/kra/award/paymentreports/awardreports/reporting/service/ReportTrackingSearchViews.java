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

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.award.paymentreports.ReportTrackingView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
