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
package org.kuali.kra.budget.document;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BudgetDocumentIntegrationTest extends KcIntegrationTestBase {
    private static final int MILLIS_PER_SECOND = 1000;
    
    private Budget budget;
    
    @Before
    public void setUp() {
        budget = new ProposalDevelopmentBudgetExt();
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
