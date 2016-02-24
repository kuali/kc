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
package org.kuali.kra.coi.disclosure;

import org.junit.Test;
import org.kuali.kra.coi.personfinancialentity.FinancialEntityReporterUnit;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.rules.TemplateRuleTest;

import java.util.ArrayList;
import java.util.List;

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
