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
package org.kuali.kra.irb.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.kuali.kra.irb.correspondence.ProtocolCorrespondenceType;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;

/**
 * This class creates the key/values pair ProtocolCorrespondenceType that are valid as a final correspondence 
 * action for batch correspondence.
 */
public class ProtocolFinalCorrespondenceActionTypeValuesFinder extends IrbActionsKeyValuesBase {
    
    /**
     * Build the list of KeyValues using the key (keyAttributeName) and
     * label (labelAttributeName) of the list of all business objects found
     * for the BO class specified along with a "no correspondence action" entry.
     * 
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        
        Collection<ProtocolCorrespondenceType> protocolCorrespondenceTypes = this.getBusinessObjectService().findAll(ProtocolCorrespondenceType.class);

        keyValues.add(new ConcreteKeyValue("  ", "no correspondence action"));
        for (ProtocolCorrespondenceType protocolCorrespondenceType : protocolCorrespondenceTypes) {
            keyValues.add(new ConcreteKeyValue(protocolCorrespondenceType.getProtoCorrespTypeCode(), protocolCorrespondenceType.getDescription()));
        }
        
        return keyValues;
    }

}
