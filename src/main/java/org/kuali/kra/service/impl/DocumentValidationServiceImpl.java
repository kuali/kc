/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ojb.broker.metadata.ClassDescriptor;
import org.apache.ojb.broker.metadata.CollectionDescriptor;
import org.apache.ojb.broker.metadata.ObjectReferenceDescriptor;
import org.apache.ojb.broker.metadata.fieldaccess.PersistentField;
import org.kuali.core.bo.PersistableBusinessObject;
import org.kuali.core.service.impl.PersistenceServiceStructureImplBase;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.ObjectUtils;
import org.kuali.kra.service.DocumentValidationService;
import org.kuali.rice.KNSServiceLocator;

public class DocumentValidationServiceImpl extends PersistenceServiceStructureImplBase implements DocumentValidationService {
    // TODO : hack to validate only updatable reference
    // if need to be rferenced for a while, then probably should be moved to some base service class that can be inherited by other modules
    public void validateDocumentRecursively(PersistableBusinessObject businessObject, int depth) {
        // put depth here just in case there is recursive reference between 2 bos
        // should not need depth?
        KNSServiceLocator.getDictionaryValidationService().validateBusinessObject(businessObject);
        validateBusinessObjectsFromDescriptors(businessObject, PropertyUtils.getPropertyDescriptors(businessObject.getClass()), depth);

    }
    
    private void validateBusinessObjectsFromDescriptors(Object object, PropertyDescriptor[] propertyDescriptors, int depth) {
        ArrayList updatableReferences=(ArrayList)getUpdatableObjects((PersistableBusinessObject)object);
        // is it possible to have recursive reference ? so we may still need depth for control to end
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor propertyDescriptor = propertyDescriptors[i];

            // validate the properties that are descended from BusinessObject
            if (propertyDescriptor.getPropertyType() != null && PersistableBusinessObject.class.isAssignableFrom(propertyDescriptor.getPropertyType()) && ObjectUtils.getPropertyValue(object, propertyDescriptor.getName()) != null) {
                PersistableBusinessObject bo = (PersistableBusinessObject) ObjectUtils.getPropertyValue(object, propertyDescriptor.getName());
                GlobalVariables.getErrorMap().addToErrorPath(propertyDescriptor.getName());
                //if (depth == 0) {
                // filter out documentbusinessobject
                if (!object.getClass().getName().equals(bo.getClass().getName()) && updatableReferences.contains(bo.getClass()) && depth > 0) {
                    validateDocumentRecursively(bo, depth-1);
                }
                GlobalVariables.getErrorMap().removeFromErrorPath(propertyDescriptor.getName());
            }
            else if (propertyDescriptor.getPropertyType() != null && (List.class).isAssignableFrom(propertyDescriptor.getPropertyType()) && ObjectUtils.getPropertyValue(object, propertyDescriptor.getName()) != null) {
                List propertyList = (List) ObjectUtils.getPropertyValue(object, propertyDescriptor.getName());
                for (int j = 0; j < propertyList.size(); j++) {
                    if (propertyList.get(j) != null && propertyList.get(j) instanceof PersistableBusinessObject) {
                        GlobalVariables.getErrorMap().addToErrorPath(StringUtils.chomp(propertyDescriptor.getName(), "s") + "[" + (new Integer(j)).toString() + "]");
                        if (updatableReferences.contains(propertyDescriptor.getName())) {
                            KNSServiceLocator.getDictionaryValidationService().validateBusinessObject((PersistableBusinessObject) propertyList.get(j));
                        }
                        GlobalVariables.getErrorMap().removeFromErrorPath(StringUtils.chomp(propertyDescriptor.getName(), "s") + "[" + (new Integer(j)).toString() + "]");
                    }
                }

            }

        }
    }
    
    private List getUpdatableObjects(PersistableBusinessObject bo) {

        ArrayList updatableObjects=new ArrayList();
        // get the OJB class-descriptor for the bo class
        ClassDescriptor classDescriptor = getClassDescriptor(bo.getClass());

        // get a list of all reference-descriptors for that class
        Vector references = classDescriptor.getObjectReferenceDescriptors();

        // walk through all of the reference-descriptors
        for (Iterator iter = references.iterator(); iter.hasNext();) {
            ObjectReferenceDescriptor reference = (ObjectReferenceDescriptor) iter.next();

            if (reference.getCascadingStore() != ObjectReferenceDescriptor.CASCADE_NONE) {
                //PersistentField persistentField = reference.getPersistentField();
                Class referenceClass = reference.getItemClass();
                //String referenceName = persistentField.getName();
                updatableObjects.add(referenceClass);
                //retrieveReferenceObject(bo, referenceName);
            }
        }
        
        Vector collections = classDescriptor.getCollectionDescriptors();
        for (Iterator iter = collections.iterator(); iter.hasNext();) {
            CollectionDescriptor collection = (CollectionDescriptor) iter.next();
            if(collection.getCascadingStore() != CollectionDescriptor.CASCADE_NONE) {
                PersistentField persistentField = collection.getPersistentField();
                String collectionName = persistentField.getName();
                updatableObjects.add(collectionName);
            }
        }

        return updatableObjects;
    }

     // end hack

}
