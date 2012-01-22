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
package org.kuali.kra.institutionalproposal.contacts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.kuali.kra.bo.UnitAdministratorType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.lookup.keyvalue.ExtendedPersistableBusinessObjectValuesFinder;
import org.kuali.kra.lookup.keyvalue.PrefixValuesFinder;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.BusinessObjectService;

public class InstitutionalProposalUnitContactsProjectRoleValuesFinder extends ExtendedPersistableBusinessObjectValuesFinder {
    
    @Override
    @SuppressWarnings("unchecked")
    public List<KeyValue> getKeyValues(){
        BusinessObjectService boService = KraServiceLocator.getService(BusinessObjectService.class);
        Collection<UnitAdministratorType> types = (Collection<UnitAdministratorType>) boService.findAll(UnitAdministratorType.class);
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        for (UnitAdministratorType aType: types) {
            if ( aType.getDefaultGroupFlag().equals(Constants.UNIT_CONTACTS_DEFAULT_GROUP_FLAG)) {    // only get Unit Contacts
                keyValues.add(new ConcreteKeyValue(aType.getUnitAdministratorTypeCode(), aType.getDescription()));
            }
        }
        keyValues.add(0, new ConcreteKeyValue(PrefixValuesFinder.getPrefixKey(), PrefixValuesFinder.getDefaultPrefixValue()));    
        
        return keyValues;
    }
}
