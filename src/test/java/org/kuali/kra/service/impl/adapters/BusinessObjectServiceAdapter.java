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
package org.kuali.kra.service.impl.adapters;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.service.BusinessObjectService;

public class BusinessObjectServiceAdapter implements BusinessObjectService {

    public int countMatching(Class clazz, Map<String, ?> positiveFieldValues, Map<String, ?> negativeFieldValues) {
        return 0;
    }

    public int countMatching(Class clazz, Map<String, ?> fieldValues) {
        return 0;
    }

    public void delete(List<? extends PersistableBusinessObject> boList) {
    }

    public void delete(PersistableBusinessObject bo) {
    }

    public void deleteMatching(Class clazz, Map<String, ?> fieldValues) {
    }

    public <T extends BusinessObject> Collection<T> findAll(Class<T> clazz) {
        return null;
    }

    public <T extends BusinessObject> Collection<T> findAllOrderBy(Class<T> clazz, String sortField, boolean sortAscending) {
        return null;
    }

    public <T extends BusinessObject> T findByPrimaryKey(Class<T> clazz, Map<String, ?> primaryKeys) {
        return null;
    }

    public <T extends BusinessObject> T findBySinglePrimaryKey(Class<T> clazz, Object primaryKey) {
        return null;
    }

    public <T extends BusinessObject> Collection<T> findMatching(Class<T> clazz, Map<String, ?> fieldValues) {
        return null;
    }

    public <T extends BusinessObject> Collection<T> findMatchingOrderBy(Class<T> clazz, Map<String, ?> fieldValues,
            String sortField, boolean sortAscending) {
        return null;
    }

    public BusinessObject getReferenceIfExists(BusinessObject bo, String referenceName) {
        return null;
    }

    public List<? extends PersistableBusinessObject> linkAndSave(List<? extends PersistableBusinessObject> businessObjects) {
        return null;
    }

    public PersistableBusinessObject linkAndSave(PersistableBusinessObject bo) {
        return null;
    }

    public void linkUserFields(List<PersistableBusinessObject> bos) {
    }

    public void linkUserFields(PersistableBusinessObject bo) {
    }

    public PersistableBusinessObject manageReadOnly(PersistableBusinessObject bo) {
        return null;
    }

    public PersistableBusinessObject retrieve(PersistableBusinessObject object) {
        return null;
    }

    public List<? extends PersistableBusinessObject> save(List<? extends PersistableBusinessObject> businessObjects) {
        return null;
    }

    public PersistableBusinessObject save(PersistableBusinessObject bo) {
        return null;
    }
    
}
