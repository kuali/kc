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
package org.kuali.coeus.sys.framework.controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.api.model.ScaleThreeDecimal;
import org.kuali.coeus.sys.framework.controller.GlobalFormatterRegistry;
import org.kuali.rice.core.web.format.FormatException;

import java.math.BigDecimal;

public class ScaleThreeDecimalFormatterTest {

    private static final String ALPHANUMERIC_VALUE = "98.efg";
    private static final String ALPHA_VALUE = "ab.cde";
    private static final String TEST_NUMBER_AS_STRING = "98.765";
    private static final double TEST_NUMBER = 98.765;
    
    private GlobalFormatterRegistry.ScaleThreeDecimalFormatter formatter;

    @Before
    public void setUp() {
        formatter = new GlobalFormatterRegistry.ScaleThreeDecimalFormatter();
    }
    
    @After
    public void tearDown() {
        formatter = null;
    }
    
    @Test
    public void testConvertingFromStringToScaleThreeDecimal_GoodData() {
        ScaleThreeDecimal scaleThreeDecimal = new ScaleThreeDecimal(new BigDecimal(TEST_NUMBER));
        Assert.assertEquals(scaleThreeDecimal, formatter.convertToObject(TEST_NUMBER_AS_STRING));
    }
    
    @Test(expected=FormatException.class)
    public void testConvertingFromStringToScaleThreeDecimal_AllBadData() {
        formatter.convertToObject(ALPHA_VALUE);
    }
    
    @Test(expected=FormatException.class)
    public void testConvertingFromStringToScaleThreeDecimal_MixedBadData() {
        formatter.convertToObject(ALPHANUMERIC_VALUE);
    }
}
