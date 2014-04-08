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
package org.kuali.coeus.propdev.impl.location;

import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;
import org.kuali.rice.location.framework.state.StateValuesFinder;

import java.util.List;

public class CongDistrictStateCodeValuesFinder extends UifKeyValuesFinderBase {

    private String countryCode = "";

    @Override
    public List<KeyValue> getKeyValues() {
        StateValuesFinder svf = new StateValuesFinder();
        svf.setCountryCode(countryCode);

        List<KeyValue> labels = svf.getKeyValues();
        labels.add(1, new ConcreteKeyValue("US", "US"));
        labels.add(2, new ConcreteKeyValue("00", "00"));
        
        return labels;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
