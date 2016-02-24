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
package org.kuali.kra.award.web.struts.action;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.paymentreports.ReportTrackingView;
import org.kuali.kra.award.paymentreports.awardreports.reporting.ReportTracking;
import org.kuali.kra.award.paymentreports.awardreports.reporting.service.ReportTrackingSearchViews;
import org.kuali.rice.kns.web.struts.form.LookupForm;

import java.util.ArrayList;
import java.util.List;

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
    
    /**
     * The Ajax details contain an open award button and as we don't know all the award numbers
     * beforehand we have to check for the openAwardReports link here.
     * @see org.kuali.rice.kns.web.struts.form.pojo.PojoFormBase#isPropertyEditable(java.lang.String)
     */
    @Override
    public boolean isPropertyEditable(String propertyName) {
        if (propertyName.startsWith("methodToCall.openAwardReports.awardNumber")) {
            return true;
        } else {
            return super.isPropertyEditable(propertyName);
        }
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
            reportTrackingViews = KcServiceLocator.getService(ReportTrackingSearchViews.class);
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
