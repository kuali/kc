/*
 * Copyright (c) 2014. Boston University
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND either express or
 * implied.
 *
 * See the License for the specific language governing permissions and limitations under the License.
 */

package edu.bu.kuali.kra.award.options;

import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.List;

/**
 * BUKC-0002: Child Type field on Award Module under Award Tab on Panel Detail&dates under sub panel Institution.
 */
public class ChildTypeValuesFinder extends UifKeyValuesFinderBase {

    /**
     * @see org.kuali.rice.krad.keyvalues.KeyValuesBase#getKeyValues()
     */
    public List getKeyValues() {
        List<ConcreteKeyValue> keyValues = new ArrayList<ConcreteKeyValue>();
        keyValues.add(0, new ConcreteKeyValue("", ""));
        keyValues.add(1, new ConcreteKeyValue("Standard", "Standard"));
        keyValues.add(2, new ConcreteKeyValue("Participant Support",
                "Participant Support"));
        keyValues.add(3, new ConcreteKeyValue("Costs", "Costs"));
        keyValues.add(4, new ConcreteKeyValue("Fabricated Equipment",
                "Fabricated Equipment"));
        keyValues.add(5, new ConcreteKeyValue("Group", "Group"));

        // BUKC-0113: Add new Child Types: Subaward, Cost Sharing, and Program Income (ENHC0012536)
        keyValues.add(6, new ConcreteKeyValue("Subaward", "Subaward"));
        keyValues.add(7, new ConcreteKeyValue("Cost Sharing", "Cost Sharing"));
        keyValues.add(8, new ConcreteKeyValue("Program Income", "Program Income"));

        return keyValues;
    }
}
