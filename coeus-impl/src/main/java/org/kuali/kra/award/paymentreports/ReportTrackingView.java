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
package org.kuali.kra.award.paymentreports;

import java.util.ArrayList;
import java.util.List;

/**
 * Report tracking view non-persisted BO. This BO is created via Spring injection of the
 * ReportTrackingSearchViews class. This allows easy configuration of the supported views via
 * Spring.
 */
public class ReportTrackingView extends org.kuali.rice.krad.bo.BusinessObjectBase {

    private String viewName;
    private List<String> groupByCols;
    private List<String> groupByDisplayCols;
    private List<String> detailCols;

    public ReportTrackingView() {
        groupByCols = new ArrayList<String>();
        groupByDisplayCols = new ArrayList<String>();
        detailCols = new ArrayList<String>();
    }
    
    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public List<String> getGroupByCols() {
        return groupByCols;
    }

    public void setGroupByCols(List<String> groupByCols) {
        this.groupByCols = groupByCols;
    }

    public List<String> getDetailCols() {
        return detailCols;
    }

    public void setDetailCols(List<String> detailCols) {
        this.detailCols = detailCols;
    }

    @Override
    public void refresh() {

    }

    public List<String> getGroupByDisplayCols() {
        return groupByDisplayCols;
    }

    public void setGroupByDisplayCols(List<String> groupByDisplayCols) {
        this.groupByDisplayCols = groupByDisplayCols;
    }    
    
}
