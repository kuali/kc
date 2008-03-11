/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.budget.calculator;


import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.budget.BudgetDecimal;

public class BudgetDecimalTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void divideTest() throws Exception{
        BudgetDecimal op1 = new BudgetDecimal(100);
        BudgetDecimal op2 = new BudgetDecimal(365);
        System.out.println(op1.divide(op2));
        Assert.assertEquals(op1.divide(op2),new BudgetDecimal(100d/365d));
    }

}
