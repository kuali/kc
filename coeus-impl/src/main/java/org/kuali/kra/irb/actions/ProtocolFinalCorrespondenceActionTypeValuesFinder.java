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

import org.kuali.kra.irb.correspondence.ProtocolCorrespondenceType;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
