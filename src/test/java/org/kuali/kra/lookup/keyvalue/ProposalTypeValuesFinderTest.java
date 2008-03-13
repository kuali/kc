/*
 * Copyright 2007 The Kuali Foundation.
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.lookup.keyvalue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.keyvalue.PersistableBusinessObjectValuesFinderTestBase;
import org.kuali.kra.keyvalue.ValuesFinderTestBase;

/**
 * This class tests ProposalTypeValuesFinder.
 */
public class ProposalTypeValuesFinderTest extends PersistableBusinessObjectValuesFinderTestBase {

    public ProposalTypeValuesFinderTest() {
        setValuesFinderClass(ExtendedPersistableBusinessObjectValuesFinder.class);
        setBusinessObjectClass(org.kuali.kra.proposaldevelopment.bo.ProposalType.class);
        setKeyAttributeName("proposalTypeCode");
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

    @Override
    protected void addKeyValues() {
        testKeyValues.add(new KeyLabelPair("", "select"));
        testKeyValues.add(new KeyLabelPair("1", "New"));
        testKeyValues.add(new KeyLabelPair("2", "Continuation"));
        testKeyValues.add(new KeyLabelPair("3", "Renewal"));
        testKeyValues.add(new KeyLabelPair("4", "Resubmission"));
        testKeyValues.add(new KeyLabelPair("5", "Revision"));
        testKeyValues.add(new KeyLabelPair("6", "Task Order"));
    }

}
