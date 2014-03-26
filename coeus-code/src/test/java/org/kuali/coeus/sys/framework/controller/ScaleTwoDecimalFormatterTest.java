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
package org.kuali.coeus.sys.framework.controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.controller.GlobalFormatterRegistry;
import org.kuali.rice.core.web.format.FormatException;

import java.math.BigDecimal;

public class ScaleTwoDecimalFormatterTest {

    private static final String ALPHA_NUMERIC_VALUE = "1000.ef";
    private static final String ALPHA_VALUE = "abcd.ef";
    private static final String TEST_NUMBER_AS_STRING = "987654321.09";
    private static final double TEST_NUMBER = 987654321.09;
    
    private GlobalFormatterRegistry.ScaleTwoDecimalFormatter formatter;

    @Before
    public void setUp() {
        formatter = new GlobalFormatterRegistry.ScaleTwoDecimalFormatter();
    }
    
    @After
    public void tearDown() {
        formatter = null;
    }
    
    @Test
    public void testConvertingFromStringToScaleTwoDecimal_GoodData() {
        ScaleTwoDecimal scaleTwoDecimal = new ScaleTwoDecimal(new BigDecimal(TEST_NUMBER));
        Assert.assertEquals(scaleTwoDecimal, formatter.convertToObject(TEST_NUMBER_AS_STRING));
    }
    
    @Test(expected=FormatException.class)
    public void testConvertingFromStringToScaleTwoDecimal_AllBadData() {
        formatter.convertToObject(ALPHA_VALUE);
    }
    
    @Test(expected=FormatException.class)
    public void testConvertingFromStringToScaleTwoDecimal_MixedBadData() {
        formatter.convertToObject(ALPHA_NUMERIC_VALUE);
    }
}
