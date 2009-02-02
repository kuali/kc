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

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.apache.commons.beanutils.BeanPropertyValueChangeClosure;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.core.util.ObjectUtils;
import org.kuali.kra.Sequenceable;
import org.kuali.kra.service.VersionException;

/**
 * This class provides Seqjuence support
 */
public class SequenceUtils {
    private static final Log LOGGER = LogFactory.getLog(SequenceUtils.class);

    public Sequenceable deepCopy(Sequenceable oldVersion) throws VersionException {
        Sequenceable newVersion = null;
        try {
            newVersion = (Sequenceable) ObjectUtils.deepCopy(oldVersion);
            sequenceAssociates(newVersion);
        } catch(Exception e) {
            LOGGER.error(e);
            throw new VersionException(e);
        }
        return newVersion;
    }

    private void sequenceAssociates(Sequenceable newVersion) throws IllegalArgumentException, 
                                                                    IllegalAccessException, 
                                                                    InvocationTargetException, 
                                                                    NoSuchMethodException {
        Field[] fields = newVersion.getClass().getDeclaredFields();
        sequenceScalarFields(newVersion, fields);
        sequenceCollections(newVersion, fields);     
    }

    private void sequenceCollections(Sequenceable newVersion, Field[] fields) throws IllegalAccessException,
                                                                              InvocationTargetException, 
                                                                              NoSuchMethodException {
        for(Field field : fields) {
            if(field.getDeclaringClass().isAssignableFrom(Collection.class)) {
                BeanPropertyValueChangeClosure closure = new BeanPropertyValueChangeClosure("sequenceOwner", 
                                                                                            newVersion);
             
                Collection<Sequenceable> associates = (Collection<Sequenceable>) PropertyUtils
                                                                .getProperty(newVersion, field.getName());
                CollectionUtils.forAllDo(associates, closure);
            }        
        }
    }

    private void sequenceScalarFields(Sequenceable newVersion, Field[] fields) throws IllegalAccessException, 
                                                                               InvocationTargetException, 
                                                                               NoSuchMethodException {
        for(Field field: fields) {
            if(field.getDeclaringClass().isAssignableFrom(Sequenceable.class)) {
                Sequenceable fieldValue = (Sequenceable) PropertyUtils.getProperty(newVersion, 
                                                                                    field.getName());
                fieldValue.setSequenceOwner(newVersion);
            }
        }
    }
}
