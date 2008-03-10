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
package org.kuali.kra.proposaldevelopment.lookup.keyvalue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.UserSession;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.keyvalue.ValuesFinderTestBase;

/**
 * This class tests LeadUnitValuesFinder.
 */
public class LeadUnitValuesFinderTest extends ValuesFinderTestBase {

    public LeadUnitValuesFinderTest() {
        setTestClass(LeadUnitValuesFinder.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        GlobalVariables.setUserSession(null);
    }

    @Test public void testGetKeyValues() throws Exception {
        super.testGetKeyValues();
    }

    @Override
    protected void addKeyValues() {
        testKeyValues.add(new KeyLabelPair("", "select"));
        testKeyValues.add(new KeyLabelPair("000001", "000001 - University"));
        testKeyValues.add(new KeyLabelPair("IN-CARD", "IN-CARD - CARDIOLOGY"));
        testKeyValues.add(new KeyLabelPair("IN-CARR", "IN-CARR - CARDIOLOGY RECHARGE CTR"));
        testKeyValues.add(new KeyLabelPair("BL-IIDC", "BL-IIDC - IND INST ON DISABILITY/COMMNTY"));
    }

}
