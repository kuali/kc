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
 * This is the integration test for Award Cost Share Tab. 
 */
@org.junit.Ignore("This test is not meant to be run against the 2.0 release")
public class AwardCostShareWebTest extends AwardTimeAndMoneyWebTest {
    
    private static final String COSTSHARE_FIELD_PREFIX = "costShareFormHelper.newAwardCostShare.";
    private static final String ONE = "1";
    private static final String FIFTY = "50";
    private static final String TWO_THOUSAND_EIGHT = "2008";
    private static final String ADD_COST_SHARE_METHOD = "methodToCall.addCostShare";
    private static final String SAVE_COST_SHARE_METHOD = "methodToCall.save";
    private static final String RELOAD_METHOD = "methodToCall.reload";
    private static final String PROCESS_ANSWER_METHOD = "methodToCall.processAnswer.button0";
    private static final String DELETE_METHOD = "methodToCall.deleteCostShare.line0.anchor3";
    private static final String TEST_COMMENTS = "we are testing comments.";
    
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
        
        setCostShareFieldValues(awardTimeAndMoneyPage,FIFTY,ONE,TWO_THOUSAND_EIGHT,"12345","54321","100000");
        
        HtmlForm form1 = (HtmlForm) awardTimeAndMoneyPage.getForms().get(0);        
        String completeButtonName1=getImageTagName(awardTimeAndMoneyPage, ADD_COST_SHARE_METHOD);        
        HtmlImageInput button1 = (HtmlImageInput) form1.getInputByName(completeButtonName1);
        HtmlPage awardTimeAndMoneyPageAfterAddingCostShare = (HtmlPage) button1.click();
        
        setCostShareFieldValues(awardTimeAndMoneyPageAfterAddingCostShare,
                                FIFTY,ONE,TWO_THOUSAND_EIGHT,"6789","9876","20000");

        HtmlForm form2 = (HtmlForm) awardTimeAndMoneyPageAfterAddingCostShare.getForms().get(0);        
        String completeButtonName2=getImageTagName(awardTimeAndMoneyPageAfterAddingCostShare,
                                                    ADD_COST_SHARE_METHOD);        
        HtmlImageInput button2 = (HtmlImageInput) form2.getInputByName(completeButtonName2);
        HtmlPage awardTimeAndMoneyPageAfterAddingTwoCostShares = (HtmlPage) button2.click();
        
        HtmlPage awardTimeAndMoneyPageAfterSave = 
            clickOn(awardTimeAndMoneyPageAfterAddingTwoCostShares, SAVE_COST_SHARE_METHOD);
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
        
        setCostShareFieldValues(awardTimeAndMoneyPage,FIFTY,ONE,TWO_THOUSAND_EIGHT,"2468","8642","30000");
        
        HtmlForm form1 = (HtmlForm) awardTimeAndMoneyPage.getForms().get(0);        
        String completeButtonName1=getImageTagName(awardTimeAndMoneyPage, ADD_COST_SHARE_METHOD);        
        HtmlImageInput button1 = (HtmlImageInput) form1.getInputByName(completeButtonName1);
        HtmlPage awardTimeAndMoneyPageAfterAddingCostShare = (HtmlPage) button1.click();
        
        setCostShareFieldValues(awardTimeAndMoneyPageAfterAddingCostShare,
                                FIFTY,ONE,TWO_THOUSAND_EIGHT,"57347","4621","40000");
        
        HtmlForm form2 = (HtmlForm) awardTimeAndMoneyPageAfterAddingCostShare.getForms().get(0);        
        String completeButtonName2=getImageTagName(awardTimeAndMoneyPageAfterAddingCostShare,
                                                    ADD_COST_SHARE_METHOD);        
        HtmlImageInput button2 = (HtmlImageInput) form2.getInputByName(completeButtonName2);
        HtmlPage awardTimeAndMoneyPageAfterAddingTwoCostShares = (HtmlPage) button2.click();
        
