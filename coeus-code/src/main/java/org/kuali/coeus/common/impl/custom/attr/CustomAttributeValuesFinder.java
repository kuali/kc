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
package org.kuali.coeus.common.impl.custom.attr;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
 * This is for Custom Attribute document types.
 */
public class CustomAttributeValuesFinder extends UifKeyValuesFinderBase {
    private List<KeyValue> documentTypeParams;

    private static final String EQUAL_CHAR = "=";

    @Override
    public List<KeyValue> getKeyValues() {
        if (documentTypeParams == null) {
            Collection<String> validTypes = KcServiceLocator.getService(ParameterService.class).getParameterValuesAsString(Constants.KC_GENERIC_PARAMETER_NAMESPACE,
                    Constants.CUSTOM_ATTRIBUTE_DOCUMENT_DETAIL_TYPE_CODE, Constants.CUSTOM_ATTRIBUTE_DOCUMENT_PARAM_NAME);
            List<KeyValue> newList = new ArrayList<KeyValue>();
            newList.add(new ConcreteKeyValue("", "select"));
            for (String documentType : validTypes) {
                String[] params = documentType.split(EQUAL_CHAR);
                newList.add(new ConcreteKeyValue(params[0].replace(" ", "+"), params[1]));
            }
            documentTypeParams = newList;
        }
        return documentTypeParams;
    }

}
