/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.award.home;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.home.AwardApprovedSubaward;

/**
 * 
 * This class tests methods in Award.java class
 */
public class AwardApprovedSubawardTest { 
    private static final int AWARD_APPROVED_SUBAWARD_ATTRIBUTES_COUNT = 5;
    
    private AwardApprovedSubaward awardApprovedSubawrdBo;
    
    /**
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        awardApprovedSubawrdBo = new AwardApprovedSubaward();
    }

    /**
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        awardApprovedSubawrdBo = null;
    }
    
    /**
     * 
     * This method tests that total attributes of Award Business Object 
     * @throws Exception
     */
    @Test
    public void testAwardApprovedSubawardBoAttributesCount() throws Exception {              
        Assert.assertEquals(AWARD_APPROVED_SUBAWARD_ATTRIBUTES_COUNT, awardApprovedSubawrdBo.toStringMapper().size());
    }
    
}
