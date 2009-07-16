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
package org.kuali.kra.rice;

import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.util.IdentitySet;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.service.PersistenceStructureService;
import org.kuali.rice.kns.service.impl.DictionaryValidationServiceImpl;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.ObjectUtils;

/**
 * This class is a hack to stop DictionaryValidationServiceImpl from recursing incorrectly (for an incredibly long time)
 * with m:m BO relationships.
 * 
 * The code is this class was mostly copied from its super class.  This is not meant to be a permanent solution to the
 * long-running recursion problem.
 */
public class KraDictionaryValidationServiceImpl extends DictionaryValidationServiceImpl {

    private static final Log LOG = LogFactory.getLog(KraDictionaryValidationServiceImpl.class);
    
    private PersistenceStructureService persistenceStructureService;
    
    /** 
     * {@inheritDoc}
     * 
     * mostly copied from super class.
     */
    @Override
    public void validateDocumentAndUpdatableReferencesRecursively(Document document, int maxDepth, boolean validateRequired) {
        this.validateDocumentAndUpdatableReferencesRecursively(document, maxDepth, validateRequired, false);
    }
    
    /** 
     * {@inheritDoc}
     * 
     * mostly copied from super class.
     */
    @Override
    public void validateDocumentAndUpdatableReferencesRecursively(Document document, int maxDepth, boolean validateRequired, boolean chompLastLetterSFromCollectionName) {
        String documentEntryName = document.getDocumentHeader().getWorkflowDocument().getDocumentType();
        // validate primitive values of the document
        validatePrimitivesFromDescriptors(documentEntryName, document, PropertyUtils.getPropertyDescriptors(document.getClass()), "", validateRequired);
        
        if (maxDepth > 0) {
            validateUpdatabableReferencesRecursively(document, maxDepth - 1, validateRequired, chompLastLetterSFromCollectionName, newIdentitySet());
        }
    }
    
    /** 
     * creates a new IdentitySet.
     * @return a new Set
     */
    @SuppressWarnings("unchecked")
    private static Set<BusinessObject> newIdentitySet() {
        //using Hibernate's IdentitySet because Java 5 doesn't have a good way to create one
        return new IdentitySet();
        //in Java 6
        //return java.util.Collections.newSetFromMap(new java.util.IdentityHashMap<BusinessObject, Boolean>());
    }
    
    /** 
     * mostly copied from super class.
     */
    private void validatePrimitivesFromDescriptors(String entryName, Object object, PropertyDescriptor[] propertyDescriptors, String errorPrefix, boolean validateRequired) {
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor propertyDescriptor = propertyDescriptors[i];

            validatePrimitiveFromDescriptor(entryName, object, propertyDescriptor, errorPrefix, validateRequired);
        }
    }
    
    /** 
     * mostly copied from super class.
     */
    private void validateUpdatabableReferencesRecursively(BusinessObject businessObject, int maxDepth, boolean validateRequired, boolean chompLastLetterSFromCollectionName, Set<BusinessObject> processedBOs) {
        if (ObjectUtils.isNull(businessObject) || processedBOs.contains(businessObject)) {
            return;
        }
        processedBOs.add(businessObject);
        
        Map<String, Class> references = persistenceStructureService.listReferenceObjectFields(businessObject.getClass());
        for (String referenceName : references.keySet()) {
            if (persistenceStructureService.isReferenceUpdatable(businessObject.getClass(), referenceName)) {
                Object referenceObj = ObjectUtils.getPropertyValue(businessObject, referenceName);

                if (ObjectUtils.isNull(referenceObj) || !(referenceObj instanceof PersistableBusinessObject)) {
                    continue;
                }

                BusinessObject referenceBusinessObject = (BusinessObject) referenceObj;
                GlobalVariables.getErrorMap().addToErrorPath(referenceName);
                validateBusinessObject(referenceBusinessObject, validateRequired);
                if (maxDepth > 0) {
                    validateUpdatabableReferencesRecursively(referenceBusinessObject, maxDepth - 1, validateRequired, chompLastLetterSFromCollectionName, processedBOs);
                }
                GlobalVariables.getErrorMap().removeFromErrorPath(referenceName);

            }
        }
        Map<String, Class> collections = persistenceStructureService.listCollectionObjectTypes(businessObject.getClass());
        for (String collectionName : collections.keySet()) {
            if (persistenceStructureService.isCollectionUpdatable(businessObject.getClass(), collectionName)) {
            Object listObj = ObjectUtils.getPropertyValue(businessObject, collectionName);
            
            if (ObjectUtils.isNull(listObj)) {
                continue;
            }
            
            if (!(listObj instanceof List)) {
                LOG.error("The reference named " + collectionName + " of BO class " + businessObject.getClass().getName() + " should be of type java.util.List to be validated properly.");
                continue;
            }
            
            List list = (List) listObj;
            for (int i = 0; i < list.size(); i++) {
                if (ObjectUtils.isNotNull(list.get(i)) && list.get(i) instanceof PersistableBusinessObject) {
                String errorPathAddition;
                if (chompLastLetterSFromCollectionName) {
                    errorPathAddition = StringUtils.chomp(collectionName, "s") + "[" + Integer.toString(i) + "]";
                }
                else {
                    errorPathAddition = collectionName + "[" + Integer.toString(i) + "]";
                }
                BusinessObject element = (BusinessObject) list.get(i);
                
                GlobalVariables.getErrorMap().addToErrorPath(errorPathAddition);
                validateBusinessObject(element, validateRequired);
                if (maxDepth > 0) {
                    validateUpdatabableReferencesRecursively(element, maxDepth - 1, validateRequired, chompLastLetterSFromCollectionName, processedBOs);
                }
                GlobalVariables.getErrorMap().removeFromErrorPath(errorPathAddition);
                }
            }
            }
        }
    }
    
    /** 
     * {@inheritDoc}
     * 
     * mostly copied from super class.
     */
    @Override
    public void setPersistenceStructureService(PersistenceStructureService persistenceStructureService) {
        this.persistenceStructureService = persistenceStructureService;
        
        //setting the super classes
        super.setPersistenceStructureService(persistenceStructureService);
    }
}
