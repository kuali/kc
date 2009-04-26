/*
 * Copyright 2006-2008 The Kuali Foundation
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
import org.kuali.kra.keyvalue.ValuesFinderTestBase;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

/**
 * This class tests NoticeOfOpportunityValuesFinder.
 */
public class NoticeOfOpportunityValuesFinderTest extends ValuesFinderTestBase {

    public NoticeOfOpportunityValuesFinderTest() {
        setTestClass(NoticeOfOpportunityValuesFinder.class);
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
        testKeyValues.add(new KeyLabelPair("1", "Federal Solicitation"));
        testKeyValues.add(new KeyLabelPair("2", "Unsolicited"));
        testKeyValues.add(new KeyLabelPair("3", "Verbal Request for Proposal"));
        testKeyValues.add(new KeyLabelPair("4", "SBIR Solicitation"));
        testKeyValues.add(new KeyLabelPair("5", "STTR Solicitation"));
        testKeyValues.add(new KeyLabelPair("6", "Non-Federal Solicitation"));
        testKeyValues.add(new KeyLabelPair("7", "MIT Alliance/Internal"));

    }

}
