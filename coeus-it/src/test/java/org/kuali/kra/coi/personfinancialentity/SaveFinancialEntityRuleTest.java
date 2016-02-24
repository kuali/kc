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
package org.kuali.kra.coi.personfinancialentity;

import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.rules.TemplateRuleTest;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;

public class SaveFinancialEntityRuleTest extends KcIntegrationTestBase {
// "extends KcIntegrationTestBase" to force it to load the configuration
// can't override getBusinessObjectService, which is final, of rule class
    @Test
    public void testSaveFinancialSponsorCodeOK() {
        new TemplateRuleTest<SaveFinancialEntityEvent, SaveFinancialEntityRule>() {
            @Override
            protected void prerequisite() {
                PersonFinIntDisclosure personFinIntDisclosure = new PersonFinIntDisclosure();
                personFinIntDisclosure.setSponsorCode("005770");
                event = new SaveFinancialEntityEvent(Constants.EMPTY_STRING, personFinIntDisclosure);
                 rule = new SaveFinancialEntityRule();
                expectedReturnValue = true;
            }
        };
    }

    @Test
    public void testInvalidSponsorCode() {
        new TemplateRuleTest<SaveFinancialEntityEvent, SaveFinancialEntityRule>() {
            @Override
            protected void prerequisite() {
                PersonFinIntDisclosure personFinIntDisclosure = new PersonFinIntDisclosure();
                personFinIntDisclosure.setSponsorCode("abc");
                event = new SaveFinancialEntityEvent(Constants.EMPTY_STRING, personFinIntDisclosure);
                rule = new SaveFinancialEntityRule();
                expectedReturnValue = false;
            }
        };
    }

}
