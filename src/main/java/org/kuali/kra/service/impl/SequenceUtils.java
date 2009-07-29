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
package org.kuali.kra.service.impl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.util.IdentitySet;
import org.kuali.kra.SeparatelySequenceableAssociate;
import org.kuali.kra.SequenceAssociate;
import org.kuali.kra.SequenceOwner;
import org.kuali.kra.Sequenceable;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.service.VersionException;
import org.kuali.rice.kns.util.ObjectUtils;

/**
 * This class provides Sequence support to the VersioningService.
 * 
 * Fan-out complexity exceeds 20 because <b>reflection</b> is used and that 
 * introduces so many exception and meta-data types
 */
public class SequenceUtils {
    private static final String SEQUENCING_ERR_MSG = "An error occured sequencing";

    private static final Log LOGGER = LogFactory.getLog(SequenceUtils.class);

    /**
     * Using an identity set to store already sequenced references.  In Java 6 and above the following can be used
     * as a Set Implementation java.util.Collections.newSetFromMap(new java.util.IdentityHashMap<SequenceAssociate<?>, Boolean>())
     */
    @SuppressWarnings("unchecked")
    private final Set<SequenceAssociate<?>> alreadySequencedAssociates = Collections.synchronizedSet(new IdentitySet());

    /**
     * This method sequences a SequenceOwner to a new version.
     * @param <T> the type of SequenceOwner to sequence.
     * @param oldVersion the old sequence owner
     * @return The newly versioned owner 
     * @throws VersionException if versioning fails
     */
    public <T extends SequenceOwner<?>> T sequence(T oldVersion) throws VersionException {
        
        try {
            @SuppressWarnings("unchecked")
            T newVersion = (T) ObjectUtils.deepCopy(oldVersion);
            newVersion.incrementSequenceNumber();
            newVersion.resetPersistenceState();
            sequenceAssociations(newVersion);
            return newVersion;
        } catch (Exception e) {
            LOGGER.error(SEQUENCING_ERR_MSG, e);
            throw new VersionException(e);
        }
    }

    /**
     * This method sequences a SeparatelySequenceableAssociate a new version.
     * @param <T> the type of SeparatelySequenceableAssociate to sequence.
     * @param oldAssociate the old SeparatelySequenceableAssociate to sequence
     * @return The newly versioned associate 
     * @throws VersionException if versioning fails
     */
    public <T extends SeparatelySequenceableAssociate> T sequence(T oldAssociate) throws VersionException {
        try {
            @SuppressWarnings("unchecked")
            T newAssociate = (T) ObjectUtils.deepCopy(oldAssociate);
            newAssociate.incrementSequenceNumber();
            newAssociate.resetPersistenceState();
            return newAssociate;
        } catch (Exception e) {
            LOGGER.error(SEQUENCING_ERR_MSG, e);
            throw new VersionException(e);
        }
    }
    
    /**
     * This method sequences a SequenceOwner and a of SeparatelySequenceableAssociates to a new version.
     * @param <T> the type of SeparatelySequenceableAssociate to sequence.
     * @param oldAssociates the list of old associates
     * @return a List of new Associates
     * @throws VersionException if versioning fails
     */
    public <T extends SeparatelySequenceableAssociate> List<T> sequence(List<T> oldAssociates) throws VersionException {
        try {
            List<T> newAssociates = new ArrayList<T>();
            for (T oldAssociate : oldAssociates) {
                newAssociates.add(sequence(oldAssociate));
            }
            return newAssociates;
        } catch (Exception e) {
            LOGGER.error(SEQUENCING_ERR_MSG, e);
            throw new VersionException(e);
        }
    }

    private void sequenceAssociations(SequenceAssociate<?> associate) {
        this.alreadySequencedAssociates.add(associate);
        sequenceOneToOneAssociations(associate);
        sequenceCollections(associate);
    }

    private void sequenceOneToOneAssociations(SequenceAssociate<?> parent) {
        for (Field field : parent.getClass().getDeclaredFields()) {
            if (isFieldASequenceAssociate(field) & !skipVersioning(field)) {
                final Method getter = findReadMethod(parent, field);
                SequenceAssociate<?> associate = getSequenceAssociateReference(parent, getter);
                this.executeSequencing(associate, parent);
            }
        }
    }
    
    /**
     * Recursively sequences collection(s) of SequenceAssociates represented in the Fields array.
     * 
     * @param newVersion
     * @param parent
     */
    private void sequenceCollections(SequenceAssociate<?> parent) {
        for (Field field : parent.getClass().getDeclaredFields()) {
            if (isFieldACollection(field)) {
                final Method getter = findReadMethod(parent, field);
                Type type = getter.getGenericReturnType();
                if (isCollectionElementASequenceAssociate(type) && !skipVersioning(field)) {
                    Collection<SequenceAssociate<?>> c = getSequenceAssociateCollection(parent, getter);
                    if(c != null) {
                        for (SequenceAssociate<?> associate : c) {
                            this.executeSequencing(associate, parent);
                        }
                    }
                }
            }
        }
    }

