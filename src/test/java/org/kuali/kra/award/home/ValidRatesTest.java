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
    
    public static final int VALID_RATES_ATTRIUTES_COUNT = 5;
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
        Assert.assertEquals(VALID_RATES_ATTRIUTES_COUNT, validRates.getClass().getFields().length);
    }

}
