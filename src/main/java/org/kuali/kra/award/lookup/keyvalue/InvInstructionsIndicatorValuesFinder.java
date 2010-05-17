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
package org.kuali.kra.award.lookup.keyvalue;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.award.home.InvInstructionsIndicatorConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.core.util.KeyLabelPair;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.service.KualiConfigurationService;

/**
 * Values Finder for Invoice Instructions Indicator Values.
 * 
 */
public class InvInstructionsIndicatorValuesFinder extends KeyValuesBase {
    
    private transient KualiConfigurationService configurationService;
    private List<KeyLabelPair> labels;
    private static final String PROPERTY_PREFIX = "label.award.validBasisMethodPayment.invInstructionsIndicator.";

    public List<KeyLabelPair> getKeyValues() {
        if( labels!=null ) return labels;
        labels = new ArrayList<KeyLabelPair>();
        
        for( InvInstructionsIndicatorConstants inv : InvInstructionsIndicatorConstants.values() ) 
            labels.add(new KeyLabelPair( inv.getCode(),  getConfigurationService().getPropertyString(PROPERTY_PREFIX+inv.getCode())));
        return labels;
    }
    
    private KualiConfigurationService getConfigurationService() {
        if( configurationService == null ) {
            configurationService = KraServiceLocator.getService(KualiConfigurationService.class);
        }
        return configurationService;
    }
    
}
