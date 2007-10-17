/*
 * Copyright 2005-2006 The Kuali Foundation.
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
package org.kuali.kra.infrastructure;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.lang.StringUtils;
import org.kuali.core.bo.Parameter;
import org.kuali.core.exceptions.ApplicationParameterException;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.util.JstlPropertyHolder;


/**
 * Access financial system parameters like they were a map
 *
 * @author $Author: gmcgrego $
 * @version $Revision: 1.3 $
 */
public class SystemParametersPropertyHolder extends JstlPropertyHolder {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(SystemParametersPropertyHolder.class);
    //private String scriptName;
    
    /**
     * Build SystemParametersPropertyHolder from a script name
     */
//    public SystemParametersPropertyHolder(String name) {
//        setScriptName(name);
//    }

    /**
     * Set the script name used to retrieve System Parameters
     *
     * @param s
     */
//    public void setScriptName(String s) {
//        scriptName = s;
//    }
    
    /**
     * The script name used to retrieve System Parameters
     *
     * @return String
     */
//    public String getScriptName() {
//        return scriptName;
//    }

    // delegated methods
    /**
     * @see org.kuali.core.util.properties.PropertyTree#get(java.lang.Object)
     */
    public Object get(Object key) {
        try { // Return a map of the parameter or don't return at all.
            return new PropertyUtilsBean().describe(getParameter("KRA-PD", "D", key.toString()));
        }        
        catch (Exception e) {
            return null;
        }
    }


    /**
     * @see org.kuali.core.util.properties.PropertyTree#clear()
     */
    public void clear() {
        // Can't clear these
    }

    /**
     * @see org.kuali.core.util.properties.PropertyTree#isEmpty()
     */
    public boolean isEmpty() {
        return false; // There are always financial system parameters
    }

    /**
     * @see org.kuali.core.util.properties.PropertyTree#containsKey(java.lang.Object)
     */
    public boolean containsKey(Object key) {
        try {
            return getConfigurationService().parameterExists("KRA-PD", "D", key.toString());
        }
        catch(Exception e) {
            return false;
        }
    }

    /**
     * @see org.kuali.core.util.properties.PropertyTree#putAll(java.util.Map)
     */
    public void putAll(Map m) {
        // cannot override
    }

    /**
     * @see org.kuali.core.util.properties.PropertyTree#remove(java.lang.Object)
     */
    public Object remove(Object key) {
        // cannot override
        return null;
    }

    /**
     * @see org.kuali.core.util.properties.PropertyTree#put(java.lang.Object, java.lang.Object)
     */
    public Object put(Object key, Object value) {
        // cannot override
        return null;
    }

    private KualiConfigurationService getConfigurationService() {
        return KraServiceLocator.getService(KualiConfigurationService.class);
    }

    private BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }

    /**
     * Code duplication. Used because private in KualiConfigurationService
     * @see org.kuali.core.service.KualiConfigurationService#getApplicationParameter(String, String)
     */
    private Collection getParameters(String parameterNamespaceCode, String parameterDetailTypeCode, String parameterName) {
        if (StringUtils.isBlank(parameterNamespaceCode)) {
            throw new IllegalArgumentException("blank scriptName: '" + parameterNamespaceCode + "'");
        }
        else if (StringUtils.isBlank(parameterDetailTypeCode)) {
            throw new IllegalArgumentException("blank parameter: '" + parameterDetailTypeCode + "'");
        }
        else if (StringUtils.isBlank(parameterName)) {
            throw new IllegalArgumentException("blank parameter: '" + parameterName + "'");
        }
        HashMap map = new HashMap();
        map.put("parameterNamespaceCode", parameterNamespaceCode);
        map.put("parameterDetailTypeCode", parameterDetailTypeCode);
        map.put("parameterName", parameterName);
        return getBusinessObjectService().findMatching(Parameter.class, map);
    }
    /**
     * Code duplication. Used because private in KualiConfigurationService
     * @see org.kuali.core.service.KualiConfigurationService#getApplicationParameter(String, String)
     */
    private Parameter getParameter(String parameterNamespaceCode, String parameterDetailTypeCode, String parameter) {
        LOG.debug("getApplicationParameter() started");

        Collection c = getParameters(parameterNamespaceCode, parameterDetailTypeCode, parameter);
        switch (c.size()) {
            case 0:
                throw new ApplicationParameterException(parameterNamespaceCode + ", " + parameterDetailTypeCode, parameter, "not found");
            case 1:
                return (Parameter) c.iterator().next();
            default:
                throw new ApplicationParameterException(parameterNamespaceCode + ", " + parameterDetailTypeCode, parameter, "multiple found");
        }
    }
}
