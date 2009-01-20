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

import org.kuali.core.util.KualiDecimal;

import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlImageInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * 
 * This is the integration test for Award Home Page. 
 */
@SuppressWarnings("unchecked")
public class AwardHomeWebTest extends AwardWebTestBase {

    HtmlPage awardHomePage;
    
    /**
     * The set up method calls the parent super method and gets the 
     * award Home page after that.
     * @see org.kuali.kra.award.htmlunitwebtest.AwardWebTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        awardHomePage = getAwardHomePage();
        this.setDefaultRequiredFields(awardHomePage);
    }

    /**
     * This method calls parent tear down method and than sets awardHomePage to null
     * @see org.kuali.kra.award.htmlunitwebtest.AwardWebTestBase#tearDown()
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        awardHomePage = null;
    }
    
    /**
     * 
     * 
     * This method tests the adding of Approved Subawards
     * and saving them.  
     * @throws Exception
     */
    @Test
    public void testAwardSubawardPanelAddApprovedSubaward() throws Exception{
        setFieldValue(awardHomePage, "newAwardApprovedSubaward.organizationName", "organization one");
        setFieldValue(awardHomePage, "newAwardApprovedSubaward.amount", "10000");
       
        final HtmlForm form1 = (HtmlForm) awardHomePage.getForms().get(0);        
        String completeButtonName1=getImageTagName(awardHomePage, "methodToCall.addApprovedSubaward.anchor");        
        final HtmlImageInput button1 = (HtmlImageInput) form1.getInputByName(completeButtonName1);
        HtmlPage awardHomePageAfterAddingApprovedSubaward = (HtmlPage) button1.click();
        
        setFieldValue(awardHomePageAfterAddingApprovedSubaward, "newAwardApprovedSubaward.organizationName", "organization two");
        setFieldValue(awardHomePageAfterAddingApprovedSubaward, "newAwardApprovedSubaward.amount", "15000");
        
        final HtmlForm form2 = (HtmlForm) awardHomePageAfterAddingApprovedSubaward.getForms().get(0);        
        String completeButtonName2=getImageTagName(awardHomePageAfterAddingApprovedSubaward, "methodToCall.addApprovedSubaward.anchor");        
        final HtmlImageInput button2 = (HtmlImageInput) form2.getInputByName(completeButtonName2);
        HtmlPage awardHomePageAfterAddingApprovedSubaward2 = (HtmlPage) button2.click();
        
        HtmlPage awardHomePageAfterSave = clickOn(awardHomePageAfterAddingApprovedSubaward2, "methodToCall.save");
        assertDoesNotContain(awardHomePageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);        
        assertDoesNotContain(awardHomePageAfterSave, ERRORS_FOUND_ON_PAGE);
        assertContains(awardHomePageAfterSave,SAVE_SUCCESS_MESSAGE);        
    }
    
    /**
     * 
     * 
     * This method tests the adding of Approved Subawards
     * and saving them.  
     * @throws Exception
     */
    @Test
    public void testAwardSubawardPanelDeleteApprovedSubaward() throws Exception{
        setFieldValue(awardHomePage, "newAwardApprovedSubaward.organizationName", "organization three");
        setFieldValue(awardHomePage, "newAwardApprovedSubaward.amount", "99999");
       
        final HtmlForm form1 = (HtmlForm) awardHomePage.getForms().get(0);        
        String completeButtonName1=getImageTagName(awardHomePage, "methodToCall.addApprovedSubaward.anchor");        
        final HtmlImageInput button1 = (HtmlImageInput) form1.getInputByName(completeButtonName1);
        HtmlPage awardHomePageAfterAddingApprovedSubaward = (HtmlPage) button1.click();
        
        setFieldValue(awardHomePageAfterAddingApprovedSubaward, "newAwardApprovedSubaward.organizationName", "organization four");
        setFieldValue(awardHomePageAfterAddingApprovedSubaward, "newAwardApprovedSubaward.amount", "30000");
        
        final HtmlForm form2 = (HtmlForm) awardHomePageAfterAddingApprovedSubaward.getForms().get(0);        
        String completeButtonName2=getImageTagName(awardHomePageAfterAddingApprovedSubaward, "methodToCall.addApprovedSubaward.anchor");        
        final HtmlImageInput button2 = (HtmlImageInput) form2.getInputByName(completeButtonName2);
        HtmlPage awardHomePageAfterAddingApprovedSubaward2 = (HtmlPage) button2.click();
        
        HtmlPage awardHomePageAfterSave = clickOn(awardHomePageAfterAddingApprovedSubaward2, "methodToCall.save");
        assertDoesNotContain(awardHomePageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);        
        assertDoesNotContain(awardHomePageAfterSave, ERRORS_FOUND_ON_PAGE);
        assertContains(awardHomePageAfterSave,SAVE_SUCCESS_MESSAGE);        
        
        HtmlPage awardHomePageAfterDelete1 = clickOn(awardHomePageAfterSave, "methodToCall.deleteApprovedSubaward.line1.anchor3");
        HtmlPage awardHomePageAfterDelete2 = clickOn(awardHomePageAfterDelete1, "methodToCall.deleteApprovedSubaward.line0.anchor3");
        
        HtmlPage awardHomePageAfterSaveAgain = clickOn(awardHomePageAfterDelete2, "methodToCall.save");
        
        assertDoesNotContain(awardHomePageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);
        assertDoesNotContain(awardHomePageAfterSaveAgain, ERRORS_FOUND_ON_PAGE);
        assertContains(awardHomePageAfterSaveAgain,SAVE_SUCCESS_MESSAGE);
        
        
    }
    
