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
package org.kuali.kra.coi.disclosure;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.kuali.kra.coi.personfinancialentity.FinancialEntityReporterUnit;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.rules.TemplateRuleTest;

public class SaveDisclosureReporterUnitRuleTest {

    @Test
    public void testSaveFinancialEntityReporterUnitOK() {
        new TemplateRuleTest<SaveDisclosureReporterUnitEvent, SaveDisclosureReporterUnitRule>() {
            @Override
            protected void prerequisite() {
                List<FinancialEntityReporterUnit> financialEntityReporterUnits = new ArrayList<FinancialEntityReporterUnit>();
                FinancialEntityReporterUnit financialEntityReporterUnit = new FinancialEntityReporterUnit();
                financialEntityReporterUnit.setUnitNumber("1");
                financialEntityReporterUnit.setLeadUnitFlag(true);
                financialEntityReporterUnits.add(financialEntityReporterUnit);
                FinancialEntityReporterUnit financialEntityReporterUnit1 = new FinancialEntityReporterUnit();
                financialEntityReporterUnit1.setUnitNumber("2");
                financialEntityReporterUnits.add(financialEntityReporterUnit1);
                event = new SaveDisclosureReporterUnitEvent(Constants.EMPTY_STRING, financialEntityReporterUnits);
                rule = new SaveDisclosureReporterUnitRule();
                expectedReturnValue = true;
            }
        };
    }

    @Test
    public void testNoFinancialEntityReporterUnit() {
        new TemplateRuleTest<SaveDisclosureReporterUnitEvent, SaveDisclosureReporterUnitRule>() {
            @Override
            protected void prerequisite() {
                List<FinancialEntityReporterUnit> financialEntityReporterUnits = new ArrayList<FinancialEntityReporterUnit>();
                event = new SaveDisclosureReporterUnitEvent(Constants.EMPTY_STRING, financialEntityReporterUnits);
                rule = new SaveDisclosureReporterUnitRule();
                expectedReturnValue = false;
            }
        };
    }

    @Test
    public void testNoLeadUnit() {
        new TemplateRuleTest<SaveDisclosureReporterUnitEvent, SaveDisclosureReporterUnitRule>() {
            @Override
            protected void prerequisite() {
                List<FinancialEntityReporterUnit> financialEntityReporterUnits = new ArrayList<FinancialEntityReporterUnit>();
                FinancialEntityReporterUnit financialEntityReporterUnit = new FinancialEntityReporterUnit();
                financialEntityReporterUnit.setUnitNumber("1");
                financialEntityReporterUnits.add(financialEntityReporterUnit);
                FinancialEntityReporterUnit financialEntityReporterUnit1 = new FinancialEntityReporterUnit();
                financialEntityReporterUnit1.setUnitNumber("2");
                financialEntityReporterUnits.add(financialEntityReporterUnit1);
                event = new SaveDisclosureReporterUnitEvent(Constants.EMPTY_STRING, financialEntityReporterUnits);
                rule = new SaveDisclosureReporterUnitRule();
                expectedReturnValue = false;
            }
        };
    }

}
