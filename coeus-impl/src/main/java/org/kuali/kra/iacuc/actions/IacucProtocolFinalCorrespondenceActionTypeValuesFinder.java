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
package org.kuali.kra.iacuc.actions;

import org.kuali.coeus.common.committee.impl.service.CommitteeServiceBase;
import org.kuali.kra.iacuc.committee.service.IacucCommitteeService;
import org.kuali.kra.iacuc.correspondence.IacucProtocolCorrespondenceType;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IacucProtocolFinalCorrespondenceActionTypeValuesFinder extends IacucActionsKeyValuesBase {


    private static final long serialVersionUID = -7873988843510970100L;

    /**
     * Build the list of KeyValues using the key (keyAttributeName) and
     * label (labelAttributeName) of the list of all business objects found
     * for the BO class specified along with a "no correspondence action" entry.
     * 
     * {@inheritDoc}
     */
    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        
        Collection<IacucProtocolCorrespondenceType> protocolCorrespondenceTypes = this.getBusinessObjectService().findAll(IacucProtocolCorrespondenceType.class);

        keyValues.add(new ConcreteKeyValue("  ", "no correspondence action"));
        for (IacucProtocolCorrespondenceType protocolCorrespondenceType : protocolCorrespondenceTypes) {
            keyValues.add(new ConcreteKeyValue(protocolCorrespondenceType.getProtoCorrespTypeCode(), protocolCorrespondenceType.getDescription()));
        }
        
        return keyValues;
    }
    
    
    @Override
    protected Class<? extends CommitteeServiceBase> getCommitteeServiceClassHook() {
        return IacucCommitteeService.class;
    }

}
