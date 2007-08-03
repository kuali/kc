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
import org.kuali.kra.keyvalue.ValuesFinderTestBase;

/**
 * This class tests NsfCodeValuesFinder.
 */
public class NsfCodeValuesFinderTest extends ValuesFinderTestBase {

    public NsfCodeValuesFinderTest() {
        setTestClass(NsfCodeValuesFinder.class);
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
        testKeyValues.add(new KeyLabelPair("", "select:"));
        testKeyValues.add(new KeyLabelPair("J.02", "Law - Non-Science and Engineering Fields: J.02"));
        testKeyValues.add(new KeyLabelPair("A.01", "Aeronautical and Astronautical - Engineering: A.01"));
        testKeyValues.add(new KeyLabelPair("F.01", "Agricultural - Life Sciences: F.01"));
        testKeyValues.add(new KeyLabelPair("B.01", "Astronomy - Physical Sciences: B.01"));
        testKeyValues.add(new KeyLabelPair("C.01", "Atmospheric - Environmental Sciences: C.01"));
        testKeyValues.add(new KeyLabelPair("A.02", "Bioengineering/Biomedical - Engineering: A.02"));
        testKeyValues.add(new KeyLabelPair("F.02", "Biological - Life Sciences: F.02"));
    }

}
