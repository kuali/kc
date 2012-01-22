/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.award.paymentreports.closeout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;
import org.kuali.rice.krad.service.KeyValuesService;

/**
 * 
 * This class is a values finder for <code>ReportClass</code> business object.
 */
public class CloseoutReportTypeValuesFinder extends KeyValuesBase {
    
    private ParameterService parameterService;
    
    /**
     * Constructs the list of Report Classes using KeyValuesService.  
     * Each entry in the list is a &lt;key, value&gt; pair, where the "key" is the unique
     * report class code and the "value" is the textual description that is viewed
     * by a user.
     * 
     * @see org.kuali.core.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */    
    public List<KeyValue> getKeyValues() {
        Collection<CloseoutReportType> closeoutReportTypes = (Collection<CloseoutReportType>)getKeyValuesService().findAll(CloseoutReportType.class);
        
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        
        for(CloseoutReportType closeoutReportType: closeoutReportTypes){
            if (!StringUtils.equalsIgnoreCase(closeoutReportType.getCloseoutReportCode(), this.getParameterService().getParameterValueAsString(
                    AwardDocument.class, 
                    KeyConstants.CLOSE_OUT_REPORT_TYPE_USER_DEFINED))){
                keyValues.add(new ConcreteKeyValue(closeoutReportType.getCloseoutReportCode(), closeoutReportType.getDescription()));    
            }
        }
        
        return keyValues;
    }
    
    protected KeyValuesService getKeyValuesService(){
        return (KeyValuesService) KraServiceLocator.getService(KeyValuesService.class);
    }
    
    /**
     * Looks up and returns the ParameterService.
     * @return the parameter service. 
     */
    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KraServiceLocator.getService(ParameterService.class);        
        }
        return this.parameterService;
    }
   
}
