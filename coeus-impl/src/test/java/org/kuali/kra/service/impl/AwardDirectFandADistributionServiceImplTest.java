/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.service.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.service.impl.AwardDirectFandADistributionServiceImpl;
import org.kuali.kra.award.timeandmoney.AwardDirectFandADistribution;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * This class tests public methods in AwardDirectFandADistributionServiceImpl.
 */
public class AwardDirectFandADistributionServiceImplTest extends AwardDirectFandADistributionServiceImpl {

    private static final int TWO = 2;
    private static final int SIX = 6;
    private static final int THREE = 3;
    AwardDirectFandADistributionServiceImpl awardDirectFandADistributionServiceImpl;
    List<AwardDirectFandADistribution> awardDirectFandADistributions;
    Award award;
    
    
    @Before
    public void setUp() throws Exception {
        awardDirectFandADistributionServiceImpl = new AwardDirectFandADistributionServiceImpl();
        awardDirectFandADistributions = new ArrayList<AwardDirectFandADistribution>();
        award = new Award();
        Calendar calendar = Calendar.getInstance();
        award.setAwardEffectiveDate(new Date(calendar.getTime().getTime()));
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
