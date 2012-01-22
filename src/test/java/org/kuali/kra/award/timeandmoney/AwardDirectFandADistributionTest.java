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
package org.kuali.kra.award.timeandmoney;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.home.Award;

/**
 * This class tests methods in AwardDirectFandADistribution.java class
 */
public class AwardDirectFandADistributionTest {

private static final int AWARD_DIRECT_F_AND_A_DISTRIBUTION_ATTRIBUTES_COUNT = 8;
    
    private AwardDirectFandADistribution awardDirectFandADistributionBo;
    private Award award = new Award();
    
    /**
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        awardDirectFandADistributionBo = new AwardDirectFandADistribution();
        awardDirectFandADistributionBo.setAward(award);
    }

    /**
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        awardDirectFandADistributionBo = null;
    }
    
    /**
     * 
     * This method tests that total attributes of Award Business Object 
     * @throws Exception
     */
    @Test
    public void testAwardCostShareBoAttributesCount() throws Exception {              
        Assert.assertEquals(AWARD_DIRECT_F_AND_A_DISTRIBUTION_ATTRIBUTES_COUNT, awardDirectFandADistributionBo.getClass().getFields().length);
    }
    
}
