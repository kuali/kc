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
package org.kuali.kra.committee.lookup.keyvalue;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.rice.core.util.KeyLabelPair;

public class CommitteeIdIdPairValuesFinder extends CommitteeIdValuesFinder {

    /**
     * This override will return the active committee <id, id> pairs list as key-labels. 
     * 
     * @see org.kuali.kra.committee.lookup.keyvalue.CommitteeIdValuesFinder#getKeyValues()
     */
    @Override
    public List<KeyLabelPair> getKeyValues() {        
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        keyValues.add(new KeyLabelPair("", "select"));
        
        List<Committee> committees = this.getActiveCommittees();
        if (CollectionUtils.isNotEmpty(committees)) {
            for (Committee committee : committees) {
                keyValues.add(new KeyLabelPair(committee.getCommitteeId(), committee.getCommitteeId()));
            }
        }
        return keyValues;
    }
    

}
