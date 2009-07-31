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
package org.kuali.kra.institutionalproposal.htmlunitwebtest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlImageInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * This class is integration tests for Institutional Proposal Cost Share panel.
 */
public class InstitutionalProposalCostShareWebTest extends InstitutionalProposalDistributionWebTest {

    private static final String COSTSHARE_FIELD_PREFIX = "institutionalProposalCostShareBean.newInstitutionalProposalCostShare.";
    private static final String ONE = "1";
    private static final String FIFTY = "50";
    private static final String TWO_THOUSAND_EIGHT = "2008";
    private static final String ADD_COST_SHARE_METHOD = "methodToCall.addCostShare";
    private static final String SAVE_COST_SHARE_METHOD = "methodToCall.save";
    private static final String RELOAD_METHOD = "methodToCall.reload";
    private static final String PROCESS_ANSWER_METHOD = "methodToCall.processAnswer.button0";
    private static final String DELETE_METHOD = "methodToCall.deleteCostShare.line0.anchor";
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
    public void testInstitutionalProposalCostShareAddCostShare() throws Exception{
        
        setCostShareFieldValues(proposalDistributionPage,FIFTY,ONE,TWO_THOUSAND_EIGHT,"12345","100000");
        
        HtmlForm form1 = (HtmlForm) proposalDistributionPage.getForms().get(0);        
        String completeButtonName1=getImageTagName(proposalDistributionPage, ADD_COST_SHARE_METHOD);        
        HtmlImageInput button1 = (HtmlImageInput) form1.getInputByName(completeButtonName1);
        HtmlPage proposalDistributionPageAfterAddingCostShare = (HtmlPage) button1.click();
        
        setCostShareFieldValues(proposalDistributionPageAfterAddingCostShare,
                                FIFTY,ONE,TWO_THOUSAND_EIGHT,"6789","20000");

        HtmlForm form2 = (HtmlForm) proposalDistributionPageAfterAddingCostShare.getForms().get(0);        
        String completeButtonName2=getImageTagName(proposalDistributionPageAfterAddingCostShare,
                                                    ADD_COST_SHARE_METHOD);        
        HtmlImageInput button2 = (HtmlImageInput) form2.getInputByName(completeButtonName2);
        HtmlPage proposalDistributionPageAfterAddingTwoCostShares = (HtmlPage) button2.click();
        
        HtmlPage proposalDistributionPageAfterSave = 
            clickOn(proposalDistributionPageAfterAddingTwoCostShares, SAVE_COST_SHARE_METHOD);
        assertDoesNotContain(proposalDistributionPageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);        
        assertDoesNotContain(proposalDistributionPageAfterSave, ERRORS_FOUND_ON_PAGE);
        assertContains(proposalDistributionPageAfterSave,SAVE_SUCCESS_MESSAGE);        
    }
    
    /**
     * 
     * This method tests deletion of Cost Share from the Award Time & Money page.
     * @throws Exception
     */
    @Test
    public void testInstitutionalProposalCostSharePanelDeleteCostShare() throws Exception{
        
        setCostShareFieldValues(proposalDistributionPage,FIFTY,ONE,TWO_THOUSAND_EIGHT,"2468","30000");
        
        HtmlForm form1 = (HtmlForm) proposalDistributionPage.getForms().get(0);        
        String completeButtonName1=getImageTagName(proposalDistributionPage, ADD_COST_SHARE_METHOD);        
        HtmlImageInput button1 = (HtmlImageInput) form1.getInputByName(completeButtonName1);
        HtmlPage proposalDistributionPageAfterAddingCostShare = (HtmlPage) button1.click();
        
        setCostShareFieldValues(proposalDistributionPageAfterAddingCostShare,
                                FIFTY,ONE,TWO_THOUSAND_EIGHT,"57347","40000");
        
        HtmlForm form2 = (HtmlForm) proposalDistributionPageAfterAddingCostShare.getForms().get(0);        
        String completeButtonName2=getImageTagName(proposalDistributionPageAfterAddingCostShare,
                                                    ADD_COST_SHARE_METHOD);        
        HtmlImageInput button2 = (HtmlImageInput) form2.getInputByName(completeButtonName2);
        HtmlPage proposalDistributionPageAfterAddingTwoCostShares = (HtmlPage) button2.click();
        
        HtmlPage proposalDistributionPageAfterSave = clickOn(proposalDistributionPageAfterAddingTwoCostShares,
                                                            SAVE_COST_SHARE_METHOD);
        assertDoesNotContain(proposalDistributionPageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);        
        assertDoesNotContain(proposalDistributionPageAfterSave, ERRORS_FOUND_ON_PAGE);
        assertContains(proposalDistributionPageAfterSave,SAVE_SUCCESS_MESSAGE);  
        
        //simulate clicking yes in confirm page(found correct methodToCall in html source code).
        HtmlPage proposalDistributionPageConfirm1 = clickOn(proposalDistributionPageAfterSave,
                                                            DELETE_METHOD);
        HtmlPage proposalDistributionPageAfterDelete1 = clickOn(proposalDistributionPageConfirm1,
                                                                PROCESS_ANSWER_METHOD);
        //simulate clicking yes in confirm page(found correct methodToCall in html source code).
        HtmlPage proposalDistributionPageConfirm2 = clickOn(proposalDistributionPageAfterDelete1,
                                                            DELETE_METHOD);
        HtmlPage proposalDistributionPageAfterDelete2 = clickOn(proposalDistributionPageConfirm2,
                                                              PROCESS_ANSWER_METHOD);
        
        HtmlPage proposalDistributionPageAfterSaveAgain = clickOn(proposalDistributionPageAfterDelete2, 
                                                                SAVE_COST_SHARE_METHOD);
        
        assertDoesNotContain(proposalDistributionPageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);
        assertDoesNotContain(proposalDistributionPageAfterSaveAgain, ERRORS_FOUND_ON_PAGE);
        assertContains(proposalDistributionPageAfterSaveAgain,SAVE_SUCCESS_MESSAGE);
                
    }
    
