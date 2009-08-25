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
package org.kuali.kra.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.service.BusinessObjectService;

public class BusinessObjectServiceAdapter implements BusinessObjectService {

    public int countMatching(Class clazz, Map fieldValues) {
        return 0;
    }

    public int countMatching(Class clazz, Map positiveFieldValues, Map negativeFieldValues) {
        return 0;
    }

    public void delete(PersistableBusinessObject bo) {

    }

    public void delete(List<? extends PersistableBusinessObject> boList) {

    }

    public void deleteMatching(Class clazz, Map fieldValues) {

    }

    public Collection findAll(Class clazz) {
        return null;
    }

    public PersistableBusinessObject findByPrimaryKey(Class clazz, Map primaryKeys) {
        return null;
    }

    public Collection findMatching(Class clazz, Map fieldValues) {
        return null;
    }

    public Collection findMatchingOrderBy(Class clazz, Map fieldValues, String sortField, boolean sortAscending) {
        return null;
    }

    public BusinessObject getReferenceIfExists(BusinessObject bo, String referenceName) {
        return null;
    }

    public void linkAndSave(PersistableBusinessObject bo) {

    }

    public void linkAndSave(List<PersistableBusinessObject> businessObjects) {

    }

    public void linkUserFields(PersistableBusinessObject bo) {

    }

    public void linkUserFields(List<PersistableBusinessObject> bos) {

    }

    public PersistableBusinessObject retrieve(PersistableBusinessObject object) {
        return null;
    }

    public void save(PersistableBusinessObject bo) {

    }

    public void save(List businessObjects) {

    }
}
