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

import org.junit.Test;
import org.kuali.coeus.common.framework.type.DeadlineType;
import org.kuali.kra.keyvalue.PersistableBusinessObjectValuesFinderTestBase;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.krad.keyvalues.PersistableBusinessObjectValuesFinder;

/**
 * This class tests DeadlineTypeValuesFinder.
 */
public class DeadlineTypeValuesFinderTest extends PersistableBusinessObjectValuesFinderTestBase{
    
    public DeadlineTypeValuesFinderTest() {
        setValuesFinderClass(PersistableBusinessObjectValuesFinder.class);
        setBusinessObjectClass(DeadlineType.class);
        setKeyAttributeName("deadlineTypeCode");
        setLabelAttributeName("description");
        setIncludeKeyInDescription(false);
    }

    @Test public void testGetKeyValues() throws Exception {
        super.testGetKeyValues();
    }
    
    protected void addKeyValues() {
        testKeyValues.add(new ConcreteKeyValue("P", "Postmark"));
        testKeyValues.add(new ConcreteKeyValue("R", "Receipt"));
        testKeyValues.add(new ConcreteKeyValue("T", "Target"));
    }
}
