/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.coi.lookup.keyvalue;

import org.apache.commons.collections4.CollectionUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.coi.CoiDisclosureEventType;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.List;

public class CoiDisclosureManualEventTypesValuesFinder extends UifKeyValuesFinderBase {


    private static final long serialVersionUID = -2760943363607401164L;

    /**
     * This method returns all active coi disclosure event types that qualify for a manual disclosure type
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue("", "select"));

        List<CoiDisclosureEventType> eventTypes = (List<CoiDisclosureEventType>) getBusinessObjectService().findAll(CoiDisclosureEventType.class);
        if (CollectionUtils.isNotEmpty(eventTypes)) {
            for (CoiDisclosureEventType eventType : eventTypes) {
                if (eventType.isActive() && !eventType.isSystemEvent()) {
                    keyValues.add(new ConcreteKeyValue(eventType.getEventTypeCode(), eventType.getDescription()));
                }
            }
        }
        
        return keyValues;
    }

    /**
     * 
     * This method returns a reference to the business object service
     * @return the business object service
     */
    private BusinessObjectService getBusinessObjectService() {
        return KcServiceLocator.getService(BusinessObjectService.class);
    }
}
