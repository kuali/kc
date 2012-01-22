/*
 * Copyright 2005-2010 The Kuali Foundation
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
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.ojb.broker.core.proxy.ProxyHelper;
import org.kuali.rice.core.web.format.FormatException;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.service.KRADServiceLocator;
import org.kuali.rice.krad.util.ObjectUtils;

public class ObjectCopyUtils {
    
    public static final int MAX_DEPTH_FOR_PROXY_MATERILIZATION = 3; 
    
    public static void prepareObjectForDeepCopy(PersistableBusinessObject bo) {
        try {
            materializeAllProxies(bo);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static void materializeAllProxies(PersistableBusinessObject bo) throws Exception {
        ObjectUtils.materializeSubObjectsToDepth(bo, MAX_DEPTH_FOR_PROXY_MATERILIZATION); 
        ObjectCopyUtils.materializeUpdateableCollections(bo);
    }
    
    public static void materializeUpdateableCollections(Object bo) throws FormatException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        ObjectCopyUtils.materializeUpdateableCollections(bo, MAX_DEPTH_FOR_PROXY_MATERILIZATION);
    }
    
    public static void materializeUpdateableCollections(Object bo, int depth) throws FormatException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (depth == 0 || ObjectUtils.isNull(bo)) {
            return;
        }
        
        if (depth < 0 || depth > MAX_DEPTH_FOR_PROXY_MATERILIZATION) {
            throw new IllegalArgumentException("The depth passed in was out of bounds.  Only values between 0 and 3, inclusively, are allowed.");
        }
        
        PropertyDescriptor[] propertyDescriptors = PropertyUtils.getPropertyDescriptors(bo.getClass());
        for (int i = 0; i < propertyDescriptors.length; i++) {
            if (KRADServiceLocator.getPersistenceStructureService().hasCollection(bo.getClass(), propertyDescriptors[i].getName()) && KRADServiceLocator.getPersistenceStructureService().isCollectionUpdatable(bo.getClass(), propertyDescriptors[i].getName())) {
                Collection<PersistableBusinessObject> updateableCollection = (Collection<PersistableBusinessObject>) ObjectUtils.getPropertyValue(bo, propertyDescriptors[i].getName());
                if ((updateableCollection != null) && ProxyHelper.isCollectionProxy(updateableCollection)) {
                    ObjectUtils.materializeObjects(updateableCollection);
                    for(PersistableBusinessObject elementBo : updateableCollection) {
                        materializeUpdateableCollections(elementBo, depth-1);
                    }
                }
            }
        }
    }
}
