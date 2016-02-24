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
package org.kuali.kra.coi.lookup.keyvalue;

import org.apache.commons.collections4.CollectionUtils;
import org.kuali.coeus.common.framework.type.ProposalType;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.List;


public class CoiDisclosureProjectsProjectTypeValuesFinder extends UifKeyValuesFinderBase {


    private static final long serialVersionUID = -5160412959634597155L;

    /**
     * This method returns all active project types (ie proposal types) that qualify for a manual disclosure type.
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue("", "select"));

        List<ProposalType> proposalTypes = (List<ProposalType>) getBusinessObjectService().findAll(ProposalType.class);
        if (CollectionUtils.isNotEmpty(proposalTypes)) {
            for (ProposalType proposalType : proposalTypes) {
                keyValues.add(new ConcreteKeyValue(proposalType.getCode(), proposalType.getDescription()));
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
