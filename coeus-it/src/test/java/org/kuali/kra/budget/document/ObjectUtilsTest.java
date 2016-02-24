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

import org.junit.Test;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.util.ObjectUtils;
import static org.junit.Assert.*;
/**
 * Testing ObjectUtils equalsByKey logic change
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 */
public class ObjectUtilsTest extends KcIntegrationTestBase {
    @Test
    public void testObjectUtils_equalsByKey() throws Exception {
        BudgetPeriod periodDB = new BudgetPeriod();
        periodDB.setBudgetPeriodId(new Long(268));
//        periodDB.setProposalNumber("103");
//        periodDB.setBudgetVersionNumber(1);
        periodDB.setBudgetPeriod(3);
        
        BudgetPeriod periodNew = new BudgetPeriod();
        periodNew.setBudgetPeriodId(null);
//        periodNew.setProposalNumber(null);
//        periodNew.setBudgetVersionNumber(null);
        periodNew.setBudgetPeriod(3);
        
        boolean equalsResult = false;
        equalsResult = ObjectUtils.equalByKeys(periodDB, periodNew);
        assertFalse(equalsResult);
    }
}