        HtmlPage awardTimeAndMoneyPageAfterSave = clickOn(awardTimeAndMoneyPageAfterAddingTwoCostShares,
                                                            SAVE_COST_SHARE_METHOD);
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);        
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERRORS_FOUND_ON_PAGE);
        assertContains(awardTimeAndMoneyPageAfterSave,SAVE_SUCCESS_MESSAGE);  
        
        //simulate clicking yes in confirm page(found correct methodToCall in html source code).
        HtmlPage awardTimeAndMoneyPageConfirm1 = clickOn(awardTimeAndMoneyPageAfterSave,
                                                            DELETE_METHOD);
        HtmlPage awardTimeAndMoneyPageAfterDelete1 = clickOn(awardTimeAndMoneyPageConfirm1,
                                                                PROCESS_ANSWER_METHOD);
        //simulate clicking yes in confirm page(found correct methodToCall in html source code).
        HtmlPage awardTimeAndMoneyPageConfirm2 = clickOn(awardTimeAndMoneyPageAfterDelete1,
                                                            DELETE_METHOD);
        HtmlPage awardTimeAndMoneyPageAfterDelete2 = clickOn(awardTimeAndMoneyPageConfirm2,
                                                              PROCESS_ANSWER_METHOD);
        
        HtmlPage awardTimeAndMoneyPageAfterSaveAgain = clickOn(awardTimeAndMoneyPageAfterDelete2, 
                                                                SAVE_COST_SHARE_METHOD);
        
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
        
        setCostShareFieldValues(awardTimeAndMoneyPage,FIFTY,ONE,TWO_THOUSAND_EIGHT,"766575","32424","50000");
        
        HtmlForm form1 = (HtmlForm) awardTimeAndMoneyPage.getForms().get(0);        
        String completeButtonName1=getImageTagName(awardTimeAndMoneyPage, ADD_COST_SHARE_METHOD);        
        HtmlImageInput button1 = (HtmlImageInput) form1.getInputByName(completeButtonName1);
        HtmlPage awardTimeAndMoneyPageAfterAddingCostShare = (HtmlPage) button1.click();
        
        setCostShareFieldValues(awardTimeAndMoneyPageAfterAddingCostShare,
                                    FIFTY,ONE,TWO_THOUSAND_EIGHT,"89768","345612","60000");

        
        HtmlForm form2 = (HtmlForm) awardTimeAndMoneyPageAfterAddingCostShare.getForms().get(0);        
        String completeButtonName2=getImageTagName(awardTimeAndMoneyPageAfterAddingCostShare, 
                                                    ADD_COST_SHARE_METHOD);        
        HtmlImageInput button2 = (HtmlImageInput) form2.getInputByName(completeButtonName2);
        HtmlPage awardTimeAndMoneyPageAfterAddingTwoCostShares = (HtmlPage) button2.click();
        
        HtmlPage awardTimeAndMoneyPageAfterSave = clickOn(awardTimeAndMoneyPageAfterAddingTwoCostShares, 
                                                            SAVE_COST_SHARE_METHOD);
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);        
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERRORS_FOUND_ON_PAGE);
        assertContains(awardTimeAndMoneyPageAfterSave,SAVE_SUCCESS_MESSAGE);        
        
        setFieldValue(awardTimeAndMoneyPageAfterSave,
                        "document.awardList[0].awardCostShares[0].commitmentAmount","10000");
        setFieldValue(awardTimeAndMoneyPageAfterSave,
                        "document.awardList[0].awardCostShares[1].commitmentAmount","12345.00");
        
        HtmlPage awardTimeAndMoneyPageAfterRecalculate = 
                clickOn(awardTimeAndMoneyPageAfterSave,"methodToCall.recalculateCostShareTotal.anchorCostShare");
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
        
        setCostShareFieldValues(awardTimeAndMoneyPage,
                                FIFTY,ONE,TWO_THOUSAND_EIGHT,"7568657","576434","70000");
        
        HtmlForm form1 = (HtmlForm) awardTimeAndMoneyPage.getForms().get(0);        
        String completeButtonName1=getImageTagName(awardTimeAndMoneyPage, ADD_COST_SHARE_METHOD);        
        HtmlImageInput button1 = (HtmlImageInput) form1.getInputByName(completeButtonName1);
        HtmlPage awardTimeAndMoneyPageAfterAddingCostShare = (HtmlPage) button1.click();
        
        setCostShareFieldValues(awardTimeAndMoneyPageAfterAddingCostShare,
                                FIFTY,ONE,TWO_THOUSAND_EIGHT,"555555","777777","80000");
        
        HtmlForm form2 = (HtmlForm) awardTimeAndMoneyPageAfterAddingCostShare.getForms().get(0);        
        String completeButtonName2=getImageTagName(awardTimeAndMoneyPageAfterAddingCostShare, 
                                                    ADD_COST_SHARE_METHOD);        
        HtmlImageInput button2 = (HtmlImageInput) form2.getInputByName(completeButtonName2);
        HtmlPage awardTimeAndMoneyPageAfterAddingTwoCostShares = (HtmlPage) button2.click();
        
        HtmlPage awardTimeAndMoneyPageAfterSave = 
                clickOn(awardTimeAndMoneyPageAfterAddingTwoCostShares, SAVE_COST_SHARE_METHOD);
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);        
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERRORS_FOUND_ON_PAGE);
        assertContains(awardTimeAndMoneyPageAfterSave,SAVE_SUCCESS_MESSAGE);        
        
        
        setFieldValue(awardTimeAndMoneyPageAfterSave,
                      "document.awardList[0].awardCostShareComment.comments", 
                      TEST_COMMENTS);
        
        HtmlPage awardTimeAndMoneyPageAfterAnotherSave = 
            clickOn(awardTimeAndMoneyPageAfterSave, SAVE_COST_SHARE_METHOD);
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);
        assertDoesNotContain(awardTimeAndMoneyPageAfterAnotherSave, ERRORS_FOUND_ON_PAGE);
        assertContains(awardTimeAndMoneyPageAfterAnotherSave,SAVE_SUCCESS_MESSAGE);
        HtmlPage awardTimeAndMoneyPageAfterAnotherReload = 
            clickOn(awardTimeAndMoneyPageAfterAnotherSave, RELOAD_METHOD);
        assertContains(awardTimeAndMoneyPageAfterAnotherReload,TEST_COMMENTS); 
    }
    
    public void setCostShareFieldValues(HtmlPage htmlPage, String costSharePercentage, 
                                        String costShareTypeCode, String fiscalYear, 
                                        String source, String destination, String commitmentAmount){
        setFieldValue(htmlPage, COSTSHARE_FIELD_PREFIX + "costSharePercentage", costSharePercentage);
        setFieldValue(htmlPage, COSTSHARE_FIELD_PREFIX + "costShareTypeCode", costShareTypeCode);
        setFieldValue(htmlPage, COSTSHARE_FIELD_PREFIX + "fiscalYear", fiscalYear);
        setFieldValue(htmlPage, COSTSHARE_FIELD_PREFIX + "source", source);
        setFieldValue(htmlPage, COSTSHARE_FIELD_PREFIX + "destination", destination);
        setFieldValue(htmlPage, COSTSHARE_FIELD_PREFIX + "commitmentAmount", commitmentAmount);
    }
  
}
