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

import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.rules.TemplateRuleTest;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;

public class SaveFinancialEntityRuleTest extends KcUnitTestBase {
// "extends KcUnitTestBase" to force it to load the configuration
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
