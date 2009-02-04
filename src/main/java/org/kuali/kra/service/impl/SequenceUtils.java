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
package org.kuali.kra.service.impl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.core.util.ObjectUtils;
import org.kuali.kra.SequenceAssociate;
import org.kuali.kra.SequenceOwner;
import org.kuali.kra.Sequenceable;
import org.kuali.kra.service.VersionException;

import edu.emory.mathcs.backport.java.util.Collections;

/**
 * This class provides Seqjuence support
 */
public class SequenceUtils {
    private static final Log LOGGER = LogFactory.getLog(SequenceUtils.class);

    private Set<SequenceAssociate> sequencedAssociates;
    
    @SuppressWarnings("unchecked")
    public SequenceUtils() {
        sequencedAssociates = (Set<SequenceAssociate>) Collections.synchronizedSet(new HashSet<SequenceAssociate>());
    }
    
    /**
     * This method sequences a SequenceOwner to a new version
     * @param oldVersion
     * @return
     * @throws VersionException
     */
    public SequenceOwner sequence(SequenceOwner oldVersion) throws VersionException {
        SequenceOwner newVersion = null;
        try {
            newVersion = (SequenceOwner) ObjectUtils.deepCopy(oldVersion);
            newVersion.incrementSequenceNumber();
            sequenceAssociations(newVersion);
        } catch(Exception e) {
            LOGGER.error(e);
            throw new VersionException(e);
        }
        return newVersion;
    } 
    
    @SuppressWarnings("unchecked")
    private Collection<SequenceAssociate> getSequenceAssociateCollection(Sequenceable parent, Method getter)
                                                                                throws IllegalAccessException, 
                                                                                       InvocationTargetException {
        Object value = getter.invoke(parent, (Object[]) null);
        return (Collection<SequenceAssociate>) value;
    }
    
    @SuppressWarnings("unchecked")
    private boolean isCollectionElementASequenceAssociate(Type returnType) {
        boolean isSequenceAssociate = returnType instanceof ParameterizedType; 
        if(isSequenceAssociate){
            ParameterizedType type = (ParameterizedType) returnType;
            Type[] typeArguments = type.getActualTypeArguments();
            isSequenceAssociate = typeArguments.length == 1 
                                && SequenceAssociate.class.isAssignableFrom((Class) typeArguments[0]);
        }
        return isSequenceAssociate;
    }

    private Method findReadMethod(Object parent, Field field) throws IllegalAccessException, InvocationTargetException,
                                                                            NoSuchMethodException {
        PropertyDescriptor pd = PropertyUtils.getPropertyDescriptor(parent, field.getName());
        Method getter = PropertyUtils.getReadMethod(pd);
        return getter;
    }
    

    private void sequenceOneToOneAssociations(SequenceAssociate parent) throws IllegalAccessException, 
                                                                               InvocationTargetException, 
                                                                               NoSuchMethodException {
        Field[] fields = parent.getClass().getDeclaredFields();
        for(Field field : fields) {
            Class fieldType = field.getType();
            boolean isAssociate = SequenceAssociate.class.isAssignableFrom(fieldType); 
            if(isAssociate) {
                Method getter = findReadMethod(parent, field);
                if(getter != null) {
                    SequenceAssociate associate = (SequenceAssociate) getter.invoke(parent, (Object[]) null);
                    if(!sequencedAssociates.contains(associate)) {
                        associate.setSequenceOwner(parent.getSequenceOwner());
                        sequenceAssociations(associate);
                    }
                }
            }        
        }
    }
    
    private void sequenceAssociations(SequenceAssociate associate) throws IllegalAccessException, InvocationTargetException,
                                                                            NoSuchMethodException {
        sequencedAssociates.add(associate);
        sequenceOneToOneAssociations(associate);
        sequenceCollections(associate);
    }

/**
* Recursively sequences collection(s) of SequenceAssociates represented in the Fields array
* @param newVersion
* @param parent
* @throws IllegalAccessException
* @throws InvocationTargetException
* @throws NoSuchMethodException
*/
@SuppressWarnings("unchecked")
    private void sequenceCollections(SequenceAssociate parent) throws IllegalAccessException, InvocationTargetException,
                                                                        NoSuchMethodException {
        Field[] fields = parent.getClass().getDeclaredFields();
        for (Field field : fields) {
            Class fieldType = field.getType();
            boolean isCollection = Collection.class.isAssignableFrom(fieldType);
            if (isCollection) {
                Method getter = findReadMethod(parent, field);
                boolean isSequenceAssociate = isCollectionElementASequenceAssociate(getter.getGenericReturnType());
                if (isSequenceAssociate) {
                    Collection<SequenceAssociate> associates = getSequenceAssociateCollection(parent, getter);
                    for (SequenceAssociate associate : associates) {
                        associate.setSequenceOwner(parent.getSequenceOwner());
                        sequenceAssociations(associate);
                    }
                }
            }
        }
    }
}
