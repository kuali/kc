/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.common.committee.lookup.keyvalue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.kuali.kra.common.committee.bo.CommitteeDecisionMotionType;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;
import org.kuali.rice.krad.service.KeyValuesService;

/**
 * Returns all possible values for the Committee Decision Motion dropdown box.
 */
public class CommitteeDecisionMotionValuesFinder extends KeyValuesBase {
    
    private KeyValuesService keyValuesService;
    
    /**
     * {@inheritDoc}
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @SuppressWarnings("unchecked")
    public List<KeyValue> getKeyValues() {
        Collection<CommitteeDecisionMotionType> motionTypes = getKeyValuesService().findAll(CommitteeDecisionMotionType.class);
        
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue("", "select"));
        
        for (CommitteeDecisionMotionType motionType : motionTypes) {
            keyValues.add(new ConcreteKeyValue(motionType.getMotionTypeCode(), motionType.getDescription()));
        }
        
        return keyValues;
    }
    
    /**
     * 
     * This method returns an instance of CommitteeService.
     * @return KeyValuesService
     */
    public KeyValuesService getKeyValuesService() {
        if (this.keyValuesService == null) {
            this.keyValuesService = KraServiceLocator.getService(KeyValuesService.class);
        }
        return this.keyValuesService;
    }
}
