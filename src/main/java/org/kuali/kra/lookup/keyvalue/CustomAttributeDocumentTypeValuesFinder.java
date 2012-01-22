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
package org.kuali.kra.lookup.keyvalue;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;

/**
 * Get custom attribute document 'document type code' list from sys param
 * This class...
 */
public class CustomAttributeDocumentTypeValuesFinder extends KeyValuesBase {
    List<KeyValue> moduleCodes = null;

    /*
     * @see org.kuali.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @SuppressWarnings("unchecked")
    public List<KeyValue> getKeyValues() {

        List<KeyValue> labels = new ArrayList<KeyValue>();
        labels.add(new ConcreteKeyValue("", "select"));
        for (String documentType : KraServiceLocator.getService(ParameterService.class).getParameterValuesAsString(Constants.KC_GENERIC_PARAMETER_NAMESPACE,
                Constants.CUSTOM_ATTRIBUTE_DOCUMENT_DETAIL_TYPE_CODE, Constants.CUSTOM_ATTRIBUTE_DOCUMENT_PARAM_NAME)) {
            String[] params = documentType.split("=");
            labels.add(new ConcreteKeyValue(params[0], params[1]));
        }
        return labels;
    }


}
