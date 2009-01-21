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
package org.kuali.kra.award.web.struts.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.bo.AwardReportTerm;

/**
 * 
 * This class tests the non-struts methods in AwardPaymentReportsAndTermsAction.
 */
public class AwardPaymentReportsAndTermsActionTest {
    
    public static final String MOCK_REPORT_CODE1 = "5";
    public static final String MOCK_REPORT_CODE2 = "10";
    public static final String MOCK_REPORT_CODE3 = "15";
    public static final String MOCK_REPORT_CLASS_CODE = "2";
    public static final String MOCK_FREQUENCY_CODE = "2";
    public static final String MOCK_FREQUENCY_BASE_CODE = "2";
    public static final String MOCK_OSP_DISTRIBUTION_CODE = "2";
    public static final String MOCK_DUE_DATE = "1214852613046";
    public static final String MOCK_CONTACT_TYPE_CODE = "2";
    public static final int MOCK_ROLODEX_ID = 2;
    public static final int MOCK_NUMBER_OF_COPIES = 2;
    public static final int MOCK_EXPECTED_SIZE_OF_AWARD_REPORT_TERM_LIST = 6;
    public static final int MOCK_EXPECTED_SIZE_OF_AWARD_REPORT_TERM_LIST_TO_BE_DELETED = 4;
    
    AwardPaymentReportsAndTermsAction awardPaymentReportsAndTermsAction;
    Award award;
    AwardReportTerm awardReportTerm1;
    AwardReportTerm awardReportTerm2;
    AwardReportTerm awardReportTerm3;
    AwardReportTerm awardReportTermRecipient1;
    AwardReportTerm awardReportTermRecipient2;
    AwardReportTerm awardReportTermRecipient3;
    List<AwardReportTerm> awardReportTerms;
    
    @Before
    public void setUp() throws Exception {
        awardPaymentReportsAndTermsAction = new AwardPaymentReportsAndTermsAction();
        award = new Award();
        awardReportTerms = new ArrayList<AwardReportTerm>();
        awardReportTerm1 = new AwardReportTerm();
        awardReportTerm2 = new AwardReportTerm();
        awardReportTerm3 = new AwardReportTerm();
        awardReportTermRecipient1 = new AwardReportTerm();
        awardReportTermRecipient2 = new AwardReportTerm();
        awardReportTermRecipient3 = new AwardReportTerm();
        
        awardReportTerm1 = 
            initializeAwardReportTermWithMockValues(awardReportTerm1, MOCK_REPORT_CLASS_CODE, 
                    MOCK_REPORT_CODE1,MOCK_FREQUENCY_CODE, MOCK_FREQUENCY_BASE_CODE, 
                    MOCK_OSP_DISTRIBUTION_CODE, MOCK_DUE_DATE);
        awardReportTerm2 = 
            initializeAwardReportTermWithMockValues(awardReportTerm2, MOCK_REPORT_CLASS_CODE, 
                    MOCK_REPORT_CODE2, MOCK_FREQUENCY_CODE, MOCK_FREQUENCY_BASE_CODE, 
                    MOCK_OSP_DISTRIBUTION_CODE, MOCK_DUE_DATE);
        awardReportTerm3 = 
            initializeAwardReportTermWithMockValues(awardReportTerm3, MOCK_REPORT_CLASS_CODE, 
                    MOCK_REPORT_CODE3, MOCK_FREQUENCY_CODE, MOCK_FREQUENCY_BASE_CODE, 
                    MOCK_OSP_DISTRIBUTION_CODE, MOCK_DUE_DATE);
        
        awardReportTermRecipient1 = 
            initializeAwardReportTermRecipientWithMockValues(awardReportTermRecipient1, 
                    MOCK_REPORT_CLASS_CODE, MOCK_REPORT_CODE1, MOCK_FREQUENCY_CODE, MOCK_FREQUENCY_BASE_CODE, 
                    MOCK_OSP_DISTRIBUTION_CODE, MOCK_CONTACT_TYPE_CODE, MOCK_ROLODEX_ID, 
                    MOCK_NUMBER_OF_COPIES, MOCK_DUE_DATE);
        awardReportTermRecipient2 = 
            initializeAwardReportTermRecipientWithMockValues(awardReportTermRecipient2, 
                    MOCK_REPORT_CLASS_CODE, MOCK_REPORT_CODE1, MOCK_FREQUENCY_CODE, MOCK_FREQUENCY_BASE_CODE, 
                    MOCK_OSP_DISTRIBUTION_CODE, MOCK_CONTACT_TYPE_CODE, MOCK_ROLODEX_ID, 
                    MOCK_NUMBER_OF_COPIES, MOCK_DUE_DATE);
        awardReportTermRecipient3 = 
            initializeAwardReportTermRecipientWithMockValues(awardReportTermRecipient3, 
                    MOCK_REPORT_CLASS_CODE, MOCK_REPORT_CODE1, MOCK_FREQUENCY_CODE, 
                    MOCK_FREQUENCY_BASE_CODE, MOCK_OSP_DISTRIBUTION_CODE,MOCK_CONTACT_TYPE_CODE, 
                    MOCK_ROLODEX_ID, MOCK_NUMBER_OF_COPIES, MOCK_DUE_DATE);        
    }
    
