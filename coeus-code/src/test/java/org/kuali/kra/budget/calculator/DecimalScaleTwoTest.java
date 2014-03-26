/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.budget.calculator;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

public class DecimalScaleTwoTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void divideTest() throws Exception{
        ScaleTwoDecimal op1 = new ScaleTwoDecimal(100);
        ScaleTwoDecimal op2 = new ScaleTwoDecimal(365);
        Assert.assertEquals(op1.divide(op2, false),new ScaleTwoDecimal(100d/365d));
    }

    @Test
    public void percentageTest() throws Exception{
        ScaleTwoDecimal op1 = new ScaleTwoDecimal(39);
        ScaleTwoDecimal op2 = new ScaleTwoDecimal(9);
        Assert.assertEquals(op1.percentage(op2),new ScaleTwoDecimal(3.51));
    }
    @Test
    public void divide1Test() throws Exception{
        ScaleTwoDecimal op1 = new ScaleTwoDecimal(100);
        ScaleTwoDecimal op2 = new ScaleTwoDecimal(3);
        Assert.assertEquals(op1.divide(op2, false),new ScaleTwoDecimal(100d/3d));
    }
}
