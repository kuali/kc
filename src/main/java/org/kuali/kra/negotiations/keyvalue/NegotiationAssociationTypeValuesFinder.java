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
package org.kuali.kra.negotiations.keyvalue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.lookup.keyvalue.ExtendedPersistableBusinessObjectValuesFinder;
import org.kuali.kra.lookup.keyvalue.PrefixValuesFinder;
import org.kuali.kra.negotiations.bo.NegotiationAssociationType;
import org.kuali.kra.negotiations.service.NegotiationService;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.BusinessObjectService;

public class NegotiationAssociationTypeValuesFinder extends ExtendedPersistableBusinessObjectValuesFinder {
    
    private BusinessObjectService businessObjectService;
    private NegotiationService negotiationService;

    /**
     * Filter results based on whether the association is enabled.
     * @see org.kuali.rice.krad.keyvalues.PersistableBusinessObjectValuesFinder#getKeyValues()
     */
    @SuppressWarnings("unchecked")
    public List<KeyValue> getKeyValues(){
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
            businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    protected NegotiationService getNegotiationService() {
        if (negotiationService == null) {
            negotiationService = KraServiceLocator.getService(NegotiationService.class);
        }
        return negotiationService;
    }

    public void setNegotiationService(NegotiationService negotiationService) {
        this.negotiationService = negotiationService;
    }
}