    /**
     * 
     * This method tests the recalculate on Award Cost Share Recalculate on Time & Money page.
     * @throws Exception
     */
    @Test
    public void testInstitutionalProposalCostShareRecalculate() throws Exception{
        
        setCostShareFieldValues(proposalDistributionPage,FIFTY,ONE,TWO_THOUSAND_EIGHT,"766575","50000");
        
        HtmlForm form1 = (HtmlForm) proposalDistributionPage.getForms().get(0);        
        String completeButtonName1=getImageTagName(proposalDistributionPage, ADD_COST_SHARE_METHOD);        
        HtmlImageInput button1 = (HtmlImageInput) form1.getInputByName(completeButtonName1);
        HtmlPage proposalDistributionPageAfterAddingCostShare = (HtmlPage) button1.click();
        
        setCostShareFieldValues(proposalDistributionPageAfterAddingCostShare,
                                    FIFTY,ONE,TWO_THOUSAND_EIGHT,"89768","60000");

        
        HtmlForm form2 = (HtmlForm) proposalDistributionPageAfterAddingCostShare.getForms().get(0);        
        String completeButtonName2=getImageTagName(proposalDistributionPageAfterAddingCostShare, 
                                                    ADD_COST_SHARE_METHOD);        
        HtmlImageInput button2 = (HtmlImageInput) form2.getInputByName(completeButtonName2);
        HtmlPage proposalDistributionPageAfterAddingTwoCostShares = (HtmlPage) button2.click();
        
        HtmlPage proposalDistributionPageAfterSave = clickOn(proposalDistributionPageAfterAddingTwoCostShares, 
                                                            SAVE_COST_SHARE_METHOD);
        assertDoesNotContain(proposalDistributionPageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);        
        assertDoesNotContain(proposalDistributionPageAfterSave, ERRORS_FOUND_ON_PAGE);
        assertContains(proposalDistributionPageAfterSave,SAVE_SUCCESS_MESSAGE);        
        
        setFieldValue(proposalDistributionPageAfterSave,
                        "document.institutionalProposal.institutionalProposalCostShares[0].amount","10000");
        setFieldValue(proposalDistributionPageAfterSave,
                        "document.institutionalProposal.institutionalProposalCostShares[1].amount","12345.00");
        
        HtmlPage awardTimeAndMoneyPageAfterRecalculate = 
                clickOn(proposalDistributionPageAfterSave,"methodToCall.recalculateCostShareTotal.anchor");
        System.out.println(awardTimeAndMoneyPageAfterRecalculate.asText());
        assertContains(awardTimeAndMoneyPageAfterRecalculate,"22345");
    }
    
    
    public void setCostShareFieldValues(HtmlPage htmlPage, String costSharePercentage, 
                                        String costShareTypeCode, String fiscalYear, 
                                        String sourceAccount, String amount){
        setFieldValue(htmlPage, COSTSHARE_FIELD_PREFIX + "costSharePercentage", costSharePercentage);
        setFieldValue(htmlPage, COSTSHARE_FIELD_PREFIX + "costShareTypeCode", costShareTypeCode);
        setFieldValue(htmlPage, COSTSHARE_FIELD_PREFIX + "fiscalYear", fiscalYear);
        setFieldValue(htmlPage, COSTSHARE_FIELD_PREFIX + "sourceAccount", sourceAccount);
        setFieldValue(htmlPage, COSTSHARE_FIELD_PREFIX + "amount", amount);
    }
  
}

