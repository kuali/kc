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
 * This is the integration test for Award Time and Money Page. 
 */
@SuppressWarnings("unchecked")
public class AwardTimeAndMoneyWebTest extends AwardWebTestBase{
    
    HtmlPage awardTimeAndMoneyPage;

    /**
     * The set up method calls the parent super method and gets the 
     * award Time and Money page after that.
     * @see org.kuali.kra.award.htmlunitwebtest.AwardWebTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        awardTimeAndMoneyPage = getAwardTimeAndMoneyPage();
    }

    /**
     * This method calls parent tear down method and than sets awardTimeAndMoneyPage to null
     * @see org.kuali.kra.award.htmlunitwebtest.AwardWebTestBase#tearDown()
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        awardTimeAndMoneyPage = null;
    }
    
    /**
     * 
     * This method tests the adding of 2 F & A Rates (on and off campus) 
     * and saving them.  
     * @throws Exception
     */
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
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);        
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERRORS_FOUND_ON_PAGE);
        assertContains(awardTimeAndMoneyPageAfterSave,SAVE_SUCCESS_MESSAGE);        
    }
    
    /**
     * 
     * This method tests the business rule that says
     * F&A rates must be input in pairs(both on and off campus rates).
     * 
     * @throws Exception
     */
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
    
    /**
     * 
     * This method tests deletion of F & A Rate from the Award Time & Money page.
     * @throws Exception
     */
    @Test
    public void testAwardFandaRatePanelDeleteFandaRate() throws Exception{
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
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERRORS_FOUND_ON_PAGE);
        assertContains(awardTimeAndMoneyPageAfterSave,SAVE_SUCCESS_MESSAGE);
        
        HtmlPage awardTimeAndMoneyPageAfterDelete1 = clickOn(awardTimeAndMoneyPageAfterSave, "methodToCall.deleteFandaRate.line1.anchor4");
        HtmlPage awardTimeAndMoneyPageAfterDelete2 = clickOn(awardTimeAndMoneyPageAfterDelete1, "methodToCall.deleteFandaRate.line0.anchor4");
        
        HtmlPage awardTimeAndMoneyPageAfterSaveAgain = clickOn(awardTimeAndMoneyPageAfterDelete2, "methodToCall.save");
        
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);
        assertDoesNotContain(awardTimeAndMoneyPageAfterSaveAgain, ERRORS_FOUND_ON_PAGE);
        assertContains(awardTimeAndMoneyPageAfterSaveAgain,SAVE_SUCCESS_MESSAGE);
                
    }
    
    /**
     * 
     * This method tests the recalculate on Award F & A Rate on Time & Money page.
     * @throws Exception
     */
    @Test
    public void testAwardFandaRateRecalculate() throws Exception{
        
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
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERRORS_FOUND_ON_PAGE);
        assertContains(awardTimeAndMoneyPageAfterSave,SAVE_SUCCESS_MESSAGE);
        
        setFieldValue(awardTimeAndMoneyPageAfterSave,"document.awardList[0].awardFandaRate[0].underrecoveryOfIndirectCost","2000.00");
        setFieldValue(awardTimeAndMoneyPageAfterSave,"document.awardList[0].awardFandaRate[1].underrecoveryOfIndirectCost","1945.00");
        
        HtmlPage awardTimeAndMoneyPageAfterRecalculate = clickOn(awardTimeAndMoneyPageAfterSave, "methodToCall.recalculateFandARate");
        System.out.println(awardTimeAndMoneyPageAfterRecalculate.asText());
        assertContains(awardTimeAndMoneyPageAfterRecalculate,"3945");
    }
    
    /**
     * 
     * This method tests the comments functionality on Award F&A Rate panel
     *  
     * @throws Exception
     */
    @Test
    public void testAwardFandaRatePanelComments() throws Exception {
        
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
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERRORS_FOUND_ON_PAGE);
        assertContains(awardTimeAndMoneyPageAfterSave,SAVE_SUCCESS_MESSAGE);
        
        
        setFieldValue(awardTimeAndMoneyPageAfterSave, "document.award.awardFandaRateComment.comments", "We are testing Comments");
        
        HtmlPage awardTimeAndMoneyPageAfterAnotherSave = clickOn(awardTimeAndMoneyPageAfterSave, "methodToCall.save");
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);
        assertDoesNotContain(awardTimeAndMoneyPageAfterAnotherSave, ERRORS_FOUND_ON_PAGE);
        assertContains(awardTimeAndMoneyPageAfterAnotherSave,SAVE_SUCCESS_MESSAGE);
        HtmlPage awardTimeAndMoneyPageAfterAnotherReload = clickOn(awardTimeAndMoneyPageAfterAnotherSave, "methodToCall.reload");
        assertContains(awardTimeAndMoneyPageAfterAnotherReload,"We are testing Comments");
        
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
        setFieldValue(awardTimeAndMoneyPageAfterAddingCostShare, "newAwardCostShare.commitmentAmount", "10000");
        
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
        setFieldValue(awardTimeAndMoneyPageAfterAddingCostShare, "newAwardCostShare.commitmentAmount", "10000");
        
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
        setFieldValue(awardTimeAndMoneyPageAfterAddingCostShare, "newAwardCostShare.commitmentAmount", "10000");
        
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
        setFieldValue(awardTimeAndMoneyPageAfterAddingCostShare, "newAwardCostShare.commitmentAmount", "10000");
        
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
    
    /**
     * 
     * This method tests Preaward Authorizations Panel.  Adding amounts and effective dates. 
     * and saving them.  
     * @throws Exception
     */
    @Test
    public void testPreawardAuthorizationsPanel() throws Exception{
        
        setFieldValue(awardTimeAndMoneyPage, "document.awardList[0].preAwardAuthorizedAmount", "10000");
        setFieldValue(awardTimeAndMoneyPage, "document.awardList[0].preAwardEffectiveDate", "01/18/2009");
        setFieldValue(awardTimeAndMoneyPage, "document.awardList[0].preAwardInstitutionalAuthorizedAmount", "25000");
        setFieldValue(awardTimeAndMoneyPage, "document.awardList[0].preAwardInstitutionalEffectiveDate", "01/18/2009");
        
        HtmlPage awardTimeAndMoneyPageAfterSave = clickOn(awardTimeAndMoneyPage, "methodToCall.save");
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERRORS_FOUND_ON_PAGE);
        assertContains(awardTimeAndMoneyPageAfterSave,SAVE_SUCCESS_MESSAGE);
        
    }
    
    /**
     * 
     * This method tests Preaward Sponsor Authorization Comments
     * @throws Exception
     */
    @Test
    public void testPreawardAuthorizationComments() throws Exception{
        
        setFieldValue(awardTimeAndMoneyPage, "document.awardList[0].preAwardAuthorizedAmount", "10000");
        setFieldValue(awardTimeAndMoneyPage, "document.awardList[0].preAwardEffectiveDate", "01/19/2009");
        setFieldValue(awardTimeAndMoneyPage, "document.awardList[0].preAwardInstitutionalAuthorizedAmount", "25000");
        setFieldValue(awardTimeAndMoneyPage, "document.awardList[0].preAwardInstitutionalEffectiveDate", "01/19/2009");
        
        HtmlPage awardTimeAndMoneyPageAfterSave = clickOn(awardTimeAndMoneyPage, "methodToCall.save");
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERRORS_FOUND_ON_PAGE);
        assertContains(awardTimeAndMoneyPageAfterSave,SAVE_SUCCESS_MESSAGE);
        
        setFieldValue(awardTimeAndMoneyPageAfterSave, "document.awardList[0].awardPreAwardSponsorAuthorizationComment.comments", "We are testing Sponsor Authorization Comments");
        
        HtmlPage awardTimeAndMoneyPageAfterAnotherSave = clickOn(awardTimeAndMoneyPageAfterSave, "methodToCall.save");
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);
        assertDoesNotContain(awardTimeAndMoneyPageAfterAnotherSave, ERRORS_FOUND_ON_PAGE);
        assertContains(awardTimeAndMoneyPageAfterAnotherSave,SAVE_SUCCESS_MESSAGE);
        HtmlPage awardTimeAndMoneyPageAfterAnotherReload = clickOn(awardTimeAndMoneyPageAfterAnotherSave, "methodToCall.reload");
        assertContains(awardTimeAndMoneyPageAfterAnotherReload,"We are testing Sponsor Authorization Comments");
        
    }
    
    /**
     * 
     * This method tests Preaward Institutional Authorization Comments 
     * @throws Exception
     */
     
    @Test
    public void testPreawardInstitutionalAuthorizationComments() throws Exception{
        
        setFieldValue(awardTimeAndMoneyPage, "document.awardList[0].preAwardAuthorizedAmount", "10000");
        setFieldValue(awardTimeAndMoneyPage, "document.awardList[0].preAwardEffectiveDate", "01/20/2009");
        setFieldValue(awardTimeAndMoneyPage, "document.awardList[0].preAwardInstitutionalAuthorizedAmount", "25000");
        setFieldValue(awardTimeAndMoneyPage, "document.awardList[0].preAwardInstitutionalEffectiveDate", "01/20/2009");
        
        HtmlPage awardTimeAndMoneyPageAfterSave = clickOn(awardTimeAndMoneyPage, "methodToCall.save");
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERRORS_FOUND_ON_PAGE);
        assertContains(awardTimeAndMoneyPageAfterSave,SAVE_SUCCESS_MESSAGE);
        
        setFieldValue(awardTimeAndMoneyPageAfterSave, "document.awardList[0].awardPreAwardInstitutionalAuthorizationComment.comments", "We are testing Institutional Authorization Comments");
        
        HtmlPage awardTimeAndMoneyPageAfterAnotherSave = clickOn(awardTimeAndMoneyPageAfterSave, "methodToCall.save");
        assertDoesNotContain(awardTimeAndMoneyPageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);
        assertDoesNotContain(awardTimeAndMoneyPageAfterAnotherSave, ERRORS_FOUND_ON_PAGE);
        assertContains(awardTimeAndMoneyPageAfterAnotherSave,SAVE_SUCCESS_MESSAGE);
        HtmlPage awardTimeAndMoneyPageAfterAnotherReload = clickOn(awardTimeAndMoneyPageAfterAnotherSave, "methodToCall.reload");
        assertContains(awardTimeAndMoneyPageAfterAnotherReload,"We are testing Institutional Authorization Comments");
        
    }
        
    
    

}
