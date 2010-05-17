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

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.commitments.AwardCostShare;
import org.kuali.kra.award.commitments.AwardCostShareRuleEvent;
import org.kuali.kra.award.commitments.AwardCostShareRuleImpl;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KualiDecimal;

/**
 * This class tests <code>AwardCostShareRule</code>
 */
public class AwardCostShareAuditRuleTest {
    
    private static final String TEST_SOURCE = "54321";
    private static final String TEST_DESTINATION = "12345";
    private static final String TEST_FISCAL_YEAR = "2008";
    private static final String TEST_FISCAL_YEAR_2 = "2009";
    private static final String TEST_INVALID_FISCAL_YEAR = "1000";
    private static final Integer TEST_COST_SHARE_TYPE = 1;
    private static final Integer PERCENTAGE = 50;
    private static final Integer COMMITMENT_AMOUNT = 10000;
    AwardCostShareAuditRule awardCostShareAuditRule;
    List<AwardCostShare> awardCostShares = new ArrayList<AwardCostShare>();
    
    /**
     * This method...
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        awardCostShareAuditRule = new AwardCostShareAuditRule();
        AwardCostShare awardCostShare = new AwardCostShare();
        awardCostShare.setCostSharePercentage(new KualiDecimal(PERCENTAGE));
        awardCostShare.setCostShareTypeCode(TEST_COST_SHARE_TYPE);
        awardCostShare.setFiscalYear(TEST_FISCAL_YEAR);
        awardCostShare.setDestination(TEST_DESTINATION);
        awardCostShare.setSource(TEST_SOURCE);
        awardCostShare.setCommitmentAmount(new KualiDecimal(COMMITMENT_AMOUNT));
        awardCostShares.add(awardCostShare);
        awardCostShare = new AwardCostShare();
        awardCostShare.setCostSharePercentage(new KualiDecimal(PERCENTAGE));
        awardCostShare.setCostShareTypeCode(TEST_COST_SHARE_TYPE);
        awardCostShare.setFiscalYear(TEST_FISCAL_YEAR_2);
        awardCostShare.setDestination(TEST_DESTINATION);
        awardCostShare.setSource(TEST_SOURCE);
        awardCostShare.setCommitmentAmount(new KualiDecimal(COMMITMENT_AMOUNT));
        awardCostShares.add(awardCostShare);
        GlobalVariables.setErrorMap(new ErrorMap());
          
    }
    
    /**
     * This method...
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        awardCostShareAuditRule = null;
        awardCostShares = null;
    }
   
    @Test
    public void testCostShareUniqueCheck() throws Exception {
        assertTrue(awardCostShareAuditRule.validateCostShareDoesNotViolateUniqueConstraint(awardCostShares));
        awardCostShares.get(1).setFiscalYear(TEST_FISCAL_YEAR);
        assertFalse(awardCostShareAuditRule.validateCostShareDoesNotViolateUniqueConstraint(awardCostShares));
    }
    


}
