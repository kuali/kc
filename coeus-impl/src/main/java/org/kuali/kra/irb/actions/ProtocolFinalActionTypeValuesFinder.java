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
package org.kuali.kra.irb.actions;

import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;

import java.util.*;

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
