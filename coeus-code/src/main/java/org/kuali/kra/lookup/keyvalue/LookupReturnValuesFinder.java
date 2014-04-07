/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.framework.custom.arg.ArgValueLookup;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class get a list of lookup fileds for the selected lookup class
 */
public class LookupReturnValuesFinder extends UifKeyValuesFinderBase {
    private static final Log LOG = LogFactory.getLog(LookupReturnValuesFinder.class);
    private static final String ARGVALUELOOKUPE_CLASS = ArgValueLookup.class.getName();

    @Override
    public List<KeyValue> getKeyValues() {
        // this will be called twice for each maintenancedocument page load
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue("", "select"));
        String lookupClass = (String) GlobalVariables.getUserSession().retrieveObject(Constants.LOOKUP_CLASS_NAME);

        List lookupReturnFields = (List) GlobalVariables.getUserSession().retrieveObject(Constants.LOOKUP_RETURN_FIELDS);
        try {
            if (lookupReturnFields != null) {
                GlobalVariables.getUserSession().removeObject(Constants.LOOKUP_RETURN_FIELDS);
                GlobalVariables.getUserSession().removeObject(Constants.LOOKUP_CLASS_NAME);
            }
            else {
                if (lookupClass != null) {
                    lookupReturnFields = KcServiceLocator.getService(CustomAttributeService.class).getLookupReturns(lookupClass);
                    GlobalVariables.getUserSession().addObject(Constants.LOOKUP_RETURN_FIELDS, lookupReturnFields);
                }
            }
        }
        catch (Exception e) {
            LOG.info(e.getMessage(), e);
        }
        
        if (lookupReturnFields != null) {
            for (Object fieldName : lookupReturnFields) {
                keyValues.add(new ConcreteKeyValue(fieldName.toString(), (ARGVALUELOOKUPE_CLASS.equals(lookupClass) ? fieldName.toString() : KcServiceLocator.getService(DataDictionaryService.class).getAttributeLabel(lookupClass,fieldName.toString()))));
            }
        }

        return keyValues;
    }


}
