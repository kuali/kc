/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award.htmlunitwebtest;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlImageInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class AwardTimeAndMoneyWebTest extends AwardWebTestBase{
    
    HtmlPage awardTimeAndMoneyPage;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        awardTimeAndMoneyPage = getAwardTimeAndMoneyPage();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    @Test
    public void testAwardTimeAndMoneyPage(){
        System.out.println("Page is : " + awardTimeAndMoneyPage.asText());
        assertContains(awardTimeAndMoneyPage, "Under Construction");
    }
    
    @Test
    public void testAwardFandaRatePanelAddFandaRate() throws Exception{
        setFieldValue(awardTimeAndMoneyPage, "newAwardFandaRate.applicableFandaRate", "5");
        setFieldValue(awardTimeAndMoneyPage, "newAwardFandaRate.fandaRateTypeCode", "1");
        setFieldValue(awardTimeAndMoneyPage, "newAwardFandaRate.fiscalYear", "2008");
        setFieldValue(awardTimeAndMoneyPage, "newAwardFandaRate.startDate", "07/01/2007");
        setFieldValue(awardTimeAndMoneyPage, "newAwardFandaRate.endDate", "06/30/2008");
        setFieldValue(awardTimeAndMoneyPage, "newAwardFandaRate.onCampusFlag", "N");
        
        final HtmlForm form1 = (HtmlForm) awardTimeAndMoneyPage.getForms().get(0);        
        String completeButtonName1=getImageTagName(awardTimeAndMoneyPage, "methodToCall.addFandaRate");        
        final HtmlImageInput button1 = (HtmlImageInput) form1.getInputByName(completeButtonName1);
        HtmlPage awardTimeAndMoneyPageAfterAddingOnCampus = (HtmlPage) button1.click();
        
        setFieldValue(awardTimeAndMoneyPageAfterAddingOnCampus, "newAwardFandaRate.applicableFandaRate", "5");
        setFieldValue(awardTimeAndMoneyPageAfterAddingOnCampus, "newAwardFandaRate.fandaRateTypeCode", "1");
        setFieldValue(awardTimeAndMoneyPageAfterAddingOnCampus, "newAwardFandaRate.fiscalYear", "2008");
        setFieldValue(awardTimeAndMoneyPageAfterAddingOnCampus, "newAwardFandaRate.startDate", "07/01/2007");
        setFieldValue(awardTimeAndMoneyPageAfterAddingOnCampus, "newAwardFandaRate.endDate", "06/30/2008");
        setFieldValue(awardTimeAndMoneyPageAfterAddingOnCampus, "newAwardFandaRate.onCampusFlag", "F");
        
        final HtmlForm form2 = (HtmlForm) awardTimeAndMoneyPageAfterAddingOnCampus.getForms().get(0);        
        String completeButtonName2=getImageTagName(awardTimeAndMoneyPageAfterAddingOnCampus, "methodToCall.addFandaRate");        
        final HtmlImageInput button2 = (HtmlImageInput) form2.getInputByName(completeButtonName2);
        HtmlPage awardTimeAndMoneyPageAfterAddingOnCampusAndOffCampus = (HtmlPage) button2.click();
        
        HtmlPage awardTimeAndMoneyPageAfterSave = clickOn(awardTimeAndMoneyPageAfterAddingOnCampusAndOffCampus, "methodToCall.save");
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERRORS_FOUND_ON_PAGE);
        assertContains(awardTimeAndMoneyPageAfterSave,SAVE_SUCCESS_MESSAGE);        
    }
    
    @Test
    public void testAwardFandaRatePanelAddFandaRateOnlyOnCampus() throws Exception{
        setFieldValue(awardTimeAndMoneyPage, "newAwardFandaRate.applicableFandaRate", "5");
        setFieldValue(awardTimeAndMoneyPage, "newAwardFandaRate.fandaRateTypeCode", "1");
        setFieldValue(awardTimeAndMoneyPage, "newAwardFandaRate.fiscalYear", "2008");
        setFieldValue(awardTimeAndMoneyPage, "newAwardFandaRate.startDate", "07/01/2007");
        setFieldValue(awardTimeAndMoneyPage, "newAwardFandaRate.endDate", "06/30/2008");
        setFieldValue(awardTimeAndMoneyPage, "newAwardFandaRate.onCampusFlag", "N");
        
        final HtmlForm form1 = (HtmlForm) awardTimeAndMoneyPage.getForms().get(0);        
        String completeButtonName1=getImageTagName(awardTimeAndMoneyPage, "methodToCall.addFandaRate");        
        final HtmlImageInput button1 = (HtmlImageInput) form1.getInputByName(completeButtonName1);
        HtmlPage awardTimeAndMoneyPageAfterAddingOnCampus = (HtmlPage) button1.click();
        
        HtmlPage awardTimeAndMoneyPageAfterSave = clickOn(awardTimeAndMoneyPageAfterAddingOnCampus, "methodToCall.save");
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, SAVE_SUCCESS_MESSAGE);
        assertContains(awardTimeAndMoneyPageAfterSave,ERRORS_FOUND_ON_PAGE);
        assertContains(awardTimeAndMoneyPageAfterSave,"F&A rates must be input in pairs(both on and off campus rates).");
        
    }
    protected String getImageTagName(HtmlPage page, String uniqueNamePrefix) {
        int idx1 = page.asXml().indexOf(uniqueNamePrefix);
        //int idx2 = page.asXml().indexOf(".((##)).((&lt;&gt;)).(([])).((**)).((^^)).((&amp;&amp;)).((//)).((~~)).anchor", idx1);
        int idx2 = page.asXml().indexOf("\"", idx1);
        return page.asXml().substring(idx1, idx2).replace("&amp;", "&").replace("((&lt;&gt;))", "((<>))");
    }

}
