/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.web.struts.form;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class contains methods to help reset an Objects properties to default values.
 */
final class ResetElementsHelper {
  
    private static final Map<Class<?>, Object> WRAPPER_RESET_VALUES;
    static {
        final Map<Class<?>, Object> values = new HashMap<Class<?>, Object>();
        
        putResetValue(values, Boolean.class, Boolean.FALSE);
        putResetValue(values, Byte.class, Byte.valueOf((byte) 0));
        putResetValue(values, Short.class, Short.valueOf((short) 0));
        putResetValue(values, Integer.class, Integer.valueOf(0));
        putResetValue(values, Long.class, Long.valueOf(0L));
        putResetValue(values, Float.class, Float.valueOf(0.0f));
        putResetValue(values, Double.class, Double.valueOf(0.0d));
        putResetValue(values, Character.class, Character.valueOf('\u0000'));
        
        WRAPPER_RESET_VALUES = Collections.unmodifiableMap(values);
    }
    
    
    private static final String ELEMENTS_TO_RESET = "elementsToReset";
    private static final Log LOG = LogFactory.getLog(ResetElementsHelper.class);
    
    /**
     * This method does a type safe put on map where it associates a class token and an instance of that class.
     * 
     * @param <T> the type
     * @param toPut the map to put the items in
     * @param clazz the class key
     * @param value the value
     */
    private static <T> void putResetValue(final Map<Class<?>, Object> toPut, final Class<T> clazz, final T value) {
        toPut.put(clazz, value);
    }
    
    /**
     * This method resets elements contained in the passed in object.
     * 
     * @param object the object following the JavaBean's specification to reset values on.
     * @param elementsToReset the elements contained in the passed in object to reset.
     * @throws ResetException if unable to reset the element or if the property type specified for reset is not a supported type.
     * @throws NullPointerException if the object or the elementsToReset is null
     */
    public static void resetElements(final Object object, final Collection<String> elementsToReset) {
        
        if (object == null) {
            throw new NullPointerException("the object is null");
        }

        if (elementsToReset == null) {
            throw new NullPointerException("the elementsToReset is null");
        }
        
        for (final String element : elementsToReset) {
            try {
                PropertyUtils.setNestedProperty(object, element, getResetValue(object, element));
            } catch (final IllegalAccessException e) {
                throw new ResetException(e);
            } catch (final InvocationTargetException e) {
                throw new ResetException(e);
            } catch (final NoSuchMethodException e) {
                throw new ResetException(e);
            }
        }
    }
      
    /**
     * Gets the reset value of a given element.
     * 
     * @param parent the parent object
     * @param element the name of a element to reset on the parent object.
     * @return the reset value
     * @throws IllegalAccessException if a problem occurs getting the element
     * @throws InvocationTargetException getting the element
     * @throws NoSuchMethodException getting the element
     * @throws ResetException if unable to get the element or if the property type specified for reset is not a supported type.
     */
    private static Object getResetValue(final Object parent, final String element) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        assert !StringUtils.isBlank(element) : "the element is blank";
        
        final PropertyDescriptor descriptor = PropertyUtils.getPropertyDescriptor(parent, element);
        
        if (descriptor == null) {
            throw new ResetException("cannot find property [" + element + "] on parent type [" + parent.getClass().getName() + "]");
        }
        
        if (descriptor.getPropertyType().isPrimitive()) {
            return WRAPPER_RESET_VALUES.get(ClassUtils.primitiveToWrapper(descriptor.getPropertyType()));
        }
        //either return a wrapper value or null in the case of any other type of object.
        //it is important to return a non-null value for wrappers because we are using Auto-boxing so
        //heavily in KC which causes a lot of Null Pointers
        return WRAPPER_RESET_VALUES.get(descriptor.getPropertyType());
    }
    
    /**
     * This gets the elements to reset parameters values from the request.  If the parameter
     * does not exist then an empty Collection will be returned.  The Collection returned
     * will always be mutable.
     * 
     * @param request the http request.
     * @return a collection of element names to reset.
     * @throws NullPointerException if the request is null
     */
    public static Collection<String> getElementsToReset(final HttpServletRequest request) {
        if (request == null) {
            throw new NullPointerException("the request is null");
        }
        
        final String[] elementsToReset = request.getParameterValues(ELEMENTS_TO_RESET);
        
        final Collection<String> elements = new ArrayList<String>();
        if (elementsToReset == null) {
            return elements;
        }
        
        for (final String element : elementsToReset) {
            if (StringUtils.isNotBlank(element)) {
                elements.add(element);
            } else {
                LOG.warn("the element to reset was blank");
            }
        }
        
        return elements;
    }
    
    /**
     * Exception thrown when a problem occurs executing a reset.
     */
    public static final class ResetException extends RuntimeException {
        private static final long serialVersionUID = -6820043337094780348L;
        
        /** @see Throwable#Throwable(Throwable) */
        public ResetException(final Throwable t) {
            super(t);
        }
        /** @see Throwable#Throwable(String, Throwable) */
        public ResetException(final String message, final Throwable t) {
            super(message, t);
        }
        /** @see Throwable#Throwable(String) */
        public ResetException(final String message) {
            super(message);
        }
    }
}
