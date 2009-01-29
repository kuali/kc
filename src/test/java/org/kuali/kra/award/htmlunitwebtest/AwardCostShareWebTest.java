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

/**
 * 
 * This is the integration test for Award Cost Share Tab. 
 */

public class AwardCostShareWebTest extends AwardTimeAndMoneyWebTest {

    /**
     * The set up method calls the parent super method and gets the 
     * award Payment Reports And Terms page after that.
     * @see org.kuali.kra.award.htmlunitwebtest.AwardWebTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * This method calls parent tear down method and than sets paymentReportsAndTermsPage to null
     * @see org.kuali.kra.award.htmlunitwebtest.AwardWebTestBase#tearDown()
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    
    /**
     * 
     * This method tests the adding of 2 Cost Shares 
     * and saving them.  
     * @throws Exception
     */
    @Test
    public void testAwardCostShareAddCostShare() throws Exception{
        setFieldValue(awardTimeAndMoneyPage, "newAwardCostShare.costSharePercentage", "50");
        setFieldValue(awardTimeAndMoneyPage, "newAwardCostShare.costShareTypeCode", "1");
        setFieldValue(awardTimeAndMoneyPage, "newAwardCostShare.fiscalYear", "2008");
        setFieldValue(awardTimeAndMoneyPage, "newAwardCostShare.source", "12345");
        setFieldValue(awardTimeAndMoneyPage, "newAwardCostShare.destination", "54321");
        setFieldValue(awardTimeAndMoneyPage, "newAwardCostShare.commitmentAmount", "10000");
        
        final HtmlForm form1 = (HtmlForm) awardTimeAndMoneyPage.getForms().get(0);        
        String completeButtonName1=getImageTagName(awardTimeAndMoneyPage, "methodToCall.addCostShare");        
        final HtmlImageInput button1 = (HtmlImageInput) form1.getInputByName(completeButtonName1);
        HtmlPage awardTimeAndMoneyPageAfterAddingCostShare = (HtmlPage) button1.click();
        
        setFieldValue(awardTimeAndMoneyPageAfterAddingCostShare, "newAwardCostShare.costSharePercentage", "50");
        setFieldValue(awardTimeAndMoneyPageAfterAddingCostShare, "newAwardCostShare.costShareTypeCode", "1");
        setFieldValue(awardTimeAndMoneyPageAfterAddingCostShare, "newAwardCostShare.fiscalYear", "2008");
        setFieldValue(awardTimeAndMoneyPageAfterAddingCostShare, "newAwardCostShare.source", "6789");
        setFieldValue(awardTimeAndMoneyPageAfterAddingCostShare, "newAwardCostShare.destination", "9876");
        setFieldValue(awardTimeAndMoneyPageAfterAddingCostShare, "newAwardCostShare.commitmentAmount", "20000");
        
        final HtmlForm form2 = (HtmlForm) awardTimeAndMoneyPageAfterAddingCostShare.getForms().get(0);        
        String completeButtonName2=getImageTagName(awardTimeAndMoneyPageAfterAddingCostShare, "methodToCall.addCostShare");        
        final HtmlImageInput button2 = (HtmlImageInput) form2.getInputByName(completeButtonName2);
        HtmlPage awardTimeAndMoneyPageAfterAddingTwoCostShares = (HtmlPage) button2.click();
        
        HtmlPage awardTimeAndMoneyPageAfterSave = clickOn(awardTimeAndMoneyPageAfterAddingTwoCostShares, "methodToCall.save");
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);        
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERRORS_FOUND_ON_PAGE);
        assertContains(awardTimeAndMoneyPageAfterSave,SAVE_SUCCESS_MESSAGE);        
    }
    
    /**
     * 
     * This method tests deletion of Cost Share from the Award Time & Money page.
     * @throws Exception
     */
    @Test
    public void testAwardCostSharePanelDeleteCostShare() throws Exception{
        setFieldValue(awardTimeAndMoneyPage, "newAwardCostShare.costSharePercentage", "50");
        setFieldValue(awardTimeAndMoneyPage, "newAwardCostShare.costShareTypeCode", "1");
        setFieldValue(awardTimeAndMoneyPage, "newAwardCostShare.fiscalYear", "2008");
        setFieldValue(awardTimeAndMoneyPage, "newAwardCostShare.source", "2468");
        setFieldValue(awardTimeAndMoneyPage, "newAwardCostShare.destination", "8642");
        setFieldValue(awardTimeAndMoneyPage, "newAwardCostShare.commitmentAmount", "30000");
        
        final HtmlForm form1 = (HtmlForm) awardTimeAndMoneyPage.getForms().get(0);        
        String completeButtonName1=getImageTagName(awardTimeAndMoneyPage, "methodToCall.addCostShare");        
        final HtmlImageInput button1 = (HtmlImageInput) form1.getInputByName(completeButtonName1);
        HtmlPage awardTimeAndMoneyPageAfterAddingCostShare = (HtmlPage) button1.click();
        
        setFieldValue(awardTimeAndMoneyPageAfterAddingCostShare, "newAwardCostShare.costSharePercentage", "50");
        setFieldValue(awardTimeAndMoneyPageAfterAddingCostShare, "newAwardCostShare.costShareTypeCode", "1");
        setFieldValue(awardTimeAndMoneyPageAfterAddingCostShare, "newAwardCostShare.fiscalYear", "2008");
        setFieldValue(awardTimeAndMoneyPageAfterAddingCostShare, "newAwardCostShare.source", "57347");
        setFieldValue(awardTimeAndMoneyPageAfterAddingCostShare, "newAwardCostShare.destination", "4621");
        setFieldValue(awardTimeAndMoneyPageAfterAddingCostShare, "newAwardCostShare.commitmentAmount", "40000");
        
        final HtmlForm form2 = (HtmlForm) awardTimeAndMoneyPageAfterAddingCostShare.getForms().get(0);        
        String completeButtonName2=getImageTagName(awardTimeAndMoneyPageAfterAddingCostShare, "methodToCall.addCostShare");        
        final HtmlImageInput button2 = (HtmlImageInput) form2.getInputByName(completeButtonName2);
        HtmlPage awardTimeAndMoneyPageAfterAddingTwoCostShares = (HtmlPage) button2.click();
        
        HtmlPage awardTimeAndMoneyPageAfterSave = clickOn(awardTimeAndMoneyPageAfterAddingTwoCostShares, "methodToCall.save");
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);        
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERRORS_FOUND_ON_PAGE);
        assertContains(awardTimeAndMoneyPageAfterSave,SAVE_SUCCESS_MESSAGE);  
        
        //simulate clicking yes in confirm page(found correct methodToCall in html source code).
        HtmlPage awardTimeAndMoneyPageConfirm1 = clickOn(awardTimeAndMoneyPageAfterSave, "methodToCall.deleteCostShare.line1.anchor3");
        HtmlPage awardTimeAndMoneyPageAfterDelete1 = clickOn(awardTimeAndMoneyPageConfirm1, "methodToCall.processAnswer.button0");
        //simulate clicking yes in confirm page(found correct methodToCall in html source code).
        HtmlPage awardTimeAndMoneyPageConfirm2 = clickOn(awardTimeAndMoneyPageAfterDelete1, "methodToCall.deleteCostShare.line0.anchor3");
        HtmlPage awardTimeAndMoneyPageAfterDelete2 = clickOn(awardTimeAndMoneyPageConfirm2, "methodToCall.processAnswer.button0");
        
        HtmlPage awardTimeAndMoneyPageAfterSaveAgain = clickOn(awardTimeAndMoneyPageAfterDelete2, "methodToCall.save");
        
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);
        assertDoesNotContain(awardTimeAndMoneyPageAfterSaveAgain, ERRORS_FOUND_ON_PAGE);
        assertContains(awardTimeAndMoneyPageAfterSaveAgain,SAVE_SUCCESS_MESSAGE);
                
    }
    
    /**
     * 
     * This method tests the recalculate on Award Cost Share Recalculate on Time & Money page.
     * @throws Exception
     */
    @Test
    public void testAwardCostShareRecalculate() throws Exception{
        
        setFieldValue(awardTimeAndMoneyPage, "newAwardCostShare.costSharePercentage", "50");
        setFieldValue(awardTimeAndMoneyPage, "newAwardCostShare.costShareTypeCode", "1");
        setFieldValue(awardTimeAndMoneyPage, "newAwardCostShare.fiscalYear", "2008");
        setFieldValue(awardTimeAndMoneyPage, "newAwardCostShare.source", "879678");
        setFieldValue(awardTimeAndMoneyPage, "newAwardCostShare.destination", "123412");
        setFieldValue(awardTimeAndMoneyPage, "newAwardCostShare.commitmentAmount", "50000");
        
        final HtmlForm form1 = (HtmlForm) awardTimeAndMoneyPage.getForms().get(0);        
        String completeButtonName1=getImageTagName(awardTimeAndMoneyPage, "methodToCall.addCostShare");        
        final HtmlImageInput button1 = (HtmlImageInput) form1.getInputByName(completeButtonName1);
        HtmlPage awardTimeAndMoneyPageAfterAddingCostShare = (HtmlPage) button1.click();
        
        setFieldValue(awardTimeAndMoneyPageAfterAddingCostShare, "newAwardCostShare.costSharePercentage", "50");
        setFieldValue(awardTimeAndMoneyPageAfterAddingCostShare, "newAwardCostShare.costShareTypeCode", "1");
        setFieldValue(awardTimeAndMoneyPageAfterAddingCostShare, "newAwardCostShare.fiscalYear", "2008");
        setFieldValue(awardTimeAndMoneyPageAfterAddingCostShare, "newAwardCostShare.source", "89768");
        setFieldValue(awardTimeAndMoneyPageAfterAddingCostShare, "newAwardCostShare.destination", "3421341");
        setFieldValue(awardTimeAndMoneyPageAfterAddingCostShare, "newAwardCostShare.commitmentAmount", "60000");
        
        final HtmlForm form2 = (HtmlForm) awardTimeAndMoneyPageAfterAddingCostShare.getForms().get(0);        
        String completeButtonName2=getImageTagName(awardTimeAndMoneyPageAfterAddingCostShare, "methodToCall.addCostShare");        
        final HtmlImageInput button2 = (HtmlImageInput) form2.getInputByName(completeButtonName2);
        HtmlPage awardTimeAndMoneyPageAfterAddingTwoCostShares = (HtmlPage) button2.click();
        
        HtmlPage awardTimeAndMoneyPageAfterSave = clickOn(awardTimeAndMoneyPageAfterAddingTwoCostShares, "methodToCall.save");
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);        
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERRORS_FOUND_ON_PAGE);
        assertContains(awardTimeAndMoneyPageAfterSave,SAVE_SUCCESS_MESSAGE);        
        
        setFieldValue(awardTimeAndMoneyPageAfterSave,"document.awardList[0].awardCostShares[0].commitmentAmount","10000");
        setFieldValue(awardTimeAndMoneyPageAfterSave,"document.awardList[0].awardCostShares[1].commitmentAmount","12345.00");
        
        HtmlPage awardTimeAndMoneyPageAfterRecalculate = clickOn(awardTimeAndMoneyPageAfterSave, "methodToCall.recalculateCostShareTotal.anchor");
        System.out.println(awardTimeAndMoneyPageAfterRecalculate.asText());
        assertContains(awardTimeAndMoneyPageAfterRecalculate,"22345");
    }
    
    /**
     * 
     * This method tests the comments functionality on Award CostShare panel
     *  
     * @throws Exception
     */
    @Test
    public void testAwardCostSharePanelComments() throws Exception {
        
        setFieldValue(awardTimeAndMoneyPage, "newAwardCostShare.costSharePercentage", "50");
        setFieldValue(awardTimeAndMoneyPage, "newAwardCostShare.costShareTypeCode", "1");
        setFieldValue(awardTimeAndMoneyPage, "newAwardCostShare.fiscalYear", "2008");
        setFieldValue(awardTimeAndMoneyPage, "newAwardCostShare.source", "7568657");
        setFieldValue(awardTimeAndMoneyPage, "newAwardCostShare.destination", "576434");
        setFieldValue(awardTimeAndMoneyPage, "newAwardCostShare.commitmentAmount", "70000");
        
        final HtmlForm form1 = (HtmlForm) awardTimeAndMoneyPage.getForms().get(0);        
        String completeButtonName1=getImageTagName(awardTimeAndMoneyPage, "methodToCall.addCostShare");        
        final HtmlImageInput button1 = (HtmlImageInput) form1.getInputByName(completeButtonName1);
        HtmlPage awardTimeAndMoneyPageAfterAddingCostShare = (HtmlPage) button1.click();
        
        setFieldValue(awardTimeAndMoneyPageAfterAddingCostShare, "newAwardCostShare.costSharePercentage", "50");
        setFieldValue(awardTimeAndMoneyPageAfterAddingCostShare, "newAwardCostShare.costShareTypeCode", "1");
        setFieldValue(awardTimeAndMoneyPageAfterAddingCostShare, "newAwardCostShare.fiscalYear", "2008");
        setFieldValue(awardTimeAndMoneyPageAfterAddingCostShare, "newAwardCostShare.source", "555555");
        setFieldValue(awardTimeAndMoneyPageAfterAddingCostShare, "newAwardCostShare.destination", "777777");
        setFieldValue(awardTimeAndMoneyPageAfterAddingCostShare, "newAwardCostShare.commitmentAmount", "80000");
        
        final HtmlForm form2 = (HtmlForm) awardTimeAndMoneyPageAfterAddingCostShare.getForms().get(0);        
        String completeButtonName2=getImageTagName(awardTimeAndMoneyPageAfterAddingCostShare, "methodToCall.addCostShare");        
        final HtmlImageInput button2 = (HtmlImageInput) form2.getInputByName(completeButtonName2);
        HtmlPage awardTimeAndMoneyPageAfterAddingTwoCostShares = (HtmlPage) button2.click();
        
        HtmlPage awardTimeAndMoneyPageAfterSave = clickOn(awardTimeAndMoneyPageAfterAddingTwoCostShares, "methodToCall.save");
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);        
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERRORS_FOUND_ON_PAGE);
        assertContains(awardTimeAndMoneyPageAfterSave,SAVE_SUCCESS_MESSAGE);        
        
        
        setFieldValue(awardTimeAndMoneyPageAfterSave, "document.awardList[0].awardCostShareComment.comments", "We are testing Comments");
        
        HtmlPage awardTimeAndMoneyPageAfterAnotherSave = clickOn(awardTimeAndMoneyPageAfterSave, "methodToCall.save");
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);
        assertDoesNotContain(awardTimeAndMoneyPageAfterAnotherSave, ERRORS_FOUND_ON_PAGE);
        assertContains(awardTimeAndMoneyPageAfterAnotherSave,SAVE_SUCCESS_MESSAGE);
        HtmlPage awardTimeAndMoneyPageAfterAnotherReload = clickOn(awardTimeAndMoneyPageAfterAnotherSave, "methodToCall.reload");
        assertContains(awardTimeAndMoneyPageAfterAnotherReload,"We are testing Comments");
        
    }
  
}
