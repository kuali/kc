/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
