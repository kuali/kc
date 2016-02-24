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
package org.kuali.kra.subaward.customdata;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.subaward.bo.SubAward;

public class SubAwardCustomDataTest  {
    
    private static final int SUBAWARD_PROPOSAL_CUSTOM_DATA_ATTRIBUTES_COUNT = 6;
    
    private SubAwardCustomData subAwardCustomDataBo;
    private SubAward subAward = new SubAward();
    
    
    @Before
    public void setUp() throws Exception {
        subAwardCustomDataBo = new SubAwardCustomData();
        subAwardCustomDataBo.setSubAward(subAward);
    }

    @After
    public void tearDown() throws Exception {
        subAwardCustomDataBo= null;
    }
    
    @Test
    public void testSubAwardCustomDataBoAttributesCount() throws Exception {              
        Assert.assertEquals(SUBAWARD_PROPOSAL_CUSTOM_DATA_ATTRIBUTES_COUNT, subAwardCustomDataBo.getClass().getDeclaredFields().length);
    }
}
