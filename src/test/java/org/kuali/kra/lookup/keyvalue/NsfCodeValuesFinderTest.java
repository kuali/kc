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
package org.kuali.kra.lookup.keyvalue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.keyvalue.PersistableBusinessObjectValuesFinderTestBase;
import org.kuali.rice.core.api.util.ConcreteKeyValue;

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
        testKeyValues.add(new ConcreteKeyValue("", "select"));
        testKeyValues.add(new ConcreteKeyValue("J.02","Law - Non-Science and Engineering Fields: J.02"));
        testKeyValues.add(new ConcreteKeyValue("A.01","Aeronautical and Astronautical - Engineering: A.01"));
        testKeyValues.add(new ConcreteKeyValue("F.01","Agricultural - Life Sciences: F.01"));
        testKeyValues.add(new ConcreteKeyValue("B.01","Astronomy - Physical Sciences: B.01"));
        testKeyValues.add(new ConcreteKeyValue("C.01","Atmospheric - Environmental Sciences: C.01"));
        testKeyValues.add(new ConcreteKeyValue("A.02","Bioengineering/Biomedical - Engineering: A.02"));
        testKeyValues.add(new ConcreteKeyValue("F.02","Biological - Life Sciences: F.02"));
        testKeyValues.add(new ConcreteKeyValue("J.05","Business and Management - Non-Science and Engineering Fields: J.05"));
        testKeyValues.add(new ConcreteKeyValue("A.03","Chemical - Engineering: A.03"));
        testKeyValues.add(new ConcreteKeyValue("B.02","Chemistry - Physical Sciences: B.02"));
        testKeyValues.add(new ConcreteKeyValue("A.04","Civil - Engineering: A.04"));
        testKeyValues.add(new ConcreteKeyValue("J.06","Communications, Journalism and Library Sciences - Non-Science and Engineering Fields: J.06"));
        testKeyValues.add(new ConcreteKeyValue("E.01","Computer Sciences: E.01"));
        testKeyValues.add(new ConcreteKeyValue("Z.99","Costs not included in NSF report - used for reconciliation purposes: Z.99"));
        testKeyValues.add(new ConcreteKeyValue("J.98","Costs to be allocated: J.98"));
        testKeyValues.add(new ConcreteKeyValue("C.02","Earth Sciences - Environmental Sciences: C.02"));
        testKeyValues.add(new ConcreteKeyValue("H.01","Economics - Social Sciences: H.01"));
        testKeyValues.add(new ConcreteKeyValue("J.01","Education - Non-Science and Engineering Fields: J.01"));
        testKeyValues.add(new ConcreteKeyValue("A.05","Electrical - Engineering: A.05"));
        testKeyValues.add(new ConcreteKeyValue("J.03","Humanities - Non-Science and Engineering Fields: J.03"));
        testKeyValues.add(new ConcreteKeyValue("D.01","Mathematical Science: D.01"));
        testKeyValues.add(new ConcreteKeyValue("A.06","Mechanical  - Engineering: A.06"));
        testKeyValues.add(new ConcreteKeyValue("F.03","Medical - Life Sciences: F.03"));
        testKeyValues.add(new ConcreteKeyValue("A.07","Metallurgical and Materials  - Engineering: A.07"));
        testKeyValues.add(new ConcreteKeyValue("C.03","Oceanography - Environmental Sciences: C.03"));
        testKeyValues.add(new ConcreteKeyValue("A.99","Other - Engineering: A.99"));
        testKeyValues.add(new ConcreteKeyValue("C.99","Other - Environmental Sciences: C.99"));
        testKeyValues.add(new ConcreteKeyValue("F.99","Other - Life Sciences: F.99"));
        testKeyValues.add(new ConcreteKeyValue("J.99","Other - Non-Science and Engineering Fields: J.99"));
        testKeyValues.add(new ConcreteKeyValue("B.99","Other - Physical Sciences: B.99"));
        testKeyValues.add(new ConcreteKeyValue("H.99","Other - Social Sciences: H.99"));
        testKeyValues.add(new ConcreteKeyValue("I.01","Other Sciences, n.e.c.: I.01"));
        testKeyValues.add(new ConcreteKeyValue("B.03","Physics - Physical Sciences: B.03"));
        testKeyValues.add(new ConcreteKeyValue("H.02","Political Science - Social Sciences: H.02"));
        testKeyValues.add(new ConcreteKeyValue("G.01","Psychology: G.01"));
        testKeyValues.add(new ConcreteKeyValue("J.07","Social Work - Non-Science and Engineering Fields: J.07"));
        testKeyValues.add(new ConcreteKeyValue("H.03","Sociology - Social Sciences: H.03"));
        testKeyValues.add(new ConcreteKeyValue("J.04","Visual and Performing Arts - Non-Science and Engineering Fields: J.04"));
    }

}
