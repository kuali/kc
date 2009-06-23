/*
 * Copyright 2006-2008 The Kuali Foundation
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
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.service.KeyValuesService;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

/**
 * 
 * This class is a values finder for <code>ReportClass</code> business object.
 */
public class CloseoutReportTypeValuesFinder extends KeyValuesBase {
    
    /**
     * Constructs the list of Report Classes using KeyValuesService.  
     * Each entry in the list is a &lt;key, value&gt; pair, where the "key" is the unique
     * report class code and the "value" is the textual description that is viewed
     * by a user.
     * 
     * @see org.kuali.core.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */    
    public List<KeyLabelPair> getKeyValues() {
        Collection<CloseoutReportType> closeoutReportTypes = (Collection<CloseoutReportType>)getKeyValuesService().findAll(CloseoutReportType.class);
        
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        
        for(CloseoutReportType closeoutReportType: closeoutReportTypes){
            if (!StringUtils.equalsIgnoreCase(closeoutReportType.getCloseoutReportCode(), getKualiConfigurationService().getParameter(
                    Constants.PARAMETER_MODULE_AWARD, Constants.PARAMETER_COMPONENT_DOCUMENT, 
                    KeyConstants.CLOSE_OUT_REPORT_TYPE_USER_DEFINED).getParameterValue())){
                keyValues.add(new KeyLabelPair(closeoutReportType.getCloseoutReportCode(), closeoutReportType.getDescription()));    
            }
        }
        
        return keyValues;
    }
    
    protected KeyValuesService getKeyValuesService(){
        return (KeyValuesService) KraServiceLocator.getService(KeyValuesService.class);
    }
    
    protected KualiConfigurationService getKualiConfigurationService(){
        return (KualiConfigurationService) KraServiceLocator.getService(KualiConfigurationService.class);
    }
   
}
