/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.impl.rpt.cust;

import org.kuali.coeus.common.impl.rpt.BirtReportService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.List;

public class CustomReportDetailsValueFinder extends UifKeyValuesFinderBase {

    private static final long serialVersionUID = 1L;
    private BirtReportService birtReportService;
    private BirtReportService getBirtReportService() {
        if (birtReportService == null) {
            birtReportService = KcServiceLocator.getService(BirtReportService.class);
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
