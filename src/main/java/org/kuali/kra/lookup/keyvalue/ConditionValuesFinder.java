/*
 * Copyright 2006-2008 The Kuali Foundation
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

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.service.KeyValuesService;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

/**
 * This class allows you to specify conditions used for retrieving key-value pairs.
 * @param <T> the clazz type.
 */
public class ConditionValuesFinder<T extends PersistableBusinessObject> extends KeyValuesBase {

    private final KeyValuesService service;
    private Class<T> clazz;
    private Map<String, Object> conditions;
    private String key;
    private String value; 
    
    /**
     * Creates value finder setting dependencies to default values.
     */
    public ConditionValuesFinder() {
        this(KraServiceLocator.getService(KeyValuesService.class));
    }
    
    /**
     * Creates value finder setting dependencies.
     * @param service the {@link KeyValuesService KeyValuesService}
     * @throws IllegalArgumentException if service is null
     */
    ConditionValuesFinder(final KeyValuesService service) {
        if (service == null) {
            throw new IllegalArgumentException("the service is null");
        }
        this.service = service;
    }

    /** {@inheritDoc} */
    public List<KeyLabelPair> getKeyValues() {

        this.validateRequiredProperties();
        
        final List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        
        @SuppressWarnings("unchecked")
        final Collection<T> bos = this.service.findMatching(this.clazz, this.conditions);
        
        for (T o : bos) {
            keyValues.add(new KeyLabelPair(this.getPropery(o, this.key), this.getPropery(o, this.value)));
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
        } catch (IllegalAccessException e) {
            throw new PropertyException(e);
        } catch (InvocationTargetException e) {
            throw new PropertyException(e);
        } catch (NoSuchMethodException e) {
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
