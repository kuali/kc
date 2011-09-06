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
package org.kuali.kra.coi.personfinancialentity;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.rules.TemplateRuleTest;

public class AddFinancialEntityUnitRuleTest {

    @Test
    public void testAddFinancialEntityUnitOK() {
        new TemplateRuleTest<AddFinancialEntityUnitEvent, AddFinancialEntityUnitRule>() {
            @Override
            protected void prerequisite() {
                FinancialEntityUnit financialEntityUnit = new FinancialEntityUnit();
                financialEntityUnit.setUnitNumber("1");
                FinancialEntityUnit financialEntityUnit1 = new FinancialEntityUnit();
                financialEntityUnit1.setUnitNumber("2");
                List<FinancialEntityUnit> financialEntityUnits = new ArrayList<FinancialEntityUnit>();
                financialEntityUnits.add(financialEntityUnit1);
                event = new AddFinancialEntityUnitEvent(Constants.EMPTY_STRING, financialEntityUnit, financialEntityUnits);
                rule = new AddFinancialEntityUnitRule();
                expectedReturnValue = true;
            }
        };
    }

    @Test
    public void testMissingUnitNumber() {
        new TemplateRuleTest<AddFinancialEntityUnitEvent, AddFinancialEntityUnitRule>() {
            @Override
            protected void prerequisite() {
                FinancialEntityUnit financialEntityUnit = new FinancialEntityUnit();
                financialEntityUnit.setUnitNumber("");
                FinancialEntityUnit financialEntityUnit1 = new FinancialEntityUnit();
                financialEntityUnit1.setUnitNumber("2");
                List<FinancialEntityUnit> financialEntityUnits = new ArrayList<FinancialEntityUnit>();
                financialEntityUnits.add(financialEntityUnit1);
                event = new AddFinancialEntityUnitEvent(Constants.EMPTY_STRING, financialEntityUnit, financialEntityUnits);
                rule = new AddFinancialEntityUnitRule();
                expectedReturnValue = false;
            }
        };

    }

    @Test
    public void testDuplicateUnitNumber() {
        new TemplateRuleTest<AddFinancialEntityUnitEvent, AddFinancialEntityUnitRule>() {
            @Override
            protected void prerequisite() {
                FinancialEntityUnit financialEntityUnit = new FinancialEntityUnit();
                financialEntityUnit.setUnitNumber("1");
                FinancialEntityUnit financialEntityUnit1 = new FinancialEntityUnit();
                financialEntityUnit1.setUnitNumber("1");
                List<FinancialEntityUnit> financialEntityUnits = new ArrayList<FinancialEntityUnit>();
                financialEntityUnits.add(financialEntityUnit1);
                event = new AddFinancialEntityUnitEvent(Constants.EMPTY_STRING, financialEntityUnit, financialEntityUnits);
                rule = new AddFinancialEntityUnitRule();
                expectedReturnValue = false;
            }
        };

    }

}
