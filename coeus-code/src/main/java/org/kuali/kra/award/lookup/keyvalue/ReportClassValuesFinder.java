/*
 * Copyright 2005-2013 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.award.lookup.keyvalue;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.paymentreports.ReportClass;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.KeyValuesService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 
 * This class is a values finder for <code>ReportClass</code> business object.
 */
public class ReportClassValuesFinder extends UifKeyValuesFinderBase {
    
    /**
     * Constructs the list of Report Classes using KeyValuesService.  
     * Each entry in the list is a &lt;key, value&gt; pair, where the "key" is the unique
     * report class code and the "value" is the textual description that is viewed
     * by a user.
     * 
     */
    @Override
    public List<KeyValue> getKeyValues() {
        Collection<ReportClass> reportClasses = (Collection<ReportClass>)getKeyValuesService().findAll(ReportClass.class);
        
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        
        for(ReportClass reportClass: reportClasses){
            keyValues.add(new ConcreteKeyValue(reportClass.getReportClassCode(), reportClass.getDescription()));
        }
        
        return keyValues;
    }
    
    protected KeyValuesService getKeyValuesService(){
        return (KeyValuesService) KcServiceLocator.getService("keyValuesService");
    }
   
}
