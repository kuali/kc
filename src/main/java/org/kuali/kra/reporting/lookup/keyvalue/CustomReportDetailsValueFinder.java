/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.reporting.lookup.keyvalue;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.reporting.bo.CustReportDetails;
import org.kuali.kra.reporting.service.BirtReportService;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;

public class CustomReportDetailsValueFinder extends KeyValuesBase {
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    private BirtReportService birtReportService;
    private BirtReportService getBirtReportService() {
        if (birtReportService == null) {
            birtReportService = KraServiceLocator.getService(BirtReportService.class);
        }
        return birtReportService;
    }

    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyLabels = new ArrayList<KeyValue>();
        keyLabels.add(new ConcreteKeyValue("0","select"));
        List<CustReportDetails> results = getBirtReportService().getReports();
        for (CustReportDetails custReportDetails : results) {
            keyLabels.add(new ConcreteKeyValue(custReportDetails.getReportId().toString() , custReportDetails.getReportLabel()));
        }            
        return keyLabels;
    }

}
