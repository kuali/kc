/*
 * Copyright 2005-2010 The Kuali Foundation
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
import org.kuali.kra.bo.SponsorTerm;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;

/**
 * This class tests AwardSponsorTermService methods.
 */
public class AwardSponsorTermServiceImplTest extends AwardSponsorTermServiceImpl {

    private static final String ONE = "1";
    private static final String TWO= "2";

    private static final String TEST_STRING_ONE = "test1";
    private static final String TEST_STRING_TWO = "test2";
    
    AwardSponsorTermServiceImpl awardSponsorTermServiceImpl;
    List<KeyValue> KeyValueList;
    List<SponsorTerm> sponsorTerms;
    
    @Before
    public void setUp() throws Exception {
        awardSponsorTermServiceImpl = new AwardSponsorTermServiceImpl();
        KeyValueList = new ArrayList<KeyValue>();
        KeyValueList.add(new ConcreteKeyValue(ONE, TEST_STRING_ONE));
        KeyValueList.add(new ConcreteKeyValue(TWO, TEST_STRING_TWO));
    }

    @After
    public void tearDown() throws Exception {
        awardSponsorTermServiceImpl = null;
        KeyValueList = null;
    }

    @Test
    public final void testAddEmptyNewSponsorTerms() {
        sponsorTerms = awardSponsorTermServiceImpl.getEmptyNewSponsorTerms(KeyValueList);
        Assert.assertEquals(TWO, sponsorTerms.size());
    }
}
