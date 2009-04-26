/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.budget.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.service.BusinessObjectService;

public class BusinessObjectServiceAdapter implements BusinessObjectService {

    public int countMatching(Class clazz, Map fieldValues) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int countMatching(Class clazz, Map positiveFieldValues, Map negativeFieldValues) {
        // TODO Auto-generated method stub
        return 0;
    }

    public void delete(PersistableBusinessObject bo) {
        // TODO Auto-generated method stub
        
    }

    public void delete(List<? extends PersistableBusinessObject> boList) {
        // TODO Auto-generated method stub
        
    }

    public void deleteMatching(Class clazz, Map fieldValues) {
        // TODO Auto-generated method stub
        
    }

    public Collection findAll(Class clazz) {
        // TODO Auto-generated method stub
        return null;
    }

    public PersistableBusinessObject findByPrimaryKey(Class clazz, Map primaryKeys) {
        // TODO Auto-generated method stub
        return null;
    }

    public Collection findMatching(Class clazz, Map fieldValues) {
        // TODO Auto-generated method stub
        return null;
    }

    public Collection findMatchingOrderBy(Class clazz, Map fieldValues, String sortField, boolean sortAscending) {
        // TODO Auto-generated method stub
        return null;
    }

    public PersistableBusinessObject getReferenceIfExists(PersistableBusinessObject bo, String referenceName) {
        // TODO Auto-generated method stub
        return null;
    }

    public void linkAndSave(PersistableBusinessObject bo) {
        // TODO Auto-generated method stub
        
    }

    public void linkAndSave(List<PersistableBusinessObject> businessObjects) {
        // TODO Auto-generated method stub
        
    }

    public void linkUserFields(PersistableBusinessObject bo) {
        // TODO Auto-generated method stub
        
    }

    public void linkUserFields(List<PersistableBusinessObject> bos) {
        // TODO Auto-generated method stub
        
    }

    public PersistableBusinessObject retrieve(PersistableBusinessObject object) {
        // TODO Auto-generated method stub
        return null;
    }

    public void save(PersistableBusinessObject bo) {
        // TODO Auto-generated method stub
        
    }

    public void save(List businessObjects) {
        // TODO Auto-generated method stub
        
    }
    
    public BusinessObject getReferenceIfExists(BusinessObject bo, String referenceName) {
        return null;
    }
    
}
