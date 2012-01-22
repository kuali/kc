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
package org.kuali.kra.award.paymentreports;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.rice.krad.bo.BusinessObjectBase;

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