    private AwardReportTerm initializeAwardReportTermWithMockValues(AwardReportTerm awardReportTerm, 
            String reportClassCode, String reportCode,String frequencyCode, String frequencyBaseCode, 
            String ospDistributionCode, String dueDate){
        awardReportTerm.setReportClassCode(reportClassCode);
        awardReportTerm.setReportCode(reportCode);
        awardReportTerm.setFrequencyCode(frequencyCode);
        awardReportTerm.setFrequencyBaseCode(frequencyBaseCode);
        awardReportTerm.setOspDistributionCode(ospDistributionCode);
        awardReportTerm.setDueDate(new Date(new Long(dueDate)));
        return awardReportTerm;
    }
    
    private AwardReportTerm initializeAwardReportTermRecipientWithMockValues(
            AwardReportTerm awardReportTermRecipient,String reportClassCode, String reportCode,String frequencyCode, 
            String frequencyBaseCode, String ospDistributionCode, String contactTypeCode, int rolodexId, 
            int numberOfCopies, String dueDate){        
        awardReportTermRecipient.setReportClassCode(reportClassCode);
        awardReportTermRecipient.setReportCode(reportCode);
        awardReportTermRecipient.setFrequencyCode(frequencyCode);
        awardReportTermRecipient.setFrequencyBaseCode(frequencyBaseCode);
        awardReportTermRecipient.setOspDistributionCode(ospDistributionCode);
        awardReportTermRecipient.setDueDate(new Date(new Long(dueDate)));        
        return awardReportTermRecipient;
    }

    @After
    public void tearDown() throws Exception {
        awardPaymentReportsAndTermsAction = null;
        award = null;
        awardReportTerm1 = null;
        awardReportTerm2 = null;
        awardReportTerm3 = null;
        awardReportTermRecipient1 = null;
        awardReportTermRecipient2 = null;
        awardReportTermRecipient3 = null;
    }

    /*@Test
    public final void testAddAwardReportTermToAward() {
        awardPaymentReportsAndTermsAction.addAwardReportTermToAward(
                award, awardReportTerm1, MOCK_REPORT_CODE1);                
        awardPaymentReportsAndTermsAction.addAwardReportTermToAward(
                award, awardReportTermRecipient1, MOCK_REPORT_CODE1);       
        Assert.assertNull(award.getAwardReportTerms().get(0).getContactTypeCode());
        Assert.assertNotNull(award.getAwardReportTerms().get(1).getContactTypeCode());                
    }*/
    
    @Test
    public final void testGetAwardReportTermRecipientsToBeDeleted(){        
        awardReportTerms = awardPaymentReportsAndTermsAction.addAwardReportTermToAward(
                award, awardReportTerm1, awardReportTerm1.getReportCode());
        awardReportTerms = awardPaymentReportsAndTermsAction.addAwardReportTermToAward(
                award, awardReportTerm2, awardReportTerm2.getReportCode());
        awardReportTerms = awardPaymentReportsAndTermsAction.addAwardReportTermToAward(
                award, awardReportTerm3, awardReportTerm3.getReportCode());
        awardReportTerms = awardPaymentReportsAndTermsAction.addAwardReportTermToAward(
                award, awardReportTermRecipient1, awardReportTermRecipient1.getReportCode());
        awardReportTerms = awardPaymentReportsAndTermsAction.addAwardReportTermToAward(
                award, awardReportTermRecipient2, awardReportTermRecipient2.getReportCode());
        awardReportTerms = awardPaymentReportsAndTermsAction.addAwardReportTermToAward(
                award, awardReportTermRecipient3, awardReportTermRecipient3.getReportCode());
        
        Assert.assertEquals(MOCK_EXPECTED_SIZE_OF_AWARD_REPORT_TERM_LIST, awardReportTerms.size());
        
        /*Assert.assertEquals(MOCK_EXPECTED_SIZE_OF_AWARD_REPORT_TERM_LIST_TO_BE_DELETED,
                awardPaymentReportsAndTermsAction.getAwardReportTermRecipientsToBeDeleted(
                awardReportTerms, awardReportTerm1).size());*/
        
        
    }
  

}
