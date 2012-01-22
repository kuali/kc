/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.lookup.keyvalue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.keyvalue.PersistableBusinessObjectValuesFinderTestBase;
import org.kuali.kra.lookup.keyvalue.ExtendedPersistableBusinessObjectValuesFinder;
import org.kuali.rice.core.api.util.ConcreteKeyValue;

/**
 * This class tests DeadlineTypeValuesFinder.
 */
public class DeadlineTypeValuesFinderTest extends PersistableBusinessObjectValuesFinderTestBase{
    
    public DeadlineTypeValuesFinderTest() {
        setValuesFinderClass(ExtendedPersistableBusinessObjectValuesFinder.class);
        setBusinessObjectClass(org.kuali.kra.proposaldevelopment.bo.DeadlineType.class);
        setKeyAttributeName("deadlineTypeCode");
        setLabelAttributeName("description");
        setIncludeKeyInDescription(false);
    }
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test public void testGetKeyValues() throws Exception {
        super.testGetKeyValues();
    }
    
    protected void addKeyValues() {
        testKeyValues.add(new ConcreteKeyValue("", "select"));
        testKeyValues.add(new ConcreteKeyValue("P", "Postmark"));
        testKeyValues.add(new ConcreteKeyValue("R", "Receipt"));
        testKeyValues.add(new ConcreteKeyValue("T", "Target"));
    }
}
