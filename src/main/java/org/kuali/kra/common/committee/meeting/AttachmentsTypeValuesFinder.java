/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.common.committee.meeting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.lookup.keyvalue.KeyValueFinderService;
import org.kuali.kra.proposaldevelopment.bo.ProposalColumnsToAlter;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;
import org.kuali.rice.krad.service.BusinessObjectService;

public class AttachmentsTypeValuesFinder extends KeyValuesBase {

    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        for ( AttachmentsEntryType attachmentsEntryType : getAttachmentsEntryTypes()) {
                  keyValues.add(new ConcreteKeyValue(attachmentsEntryType.getAttachmentsTypeCode(), attachmentsEntryType.getDescription()));
        }
        keyValues.add(0, new ConcreteKeyValue("", "select"));
        return keyValues;
        
    }
    
    private List<AttachmentsEntryType> getAttachmentsEntryTypes(){
        List<AttachmentsEntryType> entryTypes = (List<AttachmentsEntryType>) KraServiceLocator.getService(BusinessObjectService.class)
                .findAll(AttachmentsEntryType.class);
       Collections.sort(entryTypes);
       return entryTypes;
    }
    
}
