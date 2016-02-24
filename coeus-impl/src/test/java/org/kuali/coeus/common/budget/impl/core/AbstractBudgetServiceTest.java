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
package org.kuali.coeus.common.budget.impl.core;

import org.junit.Test;
import org.kuali.coeus.common.budget.api.rate.RateClassType;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetParent;
import org.kuali.coeus.common.budget.framework.core.BudgetParentDocument;
import org.kuali.coeus.common.budget.framework.core.CostElement;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.rate.RateClass;
import org.kuali.coeus.common.budget.framework.rate.ValidCeRateType;

import java.util.ArrayList;
import java.util.Map;

public class AbstractBudgetServiceTest {

    @Test
    public void test_validInflationCeRate_inflation() {

        CostElement e = new CostElement();
        e.setValidCeRateTypes(new ArrayList<ValidCeRateType>(){{
            ValidCeRateType type = new ValidCeRateType();
            RateClass rateClass = new RateClass();
            rateClass.setRateClassTypeCode(RateClassType.INFLATION.getRateClassType());
            type.setRateClass(rateClass);
            add(type);
        }});
        AbstractBudgetService abstractBudgetService = new MockAbstractBudgetService(e);

        BudgetLineItem item = new BudgetLineItem();
        item.setCostElement("ABCDEF");

        abstractBudgetService.validInflationCeRate(item);

    }

    @Test
    public void test_validInflationCeRate_noinflation() {
        CostElement e = new CostElement();
        e.setValidCeRateTypes(new ArrayList<ValidCeRateType>(){{
            ValidCeRateType type = new ValidCeRateType();
            RateClass rateClass = new RateClass();
            rateClass.setRateClassTypeCode(RateClassType.OTHER.getRateClassType());
            type.setRateClass(rateClass);
            add(type);
        }});
        AbstractBudgetService abstractBudgetService = new MockAbstractBudgetService(e);

        BudgetLineItem item = new BudgetLineItem();
        item.setCostElement("123456");

        abstractBudgetService.validInflationCeRate(item);
    }

    static class MockAbstractBudgetService extends AbstractBudgetService {

        final CostElement costElement;

        public MockAbstractBudgetService(CostElement costElement) {
            this.costElement = costElement;
        }

        @Override
        public boolean isBudgetVersionNameValid(BudgetParent parent, String versionName) {
            return false;
        }

        @Override
        public Budget copyBudgetVersion(Budget budget, boolean onlyOnePeriod) {
            return null;
        }

        @Override
        public void populateNewBudgetLineItem(BudgetLineItem newBudgetLineItem, BudgetPeriod budgetPeriod) {

        }

        @Override
        protected Budget getNewBudgetVersion(BudgetParentDocument parent, String versionName, Map options) {
            return null;
        }

        @Override
        protected CostElement getCostElement(String costElement) {
            return this.costElement;
        }
    }
}
