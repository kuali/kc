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
package org.kuali.kra.award.customdata;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.home.Award;

/**
 * 
 * This class tests methods in Award.java class
 */
public class AwardCustomDataTest { 
    private static final int AWARD_CUSTOM_DATA_ATTRIBUTES_COUNT = 5;
    
    private AwardCustomData awardCustomDataBo;
    private Award award = new Award();
    
    /**
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        awardCustomDataBo = new AwardCustomData();
        awardCustomDataBo.setAward(award);
    }

    /**
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        awardCustomDataBo = null;
    }
    
    /**
     * 
     * This method tests that total attributes of Award Business Object 
     * @throws Exception
     */
    @Test
    public void testAwardCostShareBoAttributesCount() throws Exception {              
        Assert.assertEquals(AWARD_CUSTOM_DATA_ATTRIBUTES_COUNT, awardCustomDataBo.getClass().getDeclaredFields().length);
    }
    
}
