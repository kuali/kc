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
package org.kuali.kra.budget.document;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BudgetDocumentIntegrationTest {
    private static final int MILLIS_PER_SECOND = 1000;
    
    private BudgetDocument budgetDocument;
    
    @Before
    public void setUp() {
        budgetDocument = new BudgetDocument();
    }
    
    @After
    public void tearDown() {
        budgetDocument = null;
    }
    
    @Test
    public void testLoadingFiscalYearStart() {
        Date fiscalYearStart = budgetDocument.loadFiscalYearStart();
        
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(2000, Calendar.JULY, 1, 0, 0, 0); // test data set via load_system_params.sql
        
        // a small delta has resulted during testing, but always less than a second. Why? I have no idea.
        Assert.assertTrue(Math.abs(cal.getTimeInMillis() - fiscalYearStart.getTime()) < MILLIS_PER_SECOND); 
    }
}
