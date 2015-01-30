/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.common.budget.impl.copy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.budget.framework.copy.DeepCopyIgnore;
import org.kuali.coeus.common.budget.framework.copy.DeepCopyPostProcessor;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.krad.util.ObjectUtils;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
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
    
}