    /**
     * This method will execute the sequencing logic (figure out if the associate has been sequenced, reset persistence state, etc.).
     * 
     * @param associate the associate to sequence
     * @param parent the associates parent
     */
    private void executeSequencing(SequenceAssociate<?> associate, SequenceAssociate<?> parent) {
        if (associate != null && parent != null && !this.alreadySequencedAssociates.contains(associate)) {
            final SequenceOwner<?> owner = parent instanceof SequenceOwner<?> ? (SequenceOwner<?>) parent : parent.getSequenceOwner();
            this.setSequenceOwner(associate, owner);
            associate.resetPersistenceState();
            if (!isAssociateAlsoASequenceOwner(associate)) {
                sequenceAssociations(associate);
            }
        }
    }

    private boolean isFieldASequenceAssociate(Field field) {
        return isFieldASpecifiedType(field, SequenceAssociate.class);
    }
    
    private boolean isFieldASpecifiedType(Field field, Class<?> type) {
        return type.isAssignableFrom(field.getType());
    }
    
    private SequenceAssociate<?> getSequenceAssociateReference(SequenceAssociate<?> parent, Method getter) {
        return this.getProperty(parent, getter);
    }
    
    private boolean isAssociateAlsoASequenceOwner(SequenceAssociate<?> associate) {
        return SequenceOwner.class.isAssignableFrom(associate.getClass());
    }

    private boolean isFieldACollection(Field field) {
        return Collection.class.isAssignableFrom(field.getType());
    }
    
    private boolean isCollectionElementASequenceAssociate(Type returnType) {
        return isCollectionElementOfSpecifiedType(returnType, SequenceAssociate.class);
    }

    private boolean isCollectionElementOfSpecifiedType(Type returnType, Class<?> checkType) {
        boolean isCheckedType = returnType instanceof ParameterizedType;
        if (isCheckedType) {
            ParameterizedType type = (ParameterizedType) returnType;
            Type[] typeArguments = type.getActualTypeArguments();
            
            isCheckedType = typeArguments.length == 1 && checkType.isAssignableFrom((Class<?>) typeArguments[0]);
        }
        return isCheckedType;
    }
    
    private Collection<SequenceAssociate<?>> getSequenceAssociateCollection(Sequenceable parent, Method getter) {
        return getProperty(parent, getter);
    }
    
    /**
     * Sets the sequence associate's owner to the potential owner if the associate's owner is not itself.
     * @param toSet the associate to set the owner on.
     * @param potentialOwner the potential new owner
     */
    @SuppressWarnings("unchecked")
    private <T extends SequenceOwner<?>> void setSequenceOwner(SequenceAssociate<T> toSet, SequenceOwner<?> potentialOwner) {
        //this is sort of a suspect workaround for possible incorrect owner setting
        if (toSet.getSequenceOwner() != toSet) {
            toSet.setSequenceOwner((T) potentialOwner);
        }
    }
    
    /**
     * Gets a property on an object using a getter.
     * @param <T> the type of object to return
     * @param o the object to execute the getter on
     * @param getter the getter
     * @return the object retrieved via the getter
     * @throws PropertyAccessException if unable to retrieve the object from the getter for some reason
     */
    @SuppressWarnings("unchecked")
    private <T extends Object> T getProperty(Object o, Method getter) throws PropertyAccessException {
        
        try {
            return (T) getter.invoke(o, (Object[]) null);
        } catch (IllegalArgumentException e) {
            throw new PropertyAccessException(e);
        } catch (IllegalAccessException e) {
            throw new PropertyAccessException(e);
        } catch (InvocationTargetException e) {
            throw new PropertyAccessException(e);
        }
    }
    
    /**
     * Exception thrown when there is a problem retrieving a property.
     */
    private static class PropertyAccessException extends RuntimeException {
        private static final long serialVersionUID = 4282833885999270264L;
        
        /**
         * Construct an exception wrapping a Throwable.
         * @param t the throwable.
         */
        public PropertyAccessException(Throwable t) {
            super(t);
        }
    }
    
    /**
     * This method retrieves the getter for a field on the passed in object.
     * 
     * @param parent the object to retrieve the getter from
     * @param field the field to retrieve the getter for
     * @return the getter.  Will never return null
     * @throws GetterException if there is a problem retrieving the "getter" for a field
     */
    private Method findReadMethod(Object parent, Field field) throws GetterException {
        
        final PropertyDescriptor pd;
        try {
            pd = PropertyUtils.getPropertyDescriptor(parent, field.getName());
            if (pd == null) {
                throw new GetterException(String.format("The property descriptor for field [%s] on class [%s] could not be found", field.getName(), parent.getClass().getName()));
            }
        } catch (IllegalAccessException e) {
            throw new GetterException(e);
        } catch (InvocationTargetException e) {
            throw new GetterException(e);
        } catch (NoSuchMethodException e) {
            throw new GetterException(e);
        }
        final Method getter = pd.getReadMethod();
        if (getter == null) {
            throw new GetterException(String.format("No getter defined for field [%s] on class [%s]", field.getName(), parent.getClass().getName()));
        }
        
        return getter;
    }

    private boolean skipVersioning(Field field) {
        return field.getAnnotation(SkipVersioning.class) != null;
    }
    
    /**
     * Exception thrown when there is a problem retrieving a getter.
     */
    private static class GetterException extends RuntimeException {
        private static final long serialVersionUID = 4282833885999270264L;

        /**
         * Construct an exception wrapping a Throwable.
         * @param t the throwable.
         */
        public GetterException(Throwable t) {
            super(t);
        }
        
        /**
         * Construct an exception with a message.
         * @param msg the message.
         */
        public GetterException(String msg) {
            super(msg);
        }  
    }
}
