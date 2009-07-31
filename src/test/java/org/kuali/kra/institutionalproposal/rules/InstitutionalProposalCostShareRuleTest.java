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
package org.kuali.kra.institutionalproposal.rules;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalCostShare;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KualiDecimal;

/**
 * This class...
 */
public class InstitutionalProposalCostShareRuleTest {

    private static final String TEST_SOURCE = "54321";
    private static final String TEST_FISCAL_YEAR = "2008";
    private static final String TEST_INVALID_FISCAL_YEAR = "1000";
    private static final Integer PERCENTAGE = 50;
    private static final Integer AMOUNT = 10000;
    InstitutionalProposalAddCostShareRuleImpl institutionalProposalAddCostShareRule;
    InstitutionalProposalCostShare institutionalProposalCostShare;
    InstitutionalProposalDocument institutionalProposalDocument;
    InstitutionalProposalAddCostShareRuleEvent institutionalProposalCostShareRuleEvent;
    
    /**
     * This method...
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        institutionalProposalAddCostShareRule = new InstitutionalProposalAddCostShareRuleImpl();
        institutionalProposalCostShare = new InstitutionalProposalCostShare();
        institutionalProposalCostShare.setCostSharePercentage(new KualiDecimal(PERCENTAGE));
        institutionalProposalCostShare.setFiscalYear(TEST_FISCAL_YEAR);
        institutionalProposalCostShare.setSourceAccount(TEST_SOURCE);
        institutionalProposalCostShare.setAmount(new KualiDecimal(AMOUNT));
        GlobalVariables.setErrorMap(new ErrorMap());
          
    }
    
    /**
     * This method...
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        institutionalProposalAddCostShareRule = null;
        institutionalProposalCostShare = null;
    }
    
    /**
     * Test method for {@link org.kuali.kra.award.commitments.AwardCostShareRule#processCommonValidations
     * (org.kuali.kra.award.commitments.AwardCostShare)}.
     */
    @Test
    public final void testProcessCommonValidations() {
        Assert.assertTrue(institutionalProposalAddCostShareRule.processCommonValidations(institutionalProposalCostShare));
    }
    
    
    /**
     * Test method for {@link org.kuali.kra.award.commitments.AwardCostShareRule#validateCostShareFiscalYearRange
     * (org.kuali.kra.award.commitments.AwardCostShare)}.
     */
    @Test
    public final void testValidateCostShareFiscalYearRange() {
        Assert.assertTrue(institutionalProposalAddCostShareRule.validateCostShareFiscalYearRange(institutionalProposalCostShare));
        institutionalProposalCostShare.setFiscalYear(TEST_INVALID_FISCAL_YEAR);
        Assert.assertFalse(institutionalProposalAddCostShareRule.validateCostShareFiscalYearRange(institutionalProposalCostShare));
        institutionalProposalCostShare.setSourceAccount(TEST_FISCAL_YEAR);
    }
    
}

