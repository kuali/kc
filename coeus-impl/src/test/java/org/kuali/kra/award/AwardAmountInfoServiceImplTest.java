/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
