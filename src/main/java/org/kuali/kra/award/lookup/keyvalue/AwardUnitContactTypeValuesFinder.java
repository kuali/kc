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
package org.kuali.kra.award.lookup.keyvalue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.bo.UnitAdministratorType;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.core.util.KeyLabelPair;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.service.BusinessObjectService;

public class AwardUnitContactTypeValuesFinder extends KeyValuesBase {
    
    private BusinessObjectService businessObjectService;
    
    public AwardUnitContactTypeValuesFinder() {
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
    }

    /**
     * @see org.kuali.rice.kns.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @SuppressWarnings("unchecked")
    public List<KeyLabelPair> getKeyValues() {
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("defaultGroupFlag", "U");
        List<KeyLabelPair> result = new ArrayList<KeyLabelPair>();
        Collection<UnitAdministratorType> types = getBusinessObjectService().findMatching(UnitAdministratorType.class, values);
        for (UnitAdministratorType type : types) {
            KeyLabelPair pair = new KeyLabelPair();
            pair.setKey(type.getUnitAdministratorTypeCode());
            pair.setLabel(type.getDescription());
            result.add(pair);
        }
        return result;
    }
    
    protected BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
}
