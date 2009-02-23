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
package org.kuali.kra.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.bo.SponsorTerm;

/**
 * This class tests AwardSponsorTermService methods.
 */
public class AwardSponsorTermServiceImplTest extends AwardSponsorTermServiceImpl {

    private static final int ONE = 1;
    private static final int TWO= 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;

    private static final String TEST_STRING_ONE = "test1";
    private static final String TEST_STRING_TWO = "test2";
    private static final String TEST_STRING_THREE = "test3";
    private static final String TEST_STRING_FOUR = "test4";
    private static final String TEST_STRING_FIVE = "test5";

    
    AwardSponsorTermServiceImpl awardSponsorTermServiceImpl;
    List<KeyLabelPair> keyLabelPairList;
    List<SponsorTerm> sponsorTerms;
    
    @Before
    public void setUp() throws Exception {
        awardSponsorTermServiceImpl = new AwardSponsorTermServiceImpl();
        keyLabelPairList = new ArrayList<KeyLabelPair>();
        keyLabelPairList.add(new KeyLabelPair(ONE, TEST_STRING_ONE));
        keyLabelPairList.add(new KeyLabelPair(TWO, TEST_STRING_TWO));
        keyLabelPairList.add(new KeyLabelPair(THREE, TEST_STRING_THREE));
        keyLabelPairList.add(new KeyLabelPair(FOUR, TEST_STRING_FOUR));
        keyLabelPairList.add(new KeyLabelPair(FIVE, TEST_STRING_FIVE));
    }

    @After
    public void tearDown() throws Exception {
        awardSponsorTermServiceImpl = null;
        keyLabelPairList = null;
    }

    @Test
    public final void testAddEmptyNewSponsorTerms() {
        sponsorTerms = awardSponsorTermServiceImpl.addEmptyNewSponsorTerms(keyLabelPairList);
        Assert.assertEquals(FIVE, sponsorTerms.size());
    }
}
