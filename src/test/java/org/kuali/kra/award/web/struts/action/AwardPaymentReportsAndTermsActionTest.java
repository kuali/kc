/*
 * Copyright 2005-2013 The Kuali Foundation
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

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardSponsorTerm;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;

import java.sql.Date;

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
    public static final int THREE = 3;
    
    AwardPaymentReportsAndTermsAction awardPaymentReportsAndTermsAction;
    Award award;
    AwardReportTerm awardReportTerm1;
    AwardReportTerm awardReportTerm2;
    AwardReportTerm awardReportTerm3;

    AwardSponsorTerm awardSponsorTerm1;
    AwardSponsorTerm awardSponsorTerm2;
    AwardSponsorTerm awardSponsorTerm3;

    @Before
    public void setUp() throws Exception {
        awardPaymentReportsAndTermsAction = new AwardPaymentReportsAndTermsAction();
        award = new Award();
        awardReportTerm1 = new AwardReportTerm();
        awardReportTerm2 = new AwardReportTerm();
        awardReportTerm3 = new AwardReportTerm();

        awardSponsorTerm1 = new AwardSponsorTerm();
        awardSponsorTerm2 = new AwardSponsorTerm();
        awardSponsorTerm3 = new AwardSponsorTerm();

        
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

    @After
    public void tearDown() throws Exception {
        awardPaymentReportsAndTermsAction = null;
        award = null;
        awardReportTerm1 = null;
        awardReportTerm2 = null;
        awardReportTerm3 = null;

        awardSponsorTerm1 = null;
        awardSponsorTerm1 = null;
        awardSponsorTerm1 = null;

    }

    /**
     * This method tests add functionality of AwardPaymentsReportsAndTerms Add AwardSponsorTerm.
     */
    @Test
    public void testAddAwardSponsorTermToAward(){
        awardPaymentReportsAndTermsAction.addAwardSponsorTermToAward(award, awardSponsorTerm1);
        awardPaymentReportsAndTermsAction.addAwardSponsorTermToAward(award, awardSponsorTerm2);
        awardPaymentReportsAndTermsAction.addAwardSponsorTermToAward(award, awardSponsorTerm3);
        Assert.assertEquals(THREE, award.getAwardSponsorTerms().size());
    }
    
    /**
     * This method tests delete functionality of AwardPaymentsReportsAndTerms delete AwardSponsorTerm.
     */
    @Test
    public void testDeleteAwardSponsorTermFromAward(){
        awardPaymentReportsAndTermsAction.addAwardSponsorTermToAward(award, awardSponsorTerm1);
        awardPaymentReportsAndTermsAction.addAwardSponsorTermToAward(award, awardSponsorTerm2);
        awardPaymentReportsAndTermsAction.deleteAwardSponsorTermFromAward(award, 1);
        Assert.assertEquals(1, award.getAwardSponsorTerms().size());
    }
}
