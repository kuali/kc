/*
 * Copyright 2007 The Kuali Foundation.
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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.core.dao.BusinessObjectDao;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.web.ui.KeyLabelPair;

/**
 * This class...
 */
public class KeyValueFinderServiceImpl implements KeyValueFinderService {
    private BusinessObjectService businessObjectService;
    private static final Log LOG = LogFactory.getLog(KeyValueFinderServiceImpl.class);
    /**
     * @see org.kuali.kra.lookup.keyvalue.KeyValueFinderService#getKeyValuesFor(java.lang.Class)
     */
    public List<KeyLabelPair> getKeyValues(Class keyValClass,String codePropName,String valPropName) {
        Collection keyVals = businessObjectService.findAll(keyValClass);
        List<KeyLabelPair> keyValueList = new ArrayList<KeyLabelPair>(keyVals.size());
        keyValueList.add(new KeyLabelPair("", "select:"));
        for (Iterator iterator = keyVals.iterator(); iterator.hasNext();) {
            Object keyValObj = iterator.next();
            Method getCodeMeth;
            try {
                getCodeMeth = keyValObj.getClass().getMethod("get"+StringUtils.capitalize(codePropName), null);
                Method getValMeth = keyValObj.getClass().getMethod("get"+StringUtils.capitalize(valPropName), null);
                String code = (String)getCodeMeth.invoke(keyValObj, null);
                String value = (String)getValMeth.invoke(keyValObj, null);
                keyValueList.add(new KeyLabelPair(code, value));

            }
            catch (SecurityException e) {
                LOG.debug(e.getMessage(), e);
                LOG.error(e.getMessage());
            }
            catch (NoSuchMethodException e) {
                LOG.debug(e.getMessage(), e);
                LOG.error(e.getMessage());
            }
            catch (IllegalArgumentException e) {
                LOG.debug(e.getMessage(), e);
                LOG.error(e.getMessage());
            }
            catch (IllegalAccessException e) {
                LOG.debug(e.getMessage(), e);
                LOG.error(e.getMessage());
            }
            catch (InvocationTargetException e) {
                LOG.debug(e.getMessage(), e);
                LOG.error(e.getMessage());
            }
        }
        return keyValueList;
    }

    public List<KeyLabelPair> getKeyValues(Class keyValClass, String codePropName, String valPropName, String groupPropName,
            String groupValue) {
        Collection keyVals = businessObjectService.findAll(keyValClass);
        List<KeyLabelPair> keyValueList = new ArrayList<KeyLabelPair>(keyVals.size());
        keyValueList.add(new KeyLabelPair("", "select:"));
        for (Iterator iterator = keyVals.iterator(); iterator.hasNext();) {
            Object keyValObj = iterator.next();
            Method getCodeMeth;
            try {
                getCodeMeth = keyValObj.getClass().getMethod("get"+StringUtils.capitalize(codePropName), null);
                Method getValMeth = keyValObj.getClass().getMethod("get"+StringUtils.capitalize(valPropName), null);
                Method getGroupMeth = keyValObj.getClass().getMethod("get"+StringUtils.capitalize(groupPropName), null);
                String code = (String)getCodeMeth.invoke(keyValObj, null);
                String value = (String)getValMeth.invoke(keyValObj, null);
                String group = (String)getGroupMeth.invoke(keyValObj, null);
                if (StringUtils.isNotBlank(group) && group.equals(groupValue)) {
                    keyValueList.add(new KeyLabelPair(code, value));
                }

            }
            catch (SecurityException e) {
                LOG.debug(e.getMessage(), e);
                LOG.error(e.getMessage());
            }
            catch (NoSuchMethodException e) {
                LOG.debug(e.getMessage(), e);
                LOG.error(e.getMessage());
            }
            catch (IllegalArgumentException e) {
                LOG.debug(e.getMessage(), e);
                LOG.error(e.getMessage());
            }
            catch (IllegalAccessException e) {
                LOG.debug(e.getMessage(), e);
                LOG.error(e.getMessage());
            }
            catch (InvocationTargetException e) {
                LOG.debug(e.getMessage(), e);
                LOG.error(e.getMessage());
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
