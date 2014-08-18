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
package org.kuali.kra.iacuc.committee.lookup.keyvalue;

import org.apache.commons.collections4.CollectionUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;

import java.util.ArrayList;
import java.util.List;

public class IacucCommitteeIdIdPairValuesFinder extends IacucCommitteeIdValuesFinder {


    private static final long serialVersionUID = -1856217969303750675L;
    
    /**
     * This override will return the active committee <id, id> pairs list as key-labels. 
     * 
     * @see org.kuali.coeus.common.committee.impl.lookup.keyvalue.CommitteeIdValuesFinderBase#getKeyValues()
     */
    @Override
    public List<KeyValue> getKeyValues() {        
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue("", "select"));
        
        List<CommitteeBase> committees = this.getActiveCommittees();
        if (CollectionUtils.isNotEmpty(committees)) {
            for (CommitteeBase committee : committees) {
                keyValues.add(new ConcreteKeyValue(committee.getCommitteeId(), committee.getCommitteeId()));
            }
        }
        return keyValues;
    }
    

    

}
