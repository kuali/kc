/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.core.lookup.keyvalues.KeyValuesBase;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.web.struts.form.LookupForm;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.CustomAttributeService;

public class CustomAttributeLookupReturnValuesFinder extends KeyValuesBase {
    private static final Log LOG = LogFactory.getLog(CustomAttributeLookupReturnValuesFinder.class);

    public List<KeyLabelPair> getKeyValues() {
        // this will be called twice for each maintenancedocument page load
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        keyValues.add(new KeyLabelPair("", "select:"));
        String lookupClass = (String) GlobalVariables.getUserSession().retrieveObject("lookupClassName");

        List lookupReturnFields = null;
        try {
            if (StringUtils.isNotBlank(lookupClass)) {
                lookupReturnFields = KraServiceLocator.getService(CustomAttributeService.class).getLookupReturns(lookupClass);
                GlobalVariables.getUserSession().addObject("lookupReturnFields", lookupReturnFields);
                GlobalVariables.getUserSession().removeObject("lookupClassName");
            }
            else {
                lookupReturnFields = (List) GlobalVariables.getUserSession().retrieveObject("lookupReturnFields");
                GlobalVariables.getUserSession().removeObject("lookupReturnFields");
            }
        }
        catch (Exception e) {
            LOG.info(e.getMessage());
            // TODO
        }

        if (lookupReturnFields != null) {
            for (Object fieldName : lookupReturnFields) {
                keyValues.add(new KeyLabelPair(fieldName.toString(), fieldName.toString()));
            }
        }

        return keyValues;
    }


}
