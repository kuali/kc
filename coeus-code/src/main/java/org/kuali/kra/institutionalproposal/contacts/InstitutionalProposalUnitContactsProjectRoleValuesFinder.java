/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.kuali.coeus.common.framework.unit.admin.UnitAdministratorType;
import org.kuali.coeus.sys.framework.keyvalue.PrefixValuesFinder;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class InstitutionalProposalUnitContactsProjectRoleValuesFinder extends UifKeyValuesFinderBase {

    @Override
    public List<KeyValue> getKeyValues() {
        BusinessObjectService boService = KcServiceLocator.getService(BusinessObjectService.class);
        Collection<UnitAdministratorType> types = (Collection<UnitAdministratorType>) boService.findAll(UnitAdministratorType.class);
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        for (UnitAdministratorType aType: types) {
            if ( aType.getDefaultGroupFlag().equals(Constants.UNIT_CONTACTS_DEFAULT_GROUP_FLAG)) {    // only get Unit Contacts
                keyValues.add(new ConcreteKeyValue(aType.getCode(), aType.getDescription()));
            }
        }
        keyValues.add(0, new ConcreteKeyValue(PrefixValuesFinder.getPrefixKey(), PrefixValuesFinder.getDefaultPrefixValue()));    
        
        return keyValues;
    }
}
