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
package org.kuali.kra.proposaldevelopment.lookup.keyvalue;

import org.kuali.coeus.propdev.impl.attachment.NarrativeStatusValuesFinder;
import org.kuali.kra.keyvalue.ValuesFinderTestBase;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;

import java.util.ArrayList;
import java.util.List;

/**
 * This class tests NsfCodeValuesFinder.
 */
public class NarrativeStatusValuesFinderTest extends ValuesFinderTestBase {

    @Override
    protected Class<NarrativeStatusValuesFinder> getTestClass() {
        return NarrativeStatusValuesFinder.class;
    }

    @Override
    protected List<KeyValue> getKeyValues() {
        final List<KeyValue> keylabel = new ArrayList<KeyValue>();
        
        keylabel.add(new ConcreteKeyValue("", "select"));
        keylabel.add(new ConcreteKeyValue("I","Incomplete"));
        keylabel.add(new ConcreteKeyValue("C","Complete"));
        
        return keylabel;
    }

}
