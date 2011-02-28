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
package org.kuali.kra.irb.selenium;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.UnitService;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

public class ProtocolRequiredFieldsTestBase extends KcSeleniumTestBase {

    protected void setupRequiredFields(String title, String personId, String unitNumber) throws Exception {
        selenium.type("//input[@id='document.documentHeader.documentDescription']", "test");
        selenium.type("//textarea[@id='document.protocolList[0].title']", title);
        if (StringUtils.isNotBlank(unitNumber)) {
            selenium.type("protocolHelper.leadUnitNumber", unitNumber);
            selenium.fireEvent("protocolHelper.leadUnitNumber", "blur");
            Assert.assertTrue(selenium.isTextPresent(getUnitService().getUnitName(unitNumber)));
        }
        selenium.click("methodToCall.performLookup.(!!org.kuali.kra.bo.KcPerson!!).(((personId:protocolHelper.personId,fullName:protocolHelper.principalInvestigatorName,unit.unitNumber:protocolHelper.lookupUnitNumber,unit.unitName:protocolHelper.lookupUnitName))).((``)).((<>)).(([])).((**)).((^^)).((&&)).((//)).((~~)).(::::;;::::).anchor");
        selenium.waitForPageToLoad("30000");
        selenium.type("//input[@id='personId']", personId);
        selenium.click("//input[@name='methodToCall.search' and @value='search']");
        selenium.waitForPageToLoad("30000");
        selenium.click("//table[@id='row']/tbody/tr[1]/td[1]/a");

    }

    private UnitService getUnitService() {
        return KraServiceLocator.getService(UnitService.class);
    }
}
