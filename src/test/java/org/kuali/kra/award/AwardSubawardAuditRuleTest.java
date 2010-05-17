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
package org.kuali.kra.award;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.approvedsubawards.AwardApprovedSubaward;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KualiDecimal;

public class AwardSubawardAuditRuleTest extends KraTestBase {

    private AwardSubawardAuditRule auditRule;
    private AwardDocument awardDoc;
    private Award award;
    private AwardApprovedSubaward subAwardOrg1;
    private AwardApprovedSubaward subAwardOrg2;
    private AwardApprovedSubaward subAward2Org1;
    

    @Before
    public void setUp() throws Exception {
        super.setUp();
        auditRule = new AwardSubawardAuditRule();
        awardDoc = new AwardDocument();
        award = new Award();
        awardDoc.setAward(award);
        subAwardOrg1 = new AwardApprovedSubaward();
        subAwardOrg1.setOrganizationName("Org1");
        subAwardOrg1.setAmount(new KualiDecimal(1000.00));
        subAwardOrg2 = new AwardApprovedSubaward();
        subAwardOrg2.setOrganizationName("Org2");
        subAwardOrg2.setAmount(new KualiDecimal(1000.00));
        subAward2Org1 = new AwardApprovedSubaward();
        subAward2Org1.setOrganizationName("Org1");
        subAward2Org1.setAmount(new KualiDecimal(1000.00));              
        subAwardOrg2.setAmount(new KualiDecimal(1000.00));
        award.getAwardApprovedSubawards().clear();
        award.getAwardApprovedSubawards().add(subAwardOrg1);
        award.getAwardApprovedSubawards().add(subAwardOrg2);
        
        GlobalVariables.getAuditErrorMap().clear();        
    }
    
    @Test
    public void testNoErrors() throws Exception {
        assertTrue(auditRule.processRunAuditBusinessRules(awardDoc));
        assertTrue(GlobalVariables.getAuditErrorMap().isEmpty());
    }

    @Test
    public void testNegativeValueWarning() throws Exception {
        subAwardOrg2.setAmount(new KualiDecimal(-1000.00));
        assertFalse(auditRule.processRunAuditBusinessRules(awardDoc));
        assertFalse(GlobalVariables.getAuditErrorMap().isEmpty());
    }
    
    @Test
    public void testNullAmount() throws Exception {
        subAwardOrg2.setAmount(null);
        assertFalse(auditRule.processRunAuditBusinessRules(awardDoc));
        //assumes data dictionary will catch this error and does not add error
        assertTrue(GlobalVariables.getAuditErrorMap().isEmpty());
    }
    
    @Test
    public void testDuplicateOrg() throws Exception {
        award.getAwardApprovedSubawards().add(subAward2Org1);
        assertFalse(auditRule.processRunAuditBusinessRules(awardDoc));
        assertFalse(GlobalVariables.getAuditErrorMap().isEmpty());        
    }
}
