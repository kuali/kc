/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.budget.lookup.keyvalue;

import org.kuali.kra.award.infrastructure.AwardOnOffCampusFlagConstants;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This is a values finder class used in AwardIndirectCostRate.xml for populating
 * On-Off CampusContractContractContract Flag Select box.
 */
public class AwardOnOffCampusValuesFinder extends UifKeyValuesFinderBase {

    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> KeyValues = new ArrayList<KeyValue>();

        for (AwardOnOffCampusFlagConstants awardOnOffCampusFlagConstants : AwardOnOffCampusFlagConstants.values()) {
            KeyValues.add(new ConcreteKeyValue(awardOnOffCampusFlagConstants.code(), awardOnOffCampusFlagConstants.description()));
        }
        
        return KeyValues; 
    }
    
}
