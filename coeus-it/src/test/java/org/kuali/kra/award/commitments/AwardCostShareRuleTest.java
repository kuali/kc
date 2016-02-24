/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.award.commitments;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.impl.validation.ErrorReporterImpl;
import org.kuali.coeus.common.framework.costshare.CostShareService;
import org.kuali.kra.costshare.CostShareServiceTest;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

/**
 * This class tests <code>AwardCostShareRule</code>
 */
public class AwardCostShareRuleTest extends KcIntegrationTestBase {
    
    private static final String TEST_SOURCE = "54321";
    private static final String TEST_DESTINATION = "12345";
    private static final String TEST_FISCAL_YEAR = "2008";
    private static final String TEST_INVALID_FISCAL_YEAR = "1000";
    private static final Integer PERCENTAGE = 50;
    private static final Integer COMMITMENT_AMOUNT = 10000;
    AwardCostShareRuleImpl awardCostShareRule;
    AwardCostShare awardCostShare;

    @Before
    public void setUp() throws Exception {
        awardCostShareRule = new AwardCostShareRuleImpl();
        awardCostShareRule.setErrorReporter(new ErrorReporterImpl());
        awardCostShare = new AwardCostShare();
        awardCostShare.setCostSharePercentage(new ScaleTwoDecimal(PERCENTAGE));
        awardCostShare.setProjectPeriod(TEST_FISCAL_YEAR);
        awardCostShare.setDestination(TEST_DESTINATION);
        awardCostShare.setSource(TEST_SOURCE);
        awardCostShare.setCommitmentAmount(new ScaleTwoDecimal(COMMITMENT_AMOUNT));
        GlobalVariables.setMessageMap(new MessageMap());
          
    }

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
    @Test
    public final void testValidateCostShareFiscalYearRange() {
        updateParameterForTesting(CostShareServiceTest.class, "CostShareProjectPeriodNameLabel", "Fiscal Year");        
        CostShareService costShareService = KcServiceLocator.getService(CostShareService.class);
        costShareService.getCostShareLabel();
        awardCostShareRule.setCostShareService(costShareService);
        
        Assert.assertTrue(awardCostShareRule.validateCostShareFiscalYearRange(awardCostShare));
        awardCostShare.setProjectPeriod(TEST_INVALID_FISCAL_YEAR);
        Assert.assertFalse(awardCostShareRule.validateCostShareFiscalYearRange(awardCostShare));
        awardCostShare.setSource(TEST_FISCAL_YEAR);
    }
    
    


}
