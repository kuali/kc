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
package org.kuali.coeus.common.impl.print.watermark;

import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class for storing the details of watermark Color.
 */
public class WatermarkColorValuesFinder extends UifKeyValuesFinderBase {
    /**
     * This method for storing lookup keyvalues of watermark font Color.
     * 
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue("LIGHT_GRAY", "LIGHT_GRAY"));
        keyValues.add(new ConcreteKeyValue("BLACK", "BLACK"));
        keyValues.add(new ConcreteKeyValue("BLUE", "BLUE"));
        keyValues.add(new ConcreteKeyValue("MAGENTA", "MAGENTA"));
        keyValues.add(new ConcreteKeyValue("CYAN", "CYAN"));
        keyValues.add(new ConcreteKeyValue("ORANGE", "ORANGE"));
        keyValues.add(new ConcreteKeyValue("DARK_GRAY", "DARK_GRAY"));
        keyValues.add(new ConcreteKeyValue("PINK", "PINK"));
        keyValues.add(new ConcreteKeyValue("GRAY", "GRAY"));
        keyValues.add(new ConcreteKeyValue("RED", "RED"));
        keyValues.add(new ConcreteKeyValue("GREEN", "GREEN"));
        keyValues.add(new ConcreteKeyValue("WHITE", "WHITE"));
        keyValues.add(new ConcreteKeyValue("YELLOW", "YELLOW"));

        return keyValues;
    }


}
