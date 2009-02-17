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
import org.kuali.kra.service.AwardReportsService;

public class AwardReportsServiceImplTest extends AwardReportsServiceImpl{

    public static final String MOCK_EXPECTED_STRING = "1;test1,2;test2,3;test3,4;test4,5;test5";
    AwardReportsService awardReportsService;
    AwardReportsServiceImpl awardReportsServiceImpl;
    List<KeyLabelPair> keyLabelPairList;
    
    
    @Before
    public void setUp() throws Exception {
        awardReportsService = new AwardReportsServiceImpl();
        awardReportsServiceImpl = new AwardReportsServiceImpl();
        keyLabelPairList = new ArrayList<KeyLabelPair>();
        keyLabelPairList.add(new KeyLabelPair(1, "test1"));
        keyLabelPairList.add(new KeyLabelPair(2, "test2"));
        keyLabelPairList.add(new KeyLabelPair(3, "test3"));
        keyLabelPairList.add(new KeyLabelPair(4, "test4"));
        keyLabelPairList.add(new KeyLabelPair(5, "test5"));
    }

    @After
    public void tearDown() throws Exception {
        awardReportsService = null;
    }

    @Test
    public final void testProcessFrequencyBaseCodes() {
        Assert.assertEquals(MOCK_EXPECTED_STRING,
                awardReportsServiceImpl.processKeyLabelPairList(keyLabelPairList));
    }

}
