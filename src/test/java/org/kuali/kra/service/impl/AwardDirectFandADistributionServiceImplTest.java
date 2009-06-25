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

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.timeandmoney.AwardDirectFandADistribution;

/**
 * This class tests public methods in AwardDirectFandADistributionServiceImpl.
 */
public class AwardDirectFandADistributionServiceImplTest extends AwardDirectFandADistributionServiceImpl {

    private static final int TWO = 2;
    private static final int SIX = 6;
    private static final int THREE = 3;
    AwardDirectFandADistributionServiceImpl awardDirectFandADistributionServiceImpl;
    List<AwardDirectFandADistribution> awardDirectFandADistributions;
    AwardDirectFandADistribution awardDirectFandADistribution;
    Award award;
    
    
    @Before
    public void setUp() throws Exception {
        awardDirectFandADistributionServiceImpl = new AwardDirectFandADistributionServiceImpl();
        awardDirectFandADistributions = new ArrayList<AwardDirectFandADistribution>();
        awardDirectFandADistribution = new AwardDirectFandADistribution();
        award = new Award();
        Calendar calendar = Calendar.getInstance();
        award.setBeginDate(new Date(calendar.getTime().getTime()));
        calendar.add(Calendar.YEAR, TWO);
        calendar.add(Calendar.MONTH, SIX);
        award.setProjectEndDate(new Date(calendar.getTime().getTime()));
        
    }

    @After
    public void tearDown() throws Exception {
        awardDirectFandADistributionServiceImpl = null;
        
    }

    @Test
    public final void testGenerateDefaultAwardDirectFandADistributionPeriods() {
        awardDirectFandADistributions = awardDirectFandADistributionServiceImpl.generateDefaultAwardDirectFandADistributionPeriods(award);
        Assert.assertTrue(awardDirectFandADistributions.size() == THREE);
    }
}
