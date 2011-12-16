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
package org.kuali.kra.subaward.customdata;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;

public class SubAwardCustomDataTest extends KcUnitTestBase{
    
    private static final int SUBAWARD_PROPOSAL_CUSTOM_DATA_ATTRIBUTES_COUNT = 4;
    
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
        Assert.assertEquals(SUBAWARD_PROPOSAL_CUSTOM_DATA_ATTRIBUTES_COUNT, subAwardCustomDataBo.toStringMapper().size());
    }
}
