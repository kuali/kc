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

    public void FinancialEntityTest() {
     //   open("/kc-dev/portal.jsp");
        click("Financial Entities");
     //   waitForPageToLoad("30000");
     //   login( "jtester");
     //   click("css=input[type=\"submit\"]");
  //      waitForPageToLoad("30000");
  //      selectFrame("iframeportlet");
        click("methodToCall.editNew");
 //       waitForPageToLoad("30000");
        set("financialEntityHelper.newPersonFinancialEntity.entityName", String.valueOf(new Date().getTime()));
        click("financialEntityHelper.newPersonFinancialEntity.sponsorCode");
        set("financialEntityHelper.newPersonFinancialEntity.sponsorCode", "005770");
        set("financialEntityHelper.newPersonFinancialEntity.entityTypeCode", "1");
        set("financialEntityHelper.newPersonFinancialEntity.principalBusinessActivity", "search company");
        set("financialEntityHelper.newRelationDetails[0].relationshipTypeBeans[0].stringValue", "1");
        click("financialEntityHelper.newRelationDetails[1].relationshipTypeBeans[0].booleanValue");
        click("methodToCall.submit.new.anchor");
     //   waitForPageToLoad("30000");
        click("methodToCall.editList");
    //    waitForPageToLoad("30000");
    }

    public void FinancialEntityCompleteTest() {
        //   waitForPageToLoad("30000");
        //   login( "jtester");
        //   click("css=input[type=\"submit\"]");
     //      waitForPageToLoad("30000");
     //      selectFrame("iframeportlet");
        click("Financial Entities");
          click("methodToCall.editNew");
    //       waitForPageToLoad("30000");
           set("financialEntityHelper.newPersonFinancialEntity.entityName", String.valueOf(new Date().getTime()));
           click("financialEntityHelper.newPersonFinancialEntity.sponsorCode");
           set("financialEntityHelper.newPersonFinancialEntity.sponsorCode", "005770");
           set("financialEntityHelper.newPersonFinancialEntity.entityTypeCode", "1");
           set("financialEntityHelper.newPersonFinancialEntity.principalBusinessActivity", "search company");
           set("financialEntityHelper.newRelationDetails[0].relationshipTypeBeans[0].stringValue", "1");
           click("financialEntityHelper.newRelationDetails[1].relationshipTypeBeans[0].booleanValue");
           click("methodToCall.submit.new.anchor");
           assertNoPageErrors() ;
           click("methodToCall.editList");
       // waitForPageToLoad("30000");
        int lastEntityIndex = findLastEntity();
        click("methodToCall.editFinancialEntity.line"+lastEntityIndex);
        //waitForPageToLoad("30000");
        set("financialEntityHelper.activeFinancialEntities["+lastEntityIndex+"].principalBusinessActivity", "test company - v2");
        click("methodToCall.save.line"+lastEntityIndex+".anchor");
        assertNoPageErrors() ;
        assertElementContains("financialEntityHelper.activeFinancialEntities["+lastEntityIndex+"].sponsorCode","005770");
        assertElementContains("financialEntityHelper.activeFinancialEntities["+lastEntityIndex+"].principalBusinessActivity", "test company - v2");
        // waitForPageToLoad("30000");
        click("methodToCall.submit.line"+lastEntityIndex+".anchor");
        assertNoPageErrors() ;
        assertElementContains("financialEntityHelper.activeFinancialEntities["+lastEntityIndex+"].sponsorCode","005770");
        assertElementContains("financialEntityHelper.activeFinancialEntities["+lastEntityIndex+"].principalBusinessActivity", "test company - v2");
      //  waitForPageToLoad("30000");
        click("methodToCall.inactivateFinancialEntity.line"+lastEntityIndex+".anchor");
      //  waitForPageToLoad("30000");
        set("reason", "deactivate");
        click("methodToCall.processAnswer.button0");
        assertNoPageErrors() ;
    //    waitForPageToLoad("30000");
        // this should be ok, unless last test failed after deactivate before activate ?
        //  anyway, there is at least one in the inactive list
        click("methodToCall.activateFinancialEntity.line0.anchor");
        assertNoPageErrors() ;
        //waitForPageToLoad("30000");

    }
    
    private int findLastEntity() {
        // this is for local testing which does not have db refreshed after every test
        int i = 0;
        String message = "";
        while (i < 1000) {
            try {
            message = get("methodToCall.editFinancialEntity.line"+i);
            } catch (AssertionError  e) {
            //if (message.endsWith(" not found")) {
                return (i-1);
            //}
            }
            i ++ ;
        }
        return i;
    }
}
