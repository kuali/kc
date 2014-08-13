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
package org.kuali.coeus.common.budget.impl.copy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.budget.framework.copy.DeepCopyIgnore;
import org.kuali.coeus.common.budget.framework.copy.DeepCopyPostProcessor;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.common.budget.framework.core.BudgetDocument;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.util.ObjectUtils;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * This class is to process deep copy with Object Utils and then 
 */
@Component("deepCopyPostProcessor")
public class DeepCopyPostProcessorImpl implements DeepCopyPostProcessor {

    private static final Log LOG = LogFactory.getLog(DeepCopyPostProcessorImpl.class);

    public <T extends Serializable> T processDeepCopyWithDeepCopyIgnore(T source) {
        Serializable copiedObject = ObjectUtils.deepCopy(source);
        return (T) processDeepCopyIgnoreAnnotation(copiedObject);
    }
    private Serializable processDeepCopyIgnoreAnnotation(Serializable copiedObject) {
        List<Field> list = new ArrayList<Field>();
        findAllFields(copiedObject.getClass(), list);
        for (Field field : list) {
            if(field.isAnnotationPresent(DeepCopyIgnore.class)){
                Annotation deepCopyIgnoreAnn = field.getAnnotation(DeepCopyIgnore.class);
                if(deepCopyIgnoreAnn!=null){
                    try {
                        ObjectUtils.setObjectProperty(copiedObject, field.getName(), null);
                    }catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                    }
                }
            }
            if(field.getType().isAssignableFrom(List.class)){
                List<Serializable> objectList = (List<Serializable>)ObjectUtils.getPropertyValue(copiedObject, field.getName());
                if(objectList!=null)
                for (Serializable objectFromList : objectList) {
                    processDeepCopyWithDeepCopyIgnore(objectFromList);
                }
            }
        }
        return copiedObject; 
    }
    /**
     * This method uses recursion to find all declared fields for a class hierarchy
     * @param klass
     * @param allFields
     */
    @SuppressWarnings("unchecked")
    private void findAllFields(Class klass, List<Field> allFields) {
        Field[] fields = klass.getDeclaredFields();
        allFields.addAll(Arrays.asList(fields));
        klass = klass.getSuperclass();
        if(klass != null && !klass.equals(KcPersistableBusinessObjectBase.class) ) {
            findAllFields(klass, allFields);
        }
    }

    @SuppressWarnings("unchecked")
    public void fixProperty(Object object, String methodName, Class clazz, Object propertyValue, Map<String, Object> objectMap){
        if(ObjectUtils.isNotNull(object)) {
            if (object instanceof PersistableBusinessObject) {
                PersistableBusinessObject objectWId = (PersistableBusinessObject) object;
                if (objectMap.get(objectWId.getObjectId()) != null) return;
                objectMap.put(((PersistableBusinessObject) object).getObjectId(), object);
                
                Method[] methods = object.getClass().getMethods();
                for (Method method : methods) {
                    if (method.getName().equals(methodName)) {
                        if (!(object instanceof BudgetDocument)) {
                              try {
                                if(clazz.equals(Long.class))
                                    method.invoke(object, (Long) propertyValue);  
                                else 
                                    method.invoke(object, (Integer) propertyValue);
                               } catch (Throwable e) { }  
                        }
                    } else if (isPropertyGetterMethod(method, methods)) {
                        Object value = null;
                        try {
                            value = method.invoke(object);
                        } catch (Throwable e) {
                            //We don't need to propagate this exception
                        }
                        
                        if(value != null) {
                            if (value instanceof Collection) {
                                Collection<Object> c = (Collection<Object>) value;
                                Iterator<Object> iter = c.iterator();
                                while (iter.hasNext()) {
                                    Object entry = iter.next();
                                    fixProperty(entry, methodName, clazz, propertyValue, objectMap);
                                }
                            } else {
                                fixProperty(value, methodName, clazz, propertyValue, objectMap);
                            }   
                        }
                    }
                }
            }
        }
    }
    /**
     * Is the given method a getter method for a property?  Must conform to
     * the following:
     * <ol>
     * <li>Must start with the <b>get</b></li>
     * <li>Must have a corresponding setter method</li>
     * <li>Must have zero arguments.</li>
     * </ol>
     * @param method the method to check
     * @param methods the other methods in the object
     * @return true if it is property getter method; otherwise false
     */
    private boolean isPropertyGetterMethod(Method method, Method methods[]) {
        if (method.getName().startsWith("get") && method.getParameterTypes().length == 0) {
            String setterName = method.getName().replaceFirst("get", "set");
            for (Method m : methods) {
                if (m.getName().equals(setterName)) {
                    return true;
                }
            }
        }
        return false;
    }
    
}
