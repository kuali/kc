/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.award;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.home.AwardAmountInfo;

import java.util.ArrayList;
import java.util.List;

public class AwardAmountInfoServiceImplTest {

    AwardAmountInfoServiceImpl awardAmountInfoServiceImpl;
    
    @Before
    public void setUp() throws Exception {
        awardAmountInfoServiceImpl = new AwardAmountInfoServiceImpl();
    }

    @After
    public void tearDown() throws Exception {
        awardAmountInfoServiceImpl = null;
    }

    @Test
    public void testFetchAwardAmountInfoWithHighestTransactionId(){
        List<AwardAmountInfo> awardAmountInfos = new ArrayList<AwardAmountInfo>();
        addAwardAmountInfos(awardAmountInfos, 1);
        addAwardAmountInfos(awardAmountInfos, 4);
        addAwardAmountInfos(awardAmountInfos, 3);
        addAwardAmountInfos(awardAmountInfos, 13);
        addAwardAmountInfos(awardAmountInfos, 32);
        addAwardAmountInfos(awardAmountInfos, 115);
        Assert.assertEquals(new Long(115), awardAmountInfoServiceImpl.fetchAwardAmountInfoWithHighestTransactionId(awardAmountInfos).getAwardAmountInfoId());
    }
    
    private void addAwardAmountInfos(List<AwardAmountInfo> awardAmountInfos, int awardAmountInfoId) {
        AwardAmountInfo newAwardAmountInfo = new AwardAmountInfo();
        newAwardAmountInfo.setAwardAmountInfoId(new Long(awardAmountInfoId));
        awardAmountInfos.add(newAwardAmountInfo);
    }

}
