/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.committee.impl.meeting;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AttachmentsTypeValuesFinder extends UifKeyValuesFinderBase {
    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        for ( AttachmentsEntryType attachmentsEntryType : getAttachmentsEntryTypes()) {
                  keyValues.add(new ConcreteKeyValue(attachmentsEntryType.getAttachmentsTypeCode(), attachmentsEntryType.getDescription()));
        }
        keyValues.add(0, new ConcreteKeyValue("", "select"));
        return keyValues;
        
    }
    
    private List<AttachmentsEntryType> getAttachmentsEntryTypes(){
        List<AttachmentsEntryType> entryTypes = (List<AttachmentsEntryType>) KcServiceLocator.getService(BusinessObjectService.class)
                .findAll(AttachmentsEntryType.class);
       Collections.sort(entryTypes);
       return entryTypes;
    }
    
}
