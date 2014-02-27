/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.sys.impl.persistence;

import org.apache.ojb.broker.metadata.ClassDescriptor;
import org.apache.ojb.broker.metadata.FieldDescriptor;
import org.kuali.coeus.sys.framework.persistence.KcPersistenceStructureService;
import org.kuali.rice.krad.exception.ClassNotPersistableException;
import org.kuali.rice.krad.service.PersistenceStructureService;
import org.kuali.rice.krad.service.impl.PersistenceStructureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    @Qualifier("persistenceStructureServiceOjb")
    @Override
    public void setPersistenceStructureServiceOjb(PersistenceStructureService persistenceStructureServiceOjb) {
        super.setPersistenceStructureServiceOjb(persistenceStructureServiceOjb);
    }
}
