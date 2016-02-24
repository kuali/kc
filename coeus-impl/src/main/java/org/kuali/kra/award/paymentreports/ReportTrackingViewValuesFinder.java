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

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.paymentreports.awardreports.reporting.service.ReportTrackingSearchViews;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.List;


/**
 * ReportTracking views values finder class.
 */
public class ReportTrackingViewValuesFinder extends UifKeyValuesFinderBase {
    
    private ReportTrackingSearchViews reportTrackingSearchViews;
   
    /**
     * Get the report regeneration types and use the name as the key in the label.
     */
    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> labels = new ArrayList<KeyValue>();
        for (int i = 0; i < getReportTrackingSearchViews().getReportTrackingViews().size(); i++) {
            labels.add(new ConcreteKeyValue(i+"", getReportTrackingSearchViews().getReportTrackingViews().get(i).getViewName()));
        }
        return labels;
    }

    protected ReportTrackingSearchViews getReportTrackingSearchViews() {
        if (reportTrackingSearchViews == null) {
            reportTrackingSearchViews = KcServiceLocator.getService(ReportTrackingSearchViews.class);
        }
        return reportTrackingSearchViews;
    }

    public void setReportTrackingSearchViews(ReportTrackingSearchViews reportTrackingSearchViews) {
        this.reportTrackingSearchViews = reportTrackingSearchViews;
    }
}
