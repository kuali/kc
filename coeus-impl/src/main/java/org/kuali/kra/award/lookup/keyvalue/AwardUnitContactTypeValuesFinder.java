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
package org.kuali.kra.award.lookup.keyvalue;

import org.kuali.coeus.common.framework.unit.admin.UnitAdministratorType;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.*;

public class AwardUnitContactTypeValuesFinder extends UifKeyValuesFinderBase {
    
    private BusinessObjectService businessObjectService;
    
    public AwardUnitContactTypeValuesFinder() {
        businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
    }

    @Override
    public List<KeyValue> getKeyValues() {
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("defaultGroupFlag", "U");
        List<KeyValue> result = new ArrayList<KeyValue>();
        Collection<UnitAdministratorType> types = getBusinessObjectService().findMatching(UnitAdministratorType.class, values);
        for (UnitAdministratorType type : types) {
            ConcreteKeyValue pair = new ConcreteKeyValue();
            pair.setKey(type.getCode());
            pair.setValue(type.getDescription());
            result.add(pair);
        }
        return result;
    }
    
    protected BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
}
