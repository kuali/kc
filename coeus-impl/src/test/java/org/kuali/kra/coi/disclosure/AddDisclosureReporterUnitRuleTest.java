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
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.kra.coi.personfinancialentity.FinancialEntityReporterUnit;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.rules.TemplateRuleTest;

import java.util.ArrayList;
import java.util.List;

public class AddDisclosureReporterUnitRuleTest {

    @Test
    public void testAddFinancialEntityReporterUnitOK() {
        new TemplateRuleTest<AddDisclosureReporterUnitEvent, AddDisclosureReporterUnitRule>() {
            @Override
            protected void prerequisite() {
                FinancialEntityReporterUnit financialEntityReporterUnit = new FinancialEntityReporterUnit() {
                    public Unit getUnit() {
                        Unit unit = new Unit();
                        unit.setUnitNumber(this.getUnitNumber());
                        unit.setUnitName("unit name");
                        return unit;
                    }

                };
                financialEntityReporterUnit.setUnitNumber("1");
                FinancialEntityReporterUnit financialEntityReporterUnit1 = new FinancialEntityReporterUnit();
                financialEntityReporterUnit1.setUnitNumber("2");
                List<FinancialEntityReporterUnit> financialEntityReporterUnits = new ArrayList<FinancialEntityReporterUnit>();
                financialEntityReporterUnits.add(financialEntityReporterUnit1);
                event = new AddDisclosureReporterUnitEvent(Constants.EMPTY_STRING, financialEntityReporterUnit, financialEntityReporterUnits);
                rule = new AddDisclosureReporterUnitRule();
                expectedReturnValue = true;
            }
        };
    }

    @Test
    public void testMissingUnitNumber() {
        new TemplateRuleTest<AddDisclosureReporterUnitEvent, AddDisclosureReporterUnitRule>() {
            @Override
            protected void prerequisite() {
                FinancialEntityReporterUnit financialEntityReporterUnit = new FinancialEntityReporterUnit();
                financialEntityReporterUnit.setUnitNumber("");
                FinancialEntityReporterUnit financialEntityReporterUnit1 = new FinancialEntityReporterUnit();
                financialEntityReporterUnit1.setUnitNumber("2");
                List<FinancialEntityReporterUnit> financialEntityReporterUnits = new ArrayList<FinancialEntityReporterUnit>();
                financialEntityReporterUnits.add(financialEntityReporterUnit1);
                event = new AddDisclosureReporterUnitEvent(Constants.EMPTY_STRING, financialEntityReporterUnit, financialEntityReporterUnits);
                rule = new AddDisclosureReporterUnitRule();
                expectedReturnValue = false;
            }
        };

    }

    @Test
    public void testDuplicateUnitNumber() {
        new TemplateRuleTest<AddDisclosureReporterUnitEvent, AddDisclosureReporterUnitRule>() {
            @Override
            protected void prerequisite() {
                FinancialEntityReporterUnit financialEntityReporterUnit = new FinancialEntityReporterUnit() {
                    public Unit getUnit() {
                        Unit unit = new Unit();
                        unit.setUnitNumber(this.getUnitNumber());
                        unit.setUnitName("unit name");
                        return unit;
                    }
                };
                financialEntityReporterUnit.setUnitNumber("1");
                FinancialEntityReporterUnit financialEntityReporterUnit1 = new FinancialEntityReporterUnit();
                financialEntityReporterUnit1.setUnitNumber("1");
                List<FinancialEntityReporterUnit> financialEntityReporterUnits = new ArrayList<FinancialEntityReporterUnit>();
                financialEntityReporterUnits.add(financialEntityReporterUnit1);
                event = new AddDisclosureReporterUnitEvent(Constants.EMPTY_STRING, financialEntityReporterUnit, financialEntityReporterUnits);
                rule = new AddDisclosureReporterUnitRule();
                expectedReturnValue = false;
            }
        };

    }

    @Test
    public void testAddDisclosurePersonUnitOK() {
        new TemplateRuleTest<AddDisclosureReporterUnitEvent, AddDisclosureReporterUnitRule>() {
            @Override
            protected void prerequisite() {
                DisclosurePersonUnit disclosurePersonUnit = new DisclosurePersonUnit() {
                    public Unit getUnit() {
                        Unit unit = new Unit();
                        unit.setUnitNumber(this.getUnitNumber());
                        unit.setUnitName("unit name");
                        return unit;
                    }

                };
                disclosurePersonUnit.setUnitNumber("1");
                DisclosurePersonUnit disclosurePersonUnit1 = new DisclosurePersonUnit();
                disclosurePersonUnit1.setUnitNumber("2");
                List<DisclosurePersonUnit> disclosurePersonUnits = new ArrayList<DisclosurePersonUnit>();
                disclosurePersonUnits.add(disclosurePersonUnit1);
                event = new AddDisclosureReporterUnitEvent(Constants.EMPTY_STRING, disclosurePersonUnit, disclosurePersonUnits);
                rule = new AddDisclosureReporterUnitRule();
                expectedReturnValue = true;
            }
        };
    }

}
