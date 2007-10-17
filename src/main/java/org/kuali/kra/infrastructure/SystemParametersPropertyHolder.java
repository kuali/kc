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

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.lang.StringUtils;
import org.kuali.core.bo.Parameter;
import org.kuali.core.exceptions.ApplicationParameterException;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.KualiConfigurationService;

/**
 * Access financial system parameters like they were a map
 *
 * @author $Author: lprzybyl $
 * @version $Revision: 1.5 $
 */
public class SystemParametersPropertyHolder extends HashMap {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(SystemParametersPropertyHolder.class);    
    private static final String PARAMETER_NAMESPACE_CODE = "parameterNamespaceCode";
    private static final String PARAMETER_DETAIL_TYPE_CODE = "parameterDetailTypeCode";
    private static final String PARAMETER_NAME = "parameterName";
    
    private String parameterNamespaceCode;
    private String parameterDetailTypeCode;

    /**
     * default constructor
     */
    public SystemParametersPropertyHolder() {
    }

    /**
     * Create System Parameter Property Holder with a namespace code and detail type code
     *
     * @param namespaceCode
     * @param detailTypeCode
     */
    public SystemParametersPropertyHolder(String namespaceCode, String detailTypeCode) {
        LOG.info("Creating access to System Parameters as described: \n" 
                 + PARAMETER_NAMESPACE_CODE + "'" + namespaceCode + "'\n"
                 + PARAMETER_DETAIL_TYPE_CODE + "'" + detailTypeCode + "'");
        parameterNamespaceCode = namespaceCode;
        parameterDetailTypeCode = detailTypeCode;
    }

    /**
     * accessor for the parameter namespace code
     *
     * @param namespaceCode
     */
    public void setParameterNamespaceCode(String namespaceCode) {
        parameterNamespaceCode = namespaceCode;
    }

    /**
     * accessor for the parameter namespace code
     *
     * @param namespaceCode
     */
    public void setParameterDetailTypeCode(String detailTypeCode) {
        parameterDetailTypeCode = detailTypeCode;
    }
    
    /**
     * accessor for the parameter namespace code
     *
     * @return String
     */
    public String getParameterNamespaceCode() {
        return parameterNamespaceCode;
    }

    /**
     * Accessor for the parameter detail type code
     *
     * @return String
     */
    public String getParameterDetailTypeCode() {
        return parameterDetailTypeCode;
    }

    // delegated methods
    /**
     * @see org.kuali.core.util.properties.PropertyTree#get(java.lang.Object)
     */
    public Object get(Object key) {        
        Object retval = null;

        LOG.info("Requesting parameter " + key);
        
        if (!super.containsKey(key)) { 
            try {
                retval = new PropertyUtilsBean().describe(getParameter(getParameterNamespaceCode(), getParameterDetailTypeCode(), key.toString()));
            }
            catch (InvocationTargetException e) {
                throw new ApplicationParameterException(getParameterNamespaceCode() + ", " + getParameterDetailTypeCode(), key.toString(), " cannot be described as a map");
            }
            catch (IllegalAccessException e) {
                throw new ApplicationParameterException(getParameterNamespaceCode() + ", " + getParameterDetailTypeCode(), key.toString(), " cannot be described as a map");
            }
            catch (NoSuchMethodException e) {
                throw new ApplicationParameterException(getParameterNamespaceCode() + ", " + getParameterDetailTypeCode(), key.toString(), " cannot be described as a map");
            }
            
            LOG.info("Adding " + retval);
            super.put(key, retval);
        }

        LOG.info("Returning " + retval);
        return retval;
    }


    /**
     * @see org.kuali.core.util.properties.PropertyTree#clear()
     */
    public void clear() {
        throw new UnsupportedOperationException("Can't clear these System parameters");
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
        boolean retval = super.containsKey(key);

        if (retval) {
            return retval;
        }
        
        return getConfigurationService().parameterExists(getParameterNamespaceCode(), getParameterDetailTypeCode(), key.toString());
    }

    /**
     * @see org.kuali.core.util.properties.PropertyTree#remove(java.lang.Object)
     */
    public Object remove(Object key) {
        throw new UnsupportedOperationException("Can't remove system parameters through a hashmap");
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
    private Parameter getParameters(String parameterNamespaceCode, String parameterDetailTypeCode, String parameterName) {
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
        map.put(PARAMETER_NAMESPACE_CODE, parameterNamespaceCode);
        map.put(PARAMETER_DETAIL_TYPE_CODE, parameterDetailTypeCode);
        map.put(PARAMETER_NAME, parameterName);

        return (Parameter) getBusinessObjectService().findByPrimaryKey(Parameter.class, map);
    }

    /**
     * Code duplication. Used because private in KualiConfigurationService
     * @see org.kuali.core.service.KualiConfigurationService#getApplicationParameter(String, String)
     */
    private Parameter getParameter(String parameterNamespaceCode, String parameterDetailTypeCode, String parameter) {
        LOG.debug("getApplicationParameter() started");

        Parameter retval = getParameters(parameterNamespaceCode, parameterDetailTypeCode, parameter);
        if (retval == null) {
            throw new ApplicationParameterException(parameterNamespaceCode + ", " + parameterDetailTypeCode, parameter, "not found");
        }
        return retval;
    }
}
