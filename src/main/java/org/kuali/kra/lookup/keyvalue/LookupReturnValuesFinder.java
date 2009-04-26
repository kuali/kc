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
package org.kuali.kra.lookup.keyvalue;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.CustomAttributeService;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

/**
 * 
 * This class get a list of lookup fileds for the selected lookup class
 */
public class LookupReturnValuesFinder extends KeyValuesBase {
    private static final Log LOG = LogFactory.getLog(LookupReturnValuesFinder.class);

    public List<KeyLabelPair> getKeyValues() {
        // this will be called twice for each maintenancedocument page load
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        keyValues.add(new KeyLabelPair("", "select"));
        String lookupClass = (String) GlobalVariables.getUserSession().retrieveObject(Constants.LOOKUP_CLASS_NAME);

        List lookupReturnFields = (List) GlobalVariables.getUserSession().retrieveObject(Constants.LOOKUP_RETURN_FIELDS);
        try {
            if (lookupReturnFields != null) {
                GlobalVariables.getUserSession().removeObject(Constants.LOOKUP_RETURN_FIELDS);
                GlobalVariables.getUserSession().removeObject(Constants.LOOKUP_CLASS_NAME);
            }
            else {
                lookupReturnFields = KraServiceLocator.getService(CustomAttributeService.class).getLookupReturns(lookupClass);
                GlobalVariables.getUserSession().addObject(Constants.LOOKUP_RETURN_FIELDS, lookupReturnFields);
            }
        }
        catch (Exception e) {
            LOG.info(e.getMessage());
            // TODO
        }
        
        if (lookupReturnFields != null) {
            for (Object fieldName : lookupReturnFields) {
                keyValues.add(new KeyLabelPair(fieldName.toString(), KraServiceLocator.getService(DataDictionaryService.class).getAttributeLabel(lookupClass,fieldName.toString())));
            }
        }

        return keyValues;
    }


}
