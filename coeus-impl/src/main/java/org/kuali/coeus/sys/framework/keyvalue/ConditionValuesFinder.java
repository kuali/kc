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
package org.kuali.coeus.sys.framework.keyvalue;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.service.KeyValuesService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * This class allows you to specify conditions used for retrieving key-value pairs.
 * @param <T> the clazz type.
 */
public class ConditionValuesFinder<T extends PersistableBusinessObject> extends UifKeyValuesFinderBase {

    private KeyValuesService service;
    private Class<T> clazz;
    private Map<String, Object> conditions;
    private String key;
    private String value; 

    @Override
    public List<KeyValue> getKeyValues() {

        this.validateRequiredProperties();
        
        final List<KeyValue> keyValues = new ArrayList<KeyValue>();
        
        @SuppressWarnings("unchecked")
        final Collection<T> bos = this.getKeyValuesService().findMatching(this.clazz, this.conditions);
        
        for (T o : bos) {
            keyValues.add(new ConcreteKeyValue(this.getPropery(o, this.key), this.getPropery(o, this.value)));
        }
        
        return keyValues;
    }
    
    /**
     * Gets a String property on the passed in Object. The property can be a nested property.
     * If the property is not a String then property.toString() is returned.
     * @param o the Object
     * @param name the property name
     * @return the property value.
     * @throws PropertyException if this methods fails to retrieve the property.
     */
    private String getPropery(T o, String name) {
        assert o != null : "o is null";
        assert !StringUtils.isBlank(name) : "name is blank";
        
        try {
            final Object property = PropertyUtils.getNestedProperty(o, name);
            if (property == null) {
                throw new PropertyException("property " + name + " is null.");
            }
            return property.toString();
        } catch (IllegalAccessException|InvocationTargetException|NoSuchMethodException e) {
            throw new PropertyException(e);
        }
    }
    
    /**
     * Gets the clazz to lookup the key/values on.
     * @return the clazz
     */
    public Class<T> getClazz() {
        return this.clazz;
    }

    /**
     * Sets the clazz to lookup the key/values on.
     * @param clazz the clazz
     */
    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * Gets the conditions to apply to the lookup.
     * (ex: column1_name="foobar")
     * @return the conditions
     */
    public Map<String, Object> getConditions() {
        return new HashMap<String, Object>(this.conditions);
    }

    /**
     * Sets the conditions to apply to the lookup.
     * (ex: column1_name="foobar")
     * @param conditions the conditions
     */
    public void setConditions(Map<String, Object> conditions) {
        this.conditions = new HashMap<String, Object>(conditions);
    }

    /**
     * Gets the key (the property name of the key).
     * 
     * <p>
     * The property can be a nested property.
     * If the property is not a String then key.toString() is returned.
     * </p>
     * 
     * @return the key
     */
    public String getKey() {
        return this.key;
    }
    
    /**
     * Sets the key.
     * @param key the key
     * @see #getKey()
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Gets the value (the property name of the value).
     * 
     * <p>
     * The property can be a nested property.
     * If the property is not a String then value.toString() is returned.
     * </p>
     * 
     * @return the value
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Sets the value.
     * @param value the value
     * @see #getValue()
     */
    public void setValue(String value) {
        this.value = value;
    }
    
    /**
     * Validates the the proper fields have been set on this object.
     * @throws IllegalStateException if this properties are invalid
     */
    private void validateRequiredProperties() {
        if (this.clazz == null) {
            throw new IllegalStateException("the clazz has not been set to a non-null value");
        }
        
        if (this.conditions == null) {
            throw new IllegalStateException("the conditions has not been set to a non-null value");
        }
        
        if (StringUtils.isBlank(this.key)) {
            throw new IllegalStateException("the key has not been set to a non-blank value");
        }
        
        if (StringUtils.isBlank(this.value)) {
            throw new IllegalStateException("the value has not been set to a non-blank value");
        }
    }

    public KeyValuesService getKeyValuesService() {
        if (service == null) {
            service = KcServiceLocator.getService(KeyValuesService.class);
        }

        return service;
    }

    public void setKeyValuesService(KeyValuesService service) {
        this.service = service;
    }

    /** Exception thrown when failing to retrieve a property. */
    public static class PropertyException extends RuntimeException implements Serializable {
        
        private static final long serialVersionUID = 4479172838906429551L;

        /**
         * Wraps a caused-by Throwable.
         * @param t the throwable
         */
        public PropertyException(Throwable t) {
            super(t);
        }
        
        /**
         * Creates exception with a message.
         * @param message the message
         */
        public PropertyException(String message) {
            super(message);
        }
    }
}
