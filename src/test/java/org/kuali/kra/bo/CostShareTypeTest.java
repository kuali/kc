/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.bo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * This class tests methods in Award.java class
 */
public class CostShareTypeTest { 
    private static final int COST_SHARE_TYPE_ATTRIBUTES_COUNT = 2;
    
    private CostShareType costShareTypeBo;
    
    /**
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        costShareTypeBo = new CostShareType();
    }

    /**
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        costShareTypeBo = null;
    }
    
    /**
     * 
     * This method tests that total attributes of Award Business Object 
     * @throws Exception
     */
    @Test
    public void testCostShareTypeBoAttributesCount() throws Exception {              
        Assert.assertEquals(COST_SHARE_TYPE_ATTRIBUTES_COUNT, costShareTypeBo.getClass().getFields().length);
    }
    
}

