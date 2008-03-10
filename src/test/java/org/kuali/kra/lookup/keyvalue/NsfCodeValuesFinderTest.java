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

/**
 * This class tests NsfCodeValuesFinder.
 */
public class NsfCodeValuesFinderTest extends PersistableBusinessObjectValuesFinderTestBase {

    public NsfCodeValuesFinderTest() {
        setValuesFinderClass(ExtendedPersistableBusinessObjectValuesFinder.class);
        setBusinessObjectClass(org.kuali.kra.bo.NsfCode.class);
        setKeyAttributeName("nsfCode");
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
        testKeyValues.add(new KeyLabelPair("J.02","Law - Non-Science and Engineering Fields: J.02"));
        testKeyValues.add(new KeyLabelPair("A.01","Aeronautical and Astronautical - Engineering: A.01"));
        testKeyValues.add(new KeyLabelPair("F.01","Agricultural - Life Sciences: F.01"));
        testKeyValues.add(new KeyLabelPair("B.01","Astronomy - Physical Sciences: B.01"));
        testKeyValues.add(new KeyLabelPair("C.01","Atmospheric - Environmental Sciences: C.01"));
        testKeyValues.add(new KeyLabelPair("A.02","Bioengineering/Biomedical - Engineering: A.02"));
        testKeyValues.add(new KeyLabelPair("F.02","Biological - Life Sciences: F.02"));
        testKeyValues.add(new KeyLabelPair("J.05","Business and Management - Non-Science and Engineering Fields: J.05"));
        testKeyValues.add(new KeyLabelPair("A.03","Chemical - Engineering: A.03"));
        testKeyValues.add(new KeyLabelPair("B.02","Chemistry - Physical Sciences: B.02"));
        testKeyValues.add(new KeyLabelPair("A.04","Civil - Engineering: A.04"));
        testKeyValues.add(new KeyLabelPair("J.06","Communications, Journalism and Library Sciences - Non-Science and Engineering Fields: J.06"));
        testKeyValues.add(new KeyLabelPair("E.01","Computer Sciences: E.01"));
        testKeyValues.add(new KeyLabelPair("Z.99","Costs not included in NSF report - used for reconciliation purposes: Z.99"));
        testKeyValues.add(new KeyLabelPair("J.98","Costs to be allocated: J.98"));
        testKeyValues.add(new KeyLabelPair("C.02","Earth Sciences - Environmental Sciences: C.02"));
        testKeyValues.add(new KeyLabelPair("H.01","Economics - Social Sciences: H.01"));
        testKeyValues.add(new KeyLabelPair("J.01","Education - Non-Science and Engineering Fields: J.01"));
        testKeyValues.add(new KeyLabelPair("A.05","Electrical - Engineering: A.05"));
        testKeyValues.add(new KeyLabelPair("J.03","Humanities - Non-Science and Engineering Fields: J.03"));
        testKeyValues.add(new KeyLabelPair("D.01","Mathematical Science: D.01"));
        testKeyValues.add(new KeyLabelPair("A.06","Mechanical  - Engineering: A.06"));
        testKeyValues.add(new KeyLabelPair("F.03","Medical - Life Sciences: F.03"));
        testKeyValues.add(new KeyLabelPair("A.07","Metallurgical and Materials  - Engineering: A.07"));
        testKeyValues.add(new KeyLabelPair("C.03","Oceanography - Environmental Sciences: C.03"));
        testKeyValues.add(new KeyLabelPair("A.99","Other - Engineering: A.99"));
        testKeyValues.add(new KeyLabelPair("C.99","Other - Environmental Sciences: C.99"));
        testKeyValues.add(new KeyLabelPair("F.99","Other - Life Sciences: F.99"));
        testKeyValues.add(new KeyLabelPair("J.99","Other - Non-Science and Engineering Fields: J.99"));
        testKeyValues.add(new KeyLabelPair("B.99","Other - Physical Sciences: B.99"));
        testKeyValues.add(new KeyLabelPair("H.99","Other - Social Sciences: H.99"));
        testKeyValues.add(new KeyLabelPair("I.01","Other Sciences, n.e.c.: I.01"));
        testKeyValues.add(new KeyLabelPair("B.03","Physics - Physical Sciences: B.03"));
        testKeyValues.add(new KeyLabelPair("H.02","Political Science - Social Sciences: H.02"));
        testKeyValues.add(new KeyLabelPair("G.01","Psychology: G.01"));
        testKeyValues.add(new KeyLabelPair("J.07","Social Work - Non-Science and Engineering Fields: J.07"));
        testKeyValues.add(new KeyLabelPair("H.03","Sociology - Social Sciences: H.03"));
        testKeyValues.add(new KeyLabelPair("J.04","Visual and Performing Arts - Non-Science and Engineering Fields: J.04"));
    }

}
