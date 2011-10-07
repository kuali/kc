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

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.test.infrastructure.KcSeleniumHelper;
import org.openqa.selenium.WebDriver;

public class FinancialEntitySeleniumHelper extends KcSeleniumHelper {
    private static FinancialEntitySeleniumHelper helper;

    public static FinancialEntitySeleniumHelper instance(WebDriver driver) {

        if (helper == null) {
            helper = new FinancialEntitySeleniumHelper(driver);
        }
        return helper;
    }

    private FinancialEntitySeleniumHelper(WebDriver driver) {
        super(driver);
    }

    public void FinancialEntityCompleteTest() {

        click("Financial Entity");
        click("methodToCall.editNew");
        click("relationDetailControl");
        setupFields();
        waitForSponsorCodeAjax();
        click("methodToCall.submit.new.anchor");
        assertNoPageErrors();

        click("methodToCall.editList");
        int lastEntityIndex = findLastEntity();
        click("methodToCall.editActiveFinancialEntity.line" + lastEntityIndex);
        set("financialEntityHelper.activeFinancialEntities[" + lastEntityIndex + "].principalBusinessActivity", "test company - v2");

        click("methodToCall.save.line" + lastEntityIndex + ".anchor");
        checkEditActionOK(lastEntityIndex);
        
        click("methodToCall.submit.line" + lastEntityIndex + ".anchor");
        checkEditActionOK(lastEntityIndex);

        click("methodToCall.inactivateFinancialEntity.line" + lastEntityIndex + ".anchor");
        set("reason", "deactivate");
        click("methodToCall.processAnswer.button0");
        assertNoPageErrors();

        click("methodToCall.activateFinancialEntity.line0.anchor");
        assertNoPageErrors();

    }

    private void checkEditActionOK(int lastEntityIndex) {
        assertNoPageErrors();
        assertElementContains("financialEntityHelper.activeFinancialEntities[" + lastEntityIndex + "].sponsorCode", "005770");
        assertElementContains("financialEntityHelper.activeFinancialEntities[" + lastEntityIndex + "].principalBusinessActivity",
                "test company - v2");
        
    }
    
    /*
     * set up required and some testing data
     */
    private void setupFields() {
        set("financialEntityHelper.newPersonFinancialEntity.entityName", String.valueOf(new Date().getTime()));
        click("financialEntityHelper.newPersonFinancialEntity.sponsorCode");
        set("financialEntityHelper.newPersonFinancialEntity.sponsorCode", "005770");
        set("financialEntityHelper.newPersonFinancialEntity.entityTypeCode", "1");
        set("financialEntityHelper.newPersonFinancialEntity.principalBusinessActivity", "test company");
        set("financialEntityHelper.newRelationDetails[0].relationshipTypeBeans[0].stringValue", "1");
        click("financialEntityHelper.newRelationDetails[1].relationshipTypeBeans[0].booleanValue");
    
    }
    
    /*
     * utility method for local test.  Local test does not refresh db after each test, so
     * there might be several financial entity.  find the last one to 'edit'
     */
    private int findLastEntity() {
        // this is for local testing which does not have db refreshed after every test
        int i = 0;
        String message = "";
        while (i < 1000) {
            try {
                message = get("methodToCall.editActiveFinancialEntity.line" + i);
            }
            catch (AssertionError e) {
                // if (message.endsWith(" not found")) {
                return (i - 1);
                // }
            }
            i++;
        }
        return i;
    }
    
    private void waitForSponsorCodeAjax() {
        // use "i" as safety valve to prevent infinite loop, in case something went wrong
        // no more than 5 sec wait.
        int i = 0;
        while (i++ < 10 && StringUtils.isBlank(get("financialEntityHelper.newPersonFinancialEntity.finEntityContactInfos[0].postalCode"))) {
            try {
               wait(500L);
            } catch (Exception e) {
                
            }
        }
    }
}
