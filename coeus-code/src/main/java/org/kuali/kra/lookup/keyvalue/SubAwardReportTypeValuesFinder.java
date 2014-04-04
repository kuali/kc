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
package org.kuali.kra.lookup.keyvalue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.subaward.bo.SubAwardReportType;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.KeyValuesService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

public class SubAwardReportTypeValuesFinder extends UifKeyValuesFinderBase {
    
    @Override
    public List<KeyValue> getKeyValues() {
        KeyValuesService keyValuesService = (KeyValuesService) KcServiceLocator.getService("keyValuesService");
        Collection SubAwardReportType = keyValuesService.findAllOrderBy(SubAwardReportType.class,"sortId",true);
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        
        for (Iterator iter = SubAwardReportType.iterator(); iter.hasNext();) {
            SubAwardReportType reportTypes = (SubAwardReportType) iter.next();
            keyValues.add(new ConcreteKeyValue(reportTypes.getSubAwardReportTypeCode().toString(), reportTypes.getDescription()));                            
        }       
        return keyValues;
    }

}
