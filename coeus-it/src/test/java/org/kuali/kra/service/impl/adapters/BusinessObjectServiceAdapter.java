/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.service.impl.adapters;

import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class BusinessObjectServiceAdapter implements BusinessObjectService {

    public int countMatching(Class clazz, Map<String, ?> positiveFieldValues, Map<String, ?> negativeFieldValues) {
        return 0;
    }

    public int countMatching(Class clazz, Map<String, ?> fieldValues) {
        return 0;
    }

    public void delete(List<? extends PersistableBusinessObject> boList) {
    }

    public void delete(Object bo) {
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

    public void linkUserFields(Object bo) {
    }

    public PersistableBusinessObject manageReadOnly(PersistableBusinessObject bo) {
        return null;
    }

    public PersistableBusinessObject retrieve(Object object) {
        return null;
    }

    public List<? extends PersistableBusinessObject> save(List<? extends PersistableBusinessObject> businessObjects) {
        return null;
    }

    public PersistableBusinessObject save(PersistableBusinessObject bo) {
        return null;
    }
    
}
