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
package org.kuali.kra.award.htmlunitwebtest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlImageInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * 
 * This is the integration test for Award Approved Subawards Tab. 
 */
@org.junit.Ignore("This test is not meant to be run against the 2.0 release")
public class AwardApprovedSubawardWebTest extends AwardHomeWebTest {
    
    private static final String APPROVED_SUBAWARD_FIELD_PREFIX = 
                                    "approvedSubawardFormHelper.newAwardApprovedSubaward.";
    private static final String ADD_APPROVED_SUBAWARD_METHOD = "methodToCall.addApprovedSubaward";
    private static final String SAVE_APPROVED_SUBAWARD_METHOD = "methodToCall.save";
    
    /**
     * The set up method calls the parent super method and gets the 
     * award Home page after that.
     * @see org.kuali.kra.award.htmlunitwebtest.AwardWebTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * This method calls parent tear down method and than sets awardHomePage to null
     * @see org.kuali.kra.award.htmlunitwebtest.AwardWebTestBase#tearDown()
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
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
        
        setApprovedSubawardFieldValues(awardHomePage,"organization one","10000");
       
        HtmlForm form1 = (HtmlForm) awardHomePage.getForms().get(0);        
        String completeButtonName1=getImageTagName(awardHomePage, 
                                                    ADD_APPROVED_SUBAWARD_METHOD);        
        HtmlImageInput button1 = (HtmlImageInput) form1.getInputByName(completeButtonName1);
        HtmlPage awardHomePageAfterAddingApprovedSubaward = (HtmlPage) button1.click();
        
        setApprovedSubawardFieldValues(awardHomePageAfterAddingApprovedSubaward,"organization two","15000");
        
        HtmlForm form2 = (HtmlForm) awardHomePageAfterAddingApprovedSubaward.getForms().get(0);        
        String completeButtonName2=getImageTagName(awardHomePageAfterAddingApprovedSubaward, 
                                                     ADD_APPROVED_SUBAWARD_METHOD);        
        HtmlImageInput button2 = (HtmlImageInput) form2.getInputByName(completeButtonName2);
        HtmlPage awardHomePageAfterAddingApprovedSubaward2 = (HtmlPage) button2.click();
        
        HtmlPage awardHomePageAfterSave = clickOn(awardHomePageAfterAddingApprovedSubaward2, 
                                                        SAVE_APPROVED_SUBAWARD_METHOD);
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
        setApprovedSubawardFieldValues(awardHomePage,"organization three","99999");     
        HtmlForm form1 = (HtmlForm) awardHomePage.getForms().get(0);        
        String completeButtonName1=getImageTagName(awardHomePage, 
                                                        ADD_APPROVED_SUBAWARD_METHOD);        
        HtmlImageInput button1 = (HtmlImageInput) form1.getInputByName(completeButtonName1);
        HtmlPage awardHomePageAfterAddingApprovedSubaward = (HtmlPage) button1.click();       
        setApprovedSubawardFieldValues(awardHomePageAfterAddingApprovedSubaward,
                                            "organization four","30000");       
        HtmlForm form2 = (HtmlForm) awardHomePageAfterAddingApprovedSubaward.getForms().get(0);        
        String completeButtonName2=getImageTagName(awardHomePageAfterAddingApprovedSubaward, 
                                                         ADD_APPROVED_SUBAWARD_METHOD);        
        HtmlImageInput button2 = (HtmlImageInput) form2.getInputByName(completeButtonName2);
        HtmlPage awardHomePageAfterAddingApprovedSubaward2 = (HtmlPage) button2.click();        
        HtmlPage awardHomePageAfterSave = clickOn(awardHomePageAfterAddingApprovedSubaward2, 
                                                        SAVE_APPROVED_SUBAWARD_METHOD);
        assertDoesNotContain(awardHomePageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);        
        assertDoesNotContain(awardHomePageAfterSave, ERRORS_FOUND_ON_PAGE);
        assertContains(awardHomePageAfterSave,SAVE_SUCCESS_MESSAGE);               
        HtmlPage awardHomePageAfterDelete1 = clickOn(awardHomePageAfterSave, 
                                                        "methodToCall.deleteApprovedSubaward.line1.anchor3");
        HtmlPage awardHomePageAfterDelete2 = clickOn(awardHomePageAfterDelete1, 
                                                  "methodToCall.deleteApprovedSubaward.line0.anchor3");       
        HtmlPage awardHomePageAfterSaveAgain = clickOn(awardHomePageAfterDelete2, 
                                                            SAVE_APPROVED_SUBAWARD_METHOD);       
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
        
        setApprovedSubawardFieldValues(awardHomePage,"organization five","22000");
       
        HtmlForm form1 = (HtmlForm) awardHomePage.getForms().get(0);        
        String completeButtonName1=getImageTagName(awardHomePage, 
                                                        ADD_APPROVED_SUBAWARD_METHOD);        
        HtmlImageInput button1 = (HtmlImageInput) form1.getInputByName(completeButtonName1);
        HtmlPage awardHomePageAfterAddingApprovedSubaward = (HtmlPage) button1.click();      
        setApprovedSubawardFieldValues(awardHomePageAfterAddingApprovedSubaward,"organization six","75000"); 
        HtmlForm form2 = (HtmlForm) awardHomePageAfterAddingApprovedSubaward.getForms().get(0);        
        String completeButtonName2=getImageTagName(awardHomePageAfterAddingApprovedSubaward, 
                                                        ADD_APPROVED_SUBAWARD_METHOD);        
        HtmlImageInput button2 = (HtmlImageInput) form2.getInputByName(completeButtonName2);
        HtmlPage awardHomePageAfterAddingApprovedSubaward2 = (HtmlPage) button2.click();     
        HtmlPage awardHomePageAfterSave = clickOn(awardHomePageAfterAddingApprovedSubaward2, 
                                                        SAVE_APPROVED_SUBAWARD_METHOD);
        assertDoesNotContain(awardHomePageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);        
        assertDoesNotContain(awardHomePageAfterSave, ERRORS_FOUND_ON_PAGE);
        assertContains(awardHomePageAfterSave,SAVE_SUCCESS_MESSAGE);  
        
        setFieldValue(awardHomePageAfterSave,
                "document.awardList[0].awardApprovedSubawards[0].amount","2000.00");
        setFieldValue(awardHomePageAfterSave,
                "document.awardList[0].awardApprovedSubawards[1].amount","1945.00");        
        HtmlPage awardHomePageAfterRecalculate = clickOn(awardHomePageAfterSave, 
                                                            "methodToCall.recalculateSubawardTotal.anchor");
        assertContains(awardHomePageAfterRecalculate,"3945");
    }
    
    public void setApprovedSubawardFieldValues(HtmlPage htmlPage, String organizationName, String amount){
            setFieldValue(htmlPage, APPROVED_SUBAWARD_FIELD_PREFIX + "organizationName", organizationName);
            setFieldValue(htmlPage, APPROVED_SUBAWARD_FIELD_PREFIX + "amount", amount);
    }
    
    
}

    

