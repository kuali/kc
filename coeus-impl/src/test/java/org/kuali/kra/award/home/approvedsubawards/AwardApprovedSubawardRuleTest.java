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
package org.kuali.kra.award.home.approvedsubawards;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class AwardApprovedSubawardRuleTest {
    
    public static final String ORGANIZATION_ONE = "organization one";
    public static final String ORGANIZATION_TWO = "organization two";
    public static final String ORGANIZATION_THREE = "organization three";
    public static final int TEN_THOUSAND = 10000;
    public static final int TWENTY_THOUSAND = 20000;
    public static final int THIRTY_THOUSAND = 30000;

    
    AwardApprovedSubawardRuleImpl awardApprovedSubawardRule;
    AwardApprovedSubaward awardApprovedSubawardOne;
    AwardApprovedSubaward awardApprovedSubawardTwo;
    AwardApprovedSubaward awardApprovedSubawardTest;
    List<AwardApprovedSubaward> awardApprovedSubawards;

    @Before
    public void setUp() throws Exception {
        awardApprovedSubawardRule = new AwardApprovedSubawardRuleImpl();
        awardApprovedSubawardOne = new AwardApprovedSubaward();
        awardApprovedSubawardTwo = new AwardApprovedSubaward();
        awardApprovedSubawardTest = new AwardApprovedSubaward();
        awardApprovedSubawards = new ArrayList<AwardApprovedSubaward>();
        awardApprovedSubawardOne.setOrganizationName(ORGANIZATION_ONE);
        awardApprovedSubawardOne.setAmount(new ScaleTwoDecimal(TEN_THOUSAND));
        awardApprovedSubawardTwo.setOrganizationName(ORGANIZATION_TWO);
        awardApprovedSubawardTwo.setAmount(new ScaleTwoDecimal(TWENTY_THOUSAND));
        awardApprovedSubawardTest.setOrganizationName(ORGANIZATION_THREE);
        awardApprovedSubawardTest.setAmount(new ScaleTwoDecimal(THIRTY_THOUSAND));

        awardApprovedSubawards.add(awardApprovedSubawardOne);
        awardApprovedSubawards.add(awardApprovedSubawardTwo);
        
        awardApprovedSubawardRule.setAwardApprovedSubaward(awardApprovedSubawardTest);
        awardApprovedSubawardRule.setAwardApprovedSubawards(awardApprovedSubawards);
        awardApprovedSubawardRule.setErrorPath("testErrorPath");
        
        
        GlobalVariables.setMessageMap(new MessageMap());
          
    }

    @After
    public void tearDown() throws Exception {
        awardApprovedSubawardRule = null;
        awardApprovedSubawardOne = null;
        awardApprovedSubawardTwo = null;
        awardApprovedSubawardTest = null;
        awardApprovedSubawards = null;
    }
    
    //removed existing test functions as they all dealt with code moved to audit rule
    @Test
    public void foo() throws Exception {
        assertTrue(true);
    }
    
    



}
