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
package org.kuali.kra.award.home;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * This class tests the Valid Rates BO.
 */
public class ValidRatesTest {
    
    public static final int VALID_RATES_ATTRIUTES_COUNT = 7;
    ValidRates validRates;

    @Before
    public void setUp() throws Exception {
        validRates = new ValidRates();
    }

    @After
    public void tearDown() throws Exception {
        validRates = null;
    }

    @Test
    public final void testToStringMapper() {
        Assert.assertEquals(VALID_RATES_ATTRIUTES_COUNT, validRates.getClass().getDeclaredFields().length);
    }

}
