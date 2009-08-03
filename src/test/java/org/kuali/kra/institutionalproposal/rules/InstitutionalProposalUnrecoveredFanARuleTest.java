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

import java.util.HashMap;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.institutionalproposal.IndirectcostRateType;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalCostShare;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalUnrecoveredFandA;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KualiDecimal;

/**
 * This class...
 */
@RunWith(JMock.class)
public class InstitutionalProposalUnrecoveredFanARuleTest {

    final Map<String, Object> queryMap = new HashMap<String, Object>();
    private Mockery context = new JUnit4Mockery();
    
    private static final String TEST_SOURCE = "54321";
    private static final String TEST_FISCAL_YEAR = "2008";
    private static final String TEST_INVALID_FISCAL_YEAR = "1000";
    private static final Integer PERCENTAGE = 50;
    private static final Integer AMOUNT = 10000;
    private static final Integer RATE_TYPE_CODE = 1;
    InstitutionalProposalUnrecoveredFandARuleImpl institutionalProposalUnrecoveredFandARuleImpl;
    InstitutionalProposalUnrecoveredFandA institutionalProposalUnrecoveredFandA;
    InstitutionalProposalDocument institutionalProposalDocument;
    
    /**
     * This method...
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        institutionalProposalUnrecoveredFandARuleImpl = new InstitutionalProposalUnrecoveredFandARuleImpl();
        institutionalProposalUnrecoveredFandA = new InstitutionalProposalUnrecoveredFandA();
        institutionalProposalUnrecoveredFandA.setApplicableIndirectcostRate(new KualiDecimal(PERCENTAGE));
        institutionalProposalUnrecoveredFandA.setFiscalYear(TEST_FISCAL_YEAR);
        institutionalProposalUnrecoveredFandA.setSourceAccount(TEST_SOURCE);
        institutionalProposalUnrecoveredFandA.setUnderrecoveryOfIndirectcost(new KualiDecimal(AMOUNT));
        institutionalProposalUnrecoveredFandA.setIndirectcostRateTypeCode(RATE_TYPE_CODE);
        queryMap.put("indirectcostRateTypeCode", 1);
        GlobalVariables.setErrorMap(new ErrorMap());
          
    }
    
    /**
     * This method...
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        institutionalProposalUnrecoveredFandARuleImpl = null;
        institutionalProposalUnrecoveredFandA = null;
    }
    
    /**
     * Test method for {@link org.kuali.kra.award.commitments.AwardCostShareRule#processCommonValidations
     * (org.kuali.kra.award.commitments.AwardCostShare)}.
     */
    @Test
    public final void testProcessCommonValidations() {
        final BusinessObjectService MOCKED_BUSINESS_OBJECT_SERVICE;
        MOCKED_BUSINESS_OBJECT_SERVICE = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            one(MOCKED_BUSINESS_OBJECT_SERVICE).countMatching(IndirectcostRateType.class, queryMap); 
            will(returnValue(1));
        }});
        institutionalProposalUnrecoveredFandARuleImpl.setBusinessObjectService(MOCKED_BUSINESS_OBJECT_SERVICE);
        Assert.assertTrue(institutionalProposalUnrecoveredFandARuleImpl.processCommonValidations(institutionalProposalUnrecoveredFandA));
    }
    
    
    /**
     * Test method for {@link org.kuali.kra.award.commitments.AwardCostShareRule#validateCostShareFiscalYearRange
     * (org.kuali.kra.award.commitments.AwardCostShare)}.
     */
    @Test
    public final void testValidateCostShareFiscalYearRange() {
        Assert.assertTrue(institutionalProposalUnrecoveredFandARuleImpl.validateUnrecoveredFandAFiscalYearRange(institutionalProposalUnrecoveredFandA));
        institutionalProposalUnrecoveredFandA.setFiscalYear(TEST_INVALID_FISCAL_YEAR);
        Assert.assertFalse(institutionalProposalUnrecoveredFandARuleImpl.validateUnrecoveredFandAFiscalYearRange(institutionalProposalUnrecoveredFandA));
        institutionalProposalUnrecoveredFandA.setSourceAccount(TEST_FISCAL_YEAR);
    }
}
