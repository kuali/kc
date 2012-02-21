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
package org.kuali.kra.coi.lookup.keyvalue;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.ProposalType;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;
import org.kuali.rice.krad.service.BusinessObjectService;


public class CoiDisclosureProjectsProjectTypeValuesFinder extends KeyValuesBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
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
                keyValues.add(new ConcreteKeyValue(proposalType.getProposalTypeCode(), proposalType.getDescription()));
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
        return KraServiceLocator.getService(BusinessObjectService.class);
    }    
}
