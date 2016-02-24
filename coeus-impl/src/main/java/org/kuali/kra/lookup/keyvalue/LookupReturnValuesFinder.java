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
