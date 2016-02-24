/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
