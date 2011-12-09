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
package org.kuali.kra.award.web.struts.action;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.award.paymentreports.ReportTrackingView;
import org.kuali.kra.award.paymentreports.awardreports.reporting.ReportTracking;
import org.kuali.kra.award.paymentreports.awardreports.reporting.service.ReportTrackingSearchViews;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.web.struts.form.LookupForm;

/**
 * Report Tracking Lookup Form.
 */
public class ReportTrackingLookupForm extends LookupForm {
    
    private static final long serialVersionUID = -9223223488568942221L;
    
    private List<String> groupedByFields;
    private List<String> groupedByDisplayFields;
    private List<String> detailFields;
    private List<ReportTracking> groupedByResults;
    private List<ReportTracking> detailResults;
    private ReportTrackingView currentView;
    private int currentViewIndex;
    private Integer groupByResultIndex;
    private List<String> customGroupByFields;
    private List<String> customDetailFields;
    private ReportTrackingSearchViews reportTrackingViews;
    private boolean viewRawResults;
    private String moveField;
    private Integer newColumnIndex;
    
    public ReportTrackingLookupForm() {
        init();
    }
    
    public void init() {
        currentViewIndex = 0;
        setCurrentView();
        resetCustomFields();
    }
    
    /**
     * Reset the custom fields to the defaults defined via Spring(ReportTrackingSearchViews).
     */
    public void resetCustomFields() {
        ReportTrackingView customView = getReportTrackingViews().getCustomView();
        setCustomGroupByFields(new ArrayList<String>(customView.getGroupByDisplayCols()));
        setCustomDetailFields(new ArrayList<String>(customView.getDetailCols()));
    }

    public List<String> getGroupedByFields() {
        return groupedByFields;
    }

    public void setGroupedByFields(List<String> groupedByFields) {
        this.groupedByFields = groupedByFields;
    }

    public ReportTrackingView getCurrentView() {
        return currentView;
    }
    
    /**
     * Using the current view index, set the current view and the groupedByFields, 
     * groupedByDisplayFields, and detailFields list of columns. If the view is a 
     * custom view, use the customGroupByFields and customDetailFields instead.
     */
    public void setCurrentView() {
        currentView = getReportTrackingViews().getReportTrackingViews().get(currentViewIndex);
        if (currentViewIndex == getReportTrackingViews().getCustomViewIndex()) {
            groupedByFields = new ArrayList<String>(getReportTrackingViews().convertToGroupByCols(getCustomGroupByFields()));
            groupedByDisplayFields = new ArrayList<String>(getCustomGroupByFields());
            detailFields = new ArrayList<String>(getCustomDetailFields());
        } else {
            groupedByFields = new ArrayList<String>(currentView.getGroupByCols());
            groupedByDisplayFields = new ArrayList<String>(currentView.getGroupByDisplayCols());
            detailFields = new ArrayList<String>(currentView.getDetailCols());
        }        
    }
    
    public int getCurrentViewIndex() {
        return getReportTrackingViews().getReportTrackingViews().indexOf(currentView);
    }
    
    public void setCurrentViewIndex(int index) {
        currentViewIndex = index;
    }

    public List<ReportTracking> getGroupedByResults() {
        return groupedByResults;
    }

    public void setGroupedByResults(List<ReportTracking> groupedByResults) {
        this.groupedByResults = groupedByResults;
    }

    public List<String> getGroupedByDisplayFields() {
        return groupedByDisplayFields;
    }

    public void setGroupedByDisplayFields(List<String> groupedByDisplayFields) {
        this.groupedByDisplayFields = groupedByDisplayFields;
    }

    public List<String> getDetailFields() {
        return detailFields;
    }

    public void setDetailFields(List<String> detailFields) {
        this.detailFields = detailFields;
    }

    public List<ReportTracking> getDetailResults() {
        return detailResults;
    }

    public void setDetailResults(List<ReportTracking> detailResults) {
        this.detailResults = detailResults;
    }

    public Integer getGroupByResultIndex() {
        return groupByResultIndex;
    }

    public void setGroupByResultIndex(Integer groupByResultIndex) {
        this.groupByResultIndex = groupByResultIndex;
    }

    public void setReportTrackingViews(ReportTrackingSearchViews reportTrackingViews) {
        this.reportTrackingViews = reportTrackingViews;
    }
    
    public ReportTrackingSearchViews getReportTrackingViews() {
        if (reportTrackingViews == null) {
            reportTrackingViews = KraServiceLocator.getService(ReportTrackingSearchViews.class);
        }
        return reportTrackingViews;
    }

    public List<String> getCustomGroupByFields() {
        return customGroupByFields;
    }

    public void setCustomGroupByFields(List<String> customGroupByFields) {
        this.customGroupByFields = customGroupByFields;
    }

    public List<String> getCustomDetailFields() {
        return customDetailFields;
    }

    public void setCustomDetailFields(List<String> customDetailFields) {
        this.customDetailFields = customDetailFields;
    }

    public boolean isViewRawResults() {
        return viewRawResults;
    }

    public void setViewRawResults(boolean viewRawResults) {
        this.viewRawResults = viewRawResults;
    }

    public Integer getNewColumnIndex() {
        return newColumnIndex;
    }

    public void setNewColumnIndex(Integer newColumnIndex) {
        this.newColumnIndex = newColumnIndex;
    }

    public String getMoveField() {
        return moveField;
    }

    public void setMoveField(String moveField) {
        this.moveField = moveField;
    }

}
