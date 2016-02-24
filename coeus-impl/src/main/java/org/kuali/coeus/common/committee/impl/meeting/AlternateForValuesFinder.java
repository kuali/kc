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
package org.kuali.coeus.common.committee.impl.meeting;

import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class is to set up the 'Alternate For' drop down list for members who have 'Alternate' role.
 */
public class AlternateForValuesFinder extends UifKeyValuesFinderBase {

    private static final String MEMBER_SEPARATOR = "#m#";
    private static final String FIELD_SEPARATOR = "#f#";
    private String absenteeList;

    @Override
    public List<KeyValue> getKeyValues() {

        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        for (String idName : absenteeList.split(MEMBER_SEPARATOR)) {
            String[] valuePair = idName.split(FIELD_SEPARATOR);
            keyValues.add(new ConcreteKeyValue(valuePair[0], valuePair[1]));
        }
        keyValues.add(0, new ConcreteKeyValue("", "select"));
        return keyValues;
    }

    public String getAbsenteeList() {
        return absenteeList;
    }

    public void setAbsenteeList(String absenteeList) {
        this.absenteeList = absenteeList;
    }

}
