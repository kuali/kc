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
package org.kuali.coeus.sys.impl.keyvalue;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.keyvalue.KeyValueFinderService;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@Component("keyValueFinderService")
public class KeyValueFinderServiceImpl implements KeyValueFinderService {

    private static final Log LOG = LogFactory.getLog(KeyValueFinderServiceImpl.class);

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Override
    public List<KeyValue> getKeyValues(Class<? extends BusinessObject> keyValClass,String codePropName,String valPropName) {
        Collection keyVals = businessObjectService.findAll(keyValClass);
        List<KeyValue> keyValueList = new ArrayList<KeyValue>(keyVals.size());
        keyValueList.add(new ConcreteKeyValue("", "select"));
        for (Iterator iterator = keyVals.iterator(); iterator.hasNext();) {
            Object keyValObj = iterator.next();
            Method getCodeMeth;
            try {
                getCodeMeth = keyValObj.getClass().getMethod("get"+StringUtils.capitalize(codePropName), null);
                Method getValMeth = keyValObj.getClass().getMethod("get"+StringUtils.capitalize(valPropName), null);
                Object code = getCodeMeth.invoke(keyValObj, null);
                Object value = getValMeth.invoke(keyValObj, null);
                if(code!=null && value!=null){
                    keyValueList.add(new ConcreteKeyValue(code.toString(), value.toString()));
                }
            }
            catch (SecurityException|NoSuchMethodException|IllegalArgumentException|IllegalAccessException|InvocationTargetException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return keyValueList;
    }

    @Override
    public List<KeyValue> getKeyValues(Class<? extends BusinessObject> keyValClass, String codePropName, String valPropName, Map<String, ?> queryMap) {
        
        Collection keyVals = businessObjectService.findMatching(keyValClass,queryMap);
        List<KeyValue> keyValueList = new ArrayList<KeyValue>(keyVals.size());
        keyValueList.add(new ConcreteKeyValue("", "select:"));
        for (Iterator iterator = keyVals.iterator(); iterator.hasNext();) {
            Object keyValObj = iterator.next();
            Method getCodeMeth;
            try {
                getCodeMeth = keyValObj.getClass().getMethod("get"+StringUtils.capitalize(codePropName), null);
                Method getValMeth = keyValObj.getClass().getMethod("get"+StringUtils.capitalize(valPropName), null);
                String code = (String)getCodeMeth.invoke(keyValObj, null);
                String value = (String)getValMeth.invoke(keyValObj, null);
                keyValueList.add(new ConcreteKeyValue(code, value));
            } catch (SecurityException|NoSuchMethodException|IllegalArgumentException|IllegalAccessException|InvocationTargetException e) {
                    LOG.error(e.getMessage(), e);
            }
        }
        return keyValueList;
    }

    /**
     * Gets the businessObjectService attribute.
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * Sets the businessObjectService attribute value.
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}
