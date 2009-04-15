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
import org.kuali.core.web.format.FormatException;
import org.kuali.kra.budget.RateDecimal;

public class RateDecimalFormatterTest {

    private static final String ALPHANUMERIC_VALUE = "98.efg";
    private static final String ALPHA_VALUE = "ab.cde";
    private static final String TEST_NUMBER_AS_STRING = "98.765";
    private static final double TEST_NUMBER = 98.765;
    
    private RateDecimalFormatter formatter;

    @Before
    public void setUp() {
        formatter = new RateDecimalFormatter();
    }
    
    @After
    public void tearDown() {
        formatter = null;
    }
    
    @Test
    public void testConvertingFromStringToRateDecimal_GoodData() {
        RateDecimal rateDecimal = new RateDecimal(new BigDecimal(TEST_NUMBER));
        Assert.assertEquals(rateDecimal, formatter.convertToObject(TEST_NUMBER_AS_STRING));
    }
    
    @Test(expected=FormatException.class)
    public void testConvertingFromStringToBudgetDecimal_AllBadData() {
        formatter.convertToObject(ALPHA_VALUE);
    }
    
    @Test(expected=FormatException.class)
    public void testConvertingFromStringToBudgetDecimal_MixedBadData() {
        formatter.convertToObject(ALPHANUMERIC_VALUE);
    }
}
