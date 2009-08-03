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
 * This class...
 */
public class InstitutionalProposalUnrecoveredFandAWebTest extends InstitutionalProposalDistributionWebTest {

    private static final String UNRECOVERED_FNA_FIELD_PREFIX = "institutionalProposalUnrecoveredFandABean.newInstitutionalProposalUnrecoveredFandA.";
    private static final String ONE = "1";
    private static final String FIFTY = "50";
    private static final String TWO_THOUSAND_EIGHT = "2008";
    private static final String ADD_UNRECOVERED_FNA_METHOD = "methodToCall.addUnrecoveredFandA";
    private static final String SAVE_UNRECOVERED_FNA_METHOD = "methodToCall.save";
    private static final String PROCESS_ANSWER_METHOD = "methodToCall.processAnswer.button0";
    private static final String DELETE_METHOD = "methodToCall.deleteUnrecoveredFandA.line0.anchor1";
    
    /**
     * The set up method calls the parent super method.
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
    public void testInstitutionalProposalUnrecoveredFandAAddUnrecoveredFandA() throws Exception{
        
        setUnrecoveredFandAFieldValues(proposalDistributionPage,FIFTY,ONE,TWO_THOUSAND_EIGHT,"12345","100000");
        
        HtmlForm form1 = (HtmlForm) proposalDistributionPage.getForms().get(0);        
        String completeButtonName1=getImageTagName(proposalDistributionPage, ADD_UNRECOVERED_FNA_METHOD);        
        HtmlImageInput button1 = (HtmlImageInput) form1.getInputByName(completeButtonName1);
        HtmlPage proposalDistributionPageAfterAddingUnrecoveredFandA = (HtmlPage) button1.click();
        
        setUnrecoveredFandAFieldValues(proposalDistributionPageAfterAddingUnrecoveredFandA,
                                FIFTY,ONE,TWO_THOUSAND_EIGHT,"6789","20000");

        HtmlForm form2 = (HtmlForm) proposalDistributionPageAfterAddingUnrecoveredFandA.getForms().get(0);        
        String completeButtonName2=getImageTagName(proposalDistributionPageAfterAddingUnrecoveredFandA,
                ADD_UNRECOVERED_FNA_METHOD);        
        HtmlImageInput button2 = (HtmlImageInput) form2.getInputByName(completeButtonName2);
        HtmlPage proposalDistributionPageAfterAddingTwoUnrecoveredFandAs = (HtmlPage) button2.click();
        
        HtmlPage proposalDistributionPageAfterSave = 
            clickOn(proposalDistributionPageAfterAddingTwoUnrecoveredFandAs, SAVE_UNRECOVERED_FNA_METHOD);
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
    public void testInstitutionalProposalUnrecoveredFandADeleteUnrecoveredFandA() throws Exception{
        
        setUnrecoveredFandAFieldValues(proposalDistributionPage,FIFTY,ONE,TWO_THOUSAND_EIGHT,"2468","30000");
        
        HtmlForm form1 = (HtmlForm) proposalDistributionPage.getForms().get(0);        
        String completeButtonName1=getImageTagName(proposalDistributionPage, ADD_UNRECOVERED_FNA_METHOD);        
        HtmlImageInput button1 = (HtmlImageInput) form1.getInputByName(completeButtonName1);
        HtmlPage proposalDistributionPageAfterAddingUnrecoveredFandA = (HtmlPage) button1.click();
        
        setUnrecoveredFandAFieldValues(proposalDistributionPageAfterAddingUnrecoveredFandA,
                                FIFTY,ONE,TWO_THOUSAND_EIGHT,"57347","40000");
        
        HtmlForm form2 = (HtmlForm) proposalDistributionPageAfterAddingUnrecoveredFandA.getForms().get(0);        
        String completeButtonName2=getImageTagName(proposalDistributionPageAfterAddingUnrecoveredFandA,
                ADD_UNRECOVERED_FNA_METHOD);        
        HtmlImageInput button2 = (HtmlImageInput) form2.getInputByName(completeButtonName2);
        HtmlPage proposalDistributionPageAfterAddingTwoUnrecoveredFandAs = (HtmlPage) button2.click();
        
        HtmlPage proposalDistributionPageAfterSave = clickOn(proposalDistributionPageAfterAddingTwoUnrecoveredFandAs, SAVE_UNRECOVERED_FNA_METHOD);
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
        
        HtmlPage proposalDistributionPageAfterSaveAgain = clickOn(proposalDistributionPageAfterDelete2, SAVE_UNRECOVERED_FNA_METHOD);
        
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
    public void testInstitutionalProposalUnrecoveredFandARecalculate() throws Exception{
        
        setUnrecoveredFandAFieldValues(proposalDistributionPage,FIFTY,ONE,TWO_THOUSAND_EIGHT,"766575","50000");
        
        HtmlForm form1 = (HtmlForm) proposalDistributionPage.getForms().get(0);        
        String completeButtonName1=getImageTagName(proposalDistributionPage, ADD_UNRECOVERED_FNA_METHOD);        
        HtmlImageInput button1 = (HtmlImageInput) form1.getInputByName(completeButtonName1);
        HtmlPage proposalDistributionPageAfterAddingUnrecoveredFandA = (HtmlPage) button1.click();
        
        setUnrecoveredFandAFieldValues(proposalDistributionPageAfterAddingUnrecoveredFandA,
                                    FIFTY,ONE,TWO_THOUSAND_EIGHT,"89768","60000");

        
        HtmlForm form2 = (HtmlForm) proposalDistributionPageAfterAddingUnrecoveredFandA.getForms().get(0);        
        String completeButtonName2=getImageTagName(proposalDistributionPageAfterAddingUnrecoveredFandA, 
                ADD_UNRECOVERED_FNA_METHOD);        
        HtmlImageInput button2 = (HtmlImageInput) form2.getInputByName(completeButtonName2);
        HtmlPage proposalDistributionPageAfterAddingTwoUnrecoveredFandAs = (HtmlPage) button2.click();
        
        HtmlPage proposalDistributionPageAfterSave = clickOn(proposalDistributionPageAfterAddingTwoUnrecoveredFandAs, 
                SAVE_UNRECOVERED_FNA_METHOD);
        assertDoesNotContain(proposalDistributionPageAfterSave, ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST);        
        assertDoesNotContain(proposalDistributionPageAfterSave, ERRORS_FOUND_ON_PAGE);
        assertContains(proposalDistributionPageAfterSave,SAVE_SUCCESS_MESSAGE);        
        
        setFieldValue(proposalDistributionPageAfterSave,
                        "document.institutionalProposal.institutionalProposalUnrecoveredFandAs[0].underrecoveryOfIndirectcost","10000");
        setFieldValue(proposalDistributionPageAfterSave,
                        "document.institutionalProposal.institutionalProposalUnrecoveredFandAs[1].underrecoveryOfIndirectcost","12345.00");
        
        HtmlPage awardDistributionPageAfterRecalculate = 
                clickOn(proposalDistributionPageAfterSave,"methodToCall.recalculateUnrecoveredFandATotal.anchorUnrecoveredFA");
        System.out.println(awardDistributionPageAfterRecalculate.asText());
        assertContains(awardDistributionPageAfterRecalculate,"22345");
    }
    
    
    public void setUnrecoveredFandAFieldValues(HtmlPage htmlPage, String applicableIndirectcostRate, 
                                        String indirectcostRateTypeCode, String fiscalYear, 
                                        String sourceAccount, String underrecoveryOfIndirectcost){
        setFieldValue(htmlPage, UNRECOVERED_FNA_FIELD_PREFIX + "applicableIndirectcostRate", applicableIndirectcostRate);
        setFieldValue(htmlPage, UNRECOVERED_FNA_FIELD_PREFIX + "indirectcostRateTypeCode", indirectcostRateTypeCode);
        setFieldValue(htmlPage, UNRECOVERED_FNA_FIELD_PREFIX + "fiscalYear", fiscalYear);
        setFieldValue(htmlPage, UNRECOVERED_FNA_FIELD_PREFIX + "sourceAccount", sourceAccount);
        setFieldValue(htmlPage, UNRECOVERED_FNA_FIELD_PREFIX + "underrecoveryOfIndirectcost", underrecoveryOfIndirectcost);
    }
}
