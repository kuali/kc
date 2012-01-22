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
package org.kuali.kra.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItemCalculatedAmount;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.service.impl.DeepCopyPostProcessorImpl;

public class DeepCopyPostProcessorTest {
    BudgetPeriod bp;
    @Before
    public void setUp() throws Exception {
        bp = new BudgetPeriod();
        bp.setBudgetPeriodId(1L);
        bp.setBudgetPeriod(1);
        BudgetLineItem bli = new BudgetLineItem();
        bli.setBudgetLineItemId(1L);
        bli.setBudgetJustification("Test justification");
        bp.getBudgetLineItems().add(bli);
        BudgetLineItemCalculatedAmount blic = new BudgetLineItemCalculatedAmount();
        blic.setBudgetLineItemCalculatedAmountId(1L);
        blic.setBudgetPeriod(2);
        bli.getBudgetCalculatedAmounts().add(blic);
    }

    @After
    public void tearDown() throws Exception {
        bp=null;
    }

    @Test
    public void testProcessDeepCopyWithDeepCopyIgnore() {
        BudgetPeriod copiedBp = (BudgetPeriod)new DeepCopyPostProcessorImpl().processDeepCopyWithDeepCopyIgnore(bp);
        assertNull(copiedBp.getBudgetPeriodId());
        assertNotNull(copiedBp.getBudgetLineItems());
        assertEquals(new Integer(1),copiedBp.getBudgetPeriod());
        assertNull(copiedBp.getBudgetLineItem(0).getBudgetLineItemId());
        assertNotNull(copiedBp.getBudgetLineItem(0).getBudgetJustification());
        assertNotNull(copiedBp.getBudgetLineItem(0).getBudgetCalculatedAmounts());
        BudgetLineItemCalculatedAmount blic = (BudgetLineItemCalculatedAmount)copiedBp.getBudgetLineItem(0).getBudgetCalculatedAmounts().get(0);
        assertNull(blic.getBudgetLineItemCalculatedAmountId());
        assertNotNull(blic.getBudgetPeriod());
    }

}
