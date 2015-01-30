/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
