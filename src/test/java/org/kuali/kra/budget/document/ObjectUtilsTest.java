/*
 * Copyright 2007 The Kuali Foundation
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

import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.rice.kns.util.ObjectUtils;

/**
 * Testing ObjectUtils equalsByKey logic change
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 */
public class ObjectUtilsTest extends KraTestBase {
    @Test
    public void testObjectUtils_equalsByKey() throws Exception {
        BudgetPeriod periodDB = new BudgetPeriod();
        periodDB.setBudgetPeriodId(new Long(268));
        periodDB.setProposalNumber("103");
        periodDB.setBudgetVersionNumber(1);
        periodDB.setBudgetPeriod(3);
        
        BudgetPeriod periodNew = new BudgetPeriod();
        periodNew.setBudgetPeriodId(null);
        periodNew.setProposalNumber(null);
        periodNew.setBudgetVersionNumber(null);
        periodNew.setBudgetPeriod(3);
        
        boolean equalsResult = false;
        equalsResult = ObjectUtils.equalByKeys(periodDB, periodNew);
        assertFalse(equalsResult);
    }
}
