/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.award.lookup.keyvalue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.KraTestBase;


/**
 * This class tests that SponsorTermTypeValuesFinder getKeyValues returns a list<KeyLabelPair>
 * with 9 entries and that each entry has set keys and labels.
 */
public class SponsorTermTypeValuesFinderTest extends KraTestBase{
    private static final int NINE = 9;

    SponsorTermTypeValuesFinder sponsorTermTypeValuesFinder;
    List<KeyLabelPair> sponsorTermTypes;

    /**
     * @see org.kuali.kra.KraTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        sponsorTermTypeValuesFinder = new SponsorTermTypeValuesFinder();
        sponsorTermTypes = new ArrayList<KeyLabelPair>();
        sponsorTermTypes = sponsorTermTypeValuesFinder.getKeyValues();
    }

    /**
     * @see org.kuali.kra.KraTestBase#tearDown()
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        sponsorTermTypeValuesFinder = null;
        sponsorTermTypes = null;
    }

    /**
     * This method tests that the getKeyValues method returns the correct number of keyValues and that each entry is not null.
     */
    @Test
    public final void testGetKeyValues() {
        Assert.assertEquals(NINE,sponsorTermTypes.size());
        
        for(KeyLabelPair keyLabelPair:sponsorTermTypes){
            Assert.assertNotNull(keyLabelPair.getKey());
            Assert.assertNotNull(keyLabelPair.getLabel());
        }
    }
}
