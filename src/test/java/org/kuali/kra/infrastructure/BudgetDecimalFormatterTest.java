/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.infrastructure;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.rice.kns.web.format.FormatException;

public class BudgetDecimalFormatterTest {

    private static final String ALPHA_NUMERIC_VALUE = "1000.ef";
    private static final String ALPHA_VALUE = "abcd.ef";
    private static final String TEST_NUMBER_AS_STRING = "987654321.09";
    private static final double TEST_NUMBER = 987654321.09;
    
    private BudgetDecimalFormatter formatter;

    @Before
    public void setUp() {
        formatter = new BudgetDecimalFormatter();
    }
    
    @After
    public void tearDown() {
        formatter = null;
    }
    
    @Test
    public void testConvertingFromStringToBudgetDecimal_GoodData() {
        BudgetDecimal budgetDecimal = new BudgetDecimal(new BigDecimal(TEST_NUMBER));
        Assert.assertEquals(budgetDecimal, formatter.convertToObject(TEST_NUMBER_AS_STRING));
    }
    
    @Test(expected=FormatException.class)
    public void testConvertingFromStringToBudgetDecimal_AllBadData() {
        formatter.convertToObject(ALPHA_VALUE);
    }
    
    @Test(expected=FormatException.class)
    public void testConvertingFromStringToBudgetDecimal_MixedBadData() {
        formatter.convertToObject(ALPHA_NUMERIC_VALUE);
    }
}
