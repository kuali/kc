/*
 * Copyright 2005-2014 The Kuali Foundation
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
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This class tests <code>AwardCostShareRule</code>
 */
public class AwardCostShareAuditRuleTest extends KcIntegrationTestBase {
    
    private static final String TEST_SOURCE = "54321";
    private static final String TEST_DESTINATION = "12345";
    private static final String TEST_FISCAL_YEAR = "2008";
    private static final String TEST_FISCAL_YEAR_2 = "2009";
    private static final Integer TEST_COST_SHARE_TYPE = 1;
    private static final Integer PERCENTAGE = 50;
    private static final Integer COMMITMENT_AMOUNT = 10000;
    AwardCostShareAuditRule awardCostShareAuditRule;
    List<AwardCostShare> awardCostShares = new ArrayList<AwardCostShare>();

    @Before
    public void setUp() throws Exception {
        awardCostShareAuditRule = new AwardCostShareAuditRule();
        AwardCostShare awardCostShare = new AwardCostShare();
        awardCostShare.setCostSharePercentage(new KualiDecimal(PERCENTAGE));
        awardCostShare.setCostShareTypeCode(TEST_COST_SHARE_TYPE);
        awardCostShare.setProjectPeriod(TEST_FISCAL_YEAR);
        awardCostShare.setDestination(TEST_DESTINATION);
        awardCostShare.setSource(TEST_SOURCE);
        awardCostShare.setCommitmentAmount(new KualiDecimal(COMMITMENT_AMOUNT));
        awardCostShares.add(awardCostShare);
        awardCostShare = new AwardCostShare();
        awardCostShare.setCostSharePercentage(new KualiDecimal(PERCENTAGE));
        awardCostShare.setCostShareTypeCode(TEST_COST_SHARE_TYPE);
        awardCostShare.setProjectPeriod(TEST_FISCAL_YEAR_2);
        awardCostShare.setDestination(TEST_DESTINATION);
        awardCostShare.setSource(TEST_SOURCE);
        awardCostShare.setCommitmentAmount(new KualiDecimal(COMMITMENT_AMOUNT));
        awardCostShares.add(awardCostShare);
        GlobalVariables.setMessageMap(new MessageMap());
          
    }

    @After
    public void tearDown() throws Exception {
        awardCostShareAuditRule = null;
        awardCostShares = null;
    }
   
    @Test
    public void testCostShareUniqueCheck() throws Exception {
        assertTrue(awardCostShareAuditRule.validateCostShareDoesNotViolateUniqueConstraint(awardCostShares));
        awardCostShares.get(1).setProjectPeriod(TEST_FISCAL_YEAR);
        assertFalse(awardCostShareAuditRule.validateCostShareDoesNotViolateUniqueConstraint(awardCostShares));
    }
    


}
