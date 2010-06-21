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
package org.kuali.kra.proposaldevelopment.lookup.keyvalue;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.lookup.keyvalue.ExtendedPersistableBusinessObjectValuesFinder;
import org.kuali.rice.core.util.KeyLabelPair;
import org.kuali.rice.kns.bo.State;
import org.kuali.rice.kns.service.StateService;

public class CongDistrictStateCodeValuesFinder extends ExtendedPersistableBusinessObjectValuesFinder {

    @Override
    public List<KeyLabelPair> getKeyValues() {
        List<State> codes = KraServiceLocator.getService(StateService.class).findAllStates();
        List<KeyLabelPair> labels = new ArrayList<KeyLabelPair>();
        labels.add(new KeyLabelPair("", ""));
        for (State state : codes) {
            if (state.isActive()) {
                labels.add(new KeyLabelPair(state.getPostalStateCode(), state.getPostalStateCode()));
            }
        }
        
        labels.add(1, new KeyLabelPair("US", "US"));
        labels.add(2, new KeyLabelPair("00", "00"));
        
        return labels;
    }
}
