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
package org.kuali.kra.negotiations.keyvalue;

import org.kuali.coeus.sys.framework.keyvalue.PrefixValuesFinder;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.negotiations.bo.NegotiationAssociationType;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class NegotiationAssociationTypeValuesFinder extends UifKeyValuesFinderBase {
    
    private BusinessObjectService businessObjectService;
    /**
     * Filter results based on whether the association is enabled.
     * @see org.kuali.rice.krad.keyvalues.PersistableBusinessObjectValuesFinder#getKeyValues()
     */
    @Override
    public List<KeyValue> getKeyValues() {
        List<ConcreteKeyValue> labels = new ArrayList<ConcreteKeyValue>();
        Collection<NegotiationAssociationType> associations = getBusinessObjectService().findAll(NegotiationAssociationType.class);
        for (NegotiationAssociationType type : associations) {
            if (type.isActive()){
                labels.add(new ConcreteKeyValue(type.getId().toString(), type.getDescription()));
            }
        }
        Collections.sort(labels);
        labels.add(0, new ConcreteKeyValue(PrefixValuesFinder.getPrefixKey(), PrefixValuesFinder.getDefaultPrefixValue()));
        
        List<KeyValue> returnLabels = new ArrayList<KeyValue>();
        returnLabels.addAll(labels);
        
        return returnLabels;
    }

    protected BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
}
