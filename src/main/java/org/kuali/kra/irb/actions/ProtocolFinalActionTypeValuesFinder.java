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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;

/**
 * 
 * This class creates key/values pair of ProtocolActionType that are valid as a final action for batch correspondence.
 */
public class ProtocolFinalActionTypeValuesFinder extends IrbActionsKeyValuesBase {

    private static final String FINAL_ACTION_FOR_BATCH_CORRESP_FIELD = "FINAL_ACTION_FOR_BATCH_CORRESP";
    private static final String DESCRIPTION_FIELD = "DESCRIPTION";

    /**
     * Build the list of KeyValues using the key (keyAttributeName) and
     * label (labelAttributeName) of the list of all business objects found
     * for the BO class specified along with a "no action" entry.
     * 
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();

        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(FINAL_ACTION_FOR_BATCH_CORRESP_FIELD, "Y");
        Collection<ProtocolActionType> protocolActionTypes = 
            this.getBusinessObjectService().findMatchingOrderBy(ProtocolActionType.class, fieldValues, DESCRIPTION_FIELD, true);
        
        keyValues.add(new ConcreteKeyValue("  ", "no action"));
        for (ProtocolActionType protocolActionType : protocolActionTypes) {
            keyValues.add(new ConcreteKeyValue(protocolActionType.getProtocolActionTypeCode(), protocolActionType.getDescription()));
        }
        
        return keyValues;
    }

}
