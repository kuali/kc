/*
 * Copyright 2005-2014 The Kuali Foundation
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
