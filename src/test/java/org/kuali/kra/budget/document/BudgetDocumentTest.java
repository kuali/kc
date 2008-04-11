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
package org.kuali.kra.budget.document;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BudgetDocumentTest extends TestCase {
    private static final int DEVIATION_LIMIT_MS = 1000;
    private static final int DAY_1 = 1;
    private static final int YEAR_2000 = 2000;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    /**
     * This method does what its name says
     * @throws Exception
     */
    @Test
    public void testCalculatingDatefromString() throws Exception {
        BudgetDocument bd = new BudgetDocument();
        Date fiscalYearDate = bd.createDateFromString("07/01/2000");
        
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(YEAR_2000, Calendar.JULY, DAY_1, 0, 0, 0);
        Assert.assertTrue(cal.getTimeInMillis() - fiscalYearDate.getTime() < DEVIATION_LIMIT_MS);
    }
}