    /**
     * 
     * 
     * This method tests the adding of Approved Subawards
     * and saving them.  
     * @throws Exception
     */
    @Test
    public void testAwardSubawardPanelRecalculate() throws Exception{
        setFieldValue(awardHomePage, "newAwardApprovedSubaward.organizationName", "organization five");
        setFieldValue(awardHomePage, "newAwardApprovedSubaward.amount", "10000");
       
        final HtmlForm form1 = (HtmlForm) awardHomePage.getForms().get(0);        
        String completeButtonName1=getImageTagName(awardHomePage, "methodToCall.addApprovedSubaward.anchor");        
        final HtmlImageInput button1 = (HtmlImageInput) form1.getInputByName(completeButtonName1);
        HtmlPage awardHomePageAfterAddingApprovedSubaward = (HtmlPage) button1.click();
        
        setFieldValue(awardHomePageAfterAddingApprovedSubaward, "newAwardApprovedSubaward.organizationName", "organization six");
        setFieldValue(awardHomePageAfterAddingApprovedSubaward, "newAwardApprovedSubaward.amount", "15000");
        
        final HtmlForm form2 = (HtmlForm) awardHomePageAfterAddingApprovedSubaward.getForms().get(0);        
        String completeButtonName2=getImageTagName(awardHomePageAfterAddingApprovedSubaward, "methodToCall.addApprovedSubaward.anchor");        
        final HtmlImageInput button2 = (HtmlImageInput) form2.getInputByName(completeButtonName2);
        HtmlPage awardHomePageAfterAddingApprovedSubaward2 = (HtmlPage) button2.click();
        
        HtmlPage awardHomePageAfterSave = clickOn(awardHomePageAfterAddingApprovedSubaward2, "methodToCall.save");
        assertDoesNotContain(awardHomePageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);        
        assertDoesNotContain(awardHomePageAfterSave, ERRORS_FOUND_ON_PAGE);
        assertContains(awardHomePageAfterSave,SAVE_SUCCESS_MESSAGE);  
        
        setFieldValue(awardHomePageAfterSave,"document.awardList[0].awardApprovedSubawards[0].amount","2000.00");
        setFieldValue(awardHomePageAfterSave,"document.awardList[0].awardApprovedSubawards[1].amount","1945.00");
        
        HtmlPage awardHomePageAfterRecalculate = clickOn(awardHomePageAfterSave, "methodToCall.recalculateSubawardTotal");
        System.out.println(awardHomePageAfterRecalculate.asText());
        assertContains(awardHomePageAfterRecalculate,"3945");
    }
    
    /**
     * 
     * This method...
     * @param page
     * @param uniqueNamePrefix
     * @return
     */
    protected String getImageTagName(HtmlPage page, String uniqueNamePrefix) {
        int idx1 = page.asXml().indexOf(uniqueNamePrefix);        
        int idx2 = page.asXml().indexOf("\"", idx1);
        return page.asXml().substring(idx1, idx2).replace("&amp;", "&").replace("((&lt;&gt;))", "((<>))");
    }
    
    
    
}
