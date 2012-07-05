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
package org.kuali.kra.iacuc.actions.decision;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.common.committee.lookup.keyvalue.CommitteeDecisionMotionValuesFinder;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;

public class IacucCommitteeDecisionMotionValuesFinder extends CommitteeDecisionMotionValuesFinder {
    
    public static final String APPROVE = "1";
    public static final String DISAPPROVE = "2";
    public static final String SMR = "3";
    public static final String SRR = "4";

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -449881228899486120L;
    
    /*
     * We replace the default descriptions of SRR and SMR with IACUC-specific labels. 
     */
    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> originalList = super.getKeyValues();
        List<KeyValue> newList = new ArrayList<KeyValue>();
        
        for(KeyValue originalKV: originalList) {
            if(SMR.equals(originalKV.getKey())) {
                newList.add(new ConcreteKeyValue(SMR, "Minor revisions"));
            }
            else if(SRR.equals(originalKV.getKey())) {
                newList.add(new ConcreteKeyValue(SRR, "Major revisions"));
            }
            else {
                newList.add(originalKV); 
            }
        }
        
        return newList;
    }

}
