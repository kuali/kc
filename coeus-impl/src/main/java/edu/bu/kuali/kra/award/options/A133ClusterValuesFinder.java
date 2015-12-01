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
 * BUKC-0002: Field A133 Cluster on Award Module under Award Tab  on Panel Detail&dates under sub panel Project.
 */
public class A133ClusterValuesFinder extends UifKeyValuesFinderBase {

    /**
     * @see org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase#()
     */
    public List getKeyValues() {
        List<ConcreteKeyValue> keyValues = new ArrayList<ConcreteKeyValue>();
        keyValues.add(0, new ConcreteKeyValue("M", "Major"));
        keyValues.add(1, new ConcreteKeyValue("N", "Non-major"));
        return keyValues;
    }
}
