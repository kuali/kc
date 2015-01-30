/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.common.framework.ynq;

import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class YnqDummyAnswersValuesFinder extends UifKeyValuesFinderBase {

    private static final List<KeyValue> KEY_VALUES;
    static {
        final List<KeyValue> temp = new ArrayList<>();
        temp.add(new ConcreteKeyValue("Y", "Yes"));
        temp.add(new ConcreteKeyValue("N", "No"));
        temp.add(new ConcreteKeyValue("X", "N/A"));
        KEY_VALUES = Collections.unmodifiableList(temp);
    }

    @Override
    public List<KeyValue> getKeyValues() {
        return KEY_VALUES;
    }
}

