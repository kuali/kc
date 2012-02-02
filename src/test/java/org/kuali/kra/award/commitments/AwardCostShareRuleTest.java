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
package org.kuali.kra.award.commitments;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.costshare.CostShareService;
import org.kuali.kra.costshare.CostShareServiceTest;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

/**
 * This class tests <code>AwardCostShareRule</code>
 */
public class AwardCostShareRuleTest extends KcUnitTestBase {
    
    private static final String TEST_SOURCE = "54321";
    private static final String TEST_DESTINATION = "12345";
    private static final String TEST_FISCAL_YEAR = "2008";
    private static final String TEST_INVALID_FISCAL_YEAR = "1000";
    private static final Integer PERCENTAGE = 50;
    private static final Integer COMMITMENT_AMOUNT = 10000;
    AwardCostShareRuleImpl awardCostShareRule;
    AwardCostShare awardCostShare;
    AwardDocument awardDocument;
    AwardCostShareRuleEvent awardCostShareRuleEvent;
    
    /**
     * This method...
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        awardCostShareRule = new AwardCostShareRuleImpl();
        awardCostShare = new AwardCostShare();
        awardCostShare.setCostSharePercentage(new KualiDecimal(PERCENTAGE));
        awardCostShare.setProjectPeriod(TEST_FISCAL_YEAR);
        awardCostShare.setDestination(TEST_DESTINATION);
        awardCostShare.setSource(TEST_SOURCE);
        awardCostShare.setCommitmentAmount(new KualiDecimal(COMMITMENT_AMOUNT));
        GlobalVariables.setMessageMap(new MessageMap());
          
    }
    
    /**
     * This method...
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        awardCostShareRule = null;
        awardCostShare = null;
    }
    
    /**
     * Test method for {@link org.kuali.kra.award.commitments.AwardCostShareRule#processCommonValidations
     * (org.kuali.kra.award.commitments.AwardCostShare)}.
     */
    @Test
    public final void testProcessCommonValidations() {
        Assert.assertTrue(awardCostShareRule.processCommonValidations(awardCostShare));
    }
    
    /**
     * Test method for {@link org.kuali.kra.award.commitments.AwardCostShareRule#validateCostShareSourceAndDestinationForEquality
     * (org.kuali.kra.award.commitments.AwardCostShare)}.
     */
    @Test
    public final void testValidateCostShareSourceAndDestinationForEquality() {
        Assert.assertTrue
            (awardCostShareRule.validateCostShareSourceAndDestinationForEquality(awardCostShare));
        awardCostShare.setSource(TEST_DESTINATION);
        Assert.assertFalse
            (awardCostShareRule.validateCostShareSourceAndDestinationForEquality(awardCostShare));
        awardCostShare.setSource(TEST_SOURCE);
    }
    
    /**
     * Test method for {@link org.kuali.kra.award.commitments.AwardCostShareRule#validateCostShareFiscalYearRange
     * (org.kuali.kra.award.commitments.AwardCostShare)}.
     */
    /* Test ignored due to Parameter Cache flushing issue in Rice 2.0.0-RC1 */
    @Ignore
    @Test
    public final void testValidateCostShareFiscalYearRange() {
        updateParameterForTesting(CostShareServiceTest.class, "CostShareProjectPeriodNameLabel", "Fiscal Year");        
        CostShareService costShareService = KraServiceLocator.getService(CostShareService.class);
        costShareService.getCostShareLabel();
        awardCostShareRule.setCostShareService(costShareService);
        
        Assert.assertTrue(awardCostShareRule.validateCostShareFiscalYearRange(awardCostShare));
        awardCostShare.setProjectPeriod(TEST_INVALID_FISCAL_YEAR);
        Assert.assertFalse(awardCostShareRule.validateCostShareFiscalYearRange(awardCostShare));
        awardCostShare.setSource(TEST_FISCAL_YEAR);
    }
    
    


}
