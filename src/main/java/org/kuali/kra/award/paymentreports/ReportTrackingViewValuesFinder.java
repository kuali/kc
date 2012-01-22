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
import java.util.List;

import org.kuali.kra.award.paymentreports.awardreports.reporting.service.ReportTrackingSearchViews;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.lookup.keyvalue.ExtendedPersistableBusinessObjectValuesFinder;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;


/**
 * ReportTracking views values finder class.
 */
public class ReportTrackingViewValuesFinder extends ExtendedPersistableBusinessObjectValuesFinder {
    
    private ReportTrackingSearchViews reportTrackingSearchViews;
   
    /**
     * Get the report regeneration types and use the name as the key in the label.
     * @see org.kuali.kra.lookup.keyvalue.ExtendedPersistableBusinessObjectValuesFinder#getKeyValues()
     */
    public List<KeyValue> getKeyValues() {
        List<KeyValue> labels = new ArrayList<KeyValue>();
        for (int i = 0; i < getReportTrackingSearchViews().getReportTrackingViews().size(); i++) {
            labels.add(new ConcreteKeyValue(i+"", getReportTrackingSearchViews().getReportTrackingViews().get(i).getViewName()));
        }
        return labels;
    }

    protected ReportTrackingSearchViews getReportTrackingSearchViews() {
        if (reportTrackingSearchViews == null) {
            reportTrackingSearchViews = KraServiceLocator.getService(ReportTrackingSearchViews.class);
        }
        return reportTrackingSearchViews;
    }

    public void setReportTrackingSearchViews(ReportTrackingSearchViews reportTrackingSearchViews) {
        this.reportTrackingSearchViews = reportTrackingSearchViews;
    }
}
