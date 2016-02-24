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
package org.kuali.coeus.sys.impl.persistence;

import org.apache.ojb.broker.metadata.ClassDescriptor;
import org.apache.ojb.broker.metadata.FieldDescriptor;
import org.apache.ojb.broker.metadata.ObjectReferenceDescriptor;
import org.kuali.coeus.sys.framework.persistence.KcPersistenceStructureService;
import org.kuali.rice.krad.bo.DataObjectRelationship;
import org.kuali.rice.krad.exception.ClassNotPersistableException;
import org.kuali.rice.krad.service.PersistenceStructureService;
import org.kuali.rice.krad.service.impl.PersistenceStructureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("kcPersistenceStructureService")
public class KcPersistenceStructureServiceImpl extends PersistenceStructureServiceImpl implements KcPersistenceStructureService {

    public Map<String, String> getDBColumnToObjectAttributeMap(Class clazz) throws ClassNotPersistableException {
        Map<String, String> fieldMap = new HashMap<String, String>();
        if(isPersistable(clazz)) {
            ClassDescriptor classDescriptor = getClassDescriptor(clazz);
            FieldDescriptor fieldDescriptors[] = classDescriptor.getFieldDescriptions();
            for(FieldDescriptor fieldDescriptor : fieldDescriptors) {
                fieldMap.put(fieldDescriptor.getColumnName(), fieldDescriptor.getAttributeName());
            }
            return fieldMap;
        } 
        
        throw new ClassNotPersistableException(clazz.getName() + " is not Persistable");
    }

    public Map<String, String> getPersistableAttributesColumnMap(Class clazz) throws ClassNotPersistableException {
        Map<String, String> fieldMap = new HashMap<String, String>();
        if(isPersistable(clazz)) {
            ClassDescriptor classDescriptor = getClassDescriptor(clazz);
            FieldDescriptor fieldDescriptors[] = classDescriptor.getFieldDescriptions();
            for(FieldDescriptor fieldDescriptor : fieldDescriptors) {
                fieldMap.put(fieldDescriptor.getAttributeName(), fieldDescriptor.getColumnName());
            }
            return fieldMap;
        } 
        
        throw new ClassNotPersistableException(clazz.getName() + " is not Persistable");
     }

    @Override
    public List<DataObjectRelationship> getRelationshipsTo(Class persistableClass) throws ClassNotPersistableException {
        if(!isPersistable(persistableClass)) {
            throw new ClassNotPersistableException(persistableClass.getName() + " is not persistable");
        }

        final List<DataObjectRelationship> relationships = new ArrayList<>();

        @SuppressWarnings("unchecked")
        final Set<Map.Entry<String, ClassDescriptor>> entries = (Set<Map.Entry<String, ClassDescriptor>>) getDescriptorRepository().getDescriptorTable().entrySet();
        for (Map.Entry<String, ClassDescriptor> entry : entries) {
            @SuppressWarnings("unchecked")
            final Collection<ObjectReferenceDescriptor> references = entry.getValue().getObjectReferenceDescriptors();
            if (references != null) {
                for (ObjectReferenceDescriptor reference : references) {
                    if (reference.getItemClass().equals(persistableClass)) {
                        final DataObjectRelationship relationship = new DataObjectRelationship(entry.getValue().getClassOfObject(), reference.getAttributeName(), reference.getItemClass());
                        final Map<String, String> pToC = new HashMap<>();
                        for (int i = 0; i < reference.getForeignKeyFields().size(); i++) {
                            final String fkField = (String) reference.getForeignKeyFields().get(i);
                            pToC.put(fkField, getDescriptorRepository().getDescriptorFor(reference.getItemClass()).getPkFields()[i].getAttributeName());
                        }
                        relationship.setParentToChildReferences(pToC);
                        relationships.add(relationship);
                    }
                }
            }
        }

        return relationships;
    }

    @Autowired
    @Qualifier("persistenceStructureServiceOjb")
    @Override
    public void setPersistenceStructureServiceOjb(PersistenceStructureService persistenceStructureServiceOjb) {
        super.setPersistenceStructureServiceOjb(persistenceStructureServiceOjb);
    }
}
