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
package org.kuali.kra.budget.document;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.budget.core.Budget;

public class BudgetDocumentIntegrationTest {
    private static final int MILLIS_PER_SECOND = 1000;
    
    private Budget budget;
    
    @Before
    public void setUp() {
        budget = new Budget();
    }
    
    @After
    public void tearDown() {
        budget = null;
    }
    
    @Test
    public void testLoadingFiscalYearStart() {
        Date fiscalYearStart = budget.loadFiscalYearStart();
        
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(2000, Calendar.JULY, 1, 0, 0, 0);
        
        // a small delta has resulted during testing, but always less than a second. Why? I have no idea.
        Assert.assertTrue(Math.abs(cal.getTimeInMillis() - fiscalYearStart.getTime()) < MILLIS_PER_SECOND); 
    }
}
