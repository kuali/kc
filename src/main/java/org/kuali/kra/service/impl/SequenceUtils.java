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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.SeparatelySequenceableAssociate;
import org.kuali.kra.SequenceAssociate;
import org.kuali.kra.SequenceOwner;
import org.kuali.kra.Sequenceable;
import org.kuali.kra.service.VersionException;
import org.kuali.rice.kns.util.ObjectUtils;

import edu.emory.mathcs.backport.java.util.Collections;

/**
 * This class provides Sequence support to the VersioningService
 * 
 * Fan-out complexity exceeds 20 because <b>reflection</b> is used and that 
 * introduces so many exception and meta-data types
 */
public class SequenceUtils {
    private static final Log LOGGER = LogFactory.getLog(SequenceUtils.class);

    /**
     * This set acts to prevent circular sequencing; 
     * i.e. BO A has a collection of BO B which has a collection of BO A's that have been sequenced
     * 
     * This means SequenceAssociates must override equals and hashCode!  
     */
    private Set<SequenceAssociate> alreadySequencedAssociates;

    @SuppressWarnings("unchecked")
    public SequenceUtils() {
        alreadySequencedAssociates = (Set<SequenceAssociate>) Collections.synchronizedSet(new HashSet<SequenceAssociate>());
    }

    /**
     * This method sequences a SequenceOwner to a new version
     * 
     * @param oldVersion
     * @return
     * @throws VersionException
     */
    public SequenceOwner sequence(SequenceOwner oldVersion) throws VersionException {
        SequenceOwner newVersion = null;
        try {
            newVersion = (SequenceOwner) ObjectUtils.deepCopy(oldVersion);
            newVersion.incrementSequenceNumber();
            newVersion.resetPersistenceState();
            sequenceAssociations(newVersion);
        } catch (Exception e) {
            LOGGER.error(e);
            throw new VersionException(e);
        }
        return newVersion;
    }

    /**
     * This method sequences a SeparatelySequenceableAssociate a new version
     * 
     * @param oldAssociate
     * @retrun The newly versioned associate 
     * @throws VersionException
     */
    public SeparatelySequenceableAssociate sequence(SeparatelySequenceableAssociate oldAssociate) throws VersionException {
        try {
            SeparatelySequenceableAssociate newAssociate = (SeparatelySequenceableAssociate) ObjectUtils.deepCopy(oldAssociate);
            newAssociate.resetPersistenceState();
            return newAssociate;
        } catch (Exception e) {
            LOGGER.error(e);
            throw new VersionException(e);
        }
    }
    
    /**
     * This method sequences a SequenceOwner and a of SeparatelySequenceableAssociates to a new version
     * 
     * @param oldVersion
     * @param oldAssociates
     * @param callback
     * @retrun a List of new Associates
     * @throws VersionException
     */
    public List<? extends SeparatelySequenceableAssociate> sequence(SequenceOwner newOwner, List<? extends SeparatelySequenceableAssociate> oldAssociates) 
                                                                                        throws VersionException {
        try {
            List<SeparatelySequenceableAssociate> newAssociates = new ArrayList<SeparatelySequenceableAssociate>();
            for (SeparatelySequenceableAssociate oldAssociate : oldAssociates) {
                SeparatelySequenceableAssociate newAssociate = (SeparatelySequenceableAssociate) ObjectUtils.deepCopy(oldAssociate);
                newAssociate.resetPersistenceState();
                newAssociates.add(newAssociate);
            }
            return newAssociates;
        } catch (Exception e) {
            LOGGER.error(e);
            throw new VersionException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private Collection<SequenceAssociate> getSequenceAssociateCollection(Sequenceable parent, Method getter) throws IllegalAccessException, InvocationTargetException {
        return (Collection<SequenceAssociate>) getter.invoke(parent, (Object[]) null);
    }

    @SuppressWarnings("unchecked")
    private boolean isCollectionElementASequenceAssociate(Type returnType) {
        boolean isSequenceAssociate = returnType instanceof ParameterizedType;
        if (isSequenceAssociate) {
            ParameterizedType type = (ParameterizedType) returnType;
            Type[] typeArguments = type.getActualTypeArguments();
            isSequenceAssociate = typeArguments.length == 1 && SequenceAssociate.class.isAssignableFrom((Class) typeArguments[0]);
        }
        return isSequenceAssociate;
    }

    private Method findReadMethod(Object parent, Field field) throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {
        PropertyDescriptor pd = PropertyUtils.getPropertyDescriptor(parent, field.getName());
        Method getter = PropertyUtils.getReadMethod(pd);
        return getter;
    }

    private void sequenceAssociations(SequenceAssociate associate) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        alreadySequencedAssociates.add(associate);
        sequenceOneToOneAssociations(associate);
        sequenceCollections(associate);
    }

    private void sequenceOneToOneAssociations(SequenceAssociate parent) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        for (Field field : parent.getClass().getDeclaredFields()) {
            if (isFieldASequenceAssociate(field)) {
                Method getter = findReadMethod(parent, field);
                if (getter != null) {
                    SequenceAssociate associate = getSequenceAssociateReference(parent, getter);
                    if (!alreadySequencedAssociates.contains(associate)) {
                        SequenceOwner owner = (parent instanceof SequenceOwner ? (SequenceOwner) parent : parent.getSequenceOwner());
                        associate.setSequenceOwner(owner);
                        associate.resetPersistenceState();
                        if(!isAssociateAlsoASequenceOwner(associate)) {
                            sequenceAssociations(associate);
                        }
                    }
                } else {
                    throw createExceptionForNoGetter(parent, field);
                }
            }
        }
    }

    /**
     * This method...
     * @param parent
     * @param field
     * @return
     */
    private RuntimeException createExceptionForNoGetter(SequenceAssociate parent, Field field) {
        return new RuntimeException(String.format("No getter defined for field %s on class %s", field.getName(), parent.getClass().getName()));
    }

    /**
     * Recursively sequences collection(s) of SequenceAssociates represented in the Fields array
     * 
     * @param newVersion
     * @param parent
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    private void sequenceCollections(SequenceAssociate parent) throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {
        for (Field field : parent.getClass().getDeclaredFields()) {
            if (isFieldACollection(field)) {
                Method getter = findReadMethod(parent, field);
                if (getter != null) {
                    if (isCollectionElementASequenceAssociate(getter.getGenericReturnType())) {
                        SequenceOwner owner = (parent instanceof SequenceOwner ? (SequenceOwner) parent : parent.getSequenceOwner());
                        for (SequenceAssociate associate : getSequenceAssociateCollection(parent, getter)) {
                            associate.setSequenceOwner(owner);
                            associate.resetPersistenceState();
                            if(!isAssociateAlsoASequenceOwner(associate)) {
                                sequenceAssociations(associate);
                            }
                        }
                    }
                } else {
                    throw createExceptionForNoGetter(parent, field);
                }
            }
        }
    }

    private SequenceAssociate getSequenceAssociateReference(SequenceAssociate parent, Method getter) throws IllegalAccessException,
            InvocationTargetException {
        return (SequenceAssociate) getter.invoke(parent, (Object[]) null);
    }

    private boolean isFieldASequenceAssociate(Field field) {
        return SequenceAssociate.class.isAssignableFrom(field.getType());
    }
    
    private boolean isAssociateAlsoASequenceOwner(SequenceAssociate associate) {
        return SequenceOwner.class.isAssignableFrom(associate.getClass());
    }

    private boolean isFieldACollection(Field field) {
        return Collection.class.isAssignableFrom(field.getType());
    }
}
